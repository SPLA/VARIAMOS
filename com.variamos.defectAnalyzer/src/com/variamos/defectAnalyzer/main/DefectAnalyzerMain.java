package com.variamos.defectAnalyzer.main;

import com.variamos.core.enums.NotationType;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.defectAnalyzer.defectAnalyzer.DefectAnalyzerController;
import com.variamos.defectAnalyzer.dto.DefectAnalyzerControllerInDTO;
import com.variamos.defectAnalyzer.dto.DefectAnalyzerControllerOutDTO;
import com.variamos.defectAnalyzer.dto.VMTransformerInDTO;
import com.variamos.defectAnalyzer.model.VariabilityModel;
import com.variamos.defectAnalyzer.model.enums.DefectAnalyzerMode;
import com.variamos.defectAnalyzer.transformer.VariabilityModelTransformer;

public class DefectAnalyzerMain {

	public DefectAnalyzerControllerOutDTO analyzeSplotFM(String modelName,
			String modelPath, String outputDirectoryPath,
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO) {
		try {

			DefectAnalyzerController defectAnalyzer = new DefectAnalyzerController();
			
			// Variables
			VariabilityModel variabilityModel = null;

			if (!outputDirectoryPath.endsWith("\\")) {
				// Si el directorio no tiene \ entonces se adiciona
				outputDirectoryPath = outputDirectoryPath + "\\";
			}

		
			// Se instancia el transformador
			VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
			transformerInDTO.setNotationType(NotationType.FEATURES_MODELS);
			transformerInDTO.setPathToTransform(modelPath);
			VariabilityModelTransformer transformer = new VariabilityModelTransformer(
					transformerInDTO);
			variabilityModel = transformer.transformToVariabilityModel();
			
			//Se establece el modelo de variabilidad 
			defectAnalyzerInDTO.setVariabilityModel(variabilityModel);
			DefectAnalyzerControllerOutDTO outDTO = defectAnalyzer
					.analyzeModel(defectAnalyzerInDTO,
							modelName + System.currentTimeMillis(),
							outputDirectoryPath);

			System.out
					.println("El resultado del análisis se exportó exitosamente en la ruta "
							+ outputDirectoryPath);
			return outDTO;
		} catch (FunctionalException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Método para ejecutar el análisis de los defectos de un feature model
	 * desde código ( sin pantalla gráfica)
	 * 
	 * @param modelName
	 * @param modelPath
	 * @throws FunctionalException
	 */

	public DefectAnalyzerControllerOutDTO analyzeSplotFM(String modelName,
			String modelPath, String outputDirectoryPath)
			throws FunctionalException {

		
			// Se instancia el analizador de los defectos
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO = new DefectAnalyzerControllerInDTO();

			// Tipo de solver
			SolverEditorType prologEditorType = SolverEditorType.SWI_PROLOG;

			// PARAMETRIZACIÓN
			// Defectos que se desean verificar
			defectAnalyzerInDTO.setVerifyDeadFeatures(Boolean.TRUE);
			defectAnalyzerInDTO.setVerifyFalseOptionalElement(Boolean.TRUE);
			defectAnalyzerInDTO.setVerifyNonAttainableDomains(Boolean.FALSE);
			defectAnalyzerInDTO.setVerifyFalseProductLine(Boolean.TRUE);
			defectAnalyzerInDTO.setVerifyRedundancies(Boolean.TRUE);

			// Defectos para los que se desean analizar las causas y
			// correcciones
			defectAnalyzerInDTO.setAnalyzeDeadFeatures(Boolean.TRUE);
			defectAnalyzerInDTO.setAnalyzeFalseOptional(Boolean.TRUE);
			defectAnalyzerInDTO.setAnalyzeVoidModel(Boolean.TRUE);
			defectAnalyzerInDTO.setIdentifyCausesFalseProductLine(Boolean.TRUE);
			defectAnalyzerInDTO.setAnalyzeRedundancies(Boolean.TRUE);

			// Modelo transformado, editor de prolog y tipo de identificación:
			// completa o parcial
		
			defectAnalyzerInDTO.setSolverEditorType(prologEditorType);
			defectAnalyzerInDTO
					.setDefectAnalyzerMode(DefectAnalyzerMode.PARTIAL);

			return analyzeSplotFM(modelName, modelPath, outputDirectoryPath,
					defectAnalyzerInDTO);
		
		
	}
}
