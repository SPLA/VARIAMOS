package com.variamos.defectAnalyzer.util;

import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericIdentifier;
import com.variamos.defectAnalyzer.constants.TransformerConstants;
import com.variamos.defectAnalyzer.model.VariabilityElement;

public class VerifierUtilExpression {

	private static final HlclFactory f = new HlclFactory();

	public static Expression verifyAssignValueToVariabilityElementExpression(
			VariabilityElement variabilityElement, int valueToVerify) {

		// VariabilityElement = valueToVerify
		Identifier element = f.newIdentifier(variabilityElement.getName());
		NumericIdentifier nonValue = f.number(valueToVerify);
		Expression numericExpression = f.equals(element, nonValue);
		return numericExpression;
	}
	
	
	public static Expression verifyFalseOptionalExpression(
			VariabilityElement variabilityElement) {

		// VariabilityElement = 0
		Identifier element = f.newIdentifier(variabilityElement.getName());
		NumericIdentifier nonValue = f.number(TransformerConstants.NON_SELECTED_VALUE);
		Expression numericExpression = f.equals(element, nonValue);
		return numericExpression;
	}

}
