package com.variamos.perspsupport.syntaxsupport;

import com.variamos.hlcl.Domain;

public class ExecutionAttribute extends AbstractAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2574853604112183883L;

	public ExecutionAttribute() {
		super();
	}

	public ExecutionAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			int defaultGroup) {
		super(name, type, affectProperties, displayName, defaultValue,
				defaultGroup);
	}

	public ExecutionAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			Domain domain, int defaultGroup) {
		super(name, type, affectProperties, displayName, defaultValue, domain,
				defaultGroup);
	}

	public ExecutionAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue, int defaultGroup) {
		super(name, type, affectProperties, displayName, defaultValue,
				defaultGroup);
	}

}
