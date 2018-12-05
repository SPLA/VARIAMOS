package com.variamos.io.core.importExport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.variamos.common.core.exceptions.FunctionalException;


public class ExportDefectAnalyzer {
	/**
	 * Entrega para cada modelo analizado para cada defecto cuantas causas y
	 * correcciones encontro
	 * 
	 * @param defectAnalyzerControllerOutDTOList
	 * @param directoryName
	 */
	public static void exportarListaModelosResumidaxTamCorrecc(
			List<String> defectAnalyzerControllerOutDTOList,
			String directoryName, String outputDirectoryPath)
			throws FunctionalException {

		// Se valida si existe el directorio
		validatePath(outputDirectoryPath);

		List<List<String>> resultadosAnalisis = new ArrayList<List<String>>();
		HSSFWorkbook resultadosLibro = new HSSFWorkbook();
		int cont = 1;
		// Informacion para guardar
		String titulos[] = { "Modelo", "#carac", "#depen", "#NonTraversal",
				"%NonTraversal", "#traver", "%traver", "tipDef", "def",
				"#caus", "#corr", "tiemp" };

		HSSFSheet hoja = resultadosLibro.createSheet();

		for (String defectAnalyzerControllerOutDTO : defectAnalyzerControllerOutDTOList) {

			for (int i = 0; i < defectAnalyzerControllerOutDTO.length(); i++) {
				List<String> resultadosFila = new ArrayList<String>();
				resultadosAnalisis.add(resultadosFila);

			}
			cont++;
		}
		// Se guarda la hoja xls creada
		String resuladosPath = outputDirectoryPath + directoryName + "Resumida"
				+ System.currentTimeMillis() + ".xls";
		ExportUtil.saveXls(resultadosLibro, resuladosPath);
	}

	private static void validatePath(String outputDirectoryPath)
			throws FunctionalException {
		File file = new File(outputDirectoryPath);
		if (!file.exists()) {
			// Si no existe el directorio se lanza una excepcion
			throw new FunctionalException("El directorio de salida "
					+ outputDirectoryPath + " no existe");
		}
	}
}

