package com.variamos.solver;

import java.util.Set;
import java.util.TreeMap;

/**
 * @author David Lopez
 * @author Luisa Rincon <lufe089@gmail.com@>
 * @version 1.2
 * 
 *          It was eliminated imports and it was added some documentation
 */
public class Configuration {

	public static final int IGNORED = -1, ENFORCED = 1, BANNED = 0;

	protected TreeMap<String, Number> configuration;

	public Configuration() {
		configuration = new TreeMap<String, Number>();
	}

	public Configuration set(String id, Number value) {
		if (value == null)
			ignore(id);
		else
			configuration.put(id, value);
		return this;
	}

	/**
	 * @param id
	 *            : name of variabilityElement
	 * @return Assign 1 to variabilityElement
	 */
	public Configuration enforce(String id) {
		configuration.put(id, ENFORCED);
		return this;
	}

	/**
	 * @param id
	 *            : name of variabilityElement
	 * @return Assign 1 to variabilityElement
	 */
	public Configuration ban(String id) {
		configuration.put(id, BANNED);
		return this;
	}

	public Configuration ignore(String id) {
		configuration.remove(id);
		return this;
	}

	public Set<String> getNotIgnored() {
		return configuration.keySet();
	}

	public float stateOf(String id) {
		if (!configuration.containsKey(id))
			return IGNORED;

		return configuration.get(id).floatValue();
	}

	public void debugPrint() {
		for (String key : configuration.keySet()) {
			System.out.println(key + " : " + configuration.get(key));
		}
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null && this.configuration.size() == 0)
			return true;

		if (obj instanceof Configuration) {
			Configuration other = (Configuration) obj;

			Set<String> mine = getNotIgnored();
			if (mine.size() != other.getNotIgnored().size())
				return false;

			for (String id : other.getNotIgnored()) {
				if (stateOf(id) != other.stateOf(id))
					return false;
			}

			return true;
		}
		return super.equals(obj);
	}

	public TreeMap<String, Number> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(TreeMap<String, Number> configuration) {
		this.configuration = configuration;
	}

	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		if (configuration != null) {

			for (String key : configuration.keySet()) {
				text.append(key);
				text.append(": ");
				text.append(configuration.get(key));
				text.append(" ");
			}
		} else {
			text.append("null object");
		}
		return text.toString();
	}

}
