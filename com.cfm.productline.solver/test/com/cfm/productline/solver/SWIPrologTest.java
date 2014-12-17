package com.cfm.productline.solver;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cfm.hlcl.BinaryDomain;
import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.cfm.hlcl.NumericIdentifier;

public class SWIPrologTest {

	
	private HlclFactory f= null;
	@Before
	public void setUp() throws Exception {
		f =   new HlclFactory();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Little example to test the program load in SWI Prolog. 
	 */
	
	public void isSatisfiableTest() {
				
		Identifier A= f.newIdentifier("A", "A");
		A.setDomain(new BinaryDomain());
		Identifier B= f.newIdentifier("B", "B");
		B.setDomain(new BinaryDomain());
		// A <=> B
		BooleanExpression numericExpression = f.doubleImplies( B,A);
		HlclProgram hlclProgram= new HlclProgram();
		hlclProgram.add(numericExpression);
		Solver swiSolver= new SWIPrologSolver(hlclProgram);
		swiSolver.solve(new Configuration(), new ConfigurationOptions());
		boolean  isSatisfiable= swiSolver.hasNextSolution();
		assertTrue(isSatisfiable);
		
	}
	
	
	
	public void oneConfigurationTest() {
				
		Identifier A= f.newIdentifier("A", "A");
		A.setDomain(new BinaryDomain());
		Identifier B= f.newIdentifier("B", "B");
		B.setDomain(new BinaryDomain());
		// A <=> B
		BooleanExpression numericExpression = f.doubleImplies(A, B);
		HlclProgram hlclProgram= new HlclProgram();
		hlclProgram.add(numericExpression);
		Solver swiSolver= new SWIPrologSolver(hlclProgram);
		swiSolver.solve(new Configuration(), new ConfigurationOptions());
		Configuration configuration= swiSolver.getSolution();
		assertTrue(configuration!=null);
		
	}
	
	
	@Test
	public void allConfigurationsTest() {
				
		Identifier A= f.newIdentifier("A", "A");
		A.setDomain(new BinaryDomain());
		Identifier B= f.newIdentifier("B", "B");
		B.setDomain(new BinaryDomain());
		// A <=> B
		BooleanExpression numericExpression = f.doubleImplies(A, B);
		HlclProgram hlclProgram= new HlclProgram();
		hlclProgram.add(numericExpression);
		Solver swiSolver= new SWIPrologSolver(hlclProgram);
		swiSolver.solve(new Configuration(), new ConfigurationOptions());
		int solFound=0;
		while(swiSolver.hasNextSolution()){
			solFound++;
			System.out.println("Configuration: " + solFound);
			final Configuration configuration = swiSolver.getSolution();
			System.out.println("----"+ configuration.toString());
		
			
		}
		
		assertTrue(solFound==2);
		
	}
	
	
	public void isSatisfiableWithParametersTest() {
				
		Identifier A= f.newIdentifier("A", "A");
		A.setDomain(new BinaryDomain());
		Identifier B= f.newIdentifier("B", "B");
		B.setDomain(new BinaryDomain());
		// A <=> B
		BooleanExpression numericExpression = f.doubleImplies(A, B);
		HlclProgram hlclProgram= new HlclProgram();
		hlclProgram.add(numericExpression);
		Solver swiSolver= new SWIPrologSolver(hlclProgram);

		//Additional configuration parameters
		Identifier C= f.newIdentifier("C", "C");
		C.setDomain(new BinaryDomain());

		// a + 1
				
		NumericIdentifier one = f.number(1);
		NumericIdentifier zero = f.number(0);

		// (1- A)
		NumericExpression substractionA = f.diff(one, A);
		//(1 - A) + C
		NumericExpression sum = f.sum(substractionA, C);
		//(1 - A) + C #> 0,
		BooleanExpression comparaison=f.greaterThan(sum, zero);
		ConfigurationOptions options= new ConfigurationOptions();
		options.addAdditionalExpression(comparaison);
		swiSolver.solve(new Configuration(), new ConfigurationOptions());
		boolean  isSatisfiable= swiSolver.hasNextSolution();

		assertTrue(isSatisfiable);		
	}
	
	
}
