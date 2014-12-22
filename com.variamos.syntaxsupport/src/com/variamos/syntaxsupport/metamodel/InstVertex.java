package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cfm.productline.Prototype;
import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaVertex;

/**
 * A class to represented the common aspects of modeling vertex of concepts from
 * meta model and semantic model on VariaMos. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24 *
 * @see com.variamos.syntaxsupport.metametamodel.MetaConcept
 */
public abstract class InstVertex extends InstElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2214656166959965220L;
	/*
	 * private String identifier; private Map<String, InstAttribute>
	 * instAttributes; private Map<String, InstRelation> instRelations;
	 */

	/**
	 * The edges incoming to the vertex
	 */
	private List<InstEdge> sourceRelations;
	/**
	 * THe edge outgoing from the vertex
	 */
	private List<InstEdge> targetRelations;

	public InstVertex() {
		this(null, new HashMap<String, InstAttribute>(),
				new HashMap<String, InstEdge>());
	}

	public InstVertex(String identifier) {
		this(identifier, new HashMap<String, InstAttribute>(),
				new HashMap<String, InstEdge>());

	}

	public InstVertex(String identifier,
			Map<String, InstAttribute> instAttributes,
			Map<String, InstEdge> instRelations) {
		super(identifier);
		vars.put(VAR_INSTATTRIBUTES, instAttributes);

		sourceRelations = new ArrayList<InstEdge>();

		targetRelations = new ArrayList<InstEdge>();

	}

	public Object getVariable(String name) {
		return vars.get(name);
	}

	public void setVariable(String name, Object value) {
		vars.put(name, value);
	}

	public String getIdentifier() {
		return (String) getVariable(VAR_IDENTIFIER);
		// return identifier;
	}

	public void setIdentifier(String identifier) {
		setVariable(VAR_IDENTIFIER, identifier);
		setInstAttribute(VAR_IDENTIFIER, identifier);
		// this.identifier = identifier;
	}

	@SuppressWarnings("unchecked")
	public Map<String, InstAttribute> getInstAttributes() {
		return (Map<String, InstAttribute>) getVariable(VAR_INSTATTRIBUTES);
		// return instAttributes;
	}

	public Collection<InstAttribute> getInstAttributesCollection() {
		return ((Map<String, InstAttribute>) getVariable(VAR_INSTATTRIBUTES))
				.values();
		// return instAttributes;
	}

	public void setInstAttributes(Map<String, InstAttribute> instAttributes) {
		setVariable(VAR_INSTATTRIBUTES, instAttributes);
	}

	public List<InstEdge> getTargetRelations() {
		return targetRelations;
	}

	public void addTargetRelation(InstEdge target) {
		this.targetRelations.add(target);
	}

	public List<InstEdge> getSourceRelations() {
		return sourceRelations;
	}

	public void addSourceRelation(InstEdge source) {
		this.sourceRelations.add(source);
	}

	@SuppressWarnings("unchecked")
	public InstAttribute getInstAttribute(String name) {
		return ((Map<String, InstAttribute>) getVariable(VAR_INSTATTRIBUTES))
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
					type = getInstAttributes().get(name)
							.getModelingAttributeType();
					defvalue = attribute.substring(valueEnd + 1);
				} else
					value = attribute.substring(condEnd + 1);
				InstAttribute varValue = getInstAttributes().get(variable);
				if (varValue == null) {
					if (valueEnd != -1)
						getInstAttributes().get(name).setValue(
								createValue(type, defvalue));
					continue;
				} else if (varValue.getValue().toString().trim().equals(value)) {
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

	public String toString() {
		/*
		 * if (getInstAttributes().get("name") == null) return
		 * "Error: no name attribure!"; else return
		 * getInstAttributes().get("name").toString(); }
		 */
		return "";
	}

	public Map<String, Object> getVars() {
		return vars;
	}

	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}

	public void clearInstAttributesObjects() {
		for (InstAttribute attribute : this.getInstAttributes().values()) {
			attribute.setValueObject(null);
			attribute.clearModelingAttribute();
		}
	}

	public abstract MetaVertex getMetaVertex();

	public abstract void clearMetaVertex();

	public abstract String getMetaVertexIdentifier();

	public abstract void setMetaVertex(MetaVertex mc);

	public String getInstAttributeFullIdentifier(String insAttributeLocalId) {
		return this.getIdentifier() + "_"
				+ this.getInstAttribute(insAttributeLocalId).getIdentifier();
	}

}
