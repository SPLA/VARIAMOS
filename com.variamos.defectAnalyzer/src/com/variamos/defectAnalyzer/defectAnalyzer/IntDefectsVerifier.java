package com.variamos.defectAnalyzer.defectAnalyzer;

import java.util.List;
import java.util.Set;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Identifier;
import com.cfm.productline.solver.Configuration;
import com.cfm.productline.solver.ConfigurationOptions;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.defectAnalyzer.dto.VerificationResult;
import com.variamos.defectAnalyzer.model.defects.Defect;

public interface IntDefectsVerifier {

	/**
	 * @return This operation returns TRUE if the hlclprogram does not define any products.
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
	 * This method try to obtain a solution for each identifier . If no solution is found then the identifier is a dead element
	 * @param model
	 * @param identifier
	 * @return null if the identifier is not dead, a DeadElement defect otherwise
	 * @throws FunctionalException
	 */
	public Defect isDeadElement(Identifier identifier) throws FunctionalException;
	public Defect isDeadElement(Identifier identifier, ConfigurationOptions options, Configuration configuration) throws FunctionalException;


	public Defect isFalseOptionalElement(Identifier identifier) throws FunctionalException;
	public Defect isFalseOptionalElement(Identifier identifier,ConfigurationOptions options, Configuration configuration) throws FunctionalException;


	public Defect isRedundant(BooleanExpression expressionToVerify) throws FunctionalException;

	public List<Defect> getDeadElements(Set<Identifier> elementsToVerify) throws FunctionalException;

	public List<Defect> getFalseOptionalElements(Set<Identifier> elementsToVerify) throws FunctionalException;
	
	
	public List<Defect> getDeadElements(Set<Identifier> elementsToVerify, ConfigurationOptions options, Configuration configuration) throws FunctionalException;

	public List<Defect> getFalseOptionalElements(Set<Identifier> elementsToVerify, ConfigurationOptions options, Configuration configuration) throws FunctionalException;
	

	/**
	 * @param model
	 *            expressed as Hlclprogram
	 * @param constraitsToVerifyRedundacies
	 *            : expressions to verify if they are redundant.
	 * @return Defect
	 */
	public List<Defect> getRedundancies(List<BooleanExpression> constraitsToVerifyRedundacies) throws FunctionalException;
	
	public List<Defect> getAllNonAttainableDomains(	Set<Identifier> elementsToVerify) throws FunctionalException;
	
	public List<Defect> getNonAttainableDomains(Identifier identifier) throws FunctionalException ;

	public VerificationResult getDefects(
			Set<Identifier> optionalElements,
			Set<Identifier> deadElementsToVerify, List<BooleanExpression> constraintsToVerifyRedundancies);

}
