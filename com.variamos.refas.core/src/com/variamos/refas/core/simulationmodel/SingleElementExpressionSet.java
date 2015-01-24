package com.variamos.refas.core.simulationmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.mxgraph.util.mxResources;
import com.variamos.refas.core.expressions.AndBooleanExpression;
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
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaVertex;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;

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
			InstVertex instVertex, int execType) {
		super(identifier,
				mxResources.get("defect-concepts") + " " + identifier, idMap,
				hlclFactory);
		this.instVertex = instVertex;
		defineTransformations(execType);
	}

	public InstVertex getInstEdge() {
		return instVertex;
	}

	private void defineTransformations(int execType) {

		if (instVertex instanceof InstConcept
				|| instVertex instanceof InstOverTwoRelation) {
			MetaVertex metaElement = ((MetaVertex) instVertex
					.getTransSupportMetaElement());
			IntSemanticElement semElement = metaElement
					.getTransSemanticConcept();
			while (semElement != null && semElement.getIdentifier() != null
					&& !semElement.getIdentifier().equals("SemGeneralElement"))
				semElement = semElement.getParent();
			if (semElement != null && semElement.getIdentifier() != null
					&& semElement.getIdentifier().equals("SemGeneralElement")) {
				InstAttribute validAttribute = instVertex
						.getInstAttribute("Active");
				if (validAttribute == null
						|| ((boolean) validAttribute.getValue()) == true) {
					List<AbstractExpression> coreAndFalseOptList = new ArrayList<AbstractExpression>();
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
										.valueOf((String) instAttribute
												.getValue());
							else
								attributeValue = (Integer) instAttribute
										.getValue();
						}

						// Init Validation Only
						// ///////////////////////////////////////////////////////////////////////////////////////////////////////

						if (execType == Refas2Hlcl.VAL_UPD_EXEC) {
							if (instAttribute.getIdentifier().equals(
									"IsRootFeature")) {
								List<AbstractExpression> rootList = new ArrayList<AbstractExpression>();
								if (instVertex.getTransSupportMetaElement()
										.getIdentifier().equals("RootFeature")) {
									rootList.add(new EqualsComparisonExpression(
											instVertex, instAttribute
													.getIdentifier(),
											getHlclFactory().number(1)));
									rootList.add(new EqualsComparisonExpression(
											instVertex, "Required",
											getHlclFactory().number(1)));
									this.getRelaxableExpressions().put("Root",
											rootList);
								} else {
									// identifierId_Root #= 0
									rootList.add(new EqualsComparisonExpression(
											instVertex, instAttribute
													.getIdentifier(),
											getHlclFactory().number(0)));
									this.getRelaxableExpressions().put("Root",
											rootList);
								}
							}

							if (instAttribute.getIdentifier().equals(
									"HasParent")) {
								MetaElement element = (MetaElement) instVertex
										.getTransSupportMetaElement();

								List<AbstractExpression> parentList = new ArrayList<AbstractExpression>();
								if (element.getIdentifier().equals(
										"LeafFeature")
										|| element.getIdentifier().equals(
												"GeneralFeature")) {
									if (oneParent(instVertex))
										parentList
												.add(new EqualsComparisonExpression(
														instVertex,
														instAttribute
																.getIdentifier(),
														getHlclFactory()
																.number(1)));
									else
										parentList
												.add(new EqualsComparisonExpression(
														instVertex,
														instAttribute
																.getIdentifier(),
														getHlclFactory()
																.number(0)));
								} else
									parentList
											.add(new EqualsComparisonExpression(
													instVertex, instAttribute
															.getIdentifier(),
													getHlclFactory().number(1)));
								this.getRelaxableExpressions().put("Parent",
										parentList);
							}

							if (instAttribute.getIdentifier().equals(
									"ConfigNotSelected")) {

								coreAndFalseOptList
										.add(new EqualsComparisonExpression(
												instVertex, instAttribute
														.getIdentifier(),
												getHlclFactory().number(0)));
							}

							if (instAttribute.getIdentifier()
									.equals("Required")) {
								if (instVertex.getTransSupportMetaElement()
										.getIdentifier().equals("RootFeature")) {
									coreAndFalseOptList
											.add(new EqualsComparisonExpression(
													instVertex, instAttribute
															.getIdentifier(),
													getHlclFactory().number(1)));
								} else
									coreAndFalseOptList
											.add(new EqualsComparisonExpression(
													instVertex, instAttribute
															.getIdentifier(),
													getHlclFactory().number(
															attributeValue)));

								// identifierId_Required #==>
								// identifierId_Selected #= 1
								AbstractComparisonExpression transformation9 = new EqualsComparisonExpression(
										instVertex, "Selected",
										getHlclFactory().number(1));
								coreAndFalseOptList
										.add(new ImplicationBooleanExpression(
												instVertex, instAttribute
														.getIdentifier(), true,
												transformation9));
							}

							if (instAttribute.getIdentifier()
									.equals("Selected")) {

								coreAndFalseOptList
										.add(new EqualsComparisonExpression(
												instVertex, instVertex,
												"Order", "Selected"));

								coreAndFalseOptList
										.add(new EqualsComparisonExpression(
												instVertex, "Opt",
												getHlclFactory().number(0)));
							}
						}

						// End Validation Only
						// //////////////////////////////////////////////////////////////////////////////////////////////////////////

						// Init Simulation Only
						// //////////////////////////////////////////////////////////////////////////////////////////////////////////

						if (execType == Refas2Hlcl.SIMUL_EXEC) {


							if (instAttribute.getIdentifier().equals(
									"NextNotSelected")) {
								getElementExpressions().add(
										new EqualsComparisonExpression(
												instVertex, instAttribute
														.getIdentifier(),
												getHlclFactory().number(
														attributeValue)));
							}
							
							if (instAttribute.getIdentifier().equals(
									"NextPrefSelected")) {
								getElementExpressions().add(
										new EqualsComparisonExpression(
												instVertex, instAttribute
														.getIdentifier(),
												getHlclFactory().number(
														attributeValue)));
							}

						}

						// End Simulation Only
						// //////////////////////////////////////////////////////////////////////////////////////////////////////////

						if (instAttribute.getIdentifier().equals(
								"ConfigSelected")) {
							getElementExpressions().add(
									new EqualsComparisonExpression(instVertex,
											instAttribute.getIdentifier(),
											getHlclFactory().number(
													attributeValue)));
						}

						if (instAttribute.getIdentifier().equals(
								"ConfigNotSelected")) {
							getElementExpressions().add(
									new EqualsComparisonExpression(instVertex,
											instAttribute.getIdentifier(),
											getHlclFactory().number(
													attributeValue)));

						}

						// identifierId_Dead #= value for simulation
						if (instAttribute.getIdentifier().equals("Dead")) {
							getElementExpressions().add(
									new EqualsComparisonExpression(instVertex,
											instAttribute.getIdentifier(),
											getHlclFactory().number(
													attributeValue)));
						}

						// identifierId_Active #= value for simulation
						if (instAttribute.getIdentifier().equals("Active")) {
							getElementExpressions().add(
									new EqualsComparisonExpression(instVertex,
											instAttribute.getIdentifier(),
											getHlclFactory().number(
													attributeValue)));
						}

						// identifierId_Core #= value for simulation
						if (instAttribute.getIdentifier().equals("Core")) {
							getElementExpressions().add(
									new EqualsComparisonExpression(instVertex,
											instAttribute.getIdentifier(),
											getHlclFactory().number(
													attributeValue)));
						}

						if (instAttribute.getIdentifier().equals("Required")) {
							getElementExpressions().add(
									new EqualsComparisonExpression(instVertex,
											"Required", getHlclFactory()
													.number(attributeValue)));
						}

						// identifierId_SimInitialRequiredLevel #=
						// identifierId_RequiredLevel
						if (instAttribute.getIdentifier().equals(
								"RequiredLevel")
								&& (execType != Refas2Hlcl.CORE_EXEC)) {
							getElementExpressions().add(
									new EqualsComparisonExpression(instVertex,
											instAttribute.getIdentifier(),
											getHlclFactory().number(
													attributeValue)));

							getElementExpressions().add(
									new EqualsComparisonExpression(instVertex,
											instVertex, "InitialRequiredLevel",
											instAttribute.getIdentifier()));
						}
						/*
						 * if
						 * (instAttribute.getIdentifier().equals("Satisfied")) {
						 * // ( ( 1 - identifierId_SimRequired ) + //
						 * identifierId_Satisfied ) #>= 1
						 * AbstractNumericExpression transformation1 = new
						 * DiffNumericExpression( instVertex, "SimRequired",
						 * false, getHlclFactory().number(1)); transformation1 =
						 * new SumNumericExpression(instVertex,
						 * instAttribute.getIdentifier(), false,
						 * transformation1);
						 * 
						 * getElementExpressions().add( new
						 * GreaterOrEqualsBooleanExpression( transformation1,
						 * new NumberNumericExpression(1)));
						 * 
						 * // Restriction to only show satisfaction is selected
						 * getElementExpressions().add( new
						 * DoubleImplicationBooleanExpression(
						 * instVertex,instVertex,
						 * instAttribute.getIdentifier(),"Selected"));
						 * 
						 * // ( ( 1 - identifierId_Selected ) + //
						 * identifierId_Satisfied // ) #>= 1
						 * AbstractNumericExpression transformation2 = new
						 * DiffNumericExpression( instVertex, "Selected", false,
						 * getHlclFactory() .number(1)); transformation2 = new
						 * SumNumericExpression(instVertex,
						 * instAttribute.getIdentifier(), false,
						 * transformation2);
						 * 
						 * getElementExpressions().add( new
						 * GreaterOrEqualsBooleanExpression( transformation2,
						 * new NumberNumericExpression(1))); }
						 */
						/*
						 * if (instAttribute.getIdentifier()
						 * .equals("NextNotSatisfied")) { //
						 * IdentifierId_Satisfied #<=> // ( (
						 * IdentifierId_ConfigSatisfied #\/ //
						 * IdentifierId_NextPrefSatisfied ) #\/ // (
						 * IdentifierId_NextReqSatisfied ) // #/\ ( 1 -
						 * identifierId_NextNotSatisfied ) )
						 * getElementExpressions() .add(new
						 * EqualsComparisonExpression(instVertex,
						 * instAttribute.getIdentifier(),
						 * getHlclFactory().number(attributeValue)));
						 * AbstractBooleanExpression transformation6 = new
						 * OrBooleanExpression( instVertex, instVertex,
						 * "ConfigSatisfied", "NextPrefSatisfied");
						 * AbstractBooleanExpression transformation7 = new
						 * OrBooleanExpression( instVertex, "NextReqSatisfied",
						 * false, transformation6); AbstractBooleanExpression
						 * transformation9 = new NotBooleanExpression(
						 * instVertex, "NextNotSatisfied");
						 * AbstractBooleanExpression transformation10 = new
						 * AndBooleanExpression( transformation7,
						 * transformation9); getElementExpressions().add( new
						 * DoubleImplicationBooleanExpression( instVertex,
						 * "Satisfied", true, transformation10)); }
						 */

						if (instAttribute.getIdentifier().equals(
								"NextNotSelected")) {
							List<String> outRelations = new ArrayList<String>();

							List<String> inRelations = new ArrayList<String>();
							// .add("mandatory");
							// inRelations.add("optional");
							/*
							 * AbstractNumericExpression transformation500 =
							 * sumRelations( instVertex, "Active", outRelations,
							 * inRelations); EqualsComparisonExpression
							 * transformation501 = new
							 * EqualsComparisonExpression( transformation500,
							 * new NumberNumericExpression(0));
							 * EqualsComparisonExpression transformation502 =
							 * new EqualsComparisonExpression( instVertex,
							 * "NextNotSatisfied",true, new
							 * NumberNumericExpression(0));
							 * getElementExpressions().add( new
							 * DoubleImplicationBooleanExpression(
							 * transformation501,transformation502 ));
							 */
					/*		outRelations = new ArrayList<String>();
							outRelations.add("conflict");
							outRelations.add("optional");
							outRelations.add("mandatory");
							outRelations.add("required");
							inRelations = new ArrayList<String>();
							inRelations.add("conflict");
							inRelations.add("mandatory");
							AbstractNumericExpression transformation50 = sumRelations(
									instVertex, "NotAvailable", outRelations,
									inRelations);
							AbstractBooleanExpression transformation51 = new GreaterOrEqualsBooleanExpression(
									transformation50,
									new NumberNumericExpression(1));
									*/
							/*
							 * AbstractBooleanExpression transformation511 = new
							 * OrBooleanExpression( instVertex,
							 * "NextNotSatisfied", true, transformation51);
							 */
					/*		AbstractBooleanExpression transformation52 = new NotBooleanExpression(
									instVertex, "Dead");
							AbstractBooleanExpression transformation53 = new AndBooleanExpression(
									transformation51, transformation52);
							AbstractBooleanExpression transformation54 = new NotBooleanExpression(
									instVertex, "ConfigNotSelected");
							AbstractBooleanExpression transformation55 = new AndBooleanExpression(
									transformation54, transformation53);
							getElementExpressions().add(
									new DoubleImplicationBooleanExpression(
											instVertex, "NextNotSelected",
											false, transformation55));
											*/
						}

						// Order#<==>
						if (instAttribute.getIdentifier().equals("Order")
								&& (execType != Refas2Hlcl.CORE_EXEC && (execType != Refas2Hlcl.DESIGN_EXEC))) {
							AbstractNumericExpression transformation48 = new ProdNumericExpression(
									instVertex, "NextReqSelected", true,
									getHlclFactory().number(4));
							// AbstractNumericExpression transformation49 = new
							// SumNumericExpression(
							// instVertex, instVertex, "NextPrefSatisfied",
							// "ConfigSatisfied");
							// AbstractNumericExpression transformation50 = new
							// DiffNumericExpression(
							// instVertex, "NextPrefSelected", false,
							// transformation49);
							// AbstractNumericExpression transformation51 = new
							// ProdNumericExpression(
							// transformation50,
							// new NumberNumericExpression(8));
							// AbstractNumericExpression transformation52 = new
							// SumNumericExpression(
							// instVertex, instVertex, "NextReqSatisfied",
							// "ConfigSatisfied");

							// AbstractNumericExpression transformation53 = new
							// DiffNumericExpression(
							// instVertex, "NextReqSelected", false,
							// transformation52);
							// AbstractNumericExpression transformation54 = new
							// ProdNumericExpression(
							// transformation53, new NumberNumericExpression(
							// 16));

							AbstractNumericExpression transformation55 = new SumNumericExpression(
									instVertex, "NextPrefSelected", true,
									transformation48);

							getElementExpressions().add(
									new EqualsComparisonExpression(instVertex,
											"Order", true, transformation55));
						}

						// Set ForceSelected from GUI properties

						if (instAttribute.getIdentifier()
								.equals("NotAvailable")) {
							// identifierId_NotAvailable #<=>
							// ( ( ( identifierId_NextNotSelected
							// #\/ identifierId_ConfigNotSelected )
							AbstractBooleanExpression transformation6 = new OrBooleanExpression(
									instVertex, instVertex,
									"ConfigNotSelected", "NextNotSelected");
							getElementExpressions().add(
									new DoubleImplicationBooleanExpression(
											instVertex, "NotAvailable", true,
											transformation6));
						}

						if (instAttribute.getIdentifier().equals("Selected")) {
							// identifierId_Selected #<=>
							// ( ( ( identifierId_ConfigSelected
							// #\/ identifierId_NextPrefSelected ) #\/
							// identifierId_NextReqSelected ) )

							AbstractBooleanExpression transformation6 = new OrBooleanExpression(
									instVertex, instVertex, "ConfigSelected",
									"NextPrefSelected");
							AbstractBooleanExpression transformation7 = new OrBooleanExpression(
									instVertex, "NextReqSelected", false,
									transformation6);
							getElementExpressions().add(
									new DoubleImplicationBooleanExpression(
											instVertex, "Selected", true,
											transformation7));

							// identifierId_Selected ) *
							// identifierId_NotAvailable ) #= 0
							AbstractNumericExpression transformation61 = new ProdNumericExpression(
									instVertex, instVertex, "Selected",
									"NotAvailable");
							EqualsComparisonExpression transformation62 = new EqualsComparisonExpression(
									transformation61,
									new NumberNumericExpression(0));
							getElementExpressions().add(transformation62);

							// Opt #<==>
							if (execType != Refas2Hlcl.CORE_EXEC
									&& (execType != Refas2Hlcl.DESIGN_EXEC)) {
								AbstractNumericExpression transformation50 = new SumNumericExpression(
										instVertex, instVertex,
										"NextReqSelected", "ConfigSelected");
								AbstractNumericExpression transformation51 = new ProdNumericExpression(
										instVertex, "NextPrefSelected", true,
										transformation50);

								AbstractNumericExpression transformation52 = new SumNumericExpression(
										instVertex, instVertex,
										"NextPrefSelected", "ConfigSelected");
								AbstractNumericExpression transformation53 = new ProdNumericExpression(
										instVertex, "NextReqSelected", true,
										transformation52);

								AbstractNumericExpression transformation54 = new SumNumericExpression(
										transformation51, transformation53);

								// AbstractNumericExpression transformation55 =
								// new
								// SumNumericExpression(
								// instVertex, instVertex, "NextReqSatisfied",
								// "ConfigSatisfied");

								// AbstractNumericExpression transformation56 =
								// new
								// ProdNumericExpression(
								// instVertex, "NextPrefSatisfied", true,
								// transformation55);

								// AbstractNumericExpression transformation57 =
								// new
								// SumNumericExpression(
								// instVertex, instVertex,
								// "NextPrefSatisfied", "ConfigSatisfied");
								// AbstractNumericExpression transformation58 =
								// new
								// ProdNumericExpression(
								// instVertex, "NextReqSatisfied", true,
								// transformation57);

								// AbstractNumericExpression transformation59 =
								// new
								// SumNumericExpression(
								// transformation56, transformation58);

								// AbstractNumericExpression transformation60 =
								// new
								// SumNumericExpression(
								// transformation54, transformation59);

								getElementExpressions().add(
										new EqualsComparisonExpression(
												instVertex, "Opt", true,
												transformation54));
							}
							// Opt#=0

							getElementExpressions().add(
									new EqualsComparisonExpression(instVertex,
											"Opt", getHlclFactory().number(0)));

						}
					}
					List<AbstractExpression> coreList = this
							.getCompulsoryExpressionList("Core");
					if (coreList != null)
						coreList.addAll(coreAndFalseOptList);
					else
						this.getCompulsoryExpressions().put("Core",
								coreAndFalseOptList);

					List<AbstractExpression> falseList = this
							.getCompulsoryExpressionList("FalseOpt");
					if (falseList != null)
						falseList.addAll(coreAndFalseOptList);
					this.getCompulsoryExpressions().put("FalseOpt",
							coreAndFalseOptList);

					List<AbstractExpression> falseList2 = this
							.getCompulsoryExpressionList("FalseOpt2");
					if (falseList2 != null)
						falseList2.addAll(coreAndFalseOptList);
					this.getCompulsoryExpressions().put("FalseOpt2",
							coreAndFalseOptList);

				}
			}
		}

	}

	private AbstractNumericExpression sumRelations(InstVertex instVertex2,
			String string, List<String> outRelations, List<String> inRelations) {
		AbstractNumericExpression outExp = null;
		for (String relName : outRelations) {
			for (InstElement target : instVertex2.getTargetRelations()) {
				String type = ((InstPairwiseRelation) target)
						.getSemanticPairwiseRelType();
				if (relName.equals(type)) {
					if (outExp == null)
						outExp = new SumNumericExpression((InstVertex) target
								.getTargetRelations().get(0), string, false,
								getHlclFactory().number(0));
					else
						outExp = new SumNumericExpression((InstVertex) target
								.getTargetRelations().get(0), string, true,
								outExp);
				} else if (type != null
						&& (type.equals("none") || type.equals("Group"))) {
					InstVertex grouprel = (InstVertex) target
							.getTargetRelations().get(0);
					if (grouprel.getTargetRelations().size() > 0) {
						String relType = ((InstPairwiseRelation) grouprel
								.getTargetRelations().get(0))
								.getSemanticPairwiseRelType();
						if (relType.equals(relName))
							if (outExp == null)
								outExp = new SumNumericExpression(
										(InstVertex) grouprel
												.getTargetRelations().get(0)
												.getTargetRelations().get(0),
										string, false, getHlclFactory().number(
												0));
							else
								outExp = new SumNumericExpression(
										(InstVertex) grouprel
												.getTargetRelations().get(0)
												.getTargetRelations().get(0),
										string, true, outExp);
					}
				}
			}
		}
		for (String relName : inRelations) {
			for (InstElement target : instVertex.getSourceRelations()) {
				String type = ((InstPairwiseRelation) target)
						.getSemanticPairwiseRelType();
				if (relName.equals(type)) {
					if (outExp == null)
						outExp = new SumNumericExpression((InstVertex) target
								.getSourceRelations().get(0), string, false,
								getHlclFactory().number(0));
					else
						outExp = new SumNumericExpression((InstVertex) target
								.getSourceRelations().get(0), string, true,
								outExp);
				}
			}
		}
		if (outExp == null)
			return new NumberNumericExpression(0);
		return outExp;
	}

	private boolean oneParent(InstVertex instVertex2) {
		int out = 0;
		List<String> outRelations = new ArrayList<String>();
		outRelations.add("mandatory");
		outRelations.add("optional");
		for (String relName : outRelations) {
			for (InstElement target : instVertex2.getTargetRelations()) {
				String type = ((InstPairwiseRelation) target)
						.getSemanticPairwiseRelType();
				if (relName.equals(type)) {
					if (target.getTargetRelations().get(0)
							.getInstAttribute("Active").getAsBoolean())
						out++;
				} else if (type != null
						&& (type.equals("Group") || type.equals("none"))) {
					InstVertex grouprel = (InstVertex) target
							.getTargetRelations().get(0);
					if (grouprel.getTargetRelations().size() > 0) {
						String relType = ((InstPairwiseRelation) grouprel
								.getTargetRelations().get(0))
								.getSemanticPairwiseRelType();
						if (relType.equals(relName))
							if (grouprel.getTargetRelations().get(0)
									.getTargetRelations().get(0)
									.getInstAttribute("Active").getAsBoolean())
								out++;
					}

				}

			}
		}
		if (out == 1)
			return true;
		return false;
	}
}
