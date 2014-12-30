package com.variamos.refas.core.simulationmodel;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.mxgraph.util.mxResources;
import com.variamos.refas.core.transformations.AndBooleanTransformation;
import com.variamos.refas.core.transformations.DiffNumericTransformation;
import com.variamos.refas.core.transformations.DoubleImplicationBooleanTransformation;
import com.variamos.refas.core.transformations.EqualsComparisonTransformation;
import com.variamos.refas.core.transformations.GreaterOrEqualsBooleanTransformation;
import com.variamos.refas.core.transformations.ImplicationBooleanTransformation;
import com.variamos.refas.core.transformations.LiteralBooleanTransformation;
import com.variamos.refas.core.transformations.NotBooleanTransformation;
import com.variamos.refas.core.transformations.NumberNumericTransformation;
import com.variamos.refas.core.transformations.SumNumericTransformation;
import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.syntaxsupport.metametamodel.MetaPairwiseRelation;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstVertex;

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
	private InstEdge instEdge;

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
			InstEdge instEdge) {
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

	public InstEdge getInstEdge() {
		return instEdge;
	}

	private void defineTransformations() {

		MetaEdge metaEdge = instEdge.getMetaEdge();
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
						.getInstAttribute(MetaPairwiseRelation.VAR_METADIRECTEDGETYPE) != null
				&& !(instEdge.getTargetRelations().get(0) instanceof InstGroupDependency)
				&& instEdge.getInstAttribute(
						MetaPairwiseRelation.VAR_METADIRECTEDGETYPE).getValue() != null) {
			directEdgeType = DirectEdgeType
					.valueOf(((String) instEdge.getInstAttribute(
							MetaPairwiseRelation.VAR_METADIRECTEDGETYPE)
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
				AbstractBooleanTransformation transformation1 = new AndBooleanTransformation(
						instEdge.getSourceRelations().get(0),
						instEdge.getTargetRelations().get(0), "Satisfied", "Satisfied");
				AbstractBooleanTransformation transformation2 = new AndBooleanTransformation(
						instEdge.getSourceRelations().get(0), "PreferredSelected",
						false, transformation1);

				AbstractComparisonTransformation transformation3 = new EqualsComparisonTransformation(
						instEdge.getTargetRelations().get(0), "PreferredSelected",
						getHlclFactory().number(1));
				AbstractComparisonTransformation transformation4 = new EqualsComparisonTransformation(
						instEdge.getSourceRelations().get(0), "PreferredSelected",
						getHlclFactory().number(0));

				AbstractBooleanTransformation transformation5 = new AndBooleanTransformation(
						transformation3, transformation4);

				getTransformations().add(
						new ImplicationBooleanTransformation(transformation2,
								transformation5));

				break;
			case required:
				sourceAttributeNames.add("Selected");
				// (( 1 - SourceId_Selected) + targetId_Selected) #>= 1
				AbstractNumericTransformation transformation6 = new DiffNumericTransformation(
						instEdge.getSourceRelations().get(0), "Selected", false,
						getHlclFactory().number(1));
				AbstractNumericTransformation transformation7 = new SumNumericTransformation(
						instEdge.getTargetRelations().get(0), "Selected", false,
						transformation6);
				getTransformations().add(
						new GreaterOrEqualsBooleanTransformation(
								transformation7,
								new NumberNumericTransformation(1)));
				break;
			case conflict:

				sourceAttributeNames.add("Satisfied");
				sourceAttributeNames.add("NoSatisfactionConflict");
				// SourceId_Satisfied #==> targetId_NoSatisfactionConflict #= 0
				AbstractComparisonTransformation transformation8 = new EqualsComparisonTransformation(
						instEdge.getTargetRelations().get(0), "NoSatisfactionConflict",
						getHlclFactory().number(0));
				getTransformations().add(
						new ImplicationBooleanTransformation(instEdge
								.getSourceRelations().get(0), "Satisfied", true,
								transformation8));

				// targetId_Satisfied #==> SourceId_NoSatisfactionConflict #= 0
				AbstractComparisonTransformation transformation9 = new EqualsComparisonTransformation(
						instEdge.getSourceRelations().get(0), "NoSatisfactionConflict",
						getHlclFactory().number(0));
				getTransformations().add(
						new ImplicationBooleanTransformation(instEdge
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
				AbstractBooleanTransformation transformation10 = new NotBooleanTransformation(
						instEdge.getSourceRelations().get(0), "Satisfied");
				AbstractBooleanTransformation transformation11 = new AndBooleanTransformation(
						instEdge.getTargetRelations().get(0), "Satisfied", false,
						transformation10);
				AbstractBooleanTransformation transformation12 = new AndBooleanTransformation(
						instEdge.getSourceRelations().get(0), "ValidationSelected",
						false, transformation11);
				AbstractComparisonTransformation transformation13 = new EqualsComparisonTransformation(
						instEdge.getSourceRelations().get(0), "AlternativeSatisfied",
						getHlclFactory().number(1));
				AbstractComparisonTransformation transformation14 = new EqualsComparisonTransformation(
						instEdge.getTargetRelations().get(0), "ValidationSelected",
						getHlclFactory().number(1));
				AbstractBooleanTransformation transformation15 = new AndBooleanTransformation(
						transformation13, transformation14);
				getTransformations().add(
						new ImplicationBooleanTransformation(transformation12,
								transformation15));
				break;
			case means_ends:
				sourceAttributeNames.add("Selected");
			case implication:
				sourceAttributeNames.add("Satisfied");
				// SourceId_Satisfied #==> targetId_ValidationSatisfied #= 1
				AbstractComparisonTransformation transformation16 = new EqualsComparisonTransformation(
						instEdge.getTargetRelations().get(0), "ValidationSatisfied",
						getHlclFactory().number(1));
				getTransformations().add(
						new ImplicationBooleanTransformation(instEdge
								.getSourceRelations().get(0), "Satisfied", true,
								transformation16));
				// No break to include the following expression
			case implementation:

				sourceAttributeNames.add("ValidationSatisfied");
				// targetId_Selected #==> SourceId_ValidationSelected #= 1
				AbstractComparisonTransformation transformation18 = new EqualsComparisonTransformation(
						instEdge.getSourceRelations().get(0), "ValidationSelected",
						getHlclFactory().number(1));
				getTransformations().add(
						new ImplicationBooleanTransformation(instEdge
								.getTargetRelations().get(0), "Selected", true,
								transformation18));
				break;
			case mandatory:
				sourceAttributeNames.add("Selected");
				sourceAttributeNames.add("ValidationSelected");
				// SourceId_Selected #==> targetId_ValidationSelected #=1
				AbstractComparisonTransformation transformation19 = new EqualsComparisonTransformation(
						instEdge.getTargetRelations().get(0), "ValidationSelected",
						getHlclFactory().number(1));
				getTransformations().add(
						new ImplicationBooleanTransformation(instEdge
								.getSourceRelations().get(0), "Selected", true,
								transformation19));
				// targetId_Selected #==> SourceId_ValidationSelected #=1
				AbstractComparisonTransformation transformation20 = new EqualsComparisonTransformation(
						instEdge.getSourceRelations().get(0), "ValidationSelected",
						getHlclFactory().number(1));
				getTransformations().add(
						new ImplicationBooleanTransformation(instEdge
								.getTargetRelations().get(0), "Selected", true,
								transformation20));
				break;
			case optional:
				sourceAttributeNames.add("Selected");
				// SourceId_Selected #>= targetId_Selected
				getTransformations().add(
						new GreaterOrEqualsBooleanTransformation(instEdge
								.getSourceRelations().get(0), instEdge
								.getTargetRelations().get(0), "Selected", "Selected"));

				// targetId_Optional #= 1
				getTransformations().add(
						new EqualsComparisonTransformation(instEdge
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
						new EqualsComparisonTransformation(instEdge
								.getSourceRelations().get(0), "CompExp",
								getHlclFactory().number(1)));
				AbstractBooleanTransformation transformation21 = new AndBooleanTransformation(
						instEdge.getSourceRelations().get(0),
						instEdge.getSourceRelations().get(0), "Selected", "CompExp");
				EqualsComparisonTransformation transformation22 = new EqualsComparisonTransformation(
						instEdge.getTargetRelations().get(0), instEdge, "level",
						"level");

				AbstractBooleanTransformation transformation23 = new ImplicationBooleanTransformation(
						transformation21, transformation22);
				getTransformations().add(
						new DoubleImplicationBooleanTransformation(instEdge
								.getSourceRelations().get(0), "ClaimSelected", true,
								transformation23));
				break;
			case softdependency:
				// SourceId_SD #<=> SourceId_CompExp #==>
				// targetId_level #=
				// R_level
				// TODO review
				getTransformations().add(
						new EqualsComparisonTransformation(instEdge
								.getSourceRelations().get(0), "CompExp",
								getHlclFactory().number(1)));
				EqualsComparisonTransformation transformation24 = new EqualsComparisonTransformation(
						instEdge.getTargetRelations().get(0), instEdge, "level",
						"level");

				AbstractBooleanTransformation transformation25 = new ImplicationBooleanTransformation(
						instEdge.getSourceRelations().get(0), "CompExp", true,
						transformation24);
				getTransformations().add(
						new DoubleImplicationBooleanTransformation(instEdge
								.getSourceRelations().get(0), "SDSelected", true,
								transformation25));

				break;
			case generalConstraint:
				String attributeValue = (String) instEdge.getInstAttribute(
						"generalConstraint").getValue();
				if (attributeValue != null && !attributeValue.equals(""))
					getTransformations().add(
							new LiteralBooleanTransformation(attributeValue));

				break;
			case none:
				break;
			}
			InstElement instVertex = instEdge.getSourceRelations().get(0);
			if (instVertex instanceof InstGroupDependency) {
				((InstGroupDependency) instVertex).clearSourceAttributeNames();
				((InstGroupDependency) instVertex)
						.addSourceAttributeNames(sourceAttributeNames);
			}

		}

	}
}
