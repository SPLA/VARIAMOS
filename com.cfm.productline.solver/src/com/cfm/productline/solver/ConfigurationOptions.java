package com.cfm.productline.solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cfm.hlcl.BooleanExpression;

public class ConfigurationOptions {
	private ConfigurationMode mode;
	private List<String> additionalConstraints;

	private List<BooleanExpression> additionalConstraintExpressions;
	

	

	//En GNU prolog sirve para saber cuando cerrar la conexión anterior para abrir una nueva
	public boolean startFromZero;
	
	public ConfigurationOptions(){
		mode = ConfigurationMode.FULL;
		additionalConstraints = new ArrayList<>();
		additionalConstraintExpressions= new ArrayList<BooleanExpression>();
		startFromZero = true;
	}

	public ConfigurationMode getMode() {
		return mode;
	}

	public void setMode(ConfigurationMode mode) {
		this.mode = mode;
	}

	public List<String> getAdditionalConstraints() {
		return additionalConstraints;
	}

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

	public boolean addAllAdditionalExpression(Collection<? extends BooleanExpression> c) {
		return additionalConstraintExpressions.addAll(c);
	}

	public boolean addAllAdditionalExpression(int index, Collection<? extends BooleanExpression> c) {
		return additionalConstraintExpressions.addAll(index, c);
	}
}
