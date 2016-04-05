package com.variamos.dynsup.instance;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.model.SyntaxEnum;
import com.variamos.dynsup.model.SyntaxVertex;

/**
 * A class to represented modeling instances of enumerations from meta model on
 * VariaMos. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-10 *
 * @see com.variamos.dynsup.model.SyntaxEnum
 */
public class InstEnum extends InstVertex {

	/**
	 * 
	 */
	private static final long serialVersionUID = 188655707058755882L;

	public InstEnum() {
		super();
	}

	public InstEnum(SyntaxEnum metaEnumeration) {
		super();
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes(null);
	}

	public InstEnum(SyntaxVertex metaEnumeration,
			SyntaxElement editableMetaElement) {
		super();
		setEditableMetaElement(editableMetaElement);
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes(null);
	}

	public InstEnum(String identifier, SyntaxVertex metaEnumeration,
			Map<String, InstAttribute> attributes,
			Map<String, InstPairwiseRel> relations) {
		super(identifier, attributes, relations);
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes(null);
	}

	public InstEnum(String identifier, SyntaxVertex metaEnumeration) {
		super(identifier);
		setTransSupportMetaElement(metaEnumeration);
		createInstAttributes(null);
	}

	public InstEnum(String identifier, SyntaxVertex metaEnumeration,
			SyntaxElement editableMetaElement) {
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
		SyntaxElement supportMetaElement = this.getTransSupportMetaElement();
		setDynamicVariable(SyntaxElement.VAR_DESCRIPTION,
				supportMetaElement.getDescription());
	}

	public void setTransSupportMetaElement(SyntaxVertex metaVertex) {
		super.setTransSupportMetaElement(metaVertex);
		setDynamicVariable(InstConcept.VAR_METACONCEPT_IDEN,
				metaVertex.getAutoIdentifier());
		setDynamicVariable(SyntaxElement.VAR_DESCRIPTION,
				metaVertex.getDescription());
		setDynamicVariable(SyntaxElement.VAR_DESCRIPTION,
				metaVertex.getDescription());

		// createInstAttributes();
	}

	public void setMetaEnumerationUserIdentifier(
			String metaEnumerationIdentifier) {
		SyntaxElement supportMetaElement = this.getTransSupportMetaElement();
		setDynamicVariable(InstConcept.VAR_METACONCEPT_IDEN,
				metaEnumerationIdentifier);
		setDynamicVariable(SyntaxElement.VAR_DESCRIPTION,
				supportMetaElement.getDescription());
		// createInstAttributes();
	}

	public void clearEditableMetaVertex() {
		super.clearEditableMetaVertex();
		// supportMetaElement = null;
	}

}
