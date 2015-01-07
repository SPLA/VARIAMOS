package com.variamos.defectAnalyzer.model;

import com.cfm.hlcl.HlclProgram;

public class Correction extends CauCos {
	private static Long indx = 1L;

	public Correction(HlclProgram elements) {
		super(elements);
		indx++;
		id = indx;
	}

	
	public int getCorrectionSize(){
		return elements.size();
	}
}
