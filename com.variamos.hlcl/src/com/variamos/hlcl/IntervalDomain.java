package com.variamos.hlcl;

import java.util.ArrayList;
import java.util.List;

public class IntervalDomain implements Domain {

	protected List<Integer> rangeValues;

	public List<Integer> getDomainValues() {
		return rangeValues;
	}

	public IntervalDomain() {
		super();
		rangeValues = new ArrayList<Integer>();
	}

	/**
	 * @return the rangeValues
	 */
	public List<Integer> getRangeValues() {
		return rangeValues;
	}

	/**
	 * @param rangeValues
	 *            the rangeValues to set
	 */
	public void setRangeValues(List<Integer> rangeValues) {
		this.rangeValues = rangeValues;
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Integer e) {
		return rangeValues.add(e);
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.List#remove(int)
	 */
	public Integer remove(int index) {
		return rangeValues.remove(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((rangeValues == null) ? 0 : rangeValues.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntervalDomain other = (IntervalDomain) obj;
		if (rangeValues == null) {
			if (other.rangeValues != null)
				return false;
		} else if (!rangeValues.equals(other.rangeValues))
			return false;
		return true;
	}

	@Override
	public int size() {
		return rangeValues.size();
	}

	@Override
	public String getStringRepresentation() {
		StringBuffer str = new StringBuffer();

		for (int i = 0; i < rangeValues.size(); i++) {
			str.append(rangeValues.get(i));
			if (i < rangeValues.size() - 1)
				str.append(", ");
		}

		return str.toString();
	}

	@Override
	public List<Integer> getPossibleValues() {
		List<Integer> vals = new ArrayList<Integer>();
		// Do I have to check if they are actually Integers ?
		// Srsly Java?
		for (Object obj : rangeValues) {
			if (obj instanceof Integer) {
				vals.add((Integer) obj);
				continue;
			}
			if (obj instanceof String) {
				vals.add(Integer.parseInt((String) obj));
				continue;
			}
		}
		return vals;
	}

	@Override
	public List<String> getPossibleStringValues() {
		List<String> vals = new ArrayList<String>();
		// Do I have to check if they are actually Integers ?
		// Srsly Java?
		for (Object obj : rangeValues) {
			if (obj instanceof Integer) {
				vals.add(((Integer) obj).toString());
				continue;
			}
			if (obj instanceof String) {
				vals.add((String) obj);
				continue;
			}
		}
		return vals;
	}
}
