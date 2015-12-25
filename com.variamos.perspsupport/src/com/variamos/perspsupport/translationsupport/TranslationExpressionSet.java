package com.variamos.perspsupport.translationsupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mxgraph.util.mxResources;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.Identifier;
import com.variamos.perspsupport.expressionsupport.InstanceExpression;
import com.variamos.perspsupport.expressionsupport.OperationSubActionExpType;
import com.variamos.perspsupport.expressionsupport.SemanticExpression;
import com.variamos.perspsupport.expressionsupport.SemanticOperationAction;
import com.variamos.perspsupport.expressionsupport.SemanticOperationSubAction;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.perspsupport.semanticinterface.IntSemanticElement;
import com.variamos.perspsupport.semanticinterface.IntSemanticExpression;
import com.variamos.perspsupport.semanticsupport.SemanticOverTwoRelation;
import com.variamos.perspsupport.semanticsupport.SemanticPairwiseRelation;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.types.ExpressionVertexType;
import com.variamos.perspsupport.types.OperationSubActionExecType;
import com.variamos.semantic.expressionsupport.ElementExpressionSet;

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
	/**
	 * 
	 */
	private boolean optional = false;

	/**
	 * Assign the parameters on the abstract class
	 * 
	 * @param operation
	 * @param column
	 */
	public TranslationExpressionSet(String operation,
			Map<String, Identifier> idMap, HlclFactory hlclFactory) {
		super(operation, mxResources.get("defect-concepts") + " " + operation,
				idMap, hlclFactory);
		instanceExpressions = new HashMap<String, List<InstanceExpression>>();
		this.idMap = idMap;
		this.hlclFactory = hlclFactory;
		this.operation = operation;
	}

	public void addExpressions(RefasModel refas, InstElement instElement,
			String subAction, OperationSubActionExecType expressionType) {

		List<InstanceExpression> out = new ArrayList<InstanceExpression>();

		List<InstElement> semModel = refas.getSemanticRefas()
				.getVariabilityVertex("CSModel");
		for (InstElement oper : semModel) {
			IntSemanticElement operAction = oper.getEditableSemanticElement();
			// if (instElement == null)
			// out.addAll(createElementInstanceExpressions(operAction));
		}

		List<InstElement> operActions = refas.getSemanticRefas()
				.getVariabilityVertex("CSOpAction");
		SemanticOperationAction operAction = null;
		for (InstElement oper : operActions) {
			if (oper.getIdentifier().equals(operation))
				operAction = (SemanticOperationAction) oper
						.getEditableSemanticElement();

		}
		SemanticOperationSubAction operSubAction = operAction
				.getExpressionSubAction(subAction);
		if (operSubAction != null) {
			OperationSubActionExpType operExpType = operSubAction
					.getOperationSubActionExpType(expressionType);
			if (operExpType != null) {
				List<SemanticExpression> semExp = operExpType
						.getSemanticExpressions();

				if (instElement == null)
					for (InstElement instE : refas.getElements()) {
						out.addAll(createElementInstanceExpressions(instE,
								semExp));
						for (AbstractAttribute var : operSubAction
								.getInVariables()) {
							int attributeValue = 0;
							InstAttribute instAttribute = instE
									.getInstAttribute(var.getName());
							if (instAttribute != null) {
								String type = (String) instAttribute.getType();
								if (type.equals("Integer")
										|| type.equals("Boolean")) {
									if (instAttribute.getValue() instanceof Boolean)
										attributeValue = ((boolean) instAttribute
												.getValue()) ? 1 : 0;
									else if (instAttribute.getValue() instanceof String)
										attributeValue = Integer
												.valueOf((String) instAttribute
														.getValue());
									else
										attributeValue = (Integer) instAttribute
												.getValue();
								}
								InstanceExpression instanceExpression = new InstanceExpression(
										true, "t", true);
								instanceExpression
										.setSemanticExpressionType(refas
												.getSemanticExpressionTypes()
												.get("Equals"));
								instanceExpression.setLeftElement(instE);
								instanceExpression
										.setLeftAttributeName(instAttribute
												.getIdentifier());
								instanceExpression
										.setRightNumber(attributeValue);
								instanceExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE);
								out.add(instanceExpression);
							}
						}
					}
				else
					out.addAll(createElementInstanceExpressions(instElement,
							semExp));

			}
		}
		instanceExpressions.put(subAction + "-" + expressionType, out);

	}

	public List<String> getOutVariables(RefasModel refas, String subAction) {
		List<String> out = new ArrayList<String>();
		List<InstElement> operActions = refas.getSemanticRefas()
				.getVariabilityVertex("CSOpAction");
		SemanticOperationAction operAction = null;
		for (InstElement oper : operActions) {
			if (oper.getIdentifier().equals(operation))
				operAction = (SemanticOperationAction) oper
						.getEditableSemanticElement();

		}
		SemanticOperationSubAction operSubAction = operAction
				.getExpressionSubAction(subAction);
		for (AbstractAttribute att : operSubAction.getOutVariables()) {
			out.add(att.getName());
		}
		return out;
	}

	protected List<InstanceExpression> createElementInstanceExpressions(
			InstElement instElement,
			List<SemanticExpression> semanticExpressions) {
		IntSemanticElement semElement = instElement
				.getTransSupportMetaElement().getTransSemanticConcept();
		List<InstanceExpression> out = new ArrayList<InstanceExpression>();
		if (semElement != null
				&& semElement.getAllSemanticExpressions() != null)
			for (IntSemanticExpression semExpression : semElement
					.getAllSemanticExpressions()) {
				if (semanticExpressions.contains(semExpression)) {
					InstanceExpression instanceExpression = new InstanceExpression(
							false, (SemanticExpression) semExpression);
					instanceExpression
							.createFromSemanticExpression(instElement);
					out.add(instanceExpression);
				}
			}
		if (semElement != null
				&& (semElement instanceof SemanticOverTwoRelation || semElement instanceof SemanticPairwiseRelation)) {
			InstAttribute ia = instElement.getTransSupportMetaElement()
					.getTransInstSemanticElement()
					.getInstAttribute("relationTypesSemExpressions");
			List<InstAttribute> ias = (List<InstAttribute>) ia.getValue();
			for (InstAttribute attribute : ias) {
				String att = attribute.getIdentifier();
				String comp = (String) instElement.getInstAttribute(
						"relationType").getValue();
				if (att.equals(comp))
					for (IntSemanticExpression semExpression : (List<IntSemanticExpression>) attribute
							.getValue()) {
						if (semanticExpressions.contains(semExpression)) {
							InstanceExpression instanceExpression = new InstanceExpression(
									false, (SemanticExpression) semExpression);
							instanceExpression
									.createFromSemanticExpression(instElement);
							out.add(instanceExpression);
						}
					}
			}
		}
		return out;
	}

	protected List<InstanceExpression> createElementInstanceExpressions(
			IntSemanticElement semElement) {
		List<InstanceExpression> out = new ArrayList<InstanceExpression>();
		if (semElement != null
				&& semElement.getAllSemanticExpressions() != null)
			for (IntSemanticExpression semExpression : semElement
					.getAllSemanticExpressions()) {
				InstanceExpression instanceExpression = new InstanceExpression(
						false, (SemanticExpression) semExpression);
				instanceExpression.createFromSemanticExpression(null);
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
			out.add(expression.createSGSExpression());
		}
		return out;
	}

	public HlclProgram getHlCLProgramExpressions(String column) {
		HlclProgram prog = new HlclProgram();
		for (InstanceExpression expression : instanceExpressions.get(column)) {
			// idMap.putAll(transformation.getIdentifiers(hlclFactory));
			prog.add((BooleanExpression) expression.createSGSExpression());
		}
		return prog;
	}

}
