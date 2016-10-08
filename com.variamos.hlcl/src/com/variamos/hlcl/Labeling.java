package com.variamos.hlcl;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to support Multiple labelings. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2016-02-23
 */
public class Labeling {
	private List<NumericExpression> orderExpressionList;
	private List<LabelingOrder> labelingOrderList;

	private String name;
	private String labelId;
	private boolean once, order, includeLabel, outputSet;
	private int position;
	private List<Identifier> variables;

	public Labeling(String name, String labelId, int position,
			boolean outputSet, boolean includeLabel, boolean once,
			boolean order, List<LabelingOrder> labelingOrderList,
			List<NumericExpression> orderExpressionList

	) {
		super();
		this.name = name;
		this.labelId = labelId;
		this.includeLabel = includeLabel;
		this.outputSet = outputSet;
		this.orderExpressionList = orderExpressionList;
		this.labelingOrderList = labelingOrderList;
		this.position = position;
		this.once = once;
		this.order = order;
		variables = new ArrayList<Identifier>();
	}

	public List<NumericExpression> getOrderExpressionList() {
		return orderExpressionList;
	}

	public void setOrderExpressionList(
			List<NumericExpression> orderExpressionList) {
		this.orderExpressionList = orderExpressionList;
	}

	public List<LabelingOrder> getLabelingOrderList() {
		return labelingOrderList;
	}

	public void setLabelingOrderList(List<LabelingOrder> labelingOrderList) {
		this.labelingOrderList = labelingOrderList;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOnce() {
		return once;
	}

	public void setOnce(boolean once) {
		this.once = once;
	}

	public List<Identifier> getVariables() {
		return variables;
	}

	public boolean isIncludeLabel() {
		return includeLabel;
	}

	public void setIncludeLabel(boolean includeLabel) {
		this.includeLabel = includeLabel;
	}

	public boolean isOutputSet() {
		return outputSet;
	}

	public void setOutputSet(boolean outputSet) {
		this.outputSet = outputSet;
	}

	public void setVariables(List<Identifier> variables) {
		this.variables = variables;
	}

	public void addAttribute(Identifier attribute) {
		variables.add(attribute);
	}

	public boolean hasAttribute(String name) {
		for (Identifier s : variables)
			if (s.getId().equals(name))
				return true;
		return false;
	}

	public Object getName() {
		return name;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public boolean isOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}
}
