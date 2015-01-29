package com.cfm.hlcl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class HlclUtil {

	public static Set<Identifier> getUsedIdentifiers(Expression e) {

		Set<Identifier> ids = new TreeSet<Identifier>();

		if (e instanceof HlclProgram) {
			for (Expression exp : (HlclProgram) e)
				ids.addAll(getUsedIdentifiers(exp));
		}

		if (e instanceof ComparisonExpression) {
			ids.addAll(getUsedIdentifiers(((ComparisonExpression) e).getLeft()));
			ids.addAll(getUsedIdentifiers(((ComparisonExpression) e).getRight()));
		}

		if (e instanceof NumericOperation) {
			ids.addAll(getUsedIdentifiers(((NumericOperation) e).getLeft()));
			ids.addAll(getUsedIdentifiers(((NumericOperation) e).getRight()));
		}

		if (e instanceof BooleanOperation) {
			ids.addAll(getUsedIdentifiers(((BooleanOperation) e).getLeft()));
			ids.addAll(getUsedIdentifiers(((BooleanOperation) e).getRight()));
		}

		if (e instanceof ListDefinitionExpression) {
			ids.addAll(((ListDefinitionExpression) e).getValues());
		}

		if (e instanceof SymbolicExpression) {
			ids.addAll(((SymbolicExpression) e).getArgs());
		}

		if (e instanceof Identifier) {
			ids.add((Identifier) e);
		}

		if (e instanceof LiteralBooleanExpression) {

			if (((LiteralBooleanExpression) e).getIdentifierExpressionList()
					.isEmpty()) {
				List<String> variablesLiteralExpression = LiteralExpressionUtil
						.findVariablesByLine(((LiteralBooleanExpression) e)
								.getPrologConstraint());
				HlclFactory f = new HlclFactory();

				for (String variable : variablesLiteralExpression) {
					Identifier id = f.newIdentifier(variable, variable);
					if (!ids.contains(id)) {
						ids.add(id);
					}
				}
			}else{
				Collection<Identifier> idsCollection=((LiteralBooleanExpression) e).getIdentifierExpressionList();
				ids.addAll(idsCollection);
			}

		}
		return ids;
	}

	public static Map<Identifier, Long> getCountOfIdentifiers(Expression e) {

		Map<Identifier, Long> identifierCountMap = new TreeMap<Identifier, Long>();
		List<Identifier> idsExpressionList = new ArrayList<Identifier>();
		if (e instanceof HlclProgram) {
			for (Expression exp : (HlclProgram) e) {
				Map<Identifier, Long> localResultMap = getCountOfIdentifiers(exp);
				for (Identifier localIdentifier : localResultMap.keySet()) {
					if (identifierCountMap.containsKey(localIdentifier)) {
						Long idOccurenceCount = identifierCountMap
								.get(localIdentifier);
						idOccurenceCount += localResultMap.get(localIdentifier);
						identifierCountMap.put(localIdentifier,
								idOccurenceCount);
					} else {
						Long idOccurenceCount = localResultMap
								.get(localIdentifier);
						identifierCountMap.put(localIdentifier,
								idOccurenceCount);
					}
				}
			}
		}

		if (e instanceof ComparisonExpression) {
			idsExpressionList
					.addAll(getUsedIdentifiersWithRepetitions(((ComparisonExpression) e)
							.getLeft()));
			idsExpressionList
					.addAll(getUsedIdentifiersWithRepetitions(((ComparisonExpression) e)
							.getRight()));
			for (Identifier idExpression : idsExpressionList) {
				updateMapIdentifierCount(identifierCountMap, idExpression);
			}
		}

		if (e instanceof NumericOperation) {
			idsExpressionList
					.addAll(getUsedIdentifiersWithRepetitions(((NumericOperation) e)
							.getLeft()));
			idsExpressionList
					.addAll(getUsedIdentifiersWithRepetitions(((NumericOperation) e)
							.getRight()));
			for (Identifier idExpression : idsExpressionList) {
				updateMapIdentifierCount(identifierCountMap, idExpression);
			}
		}

		if (e instanceof BooleanOperation) {
			idsExpressionList
					.addAll(getUsedIdentifiersWithRepetitions(((BooleanOperation) e)
							.getLeft()));
			idsExpressionList
					.addAll(getUsedIdentifiersWithRepetitions(((BooleanOperation) e)
							.getRight()));
			for (Identifier idExpression : idsExpressionList) {
				updateMapIdentifierCount(identifierCountMap, idExpression);
			}
		}

		if (e instanceof SymbolicExpression) {
			idsExpressionList.addAll((((SymbolicExpression) e).getArgs()));
			for (Identifier idExpression : idsExpressionList) {
				updateMapIdentifierCount(identifierCountMap, idExpression);
			}
		}

		if (e instanceof Identifier) {
			updateMapIdentifierCount(identifierCountMap, ((Identifier) e));
		}

		return identifierCountMap;
	}

	private static void updateMapIdentifierCount(
			Map<Identifier, Long> identifierCountMap, Identifier id) {
		if (identifierCountMap.containsKey(id)) {
			Long idOccurenceCount = identifierCountMap.get(id);
			idOccurenceCount++;
			identifierCountMap.put(id, idOccurenceCount);
		} else {
			identifierCountMap.put(id, new Long(1));
		}
	}

	private static List<Identifier> getUsedIdentifiersWithRepetitions(
			Expression e) {

		List<Identifier> ids = new ArrayList<>();

		if (e instanceof HlclProgram) {
			for (Expression exp : (HlclProgram) e)
				ids.addAll(getUsedIdentifiers(exp));
		}

		if (e instanceof ComparisonExpression) {
			ids.addAll(getUsedIdentifiers(((ComparisonExpression) e).getLeft()));
			ids.addAll(getUsedIdentifiers(((ComparisonExpression) e).getRight()));
		}

		if (e instanceof NumericOperation) {
			ids.addAll(getUsedIdentifiers(((NumericOperation) e).getLeft()));
			ids.addAll(getUsedIdentifiers(((NumericOperation) e).getRight()));
		}

		if (e instanceof BooleanOperation) {
			ids.addAll(getUsedIdentifiers(((BooleanOperation) e).getLeft()));
			ids.addAll(getUsedIdentifiers(((BooleanOperation) e).getRight()));
		}

		if (e instanceof SymbolicExpression) {
			ids.addAll(((SymbolicExpression) e).getArgs());
		}

		if (e instanceof Identifier) {
			ids.add((Identifier) e);
		}

		return ids;
	}

}
