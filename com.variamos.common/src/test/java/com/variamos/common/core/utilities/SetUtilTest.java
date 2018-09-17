package com.variamos.common.core.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.variamos.common.core.utilities.SetUtil;

/**
 * Verifies functionality of SetUtil class
 * @author Esteban Echavarria - Monitor Investigativo - Eafit 2018. 
 * @author Luisa Rincon
 */
public class SetUtilTest {
	@Test 
	/**
	 * Verifies union method. 
	 */
	public void union(){
		//Set1
		List<String> set1 = new ArrayList<String>();
		set1.add("A");
		set1.add("B");
		set1.add("C");
		set1.add("D");
		set1.add("E");
		set1.add("F");
		set1.add("J");
		//Set2
		List<String> set2 = new ArrayList<String>();
		set2.add("A");
		set2.add("A");
		set2.add("D");
		set2.add("E");
		set2.add("F");
		set2.add("G");
		//Set3
		List<String> set3 = new ArrayList<String>();
		set3.add("A");
		set3.add("B");
		set3.add("C");
		set3.add("D");
		set3.add("E");
		set3.add("H");
		set3.add("I");

		Set<String> resultSet = new HashSet<String>();
		resultSet.add("A");
		resultSet.add("B");
		resultSet.add("C");
		resultSet.add("D");
		resultSet.add("E");
		resultSet.add("F");
		resultSet.add("G");
		resultSet.add("H");
		resultSet.add("I");
		resultSet.add("J");
		
		Set<String> UnionOfCollection = new HashSet<String>();
		UnionOfCollection = SetUtil.union(set1,set2,set3); 
		
		//Verify if every element in the unionSet is in the resultSet
		for(String elem : UnionOfCollection){
			Assert.assertFalse(!resultSet.contains(elem));
		}

		//Once verified all in union is in result, assert true. 
		Assert.assertTrue(UnionOfCollection.size() == resultSet.size());
	}
	
	
	@Test
	/** 
	 * Verifies removeSets method
	 */
	public void removeSets(){
		//Set1
		List<String> set1 = new ArrayList<String>();
		set1.add("A");
		set1.add("B");
		set1.add("C");
		set1.add("D");
		//Set2
		List<String> set2 = new ArrayList<String>();
		set2.add("E");
		set2.add("F");
		set2.add("G");
		//Set3
		List<String> set3 = new ArrayList<String>();
		set3.add("H");
		set3.add("I");
		//Set4
		List<String> set4 = new ArrayList<String>();
		set4.add("B");
		set4.add("A");
		set4.add("C");
		set4.add("D");

		//Collection 1
		List<List<String>> collectionOfSets = new ArrayList<List<String>>();
		collectionOfSets.add(set1);
		collectionOfSets.add(set2);
		collectionOfSets.add(set3);

		//Collection 2
		List<List<String>> collectionToRemove = new ArrayList<List<String>>();
		collectionToRemove.add(set4);
		collectionToRemove.add(set3);

		Collection<List<String>> resultCollection = new ArrayList<List<String>>();
		resultCollection = SetUtil.removeSets(collectionOfSets, collectionToRemove);
		
		//Verifies if contains expected Collection and its size is as expected
		Assert.assertFalse(!resultCollection.contains(set2));
		Assert.assertFalse(!resultCollection.contains(set1));
		Assert.assertTrue(resultCollection.size() == 2);
	}

	@Test
	/** 
	 * Verifies removeSets method
	 */
	public void isSubset(){
		//Set1
		List<String> set1 = new ArrayList<String>();
		set1.add("A");
		set1.add("B");
		set1.add("C");
		set1.add("A");
		set1.add("A");

		//Set2
		List<String> set2 = new ArrayList<String>();
		set2.add("A");
		set2.add("B");
		set2.add("C");
		set2.add("D");
		set2.add("E");
		set2.add("F");

		boolean result;
		result = SetUtil.isSubset(set1, set2);
		
		//Assert true if result is as expected (True)
		Assert.assertTrue(result);
		Assert.assertFalse(!result);

	}
	@Test
	/** 
	 * Verifies isEquals method
	 */
	public void isEquals(){
		//Set1
		List<String> set1 = new ArrayList<String>();
		set1.add("A");
		set1.add("B");
		set1.add("C");
	
		//Set2
		List<String> set2 = new ArrayList<String>();
		set2.add("B");
		set2.add("A");
		set2.add("C");


		boolean result;
		result = SetUtil.isEquals(set1, set2);
		
		//Assert true if result is as expected (True)
		Assert.assertTrue(result);
		Assert.assertFalse(!result);

	}
	@Test
	/** 
	 * Verifies isSuperSet method
	 */
	public void isSuperSet(){
		//Set1
		List<String> set1 = new ArrayList<String>();
		set1.add("A");
		set1.add("B");
		set1.add("C");
		set1.add("A");
	
		//Set2
		List<String> set2 = new ArrayList<String>();
		set2.add("B");
		set2.add("A");
		set2.add("C");
		set2.add("B");
		set2.add("B");


		boolean result;
		result = SetUtil.isSuperSet(set1, set2);
		
		//Assert true if result is as expected (True)
		Assert.assertTrue(result);
		Assert.assertFalse(!result);

	}

	@Test
	/** 
	 * Verifies verifySetIsSubSetOfCollectionSets method
	 */
	public void verifySetIsSubSetOfCollectionSets(){
		//Set1
		List<String> set1 = new ArrayList<String>();
		set1.add("H");
		set1.add("I");
		//Set2
		List<String> set2 = new ArrayList<String>();
		set2.add("E");
		set2.add("F");
		set2.add("G");
		//Set3
		List<String> set3 = new ArrayList<String>();
		set3.add("A");
		set3.add("B");
		set3.add("C");
		set3.add("D");
		//Set4
		List<String> set4 = new ArrayList<String>();
		set4.add("B");
		set4.add("A");
		set4.add("C");
		set4.add("D");
		set4.add("A");
		set4.add("C");
		set4.add("D");
		
		//Collection 1
		List<List<String>> collectionOfSets = new ArrayList<List<String>>();
		collectionOfSets.add(set1);
		collectionOfSets.add(set2);
		collectionOfSets.add(set3);


		boolean result;
		result = SetUtil.verifySetIsSubSetOfCollectionSets(set4, collectionOfSets);
		
		//Assert true if result is as expected (True)
		Assert.assertTrue(result);
		Assert.assertFalse(!result);
	}

	@Test
	/** 
	 * Verifies verifySetIsSuperSetOfCollectionSets method
	 */
	public void verifySetIsSuperSetOfCollectionSets(){
		//Set1
		List<String> set1 = new ArrayList<String>();
		set1.add("A");
		set1.add("B");
		set1.add("C");
		set1.add("D");
		set1.add("A");
		set1.add("B");
		//Set2
		List<String> set2 = new ArrayList<String>();
		set2.add("E");
		set2.add("F");
		set2.add("G");
		//Set3
		List<String> set3 = new ArrayList<String>();
		set3.add("H");
		set3.add("I");
		//Set4
		List<String> set4 = new ArrayList<String>();
		set4.add("B");
		set4.add("A");
		set4.add("C");
		set4.add("D");
		
		//Collection 1
		List<List<String>> collectionOfSets = new ArrayList<List<String>>();
		collectionOfSets.add(set1);
		collectionOfSets.add(set2);
		collectionOfSets.add(set3);


		boolean result;
		result = SetUtil.verifySetIsSuperSetOfCollectionSets(set4, collectionOfSets);
		
		//Assert true if result is as expected (True)
		Assert.assertTrue(result);
		Assert.assertFalse(!result);
	}

	@Test
	/** 
	 * Verifies getUnionCollectionOfSets method
	 */
	public void getUnionCollectionOfSets(){
		//Set1
		List<String> set1 = new ArrayList<String>();
		set1.add("A");
		set1.add("B");
		set1.add("C");
		set1.add("D");
		//Set2
		List<String> set2 = new ArrayList<String>();
		set2.add("E");
		set2.add("F");
		set2.add("G");
		//Set3
		List<String> set3 = new ArrayList<String>();
		set3.add("H");
		set3.add("I");
		//Set4
		List<String> set4 = new ArrayList<String>();
		set4.add("J");
		set4.add("K");

		List<String> expectedSet = new ArrayList<String>();
		expectedSet.add("A");
		expectedSet.add("B");
		expectedSet.add("C");
		expectedSet.add("D");
		expectedSet.add("E");
		expectedSet.add("F");
		expectedSet.add("G");
		expectedSet.add("H");
		expectedSet.add("I");
		expectedSet.add("J");
		expectedSet.add("K");

		//Collection 1
		Collection<List<String>> collectionOfSets = new ArrayList<List<String>>();
		collectionOfSets.add(set1);
		collectionOfSets.add(set2);
		collectionOfSets.add(set3);
		collectionOfSets.add(set4);
		collectionOfSets.add(set1);

		Collection<String> resultCollection = new ArrayList<String>();
		resultCollection = SetUtil.getUnionCollectionOfSets(collectionOfSets);
		
		//Verifies every element of resultCollection in expectedResult
		for(String elem : resultCollection){
			Assert.assertFalse(!expectedSet.contains(elem));
		}
		//Once verified every element is as expected, if both sets expected, and result got same size, Assert True
		Assert.assertTrue(resultCollection.size() == expectedSet.size());
	}
	
	/**
	 * Verifies maintainNoSubsets method
	 */
	@Test
	public void maintainNoSubsets() {

		 //Set 1
		List<String> set1 = new ArrayList<String>();
		set1.add("B");
		set1.add("A");
		set1.add("A");
		set1.add("C");
		//Set 2
		List<String> set2 = new ArrayList<String>();
		set2.add("A");
		set2.add("B");
		set2.add("C");
		//Set 3
		List<String> set3 = new ArrayList<String>();
		set3.add("D");
		set3.add("E");
		set3.add("F");

		
		//Collection of set1,set2,set3
		List<List<String>> originalCollectionOfSets = new ArrayList<List<String>>();
		originalCollectionOfSets.add(set1);
		originalCollectionOfSets.add(set2);
		originalCollectionOfSets.add(set3);

		//Copies the collection for comparison.
		List<List<String>> newCollectionOfSets = new ArrayList<List<String>>();
		newCollectionOfSets.addAll(originalCollectionOfSets);

		//Calls MaintainNoSubsets into the copy
		newCollectionOfSets = SetUtil.maintainNoSubsets(newCollectionOfSets);

		
		Assert.assertFalse(!(newCollectionOfSets.size() < originalCollectionOfSets
				.size()));
		Assert.assertFalse(newCollectionOfSets.contains(set1));
		Assert.assertTrue(!newCollectionOfSets.contains(set2));

	}

	@Test
	/**
	 * Verifies maintainNoSuperSets method
	 */
	public void maintainNoSuperSets() {
		List<String> set1 = new ArrayList<String>();
		set1.add("A");
		set1.add("A");
		set1.add("B");
		set1.add("B");
		set1.add("C");


		List<String> set2 = new ArrayList<String>();
		set2.add("B");
		set2.add("A");
		set2.add("C");

		List<String> set4 = new ArrayList<String>();
		set4.add("B");
		set4.add("A");
		set4.add("C");
		set4.add("D");

		List<String> set3 = new ArrayList<String>();
		set3.add("B");
		set3.add("D");
		set3.add("E");



		List<List<String>> originalCollectionOfSets = new ArrayList<List<String>>();
		originalCollectionOfSets.add(set1);
		originalCollectionOfSets.add(set2);
		originalCollectionOfSets.add(set3);
		originalCollectionOfSets.add(set4);
		originalCollectionOfSets.add(set3);

		List<List<String>> newCollectionOfSets = new ArrayList<List<String>>();
		newCollectionOfSets.addAll(originalCollectionOfSets);
		newCollectionOfSets = SetUtil.maintainNoSupersets(newCollectionOfSets);

		//Verifies only set3 left 
		Assert.assertFalse(newCollectionOfSets.contains(set4));
		Assert.assertFalse(newCollectionOfSets.contains(set1));
		Assert.assertFalse(newCollectionOfSets.contains(set2));
		Assert.assertTrue(newCollectionOfSets.size() == 1);
	}

	
}
