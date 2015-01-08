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
	private Map<Identifier, Set<Integer>> attainableDomainsByVariabilityElementMap;
	private SolverOperationsUtil solver;

	public DefectsVerifier(SolverEditorType solverEditorType) {
		attainableDomainsByVariabilityElementMap = new HashMap<Identifier, Set<Integer>>();
		solver = new SolverOperationsUtil(solverEditorType);

	}

	@Override
	public Defect isVoid(HlclProgram model) {

		boolean isVoid = !solver.isSatisfiable(model);
		if (isVoid) {
			return new VoidModel();
		} else {
			return null;
		}

	}

	@Override
	public Defect isFalsePL(HlclProgram model) {
		boolean isFPL = solver.isFalseProductLine(model);
		if (isFPL) {
			return new FalseProductLine();
		} else {
			return null;
		}
	}

	public List<Defect> getNonAttainableDomains(HlclProgram model,
			Identifier identifier) throws FunctionalException {

		List<Defect> notAttainableDomains = new ArrayList<Defect>();
		List<Integer> definedDomainValues = null;
		Set<Integer> attainableDomains = null;
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

				attainableDomains = attainableDomainsByVariabilityElementMap
						.get(identifier);
				variabilityModelConstraintRepresentation.clear();
				if (attainableDomains == null
						|| (attainableDomains != null && !attainableDomains
								.contains(valueToTest))) {
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
						Set<Identifier> identifiersList = HlclUtil
								.getUsedIdentifiers(model);
						// Los valores identificados no son non attainable
						// domains, se actualizan los valores en el mapa
						updateEvaluatedDomainsMap(configurationResult,
								identifiersList);
					} else {
						// Se crea un nuevo defecto
						NonAttainableDomain defect = new NonAttainableDomain(
								identifier, valueToTest, verificationExpression);
						notAttainableDomains.add(defect);
					}
				}

			}
		}else{
			throw new FunctionalException(
					"Model is void. Unable to verify not attainable dommains");
		}
		return notAttainableDomains;

	}

	private void updateEvaluatedDomainsMap(Configuration configuration,
			Set<Identifier> identifiersList) {

		TreeMap<String, Integer> configurationValues = configuration
				.getConfiguration();

		Integer number = null;

		for (Identifier identifier : identifiersList) {

			// Se busca en la configuración el id del identificador
			if (configurationValues.containsKey(identifier.getId())) {

				number = configurationValues.get(identifier.getId());
				// Se verifica si el mapa de dominios tiene el identifier
				if (attainableDomainsByVariabilityElementMap
						.containsKey(identifier)) {
					// Si existe se adiciona el valor de la variable al set de
					// dominios permitidos
					attainableDomainsByVariabilityElementMap.get(identifier)
							.add(number);
				} else {
					// Si el mapa no contiene el identifier se adiciona
					// al mapa de dominios
					Set<Integer> attainableDomainValuesSet = new HashSet<Integer>();
					attainableDomainValuesSet.add(number);
					attainableDomainsByVariabilityElementMap.put(identifier,
							attainableDomainValuesSet);

				}

			} else {
				// Quiere decir que no se encuentran dentro de los elementos
				// para los que se debe verificar el valor
			}

		}

	}

	public static void printFoundDefects(VerificationResult outDTO) {
		// 3. PRINT RESULTS
		System.out.println("VOID MODEL: " + outDTO.isVoidModel());
		System.out.println("FALSE PRODUCT LINE: "
				+ outDTO.isFalseProductLineModel());

		if (outDTO.getDeadFeaturesList() != null) {
			for (Defect deadElement : outDTO.getDeadFeaturesList()) {
				System.out.println("DEAD FEATURE " + deadElement.getId());
			}
		}

		if (outDTO.getFalseOptionalFeaturesList() != null) {
			for (Defect falseOptionalFeature : outDTO
					.getFalseOptionalFeaturesList()) {
				System.out.println("FALSE OPTIONAL FEATURE "
						+ falseOptionalFeature.getId());
			}
		}
		/*
		 * if (outDTO.getDomainNotAttainableList() != null) { for (Defect
		 * nonAttainableDomain : outDTO .getDomainNotAttainableList()) {
		 * 
		 * Integer nonAttainableValue = ((NonAttainableDomain)
		 * nonAttainableDomain) .getNotAttainableDomain();
		 * System.out.println("NON ATTAINABLE DOMAIN " +
		 * nonAttainableDomain.getId() + " VALUE : " + nonAttainableValue); } }
		 */
	}

	@Override
	public Defect isDeadElement(HlclProgram model, Identifier identifier)
			throws FunctionalException {

		VoidModel isVoid = (VoidModel) isVoid(model);

		if (isVoid == null) {

			List<Integer> definedDomainValues = null;
			List<BooleanExpression> variabilityModelConstraintRepresentation = new ArrayList<BooleanExpression>();
			BooleanExpression constraintToAdd = null;
			BooleanExpression verificationExpression = null;
			boolean isDead = Boolean.TRUE;
			Domain domain = identifier.getDomain();
			// Se obtienen los valores parametrizados para esta variable
			definedDomainValues = domain.getPossibleValues();
			boolean isSatisfiable = Boolean.FALSE;
			// Se busca para cada variable los valores de dominio y se analiza
			for (Integer definedDomainValue : definedDomainValues) {
				// Ejm F1 #= 1. Se excluye el 0
				isSatisfiable = Boolean.FALSE;

				if (definedDomainValue > TransformerConstants.NON_SELECTED_VALUE) {
					variabilityModelConstraintRepresentation.clear();

					constraintToAdd = VerifierUtilExpression
							.verifyAssignValueToVariabilityElementExpression(
									identifier, definedDomainValue);
					// Se adiciona la restricción al conjunto de restricciones
					// que representa el modelo de variabilidad
					ConfigurationOptions options = new ConfigurationOptions();
					options.addAdditionalExpression(constraintToAdd);

					// Se evalua si el modelo de restricciones es satisfacible
					isSatisfiable = solver.isSatisfiable(model,
							new Configuration(), options);

					if (isSatisfiable) {
						// Si es satisfacible la feature no es dead y se pasa a
						// la siguiente feature
						isDead = Boolean.FALSE;

						// Se adiciona ese valor como valor permitido para no
						// verificarlo en los notAttainableDomains
						if (attainableDomainsByVariabilityElementMap
								.containsKey(identifier)) {
							// Se adiciona el valor a la lista de dominios
							// permitidos
							attainableDomainsByVariabilityElementMap.get(
									identifier).add(definedDomainValue);
						} else {
							// Se adiciona el variabilityElement al mapa y se
							// adicionan el valor de dominio. Se crea un nuevo
							// objeto para evitar problemas de referencia entre
							// objetos
							Set<Integer> attainableDomainValuesList = new HashSet<Integer>();
							attainableDomainValuesList.add(definedDomainValue);
							attainableDomainsByVariabilityElementMap.put(
									identifier, attainableDomainValuesList);
						}
						// Se termina el ciclo y no se analizan mas valores de
						// dominio
						break;
					} else {
						verificationExpression = constraintToAdd;
					}
				}

			}

			if (isDead) {
				// Se crea un nuevo defecto
				DeadElement deadElement = new DeadElement(identifier,
						verificationExpression);
				System.out.println("dead feature" + identifier.getId());
				return deadElement;
			}
			return null;
		} else {
			throw new FunctionalException(
					"Model is void. Unable to verify dead elements elements");
		}
	}

	@Override
	public Defect isFalseOptionalElement(HlclProgram model,
			Identifier identifier) throws FunctionalException {
		BooleanExpression verificationExpression = null;
		boolean isSatisfiable = Boolean.TRUE;
		VoidModel isVoid = (VoidModel) isVoid(model);

		if (isVoid == null) {

			// Ejm F1 #= 0.
			// Se adiciona las restricciones que tiene el modelo de
			// variabilidad inicial

			verificationExpression = VerifierUtilExpression
					.verifyFalseOptionalExpression(identifier);
			// Se adiciona la restricción de verificacion al conjunto de
			// restricciones
			// que representa el modelo de variabilidad
			ConfigurationOptions options = new ConfigurationOptions();
			options.addAdditionalExpression(verificationExpression);

			// Se evalua si el modelo de restricciones es satisfacible
			isSatisfiable = solver.isSatisfiable(model, new Configuration(),
					options);

			// Se evalua si este nuevo modelo es satisfacible
			if (!isSatisfiable) {
				// Se crea un nuevo defecto
				FalseOptionalElement falseOptionalElement = new FalseOptionalElement(
						identifier, verificationExpression);
				return falseOptionalElement;
			} else {
				// No defect was found
				return null;
			}
		} else {
			throw new FunctionalException(
					"Model is void. Unable to verify false optional elements");
		}

	}

	@Override
	public Defect isRedundant(HlclProgram model,
			BooleanExpression expressionToVerify) throws FunctionalException {

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
				} else {
					System.out.println("Encontre la expresion");
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
					Defect redundancy = new Redundancy(expressionToVerify,
							negationList);
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
	public List<Defect> getDeadElements(HlclProgram model,
			Set<Identifier> elementsToVerify) throws FunctionalException {
		List<Defect> deadElementsList = new ArrayList<Defect>();

		for (Identifier identifier : elementsToVerify) {
			DeadElement deadElement = (DeadElement) isDeadElement(model,
					identifier);
			if (deadElement != null) {
				deadElementsList.add(deadElement);
			}
		}
		return deadElementsList;
	}

	@Override
	public List<Defect> getFalseOptionalElements(HlclProgram model,
			Set<Identifier> elementsToVerify) throws FunctionalException {
		List<Defect> falseOptionalList = new ArrayList<Defect>();

		for (Identifier identifier : elementsToVerify) {
			FalseOptionalElement falseOptionalElement = (FalseOptionalElement) isFalseOptionalElement(
					model, identifier);
			if (falseOptionalElement != null) {
				falseOptionalList.add(falseOptionalElement);
			}
		}
		return falseOptionalList;
	}

	@Override
	public List<Defect> getRedundancies(HlclProgram model,
			List<BooleanExpression> constraitsToVerify)
			throws FunctionalException {
		List<Defect> redundanciesList = new ArrayList<Defect>();
		Iterator<BooleanExpression> it = constraitsToVerify.iterator();
		while (it.hasNext()) {
			BooleanExpression expressionToVerify = it.next();
			Redundancy redudancy = (Redundancy) isRedundant(model,
					expressionToVerify);
			if (redudancy != null) {
				redundanciesList.add(redudancy);
			}

		}

		return redundanciesList;

	}

	public List<Defect> getAllNonAttainableDomains(HlclProgram model,
			Set<Identifier> elementsToVerify) throws FunctionalException {
		List<Defect> nonAttainableDomainsList = new ArrayList<Defect>();

		for (Identifier identifier : elementsToVerify) {
			List<Defect> nonAttainableDomains = getNonAttainableDomains(model,
					identifier);
			if (nonAttainableDomains != null && !nonAttainableDomains.isEmpty()) {
				nonAttainableDomainsList.addAll(nonAttainableDomains);
			}
		}
		return nonAttainableDomainsList;

	}

	@Override
	public VerificationResult getDefects(HlclProgram model,
			Set<Identifier> optionalElements,
			Set<Identifier> deadElementsToVerify,
			List<BooleanExpression> constraintsToVerifyRedundancies) {

		VerificationResult verificationResult = new VerificationResult();
		List<Defect> defectsList = new ArrayList<Defect>();
		if (model == null) {
			throw new TechnicalException(
					"HLCL program is required to verify defects");
		}
		VoidModel isVoid = (VoidModel) isVoid(model);
		if (isVoid.isVoidModel()) {
			defectsList.add(isVoid);
			verificationResult.setVoidModel(isVoid);
		} else {
			try {
				FalseProductLine falseProductLine = (FalseProductLine) isFalsePL(model);
				if (falseProductLine.isFalsePL()) {
					verificationResult
							.setFalseProductLineModel(falseProductLine);
				}
				if (!deadElementsToVerify.isEmpty()) {
					List<Defect> deadList = getDeadElements(model,
							deadElementsToVerify);
					if (deadList.isEmpty()) {
						verificationResult.setDeadFeaturesList(deadList);
					}

				}
				if (!optionalElements.isEmpty()) {
					List<Defect> falseOptionalList = getFalseOptionalElements(
							model, optionalElements);
					if (falseOptionalList.isEmpty()) {
						verificationResult
								.setFalseOptionalFeaturesList(falseOptionalList);
					}
				}
				List<Defect> redundanciesList = getRedundancies(model,
						constraintsToVerifyRedundancies);
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
}