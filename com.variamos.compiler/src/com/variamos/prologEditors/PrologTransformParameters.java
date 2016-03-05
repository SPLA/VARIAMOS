package com.variamos.prologEditors;

import java.util.List;

import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.LabelingOrder;
import com.variamos.hlcl.NumericExpression;

public class PrologTransformParameters {
	private String labelId;
	private List<Identifier> identifiers;
	private boolean fdLabeling;

	// First fail. Label the leftmost variable with smallest domain next, in
	// order to detect infeasibility early. This is often a good strategy.
	private boolean ff;
	// The solutions order is influenced by min or max expressions
	private boolean order;

	private List<LabelingOrder> labelingOrder;
	private List<NumericExpression> orderExpressions;

	public PrologTransformParameters() {
		fdLabeling = true;
	}

	public boolean isFdLabeling() {
		return fdLabeling;
	}

	public void setFdLabeling(boolean fdLabeling) {
		this.fdLabeling = fdLabeling;
	}

	public boolean isOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}

	public List<NumericExpression> getOrderExpressions() {
		return orderExpressions;
	}

	public void setOrderExpressions(List<NumericExpression> orderExpressions) {
		this.orderExpressions = orderExpressions;
	}

	public boolean isFf() {
		return ff;
	}

	public void setFf(boolean ff) {
		this.ff = ff;
	}

	public List<LabelingOrder> getLabelingOrder() {
		return labelingOrder;
	}

	public void setLabelingOrder(List<LabelingOrder> labelingOrder) {
		this.labelingOrder = labelingOrder;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public List<Identifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(List<Identifier> identifiers) {
		this.identifiers = identifiers;

	}

}
