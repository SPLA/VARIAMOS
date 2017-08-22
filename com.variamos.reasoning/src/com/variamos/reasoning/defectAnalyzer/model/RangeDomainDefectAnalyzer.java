package com.variamos.reasoning.defectAnalyzer.model;

import java.util.ArrayList;
import java.util.List;

import com.variamos.hlcl.RangeDomain;

public class RangeDomainDefectAnalyzer extends RangeDomain implements
		DefectAnalyzerDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4600360579071747427L;

	public RangeDomainDefectAnalyzer() {
		super();

	}

	public RangeDomainDefectAnalyzer(int lowerValue, int upperValue) {
		super(lowerValue, upperValue, 0);

	}

	private List<Integer> allowedValues = new ArrayList<Integer>();

	public List<Integer> getDomainValues() {
		if (allowedValues.isEmpty()) {
			for (float i = lowerValue; i <= upperValue; i++) {
				allowedValues.add((int) i);
			}
		}
		return allowedValues;
	}

}
