package com.cfm.productline.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cfm.productline.defectAnalyzer.HittingSetIdentifier;
import com.cfm.productline.exceptions.FunctionalException;
import com.cfm.productline.model.defectAnalyzerModel.Dependency;

public class HittingSetTest {

//	@Test
//	public void probarMetodosMUSconSuperSet() {
//		List<String> constraintProgram10FeaturesModel1 = new ArrayList<String>();
//		constraintProgram10FeaturesModel1.add("ROOT#<==>F1,");
//
//		List<String> constraintProgram10FeaturesModel2 = new ArrayList<String>();
//		constraintProgram10FeaturesModel2.add("F1#<==>F6,");
//		constraintProgram10FeaturesModel2.add("F4#<==>F8,");
//		constraintProgram10FeaturesModel2.add("ROOT#<==>F1,");
//
//		List<String> constraintProgram10FeaturesModel3 = new ArrayList<String>();
//		constraintProgram10FeaturesModel3.add("F1#<==>F6,");
//		constraintProgram10FeaturesModel3.add("(1-F5)+(1-F3)#>0,");
//		constraintProgram10FeaturesModel3.add("(1-F3)+(1-F8)#>0,");
//
//		List<List<String>> MCSes = new ArrayList<List<String>>();
//		MCSes.add(constraintProgram10FeaturesModel1);
//		MCSes.add(constraintProgram10FeaturesModel2);
//		MCSes.add(constraintProgram10FeaturesModel3);
//
//		// List<String> MCS1 = new ArrayList<String>();
//		// MCS1.add("C1");
//		//
//		// List<String> MCS2 = new ArrayList<String>();
//		// MCS2.add("C2");
//		// MCS2.add("C3");
//		// MCS2.add("C5");
//		//
//		// List<String> MCS3 = new ArrayList<String>();
//		// MCS3.add("C2");
//		// MCS3.add("C3");
//		// MCS3.add("C6");
//		//
//		// List<String> MCS4 = new ArrayList<String>();
//		// MCS4.add("C2");
//		// MCS4.add("C4");
//		// MCS4.add("C5");
//		//
//		// List<String> MCS5 = new ArrayList<String>();
//		// MCS5.add("C2");
//		// MCS5.add("C4");
//		// MCS5.add("C6");
//		//
//		// List<List<String>> MCSes = new ArrayList<List<String>>();
//		// MCSes.add(MCS1);
//		// MCSes.add(MCS2);
//		// MCSes.add(MCS3);
//		// MCSes.add(MCS4);
//		// MCSes.add(MCS5);
//
//		// propagateChoice(MCSes, "F1#<==>F6,",
//		// constraintProgram10FeaturesModel2);
//
//		// genarateMUS(MCSes);
//		// De esta lista se debe eliminar el tercer elemento
//		// maintainNoSupersets(MCSes);
//
//		// allMUSes(MCSes,new ArrayList<String>(),new HashSet<String>());
//
//		// generateAllMUSes(MCSes);
//
//		/*try {*/
//			List<List<String>> hittingSets = new ArrayList<List<String>>(); 
//					HittingSetIdentifier
//					.allMUSes(MCSes, new ArrayList<String>(),hittingSets);
//
//			
//			for (List<String> elements : hittingSets) {
//				System.out.println("HITTING SET:" + elements.toString());
//			}
//
//			Assert.assertTrue(hittingSets.size() > 0);
//			Assert.assertEquals(3, hittingSets.size());
//			Assert.assertNotSame(hittingSets.get(0), hittingSets.get(1));
//			Assert.assertNotSame(hittingSets.get(0), hittingSets.get(2));
//			Assert.assertNotSame(hittingSets.get(1), hittingSets.get(2));
//
//	/*	} catch (FunctionalException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}*/
//
//	}
//
//	@Test
//	public void probarMetodosMUSSinSuperSet() {
//		List<String> constraintProgram10FeaturesModel1 = new ArrayList<String>();
//		constraintProgram10FeaturesModel1.add("ROOT#<==>F1,");
//
//		List<String> constraintProgram10FeaturesModel2 = new ArrayList<String>();
//		constraintProgram10FeaturesModel2.add("F1#<==>F6,");
//		constraintProgram10FeaturesModel2.add("F4#<==>F8,");
//
//		List<String> constraintProgram10FeaturesModel3 = new ArrayList<String>();
//		constraintProgram10FeaturesModel3.add("F1#<==>F6,");
//		constraintProgram10FeaturesModel3.add("(1-F5)+(1-F3)#>0,");
//		constraintProgram10FeaturesModel3.add("(1-F3)+(1-F8)#>0,");
//
//		List<List<String>> MCSes = new ArrayList<List<String>>();
//		MCSes.add(constraintProgram10FeaturesModel1);
//		MCSes.add(constraintProgram10FeaturesModel2);
//		MCSes.add(constraintProgram10FeaturesModel3);
//
//		try {
//			List<List<String>> hittingSets = HittingSetIdentifier
//					.generateAllMinimalHittingSets(MCSes);
//			for (List<String> elements : hittingSets) {
//				System.out.println("HITTING SET:" + elements.toString());
//			}
//
//			Assert.assertTrue(hittingSets.size() > 0);
//			Assert.assertEquals(3, hittingSets.size());
//			Assert.assertNotSame(hittingSets.get(0), hittingSets.get(1));
//			Assert.assertNotSame(hittingSets.get(0), hittingSets.get(2));
//			Assert.assertNotSame(hittingSets.get(1), hittingSets.get(2));
//
//		} catch (FunctionalException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//	
//	
//	@Test
//	public void probarMetodosMUSconSuperSetMUsOriginal() {
//
//
//		 List<String> MCS1 = new ArrayList<String>();
//		 MCS1.add("C1");
//		
//		 List<String> MCS2 = new ArrayList<String>();
//		 MCS2.add("C2");
//		 MCS2.add("C3");
//		 MCS2.add("C5");
//		
//		 List<String> MCS3 = new ArrayList<String>();
//		 MCS3.add("C2");
//		 MCS3.add("C3");
//		 MCS3.add("C6");
//		
//		 List<String> MCS4 = new ArrayList<String>();
//		 MCS4.add("C2");
//		 MCS4.add("C4");
//		 MCS4.add("C5");
//		
//		 List<String> MCS5 = new ArrayList<String>();
//		 MCS5.add("C2");
//		 MCS5.add("C4");
//		 MCS5.add("C6");
//		
//		 List<List<String>> MCSes = new ArrayList<List<String>>();
//		 MCSes.add(MCS1);
//		 MCSes.add(MCS2);
//		 MCSes.add(MCS3);
//		 MCSes.add(MCS4);
//		 MCSes.add(MCS5);
//
//		// propagateChoice(MCSes, "F1#<==>F6,",
//		// constraintProgram10FeaturesModel2);
//
//		// genarateMUS(MCSes);
//		// De esta lista se debe eliminar el tercer elemento
//		// maintainNoSupersets(MCSes);
//
//		// allMUSes(MCSes,new ArrayList<String>(),new HashSet<String>());
//
//		// generateAllMUSes(MCSes);
//
//		try {
//			List<String> hittingSet = HittingSetIdentifier
//					.generateOneMinimalHittingSet(MCSes, "C6");
//			
//			System.out.println("HITTING SET Liffiton approach:" + hittingSet.toString());
//			
////			List<List<String>> hittingSets2 = HittingSetIdentifier
////					.generateAllHittingSets(MCSes);
////			for (List<String> elements : hittingSets) {
////				System.out.println("HITTING SET:" + elements.toString());
////			}
////
////			Assert.assertTrue(hittingSets.size() > 0);
////			Assert.assertEquals(3, hittingSets.size());
////			Assert.assertNotSame(hittingSets.get(0), hittingSets.get(1));
////			Assert.assertNotSame(hittingSets.get(0), hittingSets.get(2));
////			Assert.assertNotSame(hittingSets.get(1), hittingSets.get(2));
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
