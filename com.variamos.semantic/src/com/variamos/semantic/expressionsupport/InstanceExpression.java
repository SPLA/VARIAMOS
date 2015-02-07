package com.variamos.semantic.expressionsupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.hlcl.Expression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.Identifier;
import com.variamos.semantic.types.ExpressionVertexType;
import com.variamos.syntax.instancesupport.InstElement;
import com.variamos.syntax.instancesupport.InstVertex;
import com.variamos.syntax.metamodelsupport.AbstractAttribute;

/**
 * A class to represent InstanceExpressions. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
public class InstanceExpression implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296982198124042304L;
	private static HlclFactory hlclFactory = new HlclFactory();
	private MetaExpression volatileMetaExpression;

	private MetaExpression customMetaExpression;
	private InstElement leftElement;
	private InstElement rightElement;
	private InstanceExpression leftSubExpression;
	private InstanceExpression rightSubExpression;
	private String leftValue;
	private String rightValue;
	private String lastLeft = null;
	private String lastRight = null;

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

	private int number;
	private boolean customExpression;
	private String metaExpressionId;

	public InstanceExpression() {
		customExpression = false;
	}

	public InstanceExpression(boolean customExpression, String id) {
		this.customExpression = customExpression;
		if (customExpression) {
			customMetaExpression = new MetaExpression(id);
			metaExpressionId = id;
		}

	}

	public InstanceExpression(boolean customExpression,
			MetaExpression metaExpression) {
		this.customExpression = customExpression;
		if (customExpression) {
			customMetaExpression = metaExpression;
			metaExpressionId = metaExpression.getIdentifier();
		}
	}

	public InstanceExpression(MetaExpression metaExpression, InstElement left,
			InstElement right) {
		this.volatileMetaExpression = metaExpression;
		this.metaExpressionId = metaExpression.getIdentifier();
		this.leftElement = left;
		this.rightElement = right;
	}

	public InstanceExpression(MetaExpression metaExpression,
			InstElement vertex, boolean replaceTarget,
			InstanceExpression subExpression) {
		this.volatileMetaExpression = metaExpression;
		this.metaExpressionId = metaExpression.getIdentifier();
		if (replaceTarget) {
			this.leftElement = vertex;
			this.rightSubExpression = subExpression;
		} else {
			this.rightElement = vertex;
			this.leftSubExpression = subExpression;
		}
	}

	public InstanceExpression(MetaExpression metaExpression,
			InstanceExpression leftSubExpression,
			InstanceExpression rightSubExpression) {
		this.volatileMetaExpression = metaExpression;
		this.metaExpressionId = metaExpression.getIdentifier();
		this.leftSubExpression = leftSubExpression;
		this.rightSubExpression = rightSubExpression;
	}

	public InstanceExpression(MetaExpression metaExpression, InstElement vertex) {
		this.volatileMetaExpression = metaExpression;
		this.metaExpressionId = metaExpression.getIdentifier();
		this.leftElement = vertex;
	}

	public Expression createExpression() {
		Map<String, Identifier> idMap = getIdentifiers();
		List<Expression> expressionTerms = expressionTerms();
		// return hlclFactory.greaterOrEqualsThan(
		// (NumericExpression) expressionTerms.get(0),
		// (NumericExpression) expressionTerms.get(1));
		return null;
	}

	private Map<String, Identifier> getIdentifiers() {
		Map<String, Identifier> out = new HashMap<String, Identifier>();
		if (leftElement != null) {
			// System.out.println(leftVertex.getIdentifier() + " "
			// + leftAttributeName);
			Identifier identifier = hlclFactory.newIdentifier(leftElement
					.getInstAttributeFullIdentifier(volatileMetaExpression
							.getLeftAttributeName()), volatileMetaExpression
					.getLeftAttributeName());
			out.put(leftElement
					.getInstAttributeFullIdentifier(volatileMetaExpression
							.getLeftAttributeName()), identifier);
			AbstractAttribute attribute = leftElement.getInstAttribute(
					volatileMetaExpression.getLeftAttributeName())
					.getAttribute();
			if (attribute.getType().equals("Integer")) {
				if (attribute.getDomain() != null)
					identifier.setDomain(attribute.getDomain());
			}
		}
		if (rightElement != null) {
			// System.out
			// .println(rightVertex.getIdentifier() + rightAttributeName);
			Identifier identifier = hlclFactory.newIdentifier(rightElement
					.getInstAttributeFullIdentifier(volatileMetaExpression
							.getRightAttributeName()), volatileMetaExpression
					.getRightAttributeName());

			out.put(rightElement
					.getInstAttributeFullIdentifier(volatileMetaExpression
							.getRightAttributeName()), identifier);
			AbstractAttribute attribute = rightElement.getInstAttribute(
					volatileMetaExpression.getRightAttributeName())
					.getAttribute();
			if (attribute.getType().equals("Integer")) {
				if (attribute.getDomain() != null)
					identifier.setDomain(attribute.getDomain());
			}

		}
		if (leftSubExpression != null) {
			out.putAll(leftSubExpression.getIdentifiers());
		}
		if (rightSubExpression != null) {
			out.putAll(rightSubExpression.getIdentifiers());
		}
		return out;
	}

	private List<Expression> expressionTerms() {
		Map<String, Identifier> idMap = getIdentifiers();
		List<Expression> out = new ArrayList<Expression>();

		List<ExpressionVertexType> expressionVertexTypes = new ArrayList<ExpressionVertexType>();

		ExpressionVertexType left = getMetaExpression().getLeftExpressionType();
		ExpressionVertexType right = getMetaExpression()
				.getRightExpressionType();
		if (left != null)
			expressionVertexTypes.add(left);
		if (right != null)
			expressionVertexTypes.add(right);

		for (ExpressionVertexType expressionType : expressionVertexTypes) {
			switch (expressionType) {
			case LEFT:
				out.add(idMap.get(leftElement
						.getInstAttributeFullIdentifier(volatileMetaExpression
								.getLeftAttributeName())));
				break;
			case RIGHT:
				out.add(idMap.get(rightElement
						.getInstAttributeFullIdentifier(volatileMetaExpression
								.getRightAttributeName())));

				break;
			case LEFTSUBEXPRESSION:
				out.add(leftSubExpression.createExpression());
				break;
			case RIGHTSUBEXPRESSION:
				out.add(rightSubExpression.createExpression());
				break;
			default:
				break;

			}
		}

		return out;
	}

	public InstElement getLeftElement() {
		return leftElement;
	}

	public void setLeftElement(InstElement leftElement) {
		this.leftElement = leftElement;
	}

	public InstElement getRightElement() {
		return rightElement;
	}

	public void setRightElement(InstElement rightElement) {
		this.rightElement = rightElement;
	}

	public InstanceExpression getLeftSubExpression() {
		return leftSubExpression;
	}

	public void setLeftSubExpression(InstanceExpression leftSubExpression) {
		this.leftSubExpression = leftSubExpression;
	}

	public InstanceExpression getRightSubExpression() {
		return rightSubExpression;
	}

	public void setRightSubExpression(InstanceExpression rightSubExpression) {
		this.rightSubExpression = rightSubExpression;
	}

	public void setLeftSubExpression(ExpressionVertexType type,
			MetaExpressionType metaExpressionType, String id) {
		if (type == ExpressionVertexType.LEFTSUBEXPRESSION)
			this.leftSubExpression = new InstanceExpression(true, id);
		if (type == ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE)
			this.leftSubExpression = new InstanceExpression(true,
					new MetaExpression(id, metaExpressionType));
		getMetaExpression().setLeftExpressionType(type);
	}

	public void setLeftElement(InstElement instElement, String attribute) {
		getMetaExpression().setLeftExpressionType(ExpressionVertexType.LEFT);
		this.leftElement = instElement;
		getMetaExpression().setLeftAttributeName(attribute);
	}

	public void setRightSubExpression(ExpressionVertexType type,
			MetaExpressionType metaExpressionType, String id) {
		if (type == ExpressionVertexType.RIGHTSUBEXPRESSION)
			this.rightSubExpression = new InstanceExpression(true, id);
		if (type == ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE)
			this.rightSubExpression = new InstanceExpression(true,
					new MetaExpression(id, metaExpressionType));
		getMetaExpression().setRightExpressionType(type);
	}

	public void setRightElement(InstElement instElement, String attribute) {
		getMetaExpression().setRightExpressionType(ExpressionVertexType.RIGHT);
		this.leftElement = instElement;
		getMetaExpression().setRightAttributeName(attribute);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setMetaExpressionType(MetaExpressionType metaExpressionType) {
		if (customExpression)
			customMetaExpression.setMetaExpressionType(metaExpressionType);
		if (metaExpressionType == null
				|| metaExpressionType.isSingleInExpression()) {
			rightSubExpression = null;
			rightElement = null;
			getMetaExpression().setRightExpressionType(null);
		}
	}

	public int getLeftValidExpressions() {
		return getMetaExpression().getLeftValidExpressions();
	}

	public int getRightValidExpressions() {
		return getMetaExpression().getRightValidExpressions();
	}

	public int getResultExpressions() {
		return getMetaExpression().getResultExpressions();
	}

	public MetaExpression getMetaExpression() {
		if (customExpression)
			return customMetaExpression;
		return volatileMetaExpression;
	}

	public void setMetaExpression(MetaExpression metaExpression) {
		if (customExpression)
			this.customMetaExpression = metaExpression;
		else
			this.volatileMetaExpression = metaExpression;
	}

	// Required for graph serialization
	public MetaExpression getCustomMetaExpression() {
		return customMetaExpression;
	}

	// Required for graph serialization
	public void setCustomMetaExpression(MetaExpression customMetaExpression) {
		this.customMetaExpression = customMetaExpression;
	}

	public boolean isCustomExpression() {
		return customExpression;
	}

	public void setCustomExpression(boolean customExpression) {
		this.customExpression = customExpression;
	}

	public String getMetaExpressionId() {
		return metaExpressionId;
	}

	public void setMetaExpressionId(String metaExpressionId) {
		this.metaExpressionId = metaExpressionId;
	}

	public String getLeftAttributeName() {
		return getMetaExpression().getLeftAttributeName();
	}

	public String getRightAttributeName() {
		return getMetaExpression().getRightAttributeName();
	}

	public void setLeftAttributeName(String attribute) {
		getMetaExpression().setLeftAttributeName(attribute);
	}

	public void setRightAttributeName(String attribute) {
		getMetaExpression().setRightAttributeName(attribute);
	}

	public String getOperation() {
		return getMetaExpression().getOperation();
	}

	public ExpressionVertexType getLeftExpressionType() {
		return getMetaExpression().getLeftExpressionType();
	}

	public ExpressionVertexType getRightExpressionType() {
		return getMetaExpression().getRightExpressionType();
	}

	public void setLeftExpressionType(ExpressionVertexType expressionVertexType) {
		getMetaExpression().setLeftExpressionType(expressionVertexType);
	}

	public void setRightExpressionType(ExpressionVertexType expressionVertexType) {
		getMetaExpression().setRightExpressionType(expressionVertexType);
	}

	public String getElementIdentifier(ExpressionVertexType expressionVertexType) {
		if (expressionVertexType == ExpressionVertexType.LEFT)
			if (getLeftElement() != null)
				return getLeftElement().getIdentifier();
			else if (expressionVertexType == ExpressionVertexType.RIGHT)
				if (getRightElement() != null)
					return getRightElement().getIdentifier();
		return null;
	}

	public String getElementAttributeIdentifier(
			ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFT:
			if (getLeftElement() != null)
				return getLeftElement().getIdentifier() + "_"
						+ getLeftAttributeName();
			break;
		case RIGHT:
			if (getRightElement() != null)
				return getRightElement().getIdentifier() + "_"
						+ getRightAttributeName();
			break;
		case LEFTVARIABLEVALUE:
			if (getLeftElement() != null)
				return getLeftElement().getIdentifier() + "_" + leftValue;
			break;
		case RIGHTVARIABLEVALUE:
			if (getRightElement() != null)
				return getRightElement().getIdentifier() + "_" + rightValue;
			break;
		default:
			return null;
		}
		return null;
	}

	public void setElement(InstVertex vertex,
			ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFT:
		case LEFTVARIABLEVALUE:
			this.setLeftElement(vertex);
			break;
		case RIGHT:
		case RIGHTVARIABLEVALUE:
			this.setRightElement(vertex);
			default:
		}
	}

	public void setAttributeName(String attributeName,
			ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFT:
			this.setLeftAttributeName(attributeName);
			break;
		case RIGHT:
			this.setRightAttributeName(attributeName);
			break;
		case LEFTVARIABLEVALUE:
			this.leftValue = attributeName;
			break;
		case RIGHTVARIABLEVALUE:
			this.rightValue = attributeName;
		default:
		}
	}

	public boolean isSingleInExpression() {
		return getMetaExpression().isSingleInExpression();
	}

	public String toString() {
		return "";//expressionStructure();
	}
	public String expressionStructure() {
		String out = "";
		int i = 0;
		for (ExpressionVertexType expressionVertex : getMetaExpression().getExpressionTypes()) {
			//if (expressionConnectors.size() > i)
				//out += " " + expressionConnectors.get(i) + " ";
			switch (expressionVertex) {
			case LEFTVARIABLEVALUE:
				out += leftValue;
				break;
			case RIGHTVARIABLEVALUE:
				out += rightValue;
				break;
			case LEFT:
				out += getLeftAttributeName();
				break;
			case RIGHT:
				out += getRightAttributeName();

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
}
