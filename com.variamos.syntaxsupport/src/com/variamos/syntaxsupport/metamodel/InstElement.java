package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.cfm.productline.Prototype;

public abstract class InstElement implements Serializable, Prototype,
EditableElement {
	/**
	 * Dynamic storage of modeling, semantic and simulation instance attribute
	 * instances
	 */
	public static final String VAR_IDENTIFIER = "identifier", VAR_INSTATTRIBUTES = "InstAttribute";
	
	protected Map<String, Object> vars = new HashMap<>();

	public InstElement(String identifier)
	{
		vars.put(VAR_IDENTIFIER, identifier);
	}
	
	public Object getVariable(String name) {
		return vars.get(name);
	}
	
	public InstAttribute getInstAttribute(String name) {
		return ((Map<String, InstAttribute>) getVariable(VAR_INSTATTRIBUTES))
				.get(name);
		// return instAttributes.get(name);
	}

	public String getInstAttributeFullIdentifier(String insAttributeLocalId) {
		return this.getIdentifier() + "_"
				+ this.getInstAttribute(insAttributeLocalId).getIdentifier();
	}
}
