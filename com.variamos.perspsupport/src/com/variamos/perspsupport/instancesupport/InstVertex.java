package com.variamos.perspsupport.instancesupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaVertex;

/**
 * A class to represented the common aspects of modeling vertex of concepts from
 * meta model and semantic model on VariaMos. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24 *
 * @see com.variamos.perspsupport.syntaxsupport.MetaConcept
 */
public abstract class InstVertex extends InstElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2214656166959965220L;

	private MetaVertex supportMetaElement;

	/*
	 * private String identifier; private Map<String, InstAttribute>
	 * instAttributes; private Map<String, InstRelation> instRelations;
	 */

	public InstVertex() {
		this(null, new HashMap<String, InstAttribute>(),
				new HashMap<String, InstPairwiseRelation>());
	}

	public InstVertex(String identifier) {
		this(identifier, new HashMap<String, InstAttribute>(),
				new HashMap<String, InstPairwiseRelation>());

	}

	public InstVertex(String identifier,
			Map<String, InstAttribute> instAttributes,
			Map<String, InstPairwiseRelation> instRelations) {
		super(identifier);
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_INSTATTRIBUTES, instAttributes);
	}
	/**
	 * Name changed from standard to avoid graph serialization of the object
	 * @return
	 */
	public MetaVertex getTransSupportMetaElement() {
		return supportMetaElement;
	}
	/**
	 * Name changed from standard to avoid graph serialization of the object
	 * @return
	 */

	@Override	
	public void setTransSupportMetaElement(MetaElement supportMetaElement) {
		this.setSupportMetaElementIden(supportMetaElement.getIdentifier());
		this.supportMetaElement = (MetaVertex)supportMetaElement;
	}

	public void setIdentifier(String identifier) {
		setDynamicVariable(VAR_IDENTIFIER, identifier);
		setInstAttribute(VAR_IDENTIFIER, identifier);
		// this.identifier = identifier;
	}

	@SuppressWarnings("unchecked")
	public Map<String, InstAttribute> getInstAttributes() {
		return (Map<String, InstAttribute>) getDynamicVariable(VAR_INSTATTRIBUTES);
		// return instAttributes;
	}

	@SuppressWarnings("unchecked")
	public Collection<InstAttribute> getInstAttributesCollection() {
		return ((Map<String, InstAttribute>) getDynamicVariable(VAR_INSTATTRIBUTES))
				.values();
		// return instAttributes;
	}

	public void setInstAttributes(Map<String, InstAttribute> instAttributes) {
		setDynamicVariable(VAR_INSTATTRIBUTES, instAttributes);
	}

	@SuppressWarnings("unchecked")
	public InstAttribute getInstAttribute(String name) {
		return ((Map<String, InstAttribute>) getDynamicVariable(VAR_INSTATTRIBUTES))
				.get(name);
		// return instAttributes.get(name);
	}

	public void setInstAttribute(String name, Object value) {
		if (getInstAttribute(name) != null)
			getInstAttribute(name).setValue(value);
	}

	public void addInstAttribute(String name,
			AbstractAttribute modelingAttribute, Object value) {
		if (getInstAttribute(name) == null) {
			InstAttribute instAttribute = new InstAttribute(name,
					modelingAttribute,
					value == null ? modelingAttribute.getDefaultValue() : value);
			getInstAttributes().put(name, instAttribute);
			// instAttributes.put(name, instAttribute);
		}
	}

	public List<InstAttribute> getVisibleVariables() { // TODO move to
		// superclass
		if (getTransSupportMetaElement() == null)
			return null;
		Set<String> attributesNames = getTransSupportMetaElement()
				.getPropVisibleAttributes();
		return getFilteredInstAttributes(attributesNames, null);
	}

	public List<InstAttribute> getFilteredInstAttributes(
			Set<String> attributesNames, List<InstAttribute> instAttributes) {
		List<String> listEditableAttributes = new ArrayList<String>();
		listEditableAttributes.addAll(attributesNames);
		Collections.sort(listEditableAttributes);

		List<String> listEditableAttribNames = new ArrayList<String>();
		for (String attribute : listEditableAttributes) {
			int nameEnd = attribute.indexOf("#", 3);
			int varEnd = attribute.indexOf("#", nameEnd + 1);
			int condEnd = attribute.indexOf("#", varEnd + 1);
			int valueEnd = attribute.indexOf("#", condEnd + 1);
			if (nameEnd != -1) {
				String name = null;
				String type = null;
				String variable = null;
				String condition = null;
				String value = null;
				String defvalue = null;
				name = attribute.substring(3, nameEnd);
				variable = attribute.substring(nameEnd + 1, varEnd);
				condition = attribute.substring(varEnd + 1, condEnd);
				if (valueEnd != -1) {
					value = attribute.substring(condEnd + 1, valueEnd);
					type = getInstAttributes().get(name).getAttributeType();
					defvalue = attribute.substring(valueEnd + 1);
				} else
					value = attribute.substring(condEnd + 1);
				InstAttribute varValue = getInstAttributes().get(variable);
				if (varValue == null) {
					if (valueEnd != -1)
						getInstAttributes().get(name).setValue(
								createValue(type, defvalue));
					continue;
				} else if (varValue.getValue().toString().trim().equals(value.toString())) {
					if (condition.equals("!=")) {
						if (valueEnd != -1)
							getInstAttributes().get(name).setValue(
									createValue(type, defvalue));
						continue;
					}
				} else {
					if (condition.equals("==")) {
						if (valueEnd != -1)
							getInstAttributes().get(name).setValue(
									createValue(type, defvalue));
						continue;
					}
				}
				listEditableAttribNames.add(attribute.substring(3, nameEnd));

			} else
				listEditableAttribNames.add(attribute.substring(3));
		}

		List<InstAttribute> editableInstAttributes = new ArrayList<InstAttribute>();
		for (String attributeName : listEditableAttribNames) {
			editableInstAttributes.add(getInstAttribute(attributeName));
		}
		return editableInstAttributes;
	}

	private Object createValue(String type, String value) {
		if (type.equals("Boolean"))
			return new Boolean(value);
		if (type.equals("Integer"))
			return new Integer(value);
		return value;

	}

	public abstract String getSupportMetaElementIdentifier();


	public String getInstAttributeFullIdentifier(String insAttributeLocalId) {
		//System.out.println("InstV:"+this.getIdentifier() + insAttributeLocalId);
		return this.getIdentifier() + "_"
				+ this.getInstAttribute(insAttributeLocalId).getIdentifier();
	}

}
