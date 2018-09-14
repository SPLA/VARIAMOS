package com.variamos.hlcl.model.expressions;

import java.util.List;

import com.variamos.hlcl.model.AssignTypeEnum;
import com.variamos.hlcl.model.BooleanOperatorEnum;
import com.variamos.hlcl.model.ComparisonTypeEnum;
import com.variamos.hlcl.model.HlclFunction;
import com.variamos.hlcl.model.NumericOperatorEnum;

public class HlclFactory {
	public IntBooleanExpression and(IntBooleanExpression left, IntBooleanExpression right) {
		return new BooleanOperation(left, right, BooleanOperatorEnum.And);
	}

	public AssignExpression assign(Identifier identifier, IntExpression expression) {
		return new AssignExpression(identifier, expression);
	}

	

	

	public IntNumericExpression diff(IntNumericExpression left,
			IntNumericExpression right) {
		return new NumericOperation(left, right, NumericOperatorEnum.Diff);
	}

	public IntBooleanExpression doubleImplies(IntBooleanExpression left,
			IntBooleanExpression right) {
		return new BooleanOperation(left, right, BooleanOperatorEnum.DoubleImplies);
	}

	public ComparisonExpression equals(IntExpression left, IntExpression right) {
		return new ComparisonExpression(left, right, ComparisonTypeEnum.Equals);
	}

	

	public ComparisonExpression greaterOrEqualsThan(IntNumericExpression left,
			IntNumericExpression right) {
		return new ComparisonExpression(left, right,
				ComparisonTypeEnum.GreaterOrEqualsThan);
	}

	public ComparisonExpression greaterThan(IntNumericExpression left,
			IntNumericExpression right) {
		return new ComparisonExpression(left, right, ComparisonTypeEnum.GreaterThan);
	}

	public IntBooleanExpression implies(IntBooleanExpression left,
			IntBooleanExpression right) {
		return new BooleanOperation(left, right, BooleanOperatorEnum.Implies);
	}

	public ComparisonExpression lessOrEqualsThan(IntNumericExpression left,
			IntNumericExpression right) {
		return new ComparisonExpression(left, right,
				ComparisonTypeEnum.LessOrEqualsThan);
	}

	public ComparisonExpression lessThan(IntNumericExpression left,
			IntNumericExpression right) {
		return new ComparisonExpression(left, right, ComparisonTypeEnum.LessThan);
	}

	public LiteralBooleanExpression literalBooleanExpression(String expression) {
		return new LiteralBooleanExpression(expression);
	}

	

	public Identifier newIdentifier(String id) {
		return new Identifier(id);
	}

	public Identifier newIdentifier(String id, String name) {
		return new Identifier(id);
	}

	

	public SymbolicExpression newSymbolic(String name, Identifier... args) {
		return new SymbolicExpression(name, args);
	}
	

	public IntBooleanExpression not(IntBooleanExpression e) {
		return new BooleanNegation(e);
	}

	public ComparisonExpression notEquals(IntExpression expression,
			IntExpression expression2) {
		return new ComparisonExpression(expression, expression2,
				ComparisonTypeEnum.NotEquals);
	}

	public NumericIdentifier number(int value) {
		return new NumericIdentifier(value);
	}

	public NumericFloatIdentifier floatNumber(float value) {
		return new NumericFloatIdentifier(value);
	}

	public IntBooleanExpression or(IntBooleanExpression left, IntBooleanExpression right) {
		return new BooleanOperation(left, right, BooleanOperatorEnum.Or);
	}
	
	

	public IntNumericExpression prod(IntNumericExpression... p) {
		if (p == null || p.length == 0)
			return new NumericIdentifier(0);

		IntNumericExpression prod = null;
		prod = p[0];
		for (int i = 1; i < p.length; i++) {
			prod = new NumericOperation(prod, p[i], NumericOperatorEnum.Prod);
		}
		return prod;
	}

	public IntNumericExpression sum(List<IntNumericExpression> expressionsList) {
		if (expressionsList == null || expressionsList.isEmpty())
			return new NumericIdentifier(0);

		IntNumericExpression sum = null;
		sum = expressionsList.get(0);
		for (int i = 1; i < expressionsList.size(); i++) {
			sum = new NumericOperation(sum, expressionsList.get(i),
					NumericOperatorEnum.Sum);
		}
		return sum;
	}

	public IntNumericExpression sum(IntNumericExpression... s) {
		if (s == null || s.length == 0)
			return new NumericIdentifier(0);

		IntNumericExpression sum = null;
		sum = s[0];
		for (int i = 1; i < s.length; i++) {
			sum = new NumericOperation(sum, s[i], NumericOperatorEnum.Sum);
		}
		return sum;
	}
}
