package com.cfm.productline.defectAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.cfm.productline.dto.VMAnalyzerInDTO;
import com.cfm.productline.exceptions.FunctionalException;
import com.cfm.productline.model.defectAnalyzerModel.VMConfiguration;
import com.cfm.productline.model.defectAnalyzerModel.VariabilityModel;
import com.cfm.productline.util.ConstraintRepresentationUtil;
import com.cfm.productline.util.SolverOperationsUtil;

public class VariabilityModelOperations extends VariabilityModelAnalyzer {

	protected VariabilityModel variabilityModel;

	public VariabilityModelOperations(VMAnalyzerInDTO inDTO) {
		super(inDTO);
		variabilityModel = inDTO.getVariabilityModel();
	}

	public List<VMConfiguration> getConfigurations(
			int numberOfConfigurationDefectAnalyzers)
			throws FunctionalException {
		List<VMConfiguration> obtainedConfigurationDefectAnalyzers = new ArrayList<VMConfiguration>();

		if (numberOfConfigurationDefectAnalyzers > 0) {

			List<Expression> variabilityModelConstraintRepresentation = new ArrayList<Expression>();
			variabilityModelConstraintRepresentation
					.addAll(ConstraintRepresentationUtil
							.dependencyToExpressionList(
									variabilityModel.getDependencies(),
									variabilityModel.getFixedDependencies()));

			// Se convierte el conjunto de restricciones a un archivo en
			// prolog que se guarda en la ruta temporal
			ConstraintRepresentationUtil.savePrologRepresentationProgram(
					prologTempPath, variabilityModelConstraintRepresentation,
					prologEditorType);

			List<List<Integer>> configuredValues = new ArrayList<List<Integer>>();

			HlclProgram expressionProgram = ConstraintRepresentationUtil
					.expressionToHlclProgram(variabilityModelConstraintRepresentation);
			Set<Identifier> constraintProgramIdentifiersCollection = HlclUtil
					.getUsedIdentifiers(expressionProgram);

			// Se obtienen las configuraciones que se solicitan
			configuredValues = SolverOperationsUtil
					.getSelectedVariablesByConfigurations(prologTempPath,
							numberOfConfigurationDefectAnalyzers,
							prologEditorType);

			// Si se obtienen valores esto quiere decir q es
			// satisfacible
			if (configuredValues != null && !configuredValues.isEmpty()) {

				for (List<Integer> configurationDefectAnalyzerValues : configuredValues) {
					VMConfiguration configurationDefectAnalyzer = createConfiguration(
							configurationDefectAnalyzerValues,
							constraintProgramIdentifiersCollection);
					obtainedConfigurationDefectAnalyzers
							.add(configurationDefectAnalyzer);
				}

			}
		} else {
			throw new FunctionalException(
					"ConfigurationDefectAnalyzer must be upper than 0");
		}
		return obtainedConfigurationDefectAnalyzers;
	}

	public int countNumberConfigurationDefectAnalyzers() {
		int numberOfConfigurationDefectAnalyzers = 0;

		return numberOfConfigurationDefectAnalyzers;
	}

	public List<VMConfiguration> getAllConfigurations()
			throws FunctionalException {
		List<VMConfiguration> obtainedConfigurationDefectAnalyzers = new ArrayList<VMConfiguration>();

		List<Expression> variabilityModelConstraintRepresentation = new ArrayList<Expression>();
		variabilityModelConstraintRepresentation
				.addAll(ConstraintRepresentationUtil
						.dependencyToExpressionList(
								variabilityModel.getDependencies(),
								variabilityModel.getFixedDependencies()));

		// Se convierte el conjunto de restricciones a un archivo en
		// prolog que se guarda en la ruta temporal
		ConstraintRepresentationUtil.savePrologRepresentationProgram(
				prologTempPath, variabilityModelConstraintRepresentation,
				prologEditorType);

		List<List<Integer>> configuredValues = new ArrayList<List<Integer>>();
		HlclProgram expressionProgram = ConstraintRepresentationUtil
				.expressionToHlclProgram(variabilityModelConstraintRepresentation);
		Set<Identifier> constraintProgramIdentifiersCollection = HlclUtil
				.getUsedIdentifiers(expressionProgram);

		// Se obtienen las configuraciones que se solicitan
		configuredValues = SolverOperationsUtil.getAllConfigurations(
				prologTempPath, prologEditorType);

		// Si se obtienen valores esto quiere decir q es
		// satisfacible
		if (configuredValues != null && !configuredValues.isEmpty()) {

			for (List<Integer> configurationDefectAnalyzerValues : configuredValues) {
				VMConfiguration configurationDefectAnalyzer = createConfiguration(
						configurationDefectAnalyzerValues,
						constraintProgramIdentifiersCollection);
				obtainedConfigurationDefectAnalyzers
						.add(configurationDefectAnalyzer);

			}

		}

		return obtainedConfigurationDefectAnalyzers;
	}

	private VMConfiguration createConfiguration(
			List<Integer> configurationDefectAnalyzerValues,
			Set<Identifier> constraintProgramIdentifiersCollection)
			throws FunctionalException {
		VMConfiguration configurationDefectAnalyzer = new VMConfiguration();
		int i = 0;
		if (constraintProgramIdentifiersCollection.size() == configurationDefectAnalyzerValues
				.size()) {
			for (Identifier identifier : constraintProgramIdentifiersCollection) {
				int value = configurationDefectAnalyzerValues.get(i);
				if (value == 0) {
					configurationDefectAnalyzer.ban(identifier.getId());
				}
				if (value == 1) {
					configurationDefectAnalyzer.enforce(identifier.getId());
				}
				i++;
			}

			return configurationDefectAnalyzer;
		} else {
			throw new FunctionalException(
					"ConfigurationDefectAnalyzer values and number of variables must be equals");
		}
	}

}