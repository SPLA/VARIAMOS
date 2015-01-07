package com.cfm.productline.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.variamos.core.enums.NotationType;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.TransformerException;
import com.variamos.defectAnalyzer.defectAnalyzer.DefectsVerifier;
import com.variamos.defectAnalyzer.defectAnalyzer.IntDefectsVerifier;
import com.variamos.defectAnalyzer.dto.VMAnalyzerInDTO;
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

		// Make input DTO
		VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();

		// Set transformed variability model
		verifierInDTO.setVariabilityModel(variabilityModel);
		// Set Prolog editor type
		verifierInDTO.setSolverEditorType(SolverEditorType.SWI_PROLOG);

		IntDefectsVerifier verifier = new DefectsVerifier(verifierInDTO);
		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		VoidModel isVoid = (VoidModel) verifier.isVoid(model);
		assertFalse(isVoid.isVoidModel());

	}

	public void isFalsePLTest() {

		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		// Make input DTO
		VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();

		// Set transformed variability model
		verifierInDTO.setVariabilityModel(variabilityModel);
		// Set Prolog editor type
		verifierInDTO.setSolverEditorType(SolverEditorType.SWI_PROLOG);

		IntDefectsVerifier verifier = new DefectsVerifier(verifierInDTO);
		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		FalseProductLine isFalsePL = (FalseProductLine) verifier
				.isFalsePL(model);
		assertTrue(isFalsePL.isFalsePL());

	}

	@Test
	public void isDeadElement() {
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());
		// Make input DTO
		VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();

		// Set transformed variability model
		verifierInDTO.setVariabilityModel(variabilityModel);
		// Set Prolog editor type
		verifierInDTO.setSolverEditorType(SolverEditorType.SWI_PROLOG);

		IntDefectsVerifier verifier = new DefectsVerifier(verifierInDTO);
		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		DeadElement deadElement = (DeadElement) verifier.isDeadElement(model,
				identifiers.iterator().next());
		assertTrue(deadElement != null);

	}

	@Test
	public void isRedundant() {
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());
		// Make input DTO
		VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();

		// Set transformed variability model
		verifierInDTO.setVariabilityModel(variabilityModel);
		// Set Prolog editor type
		verifierInDTO.setSolverEditorType(SolverEditorType.SWI_PROLOG);

		IntDefectsVerifier verifier = new DefectsVerifier(verifierInDTO);
		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		Collection<Dependency> traversalDependencies = variabilityModel
				.getInclusionExclusionDependencies().values();
		Dependency dependencyToEvaluate = traversalDependencies.iterator()
				.next();
		Redundancy redundancy = (Redundancy) verifier.isRedundant(model,
				dependencyToEvaluate.getConstraintExpression(),
				dependencyToEvaluate.getNegationExpression());
		assertTrue(redundancy == null);

	}

	@Test
	public void allRedundancies() {
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalTesis.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());
		// Make input DTO
		VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();

		// Set transformed variability model
		verifierInDTO.setVariabilityModel(variabilityModel);
		// Set Prolog editor type
		verifierInDTO.setSolverEditorType(SolverEditorType.SWI_PROLOG);

		IntDefectsVerifier verifier = new DefectsVerifier(verifierInDTO);
		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);

		Map<BooleanExpression, BooleanExpression> constraitsToVerify = new HashMap<BooleanExpression, BooleanExpression>();

		Collection<Dependency> traversalDependencies = variabilityModel
				.getInclusionExclusionDependencies().values();
		Iterator<Dependency> it = traversalDependencies.iterator();

		while (it.hasNext()) {
			Dependency dependency = it.next();
			constraitsToVerify.put(dependency.getConstraintExpression(),
					dependency.getNegationExpression());

		}

		List<Defect> redundancies = verifier.getRedundancies(model,
				constraitsToVerify);
		assertTrue(redundancies.size() == 3);

	}

	@Test
	public void allDeadElements() {
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalTesis.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());
		// Make input DTO
		VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();

		// Set transformed variability model
		verifierInDTO.setVariabilityModel(variabilityModel);
		// Set Prolog editor type
		verifierInDTO.setSolverEditorType(SolverEditorType.SWI_PROLOG);

		IntDefectsVerifier verifier = new DefectsVerifier(verifierInDTO);
		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		List<Defect> deadElements = verifier
				.getDeadElements(model, identifiers);
		assertTrue(deadElements.size() == 10);

	}

	@Test
	public void allFalseOptional() {
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalTesis.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());
		// Make input DTO
		VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();

		// Set transformed variability model
		verifierInDTO.setVariabilityModel(variabilityModel);
		// Set Prolog editor type
		verifierInDTO.setSolverEditorType(SolverEditorType.SWI_PROLOG);

		IntDefectsVerifier verifier = new DefectsVerifier(verifierInDTO);
		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);

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

		List<Defect> falseOptionalElements = verifier.getFalseOptionalElements(
				model, identifiersToVerify);
		assertTrue(falseOptionalElements.size() == 2);

	}

	@Test
	public void isFalseOptional() {
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalTesis.sxfm");
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());
		// Make input DTO
		VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();

		// Set transformed variability model
		verifierInDTO.setVariabilityModel(variabilityModel);
		// Set Prolog editor type
		verifierInDTO.setSolverEditorType(SolverEditorType.SWI_PROLOG);

		IntDefectsVerifier verifier = new DefectsVerifier(verifierInDTO);
		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		Identifier identify = null;
		Iterator<Identifier> iterator = identifiers.iterator();
		while (iterator.hasNext()) {

			identify = (Identifier) iterator.next();
			if (identify.getId() == "Archivo") {
				break;
			}
		}
		FalseOptionalElement falseOptional = (FalseOptionalElement) verifier
				.isFalseOptionalElement(model, identify);
		assertTrue(falseOptional != null);

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
