package com.variamos.dynsup.model;

import java.io.Serializable;
import java.util.Map;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.types.ExpressionVertexType;

/**
 * A class to represent SemanticExpressions. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
public class OpersExpr implements Serializable {
	// FIXME v1.1 RENAME to SeSupExpr
	/**
	 * 
	 */
	private static final long serialVersionUID = -1977213643411960771L;
	private String identifier;
	private OpersExprType semanticExpressionType;
	private String naturalLangDesc;

	// TODO change to a new class for type (normal, relax)
	// private List<ExpressionSubAction> expressionSubActions;

	private String lastLeft = null;
	private String lastRight = null;

	private int leftNumber, rightNumber;
	private float rightFloatNumber;
	private String leftString, rightString;
	private boolean recursiveExpression;

	private InstElement volSemElement;

	private String semElemId;

	/**
	 * for LEFT Concept (Definition concept)
	 */
	private InstElement volatileLeftSemanticElement;
	private String rightSemanticElementId;
	private String leftSemanticElementId;
	private String rightSemanticRelElementId;
	private String leftSemanticRelElementId;

	/**
	 * for RIGHT Concept (Definition concept)
	 */
	private InstElement volatileRightSemanticElement;

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
	private OpersExpr leftSemanticExpression;

	/**
	 * for RIGHTSUBEXPRESSION
	 */
	private OpersExpr rightSemanticExpression;

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

	public OpersExpr() {
	}

	public OpersExpr(InstElement instElement) {
		identifier = "     ";
		naturalLangDesc = "     ";
		this.setSemanticElement(instElement);
		this.setLeftSemanticElement(instElement);
		this.setRightSemanticElement(instElement);
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);
		this.setSemanticExpressionType(semanticExpressionType);
	}

	public OpersExpr(InstElement instElement,
			OpersExprType semanticExpressionType) {
		this("     ", semanticExpressionType);
		naturalLangDesc = "     ";
		this.setSemanticElement(instElement);
		this.setLeftSemanticElement(instElement);
		this.setRightSemanticElement(instElement);
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);
		this.setSemanticExpressionType(semanticExpressionType);
	}

	public OpersExpr(String identifier, String naturalLangDesc) {
		this(identifier);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier) {
		this.identifier = identifier;
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);
	}

	public OpersExpr(String identifier, InstElement instElement) {
		this.identifier = identifier;
		this.setSemanticElement(instElement);
		this.setLeftSemanticElement(instElement);
		this.setRightSemanticElement(instElement);
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);
	}

	public OpersExpr(String identifier, InstElement instElement,
			OpersExprType semanticExpressionType) {
		this(identifier, instElement);

	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			String naturalLangDesc) {
		this(identifier, semanticExpressionType);
		this.naturalLangDesc = naturalLangDesc;
		this.semanticExpressionType = semanticExpressionType;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);
	}

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType, InstElement semanticElement,
			InstElement leftSemanticElement, InstElement rightSemanticElement,
			String leftAttributeName, String rightAttributeName) {
		this(identifier, semanticExpressionType, semanticElement,
				leftSemanticElement, rightSemanticElement, leftAttributeName,
				rightAttributeName);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			InstElement semanticElement, InstElement leftSemanticElement,
			InstElement rightSemanticElement, String leftAttributeName,
			String rightAttributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setSemanticElement(semanticElement);
		this.setLeftSemanticElement(leftSemanticElement);
		this.setRightSemanticElement(rightSemanticElement);
		this.leftAttributeName = leftAttributeName;
		this.rightAttributeName = rightAttributeName;
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
	}

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType,
			ExpressionVertexType leftExpressionVertexType,
			ExpressionVertexType rightExpressionVertexType,
			InstElement semanticElement, InstElement leftSemanticElement,
			InstElement rightSemanticElement, String leftAttributeName,
			String rightAttributeName) {
		this(identifier, semanticExpressionType, leftExpressionVertexType,
				rightExpressionVertexType, semanticElement,
				leftSemanticElement, rightSemanticElement, leftAttributeName,
				rightAttributeName);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType leftExpressionVertexType,
			ExpressionVertexType rightExpressionVertexType,
			InstElement semanticElement, InstElement leftSemanticElement,
			InstElement rightSemanticElement, String leftAttributeName,
			String rightAttributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setSemanticElement(semanticElement);
		this.setLeftSemanticElement(leftSemanticElement);
		this.leftAttributeName = leftAttributeName;
		this.setRightSemanticElement(rightSemanticElement);
		this.rightAttributeName = rightAttributeName;
		setLeftExpressionType(leftExpressionVertexType);
		setRightExpressionType(rightExpressionVertexType);
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			InstElement semanticElement, InstElement semanticConElement,
			String attributeName, boolean replaceRight, int number,
			String naturalLangDesc) {
		this(identifier, semanticExpressionType, semanticElement,
				semanticConElement, attributeName, replaceRight, number);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			InstElement semanticElement, InstElement semanticConElement,
			String attributeName, boolean replaceRight, int number) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setSemanticElement(semanticElement);
		if (replaceRight) {

			this.setLeftSemanticElement(semanticConElement);
			this.leftAttributeName = attributeName;
			this.rightNumber = number;
			setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
			setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
		} else {
			this.setRightSemanticElement(semanticConElement);
			this.rightAttributeName = attributeName;
			this.leftNumber = number;
			setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
			setLeftExpressionType(ExpressionVertexType.LEFTNUMERICVALUE);
		}
	}

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement leftSemanticElement,
			String leftAttributeName, int rightNumber) {
		this(identifier, semanticExpressionType, expressionVertexType,
				semanticElement, leftSemanticElement, leftAttributeName,
				rightNumber);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement leftSemanticElement,
			String leftAttributeName, int rightNumber) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setSemanticElement(semanticElement);
		this.setLeftSemanticElement(leftSemanticElement);
		this.setRightSemanticElement(semanticElement);
		this.leftAttributeName = leftAttributeName;
		this.rightNumber = rightNumber;
		setLeftExpressionType(expressionVertexType);
		setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
	}

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement leftSemanticElement,
			String leftAttributeName, boolean replaceRight,
			String rightAttributeName) {
		this(identifier, semanticExpressionType, expressionVertexType,
				semanticElement, leftSemanticElement, leftAttributeName,
				replaceRight, rightAttributeName);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement leftSemanticElement,
			String leftAttributeName, boolean replaceRight,
			String rightAttributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setSemanticElement(semanticElement);
		this.setLeftSemanticElement(leftSemanticElement);
		this.leftAttributeName = leftAttributeName;
		this.rightAttributeName = rightAttributeName;
		this.setRightSemanticElement(semanticElement);
		setLeftExpressionType(expressionVertexType);
		setRightExpressionType(ExpressionVertexType.RIGHTCONCEPTVARIABLE);
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			String attributeName, boolean replaceRight, int number,
			String naturalLangDesc) {
		this(identifier, semanticExpressionType, expressionVertexType,
				semanticElement, semanticConElement, attributeName,
				replaceRight, number);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			String attributeName, boolean replaceRight, int number) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.semanticExpressionType = semanticExpressionType;
		if (replaceRight) {
			this.setLeftSemanticElement(semanticConElement);
			this.leftAttributeName = attributeName;
			this.rightNumber = number;
			setLeftExpressionType(expressionVertexType);
			this.setRightSemanticElement(semanticElement);
			setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
		} else {
			this.setRightSemanticElement(semanticConElement);
			this.rightAttributeName = attributeName;
			this.leftNumber = number;
			setRightExpressionType(expressionVertexType);
			this.setLeftSemanticElement(semanticElement);
			setLeftExpressionType(ExpressionVertexType.LEFTNUMERICVALUE);
		}
	}

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			InstElement semanticRelElement, String attributeName1,
			String attributeName2, boolean replaceRight) {
		this(identifier, semanticExpressionType, expressionVertexType,
				semanticElement, semanticConElement, semanticRelElement,
				attributeName1, attributeName2, replaceRight);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			InstElement semanticRelElement, String attributeName1,
			String attributeName2, boolean replaceRight) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.semanticExpressionType = semanticExpressionType;
		if (replaceRight) {
			this.setLeftSemanticElement(semanticConElement);
			this.leftAttributeName = attributeName1;
			this.rightAttributeName = attributeName2;
			setLeftExpressionType(expressionVertexType);
			setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
			setRightSemanticElement(semanticConElement);
		} else {
			this.setRightSemanticElement(semanticConElement);
			this.rightAttributeName = attributeName1;
			this.leftAttributeName = attributeName2;
			setRightExpressionType(expressionVertexType);
			setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
			setLeftSemanticElement(semanticConElement);
		}
	}

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			InstElement semanticRelElement, String attributeName1,
			int attributeValue, boolean replaceRight) {
		this(identifier, semanticExpressionType, expressionVertexType,
				semanticElement, semanticConElement, semanticRelElement,
				attributeName1, attributeValue, replaceRight);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			InstElement semanticRelElement, String attributeName1,
			int attributeValue, boolean replaceRight) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.semanticExpressionType = semanticExpressionType;
		if (replaceRight) {
			this.setLeftSemanticElement(semanticConElement);
			this.leftAttributeName = attributeName1;
			this.rightNumber = attributeValue;
			setLeftExpressionType(expressionVertexType);
			setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
		} else {
			this.setRightSemanticElement(semanticConElement);
			this.rightAttributeName = attributeName1;
			this.leftNumber = attributeValue;
			setRightExpressionType(expressionVertexType);
			setLeftExpressionType(ExpressionVertexType.LEFTNUMERICVALUE);
		}
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			OpersExpr semanticExpression, int rightNumber,
			String naturalLangDesc) {
		this(identifier, semanticExpressionType, expressionVertexType,
				semanticElement, semanticConElement, semanticExpression,
				rightNumber);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			OpersExpr semanticExpression, int rightNumber) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticExpression(semanticExpression);
		this.setLeftSemanticElement(semanticConElement);
		this.rightNumber = rightNumber;
		setLeftExpressionType(expressionVertexType);
		setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			OpersExpr semanticExpression, String rightAttribute,
			String naturalLangDesc) {
		this(identifier, semanticExpressionType, expressionVertexType,
				semanticElement, semanticConElement, semanticExpression,
				rightAttribute);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement semanticConElement,
			OpersExpr semanticExpression, String rightAttribute) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.setLeftSemanticExpression(semanticExpression);
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticElement(semanticConElement);
		this.rightAttributeName = rightAttribute;
		this.setRightSemanticElement(semanticConElement);
		setLeftExpressionType(expressionVertexType);
		setRightExpressionType(ExpressionVertexType.RIGHTCONCEPTVARIABLE);
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType leftExpressionVertexType,
			ExpressionVertexType rightExpressionVertexType,
			InstElement semanticElement, InstElement semanticLeftConElement,
			InstElement semanticRightConElement, OpersExpr semanticExpression,
			String rightAttribute) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.setLeftSemanticExpression(semanticExpression);
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticElement(semanticLeftConElement);
		this.rightAttributeName = rightAttribute;
		this.setRightSemanticElement(semanticRightConElement);
		setLeftExpressionType(leftExpressionVertexType);
		setRightExpressionType(rightExpressionVertexType);
	}

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType, InstElement semanticElement,
			int number, boolean replaceTarget, OpersExpr semanticExpression) {
		this(identifier, semanticExpressionType, semanticElement, number,
				replaceTarget, semanticExpression);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			InstElement semanticElement, int number, boolean replaceTarget,
			OpersExpr semanticExpression) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setSemanticElement(semanticElement);
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

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType, InstElement semanticElement,
			InstElement leftSemanticElement, String leftAttributeName,
			String rightString) {
		this(identifier, semanticExpressionType, semanticElement,
				leftSemanticElement, leftAttributeName, rightString);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			InstElement semanticElement, InstElement leftSemanticElement,
			String leftAttributeName, String rightString) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticElement(leftSemanticElement);
		this.leftAttributeName = leftAttributeName;
		this.rightString = rightString;
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
		setRightExpressionType(ExpressionVertexType.RIGHTSTRINGVALUE);
	}

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType,
			ExpressionVertexType leftExpressionVertexType,
			InstElement semanticElement, InstElement leftSemanticElement,
			String leftAttributeName, String rightString) {
		this(identifier, semanticExpressionType, leftExpressionVertexType,
				semanticElement, leftSemanticElement, leftAttributeName,
				rightString);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
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

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			String string, boolean replaceTarget, OpersExpr semanticExpression,
			String naturalLangDesc) {
		this(identifier, semanticExpressionType, string, replaceTarget,
				semanticExpression);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			String string, boolean replaceTarget, OpersExpr semanticExpression) {
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

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement sideSemanticElement,
			String attributeName, boolean replaceTarget,
			OpersExpr semanticExpression) {
		this(identifier, semanticExpressionType, expressionVertexType,
				semanticElement, sideSemanticElement, attributeName,
				replaceTarget, semanticExpression);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType expressionVertexType,
			InstElement semanticElement, InstElement sideSemanticElement,
			String attributeName, boolean replaceTarget,
			OpersExpr semanticExpression) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setSemanticElement(semanticElement);
		if (replaceTarget) {
			this.setLeftSemanticElement(sideSemanticElement);
			this.leftAttributeName = attributeName;
			setLeftExpressionType(expressionVertexType);
			setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
			this.rightSemanticExpression = semanticExpression;
		} else {
			this.setRightSemanticElement(sideSemanticElement);
			this.rightAttributeName = attributeName;
			setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
			setRightExpressionType(expressionVertexType);
			this.leftSemanticExpression = semanticExpression;
		}
	}

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType, InstElement semanticElement,
			InstElement semanticConElement, String attributeName,
			boolean replaceTarget, OpersExpr semanticExpression) {
		this(identifier, semanticExpressionType, semanticElement,
				semanticConElement, attributeName, replaceTarget,
				semanticExpression);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			InstElement semanticElement, InstElement semanticConElement,
			String attributeName, boolean replaceTarget,
			OpersExpr semanticExpression) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setSemanticElement(semanticElement);
		if (replaceTarget) {
			this.setLeftSemanticElement(semanticConElement);
			this.leftAttributeName = attributeName;
			setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
			setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
			this.rightSemanticExpression = semanticExpression;
		} else {
			this.setRightSemanticElement(semanticConElement);
			this.rightAttributeName = attributeName;
			setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
			setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
			this.leftSemanticExpression = semanticExpression;
		}
	}

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType, InstElement semanticElement,
			OpersExpr semanticExpression, int rightNumber) {
		this(identifier, semanticExpressionType, semanticElement,
				semanticExpression, rightNumber);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			InstElement semanticElement, OpersExpr semanticExpression,
			int rightNumber) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setSemanticElement(semanticElement);
		this.leftSemanticExpression = semanticExpression;
		setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
		setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
		this.rightNumber = rightNumber;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			OpersExpr semanticExpression, String attributeName,
			String naturalLangDesc) {
		this(identifier, semanticExpressionType, semanticExpression,
				attributeName);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			OpersExpr semanticExpression, String attributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticExpression = semanticExpression;
		setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
		this.rightAttributeName = attributeName;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType leftExpressionType,
			InstElement semanticElement, InstElement leftSemanticElement,
			OpersExpr semanticExpression, InstElement rightSemanticElement,
			String rightAttribute, String naturalLangDesc) {
		this(identifier, semanticExpressionType, leftExpressionType,
				semanticElement, leftSemanticElement, semanticExpression,
				rightSemanticElement, rightAttribute);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType leftExpressionType,
			InstElement semanticElement, InstElement leftSemanticElement,
			OpersExpr semanticExpression, InstElement rightSemanticElement,
			String rightAttribute) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticExpression = semanticExpression;
		setLeftExpressionType(leftExpressionType);
		this.setLeftSemanticElement(leftSemanticElement);
		setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
		this.setRightSemanticElement(rightSemanticElement);
		this.rightAttributeName = rightAttribute;
	}

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType, InstElement semanticElement,
			OpersExpr leftSemanticExpression, OpersExpr rightSemanticExpression) {
		this(identifier, semanticExpressionType, semanticElement,
				leftSemanticExpression, rightSemanticExpression);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			InstElement semanticElement, OpersExpr leftSemanticExpression,
			OpersExpr rightSemanticExpression) {
		this.identifier = identifier;
		this.setSemanticElement(semanticElement);
		this.semanticExpressionType = semanticExpressionType;
		this.leftSemanticExpression = leftSemanticExpression;
		this.rightSemanticExpression = rightSemanticExpression;
		setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
		setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
	}

	public OpersExpr(String identifier, String naturalLangDesc,
			OpersExprType semanticExpressionType,
			InstElement leftSemanticElement, String attributeName) {
		this(identifier, semanticExpressionType, leftSemanticElement,
				attributeName);
		this.naturalLangDesc = naturalLangDesc;
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			InstElement leftSemanticElement, String attributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticElement(leftSemanticElement);
		this.leftAttributeName = attributeName;
		setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
	}

	public OpersExpr(String identifier, OpersExprType semanticExpressionType,
			ExpressionVertexType leftExpressionVertexType,
			InstElement semanticElement, InstElement leftSemanticElement,
			String attributeName) {
		this.identifier = identifier;
		this.semanticExpressionType = semanticExpressionType;
		this.setLeftSemanticElement(leftSemanticElement);
		this.leftAttributeName = attributeName;
		setLeftExpressionType(leftExpressionVertexType);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof OpersExpr) {
			OpersExpr exp = (OpersExpr) o;
			boolean elem = false;
			if (exp.getSemElemId() != null && this.getSemElemId() != null
					&& exp.getSemElemId().equals(this.getSemElemId()))
				elem = true;
			if (exp.getSemElemId() == null && this.getSemElemId() == null)
				return true;
			if (exp.getIdentifier() == null && this.getIdentifier() == null)
				return true;
			if (exp.getIdentifier() == null || this.getIdentifier() == null)
				return false;
			if (elem)
				return exp.getIdentifier().equals(this.getIdentifier());

		}
		return false;
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	public boolean isValidExpression() {
		switch (volatileLeftExpType) {
		case LEFTVARIABLE:
		case LEFTMODELVARS:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:

		case LEFTSUBITERANYVARIABLE:
		case LEFSUBTITERCONVARIABLE:
		case LEFTSUBITERINCCONVARIABLE:
		case LEFTSUBITERINCRELVARIABLE:
		case LEFTSUBITEROUTCONVARIABLE:
		case LEFTSUBITEROUTRELVARIABLE:

		case LEFTITERANYCONVARIABLE:
		case LEFTITERINCCONVARIABLE:
		case LEFTITEROUTCONVARIABLE:
		case LEFTITERCONCEPTVARIABLE:
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERANYRELVARIABLE:

		case LEFTCONCEPTVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
			if (volatileLeftSemanticElement == null)
				return false;
		case LEFTBOOLEANEXPRESSION:
		case LEFTNUMERICVALUE:
		case LEFTSTRINGVALUE:
		case LEFTSUBEXPRESSION:
		case LEFTVARIABLEVALUE:
		case RIGHTBOOLEANEXPRESSION:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTMODELVARS:
		case RIGHTNUMERICVALUE:
		case RIGHTSTRINGVALUE:
		case RIGHTSUBEXPRESSION:
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTVARIABLE:
		case RIGHTVARIABLEVALUE:
			break;
		default:
			break;
		}
		switch (volatileRightExpType) {
		case RIGHTMODELVARS:
		case RIGHTVARIABLE:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
			if (volatileRightSemanticElement == null)
				return false;
		case LEFTBOOLEANEXPRESSION:
		case LEFTCONCEPTVARIABLE:

		case LEFTITERANYCONVARIABLE:
		case LEFTITERANYRELVARIABLE:
		case LEFTITERCONCEPTVARIABLE:
		case LEFTITERINCCONVARIABLE:
		case LEFTITEROUTCONVARIABLE:
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:

		case LEFTSUBITERANYVARIABLE:
		case LEFTSUBITEROUTCONVARIABLE:
		case LEFTSUBITERINCCONVARIABLE:
		case LEFTSUBITEROUTRELVARIABLE:
		case LEFTSUBITERINCRELVARIABLE:
		case LEFSUBTITERCONVARIABLE:

		case LEFTMODELVARS:
		case LEFTNUMERICVALUE:
		case LEFTSTRINGVALUE:
		case LEFTSUBEXPRESSION:

		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:

		case LEFTVARIABLE:
		case LEFTVARIABLEVALUE:
		case RIGHTBOOLEANEXPRESSION:
		case RIGHTNUMERICVALUE:
		case RIGHTSTRINGVALUE:
		case RIGHTSUBEXPRESSION:
		case RIGHTVARIABLEVALUE:
			break;
		default:
			break;
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
		if (leftSemanticExpression != null)
			leftSemanticExpression.loadVolatileElements(instVertices);
		if (rightSemanticExpression != null)
			rightSemanticExpression.loadVolatileElements(instVertices);
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

	public void setRightNumber(float rightNumber) {
		this.rightFloatNumber = rightNumber;
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

	public void setLeftSemanticExpression(OpersExpr leftSemanticExpression) {
		this.leftSemanticExpression = leftSemanticExpression;
	}

	public void setLeftSemanticExpression(ExpressionVertexType type,
			OpersExprType semanticExpressionType, String id) {
		if (type == ExpressionVertexType.LEFTSUBEXPRESSION
				|| type == ExpressionVertexType.LEFTITERINCSUBEXP
				|| type == ExpressionVertexType.LEFTITEROUTSUBEXP
				|| type == ExpressionVertexType.LEFSUBTITERINCSUBEXP
				|| type == ExpressionVertexType.LEFTSUBITEROUTSUBEXP
				|| type == ExpressionVertexType.LEFSUBTITERCONVARIABLE
				|| type == ExpressionVertexType.LEFTSUBITERINCCONVARIABLE
				|| type == ExpressionVertexType.LEFTSUBITEROUTCONVARIABLE
				|| type == ExpressionVertexType.LEFTSUBITERINCRELVARIABLE
				|| type == ExpressionVertexType.LEFTSUBITEROUTRELVARIABLE
				|| type == ExpressionVertexType.LEFTSUBITERANYVARIABLE
				|| type == ExpressionVertexType.LEFTITERCONCEPTVARIABLE
				|| type == ExpressionVertexType.LEFTITERINCCONVARIABLE
				|| type == ExpressionVertexType.LEFTITEROUTCONVARIABLE
				|| type == ExpressionVertexType.LEFTITERINCRELVARIABLE
				|| type == ExpressionVertexType.LEFTITEROUTRELVARIABLE
				|| type == ExpressionVertexType.LEFTITERANYCONVARIABLE
				|| type == ExpressionVertexType.LEFTITERANYRELVARIABLE)
			this.leftSemanticExpression = new OpersExpr(id, volSemElement,
					semanticExpressionType);
		if (type == ExpressionVertexType.LEFTNUMERICVALUE)
			this.leftSemanticExpression = new OpersExpr(id,
					semanticExpressionType);
		setLeftExpressionType(type);
	}

	public void setRightSemanticExpression(OpersExpr rightSemanticExpression) {
		this.rightSemanticExpression = rightSemanticExpression;
	}

	public void setRightSemanticExpression(ExpressionVertexType type,
			OpersExprType semanticExpressionType, String id) {
		if (type == ExpressionVertexType.RIGHTSUBEXPRESSION)
			this.rightSemanticExpression = new OpersExpr(id, volSemElement,
					semanticExpressionType);
		if (type == ExpressionVertexType.RIGHTNUMERICVALUE)
			this.rightSemanticExpression = new OpersExpr(id,
					semanticExpressionType);
		if (type == ExpressionVertexType.RIGHTNUMERICFLOATVALUE)
			this.rightSemanticExpression = new OpersExpr(id,
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

	public void setSemanticExpressionType(OpersExprType semanticExpressionType) {
		this.semanticExpressionType = semanticExpressionType;
	}

	public OpersExprType getSemanticExpressionType() {
		return semanticExpressionType;
	}

	public InstElement getLeftSemanticElement() {
		return volatileLeftSemanticElement;
	}

	public InstElement getRightSemanticElement() {
		return volatileRightSemanticElement;
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

	public OpersExpr getLeftSemanticExpression() {
		return leftSemanticExpression;
	}

	public OpersExpr getRightSemanticExpression() {
		return rightSemanticExpression;
	}

	public int getLeftNumber() {
		return leftNumber;
	}

	public int getRightNumber() {
		return rightNumber;
	}

	public float getRightFloatNumber() {
		return rightFloatNumber;
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
		case LEFTMODELVARS:
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
			if (volatileLeftSemanticElement != null)
				return volatileLeftSemanticElement.getIdentifier();
			break;
		case RIGHTMODELVARS:
		case RIGHTVARIABLE:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
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
		case LEFTITERANYRELVARIABLE:
		case LEFTCONCEPTVARIABLE:
		case LEFTITERCONCEPTVARIABLE:
		case LEFTVARIABLE:
		case LEFTMODELVARS:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
			return volatileLeftSemanticElement;
		case RIGHTMODELVARS:
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

	public String getNaturalLangDesc() {
		return naturalLangDesc;
	}

	public void setNaturalLangDesc(String naturalLangDesc) {
		this.naturalLangDesc = naturalLangDesc;
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

		case LEFTVARIABLE:
		case LEFTCONCEPTVARIABLE:

		case LEFTMODELVARS:

		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERANYRELVARIABLE:
		case LEFTITERANYCONVARIABLE:
		case LEFTITERCONCEPTVARIABLE:
		case LEFTITERINCCONVARIABLE:
		case LEFTITEROUTCONVARIABLE:

		case LEFSUBTITERCONVARIABLE:
		case LEFTSUBITERINCCONVARIABLE:
		case LEFTSUBITEROUTCONVARIABLE:
		case LEFTSUBITERINCRELVARIABLE:
		case LEFTSUBITEROUTRELVARIABLE:
		case LEFTSUBITERANYVARIABLE:

		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
			if (volatileLeftSemanticElement != null) {
				concept = volatileLeftSemanticElement.getIdentifier();
			}
			variable = getLeftAttributeName();

			break;
		case RIGHTMODELVARS:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
			if (volatileRightSemanticElement != null) {
				concept = volatileRightSemanticElement.getIdentifier();
			}
			variable = getRightAttributeName();

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

		case LEFTMODELVARS:

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
		case RIGHTMODELVARS:
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
		case LEFTMODELVARS:
		case LEFTVARIABLE:
		case LEFTVARIABLEVALUE:
		case LEFTCONCEPTVARIABLE:

		case LEFSUBTITERCONVARIABLE:
		case LEFTSUBITERINCCONVARIABLE:
		case LEFTSUBITEROUTCONVARIABLE:
		case LEFTSUBITERINCRELVARIABLE:
		case LEFTSUBITEROUTRELVARIABLE:
		case LEFTSUBITERANYVARIABLE:

		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERINCCONVARIABLE:
		case LEFTITEROUTCONVARIABLE:
		case LEFTITERANYRELVARIABLE:
		case LEFTITERANYCONVARIABLE:
		case LEFTITERCONCEPTVARIABLE:

		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
			this.setLeftSemanticElement(intSemanticElement);
			break;
		case RIGHTMODELVARS:
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
		case LEFTMODELVARS:
		case LEFTVARIABLE:
		case LEFTCONCEPTVARIABLE:

		case LEFTSUBITERINCCONVARIABLE:
		case LEFTSUBITEROUTCONVARIABLE:
		case LEFSUBTITERCONVARIABLE:
		case LEFTSUBITERINCRELVARIABLE:
		case LEFTSUBITEROUTRELVARIABLE:
		case LEFTSUBITERANYVARIABLE:

		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERINCCONVARIABLE:
		case LEFTITEROUTCONVARIABLE:
		case LEFTITERANYRELVARIABLE:
		case LEFTITERANYCONVARIABLE:
		case LEFTITERCONCEPTVARIABLE:

		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
			this.setLeftAttributeName(attributeName);
			break;
		case RIGHTMODELVARS:
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

	public String expressionStructure() {
		String out = "";
		int i = 2;

		out += " ::" + this.getIdentifier() + ":: ";
		for (ExpressionVertexType expressionVertex : getExpressionTypes()) {
			if (expressionVertex == null) {
				out += this.getIdentifier() + ": error";
				return out;
			}
			if (getExpressionTypes().length > i--)
				out += " " + this.getOperation() + " ";
			switch (expressionVertex) {
			case LEFTVARIABLEVALUE:
				out += "leftVarValue";
				break;
			case RIGHTVARIABLEVALUE:
				out += "rightVarValue";
				break;
			case LEFTVARIABLE:
				out += this.getLeftSemanticElementId() + ":";
				out += getLeftAttributeName() + " ";
				break;
			case RIGHTVARIABLE:
				out += this.getRightSemanticElementId() + ":";
				out += getRightAttributeName() + " ";
				break;
			case LEFTSUBEXPRESSION:
				out += "(" + this.leftSemanticExpression.expressionStructure()
						+ ")";
				break;
			case RIGHTSUBEXPRESSION:
				out += "(" + rightSemanticExpression.expressionStructure()
						+ ")";
				break;
			case LEFTBOOLEANEXPRESSION:
				out += "LeftBooleanExpression" + ":";
				break;
			case LEFTCONCEPTVARIABLE:
				out += this.getLeftSemanticElementId() + ":";
				out += getLeftAttributeName() + " ";
				break;

			case LEFTITERANYCONVARIABLE:
				out += "LeftIterAnyConceptVar" + ":";
				out += getLeftAttributeName();
				break;
			case LEFTSUBITERANYVARIABLE:
				out += "LeftSubIterAnyVar" + ":";
				out += getLeftAttributeName();
				break;
			case LEFTITERANYRELVARIABLE:
				out += "LeftIterAnyRelationVar" + ":";
				out += getLeftAttributeName();
				break;

			case LEFTITERCONCEPTVARIABLE:
				out += "LeftIterConceptVar" + ":";
				if (leftSemanticExpression != null)
					out += "(" + leftSemanticExpression.expressionStructure()
							+ ")";
				break;
			case LEFSUBTITERCONVARIABLE:
				out += "LeftSubIterConceptVar" + ":";
				out += this.getLeftSemanticElementId() + ":";
				out += getLeftAttributeName();
				break;

			case LEFTSUBITERINCCONVARIABLE:
				out += "LeftSubIterIncomigConceptVar" + ":";
				out += this.getLeftSemanticElementId() + ":";
				out += getLeftAttributeName();
				break;
			case LEFTITERINCCONVARIABLE:
				out += "LeftIterIncomigConceptVar" + ":";
				if (leftSemanticExpression != null)
					out += "(" + leftSemanticExpression.expressionStructure()
							+ ")";
				break;
			case LEFTSUBITERINCRELVARIABLE:
				out += "LeftSubIterIncomigRelationVar" + ":";
				out += this.getLeftSemanticElementId() + ":";
				out += getLeftAttributeName();
				break;
			case LEFTITERINCRELVARIABLE:
				out += "LeftIterIncomigRelationVar" + ":";
				if (leftSemanticExpression != null)
					out += "(" + leftSemanticExpression.expressionStructure()
							+ ")";
				break;
			case LEFTSUBITEROUTCONVARIABLE:
				out += "LeftSubIterOutgoingConceptVar" + ":";
				out += this.getLeftSemanticElementId() + ":";
				out += getLeftAttributeName();
				break;
			case LEFTITEROUTCONVARIABLE:
				out += "LeftIterOutgoingConceptVar" + ":";
				if (leftSemanticExpression != null)
					out += "(" + leftSemanticExpression.expressionStructure()
							+ ")";
				break;
			case LEFTSUBITEROUTRELVARIABLE:
				out += "LeftSubIterOutgoingRelationVar" + ":";
				out += getLeftAttributeName();
				break;
			case LEFTITEROUTRELVARIABLE:
				out += "LeftIterOutgoingRelationVar" + ":";
				if (leftSemanticExpression != null)
					out += "(" + leftSemanticExpression.expressionStructure()
							+ ")";
				break;
			case LEFTMODELVARS:
				out += "LeftModelVars" + ":";
				out += this.getLeftSemanticElementId() + ":";
				out += getLeftAttributeName();
				break;
			case LEFTNUMERICVALUE:
				out += this.leftNumber;
				break;
			case LEFTSTRINGVALUE:
				out += "\"" + this.leftString + "\"";
				break;
			case LEFTUNIQUEINCCONVARIABLE:
				out += "LeftUniqueIncomingConceptVar" + ":";
				out += this.getLeftSemanticElementId() + ":";
				out += getLeftAttributeName();
				break;
			case LEFTUNIQUEOUTCONVARIABLE:
				out += "LeftUniqueOutgoingConceptVar" + ":";
				out += this.getLeftSemanticElementId() + ":";
				out += getLeftAttributeName();
				break;
			case LEFTUNIQUEINCRELVARIABLE:
				out += "LeftUniqueIncomingRelationtVar" + ":";
				out += this.getLeftSemanticElementId() + ":";
				out += getLeftAttributeName();
				break;
			case LEFTUNIQUEOUTRELVARIABLE:
				out += "LeftUniqueOutgoingRelationtVar" + ":";
				out += this.getLeftSemanticElementId() + ":";
				out += getLeftAttributeName();
				break;
			case RIGHTUNIQUEINCCONVARIABLE:
				out += "RightUniqueIncomingConceptVar" + ":";
				out += this.getRightSemanticElementId() + ":";
				out += getRightAttributeName();
				break;
			case RIGHTUNIQUEINCRELVARIABLE:
				out += "RightUniquIncomingRelationtVar" + ":";
				out += this.getRightSemanticElementId() + ":";
				out += getRightAttributeName();
				break;
			case RIGHTUNIQUEOUTCONVARIABLE:
				out += "RightUniqueOutgoingConceptVar" + ":";
				out += this.getRightSemanticElementId() + ":";
				out += getRightAttributeName();
				break;
			case RIGHTUNIQUEOUTRELVARIABLE:
				out += "RightUniqueOutgoingRelationtVar" + ":";
				out += this.getRightSemanticElementId() + ":";
				out += getRightAttributeName();
				break;

			case RIGHTBOOLEANEXPRESSION:
				out += "RightBooleanExpression" + ":";
				break;
			case RIGHTCONCEPTVARIABLE:
				out += this.getRightSemanticElementId() + ":";
				out += getRightAttributeName();
				break;
			case RIGHTMODELVARS:
				out += "RightModelVars" + ":";

				out += this.getRightSemanticElementId() + ":";
				out += getRightAttributeName();
				break;
			case RIGHTNUMERICVALUE:
				out += rightNumber;
				break;
			case RIGHTSTRINGVALUE:
				out += "\"" + rightString + "\"";
				break;
			case LEFSUBTITERINCSUBEXP:
				out += "(" + leftSemanticExpression.expressionStructure() + ")";
				break;
			default:
				out += expressionVertex.toString();
			}
		}
		return out;
	}
}
