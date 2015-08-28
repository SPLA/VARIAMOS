package com.variamos.perspsupport.syntaxsupport;

import com.variamos.hlcl.Domain;

public class SyntaxAttribute extends AbstractAttribute {
	/**
	 * 
	 */
	private static final long serialVersionUID = 552704923296673747L;

	public SyntaxAttribute() {
		super();
	}

	public SyntaxAttribute(String name, String type, boolean affectProperties,
			String displayName, Object defaultValue, int defaultGroup) {
		super(name, type, affectProperties, displayName, defaultValue,
				defaultGroup);
	}

	public SyntaxAttribute(String name, String type, boolean affectProperties,
			String displayName, String enumType, Object defaultValue,
			int defaultGroup) {
		super(name, type, affectProperties, displayName, enumType,
				defaultValue, defaultGroup);
	}

	public SyntaxAttribute(String name, String type, boolean affectProperties,
			String displayName, Object defaultValue, Domain domain,
			int defaultGroup) {
		super(name, type, affectProperties, displayName, defaultValue, domain,
				defaultGroup);
	}
}
