package com.cfm.productline.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.cfm.productline.defectAnalyzer.VariabilityModelOperations;
import com.cfm.productline.dto.VMAnalyzerInDTO;
import com.cfm.productline.dto.VMTransformerInDTO;
import com.cfm.productline.exceptions.FunctionalException;
import com.cfm.productline.exceptions.TransformerException;
import com.cfm.productline.model.defectAnalyzerModel.VMConfiguration;
import com.cfm.productline.model.defectAnalyzerModel.VariabilityModel;
import com.cfm.productline.model.enums.NotationType;
import com.cfm.productline.model.enums.SolverEditorType;
import com.cfm.productline.transformer.VariabilityModelTransformer;

public class VariabilityModelOperationsTest {

	@Test
	public void testConfigurations() {
		//Transform VariabilityModel
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/Betty8Features_FeatureModel0.sxfm");

		// Define Prolog editor type ( GNU or SWI)
		SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;

		if (variabilityModel != null) {

			// Make input DTO
			VMAnalyzerInDTO analyzerInDTO = new VMAnalyzerInDTO();

			// Set transfomed variability model
			analyzerInDTO.setVariabilityModel(variabilityModel);
			
			// Set Prolog editor type
			analyzerInDTO.setPrologEditorType(prologEditorType);

			// Create  class
			VariabilityModelOperations operationsVM = new VariabilityModelOperations(
					analyzerInDTO);
			
			List<VMConfiguration> configurationsList= new ArrayList<VMConfiguration>();
					
			try {
				//Invoke method to obtain two configurations
				int numberOfConfigurations=2;
				configurationsList=operationsVM.getConfigurations(numberOfConfigurations);
				Assert.assertTrue(configurationsList.size()==numberOfConfigurations);
				int i=1;
				
				//Print configurations
				for(VMConfiguration configuration:configurationsList){
					System.out.println("Configuration: " + i);
					
					//Obtain values
					Map<String, Integer> product= configuration.getProductRepresentation();
					
					//Print configuration
					configuration.printConfiguration();
					
				}
			} catch (FunctionalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	@Test
	public void testAllConfigurations() {
		//Transform VariabilityModel
		//VariabilityModel variabilityModel = transformFeatureModel("test/testModels/Betty8Features_FeatureModel0.sxfm");
		//VariabilityModel variabilityModel = transformFeatureModel("test/testModels/MobileFeatureModelCCC.sfxm");
		VariabilityModel variabilityModel = transformFeatureModel("test/testModels/WebPortalSimplified_24_fmDefects.sxfm");

		// Define Prolog editor type ( GNU or SWI)
		SolverEditorType prologEditorType = SolverEditorType.SWI_PROLOG;

		// 2. VERIFY DEFECTS IN TRANSFORMED MODEL
		if (variabilityModel != null) {

			// Make input DTO
			VMAnalyzerInDTO analyzerInDTO = new VMAnalyzerInDTO();

			// Set transfomed variability model
			analyzerInDTO.setVariabilityModel(variabilityModel);
			// Set Prolog editor type
			analyzerInDTO.setPrologEditorType(prologEditorType);
			
			// Create  class
			VariabilityModelOperations operationsVM = new VariabilityModelOperations(
					analyzerInDTO);
			
			List<VMConfiguration> configurationsList= new ArrayList<VMConfiguration>();
			
			
			try {
				//Invoke method to obtain two configurations
				configurationsList=operationsVM.getAllConfigurations();
				
				//Assert.assertTrue(configurationsList.size()==16);
				int i=1;
				
				//Print configurations
				for(VMConfiguration configuration:configurationsList){
					System.out.println("Configuration: " + i);
					System.out.println(configuration);
					i++;
				}
				
			} catch (FunctionalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private VariabilityModel transformFeatureModel(String modelInputPath) {
		// Se instancia el transformador
		VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
		transformerInDTO.setNotationType(NotationType.FEATURES_MODELS);
		transformerInDTO.setPathToTransform(modelInputPath);

		VariabilityModel variabilityModel = null;
		VariabilityModelTransformer transformer = new VariabilityModelTransformer(
				transformerInDTO);
		try {
			variabilityModel = transformer
					.transformToVariabilityModel();
			Assert.assertTrue(variabilityModel != null);
			Assert.assertTrue(variabilityModel.getDependencies() != null);
			System.out.println("The FM was correctly transformed \n");
			return variabilityModel;
		} catch (TransformerException e) {
			e.printStackTrace();
			fail();
		}

		return null;
	}

}
