package com.variamos.reasoning.defectAnalyzer.core;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.common.core.exceptions.TransformerException;
import com.variamos.common.model.enums.NotationType;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.core.HlclUtil;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.reasoning.core.transformer.VariabilityModelTransformer;
import com.variamos.reasoning.defectAnalyzer.model.defects.DeadElement;
import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;
import com.variamos.reasoning.defectAnalyzer.model.defects.FalseOptionalElement;
import com.variamos.reasoning.defectAnalyzer.model.defects.FalseProductLine;
import com.variamos.reasoning.defectAnalyzer.model.defects.Redundancy;
import com.variamos.reasoning.defectAnalyzer.model.defects.VoidModel;
import com.variamos.reasoning.defectAnalyzer.model.dto.VMTransformerInDTO;
import com.variamos.reasoning.defectAnalyzer.model.transformation.Dependency;
import com.variamos.reasoning.defectAnalyzer.model.transformation.VariabilityModel;
import com.variamos.reasoning.util.ConstraintRepresentationUtil;
import com.variamos.solver.model.ConfigurationOptionsDTO;
import com.variamos.solver.model.SolverSolution;

public class VerificatorTest {

	@Test
	public void isNotVoidTest() {

		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<IntBooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model);
		VoidModel isVoid = (VoidModel) verifier.isVoid();
		assertTrue(isVoid == null);

	}

	@Test
	public void isFalsePLTest() {

		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<IntBooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());
		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model);
		FalseProductLine isFalsePL = (FalseProductLine) verifier.isFalsePL();
		assertTrue(isFalsePL.isFalsePL());

	}

	@Test
	public void isDeadElement() {
		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<IntBooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model);
		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		DeadElement deadElement;
		try {
			deadElement = (DeadElement) verifier.isDeadElement(identifiers
					.iterator().next());
			assertTrue(deadElement != null);
		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	public void isRedundant() {
		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<IntBooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model);
		Collection<Dependency> traversalDependencies = variabilityModel
				.getInclusionExclusionDependencies().values();
		Dependency dependencyToEvaluate = traversalDependencies.iterator()
				.next();
		List<IntBooleanExpression> negationList = new ArrayList<IntBooleanExpression>();
		negationList.add(dependencyToEvaluate.getNegationExpression());
		Redundancy redundancy;
		try {
			redundancy = (Redundancy) verifier.isRedundant(dependencyToEvaluate
					.getConstraintExpression());
			assertTrue(redundancy == null);
		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	public void allRedundancies() {
		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalTesis.sxfm");
		Collection<IntBooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model);

		List<IntBooleanExpression> constraitsToVerify = new ArrayList<IntBooleanExpression>();

		Collection<Dependency> traversalDependencies = variabilityModel
				.getInclusionExclusionDependencies().values();
		Iterator<Dependency> it = traversalDependencies.iterator();

		while (it.hasNext()) {
			Dependency dependency = it.next();
			constraitsToVerify.add(dependency.getConstraintExpression());
		}

		List<Defect> redundancies;
		try {
			redundancies = verifier.getRedundancies(constraitsToVerify);
			assertTrue(redundancies.size() == 3);

		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	public void allDeadElements() {
		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalTesis.sxfm");
		Collection<IntBooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		List<Defect> deadElements;
		try {
			deadElements = verifier.getDeadElements(identifiers);
			assertTrue(deadElements.size() == 10);
		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void allDeadElementsConfigurationEnforce() {
		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalTesis.sxfm");
		Collection<IntBooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		List<Defect> deadElements;
		SolverSolution configuration= new SolverSolution();
		configuration.enforce("HTTPS");
		try {
			deadElements = verifier.getDeadElements(identifiers, new ConfigurationOptionsDTO(),configuration);
			assertTrue(deadElements.size() == 25);
		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Test
	public void allFalseOptional() {
		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalTesis.sxfm");
		Collection<IntBooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		Set<Identifier> identifiersToVerify = new HashSet<Identifier>();

		Identifier identify = null;
		Iterator<Identifier> iterator = identifiers.iterator();
		while (iterator.hasNext()) {

			identify = (Identifier) iterator.next();
			switch (identify.getId()) {
			case "Archivo":
			case "Flash":
			case "Busquedas":
				identifiersToVerify.add(identify);
				break;
			}

		}

		List<Defect> falseOptionalElements;
		try {
			long startTime = System.currentTimeMillis();
			falseOptionalElements = verifier
					.getFalseOptionalElements(identifiers,new ConfigurationOptionsDTO(), new SolverSolution());
			assertTrue(falseOptionalElements.size() == 15);
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(" Analysis time method 1: " + totalTime);
		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	@Test
	public void allFalseOptionalPartialConfiguration() {
		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalTesis.sxfm");
		Collection<IntBooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		Set<Identifier> identifiersToVerify = new HashSet<Identifier>();

		Identifier identify = null;
		Iterator<Identifier> iterator = identifiers.iterator();
		while (iterator.hasNext()) {

			identify = (Identifier) iterator.next();
			switch (identify.getId()) {
			case "Archivo":
			case "Flash":
			case "Busquedas":
				identifiersToVerify.add(identify);
				break;
			}

		}

		List<Defect> falseOptionalElements;
		try {
			long startTime = System.currentTimeMillis();
			SolverSolution configuration= new SolverSolution();
			configuration.enforce("HTTPS");
			falseOptionalElements = verifier
					.getFalseOptionalElements(identifiers,new ConfigurationOptionsDTO(), configuration);
			assertTrue(falseOptionalElements.size() == 20);
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(" Analysis time method 1: " + totalTime);
		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void isFalseOptional() {
		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalTesis.sxfm");
		Collection<IntBooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model);
		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		Identifier identify = null;
		Iterator<Identifier> iterator = identifiers.iterator();
		while (iterator.hasNext()) {

			identify = (Identifier) iterator.next();
			if (identify.getId().equals("Archivo")) {
				break;
			}
		}
		FalseOptionalElement falseOptional;
		try {
			long startTime = System.currentTimeMillis();
			falseOptional = (FalseOptionalElement) verifier
					.isFalseOptionalElement(identify);
			assertTrue(falseOptional != null);
			assertTrue(falseOptional.getId().equals("Archivo"));
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(" Analysis time method 1: " + totalTime);
		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	public void isNotAttainableDomains() {
		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<IntBooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model);
		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		List<Defect> nonAttainableDomains;
		try {
			nonAttainableDomains = verifier.getNonAttainableDomains(identifiers
					.iterator().next());
			assertTrue(nonAttainableDomains.size() == 1);
		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	private VariabilityModel transformFeatureModel(String modelInputPath) {
		// Se instancia el transformador
		VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
		transformerInDTO.setNotationType(NotationType.FEATURES_MODELS);
		transformerInDTO.setPathToTransform(modelInputPath);

		VariabilityModel variabilityModel = null;
		VariabilityModelTransformer transformer = new VariabilityModelTransformer(
				transformerInDTO);
		try {
			variabilityModel = transformer.transformToVariabilityModel();
			assertTrue(variabilityModel != null);
			System.out.println("The FM was correctly transformed \n");
			return variabilityModel;
		} catch (TransformerException e) {
			e.printStackTrace();

		}

		return null;
	}

}
