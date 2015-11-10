package com.variamos.perspsupport.expressionsupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
public class OperationSubAction {

	private int position;
	private String description;
	private List<OperationSubActionExpType> operationSubActionExpTypes;

	private List<OperationLabeling> operationLabelings;
	private OperationSubActionType operationSubActionType;

	public OperationSubAction(int position, String description,
			OperationSubActionType operationSubActionType) {
		super();
		this.position = position;
		this.description = description;
		this.operationSubActionType = operationSubActionType;
		operationSubActionExpTypes = new ArrayList<OperationSubActionExpType>();
		operationLabelings = new ArrayList<OperationLabeling>();
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void addOperationLabeling(OperationLabeling operationLabeling) {
		operationLabelings.add(operationLabeling);
	}

	public void addOperationSubActionExpType(
			OperationSubActionExpType operationSubActionExpType) {
		operationSubActionExpTypes.add(operationSubActionExpType);
	}

	public List<String> getOperationSubActionExpTypesNames() {
		List<String> out = new ArrayList<String>();
		for (OperationSubActionExpType oper : operationSubActionExpTypes) {
			out.add(this.getDescription() + "-"
					+ oper.getExpressionType().toString());
		}
		return out;

	}

	public Collection<? extends String> getOperLabels() {
		List<String> out = new ArrayList<String>();
		for (OperationLabeling oper : operationLabelings) {
			out.add(this.getDescription() + "-" + oper.getName());
		}
		return out;
	}
}
