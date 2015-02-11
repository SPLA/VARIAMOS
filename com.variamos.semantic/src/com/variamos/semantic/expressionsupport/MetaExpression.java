package com.variamos.semantic.expressionsupport;

import java.io.Serializable;
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
public class MetaExpression implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1977213643411960771L;
	private String identifier;
	private MetaExpressionType metaExpressionType;
	
	// TODO change to a new class for type (normal, relax) 
	private List<ExpressionSubAction>  expressionSubActions;
	
	
	private int number;
	private boolean recursiveExpression;
	
	/**
	 * for LEFT
	 */
	private AbstractSemanticElement leftSemanticElement;
	
	/**
	 * for RIGHT
	 */
	private AbstractSemanticElement rightSemanticElement;
	
	/**
	 * for LEFT
	 */
	private String leftAttributeName;
	
	/**
	 * for RIGHT 
	 */
	private String rightAttributeName;
	
	/**
	 * for LEFTSUBEXPRESSION
	 */
	private MetaExpression leftMetaExpression;
	
	/**
	 * for RIGHTSUBEXPRESSION 
	 */
	private MetaExpression rightMetaExpression;
	
	/**
	 * for LEFTSUBEXPRESSION
	 */
	private ExpressionVertexType volatileLeftExpType;
	
	/**
	 * for LEFTSUBEXPRESSION XML serialization
	 */
	private String leftExpTypeStr;
	
	/**
	 * for RIGHTSUBEXPRESSION 
	 */
	private ExpressionVertexType volatileRightExpType;

	/**
	 * for RIGHTSUBEXPRESSION  XML Serialization
	 */
	private String rightExpTypeStr;

	public MetaExpression() {

	}

	public MetaExpression(String identifier) {
		this.identifier = identifier;
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);

	}

	public MetaExpression(String identifier,
			MetaExpressionType metaExpressionType) {
		this.identifier = identifier;
		this.metaExpressionType = metaExpressionType;
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);
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
		setLeftExpressionType(ExpressionVertexType.LEFT);
		setRightExpressionType(ExpressionVertexType.RIGHT);
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
			setLeftExpressionType(ExpressionVertexType.LEFT);
			setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
			this.rightMetaExpression = leftMetaExpression;
		} else {
			this.rightSemanticElement = leftSemanticElement;
			this.rightAttributeName = leftAttributeName;
			setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
			setRightExpressionType(ExpressionVertexType.RIGHT);
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
		setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
		setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
	}

	public MetaExpression(String identifier,
			MetaExpressionType metaExpressionType,
			AbstractSemanticElement leftSemanticElement, String attributeName) {
		this.identifier = identifier;
		this.metaExpressionType = metaExpressionType;
		this.leftSemanticElement = leftSemanticElement;
		this.leftAttributeName = attributeName;
		setLeftExpressionType(ExpressionVertexType.LEFT);
	}

	public String getLeftExpTypeStr() {
		return leftExpTypeStr;
	}

	public void setLeftExpTypeStr(String leftExpressionTypeString) {
		this.leftExpTypeStr = leftExpressionTypeString;
		this.volatileLeftExpType = ExpressionVertexType.valueOf(leftExpTypeStr);
	}

	public String getRightExpTypeStr() {
		return rightExpTypeStr;
	}

	public void setRightExpTypeStr(String rightExpressionTypeString) {
		this.rightExpTypeStr = rightExpressionTypeString;
		this.volatileRightExpType = ExpressionVertexType.valueOf(rightExpTypeStr);
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public ExpressionVertexType getLeftExpressionType() {
		return volatileLeftExpType;
	}

	public void setLeftExpressionType(ExpressionVertexType leftExpressionType) {
		this.volatileLeftExpType = leftExpressionType;
		this.leftExpTypeStr = volatileLeftExpType.toString();
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
		return volatileRightExpType;
	}

	public void setRightExpressionType(ExpressionVertexType rightExpressionType) {
		this.volatileRightExpType = rightExpressionType;
		this.rightExpTypeStr = volatileRightExpType.toString();
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
