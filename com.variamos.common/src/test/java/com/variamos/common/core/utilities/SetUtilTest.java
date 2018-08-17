package com.variamos.common.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.variamos.common.core.utilities.SetUtil;

/**
 * Verifies functionality of SetUtil class
 */
public class SetUtilTest {

	/**
	 * Verifies maintainNoSubsets method
	 * in 3 ways, true, false, error.
	 */
	@Test
	public void maintainNoSubsets() {
		/**
		 * Creates 3 sets, set 1 is subset of set 2
		 * expect : 2 sets (set2,set3)
		 * Most basic test. 
		 */

		 //Set 1
		List<String> set1 = new ArrayList<String>();
		set1.add("A");
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

		
		Assert.assertTrue(newCollectionOfSets.size() < originalCollectionOfSets
				.size());
		Assert.assertTrue(!newCollectionOfSets.contains(set1));


	}

	@Test
	public void maintainNoSuperSets() {
		List<String> set1 = new ArrayList<String>();
		set1.add("ROOT#<==>F1,");

		List<String> set2 = new ArrayList<String>();
		set2.add("F1#<==>F6,");
		set2.add("ROOT#<==>F1,");
		set2.add("F4#<==>F8,");

		List<String> set4 = new ArrayList<String>();
		set4.add("F1#<==>F6,");
		set4.add("ROOT#<==>F1,");
		set4.add("F4#<==>F8,");
		set4.add("(1-F5)+(1-F3)#>0,");

		List<String> set3 = new ArrayList<String>();
		set3.add("F1#<==>F6,");
		set3.add("(1-F5)+(1-F3)#>0,");
		set3.add("(1-F3)+(1-F8)#>0,");

		List<List<String>> originalCollectionOfSets = new ArrayList<List<String>>();
		originalCollectionOfSets.add(set1);
		originalCollectionOfSets.add(set2);
		originalCollectionOfSets.add(set4);
		originalCollectionOfSets.add(set3);

		List<List<String>> newCollectionOfSets = new ArrayList<List<String>>();
		newCollectionOfSets.addAll(originalCollectionOfSets);

		newCollectionOfSets = SetUtil.maintainNoSupersets(newCollectionOfSets);

		for (List<String> elements : newCollectionOfSets) {
			System.out.println("NEW COLLECTION:without supersets"
					+ elements.toString());
		}

		Assert.assertTrue(newCollectionOfSets.size() < originalCollectionOfSets
				.size());
		Assert.assertTrue(!newCollectionOfSets.contains(set4));

	}

	@Test
	public void getSuperSetsOfCollectionSets() {
		List<String> set1 = new ArrayList<String>();
		set1.add("ROOT#<==>F1,");

		List<String> set2 = new ArrayList<String>();
		set2.add("F1#<==>F6,");
		set2.add("F4#<==>F8,");
		set2.add("ROOT#<==>F1,");

		List<String> set3 = new ArrayList<String>();
		set3.add("F1#<==>F6,");
		set3.add("(1-F5)+(1-F3)#>0,");
		set3.add("(1-F3)+(1-F8)#>0,");

		List<List<String>> originalCollectionOfSets = new ArrayList<List<String>>();
		originalCollectionOfSets.add(set1);
		originalCollectionOfSets.add(set2);
		originalCollectionOfSets.add(set3);

		List<List<String>> setToVerify = new ArrayList<List<String>>();
		setToVerify.add(set1);

		List<List<String>> newCollectionOfSets = new ArrayList<List<String>>();

		newCollectionOfSets = SetUtil.getSuperSetsOfSet(set1,
				originalCollectionOfSets);

		for (List<String> elements : newCollectionOfSets) {
			System.out.println("SUPERSETS" + elements.toString());
		}

		Assert.assertTrue(newCollectionOfSets.size() < originalCollectionOfSets
				.size());

	}

	@Test
	public void subsetEqualsTest() {
		List<String> set2 = new ArrayList<String>();
		set2.add("F1#<==>F6,");
		set2.add("F4#<==>F8,");
		set2.add("ROOT#<==>F1,");

		List<String> set3 = new ArrayList<String>();
		set3.add("F1#<==>F6,");
		set3.add("F4#<==>F8,");
		set3.add("ROOT#<==>F1,");

		Assert.assertTrue(SetUtil.isSubset(set2, set3));
	}

	@Test
	public void superSetEqualsTest() {
		List<String> set2 = new ArrayList<String>();
		set2.add("F1#<==>F6,");
		set2.add("F4#<==>F8,");
		set2.add("ROOT#<==>F1,");

		List<String> set3 = new ArrayList<String>();
		set3.add("F1#<==>F6,");
		set3.add("F4#<==>F8,");
		set3.add("ROOT#<==>F1,");

		Assert.assertTrue(SetUtil.isSuperSet(set2, set3));
	}

	@Test
	public void equalsTest() {
		List<String> set2 = new ArrayList<String>();
		set2.add("F1#<==>F6,");
		set2.add("F4#<==>F8,");
		set2.add("ROOT#<==>F1,");

		List<String> set3 = new ArrayList<String>();
		set3.add("F1#<==>F6,");
		set3.add("F4#<==>F8,");
		set3.add("ROOT#<==>F1,");

		Assert.assertTrue(SetUtil.isEquals(set2, set3));
	}

	@Test
	public void subsetCollectionTest() {
		List<String> set1 = new ArrayList<String>();
		set1.add("ROOT#<==>F1,");

		List<String> set2 = new ArrayList<String>();
		set2.add("ROOT#<==>F1,");
		set2.add("F4#<==>F8,");
		set2.add("F1#<==>F6,");

		List<String> set3 = new ArrayList<String>();
		set3.add("F1#<==>F6,");
		set3.add("F4#<==>F8,");
		set3.add("ROOT#<==>F1,");

		List<List<String>> originalCollectionOfSets = new ArrayList<List<String>>();
		originalCollectionOfSets.add(set2);
		originalCollectionOfSets.add(set1);

		Assert.assertTrue(SetUtil.verifySetIsSubSetOfCollectionSets(set3,
				originalCollectionOfSets));
	}

	@Test
	public void complementOfSets() {
		List<String> set1 = new ArrayList<String>();
		set1.add("ROOT#<==>F1,");

		List<String> set2 = new ArrayList<String>();
		set2.add("F1#<==>F6,");
		set2.add("F4#<==>F8,");
		set2.add("ROOT#<==>F1,");

		List<String> set3 = new ArrayList<String>();
		set3.add("F1#<==>F6,");
		set3.add("(1-F5)+(1-F3)#>0,");
		set3.add("(1-F3)+(1-F8)#>0,");

		List<String> universalSet = new ArrayList<String>();
		universalSet.add("F1#<==>F6,");
		universalSet.add("B");
		universalSet.add("(1-F5)+(1-F3)#>0,");
		universalSet.add("(1-F3)+(1-F8)#>0,");
		universalSet.add("F1#<==>F6,");
		universalSet.add("F4#<==>F8,");
		universalSet.add("ROOT#<==>F1,");
		universalSet.add("A");

		List<List<String>> setOfSets = new ArrayList<List<String>>();
		setOfSets.add(set2);
		setOfSets.add(set1);
		setOfSets.add(set3);
		
		List<String> complement = new ArrayList<String>();
		int sizeOrginalUniversalSet=universalSet.size();
		complement=(List<String>) SetUtil.complementOfSets(universalSet, setOfSets);
		Assert.assertTrue(complement.size()==2);
		Assert.assertTrue(complement.get(0)=="B");
		Assert.assertTrue(complement.get(1)=="A");
		Assert.assertTrue(sizeOrginalUniversalSet==universalSet.size());
		
		
	}
}
