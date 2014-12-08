package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

public class SemanticVariable extends AbstractSemanticVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5538738414024566452L;

	public static final String VAR_SCOPE = "scope",
			VAR_SCOPECLASS = "com.variamos.refas.core.types.VariationScopeType",
			VAR_CONTEXTTYPE = "contextType",
			VAR_CONTEXTTYPECLASS = "com.variamos.refas.core.types.ContextType",
			VAR_CATEGORY = "category",
			VAR_VARIABLETYPE = "variableType",
			VAR_VARIABLETYPECLASS = "com.variamos.refas.core.types.VariableType";

	public SemanticVariable() {
		this(null, null);
	}

	public SemanticVariable(String name) {
		this(null,name);
	}

	public SemanticVariable(AbstractSemanticVertex parentConcept, String name) {
		super(parentConcept, name, true);
		defineSemanticAttributes();
	}

	private void defineSemanticAttributes() {

		putSemanticAttribute(VAR_SCOPE, new SemanticAttribute(VAR_SCOPE,
				"Enumeration", VAR_SCOPECLASS, "user", ""));
		putSemanticAttribute(VAR_CONTEXTTYPE, new SemanticAttribute(
				VAR_CONTEXTTYPE, "Enumeration", VAR_CONTEXTTYPECLASS,
				"profiled", ""));
		putSemanticAttribute(VAR_CATEGORY, new SemanticAttribute(VAR_CATEGORY,
				"String", "<<new>>"));
		putSemanticAttribute(VAR_VARIABLETYPE, new SemanticAttribute(
				VAR_VARIABLETYPE, "Enumeration", VAR_VARIABLETYPECLASS,
				"String", ""));

		this.addDisPropEditableAttribute("01#" + VAR_CATEGORY);
		this.addDisPropEditableAttribute("02#" + VAR_VARIABLETYPE);
		this.addDisPropEditableAttribute("03#" + VAR_CONTEXTTYPE);
		this.addDisPropEditableAttribute("04#" + VAR_SCOPE);

		this.addDisPanelVisibleAttribute("01#" + VAR_CATEGORY);
		this.addDisPanelVisibleAttribute("02#" + VAR_VARIABLETYPE);
		this.addDisPanelVisibleAttribute("03#" + VAR_CONTEXTTYPE);
		this.addDisPanelVisibleAttribute("04#" + VAR_SCOPE);

		this.addDisPanelSpacersAttribute("#" + VAR_CATEGORY + "#\n");
		this.addDisPanelSpacersAttribute("{#" + VAR_VARIABLETYPE + "#} ");
		this.addDisPanelSpacersAttribute("{#" + VAR_CONTEXTTYPE + "#} ");
		this.addDisPanelSpacersAttribute("{#" + VAR_SCOPE + "#}");

	}

	public String toString() {

		return " VAR: " + super.toString();
	}
}
