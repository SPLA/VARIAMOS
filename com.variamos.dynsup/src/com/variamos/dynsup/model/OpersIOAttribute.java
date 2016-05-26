package com.variamos.dynsup.model;

import java.io.Serializable;

public class OpersIOAttribute implements Comparable<OpersIOAttribute>,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7692216138211081188L;
	private String conceptId;
	private String attributeId;
	private boolean include; // Include or Exclude the attribute

	public OpersIOAttribute() {
		super();
	}

	public OpersIOAttribute(String conceptId, String attributeId,
			boolean include) {
		super();
		this.conceptId = conceptId;
		this.attributeId = attributeId;
		this.include = include;
	}

	public String getConceptId() {
		return conceptId;
	}

	public void setConceptId(String conceptId) {
		this.conceptId = conceptId;
	}

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public boolean isInclude() {
		return include;
	}

	public void setInclude(boolean include) {
		this.include = include;
	}

	public String getId() {
		return conceptId + "#" + attributeId + "#" + include;
	}

	@Override
	public int compareTo(OpersIOAttribute o) {
		return o.getId().compareTo(this.getId());

	}
}
