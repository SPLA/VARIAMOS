package com.variamos.dynsup.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.instance.InstVertex;
import com.variamos.dynsup.types.ExpressionVertexType;
import com.variamos.hlcl.core.DomainParser;
import com.variamos.hlcl.model.domains.RangeDomain;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntExpression;

/**
 * A class to represent InstanceExpressions. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
/**
 * @author jcmunoz
 *
 */
public class ModelExpr implements Serializable, Cloneable {
	// FIXME v1.1 RENAME to ModelSupExpr
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
	private InstanceModel refas;
	private int expressionInstance = -1;
	private boolean iterInstance;
	private String sourceInstanceId = "";
	private String targetInstanceId = "";
	private String elementInstanceId = "";

	/**
	 * It sourceConceptId represents the id in the model  
	 */
	private String sourceConceptId;
	
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

	public ModelExpr(String conceptId) {
		sourceConceptId = conceptId;
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
	
	/**
	 * Se incluye el identificador conceptId
	 * @param conceptId
	 * @param customExpression
	 * @param id
	 * @param first
	 * @param instance
	 */
	public ModelExpr(String conceptId, boolean customExpression, String id, boolean first,
			int instance) {
		this(customExpression, id, first,
				instance);
		sourceConceptId = conceptId;
	}

	public ModelExpr(boolean customExpression, String id, boolean first,
			int instance) {
		this.customExpression = customExpression;
		this.expressionInstance = instance;
		if (customExpression) {
			customSemanticExpression = new OpersExpr(id);
			semanticExpressionId = id;
		}
		if (first) {
			setLeftInstanceExpression(ExpressionVertexType.LEFTSUBEXPRESSION,
					null, "id", instance);
			setRightInstanceExpression(ExpressionVertexType.RIGHTSUBEXPRESSION,
					null, "id", instance);
		}
		this.setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
		this.setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
	}

	public int getExpressionInstance() {
		return expressionInstance;
	}

	public void setExpressionInstance(int expressionInstance) {
		this.expressionInstance = expressionInstance;
	}

	public ModelExpr(boolean customExpression, OpersExpr semanticExpression) {
		this.customExpression = customExpression;
		if (customExpression) {
			customSemanticExpression = semanticExpression;
		} else
			volatileSemanticExpression = semanticExpression;
		semanticExpressionId = semanticExpression.getIdentifier();
	}

	public ModelExpr(InstanceModel refas, boolean customExpression,
			OpersExpr semanticExpression, boolean iterInstance) {
		this.refas = refas;
		this.iterInstance = iterInstance;
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

	public IntExpression createSGSExpression(String element)
			throws FunctionalException {
		// System.out.println(element);
		IntExpression condition = createExpression(0, -1);
		Identifier iden = hlclFactory.newIdentifier(element + "_CompExp");
		// System.out.println(hlclFactory.doubleImplies(iden,
		// (BooleanExpression) condition));
		return hlclFactory
				.doubleImplies(iden, (IntBooleanExpression) condition);
	}

	public IntExpression createSGSExpression() throws FunctionalException {
		// System.out.println("PNT Struc: " + this.getSemanticExpressionId() +
		// " "
		// + this.expressionStructure());
		return createExpression(0, -1);

	}

	public IntExpression createExpression(int pos, int leftIterInstance)
			throws FunctionalException {
		int i = 0;
		List<IntExpression> expressionTerms = expressionTerms(pos,
				leftIterInstance, 0);
		// System.out.println("ERR MODELEXP" + expressionTerms.get(0) + " "
		// + expressionTerms.get(1));

		Class<? extends HlclFactory> hlclFactoryClass = hlclFactory.getClass();
		OpersExprType semanticExpressionType = getSemanticExpression()
				.getSemanticExpressionType();
		boolean singleParameter = semanticExpressionType.isSingleInExpression();
		boolean arrayParameters = semanticExpressionType.isArrayParameters();
		Class<? extends IntExpression> parameter1 = null, parameter2 = null;
		parameter1 = getSemanticExpression().getSemanticExpressionType()
				.getLeftExpressionClass();

		Method factoryMethod = null;
		if (!singleParameter) {
			parameter2 = semanticExpressionType.getRightExpressionClass();
			if (expressionTerms.size() < 2)
				System.out.println("ERROR IN NUMBER OF TERMS");
			if (expressionTerms.size() < 2 || expressionTerms.get(0) == null
					|| expressionTerms.get(1) == null) {
				// if (expressionTerms.get(1) != null) {
				// try {
				// factoryMethod = hlclFactoryClass.getMethod(
				// semanticExpressionType.getMethod(), parameter2,
				// parameter2);
				// return (IntExpression) factoryMethod.invoke(
				// hlclFactory,
				// parameter2.cast(expressionTerms.get(1)),
				// parameter2.cast(expressionTerms.get(1)));
				// } catch (NoSuchMethodException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (SecurityException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (IllegalAccessException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (IllegalArgumentException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (InvocationTargetException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				//
				// } else
				System.out.println("ERR: expression ignored "
						+ this.getSemanticExpressionId());
				return null;
			}
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

		try {
			if (singleParameter) {
				// For negation, literal and number expressions
				factoryMethod = hlclFactoryClass.getMethod(
						semanticExpressionType.getMethod(), parameter1);
				return (IntExpression) factoryMethod.invoke(hlclFactory,
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
				return (IntExpression) factoryMethod.invoke(hlclFactory,
						dynamicArrayObject);

			} else {
				// For the other expressions
				factoryMethod = hlclFactoryClass.getMethod(
						semanticExpressionType.getMethod(), parameter1,
						parameter2);
				// System.out.println("EXPRR " + this.getSemanticExpressionId()
				// + " " + expressionTerms.get(0) + " "
				// + expressionTerms.get(1) + factoryMethod.getName());
				// try {
				// Thread.sleep(300);
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				return (IntExpression) factoryMethod.invoke(hlclFactory,
						parameter1.cast(expressionTerms.get(0)),
						parameter2.cast(expressionTerms.get(1)));
			}
		} catch (Exception e) {
			// FIXME issue#230
			throw new FunctionalException("EXPRR " + expressionTerms.get(0)
					+ " " + expressionTerms.get(1) + factoryMethod.getName()
					+ e.getMessage());

			// ConsoleTextArea.addText(e.getStackTrace());

		}

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
		elements = new ArrayList<InstElement>();
		switch (expressionVertexType) {

		case LEFTITERCONCEPTVARIABLE:
		case LEFSUBTITERCONVARIABLE:
			String elemetType = this.getSemanticExpression()
					.getLeftSemanticElement().getIdentifier();
			elements.addAll(refas.getVariabilityVertexMC(elemetType));
			out = elements.size();
			break;
		case LEFTITERINCRELVARIABLE:
		case LEFTITERINCCONVARIABLE:
		case LEFTSUBITERINCCONVARIABLE:
		case LEFTSUBITERINCRELVARIABLE:
		case LEFTITERINCSUBEXP:
		case LEFSUBTITERINCSUBEXP:
			elements = volatileLeftInstElement.getSourceRelations();
			out = volatileLeftInstElement.getSourceRelations().size();
			break;
		case LEFTITEROUTRELVARIABLE:
		case LEFTITEROUTCONVARIABLE:
		case LEFTSUBITEROUTCONVARIABLE:
		case LEFTSUBITEROUTRELVARIABLE:
		case LEFTITEROUTSUBEXP:
		case LEFTSUBITEROUTSUBEXP:
			elements = volatileLeftInstElement.getTargetRelations();
			out = volatileLeftInstElement.getTargetRelations().size();
			break;
		case LEFTBOOLEANEXPRESSION:
			break;
		case LEFTCONCEPTVARIABLE:
			break;
		case LEFTITERANYCONVARIABLE:
			break;
		case LEFTSUBITERANYVARIABLE:
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
			int pos, int iterInstance) {
		Identifier out = null;
		List<InstElement> elements = null;
		InstElement volatileInstElement = null;
		InstElement expInstElement = null;
		String expAttributeName = null;
		switch (expressionVertexType) {

		case LEFTITERCONCEPTVARIABLE:
		case LEFSUBTITERCONVARIABLE:
			elements = new ArrayList<InstElement>();
			String elemetType = this.getSemanticExpression()
					.getLeftSemanticElement().getIdentifier();
			elements.addAll(refas.getVariabilityVertexMC(elemetType));
			size = elements.size();
			break;
		case LEFTITERINCRELVARIABLE:
		case LEFTSUBITERINCRELVARIABLE:
		case LEFTITERINCCONVARIABLE:
		case LEFTSUBITERINCCONVARIABLE:
		case LEFTITERINCSUBEXP:
		case LEFSUBTITERINCSUBEXP:
			elements = volatileLeftInstElement.getSourceRelations();
			size = volatileLeftInstElement.getSourceRelations().size();
			break;
		case LEFTITEROUTRELVARIABLE:
		case LEFTSUBITEROUTRELVARIABLE:
		case LEFTITEROUTCONVARIABLE:
		case LEFTSUBITEROUTCONVARIABLE:
		case LEFTITEROUTSUBEXP:
		case LEFTSUBITEROUTSUBEXP:
			elements = volatileLeftInstElement.getTargetRelations();
			size = volatileLeftInstElement.getTargetRelations().size();
			break;
		case LEFTITERANYCONVARIABLE:
		case LEFTSUBITERANYVARIABLE:
			elements = volatileLeftInstElement.getTargetRelations();
			elements.addAll(volatileLeftInstElement.getSourceRelations());
			size = volatileLeftInstElement.getTargetRelations().size();
			break;
		case LEFTUNIQUEINCRELVARIABLE:
			if (volatileLeftInstElement instanceof InstPairwiseRel) {
				elements = volatileLeftInstElement.getSourceRelations().get(0)
						.getSourceRelations();
				size = volatileLeftInstElement.getSourceRelations().get(0)
						.getSourceRelations().size();
			} else {
				elements = volatileLeftInstElement.getSourceRelations();
				size = volatileLeftInstElement.getSourceRelations().size();
			}
			volatileInstElement = volatileLeftInstElement;
			break;

		case LEFTUNIQUEINCCONVARIABLE:
			if (volatileLeftInstElement instanceof InstPairwiseRel) {
				elements = volatileLeftInstElement.getSourceRelations().get(0)
						.getSourceRelations();
				size = volatileLeftInstElement.getSourceRelations().size();
			} else {
				elements = volatileLeftInstElement.getSourceRelations().get(0)
						.getSourceRelations();
				size = volatileLeftInstElement.getSourceRelations().get(0)
						.getSourceRelations().size();
			}
			volatileInstElement = volatileLeftInstElement;
			break;
		case LEFTUNIQUEOUTRELVARIABLE:
			if (volatileLeftInstElement instanceof InstPairwiseRel) {
				elements = volatileLeftInstElement.getTargetRelations().get(0)
						.getTargetRelations();
				size = volatileLeftInstElement.getTargetRelations().get(0)
						.getTargetRelations().size();
			} else {
				elements = volatileLeftInstElement.getTargetRelations();
				size = volatileLeftInstElement.getTargetRelations().size();
			}
			volatileInstElement = volatileLeftInstElement;
			break;
		case LEFTUNIQUEOUTCONVARIABLE:
			if (volatileLeftInstElement instanceof InstPairwiseRel) {
				elements = volatileLeftInstElement.getTargetRelations().get(0)
						.getTargetRelations();
				size = volatileLeftInstElement.getTargetRelations().size();
			} else {
				if (volatileLeftInstElement.getTargetRelations().size() == 0)
					break;
				elements = volatileLeftInstElement.getTargetRelations().get(0)
						.getTargetRelations();
				size = volatileLeftInstElement.getTargetRelations().get(0)
						.getTargetRelations().size();
			}
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
		case LEFSUBTITERCONVARIABLE:
			InstElement metaElement = null;
			List<InstElement> parents = null;
			// FIXME include the following validation for other iterative
			// alternatives
			pos--;
			do {
				pos++;
				if (elements.size() == pos)
					break;
				expInstElement = elements.get(pos);
				metaElement = getSemanticExpression().getLeftSemanticElement();
				parents = expInstElement.getTransSupportMetaElement()
						.getTransInstSemanticElement().getParentOpersConcept();
				parents.add(expInstElement.getTransSupportMetaElement()
						.getTransInstSemanticElement());
			} while (!parents.contains(metaElement));

			expAttributeName = getSemanticExpression().getLeftAttributeName();

			break;
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTSUBITERINCRELVARIABLE:
		case LEFTSUBITEROUTRELVARIABLE:
			expInstElement = elements.get(pos);
			expAttributeName = getSemanticExpression().getLeftAttributeName();
			break;

		case LEFTITERINCCONVARIABLE:
		case LEFTSUBITERINCCONVARIABLE:
			expInstElement = elements.get(pos).getSourceRelations().get(0);
			expAttributeName = getSemanticExpression().getLeftAttributeName();
			break;
		case LEFTITEROUTCONVARIABLE:
		case LEFTSUBITEROUTCONVARIABLE:
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
		case RIGHTMODELVARS:
			expInstElement = refas.getVariabilityVertexMC(
					this.getSemanticExpression().getRightSemanticElementId())
					.get(0);
			expAttributeName = getSemanticExpression().getRightAttributeName();
			break;

		case RIGHTVARIABLE:
			expInstElement = volatileRightInstElement;
			expAttributeName = getSemanticExpression().getRightAttributeName();
			break;
		case RIGHTCONCEPTVARIABLE:
			expInstElement = this.getRightElement();
			expAttributeName = getSemanticExpression().getRightAttributeName();
			break;
		case RIGHTUNIQUEOUTCONVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
			if (volatileRightInstElement instanceof InstConcept)
				expInstElement = outExpIndirInstElement;
			else
				expInstElement = outExpDirInstElement;
			expAttributeName = getSemanticExpression().getRightAttributeName();
			break;
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEINCRELVARIABLE:
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
		case LEFTSUBITERANYVARIABLE:
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
			int appInstance = -1;
			if (expressionInstance > -1)
				appInstance = expressionInstance;
			if (iterInstance > -1)
				appInstance = iterInstance;
			String fullIdentifier = expInstElement
					.getInstAttributeFullIdentifier(expAttributeName,
							appInstance);
			if (fullIdentifier == null) {
				System.out.println("NUllID: " + expInstElement.getIdentifier()
						+ " " + expAttributeName);
				return null;
			}
			Identifier identifier = hlclFactory.newIdentifier(fullIdentifier,
					expAttributeName);
			InstAttribute att = expInstElement
					.getInstAttribute(expAttributeName);
			if (expressionInstance > -1)
				att = att.getAdditionalAttribute(expressionInstance);
			ElemAttribute attribute = att.getAttribute();
			// Specifically for Variables
			updateDomain(attribute, expInstElement, identifier);

			return identifier;
		}

		return out;
	}

	// TODO: remove: replace by dynamic implementation (keep only value
	// attribute)
	public static void updateDomain(ElemAttribute attribute,
			InstElement instVertex, Identifier identifier) {
		if (!attribute.getDomainFiltersOwnFields().equals("")
				|| !attribute.getDomainFiltersRelFields().equals("")) {
			String configdomain = "";
			Set<Integer> values = new HashSet<Integer>();
			String[] filters = attribute.getDomainFiltersOwnFields().split(";");
			for (String filter : filters) {
				if (((InstConcept) instVertex).getInstAttribute(filter)
						.toString().length() != 0) {
					InstAttribute obj = ((InstConcept) instVertex)
							.getInstAttribute(filter);
					if (obj.getAsInteger() != 5)
						values.add(obj.getAsInteger());
				}
			}

			filters = attribute.getDomainFiltersRelFields().split(";");
			for (String filter : filters) {
				for (InstElement relation : instVertex.getSourceRelations()) {
					if (((InstPairwiseRel) relation).getInstAttribute(filter) != null)
						values.add(((InstPairwiseRel) relation)
								.getInstAttribute(filter).getAsInteger());
				}
				for (InstElement relation : instVertex.getTargetRelations()) {
					if (((InstPairwiseRel) relation).getInstAttribute(filter) != null)
						values.add(((InstPairwiseRel) relation)
								.getInstAttribute(filter).getAsInteger());
				}
			}

			if (values.size() == 0) {
				if (attribute.getDefaultDomainValueField() != null)
					values.add(((InstConcept) instVertex).getInstAttribute(
							attribute.getDefaultDomainValueField())
							.getAsInteger());
				else
					// FIXME remove me
					values.add(new Integer(0));
			}

			for (Integer value : values) {
				configdomain += value.toString() + ",";
			}
			configdomain = configdomain.substring(0, configdomain.length() - 1);
			identifier.setDomain(DomainParser.parseDomain(configdomain, 0));
		} else if (attribute.getName().equals("SDReqLevel")
				|| attribute.getName().equals("ClaimExpLevel")) {
			String configdomain = "";
			Set<Integer> values = new HashSet<Integer>();
			if (((InstConcept) instVertex).getInstAttribute("Required") != null
					&& ((InstConcept) instVertex)
							.getInstAttribute("ConfigReqLevel") != null
					&& ((InstConcept) instVertex).getInstAttribute("Required")
							.getAsBoolean()
					&& ((InstConcept) instVertex).getInstAttribute(
							"ConfigReqLevel").getAsInteger() != 5)
				values.add(((InstConcept) instVertex).getInstAttribute(
						"ConfigReqLevel").getAsInteger());
			for (InstElement relation : instVertex.getSourceRelations()) {
				// FIXME implement a dynamic definition for this validation
				if (((InstPairwiseRel) relation)
						.getInstAttribute("sourceLevel") != null)
					values.add(((InstPairwiseRel) relation).getInstAttribute(
							"sourceLevel").getAsInteger());
				if (((InstPairwiseRel) relation)
						.getInstAttribute("targetLevel") != null)
					values.add(((InstPairwiseRel) relation).getInstAttribute(
							"targetLevel").getAsInteger());
				if (((InstPairwiseRel) relation).getInstAttribute("level") != null)
					values.add(((InstPairwiseRel) relation).getInstAttribute(
							"level").getAsInteger());
				if (((InstPairwiseRel) relation).getInstAttribute("CLSGLevel") != null)
					values.add(((InstPairwiseRel) relation).getInstAttribute(
							"CLSGLevel").getAsInteger());
			}
			for (InstElement relation : instVertex.getTargetRelations()) {
				// FIXME implement a dynamic definition for this validation
				if (((InstPairwiseRel) relation)
						.getInstAttribute("sourceLevel") != null)
					values.add(((InstPairwiseRel) relation).getInstAttribute(
							"sourceLevel").getAsInteger());
				if (((InstPairwiseRel) relation)
						.getInstAttribute("targetLevel") != null)
					values.add(((InstPairwiseRel) relation).getInstAttribute(
							"targetLevel").getAsInteger());
			}
			if (values.size() == 0) {
				values.add(new Integer(0)); // TODO use value according to
											// MAX/MIN/As close as possible type
											// of SG.
			}
			for (Integer value : values) {
				configdomain += value.toString() + ",";
			}
			configdomain = configdomain.substring(0, configdomain.length() - 1);
			identifier.setDomain(DomainParser.parseDomain(configdomain, 0));
		} else if (attribute.getName().equals("varConfValue")) {
			Boolean isConfigdomain = (Boolean) instVertex.getInstAttribute(
					"isConfDom").getValue();
			String configdomain = instVertex.getInstAttribute("varConfDom")
					.getValue() + "";
			if (configdomain != null && isConfigdomain != null
					&& isConfigdomain && !configdomain.equals(""))
				identifier.setDomain(DomainParser.parseDomain(configdomain, 0));
			else
				identifier.setDomain(DomainParser.parseDomain("0", 0));
		} else if (attribute.getName().equals("value")) {
			String type = (String) instVertex.getInstAttribute("variableType")
					.getValue();

			if (type.equals("Integer")) {
				String domain = (String) instVertex.getInstAttribute("varDom")
						.getValue();
				identifier.setDomain(DomainParser.parseDomain(domain, 0));
			} else if (type.equals("Float")) {
				String domain = transformDomain((String) instVertex
						.getInstAttribute("floatDom").getValue(),
						(int) instVertex.getInstAttribute("floatPrec")
								.getValue());
				identifier.setDomain(DomainParser.parseDomain(domain,
						(int) instVertex.getInstAttribute("floatPrec")
								.getValue()));
			} else if (type.equals("Enumeration")) {
				Object object = instVertex.getInstAttribute("enumType")
						.getValueObject();
				String domain = "";
				if (object != null) {
					@SuppressWarnings("unchecked")
					Collection<InstAttribute> values = (Collection<InstAttribute>) ((InstElement) object)
							.getInstAttribute(SyntaxElement.VAR_METAENUMVALUE)
							.getValue();
					for (InstAttribute value : values) {
						String[] split = ((String) value.getValue()).split("#");
						domain += split[0] + ",";
					}
					domain = domain.substring(0, domain.length() - 1);
				}
				identifier.setDomain(DomainParser.parseDomain(domain, 0));
			}
		} else

		if (attribute.getType().equals("Integer")) {
			if (attribute.getDomain() != null)
				identifier.setDomain(attribute.getDomain());
			else
				identifier.setDomain(new RangeDomain(0, 4, 0));
		} else if (attribute.getType().equals("Float")) {
			if (attribute.getDomain() != null)
				identifier.setDomain(attribute.getDomain());
			else
				identifier.setDomain(new RangeDomain(0, 4, 0));
		} else if (attribute.getType().equals("String")) {
			if (attribute.getDomain() != null)
				identifier.setDomain(attribute.getDomain());
		}
	}

	public static String transformDomain(String value, int value2) {
		String toSplit = value.replace(',', '-');
		String[] elem = toSplit.split("-");
		String[] tmp = new String[elem.length];
		String out = "";

		for (int i = 0; i < elem.length; i++) {
			tmp[i] = (int) (Float.parseFloat(elem[i]) * Math.pow(10, value2))
					+ "";
		}
		out = tmp[0];
		toSplit = value.replace('.', '0');
		elem = value.split("\\w+");
		for (int i = 1; i < tmp.length; i++) {
			out += elem[i] + tmp[i];
		}
		return out;
	}

	private List<IntExpression> expressionTerms(int pos, int leftIterInstance,
			int leftIterInstances) throws FunctionalException {
		List<IntExpression> out = new ArrayList<IntExpression>();

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
		List<InstElement> elements = null;
		switch (expressionVertexTypes.get(0)) {
		case LEFTITERCONCEPTVARIABLE:
		case LEFSUBTITERCONVARIABLE:
			elements = new ArrayList<InstElement>();
			String elemetType = this.getSemanticExpression()
					.getLeftSemanticElement().getIdentifier();
			elements.addAll(refas.getVariabilityVertexMC(elemetType));
			break;
		case LEFTITERINCRELVARIABLE:
		case LEFTSUBITERINCRELVARIABLE:
		case LEFTITERINCCONVARIABLE:
		case LEFTSUBITERINCCONVARIABLE:
		case LEFTITERINCSUBEXP:
		case LEFSUBTITERINCSUBEXP:
			elements = volatileLeftInstElement.getSourceRelations();
			break;
		case LEFTITEROUTRELVARIABLE:
		case LEFTSUBITEROUTRELVARIABLE:
		case LEFTITEROUTCONVARIABLE:
		case LEFTSUBITEROUTCONVARIABLE:
		case LEFTITEROUTSUBEXP:
		case LEFTSUBITEROUTSUBEXP:
			elements = volatileLeftInstElement.getTargetRelations();
			break;
		case LEFTITERANYCONVARIABLE:
		case LEFTSUBITERANYVARIABLE:
			elements = volatileLeftInstElement.getTargetRelations();
			elements.addAll(volatileLeftInstElement.getSourceRelations());
			break;
		case LEFTVARIABLE:
			elements = new ArrayList<InstElement>();
			elements.add(volatileLeftInstElement);
			break;
		case LEFTUNIQUEINCRELVARIABLE:
			if (volatileLeftInstElement instanceof InstPairwiseRel) {
				elements = volatileLeftInstElement.getSourceRelations().get(0)
						.getSourceRelations();
				size = volatileLeftInstElement.getSourceRelations().get(0)
						.getSourceRelations().size();
			} else {
				elements = volatileLeftInstElement.getSourceRelations();
				size = volatileLeftInstElement.getSourceRelations().size();
			}
			break;
		case LEFTUNIQUEINCCONVARIABLE:
			if (volatileLeftInstElement instanceof InstPairwiseRel) {
				elements = volatileLeftInstElement.getSourceRelations();
			} else {
				elements = volatileLeftInstElement.getSourceRelations().get(0)
						.getSourceRelations();
			}
			break;
		case LEFTUNIQUEOUTRELVARIABLE:
			if (volatileLeftInstElement instanceof InstPairwiseRel) {
				elements = volatileLeftInstElement.getTargetRelations().get(0)
						.getTargetRelations();
			} else {
				elements = volatileLeftInstElement.getTargetRelations();
			}
			break;
		case LEFTUNIQUEOUTCONVARIABLE:
			if (volatileLeftInstElement instanceof InstPairwiseRel) {
				elements = volatileLeftInstElement.getTargetRelations();
			} else {
				if (volatileLeftInstElement.getTargetRelations().size() == 0)
					break;
				elements = volatileLeftInstElement.getTargetRelations().get(0)
						.getTargetRelations();
			}
			break;
		}

		for (ExpressionVertexType expressionType : expressionVertexTypes) {
			switch (expressionType) {
			case LEFTITERCONCEPTVARIABLE:
				String elemetType = getSemanticExpression()
						.getLeftSemanticElement().getIdentifier();
				leftIterInstances = 1;
				InstElement leftInstElement = null;
				if (refas.getVariabilityVertexMC(elemetType).size() > 0) {
					leftInstElement = refas.getVariabilityVertexMC(elemetType)
							.get(pos);
					leftIterInstances = leftInstElement.getInstances(refas);
				}
				leftIterInstance = -1;
			case LEFTITEROUTRELVARIABLE:
			case LEFTITERINCRELVARIABLE:
			case LEFTITEROUTCONVARIABLE:
			case LEFTITERINCCONVARIABLE:
			case LEFTITERINCSUBEXP:
			case LEFTITEROUTSUBEXP:
				iter = true;
				pos = -1;
				size = getSize(expressionType);
				if (pos >= size - 1)
					iter = false;
				break;

			case LEFSUBTITERCONVARIABLE:
			case LEFTSUBITERINCCONVARIABLE:
			case LEFTSUBITEROUTCONVARIABLE:
			case LEFTSUBITERINCRELVARIABLE:
			case LEFTSUBITEROUTRELVARIABLE:
				iter = true;
			case LEFTVARIABLE:
			case LEFTUNIQUEOUTRELVARIABLE:
			case LEFTUNIQUEINCRELVARIABLE:
			case LEFTUNIQUEOUTCONVARIABLE:
			case LEFTUNIQUEINCCONVARIABLE:
				// size = getSize(expressionType);
				out.add(getIdentifier(expressionType, pos, leftIterInstance));
				if (pos >= size - 1)
					iter = false;
				break;
			case RIGHTVARIABLE:
			case RIGHTUNIQUEOUTRELVARIABLE:
			case RIGHTUNIQUEINCRELVARIABLE:
			case RIGHTUNIQUEOUTCONVARIABLE:
			case RIGHTUNIQUEINCCONVARIABLE:
				if (iter) {
					//elemetType = getSemanticExpression()
					//		.getLeftSemanticElement().getIdentifier();
					if (pos == -1)
						leftInstElement = elements.get(0);
					else
						leftInstElement = elements.get(pos);
					if (leftIterInstance + 2 < leftInstElement
							.getInstances(refas) && !iterInstance) {
						out.add(leftInstanceExpression.createExpression(pos,
								leftIterInstance + 1));
					} else if (pos + 1 < size)
						out.add(leftInstanceExpression.createExpression(
								pos + 1, -1));
				} else {
					if (getSemanticExpression().getLeftSemanticElement() != null
							&& elements != null && elements.size() > 0) {
						leftInstElement = elements.get(pos);
						if (leftIterInstance + 2 < leftInstElement
								.getInstances(refas) && !iterInstance) {
							out.add(leftInstanceExpression.createExpression(
									pos, leftIterInstance + 1));
							break;
						}
					}
				}
				if (pos == -1 && !iter)
					// FIXME support other types of default values for iter
					// expressions
					if (getSemanticExpression().getLeftSemanticExpression()
							.getRightAttributeName() != null) {
						out.add(hlclFactory.newIdentifier(this
								.getRightElement().getIdentifier()
								+ getSemanticExpression()
										.getLeftSemanticExpression()
										.getRightAttributeName()));
					} else {
						out.add(hlclFactory.number(getSemanticExpression()
								.getLeftSemanticExpression().getRightNumber()));
					}
				if (pos == -1 || !iter)
					out.add(getIdentifier(expressionType, pos, -1));
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
				out.add(getIdentifier(expressionType, 0, leftIterInstance));
				// out.add(leftInstanceExpression.createExpression(0));
				break;

			case LEFSUBTITERINCSUBEXP:
			case LEFTSUBITEROUTSUBEXP:
				iter = true;
				getIdentifier(expressionType, pos, leftIterInstance);
				if (pos >= size - 1)
					iter = false;
				out.add(leftInstanceExpression.createExpression(pos,
						leftIterInstance));
				break;
			case LEFTSUBEXPRESSION:
				out.add(leftInstanceExpression.createExpression(0,
						leftIterInstance));
				break;
			case RIGHTSUBEXPRESSION:
				if (iter) {
					if (pos == -1)
						leftInstElement = elements.get(0);
					else
						leftInstElement = elements.get(pos);
					if (leftIterInstance + 2 < leftInstElement
							.getInstances(refas) && !iterInstance) {
						out.add(leftInstanceExpression.createExpression(pos,
								leftIterInstance + 1));
						break;
					} else if (pos + 1 < size)
						out.add(leftInstanceExpression.createExpression(
								pos + 1, -1));
				} else {
					if (getSemanticExpression().getLeftSemanticElement() != null
							&& elements != null && elements.size() > 0) {
						leftInstElement = elements.get(pos);
						if (leftIterInstance + 2 < leftInstElement
								.getInstances(refas) && !iterInstance) {
							out.add(leftInstanceExpression.createExpression(
									pos, leftIterInstance + 1));
							break;
						}
					}
				}
				if (pos == -1 && !iter)
					// FIXME support other types of default values for iter
					// expressions
					if (getSemanticExpression().getLeftSemanticExpression()
							.getRightAttributeName() != null) {
						out.add(hlclFactory.newIdentifier(this
								.getRightElement().getIdentifier()
								+ getSemanticExpression()
										.getLeftSemanticExpression()
										.getRightAttributeName()));
					} else {
						out.add(hlclFactory.number(getSemanticExpression()
								.getLeftSemanticExpression().getRightNumber()));
					}

				if (pos == -1 || !iter)
					out.add(rightInstanceExpression.createExpression(0, -1));
				break;
			case RIGHTMODELVARS:
			case RIGHTCONCEPTVARIABLE:
				if (iter) {
					if (leftInstanceExpression == null)
						System.out.println("leftInstanceExpression null");
					if (pos == -1)
						leftInstElement = elements.get(0);
					else
						leftInstElement = elements.get(pos);
					if (leftIterInstance + 2 < leftInstElement
							.getInstances(refas) && !iterInstance) {
						out.add(leftInstanceExpression.createExpression(pos,
								leftIterInstance + 1));
						break;
					} else if (pos + 1 < size)
						out.add(leftInstanceExpression.createExpression(
								pos + 1, -1));
				} else {
					if (getSemanticExpression().getLeftSemanticElement() != null
							&& elements != null && elements.size() > 0) {
						if (pos == -1)
							leftInstElement = elements.get(0);
						else
							leftInstElement = elements.get(pos);
						if (leftIterInstance + 2 < leftInstElement
								.getInstances(refas) && !iterInstance) {
							out.add(leftInstanceExpression.createExpression(
									pos, leftIterInstance + 1));
							break;
						}
					}
				}
				if (pos == -1 && !iter)
					// FIXME support other types of default values for iter
					// expressions
					if (getSemanticExpression().getLeftSemanticExpression()
							.getRightAttributeName() != null) {
						out.add(hlclFactory.newIdentifier(this
								.getRightElement().getIdentifier()
								+ getSemanticExpression()
										.getLeftSemanticExpression()
										.getRightAttributeName()));
					} else {
						out.add(hlclFactory.number(getSemanticExpression()
								.getLeftSemanticExpression().getRightNumber()));
					}
				if (pos == -1 || !iter)
					out.add(getIdentifier(expressionType, pos, leftIterInstance));
				break;

			case RIGHTNUMERICVALUE:
				if (iter) {
					if (pos < size) {
						if (pos == -1)
							leftInstElement = elements.get(0);
						else
							leftInstElement = elements.get(pos);
						if (leftIterInstance + 2 < leftInstElement
								.getInstances(refas) && !iterInstance) {
							out.add(leftInstanceExpression.createExpression(
									pos, leftIterInstance + 1));
							break;
						} else if (pos + 1 < size)
							out.add(leftInstanceExpression.createExpression(
									pos + 1, -1));
					}
				} else {
					if (getSemanticExpression().getLeftSemanticElement() != null
							&& elements != null && elements.size() > 0) {
						leftInstElement = elements.get(pos);
						if (leftIterInstance + 2 < leftInstElement
								.getInstances(refas) && !iterInstance) {
							out.add(leftInstanceExpression.createExpression(
									pos, leftIterInstance + 1));
							break;
						}
					}
				}
				if (pos == -1 || !iter)
					out.add(hlclFactory.number(getSemanticExpression()
							.getRightNumber()));
				if (pos == -1 && !iter)
					// FIXME support other types of default values for iter
					// expressions
					if (getSemanticExpression().getLeftSemanticExpression()
							.getRightAttributeName() != null) {
						out.add(hlclFactory.newIdentifier(this
								.getRightElement().getIdentifier()
								+ getSemanticExpression()
										.getLeftSemanticExpression()
										.getRightAttributeName()));
					} else {
						out.add(hlclFactory.number(getSemanticExpression()
								.getLeftSemanticExpression().getRightNumber()));
					}
				break;
			case RIGHTNUMERICFLOATVALUE:
				if (iter) {
					if (pos < size) {
						if (pos == -1)
							leftInstElement = elements.get(0);
						else
							leftInstElement = elements.get(pos);
						if (leftIterInstance + 2 < leftInstElement
								.getInstances(refas) && !iterInstance) {
							out.add(leftInstanceExpression.createExpression(
									pos, leftIterInstance + 1));
							break;
						} else if (pos + 1 < size)
							out.add(leftInstanceExpression.createExpression(
									pos + 1, -1));
					}
				} else {
					if (getSemanticExpression().getLeftSemanticElement() != null
							&& elements != null && elements.size() > 0) {
						leftInstElement = elements.get(pos);
						if (leftIterInstance + 2 < leftInstElement
								.getInstances(refas) && !iterInstance) {
							out.add(leftInstanceExpression.createExpression(
									pos, leftIterInstance + 1));
							break;
						}
					}
				}
				if (pos == -1 || !iter)
					out.add(hlclFactory.floatNumber(getSemanticExpression()
							.getRightFloatNumber()));
				if (pos == -1 && !iter)
					// FIXME support other types of default values for iter
					// expressions
					if (getSemanticExpression().getLeftSemanticExpression()
							.getRightAttributeName() != null) {
						out.add(hlclFactory.newIdentifier(this
								.getRightElement().getIdentifier()
								+ getSemanticExpression()
										.getLeftSemanticExpression()
										.getRightAttributeName()));
					} else {
						out.add(hlclFactory.floatNumber(getSemanticExpression()
								.getLeftSemanticExpression()
								.getRightFloatNumber()));
					}

				break;
			case RIGHTVARIABLEVALUE:
			case RIGHTSTRINGVALUE:
				if (iter) {
					if (pos < size) {
						if (pos == -1)
							leftInstElement = elements.get(0);
						else
							leftInstElement = elements.get(pos);
						if (leftIterInstance + 2 < leftInstElement
								.getInstances(refas) && !iterInstance) {
							out.add(leftInstanceExpression.createExpression(
									pos, leftIterInstance + 1));
							break;
						} else if (pos + 1 < size)
							out.add(leftInstanceExpression.createExpression(
									pos + 1, -1));
					}
				} else {
					if (getSemanticExpression().getLeftSemanticElement() != null
							&& elements != null && elements.size() > 0) {
						leftInstElement = elements.get(pos);
						if (leftIterInstance + 2 < leftInstElement
								.getInstances(refas) && !iterInstance) {
							out.add(leftInstanceExpression.createExpression(
									pos, leftIterInstance + 1));
							break;
						}
					}
				}
				if (pos == -1 || !iter)
					out.add(hlclFactory
							.number(getVariableIntValue(expressionType)));
				if (pos == -1 && !iter)
					// FIXME support other types of default values for iter
					// expressions
					if (getSemanticExpression().getLeftSemanticExpression()
							.getRightAttributeName() != null) {
						out.add(hlclFactory.newIdentifier(this
								.getRightElement().getIdentifier()
								+ getSemanticExpression()
										.getLeftSemanticExpression()
										.getRightAttributeName()));
					} else {
						out.add(hlclFactory.number(getSemanticExpression()
								.getLeftSemanticExpression().getRightNumber()));
					}
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
		int floatPrec = 0;
		switch (expressionVertexType) {
		case LEFTVARIABLEVALUE:
			value = leftValue;
			valueType = (String) this.volatileLeftInstElement.getInstAttribute(
					"variableType").getValue();
			// if (this.volatileLeftInstElement.getInstAttribute("floatPrec")
			// .getValue() != null)
			// floatPrec = (int) this.volatileLeftInstElement
			// .getInstAttribute("floatPrec").getValue();
			break;
		case RIGHTVARIABLEVALUE:
			value = rightValue;
			valueType = (String) this.volatileRightInstElement
					.getInstAttribute("variableType").getValue();
			// if (this.volatileRightInstElement.getInstAttribute("floatPrec")
			// .getValue() != null)
			// floatPrec = (int) this.volatileRightInstElement
			// .getInstAttribute("floatPrec").getValue();

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
		case "Float":
			return (int) (Integer.parseInt(value) * Math.pow(10, floatPrec));
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
			OpersExprType semanticExpressionType, String id, int instance) {
		if (type == ExpressionVertexType.LEFTSUBEXPRESSION)
			this.leftInstanceExpression = new ModelExpr(true, id, false,
					instance);
		if (type == ExpressionVertexType.LEFTNUMERICVALUE)
			this.leftInstanceExpression = new ModelExpr(refas, true,
					new OpersExpr(id, semanticExpressionType), iterInstance);
		getSemanticExpression().setLeftExpressionType(type);
	}

	public void setLeftElement(InstElement instElement, String attribute) {
		getSemanticExpression().setLeftExpressionType(
				ExpressionVertexType.LEFTVARIABLE);
		this.volatileLeftInstElement = instElement;
		getSemanticExpression().setLeftAttributeName(attribute);
	}

	public void setRightInstanceExpression(ExpressionVertexType type,
			OpersExprType semanticExpressionType, String id, int instance) {
		if (type == ExpressionVertexType.RIGHTSUBEXPRESSION)
			this.rightInstanceExpression = new ModelExpr(true, id, false,
					instance);
		if (type == ExpressionVertexType.RIGHTNUMERICVALUE)
			this.rightInstanceExpression = new ModelExpr(refas, true,
					new OpersExpr(id, semanticExpressionType), iterInstance);
		if (type == ExpressionVertexType.RIGHTNUMERICFLOATVALUE)
			this.rightInstanceExpression = new ModelExpr(refas, true,
					new OpersExpr(id, semanticExpressionType), iterInstance);
		getSemanticExpression().setRightExpressionType(type);
	}

	public void setRightElement(InstElement instElement, String attribute) {
		getSemanticExpression().setRightExpressionType(
				ExpressionVertexType.RIGHTVARIABLE);
		this.volatileLeftInstElement = instElement;
		getSemanticExpression().setRightAttributeName(attribute);
	}

	public float getLeftNumber() {
		return getSemanticExpression().getLeftNumber();
	}

	public float getRightNumber() {
		return getSemanticExpression().getRightNumber();
	}

	public void setLeftNumber(int number) {
		getSemanticExpression().setLeftNumber(number);
	}

	public void setRightNumber(int number) {
		getSemanticExpression().setRightNumber(number);
	}

	public void setRightNumber(float number) {
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
						.getInstAttribute("userId").getValue()
						: getLeftElement().getIdentifier())
						+ "_" + getLeftAttributeName();
			break;
		case RIGHTVARIABLE:
			if (getRightElement() != null)
				return (displayName ? (String) getRightElement()
						.getInstAttribute("userId").getValue()
						: getRightElement().getIdentifier())
						+ "_" + getRightAttributeName();
			break;
		case LEFTVARIABLEVALUE:
			if (getLeftElement() != null)
				return (displayName ? (String) getLeftElement()
						.getInstAttribute("userId").getValue()
						: getLeftElement().getIdentifier())
						+ "_" + leftValue;
			break;
		case RIGHTVARIABLEVALUE:
			if (getRightElement() != null)
				return (displayName ? (String) getRightElement()
						.getInstAttribute("userId").getValue()
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

	@Override
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
				out += this.getLeftInstElementId() + ":";
				out += getLeftAttributeName() + " ";
				break;
			case RIGHTVARIABLE:
				out += getRightAttributeName() + " ";

				break;
			case LEFTSUBEXPRESSION:
				out += "(" + leftInstanceExpression.expressionStructure() + ")";
				break;
			case RIGHTSUBEXPRESSION:
				out += "(" + rightInstanceExpression.expressionStructure()
						+ ")";
				break;
			case LEFTBOOLEANEXPRESSION:
				break;
			case LEFTCONCEPTVARIABLE:
				out += this.getLeftInstElementId() + ":";
				out += getLeftAttributeName() + " ";
				break;
			case LEFTITERANYCONVARIABLE:
				out += getLeftAttributeName();
				break;
			case LEFTSUBITERANYVARIABLE:
				out += getLeftAttributeName();
				break;
			case LEFTITERANYRELVARIABLE:
				out += getLeftAttributeName();
				break;
			case LEFTITERCONCEPTVARIABLE:
				out += getLeftAttributeName();
				break;
			case LEFSUBTITERCONVARIABLE:
				out += getLeftAttributeName();
				break;
			case LEFTSUBITERINCCONVARIABLE:
				out += getLeftAttributeName();
				break;
			case LEFTITERINCCONVARIABLE:
				if (leftInstanceExpression != null)
					out += "(" + leftInstanceExpression.expressionStructure()
							+ ")";
				break;
			case LEFTSUBITERINCRELVARIABLE:
				out += this.getLeftInstElementId() + ":";
				out += getLeftAttributeName();
				break;
			case LEFTITERINCRELVARIABLE:
				out += this.getLeftInstElementId() + ":";
				out += getLeftAttributeName();
				break;
			case LEFTSUBITEROUTCONVARIABLE:
				out += this.getLeftInstElementId() + ":";
				out += getLeftAttributeName();
				break;
			case LEFTITEROUTCONVARIABLE:
				out += getLeftAttributeName();
				break;
			case LEFTSUBITEROUTRELVARIABLE:
				out += getLeftAttributeName();
				break;
			case LEFTITEROUTRELVARIABLE:
				out += getLeftAttributeName();
				break;
			case LEFTMODELVARS:
				break;
			case LEFTNUMERICVALUE:
				break;
			case LEFTSTRINGVALUE:
				break;
			case LEFTUNIQUEINCCONVARIABLE:
				out += getLeftAttributeName();
				break;
			case LEFTUNIQUEINCRELVARIABLE:
				out += getLeftAttributeName();
				break;
			case LEFTUNIQUEOUTCONVARIABLE:
				out += getLeftAttributeName();
				break;
			case LEFTUNIQUEOUTRELVARIABLE:
				out += getLeftAttributeName();
				break;
			case RIGHTBOOLEANEXPRESSION:
				break;
			case RIGHTCONCEPTVARIABLE:
				out += this.getRightInstElementId() + ":";
				out += getRightAttributeName();
				break;
			case RIGHTMODELVARS:
				out += this.getRightInstElementId() + ":";
				out += getRightAttributeName();
				break;
			case RIGHTNUMERICVALUE:
				break;
			case RIGHTSTRINGVALUE:
				break;
			case RIGHTUNIQUEINCCONVARIABLE:

				out += this.getRightInstElementId() + ":";
				out += getRightAttributeName();
				break;
			case RIGHTUNIQUEINCRELVARIABLE:

				out += this.getRightInstElementId() + ":";
				out += getRightAttributeName();
				break;
			case RIGHTUNIQUEOUTCONVARIABLE:

				out += this.getRightInstElementId() + ":";
				out += getRightAttributeName();
				break;
			case RIGHTUNIQUEOUTRELVARIABLE:

				out += this.getRightInstElementId() + ":";
				out += getRightAttributeName();
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

	public void createFromSemanticExpression(InstElement instElement, int pos,
			int instanceExpression, boolean iterExpression, int leftIterInstance) {
		if (instElement.getSourceRelations().size() != 0)
			this.setSourceInstanceId(instElement.getSourceRelations().get(0)
					.getIdentifier());
		if (instElement.getTargetRelations().size() != 0)
			this.setTargetInstanceId(instElement.getTargetRelations().get(0)
					.getIdentifier());
		this.setElementInstanceId(instElement.getIdentifier());
		this.expressionInstance = instanceExpression;
		ExpressionVertexType type = volatileSemanticExpression
				.getLeftExpressionType();

		switch (type) {
		case LEFTSUBEXPRESSION:
			this.volatileLeftInstElement = instElement;
			leftInstanceExpression = new ModelExpr(refas, false,
					volatileSemanticExpression.getLeftSemanticExpression(),
					iterInstance);
			leftInstanceExpression.createFromSemanticExpression(instElement,
					pos, instanceExpression, iterExpression, leftIterInstance);
			break;
		case LEFTNUMERICVALUE:
			this.leftValue = volatileSemanticExpression.getLeftNumber() + "";
			this.volatileLeftInstElement = instElement;
			break;
		case LEFTUNIQUEOUTRELVARIABLE: {
			this.volatileLeftInstElement = instElement;
			InstElement leftInstElement;
			if (instElement instanceof InstPairwiseRel)
				leftInstElement = instElement.getTargetRelations().get(pos)
						.getTargetRelations().get(0);
			else
				leftInstElement = instElement.getTargetRelations().get(pos);
			if (leftIterInstance + 1 < leftInstElement.getInstances(refas)
					&& !iterExpression) {

				leftInstanceExpression = new ModelExpr(refas, false,
						this.getSemanticExpression(), iterInstance);
				leftInstanceExpression.createFromSemanticExpression(
						instElement, pos, instanceExpression, iterExpression,
						leftIterInstance + 1);
			}

		}
			break;
		case LEFTUNIQUEINCRELVARIABLE: {
			this.volatileLeftInstElement = instElement;
			InstElement leftInstElement;
			if (instElement instanceof InstPairwiseRel)
				leftInstElement = instElement.getSourceRelations().get(pos)
						.getSourceRelations().get(0);
			else
				leftInstElement = instElement.getSourceRelations().get(pos);
			if (leftIterInstance + 1 < leftInstElement.getInstances(refas)
					&& !iterExpression) {

				leftInstanceExpression = new ModelExpr(refas, false,
						this.getSemanticExpression(), iterInstance);
				leftInstanceExpression.createFromSemanticExpression(
						instElement, pos, instanceExpression, iterExpression,
						leftIterInstance + 1);
			}

		}
			break;
		case LEFTUNIQUEOUTCONVARIABLE: {
			this.volatileLeftInstElement = instElement;
			InstElement leftInstElement;
			if (instElement.getTargetRelations().size() == 0)
				break;
			if (instElement instanceof InstPairwiseRel)
				leftInstElement = instElement.getTargetRelations().get(pos);
			else
				leftInstElement = instElement.getTargetRelations().get(pos)
						.getTargetRelations().get(0);
			if (leftIterInstance + 1 < leftInstElement.getInstances(refas)
					&& !iterExpression) {

				leftInstanceExpression = new ModelExpr(refas, false,
						this.getSemanticExpression(), iterInstance);
				leftInstanceExpression.createFromSemanticExpression(
						instElement, pos, instanceExpression, iterExpression,
						leftIterInstance + 1);
			}

		}
			break;
		case LEFTUNIQUEINCCONVARIABLE: {
			this.volatileLeftInstElement = instElement;
			InstElement leftInstElement;
			if (instElement.getSourceRelations().size() == 0)
				break;
			if (instElement instanceof InstPairwiseRel)
				leftInstElement = instElement.getSourceRelations().get(pos);
			else
				leftInstElement = instElement.getSourceRelations().get(pos)
						.getSourceRelations().get(0);
			if (leftIterInstance + 1 < leftInstElement.getInstances(refas)
					&& !iterExpression) {
				leftInstanceExpression = new ModelExpr(refas, false,
						this.getSemanticExpression(), iterInstance);
				leftInstanceExpression.createFromSemanticExpression(
						instElement, pos, instanceExpression, iterExpression,
						leftIterInstance + 1);
			}
		}
			break;
		case LEFTVARIABLEVALUE:
		case LEFTSTRINGVALUE:
			this.leftValue = volatileSemanticExpression.getLeftString();

		case LEFTVARIABLE:
		case LEFTCONCEPTVARIABLE: // TODO verify
		{
			this.volatileLeftInstElement = instElement;
			if (leftIterInstance + 1 < instElement.getInstances(refas)
					&& !iterExpression) {

				leftInstanceExpression = new ModelExpr(refas, false,
						this.getSemanticExpression(), iterInstance);
				leftInstanceExpression.createFromSemanticExpression(
						instElement, pos, instanceExpression, iterExpression,
						leftIterInstance + 1);
			}

		}
			break;
		case LEFTITERCONCEPTVARIABLE:
			this.volatileLeftInstElement = instElement;
			String elemetType = this.getSemanticExpression()
					.getLeftSemanticElement().getIdentifier();
			if (pos < refas.getVariabilityVertexMC(elemetType).size()) {
				leftInstanceExpression = new ModelExpr(refas, false, this
						.getSemanticExpression().getLeftSemanticExpression(),
						iterInstance);
				InstElement leftInstElement = refas.getVariabilityVertexMC(
						elemetType).get(pos);
				if (leftIterInstance + 1 < leftInstElement.getInstances(refas)
						&& !iterExpression)
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos, instanceExpression,
							iterExpression, leftIterInstance + 1);
				else if (pos < refas.getVariabilityVertexMC(elemetType).size())
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos + 1, instanceExpression,
							iterExpression, -1);
			}
			break;
		case LEFSUBTITERCONVARIABLE:
			this.volatileLeftInstElement = instElement;
			elemetType = this.getSemanticExpression().getLeftSemanticElement()
					.getIdentifier();
			if (pos < refas.getVariabilityVertexMC(elemetType).size()) {
				InstElement leftInstElement = refas.getVariabilityVertexMC(
						elemetType).get(pos);
				leftInstanceExpression = new ModelExpr(refas, false,
						this.getSemanticExpression(), iterInstance);
				if (leftIterInstance + 1 < leftInstElement.getInstances(refas)
						&& !iterExpression)
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos, instanceExpression,
							iterExpression, leftIterInstance + 1);
				else if (pos < refas.getVariabilityVertexMC(elemetType).size())
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos + 1, instanceExpression,
							iterExpression, -1);
			}
			break;
		case LEFTITERINCRELVARIABLE:
		case LEFTITERINCCONVARIABLE:
		case LEFTITERINCSUBEXP:
			this.volatileLeftInstElement = instElement;
			if (pos < instElement.getSourceRelations().size()) {
				InstElement leftInstElement = null;
				if (type.equals(ExpressionVertexType.LEFTITERINCCONVARIABLE))
					leftInstElement = instElement.getSourceRelations().get(pos)
							.getSourceRelations().get(0);
				if (type.equals(ExpressionVertexType.LEFTITERINCRELVARIABLE))
					leftInstElement = instElement.getSourceRelations().get(pos);
				leftInstanceExpression = new ModelExpr(refas, false, this
						.getSemanticExpression().getLeftSemanticExpression(),
						iterInstance);

				if (leftIterInstance + 1 < leftInstElement.getInstances(refas)
						&& !iterExpression)
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos, instanceExpression,
							iterExpression, leftIterInstance + 1);

				else if (pos < instElement.getSourceRelations().size())
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos + 1, instanceExpression,
							iterExpression, -1);
			}
			break;
		case LEFTSUBITERINCCONVARIABLE:
		case LEFTSUBITERINCRELVARIABLE:
			this.volatileLeftInstElement = instElement;
			if (pos < instElement.getSourceRelations().size()) {
				InstElement leftInstElement = null;
				if (type.equals(ExpressionVertexType.LEFTSUBITERINCCONVARIABLE))
					leftInstElement = instElement.getSourceRelations().get(pos)
							.getSourceRelations().get(0);
				if (type.equals(ExpressionVertexType.LEFTSUBITERINCRELVARIABLE))
					leftInstElement = instElement.getSourceRelations().get(pos);
				leftInstanceExpression = new ModelExpr(refas, false,
						this.getSemanticExpression(), iterInstance);
				if (leftIterInstance + 1 < leftInstElement.getInstances(refas)
						&& !iterExpression)
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos, instanceExpression,
							iterExpression, leftIterInstance + 1);

				else if (pos < instElement.getSourceRelations().size())
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos + 1, instanceExpression,
							iterExpression, -1);
			}
			break;
		case LEFSUBTITERINCSUBEXP:
			this.volatileLeftInstElement = instElement;
			if (pos < instElement.getSourceRelations().size()) {
				leftInstanceExpression = new ModelExpr(refas, false,
						volatileSemanticExpression, iterInstance);
			} else {
				leftInstanceExpression = new ModelExpr(refas, false,
						volatileSemanticExpression.getLeftSemanticExpression(),
						iterInstance);
			}
			if (pos < instElement.getSourceRelations().size()) {
				InstElement leftInstElement = instElement.getSourceRelations()
						.get(pos).getSourceRelations().get(0);
				if (leftIterInstance + 1 < leftInstElement.getInstances(refas)
						&& !iterExpression)
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos, instanceExpression,
							iterExpression, leftIterInstance + 1);

				else if (pos < instElement.getSourceRelations().size())
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos + 1, instanceExpression,
							iterExpression, -1);
			}
			break;
		case LEFTSUBITEROUTSUBEXP:
			this.volatileLeftInstElement = instElement;
			if (pos < instElement.getTargetRelations().size()) {
				leftInstanceExpression = new ModelExpr(refas, false,
						volatileSemanticExpression, iterInstance);
			} else {
				leftInstanceExpression = new ModelExpr(refas, false,
						volatileSemanticExpression.getLeftSemanticExpression(),
						iterInstance);
			}
			if (pos < instElement.getTargetRelations().size()) {
				InstElement leftInstElement = instElement.getSourceRelations()
						.get(pos).getSourceRelations().get(0);
				if (leftIterInstance + 1 < leftInstElement.getInstances(refas)
						&& !iterExpression)
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos, instanceExpression,
							iterExpression, leftIterInstance + 1);

				else if (pos < instElement.getTargetRelations().size())
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos + 1, instanceExpression,
							iterExpression, -1);
			}
			break;
		case LEFTITEROUTCONVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITEROUTSUBEXP:
			this.volatileLeftInstElement = instElement;
			if (pos < instElement.getTargetRelations().size()) {
				InstElement leftInstElement = instElement.getTargetRelations()
						.get(pos).getTargetRelations().get(0);
				leftInstanceExpression = new ModelExpr(refas, false, this
						.getSemanticExpression().getLeftSemanticExpression(),
						iterInstance);
				if (leftIterInstance + 1 < leftInstElement.getInstances(refas)
						&& !iterExpression)
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos, instanceExpression,
							iterExpression, leftIterInstance + 1);

				else if (pos < instElement.getTargetRelations().size())
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos + 1, instanceExpression,
							iterExpression, -1);
			}
			break;
		case LEFTSUBITEROUTCONVARIABLE:
		case LEFTSUBITEROUTRELVARIABLE:
			this.volatileLeftInstElement = instElement;
			if (pos < instElement.getTargetRelations().size()) {
				InstElement leftInstElement = instElement.getTargetRelations()
						.get(pos).getTargetRelations().get(0);
				leftInstanceExpression = new ModelExpr(refas, false,
						this.getSemanticExpression(), iterInstance);
				if (leftIterInstance + 1 < leftInstElement.getInstances(refas)
						&& !iterExpression)
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos, instanceExpression,
							iterExpression, leftIterInstance + 1);

				else if (pos < instElement.getTargetRelations().size())
					leftInstanceExpression.createFromSemanticExpression(
							instElement, pos + 1, instanceExpression,
							iterExpression, -1);
			}
			break;
		case LEFTBOOLEANEXPRESSION:
			break;
		case LEFTITERANYCONVARIABLE:
			break;
		case LEFTSUBITERANYVARIABLE:
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
			System.out.println("null right type");
		}

		switch (type) {
		case RIGHTSUBEXPRESSION:
			rightInstanceExpression = new ModelExpr(refas, false,
					volatileSemanticExpression.getRightSemanticExpression(),
					iterInstance);
			if (instElement != null)
				rightInstanceExpression.createFromSemanticExpression(
						instElement, pos, instanceExpression, iterExpression,
						-1);
			break;

		case RIGHTNUMERICVALUE:
			this.rightValue = volatileSemanticExpression.getRightNumber() + "";
			this.volatileRightInstElement = instElement;
			break;
		case RIGHTNUMERICFLOATVALUE:
			this.rightValue = volatileSemanticExpression.getRightFloatNumber()
					+ "";
			this.volatileRightInstElement = instElement;
			break;
		case RIGHTVARIABLEVALUE:
		case RIGHTSTRINGVALUE:
			this.rightValue = volatileSemanticExpression.getRightString();
		case RIGHTMODELVARS:
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
		case LEFTSUBITERANYVARIABLE:
			break;
		case LEFTITERANYRELVARIABLE:
			break;
		case LEFTITERCONCEPTVARIABLE:
			break;
		case LEFSUBTITERCONVARIABLE:
			break;
		case LEFTSUBITERINCCONVARIABLE:
			break;
		case LEFTITERINCCONVARIABLE:
			break;
		case LEFTSUBITERINCRELVARIABLE:
			break;
		case LEFTITERINCRELVARIABLE:
			break;
		case LEFTSUBITEROUTCONVARIABLE:
			break;
		case LEFTITEROUTCONVARIABLE:
			break;
		case LEFTSUBITEROUTRELVARIABLE:
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
		default:
			break;
		}

	}

	public ModelExpr clone(int pos) {
		ModelExpr obj = null;
		try {
			obj = (ModelExpr) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		obj.setExpressionInstance(pos);
		if (obj.leftInstanceExpression != null)
			obj.setLeftInstanceExpression(obj.leftInstanceExpression.clone(pos));
		if (obj.rightInstanceExpression != null)
			obj.setRightInstanceExpression(obj.rightInstanceExpression
					.clone(pos));
		return obj;
	}

	public List<InstElement> getConcepts() {
		ArrayList<InstElement> out = new ArrayList<InstElement>();
		ExpressionVertexType leftType = customSemanticExpression
				.getLeftExpressionType();
		ExpressionVertexType rightType = customSemanticExpression
				.getRightExpressionType();
		if (leftType == ExpressionVertexType.LEFTSUBEXPRESSION)
			out.addAll(this.leftInstanceExpression.getConcepts());
		if (rightType == ExpressionVertexType.RIGHTSUBEXPRESSION)
			out.addAll(this.rightInstanceExpression.getConcepts());
		if (leftType == ExpressionVertexType.LEFTVARIABLE)
			out.add(this.getLeftElement());
		if (rightType == ExpressionVertexType.RIGHTVARIABLE)
			out.add(this.getRightElement());
		return out;
	}

	public String getSourceInstanceId() {
		return sourceInstanceId;
	}

	public void setSourceInstanceId(String sourceInstanceId) {
		this.sourceInstanceId = sourceInstanceId;
	}

	public String getTargetInstanceId() {
		return targetInstanceId;
	}

	public void setTargetInstanceId(String targetInstanceId) {
		this.targetInstanceId = targetInstanceId;
	}

	public String getElementInstanceId() {
		return elementInstanceId;
	}

	public void setElementInstanceId(String elementInstanceId) {
		this.elementInstanceId = elementInstanceId;
	}
	
	public String getSourceConceptId() {
		return sourceConceptId;
	}
	
	public void setSourceConceptId(String sourceConceptId) {
		this.sourceConceptId = sourceConceptId;
	}
}
