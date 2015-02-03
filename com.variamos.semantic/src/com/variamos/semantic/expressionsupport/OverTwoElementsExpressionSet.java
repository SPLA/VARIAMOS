package com.variamos.semantic.expressionsupport;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.Identifier;
import com.mxgraph.util.mxResources;
import com.variamos.semantic.expressions.AbstractBooleanExpression;
import com.variamos.semantic.expressions.AbstractExpression;
import com.variamos.semantic.expressions.AndBooleanExpression;
import com.variamos.semantic.expressions.DoubleImplicationBooleanExpression;
import com.variamos.semantic.expressions.EqualsComparisonExpression;
import com.variamos.semantic.expressions.GreaterOrEqualsBooleanExpression;
import com.variamos.semantic.expressions.LessOrEqualsBooleanExpression;
import com.variamos.semantic.expressions.NumberNumericExpression;
import com.variamos.semantic.expressions.OrBooleanExpression;
import com.variamos.semantic.expressions.SumNumericExpression;
import com.variamos.semantic.semanticsupport.SemanticOverTwoRelation;
import com.variamos.syntax.instancesupport.InstElement;
import com.variamos.syntax.instancesupport.InstOverTwoRelation;
import com.variamos.syntax.instancesupport.InstPairwiseRelation;
import com.variamos.syntax.metamodelsupport.MetaElement;

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
	private String relationType;
	/**
	 * The source edge for the constraint
	 */
	private InstOverTwoRelation instOverTwoRelation;

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
			InstOverTwoRelation instOverTwoRelation, int execType,
			String element) {
		super(identifier, mxResources.get("defect-concept") + " " + identifier,
				idMap, hlclFactory);
		this.instOverTwoRelation = instOverTwoRelation;
		SingleElementExpressionSet restConst = new SingleElementExpressionSet(
				identifier, idMap, hlclFactory, instOverTwoRelation, execType);
		if (element.equals(""))
			getElementExpressions().addAll(restConst.getElementExpressions());
		else {
			this.getRelaxableExpressions().put(element,
					restConst.getRelaxableExpressionList(element));
			this.getCompulsoryExpressions().put(element,
					restConst.getCompulsoryExpressionList(element));
			this.getVerificationExpressions().put(element,
					restConst.getVerificationExpressionsList(element));
		}
		defineTransformations(element);
	}

	public String getCardinalityType() {
		return relationType;
	}

	public InstOverTwoRelation getInstEdge() {
		return instOverTwoRelation;
	}

	private void defineTransformations(String element) {

		MetaElement metaGroupDep = instOverTwoRelation
				.getTransSupportMetaElement();
		boolean targetActiveAttribute = false;
		if (instOverTwoRelation.getTargetRelations().size() > 0)
			// TODO support multiple targets
			targetActiveAttribute = (boolean) ((InstPairwiseRelation) instOverTwoRelation
					.getTargetRelations().get(0)).getTargetRelations().get(0)
					.getInstAttribute("Active").getValue();
		if (targetActiveAttribute && metaGroupDep != null) {
			relationType = (String) instOverTwoRelation.getInstAttribute(
					SemanticOverTwoRelation.VAR_RELATIONTYPE_IDEN).getValue();
			// System.out.println(relationType);
			List<AbstractExpression> allList = new ArrayList<AbstractExpression>();
			List<AbstractExpression> coreList = new ArrayList<AbstractExpression>();
			/*
			 * for (String sourceName : instOverTwoRelation
			 * .getSourceNegativeAttributeNames()) { AbstractExpression
			 * abstractTransformation = null; Iterator<InstElement> instEdges1 =
			 * instOverTwoRelation .getSourceRelations().iterator();
			 * AbstractExpression recursiveExpression1 = null;
			 * AbstractExpression recursiveExpression2 = null; if
			 * (instEdges1.hasNext()) { InstElement left1 = instEdges1.next();
			 * while ((boolean) ((InstPairwiseRelation) left1)
			 * .getSourceRelations().get(0)
			 * .getInstAttribute("Active").getValue() == false) { if
			 * (instEdges1.hasNext()) left1 = instEdges1.next(); else return; }
			 * abstractTransformation = new AndBooleanExpression();
			 * Constructor<?> constructor1 = null, constructor2 = null; try {
			 * constructor1 = abstractTransformation.getClass()
			 * .getConstructor(InstElement.class, String.class, Boolean.TYPE,
			 * AbstractExpression.class); constructor2 =
			 * abstractTransformation.getClass()
			 * .getConstructor(InstElement.class, InstElement.class,
			 * String.class, String.class); } catch (NoSuchMethodException |
			 * SecurityException e) { e.printStackTrace(); }
			 * 
			 * recursiveExpression1 = transformation(constructor1, constructor2,
			 * instEdges1, left1, sourceName); AbstractBooleanExpression out =
			 * new DoubleImplicationBooleanExpression( instOverTwoRelation,
			 * sourceName, true, recursiveExpression1);
			 * getElementExpressions().add(out); if (relationType.equals("and"))
			 * coreList.add(out); allList.add(out); } }
			 */
			// System.out.println(relationType + " " + element);
			if (relationType.equals("and") || element == null
					|| !element.equals("Core"))
				for (String sourceName : instOverTwoRelation
						.getSourcePositiveAttributeNames()) {
					AbstractExpression abstractTransformation = null;
					Iterator<InstElement> instEdges1 = instOverTwoRelation
							.getSourceRelations().iterator();
					AbstractExpression recursiveExpression1 = null;
					AbstractExpression recursiveExpression2 = null;
					if (instEdges1.hasNext()) {
						InstElement left1 = instEdges1.next();
						while (((InstPairwiseRelation) left1)
								.getSourceRelations().size() != 0
								&& (boolean) ((InstPairwiseRelation) left1)
										.getSourceRelations().get(0)
										.getInstAttribute("Active").getValue() == false) {
							if (instEdges1.hasNext())
								left1 = instEdges1.next();
							else
								return;
						}
						switch (relationType) {
						case "and":
							abstractTransformation = new AndBooleanExpression();
							break;
						case "or":
							abstractTransformation = new OrBooleanExpression();
							break;
						case "mutex":
							abstractTransformation = new SumNumericExpression();
							break;
						case "range":
							abstractTransformation = new SumNumericExpression();
							Iterator<InstElement> instEdges2 = instOverTwoRelation
									.getSourceRelations().iterator();
							// instEdges2.next(); // TODO eliminate duplicated
							// edges
							// from collection and remove this
							// line
							InstElement left2 = instEdges2.next();
							Constructor<?> constructor3 = null,
							constructor4 = null;
							try {
								constructor3 = abstractTransformation
										.getClass().getConstructor(
												InstElement.class,
												String.class, Boolean.TYPE,
												AbstractExpression.class);
								constructor4 = abstractTransformation
										.getClass().getConstructor(
												InstElement.class,
												InstElement.class,
												String.class, String.class);
							} catch (NoSuchMethodException | SecurityException e) {
								e.printStackTrace();
							}

							recursiveExpression2 = transformation(constructor3,
									constructor4, instEdges2, left2, sourceName);
							break;
						case "":
							return;

						default:
							return;
						}

						Constructor<?> constructor1 = null, constructor2 = null;
						try {
							constructor1 = abstractTransformation.getClass()
									.getConstructor(InstElement.class,
											String.class, Boolean.TYPE,
											AbstractExpression.class);
							constructor2 = abstractTransformation.getClass()
									.getConstructor(InstElement.class,
											InstElement.class, String.class,
											String.class);
						} catch (NoSuchMethodException | SecurityException e) {
							e.printStackTrace();
						}

						switch (relationType) {

						case "and":
							// B_Satisfied #<=> ( ( A1_"attribute" #/\
							// A2_"attribute" ) #/\ ... )
						case "or":
							// B_Satisfied #<=> ( ( A1_"attribute" #\/
							// A2_"attribute" ) #\/ ... )
							recursiveExpression1 = transformation(constructor1,
									constructor2, instEdges1, left1, sourceName);
							AbstractBooleanExpression out = new DoubleImplicationBooleanExpression(
									instOverTwoRelation, sourceName, true,
									recursiveExpression1);
							getElementExpressions().add(out);
							if (relationType.equals("and"))
								coreList.add(out);
							allList.add(out);
							break;
						case "mutex":
							// (( ( A1_"attribute" + A2_"attribute"
							// ) + ... ) #<= 1)
							recursiveExpression1 = transformation(constructor1,
									constructor2, instEdges1, left1, sourceName);
							LessOrEqualsBooleanExpression out2 = new LessOrEqualsBooleanExpression(
									recursiveExpression1,
									new NumberNumericExpression(1));
							getElementExpressions().add(out2);
							allList.add(out2);
							// B_Satisfied #<=> (( ( A1_"attribute" +
							// A2_"attribute"
							// ) + ... ) #<=> 1)
							AbstractExpression transformation1 = new EqualsComparisonExpression(
									recursiveExpression1,
									new NumberNumericExpression(1));
							AbstractBooleanExpression out3 = new DoubleImplicationBooleanExpression(
									instOverTwoRelation, sourceName, true,
									transformation1);
							getElementExpressions().add(out3);
							allList.add(out3);
							break;
						case "range":

							// B_"attribute" #<=> ( ( ( ( A1_"attribute" +
							// A2_"attribute" ) + ... ) #>= GD_LowCardinality)
							// #/\
							// ( ( ( A1_"attribute" + A2_"attribute" ) + ... )
							// #<=
							// GD_HighCardinality ) )
							recursiveExpression1 = transformation(constructor1,
									constructor2, instEdges1, left1, sourceName);
							AbstractExpression transformation3 = new GreaterOrEqualsBooleanExpression(
									instOverTwoRelation, "lowCardinality",
									false, recursiveExpression1);

							AbstractExpression transformation4 = new LessOrEqualsBooleanExpression(
									instOverTwoRelation, "highCardinality",
									false, recursiveExpression2);

							AbstractExpression transformation5 = new AndBooleanExpression(
									transformation3, transformation4);
							AbstractBooleanExpression out0 = new DoubleImplicationBooleanExpression(
									instOverTwoRelation/*
														 * .getTargetRelations
														 * ().get(0)
														 * .getToRelation()
														 */, sourceName, true,
									transformation5);
							getElementExpressions().add(out0);
							if (instOverTwoRelation.getSourceRelations().size() <= instOverTwoRelation
									.getInstAttribute("lowCardinality")
									.getAsInteger())
								coreList.add(out0);
							allList.add(out0);
							break;

						default:
							return;
						}
					}
				}
			List<AbstractExpression> parentList = this
					.getCompulsoryExpressionList("Parent");
			if (parentList != null)
				parentList.addAll(allList);
			else
				this.getCompulsoryExpressions().put("Parent", allList);

			List<AbstractExpression> coreLists = this
					.getCompulsoryExpressionList("Core");
			if (coreLists != null)
				coreLists.addAll(coreList);
			else
				this.getCompulsoryExpressions().put("Core", coreList);

			List<AbstractExpression> falseList = this
					.getCompulsoryExpressionList("FalseOpt");
			if (falseList != null)
				falseList.addAll(coreList);
			this.getCompulsoryExpressions().put("FalseOpt", coreList);

			List<AbstractExpression> falseList2 = this
					.getCompulsoryExpressionList("FalseOpt2");
			if (falseList2 != null)
				falseList2.addAll(allList);
			this.getCompulsoryExpressions().put("FalseOpt2", allList);
		}
	}

	// TODO refactor createExpression
	private AbstractExpression transformation(Constructor<?> constructor1,
			Constructor<?> constructor2, Iterator<InstElement> instEdges,
			InstElement left, String sourceName) {
		// instEdges.next(); // TODO eliminate duplicated edges from collection
		// and
		// remove this line
		if (instEdges.hasNext()) {
			InstElement instEdge = instEdges.next();
			while ((boolean) ((InstPairwiseRelation) instEdge)
					.getSourceRelations().get(0).getInstAttribute("Active")
					.getValue() == false) {
				if (instEdges.hasNext())
					instEdge = instEdges.next();
				else
					// TODO define a cleaner way to deal with group relations
					// with one
					// element
					return new AndBooleanExpression(
							((InstPairwiseRelation) left).getSourceRelations()
									.get(0), ((InstPairwiseRelation) left)
									.getSourceRelations().get(0), sourceName,
							sourceName);
			}
			if (instEdges.hasNext()) {
				try {
					return (AbstractExpression) constructor1.newInstance(
							((InstPairwiseRelation) left).getSourceRelations()
									.get(0),
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
							((InstPairwiseRelation) left).getSourceRelations()
									.get(0), ((InstPairwiseRelation) instEdge)
									.getSourceRelations().get(0), sourceName,
							sourceName);
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
		} else
			// TODO define a cleaner way to deal with group relations with one
			// element
			return new AndBooleanExpression(((InstPairwiseRelation) left)
					.getSourceRelations().get(0), ((InstPairwiseRelation) left)
					.getSourceRelations().get(0), sourceName, sourceName);
		return null;
	}
}
