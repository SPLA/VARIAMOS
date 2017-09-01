package com.variamos.defectAnalyzer.model;

import java.util.ArrayList;
import java.util.List;

import com.cfm.hlcl.RangeDomain;

public class RangeDomainDefectAnalyzer extends RangeDomain implements
		DefectAnalyzerDomain {

	public RangeDomainDefectAnalyzer() {
		super();
		
	}

	public RangeDomainDefectAnalyzer(int lowerValue, int upperValue) {
		super(lowerValue, upperValue);
	
	}

	private List<Integer> allowedValues = new ArrayList<Integer>();

	public List<Integer> getDomainValues() {
		if (allowedValues.isEmpty()) {
			for (int i = lowerValue; i <= upperValue; i++) {
				allowedValues.add(i);
			}
		}
		return allowedValues;
	}

	
}
