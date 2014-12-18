package com.variamos.refas.core.simulationmodel;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.mxgraph.util.mxResources;
import com.variamos.refas.core.transformations.AndBooleanTransformation;
import com.variamos.refas.core.transformations.AssignBooleanTransformation;
import com.variamos.refas.core.transformations.DiffNumericTransformation;
import com.variamos.refas.core.transformations.EqualsComparisonTransformation;
import com.variamos.refas.core.transformations.GreaterOrEqualsBooleanTransformation;
import com.variamos.refas.core.transformations.ImplicationBooleanTransformation;
import com.variamos.refas.core.transformations.LiteralBooleanTransformation;
import com.variamos.refas.core.transformations.NotBooleanTransformation;
import com.variamos.refas.core.transformations.NumberNumericTransformation;
import com.variamos.refas.core.transformations.SumNumericTransformation;
import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.syntaxsupport.metametamodel.MetaDirectRelation;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstVertex;

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
				+ instEdge.getFromRelation().getIdentifier()
				+ mxResources.get("defect-pairrelations1") + " "
				+ instEdge.getToRelation().getIdentifier()
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
		if (metaEdge != null
				&& instEdge
						.getInstAttribute(MetaDirectRelation.VAR_METADIRECTEDGETYPE) != null
				&& !(instEdge.getToRelation() instanceof InstGroupDependency) && instEdge
						.getInstAttribute(
								MetaDirectRelation.VAR_METADIRECTEDGETYPE)
						.getValue() != null) {
			String value = (String) instEdge.getInstAttribute(
					MetaDirectRelation.VAR_METADIRECTEDGETYPE)
					.getValue();
			directEdgeType = DirectEdgeType
					.valueOf(((String) instEdge.getInstAttribute(
							MetaDirectRelation.VAR_METADIRECTEDGETYPE)
							.getValue()).trim().replace(" ", "_"));
			setDescription(getDescription() + directEdgeType);
			Set<String> sourceAttributeNames = new HashSet<String>();
			switch (directEdgeType) {

			case preferred:
				sourceAttributeNames.add("Satisfied"); // TODO fix, only
														// shows the second
														// attribute
				sourceAttributeNames.add("PreferredSelected");
				// ( ( A_Satisfied #/\ B_Satisfied ) #/\ A_PreferredSelected
				// ) #==> ( (B_PreferredSelected #=1) #/\
				// (A_PreferredSelected #= 0) )
				AbstractBooleanTransformation transformation1 = new AndBooleanTransformation(
						instEdge.getFromRelation(), instEdge.getToRelation(),
						"Satisfied", "Satisfied");
				AbstractBooleanTransformation transformation2 = new AndBooleanTransformation(
						instEdge.getFromRelation(), "PreferredSelected", false,
						transformation1);

				AbstractComparisonTransformation transformation3 = new EqualsComparisonTransformation(
						instEdge.getToRelation(), "PreferredSelected",
						getHlclFactory().number(1));
				AbstractComparisonTransformation transformation4 = new EqualsComparisonTransformation(
						instEdge.getFromRelation(), "PreferredSelected",
						getHlclFactory().number(0));

				AbstractBooleanTransformation transformation5 = new AndBooleanTransformation(
						transformation3, transformation4);

				getTransformations().add(
						new ImplicationBooleanTransformation(transformation2,
								transformation5));

				break;
			case required:
				sourceAttributeNames.add("Selected");
				// (( 1 - A_Selected) + B_Selected) #>= 1
				AbstractNumericTransformation transformation6 = new DiffNumericTransformation(
						instEdge.getFromRelation(), "Selected", false,
						getHlclFactory().number(1));
				AbstractNumericTransformation transformation7 = new SumNumericTransformation(
						instEdge.getToRelation(), "Selected", false,
						transformation6);
				getTransformations().add(
						new GreaterOrEqualsBooleanTransformation(
								transformation7,
								new NumberNumericTransformation(1)));
				break;
			case conflict:
				sourceAttributeNames.add("Selected");

				sourceAttributeNames.add("ValidationSelected");
				// A_Selected #==> B_ValidationSelected #= 0
				AbstractComparisonTransformation transformation8 = new EqualsComparisonTransformation(
						instEdge.getToRelation(), "ValidationSelected",
						getHlclFactory().number(0));
				getTransformations().add(
						new ImplicationBooleanTransformation(instEdge
								.getFromRelation(), "Selected", true,
								transformation8));

				// B_Selected #==> A_ValidationSelected #= 0
				AbstractComparisonTransformation transformation9 = new EqualsComparisonTransformation(
						instEdge.getFromRelation(), "ValidationSelected",
						getHlclFactory().number(0));
				getTransformations().add(
						new ImplicationBooleanTransformation(instEdge
								.getToRelation(), "Selected", true,
								transformation9));
				// TODO to validation expressions missing
				break;
			case alternative:
				sourceAttributeNames.add("Satisfied");
				sourceAttributeNames.add("Selected");
				sourceAttributeNames.add("AlternativeSelected");
				// ( ( ( 1 - A_Satisfied ) #/\ B_Satisfied ) #/\ A_Selected
				// ) ) #==> ( A_AlternativeSatisfied #= 1 #/\
				// B_ValidationSelected #= 1 )
				AbstractBooleanTransformation transformation10 = new NotBooleanTransformation(
						instEdge.getFromRelation(), "Satisfied");
				AbstractBooleanTransformation transformation11 = new AndBooleanTransformation(
						instEdge.getToRelation(), "Satisfied", false,
						transformation10);
				AbstractBooleanTransformation transformation12 = new AndBooleanTransformation(
						instEdge.getFromRelation(), "Selected", false,
						transformation11);
				AbstractComparisonTransformation transformation13 = new EqualsComparisonTransformation(
						instEdge.getFromRelation(), "AlternativeSatisfied",
						getHlclFactory().number(1));
				AbstractComparisonTransformation transformation14 = new EqualsComparisonTransformation(
						instEdge.getToRelation(), "ValidationSelected",
						getHlclFactory().number(1));
				AbstractBooleanTransformation transformation15 = new AndBooleanTransformation(
						transformation13, transformation14);
				getTransformations().add(
						new ImplicationBooleanTransformation(transformation12,
								transformation15));
				break;
			case means_ends:
			case implication:
				sourceAttributeNames.add("Satisfied");
				// A_Satisfied #==> B_ValidationSatisfied #= 1
				AbstractComparisonTransformation transformation16 = new EqualsComparisonTransformation(
						instEdge.getToRelation(), "ValidationSatisfied",
						getHlclFactory().number(1));
				getTransformations().add(
						new ImplicationBooleanTransformation(instEdge
								.getFromRelation(), "Satisfied", true,
								transformation16));
				// No break to include the following transformation
			case implementation:

				sourceAttributeNames.add("ValidationSatisfied");
				// B_Selected #==> A_ValidationSelected #= 1
				AbstractComparisonTransformation transformation18 = new EqualsComparisonTransformation(
						instEdge.getFromRelation(), "ValidationSelected",
						getHlclFactory().number(1));
				getTransformations().add(
						new ImplicationBooleanTransformation(instEdge
								.getToRelation(), "Selected", true,
								transformation18));
				break;
			case mandatory:
				sourceAttributeNames.add("Selected");
				sourceAttributeNames.add("ValidationSelected");
				// A_Selected #==> B_ValidationSelected #=1
				AbstractComparisonTransformation transformation19 = new EqualsComparisonTransformation(
						instEdge.getToRelation(), "ValidationSelected",
						getHlclFactory().number(1));
				getTransformations().add(
						new ImplicationBooleanTransformation(instEdge
								.getFromRelation(), "Selected", true,
								transformation19));
				// B_Selected #==> A_ValidationSelected #=1
				AbstractComparisonTransformation transformation20 = new EqualsComparisonTransformation(
						instEdge.getFromRelation(), "ValidationSelected",
						getHlclFactory().number(1));
				getTransformations().add(
						new ImplicationBooleanTransformation(instEdge
								.getToRelation(), "Selected", true,
								transformation20));
				break;
			case optional:
				sourceAttributeNames.add("Selected");
				// A_Selected #>= B_Selected
				getTransformations().add(
						new GreaterOrEqualsBooleanTransformation(instEdge
								.getFromRelation(), instEdge.getToRelation(),
								"Selected", "Selected"));

				// B_Optional #= 1
				getTransformations().add(
						new AssignBooleanTransformation(instEdge
								.getToRelation(), "Optional", getHlclFactory()
								.number(1)));
				setOptional(true);
				break;
			case claim:
				// A_Claim #<=> A_Opers #/\ A_CompExp #==> B_SatisfiedLevel
				// #= R_level
				// TODO implement

				break;
			case softdependency:
				// A_SD #<=> A_CompExp #==> B_ValidationRequiredLevel #=
				// R_level

				// TODO implement

				break;
			case generalConstraint:
				String attributeValue = (String) instEdge.getInstAttribute(
						"generalConstraint").getValue();
				if (attributeValue != null && !attributeValue.equals(""))
					getTransformations().add(
							new LiteralBooleanTransformation(attributeValue));

				break;
			case group:
				break;
			case none:
				break;
			}
			InstVertex instVertex = instEdge.getFromRelation();
			if (instVertex instanceof InstGroupDependency) {
				((InstGroupDependency) instVertex).clearSourceAttributeNames();
				((InstGroupDependency) instVertex)
						.addSourceAttributeNames(sourceAttributeNames);
			}

		}

	}
}
