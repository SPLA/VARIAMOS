package com.variamos.compiler.solverSymbols;

public interface ConstraintSymbols {

	public static final String CONSTRAINT_SEPARATOR = "#";

	// In order to escape the slash symbol, it is necessary duplicate the slash
	// public
	public static final String GENERIC_CONSTRAINTS_SYMBOLS[] = { "#=", "#\\=",
			"+", "*", "-", "#>=", "#=<", "#>", "#<", "#==>", "#/\\", "#\\/",
			"#\\ " };
	public static final String RESERVED_CONSTRAINTS_SYMBOLS[] = { "(", ")",
			",", "%", "." };

	// RESERVED SYMBOLS
	public static final String OPEN_PARENTHESIS = "(";
	public static final String CLOSE_PARENHESIS = ")";
	public static final String COMMA = ",";
	public static final String COMMENT_SYMBOL = "%";
	public static final String DOT = ".";
	public static final String ASSIGN_VARIABLE = "=";
	public static final String FUNCTION_DECLARATION = ":-";
	
	// OPERATION CONSTANTS
	public static final String EQUALS = "#=";
	// In order to escape the slash symbo\l, it is necessary duplicate the slash
	// public
	public static final String NOT_EQUALS = "#\\=";
	public static final String PLUS = "+";
	public static final String MULTIPLY = "*";
	public static final String SUBSTRACTION = "-";
	public static final String MORE_OR_EQUALS = "#>=";
	public static final String LESS_OR_EQUALS = "#=<";
	public static final String MORE = "#>";
	public static final String LESS = "#<";
	public static final String IMPLIES = "#==>";
	public static final String ASSIGN = "#=";

	// In order to escape the slash symbo\l, it is necessary duplicate the slash
	public static final String AND = "#/\\";
	public static final String OR = "#\\/";
	public static final String NOT = "#\\ ";
	
	//Line Feed
	public static final String LF = "\n";
	
	public static final int ONE = 1;
	public static final int ZERO = 0;
	public static final String SPACE=" ";
	
	public static final String OPEN_BRACKET=" [";
	public static final String CLOSE_BRACKET="] ";
	

}
