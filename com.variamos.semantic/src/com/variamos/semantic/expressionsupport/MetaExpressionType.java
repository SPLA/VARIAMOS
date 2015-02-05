package com.variamos.semantic.expressionsupport;

import java.util.ArrayList;
import java.util.List;

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
	private int leftExpression;
	private int rightExpression;
	private int resultExpression;
	private String method;
	private boolean singleInExpression;

	public static final int NONE = 0, BOOL = 1, NUM = 2, IDEN = 3, ANY = 4,
			LIT = 6, INT = 6;

	public MetaExpressionType(String textConnector, String gnuPrologConnector,
			String swiPrologConnector, String method, int leftExpression,
			int rightExpression, int resultExpression,
			boolean singleInExpression) {
		super();
		this.gnuPrologConnector = gnuPrologConnector;
		this.swiPrologConnector = swiPrologConnector;
		this.textConnector = textConnector;
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
		this.resultExpression = resultExpression;
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

	public boolean isSingleInExpression() {
		return singleInExpression;
	}

	public static List<MetaExpressionType> getValidMetaExpressionTypes(
			List<MetaExpressionType> metaExpressionList, int valid) {
		List<MetaExpressionType> out = new ArrayList<MetaExpressionType>();
		for (MetaExpressionType metaExpressionType : metaExpressionList) {
			int evaluated = metaExpressionType.getResultExpressions();
			if (evaluated == valid)
				out.add(metaExpressionType);
			if (valid == MetaExpressionType.ANY)
				if (evaluated == MetaExpressionType.BOOL
						|| evaluated == MetaExpressionType.NUM)
					out.add(metaExpressionType);
		}
		return out;
	}

}
