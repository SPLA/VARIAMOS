package com.variamos.dynsup.staticexpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mxgraph.util.mxResources;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstOverTwoRel;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ModelExpr;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.staticexprsup.AbstractBooleanExpression;
import com.variamos.dynsup.staticexprsup.AbstractComparisonExpression;
import com.variamos.dynsup.staticexprsup.AbstractExpression;
import com.variamos.dynsup.staticexprsup.AbstractNumericExpression;
import com.variamos.dynsup.staticexprsup.DiffNumericExpression;
import com.variamos.dynsup.staticexprsup.DoubleImplicationBooleanExpression;
import com.variamos.dynsup.staticexprsup.EqualsComparisonExpression;
import com.variamos.dynsup.staticexprsup.GreaterOrEqualsBooleanExpression;
import com.variamos.dynsup.staticexprsup.ImplicationBooleanExpression;
import com.variamos.dynsup.staticexprsup.LessOrEqualsBooleanExpression;
import com.variamos.dynsup.staticexprsup.NumberNumericExpression;
import com.variamos.dynsup.staticexprsup.OrBooleanExpression;
import com.variamos.dynsup.staticexprsup.ProdNumericExpression;
import com.variamos.dynsup.staticexprsup.SumNumericExpression;
import com.variamos.dynsup.translation.ModelExpr2HLCL;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.Identifier;

/**
 * A class to represent the constraints for restrictions of a concept. Part of
 * PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-16
 */
public class SingleElementExpressionSet extends ElementExpressionSet {

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
	private InstElement instVertex;

	private Map<String, BooleanExpression> booleanExpressions = new HashMap<String, BooleanExpression>();

	public BooleanExpression getBooleanExpression(String element) {
		return booleanExpressions.get(element);
	}

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
			InstElement instVertex, int execType) {
		super(identifier,
				mxResources.get("defect-concepts") + " " + identifier, idMap,
				hlclFactory);
		this.instVertex = instVertex;
		defineTransformations(execType);
	}

	public InstElement getInstEdge() {
		return instVertex;
	}

	public boolean validateConceptType(InstElement instElement, String element) {
		if (instElement == null)// || !(instElement instanceof InstVertex))
			return false;
		SyntaxElement metaElement = ((SyntaxElement) instElement
				.getTransSupportMetaElement());
		if (metaElement == null)
			return false;
		InstElement semElement = metaElement.getTransInstSemanticElement();
		while (semElement != null && semElement.getIdentifier() != null
				&& !semElement.getIdentifier().equals(element)) {
			InstElement sEle = semElement;
			semElement = null;
			for (InstElement ele : sEle.getTargetRelations())
				if (ele instanceof InstPairwiseRel) {
					if (((InstPairwiseRel) ele).getSupportMetaPairwiseRelIden()
							.equals("ExtendsRelation")) {
						semElement = ele.getTargetRelations().get(0);
						break;
					}
				} else if (((InstPairwiseRel) ele).getSupSyntaxEleId().equals(
						"ExtendsRelation")) {
					semElement = ele.getTargetRelations().get(0);
					break;
				}
		}
		if (semElement != null && semElement.getIdentifier() != null
				&& semElement.getIdentifier().equals(element)) {
			return true;
		}
		return false;
	}

	private void defineTransformations(int execType) {

		if (instVertex instanceof InstConcept
				|| instVertex instanceof InstOverTwoRel) {
			if (validateConceptType(instVertex, "GeneralElement")
					|| instVertex.getIdentifier().contains("Variable")) {
				InstAttribute validAttribute = instVertex
						.getInstAttribute("Active");
				if (validAttribute == null
						|| ((boolean) validAttribute.getValue()) == true) {
					List<AbstractExpression> coreAndFalseOptList = new ArrayList<AbstractExpression>();

					for (InstAttribute instAttribute : ((InstElement) instVertex)
							.getInstAttributesCollection()) {
						// System.out.println(instVertex.getIdentifier() + " "
						// + instAttribute.getIdentifier());
						int attributeValue = 0;
						String type = (String) instAttribute.getType();
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

						if (execType == ModelExpr2HLCL.VAL_UPD_EXEC) {
							if (instAttribute.getIdentifier().equals(
									"IsRootFeature")) {
								List<AbstractExpression> rootList = new ArrayList<AbstractExpression>();
								String id = instVertex
										.getTransSupportMetaElement()
										.getAutoIdentifier();
								if (id.equals("RootFeature")) {
									rootList.add(new EqualsComparisonExpression(
											instVertex, instAttribute
													.getIdentifier(),
											getHlclFactory().number(1)));
									/*
									 * rootList.add(new
									 * EqualsComparisonExpression( instVertex,
									 * "Required", getHlclFactory().number(1)));
									 */
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
								SyntaxElement element = (SyntaxElement) instVertex
										.getTransSupportMetaElement();

								List<AbstractExpression> parentList = new ArrayList<AbstractExpression>();
								if (element.getAutoIdentifier().equals(
										"LeafFeature")
										|| element.getAutoIdentifier().equals(
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
							/*
							 * if (instAttribute.getIdentifier().equals(
							 * "ConfSel")) {
							 * 
							 * coreAndFalseOptList .add(new
							 * EqualsComparisonExpression( instVertex,
							 * instAttribute .getIdentifier(),
							 * getHlclFactory().number(0))); }
							 * 
							 * if (instAttribute.getIdentifier().equals(
							 * "ConfNotSel")) {
							 * 
							 * coreAndFalseOptList .add(new
							 * EqualsComparisonExpression( instVertex,
							 * instAttribute .getIdentifier(),
							 * getHlclFactory().number(0))); }
							 */
							if (instAttribute.getIdentifier()
									.equals("Required")) {
								if (instVertex.getTransSupportMetaElement()
										.getAutoIdentifier()
										.equals("RootFeature")) {
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
										instVertex, "Sel", getHlclFactory()
												.number(1));
								coreAndFalseOptList
										.add(new ImplicationBooleanExpression(
												instVertex, instAttribute
														.getIdentifier(), true,
												transformation9));
							}

							/*
							 * if (instAttribute.getIdentifier() .equals("Sel"))
							 * {
							 * 
							 * coreAndFalseOptList .add(new
							 * EqualsComparisonExpression( instVertex,
							 * instVertex, "Order", "Sel"));
							 * 
							 * coreAndFalseOptList .add(new
							 * EqualsComparisonExpression( instVertex, "Opt",
							 * getHlclFactory().number(0))); } if
							 * (instAttribute.getIdentifier().equals(
							 * "NReqSel")) {
							 * 
							 * coreAndFalseOptList .add(new
							 * EqualsComparisonExpression( instVertex,
							 * instAttribute .getIdentifier(),
							 * getHlclFactory().number(0))); }
							 */
						}

						// End Validation Only
						// //////////////////////////////////////////////////////////////////////////////////////////////////////////

						// Init Simulation Only
						// //////////////////////////////////////////////////////////////////////////////////////////////////////////

						if (execType == ModelExpr2HLCL.SIMUL_MAPE
								|| execType == ModelExpr2HLCL.SIMUL_EXPORT
								|| execType == ModelExpr2HLCL.SIMUL_EXEC) {

							if (instAttribute.getIdentifier().equals(
									"ConditionalExpression")) {
								boolean dr = false;
								if (instAttribute.getValue() instanceof Integer)
									dr = true;
								if (!dr) {
									ModelExpr instanceExpression = (ModelExpr) instAttribute
											.getValue();
									if (instanceExpression != null
											&& !instanceExpression
													.createSGSExpression(
															instVertex
																	.getIdentifier())
													.toString()
													.endsWith(
															"right=null, operator=DoubleImplies]")) {
										// TODO Fix this when the expressions
										// are
										// verified
										booleanExpressions
												.put("Simul",
														(BooleanExpression) (instanceExpression)
																.createSGSExpression(instVertex
																		.getIdentifier()));
									} else
										dr = true;
								}
								if (dr)

									booleanExpressions
											.put("Simul",
													(BooleanExpression) getHlclFactory()
															.equals(getHlclFactory()
																	.newIdentifier(
																			instVertex
																					.getIdentifier()
																					+ "_CompExp"),
																	getHlclFactory()
																			.number(1)));
							}

							if (instAttribute.getIdentifier().equals(
									"ConfNotSel")) {
								getElementExpressions().add(
										new EqualsComparisonExpression(
												instVertex, instAttribute
														.getIdentifier(),
												getHlclFactory().number(
														attributeValue)));
							}
							if (instAttribute.getIdentifier().equals(
									"varConfDom")) {
								if (!((String) instAttribute.getValue())
										.equals(""))
									getElementExpressions().add(
											new EqualsComparisonExpression(
													instVertex, instVertex,
													"varConfValue", "value"));
							}

							if (instAttribute.getIdentifier().equals("ConfSel")) {
								getElementExpressions().add(
										new EqualsComparisonExpression(
												instVertex, instAttribute
														.getIdentifier(),
												getHlclFactory().number(
														attributeValue)));
							}
							if (instAttribute.getIdentifier()
									.equals("Prohibit")) {
								getElementExpressions().add(
										new EqualsComparisonExpression(
												instVertex, instAttribute
														.getIdentifier(),
												getHlclFactory().number(
														attributeValue)));
							}

							if (instAttribute.getIdentifier().equals(
									"SDReqLevel")) {

								AbstractExpression transformation50 = null;

								String satisficingType = (String) instVertex
										.getInstAttribute("satisficingType")
										.getValue();

								if (satisficingType.contains("high")) {
									transformation50 = new LessOrEqualsBooleanExpression(
											instVertex, instVertex,
											"SDReqLevel", "ClaimExpLevel");
								} else if (satisficingType.contains("low")) {
									transformation50 = new GreaterOrEqualsBooleanExpression(
											instVertex, instVertex,
											"SDReqLevel", "ClaimExpLevel");
								} else
									// (satisficingType.contains("close"))
									transformation50 = new EqualsComparisonExpression(
											instVertex, instVertex,
											"SDReqLevel", "ClaimExpLevel");

								getElementExpressions().add(
										new DoubleImplicationBooleanExpression(
												instVertex, "Sel", true,
												transformation50));
							}

							/*
							 * if (instAttribute.getIdentifier().equals(
							 * "NReqSel")) { getElementExpressions().add( new
							 * EqualsComparisonExpression( instVertex,
							 * instAttribute .getIdentifier(),
							 * getHlclFactory().number( attributeValue))); }
							 */
							if (instAttribute.getIdentifier()
									.equals("NPrefSel")) {
								getElementExpressions().add(
										new EqualsComparisonExpression(
												instVertex, instAttribute
														.getIdentifier(),
												getHlclFactory().number(0)));
							}

							if (instAttribute.getIdentifier().equals(
									"NNotPrefSel")) {
								getElementExpressions().add(
										new EqualsComparisonExpression(
												instVertex, instAttribute
														.getIdentifier(),
												getHlclFactory().number(0)));
							}

							if (instAttribute.getIdentifier().equals("Opt")) {
								// Opt #<==>
								if (execType != ModelExpr2HLCL.CORE_EXEC
										&& (execType != ModelExpr2HLCL.DESIGN_EXEC)) {
									AbstractNumericExpression transformation50 = new SumNumericExpression(
											instVertex, instVertex, "NReqSel",
											"ConfSel");
									AbstractNumericExpression transformation51 = new ProdNumericExpression(
											instVertex, "NPrefSel", true,
											transformation50);

									AbstractNumericExpression transformation52 = new SumNumericExpression(
											instVertex, instVertex, "NPrefSel",
											"ConfSel");
									AbstractNumericExpression transformation53 = new ProdNumericExpression(
											instVertex, "NReqSel", true,
											transformation52);

									AbstractNumericExpression transformation54 = new SumNumericExpression(
											transformation51, transformation53);

									getElementExpressions().add(
											new EqualsComparisonExpression(
													instVertex, "Opt", true,
													transformation54));
								}
								// Opt#=0

								getElementExpressions().add(
										new EqualsComparisonExpression(
												instVertex, "Opt",
												getHlclFactory().number(0)));

							}
							// Order#<==>
							if (instAttribute.getIdentifier().equals("Order")
									&& (execType != ModelExpr2HLCL.CORE_EXEC && (execType != ModelExpr2HLCL.DESIGN_EXEC))) {
								AbstractNumericExpression transformation49 = null;
								if (instVertex.getTransSupportMetaElement()
										.getAutoIdentifier()
										.endsWith("Softgoal")) {
									// System.out.println(instVertex
									// .getTransSupportMetaElement()
									// .getIdentifier());

									// DiffNumericExpression transformation488 =
									// new DiffNumericExpression(
									// instVertex, "Sel", false,
									// getHlclFactory().number(1));
									// DiffNumericExpression transformation489 =
									// new DiffNumericExpression(new
									// NumberNumericExpression(1),new
									// EqualsComparisonExpression(
									// instVertex,instVertex,
									// "SDReqLevel","ClaimExpLevel" ));
									// AbstractNumericExpression
									// transformation490 = new
									// ProdNumericExpression(
									// transformation488,
									// new NumberNumericExpression(8));
									// transformation49=new
									// SumNumericExpression(
									// transformation488, transformation490);

									DiffNumericExpression transformation488 = new DiffNumericExpression(
											instVertex, "Sel", false,
											getHlclFactory().number(1));
									transformation49 = new ProdNumericExpression(
											transformation488,
											new NumberNumericExpression(8));
								} else
									transformation49 = new NumberNumericExpression(
											0);

								AbstractNumericExpression transformation48 = new ProdNumericExpression(
										instVertex, "NReqSel", true,
										getHlclFactory().number(4));
								AbstractNumericExpression transformation50 = new SumNumericExpression(
										transformation49, transformation48);
								AbstractNumericExpression transformation55 = new SumNumericExpression(
										instVertex, "NPrefSel", true,
										transformation50);

								getElementExpressions().add(
										new EqualsComparisonExpression(
												instVertex, "Order", true,
												transformation55));
							}
						}

						// End Simulation Only
						// //////////////////////////////////////////////////////////////////////////////////////////////////////////

						// identifierId_Active #= value for simulation
						/*
						 * if (instAttribute.getIdentifier().equals("Active")) {
						 * getElementExpressions().add( new
						 * EqualsComparisonExpression(instVertex,
						 * instAttribute.getIdentifier(),
						 * getHlclFactory().number( attributeValue))); }
						 */
						// identifierId_Dead #= value for simulation
						if (instAttribute.getIdentifier().equals("Dead")) {
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
						/*
						 * if (instAttribute.getIdentifier().equals("Required"))
						 * { getElementExpressions().add( new
						 * EqualsComparisonExpression(instVertex, "Required",
						 * getHlclFactory() .number(attributeValue))); }
						 */
						// identifierId_SimInitialRequiredLevel #=
						// identifierId_RequiredLevel
						/*
						 * if (instAttribute.getIdentifier().equals( "RequLev")
						 * && (execType != Refas2Hlcl.CORE_EXEC)) {
						 * getElementExpressions().add( new
						 * EqualsComparisonExpression(instVertex,
						 * instAttribute.getIdentifier(),
						 * getHlclFactory().number( attributeValue)));
						 * 
						 * getElementExpressions().add( new
						 * EqualsComparisonExpression(instVertex, instVertex,
						 * "IniReqLev", instAttribute.getIdentifier()));
						 * 
						 * }
						 */
						if (instAttribute.getIdentifier().equals("ConfSel")) {

							AbstractNumericExpression transformation53 = new SumNumericExpression(
									instVertex, instVertex, "ConfSel",
									"NReqSel");
							AbstractNumericExpression transformation54 = new SumNumericExpression(
									instVertex, "Core", true, transformation53);
							AbstractBooleanExpression transformation55 = new LessOrEqualsBooleanExpression(
									transformation54,
									new NumberNumericExpression(1));
							getElementExpressions().add(transformation55);

						}

						// Set ForceSelected from GUI properties

						if (instAttribute.getIdentifier().equals("NNotPrefSel")) {
							// identifierId_NotAvailable #<=>
							// ( ( ( identifierId_ConfigNotSelected
							// #\/ identifierId_Dead )
							AbstractBooleanExpression transformation6 = new OrBooleanExpression(
									instVertex, instVertex, "ConfNotSel",
									"NNotPrefSel");
							AbstractBooleanExpression transformation7 = new OrBooleanExpression(
									instVertex, "Dead", true, transformation6);
							transformation7 = new OrBooleanExpression(
									instVertex, "Prohibit", true,
									transformation7);
							getElementExpressions().add(
									new DoubleImplicationBooleanExpression(
											instVertex, "Exclu", true,
											transformation7));
						}

						if (instAttribute.getIdentifier().equals("NReqSel")) {
							// identifierId_Selected #<=>
							// ( ( ( identifierId_ConfigSelected
							// #\/ identifierId_NextPrefSelected ) #\/
							// identifierId_NextReqSelected ) )

							AbstractBooleanExpression transformation6 = new OrBooleanExpression(
									instVertex, instVertex, "Core", "ConfSel");
							AbstractBooleanExpression transformation7 = new OrBooleanExpression(
									instVertex, instVertex, "NReqSel",
									"NPrefSel");
							AbstractBooleanExpression transformation8 = new OrBooleanExpression(
									transformation7, transformation6);
							getElementExpressions().add(
									new DoubleImplicationBooleanExpression(
											instVertex, "Sel", true,
											transformation8));

							// identifierId_Selected ) *
							// identifierId_NotAvailable ) #= 0
							AbstractNumericExpression transformation61 = new ProdNumericExpression(
									instVertex, instVertex, "Sel", "Exclu");
							EqualsComparisonExpression transformation62 = new EqualsComparisonExpression(
									transformation61,
									new NumberNumericExpression(0));
							getElementExpressions().add(transformation62);

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

	/*
	 * private AbstractNumericExpression sumRelations(InstVertex instVertex2,
	 * String string, List<String> outRelations, List<String> inRelations) {
	 * AbstractNumericExpression outExp = null; for (String relName :
	 * outRelations) { for (InstElement target :
	 * instVertex2.getTargetRelations()) { String type = ((InstPairwiseRelation)
	 * target) .getSemanticPairwiseRelType(); if (relName.equals(type)) { if
	 * (outExp == null) outExp = new SumNumericExpression((InstVertex) target
	 * .getTargetRelations().get(0), string, false, getHlclFactory().number(0));
	 * else outExp = new SumNumericExpression((InstVertex) target
	 * .getTargetRelations().get(0), string, true, outExp); } else if (type !=
	 * null && (type.equals("none") || type.equals("Group"))) { InstVertex
	 * grouprel = (InstVertex) target .getTargetRelations().get(0); if
	 * (grouprel.getTargetRelations().size() > 0) { String relType =
	 * ((InstPairwiseRelation) grouprel .getTargetRelations().get(0))
	 * .getSemanticPairwiseRelType(); if (relType.equals(relName)) if (outExp ==
	 * null) outExp = new SumNumericExpression( (InstVertex) grouprel
	 * .getTargetRelations().get(0) .getTargetRelations().get(0), string, false,
	 * getHlclFactory().number( 0)); else outExp = new SumNumericExpression(
	 * (InstVertex) grouprel .getTargetRelations().get(0)
	 * .getTargetRelations().get(0), string, true, outExp); } } } } for (String
	 * relName : inRelations) { for (InstElement target :
	 * instVertex.getSourceRelations()) { String type = ((InstPairwiseRelation)
	 * target) .getSemanticPairwiseRelType(); if (relName.equals(type)) { if
	 * (outExp == null) outExp = new SumNumericExpression((InstVertex) target
	 * .getSourceRelations().get(0), string, false, getHlclFactory().number(0));
	 * else outExp = new SumNumericExpression((InstVertex) target
	 * .getSourceRelations().get(0), string, true, outExp); } } } if (outExp ==
	 * null) return new NumberNumericExpression(0); return outExp; }
	 */

	private boolean oneParent(InstElement instVertex2) {
		int out = 0;
		List<String> outRelations = new ArrayList<String>();
		outRelations.add("mandatory");
		outRelations.add("optional");
		for (String relName : outRelations) {
			for (InstElement target : instVertex2.getTargetRelations()) {
				String type = ((InstPairwiseRel) target)
						.getSemanticPairwiseRelType();
				if (relName.equals(type)) {
					if (target.getTargetRelations().get(0)
							.getInstAttribute("Active").getAsBoolean())
						out++;
				} else if (type == null || (type.equals("Group"))) {
					InstElement grouprel = (InstElement) target
							.getTargetRelations().get(0);
					if (grouprel.getTargetRelations().size() > 0) {
						String relType = ((InstPairwiseRel) grouprel
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
