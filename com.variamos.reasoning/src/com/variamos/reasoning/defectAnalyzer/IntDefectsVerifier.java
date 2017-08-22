package com.variamos.reasoning.defectAnalyzer;

import java.util.List;
import java.util.Set;

import com.variamos.core.exceptions.FunctionalException;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Identifier;
import com.variamos.reasoning.defectAnalyzer.dto.VerificationResult;
import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;
import com.variamos.solver.Configuration;
import com.variamos.solver.ConfigurationOptions;

public interface IntDefectsVerifier {

	/**
	 * @return This operation returns TRUE if the hlclprogram does not define
	 *         any products.
	 *
	 */
	public Defect isVoid();

	/**
	 * This operation takes a HLCLProgram as input and returns true if at most
	 * one valid product can be configured with it
	 * 
	 * @param path
	 * @param prologEditorType
	 * @return
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
			ConfigurationOptions options, Configuration configuration)
			throws FunctionalException;

	public Defect isFalseOptionalElement(Identifier identifier)
			throws FunctionalException;

	public Defect isFalseOptionalElement(Identifier identifier,
			ConfigurationOptions options, Configuration configuration)
			throws FunctionalException;

	public Defect isRedundant(BooleanExpression expressionToVerify)
			throws FunctionalException;

	public List<Defect> getDeadElements(Set<Identifier> elementsToVerify)
			throws FunctionalException, InterruptedException;

	public List<Defect> getFalseOptionalElements(
			Set<Identifier> elementsToVerify) throws FunctionalException,
			InterruptedException;

	public List<Defect> getDeadElements(Set<Identifier> elementsToVerify,
			ConfigurationOptions options, Configuration configuration)
			throws FunctionalException, InterruptedException;

	public List<Defect> getFalseOptionalElements(
			Set<Identifier> elementsToVerify, ConfigurationOptions options,
			Configuration configuration) throws FunctionalException,
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
			List<BooleanExpression> constraitsToVerifyRedundacies)
			throws FunctionalException;

	public List<Defect> getAllNonAttainableDomains(
			Set<Identifier> elementsToVerify) throws FunctionalException;

	public List<Defect> getNonAttainableDomains(Identifier identifier)
			throws FunctionalException;

	public VerificationResult getDefects(Set<Identifier> optionalElements,
			Set<Identifier> deadElementsToVerify,
			List<BooleanExpression> constraintsToVerifyRedundancies)
			throws InterruptedException;

	// jcmunoz: new method to support additional constraints in the verification
	// of false product lines
	List<Defect> getFalsePLs(List<BooleanExpression> additionalConstraints);
	// jcmunoz: new method to support additional constraints in the verification
	// of void models
	List<Defect> getVoids(List<BooleanExpression> additionalConstraints);

}
