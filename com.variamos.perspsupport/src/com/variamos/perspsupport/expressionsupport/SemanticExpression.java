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

	private InstElement volSemElement;

	private String semElemId;

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
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			ExpressionVertexType leftExpressionVertexType,
			ExpressionVertexType rightExpressionVertexType,
			InstElement leftSemanticElement, InstElement rightSemanticElement,
			String leftAttributeName, String rightAttributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticElement(leftSemanticElement);
		this.leftAttributeName = leftAttributeName;
		this.setRightSemanticElement(rightSemanticElement);
		this.rightAttributeName = rightAttributeName;
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
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
		setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement leftSemanticElement, String leftAttributeName,
			int rightNumber) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticElement(leftSemanticElement);
		this.leftAttributeName = leftAttributeName;
		this.rightNumber = rightNumber;
		setLeftExpressionType(expressionVertexType);
		setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
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
			setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
		} else {
			this.setRightSemanticElement(semanticConElement);
			this.setRightSemanticRelElement(semanticRelElement);
			this.rightAttributeName = attributeName;
			this.leftNumber = number;
			setRightExpressionType(expressionVertexType);
			setLeftExpressionType(ExpressionVertexType.LEFTNUMERICVALUE);
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
			setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
		} else {
			this.setRightSemanticElement(semanticConElement);
			this.setRightSemanticRelElement(semanticRelElement);
			this.rightAttributeName = attributeName1;
			this.leftAttributeName = attributeName2;
			setRightExpressionType(expressionVertexType);
			setLeftExpressionType(ExpressionVertexType.RIGHTVARIABLE);
		}
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			SemanticExpression semanticExpression, int rightNumber) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticExpression(semanticExpression);
		this.setLeftSemanticElement(semanticConElement);
		this.rightNumber = rightNumber;
		setLeftExpressionType(expressionVertexType);
		setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			SemanticExpression semanticExpression, String rightAttribute) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.setLeftSemanticExpression(semanticExpression);
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticElement(semanticConElement);
		this.rightAttributeName = rightAttribute;
		this.setRightSemanticElement(semanticElement);
		setLeftExpressionType(expressionVertexType);
		setRightExpressionType(ExpressionVertexType.RIGHTCONCEPTVARIABLE);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType, int number,
			boolean replaceTarget, SemanticExpression semanticExpression) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		if (replaceTarget) {
			this.leftNumber = number;
			setLeftExpressionType(ExpressionVertexType.LEFTNUMERICVALUE);
			setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
			this.rightSemanticExpression = semanticExpression;
		} else {
			this.rightNumber = number;
			setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
			setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
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
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
		setRightExpressionType(ExpressionVertexType.RIGHTSTRINGVALUE);
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			ExpressionVertexType leftExpressionVertexType,
			InstElement semanticElement, InstElement leftSemanticElement,
			String leftAttributeName, String rightString) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setSemanticElement(semanticElement);
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
			setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
			setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
			this.rightSemanticExpression = semanticExpression;
		} else {
			this.setRightSemanticElement(semanticElement);
			this.rightAttributeName = attributeName;
			setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
			setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
			this.leftSemanticExpression = semanticExpression;
		}
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			SemanticExpression semanticExpression, int rightNumber) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticExpression = semanticExpression;
		setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
		setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
		this.rightNumber = rightNumber;
	}

	public SemanticExpression(String identifier,
			SemanticExpressionType semanticExpressionType,
			SemanticExpression semanticExpression, String attributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticExpression = semanticExpression;
		setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
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
		setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
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
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
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

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	public boolean isValidExpression() {
		switch (volatileLeftExpType) {
		case LEFTVARIABLE:
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERANYRELVARIABLE:
		case LEFTCONCEPTVARIABLE:
		case LEFTITERCONCEPTVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
			if (volatileLeftSemanticElement == null)
				return false;
		}
		switch (volatileRightExpType) {
		case RIGHTVARIABLE:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
			if (volatileRightSemanticElement == null)
				return false;
		}
		return true;
	};

	public String getSemElemId() {
		return semElemId;
	}

	public void setSemElemId(String semanticElementId) {
		this.semElemId = semanticElementId;
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
		if (semElemId != null)
			volSemElement = instVertices.get(semElemId);
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

	public InstElement getSemanticElement() {
		return volSemElement;
	}

	public void setSemanticElement(InstElement semanticElement) {
		this.volSemElement = semanticElement;
		if (semanticElement != null)
			this.semElemId = semanticElement.getIdentifier();
	}

	public void setLeftSemanticElement(InstElement leftSemanticElement) {
		this.volatileLeftSemanticElement = leftSemanticElement;
		if (leftSemanticElement != null)
			this.leftSemanticElementId = leftSemanticElement.getIdentifier();
		else
			leftSemanticElementId = null;
	}

	public void setLeftSemanticElement() {
		this.volatileLeftSemanticElement = volSemElement;
		this.leftSemanticElementId = volSemElement.getIdentifier();
	}

	public void setRightSemanticElement(InstElement rightSemanticElement) {
		this.volatileRightSemanticElement = rightSemanticElement;
		if (rightSemanticElement != null)
			this.rightSemanticElementId = rightSemanticElement.getIdentifier();
		else
			rightSemanticElementId = null;
	}

	public void setRightSemanticElement() {
		this.volatileRightSemanticElement = volSemElement;
		this.rightSemanticElementId = volSemElement.getIdentifier();
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
				|| type == ExpressionVertexType.LEFTITERCONCEPTVARIABLE
				|| type == ExpressionVertexType.LEFTITERFIXEDVARIABLE
				|| type == ExpressionVertexType.LEFTITERINCRELVARIABLE
				|| type == ExpressionVertexType.LEFTITEROUTRELVARIABLE
				|| type == ExpressionVertexType.LEFTITERANYRELVARIABLE)
			this.leftSemanticExpression = new SemanticExpression(id,
					volSemElement);
		if (type == ExpressionVertexType.LEFTNUMERICVALUE)
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
					volSemElement);
		if (type == ExpressionVertexType.RIGHTNUMERICVALUE)
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

	public InstElement getRightSemanticRelElement() {
		return volatileRightSemanticRelElement;
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
		case LEFTVARIABLE:
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTITERCONCEPTVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
			if (volatileLeftSemanticElement != null)
				return volatileLeftSemanticElement.getIdentifier();
			break;
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
			if (volatileLeftSemanticRelElement != null)
				return volatileLeftSemanticRelElement.getIdentifier();
			break;
		case RIGHTVARIABLE:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
			if (volatileRightSemanticElement != null)
				return volatileRightSemanticElement.getIdentifier();
			break;
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
			if (volatileRightSemanticRelElement != null)
				return volatileRightSemanticRelElement.getIdentifier();
			break;
		default:
		}
		return null;
	}

	public InstElement getSideElement(ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERANYRELVARIABLE:
		case LEFTCONCEPTVARIABLE:
		case LEFTITERCONCEPTVARIABLE:
		case LEFTVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
			return volatileLeftSemanticElement;
		case RIGHTVARIABLE:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
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
		String variable = null;
		switch (expressionVertexType) {

		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTITERCONCEPTVARIABLE:
		case LEFTITERFIXEDVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
			if (volatileLeftSemanticElement != null) {
				concept = volatileLeftSemanticElement.getIdentifier();
				variable = getLeftAttributeName();
			}
			break;
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
			if (volatileRightSemanticElement != null) {
				concept = volatileRightSemanticElement.getIdentifier();
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

	public InstElement getSelectedElement(
			ExpressionVertexType expressionVertexType, char elementType) {
		InstElement concept = null;
		switch (expressionVertexType) {

		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTITERCONCEPTVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
			if (volatileLeftSemanticElement != null) {
				concept = volatileLeftSemanticElement;
			}
			break;

		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
			if (volatileRightSemanticElement != null) {
				concept = volatileRightSemanticElement;
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
		case LEFTVARIABLE:
		case LEFTVARIABLEVALUE:
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERANYRELVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTCONCEPTVARIABLE:
		case LEFTITERCONCEPTVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
			this.setLeftSemanticElement(intSemanticElement);
			break;
		case RIGHTVARIABLE:
		case RIGHTVARIABLEVALUE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
			this.setRightSemanticElement(intSemanticElement);
			break;
		default:
		}
	}

	public void setAttributeName(String attributeName,
			ExpressionVertexType expressionVertexType, char elementType) {
		switch (expressionVertexType) {
		case LEFTVARIABLE:
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERANYRELVARIABLE:
		case LEFTCONCEPTVARIABLE:
		case LEFTITERCONCEPTVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
			this.setLeftAttributeName(attributeName);
			break;
		case RIGHTVARIABLE:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
			this.setRightAttributeName(attributeName);
			break;
		default:
		}
	}
}
