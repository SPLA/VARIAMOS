package com.variamos.perspsupport.instancesupport;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaEnumeration;
import com.variamos.perspsupport.syntaxsupport.MetaVertex;

/**
 * A class to represented modeling instances of enumerations from meta model on
 * VariaMos. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-10 *
 * @see com.variamos.perspsupport.syntaxsupport.MetaEnumeration
 */
public class InstEnumeration extends InstVertex {

	/**
	 * 
	 */
	private static final long serialVersionUID = 188655707058755882L;
	public static final String VAR_METAENUM_IDEN = "MetaEnumIde";

	public InstEnumeration() {
		super();
	}

	public InstEnumeration(MetaEnumeration metaEnumeration) {
		super();
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes();
	}

	public InstEnumeration(MetaVertex metaEnumeration,
			MetaElement editableMetaElement) {
		super();
		setEditableMetaElement(editableMetaElement);
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes();
	}

	public InstEnumeration(String identifier, MetaVertex metaEnumeration,
			Map<String, InstAttribute> attributes,
			Map<String, InstPairwiseRelation> relations) {
		super(identifier, attributes, relations);
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes();
	}

	public InstEnumeration(String identifier, MetaVertex metaEnumeration) {
		super(identifier);
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes();
	}

	public InstEnumeration(String identifier, MetaVertex metaEnumeration,
			MetaElement editableMetaElement) {
		super(identifier);
		setEditableMetaElement(editableMetaElement);
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes();
	}

	protected void createInstAttributes() {
		Iterator<String> modelingAttributes = getTransSupportMetaElement()
				.getModelingAttributesNames(null).iterator();
		while (modelingAttributes.hasNext()) {
			String name = modelingAttributes.next();
			if (name.equals(MetaElement.VAR_AUTOIDENTIFIER))
				addInstAttribute(name, getTransSupportMetaElement()
						.getModelingAttribute(name, null), getAutoIdentifier());
			else if (name.equals(MetaElement.VAR_USERIDENTIFIER))
				addInstAttribute(name, getTransSupportMetaElement()
						.getModelingAttribute(name, null), getUserIdentifier());
			else if (name.equals(MetaElement.VAR_DESCRIPTION))
				addInstAttribute(name, getTransSupportMetaElement()
						.getModelingAttribute(name, null),
						getTransSupportMetaElement().getDescription());
			else
				addInstAttribute(name, getTransSupportMetaElement()
						.getModelingAttribute(name, null), null);
		}
	}

	public List<InstAttribute> getEditableVariables(List<InstElement> parents) { // TODO
																					// move
																					// to
		// superclass
		Set<String> attributesNames = getTransSupportMetaElement()
				.getPropEditableAttributes(parents);
		return getFilteredInstAttributes(attributesNames, null);
	}

	public List<InstAttribute> getVisibleVariables(List<InstElement> parents) { // TODO
																				// move
																				// to
		// superclass
		Set<String> attributesNames = getTransSupportMetaElement()
				.getPropVisibleAttributes(parents);
		return getFilteredInstAttributes(attributesNames, null);
	}

	public String getSupportMetaElementUserIdentifier() {
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		return (String) dynamicAttributesMap.get(VAR_METAENUM_IDEN);
	}

	public void setIdentifier(String identifier) {
		super.setIdentifier(identifier);
		MetaElement supportMetaElement = this.getTransSupportMetaElement();
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				supportMetaElement.getDescription());
	}

	public void setTransSupportMetaElement(MetaVertex metaEnumeration) {
		super.setTransSupportMetaElement(metaEnumeration);
		setDynamicVariable(VAR_METAENUM_IDEN,
				metaEnumeration.getAutoIdentifier());
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				metaEnumeration.getDescription());
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				metaEnumeration.getDescription());

		// createInstAttributes();
	}

	public void setMetaEnumerationUserIdentifier(
			String metaEnumerationIdentifier) {
		MetaElement supportMetaElement = this.getTransSupportMetaElement();
		setDynamicVariable(VAR_METAENUM_IDEN, metaEnumerationIdentifier);
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				supportMetaElement.getDescription());
		// createInstAttributes();
	}

	public void clearEditableMetaVertex() {
		super.clearEditableMetaVertex();
		// supportMetaElement = null;
	}

}
