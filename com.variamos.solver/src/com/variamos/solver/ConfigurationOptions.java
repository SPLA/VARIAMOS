package com.variamos.solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Labeling;
import com.variamos.hlcl.LabelingOrder;
import com.variamos.hlcl.NumericExpression;

public class ConfigurationOptions {
	private ConfigurationMode mode;
	private List<String> additionalConstraints;

	private List<BooleanExpression> additionalConstraintExpressions;
	// First fail. Label the leftmost variable with smallest domain next, in
	// order to detect infeasibility early. This is often a good strategy.
	private boolean ff;
	// The solutions order is influenced by min or max expressions
	private boolean order;

	// jcmunoz: new multi-labeling support
	private List<Labeling> labelings;
	// Mandatory list when order is necessary
	private List<LabelingOrder> labelingOrder;
	private List<NumericExpression> orderExpressions;

	// Sirve para saber cuando cerrar la conexión anterior para abrir una nueva
	public boolean startFromZero;

	public String programName;

	// Sirve para cargar directamente un archivo que ya existe en prolog
	public String programPath;

	public ConfigurationOptions() {
		mode = ConfigurationMode.FULL;
		ff = Boolean.TRUE;
		order = Boolean.FALSE;
		additionalConstraints = new ArrayList<>();
		additionalConstraintExpressions = new ArrayList<BooleanExpression>();
		startFromZero = true;
	}

	public ConfigurationMode getMode() {
		return mode;
	}

	public void setMode(ConfigurationMode mode) {
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

	public List<BooleanExpression> getAdditionalConstraintExpressions() {
		return additionalConstraintExpressions;
	}

	public void setAdditionalConstraintExpressions(
			List<BooleanExpression> additionalConstraintExpressions) {
		this.additionalConstraintExpressions = additionalConstraintExpressions;
	}

	public boolean addAdditionalExpression(BooleanExpression e) {
		return additionalConstraintExpressions.add(e);
	}

	public void addAdditionalExpression(int index, BooleanExpression element) {
		additionalConstraintExpressions.add(index, element);
	}

	public boolean addAllAdditionalExpression(
			Collection<? extends BooleanExpression> c) {
		return additionalConstraintExpressions.addAll(c);
	}

	public boolean addAllAdditionalExpression(int index,
			Collection<? extends BooleanExpression> c) {
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

	public List<LabelingOrder> getLabelingOrder() {
		return labelingOrder;
	}

	public void setLabelingOrder(List<LabelingOrder> labelingOrder) {
		this.labelingOrder = labelingOrder;
	}

	public List<NumericExpression> getOrderExpressions() {
		return orderExpressions;
	}

	public void setOrderExpressions(List<NumericExpression> orderExpressions) {
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
