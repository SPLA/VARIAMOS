package com.cfm.productline.compiler;

import org.junit.Test;

import com.cfm.hlcl.ComparisonExpression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.cfm.hlcl.NumericIdentifier;
import com.cfm.productline.ProductLine;
import com.cfm.productline.io.SXFMReader;
import com.cfm.productline.productLine.Pl2Hlcl;
import com.cfm.productline.prologEditors.Hlcl2GnuProlog;
import com.cfm.productline.prologEditors.Hlcl2GnuPrologExact;
import com.cfm.productline.prologEditors.Hlcl2SWIProlog;

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
		System.out.println(t.transform(prog));
	}
	
	@Test
	public void generationPL() throws FeatureModelException{
		SXFMReader reader = new SXFMReader();
		ProductLine pl = reader.readFile("fm.splx");
		HlclProgram prog = Pl2Hlcl.transform(pl);
		Hlcl2GnuProlog t = new Hlcl2GnuProlog();
		System.out.println(t.transform(prog));
	}
	
	public void SWIPrologTransformer() throws FeatureModelException{
		SXFMReader reader = new SXFMReader();
		ProductLine pl = reader.readFile("fm.splx");
		HlclProgram prog = Pl2Hlcl.transform(pl);
		Hlcl2SWIProlog t = new Hlcl2SWIProlog();
		System.out.println("SWI");
		System.out.println(t.transform(prog));
	}
	

}
