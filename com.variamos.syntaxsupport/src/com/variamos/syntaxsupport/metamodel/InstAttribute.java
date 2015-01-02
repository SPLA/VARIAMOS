package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;
import com.variamos.syntaxsupport.metamodelsupport.EditableElementAttribute;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaOverTwoRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaPairwiseRelation;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticPairwiseRelation;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticOverTwoRelation;

/**
 * A class to represented modeling instances of attributes from meta model and
 * semantic model AbstractAttributes on VariaMos. Part of PhD work at University
 * of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.syntaxsupport.metamodelsupport.AbtractAttribute
 */
public class InstAttribute implements Serializable, EditableElementAttribute {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6097914483168975659L;
	/**
	 * Object associated to an existing metaElement, syntaxElement or
	 * InstElement - from JList or JComboBox
	 */
	private Object valueObject;
	/**
	 * MetaModel attribute object supporting the instance
	 */
	private AbstractAttribute attributeObject;

	public static final String
	/**
	 * Name of Instance identifier
	 */
	VAR_IDENTIFIER = "Identifier",
	/**
	 * Name of MetaModel attribute identifier, to recover object after load
	 */
	VAR_ATTRIBUTE_IDEN = "attributeIden",
	/**
	 * Name of the Value for the InstAttribute - indexes in case of JList
	 */
	VAR_VALUE = "Value",
	/**
	 * Name of display value for JList with indexes for VAR_VALUE (MClass and
	 * MEnumeration)
	 */
	VAR_DISPLAYVALUE = "DispValue",
	/**
	 * Initially for List IntSemanticGroupDependency
	 */
	VAR_OVERTWOREL_VALIDATION_LIST = "ValidGDList",
	/**
	 * Initially for List IntSemanticDirectRelation
	 */
	VAR_PAIRWISEREL_VALIDATION_LIST = "ValidDRList",
	/**
	 * Initially for List to support MetaEnumerations
	 */
	VAR_METAEDGE_LIST_VALIDATION = "ValidMEList";
	/**
	 * Dynamic storage of attributes
	 */
	protected Map<String, Object> vars = new HashMap<>();

	public InstAttribute() {

	}

	public InstAttribute(String identifier) {
		vars.put(VAR_IDENTIFIER, identifier);
	}

	public InstAttribute(String identifier,
			AbstractAttribute modelingAttribute, Object value) {
		super();
		this.attributeObject = modelingAttribute;
		vars.put(VAR_IDENTIFIER, identifier);
		vars.put(VAR_ATTRIBUTE_IDEN, modelingAttribute.getName());
		vars.put(VAR_VALUE, value);
		vars.put(VAR_DISPLAYVALUE, null);
	}

	public InstAttribute(String identifier,
			AbstractAttribute modelingAttribute, Object value,
			Object valueObject) {
		super();
		this.attributeObject = modelingAttribute;
		vars.put(VAR_IDENTIFIER, identifier);
		vars.put(VAR_ATTRIBUTE_IDEN, modelingAttribute.getName());
		vars.put(VAR_VALUE, value);
		vars.put(VAR_DISPLAYVALUE, null);
		this.valueObject = valueObject;

		// this.value = value;
	}

	public Object getVariable(String name) {
		return vars.get(name);
	}

	public void setVariable(String name, Object value) {
		vars.put(name, value);
	}

	public void setIdentifier(String identifier) {
		// this.identifier = identifier;
		setVariable(VAR_IDENTIFIER, identifier);
	}

	public void setAffectProperties(boolean affectProperties) {
		attributeObject.setAffectProperties(affectProperties);
	}

	public boolean isAffectProperties() {
		return attributeObject.isAffectProperties();
	}

	public void setDisplayName(String displayName) {
		attributeObject.setDisplayName(displayName);
	}

	public String getDisplayName() {
		return attributeObject.getDisplayName();
	}

	public void setValidationMEList(List<MetaPairwiseRelation> metaEdge) {
		// this.identifier = identifier;
		setVariable(VAR_METAEDGE_LIST_VALIDATION, metaEdge);
	}

	@SuppressWarnings("unchecked")
	public List<MetaPairwiseRelation> getValidationMEList() {
		return (List<MetaPairwiseRelation>) getVariable(VAR_METAEDGE_LIST_VALIDATION);
		// return identifier;
	}

	public void setValidationGDList(List<IntSemanticOverTwoRelation> semGD) {
		// this.identifier = identifier;
		setVariable(VAR_OVERTWOREL_VALIDATION_LIST, semGD);
	}

	@SuppressWarnings("unchecked")
	public List<IntSemanticOverTwoRelation> getOverTwoRelValidationList() {
		return (List<IntSemanticOverTwoRelation>) getVariable(VAR_OVERTWOREL_VALIDATION_LIST);
		// return identifier;
	}

	public void setValidationDRList(List<IntSemanticPairwiseRelation> semGD) {
		// this.identifier = identifier;
		setVariable(VAR_PAIRWISEREL_VALIDATION_LIST, semGD);
	}

	@SuppressWarnings("unchecked")
	public List<IntSemanticPairwiseRelation> getPairwiseRelValidationList() {
		return (List<IntSemanticPairwiseRelation>) getVariable(VAR_PAIRWISEREL_VALIDATION_LIST);
		// return identifier;
	}

	public void setAttribute(AbstractAttribute modelingAttribute) {
		this.attributeObject = modelingAttribute;
		if (modelingAttribute != null)
			setVariable(VAR_ATTRIBUTE_IDEN, modelingAttribute.getName());
	}

	public String getIdentifier() {
		return (String) getVariable(VAR_IDENTIFIER);
		// return identifier;
	}

	public AbstractAttribute getAttribute() {
		return attributeObject;
	}

	public String getAttributeName() {
		return (String) getVariable(VAR_ATTRIBUTE_IDEN);
	}

	public Object getValue() {
		return getVariable(VAR_VALUE);
		// return value;
	}

	public Object getDisplayValue() {
		if (getVariable(VAR_DISPLAYVALUE) == null)
			return getVariable(VAR_VALUE);
		else
			return getVariable(VAR_DISPLAYVALUE);
		// return value;
	}

	public void setValue(Object value) {
		setVariable(VAR_VALUE, value);
		// this.value = value;
	}

	public String getAttributeType() {
		return attributeObject.getType();
	}

	public Object getEnumType() {
		// TODO Auto-generated method stub
		return attributeObject.getClassCanonicalName();
	}

	public void setType(String selectedItem) {
		// TODO Auto-generated method stub

	}

	// Method from com.cfm.productline.Variable class
	public Float getAsFloat() {
		Object val = getValue();
		if (val == null)
			return null;

		if (val instanceof Float)
			return (Float) val;

		if (val instanceof String)
			return Float.parseFloat((String) val);

		return 0f;
	}

	// Method from com.cfm.productline.Variable class
	public Integer getAsInteger() {
		Object val = getValue();
		if (val == null)
			return null;

		if (val instanceof Integer)
			return (Integer) val;

		if (val instanceof String)
			return Integer.parseInt((String) val);

		return 0;
	}

	// Method from com.cfm.productline.Variable class
	public Boolean getAsBoolean() {
		Object val = getValue();
		if (val == null)
			return null;

		if (val instanceof Boolean)
			return (Boolean) val;

		if (val instanceof String) {
			// Check if it's 0
			if ("0".equals(val))
				return false;

			// Check if it's "false"
			if ("false".equalsIgnoreCase((String) val))
				return false;
		}
		return true;
	}

	public String toString() {
		Object val = getDisplayValue();
		if (val == null)
			return "";
		return val.toString();
	}

	public Map<String, Object> getVars() {
		return vars;
	}

	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}

	public void clearModelingAttribute() {
		attributeObject = null;
		valueObject = null;
		setVariable(VAR_OVERTWOREL_VALIDATION_LIST, null);
		setVariable(VAR_PAIRWISEREL_VALIDATION_LIST, null);
		setVariable(VAR_METAEDGE_LIST_VALIDATION, null);
	}

	public void displayValue(String out) {
		setVariable(VAR_DISPLAYVALUE, out);

	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	public Object getValueObject() {
		return valueObject;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof InstAttribute)
			return getIdentifier().equals(((InstAttribute) o).getIdentifier());
		else
			return false;
	}

	@Override
	public int hashCode() {
		return getIdentifier().hashCode();
	}

	@Override
	public String getName() {
		return (String) getVariable(VAR_ATTRIBUTE_IDEN);
	}

	public String getAttributeDefaultValue() {
		return (String) attributeObject.getDefaultValue();
	}

	public void updateValidationList(MetaElement metaElement,
			Map<String, MetaElement> mapElements) {
		if (metaElement instanceof MetaOverTwoRelation) {

			if (getEnumType() != null
					&& getEnumType().equals(
							MetaOverTwoRelation.VAR_SEMANTICPAIRWISEREL_CLASS)) {
				List<IntSemanticOverTwoRelation> metaGD = ((MetaOverTwoRelation) metaElement)
						.getSemanticRelations();
				setValidationGDList(metaGD);
			}
		}
		if (metaElement instanceof MetaPairwiseRelation) {

			if (getEnumType() != null
					&& getEnumType().equals(
							MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_CLASS)) {
				List<IntSemanticPairwiseRelation> directRel = ((MetaPairwiseRelation) metaElement)
						.getSemanticRelations();
				setValidationDRList(directRel);

			}
			if (getEnumType() != null
					&& getEnumType().equals(
							InstPairwiseRelation.VAR_METAPAIRWISE_CLASS)) {

				Iterator<String> elementNames = mapElements.keySet().iterator();
				List<MetaPairwiseRelation> metaGD = new ArrayList<MetaPairwiseRelation>();
				while (elementNames.hasNext()) {
					String elementName = elementNames.next();
					if (mapElements.get(elementName) instanceof MetaPairwiseRelation) // TODO
						// also
						// validate
						// origin
						// and
						// destination
						// relation
						metaGD.add((MetaPairwiseRelation) mapElements
								.get(elementName));
				}
				setValidationMEList(metaGD);
			}
		}
	}
}
