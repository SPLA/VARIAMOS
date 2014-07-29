package com.cfm.productline.defectAnalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cfm.productline.constants.ConstraintSymbolsConstant;
import com.cfm.productline.constants.GNUPrologConstraintSymbolsConstant;
import com.cfm.productline.constants.SWIPrologConstraintSymbolsConstant;
import com.cfm.productline.model.enums.SolverEditorType;
import com.cfm.productline.util.FileReader;
@SuppressWarnings("rawtypes")
public class SyntactConstraintProgramAnalyer {

	SolverEditorType prologEditor = null;

	public SyntactConstraintProgramAnalyer(SolverEditorType prologEditor) {
		this.prologEditor = prologEditor;
	}

	public void analyzeProgram(String filePath) {

		List<String> allVariablesList = new ArrayList<String>();
		List<String> variableList = new ArrayList<String>();
		List<String> mostRelatedVariable = new ArrayList<String>();
		Map<String, Long> allVariablesMap = new HashMap<String, Long>();

		// This line reads the prolog file and returns only lines that contains
		// variables in the program
		List<String> fileConstraintLines = FileReader
				.readConstraintsOfAFile(filePath);
		int maxOccurCount = 1;

		for (String constraintLine : fileConstraintLines) {
			// x Cada línea se obtienen las variables de la línea
			if (!constraintLine
					.startsWith(GNUPrologConstraintSymbolsConstant.COMMENT_SYMBOL)) {
				variableList = findVariablesByLine(constraintLine);
				if (variableList.size() > 0) {
					allVariablesList.addAll(variableList);
				}
			}
		}

		// Count for each variable its occurrence. All numeric variables are
		// removed.
		for (String variable : allVariablesList) {
			Character character = variable.charAt(0);
			// The map doesn't have the variable and this variable doesn't begin with
			// a number
			if (!allVariablesMap.containsKey(variable)
					&& !Character.isDigit(character)) {
				// Putting the variable in the map and adding one in the
				// occurrence count
				allVariablesMap.put(variable, 1L);

			} else if (allVariablesMap.containsKey(variable)) {
				Long variableOccurenceCount = allVariablesMap.get(variable);
				variableOccurenceCount++;

				// Saving the highest number that counts the occurrences.
				if (variableOccurenceCount > maxOccurCount) {
					maxOccurCount = variableOccurenceCount.intValue();
				}
				allVariablesMap.put(variable, variableOccurenceCount);

			}

		}

		// Iterates the map to identify the most constrained variable

		Iterator<Entry<String, Long>> it = allVariablesMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			
			Map.Entry e = (Map.Entry) it.next();
			Long occurrencesValue = (Long) e.getValue();
			if (occurrencesValue == maxOccurCount) {
				System.out.println("Variable with more occurrences "
						+ (e.getKey() + " number of occurrences " + e
								.getValue()));

				mostRelatedVariable.add((String) e.getKey());
			}
		}

		// Only test: Print the variable's list
		for (String constraintLine : allVariablesMap.keySet()) {
			System.out.println(constraintLine);
		}

		System.out.println("Sorted Map......");
		Map<String, Long> sortedMap = sortByComparator(allVariablesMap);

		for (Map.Entry entry : sortedMap.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : "
					+ entry.getValue());
		}
	}

	/**
	 * Ordena un mapa
	 * 
	 * @param unsortMap
	 * @return
	 */
	private static Map<String, Long> sortByComparator(Map unsortMap) {

		List list = new LinkedList<Object>(unsortMap.entrySet());

		// sort list based on comparator
		Collections.sort(list, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
						.compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		// put sorted list into map again
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	/**
	 * Extract the variables of a given line
	 * 
	 * @param line
	 * @return List of all variables. One or more occurrences are possibles
	 */
	private List<String> findVariablesByLine(String line) {

		List<String> variableList = new ArrayList<String>();
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
					variableList.add(line.substring(startPosition, i));
					countVariableName = 0;
				}
			}
		}

		return variableList;
	}

	/**
	 * @param character
	 *            to analyze
	 * @return true: the character is a operation character
	 */
	private boolean isOperationCharacter(CharSequence character) {

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
	private boolean isReservedSymbol(CharSequence character) {

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
	private List<String> getOperatorCharacters() {
		List<String> operatorCharacters = new ArrayList<String>();
		for (String operation : Arrays
				.asList(ConstraintSymbolsConstant.GENERIC_CONSTRAINTS_SYMBOLS)) {
			operatorCharacters.add(operation);
		}
		if (prologEditor.equals(SolverEditorType.SWI_PROLOG)) {
			List<String> SWIPrologOperations = Arrays
					.asList(SWIPrologConstraintSymbolsConstant.SWI_CONSTRAINTS_SYMBOLS);
			for (String SWIOperation : SWIPrologOperations) {
				operatorCharacters.add(SWIOperation);
			}

		}
		return operatorCharacters;
	}

	/**
	 * @return List with reserved symbols
	 */
	private static List<String> getReservedSymbols() {
		List<String> reservedSymbols = Arrays
				.asList(ConstraintSymbolsConstant.RESERVED_CONSTRAINTS_SYMBOLS);
		return reservedSymbols;
	}

}
