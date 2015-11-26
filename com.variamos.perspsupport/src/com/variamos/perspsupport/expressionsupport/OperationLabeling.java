package com.variamos.perspsupport.expressionsupport;

import java.util.ArrayList;
import java.util.List;

import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;

public class OperationLabeling {
	private String orderExpressions;
	private String name;
	private boolean once;
	private int position;
	private List<AbstractAttribute> variables;

	public OperationLabeling(String name, String orderExpressions,
			int position, boolean once) {
		super();
		this.name = name;
		this.orderExpressions = orderExpressions;
		this.position = position;
		this.once = once;
		variables = new ArrayList<AbstractAttribute>();
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
