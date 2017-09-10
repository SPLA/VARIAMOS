package com.variamos.hlcl.model.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to support an String domain. Initially copied from IntervalDomain.
 * Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.0
 * @since 2015-11-10
 * @see com.variamos.hlcl.model.domains.IntervalDomain
 */
public class StringDomain implements IntDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4268925453726727704L;
	protected List<String> stringValues;

	public List<String> getDomainValues() {
		return stringValues;
	}

	public StringDomain() {
		super();
		stringValues = new ArrayList<String>();
	}

	/**
	 * @return the stringValues
	 */
	public List<String> getStringValues() {
		return stringValues;
	}

	/**
	 * @param stringValues
	 *            the stringValues to set
	 */
	public void setStringValues(List<String> stringValues) {
		this.stringValues = stringValues;
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(String e) {
		return stringValues.add(e);
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.List#remove(int)
	 */
	public String remove(int index) {
		return stringValues.remove(index);
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
				+ ((stringValues == null) ? 0 : stringValues.hashCode());
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
		StringDomain other = (StringDomain) obj;
		if (stringValues == null) {
			if (other.stringValues != null)
				return false;
		} else if (!stringValues.equals(other.stringValues))
			return false;
		return true;
	}

	@Override
	public int size() {
		return stringValues.size();
	}

	@Override
	public String getStringRepresentation() {
		StringBuffer str = new StringBuffer();

		for (int i = 0; i < stringValues.size(); i++) {
			str.append(stringValues.get(i));
			if (i < stringValues.size() - 1)
				str.append(", ");
		}

		return str.toString();
	}

	public List<String> getPossibleStringValues() {
		List<String> vals = new ArrayList<String>();
		// Do I have to check if they are actually Integers ?
		// Srsly Java?
		for (String obj : stringValues) {
			vals.add(obj);
			continue;
		}
		return vals;
	}

	@Override
	public List<Integer> getPossibleValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Float> getPossibleFloatValues() {
		// TODO Auto-generated method stub
		return null;
	}
}
