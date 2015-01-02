package com.variamos.syntaxsupport.metamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaEnumeration;
import com.variamos.syntaxsupport.metamodelsupport.MetaVertex;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;

/**
 * A class to represented modeling instances of concepts from meta model and
 * semantic model on VariaMos. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24 *
 * @see com.variamos.syntaxsupport.metamodelsupport.MetaConcept
 */
public class InstConcept extends InstVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = -351673247632968251L;
	/**
	 * 
	 */

	public static final String VAR_METACONCEPT_IDEN = "MetaConceptIde";
	// protected Map<String, MetaConcept> vars = new HashMap<>();
	private MetaConcept metaConcept;

	public InstConcept() {
		super("");
	}

	public InstConcept(MetaConcept metaConcept) {
		super("");
		setMetaVertex(metaConcept);
		createInstAttributes();
	}

	public InstConcept(String identifier, MetaConcept metaConcept,
			MetaElement editableMetaElement) {
		super(identifier);
		if (metaConcept != null)
			setMetaVertex(metaConcept);
		setEditableMetaElement(editableMetaElement);
		createInstAttributes();
		copyValuesToInstAttributes();
	}

	public InstConcept(String identifier, MetaConcept metaConcept,
			IntSemanticElement semanticElement) {
		super(identifier);
		setMetaVertex(metaConcept);
		setEditableSemanticElement(semanticElement);
		createInstAttributes();
	}

	public InstConcept(String identifier, MetaConcept metaConcept,
			Map<String, InstAttribute> attributes,
			Map<String, InstPairwiseRelation> relations) {
		super(identifier, attributes, relations);
		setMetaVertex(metaConcept);
		createInstAttributes();
	}

	public InstConcept(String identifier, MetaConcept metaConcept) {
		super(identifier);
		setMetaVertex(metaConcept);
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

	protected void createInstAttributes() {
		if (getMetaConcept() != null) {
			Iterator<String> modelingAttributes = getMetaConcept()
					.getModelingAttributesNames().iterator();
			while (modelingAttributes.hasNext()) {
				String name = modelingAttributes.next();
				if (name.equals(MetaElement.VAR_IDENTIFIER))
					addInstAttribute(name, getMetaConcept()
							.getModelingAttribute(name), getIdentifier());
				else if (name.equals(MetaElement.VAR_DESCRIPTION))
					addInstAttribute(name, getMetaConcept()
							.getModelingAttribute(name), getMetaConcept()
							.getDescription());
				else
					addInstAttribute(name, getMetaConcept()
							.getModelingAttribute(name), null);
			}

			Iterator<String> semanticAttributes = getMetaConcept()
					.getSemanticAttributes().iterator();
			while (semanticAttributes.hasNext()) {
				String name = semanticAttributes.next();
				if (name.equals(MetaElement.VAR_IDENTIFIER))
					addInstAttribute(name, getMetaConcept()
							.getSemanticAttribute(name), getIdentifier());
				else if (name.equals(MetaElement.VAR_DESCRIPTION))
					addInstAttribute(name, getMetaConcept()
							.getSemanticAttribute(name), getMetaConcept()
							.getDescription());
				else
					addInstAttribute(name, getMetaConcept()
							.getSemanticAttribute(name), null);
			}
		}
	}

	/*
	 * public MetaConcept getMetaConcept() { return metaConcept; }
	 */

	public List<InstAttribute> getEditableVariables() { // TODO move to
														// superclass
		Set<String> attributesNames = getMetaConcept()
				.getPropEditableAttributes();
		return getFilteredInstAttributes(attributesNames, null);
	}

	public List<InstAttribute> getVisibleVariables() { // TODO move to
														// superclass
		Set<String> attributesNames = getMetaConcept()
				.getPropVisibleAttributes();
		return getFilteredInstAttributes(attributesNames, null);
	}

	public String getMetaVertexIdentifier() {
		// return metaConcept.getIdentified();
		return (String) vars.get(VAR_METACONCEPT_IDEN);
	}

	/**
	 * TODO Delete pending
	 * @return
	 */
	@Deprecated
	public String toStringOld() { 
		String out = "";
		if (getMetaConcept() != null) {
			Set<String> visibleAttributesNames = getMetaConcept()
					.getPanelVisibleAttributes();
			List<String> listVisibleAttributes = new ArrayList<String>();
			listVisibleAttributes.addAll(visibleAttributesNames);
			Collections.sort(listVisibleAttributes);
			Set<String> spacersAttributes = getMetaConcept()
					.getPanelSpacersAttributes();
			for (String visibleAttribute : listVisibleAttributes) {
				boolean validCondition = true;

				int nameEnd = visibleAttribute.indexOf("#", 3);
				int varEnd = visibleAttribute.indexOf("#", nameEnd + 1);
				int condEnd = visibleAttribute.indexOf("#", varEnd + 1);

				String name = visibleAttribute.substring(3);
				if (nameEnd != -1) {
					name = visibleAttribute.substring(3, nameEnd);
					String variable = null;
					String condition = null;
					String value = null;
					variable = visibleAttribute.substring(nameEnd + 1, varEnd);
					condition = visibleAttribute.substring(varEnd + 1, condEnd);
					value = visibleAttribute.substring(condEnd + 1);
					InstAttribute varValue = getInstAttributes().get(variable);
					if (varValue == null)
						validCondition = false;
					else if (varValue.getValue().toString().trim()
							.equals(value)) {
						if (condition.equals("!="))
							validCondition = false;
					} else {
						if (condition.equals("=="))
							validCondition = false;
					}
				}
				boolean nvar = false;
				if (name != null && validCondition) {
					Iterator<String> spacers = spacersAttributes.iterator();
					while (spacers.hasNext()) {
						String spacer = spacers.next();
						if (spacer.indexOf("#" + name + "#") != -1) {
							nvar = true;
							int sp1 = spacer.indexOf("#");
							int sp2 = spacer.indexOf("#", sp1 + 1);

							out += spacer.substring(0, sp1);
							if (name.equals("name")
									&& getInstAttributes().get(name).toString()
											.trim().equals(""))
								out += "<<NoName>>";
							{
								InstAttribute instAttribute = getInstAttributes()
										.get(name);
								if (instAttribute.getEnumType() != null
										&& instAttribute.getEnumType().equals(
												InstEnumeration.class
														.getCanonicalName()))
									out += (String) instAttribute.getValue(); // TODO
																				// retrieve
																				// the
																				// values
								else
									out += instAttribute.toString().trim();
							}
							while (sp2 != spacer.length()) {
								int sp3 = spacer.indexOf("#", sp2 + 1);
								if (sp3 == -1) {
									out += spacer.substring(sp2 + 1);
									break;
								}
								out += spacer.substring(sp2 + 1, sp3);

								sp2 = sp3;
							}
						}

					}
					if (!nvar)
						if (name.equals("name")
								&& getInstAttributes().get(name).toString()
										.trim().equals(""))
							out += "<<NoName>>";
						else {
							InstAttribute instAttribute = getInstAttributes()
									.get(name);
							if (instAttribute.getEnumType() != null
									&& instAttribute.getEnumType().equals(
											InstEnumeration.class
													.getCanonicalName()))
								out += (String) instAttribute.getValue(); // TODO
																			// retrieve
																			// the
																			// list
																			// of
																			// values
							else
								out += instAttribute.toString().trim();
						}
				}
			}
			if (out.equals(""))
				out = "No display attributes defined";
		}
		return out;
	}

	public void setIdentifier(String identifier) {
		super.setIdentifier(identifier);
		setVariable(MetaElement.VAR_DESCRIPTION, metaConcept.getDescription());
	}

	public void setMetaVertex(MetaVertex metaConcept) {
		this.metaConcept = (MetaConcept) metaConcept;
		setVariable(VAR_METACONCEPT_IDEN, metaConcept.getIdentifier());
		setVariable(MetaElement.VAR_DESCRIPTION, metaConcept.getDescription());
		// createInstAttributes();
	}

	public void setMetaConceptIdentifier(String metaConceptIdentifier) {
		setVariable(VAR_METACONCEPT_IDEN, metaConceptIdentifier);
		setVariable(MetaElement.VAR_DESCRIPTION, metaConcept.getDescription());
		// createInstAttributes();
	}

	public void clearMetaVertex() {
		super.clearMetaVertex();
		metaConcept = null;

	}

	@Override
	public MetaVertex getMetaVertex() {
		return metaConcept;
	}

	@Override
	public MetaElement getSupportMetaElement() {
		return metaConcept;
	}

}
