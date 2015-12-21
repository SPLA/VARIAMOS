package com.variamos.perspsupport.expressionsupport;

import java.util.ArrayList;
import java.util.List;

import com.variamos.perspsupport.semanticsupport.AbstractSemanticElement;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;

/**
 * A class to support the operations definition with GUI edition. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-11-07
 */
public class SemanticOperationAction extends AbstractSemanticElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6532989148655801713L;

	public SemanticOperationAction(int position, String identifier) {
		super(identifier);
		this.position = position;
		expressionSubActions = new ArrayList<SemanticOperationSubAction>();
		inVariables = new ArrayList<AbstractAttribute>();
		outVariables = new ArrayList<AbstractAttribute>();
	}

	private int position;
	private List<SemanticOperationSubAction> expressionSubActions;
	private List<AbstractAttribute> inVariables;
	private List<AbstractAttribute> outVariables;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public List<SemanticOperationSubAction> getExpressionSubActions() {
		return expressionSubActions;
	}

	public SemanticOperationSubAction getExpressionSubAction(String column) {
		for (SemanticOperationSubAction oper : expressionSubActions)
			if (oper.getIdentifier().equals(column))
				return oper;
		return null;
	}

	public void setExpressionSubActions(
			List<SemanticOperationSubAction> expressionSubActions) {
		this.expressionSubActions = expressionSubActions;
	}

	public void addExpressionSubAction(
			SemanticOperationSubAction operationSubAction) {
		expressionSubActions.add(operationSubAction);
	}

	public List<String> getOperColumnsNames() {

		List<String> out = new ArrayList<String>();
		for (SemanticOperationSubAction subAction : expressionSubActions) {
			out.addAll(subAction.getOperationSubActionExpTypesNames());
		}
		return out;
	}

	public List<OperationSubActionExpType> getOperColumns() {

		List<OperationSubActionExpType> out = new ArrayList<OperationSubActionExpType>();
		for (SemanticOperationSubAction subAction : expressionSubActions) {
			out.addAll(subAction.getOperationSubActionExpTypes());
		}
		return out;
	}

	public List<String> getOperLabelNames() {
		List<String> out = new ArrayList<String>();
		for (SemanticOperationSubAction subAction : expressionSubActions) {
			out.addAll(subAction.getOperLabelNames());
		}
		return out;
	}

	public List<OperationLabeling> getOperLabels() {
		List<OperationLabeling> out = new ArrayList<OperationLabeling>();
		for (SemanticOperationSubAction subAction : expressionSubActions) {
			out.addAll(subAction.getOperLabels());
		}
		return out;
	}

	public boolean hasInVariable(String variable) {
		for (AbstractAttribute var : inVariables)
			if (var.getName().equals(variable))
				return true;
		return false;
	}

	public boolean hasOutVariable(String variable) {
		for (AbstractAttribute var : outVariables)
			if (var.getName().equals(variable))
				return true;
		return false;
	}

	public void addInVariable(AbstractAttribute attribute) {
		inVariables.add(attribute);
	}

	public void addOutVariable(AbstractAttribute attribute) {
		outVariables.add(attribute);
	}
}
