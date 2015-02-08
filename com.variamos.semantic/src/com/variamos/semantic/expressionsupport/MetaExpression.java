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
	private ExpressionSubAction expressionSubAction;
	private int number;
	private boolean recursiveExpression;
	private AbstractSemanticElement leftSemanticElement;
	private AbstractSemanticElement rightSemanticElement;
	private String leftAttributeName;
	private String rightAttributeName;
	private MetaExpression leftMetaExpression;
	private MetaExpression rightMetaExpression;
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
			AbstractSemanticElement leftSemanticElement, AbstractSemanticElement rightSemanticElement,
			String leftAttributeName, String rightAttributeName) {
		this.identifier = identifier;
		this.metaExpressionType = metaExpressionType;
		this.leftSemanticElement = leftSemanticElement;
		this.rightSemanticElement = rightSemanticElement;
		this.leftAttributeName = leftAttributeName;
		this.rightAttributeName = rightAttributeName;
		this.leftExpressionType = ExpressionVertexType.LEFT;
		this.rightExpressionType = ExpressionVertexType.RIGHT;
	}

	public MetaExpression(String identifier,
			MetaExpressionType metaExpressionType,
			AbstractSemanticElement leftSemanticElement, String leftAttributeName,
			boolean replaceTarget, MetaExpression leftMetaExpression) {
		this.identifier = identifier;
		this.metaExpressionType = metaExpressionType;
		if (replaceTarget) {
			this.leftSemanticElement = leftSemanticElement;
			this.leftAttributeName = leftAttributeName;
			this.leftExpressionType = ExpressionVertexType.LEFT;
			this.rightExpressionType = ExpressionVertexType.RIGHTSUBEXPRESSION;
			this.rightMetaExpression = leftMetaExpression;
		} else {
			this.rightSemanticElement = leftSemanticElement;
			this.rightAttributeName = leftAttributeName;
			this.leftExpressionType = ExpressionVertexType.LEFTSUBEXPRESSION;
			this.rightExpressionType = ExpressionVertexType.RIGHT;
			this.leftMetaExpression = leftMetaExpression;
		}
	}

	public MetaExpression(String identifier,
			MetaExpressionType metaExpressionType,
			MetaExpression leftMetaExpression, MetaExpression rightMetaExpression) {
		this.identifier = identifier;
		this.metaExpressionType = metaExpressionType;
		this.leftMetaExpression = leftMetaExpression;
		this.rightMetaExpression = rightMetaExpression;
		this.leftExpressionType = ExpressionVertexType.LEFTSUBEXPRESSION;
		this.rightExpressionType = ExpressionVertexType.RIGHTSUBEXPRESSION;
	}

	public MetaExpression(String identifier,
			MetaExpressionType metaExpressionType,
			AbstractSemanticElement leftSemanticElement, String attributeName) {
		this.identifier = identifier;
		this.metaExpressionType = metaExpressionType;
		this.leftSemanticElement = leftSemanticElement;
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

	public boolean isRecursiveExpression() {
		return recursiveExpression;
	}

	public void setRecursiveExpression(boolean recursiveExpression) {
		this.recursiveExpression = recursiveExpression;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setLeftSemanticElement(AbstractSemanticElement leftSemanticElement) {
		this.leftSemanticElement = leftSemanticElement;
	}

	public void setRightSemanticElement(AbstractSemanticElement rightSemanticElement) {
		this.rightSemanticElement = rightSemanticElement;
	}

	public void setLeftMetaExpression(MetaExpression leftMetaExpression) {
		this.leftMetaExpression = leftMetaExpression;
	}

	public void setRightMetaExpression(MetaExpression rightMetaExpression) {
		this.rightMetaExpression = rightMetaExpression;
	}

	public ExpressionVertexType getRightExpressionType() {
		return rightExpressionType;
	}

	public void setRightExpressionType(ExpressionVertexType rightExpressionType) {
		this.rightExpressionType = rightExpressionType;
	}
	
	public ExpressionVertexType[] getExpressionTypes()
	{
		ExpressionVertexType[] out = new ExpressionVertexType[2];
		out[0] = getLeftExpressionType();
		out[1] = getRightExpressionType();
		return out;
	}

	public void setMetaExpressionType(MetaExpressionType metaExpressionType) {
		this.metaExpressionType = metaExpressionType;
	}

	public MetaExpressionType getMetaExpressionType() {
		return metaExpressionType;
	}

	public AbstractSemanticElement getLeftSemanticElement() {
		return leftSemanticElement;
	}

	public AbstractSemanticElement getRightSemanticElement() {
		return rightSemanticElement;
	}

	public String getLeftAttributeName() {
		return leftAttributeName;
	}

	public String getRightAttributeName() {
		return rightAttributeName;
	}

	public MetaExpression getLeftMetaExpression() {
		return leftMetaExpression;
	}

	public MetaExpression getRightMetaExpression() {
		return rightMetaExpression;
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

	public boolean isSingleInExpression()
	{
		if (metaExpressionType != null)
			return metaExpressionType.isSingleInExpression();
		return false;
		
	}
}
