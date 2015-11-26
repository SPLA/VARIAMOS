package com.variamos.perspsupport.semanticsupport;

import com.variamos.perspsupport.instancesupport.InstEnumeration;
import com.variamos.perspsupport.types.VariableType;

/**
 * A class to represent the Semantics of Variables. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-02
 * @see com.cfm.productline.
 */
public class SemanticVariable extends AbstractSemanticVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5538738414024566452L;

	public static final String VAR_NAME = "name",
			VAR_NAMENAME = "Name",

			VAR_VALUE = "value",
			VAR_VALUENAME = "Value",

			VAR_EXTVISIBLE = "ExtVisible",
			VAR_EXTVISIBLENAME = "Externally Visible",

			VAR_EXTCONTROL = "ExtControl",
			VAR_EXTCONTROLNAME = "Externally Controlled",

			VAR_VARIABLETYPE = "variableType",
			VAR_VARIABLETYPENAME = "Variable Type",
			VAR_VARIABLETYPECLASS = VariableType.class.getCanonicalName(),

			VAR_CONTEXT = "isContext",
			VAR_CONTEXTNAME = "Context Defined",

			VAR_VARIABLEDOMAIN = "variableDomain",
			VAR_VARIABLEDOMAINNAME = "Variable Domain",

			VAR_ENUMERATIONTYPE = "enumerationType",
			VAR_ENUMERATIONTYPENAME = "Enumeration",
			VAR_ENUMERATIONTYPECLASS = InstEnumeration.class.getCanonicalName(),

			VAR_VARIABLECONFIGVALUE = "variableConfigValue",
			VAR_VARIABLECONFIGVALUENAME = "Configured Value",

			VAR_VARIABLECONFIGDOMAIN = "variableConfigDomain",
			VAR_VARIABLECONFIGDOMAINNAME = "Configured Domain";

	public SemanticVariable() {
		this(null, null);
	}

	public SemanticVariable(String name) {
		this(null, name);
	}

	public SemanticVariable(AbstractSemanticVertex parentConcept, String name) {
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

	}

	public String toString() {

		return " VAR: " + super.toString();
	}
}
