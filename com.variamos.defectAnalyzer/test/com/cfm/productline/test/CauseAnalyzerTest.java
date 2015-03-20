package com.cfm.productline.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.variamos.core.enums.NotationType;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.core.exceptions.TransformerException;
import com.variamos.defectAnalyzer.defectAnalyzer.CauCosAnayzer;
import com.variamos.defectAnalyzer.defectAnalyzer.DefectsVerifier;
import com.variamos.defectAnalyzer.defectAnalyzer.IntCauCosAnalyzer;
import com.variamos.defectAnalyzer.defectAnalyzer.IntDefectsVerifier;
import com.variamos.defectAnalyzer.dto.DefectAnalyzerResult;
import com.variamos.defectAnalyzer.dto.VMTransformerInDTO;
import com.variamos.defectAnalyzer.model.CauCos;
import com.variamos.defectAnalyzer.model.Dependency;
import com.variamos.defectAnalyzer.model.Diagnosis;
import com.variamos.defectAnalyzer.model.VariabilityModel;
import com.variamos.defectAnalyzer.model.defects.DeadElement;
import com.variamos.defectAnalyzer.model.defects.Defect;
import com.variamos.defectAnalyzer.model.enums.DefectAnalyzerMode;
import com.variamos.defectAnalyzer.transformer.VariabilityModelTransformer;
import com.variamos.defectAnalyzer.util.ConstraintRepresentationUtil;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.HlclUtil;
import com.variamos.hlcl.Identifier;

//import com.cfm.productline.defectAnalyzer.VariabilityModelCauseAnalyzer;

public class CauseAnalyzerTest {

	
	public void testGetCorrectionsOneDefect() {

		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalTesis.sxfm");
		Collection<BooleanExpression> modelExpression = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						new HashMap<Long, Dependency>());
		Collection<BooleanExpression> fixedExpressions = ConstraintRepresentationUtil
				.dependencyToExpressionList(new HashMap<Long, Dependency>(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(modelExpression);
		HlclProgram fixedConstraints = ConstraintRepresentationUtil
				.expressionToHlclProgram(fixedExpressions);

		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		DeadElement deadElement;
		try {
			deadElement = (DeadElement) verifier.isDeadElement(
					identifiers.iterator().next());
			IntCauCosAnalyzer caucosAnalyzer = new CauCosAnayzer(
					SolverEditorType.SWI_PROLOG);
			List<CauCos> corrections = caucosAnalyzer.getCorrections(
					deadElement, model, fixedConstraints,
					DefectAnalyzerMode.COMPLETE);
			assertTrue(corrections.size() == 12);

			// 3. PRINT RESULTS
			System.out
					.println("_________________ RESULTADOS____________________");
			System.out.println("_________________ MCS____________________");
			int i = 1;
			for (CauCos correction : corrections) {

				System.out.println("MCS " + correction.getId() + " :"
						+ correction.getElements().toString());
				i++;
			}

		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	
	public void testGetCaucos() {

		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalTesis.sxfm");
		Collection<BooleanExpression> modelExpression = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						new HashMap<Long, Dependency>());
		Collection<BooleanExpression> fixedExpressions = ConstraintRepresentationUtil
				.dependencyToExpressionList(new HashMap<Long, Dependency>(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(modelExpression);
		HlclProgram fixedConstraints = ConstraintRepresentationUtil
				.expressionToHlclProgram(fixedExpressions);

		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		DeadElement deadElement;
		try {
			deadElement = (DeadElement) verifier.isDeadElement(
					identifiers.iterator().next());
			IntCauCosAnalyzer caucosAnalyzer = new CauCosAnayzer(
					SolverEditorType.SWI_PROLOG);
			Diagnosis diagnosis = caucosAnalyzer.getCauCos(deadElement, model,
					fixedConstraints, DefectAnalyzerMode.COMPLETE);
			assertTrue(diagnosis.getCorrections().size() == 12);
			assertTrue(diagnosis.getCauses().size() == 3);

			System.out.println("corrections time : "
					+ diagnosis.getCorrectionsProcessingTime()
					+ " causes time " + diagnosis.getCausesProcessingTime());

		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	public void testGetCaucosDefects() {

		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalTesis.sxfm");
		Collection<BooleanExpression> modelExpression = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						new HashMap<Long, Dependency>());
		Collection<BooleanExpression> fixedExpressions = ConstraintRepresentationUtil
				.dependencyToExpressionList(new HashMap<Long, Dependency>(),
						variabilityModel.getFixedDependencies());

		HlclProgram model = ConstraintRepresentationUtil
				.expressionToHlclProgram(modelExpression);
		HlclProgram fixedConstraints = ConstraintRepresentationUtil
				.expressionToHlclProgram(fixedExpressions);

		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);

		Set<Identifier> identifiers = HlclUtil.getUsedIdentifiers(model);

		List<Defect> deadElements;
		try {
			deadElements = verifier.getDeadElements(identifiers);
			IntCauCosAnalyzer caucosAnalyzer = new CauCosAnayzer(
					SolverEditorType.SWI_PROLOG);
			DefectAnalyzerResult result = caucosAnalyzer.getCauCos(
					deadElements, model, fixedConstraints,
					DefectAnalyzerMode.COMPLETE);
			assertTrue(result.getAllDiagnosis().size() == 10);
			//assertTrue(result.getClassifiedCorrections().getCommonDiagnosis()
					//.size() == 11);

			System.out.println("total time : " + result.getTotalTime());

		} catch (FunctionalException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// @Test
	// public void testVerifyCausesDeadFeatureSWI() {
	// // VariabilityModel variabilityModel =
	// //
	// transformFeatureModel("test/testModels/Feature10ModelAllFormatsFeatureModelFeatureModel0.splx");
	// // VariabilityModel variabilityModel =
	// //
	// transformFeatureModel("test/testModels/Feature10ModelAllFormatsFeatureModelFeatureModel1.sxfm");
	// // VariabilityModel variabilityModel =
	// //
	// transformFeatureModel("test/testModels/Betty5Features_FeatureModel10.sxfm");
	// // VariabilityModel variabilityModel =
	// // transformFeatureModel("test/testModels/MobileFeatureModelCCC.sfxm");
	//
	// //Termina
	// // VariabilityModel variabilityModel =
	// //
	// transformFeatureModel("test/testModels/Betty25Features_FeatureModel13.sxfm");
	//
	// //F27 demorado
	// VariabilityModel variabilityModel =
	// transformFeatureModel("test/testModels/Betty25Features_FeatureModel0.splx");
	// //
	//
	//
	// // VariabilityModel variabilityModel =
	// //
	// transformFeatureModel("test/testModels/Betty15Features_FeatureModel1.splx");
	// //
	// //
	// // VariabilityModel variabilityModel = transformFeatureModel(
	// // "test/testModels/Betty15Features_FeatureModel9.splx");
	//
	//
	// // VariabilityModel variabilityModel =
	// //
	// transformFeatureModel("test/testModels/Betty50Features_FeatureModel0.splx");
	//
	// // VariabilityModel variabilityModel =
	// //
	// transformFeatureModel("test/testModels/Betty5Features_FeatureModel20.splx");
	//
	// // VariabilityModel variabilityModel =
	// // transformFeatureModel("test/testModels/WebPortal_43_fm.xml");
	//
	// // Transform VariabilityModel
	// //VariabilityModel variabilityModel =
	// transformFeatureModel("test/testModels/WebPortalSimplified_24_fmDefects.sxfm");
	//
	// /*
	// * VariabilityModel variabilityModel = transformFeatureModel(
	// * "test/testModels/Betty100Features_FeatureModel0.splx");
	// */
	//
	// // // Define Prolog editor type ( GNU or SWI)
	// SolverEditorType prologEditorType = SolverEditorType.SWI_PROLOG;
	//
	// // 2. VERIFY DEFECTS IN TRANSFORMED MODEL
	// if (variabilityModel != null) {
	//
	// // Make input verifier inDTO
	// VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
	//
	// // Transfomed variability model
	// verifierInDTO.setVariabilityModel(variabilityModel);
	// // Prolog editor type
	// verifierInDTO.setPrologEditorType(prologEditorType);
	//
	// // CREATE VERIFIER MAIN CLASS
	// DefectsVerifier verifier = new DefectsVerifier(
	// verifierInDTO);
	//
	// try {
	// List<Defect> deadElements = verifier
	// .identifyDeadFeatures(verifierInDTO
	// .getVariabilityModel().getElements());
	//
	// // Información del DTO
	// VMCauseAnalyzerInDTO causeAnalyzerInDTO = new VMCauseAnalyzerInDTO();
	//
	// causeAnalyzerInDTO.setVariabilityModel(variabilityModel);
	// causeAnalyzerInDTO
	// .setCorrectionSetIdentifcationType(CorrectionSetIdentificationType.ALL_MCSes);
	// causeAnalyzerInDTO.setPrologEditorType(prologEditorType);
	//
	// // Se establecen en el DTO de entrada los defectos identificados
	// // del
	// // modelo
	// causeAnalyzerInDTO.setDeadFeaturesList(deadElements);
	//
	// // Se invoca el analizador de causas
	// VariabilityModelCauseAnalyzer causesAnalyzer = new
	// VariabilityModelCauseAnalyzer(
	// causeAnalyzerInDTO);
	// Map<Long, Dependency> fixedDependencies = variabilityModel
	// .getFixedDependencies();
	//
	// Map<Long, Dependency> modelDependencies = new HashMap<Long,
	// Dependency>();
	// modelDependencies.putAll(variabilityModel.getDependencies());
	//
	// Diagnostic diagnosticDeadFeature = null;
	// // Se analizan los MCS
	// long startCorrectionSetTestTime = System.currentTimeMillis();
	//
	// // if (!deadElements.isEmpty()) {
	// // System.out.println("DEAD FEATURE:"
	// // + deadElements.get(0).toString());
	// // diagnosticDeadFeatures = causesAnalyzer
	// // .analizeCausesDeadFeature(deadElements.get(0),
	// // relaxableDependenciesList,
	// // notRelaxableDependencyList);
	// // }
	// List<AnalyzedCorrectionSet> analyzedCorrectionSetList = new
	// ArrayList<AnalyzedCorrectionSet>();
	// List<Diagnostic> allDiagnostics = new ArrayList<Diagnostic>();
	//
	// if (!deadElements.isEmpty()) {
	// for (Defect dead : deadElements) {
	// // if (dead.getId().equals("F5")) {
	// diagnosticDeadFeature = causesAnalyzer
	// .analyzeCausesOneDefect(dead,
	// modelDependencies, fixedDependencies);
	//
	// allDiagnostics.add(diagnosticDeadFeature);
	//
	// // 3. PRINT RESULTS
	// System.out
	// .println("_________________ RESULTADOS____________________");
	// System.out
	// .println("_________________ MCS____________________");
	// int i = 1;
	// for (List<Dependency> mcsSet : diagnosticDeadFeature
	// .getCorrectionSubsets()) {
	// System.out.println("MCS " + i + " :"
	// + mcsSet.toString());
	// i++;
	// }
	//
	// System.out
	// .println("_________________ CAUSES____________________");
	// i = 1;
	// for (List<Dependency> mcsSet : diagnosticDeadFeature
	// .getCauses()) {
	// System.out.println("CAUSES " + i + " :"
	// + mcsSet.toString());
	// i++;
	// }
	//
	// // }
	// }
	//
	// long endCorrectionSetTestTime = System.currentTimeMillis();
	// long totalCorrectionSetTestTime = endCorrectionSetTestTime
	// - startCorrectionSetTestTime;
	//
	// int seconds = (int) ((totalCorrectionSetTestTime / 1000) % 60);
	// int minutes = (int) ((totalCorrectionSetTestTime / 1000) / 60);
	// System.out.println(("minutes " + minutes + "seg" + seconds
	// + " mils: " + totalCorrectionSetTestTime));
	//
	// System.out
	// .println("ANALIZANDO LOS SUBCONJUNTOS CORRECCIÓN");
	//
	// long startAnalysisTime = System.currentTimeMillis();
	// // for (Diagnostic diagnostic : allDiagnostics) {
	// // int i = 1;
	// // for (List<Dependency> mcsSet : diagnostic
	// // .getCorrectionSubsets()) {
	// // AnalyzedCorrectionSet analyzedCorrectionSet =
	// // causesAnalyzer
	// // .analyzeCorrectionSets(mcsSet, new Long(i),
	// // diagnostic.getDefect());
	// // analyzedCorrectionSetList
	// // .add(analyzedCorrectionSet);
	// // i++;
	// // }
	// // }
	//
	// System.out.println(" Resultado análisis causas");
	// // for (AnalyzedCorrectionSet analizedCorrectionSet :
	// // analyzedCorrectionSetList) {
	// // System.out.println("Defect "
	// // + analizedCorrectionSet.getAnalyzedDefect());
	// // System.out.println("MCS id: "
	// // + analizedCorrectionSet.getId());
	// // System.out.println("MCS size: "
	// // + analizedCorrectionSet.getCorrectionSubsets()
	// // .size());
	// // System.out.println("MCS content "
	// // + analizedCorrectionSet.getId()
	// // + " :"
	// // + analizedCorrectionSet.getCorrectionSubsets()
	// // .toString());
	// // System.out.println("Defect verification");
	// // DefectsVerifier
	// // .printFoundDefects(analizedCorrectionSet
	// // .getVerifierOutDTO());
	// //
	// // System.out.println("_____________________________");
	// // }
	// //
	// // long endAnalysisTime = System.currentTimeMillis();
	// // long totalAnalysysTime = endAnalysisTime
	// // - startAnalysisTime;
	// // System.out.println("Correction set analisis time");
	// // seconds = (int) ((totalAnalysysTime / 1000) % 60);
	// // minutes = (int) ((totalAnalysysTime / 1000) / 60);
	// // System.out.println(("minutes " + minutes + "seg" +
	// // seconds
	// // + " mils: " + totalAnalysysTime));
	//
	// }
	// } catch (FunctionalException e) {
	// e.printStackTrace();
	// fail();
	// }
	// }
	//
	// }
	//
	//
	// public void testCausesVoidModel() {
	// // VariabilityModel variabilityModel =
	// //
	// transformFeatureModel("test/testModels/voidModels/Feature10ModelAllFormatsFeatureModelFeatureModel11.splx");
	//
	// // String modelInputPath =
	// //
	// "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\prologFiles\\Rexel_raul_29_06_2013.pl";
	// // String modelInputPath =
	// //
	// "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\prologFiles\\UNIX_model_1.pl";
	//
	// SolverEditorType solverEditorType = SolverEditorType.SWI_PROLOG;
	// // VariabilityModel variabilityModel = transformBooleanPrologFile(
	// // modelInputPath, solverEditorType);
	//
	// // SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;
	//
	// // Transform VariabilityModel
	// VariabilityModel variabilityModel =
	// transformFeatureModel("test/testModels/WebPortalSimplified_24_fmDefects.sxfm");
	//
	// // 2. VERIFY DEFECTS IN TRANSFORMED MODEL
	// if (variabilityModel != null) {
	//
	// // Make input verifier inDTO
	// VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
	//
	// // Transfomed variability model
	// verifierInDTO.setVariabilityModel(variabilityModel);
	// // Prolog editor type
	// verifierInDTO.setPrologEditorType(solverEditorType);
	//
	// // CREATE VERIFIER MAIN CLASS
	// DefectsVerifier verifier = new DefectsVerifier(
	// verifierInDTO);
	//
	// try {
	// boolean isVoid = verifier.isVoid();
	// System.out.println("El modelo es vacío");
	//
	// VoidModel voidModelDefect = new VoidModel(
	// variabilityModel.getModelName());
	//
	// // Información del DTO
	// VMCauseAnalyzerInDTO causeAnalyzerInDTO = new VMCauseAnalyzerInDTO();
	//
	// causeAnalyzerInDTO.setVariabilityModel(variabilityModel);
	// causeAnalyzerInDTO
	// .setCorrectionSetIdentifcationType(CorrectionSetIdentificationType.SMALLEST_MCSes);
	// causeAnalyzerInDTO.setPrologEditorType(solverEditorType);
	//
	// // Se invoca el analizador de causas
	// VariabilityModelCauseAnalyzer causesAnalyzer = new
	// VariabilityModelCauseAnalyzer(
	// causeAnalyzerInDTO);
	// Map<Long, Dependency> fixedDependencies = variabilityModel
	// .getFixedDependencies();
	//
	// Map<Long, Dependency> modelDependencies = new HashMap<Long,
	// Dependency>();
	// modelDependencies.putAll(variabilityModel.getDependencies());
	//
	// Diagnostic diagnosticVoidFeatures = null;
	// List<Diagnostic> allDiagnostics = new ArrayList<Diagnostic>();
	// // Se analizan los MCS
	// long startCorrectionSetTestTime = System.currentTimeMillis();
	//
	// if (isVoid) {
	//
	// diagnosticVoidFeatures = causesAnalyzer
	// .analyzeCausesOneDefect(voidModelDefect,
	// modelDependencies, fixedDependencies);
	// allDiagnostics.add(diagnosticVoidFeatures);
	// // 3. PRINT RESULTS
	// System.out
	// .println("_________________ RESULTADOS____________________");
	// System.out
	// .println("_________________ MCS____________________");
	// int i = 1;
	// for (List<Dependency> mcsSet : diagnosticVoidFeatures
	// .getCorrectionSubsets()) {
	// System.out.println("MCS " + i + " :"
	// + mcsSet.toString());
	// i++;
	// }
	//
	// System.out
	// .println("_________________ CAUSES____________________");
	// for (List<Dependency> mcsSet : diagnosticVoidFeatures
	// .getCauses()) {
	// System.out.println("CAUSES" + mcsSet.toString());
	// }
	// }
	//
	// long endCorrectionSetTestTime = System.currentTimeMillis();
	// long totalCorrectionSetTestTime = endCorrectionSetTestTime
	// - startCorrectionSetTestTime;
	//
	// int seconds = (int) ((totalCorrectionSetTestTime / 1000) % 60);
	// int minutes = (int) ((totalCorrectionSetTestTime / 1000) / 60);
	// System.out.println(("minutes " + minutes + "seg" + seconds
	// + " mils: " + totalCorrectionSetTestTime));
	//
	// System.out.println("ANALIZANDO LOS SUBCONJUNTOS CORRECCIÓN");
	//
	// long startAnalysisTime = System.currentTimeMillis();
	// List<AnalyzedCorrectionSet> analyzedCorrectionSetList = new
	// ArrayList<AnalyzedCorrectionSet>();
	// for (Diagnostic diagnostic : allDiagnostics) {
	// int i = 1;
	// for (List<Dependency> mcsSet : diagnostic
	// .getCorrectionSubsets()) {
	// AnalyzedCorrectionSet analyzedCorrectionSet = causesAnalyzer
	// .analyzeCorrectionSets(mcsSet, new Long(i),
	// diagnostic.getDefect());
	// analyzedCorrectionSetList.add(analyzedCorrectionSet);
	// i++;
	// }
	// }
	//
	// System.out.println(" Resultado análisis causas");
	// for (AnalyzedCorrectionSet analizedCorrectionSet :
	// analyzedCorrectionSetList) {
	// System.out.println("Defect "
	// + analizedCorrectionSet.getAnalyzedDefect());
	// System.out.println("MCS id: "
	// + analizedCorrectionSet.getId());
	// System.out.println("MCS size: "
	// + analizedCorrectionSet.getCorrectionSubsets()
	// .size());
	// System.out.println("MCS content "
	// + analizedCorrectionSet.getId()
	// + " :"
	// + analizedCorrectionSet.getCorrectionSubsets()
	// .toString());
	// System.out.println("Defect verification");
	// DefectsVerifier
	// .printFoundDefects(analizedCorrectionSet
	// .getVerifierOutDTO());
	//
	// System.out.println("_____________________________");
	// }
	//
	// long endAnalysisTime = System.currentTimeMillis();
	// long totalAnalysysTime = endAnalysisTime - startAnalysisTime;
	// System.out.println("Correction set analisis time");
	// seconds = (int) ((totalAnalysysTime / 1000) % 60);
	// minutes = (int) ((totalAnalysysTime / 1000) / 60);
	// System.out.println(("minutes " + minutes + "seg" + seconds
	// + " mils: " + totalAnalysysTime));
	//
	// } catch (FunctionalException e) {
	// e.printStackTrace();
	// fail();
	// }
	// }
	// }
	//
	// @Test
	// public void testCausesFalseProductLineDeadFeaturesFalseOptionalFeatures()
	// {
	// // VariabilityModel variabilityModel =
	// //
	// transformFeatureModel("test/testModels/voidModels/Feature10ModelAllFormatsFeatureModelFeatureModel11.splx");
	//
	// String modelInputPath =
	// "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\prologFiles\\Rexel_raul_29_06_2013.pl";
	// // String modelInputPath =
	// //
	// "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\prologFiles\\UNIX_model_1.pl";
	//
	// SolverEditorType solverEditorType = SolverEditorType.GNU_PROLOG;
	// VariabilityModel variabilityModel = transformBooleanPrologFile(
	// modelInputPath, solverEditorType);
	//
	// // SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;
	//
	// // // Transform VariabilityModel
	// // VariabilityModel variabilityModel =
	// transformFeatureModel("test/testModels/WebPortalSimplified_24_fmDefects.sxfm");
	//
	// // 2. VERIFY DEFECTS IN TRANSFORMED MODEL
	// if (variabilityModel != null) {
	//
	// // Make input verifier inDTO
	// VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
	//
	// // Transfomed variability model
	// verifierInDTO.setVariabilityModel(variabilityModel);
	// // Prolog editor type
	// verifierInDTO.setPrologEditorType(solverEditorType);
	//
	// // CREATE VERIFIER MAIN CLASS
	// DefectsVerifier verifier = new DefectsVerifier(
	// verifierInDTO);
	//
	// List<Defect> deadElements;
	// try {
	// deadElements = verifier.identifyDeadFeatures(verifierInDTO
	// .getVariabilityModel().getElements());
	//
	// // Información del DTO
	// VMCauseAnalyzerInDTO causeAnalyzerInDTO = new VMCauseAnalyzerInDTO();
	// causeAnalyzerInDTO.setVariabilityModel(variabilityModel);
	// causeAnalyzerInDTO
	// .setCorrectionSetIdentifcationType(CorrectionSetIdentificationType.SMALLEST_MCSes);
	// causeAnalyzerInDTO.setPrologEditorType(solverEditorType);
	//
	// // Se establecen en el DTO de entrada los defectos identificados
	// // del
	// // modelo
	// causeAnalyzerInDTO.setDeadFeaturesList(deadElements);
	//
	// // Se invoca el analizador de causas
	// VariabilityModelCauseAnalyzer causesAnalyzer = new
	// VariabilityModelCauseAnalyzer(
	// causeAnalyzerInDTO);
	// Map<Long, Dependency> fixedDependencies = variabilityModel
	// .getFixedDependencies();
	//
	// Map<Long, Dependency> modelDependencies = new HashMap<Long,
	// Dependency>();
	// modelDependencies.putAll(variabilityModel.getDependencies());
	//
	// Diagnostic diagnosticDeadFeature = null;
	// // Se analizan los MCS
	// long startCorrectionSetTestTime = System.currentTimeMillis();
	//
	// List<AnalyzedCorrectionSet> analyzedCorrectionSetList = new
	// ArrayList<AnalyzedCorrectionSet>();
	// List<Diagnostic> allDiagnostics = new ArrayList<Diagnostic>();
	//
	// if (!deadElements.isEmpty()) {
	// for (Defect dead : deadElements) {
	// // if (dead.getId().equals("F5")) {
	// diagnosticDeadFeature = causesAnalyzer
	// .analyzeCausesOneDefect(dead,
	// modelDependencies, fixedDependencies);
	//
	// allDiagnostics.add(diagnosticDeadFeature);
	//
	// // 3. PRINT RESULTS
	// System.out
	// .println("_________________ RESULTADOS____________________");
	// System.out
	// .println("_________________ MCS____________________");
	// diagnosticDeadFeature.printMCSes();
	//
	// System.out
	// .println("_________________ CAUSES____________________");
	// diagnosticDeadFeature.printCauses();
	//
	// // }
	// }
	//
	// long endCorrectionSetTestTime = System.currentTimeMillis();
	// long totalCorrectionSetTestTime = endCorrectionSetTestTime
	// - startCorrectionSetTestTime;
	//
	// int seconds = (int) ((totalCorrectionSetTestTime / 1000) % 60);
	// int minutes = (int) ((totalCorrectionSetTestTime / 1000) / 60);
	// System.out.println(("minutes " + minutes + "seg" + seconds
	// + " mils: " + totalCorrectionSetTestTime));
	//
	// System.out
	// .println("ANALIZANDO LOS SUBCONJUNTOS CORRECCIÓN");
	//
	// long startAnalysisTime = System.currentTimeMillis();
	// // for (Diagnostic diagnostic : allDiagnostics) {
	// // int i = 1;
	// // for (List<Dependency> mcsSet : diagnostic
	// // .getCorrectionSubsets()) {
	// // AnalyzedCorrectionSet analyzedCorrectionSet =
	// // causesAnalyzer
	// // .analyzeCorrectionSets(mcsSet, new Long(i),
	// // diagnostic.getDefect());
	// // analyzedCorrectionSetList
	// // .add(analyzedCorrectionSet);
	// // i++;
	// // }
	// // }
	//
	// // System.out.println(" Resultado análisis causas");
	// // for (AnalyzedCorrectionSet analizedCorrectionSet :
	// // analyzedCorrectionSetList) {
	// // System.out.println("Defect "
	// // + analizedCorrectionSet.getAnalyzedDefect());
	// // System.out.println("MCS id: "
	// // + analizedCorrectionSet.getId());
	// // System.out.println("MCS size: "
	// // + analizedCorrectionSet.getCorrectionSubsets()
	// // .size());
	// // System.out.println("MCS content "
	// // + analizedCorrectionSet.getId()
	// // + " :"
	// // + analizedCorrectionSet.getCorrectionSubsets()
	// // .toString());
	// // System.out.println("Defect verification");
	// // DefectsVerifier
	// // .printFoundDefects(analizedCorrectionSet
	// // .getVerifierOutDTO());
	// //
	// // System.out.println("_____________________________");
	// // }
	// //
	// // long endAnalysisTime = System.currentTimeMillis();
	// // long totalAnalysysTime = endAnalysisTime
	// // - startAnalysisTime;
	// // System.out.println("Correction set analisis time");
	// // seconds = (int) ((totalAnalysysTime / 1000) % 60);
	// // minutes = (int) ((totalAnalysysTime / 1000) / 60);
	// // System.out.println(("minutes " + minutes + "seg" +
	// // seconds
	// // + " mils: " + totalAnalysysTime));
	//
	// }
	//
	// } catch (FunctionalException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// private VariabilityModel transformFeatureModel(String modelInputPath) {
	// // Se instancia el transformador
	// VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
	// transformerInDTO.setNotationType(NotationType.FEATURES_MODELS);
	// transformerInDTO.setPathToTransform(modelInputPath);
	//
	// VariabilityModel variabilityModel = null;
	// VariabilityModelTransformer transformer = new
	// VariabilityModelTransformer(
	// transformerInDTO);
	// try {
	// variabilityModel = transformer.transformToVariabilityModel();
	// Assert.assertTrue(variabilityModel != null);
	// System.out.println("The FM was correctly transformed \n");
	// return variabilityModel;
	// } catch (TransformerException e) {
	// e.printStackTrace();
	// fail();
	// }
	//
	// return null;
	// }
	//
	// private VariabilityModel transformBooleanPrologFile(String
	// modelInputPath,
	// SolverEditorType solverEditorType) {
	//
	// // Se instancia el transformador
	// VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
	// transformerInDTO.setNotationType(NotationType.PROLOG);
	// transformerInDTO.setPrologEditorTypeFileToTransform(solverEditorType);
	// transformerInDTO.setPathToTransform(modelInputPath);
	//
	// VariabilityModel variabilityModel = null;
	// VariabilityModelTransformer transformer = new
	// VariabilityModelTransformer(
	// transformerInDTO);
	//
	// try {
	// variabilityModel = transformer.transformToVariabilityModel();
	//
	// return variabilityModel;
	// } catch (TransformerException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// fail();
	// }
	//
	// return null;
	// }

	private VariabilityModel transformFeatureModel(String modelInputPath) {
		// Se instancia el transformador
		VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
		transformerInDTO.setNotationType(NotationType.FEATURES_MODELS);
		transformerInDTO.setPathToTransform(modelInputPath);

		VariabilityModel variabilityModel = null;
		VariabilityModelTransformer transformer = new VariabilityModelTransformer(
				transformerInDTO);
		try {
			variabilityModel = transformer.transformToVariabilityModel();
			assertTrue(variabilityModel != null);
			System.out.println("The FM was correctly transformed \n");
			return variabilityModel;
		} catch (TransformerException e) {
			e.printStackTrace();

		}

		return null;
	}

}
