package com.variamos.dynsup.instance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.variamos.dynsup.interfaces.IntInstAttribute;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelExpr;
import com.variamos.dynsup.model.OpersConcept;

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
public class InstAttribute implements Serializable, IntInstAttribute,
		Comparable<Object>, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6097914483168975659L;
	/**
	 * Object associated to an existing metaElement, syntaxElement or
	 * InstElement - from JList or JComboBox This attribute is not serialized
	 * (get/set differ from name)
	 */
	private Object volatileValueObject;
	/**
	 * MetaModel/Semantic attribute object supporting the instance This
	 * attribute is not serialized (get/set differ from name)
	 */
	private ElemAttribute volatileAttribute;

	private List<InstAttribute> additionalAttributes = new ArrayList<InstAttribute>();

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
	VAR_ATTRIBUTE_IDEN = "attId",
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
	 * List InstAttributes of Relation Types
	 */
	VAR_OPERS_OVERTWOREL_LIST = "opersOTRelList",
	/**
	 * Initially for List IntSemanticDirectRelation
	 */
	VAR_PAIRWISEREL_VALIDATION_LIST = "ValidDRList",
	/**
	 * Initially for List to support MetaEnumerations
	 */
	VAR_METAEDGE_LIST_VALIDATION = "ValidMEList",
	/**
	 * Dynamic storage of attributes
	 */
	VAR_GROUP = "Group";
	protected Map<String, Object> instAttributeAttributes = new HashMap<>();

	public InstAttribute() {

	}

	public InstAttribute(String identifier) {
		instAttributeAttributes.put(VAR_IDENTIFIER, identifier);
	}

	public InstAttribute(String identifier, ElemAttribute modelingAttribute,
			Object value) {
		super();
		this.volatileAttribute = modelingAttribute;
		instAttributeAttributes.put(VAR_IDENTIFIER, identifier);
		instAttributeAttributes.put(VAR_ATTRIBUTE_IDEN,
				modelingAttribute.getName());
		instAttributeAttributes.put(VAR_VALUE, value);
		instAttributeAttributes.put(VAR_DISPLAYVALUE, null);
	}

	public InstAttribute(String identifier, ElemAttribute modelingAttribute,
			Object value, Object valueObject) {
		super();
		this.volatileAttribute = modelingAttribute;
		instAttributeAttributes.put(VAR_IDENTIFIER, identifier);
		instAttributeAttributes.put(VAR_ATTRIBUTE_IDEN,
				modelingAttribute.getName());
		instAttributeAttributes.put(VAR_VALUE, value);
		instAttributeAttributes.put(VAR_DISPLAYVALUE, null);
		this.volatileValueObject = valueObject;

		// this.value = value;
	}

	@Override
	protected InstAttribute clone() {
		InstAttribute out = null;
		try {
			out = (InstAttribute) super.clone();
			out.instAttributeAttributes = new HashMap<>();
			out.instAttributeAttributes.putAll(instAttributeAttributes);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return out;
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

	@Override
	public boolean isAffectProperties() {
		return volatileAttribute.isAffectProperties();
	}

	public void setDisplayName(String displayName) {
		volatileAttribute.setDisplayName(displayName);
	}

	@Override
	public String getDisplayName() {
		return volatileAttribute.getDisplayName();
	}

	public void setElemAttributeName(String name) {
		volatileAttribute.setName(name);
	}

	public String getElemAttributeName() {
		return volatileAttribute.getName();
	}

	public void setValidationMEList(List<InstElement> metaEdge) {
		// this.identifier = identifier;
		setInstAttributeAttribute(VAR_METAEDGE_LIST_VALIDATION, metaEdge);
	}

	@SuppressWarnings("unchecked")
	public List<InstElement> getValidationMEList() {
		return (List<InstElement>) getInstAttributeAttribute(VAR_METAEDGE_LIST_VALIDATION);
		// return identifier;
	}

	// New
	public void setOpersOverTwoRelList(List<InstAttribute> semGD) {
		// this.identifier = identifier;
		setInstAttributeAttribute(VAR_OPERS_OVERTWOREL_LIST, semGD);
	}

	@SuppressWarnings("unchecked")
	public List<InstAttribute> getOpersOverTwoRelList() {
		return (List<InstAttribute>) getInstAttributeAttribute(VAR_OPERS_OVERTWOREL_LIST);
		// return identifier;
	}

	public void setValidationDRList(List<OpersConcept> semGD) {
		// this.identifier = identifier;
		setInstAttributeAttribute(VAR_PAIRWISEREL_VALIDATION_LIST, semGD);
	}

	@SuppressWarnings("unchecked")
	public List<OpersConcept> getPairwiseRelValidationList() {
		return (List<OpersConcept>) getInstAttributeAttribute(VAR_PAIRWISEREL_VALIDATION_LIST);
		// return identifier;
	}

	public void setAttribute(ElemAttribute modelingAttribute) {
		this.volatileAttribute = modelingAttribute;
		if (modelingAttribute != null)
			setInstAttributeAttribute(VAR_ATTRIBUTE_IDEN,
					modelingAttribute.getName());
	}

	@Override
	public String getIdentifier() {
		return (String) getInstAttributeAttribute(VAR_IDENTIFIER);
		// return identifier;
	}

	public ElemAttribute getAttribute() {
		return volatileAttribute;
	}

	public String getAttributeName() {
		return (String) getInstAttributeAttribute(VAR_ATTRIBUTE_IDEN);
	}

	public void setAttributeName(String name) {
		setInstAttributeAttribute(VAR_ATTRIBUTE_IDEN, name);
	}

	@Override
	public Object getValue() {
		return getInstAttributeAttribute(VAR_VALUE);
		// return value;
	}

	@Override
	public Object getGroup() {
		return getInstAttributeAttribute(VAR_GROUP);
		// return value;
	}

	public Object getDisplayValue() {
		if (getInstAttributeAttribute(VAR_DISPLAYVALUE) == null)
			return getInstAttributeAttribute(VAR_VALUE);
		else
			return getInstAttributeAttribute(VAR_DISPLAYVALUE);
		// return value;
	}

	@Override
	public void setValue(Object value) {
		setInstAttributeAttribute(VAR_VALUE, value);
		// this.value = value;
	}

	public void setValues(Object value) {
		setInstAttributeAttribute(VAR_VALUE, value);
		int pos = 0;
		if (value instanceof ModelExpr)
			for (InstAttribute att : this.additionalAttributes) {
				att.setInstAttributeAttribute(VAR_VALUE,
						((ModelExpr) value).clone(pos++));
			}
		// this.value = value;
	}

	@Override
	public void setGroup(Object value) {
		setInstAttributeAttribute(VAR_GROUP, value);
		// this.value = value;
	}

	@Override
	public String getType() {
		if (volatileAttribute != null)
			return volatileAttribute.getType();
		else
			return null;
	}

	public Object getEnumType() {
		// TODO Auto-generated method stub
		if (volatileAttribute != null)
			return volatileAttribute.getClassCanonicalName();
		System.out.println("null volatile att:" + this.getIdentifier());
		return null;
	}

	@Override
	public void setType(String selectedItem) {
		// TODO Auto-generated method stub

	}

	// Method from com.cfm.productline.Variable class
	@Override
	public Float getAsFloat() {
		Object val = getValue();
		if (val == null)
			return null;

		if (val instanceof Integer)
			return ((Integer) val).floatValue();

		if (val instanceof Float)
			return (Float) val;

		if (val instanceof String)
			return Float.parseFloat((String) val);

		return 0f;
	}

	// Method from com.cfm.productline.Variable class
	@Override
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
	@Override
	public Boolean getAsBoolean() {
		Object val = getValue();
		if (val == null)
			return null;

		if (val instanceof Boolean)
			return (Boolean) val;

		if (val instanceof Integer)
			if (((Integer) val).intValue() == 1)
				return true;
			else
				return false;

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

	@Override
	public String toString() {
		Object val = this.getDisplayValue();// .getDisplayName();
		if (val == null)
			return "";
		if (val instanceof ArrayList) {
			String out = val.toString();
			out = out.substring(0, out.length() - 2) + "]";
			return out;
		}
		return val.toString();
	}

	// TODO remove non serializable attributes
	public Map<String, Object> getInstAttributeAttributes() {
		return instAttributeAttributes;
	}

	// TODO add non serializable attributes
	public void setInstAttributeAttributes(Map<String, Object> vars) {
		this.instAttributeAttributes = vars;
	}

	public void clearModelingAttribute() {
		// attributeObject = null;
		// valueObject = null;
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
			Map<String, InstElement> mapElements) {
		// FIXME change this validation to use relationTypesAttributes instead
		// of relationType
		if (!(instElement instanceof InstPairwiseRel)
				&& instElement.getTransSupportMetaElement() == null)
			return;
		if ((instElement instanceof InstPairwiseRel)
				|| instElement.getTransSupportMetaElement().getType() == 'P') {
			if (this.getAttribute() != null
					&& getEnumType() != null
					&& getEnumType().equals(
							InstAttribute.class.getCanonicalName())) {
				List<InstAttribute> semanticRelationTypes = instElement
						.getTransSupportMetaElement().getOpersRelationTypes();
				this.setOpersOverTwoRelList(semanticRelationTypes);
			}
			if (this.getAttribute() != null
					&& getEnumType() != null
					&& getEnumType().equals(
							InstPairwiseRel.VAR_METAPAIRWISE_CLASS)) {
				if (mapElements != null) {
					Iterator<String> elementNames = mapElements.keySet()
							.iterator();
					List<InstElement> metaGD = new ArrayList<InstElement>();
					while (elementNames.hasNext()) {
						String elementName = elementNames.next();
						if (mapElements.get(elementName) instanceof InstElement) // TODO
							metaGD.add(mapElements.get(elementName));
					}
					setValidationMEList(metaGD);
				}
			}
		} else if (instElement.getTransSupportMetaElement().getType() == 'O'
				|| instElement.getTransSupportMetaElement().getType() == 'T') {
			if (this.getAttribute() != null
					&& getEnumType() != null
					&& getEnumType().equals(
							InstAttribute.class.getCanonicalName())) {
				List<InstAttribute> semanticRelationTypes = instElement
						.getTransSupportMetaElement().getOpersRelationTypes();
				// System.out.println("semreltypes: " + semanticRelationTypes);
				setOpersOverTwoRelList(semanticRelationTypes);
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(Object o) {
		if (getValue() instanceof Comparable
				&& ((InstAttribute) o).getValue() instanceof Comparable) {
			return ((Comparable<Comparable<?>>) getValue())
					.compareTo((Comparable<?>) ((InstAttribute) o).getValue());
		}
		return 0;
	}

	public String getToolTipText() {
		return volatileAttribute.getToolTipText();
	}

	public String setToolTipText(String out) {
		volatileAttribute.setToolTipText(out);
		return null;
	}

	public void updateAdditionalAttributes(int size) {
		int currentSize = additionalAttributes.size();
		if (currentSize < size)
			for (int i = currentSize; i < size; i++)
				additionalAttributes.add(this.clone());
		else if (currentSize > size)
			for (int i = currentSize; i > size; i--)
				additionalAttributes.remove(i);
	}

	public List<InstAttribute> getAdditionalAttributes() {
		return additionalAttributes;
	}

	public InstAttribute getAdditionalAttribute(int pos) {
		return additionalAttributes.get(pos);
	}

	public void setAdditionalAttributes(List<InstAttribute> additionalAttributes) {
		this.additionalAttributes = additionalAttributes;
	}
}
