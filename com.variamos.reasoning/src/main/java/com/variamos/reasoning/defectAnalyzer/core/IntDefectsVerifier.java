package com.variamos.reasoning.defectAnalyzer.core;

import java.util.List;
import java.util.Set;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;
import com.variamos.reasoning.defectAnalyzer.model.dto.VerificationResultDTO;
import com.variamos.solver.model.ConfigurationOptionsDTO;
import com.variamos.solver.model.SolverSolution;

public interface IntDefectsVerifier {

	/**
	 * @return This operation returns TRUE if the hlclprogram does not define
	 *         any products and null otherwise
	 *
	 */
	public Defect isVoid();

	/**
	 * This operation takes a HLCLProgram as input and returns true if at most
	 * one valid product can be configured with it
	 * 
	 * @param path
	 * @param prologEditorType
	 * @return null if the product line is not false
	 * 
	 */
	public Defect isFalsePL();

	/**
	 * This method try to obtain a solution for each identifier . If no solution
	 * is found then the identifier is a dead element
	 * 
	 * @param model
	 * @param identifier
	 * @return null if the identifier is not dead, a DeadElement defect
	 *         otherwise
	 * @throws FunctionalException
	 */
	public Defect isDeadElement(Identifier identifier)
			throws FunctionalException;

	public Defect isDeadElement(Identifier identifier,
			ConfigurationOptionsDTO options, SolverSolution configuration)
			throws FunctionalException;

	public Defect isFalseOptionalElement(Identifier identifier)
			throws FunctionalException;

	public Defect isFalseOptionalElement(Identifier identifier,
			ConfigurationOptionsDTO options, SolverSolution configuration)
			throws FunctionalException;

	public Defect isRedundant(IntBooleanExpression expressionToVerify)
			throws FunctionalException;

	public List<Defect> getDeadElements(Set<Identifier> elementsToVerify)
			throws FunctionalException, InterruptedException;

	public List<Defect> getFalseOptionalElements(
			Set<Identifier> elementsToVerify) throws FunctionalException,
			InterruptedException;

	public List<Defect> getDeadElements(Set<Identifier> elementsToVerify,
			ConfigurationOptionsDTO options, SolverSolution configuration)
			throws FunctionalException, InterruptedException;

	public List<Defect> getFalseOptionalElements(
			Set<Identifier> elementsToVerify, ConfigurationOptionsDTO options,
			SolverSolution configuration) throws FunctionalException,
			InterruptedException;

	public long getSolverTime();

	public long getTotalTime();

	public void resetTime();

	/**
	 * @param model
	 *            expressed as Hlclprogram
	 * @param constraitsToVerifyRedundacies
	 *            : expressions to verify if they are redundant.
	 * @return Defect
	 */
	public List<Defect> getRedundancies(
			List<IntBooleanExpression> constraitsToVerifyRedundacies)
			throws FunctionalException;

	public List<Defect> getAllNonAttainableDomains(
			Set<Identifier> elementsToVerify) throws FunctionalException;

	public List<Defect> getNonAttainableDomains(Identifier identifier)
			throws FunctionalException;

	public VerificationResultDTO getDefects(Set<Identifier> optionalElements,
			Set<Identifier> deadElementsToVerify,
			List<IntBooleanExpression> constraintsToVerifyRedundancies)
			throws InterruptedException;

	// jcmunoz: new method to support additional constraints in the verification
	// of false product lines
	List<Defect> getFalsePLs(List<IntBooleanExpression> additionalConstraints);
	// jcmunoz: new method to support additional constraints in the verification
	// of void models
	List<Defect> getVoids(List<IntBooleanExpression> additionalConstraints);

}
