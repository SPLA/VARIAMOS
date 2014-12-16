package com.variamos.refas.core.simulationmodel;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.variamos.refas.core.transformations.AndBooleanTransformation;
import com.variamos.refas.core.transformations.AssignBooleanTransformation;
import com.variamos.refas.core.transformations.DiffNumericTransformation;
import com.variamos.refas.core.transformations.EqualsComparisonTransformation;
import com.variamos.refas.core.transformations.GreaterOrEqualsBooleanTransformation;
import com.variamos.refas.core.transformations.ImplicationBooleanTransformation;
import com.variamos.refas.core.transformations.NotBooleanTransformation;
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
	public RestrictionConstraint(String identifier, String description,
			Map<String, Identifier> idMap, HlclFactory hlclFactory,
			InstVertex instVertex) {
		super(identifier, description, idMap, hlclFactory);
		this.instVertex = instVertex;
		defineTransformations();
	}

	public InstVertex getInstEdge() {
		return instVertex;
	}

	private void defineTransformations() {

		if (instVertex instanceof InstConcept) { // TODO not required if the
													// method
			// returns InstConcepts
			InstConcept instConcept = (InstConcept) instVertex;

			MetaConcept metaConcept = instConcept.getMetaConcept();
			if (metaConcept != null) {

				for (InstAttribute instAttribute : instConcept
						.getInstAttributesCollection()) {
					// A_SimAllowed #= A_Allowed
					if (instAttribute.getIdentifier().equals("Allowed")) {
						int attributeVale = ((boolean) instAttribute.getValue()) ? 1
								: 0;
						getTransformations()
								.add(new AssignBooleanTransformation(
										instVertex, instAttribute
												.getIdentifier(),
										getHlclFactory().number(attributeVale)));

						getTransformations().add(
								new AssignBooleanTransformation(instVertex,
										instVertex, "SimAllowed", instAttribute
												.getIdentifier()));
					}
					// A_SimRequired #= A_Required
					if (instAttribute.getIdentifier().equals("Required")) {
						int attributeVale = (Boolean) ((boolean) instAttribute
								.getValue()) ? 1 : 0;
						getTransformations()
								.add(new AssignBooleanTransformation(
										instVertex, instAttribute
												.getIdentifier(),
										getHlclFactory().number(attributeVale)));
						getTransformations().add(
								new AssignBooleanTransformation(instVertex,
										instVertex, "SimRequired",
										instAttribute.getIdentifier()));
					}
					// A_PreferredSelected #= 1
					if (instAttribute.getIdentifier().equals(
							"PreferredSelected")) {
						int attributeVale = ((boolean) instAttribute.getValue()) ? 1
								: 0;
						getTransformations()
								.add(new AssignBooleanTransformation(
										instVertex, instAttribute
												.getIdentifier(),
										getHlclFactory().number(attributeVale)));
					}
					// A_Optional #= 0
					if (instAttribute.getIdentifier().equals("Optional")) {
						int attributeVale = ((boolean) instAttribute.getValue()) ? 1
								: 0;
						getTransformations()
								.add(new AssignBooleanTransformation(
										instVertex, instAttribute
												.getIdentifier(),
										getHlclFactory().number(attributeVale)));
					}
					// A_SimInitialRequiredLevel #= A_RequiredLevel
					if (instAttribute.getIdentifier().equals("RequiredLevel")) {
						int attributeVale = ((Integer) instAttribute.getValue())
								.intValue();
						getTransformations()
								.add(new AssignBooleanTransformation(
										instVertex, instAttribute
												.getIdentifier(),
										getHlclFactory().number(attributeVale)));
						getTransformations().add(
								new AssignBooleanTransformation(instVertex,
										instVertex, "InitialRequiredLevel",
										instAttribute.getIdentifier()));
					}
					if (instAttribute.getIdentifier().equals("Satisfied")) {
						// (( 1 - A_SimRequired) + A_Satisfied) #>= 1
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

						// (( 1 - A_Selected) + A_Satisfied) #>= 1
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

						// (1 - A_SimAllowed) * (A_SimRequired +
						// A_Satisfied) #= 0
						AbstractNumericTransformation transformation3 = new DiffNumericTransformation(
								instVertex, "Selected", false, getHlclFactory()
										.number(1));
						AbstractNumericTransformation transformation4 = new SumNumericTransformation(
								instVertex, instVertex, "SimRequired",
								instAttribute.getIdentifier());
						transformation3 = new ProdNumericTransformation(
								transformation3, transformation4);

						getTransformations().add(
								new EqualsComparisonTransformation(
										transformation3,
										new NumberNumericTransformation(0)));

						// A_Satisfied #<=> ( ( A_ForcedSatisfied #\/
						// A_AlternativeSatisfied ) #\/ (
						// A_ValidationSatisfied) #/\ A_SimAllowed ) )

						AbstractBooleanTransformation transformation5 = new OrBooleanTransformation(
								instVertex, instVertex, "ForcedSatisfied",
								"AlternativeSatisfied");
						AbstractBooleanTransformation transformation6 = new AndBooleanTransformation(
								instVertex, instVertex, "ValidationSatisfied",
								"SimAllowed");
						AbstractBooleanTransformation transformation7 = new OrBooleanTransformation(
								transformation5, transformation6);
						getTransformations().add(
								new EqualsComparisonTransformation(instVertex,
										instAttribute.getIdentifier(), true,
										transformation7));

					}
					if (instAttribute.getIdentifier().equals("Selected")) {
						// A_Selected #<=> ( ( ( A_SolverSelected #/\
						// A_PreferredSelected) #\/ A_ValidationSelected )
						// #\/ A_SimRequired )

						AbstractBooleanTransformation transformation8 = new AndBooleanTransformation(
								instVertex, instVertex, "SolverSelected",
								"PreferredSelected");
						AbstractBooleanTransformation transformation9 = new OrBooleanTransformation(
								instVertex, instVertex, "ValidationSelected",
								"SimRequired");
						AbstractBooleanTransformation transformation10 = new OrBooleanTransformation(
								transformation8, transformation9);
						getTransformations().add(
								new EqualsComparisonTransformation(instVertex,
										instAttribute.getIdentifier(), true,
										transformation10));

					}

				}

			}

		}

	}
}
