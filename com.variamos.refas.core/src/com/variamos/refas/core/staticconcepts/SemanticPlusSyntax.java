package com.variamos.refas.core.staticconcepts;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.refas.core.sematicassociation.*;
import com.variamos.refas.core.sematicsmetamodel.*;
import com.variamos.syntaxsupport.metametamodel.*;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstView;

public class SemanticPlusSyntax {
	private List<AbstractSemanticConcept> semanticConcepts = new ArrayList<AbstractSemanticConcept>();
	private List<InstView> instViews = new ArrayList<InstView>();
	private List<MetaView> metaViews = new ArrayList<MetaView>();
	private Map<String,MetaConcept> metaConcepts = new HashMap<String,MetaConcept>();


	public List<AbstractSemanticConcept> getSemanticConcepts() {
		return semanticConcepts;
	}

	public List<InstView> getInstViews() {
		return instViews;
	}
	public List<MetaView> getMetaViews() {
		return metaViews;
	}

	public SemanticPlusSyntax() {

		// Dependencies

		SemanticAttribute sentence1 = null;
		SemanticAttribute sentence2 = null;
		SemanticAttribute logicalOperator = null;

		sentence1 = new SemanticAttribute("sentence1", "String", true); // todo:
																		// Sentence
																		// concept
		sentence2 = new SemanticAttribute("sentence2", "String", true);// todo:
																		// Sentence
																		// concept
		logicalOperator = new SemanticAttribute("operator", "String", true);// todo:
																			// LogicalOperator
																			// concept

		ConditionalExpression condition = new ConditionalExpression(sentence1,
				sentence2, logicalOperator);

		// Dependencies
		Dependency means_endsDependency = new Dependency(GroupRelationType.means_ends);
		Dependency requiredDependency = new Dependency(GroupRelationType.required);
		Dependency conflictDependency = new Dependency(GroupRelationType.conflict);
		Dependency alternativeDependency = new Dependency(GroupRelationType.alternative);
		Dependency mutexDependency = new Dependency(GroupRelationType.mutex);
		Dependency implicationDependency = new Dependency(GroupRelationType.implication);
	

		ImplementationDependency implementationDependency = new ImplementationDependency(condition);

		// Definition of variability concept and relations
		HardSemanticConcept variabilityElement = new HardSemanticConcept(
				"VariabilityElement", false, true);
		semanticConcepts.add(variabilityElement);

		DirectRelation dirRelation = null;
		// Direct Relations of the assumption
	/*	dirRelation = new DirectRelation(variabilityElement,
				DirectRelationType.preferred);
		variabilityElement.addDirectRelation(dirRelation);
		dirRelation = new DirectRelation(variabilityElement,
				DirectRelationType.required);
		variabilityElement.addDirectRelation(dirRelation);
		dirRelation = new DirectRelation(variabilityElement,
				DirectRelationType.conflict);
		variabilityElement.addDirectRelation(dirRelation);
		dirRelation = new DirectRelation(variabilityElement,
				DirectRelationType.alternative);
		variabilityElement.addDirectRelation(dirRelation);
		dirRelation = new DirectRelation(variabilityElement,
				DirectRelationType.mutex);
		variabilityElement.addDirectRelation(dirRelation);
	*/	
		GroupRelation groupRelation = null;
		// Group Relations of the assumption
		groupRelation = new GroupRelation(false, variabilityElement, requiredDependency);
		variabilityElement.addGroupRelation(groupRelation);
		groupRelation = new GroupRelation(false, variabilityElement, conflictDependency);
		variabilityElement.addGroupRelation(groupRelation);
		groupRelation = new GroupRelation(false, variabilityElement, alternativeDependency);
		variabilityElement.addGroupRelation(groupRelation);
		groupRelation = new GroupRelation(false, variabilityElement, mutexDependency);
		variabilityElement.addGroupRelation(groupRelation);
		
		
		// definition of other concepts
		HardSemanticConcept assumption = new HardSemanticConcept(
				variabilityElement, "Assumption", false, true);
		semanticConcepts.add(assumption);

		HardSemanticConcept topGoal = new HardSemanticConcept(
				variabilityElement, "TopGoal", true, false);
		semanticConcepts.add(topGoal);

		HardSemanticConcept generalGoal = new HardSemanticConcept(
				variabilityElement, "GeneralGoal", false, false);
		semanticConcepts.add(generalGoal);

		HardSemanticConcept operationalization = new HardSemanticConcept(
				variabilityElement, "Operationalization", false, false);
		semanticConcepts.add(operationalization);

		SoftSemanticConcept topSoftGoal = new SoftSemanticConcept(
				"TopSoftGoal", true, true);
		semanticConcepts.add(topSoftGoal);

		SoftSemanticConcept generalSoftGoal = new SoftSemanticConcept(
				"GeneralSoftGoal", false, true);
		semanticConcepts.add(generalSoftGoal);
		
		HardSemanticConcept asset = new HardSemanticConcept(
				"Asset", false, false);
		semanticConcepts.add(asset);
		
		SoftSemanticConceptSatisficing claim = new SoftSemanticConceptSatisficing(
				"Claim", condition);
		semanticConcepts.add(claim);
		
		SoftSemanticConceptSatisficing softDependency = new SoftSemanticConceptSatisficing(
				"SoftDependency", condition);
		semanticConcepts.add(softDependency);

		// Always allows Assumptions
		List<AbstractSemanticConcept> assumptionList = new ArrayList<AbstractSemanticConcept>();
		assumptionList.add(assumption);

		// Relations of the assumption
		dirRelation = new DirectRelation(topGoal, DirectRelationType.means_ends);
		assumption.addDirectRelation(dirRelation);
		dirRelation = new DirectRelation(generalGoal,
				DirectRelationType.means_ends);
		assumption.addDirectRelation(dirRelation);
		dirRelation = new DirectRelation(operationalization,
				DirectRelationType.means_ends);
		assumption.addDirectRelation(dirRelation);

		// means_endsDependency of the GeneralGoal
		groupRelation = new GroupRelation(true, topGoal, means_endsDependency,
				null, assumptionList);
		generalGoal.addGroupRelation(groupRelation);
		groupRelation = new GroupRelation(true, generalGoal,
				means_endsDependency, null, assumptionList);
		generalGoal.addGroupRelation(groupRelation);

		// means_endsDependency of the Operationalization
		groupRelation = new GroupRelation(true, topGoal, means_endsDependency,
				null, assumptionList);
		operationalization.addGroupRelation(groupRelation);
		groupRelation = new GroupRelation(true, generalGoal,
				means_endsDependency, null, assumptionList);
		operationalization.addGroupRelation(groupRelation);
		groupRelation = new GroupRelation(false, operationalization,
				means_endsDependency, null, assumptionList);
		operationalization.addGroupRelation(groupRelation);

		// implicationDependency of the Operationalizations
		groupRelation = new GroupRelation(true, claim,
				implicationDependency);
		operationalization.addGroupRelation(groupRelation);
		
		// means_endsDependency of the GeneralSoftGoal
		groupRelation = new GroupRelation(true, topSoftGoal,
				means_endsDependency, null, assumptionList);
		operationalization.addGroupRelation(groupRelation);
		groupRelation = new GroupRelation(true, generalSoftGoal,
				means_endsDependency, null, assumptionList);
		operationalization.addGroupRelation(groupRelation);

		// implementationRelation of the Asset
		groupRelation = new GroupRelation(true, operationalization,
				implementationDependency, null, assumptionList);
		asset.addGroupRelation(groupRelation);
		
		//TODO: structural and functional dependency relations
		
		// normal relation of the Claim
		dirRelation = new DirectRelation(topSoftGoal, DirectRelationType.normal);
		claim.addDirectRelation(dirRelation);
		dirRelation = new DirectRelation(generalSoftGoal, DirectRelationType.normal);
		claim.addDirectRelation(dirRelation);
		
		// normal relation of the SoftDependencies
		dirRelation = new DirectRelation(topSoftGoal, DirectRelationType.normal);
		softDependency.addDirectRelation(dirRelation);
		dirRelation = new DirectRelation(generalSoftGoal, DirectRelationType.normal);
		softDependency.addDirectRelation(dirRelation);
		

		
		
		
		//Our MetaModel objects definition
		InstSemanticConcept semConcept  = null;
		InstConcept syntaxConcept = null;
		MetaView sMetaView = null;
		InstView instView = null;
		
		sMetaView = new MetaView("GoalsAndVaribilityModel", "Goals And Varibility Model", "Goals and Variability Palette", 0);

		
		semConcept = new InstSemanticConcept("AC",null);
		MetaConcept sAbstractConcept = new MetaConcept("AC", "AbstractConcept", null, 0, 0, null, true, null, 3, true, semConcept);
				sAbstractConcept.addMetaAttribute("active","Boolean", false);
		sAbstractConcept.addMetaAttribute("description","String","");
		sAbstractConcept.addMetaAttribute("visibility","Boolean",true);
		sAbstractConcept.addPropEditableAttributes("active");
		sAbstractConcept.addPropEditableAttributes("description");
		sAbstractConcept.addPropVisibleAttributes("identifier");
		sMetaView.addConcept(sAbstractConcept);
		metaConcepts.put("AC", sAbstractConcept);
		
		semConcept = new InstSemanticConcept("VA",null);
		MetaConcept sVariabilityArtifact = new MetaConcept("VE", "VariabilityArtifact", null, 0, 0, null, true, null, 3, true, semConcept);
		sVariabilityArtifact.addMetaAttribute("name","String","");
		sVariabilityArtifact.addPanelVisibleAttributes("name");
		sVariabilityArtifact.addPropEditableAttributes("name");
		sMetaView.addConcept(sVariabilityArtifact);
		metaConcepts.put("VA", sVariabilityArtifact);
		
		System.out.println("VA: "+sVariabilityArtifact.getMetaAttributes().toString());
		sVariabilityArtifact.addExtendMetaDirectRelation(DirectRelationType.means_ends, sVariabilityArtifact, sAbstractConcept, false);
		System.out.println("VAe: "+sVariabilityArtifact.getMetaAttributes().toString());
		
		instView = new InstView("Goals", sMetaView);
		instViews.add(instView);
		metaViews.add(sMetaView);
				
			semConcept = new InstSemanticConcept("TG",topGoal);
			MetaConcept sTopGoal = new MetaConcept("TG", "Top Goal", "rqgoal", 100, 40, "/com/variamos/gui/refas/editor/images/goal.png", true, Color.BLUE.toString(), 3, true, semConcept);
			System.out.println("TG: "+sTopGoal.getMetaAttributes().toString());
			sTopGoal.addExtendMetaDirectRelation(DirectRelationType.means_ends, sTopGoal, sVariabilityArtifact, false);
			System.out.println("TGe: "+sTopGoal.getMetaAttributes().toString());
			sMetaView.addConcept(sTopGoal);
			metaConcepts.put("TG", sTopGoal);
	
			semConcept = new InstSemanticConcept("GG", generalGoal);
			MetaConcept sGeneralGoal = new MetaConcept("GG", "General Goal", "rqgoal", 100, 40, "/com/variamos/gui/refas/editor/images/goal.png", true, Color.BLUE.toString(), 2, true, semConcept);
			sGeneralGoal.addExtendMetaDirectRelation(DirectRelationType.means_ends, sGeneralGoal, sVariabilityArtifact, false);
			sMetaView.addConcept(sGeneralGoal);
			metaConcepts.put("GG", sGeneralGoal);
			
			semConcept = new InstSemanticConcept("Operationalization", operationalization);
			MetaConcept sOperationalization = new MetaConcept("OPER", "Operationalization", "rqoper", 100, 40, "/com/variamos/gui/refas/editor/images/operational.png", true, Color.BLUE.toString(), 2, true, semConcept);
			sOperationalization.addExtendMetaDirectRelation(DirectRelationType.means_ends, sOperationalization, sVariabilityArtifact, false);
			sMetaView.addConcept(sOperationalization);
			metaConcepts.put("GG", sGeneralGoal);
	
			semConcept = new InstSemanticConcept("Assumption", operationalization);
			MetaConcept sAssumption = new MetaConcept("ASSUM", "Assumption", "rqsassump", 100, 40, "/com/variamos/gui/refas/editor/images/assump.png", true, Color.WHITE.toString(), 1, true, semConcept);
			sAssumption.addExtendMetaDirectRelation(DirectRelationType.means_ends, sAssumption, sVariabilityArtifact, false);
			
			sMetaView.addConcept(sAssumption);

		sMetaView = new MetaView("SoftGoals", "Soft Goals Model", "Soft Goals Palette", 1);
		instView = new InstView("SoftGoals", sMetaView);
		instViews.add(instView);
		metaViews.add(sMetaView);
				
			semConcept = new InstSemanticConcept("TopSoftsoal",topSoftGoal);
			MetaConcept sTopSoftGoal = new MetaConcept("TSG", "Top Softgoal", "rqsoftgoal", 100, 40, "/com/variamos/gui/refas/editor/images/softgoal.png", true, Color.WHITE.toString(), 3, true, semConcept);
			sTopSoftGoal.addExtendMetaDirectRelation(DirectRelationType.means_ends, sTopSoftGoal, sAbstractConcept, false);
			sTopSoftGoal.addMetaAttribute("name","String","");
			sTopSoftGoal.addPanelVisibleAttributes("name");
			sTopSoftGoal.addPropEditableAttributes("name");
			sMetaView.addConcept(sTopSoftGoal);
			metaConcepts.put("TSG", sTopSoftGoal);
		
			semConcept = new InstSemanticConcept("GeneralSoftGoal", generalSoftGoal);
			MetaConcept sGeneralSoftGoal = new MetaConcept("GSG", "General Softgoal", "rqsoftgoal", 100, 40, "/com/variamos/gui/refas/editor/images/softgoal.png", true, Color.WHITE.toString(), 1, true, semConcept);
			sGeneralSoftGoal.addExtendMetaDirectRelation(DirectRelationType.means_ends, sGeneralSoftGoal, sAbstractConcept, false);
			sGeneralSoftGoal.addMetaAttribute("name","String","");
			sGeneralSoftGoal.addPanelVisibleAttributes("name");
			sGeneralSoftGoal.addPropEditableAttributes("name");
			sMetaView.addConcept(sGeneralSoftGoal);
			metaConcepts.put("TGG", sGeneralSoftGoal);

		sMetaView = new MetaView("Context", "Context Model", "Context Palette", 2);
		instView = new InstView("Context", sMetaView);
		instViews.add(instView);
		metaViews.add(sMetaView);
			
			//TODO define context model

		sMetaView = new MetaView("SoftGoalsSatisficing", "Soft Goals Satisficing Model", "Soft Goals Satisficing Palette", 3);
		instView = new InstView("SGSatis", sMetaView);
		instViews.add(instView);
		metaViews.add(sMetaView);

			semConcept = new InstSemanticConcept("Claim", claim);
			MetaConcept sClaim = new MetaConcept("CL", "Claim", "rqclaim", 100, 40, "/com/variamos/gui/refas/editor/images/claim.png", true, Color.BLUE.toString(), 1, true, semConcept);
			sClaim.addExtendMetaDirectRelation(DirectRelationType.means_ends, sClaim, sAbstractConcept, false);
			sClaim.addMetaAttribute("operationalizations","String","");
			sClaim.addPanelVisibleAttributes("operationalizations");
			sClaim.addPropEditableAttributes("operationalizations");
			sClaim.addPanelSpacersAttributes("\n");
			sClaim.addMetaAttribute("ConditionalExpression","String","");
			sClaim.addPanelVisibleAttributes("ConditionalExpression");
			sClaim.addPropEditableAttributes("ConditionalExpression");
			sMetaView.addConcept(sClaim);
			metaConcepts.put("CL", sClaim);			
			
			semConcept = new InstSemanticConcept("SoftDependency", softDependency);
			MetaConcept sSoftDependency = new MetaConcept("SD", "Soft Dependency", "rqsoftdep", 100, 40, "/com/variamos/gui/refas/editor/images/softdep.png", true, Color.BLUE.toString(), 1, true, semConcept);
			sSoftDependency.addExtendMetaDirectRelation(DirectRelationType.means_ends, sSoftDependency, sAbstractConcept, false);
			sSoftDependency.addMetaAttribute("ConditionalExpression","String","");
			sSoftDependency.addPanelVisibleAttributes("ConditionalExpression");
			sSoftDependency.addPropEditableAttributes("ConditionalExpression");
			sMetaView.addConcept(sSoftDependency);
			metaConcepts.put("SD", sSoftDependency);
			
		sMetaView = new MetaView("Assets", "Assets Model", "Assets Palette", 4);
		instView = new InstView("Assets", sMetaView);
		instViews.add(instView);
		metaViews.add(sMetaView);

			semConcept = new InstSemanticConcept("Asset", asset);
			MetaConcept sAsset = new MetaConcept("AS", "Asset", "rqcompon", 100, 40, "/com/variamos/gui/refas/editor/images/component.png", true, Color.WHITE.toString(), 1, true, semConcept);
			sAsset.addExtendMetaDirectRelation(DirectRelationType.means_ends, sAsset, sAbstractConcept, false);
			sAsset.addMetaAttribute("name","String","");
			sAsset.addPanelVisibleAttributes("name");
			sAsset.addPropEditableAttributes("name");
			sMetaView.addConcept(sAsset);
			metaConcepts.put("AS", sAsset);

	}
	
	public Map<String, MetaConcept> getMetaConcepts() {
		return metaConcepts;
	}
	
	public MetaConcept getMetaConcept(String name) {
		return metaConcepts.get(name);
	}

	public void setMetaConcepts(Map<String, MetaConcept> metaConcepts) {
		this.metaConcepts = metaConcepts;
	}

	public static void main(String[] args) {
		SemanticPlusSyntax  mst= new SemanticPlusSyntax();
		List<AbstractSemanticConcept> ascs = mst.getSemanticConcepts();
		for (int i = 0; i< ascs.size(); i++ )
			System.out.println(ascs.get(i));
	}



}
