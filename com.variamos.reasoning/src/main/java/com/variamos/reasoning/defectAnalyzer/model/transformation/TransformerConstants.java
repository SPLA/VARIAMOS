package com.variamos.reasoning.defectAnalyzer.model.transformation;

public interface TransformerConstants {

	
	//Text
	public static final String TO = " y ";
	public static final String LOW_CARDINALITY = " lower cardinality";
	public static final String UPPER_CARDINALITY = " upper cardinality";
	public static final String DEPENDENCY = "Dependency ";
	public static final String MODEL_ROOT = "Model root: ";
	public static final String GROUP = "groupal ";
	public static final String MANDATORY = "mandatory ";
	public static final String OPTIONAL = "optional ";
	public static final String TRAVERSAL = "traversal ";
	public static final String BETWEEN = "between ";
	
	
	// Valor que indica que una variable no fue asignada. Feature no fue seleccionda.
	public static final Integer NON_SELECTED_VALUE=0;

	public static final int ONE = 1;
	public static final int ZERO = 0;
	public static final String SPACE=" ";
	
	public static final String OPEN_BRACKET=" [";
	public static final String CLOSE_BRACKET="] ";
}
