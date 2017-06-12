package com.variamos.dynsup.instance;

import java.util.HashMap;
import java.util.Map;

import com.variamos.dynsup.model.OpersElement;
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

	public InstConcept(InstElement supportInstElement) {
		super("", new HashMap<String, InstAttribute>());
		setTransSupInstElement(supportInstElement);
		createInstAttributes(null);
	}

	public InstConcept(String identifier, InstElement supportInstElement,
			SyntaxElement editableMetaElement) {
		super(identifier, new HashMap<String, InstAttribute>());
		if (supportInstElement != null)
			setTransSupInstElement(supportInstElement);
		setEdSyntaxEle(editableMetaElement);
		createInstAttributes(null);
		copyValuesToInstAttributes(null);
	}

	public InstConcept(String identifier, OpersElement editableSemanticElement,
			InstElement supInstElement) {
		this(identifier, supInstElement, editableSemanticElement);
		setTransSupInstElement(supInstElement);
	}

	public InstConcept(String identifier, InstElement supportInstElement,
			OpersElement editableSemanticElement) {
		super(identifier, new HashMap<String, InstAttribute>());
		if (supportInstElement != null)
			setTransSupInstElement(supportInstElement);
		setEdOperEle(editableSemanticElement);
		createInstAttributes(null);
	}

	public void createInstAttributes() {
		createInstAttributes(null);
	}

	public InstConcept(String identifier, InstElement supportMetaElement,
			Map<String, InstAttribute> attributes,
			Map<String, InstPairwiseRel> relations) {
		super(identifier, attributes);
		setTransSupInstElement(supportMetaElement);
		createInstAttributes(null);
	}

	public InstConcept(String identifier, InstElement supportInstElement) {
		super(identifier, new HashMap<String, InstAttribute>());
		setTransSupInstElement(supportInstElement);
		createInstAttributes(null);
	}

	@Override
	@Deprecated
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
