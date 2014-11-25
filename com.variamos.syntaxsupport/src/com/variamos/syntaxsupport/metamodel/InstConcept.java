package com.variamos.syntaxsupport.metamodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import com.cfm.productline.Variable;
import com.cfm.productline.type.StringType;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;

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
	private static final long serialVersionUID = 6231317731982400120L;
	public static final String 	VAR_METACONCEPT = "MetaConceptIdentifier";
//	protected Map<String, MetaConcept> vars = new HashMap<>();
	private MetaConcept metaConcept;

	public InstConcept(String identifier, MetaConcept metaConcept,
			Map<String, InstAttribute> attributes,
			Map<String, InstRelation> relations) {
		super(identifier, attributes, relations);
		vars.put(VAR_METACONCEPT, metaConcept.getIdentified());
		this.metaConcept = metaConcept;
		createInstAttributes();
	}

	public InstConcept(String identifier, MetaConcept metaConcept) {
		super(identifier);
		vars.put(VAR_METACONCEPT, metaConcept.getIdentified());
		this.metaConcept = metaConcept;
		createInstAttributes();
	}
	
	public Object getVariable(String name){
		return vars.get(name);
	}
	
	public void setVariable(String name, MetaConcept value){
		vars.put(name, value);
	}	

	
	public MetaConcept getMetaConcept() {
		//return (MetaConcept)getVariable(VAR_METACONCEPT);
		return metaConcept;
	}

	public InstConcept() {
		super("");
	}

	public InstConcept(MetaConcept metaConcept) {
		super("");
		vars.put(VAR_METACONCEPT, metaConcept.getIdentified());
		this.metaConcept = metaConcept;
		createInstAttributes();
	}

	private void createInstAttributes() {
		Iterator<String> metaAttributes = getMetaConcept().getMetaAttributes()
				.iterator();
	//	Iterator<String> metaAttributes = metaConcept.getMetaAttributes()
	//			.iterator();
		while (metaAttributes.hasNext()) {
			String name = metaAttributes.next();
			if (name.equals("identifier"))
				addInstAttribute(name, getMetaConcept().getMetaAttribute(name),
						getIdentifier());
		//		addInstAttribute(name, metaConcept.getMetaAttribute(name),
		//				getIdentifier());
			else
				addInstAttribute(name, getMetaConcept().getMetaAttribute(name), null);
		//	addInstAttribute(name, metaConcept.getMetaAttribute(name), null);
		}
	}
/*
	public MetaConcept getMetaConcept() {
		return metaConcept;
	}
	*/

	public InstAttribute[] getEditableVariables() {
		List<String> attributesNames = getMetaConcept().getPropEditableAttributes();
		
		//	List<String> attributesNames = metaConcept.getPropEditableAttributes();
		InstAttribute[] editableInstAttributes = new InstAttribute[attributesNames
				.size()];
		for (int i = 0; i < attributesNames.size(); i++) {
			editableInstAttributes[i] = getInstAttribute(attributesNames.get(i));
		}
		return editableInstAttributes;
	}

	public String getMetaConceptIdentifier() {
	//	return metaConcept.getIdentified();
		return (String)vars.get(VAR_METACONCEPT);
	}

	public String toString() {
		String out = "";
	//	List<String> visibleAttributesNames = metaConcept
	//			.getPanelVisibleAttributes();
				List<String> visibleAttributesNames = getMetaConcept()
						.getPanelVisibleAttributes();
	//			List<String> spacersAttributes = metaConcept
	//			.getPanelSpacersAttributes();
				List<String> spacersAttributes = getMetaConcept()
						.getPanelSpacersAttributes();
				for (int i = 0; i < visibleAttributesNames.size(); i++) {
			if (visibleAttributesNames.get(i) != null) {
				out += getInstAttributes().get(visibleAttributesNames.get(i));
				if (spacersAttributes.size() > i
						&& spacersAttributes.get(i) != null) {
					out += spacersAttributes.get(i);
				}

			}
		}
		if (out.equals (""))
			out = "No display attributes defined";

		return out;
	}

	public void setMetaConcept(MetaConcept metaConcept) {
		this.metaConcept = metaConcept;
		setVariable(VAR_METACONCEPT, metaConcept.getIdentified());
		//createInstAttributes();
	}
	public void setMetaConceptIdentifier(String metaConceptIdentifier) {
		setVariable(VAR_METACONCEPT, metaConceptIdentifier);
		//createInstAttributes();
	}

	public void clearMetaConcept() {
		// TODO Auto-generated method stub
		metaConcept= null;
	}
}
