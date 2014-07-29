package com.cfm.productline.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import jpl.Compound;
import jpl.Query;
import jpl.Variable;

import com.cfm.jgprolog.core.CompoundTerm;
import com.cfm.jgprolog.core.IntegerTerm;
import com.cfm.jgprolog.core.ListTerm;
import com.cfm.jgprolog.core.PrologContext;
import com.cfm.jgprolog.core.PrologEngine;
import com.cfm.jgprolog.core.PrologException;
import com.cfm.jgprolog.core.PrologTermFactory;
import com.cfm.jgprolog.core.QueryResult;
import com.cfm.jgprolog.core.Term;
import com.cfm.jgprolog.core.VariableTerm;
import com.cfm.jgprolog.gnuprolog.GNUPrologContext;
import com.cfm.productline.exceptions.TechnicalException;
import com.cfm.productline.model.enums.SolverEditorType;

public class SolverOperationsUtil {

	// Variables para usar el GNU Prolog Engine
	private static PrologEngine prolog = null;
	private static boolean startPrologEngine = Boolean.FALSE;
	private static PrologTermFactory termFactory = null;
	private static PrologContext ctx = null;

	// Variables para cargarlo por SWI Prolog
	public final static String CONSTRAINT_PROGRAM_INVOCATION = "productline(L)";

	public static boolean isSatisfiable(String path,
			SolverEditorType prologEditorType) {

		if (prologEditorType.equals(SolverEditorType.SWI_PROLOG)) {
			return isSatisfiableSWIProlog(path);
		} else if (prologEditorType.equals(SolverEditorType.GNU_PROLOG)) {
			return isSatisfiableGNUProlog(path);
		}
		throw new TechnicalException("Undefined Prolog Editor");

	}

	/**
	 * Genera una solución para el programa en Prolog y retorna una lista con
	 * los valores asignados a cada variable de la soluciónF
	 * 
	 * @return
	 */

	public static List<List<Integer>> getSelectedVariablesByConfigurations(
			String path, int numberOfConfigurations,
			SolverEditorType prologEditorType) {

		List<List<Integer>> allConfigurationValues = new ArrayList<List<Integer>>();
		if (prologEditorType.equals(SolverEditorType.SWI_PROLOG)) {

			List<Hashtable> configurationList = getConfigurationsSWI(path,
					numberOfConfigurations);

			if (!configurationList.isEmpty()) {
				for (Hashtable configuration : configurationList) {
					if (configuration != null) {
						List<Integer> values = new ArrayList<Integer>();
						Compound configurationCompound = (Compound) configuration
								.get("L");
						values = getSolutionValues(configurationCompound,
								values);
						allConfigurationValues.add(values);

					}
				}
			}
		} else if (prologEditorType.equals(SolverEditorType.GNU_PROLOG)) {

			allConfigurationValues = getConfigurationsGNUValues(path,
					numberOfConfigurations);
		}
		return allConfigurationValues;

	}

	public static List<List<Integer>> getAllConfigurations(String path, SolverEditorType prologEditorType) {

		List<List<Integer>> allConfigurationValues = new ArrayList<List<Integer>>();
		if (prologEditorType.equals(SolverEditorType.SWI_PROLOG)) {

			List<Hashtable> configurationList = getAllConfigurationsSWI(path);

			if (!configurationList.isEmpty()) {
				for (Hashtable configuration : configurationList) {
					if (configuration != null) {
						List<Integer> values = new ArrayList<Integer>();
						Compound configurationCompound = (Compound) configuration
								.get("L");
						values = getSolutionValues(configurationCompound,
								values);
						allConfigurationValues.add(values);

					}
				}
			}
		} else if (prologEditorType.equals(SolverEditorType.GNU_PROLOG)) {

			allConfigurationValues = getAllConfigurationsGNU(path);
		}
		return allConfigurationValues;

	}

	private static List<Integer> getSolutionValues(Compound compound,
			List<Integer> values) {
		jpl.Term[] terms = compound.args();

		if (terms[1].isAtom()) {
			Integer valueInteger = terms[0].intValue();
			values.add(valueInteger);
			return values;
		} else {
			Integer valueInteger = terms[0].intValue();
			Compound compoundValues = (Compound) terms[1];
			values.add(valueInteger);
			getSolutionValues(compoundValues, values);

		}

		return values;
	}

	private static List<Hashtable> getConfigurationsSWI(String path,
			int numberOfSolutions) {
		boolean successfullLoad = cargaInicial(path);
		String t2 = CONSTRAINT_PROGRAM_INVOCATION;
		List<Hashtable> configurationsList = new ArrayList<Hashtable>();
		if (numberOfSolutions > 0) {
			if (successfullLoad) {
				Query prologQuery = new Query(t2);
				if (prologQuery.hasSolution()) {
					Variable L = new Variable();
					Hashtable[] configurations = prologQuery
							.nSolutions(numberOfSolutions);
					for (Hashtable configuration : configurations) {
						configurationsList.add(configuration);
					}
				}

			}
		}
		return configurationsList;
	}

	private static List<Hashtable> getAllConfigurationsSWI(String path) {
		boolean successfullLoad = cargaInicial(path);
		String t2 = CONSTRAINT_PROGRAM_INVOCATION;
		List<Hashtable> configurationsList = new ArrayList<Hashtable>();
		if (successfullLoad) {
			Query prologQuery = new Query(t2);
			if (prologQuery.hasSolution()) {
				Variable L = new Variable();
				Hashtable[] configurations = prologQuery
						.allSolutions();
				for (Hashtable configuration : configurations) {
					configurationsList.add(configuration);
				}
			}

		}

		return configurationsList;
	}

	private static List<List<Integer>> getConfigurationsGNUValues(String path,
			int numberOfSolutions) {

		List<List<Integer>> allConfigurationValues = new ArrayList<List<Integer>>();
		// We read the file with input path and we identify its absolute
		// location
		File file = new File(path);
		String absolutePath = file.getAbsolutePath();
		termFactory = ctx.getTermFactory();
		int solutionsCount = 0;

		if (!startPrologEngine) {
			starPrologEngine();
		}
		if (prolog != null && startPrologEngine) {
			try {
				prolog.consult(absolutePath);
			} catch (PrologException e1) {
				throw new TechnicalException(e1);
			}
			// Create a Variable L
			VariableTerm L = termFactory.newVariable("L");

			// Create the compound productline(L)
			CompoundTerm query = termFactory.newCompound("productline",
					new Term[] { L });

			// Execute query
			try (QueryResult qr = prolog.runQuery(query)) {

				while (qr.hasMoreSolutions()
						&& solutionsCount < numberOfSolutions) {
					List<Integer> values = new ArrayList<Integer>();
					ListTerm termsList = (ListTerm) L.getValue();
					Term[] allTerms = termsList.getAllTerms();
					for (int i = 0; i < allTerms.length; i++) {
						if (allTerms[i] instanceof IntegerTerm) {
							int value = ((IntegerTerm) allTerms[i]).getValue();
							values.add(value);
						} else {
							throw new TechnicalException("Unknown return value");
						}

					}
					allConfigurationValues.add(values);
					solutionsCount++;
					qr.nextSolution();
				}

			} catch (Exception e) {
				throw new TechnicalException("Technical Exception", e);
			}

		} else {
			throw new TechnicalException("GNU Prolog is not started");

		}

		return allConfigurationValues;
	}
	
	
	private static List<List<Integer>> getAllConfigurationsGNU(String path) {

		List<List<Integer>> allConfigurationValues = new ArrayList<List<Integer>>();
		// We read the file with input path and we identify its absolute
		// location
		File file = new File(path);
		String absolutePath = file.getAbsolutePath();
		termFactory = ctx.getTermFactory();

		if (!startPrologEngine) {
			starPrologEngine();
		}
		if (prolog != null && startPrologEngine) {
			try {
				prolog.consult(absolutePath);
			} catch (PrologException e1) {
				throw new TechnicalException(e1);
			}
			// Create a Variable L
			VariableTerm L = termFactory.newVariable("L");

			// Create the compound productline(L)
			CompoundTerm query = termFactory.newCompound("productline",
					new Term[] { L });

			// Execute query
			try (QueryResult qr = prolog.runQuery(query)) {

				while (qr.hasMoreSolutions()) {
					List<Integer> values = new ArrayList<Integer>();
					ListTerm termsList = (ListTerm) L.getValue();
					Term[] allTerms = termsList.getAllTerms();
					for (int i = 0; i < allTerms.length; i++) {
						if (allTerms[i] instanceof IntegerTerm) {
							int value = ((IntegerTerm) allTerms[i]).getValue();
							values.add(value);
						} else {
							throw new TechnicalException("Unknown return value");
						}

					}
					allConfigurationValues.add(values);
					qr.nextSolution();
				}

			} catch (Exception e) {
				throw new TechnicalException("Technical Exception", e);
			}

		} else {
			throw new TechnicalException("GNU Prolog is not started");

		}

		return allConfigurationValues;
	}

	/**
	 * This operation takes a PLM as input and returns true if at most one valid
	 * product can be configured with it. Although
	 * 
	 * @param path
	 * @param prologEditorType
	 * @return
	 */
	public static boolean isFalseProductLine(String path,
			SolverEditorType prologEditorType) {

		if (prologEditorType.equals(SolverEditorType.SWI_PROLOG)) {
			return isFalseProductLineSWIProlog(path);
		} else if (prologEditorType.equals(SolverEditorType.GNU_PROLOG)) {
			return isFalseProductLineGNUProlog(path);
		}
		throw new TechnicalException("Undefined Prolog Editor");

	}

	private static boolean isFalseProductLineSWIProlog(String path) {
		boolean successfullLoad = cargaInicial(path);
		String t2 = CONSTRAINT_PROGRAM_INVOCATION;
		if (successfullLoad) {
			Query prologQuery = new Query(t2);
			// Se obtiene una solución
			Hashtable[] table = prologQuery.nSolutions(2);
			if (table.length >= 2) {
				// Si la longitud es > a 1 el programa de restricciones no es
				// void
				return false;
			}
		}
		return true;
	}

	private static boolean isFalseProductLineGNUProlog(String path) {
		// We read the file with input path and we identify its absolute
		// location
		File file = new File(path);
		String absolutePath = file.getAbsolutePath();
		termFactory = ctx.getTermFactory();
		int countSolutions = 0;

		if (!startPrologEngine) {
			starPrologEngine();
		}

		if (prolog != null && startPrologEngine) {
			try {
				prolog.consult(absolutePath);
			} catch (PrologException e1) {
				throw new TechnicalException(e1);
			}
			// Create a Variable L
			VariableTerm L = termFactory.newVariable("L");

			// Create the compound productline(L)
			CompoundTerm query = termFactory.newCompound("productline",
					new Term[] { L });

			// Execute query
			try (QueryResult qr = prolog.runQuery(query)) {

				while (qr.hasMoreSolutions()) {
					countSolutions++;
					qr.nextSolution();
					if (countSolutions >= 2) {
						return false;
					}
				}
				return true;

			} catch (Exception e) {
				throw new TechnicalException("Technical Exception");
			}

		} else {
			throw new TechnicalException("GNU Prolog is not started");

		}

	}

	/**
	 * Verifica si un programa expresado en constraint programming en prolog es
	 * o no satisfacible
	 * 
	 * @param path
	 * @return
	 */
	private static boolean isSatisfiableSWIProlog(String path) {
		boolean successfullLoad = cargaInicial(path);
		String t2 = CONSTRAINT_PROGRAM_INVOCATION;
		if (successfullLoad) {
			Query prologQuery = new Query(t2);
			// Se obtiene una solución
			Hashtable[] table = prologQuery.nSolutions(1);
			if (table.length >= 1) {
				// Si la longitud es > a 1 el programa de restricciones no es
				// void
				return true;
			}
		}
		return false;
	}

	private static boolean isSatisfiableGNUProlog(String path) {
		// We read the file with input path and we identify its absolute
		// location
		File file = new File(path);
		String absolutePath = file.getAbsolutePath();
		boolean isSatisfiable = Boolean.FALSE;
		termFactory = ctx.getTermFactory();

		if (!startPrologEngine) {
			starPrologEngine();
		}

		if (prolog != null && startPrologEngine) {
			try {
				prolog.consult(absolutePath);
			} catch (PrologException e1) {
				throw new TechnicalException(e1);
			}
			// Create a Variable L
			VariableTerm L = termFactory.newVariable("L");

			// Create the compound productline(L)
			CompoundTerm query = termFactory.newCompound("productline",
					new Term[] { L });

			// Execute query
			try (QueryResult qr = prolog.runQuery(query)) {

				if (qr.hasMoreSolutions()) {
					isSatisfiable = qr.hasMoreSolutions();
					return isSatisfiable;
				}

			} catch (Exception e) {
				throw new TechnicalException("Technical Exception", e);
			}

		} else {
			throw new TechnicalException("GNU Prolog is not started");

		}
		return false;

	}

	private static boolean cargaInicial(String program) {
		//To solve out of global stack
		jpl.fli.Prolog.set_default_init_args(new String[] {"pl", "-nosignals", "-L128m", "-G128m"}); 
		Query q1 = new Query("consult('" + program + "')");
		return q1.hasSolution();
	}

	public static void starPrologEngine() {
		// Using the GNUProlog Context
		if (!startPrologEngine) {
			ctx = new GNUPrologContext();

			// Get the Prolog Engine
			prolog = ctx.getEngine();

			// Start Prolog
			prolog.startProlog();

			startPrologEngine = Boolean.TRUE;
		}
	}

	public static void stopProlog() {

		if (prolog != null && startPrologEngine) {
			// Stop the engine
			prolog.stopProlog();
			startPrologEngine = Boolean.FALSE;

		}
	}

	/**
	 * @return the startPrologEngine
	 */
	public static boolean isStartPrologEngine() {
		return startPrologEngine;
	}

}
