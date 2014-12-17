package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

/**
 * A class to represent the edges at semantic level. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-08
 * @see com.cfm.productline.
 */
public class SemanticContextGroup extends AbstractSemanticVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5538738414024566452L;

	private static final String VAR_SCOPE = "scope",
			VAR_SCOPENAME = "Scope",
			VAR_SCOPECLASS = "com.variamos.refas.core.types.VariationScopeType",
			VAR_CONTEXTTYPE = "contextType",
			VAR_CONTEXTTYPENAME = "Context Type",
			VAR_CONTEXTTYPECLASS = "com.variamos.refas.core.types.ContextType",
			VAR_VARIABLENAME = "variableName",
			VAR_VARIABLENAMENAME = "Variable Name", VAR_DOMAIN = "domain",
			VAR_DOMAINNAME = "Domain";

	public SemanticContextGroup() {
		super();
		defineSemanticAttributes();
	}

	public SemanticContextGroup(String name) {
		super(name, true);
		defineSemanticAttributes();
	}

	public SemanticContextGroup(AbstractSemanticVertex parentConcept,
			String name) {
		super(parentConcept, name, true);
		defineSemanticAttributes();
	}

	private void defineSemanticAttributes() {

		putSemanticAttribute(VAR_SCOPE,
				new SemanticAttribute(VAR_SCOPE, "Enumeration", false,
						VAR_SCOPENAME, VAR_SCOPECLASS, "user", ""));
		putSemanticAttribute(VAR_CONTEXTTYPE, new SemanticAttribute(
				VAR_CONTEXTTYPE, "Enumeration", false, VAR_CONTEXTTYPENAME,
				VAR_CONTEXTTYPECLASS, "profiled", ""));
		putSemanticAttribute(VAR_VARIABLENAME, new SemanticAttribute(
				VAR_VARIABLENAME, "String", false, VAR_VARIABLENAMENAME,
				"<<new>>"));
		putSemanticAttribute(VAR_DOMAIN, new SemanticAttribute(VAR_DOMAIN,
				"String", false, VAR_DOMAINNAME, ""));

		this.addDisPropEditableAttribute("01#" + VAR_VARIABLENAME);
		this.addDisPropEditableAttribute("03#" + VAR_CONTEXTTYPE);
		this.addDisPropEditableAttribute("04#" + VAR_SCOPE);
		this.addDisPropEditableAttribute("05#" + VAR_DOMAIN);
		
		this.addDisPropVisibleAttribute("01#" + VAR_VARIABLENAME);
		this.addDisPropVisibleAttribute("03#" + VAR_CONTEXTTYPE);
		this.addDisPropVisibleAttribute("04#" + VAR_SCOPE);
		this.addDisPropVisibleAttribute("05#" + VAR_DOMAIN);

		this.addDisPanelVisibleAttribute("01#" + VAR_VARIABLENAME);
		this.addDisPanelVisibleAttribute("03#" + VAR_CONTEXTTYPE);
		this.addDisPanelVisibleAttribute("04#" + VAR_SCOPE);

		this.addDisPanelSpacersAttribute("{#" + VAR_CONTEXTTYPE + "#} ");
		this.addDisPanelSpacersAttribute("{#" + VAR_SCOPE + "#}");

	}

	public String toString() {

		return " VAR: " + super.toString();
	}
}
