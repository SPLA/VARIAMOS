package com.cfm.productline.defectAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cfm.hlcl.Expression;
import com.cfm.productline.dto.DefectAnalyzerControllerInDTO;
import com.cfm.productline.dto.DefectAnalyzerControllerOutDTO;
import com.cfm.productline.dto.VMAnalyzerInDTO;
import com.cfm.productline.dto.VMCauseAnalyzerInDTO;
import com.cfm.productline.dto.VMCauseAnalyzerOutDTO;
import com.cfm.productline.dto.VMVerifierOutDTO;
import com.cfm.productline.exceptions.FunctionalException;
import com.cfm.productline.model.defect.Defect;
import com.cfm.productline.model.defect.Redundancy;
import com.cfm.productline.model.defectAnalyzerModel.Dependency;
import com.cfm.productline.model.diagnostic.ClassifiedDiagnosis;
import com.cfm.productline.model.diagnostic.DefectsByMCSMUSes;
import com.cfm.productline.model.diagnostic.Diagnostic;
import com.cfm.productline.model.enums.ClassificationType;
import com.cfm.productline.model.enums.SolverEditorType;
import com.cfm.productline.util.ConstraintRepresentationUtil;
import com.cfm.productline.util.ExportUtil;
import com.cfm.productline.util.FileUtils;
import com.cfm.productline.util.SolverOperationsUtil;

public class DefectAnalyzerController {

	public DefectAnalyzerControllerOutDTO analyzeModel(
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO, String fileName,
			String outputDirectoryPath) throws FunctionalException {

		long startCorrectionSetTestTime, endCorrectionSetTestTime, totalCorrectionSetTestTime = 0;
		VMVerifierOutDTO verifierOutDTO = null;
		VMCauseAnalyzerOutDTO causeAnalizerOutDTO = new VMCauseAnalyzerOutDTO();
		DefectAnalyzerControllerOutDTO defectAnalyzerControllerOut = new DefectAnalyzerControllerOutDTO();
		defectAnalyzerControllerOut.setVariabilityModel(defectAnalyzerInDTO
				.getVariabilityModel());

		// Se valida que existan los directorios antes de continuar
		File file = new File(outputDirectoryPath);
		if (!file.exists()) {
			// Si no existe el directorio se lanza una excepción
			throw new FunctionalException("El directorio de salida "
					+ outputDirectoryPath + " no existe");
		}

		// Se verifican los defectos del modelo
		verifierOutDTO = verifierOfDefects(defectAnalyzerInDTO);

		// Se analizan los MCS
		startCorrectionSetTestTime = System.currentTimeMillis();
		causeAnalizerOutDTO = causesAnalyzer(defectAnalyzerInDTO,
				verifierOutDTO);

		// Se clasifican las causa entre comunes y no comunes
	/*	VariabilityModelCausesCorrectionsSorter sorter = new VariabilityModelCausesCorrectionsSorter();
		ClassifiedDiagnosis classifiedCauses = sorter.classifyDiagnosis(
				causeAnalizerOutDTO.getAllDiagnostics(),
				ClassificationType.CAUSES);

		// Se clasifican las correciones entre comunes y no comunes
		ClassifiedDiagnosis classifiedCorrections = sorter.classifyDiagnosis(
				causeAnalizerOutDTO.getAllDiagnostics(),
				ClassificationType.CORRECTIONS);*/

		// Se calcula el tiempo final
		endCorrectionSetTestTime = System.currentTimeMillis();
		totalCorrectionSetTestTime = endCorrectionSetTestTime
				- startCorrectionSetTestTime;
		System.out.println("Termino");
		System.out.println("Tiempo: " + totalCorrectionSetTestTime + " mls");
		ClassifiedDiagnosis classifiedCauses= new ClassifiedDiagnosis();
		ClassifiedDiagnosis classifiedCorrections= new ClassifiedDiagnosis();
		
		// Se exportan los resultados
		exportCorrectionCausesSubsets(verifierOutDTO, causeAnalizerOutDTO,
				defectAnalyzerInDTO, totalCorrectionSetTestTime,
				classifiedCauses, classifiedCorrections, fileName,
				outputDirectoryPath);

		// Se imprimen todos los resultados en pantalla
		// System.out.println();
		// System.out.println();
		// System.out.println();
		// System.out.println();
		// System.out.println();
		// System.out
		// .println("............................RESULTADOS............");
		// System.out.println("Cantidad de defectos analizados"
		// + causeAnalizerOutDTO.getAllDiagnostics().size());
		// printDiagnosis(causeAnalizerOutDTO.getAllDiagnostics());
		// printAllClassifiedDiagnosis(classifiedCorrections,
		// ClassificationType.CORRECTIONS);
		// printAllClassifiedDiagnosis(classifiedCauses,
		// ClassificationType.CAUSES);

		// Información DTO de salida que sirve para analizar xls resumidos
		defectAnalyzerControllerOut.setAllDiagnostics(causeAnalizerOutDTO
				.getAllDiagnostics());
		defectAnalyzerControllerOut.setTime(totalCorrectionSetTestTime);
		defectAnalyzerControllerOut
				.setClassifiedCorrections(classifiedCorrections);
		defectAnalyzerControllerOut.setClassifiedCauses(classifiedCauses);

		return defectAnalyzerControllerOut;
	}

	public void analyzeModels(
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

	}

	/**
	 * Imprime el conjunto de causas o de correcciones clasificado
	 * 
	 * @param classifiedDiagnosis
	 * @param classificationType
	 */
	public void printAllClassifiedDiagnosis(
			ClassifiedDiagnosis classifiedDiagnosis,
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

	}

	public void printClassifiedDiagnosis(
			List<DefectsByMCSMUSes> defectsByMCSMUSesList,
			ClassificationType classificationType) {
		for (DefectsByMCSMUSes defectsByMCSMUSes : defectsByMCSMUSesList) {
			System.out.println(classificationType.name() + " " + "Id:"
					+ defectsByMCSMUSes.getId());
			System.out.println("Cantidad elementos:"
					+ defectsByMCSMUSes.getDiagnosticElements().size());
			System.out.println("Elementos:"
					+ defectsByMCSMUSes.getDiagnosticElements());
			System.out.println("Defectos asociados");
			int i = 1;
			for (int j = 0; j < defectsByMCSMUSes.getDefectsList().size(); j++) {

				System.out.println(i + ". "
						+ defectsByMCSMUSes.getDefectsList().get(j));
				i++;
			}
			System.out.println();
		}
	}

	public List<List<String>> validarCausas(List<Diagnostic> allDiagnosis,
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO, String folderPath)
			throws FunctionalException {

		List<List<String>> resultadosValidacion = new ArrayList<List<String>>();
		List<String> tiempo = new ArrayList<String>();
		long starTime, endTime, totalTiem = 0;
		starTime = System.currentTimeMillis();
		for (Diagnostic diagnostic : allDiagnosis) {
			resultadosValidacion.addAll(validarCausa(diagnostic,
					defectAnalyzerInDTO, folderPath));
		}
		endTime = System.currentTimeMillis();
		totalTiem = endTime - starTime;

		float seconds = (float) (((float) totalTiem / 1000) % 60);
		float minutes = (float) (((float) totalTiem / 1000) / 60);
		tiempo.add(" Total mls");
		tiempo.add(Long.toString(totalTiem));
		tiempo.add("Minutos ");
		tiempo.add(Float.toString(minutes));
		tiempo.add("Segundos ");
		tiempo.add(Float.toString(seconds));

		// Se adiciona la lista con la información del tiempo en el primer
		// elemento de la lista
		resultadosValidacion.add(0, tiempo);
		return resultadosValidacion;

	}

	/**
	 * Crea con el conjunto de causas identificadas un programa de restricciones
	 * y guarda los resultados que verifican si el programa de restricciones es
	 * o no resoluble
	 * 
	 * @param diagnostic
	 * @param defectAnalyzerInDTO
	 * @param folderPath
	 * @return
	 * @throws FunctionalException
	 */
	private List<List<String>> validarCausa(Diagnostic diagnostic,
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO, String folderPath)
			throws FunctionalException {
		List<List<String>> resultadosValidacion = new ArrayList<List<String>>();
		File folder = new File(folderPath);
		// Crea la carpeta
		folder.mkdirs();
		int causesCount = 1;
		for (List<Dependency> causesDependencyList : diagnostic.getCauses()) {
			// Crear la representación en restricciones de las dependencia de
			// las causas, mas la restriccion fija y la restricción de
			// verificación
			Collection<Expression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
					.dependencyToExpressionList(causesDependencyList,
							defectAnalyzerInDTO.getVariabilityModel()
									.getFixedDependencies());

			// Se adiciona a la lista la expression que permite verificar el
			// defecto
			if (diagnostic.getDefect().getVerificationExpression() != null) {
				variabilityModelConstraintRepresentation.add(diagnostic
						.getDefect().getVerificationExpression());
			}
			if (folder.exists()) {
				String filePath = folder.getAbsoluteFile()
						+ "//"
						+ defectAnalyzerInDTO.getVariabilityModel()
								.getModelName() + "_"
						+ diagnostic.getDefect().getDefectType().name() + "_"
						+ diagnostic.getDefect().getId().replace(":", "_")
						+ "_Causa" + causesCount + ".pl";

				File MUSFile = new File(filePath);
				try {
					MUSFile.createNewFile();
					String musFilePath = MUSFile.getAbsolutePath().replace(
							"\\", "/");
					List<String> MUSResultados = new ArrayList<String>();
					// Save the variability model in a prolog program
					ConstraintRepresentationUtil
							.savePrologRepresentationProgram(
									MUSFile.getAbsolutePath(),
									variabilityModelConstraintRepresentation,
									SolverEditorType.SWI_PROLOG);

					// Se verifica si el conjunto de restricciones es
					// satisfacible
					// Se verifica si el problema guardado es satisfacible
					boolean isSatisfiable = SolverOperationsUtil.isSatisfiable(
							musFilePath, SolverEditorType.SWI_PROLOG);
					MUSResultados.add(defectAnalyzerInDTO.getVariabilityModel()
							.getModelName()
							+ " "
							+ diagnostic.getDefect().getDefectType().name()
							+ " "
							+ diagnostic.getDefect().getId()
							+ "Causa"
							+ causesCount);
					MUSResultados.add("Irresoluble");
					MUSResultados.add(Boolean.toString(!isSatisfiable));

					// Se le quita algún pedazo a la causa para ver si al quedar
					// incompleta es resoluble. No se pone en el programa de
					// restricciones la primera restricción

					String filePathNoMUS = folder.getAbsoluteFile()
							+ "//"
							+ defectAnalyzerInDTO.getVariabilityModel()
									.getModelName() + "_"
							+ diagnostic.getDefect().getDefectType().name()
							+ "_"
							+ diagnostic.getDefect().getId().replace(":", "_")
							+ "_NOCausa" + causesCount + ".pl";

					File noMUSFile = new File(filePathNoMUS);
					String noMUSFilePath = noMUSFile.getAbsolutePath().replace(
							"\\", "/");

					// Quitar una dependencia de la lista del MUS
					List<Dependency> dependenciesList = new ArrayList<Dependency>();
					dependenciesList.addAll(causesDependencyList);
					// Se le quita un elemento
					dependenciesList.remove(0);

					// Se crea una representación en restricciones con ese nuevo
					// conjunto
					variabilityModelConstraintRepresentation.clear();
					variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
							.dependencyToExpressionList(dependenciesList,
									defectAnalyzerInDTO.getVariabilityModel()
											.getFixedDependencies());

					// Se adiciona a la lista la expression que permite
					// verificar el
					// defecto
					if (diagnostic.getDefect().getVerificationExpression() != null) {
						variabilityModelConstraintRepresentation.add(diagnostic
								.getDefect().getVerificationExpression());
					}

					// Se guarda el programa de restricciones debe ser
					// irresoluble
					ConstraintRepresentationUtil
							.savePrologRepresentationProgram(
									noMUSFile.getAbsolutePath(),
									variabilityModelConstraintRepresentation,
									SolverEditorType.SWI_PROLOG);

					// Se verifica si el problema guardado es satisfacible
					isSatisfiable = SolverOperationsUtil.isSatisfiable(
							noMUSFilePath, SolverEditorType.SWI_PROLOG);
					MUSResultados.add("Incompleto");
					MUSResultados.add("Irresoluble");
					MUSResultados.add(Boolean.toString(!isSatisfiable));

					causesCount++;
					resultadosValidacion.add(MUSResultados);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return resultadosValidacion;
	}

	private List<List<String>> validarCorrecciones(
			List<Diagnostic> allDiagnosis,
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO, String folderPath)
			throws FunctionalException {

		List<List<String>> resultadosValidacion = new ArrayList<List<String>>();
		List<String> tiempo = new ArrayList<String>();
		long starTime, endTime, totalTiem = 0;
		starTime = System.currentTimeMillis();
		for (Diagnostic diagnostic : allDiagnosis) {
			resultadosValidacion.addAll(validarCorreccion(diagnostic,
					defectAnalyzerInDTO, folderPath));
		}
		endTime = System.currentTimeMillis();
		totalTiem = endTime - starTime;

		float seconds = (float) (((float) totalTiem / 1000) % 60);
		float minutes = (float) (((float) totalTiem / 1000) / 60);
		tiempo.add(" Total mls");
		tiempo.add(Long.toString(totalTiem));
		tiempo.add("Minutos ");
		tiempo.add(Float.toString(minutes));
		tiempo.add("Segundos ");
		tiempo.add(Float.toString(seconds));

		// Se adiciona la lista con la información del tiempo en el primer
		// elemento de la lista
		resultadosValidacion.add(0, tiempo);
		return resultadosValidacion;

	}

	private List<List<String>> validarCorreccion(Diagnostic diagnostic,
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO, String folderPath)
			throws FunctionalException {
		List<List<String>> resultadosValidacion = new ArrayList<List<String>>();
		List<Dependency> collectionOfDependencies = new ArrayList<Dependency>();
		File folder = new File(folderPath + "Correc");
		// Crea la carpeta
		folder.mkdirs();
		int correcc = 1;
		for (List<Dependency> correcDependenciesList : diagnostic
				.getCorrectionSubsets()) {
			// Crear la representación en restricciones de las dependencia de
			// las causas, mas la restriccion fija y la restricción de
			// verificación

			collectionOfDependencies
					.addAll((Collection<? extends Dependency>) defectAnalyzerInDTO
							.getVariabilityModel().getDependencies().values());
			collectionOfDependencies.removeAll(correcDependenciesList);

			// Si el defecto es una redundancia se borra antes de construir el
			// programa de restricciones irresoluble
			// para no poner información contradictoria
			if (diagnostic.getDefect() instanceof Redundancy) {
				collectionOfDependencies.remove(((Redundancy) diagnostic
						.getDefect()).getRedundantDependency());
			}

			Collection<Expression> variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
					.dependencyToExpressionList(collectionOfDependencies,
							defectAnalyzerInDTO.getVariabilityModel()
									.getFixedDependencies());

			// Se adiciona a la lista la expression que permite verificar el
			// defecto
			if (diagnostic.getDefect().getVerificationExpression() != null) {
				variabilityModelConstraintRepresentation.add(diagnostic
						.getDefect().getVerificationExpression());
			}
			if (folder.exists()) {
				String filePath = folder.getAbsoluteFile()
						+ "//"
						+ defectAnalyzerInDTO.getVariabilityModel()
								.getModelName() + "_"
						+ diagnostic.getDefect().getDefectType().name() + "_"
						+ diagnostic.getDefect().getId().replace(":", "_")
						+ "_Correc" + correcc + ".pl";

				File MCSFile = new File(filePath);
				try {
					MCSFile.createNewFile();
					String mcsFilePath = MCSFile.getAbsolutePath().replace(
							"\\", "/");
					List<String> MCSResultados = new ArrayList<String>();
					// Save the variability model in a prolog program
					ConstraintRepresentationUtil
							.savePrologRepresentationProgram(
									MCSFile.getAbsolutePath(),
									variabilityModelConstraintRepresentation,
									SolverEditorType.SWI_PROLOG);

					// Se verifica si el conjunto de restricciones es
					// satisfacible
					boolean isSatisfiable = SolverOperationsUtil.isSatisfiable(
							mcsFilePath, SolverEditorType.SWI_PROLOG);
					MCSResultados.add(defectAnalyzerInDTO.getVariabilityModel()
							.getModelName()
							+ " "
							+ diagnostic.getDefect().getDefectType().name()
							+ " "
							+ diagnostic.getDefect().getId()
							+ "Correc"
							+ correcc);
					MCSResultados.add("Irresoluble :");
					MCSResultados.add(Boolean.toString(!isSatisfiable));

					// Se crea nuevamente un programa de restricciones dejando
					// alguna parte del MCS ( siempre se deja la primera
					// correccion de la lista)
					collectionOfDependencies.clear();
					collectionOfDependencies
							.addAll((Collection<? extends Dependency>) defectAnalyzerInDTO
									.getVariabilityModel().getDependencies()
									.values());
					collectionOfDependencies.removeAll(correcDependenciesList);

					// Si el defecto es una redundancia se borra antes de
					// construir el programa de restricciones irresoluble
					// para no poner información contradictoria
					if (diagnostic.getDefect() instanceof Redundancy) {
						collectionOfDependencies
								.remove(((Redundancy) diagnostic.getDefect())
										.getRedundantDependency());
					}

					// Vuelvo y agrego una correccion así la corrección no se
					// aplica completa, o no se aplica, y el resultado debe ser
					// un programa de restricciones irresoluble
					collectionOfDependencies.add(correcDependenciesList.get(0));

					variabilityModelConstraintRepresentation = ConstraintRepresentationUtil
							.dependencyToExpressionList(
									collectionOfDependencies,
									defectAnalyzerInDTO.getVariabilityModel()
											.getFixedDependencies());
					// Se adiciona a la lista la expression que permite
					// verificar el
					// defecto
					if (diagnostic.getDefect().getVerificationExpression() != null) {
						variabilityModelConstraintRepresentation.add(diagnostic
								.getDefect().getVerificationExpression());
					}
					String filePathNoMCS = folder.getAbsoluteFile()
							+ "//"
							+ defectAnalyzerInDTO.getVariabilityModel()
									.getModelName() + "_"
							+ diagnostic.getDefect().getDefectType().name()
							+ "_"
							+ diagnostic.getDefect().getId().replace(":", "_")
							+ "_NOCorrec" + correcc + ".pl";

					File noMCSFile = new File(filePathNoMCS);
					String noMCSFilePath = noMCSFile.getAbsolutePath().replace(
							"\\", "/");
					// Save the variability model in a prolog program. NO DEBE
					// SER RESOLUBLE
					ConstraintRepresentationUtil
							.savePrologRepresentationProgram(
									noMCSFile.getAbsolutePath(),
									variabilityModelConstraintRepresentation,
									SolverEditorType.SWI_PROLOG);

					// Se verifica si el conjunto de restricciones es
					// satisfacible
					isSatisfiable = SolverOperationsUtil.isSatisfiable(
							noMCSFilePath, SolverEditorType.SWI_PROLOG);

					correcc++;
					MCSResultados.add("MCS modif es irresoluble ");
					MCSResultados.add(Boolean.toString(!isSatisfiable));
					resultadosValidacion.add(MCSResultados);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return resultadosValidacion;
	}

	public void printDiagnosis(List<Diagnostic> allDiagnosis) {
		for (Diagnostic diagnostic : allDiagnosis) {
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

	public void exportCorrectionCausesSubsets(
			VMVerifierOutDTO vmVerifierOutDTO,
			VMCauseAnalyzerOutDTO causeAnalyzerOutDTO,
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO, Long tiempoTest,
			ClassifiedDiagnosis classifiedCauses,
			ClassifiedDiagnosis classifiedCorrections, String fileName,
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

		defectos.addAll(guardarDefectos("DeadFeatures",
				vmVerifierOutDTO.getDeadFeaturesList()));
		defectos.addAll(guardarDefectos("False optional features",
				vmVerifierOutDTO.getFalseOptionalFeaturesList()));
		defectos.addAll(guardarDefectos("Redundancies",
				vmVerifierOutDTO.getRedundanciesList()));

		// Se pone información en la hoja
		ExportUtil.adicionarInfoHoja(Arrays.asList(titulosDefectos), 0,
				hojaDefectos, defectos, tiempoTest, cont);

		// CAUSAS Y CORECCIONES
		// Información para guardar
		String titulos[] = { "Defecto", "#elementos relajados",
				"Solución número", "correcionsets" };

		HSSFSheet hoja = resultadosLibro.createSheet();
		List<Diagnostic> allDiagnostics = causeAnalyzerOutDTO
				.getAllDiagnostics();

		for (Diagnostic diagnosticMapElement : allDiagnostics) {
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
			/*
			 * List<String> causas = new ArrayList<String>(); causas = new
			 * ArrayList<String>(); causas.add("Causas: "); if
			 * (diagnosticMapElement.getCauses() != null) { cont = 1; for
			 * (List<Dependency> cause : diagnosticMapElement.getCauses()) {
			 * resultadosFila = new ArrayList<String>();
			 * resultadosFila.add("Causa");
			 * resultadosFila.add(Integer.toString(cont));
			 * resultadosFila.add(cause.toString());
			 * resultadosFila.add(Integer.toString(cause.size()));
			 * resultadosAnalisis.add(resultadosFila); } } else { resultadosFila
			 * = new ArrayList<String>();
			 * resultadosFila.add("Ocurrio un error"); }
			 */

			resultadosAnalisis.add(new ArrayList<String>());
		}

		// Se pone información en la hoja
		ExportUtil.adicionarInfoHoja(Arrays.asList(titulos), 0, hoja,
				resultadosAnalisis, tiempoTest, cont);

		// Se crea una nueva hoja para poner la clasificación de las causas y
		// las correciones
		HSSFSheet hojaClasificacion = resultadosLibro.createSheet();

		// CLASIFICACION
		/*
		 * String titulosClasificacion[] = { "Clasificacion" };
		 * List<List<String>> clasificacion = new ArrayList<List<String>>();
		 * clasificacion.addAll(guardarClasificacion("Causas Comunes",
		 * classifiedCauses.getCommonDiagnosis()));
		 * 
		 * clasificacion.addAll(guardarClasificacion("Causas no Comunes",
		 * classifiedCauses.getNoCommonDiagnosis()));
		 * 
		 * clasificacion.addAll(guardarClasificacion("Correcciones Comunes",
		 * classifiedCorrections.getCommonDiagnosis()));
		 * 
		 * clasificacion.addAll(guardarClasificacion("correcciones no Comunes",
		 * classifiedCorrections.getNoCommonDiagnosis()));
		 * 
		 * // Se pone información en la hoja
		 * ExportUtil.adicionarInfoHoja(Arrays.asList(titulosClasificacion), 0,
		 * hojaClasificacion, clasificacion, tiempoTest, cont);
		 * 
		 * // VALIDACION HSSFSheet hojaValidacionCausas =
		 * resultadosLibro.createSheet(); List<List<String>> validacion = new
		 * ArrayList<List<String>>(); validacion =
		 * validarCausas(causeAnalyzerOutDTO.getAllDiagnostics(),
		 * defectAnalyzerInDTO, "pl/" + nombreFile); // Se pone información en
		 * la hoja. La primer lista tiene el tiempo
		 * ExportUtil.adicionarInfoHoja(Arrays.asList(titulosValidacion), 0,
		 * hojaValidacionCausas, validacion,
		 * Long.valueOf(validacion.get(0).get(1)), cont);
		 */
		String titulosValidacion[] = { "Modelo", "ResultadoEsperado",
				"ResultadoEncontrado", "ResultadoEsperado",
				"ResultadoEncontrado" };
		// Se validan las causas
		HSSFSheet hojaValidacionCorrecc = resultadosLibro.createSheet();
		// Se validan las causas
		List<List<String>> validacionCorrect = new ArrayList<List<String>>();
		validacionCorrect = validarCorrecciones(
				causeAnalyzerOutDTO.getAllDiagnostics(), defectAnalyzerInDTO,
				outputDirectoryPath + fileName);
		// Se pone información en la hoja. La primer lista tiene el tiempo
		ExportUtil.adicionarInfoHoja(Arrays.asList(titulosValidacion), 0,
				hojaValidacionCorrecc, validacionCorrect, 0, cont);

		// Se guarda la hoja xls creada
		String resuladosPath = outputDirectoryPath + fileName + ".xls";
		ExportUtil.guardarXls(resultadosLibro, resuladosPath);
	}

	private List<List<String>> guardarDefectos(String encabezado,
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
	private List<List<String>> guardarClasificacion(String encabezado,
			List<DefectsByMCSMUSes> classifiedCausesCorrectionsList) {

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

		for (DefectsByMCSMUSes defectsByMCSMUSes : classifiedCausesCorrectionsList) {
			List<String> resultadosFila = new ArrayList<String>();
			resultadosFila.add(Long.toString(defectsByMCSMUSes.getId()));
			resultadosFila.add(defectsByMCSMUSes.getDiagnosticElements()
					.toString());
			resultadosFila.add(Integer.toString(defectsByMCSMUSes
					.getDiagnosticElements().size()));
			resultadosFila.add(defectsByMCSMUSes.getDefectsList().toString());
			resultadosFila.add(Integer.toString(defectsByMCSMUSes
					.getDefectsList().size()));
			clasificacion.add(resultadosFila);
		}
		return clasificacion;
	}

	/**
	 * Invoca el verificador de defectos
	 * 
	 * @param defectAnalyzerInDTO
	 * @return VerifierOutDTO
	 * @throws FunctionalException
	 * 
	 */
	private VMVerifierOutDTO verifierOfDefects(
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO)
			throws FunctionalException {

		VMVerifierOutDTO outDTO = new VMVerifierOutDTO();
		// Se construye el DTO de entrada del verificador
		// Make input DTO
		VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();

		// Set transformed variability model
		verifierInDTO.setVariabilityModel(defectAnalyzerInDTO
				.getVariabilityModel());
		// Set Prolog editor type
		verifierInDTO.setPrologEditorType(defectAnalyzerInDTO
				.getPrologEditorType());

		// Create class
		VariabilityModelVerifier verifier = new VariabilityModelVerifier(
				verifierInDTO);

		outDTO = verifier.verifierOfDefects(
				defectAnalyzerInDTO.isVerifyDeadFeatures(),
				defectAnalyzerInDTO.isVerifyFalseOptionalElement(),
				defectAnalyzerInDTO.isVerifyFalseProductLine(),
				defectAnalyzerInDTO.isVerifyNonAttainableDomain(),
				defectAnalyzerInDTO.isVerifyRedundancies());

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
	private VMCauseAnalyzerOutDTO causesAnalyzer(
			DefectAnalyzerControllerInDTO defectAnalyzerInDTO,
			VMVerifierOutDTO verifierOutDTO) throws FunctionalException {

		// Información del DTO
		VMAnalyzerInDTO vmAnalyzerInDTO = new VMAnalyzerInDTO();
		vmAnalyzerInDTO.setVariabilityModel(defectAnalyzerInDTO
				.getVariabilityModel());
		vmAnalyzerInDTO.setPrologEditorType(defectAnalyzerInDTO
				.getPrologEditorType());

		// Se invoca el analizador de causas
		VariabilityModelCausesAndCorrectionsAnalyzer causesAnalyzer = new VariabilityModelCausesAndCorrectionsAnalyzer(
				vmAnalyzerInDTO);

		VMCauseAnalyzerInDTO vmCauseAnalyzerInDTO = new VMCauseAnalyzerInDTO();

		vmCauseAnalyzerInDTO
				.setCorrectionSetIdentifcationType(defectAnalyzerInDTO
						.getCorrectionSetIdentificationType());

		vmCauseAnalyzerInDTO.setFalseProductLine(verifierOutDTO
				.getFalseProductLineModel());
		vmCauseAnalyzerInDTO.setDeadFeaturesList(verifierOutDTO
				.getDeadFeaturesList());
		vmCauseAnalyzerInDTO.setVoidModel(verifierOutDTO.getVoidModel());
		vmCauseAnalyzerInDTO.setFalseOptionalFeaturesList(verifierOutDTO
				.getFalseOptionalFeaturesList());
		vmCauseAnalyzerInDTO.setRedundancies(verifierOutDTO
				.getRedundanciesList());
		VMCauseAnalyzerOutDTO vmCauseAnalyzerOutDTO = causesAnalyzer
				.causesAnalyzer(defectAnalyzerInDTO.isAnalyzeDeadFeatures(),
						defectAnalyzerInDTO.isAnalyzeFalseOptional(),
						defectAnalyzerInDTO.isIdentifyCausesFalseProductLine(),
						defectAnalyzerInDTO.isAnalyzeDomains(),
						defectAnalyzerInDTO.isAnalyzeVoidModel(),
						defectAnalyzerInDTO.isAnalyzeRedundancies(),
						vmCauseAnalyzerInDTO);

		return vmCauseAnalyzerOutDTO;
	}

}
