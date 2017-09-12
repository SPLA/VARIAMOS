package com.variamos.reasoning.defectAnalyzer.model.transformation;

import java.util.List;

import com.variamos.hlcl.model.domains.IntDomain;

public interface IntDefectAnalyzerDomain extends IntDomain {

	public List<Integer> getDomainValues();
	
	
}
