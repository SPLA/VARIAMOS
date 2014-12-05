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
	private Map<String, AbstractSemanticConcept> semanticConcepts = new HashMap<String, AbstractSemanticConcept>();
	private List<MetaView> metaViews = new ArrayList<MetaView>();
	private Map<String, MetaElement> syntaxConcepts = new HashMap<String, MetaElement>();

	public Map<String,AbstractSemanticConcept> getSemanticConcepts() {
		return semanticConcepts;
	}
	public Map<String, MetaElement> getsyntaxConcepts() {
		return syntaxConcepts;
	}

	public MetaElement getMetaConcept(String name) {
		return syntaxConcepts.get(name);
	}

	public void setSyntaxConcepts(Map<String, MetaElement> syntaxConcepts) {
		this.syntaxConcepts = syntaxConcepts;
	}

	public AbstractSemanticConcept getSemanticConcept(
			String abstractSemanticConceptIdentifier) {
		return semanticConcepts.get(abstractSemanticConceptIdentifier);
	}

	public boolean elementsValidation(String element, int modelViewInd,
			int modelViewSubInd) {
		if (modelViewInd < metaViews.size() && modelViewSubInd == -1) {
			Iterator<MetaElement> metaConcept = metaViews.get(modelViewInd)
					.getConcepts().iterator();
			for (int i = 0; i < metaViews.get(modelViewInd).getConcepts()
					.size(); i++) {

				if (metaConcept.next().getIdentifier().equals(element))
					return true;
			}
		}
		if (modelViewInd < metaViews.size()
				&& modelViewSubInd != -1
				&& modelViewSubInd < metaViews.get(modelViewInd)
						.getChildViews().size()) {
			Iterator<MetaElement> metaConcepts = metaViews.get(modelViewInd)
					.getChildViews().get(modelViewSubInd).getConcepts()
					.iterator();
			while (metaConcepts.hasNext())
				if (metaConcepts.next().getIdentifier().equals(element))
					return true;
		}
		return false;
	}

	public List<String> modelElements(int modelViewInd, int modelViewSubInd) {
		List<String> elements = new ArrayList<String>();
		if (modelViewInd < metaViews.size() && modelViewSubInd == -1) {
			Iterator<MetaElement> metaConcepts = metaViews.get(modelViewInd)
					.getConcepts().iterator();
			while (metaConcepts.hasNext()) {
				MetaElement tmp = metaConcepts.next();
				if (tmp.getStyle() != null)
					elements.add(tmp.getIdentifier());
			}
		}
		if (modelViewInd < metaViews.size()
				&& modelViewSubInd != -1
				&& modelViewSubInd < metaViews.get(modelViewInd)
						.getChildViews().size()) {
			Iterator<MetaElement> metaConcepts = metaViews.get(modelViewInd)
					.getChildViews().get(modelViewSubInd).getConcepts()
					.iterator();
			while (metaConcepts.hasNext()) {
				MetaElement tmp = metaConcepts.next();
				if (tmp.getStyle() != null)
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

		DirectSemanticRelation dirRelation = null;
		IncomingSemanticRelation groupRelation = null;

		AbstractSemanticConcept semGeneralElement = new AbstractSemanticConcept();
		semanticConcepts.put("GE",semGeneralElement);

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
		semanticConcepts.put("HC",semHardConcept);

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

		List<OutgoingSemanticRelation> semHardOutgoingRelation = new ArrayList<OutgoingSemanticRelation>();
		semHardOutgoingRelation
				.add(new OutgoingSemanticRelation(semHardConcept));

		// required and conflict group relations of the HardSemanticConcept
		List<GroupRelationType> requires_conflictsGroupRelation = new ArrayList<GroupRelationType>();
		requires_conflictsGroupRelation.add(GroupRelationType.required);
		requires_conflictsGroupRelation.add(GroupRelationType.conflict);

		SemanticGroupDependency semanticGroupRelation = new SemanticGroupDependency(
				"HardGroupRel", false, requires_conflictsGroupRelation,
				semHardOutgoingRelation);
		groupRelation = new IncomingSemanticRelation(semanticGroupRelation);
		semHardConcept.addGroupRelation(groupRelation);

		// required and conflict direct relations of the HardSemanticConcept
		List<IntDirectRelationType> requires_conflictsDirectRelation = new ArrayList<IntDirectRelationType>();
		requires_conflictsDirectRelation.add(DirectRelationType.required);
		requires_conflictsDirectRelation.add(DirectRelationType.conflict);
		DirectSemanticRelation directRelation = new DirectSemanticRelation(
				"HardDirectRel", false, requires_conflictsDirectRelation);
		semHardConcept.addDirectRelation(directRelation);

		// definition of other concepts
		HardSemanticConcept semAssumption = new HardSemanticConcept(
				semHardConcept, "Assumption");
		semanticConcepts.put("AS",semAssumption);

		HardSemanticConcept semGoal = new HardSemanticConcept(semHardConcept,
				"Goal");

		semGoal.addDisPanelVisibleAttribute("01#" + "satisfactionType");
		semGoal.addDisPanelSpacersAttribute("<#" + "satisfactionType" + "#>\n");
		semanticConcepts.put("G",semGoal);

		HardSemanticConcept semOperationalization = new HardSemanticConcept(
				semHardConcept, "Operationalization");
		semanticConcepts.put("OPER",semOperationalization);

		SoftSemanticConcept semSoftGoal = new SoftSemanticConcept(
				semGeneralElement, "SoftGoal");
		semanticConcepts.put("SG",semSoftGoal);

		HardSemanticConcept semAsset = new HardSemanticConcept(
				semGeneralElement, "Asset");
		semanticConcepts.put("ASSE",semAsset);

		SoftSemanticConceptSatisficing semClaim = new SoftSemanticConceptSatisficing(
				semGeneralElement, "Claim", true);
		semanticConcepts.put("CL",semClaim);
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
		semanticConcepts.put("SD",semSoftDependency);

		// Elements Lists
		List<AbstractSemanticConcept> semAssumptionElements = new ArrayList<AbstractSemanticConcept>();
		semAssumptionElements.add(semAssumption);

		List<AbstractSemanticConcept> SemGoalOperElements = new ArrayList<AbstractSemanticConcept>();
		SemGoalOperElements.add(semGoal);
		SemGoalOperElements.add(semOperationalization);

		List<AbstractSemanticConcept> semGoalElements = new ArrayList<AbstractSemanticConcept>();
		semGoalElements.add(semGoal);

		List<AbstractSemanticConcept> semOperationalizationElements = new ArrayList<AbstractSemanticConcept>();
		semOperationalizationElements.add(semOperationalization);

		List<AbstractSemanticConcept> semSoftgoalElements = new ArrayList<AbstractSemanticConcept>();
		semSoftgoalElements.add(semSoftGoal);

		List<AbstractSemanticConcept> semClaimsElements = new ArrayList<AbstractSemanticConcept>();
		semClaimsElements.add(semClaim);

		List<AbstractSemanticConcept> semSDElements = new ArrayList<AbstractSemanticConcept>();
		semSDElements.add(semSoftDependency);

		// Relations
		List<GroupRelationType> alternativeGroupRelation = new ArrayList<GroupRelationType>();
		alternativeGroupRelation.add(GroupRelationType.alternative);

		List<GroupRelationType> alternative_implicates_means_endsDirectRelation = new ArrayList<GroupRelationType>();
		alternative_implicates_means_endsDirectRelation
				.add(GroupRelationType.alternative);
		alternative_implicates_means_endsDirectRelation
				.add(GroupRelationType.means_ends);
		alternative_implicates_means_endsDirectRelation
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

		// Goal to Goal

		List<OutgoingSemanticRelation> outgoingGoalRelation = new ArrayList<OutgoingSemanticRelation>();
		outgoingGoalRelation.add(new OutgoingSemanticRelation(semGoal));
		SemanticGroupDependency semanticGoalGoalGroupRelation = new SemanticGroupDependency(
				"GroupDep Goal to Goal", false,
				alternative_implicates_means_endsDirectRelation,
				outgoingGoalRelation);
		groupRelation = new IncomingSemanticRelation(
				semanticGoalGoalGroupRelation);
		semGoal.addGroupRelation(groupRelation);
		semanticConcepts.put("GroupDep Goal to Goal",semanticGoalGoalGroupRelation);

		directRelation = new DirectSemanticRelation("GoalDirectRel", false,
				alternative_preffered_implication_means_endsDirectRelation);
		semGoal.addDirectRelation(directRelation);

		// Oper to Goal and Oper
		List<OutgoingSemanticRelation> outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticRelation>();
		outgoingOperationalizationRelation.add(new OutgoingSemanticRelation(
				semGoal));
		outgoingOperationalizationRelation.add(new OutgoingSemanticRelation(
				semOperationalization));
		SemanticGroupDependency semanticOperGoalGroupRelation = new SemanticGroupDependency(
				"GroupDep Oper to Goal and Oper", false,
				means_endsImplicationGroupRelation,
				outgoingOperationalizationRelation);
		directRelation = new DirectSemanticRelation("OperDirectRel1", false,
				means_endsImplicationDirectRelation);
		groupRelation = new IncomingSemanticRelation(
				semanticOperGoalGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);
		semOperationalization.addDirectRelation(directRelation);
		semanticConcepts.put("GroupDep Oper to Goal and Oper",semanticOperGoalGroupRelation);

		// Oper to Oper
		outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticRelation>();
		outgoingOperationalizationRelation.add(new OutgoingSemanticRelation(
				semOperationalization));
		SemanticGroupDependency semanticOperOperGroupRelation = new SemanticGroupDependency(
				"GroupDep Oper to Oper", false, alternativeGroupRelation,
				outgoingOperationalizationRelation);
		groupRelation = new IncomingSemanticRelation(
				semanticOperOperGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);
		directRelation = new DirectSemanticRelation("OperDirectRel2", false,
				alternative_prefferedDirectRelation);
		semOperationalization.addDirectRelation(directRelation);
		semanticConcepts.put("GroupDep Oper to Oper",semanticOperOperGroupRelation);

		// SG to SG
		List<OutgoingSemanticRelation> outgoingSoftgoalRelation = new ArrayList<OutgoingSemanticRelation>();
		outgoingSoftgoalRelation.add(new OutgoingSemanticRelation(true,
				semSoftGoal));
		semanticGroupRelation = new SemanticGroupDependency("SoftgoalGroupRel",
				false, allSGGroupRelation, outgoingSoftgoalRelation);
		semSoftGoal.addGroupRelation(groupRelation);
		directRelation = new DirectSemanticRelation("OperDirectRel1", false,
				allSGDirectRelation);
		semSoftGoal.addDirectRelation(directRelation);

		// Oper to Claim
		outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticRelation>();
		outgoingSoftgoalRelation.add(new OutgoingSemanticRelation(semClaim));
		semanticGroupRelation = new SemanticGroupDependency("OperGroupRel3",
				true, implicationGroupRelation, outgoingSoftgoalRelation);
		semOperationalization.addGroupRelation(groupRelation);
		directRelation = new DirectSemanticRelation("OperDirectRel3", true,
				implicationDirectRelation);
		semOperationalization.addDirectRelation(directRelation);

		// Claim to SG
		directRelation = new DirectSemanticRelation(true, "ClaimDirectRel",
				true, normalDirectRelation);
		semClaim.addDirectRelation(directRelation);

		// SD to SG
		directRelation = new DirectSemanticRelation(true,
				"SoftDependencyDirectRel", true, normalDirectRelation);
		semSoftDependency.addDirectRelation(directRelation);

		// Asset to Oper
		List<OutgoingSemanticRelation> outgoingAssetRelation = new ArrayList<OutgoingSemanticRelation>();
		outgoingAssetRelation.add(new OutgoingSemanticRelation(
				semOperationalization));
		semanticGroupRelation = new SemanticGroupDependency("AssetGroupRel",
				false, implementationGroupRelation, outgoingAssetRelation);
		semSoftGoal.addGroupRelation(groupRelation);
		directRelation = new DirectSemanticRelation("AssetDirectRel", true,
				implementationDirectRelation);
		semAsset.addDirectRelation(directRelation);

		// TODO: structural and functional dependency relations

		// Our MetaModel objects definition

		MetaView syntaxMetaView = null;

		syntaxMetaView = new MetaView("GoalsAndVaribilityModel",
				"Goals and Variability Model", "Goals and Variability Palette",
				0);

		MetaConcept syntaxVariabilityArtifact = new MetaConcept("VE",
				"VariabilityArtifact", null, 0, 0, null, true, null, 3, true,
				semHardConcept);
		syntaxVariabilityArtifact.addMetaAttribute("name", "String", "");

		syntaxVariabilityArtifact.addDisPanelVisibleAttribute("03#" + "name");

		syntaxVariabilityArtifact.addDisPropEditableAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxVariabilityArtifact);
		syntaxConcepts.put("VA", syntaxVariabilityArtifact);
		System.out.println("VA: "
				+ syntaxVariabilityArtifact.getMetaAttributes().toString());

		metaViews.add(syntaxMetaView);

		MetaConcept syntaxTopGoal = new MetaConcept("TG", "Top Goal", "rqgoal",
				100, 40, "/com/variamos/gui/refas/editor/images/goal.png",
				true, Color.BLUE.toString(), 3, true, semGoal);
		System.out.println("TG: "
				+ syntaxTopGoal.getMetaAttributes().toString());
		syntaxTopGoal.addMetaExtendRelation(syntaxVariabilityArtifact, false);
		System.out.println("TGe: "
				+ syntaxTopGoal.getMetaAttributes().toString());
		syntaxMetaView.addConcept(syntaxTopGoal);
		syntaxConcepts.put("TG", syntaxTopGoal);

		MetaConcept syntaxGeneralGoal = new MetaConcept("GG", "General Goal",
				"rqgoal", 100, 40,
				"/com/variamos/gui/refas/editor/images/goal.png", true,
				Color.BLUE.toString(), 2, true, semGoal);
		syntaxGeneralGoal.addMetaExtendRelation(syntaxVariabilityArtifact,
				false);
		syntaxMetaView.addConcept(syntaxGeneralGoal);
		syntaxConcepts.put("GG", syntaxGeneralGoal);

		MetaConcept sOperationalization = new MetaConcept("OPER",
				"Operationalization", "rqoper", 100, 40,
				"/com/variamos/gui/refas/editor/images/operational.png", true,
				Color.BLUE.toString(), 2, true, semOperationalization);
		sOperationalization.addMetaExtendRelation(syntaxVariabilityArtifact,
				false);
		syntaxMetaView.addConcept(sOperationalization);
		syntaxConcepts.put("OPER", sOperationalization);

		MetaConcept syntaxAssumption = new MetaConcept("ASSUM",
				"semanticAssumption", "rqsassump", 100, 40,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.WHITE.toString(), 1, true, semAssumption);
		syntaxAssumption
				.addMetaExtendRelation(syntaxVariabilityArtifact, false);

		syntaxMetaView.addConcept(syntaxAssumption);

		List<IntSemanticGroupDependency> semanticRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticRelations.add(semanticGoalGoalGroupRelation);
		semanticRelations.add(semanticOperGoalGroupRelation);
		semanticRelations.add(semanticOperOperGroupRelation);

		MetaGroupDependency syntaxGroupDependency = new MetaGroupDependency(
				"HardGD", "Group Dependency", "plgroup", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);
		syntaxMetaView.addConcept(syntaxGroupDependency);
		syntaxConcepts.put("HardGD", syntaxGroupDependency);

		syntaxMetaView = new MetaView("SoftGoals", "Soft Goals Model",
				"Soft Goals Palette", 1);
		metaViews.add(syntaxMetaView);

		MetaConcept syntaxTopSoftGoal = new MetaConcept("TSG", "Top Softgoal",
				"rqsoftgoal", 100, 40,
				"/com/variamos/gui/refas/editor/images/softgoal.png", true,
				Color.WHITE.toString(), 3, true, semSoftGoal);
		syntaxTopSoftGoal.addMetaAttribute("name", "String", "");
		syntaxTopSoftGoal.addDisPanelVisibleAttribute("03#" + "name");

		syntaxTopSoftGoal.addDisPropEditableAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxTopSoftGoal);
		syntaxConcepts.put("TSG", syntaxTopSoftGoal);

		MetaConcept syntaxGeneralSoftGoal = new MetaConcept("GSG",
				"General Softgoal", "rqsoftgoal", 100, 40,
				"/com/variamos/gui/refas/editor/images/softgoal.png", true,
				Color.WHITE.toString(), 1, true, semSoftGoal);
		syntaxGeneralSoftGoal.addMetaAttribute("name", "String", "");

		syntaxGeneralSoftGoal.addDisPanelVisibleAttribute("03#" + "name");

		syntaxGeneralSoftGoal.addDisPropEditableAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxGeneralSoftGoal);
		syntaxConcepts.put("GSG", syntaxGeneralSoftGoal);

		syntaxMetaView = new MetaView("Context", "Context Model",
				"Context Palette", 2);
		metaViews.add(syntaxMetaView);

		// TODO define context model

		syntaxMetaView = new MetaView("SoftGoalsSatisficing",
				"SG Satisficing Model", "Soft Goals Satisficing Palette", 3);
		metaViews.add(syntaxMetaView);
		syntaxMetaView.addConcept(syntaxTopSoftGoal);
		syntaxMetaView.addConcept(syntaxGeneralSoftGoal);
		syntaxMetaView.addConcept(sOperationalization);

		MetaConcept syntaxClaim = new MetaConcept("CL", "semClaim", "rqclaim",
				100, 40, "/com/variamos/gui/refas/editor/images/claim.png",
				true, Color.BLUE.toString(), 1, true, semClaim);
		syntaxMetaView.addConcept(syntaxClaim);
		syntaxConcepts.put("CL", syntaxClaim);

		MetaConcept syntaxSoftDependency = new MetaConcept("SD",
				"Soft Dependency", "rqsoftdep", 100, 40,
				"/com/variamos/gui/refas/editor/images/softdep.png", true,
				Color.BLUE.toString(), 1, true, semSoftDependency);
		syntaxSoftDependency.addMetaAttribute("03#" + "ConditionalExpression",
				"String", "");
		syntaxSoftDependency.addDisPanelVisibleAttribute("03#"
				+ "ConditionalExpression"); // TODO move to semantic attributes
		syntaxSoftDependency.addDisPropEditableAttribute("03#"
				+ "ConditionalExpression");
		syntaxMetaView.addConcept(syntaxSoftDependency);
		syntaxConcepts.put("SD", syntaxSoftDependency);

		syntaxMetaView = new MetaView("Assets", "Assets General Model",
				"Assets Palette", 4);
		metaViews.add(syntaxMetaView);
		syntaxMetaView.addConcept(sOperationalization);

		MetaConcept syntaxAsset = new MetaConcept("AS", "Asset", "rqcompon",
				100, 40, "/com/variamos/gui/refas/editor/images/component.png",
				true, Color.WHITE.toString(), 1, true, semAsset);
		syntaxAsset.addMetaAttribute("name", "String", ""); // TODO move to
															// semantic
															// attributes
		syntaxAsset.addDisPanelVisibleAttribute("03#" + "name");
		syntaxAsset.addDisPropEditableAttribute("03#" + "name");
		syntaxMetaView.addConcept(syntaxAsset);
		syntaxMetaView.addConcept(sOperationalization);
		syntaxConcepts.put("AS", syntaxAsset);

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

	}

	public static void main(String[] args) {
		SemanticPlusSyntax mst = new SemanticPlusSyntax();
		List<AbstractSemanticConcept> ascs = (List<AbstractSemanticConcept>) mst.getSemanticConcepts().values();
		for (int i = 0; i < ascs.size(); i++)
			System.out.println(ascs.get(i));
	}



}
