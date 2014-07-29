package com.cfm.productline.solver;

import com.cfm.jgprolog.core.PrologContext;
import com.cfm.jgprolog.gnuprolog.GNUPrologContext;
import com.cfm.productline.ProductLine;
import com.cfm.productline.io.SXFMReader;

import fm.FeatureModelException;

public class Test {
	
	public static void main(String[] args) throws FeatureModelException{
		
		PrologContext ctx = new GNUPrologContext();
		PrologSolver solver = new PrologSolver(ctx);
		
		SXFMReader reader = new SXFMReader();
		ProductLine pl = reader.readFile("fm.splx");
		solver.setProductLine(pl);
		
		Configuration cfg = new Configuration();
		cfg.enforce("Root");
		cfg.enforce("F2");
		
		ConfigurationOptions op = new ConfigurationOptions();
		op.setMode(ConfigurationMode.FULL);
		solver.solve(cfg, op);
		
		if(!solver.hasNextSolution())
			System.out.println("No solution found");
		
		while( solver.hasNextSolution() ){
			System.out.println("---------- Solution ----------- ");
			Configuration sol = solver.getSolution();
			sol.debugPrint();
			solver.nextSolution();
		}
		
		solver.endSolving();
	}
}
