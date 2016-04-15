package com.variamos.dynsup.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstVertex;
import com.variamos.dynsup.interfaces.IntModelExpression;
import com.variamos.dynsup.types.ExpressionVertexType;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.DomainParser;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.RangeDomain;

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
public class ModelExpr implements Serializable, IntModelExpression {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296982198124042304L;
	private static HlclFactory hlclFactory = new HlclFactory();

	/**
	 * Associated SemanticExpression: volatile for semantic SemanticExpression;
	 * custom for variable expressions or InstElement custom expressions
	 */
	private OpersExpr volatileSemanticExpression;
	private OpersExpr customSemanticExpression;

	/**
	 * for LEFT
	 */
	private InstElement volatileLeftInstElement;

	/**
	 * for RIGHT
	 */
	private InstElement volatileRightInstElement;

	/**
	 * for LEFTSUBEXPRESSION
	 */
	private ModelExpr leftInstanceExpression;
	/**
	 * for RIGHTSUBEXPRESSION
	 */
	private ModelExpr rightInstanceExpression;

	// TODO change to a new class for type (normal, relax)
	private List<OpersSubOperation> SemExprSubActions;

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

	private int size = 0;
	private ModelInstance refas;

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

	public ModelExpr() {
		customExpression = false;
	}

	public void loadVolatileElements(Map<String, InstElement> instVertices) {
		if (leftInstElementId != null)
			volatileLeftInstElement = instVertices.get(leftInstElementId);
		if (rightInstElementId != null)
			volatileRightInstElement = instVertices.get(rightInstElementId);
		if (leftInstanceExpression != null)
			leftInstanceExpression.loadVolatileElements(instVertices);
		if (rightInstanceExpression != null)
			rightInstanceExpression.loadVolatileElements(instVertices);
	}

	public ModelExpr(boolean customExpression, String id, boolean first) {
		this.customExpression = customExpression;
		if (customExpression) {
			customSemanticExpression = new OpersExpr(id);
			semanticExpressionId = id;
		}
		if (first) {
			setLeftInstanceExpression(ExpressionVertexType.LEFTSUBEXPRESSION,
					null, "id");
			setRightInstanceExpression(ExpressionVertexType.RIGHTSUBEXPRESSION,
					null, "id");
		}
		this.setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
		this.setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
	}

	public ModelExpr(boolean customExpression, OpersExpr semanticExpression) {
		this.customExpression = customExpression;
		if (customExpression) {
			customSemanticExpression = semanticExpression;
		} else
			volatileSemanticExpression = semanticExpression;
		semanticExpressionId = semanticExpression.getIdentifier();
	}

	public ModelExpr(ModelInstance refas, boolean customExpression,
			OpersExpr semanticExpression) {
		this.refas = refas;
		this.customExpression = customExpression;
		if (customExpression) {
			customSemanticExpression = semanticExpression;
		} else
			volatileSemanticExpression = semanticExpression;
		semanticExpressionId = semanticExpression.getIdentifier();
	}

	public ModelExpr(OpersExpr semanticExpression, InstElement left,
			InstElement right) {
		this.volatileSemanticExpression = semanticExpression;
		this.semanticExpressionId = semanticExpression.getIdentifier();
		setLeftElement(left);
		setRightElement(right);
	}

	public ModelExpr(OpersExpr semanticExpression, InstElement vertex,
			boolean replaceTarget, ModelExpr instanceExpression) {
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

	public ModelExpr(OpersExpr semanticExpression,
			ModelExpr leftInstanceExpression, ModelExpr rightInstanceExpression) {
		this.volatileSemanticExpression = semanticExpression;
		this.semanticExpressionId = semanticExpression.getIdentifier();
		this.leftInstanceExpression = leftInstanceExpression;
		this.rightInstanceExpression = rightInstanceExpression;
	}

	public ModelExpr(OpersExpr semanticExpression, InstElement vertex) {
		this.volatileSemanticExpression = semanticExpression;
		this.semanticExpressionId = semanticExpression.getIdentifier();
		setLeftElement(vertex);
	}

	public Expression createSGSExpression(String element) {
		// System.out.println(element);
		Expression condition = createExpression(0);
		Identifier iden = hlclFactory.newIdentifier(element + "_CompExp");
		// System.out.println(hlclFactory.doubleImplies(iden,
		// (BooleanExpression) condition));
		return hlclFactory.doubleImplies(iden, (BooleanExpression) condition);
	}

	public Expression createSGSExpression() {
		Expression condition = createExpression(0);
		return condition;
	}

	public Expression createExpression(int pos) {
		List<Expression> expressionTerms = expressionTerms(pos);
		Class<? extends HlclFactory> hlclFactoryClass = hlclFactory.getClass();
		OpersExprType semanticExpressionType = getSemanticExpression()
				.getSemanticExpressionType();
		boolean singleParameter = semanticExpressionType.isSingleInExpression();
		boolean arrayParameters = semanticExpressionType.isArrayParameters();
		Class<? extends Expression> parameter1 = null, parameter2 = null;
		parameter1 = getSemanticExpression().getSemanticExpressionType()
				.getLeftExpressionClass();
		if (!singleParameter) {
			parameter2 = semanticExpressionType.getRightExpressionClass();
			if (expressionTerms.get(0) == null
					|| expressionTerms.get(1) == null)
				return null;
			// InstElement element = this.getSemanticExpression()
			// .getRightSemanticElement();
			// InstElement elementRel = this.getSemanticExpression()
			// .getRightSemanticRelElement();
			// if ((element == null ||
			// !volatileRightInstElement.isChild(element))
			// && (elementRel == null || !volatileRightInstElement
			// .isChild(elementRel))) {
			// if (element != null || elementRel != null)
			// ;
			// /*
			// * System.out .println("e: " + ((element == null) ? "" : element
			// * .getIdentifier()) + " eR: " + ((elementRel == null) ? "" :
			// * elementRel .getIdentifier()) + " vRE: " +
			// * ((volatileRightInstElement == null) ? "" :
			// * volatileRightInstElement .getIdentifier()));
			// */
			// // return null;
			// }

		} else {
			if (expressionTerms.get(0) == null)
				return null;
		}

		// InstElement element = this.getSemanticExpression()
		// .getLeftSemanticElement();
		// InstElement elementRel = this.getSemanticExpression()
		// .getLeftSemanticRelElement();
		// if ((element == null || !volatileLeftInstElement.isChild(element))
		// && (elementRel == null || !volatileLeftInstElement
		// .isChild(elementRel))) {
		// if (element != null || elementRel != null)
		// ;
		// /*
		// * System.out.println("e: " + ((element == null) ? "" :
		// * element.getIdentifier()) + " eR: " + ((elementRel == null) ? "" :
		// * elementRel .getIdentifier()) + " vLE: " +
		// * ((volatileLeftInstElement == null) ? "" :
		// * volatileLeftInstElement.getIdentifier()));
		// */
		// // return null;
		// }

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
				// System.out.println(expressionTerms.get(0) + " "
				// + expressionTerms.get(1));
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

	private int getSize(ExpressionVertexType expressionVertexType) {
		int out = 0;
		List<InstElement> elements = null;
		switch (expressionVertexType) {

		case LEFTITERCONCEPTVARIABLE:
		case LEFTITERCONFIXEDVARIABLE:
			elements = new ArrayList<InstElement>();
			String elemetType = this.getSemanticExpression()
					.getLeftSemanticElement().getIdentifier();
			elements.addAll(refas.getVariabilityVertexMC(elemetType));
			out = elements.size();
			break;
		case LEFTITERINCRELVARIABLE:
		case LEFTITERINCCONVARIABLE:
		case LEFTITERINCCONFIXEDVARIABLE:
		case LEFTITERINCRELFIXEDVARIABLE:
			elements = volatileLeftInstElement.getSourceRelations();
			out = volatileLeftInstElement.getSourceRelations().size();
			break;
		case LEFTITEROUTRELVARIABLE:
		case LEFTITEROUTCONVARIABLE:
		case LEFTITEROUTCONFIXEDVARIABLE:
		case LEFTITEROUTRELFIXEDVARIABLE:
			elements = volatileLeftInstElement.getTargetRelations();
			out = volatileLeftInstElement.getTargetRelations().size();
			break;
		case LEFTBOOLEANEXPRESSION:
			break;
		case LEFTCONCEPTVARIABLE:
			break;
		case LEFTITERANYCONVARIABLE:
			break;
		case LEFTITERANYFIXEDVARIABLE:
			break;
		case LEFTITERANYRELVARIABLE:
			break;
		case LEFTMODELVARS:
			break;
		case LEFTNUMERICVALUE:
			break;
		case LEFTSTRINGVALUE:
			break;
		case LEFTSUBEXPRESSION:
			break;
		case LEFTUNIQUEINCCONVARIABLE:
			break;
		case LEFTUNIQUEINCRELVARIABLE:
			break;
		case LEFTUNIQUEOUTCONVARIABLE:
			break;
		case LEFTUNIQUEOUTRELVARIABLE:
			break;
		case LEFTVARIABLE:
			break;
		case LEFTVARIABLEVALUE:
			break;
		case RIGHTBOOLEANEXPRESSION:
			break;
		case RIGHTCONCEPTVARIABLE:
			break;
		case RIGHTMODELVARS:
			break;
		case RIGHTNUMERICVALUE:
			break;
		case RIGHTSTRINGVALUE:
			break;
		case RIGHTSUBEXPRESSION:
			break;
		case RIGHTUNIQUEINCCONVARIABLE:
			break;
		case RIGHTUNIQUEINCRELVARIABLE:
			break;
		case RIGHTUNIQUEOUTCONVARIABLE:
			break;
		case RIGHTUNIQUEOUTRELVARIABLE:
			break;
		case RIGHTVARIABLE:
			break;
		case RIGHTVARIABLEVALUE:
			break;
		default:
			break;
		}
		return out;
	}

	private Identifier getIdentifier(ExpressionVertexType expressionVertexType,
			int pos) {
		Identifier out = null;

		List<InstElement> elements = null;
		InstElement volatileInstElement = null;
		InstElement expInstElement = null;
		String expAttributeName = null;
		switch (expressionVertexType) {

		case LEFTITERCONCEPTVARIABLE:
		case LEFTITERCONFIXEDVARIABLE:
			elements = new ArrayList<InstElement>();
			String elemetType = this.getSemanticExpression()
					.getLeftSemanticElement().getIdentifier();
			elements.addAll(refas.getVariabilityVertexMC(elemetType));
			size = elements.size();
			break;
		case LEFTITERINCRELVARIABLE:
		case LEFTITERINCRELFIXEDVARIABLE:
		case LEFTITERINCCONVARIABLE:
		case LEFTITERINCCONFIXEDVARIABLE:
			elements = volatileLeftInstElement.getSourceRelations();
			size = volatileLeftInstElement.getSourceRelations().size();
			break;
		case LEFTITEROUTRELVARIABLE:
		case LEFTITEROUTRELFIXEDVARIABLE:
		case LEFTITEROUTCONVARIABLE:
		case LEFTITEROUTCONFIXEDVARIABLE:
			elements = volatileLeftInstElement.getTargetRelations();
			size = volatileLeftInstElement.getTargetRelations().size();
			break;
		case LEFTITERANYCONVARIABLE:
		case LEFTITERANYFIXEDVARIABLE:
			elements = volatileLeftInstElement.getTargetRelations();
			elements.addAll(volatileLeftInstElement.getSourceRelations());
			size = volatileLeftInstElement.getTargetRelations().size();
			break;
		case LEFTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
			volatileInstElement = volatileLeftInstElement;
			break;
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
			volatileInstElement = volatileRightInstElement;
			break;
		case LEFTBOOLEANEXPRESSION:
			break;
		case LEFTCONCEPTVARIABLE:
			break;
		case LEFTITERANYRELVARIABLE:
			break;
		case LEFTMODELVARS:
			break;
		case LEFTNUMERICVALUE:
			break;
		case LEFTSTRINGVALUE:
			break;
		case LEFTSUBEXPRESSION:
			break;
		case LEFTVARIABLE:
			break;
		case LEFTVARIABLEVALUE:
			break;
		case RIGHTBOOLEANEXPRESSION:
			break;
		case RIGHTCONCEPTVARIABLE:
			break;
		case RIGHTMODELVARS:
			break;
		case RIGHTNUMERICVALUE:
			break;
		case RIGHTSTRINGVALUE:
			break;
		case RIGHTSUBEXPRESSION:
			break;
		case RIGHTVARIABLE:
			break;
		case RIGHTVARIABLEVALUE:
			break;
		default:
			break;

		}
		InstElement incExpDirInstElement = null;
		InstElement outExpDirInstElement = null;
		InstElement incExpIndirInstElement = null;
		InstElement outExpIndirInstElement = null;
		if (volatileInstElement != null
				&& volatileInstElement.getSourceRelations().size() > pos) {
			incExpDirInstElement = volatileInstElement.getSourceRelations()
					.get(pos);
			if (incExpDirInstElement.getSourceRelations().size() > 0)
				incExpIndirInstElement = incExpDirInstElement
						.getSourceRelations().get(0);
		}
		if (volatileInstElement != null
				&& volatileInstElement.getTargetRelations().size() > pos) {
			outExpDirInstElement = volatileInstElement.getTargetRelations()
					.get(pos);
			if (outExpDirInstElement.getTargetRelations().size() > 0)
				outExpIndirInstElement = outExpDirInstElement
						.getTargetRelations().get(0);
		}
		switch (expressionVertexType) {

		case LEFTVARIABLE:
			expInstElement = volatileLeftInstElement;
			expAttributeName = getSemanticExpression().getLeftAttributeName();
			break;
		case LEFTITERCONCEPTVARIABLE:
			expInstElement = elements.get(pos);
			expAttributeName = getSemanticExpression()
					.getLeftSemanticExpression().getLeftAttributeName();
			break;
		case LEFTITERCONFIXEDVARIABLE:
			expInstElement = elements.get(pos);
			expAttributeName = getSemanticExpression().getLeftAttributeName();

			break;
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERINCRELFIXEDVARIABLE:
		case LEFTITEROUTRELFIXEDVARIABLE:
			expInstElement = elements.get(pos);
			expAttributeName = getSemanticExpression().getLeftAttributeName();
			break;

		case LEFTITERINCCONVARIABLE:
		case LEFTITERINCCONFIXEDVARIABLE:
			expInstElement = elements.get(pos).getSourceRelations().get(0);
			expAttributeName = getSemanticExpression().getLeftAttributeName();
			break;
		case LEFTITEROUTCONVARIABLE:
		case LEFTITEROUTCONFIXEDVARIABLE:
			expInstElement = elements.get(pos).getTargetRelations().get(0);
			;
			expAttributeName = getSemanticExpression().getLeftAttributeName();
			break;
		case LEFTUNIQUEOUTRELVARIABLE:
			if (!(volatileLeftInstElement instanceof InstVertex))
				expInstElement = outExpIndirInstElement;
			else
				expInstElement = outExpDirInstElement;
			expAttributeName = getSemanticExpression()
					.getLeftRelAttributeName();
			break;
		case LEFTUNIQUEINCRELVARIABLE:
			if (!(volatileLeftInstElement instanceof InstVertex))
				expInstElement = incExpIndirInstElement;
			else
				expInstElement = incExpDirInstElement;
			expAttributeName = getSemanticExpression()
					.getLeftRelAttributeName();
			break;
		case LEFTUNIQUEOUTCONVARIABLE:
			if (volatileLeftInstElement instanceof InstVertex)
				expInstElement = outExpIndirInstElement;
			else
				expInstElement = outExpDirInstElement;
			expAttributeName = getSemanticExpression().getLeftAttributeName();
			break;
		case LEFTUNIQUEINCCONVARIABLE:
			if (volatileLeftInstElement instanceof InstVertex)
				expInstElement = incExpIndirInstElement;
			else
				expInstElement = incExpDirInstElement;
			expAttributeName = getSemanticExpression().getLeftAttributeName();
			break;
		case RIGHTVARIABLE:
			expInstElement = volatileRightInstElement;
			expAttributeName = getSemanticExpression().getRightAttributeName();
			break;
		case RIGHTCONCEPTVARIABLE:
			expInstElement = this.getRightElement();
			expAttributeName = getSemanticExpression().getRightAttributeName();
			break;
		case RIGHTUNIQUEOUTRELVARIABLE:
			if (!(volatileRightInstElement instanceof InstConcept))
				expInstElement = outExpIndirInstElement;
			else
				expInstElement = outExpDirInstElement;
			expAttributeName = getSemanticExpression()
					.getRightRelAttributeName();
			break;
		case RIGHTUNIQUEINCRELVARIABLE:
			if (!(volatileRightInstElement instanceof InstConcept))
				expInstElement = incExpIndirInstElement;
			else
				expInstElement = incExpDirInstElement;
			expAttributeName = getSemanticExpression()
					.getRightRelAttributeName();
			break;
		case RIGHTUNIQUEOUTCONVARIABLE:
			if (volatileRightInstElement instanceof InstConcept)
				expInstElement = outExpIndirInstElement;
			else
				expInstElement = outExpDirInstElement;
			expAttributeName = getSemanticExpression().getRightAttributeName();
			break;
		case RIGHTUNIQUEINCCONVARIABLE:
			if (volatileRightInstElement instanceof InstConcept)
				expInstElement = incExpIndirInstElement;
			else
				expInstElement = incExpDirInstElement;

			expAttributeName = getSemanticExpression().getRightAttributeName();
			break;
		case LEFTBOOLEANEXPRESSION:
			break;
		case LEFTCONCEPTVARIABLE:
			break;
		case LEFTITERANYCONVARIABLE:
			break;
		case LEFTITERANYFIXEDVARIABLE:
			break;
		case LEFTITERANYRELVARIABLE:
			break;
		case LEFTMODELVARS:
			break;
		case LEFTNUMERICVALUE:
			break;
		case LEFTSTRINGVALUE:
			break;
		case LEFTSUBEXPRESSION:
			break;
		case LEFTVARIABLEVALUE:
			break;
		case RIGHTBOOLEANEXPRESSION:
			break;
		case RIGHTMODELVARS:
			break;
		case RIGHTNUMERICVALUE:
			break;
		case RIGHTSTRINGVALUE:
			break;
		case RIGHTSUBEXPRESSION:
			break;
		case RIGHTVARIABLEVALUE:
			break;
		default:
			break;
		}

		if (expInstElement != null && expAttributeName != null) {
			String fullIdentifier = expInstElement
					.getInstAttributeFullIdentifier(expAttributeName);
			if (fullIdentifier == null) {
				System.out.println("NUll: " + expInstElement.getIdentifier()
						+ " " + expAttributeName);
				return null;
			}
			Identifier identifier = hlclFactory.newIdentifier(fullIdentifier,
					expAttributeName);
			ElemAttribute attribute = expInstElement.getInstAttribute(
					expAttributeName).getAttribute();
			// Specifically for Variables
			updateDomain(attribute, expInstElement, identifier);

			return identifier;
		}

		return out;
	}

	public static void updateDomain(ElemAttribute attribute,
			InstElement instVertex, Identifier identifier) {
		if (attribute.getName().equals("varConfValue")) {
			String configdomain = (String) (instVertex.getInstAttribute(
					"varConfDom").getValue() + "");
			if (configdomain != null && !configdomain.equals(""))
				identifier.setDomain(DomainParser.parseDomain(configdomain));
		}
		if (attribute.getName().equals("value")) {
			String type = (String) instVertex.getInstAttribute("variableType")
					.getValue();

			if (type.equals("Integer")) {
				String domain = (String) instVertex.getInstAttribute("varDom")
						.getValue();
				identifier.setDomain(DomainParser.parseDomain(domain));
			} else if (type.equals("Enumeration")) {
				Object object = instVertex.getInstAttribute("enumType")
						.getValueObject();
				String domain = "";
				if (object != null) {
					@SuppressWarnings("unchecked")
					Collection<InstAttribute> values = (Collection<InstAttribute>) ((InstAttribute) ((InstElement) object)
							.getInstAttribute(SyntaxConcept.VAR_METAENUMVALUE))
							.getValue();
					for (InstAttribute value : values) {
						String[] split = ((String) value.getValue()).split("#");
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
		} else if (attribute.getType().equals("String")) {
			if (attribute.getDomain() != null)
				identifier.setDomain(attribute.getDomain());
		}
	}

	private List<Expression> expressionTerms(int pos) {
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
		boolean iter = false;

		for (ExpressionVertexType expressionType : expressionVertexTypes) {
			switch (expressionType) {
			case LEFTITEROUTRELVARIABLE:
			case LEFTITERINCRELVARIABLE:
			case LEFTITEROUTCONVARIABLE:
			case LEFTITERINCCONVARIABLE:
			case LEFTITERCONCEPTVARIABLE:
				iter = true;
				pos = -1;
				if (pos >= getSize(expressionType) - 1)
					iter = false;
				break;

			case LEFTITERCONFIXEDVARIABLE:
			case LEFTITERINCCONFIXEDVARIABLE:
			case LEFTITEROUTCONFIXEDVARIABLE:
			case LEFTITERINCRELFIXEDVARIABLE:
			case LEFTITEROUTRELFIXEDVARIABLE:
				iter = true;
			case LEFTVARIABLE:
			case LEFTUNIQUEOUTRELVARIABLE:
			case LEFTUNIQUEINCRELVARIABLE:
			case LEFTUNIQUEOUTCONVARIABLE:
			case LEFTUNIQUEINCCONVARIABLE:
				out.add(getIdentifier(expressionType, pos));
				if (pos >= size - 1)
					iter = false;
				break;
			case RIGHTVARIABLE:
			case RIGHTUNIQUEOUTRELVARIABLE:
			case RIGHTUNIQUEINCRELVARIABLE:
			case RIGHTUNIQUEOUTCONVARIABLE:
			case RIGHTUNIQUEINCCONVARIABLE:
				if (iter)
					out.add(leftInstanceExpression.createExpression(pos + 1));
				if (pos == -1 && !iter)
					// FIXME support other types of default values for iter
					// expressions
					out.add(hlclFactory.number(getSemanticExpression()
							.getLeftSemanticExpression().getRightNumber()));
				if (pos == -1 || !iter)
					out.add(getIdentifier(expressionType, pos));
				break;
			case LEFTNUMERICVALUE:
				out.add(hlclFactory.number(getSemanticExpression()
						.getLeftNumber()));
				break;
			case LEFTVARIABLEVALUE:
			case LEFTSTRINGVALUE:
				out.add(hlclFactory.number(getVariableIntValue(expressionType)));
				break;
			case LEFTCONCEPTVARIABLE:
				out.add(leftInstanceExpression.createExpression(0));
				break;
			case LEFTSUBEXPRESSION:
				out.add(leftInstanceExpression.createExpression(0));
				break;
			case RIGHTSUBEXPRESSION:
				if (iter)
					out.add(leftInstanceExpression.createExpression(pos + 1));
				if (pos == -1 && !iter)
					// FIXME support other types of default values for iter
					// expressions
					out.add(hlclFactory.number(getSemanticExpression()
							.getLeftSemanticExpression().getRightNumber()));
				if (pos == -1 || !iter)
					out.add(rightInstanceExpression.createExpression(0));
				break;
			case RIGHTCONCEPTVARIABLE:
				if (iter) {
					if (leftInstanceExpression == null)
						System.out.println("leftInstanceExpression null");
					out.add(leftInstanceExpression.createExpression(pos + 1));
				}
				if (pos == -1 || !iter)
					out.add(getIdentifier(expressionType, pos));
				if (pos == -1 && !iter)
					// FIXME support other types of default values for iter
					// expressions
					out.add(hlclFactory.number(getSemanticExpression()
							.getLeftSemanticExpression().getRightNumber()));
				break;
			case RIGHTNUMERICVALUE:
				if (iter)
					out.add(leftInstanceExpression.createExpression(pos + 1));
				if (pos == -1 || !iter)
					out.add(hlclFactory.number(getSemanticExpression()
							.getRightNumber()));

				if (pos == -1 && !iter)
					// FIXME support other types of default values for iter
					// expressions
					out.add(hlclFactory.number(getSemanticExpression()
							.getLeftSemanticExpression().getRightNumber()));

				break;
			case RIGHTVARIABLEVALUE:
			case RIGHTSTRINGVALUE:
				if (iter)
					out.add(leftInstanceExpression.createExpression(pos + 1));
				if (pos == -1 || !iter)
					out.add(hlclFactory
							.number(getVariableIntValue(expressionType)));
				if (pos == -1 && !iter)
					// FIXME support other types of default values for iter
					// expressions
					out.add(hlclFactory.number(getSemanticExpression()
							.getLeftSemanticExpression().getRightNumber()));
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
			valueType = (String) this.volatileLeftInstElement.getInstAttribute(
					"variableType").getValue();
			break;
		case RIGHTVARIABLEVALUE:
			value = rightValue;
			valueType = (String) this.volatileRightInstElement
					.getInstAttribute("variableType").getValue();

			break;
		case LEFTSTRINGVALUE:
			value = leftValue;
			valueType = "String";
			break;
		case RIGHTSTRINGVALUE:
			value = rightValue;
			valueType = "String";

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
			String[] split = value.split("#");
			return Integer.parseInt(split[0]);
		case "String":
			return value.hashCode();
		}
		return 0;
	}

	public InstElement getLeftElement() {
		return volatileLeftInstElement;
	}

	public void setLeftElement(InstElement leftElement) {
		this.volatileLeftInstElement = leftElement;
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

	public ModelExpr getLeftInstanceExpression() {
		return leftInstanceExpression;
	}

	public void setLeftInstanceExpression(ModelExpr leftSubExpression) {
		this.leftInstanceExpression = leftSubExpression;
	}

	public ModelExpr getRightInstanceExpression() {
		return rightInstanceExpression;
	}

	public void setRightInstanceExpression(ModelExpr rightSubExpression) {
		this.rightInstanceExpression = rightSubExpression;
	}

	public void setLeftInstanceExpression(ExpressionVertexType type,
			OpersExprType semanticExpressionType, String id) {
		if (type == ExpressionVertexType.LEFTSUBEXPRESSION)
			this.leftInstanceExpression = new ModelExpr(true, id, false);
		if (type == ExpressionVertexType.LEFTNUMERICVALUE)
			this.leftInstanceExpression = new ModelExpr(refas, true,
					new OpersExpr(id, semanticExpressionType));
		getSemanticExpression().setLeftExpressionType(type);
	}

	public void setLeftElement(InstElement instElement, String attribute) {
		getSemanticExpression().setLeftExpressionType(
				ExpressionVertexType.LEFTVARIABLE);
		this.volatileLeftInstElement = instElement;
		getSemanticExpression().setLeftAttributeName(attribute);
	}

	public void setRightInstanceExpression(ExpressionVertexType type,
			OpersExprType semanticExpressionType, String id) {
		if (type == ExpressionVertexType.RIGHTSUBEXPRESSION)
			this.rightInstanceExpression = new ModelExpr(true, id, false);
		if (type == ExpressionVertexType.RIGHTNUMERICVALUE)
			this.rightInstanceExpression = new ModelExpr(refas, true,
					new OpersExpr(id, semanticExpressionType));
		getSemanticExpression().setRightExpressionType(type);
	}

	public void setRightElement(InstElement instElement, String attribute) {
		getSemanticExpression().setRightExpressionType(
				ExpressionVertexType.RIGHTVARIABLE);
		this.volatileLeftInstElement = instElement;
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

	public void setSemanticExpressionType(OpersExprType semanticExpressionType) {
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

	public OpersExpr getSemanticExpression() {
		if (customExpression)
			return customSemanticExpression;
		return volatileSemanticExpression;
	}

	public void setSemanticExpression(OpersExpr semanticExpression) {
		if (customExpression)
			this.customSemanticExpression = semanticExpression;
		else
			this.volatileSemanticExpression = semanticExpression;
	}

	// Required for graph serialization
	public OpersExpr getCustomSemanticExpression() {
		return customSemanticExpression;
	}

	// Required for graph serialization
	public void setCustomSemanticExpression(OpersExpr customSemanticExpression) {
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
		if (expressionVertexType == ExpressionVertexType.LEFTVARIABLE
				|| expressionVertexType == ExpressionVertexType.LEFTVARIABLEVALUE) {
			if (getLeftElement() != null)
				return getLeftElement().getIdentifier();
			else
				return leftInstElementId;
		} else if (expressionVertexType == ExpressionVertexType.RIGHTVARIABLE
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
		case LEFTVARIABLE:
			if (getLeftElement() != null)
				return (displayName ? (String) getLeftElement()
						.getInstAttribute("name").getValue() : getLeftElement()
						.getIdentifier())
						+ "_" + getLeftAttributeName();
			break;
		case RIGHTVARIABLE:
			if (getRightElement() != null)
				return (displayName ? (String) getRightElement()
						.getInstAttribute("name").getValue()
						: getRightElement().getIdentifier())
						+ "_" + getRightAttributeName();
			break;
		case LEFTVARIABLEVALUE:
			if (getLeftElement() != null)
				return (displayName ? (String) getLeftElement()
						.getInstAttribute("name").getValue() : getLeftElement()
						.getIdentifier())
						+ "_" + leftValue;
			break;
		case RIGHTVARIABLEVALUE:
			if (getRightElement() != null)
				return (displayName ? (String) getRightElement()
						.getInstAttribute("name").getValue()
						: getRightElement().getIdentifier())
						+ "_" + rightValue;
			break;
		default:
			return null;
		}
		return null;
	}

	public InstElement getSideElement(ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFTVARIABLE:
		case LEFTVARIABLEVALUE:
			return getLeftElement();
		case RIGHTVARIABLE:
		case RIGHTVARIABLEVALUE:
			return getRightElement();
		default:
		}
		return null;
	}

	public void setInstElement(InstElement vertex,
			ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFTVARIABLE:
		case LEFTVARIABLEVALUE:
			this.setLeftElement(vertex);
			break;
		case RIGHTVARIABLE:
		case RIGHTVARIABLEVALUE:
			this.setRightElement(vertex);
		default:
		}
	}

	public void setAttributeName(String attributeName,
			ExpressionVertexType expressionVertexType) {
		switch (expressionVertexType) {
		case LEFTVARIABLE:
			this.setLeftAttributeName(attributeName);
			break;
		case RIGHTVARIABLE:
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
			case LEFTVARIABLE:
				out += getLeftAttributeName();
				break;
			case RIGHTVARIABLE:
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

	public List<OpersSubOperation> getSemExprSubActions() {
		return SemExprSubActions;
	}

	public void setSemExprSubActions(List<OpersSubOperation> semExprSubActions) {
		SemExprSubActions = semExprSubActions;
	}

	public void createFromSemanticExpression(InstElement instElement, int pos) {
		ExpressionVertexType type = volatileSemanticExpression
				.getLeftExpressionType();

		switch (type) {
		case LEFTSUBEXPRESSION:
			leftInstanceExpression = new ModelExpr(refas, false,
					volatileSemanticExpression.getLeftSemanticExpression());
			leftInstanceExpression.createFromSemanticExpression(instElement,
					pos);
			break;
		case LEFTNUMERICVALUE:
			this.leftValue = volatileSemanticExpression.getLeftNumber() + "";
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTVARIABLEVALUE:
		case LEFTSTRINGVALUE:
			this.leftValue = volatileSemanticExpression.getLeftString();

		case LEFTUNIQUEOUTRELVARIABLE:
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTUNIQUEINCRELVARIABLE:
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTUNIQUEOUTCONVARIABLE:
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTUNIQUEINCCONVARIABLE:
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTVARIABLE:
		case LEFTCONCEPTVARIABLE: // TODO verify
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTITERCONCEPTVARIABLE:
			String elemetType = this.getSemanticExpression()
					.getLeftSemanticElement().getIdentifier();
			if (pos < refas.getVariabilityVertexMC(elemetType).size()) {
				leftInstanceExpression = new ModelExpr(refas, false, this
						.getSemanticExpression().getLeftSemanticExpression());
				leftInstanceExpression.createFromSemanticExpression(
						instElement, pos + 1);
			}
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTITERCONFIXEDVARIABLE:
			elemetType = this.getSemanticExpression().getLeftSemanticElement()
					.getIdentifier();
			if (pos < refas.getVariabilityVertexMC(elemetType).size()) {
				leftInstanceExpression = new ModelExpr(refas, false,
						this.getSemanticExpression());
				leftInstanceExpression.createFromSemanticExpression(
						instElement, pos + 1);
			}
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTITERINCRELVARIABLE:
		case LEFTITERINCCONVARIABLE:
			if (pos < instElement.getSourceRelations().size()) {
				leftInstanceExpression = new ModelExpr(refas, false, this
						.getSemanticExpression().getLeftSemanticExpression());
				leftInstanceExpression.createFromSemanticExpression(
						instElement, pos + 1);
			}
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTITERINCCONFIXEDVARIABLE:
		case LEFTITERINCRELFIXEDVARIABLE:
			if (pos < instElement.getSourceRelations().size()) {
				leftInstanceExpression = new ModelExpr(refas, false,
						this.getSemanticExpression());
				leftInstanceExpression.createFromSemanticExpression(
						instElement, pos + 1);
			}
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTITEROUTCONVARIABLE:
		case LEFTITEROUTRELVARIABLE:
			if (pos < instElement.getTargetRelations().size()) {
				leftInstanceExpression = new ModelExpr(refas, false, this
						.getSemanticExpression().getLeftSemanticExpression());
				leftInstanceExpression.createFromSemanticExpression(
						instElement, pos + 1);
			}
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTITEROUTCONFIXEDVARIABLE:
		case LEFTITEROUTRELFIXEDVARIABLE:
			if (pos < instElement.getTargetRelations().size()) {
				leftInstanceExpression = new ModelExpr(refas, false,
						this.getSemanticExpression());
				leftInstanceExpression.createFromSemanticExpression(
						instElement, pos + 1);
			}
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTBOOLEANEXPRESSION:
			break;
		case LEFTITERANYCONVARIABLE:
			break;
		case LEFTITERANYFIXEDVARIABLE:
			break;
		case LEFTITERANYRELVARIABLE:
			break;
		case LEFTMODELVARS:
			break;
		case RIGHTBOOLEANEXPRESSION:
			break;
		case RIGHTCONCEPTVARIABLE:
			break;
		case RIGHTMODELVARS:
			break;
		case RIGHTNUMERICVALUE:
			break;
		case RIGHTSTRINGVALUE:
			break;
		case RIGHTSUBEXPRESSION:
			break;
		case RIGHTUNIQUEINCCONVARIABLE:
			break;
		case RIGHTUNIQUEINCRELVARIABLE:
			break;
		case RIGHTUNIQUEOUTCONVARIABLE:
			break;
		case RIGHTUNIQUEOUTRELVARIABLE:
			break;
		case RIGHTVARIABLE:
			break;
		case RIGHTVARIABLEVALUE:
			break;
		default:
			break;
		}

		type = volatileSemanticExpression.getRightExpressionType();
		if (type == null) {
			System.out.println("d");
		}

		switch (type) {
		case RIGHTSUBEXPRESSION:
			rightInstanceExpression = new ModelExpr(refas, false,
					volatileSemanticExpression.getRightSemanticExpression());
			if (instElement != null)
				rightInstanceExpression.createFromSemanticExpression(
						instElement, pos);
			break;

		case RIGHTNUMERICVALUE:
			this.rightValue = volatileSemanticExpression.getRightNumber() + "";
			this.volatileRightInstElement = instElement;
			break;
		case RIGHTVARIABLEVALUE:
		case RIGHTSTRINGVALUE:
			this.rightValue = volatileSemanticExpression.getRightString();
		case RIGHTVARIABLE:
		case RIGHTCONCEPTVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEINCCONVARIABLE:
			this.volatileRightInstElement = instElement;
			break;
		case LEFTBOOLEANEXPRESSION:
			break;
		case LEFTCONCEPTVARIABLE:
			break;
		case LEFTITERANYCONVARIABLE:
			break;
		case LEFTITERANYFIXEDVARIABLE:
			break;
		case LEFTITERANYRELVARIABLE:
			break;
		case LEFTITERCONCEPTVARIABLE:
			break;
		case LEFTITERCONFIXEDVARIABLE:
			break;
		case LEFTITERINCCONFIXEDVARIABLE:
			break;
		case LEFTITERINCCONVARIABLE:
			break;
		case LEFTITERINCRELFIXEDVARIABLE:
			break;
		case LEFTITERINCRELVARIABLE:
			break;
		case LEFTITEROUTCONFIXEDVARIABLE:
			break;
		case LEFTITEROUTCONVARIABLE:
			break;
		case LEFTITEROUTRELFIXEDVARIABLE:
			break;
		case LEFTITEROUTRELVARIABLE:
			break;
		case LEFTMODELVARS:
			break;
		case LEFTNUMERICVALUE:
			break;
		case LEFTSTRINGVALUE:
			break;
		case LEFTSUBEXPRESSION:
			break;
		case LEFTUNIQUEINCCONVARIABLE:
			break;
		case LEFTUNIQUEINCRELVARIABLE:
			break;
		case LEFTUNIQUEOUTCONVARIABLE:
			break;
		case LEFTUNIQUEOUTRELVARIABLE:
			break;
		case LEFTVARIABLE:
			break;
		case LEFTVARIABLEVALUE:
			break;
		case RIGHTBOOLEANEXPRESSION:
			break;
		case RIGHTMODELVARS:
			break;
		default:
			break;
		}

	}
}
