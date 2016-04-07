package com.variamos.dynsup.instance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.dynsup.interfaces.IntOpersElement;
import com.variamos.dynsup.model.SyntaxElement;

/**
 * A class to represented modeling instances of elements as vertex from meta
 * model and semantic model on VariaMos. Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24 *
 * @see com.variamos.dynsup.model.SyntaxElement
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
		super("", new HashMap<String, InstAttribute>());
	}

	public InstConcept(SyntaxElement supportMetaElement) {
		super("", new HashMap<String, InstAttribute>());
		setTransSupportMetaElement(supportMetaElement);
		createInstAttributes(null);
	}

	public InstConcept(String identifier, SyntaxElement supportMetaElement,
			SyntaxElement editableMetaElement) {
		super(identifier, new HashMap<String, InstAttribute>());
		if (supportMetaElement != null)
			setTransSupportMetaElement(supportMetaElement);
		setEdSyntaxEle(editableMetaElement);
		createInstAttributes(null);
		copyValuesToInstAttributes(null);
	}

	public InstConcept(String identifier, SyntaxElement supportMetaElement,
			IntOpersElement editableSemanticElement) {
		super(identifier, new HashMap<String, InstAttribute>());
		if (supportMetaElement != null)
			setTransSupportMetaElement(supportMetaElement);
		setEdOperEle(editableSemanticElement);
		createInstAttributes(null);
	}

	public void createInstAttributes() {
		createInstAttributes(null);
	}

	public InstConcept(String identifier, SyntaxElement supportMetaElement,
			Map<String, InstAttribute> attributes,
			Map<String, InstPairwiseRel> relations) {
		super(identifier, attributes);
		setTransSupportMetaElement(supportMetaElement);
		createInstAttributes(null);
	}

	public InstConcept(String identifier, SyntaxElement supportMetaElement) {
		super(identifier, new HashMap<String, InstAttribute>());
		setTransSupportMetaElement(supportMetaElement);
		createInstAttributes(null);
	}

	public List<InstAttribute> getEditableVariables(
			List<InstElement> syntaxParents) {
		// superclass
		createInstAttributes(syntaxParents);
		Set<String> attributesNames = getTransSupportMetaElement()
				.getPropEditableAttributesSet(syntaxParents);
		return getFilteredInstAttributes(attributesNames, null);
	}

	@Override
	public void setTransSupportMetaElement(SyntaxElement metaElement) {
		super.setTransSupportMetaElement(metaElement);

		// TODO delete
		setDynamicVariable(VAR_METACONCEPT_IDEN,
				metaElement.getAutoIdentifier());
		setDynamicVariable(SyntaxElement.VAR_DESCRIPTION,
				metaElement.getDescription());
		// createInstAttributes();
	}

	public void setMetaElementIdentifier(String metaElementIdentifier) {
		SyntaxElement supportMetaElement = this.getTransSupportMetaElement();
		setDynamicVariable(VAR_METACONCEPT_IDEN, metaElementIdentifier);
		setDynamicVariable(SyntaxElement.VAR_DESCRIPTION,
				supportMetaElement.getDescription());
		// createInstAttributes();
	}
}
