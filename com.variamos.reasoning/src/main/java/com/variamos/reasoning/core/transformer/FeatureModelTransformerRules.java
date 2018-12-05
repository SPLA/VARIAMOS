package com.variamos.reasoning.core.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.hlcl.model.domains.IntDomain;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntNumericExpression;
import com.variamos.hlcl.model.expressions.NumericIdentifier;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.VariabilityElementDefAna;
import com.variamos.reasoning.defectAnalyzer.model.transformation.TransformerConstants;

import constraints.BooleanVariable;

public class FeatureModelTransformerRules {

	private static HlclFactory f = null;
	private static Map<String, Identifier> idMap = new HashMap<>();

	public FeatureModelTransformerRules() {
		super();
		f = new HlclFactory();
		idMap = new HashMap<>();
	}

	public IntBooleanExpression getMandatoryRule(VariabilityElementDefAna element1,
			VariabilityElementDefAna element2) {
		// A <=> B
		IntBooleanExpression numericExpression = f.doubleImplies(
				createIdentifier(element1), createIdentifier(element2));
		return numericExpression;
	}

	/**
	 * Mï¿½todo para negar una restricciï¿½n mandatory
	 * 
	 * @param element1
	 * @param element2
	 * @return
	 */
	public IntBooleanExpression getNegationMandatoryRule(VariabilityElementDefAna element1,
			VariabilityElementDefAna element2) {
		// A * (1-B) + B * (1- A) > 0
		Identifier A = createIdentifier(element1);
		Identifier B = createIdentifier(element2);
		NumericIdentifier one = f.number(1);
		NumericIdentifier zero = f.number(TransformerConstants.ZERO);

		// (1- B)
		IntNumericExpression substractionB = f.diff(one, B);
		// (1- A)
		IntNumericExpression substractionA = f.diff(one, A);
		// A * (1-B)
		IntNumericExpression multiplicationA = f.prod(A, substractionB);
		// B * (1- A)
		IntNumericExpression multiplicationB = f.prod(B, substractionA);
		// A * (1-B) + B * (1- A)
		IntNumericExpression sum = f.sum(multiplicationA, multiplicationB);
		// A * (1-B) + B * (1- A)> 0
		IntBooleanExpression completeNegationExpression = f.greaterThan(sum, zero);

		return completeNegationExpression;

	}

	public IntBooleanExpression getOptionalRule(VariabilityElementDefAna element1,
			VariabilityElementDefAna element2) {

		// A >= B
		IntBooleanExpression numericExpression = f.greaterOrEqualsThan(
				createIdentifier(element1), createIdentifier(element2));

		return numericExpression;
	}

	public IntBooleanExpression getNegationOptionalRule(VariabilityElementDefAna element1,
			VariabilityElementDefAna element2) {

		// A < B
		IntBooleanExpression numericExpression = f.lessThan(createIdentifier(element1),
				createIdentifier(element2));

		return numericExpression;
	}

	public IntBooleanExpression getAssignRule(int valueToAssing,
			VariabilityElementDefAna element1) {

		// Root =1
		NumericIdentifier numericIdentifier = f.number(valueToAssing);
		IntBooleanExpression numericExpression = f.equals(createIdentifier(element1),
				numericIdentifier);
		return numericExpression;
	}

	public IntBooleanExpression getGroupalDependencyRule1(VariabilityElementDefAna element1,
			List<VariabilityElementDefAna> elements, int lowerCardinality) {
		// m * P <= SUM features
		// element1=parent feature
		List<IntNumericExpression> identifiers = new ArrayList<IntNumericExpression>();
		Identifier parentIdentifier = createIdentifier(element1);
		NumericIdentifier lowerCardinalityIdentifier = f
				.number(lowerCardinality);
		for (VariabilityElementDefAna element : elements) {
			identifiers.add(createIdentifier(element));
		}
		IntNumericExpression sumNumericExpression = f.sum(identifiers);
		IntNumericExpression multiplyExpression = f.prod(
				lowerCardinalityIdentifier, parentIdentifier);
		IntBooleanExpression ruleExpression = f.lessOrEqualsThan(multiplyExpression,
				sumNumericExpression);

		return ruleExpression;
	}
	
	
	public IntBooleanExpression getNegationGroupalDependencyRule1(VariabilityElementDefAna element1,
			List<VariabilityElementDefAna> elements, int lowerCardinality) {
		// m * P > SUM features
		// element1=parent feature
		List<IntNumericExpression> identifiers = new ArrayList<IntNumericExpression>();
		Identifier parentIdentifier = createIdentifier(element1);
		NumericIdentifier lowerCardinalityIdentifier = f
				.number(lowerCardinality);
		for (VariabilityElementDefAna element : elements) {
			identifiers.add(createIdentifier(element));
		}
		IntNumericExpression sumNumericExpression = f.sum(identifiers);
		IntNumericExpression multiplyExpression = f.prod(
				lowerCardinalityIdentifier, parentIdentifier);
		IntBooleanExpression ruleExpression = f.greaterThan(multiplyExpression,
				sumNumericExpression);

		return ruleExpression;
	}
	

	public IntBooleanExpression getGroupalDependencyRule2(VariabilityElementDefAna element1,
			List<VariabilityElementDefAna> elements, int upperCardinality) {
		// // SUM features <= n * P
		List<IntNumericExpression> identifiers = new ArrayList<IntNumericExpression>();
		Identifier parentIdentifier = createIdentifier(element1);
		NumericIdentifier upperCardinalityIdentifier = f
				.number(upperCardinality);
		for (VariabilityElementDefAna element : elements) {
			identifiers.add(createIdentifier(element));
		}
		IntNumericExpression sumNumericExpression = f.sum(identifiers);
		IntNumericExpression multiplyExpression = f.prod(
				upperCardinalityIdentifier, parentIdentifier);
		IntBooleanExpression ruleExpression = f.lessOrEqualsThan(sumNumericExpression,
				multiplyExpression);
		return ruleExpression;
	}
	
	public IntBooleanExpression getNegationGroupalDependencyRule2(VariabilityElementDefAna element1,
			List<VariabilityElementDefAna> elements, int upperCardinality) {
		// // SUM features > n * P
		List<IntNumericExpression> identifiers = new ArrayList<IntNumericExpression>();
		Identifier parentIdentifier = createIdentifier(element1);
		NumericIdentifier upperCardinalityIdentifier = f
				.number(upperCardinality);
		for (VariabilityElementDefAna element : elements) {
			identifiers.add(createIdentifier(element));
		}
		IntNumericExpression sumNumericExpression = f.sum(identifiers);
		IntNumericExpression multiplyExpression = f.prod(
				upperCardinalityIdentifier, parentIdentifier);
		IntBooleanExpression ruleExpression = f.greaterThan(sumNumericExpression,
				multiplyExpression);
		return ruleExpression;
	}

	public IntBooleanExpression getGroupalDependencyRule3(VariabilityElementDefAna element1,
			List<VariabilityElementDefAna> elements) {
		// P #= SUM features
		List<IntNumericExpression> identifiers = new ArrayList<IntNumericExpression>();
		Identifier parentIdentifier = createIdentifier(element1);
		for (VariabilityElementDefAna element : elements) {
			identifiers.add(createIdentifier(element));
		}
		IntNumericExpression sumNumericExpression = f.sum(identifiers);
		IntBooleanExpression assignExpresion = f.equals(parentIdentifier,
				sumNumericExpression);
		return assignExpresion;
	}
	
	public IntBooleanExpression getNegationGroupalDependencyRule3(VariabilityElementDefAna element1,
			List<VariabilityElementDefAna> elements) {
		// P != SUM features
		List<IntNumericExpression> identifiers = new ArrayList<IntNumericExpression>();
		Identifier parentIdentifier = createIdentifier(element1);
		for (VariabilityElementDefAna element : elements) {
			identifiers.add(createIdentifier(element));
		}
		IntNumericExpression sumNumericExpression = f.sum(identifiers);
		IntBooleanExpression assignExpresion = f.notEquals(parentIdentifier,
				sumNumericExpression);
		return assignExpresion;
	}

	public IntNumericExpression getIdentifiersOfExpression(
			BooleanVariable element, VariabilityElementDefAna variabilityElementDefAna) {
		Identifier elementIdentifier = null;
		if (element.isPositive()) {
			elementIdentifier = createIdentifier(variabilityElementDefAna);
			return elementIdentifier;
		} else {
			// (1- element)
			NumericIdentifier one = f.number(1);
			elementIdentifier = createIdentifier(variabilityElementDefAna);
			IntNumericExpression expression = f.diff(one, elementIdentifier);
			return expression;
		}

	}

	public IntBooleanExpression getPropositionalConstraintsRule(
			List<IntNumericExpression> expressionList) {
		// prop1 + prop2 +--- #>0" EJM (1 - F2) + (1 - F8) #> 0,
		NumericIdentifier zero = f.number(TransformerConstants.ZERO);
		IntNumericExpression sumNumericExpression = f.sum(expressionList);
		IntBooleanExpression ruleExpression = f.greaterThan(sumNumericExpression, zero);
		return ruleExpression;
	}
	
	public IntBooleanExpression getNegationPropositionalConstraintsRule(
			List<IntNumericExpression> expressionList) {
		// prop1 + prop2 +--- #>0" EJM (1 - F2) + (1 - F8) #<= 0,
		NumericIdentifier zero = f.number(TransformerConstants.ZERO);
		IntNumericExpression sumNumericExpression = f.sum(expressionList);
		IntBooleanExpression ruleExpression = f.lessOrEqualsThan(sumNumericExpression, zero);
		return ruleExpression;
	}


	public String getGroupalDependencyName3(VariabilityElementDefAna element1,
			String featureSet) {
		StringBuilder dependencyName = new StringBuilder();
		// Parent TO groupedFeatures`
		dependencyName.append(TransformerConstants.DEPENDENCY);
		dependencyName.append(TransformerConstants.GROUP +TransformerConstants.BETWEEN);
		dependencyName.append(element1.getName());
		dependencyName.append(TransformerConstants.TO);
		dependencyName.append(featureSet);
		return dependencyName.toString();
	}

	public String getGroupalDependencyName1(VariabilityElementDefAna element1,
			String featureSet) {
		// Parent TO groupedFeatures LowCardinality
		StringBuilder dependencyName = new StringBuilder(
				getGroupalDependencyName3(element1, featureSet));
		dependencyName.append(TransformerConstants.LOW_CARDINALITY);
		return dependencyName.toString();
	}

	public String getGroupalDependencyName2(VariabilityElementDefAna element1,
			String featureSet) {
		// Parent TO groupedFeatures UpperCardinality
		StringBuilder dependencyName = new StringBuilder(
				getGroupalDependencyName3(element1, featureSet));
		dependencyName.append(TransformerConstants.UPPER_CARDINALITY);
		return dependencyName.toString();
	}

	public String getPropositionalName(String featureSet) {
		StringBuilder dependencyName = new StringBuilder();
		// Dependency: F1-F2-F3
		dependencyName.append(TransformerConstants.DEPENDENCY);
		dependencyName.append(TransformerConstants.TRAVERSAL +TransformerConstants.BETWEEN);
		dependencyName.append(":");
		dependencyName.append(featureSet);
		return dependencyName.toString();
	}

	public String getRootDependencyName(VariabilityElementDefAna element1) {
		StringBuilder dependencyName = new StringBuilder();
		// Model root: feature1"
		dependencyName.append(TransformerConstants.MODEL_ROOT);
		dependencyName.append(element1.getName());
		return dependencyName.toString();
	}

	public String getMandatoryDependencyName(
			VariabilityElementDefAna element1, VariabilityElementDefAna element2) {
		StringBuilder dependencyName = new StringBuilder();
		// Mandatory Dependency between feature1 y feature2"
		dependencyName.append(TransformerConstants.DEPENDENCY);
		dependencyName.append(TransformerConstants.MANDATORY +TransformerConstants.BETWEEN);
		dependencyName.append(element1.getName());
		dependencyName.append(TransformerConstants.TO);
		dependencyName.append(element2.getName());
		return dependencyName.toString();
	}
	
	public String getOptionalDependencyName(
			VariabilityElementDefAna element1, VariabilityElementDefAna element2) {
		StringBuilder dependencyName = new StringBuilder();
		// Mandatory Dependency between feature1 y feature2"
		dependencyName.append(TransformerConstants.DEPENDENCY);
		dependencyName.append(TransformerConstants.OPTIONAL +TransformerConstants.BETWEEN);
		dependencyName.append(element1.getName());
		dependencyName.append(TransformerConstants.TO);
		dependencyName.append(element2.getName());
		return dependencyName.toString();
	}

	private Identifier createIdentifier(VariabilityElementDefAna element) {
		Identifier identifier = null;
		if (!idMap.containsKey(element.getName())) {
			identifier = f.newIdentifier(element.getName(), element.getName());
			identifier.setDomain((IntDomain) element.getDomain());
			idMap.put(element.getName(), identifier);
		} else {
			return idMap.get(element.getName());
		}
		return identifier;
	}

}
