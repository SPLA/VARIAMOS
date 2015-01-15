package com.variamos.defectAnalyzer.defectAnalyzer;

import java.util.List;
import java.util.Set;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.Identifier;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.defectAnalyzer.dto.VerificationResult;
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

	/**
	 * This method try to obtain a solution for each identifier . If no solution is found then the identifier is a dead element
	 * @param model
	 * @param identifier
	 * @return null if the identifier is not dead, a DeadElement defect otherwise
	 * @throws FunctionalException
	 */
	public Defect isDeadElement(HlclProgram model, Identifier identifier) throws FunctionalException;

	public Defect isFalseOptionalElement(HlclProgram model,
			Identifier identifier) throws FunctionalException;

	public Defect isRedundant(HlclProgram model,
			BooleanExpression expressionToVerify) throws FunctionalException;

	public List<Defect> getDeadElements(HlclProgram model,
			Set<Identifier> elementsToVerify) throws FunctionalException;

	public List<Defect> getFalseOptionalElements(HlclProgram model,
			Set<Identifier> elementsToVerify) throws FunctionalException;

	/**
	 * @param model
	 *            expressed as Hlclprogram
	 * @param constraitsToVerifyRedundacies
	 *            : expressions to verify if they are redundant.
	 * @return Defect
	 */
	public List<Defect> getRedundancies(HlclProgram model,
			List<BooleanExpression> constraitsToVerifyRedundacies) throws FunctionalException;
	
	public List<Defect> getAllNonAttainableDomains(HlclProgram model,
			Set<Identifier> elementsToVerify) throws FunctionalException;
	
	public List<Defect> getNonAttainableDomains(HlclProgram model,
			Identifier identifier) throws FunctionalException ;

	public VerificationResult getDefects(
			HlclProgram model,
			Set<Identifier> optionalElements,
			Set<Identifier> deadElementsToVerify, List<BooleanExpression> constraintsToVerifyRedundancies);

}
