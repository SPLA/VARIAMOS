package com.variamos.defectAnalyzer.util;

import com.cfm.jgprolog.gnuprolog.GNUPrologContext;
import com.cfm.productline.solver.GNUPrologSolver;
import com.cfm.productline.solver.SWIPrologSolver;
import com.cfm.productline.solver.Solver;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.TechnicalException;

public class SolverOperationsUtil {

	private Solver solver;

	public SolverOperationsUtil() {
		super();
	}

	public SolverOperationsUtil(SolverEditorType prologEditorType) {

		if (prologEditorType.equals(SolverEditorType.SWI_PROLOG)) {
			solver = new SWIPrologSolver();
		} else if (prologEditorType.equals(SolverEditorType.GNU_PROLOG)) {
			solver = new GNUPrologSolver(new GNUPrologContext());

		}
	}

	public boolean isSatisfiable(String programPath) {
		return solver.isSatisfiable(programPath);

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
		/*
		 * boolean successfullLoad = cargaInicial(path); String t2 =
		 * CONSTRAINT_PROGRAM_INVOCATION; if (successfullLoad) { Query
		 * prologQuery = new Query(t2); // Se obtiene una solución Hashtable[]
		 * table = prologQuery.nSolutions(2); if (table.length >= 2) { // Si la
		 * longitud es > a 1 el programa de restricciones no es // void return
		 * false; } }
		 */
		return true;
	}

	private static boolean isFalseProductLineGNUProlog(String path) {
		// We read the file with input path and we identify its absolute
		// location
		// File file = new File(path);
		// String absolutePath = file.getAbsolutePath();
		// termFactory = ctx.getTermFactory();
		// int countSolutions = 0;
		//
		// if (!startPrologEngine) {
		// starPrologEngine();
		// }
		//
		// if (prolog != null && startPrologEngine) {
		// try {
		// prolog.consult(absolutePath);
		// } catch (PrologException e1) {
		// throw new TechnicalException(e1);
		// }
		// // Create a Variable L
		// VariableTerm L = termFactory.newVariable("L");
		//
		// // Create the compound productline(L)
		// CompoundTerm query = termFactory.newCompound("productline",
		// new Term[] { L });
		//
		// // Execute query
		// try (QueryResult qr = prolog.runQuery(query)) {
		//
		// while (qr.hasMoreSolutions()) {
		// countSolutions++;
		// qr.nextSolution();
		// if (countSolutions >= 2) {
		// return false;
		// }
		// }
		// return true;
		//
		// } catch (Exception e) {
		// throw new TechnicalException("Technical Exception");
		// }
		//
		// } else {
		// throw new TechnicalException("GNU Prolog is not started");
		//
		// }

		return false;
	}

}
