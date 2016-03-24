package com.variamos.perspsupport.expressionsupport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.variamos.hlcl.LabelingOrder;
import com.variamos.hlcl.NumericExpression;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.opers.OpersAbstractElement;

public class OpersLabeling extends OpersAbstractElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4720471978011738167L;
	private List<NumericExpression> orderExpressionList;
	private List<LabelingOrder> labelingOrderList;

	private String labelId;
	private String name;
	private boolean once;
	private int position;
	private Set<OpersIOAttribute> attributes;

	public OpersLabeling(String name, String labelId, int position,
			boolean once, List<LabelingOrder> labelingOrderList,
			List<NumericExpression> orderExpressionList

	) {
		super(null);
		this.name = name;
		this.setLabelId(labelId);
		this.orderExpressionList = orderExpressionList;
		this.labelingOrderList = labelingOrderList;
		this.position = position;
		this.once = once;
		attributes = new HashSet<OpersIOAttribute>();
	}

	public List<NumericExpression> getOrderExpressionList() {
		return orderExpressionList;
	}

	public void setOrderExpressionList(
			List<NumericExpression> orderExpressionList) {
		this.orderExpressionList = orderExpressionList;
	}

	public List<LabelingOrder> getLabelingOrderList() {
		return labelingOrderList;
	}

	public void setLabelingOrderList(List<LabelingOrder> labelingOrderList) {
		this.labelingOrderList = labelingOrderList;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOnce() {
		return once;
	}

	public void setOnce(boolean once) {
		this.once = once;
	}

	public boolean hasAttribute(String concept, String name) {
		for (OpersIOAttribute s : attributes)
			if (s.getConceptId().equals(concept)
					&& s.getAttributeId().equals(name))
				return true;
		return false;
	}

	public Object getName() {
		return name;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public Set<OpersIOAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<OpersIOAttribute> outVariables) {
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
			if (elt instanceof InstPairwiseRelation)
				break;
			element = null;
			for (InstElement e : elt.getTargetRelations()) {
				if (((InstPairwiseRelation) e).getSupportMetaPairwiseRelIden()
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
