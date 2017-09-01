package com.variamos.hlcl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiteralExpressionUtil {

	private static String GENERIC_CONSTRAINTS_SYMBOLS[] = { "#=", "#\\=", "+",
			"*", "-", "#>=", "#=<", "#>", "#<", "#==>", "#/\\", "#\\/", "#\\ ",
			"#<=>", "##", "#\\==>", "#\\/\\", "#\\/", "#<==>" };

	private static final String RESERVED_CONSTRAINTS_SYMBOLS[] = { "(", ")",
			",", "%", ".","]","[" };

	/**
	 * @param character
	 *            to analyze
	 * @return true: the character is a operation character
	 */
	private static boolean isOperationCharacter(CharSequence character) {

		for (String operator : getOperatorCharacters()) {
			if (operator.contains(character)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * @param character
	 *            to analyze
	 * @return true: the character is a reserved symbol
	 */
	private static boolean isReservedSymbol(CharSequence character) {

		for (String operator : getReservedSymbols()) {
			if (operator.contains(character)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return List with operator symbols
	 */
	private static List<String> getOperatorCharacters() {
		List<String> operatorCharacters = new ArrayList<String>();
		for (String operation : Arrays.asList(GENERIC_CONSTRAINTS_SYMBOLS)) {
			operatorCharacters.add(operation);
		}
		return operatorCharacters;
	}

	/**
	 * @return List with reserved symbols
	 */
	private static List<String> getReservedSymbols() {
		List<String> reservedSymbols = Arrays
				.asList(RESERVED_CONSTRAINTS_SYMBOLS);
		return reservedSymbols;
	}

	/**
	 * Extract the variables of a given line
	 * 
	 * @param line
	 * @return List of all variables. One or more occurrences are possibles
	 */
	public static List<String> findVariablesByLine(String line) {

		List<String> variableList = new ArrayList<String>();
		//Add this character to know the condition to stop
		line=line.concat(" ");
		int countVariableName = 0;
		Character character = null;
		int lengthLine = line.length();
		for (int i = 0; i < lengthLine; i++) {

			character = line.charAt(i);
			if (!Character.isWhitespace(character) 
					&& !isOperationCharacter(line.subSequence(i, i + 1))
					&& !isReservedSymbol(line.subSequence(i, i + 1))) {
				countVariableName++;
			} else {

				if (countVariableName > 0) {
					int startPosition = i - countVariableName;
					// This validation is used to avoid index out bound
					// exception
					if (startPosition <= 0) {
						startPosition = 0;
					}
					String variableName = line.substring(startPosition, i);
					// The first character must be start with _,or upper case
					if (variableName.startsWith("_")
							|| Character.isUpperCase(variableName.charAt(0))) {
						if (!Character.isDigit(variableName.charAt(0))) {
							variableList.add(variableName);
						}
					}

					countVariableName = 0;
				}
			}
		}

		return variableList;
	}
}