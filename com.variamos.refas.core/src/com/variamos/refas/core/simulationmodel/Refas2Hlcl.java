package com.variamos.refas.core.simulationmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.Identifier;
import com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency;
import com.variamos.refas.core.staticconcepts.Refas;
import com.variamos.refas.core.transformations.AssignBooleanTransformation;
import com.variamos.refas.core.transformations.DiffNumericTransformation;
import com.variamos.refas.core.transformations.EqualsBooleanTransformation;
import com.variamos.refas.core.transformations.GreaterOrEqualsBooleanTransformation;
import com.variamos.refas.core.transformations.ImplicationBooleanTransformation;
import com.variamos.refas.core.transformations.NumberNumericTransformation;
import com.variamos.refas.core.transformations.ProdNumericTransformation;
import com.variamos.refas.core.transformations.SumNumericTransformation;
import com.variamos.refas.core.types.CardinalityType;
import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.refas.core.types.GroupRelationType;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;
import com.variamos.syntaxsupport.metametamodel.MetaDirectRelation;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metametamodel.MetaGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstVertex;

public class Refas2Hlcl {
	private HlclFactory f = new HlclFactory();

	public Refas2Hlcl(Refas refas) {
		HlclProgram prog = new HlclProgram();

		Map<String, Identifier> idMap = new HashMap<>();
		prog.addAll(vertexExpression(refas, idMap));
		prog.addAll(edgeExpression(refas, idMap));
		prog.addAll(groupExpression(refas, idMap));
		for (Expression exp : prog) {
			System.out.println(exp);
		}
	}

	private HlclProgram vertexExpression(Refas refas,
			Map<String, Identifier> idMap) {
		HlclProgram prog = new HlclProgram();
		for (InstVertex elm : refas.getVariabilityVertexCollection()) {
			if (elm instanceof InstConcept) { // TODO not required if the method
												// returns InstConcepts
				InstConcept instConcept = (InstConcept) elm;

				MetaConcept metaConcept = instConcept.getMetaConcept();
				if (metaConcept != null) {
					List<AbstractBooleanTransformation> transformations = new ArrayList<AbstractBooleanTransformation>();
					for (InstAttribute instAttribute : instConcept
							.getInstAttributesCollection()) {
						//A_SimAllowed  #= A_Allowed
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
						//A_SimRequired #= A_Required
						if (instAttribute.getIdentifier().equals("Required")) {
							int attributeVale = (Boolean) ((boolean) instAttribute
									.getValue()) ? 1 : 0;
							transformations
									.add(new AssignBooleanTransformation(elm,
											instAttribute.getIdentifier(), f
													.number(attributeVale)));
							transformations
									.add(new AssignBooleanTransformation(
											elm, elm, "SimRequired",
											instAttribute.getIdentifier()));
						}
						//A_PreferredSelected #= 1
						if (instAttribute.getIdentifier().equals(
								"PreferredSelected")) {
							int attributeVale = ((boolean) instAttribute
									.getValue()) ? 1 : 0;
							transformations
									.add(new AssignBooleanTransformation(elm,
											instAttribute.getIdentifier(), f
													.number(attributeVale)));
						}
						//A_Optional #= 0
						if (instAttribute.getIdentifier().equals("Optional")) {
							int attributeVale = ((boolean) instAttribute
									.getValue()) ? 1 : 0;
							transformations
									.add(new AssignBooleanTransformation(elm,
											instAttribute.getIdentifier(), f
													.number(attributeVale)));
						}
						//A_SimInitialRequiredLevel #= A_RequiredLevel
						if (instAttribute.getIdentifier().equals("RequiredLevel")) {
							int attributeVale = ((Integer)instAttribute.getValue()).intValue();
							transformations
									.add(new AssignBooleanTransformation(elm,
											instAttribute.getIdentifier(), f
													.number(attributeVale)));
							transformations
							.add(new AssignBooleanTransformation(
									elm, elm, "InitialRequiredLevel",
									instAttribute.getIdentifier()));
						}
						if (instAttribute.getIdentifier().equals("Satisfied")) {
							//(( 1 - A_SimRequired) + A_Satisfied) #>= 1
							AbstractNumericTransformation transformation1 = new DiffNumericTransformation(elm,
									"SimRequired", false, f.number(1));
							transformation1 = new SumNumericTransformation(elm,
									instAttribute.getIdentifier(), false, transformation1);
							
							transformations
							.add(new GreaterOrEqualsBooleanTransformation(
									transformation1, new NumberNumericTransformation(1)));
							
							//(( 1 - A_Selected) + A_Satisfied) #>= 1
							AbstractNumericTransformation transformation2 = new DiffNumericTransformation(elm,
									"Selected", false, f.number(1));
							transformation2 = new SumNumericTransformation(elm,
									instAttribute.getIdentifier(), false, transformation2);
							
							transformations
							.add(new GreaterOrEqualsBooleanTransformation(
									transformation2, new NumberNumericTransformation(1)));
							
							//(1 - A_SimAllowed) * (A_SimRequired + A_Satisfied)  #= 0
							AbstractNumericTransformation transformation3 = new DiffNumericTransformation(elm,
									"Selected", false, f.number(1));
							AbstractNumericTransformation transformation4 = new SumNumericTransformation(elm,elm,
									"SimRequired",instAttribute.getIdentifier() );
							transformation3 = new ProdNumericTransformation(transformation3, transformation4);
							
							transformations
							.add(new EqualsBooleanTransformation(
									transformation3, new NumberNumericTransformation(0)));
						}

					}
					for (AbstractBooleanTransformation transformation : transformations) {
						idMap.putAll(transformation.getIndentifiers(f));
						prog.add(transformation.transform(f, idMap));
					}

				}

			}

		}
		return prog;
	}

	private HlclProgram edgeExpression(Refas refas,
			Map<String, Identifier> idMap) {
		HlclProgram prog = new HlclProgram();
		for (InstEdge elm : refas.getConstraintInstEdgesCollection()) {
			MetaEdge metaEdge = elm.getMetaEdge();
			if (metaEdge != null
					&& elm.getInstAttribute(MetaDirectRelation.VAR_METADIRECTEDGETYPE) != null) {
				DirectEdgeType relationType = DirectEdgeType
						.valueOf(((String) elm.getInstAttribute(
								MetaDirectRelation.VAR_METADIRECTEDGETYPE)
								.getValue()).trim().replace(" ", "_"));

				List<AbstractBooleanTransformation> transformations = new ArrayList<AbstractBooleanTransformation>();

				switch (relationType) {

				case preferred:
					// TODO
					break;
				case required:
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Required", "Satisfied"));
					// TODO fix transformation
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Required", "Allowed"));
					// TODO fix transformation
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Satisfied", "Satisfied"));
					// TODO fix transformation
					break;
				case conflict:
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Satisfied", "Satisfied"));
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Required", "Allowed"));
					// TODO fix transformation

					break;
				case alternative:
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Required", "Satisfied"));
					// TODO fix transformation
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Required", "Satisfied"));
					// TODO fix transformation
					break;
				case means_ends:
				case implication:
				case implementation:
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					break;
				case optional:
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Required", "Required"));
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Required", "Required"));
					// TODO fix transformation
					break;
				case mandatory:
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Satisfied", "Satisfied"));
					// TODO fix transformation

					break;
				case claim:
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					// TODO fix transformation

					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					// TODO fix transformation
					break;
				case softdependency:
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"ComparativeExpression", "Required"));
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					// TODO fix transformation

					break;
				case generalConstraint:
					break;
				case group:
					transformations.add(new ImplicationBooleanTransformation(
							elm.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					// TODO fix transformation

					break;
				case none:
					break;

				default:
					break;
				}
				InstVertex instVertex = elm.getToRelation();
				if (instVertex instanceof InstGroupDependency)
					((InstGroupDependency) instVertex)
							.clearSourceAttributeNames();
				for (AbstractBooleanTransformation transformation : transformations) {
					idMap.putAll(transformation.getIndentifiers(f));
					prog.add(transformation.transform(f, idMap));
				}

			}

		}
		return prog;
	}

	private HlclProgram groupExpression(Refas refas,
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

				List<AbstractBooleanTransformation> transformations = new ArrayList<AbstractBooleanTransformation>();

				for (String sourceName : elm.getSourceAttributeNames()) {
					AbstractBooleanTransformation abstractTransformation = null;
					switch (relationType) {

					case and:
						abstractTransformation = new ImplicationBooleanTransformation();
						break;
					case or:
						abstractTransformation = new ImplicationBooleanTransformation();
						break;
					case range:
						abstractTransformation = new ImplicationBooleanTransformation();
						break;
					case mutex:
						abstractTransformation = new ImplicationBooleanTransformation();
						break;

					default:
						break;
					}
					Iterator<InstEdge> instEdges = elm.getSourceRelations()
							.values().iterator();
					InstEdge left = instEdges.next();
					transformations
							.add(booleanTransformation(abstractTransformation,
									instEdges, left, sourceName));
				}
				for (AbstractBooleanTransformation transformation : transformations) {
					idMap.putAll(transformation.getIndentifiers(f));			
					prog.add(transformation.transform(f, idMap));
				}

			}

		}
		return prog;
	}

	private AbstractBooleanTransformation booleanTransformation(
			AbstractBooleanTransformation abstractTransformation,
			Iterator<InstEdge> instEdges, InstEdge left, String sourceName) {
		InstEdge instEdge = instEdges.next();
		if (instEdges.hasNext())
			return new ImplicationBooleanTransformation(left.getFromRelation(),
					sourceName, true, booleanTransformation(
							abstractTransformation, instEdges, instEdge,
							sourceName));
		else
			return new ImplicationBooleanTransformation(left.getFromRelation(),
					instEdge.getFromRelation(), sourceName, sourceName);

	}

	private AbstractNumericTransformation numericTransformation(
			AbstractNumericTransformation abstractTransformation,
			Iterator<InstEdge> instEdges, InstEdge left, String sourceName) {
		InstEdge instEdge = instEdges.next();
		if (instEdges.hasNext())
			return new SumNumericTransformation(left.getFromRelation(),
					sourceName, true, numericTransformation(
							abstractTransformation, instEdges, instEdge,
							sourceName));
		else
			return new SumNumericTransformation(left.getFromRelation(),
					instEdge.getFromRelation(), sourceName, sourceName);

	}
}
