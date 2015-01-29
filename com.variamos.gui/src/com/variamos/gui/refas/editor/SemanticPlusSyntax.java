package com.variamos.gui.refas.editor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.variamos.semantic.semanticsupport.AbstractSemanticVertex;
import com.variamos.semantic.semanticsupport.SemanticConcept;
import com.variamos.semantic.semanticsupport.SemanticContextGroup;
import com.variamos.semantic.semanticsupport.SemanticOverTwoRelation;
import com.variamos.semantic.semanticsupport.SemanticPairwiseRelation;
import com.variamos.semantic.semanticsupport.SemanticRelationType;
import com.variamos.semantic.semanticsupport.SemanticVariable;
import com.variamos.semantic.semanticsupport.SoftSemanticConcept;
import com.variamos.semantic.semanticsupport.SoftSemanticConceptSatisficing;
import com.variamos.semantic.types.DirectEdgeType;
import com.variamos.semantic.types.GroupRelationType;
import com.variamos.syntax.metamodelsupport.MetaConcept;
import com.variamos.syntax.metamodelsupport.MetaElement;
import com.variamos.syntax.metamodelsupport.MetaEnumeration;
import com.variamos.syntax.metamodelsupport.MetaOverTwoRelation;
import com.variamos.syntax.metamodelsupport.MetaPairwiseRelation;
import com.variamos.syntax.metamodelsupport.MetaView;
import com.variamos.syntax.metamodelsupport.SemanticAttribute;
import com.variamos.syntax.metamodelsupport.SimulationConfigAttribute;
import com.variamos.syntax.metamodelsupport.SimulationStateAttribute;
import com.variamos.syntax.semanticinterface.IntSemanticElement;
import com.variamos.syntax.semanticinterface.IntSemanticPairwiseRelType;
import com.variamos.syntax.semanticinterface.IntSemanticPairwiseRelation;
import com.variamos.syntax.semanticinterface.IntSemanticRelationType;

/**
 * A class to create semantic and syntax instances for vertex and edges of the
 * models. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-20
 */
public class SemanticPlusSyntax {
	private Map<String, IntSemanticElement> semanticConcepts = new HashMap<String, IntSemanticElement>();
	private List<MetaView> metaViews = new ArrayList<MetaView>();
	private Map<String, MetaElement> syntaxElements = new HashMap<String, MetaElement>();

	public Map<String, IntSemanticElement> getSemanticConcepts() {
		return semanticConcepts;
	}

	public Map<String, MetaElement> getSyntaxElements() {
		return syntaxElements;
	}

	public MetaElement getSyntaxElement(String name) {
		return syntaxElements.get(name);
	}

	public void setSyntaxElements(Map<String, MetaElement> syntaxElements) {
		this.syntaxElements = syntaxElements;
	}

	public IntSemanticElement getSemanticElement(
			String abstractSemanticConceptIdentifier) {
		return semanticConcepts.get(abstractSemanticConceptIdentifier);
	}

	public boolean elementsValidation(String element, int modelViewInd,
			int modelViewSubInd) {
		if (modelViewInd < metaViews.size() && modelViewSubInd == -1) {
			Iterator<MetaElement> metaConcept = metaViews.get(modelViewInd)
					.getElements().iterator();
			for (int i = 0; i < metaViews.get(modelViewInd).getElements()
					.size(); i++) {

				if (metaConcept.next().getIdentifier().equals(element))
					return true;
			}
		}
		if (modelViewInd < metaViews.size()
				&& modelViewSubInd != -1
				&& modelViewSubInd < metaViews.get(modelViewInd)
						.getChildViews().size()) {
			Iterator<MetaElement> metaElements = metaViews.get(modelViewInd)
					.getChildViews().get(modelViewSubInd).getElements()
					.iterator();
			while (metaElements.hasNext())
				if (metaElements.next().getIdentifier().equals(element))
					return true;
		}
		return false;
	}

	public List<String> modelElements(int modelViewInd, int modelViewSubInd) {
		List<String> elements = new ArrayList<String>();
		if (modelViewInd < metaViews.size() && modelViewSubInd == -1) {
			Iterator<MetaElement> metaElements = metaViews.get(modelViewInd)
					.getElements().iterator();
			while (metaElements.hasNext()) {
				MetaElement tmp = metaElements.next();
				if (tmp.getVisible())
					elements.add(tmp.getIdentifier());
			}
		}
		if (modelViewInd < metaViews.size()
				&& modelViewSubInd != -1
				&& modelViewSubInd < metaViews.get(modelViewInd)
						.getChildViews().size()) {
			Iterator<MetaElement> metaElements = metaViews.get(modelViewInd)
					.getChildViews().get(modelViewSubInd).getElements()
					.iterator();
			while (metaElements.hasNext()) {
				MetaElement tmp = metaElements.next();
				if (tmp.getVisible())
					elements.add(tmp.getIdentifier());
			}
		}
		return elements;
	}

	public List<MetaView> getMetaViews() {
		return metaViews;
	}

	public SemanticPlusSyntax() {

		// Definition of variability concept and relations

		System.out.println("Loading semantic model...");

		SemanticConcept semGeneralElement = new SemanticConcept();
		semanticConcepts.put("GE", semGeneralElement);

		// Design attributes

		semGeneralElement.putSemanticAttribute("Description",
				new SemanticAttribute("Description", "String", false,
						"Description", ""));
		semGeneralElement.addPropEditableAttribute("04#" + "Description");
		semGeneralElement.addPropVisibleAttribute("04#" + "Description");

		// Configuration attributes

		semGeneralElement.putSemanticAttribute("Active",
				new SimulationConfigAttribute("Active", "Boolean", true,
						"Is Active", true));
		semGeneralElement.putSemanticAttribute("Visibility",
				new SimulationConfigAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		semGeneralElement.putSemanticAttribute("Required",
				new SimulationConfigAttribute("Required", "Boolean", true,
						"Is Required", false));
		semGeneralElement.putSemanticAttribute("Allowed",
				new SimulationConfigAttribute("Allowed", "Boolean", true,
						"Is Allowed", true));
		semGeneralElement.putSemanticAttribute("RequiredLevel",
				new SimulationConfigAttribute("RequiredLevel", "Integer",
						false, "Required Level", 0)); // TODO define domain or
														// Enum
														// Level

		semGeneralElement.putSemanticAttribute("ForcedSatisfied",
				new SimulationConfigAttribute("ForcedSatisfied", "Boolean",
						false, "Force Satisfaction", false));
		semGeneralElement.putSemanticAttribute("ForcedSelected",
				new SimulationConfigAttribute("ForcedSelected", "Boolean",
						false, "Force Selection", false));

		semGeneralElement.addPropEditableAttribute("01#" + "Active");
		// semGeneralElement.addDisPropEditableAttribute("02#" + "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("03#" + "Allowed" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("04#" + "Required" + "#"
				+ "Allowed" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true" + "#" + "0");
		semGeneralElement.addPropEditableAttribute("10#" + "ForcedSatisfied"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("15#" + "ForcedSelected"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");

		semGeneralElement.addPropVisibleAttribute("01#" + "Active");
		semGeneralElement.addPropVisibleAttribute("02#" + "Visibility");
		semGeneralElement.addPropVisibleAttribute("03#" + "Allowed");
		semGeneralElement.addPropVisibleAttribute("04#" + "Required");
		semGeneralElement.addPropVisibleAttribute("05#" + "RequiredLevel" + "#"
				+ "Required" + "#==#" + "true");
		semGeneralElement.addPropVisibleAttribute("10#" + "ForcedSatisfied");
		semGeneralElement.addPropVisibleAttribute("15#" + "ForcedSelected");

		// Simulation attributes

		semGeneralElement.putSemanticAttribute("InitialRequiredLevel",
				new SimulationStateAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		semGeneralElement.putSemanticAttribute("SimRequiredLevel",
				new SimulationStateAttribute("SimRequiredLevel", "Integer",
						false, "Required Level", false));
		semGeneralElement
				.putSemanticAttribute("ValidationRequiredLevel",
						new SimulationStateAttribute("ValidationRequiredLevel",
								"Integer", false,
								"Required Level by Validation", false));
		semGeneralElement.putSemanticAttribute("SimRequired",
				new SimulationStateAttribute("SimRequired", "Boolean", false,
						"***Required***", false));

		semGeneralElement.putSemanticAttribute("Satisfied",
				new SimulationStateAttribute("Satisfied", "Boolean", false,
						"***Satisfied***", false));
		semGeneralElement.putSemanticAttribute("AlternativeSatisfied",
				new SimulationStateAttribute("AlternativeSatisfied", "Boolean",
						false, "Satisfied by Alternatives", false));
		semGeneralElement.putSemanticAttribute("ValidationSatisfied",
				new SimulationStateAttribute("ValidationSatisfied", "Boolean",
						false, "Satisfied by Validation", false));
		semGeneralElement.putSemanticAttribute("SatisfiedLevel",
				new SimulationStateAttribute("SatisfiedLevel", "Integer",
						false, "Satisficing Level", false));
		semGeneralElement.putSemanticAttribute("SatisfactionConflict",
				new SimulationStateAttribute("SatisfactionConflict", "Boolean",
						false, "Satisfaction Conflict", false));

		semGeneralElement.putSemanticAttribute("Selected",
				new SimulationStateAttribute("Selected", "Boolean", false,
						"***Selected***", false));
		semGeneralElement.putSemanticAttribute("NotPrefSelected",
				new SimulationStateAttribute("NotPrefSelected", "Boolean",
						false, "Not Preferred Selected", false));
		semGeneralElement.putSemanticAttribute("ValidationSelected",
				new SimulationStateAttribute("ValidationSelected", "Boolean",
						false, "Selected by Validation", false));
		semGeneralElement.putSemanticAttribute("SolverSelected",
				new SimulationStateAttribute("SolverSelected", "Boolean",
						false, "Selected by Solver", false));

		semGeneralElement.putSemanticAttribute("Optional",
				new SimulationStateAttribute("Optional", "Boolean", false,
						"*Is Optional*", false));

		semGeneralElement.putSemanticAttribute("SimAllowed",
				new SimulationStateAttribute("SimAllowed", "Boolean", false,
						"Is Allowed", true));

		semGeneralElement.addPropVisibleAttribute("01#" + "SimRequired");
		semGeneralElement.addPropVisibleAttribute("03#" + "SimRequiredLevel");
		semGeneralElement.addPropVisibleAttribute("05#"
				+ "InitialRequiredLevel");
		semGeneralElement.addPropVisibleAttribute("07#"
				+ "ValidationRequiredLevel");

		semGeneralElement.addPropVisibleAttribute("09#" + "Selected");
		semGeneralElement.addPropVisibleAttribute("11#" + "NotPrefSelected");
		semGeneralElement.addPropVisibleAttribute("13#" + "ValidationSelected");
		semGeneralElement.addPropVisibleAttribute("15#" + "SolverSelected");

		semGeneralElement.addPropVisibleAttribute("02#" + "Satisfied");
		semGeneralElement.addPropVisibleAttribute("04#"
				+ "AlternativeSatisfied");
		semGeneralElement
				.addPropVisibleAttribute("06#" + "ValidationSatisfied");
		semGeneralElement.addPropVisibleAttribute("08#" + "SatisfiedLevel");
		semGeneralElement.addPropVisibleAttribute("10#"
				+ "SatisfactionConflict");

		semGeneralElement.addPropVisibleAttribute("12#" + "SimAllowed");

		semGeneralElement.addPropVisibleAttribute("14#" + "Optional");

		// Definition of variability concept and relations
		SemanticConcept semHardConcept = new SemanticConcept(semGeneralElement,
				"semHardConcept");
		semanticConcepts.put("HC", semHardConcept);

		// Direct Relations of the semanticAssumption
		/*
		 * dirRelation = new DirectRelation(semVariabilityElement,
		 * DirectRelationType.preferred);
		 * semVariabilityElement.addDirectRelation(dirRelation); dirRelation =
		 * new DirectRelation(semVariabilityElement,
		 * DirectRelationType.required);
		 * semVariabilityElement.addDirectRelation(dirRelation); dirRelation =
		 * new DirectRelation(semVariabilityElement,
		 * DirectRelationType.conflict);
		 * semVariabilityElement.addDirectRelation(dirRelation); dirRelation =
		 * new DirectRelation(semVariabilityElement,
		 * DirectRelationType.alternative);
		 * semVariabilityElement.addDirectRelation(dirRelation); dirRelation =
		 * new DirectRelation(semVariabilityElement, DirectRelationType.mutex);
		 * semVariabilityElement.addDirectRelation(dirRelation);
		 */

		// Feature concepts

		SemanticConcept semFeature = new SemanticConcept(semGeneralElement,
				"Feature");
		semanticConcepts.put("F", semFeature);

		SemanticContextGroup semFeatureGroup = new SemanticContextGroup(
				"ContextGroup");
		semanticConcepts.put("FCG", semFeatureGroup);

		// definition of other concepts

		SemanticConcept semAssumption = new SemanticConcept(semHardConcept,
				"Assumption");
		semanticConcepts.put("Asset", semAssumption);

		SemanticConcept semGoal = new SemanticConcept(semHardConcept, "Goal");
		semGoal.addPanelVisibleAttribute("01#" + "satisfactionType");
		semGoal.addPanelSpacersAttribute("<#" + "satisfactionType" + "#>\n");
		semanticConcepts.put("G", semGoal);

		SemanticConcept semOperationalization = new SemanticConcept(
				semHardConcept, "Operationalization");
		semanticConcepts.put("OPER", semOperationalization);

		SoftSemanticConcept semSoftgoal = new SoftSemanticConcept(
				semGeneralElement, "SoftGoal");
		semanticConcepts.put("SG", semSoftgoal);

		SemanticVariable semVariable = new SemanticVariable("Variable");
		semanticConcepts.put("VAR", semVariable);

		SemanticContextGroup semContextGroup = new SemanticContextGroup(
				"ContextGroup");
		semanticConcepts.put("CG", semContextGroup);

		SemanticConcept semAsset = new SemanticConcept(semGeneralElement,
				"Asset");
		semanticConcepts.put("Asset", semAsset);

		SoftSemanticConceptSatisficing semClaim = new SoftSemanticConceptSatisficing(
				semGeneralElement, "Claim", true);
		semanticConcepts.put("CL", semClaim);
		semClaim.putSemanticAttribute("Operationalizations",
				new SemanticAttribute("Operationalizations", "MClass", false,
						"Operationalizations",
						"com.variamos.syntaxsupport.metamodel.InstConcept",
						"OPER", "", ""));
		semClaim.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression", "String", false,
						"Conditional Expression", ""));
		semClaim.putSemanticAttribute("CompExp", new SimulationConfigAttribute(
				"CompExp", "Boolean", false, "Boolean Comp. Expression", true));
		semClaim.putSemanticAttribute("ClaimSelected",
				new SimulationConfigAttribute("ClaimSelected", "Boolean",
						false, "Claim Selected", false));

		semClaim.addPanelVisibleAttribute("01#" + "Operationalizations");
		semClaim.addPanelVisibleAttribute("03#" + "ConditionalExpression"); // TODO
																			// move
																			// to
																			// semantic
																			// attributes

		semClaim.addPropEditableAttribute("01#" + "Operationalizations");
		semClaim.addPropEditableAttribute("03#" + "ConditionalExpression");

		semClaim.addPropVisibleAttribute("01#" + "Operationalizations");
		semClaim.addPropVisibleAttribute("03#" + "ConditionalExpression");

		semClaim.addPropEditableAttribute("01#" + "CompExp");
		semClaim.addPropVisibleAttribute("01#" + "CompExp");

		semClaim.addPropVisibleAttribute("02#" + "ClaimSelected");

		semClaim.addPanelSpacersAttribute("#" + "Operationalizations" + "#\n#");

		SoftSemanticConceptSatisficing semSoftDependency = new SoftSemanticConceptSatisficing(
				semGeneralElement, "SoftDependency", false);
		semanticConcepts.put("SD", semSoftDependency);

		semSoftDependency.putSemanticAttribute("CompExp",
				new SimulationConfigAttribute("CompExp", "Boolean", false,
						"Boolean Comp. Expression", true));
		semSoftDependency.putSemanticAttribute("SDSelected",
				new SimulationConfigAttribute("SDSelected", "Boolean", false,
						"SD Selected", false));

		semSoftDependency.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression", "String", false,
						"Conditional Expression", ""));
		semSoftDependency.addPanelVisibleAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropEditableAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropVisibleAttribute("03#"
				+ "ConditionalExpression");

		semSoftDependency.addPropEditableAttribute("01#" + "CompExp");
		semSoftDependency.addPropVisibleAttribute("01#" + "CompExp");

		semSoftDependency.addPropVisibleAttribute("02#" + "SDSelected");

		// Elements Lists
		List<AbstractSemanticVertex> semAssumptionElements = new ArrayList<AbstractSemanticVertex>();
		semAssumptionElements.add(semAssumption);

		List<AbstractSemanticVertex> SemGoalOperElements = new ArrayList<AbstractSemanticVertex>();
		SemGoalOperElements.add(semGoal);
		SemGoalOperElements.add(semOperationalization);

		List<AbstractSemanticVertex> semGoalElements = new ArrayList<AbstractSemanticVertex>();
		semGoalElements.add(semGoal);

		List<AbstractSemanticVertex> semOperationalizationElements = new ArrayList<AbstractSemanticVertex>();
		semOperationalizationElements.add(semOperationalization);

		List<AbstractSemanticVertex> semSoftgoalElements = new ArrayList<AbstractSemanticVertex>();
		semSoftgoalElements.add(semSoftgoal);

		List<AbstractSemanticVertex> semClaimsElements = new ArrayList<AbstractSemanticVertex>();
		semClaimsElements.add(semClaim);

		List<AbstractSemanticVertex> semSDElements = new ArrayList<AbstractSemanticVertex>();
		semSDElements.add(semSoftDependency);

		// Relations

		// features relations
		List<GroupRelationType> featureMeansGroupRelation = new ArrayList<GroupRelationType>();
		featureMeansGroupRelation.add(GroupRelationType.means_ends);

		List<IntSemanticPairwiseRelType> FeatureDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		FeatureDirectRelation.add(DirectEdgeType.mandatory);
		FeatureDirectRelation.add(DirectEdgeType.optional);
		FeatureDirectRelation.add(DirectEdgeType.conflict);
		FeatureDirectRelation.add(DirectEdgeType.required);

		// goal relations
		List<GroupRelationType> alternativeGroupRelation = new ArrayList<GroupRelationType>();
		alternativeGroupRelation.add(GroupRelationType.alternative);

		List<GroupRelationType> altern_impl_meansGroupRelation = new ArrayList<GroupRelationType>();
		altern_impl_meansGroupRelation.add(GroupRelationType.alternative);
		altern_impl_meansGroupRelation.add(GroupRelationType.means_ends);
		altern_impl_meansGroupRelation.add(GroupRelationType.implication);

		List<IntSemanticPairwiseRelType> alternative_prefferedDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		alternative_prefferedDirectRelation.add(DirectEdgeType.alternative);
		alternative_prefferedDirectRelation.add(DirectEdgeType.preferred);

		List<IntSemanticPairwiseRelType> alter_preff_impl_meansDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.alternative);
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.preferred);
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.implication);
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.means_ends);

		List<IntSemanticPairwiseRelType> allSGDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		allSGDirectRelation.add(DirectEdgeType.alternative);
		allSGDirectRelation.add(DirectEdgeType.preferred);
		allSGDirectRelation.add(DirectEdgeType.implication);
		allSGDirectRelation.add(DirectEdgeType.means_ends);
		allSGDirectRelation.add(DirectEdgeType.conflict);
		allSGDirectRelation.add(DirectEdgeType.required);

		List<GroupRelationType> allSGGroupRelation = new ArrayList<GroupRelationType>();
		allSGGroupRelation.add(GroupRelationType.means_ends);
		allSGGroupRelation.add(GroupRelationType.implication);
		allSGGroupRelation.add(GroupRelationType.alternative);
		allSGGroupRelation.add(GroupRelationType.conflict);
		allSGGroupRelation.add(GroupRelationType.required);

		List<GroupRelationType> means_endsImplicationGroupRelation = new ArrayList<GroupRelationType>();
		means_endsImplicationGroupRelation.add(GroupRelationType.means_ends);
		means_endsImplicationGroupRelation.add(GroupRelationType.implication);

		List<GroupRelationType> implicationGroupRelation = new ArrayList<GroupRelationType>();
		implicationGroupRelation.add(GroupRelationType.implication);

		List<IntSemanticPairwiseRelType> implicationDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		implicationDirectRelation.add(DirectEdgeType.implication);

		List<IntSemanticPairwiseRelType> means_endsImplicationDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		means_endsImplicationDirectRelation.add(DirectEdgeType.means_ends);
		means_endsImplicationDirectRelation.add(DirectEdgeType.implication);

		List<IntSemanticPairwiseRelType> softdepDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		softdepDirectRelation.add(DirectEdgeType.softdependency);

		List<IntSemanticPairwiseRelType> noneDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		noneDirectRelation.add(DirectEdgeType.none);

		List<IntSemanticPairwiseRelType> claimDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		claimDirectRelation.add(DirectEdgeType.claim);

		List<GroupRelationType> implementationGroupRelation = new ArrayList<GroupRelationType>();
		implementationGroupRelation.add(GroupRelationType.implementation);

		List<IntSemanticPairwiseRelType> implementationDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		implementationDirectRelation.add(DirectEdgeType.implementation);

		// required and conflict group relations of the HardSemanticConcept
		List<GroupRelationType> requires_conflictsGroupRelation = new ArrayList<GroupRelationType>();
		requires_conflictsGroupRelation.add(GroupRelationType.required);
		requires_conflictsGroupRelation.add(GroupRelationType.conflict);

		SemanticOverTwoRelation semanticHardHardGroupRelation = new SemanticOverTwoRelation(
				"HardHardOverTwoRel", false, null, null);
		semanticConcepts.put("HardHardOverTwoRel",
				semanticHardHardGroupRelation);

		// required and conflict direct relations of the HardSemanticConcept
		List<IntSemanticPairwiseRelType> requires_conflictsDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		requires_conflictsDirectRelation.add(DirectEdgeType.required);
		requires_conflictsDirectRelation.add(DirectEdgeType.conflict);

		List<AbstractSemanticVertex> semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semHardConcept);

		List<IntSemanticRelationType> hardSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		hardSemPairwiseRelList.add(new SemanticRelationType("MeansEnds",
				"Means Ends", "means-ends", true, true, true, 1, -1, 1, 1));
		hardSemPairwiseRelList.add(new SemanticRelationType("Conflict",
				"Conflict", "conflict", false, true, true, 1, -1, 1, 1));
		hardSemPairwiseRelList.add(new SemanticRelationType("Alternative",
				"Alternative", "altern.", false, true, true, 1, -1, 1, 1));
		hardSemPairwiseRelList.add(new SemanticRelationType("Excludes",
				"Excludes", "excludes", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation directHardHardSemanticEdge = new SemanticPairwiseRelation(
				"HardHardDirectEdge", false, hardSemPairwiseRelList);
		semHardConcept.addDirectRelation(directHardHardSemanticEdge);
		semanticConcepts.put("HardHardDirectEdge", directHardHardSemanticEdge);

		// Feature to Feature

		SemanticOverTwoRelation semanticFeatureFeatureGroupRelation = new SemanticOverTwoRelation(
				"FeatureFeatureGroupRel", false, null, null);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semFeature);

		SemanticPairwiseRelation directFeatureFeatureSemanticEdge = new SemanticPairwiseRelation(
				"FeatureFeatureDirectEdge", false, hardSemPairwiseRelList);
		semGoal.addDirectRelation(directFeatureFeatureSemanticEdge);
		semanticConcepts.put("FeauteFeateuGroupRel",
				semanticFeatureFeatureGroupRelation);
		semanticConcepts.put("FeauteFeatureDirectEdge",
				directFeatureFeatureSemanticEdge);

		// Goal to Goal

		SemanticOverTwoRelation semanticGoalGoalGroupRelation = new SemanticOverTwoRelation(
				"GoalGoalOverTwoRel", false, null, null);
		semanticVertexs.add(semGoal);

		SemanticPairwiseRelation directGoalGoalSemanticEdge = new SemanticPairwiseRelation(
				"GoalGoalDirectEdge", false, hardSemPairwiseRelList);
		semGoal.addDirectRelation(directGoalGoalSemanticEdge);
		semanticConcepts.put("GoalGoalOverTwoRel",
				semanticGoalGoalGroupRelation);
		semanticConcepts.put("GoalGoalDirectEdge", directGoalGoalSemanticEdge);

		// Oper to Goal and Oper
		SemanticOverTwoRelation semanticOperGoalGroupRelation = new SemanticOverTwoRelation(
				"OperGoalOverTwoRel", false, null, null);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semOperationalization);
		semanticVertexs.add(semGoal);

		SemanticPairwiseRelation directOperGoalSemanticEdge = new SemanticPairwiseRelation(
				"OperGoalDirectEdge", false, hardSemPairwiseRelList);

		semOperationalization.addDirectRelation(directOperGoalSemanticEdge);
		semanticConcepts.put("OperGoalOverTwoRel",
				semanticOperGoalGroupRelation);
		semanticConcepts.put("OperGoalDirectEdge", directOperGoalSemanticEdge);

		// Oper to Oper
		SemanticOverTwoRelation semanticOperOperGroupRelation = new SemanticOverTwoRelation(
				"OperOperOverTwoRel", false, null, null);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semOperationalization);

		SemanticPairwiseRelation directOperOperSemanticEdge = new SemanticPairwiseRelation(
				"OperOperDirectEdge", false, hardSemPairwiseRelList);
		semOperationalization.addDirectRelation(directOperOperSemanticEdge);
		semanticConcepts.put("OperOperOverTwoRel",
				semanticOperOperGroupRelation);
		semanticConcepts.put("OperOperDirectEdge", directOperOperSemanticEdge);

		// SG to SG
		SemanticOverTwoRelation semanticSGSGGroupRelation = new SemanticOverTwoRelation(
				"SGSGGroupRel", false, null, null);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semSoftgoal);

		SemanticPairwiseRelation directSGSGSemEdge = new SemanticPairwiseRelation(
				"SGSGDirectEdge", true, hardSemPairwiseRelList);
		/*
		 * directSGSGSemEdge.putSemanticAttribute(AbstractSemanticEdge.VAR_LEVEL,
		 * new SemanticAttribute(AbstractSemanticEdge.VAR_LEVEL, "Enumeration",
		 * false, AbstractSemanticEdge.VAR_LEVELNAME,
		 * AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		 * directSGSGSemEdge.addPropEditableAttribute("08#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 * directSGSGSemEdge.addPropVisibleAttribute("08#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 * directSGSGSemEdge.addPanelVisibleAttribute("08#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 */
		semSoftgoal.addDirectRelation(directSGSGSemEdge);
		semanticConcepts.put("SGSGGroupRel", semanticSGSGGroupRelation);
		semanticConcepts.put("SGSGDirectEdge", directSGSGSemEdge);

		// CV to CG
		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semContextGroup);

		SemanticPairwiseRelation directCVCGSemanticEdge = new SemanticPairwiseRelation(
				"CVCGDirectRel", false, hardSemPairwiseRelList);
		semVariable.addDirectRelation(directCVCGSemanticEdge);
		semanticConcepts.put("CVCGDirectRel", directCVCGSemanticEdge);

		// Oper to Claim
		SemanticOverTwoRelation semanticOperClaimGroupRelation = new SemanticOverTwoRelation(
				"OperClaimGroupRel", true, null, null);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semClaim);

		SemanticPairwiseRelation directOperClaimSemanticEdge = new SemanticPairwiseRelation(
				"OperClaimDirectEdge", true, hardSemPairwiseRelList);
		semOperationalization.addDirectRelation(directOperClaimSemanticEdge);
		/*
		 * directOperClaimSemanticEdge.putSemanticAttribute(
		 * AbstractSemanticEdge.VAR_LEVEL, new SemanticAttribute(
		 * AbstractSemanticEdge.VAR_LEVEL, "Enumeration", false,
		 * AbstractSemanticEdge.VAR_LEVEL, AbstractSemanticEdge.VAR_LEVELCLASS,
		 * "plus plus", ""));
		 * directOperClaimSemanticEdge.addPropEditableAttribute("08#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 * directOperClaimSemanticEdge.addPropVisibleAttribute("08#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 * directOperClaimSemanticEdge.addPanelVisibleAttribute("08#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 */
		semClaim.addDirectRelation(directOperClaimSemanticEdge);
		semanticConcepts.put("OperClaimGroupRel",
				semanticOperClaimGroupRelation);
		semanticConcepts
				.put("OperClaimDirectEdge", directOperClaimSemanticEdge);

		// Claim to SG

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semSoftgoal);

		SemanticPairwiseRelation directClaimSGSemanticEdge = new SemanticPairwiseRelation(
				"ClaimSGDirectEdge", true, hardSemPairwiseRelList);
		/*
		 * directClaimSGSemanticEdge.putSemanticAttribute(
		 * AbstractSemanticEdge.VAR_LEVEL, new SemanticAttribute(
		 * AbstractSemanticEdge.VAR_LEVEL, "Enumeration", false,
		 * AbstractSemanticEdge.VAR_LEVEL, AbstractSemanticEdge.VAR_LEVELCLASS,
		 * "plus plus", ""));
		 * directClaimSGSemanticEdge.addPropEditableAttribute("08#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 * directClaimSGSemanticEdge.addPropVisibleAttribute("08#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 * directClaimSGSemanticEdge.addPanelVisibleAttribute("08#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 */
		semClaim.addDirectRelation(directClaimSGSemanticEdge);
		semanticConcepts.put("ClaimSGDirectEdge", directClaimSGSemanticEdge);

		// SD to SG

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semSoftgoal);

		SemanticPairwiseRelation directSDSGSemanticEdge = new SemanticPairwiseRelation(
				"SDSGDirectEdge", true, hardSemPairwiseRelList);
		/*
		 * directSDSGSemanticEdge.putSemanticAttribute(
		 * AbstractSemanticEdge.VAR_LEVEL, new SemanticAttribute(
		 * AbstractSemanticEdge.VAR_LEVEL, "Enumeration", false,
		 * AbstractSemanticEdge.VAR_LEVELNAME,
		 * AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		 * directSDSGSemanticEdge.addPropEditableAttribute("04#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 * directSDSGSemanticEdge.addPropVisibleAttribute("04#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 * directSDSGSemanticEdge.addPanelVisibleAttribute("04#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 */
		semSoftDependency.addDirectRelation(directSDSGSemanticEdge);
		semanticConcepts.put("SDSGDirectEdge", directSDSGSemanticEdge);

		// Asset to Oper
		SemanticOverTwoRelation semanticAssetOperGroupRelation = new SemanticOverTwoRelation(
				"AssetOperGroupRel", false, null, null);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semOperationalization);

		SemanticPairwiseRelation directAssetOperSemanticEdge = new SemanticPairwiseRelation(
				"AssetOperDirectEdge", false, hardSemPairwiseRelList);
		semAsset.addDirectRelation(directAssetOperSemanticEdge);
		semanticConcepts.put("AssetOperGroupRel",
				semanticAssetOperGroupRelation);
		semanticConcepts
				.put("AssetOperDirectEdge", directAssetOperSemanticEdge);

		// TODO: structural and functional dependency relations

		System.out.println("Semantic model loaded.");
		// *************************---------------****************************
		// Our MetaModel objects definition

		System.out.println("Loading syntax meta model...");

		MetaView syntaxMetaView = null;

		// *************************---------------****************************
		// Goals and avariability model

		syntaxMetaView = new MetaView("GoalsAndVaribilityModel",
				"Goals and Variability Model", "Goals and Variability Palette",
				1);

		MetaConcept syntaxFeature = new MetaConcept("F", true, "Feature",
				"plnode", "Defines a feature", 100, 40,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, true, semFeature);
		syntaxFeature.addModelingAttribute("name", "String", false, "Name", "");

		syntaxElements.put("F", syntaxFeature);
		syntaxMetaView.addConcept(syntaxFeature);

		syntaxFeature.addPanelVisibleAttribute("03#" + "name");

		syntaxFeature.addPropEditableAttribute("03#" + "name");

		syntaxFeature.addPropVisibleAttribute("03#" + "name");

		// Feature direct relations

		List<IntSemanticPairwiseRelation> directFeatureSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directFeatureSemanticEdges.add(directFeatureFeatureSemanticEdge);

		MetaPairwiseRelation metaFeatureEdge = new MetaPairwiseRelation(
				"Feature Relation", false, "Feature Relation", "plnode",
				"Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				syntaxFeature, syntaxFeature, directFeatureFeatureSemanticEdge);
		syntaxFeature
				.addMetaPairwiseRelAsOrigin(syntaxFeature, metaFeatureEdge);
		syntaxElements.put("Feature Relation", metaFeatureEdge);
		syntaxMetaView.addConcept(metaFeatureEdge);

		// Group Feature Relations

		MetaOverTwoRelation syntaxFeatureGroupDep = new MetaOverTwoRelation(
				"FeatGroupDep", true, "FeatGroupDep", "plgroup",
				"Group relation between"
						+ " Feature concepts. Defines different types of"
						+ " cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticFeatureFeatureGroupRelation);

		syntaxMetaView.addConcept(syntaxFeatureGroupDep);
		syntaxElements.put("FeatGroupDep", syntaxFeatureGroupDep);

		syntaxFeatureGroupDep.addModelingAttribute("Active",
				new SimulationConfigAttribute("Active", "Boolean", true,
						"Is Active", true));
		syntaxFeatureGroupDep.addModelingAttribute("Visibility",
				new SimulationConfigAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		syntaxFeatureGroupDep.addModelingAttribute("Required",
				new SimulationConfigAttribute("Required", "Boolean", true,
						"Is Required", false));
		syntaxFeatureGroupDep.addModelingAttribute("Allowed",
				new SimulationConfigAttribute("Allowed", "Boolean", false,
						"Is Allowed", true));
		syntaxFeatureGroupDep.addModelingAttribute("RequiredLevel",
				new SimulationConfigAttribute("RequiredLevel", "Integer",
						false, "Required Level", 0)); // TODO define domain or
														// Enum
														// Level

		syntaxFeatureGroupDep.addModelingAttribute("ForcedSatisfied",
				new SimulationConfigAttribute("ForcedSatisfied", "Boolean",
						false, "Force Satisfaction", false));
		syntaxFeatureGroupDep.addModelingAttribute("ForcedSelected",
				new SimulationConfigAttribute("ForcedSelected", "Boolean",
						false, "Force Selection", false));

		syntaxFeatureGroupDep.addPropEditableAttribute("01#" + "Active");
		// syntaxFeatureGroupDep.addDisPropEditableAttribute("02#" +
		// "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		syntaxFeatureGroupDep.addPropEditableAttribute("03#" + "Allowed" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		syntaxFeatureGroupDep.addPropEditableAttribute("04#" + "Required" + "#"
				+ "Allowed" + "#==#" + "true" + "#" + "false");
		syntaxFeatureGroupDep.addPropEditableAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true" + "#" + "0");
		syntaxFeatureGroupDep.addPropEditableAttribute("10#"
				+ "ForcedSatisfied" + "#" + "Allowed" + "#==#" + "true" + "#"
				+ "false");
		syntaxFeatureGroupDep.addPropEditableAttribute("15#" + "ForcedSelected"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");

		syntaxFeatureGroupDep.addPropVisibleAttribute("01#" + "Active");
		syntaxFeatureGroupDep.addPropVisibleAttribute("02#" + "Visibility");
		syntaxFeatureGroupDep.addPropVisibleAttribute("03#" + "Allowed");
		syntaxFeatureGroupDep.addPropVisibleAttribute("04#" + "Required");
		syntaxFeatureGroupDep.addPropVisibleAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true");
		syntaxFeatureGroupDep
				.addPropVisibleAttribute("10#" + "ForcedSatisfied");
		syntaxFeatureGroupDep.addPropVisibleAttribute("15#" + "ForcedSelected");

		// Simulation attributes

		syntaxFeatureGroupDep.addModelingAttribute("InitialRequiredLevel",
				new SimulationStateAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		syntaxFeatureGroupDep.addModelingAttribute("SimRequiredLevel",
				new SimulationStateAttribute("SimRequiredLevel", "Integer",
						false, "Required Level", false));
		syntaxFeatureGroupDep
				.addModelingAttribute("ValidationRequiredLevel",
						new SimulationStateAttribute("ValidationRequiredLevel",
								"Integer", false,
								"Required Level by Validation", false));
		syntaxFeatureGroupDep.addModelingAttribute("SimRequired",
				new SimulationStateAttribute("SimRequired", "Boolean", false,
						"Required", false));

		syntaxFeatureGroupDep.addModelingAttribute("Satisfied",
				new SimulationStateAttribute("Satisfied", "Boolean", false,
						"Satisfied", false));
		syntaxFeatureGroupDep.addModelingAttribute("AlternativeSatisfied",
				new SimulationStateAttribute("AlternativeSatisfied", "Boolean",
						false, "Satisfied by Alternatives", false));
		syntaxFeatureGroupDep.addModelingAttribute("ValidationSatisfied",
				new SimulationStateAttribute("ValidationSatisfied", "Boolean",
						false, "Satisfied by Validation", false));
		syntaxFeatureGroupDep.addModelingAttribute("SatisfiedLevel",
				new SimulationStateAttribute("SatisfiedLevel", "Integer",
						false, "Satisficing Level", false));
		syntaxFeatureGroupDep.addModelingAttribute("SatisfactionConflict",
				new SimulationStateAttribute("SatisfactionConflict", "Boolean",
						false, "Satisfaction Conflict", false));

		syntaxFeatureGroupDep.addModelingAttribute("Selected",
				new SimulationStateAttribute("Selected", "Boolean", false,
						"Selected", false));
		syntaxFeatureGroupDep.addModelingAttribute("NotPrefSelected",
				new SimulationStateAttribute("NotPrefSelected", "Boolean",
						false, "Select by Preferred", false));
		syntaxFeatureGroupDep.addModelingAttribute("ValidationSelected",
				new SimulationStateAttribute("ValidationSelected", "Boolean",
						false, "Selected by Validation", false));
		syntaxFeatureGroupDep.addModelingAttribute("SolverSelected",
				new SimulationStateAttribute("SolverSelected", "Boolean",
						false, "Selected by Solver", false));

		syntaxFeatureGroupDep.addModelingAttribute("Optional",
				new SimulationStateAttribute("Optional", "Boolean", false,
						"Is Optional", false));

		syntaxFeatureGroupDep.addModelingAttribute("SimAllowed",
				new SimulationStateAttribute("SimAllowed", "Boolean", false,
						"Is Allowed", true));

		syntaxFeatureGroupDep.addPropVisibleAttribute("01#" + "SimRequired");
		syntaxFeatureGroupDep.addPropVisibleAttribute("03#"
				+ "SimRequiredLevel");
		syntaxFeatureGroupDep.addPropVisibleAttribute("05#"
				+ "InitialRequiredLevel");
		syntaxFeatureGroupDep.addPropVisibleAttribute("07#"
				+ "ValidationRequiredLevel");
		syntaxFeatureGroupDep.addPropVisibleAttribute("09#" + "Selected");
		syntaxFeatureGroupDep
				.addPropVisibleAttribute("11#" + "NotPrefSelected");
		syntaxFeatureGroupDep.addPropVisibleAttribute("13#"
				+ "ValidationSelected");
		syntaxFeatureGroupDep.addPropVisibleAttribute("15#" + "SolverSelected");

		syntaxFeatureGroupDep.addPropVisibleAttribute("02#" + "Satisfied");
		syntaxFeatureGroupDep.addPropVisibleAttribute("04#"
				+ "AlternativeSatisfied");
		syntaxFeatureGroupDep.addPropVisibleAttribute("06#"
				+ "ValidationSatisfied");
		syntaxFeatureGroupDep.addPropVisibleAttribute("08#" + "SatisfiedLevel");
		syntaxFeatureGroupDep.addPropVisibleAttribute("10#"
				+ "SatisfactionConflict");

		syntaxFeatureGroupDep.addPropVisibleAttribute("12#" + "SimAllowed");

		syntaxFeatureGroupDep.addPropVisibleAttribute("14#" + "Optional");

		MetaConcept syntaxVariabilityArtifact = new MetaConcept("VA", false,
				"VariabilityArtifact", null, "", 0, 0, null, true, null, 3,
				true, semHardConcept);
		syntaxVariabilityArtifact.addModelingAttribute("name", "String", false,
				"Name", "");

		syntaxVariabilityArtifact.addPanelVisibleAttribute("03#" + "name");

		syntaxVariabilityArtifact.addPropEditableAttribute("03#" + "name");

		syntaxVariabilityArtifact.addPropVisibleAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxVariabilityArtifact);
		syntaxElements.put("VA", syntaxVariabilityArtifact);

		metaViews.add(syntaxMetaView);

		MetaConcept syntaxTopGoal = new MetaConcept("TopGoal", true,
				"Top Goal", "refasgoal", "Defines a top goal of the system"
						+ " from the stakeholder perspective that can be"
						+ " satisfied with a clear cut condition", 100, 40,
				"/com/variamos/gui/refas/editor/images/goal.png", true,
				Color.BLUE.toString(), 3, true, semGoal);
		syntaxTopGoal.setParent(syntaxVariabilityArtifact);

		syntaxMetaView.addConcept(syntaxTopGoal);
		syntaxElements.put("TopGoal", syntaxTopGoal);

		MetaConcept syntaxGeneralGoal = new MetaConcept("GeneralGoal", true,
				"General Goal", "refasgoal", "Defines a general goal of the"
						+ " system from the stakeholder perspective that can"
						+ " be satisfied with a clear cut condition", 100, 40,
				"/com/variamos/gui/refas/editor/images/goal.png", true,
				Color.BLUE.toString(), 2, true, semGoal);
		syntaxGeneralGoal.setParent(syntaxVariabilityArtifact);

		syntaxMetaView.addConcept(syntaxGeneralGoal);
		syntaxElements.put("GeneralGoal", syntaxGeneralGoal);

		MetaConcept sOperationalization = new MetaConcept("OPER", true,
				"Operationalization", "refasoper",
				"An operationalization allows"
						+ " the partial or complete satisfaction of a goal or"
						+ " another operationalization. If"
						+ " the operationalizations defined is satisfied,"
						+ " according to the defined relation, the goal"
						+ " associated will be also satisfied", 100, 40,
				"/com/variamos/gui/refas/editor/images/operational.png", true,
				Color.BLUE.toString(), 2, true, semOperationalization);
		sOperationalization.setParent(syntaxVariabilityArtifact);

		syntaxMetaView.addConcept(sOperationalization);
		syntaxElements.put("OPER", sOperationalization);

		MetaConcept syntaxAssumption = new MetaConcept("Assumption", true,
				"semanticAssumption", "refassassump", "An assumption is a"
						+ " condition that should me truth for the goal or"
						+ " operationalization to be satisfied", 100, 40,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.WHITE.toString(), 1, true, semAssumption);
		syntaxAssumption.setParent(syntaxVariabilityArtifact);

		syntaxMetaView.addConcept(syntaxAssumption);
		syntaxElements.put("Assumption", syntaxAssumption);

		// Direct Hard Relations

		MetaPairwiseRelation metaHardEdge = new MetaPairwiseRelation(
				"HardRelation", true, "HardRelation", "ploptional",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cartinalities", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxVariabilityArtifact, syntaxVariabilityArtifact,
				directHardHardSemanticEdge);
		syntaxVariabilityArtifact.addMetaPairwiseRelAsOrigin(
				syntaxVariabilityArtifact, metaHardEdge);
		syntaxElements.put("HardRelation", metaHardEdge);

		// Group Hard Relations

		MetaOverTwoRelation syntaxGroupDependency = new MetaOverTwoRelation(
				"HardOverTwoRel", true, "HardOverTwoRel", "plgroup",
				"Group relation between"
						+ " hard concepts. Defines different types of"
						+ " relations and cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticHardHardGroupRelation);

		syntaxMetaView.addConcept(syntaxGroupDependency);
		syntaxElements.put("HardOverTwoRel", syntaxGroupDependency);

		syntaxGroupDependency.addModelingAttribute("Active",
				new SimulationConfigAttribute("Active", "Boolean", true,
						"Is Active", true));
		syntaxGroupDependency.addModelingAttribute("Visibility",
				new SimulationConfigAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		syntaxGroupDependency.addModelingAttribute("Required",
				new SimulationConfigAttribute("Required", "Boolean", true,
						"Is Required", false));
		syntaxGroupDependency.addModelingAttribute("Allowed",
				new SimulationConfigAttribute("Allowed", "Boolean", false,
						"Is Allowed", true));
		syntaxGroupDependency.addModelingAttribute("RequiredLevel",
				new SimulationConfigAttribute("RequiredLevel", "Integer",
						false, "Required Level", 0)); // TODO define domain or
														// Enum
														// Level
		syntaxGroupDependency.addModelingAttribute("ForcedSatisfied",
				new SimulationConfigAttribute("ForcedSatisfied", "Boolean",
						false, "Force Satisfaction", false));
		syntaxGroupDependency.addModelingAttribute("ForcedSelected",
				new SimulationConfigAttribute("ForcedSelected", "Boolean",
						false, "Force Selection", false));

		syntaxGroupDependency.addPropEditableAttribute("01#" + "Active");
		// syntaxGroupDependency.addDisPropEditableAttribute("02#" +
		// "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		syntaxGroupDependency.addPropEditableAttribute("03#" + "Allowed" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		syntaxGroupDependency.addPropEditableAttribute("04#" + "Required" + "#"
				+ "Allowed" + "#==#" + "true" + "#" + "false");
		syntaxGroupDependency.addPropEditableAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true" + "#" + "0");
		syntaxGroupDependency.addPropEditableAttribute("10#"
				+ "ForcedSatisfied" + "#" + "Allowed" + "#==#" + "true" + "#"
				+ "false");
		syntaxGroupDependency.addPropEditableAttribute("15#" + "ForcedSelected"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");

		syntaxGroupDependency.addPropVisibleAttribute("01#" + "Active");
		syntaxGroupDependency.addPropVisibleAttribute("02#" + "Visibility");
		syntaxGroupDependency.addPropVisibleAttribute("03#" + "Allowed");
		syntaxGroupDependency.addPropVisibleAttribute("04#" + "Required");
		syntaxGroupDependency.addPropVisibleAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true");
		syntaxGroupDependency
				.addPropVisibleAttribute("10#" + "ForcedSatisfied");
		syntaxGroupDependency.addPropVisibleAttribute("15#" + "ForcedSelected");

		// Simulation attributes

		syntaxGroupDependency.addModelingAttribute("InitialRequiredLevel",
				new SimulationStateAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		syntaxGroupDependency.addModelingAttribute("SimRequiredLevel",
				new SimulationStateAttribute("SimRequiredLevel", "Integer",
						false, "Required Level", false));
		syntaxGroupDependency
				.addModelingAttribute("ValidationRequiredLevel",
						new SimulationStateAttribute("ValidationRequiredLevel",
								"Integer", false,
								"Required Level by Validation", false));
		syntaxGroupDependency.addModelingAttribute("SimRequired",
				new SimulationStateAttribute("SimRequired", "Boolean", false,
						"***Required***", false));

		syntaxGroupDependency.addModelingAttribute("Satisfied",
				new SimulationStateAttribute("Satisfied", "Boolean", false,
						"***Satisfied***", false));
		syntaxGroupDependency.addModelingAttribute("AlternativeSatisfied",
				new SimulationStateAttribute("AlternativeSatisfied", "Boolean",
						false, "Satisfied by Alternatives", false));
		syntaxGroupDependency.addModelingAttribute("ValidationSatisfied",
				new SimulationStateAttribute("ValidationSatisfied", "Boolean",
						false, "Satisfied by Validation", false));
		syntaxGroupDependency.addModelingAttribute("SatisfiedLevel",
				new SimulationStateAttribute("SatisfiedLevel", "Integer",
						false, "Satisficing Level", false));
		syntaxGroupDependency.addModelingAttribute("SatisfactionConflict",
				new SimulationStateAttribute("SatisfactionConflict", "Boolean",
						false, "Satisfaction Conflict", false));

		syntaxGroupDependency.addModelingAttribute("Selected",
				new SimulationStateAttribute("Selected", "Boolean", false,
						"***Selected***", false));
		syntaxGroupDependency.addModelingAttribute("NotPrefSelected",
				new SimulationStateAttribute("NotPrefSelected", "Boolean",
						false, "Not Preferred Selected", false));
		syntaxGroupDependency.addModelingAttribute("ValidationSelected",
				new SimulationStateAttribute("ValidationSelected", "Boolean",
						false, "Selected by Validation", false));
		syntaxGroupDependency.addModelingAttribute("SolverSelected",
				new SimulationStateAttribute("SolverSelected", "Boolean",
						false, "Selected by Solver", false));

		syntaxGroupDependency.addModelingAttribute("Optional",
				new SimulationStateAttribute("Optional", "Boolean", false,
						"*Is Optional*", false));

		syntaxGroupDependency.addModelingAttribute("SimAllowed",
				new SimulationStateAttribute("SimAllowed", "Boolean", false,
						"Is Allowed", true));

		syntaxGroupDependency.addPropVisibleAttribute("01#" + "SimRequired");
		syntaxGroupDependency.addPropVisibleAttribute("03#"
				+ "SimRequiredLevel");
		syntaxGroupDependency.addPropVisibleAttribute("05#"
				+ "InitialRequiredLevel");
		syntaxGroupDependency.addPropVisibleAttribute("07#"
				+ "ValidationRequiredLevel");
		syntaxGroupDependency.addPropVisibleAttribute("09#" + "Selected");
		syntaxGroupDependency
				.addPropVisibleAttribute("11#" + "NotPrefSelected");
		syntaxGroupDependency.addPropVisibleAttribute("13#"
				+ "ValidationSelected");
		syntaxGroupDependency.addPropVisibleAttribute("15#" + "SolverSelected");

		syntaxGroupDependency.addPropVisibleAttribute("02#" + "Satisfied");
		syntaxGroupDependency.addPropVisibleAttribute("04#"
				+ "AlternativeSatisfied");
		syntaxGroupDependency.addPropVisibleAttribute("06#"
				+ "ValidationSatisfied");
		syntaxGroupDependency.addPropVisibleAttribute("08#" + "SatisfiedLevel");
		syntaxGroupDependency.addPropVisibleAttribute("10#"
				+ "SatisfactionConflict");

		syntaxGroupDependency.addPropVisibleAttribute("12#" + "SimAllowed");

		syntaxGroupDependency.addPropVisibleAttribute("14#" + "Optional");

		// *************************---------------****************************
		// Softgoals model

		syntaxMetaView = new MetaView("SoftGoals", "Soft Goals Model",
				"Soft Goals Palette", 2);
		metaViews.add(syntaxMetaView);

		MetaConcept syntaxAbsSoftGoal = new MetaConcept("Softgoal", false,
				"Softgoal", "", null, 0, 0, null, true, null, 3, true,
				semSoftgoal);

		syntaxAbsSoftGoal.addModelingAttribute("name", "String", false, "Name",
				"");
		syntaxAbsSoftGoal.addPanelVisibleAttribute("03#" + "name");

		syntaxAbsSoftGoal.addPropEditableAttribute("03#" + "name");
		syntaxAbsSoftGoal.addPropVisibleAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxAbsSoftGoal);
		syntaxElements.put("Softgoal", syntaxAbsSoftGoal);

		MetaConcept syntaxTopSoftGoal = new MetaConcept(
				"TopSoftgoal",
				true,
				"Top Softgoal",
				"refassoftgoal",
				"Defines a top goal of the"
						+ " system from the stakeholder"
						+ " perspective that can at most be satisficed without"
						+ " a clear cut verification. Given the satisficing problem,"
						+ " it includes an scale of levels of satisfaction/denial."
						+ " The SG satisficing level can be measured globally or"
						+ " locally, for the system or for each user, depending"
						+ " on the SG", 100, 40,
				"/com/variamos/gui/refas/editor/images/softgoal.png", true,
				Color.WHITE.toString(), 3, true, semSoftgoal);

		syntaxTopSoftGoal.setParent(syntaxAbsSoftGoal);

		syntaxMetaView.addConcept(syntaxTopSoftGoal);
		syntaxElements.put("TopSoftgoal", syntaxTopSoftGoal);

		MetaConcept syntaxGeneralSoftGoal = new MetaConcept(
				"GeneralSSoftgoal",
				true,
				"General Softgoal",
				"refassoftgoal",
				"Defines a general"
						+ " softgoal of the system from the stakeholder"
						+ " perspective that can at most be satisficed without"
						+ " a clear cut verification. Given the satisficing problem,"
						+ " it includes an scale of levels of satisfaction/denial."
						+ " The SG satisficing level can be measured globally or"
						+ " locally, for the system or for each user, depending"
						+ " on the SG", 100, 40,
				"/com/variamos/gui/refas/editor/images/softgoal.png", true,
				Color.WHITE.toString(), 1, true, semSoftgoal);

		syntaxGeneralSoftGoal.setParent(syntaxAbsSoftGoal);
		syntaxMetaView.addConcept(syntaxGeneralSoftGoal);
		syntaxElements.put("GeneralSSoftgoal", syntaxGeneralSoftGoal);

		// Direct Soft relation

		MetaPairwiseRelation metaSoftEdge = new MetaPairwiseRelation(
				"Soft Relation", true, "Soft Relation", "ploptional",
				"Direct relation between two soft concepts. Defines"
						+ " different types of relations and cartinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAbsSoftGoal, syntaxAbsSoftGoal, directSGSGSemEdge);
		syntaxAbsSoftGoal.addMetaPairwiseRelAsOrigin(syntaxAbsSoftGoal,
				metaSoftEdge);
		syntaxElements.put("Soft Relation", metaSoftEdge);

		// Group soft relation

		syntaxGroupDependency = new MetaOverTwoRelation("SoftgoalGroupDep",
				true, "SoftgoalGroupDep", "plgroup",
				"Direct relation between soft"
						+ " concepts. Defines different types of relations"
						+ " and cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticSGSGGroupRelation);

		syntaxMetaView.addConcept(syntaxGroupDependency);
		syntaxElements.put("SoftgoalGroupDep", syntaxGroupDependency);

		// *************************---------------****************************
		// Context model

		syntaxMetaView = new MetaView("Context", "Context Model",
				"Context Palette", 3);
		metaViews.add(syntaxMetaView);
		// syntaxMetaView.addConcept(syntaxVariable);

		MetaConcept syntaxContextGroup = new MetaConcept("CG", true,
				"Context Group", "refascontextgrp", " A context group"
						+ " is defined to associate variables with common"
						+ " characteristics. The type defines if variables"
						+ " are sensed or profiled, in the first case they"
						+ " are modifie by the system or environment; in the"
						+ " second case they are defined by the administrator"
						+ " or the user. The scope can be local or global,"
						+ " the first means the value is independently for"
						+ " each user while global variables are shared"
						+ " between all the users.", 150, 40,
				"/com/variamos/gui/refas/editor/images/contextgrp.png", true,
				Color.BLUE.toString(), 1, true, semContextGroup);

		MetaView syntaxMetaChildView = new MetaView("Context",
				"Context with Enumerations", "Context Palette", 0);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		syntaxMetaChildView.addConcept(syntaxContextGroup);
		syntaxMetaView.addConcept(syntaxContextGroup);
		syntaxElements.put("CG", syntaxContextGroup);

		MetaConcept syntaxAbsVariable = new MetaConcept("Variable", false,
				"Variable", "", null, 0, 0, null, true, null, 1, true,
				semVariable);

		syntaxElements.put("Variable", syntaxAbsVariable);

		MetaConcept syntaxGlobalVariable = new MetaConcept(
				"GlobalVariable",
				true,
				"Global Variable",
				"refasglobcnxt",
				"A global variable"
						+ " is an abstraction of a variable or component of the"
						+ " system or the environment that are relevant the system."
						+ " The relevance applies to the system in general."
						+ " The variable values should be"
						+ " simplified as much as possible considering the modeling"
						+ " requirements", 150, 40,
				"/com/variamos/gui/refas/editor/images/globCnxtVar.png", true,
				Color.BLUE.toString(), 1, true, semVariable);

		syntaxGlobalVariable.setParent(syntaxAbsVariable);

		syntaxMetaChildView.addConcept(syntaxGlobalVariable);
		syntaxMetaView.addConcept(syntaxGlobalVariable);
		syntaxElements.put("GlobalVariable", syntaxGlobalVariable);

		MetaConcept syntaxLocalVariable = new MetaConcept(
				"LocalVariable",
				true,
				"Local Variable",
				"refaslocalcnxt",
				" A local variable"
						+ " represents an instance of a component or a variable"
						+ " with local scope. This variables may have different"
						+ " values for each user of the system. Local variables"
						+ " are used mainly for SG with local satisfaction"
						+ " evaluation", 100, 40,
				"/com/variamos/gui/refas/editor/images/localCnxtVar.png", true,
				Color.BLUE.toString(), 1, true, semVariable);

		syntaxLocalVariable.setParent(syntaxAbsVariable);

		syntaxMetaChildView.addConcept(syntaxLocalVariable);
		syntaxMetaView.addConcept(syntaxLocalVariable);
		syntaxElements.put("LocalVariable", syntaxLocalVariable);

		MetaEnumeration metaEnumeration = new MetaEnumeration("ME", true,
				"MetaEnumeration", "refasenumeration", "Allows the"
						+ " creation of user defined enumeration for"
						+ " variables", 100, 150,
				"/com/variamos/gui/refas/editor/images/assump.png", true, "",
				1, true);
		syntaxMetaChildView.addConcept(metaEnumeration);
		syntaxElements.put("ME", metaEnumeration);
		syntaxMetaView.addConcept(metaEnumeration);

		syntaxMetaChildView = new MetaView("ContandModelEnum",
				"Context without Enumerations", "Context Palette", 1);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		// syntaxMetaChildView.addConcept(metaEnumeration);
		syntaxMetaChildView.addConcept(syntaxContextGroup);
		syntaxMetaChildView.addConcept(syntaxLocalVariable);
		syntaxMetaChildView.addConcept(syntaxGlobalVariable);

		// Direct variable relations

		MetaPairwiseRelation metaVariableEdge = new MetaPairwiseRelation(
				"Variable To Context Relation", true,
				"Variable To Context Relation", "ploptional",
				"Associates a Variable" + " with the Context Group", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAbsVariable, syntaxContextGroup, directCVCGSemanticEdge);
		syntaxAbsVariable.addMetaPairwiseRelAsOrigin(syntaxContextGroup,
				metaVariableEdge);
		syntaxElements.put("Variable To Context Relation", metaVariableEdge);

		MetaPairwiseRelation metaContextEdge = new MetaPairwiseRelation(
				"Context To Context Relation", true,
				"Context To Context Relation", "ploptional", "Associates a"
						+ " Context Group with other Context Group", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxContextGroup, syntaxContextGroup, directCVCGSemanticEdge);
		syntaxContextGroup.addMetaPairwiseRelAsOrigin(syntaxContextGroup,
				metaContextEdge);
		syntaxElements.put("Context To Context Relation", metaVariableEdge);

		// *************************---------------****************************
		// SG Satisficing Model

		syntaxMetaView = new MetaView("SoftGoalsSatisficing",
				"SG Satisficing Model", "Soft Goals Satisficing Palette", 4);
		metaViews.add(syntaxMetaView);
		syntaxMetaView.addConcept(syntaxTopSoftGoal);
		syntaxMetaView.addConcept(syntaxGeneralSoftGoal);
		syntaxMetaView.addConcept(sOperationalization);

		MetaConcept syntaxClaim = new MetaConcept("CL", true, "Claim",
				"refasclaim", "A claim includes a group of"
						+ " operationalizations and a logical condition"
						+ " to evaluate the claim satisfaction."
						+ " The claim is activated only when all the"
						+ " operationalizations are selected and the"
						+ " conditional expression is true. The claim"
						+ " includes a relation with a softgoal (SG)", 100, 40,
				"/com/variamos/gui/refas/editor/images/claim.png", true,
				Color.BLUE.toString(), 1, true, semClaim);
		syntaxMetaView.addConcept(syntaxClaim);
		syntaxElements.put("CL", syntaxClaim);

		MetaConcept syntaxSoftDependency = new MetaConcept(
				"SD",
				true,
				"Soft Dependency",
				"refassoftdep",
				"A Soft Dependency"
						+ " (SD express a logical condition useful to express"
						+ " an expected level of"
						+ " satisfaction of a softgoal. The soft dependency is"
						+ " activated only when the conditional expression is true."
						+ " The SD includes a relation with a softgoal (SG)",
				100, 40, "/com/variamos/gui/refas/editor/images/softdep.png",
				true, Color.BLUE.toString(), 1, true, semSoftDependency);

		syntaxMetaView.addConcept(syntaxSoftDependency);
		syntaxElements.put("SD", syntaxSoftDependency);

		syntaxGroupDependency = new MetaOverTwoRelation(
				"OperClaimGD",
				true,
				"OperClaimGD",
				"plgroup",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " expected on the softgoal in case the Claim is satisfied",
				20, 20, "/com/variamos/gui/pl/editor/images/plgroup.png",
				false, "white", 1, false, semanticOperClaimGroupRelation);
		syntaxMetaView.addConcept(syntaxGroupDependency);
		syntaxElements.put("OperClaimGD", syntaxGroupDependency);

		List<IntSemanticPairwiseRelation> directSDSGSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directSDSGSemanticEdges.add(directSDSGSemanticEdge);

		MetaPairwiseRelation metaSDSGEdge = new MetaPairwiseRelation(
				"SDSGRelation",
				true,
				"SDSGRelation",
				"ploptional",
				"Express the relation between"
						+ " the SD and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxSoftDependency, syntaxAbsSoftGoal, directSDSGSemanticEdge);
		syntaxSoftDependency.addMetaPairwiseRelAsOrigin(syntaxAbsSoftGoal,
				metaSDSGEdge);

		syntaxElements.put("SDSGRelation", metaSDSGEdge);

		List<IntSemanticPairwiseRelation> directClaimSGSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directClaimSGSemanticEdges.add(directClaimSGSemanticEdge);

		MetaPairwiseRelation metaClaimSGEdge = new MetaPairwiseRelation(
				"Claim-Softgoal Relation",
				true,
				"Claim-Softgoal Relation",
				"ploptional",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxClaim, syntaxAbsSoftGoal, directClaimSGSemanticEdge);
		syntaxClaim.addMetaPairwiseRelAsOrigin(syntaxAbsSoftGoal,
				metaClaimSGEdge);

		syntaxElements.put("Claim-Softgoal Relation", metaClaimSGEdge);

		// *************************---------------****************************
		// Assets model

		syntaxMetaView = new MetaView("Assets", "Assets General Model",
				"Assets Palette", 5);
		metaViews.add(syntaxMetaView);
		syntaxMetaView.addConcept(sOperationalization);

		MetaConcept syntaxAsset = new MetaConcept("Asset", true, "Asset",
				"refasasset", "Represents a asset of the system. The most"
						+ " important assets to represent are those than"
						+ " can implement operationalizations", 100, 40,
				"/com/variamos/gui/refas/editor/images/component.png", true,
				Color.WHITE.toString(), 1, true, semAsset);
		syntaxAsset.addModelingAttribute("name", "String", false, "Name", ""); // TODO
																				// move
																				// to
		// semantic
		// attributes
		syntaxMetaChildView = new MetaView("Assets", "Assets General Model",
				"Assets Palette", 0);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		syntaxAsset.addPanelVisibleAttribute("03#" + "name");
		syntaxAsset.addPropEditableAttribute("03#" + "name");
		syntaxAsset.addPropVisibleAttribute("03#" + "name");
		syntaxMetaChildView.addConcept(syntaxAsset);
		syntaxMetaView.addConcept(syntaxAsset);

		syntaxMetaChildView.addConcept(sOperationalization);
		syntaxMetaView.addConcept(sOperationalization);
		syntaxElements.put("Asset", syntaxAsset);

		syntaxMetaChildView = new MetaView("FunctionalAssets",
				"Functionl Assets Relations", "Assets Palette", 1);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		syntaxMetaChildView.addConcept(sOperationalization);
		syntaxMetaChildView.addConcept(syntaxAsset);

		syntaxMetaChildView.addConcept(syntaxAsset);

		syntaxMetaChildView = new MetaView("StructuralAssets",
				"Structural Assets Relations", "Assets Palette", 2);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		syntaxMetaChildView.addConcept(sOperationalization);
		syntaxMetaChildView.addConcept(syntaxAsset);

		syntaxGroupDependency = new MetaOverTwoRelation("AssetOperGroupDep",
				true, "AssetOperGroupDep", "plgroup",
				"Represents the implementation "
						+ "of an operationalization by a group of assets", 20,
				20, "/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticAssetOperGroupRelation);

		syntaxMetaView.addConcept(syntaxGroupDependency);
		syntaxMetaChildView.addConcept(syntaxAsset);
		syntaxElements.put("Asset-Oper GroupDep", syntaxGroupDependency);

		List<IntSemanticPairwiseRelation> directAssetOperSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directAssetOperSemanticEdges.add(directAssetOperSemanticEdge);

		MetaPairwiseRelation metaOperEdge = new MetaPairwiseRelation(
				"Asset To Oper Relation", true, "Asset To Oper Relation",
				"ploptional", "Represents the "
						+ "implementation of an operationzalization by an"
						+ " asset", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAsset, sOperationalization, directAssetOperSemanticEdge);

		// syntaxMetaView.addConcept(metaOperEdge);
		syntaxAsset.addMetaPairwiseRelAsOrigin(sOperationalization,
				metaOperEdge);
		syntaxElements.put("Asset To Oper Relation", metaOperEdge);

		System.out.println("Syntax meta model loaded.");

		System.out.println("Loading gui elements...");

	}
}