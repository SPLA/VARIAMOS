package com.variamos.defectAnalyzer.main;

import java.util.ArrayList;
import java.util.List;

import com.variamos.core.enums.NotationType;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.core.exceptions.TransformerException;
import com.variamos.defectAnalyzer.defectAnalyzer.VariabilityModelVerifier;
import com.variamos.defectAnalyzer.dto.VMAnalyzerInDTO;
import com.variamos.defectAnalyzer.dto.VMTransformerInDTO;
import com.variamos.defectAnalyzer.model.VariabilityModel;
import com.variamos.defectAnalyzer.model.defects.Defect;
import com.variamos.defectAnalyzer.model.defects.NonAttainableDomain;
import com.variamos.defectAnalyzer.transformer.VariabilityModelTransformer;

public class UseVerifierExample {

	public static void main(String[] args) {

		// Load variabily model ( now SPLOT format sxfm or splx extension)
		String modelInputPath = "pl/causeAnalyzer/Aircraft_13_fm.xml";

		// Define Prolog editor type ( GNU or SWI)
		SolverEditorType prologEditorType = SolverEditorType.SWI_PROLOG;

		// 1. TRANSFORM INPUT MODEL
		VariabilityModel variabilityModel = transformModel(modelInputPath,
				prologEditorType);

		// 2. VERIFY DEFECTS IN TRANSFORMED MODEL
		if (variabilityModel != null) {

			// Make input verifier inDTO
			VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
			// Transfomed variability model
			verifierInDTO.setVariabilityModel(variabilityModel);
			// Prolog editor type
			verifierInDTO.setPrologEditorType(prologEditorType);

			// CREATE VERIFIER MAIN CLASS
			VariabilityModelVerifier verifier = new VariabilityModelVerifier(
					verifierInDTO);

			// DEAD ELEMENTS
			// Dead features
			List<Defect> deadElements = new ArrayList<Defect>();
			try {
				// Start verification operations
				// VOID MODEL
				Defect isVoid = verifier.isVoid();

				// FALSE PRODUCT LINE
				Defect isFalseProductLine = verifier.isFalsePLM();

				deadElements = verifier.identifyDeadFeatures(verifierInDTO
						.getVariabilityModel().getElements());

				// False optional elements
				List<Defect> falseOptionalFeatures = new ArrayList<Defect>();
				// If the variability model has optional elements we verify its
				// false optional elements
				if (variabilityModel.getOptionalVariabilityElements() != null
						&& !variabilityModel.getOptionalVariabilityElements()
								.isEmpty()) {

					falseOptionalFeatures = verifier
							.identifyFalseOptionalFeatures(variabilityModel
									
									.getOptionalVariabilityElements());

				}

				// Domain not attainables
				List<Defect> nonAttainableDomainsList = verifier
						.identifyNonAttainableDomains(verifierInDTO
								.getVariabilityModel().getElements());

				// 3. PRINT RESULTS
				System.out
						.println("_________________ VERIFICATION RESULTS_____________________");
				System.out.println(" VOID MODEL: " + isVoid);
				System.out
						.println(" FALSE PRODUCT LINE: " + isFalseProductLine);

				for (Defect deadElement : deadElements) {
					System.out.println("DEAD FEATURE " + deadElement.getId());
				}

				for (Defect falseOptionalFeature : falseOptionalFeatures) {
					System.out.println("FALSE OPTIONAL FEATURE "
							+ falseOptionalFeature.getId());
				}

				for (Defect nonAttainableDomain : nonAttainableDomainsList) {

					Integer nonAttainableValue = ((NonAttainableDomain) nonAttainableDomain)
							.getNotAttainableDomain();
					System.out.println("NON ATTAINABLE DOMAIN "
							+ nonAttainableDomain.getId() + " VALUE : "
							+ nonAttainableValue);
				}
			} catch (FunctionalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	private static VariabilityModel transformModel(String modelInputPath,
			SolverEditorType prologEditorType) {
		// Create VMTransformerInDTO with input parameters
		VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
		transformerInDTO.setNotationType(NotationType.FEATURES_MODELS);
		transformerInDTO.setPathToTransform(modelInputPath);

		VariabilityModel variabilityModel = null;

		VariabilityModelTransformer transformer = new VariabilityModelTransformer(
				transformerInDTO);
		try {
			// 1. TRANSFORM INPUT MODEL
			variabilityModel = transformer
					.transformToVariabilityModel();
			return variabilityModel;
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return null;

	}

}
