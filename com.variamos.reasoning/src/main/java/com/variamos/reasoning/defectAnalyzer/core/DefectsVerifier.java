package com.variamos.reasoning.defectAnalyzer.core;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.ProgressMonitor;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.common.core.exceptions.TechnicalException;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.core.HlclUtil;
import com.variamos.hlcl.model.domains.IntDomain;
import com.variamos.hlcl.model.expressions.BooleanNegation;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.reasoning.defectAnalyzer.model.defects.DeadElement;
import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;
import com.variamos.reasoning.defectAnalyzer.model.defects.FalseOptionalElement;
import com.variamos.reasoning.defectAnalyzer.model.defects.FalseProductLine;
import com.variamos.reasoning.defectAnalyzer.model.defects.NonAttainableDomain;
import com.variamos.reasoning.defectAnalyzer.model.defects.Redundancy;
import com.variamos.reasoning.defectAnalyzer.model.defects.VoidModel;
import com.variamos.reasoning.defectAnalyzer.model.dto.VerificationResultDTO;
import com.variamos.reasoning.defectAnalyzer.model.transformation.TransformerConstants;
import com.variamos.reasoning.util.SolverOperationsUtil;
import com.variamos.reasoning.util.VerifierUtilExpression;
import com.variamos.solver.model.ConfigurationOptionsDTO;
import com.variamos.solver.model.SolverSolution;

public class DefectsVerifier implements IntDefectsVerifier {

	// Variables usadas para almacenar información que es útil cuando se hacen
	// otras operaciones de verificación
	private Map<Identifier, Set<Number>> verifiedValuesMap;
	private SolverOperationsUtil solver;
	private HlclProgram model;
	// Hlcl program identifiers
	private Set<Identifier> identifiersList;
	private Component parentComponent = null;
	private String progressDisplay = null;
	private long solverTime = 0;
	private long totalTime = 0;

	public DefectsVerifier(HlclProgram model) {
		verifiedValuesMap = new HashMap<Identifier, Set<Number>>();
		solver = new SolverOperationsUtil();
		this.model = model;
		identifiersList = HlclUtil.getUsedIdentifiers(model);
	}

	public DefectsVerifier(HlclProgram model, Component parentComponent,
			String progressDisplay) {
		verifiedValuesMap = new HashMap<Identifier, Set<Number>>();
		solver = new SolverOperationsUtil();
		this.model = model;
		identifiersList = HlclUtil.getUsedIdentifiers(model);
		this.parentComponent = parentComponent;
		this.progressDisplay = progressDisplay;
	}

	@Override
	public void resetTime() {
		solverTime = 0;
		totalTime = 0;
	}

	@Override
	public Defect isVoid() {

		boolean isVoid = !solver.isSatisfiable(model);
		if (isVoid) {
			return new VoidModel();
		} else {
			return null;
		}

	}

	@Override
	public Defect isFalsePL() {
		boolean isFPL = solver.isFalseProductLine(model);
		if (isFPL) {
			return new FalseProductLine();
		} else {
			return null;
		}
	}

	@Override
	// jcmunoz: new method to support additional constraints in the verification
	// of false product lines
	public List<Defect> getFalsePLs(
			List<IntBooleanExpression> additionalConstraints) {
		List<Defect> out = new ArrayList<Defect>();
		if (additionalConstraints == null || additionalConstraints.size() == 0) {
			boolean isFPL = solver.isFalseProductLine(model);
			if (isFPL)
				out.add(new FalseProductLine());
			return out;
		}

		for (IntBooleanExpression additional : additionalConstraints) {
			HlclProgram testModel = new HlclProgram();
			testModel.addAll(model);
			testModel.add(additional);

			boolean isFPL = solver.isFalseProductLine(testModel);
			if (isFPL)
				out.add(new FalseProductLine(additional));
		}
		return out;
	}

	@Override
	// jcmunoz: new method to support additional constraints in the verification
	// of voids
	public List<Defect> getVoids(
			List<IntBooleanExpression> additionalConstraints) {
		List<Defect> out = new ArrayList<Defect>();
		if (additionalConstraints == null || additionalConstraints.size() == 0) {
			boolean isVoid = !solver.isSatisfiable(model);
			if (isVoid)
				out.add(new VoidModel());
			return out;
		}

		for (IntBooleanExpression additional : additionalConstraints) {
			HlclProgram testModel = new HlclProgram();
			testModel.addAll(model);
			testModel.add(additional);

			boolean isVoid = !solver.isSatisfiable(testModel);
			if (isVoid)
				out.add(new VoidModel(additional));
		}
		return out;
	}

	private boolean existValue(Identifier identifier, int valueToTest) {
		// Cada vez que se hace una configuración se bloquean valores no tener
		// que verificar luego estos valores
		Set<Number> attainableDomains = verifiedValuesMap.get(identifier);
		if (attainableDomains == null
				|| (attainableDomains != null && !attainableDomains
						.contains(valueToTest))) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public List<Defect> getNonAttainableDomains(Identifier identifier)
			throws FunctionalException {

		List<Defect> notAttainableDomains = new ArrayList<Defect>();
		List<Integer> definedDomainValues = null;
		List<IntBooleanExpression> variabilityModelConstraintRepresentation = new ArrayList<IntBooleanExpression>();

		IntBooleanExpression verificationExpression = null;

		// Se verifica si el modelo es vacío
		boolean isSatisfiable = solver.isSatisfiable(model);
		if (isSatisfiable) {

			// Se obtienen los valores parametrizados para esta variable
			definedDomainValues = identifier.getDomain().getPossibleValues();

			// Se busca para cada variable los valores de dominio y se analiza
			// siempre y cuando no este en la lista de valores permitidos
			for (Integer valueToTest : definedDomainValues) {

				variabilityModelConstraintRepresentation.clear();
				if (!existValue(identifier, valueToTest)) {
					// Constraint para verificar el dominio no alcanzable
					verificationExpression = VerifierUtilExpression
							.verifyAssignValueToVariabilityElementExpression(
									identifier, valueToTest);

					// Se adiciona la restricción al conjunto de restricciones
					// que representa el modelo de variabilidad
					ConfigurationOptionsDTO options = new ConfigurationOptionsDTO();
					options.addAdditionalExpression(verificationExpression);

					// Se obtiene una configuración del solver
					SolverSolution configurationResult = solver
							.getConfiguration(model, new SolverSolution(),
									options);

					// Si se obtienen valores esto quiere decir q es
					// satisfacible
					if (configurationResult != null
							&& !configurationResult.getSolverSolution()
									.isEmpty()) {
						// Los valores identificados no son non attainable
						// domains, se actualizan los valores en el mapa
						updateEvaluatedDomainsMap(configurationResult);
					} else {
						// Se crea un nuevo defecto
						NonAttainableDomain defect = new NonAttainableDomain(
								identifier, valueToTest, verificationExpression);
						notAttainableDomains.add(defect);
					}
				}

			}
		} else {
			throw new FunctionalException(
					"Model is void. Unable to verify not attainable dommains");
		}
		return notAttainableDomains;

	}

	private void updateEvaluatedDomainsMap(SolverSolution configuration) {

		TreeMap<String, Number> configurationValues = configuration
				.getSolverSolution();

		Number number = null;

		for (Identifier identifier : identifiersList) {

			// Se busca en la configuración el id del identificador
			if (configurationValues.containsKey(identifier.getId())) {

				number = configurationValues.get(identifier.getId());
				// Se verifica si el mapa de dominios tiene el identifier
				if (verifiedValuesMap.containsKey(identifier)) {
					// Si existe se adiciona el valor de la variable al set de
					// dominios permitidos
					verifiedValuesMap.get(identifier).add(number);
				} else {
					// Si el mapa no contiene el identifier se adiciona
					// al mapa de dominios
					Set<Number> attainableDomainValuesSet = new HashSet<Number>();
					attainableDomainValuesSet.add(number);
					verifiedValuesMap
							.put(identifier, attainableDomainValuesSet);

				}

			} else {
				// Quiere decir que no se encuentran dentro de los elementos
				// para los que se debe verificar el valor
			}

		}

	}

	@Override
	public Defect isDeadElement(Identifier identifier)
			throws FunctionalException {

		return isDeadElement(identifier, new ConfigurationOptionsDTO(),
				new SolverSolution());
	}

	@Override
	public Defect isDeadElement(Identifier identifier,
			ConfigurationOptionsDTO options, SolverSolution configuration)
			throws FunctionalException {

		List<Integer> definedDomainValues = null;
		boolean createDefect = Boolean.TRUE;
		IntDomain domain = identifier.getDomain();
		// Se obtienen los valores parametrizados para esta variable
		definedDomainValues = domain.getPossibleValues();
		SolverSolution configurationResult = null;
		int nonAttainableValue = 0;

		if (configuration == null) {
			configuration = new SolverSolution();
		}
		if (options == null) {
			options = new ConfigurationOptionsDTO();
		}

		for (Integer definedDomainValue : definedDomainValues) {

			if (definedDomainValue > TransformerConstants.NON_SELECTED_VALUE) {
				// Se verifica si ya existe en el mapa el identificador con
				// el valor
				if (!existValue(identifier, definedDomainValue)) {

					SolverSolution copy = new SolverSolution();
					TreeMap<String, Number> configurationValues = new TreeMap<String, Number>();
					configurationValues.putAll(configuration
							.getSolverSolution());
					copy.setSolverSolution(configurationValues);
					copy.set(identifier.getId(), definedDomainValue);

					configurationResult = solver.getConfiguration(model, copy,
							new ConfigurationOptionsDTO());
					solverTime += solver.getLastExecutionTime();
					if (configurationResult != null) {
						// Los valores identificados se actualizan en el
						// mapa para
						// evitar luego consultas innecesarias
						updateEvaluatedDomainsMap(configurationResult);
						createDefect = Boolean.FALSE;
						break;
					} else {
						nonAttainableValue = definedDomainValue;
					}
				} else {
					// no hay defecto
					createDefect = Boolean.FALSE;
					break;
				}
			}
		}
		if (createDefect) {
			IntBooleanExpression verificationExpression = null;
			// Ejm F1 #= 0.
			// Se adiciona las restricciones que tiene el modelo de
			// variabilidad inicial
			verificationExpression = VerifierUtilExpression
					.verifyAssignValueToVariabilityElementExpression(
							identifier, nonAttainableValue);
			// Se crea un nuevo defecto
			DeadElement deadElement = new DeadElement(identifier,
					verificationExpression);
			return deadElement;
		} else {
			return null;
		}

	}

	/* We simpliy the verification operation using partial configurations */
	// public Defect isDeadElement(Identifier identifier)
	// throws FunctionalException {
	//
	// VoidModel isVoid = (VoidModel) isVoid();
	//
	// if (isVoid == null) {
	//
	// List<Integer> definedDomainValues = null;
	// List<BooleanExpression> variabilityModelConstraintRepresentation = new
	// ArrayList<BooleanExpression>();
	// BooleanExpression constraintToAdd = null;
	// BooleanExpression verificationExpression = null;
	// boolean isDead = Boolean.TRUE;
	// Domain domain = identifier.getDomain();
	// // Se obtienen los valores parametrizados para esta variable
	// definedDomainValues = domain.getPossibleValues();
	// boolean isSatisfiable = Boolean.FALSE;
	// // Se busca para cada variable los valores de dominio y se analiza
	// for (Integer definedDomainValue : definedDomainValues) {
	// // Ejm F1 #= 1. Se excluye el 0
	// isSatisfiable = Boolean.FALSE;
	//
	// if (definedDomainValue > TransformerConstants.NON_SELECTED_VALUE) {
	// variabilityModelConstraintRepresentation.clear();
	//
	// constraintToAdd = VerifierUtilExpression
	// .verifyAssignValueToVariabilityElementExpression(
	// identifier, definedDomainValue);
	// // Se adiciona la restricción al conjunto de restricciones
	// // que representa el modelo de variabilidad
	// ConfigurationOptions options = new ConfigurationOptions();
	// options.addAdditionalExpression(constraintToAdd);
	//
	// // Se evalua si el modelo de restricciones es satisfacible
	// isSatisfiable = solver.isSatisfiable(model,
	// new Configuration(), options);
	//
	// if (isSatisfiable) {
	// // Si es satisfacible la feature no es dead y se pasa a
	// // la siguiente feature
	// isDead = Boolean.FALSE;
	//
	// // Se adiciona ese valor como valor permitido para no
	// // verificarlo en los notAttainableDomains
	// if (verifiedValuesMap.containsKey(identifier)) {
	// // Se adiciona el valor a la lista de dominios
	// // permitidos
	// verifiedValuesMap.get(identifier).add(
	// definedDomainValue);
	// } else {
	// // Se adiciona el variabilityElement al mapa y se
	// // adicionan el valor de dominio. Se crea un nuevo
	// // objeto para evitar problemas de referencia entre
	// // objetos
	// Set<Integer> attainableDomainValuesList = new HashSet<Integer>();
	// attainableDomainValuesList.add(definedDomainValue);
	// verifiedValuesMap.put(identifier,
	// attainableDomainValuesList);
	// }
	// // Se termina el ciclo y no se analizan mas valores de
	// // dominio
	// break;
	// } else {
	// verificationExpression = constraintToAdd;
	// }
	// }
	//
	// }
	//
	// if (isDead) {
	// // Se crea un nuevo defecto
	// DeadElement deadElement = new DeadElement(identifier,
	// verificationExpression);
	// System.out.println("dead feature" + identifier.getId());
	// return deadElement;
	// }
	// return null;
	// } else {
	// throw new FunctionalException(
	// "Model is void. Unable to verify dead elements elements");
	// }
	// }

	/* We simpliy the verification operation using partial configurations */

	// public Defect isFalseOptionalElement(Identifier identifier)
	// throws FunctionalException {
	//
	// // Se verifica si ya existe en el mapa el valor de cero para ese
	// // identificador
	// if (!existValue(identifier, 0)) {
	//
	// Configuration configurationResult = solver.getConfiguration(model,
	// new Configuration(), new ConfigurationOptions());
	// BooleanExpression verificationExpression = null;
	// if (configurationResult != null) {
	//
	// // Los valores identificados se actualizan en el mapa para
	// // evitar luego consultas innecesarias
	// updateEvaluatedDomainsMap(configurationResult);
	//
	// // Ejm F1 #= 0.
	// // Se adiciona las restricciones que tiene el modelo de
	// // variabilidad inicial
	//
	// verificationExpression = VerifierUtilExpression
	// .verifyFalseOptionalExpression(identifier);
	// // Se adiciona la restricción de verificacion al conjunto de
	// // restricciones
	// // que representa el modelo de variabilidad
	// ConfigurationOptions options = new ConfigurationOptions();
	// options.addAdditionalExpression(verificationExpression);
	//
	// // Se evalua si el modelo de restricciones es satisfacible
	// configurationResult = solver.getConfiguration(model,
	// new Configuration(), options);
	//
	// // Se evalua si este nuevo modelo es satisfacible
	// if (configurationResult == null) {
	// // Se crea un nuevo defecto
	// FalseOptionalElement falseOptionalElement = new FalseOptionalElement(
	// identifier, verificationExpression);
	// return falseOptionalElement;
	// } else {
	// // No defect was found, values domains are updated
	// updateEvaluatedDomainsMap(configurationResult);
	// return null;
	// }
	// } else {
	// throw new FunctionalException(
	// "Model is void. Unable to verify false optional elements");
	// }
	//
	// } else {
	// // No defect was found
	// return null;
	// }
	// }

	@Override
	public Defect isFalseOptionalElement(Identifier identifier)
			throws FunctionalException {

		return isFalseOptionalElement(identifier,
				new ConfigurationOptionsDTO(), new SolverSolution());
	}

	@Override
	public Defect isFalseOptionalElement(Identifier identifier,
			ConfigurationOptionsDTO options, SolverSolution configuration)
			throws FunctionalException {

		boolean createDefect = Boolean.FALSE;

		if (configuration == null) {
			configuration = new SolverSolution();
		}
		if (options == null) {
			options = new ConfigurationOptionsDTO();
		}

		// Se verifica si ya existe en el mapa el valor de cero para ese
		// identificador
		if (!existValue(identifier, 0)) {

			SolverSolution copy = new SolverSolution();
			TreeMap<String, Number> configurationValues = new TreeMap<String, Number>();
			configurationValues.putAll(configuration.getSolverSolution());
			copy.setSolverSolution(configurationValues);
			copy.ban(identifier.getId());
			SolverSolution configurationResult = solver.getConfiguration(model,
					copy, options);
			solverTime += solver.getLastExecutionTime();

			if (configurationResult != null) {
				// Los valores identificados se actualizan en el mapa para
				// evitar luego consultas innecesarias
				updateEvaluatedDomainsMap(configurationResult);
			} else {
				createDefect = Boolean.TRUE;
			}
		}
		if (createDefect) {
			IntBooleanExpression verificationExpression = null;
			// Ejm F1 #= 0.
			// Se adiciona las restricciones que tiene el modelo de
			// variabilidad inicial
			verificationExpression = VerifierUtilExpression
					.verifyFalseOptionalExpression(identifier);
			// Se crea un nuevo defecto
			FalseOptionalElement falseOptionalElement = new FalseOptionalElement(
					identifier, verificationExpression);
			return falseOptionalElement;
		} else {
			return null;
		}

	}

	@Override
	public Defect isRedundant(IntBooleanExpression expressionToVerify)
			throws FunctionalException {

		if (expressionToVerify == null) {
			throw new TechnicalException(
					"The expression to verify redundancies is mandatory");
		}

		if (!model.contains(expressionToVerify)) {
			throw new TechnicalException(
					"HlclProgram does not contain the expression to verify:"
							+ expressionToVerify
							+ "redundancy verification is not possible");
		}

		HlclProgram modelWithoutRedundancy = new HlclProgram();
		boolean isSatisfiable = Boolean.FALSE;

		// Se evalua si el modelo de restricciones guardado en la
		// ruta temporal es resoluble
		isSatisfiable = solver.isSatisfiable(model);

		if (isSatisfiable) {

			// 2. Se verifica si el modelo sin la restriccion redundante es
			// resoluble
			for (IntBooleanExpression expression : model) {
				if (!expression.equals(expressionToVerify)) {
					modelWithoutRedundancy.add(expression);
				}

			}

			// Se evalua si el modelo de restricciones sin la redundancia es
			// resoluble
			isSatisfiable = solver.isSatisfiable(modelWithoutRedundancy);

			if (!isSatisfiable) {
				// La relación no es redundante, pq se requiere para que el
				// modelo funcione
				return null;
			} else {
				// Si el nuevo modelo es resoluble entonces se adicionan las
				// instrucciones que corresponen a la negación de la restricción
				// que se cree redundante

				HlclFactory f = new HlclFactory();
				BooleanNegation negation;
				Identifier reification;
				IntBooleanExpression reifedExpression;
				List<IntBooleanExpression> negationList = new ArrayList<IntBooleanExpression>();
				// Se reifica la expresión a negar en una variable, luego se
				// aplica la negación sobre esa variable que es ( 1- Variable)
				// #> 0
				reification = f.newIdentifier("ReifRedundancy");
				reifedExpression = f.doubleImplies(reification,
						expressionToVerify);
				negationList.add(reifedExpression);
				negation = (BooleanNegation) f.not(reification);
				negationList.add(negation);

				ConfigurationOptionsDTO options = new ConfigurationOptionsDTO();
				options.addAllAdditionalExpression(negationList);

				isSatisfiable = solver.isSatisfiable(modelWithoutRedundancy,
						new SolverSolution(), options);

				if (!isSatisfiable) {
					// La restricción si es redundante pq el modelo se volvió
					// irresoluble
					Defect redundancy = new Redundancy(negationList,
							expressionToVerify);
					return redundancy;

				}
			}
		} else {

			throw new FunctionalException(
					"Model is void. Unable to verify redundant constraints");

		}
		return null;
	}

	@Override
	public List<Defect> getDeadElements(Set<Identifier> elementsToVerify)
			throws FunctionalException, InterruptedException {
		return getDeadElements(elementsToVerify, null, null);
	}

	@Override
	public List<Defect> getDeadElements(Set<Identifier> elementsToVerify,
			ConfigurationOptionsDTO options, SolverSolution configuration)
			throws FunctionalException, InterruptedException {
		long initTotal = System.currentTimeMillis();
		ProgressMonitor progressMonitor = null;
		if (parentComponent != null) {
			progressMonitor = new ProgressMonitor(parentComponent,
					progressDisplay + " (Dead Elements)", "", 0,
					elementsToVerify.size());
			progressMonitor.setMillisToDecideToPopup(5);
			progressMonitor.setMillisToPopup(5);
			progressMonitor.setProgress(0);
		}
		int i = 0;
		List<Defect> deadElementsList = new ArrayList<Defect>();

		for (Identifier identifier : elementsToVerify) {
			if (progressMonitor != null && progressMonitor.isCanceled())
				throw (new InterruptedException());
			DeadElement deadElement = (DeadElement) isDeadElement(identifier,
					options, configuration);
			if (deadElement != null) {
				deadElementsList.add(deadElement);
			}
			i++;
			if (progressMonitor != null) {
				progressMonitor.setProgress(i);
				String message = String.format("Completed %d%%.\n" + "("
						+ elementsToVerify.size() + ")", i * 100
						/ elementsToVerify.size());
				progressMonitor.setNote(message);
			}
		}
		totalTime += System.currentTimeMillis() - initTotal;
		return deadElementsList;
	}

	@Override
	public List<Defect> getFalseOptionalElements(
			Set<Identifier> elementsToVerify) throws FunctionalException,
			InterruptedException {

		return getFalseOptionalElements(elementsToVerify, null, null);
	}

	@Override
	public List<Defect> getFalseOptionalElements(
			Set<Identifier> elementsToVerify, ConfigurationOptionsDTO options,
			SolverSolution configuration) throws FunctionalException,
			InterruptedException {
		long initTotal = System.currentTimeMillis();
		List<Defect> falseOptionalList = new ArrayList<Defect>();
		ProgressMonitor progressMonitor = null;
		if (parentComponent != null) {
			progressMonitor = new ProgressMonitor(parentComponent,
					progressDisplay + " (Full mandatory)", "", 0,
					elementsToVerify.size());
			progressMonitor.setMillisToDecideToPopup(5);
			progressMonitor.setMillisToPopup(5);
			progressMonitor.setProgress(0);
		}
		int i = 0;
		for (Identifier identifier : elementsToVerify) {
			if (progressMonitor != null && progressMonitor.isCanceled())
				throw (new InterruptedException());
			FalseOptionalElement falseOptionalElement = (FalseOptionalElement) isFalseOptionalElement(
					identifier, options, configuration);
			if (falseOptionalElement != null) {
				falseOptionalList.add(falseOptionalElement);
			}
			i++;
			if (progressMonitor != null) {
				progressMonitor.setProgress(i);
				String message = String.format("Completed %d%%.\n" + "("
						+ elementsToVerify.size() + ")", i * 100
						/ elementsToVerify.size());
				progressMonitor.setNote(message);
			}
		}
		totalTime += System.currentTimeMillis() - initTotal;
		return falseOptionalList;
	}

	@Override
	public long getSolverTime() {
		return solverTime;
	}

	@Override
	public long getTotalTime() {
		return totalTime;
	}

	@Override
	public List<Defect> getRedundancies(
			List<IntBooleanExpression> constraitsToVerify)
			throws FunctionalException {
		List<Defect> redundanciesList = new ArrayList<Defect>();
		Iterator<IntBooleanExpression> it = constraitsToVerify.iterator();
		while (it.hasNext()) {
			IntBooleanExpression expressionToVerify = it.next();
			Redundancy redudancy = (Redundancy) isRedundant(expressionToVerify);
			if (redudancy != null) {
				redundanciesList.add(redudancy);
			}

		}

		return redundanciesList;

	}

	@Override
	public List<Defect> getAllNonAttainableDomains(
			Set<Identifier> elementsToVerify) throws FunctionalException {
		List<Defect> nonAttainableDomainsList = new ArrayList<Defect>();

		for (Identifier identifier : elementsToVerify) {
			List<Defect> nonAttainableDomains = getNonAttainableDomains(identifier);
			if (nonAttainableDomains != null && !nonAttainableDomains.isEmpty()) {
				nonAttainableDomainsList.addAll(nonAttainableDomains);
			}
		}
		return nonAttainableDomainsList;

	}

	@Override
	public VerificationResultDTO getDefects(Set<Identifier> optionalElements,
			Set<Identifier> deadElementsToVerify,
			List<IntBooleanExpression> constraintsToVerifyRedundancies)
			throws InterruptedException {

		VerificationResultDTO verificationResult = new VerificationResultDTO();
		List<Defect> defectsList = new ArrayList<Defect>();
		if (model == null) {
			throw new TechnicalException(
					"HLCL program is required to verify defects");
		}
		VoidModel isVoid = (VoidModel) isVoid();
		if (isVoid.isVoidModel()) {
			defectsList.add(isVoid);
			verificationResult.setVoidModel(isVoid);
		} else {
			try {
				FalseProductLine falseProductLine = (FalseProductLine) isFalsePL();
				if (falseProductLine.isFalsePL()) {
					verificationResult
							.setFalseProductLineModel(falseProductLine);
				}
				if (!deadElementsToVerify.isEmpty()) {
					List<Defect> deadList = getDeadElements(deadElementsToVerify);
					if (deadList.isEmpty()) {
						verificationResult.setDeadFeaturesList(deadList);
					}

				}
				if (!optionalElements.isEmpty()) {
					List<Defect> falseOptionalList = getFalseOptionalElements(optionalElements);
					if (falseOptionalList.isEmpty()) {
						verificationResult
								.setFalseOptionalFeaturesList(falseOptionalList);
					}
				}
				List<Defect> redundanciesList = getRedundancies(constraintsToVerifyRedundancies);
				if (redundanciesList.isEmpty()) {
					verificationResult.setRedundanciesList(redundanciesList);
				}
			} catch (FunctionalException e) {
				// This is exception is handled because we analyze defects only
				// when the model is not void
			}
		}
		return verificationResult;

	}

	public HlclProgram getModel() {
		return model;
	}

	public void setProgram(HlclProgram model) {
		// Si se cambia el program se debe borrar el mapa que tiene cache de los
		// valores datos por el solver a los identificadores
		verifiedValuesMap.clear();
		identifiersList = HlclUtil.getUsedIdentifiers(model);
		this.model = model;
	}
}
