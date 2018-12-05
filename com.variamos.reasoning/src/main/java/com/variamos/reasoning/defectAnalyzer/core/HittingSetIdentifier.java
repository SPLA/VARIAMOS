package com.variamos.reasoning.defectAnalyzer.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.variamos.common.core.utilities.CollectionsSizeComparator;
import com.variamos.common.core.utilities.SetUtil;


/**
 * Contiene los métodos relacionados con la identificación de los MUSes
 * @author LuFe
 *
 */

public class HittingSetIdentifier {

	
	/*
	 * Implementa la lógica propuesta en Lifton(2009) pero se modifica pq el
	 * algoritmo original omitía algunos subconjuntos
	 */

	public static <E> void allMUSes(List<List<E>> minimalCorrectionSubsets,
			List<E> actualHittingSet, List<List<E>> hittingSets) {

		// Adiciona al MUS actual los conjutos de la lista que tengan tamaño
		// 1.
		// Estos siempre tienen que ir y permite ganar en velocidad. Elimina
		// luego estos conjuntos de la lista, y si hay mas conjuntos que
		// tienene
		// los elementos de los conjuntos singleton se quitan
		propagateSingletons(minimalCorrectionSubsets, actualHittingSet);

		if (minimalCorrectionSubsets.isEmpty()) {
			// Adicionar MUS a la list
			hittingSets.add(actualHittingSet);
			// Elimina los supersets que existan hata ahora en la lista de
			// hitting sets
			SetUtil.maintainNoSupersets(hittingSets);
			return;
		} else {

			// Unión de elementos que formarán los hitting sets
			Collection<E> allClausesMCSes = SetUtil
					.getUnionCollectionOfSets(minimalCorrectionSubsets);
			boolean stop = Boolean.FALSE;
			while (!stop) {

				E clause = searchMoreRelatedElement(allClausesMCSes,
						minimalCorrectionSubsets);

				if (clause == null) {
					stop = Boolean.TRUE;
				} else {
					// Almacena el historial de nodos visitados para evitar
					// visitar
					// nodos repetidos
					List<E> newMUS = new ArrayList<E>();
					newMUS.addAll(actualHittingSet);
					newMUS.add(clause);

					// Se verifica que ese MUS no sea superset de los MUSes
					// existentes
					boolean existMUS = SetUtil
							.<E> verifySetIsSuperSetOfCollectionSets(newMUS,
									hittingSets);

					if (!existMUS) {
						List<List<E>> newMCSes = new ArrayList<List<E>>();
						newMCSes = cloneSet(minimalCorrectionSubsets);
						// Se quita el MCS seleccionado de la lista de
						// MCSes,
						// los otros conjuntos que tengan las clausulas del
						// MC
						// seleccionad
						propagateChoiceSinEliminarClausulas(newMCSes, clause);
						// LLamado recursivo con los elementos modificado
						allMUSes(newMCSes, newMUS, hittingSets);
					}
				}
			}

		}
		return;

	}

	
	
	@SuppressWarnings("unchecked")
	private static <T> void propagateSingletons(
			List<List<T>> minimalCorrectionSubsets, List<T> actualMUS) {

		// List<List<T>> newMCSes = new ArrayList<List<T>>();
		Collection<List<T>> minimalCorrectionSubsetsToRemove = new ArrayList<List<T>>();

		// Se ordenan los conjuntos por tamaño de menor mayor
		Collections.sort(minimalCorrectionSubsets,
				new CollectionsSizeComparator());

		Set<T> clausesToRemove = new HashSet<T>();

		for (List<T> list : minimalCorrectionSubsets) {
			if (list.size() == 1) {
				actualMUS.addAll(list);
				minimalCorrectionSubsetsToRemove.add(list);
				// Se eliminan de los otros conjuntos las cláusulas que contiene
				// el conjunto con un solo elemento
				clausesToRemove.addAll(list);
			} else {
				// Como esta ordenado por tamaño, si la lista no es de tamaño 1
				// es pq no hay más de ese tamaño y se puede terminar el ciclo
				break;
			}
		}

		for (T clause : clausesToRemove) {
			for (List<T> testMCS : minimalCorrectionSubsets) {
				if (testMCS.contains(clause)) {
					// preventing any of the other clauses in thisMCS from
					// being added in later iterations (Lifton,2009)
					testMCS.remove(clause);
				}
				if (testMCS.isEmpty()) {
					// Si la lista es vacía se elimina del la lista de MCSes
					minimalCorrectionSubsetsToRemove.add(testMCS);
				}

			}
		}
		// Se quitan los sets que estan en la lista de sets a remover de la
		// copia
		minimalCorrectionSubsets.removeAll(minimalCorrectionSubsetsToRemove);

		// La lista modificada de minimalCorrectionsSUbsets y el actualMUS sale
		// de aqui modificado pq los parámetros fueron pasados por referencia

	}

	

	/**
	 * Verifica que no existan superconjuntos en los hitting sets generados para
	 * garantizar que cada hitting set es mínimo
	 * 
	 * @param minimalCorrectionSubsets
	 * @param actualMUS
	 * @param hittingSubsets
	 * @return
	 */
	public static <E> List<List<E>> filterMUSes(
			List<List<E>> minimalCorrectionSubsets, List<E> actualMUS,
			List<List<E>> hittingSubsets) {

		// En caso de que la lista de entrada tenga supersets se eliminan (
		// Para la formación de un hitting set esto no debería pasar, pero se
		// hace por seguridad)
		List<List<E>> collectionOfSetCopy = cloneSet(minimalCorrectionSubsets);
		collectionOfSetCopy = SetUtil.maintainNoSupersets(collectionOfSetCopy);
		allMUSes(collectionOfSetCopy, actualMUS, hittingSubsets);

		// Se eliminan los supersets de los hitting sets. Esto para garantizar
		// que los hittings sets identificados sean mínimo
		// Un hitting set es mínimo si sus subconjuntos propios no son hitting
		// sets, por lo tanto, no pueden haber supersets entre los conjuntos
		// para cumplir con esta propiedad
		SetUtil.maintainNoSupersets(hittingSubsets);
		return hittingSubsets;

	}

	private static <E> List<List<E>> cloneSet(List<List<E>> collectionToClone) {
		List<List<E>> clone = new ArrayList<List<E>>();

		for (List<E> collectionToCloneElement : collectionToClone) {
			List<E> cloneElementList = new ArrayList<E>();
			cloneElementList.addAll(collectionToCloneElement);
			clone.add(cloneElementList);
		}

		return clone;
	}


	



	/**
	 * Selecciona la siguiente cláusula con la cuál probar la generación del MUS
	 * 
	 * @param collectionOfSets
	 * @param blockedClauses
	 * @return
	 */
	@SuppressWarnings("unused")
	private static <E> E selectRemainingClause(List<List<E>> collectionOfSets,
			Collection<E> blockedClauses) {

		Collection<E> collectionOfSetElements = SetUtil
				.getUnionCollectionOfSets(collectionOfSets);
		List<E> set = new ArrayList<E>();
		// Se vuelve el set una lista
		for (E setToList : collectionOfSetElements) {
			if (!blockedClauses.contains(setToList)) {
				set.add(setToList);
			}
		}

		// Se retorna el primer elemento si la lista no es vacía
		if (!set.isEmpty()) {
			return set.get(0);
		}
		return null;

	}

	
	/**
	 * Implementa la lógica propuesta en Lifton(2009). Pero modifica la parte en
	 * la que elimina las clausulas del MCS seleccionado de otros conjuntos pq
	 * estaba descartando hitting set mínimos
	 * 
	 * @param mCSes
	 * @param clauseToAdd
	 * @param MCSwithClauseToAdd
	 * @return
	 */
	private static <E> void propagateChoiceSinEliminarClausulas(
			List<List<E>> mCSes, E clauseToAdd) {

		Collection<List<E>> minimalCorrectionSubsetsToRemove = new ArrayList<List<E>>();
		if (!mCSes.isEmpty()) {
			// Se eliminan los subconjuntos que tienen la cláusula que se
			// seleccionó
			for (List<E> testMCS : mCSes) {
				// Remove any other MCSes hit by choosing thisClause, because
				// they have now been satisfied by the partial
				// solution(Lifton,2009)
				if (testMCS.contains(clauseToAdd)) {
					minimalCorrectionSubsetsToRemove.add(testMCS);
					// Se deben eliminar las cláusulas de este MCS de los otros
					// MCS pq no son cláusulas válidas
					// clausesToRemove.addAll(testMCS);
				}
			}

			// Se eliminan los elementos
			if (!minimalCorrectionSubsetsToRemove.isEmpty()) {
				SetUtil.removeSets(mCSes, minimalCorrectionSubsetsToRemove);
			}

		}

	}

	private static <E> E searchMoreRelatedElement(Collection<E> elements,
			List<List<E>> collectionOfSets) {

		if (!elements.isEmpty()) {

			// Se guarda la colección en una lista
			List<E> elementsToCount = new ArrayList<E>();
			for (E element : elements) {
				elementsToCount.add(element);
			}

			if (elementsToCount.size() == 1) {
				elements.clear();
				return elementsToCount.get(0);
			}

			// Vector que almacena el número de ocurrencias que tiene cada
			// elemento del set elements en la collectionOfSets
			int recordCount[] = new int[elements.size()];

			for (int i = 0; i < elementsToCount.size(); i++) {
				int count = 0;
				for (List<E> list : collectionOfSets) {
					E element = elementsToCount.get(i);
					if (list.contains(element)) {
						count++;
					}

				}
				recordCount[i] = count;
			}

			// Se verifica cuál es la posición del array que tiene el número más
			// grande
			int position = 0;
			for (int i = 1; i < recordCount.length; i++) {
				if (recordCount[i] > recordCount[position]) {
					position = i;
				}
			}

			// Se retorna la dependencia de la lista que este en la posición que
			// tenía el mayor número de ocurrencias y se elimina de la lista
			// original para que avance el ciclo
			elements.remove(elementsToCount.get(position));
			return elementsToCount.get(position);
		} else {
			return null;
		}
	}

}
