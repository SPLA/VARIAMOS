package com.variamos.perspsupport.syntaxsupport;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public class ExecTemporalControlAttribute extends ExecutionAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3168115245345796844L;

	public ExecTemporalControlAttribute() {
		super();
	}

	/**
	 * 
	 */

	public ExecTemporalControlAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			int defaultGroup) {
		super(name, type, affectProperties, displayName, defaultValue,
				defaultGroup);
	}

	public ExecTemporalControlAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue, int defaultGroup) {
		super(name, type, affectProperties, displayName, enumType,
				defaultValue, defaultGroup);
	}

}
