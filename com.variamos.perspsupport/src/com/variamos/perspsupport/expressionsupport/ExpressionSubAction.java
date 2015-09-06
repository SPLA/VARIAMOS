package com.variamos.perspsupport.expressionsupport;

/**
 * TODO
 * A class to support sub actions to generalize the semantic operationalization 
 * with GUI edition. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
/**
 * @author jcmunoz
 *
 */
public class ExpressionSubAction {
	private int position;
	private String 	description;
	private ExpressionAction expresionAction;
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ExpressionAction getExpresionAction() {
		return expresionAction;
	}
	public void setExpresionAction(ExpressionAction expresionAction) {
		this.expresionAction = expresionAction;
	}
}
