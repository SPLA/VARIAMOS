package com.variamos.reasoning.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.core.exceptions.TechnicalException;
import com.variamos.core.util.FileUtils;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.HlclUtil;
import com.variamos.hlcl.Identifier;
import com.variamos.prologEditors.Hlcl2GnuProlog;
import com.variamos.prologEditors.Hlcl2SWIProlog;
import com.variamos.reasoning.defectAnalyzer.model.Dependency;

public class ConstraintRepresentationUtil {

	/**
	 * Realiza la lógica de convertir la representación de constraints agnostica
	 * a el solver que corresponda y guardarlo en un archivo temporal que luego
	 * es analizado
	 * 
	 * @param prologTempPath
	 * @param constraintRepresentation
	 * @param solverEditorType
	 * @throws FunctionalException
	 */
	public static void savePrologRepresentationProgram(String prologTempPath,
			Collection<BooleanExpression> constraintRepresentation,
			SolverEditorType solverEditorType) throws FunctionalException {

		String constraintProgramString = constraintToPrologProgram(
				constraintRepresentation, solverEditorType);

		// Se guarda la representación en el archivo temporal de prolog
		FileUtils.writeFile(prologTempPath, constraintProgramString);
	}
	
	
	public static void savePrologRepresentationProgram(String prologTempPath,
			Collection<BooleanExpression> constraintRepresentation,List<String>domainList,
			SolverEditorType solverEditorType) throws FunctionalException {

		String constraintProgramString = constraintToPrologProgram(
				constraintRepresentation, domainList,solverEditorType);

		// Se guarda la representación en el archivo temporal de prolog
		FileUtils.writeFile(prologTempPath, constraintProgramString);
	}

	public static String constraintToPrologProgram(
			Collection<BooleanExpression> constraintRepresentation,
			SolverEditorType solverEditorType) throws FunctionalException {

		HlclProgram hlclProgram = new HlclProgram();
		String constraintProgramString = new String();
		if (!constraintRepresentation.isEmpty()) {
			hlclProgram = expressionToHlclProgram(constraintRepresentation);
			if (SolverEditorType.SWI_PROLOG.equals(solverEditorType)) {
				Hlcl2SWIProlog swiPrologTransformer = new Hlcl2SWIProlog();
				constraintProgramString = swiPrologTransformer
						.transform(hlclProgram);
			} else if (SolverEditorType.GNU_PROLOG.equals(solverEditorType)) {
				Hlcl2GnuProlog gnuPrologTransformer = new Hlcl2GnuProlog();
				constraintProgramString = gnuPrologTransformer
						.transform(hlclProgram);
			} else {
				throw new TechnicalException(
						"None  transformer is implemented for the solver "
								+ solverEditorType.name());
			}
		} else {
			throw new FunctionalException(
					"Not received any expression that represents the variability model");
		}
		return constraintProgramString;
	}

	
	
	public static String constraintToPrologProgram(
			Collection<BooleanExpression> constraintRepresentation,List<String>domainList,
			SolverEditorType solverEditorType) throws FunctionalException {

		HlclProgram hlclProgram = new HlclProgram();
		String constraintProgramString = new String();
		if (!constraintRepresentation.isEmpty()) {
			hlclProgram = expressionToHlclProgram(constraintRepresentation);
			if (SolverEditorType.GNU_PROLOG.equals(solverEditorType)) {
				Hlcl2GnuProlog gnuPrologTransformer = new Hlcl2GnuProlog();
				constraintProgramString = gnuPrologTransformer
						.transform(hlclProgram,domainList);
			} else {
				throw new TechnicalException(
						"None  transformer is implemented for the solver "
								+ solverEditorType.name());
			}
		} else {
			throw new FunctionalException(
					"Not received any expression that represents the variability model");
		}
		return constraintProgramString;
	}
	
	public static HlclProgram expressionToHlclProgram(
			Collection<BooleanExpression> constraintRepresentation) {
		HlclProgram hlclProgram = new HlclProgram();
		for (BooleanExpression expression : constraintRepresentation) {
			hlclProgram.add((BooleanExpression) expression);
		}
		return hlclProgram;
	}
	
	/**
	 * @param expressionsList
	 * @return creates a HLCLProgram list from a boolean Expression matrix
	 */
	public List<HlclProgram> expresions2HlclProgramList(
			List<List<BooleanExpression>> expressionsList) {

		List<HlclProgram> hlcLProgramList = new ArrayList<HlclProgram>();
		for (List<BooleanExpression> expressions : expressionsList) {
			HlclProgram expressionProgram = new HlclProgram();
			expressionProgram.addAll(expressions);
			hlcLProgramList.add(expressionProgram);
		}
		return hlcLProgramList;
	}

	public static Collection<BooleanExpression> dependencyListToExpressionList(
			Collection<Dependency> dependencies) {
		Collection<BooleanExpression> variabilityModelConstraintRepresentation = new HashSet<BooleanExpression>();

		// Se recorre la lista de restricciones a incluir
		for (Dependency dependency : dependencies) {
			variabilityModelConstraintRepresentation.add(dependency
					.getConstraintExpression());
		}
		return variabilityModelConstraintRepresentation;
	}

	public static Collection<BooleanExpression> dependencyToExpressionList(
			Map<Long, Dependency> dependenciesSet,
			Map<Long, Dependency> fixedDependencies) {

		Map<Long, Dependency> allDependenciesMap = new HashMap<Long, Dependency>();
		allDependenciesMap.putAll(dependenciesSet);
		allDependenciesMap.putAll(fixedDependencies);
		return dependencyListToExpressionList(allDependenciesMap.values());

	}

	public static Collection<BooleanExpression> dependencyToExpressionList(
			List<Dependency> dependenciesSet, List<Dependency> fixedDependencies) {

		List<Dependency> allDependenciesList = new ArrayList<Dependency>();
		allDependenciesList.addAll(dependenciesSet);
		allDependenciesList.addAll(fixedDependencies);
		return dependencyListToExpressionList(allDependenciesList);

	}
	
	public static Collection<BooleanExpression> dependencyToExpressionList(
			List<Dependency> dependenciesSet,Map<Long, Dependency> fixedDependencies) {

		List<Dependency> allDependenciesList = new ArrayList<Dependency>();
		allDependenciesList.addAll(dependenciesSet);
		allDependenciesList.addAll(fixedDependencies.values());
		return dependencyListToExpressionList(allDependenciesList);

	}

	/**
	 * Se hace este método para que el resto del código no tenga que preocuparse
	 * por volver las expressions hlclcPrograms
	 * 
	 * @param constraintRepresentation
	 * @return
	 */
	public static Collection<Identifier> getIdentifiersSet(
			Collection<BooleanExpression> constraintRepresentation) {
		HlclProgram hlclProgram = new HlclProgram();
		hlclProgram
				.addAll((Collection<? extends BooleanExpression>) constraintRepresentation);
		Collection<Identifier> ids = HlclUtil.getUsedIdentifiers(hlclProgram);
		return ids;

	}
}
