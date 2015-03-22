package com.variamos.perspsupport.expressionsupport;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.DomainParser;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.RangeDomain;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstEnumeration;
import com.variamos.perspsupport.semanticinterface.IntInstanceExpression;
import com.variamos.perspsupport.semanticsupport.SemanticVariable;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.syntaxsupport.MetaEnumeration;
import com.variamos.perspsupport.types.ExpressionVertexType;

/**
 * A class to represent InstanceExpressions. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
/**
 * @author jcmunoz
 *
 */
public class InstanceExpression implements Serializable, IntInstanceExpression {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296982198124042304L;
	private static HlclFactory hlclFactory = new HlclFactory();

	/**
	 * Associated SemanticExpression: volatile for semantic SemanticExpression;
	 * custom for variable expressions or InstElement custom expressions
	 */
	private SemanticExpression volatileSemanticExpression;
	private SemanticExpression customSemanticExpression;

	/**
	 * for LEFT
	 */
	private InstElement volatileLefInstElement;

	/**
	 * for RIGHT
	 */
	private InstElement volatileRightInstElement;

	/**
	 * for LEFTSUBEXPRESSION
	 */
	private InstanceExpression leftInstanceExpression;
	/**
	 * for RIGHTSUBEXPRESSION
	 */
	private InstanceExpression rightInstanceExpression;

	// TODO change to a new class for type (normal, relax)
	private List<ExpressionSubAction> SemExprSubActions;

	/**
	 * For LEFTVARIABLEVALUE
	 */
	private String leftValue;

	/**
	 * For RIGHTVARIABLEVALUE
	 */
	private String rightValue;

	private String lastLeft = null;
	private String lastRight = null;

	private boolean customExpression;
	private String semanticExpressionId;

	private String rightInstElementId;
	private String leftInstElementId;

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

	public InstanceExpression() {
		customExpression = false;
	}

	public void loadVolatileElements(Map<String, InstElement> instVertices) {
		if (leftInstElementId != null)
			volatileLefInstElement = instVertices.get(leftInstElementId);
		if (rightInstElementId != null)
			volatileRightInstElement = instVertices.get(rightInstElementId);
		if (leftInstanceExpression != null)
			leftInstanceExpression.loadVolatileElements(instVertices);
		if (rightInstanceExpression != null)
			rightInstanceExpression.loadVolatileElements(instVertices);
	}

	public InstanceExpression(boolean customExpression, String id, boolean first) {
		this.customExpression = customExpression;
		if (customExpression) {
			customSemanticExpression = new SemanticExpression(id);
			semanticExpressionId = id;
		}
		if (first) {
			setLeftInstanceExpression(ExpressionVertexType.LEFTSUBEXPRESSION,
					null, "id");
			setRightInstanceExpression(ExpressionVertexType.RIGHTSUBEXPRESSION,
					null, "id");
		}
		this.setLeftExpressionType(ExpressionVertexType.LEFT);
		this.setRightExpressionType(ExpressionVertexType.RIGHT);
	}

	public InstanceExpression(boolean customExpression,
			SemanticExpression semanticExpression) {
		this.customExpression = customExpression;
		if (customExpression) {
			customSemanticExpression = semanticExpression;
			semanticExpressionId = semanticExpression.getIdentifier();
		}
	}

	public InstanceExpression(SemanticExpression semanticExpression,
			InstElement left, InstElement right) {
		this.volatileSemanticExpression = semanticExpression;
		this.semanticExpressionId = semanticExpression.getIdentifier();
		setLeftElement(left);
		setRightElement(right);
	}

	public InstanceExpression(SemanticExpression semanticExpression,
			InstElement vertex, boolean replaceTarget,
			InstanceExpression instanceExpression) {
		this.volatileSemanticExpression = semanticExpression;
		this.semanticExpressionId = semanticExpression.getIdentifier();
		if (replaceTarget) {
			setLeftElement(vertex);
			this.rightInstanceExpression = instanceExpression;
		} else {
			setRightElement(vertex);
			this.leftInstanceExpression = instanceExpression;
		}
	}

	public InstanceExpression(SemanticExpression semanticExpression,
			InstanceExpression leftInstanceExpression,
			InstanceExpression rightInstanceExpression) {
		this.volatileSemanticExpression = semanticExpression;
		this.semanticExpressionId = semanticExpression.getIdentifier();
		this.leftInstanceExpression = leftInstanceExpression;
		this.rightInstanceExpression = rightInstanceExpression;
	}

	public InstanceExpression(SemanticExpression semanticExpression,
			InstElement vertex) {
		this.volatileSemanticExpression = semanticExpression;
		this.semanticExpressionId = semanticExpression.getIdentifier();
		setLeftElement(vertex);
	}

	public Expression createSGSExpression(String element) {
		Expression condition = createExpression();
		Identifier iden = hlclFactory.newIdentifier(element + "_CompExp");
		return hlclFactory.doubleImplies(iden, (BooleanExpression) condition);
	}

	public Expression createExpression() {
		List<Expression> expressionTerms = expressionTerms();
		Class<? extends HlclFactory> hlclFactoryClass = hlclFactory.getClass();
		SemanticExpressionType semanticExpressionType = getSemanticExpression()
				.getSemanticExpressionType();
		boolean singleParameter = semanticExpressionType.isSingleInExpression();
		boolean arrayParameters = semanticExpressionType.isArrayParameters();
		Class<? extends Expression> parameter1 = null, parameter2 = null;
		parameter1 = getSemanticExpression().getSemanticExpressionType()
				.getLeftExpressionClass();
		if (!singleParameter)
			parameter2 = getSemanticExpression().getSemanticExpressionType()
					.getRightExpressionClass();
		Method factoryMethod = null;
		try {
			if (singleParameter) {
				// For negation, literal and number expressions
				factoryMethod = hlclFactoryClass.getMethod(
						semanticExpressionType.getMethod(), parameter1);
				return (Expression) factoryMethod.invoke(hlclFactory,
						parameter1.cast(expressionTerms.get(0)));
			} else if (arrayParameters) {
				// For Sum and Product Expressions
				Object dynamicArrayObject = Array.newInstance(parameter1, 2);

				Object[] dynamicArrayCast = (Object[]) dynamicArrayObject;
				dynamicArrayCast[0] = parameter1.cast(expressionTerms.get(0));
				dynamicArrayCast[1] = parameter2.cast(expressionTerms.get(1));

				factoryMethod = hlclFactoryClass.getMethod(
						semanticExpressionType.getMethod(),
						dynamicArrayObject.getClass());
				return (Expression) factoryMethod.invoke(hlclFactory,
						dynamicArrayObject);

			} else {
				// For the other expressions
				factoryMethod = hlclFactoryClass.getMethod(
						semanticExpressionType.getMethod(), parameter1,
						parameter2);

				return (Expression) factoryMethod.invoke(hlclFactory,
						parameter1.cast(expressionTerms.get(0)),
						parameter2.cast(expressionTerms.get(1)));
			}
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	// private Map<String, Identifier> getIdentifiers() {
	// Map<String, Identifier> out = new HashMap<String, Identifier>();
	// if (volatileLefInsttElement != null
	// && getSemanticExpression().getLeftAttributeName() != null) {
	// // System.out.println(leftVertex.getIdentifier() + " "
	// // + leftAttributeName);
	// Identifier identifier = hlclFactory.newIdentifier(volatileLefInsttElement
	// .getInstAttributeFullIdentifier(getSemanticExpression()
	// .getLeftAttributeName()), getSemanticExpression()
	// .getLeftAttributeName());
	// out.put(volatileLefInsttElement
	// .getInstAttributeFullIdentifier(getSemanticExpression()
	// .getLeftAttributeName()), identifier);
	// AbstractAttribute attribute = volatileLefInsttElement.getInstAttribute(
	// getSemanticExpression().getLeftAttributeName()).getAttribute();
	// if (attribute.getType().equals("Integer")) {
	// if (attribute.getDomain() != null)
	// identifier.setDomain(attribute.getDomain());
	// else {
	// if (attribute.getName().equals("value")) {
	// String domain = (String) volatileLefInsttElement.getInstAttribute(
	// SemanticVariable.VAR_VARIABLEDOMAIN).getValue();
	// identifier.setDomain(DomainParser.parseDomain(domain));
	// } else
	// identifier.setDomain(new RangeDomain(0, 4));
	// }
	// }
	// }
	// if (volatileRightInstElement != null
	// && getSemanticExpression().getRightAttributeName() != null) {
	// // System.out
	// // .println(rightVertex.getIdentifier() + rightAttributeName);
	// Identifier identifier =
	// hlclFactory.newIdentifier(volatileRightInstElement
	// .getInstAttributeFullIdentifier(getSemanticExpression()
	// .getRightAttributeName()), getSemanticExpression()
	// .getRightAttributeName());
	//
	// out.put(volatileRightInstElement
	// .getInstAttributeFullIdentifier(getSemanticExpression()
	// .getRightAttributeName()), identifier);
	// AbstractAttribute attribute = volatileRightInstElement.getInstAttribute(
	// getSemanticExpression().getRightAttributeName()).getAttribute();
	// if (attribute.getType().equals("Integer")) {
	// if (attribute.getDomain() != null)
	// identifier.setDomain(attribute.getDomain());
	// else if (attribute.getName().equals("value")) {
	// String domain = (String) volatileRightInstElement.getInstAttribute(
	// SemanticVariable.VAR_VARIABLEDOMAIN).getValue();
	// identifier.setDomain(DomainParser.parseDomain(domain));
	// } else
	// identifier.setDomain(new RangeDomain(0, 4));
	// }
	//
	// }
	// if (leftInstanceExpression != null) {
	// out.putAll(leftInstanceExpression.getIdentifiers());
	// }
	// if (rightInstanceExpression != null) {
	// out.putAll(rightInstanceExpression.getIdentifiers());
	// }
	// return out;
	// }

	private Identifier getIdentifier(ExpressionVertexType expressionVertexType) {
		Identifier out = null;
		if (expressionVertexType.name().equals("LEFT")) {
			// System.out.println(leftVertex.getIdentifier() + " "
			// + leftAttributeName);
			Identifier identifier = hlclFactory
					.newIdentifier(
							volatileLefInstElement
									.getInstAttributeFullIdentifier(getSemanticExpression()
											.getLeftAttributeName()),
							getSemanticExpression().getLeftAttributeName());
			AbstractAttribute attribute = volatileLefInstElement
					.getInstAttribute(
							getSemanticExpression().getLeftAttributeName())
					.getAttribute();
			// Specifically for Variables
			updateDomain(attribute, volatileLefInstElement, identifier);

			return identifier;
		}
		if (expressionVertexType.name().equals("RIGHT")) {
			// System.out
			// .println(rightVertex.getIdentifier() + rightAttributeName);
			Identifier identifier = hlclFactory
					.newIdentifier(
							volatileRightInstElement
									.getInstAttributeFullIdentifier(getSemanticExpression()
											.getRightAttributeName()),
							getSemanticExpression().getRightAttributeName());
			AbstractAttribute attribute = volatileRightInstElement
					.getInstAttribute(
							getSemanticExpression().getRightAttributeName())
					.getAttribute();

			updateDomain(attribute, volatileRightInstElement, identifier);

			return identifier;
		}
		return out;
	}

	private void updateDomain(AbstractAttribute attribute,
			InstElement instVertex, Identifier identifier) {
		if (attribute.getName()
				.equals(SemanticVariable.VAR_VARIABLECONFIGVALUE)) {
			String configdomain = (String) volatileLefInstElement
					.getInstAttribute(SemanticVariable.VAR_VARIABLECONFIGDOMAIN)
					.getValue();
			if (configdomain != null && !configdomain.equals(""))
				identifier.setDomain(DomainParser.parseDomain(configdomain));
		}
		if (attribute.getName().equals(SemanticVariable.VAR_VALUE)) {
			String type = (String) instVertex.getInstAttribute(
					SemanticVariable.VAR_VARIABLETYPE).getValue();

			if (type.equals("Integer")) {
				String domain = (String) instVertex.getInstAttribute(
						SemanticVariable.VAR_VARIABLEDOMAIN).getValue();
				identifier.setDomain(DomainParser.parseDomain(domain));
			} else if (type.equals("Enumeration")) {
				Object object = instVertex.getInstAttribute("enumerationType")
						.getValueObject();
				String domain = "";
				if (object != null) {
					@SuppressWarnings("unchecked")
					Collection<InstAttribute> values = (Collection<InstAttribute>) ((InstAttribute) ((InstEnumeration) object)
							.getInstAttribute(MetaEnumeration.VAR_METAENUMVALUE))
							.getValue();
					for (InstAttribute value : values) {
						String[] split = ((String) value.getValue()).split("-");
						domain += split[0] + ",";
					}
					domain = domain.substring(0, domain.length() - 1);
				}
				identifier.setDomain(DomainParser.parseDomain(domain));
			}
		} else

		if (attribute.getType().equals("Integer")) {
			if (attribute.getDomain() != null)
				identifier.setDomain(attribute.getDomain());
			else
				identifier.setDomain(new RangeDomain(0, 4));
		}
	}

	private List<Expression> expressionTerms() {
		List<Expression> out = new ArrayList<Expression>();

		List<ExpressionVertexType> expressionVertexTypes = new ArrayList<ExpressionVertexType>();

		ExpressionVertexType left = getSemanticExpression()
				.getLeftExpressionType();
		ExpressionVertexType right = getSemanticExpression()
				.getRightExpressionType();
		if (left != null)
			expressionVertexTypes.add(left);
		if (right != null)
			expressionVertexTypes.add(right);

		for (ExpressionVertexType expressionType : expressionVertexTypes) {
			switch (expressionType) {
			case LEFT:
				out.add(getIdentifier(expressionType));
				break;
			case RIGHT:
				out.add(getIdentifier(expressionType));
				break;
			case LEFTNUMERICEXPRESSIONVALUE:
				out.add(hlclFactory.number(getSemanticExpression()
						.getLeftNumber()));
				break;
			case RIGHTNUMERICEXPRESSIONVALUE:
				out.add(hlclFactory.number(getSemanticExpression()
						.getRightNumber()));

				break;
			case LEFTVARIABLEVALUE:
				out.add(hlclFactory.number(getVariableIntValue(expressionType)));
				break;
			case RIGHTVARIABLEVALUE:
				out.add(hlclFactory.number(getVariableIntValue(expressionType)));
				break;
			case LEFTSUBEXPRESSION:
				out.add(leftInstanceExpression.createExpression());
				break;
			case RIGHTSUBEXPRESSION:
				out.add(rightInstanceExpression.createExpression());
				break;
			default:
				break;

			}
		}

		return out;
	}

	private int getVariableIntValue(ExpressionVertexType expressionVertexType) {
		String value = null;
		String valueType = null;
		switch (expressionVertexType) {
		case LEFTVARIABLEVALUE:
			value = leftValue;
			valueType = (String) this.volatileLefInstElement.getInstAttribute(
					SemanticVariable.VAR_VARIABLETYPE).getValue();
			break;
		case RIGHTVARIABLEVALUE:
			value = rightValue;
			valueType = (String) this.volatileRightInstElement
					.getInstAttribute(SemanticVariable.VAR_VARIABLETYPE)
					.getValue();

			break;
		default:
		}
		switch (valueType) {
		case "Boolean":
			if (value.equals("true"))
				return 1;
			else
				return 0;
		case "Integer":
			return Integer.parseInt(value);
		case "Enumeration":
			String[] split = value.split("-");
			return Integer.parseInt(split[0]);
		}
		return 0;
	}

	public InstElement getLeftElement() {
		return volatileLefInstElement;
	}

	public void setLeftElement(InstElement leftElement) {
		this.volatileLefInstElement = leftElement;
		if (leftElement != null)
			leftInstElementId = leftElement.getIdentifier();
		else
			leftInstElementId = null;
	}

	public InstElement getRightElement() {
		return volatileRightInstElement;
	}

	public void setRightElement(InstElement rightElement) {
		this.volatileRightInstElement = rightElement;
		if (rightElement != null)
			rightInstElementId = rightElement.getIdentifier();
		else
			rightInstElementId = null;
	}

	public InstanceExpression getLeftInstanceExpression() {
		return leftInstanceExpression;
	}

	public void setLeftInstanceExpression(InstanceExpression leftSubExpression) {
		this.leftInstanceExpression = leftSubExpression;
	}

	public InstanceExpression getRightInstanceExpression() {
		return rightInstanceExpression;
	}

	public void setRightInstanceExpression(InstanceExpression rightSubExpression) {
		this.rightInstanceExpression = rightSubExpression;
	}

	public void setLeftInstanceExpression(ExpressionVertexType type,
			SemanticExpressionType semanticExpressionType, String id) {
		if (type == ExpressionVertexType.LEFTSUBEXPRESSION)
			this.leftInstanceExpression = new InstanceExpression(true, id,
					false);
		if (type == ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE)
			this.leftInstanceExpression = new InstanceExpression(true,
					new SemanticExpression(id, semanticExpressionType));
		getSemanticExpression().setLeftExpressionType(type);
	}

	public void setLeftElement(InstElement instElement, String attribute) {
		getSemanticExpression()
				.setLeftExpressionType(ExpressionVertexType.LEFT);
		this.volatileLefInstElement = instElement;
		getSemanticExpression().setLeftAttributeName(attribute);
	}

	public void setRightInstanceExpression(ExpressionVertexType type,
			SemanticExpressionType semanticExpressionType, String id) {
		if (type == ExpressionVertexType.RIGHTSUBEXPRESSION)
			this.rightInstanceExpression = new InstanceExpression(true, id,
					false);
		if (type == ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE)
			this.rightInstanceExpression = new InstanceExpression(true,
					new SemanticExpression(id, semanticExpressionType));
		getSemanticExpression().setRightExpressionType(type);
	}

	public void setRightElement(InstElement instElement, String attribute) {
		getSemanticExpression().setRightExpressionType(
				ExpressionVertexType.RIGHT);
		this.volatileLefInstElement = instElement;
		getSemanticExpression().setRightAttributeName(attribute);
	}

	public int getLeftNumber() {
		return getSemanticExpression().getLeftNumber();
	}

	public int getRightNumber() {
		return getSemanticExpression().getRightNumber();
	}

	public void setLeftNumber(int number) {
		getSemanticExpression().setLeftNumber(number);
	}

	public void setRightNumber(int number) {
		getSemanticExpression().setRightNumber(number);
	}

	public void setSemanticExpressionType(
			SemanticExpressionType semanticExpressionType) {
		if (customExpression)
			customSemanticExpression
					.setSemanticExpressionType(semanticExpressionType);
		if (semanticExpressionType == null
				|| semanticExpressionType.isSingleInExpression()) {
			rightInstanceExpression = null;
			volatileRightInstElement = null;
			getSemanticExpression().setRightExpressionType(null);
		}
	}

	public int getLeftValidExpressions() {
		return getSemanticExpression().getLeftValidExpressions();
	}

	public int getRightValidExpressions() {
		return getSemanticExpression().getRightValidExpressions();
	}

	public int getResultExpressions() {
		return getSemanticExpression().getResultExpressions();
	}

	public SemanticExpression getSemanticExpression() {
		if (customExpression)
			return customSemanticExpression;
		return volatileSemanticExpression;
	}

	public void setSemanticExpression(SemanticExpression semanticExpression) {
		if (customExpression)
			this.customSemanticExpression = semanticExpression;
		else
			this.volatileSemanticExpression = semanticExpression;
	}

	// Required for graph serialization
	public SemanticExpression getCustomSemanticExpression() {
		return customSemanticExpression;
	}

	// Required for graph serialization
	public void setCustomSemanticExpression(
			SemanticExpression customSemanticExpression) {
		this.customSemanticExpression = customSemanticExpression;
	}

	public boolean isCustomExpression() {
		return customExpression;
	}

	public void setCustomExpression(boolean customExpression) {
		this.customExpression = customExpression;
	}

	public String getSemanticExpressionId() {
		return semanticExpressionId;
	}

	public void setSemanticExpressionId(String semanticExpressionId) {
		this.semanticExpressionId = semanticExpressionId;
	}

	public String getLeftAttributeName() {
		return getSemanticExpression().getLeftAttributeName();
	}

	public String getRightAttributeName() {
		return getSemanticExpression().getRightAttributeName();
	}

	public void setLeftAttributeName(String attribute) {
		getSemanticExpression().setLeftAttributeName(attribute);
	}

	public void setRightAttributeName(String attribute) {
		getSemanticExpression().setRightAttributeName(attribute);
	}

	public String getOperation() {
		return getSemanticExpression().getOperation();
	}

	public ExpressionVertexType getLeftExpressionType() {
		return getSemanticExpression().getLeftExpressionType();
	}

	public ExpressionVertexType getRightExpressionType() {
		return getSemanticExpression().getRightExpressionType();
	}

	public void setLeftExpressionType(ExpressionVertexType expressionVertexType) {
		getSemanticExpression().setLeftExpressionType(expressionVertexType);
	}

	public void setRightExpressionType(ExpressionVertexType expressionVertexType) {
		getSemanticExpression().setRightExpressionType(expressionVertexType);
	}

	public String getSideElementIdentifier(
			ExpressionVertexType expressionVertexType,
			boolean displayVariableName) {
		if (expressionVertexType == ExpressionVertexType.LEFT
				|| expressionVertexType == ExpressionVertexType.LEFTVARIABLEVALUE) {
			if (getLeftElement() != null)
				return getLeftElement().getIdentifier();
			else
				return leftInstElementId;
		} else if (expressionVertexType == ExpressionVertexType.RIGHT
				|| expressionVertexType == ExpressionVertexType.RIGHTVARIABLEVALUE) {
			if (getRightElement() != null)
				return getRightElement().getIdentifier();
			else
				return rightInstElementId;
		}
		return null;
	}

	public String getRightInstElementId() {
		return rightInstElementId;
	}

	public void setRightInstElementId(String rightElementId) {
		this.rightInstElementId = rightElementId;
	}

	public String getLeftInstElementId() {
		return leftInstElementId;
	}

	public void setLeftInstElementId(String leftElementId) {
		this.leftInstElementId = leftElementId;
	}

	public String getLeftValue() {
		return leftValue;
	}

	public void setLeftValue(String leftValue) {
		this.leftValue = leftValue;
	}

	public String getRightValue() {
		return rightValue;
	}

	public void setRightValue(String rightValue) {
		this.rightValue = rightValue;
	}

	public String getElementAttributeIdentifier(
			ExpressionVertexType expressionVertexType, boolean displayName) {
		switch (expressionVertexType) {
		case LEFT:
			if (getLeftElement() != null)
				return displayName ? (String) getLeftElement()
						.getInstAttribute("name").getValue() : getLeftElement()
						.getIdentifier() + "_" + getLeftAttributeName();
			break;
		case RIGHT:
			if (getRightElement() != null)
				return displayName ? (String) getRightElement()
						.getInstAttribute("name").getValue()
						: getRightElement().getIdentifier() + "_"
								+ getRightAttributeName();
			break;
		case LEFTVARIABLEVALUE:
			if (getLeftElement() != null)
				return displayName ? (String) getLeftElement()
						.getInstAttribute("name").getValue() : getLeftElement()
						.getIdentifier() + "_" + leftValue;
			break;
		case RIGHTVARIABLEVALUE:
			if (getRightElement() != null)
				return displayName ? (String) getRightElement()
						.getInstAttribute("name").getValue()
						: getRightElement().getIdentifier() + "_" + rightValue;
			break;
		default:
			return null;
		}
		return null;
	}

	public InstElement getSideElement(ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFT:
		case LEFTVARIABLEVALUE:
			return getLeftElement();
		case RIGHT:
		case RIGHTVARIABLEVALUE:
			return getRightElement();
		default:
		}
		return null;
	}

	public void setInstElement(InstElement vertex,
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
		return getSemanticExpression().isSingleInExpression();
	}

	public String toString() {
		return "";// expressionStructure();
	}

	public String expressionStructure() {
		String out = "";
		for (ExpressionVertexType expressionVertex : getSemanticExpression()
				.getExpressionTypes()) {
			// if (expressionConnectors.size() > i)
			// out += " " + expressionConnectors.get(i) + " ";
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
				out += "(" + leftInstanceExpression.expressionStructure() + ")";
				break;
			case RIGHTSUBEXPRESSION:
				out += "(" + rightInstanceExpression.expressionStructure()
						+ ")";
				break;
			default:
				break;
			}
		}
		return out;
	}

	public List<ExpressionSubAction> getSemExprSubActions() {
		return SemExprSubActions;
	}

	public void setSemExprSubActions(List<ExpressionSubAction> semExprSubActions) {
		SemExprSubActions = semExprSubActions;
	}
}
