package com.variamos.reasoning.defectAnalyzer.core;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.variamos.common.core.exceptions.TransformerException;
import com.variamos.common.model.enums.NotationType;
import com.variamos.common.model.enums.SolverEditorType;
import com.variamos.reasoning.core.transformer.VariabilityModelTransformer;
import com.variamos.reasoning.defectAnalyzer.model.dto.VMTransformerInDTO;
import com.variamos.reasoning.defectAnalyzer.model.transformation.VariabilityModel;

import junit.framework.Assert;

public class VariabilityModelTransformerTest {


	
	public void transformFeatureModel() {

		String modelInputPath = "src/test/resources/testModels/Betty5Features_FeatureModel10.sxfm";
		// Se instancia el transformador
		VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
		transformerInDTO.setNotationType(NotationType.FEATURES_MODELS);
		transformerInDTO.setPathToTransform(modelInputPath);

		VariabilityModel variabilityModel = null;
		VariabilityModelTransformer transformer = new VariabilityModelTransformer(
				transformerInDTO);
		try {
			variabilityModel = transformer
					.transformToVariabilityModel();
			Assert.assertTrue(variabilityModel != null);
			System.out.println("The FM was correctly transformed \n");
			System.out.println("FEATURE MODEL:");
			transformer.printTransformedModelSWIProlog(variabilityModel);
			Assert.assertNotNull(variabilityModel);
			Assert.assertNotNull(variabilityModel.getDependencies());
		} catch (TransformerException e) {
			e.printStackTrace();
			fail();
		}

	}

	
}
