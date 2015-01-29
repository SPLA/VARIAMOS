package com.variamos.defectAnalyzer.util;

import com.variamos.defectAnalyzer.constants.TransformerConstants;
import com.variamos.defectAnalyzer.model.VariabilityElementDefAna;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.NumericIdentifier;

public class VerifierUtilExpression {

	private static final HlclFactory f = new HlclFactory();



	public static BooleanExpression verifyAssignValueToVariabilityElementExpression(
			Identifier element, int valueToVerify) {
		NumericIdentifier nonValue = f.number(valueToVerify);
		BooleanExpression numericExpression = f.equals(element, nonValue);
		return numericExpression;
	}
	
	public static BooleanExpression verifyFalseOptionalExpression(
			Identifier element) {

		// VariabilityElement = 0
		NumericIdentifier nonValue = f
				.number(TransformerConstants.NON_SELECTED_VALUE);
		BooleanExpression numericExpression = f.equals(element, nonValue);
		return numericExpression;
	}


	public static BooleanExpression verifyFalseOptionalExpression(
			VariabilityElementDefAna variabilityElementDefAna) {

		// VariabilityElement = 0
		Identifier element = f
				.newIdentifier(variabilityElementDefAna.getName());
		NumericIdentifier nonValue = f
				.number(TransformerConstants.NON_SELECTED_VALUE);
		BooleanExpression numericExpression = f.equals(element, nonValue);
		return numericExpression;
	}

}
