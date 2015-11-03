package com.variamos.perspsupport.expressionsupport;

import java.io.Serializable;

import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.semanticinterface.IntSemanticExpression;
import com.variamos.perspsupport.types.ExpressionVertexType;

/**
 * A class to represent SemanticExpressions. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
public class SemanticExpression implements Serializable, IntSemanticExpression {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1977213643411960771L;
	private String identifier;
	private SemanticExpressionType semanticExpressionType;

	// TODO change to a new class for type (normal, relax)
	// private List<ExpressionSubAction> expressionSubActions;

	private String lastLeft = null;
	private String lastRight = null;

	private int leftNumber, rightNumber;
	private String leftString, rightString;
	private boolean recursiveExpression;

	private InstElement instElement;

	/**
	 * for LEFT Concept - Related Concept
	 */
	private InstElement leftSemanticElement;

	/**
	 * for LEFT Relation Concept
	 */
	private InstElement leftSemanticRelElement;

	/**
	 * for RIGHT Concept - Related Concept
	 */
	private InstElement rightSemanticElement;

	/**
	 * for RIGHT Relation Concept
	 */
	private InstElement rightSemanticRelElement;

	/**
	 * for LEFT
	 */
	private String leftAttributeName;

	/**
	 * for LEFT
	 */
	private String leftRelAttributeName;

	/**
	 * for RIGHT
	 */
	private String rightAttributeName;

	/**
	 * for RIGHT
	 */
	private String rightRelAttributeName;

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

	public SemanticExpression() {
	}

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

	public SemanticExpression(String identifier, InstElement instElement) {
		this.identifier = identifier;
		this.instElement = instElement;

		this.setLeftSemanticElement(instElement);
		this.setRightSemanticElement(instElement);
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
			InstElement leftSemanticElement, InstElement rightSemanticElement,
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
			ExpressionVertexType leftExpressionVertexType,
			ExpressionVertexType rightExpressionVertexType,
			InstElement leftSemanticElement, InstElement rightSemanticElement,
			String leftAttributeName, String rightAttributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		if (semanticExpressionType
				.equals(ExpressionVertexType.LEFTUNIQUEINCRELVARIABLE)
				|| semanticExpressionType
						.equals(ExpressionVertexType.LEFTUNIQUEOUTRELVARIABLE))
			this.leftRelAttributeName = leftAttributeName;
		else
			this.leftAttributeName = leftAttributeName;
		if (semanticExpressionType
				.equals(ExpressionVertexType.RIGHTUNIQUEINCRELVARIABLE)
				|| semanticExpressionType
						.equals(ExpressionVertexType.RIGHTUNIQUEOUTRELVARIABLE))
			this.rightRelAttributeName = rightAttributeName;
		else
			this.rightAttributeName = rightAttributeName;

		this.leftSemanticElement = leftSemanticElement;
		this.rightSemanticElement = rightSemanticElement;
		setLeftExpressionType(leftExpressionVertexType);
		setRightExpressionType(rightExpressionVertexType);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			InstElement leftSemanticElement, String leftAttributeName,
			int rightNumber) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticElement = leftSemanticElement;
		this.leftAttributeName = leftAttributeName;
		this.rightNumber = rightNumber;
		setLeftExpressionType(ExpressionVertexType.LEFT);
		setRightExpressionType(ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, String attributeName,
			boolean replaceRight, int number) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		if (replaceRight) {
			this.leftSemanticElement = semanticElement;
			this.leftAttributeName = attributeName;
			this.rightNumber = number;
			setLeftExpressionType(expressionVertexType);
			setRightExpressionType(ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE);
		} else {
			this.rightSemanticElement = semanticElement;
			this.rightAttributeName = attributeName;
			this.leftNumber = number;
			setRightExpressionType(expressionVertexType);
			setLeftExpressionType(ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE);
		}

	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType, int number,
			boolean replaceTarget, SemanticExpression semanticExpression) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		if (replaceTarget) {
			this.leftNumber = number;
			setLeftExpressionType(ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE);
			setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
			this.rightSemanticExpression = semanticExpression;
		} else {
			this.rightNumber = number;
			setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
			setRightExpressionType(ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE);
			this.leftSemanticExpression = semanticExpression;
		}
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			InstElement leftSemanticElement, String leftAttributeName,
			String rightString) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticElement = leftSemanticElement;
		this.leftAttributeName = leftAttributeName;
		this.rightString = rightString;
		setLeftExpressionType(ExpressionVertexType.LEFT);
		setRightExpressionType(ExpressionVertexType.RIGHTSTRINGVALUE);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType, String string,
			boolean replaceTarget, SemanticExpression semanticExpression) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		if (replaceTarget) {
			this.leftString = string;
			setLeftExpressionType(ExpressionVertexType.LEFTSTRINGVALUE);
			setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
			this.rightSemanticExpression = semanticExpression;
		} else {
			this.rightString = string;
			setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
			setRightExpressionType(ExpressionVertexType.RIGHTSTRINGVALUE);
			this.leftSemanticExpression = semanticExpression;
		}
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, String attributeName,
			boolean replaceTarget, SemanticExpression semanticExpression) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		if (replaceTarget) {

			this.leftSemanticElement = semanticElement;
			this.leftAttributeName = attributeName;
			setLeftExpressionType(expressionVertexType);
			setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
			this.rightSemanticExpression = semanticExpression;
		} else {
			this.rightSemanticElement = semanticElement;
			this.rightAttributeName = attributeName;
			setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
			setRightExpressionType(expressionVertexType);
			this.leftSemanticExpression = semanticExpression;
		}
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

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,

			ExpressionVertexType leftExpressionVertexType,
			InstElement leftSemanticElement, String attributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticElement = leftSemanticElement;
		this.leftAttributeName = attributeName;
		setLeftExpressionType(leftExpressionVertexType);
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

	public void setLeftNumber(int leftNumber) {
		this.leftNumber = leftNumber;
	}

	public void setRightNumber(int rightNumber) {
		this.rightNumber = rightNumber;
	}

	public void setLeftString(String leftString) {
		this.leftString = leftString;
	}

	public void setRightString(String rightString) {
		this.rightString = rightString;
	}

	public void setLeftSemanticElement(InstElement leftSemanticElement) {
		this.leftSemanticElement = leftSemanticElement;
	}

	public void setLeftSemanticElement() {
		this.leftSemanticElement = instElement;
	}

	public void setRightSemanticElement(InstElement rightSemanticElement) {
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
		if (type == ExpressionVertexType.LEFTSUBEXPRESSION
				|| type == ExpressionVertexType.LEFTINCOMRELVARIABLE
				|| type == ExpressionVertexType.LEFTOUTGRELVARIABLE
				|| type == ExpressionVertexType.LEFTANYRELVARIABLE)
			this.leftSemanticExpression = new SemanticExpression(id,
					instElement);
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
			this.rightSemanticExpression = new SemanticExpression(id,
					instElement);
		if (type == ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE)
			this.rightSemanticExpression = new SemanticExpression(id,
					semanticExpressionType);
		setRightExpressionType(type);
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

	public String getLeftRelAttributeName() {
		return leftRelAttributeName;
	}

	public String getRightAttributeName() {
		return rightAttributeName;
	}

	public String getRightRelAttributeName() {
		return rightRelAttributeName;
	}

	public SemanticExpression getLeftSemanticExpression() {
		return leftSemanticExpression;
	}

	public SemanticExpression getRightSemanticExpression() {
		return rightSemanticExpression;
	}

	public int getLeftNumber() {
		return leftNumber;
	}

	public int getRightNumber() {
		return rightNumber;
	}

	public String getLeftString() {
		return leftString;
	}

	public String getRightString() {
		return rightString;
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

	public void setLeftRelAttributeName(String attribute) {
		this.leftRelAttributeName = attribute;
	}

	public void setRightRelAttributeName(String attribute) {
		this.rightRelAttributeName = attribute;
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
		case LEFTRELATEDCONCEPT:
		case LEFTRELATIONCONCEPT:
		case LEFTINCOMRELVARIABLE:
		case LEFTOUTGRELVARIABLE:
		case LEFTANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
			if (leftSemanticElement != null)
				return leftSemanticElement.getIdentifier();
			break;

		case RIGHT:
		case RIGHTRELATEDCONCEPT:
		case RIGHTRELATIONCONCEPT:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
			if (rightSemanticElement != null)
				return rightSemanticElement.getIdentifier();
			break;
		default:
		}
		return null;
	}

	public InstElement getSideElement(ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFTINCOMRELVARIABLE:
		case LEFTOUTGRELVARIABLE:
		case LEFTANYRELVARIABLE:
		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFT:
		case LEFTRELATEDCONCEPT:
		case LEFTRELATIONCONCEPT:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
			return leftSemanticElement;
		case RIGHT:
		case RIGHTRELATEDCONCEPT:
		case RIGHTRELATIONCONCEPT:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
			return rightSemanticElement;
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
			boolean isConcept, char elementType) {
		String concept = null;
		String concept2 = null;
		String variable = null;
		String variable2 = null;
		switch (expressionVertexType) {

		case LEFTINCOMRELVARIABLE:
		case LEFTOUTGRELVARIABLE:
		case LEFTANYRELVARIABLE:
		case LEFTRELATEDCONCEPT:
		case LEFTRELATIONCONCEPT:

		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
			if (leftSemanticElement != null) {
				concept = leftSemanticElement.getIdentifier();
				variable = getLeftAttributeName();
			}
			if (leftSemanticRelElement != null) {
				concept2 = leftSemanticRelElement.getIdentifier();
				variable2 = getLeftRelAttributeName();
			}

			break;
		case RIGHTRELATEDCONCEPT:
		case RIGHTRELATIONCONCEPT:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
			if (rightSemanticElement != null) {
				concept = rightSemanticElement.getIdentifier();
				variable = getRightAttributeName();
			}
			if (rightSemanticRelElement != null) {
				concept2 = rightSemanticRelElement.getIdentifier();
				variable2 = getRightRelAttributeName();
			}
			break;
		default:
			return null;
		}
		if (elementType == 'P') {
			if (isConcept)
				return concept2;
			else
				return variable2;
		} else {
			if (isConcept)
				return concept;
			else
				return variable;
		}
	}

	public InstElement getSelectedElement(
			ExpressionVertexType expressionVertexType, char elementType) {
		InstElement concept = null;
		switch (expressionVertexType) {

		case LEFTINCOMRELVARIABLE:
		case LEFTOUTGRELVARIABLE:
		case LEFTANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
			if (leftSemanticElement != null) {
				if (elementType == 'C')
					concept = leftSemanticElement;
				if (elementType == 'P')
					concept = leftSemanticRelElement;
			}
			break;
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
			if (rightSemanticElement != null) {
				if (elementType == 'C')
					concept = rightSemanticElement;
				if (elementType == 'P')
					concept = rightSemanticElement;
			}
			break;
		default:
			return null;
		}
		return concept;
	}

	public void setInstElement(InstElement intSemanticElement,
			ExpressionVertexType expressionVertexType, char elementType) {
		switch (expressionVertexType) {
		case LEFT:
		case LEFTRELATEDCONCEPT:
		case LEFTRELATIONCONCEPT:
		case LEFTVARIABLEVALUE:
		case LEFTINCOMRELVARIABLE:
		case LEFTOUTGRELVARIABLE:
		case LEFTANYRELVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
			if (elementType == 'C')
				this.leftSemanticElement = intSemanticElement;
			if (elementType == 'P')
				this.leftSemanticRelElement = intSemanticElement;
			break;
		case RIGHT:
		case RIGHTRELATEDCONCEPT:
		case RIGHTRELATIONCONCEPT:
		case RIGHTVARIABLEVALUE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
			if (elementType == 'C')
				this.rightSemanticElement = intSemanticElement;
			if (elementType == 'P')
				this.rightSemanticRelElement = intSemanticElement;
		default:
		}
	}

	public void setAttributeName(String attributeName,
			ExpressionVertexType expressionVertexType, char elementType) {
		switch (expressionVertexType) {
		case LEFT:
		case LEFTRELATEDCONCEPT:
		case LEFTRELATIONCONCEPT:
		case LEFTINCOMRELVARIABLE:
		case LEFTOUTGRELVARIABLE:
		case LEFTANYRELVARIABLE:
		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
			if (elementType == 'C')
				this.setLeftAttributeName(attributeName);
			if (elementType == 'P')
				this.setLeftRelAttributeName(attributeName);
			break;
		case RIGHT:
		case RIGHTRELATEDCONCEPT:
		case RIGHTRELATIONCONCEPT:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
			if (elementType == 'C')
				this.setRightAttributeName(attributeName);
			if (elementType == 'P')
				this.setRightRelAttributeName(attributeName);
			break;
		default:
		}
	}
}
