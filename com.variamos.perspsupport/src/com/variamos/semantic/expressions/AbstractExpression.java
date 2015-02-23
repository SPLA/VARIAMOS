package com.variamos.semantic.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.NumericExpression;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.types.ExpressionVertexType;

/**
 * Abstract root Class to group at the Transformation functionality. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public abstract class AbstractExpression {
	private InstElement leftVertex;
	private InstElement rightVertex;
	private String leftAttributeName;
	private String rightAttributeName;
	private AbstractExpression leftSubExpression;
	private AbstractExpression rightSubExpression;
	private BooleanExpression leftBooleanExpression;
	private BooleanExpression rightBooleanExpression;
	private NumericExpression leftNumericExpression;
	private NumericExpression rightNumericExpression;
	protected String operation;

	protected List<ExpressionVertexType> expressionVertexTypes;
	protected List<String> expressionConnectors;

	public AbstractExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();

		this.leftVertex = left;
		this.rightVertex = right;
		this.leftAttributeName = leftAttributeName;
		this.rightAttributeName = rightAttributeName;

		this.expressionVertexTypes.add(ExpressionVertexType.LEFT);
		this.expressionVertexTypes.add(ExpressionVertexType.RIGHT);
	}

	public AbstractExpression(InstElement vertex, String attributeName,
			boolean replaceTarget, AbstractExpression subExpression) {
		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();

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

	public AbstractExpression(InstElement vertex, String attributeName,
			boolean replaceTarget, BooleanExpression booleanExpression) {
		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();

		if (replaceTarget) {
			this.leftVertex = vertex;
			this.leftAttributeName = attributeName;
			this.expressionVertexTypes.add(ExpressionVertexType.LEFT);
			this.expressionVertexTypes
					.add(ExpressionVertexType.RIGHTBOOLEANEXPRESSIONVALUE);
			this.rightBooleanExpression = booleanExpression;
		} else {
			this.rightVertex = vertex;
			this.rightAttributeName = attributeName;
			this.expressionVertexTypes
					.add(ExpressionVertexType.LEFTBOOLEANEXPRESSIONVALUE);
			this.expressionVertexTypes.add(ExpressionVertexType.RIGHT);
			this.leftBooleanExpression = booleanExpression;
		}
	}

	public AbstractExpression(InstElement vertex, String attributeName,
			boolean replaceTarget, NumericExpression numericExpression) {
		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();

		if (replaceTarget) {
			this.leftVertex = vertex;
			this.leftAttributeName = attributeName;
			this.expressionVertexTypes.add(ExpressionVertexType.LEFT);
			this.expressionVertexTypes
					.add(ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE);
			this.rightNumericExpression = numericExpression;
		} else {
			this.rightVertex = vertex;
			this.rightAttributeName = attributeName;
			this.expressionVertexTypes
					.add(ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE);
			this.expressionVertexTypes.add(ExpressionVertexType.RIGHT);
			this.leftNumericExpression = numericExpression;
		}
	}

	public AbstractExpression(AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();

		this.leftSubExpression = leftSubExpression;
		this.rightSubExpression = rightSubExpression;
		this.expressionVertexTypes.add(ExpressionVertexType.LEFTSUBEXPRESSION);
		this.expressionVertexTypes.add(ExpressionVertexType.RIGHTSUBEXPRESSION);
	}

	public AbstractExpression() {
		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();
	}

	public AbstractExpression(InstElement vertex, String attributeName) {
		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();
		
		this.leftVertex = vertex;
		this.leftAttributeName = attributeName;
		this.expressionVertexTypes.add(ExpressionVertexType.LEFT);
	}

	public Map<String, Identifier> getIdentifiers(HlclFactory f) {
		Map<String, Identifier> out = new HashMap<String, Identifier>();
		if (leftVertex != null) {
		//	System.out.println(leftVertex.getIdentifier() + " "
		//			+ leftAttributeName);
			Identifier identifier = f
					.newIdentifier(leftVertex
							.getInstAttributeFullIdentifier(leftAttributeName),
							leftAttributeName);
			out.put(leftVertex
					.getInstAttributeFullIdentifier(leftAttributeName), identifier);
			AbstractAttribute attribute = leftVertex.getInstAttribute(leftAttributeName).getAttribute();
			if(attribute.getType().equals("Integer"))
			{
				if (attribute.getDomain() != null)
					identifier.setDomain(attribute.getDomain());
			}
		}
		if (rightVertex != null) {
		//	System.out
		//			.println(rightVertex.getIdentifier() + rightAttributeName);
			Identifier identifier= f.newIdentifier(
					rightVertex
					.getInstAttributeFullIdentifier(rightAttributeName),
			rightAttributeName);
		
			out.put(rightVertex
					.getInstAttributeFullIdentifier(rightAttributeName), identifier
					);
			AbstractAttribute attribute = rightVertex.getInstAttribute(rightAttributeName).getAttribute();
			if(attribute.getType().equals("Integer"))
			{
				if (attribute.getDomain() != null)
					identifier.setDomain(attribute.getDomain());
			}
				
		}
		if (leftSubExpression != null) {
			out.putAll(leftSubExpression.getIdentifiers(f));
		}
		if (rightSubExpression != null) {
			out.putAll(rightSubExpression.getIdentifiers(f));
		}
		return out;
	}

	public InstElement getLeft() {
		return leftVertex;
	}

	public InstElement getRight() {
		return rightVertex;
	}

	public InstElement getLeftVertex() {
		return leftVertex;
	}

	public InstElement getRightVertex() {
		return rightVertex;
	}

	public String getLeftAttributeName() {
		return leftAttributeName;
	}

	public String getRightAttributeName() {
		return rightAttributeName;
	}

	public AbstractExpression getLeftSubExpression() {
		return leftSubExpression;
	}

	public AbstractExpression getRightSubExpression() {
		return rightSubExpression;
	}

	public Expression getLeftComparativeExpression() {
		return leftBooleanExpression;
	}

	public Expression getRightComparativeExpression() {
		return rightBooleanExpression;
	}

	public List<ExpressionVertexType> getExpressionVertexTypes() {
		return expressionVertexTypes;
	}

	public List<String> getExpressionConnectors() {
		return expressionConnectors;
	}

	public BooleanExpression getLeftBooleanExpression() {
		return leftBooleanExpression;
	}

	public BooleanExpression getRightBooleanExpression() {
		return rightBooleanExpression;
	}

	public NumericExpression getLeftNumericExpression() {
		return leftNumericExpression;
	}

	public NumericExpression getRightNumericExpression() {
		return rightNumericExpression;
	}

	public String getOperation() {
		return operation;
	}

	public String expressionStructure() {
		String out = "";
		int i = 0;
		for (ExpressionVertexType expressionVertex : expressionVertexTypes) {
			if (expressionConnectors.size() > i)
				out += " " + expressionConnectors.get(i) + " ";
			switch (expressionVertex) {
			case LEFTBOOLEANEXPRESSIONVALUE:
				out += leftBooleanExpression;
				break;
			case RIGHTBOOLEANEXPRESSIONVALUE:
				out += rightBooleanExpression;
				break;
			case LEFT:
				out += leftAttributeName;
				break;
			case RIGHT:
				out += rightAttributeName;

				break;
			case LEFTSUBEXPRESSION:
				out += "(" + leftSubExpression.expressionStructure() + ")";
				break;
			case RIGHTSUBEXPRESSION:
				out += "(" + rightSubExpression.expressionStructure() + ")";
				break;
			default:
				break;

			}
			i++;
		}
		return out;
	}

	protected List<Expression> expressionTerms(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<Expression> out = new ArrayList<Expression>();

		for (ExpressionVertexType expressionType : expressionVertexTypes) {
			switch (expressionType) {
			case LEFTBOOLEANEXPRESSIONVALUE:
				out.add(leftBooleanExpression);
				break;
			case RIGHTBOOLEANEXPRESSIONVALUE:
				out.add(rightBooleanExpression);
				break;
			case LEFTNUMERICEXPRESSIONVALUE:
				out.add(leftNumericExpression);
				break;
			case RIGHTNUMERICEXPRESSIONVALUE:
				out.add(rightNumericExpression);
				break;
			case LEFT:
				out.add(idMap.get(getLeft().getInstAttributeFullIdentifier(
						leftAttributeName)));
				break;
			case RIGHT:
				out.add(idMap.get(getRight().getInstAttributeFullIdentifier(
						rightAttributeName)));

				break;
			case LEFTSUBEXPRESSION:
				if (leftSubExpression instanceof AbstractBooleanExpression)
					out.add(( leftSubExpression)
							.transform(f, idMap));
				else if (leftSubExpression instanceof AbstractNumericExpression)
					out.add(((AbstractNumericExpression) leftSubExpression)
							.transform(f, idMap));
				else
					out.add(((AbstractComparisonExpression) leftSubExpression)
							.transform(f, idMap));
				break;
			case RIGHTSUBEXPRESSION:
				if (rightSubExpression instanceof AbstractBooleanExpression)
					out.add(((AbstractBooleanExpression) rightSubExpression)
							.transform(f, idMap));
				else if (rightSubExpression instanceof AbstractNumericExpression)
					out.add(((AbstractNumericExpression) rightSubExpression)
							.transform(f, idMap));
				else
					out.add(((AbstractComparisonExpression) rightSubExpression)
							.transform(f, idMap));
				break;
			default:
				break;

			}
		}

		return out;
	}

	protected List<Expression> expressionTermsNegation(HlclFactory f,
			Map<String, Identifier> idMap, boolean negateLeft,
			boolean negateRight) {
		List<Expression> out = new ArrayList<Expression>();

		for (ExpressionVertexType expressionType : expressionVertexTypes) {
			switch (expressionType) {
			case LEFTBOOLEANEXPRESSIONVALUE:
				if (negateLeft)
					out.add(f.not(leftBooleanExpression));
				else
					out.add(leftBooleanExpression);
				break;
			case RIGHTBOOLEANEXPRESSIONVALUE:
				out.add(rightBooleanExpression);
				break;
			case LEFTNUMERICEXPRESSIONVALUE:
				out.add(leftNumericExpression);
				break;
			case RIGHTNUMERICEXPRESSIONVALUE:
				out.add(rightNumericExpression);
				break;
			case LEFT:
				if (negateLeft)
					out.add(f.not(idMap.get(getLeft()
							.getInstAttributeFullIdentifier(leftAttributeName))));
				else
					out.add(idMap.get(getLeft().getInstAttributeFullIdentifier(
							leftAttributeName)));
				break;
			case RIGHT:
				if (negateRight)
					out.add(f.not(idMap
							.get(getRight().getInstAttributeFullIdentifier(
									rightAttributeName))));
				else
					out.add(idMap
							.get(getRight().getInstAttributeFullIdentifier(
									rightAttributeName)));
				break;
			case LEFTSUBEXPRESSION:
				if (negateLeft
						&& leftSubExpression instanceof AbstractBooleanExpression) 
					// TODO special case for assign
					out.add(((AbstractBooleanExpression) leftSubExpression)
							.transformNegation(f, idMap, false, false));
				else
					out.add(leftSubExpression.transform(f, idMap));
				break;
			case RIGHTSUBEXPRESSION:
				if (negateRight
						&& rightSubExpression instanceof AbstractBooleanExpression)
					// TODO special case for assign
					out.add(((AbstractBooleanExpression) rightSubExpression)
							.transformNegation(f, idMap, false, false));
				else

					out.add(rightSubExpression.transform(f, idMap));
				break;
			default:
				break;

			}
		}

		return out;
	}

	public abstract Expression transform(HlclFactory hlclFactory,
			Map<String, Identifier> idMap);
}
