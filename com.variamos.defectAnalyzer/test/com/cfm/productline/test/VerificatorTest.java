package com.cfm.productline.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.variamos.core.enums.NotationType;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.core.exceptions.TransformerException;
import com.variamos.defectAnalyzer.defectAnalyzer.DefectsVerifier;
import com.variamos.defectAnalyzer.defectAnalyzer.IntDefectsVerifier;
import com.variamos.defectAnalyzer.dto.VMTransformerInDTO;
import com.variamos.defectAnalyzer.model.Dependency;
import com.variamos.defectAnalyzer.model.VariabilityModel;
import com.variamos.defectAnalyzer.model.defects.DeadElement;
import com.variamos.defectAnalyzer.model.defects.Defect;
import com.variamos.defectAnalyzer.model.defects.FalseOptionalElement;
import com.variamos.defectAnalyzer.model.defects.FalseProductLine;
import com.variamos.defectAnalyzer.model.defects.Redundancy;
import com.variamos.defectAnalyzer.model.defects.VoidModel;
import com.variamos.defectAnalyzer.transformer.VariabilityModelTransformer;
import com.variamos.defectAnalyzer.util.ConstraintRepresentationUtil;

public class VerificatorTest {

	public void isVoidTest() {

		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);
		VoidModel isVoid = (VoidModel) verifier.isVoid();
		assertFalse(isVoid.isVoidModel());

	}

	public void isFalsePLTest() {

		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());
		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);
		FalseProductLine isFalsePL = (FalseProductLine) verifier.isFalsePL();
		assertTrue(isFalsePL.isFalsePL());

	}

	@Test
	public void isDeadElement() {
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);
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
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);
		Collection<Dependency> traversalDependencies = variabilityModel
				.getInclusionExclusionDependencies().values();
		Dependency dependencyToEvaluate = traversalDependencies.iterator()
				.next();
		List<BooleanExpression> negationList = new ArrayList<BooleanExpression>();
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
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalTesis.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);

		List<BooleanExpression> constraitsToVerify = new ArrayList<BooleanExpression>();

		Collection<Dependency> traversalDependencies = variabilityModel
				.getInclusionExclusionDependencies().values();
		Iterator<Dependency> it = traversalDependencies.iterator();

		while (it.hasNext()) {
			Dependency dependency = it.next();
			constraitsToVerify.add(dependency.getConstraintExpression());
		}

		List<Defect> redundancies;
		try {
			redundancies = verifier.getRedundancies(model);
			assertTrue(redundancies.size() == 3);

		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	public void allDeadElements() {
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalTesis.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		List<Defect> deadElements;
		try {
			deadElements = verifier.getDeadElements(identifiers);
			assertTrue(deadElements.size() == 10);
		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	public void allFalseOptional() {
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalTesis.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);

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
			falseOptionalElements = verifier.getFalseOptionalElements(
					identifiersToVerify);
			assertTrue(falseOptionalElements.size() == 2);
		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	public void isFalseOptional() {
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalTesis.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);
		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		Identifier identify = null;
		Iterator<Identifier> iterator = identifiers.iterator();
		while (iterator.hasNext()) {

			identify = (Identifier) iterator.next();
			if (identify.getId() == "Archivo") {
				break;
			}
		}
		FalseOptionalElement falseOptional;
		try {
			falseOptional = (FalseOptionalElement) verifier
					.isFalseOptionalElement( identify);
			assertTrue(falseOptional != null);

		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	public void isNotAttainableDomains() {
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);
		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		List<Defect> nonAttainableDomains;
		try {
			nonAttainableDomains = verifier.getNonAttainableDomains(
					identifiers.iterator().next());
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
