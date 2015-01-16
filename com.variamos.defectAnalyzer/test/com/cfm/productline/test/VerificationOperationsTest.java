package com.cfm.productline.test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.HlclProgram;
import com.variamos.core.enums.NotationType;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.TransformerException;
import com.variamos.defectAnalyzer.defectAnalyzer.DefectsVerifier;
import com.variamos.defectAnalyzer.defectAnalyzer.IntDefectsVerifier;
import com.variamos.defectAnalyzer.dto.VMAnalyzerInDTO;
import com.variamos.defectAnalyzer.dto.VMTransformerInDTO;
import com.variamos.defectAnalyzer.model.VariabilityModel;
import com.variamos.defectAnalyzer.model.defects.VoidModel;
import com.variamos.defectAnalyzer.transformer.VariabilityModelTransformer;
import com.variamos.defectAnalyzer.util.ConstraintRepresentationUtil;

public class VerificationOperationsTest {


	// public void testVerifyDefectsModel() {
	//
	// // Transform VariabilityModel
	// // VariabilityModel variabilityModel =
	// //
	// transformFeatureModel("test/testModels/WebPortalSimplified_24_fmFalseProductLine.sxfm");
	// VariabilityModel variabilityModel =
	// transformFeatureModel("test/testModels/WebPortalSimplified_24_fmDefects.sxfm");
	//
	// // Define Prolog editor type ( GNU or SWI)
	// SolverEditorType prologEditorType = SolverEditorType.SWI_PROLOG;
	//
	// if (variabilityModel != null) {
	//
	// // Make input DTO
	// VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
	//
	// // Set transformed variability model
	// verifierInDTO.setVariabilityModel(variabilityModel);
	// // Set Prolog editor type
	// verifierInDTO.setPrologEditorType(prologEditorType);
	//
	// // Create class
	// DefectsVerifier verifier = new DefectsVerifier(
	// verifierInDTO);
	//
	// try {
	//
	// // Void model
	// System.out.println("Void model: " + verifier.isVoid());
	//
	// // False product line
	// System.out
	// .println("False product line" + verifier.isFalsePLM());
	//
	// // Dead features
	// // Invoke method
	// List<Defect> deadElements = verifier
	// .identifyDeadFeatures(verifierInDTO
	// .getVariabilityModel().getElements());
	// System.out.println("Dead features");
	// for (Defect deadElement : deadElements) {
	// System.out.println("DEAD FEATURE " + deadElement.getId());
	// }
	// // False optional features
	// System.out.println("False optional features");
	// List<Defect> falseOptionalElements = verifier
	// .identifyFalseOptionalFeatures(verifierInDTO
	// .getVariabilityModel()
	// .getOptionalVariabilityElements());
	//
	// for (Defect falseOptionalElement : falseOptionalElements) {
	// System.out.println("FALSE OPTIONAL FEATURES "
	// + falseOptionalElement.getId());
	// }
	//
	// // Non attainable domain
	// System.out.println("none attainable domain");
	//
	// List<Defect> domainNotAttainableDefect = verifier
	// .identifyNonAttainableDomains(verifierInDTO
	// .getVariabilityModel().getElements());
	// for (Defect nonAttainableDomain : domainNotAttainableDefect) {
	//
	// Integer nonAttainableValue = ((NonAttainableDomain) nonAttainableDomain)
	// .getNotAttainableDomain();
	// System.out.println("NON ATTAINABLE DOMAIN "
	// + nonAttainableDomain.getId() + " VALUE : "
	// + nonAttainableValue);
	// }
	//
	// // Redundancias
	// } catch (FunctionalException e) {
	// fail();
	// }
	// }
	// }
	//
	// @Test
	// public void testVerifyRedundancySWI() {
	//
	// // Transform VariabilityModel
	// VariabilityModel variabilityModel =
	// transformFeatureModel("test/testModels/WebPortalSimplified_24_fmOnlyRedundancies.sxfm");
	//
	// // Define Prolog editor type ( GNU or SWI)
	// SolverEditorType prologEditorType = SolverEditorType.SWI_PROLOG;
	// if (variabilityModel != null) {
	//
	// // Make input DTO
	// VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
	//
	// // Set transformed variability model
	// verifierInDTO.setVariabilityModel(variabilityModel);
	// // Set Prolog editor type
	// verifierInDTO.setPrologEditorType(prologEditorType);
	//
	// // Create class
	// DefectsVerifier verifier = new DefectsVerifier(
	// verifierInDTO);
	//
	// try {
	// // Invoke method
	// //7=Web_Portal to Web_Server
	// //Web_Portal <==> Web_Server
	// //Negacion ( Web_Portal #/\(1-Web_Server #>0)#\/ ( Web_Server #/\
	// (1-Web_Portal #>0)))
	// //Esta relación no es redundante
	// //String
	// negationExpression="( Web_Portal #/\\(1-Web_Server #>0)#\\/ ( Web_Server #/\\  (1-Web_Portal #>0)))";
	// String
	// negationExpression="( Web_Portal * (1-Web_Server)+ ( Web_Server * (1-Web_Portal)) #>0)";
	// Long dependencyNumber=7L;
	// Defect redundancy = verifier
	// .identifyRedundancy(negationExpression,
	// variabilityModel.getDependencies().get(dependencyNumber));
	//
	// // Test Results
	// Assert.assertTrue(redundancy==null);
	//
	//
	// //Se prueba con una relación que sí sea redundante
	// // Dependency: Flash-Image
	// // id 25 (1-Flash)+ Image #>0
	// // negacion (1-Flash)+ Image #=<0
	// negationExpression="((1-Flash)+ Image #=<0)";
	// dependencyNumber=25L;
	// redundancy = verifier
	// .identifyRedundancy(negationExpression,
	// variabilityModel.getDependencies().get(dependencyNumber));
	// //Si es redundante
	// Assert.assertTrue(redundancy!=null);
	//
	// // Assert.assertTrue(deadElements.size() == 2);
	// // Assert.assertTrue(deadElements.get(0).getId().equals("F1")
	// // || deadElements.get(0).getId().equals("F4"));
	// // Assert.assertTrue(deadElements.get(1).getId().equals("F1")
	// // || deadElements.get(1).getId().equals("F4"));
	//
	// // Print results
	// System.out
	// .println("_________________ VERIFICATION RESULTS_____________________");
	//
	// } catch (FunctionalException e) {
	// fail();
	// e.getMessage();
	// }
	// }
	// }
	//
	// public void testVerifyDeadFeatureSWI() {
	//
	// // Transform VariabilityModel
	// VariabilityModel variabilityModel =
	// transformFeatureModel("test/testModels/Betty5Features_FeatureModel10.sxfm");
	//
	// // Define Prolog editor type ( GNU or SWI)
	// SolverEditorType prologEditorType = SolverEditorType.SWI_PROLOG;
	//
	// if (variabilityModel != null) {
	//
	// // Make input DTO
	// VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
	//
	// // Set transformed variability model
	// verifierInDTO.setVariabilityModel(variabilityModel);
	// // Set Prolog editor type
	// verifierInDTO.setPrologEditorType(prologEditorType);
	//
	// // Create class
	// DefectsVerifier verifier = new DefectsVerifier(
	// verifierInDTO);
	//
	// try {
	// // Invoke method
	// List<Defect> deadElements = verifier
	// .identifyDeadFeatures(verifierInDTO
	// .getVariabilityModel().getElements());
	//
	// // Test Results
	// Assert.assertTrue(!deadElements.isEmpty());
	// Assert.assertTrue(deadElements.size() == 2);
	// Assert.assertTrue(deadElements.get(0).getId().equals("F1")
	// || deadElements.get(0).getId().equals("F4"));
	// Assert.assertTrue(deadElements.get(1).getId().equals("F1")
	// || deadElements.get(1).getId().equals("F4"));
	//
	// // Print results
	// System.out
	// .println("_________________ VERIFICATION RESULTS_____________________");
	//
	// for (Defect deadElement : deadElements) {
	// System.out.println("DEAD FEATURE " + deadElement.getId());
	// }
	// } catch (FunctionalException e) {
	// fail();
	// }
	// }
	//
	// }
	//
	// public void testVerifyDeadFeatureGNU() {
	// VariabilityModel variabilityModel =
	// transformFeatureModel("test/testModels/Feature10ModelAllFormatsFeatureModelFeatureModel0.splx");
	//
	// // Define Prolog editor type ( GNU or SWI)
	// SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;
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
	// // DEAD ELEMENTS
	// // Dead features
	//
	// try {
	// List<Defect> deadElements = verifier
	// .identifyDeadFeatures(verifierInDTO
	// .getVariabilityModel().getElements());
	//
	// // Para el modelo analizado se conocen ya algunas cosas que se
	// // verifican en el test
	// Assert.assertTrue(!deadElements.isEmpty());
	// Assert.assertTrue(deadElements.size() == 1);
	// Assert.assertTrue(deadElements.get(0).getId().equals("F5"));
	//
	// // 3. PRINT RESULTS
	// System.out
	// .println("_________________ VERIFICATION RESULTS_____________________");
	//
	// for (Defect deadElement : deadElements) {
	// System.out.println("DEAD FEATURE " + deadElement.getId());
	// }
	// } catch (FunctionalException e) {
	// fail();
	// }
	//
	// }
	//
	// }
	//
	// public void testVerifyDeadPrologFileGNU() {
	//
	// String modelInputPath =
	// "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\Bycicle_27_fm.pl";
	// SolverEditorType solverEditorType = SolverEditorType.GNU_PROLOG;
	//
	// VariabilityModel variabilityModel = null;
	//
	// variabilityModel = transformBooleanPrologFile(modelInputPath,
	// solverEditorType);
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
	// // DEAD ELEMENTS
	// try {
	// List<Defect> deadElements = verifier
	// .identifyDeadFeatures(verifierInDTO
	// .getVariabilityModel().getElements());
	//
	// // Para el modelo analizado se conocen ya algunas cosas que
	// // se
	// // verifican en el test
	// Assert.assertTrue(!deadElements.isEmpty());
	// Assert.assertTrue(deadElements.size() == 2);
	// Assert.assertTrue(deadElements.get(0).getId()
	// .equals("Var_Black"));
	//
	// // 3. PRINT RESULTS
	// System.out
	// .println("_________________ VERIFICATION RESULTS_____________________");
	//
	// for (Defect deadElement : deadElements) {
	// System.out.println("DEAD FEATURE " + deadElement.getId());
	// }
	// } catch (FunctionalException e) {
	// fail();
	// }
	//
	// }
	//
	// }
	//
	// public void testVerifyVoidModelPrologFileGNU() {
	//
	// String modelInputPath =
	// "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\prologFiles\\GNU_PROLOGBetty5Features_FeatureModel3.pl";
	// SolverEditorType solverEditorType = SolverEditorType.GNU_PROLOG;
	//
	// VariabilityModel variabilityModel = null;
	//
	// variabilityModel = transformBooleanPrologFile(modelInputPath,
	// solverEditorType);
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
	// // DEAD ELEMENTS
	// try {
	// boolean isVoid = verifier.isVoid();
	//
	// // 3. PRINT RESULTS
	// System.out
	// .println("_________________ VERIFICATION RESULTS_____________________");
	//
	// System.out.println("Prolog void model: " + isVoid);
	// Assert.assertTrue(isVoid);
	// } catch (FunctionalException e) {
	// fail();
	// }
	//
	// }
	//
	// }
	//
	// public void testVerifyFalseProductLinePrologFileGNU() {
	//
	// // Transform VariabilityModel
	// String modelInputPath =
	// "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\prologFiles\\GNU_PROLOGBetty5Features_FeatureModel4FalsePLM.pl";
	// VariabilityModel variabilityModel = null;
	//
	// // Define Prolog editor type ( GNU or SWI)
	// SolverEditorType solverEditorType = SolverEditorType.GNU_PROLOG;
	// variabilityModel = transformBooleanPrologFile(modelInputPath,
	// solverEditorType);
	// if (variabilityModel != null) {
	//
	// // Make input DTO
	// VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
	// // Set transformed variability model
	// verifierInDTO.setVariabilityModel(variabilityModel);
	// // Set Prolog editor type
	// verifierInDTO.setPrologEditorType(solverEditorType);
	//
	// // Create class
	// DefectsVerifier verifier = new DefectsVerifier(
	// verifierInDTO);
	// try {
	// // Invoke method
	// boolean isFalsePLM = verifier.isFalsePLM();
	//
	// // Print results
	// System.out
	// .println("_________________ VERIFICATION RESULTS_____________________");
	//
	// System.out.println("False PLM model: " + isFalsePLM);
	// Assert.assertTrue(isFalsePLM);
	// } catch (FunctionalException e) {
	// fail();
	// }
	//
	// }
	//
	// }
	//
	// public void testVerifyNoFalseProductLinePrologFileGNU() {
	//
	// String modelInputPath =
	// "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\prologFiles\\Car_9_fm.pl";
	// SolverEditorType solverEditorType = SolverEditorType.GNU_PROLOG;
	//
	// VariabilityModel variabilityModel = null;
	//
	// variabilityModel = transformBooleanPrologFile(modelInputPath,
	// solverEditorType);
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
	// // DEAD ELEMENTS
	// try {
	// boolean isFalsePLM = verifier.isFalsePLM();
	//
	// // 3. PRINT RESULTS
	// System.out
	// .println("_________________ VERIFICATION RESULTS_____________________");
	//
	// System.out.println("Prolog is False PLM model: " + isFalsePLM);
	// Assert.assertTrue(!isFalsePLM);
	// } catch (FunctionalException e) {
	// fail();
	// }
	//
	// }
	//
	// }
	//
	// public void testVerifyFalseOptionalGNUPrologFile() {
	//
	// System.out.println(" FALSE OPTIONAL GNU PROLOG FILE");
	//
	// // Transform VariabilityModel
	// String modelInputPath =
	// "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\prologFiles\\CellPhone_15_fm.pl";
	// SolverEditorType solverEditorType = SolverEditorType.GNU_PROLOG;
	// VariabilityModel variabilityModel = transformBooleanPrologFile(
	// modelInputPath, solverEditorType);
	//
	// // 2. VERIFY DEFECTS IN TRANSFORMED MODEL
	// if (variabilityModel != null) {
	//
	// // Make input DTO
	// VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
	// // Set transformed variability model
	// verifierInDTO.setVariabilityModel(variabilityModel);
	// // Prolog editor type
	// verifierInDTO.setPrologEditorType(solverEditorType);
	//
	// // Create class
	// DefectsVerifier verifier = new DefectsVerifier(
	// verifierInDTO);
	//
	// try {
	// // Invoke method
	// List<Defect> falseOptionalElements = verifier
	// .identifyFalseOptionalFeatures(verifierInDTO
	// .getVariabilityModel().getElements());
	//
	// // Assert results
	// Assert.assertTrue(!falseOptionalElements.isEmpty());
	// Assert.assertTrue(falseOptionalElements.size() == 7);
	//
	// // Print results
	// System.out
	// .println("_________________ VERIFICATION RESULTS_____________________");
	//
	// for (Defect falseOptionalElement : falseOptionalElements) {
	// System.out.println("FALSE OPTIONAL FEATURES "
	// + falseOptionalElement.getId());
	// }
	// } catch (FunctionalException e) {
	// fail();
	// }
	//
	// }
	//
	// }
	//
	// public void testVerifyVoidModelGNUPrologFile() {
	//
	// System.out.println(" VOID MODEL PROLOG FILE");
	//
	// // Transform VariabilityModel
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
	// // 2. VERIFY DEFECTS IN TRANSFORMED MODEL
	// if (variabilityModel != null) {
	//
	// // Make input DTO
	// VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
	// // Set transformed variability model
	// verifierInDTO.setVariabilityModel(variabilityModel);
	// // Prolog editor type
	// verifierInDTO.setPrologEditorType(solverEditorType);
	//
	// // Create class
	// DefectsVerifier verifier = new DefectsVerifier(
	// verifierInDTO);
	//
	// try {
	// // Invoke method
	// boolean isVoid = verifier.isVoid();
	//
	// // Assert results
	// Assert.assertTrue(isVoid);
	//
	// // Print results
	// System.out
	// .println("_________________ VERIFICATION RESULTS_____________________");
	//
	// } catch (FunctionalException e) {
	// fail();
	// }
	//
	// }
	//
	// }
	//
	// public void testVerifyFalseOptionalFeatureGNU() {
	// VariabilityModel variabilityModel =
	// transformFeatureModel("test/testModels/Betty8Features_FeatureModel0.sxfm");
	//
	// // Define Prolog editor type ( GNU or SWI)
	// SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;
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
	// // DEAD ELEMENTS
	// // Dead features
	//
	// try {
	//
	// Map<String, VariabilityElement> optionalElements = variabilityModel
	// .getOptionalVariabilityElements();
	// if (optionalElements == null || optionalElements.isEmpty()) {
	// // All elements are verified
	// optionalElements = variabilityModel.getElements();
	// }
	// List<Defect> falseOptionalElements = verifier
	// .identifyFalseOptionalFeatures(optionalElements);
	//
	// // 3. PRINT RESULTS
	// System.out
	// .println("_________________ VERIFICATION RESULTS_____________________");
	//
	// for (Defect falseOptionalElement : falseOptionalElements) {
	// System.out.println("FALSE OPTIONAL "
	// + falseOptionalElement.getId());
	// }
	//
	// // Para el modelo analizado se conocen ya algunas cosas que se
	// // verifican en el test
	// Assert.assertTrue(!falseOptionalElements.isEmpty());
	// Assert.assertTrue(falseOptionalElements.size() == 1);
	// Assert.assertTrue(falseOptionalElements.get(0).getId()
	// .equals("F3"));
	// } catch (FunctionalException e) {
	// fail();
	// }
	//
	// }
	//
	// }
	//
	// public void testVerifyDomainNotAttainableGNUProlog() {
	// System.out.println(" NON ATTAINABLE DOMAIN GNU PROLOG FILE");
	//
	// // Transform VariabilityModel
	// String modelInputPath =
	// "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\prologFiles\\CellPhone_15_fm.pl";
	// SolverEditorType solverEditorType = SolverEditorType.GNU_PROLOG;
	// VariabilityModel variabilityModel = transformBooleanPrologFile(
	// modelInputPath, solverEditorType);
	//
	// if (variabilityModel != null) {
	//
	// // Make input DTO
	// VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
	//
	// // Set transformed variability model
	// verifierInDTO.setVariabilityModel(variabilityModel);
	// // Set Prolog editor type
	// verifierInDTO.setPrologEditorType(solverEditorType);
	//
	// // Create class
	// DefectsVerifier verifier = new DefectsVerifier(
	// verifierInDTO);
	//
	// try {
	// // Invoke method
	// List<Defect> domainNotAttainableDefect = verifier
	// .identifyNonAttainableDomains(verifierInDTO
	// .getVariabilityModel().getElements());
	//
	// // Test results
	// Assert.assertTrue(!domainNotAttainableDefect.isEmpty());
	// Assert.assertTrue(domainNotAttainableDefect.size() == 7);
	//
	// // Print results
	// System.out
	// .println("_________________ VERIFICATION RESULTS_____________________");
	//
	// for (Defect nonAttainableDomain : domainNotAttainableDefect) {
	//
	// Integer nonAttainableValue = ((NonAttainableDomain) nonAttainableDomain)
	// .getNotAttainableDomain();
	// System.out.println("NON ATTAINABLE DOMAIN "
	// + nonAttainableDomain.getId() + " VALUE : "
	// + nonAttainableValue);
	// }
	// } catch (FunctionalException e) {
	// fail();
	// e.printStackTrace();
	// }
	//
	// }
	//
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

	// private VariabilityModel transformProductLine(String modelInputPath) {
	// // Variable del modelo a analizar
	// SXFMReader reader = new SXFMReader();
	// ProductLine pl = null;
	//
	// try {
	// pl = reader.readFile(modelInputPath);
	// } catch (FeatureModelException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // Se instancia el transformador
	// VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
	// transformerInDTO.setNotationType(NotationType.PRODUCT_LINE);
	// transformerInDTO.setProductLineConfigurator(pl);
	// VariabilityModel variabilityModel = null;
	// VariabilityModelTransformer transformer = new
	// VariabilityModelTransformer(
	// transformerInDTO);
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
	//
	// public void testNotvoidModelGNUProductLine() {
	// VariabilityModel variabilityModel =
	// transformProductLine("test/testModels/Betty5Features_FeatureModel10.sxfm");
	//
	// // Define Prolog editor type ( GNU or SWI)
	// SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;
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
	// // VOID MODEL
	// boolean isVoid;
	// isVoid = verifier.isVoid();
	// System.out.println("IS VOID " + isVoid);
	//
	// Assert.assertFalse(isVoid);
	// } catch (FunctionalException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// fail();
	// }
	//
	// }
	//
	// }
	//
	// public void testVoidModelGNUProductLine() {
	//
	// // Transform VariabilityModel
	// VariabilityModel variabilityModel =
	// transformProductLine("test/testModels/Betty25Features_FeatureModel0.splx");
	//
	// // Define Prolog editor type ( GNU or SWI)
	// SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;
	//
	// if (variabilityModel != null) {
	//
	// // Make input DTO
	// VMAnalyzerInDTO verifierInDTO = new VMAnalyzerInDTO();
	//
	// // Set transfomed variability model
	// verifierInDTO.setVariabilityModel(variabilityModel);
	//
	// // Set Prolog editor type
	// verifierInDTO.setPrologEditorType(prologEditorType);
	//
	// // Create class
	// DefectsVerifier verifier = new DefectsVerifier(
	// verifierInDTO);
	// try {
	// boolean isVoid;
	//
	// // Invoke method
	// isVoid = verifier.isVoid();
	//
	// System.out.println("IS VOID " + isVoid);
	//
	// Assert.assertTrue(isVoid);
	// } catch (FunctionalException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// fail();
	// }
	//
	// }
	//
	// }
	//
	// public void testFalseProductLineModelGNUProductLine() {
	// VariabilityModel variabilityModel =
	// transformFeatureModel("test/testModels/Betty25Features_FeatureModel0.splx");
	//
	// // Define Prolog editor type ( GNU or SWI)
	// SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;
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
	// // VOID MODEL
	// boolean isVoid;
	// isVoid = verifier.isVoid();
	// System.out.println("IS VOID " + isVoid);
	//
	// Assert.assertTrue(isVoid);
	// } catch (FunctionalException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// fail();
	// }
	//
	// }
	//
	// }

}
