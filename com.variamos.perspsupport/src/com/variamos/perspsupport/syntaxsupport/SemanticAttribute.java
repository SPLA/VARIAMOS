package com.variamos.perspsupport.syntaxsupport;

import com.variamos.hlcl.Domain;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public class SemanticAttribute extends AbstractAttribute {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2582154145921483304L;

	public SemanticAttribute() {
		super();
	}

	public SemanticAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue, int defaultGroup) {
		super(name, type, affectProperties, displayName, defaultValue, defaultGroup);
	}

	public SemanticAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue, int defaultGroup) {
		super(name, type, affectProperties, displayName, enumType, defaultValue, defaultGroup);
	}

	public SemanticAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			Domain domain, int defaultGroup) {
		super(name, type, affectProperties, displayName, defaultValue, domain, defaultGroup);
	}

	public SemanticAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			String hint, int defaultGroup) {
		super(name, type, affectProperties, displayName, defaultValue, hint, defaultGroup);
	}

	public SemanticAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue, String hint, int defaultGroup) {
		super(name, type, affectProperties, displayName, enumType,
				defaultValue, hint, defaultGroup);
	}

	public SemanticAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			String object, Object defaultValue, String hint, int defaultGroup) {
		super(name, type, affectProperties, displayName, enumType, object,
				defaultValue, hint, defaultGroup);
	}

}
