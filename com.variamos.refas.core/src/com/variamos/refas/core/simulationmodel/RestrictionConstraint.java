package com.variamos.refas.core.simulationmodel;

import java.util.Collection;
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
import com.variamos.refas.core.transformations.NumberNumericTransformation;
import com.variamos.refas.core.transformations.OrBooleanTransformation;
import com.variamos.refas.core.transformations.ProdNumericTransformation;
import com.variamos.refas.core.transformations.SumNumericTransformation;
import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;
import com.variamos.syntaxsupport.metametamodel.MetaDirectRelation;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstVertex;

//TODO refactor: SingleElementExpressionSet
/**
 * A class to represent the constraints for restrictions of a concept. Part of
 * PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-16
 */
public class RestrictionConstraint extends AbstractConstraintGroup {

	static {
		try {
			mxResources.add("com/variamos/gui/maineditor/resources/editor");
		} catch (Exception e) {
			// ignore
		}
	}
	/**
	 * The source vertex for the constraint
	 */
	private InstVertex instVertex;

	/**
	 * Create the Constraint with all required parameters
	 * 
	 * @param identifier
	 * @param description
	 * @param directEdgeType
	 * @param source
	 * @param target
	 */
	public RestrictionConstraint(String identifier,
			Map<String, Identifier> idMap, HlclFactory hlclFactory,
			InstVertex instVertex) {
		super(identifier,
				mxResources.get("defect-concepts") + " " + identifier, idMap,
				hlclFactory);
		this.instVertex = instVertex;
		defineTransformations();
	}

	public InstVertex getInstEdge() {
		return instVertex;
	}

	private void defineTransformations() {

		if (instVertex instanceof InstConcept
				|| instVertex instanceof InstGroupDependency) {

			InstAttribute validAttribute = instVertex.getInstAttribute("Active");
			if (validAttribute == null || ((boolean) validAttribute.getValue()) == true) {
				for (InstAttribute instAttribute : instVertex
						.getInstAttributesCollection()) {

					int attributeValue = 0;
					String type = (String) instAttribute
							.getModelingAttributeType();
					if (type.equals("Integer") || type.equals("Boolean")) {
						if (instAttribute.getValue() instanceof Boolean)
							attributeValue = ((boolean) instAttribute
									.getValue()) ? 1 : 0;
						else if (instAttribute.getValue() instanceof String)
							attributeValue = Integer
									.valueOf((String) instAttribute.getValue());
						else
							attributeValue = (Integer) instAttribute.getValue();
					}
					// identifierId_SimAllowed #= identifierId_Allowed
					if (instAttribute.getIdentifier().equals("Allowed")) {

						getTransformations()
								.add(new EqualsComparisonTransformation(
										instVertex, instAttribute
												.getIdentifier(),
										getHlclFactory().number(attributeValue)));

						getTransformations().add(
								new EqualsComparisonTransformation(instVertex,
										instVertex, "SimAllowed", instAttribute
												.getIdentifier()));
					}
					// identifierId_SimRequired #= identifierId_Required
					if (instAttribute.getIdentifier().equals("Required")) {
						getTransformations()
								.add(new EqualsComparisonTransformation(
										instVertex, instAttribute
												.getIdentifier(),
										getHlclFactory().number(attributeValue)));

						getTransformations().add(
								new EqualsComparisonTransformation(instVertex,
										instVertex, "SimRequired",
										instAttribute.getIdentifier()));
					}
					// identifierId_PreferredSelected #= 1
					if (instAttribute.getIdentifier().equals(
							"PreferredSelected")) {

						getTransformations()
								.add(new EqualsComparisonTransformation(
										instVertex, instAttribute
												.getIdentifier(),
										getHlclFactory().number(attributeValue)));
					}
					// identifierId_Optional #= 0
					if (instAttribute.getIdentifier().equals("Optional")) {
						getTransformations()
								.add(new EqualsComparisonTransformation(
										instVertex, instAttribute
												.getIdentifier(),
										getHlclFactory().number(attributeValue)));
					}
					// identifierId_SimInitialRequiredLevel #=
					// identifierId_RequiredLevel
					if (instAttribute.getIdentifier().equals("RequiredLevel")) {
						getTransformations()
								.add(new EqualsComparisonTransformation(
										instVertex, instAttribute
												.getIdentifier(),
										getHlclFactory().number(attributeValue)));

						getTransformations().add(
								new EqualsComparisonTransformation(instVertex,
										instVertex, "InitialRequiredLevel",
										instAttribute.getIdentifier()));
					}
					if (instAttribute.getIdentifier().equals("Satisfied")) {
						// ( ( 1 - identifierId_SimRequired ) +
						// identifierId_Satisfied ) #>= 1
						AbstractNumericTransformation transformation1 = new DiffNumericTransformation(
								instVertex, "SimRequired", false,
								getHlclFactory().number(1));
						transformation1 = new SumNumericTransformation(
								instVertex, instAttribute.getIdentifier(),
								false, transformation1);

						getTransformations().add(
								new GreaterOrEqualsBooleanTransformation(
										transformation1,
										new NumberNumericTransformation(1)));

						// ( ( 1 - identifierId_Selected ) +
						// identifierId_Satisfied
						// ) #>= 1
						AbstractNumericTransformation transformation2 = new DiffNumericTransformation(
								instVertex, "Selected", false, getHlclFactory()
										.number(1));
						transformation2 = new SumNumericTransformation(
								instVertex, instAttribute.getIdentifier(),
								false, transformation2);

						getTransformations().add(
								new GreaterOrEqualsBooleanTransformation(
										transformation2,
										new NumberNumericTransformation(1)));

						// ( 1 - identifierId_SimAllowed ) * (
						// identifierId_SimRequired + identifierId_Satisfied )
						// #=
						// 0
						AbstractNumericTransformation transformation3 = new DiffNumericTransformation(
								instVertex, "SimAllowed", false,
								getHlclFactory().number(1));
						AbstractNumericTransformation transformation4 = new SumNumericTransformation(
								instVertex, instVertex, "SimRequired",
								instAttribute.getIdentifier());
						transformation3 = new ProdNumericTransformation(
								transformation3, transformation4);

						getTransformations().add(
								new EqualsComparisonTransformation(
										transformation3,
										new NumberNumericTransformation(0)));
					}

					if (instAttribute.getIdentifier().equals("ForcedSatisfied")) {
						// Identifier_Satisfied #<=>
						// ( ( Identifier_ForcedSatisfied #\/
						// Identifier_AlternativeSatisfied ) #\/
						// ( Identifier_ValidationSatisfied #/\
						// identifierId_SimAllowed )
						// #/\ identifierId_NoSatisfactionConflict )
						getTransformations().add(
								new EqualsComparisonTransformation(instVertex,
										"NoSatisfactionConflict",
										getHlclFactory().number(1)));
						getTransformations()
								.add(new EqualsComparisonTransformation(
										instVertex, instAttribute
												.getIdentifier(),
										getHlclFactory().number(attributeValue)));
						AbstractBooleanTransformation transformation6 = new OrBooleanTransformation(
								instVertex, instVertex, "ForcedSatisfied",
								"AlternativeSatisfied");
						AbstractBooleanTransformation transformation7 = new AndBooleanTransformation(
								instVertex, instVertex, "ValidationSatisfied",
								"SimAllowed");
						AbstractBooleanTransformation transformation8 = new OrBooleanTransformation(
								transformation6, transformation7);
						AbstractBooleanTransformation transformation9 = new AndBooleanTransformation(
								instVertex, "NoSatisfactionConflict", false,
								transformation8);
						getTransformations().add(
								new DoubleImplicationBooleanTransformation(
										instVertex, "Satisfied", true,
										transformation9));

					}
					// Set ForceSelected from GUI properties
					if (instAttribute.getIdentifier().equals("ForcedSelected")) {

						getTransformations()
								.add(new EqualsComparisonTransformation(
										instVertex, instAttribute
												.getIdentifier(),
										getHlclFactory().number(attributeValue)));
					}
					if (instAttribute.getIdentifier().equals("Selected")) {
						// identifierId_Selected #<=>
						// ( ( ( identifierId_SolverSelected #/\
						// identifierId_PreferredSelected )
						// #\/ ( identifierId_ValidationSelected #\/
						// identifierId_SimRequired ) ) #\/
						// identifierId_ForceSelected )

						AbstractBooleanTransformation transformation10 = new AndBooleanTransformation(
								instVertex, instVertex, "SolverSelected",
								"PreferredSelected");
						AbstractBooleanTransformation transformation11 = new OrBooleanTransformation(
								instVertex, instVertex, "ValidationSelected",
								"SimRequired");
						AbstractBooleanTransformation transformation12 = new OrBooleanTransformation(
								transformation10, transformation11);
						AbstractBooleanTransformation transformation13 = new OrBooleanTransformation(
								instVertex, "ForcedSelected", false,
								transformation12);
						getTransformations().add(
								new DoubleImplicationBooleanTransformation(
										instVertex, instAttribute
												.getIdentifier(), true,
										transformation13));
					}
				}

			}

		}

	}
}
