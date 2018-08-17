package com.variamos.common.core.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * This class implements the logic related with set operations
 * @author Luisa Rincon - lufe089@gmail.com
 * 
 */
public class SetUtil {

	
	/**Gets union of collection of sets
	 * @param Collection<sets>
	 * @return Union of sets without repetitions
	 */
	@SafeVarargs
	public static <E> Collection<E> union(Collection<E>... sets) {

		Set<E> union = new HashSet<E>();

		for (Collection<E> set : sets) {
			if (set != null) {
				// Add elements for each set
				union.addAll(set);
			}
		}

		return union;

	}

	/**Gets the intersection of 2 sets
	 * @param Set1 
	 * @param Set2
	 * @return Intersection of sets without repetitions
	 */

	public static <E> List<E> intersection(Collection<E> set1,
			Collection<E> set2) {

		List<E> setCopy = new ArrayList<E>();
		
		setCopy.addAll(set1);
		setCopy.retainAll(set2);

		return setCopy;
	}

	/**Gets complement of a set
	 * @param universalSet set of the universe
	 * @param setOfSets sets to remove from universe
	 * @return Complement of setOfSets (Universe - setOfSets)
	 */

	public static <E> List<E> complementOfSets(Collection<E> universalSet,
			List<List<E>> setOfSets) {

		List<E> universalSetCopy = new ArrayList<E>();
		universalSetCopy.addAll(universalSet);

		for (List<E> subSet : setOfSets) {
			universalSetCopy.removeAll(subSet);
		}

		return universalSetCopy;
	}

	/**Gets the difference between set1 and set2
	 * @param set1 
	 * @param set2
	 * @return Difference of set1 minus set2
	 */
	public static <E> Collection<E> difference(Collection<E> set1,
			Collection<E> set2) {
		List<E> differenceCopy = new ArrayList<E>();
		differenceCopy.addAll(set1);
		differenceCopy.removeAll(set2);
		return differenceCopy;

	}

	/**Removes elements from a set 
	 * @param set of elements to remove
	 * @param elementsToRemove from the set
	 * @return set without elementsToRemove (Set - elementsToRemove)
	 */
	
	public static <E extends Collection<E>> Collection<E> removeElementsOfList(
			Collection<E> set, Collection<E> elementsToRemove) {

		for (E element : elementsToRemove) {
			if (set.contains(element)) {
				set.remove(element);
			}
		}

		return set;

	}

	/**Removes sets of a collection of sets
	 * @param originalCollectionOfSets main collection fo sets. 
	 * @param setsToRemove group of sets to remove
	 * @return CollectionOfSets without setsToRemove (CollectionOfSets - setsToRemove) 
	 */
	public static <E> Collection<List<E>> removeSets(
			Collection<List<E>> originalCollectionOfSets,
			Collection<List<E>> setsToRemove) {

		for (List<E> mcsToRemove : setsToRemove) {
			originalCollectionOfSets.remove(mcsToRemove);
		}

		return originalCollectionOfSets;
	}



	/**Removes elements from a set
	 * @param set 
	 * @param obj object in set to remove
	 * @return set without object (set - object)
	 */
	public static <E extends Collection<E>, T> Collection<E> removeOneElementsOfList(
			Collection<E> set, T obj) {

		if (set.contains(obj)) {
			set.remove(obj);
		}

		return set;

	}


	/**
	 * Verify if a set exist in other set of sets(Collection of sets)
	 * @param setToVerify
	 * @param setOfSets collection of sets
	 * @return True exist the set, false otherwise
	 */
	public static <E> boolean verifyExistSetInSetofSets(List<E> setToVerify,
			List<List<E>> setOfSets) {

		if (setToVerify != null && !setToVerify.isEmpty() && setOfSets != null
				&& !setOfSets.isEmpty())
			for (Collection<E> set : setOfSets) {

				if (isEquals(set, setToVerify)) {
					return true;
				}

			}
		return false;

	}

	/**
	 * Verify if a set is subset of other set
	 * @param subSet
	 * @param superSet Set to verify if cantains subSet param
	 * @return True is subset, false otherwise
	 */
	public static <E> boolean isSubset(Collection<E> subSet,
			Collection<E> superSet) {
		int occurrenceCount = 0;
		if (subSet != null && superSet != null) {
			for (E subSetElement : subSet) {
				if (superSet.contains(subSetElement)) {
					occurrenceCount++;
				}
			}
			if (occurrenceCount == subSet.size()) {
				return true;
			}
		}

		return false;

	}

	/**Verifies if a set is equals to another set
	 * @param set1 
	 * @param set2
	 * @return true: Set1 has the same elements of false : otherwise
	 */
	public static <E> boolean isEquals(Collection<E> set1, Collection<E> set2) {

		if (set1.size() == set2.size()) {
			return isSubset(set1, set2);
		}

		return false;

	}

	
	/**
	 * Verify if a set is superset of other set
	 * @param superSet 
	 * @param subSet 
	 * @return true : if subset is in superSet, false : otherwise
	 */
	public static <E> boolean isSuperSet(Collection<E> superSet,
			Collection<E> subSet) {
		return isSubset(subSet, superSet);

	}

	//FLAG0 isn't verifyExistSetInSetofSets same? 

	/**Verify if a set is contained into a colecctions of sets 
	 * @param inputSet
	 * @param collectionOfSets
	 * @return True : if Set is contained in the collection set. False : Otherwise
	 */
	public static <E> boolean verifyCollectionSetContainsSet(List<E> inputSet,
			List<List<E>> collectionOfSets) {

		for (Collection<E> set : collectionOfSets) {
			boolean isEquals = isEquals(set, inputSet);
			if (isEquals) {
				return true;
			}
		}
		return false;
	}

	//FLAG1 With this logic returns true if inputSet contains at least 1 set of collections. 
	//error in name? (It should be verifySetIsSuperSetOfSetInCollectionSets)
	/**Verifies if  a set is superSet of collectionOfSets 
	 * @param inputSet
	 * @param collectionOfSets
	 * @return true:Set is superset of some set of collection of sets.
	 *         Otherwise Set is not superset of some set of collection of sets
	 * 		   
	 */
	public static <E> boolean verifySetIsSuperSetOfCollectionSets(
			List<E> inputSet, List<List<E>> collectionOfSets) {

		if (inputSet != null && !inputSet.isEmpty() && collectionOfSets != null
				&& !collectionOfSets.isEmpty()) {
			for (Collection<E> set : collectionOfSets) {
				boolean isSuperSet = isSuperSet(inputSet, set);
				if (isSuperSet) {
					return true;
				}
			}
		}
		return false;
	}

	//FLAG3 error in the name. 
	/**Verifies if an element is in a set of a collection of sets
	 * @param input 
	 * @param colecctionOfSets
	 * @return True : input element is in a subset of a collection of sets set False : otherwise
	 */
	public static <E> boolean verifyElementIsSubSetOfCollectionSets(E input,
			List<List<E>> collectionOfSets) {

		if (input != null && collectionOfSets != null) {
			for (Collection<E> set : collectionOfSets) {
				if (set.contains(input)) {
					return true;
				}
			}
		}
		return false;
	}
	
//FLAG2 error in the name (Should be verifySetIsSubSetOfSetInColecctionSets)
	/**Verifies if a set is sub set of a set in collection of sets
	 * @param inputSet 
	 * @param collectionOfSets
	 * @return True : If set is subSet of a collection of sets False : Otherwise 
	 */
	public static <E> boolean verifySetIsSubSetOfCollectionSets(
			List<E> inputSet, List<List<E>> collectionOfSets) {
		if (inputSet != null && collectionOfSets != null) {
			for (Collection<E> set : collectionOfSets) {
				boolean isSubset = isSubset(inputSet, set);
				if (isSubset) {
					return true;
				}
			}
		}
		return false;
	}


	/**Gets a super set whichs contains an input set
	 * @param inputSet 
	 * @param clauses collection of super sets.
	 * @return Set Of sets which contains inputSet param 
	 */
	public static <E> List<List<E>> getSuperSetsOfSet(List<E> inputSet,
			List<List<E>> clauses) {

		List<List<E>> superSetOfInputSet = new ArrayList<List<E>>();
		if (clauses != null && inputSet != null) {
			for (List<E> clause : clauses) {
				for (E element : inputSet) {
					if (clause.contains(element)) {
						superSetOfInputSet.add(clause);
						break;
					}
				}
			}
		}
		return superSetOfInputSet;
	}

	/**Gets the union of a collection of sets
	 * @param collectionOfSets
	 * @return union without repetitions of a collection of sets
	 */
	public static <E> Collection<E> getUnionCollectionOfSets(
			Collection<List<E>> collectionOfSets) {
		Set<E> union = new HashSet<E>();
		for (List<E> set : collectionOfSets) {
			for (E clause : set) {
				union.add(clause);
			}
		}

		return union;
	}

	/**
	 * Removes any set in a collection of sets that is a superset of some
	 * other
	 * @param inputCollectionOfSets
	 * @return Collection of sets which are'nt superSets of some other set
	 */
	public static <E> List<List<E>> maintainNoSupersets(
			List<List<E>> inputCollectionOfSets) {

		List<E> testSet = null;
		List<E> inputSet = null;
		Set<List<E>> setsToRemove = new HashSet<List<E>>();

		// Se ordenan los conjuntos por tama�o de menor mayor
		Collections
				.sort(inputCollectionOfSets, new CollectionsSizeComparator());

		for (int j = 0; j < inputCollectionOfSets.size(); j++) {
			inputSet = testSet = inputCollectionOfSets.get(j);
			for (int i = 0; i < inputCollectionOfSets.size(); i++) {
				testSet = inputCollectionOfSets.get(i);
				if (i != j) {
					if (!inputSet.equals(testSet)) {
						if (SetUtil.<E> isSubset(inputSet, testSet)) {
							// Se debe eliminar pq es super set
							setsToRemove.add(testSet);

						}
					} else {
						// Si son iguales se remueve al menos 1 pq no se
						// necesita
						setsToRemove.add(testSet);
					}
				}

			}// Cierra while que recorre lista desde la segunda posicion
		}// Cierra while que recorre la lista desde la primera posici�n

		// Se eliminan los elementos
		if (!setsToRemove.isEmpty()) {
			removeSets(inputCollectionOfSets, setsToRemove);

		}

		return inputCollectionOfSets;
	}// Cierra metodo

	/**
	 * Removes any set in a collection of sets that is
	 * a sub set of some other set 
	 * @param inputCollectionOfSets
	 * @return
	 */
	public static <E> List<List<E>> maintainNoSubsets(
			List<List<E>> inputCollectionOfSets) {

		List<E> testSet = null;
		List<E> inputSet = null;
		List<List<E>> setsToRemove = new ArrayList<List<E>>();

		for (int j = 0; j < inputCollectionOfSets.size(); j++) {
			inputSet = testSet = inputCollectionOfSets.get(j);
			for (int i = 0; i < inputCollectionOfSets.size(); i++) {
				testSet = inputCollectionOfSets.get(i);

				if (i != j) {
					if (!inputSet.equals(testSet)) {
						if (SetUtil.<E> isSubset(testSet, inputSet)) {
							// Se debe eliminar pq es sub set
							setsToRemove.add(testSet);
							System.out.println(" A remover set: " + testSet);
						}
					} else {
						// Si son iguales se remueve pq no se necesita
						setsToRemove.add(testSet);
						System.out.println(" A remover set por equals "
								+ testSet.size());
					}
				}

			}// Cierra while que recorre lista de MCS desde la segunda posicion
		}// Cierra while que recorre la lista de MCS desde la primera posici�n

		// Se eliminan los elementos
		if (!setsToRemove.isEmpty()) {
			removeSets(inputCollectionOfSets, setsToRemove);
		}
		return inputCollectionOfSets;
	}// Cierra metodo

	/**
	 * Removes any duplicate set in 
	 * a colecction of sets
	 * @param inputCollectionOfSets
	 * @return
	 */
	public static <E> List<List<E>> maintainNoEqualsSets(
			Set<List<E>> inputCollectionOfSets) {

		List<E> testSet = null;
		List<E> inputSet = null;
		List<List<E>> setsToRemove = new ArrayList<List<E>>();
		List<List<E>> sets = new ArrayList<List<E>>();
		// Se pasa todo a una lista
		for (List<E> list : inputCollectionOfSets) {
			sets.add(list);
		}

		for (int j = 0; j < inputCollectionOfSets.size() - 1; j++) {
			inputSet = testSet = sets.get(j);
			for (int i = j + 1; i < inputCollectionOfSets.size(); i++) {
				testSet = sets.get(i);
				if (inputSet.size()==testSet.size()) {
					
					if (SetUtil.<E> isSubset(testSet, inputSet)) {
						// Se debe eliminar pq es super set
						setsToRemove.add(testSet);
						System.out.println(" A remover set: " + testSet);
					}
				} 

			}
		}

		// Se eliminan los elementos
		if (!setsToRemove.isEmpty()) {
			removeSets(sets, setsToRemove);
		}
		return sets;
	}//

	/**
	 * Transforms Collection into List
	 * @param collection
	 * @return List of collections content
	 */
	public static <E> List<E> transformCollectionTOList(Collection<E> collection) {

		List<E> collectionList = new ArrayList<E>();
		for (E obj : collection) {
			collectionList.add(obj);
		}

		return collectionList;
	}

	/**
	 * Transforms Collection into Set
	 * @param collection
	 * @return Set of collection content
	 */
	public static <E> Set<E> transformCollectionTOSet(Collection<E> collection) {

		Set<E>collectionSet = new HashSet<E>();
		for (E obj : collection) {
			collectionSet.add(obj);
		}

		return collectionSet;
	}

}
