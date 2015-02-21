package com.variamos.semantic.expressionsupport;

import java.io.Serializable;
import java.util.List;

import com.variamos.semantic.semanticsupport.AbstractSemanticElement;
import com.variamos.semantic.types.ExpressionVertexType;
import com.variamos.semantic.types.RecursiveExpressionType;
import com.variamos.syntax.instancesupport.InstElement;
import com.variamos.syntax.instancesupport.InstVertex;
import com.variamos.syntax.semanticinterface.IntSemanticElement;

/**
 * A class to represent SemanticExpressions. Part of PhD work at University of
 * Paris 1
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
	private List<ExpressionSubAction> expressionSubActions;

	private String lastLeft = null;
	private String lastRight = null;

	private int number;
	private boolean recursiveExpression;
	private RecursiveExpressionType recursiveExpressionType;
	
	private InstElement instElement;

	/**
	 * for LEFT
	 */
	private InstElement leftSemanticElement;

	/**
	 * for RIGHT
	 */
	private InstElement rightSemanticElement;

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
	 * for RIGHTSUBEXPRESSION XML Serialization
	 */
	private String rightExpTypeStr;

	public SemanticExpression(InstElement instElement) {
		this.instElement = instElement;
		this.setLeftSemanticElement(instElement);
		this.setRightSemanticElement(instElement);
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
			InstElement leftSemanticElement,
			InstElement rightSemanticElement,
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
			InstElement semanticElement, String attributeName,
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
			SemanticExpression leftSemanticExpression,
			SemanticExpression rightSemanticExpression) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticExpression = leftSemanticExpression;
		this.rightSemanticExpression = rightSemanticExpression;
		setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
		setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			InstElement leftSemanticElement, String attributeName) {
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
		this.volatileRightExpType = ExpressionVertexType
				.valueOf(rightExpTypeStr);
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

	public void setLeftSemanticElement(
			InstElement leftSemanticElement) {
		this.leftSemanticElement = leftSemanticElement;
	}
	public void setLeftSemanticElement() {
		this.leftSemanticElement = instElement;
	}

	public void setRightSemanticElement(
			InstElement rightSemanticElement) {
		this.rightSemanticElement = rightSemanticElement;
	}
	public void setRightSemanticElement() {
		this.rightSemanticElement = instElement;
	}

	public void setLeftSemanticExpression(
			SemanticExpression leftSemanticExpression) {
		this.leftSemanticExpression = leftSemanticExpression;
	}

	public void setLeftSemanticExpression(ExpressionVertexType type,
			SemanticExpressionType semanticExpressionType, String id) {
		if (type == ExpressionVertexType.LEFTSUBEXPRESSION)
			this.leftSemanticExpression = new SemanticExpression(id);
		if (type == ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE)
			this.leftSemanticExpression = new SemanticExpression(id,
					semanticExpressionType);
		setLeftExpressionType(type);
	}

	public void setRightSemanticExpression(
			SemanticExpression rightSemanticExpression) {
		this.rightSemanticExpression = rightSemanticExpression;
	}

	public void setRightSemanticExpression(ExpressionVertexType type,
			SemanticExpressionType semanticExpressionType, String id) {
		if (type == ExpressionVertexType.RIGHTSUBEXPRESSION)
			this.rightSemanticExpression = new SemanticExpression(id);
		if (type == ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE)
			this.rightSemanticExpression = new SemanticExpression(id,
					semanticExpressionType);
		setLeftExpressionType(type);
	}

	public ExpressionVertexType getRightExpressionType() {
		return volatileRightExpType;
	}

	public void setRightExpressionType(ExpressionVertexType rightExpressionType) {
		this.volatileRightExpType = rightExpressionType;
		this.rightExpTypeStr = volatileRightExpType.toString();
	}

	public ExpressionVertexType[] getExpressionTypes() {
		ExpressionVertexType[] out = new ExpressionVertexType[2];
		out[0] = getLeftExpressionType();
		out[1] = getRightExpressionType();
		return out;
	}

	public void setSemanticExpressionType(
			SemanticExpressionType semanticExpressionType) {
		this.semanticExpressionType = semanticExpressionType;
	}

	public SemanticExpressionType getSemanticExpressionType() {
		return semanticExpressionType;
	}

	public InstElement getLeftSemanticElement() {
		return leftSemanticElement;
	}

	public InstElement getRightSemanticElement() {
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

	public boolean isSingleInExpression() {
		if (semanticExpressionType != null)
			return semanticExpressionType.isSingleInExpression();
		return false;

	}

	public String getSideElementIdentifier(
			ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFT:
		case LEFTINCOMRELVARIABLE:
		case LEFTOUTGRELVARIABLE:
		case LEFTANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTANYCONCEPTVARIABLE:
			if (leftSemanticElement != null)
				return leftSemanticElement.getIdentifier();
			break;
		case RIGHT:
			if (rightSemanticElement != null)
				return rightSemanticElement.getIdentifier();
			break;
		default:
		}
		return null;
	}

	public InstElement getSideElement(
			ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFTINCOMRELVARIABLE:
		case LEFTOUTGRELVARIABLE:
		case LEFTANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTANYCONCEPTVARIABLE:
		case LEFT:
			if (leftSemanticElement != null)
				return leftSemanticElement;
			break;
		case RIGHT:
			if (rightSemanticElement != null)
				return rightSemanticElement;
			break;
		default:
		}
		return null;
	}

	public String getLastLeft() {
		return lastLeft;
	}

	public void setLastLeft(String lastLeft) {
		this.lastLeft = lastLeft;
	}

	public String getLastRight() {
		return lastRight;
	}

	public void setLastRight(String lastRight) {
		this.lastRight = lastRight;
	}

	public String getSelectedElement(ExpressionVertexType expressionVertexType,
			boolean isConcept) {
		String concept = null;
		String variable = null;
		switch (expressionVertexType) {

		case LEFTINCOMRELVARIABLE:
		case LEFTOUTGRELVARIABLE:
		case LEFTANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTANYCONCEPTVARIABLE:
			if (leftSemanticElement != null) {
				concept = leftSemanticElement.getIdentifier();
				variable = getLeftAttributeName();
			}
			break;
		case RIGHTCONCEPTVARIABLE:
			if (rightSemanticElement != null) {
				concept = rightSemanticElement.getIdentifier();
				variable = getRightAttributeName();
			}
			break;

		default:
			return null;
		}
		if (isConcept)
			return concept;
		else
			return variable;
	}

	public void setInstElement(InstElement intSemanticElement,
			ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFT:
		case LEFTVARIABLEVALUE:
		case LEFTINCOMRELVARIABLE:
		case LEFTOUTGRELVARIABLE:
		case LEFTANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTANYCONCEPTVARIABLE:
			this.leftSemanticElement = intSemanticElement;
			break;
		case RIGHT:
		case RIGHTVARIABLEVALUE:
			this.rightSemanticElement = intSemanticElement;
		default:
		}
	}

	public void setAttributeName(String attributeName,
			ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFT:
		case LEFTINCOMRELVARIABLE:
		case LEFTOUTGRELVARIABLE:
		case LEFTANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTANYCONCEPTVARIABLE:
			this.setLeftAttributeName(attributeName);
			break;
		case RIGHT:
			this.setRightAttributeName(attributeName);
			break;
		default:
		}
	}
}
