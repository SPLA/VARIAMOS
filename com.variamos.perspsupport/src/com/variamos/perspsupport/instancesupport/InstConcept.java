package com.variamos.perspsupport.instancesupport;

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

	public InstConcept(MetaElement supportMetaElement) {
		super("");
		setTransSupportMetaElement(supportMetaElement);
		createInstAttributes(null);
	}

	public InstConcept(String identifier, MetaElement supportMetaElement,
			MetaElement editableMetaElement) {
		super(identifier);
		if (supportMetaElement != null)
			setTransSupportMetaElement(supportMetaElement);
		setEditableMetaElement(editableMetaElement);
		createInstAttributes(null);
		copyValuesToInstAttributes(null);
	}

	public InstConcept(String identifier, MetaElement supportMetaElement,
			IntSemanticElement editableSemanticElement) {
		super(identifier);
		if (supportMetaElement != null)
			setTransSupportMetaElement(supportMetaElement);
		setEditableSemanticElement(editableSemanticElement);
		createInstAttributes(null);
	}

	public InstConcept(String identifier, MetaElement supportMetaElement,
			Map<String, InstAttribute> attributes,
			Map<String, InstPairwiseRelation> relations) {
		super(identifier, attributes, relations);
		setTransSupportMetaElement(supportMetaElement);
		createInstAttributes(null);
	}

	public InstConcept(String identifier, MetaElement supportMetaElement) {
		super(identifier);
		setTransSupportMetaElement(supportMetaElement);
		createInstAttributes(null);
	}

	public List<InstAttribute> getEditableVariables(List<InstElement> parents) {
		// superclass
		createInstAttributes(parents);
		Set<String> attributesNames = getTransSupportMetaElement()
				.getPropEditableAttributesSet(parents);
		return getFilteredInstAttributes(attributesNames, null);
	}

	public String getSupportMetaElementUserIdentifier() {
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		return (String) dynamicAttributesMap.get(VAR_METACONCEPT_IDEN);
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
		setDynamicVariable(VAR_METACONCEPT_IDEN,
				metaElement.getAutoIdentifier());
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
