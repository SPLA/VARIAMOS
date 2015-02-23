package com.variamos.perspsupport.semanticsupport;

import com.variamos.perspsupport.syntaxsupport.SemanticAttribute;
import com.variamos.semantic.types.ContextType;
import com.variamos.semantic.types.VariationScopeType;

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

	private static final String VAR_SCOPE = "scope", VAR_SCOPENAME = "Scope",
			VAR_SCOPECLASS = VariationScopeType.class.getCanonicalName(),
			VAR_CONTEXTTYPE = "contextType",
			VAR_CONTEXTTYPENAME = "Context Type",
			VAR_CONTEXTTYPECLASS = ContextType.class.getCanonicalName(),
			VAR_VARIABLENAME = "variableName",
			VAR_VARIABLENAMENAME = "Variable Name",

			VAR_INSTANCENUMBER = "instances",
			VAR_INSTANCENUMBERNAME = "Number of Instances",

			VAR_EXTVISIBLE = "ExtVisible",
			VAR_EXTVISIBLENAME = "Externally Visible",

			VAR_EXTCONTROL = "ExtControl",
			VAR_EXTCONTROLNAME = "Externally Controlled";

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

		// putSemanticAttribute(VAR_SCOPE,
		// new SemanticAttribute(VAR_SCOPE, "Enumeration", false,
		// VAR_SCOPENAME, VAR_SCOPECLASS, "user", ""));
		// putSemanticAttribute(VAR_CONTEXTTYPE, new SemanticAttribute(
		// VAR_CONTEXTTYPE, "Enumeration", false, VAR_CONTEXTTYPENAME,
		// VAR_CONTEXTTYPECLASS, "profiled", ""));
		putSemanticAttribute(VAR_VARIABLENAME, new SemanticAttribute(
				VAR_VARIABLENAME, "String", false, VAR_VARIABLENAMENAME,
				"<<new>>"));
		putSemanticAttribute(VAR_INSTANCENUMBER, new SemanticAttribute(
				VAR_INSTANCENUMBER, "Integer", false, VAR_INSTANCENUMBERNAME,
				"1"));
		putSemanticAttribute(VAR_EXTVISIBLE, new SemanticAttribute(
				VAR_EXTVISIBLE, "Boolean", false, VAR_EXTVISIBLENAME, false));
		putSemanticAttribute(VAR_EXTCONTROL, new SemanticAttribute(
				VAR_EXTCONTROL, "Boolean", false, VAR_EXTCONTROLNAME, false));
		// putSemanticAttribute(VAR_DOMAIN, new SemanticAttribute(VAR_DOMAIN,
		// "String", false, VAR_DOMAINNAME, ""));

		this.addPropEditableAttribute("01#" + VAR_VARIABLENAME);
		// this.addPropEditableAttribute("03#" + VAR_CONTEXTTYPE);
		// this.addPropEditableAttribute("04#" + VAR_SCOPE);
		// this.addPropEditableAttribute("05#" + VAR_DOMAIN);
		this.addPropEditableAttribute("07#" + VAR_INSTANCENUMBER);
		this.addPropEditableAttribute("08#" + VAR_EXTVISIBLE);
		this.addPropEditableAttribute("09#" + VAR_EXTCONTROL);

		this.addPropVisibleAttribute("01#" + VAR_VARIABLENAME);
		// this.addPropVisibleAttribute("03#" + VAR_CONTEXTTYPE);
		// this.addPropVisibleAttribute("04#" + VAR_SCOPE);
		// this.addPropVisibleAttribute("05#" + VAR_DOMAIN);
		this.addPropVisibleAttribute("07#" + VAR_INSTANCENUMBER);
		this.addPropVisibleAttribute("08#" + VAR_EXTVISIBLE);
		this.addPropVisibleAttribute("09#" + VAR_EXTCONTROL);

		this.addPanelVisibleAttribute("01#" + VAR_VARIABLENAME);
		// this.addPanelVisibleAttribute("03#" + VAR_CONTEXTTYPE);
		// this.addPanelVisibleAttribute("04#" + VAR_SCOPE);

		// this.addPanelSpacersAttribute("{#" + VAR_CONTEXTTYPE + "#} ");
		// this.addPanelSpacersAttribute("{#" + VAR_SCOPE + "#}");

	}

	public String toString() {

		return " VAR: " + super.toString();
	}
}
