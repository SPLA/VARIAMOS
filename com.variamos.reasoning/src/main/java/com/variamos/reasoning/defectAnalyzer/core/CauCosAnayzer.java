package com.variamos.reasoning.defectAnalyzer.core;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ProgressMonitor;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.common.core.exceptions.TechnicalException;
import com.variamos.common.core.utilities.SetUtil;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;
import com.variamos.reasoning.defectAnalyzer.model.defects.Redundancy;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.CauCos;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.ClassificationTypeEnum;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.ClassifiedElement;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.DefectAnalyzerModeEnum;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.Diagnosis;
import com.variamos.reasoning.defectAnalyzer.model.dto.DefectAnalyzerResultDTO;
import com.variamos.reasoning.util.PowerSetUtil;
import com.variamos.reasoning.util.SolverOperationsUtil;
import com.variamos.solver.model.ConfigurationOptionsDTO;
import com.variamos.solver.model.SolverSolution;

public class CauCosAnayzer implements IntCauCosAnalyzer {

	private SolverOperationsUtil solver;
	private Component parentComponent = null;
	private String progressDisplay = null;
	private long solverTime = 0;
	private long totalTime = 0;
	ProgressMonitor progressMonitor = null;

	public CauCosAnayzer() {
		solver = new SolverOperationsUtil();
	}

	public CauCosAnayzer(Component parentComponent, String progressDisplay) {
		solver = new SolverOperationsUtil();
		this.parentComponent = parentComponent;
		this.progressDisplay = progressDisplay;
	}

	/**
	 * Verifica si un conjunto de restricciones son satisfacibles o
	 * insatisfacibles
	 * 
	 * @param modelExpressions
	 * @param defectToAnalyze
	 * @return true: is satisfiable. Otherwise False
	 * @throws FunctionalException
	 */
	private boolean hasSolution(List<IntBooleanExpression> modelExpressions,
			List<IntBooleanExpression> fixedExpressions, Defect defectToAnalyze) throws FunctionalException {

		// Se adiciona a la lista la expression que permite verificar el defecto
		if (defectToAnalyze.getVerificationExpressions() != null) {

			List<IntBooleanExpression> modelCopy = new ArrayList<IntBooleanExpression>();
			modelCopy.addAll(modelExpressions);
			modelCopy.addAll(defectToAnalyze.getVerificationExpressions());
			if (!fixedExpressions.isEmpty()) {
				modelCopy.addAll(fixedExpressions);
			}

			HlclProgram program = new HlclProgram();
			program.addAll(modelCopy);
			boolean isMCS = solver.isSatisfiable(program, new SolverSolution(), new ConfigurationOptionsDTO());

			// Se retorna el resultado
			return isMCS;
		} else {
			throw new FunctionalException("Verification expression is mandatory to identify causes and corrections");
		}

	}

	private List<IntBooleanExpression> getNextSet(List<List<IntBooleanExpression>> dependenciesToChange,
			List<List<IntBooleanExpression>> blockedClausesSets) {

		List<IntBooleanExpression> dependencyToReturn = null;
		int indexDependencyToRemove = 0;
		if (!dependenciesToChange.isEmpty()) {
			// Antes de cambiar se verifica que no sea subconjunto de las
			// cláusulas bloquedas, si es subconjunto no se analiza esa
			// combinación
			for (int i = 0; i < dependenciesToChange.size(); i++) {
				if (blockedClausesSets == null || !SetUtil
						.verifySetIsSubSetOfCollectionSets(dependenciesToChange.get(i), blockedClausesSets)) {
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
	 * Identifica los MCS para un conjunto de dependencies según el mode
	 * ingresado ( Todos los MCS o los más pequeños)
	 * 
	 * @param mode
	 * @param maxK
	 *            : Tamaño máximo del conjunto corrección cuando se desea
	 *            restringir el tamaño del espacio de búsqueda
	 * @return
	 * @throws FunctionalException
	 * @throws PrologException
	 */
	private List<List<IntBooleanExpression>> getMCSes(Defect defectToAnalyze, HlclProgram modelToTest,
			List<List<IntBooleanExpression>> unsatisfiableSets, HlclProgram fixedExpressions,
			DefectAnalyzerModeEnum mode) throws FunctionalException {

		// Bandera que controla hasta cuando se buscan los MCS
		boolean advance = Boolean.TRUE;
		int r = 1;
		// Almacena todos los MCS identificados
		List<List<IntBooleanExpression>> allMCSes = new ArrayList<List<IntBooleanExpression>>();

		// Combinaciones de potenciales MCS que no deben ser analizadas pq se
		// sabe previamente que no van a generar ningún resultado
		List<List<IntBooleanExpression>> blockedConstraints = new ArrayList<List<IntBooleanExpression>>();

		HlclProgram modelExpressions = new HlclProgram();
		modelExpressions.addAll(modelToTest);

		while (advance) {

			if (progressMonitor != null) {
				String message = "Building combinations: size " + r;
				progressMonitor.setNote(message);
			}
			System.out.println("Defecto" + defectToAnalyze);
			System.out.println("McsIdentificados" + allMCSes);
			System.out.println("Construyendo combinationes tamaño " + r + " ... ");

			// Se construye el powerset de relaciones a ajustar se podan los
			// elementos del MCS ya identificados y las cláusulas que se
			// deben bloquear.
			List<List<IntBooleanExpression>> subSets = PowerSetUtil.calculateSets(new ArrayList<IntBooleanExpression>(),
					modelExpressions, new ArrayList<List<IntBooleanExpression>>(), r, allMCSes, blockedConstraints);

			if (subSets.isEmpty()) {
				// No se pudieron hacer combinaciones de tamaño k con los
				// elementos de entrada, entonces se termina el algoritmo
				advance = Boolean.FALSE;
			} else {
				// Se consultan los MCS de este nivel, teniendo en cuenta
				// los bloqueos ya encontrados. Se generan nuevos bloqueos
				List<List<IntBooleanExpression>> MCSes = identifyMCSBySize(subSets, modelExpressions, fixedExpressions,
						blockedConstraints, unsatisfiableSets, defectToAnalyze);

				if (!MCSes.isEmpty()) {
					allMCSes.addAll(MCSes);
				}

				// Se se encuentran MCS para este nivel
				if (!allMCSes.isEmpty()) {

					// Se verifica el modo
					if (mode.equals(DefectAnalyzerModeEnum.PARTIAL)) {
						advance = Boolean.FALSE;
					}
				}

				// Se verifica el modo
				if (!unsatisfiableSets.isEmpty() && ((mode.equals(DefectAnalyzerModeEnum.INCOMPLETE_FAST) && r > 1))
						|| (mode.equals(DefectAnalyzerModeEnum.INCOMPLETE_MED) && r > 3)
						|| (mode.equals(DefectAnalyzerModeEnum.INCOMPLETE_SLOW) && r > 5)) {
					advance = Boolean.FALSE;
					allMCSes = unsatisfiableSets;

				}
				// Se incrementa el tamaño del nivel de búsqueda
				r++;
			}
		}
		return allMCSes;

	}

	private List<CauCos> getCauses(Defect defectToAnalyze, HlclProgram fixedConstraints, List<CauCos> corrections,
			List<List<IntBooleanExpression>> unsatisfiableSets, DefectAnalyzerModeEnum mode)
					throws FunctionalException {

		List<List<IntBooleanExpression>> allMUSes = new ArrayList<List<IntBooleanExpression>>();
		List<CauCos> causes = new ArrayList<CauCos>();

		if (mode.equals(DefectAnalyzerModeEnum.PARTIAL) || mode.equals(DefectAnalyzerModeEnum.COMPLETE )) {
			// We use the unsatisfiable constraints that were previously identified. They
			// are causes identified while we identify corrections

			// Each MUS is filter to avoid verification expressions and fixed
			// expressions in the set of causes
			for (List<IntBooleanExpression> expressions : unsatisfiableSets) {
				List<IntBooleanExpression> expressionsFiltered = new ArrayList<IntBooleanExpression>();
				expressionsFiltered.addAll(expressions);
				expressionsFiltered.removeAll(defectToAnalyze.getVerificationExpressions());
				expressionsFiltered.removeAll(fixedConstraints);
				allMUSes.add(expressionsFiltered);
			}
		} else if (mode.equals(DefectAnalyzerModeEnum.CAUSES_CORRECTIONS)) {
			// In this case we use the hitting set method to identify causes
			List<List<IntBooleanExpression>> sets = new ArrayList<List<IntBooleanExpression>>();
			for (CauCos correction : corrections) {
				sets.add(correction.getElements());
			}
			allMUSes = HittingSetIdentifier.filterMUSes(sets, new ArrayList<IntBooleanExpression>(), allMUSes);

		}
		// Es solo una regla de control para garantizar que el hitting set
		// este obteniendo los conjuntos correctos
		if (unsatisfiableSets.size() < allMUSes.size()) {
			throw new FunctionalException("Other MUSes should be identified with the hitting set algorithm");
		}

		// Se crean tantas causas como MUSes se hubieran identificado
		for (List<IntBooleanExpression> MUSes : allMUSes) {
			causes.add(new CauCos(MUSes));
		}

		return causes;

	}

	/**
	 * Reemplaza en el programa de restricciones de entrada una a una las
	 * restricciones que se encuentran en dependenciesToChange y verifica si el
	 * nuevo programa es satisfacible, si es satisfacible entonces esa
	 * restricción es un MCS
	 * 
	 * @param expressionsToTest
	 * @param modelToTest
	 * @param fixedExpressions
	 * @param blockedConstraints
	 * @param unsatisifableSets
	 * @param defect
	 * @return
	 * @throws FunctionalException
	 */
	private List<List<IntBooleanExpression>> identifyMCSBySize(List<List<IntBooleanExpression>> expressionsToTest,
			HlclProgram modelToTest, HlclProgram fixedExpressions, List<List<IntBooleanExpression>> blockedConstraints,
			List<List<IntBooleanExpression>> unsatisifableSets, Defect defect) throws FunctionalException {
		List<IntBooleanExpression> candidateMCS = null;
		List<IntBooleanExpression> modelExpressions = new ArrayList<IntBooleanExpression>();
		// LLeva la cuenta de cuantas instancias se ejecutan realmente
		int analyzedSets = 0;
		boolean isMCS = Boolean.FALSE;
		boolean advance = Boolean.TRUE;
		int r = expressionsToTest.get(0).size();
		int n = expressionsToTest.size();
		List<List<IntBooleanExpression>> MCSes = new ArrayList<List<IntBooleanExpression>>();
		List<List<IntBooleanExpression>> newUnsatisfiableSet = new ArrayList<List<IntBooleanExpression>>();
		// System.out.println("INICIO IDENTIFICACIÓN NIVEL " + r);

		while (advance) {

			candidateMCS = getNextSet(expressionsToTest, blockedConstraints);

			if (candidateMCS != null) {

				if (candidateMCS.isEmpty()) {
					throw new TechnicalException("Next candidate set to MCS is empty. Make debug to find the error");
				}
				analyzedSets++;
				isMCS = Boolean.FALSE;

				// Se crea la colección de dependencias a verificar
				// excluyendo las cláusulas que deben ser removidas
				modelExpressions.clear();
				modelExpressions.addAll(modelToTest);
				modelExpressions.removeAll(candidateMCS);

				// Se verifica si el nuevo conjunto es satisfacible
				isMCS = hasSolution(modelExpressions, fixedExpressions, defect);

				// Si es satisfacible entonces se adiciona al conjunto de MCS
				// identificados, se
				// actualizan las cláusulas a bloquear y se veridia si se puede
				// terminar
				if (isMCS) {
					System.out.println("Identifing MCS size " + r);
					MCSes.add(candidateMCS);

				} else {

					// System.out
					// .println("Iniciando bloqueo dicotómico tamaño K: "
					// + candidateMCS.size());
					newUnsatisfiableSet.add(blockConstraints(modelExpressions, modelToTest, fixedExpressions,
							unsatisifableSets, blockedConstraints, r, defect));
				}
				System.out.println();
			} else {
				advance = Boolean.FALSE;
			}

		}
		// System.out.println("RESULT: Level " + r + " elements ejecuted: "
		// + analyzedSets + " of " + n);
		// System.out.println("Unsatisfiable collection of sets "
		// + unsatisifableSets.size());
		// System.out.println("ClausesToBlock size: " +
		// blockedConstraints.size());
		// System.out.println("MCS encontrados : " + MCSes);
		return MCSes;

	}

	/**
	 * Tomado de: Hemery, F., Lecoutre, C., Sais, L., & Boussemart, F. (2006).
	 * Extracting MUCs from Constraint Networks. Proceedings of the 17th
	 * European Conference on Artificial Intelligence (pp. 113117). Riva del
	 * Garda, Italy: IOS Press.
	 * 
	 * @return
	 * @throws FunctionalException
	 */
	private int getTransitionClauses(List<IntBooleanExpression> originalSetOfClauses,
			List<IntBooleanExpression> fixedDependenciesList, Defect defectToAnalyze, int startConstraintPosition)
					throws FunctionalException {

		int min = startConstraintPosition + 1;
		int max = originalSetOfClauses.size();
		int center = 0;
		boolean isSatisfiable = Boolean.FALSE;
		List<IntBooleanExpression> subsetOriginalSetOfClauses = new ArrayList<IntBooleanExpression>();

		while (min != max) {
			subsetOriginalSetOfClauses.clear();
			center = (min + max) / 2;
			// Se verifica de la lista desde el 1 hasta el centro si es o no
			// satisfiable
			subsetOriginalSetOfClauses.addAll(originalSetOfClauses.subList(0, center));
			isSatisfiable = hasSolution(subsetOriginalSetOfClauses, fixedDependenciesList, defectToAnalyze);
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

	private List<IntBooleanExpression> reduceUnsatisfiableSet(List<IntBooleanExpression> unsatisfiableDependenciesList,
			List<IntBooleanExpression> fixedDependenciesList, Defect defectToAnalyze) throws FunctionalException {

		int identifiedTransitionConstraints = 0;
		int unsatisfiableSize = unsatisfiableDependenciesList.size();
		IntBooleanExpression transitionClause = null;
		List<IntBooleanExpression> newUnsatisfiableSet = new ArrayList<IntBooleanExpression>();
		newUnsatisfiableSet.addAll(unsatisfiableDependenciesList);
		int indexTransitionConstraint = 0;
		while (identifiedTransitionConstraints < unsatisfiableSize) {

			indexTransitionConstraint = getTransitionClauses(newUnsatisfiableSet, fixedDependenciesList,
					defectToAnalyze, identifiedTransitionConstraints);
			transitionClause = newUnsatisfiableSet.get(indexTransitionConstraint);

			if (indexTransitionConstraint >= 0) {

				// Se quita de la lista de unsatisfiable constraint las
				// constraints que esten por encima del index de la transition
				// constraint
				List<IntBooleanExpression> newUnsatisfiableSetTemp = new ArrayList<IntBooleanExpression>();
				newUnsatisfiableSetTemp.addAll(newUnsatisfiableSet.subList(0, indexTransitionConstraint));

				// Se ajusta el nuevo unsatisfiable sets
				newUnsatisfiableSet.clear();

				newUnsatisfiableSet.addAll(newUnsatisfiableSetTemp);

				// Se inserta la transition constraint en el primer elemento
				newUnsatisfiableSet.add(0, transitionClause);
			} else {
				throw new FunctionalException("Verify dicothomic approach to identify defect corrections");
			}
			identifiedTransitionConstraints = identifiedTransitionConstraints + 1;
			unsatisfiableSize = newUnsatisfiableSet.size();
		}

		// We have to determine if the last constraint belongs to the MUC
		boolean isSatisfiable = hasSolution(newUnsatisfiableSet.subList(1, newUnsatisfiableSet.size()),
				fixedDependenciesList, defectToAnalyze);
		if (!isSatisfiable) {
			return newUnsatisfiableSet.subList(1, newUnsatisfiableSet.size());
		} else {
			return newUnsatisfiableSet;
		}

	}

	/**
	 *
	 * Reduce an unsatisfiable set into a MUS using the dichotomic approch.
	 * 
	 * @param expressionsToTest
	 * @param modelToTest
	 * @param fixedExpressions
	 * @param unsatisfiableSets
	 * @param blockedConstraints
	 * @param k
	 * @param defect
	 * @return
	 * @throws FunctionalException
	 */
	private List<IntBooleanExpression> blockConstraints(List<IntBooleanExpression> expressionsToTest,
			HlclProgram modelToTest, List<IntBooleanExpression> fixedExpressions,
			List<List<IntBooleanExpression>> unsatisfiableSets, List<List<IntBooleanExpression>> blockedConstraints,
			int k, Defect defect) throws FunctionalException {

		boolean addedColllection = Boolean.FALSE;
		List<IntBooleanExpression> newUnsatisfiableSet = new ArrayList<IntBooleanExpression>();
		List<IntBooleanExpression> unsatisifableSetCopy = new ArrayList<IntBooleanExpression>();
		List<IntBooleanExpression> modelToTestCopy = new ArrayList<IntBooleanExpression>();
		List<IntBooleanExpression> newUnsatisfiableSetComplement = new ArrayList<IntBooleanExpression>();

		// Guardará la copia que queda al final de cláusulas insatisfacibles (
		// sin el defecto a analizar)
		unsatisifableSetCopy.addAll(expressionsToTest);

		// Se trata de reducir el set unsatisfiable a ser un MUS
		// usando el enfoque dicotomico
		System.out.println("Reducing  unsatisfiable set ...");
		newUnsatisfiableSet = reduceUnsatisfiableSet(unsatisifableSetCopy, fixedExpressions, defect);

		if (newUnsatisfiableSet.isEmpty()) {
			throw new FunctionalException(
					"Error with the dicothomic approach.Please verify that fixed restrictions do not conflict with verification constraints");
		}

		// Se adiciona a la colección general de clausulas
		// unsatisfiables
		addedColllection = addUnsatisfiableConstraints(unsatisfiableSets, newUnsatisfiableSet);

		if (addedColllection) {
			// Se obtiene copia de la lista de dependencias
			// original, para
			// obtener el complemento correctamente. Sino como
			// el
			// paso es por referencia se afecta la colección
			// original
			modelToTestCopy.clear();
			modelToTestCopy.addAll(modelToTest);
			newUnsatisfiableSetComplement.clear();
			newUnsatisfiableSetComplement.addAll(SetUtil.difference(modelToTestCopy, newUnsatisfiableSet));

			// Se adiciona al conjunto de cláusulas
			// insatisfacibles
			// completo
			blockedConstraints = addConstraintsToBlock(blockedConstraints, newUnsatisfiableSetComplement, k);
		}

		// System.out.println("Conjunto de restricciones a bloquear: "
		// + blockedConstraints.size());
		return newUnsatisfiableSetComplement;
	}

	/**
	 * Adiciona a la collección de sets los sets insatisfacibles. Verificando
	 * que no existan subsets
	 * 
	 * @param blockedSets
	 * @param setToBlock
	 * @return
	 */
	private List<List<IntBooleanExpression>> addConstraintsToBlock(List<List<IntBooleanExpression>> blockedSets,
			List<IntBooleanExpression> setToBlock, int minimalSize) {

		// Se adiciona la cláusula y luego se eliminan los subsets que existan
		// dentro de la lista de cláusulas a bloquear, pq son innecesarios

		if (setToBlock != null && !setToBlock.isEmpty()) {
			// Si la cláusula a bloquear es mayor al tamaño de las cláusulas que
			// se están analizando se adiciona. Sino no, pues ya se habría
			// analizado esa coleccion
			if (blockedSets != null) {
				if (setToBlock.size() >= minimalSize) {
					if (!SetUtil.verifyCollectionSetContainsSet(setToBlock, blockedSets)) {
						blockedSets.add(setToBlock);
						SetUtil.maintainNoSubsets(blockedSets);
					} else {
						System.out.println("Set a bloquear ya existía");
					}

				} else {
					System.out.println("No cumple el tamaño mínimo el set a bloquear");
				}
			}
		}

		return blockedSets;

	}

	/**
	 * Adiciona a la collección de sets las restricciones insatisfacibles.
	 * Verificando que no existan supersets
	 * 
	 * @param unsatisfiableCollectionOfSets
	 * @param clausesToAdd
	 * @return
	 */
	private boolean addUnsatisfiableConstraints(List<List<IntBooleanExpression>> unsatisfiableCollectionOfSets,
			List<IntBooleanExpression> clausesToAdd) {

		// Se adiciona la cláusula y luego se eliminan los supersets que existan
		// dentro de la lista de cláusulas a bloquear, pq son innecesarios
		boolean addedCollection = false;
		if (clausesToAdd != null && !clausesToAdd.isEmpty()) {
			if (unsatisfiableCollectionOfSets != null) {
				if (!SetUtil.verifyCollectionSetContainsSet(clausesToAdd, unsatisfiableCollectionOfSets)) {

					unsatisfiableCollectionOfSets.add(clausesToAdd);
					addedCollection = true;
				} else {
					System.out.println("Unsatisfiable set ya existía");
				}
			}
		}

		return addedCollection;

	}

	public Diagnosis analyzeCausesOneDefect(Defect defectToAnalyze,
			Map<Long, IntBooleanExpression> modelDependenciesList,
			Map<Long, IntBooleanExpression> fixedDependenciesList, DefectAnalyzerModeEnum defectAnalyzerMode)
					throws FunctionalException {
		System.out
				.println("Analyzed defect: " + defectToAnalyze.getDefectType().name() + " " + defectToAnalyze.getId());
		long startTime = System.currentTimeMillis();

		// Se identifican los MCSes y los MUSes
		if (defectAnalyzerMode != null) {
			/*
			 * Diagnosis diagnostic = identifyMCSandMUSes(defectAnalyzerMode,
			 * modelDependenciesList, fixedDependenciesList, prologTempPath,
			 * defectToAnalyze);
			 */
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(" Analysis time: " + totalTime);
			return null;
		} else {
			throw new FunctionalException("Correction set type must be initialized");
		}

	}

	public void printResults(Diagnosis diagnostic) {
		// 3. PRINT RESULTS
		System.out.println("_________________ RESULTS____________________");
		System.out.println("_________________ MCS____________________");
		diagnostic.printCorrections();

		if (diagnostic.getCauses()!=null && !diagnostic.getCauses().isEmpty()){
			System.out.println("_________________ CAUSES____________________");
			diagnostic.printCauses();
		}

	}

	@Override
	public Diagnosis getCauCos(Defect defect, HlclProgram model, HlclProgram fixedConstraints,
			DefectAnalyzerModeEnum mode) throws FunctionalException {
		long startTime = System.nanoTime();
		if (parentComponent != null) {
			progressMonitor = new ProgressMonitor(parentComponent, progressDisplay, "", 0, 100);
			progressMonitor.setMillisToDecideToPopup(5);
			progressMonitor.setMillisToPopup(5);
			progressMonitor.setProgress(0);
		}
		Diagnosis diagnosis = new Diagnosis(defect);
		List<List<IntBooleanExpression>> unsatisfiableSets = new ArrayList<List<IntBooleanExpression>>();

		// To avoid null pointer exceptions
		if (fixedConstraints == null) {
			fixedConstraints = new HlclProgram();
		}

		if (!hasSolution(model, fixedConstraints, defect)) {

			// Corrections
			List<CauCos> corrections = getCorrections(defect, model, fixedConstraints, unsatisfiableSets, mode);
			diagnosis.setCorrections(corrections);
			long endTime = System.nanoTime();
			long totalTime = endTime - startTime;
			diagnosis.setCorrectionsProcessingTime(totalTime);
			System.out.println(" Correction time: " + totalTime);

			// Causes
			if (mode.equals(DefectAnalyzerModeEnum.CAUSES_CORRECTIONS)) {
				startTime = System.nanoTime();

				List<CauCos> causes = getCauses(defect, fixedConstraints, corrections, unsatisfiableSets, mode);
				diagnosis.setCauses(causes);
				endTime = System.nanoTime();
				totalTime = endTime - startTime;
				diagnosis.setCausesProcessingTime(totalTime);
				System.out.println(" Causes time: " + totalTime);
			}
			if (parentComponent != null) {
				progressMonitor.setProgress(100);
			}
			return diagnosis;
		} else {
			// Solo se identifican causas y correcciones si el modelo es
			// irresoluble
			if (parentComponent != null) {
				progressMonitor.setProgress(100);
			}
			return diagnosis;
		}
	}

	@Override
	public List<CauCos> getCorrections(Defect defect, HlclProgram model, HlclProgram fixedConstraints,
			DefectAnalyzerModeEnum mode) throws FunctionalException {
		// La lista vacía se envía para guardar los conjuntos irresolubles. Esto
		// es importante cuando se hallan causas y correcciones juntas
		List<CauCos> corrections = getCorrections(defect, model, fixedConstraints,
				new ArrayList<List<IntBooleanExpression>>(), mode);
		return corrections;
	}

	private List<CauCos> getCorrections(Defect defect, HlclProgram model, HlclProgram fixedConstraints,
			List<List<IntBooleanExpression>> unsatisfiableSets, DefectAnalyzerModeEnum mode)
					throws FunctionalException {
		long i = 0;

		if (progressMonitor != null) {
			progressMonitor.setProgress(1);
		}
		if (defect instanceof Redundancy) {
			// Se quita de la lista la expresión que se considera
			// redundante para que pueda ponerse su instrucción de verificación.
			// Si ambas se dejan juntas se estaría generando una contradicción
			// en el que la solución es quitar la misma restricción redundante y
			// eso
			// no tiene sentido en este caso
			HlclProgram modelCopy = new HlclProgram();
			for (IntBooleanExpression expression : model) {
				if (!expression.equals(defect.getVerificationExpression())) {
					modelCopy.add(expression);
				}
				i += 1 * 50.0 / model.size();

				if (progressMonitor != null) {
					progressMonitor.setProgress((int) i);
				}
			}

		}

		List<CauCos> corrections = new ArrayList<CauCos>();
		List<List<IntBooleanExpression>> allMCSes = getMCSes(defect, model, unsatisfiableSets, fixedConstraints, mode);

		// Se crean tantas correcciones como MCSes se hubieran identificado
		for (List<IntBooleanExpression> MCS : allMCSes) {
			corrections.add(new CauCos(MCS));
			i += 1 * 50.0 / allMCSes.size();

			if (progressMonitor != null) {
				progressMonitor.setProgress((int) i);
			}
		}

		return corrections;

	}

	@Override
	public DefectAnalyzerResultDTO getCauCos(List<Defect> defects, HlclProgram model, HlclProgram fixedConstraints,
			DefectAnalyzerModeEnum mode) throws FunctionalException {

		long startTime = System.nanoTime();
		DefectAnalyzerResultDTO caucosResult = new DefectAnalyzerResultDTO();
		caucosResult.setModel(model);
		caucosResult.setFixedConstraints(fixedConstraints);
		List<Diagnosis> allDiagnosis = new ArrayList<Diagnosis>();
		for (Defect defect : defects) {
			Diagnosis diagnosis = new Diagnosis();
			diagnosis = getCauCos(defect, model, fixedConstraints, mode);
			allDiagnosis.add(diagnosis);

		}
		caucosResult.setAllDiagnosis(allDiagnosis);

		VariabilityModelCausesCorrectionsSorter sorter = new VariabilityModelCausesCorrectionsSorter();
		long classificationStartTime = 0;
		long classificationEndTime = 0;
		long classificationTotalTime = 0;
		// Se invoca al clasificador de causas y correcciones
		if (mode.equals(DefectAnalyzerModeEnum.CAUSES_CORRECTIONS)) {

			classificationStartTime = System.nanoTime();
			ClassifiedElement classifiedCauses = sorter.classifyDiagnosis(allDiagnosis, ClassificationTypeEnum.CAUSES);
			classificationEndTime = System.nanoTime();
			classificationTotalTime = classificationEndTime - classificationStartTime;
			caucosResult.setCausesClassificationTime(classificationTotalTime);
			caucosResult.setClassifiedCauses(classifiedCauses);
		}
		classificationStartTime = System.nanoTime();
		ClassifiedElement classifiedCorrections = sorter.classifyDiagnosis(allDiagnosis,
				ClassificationTypeEnum.CORRECTIONS);
		classificationEndTime = System.nanoTime();
		classificationTotalTime = classificationEndTime - classificationStartTime;
		caucosResult.setCorrectionsClassificationTime(classificationTotalTime);
		caucosResult.setClassifiedCorrections(classifiedCorrections);

		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;

		caucosResult.setTotalTime(totalTime);
		return caucosResult;
	}

	@Override
	public List<Diagnosis> getCorrections(List<Defect> defects, HlclProgram model, HlclProgram fixedConstraints,
			DefectAnalyzerModeEnum mode) throws FunctionalException {

		List<Diagnosis> allDiagnosis = new ArrayList<Diagnosis>();
		for (Defect defect : defects) {
			Diagnosis diagnosis = new Diagnosis(defect);
			long startTime = System.nanoTime();
			List<CauCos> corrections = getCorrections(defect, model, fixedConstraints,
					new ArrayList<List<IntBooleanExpression>>(), mode);
			diagnosis.setCorrections(corrections);
			long endTime = System.nanoTime();
			long totalTime = endTime - startTime;
			diagnosis.setCorrectionsProcessingTime(totalTime);
			allDiagnosis.add(diagnosis);

		}
		return allDiagnosis;
	}

}