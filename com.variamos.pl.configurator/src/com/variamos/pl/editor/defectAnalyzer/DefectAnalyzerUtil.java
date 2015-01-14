package com.variamos.pl.editor.defectAnalyzer;

import com.cfm.productline.ProductLine;
import com.variamos.core.enums.NotationType;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.TransformerException;
import com.variamos.defectAnalyzer.defectAnalyzer.DefectsVerifier;
import com.variamos.defectAnalyzer.dto.VMAnalyzerInDTO;
import com.variamos.defectAnalyzer.dto.VMTransformerInDTO;
import com.variamos.defectAnalyzer.model.VariabilityModel;
import com.variamos.defectAnalyzer.transformer.VariabilityModelTransformer;

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

	public static DefectsVerifier createVerifierClass(
			ProductLine productLine, SolverEditorType prologEditorType)
			throws TransformerException {

		VariabilityModel variabilityModel = transformProductLine(productLine);

		// Make input verifier inDTO
		VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
		// Transfomed variability model
		verifierInDTO.setVariabilityModel(variabilityModel);
		// Prolog editor type
		verifierInDTO.setSolverEditorType(prologEditorType);

		// CREATE VERIFIER MAIN CLASS
		DefectsVerifier verifier = new DefectsVerifier(
				verifierInDTO);

		return verifier;
	}

}
