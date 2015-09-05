package com.variamos.perspsupport.expressionsupport;

import java.util.List;

import com.variamos.perspsupport.semanticsupport.AbstractSemanticElement;

/**
 * A class to store MetaExpression Sets. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-02-05
 */
public class SemExprSet {
	private String identifier;
	private String description;
	private boolean userSelectable;
	private int priority;
	private int responseAction;
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isUserSelectable() {
		return userSelectable;
	}
	public void setUserSelectable(boolean userSelectable) {
		this.userSelectable = userSelectable;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getResponseAction() {
		return responseAction;
	}
	public void setResponseAction(int responseAction) {
		this.responseAction = responseAction;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public List<AbstractSemanticElement> getSemanticElements() {
		return semanticElements;
	}
	public void setSemanticElements(List<AbstractSemanticElement> semanticElements) {
		this.semanticElements = semanticElements;
	}
	public List<SemExprSetSubAction> getExpressionSetSubAction() {
		return expressionSetSubAction;
	}
	public void setExpressionSetSubAction(
			List<SemExprSetSubAction> expressionSetSubAction) {
		this.expressionSetSubAction = expressionSetSubAction;
	}
	private String errorDescription;
	private List<AbstractSemanticElement> semanticElements;
	private List<SemExprSetSubAction> expressionSetSubAction;
}
