package com.variamos.dynsup.instance;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to represented the common aspects of modeling vertex of concepts from
 * meta model and semantic model on VariaMos. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24 *
 * @see com.variamos.dynsup.model.SyntaxConcept
 */
public abstract class InstVertex extends InstElement {
	private static final long serialVersionUID = -2214656166959965220L;

	public InstVertex() {
		this(null, new HashMap<String, InstAttribute>(),
				new HashMap<String, InstPairwiseRel>());
	}

	public InstVertex(String identifier) {
		this(identifier, new HashMap<String, InstAttribute>(),
				new HashMap<String, InstPairwiseRel>());
	}

	public InstVertex(String identifier,
			Map<String, InstAttribute> instAttributes,
			Map<String, InstPairwiseRel> instRelations) {
		super(identifier);
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_INSTATTRIBUTES, instAttributes);
	}
}
