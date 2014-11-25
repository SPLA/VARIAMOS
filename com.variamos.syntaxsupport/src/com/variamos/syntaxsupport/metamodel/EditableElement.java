package com.variamos.syntaxsupport.metamodel;

import java.util.Map;

public interface EditableElement {
	public String getIdentifier();
	public InstAttribute[] getEditableVariables();
	public Map<String, InstAttribute> getInstAttributes() ;
}
