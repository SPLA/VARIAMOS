package com.variamos.syntax.instancesupport;

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
import com.variamos.syntaxsupport.semanticinterface.IntSemanticRelationType;

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
	 * This attribute is not serialized (get/set differ from name)
	 */
	private Object volatileValueObject;
	/**
	 * MetaModel/Semantic attribute object supporting the instance
	 * This attribute is not serialized (get/set differ from name)
	 */
	private AbstractAttribute volatileAttribute;
	
	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

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
	protected Map<String, Object> instAttributeAttributes = new HashMap<>();

	public InstAttribute() {

	}

	public InstAttribute(String identifier) {
		instAttributeAttributes.put(VAR_IDENTIFIER, identifier);
	}

	public InstAttribute(String identifier,
			AbstractAttribute modelingAttribute, Object value) {
		super();
		this.volatileAttribute = modelingAttribute;
		instAttributeAttributes.put(VAR_IDENTIFIER, identifier);
		instAttributeAttributes.put(VAR_ATTRIBUTE_IDEN, modelingAttribute.getName());
		instAttributeAttributes.put(VAR_VALUE, value);
		instAttributeAttributes.put(VAR_DISPLAYVALUE, null);
	}

	public InstAttribute(String identifier,
			AbstractAttribute modelingAttribute, Object value,
			Object valueObject) {
		super();
		this.volatileAttribute = modelingAttribute;
		instAttributeAttributes.put(VAR_IDENTIFIER, identifier);
		instAttributeAttributes.put(VAR_ATTRIBUTE_IDEN, modelingAttribute.getName());
		instAttributeAttributes.put(VAR_VALUE, value);
		instAttributeAttributes.put(VAR_DISPLAYVALUE, null);
		this.volatileValueObject = valueObject;

		// this.value = value;
	}

	public Object getInstAttributeAttribute(String name) {
		return instAttributeAttributes.get(name);
	}

	public void setInstAttributeAttribute(String name, Object value) {
		instAttributeAttributes.put(name, value);
	}

	public void setIdentifier(String identifier) {
		// this.identifier = identifier;
		setInstAttributeAttribute(VAR_IDENTIFIER, identifier);
	}

	public void setAffectProperties(boolean affectProperties) {
		volatileAttribute.setAffectProperties(affectProperties);
	}

	public boolean isAffectProperties() {
		return volatileAttribute.isAffectProperties();
	}

	public void setDisplayName(String displayName) {
		volatileAttribute.setDisplayName(displayName);
	}

	public String getDisplayName() {
		return volatileAttribute.getDisplayName();
	}

	public void setValidationMEList(List<MetaPairwiseRelation> metaEdge) {
		// this.identifier = identifier;
		setInstAttributeAttribute(VAR_METAEDGE_LIST_VALIDATION, metaEdge);
	}

	@SuppressWarnings("unchecked")
	public List<MetaPairwiseRelation> getValidationMEList() {
		return (List<MetaPairwiseRelation>) getInstAttributeAttribute(VAR_METAEDGE_LIST_VALIDATION);
		// return identifier;
	}

	public void setValidationRelationTypes(List<IntSemanticRelationType> semGD) {
		// this.identifier = identifier;
		setInstAttributeAttribute(VAR_OVERTWOREL_VALIDATION_LIST, semGD);
	}

	@SuppressWarnings("unchecked")
	public List<IntSemanticRelationType> getOverTwoRelValidationList() {
		return (List<IntSemanticRelationType>) getInstAttributeAttribute(VAR_OVERTWOREL_VALIDATION_LIST);
		// return identifier;
	}

	public void setValidationDRList(List<IntSemanticPairwiseRelation> semGD) {
		// this.identifier = identifier;
		setInstAttributeAttribute(VAR_PAIRWISEREL_VALIDATION_LIST, semGD);
	}

	@SuppressWarnings("unchecked")
	public List<IntSemanticPairwiseRelation> getPairwiseRelValidationList() {
		return (List<IntSemanticPairwiseRelation>) getInstAttributeAttribute(VAR_PAIRWISEREL_VALIDATION_LIST);
		// return identifier;
	}

	public void setAttribute(AbstractAttribute modelingAttribute) {
		this.volatileAttribute = modelingAttribute;
		if (modelingAttribute != null)
			setInstAttributeAttribute(VAR_ATTRIBUTE_IDEN, modelingAttribute.getName());
	}

	public String getIdentifier() {
		return (String) getInstAttributeAttribute(VAR_IDENTIFIER);
		// return identifier;
	}

	public AbstractAttribute getAttribute() {
		return volatileAttribute;
	}

	public String getAttributeName() {
		return (String) getInstAttributeAttribute(VAR_ATTRIBUTE_IDEN);
	}

	public Object getValue() {
		return getInstAttributeAttribute(VAR_VALUE);
		// return value;
	}

	public Object getDisplayValue() {
		if (getInstAttributeAttribute(VAR_DISPLAYVALUE) == null)
			return getInstAttributeAttribute(VAR_VALUE);
		else
			return getInstAttributeAttribute(VAR_DISPLAYVALUE);
		// return value;
	}

	public void setValue(Object value) {
		setInstAttributeAttribute(VAR_VALUE, value);
		// this.value = value;
	}

	public String getAttributeType() {
		return volatileAttribute.getType();
	}

	public Object getEnumType() {
		// TODO Auto-generated method stub
		return volatileAttribute.getClassCanonicalName();
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
	//TODO remove non serializable attributes
	public Map<String, Object> getInstAttributeAttributes() {
		return instAttributeAttributes;
	}
	//TODO add non serializable attributes
	public void setInstAttributeAttributes(Map<String, Object> vars) {
		this.instAttributeAttributes = vars;
	}

	public void clearModelingAttribute() {
	//	attributeObject = null;
	//	valueObject = null;
		setInstAttributeAttribute(VAR_OVERTWOREL_VALIDATION_LIST, null);
		setInstAttributeAttribute(VAR_PAIRWISEREL_VALIDATION_LIST, null);
		setInstAttributeAttribute(VAR_METAEDGE_LIST_VALIDATION, null);
	}

	public void displayValue(String out) {
		setInstAttributeAttribute(VAR_DISPLAYVALUE, out);

	}

	public void setValueObject(Object valueObject) {
		this.volatileValueObject = valueObject;
	}

	public Object getValueObject() {
		return volatileValueObject;
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
		return (String) getInstAttributeAttribute(VAR_ATTRIBUTE_IDEN);
	}

	public String getAttributeDefaultValue() {
		return (String) volatileAttribute.getDefaultValue();
	}

	public void updateValidationList(InstElement instElement,
			Map<String, MetaElement> mapElements) {
		if (instElement instanceof InstOverTwoRelation) {

			if (this.getAttribute() != null && getEnumType() != null
					&& getEnumType()
							.equals("com.variamos.refas.core.sematicsmetamodel.SemanticRelationType")) {
				List<IntSemanticRelationType> semanticRelationTypes = ((MetaOverTwoRelation) instElement
						.getTransSupportMetaElement()).getSemanticRelationTypes();
				setValidationRelationTypes(semanticRelationTypes);
			}
		}
		if (instElement instanceof InstPairwiseRelation) {
			if (this.getAttribute() != null && getEnumType() != null
					&& getEnumType()
							.equals("com.variamos.refas.core.sematicsmetamodel.SemanticRelationType")) {
				List<IntSemanticRelationType> semanticRelationTypes = ((MetaPairwiseRelation) instElement
						.getTransSupportMetaElement()).getSemanticRelationTypes();
				setValidationRelationTypes(semanticRelationTypes);
			}
			if (this.getAttribute() != null && getEnumType() != null
					&& getEnumType().equals(
							InstPairwiseRelation.VAR_METAPAIRWISE_OBJ_CLASS)) {

				Iterator<String> elementNames = mapElements.keySet().iterator();
				List<MetaPairwiseRelation> metaGD = new ArrayList<MetaPairwiseRelation>();
				while (elementNames.hasNext()) {
					String elementName = elementNames.next();
					if (mapElements.get(elementName) instanceof MetaPairwiseRelation) // TODO
						metaGD.add((MetaPairwiseRelation) mapElements
								.get(elementName));
				}
				setValidationMEList(metaGD);
			}
		}
	}
}
