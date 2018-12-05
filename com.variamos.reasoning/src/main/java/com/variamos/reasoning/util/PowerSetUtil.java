package com.variamos.reasoning.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.variamos.common.core.utilities.SetUtil;

public class PowerSetUtil {


	/**
	 * Crea las combinaciones de k elementos de un conjunto de tamaño n
	 * 
	 * @param prefix
	 * @param elements
	 * @param resultList
	 * @param r
	 * @param notAllowedListSuperSets
	 *            : Cualquier superset de ese conjunto se bloquea
	 * @param notAllowedCollectionOfSets
	 *            : Cualquier set generado que este dentro de esta lista se
	 *            bloquea
	 * @return
	 */
	public static <E> List<List<E>> doCombinations(List<E> prefix,
			List<E> elements, List<List<E>> resultList, int r,
			List<List<E>> notAllowedListSuperSets,
			List<List<E>> notAllowedCollectionOfSets) {

		if (r == 0) {

			if ((notAllowedCollectionOfSets == null || !SetUtil
					.<E> verifySetIsSubSetOfCollectionSets(prefix,
							notAllowedCollectionOfSets))) {
				List<E> result = new ArrayList<E>();
				result.addAll(prefix);
				resultList.add(result);
			}
			return resultList;
		}

		for (int i = 0; i < elements.size(); i++) {
			List<E> prefix2 = new ArrayList<E>();
			prefix2.addAll(prefix);
			prefix2.add(elements.get(i));

			if ((notAllowedListSuperSets == null || !SetUtil
					.<E> verifySetIsSuperSetOfCollectionSets(prefix2,
							notAllowedListSuperSets))) {
				doCombinations(prefix2,
						elements.subList(i + 1, elements.size()), resultList,
						r - 1, notAllowedListSuperSets,
						notAllowedCollectionOfSets);

			}
		}
		return resultList;
	}

	/**
	 * Invoca el algoritmo que hace las combinaciones válidas.
	 * 
	 * Construye las combinaciones de tamaño r de elementos que no son
	 * superconjuntos de los conjuntos bloqueados, ni superconjuntos de los
	 * conjuntos no permitidos
	 * @param prefix
	 * @param elements
	 * @param resultList
	 * @param r
	 * @param notAllowedListSuperSets
	 * @param notAllowedCollectionOfSets
	 * @return
	 */
	public static <E> List<List<E>> calculateSets(List<E> prefix,
			List<E> elements, List<List<E>> resultList, int r,
			List<List<E>> notAllowedListSuperSets,
			List<List<E>> notAllowedCollectionOfSets) {

		for (int i = 0; i < elements.size(); i++) {
			List<E> prefix2 = new ArrayList<E>();
			prefix2.add(elements.get(i));
			List<List<E>> localResultList = new ArrayList<List<E>>();
			localResultList = doCombinations2(prefix2,
					elements.subList(i + 1, elements.size()), localResultList,
					r - 1, notAllowedListSuperSets, notAllowedCollectionOfSets);
			if (!localResultList.isEmpty()) {
				resultList.addAll(localResultList);
			}
		}
		return resultList;

	}

	/**
	 * Crea las combinaciones de k elementos de un conjunto de tamaño n
	 * 
	 * @param prefix
	 * @param elements
	 * @param resultList
	 * @param r
	 * @param notAllowedListSuperSets
	 *            : Cualquier superset de ese conjunto se bloquea
	 * @param notAllowedCollectionOfSets
	 *            : Cualquier set generado que este dentro de esta lista se
	 *            bloquea
	 * @return
	 */
	public static <E> List<List<E>> doCombinations2(List<E> prefix,
			List<E> elements, List<List<E>> resultList, int r,
			List<List<E>> notAllowedListSuperSets,
			List<List<E>> notAllowedCollectionOfSets) {

		Set<E> impliedElements = getImpliedElements(prefix,
				notAllowedCollectionOfSets);

		List<E> elementsToBlock = new ArrayList<E>();
		elementsToBlock.addAll(elements);
		elementsToBlock.retainAll(impliedElements);

		List<E> validElements = new ArrayList<E>();
		validElements.addAll(elements);
		validElements.removeAll(elementsToBlock);

		if (r == 0) {

			if ((notAllowedCollectionOfSets == null || !SetUtil
					.<E> verifySetIsSubSetOfCollectionSets(prefix,
							notAllowedCollectionOfSets))
					&& (notAllowedListSuperSets == null || !SetUtil
							.<E> verifySetIsSuperSetOfCollectionSets(prefix,
									notAllowedListSuperSets))) {
				List<E> result = new ArrayList<E>();
				result.addAll(prefix);
				resultList.add(result);
			}
			return resultList;
		}

		// Ciclo con los que si se pueden
		if (!validElements.isEmpty()) {
			List<E> prefix2 = new ArrayList<E>();
			prefix2.addAll(prefix);
			List<List<E>> resultListValids = new ArrayList<List<E>>();
			resultListValids = doCombinations(prefix2, validElements,
					resultListValids, notAllowedListSuperSets, r);
			// Si hay elementos válidos
			if (!resultListValids.isEmpty()) {
				resultList.addAll(resultListValids);
			}
		}
		// Ciclo con los que no se pueden ( debe ser menor al tamaño mínimo de r
		// en al menos 1)
		if (!elementsToBlock.isEmpty() && r > 1) {
			for (int j = 0; j < elementsToBlock.size(); j++) {
				List<E> prefix2 = new ArrayList<E>();
				prefix2.addAll(prefix);
				prefix2.add(elementsToBlock.get(j));
				List<List<E>> resultListValids = new ArrayList<List<E>>();
				// Se verifica si prefix es subset de los sets no permitidos
				if ((notAllowedListSuperSets == null || !SetUtil
						.<E> verifySetIsSuperSetOfCollectionSets(prefix2,
								notAllowedListSuperSets))) {

					// Se obtiene la posición del elemento a bloquear en la
					// lista de entrada
					int actualPosition = elements.indexOf(elementsToBlock
							.get(j));
					if (actualPosition != -1) {

						resultListValids = doCombinations2(
								prefix2,
								elements.subList(actualPosition + 1,
										elements.size()), resultListValids,
								r - 1, notAllowedListSuperSets,
								notAllowedCollectionOfSets);
						// Si hay elementos válidos
						if (!resultListValids.isEmpty()) {
							resultList.addAll(resultListValids);
						}
					} else {
						throw new RuntimeException(
								"Error in power set generation. Please check");
					}

				}
			}
		}
		return resultList;
	}

	private static <E> Set<E> getImpliedElements(List<E> prefix,
			List<List<E>> notAllowedCollectionOfSets) {

		int cont = 0;
		Set<E> impliedElements = new HashSet<E>();
		for (List<E> notAllowedList : notAllowedCollectionOfSets) {
			cont = 0;
			for (E prefixElement : prefix) {
				// Si el subconjunto a bloquear no tiene el elemento, entonces
				// se continúa con el siguiente
				if (!notAllowedList.contains(prefixElement)) {
					break;
				} else {
					cont++;
				}
			}
			if (cont == prefix.size()) {
				// Si se tienen los elementos
				impliedElements.addAll(notAllowedList);
			}

		}
		// Se quitan los elementos del prefix
		impliedElements.removeAll(prefix);
		return impliedElements;

	}

	public static <E> List<List<E>> doCombinations(List<E> prefix,
			List<E> elements, List<List<E>> resultList,
			List<List<E>> notAllowedListSuperSets, int r) {

		return doCombinations(prefix, elements, resultList, r,
				notAllowedListSuperSets, null);

	}

	public static <E> List<List<E>> doCombinations(List<E> prefix,
			List<E> elements, List<List<E>> listaResultado, int r) {

		return doCombinations(prefix, elements, listaResultado, r, null, null);

	}

	

}
