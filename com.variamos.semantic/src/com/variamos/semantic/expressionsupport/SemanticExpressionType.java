package com.variamos.semantic.expressionsupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.NumericExpression;

/**
 * A class to represent the types of expression. Part of PhD work at University
 * of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-02-05
 */
public class SemanticExpressionType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3166856881413230258L;
	private String gnuPrologConnector;
	private String swiPrologConnector;
	private String textConnector;
	private int leftExpression;
	private int rightExpression;
	private int resultExpression;
	private String method;
	private boolean arrayParameters;
	private boolean singleInExpression;

	public static final int NONE = 0, BOOLEXP = 1, NUMEXP = 2, EXP = 3,
			IDEN = 4, BOOLVAL = 5, INTVAL = 6, LIT = 7;

	public SemanticExpressionType() {

	}

	public SemanticExpressionType(String textConnector, String gnuPrologConnector,
			String swiPrologConnector, String method, int leftExpression,
			int rightExpression, int resultExpression,
			boolean singleInExpression, boolean arrayParameters) {
		super();
		this.gnuPrologConnector = gnuPrologConnector;
		this.swiPrologConnector = swiPrologConnector;
		this.textConnector = textConnector;
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
		this.resultExpression = resultExpression;
		this.method = method;
		this.singleInExpression = singleInExpression;
		this.arrayParameters = arrayParameters;
	}

	public Class<? extends Expression> getExpressionClass(int expressionType) {
		switch (expressionType) {
		case LIT:
		case BOOLEXP:
			return BooleanExpression.class;
		case NUMEXP:
			return NumericExpression.class;
		case EXP:
			return Expression.class;
		default:
			return null;
		}
	}
	public int getLeftExpression() {
		return leftExpression;
	}

	public void setLeftExpression(int leftExpression) {
		this.leftExpression = leftExpression;
	}

	public int getRightExpression() {
		return rightExpression;
	}

	public void setRightExpression(int rightExpression) {
		this.rightExpression = rightExpression;
	}

	public int getResultExpression() {
		return resultExpression;
	}

	public void setResultExpression(int resultExpression) {
		this.resultExpression = resultExpression;
	}

	public void setGnuPrologConnector(String gnuPrologConnector) {
		this.gnuPrologConnector = gnuPrologConnector;
	}

	public void setSwiPrologConnector(String swiPrologConnector) {
		this.swiPrologConnector = swiPrologConnector;
	}

	public void setTextConnector(String textConnector) {
		this.textConnector = textConnector;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setArrayParameters(boolean arrayParameters) {
		this.arrayParameters = arrayParameters;
	}

	public void setSingleInExpression(boolean singleInExpression) {
		this.singleInExpression = singleInExpression;
	}

	public Class<? extends Expression> getLeftExpressionClass() {
		return getExpressionClass(leftExpression);
	}

	public Class<? extends Expression> getRightExpressionClass() {
		return getExpressionClass(rightExpression);
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

	public int getLeftValidExpressions() {
		return leftExpression;
	}

	public int getRightValidExpressions() {
		return rightExpression;
	}

	public int getResultExpressions() {
		return resultExpression;
	}

	public String getMethod() {
		return method;
	}
	
	public boolean isArrayParameters() {
		return arrayParameters;
	}
	
	public boolean isSingleInExpression() {
		return singleInExpression;
	}

	public static List<SemanticExpressionType> getValidSemanticExpressionTypes(
			Collection<SemanticExpressionType> semanticExpressionList, int valid) {
		List<SemanticExpressionType> out = new ArrayList<SemanticExpressionType>();
		for (SemanticExpressionType semanticExpressionType : semanticExpressionList) {
			int evaluated = semanticExpressionType.getResultExpressions();
			if (evaluated == valid)
				out.add(semanticExpressionType);
			if (valid == SemanticExpressionType.EXP
					|| valid == SemanticExpressionType.NUMEXP)
				if (evaluated == SemanticExpressionType.BOOLEXP
						|| evaluated == SemanticExpressionType.NUMEXP)
					out.add(semanticExpressionType);
		}
		return out;
	}

}
