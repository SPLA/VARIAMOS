package com.variamos.defectAnalyzer.defectAnalyzer;

import java.util.List;

import com.cfm.hlcl.HlclProgram;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.defectAnalyzer.dto.DefectAnalyzerResult;
import com.variamos.defectAnalyzer.model.CauCos;
import com.variamos.defectAnalyzer.model.Diagnosis;
import com.variamos.defectAnalyzer.model.defects.Defect;
import com.variamos.defectAnalyzer.model.enums.DefectAnalyzerMode;

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
			HlclProgram fixedConstraints, DefectAnalyzerMode mode)
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
			HlclProgram fixedConstraints, DefectAnalyzerMode mode)
			throws FunctionalException;

	public DefectAnalyzerResult getCauCos(List<Defect> defects,
			HlclProgram model, HlclProgram fixedConstraints,
			DefectAnalyzerMode mode) throws FunctionalException;

	public List<Diagnosis> getCorrections(List<Defect> defects, HlclProgram model,
			HlclProgram fixedConstraints, DefectAnalyzerMode mode)
			throws FunctionalException;

}
