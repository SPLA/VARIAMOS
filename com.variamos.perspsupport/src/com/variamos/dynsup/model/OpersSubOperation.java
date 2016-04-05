package com.variamos.dynsup.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;

/**
 * TODO A class to support sub actions to generalize the semantic
 * operationalization with GUI edition. Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
public class OpersSubOperation extends OpersElement implements
		Comparable<OpersSubOperation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965378108648056600L;
	private int position;
	private Set<OpersIOAttribute> inAttributes;
	private Set<OpersIOAttribute> outAttributes;

	// private List<OperationLabeling> operationLabelings;

	public OpersSubOperation(int position, String identifier) {
		super(identifier);
		this.position = position;
		inAttributes = new HashSet<OpersIOAttribute>();
		outAttributes = new HashSet<OpersIOAttribute>();
	}

	public OpersSubOperation() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	// public void addOperationLabeling(OperationLabeling operationLabeling) {
	// operationLabelings.add(operationLabeling);
	// }

	public boolean hasInVariable(String concept, String attribute) {
		for (OpersIOAttribute var : inAttributes)
			if (var.getConceptId().equals(concept)
					&& var.getAttributeId().equals(attribute))
				return true;
		return false;
	}

	public Set<OpersIOAttribute> getInAttributes() {
		return inAttributes;
	}

	public void setInAttributes(Set<OpersIOAttribute> inVariables) {
		this.inAttributes = inVariables;
	}

	public Set<OpersIOAttribute> getOutAttributes() {
		return outAttributes;
	}

	public void setOutAttribute(Set<OpersIOAttribute> outVariables) {
		this.outAttributes = outVariables;
	}

	public boolean hasOutVariable(String concept, String attribute) {
		for (OpersIOAttribute var : outAttributes)
			if (var.getConceptId().equals(concept)
					&& var.getAttributeId().equals(attribute))
				return true;
		return false;
	}

	public void addInAttribute(OpersIOAttribute attribute) {
		inAttributes.add(attribute);
	}

	public void addOutAttribute(OpersIOAttribute attribute) {
		outAttributes.add(attribute);
	}

	// 1 include, -1 exclude, 0 unknown
	public int validateAttribute(InstElement instElement, String attribute,
			boolean in) {
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
		if (in)
			for (OpersIOAttribute ioAtt : inAttributes) {
				if (attribute.equals(ioAtt.getAttributeId())
						&& parents.contains(ioAtt.getConceptId()))
					if (ioAtt.isInclude()) {
						if (include > parents.indexOf(ioAtt.getConceptId()))
							include = parents.indexOf(ioAtt.getConceptId());
					} else if (exclude > parents.indexOf(ioAtt.getConceptId()))
						exclude = parents.indexOf(ioAtt.getConceptId());
			}
		if (!in)
			for (OpersIOAttribute ioAtt : outAttributes) {
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

	@Override
	public int compareTo(OpersSubOperation o) {
		return this.getPosition() - o.getPosition();
	}
}
