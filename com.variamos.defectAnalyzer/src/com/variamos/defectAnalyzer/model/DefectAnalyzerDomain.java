package com.variamos.defectAnalyzer.model;

import java.util.List;

import com.cfm.hlcl.Domain;

public interface DefectAnalyzerDomain extends Domain {

	public List<Integer> getDomainValues();
	
	
}
