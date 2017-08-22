package com.variamos.hlcl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RangeDomain implements Domain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5038777141268738133L;
	protected float lowerValue;
	protected float upperValue;
	protected int precision;

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public void setLowerValue(float lowerValue) {
		this.lowerValue = lowerValue;
	}

	public void setUpperValue(float upperValue) {
		this.upperValue = upperValue;
	}

	public RangeDomain(float lowerValue, float upperValue, int precision) {
		super();
		this.lowerValue = lowerValue;
		this.upperValue = upperValue;
		this.precision = precision;
	}

	public RangeDomain() {
		super();
		lowerValue = 0;
		upperValue = 1;
	}

	/**
	 * @return the lowValue
	 */
	public float getLowerValue() {
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
	public float getUpperValue() {
		return upperValue;
	}

	/**
	 * @param upperValue
	 *            the upperValue to set
	 */
	public void setUpperValue(int upperValue) {
		this.upperValue = upperValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		float result = 1;
		result = prime * result + lowerValue;
		result = prime * result + upperValue;
		return (int) (result * 1000);
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
		RangeDomain other = (RangeDomain) obj;
		if (lowerValue != other.lowerValue)
			return false;
		if (upperValue != other.upperValue)
			return false;
		return true;
	}

	@Override
	public int size() {
		return (int) ((upperValue - lowerValue) * Math.pow(10, precision) + 1);
	}

	@Override
	public String getStringRepresentation() {
		if (precision == 0)
			return (int) lowerValue + ".." + (int) upperValue;

		return lowerValue + ".." + upperValue;
	}

	@Override
	public List<Integer> getPossibleValues() {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < size(); i++)
			list.add((int) lowerValue + i);

		return list;
	}

	@Override
	public List<Float> getPossibleFloatValues() {
		List<Float> list = new ArrayList<Float>();

		// FIXME use the precision attribute
		for (int i = 0; i < size(); i++)
			list.add(new Float(lowerValue + i));

		return list;
	}

	@Override
	public List<String> getPossibleStringValues() {
		List<String> list = new ArrayList<>();

		for (int i = 0; i < size(); i++)
			list.add(String.valueOf(lowerValue + i));

		return list;
	}

}
