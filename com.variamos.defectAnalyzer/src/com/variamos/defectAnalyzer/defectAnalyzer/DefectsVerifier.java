package com.variamos.defectAnalyzer.defectAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.BooleanNegation;
import com.cfm.hlcl.Domain;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.cfm.productline.solver.Configuration;
import com.cfm.productline.solver.ConfigurationOptions;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.core.exceptions.TechnicalException;
import com.variamos.defectAnalyzer.constants.TransformerConstants;
import com.variamos.defectAnalyzer.dto.VerificationResult;
import com.variamos.defectAnalyzer.model.defects.DeadElement;
import com.variamos.defectAnalyzer.model.defects.Defect;
import com.variamos.defectAnalyzer.model.defects.FalseOptionalElement;
import com.variamos.defectAnalyzer.model.defects.FalseProductLine;
import com.variamos.defectAnalyzer.model.defects.NonAttainableDomain;
import com.variamos.defectAnalyzer.model.defects.Redundancy;
import com.variamos.defectAnalyzer.model.defects.VoidModel;
import com.variamos.defectAnalyzer.util.SolverOperationsUtil;
import com.variamos.defectAnalyzer.util.VerifierUtilExpression;

public class DefectsVerifier implements IntDefectsVerifier {

	// Variables usadas para almacenar información que es útil cuando se hacen
	// otras operaciones de verificación
	private Map<Identifier, Set<Integer>> verifiedValuesMap;
	private SolverOperationsUtil solver;
	private HlclProgram model;
	// Hlcl program identifiers
	private Set<Identifier> identifiersList;

	public DefectsVerifier(HlclProgram model, SolverEditorType solverEditorType) {
		verifiedValuesMap = new HashMap<Identifier, Set<Integer>>();
		solver = new SolverOperationsUtil(solverEditorType);
		this.model = model;
		identifiersList = HlclUtil.getUsedIdentifiers(model);
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

	private boolean existValue(Identifier identifier, int valueToTest) {
		// Cada vez que se hace una configuración se bloquean valores no tener
		// que verificar luego estos valores
		Set<Integer> attainableDomains = verifiedValuesMap.get(identifier);
		if (attainableDomains == null
				|| (attainableDomains != null && !attainableDomains
						.contains(valueToTest))) {
			return false;
		} else {
			return true;
		}

	}

	public List<Defect> getNonAttainableDomains(Identifier identifier)
			throws FunctionalException {

		List<Defect> notAttainableDomains = new ArrayList<Defect>();
		List<Integer> definedDomainValues = null;
		List<BooleanExpression> variabilityModelConstraintRepresentation = new ArrayList<BooleanExpression>();

		BooleanExpression verificationExpression = null;

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
					ConfigurationOptions options = new ConfigurationOptions();
					options.addAdditionalExpression(verificationExpression);

					// Se obtiene una configuración del solver
					Configuration configurationResult = solver
							.getConfiguration(model, new Configuration(),
									options);

					// Si se obtienen valores esto quiere decir q es
					// satisfacible
					if (configurationResult != null
							&& !configurationResult.getConfiguration()
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

	private void updateEvaluatedDomainsMap(Configuration configuration) {

		TreeMap<String, Integer> configurationValues = configuration
				.getConfiguration();

		Integer number = null;

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
					Set<Integer> attainableDomainValuesSet = new HashSet<Integer>();
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

		return isDeadElement(identifier, new ConfigurationOptions(),
				new Configuration());
	}

	@Override
	public Defect isDeadElement(Identifier identifier,
			ConfigurationOptions options, Configuration configuration)
			throws FunctionalException {

		List<Integer> definedDomainValues = null;
		boolean createDefect = Boolean.TRUE;
		Domain domain = identifier.getDomain();
		// Se obtienen los valores parametrizados para esta variable
		definedDomainValues = domain.getPossibleValues();
		Configuration configurationResult = null;
		int nonAttainableValue = 0;

		if (configuration == null) {
			configuration = new Configuration();
		}
		if (options == null) {
			options = new ConfigurationOptions();
		}

		for (Integer definedDomainValue : definedDomainValues) {

			if (definedDomainValue > TransformerConstants.NON_SELECTED_VALUE) {
				// Se verifica si ya existe en el mapa el identificador con
				// el valor
				if (!existValue(identifier, definedDomainValue)) {
							
					Configuration copy= new Configuration();
					TreeMap<String, Integer> configurationValues= new TreeMap<String, Integer>();
					configurationValues.putAll(configuration.getConfiguration());
					copy.setConfiguration(configurationValues);	
					copy.set(identifier.getId(), definedDomainValue);
					configurationResult = solver.getConfiguration(model,
							copy, new ConfigurationOptions());

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
			BooleanExpression verificationExpression = null;
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

		return isFalseOptionalElement(identifier, new ConfigurationOptions(),
				new Configuration());
	}

	@Override
	public Defect isFalseOptionalElement(Identifier identifier,
			ConfigurationOptions options, Configuration configuration)
			throws FunctionalException {

		boolean createDefect = Boolean.FALSE;

		if (configuration == null) {
			configuration = new Configuration();
		}
		if (options == null) {
			options = new ConfigurationOptions();
		}

		// Se verifica si ya existe en el mapa el valor de cero para ese
		// identificador
		if (!existValue(identifier, 0)) {

			
			
			Configuration copy= new Configuration();
			TreeMap<String, Integer> configurationValues= new TreeMap<String, Integer>();
			configurationValues.putAll(configuration.getConfiguration());
			copy.setConfiguration(configurationValues);	
			copy.ban(identifier.getId());	
			Configuration configurationResult = solver.getConfiguration(model,
					copy, options);

			if (configurationResult != null) {
				// Los valores identificados se actualizan en el mapa para
				// evitar luego consultas innecesarias
				updateEvaluatedDomainsMap(configurationResult);
			} else {
				createDefect = Boolean.TRUE;
			}
		}
		if (createDefect) {
			BooleanExpression verificationExpression = null;
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
	public Defect isRedundant(BooleanExpression expressionToVerify)
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
			for (BooleanExpression expression : model) {
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
				BooleanExpression reifedExpression;
				List<BooleanExpression> negationList = new ArrayList<BooleanExpression>();
				// Se reifica la expresión a negar en una variable, luego se
				// aplica la negación sobre esa variable que es ( 1- Variable)
				// #> 0
				reification = f.newIdentifier("ReifRedundancy");
				reifedExpression = f.doubleImplies(reification,
						expressionToVerify);
				negationList.add(reifedExpression);
				negation = (BooleanNegation) f.not(reification);
				negationList.add(negation);

				ConfigurationOptions options = new ConfigurationOptions();
				options.addAllAdditionalExpression(negationList);

				isSatisfiable = solver.isSatisfiable(modelWithoutRedundancy,
						new Configuration(), options);

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
			throws FunctionalException {
		return getDeadElements(elementsToVerify, null, null);
	}

	@Override
	public List<Defect> getDeadElements(Set<Identifier> elementsToVerify,
			ConfigurationOptions options, Configuration configuration)
			throws FunctionalException {
		List<Defect> deadElementsList = new ArrayList<Defect>();

		for (Identifier identifier : elementsToVerify) {
			DeadElement deadElement = (DeadElement) isDeadElement(identifier,
					options, configuration);
			if (deadElement != null) {
				deadElementsList.add(deadElement);
			}
		}
		return deadElementsList;
	}

	@Override
	public List<Defect> getFalseOptionalElements(
			Set<Identifier> elementsToVerify) throws FunctionalException {

		return getFalseOptionalElements(elementsToVerify, null, null);
	}

	@Override
	public List<Defect> getFalseOptionalElements(
			Set<Identifier> elementsToVerify, ConfigurationOptions options,
			Configuration configuration) throws FunctionalException {
		List<Defect> falseOptionalList = new ArrayList<Defect>();

		for (Identifier identifier : elementsToVerify) {
			FalseOptionalElement falseOptionalElement = (FalseOptionalElement) isFalseOptionalElement(
					identifier, options, configuration);
			if (falseOptionalElement != null) {
				falseOptionalList.add(falseOptionalElement);
			}
		}
		return falseOptionalList;
	}

	@Override
	public List<Defect> getRedundancies(
			List<BooleanExpression> constraitsToVerify)
			throws FunctionalException {
		List<Defect> redundanciesList = new ArrayList<Defect>();
		Iterator<BooleanExpression> it = constraitsToVerify.iterator();
		while (it.hasNext()) {
			BooleanExpression expressionToVerify = it.next();
			Redundancy redudancy = (Redundancy) isRedundant(expressionToVerify);
			if (redudancy != null) {
				redundanciesList.add(redudancy);
			}

		}

		return redundanciesList;

	}

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
	public VerificationResult getDefects(Set<Identifier> optionalElements,
			Set<Identifier> deadElementsToVerify,
			List<BooleanExpression> constraintsToVerifyRedundancies) {

		VerificationResult verificationResult = new VerificationResult();
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
