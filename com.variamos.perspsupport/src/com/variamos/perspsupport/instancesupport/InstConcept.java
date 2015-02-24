package com.variamos.perspsupport.instancesupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.perspsupport.semanticinterface.IntSemanticElement;
import com.variamos.perspsupport.syntaxsupport.MetaElement;

/**
 * A class to represented modeling instances of elements as vertex from meta
 * model and semantic model on VariaMos. Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24 *
 * @see com.variamos.perspsupport.syntaxsupport.MetaElement
 */
public class InstConcept extends InstVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = -351673247632968251L;
	/**
	 * 
	 */

	public static final String VAR_METACONCEPT_IDEN = "MetaElementIde";

	public InstConcept() {
		super("");
	}

	public InstConcept(MetaElement metaElement) {
		super("");
		setTransSupportMetaElement(metaElement);
		createInstAttributes();
	}

	public InstConcept(String identifier, MetaElement supportMetaElement,
			MetaElement editableMetaElement) {
		super(identifier);
		if (supportMetaElement != null)
			setTransSupportMetaElement(supportMetaElement);
		setEditableMetaElement(editableMetaElement);
		createInstAttributes();
		copyValuesToInstAttributes();
	}

	public InstConcept(String identifier, MetaElement supportMetaElement,
			IntSemanticElement editableSemanticElement) {
		super(identifier);
		if (supportMetaElement != null)
			setTransSupportMetaElement(supportMetaElement);
		setEditableSemanticElement(editableSemanticElement);
		createInstAttributes();
	}

	public InstConcept(String identifier, MetaElement supportMetaElement,
			Map<String, InstAttribute> attributes,
			Map<String, InstPairwiseRelation> relations) {
		super(identifier, attributes, relations);
		setTransSupportMetaElement(supportMetaElement);
		createInstAttributes();
	}

	public InstConcept(String identifier, MetaElement supportMetaElement) {
		super(identifier);
		setTransSupportMetaElement(supportMetaElement);
		createInstAttributes();
	}

	protected void createInstAttributes() {
		if (getTransSupportMetaElement() != null) {
			Iterator<String> modelingAttributes = getTransSupportMetaElement()
					.getModelingAttributesNames().iterator();
			while (modelingAttributes.hasNext()) {
				String name = modelingAttributes.next();
				if (name.equals(MetaElement.VAR_IDENTIFIER))
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name), getIdentifier());
				else if (name.equals(MetaElement.VAR_DESCRIPTION))
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name),
							getTransSupportMetaElement().getDescription());
				else
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name), null);
			}

			Iterator<String> semanticAttributes = getTransSupportMetaElement()
					.getAllAttributesNames().iterator();
			while (semanticAttributes.hasNext()) {
				String name = semanticAttributes.next();
				if (name.equals(MetaElement.VAR_IDENTIFIER))
					addInstAttribute(name, getTransSupportMetaElement()
							.getSemanticAttribute(name), getIdentifier());
				else if (name.equals(MetaElement.VAR_DESCRIPTION))
					addInstAttribute(name, getTransSupportMetaElement()
							.getSemanticAttribute(name),
							getTransSupportMetaElement().getDescription());
				else
					addInstAttribute(name, getTransSupportMetaElement()
							.getSemanticAttribute(name), null);
			}
		}
	}

	/*
	 * public MetaConcept getMetaConcept() { return metaConcept; }
	 */

	public List<InstAttribute> getEditableVariables() { // TODO move to
														// superclass
		Set<String> attributesNames = getTransSupportMetaElement()
				.getPropEditableAttributes();
		return getFilteredInstAttributes(attributesNames, null);
	}

	public String getSupportMetaElementIdentifier() {
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		return (String) dynamicAttributesMap.get(VAR_METACONCEPT_IDEN);
	}

	/**
	 * TODO Delete pending
	 * 
	 * @return
	 */
	@Deprecated
	public String toStringOld() {
		String out = "";
		if (getTransSupportMetaElement() != null) {
			Set<String> visibleAttributesNames = getTransSupportMetaElement()
					.getPanelVisibleAttributes();
			List<String> listVisibleAttributes = new ArrayList<String>();
			listVisibleAttributes.addAll(visibleAttributesNames);
			Collections.sort(listVisibleAttributes);
			Set<String> spacersAttributes = getTransSupportMetaElement()
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
		MetaElement supportMetaElement = this.getTransSupportMetaElement();
		if (supportMetaElement != null)
			setDynamicVariable(MetaElement.VAR_DESCRIPTION,
					supportMetaElement.getDescription());
	}

	public void setTransSupportMetaElement(MetaElement metaElement) {
		super.setTransSupportMetaElement(metaElement);

		// TODO delete
		setDynamicVariable(VAR_METACONCEPT_IDEN, metaElement.getIdentifier());
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				metaElement.getDescription());
		// createInstAttributes();
	}

	public void setMetaElementIdentifier(String metaElementIdentifier) {
		MetaElement supportMetaElement = this.getTransSupportMetaElement();
		setDynamicVariable(VAR_METACONCEPT_IDEN, metaElementIdentifier);
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				supportMetaElement.getDescription());
		// createInstAttributes();
	}

	public void clearEditableMetaVertex() {
		super.clearEditableMetaVertex();
		// supportMetaElement = null;

	}

}
