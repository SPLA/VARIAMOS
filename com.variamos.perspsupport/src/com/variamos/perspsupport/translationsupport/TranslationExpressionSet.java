package com.variamos.perspsupport.translationsupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.Identifier;
import com.variamos.perspsupport.expressionsupport.InstanceExpression;
import com.variamos.perspsupport.expressionsupport.OperationAction;
import com.variamos.perspsupport.expressionsupport.OperationSubAction;
import com.variamos.perspsupport.expressionsupport.OperationSubActionExpType;
import com.variamos.perspsupport.expressionsupport.SemanticExpression;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.perspsupport.semanticinterface.IntSemanticElement;
import com.variamos.perspsupport.semanticinterface.IntSemanticExpression;
import com.variamos.perspsupport.types.OperationSubActionExecType;

/**
 * A class to represent the constraints. Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public class TranslationExpressionSet {

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
		super();
		instanceExpressions = new HashMap<String, List<InstanceExpression>>();
		this.idMap = idMap;
		this.hlclFactory = hlclFactory;
		this.operation = operation;
	}

	public void addExpressions(RefasModel refas, InstElement instElement,
			String subAction, OperationSubActionExecType expressionType) {
		OperationAction operAction = refas.getSemanticRefas()
				.getOperationActions().get(operation);
		OperationSubAction operSubAction = operAction
				.getExpressionSubAction(subAction);
		OperationSubActionExpType operExpType = operSubAction
				.getOperationSubActionExpType(expressionType);
		List<SemanticExpression> semExp = operExpType.getSemanticExpressions();
		List<InstanceExpression> out = new ArrayList<InstanceExpression>();
		if (instElement == null)
			for (InstElement instE : refas.getElements()) {
				out.addAll(createElementInstanceExpressions(instE, semExp));
			}
		else
			out.addAll(createElementInstanceExpressions(instElement, semExp));

		instanceExpressions.put(subAction + "-" + expressionType, out);

	}

	protected List<InstanceExpression> createElementInstanceExpressions(
			InstElement instElement,
			List<SemanticExpression> semanticExpressions) {
		IntSemanticElement semElement = instElement
				.getTransSupportMetaElement().getTransSemanticConcept();
		List<InstanceExpression> out = new ArrayList<InstanceExpression>();
		if (semElement != null && semElement.getSemanticExpressions() != null)
			for (IntSemanticExpression semExpression : semElement
					.getSemanticExpressions()) {
				if (semanticExpressions.contains(semExpression)) {
					InstanceExpression instanceExpression = new InstanceExpression(
							false, (SemanticExpression) semExpression);
					instanceExpression
							.createFromSemanticExpression(instElement);
					out.add(instanceExpression);
				}
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
