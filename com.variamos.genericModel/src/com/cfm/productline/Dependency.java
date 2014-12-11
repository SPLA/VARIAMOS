package com.cfm.productline;

public class Dependency {

	private Constraint constraint;
	//Optional field. This field has relation with transformation process
	private Long id;
	public Constraint getConstraint() {
		return constraint;
	}
	public void setConstraint(Constraint constraint) {
		this.constraint = constraint;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
