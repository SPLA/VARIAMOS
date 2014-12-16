package com.variamos.refas.core.simulationmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.variamos.refas.core.types.ExpressionVertexType;
import com.variamos.syntaxsupport.metamodel.InstVertex;

/**
 * Abstract root Class to group at the Transformation functionality. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public abstract class AbstractTransformation {
	private InstVertex leftVertex;
	private InstVertex rightVertex;
	private String leftAttributeName;
	private String rightAttributeName;
	private AbstractTransformation leftSubExpression;
	private AbstractTransformation rightSubExpression;
	private Expression leftComparativeExpression;
	private Expression rightComparativeExpression;

	protected List<ExpressionVertexType> expressionVertexTypes;
	protected List<String> expressionConnectors;

	public AbstractTransformation(InstVertex left, InstVertex right,
			String leftAttributeName, String rightAttributeName) {

		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();

		this.leftVertex = left;
		this.rightVertex = right;
		this.leftAttributeName = leftAttributeName;
		this.rightAttributeName = rightAttributeName;

		this.expressionVertexTypes.add(ExpressionVertexType.left);
		this.expressionVertexTypes.add(ExpressionVertexType.right);
	}

	public AbstractTransformation(InstVertex vertex, String attributeName,
			boolean replaceTarget, AbstractTransformation subExpression) {
		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();

		if (replaceTarget) {
			this.leftVertex = vertex;
			this.leftAttributeName = attributeName;
			this.expressionVertexTypes.add(ExpressionVertexType.left);
			this.expressionVertexTypes
					.add(ExpressionVertexType.rightSubexpression);
			this.rightSubExpression = subExpression;
		} else {
			this.rightVertex = vertex;
			this.rightAttributeName = attributeName;
			this.expressionVertexTypes
					.add(ExpressionVertexType.leftSubexpression);
			this.expressionVertexTypes.add(ExpressionVertexType.right);
			this.leftSubExpression = subExpression;
		}

	}

	public AbstractTransformation(InstVertex vertex, String attributeName,
			boolean replaceTarget, Expression comparativeExpression) {

		this.leftComparativeExpression = comparativeExpression;
		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();

		if (replaceTarget) {
			this.leftVertex = vertex;
			this.leftAttributeName = attributeName;
			this.expressionVertexTypes.add(ExpressionVertexType.left);
			this.expressionVertexTypes
					.add(ExpressionVertexType.rightComparativeExpressionValue);
			this.rightComparativeExpression = comparativeExpression;
		} else {
			this.rightVertex = vertex;
			this.rightAttributeName = attributeName;
			this.expressionVertexTypes
					.add(ExpressionVertexType.leftComparativeExpressionValue);
			this.expressionVertexTypes.add(ExpressionVertexType.right);
			this.leftComparativeExpression = comparativeExpression;
		}
	}

	public AbstractTransformation(AbstractTransformation leftSubExpression,
			AbstractTransformation rightSubExpression) {
		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();

		this.leftSubExpression = leftSubExpression;
		this.rightSubExpression = rightSubExpression;
		this.expressionVertexTypes.add(ExpressionVertexType.leftSubexpression);
		this.expressionVertexTypes.add(ExpressionVertexType.rightSubexpression);
	}

	public Map<String, Identifier> getIndentifiers(HlclFactory f) {
		Map<String, Identifier> out = new HashMap<String, Identifier>();
		if (leftVertex != null) {
			out.put(leftVertex
					.getInstAttributeFullIdentifier(leftAttributeName), f
					.newIdentifier(leftVertex
							.getInstAttributeFullIdentifier(leftAttributeName),
							leftAttributeName));
		}
		if (rightVertex != null) {
			out.put(rightVertex
					.getInstAttributeFullIdentifier(rightAttributeName), f
					.newIdentifier(rightVertex
							.getInstAttributeFullIdentifier(rightAttributeName),
							rightAttributeName));
		}
		if (leftSubExpression != null) {
			out.putAll(leftSubExpression.getIndentifiers(f));
		}
		if (rightSubExpression != null) {
			out.putAll(rightSubExpression.getIndentifiers(f));
		}
		return out;
	}

	public AbstractTransformation() {
		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();
	}

	public AbstractTransformation(InstVertex vertex, String attributeName) {
		expressionVertexTypes = new ArrayList<ExpressionVertexType>();
		expressionConnectors = new ArrayList<String>();
		this.leftVertex = vertex;
		this.leftAttributeName = attributeName;
		this.expressionVertexTypes.add(ExpressionVertexType.left);
	}

	public InstVertex getLeft() {
		return leftVertex;
	}

	public InstVertex getRight() {
		return rightVertex;
	}

	public InstVertex getLeftVertex() {
		return leftVertex;
	}

	public InstVertex getRightVertex() {
		return rightVertex;
	}

	public String getLeftAttributeName() {
		return leftAttributeName;
	}

	public String getRightAttributeName() {
		return rightAttributeName;
	}

	public AbstractTransformation getLeftSubExpression() {
		return leftSubExpression;
	}

	public AbstractTransformation getRightSubExpression() {
		return rightSubExpression;
	}

	public Expression getLeftComparativeExpression() {
		return leftComparativeExpression;
	}

	public Expression getRightComparativeExpression() {
		return rightComparativeExpression;
	}

	public List<ExpressionVertexType> getExpressionVertexTypes() {
		return expressionVertexTypes;
	}

	public List<String> getExpressionConnectors() {
		return expressionConnectors;
	}

	public String expressionStructure() {
		String out = "";
		int i = 0;
		for (ExpressionVertexType expressionVertex : expressionVertexTypes) {
			if (expressionConnectors.size() > i)
				out += " " + expressionConnectors.get(i) + " ";
			switch (expressionVertex) {
			case leftComparativeExpressionValue:
				out += leftComparativeExpression;
				break;
			case rightComparativeExpressionValue:
				out += rightComparativeExpression;
				break;
			case left:
				out += leftAttributeName;
				break;
			case right:
				out += rightAttributeName;

				break;
			case leftSubexpression:
				out += "(" + leftSubExpression.expressionStructure() + ")";
				break;
			case rightSubexpression:
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
			case leftComparativeExpressionValue:
				out.add(leftComparativeExpression);
				break;
			case rightComparativeExpressionValue:
				out.add(rightComparativeExpression);
				break;
			case left:
				out.add(idMap.get(getLeft().getInstAttributeFullIdentifier(
						leftAttributeName)));
				break;
			case right:
				out.add(idMap.get(getRight().getInstAttributeFullIdentifier(
						rightAttributeName)));

				break;
			case leftSubexpression:
				if (leftSubExpression instanceof AbstractBooleanTransformation)
				out.add(((AbstractBooleanTransformation)leftSubExpression).transform(f, idMap));
				else
					out.add(((AbstractNumericTransformation)leftSubExpression).transform(f, idMap));	
				break;
			case rightSubexpression:
				if (rightSubExpression instanceof AbstractBooleanTransformation)
					out.add(((AbstractBooleanTransformation)rightSubExpression).transform(f, idMap));
					else
						out.add(((AbstractNumericTransformation)rightSubExpression).transform(f, idMap));	
				break;
			default:
				break;

			}
		}

		return out;
	}
}
