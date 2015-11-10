package com.variamos.perspsupport.expressionsupport;

import java.util.List;

public class OperationLabeling {
	private String orderExpressions;
	private String name;
	private boolean once;
	private int position;
	private List<String> variables;

	public OperationLabeling(String name, String orderExpressions,
			int position, boolean once) {
		super();
		this.name = name;
		this.orderExpressions = orderExpressions;
		this.position = position;
		this.once = once;
	}

	public String getOrderExpressions() {
		return orderExpressions;
	}

	public void setOrderExpressions(String orderExpressions) {
		this.orderExpressions = orderExpressions;
	}

	public boolean isOnce() {
		return once;
	}

	public void setOnce(boolean once) {
		this.once = once;
	}

	public List<String> getVariables() {
		return variables;
	}

	public void setVariables(List<String> variables) {
		this.variables = variables;
	}

	public Object getName() {
		return name;
	}
}
