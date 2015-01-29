package com.variamos.hlcl;

import java.util.List;

public class HlclFactory {
	public Identifier newIdentifier(String id) {
		return new Identifier(id);
	}

	public Identifier newIdentifier(String id, String name) {
		return new Identifier(id);
	}

	public NumericIdentifier number(int value) {
		return new NumericIdentifier(value);
	}

	public NumericExpression sum(NumericExpression... s) {
		if (s == null || s.length == 0)
			return new NumericIdentifier(0);

		NumericExpression sum = null;
		sum = s[0];
		for (int i = 1; i < s.length; i++) {
			sum = new NumericOperation(sum, s[i], NumericOperator.Sum);
		}
		return sum;
	}

	public NumericExpression sum(List<NumericExpression> expressionsList) {
		if (expressionsList == null || expressionsList.isEmpty())
			return new NumericIdentifier(0);

		NumericExpression sum = null;
		sum = expressionsList.get(0);
		for (int i=1; i<expressionsList.size();i++) {
			sum = new NumericOperation(sum,expressionsList.get(i), NumericOperator.Sum);
		}
		return sum;
	}

	public NumericExpression diff(NumericExpression left,
			NumericExpression right) {
		return new NumericOperation(left, right, NumericOperator.Diff);
	}

	public NumericExpression prod(NumericExpression... p) {
		if (p == null || p.length == 0)
			return new NumericIdentifier(0);

		NumericExpression prod = null;
		prod = p[0];
		for (int i = 1; i < p.length; i++) {
			prod = new NumericOperation(prod, p[i], NumericOperator.Prod);
		}
		return prod;
	}

	public ComparisonExpression greaterThan(NumericExpression left,
			NumericExpression right) {
		return new ComparisonExpression(left, right, ComparisonType.GreaterThan);
	}

	public ComparisonExpression greaterOrEqualsThan(NumericExpression left,
			NumericExpression right) {
		return new ComparisonExpression(left, right,
				ComparisonType.GreaterOrEqualsThan);
	}

	public ComparisonExpression lessThan(NumericExpression left,
			NumericExpression right) {
		return new ComparisonExpression(left, right, ComparisonType.LessThan);
	}

	public ComparisonExpression lessOrEqualsThan(NumericExpression left,
			NumericExpression right) {
		return new ComparisonExpression(left, right,
				ComparisonType.LessOrEqualsThan);
	}

	public ComparisonExpression equals(Identifier left, Identifier right) {
		return new ComparisonExpression(left, right, ComparisonType.Equals);
	}

	public ComparisonExpression equals(Expression left,
			Expression right) {
		return new ComparisonExpression(left, right, ComparisonType.Equals);
	}
	
	public ComparisonExpression notEquals(Expression expression,
			Expression expression2) {
		return new ComparisonExpression(expression, expression2, ComparisonType.NotEquals);
	}

	public BooleanExpression and(BooleanExpression left, BooleanExpression right) {
		return new BooleanOperation(left, right, BooleanOperator.And);
	}

	public BooleanExpression or(BooleanExpression left, BooleanExpression right) {
		return new BooleanOperation(left, right, BooleanOperator.Or);
	}

	public BooleanExpression implies(BooleanExpression left,
			BooleanExpression right) {
		return new BooleanOperation(left, right, BooleanOperator.Implies);
	}

	public BooleanExpression doubleImplies(BooleanExpression left,
			BooleanExpression right) {
		return new BooleanOperation(left, right, BooleanOperator.DoubleImplies);
	}

	public BooleanExpression not(BooleanExpression e) {
		return new BooleanNegation(e);
	}

	public SymbolicExpression newSymbolic(String name, Identifier... args) {
		return new SymbolicExpression(name, args);
	}
	
	public ListDefinitionExpression newList(List<Identifier> values){
		return new ListDefinitionExpression(values);
	}
	
	public LiteralBooleanExpression literalBooleanExpression(String expression){
		return new LiteralBooleanExpression(expression);
	}
	
	public AssignExpression assign(Identifier identifier, Expression expression){
		return new AssignExpression(identifier, expression);
	}
	
	public FunctionDeclarationExpression declare(SymbolicExpression exp){
		return new FunctionDeclarationExpression(exp);
	}
	
	public HlclFunction newFunction(FunctionDeclarationExpression fde){
		return new HlclFunction(fde);
	}
}
