package com.variamos.reasoning.defectAnalyzer.model.transformation;

import java.util.ArrayList;
import java.util.List;

import com.variamos.hlcl.model.domains.RangeDomain;

public class RangeDomainDefectAnalyzer extends RangeDomain implements
		IntDefectAnalyzerDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4600360579071747427L;

	public RangeDomainDefectAnalyzer() {
		super();

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
