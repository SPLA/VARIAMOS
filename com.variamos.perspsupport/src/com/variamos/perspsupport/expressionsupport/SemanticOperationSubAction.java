package com.variamos.perspsupport.expressionsupport;

import java.util.ArrayList;
import java.util.List;

import com.variamos.perspsupport.semanticsupport.AbstractSemanticElement;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.types.OperationSubActionExecType;
import com.variamos.perspsupport.types.OperationSubActionType;

/**
 * TODO A class to support sub actions to generalize the semantic
 * operationalization with GUI edition. Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
public class SemanticOperationSubAction extends AbstractSemanticElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965378108648056600L;
	private int position;
	private List<OperationSubActionExpType> operationSubActionExpTypes;
	private List<AbstractAttribute> inVariables;
	private List<AbstractAttribute> outVariables;

	// private List<OperationLabeling> operationLabelings;
	private OperationSubActionType operationSubActionType;

	public SemanticOperationSubAction(int position, String identifier,
			OperationSubActionType operationSubActionType) {
		super(identifier);
		this.position = position;
		this.operationSubActionType = operationSubActionType;
		operationSubActionExpTypes = new ArrayList<OperationSubActionExpType>();
		// operationLabelings = new ArrayList<OperationLabeling>();
		inVariables = new ArrayList<AbstractAttribute>();
		outVariables = new ArrayList<AbstractAttribute>();
	}

	public SemanticOperationSubAction() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public List<OperationSubActionExpType> getOperationSubActionExpTypes() {
		return operationSubActionExpTypes;
	}

	public void setOperationSubActionExpTypes(
			List<OperationSubActionExpType> operationSubActionExpType) {
		this.operationSubActionExpTypes = operationSubActionExpType;
	}

	public OperationSubActionType getOperationSubActionType() {
		return operationSubActionType;
	}

	public void setOperationSubActionType(
			OperationSubActionType operationSubActionType) {
		this.operationSubActionType = operationSubActionType;
	}

	// public void addOperationLabeling(OperationLabeling operationLabeling) {
	// operationLabelings.add(operationLabeling);
	// }

	public void addOperationSubActionExpType(
			OperationSubActionExpType operationSubActionExpType) {
		operationSubActionExpTypes.add(operationSubActionExpType);
	}

	public List<String> getOperationSubActionExpTypesNames() {
		List<String> out = new ArrayList<String>();
		for (OperationSubActionExpType oper : operationSubActionExpTypes) {
			out.add(this.getIdentifier() + "-"
					+ oper.getExpressionType().toString());
		}
		return out;

	}

	// public Collection<? extends String> getOperLabelNames() {
	// List<String> out = new ArrayList<String>();
	// for (OperationLabeling oper : operationLabelings) {
	// out.add(this.getIdentifier() + "-" + oper.getName());
	// }
	// return out;
	// }

	// public List<OperationLabeling> getOperLabels() {
	// List<OperationLabeling> out = new ArrayList<OperationLabeling>();
	// for (OperationLabeling oper : operationLabelings) {
	// out.add(oper);
	// }
	// return out;
	// }

	public OperationSubActionExpType getOperationSubActionExpType(
			OperationSubActionExecType expressionType) {
		for (OperationSubActionExpType oper : operationSubActionExpTypes) {
			if (oper.getExpressionType().equals(expressionType))
				return oper;
		}
		return null;
	}

	public boolean hasInVariable(String variable) {
		for (AbstractAttribute var : inVariables)
			if (var.getName().equals(variable))
				return true;
		return false;
	}

	public List<AbstractAttribute> getInVariables() {
		return inVariables;
	}

	public void setInVariables(List<AbstractAttribute> inVariables) {
		this.inVariables = inVariables;
	}

	public List<AbstractAttribute> getOutVariables() {
		return outVariables;
	}

	public void setOutVariables(List<AbstractAttribute> outVariables) {
		this.outVariables = outVariables;
	}

	public boolean hasOutVariable(String variable) {
		for (AbstractAttribute var : outVariables)
			if (var.getName().equals(variable))
				return true;
		return false;
	}

	public void addInVariable(AbstractAttribute attribute) {
		inVariables.add(attribute);
	}

	public void addOutVariable(AbstractAttribute attribute) {
		outVariables.add(attribute);
	}
}
