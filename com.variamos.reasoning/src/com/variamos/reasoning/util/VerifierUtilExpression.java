package com.variamos.reasoning.util;

import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.NumericIdentifier;
import com.variamos.reasoning.defectAnalyzer.constants.TransformerConstants;
import com.variamos.reasoning.defectAnalyzer.model.VariabilityElementDefAna;

public class VerifierUtilExpression {

	private static final HlclFactory f = new HlclFactory();



	public static IntBooleanExpression verifyAssignValueToVariabilityElementExpression(
			Identifier element, int valueToVerify) {
		NumericIdentifier nonValue = f.number(valueToVerify);
		IntBooleanExpression numericExpression = f.equals(element, nonValue);
		return numericExpression;
	}
	
	public static IntBooleanExpression verifyFalseOptionalExpression(
			Identifier element) {

		// VariabilityElement = 0
		NumericIdentifier nonValue = f
				.number(TransformerConstants.NON_SELECTED_VALUE);
		IntBooleanExpression numericExpression = f.equals(element, nonValue);
		return numericExpression;
	}


	public static IntBooleanExpression verifyFalseOptionalExpression(
			VariabilityElementDefAna variabilityElementDefAna) {

		// VariabilityElement = 0
		Identifier element = f
				.newIdentifier(variabilityElementDefAna.getName());
		NumericIdentifier nonValue = f
				.number(TransformerConstants.NON_SELECTED_VALUE);
		IntBooleanExpression numericExpression = f.equals(element, nonValue);
		return numericExpression;
	}

}
