package com.variamos.configurator;

import java.util.Map;

import com.cfm.jgprolog.core.PrologContext;
import com.cfm.jgprolog.core.PrologEngine;
import com.cfm.jgprolog.core.PrologException;
//import com.cfm.jgprolog.core.PrologTermFactory;
import com.cfm.jgprolog.gnuprolog.GNUPrologContext;
import com.cfm.productline.ProductLine;
import com.variamos.compiler.prologEditors.Hlcl2GnuPrologExact;
import com.variamos.compiler.prologEditors.PrologTransformParameters;
import com.variamos.hlcl.HlclProgram;
import com.variamos.io.ConsoleTextArea;
import com.variamos.productline.Pl2Hlcl;
import com.variamos.solver.GNUPrologSolver;

public class ConfiguratorUtils {

	public static void reduceDomain(
			Map<String, Object/* ConfigurationNode */> nodes, ProductLine pl) {

		PrologContext ctx = new GNUPrologContext();

		PrologEngine prolog = ctx.getEngine();
		// PrologTermFactory ptf = ctx.getTermFactory();

		HlclProgram prog = Pl2Hlcl.transformExact(pl);
		Hlcl2GnuPrologExact t = new Hlcl2GnuPrologExact();

		PrologTransformParameters params = new PrologTransformParameters();
		params.setFdLabeling(false);
		String code = t.transform(prog, params);

		try {
			GNUPrologSolver.consultString(prolog, code);
		} catch (PrologException e) {
			ConsoleTextArea.addText(e.getStackTrace());
		}

		// Query the pl.

	}
}
