package com.variamos.reasoning.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.common.core.exceptions.TechnicalException;
import com.variamos.common.core.utilities.FileUtils;
import com.variamos.common.model.enums.SolverEditorType;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.core.HlclUtil;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.reasoning.defectAnalyzer.model.transformation.Dependency;
import com.variamos.solver.core.compiler.Hlcl2SWIProlog;

public class ConstraintRepresentationUtil {

	
	
	
	

	public static String constraintToPrologProgram(
			Collection<IntBooleanExpression> constraintRepresentation,
			SolverEditorType solverEditorType) throws FunctionalException {

		HlclProgram hlclProgram = new HlclProgram();
		String constraintProgramString = new String();
		if (!constraintRepresentation.isEmpty()) {
			hlclProgram = expressionToHlclProgram(constraintRepresentation);
			if (SolverEditorType.SWI_PROLOG.equals(solverEditorType)) {
				Hlcl2SWIProlog swiPrologTransformer = new Hlcl2SWIProlog();
				constraintProgramString = swiPrologTransformer
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
			Collection<IntBooleanExpression> constraintRepresentation,List<String>domainList,
			SolverEditorType solverEditorType) throws FunctionalException {

		HlclProgram hlclProgram = new HlclProgram();
		String constraintProgramString = new String();
		if (!constraintRepresentation.isEmpty()) {
			hlclProgram = expressionToHlclProgram(constraintRepresentation);
		} else {
			throw new FunctionalException(
					"Not received any expression that represents the variability model");
		}
		return constraintProgramString;
	}
	
	public static HlclProgram expressionToHlclProgram(
			Collection<IntBooleanExpression> constraintRepresentation) {
		HlclProgram hlclProgram = new HlclProgram();
		for (IntBooleanExpression expression : constraintRepresentation) {
			hlclProgram.add((IntBooleanExpression) expression);
		}
		return hlclProgram;
	}
	
	

	public static Collection<IntBooleanExpression> dependencyListToExpressionList(
			Collection<Dependency> dependencies) {
		Collection<IntBooleanExpression> variabilityModelConstraintRepresentation = new HashSet<IntBooleanExpression>();

		// Se recorre la lista de restricciones a incluir
		for (Dependency dependency : dependencies) {
			variabilityModelConstraintRepresentation.add(dependency
					.getConstraintExpression());
		}
		return variabilityModelConstraintRepresentation;
	}

	public static Collection<IntBooleanExpression> dependencyToExpressionList(
			Map<Long, Dependency> dependenciesSet,
			Map<Long, Dependency> fixedDependencies) {

		Map<Long, Dependency> allDependenciesMap = new HashMap<Long, Dependency>();
		allDependenciesMap.putAll(dependenciesSet);
		allDependenciesMap.putAll(fixedDependencies);
		return dependencyListToExpressionList(allDependenciesMap.values());

	}

	
	
	

	
}
