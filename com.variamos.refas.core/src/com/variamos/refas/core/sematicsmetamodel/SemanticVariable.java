package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.refas.core.types.VariableType;
import com.variamos.refas.core.types.VariationScopeType;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;
import com.variamos.syntaxsupport.metamodel.InstEnumeration;

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

	public static final String VAR_SCOPE = "scope",
			VAR_SCOPENAME = "Scope",
			VAR_SCOPECLASS = VariationScopeType.class.getCanonicalName(),
			
			VAR_CONTEXTTYPE = "contextType",
			VAR_CONTEXTTYPENAME = "Context Type",
			VAR_CONTEXTTYPECLASS = "com.variamos.refas.core.types.ContextType",
			
			VAR_NAME = "name",
			VAR_NAMENAME = "Name",
			
			VAR_VARIABLETYPE = "variableType",
			VAR_VARIABLETYPENAME = "Variable Type",
			VAR_VARIABLETYPECLASS = VariableType.class.getCanonicalName(),
			
			VAR_ENUMERATIONTYPE = "enumerationType",
			VAR_ENUMERATIONTYPENAME = "Enumeration",
			VAR_ENUMERATIONTYPECLASS = InstEnumeration.class.getCanonicalName();

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
				"Enumeration", false, VAR_SCOPENAME, VAR_SCOPECLASS,  "user", ""));
		putSemanticAttribute(VAR_CONTEXTTYPE, new SemanticAttribute(
				VAR_CONTEXTTYPE, "Enumeration", false, VAR_CONTEXTTYPENAME, VAR_CONTEXTTYPECLASS,
				"profiled", ""));
		putSemanticAttribute(VAR_NAME, new SemanticAttribute(VAR_NAME,
				"String", false, VAR_NAMENAME,  ""));
		putSemanticAttribute(VAR_VARIABLETYPE, new SemanticAttribute(
				VAR_VARIABLETYPE, "Enumeration", true, VAR_VARIABLETYPENAME,  VAR_VARIABLETYPECLASS,
				 "String", ""));
		putSemanticAttribute(VAR_ENUMERATIONTYPE, new SemanticAttribute(
				VAR_ENUMERATIONTYPE, "Class", false, VAR_ENUMERATIONTYPENAME, VAR_ENUMERATIONTYPECLASS,"ME",
				"String", ""));

		this.addDisPropEditableAttribute("01#" + VAR_NAME);
		this.addDisPropEditableAttribute("02#" + VAR_VARIABLETYPE);
		this.addDisPropEditableAttribute("03#" + VAR_ENUMERATIONTYPE);
		this.addDisPropEditableAttribute("04#" + VAR_CONTEXTTYPE);
		this.addDisPropEditableAttribute("05#" + VAR_SCOPE);

		this.addDisPropVisibleAttribute("01#" + VAR_NAME);
		this.addDisPropVisibleAttribute("02#" + VAR_VARIABLETYPE);
		this.addDisPropVisibleAttribute("03#" + VAR_ENUMERATIONTYPE+"#"+VAR_VARIABLETYPE+"#==#"+"Enumeration");
		this.addDisPropVisibleAttribute("04#" + VAR_CONTEXTTYPE);
		this.addDisPropVisibleAttribute("05#" + VAR_SCOPE);
		
		this.addDisPanelVisibleAttribute("01#" + VAR_NAME);
		this.addDisPanelVisibleAttribute("02#" + VAR_VARIABLETYPE+"#"+VAR_VARIABLETYPE+"#!=#"+"Enumeration");
		this.addDisPanelVisibleAttribute("03#" + VAR_ENUMERATIONTYPE+"#"+VAR_VARIABLETYPE+"#==#"+"Enumeration");
		this.addDisPanelVisibleAttribute("04#" + VAR_CONTEXTTYPE);
		this.addDisPanelVisibleAttribute("05#" + VAR_SCOPE);

		this.addDisPanelSpacersAttribute("#" + VAR_NAME + "#\n");
		this.addDisPanelSpacersAttribute("{#" + VAR_VARIABLETYPE + "#} ");
		this.addDisPanelSpacersAttribute("{#" + VAR_CONTEXTTYPE + "#} ");
		this.addDisPanelSpacersAttribute("{#" + VAR_SCOPE + "#}");

	}

	public String toString() {

		return " VAR: " + super.toString();
	}
}
