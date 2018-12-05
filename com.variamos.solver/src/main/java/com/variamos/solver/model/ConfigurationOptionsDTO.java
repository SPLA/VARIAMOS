package com.variamos.solver.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.variamos.hlcl.model.Labeling;
import com.variamos.hlcl.model.LabelingOrderEnum;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntNumericExpression;

public class ConfigurationOptionsDTO {
	private ConfigurationModeEnum mode;
	private List<String> additionalConstraints;

	private List<IntBooleanExpression> additionalConstraintExpressions;
	// First fail. Label the leftmost variable with smallest domain next, in
	// order to detect infeasibility early. This is often a good strategy.
	private boolean ff;
	// The solutions order is influenced by min or max expressions
	private boolean order;

	// jcmunoz: new multi-labeling support
	private List<Labeling> labelings;
	// Mandatory list when order is necessary
	private List<LabelingOrderEnum> labelingOrder;
	private List<IntNumericExpression> orderExpressions;

	// Sirve para saber cuando cerrar la conexión anterior para abrir una nueva
	public boolean startFromZero;

	public String programName;

	// Sirve para cargar directamente un archivo que ya existe en prolog
	public String programPath;

	public ConfigurationOptionsDTO() {
		mode = ConfigurationModeEnum.FULL;
		ff = Boolean.TRUE;
		order = Boolean.FALSE;
		additionalConstraints = new ArrayList<>();
		additionalConstraintExpressions = new ArrayList<IntBooleanExpression>();
		startFromZero = true;
	}

	public ConfigurationModeEnum getMode() {
		return mode;
	}

	public void setMode(ConfigurationModeEnum mode) {
		this.mode = mode;
	}

	@Deprecated
	public List<String> getAdditionalConstraints() {
		return additionalConstraints;
	}

	@Deprecated
	public void setAdditionalConstraints(List<String> additionalConstraints) {
		this.additionalConstraints = additionalConstraints;
	}

	public boolean isStartFromZero() {
		return startFromZero;
	}

	public void setStartFromZero(boolean startFromZero) {
		this.startFromZero = startFromZero;
	}

	public List<IntBooleanExpression> getAdditionalConstraintExpressions() {
		return additionalConstraintExpressions;
	}

	public void setAdditionalConstraintExpressions(
			List<IntBooleanExpression> additionalConstraintExpressions) {
		this.additionalConstraintExpressions = additionalConstraintExpressions;
	}

	public boolean addAdditionalExpression(IntBooleanExpression e) {
		return additionalConstraintExpressions.add(e);
	}

	public void addAdditionalExpression(int index, IntBooleanExpression element) {
		additionalConstraintExpressions.add(index, element);
	}

	public boolean addAllAdditionalExpression(
			Collection<? extends IntBooleanExpression> c) {
		return additionalConstraintExpressions.addAll(c);
	}

	public boolean addAllAdditionalExpression(int index,
			Collection<? extends IntBooleanExpression> c) {
		return additionalConstraintExpressions.addAll(index, c);
	}

	public boolean isFf() {
		return ff;
	}

	public void setFf(boolean ff) {
		this.ff = ff;
	}

	public boolean isOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}

	public List<LabelingOrderEnum> getLabelingOrder() {
		return labelingOrder;
	}

	public void setLabelingOrder(List<LabelingOrderEnum> labelingOrder) {
		this.labelingOrder = labelingOrder;
	}

	public List<IntNumericExpression> getOrderExpressions() {
		return orderExpressions;
	}

	public void setOrderExpressions(List<IntNumericExpression> orderExpressions) {
		this.orderExpressions = orderExpressions;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramPath() {
		return programPath;
	}

	public void setProgramPath(String programPath) {
		this.programPath = programPath;
	}

	public List<Labeling> getLabelings() {
		return labelings;
	}

	public void setLabelings(List<Labeling> labelings) {
		this.labelings = labelings;
	}
}
