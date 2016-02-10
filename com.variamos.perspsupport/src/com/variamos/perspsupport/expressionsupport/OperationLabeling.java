package com.variamos.perspsupport.expressionsupport;

import java.util.ArrayList;
import java.util.List;

import com.variamos.compiler.solverSymbols.LabelingOrder;
import com.variamos.hlcl.NumericExpression;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;

public class OperationLabeling {
	private List<NumericExpression> orderExpressionList;
	private List<LabelingOrder> labelingOrderList;

	private String name;
	private boolean once;
	private int position;
	private List<AbstractAttribute> variables;

	public OperationLabeling(String name, int position, boolean once,
			List<LabelingOrder> labelingOrderList,
			List<NumericExpression> orderExpressionList

	) {
		super();
		this.name = name;
		this.orderExpressionList = orderExpressionList;
		this.labelingOrderList = labelingOrderList;
		this.position = position;
		this.once = once;
		variables = new ArrayList<AbstractAttribute>();
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

	public List<AbstractAttribute> getVariables() {
		return variables;
	}

	public void setVariables(List<AbstractAttribute> variables) {
		this.variables = variables;
	}

	public void addAttribute(AbstractAttribute attribute) {
		variables.add(attribute);
	}

	public boolean hasAttribute(String name) {
		for (AbstractAttribute s : variables)
			if (s.getName().equals(name))
				return true;
		return false;
	}

	public Object getName() {
		return name;
	}
}
