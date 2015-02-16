package com.variamos.semantic.expressionsupport;

import java.io.Serializable;
import java.util.List;

import com.variamos.semantic.semanticsupport.AbstractSemanticElement;
import com.variamos.semantic.types.ExpressionVertexType;

/**
 * A class to represent SemanticExpressions. Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
public class SemanticExpression implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1977213643411960771L;
	private String identifier;
	private SemanticExpressionType semanticExpressionType;
	
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
	private SemanticExpression leftSemanticExpression;
	
	/**
	 * for RIGHTSUBEXPRESSION 
	 */
	private SemanticExpression rightSemanticExpression;
	
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

	public SemanticExpression() {

	}

	public SemanticExpression(String identifier) {
		this.identifier = identifier;
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);

	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			AbstractSemanticElement leftSemanticElement, AbstractSemanticElement rightSemanticElement,
			String leftAttributeName, String rightAttributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticElement = leftSemanticElement;
		this.rightSemanticElement = rightSemanticElement;
		this.leftAttributeName = leftAttributeName;
		this.rightAttributeName = rightAttributeName;
		setLeftExpressionType(ExpressionVertexType.LEFT);
		setRightExpressionType(ExpressionVertexType.RIGHT);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			AbstractSemanticElement semanticElement, String attributeName,
			boolean replaceTarget, SemanticExpression semanticExpression) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		if (replaceTarget) {
			this.leftSemanticElement = semanticElement;
			this.leftAttributeName = attributeName;
			setLeftExpressionType(ExpressionVertexType.LEFT);
			setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
			this.rightSemanticExpression = semanticExpression;
		} else {
			this.rightSemanticElement = semanticElement;
			this.rightAttributeName = attributeName;
			setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
			setRightExpressionType(ExpressionVertexType.RIGHT);
			this.leftSemanticExpression = semanticExpression;
		}
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			SemanticExpression leftSemanticExpression, SemanticExpression rightSemanticExpression) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticExpression = leftSemanticExpression;
		this.rightSemanticExpression = rightSemanticExpression;
		setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
		setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			AbstractSemanticElement leftSemanticElement, String attributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
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

	public void setLeftSemanticExpression(SemanticExpression leftSemanticExpression) {
		this.leftSemanticExpression = leftSemanticExpression;
	}

	public void setRightSemanticExpression(SemanticExpression rightSemanticExpression) {
		this.rightSemanticExpression = rightSemanticExpression;
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

	public void setSemanticExpressionType(SemanticExpressionType semanticExpressionType) {
		this.semanticExpressionType = semanticExpressionType;
	}

	public SemanticExpressionType getSemanticExpressionType() {
		return semanticExpressionType;
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

	public SemanticExpression getLeftSemanticExpression() {
		return leftSemanticExpression;
	}

	public SemanticExpression getRightSemanticExpression() {
		return rightSemanticExpression;
	}

	public int getNumber() {
		return number;
	}

	public int getLeftValidExpressions() {
		if (semanticExpressionType == null)
			return 0;
		return semanticExpressionType.getLeftValidExpressions();
	}

	public int getRightValidExpressions() {
		if (semanticExpressionType == null)
			return 0;
		return semanticExpressionType.getRightValidExpressions();
	}

	public int getResultExpressions() {
		if (semanticExpressionType == null)
			return 0;
		return semanticExpressionType.getResultExpressions();
	}

	public void setLeftAttributeName(String attribute) {
		this.leftAttributeName = attribute;
	}

	public void setRightAttributeName(String attribute) {
		this.rightAttributeName = attribute;
	}

	public String getOperation() {
		if (semanticExpressionType != null)
			return semanticExpressionType.getTextConnector();
		return null;
	}

	public boolean isSingleInExpression()
	{
		if (semanticExpressionType != null)
			return semanticExpressionType.isSingleInExpression();
		return false;
		
	}
}
