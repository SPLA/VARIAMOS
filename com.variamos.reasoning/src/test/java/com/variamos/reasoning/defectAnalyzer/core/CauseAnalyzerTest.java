package com.variamos.reasoning.defectAnalyzer.core;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.common.model.enums.NotationType;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.core.HlclUtil;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.reasoning.core.transformer.VariabilityModelTransformer;
import com.variamos.reasoning.defectAnalyzer.model.defects.DeadElement;
import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.CauCos;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.DefectAnalyzerModeEnum;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.Diagnosis;
import com.variamos.reasoning.defectAnalyzer.model.dto.DefectAnalyzerResultDTO;
import com.variamos.reasoning.defectAnalyzer.model.dto.VMTransformerInDTO;
import com.variamos.reasoning.defectAnalyzer.model.transformation.Dependency;
import com.variamos.reasoning.defectAnalyzer.model.transformation.VariabilityModel;
import com.variamos.reasoning.util.ConstraintRepresentationUtil;



public class CauseAnalyzerTest {

	public void testGetCorrectionsOneDefect() {

		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalTesis.sxfm");
		Collection<IntBooleanExpression> modelExpression = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						new HashMap<Long, Dependency>());
		Collection<IntBooleanExpression> fixedExpressions = ConstraintRepresentationUtil
				.dependencyToExpressionList(new HashMap<Long, Dependency>(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(modelExpression);
		HlclProgram fixedConstraints = ConstraintRepresentationUtil
				.expressionToHlclProgram(fixedExpressions);

		IntDefectsVerifier verifier = new DefectsVerifier(model);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		DeadElement deadElement;
		try {
			deadElement = (DeadElement) verifier.isDeadElement(identifiers
					.iterator().next());
			IntCauCosAnalyzer caucosAnalyzer = new CauCosAnayzer();
			List<CauCos> corrections = caucosAnalyzer.getCorrections(
					deadElement, model, fixedConstraints,
					DefectAnalyzerModeEnum.COMPLETE);
			assertTrue(corrections.size() == 12);

			// 3. PRINT RESULTS
			System.out
					.println("_________________ RESULTADOS____________________");
			System.out.println("_________________ MCS____________________");
			int i = 1;
			for (CauCos correction : corrections) {

				System.out.println("MCS " + correction.getId() + " :"
						+ correction.getElements().toString());
				i++;
			}

		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	public void testGetCaucos() {

		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalTesis.sxfm");
		Collection<IntBooleanExpression> modelExpression = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						new HashMap<Long, Dependency>());
		Collection<IntBooleanExpression> fixedExpressions = ConstraintRepresentationUtil
				.dependencyToExpressionList(new HashMap<Long, Dependency>(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(modelExpression);
		HlclProgram fixedConstraints = ConstraintRepresentationUtil
				.expressionToHlclProgram(fixedExpressions);

		IntDefectsVerifier verifier = new DefectsVerifier(model);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		DeadElement deadElement;
		try {
			deadElement = (DeadElement) verifier.isDeadElement(identifiers
					.iterator().next());
			IntCauCosAnalyzer caucosAnalyzer = new CauCosAnayzer();
			Diagnosis diagnosis = caucosAnalyzer.getCauCos(deadElement, model,
					fixedConstraints, DefectAnalyzerModeEnum.COMPLETE);
			assertTrue(diagnosis.getCorrections().size() == 12);
			assertTrue(diagnosis.getCauses().size() == 3);

			System.out.println("corrections time : "
					+ diagnosis.getCorrectionsProcessingTime()
					+ " causes time " + diagnosis.getCausesProcessingTime());

		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	public void testGetCaucosDefects() {

		VariabilityModel variabilityModel = transformFeatureModel("src/test/resources/testModels/WebPortalTesis.sxfm");
		Collection<IntBooleanExpression> modelExpression = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						new HashMap<Long, Dependency>());
		Collection<IntBooleanExpression> fixedExpressions = ConstraintRepresentationUtil
				.dependencyToExpressionList(new HashMap<Long, Dependency>(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(modelExpression);
		HlclProgram fixedConstraints = ConstraintRepresentationUtil
				.expressionToHlclProgram(fixedExpressions);

		IntDefectsVerifier verifier = new DefectsVerifier(model);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		List<Defect> deadElements;
		try {
			deadElements = verifier.getDeadElements(identifiers);
			IntCauCosAnalyzer caucosAnalyzer = new CauCosAnayzer();
			DefectAnalyzerResultDTO result = caucosAnalyzer.getCauCos(
					deadElements, model, fixedConstraints,
					DefectAnalyzerModeEnum.CAUSES_CORRECTIONS);
			assertTrue(result.getAllDiagnosis().size() == 10);
			System.out.println("total time : " + result.getTotalTime());

		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (InterruptedException e) {
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
		} catch (FunctionalException e) {
			fail(e.getMessage());

		}

		return null;
	}

}
