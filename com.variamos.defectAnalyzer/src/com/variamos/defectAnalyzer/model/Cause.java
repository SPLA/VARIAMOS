package com.variamos.defectAnalyzer.model;

import com.cfm.hlcl.HlclProgram;

public class Cause extends CauCos {
	private static Long indx=1L;

	public Cause(HlclProgram elements) {
		super(elements);
		indx++;
		id = indx;
	}
	
	public int getCauseSize(){
		return elements.size();
	}
}
