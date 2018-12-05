package com.variamos.reasoning.util;

//FIXME: Fix this class to use functionalities defined into the I/O project
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//
//import com.variamos.core.exceptions.FunctionalException;
//import com.variamos.reasoning.defectAnalyzer.dto.DefectAnalyzerControllerOutDTO;
//import com.variamos.reasoning.defectAnalyzer.model.Dependency;
//import com.variamos.reasoning.defectAnalyzer.model.Diagnosis;
//import com.variamos.reasoning.defectAnalyzer.model.defects.DeadElement;
//import com.variamos.reasoning.defectAnalyzer.model.defects.FalseOptionalElement;
//import com.variamos.reasoning.defectAnalyzer.model.defects.FalseProductLine;
//import com.variamos.reasoning.defectAnalyzer.model.defects.Redundancy;
//import com.variamos.reasoning.defectAnalyzer.model.defects.VoidModel;


public class ExportDefectAnalyzer {

	/*public static void exportListaModelosDetalleAnalisis(
			List<DefectAnalyzerControllerOutDTO> defectAnalyzerControllerOutDTOList,
			String directoryName, String outputDirectoryPath)
			throws FunctionalException {

		// Se valida si existe el directorio
		validatePath(outputDirectoryPath);

		List<List<String>> resultadosAnalisis = new ArrayList<List<String>>();
		HSSFWorkbook resultadosLibro = new HSSFWorkbook();
		int cont = 1;
		// Información para guardar
		String titulos[] = { "Modelo", "#feat", "#depen", "#NonTraversal",
				"%NonTraversal", "#traver", "%traver", "tipoDefecto",
				"Defecto", "TamCorrec", "#Causas", "Tiempo", "Correc",
				"Causas", "timeOverCorr", "timeOverCauses" };

		HSSFSheet hoja = resultadosLibro.createSheet();

		for (DefectAnalyzerControllerOutDTO defectAnalyzerControllerOutDTO : defectAnalyzerControllerOutDTOList) {

			for (int i = 0; i < defectAnalyzerControllerOutDTO
					.getAllDiagnostics().size(); i++) {
				Diagnosis diagnostic = defectAnalyzerControllerOutDTO
						.getAllDiagnostics().get(i);

				for (int j = 0; j < diagnostic.getCorrectionSubsets().size(); j++) {
					List<String> resultadosFila = new ArrayList<String>();
					List<Dependency> correction = diagnostic
							.getCorrectionSubsets().get(j);
					resultadosFila.add(defectAnalyzerControllerOutDTO
							.getVariabilityModel().getName());
					resultadosFila
							.add(Integer
									.toString(defectAnalyzerControllerOutDTO
											.getVariabilityModel()
											.getNumbeOfFeatures()));
					resultadosFila.add(Integer
							.toString(defectAnalyzerControllerOutDTO
									.getVariabilityModel()
									.getNumberOfDependencies()));
					resultadosFila.add(Integer
							.toString(defectAnalyzerControllerOutDTO
									.getVariabilityModel()
									.getNumberOfNonTrasversalDependencies()));
					resultadosFila.add(Integer
							.toString(defectAnalyzerControllerOutDTO
									.getVariabilityModel()
									.getPercentageNonTraversalDependencies()));
					resultadosFila.add(Integer
							.toString(defectAnalyzerControllerOutDTO
									.getVariabilityModel()
									.getNumberOfTraversalDependencies()));
					resultadosFila.add(Integer
							.toString(defectAnalyzerControllerOutDTO
									.getVariabilityModel()
									.getPercentageTraversalDependencies()));
					resultadosFila.add(diagnostic.getDefect().getDefectType()
							.name());
					resultadosFila.add(diagnostic.getDefect().getId());
					resultadosFila.add(Integer.toString(correction.size()));
					resultadosFila.add("");
					resultadosFila
							.add(Long.toString(defectAnalyzerControllerOutDTO
									.getTime()));
					resultadosFila.add(correction.toString());
					resultadosFila.add("");
					resultadosFila.add(Boolean.toString(diagnostic
							.isTimeOverCorrections()));
					resultadosAnalisis.add(resultadosFila);
				}

				for (int j = 0; j < diagnostic.getCauses().size(); j++) {
					List<String> resultadosFila = new ArrayList<String>();
					List<Dependency> cause = diagnostic.getCauses().get(j);
					resultadosFila.add(defectAnalyzerControllerOutDTO
							.getVariabilityModel().getName());
					resultadosFila
							.add(Integer
									.toString(defectAnalyzerControllerOutDTO
											.getVariabilityModel()
											.getNumbeOfFeatures()));
					resultadosFila.add(Integer
							.toString(defectAnalyzerControllerOutDTO
									.getVariabilityModel()
									.getNumberOfDependencies()));
					resultadosFila.add(Integer
							.toString(defectAnalyzerControllerOutDTO
									.getVariabilityModel()
									.getNumberOfNonTrasversalDependencies()));
					resultadosFila.add(Integer
							.toString(defectAnalyzerControllerOutDTO
									.getVariabilityModel()
									.getPercentageNonTraversalDependencies()));
					resultadosFila.add(Integer
							.toString(defectAnalyzerControllerOutDTO
									.getVariabilityModel()
									.getNumberOfTraversalDependencies()));
					resultadosFila.add(Integer
							.toString(defectAnalyzerControllerOutDTO
									.getVariabilityModel()
									.getPercentageTraversalDependencies()));
					resultadosFila.add(diagnostic.getDefect().getDefectType()
							.name());
					resultadosFila.add(diagnostic.getDefect().getId());
					resultadosFila.add("");
					resultadosFila.add(Integer.toString(cause.size()));
					resultadosFila
							.add(Long.toString(defectAnalyzerControllerOutDTO
									.getTime()));
					resultadosFila.add("");
					resultadosFila.add(cause.toString());
					resultadosFila.add("");
					resultadosFila.add(Boolean.toString(diagnostic
							.isTimeOverCauses()));
					resultadosAnalisis.add(resultadosFila);
				}

			}
			cont++;

		}

		// Se pone información en la hoja
		ExportUtil.adicionarInfoHoja(Arrays.asList(titulos), 0, hoja,
				resultadosAnalisis, 0, cont);

		// Se guarda la hoja xls creada
		String resuladosPath = outputDirectoryPath + directoryName
				+ "detallado" + System.currentTimeMillis() + ".xls";
		ExportUtil.guardarXls(resultadosLibro, resuladosPath);
	}*/

	/**
	 * Entrega para cada modelo analizado para cada defecto cuantas causas y
	 * correcciones encontró
	 * 
	 * @param defectAnalyzerControllerOutDTOList
	 * @param directoryName
	 */
	/*public static void exportarListaModelosResumidaxTamCorrecc(
			List<DefectAnalyzerControllerOutDTO> defectAnalyzerControllerOutDTOList,
			String directoryName, String outputDirectoryPath)
			throws FunctionalException {

		// Se valida si existe el directorio
		validatePath(outputDirectoryPath);

		List<List<String>> resultadosAnalisis = new ArrayList<List<String>>();
		HSSFWorkbook resultadosLibro = new HSSFWorkbook();
		int cont = 1;
		// Información para guardar
		String titulos[] = { "Modelo", "#carac", "#depen", "#NonTraversal",
				"%NonTraversal", "#traver", "%traver", "tipDef", "def",
				"#caus", "#corr", "tiemp" };

		HSSFSheet hoja = resultadosLibro.createSheet();

		for (DefectAnalyzerControllerOutDTO defectAnalyzerControllerOutDTO : defectAnalyzerControllerOutDTOList) {

			for (int i = 0; i < defectAnalyzerControllerOutDTO
					.getAllDiagnostics().size(); i++) {
				Diagnosis diagnostic = defectAnalyzerControllerOutDTO
						.getAllDiagnostics().get(i);

				List<String> resultadosFila = new ArrayList<String>();
				resultadosFila.add(defectAnalyzerControllerOutDTO
						.getVariabilityModel().getName());
				resultadosFila.add(Integer
						.toString(defectAnalyzerControllerOutDTO
								.getVariabilityModel().getNumbeOfFeatures()));
				resultadosFila.add(Integer
						.toString(defectAnalyzerControllerOutDTO
								.getVariabilityModel()
								.getNumberOfDependencies()));
				resultadosFila.add(Integer
						.toString(defectAnalyzerControllerOutDTO
								.getVariabilityModel()
								.getNumberOfNonTrasversalDependencies()));
				resultadosFila.add(Integer
						.toString(defectAnalyzerControllerOutDTO
								.getVariabilityModel()
								.getPercentageNonTraversalDependencies()));
				resultadosFila.add(Integer
						.toString(defectAnalyzerControllerOutDTO
								.getVariabilityModel()
								.getNumberOfTraversalDependencies()));
				resultadosFila.add(Integer
						.toString(defectAnalyzerControllerOutDTO
								.getVariabilityModel()
								.getPercentageTraversalDependencies()));
				resultadosFila.add(diagnostic.getDefect().getDefectType()
						.name());
				resultadosFila.add(diagnostic.getDefect().getId());
				resultadosFila.add(Integer.toString(diagnostic.getCauses()
						.size()));
				resultadosFila.add(Integer.toString(diagnostic
						.getCorrectionSubsets().size()));
				resultadosFila.add(Long.toString(defectAnalyzerControllerOutDTO
						.getTime()));

				resultadosAnalisis.add(resultadosFila);

			}
			cont++;

		}

		// Se pone información en la hoja
		ExportUtil.adicionarInfoHoja(Arrays.asList(titulos), 0, hoja,
				resultadosAnalisis, 0, cont);

		// Se guarda la hoja xls creada
		String resuladosPath = outputDirectoryPath + directoryName + "Resumida"
				+ System.currentTimeMillis() + ".xls";
		ExportUtil.guardarXls(resultadosLibro, resuladosPath);
	}*/

	/**
	 * Resume los resultados de todos los modelos que estaban en @param
	 * directory name
	 * 
	 * @param defectAnalyzerControllerOutDTOList
	 * @param directoryName
	 */
	/*public static void exportaModelosAnalizadosOverview(
			List<DefectAnalyzerControllerOutDTO> defectAnalyzerControllerOutDTOList,
			String directoryName, String outputDirectoryPath)
			throws FunctionalException {

		// Se valida si existe el directorio
		validatePath(outputDirectoryPath);

		List<List<String>> resultadosAnalisis = new ArrayList<List<String>>();
		HSSFWorkbook resultadosLibro = new HSSFWorkbook();
		int cont = 1;
		// Información para guardar
		String titulos[] = { "modelo", "#caract", "#depen", "#NonTraversal",
				"%NonTraversal", "#traver", "%traver", "#def", "vac", "FPL",
				"CM", "FO", "Redun", "#caus", "#corr", "#causCom",
				"#causNoCom", "#corrCom", "#corrNoCom", "tiempo" };

		HSSFSheet hoja = resultadosLibro.createSheet();

		for (DefectAnalyzerControllerOutDTO defectAnalyzerControllerOutDTO : defectAnalyzerControllerOutDTOList) {
			int todosDefectos = 0;
			int caracMuertas = 0;
			int carcFalsOpc = 0;
			int modeloVacio = 0;
			int falsoLP = 0;
			int redun = 0;
			int numCorrecciones = 0;
			int numCausas = 0;
			int causasComunes = 0;
			int causasNoComunes = 0;
			int corrComunes = 0;
			int corrNoComunes = 0;

			causasComunes = defectAnalyzerControllerOutDTO
					.getClassifiedCauses().getCommonDiagnosis().size();
			causasNoComunes = defectAnalyzerControllerOutDTO
					.getClassifiedCauses().getNoCommonDiagnosis().size();
			corrComunes = defectAnalyzerControllerOutDTO
					.getClassifiedCorrections().getCommonDiagnosis().size();
			corrNoComunes = defectAnalyzerControllerOutDTO
					.getClassifiedCorrections().getNoCommonDiagnosis().size();

			for (int i = 0; i < defectAnalyzerControllerOutDTO
					.getAllDiagnostics().size(); i++) {
				Diagnosis diagnostic = defectAnalyzerControllerOutDTO
						.getAllDiagnostics().get(i);
				todosDefectos++;
				if (diagnostic.getDefect() instanceof VoidModel) {
					modeloVacio++;
				} else if (diagnostic.getDefect() instanceof FalseProductLine) {
					falsoLP++;
				} else if (diagnostic.getDefect() instanceof DeadElement) {
					caracMuertas++;
				} else if (diagnostic.getDefect() instanceof FalseOptionalElement) {
					carcFalsOpc++;
				} else if (diagnostic.getDefect() instanceof Redundancy) {
					redun++;
				}
				numCausas += diagnostic.getCauses().size();
				numCorrecciones += diagnostic.getCorrectionSubsets().size();

			}
			List<String> resultadosFila = new ArrayList<String>();
			resultadosFila.add(defectAnalyzerControllerOutDTO
					.getVariabilityModel().getName());

			resultadosFila.add(Integer.toString(defectAnalyzerControllerOutDTO
					.getVariabilityModel().getNumbeOfFeatures()));
			resultadosFila.add(Integer.toString(defectAnalyzerControllerOutDTO
					.getVariabilityModel().getNumberOfDependencies()));
			resultadosFila.add(Integer.toString(defectAnalyzerControllerOutDTO
					.getVariabilityModel()
					.getNumberOfNonTrasversalDependencies()));
			resultadosFila.add(Integer.toString(defectAnalyzerControllerOutDTO
					.getVariabilityModel()
					.getPercentageNonTraversalDependencies()));
			resultadosFila.add(Integer.toString(defectAnalyzerControllerOutDTO
					.getVariabilityModel().getNumberOfTraversalDependencies()));
			resultadosFila.add(Integer
					.toString(defectAnalyzerControllerOutDTO
							.getVariabilityModel()
							.getPercentageTraversalDependencies()));

			resultadosFila.add(Integer.toString(todosDefectos));
			resultadosFila.add(Integer.toString(modeloVacio));
			resultadosFila.add(Integer.toString(falsoLP));
			resultadosFila.add(Integer.toString(caracMuertas));
			resultadosFila.add(Integer.toString(carcFalsOpc));
			resultadosFila.add(Integer.toString(redun));

			resultadosFila.add(Integer.toString(numCausas));
			resultadosFila.add(Integer.toString(numCorrecciones));
			resultadosFila.add(Integer.toString(causasComunes));
			resultadosFila.add(Integer.toString(causasNoComunes));
			resultadosFila.add(Integer.toString(corrComunes));
			resultadosFila.add(Integer.toString(corrNoComunes));
			resultadosFila.add(Long.toString(defectAnalyzerControllerOutDTO
					.getTime()));
			resultadosAnalisis.add(resultadosFila);

		}

		// Se pone información en la hoja
		ExportUtil.adicionarInfoHoja(Arrays.asList(titulos), 0, hoja,
				resultadosAnalisis, 0, cont);

		// Se guarda la hoja xls creada
		String resuladosPath = outputDirectoryPath + directoryName + "Overview"
				+ System.currentTimeMillis() + ".xls";
		ExportUtil.guardarXls(resultadosLibro, resuladosPath);
	}

	public static void exportarListaModelosComparacionFama(
			List<DefectAnalyzerControllerOutDTO> defectAnalyzerControllerOutDTOList,
			String directoryName, String outputDirectoryPath)
			throws FunctionalException {

		// Se valida si existe el directorio
		validatePath(outputDirectoryPath);

		List<List<String>> resultadosAnalisis = new ArrayList<List<String>>();
		HSSFWorkbook resultadosLibro = new HSSFWorkbook();
		int cont = 1;
		// Información para guardar
		String titulos[] = { "modelo", "#caract", "#depen", "#NonTraversal",
				"%NonTraversal", "#traver", "%traver", "#def", "vac", "CM",
				"FO", "#corr", "tiempo", "correccionesMayores" };

		HSSFSheet hoja = resultadosLibro.createSheet();

		for (DefectAnalyzerControllerOutDTO defectAnalyzerControllerOutDTO : defectAnalyzerControllerOutDTOList) {
			int todosDefectos = 0;
			int caracMuertas = 0;
			int carcFalsOpc = 0;
			int modeloVacio = 0;
			int numCorrecciones = 0;
			boolean correccionesMayorTamano = Boolean.FALSE;

			for (int i = 0; i < defectAnalyzerControllerOutDTO
					.getAllDiagnostics().size(); i++) {
				Diagnosis diagnostic = defectAnalyzerControllerOutDTO
						.getAllDiagnostics().get(i);
				todosDefectos++;
				if (diagnostic.getDefect() instanceof VoidModel) {
					modeloVacio++;
				} else if (diagnostic.getDefect() instanceof DeadElement) {
					caracMuertas++;
				} else if (diagnostic.getDefect() instanceof FalseOptionalElement) {
					carcFalsOpc++;
				}
				numCorrecciones += diagnostic.getCorrectionSubsets().size();
				if (diagnostic.isCorrecMayoresUno()) {
					correccionesMayorTamano = Boolean.TRUE;
				}

			}
			List<String> resultadosFila = new ArrayList<String>();
			resultadosFila.add(defectAnalyzerControllerOutDTO
					.getVariabilityModel().getName());

			resultadosFila.add(Integer.toString(defectAnalyzerControllerOutDTO
					.getVariabilityModel().getNumbeOfFeatures()));
			resultadosFila.add(Integer.toString(defectAnalyzerControllerOutDTO
					.getVariabilityModel().getNumberOfDependencies()));
			resultadosFila.add(Integer.toString(defectAnalyzerControllerOutDTO
					.getVariabilityModel()
					.getNumberOfNonTrasversalDependencies()));
			resultadosFila.add(Integer.toString(defectAnalyzerControllerOutDTO
					.getVariabilityModel()
					.getPercentageNonTraversalDependencies()));
			resultadosFila.add(Integer.toString(defectAnalyzerControllerOutDTO
					.getVariabilityModel().getNumberOfTraversalDependencies()));
			resultadosFila.add(Integer
					.toString(defectAnalyzerControllerOutDTO
							.getVariabilityModel()
							.getPercentageTraversalDependencies()));

			resultadosFila.add(Integer.toString(todosDefectos));
			resultadosFila.add(Integer.toString(modeloVacio));
			resultadosFila.add(Integer.toString(caracMuertas));
			resultadosFila.add(Integer.toString(carcFalsOpc));
			resultadosFila.add(Integer.toString(numCorrecciones));
			resultadosFila.add(Long.toString(defectAnalyzerControllerOutDTO
					.getTime()));
			resultadosFila.add(Boolean.toString(correccionesMayorTamano));
			resultadosAnalisis.add(resultadosFila);

		}

		// Se pone información en la hoja
		ExportUtil.adicionarInfoHoja(Arrays.asList(titulos), 0, hoja,
				resultadosAnalisis, 0, cont);

		// Se guarda la hoja xls creada
		String resuladosPath = outputDirectoryPath + "compFama" + directoryName
				+ "-" + System.currentTimeMillis() + ".xls";
		ExportUtil.guardarXls(resultadosLibro, resuladosPath);
	}

	private static void validatePath(String outputDirectoryPath)
			throws FunctionalException {
		File file = new File(outputDirectoryPath);
		if (!file.exists()) {
			// Si no existe el directorio se lanza una excepción
			throw new FunctionalException("El directorio de salida "
					+ outputDirectoryPath + " no existe");
		}
	}*/
}
