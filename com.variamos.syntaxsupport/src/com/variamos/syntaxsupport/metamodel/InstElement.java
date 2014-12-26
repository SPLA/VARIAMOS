package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.variamos.syntaxsupport.metametamodel.MetaElement;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;

public abstract class InstElement implements Serializable,
EditableElement {
	/**
	 * Dynamic storage of modeling, semantic and simulation instance attribute
	 * instances
	 */
	public static final String VAR_IDENTIFIER = "identifier", VAR_INSTATTRIBUTES = "InstAttribute";
	
	protected Map<String, Object> vars = new HashMap<>();
	
	protected IntSemanticElement editableSemanticElement;
	
	protected MetaElement editableMetaElement;

	public InstElement(String identifier)
	{
		vars.put(VAR_IDENTIFIER, identifier);
	}
	
	public IntSemanticElement getEditableSemanticElement() {
		return editableSemanticElement;
	}

	public void setEditableSemanticElement(IntSemanticElement editableSemanticElement) {
		this.editableSemanticElement = editableSemanticElement;
	}

	public MetaElement getEditableMetaElement() {
		return editableMetaElement;
	}
	
	public abstract MetaElement getSupportMetaElement();

	public void setEditableMetaElement(MetaElement metaElement) {
		this.editableMetaElement = metaElement;
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
