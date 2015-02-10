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
public class MetaExpressionType implements Serializable {
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

	public MetaExpressionType() {

	}

	public MetaExpressionType(String textConnector, String gnuPrologConnector,
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

	public static List<MetaExpressionType> getValidMetaExpressionTypes(
			Collection<MetaExpressionType> metaExpressionList, int valid) {
		List<MetaExpressionType> out = new ArrayList<MetaExpressionType>();
		for (MetaExpressionType metaExpressionType : metaExpressionList) {
			int evaluated = metaExpressionType.getResultExpressions();
			if (evaluated == valid)
				out.add(metaExpressionType);
			if (valid == MetaExpressionType.EXP
					|| valid == MetaExpressionType.NUMEXP)
				if (evaluated == MetaExpressionType.BOOLEXP
						|| evaluated == MetaExpressionType.NUMEXP)
					out.add(metaExpressionType);
		}
		return out;
	}

}
