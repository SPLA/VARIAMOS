package com.variamos.reasoning.defectAnalyzer.model;

import java.util.List;

import com.variamos.hlcl.model.domains.IntDomain;

public interface DefectAnalyzerDomain extends IntDomain {

	public List<Integer> getDomainValues();
	
	
}
