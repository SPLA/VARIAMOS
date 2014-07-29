package com.cfm.productline.model.defectAnalyzerModel;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class VMConfiguration {

	public static final int IGNORED = -1, ENFORCED = 1, BANNED = 0;

	protected Map<String, Integer> configuration;

	public VMConfiguration() {
		configuration = new TreeMap<String, Integer>();
	}

	/**
	 * @param id: Name of the variabilityElement
	 * @return Assign 1 to variabilityElement
	 */
	public VMConfiguration enforce(String id) {
		configuration.put(id, ENFORCED);
		return this;
	}

	public VMConfiguration ban(String id) {
		configuration.put(id, BANNED);
		return this;
	}

	public VMConfiguration ignore(String id) {
		configuration.remove(id);
		return this;
	}

	public VMConfiguration assing(String id, Integer value) {
		configuration.put(id, value);
		return this;
	}

	public Set<String> getNotIgnored() {
		return configuration.keySet();
	}

	public int stateOf(String id) {
		if (!configuration.containsKey(id))
			return IGNORED;

		return configuration.get(id);
	}

	/**
	 * @return Assignation of values for each Variability Element of the VariabilityModel
	 */
	public Map<String, Integer> getProductRepresentation() {

		if (configuration != null) {
			return configuration;
		} else {
			return new TreeMap<String, Integer>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		for (String key : configuration.keySet()) {
			text.append(key);
			text.append(": ");
			text.append(configuration.get(key));
			text.append(" ");
		}
		return text.toString();
	}

	
	public void printConfiguration(){
		 System.out.println(this.toString());
	}
}
