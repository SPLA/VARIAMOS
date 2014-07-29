package com.cfm.hlcl;

import java.util.List;

public class ListDefinitionExpression implements Expression{
	private List<Identifier> values;
	
	public ListDefinitionExpression(List<Identifier> values){
		this.values = values;
	}

	public List<Identifier> getValues() {
		return values;
	}

	public void setValues(List<Identifier> values) {
		this.values = values;
	}
}
