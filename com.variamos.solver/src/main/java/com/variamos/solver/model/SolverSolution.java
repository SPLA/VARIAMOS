package com.variamos.solver.model;

import java.util.Set;
import java.util.TreeMap;

/**
 * This class represents a solution given for a  solver which is composed by a set of variables
 * which are saved as String. Each variable could have two possible values 1 for enforced or 0 for banned.
 * Also if a variable should be ignored, then this variable should take a value of IGNORED. 
 * @author David Lopez
 * @author Luisa Rincon <lufe089@gmail.com@>
 * @version 1.3
 * 
 *          It was eliminated imports and it was added some documentation
 */
public class SolverSolution {

	public static final int IGNORED = -1, ENFORCED = 1, BANNED = 0;

	protected TreeMap<String, Number> configuration;

	public SolverSolution() {
		configuration = new TreeMap<String, Number>();
	}

	public SolverSolution set(String id, Number value) {
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
	public SolverSolution enforce(String id) {
		configuration.put(id, ENFORCED);
		return this;
	}

	/**
	 * @param id
	 *            : name of variabilityElement
	 * @return Assign 1 to variabilityElement
	 */
	public SolverSolution ban(String id) {
		configuration.put(id, BANNED);
		return this;
	}

	public SolverSolution ignore(String id) {
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

	

	@Override
	public boolean equals(Object obj) {

		if (obj == null && this.configuration.size() == 0)
			return true;

		if (obj instanceof SolverSolution) {
			SolverSolution other = (SolverSolution) obj;

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

	public TreeMap<String, Number> getSolverSolution() {
		return configuration;
	}

	public void setSolverSolution(TreeMap<String, Number> configuration) {
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
