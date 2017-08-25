package com.variamos.hlcl.core;

import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.core.HlclUtil;
import com.variamos.hlcl.model.expressions.ComparisonExpression;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntNumericExpression;
import com.variamos.hlcl.model.expressions.LiteralBooleanExpression;
import com.variamos.hlcl.model.expressions.NumericIdentifier;
import com.variamos.hlcl.model.expressions.SymbolicExpression;

@SuppressWarnings("deprecation")
public class TestHlcl {
	private HlclFactory f = new HlclFactory();

	@Test
	public void testSimple() {

		// Create a + 1 > b
		Identifier a = f.newIdentifier("a");
		Identifier b = f.newIdentifier("b");
		NumericIdentifier num1 = f.number(1);

		// a + 1
		IntNumericExpression sum = f.sum(a, num1);

		// a + 1 > b
		ComparisonExpression e = f.greaterThan(sum, b);

		Set<Identifier> ids = HlclUtil.getUsedIdentifiers(e);

		Assert.assertEquals(2, ids.size());

		Assert.assertTrue(ids.contains(a));
		Assert.assertTrue(ids.contains(b));
	}

	@Test
	public void testComplex() {
		// Create ( ( 1 - a ) * ( 5 - b ) >= ( a - b - c ) ) AND d + c < 9
		Identifier a = f.newIdentifier("a");
		Identifier b = f.newIdentifier("b");
		Identifier c = f.newIdentifier("c");
		Identifier d = f.newIdentifier("d");

		NumericIdentifier n1 = f.number(1);
		NumericIdentifier n5 = f.number(5);
		NumericIdentifier n9 = f.number(9);

		// 1 - a
		IntNumericExpression oneMinusA = f.diff(n1, a);
		// 5 - b
		IntNumericExpression fiveMinusB = f.diff(n5, b);

		// (1 - a) * (5 - b)
		IntNumericExpression prod = f.prod(oneMinusA, fiveMinusB);

		// (a - b - c)
		IntNumericExpression aMinusB = f.diff(a, b);
		IntNumericExpression aMinusBMinusC = f.diff(aMinusB, c);

		// (1 - a) * (5 - b) >= (a - b - c)
		ComparisonExpression greatOrEqual = f.greaterOrEqualsThan(prod,
				aMinusBMinusC);

		// d + c
		IntNumericExpression dPlusC = f.sum(d, c);

		// d + c < 9
		ComparisonExpression lessThan = f.lessThan(dPlusC, n9);

		// ( ( 1 - a ) * ( 5 - b ) >= ( a - b - c ) ) AND d + c < 9
		IntBooleanExpression e = f.and(greatOrEqual, lessThan);

		Set<Identifier> ids = HlclUtil.getUsedIdentifiers(e);
		//System.out.println(ids);
		Assert.assertEquals(4, ids.size());

		Assert.assertTrue(ids.contains(a));
		Assert.assertTrue(ids.contains(b));
		Assert.assertTrue(ids.contains(c));
		Assert.assertTrue(ids.contains(d));

		// LiteralExpression ( Only non numeric Identifiers)
		LiteralBooleanExpression lt = f.literalBooleanExpression("Test #=5,_variable#=notVariable, 1=2");
		Set<Identifier> idsLt = HlclUtil.getUsedIdentifiers(lt);
		Identifier test = f.newIdentifier("Test");
		Assert.assertTrue(idsLt.contains(test));
		Assert.assertEquals(2, idsLt.size());

	}

	@Test
	public void testSymbolic() {
		// a < b + c AND all_different(a, b, c, h)
		Identifier a = f.newIdentifier("a");
		Identifier b = f.newIdentifier("b");
		Identifier c = f.newIdentifier("c");
		Identifier h = f.newIdentifier("h");

		// b + c
		IntNumericExpression bPlusc = f.sum(b, c);

		// a < b + c
		ComparisonExpression lessThan = f.lessThan(a, bPlusc);

		// all_different(a, b, c, h)
		SymbolicExpression allDiff = f.newSymbolic("all_different", a, b, c, h);

		// a < b + c AND all_different(a, b, c, h)
		IntBooleanExpression e = f.and(lessThan, allDiff);

		Set<Identifier> ids = HlclUtil.getUsedIdentifiers(e);

		Assert.assertEquals(4, ids.size());

		Assert.assertTrue(ids.contains(a));
		Assert.assertTrue(ids.contains(b));
		Assert.assertTrue(ids.contains(c));
		Assert.assertTrue(ids.contains(h));
	}

	@Test
	public void testOperations() {

		Identifier a = f.newIdentifier("a");
		Identifier b = f.newIdentifier("b");
		Identifier c = f.newIdentifier("c");
		Identifier h = f.newIdentifier("h");

		IntNumericExpression e = f.sum(null, null);
		// Assert.assertTrue(e instanceof NumericIdentifier);
		// Assert.assertEquals(0, ((NumericIdentifier) e).getValue());

		e = f.sum(a);

		Assert.assertEquals(e, a);

		e = f.sum(a, b, c, h);

		Set<Identifier> ids = HlclUtil.getUsedIdentifiers(e);
		Assert.assertEquals(4, ids.size());

		Assert.assertTrue(ids.contains(a));
		Assert.assertTrue(ids.contains(b));
		Assert.assertTrue(ids.contains(c));
		Assert.assertTrue(ids.contains(h));
	}

	@Test
	public void testIdentifierCountSimple() {
		// Create a + 1 > b
		Identifier a = f.newIdentifier("a");
		//Identifier b = f.newIdentifier("b");
		NumericIdentifier num1 = f.number(1);

		// a + 1
		IntNumericExpression sum = f.sum(a, num1);

		// a + 1 > b
		//ComparisonExpression e = f.greaterThan(sum, b);

		// a + 1 = a+1

		ComparisonExpression eq = f.equals(sum, sum);

		Map<Identifier, Long> idsCountMap = HlclUtil.getCountOfIdentifiers(eq);

		Assert.assertTrue(!idsCountMap.isEmpty());
		Assert.assertEquals(1, idsCountMap.size());
		Assert.assertTrue(idsCountMap.get(a) != null);
		Assert.assertTrue(idsCountMap.get(a).equals(new Long(2)));

	}

	@Test
	public void testIdentifierCountComplex() {

		// Create ( ( 1 - a ) * ( 5 - b ) >= ( a - b - c ) ) AND d + c < 9 each
		// one as a constraint instruction
		Identifier a = f.newIdentifier("a");
		Identifier b = f.newIdentifier("b");
		Identifier c = f.newIdentifier("c");
		Identifier d = f.newIdentifier("d");

		NumericIdentifier n1 = f.number(1);
		NumericIdentifier n5 = f.number(5);
		NumericIdentifier n9 = f.number(9);

		// 1 - a
		IntNumericExpression oneMinusA = f.diff(n1, a);
		// 5 - b
		IntNumericExpression fiveMinusB = f.diff(n5, b);

		// (1 - a) * (5 - b)
		IntNumericExpression prod = f.prod(oneMinusA, fiveMinusB);

		// (a - b - c)
		IntNumericExpression aMinusB = f.diff(a, b);
		IntNumericExpression aMinusBMinusC = f.diff(aMinusB, c);

		// (1 - a) * (5 - b) >= (a - b - c)
		ComparisonExpression greatOrEqual = f.greaterOrEqualsThan(prod,
				aMinusBMinusC);

		// d + c
		IntNumericExpression dPlusC = f.sum(d, c);

		// d + c < 9
		ComparisonExpression lessThan = f.lessThan(dPlusC, n9);

		// ( ( 1 - a ) * ( 5 - b ) >= ( a - b - c ) ) AND d + c < 9
		IntBooleanExpression e = f.and(greatOrEqual, lessThan);

		Map<Identifier, Long> idsCountMap = HlclUtil.getCountOfIdentifiers(e);

		Assert.assertTrue(!idsCountMap.isEmpty());
		Assert.assertEquals(4, idsCountMap.size());
		Assert.assertTrue(idsCountMap.get(a) != null);
		Assert.assertTrue(idsCountMap.get(a).equals(new Long(2)));

	}

	@Test
	public void testIdentifierHlclProgram() {

		// Create ( ( 1 - a ) * ( 5 - b ) >= ( a - b - c ) ) AND d + c < 9 each
		// one as a constraint instruction
		Identifier a = f.newIdentifier("a");
		//Identifier b = f.newIdentifier("b");
		//Identifier c = f.newIdentifier("c");
		//Identifier d = f.newIdentifier("d");

		NumericIdentifier n1 = f.number(1);
		NumericIdentifier n5 = f.number(5);
		//NumericIdentifier n9 = f.number(9);

		HlclProgram program = new HlclProgram();
		// 1 >= a
		IntBooleanExpression oneMinusA = (IntBooleanExpression) f
				.greaterOrEqualsThan(n1, a);
		program.add((IntBooleanExpression) oneMinusA);
		// 5 >= b
		IntBooleanExpression fiveMinusB = (IntBooleanExpression) f
				.greaterOrEqualsThan(n5, a);
		program.add((IntBooleanExpression) fiveMinusB);

		Map<Identifier, Long> idsCountMap = HlclUtil
				.getCountOfIdentifiers(program);

		Assert.assertTrue(!idsCountMap.isEmpty());
		Assert.assertEquals(1, idsCountMap.size());
		Assert.assertTrue(idsCountMap.get(a) != null);
		Assert.assertTrue(idsCountMap.get(a).equals(new Long(2)));

	}

	@Test
	public void equalsIdentifiers() {
		HlclFactory f = null;
		f = new HlclFactory();
		Identifier identifier1 = f.newIdentifier("a");
		Identifier identifier2 = f.newIdentifier("a");

		Assert.assertEquals(identifier1, identifier2);
	}

}
