package com.variamos.reasoning.defectAnalyzer.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.variamos.reasoning.defectAnalyzer.core.HittingSetIdentifier;



public class HittingSetTest {

	@Test
	public void probarMetodosMUSconSuperSet() {
		List<String> constraintProgram10FeaturesModel1 = new ArrayList<String>();
		constraintProgram10FeaturesModel1.add("ROOT#<==>F1,");

		List<String> constraintProgram10FeaturesModel2 = new ArrayList<String>();
		constraintProgram10FeaturesModel2.add("F1#<==>F6,");
		constraintProgram10FeaturesModel2.add("F4#<==>F8,");
		constraintProgram10FeaturesModel2.add("ROOT#<==>F1,");

		List<String> constraintProgram10FeaturesModel3 = new ArrayList<String>();
		constraintProgram10FeaturesModel3.add("F1#<==>F6,");
		constraintProgram10FeaturesModel3.add("(1-F5)+(1-F3)#>0,");
		constraintProgram10FeaturesModel3.add("(1-F3)+(1-F8)#>0,");

		List<List<String>> MCSes = new ArrayList<List<String>>();
		MCSes.add(constraintProgram10FeaturesModel1);
		MCSes.add(constraintProgram10FeaturesModel2);
		MCSes.add(constraintProgram10FeaturesModel3);

		List<List<String>> hittingSets = new ArrayList<List<String>>(); 
					HittingSetIdentifier
					.allMUSes(MCSes, new ArrayList<String>(),hittingSets);

			
		for (List<String> elements : hittingSets) {
				System.out.println("HITTING SET:" + elements.toString());
		}

		Assert.assertTrue(hittingSets.size() > 0);
		Assert.assertEquals(3, hittingSets.size());
		Assert.assertNotSame(hittingSets.get(0), hittingSets.get(1));
		Assert.assertNotSame(hittingSets.get(0), hittingSets.get(2));
		Assert.assertNotSame(hittingSets.get(1), hittingSets.get(2));


	}


}
