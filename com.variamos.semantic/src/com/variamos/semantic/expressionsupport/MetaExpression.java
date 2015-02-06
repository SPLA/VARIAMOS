package com.variamos.semantic.expressionsupport;

import java.io.Serializable;

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
public class MetaExpression implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1977213643411960771L;
	private String identifier;
	private MetaExpressionType metaExpressionType;
	private AbstractSemanticElement leftVertex;
	private AbstractSemanticElement rightVertex;
	private String leftAttributeName;
	private String rightAttributeName;
	private MetaExpression leftSubExpression;
	private MetaExpression rightSubExpression;
	private int number;
	private ExpressionVertexType leftExpressionType;
	private ExpressionVertexType rightExpressionType;

	public MetaExpression() {

	}

	public MetaExpression(String identifier) {
		this.identifier = identifier;
	}

	public MetaExpression(String identifier,
			MetaExpressionType metaExpressionType) {
		this.identifier = identifier;
		this.metaExpressionType = metaExpressionType;
	}

	public MetaExpression(String identifier,
			MetaExpressionType metaExpressionType,
			AbstractSemanticElement left, AbstractSemanticElement right,
			String leftAttributeName, String rightAttributeName) {
		this.identifier = identifier;
		this.metaExpressionType = metaExpressionType;
		this.leftVertex = left;
		this.rightVertex = right;
		this.leftAttributeName = leftAttributeName;
		this.rightAttributeName = rightAttributeName;
		this.leftExpressionType = ExpressionVertexType.LEFT;
		this.rightExpressionType = ExpressionVertexType.RIGHT;
	}

	public MetaExpression(String identifier,
			MetaExpressionType metaExpressionType,
			AbstractSemanticElement vertex, String attributeName,
			boolean replaceTarget, MetaExpression subExpression) {
		this.identifier = identifier;
		this.metaExpressionType = metaExpressionType;
		if (replaceTarget) {
			this.leftVertex = vertex;
			this.leftAttributeName = attributeName;
			this.leftExpressionType = ExpressionVertexType.LEFT;
			this.rightExpressionType = ExpressionVertexType.RIGHTSUBEXPRESSION;
			this.rightSubExpression = subExpression;
		} else {
			this.rightVertex = vertex;
			this.rightAttributeName = attributeName;
			this.leftExpressionType = ExpressionVertexType.LEFTSUBEXPRESSION;
			this.rightExpressionType = ExpressionVertexType.RIGHT;
			this.leftSubExpression = subExpression;
		}
	}

	public MetaExpression(String identifier,
			MetaExpressionType metaExpressionType,
			MetaExpression leftSubExpression, MetaExpression rightSubExpression) {
		this.identifier = identifier;
		this.metaExpressionType = metaExpressionType;
		this.leftSubExpression = leftSubExpression;
		this.rightSubExpression = rightSubExpression;
		this.leftExpressionType = ExpressionVertexType.LEFTSUBEXPRESSION;
		this.rightExpressionType = ExpressionVertexType.RIGHTSUBEXPRESSION;
	}

	public MetaExpression(String identifier,
			MetaExpressionType metaExpressionType,
			AbstractSemanticElement vertex, String attributeName) {
		this.identifier = identifier;
		this.metaExpressionType = metaExpressionType;
		this.leftVertex = vertex;
		this.leftAttributeName = attributeName;
		this.leftExpressionType = ExpressionVertexType.LEFT;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public ExpressionVertexType getLeftExpressionType() {
		return leftExpressionType;
	}

	public void setLeftExpressionType(ExpressionVertexType leftExpressionType) {
		this.leftExpressionType = leftExpressionType;
	}

	public ExpressionVertexType getRightExpressionType() {
		return rightExpressionType;
	}

	public void setRightExpressionType(ExpressionVertexType rightExpressionType) {
		this.rightExpressionType = rightExpressionType;
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

	public int getLeftValidExpressions() {
		if (metaExpressionType == null)
			return 0;
		return metaExpressionType.getLeftValidExpressions();
	}

	public int getRightValidExpressions() {
		if (metaExpressionType == null)
			return 0;
		return metaExpressionType.getRightValidExpressions();
	}

	public int getResultExpressions() {
		if (metaExpressionType == null)
			return 0;
		return metaExpressionType.getResultExpressions();
	}

	public void setLeftAttributeName(String attribute) {
		this.leftAttributeName = attribute;
	}

	public void setRightAttributeName(String attribute) {
		this.rightAttributeName = attribute;
	}

	public String getOperation() {
		if (metaExpressionType != null)
			return metaExpressionType.getTextConnector();
		return null;
	}

}
