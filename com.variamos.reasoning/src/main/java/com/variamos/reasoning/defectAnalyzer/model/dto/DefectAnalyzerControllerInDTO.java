package com.variamos.reasoning.defectAnalyzer.model.dto;

import com.variamos.reasoning.defectAnalyzer.model.diagnosis.DefectAnalyzerModeEnum;

public class DefectAnalyzerControllerInDTO extends VMAnalyzerInDTO {

	private boolean verifyDeadFeatures;
	private boolean verifyFalseOptionalElement;
	private boolean verifyFalseProductLine;
	private boolean verifyNonAttainableDomains;
	private boolean verifyRedundancies;
	private boolean analyzeDeadFeatures;
	private boolean analyzeFalseOptional;
	private boolean analyzeFalseProductLine;
	private boolean analyzeDomains;
	private boolean analyzeVoidModel;
	private boolean analyzeRedundancies;


	private DefectAnalyzerModeEnum defectAnalyzerMode;


	/**
	 * @return the verifyDeadFeatures
	 */
	public boolean isVerifyDeadFeatures() {
		return verifyDeadFeatures;
	}

	/**
	 * @param verifyDeadFeatures
	 *            the verifyDeadFeatures to set
	 */
	public void setVerifyDeadFeatures(boolean verifyDeadFeatures) {
		this.verifyDeadFeatures = verifyDeadFeatures;
	}

	/**
	 * @return the verifyFalseOptional
	 */
	public boolean isVerifyFalseOptionalElement() {
		return verifyFalseOptionalElement;
	}

	/**
	 * @param verifyFalseOptionalElement
	 *            the verifyFalseOptional to set
	 */
	public void setVerifyFalseOptionalElement(boolean verifyFalseOptionalElement) {
		this.verifyFalseOptionalElement = verifyFalseOptionalElement;
	}


	/**
	 * @return the verifydomainNotAttainables
	 * Funcionalidad no implementada para features models
	 */
	@Deprecated()
	public boolean isVerifyNonAttainableDomain() {
		return verifyNonAttainableDomains;
	}

	/**
	 * @param verifyNonAttainableDomain
	 *            the verifydomainNotAttainables to set
	 */
	@Deprecated()
	public void setVerifyNonAttainableDomains(boolean verifyNonAttainableDomain) {
		this.verifyNonAttainableDomains = verifyNonAttainableDomain;
	}

	/**
	 * @return the identifyCausesDeadFeatures
	 */
	public boolean isAnalyzeDeadFeatures() {
		return analyzeDeadFeatures;
	}

	/**
	 * @param identifyCausesDeadFeatures
	 *            the identifyCausesDeadFeatures to set
	 */
	public void setAnalyzeDeadFeatures(boolean identifyCausesDeadFeatures) {
		this.analyzeDeadFeatures = identifyCausesDeadFeatures;
	}

	/**
	 * @return the identifyCausesFalseOptional
	 */
	public boolean isAnalyzeFalseOptional() {
		return analyzeFalseOptional;
	}

	/**
	 * @param identifyCausesFalseOptional
	 *            the identifyCausesFalseOptional to set
	 */
	public void setAnalyzeFalseOptional(
			boolean identifyCausesFalseOptional) {
		this.analyzeFalseOptional = identifyCausesFalseOptional;
	}

	
	/**
	 * @return the identifyCausesDomainNotAttainables
	 */
	public boolean isAnalyzeDomains() {
		return analyzeDomains;
	}

	/**
	 * @param identifyCausesDomainNotAttainables
	 *            the identifyCausesDomainNotAttainables to set
	 */
	public void setAnalyzeDomains(
			boolean identifyCausesDomainNotAttainables) {
		this.analyzeDomains = identifyCausesDomainNotAttainables;
	}

	/**
	 * @return the identifyCausesVoidModel
	 */
	public boolean isAnalyzeVoidModel() {
		return analyzeVoidModel;
	}

	/**
	 * @param identifyCausesVoidModel
	 *            the identifyCausesVoidModel to set
	 */
	public void setAnalyzeVoidModel(boolean identifyCausesVoidModel) {
		this.analyzeVoidModel = identifyCausesVoidModel;
	}



	/**
	 * @return the correctionSetIdentificationType
	 */
	public DefectAnalyzerModeEnum getDefectAnalyzerMode() {
		return defectAnalyzerMode;
	}

	/**
	 * @param defectAnalyzerMode
	 *            the correctionSetIdentificationType to set
	 */
	public void setDefectAnalyzerMode(
			DefectAnalyzerModeEnum defectAnalyzerMode) {
		this.defectAnalyzerMode = defectAnalyzerMode;
	}




	/**
	 * @return the verifyRedundancies
	 */
	public boolean isVerifyRedundancies() {
		return verifyRedundancies;
	}

	/**
	 * @param verifyRedundancies the verifyRedundancies to set
	 */
	public void setVerifyRedundancies(boolean verifyRedundancies) {
		this.verifyRedundancies = verifyRedundancies;
	}

	/**
	 * @return the identifyCausesRedundancies
	 */
	public boolean isAnalyzeRedundancies() {
		return analyzeRedundancies;
	}

	/**
	 * @param identifyCausesRedundancies the identifyCausesRedundancies to set
	 */
	public void setAnalyzeRedundancies(boolean identifyCausesRedundancies) {
		this.analyzeRedundancies = identifyCausesRedundancies;
	}

	/**
	 * @return the verifyNonAttainableDomains
	 */
	public boolean isVerifyNonAttainableDomains() {
		return verifyNonAttainableDomains;
	}

	/**
	 * @return the identifyCausesFalseProductLine
	 */
	public boolean isIdentifyCausesFalseProductLine() {
		return analyzeFalseProductLine;
	}

	/**
	 * @param identifyCausesFalseProductLine the identifyCausesFalseProductLine to set
	 */
	public void setIdentifyCausesFalseProductLine(
			boolean identifyCausesFalseProductLine) {
		this.analyzeFalseProductLine = identifyCausesFalseProductLine;
	}

	/**
	 * @return the verifyFalseProductLine
	 */
	public boolean isVerifyFalseProductLine() {
		return verifyFalseProductLine;
	}

	/**
	 * @param verifyFalseProductLine the verifyFalseProductLine to set
	 */
	public void setVerifyFalseProductLine(boolean verifyFalseProductLine) {
		this.verifyFalseProductLine = verifyFalseProductLine;
	}


}
