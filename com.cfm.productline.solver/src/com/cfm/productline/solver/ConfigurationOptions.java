package com.cfm.productline.solver;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationOptions {
	public ConfigurationMode mode;
	public List<String> additionalConstraints;
	public boolean startFromZero;
	
	public ConfigurationOptions(){
		mode = ConfigurationMode.FULL;
		additionalConstraints = new ArrayList<>();
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
	
}
