package com.variamos.configurator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cfm.jgprolog.gnuprolog.GNUPrologContext;
import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.variamos.configurator.io.ConfigurationDTO;
//import com.cfm.productline.configurator.DomainAnnotation;
//import com.cfm.productline.configurator.treetable.ConfigurationNode;
import com.variamos.solver.Configuration;
import com.variamos.solver.ConfigurationOptions;
import com.variamos.solver.ConfigurationTask;
import com.variamos.solver.ConfigurationTaskListener;
import com.variamos.solver.GNUPrologSolver;
import com.variamos.solver.Solver;

/**
 * @author unkwnown jcmunoz-diego: Splitted from class ConfiguratorPanel
 *         (Originaly Configurator) to separate non gui code.
 *
 */
public class Configurator {
	private List<Configuration> products;
	private Solver solver;

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
	public Map<String, List<Integer>> reduceDomain(Configuration configuration,
			ConfigurationOptions configOptions) {
		return solver.reduceDomain(configuration, configOptions);
	}

	public ArrayList<Configuration> solve(int numSol, Configuration config,
			ConfigurationOptions options) {
		ArrayList<Configuration> configurations = new ArrayList<Configuration>();
		config.debugPrint();

		solver.solve(config, options);
		int i = 0;

		if (!solver.hasNextSolution()) {
			System.out.println("No solutions found !!");
			return configurations;
		}
		while (solver.hasNextSolution() && i < numSol) {
			Configuration sol = solver.getSolution();
			configurations.add(sol);
			// solutionPanel.addSolution(sol);
			sol.debugPrint();
			i++;
		}
		// solutionPanel.expand();
		//
		return configurations;
	}

	public void addSolution(Configuration solution) {
		products.add(solution);
	}

	@SuppressWarnings("deprecation")
	public boolean validateInvalid() {
		for (VariabilityElement e : ((ProductLine) solver.getProductLine())
				.getVariabilityElements()) {
			for (Configuration conf : products) {
				if (conf.stateOf(e.getIdentifier()) == Configuration.ENFORCED)
					return true;
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public ProductLine getSolverProductLine() {
		return (ProductLine) solver.getProductLine();
	}

	public void performConfiguration(Configuration configuration,
			ConfigurationOptions configOptions,
			ConfigurationTaskListener listener, ProductLine pl) {

		GNUPrologContext ctx = new GNUPrologContext();
		GNUPrologSolver solver = new GNUPrologSolver(ctx);
		solver.setProductLine(pl);
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

	public ConfigurationDTO getConfigurationDTO(Configuration configuration,
			ConfigurationOptions configOptions) {
		ConfigurationDTO dto = new ConfigurationDTO();
		dto.setValues(configuration);
		dto.setOptions(configOptions);
		return dto;
	}

}
