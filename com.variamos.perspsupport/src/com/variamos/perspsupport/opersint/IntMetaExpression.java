package com.variamos.perspsupport.opersint;

public interface IntMetaExpression {
	public String getIdentifier();

	public void setIdentifier(String identifier);

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	boolean isValidExpression();
}
