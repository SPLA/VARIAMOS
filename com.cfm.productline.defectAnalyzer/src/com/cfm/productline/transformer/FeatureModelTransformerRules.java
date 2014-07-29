package com.cfm.productline.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cfm.hlcl.Domain;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.cfm.hlcl.NumericIdentifier;
import com.cfm.productline.constants.ConstraintSymbolsConstant;
import com.cfm.productline.constants.TransformerConstants;
import com.cfm.productline.model.defectAnalyzerModel.VariabilityElement;

import constraints.BooleanVariable;

public class FeatureModelTransformerRules {

	private static HlclFactory f = null;
	private static Map<String, Identifier> idMap = new HashMap<>();

	public FeatureModelTransformerRules() {
		super();
		f = new HlclFactory();
		idMap = new HashMap<>();
	}

	public Expression getMandatoryRule(VariabilityElement element1,
			VariabilityElement element2) {
		// A <=> B
		Expression numericExpression = f.doubleImplies(
				createIdentifier(element1), createIdentifier(element2));
		return numericExpression;
	}

	/**
	 * Método para negar una restricción mandatory
	 * 
	 * @param element1
	 * @param element2
	 * @return
	 */
	public Expression getNegationMandatoryRule(VariabilityElement element1,
			VariabilityElement element2) {
		// A * (1-B) + B * (1- A) > 0
		Identifier A = createIdentifier(element1);
		Identifier B = createIdentifier(element2);
		NumericIdentifier one = f.number(1);
		NumericIdentifier zero = f.number(ConstraintSymbolsConstant.ZERO);

		// (1- B)
		NumericExpression substractionB = f.diff(one, B);
		// (1- A)
		NumericExpression substractionA = f.diff(one, A);
		// A * (1-B)
		NumericExpression multiplicationA = f.prod(A, substractionB);
		// B * (1- A)
		NumericExpression multiplicationB = f.prod(B, substractionA);
		// A * (1-B) + B * (1- A)
		NumericExpression sum = f.sum(multiplicationA, multiplicationB);
		// A * (1-B) + B * (1- A)> 0
		Expression completeNegationExpression = f.greaterThan(sum, zero);

		return completeNegationExpression;

	}

	public Expression getOptionalRule(VariabilityElement element1,
			VariabilityElement element2) {

		// A >= B
		Expression numericExpression = f.greaterOrEqualsThan(
				createIdentifier(element1), createIdentifier(element2));

		return numericExpression;
	}

	public Expression getNegationOptionalRule(VariabilityElement element1,
			VariabilityElement element2) {

		// A < B
		Expression numericExpression = f.lessThan(createIdentifier(element1),
				createIdentifier(element2));

		return numericExpression;
	}

	public Expression getAssignRule(int valueToAssing,
			VariabilityElement element1) {

		// Root =1
		NumericIdentifier numericIdentifier = f.number(valueToAssing);
		Expression numericExpression = f.equals(createIdentifier(element1),
				numericIdentifier);
		return numericExpression;
	}

	public Expression getGroupalDependencyRule1(VariabilityElement element1,
			List<VariabilityElement> elements, int lowerCardinality) {
		// m * P <= SUM features
		// element1=parent feature
		List<NumericExpression> identifiers = new ArrayList<NumericExpression>();
		Identifier parentIdentifier = createIdentifier(element1);
		NumericIdentifier lowerCardinalityIdentifier = f
				.number(lowerCardinality);
		for (VariabilityElement element : elements) {
			identifiers.add(createIdentifier(element));
		}
		NumericExpression sumNumericExpression = f.sum(identifiers);
		NumericExpression multiplyExpression = f.prod(
				lowerCardinalityIdentifier, parentIdentifier);
		Expression ruleExpression = f.lessOrEqualsThan(multiplyExpression,
				sumNumericExpression);

		return ruleExpression;
	}
	
	
	public Expression getNegationGroupalDependencyRule1(VariabilityElement element1,
			List<VariabilityElement> elements, int lowerCardinality) {
		// m * P > SUM features
		// element1=parent feature
		List<NumericExpression> identifiers = new ArrayList<NumericExpression>();
		Identifier parentIdentifier = createIdentifier(element1);
		NumericIdentifier lowerCardinalityIdentifier = f
				.number(lowerCardinality);
		for (VariabilityElement element : elements) {
			identifiers.add(createIdentifier(element));
		}
		NumericExpression sumNumericExpression = f.sum(identifiers);
		NumericExpression multiplyExpression = f.prod(
				lowerCardinalityIdentifier, parentIdentifier);
		Expression ruleExpression = f.greaterThan(multiplyExpression,
				sumNumericExpression);

		return ruleExpression;
	}
	

	public Expression getGroupalDependencyRule2(VariabilityElement element1,
			List<VariabilityElement> elements, int upperCardinality) {
		// // SUM features <= n * P
		List<NumericExpression> identifiers = new ArrayList<NumericExpression>();
		Identifier parentIdentifier = createIdentifier(element1);
		NumericIdentifier upperCardinalityIdentifier = f
				.number(upperCardinality);
		for (VariabilityElement element : elements) {
			identifiers.add(createIdentifier(element));
		}
		NumericExpression sumNumericExpression = f.sum(identifiers);
		NumericExpression multiplyExpression = f.prod(
				upperCardinalityIdentifier, parentIdentifier);
		Expression ruleExpression = f.lessOrEqualsThan(sumNumericExpression,
				multiplyExpression);
		return ruleExpression;
	}
	
	public Expression getNegationGroupalDependencyRule2(VariabilityElement element1,
			List<VariabilityElement> elements, int upperCardinality) {
		// // SUM features > n * P
		List<NumericExpression> identifiers = new ArrayList<NumericExpression>();
		Identifier parentIdentifier = createIdentifier(element1);
		NumericIdentifier upperCardinalityIdentifier = f
				.number(upperCardinality);
		for (VariabilityElement element : elements) {
			identifiers.add(createIdentifier(element));
		}
		NumericExpression sumNumericExpression = f.sum(identifiers);
		NumericExpression multiplyExpression = f.prod(
				upperCardinalityIdentifier, parentIdentifier);
		Expression ruleExpression = f.greaterThan(sumNumericExpression,
				multiplyExpression);
		return ruleExpression;
	}

	public Expression getGroupalDependencyRule3(VariabilityElement element1,
			List<VariabilityElement> elements) {
		// P #= SUM features
		List<NumericExpression> identifiers = new ArrayList<NumericExpression>();
		Identifier parentIdentifier = createIdentifier(element1);
		for (VariabilityElement element : elements) {
			identifiers.add(createIdentifier(element));
		}
		NumericExpression sumNumericExpression = f.sum(identifiers);
		Expression assignExpresion = f.equals(parentIdentifier,
				sumNumericExpression);
		return assignExpresion;
	}
	
	public Expression getNegationGroupalDependencyRule3(VariabilityElement element1,
			List<VariabilityElement> elements) {
		// P != SUM features
		List<NumericExpression> identifiers = new ArrayList<NumericExpression>();
		Identifier parentIdentifier = createIdentifier(element1);
		for (VariabilityElement element : elements) {
			identifiers.add(createIdentifier(element));
		}
		NumericExpression sumNumericExpression = f.sum(identifiers);
		Expression assignExpresion = f.notEquals(parentIdentifier,
				sumNumericExpression);
		return assignExpresion;
	}

	public NumericExpression getIdentifiersOfExpression(
			BooleanVariable element, VariabilityElement variabilityElement) {
		Identifier elementIdentifier = null;
		if (element.isPositive()) {
			elementIdentifier = createIdentifier(variabilityElement);
			return elementIdentifier;
		} else {
			// (1- element)
			NumericIdentifier one = f.number(1);
			elementIdentifier = createIdentifier(variabilityElement);
			NumericExpression expression = f.diff(one, elementIdentifier);
			return expression;
		}

	}

	public Expression getPropositionalConstraintsRule(
			List<NumericExpression> expressionList) {
		// prop1 + prop2 +--- #>0" EJM (1 - F2) + (1 - F8) #> 0,
		NumericIdentifier zero = f.number(ConstraintSymbolsConstant.ZERO);
		NumericExpression sumNumericExpression = f.sum(expressionList);
		Expression ruleExpression = f.greaterThan(sumNumericExpression, zero);
		return ruleExpression;
	}
	
	public Expression getNegationPropositionalConstraintsRule(
			List<NumericExpression> expressionList) {
		// prop1 + prop2 +--- #>0" EJM (1 - F2) + (1 - F8) #<= 0,
		NumericIdentifier zero = f.number(ConstraintSymbolsConstant.ZERO);
		NumericExpression sumNumericExpression = f.sum(expressionList);
		Expression ruleExpression = f.lessOrEqualsThan(sumNumericExpression, zero);
		return ruleExpression;
	}


	public String getGroupalDependencyName3(VariabilityElement element1,
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

	public String getGroupalDependencyName1(VariabilityElement element1,
			String featureSet) {
		// Parent TO groupedFeatures LowCardinality
		StringBuilder dependencyName = new StringBuilder(
				getGroupalDependencyName3(element1, featureSet));
		dependencyName.append(TransformerConstants.LOW_CARDINALITY);
		return dependencyName.toString();
	}

	public String getGroupalDependencyName2(VariabilityElement element1,
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

	public String getRootDependencyName(VariabilityElement element1) {
		StringBuilder dependencyName = new StringBuilder();
		// Model root: feature1"
		dependencyName.append(TransformerConstants.MODEL_ROOT);
		dependencyName.append(element1.getName());
		return dependencyName.toString();
	}

	public String getMandatoryDependencyName(
			VariabilityElement element1, VariabilityElement element2) {
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
			VariabilityElement element1, VariabilityElement element2) {
		StringBuilder dependencyName = new StringBuilder();
		// Mandatory Dependency between feature1 y feature2"
		dependencyName.append(TransformerConstants.DEPENDENCY);
		dependencyName.append(TransformerConstants.OPTIONAL +TransformerConstants.BETWEEN);
		dependencyName.append(element1.getName());
		dependencyName.append(TransformerConstants.TO);
		dependencyName.append(element2.getName());
		return dependencyName.toString();
	}

	private Identifier createIdentifier(VariabilityElement element) {
		Identifier identifier = null;
		if (!idMap.containsKey(element.getName())) {
			identifier = f.newIdentifier(element.getName(), element.getName());
			identifier.setDomain((Domain) element.getDomain());
			idMap.put(element.getName(), identifier);
		} else {
			return idMap.get(element.getName());
		}
		return identifier;
	}

}
