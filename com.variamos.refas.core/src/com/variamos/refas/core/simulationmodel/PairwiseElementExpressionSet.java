package com.variamos.refas.core.simulationmodel;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.mxgraph.util.mxResources;
import com.variamos.refas.core.expressions.AndBooleanExpression;
import com.variamos.refas.core.expressions.DiffNumericExpression;
import com.variamos.refas.core.expressions.DoubleImplicationBooleanExpression;
import com.variamos.refas.core.expressions.EqualsComparisonExpression;
import com.variamos.refas.core.expressions.GreaterOrEqualsBooleanExpression;
import com.variamos.refas.core.expressions.ImplicationBooleanExpression;
import com.variamos.refas.core.expressions.LessOrEqualsBooleanExpression;
import com.variamos.refas.core.expressions.LiteralBooleanExpression;
import com.variamos.refas.core.expressions.NotBooleanExpression;
import com.variamos.refas.core.expressions.NumberNumericExpression;
import com.variamos.refas.core.expressions.OrBooleanExpression;
import com.variamos.refas.core.expressions.SumNumericExpression;
import com.variamos.refas.core.sematicsmetamodel.SemanticPairwiseRelation;
import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaPairwiseRelation;

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
public class PairwiseElementExpressionSet extends MetaExpressionSet {
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
			InstPairwiseRelation instPairwiseRelation) {
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
		defineTransformations();
	}

	public String getDirectEdgeType() {
		return relationType;
	}

	public InstPairwiseRelation getInstPairwiseRelation() {
		return instPairwiseRelation;
	}

	private void defineTransformations() {
		// instPairwiseRelation.getSourceRelations()
		// .get(0).setOptional(false);
		MetaPairwiseRelation metaPairwiseRelation = instPairwiseRelation
				.getMetaPairwiseRelation();
		boolean sourceActiveAttribute = (boolean) instPairwiseRelation
				.getSourceRelations().get(0).getInstAttribute("Active")
				.getValue();
		boolean targetActiveAttribute = (boolean) instPairwiseRelation
				.getTargetRelations().get(0).getInstAttribute("Active")
				.getValue();
		boolean activeVertex = false;
		instPairwiseRelation.setOptional(false);
		if (sourceActiveAttribute && targetActiveAttribute)
			activeVertex = true;
		if (activeVertex
				&& metaPairwiseRelation != null
				&& instPairwiseRelation
						.getInstAttribute(SemanticPairwiseRelation.VAR_RELATIONTYPE_IDEN) != null
				&& !(instPairwiseRelation.getTargetRelations().get(0) instanceof InstOverTwoRelation)
				&& instPairwiseRelation.getInstAttribute(
						SemanticPairwiseRelation.VAR_RELATIONTYPE_IDEN)
						.getValue() != null) {
			relationType = ((String) instPairwiseRelation.getInstAttribute(
					SemanticPairwiseRelation.VAR_RELATIONTYPE_IDEN).getValue())
					.trim().replace(" ", "_");
			setDescription(getDescription() + relationType);
			Set<String> sourceAttributeNames = new HashSet<String>();
			switch (relationType) {

			case "preferred":
				sourceAttributeNames.add("Satisfied");
				sourceAttributeNames.add("NotPrefSelected");
				// ( ( SourceId_Satisfied #/\ targetId_Satisfied ) #/\
				// ( 1 - SourceId_NotPrefSelected )
				// ) #==> ( SourceId_NotPrefSelected #= 0)
				AbstractBooleanExpression transformation1 = new AndBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Satisfied", "Satisfied");
				AbstractBooleanExpression transformation2 = new NotBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"NotPrefSelected");
				AbstractBooleanExpression transformation3 = new AndBooleanExpression(
						transformation2, transformation1);
				/*
				 * AbstractComparisonExpression transformation4 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getSourceRelations().get(0),
				 * "NotPrefSelected", getHlclFactory().number(1));
				 * 
				 * getTransformations().add( new
				 * ImplicationBooleanExpression(transformation3,
				 * transformation4));
				 */
				break;
			case "required":
				sourceAttributeNames.add("Selected");
				// sourceAttributeNames.add("Core");
				// (( 1 - SourceId_Selected) + targetId_Selected) #>= 1
				AbstractNumericExpression transformation6 = new DiffNumericExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"Selected", false, getHlclFactory().number(1));
				AbstractNumericExpression transformation7 = new SumNumericExpression(
						instPairwiseRelation.getTargetRelations().get(0),
						"Selected", false, transformation6);
				getElementExpressions().add(
						new GreaterOrEqualsBooleanExpression(transformation7,
								new NumberNumericExpression(1)));
				// SourceId_Core #==>
				// targetId_Core #= 1
				/*
				 * AbstractComparisonExpression transformation9 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getTargetRelations().get(0), "Core",
				 * getHlclFactory().number(1)); getElementExpressions().add( new
				 * ImplicationBooleanExpression
				 * (instPairwiseRelation.getSourceRelations().get(0), "Core",
				 * true, transformation9));
				 */
				break;
			case "conflict":

				sourceAttributeNames.add("Satisfied");
				// sourceAttributeNames.add("SatisfactionConflict");
				// ((SourceId_Satisfied) + targetId_Satisfied) #<= 1

				AbstractNumericExpression transformation76 = new SumNumericExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getTargetRelations().get(0),
						"Satisfied", "Satisfied");
				getElementExpressions().add(
						new LessOrEqualsBooleanExpression(transformation76,
								new NumberNumericExpression(1)));
				// SourceId_Satisfied #==> targetId_SatisfactionConflict #= 1
				/*
				 * AbstractComparisonExpression transformation8 = new
				 * EqualsComparisonExpression(
				 * instEdge.getTargetRelations().get(0), "SatisfactionConflict",
				 * getHlclFactory().number(1)); getTransformations().add( new
				 * ImplicationBooleanExpression(instEdge
				 * .getSourceRelations().get(0), "Satisfied", true,
				 * transformation8));
				 * 
				 * // targetId_Satisfied #==> SourceId_SatisfactionConflict #= 1
				 * AbstractComparisonExpression transformation9 = new
				 * EqualsComparisonExpression(
				 * instEdge.getSourceRelations().get(0), "SatisfactionConflict",
				 * getHlclFactory().number(1)); getTransformations().add( new
				 * ImplicationBooleanExpression(instEdge
				 * .getTargetRelations().get(0), "Satisfied", true,
				 * transformation9));
				 */
				break;
			case "alternative":
				sourceAttributeNames.add("Satisfied");
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
			case "means_ends":
				sourceAttributeNames.add("Selected");
			case "implication":
				sourceAttributeNames.add("NextReqSatisfied");
				// sourceAttributeNames.add("Core");
				// SourceId_Satisfied #==> targetId_NextReqSatisfied #= 1
				AbstractComparisonExpression transformation16 = new EqualsComparisonExpression(
						instPairwiseRelation.getTargetRelations().get(0),
						"NextReqSatisfied", getHlclFactory().number(1));
				getElementExpressions().add(
						new ImplicationBooleanExpression(instPairwiseRelation
								.getSourceRelations().get(0), "Satisfied",
								true, transformation16));
				// No break to include the following expression
				// sourceId_Core #==>
				// targetId_Core #= 1
				/*
				 * AbstractComparisonExpression transformation199 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getTargetRelations().get(0), "Core",
				 * getHlclFactory().number(1)); getElementExpressions().add( new
				 * ImplicationBooleanExpression
				 * (instPairwiseRelation.getSourceRelations().get(0), "Core",
				 * true, transformation199));
				 */
				break;
			case "implementation":

				sourceAttributeNames.add("NextReqSelected");
				// sourceAttributeNames.add("Core");
				// targetId_NextReqSelected #==> SourceId_NextReqSelected #= 1
				AbstractComparisonExpression transformation18 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"NextReqSelected", getHlclFactory().number(1));
				getElementExpressions().add(
						new ImplicationBooleanExpression(instPairwiseRelation
								.getTargetRelations().get(0),
								"NextReqSelected", true, transformation18));
				// targetId_NextPrefSelected #==> SourceId_NextReqSelected #= 1
				AbstractComparisonExpression transformation188 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"NextReqSelected", getHlclFactory().number(1));
				getElementExpressions().add(
						new ImplicationBooleanExpression(instPairwiseRelation
								.getTargetRelations().get(0),
								"NextPrefSelected", true, transformation188));
				// targetId_Core #==>
				// SourceId_Core #= 1
				/*
				 * AbstractComparisonExpression transformation189 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getSourceRelations().get(0), "Core",
				 * getHlclFactory().number(1)); getElementExpressions().add( new
				 * ImplicationBooleanExpression
				 * (instPairwiseRelation.getTargetRelations().get(0), "Core",
				 * true, transformation189));
				 */
				break;
			case "mandatory":
				sourceAttributeNames.add("NextReqSelected");
				sourceAttributeNames.add("Selected");
				// sourceAttributeNames.add("Core");
				// SourceId_Selected #==> targetId_ValidationSelected #=1
				/*
				 * AbstractComparisonExpression transformation19 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getTargetRelations().get(0),
				 * "ValidationSelected", getHlclFactory().number(1));
				 * getTransformations().add( new
				 * ImplicationBooleanExpression(instPairwiseRelation
				 * .getSourceRelations().get(0), "Selected", true,
				 * transformation19));
				 */

				// targetId_ConfigSelected #==> SourceId_NextReqSelected #=1 #\/
				// SourceId_ConfigSelected #=1
				AbstractComparisonExpression transformation201 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"NextReqSelected", getHlclFactory().number(1));
				AbstractComparisonExpression transformation202 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"ConfigSelected", getHlclFactory().number(1));
				AbstractBooleanExpression transformation203 = new OrBooleanExpression(
						transformation201, transformation202);
				getElementExpressions().add(
						new ImplicationBooleanExpression(instPairwiseRelation
								.getTargetRelations().get(0), "ConfigSelected",
								true, transformation203));

				// targetId_NextPrefSelected #==> SourceId_NextReqSelected #=1
				// #\/ SourceId_ConfigSelected #=1
				AbstractComparisonExpression transformation200 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"NextReqSelected", getHlclFactory().number(1));
				AbstractComparisonExpression transformation199 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"ConfigSelected", getHlclFactory().number(1));
				AbstractBooleanExpression transformation198 = new OrBooleanExpression(
						transformation199, transformation200);
				getElementExpressions().add(
						new ImplicationBooleanExpression(instPairwiseRelation
								.getTargetRelations().get(0),
								"NextPrefSelected", true, transformation198));

				// targetId_NextReqSelected #==> SourceId_NextReqSelected #=1
				// #\/ SourceId_ConfigSelected #=1
				AbstractComparisonExpression transformation20 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"NextReqSelected", getHlclFactory().number(1));
				AbstractComparisonExpression transformation212 = new EqualsComparisonExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"ConfigSelected", getHlclFactory().number(1));
				AbstractBooleanExpression transformation213 = new OrBooleanExpression(
						transformation20, transformation212);
				getElementExpressions().add(
						new ImplicationBooleanExpression(instPairwiseRelation
								.getTargetRelations().get(0),
								"NextReqSelected", true, transformation213));
				// targetId_Core #==>
				// SourceId_Core #= 1
				/*
				 * AbstractComparisonExpression transformation200 = new
				 * EqualsComparisonExpression(
				 * instPairwiseRelation.getSourceRelations().get(0), "Core",
				 * getHlclFactory().number(1)); getElementExpressions().add( new
				 * ImplicationBooleanExpression
				 * (instPairwiseRelation.getTargetRelations().get(0), "Core",
				 * true, transformation200));
				 */
			case "optional":
				// SourceId_Selected #>= targetId_Selected
				getElementExpressions().add(
						new LessOrEqualsBooleanExpression(instPairwiseRelation
								.getSourceRelations().get(0),
								instPairwiseRelation.getTargetRelations()
										.get(0), "Selected", "Selected"));

				// targetId_Optional #= 1
				getElementExpressions().add(
						new EqualsComparisonExpression(instPairwiseRelation
								.getTargetRelations().get(0), "Optional",
								getHlclFactory().number(1)));
				if (relationType.equals("optional"))
					instPairwiseRelation.setOptional(true);

				break;
			case "claim":

				sourceAttributeNames.add("Selected");
				// SourceId_ClaimSelected #<=> SourceId_Selected #/\
				// SourceId_CompExp #==>
				// targetId_level #= R_level
				// TODO review
				getElementExpressions().add(
						new EqualsComparisonExpression(instPairwiseRelation
								.getSourceRelations().get(0), "CompExp",
								getHlclFactory().number(1)));
				AbstractBooleanExpression transformation21 = new AndBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						instPairwiseRelation.getSourceRelations().get(0),
						"Selected", "CompExp");
				EqualsComparisonExpression transformation22 = new EqualsComparisonExpression(
						instPairwiseRelation.getTargetRelations().get(0),
						instPairwiseRelation, "level", "level");

				AbstractBooleanExpression transformation23 = new ImplicationBooleanExpression(
						transformation21, transformation22);
				getElementExpressions().add(
						new DoubleImplicationBooleanExpression(
								instPairwiseRelation.getSourceRelations()
										.get(0), "ClaimSelected", true,
								transformation23));
				break;
			case "softdependency":
				// SourceId_SD #<=> SourceId_CompExp #==>
				// targetId_level #=
				// R_level
				// TODO review
				getElementExpressions().add(
						new EqualsComparisonExpression(instPairwiseRelation
								.getSourceRelations().get(0), "CompExp",
								getHlclFactory().number(1)));
				EqualsComparisonExpression transformation24 = new EqualsComparisonExpression(
						instPairwiseRelation.getTargetRelations().get(0),
						instPairwiseRelation, "level", "level");

				AbstractBooleanExpression transformation25 = new ImplicationBooleanExpression(
						instPairwiseRelation.getSourceRelations().get(0),
						"CompExp", true, transformation24);
				getElementExpressions().add(
						new DoubleImplicationBooleanExpression(
								instPairwiseRelation.getSourceRelations()
										.get(0), "SDSelected", true,
								transformation25));

				break;
			case "generalConstraint":
				String attributeValue = (String) instPairwiseRelation
						.getInstAttribute("generalConstraint").getValue();
				if (attributeValue != null && !attributeValue.equals(""))
					getElementExpressions().add(
							new LiteralBooleanExpression(attributeValue));

				break;
			case "none":
				break;
			}
			InstElement instVertex = instPairwiseRelation.getSourceRelations()
					.get(0);
			if (instVertex instanceof InstOverTwoRelation) {
				((InstOverTwoRelation) instVertex).clearSourceAttributeNames();
				((InstOverTwoRelation) instVertex)
						.addSourceAttributeNames(sourceAttributeNames);
			}

		}

	}
}
