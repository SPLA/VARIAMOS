package com.variamos.refas.core.staticconcepts;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.variamos.refas.core.sematicsmetamodel.*;
import com.variamos.refas.core.types.DirectRelationType;
import com.variamos.refas.core.types.GroupRelationType;
import com.variamos.syntaxsupport.metametamodel.*;
import com.variamos.syntaxsupport.semanticinterface.IntDirectRelationType;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupDependency;

public class SemanticPlusSyntax {
	private Map<String, AbstractSemanticVertex> semanticConcepts = new HashMap<String, AbstractSemanticVertex>();
	private List<MetaView> metaViews = new ArrayList<MetaView>();
	private Map<String, MetaElement> syntaxElements = new HashMap<String, MetaElement>();

	public Map<String, AbstractSemanticVertex> getSemanticConcepts() {
		return semanticConcepts;
	}

	public Map<String, MetaElement> getSyntaxElements() {
		return syntaxElements;
	}

	public MetaElement getMetaElement(String name) {
		return syntaxElements.get(name);
	}

	public void setSyntaxElements(Map<String, MetaElement> syntaxElements) {
		this.syntaxElements = syntaxElements;
	}

	public AbstractSemanticVertex getSemanticElement(
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

		DirectSemanticEdge dirRelation = null;
		IncomingSemanticEdge groupRelation = null;

		AbstractSemanticVertex semGeneralElement = new AbstractSemanticVertex();
		semanticConcepts.put("GE", semGeneralElement);

		semGeneralElement.putSemanticAttribute("Description",
				new SemanticAttribute("Description", "String", ""));
		semGeneralElement.addDisPropEditableAttribute("04#" + "Description");

		semGeneralElement.putSemanticAttribute("Active",
				new SimulationAttribute("Active", "Boolean", true));
		semGeneralElement.putSemanticAttribute("Visibility",
				new SimulationAttribute("Visibility", "Boolean", true));
		semGeneralElement.putSemanticAttribute("InitiallySelected",
				new SimulationAttribute("InitiallySelected", "Boolean", false));
		semGeneralElement.putSemanticAttribute("Required",
				new SimulationAttribute("Required", "Boolean", false));
		semGeneralElement.putSemanticAttribute("CurrentlySelected",
				new SimulationAttribute("CurrentlySelected", "Boolean", false));
		semGeneralElement.addDisPropEditableAttribute("01#" + "Active");
		semGeneralElement.addDisPropEditableAttribute("02#" + "Visibility");
		semGeneralElement.addDisPropEditableAttribute("03#"
				+ "InitiallySelected");
		semGeneralElement.addDisPropEditableAttribute("04#" + "Required");
		semGeneralElement.addDisPropEditableAttribute("05#"
				+ "CurrentlySelected");

		// Definition of variability concept and relations
		HardSemanticConcept semHardConcept = new HardSemanticConcept(
				semGeneralElement, "semHardConcept");
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

		List<OutgoingSemanticEdge> semHardOutgoingRelation = new ArrayList<OutgoingSemanticEdge>();
		semHardOutgoingRelation
				.add(new OutgoingSemanticEdge(semHardConcept));

		// definition of other concepts
		HardSemanticConcept semAssumption = new HardSemanticConcept(
				semHardConcept, "Assumption");
		semanticConcepts.put("AS", semAssumption);

		HardSemanticConcept semGoal = new HardSemanticConcept(semHardConcept,
				"Goal");
		semGoal.addDisPanelVisibleAttribute("01#" + "satisfactionType");
		semGoal.addDisPanelSpacersAttribute("<#" + "satisfactionType" + "#>\n");
		semanticConcepts.put("G", semGoal);

		HardSemanticConcept semOperationalization = new HardSemanticConcept(
				semHardConcept, "Operationalization");
		semanticConcepts.put("OPER", semOperationalization);

		SoftSemanticConcept semSoftGoal = new SoftSemanticConcept(
				semGeneralElement, "SoftGoal");
		semanticConcepts.put("SG", semSoftGoal);

		SemanticVariable semVariable = new SemanticVariable("Variable");
		semanticConcepts.put("VAR", semVariable);

		HardSemanticConcept semAsset = new HardSemanticConcept(
				semGeneralElement, "Asset");
		semanticConcepts.put("ASSE", semAsset);

		SoftSemanticConceptSatisficing semClaim = new SoftSemanticConceptSatisficing(
				semGeneralElement, "Claim", true);
		semanticConcepts.put("CL", semClaim);
		semClaim.putSemanticAttribute("Operationalizations",
				new SemanticAttribute("Operationalizations", "MClass",
						"com.variamos.syntaxsupport.metamodel.InstConcept",
						"OPER", "", ""));
		semClaim.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression", "String", ""));

		semClaim.addDisPanelVisibleAttribute("01#" + "Operationalizations");
		semClaim.addDisPanelVisibleAttribute("03#" + "ConditionalExpression"); // TODO
																				// move
																				// to
																				// semantic
																				// attributes

		semClaim.addDisPropEditableAttribute("01#" + "Operationalizations");
		semClaim.addDisPropEditableAttribute("03#" + "ConditionalExpression");

		semClaim.addDisPanelSpacersAttribute("#" + "Operationalizations"
				+ "#\n#");

		SoftSemanticConceptSatisficing semSoftDependency = new SoftSemanticConceptSatisficing(
				semGeneralElement, "SoftDependency", false);
		semanticConcepts.put("SD", semSoftDependency);

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
		semSoftgoalElements.add(semSoftGoal);

		List<AbstractSemanticVertex> semClaimsElements = new ArrayList<AbstractSemanticVertex>();
		semClaimsElements.add(semClaim);

		List<AbstractSemanticVertex> semSDElements = new ArrayList<AbstractSemanticVertex>();
		semSDElements.add(semSoftDependency);

		// Relations
		List<GroupRelationType> alternativeGroupRelation = new ArrayList<GroupRelationType>();
		alternativeGroupRelation.add(GroupRelationType.alternative);

		List<GroupRelationType> alternative_implicates_means_endsGroupRelation = new ArrayList<GroupRelationType>();
		alternative_implicates_means_endsGroupRelation
				.add(GroupRelationType.alternative);
		alternative_implicates_means_endsGroupRelation
				.add(GroupRelationType.means_ends);
		alternative_implicates_means_endsGroupRelation
				.add(GroupRelationType.implication);

		List<IntDirectRelationType> alternative_prefferedDirectRelation = new ArrayList<IntDirectRelationType>();
		alternative_prefferedDirectRelation.add(DirectRelationType.alternative);
		alternative_prefferedDirectRelation.add(DirectRelationType.preferred);

		List<IntDirectRelationType> alternative_preffered_implication_means_endsDirectRelation = new ArrayList<IntDirectRelationType>();
		alternative_preffered_implication_means_endsDirectRelation
				.add(DirectRelationType.alternative);
		alternative_preffered_implication_means_endsDirectRelation
				.add(DirectRelationType.preferred);
		alternative_preffered_implication_means_endsDirectRelation
				.add(DirectRelationType.implication);
		alternative_preffered_implication_means_endsDirectRelation
				.add(DirectRelationType.means_ends);

		List<IntDirectRelationType> allSGDirectRelation = new ArrayList<IntDirectRelationType>();
		allSGDirectRelation.add(DirectRelationType.alternative);
		allSGDirectRelation.add(DirectRelationType.preferred);
		allSGDirectRelation.add(DirectRelationType.implication);
		allSGDirectRelation.add(DirectRelationType.means_ends);
		allSGDirectRelation.add(DirectRelationType.conflict);
		allSGDirectRelation.add(DirectRelationType.required);

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

		List<IntDirectRelationType> implicationDirectRelation = new ArrayList<IntDirectRelationType>();
		implicationDirectRelation.add(DirectRelationType.implication);

		List<IntDirectRelationType> means_endsImplicationDirectRelation = new ArrayList<IntDirectRelationType>();
		means_endsImplicationDirectRelation.add(DirectRelationType.means_ends);
		means_endsImplicationDirectRelation.add(DirectRelationType.implication);

		List<IntDirectRelationType> normalDirectRelation = new ArrayList<IntDirectRelationType>();
		normalDirectRelation.add(DirectRelationType.normal);

		List<GroupRelationType> implementationGroupRelation = new ArrayList<GroupRelationType>();
		implementationGroupRelation.add(GroupRelationType.implementation);

		List<IntDirectRelationType> implementationDirectRelation = new ArrayList<IntDirectRelationType>();
		implementationDirectRelation.add(DirectRelationType.implementation);

		// required and conflict group relations of the HardSemanticConcept
		List<GroupRelationType> requires_conflictsGroupRelation = new ArrayList<GroupRelationType>();
		requires_conflictsGroupRelation.add(GroupRelationType.required);
		requires_conflictsGroupRelation.add(GroupRelationType.conflict);

		SemanticGroupDependency semanticHardHardGroupRelation = new SemanticGroupDependency(
				"HardGroupRel", false, requires_conflictsGroupRelation,
				semHardOutgoingRelation);
		groupRelation = new IncomingSemanticEdge(
				semanticHardHardGroupRelation);
		semHardConcept.addGroupRelation(groupRelation);

		// required and conflict direct relations of the HardSemanticConcept
		List<IntDirectRelationType> requires_conflictsDirectRelation = new ArrayList<IntDirectRelationType>();
		requires_conflictsDirectRelation.add(DirectRelationType.required);
		requires_conflictsDirectRelation.add(DirectRelationType.conflict);
		DirectSemanticEdge directRelation = new DirectSemanticEdge(
				"HardDirectRel", false, requires_conflictsDirectRelation);
		semHardConcept.addDirectRelation(directRelation);
		semanticConcepts.put("HardGroupRel", semanticHardHardGroupRelation);

		// Goal to Goal

		List<OutgoingSemanticEdge> outgoingGoalRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingGoalRelation.add(new OutgoingSemanticEdge(semGoal));
		SemanticGroupDependency semanticGoalGoalGroupRelation = new SemanticGroupDependency(
				"GroupDep Goal to Goal", false,
				alternative_implicates_means_endsGroupRelation,
				outgoingGoalRelation);
		groupRelation = new IncomingSemanticEdge(
				semanticGoalGoalGroupRelation);
		semGoal.addGroupRelation(groupRelation);
		semanticConcepts.put("GroupDep Goal to Goal",
				semanticGoalGoalGroupRelation);

		directRelation = new DirectSemanticEdge("GoalDirectRel", false,
				alternative_preffered_implication_means_endsDirectRelation);
		semGoal.addDirectRelation(directRelation);

		// Oper to Goal and Oper
		List<OutgoingSemanticEdge> outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingOperationalizationRelation.add(new OutgoingSemanticEdge(
				semGoal));
		outgoingOperationalizationRelation.add(new OutgoingSemanticEdge(
				semOperationalization));
		SemanticGroupDependency semanticOperGoalGroupRelation = new SemanticGroupDependency(
				"GroupDep Oper to Goal and Oper", false,
				means_endsImplicationGroupRelation,
				outgoingOperationalizationRelation);
		directRelation = new DirectSemanticEdge("OperDirectRel1", false,
				means_endsImplicationDirectRelation);
		groupRelation = new IncomingSemanticEdge(
				semanticOperGoalGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);
		semOperationalization.addDirectRelation(directRelation);
		semanticConcepts.put("GroupDep Oper to Goal and Oper",
				semanticOperGoalGroupRelation);

		// Oper to Oper
		outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingOperationalizationRelation.add(new OutgoingSemanticEdge(
				semOperationalization));
		SemanticGroupDependency semanticOperOperGroupRelation = new SemanticGroupDependency(
				"GroupDep Oper to Oper", false, alternativeGroupRelation,
				outgoingOperationalizationRelation);
		groupRelation = new IncomingSemanticEdge(
				semanticOperOperGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);
		directRelation = new DirectSemanticEdge("OperDirectRel2", false,
				alternative_prefferedDirectRelation);
		semOperationalization.addDirectRelation(directRelation);
		semanticConcepts.put("GroupDep Oper to Oper",
				semanticOperOperGroupRelation);

		// SG to SG
		List<OutgoingSemanticEdge> outgoingSoftgoalRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingSoftgoalRelation.add(new OutgoingSemanticEdge(true,
				semSoftGoal));
		SemanticGroupDependency semanticSGSGGroupRelation = new SemanticGroupDependency(
				"SoftgoalGroupRel", false, allSGGroupRelation,
				outgoingSoftgoalRelation);
		groupRelation = new IncomingSemanticEdge(semanticSGSGGroupRelation);
		semSoftGoal.addGroupRelation(groupRelation);
		directRelation = new DirectSemanticEdge("OperDirectRel1", false,
				allSGDirectRelation);
		semSoftGoal.addDirectRelation(directRelation);
		semanticConcepts.put("SoftgoalGroupRel", semanticSGSGGroupRelation);

		// Oper to Claim
		outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingSoftgoalRelation.add(new OutgoingSemanticEdge(semClaim));
		SemanticGroupDependency semanticOperClaimGroupRelation = new SemanticGroupDependency(
				"OperGroupRel3", true, implicationGroupRelation,
				outgoingSoftgoalRelation);
		groupRelation = new IncomingSemanticEdge(
				semanticOperClaimGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);
		directRelation = new DirectSemanticEdge("OperDirectRel3", true,
				implicationDirectRelation);
		semOperationalization.addDirectRelation(directRelation);
		semanticConcepts.put("OperGroupRel3", semanticSGSGGroupRelation);

		// Claim to SG
		directRelation = new DirectSemanticEdge(true, "ClaimDirectRel",
				true, normalDirectRelation);
		semClaim.addDirectRelation(directRelation);

		// SD to SG
		directRelation = new DirectSemanticEdge(true,
				"SoftDependencyDirectRel", true, normalDirectRelation);
		semSoftDependency.addDirectRelation(directRelation);

		// Asset to Oper
		List<OutgoingSemanticEdge> outgoingAssetRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingAssetRelation.add(new OutgoingSemanticEdge(
				semOperationalization));
		SemanticGroupDependency semanticAssetOperGroupRelation = new SemanticGroupDependency(
				"AssetGroupRel", false, implementationGroupRelation,
				outgoingAssetRelation);
		groupRelation = new IncomingSemanticEdge(
				semanticAssetOperGroupRelation);
		semSoftGoal.addGroupRelation(groupRelation);
		directRelation = new DirectSemanticEdge("AssetDirectRel", true,
				implementationDirectRelation);
		semAsset.addDirectRelation(directRelation);
		semanticConcepts.put("AssetGroupRel", semanticAssetOperGroupRelation);

		// TODO: structural and functional dependency relations

		// *************************---------------****************************
		// Our MetaModel objects definition

		MetaView syntaxMetaView = null;

		// *************************---------------****************************
		// Goals and avariability model
		syntaxMetaView = new MetaView("GoalsAndVaribilityModel",
				"Goals and Variability Model", "Goals and Variability Palette",
				0);

		MetaConcept syntaxVariabilityArtifact = new MetaConcept("VE", false,
				"VariabilityArtifact", null, 0, 0, null, true, null, 3, true,
				semHardConcept);
		syntaxVariabilityArtifact.addModelingAttribute("name", "String", "");

		syntaxVariabilityArtifact.addDisPanelVisibleAttribute("03#" + "name");

		syntaxVariabilityArtifact.addDisPropEditableAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxVariabilityArtifact);
		syntaxElements.put("VA", syntaxVariabilityArtifact);

		metaViews.add(syntaxMetaView);

		MetaConcept syntaxTopGoal = new MetaConcept("TG", true, "Top Goal",
				"rqgoal", 100, 40,
				"/com/variamos/gui/refas/editor/images/goal.png", true,
				Color.BLUE.toString(), 3, true, semGoal);
		System.out.println("TG: "
				+ syntaxTopGoal.getModelingAttributes().toString());
		syntaxTopGoal.addMetaExtendRelation(syntaxVariabilityArtifact, false);

		syntaxMetaView.addConcept(syntaxTopGoal);
		syntaxElements.put("TG", syntaxTopGoal);

		MetaConcept syntaxGeneralGoal = new MetaConcept("GG", true,
				"General Goal", "rqgoal", 100, 40,
				"/com/variamos/gui/refas/editor/images/goal.png", true,
				Color.BLUE.toString(), 2, true, semGoal);
		syntaxGeneralGoal.addMetaExtendRelation(syntaxVariabilityArtifact,
				false);

		syntaxMetaView.addConcept(syntaxGeneralGoal);
		syntaxElements.put("GG", syntaxGeneralGoal);

		MetaConcept sOperationalization = new MetaConcept("OPER", true,
				"Operationalization", "rqoper", 100, 40,
				"/com/variamos/gui/refas/editor/images/operational.png", true,
				Color.BLUE.toString(), 2, true, semOperationalization);
		sOperationalization.addMetaExtendRelation(syntaxVariabilityArtifact,
				false);

		syntaxMetaView.addConcept(sOperationalization);
		syntaxElements.put("OPER", sOperationalization);

		MetaConcept syntaxAssumption = new MetaConcept("ASSUM", true,
				"semanticAssumption", "rqsassump", 100, 40,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.WHITE.toString(), 1, true, semAssumption);
		syntaxAssumption
				.addMetaExtendRelation(syntaxVariabilityArtifact, false);

		syntaxMetaView.addConcept(syntaxAssumption);
		syntaxElements.put("ASSUM", syntaxAssumption);

		List<IntSemanticGroupDependency> semanticRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticRelations.add(semanticGoalGoalGroupRelation);
		semanticRelations.add(semanticOperGoalGroupRelation);
		semanticRelations.add(semanticOperOperGroupRelation);
		semanticRelations.add(semanticHardHardGroupRelation);

		MetaGroupDependency syntaxGroupDependency = new MetaGroupDependency(
				"Other GD", true, "Other GD", "plgroup", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);

		syntaxMetaView.addConcept(syntaxGroupDependency);
		syntaxElements.put("Other GD", syntaxGroupDependency);

		// *************************---------------****************************
		// Softgoals model

		syntaxMetaView = new MetaView("SoftGoals", "Soft Goals Model",
				"Soft Goals Palette", 1);
		metaViews.add(syntaxMetaView);

		MetaConcept syntaxTopSoftGoal = new MetaConcept("TSG", true,
				"Top Softgoal", "rqsoftgoal", 100, 40,
				"/com/variamos/gui/refas/editor/images/softgoal.png", true,
				Color.WHITE.toString(), 3, true, semSoftGoal);

		syntaxTopSoftGoal.addModelingAttribute("name", "String", "");
		syntaxTopSoftGoal.addDisPanelVisibleAttribute("03#" + "name");

		syntaxTopSoftGoal.addDisPropEditableAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxTopSoftGoal);
		syntaxElements.put("TSG", syntaxTopSoftGoal);

		MetaConcept syntaxGeneralSoftGoal = new MetaConcept("GSG", true,
				"General Softgoal", "rqsoftgoal", 100, 40,
				"/com/variamos/gui/refas/editor/images/softgoal.png", true,
				Color.WHITE.toString(), 1, true, semSoftGoal);
		syntaxGeneralSoftGoal.addModelingAttribute("name", "String", "");

		syntaxGeneralSoftGoal.addDisPanelVisibleAttribute("03#" + "name");

		syntaxGeneralSoftGoal.addDisPropEditableAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxGeneralSoftGoal);
		syntaxElements.put("GSG", syntaxGeneralSoftGoal);

		semanticRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticRelations.add(semanticSGSGGroupRelation);

		syntaxGroupDependency = new MetaGroupDependency("Softgoal GD", true,
				"Softgoal GD", "plgroup", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);

		syntaxMetaView.addConcept(syntaxGroupDependency);
		syntaxElements.put("Softgoal GD", syntaxGroupDependency);

		// *************************---------------****************************
		// Context model

		syntaxMetaView = new MetaView("Context", "Context Model",
				"Context Palette", 2);
		metaViews.add(syntaxMetaView);
		// syntaxMetaView.addConcept(syntaxVariable);

		MetaConcept syntaxContextGroup = new MetaConcept("GV", true,
				"Global Variable", "rqcontextgrp", 150, 40,
				"/com/variamos/gui/refas/editor/images/contextgrp.png", true,
				Color.BLUE.toString(), 1, true, semVariable);

		syntaxMetaView.addConcept(syntaxContextGroup);
		syntaxElements.put("GV", syntaxContextGroup);

		MetaConcept syntaxGlobalVariable = new MetaConcept("GV", true,
				"Global Variable", "rqglobcnxt", 150, 40,
				"/com/variamos/gui/refas/editor/images/globCnxtVar.png", true,
				Color.BLUE.toString(), 1, true, semVariable);

		syntaxMetaView.addConcept(syntaxGlobalVariable);
		syntaxElements.put("GV", syntaxGlobalVariable);

		MetaConcept syntaxLocalVariable = new MetaConcept("GV", true,
				"Local Variable", "rqlocalcnxt", 100, 40,
				"/com/variamos/gui/refas/editor/images/localCnxtVar.png", true,
				Color.BLUE.toString(), 1, true, semVariable);

		syntaxMetaView.addConcept(syntaxLocalVariable);
		syntaxElements.put("GV", syntaxLocalVariable);

		// TODO define context model

		// *************************---------------****************************
		// Assets model

		syntaxMetaView = new MetaView("SoftGoalsSatisficing",
				"SG Satisficing Model", "Soft Goals Satisficing Palette", 3);
		metaViews.add(syntaxMetaView);
		syntaxMetaView.addConcept(syntaxTopSoftGoal);
		syntaxMetaView.addConcept(syntaxGeneralSoftGoal);
		syntaxMetaView.addConcept(sOperationalization);

		MetaConcept syntaxClaim = new MetaConcept("CL", true, "Claim",
				"rqclaim", 100, 40,
				"/com/variamos/gui/refas/editor/images/claim.png", true,
				Color.BLUE.toString(), 1, true, semClaim);
		syntaxMetaView.addConcept(syntaxClaim);
		syntaxElements.put("CL", syntaxClaim);

		MetaConcept syntaxSoftDependency = new MetaConcept("SD", true,
				"Soft Dependency", "rqsoftdep", 100, 40,
				"/com/variamos/gui/refas/editor/images/softdep.png", true,
				Color.BLUE.toString(), 1, true, semSoftDependency);
		syntaxSoftDependency.addModelingAttribute("03#"
				+ "ConditionalExpression", "String", "");
		syntaxSoftDependency.addDisPanelVisibleAttribute("03#"
				+ "ConditionalExpression"); // TODO move to semantic attributes
		syntaxSoftDependency.addDisPropEditableAttribute("03#"
				+ "ConditionalExpression");

		syntaxMetaView.addConcept(syntaxSoftDependency);
		syntaxElements.put("SD", syntaxSoftDependency);

		semanticRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticRelations.add(semanticOperClaimGroupRelation);

		syntaxGroupDependency = new MetaGroupDependency("Oper-Claim GD", true,
				"Oper-Claim GD", "plgroup", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);
		syntaxMetaView.addConcept(syntaxGroupDependency);
		syntaxElements.put("Oper-Claim GD", syntaxGroupDependency);

		syntaxMetaView = new MetaView("Assets", "Assets General Model",
				"Assets Palette", 4);
		metaViews.add(syntaxMetaView);
		syntaxMetaView.addConcept(sOperationalization);

		MetaConcept syntaxAsset = new MetaConcept("AS", true, "Asset",
				"rqcompon", 100, 40,
				"/com/variamos/gui/refas/editor/images/component.png", true,
				Color.WHITE.toString(), 1, true, semAsset);
		syntaxAsset.addModelingAttribute("name", "String", ""); // TODO move to
		// semantic
		// attributes
		syntaxAsset.addDisPanelVisibleAttribute("03#" + "name");
		syntaxAsset.addDisPropEditableAttribute("03#" + "name");
		syntaxMetaView.addConcept(syntaxAsset);

		syntaxMetaView.addConcept(sOperationalization);
		syntaxElements.put("AS", syntaxAsset);

		MetaView syntaxMetaChildView = new MetaView("FunctionalAssets",
				"Functionl Assets Relations", "Assets Palette", 1);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		syntaxMetaChildView.addConcept(sOperationalization);
		syntaxMetaChildView.addConcept(syntaxAsset);

		syntaxMetaChildView = new MetaView("StructuralAssets",
				"Structural Assets Relations", "Assets Palette", 2);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		syntaxMetaChildView.addConcept(sOperationalization);
		syntaxMetaChildView.addConcept(syntaxAsset);

		semanticRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticRelations.add(semanticAssetOperGroupRelation);

		syntaxGroupDependency = new MetaGroupDependency("Asset-Oper GD", true,
				"Asset-Oper GD", "plgroup", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);

		syntaxMetaView.addConcept(syntaxGroupDependency);
		syntaxElements.put("Asset-Oper GD", syntaxGroupDependency);
	}

	public static void main(String[] args) {
		SemanticPlusSyntax mst = new SemanticPlusSyntax();
		List<AbstractSemanticVertex> ascs = (List<AbstractSemanticVertex>) mst
				.getSemanticConcepts().values();
		for (int i = 0; i < ascs.size(); i++)
			System.out.println(ascs.get(i));
	}

}
