package com.variamos.syntaxsupport.metamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import com.cfm.productline.Variable;
import com.cfm.productline.type.StringType;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;
import com.variamos.syntaxsupport.metametamodel.MetaVertex;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class InstConcept extends InstElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -351673247632968251L;
	/**
	 * 
	 */

	public static final String VAR_METACONCEPT = "MetaConceptIde";
	// protected Map<String, MetaConcept> vars = new HashMap<>();
	private MetaConcept metaConcept;

	public InstConcept() {
		super("");
	}

	public InstConcept(MetaConcept metaConcept) {
		super("");
		vars.put(VAR_METACONCEPT, metaConcept.getIdentifier());
		this.metaConcept = metaConcept;
		createInstAttributes();
	}

	public InstConcept(String identifier, MetaConcept metaConcept,
			Map<String, InstAttribute> attributes,
			Map<String, InstEdge> relations) {
		super(identifier, attributes, relations);
		vars.put(VAR_METACONCEPT, metaConcept.getIdentifier());
		this.metaConcept = metaConcept;
		createInstAttributes();
	}

	public InstConcept(String identifier, MetaConcept metaConcept) {
		super(identifier);
		vars.put(VAR_METACONCEPT, metaConcept.getIdentifier());
		this.metaConcept = metaConcept;
		createInstAttributes();
	}

	public Object getVariable(String name) {
		return vars.get(name);
	}

	public void setVariable(String name, MetaConcept value) {
		vars.put(name, value);
	}

	public MetaConcept getMetaConcept() {
		// return (MetaConcept)getVariable(VAR_METACONCEPT);
		return metaConcept;
	}

	private void createInstAttributes() {
		Iterator<String> modelingAttributes = getMetaConcept().getModelingAttributes()
				.iterator();
		while (modelingAttributes.hasNext()) {
			String name = modelingAttributes.next();
			if (name.equals("identifier"))
				addInstAttribute(name, getMetaConcept().getModelingAttribute(name),
						getIdentifier());
			else
				addInstAttribute(name, getMetaConcept().getModelingAttribute(name),
						null);
		}

		Iterator<String> semanticAttributes = getMetaConcept()
				.getSemanticAttributes().iterator();
		while (semanticAttributes.hasNext()) {
			String name = semanticAttributes.next();
			if (name.equals("identifier"))
				addInstAttribute(name,
						getMetaConcept().getSemanticAttribute(name),
						getIdentifier());
			else
				addInstAttribute(name,
						getMetaConcept().getSemanticAttribute(name), null);
		}

	}

	/*
	 * public MetaConcept getMetaConcept() { return metaConcept; }
	 */

	public InstAttribute[] getEditableVariables() { //TODO move to superclass
		Set<String> attributesNames = getMetaConcept()
				.getDisPropEditableAttributes();
		List<String> listEditableAttributes = new ArrayList<String>();
		listEditableAttributes.addAll(attributesNames);
		Collections.sort(listEditableAttributes);
		
		List<String> listEditableAttribNames = new ArrayList<String>();
		for(String attribute : listEditableAttributes)
		{
			int endName = attribute.indexOf("#",3);
			if(endName != -1)
				listEditableAttribNames.add(attribute.substring(3,endName));
			else
				listEditableAttribNames.add(attribute.substring(3));
		}
		
		InstAttribute[] editableInstAttributes = new InstAttribute[attributesNames
				.size()];
		int i = 0;
		for (String attributeName : listEditableAttribNames) {
			editableInstAttributes[i++] = getInstAttribute(attributeName);
		}
		return editableInstAttributes;
	}

	public String getMetaConceptIdentifier() {
		// return metaConcept.getIdentified();
		return (String) vars.get(VAR_METACONCEPT);
	}

	public String toString() { //TODO move to superclass
		String out = "";
		// List<String> visibleAttributesNames = metaConcept
		// .getPanelVisibleAttributes();
		if (getMetaConcept() != null)
		{
		Set<String> visibleAttributesNames = getMetaConcept()
				.getDisPanelVisibleAttributes();
		List<String> listVisibleAttributes = new ArrayList<String>();
		listVisibleAttributes.addAll(visibleAttributesNames);
		Collections.sort(listVisibleAttributes);

		// List<String> spacersAttributes = metaConcept
		// .getPanelSpacersAttributes();
		Set<String> spacersAttributes = getMetaConcept()
				.getDisPanelSpacersAttributes();
		for (String visibleAttribute : listVisibleAttributes) {
			boolean validCondition=true;
			
			int nameEnd = visibleAttribute.indexOf("#",3);
			int varEnd = visibleAttribute.indexOf("#",nameEnd+1);
			int condEnd = visibleAttribute.indexOf("#",varEnd+1);
			
			String name = visibleAttribute.substring(3);
			if (nameEnd != -1)
			{				
				name  = visibleAttribute.substring(3, nameEnd);
				String variable = null;
				String condition = null;
				String value = null;				
				variable = visibleAttribute.substring(nameEnd+1, varEnd);
				condition = visibleAttribute.substring(varEnd+1, condEnd);
				value = visibleAttribute.substring(condEnd+1);
				Object varValue = getInstAttributes().get(variable);
				if(!varValue.equals(value))
					validCondition=false;
			}
			boolean nvar= false;
			if (name != null && validCondition) {				
				Iterator<String> spacers = spacersAttributes.iterator();				
				while (spacers.hasNext()) {					
					String spacer = spacers.next();
					if (spacer.indexOf("#" + name + "#") != -1) {
						nvar= true;
						int sp1 = spacer.indexOf("#");
						int var = spacer.indexOf(name);
						int sp2 = spacer.indexOf("#", sp1+1);
						
						out += spacer.substring(0,sp1);
						out += getInstAttributes().get(name).toString().trim();
						while (sp2 != spacer.length()) {
							int sp3 = spacer.indexOf("#", sp2+1);
							if (sp3==-1)
								{

								out += spacer.substring(sp2+1);
								break;
								}
							out += spacer.substring(sp2+1, sp3);
							
							sp2 = sp3;
						}
					}

				}
				if (!nvar)
					out += getInstAttributes().get(name);
			}
		}
		if (out.equals(""))
			out = "No display attributes defined";
	}
	return out;
}

	public void setMetaConcept(MetaVertex metaConcept) {
		this.metaConcept = (MetaConcept) metaConcept;
		setVariable(VAR_METACONCEPT, metaConcept.getIdentifier());
		// createInstAttributes();
	}

	public void setMetaConceptIdentifier(String metaConceptIdentifier) {
		setVariable(VAR_METACONCEPT, metaConceptIdentifier);
		// createInstAttributes();
	}

	public void clearMetaConcept() {
		metaConcept = null;
	}

}
