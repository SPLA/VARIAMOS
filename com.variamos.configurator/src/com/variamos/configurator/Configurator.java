package com.variamos.configurator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cfm.productline.ProductLine;
import com.variamos.io.importExport.core.ConfigurationDTO;
import com.variamos.solver.core.ConfigurationTask;
import com.variamos.solver.core.ConfigurationTaskListener;
import com.variamos.solver.core.IntSolver;
import com.variamos.solver.model.ConfigurationOptionsDTO;
import com.variamos.solver.model.SolverSolution;

/**
 * @author unkwnown jcmunoz-diego: Splitted from class ConfiguratorPanel
 *         (Originaly Configurator) to separate non gui code.
 *
 */
@Deprecated
public class Configurator {
	private List<SolverSolution> products;
	private IntSolver solver;

	public Configurator() {
		// solver = new GNUPrologSolver(new GNUPrologContext());

		// initComponents();
	}

	/**
	 * @param pl
	 */
	// @SuppressWarnings("deprecation")
	// public void setSolverProductLine(ModelInstance pl) {
	// solver.setProductLine(pl);
	// }

	@SuppressWarnings("deprecation")
	public Map<String, List<Integer>> reduceDomain(SolverSolution configuration,
			ConfigurationOptionsDTO configOptions) {
		return solver.reduceDomain(configuration, configOptions);
	}

	public ArrayList<SolverSolution> solve(int numSol, SolverSolution config,
			ConfigurationOptionsDTO options) {
		ArrayList<SolverSolution> configurations = new ArrayList<SolverSolution>();
		config.debugPrint();

		solver.solve(config, options);
		int i = 0;

		if (!solver.hasNextSolution()) {
			System.out.println("No solutions found !!");
			return configurations;
		}
		while (solver.hasNextSolution() && i < numSol) {
			SolverSolution sol = solver.getSolution();
			configurations.add(sol);
			// solutionPanel.addSolution(sol);
			sol.debugPrint();
			i++;
		}
		// solutionPanel.expand();
		//
		return configurations;
	}

	public void addSolution(SolverSolution solution) {
		products.add(solution);
	}

	@SuppressWarnings("deprecation")
	public boolean validateInvalid() {
//		for (VariabilityElement e : ((ProductLine) solver.getProductLine())
//				.getVariabilityElements()) {
//			for (SolverSolution conf : products) {
//				if (conf.stateOf(e.getIdentifier()) == SolverSolution.ENFORCED)
//					return true;
//			}
//		}
		return false;
	}

	@Deprecated
	public ProductLine getSolverProductLine() {
//		return (ProductLine) solver.getProductLine();
		return null;
	}

	@Deprecated
	public void performConfiguration(SolverSolution configuration,
			ConfigurationOptionsDTO configOptions,
			ConfigurationTaskListener listener, ProductLine pl) {

		//GNUPrologContext ctx = new GNUPrologContext();
		//GNUPrologSolver solver = new GNUPrologSolver(ctx);
		//solver.setProductLine(pl);
		configuration.debugPrint();
		ConfigurationTask task = new ConfigurationTask(solver, configuration,
				configOptions, listener);
		new Thread(task).start();
	}

	/*
	 * private void addDomainAnnotations(ConfigurationNode c, List<Integer>
	 * newValues, int step) { List<Integer> oldValues =
	 * c.getVariable().getDomain().getPossibleValues();
	 * 
	 * List<DomainAnnotation> dm = c.getDomainAnnotations();
	 * 
	 * for(Integer i : oldValues ){
	 * 
	 * DomainAnnotation existing = c.getAnnotationFor(i); if( existing != null
	 * ){ if( existing.getChoice() == Choice.CROSS ) continue; }
	 * 
	 * if( !newValues.contains(i) && !dm.contains(i)) dm.add(new
	 * DomainAnnotation(i, Choice.CROSS, step)); }
	 * 
	 * }
	 */


}
