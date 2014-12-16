package com.variamos.refas.core.simulationmodel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency;
import com.variamos.refas.core.staticconcepts.Refas;
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
import com.variamos.refas.core.types.CardinalityType;
import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;
import com.variamos.syntaxsupport.metametamodel.MetaDirectRelation;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metametamodel.MetaGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstVertex;

/**
 * Class to create the Hlcl program. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public class Refas2Hlcl {
	private HlclFactory f = new HlclFactory();

	public Refas2Hlcl(Refas refas) {
		HlclProgram prog = new HlclProgram();

		Map<String, Identifier> idMap = new HashMap<>();
		prog.addAll(vertexExpressions(refas, idMap));
		prog.addAll(edgeExpressions(refas, idMap));
		//Previous edgeExpressions is required to fill the attribute names for groupExpressions
		prog.addAll(groupExpressions(refas, idMap));

		Set<Identifier> identifiers = new TreeSet<Identifier>();
		for (Expression exp : prog) {
			identifiers.addAll(HlclUtil.getUsedIdentifiers(exp));
			System.out.println(exp);
		}
		// Call the SWIProlog and obtain the result
		List<String> prologOut = null;
		int i = 0;
		for (Identifier identifier : identifiers) {
			String id = identifier.getId();
			String[] split = id.split("_");
			String conceptId = split[0];
			String attribute = split[1];
			InstVertex vertex = refas.getVertex(conceptId);
			if (vertex.getInstAttribute(attribute).getModelingAttributeType()
					.equals("Boolean"))
			/*	if (prologOut.get(i).equals(1))
					vertex.getInstAttribute(attribute).setValue(true);
				else
					vertex.getInstAttribute(attribute).setValue(false);
			*/
				vertex.getInstAttribute(attribute).setValue(true); //TODO delete
			else
				//	vertex.getInstAttribute(attribute).setValue(prologOut.get(i));
			System.out.print(conceptId
					+ " "
					+ attribute
					+ " "
					+ vertex.getInstAttribute(attribute)
							.getModelingAttributeType() + "; ");
		}
		System.out.println();

	}

	private HlclProgram vertexExpressions(Refas refas,
			Map<String, Identifier> idMap) {
		HlclProgram prog = new HlclProgram();
		for (InstVertex elm : refas.getVariabilityVertexCollection()) {
			if (elm instanceof InstConcept) { // TODO not required if the method
												// returns InstConcepts
				InstConcept instConcept = (InstConcept) elm;

				MetaConcept metaConcept = instConcept.getMetaConcept();
				if (metaConcept != null) {
					List<AbstractTransformation> transformations = new ArrayList<AbstractTransformation>();
					for (InstAttribute instAttribute : instConcept
							.getInstAttributesCollection()) {
						// A_SimAllowed #= A_Allowed
						if (instAttribute.getIdentifier().equals("Allowed")) {
							int attributeVale = ((boolean) instAttribute
									.getValue()) ? 1 : 0;
							transformations
									.add(new AssignBooleanTransformation(elm,
											instAttribute.getIdentifier(), f
													.number(attributeVale)));

							transformations
									.add(new AssignBooleanTransformation(elm,
											elm, "SimAllowed", instAttribute
													.getIdentifier()));
						}
						// A_SimRequired #= A_Required
						if (instAttribute.getIdentifier().equals("Required")) {
							int attributeVale = (Boolean) ((boolean) instAttribute
									.getValue()) ? 1 : 0;
							transformations
									.add(new AssignBooleanTransformation(elm,
											instAttribute.getIdentifier(), f
													.number(attributeVale)));
							transformations
									.add(new AssignBooleanTransformation(elm,
											elm, "SimRequired", instAttribute
													.getIdentifier()));
						}
						// A_PreferredSelected #= 1
						if (instAttribute.getIdentifier().equals(
								"PreferredSelected")) {
							int attributeVale = ((boolean) instAttribute
									.getValue()) ? 1 : 0;
							transformations
									.add(new AssignBooleanTransformation(elm,
											instAttribute.getIdentifier(), f
													.number(attributeVale)));
						}
						// A_Optional #= 0
						if (instAttribute.getIdentifier().equals("Optional")) {
							int attributeVale = ((boolean) instAttribute
									.getValue()) ? 1 : 0;
							transformations
									.add(new AssignBooleanTransformation(elm,
											instAttribute.getIdentifier(), f
													.number(attributeVale)));
						}
						// A_SimInitialRequiredLevel #= A_RequiredLevel
						if (instAttribute.getIdentifier().equals(
								"RequiredLevel")) {
							int attributeVale = ((Integer) instAttribute
									.getValue()).intValue();
							transformations
									.add(new AssignBooleanTransformation(elm,
											instAttribute.getIdentifier(), f
													.number(attributeVale)));
							transformations
									.add(new AssignBooleanTransformation(elm,
											elm, "InitialRequiredLevel",
											instAttribute.getIdentifier()));
						}
						if (instAttribute.getIdentifier().equals("Satisfied")) {
							// (( 1 - A_SimRequired) + A_Satisfied) #>= 1
							AbstractNumericTransformation transformation1 = new DiffNumericTransformation(
									elm, "SimRequired", false, f.number(1));
							transformation1 = new SumNumericTransformation(elm,
									instAttribute.getIdentifier(), false,
									transformation1);

							transformations
									.add(new GreaterOrEqualsBooleanTransformation(
											transformation1,
											new NumberNumericTransformation(1)));

							// (( 1 - A_Selected) + A_Satisfied) #>= 1
							AbstractNumericTransformation transformation2 = new DiffNumericTransformation(
									elm, "Selected", false, f.number(1));
							transformation2 = new SumNumericTransformation(elm,
									instAttribute.getIdentifier(), false,
									transformation2);

							transformations
									.add(new GreaterOrEqualsBooleanTransformation(
											transformation2,
											new NumberNumericTransformation(1)));

							// (1 - A_SimAllowed) * (A_SimRequired +
							// A_Satisfied) #= 0
							AbstractNumericTransformation transformation3 = new DiffNumericTransformation(
									elm, "Selected", false, f.number(1));
							AbstractNumericTransformation transformation4 = new SumNumericTransformation(
									elm, elm, "SimRequired",
									instAttribute.getIdentifier());
							transformation3 = new ProdNumericTransformation(
									transformation3, transformation4);

							transformations
									.add(new EqualsComparisonTransformation(
											transformation3,
											new NumberNumericTransformation(0)));

							// A_Satisfied #<=> ( ( A_ForcedSatisfied #\/
							// A_AlternativeSatisfied ) #\/ (
							// A_ValidationSatisfied) #/\ A_SimAllowed ) )

							AbstractBooleanTransformation transformation5 = new OrBooleanTransformation(
									elm, elm, "ForcedSatisfied",
									"AlternativeSatisfied");
							AbstractBooleanTransformation transformation6 = new AndBooleanTransformation(
									elm, elm, "ValidationSatisfied",
									"SimAllowed");
							AbstractBooleanTransformation transformation7 = new OrBooleanTransformation(
									transformation5, transformation6);
							transformations
									.add(new EqualsComparisonTransformation(
											elm, instAttribute.getIdentifier(),
											true, transformation7));

						}
						if (instAttribute.getIdentifier().equals("Selected")) {
							// A_Selected #<=> ( ( ( A_SolverSelected #/\
							// A_PreferredSelected) #\/ A_ValidationSelected )
							// #\/ A_SimRequired )

							AbstractBooleanTransformation transformation8 = new AndBooleanTransformation(
									elm, elm, "SolverSelected",
									"PreferredSelected");
							AbstractBooleanTransformation transformation9 = new OrBooleanTransformation(
									elm, elm, "ValidationSelected",
									"SimRequired");
							AbstractBooleanTransformation transformation10 = new OrBooleanTransformation(
									transformation8, transformation9);
							transformations
									.add(new EqualsComparisonTransformation(
											elm, instAttribute.getIdentifier(),
											true, transformation10));

						}

					}
					for (AbstractTransformation transformation : transformations) {
						idMap.putAll(transformation.getIndentifiers(f));
						if (transformation instanceof AbstractBooleanTransformation)
							prog.add(((AbstractBooleanTransformation) transformation)
									.transform(f, idMap));
						else
							prog.add(((AbstractComparisonTransformation) transformation)
									.transform(f, idMap));
					}

				}

			}

		}
		return prog;
	}

	private HlclProgram edgeExpressions(Refas refas,
			Map<String, Identifier> idMap) {
		HlclProgram prog = new HlclProgram();
		for (InstEdge elm : refas.getConstraintInstEdgesCollection()) {
			MetaEdge metaEdge = elm.getMetaEdge();
			if (metaEdge != null
					&& elm.getInstAttribute(MetaDirectRelation.VAR_METADIRECTEDGETYPE) != null
					&& !(elm.getToRelation() instanceof InstGroupDependency)) {
				DirectEdgeType relationType = DirectEdgeType
						.valueOf(((String) elm.getInstAttribute(
								MetaDirectRelation.VAR_METADIRECTEDGETYPE)
								.getValue()).trim().replace(" ", "_"));

				List<AbstractBooleanTransformation> transformations = new ArrayList<AbstractBooleanTransformation>();
				Set<String> sourceAttributeNames = new HashSet<String>();
				switch (relationType) {

				case preferred:
					sourceAttributeNames.add("Satisfied"); // TODO fix, only
															// shows the second
															// attribute
					sourceAttributeNames.add("PreferredSelected");
					// ( ( A_Satisfied #/\ B_Satisfied ) #/\ A_PreferredSelected
					// ) #==> ( (B_PreferredSelected #=1) #/\
					// (A_PreferredSelected #= 0) )
					AbstractBooleanTransformation transformation1 = new AndBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Satisfied", "Satisfied");
					AbstractBooleanTransformation transformation2 = new AndBooleanTransformation(
							elm.getFromRelation(), "PreferredSelected", false,
							transformation1);

					AbstractBooleanTransformation transformation3 = new AssignBooleanTransformation(
							elm.getToRelation(), "PreferredSelected",
							f.number(1));
					AbstractBooleanTransformation transformation4 = new AssignBooleanTransformation(
							elm.getFromRelation(), "PreferredSelected",
							f.number(0));

					AbstractBooleanTransformation transformation5 = new AndBooleanTransformation(
							transformation3, transformation4);

					transformations.add(new ImplicationBooleanTransformation(
							transformation2, transformation5));

					break;
				case required:
					sourceAttributeNames.add("Selected");
					// (( 1 - A_Selected) + B_Selected) #>= 1
					AbstractNumericTransformation transformation6 = new DiffNumericTransformation(
							elm.getFromRelation(), "Selected", false,
							f.number(1));
					AbstractNumericTransformation transformation7 = new SumNumericTransformation(
							elm.getToRelation(), "Selected", false,
							transformation6);
					transformations
							.add(new GreaterOrEqualsBooleanTransformation(
									transformation7,
									new NumberNumericTransformation(1)));
					break;
				case conflict:
					sourceAttributeNames.add("Selected");
					// A_Selected #==> B_ValidationSelected #= 0
					AbstractBooleanTransformation transformation8 = new AssignBooleanTransformation(
							elm.getToRelation(), "ValidationSelected",
							f.number(0));
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), "Selected", true,
							transformation8));

					// B_Selected #==> A_ValidationSelected #= 0
					AbstractBooleanTransformation transformation9 = new AssignBooleanTransformation(
							elm.getFromRelation(), "ValidationSelected",
							f.number(0));
					transformations.add(new ImplicationBooleanTransformation(
							elm.getToRelation(), "Selected", true,
							transformation9));
					// TODO to validation expressions missing
					break;
				case alternative:
					sourceAttributeNames.add("Satisfied");
					sourceAttributeNames.add("Selected");
					// ( ( ( 1 - A_Satisfied ) #/\ B_Satisfied ) #/\ A_Selected
					// ) ) #==> ( A_AlternativeSatisfied #= 1 #/\
					// B_ValidationSelected #= 1 )
					AbstractBooleanTransformation transformation10 = new NotBooleanTransformation(
							elm.getFromRelation(), "Satisfied");
					AbstractBooleanTransformation transformation11 = new AndBooleanTransformation(
							elm.getToRelation(), "Satisfied", false,
							transformation10);
					AbstractBooleanTransformation transformation12 = new AndBooleanTransformation(
							elm.getFromRelation(), "Selected", false,
							transformation11);
					AbstractBooleanTransformation transformation13 = new AssignBooleanTransformation(
							elm.getFromRelation(), "AlternativeSatisfied",
							f.number(1));
					AbstractBooleanTransformation transformation14 = new AssignBooleanTransformation(
							elm.getToRelation(), "ValidationSelected",
							f.number(1));
					AbstractBooleanTransformation transformation15 = new AndBooleanTransformation(
							transformation13, transformation14);
					transformations.add(new ImplicationBooleanTransformation(
							transformation12, transformation15));
					break;
				case means_ends:
				case implication:
					sourceAttributeNames.add("Satisfied");
					// A_Satisfied #==> B_ValidationSatisfied #= 1
					AbstractBooleanTransformation transformation16 = new AssignBooleanTransformation(
							elm.getToRelation(), "ValidationSatisfied",
							f.number(1));
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), "Satisfied", true,
							transformation16));
					// No break to include the following transformation
				case implementation:
					// B_Selected #==> A_ValidationSelected #= 1
					AbstractBooleanTransformation transformation18 = new AssignBooleanTransformation(
							elm.getFromRelation(), "ValidationSelected",
							f.number(1));
					transformations.add(new ImplicationBooleanTransformation(
							elm.getToRelation(), "Selected", true,
							transformation18));
					break;
				case mandatory:
					sourceAttributeNames.add("Selected");
					// A_Selected #==> B_ValidationSelected #=1
					AbstractBooleanTransformation transformation19 = new AssignBooleanTransformation(
							elm.getToRelation(), "ValidationSelected",
							f.number(1));
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), "Selected", true,
							transformation19));
					// B_Selected #==> A_ValidationSelected #=1
					AbstractBooleanTransformation transformation20 = new AssignBooleanTransformation(
							elm.getFromRelation(), "ValidationSelected",
							f.number(1));
					transformations.add(new ImplicationBooleanTransformation(
							elm.getToRelation(), "Selected", true,
							transformation20));
					break;
				case optional:
					sourceAttributeNames.add("Selected");
					// A_Selected #>= B_Selected
					transformations
							.add(new GreaterOrEqualsBooleanTransformation(elm
									.getFromRelation(), elm.getToRelation(),
									"Selected", "Selected"));

					// B_Optional #= 1
					transformations.add(new AssignBooleanTransformation(elm
							.getToRelation(), "Optional", f.number(1)));

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
					break;
				case group:
					break;
				case none:
					break;
				}
				InstVertex instVertex = elm.getFromRelation();
				if (instVertex instanceof InstGroupDependency) {
					((InstGroupDependency) instVertex)
							.clearSourceAttributeNames();
					((InstGroupDependency) instVertex)
							.addSourceAttributeNames(sourceAttributeNames);
				}
				for (AbstractBooleanTransformation transformation : transformations) {
					idMap.putAll(transformation.getIndentifiers(f));
					prog.add(transformation.transform(f, idMap));
				}

			}

		}
		return prog;
	}

	private HlclProgram groupExpressions(Refas refas,
			Map<String, Identifier> idMap) {
		HlclProgram prog = new HlclProgram();
		for (InstGroupDependency elm : refas
				.getInstGroupDependenciesCollection()) {
			MetaGroupDependency metaGroupDep = elm.getMetaGroupDependency();
			if (metaGroupDep != null
					&& elm.getInstAttribute(SemanticGroupDependency.VAR_CARDINALITYTYPE) != null) {
				CardinalityType relationType = CardinalityType
						.valueOf(((String) elm.getInstAttribute(
								SemanticGroupDependency.VAR_CARDINALITYTYPE)
								.getValue()).trim());
				//System.out.println(relationType);
				List<AbstractTransformation> transformations = new ArrayList<AbstractTransformation>();

				for (String sourceName : elm.getSourceAttributeNames()) {
					AbstractTransformation abstractTransformation = null;

					List<InstEdge> md = elm.getSourceRelations();
					Iterator<InstEdge> instEdges1 = elm.getSourceRelations()
							.iterator();
					AbstractTransformation recursiveExpression1 = null;
					AbstractTransformation recursiveExpression2 = null;
					instEdges1.next(); // TODO eliminate duplicated edges from
										// collection and remove this line
					InstEdge left1 = instEdges1.next();
					switch (relationType) {

					case and:
						abstractTransformation = new AndBooleanTransformation();
						break;
					case or:
						abstractTransformation = new OrBooleanTransformation();
						break;
					case mutex:
						abstractTransformation = new SumNumericTransformation();

						break;
					case range:
						abstractTransformation = new SumNumericTransformation();
						Iterator<InstEdge> instEdges2 = elm
								.getSourceRelations().iterator();
						instEdges2.next(); // TODO eliminate duplicated edges
											// from collection and remove this
											// line
						InstEdge left2 = instEdges2.next();
						Constructor<?> constructor3 = null,
						constructor4 = null;
						try {
							constructor3 = abstractTransformation.getClass()
									.getConstructor(InstVertex.class,
											String.class, Boolean.TYPE,
											AbstractTransformation.class);
							constructor4 = abstractTransformation.getClass()
									.getConstructor(InstVertex.class,
											InstVertex.class, String.class,
											String.class);
						} catch (NoSuchMethodException | SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						recursiveExpression2 = transformation(constructor3,
								constructor4, instEdges2, left2, sourceName);
						break;

					default:
						break;
					}

					Constructor<?> constructor1 = null, constructor2 = null;
					try {
						constructor1 = abstractTransformation.getClass()
								.getConstructor(InstVertex.class, String.class,
										Boolean.TYPE,
										AbstractTransformation.class);
						constructor2 = abstractTransformation.getClass()
								.getConstructor(InstVertex.class,
										InstVertex.class, String.class,
										String.class);
					} catch (NoSuchMethodException | SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					switch (relationType) {

					case and:
						//B_Satisfied #<=>  ( ( A1_"attribute" #/\ A2_"attribute" ) #/\ ... )
					case or:
						//B_Satisfied #<=>  ( ( A1_"attribute" #\/ A2_"attribute" ) #\/ ... )
						recursiveExpression1 = transformation(constructor1,
								constructor2, instEdges1, left1, sourceName);
						transformations
								.add(new EqualsComparisonTransformation(elm
										.getTargetRelations().get(0)
										.getToRelation(), sourceName, true,
										recursiveExpression1));
						break;
					case mutex:
						//B_Satisfied #<=>  (( ( A1_"attribute" + A2_"attribute" ) + ... ) #<=> 1)
						recursiveExpression1 = transformation(constructor1,
								constructor2, instEdges1, left1, sourceName);
						AbstractTransformation transformation1 = new EqualsComparisonTransformation(
								recursiveExpression1,
								new NumberNumericTransformation(1));
						transformations
						.add(new EqualsComparisonTransformation(
								elm.getTargetRelations().get(0).getToRelation(),
								sourceName, true, transformation1));

						break;
					case range:
						
						//B_Satisfied #<=>  ( ( ( ( A1_"attribute" + A2_"attribute" ) + ... ) #>= GD_LowCardinality) #/\
						//( ( ( A1_"attribute" + A2_"attribute" ) + ... ) #<= GD_HighCardinality ) )
						recursiveExpression1 = transformation(constructor1,
								constructor2, instEdges1, left1, sourceName);
						AbstractTransformation transformation3 = new GreaterOrEqualsBooleanTransformation(
								elm,
								"lowCardinality", false, recursiveExpression1);

						AbstractTransformation transformation4 = new GreaterOrEqualsBooleanTransformation(
								elm,
								"highCardinality", false, recursiveExpression2);

						AbstractTransformation transformation5  = new AndBooleanTransformation(
								transformation3, transformation4);

						transformations
						.add( new EqualsComparisonTransformation(
								elm.getTargetRelations().get(0).getToRelation(),
								sourceName, true, transformation5));

						break;

					default:
						break;
					}

				}
				for (AbstractTransformation transformation : transformations) {
					idMap.putAll(transformation.getIndentifiers(f));
					if (transformation instanceof AbstractBooleanTransformation)
						prog.add(((AbstractBooleanTransformation) transformation)
								.transform(f, idMap));
					else
						prog.add(((AbstractComparisonTransformation) transformation)
								.transform(f, idMap));
				}

			}

		}
		return prog;
	}

	private AbstractTransformation transformation(Constructor<?> constructor1,
			Constructor<?> constructor2, Iterator<InstEdge> instEdges,
			InstEdge left, String sourceName) {
		instEdges.next(); // TODO eliminate duplicated edges from collection and
							// remove this line
		InstEdge instEdge = instEdges.next();

		if (instEdges.hasNext()) {
			try {
				return (AbstractTransformation) constructor1.newInstance(
						left.getFromRelation(),
						sourceName,
						true,
						transformation(constructor1, constructor2, instEdges,
								instEdge, sourceName));
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} else
			try {
				return (AbstractTransformation) constructor2.newInstance(
						left.getFromRelation(), instEdge.getFromRelation(),
						sourceName, sourceName);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		return null;
	}
}
