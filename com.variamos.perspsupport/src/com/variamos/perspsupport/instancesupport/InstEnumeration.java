package com.variamos.perspsupport.instancesupport;

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

	public InstEnumeration() {
		super();
	}

	public InstEnumeration(MetaEnumeration metaEnumeration) {
		super();
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes(null);
	}

	public InstEnumeration(MetaVertex metaEnumeration,
			MetaElement editableMetaElement) {
		super();
		setEditableMetaElement(editableMetaElement);
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes(null);
	}

	public InstEnumeration(String identifier, MetaVertex metaEnumeration,
			Map<String, InstAttribute> attributes,
			Map<String, InstPairwiseRelation> relations) {
		super(identifier, attributes, relations);
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes(null);
	}

	public InstEnumeration(String identifier, MetaVertex metaEnumeration) {
		super(identifier);
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes(null);
	}

	public InstEnumeration(String identifier, MetaVertex metaEnumeration,
			MetaElement editableMetaElement) {
		super(identifier);
		setEditableMetaElement(editableMetaElement);
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes(null);
	}

	public List<InstAttribute> getEditableVariables(
			List<InstElement> syntaxParents) { // TODO
		// move
		// to
		// superclass
		Set<String> attributesNames = getTransSupportMetaElement()
				.getPropEditableAttributesSet(syntaxParents);
		return getFilteredInstAttributes(attributesNames, null);
	}

	public List<InstAttribute> getVisibleVariables(
			List<InstElement> syntaxParents, List<InstElement> opersParents) { // TODO
																				// move
																				// to
		// superclass
		Set<String> attributesNames = getTransSupportMetaElement()
				.getPropVisibleAttributesSet(syntaxParents);
		return getFilteredInstAttributes(attributesNames, null);
	}

	public void setIdentifier(String identifier) {
		super.setIdentifier(identifier);
		MetaElement supportMetaElement = this.getTransSupportMetaElement();
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				supportMetaElement.getDescription());
	}

	public void setTransSupportMetaElement(MetaVertex metaVertex) {
		super.setTransSupportMetaElement(metaVertex);
		setDynamicVariable(InstConcept.VAR_METACONCEPT_IDEN,
				metaVertex.getAutoIdentifier());
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				metaVertex.getDescription());
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				metaVertex.getDescription());

		// createInstAttributes();
	}

	public void setMetaEnumerationUserIdentifier(
			String metaEnumerationIdentifier) {
		MetaElement supportMetaElement = this.getTransSupportMetaElement();
		setDynamicVariable(InstConcept.VAR_METACONCEPT_IDEN,
				metaEnumerationIdentifier);
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				supportMetaElement.getDescription());
		// createInstAttributes();
	}

	public void clearEditableMetaVertex() {
		super.clearEditableMetaVertex();
		// supportMetaElement = null;
	}

}
