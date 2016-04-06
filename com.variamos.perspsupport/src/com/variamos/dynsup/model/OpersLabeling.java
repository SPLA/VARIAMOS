package com.variamos.dynsup.model;

import java.util.ArrayList;
import java.util.List;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;

public class OpersLabeling extends OpersElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4720471978011738167L;

	private List<OpersIOAttribute> attributes;

	public OpersLabeling() {
		super(null);
	}

	public OpersLabeling(String identifier) {
		super(identifier);
		attributes = new ArrayList<OpersIOAttribute>();
	}

	public boolean hasAttribute(String concept, String name) {
		for (OpersIOAttribute s : attributes)
			if (s.getConceptId().equals(concept)
					&& s.getAttributeId().equals(name))
				return true;
		return false;
	}

	public List<OpersIOAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<OpersIOAttribute> outVariables) {
		this.attributes = outVariables;
	}

	public void addAttribute(OpersIOAttribute attribute) {
		attributes.add(attribute);
	}

	// 1 include, -1 exclude, 0 unknown
	public int validateAttribute(InstElement instElement, String attribute) {
		int include = 99999;
		int exclude = 99999;
		List<String> parents = new ArrayList<String>();
		InstElement element = instElement;
		while (element != null) {
			parents.add(element.getIdentifier());
			InstElement elt = element;
			if (elt instanceof InstPairwiseRel)
				break;
			element = null;
			for (InstElement e : elt.getTargetRelations()) {
				if (((InstPairwiseRel) e).getSupportMetaPairwiseRelIden()
						.equals("ExtendsRelation")) {
					element = e.getTargetRelations().get(0);
				}
			}
		}
		for (OpersIOAttribute ioAtt : attributes) {
			if (attribute.equals(ioAtt.getAttributeId())
					&& parents.contains(ioAtt.getConceptId()))
				if (ioAtt.isInclude()) {
					if (include > parents.indexOf(ioAtt.getConceptId()))
						include = parents.indexOf(ioAtt.getConceptId());
				} else if (exclude > parents.indexOf(ioAtt.getConceptId()))
					exclude = parents.indexOf(ioAtt.getConceptId());
		}
		if (include == 99999 && exclude == 99999)
			return 0;
		if (include < exclude)
			return 1;
		return -1;
	}
}
