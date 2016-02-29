package com.variamos.perspsupport.syntaxsupport;

import com.variamos.hlcl.Domain;
import com.variamos.semantic.types.AttributeType;

public class ConfigurationAttribute extends AbstractAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2574853604112183883L;

	public ConfigurationAttribute() {
		super();
	}

	public ConfigurationAttribute(String name, String type,
			AttributeType attributeType, boolean affectProperties,
			String displayName, Object defaultValue, int defaultGroup,
			int propTabPosition, String propTabEditionCondition,
			String propTabVisualCondition, int elementDisplayPosition,
			String elementDisplaySpacers, String elementDisplayCondition) {
		super(name, type, attributeType, affectProperties, displayName,
				defaultValue, defaultGroup, propTabPosition,
				propTabEditionCondition, propTabVisualCondition,
				elementDisplayPosition, elementDisplaySpacers,
				elementDisplayCondition);
	}

	public ConfigurationAttribute(String name, String type,
			AttributeType attributeType, boolean affectProperties,
			String displayName, Object defaultValue, Domain domain,
			int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		super(name, type, attributeType, affectProperties, displayName,
				defaultValue, domain, defaultGroup, propTabPosition,
				propTabEditionCondition, propTabVisualCondition,
				elementDisplayPosition, elementDisplaySpacers,
				elementDisplayCondition);
	}

	public ConfigurationAttribute(String name, String type,
			AttributeType attributeType, boolean affectProperties,
			String displayName, String enumType, Object defaultValue,
			int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		super(name, type, attributeType, affectProperties, displayName,
				defaultValue, defaultGroup, propTabPosition,
				propTabEditionCondition, propTabVisualCondition,
				elementDisplayPosition, elementDisplaySpacers,
				elementDisplayCondition);
	}

}
