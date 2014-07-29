package com.cfm.productline.configurator.treetable;

import com.cfm.hlcl.Domain;

public class ConfigurationValue {
	private Integer value;
	private Domain domain;
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Domain getDomain() {
		return domain;
	}
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
}
