package com.variamos.dynsup.staticexpr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mxgraph.util.mxResources;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstOverTwoRel;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.staticexprsup.AbstractBooleanExpression;
import com.variamos.dynsup.staticexprsup.AbstractComparisonExpression;
import com.variamos.dynsup.staticexprsup.AbstractExpression;
import com.variamos.dynsup.staticexprsup.AbstractNumericExpression;
import com.variamos.dynsup.staticexprsup.AndBooleanExpression;
import com.variamos.dynsup.staticexprsup.DiffNumericExpression;
import com.variamos.dynsup.staticexprsup.DoubleImplicationBooleanExpression;
import com.variamos.dynsup.staticexprsup.EqualsComparisonExpression;
import com.variamos.dynsup.staticexprsup.GreaterOrEqualsBooleanExpression;
import com.variamos.dynsup.staticexprsup.ImplicationBooleanExpression;
import com.variamos.dynsup.staticexprsup.LessOrEqualsBooleanExpression;
import com.variamos.dynsup.staticexprsup.NotBooleanExpression;
import com.variamos.dynsup.staticexprsup.NumberNumericExpression;
import com.variamos.dynsup.staticexprsup.SumNumericExpression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.Identifier;

//TODO refactor: PairwiseElementExpressionSet
/**
 * A class to represent the constraints for direct relations. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public class PairwiseElementExpressionSet extends ElementExpressionSet {
	/**
	 * Type of direct Edge from DirectEdgeType enum: Example means_ends
	 */
	private String relationType;
	/**
	 * The source edge for the constraint
	 */
	private InstPairwiseRel instPairwiseRelation;

	/**
	 * Create the Constraint with all required parameters
	 * 
	 * @param identifier
	 * @param description
	 * @param directEdgeType
	 * @param source
	 * @param target
	 */
	public PairwiseElementExpressionSet(String identifier,
			Map<String, Identifier> idMap, HlclFactory hlclFactory,
			InstPairwiseRel instPairwiseRelation, int execType) {
		super(identifier, mxResources.get("defect-pairrelations1")
				+ " "
				+ instPairwiseRelation.getSourceRelations().get(0)
						.getIdentifier()
				+ mxResources.get("defect-pairrelations1")
				+ " "
				+ instPairwiseRelation.getTargetRelations().get(0)
						.getIdentifier()
				+ mxResources.get("defect-pairrelations1") + " ", idMap,
				hlclFactory);
		this.instPairwiseRelation = instPairwiseRelation;
		defineTransformations(execType);
	}

	public String getDirectEdgeType() {
		return relationType;
	}

	public InstPairwiseRel getInstPairwiseRelation() {
		return instPairwiseRelation;
	}

	private void defineTransformations(int execType) {
		// instPairwiseRelation.getSourceRelations()
		// .get(0).setOptional(false);
		SyntaxElement metaPairwiseRelation = instPairwiseRelation
				.getMetaPairwiseRelation();
		boolean sourceActiveAttribute = true;
		if (instPairwiseRelation.getSourceRelations().get(0)
				.getInstAttribute("Active") != null) {
			sourceActiveAttribute = (boolean) instPairwiseRelation
					.getSourceRelations().get(0).getInstAttribute("Active")
					.getValue();
		}
		boolean targetActiveAttribute = true;
		if (instPairwiseRelation.getTargetRelations().get(0)
				.getInstAttribute("Active") != null) {
			targetActiveAttribute = (boolean) instPairwiseRelation
					.getTargetRelations().get(0).getInstAttribute("Active")
					.getValue();
		}
		boolean activeVertex = false;
		instPairwiseRelation.setOptional(false);
		if (sourceActiveAttribute && targetActiveAttribute)
			activeVertex = true;
		Set<String> sourcePositiveAttributeNames = new HashSet<String>();
		Set<String> sourceNegativeAttributeNames = new HashSet<String>();

		List<AbstractExpression> structureList = new ArrayList<AbstractExpression>();
		List<AbstractExpression> allList = new ArrayList<AbstractExpression>();

		if (activeVertex
				&& metaPairwiseRelation != null
				&& instPairwiseRelation.getInstAttribute("relationType") != null
				&& !(instPairwiseRelation.getTargetRelations().get(0) instanceof InstOverTwoRel)
				&& instPairwiseRelation.getInstAttribute("relationType")
						.getValue() != null) {
			relationType = ((String) instPairwiseRelation.getInstAttribute(
					"relationType").getValue()).trim().replace(" ", "_");
			setDescription(getDescription() + relationType);

			// System.out.print(relationType + " ");
			switch (relationType) {

			case "preferred":
				sourcePositiveAttributeNames.add("Sel");
				// ( ( SourceId_Satisfied #/\ targetId_Satisfied ) #/\
				// ( 1 - SourceId_NotPrefSelected )
				// ) #==> ( SourceId_NotPrefSelected #= 0)
				AbstractBooleanExpression transformation1 = new AndBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Sel", "Sel");
				AbstractBooleanExpression transformation2 = new NotBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0), "Sel");
				AbstractBooleanExpression transformation3 = new AndBooleanExpression(
						transformation2, transformation1);
				getElementExpressions().add(transformation3);
				allList.add(transformation3);
				break;
			case "require":
				sourcePositiveAttributeNames.add("Sel");
				// sourceNegativeAttributeNames.add("Exclu");
				// sourceAttributeNames.add("Core");

				// (( 1 - SourceId_Selected) + targetId_Selected) #>= 1
				AbstractNumericExpression transformation6 = new DiffNumericExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"Sel", false, getHlclFactory().number(1));
				AbstractNumericExpression transformation7 = new SumNumericExpression(
						instPairwiseRelation.getTargetRelations().get(0),
						"Sel", false, transformation6);
				AbstractBooleanExpression out10 = new GreaterOrEqualsBooleanExpression(
						transformation7, new NumberNumericExpression(1));
				getElementExpressions().add(out10);
				// structureList.add(out10);
				allList.add(out10);

				/*
				 * // ((targetId_NotAvailable) #=> sourceId_Selected) #= 0
				 * EqualsComparisonExpression transformation73 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getSourceRelations().get(0), "Sel",
				 * true, new NumberNumericExpression(0));
				 * AbstractBooleanExpression out90 = new
				 * ImplicationBooleanExpression(
				 * instPairwiseRelation.getTargetRelations().get(0), "Exclu",
				 * true, transformation73); getElementExpressions().add(out90);
				 * allList.add(out90);
				 */
				break;
			case "excludes":
			case "conflict":

				sourcePositiveAttributeNames.add("Sel");
				sourceNegativeAttributeNames.add("Exclu");
				// sourceAttributeNames.add("SatisfactionConflict");

				// ((SourceId_Selected) + targetId_Selected) #<= 1
				AbstractNumericExpression transformation76 = new SumNumericExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Sel", "Sel");
				AbstractBooleanExpression out9 = new LessOrEqualsBooleanExpression(
						transformation76, new NumberNumericExpression(1));
				getElementExpressions().add(out9);
				allList.add(out9);

				/*
				 * // ((SourceId_Selected) #=> targetId_Selected) #= 0
				 * EqualsComparisonExpression transformation75 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getTargetRelations().get(0), "Sel",
				 * true, new NumberNumericExpression(0));
				 * AbstractBooleanExpression out99 = new
				 * ImplicationBooleanExpression(
				 * instPairwiseRelation.getSourceRelations().get(0), "Sel",
				 * true, transformation75); getElementExpressions().add(out99);
				 * allList.add(out99);
				 */
				// ((targetId_Selected) #=> sourceId_NotAvailable) #= 1
				/*
				 * EqualsComparisonExpression transformation74 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getSourceRelations().get(0), "Exclu",
				 * true, new NumberNumericExpression(1));
				 * AbstractBooleanExpression out98 = new
				 * ImplicationBooleanExpression(
				 * instPairwiseRelation.getTargetRelations().get(0), "Sel",
				 * true, transformation74); getElementExpressions().add(out98);
				 * allList.add(out98);
				 */
				break;
			case "alternative":
				sourcePositiveAttributeNames.add("Sel");
				// sourceAttributeNames.add("ValidationSelected");
				// sourceAttributeNames.add("AlternativeSelected");
				// ( ( ( 1 - SourceId_Satisfied ) #/\ targetId_Satisfied ) #/\
				// SourceId_ValidationSelected ) ) #==> (
				// SourceId_AlternativeSatisfied #= 1 #/\
				// targetId_ValidationSelected #= 1 )
				/*
				 * AbstractBooleanExpression transformation10 = new
				 * NotBooleanExpression(
				 * instPairwiseRelation.getSourceRelations().get(0),
				 * "Satisfied"); AbstractBooleanExpression transformation11 =
				 * new AndBooleanExpression(
				 * instPairwiseRelation.getTargetRelations().get(0),
				 * "Satisfied", false, transformation10);
				 * AbstractBooleanExpression transformation12 = new
				 * AndBooleanExpression(
				 * instPairwiseRelation.getSourceRelations().get(0),
				 * "ValidationSelected", false, transformation11);
				 * AbstractComparisonExpression transformation13 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getSourceRelations().get(0),
				 * "AlternativeSatisfied", getHlclFactory().number(1));
				 * AbstractComparisonExpression transformation14 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getTargetRelations().get(0),
				 * "ValidationSelected", getHlclFactory().number(1));
				 * AbstractBooleanExpression transformation15 = new
				 * AndBooleanExpression( transformation13, transformation14);
				 * getTransformations().add( new
				 * ImplicationBooleanExpression(transformation12,
				 * transformation15));
				 */
				break;

			case "implication":
				sourcePositiveAttributeNames.add("Sel");
				// sourceAttributeNames.add("Core");
				// SourceId_Selected #==> targetId_Selected #= 1
				AbstractComparisonExpression transformation161 = new EqualsComparisonExpression(
						instPairwiseRelation.getTargetRelations().get(0),
						"Sel", getHlclFactory().number(1));
				AbstractBooleanExpression out8 = new ImplicationBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"Sel", true, transformation161);
				getElementExpressions().add(out8);
				structureList.add(out8);
				allList.add(out8);

				break;
			case "OperToClaim":
				sourcePositiveAttributeNames.add("Sel");
				sourceNegativeAttributeNames.add("Exclu");
				// SourceId_Selected #= targetId_Selected
				AndBooleanExpression out43 = new AndBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Sel", "CompExp");
				DoubleImplicationBooleanExpression out46 = new DoubleImplicationBooleanExpression(
						instPairwiseRelation.getTargetRelations().get(0),
						"Sel", true, out43);
				getElementExpressions().add(out46);
				// structureList.add(out46);
				allList.add(out46);
				EqualsComparisonExpression out44 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Exclu", "Exclu");
				getElementExpressions().add(out44);
				// structureList.add(out44);
				allList.add(out44);
				break;
			case "implementation":
			case "implementedBy":
			case "means_ends":
			case "delegation":
			case "mandatory":
				sourcePositiveAttributeNames.add("Sel");
				// sourcePositiveAttributeNames.add("Exclu");
				// SourceId_Selected #= targetId_Selected
				EqualsComparisonExpression out56 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Sel", "Sel");
				getElementExpressions().add(out56);
				structureList.add(out56);
				allList.add(out56);

				EqualsComparisonExpression out54 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Exclu", "Exclu");
				getElementExpressions().add(out54);
				// structureList.add(out54);
				allList.add(out54);
				break;
			case "condition":
				sourcePositiveAttributeNames.add("Sel");
				// sourcePositiveAttributeNames.add("Exclu");
				// SourceId_Selected #= targetId_Selected
				EqualsComparisonExpression out58 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Sel", "Sel");
				getElementExpressions().add(out58);
				// structureList.add(out56);
				allList.add(out58);

				EqualsComparisonExpression out57 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Exclu", "Exclu");
				getElementExpressions().add(out57);
				// structureList.add(out54);
				allList.add(out57);
				break;
			case "optional":
				sourcePositiveAttributeNames.add("Sel");
				sourceNegativeAttributeNames.add("Exclu");
				// sourceNegativeAttributeNames.add("NextNotSatisfied");
				// SourceId_Selected #<= targetId_Selected
				LessOrEqualsBooleanExpression out5 = new LessOrEqualsBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Sel", "Sel");
				getElementExpressions().add(out5);
				structureList.add(out5);
				allList.add(out5);

				// targetId_NotAvailable #<= SourceId_NotAvailable
				LessOrEqualsBooleanExpression out13 = new LessOrEqualsBooleanExpression(
						instPairwiseRelation.getTargetRelations().get(0),
						instPairwiseRelation.getSourceRelations().get(0),
						"Exclu", "Exclu");
				getElementExpressions().add(out13);
				// structureList.add(out12);
				allList.add(out13);

				break;
			case "ClaimToSG":

				sourcePositiveAttributeNames.add("Sel");
				// relId_level #= <<level>>
				EqualsComparisonExpression out21 = new EqualsComparisonExpression(
						instPairwiseRelation, "CLSGLevel", getHlclFactory()
								.number(instPairwiseRelation.getInstAttribute(
										"CLSGLevel").getAsInteger()));
				getElementExpressions().add(out21);
				allList.add(out21);
				InstElement softgoal = instPairwiseRelation
						.getTargetRelations().get(0);

				AbstractExpression out22 = null;

				String satisficingType = (String) softgoal.getInstAttribute(
						"satisficingType").getValue();

				// SourceId_Selected #==> TargetId_ClaimExpLevel #= relId_Level
				if (satisficingType.contains("low")) {

					out22 = new GreaterOrEqualsBooleanExpression(
							instPairwiseRelation.getTargetRelations().get(0),
							instPairwiseRelation, "ClaimExpLevel", "CLSGLevel");
				} else if (satisficingType.contains("high")) {

					out22 = new LessOrEqualsBooleanExpression(
							instPairwiseRelation.getTargetRelations().get(0),
							instPairwiseRelation, "ClaimExpLevel", "CLSGLevel");
				} else {
					out22 = new EqualsComparisonExpression(instPairwiseRelation
							.getTargetRelations().get(0), instPairwiseRelation,
							"ClaimExpLevel", "CLSGLevel");
				}
				AbstractBooleanExpression out23 = new ImplicationBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"Sel", true, out22);
				getElementExpressions().add(out23);
				allList.add(out23);

				break;
			case "contribution":

				sourcePositiveAttributeNames.add("Sel");
				// relId_Sourcelevel #= <<Sourcelevel>>

				InstElement sourceSoftgoal = instPairwiseRelation
						.getSourceRelations().get(0);
				AbstractExpression out21a = null;

				if (sourceSoftgoal.getSupInstEleId().equals("Softgoal")) {
					EqualsComparisonExpression out21p = new EqualsComparisonExpression(
							instPairwiseRelation, "sourceLevel",
							getHlclFactory().number(
									instPairwiseRelation.getInstAttribute(
											"sourceLevel").getAsInteger()));
					getElementExpressions().add(out21p);
					allList.add(out21p);
					/*
					 * String sourceSatisficingType = (String) sourceSoftgoal
					 * .getInstAttribute("satisficingType").getValue();
					 * 
					 * // TargetId_SDReqLevel #= relId_SourceLevel if
					 * (sourceSatisficingType.contains("low")) {
					 * 
					 * out21a = new LessOrEqualsBooleanExpression(
					 * sourceSoftgoal, instPairwiseRelation, "ClaimExpLevel",
					 * "sourceLevel"); } else if
					 * (sourceSatisficingType.contains("high")) {
					 * 
					 * out21a = new GreaterOrEqualsBooleanExpression(
					 * sourceSoftgoal, instPairwiseRelation, "ClaimExpLevel",
					 * "sourceLevel"); } else {
					 */
					out21a = new EqualsComparisonExpression(sourceSoftgoal,
							instPairwiseRelation, "ClaimExpLevel",
							"sourceLevel");
					// }
				} else
					out21a = new EqualsComparisonExpression(sourceSoftgoal,
							"Sel", getHlclFactory().number(1));
				// getElementExpressions().add(out21a);
				// allList.add(out21a);

				// relId_Targetlevel #= <<Targetlevel>>
				EqualsComparisonExpression out22p = new EqualsComparisonExpression(
						instPairwiseRelation, "targetLevel", getHlclFactory()
								.number(instPairwiseRelation.getInstAttribute(
										"targetLevel").getAsInteger()));
				getElementExpressions().add(out22p);
				allList.add(out22p);

				InstElement targetSoftgoal = instPairwiseRelation
						.getTargetRelations().get(0);
				AbstractExpression out22a = null;

				if (targetSoftgoal.getInstAttribute("satisficingType") != null) {
					String targetSatisficingType = (String) targetSoftgoal
							.getInstAttribute("satisficingType").getValue();

					// TargetId_SDReqLevel #= relId_TargetLevel
					if (targetSatisficingType.contains("low")) {

						out22a = new GreaterOrEqualsBooleanExpression(
								targetSoftgoal, instPairwiseRelation,
								"ClaimExpLevel", "targetLevel");
					} else if (targetSatisficingType.contains("high")) {

						out22a = new LessOrEqualsBooleanExpression(
								targetSoftgoal, instPairwiseRelation,
								"ClaimExpLevel", "targetLevel");
					} else {
						out22a = new EqualsComparisonExpression(targetSoftgoal,
								instPairwiseRelation, "ClaimExpLevel",
								"targetLevel");
					}

					AbstractBooleanExpression out23a = new ImplicationBooleanExpression(
							out21a, out22a);
					getElementExpressions().add(out23a);
					allList.add(out23a);
				}

				break;
			case "SD":

				sourcePositiveAttributeNames.add("Sel");
				// relId_level #= <<level>>
				EqualsComparisonExpression out24 = new EqualsComparisonExpression(
						instPairwiseRelation, "level", getHlclFactory().number(
								instPairwiseRelation.getInstAttribute("level") // FIXME
																				// not
																				// level
																				// but?
										.getAsInteger()));
				getElementExpressions().add(out24);
				allList.add(out24);
				// SourceId_ClaimExpLevel #= SourceId_Level
				InstElement softgoalSD = instPairwiseRelation
						.getTargetRelations().get(0);

				AbstractExpression out25 = null;

				String satisficingTypeSD = (String) softgoalSD
						.getInstAttribute("satisficingType").getValue();

				if (satisficingTypeSD.contains("high")) {
					out25 = new GreaterOrEqualsBooleanExpression(
							instPairwiseRelation.getTargetRelations().get(0),
							instPairwiseRelation, "SDReqLevel", "level");
				} else if (satisficingTypeSD.contains("low")) {
					out25 = new LessOrEqualsBooleanExpression(
							instPairwiseRelation.getTargetRelations().get(0),
							instPairwiseRelation, "SDReqLevel", "level");
				} else// (satisficingTypeSD.contains("close"))
				{
					out25 = new EqualsComparisonExpression(instPairwiseRelation
							.getTargetRelations().get(0), instPairwiseRelation,
							"SDReqLevel", "level");
				}
				ImplicationBooleanExpression out26 = new ImplicationBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"Sel", true, out25);
				getElementExpressions().add(out26);
				allList.add(out26);

				DoubleImplicationBooleanExpression out27 = new DoubleImplicationBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getSourceRelations().get(0),
						"Sel", "CompExp");
				getElementExpressions().add(out27);

				break;
			case "none":
				break;
			}

			/*
			 * List<AbstractExpression> parentList = this
			 * .getCompulsoryExpressionList("Parent"); if (parentList != null)
			 * parentList.addAll(structureList); else
			 * this.getCompulsoryExpressions().put("Parent", structureList);
			 */

		}
		if (instPairwiseRelation.getSourceRelations().get(0).getSupInstEleId()
				.equals("Softgoal")) {
			EqualsComparisonExpression out21p = new EqualsComparisonExpression(
					instPairwiseRelation, "sourceLevel", getHlclFactory()
							.number(instPairwiseRelation.getInstAttribute(
									"sourceLevel").getAsInteger()));
			getElementExpressions().add(out21p);
			allList.add(out21p);

		}
		List<AbstractExpression> coreList = this
				.getCompulsoryExpressionList("Core");
		if (coreList != null)
			coreList.addAll(structureList);
		else
			this.getCompulsoryExpressions().put("Core", structureList);
		List<AbstractExpression> falseList = this
				.getCompulsoryExpressionList("FalseOpt");
		if (falseList != null)
			falseList.addAll(allList);
		this.getCompulsoryExpressions().put("FalseOpt", allList); // FIXME not
																	// used

		List<AbstractExpression> falseList2 = this
				.getCompulsoryExpressionList("FalseOpt2");
		if (falseList2 != null)
			falseList2.addAll(allList);
		this.getCompulsoryExpressions().put("FalseOpt2", allList);

		InstElement instVertex = instPairwiseRelation.getSourceRelations().get(
				0);
		if (instVertex instanceof InstOverTwoRel) {
			((InstOverTwoRel) instVertex).clearSourcePositiveAttributeNames();
			((InstOverTwoRel) instVertex).clearSourceNegativeAttributeNames();
			((InstOverTwoRel) instVertex)
					.addSourcePositiveAttributeNames(sourcePositiveAttributeNames);
			((InstOverTwoRel) instVertex)
					.addSourceNegativeAttributeNames(sourceNegativeAttributeNames);
		}

	}
}

