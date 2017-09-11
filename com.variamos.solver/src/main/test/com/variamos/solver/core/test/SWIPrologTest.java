package com.variamos.solver.core.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.variamos.hlcl.BinaryDomain;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.NumericExpression;
import com.variamos.hlcl.NumericIdentifier;
import com.variamos.solver.core.SWIPrologSolver;
import com.variamos.solver.core.IntSolver;
import com.variamos.solver.model.SolverSolution;
import com.variamos.solver.model.ConfigurationOptionsDTO;

public class SWIPrologTest {

	private HlclFactory f = null;

	@Before
	public void setUp() throws Exception {
		f = new HlclFactory();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Little example to test the program load in SWI Prolog.
	 */
	@Test
	public void isSatisfiableTest() {

		Identifier A = f.newIdentifier("A", "A");
		A.setDomain(new BinaryDomain());
		Identifier B = f.newIdentifier("B", "B");
		B.setDomain(new BinaryDomain());
		// A <=> B
		BooleanExpression numericExpression = f.doubleImplies(B, A);
		HlclProgram hlclProgram = new HlclProgram();
		hlclProgram.add(numericExpression);
		IntSolver swiSolver = new SWIPrologSolver(hlclProgram);
		swiSolver.solve(new SolverSolution(), new ConfigurationOptionsDTO());
		boolean isSatisfiable = swiSolver.hasSolution();
		assertTrue(isSatisfiable);

	}

	@Test
	public void oneConfigurationTest() {

		Identifier A = f.newIdentifier("A", "A");
		A.setDomain(new BinaryDomain());
		Identifier B = f.newIdentifier("B", "B");
		B.setDomain(new BinaryDomain());
		// A <=> B
		BooleanExpression numericExpression = f.doubleImplies(A, B);
		HlclProgram hlclProgram = new HlclProgram();
		hlclProgram.add(numericExpression);
		IntSolver swiSolver = new SWIPrologSolver(hlclProgram);

		swiSolver.solve(new SolverSolution(), new ConfigurationOptionsDTO());
		SolverSolution configurationObtained = swiSolver.getSolution();
		assertTrue(configurationObtained != null);

	}

	@Test
	public void oneDynamicConfigurationTest() {

		Identifier A = f.newIdentifier("A", "A");
		A.setDomain(new BinaryDomain());
		Identifier B = f.newIdentifier("B", "B");
		B.setDomain(new BinaryDomain());
		// A <=> B
		BooleanExpression numericExpression = f.doubleImplies(A, B);
		HlclProgram hlclProgram = new HlclProgram();
		hlclProgram.add(numericExpression);
		IntSolver swiSolver = new SWIPrologSolver(hlclProgram);

		SolverSolution configuration = new SolverSolution();
		configuration.enforce("A");
		swiSolver.solve(configuration, new ConfigurationOptionsDTO());
		SolverSolution configurationObtained = swiSolver.getSolution();
		System.out.println("configuration: " + configuration.toString());
		assertTrue(configurationObtained != null);
		assertTrue(configurationObtained.getConfiguration().get("A")
				.floatValue() == 1);

	}

	@Test
	public void allConfigurationsTest() {

		Identifier A = f.newIdentifier("A", "A");
		A.setDomain(new BinaryDomain());
		Identifier B = f.newIdentifier("B", "B");
		B.setDomain(new BinaryDomain());
		// A <=> B
		BooleanExpression numericExpression = f.doubleImplies(A, B);
		HlclProgram hlclProgram = new HlclProgram();
		hlclProgram.add(numericExpression);
		IntSolver swiSolver = new SWIPrologSolver(hlclProgram);
		swiSolver.solve(new SolverSolution(), new ConfigurationOptionsDTO());
		int solFound = 0;

		SolverSolution configuration = new SolverSolution();
		while (configuration != null) {

			System.out.println("Configuration: " + solFound);
			configuration = swiSolver.getSolution();
			if (configuration != null) {
				System.out.println("----" + configuration.toString());
				solFound++;
			}

		}

		assertTrue(solFound == 2);

	}

	@Test
	public void loadMethod() {

		IntSolver swiSolver = new SWIPrologSolver(null);
		ConfigurationOptionsDTO options = new ConfigurationOptionsDTO();
		options.setProgramPath("E:/falseOptionalTest.pl");
		String productLineName = "productline";
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			options.setProgramName(productLineName + (i + 1));
			swiSolver.solve(new SolverSolution(), options);
			swiSolver.getSolution();
			if (i == 0) {
				options.setStartFromZero(false);
			}
			System.out.println(" time " + i);
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(" load time: " + totalTime);
	}

	@Test
	public void isSatisfiableWithParametersTest() {

		Identifier A = f.newIdentifier("A", "A");
		A.setDomain(new BinaryDomain());
		Identifier B = f.newIdentifier("B", "B");
		B.setDomain(new BinaryDomain());
		// A <=> B
		BooleanExpression numericExpression = f.doubleImplies(A, B);
		HlclProgram hlclProgram = new HlclProgram();
		hlclProgram.add(numericExpression);
		IntSolver swiSolver = new SWIPrologSolver(hlclProgram);

		// Additional configuration parameters
		Identifier C = f.newIdentifier("C", "C");
		C.setDomain(new BinaryDomain());

		// a + 1

		NumericIdentifier one = f.number(1);
		NumericIdentifier zero = f.number(0);

		// (1- A)
		NumericExpression substractionA = f.diff(one, A);
		// (1 - A) + C
		NumericExpression sum = f.sum(substractionA, C);
		// (1 - A) + C #> 0,
		BooleanExpression comparaison = f.greaterThan(sum, zero);
		ConfigurationOptionsDTO options = new ConfigurationOptionsDTO();
		options.addAdditionalExpression(comparaison);
		swiSolver.solve(new SolverSolution(), new ConfigurationOptionsDTO());
		boolean isSatisfiable = swiSolver.hasSolution();

		assertTrue(isSatisfiable);
	}

}
