package com.variamos.configurator;

import java.util.Map;

import com.cfm.productline.ProductLine;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.solver.core.compiler.Hlcl2GnuPrologExact;
import com.variamos.solver.core.compiler.PrologTransformParameters;
@Deprecated
public class ConfiguratorUtils {

	@Deprecated
	public static void reduceDomain(
			Map<String, Object/* ConfigurationNode */> nodes, ProductLine pl) {

		//PrologContext ctx = new GNUPrologContext();

		//PrologEngine prolog = ctx.getEngine();
		// PrologTermFactory ptf = ctx.getTermFactory();

		HlclProgram prog = null;
		Hlcl2GnuPrologExact t = new Hlcl2GnuPrologExact();

		PrologTransformParameters params = new PrologTransformParameters();
		params.setFdLabeling(false);
		String code = t.transform(prog, params);

		/*try {
			GNUPrologSolver.consultString(prolog, code);
		} catch (PrologException e) {
			ConsoleTextArea.addText(e.getStackTrace());
		}*/

		// Query the pl.

	}
}
