package com.cfm.productline.configurator.io;

import com.cfm.productline.solver.Configuration;
import com.cfm.productline.solver.ConfigurationOptions;

public class ConfigurationDTO {
	private ConfigurationOptions options;
	private Configuration values;
	
	public ConfigurationDTO() {
		options = new ConfigurationOptions();
		values = new Configuration();
	}
	
	public ConfigurationDTO(ConfigurationOptions options, Configuration values) {
		super();
		this.options = options;
		this.values = values;
	}


	public ConfigurationOptions getOptions() {
		return options;
	}
	public void setOptions(ConfigurationOptions options) {
		this.options = options;
	}
	public Configuration getValues() {
		return values;
	}
	public void setValues(Configuration values) {
		this.values = values;
	}
	
	
}
