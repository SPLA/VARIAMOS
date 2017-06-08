package com.variamos.dynsup.translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mxgraph.util.mxResources;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.model.LowExpr;
import com.variamos.dynsup.model.ModelExpr;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.OpersExpr;
import com.variamos.dynsup.model.OpersLabeling;
import com.variamos.dynsup.model.OpersSubOperation;
import com.variamos.dynsup.model.OpersSubOperationExpType;
import com.variamos.dynsup.staticexpr.ElementExpressionSet;
import com.variamos.dynsup.types.ExpressionVertexType;
import com.variamos.dynsup.types.OperationSubActionExecType;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.Labeling;
import com.variamos.hlcl.LabelingOrder;
import com.variamos.hlcl.LiteralBooleanExpression;
import com.variamos.hlcl.NumericExpression;

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
	private InstElement operation;
	/*
	 * Normal instance Expressions
	 */
	private Map<String, List<ModelExpr>> instanceExpressions;
	/*
	 * LowLevel instance Expressions
	 */
	private Map<String, List<ModelExpr>> instanceLowExpr;
	/* 
	 */
	private Map<String, List<LiteralBooleanExpression>> literalExpressions;

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
	public TranslationExpressionSet(ModelInstance refas, InstElement operation,
			Map<String, Identifier> idMap, HlclFactory hlclFactory) {
		super(operation.getIdentifier(), mxResources.get("defect-concepts")
				+ " " + operation, idMap, hlclFactory);
		this.refas = refas;
		instanceExpressions = new HashMap<String, List<ModelExpr>>();
		instanceLowExpr = new HashMap<String, List<ModelExpr>>();
		literalExpressions = new HashMap<String, List<LiteralBooleanExpression>>();
		this.idMap = idMap;
		this.hlclFactory = hlclFactory;
		this.operation = operation;
	}

	public void addExpressions(ModelInstance refas, InstElement instElement,
			String subAction, OperationSubActionExecType expressionType) {

		List<ModelExpr> out = new ArrayList<ModelExpr>();
		List<ModelExpr> outLow = new ArrayList<ModelExpr>();

		List<LiteralBooleanExpression> outLiteral = new ArrayList<LiteralBooleanExpression>();

		// List<InstElement> semModel =
		// refas.getVariabilityVertex("OMModel");
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
				.getVariabilityVertex("OMOperation");
		// InstElement operAction = null;
		InstElement operSubAction = null;
		// for (InstElement oper : operActions) {
		// if (oper.getIdentifier().equals(operation)) {
		// operAction = oper;
		// break;
		// }
		// }
		for (InstElement rel : operation.getTargetRelations()) {
			InstElement subOper = rel.getTargetRelations().get(0);
			if (subOper.getIdentifier().equals(subAction)) {
				operSubAction = subOper;
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
			@SuppressWarnings("unchecked")
			List<InstAttribute> listatt = ((List<InstAttribute>) operSubAction
					.getInstAttributeValue("exptype"));
			OpersSubOperationExpType operExpType = null;
			String subOperExpTypeName = null;
			for (InstAttribute att : listatt) {
				String attObj = (String) ((InstConcept) att.getValue())
						.getInstAttributeValue("suboperexptype");
				if (attObj.equals(expressionType.toString())) {
					operExpType = (OpersSubOperationExpType) ((InstConcept) att
							.getValue()).getEdOperEle();
					subOperExpTypeName = attObj;
				}
			}
			if (operExpType != null) {
				List<OpersExpr> semExp = operExpType.getSemanticExpressions();

				if (instElement == null)
					for (InstElement instE : refas.getElements()) {
						if (instE.getTransSupInstElement().getEdSyntaxEle() != null
								&& instE.getTransSupInstElement()
										.getEdSyntaxEle()
										.getInstSemanticElementId() != null
								&& instE.getTransSupInstElement()
										.getEdSyntaxEle()
										.getInstSemanticElementId()
										.equals("NmVariable")) {
							// System.out.println(instE.getInstAttribute("type"));
							if (instE.getInstAttribute("variableType")
									.getValue().equals("LowLevel expression")
									&& instE.getInstAttribute(
											"LowLevelExprSubOper").getValue()
											.equals(subAction)) {
								outLiteral.add(new LiteralBooleanExpression(
										((LowExpr) instE.getInstAttribute(
												"LowLevelExpressionText")
												.getValue())
												.getLowExpressions()));
							}
							if (instE.getInstAttribute("variableType")
									.getValue().equals("LowLevel variable")
									&& instE.getInstAttribute(
											"LowLevelVarInSubOper").getValue()
											.equals(subAction)) {
								if (instE.getInstAttribute("LowLevelVarValue")
										.getValue() != null
										&& !instE
												.getInstAttribute(
														"LowLevelVarValue")
												.getValue().equals("")) {
									ModelExpr instanceExpression = new ModelExpr(
											true, instE.getIdentifier()
													+ "Cond", true, -1);
									instanceExpression
											.setSemanticExpressionType(refas
													.getSemanticExpressionTypes()
													.get("Is"));
									instanceExpression.setLeftElement(instE);
									instanceExpression
											.setLeftAttributeName("value");
									instanceExpression
											.setRightExpressionType(ExpressionVertexType.RIGHTNUMERICFLOATVALUE);
									instanceExpression.setRightNumber(Float
											.parseFloat((String) instE
													.getInstAttribute(
															"LowLevelVarValue")
													.getValue()));
									outLow.add(instanceExpression);
								}
							}
							if (instE.getInstAttribute("variableType")
									.getValue().equals("LowLevel variable")
									&& instE.getInstAttribute(
											"IntegerVarInSubOper").getValue()
											.equals(subAction)) {
								if (instE.getInstAttribute("value").getValue() != null
										&& !instE.getInstAttribute("value")
												.getValue().equals("")) {
									ModelExpr instanceExpression = new ModelExpr(
											true, instE.getIdentifier()
													+ "Cond", true, -1);

									instanceExpression
											.setSemanticExpressionType(refas
													.getSemanticExpressionTypes()
													.get("Equals"));
									instanceExpression.setLeftElement(instE);
									instanceExpression
											.setLeftAttributeName("value");
									instanceExpression
											.setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
									instanceExpression
											.setRightNumber(((Integer) instE
													.getInstAttribute("value")
													.getValue()));
									outLow.add(instanceExpression);

								}
							}
						} else if (instE.getTransSupInstElement()
								.getEdSyntaxEle() == null)
							System.out.println(instE.getIdentifier());
						int expressionInstances = instE.getInstances(refas);

						if (instE.getInstAttribute("variableType") == null
								|| (!instE.getInstAttribute("variableType")
										.getValue().equals("LowLevel variable") && !instE
										.getInstAttribute("variableType")
										.getValue()
										.equals("LowLevel expression"))) {
							out.addAll(createElementInstanceExpressions(instE,
									semExp, false, expressionInstances));
							int pos = -1;
							do {
								for (InstAttribute var : instE
										.getInstAttributes().values()) {
									if (expressionInstances > 1 && pos == -1)
										var.updateAdditionalAttributes(expressionInstances);
									if (expressionInstances > 1 && pos != -1)
										var = var.getAdditionalAttribute(pos);
									int attributeValue = 0;
									if (subOperExpTypeName.equals("NORMAL")
											&& ((OpersSubOperation) operSubAction
													.getEdOperEle())
													.validateAttribute(
															instE.getTransSupportMetaElement()
																	.getTransInstSemanticElement(),
															var.getAttributeName(),
															true) == 1) {
										String type = var.getType();

										// create instance expressions for
										// conditional
										// expressions
										if (type.equals(ModelExpr.class
												.getCanonicalName())) {
											if (var.getValue() != null) {
												ModelExpr instanceExpression = new ModelExpr(
														true,
														var.getIdentifier()
																+ "Cond", true,
														pos);
												instanceExpression
														.setSemanticExpressionType(refas
																.getSemanticExpressionTypes()
																.get("DoubleImplies"));
												instanceExpression
														.setLeftElement(instE);
												instanceExpression
														.setLeftAttributeName(var
																.getIdentifier());
												// System.out.println(att.getIdentifier());
												instanceExpression
														.setRightInstanceExpression((ModelExpr) var
																.getValue());
												instanceExpression
														.setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
												out.add(instanceExpression);
											} else {
												ModelExpr instanceExpression = new ModelExpr(
														true,
														var.getIdentifier()
																+ "Cond", true,
														pos);
												instanceExpression
														.setSemanticExpressionType(refas
																.getSemanticExpressionTypes()
																.get("Equals"));
												instanceExpression
														.setLeftElement(instE);
												instanceExpression
														.setLeftAttributeName(var
																.getIdentifier());
												instanceExpression
														.setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
												instanceExpression
														.setRightNumber(1);
												out.add(instanceExpression);
											}
										} else {
											if (type.equals("Integer")
													|| type.equals("Boolean")) {
												if (var.getValue() instanceof Boolean)
													attributeValue = ((boolean) var
															.getValue()) ? 1
															: 0;
												else if (var.getValue() instanceof String)
													attributeValue = Integer
															.valueOf((String) var
																	.getValue());
												else
													attributeValue = (Integer) var
															.getValue();
											}
											ModelExpr instanceExpression = new ModelExpr(
													true, "t", true, pos);
											instanceExpression
													.setSemanticExpressionType(refas
															.getSemanticExpressionTypes()
															.get("Equals"));
											instanceExpression
													.setLeftElement(instE);
											instanceExpression
													.setLeftAttributeName(var
															.getIdentifier());
											// System.out.println("ErDe: "+var.getIdentifier());
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
								}
								pos++;
							} while (pos + 1 < expressionInstances);
							// FIXME validate better the creation of conditional
							// expressions
							if (out.size() == 0) {
								continue;

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
							// else if (instAttribute.getValue() instanceof
							// String)
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

					}
				else {
					int instances = instElement.getInstances(refas);
					out.addAll(createElementInstanceExpressions(instElement,
							semExp, false, instances));
				}

			}
		}
		instanceExpressions.put(subAction + "-" + expressionType, out);
		instanceLowExpr.put(subAction + "-" + expressionType, outLow);
		literalExpressions.put(subAction + "-" + expressionType, outLiteral);

	}

	public List<Labeling> getLabelings(ModelInstance refas, String subAction,
			OperationSubActionExecType expressionType) {

		List<InstElement> operActions = refas.getOperationalModel()
				.getVariabilityVertex("OMOperation");
		// InstElement operAction = null;
		OpersSubOperation operSubAction = null;
		InstElement instOperSubAction = null;
		// for (InstElement oper : operActions) {
		// if (oper.getIdentifier().equals(operation)) {
		// operAction = oper;
		// break;
		// }
		// }
		for (InstElement rel : operation.getTargetRelations()) {
			InstElement subOper = rel.getTargetRelations().get(0);
			if (subOper.getIdentifier().equals(subAction)) {
				instOperSubAction = subOper;
				operSubAction = (OpersSubOperation) subOper.getEdOperEle();
			}
		}

		if (operSubAction != null) {
			@SuppressWarnings("unchecked")
			List<InstAttribute> listatt = ((List<InstAttribute>) instOperSubAction
					.getInstAttributeValue("exptype"));
			OpersSubOperationExpType operExpType = null;
			for (InstAttribute att : listatt) {
				if (expressionType == null
						|| ((InstConcept) att.getValue())
								.getInstAttributeValue("suboperexptype")
								.equals(expressionType.toString())) {
					operExpType = (OpersSubOperationExpType) ((InstConcept) att
							.getValue()).getEdOperEle();
				}
			}
			if (operExpType != null) {
				List<Labeling> out = new ArrayList<Labeling>();
				for (InstElement rel : instOperSubAction.getTargetRelations()) {
					InstElement operLab = rel.getTargetRelations().get(0);
					// for (OperationLabeling operLab :
					// operSubAction.getOperLabels()) {

					List<Identifier> ident = new ArrayList<Identifier>();
					for (InstElement instE : refas.getElements()) {
						if (instE.getTransSupInstElement().getEdSyntaxEle()
								.getInstSemanticElementId() != null
								&& instE.getTransSupInstElement()
										.getEdSyntaxEle()
										.getInstSemanticElementId()
										.equals("NmVariable")
								&& instE.getInstAttribute("variableType")
										.getValue().equals("LowLevel variable")
								&& (instE
										.getInstAttribute(
												"LowLevelVarInSubOper")
										.getValue()
										.equals(instOperSubAction
												.getDynamicAttribute("userId"))
										&& instE.getInstAttribute(
												"LowLevelInVarLabel")
												.getValue()
												.equals(operLab
														.getDynamicAttribute("userId")) || instE
										.getInstAttribute(
												"LowLevelVarOutSubOper")
										.getValue()
										.equals(instOperSubAction
												.getDynamicAttribute("userId"))
										&& instE.getInstAttribute(
												"LowLevelOutVarLabel")
												.getValue()
												.equals(operLab
														.getDynamicAttribute("userId")))) {
							Identifier id = f.newIdentifier(instE
									.getIdentifier() + "_value");
							ident.add(id);
						}
						if (instE.getInstAttribute("variableType") == null
								|| (!instE.getInstAttribute("variableType")
										.getValue().equals("LowLevel variable") && !instE
										.getInstAttribute("variableType")
										.getValue()
										.equals("LowLevel expression"))) {
							int instances = instE.getInstances(refas);
							int pos = -1;
							do {
								for (InstAttribute var : instE
										.getInstAttributes().values()) {
									if (instances > 1 && pos == -1)
										var.updateAdditionalAttributes(instances);
									if (instances > 1 && pos != -1)
										var = var.getAdditionalAttribute(pos);
									if (((OpersLabeling) operLab.getEdOperEle())
											.validateAttribute(
													instE.getTransSupportMetaElement()
															.getTransInstSemanticElement(),
													var.getAttributeName()) == 1) {
										String addAtt = "";
										if (pos != -1)
											addAtt = "_" + pos;
										Identifier id = f.newIdentifier(instE
												.getIdentifier()
												+ addAtt
												+ "_"
												+ var.getAttributeName());
										// id.setDomain();
										ModelExpr.updateDomain(
												var.getAttribute(), instE, id);
										ident.add(id);
									}
								}
								pos++;
							} while (pos + 1 < instances);
						}
					}
					Collections.sort(ident);

					@SuppressWarnings("unchecked")
					ArrayList<InstAttribute> instattrs = (ArrayList<InstAttribute>) operLab
							.getInstAttributeValue("sortorder");
					List<LabelingOrder> laborder = new ArrayList<LabelingOrder>();
					for (InstAttribute att : instattrs) {
						laborder.add((LabelingOrder) att.getValue());
					}
					List<OpersExpr> semExps = operLab.getEdOperEle()
							.getSemanticExpressions();
					// FIXME support more models
					InstElement oper2 = refas.getElement("REFAS1");
					List<ModelExpr> instexp = createElementInstanceExpressions(
							oper2, semExps, true, 1);
					List<NumericExpression> explist = getNumericExpressions(instexp);
					int position = 0;
					if (operLab.getInstAttributeValue("position") instanceof String)
						position = Integer.parseInt((String) operLab
								.getInstAttributeValue("position"));
					else
						position = ((Integer) operLab
								.getInstAttributeValue("position")).intValue();
					Labeling lab = new Labeling(operLab.getIdentifier(),
							(String) operLab.getInstAttributeValue("labelId"),
							position,
							(boolean) operLab
									.getInstAttributeValue("outputSet"),
							(boolean) operLab
									.getInstAttributeValue("includeLabel"),
							(boolean) operLab.getInstAttributeValue("once"),
							(boolean) operLab.getInstAttributeValue("order"),
							laborder, explist);
					lab.setVariables(ident);
					out.add(lab);
				}
				return out;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected List<ModelExpr> createElementInstanceExpressions(
			InstElement instElement, List<OpersExpr> semanticExpressions,
			boolean semanticElement, int expressionInstances) {
		OpersElement semElement = null;
		/*
		 * if (semanticElement) semElement =
		 * instElement.getEditableSemanticElement(); else
		 */
		// System.out.println(instElement.getIdentifier());
		semElement = instElement.getTransSupInstElement().getEdSyntaxEle()
				.getTransSemanticConcept();
		List<ModelExpr> out = new ArrayList<ModelExpr>();
		List<InstElement> opersParents = null;
		if (instElement.getTransSupportMetaElement() != null
				&& instElement.getTransSupportMetaElement()
						.getTransInstSemanticElement() != null)
			opersParents = instElement.getTransSupportMetaElement()
					.getTransInstSemanticElement().getParentOpersConcept();
		// Creates the expression from concepts meta-expressions
		if (semElement != null
				&& semElement.getAllSemanticExpressions(opersParents) != null) {
			List<OpersExpr> semanticExpr = null;
			if (semanticElement)
				semanticExpr = semanticExpressions;
			else
				semanticExpr = semElement
						.getAllSemanticExpressions(opersParents);
			for (OpersExpr semExpression : semanticExpr) {
				if (semanticElement
						|| semanticExpressions.contains(semExpression)) {
					int expressionInstance = -1;
					do {
						ModelExpr instanceExpression = new ModelExpr(refas,
								false, semExpression,
								expressionInstances == 1 ? false : true);
						// System.out.println("ME_ID "
						// + instanceExpression.getSemanticExpression()
						// .getIdentifier());
						instanceExpression.createFromSemanticExpression(
								instElement, 0, expressionInstance,
								expressionInstances == 1 ? false : true, 0);
						// System.out.println("ME_STR "
						// + instanceExpression.expressionStructure());
						out.add(instanceExpression);
						expressionInstance++;
					} while (expressionInstance + 1 < expressionInstances);
				}
			}
		}
		// Creates the expression from meta-expressions of the relation
		// attribute "opersExprs"
		if (semElement != null && (semElement instanceof OpersElement)) {
			InstAttribute ia = instElement.getTransSupportMetaElement()
					.getTransInstSemanticElement()
					.getInstAttribute("opersExprs");
			if (ia != null) {
				List<InstAttribute> ias = (List<InstAttribute>) ia.getValue();

				for (InstAttribute attribute : ias) {
					String att = attribute.getIdentifier();
					if (instElement.getInstAttribute("relationType") != null) {
						String comp = (String) instElement.getInstAttribute(
								"relationType").getValue();
						if (att.equals(comp))
							for (OpersExpr semExpression : (List<OpersExpr>) attribute
									.getValue()) {
								if (semanticElement
										|| semanticExpressions
												.contains(semExpression)) {
									int expressionInstance = -1;
									do {
										ModelExpr instanceExpression = new ModelExpr(
												refas,
												false,
												semExpression,
												expressionInstances == 1 ? false
														: true);

										instanceExpression
												.createFromSemanticExpression(
														instElement,
														0,
														expressionInstance,
														expressionInstances == 1 ? false
																: true, 0);
										out.add(instanceExpression);

										// System.out
										// .println("instExppr: "
										// + instanceExpression
										// .getSemanticExpressionId()
										// + " "
										// + instanceExpression
										// .expressionStructure());
										expressionInstance++;
									} while (expressionInstance + 1 < expressionInstances);
								}
							}
					}
				}
			}
		}
		return out;
	}

	@Deprecated
	protected List<ModelExpr> createElementInstanceExpressions(
			InstElement instElement, int expressionInstance) {
		OpersElement semElement = instElement.getTransSupportMetaElement()
				.getTransSemanticConcept();
		List<ModelExpr> out = new ArrayList<ModelExpr>();
		List<InstElement> opersParents = instElement
				.getTransSupportMetaElement().getTransInstSemanticElement()
				.getParentOpersConcept();
		if (semElement != null
				&& semElement.getAllSemanticExpressions(opersParents) != null)
			for (OpersExpr semExpression : semElement
					.getAllSemanticExpressions(opersParents)) {
				ModelExpr instanceExpression = new ModelExpr(refas, false,
						semExpression, false);
				instanceExpression.createFromSemanticExpression(instElement, 0,
						expressionInstance, false, 0);
				out.add(instanceExpression);
			}

		return out;
	}

	// protected List<InstanceExpression> createElementInstanceExpressions(
	// IntOpersElement semElement) {
	// List<InstanceExpression> out = new ArrayList<InstanceExpression>();
	// if (semElement != null
	// && semElement.getAllSemanticExpressions() != null)
	// for (OpersExpr semExpression : semElement
	// .getAllSemanticExpressions()) {
	// InstanceExpression instanceExpression = new InstanceExpression(
	// refas, false, (SemanticExpression) semExpression);
	// instanceExpression.createFromSemanticExpression(null, 0);
	// out.add(instanceExpression);
	// }
	// return out;
	// }

	@Override
	protected void setOptional(boolean optional) {
		this.optional = optional;
	}

	@Override
	protected Map<String, Identifier> getIdMap() {
		return idMap;
	}

	@Override
	protected HlclFactory getHlclFactory() {
		return hlclFactory;
	}

	@Override
	public boolean isOptional() {
		return optional;
	}

	public List<ModelExpr> getInstanceExpressions(String column) {
		return instanceExpressions.get(column);
	}

	/**
	 * Expression for textual representation
	 * 
	 * @return
	 */
	public List<Expression> getHLCLExpressions(String column) {
		List<Expression> out = new ArrayList<Expression>();
		for (ModelExpr expression : instanceExpressions.get(column)) {
			// idMap.putAll(expression.(hlclFactory));
			Expression newExp = expression.createSGSExpression();
			if (newExp != null)
				out.add(newExp);
		}
		return out;
	}

	// Dynamic call
	public HlclProgram getHlCLProgramExpressions(String column) {
		HlclProgram prog = new HlclProgram();
		for (ModelExpr expression : instanceExpressions.get(column)) {
			BooleanExpression newExp = (BooleanExpression) expression
					.createSGSExpression();
			// System.out.println(expression.getSemanticExpression()
			// .expressionStructure());
			if (newExp != null)
				prog.add(newExp);
		}
		for (ModelExpr expression : instanceLowExpr.get(column)) {
			BooleanExpression newExp = (BooleanExpression) expression
					.createSGSExpression();

			if (newExp != null)
				prog.add(newExp);
		}
		return prog;
	}

	public List<NumericExpression> getNumericExpressions(
			List<ModelExpr> instanceExpressions) {
		List<NumericExpression> prog = new ArrayList<NumericExpression>();
		for (ModelExpr expression : instanceExpressions) {
			// idMap.putAll(transformation.getIdentifiers(hlclFactory));
			NumericExpression newExp = (NumericExpression) expression
					.createSGSExpression();
			if (newExp != null)
				prog.add(newExp);
		}
		return prog;
	}

	public HlclProgram getLiteralExpressions(String string) {
		HlclProgram out = new HlclProgram();
		for (List<LiteralBooleanExpression> litExps : literalExpressions
				.values())
			for (LiteralBooleanExpression lit : litExps)
				out.add(lit);
		return out;
	}

}
