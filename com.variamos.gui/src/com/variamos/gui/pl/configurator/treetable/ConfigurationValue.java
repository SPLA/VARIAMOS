package com.variamos.gui.pl.configurator.treetable;

import com.variamos.hlcl.model.domains.IntDomain;

public class ConfigurationValue {
	private Integer value;
	private IntDomain domain;
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public IntDomain getDomain() {
		return domain;
	}
	public void setDomain(IntDomain domain) {
		this.domain = domain;
	}
	
}
