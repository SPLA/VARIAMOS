package com.variamos.perspsupport.syntaxsupport;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public class GlobalConfigAttribute extends ConfigurationAttribute {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7430454253717334119L;

	public GlobalConfigAttribute() {
		super();
	}

	/**
	 * 
	 */

	public GlobalConfigAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		super(name, type, affectProperties, displayName, defaultValue,
				defaultGroup, propTabPosition, propTabEditionCondition,
				propTabVisualCondition, elementDisplayPosition,
				elementDisplaySpacers, elementDisplayCondition);
	}

	public GlobalConfigAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue, int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		super(name, type, affectProperties, displayName, enumType,
				defaultValue, defaultGroup, propTabPosition, propTabEditionCondition,
				propTabVisualCondition, elementDisplayPosition,
				elementDisplaySpacers, elementDisplayCondition);
	}

}
