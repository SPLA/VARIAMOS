package com.variamos.pl.configurator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map; 

import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.cfm.common.AbstractModel;
import com.cfm.jgprolog.gnuprolog.GNUPrologContext;
//import com.cfm.productline.configurator.DomainAnnotation;
//import com.cfm.productline.configurator.treetable.ConfigurationNode;
import com.cfm.productline.solver.Configuration;
import com.cfm.productline.solver.ConfigurationOptions;
import com.cfm.productline.solver.ConfigurationTask;
import com.cfm.productline.solver.ConfigurationTaskListener;
import com.cfm.productline.solver.GNUPrologSolver;
import com.cfm.productline.solver.Solver;
import com.variamos.pl.configurator.io.ConfigurationDTO;


/**
 * @author unkwnown
 * jcmunoz-diego:	Splitted from class ConfiguratorPanel (Originaly Configurator) to separate non gui code.
 *
 */
public class Configurator  {
	private List<Configuration> products;
	private Solver solver;	
	public Configurator(){
		solver = new GNUPrologSolver(new GNUPrologContext());
		
		//initComponents();
	}

	/**
	 * @param pl
	 */
	public void setSolverProductLine(AbstractModel pl)
	{
		solver.setProductLine(pl);
	}

	public Map<String, List<Integer>>  reduceDomain(Configuration configuration, ConfigurationOptions configOptions)
	{
		return solver.reduceDomain(configuration, configOptions);
	}

	public ArrayList <Configuration> solve(int numSol, Configuration config, ConfigurationOptions options){
		ArrayList <Configuration>  configurations = new  ArrayList <Configuration>();		
		config.debugPrint();
		
		solver.solve(config, options);
		int i = 0;
		
		if( !solver.hasNextSolution() ){
			System.out.println("No solutions found !!");
			return configurations;
		}
		while( solver.hasNextSolution() && i < numSol){
			Configuration sol = solver.getSolution();
			configurations.add(sol);
		//	solutionPanel.addSolution(sol);
			sol.debugPrint();
			i++;
		}
		//solutionPanel.expand();
		//
		return configurations;
	}

	

	
	public void addSolution(Configuration solution) {
		products.add(solution);
	}

	public boolean validateInvalid() {
		for(VariabilityElement e : ((ProductLine)solver.getProductLine()).getVariabilityElements()){
			for(Configuration conf : products ){
				if( conf.stateOf(e.getIdentifier()) == Configuration.ENFORCED )
					return true;			
				}
			}
		return false;
	}
	
	
	public ProductLine getSolverProductLine() {
		return (ProductLine)solver.getProductLine();
	}



	public void performConfiguration(Configuration configuration, ConfigurationOptions configOptions, ConfigurationTaskListener listener, AbstractModel pl) {
			
		GNUPrologContext ctx = new GNUPrologContext();
		GNUPrologSolver solver = new GNUPrologSolver(ctx);
		solver.setProductLine(pl);
		configuration.debugPrint();
		ConfigurationTask task = new ConfigurationTask(solver, configuration, configOptions, listener);
		new Thread(task).start();
	}

/*	private void addDomainAnnotations(ConfigurationNode c, List<Integer> newValues, int step) {
		List<Integer> oldValues = c.getVariable().getDomain().getPossibleValues();
		
		List<DomainAnnotation> dm = c.getDomainAnnotations();
		
		for(Integer i : oldValues ){
			
			DomainAnnotation existing = c.getAnnotationFor(i);
			if( existing != null ){
				if( existing.getChoice() == Choice.CROSS )
					continue;
			}
			
			if( !newValues.contains(i) && !dm.contains(i))
				dm.add(new DomainAnnotation(i, Choice.CROSS, step));
		}
		
	}*/



	public ConfigurationDTO getConfigurationDTO(Configuration configuration, ConfigurationOptions configOptions)
	 {
		ConfigurationDTO dto = new ConfigurationDTO();
		dto.setValues( configuration );
		dto.setOptions( configOptions );
		return dto;
	}
	
	
}
