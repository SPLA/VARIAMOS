package com.variamos.defectAnalyzer.transformer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cfm.hlcl.Domain;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.IntervalDomain;
import com.cfm.hlcl.LiteralBooleanExpression;
import com.cfm.hlcl.LiteralExpressionUtil;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.TechnicalException;
import com.variamos.core.exceptions.TransformerException;
import com.variamos.defectAnalyzer.constants.ConstraintSymbolsConstant;
import com.variamos.defectAnalyzer.constants.GNUPrologConstraintSymbolsConstant;
import com.variamos.defectAnalyzer.dto.VMTransformerInDTO;
import com.variamos.defectAnalyzer.model.DefectAnalyzerDomain;
import com.variamos.defectAnalyzer.model.Dependency;
import com.variamos.defectAnalyzer.model.IntervalDomainDefectAnalyzer;
import com.variamos.defectAnalyzer.model.RangeDomainDefectAnalyzer;
import com.variamos.defectAnalyzer.model.VariabilityElement;
import com.variamos.defectAnalyzer.model.VariabilityModel;

public class BooleanPrologTransformer implements ITransformer {

	private Long constraintCounter;

	private Set<String> allVariablesList;
	private VariabilityModel variabilityModel;
	private Map<String, VariabilityElement> variabilityElementMap;
	private Map<Long, Dependency> variabilityDependenciesMap;
	private List<String> domainList;
	private List<String> constraintList;

	private void init(VMTransformerInDTO inDTO) {

		constraintCounter = 0L;
		variabilityElementMap = new HashMap<String, VariabilityElement>();
		variabilityDependenciesMap = new HashMap<Long, Dependency>();
		allVariablesList = new HashSet<String>();
		domainList = new ArrayList<String>();
		constraintList = new ArrayList<String>();
	}

	public void createConstraintsPrologProgram() {

		List<String> variableList = new ArrayList<String>();
		List<Identifier> identifierExpressionList = new ArrayList<Identifier>();
		Map<String, Identifier> identifierExpressionMap = new HashMap<String, Identifier>();

		for (String constraint : constraintList) {

			// Se identifican las variables de esta constraint
			variableList = LiteralExpressionUtil
					.findVariablesByLine(constraint);

			// Se crean los identificadores de la constraint de acuerdo a las
			// variables que tiene ( Para que los identificadores de los
			// expressions tengan el mismo dominio)
			identifierExpressionList = createIdentifiersByVariables(
					variableList, identifierExpressionMap);

			// Se adiciona la dependencia
			Dependency variabilityDependency = new Dependency(constraint,
					constraintCounter);
			LiteralBooleanExpression constraintExpression = new LiteralBooleanExpression(
					constraint);

			constraintExpression
					.setIdentifierExpressionList(identifierExpressionList);

			variabilityDependency.setConstraintExpression(constraintExpression);
			variabilityDependenciesMap.put(constraintCounter,
					variabilityDependency);
			constraintCounter++;

		}

	}

	private List<Identifier> createIdentifiersByVariables(
			List<String> variableList,
			Map<String, Identifier> identifierExpressionMap) {

		List<Identifier> identifierExpressionList = new ArrayList<Identifier>();
		for (String variableName : variableList) {

			if (variabilityElementMap.containsKey(variableName)) {
				Domain domain = variabilityElementMap.get(variableName)
						.getDomain();

				// Se crea un nuevo identificador si el identificador no ha sido
				// creado
				if (!identifierExpressionMap.containsKey(variableName)) {

					HlclFactory f = new HlclFactory();
					Identifier identifier = f.newIdentifier(variableName,
							variableName);
					identifier.setDomain(domain);
					identifierExpressionMap.put(variableName, identifier);
					identifierExpressionList.add(identifier);
				} else {
					Identifier identifier = identifierExpressionMap
							.get(variableName);
					identifierExpressionList.add(identifier);
				}

			} else {
				throw new RuntimeException("Variable " + variableName
						+ " badly defined. Please check");
			}

		}

		return identifierExpressionList;

	}

	/**
	 * Identifica las varaibles, las declaraciones de dominio y las
	 * restricciones del programa de prolog
	 * 
	 * @param inDTO
	 */
	private void analyzePrologProgram(VMTransformerInDTO inDTO) {
		try {

			FileInputStream fstream = new FileInputStream(
					inDTO.getPathToTransform());
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			List<String> variableList = new ArrayList<String>();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {

				String line = strLine.trim();

				if (!line.startsWith(ConstraintSymbolsConstant.COMMENT_SYMBOL)) {

					if (inDTO.getPrologEditorTypeFileToTransform().equals(
							SolverEditorType.GNU_PROLOG)) {
						if (line.contains(GNUPrologConstraintSymbolsConstant.FD_DOMAIN)) {
							domainList.add(line);
						}
					}

					// If the line contains the character that identifies
					// constraints in Prolog
					if (line.contains(ConstraintSymbolsConstant.CONSTRAINT_SEPARATOR)) {

						String[] constraintsLine = line
								.split(ConstraintSymbolsConstant.COMMA);
						// Identify program variables
						variableList = LiteralExpressionUtil
								.findVariablesByLine(line);
						if (variableList.size() > 0) {
							allVariablesList.addAll(variableList);
						}

						if (constraintsLine.length == 1) {
							constraintList.add(constraintsLine[0]);

						} else if (constraintsLine.length > 1) {
							// If the line has more than one restriction, it is
							// divided in more lines in order to facilitate his
							// later analysis

							for (int i = 0; i < constraintsLine.length; i++) {
								constraintList.add(constraintsLine[i]);
							}
						}
					}

				}
			}

			// Close the input stream
			in.close();

			// // Si al terminar de leer el archivo no se encuentra ninguna
			// // instrucción que permita obtener el dominio se lanza una
			// excepción
			// if (!findDomainsDeclaration) {
			// throw new
			// TransformerException(" Domain definition not found");
			// }

		} catch (Exception e) {
			// Catch exception if any
			throw new TechnicalException(e);
		}

	}

	/**
	 * Método utilizado para encontrar los dominios de las variables de un
	 * modelo. Al salir los variabilityElements tienen asociados sus dominios
	 * 
	 * 
	 */
	public void findVariablesDomain() {

		String domainValuesString = "";
		String variablesAllowDomainString = "";
		List<Integer> domainValues = new ArrayList<Integer>();

		for (String domainDeclarationString : domainList) {
			domainValues = new ArrayList<Integer>();
			// buscamos el proximo "fd_domain", ya que inmediatamente
			// despues estará el dominio de una variable
			if (domainDeclarationString.contains("fd_domain")) {

				// se obtiene el string que posee el dominio
				if (domainDeclarationString.contains(", [")) {
					domainValuesString = domainDeclarationString.substring(
							domainDeclarationString.indexOf(", [") + 1,
							domainDeclarationString.indexOf(")") + 1);

					variablesAllowDomainString = domainDeclarationString
							.substring(0,
									domainDeclarationString.indexOf(", ["));
				} else if (domainDeclarationString.contains(",[")) {
					domainValuesString = domainDeclarationString.substring(
							domainDeclarationString.indexOf(", [") + 1,
							domainDeclarationString.indexOf(")") + 1);
					variablesAllowDomainString = domainDeclarationString
							.substring(0, domainDeclarationString.indexOf(",["));
				} else // Cuando el dominio no es un intervalo sino un rango
				if (domainDeclarationString.contains("],")) {
					domainValuesString = domainDeclarationString.substring(
							domainDeclarationString.indexOf("],") + 1,
							domainDeclarationString.indexOf(")") + 1);
				} else if (domainDeclarationString.contains("], ")) {
					domainValuesString = domainDeclarationString.substring(
							domainDeclarationString.indexOf("], ") + 1,
							domainDeclarationString.indexOf(")") + 1);
				}

				// ciclo utilizado para añadir el dominio al vector
				String number = "";
				for (int i = 0; i < domainValuesString.length(); i++) {

					if (Character.isDigit(domainValuesString.charAt(i))) {
						number = number.concat(Character
								.toString(domainValuesString.charAt(i)));
					}
					if (!number.isEmpty()
							&& (Character.isWhitespace(domainValuesString
									.charAt(i))
									|| domainValuesString.charAt(i) == ','
									|| domainValuesString.charAt(i) == ')' || domainValuesString
									.charAt(i) == ']')) {
						// Ya se tiene un número completo y se trata de volver
						// un integer
						try {
							domainValues.add(Integer.parseInt(number));
							number = "";

						} catch (NumberFormatException e) {
							throw new RuntimeException(
									"Error in domain definition.Please check");
						}

					}
				}

				DefectAnalyzerDomain domainDefectAnalyzer = null;
				// se identifica si el dominio es un intervalo (contiene [])
				// o un conjunto de valores
				if (domainValuesString.contains("[")) {

					// Se establecen los valores del dominio
					domainDefectAnalyzer = new IntervalDomainDefectAnalyzer();
					domainDefectAnalyzer = new IntervalDomainDefectAnalyzer();
					((IntervalDomain) domainDefectAnalyzer)
							.setRangeValues(domainValues);

				} else {
					domainDefectAnalyzer = new RangeDomainDefectAnalyzer();

					// Debe tener o mínimo o dos valores: mínimo y máximo
					if (domainValues.size() != 2 ) {
						throw new RuntimeException(
								"Range Badly defined . Please check");
					}

					// Se establecen los valores del dominio
					((RangeDomainDefectAnalyzer) domainDefectAnalyzer)
							.setLowerValue(domainValues.get(0));
					((RangeDomainDefectAnalyzer) domainDefectAnalyzer)
							.setUpperValue(domainValues.get(1));
				}

				// Se obtienen las variables de la declaración del dominio
				List<String> variablesDomainDeclaration = LiteralExpressionUtil
						.findVariablesByLine(variablesAllowDomainString);

				for (String variableName : variablesDomainDeclaration) {

					if (variabilityElementMap.containsKey(variableName)) {
						// Se cambia el dominio
						variabilityElementMap.get(variableName).setDomain(
								domainDefectAnalyzer);
						System.out.println("Dominio variable "
								+ variabilityElementMap.get(variableName)
										.getName()
								+ ": "
								+ variabilityElementMap.get(variableName)
										.getDomain().getDomainValues()
										.toString());

					} else {
						throw new RuntimeException("Variable " + variableName
								+ " badly defined. Please check");
					}
				}
			}
		}

	}

	@Override
	public VariabilityModel transform(VMTransformerInDTO inDTO)
			throws TransformerException {

		init(inDTO);

		// Se identifican las variables las líneas del dominio, y las
		// restricciones
		analyzePrologProgram(inDTO);

		for (String variable : allVariablesList) {
			// Por defecto por ahora se ponen booleanas
			VariabilityElement variabilityElement = new VariabilityElement(
					variable);
			variabilityElementMap.put(variable, variabilityElement);
		}

		// Se asignan los dominios a cada variable
		findVariablesDomain();

		// Se crean las restricciones teniendo encuenta en los identificadores
		// los dominios
		createConstraintsPrologProgram();

		File file = new File(inDTO.getPathToTransform());
		String variabilityModelName = file.getName().replaceFirst("[.][^.]+$",
				"");

		variabilityModel = new VariabilityModel(inDTO.getNotationType());
		variabilityModel.setModelName(variabilityModelName);

		// Se adiciona la lista de elementos y dependencias al modelo de
		// variabilidad
		variabilityModel.setElements(variabilityElementMap);
		variabilityModel.setDependencies(variabilityDependenciesMap);
		// TODO borrar
		variabilityModel.setDomainStringList(domainList);

		// TODO organizar los optional variability elements según el dominio que
		// tenga cada variable

		return variabilityModel;

	}
}
