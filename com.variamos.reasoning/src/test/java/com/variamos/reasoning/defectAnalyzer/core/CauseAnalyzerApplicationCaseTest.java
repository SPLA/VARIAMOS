package com.variamos.reasoning.defectAnalyzer.core;

//FIXME: Remove this clase one the variabiliy model be removed
//package com.cfm.productline.test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.tools.Diagnostic;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import com.cfm.productline.VariabilityElement;
//import com.variamos.common.core.exceptions.FunctionalException;
//import com.variamos.common.core.exceptions.TransformerException;
//import com.variamos.common.model.enums.NotationType;
//import com.variamos.common.model.enums.SolverEditorType;
//import com.variamos.reasoning.defectAnalyzer.DefectsVerifier;
//import com.variamos.reasoning.defectAnalyzer.dto.AnalyzedCorrectionSet;
//import com.variamos.reasoning.defectAnalyzer.dto.VMAnalyzerInDTO;
//import com.variamos.reasoning.defectAnalyzer.dto.VMCauseAnalyzerInDTO;
//import com.variamos.reasoning.defectAnalyzer.dto.VMTransformerInDTO;
//import com.variamos.reasoning.defectAnalyzer.model.Dependency;
//import com.variamos.reasoning.defectAnalyzer.model.VariabilityModel;
//import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;
//import com.variamos.reasoning.defectAnalyzer.model.defects.VoidModel;
//import com.variamos.reasoning.transformer.VariabilityModelTransformer;
//
public class CauseAnalyzerApplicationCaseTest {
	//
	// public void testCausesVoidModel() {
	//
	// SolverEditorType solverEditorType = SolverEditorType.SWI_PROLOG;
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
	// .setCorrectionSetIdentifcationType(CorrectionSetIdentificationType.ALL_MCSes);
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
	// printResults(diagnosticVoidFeatures);
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
	// analyzeMCS(causesAnalyzer, allDiagnostics);
	// } catch (FunctionalException e) {
	// e.printStackTrace();
	// fail();
	// }
	// }
	// }
	//
	// public void testVerifyCausesDeadFalseOptionalFeatNonAttaianbleDomains() {
	//
	// VariabilityModel variabilityModel =
	// transformFeatureModel("test/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
	// // VariabilityModel variabilityModel =
	// //
	// transformFeatureModel("test/testModels/WebPortalSimplified_24_fmOnlyRedundancies.sxfm");
	// // Define Prolog editor type ( GNU or SWI)
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
	// try {
	//
	// // Dead features
	// List<Defect> deadFeatures = verifier
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
	// causeAnalyzerInDTO.setDeadFeaturesList(deadFeatures);
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
	// Diagnostic diagnostic = null;
	// // Se analizan los MCS
	// long startCausesSetTestTime = System.currentTimeMillis();
	//
	// List<Diagnostic> allDiagnostics = new ArrayList<Diagnostic>();
	//
	// if (!deadFeatures.isEmpty()) {
	// for (Defect dead : deadFeatures) {
	// // if (dead.getId().equals("F5")) {
	// diagnostic = causesAnalyzer.analyzeCausesOneDefect(
	// dead, modelDependencies, fixedDependencies);
	//
	// allDiagnostics.add(diagnostic);
	//
	// // 3. PRINT RESULTS
	// printResults(diagnostic);
	//
	// }
	//
	// long endCorrectionSetTestTime = System.currentTimeMillis();
	// long totalCorrectionSetTestTime = endCorrectionSetTestTime
	// - startCausesSetTestTime;
	//
	// int seconds = (int) ((totalCorrectionSetTestTime / 1000) % 60);
	// int minutes = (int) ((totalCorrectionSetTestTime / 1000) / 60);
	// System.out.println(("minutes " + minutes + "seg" + seconds
	// + " mils: " + totalCorrectionSetTestTime));
	//
	// }
	//
	// // False optional features
	// System.out.println("False optional features");
	// startCausesSetTestTime = System.currentTimeMillis();
	// List<Defect> falseOptionalFeatures = verifier
	// .identifyFalseOptionalFeatures(verifierInDTO
	// .getVariabilityModel()
	// .getOptionalVariabilityElements());
	//
	// if (!falseOptionalFeatures.isEmpty()) {
	// for (Defect noneAttainableDefect : falseOptionalFeatures) {
	// diagnostic = causesAnalyzer.analyzeCausesOneDefect(
	// noneAttainableDefect, modelDependencies,
	// fixedDependencies);
	//
	// allDiagnostics.add(diagnostic);
	//
	// // 3. PRINT RESULTS
	// printResults(diagnostic);
	//
	// }
	//
	// System.out.println("Time false optional features");
	// long endCorrectionSetTestTime = System.currentTimeMillis();
	// long totalCorrectionSetTestTime = endCorrectionSetTestTime
	// - startCausesSetTestTime;
	// int seconds = (int) ((totalCorrectionSetTestTime / 1000) % 60);
	// int minutes = (int) ((totalCorrectionSetTestTime / 1000) / 60);
	// System.out.println(("minutes " + minutes + "seg" + seconds
	// + " mils: " + totalCorrectionSetTestTime));
	//
	// }
	//
	// // None Attainable domains
	// System.out
	// .println("___________Domains none attainables________--");
	// startCausesSetTestTime = System.currentTimeMillis();
	//
	// Map<String, VariabilityElement> elementsToVerifyDomainNoneAttainable =
	// verifierInDTO
	// .getVariabilityModel().getElements();
	//
	// // Se quitan de la lista las características que son full
	// // mandatory, pq de esas se sabe que no pueden tener el valor de
	// // cero
	// elementsToVerifyDomainNoneAttainable.remove("Web_Portal");
	// elementsToVerifyDomainNoneAttainable.remove("Web_Server");
	// elementsToVerifyDomainNoneAttainable.remove("Content");
	// elementsToVerifyDomainNoneAttainable.remove("Static");
	//
	// List<Defect> noneAttainableDomains = verifier
	// .identifyNonAttainableDomains(elementsToVerifyDomainNoneAttainable);
	//
	// if (!noneAttainableDomains.isEmpty()) {
	// for (Defect noneAttainable : noneAttainableDomains) {
	// diagnostic = causesAnalyzer.analyzeCausesOneDefect(
	// noneAttainable, modelDependencies,
	// fixedDependencies);
	//
	// allDiagnostics.add(diagnostic);
	//
	// // 3. PRINT RESULTS
	// printResults(diagnostic);
	//
	// }
	//
	// System.out.println("Time none attainable domains features");
	// long endCorrectionSetTestTime = System.currentTimeMillis();
	// long totalCorrectionSetTestTime = endCorrectionSetTestTime
	// - startCausesSetTestTime;
	// int seconds = (int) ((totalCorrectionSetTestTime / 1000) % 60);
	// int minutes = (int) ((totalCorrectionSetTestTime / 1000) / 60);
	// System.out.println(("minutes " + minutes + "seg" + seconds
	// + " mils: " + totalCorrectionSetTestTime));
	//
	// }
	//
	// } catch (FunctionalException e) {
	// e.printStackTrace();
	// fail();
	// }
	// }
	//
	// }
	//
	// @Test
	// public void testVerifyCausesRedundancies() {
	//
	// // Transform VariabilityModel
	// VariabilityModel variabilityModel =
	// transformFeatureModel("test/testModels/WebPortalSimplified_24_fmOnlyRedundancies.sxfm");
	// // Define Prolog editor type ( GNU or SWI)
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
	// try {
	//
	// // Se prueba con una relación que sí sea redundante
	// // Dependency: Flash-Image
	// // id 25 (1-Flash)+ Image #>0
	// // negacion (1-Flash)+ Image #=<0
	//
	// String negationExpression = "((1-Flash)+ Image #=<0)";
	// Long dependencyNumber = 25L;
	// Defect redundancy1 = verifier.identifyRedundancy(
	// negationExpression, variabilityModel.getDependencies()
	// .get(dependencyNumber));
	// Assert.assertTrue(redundancy1 != null);
	//
	// // Dependency: ASP-AD_SERVER
	// // id 24 (1-Asp)+ Ad_Server #>0
	// // negacion (1-Asp)+ Ad_Server #=<0
	// negationExpression = "((1-ASP)+ Ad_Server #=<0)";
	// dependencyNumber = 24L;
	// Defect redundancy2 = verifier.identifyRedundancy(
	// negationExpression, variabilityModel.getDependencies()
	// .get(dependencyNumber));
	// Assert.assertTrue(redundancy2 != null);
	//
	// // Se crea una lista para guardar los defectos
	// List<Defect> redundacyDefects = new ArrayList<Defect>();
	// redundacyDefects.add(redundancy1);
	// redundacyDefects.add(redundancy2);
	//
	// // Información del DTO
	// VMCauseAnalyzerInDTO causeAnalyzerInDTO = new VMCauseAnalyzerInDTO();
	//
	// causeAnalyzerInDTO.setVariabilityModel(variabilityModel);
	// causeAnalyzerInDTO
	// .setCorrectionSetIdentifcationType(CorrectionSetIdentificationType.ALL_MCSes);
	// causeAnalyzerInDTO.setPrologEditorType(prologEditorType);
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
	// Diagnostic diagnostic = null;
	// // Se analizan los MCS
	// long startCausesSetTestTime = System.currentTimeMillis();
	//
	// List<Diagnostic> allDiagnostics = new ArrayList<Diagnostic>();
	//
	// if (!redundacyDefects.isEmpty()) {
	// for (Defect redundancy : redundacyDefects) {
	// diagnostic = causesAnalyzer.analyzeCausesOneDefect(
	// redundancy, modelDependencies,
	// fixedDependencies);
	// allDiagnostics.add(diagnostic);
	//
	// Assert.assertTrue(diagnostic != null);
	//
	// // 3. PRINT RESULTS
	// printResults(diagnostic);
	//
	// }
	//
	// long endCorrectionSetTestTime = System.currentTimeMillis();
	// long totalCorrectionSetTestTime = endCorrectionSetTestTime
	// - startCausesSetTestTime;
	//
	// int seconds = (int) ((totalCorrectionSetTestTime / 1000) % 60);
	// int minutes = (int) ((totalCorrectionSetTestTime / 1000) / 60);
	// System.out.println(("minutes " + minutes + "seg" + seconds
	// + " mils: " + totalCorrectionSetTestTime));
	//
	// }
	//
	// } catch (FunctionalException e) {
	// e.printStackTrace();
	// fail();
	// }
	// }
	//
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
	// private void analyzeMCS(VariabilityModelCauseAnalyzer causesAnalyzer,
	// List<Diagnostic> allDiagnostics) throws FunctionalException {
	// System.out.println("ANALIZANDO LOS SUBCONJUNTOS CORRECCIÓN");
	// long startAnalysisTime = System.currentTimeMillis();
	// List<AnalyzedCorrectionSet> analyzedCorrectionSetList = new
	// ArrayList<AnalyzedCorrectionSet>();
	// for (Diagnostic diagnostic : allDiagnostics) {
	// int i = 1;
	// for (List<Dependency> mcsSet : diagnostic.getCorrectionSubsets()) {
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
	// System.out.println("MCS id: " + analizedCorrectionSet.getId());
	// System.out.println("MCS size: "
	// + analizedCorrectionSet.getCorrectionSubsets().size());
	// System.out.println("MCS content " + analizedCorrectionSet.getId()
	// + " :"
	// + analizedCorrectionSet.getCorrectionSubsets().toString());
	// System.out.println("Defect verification");
	// DefectsVerifier.printFoundDefects(analizedCorrectionSet
	// .getVerifierOutDTO());
	//
	// System.out.println("_____________________________");
	// }
	//
	// long endAnalysisTime = System.currentTimeMillis();
	// long totalAnalysysTime = endAnalysisTime - startAnalysisTime;
	// System.out.println("Correction set analisis time");
	// int seconds = (int) ((totalAnalysysTime / 1000) % 60);
	// int minutes = (int) ((totalAnalysysTime / 1000) / 60);
	// System.out
	// .println(("minutes " + minutes + "seg" + seconds + " mils: " +
	// totalAnalysysTime));
	// }
	//
	// private void printResults(Diagnostic diagnostic) {
	// // 3. PRINT RESULTS
	// System.out.println("_________________ RESULTADOS____________________");
	// System.out.println("_________________ MCS____________________");
	// diagnostic.printMCSes();
	//
	// System.out.println("_________________ CAUSES____________________");
	// diagnostic.printCauses();
	//
	// }
	//
	//
	//
}
