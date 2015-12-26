package com.variamos.perspsupport.expressionsupport;

import java.io.Serializable;
import java.util.Map;

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

	private InstElement volatileSemanticElement;

	private String semanticElementId;

	/**
	 * for LEFT Concept (Definition concept)
	 */
	private InstElement volatileLeftSemanticElement;

	/**
	 * for LEFT Relation/Related Concept
	 */
	private InstElement volatileLeftSemanticRelElement;

	private String rightSemanticElementId;
	private String leftSemanticElementId;
	private String rightSemanticRelElementId;
	private String leftSemanticRelElementId;

	/**
	 * for RIGHT Concept (Definition concept)
	 */
	private InstElement volatileRightSemanticElement;

	/**
	 * for RIGHT Relation Concept
	 */
	private InstElement volatileRightSemanticRelElement;

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
		this.setSemanticElement(instElement);
		this.setLeftSemanticElement(instElement);
		this.setRightSemanticElement(instElement);
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);
	}

	public SemanticExpression(String identifier) {
		this.identifier = identifier;
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);
	}

	public SemanticExpression(String identifier, InstElement instElement) {
		this.identifier = identifier;
		this.setSemanticElement(instElement);
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
		this.setLeftSemanticElement(leftSemanticElement);
		this.setRightSemanticElement(rightSemanticElement);
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
		this.setLeftSemanticElement(leftSemanticElement);
		this.setRightSemanticElement(rightSemanticElement);
		setLeftExpressionType(leftExpressionVertexType);
		setRightExpressionType(rightExpressionVertexType);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			InstElement leftSemanticElement, String leftAttributeName,
			int rightNumber) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticElement(leftSemanticElement);
		this.leftAttributeName = leftAttributeName;
		this.rightNumber = rightNumber;
		setLeftExpressionType(ExpressionVertexType.LEFT);
		setRightExpressionType(ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			InstElement semanticRelElement, String attributeName,
			boolean replaceRight, int number) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.semanticExpressionType = semanticExpressionType;
		if (replaceRight) {
			this.setLeftSemanticElement(semanticConElement);
			this.setLeftSemanticRelElement(semanticRelElement);
			this.leftAttributeName = attributeName;
			this.rightNumber = number;
			setLeftExpressionType(expressionVertexType);
			setRightExpressionType(ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE);
		} else {
			this.setRightSemanticElement(semanticConElement);
			this.setRightSemanticRelElement(semanticRelElement);
			this.rightAttributeName = attributeName;
			this.leftNumber = number;
			setRightExpressionType(expressionVertexType);
			setLeftExpressionType(ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE);
		}
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			InstElement semanticRelElement, String attributeName1,
			String attributeName2, boolean replaceRight) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.semanticExpressionType = semanticExpressionType;
		if (replaceRight) {
			this.setLeftSemanticElement(semanticConElement);
			this.setLeftSemanticRelElement(semanticRelElement);
			this.leftAttributeName = attributeName1;
			this.rightAttributeName = attributeName2;
			setLeftExpressionType(expressionVertexType);
			setRightExpressionType(ExpressionVertexType.RIGHT);
		} else {
			this.setRightSemanticElement(semanticConElement);
			this.setRightSemanticRelElement(semanticRelElement);
			this.rightAttributeName = attributeName1;
			this.leftAttributeName = attributeName2;
			setRightExpressionType(expressionVertexType);
			setLeftExpressionType(ExpressionVertexType.RIGHT);
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
		this.setLeftSemanticElement(leftSemanticElement);
		this.leftAttributeName = leftAttributeName;
		this.rightString = rightString;
		setLeftExpressionType(ExpressionVertexType.LEFT);
		setRightExpressionType(ExpressionVertexType.RIGHTSTRINGVALUE);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			ExpressionVertexType leftExpressionVertexType,
			InstElement leftSemanticElement, String leftAttributeName,
			String rightString) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticElement(leftSemanticElement);
		this.leftAttributeName = leftAttributeName;
		this.rightString = rightString;
		setLeftExpressionType(leftExpressionVertexType);
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
			this.setLeftSemanticElement(semanticElement);
			this.leftAttributeName = attributeName;
			setLeftExpressionType(expressionVertexType);
			setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
			this.rightSemanticExpression = semanticExpression;
		} else {
			this.setRightSemanticElement(semanticElement);
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
			this.setLeftSemanticElement(semanticElement);
			this.leftAttributeName = attributeName;
			setLeftExpressionType(ExpressionVertexType.LEFT);
			setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
			this.rightSemanticExpression = semanticExpression;
		} else {
			this.setRightSemanticElement(semanticElement);
			this.rightAttributeName = attributeName;
			setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
			setRightExpressionType(ExpressionVertexType.RIGHT);
			this.leftSemanticExpression = semanticExpression;
		}
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			SemanticExpression semanticExpression, int rightNumber) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticExpression = semanticExpression;
		setLeftExpressionType(ExpressionVertexType.LEFTRELATIONCONCEPT);
		setRightExpressionType(ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE);
		this.rightNumber = rightNumber;
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			SemanticExpression semanticExpression, String attributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticExpression = semanticExpression;
		setLeftExpressionType(ExpressionVertexType.LEFTRELATIONCONCEPT);
		setRightExpressionType(ExpressionVertexType.RIGHT);
		this.rightAttributeName = attributeName;
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			SemanticExpression semanticExpression,
			ExpressionVertexType rightExpressionType,
			InstElement rightSemanticElement, String rightAttribute) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticExpression = semanticExpression;
		setLeftExpressionType(ExpressionVertexType.LEFTRELATIONCONCEPT);
		setRightExpressionType(rightExpressionType);
		this.setRightSemanticElement(rightSemanticElement);
		this.rightAttributeName = rightAttribute;
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
		this.setLeftSemanticElement(leftSemanticElement);
		this.leftAttributeName = attributeName;
		setLeftExpressionType(ExpressionVertexType.LEFT);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			ExpressionVertexType leftExpressionVertexType,
			InstElement leftSemanticElement, String attributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticElement(leftSemanticElement);
		this.leftAttributeName = attributeName;
		setLeftExpressionType(leftExpressionVertexType);
	}

	public String getSemanticElementId() {
		return semanticElementId;
	}

	public void setSemanticElementId(String semanticElementId) {
		this.semanticElementId = semanticElementId;
	}

	public String getRightSemanticElementId() {
		return rightSemanticElementId;
	}

	public void setRightSemanticElementId(String rightSemanticElementId) {
		this.rightSemanticElementId = rightSemanticElementId;
	}

	public String getLeftSemanticElementId() {
		return leftSemanticElementId;
	}

	public void setLeftSemanticElementId(String leftSemanticElementId) {
		this.leftSemanticElementId = leftSemanticElementId;
	}

	public String getRightSemanticRelElementId() {
		return rightSemanticRelElementId;
	}

	public void setRightSemanticRelElementId(String rightSemanticRelElementId) {
		this.rightSemanticRelElementId = rightSemanticRelElementId;
	}

	public String getLeftSemanticRelElementId() {
		return leftSemanticRelElementId;
	}

	public void setLeftSemanticRelElementId(String leftSemanticRelElementId) {
		this.leftSemanticRelElementId = leftSemanticRelElementId;
	}

	public void loadVolatileElements(Map<String, InstElement> instVertices) {
		if (semanticElementId != null)
			volatileSemanticElement = instVertices.get(semanticElementId);
		if (leftExpTypeStr != null)
			volatileLeftExpType = ExpressionVertexType.valueOf(leftExpTypeStr);
		if (rightExpTypeStr != null)
			volatileRightExpType = ExpressionVertexType
					.valueOf(rightExpTypeStr);
		if (leftSemanticElementId != null)
			volatileLeftSemanticElement = instVertices
					.get(leftSemanticElementId);
		if (rightSemanticElementId != null)
			volatileRightSemanticElement = instVertices
					.get(rightSemanticElementId);
		if (leftSemanticRelElementId != null)
			volatileLeftSemanticRelElement = instVertices
					.get(leftSemanticRelElementId);
		if (rightSemanticRelElementId != null)
			volatileRightSemanticRelElement = instVertices
					.get(rightSemanticRelElementId);
		if (leftSemanticExpression != null)
			leftSemanticExpression.loadVolatileElements(instVertices);
		if (rightSemanticExpression != null)
			rightSemanticExpression.loadVolatileElements(instVertices);
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

	public void setSemanticElement(InstElement semanticElement) {
		this.volatileSemanticElement = semanticElement;
		this.semanticElementId = semanticElement.getIdentifier();
	}

	public void setLeftSemanticElement(InstElement leftSemanticElement) {
		this.volatileLeftSemanticElement = leftSemanticElement;
		if (leftSemanticElement != null)
			this.leftSemanticElementId = leftSemanticElement.getIdentifier();
		else
			leftSemanticElementId = null;
	}

	public void setLeftSemanticElement() {
		this.volatileLeftSemanticElement = volatileSemanticElement;
		this.leftSemanticElementId = volatileSemanticElement.getIdentifier();
	}

	public void setRightSemanticElement(InstElement rightSemanticElement) {
		this.volatileRightSemanticElement = rightSemanticElement;
		if (rightSemanticElement != null)
			this.rightSemanticElementId = rightSemanticElement.getIdentifier();
		else
			rightSemanticElementId = null;
	}

	public void setRightSemanticElement() {
		this.volatileRightSemanticElement = volatileSemanticElement;
		this.rightSemanticElementId = volatileSemanticElement.getIdentifier();
	}

	public void setLeftSemanticRelElement(InstElement leftSemanticRelElement) {
		this.volatileLeftSemanticRelElement = leftSemanticRelElement;
		if (leftSemanticRelElement != null)
			this.leftSemanticRelElementId = leftSemanticRelElement
					.getIdentifier();
		else
			this.leftSemanticRelElementId = null;
	}

	public void setRightSemanticRelElement(InstElement rightSemanticRelElement) {
		this.volatileRightSemanticRelElement = rightSemanticRelElement;
		if (rightSemanticRelElement != null)
			this.rightSemanticRelElementId = rightSemanticRelElement
					.getIdentifier();
		else
			this.leftSemanticRelElementId = null;
	}

	public void setLeftSemanticExpression(
			SemanticExpression leftSemanticExpression) {
		this.leftSemanticExpression = leftSemanticExpression;
	}

	public void setLeftSemanticExpression(ExpressionVertexType type,
			SemanticExpressionType semanticExpressionType, String id) {
		if (type == ExpressionVertexType.LEFTSUBEXPRESSION
				|| type == ExpressionVertexType.LEFTITERINCRELVARIABLE
				|| type == ExpressionVertexType.LEFTITEROUTRELVARIABLE
				|| type == ExpressionVertexType.LEFTANYRELVARIABLE)
			this.leftSemanticExpression = new SemanticExpression(id,
					volatileSemanticElement);
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
					volatileSemanticElement);
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
		return volatileLeftSemanticElement;
	}

	public InstElement getRightSemanticElement() {
		return volatileRightSemanticElement;
	}

	public InstElement getLeftSemanticRelElement() {
		return volatileLeftSemanticRelElement;
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
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
			if (volatileLeftSemanticElement != null)
				return volatileLeftSemanticElement.getIdentifier();
			break;

		case RIGHT:
		case RIGHTRELATEDCONCEPT:
		case RIGHTRELATIONCONCEPT:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
			if (volatileRightSemanticElement != null)
				return volatileRightSemanticElement.getIdentifier();
			break;
		default:
		}
		return null;
	}

	public InstElement getSideElement(ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
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
			return volatileLeftSemanticElement;
		case RIGHT:
		case RIGHTRELATEDCONCEPT:
		case RIGHTRELATIONCONCEPT:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
			return volatileRightSemanticElement;
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

		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTANYRELVARIABLE:
		case LEFTRELATEDCONCEPT:
		case LEFTRELATIONCONCEPT:

		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
			if (volatileLeftSemanticElement != null) {
				concept = volatileLeftSemanticElement.getIdentifier();
				variable = getLeftAttributeName();
			}
			if (volatileLeftSemanticRelElement != null) {
				concept2 = volatileLeftSemanticRelElement.getIdentifier();
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
			if (volatileRightSemanticElement != null) {
				concept = volatileRightSemanticElement.getIdentifier();
				variable = getRightAttributeName();
			}
			if (volatileRightSemanticRelElement != null) {
				concept2 = volatileRightSemanticRelElement.getIdentifier();
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

		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
			if (volatileLeftSemanticElement != null) {
				if (elementType == 'C')
					concept = volatileLeftSemanticElement;
				if (elementType == 'P')
					concept = volatileLeftSemanticRelElement;
			}
			break;
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
			if (volatileRightSemanticElement != null) {
				if (elementType == 'C')
					concept = volatileRightSemanticElement;
				if (elementType == 'P')
					concept = volatileRightSemanticRelElement;
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
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTANYRELVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTCONCEPTVARIABLE:
		case LEFTCONCEPTTYPEVARIABLE:
			if (elementType == 'C')
				this.setLeftSemanticElement(intSemanticElement);
			if (elementType == 'P')
				this.volatileLeftSemanticRelElement = intSemanticElement;
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
				this.setRightSemanticElement(intSemanticElement);
			if (elementType == 'P')
				this.volatileRightSemanticRelElement = intSemanticElement;
		default:
		}
	}

	public void setAttributeName(String attributeName,
			ExpressionVertexType expressionVertexType, char elementType) {
		switch (expressionVertexType) {
		case LEFT:
		case LEFTRELATEDCONCEPT:
		case LEFTRELATIONCONCEPT:
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
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
