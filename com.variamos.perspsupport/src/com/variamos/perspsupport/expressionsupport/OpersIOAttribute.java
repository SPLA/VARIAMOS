package com.variamos.perspsupport.expressionsupport;

public class OpersIOAttribute implements Comparable {

	private String conceptId;
	private String attributeId;
	private boolean include;

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
	public int compareTo(Object o) {
		return ((OpersIOAttribute) o).getId().compareTo(this.getId());

	}
}
