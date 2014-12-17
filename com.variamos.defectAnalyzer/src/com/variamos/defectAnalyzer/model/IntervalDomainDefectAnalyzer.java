package com.variamos.defectAnalyzer.model;

import java.util.List;

import com.cfm.hlcl.IntervalDomain;

public class IntervalDomainDefectAnalyzer extends IntervalDomain implements
		DefectAnalyzerDomain {

	public List<Integer> getDomainValues() {

		return rangeValues;
	}

}
