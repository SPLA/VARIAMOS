package com.variamos.dynsup.staticexpr;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mxgraph.util.mxResources;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstOverTwoRel;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.staticexprsup.AbstractBooleanExpression;
import com.variamos.dynsup.staticexprsup.AbstractExpression;
import com.variamos.dynsup.staticexprsup.AndBooleanExpression;
import com.variamos.dynsup.staticexprsup.DoubleImplicationBooleanExpression;
import com.variamos.dynsup.staticexprsup.EqualsComparisonExpression;
import com.variamos.dynsup.staticexprsup.GreaterOrEqualsBooleanExpression;
import com.variamos.dynsup.staticexprsup.LessOrEqualsBooleanExpression;
import com.variamos.dynsup.staticexprsup.NotBooleanExpression;
import com.variamos.dynsup.staticexprsup.NumberNumericExpression;
import com.variamos.dynsup.staticexprsup.OrBooleanExpression;
import com.variamos.dynsup.staticexprsup.SumNumericExpression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.Identifier;
import com.variamos.io.ConsoleTextArea;

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
public class OverTwoElementsExpressionSet extends ElementExpressionSet {
	/**
	 * Type of direct Edge from DirectEdgeType enum: Example means_ends
	 */
	private String relationType;
	/**
	 * The source edge for the constraint
	 */
	private InstOverTwoRel instOverTwoRelation;

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
			InstOverTwoRel instOverTwoRelation, int execType, String element) {
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

	public InstOverTwoRel getInstEdge() {
		return instOverTwoRelation;
	}

	private void defineTransformations(String element) {

		SyntaxElement metaGroupDep = instOverTwoRelation
				.getTransSupportMetaElement();
		boolean targetActiveAttribute = false;
		if (instOverTwoRelation.getTargetRelations().size() > 0)
			// TODO support multiple targets
			targetActiveAttribute = (boolean) ((InstPairwiseRel) instOverTwoRelation
					.getTargetRelations().get(0)).getTargetRelations().get(0)
					.getInstAttribute("Active").getValue();
		if (targetActiveAttribute && metaGroupDep != null) {
			List<InstAttribute> semGD = ((SyntaxElement) instOverTwoRelation
					.getTransSupportMetaElement()).getOpersRelationTypes();
			relationType = (String) instOverTwoRelation.getInstAttribute(
					"relationType").getValue();
			for (InstAttribute att : semGD) {
				if (att.getAttribute().getDisplayName().equals(relationType)) {
					relationType = (String) att
							.getInstAttributeAttribute("Identifier");
					break;
				}
			}
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
			if (relationType.equals("range")) {
				InstAttribute att = instOverTwoRelation
						.getInstAttribute("LowRange");
				int i = 0;
				Object obj = att.getValue();
				if (obj instanceof String)
					i = Integer.parseInt((String) obj);
				else
					i = (int) att.getValue();

				AbstractExpression r = new EqualsComparisonExpression(
						instOverTwoRelation, instOverTwoRelation
								.getInstAttribute("LowRange").getIdentifier(),
						getHlclFactory().number(i));
				getElementExpressions().add(r);
				allList.add(r);

				att = instOverTwoRelation.getInstAttribute("LowRange");
				obj = att.getValue();
				if (obj instanceof String)
					i = Integer.parseInt((String) obj);
				else
					i = (int) att.getValue();

				r = new EqualsComparisonExpression(instOverTwoRelation,
						instOverTwoRelation.getInstAttribute("HighRange")
								.getIdentifier(), getHlclFactory().number(i));
				getElementExpressions().add(r);
				allList.add(r);
			}
			if (relationType.equals("and") || element == null
					|| !element.equals("Core"))
				for (String sourceName : instOverTwoRelation
						.getSourcePositiveAttributeNames()) {
					AbstractExpression abstractTransformation = null;
					Iterator<InstElement> instEdges1 = instOverTwoRelation
							.getSourceRelations().iterator();
					AbstractExpression iterativeExpression1 = null;
					if (instEdges1.hasNext()) {
						InstElement left1 = instEdges1.next();
						while (((InstPairwiseRel) left1).getSourceRelations()
								.size() != 0
								&& (boolean) ((InstPairwiseRel) left1)
										.getSourceRelations().get(0)
										.getInstAttribute("Active").getValue() == false) {
							if (instEdges1.hasNext())
								left1 = instEdges1.next();
							else
								return;
						}
						switch (relationType) {
						case "and":
						case "none":
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
							// Iterator<InstElement> instEdges2 =
							// instOverTwoRelation
							// .getSourceRelations().iterator();
							// instEdges2.next(); // TODO eliminate duplicated
							// edges
							// from collection and remove this
							// line
							/*
							 * InstElement left2 = instEdges2.next();
							 * Constructor<?> constructor3 = null, constructor4
							 * = null; try { constructor3 =
							 * abstractTransformation
							 * .getClass().getConstructor( InstElement.class,
							 * String.class, Boolean.TYPE,
							 * AbstractExpression.class); constructor4 =
							 * abstractTransformation
							 * .getClass().getConstructor( InstElement.class,
							 * InstElement.class, String.class, String.class); }
							 * catch (NoSuchMethodException | SecurityException
							 * e) { e.printStackTrace(); }
							 */
							// recursiveExpression2 =
							// transformation(constructor3,
							// constructor4, instEdges2, left2, sourceName);
							break;
						case "":
							return;

						default:
							return;
						}

						Constructor<?> constructor1 = null, constructor2 = null;
						try {

							if (left1.getSupInstEleId().equals(
									"GroupSoftFromRelation")) {
								constructor1 = abstractTransformation
										.getClass().getConstructor(
												AbstractExpression.class,
												AbstractExpression.class);
								constructor2 = abstractTransformation
										.getClass().getConstructor(
												InstElement.class,
												InstElement.class,
												String.class, String.class);

							} else {
								constructor1 = abstractTransformation
										.getClass().getConstructor(
												InstElement.class,
												String.class, Boolean.TYPE,
												AbstractExpression.class);
								constructor2 = abstractTransformation
										.getClass().getConstructor(
												InstElement.class,
												InstElement.class,
												String.class, String.class);
							}
						} catch (NoSuchMethodException | SecurityException e) {
							ConsoleTextArea.addText(e.getStackTrace());
						}

						switch (relationType) {
						case "and":
						case "none":
							// B_Satisfied #<=> ( ( A1_"attribute" #/\
							// A2_"attribute" ) #/\ ... )
						case "or":
							// B_Satisfied #<=> ( ( A1_"attribute" #\/
							// A2_"attribute" ) #\/ ... )
							iterativeExpression1 = transformation(constructor1,
									constructor2, instEdges1, left1, sourceName);
							AbstractBooleanExpression out = null;
							if (!relationType.equals("none"))
								out = new DoubleImplicationBooleanExpression(
										instOverTwoRelation, sourceName, true,
										iterativeExpression1);
							else {
								AbstractBooleanExpression negation = new NotBooleanExpression(
										instOverTwoRelation, sourceName);
								out = new DoubleImplicationBooleanExpression(
										negation, iterativeExpression1);
							}

							getElementExpressions().add(out);
							if (relationType.equals("and"))
								coreList.add(out);
							allList.add(out);
							break;
						case "mutex":
							// (( ( A1_"attribute" + A2_"attribute"
							// ) + ... ) #<= 1)
							iterativeExpression1 = transformation(constructor1,
									constructor2, instEdges1, left1, sourceName);
							LessOrEqualsBooleanExpression out2 = new LessOrEqualsBooleanExpression(
									iterativeExpression1,
									new NumberNumericExpression(1));
							getElementExpressions().add(out2);
							allList.add(out2);
							// B_Satisfied #<=> (( ( A1_"attribute" +
							// A2_"attribute"
							// ) + ... ) #<=> 1)
							AbstractExpression transformation1 = new EqualsComparisonExpression(
									iterativeExpression1,
									new NumberNumericExpression(1));
							AbstractBooleanExpression out3 = new DoubleImplicationBooleanExpression(
									instOverTwoRelation, sourceName, true,
									transformation1);
							getElementExpressions().add(out3);
							allList.add(out3);
							break;
						case "range":

							// B_"attribute" #<=> ( ( ( ( A1_"attribute" +
							// A2_"attribute" ) + ... ) #>= GD_LowRange)
							// #/\
							// ( ( ( A1_"attribute" + A2_"attribute" ) + ... )
							// #<=
							// GD_HighRange ) )
							iterativeExpression1 = transformation(constructor1,
									constructor2, instEdges1, left1, sourceName);
							AbstractExpression transformation3 = new LessOrEqualsBooleanExpression(
									instOverTwoRelation, "LowRange", true,
									iterativeExpression1);

							AbstractExpression transformation4 = new GreaterOrEqualsBooleanExpression(
									instOverTwoRelation, "HighRange", true,
									iterativeExpression1);

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
									.getInstAttribute("LowRange")
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
			while ((boolean) ((InstPairwiseRel) instEdge).getSourceRelations()
					.get(0).getInstAttribute("Active").getValue() == false) {
				if (instEdges.hasNext())
					instEdge = instEdges.next();
				else
					// TODO define a cleaner way to deal with group relations
					// with one
					// element
					return new AndBooleanExpression(((InstPairwiseRel) left)
							.getSourceRelations().get(0),
							((InstPairwiseRel) left).getSourceRelations()
									.get(0), sourceName, sourceName);
			}
			if (instEdges.hasNext()) {
				try {
					if (left.getSupInstEleId().equals("GroupSoftFromRelation")) {
						InstElement softgoal = left.getSourceRelations().get(0);
						AbstractExpression out21a;
						/*
						 * String sourceSatisficingType = (String) softgoal
						 * .getInstAttribute("satisficingType").getValue();
						 * 
						 * // TargetId_SDReqLevel #= relId_SourceLevel if
						 * (sourceSatisficingType.contains("low")) {
						 * 
						 * out21a = new LessOrEqualsBooleanExpression( softgoal,
						 * left, "ClaimExpLevel", "sourceLevel"); } else if
						 * (sourceSatisficingType.contains("high")) {
						 * 
						 * out21a = new GreaterOrEqualsBooleanExpression(
						 * softgoal, left, "ClaimExpLevel", "sourceLevel"); }
						 * else {
						 */
						out21a = new EqualsComparisonExpression(softgoal, left,
								"ClaimExpLevel", "sourceLevel");
						// }
						return (AbstractExpression) constructor1.newInstance(
								out21a,
								transformation(constructor1, constructor2,
										instEdges, instEdge, sourceName));
					} else {
						return (AbstractExpression) constructor1.newInstance(
								((InstPairwiseRel) left).getSourceRelations()
										.get(0),
								sourceName,
								true,
								transformation(constructor1, constructor2,
										instEdges, instEdge, sourceName));
					}
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					ConsoleTextArea.addText(e.getStackTrace());
				}
			} else
				try {
					if (left.getSupInstEleId().equals("GroupSoftFromRelation")) {
						InstElement softgoalleft = left.getSourceRelations()
								.get(0);
						AbstractExpression out21a;
						/*
						 * String leftSatisficingType = (String) softgoalleft
						 * .getInstAttribute("satisficingType").getValue();
						 * 
						 * // TargetId_SDReqLevel #= relId_SourceLevel
						 * 
						 * if (leftSatisficingType.contains("low")) {
						 * 
						 * out21a = new LessOrEqualsBooleanExpression(
						 * softgoalleft, left, "ClaimExpLevel", "sourceLevel");
						 * } else if (leftSatisficingType.contains("high")) {
						 * 
						 * out21a = new GreaterOrEqualsBooleanExpression(
						 * softgoalleft, left, "ClaimExpLevel", "sourceLevel");
						 * } else {
						 */
						out21a = new EqualsComparisonExpression(softgoalleft,
								left, "ClaimExpLevel", "sourceLevel");
						// }

						InstElement softgoalInstEdge = instEdge
								.getSourceRelations().get(0);
						AbstractExpression out22a;
						/*
						 * String instEdgeSatisficingType = (String)
						 * softgoalInstEdge
						 * .getInstAttribute("satisficingType").getValue();
						 * 
						 * // TargetId_SDReqLevel #= relId_SourceLevel
						 * 
						 * if (instEdgeSatisficingType.contains("low")) {
						 * 
						 * out22a = new LessOrEqualsBooleanExpression(
						 * softgoalInstEdge, instEdge, "ClaimExpLevel",
						 * "sourceLevel"); } else if
						 * (instEdgeSatisficingType.contains("high")) {
						 * 
						 * out22a = new GreaterOrEqualsBooleanExpression(
						 * softgoalInstEdge, instEdge, "ClaimExpLevel",
						 * "sourceLevel"); } else {
						 */
						out22a = new EqualsComparisonExpression(
								softgoalInstEdge, instEdge, "ClaimExpLevel",
								"sourceLevel");
						// }
						return (AbstractExpression) constructor1.newInstance(
								out21a, out22a);
					} else {
						return (AbstractExpression) constructor2.newInstance(
								((InstPairwiseRel) left).getSourceRelations()
										.get(0), ((InstPairwiseRel) instEdge)
										.getSourceRelations().get(0),
								sourceName, sourceName);
					}
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					ConsoleTextArea.addText(e.getStackTrace());
				}
		} else
		// TODO define a cleaner way to deal with group relations with one
		// element
		if (constructor1.getDeclaringClass().getSimpleName()
				.equals("SumNumericExpression"))
			return new SumNumericExpression(((InstPairwiseRel) left)
					.getSourceRelations().get(0), sourceName, true,
					(new HlclFactory()).number(0));
		else
			return new AndBooleanExpression(((InstPairwiseRel) left)
					.getSourceRelations().get(0), ((InstPairwiseRel) left)
					.getSourceRelations().get(0), sourceName, sourceName);
		return null;
	}
}
