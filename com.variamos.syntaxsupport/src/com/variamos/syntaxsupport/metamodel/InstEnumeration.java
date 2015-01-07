package com.variamos.syntaxsupport.metamodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaEnumeration;
import com.variamos.syntaxsupport.metamodelsupport.MetaVertex;

/**
 * A class to represented modeling instances of enumerations from meta model on
 * VariaMos. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-10 *
 * @see com.variamos.syntaxsupport.metamodelsupport.MetaEnumeration
 */
public class InstEnumeration extends InstVertex {

	/**
	 * 
	 */
	private static final long serialVersionUID = 188655707058755882L;
	public static final String VAR_METAENUM_IDEN = "MetaEnumIde";
	private MetaVertex metaEnumeration;

	public InstEnumeration() {
		super("");
	}

	public InstEnumeration(MetaEnumeration metaEnumeration) {
		super("");
		setSupportMetaVertex(metaEnumeration);
		createInstAttributes();
	}

	public InstEnumeration(MetaVertex metaEnumeration,
			MetaElement editableMetaElement) {
		super("");
		setEditableMetaElement(editableMetaElement);
		setSupportMetaVertex(metaEnumeration);
		createInstAttributes();
	}

	public InstEnumeration(String identifier, MetaVertex metaEnumeration,
			Map<String, InstAttribute> attributes,
			Map<String, InstPairwiseRelation> relations) {
		super(identifier, attributes, relations);
		setSupportMetaVertex(metaEnumeration);
		createInstAttributes();
	}

	public InstEnumeration(String identifier, MetaVertex metaEnumeration) {
		super(identifier);
		setSupportMetaVertex(metaEnumeration);
		createInstAttributes();
	}

	public InstEnumeration(String identifier, MetaVertex metaEnumeration,
			MetaElement editableMetaElement) {
		super(identifier);
		setEditableMetaElement(editableMetaElement);
		setSupportMetaVertex(metaEnumeration);
		createInstAttributes();
	}

	public Object getVariable(String name) {
		return vars.get(name);	
	}

	public void setVariable(String name, MetaEnumeration value) {
		vars.put(name, value);
	}

	public MetaVertex getMetaEnumeration() {
		return metaEnumeration;
	}

	protected void createInstAttributes() {
		Iterator<String> modelingAttributes = getMetaEnumeration()
				.getModelingAttributesNames().iterator();
		while (modelingAttributes.hasNext()) {
			String name = modelingAttributes.next();
			if (name.equals(MetaElement.VAR_IDENTIFIER))
				addInstAttribute(name, getMetaEnumeration()
						.getModelingAttribute(name), getIdentifier());
			else if (name.equals(MetaElement.VAR_DESCRIPTION))
				addInstAttribute(name, getMetaEnumeration()
						.getModelingAttribute(name), getMetaEnumeration()
						.getDescription());
			else
				addInstAttribute(name, getMetaEnumeration()
						.getModelingAttribute(name), null);
		}
	}

	public List<InstAttribute> getEditableVariables() { // TODO move to
														// superclass
		Set<String> attributesNames = getMetaEnumeration()
				.getPropEditableAttributes();
		return getFilteredInstAttributes(attributesNames, null);
	}

	public List<InstAttribute> getVisibleVariables() { // TODO move to
														// superclass
		Set<String> attributesNames = getMetaEnumeration()
				.getPropVisibleAttributes();
		return getFilteredInstAttributes(attributesNames, null);
	}

	public String getSupportMetaVertexIdentifier() {
		// return metaConcept.getIdentified();
		return (String) vars.get(VAR_METAENUM_IDEN);
	}

	public String toStringOld() { // TODO move partially to superclass
		String out = "";
		if (getMetaEnumeration() != null) {
			Set<String> visibleAttributesNames = getMetaEnumeration()
					.getPanelVisibleAttributes();
			List<String> listVisibleAttributes = new ArrayList<String>();
			listVisibleAttributes.addAll(visibleAttributesNames);
			Collections.sort(listVisibleAttributes);
			Set<String> spacersAttributes = getMetaEnumeration()
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
							else {
								InstAttribute instAttribute = getInstAttributes()
										.get(name);
								if (instAttribute.getAttributeType().equals(
										"Set"))
									for (InstAttribute e : (Collection<InstAttribute>) instAttribute
											.getValue())
										out += e.toString().trim() + "\n";
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
						else
							out += getInstAttributes().get(name);
				}
			}
			if (out.equals(""))
				out = "No display attributes defined";
		}
		return out;
	}

	public void setIdentifier(String identifier) {
		super.setIdentifier(identifier);
		setVariable(MetaElement.VAR_DESCRIPTION,
				metaEnumeration.getDescription());
	}

	public void setSupportMetaVertex(MetaVertex metaEnumeration) {
		this.metaEnumeration = metaEnumeration;
		setVariable(VAR_METAENUM_IDEN, metaEnumeration.getIdentifier());
		setVariable(MetaElement.VAR_DESCRIPTION,
				metaEnumeration.getDescription());
		setVariable(MetaElement.VAR_DESCRIPTION, metaEnumeration.getDescription());

		// createInstAttributes();
	}

	public void setMetaEnumerationIdentifier(String metaEnumerationIdentifier) {
		setVariable(VAR_METAENUM_IDEN, metaEnumerationIdentifier);
		setVariable(MetaElement.VAR_DESCRIPTION, metaEnumeration.getDescription());
		// createInstAttributes();
	}

	public void clearEditableMetaVertex() {
		super.clearEditableMetaVertex();
		metaEnumeration = null;
	}

	@Override
	public MetaVertex getSupportMetaVertex() {
		return metaEnumeration;
	}

	@Override
	public MetaElement getSupportMetaElement() {
		return metaEnumeration;
	}

}
