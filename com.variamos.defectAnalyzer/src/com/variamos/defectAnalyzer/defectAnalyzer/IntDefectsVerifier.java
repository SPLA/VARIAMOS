package com.variamos.defectAnalyzer.defectAnalyzer;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.Identifier;
import com.variamos.defectAnalyzer.model.defects.Defect;

public interface IntDefectsVerifier {

	/**
	 * @return This operation takes a HLCLProgram as input and returns TRUE if
	 *         it does not define any products.
	 *
	 */
	public Defect isVoid(HlclProgram model);

	/**
	 * This operation takes a HLCLProgram as input and returns true if at most
	 * one valid product can be configured with it
	 * 
	 * @param path
	 * @param prologEditorType
	 * @return
	 * 
	 */
	public Defect isFalsePL(HlclProgram model);

	public Defect isDeadElement(HlclProgram model, Identifier identifier);

	public Defect isFalseOptionalElement(HlclProgram model,
			Identifier identifier);

	public Defect isRedundant(HlclProgram model,
			BooleanExpression expressionToVerify, BooleanExpression negation);

	public List<Defect> getDeadElements(HlclProgram model,
			Set<Identifier> elementsToVerify);

	public List<Defect> getFalseOptionalElements(HlclProgram model,
			Set<Identifier> elementsToVerify);

	/**
	 * @param model
	 *            expressed as Hlclprogram
	 * @param constraitsToVerify
	 *            : For each pair of elements: key of map - expression to
	 *            verify. Value of map: negation of the expression to verify
	 * @return Defect
	 */
	public List<Defect> getRedundancies(HlclProgram model,
			Map<BooleanExpression, BooleanExpression> constraitsToVerify);

	public List<Defect> getDefects(HlclProgram model,
			Set<Identifier> optionalElements,
			Set<Identifier> deadElementsToVerify,
			Map<BooleanExpression, BooleanExpression> constraitsToVerifyRedundancies);

}
