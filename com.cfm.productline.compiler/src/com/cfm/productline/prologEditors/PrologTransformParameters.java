package com.cfm.productline.prologEditors;


public class PrologTransformParameters {
	private boolean fdLabeling;
	
	
	public PrologTransformParameters(){
		fdLabeling = true;
	}
	
	public boolean isFdLabeling() {
		return fdLabeling;
	}

	public void setFdLabeling(boolean fdLabeling) {
		this.fdLabeling = fdLabeling;
	}
	
	
}
