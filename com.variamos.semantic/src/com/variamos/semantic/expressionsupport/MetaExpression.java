package com.variamos.semantic.expressionsupport;

import java.util.ArrayList;
import java.util.List;

import com.variamos.semantic.semanticsupport.AbstractSemanticElement;
import com.variamos.semantic.types.ExpressionVertexType;

/**
 * A class to represent MetaExpressions. Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
public class MetaExpression {
	private MetaExpressionType metaExpressionType;
	private AbstractSemanticElement leftVertex;
	private AbstractSemanticElement rightVertex;
	private String leftAttributeName;
	private String rightAttributeName;
	private MetaExpression leftSubExpression;
	private MetaExpression rightSubExpression;
	private int number;
	private List<ExpressionVertexType> expressionVertexTypes = new ArrayList<ExpressionVertexType>();

	public MetaExpression() {

	}

	public MetaExpression(MetaExpressionType metaExpressionType,
			AbstractSemanticElement left, AbstractSemanticElement right,
			String leftAttributeName, String rightAttributeName) {
		this.metaExpressionType = metaExpressionType;
		this.leftVertex = left;
		this.rightVertex = right;
		this.leftAttributeName = leftAttributeName;
		this.rightAttributeName = rightAttributeName;
		this.expressionVertexTypes.add(ExpressionVertexType.LEFT);
		this.expressionVertexTypes.add(ExpressionVertexType.RIGHT);
	}

	public MetaExpression(MetaExpressionType metaExpressionType,
			AbstractSemanticElement vertex, String attributeName,
			boolean replaceTarget, MetaExpression subExpression) {
		this.metaExpressionType = metaExpressionType;
		if (replaceTarget) {
			this.leftVertex = vertex;
			this.leftAttributeName = attributeName;
			this.expressionVertexTypes.add(ExpressionVertexType.LEFT);
			this.expressionVertexTypes
					.add(ExpressionVertexType.RIGHTSUBEXPRESSION);
			this.rightSubExpression = subExpression;
		} else {
			this.rightVertex = vertex;
			this.rightAttributeName = attributeName;
			this.expressionVertexTypes
					.add(ExpressionVertexType.LEFTSUBEXPRESSION);
			this.expressionVertexTypes.add(ExpressionVertexType.RIGHT);
			this.leftSubExpression = subExpression;
		}
	}

	public MetaExpression(MetaExpressionType metaExpressionType,
			MetaExpression leftSubExpression, MetaExpression rightSubExpression) {
		this.metaExpressionType = metaExpressionType;
		this.leftSubExpression = leftSubExpression;
		this.rightSubExpression = rightSubExpression;
		this.expressionVertexTypes.add(ExpressionVertexType.LEFTSUBEXPRESSION);
		this.expressionVertexTypes.add(ExpressionVertexType.RIGHTSUBEXPRESSION);
	}

	public MetaExpression(MetaExpressionType metaExpressionType,
			AbstractSemanticElement vertex, String attributeName) {
		this.metaExpressionType = metaExpressionType;
		this.leftVertex = vertex;
		this.leftAttributeName = attributeName;
		this.expressionVertexTypes.add(ExpressionVertexType.LEFT);
	}

	public void setMetaExpressionType(MetaExpressionType metaExpressionType) {
		this.metaExpressionType = metaExpressionType;
	}

	public MetaExpressionType getMetaExpressionType() {
		return metaExpressionType;
	}

	public AbstractSemanticElement getLeftVertex() {
		return leftVertex;
	}

	public AbstractSemanticElement getRightVertex() {
		return rightVertex;
	}

	public String getLeftAttributeName() {
		return leftAttributeName;
	}

	public String getRightAttributeName() {
		return rightAttributeName;
	}

	public MetaExpression getLeftSubExpression() {
		return leftSubExpression;
	}

	public MetaExpression getRightSubExpression() {
		return rightSubExpression;
	}

	public int getNumber() {
		return number;
	}

	public List<ExpressionVertexType> getExpressionVertexTypes() {
		return expressionVertexTypes;
	}
	
	public int getLeftValidExpressions()
	{
		if (metaExpressionType == null)
			return 0;
		return metaExpressionType.getLeftValidExpressions();
	}

	public int getRightValidExpressions()
	{
		if (metaExpressionType == null)
			return 0;
		return metaExpressionType.getRightValidExpressions();
	}
	
	public int getResultExpressions()
	{
		if (metaExpressionType == null)
			return 0;
		return metaExpressionType.getResultExpressions();
	}
	
}
