package com.variamos.perspsupport.expressionsupport;

import com.variamos.perspsupport.opers.OpersAbstractElement;

/**
 * A class to support the operations definition with GUI edition. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-11-07
 */
public class OpersOperation extends OpersAbstractElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6532989148655801713L;

	public OpersOperation(int position, String identifier) {
		super(identifier);
		this.position = position;
		// expressionSubActions = new ArrayList<SemanticOperationSubAction>();
	}

	public OpersOperation() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	private int position;

	// private List<SemanticOperationSubAction> expressionSubActions;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	/*
	 * public List<SemanticOperationSubAction> getExpressionSubActions() {
	 * return expressionSubActions; }
	 * 
	 * public void setExpressionSubActions( List<SemanticOperationSubAction>
	 * expressionSubActions) { this.expressionSubActions = expressionSubActions;
	 * }
	 * 
	 * public void addExpressionSubAction( SemanticOperationSubAction
	 * operationSubAction) { expressionSubActions.add(operationSubAction); }
	 * 
	 * public List<String> getSubOperColumnsNames() {
	 * 
	 * List<String> out = new ArrayList<String>(); for
	 * (SemanticOperationSubAction subAction : expressionSubActions) {
	 * out.add(subAction.getIdentifier()); } return out; }
	 * 
	 * public List<SemanticOperationSubAction> getSubOperColumns() {
	 * 
	 * List<SemanticOperationSubAction> out = new
	 * ArrayList<SemanticOperationSubAction>(); for (SemanticOperationSubAction
	 * subAction : expressionSubActions) { out.add(subAction); } return out; }
	 * 
	 * public List<String> getSubOperTypesColumnsNames() {
	 * 
	 * List<String> out = new ArrayList<String>(); for
	 * (SemanticOperationSubAction subAction : expressionSubActions) {
	 * out.addAll(subAction.getOperationSubActionExpTypesNames()); } return out;
	 * }
	 * 
	 * public List<OperationSubActionExpType> getSubOperTypesColumns() {
	 * 
	 * List<OperationSubActionExpType> out = new
	 * ArrayList<OperationSubActionExpType>(); for (SemanticOperationSubAction
	 * subAction : expressionSubActions) {
	 * out.addAll(subAction.getOperationSubActionExpTypes()); } return out; }
	 * 
	 * public List<String> getOperLabelNames() { List<String> out = new
	 * ArrayList<String>(); for (SemanticOperationSubAction subAction :
	 * expressionSubActions) { out.addAll(subAction.getOperLabelNames()); }
	 * return out; }
	 * 
	 * public List<OperationLabeling> getOperLabels() { List<OperationLabeling>
	 * out = new ArrayList<OperationLabeling>(); for (SemanticOperationSubAction
	 * subAction : expressionSubActions) {
	 * out.addAll(subAction.getOperLabels()); } return out; }
	 */
}
