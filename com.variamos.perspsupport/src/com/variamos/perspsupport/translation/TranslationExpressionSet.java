package com.variamos.perspsupport.translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mxgraph.util.mxResources;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.Labeling;
import com.variamos.perspsupport.expressionsupport.InstanceExpression;
import com.variamos.perspsupport.expressionsupport.OpersLabeling;
import com.variamos.perspsupport.expressionsupport.OpersSubOperation;
import com.variamos.perspsupport.expressionsupport.OpersSubOperationExpType;
import com.variamos.perspsupport.expressionsupport.SemanticExpression;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.model.ModelInstance;
import com.variamos.perspsupport.opers.OpersOverTwoRel;
import com.variamos.perspsupport.opers.OpersPairwiseRel;
import com.variamos.perspsupport.opersint.IntMetaExpression;
import com.variamos.perspsupport.opersint.IntOpersElement;
import com.variamos.perspsupport.types.ExpressionVertexType;
import com.variamos.perspsupport.types.OperationSubActionExecType;
import com.variamos.semantic.staticexpr.ElementExpressionSet;

/**
 * A class to represent the constraints. Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public class TranslationExpressionSet extends ElementExpressionSet {

	/**
	 * Identifier of the operation
	 */
	private String operation;
	/* 
	 */
	private Map<String, List<InstanceExpression>> instanceExpressions;

	/**
	 * must be obtained from UI
	 */
	private Map<String, Identifier> idMap;
	/**
	 * 
	 */
	private HlclFactory hlclFactory;

	static private HlclFactory f = new HlclFactory();
	/**
	 * 
	 */
	private boolean optional = false;
	private ModelInstance refas;

	/**
	 * Assign the parameters on the abstract class
	 * 
	 * @param operation
	 * @param column
	 */
	public TranslationExpressionSet(ModelInstance refas, String operation,
			Map<String, Identifier> idMap, HlclFactory hlclFactory) {
		super(operation, mxResources.get("defect-concepts") + " " + operation,
				idMap, hlclFactory);
		this.refas = refas;
		instanceExpressions = new HashMap<String, List<InstanceExpression>>();
		this.idMap = idMap;
		this.hlclFactory = hlclFactory;
		this.operation = operation;
	}

	public void addExpressions(ModelInstance refas, InstElement instElement,
			String subAction, OperationSubActionExecType expressionType) {

		List<InstanceExpression> out = new ArrayList<InstanceExpression>();

		// List<InstElement> semModel = refas.getVariabilityVertex("OMMModel");
		// for (InstElement oper : semModel) {
		// InstElement oper2 = refas.getElement("REFAS1");
		// IntSemanticElement semModelElement =
		// oper2.getEditableSemanticElement();

		// out.addAll(createElementInstanceExpressions(oper2));
		// TODO create expressions for model concepts

		// if (instElement == null)
		// out.addAll(createElementInstanceExpressions(operAction));
		// }

		List<InstElement> operActions = refas.getOperationalModel()
				.getVariabilityVertex("OMMOperation");
		InstElement operAction = null;
		OpersSubOperation operSubAction = null;
		for (InstElement oper : operActions) {
			if (oper.getIdentifier().equals(operation)) {
				operAction = oper;
				break;
			}
		}
		for (InstElement rel : operAction.getTargetRelations()) {
			InstElement subOper = rel.getTargetRelations().get(0);
			if (subOper.getIdentifier().equals(subAction)) {
				operSubAction = (OpersSubOperation) subOper
						.getEditableSemanticElement();
				break;
			}
		}

		/*
		 * SemanticOperationAction operAction = null; for (InstElement oper :
		 * operActions) { if (oper.getIdentifier().equals(operation)) operAction
		 * = (SemanticOperationAction) oper .getEditableSemanticElement();
		 * 
		 * } SemanticOperationSubAction operSubAction = operAction
		 * .getExpressionSubAction(subAction);
		 */
		if (operSubAction != null) {
			OpersSubOperationExpType operExpType = operSubAction
					.getOperationSubActionExpType(expressionType);
			if (operExpType != null) {
				List<IntMetaExpression> semExp = operExpType
						.getSemanticExpressions();

				if (instElement == null)
					for (InstElement instE : refas.getElements()) {
						out.addAll(createElementInstanceExpressions(instE,
								semExp));
						if (out.size() == 0) {
							instanceExpressions.put(subAction + "-"
									+ expressionType, out);
							return;
						}

						for (InstAttribute att : instE.getInstAttributes()
								.values()) {
							// create instance expressions for conditional
							// expressions
							if (att.getType()
									.equals(InstanceExpression.class
											.getCanonicalName())) {
								if (att.getValue() != null) {
									InstanceExpression instanceExpression = new InstanceExpression(
											true, "cond", true);
									instanceExpression
											.setSemanticExpressionType(refas
													.getSemanticExpressionTypes()
													.get("DoubleImplies"));
									instanceExpression.setLeftElement(instE);
									instanceExpression.setLeftAttributeName(att
											.getIdentifier());
									instanceExpression
											.setRightInstanceExpression((InstanceExpression) att
													.getValue());
									instanceExpression
											.setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
									out.add(instanceExpression);
								} else {
									InstanceExpression instanceExpression = new InstanceExpression(
											true, "cond", true);
									instanceExpression
											.setSemanticExpressionType(refas
													.getSemanticExpressionTypes()
													.get("Equals"));
									instanceExpression.setLeftElement(instE);
									instanceExpression.setLeftAttributeName(att
											.getIdentifier());
									instanceExpression
											.setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
									instanceExpression.setRightNumber(1);
									out.add(instanceExpression);
								}
							}
						}
						for (InstAttribute var : instE.getInstAttributes()
								.values()) {
							int attributeValue = 0;
							if (operSubAction.validateAttribute(instE
									.getTransSupportMetaElement()
									.getTransInstSemanticElement(), var
									.getAttributeName(), true) == 1) {
								String type = (String) var.getType();
								if (type.equals("Integer")
										|| type.equals("Boolean")) {
									if (var.getValue() instanceof Boolean)
										attributeValue = ((boolean) var
												.getValue()) ? 1 : 0;
									else if (var.getValue() instanceof String)
										attributeValue = Integer
												.valueOf((String) var
														.getValue());
									else
										attributeValue = (Integer) var
												.getValue();
								}
								InstanceExpression instanceExpression = new InstanceExpression(
										true, "t", true);
								instanceExpression
										.setSemanticExpressionType(refas
												.getSemanticExpressionTypes()
												.get("Equals"));
								instanceExpression.setLeftElement(instE);
								instanceExpression.setLeftAttributeName(var
										.getIdentifier());
								if (type.equals("String")) {
									instanceExpression
											.setRightValue((String) var
													.getValue());
									instanceExpression
											.setRightExpressionType(ExpressionVertexType.RIGHTSTRINGVALUE);
								} else {
									instanceExpression
											.setRightNumber(attributeValue);
									instanceExpression
											.setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
								}
								out.add(instanceExpression);
							}
						}
						// for (AbstractAttribute var : operSubAction
						// .getInVariables()) {
						// int attributeValue = 0;
						// InstAttribute instAttribute = instE
						// .getInstAttribute(var.getName());
						// // FIXME: compare attribute name and element name
						// if (instAttribute != null
						// && instAttribute.getAttribute() == var) {
						// String type = (String) instAttribute.getType();
						// if (type.equals("Integer")
						// || type.equals("Boolean")) {
						// if (instAttribute.getValue() instanceof Boolean)
						// attributeValue = ((boolean) instAttribute
						// .getValue()) ? 1 : 0;
						// else if (instAttribute.getValue() instanceof String)
						// attributeValue = Integer
						// .valueOf((String) instAttribute
						// .getValue());
						// else
						// attributeValue = (Integer) instAttribute
						// .getValue();
						// }
						// // if (type.equals("String")) {
						// // attributeValue = ((String) instAttribute
						// // .getValue()).hashCode();
						// // }
						// InstanceExpression instanceExpression = new
						// InstanceExpression(
						// true, "t", true);
						// instanceExpression
						// .setSemanticExpressionType(refas
						// .getSemanticExpressionTypes()
						// .get("Equals"));
						// instanceExpression.setLeftElement(instE);
						// instanceExpression
						// .setLeftAttributeName(instAttribute
						// .getIdentifier());
						// if (type.equals("String")) {
						// instanceExpression
						// .setRightValue((String) instAttribute
						// .getValue());
						// instanceExpression
						// .setRightExpressionType(ExpressionVertexType.RIGHTSTRINGVALUE);
						// } else {
						// instanceExpression
						// .setRightNumber(attributeValue);
						// instanceExpression
						// .setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
						// }
						// out.add(instanceExpression);
						// }
						// }
					}
				else
					out.addAll(createElementInstanceExpressions(instElement,
							semExp));

			}
		}
		instanceExpressions.put(subAction + "-" + expressionType, out);

	}

	public List<Labeling> getLabelings(ModelInstance refas, String subAction,
			OperationSubActionExecType expressionType) {

		List<InstElement> operActions = refas.getOperationalModel()
				.getVariabilityVertex("OMMOperation");
		InstElement operAction = null;
		OpersSubOperation operSubAction = null;
		InstElement instOperSubAction = null;
		for (InstElement oper : operActions) {
			if (oper.getIdentifier().equals(operation)) {
				operAction = oper;
				break;
			}
		}
		for (InstElement rel : operAction.getTargetRelations()) {
			InstElement subOper = rel.getTargetRelations().get(0);
			if (subOper.getIdentifier().equals(subAction)) {
				instOperSubAction = subOper;
				operSubAction = (OpersSubOperation) subOper
						.getEditableSemanticElement();
			}
		}

		if (operSubAction != null) {
			OpersSubOperationExpType operExpType = operSubAction
					.getOperationSubActionExpType(expressionType);
			if (operExpType != null) {
				List<Labeling> out = new ArrayList<Labeling>();
				for (InstElement rel : instOperSubAction.getTargetRelations()) {
					InstElement instOperLab = rel.getTargetRelations().get(0);
					OpersLabeling operLab = (OpersLabeling) instOperLab
							.getEditableSemanticElement();
					// for (OperationLabeling operLab :
					// operSubAction.getOperLabels()) {

					List<Identifier> ident = new ArrayList<Identifier>();
					for (InstElement instE : refas.getElements()) {
						for (InstAttribute var : instE.getInstAttributes()
								.values()) {
							int attributeValue = 0;
							if (operLab.validateAttribute(instE
									.getTransSupportMetaElement()
									.getTransInstSemanticElement(), var
									.getAttributeName()) == 1) {
								Identifier id = f.newIdentifier(instE
										.getIdentifier()
										+ "_"
										+ var.getAttributeName());
								// id.setDomain();
								InstanceExpression.updateDomain(
										var.getAttribute(), instE, id);
								ident.add(id);
							}
						}
					}
					Collections.sort(ident);
					Labeling lab = new Labeling((String) operLab.getName(),
							operLab.getLabelId(), operLab.getPosition(),
							operLab.isOnce(), operLab.getLabelingOrderList(),
							operLab.getOrderExpressionList());
					lab.setVariables(ident);
					out.add(lab);
				}
				return out;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected List<InstanceExpression> createElementInstanceExpressions(
			InstElement instElement, List<IntMetaExpression> semanticExpressions) {
		IntOpersElement semElement = instElement.getTransSupportMetaElement()
				.getTransSemanticConcept();
		List<InstanceExpression> out = new ArrayList<InstanceExpression>();
		if (semElement != null
				&& semElement.getAllSemanticExpressions() != null)
			for (IntMetaExpression semExpression : semElement
					.getAllSemanticExpressions()) {
				if (semanticExpressions.contains(semExpression)) {
					InstanceExpression instanceExpression = new InstanceExpression(
							refas, false, (SemanticExpression) semExpression);
					instanceExpression.createFromSemanticExpression(
							instElement, 0);
					out.add(instanceExpression);
				}
			}
		if (semElement != null
				&& (semElement instanceof OpersOverTwoRel || semElement instanceof OpersPairwiseRel)) {
			InstAttribute ia = instElement.getTransSupportMetaElement()
					.getTransInstSemanticElement()
					.getInstAttribute("operationsExpressions");
			List<InstAttribute> ias = (List<InstAttribute>) ia.getValue();
			for (InstAttribute attribute : ias) {
				String att = attribute.getIdentifier();
				String comp = (String) instElement.getInstAttribute(
						"relationType").getValue();
				if (att.equals(comp))
					for (IntMetaExpression semExpression : (List<IntMetaExpression>) attribute
							.getValue()) {
						if (semanticExpressions.contains(semExpression)) {
							InstanceExpression instanceExpression = new InstanceExpression(
									refas, false,
									(SemanticExpression) semExpression);
							instanceExpression.createFromSemanticExpression(
									instElement, 0);
							out.add(instanceExpression);
						}
					}
			}
		}
		return out;
	}

	protected List<InstanceExpression> createElementInstanceExpressions(
			InstElement instElement) {
		IntOpersElement semElement = instElement.getTransSupportMetaElement()
				.getTransSemanticConcept();
		List<InstanceExpression> out = new ArrayList<InstanceExpression>();
		if (semElement != null
				&& semElement.getAllSemanticExpressions() != null)
			for (IntMetaExpression semExpression : semElement
					.getAllSemanticExpressions()) {
				InstanceExpression instanceExpression = new InstanceExpression(
						refas, false, (SemanticExpression) semExpression);
				instanceExpression.createFromSemanticExpression(instElement, 0);
				out.add(instanceExpression);
			}

		return out;
	}

	protected List<InstanceExpression> createElementInstanceExpressions(
			IntOpersElement semElement) {
		List<InstanceExpression> out = new ArrayList<InstanceExpression>();
		if (semElement != null
				&& semElement.getAllSemanticExpressions() != null)
			for (IntMetaExpression semExpression : semElement
					.getAllSemanticExpressions()) {
				InstanceExpression instanceExpression = new InstanceExpression(
						refas, false, (SemanticExpression) semExpression);
				instanceExpression.createFromSemanticExpression(null, 0);
				out.add(instanceExpression);
			}
		return out;
	}

	protected void setOptional(boolean optional) {
		this.optional = optional;
	}

	protected Map<String, Identifier> getIdMap() {
		return idMap;
	}

	protected HlclFactory getHlclFactory() {
		return hlclFactory;
	}

	public boolean isOptional() {
		return optional;
	}

	public List<InstanceExpression> getInstanceExpressions(String column) {
		return instanceExpressions.get(column);
	}

	/**
	 * Expression for textual representation
	 * 
	 * @return
	 */
	public List<Expression> getHLCLExpressions(String column) {
		List<Expression> out = new ArrayList<Expression>();
		for (InstanceExpression expression : instanceExpressions.get(column)) {
			// idMap.putAll(expression.(hlclFactory));
			Expression newExp = expression.createSGSExpression();
			if (newExp != null)
				out.add(newExp);
		}
		return out;
	}

	public HlclProgram getHlCLProgramExpressions(String column) {
		HlclProgram prog = new HlclProgram();
		for (InstanceExpression expression : instanceExpressions.get(column)) {
			// idMap.putAll(transformation.getIdentifiers(hlclFactory));
			BooleanExpression newExp = (BooleanExpression) expression
					.createSGSExpression();
			if (newExp != null)
				prog.add(newExp);
		}
		return prog;
	}

}
