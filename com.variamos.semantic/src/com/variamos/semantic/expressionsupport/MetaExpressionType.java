package com.variamos.semantic.expressionsupport;

/**
 * A class to represent the types of expression. Part of PhD work at University
 * of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-02-05
 */
public class MetaExpressionType {
	private String gnuPrologConnector;
	private String swiPrologConnector;
	private String textConnector;
	private boolean booleanInExpressions;
	private boolean booleanResultExpression;
	private String method;
	private boolean singleInExpression;

	public MetaExpressionType(String textConnector, String gnuPrologConnector,
			String swiPrologConnector, boolean booleanInExpressions,
			boolean booleanResultExpression, String method,
			boolean singleInExpression) {
		super();
		this.gnuPrologConnector = gnuPrologConnector;
		this.swiPrologConnector = swiPrologConnector;
		this.textConnector = textConnector;
		this.booleanInExpressions = booleanInExpressions;
		this.booleanResultExpression = booleanResultExpression;
		this.method = method;
		this.singleInExpression = singleInExpression;
	}

	public String getGnuPrologConnector() {
		return gnuPrologConnector;
	}

	public String getSwiPrologConnector() {
		return swiPrologConnector;
	}

	public String getTextConnector() {
		return textConnector;
	}

	public boolean isBooleanInExpressions() {
		return booleanInExpressions;
	}

	public boolean isBooleanResultExpression() {
		return booleanResultExpression;
	}

	public String getMethod() {
		return method;
	}

	public boolean isSingleInExpression() {
		return singleInExpression;
	}
}
