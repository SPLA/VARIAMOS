package com.variamos.defectAnalyzer.model;

import com.cfm.hlcl.HlclProgram;

public class CauCos {

	protected HlclProgram elements;
	
	protected Long id;
	
	public CauCos() {
		super();
		
	}

	public CauCos(HlclProgram elements) {
		super();
		this.elements = elements;
		
	}



	public HlclProgram getElements() {
		return elements;
	}

	public void setElements(HlclProgram elements) {
		this.elements = elements;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
