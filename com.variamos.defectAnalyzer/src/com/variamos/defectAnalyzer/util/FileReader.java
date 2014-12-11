package com.variamos.defectAnalyzer.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.variamos.constants.ConstraintSymbolsConstant;
import com.variamos.core.exceptions.TechnicalException;


/**
 * Read a file and returns the constraints list
 * 
 * @author Lufe
 * 
 */
public class FileReader {

	public static List<String> readConstraintsOfAFile(String filePath) {
		try {
			List<String> fileConstraintLines = new ArrayList<String>();
			FileInputStream fstream = new FileInputStream(filePath);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {

				String line = strLine.trim();

				// If the line contains the character that identifies
				// constraints in Prolog
				if (strLine
						.contains(ConstraintSymbolsConstant.CONSTRAINT_SEPARATOR)) {

					String[] constraintsLine = line
							.split(ConstraintSymbolsConstant.COMMA);
					if (constraintsLine.length == 1) {
						fileConstraintLines.add(constraintsLine[0]);
					} else if (constraintsLine.length > 1) {
						// If the line has more than one restriction, it is
						// divided in more lines in order to facilitate his
						// later analysis
						fileConstraintLines.addAll(Arrays
								.asList(constraintsLine));
					}
				}
			}
			// Close the input stream
			in.close();
			return fileConstraintLines;
		} catch (Exception e) {
			// Catch exception if any
			throw new TechnicalException(e);
		}

	}

	/**
	 * Lee un archivo y retorna el conjunto de líneas que lo componen a
	 * excepción de las líneas de comentarios que inician con el caracter de
	 * comentario
	 * 
	 * @param filePath
	 * @return
	 */
	public static  List<String> readStringProgram(String filePath) {

		try {
			List<String> fileConstraintLines = new ArrayList<String>();
			FileInputStream fstream = new FileInputStream(filePath);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {

				// If the line contains the character that identifies
				// constraints in Prolog
				if (!strLine.contains(ConstraintSymbolsConstant.COMMENT_SYMBOL)) {
					fileConstraintLines.add(strLine);
				}
			}
			// Close the input stream
			in.close();
			return fileConstraintLines;
		} catch (Exception e) {
			// Catch exception if any
			throw new TechnicalException(e);
		}

	}
	
	public static  String readStringProgramOneString(String filePath) {

		try {
			StringBuffer fileConstraint = new StringBuffer();
			FileInputStream fstream = new FileInputStream(filePath);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {

				// If the line contains the character that identifies
				// constraints in Prolog
				if (!strLine.contains(ConstraintSymbolsConstant.COMMENT_SYMBOL)) {
					fileConstraint.append(strLine);
					fileConstraint.append("\n");
				}
			}
			// Close the input stream
			in.close();
			return fileConstraint.toString();
		} catch (Exception e) {
			// Catch exception if any
			throw new TechnicalException(e);
		}

	}
	
	public static String replaceStringInString(String bigString, String stringToAdd,
			String stringToReeplace) {
		if (bigString.contains(stringToReeplace)) {

			return bigString
					.replace(stringToReeplace, stringToAdd);
		}
		return bigString;
	}
}
