package com.variamos.syntax.metamodelsupport;

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
			boolean affectProperties, String displayName, Object defaultValue) {
		super(name, type, affectProperties, displayName, defaultValue);
	}

	public SemanticAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue) {
		super(name, type, affectProperties, displayName, enumType, defaultValue);
	}

	public SemanticAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			Domain domain) {
		super(name, type, affectProperties, displayName, defaultValue, domain);
	}

	public SemanticAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			String hint) {
		super(name, type, affectProperties, displayName, defaultValue, hint);
	}

	public SemanticAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue, String hint) {
		super(name, type, affectProperties, displayName, enumType,
				defaultValue, hint);
	}

	public SemanticAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			String object, Object defaultValue, String hint) {
		super(name, type, affectProperties, displayName, enumType, object,
				defaultValue, hint);
	}

}
