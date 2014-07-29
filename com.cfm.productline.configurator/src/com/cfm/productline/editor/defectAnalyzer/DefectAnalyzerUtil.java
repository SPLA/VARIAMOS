package com.cfm.productline.editor.defectAnalyzer;

import com.cfm.productline.ProductLine;
import com.cfm.productline.defectAnalyzer.VariabilityModelVerifier;
import com.cfm.productline.dto.VMAnalyzerInDTO;
import com.cfm.productline.dto.VMTransformerInDTO;
import com.cfm.productline.exceptions.TransformerException;
import com.cfm.productline.model.defectAnalyzerModel.VariabilityModel;
import com.cfm.productline.model.enums.NotationType;
import com.cfm.productline.model.enums.SolverEditorType;
import com.cfm.productline.transformer.VariabilityModelTransformer;

public class DefectAnalyzerUtil {

	public static VariabilityModel transformProductLine(ProductLine productLine)
			throws TransformerException {

		VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
		transformerInDTO
				.setNotationType(NotationType.PRODUCT_LINE);
		transformerInDTO.setProductLineConfigurator(productLine);

		VariabilityModelTransformer transformer = new VariabilityModelTransformer(
				transformerInDTO);

		// 1. TRANSFORM INPUT MODEL
		VariabilityModel variabilityModel = transformer
				.transformToVariabilityModel();
		return variabilityModel;

	}

	public static VariabilityModelVerifier createVerifierClass(
			ProductLine productLine, SolverEditorType prologEditorType)
			throws TransformerException {

		VariabilityModel variabilityModel = transformProductLine(productLine);

		// Make input verifier inDTO
		VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
		// Transfomed variability model
		verifierInDTO.setVariabilityModel(variabilityModel);
		// Prolog editor type
		verifierInDTO.setPrologEditorType(prologEditorType);

		// CREATE VERIFIER MAIN CLASS
		VariabilityModelVerifier verifier = new VariabilityModelVerifier(
				verifierInDTO);

		return verifier;
	}

}
