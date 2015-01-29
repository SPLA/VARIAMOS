package com.variamos.productline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cfm.common.AbstractModel;
import com.cfm.productline.Constraint;
import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.Variable;
import com.cfm.productline.constraints.ExcludesConstraint;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.cfm.productline.constraints.MandatoryConstraint;
import com.cfm.productline.constraints.OptionalConstraint;
import com.cfm.productline.constraints.RequiresConstraint;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.ComparisonExpression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclFunction;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.LiteralBooleanExpression;
import com.variamos.hlcl.NumericExpression;
import com.variamos.hlcl.NumericIdentifier;
import com.variamos.hlcl.SymbolicExpression;

public class Pl2Hlcl {

	private static HlclFactory f = new HlclFactory();

	public static HlclProgram transform(ProductLine pl) {
		HlclProgram prog = new HlclProgram();

		Map<String, Identifier> idMap = new HashMap<>();

		// For each variability element create a variable
		for (VariabilityElement elm : pl.getVariabilityElements())
		{
			idMap.put(elm.getIdentifier(),
					f.newIdentifier(elm.getIdentifier(), elm.getName()));
			//idMap.put(elm.getIdentifier(),
					//f.newIdentifier(elm.getName(), elm.getName()));
		}

		// For each constraint generate an expression.
		for (Constraint c : pl.getConstraints())
			prog.add(transformConstraint(c, idMap));

		return prog;
	}
	
	public static HlclProgram transformExact(AbstractModel pl){
		Map<String, Identifier> idMap = new HashMap<>();
		
		//Add a header for the program
		//Create L
		Identifier L = f.newIdentifier("L");
		
		//Create the function declaration
		SymbolicExpression se = f.newSymbolic("productline", L);
		HlclFunction prog = new HlclFunction(f.declare(se));
		
		// For each variability element create a variable
		for (VariabilityElement elm : pl.getVariabilityElements()){
			Identifier i = f.newIdentifier(elm.getIdentifier(), elm.getName());
			idMap.put(elm.getIdentifier(), i);
			
			//Identifier i = f.newIdentifier(elm.getName(), elm.getName());
			//idMap.put(elm.getName(), i);
			
			//Add a domain declaration for each variable.
			prog.add(f.newSymbolic("fd_domain", i, f.newIdentifier("0"), f.newIdentifier("1")));
			
			//Get each attribute and create a variable as well.
			for(Variable v : elm.getVarAttributes()){
				Identifier a = f.newIdentifier(v.getName());
				idMap.put(v.getName(), a);
				prog.add( f.newSymbolic("fd_domain", getDomainDefinition(a, v)));
			}
			
			//TODO Do Something with the assets?
		}
		
		List<Identifier> ids = new ArrayList<>();
		for(Identifier id : idMap.values() )
		{
			//System.out.println(id);
			ids.add(id);
		}
		
		prog.add(0, f.assign(L, f.newList( ids )));
		
		// For each constraint generate an expression.
		for (Constraint c : pl.getConstraints())
			prog.add(transformConstraint(c, idMap));
		
		return prog;
	}

	private static Identifier[] getDomainDefinition(Identifier a, Variable v) {
		
		Identifier[] iden = new Identifier[v.getDomain().size() + 1];
		iden[0] = a;
		try{
		int i = 0;
		for(Integer val : v.getDomain().getPossibleValues() ){
			iden[i + 1] = f.newIdentifier(String.valueOf(val));
			i++;
		}
		}catch(Exception e){
			System.out.println( "LOL" );
		}
		return iden;
	}

	/**
	 * Transform a Constraint of a Product Line in a BooleanExpression
	 * @param c
	 * @param idMap
	 * @return
	 */
	public static BooleanExpression transformConstraint(Constraint c,
			Map<String, Identifier> idMap) {

		// Switching
		if (c instanceof MandatoryConstraint)
			return transformMandatory((MandatoryConstraint) c, idMap);

		if (c instanceof OptionalConstraint)
			return transformOptional((OptionalConstraint) c, idMap);

		if (c instanceof GroupConstraint)
			return transformGroup((GroupConstraint) c, idMap);

		if (c instanceof RequiresConstraint)
			return transformRequires((RequiresConstraint) c, idMap);

		if (c instanceof ExcludesConstraint)
			return transformExcludes((ExcludesConstraint) c, idMap);
		
		// Generic constraint, we have to parse it
		if( c instanceof GenericConstraint )
			return parseConstraint( ((GenericConstraint)c).getText(), idMap);
		
		//We don't know this constraint type
		return null;
	}

	private static BooleanExpression parseConstraint(String text,
			Map<String, Identifier> idMap) {
		// TODO: Implement parser !
		// HACK: For now we are passing the expression as is.
		return new LiteralBooleanExpression(text);
	}

	private static BooleanExpression transformExcludes(ExcludesConstraint c,
			Map<String, Identifier> idMap) {
		Identifier a = idMap.get(c.getFeature1Id());
		Identifier b = idMap.get(c.getFeature2Id());

		return f.equals(f.prod(a, b), f.number(0));
	}

	private static BooleanExpression transformRequires(RequiresConstraint c,
			Map<String, Identifier> idMap) {

		Identifier a = idMap.get(c.getFeature1Id());
		Identifier b = idMap.get(c.getFeature2Id());

		NumericIdentifier n1 = f.number(1);

		// (1 - a) + b
		NumericExpression sum = f.sum(f.diff(n1, a), b);

		// (1 - a) + b > 0
		return f.greaterThan(sum, f.number(0));
	}

	private static BooleanExpression transformGroup(GroupConstraint c,
			Map<String, Identifier> idMap) {

		// m * A
		Identifier A = idMap.get(c.getParent());
		NumericIdentifier m = f.number(c.getLowerLimit());
		NumericExpression prodInf = f.prod(m, A);

		// Sum
		if (c.getChildCount() == 0) // Some error or something
			return null; // TODO: Should raise BadFormedException

		Identifier[] ids = new Identifier[c.getChildCount()];
		for (int i = 0; i < c.getChildCount(); i++) {
			ids[i] = idMap.get(c.getChildId(i));
		}
		NumericExpression sum = f.sum(ids);

		if (c.getLowerLimit() == c.getUpperLimit())
			return f.equals(prodInf, sum);

		// n * A
		NumericIdentifier n = f.number(c.getUpperLimit());
		NumericExpression prodSup = f.prod(n, A);

		// First comparison
		ComparisonExpression c1 = f.lessOrEqualsThan(prodInf, sum);
		// Second comparison
		ComparisonExpression c2 = f.lessOrEqualsThan(sum, prodSup);

		HlclProgram p = new HlclProgram();
		p.add(c1);
		p.add(c2);

		return p;
	}

	private static BooleanExpression transformOptional(OptionalConstraint c,
			Map<String, Identifier> idMap) {
		Identifier a = idMap.get(c.getFeature1Id());
		Identifier b = idMap.get(c.getFeature2Id());

		return f.greaterOrEqualsThan(a, b);
	}

	private static BooleanExpression transformMandatory(MandatoryConstraint c,
			Map<String, Identifier> idMap) {
		Identifier a = idMap.get(c.getFeature1Id());
		Identifier b = idMap.get(c.getFeature2Id());

		return f.doubleImplies(a, b);
	}
}
