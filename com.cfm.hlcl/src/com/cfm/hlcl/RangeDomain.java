package com.cfm.hlcl;

import java.util.ArrayList;
import java.util.List;


public class RangeDomain implements Domain {

	protected int lowerValue;
	protected int upperValue;

	public RangeDomain(int lowerValue, int upperValue) {
		super();
		this.lowerValue = lowerValue;
		this.upperValue = upperValue;
	}

	public RangeDomain() {
		super();
		lowerValue = 0;
		upperValue = 1;
	}

	/**
	 * @return the lowValue
	 */
	public int getLowerValue() {
		return lowerValue;
	}

	/**
	 * @param lowValue
	 *            the lowValue to set
	 */
	public void setLowerValue(int lowerValue) {
		this.lowerValue = lowerValue;
	}

	/**
	 * @return the upperValue
	 */
	public int getUpperValue() {
		return upperValue;
	}

	/**
	 * @param upperValue
	 *            the upperValue to set
	 */
	public void setUpperValue(int upperValue) {
		this.upperValue = upperValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lowerValue;
		result = prime * result + upperValue;
		return result;
	}

	/* (non-Javadoc)
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
		RangeDomain other = (RangeDomain) obj;
		if (lowerValue != other.lowerValue)
			return false;
		if (upperValue != other.upperValue)
			return false;
		return true;
	}

	@Override
	public int size() {
		return upperValue - lowerValue + 1;
	}

	@Override
	public String getStringRepresentation() {
		return lowerValue + " - " + upperValue;
	}

	@Override
	public List<Integer> getPossibleValues() {
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < size(); i++)
			list.add(lowerValue + i);
		
		return list;
	}
	
	
}
