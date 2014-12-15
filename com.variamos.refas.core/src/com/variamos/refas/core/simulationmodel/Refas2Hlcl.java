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
import com.variamos.refas.core.transformations.ImplicationBooleanTransformation;
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
					List<String> sourceAttributesList = new ArrayList<String>(), targetAttributesList = new ArrayList<String>(), constraintAttributesList = new ArrayList<String>();

					List<InstVertex> targetList = new ArrayList<InstVertex>();

					targetList.add(elm);

					for (InstAttribute instAttribute : instConcept
							.getInstAttributesCollection()) {
						if (instAttribute.getIdentifier().equals(
								"OriginallyAllowed")) {
							
							  int originallyAllowed =  ((boolean)instAttribute.getValue())?1:0; 
							  transformations .add(new
							  AssignBooleanTransformation(elm,"OriginallyAllowed",f.number(originallyAllowed)));					
							
							transformations.add(new ImplicationBooleanTransformation(elm,elm,"OriginallyAllowed","Allowed"));
									
							sourceAttributesList.add("OriginallyAllowed;");
							targetAttributesList.add("Allowed;");
						}
						if (instAttribute.getIdentifier().equals(
								"OriginallyRequired")) {
							Boolean originallyAllowed = (Boolean) instAttribute
									.getValue();
							transformations.add(new ImplicationBooleanTransformation(
									elm, elm,"OriginallyRequired","Required"));
						}
						if (instAttribute.getIdentifier().equals("Allowed")) {
							Boolean originallyAllowed = (Boolean) instAttribute
									.getValue();
							transformations.add(new ImplicationBooleanTransformation(
									elm,elm,"Allowed","Allowed"));
							// TODO fix transformation
							sourceAttributesList
									.add("Allowed;Required;Satisfied");
							targetAttributesList.add("Allowed;");
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
				List<String> sourceAttributesList = new ArrayList<String>(), targetAttributesList = new ArrayList<String>(), constraintAttributesList = new ArrayList<String>();

				List<InstVertex> sourcesList = new ArrayList<InstVertex>();
				List<InstVertex> targetList = new ArrayList<InstVertex>();

				sourcesList.add(elm.getFromRelation());
				targetList.add(elm.getToRelation());

				switch (relationType) {

				case preferred:
					// TODO
					break;
				case required:
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Required", "Satisfied"));
					// TODO fix transformation
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Required", "Allowed"));
					// TODO fix transformation
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Satisfied", "Satisfied"));
					// TODO fix transformation
					break;
				case conflict:
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Satisfied", "Satisfied"));
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Required", "Allowed"));
					// TODO fix transformation

					break;
				case alternative:
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Required", "Satisfied"));
					// TODO fix transformation
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Required", "Satisfied"));
					// TODO fix transformation

					sourceAttributesList.add("Satisfied;Required;");
					sourceAttributesList.add("Satisfied;Required;Required;");

					targetAttributesList.add("Satisfied;Required;");
					targetAttributesList.add("Satisfied;");
					break;
				case means_ends:
				case implication:
				case implementation:
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					break;
				case optional:
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Required", "Required"));
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Required", "Required"));
					// TODO fix transformation
					break;
				case mandatory:
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Satisfied", "Satisfied"));
					// TODO fix transformation

					break;
				case claim:
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					// TODO fix transformation

					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					// TODO fix transformation

					sourceAttributesList
							.add("Operationalizations;ComparativeExpression;");
					sourceAttributesList
							.add("Claim;Operationalizations;ComparativeExpression;");

					targetAttributesList.add("ValidationSatisfied;");
					targetAttributesList.add("");

					constraintAttributesList.add("Level");

					break;
				case softdependency:
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"ComparativeExpression", "Required"));
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
							"Satisfied", "ValidationSatisfied"));
					// TODO fix transformation

					sourceAttributesList.add("ComparativeExpression;");
					sourceAttributesList.add("ComparativeExpression;");

					targetAttributesList.add("Required;");
					targetAttributesList.add("");

					constraintAttributesList.add("Level");

					break;
				case generalConstraint:
					break;
				case group:
					transformations.add(new ImplicationBooleanTransformation(elm
							.getFromRelation(), elm.getToRelation(),
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
					//TODO attributes for GroupDependencies if needed
					/*
					transformation.get
					String sourceNames = sourceAttributesList.get(i);
					String targetNames = targetAttributesList.get(i);
					String constraintNames = null;
					if (constraintAttributesList.size() > i)
						constraintNames = constraintAttributesList.get(i);
					String[] sourceNamesArray = sourceNames.split(";");
					String[] targetNamesArray = targetNames.split(";");
					String[] constraintNamesArray = null;
					if (instVertex instanceof InstGroupDependency)
						((InstGroupDependency) instVertex)
								.addSourceAttributeNames(new HashSet<String>(
										Arrays.asList(sourceNamesArray)));

					if (constraintAttributesList.size() > i)
						constraintNamesArray = constraintNames.split(";");

					for (String name : sourceNamesArray) {
						if (name != null) {
							InstAttribute sourceInstAttribute = elm
									.getFromRelation().getInstAttribute(name);
							idMap.put(
									elm.getSourceInstAttributeIdentifier(name),
									f.newIdentifier(
											elm.getSourceInstAttributeIdentifier(name),
											sourceInstAttribute
													.getAttributeName()));
						}
					}
					for (String name : targetNamesArray) {
						InstAttribute tatgetInstAttribute = elm.getToRelation()
								.getInstAttribute(name);
						idMap.put(
								elm.getTargetInstAttributeIdentifier(name),
								f.newIdentifier(
										elm.getTargetInstAttributeIdentifier(name),
										tatgetInstAttribute.getAttributeName()));
					}
*/
					// TODO include constraint attributes

					// idMap.put(elm.getIdentifier(),
					// f.newIdentifier(elm.getName(), elm.getName()));
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
			//	List<String> sourceAttributesList = new ArrayList<String>(), targetAttributesList = new ArrayList<String>(), constraintAttributesList = new ArrayList<String>();

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
					transformations.add(booleanTransformation(abstractTransformation,
							instEdges, left, sourceName));
				}
				//int i = 0;
				for (AbstractBooleanTransformation transformation : transformations) {
					idMap.putAll(transformation.getIndentifiers(f));
					/*	String sourceNames = sourceAttributesList.get(i);
					String targetNames = targetAttributesList.get(i);
					String constraintNames = null;
					if (constraintAttributesList.size() > i)
						constraintNames = constraintAttributesList.get(i);
					String[] sourceNamesArray = sourceNames.split(";");
					String[] targetNamesArray = targetNames.split(";");
					String[] constraintNamesArray = null;

					if (constraintAttributesList.size() > i)
						constraintNamesArray = constraintNames.split(";");

					// TODO for all sources
					/*
					 * for (String name : sourceNamesArray) { if (name != null)
					 * { InstAttribute sourceInstAttribute = elm
					 * .getFromRelation().getInstAttribute(name); idMap.put(
					 * elm.getSourceInstAttributeIdentifier(name),
					 * f.newIdentifier(
					 * elm.getSourceInstAttributeIdentifier(name),
					 * sourceInstAttribute .getAttributeName())); } } for
					 * (String name : targetNamesArray) { InstAttribute
					 * tatgetInstAttribute = elm.getToRelation()
					 * .getInstAttribute(name); idMap.put(
					 * elm.getTargetInstAttributeIdentifier(name),
					 * f.newIdentifier(
					 * elm.getTargetInstAttributeIdentifier(name),
					 * tatgetInstAttribute.getAttributeName())); }
					 */
					// TODO include constraint attributes

					// idMap.put(elm.getIdentifier(),
					// f.newIdentifier(elm.getName(), elm.getName()));
					prog.add(transformation.transform(f, idMap));
				//	i++;
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
					sourceName, true, booleanTransformation(abstractTransformation,
							instEdges, instEdge, sourceName));
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
					sourceName, true, numericTransformation(abstractTransformation,
							instEdges, instEdge, sourceName));
		else
			return new SumNumericTransformation(left.getFromRelation(),
					instEdge.getFromRelation(), sourceName, sourceName);

	}
}
