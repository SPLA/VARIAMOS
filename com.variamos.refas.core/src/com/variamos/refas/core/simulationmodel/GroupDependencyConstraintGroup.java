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
import com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency;
import com.variamos.refas.core.transformations.AndBooleanTransformation;
import com.variamos.refas.core.transformations.DoubleImplicationBooleanTransformation;
import com.variamos.refas.core.transformations.EqualsComparisonTransformation;
import com.variamos.refas.core.transformations.GreaterOrEqualsBooleanTransformation;
import com.variamos.refas.core.transformations.LessOrEqualsBooleanTransformation;
import com.variamos.refas.core.transformations.NumberNumericTransformation;
import com.variamos.refas.core.transformations.OrBooleanTransformation;
import com.variamos.refas.core.transformations.SumNumericTransformation;
import com.variamos.refas.core.types.CardinalityType;
import com.variamos.syntaxsupport.metametamodel.MetaGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstVertex;

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
public class GroupDependencyConstraintGroup extends AbstractConstraintGroup {
	/**
	 * Type of direct Edge from DirectEdgeType enum: Example means_ends
	 */
	private CardinalityType relationType;
	/**
	 * The source edge for the constraint
	 */
	private InstGroupDependency instGroupDependency;

	/**
	 * Create the Constraint with all required parameters
	 * 
	 * @param identifier
	 * @param description
	 * @param directEdgeType
	 * @param source
	 * @param target
	 */
	public GroupDependencyConstraintGroup(String identifier,
			Map<String, Identifier> idMap, HlclFactory hlclFactory,
			InstGroupDependency instGroupDependency) {
		super(identifier, mxResources.get("defect-concept") + " " + identifier,
				idMap, hlclFactory);
		this.instGroupDependency = instGroupDependency;
		RestrictionConstraint restConst = new RestrictionConstraint(identifier,
				idMap, hlclFactory, instGroupDependency);
		getTransformations().addAll(restConst.getTransformations());
		defineTransformations();
	}

	public CardinalityType getCardinalityType() {
		return relationType;
	}

	public InstGroupDependency getInstEdge() {
		return instGroupDependency;
	}

	private void defineTransformations() {

		MetaGroupDependency metaGroupDep = instGroupDependency
				.getMetaGroupDependency();
		boolean targetActiveAttribute =false;
		if (instGroupDependency
				.getTargetRelations().size() > 0)
			targetActiveAttribute = (boolean) instGroupDependency
				.getTargetRelations().get(0).getTargetRelation().getInstAttribute("Active")
				.getValue(); 
		if (targetActiveAttribute
				&& metaGroupDep != null
				&& instGroupDependency
						.getInstAttribute(SemanticGroupDependency.VAR_CARDINALITYTYPE) != null) {
			relationType = CardinalityType
					.valueOf(((String) instGroupDependency.getInstAttribute(
							SemanticGroupDependency.VAR_CARDINALITYTYPE)
							.getValue()).trim());
			// System.out.println(relationType);

			for (String sourceName : instGroupDependency
					.getSourceAttributeNames()) {
				AbstractTransformation abstractTransformation = null;

				List<InstEdge> md = instGroupDependency.getSourceRelations();
				Iterator<InstEdge> instEdges1 = instGroupDependency
						.getSourceRelations().iterator();
				AbstractTransformation recursiveExpression1 = null;
				AbstractTransformation recursiveExpression2 = null;
				if (instEdges1.hasNext()) {
					InstEdge left1 = instEdges1.next();
					while ((boolean) left1.getSourceRelation()
							.getInstAttribute("Active").getValue() == false) {
						if (instEdges1.hasNext())
							left1 = instEdges1.next();
						else
							return;
					}
					switch (relationType) {
					case and:
						abstractTransformation = new AndBooleanTransformation();
						break;
					case or:
						abstractTransformation = new OrBooleanTransformation();
						break;
					case mutex:
						abstractTransformation = new SumNumericTransformation();
						break;
					case range:
						abstractTransformation = new SumNumericTransformation();
						Iterator<InstEdge> instEdges2 = instGroupDependency
								.getSourceRelations().iterator();
						// instEdges2.next(); // TODO eliminate duplicated edges
						// from collection and remove this
						// line
						InstEdge left2 = instEdges2.next();
						Constructor<?> constructor3 = null,
						constructor4 = null;
						try {
							constructor3 = abstractTransformation.getClass()
									.getConstructor(InstVertex.class,
											String.class, Boolean.TYPE,
											AbstractTransformation.class);
							constructor4 = abstractTransformation.getClass()
									.getConstructor(InstVertex.class,
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
								.getConstructor(InstVertex.class, String.class,
										Boolean.TYPE,
										AbstractTransformation.class);
						constructor2 = abstractTransformation.getClass()
								.getConstructor(InstVertex.class,
										InstVertex.class, String.class,
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
								new DoubleImplicationBooleanTransformation(
										instGroupDependency/*
															 * .getTargetRelations
															 * ().get(0)
															 * .getToRelation()
															 */, sourceName,
										true, recursiveExpression1));
						break;
					case mutex:
						// B_Satisfied #<=> (( ( A1_"attribute" + A2_"attribute"
						// ) + ... ) #<=> 1)
						recursiveExpression1 = transformation(constructor1,
								constructor2, instEdges1, left1, sourceName);
						AbstractTransformation transformation1 = new EqualsComparisonTransformation(
								recursiveExpression1,
								new NumberNumericTransformation(1));
						getTransformations().add(
								new DoubleImplicationBooleanTransformation(
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
						AbstractTransformation transformation3 = new GreaterOrEqualsBooleanTransformation(
								instGroupDependency, "lowCardinality", false,
								recursiveExpression1);

						AbstractTransformation transformation4 = new LessOrEqualsBooleanTransformation(
								instGroupDependency, "highCardinality", false,
								recursiveExpression2);

						AbstractTransformation transformation5 = new AndBooleanTransformation(
								transformation3, transformation4);

						getTransformations().add(
								new DoubleImplicationBooleanTransformation(
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

	private AbstractTransformation transformation(Constructor<?> constructor1,
			Constructor<?> constructor2, Iterator<InstEdge> instEdges,
			InstEdge left, String sourceName) {
		// instEdges.next(); // TODO eliminate duplicated edges from collection
		// and
		// remove this line
		if (instEdges.hasNext()) {
			InstEdge instEdge = instEdges.next();
			while ((boolean) instEdge.getSourceRelation()
					.getInstAttribute("Active").getValue() == false) {
				if (instEdges.hasNext())
					instEdge = instEdges.next();
				else
					// TODO define a cleaner way to deal with group relations
					// with one
					// element
					return new AndBooleanTransformation(
							left.getSourceRelation(), left.getSourceRelation(),
							sourceName, sourceName);
			}
			if (instEdges.hasNext()) {
				try {
					return (AbstractTransformation) constructor1.newInstance(
							left.getSourceRelation(),
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
					return (AbstractTransformation) constructor2.newInstance(
							left.getSourceRelation(),
							instEdge.getSourceRelation(), sourceName,
							sourceName);
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
		} else
			// TODO define a cleaner way to deal with group relations with one
			// element
			return new AndBooleanTransformation(left.getSourceRelation(),
					left.getSourceRelation(), sourceName, sourceName);
		return null;
	}
}
