package com.variamos.solver.model.compiler;

public interface SWIPrologSymbolsConstant {

	// OPERATION CONSTANTS FOR SWI PROLOG
	public static final String EQUIVALENT = "#<==>";
	public static final String SWI_CONSTRAINTS_SYMBOLS[] = { "#<==>" };

	// HEADER FOR GNU PROLOG
	public static final String HEADER = ":-use_module(library(clpfd)).\nproductline(L):-\n";

	public static final String HEADER_INI = ":-use_module(library(clpfd)).\nproductline(";
	public static final String HEADER_END = "):-\n";

	public static final String LABELING = "labeling";
	public static final String ONCE = "once";
	public static final String FF = "ff";
	public static final String MIN = "min";
	public static final String MAX = "max";
	public static final String INVOCATION = "L";

	public static final String IN = " in ";
	public static final String INS = " ins";
	public static final String DOMAIN_INTERVAL = "..";
	public static final String ORDOMAIN = "\\/";

}
