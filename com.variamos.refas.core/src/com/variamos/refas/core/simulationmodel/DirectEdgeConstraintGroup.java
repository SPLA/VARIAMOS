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
import com.variamos.refas.core.expressions.LiteralBooleanExpression;
import com.variamos.refas.core.expressions.NotBooleanExpression;
import com.variamos.refas.core.expressions.NumberNumericExpression;
import com.variamos.refas.core.expressions.SumNumericExpression;
import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodelsupport.MetaPairwiseRelation;
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
public class DirectEdgeConstraintGroup extends AbstractConstraintGroup {
	/**
	 * Type of direct Edge from DirectEdgeType enum: Example means_ends
	 */
	private DirectEdgeType directEdgeType;
	/**
	 * The source edge for the constraint
	 */
	private InstPairwiseRelation instEdge;

	/**
	 * Create the Constraint with all required parameters
	 * 
	 * @param identifier
	 * @param description
	 * @param directEdgeType
	 * @param source
	 * @param target
	 */
	public DirectEdgeConstraintGroup(String identifier,
			Map<String, Identifier> idMap, HlclFactory hlclFactory,
			InstPairwiseRelation instEdge) {
		super(identifier, mxResources.get("defect-pairrelations1") + " "
				+ instEdge.getSourceRelations().get(0).getIdentifier()
				+ mxResources.get("defect-pairrelations1") + " "
				+ instEdge.getTargetRelations().get(0).getIdentifier()
				+ mxResources.get("defect-pairrelations1") + " ", idMap,
				hlclFactory);
		this.instEdge = instEdge;
		defineTransformations();
	}

	public DirectEdgeType getDirectEdgeType() {
		return directEdgeType;
	}

	public InstPairwiseRelation getInstEdge() {
		return instEdge;
	}

	private void defineTransformations() {

		MetaPairwiseRelation metaEdge = instEdge.getMetaPairwiseRelation();
		boolean sourceActiveAttribute = (boolean) instEdge.getSourceRelations().get(0)
				.getInstAttribute("Active").getValue();
		boolean targetActiveAttribute = (boolean) instEdge.getTargetRelations().get(0)
				.getInstAttribute("Active").getValue();
		boolean activeVertex = false;
		if (sourceActiveAttribute && targetActiveAttribute)
			activeVertex = true;
		if (activeVertex
				&& metaEdge != null
				&& instEdge
						.getInstAttribute(MetaPairwiseRelation.VAR_METAPAIRWISERELTYPE) != null
				&& !(instEdge.getTargetRelations().get(0) instanceof InstOverTwoRelation)
				&& instEdge.getInstAttribute(
						MetaPairwiseRelation.VAR_METAPAIRWISERELTYPE).getValue() != null) {
			directEdgeType = DirectEdgeType
					.valueOf(((String) instEdge.getInstAttribute(
							MetaPairwiseRelation.VAR_METAPAIRWISERELTYPE)
							.getValue()).trim().replace(" ", "_"));
			setDescription(getDescription() + directEdgeType);
			Set<String> sourceAttributeNames = new HashSet<String>();
			switch (directEdgeType) {

			case preferred:
				sourceAttributeNames.add("Satisfied");
				sourceAttributeNames.add("PreferredSelected");
				// ( ( SourceId_Satisfied #/\ targetId_Satisfied ) #/\
				// SourceId_PreferredSelected
				// ) #==> ( (targetId_PreferredSelected #=1) #/\
				// (SourceId_PreferredSelected #= 0) )
				AbstractBooleanTransformation transformation1 = new AndBooleanExpression(
						instEdge.getSourceRelations().get(0),
						instEdge.getTargetRelations().get(0), "Satisfied", "Satisfied");
				AbstractBooleanTransformation transformation2 = new AndBooleanExpression(
						instEdge.getSourceRelations().get(0), "PreferredSelected",
						false, transformation1);

				AbstractComparisonTransformation transformation3 = new EqualsComparisonExpression(
						instEdge.getTargetRelations().get(0), "PreferredSelected",
						getHlclFactory().number(1));
				AbstractComparisonTransformation transformation4 = new EqualsComparisonExpression(
						instEdge.getSourceRelations().get(0), "PreferredSelected",
						getHlclFactory().number(0));

				AbstractBooleanTransformation transformation5 = new AndBooleanExpression(
						transformation3, transformation4);

				getTransformations().add(
						new ImplicationBooleanExpression(transformation2,
								transformation5));

				break;
			case required:
				sourceAttributeNames.add("Selected");
				// (( 1 - SourceId_Selected) + targetId_Selected) #>= 1
				AbstractNumericTransformation transformation6 = new DiffNumericExpression(
						instEdge.getSourceRelations().get(0), "Selected", false,
						getHlclFactory().number(1));
				AbstractNumericTransformation transformation7 = new SumNumericExpression(
						instEdge.getTargetRelations().get(0), "Selected", false,
						transformation6);
				getTransformations().add(
						new GreaterOrEqualsBooleanExpression(
								transformation7,
								new NumberNumericExpression(1)));
				break;
			case conflict:

				sourceAttributeNames.add("Satisfied");
				sourceAttributeNames.add("NoSatisfactionConflict");
				// SourceId_Satisfied #==> targetId_NoSatisfactionConflict #= 0
				AbstractComparisonTransformation transformation8 = new EqualsComparisonExpression(
						instEdge.getTargetRelations().get(0), "NoSatisfactionConflict",
						getHlclFactory().number(0));
				getTransformations().add(
						new ImplicationBooleanExpression(instEdge
								.getSourceRelations().get(0), "Satisfied", true,
								transformation8));

				// targetId_Satisfied #==> SourceId_NoSatisfactionConflict #= 0
				AbstractComparisonTransformation transformation9 = new EqualsComparisonExpression(
						instEdge.getSourceRelations().get(0), "NoSatisfactionConflict",
						getHlclFactory().number(0));
				getTransformations().add(
						new ImplicationBooleanExpression(instEdge
								.getTargetRelations().get(0), "Satisfied", true,
								transformation9));
				break;
			case alternative:
				sourceAttributeNames.add("Satisfied");
				sourceAttributeNames.add("ValidationSelected");
				sourceAttributeNames.add("AlternativeSelected");
				// ( ( ( 1 - SourceId_Satisfied ) #/\ targetId_Satisfied ) #/\
				// SourceId_ValidationSelected ) ) #==> (
				// SourceId_AlternativeSatisfied #= 1 #/\
				// targetId_ValidationSelected #= 1 )
				AbstractBooleanTransformation transformation10 = new NotBooleanExpression(
						instEdge.getSourceRelations().get(0), "Satisfied");
				AbstractBooleanTransformation transformation11 = new AndBooleanExpression(
						instEdge.getTargetRelations().get(0), "Satisfied", false,
						transformation10);
				AbstractBooleanTransformation transformation12 = new AndBooleanExpression(
						instEdge.getSourceRelations().get(0), "ValidationSelected",
						false, transformation11);
				AbstractComparisonTransformation transformation13 = new EqualsComparisonExpression(
						instEdge.getSourceRelations().get(0), "AlternativeSatisfied",
						getHlclFactory().number(1));
				AbstractComparisonTransformation transformation14 = new EqualsComparisonExpression(
						instEdge.getTargetRelations().get(0), "ValidationSelected",
						getHlclFactory().number(1));
				AbstractBooleanTransformation transformation15 = new AndBooleanExpression(
						transformation13, transformation14);
				getTransformations().add(
						new ImplicationBooleanExpression(transformation12,
								transformation15));
				break;
			case means_ends:
				sourceAttributeNames.add("Selected");
			case implication:
				sourceAttributeNames.add("Satisfied");
				// SourceId_Satisfied #==> targetId_ValidationSatisfied #= 1
				AbstractComparisonTransformation transformation16 = new EqualsComparisonExpression(
						instEdge.getTargetRelations().get(0), "ValidationSatisfied",
						getHlclFactory().number(1));
				getTransformations().add(
						new ImplicationBooleanExpression(instEdge
								.getSourceRelations().get(0), "Satisfied", true,
								transformation16));
				// No break to include the following expression
			case implementation:

				sourceAttributeNames.add("ValidationSatisfied");
				// targetId_Selected #==> SourceId_ValidationSelected #= 1
				AbstractComparisonTransformation transformation18 = new EqualsComparisonExpression(
						instEdge.getSourceRelations().get(0), "ValidationSelected",
						getHlclFactory().number(1));
				getTransformations().add(
						new ImplicationBooleanExpression(instEdge
								.getTargetRelations().get(0), "Selected", true,
								transformation18));
				break;
			case mandatory:
				sourceAttributeNames.add("Selected");
				sourceAttributeNames.add("ValidationSelected");
				// SourceId_Selected #==> targetId_ValidationSelected #=1
				AbstractComparisonTransformation transformation19 = new EqualsComparisonExpression(
						instEdge.getTargetRelations().get(0), "ValidationSelected",
						getHlclFactory().number(1));
				getTransformations().add(
						new ImplicationBooleanExpression(instEdge
								.getSourceRelations().get(0), "Selected", true,
								transformation19));
				// targetId_Selected #==> SourceId_ValidationSelected #=1
				AbstractComparisonTransformation transformation20 = new EqualsComparisonExpression(
						instEdge.getSourceRelations().get(0), "ValidationSelected",
						getHlclFactory().number(1));
				getTransformations().add(
						new ImplicationBooleanExpression(instEdge
								.getTargetRelations().get(0), "Selected", true,
								transformation20));
				break;
			case optional:
				sourceAttributeNames.add("Selected");
				// SourceId_Selected #>= targetId_Selected
				getTransformations().add(
						new GreaterOrEqualsBooleanExpression(instEdge
								.getSourceRelations().get(0), instEdge
								.getTargetRelations().get(0), "Selected", "Selected"));

				// targetId_Optional #= 1
				getTransformations().add(
						new EqualsComparisonExpression(instEdge
								.getTargetRelations().get(0), "Optional",
								getHlclFactory().number(1)));
				setOptional(true);
				break;
			case claim:

				sourceAttributeNames.add("Selected");
				// SourceId_ClaimSelected #<=> SourceId_Selected #/\
				// SourceId_CompExp #==>
				// targetId_level #= R_level
				// TODO review
				getTransformations().add(
						new EqualsComparisonExpression(instEdge
								.getSourceRelations().get(0), "CompExp",
								getHlclFactory().number(1)));
				AbstractBooleanTransformation transformation21 = new AndBooleanExpression(
						instEdge.getSourceRelations().get(0),
						instEdge.getSourceRelations().get(0), "Selected", "CompExp");
				EqualsComparisonExpression transformation22 = new EqualsComparisonExpression(
						instEdge.getTargetRelations().get(0), instEdge, "level",
						"level");

				AbstractBooleanTransformation transformation23 = new ImplicationBooleanExpression(
						transformation21, transformation22);
				getTransformations().add(
						new DoubleImplicationBooleanExpression(instEdge
								.getSourceRelations().get(0), "ClaimSelected", true,
								transformation23));
				break;
			case softdependency:
				// SourceId_SD #<=> SourceId_CompExp #==>
				// targetId_level #=
				// R_level
				// TODO review
				getTransformations().add(
						new EqualsComparisonExpression(instEdge
								.getSourceRelations().get(0), "CompExp",
								getHlclFactory().number(1)));
				EqualsComparisonExpression transformation24 = new EqualsComparisonExpression(
						instEdge.getTargetRelations().get(0), instEdge, "level",
						"level");

				AbstractBooleanTransformation transformation25 = new ImplicationBooleanExpression(
						instEdge.getSourceRelations().get(0), "CompExp", true,
						transformation24);
				getTransformations().add(
						new DoubleImplicationBooleanExpression(instEdge
								.getSourceRelations().get(0), "SDSelected", true,
								transformation25));

				break;
			case generalConstraint:
				String attributeValue = (String) instEdge.getInstAttribute(
						"generalConstraint").getValue();
				if (attributeValue != null && !attributeValue.equals(""))
					getTransformations().add(
							new LiteralBooleanExpression(attributeValue));

				break;
			case none:
				break;
			}
			InstElement instVertex = instEdge.getSourceRelations().get(0);
			if (instVertex instanceof InstOverTwoRelation) {
				((InstOverTwoRelation) instVertex).clearSourceAttributeNames();
				((InstOverTwoRelation) instVertex)
						.addSourceAttributeNames(sourceAttributeNames);
			}

		}

	}
}
