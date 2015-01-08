package com.variamos.defectAnalyzer.defectAnalyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.jgprolog.core.PrologException;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.defectAnalyzer.dto.VMAnalyzerInDTO;
import com.variamos.defectAnalyzer.dto.VMCauseAnalyzerInDTO;
import com.variamos.defectAnalyzer.dto.VMCauseAnalyzerOutDTO;
import com.variamos.defectAnalyzer.dto.VerificationResult;
import com.variamos.defectAnalyzer.model.AnalyzedCorrectionSet;
import com.variamos.defectAnalyzer.model.Dependency;
import com.variamos.defectAnalyzer.model.Diagnosis;
import com.variamos.defectAnalyzer.model.VariabilityModel;
import com.variamos.defectAnalyzer.model.defects.Defect;
import com.variamos.defectAnalyzer.model.defects.Redundancy;
import com.variamos.defectAnalyzer.model.defects.VoidModel;
import com.variamos.defectAnalyzer.model.enums.DefectAnalyzerMode;
import com.variamos.defectAnalyzer.util.ConstraintRepresentationUtil;
import com.variamos.defectAnalyzer.util.PowerSetUtil;
import com.variamos.defectAnalyzer.util.SetUtil;
import com.variamos.defectAnalyzer.util.SolverOperationsUtil;


public class VariabilityModelCausesAndCorrectionsAnalyzer {

	
	private SolverOperationsUtil solver;

	public VariabilityModelCausesAndCorrectionsAnalyzer(
			SolverEditorType solverEditorType) {
		solver = new SolverOperationsUtil(solverEditorType);

	}

	/**
	 * Verifica si un conjunto de restricciones son satisfacibles o
	 * insatisfacibles
	 * 
	 * @param dependenciesSet
	 * @param defectToAnalyze
	 * @return true: is satisfiable. Otherwise False
	 * @throws FunctionalException
	 */
	private boolean isSatisfiableDependenciesWithDefectAndFixedDependencies(
			List<Dependency> dependenciesSet,
			List<Dependency> fixedDependenciesList, Defect defectToAnalyze)
			throws FunctionalException {

		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(dependenciesSet,
						fixedDependenciesList);

		// Se adiciona a la lista la expression que permite verificar el defecto
		if (defectToAnalyze.getVerificationExpression() != null) {
			variabilityModelConstraintRepresentation.add(defectToAnalyze
					.getVerificationExpression());
		}

		// Se transforma la expresión al solver que se guarda en la ruta
		// temporal si no hay errores no se generan excepciones

		if (vmAnalyzerInDTO.getVariabilityModel().getDomainStringList() != null
				&& !vmAnalyzerInDTO.getVariabilityModel().getDomainStringList()
						.isEmpty()) {
			// Save the variability model in a prolog program
			ConstraintRepresentationUtil
					.savePrologRepresentationProgram(prologTempPath,
							variabilityModelConstraintRepresentation,
							vmAnalyzerInDTO.getVariabilityModel()
									.getDomainStringList(), prologEditorType);

		} else {

			// Save the variability model in a prolog program
			ConstraintRepresentationUtil.savePrologRepresentationProgram(
					prologTempPath, variabilityModelConstraintRepresentation,
					prologEditorType);
		}
		// Se verifica si el conjunto de restricciones es satisfacible
		// Se verifica si el problema guardado es satisfacible
		boolean isSatisfiable = solver.isSatisfiable(prologTempPath);

		// Se retorna el resultado
		return isSatisfiable;

	}

	/**
	 * Identifica los MCS para un conjunto de dependencies según el
	 * CorrectionSetIdentificationType ingresado ( Todos los MCS, de máximo un
	 * tamaño, el más pequeño)
	 * 
	 * @param correctionSetIdentifcationType
	 * @param maxK
	 *            : Tamaño máximo del conjunto corrección cuando se desea
	 *            restringir el tamaño del espacio de búsqueda
	 * @return
	 * @throws FunctionalException
	 * @throws PrologException
	 */
	private Diagnosis identifyMCSandMUSes(
			DefectAnalyzerMode correctionSetIdentifcationType,
			Map<Long, Dependency> modelDependenciesListToTest,
			Map<Long, Dependency> fixedDependenciesList,
			String pathPrologTempFile, Defect defectToAnalyze)
			throws FunctionalException {

		// Bandera que controla hasta cuando se buscan los MCS
		boolean continueMCSesIdentification = Boolean.TRUE;
		int r = 1;
		// Almacena todos los MCS identificados
		List<List<Dependency>> allMCSes = new ArrayList<List<Dependency>>();
		// Almacena todos los MUSes
		List<List<Dependency>> allMUSes = new ArrayList<List<Dependency>>();
		// Combinaciones de potenciales MCS que no deben ser analizadas pq se
		// sabe previamente que no van a generar ningún resultado
		List<List<Dependency>> lockedClausesSets = new ArrayList<List<Dependency>>();
		List<List<Dependency>> unsatisfiableCollectionOfSets = new ArrayList<List<Dependency>>();
		List<Dependency> variabilityDependencyModelList = new ArrayList<Dependency>();
		List<Dependency> fixedDependencyModelList = new ArrayList<Dependency>();
		variabilityDependencyModelList.addAll(modelDependenciesListToTest
				.values());
		fixedDependencyModelList.addAll(fixedDependenciesList.values());

		try {
			while (continueMCSesIdentification) {

				System.out.println("Defecto" + defectToAnalyze);
				System.out.println("McsIdentificados" + allMCSes);

				// Se construye el powerset de relaciones a ajustar se podan los
				// elementos del MCS ya identificados y las cláusulas que se
				// deben bloquear.
				System.out.println("Construyendo combinationes tamaño " + r
						+ " ... ");
				List<List<Dependency>> combinatorialPowerSetByLevel = PowerSetUtil
						.calculateSets(new ArrayList<Dependency>(),
								variabilityDependencyModelList,
								new ArrayList<List<Dependency>>(), r, allMCSes,
								lockedClausesSets);

				// No se pudieron hacer combinaciones de tamaño k con los
				// elementos
				// de entrada, entonces se termina el algoritmo
				if (combinatorialPowerSetByLevel.isEmpty()) {
					continueMCSesIdentification = Boolean.FALSE;
				} else {

					// Se consultan los MCS de este nivel, teniendo en cuenta
					// los
					// bloqueos ya encontrados. Se generan nuevos bloqueos
					List<List<Dependency>> MCSes = identifyMCSByLevel(
							combinatorialPowerSetByLevel,
							variabilityDependencyModelList,
							fixedDependencyModelList, lockedClausesSets,
							unsatisfiableCollectionOfSets, defectToAnalyze);

					if (!MCSes.isEmpty()) {
						allMCSes.addAll(MCSes);
					}

					// Se se encuentran MCS para este nivel
					if (!allMCSes.isEmpty()) {

						// Se verifica si se quería identificar los MCS más
						// pequeños
						// y si es así se termina la búsqueda con los MCS
						// identificados
						if (correctionSetIdentifcationType
								.equals(DefectAnalyzerMode.PARTIAL)) {
							continueMCSesIdentification = Boolean.FALSE;
						}
					}
					// Se incrementa el tamaño del nivel de búsqueda
					r++;
				}
			}

			// Se crea un diagnóstico
			Diagnosis diagnostic = createDiagnostic(allMCSes, defectToAnalyze);
			if (r > 2) {
				diagnostic.setCorrecMayoresUno(true);
			}

			System.out.println("Conjuntos unsatisfiables encontrados");
			System.out.println(unsatisfiableCollectionOfSets);
			System.out.println("Tamaño clausulas unsatisifables:"
					+ unsatisfiableCollectionOfSets.size());
			System.out.println("Construyendo las causas");

			// Se identifican las causas de acuerdo a si se tiene o no la
			// colección completa de MCSes
			if (correctionSetIdentifcationType
					.equals(DefectAnalyzerMode.PARTIAL)) {
				allMUSes = unsatisfiableCollectionOfSets;

			} else if (correctionSetIdentifcationType
					.equals(DefectAnalyzerMode.COMPLETE)) {
				allMUSes = HittingSetIdentifier.filterMUSes(allMCSes,
						new ArrayList<Dependency>(), allMUSes);

			}
			// Es solo una regla de control para garantizar que el hitting set
			// este obteniendo los conjuntos correctos
			if (unsatisfiableCollectionOfSets.size() < allMUSes.size()) {
				throw new FunctionalException(
						"Other MUSes should be identified with the hitting set algorithm");
			}
			diagnostic.setCauses(allMUSes);

			return diagnostic;

		} catch (Exception e) {
			// Para que no se pierdan los resultados retorno lo que halla
			System.out.println("EXCEPTION");
			e.printStackTrace();
			return null;
		}

	}

	private List<Dependency> obtainNextDependenciesToTest(
			List<List<Dependency>> dependenciesToChange,
			List<List<Dependency>> blockedClausesSets) {

		List<Dependency> dependencyToReturn = null;
		int indexDependencyToRemove = 0;
		if (!dependenciesToChange.isEmpty()) {
			// Antes de cambiar se verifica que no sea subconjunto de las
			// cláusulas bloquedas, si es subconjunto no se analiza esa
			// combinación
			for (int i = 0; i < dependenciesToChange.size(); i++) {
				if (blockedClausesSets == null
						|| !SetUtil
								.verifySetIsSubSetOfCollectionSets(
										dependenciesToChange.get(i),
										blockedClausesSets)) {
					// Se quita la depden
					dependencyToReturn = dependenciesToChange.get(i);
					indexDependencyToRemove = i;
					break;
				}
			}

			// Se quita la dependencia a remover de la lista
			if (dependencyToReturn != null) {
				dependenciesToChange.remove(indexDependencyToRemove);
			}
		}

		return dependencyToReturn;
	}

	/**
	 * Reemplaza en el programa de restricciones de entrada una a una las
	 * restricciones que se encuentran en dependenciesToChange y verifica si el
	 * nuevo programa es satisfacible, si es satisfacible entonces esa
	 * restricción es un MCS
	 * 
	 * @param dependenciesToChange
	 * @param constraintProgramContent
	 * @param pathPrologTempFile
	 * @return
	 * @throws FunctionalException
	 * @throws PrologException
	 */

	private List<List<Dependency>> identifyMCSByLevel(
			List<List<Dependency>> dependenciesToChange,
			List<Dependency> modelDependenciesList,
			List<Dependency> fixedDependenciesList,
			List<List<Dependency>> blockedClausesSets,
			List<List<Dependency>> unsatisfiableCollectionOfSets,
			Defect defectToAnalyze) throws FunctionalException {
		List<Dependency> dependendencyToRemoveList = null;
		List<Dependency> collectionOfDependenciesToTest = new ArrayList<Dependency>();
		// LLeva la cuenta de cuantas instancias se ejecutan realmente
		int countAnalyzedInstancesByR = 0;
		boolean isSatisfiable = Boolean.FALSE;
		boolean continueMCSIdentification = Boolean.TRUE;
		int r = dependenciesToChange.get(0).size();
		int n = dependenciesToChange.size();
		List<List<Dependency>> MCSes = new ArrayList<List<Dependency>>();
		List<List<Dependency>> newUnsatisfiableCollectionOfSets = new ArrayList<List<Dependency>>();
		System.out.println("INICIO IDENTIFICACIÓN NIVEL " + r);

		while (continueMCSIdentification) {

			dependendencyToRemoveList = obtainNextDependenciesToTest(
					dependenciesToChange, newUnsatisfiableCollectionOfSets);

			if (dependendencyToRemoveList != null) {

				if (dependendencyToRemoveList.isEmpty()) {
					throw new RuntimeException(
							"Cláusula para ser MCS vacía verificar");
				}
				countAnalyzedInstancesByR++;
				System.out.println("Defecto" + defectToAnalyze);
				System.out.println("Ejecución: " + countAnalyzedInstancesByR
						+ " de " + n);
				isSatisfiable = Boolean.FALSE;

				// Se crea la colección de dependencias a verificar
				// excluyendo las cláusulas que deben ser removidas
				collectionOfDependenciesToTest.clear();
				collectionOfDependenciesToTest.addAll(modelDependenciesList);
				collectionOfDependenciesToTest
						.removeAll(dependendencyToRemoveList);

				// Se verifica si el nuevo conjunto es satisfacible
				isSatisfiable = isSatisfiableDependenciesWithDefectAndFixedDependencies(
						collectionOfDependenciesToTest, fixedDependenciesList,
						defectToAnalyze);

				// Si es satisfacible entonces se adiciona al conjunto de MCS
				// identificados, se
				// actualizan las cláusulas a bloquear y se veridia si se puede
				// terminar
				if (isSatisfiable) {
					System.out.println("Identificado MCS tamaño " + r);
					MCSes.add(dependendencyToRemoveList);

				} else {

					System.out
							.println("Iniciando bloqueo dicotómico tamaño K: "
									+ dependendencyToRemoveList.size());
					newUnsatisfiableCollectionOfSets
							.add(blockClausesByDichotomicApproach(
									collectionOfDependenciesToTest,
									modelDependenciesList,
									fixedDependenciesList,
									unsatisfiableCollectionOfSets,
									blockedClausesSets, r, defectToAnalyze));
				}
				System.out.println();
			} else {
				continueMCSIdentification = Boolean.FALSE;
			}

		}
		System.out.println("RESUMEN:Elementos ejecutados nivel " + r + ": "
				+ countAnalyzedInstancesByR + " de " + n);
		System.out.println("Unsatisfiable collection of sets "
				+ unsatisfiableCollectionOfSets.size());
		System.out.println("ClausesToBlock size: " + blockedClausesSets.size());
		System.out.println("MCS encontrados : " + MCSes);
		return MCSes;

	}

	/**
	 * Tomado de: Hemery, F., Lecoutre, C., Sais, L., & Boussemart, F. (2006).
	 * Extracting MUCs from Constraint Networks. Proceedings of the 17th
	 * European Conference on Artificial Intelligence (pp. 113–117). Riva del
	 * Garda, Italy: IOS Press.
	 * 
	 * @return
	 * @throws FunctionalException
	 */
	private int identifyTransitionClausesByDichotomicApproach(
			List<Dependency> originalSetOfClauses,
			List<Dependency> fixedDependenciesList, Defect defectToAnalyze,
			int startConstraintPosition) throws FunctionalException {

		int min = startConstraintPosition + 1;
		int max = originalSetOfClauses.size();
		int center = 0;
		boolean isSatisfiable = Boolean.FALSE;
		List<Dependency> subsetOriginalSetOfClauses = new ArrayList<Dependency>();

		while (min != max) {
			subsetOriginalSetOfClauses.clear();
			center = (int) (min + max) / 2;
			// Se verifica de la lista desde el 1 hasta el centro si es o no
			// satisfiable
			subsetOriginalSetOfClauses.addAll(originalSetOfClauses.subList(0,
					center));
			isSatisfiable = isSatisfiableDependenciesWithDefectAndFixedDependencies(
					subsetOriginalSetOfClauses, fixedDependenciesList,
					defectToAnalyze);
			if (isSatisfiable) {
				min = center + 1;
			} else {
				max = center;
			}
		}
		// Se resta 1 pq en java los vectores comienzan con la posición cero y
		// llegan hasta n-1
		return (min - 1);

	}

	private List<Dependency> reduceUSWithDichotomicApproach(
			List<Dependency> unsatisfiableDependenciesList,
			List<Dependency> fixedDependenciesList, Defect defectToAnalyze)
			throws FunctionalException {

		int identifiedTransitionConstraints = 0;
		int unsatisfiableSize = unsatisfiableDependenciesList.size();
		Dependency transitionClause = null;
		List<Dependency> newUnsatisfiableSet = new ArrayList<Dependency>();
		newUnsatisfiableSet.addAll(unsatisfiableDependenciesList);
		int indexTransitionConstraint = 0;
		while (identifiedTransitionConstraints < unsatisfiableSize) {

			indexTransitionConstraint = identifyTransitionClausesByDichotomicApproach(
					newUnsatisfiableSet, fixedDependenciesList,
					defectToAnalyze, identifiedTransitionConstraints);
			transitionClause = newUnsatisfiableSet
					.get(indexTransitionConstraint);

			if (indexTransitionConstraint >= 0) {

				// Se quita de la lista de unsatisfiable constraint las
				// constraints que esten por encima del index de la transition
				// constraint
				List<Dependency> newUnsatisfiableSetTemp = new ArrayList<Dependency>();
				newUnsatisfiableSetTemp.addAll(newUnsatisfiableSet.subList(0,
						indexTransitionConstraint));

				// Se ajusta el nuevo unsatisfiable sets
				newUnsatisfiableSet.clear();

				newUnsatisfiableSet.addAll(newUnsatisfiableSetTemp);

				// Se inserta la transition constraint en el primer elemento
				newUnsatisfiableSet.add(0, transitionClause);
			} else {
				throw new RuntimeException("Verificar enfoque dicothomic");
			}
			identifiedTransitionConstraints = identifiedTransitionConstraints + 1;
			unsatisfiableSize = newUnsatisfiableSet.size();
		}

		// We have to determine if the last constraint belongs to the MUC
		boolean isSatisfiable = isSatisfiableDependenciesWithDefectAndFixedDependencies(
				newUnsatisfiableSet.subList(1, newUnsatisfiableSet.size()),
				fixedDependenciesList, defectToAnalyze);
		if (!isSatisfiable) {
			return newUnsatisfiableSet.subList(1, newUnsatisfiableSet.size());
		} else {
			return newUnsatisfiableSet;
		}

	}

	/**
	 * Bloquea usanado un enfoque dicotomico cláusulas.
	 * 
	 * @param clausesToRemove
	 * @param transitionClausesList
	 * @param clausesToBlockCollection
	 * @param defectToAnalyze
	 * @return
	 * @throws FunctionalException
	 */
	private List<Dependency> blockClausesByDichotomicApproach(
			List<Dependency> testedModelDependenciesList,
			List<Dependency> originalSetOfClauses,
			List<Dependency> fixedDependenciesList,
			List<List<Dependency>> unsatisfiableCollectionOfSets,
			List<List<Dependency>> blockedClausesSets, int k,
			Defect defectToAnalyze) throws FunctionalException {

		boolean addedColllection = Boolean.FALSE;
		List<Dependency> newUnsatisfiableSet = new ArrayList<Dependency>();
		List<Dependency> unsatisifableSetCopy = new ArrayList<Dependency>();
		List<Dependency> originalDependenciesCopy = new ArrayList<Dependency>();
		List<Dependency> newUnsatisfiableSetComplement = new ArrayList<Dependency>();

		// Guardará la copia que queda al final de cláusulas insatisfacibles (
		// sin el defecto a analizar)
		unsatisifableSetCopy.addAll(testedModelDependenciesList);

		// Se trata de reducir el set unsatisfiable a ser un MUS
		// usando el enfoque dicotomico
		System.out.println("Reduciendo set unsatisfiable ...");
		newUnsatisfiableSet = reduceUSWithDichotomicApproach(
				unsatisifableSetCopy, fixedDependenciesList, defectToAnalyze);

		if (newUnsatisfiableSet.isEmpty()) {
			throw new FunctionalException(
					"Error with the dicothomic approach.Please verify that fixed restrictions do not conflict with verification constraints");
		}

		// Se adiciona a la colección general de clausulas
		// unsatisfiables
		addedColllection = addUnsatisfiableCollectionsOfDependenciesToBlock(
				unsatisfiableCollectionOfSets, newUnsatisfiableSet);

		System.out.println("Clausulas unsatisfiables reducidas: "
				+ unsatisfiableCollectionOfSets);

		if (addedColllection) {
			// Se obtiene copia de la lista de dependencias
			// original, para
			// obtener el complemento correctamente. Sino como
			// el
			// paso es por referencia se afecta la colección
			// original
			originalDependenciesCopy.clear();
			originalDependenciesCopy.addAll(originalSetOfClauses);
			newUnsatisfiableSetComplement.clear();

			newUnsatisfiableSetComplement.addAll((List<Dependency>) SetUtil
					.difference(originalDependenciesCopy, newUnsatisfiableSet));

			// Se adiciona al conjunto de cláusulas
			// insatisfacibles
			// completo
			blockedClausesSets = addUnneccesaryCollectionsOfDependenciesToBlock(
					blockedClausesSets, newUnsatisfiableSetComplement, k);
		}

		System.out.println("Conjunto de restricciones a bloquear: "
				+ blockedClausesSets.size());
		return newUnsatisfiableSetComplement;
	}

	/**
	 * Adiciona a la collección de sets las cláusulas insatisfacibles.
	 * Verificando que no existan subsets
	 * 
	 * @param blockedClausesSets
	 * @param clausesToBlock
	 * @return
	 */
	private List<List<Dependency>> addUnneccesaryCollectionsOfDependenciesToBlock(
			List<List<Dependency>> blockedClausesSets,
			List<Dependency> clausesToBlock, int minimalSize) {

		// Se adiciona la cláusula y luego se eliminan los subsets que existan
		// dentro de la lista de cláusulas a bloquear, pq son innecesarios

		if (clausesToBlock != null && !clausesToBlock.isEmpty()) {
			// Si la cláusula a bloquear es mayor al tamaño de las cláusulas que
			// se están analizando se adiciona. Sino no, pues ya se habría
			// analizado esa coleccion
			if (blockedClausesSets != null) {
				if (clausesToBlock.size() >= minimalSize) {
					if (!SetUtil.verifyCollectionSetContainsSet(clausesToBlock,
							blockedClausesSets)) {
						blockedClausesSets.add(clausesToBlock);
						SetUtil.maintainNoSubsets(blockedClausesSets);
					} else {
						System.out.println("Set a bloquear ya existía");
					}

				} else {
					System.out
							.println("No cumple el tamaño mínimo el set a bloquear");
				}
			}
		}

		return blockedClausesSets;

	}

	/**
	 * Adiciona a la collección de sets las cláusulas insatisfacibles.
	 * Verificando que no existan supersets
	 * 
	 * @param unsatisfiableCollectionOfSets
	 * @param clausesToAdd
	 * @return
	 */
	private boolean addUnsatisfiableCollectionsOfDependenciesToBlock(
			List<List<Dependency>> unsatisfiableCollectionOfSets,
			List<Dependency> clausesToAdd) {

		// Se adiciona la cláusula y luego se eliminan los supersets que existan
		// dentro de la lista de cláusulas a bloquear, pq son innecesarios
		boolean addedCollection = false;
		if (clausesToAdd != null && !clausesToAdd.isEmpty()) {
			if (unsatisfiableCollectionOfSets != null) {
				if (!SetUtil.verifyCollectionSetContainsSet(clausesToAdd,
						unsatisfiableCollectionOfSets)) {

					unsatisfiableCollectionOfSets.add(clausesToAdd);
					addedCollection = true;
				} else {
					System.out.println("Unsatisfiable set ya existía");
				}
			}
		}

		return addedCollection;

	}

	public Diagnosis analyzeCausesCorrectionsRedundancies(
			Defect defectToAnalyze,
			Map<Long, Dependency> modelDependenciesList,
			Map<Long, Dependency> fixedDependenciesList,
			DefectAnalyzerMode defectAnalyzerMode) throws FunctionalException {
		// Se quita de la lista de dependencias la relación que se considera
		// redundante para que pueda ponerse su instrucción de verificación.
		// Si ambas se dejan juntas se estaría generando una contradicción en el
		// que la solución es quitar la misma restricción redundante y eso no
		// tiene lógica
		Map<Long, Dependency> modelDependenciesCopy = new HashMap<Long, Dependency>();
		modelDependenciesCopy.putAll(modelDependenciesList);
		modelDependenciesCopy.remove(((Redundancy) defectToAnalyze)
				.getRedundantDependency().getRelationShipNumber());

		// Se invoca al método que analiza las causas y correcciones de todos
		// los defectos y se retorna el diagnóstico
		return analyzeCausesOneDefect(defectToAnalyze, modelDependenciesCopy,
				fixedDependenciesList, defectAnalyzerMode);
	}

	public Diagnosis analyzeCausesOneDefect(Defect defectToAnalyze,
			Map<Long, Dependency> modelDependenciesList,
			Map<Long, Dependency> fixedDependenciesList,
			DefectAnalyzerMode defectAnalyzerMode) throws FunctionalException {
		System.out.println("Analyzed defect: "
				+ defectToAnalyze.getDefectType().name() + " "
				+ defectToAnalyze.getId());
		long startTime = System.currentTimeMillis();

		// Se identifican los MCSes y los MUSes
		if (defectAnalyzerMode != null) {
			Diagnosis diagnostic = identifyMCSandMUSes(defectAnalyzerMode,
					modelDependenciesList, fixedDependenciesList,
					prologTempPath, defectToAnalyze);
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(" Analysis time: " + totalTime);
			return diagnostic;
		} else {
			throw new FunctionalException(
					"Correction set type must be initialized");
		}

	}

	/**
	 * Identifica las causas que originan defectos en los modelos de
	 * variabilidad según los parámetros que se reciben de entrada el DTO del
	 * construtor de la clase
	 * 
	 * @return
	 * @throws FunctionalException
	 * 
	 */
	public VMCauseAnalyzerOutDTO causesAnalyzer(
			boolean identifyCausesDeadFeatures,
			boolean identifyCausesFalseOptional,
			boolean identifyCausesFalseProductLine,
			boolean identifyCausesDomainNotAttainables,
			boolean identifyCausesVoidModel,
			boolean identifyCausesRedundancies,
			VMCauseAnalyzerInDTO vmCauseAnalizerInDTO)
			throws FunctionalException {

		Map<Long, Dependency> modelDependencies = new HashMap<Long, Dependency>();
		modelDependencies.putAll(vmAnalyzerInDTO.getVariabilityModel()
				.getDependencies());

		List<Defect> allDefects = new ArrayList<Defect>();
		allDefects.addAll(vmCauseAnalizerInDTO.getDeadFeaturesList());
		if (vmCauseAnalizerInDTO.getVoidModel() != null) {
			allDefects.add(vmCauseAnalizerInDTO.getVoidModel());
		}
		allDefects.addAll(vmCauseAnalizerInDTO.getFalseOptionalFeaturesList());

		List<Diagnosis> allDiagnostics = new ArrayList<Diagnosis>();
		// if (allDefects.size() == 1) {
		// // Se comienzan a analizar las causas para cada defecto
		if (vmCauseAnalizerInDTO.getVoidModel() != null
				&& identifyCausesVoidModel) {
			// Se invoca al identificador de conjuntos correción para cada
			// defecto

			/*
			 * VoidModel voidModelDefect = new VoidModel(vmAnalyzerInDTO
			 * .getVariabilityModel().getName()); Diagnosis diagnosticVoid =
			 * invokeCausesIdentiferOneDefect( voidModelDefect,
			 * vmCauseAnalizerInDTO.getDefectAnalyzerMode());
			 * allDiagnostics.add(diagnosticVoid);
			 */
		}

		// Si se van a analizar las cauas de las dead features o de la falsa
		// línea de productos
		if ((identifyCausesDeadFeatures || identifyCausesFalseProductLine)
				&& (vmCauseAnalizerInDTO.getDeadFeaturesList() != null && !vmCauseAnalizerInDTO
						.getDeadFeaturesList().isEmpty())) {

			// Se recorren las dead features del modelo y se analizan sus
			// causas
			for (Defect deadFeature : vmCauseAnalizerInDTO
					.getDeadFeaturesList()) {

				// Se invoca el analizador de causas primero para
				// identificar
				// los conjuntos de correción se crea el diagnóstico en el
				// mapa
				Diagnosis diagnosticDeadFeatures = invokeCausesIdentiferOneDefect(
						deadFeature,
						vmCauseAnalizerInDTO.getDefectAnalyzerMode());
				allDiagnostics.add(diagnosticDeadFeatures);

			}

		}
		// // Si se van a analizar las causas de false optional features o
		// de la
		// falsa línea de productos
		if ((identifyCausesFalseOptional || identifyCausesFalseProductLine)
				&& vmCauseAnalizerInDTO.getFalseOptionalFeaturesList() != null
				&& !vmCauseAnalizerInDTO.getFalseOptionalFeaturesList()
						.isEmpty()) {

			// Se recorren las false optional features del modelo y se
			// analizan
			// sus causas
			for (Defect falseOptionalFeature : vmCauseAnalizerInDTO
					.getFalseOptionalFeaturesList()) {
				Diagnosis diagnosticFalseOptional = invokeCausesIdentiferOneDefect(
						falseOptionalFeature,
						vmCauseAnalizerInDTO.getDefectAnalyzerMode());
				allDiagnostics.add(diagnosticFalseOptional);

			}

		}

		// Si se van a analizar las causas y correcciones de las
		// redundancias
		if (identifyCausesRedundancies
				&& vmCauseAnalizerInDTO.getRedundancies() != null
				&& !vmCauseAnalizerInDTO.getRedundancies().isEmpty()) {
			// Se recorren las redundancias del modelo y se analizan
			// sus causas
			for (Defect redundancy : vmCauseAnalizerInDTO.getRedundancies()) {
				Diagnosis diagnosticRedundancy = analyzeCausesCorrectionsRedundancies(
						redundancy, vmAnalyzerInDTO.getVariabilityModel()
								.getDependencies(), vmAnalyzerInDTO
								.getVariabilityModel().getFixedDependencies(),
						vmCauseAnalizerInDTO.getDefectAnalyzerMode());
				allDiagnostics.add(diagnosticRedundancy);

			}
		}

		// Si se van a analizar las causas y las correcciones del falso
		// modelo
		// de línea de productos
		if (identifyCausesFalseProductLine
				&& vmCauseAnalizerInDTO.getFalseProductLine() != null) {
			// Se crea un diagnóstico vacío pq las causas y las correcciones
			// son
			// iguales a las de cualquier característica muerta o falsa
			// característica opcional
			Diagnosis diagnostic = new Diagnosis();
			diagnostic.setDefect(vmCauseAnalizerInDTO.getFalseProductLine());
			allDiagnostics.add(diagnostic);
		}
		// }
		// Se crea el DTO de Salida
		VMCauseAnalyzerOutDTO causeAnalizerOutDTO = new VMCauseAnalyzerOutDTO();
		causeAnalizerOutDTO.setAllDiagnostics(allDiagnostics);
		return causeAnalizerOutDTO;
	}

	/**
	 * @param allCorrectionSubsets
	 * @param defectToAnalyze
	 * @return Diagnostic object with the defect, and the correctionsubsets
	 * @throws FunctionalException
	 */
	private Diagnosis createDiagnostic(
			List<List<Dependency>> allCorrectionSubsets, Defect defectToAnalyze)
			throws FunctionalException {

		if (!allCorrectionSubsets.isEmpty()) {
			// Se crea un diagnóstico y se adiciona a la lista
			Diagnosis diagnostic = new Diagnosis();
			diagnostic.setDefect(defectToAnalyze);
			diagnostic.setCorrectionSubsets(allCorrectionSubsets);
			return diagnostic;
		} else {
			// Se lanza una excepción que indica que no se encontró ningún
			// correction subset pq esto no es normal
			// throw new FunctionalException(
			// "The analyzer found none fix for the defect :"
			// + defectToAnalyze.getDefectType().name() + " "
			// + defectToAnalyze.getId());
		}
		return new Diagnosis();
	}

	public AnalyzedCorrectionSet analyzeCorrectionSets(
			List<Dependency> correctionSet, Long idCorrectionSet,
			Defect analyzedDefect) throws FunctionalException {

		AnalyzedCorrectionSet analyzedCorrectionSet = new AnalyzedCorrectionSet();

		// Se eliminan los correction set del variabilityModel, así se obtendrá
		// un nuevo modelo sobre el que se hara una verificación de defectos
		VariabilityModel newVariabilityModel = new VariabilityModel();

		newVariabilityModel.setElements(vmAnalyzerInDTO.getVariabilityModel()
				.getElements());
		newVariabilityModel.setFixedDependencies(vmAnalyzerInDTO
				.getVariabilityModel().getFixedDependencies());
		Map<Long, Dependency> newVariabilityModelDependencies = new HashMap<Long, Dependency>();
		newVariabilityModelDependencies.putAll(vmAnalyzerInDTO
				.getVariabilityModel().getDependencies());
		newVariabilityModel.setOptionalVariabilityElements(vmAnalyzerInDTO
				.getVariabilityModel().getOptionalVariabilityElements());
		for (Dependency correctionDependency : correctionSet) {
			if (newVariabilityModelDependencies
					.containsKey(correctionDependency.getRelationShipNumber())) {
				newVariabilityModelDependencies.remove(correctionDependency
						.getRelationShipNumber());
			} else {
				throw new RuntimeException(
						"Variability model does not have a dependency of the correction set. Review");
			}
		}

		newVariabilityModel.setDependencies(newVariabilityModelDependencies);

		// CREATE VERIFIER MAIN CLASS
		// DefectsVerifier verifier = new DefectsVerifier(sol);
		/*
		 * VerificationResult verificationResult =
		 * verifier.verifierOfDefects(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
		 * Boolean.FALSE, Boolean.TRUE);
		 */

		// Se pone la información en el analyzed correction set
		// analyzedCorrectionSet.setVerifierOutDTO(verificationResult);
		analyzedCorrectionSet.setCorrectionSubsets(correctionSet);
		analyzedCorrectionSet.setId(idCorrectionSet);
		analyzedCorrectionSet.setAnalyzedDefect(analyzedDefect);

		return analyzedCorrectionSet;
	}

	public void printResults(Diagnosis diagnostic) {
		// 3. PRINT RESULTS
		System.out.println("_________________ RESULTADOS____________________");
		System.out.println("_________________ MCS____________________");
		diagnostic.printCorrections();

		System.out.println("_________________ CAUSES____________________");
		diagnostic.printCauses();

	}

	private Diagnosis invokeCausesIdentiferOneDefect(Defect defect,
			DefectAnalyzerMode defectAnalyzerMode) throws FunctionalException {
		long startCorrectionSetTestTime = System.currentTimeMillis();

		Diagnosis diagnostic = null;
		if (defect instanceof Redundancy) {
			diagnostic = analyzeCausesCorrectionsRedundancies(defect,
					vmAnalyzerInDTO.getVariabilityModel().getDependencies(),
					vmAnalyzerInDTO.getVariabilityModel()
							.getFixedDependencies(), defectAnalyzerMode);

		} else {
			diagnostic = analyzeCausesOneDefect(defect, vmAnalyzerInDTO
					.getVariabilityModel().getDependencies(), vmAnalyzerInDTO
					.getVariabilityModel().getFixedDependencies(),
					defectAnalyzerMode);
		}

		// 3. PRINT RESULTS
		// printResults(diagnostic);

		long endCorrectionSetTestTime = System.currentTimeMillis();
		long totalCorrectionSetTestTime = endCorrectionSetTestTime
				- startCorrectionSetTestTime;

		int seconds = (int) ((totalCorrectionSetTestTime / 1000) % 60);
		int minutes = (int) ((totalCorrectionSetTestTime / 1000) / 60);
		System.out
				.println(("Tiempo defecto " + defect.getDefectType() + minutes
						+ "seg" + seconds + " mils: " + totalCorrectionSetTestTime));
		return diagnostic;
	}
}