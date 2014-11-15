package com.variamos.defectAnalyzer.defectAnalyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.LiteralBooleanExpression;
import com.cfm.jgprolog.core.PrologException;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.defectAnalyzer.constants.TransformerConstants;
import com.variamos.defectAnalyzer.dto.VMAnalyzerInDTO;
import com.variamos.defectAnalyzer.dto.VMVerifierOutDTO;
import com.variamos.defectAnalyzer.model.DefectAnalyzerDomain;
import com.variamos.defectAnalyzer.model.Dependency;
import com.variamos.defectAnalyzer.model.VariabilityElement;
import com.variamos.defectAnalyzer.model.defects.DeadElement;
import com.variamos.defectAnalyzer.model.defects.Defect;
import com.variamos.defectAnalyzer.model.defects.FalseOptionalElement;
import com.variamos.defectAnalyzer.model.defects.FalseProductLine;
import com.variamos.defectAnalyzer.model.defects.NonAttainableDomain;
import com.variamos.defectAnalyzer.model.defects.Redundancy;
import com.variamos.defectAnalyzer.model.defects.VoidModel;
import com.variamos.defectAnalyzer.util.ConstraintRepresentationUtil;
import com.variamos.defectAnalyzer.util.SolverOperationsUtil;
import com.variamos.defectAnalyzer.util.VerifierUtilExpression;

public class VariabilityModelVerifier extends VariabilityModelAnalyzer {

	private VMAnalyzerInDTO analyzerInDTO;

	// Variables usadas para almacenar información que es útil cuando se hacen
	// otras operaciones de verificación
	private Map<VariabilityElement, Set<Integer>> attainableDomainsByVariabilityElementMap;
	private SolverOperationsUtil solver;

	public VariabilityModelVerifier(VMAnalyzerInDTO analyzerInDTO) {
		super(analyzerInDTO);
		this.analyzerInDTO = analyzerInDTO;
		attainableDomainsByVariabilityElementMap = new HashMap<VariabilityElement, Set<Integer>>();
		solver= new SolverOperationsUtil(analyzerInDTO.getPrologEditorType());
		
	}

	/**
	 * 
	 * @return This operation takes a PLM as input and returns TRUE if the PLM
	 *         does not define any products.
	 * @throws FunctionalException
	 */
	public Defect isVoid() throws FunctionalException {

		Collection<Expression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(analyzerInDTO.getVariabilityModel()
						.getDependencies(), analyzerInDTO.getVariabilityModel()
						.getFixedDependencies());
		if (analyzerInDTO.getVariabilityModel().getDomainStringList() != null
				&& !analyzerInDTO.getVariabilityModel().getDomainStringList()
						.isEmpty()) {
			// Saves the variability model in a prolog program
			ConstraintRepresentationUtil.savePrologRepresentationProgram(
					prologTempPath, variabilityModelConstraintRepresentation,
					analyzerInDTO.getVariabilityModel().getDomainStringList(),
					prologEditorType);

		} else {

			// Saves the variability model in a prolog program
			ConstraintRepresentationUtil.savePrologRepresentationProgram(
					prologTempPath, variabilityModelConstraintRepresentation,
					prologEditorType);
		}

		boolean isVoid = !solver.isSatisfiable(prologTempPath);

		if (isVoid) {
			return new VoidModel(analyzerInDTO.getVariabilityModel()
					.getModelName());
		}else{
			return null;
		}
	
	}

	/**
	 * This operation takes a PLM as input and returns true if at most one valid
	 * product can be configured with it
	 * 
	 * @param path
	 * @param prologEditorType
	 * @return
	 * @throws FunctionalException
	 */
	public Defect isFalsePLM() throws FunctionalException {
		Collection<Expression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(analyzerInDTO.getVariabilityModel()
						.getDependencies(), analyzerInDTO.getVariabilityModel()
						.getFixedDependencies());
		// Saves the variability model in a prolog program
		ConstraintRepresentationUtil.savePrologRepresentationProgram(
				prologTempPath, variabilityModelConstraintRepresentation,
				prologEditorType);
		boolean isFPL= SolverOperationsUtil.isFalseProductLine(prologTempPath,
				prologEditorType);
		
		if(isFPL){
			return new FalseProductLine(analyzerInDTO.getVariabilityModel());
		}else{
			return null;
		}
	}

	/**
	 * Identifica dead features, intentando para cada varible asignar algún
	 * valor posible para su dominio. Cuando esto es posible se sabe entonces
	 * que la variable no es dead y se continúa con la siguiente variable
	 * 
	 * @return
	 * @throws FunctionalException
	 * 
	 */
	public List<Defect> identifyDeadFeatures(
			Map<String, VariabilityElement> elementsListToVerify)
			throws FunctionalException {

		List<Integer> definedDomainValues = null;
		List<Defect> deadElementsList = new ArrayList<Defect>();
		List<Expression> variabilityModelConstraintRepresentation = new ArrayList<Expression>();
		Expression constraintToAdd = null;
		Expression constraintToIdentifyDeadFeature = null;
		boolean isDead = Boolean.TRUE;
		for (VariabilityElement element : elementsListToVerify.values()) {

			DefectAnalyzerDomain domain = element.getDomain();
			// Se obtienen los valores parametrizados para esta variable
			definedDomainValues = domain.getDomainValues();
			isDead = Boolean.TRUE;
			boolean isSatisfiable = Boolean.FALSE;
			// Se busca para cada variable los valores de dominio y se analiza
			for (Integer definedDomainValue : definedDomainValues) {
				// Ejm F1 #= 1. Se excluye el 0
				isSatisfiable = Boolean.FALSE;

				if (definedDomainValue > TransformerConstants.NON_SELECTED_VALUE) {

					variabilityModelConstraintRepresentation.clear();
					// Se adiciona las restricciones que tiene el modelo de
					// variabilidad más las restricciones fijas
					variabilityModelConstraintRepresentation
							.addAll(ConstraintRepresentationUtil
									.dependencyToExpressionList(analyzerInDTO
											.getVariabilityModel()
											.getDependencies(), analyzerInDTO
											.getVariabilityModel()
											.getFixedDependencies()));

					constraintToAdd = VerifierUtilExpression
							.verifyAssignValueToVariabilityElementExpression(
									element, definedDomainValue);

					// Se adiciona la restricción al conjunto de restricciones
					// que representa el modelo de variabilidad
					variabilityModelConstraintRepresentation.add(0,
							constraintToAdd);

					// Se convierte el conjunto de restricciones a un archivo en
					// prolog que se guarda en la ruta temporal
					ConstraintRepresentationUtil
							.savePrologRepresentationProgram(prologTempPath,
									variabilityModelConstraintRepresentation,
									prologEditorType);

					// Se evalua si el modelo de restricciones guardado en la
					// ruta temporal es satisfacible
					isSatisfiable = solver.isSatisfiable(
							prologTempPath);

					if (isSatisfiable) {
						// Si es satisfacible la feature no es dead y se pasa a
						// la siguiente feature
						isDead = Boolean.FALSE;

						// Se adiciona ese valor como valor permitido para no
						// verificarlo en los notAttainableDomains
						if (attainableDomainsByVariabilityElementMap
								.containsKey(element)) {
							// Se adiciona el valor a la lista de dominios
							// permitidos
							attainableDomainsByVariabilityElementMap.get(
									element).add(definedDomainValue);
						} else {
							// Se adiciona el variabilityElement al mapa y se
							// adicionan el valor de dominio. Se crea un nuevo
							// objeto para evitar problemas de referencia entre
							// objetos
							Set<Integer> attainableDomainValuesList = new HashSet<Integer>();
							attainableDomainValuesList.add(definedDomainValue);
							attainableDomainsByVariabilityElementMap.put(
									element, attainableDomainValuesList);
						}
						break;
					} else {
						constraintToIdentifyDeadFeature = constraintToAdd;
					}
				}

			}

			if (isDead) {
				// Se crea un nuevo defecto
				DeadElement deadElement = new DeadElement(element,
						constraintToIdentifyDeadFeature);
				deadElementsList.add(deadElement);

				System.out.println("dead feature" + element.getName());
			}

		}
		return deadElementsList;
	}

	/**
	 * Una false optional es una feature que debe estar presente en todas las
	 * configuraciones posibles de la línea de productos aunque es declarada
	 * como opcional
	 * 
	 * @return
	 * @throws FunctionalException
	 * 
	 */
	public List<Defect> identifyFalseOptionalFeatures(
			Map<String, VariabilityElement> optionalElementsMap)
			throws FunctionalException {

		List<Defect> falseOptionalElementsList = new ArrayList<Defect>();
		List<Expression> variabilityModelConstraintRepresentation = new ArrayList<Expression>();

		Expression constraintToIdentifyFalseOptionalFeature = null;

		boolean isSatisfiable = Boolean.TRUE;
		if (optionalElementsMap != null) {
			for (VariabilityElement element : optionalElementsMap.values()) {

				variabilityModelConstraintRepresentation.clear();
				// Ejm F1 #= 0.
				// Se adiciona las restricciones que tiene el modelo de
				// variabilidad inicial
				variabilityModelConstraintRepresentation
						.addAll(ConstraintRepresentationUtil
								.dependencyToExpressionList(analyzerInDTO
										.getVariabilityModel()
										.getDependencies(), analyzerInDTO
										.getVariabilityModel()
										.getFixedDependencies()));

				constraintToIdentifyFalseOptionalFeature = VerifierUtilExpression
						.verifyFalseOptionalExpression(element);

				// Se adiciona la restricción al conjunto de restricciones
				// que representa el modelo de variabilidad
				variabilityModelConstraintRepresentation.add(0,
						constraintToIdentifyFalseOptionalFeature);

				// Se convierte el conjunto de restricciones a un archivo en
				// prolog que se guarda en la ruta temporal
				ConstraintRepresentationUtil.savePrologRepresentationProgram(
						prologTempPath,
						variabilityModelConstraintRepresentation,
						prologEditorType);

				// Se evalua si el modelo de restricciones guardado en la
				// ruta temporal es resoluble
				isSatisfiable = solver.isSatisfiable(
						prologTempPath);

				// Se evalua si este nuevo modelo es satisfacible
				if (!isSatisfiable) {
					// Se crea un nuevo defecto
					FalseOptionalElement falseOptionalElement = new FalseOptionalElement(
							element, constraintToIdentifyFalseOptionalFeature);
					falseOptionalElementsList.add(falseOptionalElement);
					System.out.println("false optional" + element.getName());
				}

			}
		} else {
			throw new FunctionalException("Optional elements were not received");
		}
		return falseOptionalElementsList;
	}

	/**
	 * Identifica todas las redudancias de un modelo de variabilidad expresado
	 * en SPLOT. Funciona para este modelo pues fue para ese transformador que
	 * se almacenaron las redundancias
	 * 
	 * @throws FunctionalException
	 */
	public List<Defect> identifyAllRedundanciesSPLOTModels(
			Map<Long, Dependency> dependenciesMap) throws FunctionalException {

		List<Defect> redundancies = new ArrayList<Defect>();

		for (Dependency dependency : dependenciesMap.values()) {
			// Se verifica cada dependencia para ver si es redundante, siempre y
			// cuando tenga la negación de la restricción.
			if (dependency.getNegationExpression() != null) {
				Defect defect = identifyRedundancy(null,
						dependency.getNegationExpression(), dependency);
				if (defect != null) {
					redundancies.add(defect);
				}
			} else {
				// No se puede evaluar la redundacias, el modelo debe ser
				// resoluble
				throw new FunctionalException(
						"No se puede verificar la redundancia, la dependencia no tiene la expresión de negación");
			}
		}
		return redundancies;
	}

	public Defect identifyRedundancy(String instructionToCheckRedundacy,
			Expression expressionInstructionToCheckRedundancy,
			Dependency redundantDependency) throws FunctionalException {

		Collection<Expression> completeVariabilityModelConstraintRepresentation = new HashSet<Expression>();
		Collection<Expression> variabilityModelConstraintRepresentationWithoutRedundacy = new HashSet<Expression>();
		Map<Long, Dependency> dependenciesModel = new HashMap<Long, Dependency>();
		// Copia del mapa con todas las dependencias
		dependenciesModel.putAll(analyzerInDTO.getVariabilityModel()
				.getDependencies());

		boolean isSatisfiable = Boolean.FALSE;

		// 1.Se verifica si el modelo con la redundancia es resoluble
		completeVariabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(analyzerInDTO.getVariabilityModel()
						.getDependencies(), analyzerInDTO.getVariabilityModel()
						.getFixedDependencies());

		// Se convierte el conjunto de restricciones a un archivo en
		// prolog que se guarda en la ruta temporal
		ConstraintRepresentationUtil.savePrologRepresentationProgram(
				prologTempPath,
				completeVariabilityModelConstraintRepresentation,
				prologEditorType);

		// Se evalua si el modelo de restricciones guardado en la
		// ruta temporal es resoluble
		isSatisfiable = solver.isSatisfiable(prologTempPath);

		if (isSatisfiable) {

			// 2. Se verifica si el modelo sin la característica redundante
			// ofrece es resoluble
			if (dependenciesModel.containsKey(redundantDependency
					.getRelationShipNumber())) {
				dependenciesModel.remove(redundantDependency
						.getRelationShipNumber());
			} else {
				throw new FunctionalException(
						"Variability model does not have the redundant dependency that you want to check");
			}
			variabilityModelConstraintRepresentationWithoutRedundacy = ConstraintRepresentationUtil
					.dependencyToExpressionList(dependenciesModel,
							analyzerInDTO.getVariabilityModel()
									.getFixedDependencies());
			// Se convierte el conjunto de restricciones a un archivo en
			// prolog que se guarda en la ruta temporal
			ConstraintRepresentationUtil.savePrologRepresentationProgram(
					prologTempPath,
					variabilityModelConstraintRepresentationWithoutRedundacy,
					prologEditorType);

			// Se evalua si el modelo de restricciones guardado en la
			// ruta temporal es resoluble
			isSatisfiable = solver.isSatisfiable(prologTempPath);

			// Se evalua si este nuevo modelo es satisfacible
			if (!isSatisfiable) {
				// La relación no es redundante, pq se requiere para que el
				// modelo funcione
				return null;
			} else {
				// Si el nuevo modelo es resoluble entonces se adicionan las
				// instrucciones que corresponen a la negación de la restricción
				// que se cree redundante
				Expression redundancyNegationExpression = null;
				if (instructionToCheckRedundacy != null
						&& expressionInstructionToCheckRedundancy == null) {
					redundancyNegationExpression = new LiteralBooleanExpression(
							instructionToCheckRedundacy);
				} else if (expressionInstructionToCheckRedundancy != null) {
					redundancyNegationExpression = expressionInstructionToCheckRedundancy;
				}
				variabilityModelConstraintRepresentationWithoutRedundacy
						.add(redundancyNegationExpression);

				// Se convierte el conjunto de restricciones sin la instrucción
				// redundante y con las instrucciones que niegan la redundancia
				// a un archivo en
				// prolog que se guarda en la ruta temporal. Se quita la
				// instrucción que se crea redundante pq si es realmente
				// redundante otras cosas en el modelo estan haciendo que ese
				// valor sera verdadero
				ConstraintRepresentationUtil
						.savePrologRepresentationProgram(
								prologTempPath,
								variabilityModelConstraintRepresentationWithoutRedundacy,
								prologEditorType);

				// Se evalua si el modelo de restricciones guardado en la
				// ruta temporal es resoluble
				isSatisfiable = solver.isSatisfiable(
						prologTempPath);

				if (!isSatisfiable) {
					// La restricción si es redundante pq el modelo se volvió
					// irresoluble
					Defect redundancy = new Redundancy(redundantDependency,
							redundancyNegationExpression);
					return redundancy;

				}
			}
		} else {
			// No se puede evaluar la redundacias, el modelo debe ser resoluble
			throw new FunctionalException(
					"Variability model must be not void to check redundancies");
		}

		return null;
	}

	/**
	 * @param elementsMapToVerify
	 * @return
	 * @throws FunctionalException
	 */
	public List<Defect> identifyNonAttainableDomains(
			Map<String, VariabilityElement> elementsMapToVerify)
			throws FunctionalException {

		List<Defect> notAttainableDomains = new ArrayList<Defect>();
		List<Integer> definedDomainValues = null;
		Set<Integer> attainableDomains = null;
		List<Expression> variabilityModelConstraintRepresentation = new ArrayList<Expression>();

		Expression constraintToIdentifyNonAttainableDomain = null;

		for (VariabilityElement element : elementsMapToVerify.values()) {
			DefectAnalyzerDomain domain = element.getDomain();
			// Se obtienen los valores parametrizados para esta variable
			definedDomainValues = domain.getDomainValues();

			// Se busca para cada variable los valores de dominio y se analiza
			// siempre y cuando no este en la lista de valores permitidos
			for (Integer valueToTest : definedDomainValues) {

				attainableDomains = attainableDomainsByVariabilityElementMap
						.get(element);
				attainableDomains = null;
				variabilityModelConstraintRepresentation.clear();
				if (attainableDomains == null
						|| (attainableDomains != null && !attainableDomains
								.contains(valueToTest))) {

					// Se adiciona las restricciones que tiene el modelo de
					// variabilidad inicial
					variabilityModelConstraintRepresentation
							.addAll(ConstraintRepresentationUtil
									.dependencyToExpressionList(analyzerInDTO
											.getVariabilityModel()
											.getDependencies(), analyzerInDTO
											.getVariabilityModel()
											.getFixedDependencies()));

					// Constraint para verificar el dominio no alcanzable
					// variabilityElement= valueToTest
					constraintToIdentifyNonAttainableDomain = VerifierUtilExpression
							.verifyAssignValueToVariabilityElementExpression(
									element, valueToTest);

					// Se adiciona la restricción al conjunto de restricciones
					// que representa el modelo de variabilidad
					variabilityModelConstraintRepresentation.add(0,
							constraintToIdentifyNonAttainableDomain);

					// Se convierte el conjunto de restricciones a un archivo en
					// prolog que se guarda en la ruta temporal
					ConstraintRepresentationUtil
							.savePrologRepresentationProgram(prologTempPath,
									variabilityModelConstraintRepresentation,
									prologEditorType);

					List<List<Integer>> configuredValuesList = new ArrayList<List<Integer>>();

					// Se evalua si este nuevo modelo es satisfacible y se
					// obtienen los valores de la configuración
					//FIXME
					/*configuredValuesList = SolverOperationsUtil
							.getSelectedVariablesByConfigurations(
									prologTempPath, 1, prologEditorType);*/

					// Si se obtienen valores esto quiere decir q es
					// satisfacible
					if (configuredValuesList != null
							&& !configuredValuesList.isEmpty()) {

						List<Integer> configuredValues = configuredValuesList
								.get(0);
						HlclProgram expressionProgram = ConstraintRepresentationUtil
								.expressionToHlclProgram(variabilityModelConstraintRepresentation);
						Set<Identifier> constraintProgramIdentifiersCollection = HlclUtil
								.getUsedIdentifiers(expressionProgram);
						// Los valores identificados no son non attainable
						// domains, se actualizan los valores en el mapa
						updateEvaluatedDomainsMap(configuredValues,
								elementsMapToVerify,
								constraintProgramIdentifiersCollection);
					} else {
						// Se crea un nuevo defecto
						NonAttainableDomain defect = new NonAttainableDomain(
								element, valueToTest);
						notAttainableDomains.add(defect);
						System.out.println("NonattainableDomain"
								+ element.getName() + ": " + valueToTest);
					}
				}

			}

		}
		return notAttainableDomains;

	}

	private void updateEvaluatedDomainsMap(List<Integer> configuredValues,
			Map<String, VariabilityElement> variabilityElements,
			Set<Identifier> constraintProgramIdentifiersCollection) {

		VariabilityElement element = null;
		int i = 0;
		for (Identifier identifier : constraintProgramIdentifiersCollection) {

			Integer value = configuredValues.get(i);

			// Se busca en la lista de variabilityElements
			if (variabilityElements.containsKey(identifier.getId())) {
				// Se obtiene el variabilityElement
				element = variabilityElements.get(identifier.getId());
				// Se verifica si el mapa tiene el variability element
				if (attainableDomainsByVariabilityElementMap
						.containsKey(element)) {
					// Si existe se adiciona el valor de la variable al set de
					// dominios permitidos
					attainableDomainsByVariabilityElementMap.get(element).add(
							value);
				} else {
					// Si el mapa no contiene el variabilityElement se adiciona
					// a
					// mapa y se adiciona el valor de dominio
					// objetos
					Set<Integer> attainableDomainValuesSet = new HashSet<Integer>();
					attainableDomainValuesSet.add(value);
					attainableDomainsByVariabilityElementMap.put(element,
							attainableDomainValuesSet);
				}

			} else {
				// Quiere decir que no se encuentran dentro de los elementos
				// para los que se debe verificar el valor
			}
			i++;
		}

	}

	/**
	 * Contrala las invocaciones a la clase que verifica los defectos del modelo
	 * 
	 * @param defectAnalyzerInDTO
	 * @return VerifierOutDTO
	 * @throws FunctionalException
	 * @throws PrologException
	 */
	public VMVerifierOutDTO verifierOfDefects(boolean verifyDeadFeatures,
			boolean verifyFalseOptionalElement, boolean verifyFalseProductLine,
			boolean verifyNonAttainableDomains, boolean verifyRedundancies)
			throws FunctionalException {

		VMVerifierOutDTO outDTO = new VMVerifierOutDTO();

		// Siempre se verifica si es void, pq por como está pensado la
		// solución
		// es obligatorio hacer esto
		Defect isVoid = isVoid();
		outDTO.setVoidModel(isVoid);

		if (isVoid==null) {

			outDTO.setVoidModel(isVoid);
			if (verifyFalseProductLine) {
				Defect isFalsePLM = isFalsePLM();
				outDTO.setFalseProductLineModel(isFalsePLM);
			}
			if (verifyDeadFeatures) {
				// Dead features
				List<Defect> deadFeatures = identifyDeadFeatures(analyzerInDTO
						.getVariabilityModel().getElements());
				outDTO.setDeadFeaturesList(deadFeatures);
			}
			if (verifyFalseOptionalElement) {
				// False optional elements
				Map<String, VariabilityElement> optionalElements = analyzerInDTO
						.getVariabilityModel().getOptionalVariabilityElements();
				if (optionalElements != null && !optionalElements.isEmpty()) {
					// Si esta vacío entonces no se verifica ninguno
					List<Defect> falseOptionalFeatures = identifyFalseOptionalFeatures(optionalElements);
					outDTO.setFalseOptionalFeaturesList(falseOptionalFeatures);
				}
				
			}
			if (verifyNonAttainableDomains) {
				List<Defect> nonAttainableDomainsList = identifyNonAttainableDomains(analyzerInDTO
						.getVariabilityModel().getElements());
				outDTO.setDomainNotAttainableList(nonAttainableDomainsList);
			}

			if (verifyRedundancies) {
				List<Defect> redundancies = identifyAllRedundanciesSPLOTModels(analyzerInDTO
						.getVariabilityModel()
						.getInclusionExclusionDependencies());
				outDTO.setRedundanciesList(redundancies);
			}

		} else {
			System.out.println("Void model");
		}

		return outDTO;

	}

	public static void printFoundDefects(VMVerifierOutDTO outDTO) {
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
		if (outDTO.getDomainNotAttainableList() != null) {
			for (Defect nonAttainableDomain : outDTO
					.getDomainNotAttainableList()) {

				Integer nonAttainableValue = ((NonAttainableDomain) nonAttainableDomain)
						.getNotAttainableDomain();
				System.out.println("NON ATTAINABLE DOMAIN "
						+ nonAttainableDomain.getId() + " VALUE : "
						+ nonAttainableValue);
			}
		}
	}

	/**
	 * @return the prologEditorType
	 */
	public SolverEditorType getPrologEditorType() {
		return prologEditorType;
	}

	/**
	 * @param prologEditorType
	 *            the prologEditorType to set
	 */
	public void setPrologEditorType(SolverEditorType prologEditorType) {
		this.prologEditorType = prologEditorType;
	}
}