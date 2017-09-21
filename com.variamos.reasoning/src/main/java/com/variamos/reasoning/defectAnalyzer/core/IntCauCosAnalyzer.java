package com.variamos.reasoning.defectAnalyzer.core;

import java.util.List;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.CauCos;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.DefectAnalyzerModeEnum;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.Diagnosis;
import com.variamos.reasoning.defectAnalyzer.model.dto.DefectAnalyzerResultDTO;

public interface IntCauCosAnalyzer {

	/**
	 * @param defect
	 * @param model
	 * @param fixedConstraints
	 *            : are constraints related to the notation in which the product
	 *            line model is represented.A fixed constraint of a FM is for
	 *            instance, that the root feature must be selected in any
	 *            product derived from the FM
	 * @param mode
	 * @return
	 */
	public Diagnosis getCauCos(Defect defect, HlclProgram model,
			HlclProgram fixedConstraints, DefectAnalyzerModeEnum mode)
			throws FunctionalException;

	/**
	 * @param defect
	 * @param model
	 * @param fixedConstraints
	 * @param mode
	 * @return
	 * @throws FunctionalException
	 */
	public List<CauCos> getCorrections(Defect defect, HlclProgram model,
			HlclProgram fixedConstraints, DefectAnalyzerModeEnum mode)
			throws FunctionalException;

	public DefectAnalyzerResultDTO getCauCos(List<Defect> defects,
			HlclProgram model, HlclProgram fixedConstraints,
			DefectAnalyzerModeEnum mode) throws FunctionalException;

	public List<Diagnosis> getCorrections(List<Defect> defects,
			HlclProgram model, HlclProgram fixedConstraints,
			DefectAnalyzerModeEnum mode) throws FunctionalException;

}
