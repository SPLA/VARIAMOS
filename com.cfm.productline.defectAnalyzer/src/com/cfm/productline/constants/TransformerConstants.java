package com.cfm.productline.constants;

public interface TransformerConstants {

	
	//Text
	public static final String TO = " y ";
	public static final String LOW_CARDINALITY = " cardialidad inferior";
	public static final String UPPER_CARDINALITY = " cardinalidad superior";
	public static final String DEPENDENCY = "Dependencia ";
	public static final String MODEL_ROOT = "Raíz del modelo: ";
	public static final String GROUP = "grupal ";
	public static final String MANDATORY = "obligatoria ";
	public static final String OPTIONAL = "opcional ";
	public static final String TRAVERSAL = "transversal ";
	public static final String BETWEEN = "entre ";
	
	
	// Valor que indica que una variable no fue asignada. Feature no fue seleccionda.
	public static final Integer NON_SELECTED_VALUE=0;
	//Instrucción que se inserta par saber donde se pueden insertar restricciones en el archivo prolog cuando se comience a hacer el análisis
	public static final String ADDING_ZONE="%%Addedconstraints\n";
}
