package com.cfm.productline.compiler.solverSymbols;

public interface GNUPrologSymbols  {


	public static final String GNU_CONSTRAINTS_SYMBOLS[] = { "#<=>", "#<=>",
		"##", "#\\==>", "#\\/\\", "#\\/"}; 
	
	//OPERATION CONSTANTS FOR GNU PROLOG
	public static final String EQUIVALENT="#<=>";
	public static final String NOT_EQUIVALENT="#\\<=>";
	public static final String NOT_EQUIVALENT_2="##";
	public static final String NOT_IMPLIES="#\\==>";
	public static final String NAND="#\\/\\";
	public static final String NOR="#\\/";
	
	// HEADER FOR GNU PROLOG 
	public static final String HEADER="productline(L):-\n";
	
	//END FILE
	public static final String FD_LABELING = "fd_labeling(L)";
	public static final String POINT = ".";
	public static final String END = FD_LABELING + POINT;
	
	public static final String FD_DOMAIN="fd_domain";

}
