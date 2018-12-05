package com.variamos.reasoning.defectAnalyzer.core;

import java.util.ArrayList;
import java.util.List;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.Diagnosis;
import com.variamos.reasoning.defectAnalyzer.model.dto.DefectAnalyzerControllerInDTO;
import com.variamos.reasoning.defectAnalyzer.model.dto.DefectAnalyzerResultDTO;
import com.variamos.reasoning.defectAnalyzer.model.dto.VMAnalyzerInDTO;
import com.variamos.reasoning.defectAnalyzer.model.dto.VMCauseAnalyzerInDTO;
import com.variamos.reasoning.defectAnalyzer.model.dto.VerificationResultDTO;

public class DefectAnalyzerController {

	public DefectAnalyzerController() {
		super();

	}

	/*public DefectAnalyzerControllerOutDTO analyzeModel(
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO, String fileName,
			String outputDirectoryPath) throws FunctionalException {

		long startCorrectionSetTestTime, endCorrectionSetTestTime, totalCorrectionSetTestTime = 0;
		VerificationResult verifierOutDTO = null;
		DefectAnalyzerResult causeAnalizerOutDTO = new DefectAnalyzerResult();
		DefectAnalyzerControllerOutDTO defectAnalyzerControllerOut = new DefectAnalyzerControllerOutDTO();
		defectAnalyzerControllerOut.setVariabilityModel(defectAnalyzerInDTO
				.getVariabilityModel());

		// Se valida que existan los directorios antes de continuar
		File file = new File(outputDirectoryPath);
		if (!file.exists()) {
			// Si no existe el directorio se lanza una excepción
			throw new FunctionalException("output directory "
					+ outputDirectoryPath + " does not exist");
		}

		// Se verifican los defectos del modelo
		verifierOutDTO = verifyDefects(defectAnalyzerInDTO);

		// Se analizan los MCS
		startCorrectionSetTestTime = System.currentTimeMillis();
		causeAnalizerOutDTO = causesAnalyzer(defectAnalyzerInDTO,
				verifierOutDTO);

		// Se clasifican las causa entre comunes y no comunes
		VariabilityModelCausesCorrectionsSorter sorter = new VariabilityModelCausesCorrectionsSorter();
		ClassifiedElement classifiedCauses = sorter.classifyDiagnosis(
				causeAnalizerOutDTO.getAllDiagnosis(),
				ClassificationType.CAUSES);

		// Se clasifican las correciones entre comunes y no comunes
		ClassifiedElement classifiedCorrections = sorter.classifyDiagnosis(
				causeAnalizerOutDTO.getAllDiagnosis(),
				ClassificationType.CORRECTIONS);

		// Se calcula el tiempo final
		endCorrectionSetTestTime = System.currentTimeMillis();
		totalCorrectionSetTestTime = endCorrectionSetTestTime
				- startCorrectionSetTestTime;
		System.out.println("Time: " + totalCorrectionSetTestTime + " mls");

		// Se exportan los resultados
		exportCorrectionCausesSubsets(verifierOutDTO, causeAnalizerOutDTO,
				defectAnalyzerInDTO, totalCorrectionSetTestTime,
				classifiedCauses, classifiedCorrections, fileName,
				outputDirectoryPath);

		// Print output result
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("............................RESULTS............");
		System.out.println("Cantidad de defectos analizados"
				+ causeAnalizerOutDTO.getAllDiagnosis().size());
		printDiagnosis(causeAnalizerOutDTO.getAllDiagnosis());
		printAllClassifiedDiagnosis(classifiedCorrections,
				ClassificationType.CORRECTIONS);
		printAllClassifiedDiagnosis(classifiedCauses, ClassificationType.CAUSES);

		// Información DTO de salida que sirve para analizar xls resumidos
		defectAnalyzerControllerOut.setAllDiagnostics(causeAnalizerOutDTO
				.getAllDiagnosis());
		defectAnalyzerControllerOut.setTime(totalCorrectionSetTestTime);
		defectAnalyzerControllerOut
				.setClassifiedCorrections(classifiedCorrections);
		defectAnalyzerControllerOut.setClassifiedCauses(classifiedCauses);

		return defectAnalyzerControllerOut;
	}*/

	/*public void analyzeModels(
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO,
			String directoryPath, String outputDirectoryPath)
			throws FunctionalException {

		List<DefectAnalyzerControllerOutDTO> defectAnalyzerControllerOutDTOList = new ArrayList<DefectAnalyzerControllerOutDTO>();

		List<File> fileList = FileUtils.readFileFromDirectory(directoryPath);
		for (File file : fileList) {
			// analizador.razonarSobreModelo();
			// Se toma el tiempo inicial
			if (file.getPath().endsWith("sxfm")
					|| file.getPath().endsWith("splx")
					|| file.getPath().endsWith("xml")) {
				System.out.println("\n!!Analizando modelo: " + file.getName());
				defectAnalyzerControllerOutDTOList.add(analyzeModel(
						defectAnalyzerInDTO, file.getName(),
						outputDirectoryPath));
			}

		}

	}*/

	/**
	 * Imprime el conjunto de causas o de correcciones clasificado
	 * 
	 * @param classifiedDiagnosis
	 * @param classificationType
	 */
	/*public void printAllClassifiedDiagnosis(
			ClassifiedElement classifiedDiagnosis,
			ClassificationType classificationType) {

		System.out.println(" \n CLASIFICACIÓN:" + classificationType.name());
		System.out.println("_______________________________________");
		System.out.println("A.Comunes");
		System.out.println("Cantidad: "
				+ classifiedDiagnosis.getCommonDiagnosis().size());
		printClassifiedDiagnosis(classifiedDiagnosis.getCommonDiagnosis(),
				classificationType);

		System.out.println("B.NoComunes");
		System.out.println("Cantidad: "
				+ classifiedDiagnosis.getNoCommonDiagnosis().size());
		printClassifiedDiagnosis(classifiedDiagnosis.getNoCommonDiagnosis(),
				classificationType);

	}*/

	/*public void printClassifiedDiagnosis(
			List<ClassifiableDiagnosis> defectsByMCSMUSesList,
			ClassificationType classificationType) {
		for (ClassifiableDiagnosis defectsByMCSMUSes : defectsByMCSMUSesList) {
			System.out.println(classificationType.name() + " " + "Id:"
					+ defectsByMCSMUSes.getId());
			System.out.println("Cantidad elementos:"
					+ defectsByMCSMUSes.getDiagnosticElements().size());
			System.out.println("Elementos:"
					+ defectsByMCSMUSes.getDiagnosticElements());
			System.out.println("Defectos asociados");
			int i = 1;
			for (int j = 0; j < defectsByMCSMUSes.getDefects().size(); j++) {

				System.out.println(i + ". "
						+ defectsByMCSMUSes.getDefects().get(j));
				i++;
			}
			System.out.println();
		}
	}*/

	public void printDiagnosis(List<Diagnosis> allDiagnosis) {
		for (Diagnosis diagnostic : allDiagnosis) {
			System.out.println("______________________________");
			System.out.println("Defecto:" + diagnostic.getDefect());
			System.out.println("Causas");
			System.out.println();
			diagnostic.printCauses();
			System.out.println();
			System.out.println("Correcciones");
			diagnostic.printCorrections();
			System.out.println();
		}
	}

	/*public void exportCorrectionCausesSubsets(
			VerificationResult vmVerifierOutDTO,
			DefectAnalyzerResult causeAnalyzerOutDTO,
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO, Long tiempoTest,
			ClassifiedElement classifiedCauses,
			ClassifiedElement classifiedCorrections, String fileName,
			String outputDirectoryPath) throws FunctionalException {

		List<String> resultadosFila = new ArrayList<String>();
		List<List<String>> resultadosAnalisis = new ArrayList<List<String>>();
		HSSFWorkbook resultadosLibro = new HSSFWorkbook();
		int cont = 1;
		StringBuilder correctionSetContent = new StringBuilder();

		HSSFSheet hojaDefectos = resultadosLibro.createSheet();
		String titulosDefectos[] = { "Defectos" };

		List<List<String>> defectos = new ArrayList<List<String>>();

		// Estadistícas
		List<String> numCaracteristicas = new ArrayList<String>();
		numCaracteristicas.add("#Caracteristicas");
		numCaracteristicas.add(Integer.toString(defectAnalyzerInDTO
				.getVariabilityModel().getNumbeOfFeatures()));
		defectos.add(numCaracteristicas);

		List<String> numDependencias = new ArrayList<String>();
		numDependencias.add("#Dependencias");
		numDependencias.add(Integer.toString(defectAnalyzerInDTO
				.getVariabilityModel().getNumberOfDependencies()));
		defectos.add(numDependencias);

		List<String> numNonTraversal = new ArrayList<String>();
		numNonTraversal.add("#NonTraversal");
		numNonTraversal.add(Integer.toString(defectAnalyzerInDTO
				.getVariabilityModel().getNumberOfNonTrasversalDependencies()));
		defectos.add(numNonTraversal);

		List<String> porcNonTraversal = new ArrayList<String>();
		porcNonTraversal.add("%NonTraversal");
		porcNonTraversal
				.add(Integer.toString(defectAnalyzerInDTO.getVariabilityModel()
						.getPercentageNonTraversalDependencies()));
		defectos.add(porcNonTraversal);

		List<String> numTraversal = new ArrayList<String>();
		numTraversal.add("#Traversal");
		numTraversal.add(Integer.toString(defectAnalyzerInDTO
				.getVariabilityModel().getNumberOfTraversalDependencies()));
		defectos.add(numTraversal);

		List<String> porcTraversal = new ArrayList<String>();
		porcTraversal.add("%Traversal");
		porcTraversal.add(Integer.toString(defectAnalyzerInDTO
				.getVariabilityModel().getPercentageTraversalDependencies()));
		defectos.add(porcTraversal);

		// Defectos
		List<String> general = new ArrayList<String>();
		general.add("Numero defectos");
		int cantidadDefectos = 0;
		if (vmVerifierOutDTO.isVoidModel()) {
			cantidadDefectos++;
		}
		if (vmVerifierOutDTO.isFalseProductLineModel()) {
			cantidadDefectos++;
		}
		if (vmVerifierOutDTO.getDeadFeaturesList() != null
				&& !vmVerifierOutDTO.getDeadFeaturesList().isEmpty()) {
			cantidadDefectos += vmVerifierOutDTO.getDeadFeaturesList().size();
		}
		if (vmVerifierOutDTO.getFalseOptionalFeaturesList() != null
				&& !vmVerifierOutDTO.getFalseOptionalFeaturesList().isEmpty()) {
			cantidadDefectos += vmVerifierOutDTO.getFalseOptionalFeaturesList()
					.size();
		}
		if (vmVerifierOutDTO.getRedundanciesList() != null
				&& !vmVerifierOutDTO.getRedundanciesList().isEmpty()) {
			cantidadDefectos += vmVerifierOutDTO.getRedundanciesList().size();
		}
		general.add(Integer.toString(cantidadDefectos));
		defectos.add(general);
		List<String> defectoVoidModelo = new ArrayList<String>();
		defectoVoidModelo.add("Modelo vacío");
		defectoVoidModelo.add(Boolean.toString(vmVerifierOutDTO.isVoidModel()));
		defectos.add(defectoVoidModelo);

		List<String> defectoFalseProductLine = new ArrayList<String>();
		defectoFalseProductLine.add("Falsa Línea de productos");
		defectoFalseProductLine.add(Boolean.toString(vmVerifierOutDTO
				.isFalseProductLineModel()));
		defectos.add(defectoFalseProductLine);

		defectos.addAll(saveDefects("DeadFeatures",
				vmVerifierOutDTO.getDeadFeaturesList()));
		defectos.addAll(saveDefects("False optional features",
				vmVerifierOutDTO.getFalseOptionalFeaturesList()));
		defectos.addAll(saveDefects("Redundancies",
				vmVerifierOutDTO.getRedundanciesList()));

		// Se pone información en la hoja
		ExportUtil.adicionarInfoHoja(Arrays.asList(titulosDefectos), 0,
				hojaDefectos, defectos, tiempoTest, cont);

		// CAUSAS Y CORECCIONES
		// Información para guardar
		String titulos[] = { "Defecto", "#elementos relajados",
				"Solución número", "correcionsets" };

		HSSFSheet hoja = resultadosLibro.createSheet();
		List<Diagnosis> allDiagnostics = causeAnalyzerOutDTO
				.getAllDiagnosis();

		for (Diagnosis diagnosticMapElement : allDiagnostics) {
			cont = 0;
			if (diagnosticMapElement != null
					&& diagnosticMapElement.getCorrectionSubsets() != null) {
				for (List<Dependency> correctionSubsets : diagnosticMapElement
						.getCorrectionSubsets()) {
					resultadosFila = new ArrayList<String>();
					resultadosFila.add(diagnosticMapElement.getDefect()
							.getDefectType().name()
							+ "-" + diagnosticMapElement.getDefect().getId());
					resultadosFila.add(Integer.toString(correctionSubsets
							.size()));
					resultadosFila.add(Integer.toString(cont));
					correctionSetContent = new StringBuilder();
					for (Dependency correctionDependency : correctionSubsets) {
						correctionSetContent.append(correctionDependency
								.toString());
						correctionSetContent.append(",");
					}

					resultadosFila.add(correctionSetContent.toString());
					resultadosAnalisis.add(resultadosFila);
					cont++;
				}
			} else {
				resultadosFila = new ArrayList<String>();
				resultadosFila.add("Ocurrio un error");
			}
			// Se adicionan las causas

			List<String> causas = new ArrayList<String>();
			causas = new ArrayList<String>();
			causas.add("Causas: ");
			if (diagnosticMapElement.getCauses() != null) {
				cont = 1;
				for (List<Dependency> cause : diagnosticMapElement.getCauses()) {
					resultadosFila = new ArrayList<String>();
					resultadosFila.add("Causa");
					resultadosFila.add(Integer.toString(cont));
					resultadosFila.add(cause.toString());
					resultadosFila.add(Integer.toString(cause.size()));
					resultadosAnalisis.add(resultadosFila);
				}
			} else {
				resultadosFila = new ArrayList<String>();
				resultadosFila.add("Ocurrio un error");
			}

			resultadosAnalisis.add(new ArrayList<String>());
		}

		// Se pone información en la hoja
		ExportUtil.adicionarInfoHoja(Arrays.asList(titulos), 0, hoja,
				resultadosAnalisis, tiempoTest, cont);

		// Se crea una nueva hoja para poner la clasificación de las causas y
		// las correciones
		HSSFSheet hojaClasificacion = resultadosLibro.createSheet();

		// CLASIFICACION

		String titulosClasificacion[] = { "Clasificacion" };
		List<List<String>> clasificacion = new ArrayList<List<String>>();
		clasificacion.addAll(saveClassification("Causas Comunes",
				classifiedCauses.getCommonDiagnosis()));

		clasificacion.addAll(saveClassification("Causas no Comunes",
				classifiedCauses.getNoCommonDiagnosis()));

		clasificacion.addAll(saveClassification("Correcciones Comunes",
				classifiedCorrections.getCommonDiagnosis()));

		clasificacion.addAll(saveClassification("correcciones no Comunes",
				classifiedCorrections.getNoCommonDiagnosis()));

		// Se pone información en la hoja
		ExportUtil.adicionarInfoHoja(Arrays.asList(titulosClasificacion), 0,
				hojaClasificacion, clasificacion, tiempoTest, cont);

		// Se guarda la hoja xls creada
		String resuladosPath = outputDirectoryPath + fileName + ".xls";
		ExportUtil.guardarXls(resultadosLibro, resuladosPath);
	}
*/
	private List<List<String>> saveDefects(String encabezado,
			List<Defect> defectosLista) {

		List<List<String>> defectosFilas = new ArrayList<List<String>>();

		if (defectosLista != null && !defectosLista.isEmpty()) {
			defectosFilas.add(new ArrayList<String>());
			List<String> encabezadoClasificacion = new ArrayList<String>();
			encabezadoClasificacion.add("Cantidad");
			encabezadoClasificacion.add(Integer.toString(defectosLista.size()));
			defectosFilas.add(encabezadoClasificacion);

			List<String> encabezadoClasificacion1 = new ArrayList<String>();
			encabezadoClasificacion1.add("Tipo");
			encabezadoClasificacion1.add("Defecto");
			defectosFilas.add(encabezadoClasificacion1);

			for (Defect defect : defectosLista) {
				List<String> resultadosFila = new ArrayList<String>();
				resultadosFila.add(defect.getDefectType().name());
				resultadosFila.add(defect.getId());
				defectosFilas.add(resultadosFila);
			}
		}
		return defectosFilas;
	}

	/**
	 * Crea la información para guardar la clasificación en una hoja de excel
	 * 
	 * @param encabezado
	 * @param classifiedCausesCorrectionsList
	 * @return
	 */
	/*private List<List<String>> saveClassification(String encabezado,
			List<ClassifiableDiagnosis> classifiedCausesCorrectionsList) {

		List<List<String>> clasificacion = new ArrayList<List<String>>();

		clasificacion.add(new ArrayList<String>());
		List<String> encabezadoClasificacion = new ArrayList<String>();
		encabezadoClasificacion.add("Cantidad");
		encabezadoClasificacion.add(Integer
				.toString(classifiedCausesCorrectionsList.size()));
		clasificacion.add(encabezadoClasificacion);

		List<String> encabezadoClasificacion1 = new ArrayList<String>();
		encabezadoClasificacion1.add("Id");
		encabezadoClasificacion1.add(encabezado);
		encabezadoClasificacion1.add("Tam");
		encabezadoClasificacion1.add("Defectos comunes");
		encabezadoClasificacion1.add("CantDefe");
		clasificacion.add(encabezadoClasificacion1);

		for (ClassifiableDiagnosis defectsByMCSMUSes : classifiedCausesCorrectionsList) {
			List<String> resultadosFila = new ArrayList<String>();
			resultadosFila.add(Long.toString(defectsByMCSMUSes.getId()));
			resultadosFila.add(defectsByMCSMUSes.getDiagnosticElements()
					.toString());
			resultadosFila.add(Integer.toString(defectsByMCSMUSes
					.getDiagnosticElements().size()));
			resultadosFila.add(defectsByMCSMUSes.getDefects().toString());
			resultadosFila.add(Integer.toString(defectsByMCSMUSes.getDefects()
					.size()));
			clasificacion.add(resultadosFila);
		}
		return clasificacion;
	}*/

	/**
	 * Invoca el verificador de defectos
	 * 
	 * @param defectAnalyzerInDTO
	 * @return VerifierOutDTO
	 * @throws FunctionalException
	 * 
	 */
	private VerificationResultDTO verifyDefects(
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO)
			throws FunctionalException {

		VerificationResultDTO outDTO = new VerificationResultDTO();
		// Se construye el DTO de entrada del verificador
		// Make input DTO
		VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();

		// Set transformed variability model
		verifierInDTO.setVariabilityModel(defectAnalyzerInDTO
				.getVariabilityModel());
		// Set Prolog editor type
		verifierInDTO.setSolverEditorType(defectAnalyzerInDTO
				.getSolverEditorType());

		// Create class
		//DefectsVerifier verifier = new DefectsVerifier(
				//defectAnalyzerInDTO.getSolverEditorType());

		/*
		 * outDTO = verifier.verifierOfDefects(
		 * defectAnalyzerInDTO.isVerifyDeadFeatures(),
		 * defectAnalyzerInDTO.isVerifyFalseOptionalElement(),
		 * defectAnalyzerInDTO.isVerifyFalseProductLine(),
		 * defectAnalyzerInDTO.isVerifyNonAttainableDomain(),
		 * defectAnalyzerInDTO.isVerifyRedundancies());
		 */

		return outDTO;

	}

	/**
	 * Identifica las causas de los defectos identificados según los criterios
	 * definidos en el DTO de entrada
	 * 
	 * @param defectAnalyzerInDTO
	 * @param verifierOutDTO
	 * @return
	 * @throws FunctionalException
	 */
	private DefectAnalyzerResultDTO causesAnalyzer(
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO,
			VerificationResultDTO verifierOutDTO) throws FunctionalException {

		// Información del DTO
		VMAnalyzerInDTO vmAnalyzerInDTO = new VMAnalyzerInDTO();
		vmAnalyzerInDTO.setVariabilityModel(defectAnalyzerInDTO
				.getVariabilityModel());
		vmAnalyzerInDTO.setSolverEditorType(defectAnalyzerInDTO
				.getSolverEditorType());

		// Se invoca el analizador de causas
		CauCosAnayzer causesAnalyzer = new CauCosAnayzer();

		VMCauseAnalyzerInDTO vmCauseAnalyzerInDTO = new VMCauseAnalyzerInDTO();

		vmCauseAnalyzerInDTO.setDefectAnalyzerMode(defectAnalyzerInDTO
				.getDefectAnalyzerMode());

		vmCauseAnalyzerInDTO.setFalseProductLine(verifierOutDTO
				.getFalseProductLineModel());
		vmCauseAnalyzerInDTO.setDeadFeaturesList(verifierOutDTO
				.getDeadFeaturesList());
		vmCauseAnalyzerInDTO.setVoidModel(verifierOutDTO.getVoidModel());
		vmCauseAnalyzerInDTO.setFalseOptionalFeaturesList(verifierOutDTO
				.getFalseOptionalFeaturesList());
		vmCauseAnalyzerInDTO.setRedundancies(verifierOutDTO
				.getRedundanciesList());
		/*DefectAnalyzerResult vmCauseAnalyzerOutDTO = causesAnalyzer
				.causesAnalyzer(defectAnalyzerInDTO.isAnalyzeDeadFeatures(),
						defectAnalyzerInDTO.isAnalyzeFalseOptional(),
						defectAnalyzerInDTO.isIdentifyCausesFalseProductLine(),
						defectAnalyzerInDTO.isAnalyzeDomains(),
						defectAnalyzerInDTO.isAnalyzeVoidModel(),
						defectAnalyzerInDTO.isAnalyzeRedundancies(),
						vmCauseAnalyzerInDTO);*/

		//return vmCauseAnalyzerOutDTO;
		return null;
	}

}
