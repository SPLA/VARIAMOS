package com.cfm.productline.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.cfm.productline.defectAnalyzer.DefectAnalyzerController;
import com.cfm.productline.dto.DefectAnalyzerControllerOutDTO;
import com.cfm.productline.dto.VMTransformerInDTO;
import com.cfm.productline.exceptions.FunctionalException;
import com.cfm.productline.exceptions.TransformerException;
import com.cfm.productline.model.defectAnalyzerModel.VariabilityModel;
import com.cfm.productline.model.enums.NotationType;
import com.cfm.productline.model.enums.SolverEditorType;
import com.cfm.productline.transformer.VariabilityModelTransformer;
import com.cfm.productline.util.ExportDefectAnalyser;
import com.cfm.productline.util.FileUtils;

/**
 * @author LuFe
 * 
 */
public class Main1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// analyzeDefectsFeatureModelSPLOTModel(
		// "test/testModels/Betty25Features_FeatureModel1.splx",
		// "Betty25Features_FeatureModel1");
		// String folderName = "FamaModelosComparar5-15";
		// String folderName = "ModelosProbadosExactitud";
		String folderName = "ModelosProbadosExactitud";
		analizarListaModelos("pl/" + folderName, folderName, "lala/");

	}

	private static void analyzeSWIPrologFile() {
		// Variable del modelo a analizar
		String modelInputPath = "pl/causeAnalyzer/UNIX modelSWI.pl";
		SolverEditorType prologEditorType = SolverEditorType.SWI_PROLOG;
		SolverEditorType prologEditorTypeFileToTransform = SolverEditorType.SWI_PROLOG;
		// Se instancia el transformador
		VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
		transformerInDTO.setNotationType(NotationType.PROLOG);
		transformerInDTO.setPathToTransform(modelInputPath);
		transformerInDTO
				.setPrologEditorTypeFileToTransform(prologEditorTypeFileToTransform);
		VariabilityModel variabilityModel = null;
		DefectAnalyzerController defectAnalyzer = null;
		VariabilityModelTransformer transformer = new VariabilityModelTransformer(
				transformerInDTO);
		try {
			variabilityModel = transformer.transformToVariabilityModel();
		} catch (TransformerException e) {
			e.printStackTrace();

		}

		System.out.println("The FM was correctly transformed \n");
		// // Se instancia el analizador de los defectos
		// DefectAnalyzerControllerInDTO defectAnalyzerInDTO = new
		// DefectAnalyzerControllerInDTO();
		//
		// // Defectos que se desean verificar
		// defectAnalyzerInDTO.setVerifyDeadFeatures(Boolean.TRUE);
		// defectAnalyzerInDTO.setVerifyFalseOptionalElement(Boolean.TRUE);
		// defectAnalyzerInDTO.setVerifyNonAttainableDomains(Boolean.TRUE);
		// defectAnalyzerInDTO.setVerifyFalseOptionalProductLine(Boolean.FALSE);
		//
		// // Defectos para los que se desean analizar las causas
		// defectAnalyzerInDTO.setIdentifyCausesDeadFeatures(Boolean.TRUE);
		// defectAnalyzerInDTO.setIdentifyCausesFalseOptional(Boolean.TRUE);
		//
		// defectAnalyzerInDTO.setTransformerOutDTO(transformeOutDTO);
		// defectAnalyzerInDTO.setPrologEditorType(prologEditorType);
		// defectAnalyzerInDTO
		// .setCorrectionSetIdentificationType(CorrectionSetIdentificationType.MINIMUM_CORRECTION_SET);
		// try {
		// defectAnalyzer = new DefectAnalyzerController();
		// defectAnalyzer.analyzeModel(defectAnalyzerInDTO);
		// System.out
		// .println("El resultado del análisis se exportó exitosamente");
		// } catch (Exception e) {
		// e.printStackTrace();
		//
		// }
	}

	/**
	 * Ejemplo de utilización del Transformer,verifier y causeAnalyzer
	 */
	private static void analyzeDefectsFeatureModelProductLineConfiguration() {

		// // Variable del modelo a analizar
		// SXFMReader reader = new SXFMReader();
		// ProductLine pl = null;
		// PrologEditorType prologEditorType = PrologEditorType.GNU_PROLOG;
		// try {
		// pl = reader
		// .readFile("pl/causeAnalyzer/Betty5Features_FeatureModel10.sxfm");
		// // Se instancia el transformador
		// VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
		// transformerInDTO
		// .setNotationType(NotationType.PRODUCT_LINE_CONFIGURATOR);
		// transformerInDTO.setProductLineConfigurator(pl);
		// transformerInDTO.setPrologEditorType(prologEditorType);
		// VMTransformerOutDTO transformeOutDTO = null;
		// DefectAnalyzerController defectAnalyzer = null;
		// VariabilityModelTransformer transformer = new
		// VariabilityModelTransformer(
		// transformerInDTO);
		// transformeOutDTO = transformer
		// .transformVariabililModelToConstraints();
		//
		// System.out.println("The FM was correctly transformed \n");
		// // Se instancia el analizador de los defectos
		// DefectAnalyzerControllerInDTO defectAnalyzerInDTO = new
		// DefectAnalyzerControllerInDTO();
		// // Defectos que se desean verificar
		// defectAnalyzerInDTO.setVerifyDeadFeatures(Boolean.TRUE);
		// defectAnalyzerInDTO.setVerifyFalseOptionalElement(Boolean.TRUE);
		// defectAnalyzerInDTO.setVerifyNonAttainableDomains(Boolean.TRUE);
		// defectAnalyzerInDTO.setVerifyFalseOptionalProductLine(Boolean.TRUE);
		//
		// // Defectos para los que se desean analizar las causas
		// defectAnalyzerInDTO.setVerifyDeadFeatures(Boolean.TRUE);
		//
		// defectAnalyzerInDTO.setPrologEditorType(prologEditorType);
		// defectAnalyzerInDTO.setTransformerOutDTO(transformeOutDTO);
		// defectAnalyzerInDTO
		// .setCorrectionSetIdentificationType(CorrectionSetIdentificationType.CORRECTION_SET_UNTIL_K);
		//
		// defectAnalyzer = new DefectAnalyzerController();
		// defectAnalyzer.analyzeModel(defectAnalyzerInDTO);
		// System.out
		// .println("El resultado del análisis se exportó exitosamente");
		//
		// } catch (FeatureModelException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (TransformerException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (Exception e2) {
		// // TODO Auto-generated catch block
		// e2.printStackTrace();
		// }

	}

	private static void analizarModelo(File file, String outputDirectoryPath) {
		DefectAnalyzerControllerOutDTO defectAnalyzerControllerOutDTO = new DefectAnalyzerControllerOutDTO();
		MainDefectAnalyzer defectAnalyzer = new MainDefectAnalyzer();
		try {
			defectAnalyzerControllerOutDTO = defectAnalyzer
					.analyzeSplotFM(
					file.getPath(), file.getName().replace(".splx", ""),
					outputDirectoryPath);
		} catch (FunctionalException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void analizarListaModelos(String directoryPath,
			String directoryName, String outputDirectoryPath) {
		List<File> fileList = FileUtils.readFileFromDirectory(directoryPath);
		List<DefectAnalyzerControllerOutDTO> defectAnalyzerControllerOutDTOList = new ArrayList<DefectAnalyzerControllerOutDTO>();
		MainDefectAnalyzer defectAnalyzer = new MainDefectAnalyzer();

		try {
			for (File file : fileList) {
				// Se toma el tiempo inicial
				if (file.getPath().endsWith("sxfm")
						|| file.getPath().endsWith("splx")
						|| file.getPath().endsWith("xml")) {
					System.out.println("\n!!Analizando modelo: "
							+ file.getName());
					DefectAnalyzerControllerOutDTO defectAnalyzerControllerOutDTO = new DefectAnalyzerControllerOutDTO();
					defectAnalyzerControllerOutDTO = defectAnalyzer
							.analyzeSplotFM(file.getPath(), file.getName()
									.replace(".splx", ""), outputDirectoryPath);

					defectAnalyzerControllerOutDTOList
							.add(defectAnalyzerControllerOutDTO);
				}

			}
			/* Exporta los resultados a XLS */
			ExportDefectAnalyser.exportListaModelosDetalleAnalisis(
					defectAnalyzerControllerOutDTOList, directoryName,
					outputDirectoryPath);

			ExportDefectAnalyser.exportaModelosAnalizadosOverview(
					defectAnalyzerControllerOutDTOList, directoryName,
					outputDirectoryPath);

			ExportDefectAnalyser.exportarListaModelosResumidaxTamCorrecc(
					defectAnalyzerControllerOutDTOList, directoryName,
					outputDirectoryPath);
			/*
			 * exportarListaModelosComparacionFama(
			 * defectAnalyzerControllerOutDTOList, directoryName,String
			 * outputDirectoryPath);
			 */
		} catch (FunctionalException e) {
			System.out.println(e.getMessage());
		}
	}


}
