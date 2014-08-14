package com.variamos.defectAnalyzer.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cfm.productline.Constraint;

public class PowerSetUtil {

	public static List<List<String>> formarConjuntoPotenciaString(
			List<String> conjuntoAnalizar) {

		// Si la lista de elementos solo le queda un elemento en ese caso
		// retorna la lista que con toda seguridad solo tiene un elemento
		if (conjuntoAnalizar.size() == 1) {
			List<List<String>> listaResultado = new ArrayList<List<String>>();
			listaResultado.add(conjuntoAnalizar);
			return listaResultado;
		} else {

			int ultimaPosicion = (conjuntoAnalizar.size() - 1);
			// Se obtiene el último elemento de los elementos del conjunto. Este
			// elemento luego será adicionado al conjunto potencia que tiene el
			// resultado
			String elementoAnalizar = conjuntoAnalizar.get(ultimaPosicion);
			// Se quita este elemento del conjunto y nuevamente se llama la
			// función recursiva, así el primer elemento que se analizar es el
			// primer elemento de la lista que se envía
			conjuntoAnalizar.remove(ultimaPosicion);

			// Esta lista de listas almacenará el conjunto resultado de la
			// recursión, y
			// será el conjunto que se retorne al final del método con el
			// conjunto potencia
			List<List<String>> conjuntoPotencia = formarConjuntoPotenciaString(conjuntoAnalizar);

			// Conjunto de conjuntos que se va a incrementando por cada
			// iteración (iniciando siempre vacío) para construir con el
			// elemento que se esta analizando más los anteriores, los posibles
			// futuros conjuntos. Por ejemplo, si se tiene ya como resultado de
			// una recursión el conjuntoPotencia {{A}} y se esta analizando el
			// elemento B, el nuevo conjunto temporal es {{A,B}}
			List<List<String>> conjuntoTemporal = new ArrayList<List<String>>();
			for (List<String> elementosConjunto : conjuntoPotencia) {
				List<String> nuevoConjuntoDelConjuntoPotencia = new ArrayList<String>();
				nuevoConjuntoDelConjuntoPotencia.addAll(elementosConjunto);
				nuevoConjuntoDelConjuntoPotencia.add(elementoAnalizar);
				conjuntoTemporal.add(nuevoConjuntoDelConjuntoPotencia);

			}
			// Se adiciona al conjunto potencia, un conjunto de un solo elemento
			// con el elemento que se está analizando en esta pasada recursiva
			// Por ejeemplo si el conjunto potencia es {{A}} , y se esta
			// analizando el elemento B, se crea un conjunto de un solo elemento
			// {B} y luego se adiciona al conjunto potencia quedando este como
			// {{A}, {B}},
			List<String> conjuntoElementoAnalizado = new ArrayList<String>();
			conjuntoElementoAnalizado.add(elementoAnalizar);
			conjuntoPotencia.add(conjuntoElementoAnalizado);

			// Se adiciona el resultado del conjunto temporal a lo que se
			// tuviera del conjunto potencia, por ejemplo si el conjunto
			// potencia tiene el elemento {{A}, {B}}, y el conjunto temporal es
			// {{A,B}}, el nuevo conjunto potencia es {{A}, {B}, {A,B}}
			conjuntoPotencia.addAll(conjuntoTemporal);
			return conjuntoPotencia;
		}

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
	 * @param prefix
	 * @param elements
	 * @param resultList
	 * @param r
	 * @param notAllowedListSuperSets
	 * @param notAllowedCollectionOfSets
	 * @return
	 */
	public static <E> List<List<E>> externalDoCombinations(List<E> prefix,
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

	/**
	 * Construye las combinaciones de tamaño r de elementos que no son
	 * superconjuntos de los conjuntos bloqueados, ni superconjuntos de los
	 * conjuntos no permitidos
	 * 
	 * @param prefix
	 * @param inputSet
	 * @param listaResultado
	 * @param r
	 * @param notAllowedListSuperSets
	 * @param notAllowedCollectionOfSets
	 * @return
	 */
	public static <E> List<List<E>> doCombinationsWithoutblockedsubsets(
			List<E> prefix, List<E> inputSet, List<List<E>> listaResultado,
			int r, List<List<E>> notAllowedListSuperSets,
			List<List<E>> notAllowedCollectionOfSets) {

		List<List<E>> possibleMCSToVerify = new ArrayList<List<E>>();
		List<E> complementBloquedSet = new ArrayList<E>();
		for (List<E> bloquedSet : notAllowedCollectionOfSets) {
			complementBloquedSet.clear();
			complementBloquedSet.addAll(inputSet);
			complementBloquedSet.removeAll(bloquedSet);

			List<List<E>> partialMCS = new ArrayList<List<E>>();
			partialMCS = doCombinations(new ArrayList<E>(),
					complementBloquedSet, partialMCS, notAllowedListSuperSets,
					r);

			if (!partialMCS.isEmpty()) {
				possibleMCSToVerify.addAll(partialMCS);
			}

			// Se itera desde i=1 hasta i=k-1
			for (int i = 1; i < r; i++) {
				// La cantidad de elementos de la combinatoria que se toman del
				// conjunto no bloqueado
				int j = r - i;
				List<List<E>> conjuntoElementosNoBloqueados = new ArrayList<List<E>>();
				conjuntoElementosNoBloqueados = doCombinations(
						new ArrayList<E>(), complementBloquedSet,
						conjuntoElementosNoBloqueados, notAllowedListSuperSets,
						j);

				// Se construye el conjunto con los elementos bloqueados de
				// tamaño i
				List<List<E>> conjuntoElementosSiBloqueados = new ArrayList<List<E>>();
				conjuntoElementosSiBloqueados = doCombinations(
						new ArrayList<E>(), bloquedSet,
						conjuntoElementosSiBloqueados, notAllowedListSuperSets,
						i);

				// Por cada elemento del conjuno de elementos no bloqueados se
				// adiciona uno del elemento del conjunto de si bloqueados
				if (!conjuntoElementosSiBloqueados.isEmpty()
						&& !conjuntoElementosNoBloqueados.isEmpty()) {
					for (List<E> noBloquedSet : conjuntoElementosNoBloqueados) {

						for (List<E> bloquedSet2 : conjuntoElementosSiBloqueados) {
							List<E> possibleMCS = new ArrayList<E>();
							possibleMCS.addAll(noBloquedSet);
							possibleMCS.addAll(bloquedSet2);
							if (!SetUtil.verifySetIsSuperSetOfCollectionSets(
									possibleMCS, notAllowedListSuperSets)
									&& !SetUtil
											.<E> verifySetIsSubSetOfCollectionSets(
													possibleMCS,
													notAllowedCollectionOfSets)
									&& !SetUtil.verifyExistSetInSetofSets(
											possibleMCS, possibleMCSToVerify)) {
								possibleMCSToVerify.add(possibleMCS);
							}
						}
					}
				}

			}

		}
		return possibleMCSToVerify;

	}

	/**
	 * 
	 * Para probarlo: System.out.println("PowerSet" +
	 * constraintPowerSet.toString()); int N = 4; int k = 2; String elements =
	 * "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	 * 
	 * @param prefix
	 * @param elements
	 * @param r
	 */
	public static void generate(String prefix, String elements, int r) {
		if (r == 0) {
			System.out.println(prefix);
			return;
		}
		for (int i = 0; i < elements.length(); i++)
			generate(prefix + elements.charAt(i), elements.substring(i + 1),
					r - 1);
	}

	/**
	 * Construye el conjunto potencia de una lista dada sin incluir el conjunto
	 * vacío. Si se tiene el conjunto {A,B,C}, el orden en el que se formará
	 * este conjunto será: {{A},{B},{AB},{C},{AC},{BC},{ABC}}
	 * 
	 * @param inputSet
	 * @return Conjunto potencia sin el conjunto vacío.
	 */
	public static <E> List<List<E>> makePowerSet(List<E> inputSet) {

		// Si la lista de elementos solo le queda un elemento en ese caso
		// retorna la lista que con toda seguridad solo tiene un elemento
		if (inputSet.size() == 1) {
			List<List<E>> listaResultado = new ArrayList<List<E>>();
			listaResultado.add(inputSet);
			return listaResultado;
		} else if (inputSet.size() > 1) {

			int ultimaPosicion = (inputSet.size() - 1);
			// Se obtiene el último elemento de los elementos del conjunto. Este
			// elemento luego será adicionado al conjunto potencia que tiene el
			// resultado
			E elementoAnalizar = inputSet.get(ultimaPosicion);
			// Se quita este elemento del conjunto y nuevamente se llama la
			// función recursiva, así el primer elemento que se analizar es el
			// primer elemento de la lista que se envía
			inputSet.remove(ultimaPosicion);

			// Esta lista de listas almacenará el conjunto resultado de la
			// recursión, y
			// será el conjunto que se retorne al final del método con el
			// conjunto potencia
			List<List<E>> conjuntoPotencia = makePowerSet(inputSet);

			// Conjunto de conjuntos que se va a incrementando por cada
			// iteración (iniciando siempre vacío) para construir con el
			// elemento que se esta analizando más los anteriores, los posibles
			// futuros conjuntos. Por ejemplo, si se tiene ya como resultado de
			// una recursión el conjuntoPotencia {{A}} y se esta analizando el
			// elemento B, el nuevo conjunto temporal es {{A,B}}
			List<List<E>> conjuntoTemporal = new ArrayList<List<E>>();
			for (List<E> elementosConjunto : conjuntoPotencia) {
				List<E> nuevoConjuntoDelConjuntoPotencia = new ArrayList<E>();
				nuevoConjuntoDelConjuntoPotencia.addAll(elementosConjunto);
				nuevoConjuntoDelConjuntoPotencia.add(elementoAnalizar);
				conjuntoTemporal.add(nuevoConjuntoDelConjuntoPotencia);

			}
			// Se adiciona al conjunto potencia, un conjunto de un solo elemento
			// con el elemento que se está analizando en esta pasada recursiva
			// Por ejeemplo si el conjunto potencia es {{A}} , y se esta
			// analizando el elemento B, se crea un conjunto de un solo elemento
			// {B} y luego se adiciona al conjunto potencia quedando este como
			// {{A}, {B}},
			List<E> conjuntoElementoAnalizado = new ArrayList<E>();
			conjuntoElementoAnalizado.add(elementoAnalizar);
			conjuntoPotencia.add(conjuntoElementoAnalizado);

			// Se adiciona el resultado del conjunto temporal a lo que se
			// tuviera del conjunto potencia, por ejemplo si el conjunto
			// potencia tiene el elemento {{A}, {B}}, y el conjunto temporal es
			// {{A,B}}, el nuevo conjunto potencia es {{A}, {B}, {A,B}}
			conjuntoPotencia.addAll(conjuntoTemporal);
			return conjuntoPotencia;
		}
		return new ArrayList<List<E>>();

	}

	/**
	 * Elimina del power set los elementos que contengan la mismas relaciones
	 * que ya dieron como resultado alguna solución, pues estos ya no se deben
	 * ser tenidos encuenta como posibles elementos de relajación, al mejorar la
	 * semántica del modelo y solucionar el defecto
	 */
	public List<Constraint> podarPowerSet(
			List<List<Constraint>> constraintPowerSet,
			List<Constraint> causeConstraint) {

		List<Constraint> constraintToPrune = new ArrayList<Constraint>();
		for (List<Constraint> relaxedConstraintList : constraintPowerSet) {
			boolean podarConstraint = true;
			uno: for (int i = 0; i < causeConstraint.size(); i++) {
				Constraint constraint = causeConstraint.get(i);
				if (!relaxedConstraintList.contains(constraint)) {
					podarConstraint = false;
					break uno;
				}
			}
			if (podarConstraint) {
				constraintToPrune.addAll(relaxedConstraintList);

			}
		}
		return constraintToPrune;
	}

}
