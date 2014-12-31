package com.variamos.refas.core.simulationmodel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.mxgraph.util.mxResources;
import com.variamos.refas.core.expressions.AndBooleanExpression;
import com.variamos.refas.core.expressions.DoubleImplicationBooleanExpression;
import com.variamos.refas.core.expressions.EqualsComparisonExpression;
import com.variamos.refas.core.expressions.GreaterOrEqualsBooleanExpression;
import com.variamos.refas.core.expressions.LessOrEqualsBooleanExpression;
import com.variamos.refas.core.expressions.NumberNumericExpression;
import com.variamos.refas.core.expressions.OrBooleanExpression;
import com.variamos.refas.core.expressions.SumNumericExpression;
import com.variamos.refas.core.sematicsmetamodel.SemanticOverTwoRelation;
import com.variamos.refas.core.types.CardinalityType;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodelsupport.MetaOverTwoRelation;

//TODO refactor: OverTwoElementExpressionSet
/**
 * A class to represent the constraints for group relations. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-16
 */
public class OverTwoElementsExpressionSet extends MetaExpressionSet {
	/**
	 * Type of direct Edge from DirectEdgeType enum: Example means_ends
	 */
	private CardinalityType relationType;
	/**
	 * The source edge for the constraint
	 */
	private InstOverTwoRelation instGroupDependency;

	/**
	 * Create the Constraint with all required parameters
	 * 
	 * @param identifier
	 * @param description
	 * @param directEdgeType
	 * @param source
	 * @param target
	 */
	public OverTwoElementsExpressionSet(String identifier,
			Map<String, Identifier> idMap, HlclFactory hlclFactory,
			InstOverTwoRelation instGroupDependency) {
		super(identifier, mxResources.get("defect-concept") + " " + identifier,
				idMap, hlclFactory);
		this.instGroupDependency = instGroupDependency;
		SingleElementExpressionSet restConst = new SingleElementExpressionSet(identifier,
				idMap, hlclFactory, instGroupDependency);
		getTransformations().addAll(restConst.getTransformations());
		defineTransformations();
	}

	public CardinalityType getCardinalityType() {
		return relationType;
	}

	public InstOverTwoRelation getInstEdge() {
		return instGroupDependency;
	}

	private void defineTransformations() {

		MetaOverTwoRelation metaGroupDep = instGroupDependency
				.getMetaOverTwoRelation();
		boolean targetActiveAttribute =false;
		if (instGroupDependency
				.getTargetRelations().size() > 0)
			targetActiveAttribute = (boolean) ((InstPairwiseRelation)instGroupDependency
				.getTargetRelations().get(0)).getTargetRelations().get(0).getInstAttribute("Active")
				.getValue(); 
		if (targetActiveAttribute
				&& metaGroupDep != null
				&& instGroupDependency
						.getInstAttribute(SemanticOverTwoRelation.VAR_CARDINALITYTYPE) != null) {
			relationType = CardinalityType
					.valueOf(((String) instGroupDependency.getInstAttribute(
							SemanticOverTwoRelation.VAR_CARDINALITYTYPE)
							.getValue()).trim());
			// System.out.println(relationType);

			for (String sourceName : instGroupDependency
					.getSourceAttributeNames()) {
				AbstractExpression abstractTransformation = null;
				Iterator<InstElement> instEdges1 = instGroupDependency
						.getSourceRelations().iterator();
				AbstractExpression recursiveExpression1 = null;
				AbstractExpression recursiveExpression2 = null;
				if (instEdges1.hasNext()) {
					InstElement left1 = instEdges1.next();
					while ((boolean) ((InstPairwiseRelation)left1).getSourceRelations().get(0)
							.getInstAttribute("Active").getValue() == false) {
						if (instEdges1.hasNext())
							left1 = instEdges1.next();
						else
							return;
					}
					switch (relationType) {
					case and:
						abstractTransformation = new AndBooleanExpression();
						break;
					case or:
						abstractTransformation = new OrBooleanExpression();
						break;
					case mutex:
						abstractTransformation = new SumNumericExpression();
						break;
					case range:
						abstractTransformation = new SumNumericExpression();
						Iterator<InstElement> instEdges2 = instGroupDependency
								.getSourceRelations().iterator();
						// instEdges2.next(); // TODO eliminate duplicated edges
						// from collection and remove this
						// line
						InstElement left2 = instEdges2.next();
						Constructor<?> constructor3 = null,
						constructor4 = null;
						try {
							constructor3 = abstractTransformation.getClass()
									.getConstructor(InstElement.class,
											String.class, Boolean.TYPE,
											AbstractExpression.class);
							constructor4 = abstractTransformation.getClass()
									.getConstructor(InstElement.class,
											InstVertex.class, String.class,
											String.class);
						} catch (NoSuchMethodException | SecurityException e) {
							e.printStackTrace();
						}

						recursiveExpression2 = transformation(constructor3,
								constructor4, instEdges2, left2, sourceName);
						break;

					default:
						break;
					}

					Constructor<?> constructor1 = null, constructor2 = null;
					try {
						constructor1 = abstractTransformation.getClass()
								.getConstructor(InstElement.class, String.class,
										Boolean.TYPE,
										AbstractExpression.class);
						constructor2 = abstractTransformation.getClass()
								.getConstructor(InstElement.class,
										InstElement.class, String.class,
										String.class);
					} catch (NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}

					switch (relationType) {

					case and:
						// B_Satisfied #<=> ( ( A1_"attribute" #/\
						// A2_"attribute" ) #/\ ... )
					case or:
						// B_Satisfied #<=> ( ( A1_"attribute" #\/
						// A2_"attribute" ) #\/ ... )
						recursiveExpression1 = transformation(constructor1,
								constructor2, instEdges1, left1, sourceName);
						getTransformations().add(
								new DoubleImplicationBooleanExpression(
										instGroupDependency, sourceName,
										true, recursiveExpression1));
						break;
					case mutex:
						// B_Satisfied #<=> (( ( A1_"attribute" + A2_"attribute"
						// ) + ... ) #<=> 1)
						recursiveExpression1 = transformation(constructor1,
								constructor2, instEdges1, left1, sourceName);
						AbstractExpression transformation1 = new EqualsComparisonExpression(
								recursiveExpression1,
								new NumberNumericExpression(1));
						getTransformations().add(
								new DoubleImplicationBooleanExpression(
										instGroupDependency, sourceName,
										true, transformation1));

						break;
					case range:

						// B_"attribute" #<=> ( ( ( ( A1_"attribute" +
						// A2_"attribute" ) + ... ) #>= GD_LowCardinality) #/\
						// ( ( ( A1_"attribute" + A2_"attribute" ) + ... ) #<=
						// GD_HighCardinality ) )
						recursiveExpression1 = transformation(constructor1,
								constructor2, instEdges1, left1, sourceName);
						AbstractExpression transformation3 = new GreaterOrEqualsBooleanExpression(
								instGroupDependency, "lowCardinality", false,
								recursiveExpression1);

						AbstractExpression transformation4 = new LessOrEqualsBooleanExpression(
								instGroupDependency, "highCardinality", false,
								recursiveExpression2);

						AbstractExpression transformation5 = new AndBooleanExpression(
								transformation3, transformation4);

						getTransformations().add(
								new DoubleImplicationBooleanExpression(
										instGroupDependency/*
															 * .getTargetRelations
															 * ().get(0)
															 * .getToRelation()
															 */, sourceName,
										true, transformation5));

						break;

					default:
						break;
					}
				}
			}
		}
	}

	//TODO refactor createExpression
	private AbstractExpression transformation(Constructor<?> constructor1,
			Constructor<?> constructor2, Iterator<InstElement> instEdges,
			InstElement left, String sourceName) {
		// instEdges.next(); // TODO eliminate duplicated edges from collection
		// and
		// remove this line
		if (instEdges.hasNext()) {
			InstElement instEdge = instEdges.next();
			while ((boolean) ((InstPairwiseRelation) instEdge).getSourceRelations().get(0)
					.getInstAttribute("Active").getValue() == false) {
				if (instEdges.hasNext())
					instEdge = instEdges.next();
				else
					// TODO define a cleaner way to deal with group relations
					// with one
					// element
					return new AndBooleanExpression(
							((InstPairwiseRelation)left).getSourceRelations().get(0), ((InstPairwiseRelation)left).getSourceRelations().get(0),
							sourceName, sourceName);
			}
			if (instEdges.hasNext()) {
				try {
					return (AbstractExpression) constructor1.newInstance(
							((InstPairwiseRelation)left).getSourceRelations().get(0),
							sourceName,
							true,
							transformation(constructor1, constructor2,
									instEdges, instEdge, sourceName));
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			} else
				try {
					return (AbstractExpression) constructor2.newInstance(
							((InstPairwiseRelation)left).getSourceRelations().get(0),
							((InstPairwiseRelation)instEdge).getSourceRelations().get(0), sourceName,
							sourceName);
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
		} else
			// TODO define a cleaner way to deal with group relations with one
			// element
			return new AndBooleanExpression(((InstPairwiseRelation)left).getSourceRelations().get(0),
					((InstPairwiseRelation)left).getSourceRelations().get(0), sourceName, sourceName);
		return null;
	}
}
