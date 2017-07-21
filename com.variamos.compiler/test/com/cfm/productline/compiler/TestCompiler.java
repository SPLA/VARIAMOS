package com.cfm.productline.compiler;

import org.junit.Test;

import com.cfm.productline.ProductLine;
import com.variamos.compiler.prologEditors.Hlcl2GnuProlog;
import com.variamos.compiler.prologEditors.Hlcl2GnuPrologExact;
import com.variamos.compiler.prologEditors.Hlcl2SWIProlog;
import com.variamos.hlcl.ComparisonExpression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.NumericExpression;
import com.variamos.hlcl.NumericIdentifier;
import com.variamos.io.SXFMReader;
import com.variamos.productline.Pl2Hlcl;

import fm.FeatureModelException;

public class TestCompiler {
	private HlclFactory f = new HlclFactory();
	
	public void generationSimple() {
		//Create a + 1 > b
		Identifier a = f.newIdentifier("a");
		Identifier b = f.newIdentifier("b");
		NumericIdentifier num1 = f.number(1);
		
		//a + 1
		NumericExpression sum = f.sum(a, num1);
		
		//a + 1 > b
		ComparisonExpression e = f.greaterThan(sum, b);
		
		HlclProgram p = new HlclProgram();
		p.add(e);
		
		Hlcl2GnuProlog t = new Hlcl2GnuProlog();
		System.out.println(t.transform(p));
	}
	
	@Test
	public void generationPLExact() throws FeatureModelException{
		SXFMReader reader = new SXFMReader();
		ProductLine pl = reader.readFile("fm.splx");
		HlclProgram prog = Pl2Hlcl.transformExact(pl);
		Hlcl2GnuPrologExact t = new Hlcl2GnuPrologExact();
		System.out.println("GNU");
		System.out.println(t.transform(prog));
		System.out.println();
	}
	
	@Test
	public void generationPL() throws FeatureModelException{
		SXFMReader reader = new SXFMReader();
		ProductLine pl = reader.readFile("fm.splx");
		HlclProgram prog = Pl2Hlcl.transform(pl);
		Hlcl2GnuProlog t = new Hlcl2GnuProlog();
		System.out.println("GNU");
		System.out.println(t.transform(prog));
		System.out.println();
	}
	@Test
	public void SWIPrologTransformer() throws FeatureModelException{
		SXFMReader reader = new SXFMReader();
		ProductLine pl = reader.readFile("fm.splx");
		HlclProgram prog = Pl2Hlcl.transform(pl);
		Hlcl2SWIProlog t = new Hlcl2SWIProlog();
		System.out.println("SWI");
		System.out.println(t.transform(prog));
		System.out.println();
	}
	

}
