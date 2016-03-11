package com.variamos.semantic.staticexpr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mxgraph.util.mxResources;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.Identifier;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstOverTwoRelation;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.opers.OpersPairwiseRel;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.semantic.expressions.AbstractBooleanExpression;
import com.variamos.semantic.expressions.AbstractComparisonExpression;
import com.variamos.semantic.expressions.AbstractExpression;
import com.variamos.semantic.expressions.AbstractNumericExpression;
import com.variamos.semantic.expressions.AndBooleanExpression;
import com.variamos.semantic.expressions.DiffNumericExpression;
import com.variamos.semantic.expressions.DoubleImplicationBooleanExpression;
import com.variamos.semantic.expressions.EqualsComparisonExpression;
import com.variamos.semantic.expressions.GreaterOrEqualsBooleanExpression;
import com.variamos.semantic.expressions.ImplicationBooleanExpression;
import com.variamos.semantic.expressions.LessOrEqualsBooleanExpression;
import com.variamos.semantic.expressions.NotBooleanExpression;
import com.variamos.semantic.expressions.NumberNumericExpression;
import com.variamos.semantic.expressions.SumNumericExpression;

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
	private InstPairwiseRelation instPairwiseRelation;

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
			InstPairwiseRelation instPairwiseRelation, int execType) {
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

	public InstPairwiseRelation getInstPairwiseRelation() {
		return instPairwiseRelation;
	}

	private void defineTransformations(int execType) {
		// instPairwiseRelation.getSourceRelations()
		// .get(0).setOptional(false);
		MetaElement metaPairwiseRelation = instPairwiseRelation
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
		if (activeVertex
				&& metaPairwiseRelation != null
				&& instPairwiseRelation
						.getInstAttribute(OpersPairwiseRel.VAR_RELATIONTYPE_IDEN) != null
				&& !(instPairwiseRelation.getTargetRelations().get(0) instanceof InstOverTwoRelation)
				&& instPairwiseRelation.getInstAttribute(
						OpersPairwiseRel.VAR_RELATIONTYPE_IDEN)
						.getValue() != null) {
			relationType = ((String) instPairwiseRelation.getInstAttribute(
					OpersPairwiseRel.VAR_RELATIONTYPE_IDEN).getValue())
					.trim().replace(" ", "_");
			setDescription(getDescription() + relationType);
			Set<String> sourcePositiveAttributeNames = new HashSet<String>();
			Set<String> sourceNegativeAttributeNames = new HashSet<String>();

			List<AbstractExpression> structureList = new ArrayList<AbstractExpression>();
			List<AbstractExpression> allList = new ArrayList<AbstractExpression>();

			switch (relationType) {

			case "preferred":
				sourcePositiveAttributeNames.add("Selected");
				// ( ( SourceId_Satisfied #/\ targetId_Satisfied ) #/\
				// ( 1 - SourceId_NotPrefSelected )
				// ) #==> ( SourceId_NotPrefSelected #= 0)
				AbstractBooleanExpression transformation1 = new AndBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Selected", "Selected");
				AbstractBooleanExpression transformation2 = new NotBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"Selected");
				AbstractBooleanExpression transformation3 = new AndBooleanExpression(
						transformation2, transformation1);
				getElementExpressions().add(transformation3);
				allList.add(transformation3);
				break;
			case "require":
				sourcePositiveAttributeNames.add("Selected");
				// sourceNegativeAttributeNames.add("NotAvailable");
				// sourceAttributeNames.add("Core");

				// (( 1 - SourceId_Selected) + targetId_Selected) #>= 1
				AbstractNumericExpression transformation6 = new DiffNumericExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"Selected", false, getHlclFactory().number(1));
				AbstractNumericExpression transformation7 = new SumNumericExpression(
						instPairwiseRelation.getTargetRelations().get(0),
						"Selected", false, transformation6);
				AbstractBooleanExpression out10 = new GreaterOrEqualsBooleanExpression(
						transformation7, new NumberNumericExpression(1));
				getElementExpressions().add(out10);
				allList.add(out10);

				/*
				 * // ((targetId_NotAvailable) #=> sourceId_Selected) #= 0
				 * EqualsComparisonExpression transformation73 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getSourceRelations().get(0), "Selected",
				 * true, new NumberNumericExpression(0));
				 * AbstractBooleanExpression out90 = new
				 * ImplicationBooleanExpression(
				 * instPairwiseRelation.getTargetRelations().get(0),
				 * "NotAvailable", true, transformation73);
				 * getElementExpressions().add(out90); allList.add(out90);
				 */
				break;
			case "conflict":

				sourcePositiveAttributeNames.add("Selected");
				// sourceNegativeAttributeNames.add("NotAvailable");
				// sourceAttributeNames.add("SatisfactionConflict");

				// ((SourceId_Selected) + targetId_Selected) #<= 1
				AbstractNumericExpression transformation76 = new SumNumericExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Selected", "Selected");
				AbstractBooleanExpression out9 = new LessOrEqualsBooleanExpression(
						transformation76, new NumberNumericExpression(1));
				getElementExpressions().add(out9);
				allList.add(out9);

				/*
				 * // ((SourceId_Selected) #=> targetId_Selected) #= 0
				 * EqualsComparisonExpression transformation75 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getTargetRelations().get(0), "Selected",
				 * true, new NumberNumericExpression(0));
				 * AbstractBooleanExpression out99 = new
				 * ImplicationBooleanExpression(
				 * instPairwiseRelation.getSourceRelations().get(0), "Selected",
				 * true, transformation75); getElementExpressions().add(out99);
				 * allList.add(out99);
				 */
				// ((targetId_Selected) #=> sourceId_NotAvailable) #= 1
				/*
				 * EqualsComparisonExpression transformation74 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getSourceRelations().get(0),
				 * "NotAvailable", true, new NumberNumericExpression(1));
				 * AbstractBooleanExpression out98 = new
				 * ImplicationBooleanExpression(
				 * instPairwiseRelation.getTargetRelations().get(0), "Selected",
				 * true, transformation74); getElementExpressions().add(out98);
				 * allList.add(out98);
				 */
				break;
			case "alternative":
				sourcePositiveAttributeNames.add("Selected");
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
				sourcePositiveAttributeNames.add("Selected");
				// sourceAttributeNames.add("Core");
				// SourceId_Selected #==> targetId_Selected #= 1
				AbstractComparisonExpression transformation161 = new EqualsComparisonExpression(
						instPairwiseRelation.getTargetRelations().get(0),
						"Selected", getHlclFactory().number(1));
				AbstractBooleanExpression out8 = new ImplicationBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"Selected", true, transformation161);
				getElementExpressions().add(out8);
				allList.add(out8);

				break;
			case "OperToClaim":
				sourcePositiveAttributeNames.add("Selected");
				sourceNegativeAttributeNames.add("NotAvailable");
				// SourceId_Selected #= targetId_Selected
				AndBooleanExpression out43 = new AndBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Selected", "CompExp");
				DoubleImplicationBooleanExpression out46 = new DoubleImplicationBooleanExpression(
						instPairwiseRelation.getTargetRelations().get(0),
						"Selected", true, out43);
				getElementExpressions().add(out46);
				// structureList.add(out46);
				allList.add(out46);
				EqualsComparisonExpression out44 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"NotAvailable", "NotAvailable");
				getElementExpressions().add(out44);
				// structureList.add(out44);
				allList.add(out44);
				break;
			case "implementation":
			case "means_ends":
			case "delegation":
			case "mandatory":
				sourcePositiveAttributeNames.add("Selected");
				// sourcePositiveAttributeNames.add("NotAvailable");
				// SourceId_Selected #= targetId_Selected
				EqualsComparisonExpression out56 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Selected", "Selected");
				getElementExpressions().add(out56);
				structureList.add(out56);
				allList.add(out56);

				EqualsComparisonExpression out54 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"NotAvailable", "NotAvailable");
				getElementExpressions().add(out54);
				// structureList.add(out54);
				allList.add(out54);
				break;
			case "condition":
				sourcePositiveAttributeNames.add("Selected");
				// sourcePositiveAttributeNames.add("NotAvailable");
				// SourceId_Selected #= targetId_Selected
				EqualsComparisonExpression out58 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Selected", "Selected");
				getElementExpressions().add(out58);
				// structureList.add(out56);
				allList.add(out58);

				EqualsComparisonExpression out57 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"NotAvailable", "NotAvailable");
				getElementExpressions().add(out57);
				// structureList.add(out54);
				allList.add(out57);
				break;
			case "optional":
				sourcePositiveAttributeNames.add("Selected");
				sourceNegativeAttributeNames.add("NotAvailable");
				// sourceNegativeAttributeNames.add("NextNotSatisfied");
				// SourceId_Selected #<= targetId_Selected
				LessOrEqualsBooleanExpression out5 = new LessOrEqualsBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Selected", "Selected");
				getElementExpressions().add(out5);
				structureList.add(out5);
				allList.add(out5);

				// targetId_NotAvailable #<= SourceId_NotAvailable
				LessOrEqualsBooleanExpression out13 = new LessOrEqualsBooleanExpression(
						instPairwiseRelation.getTargetRelations().get(0),
						instPairwiseRelation.getSourceRelations().get(0),
						"NotAvailable", "NotAvailable");
				getElementExpressions().add(out13);
				// structureList.add(out12);
				allList.add(out13);

				break;
			case "ClaimToSG":

				sourcePositiveAttributeNames.add("Selected");
				// relId_level #= <<level>>
				EqualsComparisonExpression out21 = new EqualsComparisonExpression(
						instPairwiseRelation, "CLSGLevel", getHlclFactory()
								.number((Integer) instPairwiseRelation
										.getInstAttribute("CLSGLevel")
										.getAsInteger()));
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
						"Selected", true, out22);
				getElementExpressions().add(out23);
				allList.add(out23);

				break;
			case "contribution":

				sourcePositiveAttributeNames.add("Selected");
				// relId_Sourcelevel #= <<Sourcelevel>>
				EqualsComparisonExpression out21p = new EqualsComparisonExpression(
						instPairwiseRelation,
						OpersPairwiseRel.VAR_SOURCE_LEVEL,
						getHlclFactory()
								.number((Integer) instPairwiseRelation
										.getInstAttribute(
												OpersPairwiseRel.VAR_SOURCE_LEVEL)
										.getAsInteger()));
				getElementExpressions().add(out21p);
				allList.add(out21p);

				InstElement sourceSoftgoal = instPairwiseRelation
						.getSourceRelations().get(0);
				AbstractExpression out21a = null;

				String sourceSatisficingType = (String) sourceSoftgoal
						.getInstAttribute("satisficingType").getValue();

				// TargetId_SDReqLevel #= relId_SourceLevel
				if (sourceSatisficingType.contains("low")) {

					out21a = new LessOrEqualsBooleanExpression(sourceSoftgoal,
							instPairwiseRelation, "SDReqLevel",
							OpersPairwiseRel.VAR_SOURCE_LEVEL);
				} else if (sourceSatisficingType.contains("high")) {

					out21a = new GreaterOrEqualsBooleanExpression(
							sourceSoftgoal, instPairwiseRelation, "SDReqLevel",
							OpersPairwiseRel.VAR_SOURCE_LEVEL);
				} else {
					out21a = new EqualsComparisonExpression(sourceSoftgoal,
							instPairwiseRelation, "SDReqLevel",
							OpersPairwiseRel.VAR_SOURCE_LEVEL);
				}
				// getElementExpressions().add(out21a);
				// allList.add(out21a);

				// relId_Targetlevel #= <<Targetlevel>>
				EqualsComparisonExpression out22p = new EqualsComparisonExpression(
						instPairwiseRelation,
						OpersPairwiseRel.VAR_TARGET_LEVEL,
						getHlclFactory()
								.number((Integer) instPairwiseRelation
										.getInstAttribute(
												OpersPairwiseRel.VAR_TARGET_LEVEL)
										.getAsInteger()));
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

						out22a = new LessOrEqualsBooleanExpression(
								targetSoftgoal, instPairwiseRelation,
								"SDReqLevel",
								OpersPairwiseRel.VAR_TARGET_LEVEL);
					} else if (targetSatisficingType.contains("high")) {

						out22a = new GreaterOrEqualsBooleanExpression(
								targetSoftgoal, instPairwiseRelation,
								"SDReqLevel",
								OpersPairwiseRel.VAR_TARGET_LEVEL);
					} else {
						out22a = new EqualsComparisonExpression(targetSoftgoal,
								instPairwiseRelation, "SDReqLevel",
								OpersPairwiseRel.VAR_TARGET_LEVEL);
					}

					AbstractBooleanExpression out23a = new ImplicationBooleanExpression(
							out21a, out22a);
					getElementExpressions().add(out23a);
					allList.add(out23a);
				}

				break;
			case "SD":

				sourcePositiveAttributeNames.add("Selected");
				// relId_level #= <<level>>
				EqualsComparisonExpression out24 = new EqualsComparisonExpression(
						instPairwiseRelation, "level", getHlclFactory().number(
								(Integer) instPairwiseRelation
										.getInstAttribute("level")
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
						"Selected", true, out25);
				getElementExpressions().add(out26);
				allList.add(out26);

				DoubleImplicationBooleanExpression out27 = new DoubleImplicationBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getSourceRelations().get(0),
						"Selected", "CompExp");
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
			this.getCompulsoryExpressions().put("FalseOpt", allList);

			List<AbstractExpression> falseList2 = this
					.getCompulsoryExpressionList("FalseOpt2");
			if (falseList2 != null)
				falseList2.addAll(allList);
			this.getCompulsoryExpressions().put("FalseOpt2", allList);

			InstElement instVertex = instPairwiseRelation.getSourceRelations()
					.get(0);
			if (instVertex instanceof InstOverTwoRelation) {
				((InstOverTwoRelation) instVertex)
						.clearSourcePositiveAttributeNames();
				((InstOverTwoRelation) instVertex)
						.clearSourceNegativeAttributeNames();
				((InstOverTwoRelation) instVertex)
						.addSourcePositiveAttributeNames(sourcePositiveAttributeNames);
				((InstOverTwoRelation) instVertex)
						.addSourceNegativeAttributeNames(sourceNegativeAttributeNames);
			}

		}

	}
}
