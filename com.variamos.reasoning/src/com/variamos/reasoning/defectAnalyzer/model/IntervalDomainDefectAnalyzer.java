package com.variamos.reasoning.defectAnalyzer.model;

import java.util.List;

import com.variamos.hlcl.IntervalDomain;

public class IntervalDomainDefectAnalyzer extends IntervalDomain implements
		DefectAnalyzerDomain {

	public List<Integer> getDomainValues() {

		return rangeValues;
	}

}
