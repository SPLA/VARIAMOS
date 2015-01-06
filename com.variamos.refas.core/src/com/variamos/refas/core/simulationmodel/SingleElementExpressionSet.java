package com.variamos.refas.core.simulationmodel;

import java.util.Map;

import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.mxgraph.util.mxResources;
import com.variamos.refas.core.expressions.AndBooleanExpression;
import com.variamos.refas.core.expressions.DiffNumericExpression;
import com.variamos.refas.core.expressions.DoubleImplicationBooleanExpression;
import com.variamos.refas.core.expressions.EqualsComparisonExpression;
import com.variamos.refas.core.expressions.GreaterOrEqualsBooleanExpression;
import com.variamos.refas.core.expressions.ImplicationBooleanExpression;
import com.variamos.refas.core.expressions.NotBooleanExpression;
import com.variamos.refas.core.expressions.NumberNumericExpression;
import com.variamos.refas.core.expressions.OrBooleanExpression;
import com.variamos.refas.core.expressions.ProdNumericExpression;
import com.variamos.refas.core.expressions.SumNumericExpression;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
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
public class SingleElementExpressionSet extends MetaExpressionSet {

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
	public SingleElementExpressionSet(String identifier,
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
				|| instVertex instanceof InstOverTwoRelation) {

			InstAttribute validAttribute = instVertex
					.getInstAttribute("Active");
			if (validAttribute == null
					|| ((boolean) validAttribute.getValue()) == true) {
				for (InstAttribute instAttribute : instVertex
						.getInstAttributesCollection()) {

					int attributeValue = 0;
					String type = (String) instAttribute.getAttributeType();
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
								.add(new EqualsComparisonExpression(instVertex,
										instAttribute.getIdentifier(),
										getHlclFactory().number(attributeValue)));

						getTransformations().add(
								new EqualsComparisonExpression(instVertex,
										instVertex, "SimAllowed", instAttribute
												.getIdentifier()));
					}
					// identifierId_SimRequired #= identifierId_Required
					if (instAttribute.getIdentifier().equals("Required")) {
						getTransformations()
								.add(new EqualsComparisonExpression(instVertex,
										instAttribute.getIdentifier(),
										getHlclFactory().number(attributeValue)));

						getTransformations().add(
								new EqualsComparisonExpression(instVertex,
										instVertex, "SimRequired",
										instAttribute.getIdentifier()));
					}
					// identifierId_SimRequired #==>
					// identifierId_ValidationSelected #= 1
					if (instAttribute.getIdentifier().equals(
							"ValidationSelected")) {
						AbstractComparisonExpression transformation9 = new EqualsComparisonExpression(
								instVertex, instAttribute.getIdentifier(),
								getHlclFactory().number(1));
						getTransformations().add(
								new ImplicationBooleanExpression(instVertex,
										"SimRequired", true, transformation9));
					}

					// identifierId_SimInitialRequiredLevel #=
					// identifierId_RequiredLevel
					if (instAttribute.getIdentifier().equals("RequiredLevel")) {
						getTransformations()
						.add(new EqualsComparisonExpression(instVertex,
								"SatisfactionConflict",
								getHlclFactory().number(0)));
						getTransformations()
								.add(new EqualsComparisonExpression(instVertex,
										instAttribute.getIdentifier(),
										getHlclFactory().number(attributeValue)));

						getTransformations().add(
								new EqualsComparisonExpression(instVertex,
										instVertex, "InitialRequiredLevel",
										instAttribute.getIdentifier()));
					}
					if (instAttribute.getIdentifier().equals("Satisfied")) {
						// ( ( 1 - identifierId_SimRequired ) +
						// identifierId_Satisfied ) #>= 1
						AbstractNumericExpression transformation1 = new DiffNumericExpression(
								instVertex, "SimRequired", false,
								getHlclFactory().number(1));
						transformation1 = new SumNumericExpression(instVertex,
								instAttribute.getIdentifier(), false,
								transformation1);

						getTransformations().add(
								new GreaterOrEqualsBooleanExpression(
										transformation1,
										new NumberNumericExpression(1)));

						// ( ( 1 - identifierId_Selected ) +
						// identifierId_Satisfied
						// ) #>= 1
						AbstractNumericExpression transformation2 = new DiffNumericExpression(
								instVertex, "Selected", false, getHlclFactory()
										.number(1));
						transformation2 = new SumNumericExpression(instVertex,
								instAttribute.getIdentifier(), false,
								transformation2);

						getTransformations().add(
								new GreaterOrEqualsBooleanExpression(
										transformation2,
										new NumberNumericExpression(1)));

						// ( 1 - identifierId_SimAllowed ) * (
						// identifierId_SimRequired + identifierId_Satisfied )
						// #=
						// 0
						AbstractNumericExpression transformation3 = new DiffNumericExpression(
								instVertex, "SimAllowed", false,
								getHlclFactory().number(1));
						AbstractNumericExpression transformation4 = new SumNumericExpression(
								instVertex, instVertex, "SimRequired",
								instAttribute.getIdentifier());
						transformation3 = new ProdNumericExpression(
								transformation3, transformation4);

						getTransformations().add(
								new EqualsComparisonExpression(transformation3,
										new NumberNumericExpression(0)));
					}

					if (instAttribute.getIdentifier().equals("ForcedSatisfied")) {
						// Identifier_Satisfied #<=>
						// ( ( Identifier_ForcedSatisfied #\/
						// Identifier_AlternativeSatisfied ) #\/
						// ( Identifier_ValidationSatisfied #/\
						// identifierId_SimAllowed )
						// #/\ ( 1 - identifierId_SatisfactionConflict ) )
						getTransformations()
								.add(new EqualsComparisonExpression(instVertex,
										instAttribute.getIdentifier(),
										getHlclFactory().number(attributeValue)));
						AbstractBooleanExpression transformation6 = new OrBooleanExpression(
								instVertex, instVertex, "ForcedSatisfied",
								"AlternativeSatisfied");
						AbstractBooleanExpression transformation7 = new AndBooleanExpression(
								instVertex, instVertex, "ValidationSatisfied",
								"SimAllowed");
						AbstractBooleanExpression transformation8 = new OrBooleanExpression(
								transformation6, transformation7);
						AbstractBooleanExpression transformation9 = new NotBooleanExpression(
								instVertex, "SatisfactionConflict");
						AbstractBooleanExpression transformation10 = new AndBooleanExpression(
								transformation8, transformation9);
						getTransformations().add(
								new DoubleImplicationBooleanExpression(
										instVertex, "Satisfied", true,
										transformation10));

					}
					// Set ForceSelected from GUI properties
					if (instAttribute.getIdentifier().equals("ForcedSelected")) {

						getTransformations()
								.add(new EqualsComparisonExpression(instVertex,
										instAttribute.getIdentifier(),
										getHlclFactory().number(attributeValue)));
					}
					if (instAttribute.getIdentifier().equals("Selected")) {
						// identifierId_Selected #<=>
						// ( ( ( ( identifierId_SolverSelected #/\
						// identifierId_NotPrefSelected )
						// #\/ identifierId_ValidationSelected ) #\/
						// identifierId_ForceSelected ) #/\ identifierId_SimAllowed ) 

						AbstractBooleanExpression transformation10 = new AndBooleanExpression(
								instVertex, instVertex, "SolverSelected",
								"NotPrefSelected");
						AbstractBooleanExpression transformation11 = new OrBooleanExpression(
								instVertex, "ValidationSelected", false,
								transformation10);
						AbstractBooleanExpression transformation12 = new OrBooleanExpression(
								instVertex, "ForcedSelected", false,
								transformation11);
						AbstractBooleanExpression transformation13 = new AndBooleanExpression(
								instVertex, "SimAllowed", false,
								transformation12);
						getTransformations().add(
								new DoubleImplicationBooleanExpression(
										instVertex, instAttribute
												.getIdentifier(), true,
										transformation13));
					}
				}

			}

		}

	}
}
