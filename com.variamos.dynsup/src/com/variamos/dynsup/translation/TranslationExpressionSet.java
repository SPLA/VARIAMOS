package com.variamos.dynsup.translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private String operation;
	/* 
	 */
	private Map<String, List<ModelExpr>> instanceExpressions;
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
	public TranslationExpressionSet(ModelInstance refas, String operation,
			Map<String, Identifier> idMap, HlclFactory hlclFactory) {
		super(operation, mxResources.get("defect-concepts") + " " + operation,
				idMap, hlclFactory);
		this.refas = refas;
		instanceExpressions = new HashMap<String, List<ModelExpr>>();
		literalExpressions = new HashMap<String, List<LiteralBooleanExpression>>();
		this.idMap = idMap;
		this.hlclFactory = hlclFactory;
		this.operation = operation;
	}

	public void addExpressions(ModelInstance refas, InstElement instElement,
			String subAction, OperationSubActionExecType expressionType) {

		List<ModelExpr> out = new ArrayList<ModelExpr>();

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
		InstElement operAction = null;
		InstElement operSubAction = null;
		for (InstElement oper : operActions) {
			if (oper.getIdentifier().equals(operation)) {
				operAction = oper;
				break;
			}
		}
		for (InstElement rel : operAction.getTargetRelations()) {
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
			for (InstAttribute att : listatt) {
				Object attval = ((InstConcept) att.getValue())
						.getInstAttributeValue("suboperexptype");
				if (attval.equals(expressionType.toString())) {
					operExpType = (OpersSubOperationExpType) ((InstConcept) att
							.getValue()).getEdOperEle();
				}
			}
			if (operExpType != null) {
				List<OpersExpr> semExp = operExpType.getSemanticExpressions();

				if (instElement == null)
					for (InstElement instE : refas.getElements()) {
						out.addAll(createElementInstanceExpressions(instE,
								semExp, false));
						// FIXME better validate to create conditional
						// expressions
						if (out.size() == 0) {
							continue;
						}
						if (instE.getTransSupInstElement().getEdSyntaxEle()
								.getInstSemanticElementId() != null
								&& instE.getTransSupInstElement()
										.getEdSyntaxEle()
										.getInstSemanticElementId()
										.equals("nmVariable")) {
							// System.out.println(instE.getInstAttribute("type"));
							if (instE.getInstAttribute("variableType")
									.getValue().equals("LowLevel expression")
									&& instE.getInstAttribute(
											"LowLevelExpressionOper")
											.getValue().equals(subAction)) {
								outLiteral.add(new LiteralBooleanExpression(
										((LowExpr) instE.getInstAttribute(
												"LowLevelExpressionText")
												.getValue())
												.getLowExpressions()));
							}
							if (instE.getInstAttribute("variableType")
									.getValue().equals("LowLevel variable")
									&& instE.getInstAttribute("LowLevelVarOper")
											.getValue().equals(subAction)) {
								if (instE.getInstAttribute("LowLevelVarValue")
										.getValue() != null
										&& !instE
												.getInstAttribute(
														"LowLevelVarValue")
												.getValue().equals("")) {
									ModelExpr instanceExpression = new ModelExpr(
											true, "cond", true);
									instanceExpression
											.setSemanticExpressionType(refas
													.getSemanticExpressionTypes()
													.get("Equals"));
									instanceExpression.setLeftElement(instE);
									instanceExpression
											.setLeftAttributeName("value");
									instanceExpression
											.setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
									instanceExpression.setRightNumber(Integer
											.parseInt((String) instE
													.getInstAttribute(
															"LowLevelVarValue")
													.getValue()));
									out.add(instanceExpression);
								}
							}
						}

						for (InstAttribute att : instE.getInstAttributes()
								.values()) {
							// create instance expressions for conditional
							// expressions
							if (att.getType().equals(
									ModelExpr.class.getCanonicalName())) {
								if (att.getValue() != null) {
									ModelExpr instanceExpression = new ModelExpr(
											true, "cond", true);
									instanceExpression
											.setSemanticExpressionType(refas
													.getSemanticExpressionTypes()
													.get("DoubleImplies"));
									instanceExpression.setLeftElement(instE);
									instanceExpression.setLeftAttributeName(att
											.getIdentifier());
									System.out.println(att.getIdentifier());
									instanceExpression
											.setRightInstanceExpression((ModelExpr) att
													.getValue());
									instanceExpression
											.setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
									out.add(instanceExpression);
								} else {
									ModelExpr instanceExpression = new ModelExpr(
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
							if (((OpersSubOperation) operSubAction
									.getEdOperEle()).validateAttribute(instE
									.getTransSupportMetaElement()
									.getTransInstSemanticElement(), var
									.getAttributeName(), true) == 1) {
								String type = var.getType();
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
								ModelExpr instanceExpression = new ModelExpr(
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
							semExp, false));

			}
		}
		instanceExpressions.put(subAction + "-" + expressionType, out);
		literalExpressions.put(subAction + "-" + expressionType, outLiteral);

	}

	public List<Labeling> getLabelings(ModelInstance refas, String subAction,
			OperationSubActionExecType expressionType) {

		List<InstElement> operActions = refas.getOperationalModel()
				.getVariabilityVertex("OMOperation");
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
				operSubAction = (OpersSubOperation) subOper.getEdOperEle();
			}
		}

		if (operSubAction != null) {
			@SuppressWarnings("unchecked")
			List<InstAttribute> listatt = ((List<InstAttribute>) instOperSubAction
					.getInstAttributeValue("exptype"));
			OpersSubOperationExpType operExpType = null;
			for (InstAttribute att : listatt) {
				if (((InstConcept) att.getValue()).getInstAttributeValue(
						"suboperexptype").equals(expressionType.toString())) {
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
					Set<InstElement> e = refas.getElements();
					for (InstElement instE : refas.getElements()) {
						if (instE.getTransSupInstElement().getEdSyntaxEle()
								.getInstSemanticElementId()
								.equals("nmVariable")
								&& instE.getInstAttribute("variableType")
										.getValue().equals("LowLevel variable")
								&& instE.getInstAttribute("LowLevelVarOper")
										.getValue()
										.equals(instOperSubAction
												.getDynamicAttribute("userId"))
								&& instE.getInstAttribute("LowLevelVarLabel")
										.getValue()
										.equals(operLab
												.getDynamicAttribute("userId"))) {
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
							for (InstAttribute var : instE.getInstAttributes()
									.values()) {
								if (((OpersLabeling) operLab.getEdOperEle())
										.validateAttribute(instE
												.getTransSupportMetaElement()
												.getTransInstSemanticElement(),
												var.getAttributeName()) == 1) {

									Identifier id = f.newIdentifier(instE
											.getIdentifier()
											+ "_"
											+ var.getAttributeName());
									// id.setDomain();
									ModelExpr.updateDomain(var.getAttribute(),
											instE, id);
									ident.add(id);
								}
							}
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
							oper2, semExps, true);
					List<NumericExpression> explist = getNumericExpressions(instexp);
					Labeling lab = new Labeling(operLab.getIdentifier(),
							(String) operLab.getInstAttributeValue("labelId"),
							(int) operLab.getInstAttributeValue("position"),
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
			boolean semanticElement) {
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
					ModelExpr instanceExpression = new ModelExpr(refas, false,
							semExpression);
					instanceExpression.createFromSemanticExpression(
							instElement, 0);
					out.add(instanceExpression);
				}
			}
		}
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
									ModelExpr instanceExpression = new ModelExpr(
											refas, false, semExpression);
									instanceExpression
											.createFromSemanticExpression(
													instElement, 0);
									out.add(instanceExpression);
									// System.out.println("instExppr: "
									// + instanceExpression
									// .expressionStructure());
								}
							}
					}
				}
			}
		}
		return out;
	}

	protected List<ModelExpr> createElementInstanceExpressions(
			InstElement instElement) {
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
						semExpression);
				instanceExpression.createFromSemanticExpression(instElement, 0);
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
		String i = "";
		for (ModelExpr expression : instanceExpressions.get(column)) {
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
