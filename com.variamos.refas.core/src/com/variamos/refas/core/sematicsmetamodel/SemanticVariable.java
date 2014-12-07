package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

public class SemanticVariable extends AbstractSemanticVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5538738414024566452L;

	private static final String VAR_SCOPE = "scope",
			VAR_SCOPECLASS = "com.variamos.refas.core.types.VariationScopeType",
			VAR_CONTEXTTYPE = "contextType",
			VAR_CONTEXTTYPECLASS = "com.variamos.refas.core.types.ContextType",
			VAR_VARIABLENAME = "variableName",
			VAR_VARIABLETYPE = "variableType",
			VAR_VARIABLETYPECLASS = "com.variamos.refas.core.types.VariableType",
			VAR_DOMAIN = "domain";

	public SemanticVariable() {
		super();
		defineSemanticAttributes();
	}

	public SemanticVariable(String name) {
		super(name, true);
		defineSemanticAttributes();
	}

	public SemanticVariable(AbstractSemanticVertex parentConcept, String name) {
		super(parentConcept, name, true);
		defineSemanticAttributes();
	}

	private void defineSemanticAttributes() {

		putSemanticAttribute(VAR_SCOPE, new SemanticAttribute("Scope",
				"Enumeration", VAR_SCOPECLASS, "user", ""));
		putSemanticAttribute(VAR_CONTEXTTYPE,
				new SemanticAttribute("Context Type", "Enumeration",
						VAR_CONTEXTTYPECLASS, "profiled", ""));
		putSemanticAttribute(VAR_VARIABLENAME, new SemanticAttribute("Name",
				"String", "<<new>>"));
		putSemanticAttribute(VAR_VARIABLETYPE, new SemanticAttribute(
				"Variable Type", "Enumeration",VAR_VARIABLETYPECLASS,"String", ""));
		putSemanticAttribute(VAR_DOMAIN, new SemanticAttribute("Domain ",
				"String", ""));

		this.addDisPropEditableAttribute("01#" + VAR_VARIABLENAME);
		this.addDisPropEditableAttribute("02#" + VAR_VARIABLETYPE);
		this.addDisPropEditableAttribute("03#" + VAR_CONTEXTTYPE);
		this.addDisPropEditableAttribute("04#" + VAR_SCOPE);
		this.addDisPropEditableAttribute("05#" + VAR_DOMAIN);

		this.addDisPanelVisibleAttribute("01#" + VAR_VARIABLENAME);
		this.addDisPanelVisibleAttribute("02#" + VAR_VARIABLETYPE);
		this.addDisPanelVisibleAttribute("03#" + VAR_CONTEXTTYPE);
		this.addDisPanelVisibleAttribute("04#" + VAR_SCOPE);

		this.addDisPanelSpacersAttribute("\n{#" + VAR_VARIABLETYPE + "#} ");
		this.addDisPanelSpacersAttribute("{#" + VAR_CONTEXTTYPE + "#} ");
		this.addDisPanelSpacersAttribute("{#" + VAR_SCOPE + "#}");

	}

	public String toString() {

		return " VAR: " + super.toString();
	}
}
