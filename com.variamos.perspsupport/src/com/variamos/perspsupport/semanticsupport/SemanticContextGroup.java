package com.variamos.perspsupport.semanticsupport;

import com.variamos.perspsupport.syntaxsupport.SemanticAttribute;

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

	private static final String 
			VAR_VARIABLENAME = "name",
			VAR_VARIABLENAMENAME = "Group Name",

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

		this.addPropEditableAttribute("01#" + VAR_VARIABLENAME);
		this.addPropEditableAttribute("07#" + VAR_INSTANCENUMBER);
		this.addPropEditableAttribute("08#" + VAR_EXTVISIBLE);
		this.addPropEditableAttribute("09#" + VAR_EXTCONTROL);

		this.addPropVisibleAttribute("01#" + VAR_VARIABLENAME);
		this.addPropVisibleAttribute("07#" + VAR_INSTANCENUMBER);
		this.addPropVisibleAttribute("08#" + VAR_EXTVISIBLE);
		this.addPropVisibleAttribute("09#" + VAR_EXTCONTROL);

		this.addPanelVisibleAttribute("01#" + VAR_VARIABLENAME);

	}

	public String toString() {

		return " VAR: " + super.toString();
	}
}
