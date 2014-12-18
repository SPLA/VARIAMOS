package com.variamos.gui.refas.editor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.variamos.refas.core.sematicsmetamodel.*;
import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.refas.core.types.GroupRelationType;
import com.variamos.syntaxsupport.metametamodel.*;
import com.variamos.syntaxsupport.semanticinterface.IntDirectEdgeType;
import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticEdge;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupDependency;

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
		IncomingSemanticEdge groupRelation = null;

		AbstractSemanticVertex semGeneralElement = new AbstractSemanticVertex();
		semanticConcepts.put("GE", semGeneralElement);

		
		//Design attributes
		
		semGeneralElement.putSemanticAttribute("Description",
				new SemanticAttribute("Description", "String", false,
						"Description", ""));
		semGeneralElement.addDisPropEditableAttribute("04#" + "Description");
		semGeneralElement.addDisPropVisibleAttribute("04#" + "Description");

		
		//Configuration attributes
				
		semGeneralElement.putSemanticAttribute("Active",
				new ConfigurationAttribute("Active", "Boolean", true, "Is Active",
						true));
		semGeneralElement.putSemanticAttribute("Visibility",
				new ConfigurationAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		semGeneralElement.putSemanticAttribute("Required",
				new ConfigurationAttribute("Required", "Boolean", true,
						"Is Required", false));
		semGeneralElement.putSemanticAttribute("Allowed",
				new ConfigurationAttribute("Allowed", "Boolean", false,
						"Is Allowed", true));
		semGeneralElement.putSemanticAttribute("RequiredLevel",
				new ConfigurationAttribute("RequiredLevel", "Integer", false,
						"Required Level", 0)); //TODO define domain or Enum Level
		
		semGeneralElement.addDisPropEditableAttribute("01#" + "Active");
		semGeneralElement.addDisPropEditableAttribute("02#" + "Visibility"+"#"+"Active"+"#==#"+"true");
		semGeneralElement.addDisPropEditableAttribute("03#" + "Allowed"+"#"+"Active"+"#==#"+"true");
		semGeneralElement.addDisPropEditableAttribute("04#" + "Required"+"#"+"Active"+"#==#"+"true");
		semGeneralElement.addDisPropEditableAttribute("05#" + "RequiredLevel"+"#"+"Required"+"#==#"+"true");
		
		semGeneralElement.addDisPropVisibleAttribute("01#" + "Active");
		semGeneralElement.addDisPropVisibleAttribute("02#" + "Visibility");
		semGeneralElement.addDisPropVisibleAttribute("03#" + "Allowed");
		semGeneralElement.addDisPropVisibleAttribute("04#" + "Required");
		semGeneralElement.addDisPropVisibleAttribute("05#" + "RequiredLevel"+"#"+"Required"+"#==#"+"true");
		
		//Simulation attributes
		
		semGeneralElement.putSemanticAttribute("ForcedSatisfied",
				new SimulationAttribute("ForcedSatisfied", "Boolean", false,	"Force Satisfaction", false));
		semGeneralElement.putSemanticAttribute("ForcedSelected",
				new SimulationAttribute("ForcedSelected", "Boolean", false,	"Force Selection", false));
		
		semGeneralElement.putSemanticAttribute("InitialRequiredLevel",
				new SimulationAttribute("InitialRequiredLevel", "Integer", false, "Initial Required Level", false));
		semGeneralElement.putSemanticAttribute("SimRequiredLevel",
				new SimulationAttribute("SimRequiredLevel", "Integer", false, "Required Level", false));
		semGeneralElement.putSemanticAttribute("ValidationRequiredLevel",
				new SimulationAttribute("ValidationRequiredLevel", "Integer", false, "Required Level by Validation", false));
		semGeneralElement.putSemanticAttribute("SimRequired",
				new SimulationAttribute("SimRequired", "Boolean", false, "Required", false));
		
		semGeneralElement.putSemanticAttribute("Satisfied",
				new SimulationAttribute("Satisfied", "Boolean", false, "Satisfied", false));
		semGeneralElement.putSemanticAttribute("AlternativeSatisfied",
				new SimulationAttribute("AlternativeSatisfied", "Boolean", false, "Satisfied by Alternatives", false));
		semGeneralElement.putSemanticAttribute("ValidationSatisfied",
				new SimulationAttribute("ValidationSatisfied", "Boolean", false, "Satisfied by Validation", false));
		semGeneralElement.putSemanticAttribute("SatisfiedLevel",
				new SimulationAttribute("SatisfiedLevel", "Integer", false, "Satisficing Level", false));

		semGeneralElement.putSemanticAttribute("Selected",
				new SimulationAttribute("Selected", "Boolean", false, "Selected", false));
		semGeneralElement.putSemanticAttribute("PreferredSelected",
				new SimulationAttribute("PreferredSelected", "Boolean", false, "Select by Preferred", true));
		semGeneralElement.putSemanticAttribute("ValidationSelected",
				new SimulationAttribute("ValidationSelected", "Boolean", false, "Selected by Validation", false));
		semGeneralElement.putSemanticAttribute("SolverSelected",
				new SimulationAttribute("SolverSelected", "Boolean", false, "Selected by Solver", false));
		
		semGeneralElement.putSemanticAttribute("Optional",
				new SimulationAttribute("Optional", "Boolean", false, "Is Optional", false));

		semGeneralElement.putSemanticAttribute("SimAllowed",
				new SimulationAttribute("SimAllowed", "Boolean", false, "Is Allowed", true));
				
		semGeneralElement.addDisPropEditableAttribute("10#"	+ "ForcedSatisfied"+"#"+"Active"+"#==#"+"true");	
		
		semGeneralElement.addDisPropEditableAttribute("15#" + "ForcedSelected"+"#"+"Active"+"#==#"+"true");
		
		semGeneralElement.addDisPropVisibleAttribute("02#" + "SimRequired");
		semGeneralElement.addDisPropVisibleAttribute("03#" + "SimRequiredLevel");
		semGeneralElement.addDisPropVisibleAttribute("04#" + "InitialRequiredLevel");
		semGeneralElement.addDisPropVisibleAttribute("05#" + "ValidationRequiredLevel");
		
		semGeneralElement.addDisPropVisibleAttribute("06#" + "Satisfied");
		semGeneralElement.addDisPropVisibleAttribute("07#" + "AlternativeSatisfied");
		semGeneralElement.addDisPropVisibleAttribute("08#" + "ValidationSatisfied");
		semGeneralElement.addDisPropVisibleAttribute("09#" + "SatisfiedLevel");
		semGeneralElement.addDisPropVisibleAttribute("10#"	+ "ForcedSatisfied");	
		
		
		semGeneralElement.addDisPropVisibleAttribute("11#" + "Selected");
		semGeneralElement.addDisPropVisibleAttribute("12#" + "PreferredSelected");
		semGeneralElement.addDisPropVisibleAttribute("13#" + "ValidationSelected");
		semGeneralElement.addDisPropVisibleAttribute("14#" + "SolverSelected");
		semGeneralElement.addDisPropVisibleAttribute("15#" + "ForcedSelected");
		
		semGeneralElement.addDisPropVisibleAttribute("16#" + "Optional");
		semGeneralElement.addDisPropVisibleAttribute("16#" + "SimAllowed");
		
		

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
				.add(new OutgoingSemanticEdge("", semHardConcept));

		
		// Feature concepts
		
		HardSemanticConcept semFeature = new HardSemanticConcept(semGeneralElement,
				"Feature");
		semanticConcepts.put("F", semFeature);
		
		SemanticContextGroup semFeatureGroup = new SemanticContextGroup(
				"ContextGroup");
		semanticConcepts.put("FCG", semFeatureGroup);
		
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

		SoftSemanticConcept semSoftgoal = new SoftSemanticConcept(
				semGeneralElement, "SoftGoal");
		semanticConcepts.put("SG", semSoftgoal);

		SemanticVariable semVariable = new SemanticVariable("Variable");
		semanticConcepts.put("VAR", semVariable);

		SemanticContextGroup semContextGroup = new SemanticContextGroup(
				"ContextGroup");
		semanticConcepts.put("CG", semContextGroup);

		HardSemanticConcept semAsset = new HardSemanticConcept(
				semGeneralElement, "Asset");
		semanticConcepts.put("ASSE", semAsset);

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

		semClaim.addDisPanelVisibleAttribute("01#" + "Operationalizations");
		semClaim.addDisPanelVisibleAttribute("03#" + "ConditionalExpression"); // TODO
																				// move
																				// to
																				// semantic
																				// attributes

		semClaim.addDisPropEditableAttribute("01#" + "Operationalizations");
		semClaim.addDisPropEditableAttribute("03#" + "ConditionalExpression");

		semClaim.addDisPropVisibleAttribute("01#" + "Operationalizations");
		semClaim.addDisPropVisibleAttribute("03#" + "ConditionalExpression");

		semClaim.addDisPanelSpacersAttribute("#" + "Operationalizations"
				+ "#\n#");

		SoftSemanticConceptSatisficing semSoftDependency = new SoftSemanticConceptSatisficing(
				semGeneralElement, "SoftDependency", false);
		semanticConcepts.put("SD", semSoftDependency);

		semSoftDependency.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression", "String", false,
						"Conditional Expression", ""));
		semSoftDependency.addDisPanelVisibleAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addDisPropEditableAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addDisPropVisibleAttribute("03#"
				+ "ConditionalExpression");

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
		
		//features relations
		List<GroupRelationType> featureMeansGroupRelation = new ArrayList<GroupRelationType>();
		featureMeansGroupRelation.add(GroupRelationType.means_ends);
		
		List<IntDirectEdgeType> FeatureDirectRelation = new ArrayList<IntDirectEdgeType>();
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

		List<IntDirectEdgeType> alternative_prefferedDirectRelation = new ArrayList<IntDirectEdgeType>();
		alternative_prefferedDirectRelation.add(DirectEdgeType.alternative);
		alternative_prefferedDirectRelation.add(DirectEdgeType.preferred);

		List<IntDirectEdgeType> alter_preff_impl_meansDirectRelation = new ArrayList<IntDirectEdgeType>();
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.alternative);
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.preferred);
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.implication);
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.means_ends);

		List<IntDirectEdgeType> allSGDirectRelation = new ArrayList<IntDirectEdgeType>();
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

		List<IntDirectEdgeType> implicationDirectRelation = new ArrayList<IntDirectEdgeType>();
		implicationDirectRelation.add(DirectEdgeType.implication);

		List<IntDirectEdgeType> means_endsImplicationDirectRelation = new ArrayList<IntDirectEdgeType>();
		means_endsImplicationDirectRelation.add(DirectEdgeType.means_ends);
		means_endsImplicationDirectRelation.add(DirectEdgeType.implication);

		List<IntDirectEdgeType> softdepDirectRelation = new ArrayList<IntDirectEdgeType>();
		softdepDirectRelation.add(DirectEdgeType.softdependency);
		
		List<IntDirectEdgeType> noneDirectRelation = new ArrayList<IntDirectEdgeType>();
		noneDirectRelation.add(DirectEdgeType.none);
		
		List<IntDirectEdgeType> claimDirectRelation = new ArrayList<IntDirectEdgeType>();
		claimDirectRelation.add(DirectEdgeType.claim);

		List<GroupRelationType> implementationGroupRelation = new ArrayList<GroupRelationType>();
		implementationGroupRelation.add(GroupRelationType.implementation);

		List<IntDirectEdgeType> implementationDirectRelation = new ArrayList<IntDirectEdgeType>();
		implementationDirectRelation.add(DirectEdgeType.implementation);

		// required and conflict group relations of the HardSemanticConcept
		List<GroupRelationType> requires_conflictsGroupRelation = new ArrayList<GroupRelationType>();
		requires_conflictsGroupRelation.add(GroupRelationType.required);
		requires_conflictsGroupRelation.add(GroupRelationType.conflict);

		SemanticGroupDependency semanticHardHardGroupRelation = new SemanticGroupDependency(
				"HardHardGroupRel", false, requires_conflictsGroupRelation,
				semHardOutgoingRelation);
		groupRelation = new IncomingSemanticEdge("",
				semanticHardHardGroupRelation);
		semHardConcept.addGroupRelation(groupRelation);
		semanticConcepts.put("HardHardGroupRel", semanticHardHardGroupRelation);
		
		
		// required and conflict direct relations of the HardSemanticConcept
		List<IntDirectEdgeType> requires_conflictsDirectRelation = new ArrayList<IntDirectEdgeType>();
		requires_conflictsDirectRelation.add(DirectEdgeType.required);
		requires_conflictsDirectRelation.add(DirectEdgeType.conflict);

		List<AbstractSemanticVertex> semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semHardConcept);

		DirectSemanticEdge directHardHardSemanticEdge = new DirectSemanticEdge(
				"HardHardDirectEdge", false, requires_conflictsDirectRelation,
				semanticVertexs, false);
		semHardConcept.addDirectRelation(directHardHardSemanticEdge);
		semanticConcepts.put("HardHardDirectEdge", directHardHardSemanticEdge);

		//Feature to Feature
		
		List<OutgoingSemanticEdge> outgoingFeatureRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingFeatureRelation.add(new OutgoingSemanticEdge("", semFeature));
		SemanticGroupDependency semanticFeatureFeatureGroupRelation = new SemanticGroupDependency(
				"FeatureFeatureGroupRel", false, featureMeansGroupRelation,
				outgoingFeatureRelation);
		groupRelation = new IncomingSemanticEdge("",
				semanticFeatureFeatureGroupRelation);
		semFeature.addGroupRelation(groupRelation);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semFeature);
		
		DirectSemanticEdge directFeatureFeatureSemanticEdge = new DirectSemanticEdge(
				"FeatureFeatureDirectEdge", false, false, semanticVertexs,
				alter_preff_impl_meansDirectRelation);
		semGoal.addDirectRelation(directFeatureFeatureSemanticEdge);
		semanticConcepts.put("FeauteFeateuGroupRel", semanticFeatureFeatureGroupRelation);
		semanticConcepts.put("FeauteFeatureDirectEdge", directFeatureFeatureSemanticEdge);

		
		// Goal to Goal

		List<OutgoingSemanticEdge> outgoingGoalRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingGoalRelation.add(new OutgoingSemanticEdge("", semGoal));
		SemanticGroupDependency semanticGoalGoalGroupRelation = new SemanticGroupDependency(
				"GoalGoalGroupRel", false, altern_impl_meansGroupRelation,
				outgoingGoalRelation);
		groupRelation = new IncomingSemanticEdge("",
				semanticGoalGoalGroupRelation);
		semGoal.addGroupRelation(groupRelation);

		
		semanticVertexs.add(semGoal);

		DirectSemanticEdge directGoalGoalSemanticEdge = new DirectSemanticEdge(
				"GoalGoalDirectEdge", false, false, semanticVertexs,
				alter_preff_impl_meansDirectRelation);
		semGoal.addDirectRelation(directGoalGoalSemanticEdge);
		semanticConcepts.put("GoalGoalGroupRel", semanticGoalGoalGroupRelation);
		semanticConcepts.put("GoalGoalDirectEdge", directGoalGoalSemanticEdge);

		// Oper to Goal and Oper
		List<OutgoingSemanticEdge> outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingOperationalizationRelation.add(new OutgoingSemanticEdge("",
				semGoal));
		outgoingOperationalizationRelation.add(new OutgoingSemanticEdge("",
				semOperationalization));
		SemanticGroupDependency semanticOperGoalGroupRelation = new SemanticGroupDependency(
				"OPerGoalGroupRel", false, means_endsImplicationGroupRelation,
				outgoingOperationalizationRelation);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semOperationalization);
		semanticVertexs.add(semGoal);

		DirectSemanticEdge directOperGoalSemanticEdge = new DirectSemanticEdge(
				"OperGoalDirectEdge", false, false, semanticVertexs,
				means_endsImplicationDirectRelation);
		groupRelation = new IncomingSemanticEdge("",
				semanticOperGoalGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);
		semOperationalization.addDirectRelation(directOperGoalSemanticEdge);
		semanticConcepts.put("OPerGoalGroupRel", semanticOperGoalGroupRelation);
		semanticConcepts.put("OperGoalDirectEdge", directOperGoalSemanticEdge);

		// Oper to Oper
		outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingOperationalizationRelation.add(new OutgoingSemanticEdge("",
				semOperationalization));
		SemanticGroupDependency semanticOperOperGroupRelation = new SemanticGroupDependency(
				"OperOperGroupRel", false, alternativeGroupRelation,
				outgoingOperationalizationRelation);
		groupRelation = new IncomingSemanticEdge("",
				semanticOperOperGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semOperationalization);

		DirectSemanticEdge directOperOperSemanticEdge = new DirectSemanticEdge(
				"OperOperDirectEdge", false, false, semanticVertexs,
				alternative_prefferedDirectRelation);
		semOperationalization.addDirectRelation(directOperOperSemanticEdge);
		semanticConcepts.put("OperOperGroupRel", semanticOperOperGroupRelation);
		semanticConcepts.put("OperOperDirectEdge", directOperOperSemanticEdge);

		// SG to SG
		List<OutgoingSemanticEdge> outgoingSoftgoalRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingSoftgoalRelation.add(new OutgoingSemanticEdge("", true,
				semSoftgoal));
		SemanticGroupDependency semanticSGSGGroupRelation = new SemanticGroupDependency(
				"SGSGGroupRel", false, allSGGroupRelation,
				outgoingSoftgoalRelation);
		groupRelation = new IncomingSemanticEdge("SGSGGroupRel", semanticSGSGGroupRelation);
		semSoftgoal.addGroupRelation(groupRelation);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semSoftgoal);

		DirectSemanticEdge directSGSGSemEdge = new DirectSemanticEdge(
				"SGSGDirectEdge", true, false, semanticVertexs,
				allSGDirectRelation);
		directSGSGSemEdge.putSemanticAttribute(AbstractSemanticEdge.VAR_LEVEL,
				new SemanticAttribute(AbstractSemanticEdge.VAR_LEVEL,
						"Enumeration", false,
						AbstractSemanticEdge.VAR_LEVELNAME,
						AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		directSGSGSemEdge.addDisPropEditableAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directSGSGSemEdge.addDisPropVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directSGSGSemEdge.addDisPanelVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		semSoftgoal.addDirectRelation(directSGSGSemEdge);
		semanticConcepts.put("SGSGGroupRel", semanticSGSGGroupRelation);
		semanticConcepts.put("SGSGDirectEdge", directSGSGSemEdge);

		// CV to CG
		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semContextGroup);

		DirectSemanticEdge directCVCGSemanticEdge = new DirectSemanticEdge(
				"CVCGDirectRel", false, false, semanticVertexs,
				noneDirectRelation);
		semVariable.addDirectRelation(directCVCGSemanticEdge);
		semanticConcepts.put("CVCGDirectRel", directCVCGSemanticEdge);

		// Oper to Claim
		outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingSoftgoalRelation.add(new OutgoingSemanticEdge("outclaim", semClaim));
		SemanticGroupDependency semanticOperClaimGroupRelation = new SemanticGroupDependency(
				"OperClaimGroupRel", true, implicationGroupRelation,
				outgoingSoftgoalRelation);
		groupRelation = new IncomingSemanticEdge("",
				semanticOperClaimGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semClaim);

		DirectSemanticEdge directOperClaimSemanticEdge = new DirectSemanticEdge(
				"OperClaimDirectEdge", true, true, semanticVertexs,
				implicationDirectRelation);
		semOperationalization.addDirectRelation(directOperClaimSemanticEdge);
		directOperClaimSemanticEdge.putSemanticAttribute(
				AbstractSemanticEdge.VAR_LEVEL, new SemanticAttribute(
						AbstractSemanticEdge.VAR_LEVEL, "Enumeration", false,
						AbstractSemanticEdge.VAR_LEVEL,
						AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		directOperClaimSemanticEdge.addDisPropEditableAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directOperClaimSemanticEdge.addDisPropVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directOperClaimSemanticEdge.addDisPanelVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		semClaim.addDirectRelation(directOperClaimSemanticEdge);
		semanticConcepts.put("OperClaimGroupRel",
				semanticOperClaimGroupRelation);
		semanticConcepts
				.put("OperClaimDirectEdge", directOperClaimSemanticEdge);

		// Claim to SG

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semSoftgoal);

		DirectSemanticEdge directClaimSGSemanticEdge = new DirectSemanticEdge(
				"ClaimSGDirectEdge", true, true, semanticVertexs,
				claimDirectRelation);
		directClaimSGSemanticEdge.putSemanticAttribute(
				AbstractSemanticEdge.VAR_LEVEL, new SemanticAttribute(
						AbstractSemanticEdge.VAR_LEVEL, "Enumeration", false,
						AbstractSemanticEdge.VAR_LEVEL,
						AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		directClaimSGSemanticEdge.addDisPropEditableAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directClaimSGSemanticEdge.addDisPropVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directClaimSGSemanticEdge.addDisPanelVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		semClaim.addDirectRelation(directClaimSGSemanticEdge);
		semanticConcepts.put("ClaimSGDirectEdge", directClaimSGSemanticEdge);

		// SD to SG

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semSoftgoal);

		DirectSemanticEdge directSDSGSemanticEdge = new DirectSemanticEdge(
				"SDSGDirectEdge", true, true, semanticVertexs,
				softdepDirectRelation);
		directSDSGSemanticEdge.putSemanticAttribute(
				AbstractSemanticEdge.VAR_LEVEL, new SemanticAttribute(
						AbstractSemanticEdge.VAR_LEVEL, "Enumeration", false,
						AbstractSemanticEdge.VAR_LEVELNAME,
						AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		directSDSGSemanticEdge.addDisPropEditableAttribute("04#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directSDSGSemanticEdge.addDisPropVisibleAttribute("04#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directSDSGSemanticEdge.addDisPanelVisibleAttribute("04#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		semSoftDependency.addDirectRelation(directSDSGSemanticEdge);
		semanticConcepts.put("SDSGDirectEdge", directSDSGSemanticEdge);

		// Asset to Oper
		List<OutgoingSemanticEdge> outgoingAssetRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingAssetRelation.add(new OutgoingSemanticEdge("outoper",
				semOperationalization));
		SemanticGroupDependency semanticAssetOperGroupRelation = new SemanticGroupDependency(
				"AssetOperGroupRel", false, implementationGroupRelation,
				outgoingAssetRelation);
		groupRelation = new IncomingSemanticEdge("AssetOperGroupRel",
				semanticAssetOperGroupRelation);
		semSoftgoal.addGroupRelation(groupRelation);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semOperationalization);

		DirectSemanticEdge directAssetOperSemanticEdge = new DirectSemanticEdge(
				"AssetOperDirectEdge", false, true, semanticVertexs,
				implementationDirectRelation);
		semAsset.addDirectRelation(directAssetOperSemanticEdge);
		semanticConcepts.put("AssetOperGroupRel",
				semanticAssetOperGroupRelation);
		semanticConcepts
				.put("AssetOperDirectEdge", directAssetOperSemanticEdge);

		// TODO: structural and functional dependency relations

		// *************************---------------****************************
		// Our MetaModel objects definition

		MetaView syntaxMetaView = null;

		// *************************---------------****************************
		// Goals and avariability model

		
		syntaxMetaView = new MetaView("GoalsAndVaribilityModel",
				"Goals and Variability Model", "Goals and Variability Palette",
				1);
		
		MetaConcept syntaxFeature = new MetaConcept("F", true,
				"Feature", "plnode", "Defines a feature", 100, 40,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, true, semFeature);
		syntaxFeature.addModelingAttribute("name", "String", false,
				"Name", "");
		
		syntaxElements.put("F", syntaxFeature);	
		syntaxMetaView.addConcept(syntaxFeature);

		syntaxFeature.addDisPanelVisibleAttribute("03#" + "name");

		syntaxFeature.addDisPropEditableAttribute("03#" + "name");

		syntaxFeature.addDisPropVisibleAttribute("03#" + "name");
		
		//Feature direct relations
		
		List<IntDirectSemanticEdge> directFeatureSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		directFeatureSemanticEdges.add(directFeatureFeatureSemanticEdge);
		
		MetaDirectRelation metaFeatureEdge = new MetaDirectRelation("Feature Relation", false,
				"Feature Relation", "plnode", "Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				syntaxFeature, syntaxFeature,
				directFeatureSemanticEdges, allSGDirectRelation);
		syntaxFeature.addMetaEdgeAsOrigin(
				syntaxFeature, metaFeatureEdge);
		syntaxElements.put("Feature Relation", metaFeatureEdge);		
		syntaxMetaView.addConcept(metaFeatureEdge);
		
		// Group Feature Relations

		List<IntSemanticGroupDependency> semanticFeatRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticFeatRelations.add(semanticFeatureFeatureGroupRelation);

		MetaGroupDependency syntaxFeatureGroupDep = new MetaGroupDependency(
				"FeatGroupDep", true, "FeatGroupDep", "plgroup","Group relation between"
						+ " Feature concepts. Defines different types of"
						+ " cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticFeatRelations);

		syntaxMetaView.addConcept(syntaxFeatureGroupDep);
		syntaxElements.put("FeatGroupDep", syntaxFeatureGroupDep);
				
		syntaxFeatureGroupDep.addModelingAttribute("Active",
				new ConfigurationAttribute("Active", "Boolean", true, "Is Active",
						true));
		syntaxFeatureGroupDep.addModelingAttribute("Visibility",
				new ConfigurationAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		syntaxFeatureGroupDep.addModelingAttribute("Required",
				new ConfigurationAttribute("Required", "Boolean", true,
						"Is Required", false));
		syntaxFeatureGroupDep.addModelingAttribute("Allowed",
				new ConfigurationAttribute("Allowed", "Boolean", false,
						"Is Allowed", true));
		syntaxFeatureGroupDep.addModelingAttribute("RequiredLevel",
				new ConfigurationAttribute("RequiredLevel", "Integer", false,
						"Required Level", 0)); //TODO define domain or Enum Level
		
		syntaxFeatureGroupDep.addDisPropEditableAttribute("01#" + "Active");
		syntaxFeatureGroupDep.addDisPropEditableAttribute("02#" + "Visibility"+"#"+"Active"+"#==#"+"true");
		syntaxFeatureGroupDep.addDisPropEditableAttribute("03#" + "Allowed"+"#"+"Active"+"#==#"+"true");
		syntaxFeatureGroupDep.addDisPropEditableAttribute("04#" + "Required"+"#"+"Active"+"#==#"+"true");
		syntaxFeatureGroupDep.addDisPropEditableAttribute("05#" + "RequiredLevel"+"#"+"Required"+"#==#"+"true");
		
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("01#" + "Active");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("02#" + "Visibility");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("03#" + "Allowed");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("04#" + "Required");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("05#" + "RequiredLevel"+"#"+"Required"+"#==#"+"true");
		
		//Simulation attributes
		
		syntaxFeatureGroupDep.addModelingAttribute("ForcedSatisfied",
				new SimulationAttribute("ForcedSatisfied", "Boolean", false,	"Force Satisfaction", false));
		syntaxFeatureGroupDep.addModelingAttribute("ForcedSelected",
				new SimulationAttribute("ForcedSelected", "Boolean", false,	"Force Selection", false));
		
		syntaxFeatureGroupDep.addModelingAttribute("InitialRequiredLevel",
				new SimulationAttribute("InitialRequiredLevel", "Integer", false, "Initial Required Level", false));
		syntaxFeatureGroupDep.addModelingAttribute("SimRequiredLevel",
				new SimulationAttribute("SimRequiredLevel", "Integer", false, "Required Level", false));
		syntaxFeatureGroupDep.addModelingAttribute("ValidationRequiredLevel",
				new SimulationAttribute("ValidationRequiredLevel", "Integer", false, "Required Level by Validation", false));
		syntaxFeatureGroupDep.addModelingAttribute("SimRequired",
				new SimulationAttribute("SimRequired", "Boolean", false, "Required", false));
		
		syntaxFeatureGroupDep.addModelingAttribute("Satisfied",
				new SimulationAttribute("Satisfied", "Boolean", false, "Satisfied", false));
		syntaxFeatureGroupDep.addModelingAttribute("AlternativeSatisfied",
				new SimulationAttribute("AlternativeSatisfied", "Boolean", false, "Satisfied by Alternatives", false));
		syntaxFeatureGroupDep.addModelingAttribute("ValidationSatisfied",
				new SimulationAttribute("ValidationSatisfied", "Boolean", false, "Satisfied by Validation", false));
		syntaxFeatureGroupDep.addModelingAttribute("SatisfiedLevel",
				new SimulationAttribute("SatisfiedLevel", "Integer", false, "Satisficing Level", false));

		syntaxFeatureGroupDep.addModelingAttribute("Selected",
				new SimulationAttribute("Selected", "Boolean", false, "Selected", false));
		syntaxFeatureGroupDep.addModelingAttribute("PreferredSelected",
				new SimulationAttribute("PreferredSelected", "Boolean", false, "Select by Preferred", true));
		syntaxFeatureGroupDep.addModelingAttribute("ValidationSelected",
				new SimulationAttribute("ValidationSelected", "Boolean", false, "Selected by Validation", false));
		syntaxFeatureGroupDep.addModelingAttribute("SolverSelected",
				new SimulationAttribute("SolverSelected", "Boolean", false, "Selected by Solver", false));
		
		syntaxFeatureGroupDep.addModelingAttribute("Optional",
				new SimulationAttribute("Optional", "Boolean", false, "Is Optional", false));

		syntaxFeatureGroupDep.addModelingAttribute("SimAllowed",
				new SimulationAttribute("SimAllowed", "Boolean", false, "Is Allowed", true));
				
		syntaxFeatureGroupDep.addDisPropEditableAttribute("10#"	+ "ForcedSatisfied"+"#"+"Active"+"#==#"+"true");	
		
		syntaxFeatureGroupDep.addDisPropEditableAttribute("15#" + "ForcedSelected"+"#"+"Active"+"#==#"+"true");
		
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("02#" + "SimRequired");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("03#" + "SimRequiredLevel");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("04#" + "InitialRequiredLevel");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("05#" + "ValidationRequiredLevel");
		
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("06#" + "Satisfied");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("07#" + "AlternativeSatisfied");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("08#" + "ValidationSatisfied");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("09#" + "SatisfiedLevel");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("10#"	+ "ForcedSatisfied");	
		
		
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("11#" + "Selected");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("12#" + "PreferredSelected");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("13#" + "ValidationSelected");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("14#" + "SolverSelected");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("15#" + "ForcedSelected");
		
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("16#" + "Optional");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("16#" + "SimAllowed");
		


		MetaConcept syntaxVariabilityArtifact = new MetaConcept("VA", false,
				"VariabilityArtifact", null, "", 0, 0, null, true, null, 3, true,
				semHardConcept);
		syntaxVariabilityArtifact.addModelingAttribute("name", "String", false,
				"Name", "");

		syntaxVariabilityArtifact.addDisPanelVisibleAttribute("03#" + "name");

		syntaxVariabilityArtifact.addDisPropEditableAttribute("03#" + "name");

		syntaxVariabilityArtifact.addDisPropVisibleAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxVariabilityArtifact);
		syntaxElements.put("VA", syntaxVariabilityArtifact);

		metaViews.add(syntaxMetaView);

		MetaConcept syntaxTopGoal = new MetaConcept("TopGoal", true,
				"Top Goal", "refasgoal", "Defines a top goal of the system"
						+ " from the stakeholder perspective that can be"
						+ " satisfied with a clear cut condition", 100, 40,
				"/com/variamos/gui/refas/editor/images/goal.png", true,
				Color.BLUE.toString(), 3, true, semGoal);
		System.out.println("TopGoal: "
				+ syntaxTopGoal.getModelingAttributes().toString());
		syntaxTopGoal.addMetaExtendRelation(syntaxVariabilityArtifact, false);

		syntaxMetaView.addConcept(syntaxTopGoal);
		syntaxElements.put("TopGoal", syntaxTopGoal);

		MetaConcept syntaxGeneralGoal = new MetaConcept("GeneralGoal", true,
				"General Goal", "refasgoal", "Defines a general goal of the"
						+ " system from the stakeholder perspective that can"
						+ " be satisfied with a clear cut condition", 100, 40,
				"/com/variamos/gui/refas/editor/images/goal.png", true,
				Color.BLUE.toString(), 2, true, semGoal);
		syntaxGeneralGoal.addMetaExtendRelation(syntaxVariabilityArtifact,
				false);

		syntaxMetaView.addConcept(syntaxGeneralGoal);
		syntaxElements.put("GeneralGoal", syntaxGeneralGoal);

		MetaConcept sOperationalization = new MetaConcept("OPER", true,
				"Operationalization", "refasoper","An operationalization allows"
						+ " the partial or complete satisfaction of a goal or"
						+ " another operationalization. If"
						+ " the operationalizations defined is satisfied,"
						+ " according to the defined relation, the goal"
						+ " associated will be also satisfied", 100, 40,
				"/com/variamos/gui/refas/editor/images/operational.png", true,
				Color.BLUE.toString(), 2, true, semOperationalization);
		sOperationalization.addMetaExtendRelation(syntaxVariabilityArtifact,
				false);

		syntaxMetaView.addConcept(sOperationalization);
		syntaxElements.put("OPER", sOperationalization);

		MetaConcept syntaxAssumption = new MetaConcept("Assumption", true,
				"semanticAssumption", "refassassump","An assumption is a"
						+ " condition that should me truth for the goal or"
						+ " operationalization to be satisfied", 100, 40,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.WHITE.toString(), 1, true, semAssumption);
		syntaxAssumption
				.addMetaExtendRelation(syntaxVariabilityArtifact, false);

		syntaxMetaView.addConcept(syntaxAssumption);
		syntaxElements.put("Assumption", syntaxAssumption);

		// Direct Hard Relations

		List<IntDirectSemanticEdge> directHardSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		directHardSemanticEdges.add(directHardHardSemanticEdge);
		directHardSemanticEdges.add(directGoalGoalSemanticEdge);
		directHardSemanticEdges.add(directOperGoalSemanticEdge);
		directHardSemanticEdges.add(directOperOperSemanticEdge);

		MetaDirectRelation metaHardEdge = new MetaDirectRelation("HardRelation", true,
				"HardRelation", "ploptional", "Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cartinalities", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxVariabilityArtifact, syntaxVariabilityArtifact,
				directHardSemanticEdges, allSGDirectRelation);
		syntaxVariabilityArtifact.addMetaEdgeAsOrigin(
				syntaxVariabilityArtifact, metaHardEdge);
		syntaxElements.put("HardRelation", metaHardEdge);

		// Group Hard Relations

		List<IntSemanticGroupDependency> semanticRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticRelations.add(semanticGoalGoalGroupRelation);
		semanticRelations.add(semanticOperGoalGroupRelation);
		semanticRelations.add(semanticOperOperGroupRelation);
		semanticRelations.add(semanticHardHardGroupRelation);

		MetaGroupDependency syntaxGroupDependency = new MetaGroupDependency(
				"HardGroupDep", true, "HardGroupDep", "plgroup","Group relation between"
						+ " hard concepts. Defines different types of"
						+ " relations and cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);

		syntaxMetaView.addConcept(syntaxGroupDependency);
		syntaxElements.put("HardGroupDep", syntaxGroupDependency);
		
		
		syntaxGroupDependency.addModelingAttribute("Active",
				new ConfigurationAttribute("Active", "Boolean", true, "Is Active",
						true));
		syntaxGroupDependency.addModelingAttribute("Visibility",
				new ConfigurationAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		syntaxGroupDependency.addModelingAttribute("Required",
				new ConfigurationAttribute("Required", "Boolean", true,
						"Is Required", false));
		syntaxGroupDependency.addModelingAttribute("Allowed",
				new ConfigurationAttribute("Allowed", "Boolean", false,
						"Is Allowed", true));
		syntaxGroupDependency.addModelingAttribute("RequiredLevel",
				new ConfigurationAttribute("RequiredLevel", "Integer", false,
						"Required Level", 0)); //TODO define domain or Enum Level
		
		syntaxGroupDependency.addDisPropEditableAttribute("01#" + "Active");
		syntaxGroupDependency.addDisPropEditableAttribute("02#" + "Visibility"+"#"+"Active"+"#==#"+"true");
		syntaxGroupDependency.addDisPropEditableAttribute("03#" + "Allowed"+"#"+"Active"+"#==#"+"true");
		syntaxGroupDependency.addDisPropEditableAttribute("04#" + "Required"+"#"+"Active"+"#==#"+"true");
		syntaxGroupDependency.addDisPropEditableAttribute("05#" + "RequiredLevel"+"#"+"Required"+"#==#"+"true");
		
		syntaxGroupDependency.addDisPropVisibleAttribute("01#" + "Active");
		syntaxGroupDependency.addDisPropVisibleAttribute("02#" + "Visibility");
		syntaxGroupDependency.addDisPropVisibleAttribute("03#" + "Allowed");
		syntaxGroupDependency.addDisPropVisibleAttribute("04#" + "Required");
		syntaxGroupDependency.addDisPropVisibleAttribute("05#" + "RequiredLevel"+"#"+"Required"+"#==#"+"true");
		
		//Simulation attributes
		
		syntaxGroupDependency.addModelingAttribute("ForcedSatisfied",
				new SimulationAttribute("ForcedSatisfied", "Boolean", false,	"Force Satisfaction", false));
		syntaxGroupDependency.addModelingAttribute("ForcedSelected",
				new SimulationAttribute("ForcedSelected", "Boolean", false,	"Force Selection", false));
		
		syntaxGroupDependency.addModelingAttribute("InitialRequiredLevel",
				new SimulationAttribute("InitialRequiredLevel", "Integer", false, "Initial Required Level", false));
		syntaxGroupDependency.addModelingAttribute("SimRequiredLevel",
				new SimulationAttribute("SimRequiredLevel", "Integer", false, "Required Level", false));
		syntaxGroupDependency.addModelingAttribute("ValidationRequiredLevel",
				new SimulationAttribute("ValidationRequiredLevel", "Integer", false, "Required Level by Validation", false));
		syntaxGroupDependency.addModelingAttribute("SimRequired",
				new SimulationAttribute("SimRequired", "Boolean", false, "Required", false));
		
		syntaxGroupDependency.addModelingAttribute("Satisfied",
				new SimulationAttribute("Satisfied", "Boolean", false, "Satisfied", false));
		syntaxGroupDependency.addModelingAttribute("AlternativeSatisfied",
				new SimulationAttribute("AlternativeSatisfied", "Boolean", false, "Satisfied by Alternatives", false));
		syntaxGroupDependency.addModelingAttribute("ValidationSatisfied",
				new SimulationAttribute("ValidationSatisfied", "Boolean", false, "Satisfied by Validation", false));
		syntaxGroupDependency.addModelingAttribute("SatisfiedLevel",
				new SimulationAttribute("SatisfiedLevel", "Integer", false, "Satisficing Level", false));

		syntaxGroupDependency.addModelingAttribute("Selected",
				new SimulationAttribute("Selected", "Boolean", false, "Selected", false));
		syntaxGroupDependency.addModelingAttribute("PreferredSelected",
				new SimulationAttribute("PreferredSelected", "Boolean", false, "Select by Preferred", true));
		syntaxGroupDependency.addModelingAttribute("ValidationSelected",
				new SimulationAttribute("ValidationSelected", "Boolean", false, "Selected by Validation", false));
		syntaxGroupDependency.addModelingAttribute("SolverSelected",
				new SimulationAttribute("SolverSelected", "Boolean", false, "Selected by Solver", false));
		
		syntaxGroupDependency.addModelingAttribute("Optional",
				new SimulationAttribute("Optional", "Boolean", false, "Is Optional", false));

		syntaxGroupDependency.addModelingAttribute("SimAllowed",
				new SimulationAttribute("SimAllowed", "Boolean", false, "Is Allowed", true));
				
		syntaxGroupDependency.addDisPropEditableAttribute("10#"	+ "ForcedSatisfied"+"#"+"Active"+"#==#"+"true");	
		
		syntaxGroupDependency.addDisPropEditableAttribute("15#" + "ForcedSelected"+"#"+"Active"+"#==#"+"true");
		
		syntaxGroupDependency.addDisPropVisibleAttribute("02#" + "SimRequired");
		syntaxGroupDependency.addDisPropVisibleAttribute("03#" + "SimRequiredLevel");
		syntaxGroupDependency.addDisPropVisibleAttribute("04#" + "InitialRequiredLevel");
		syntaxGroupDependency.addDisPropVisibleAttribute("05#" + "ValidationRequiredLevel");
		
		syntaxGroupDependency.addDisPropVisibleAttribute("06#" + "Satisfied");
		syntaxGroupDependency.addDisPropVisibleAttribute("07#" + "AlternativeSatisfied");
		syntaxGroupDependency.addDisPropVisibleAttribute("08#" + "ValidationSatisfied");
		syntaxGroupDependency.addDisPropVisibleAttribute("09#" + "SatisfiedLevel");
		syntaxGroupDependency.addDisPropVisibleAttribute("10#"	+ "ForcedSatisfied");	
		
		
		syntaxGroupDependency.addDisPropVisibleAttribute("11#" + "Selected");
		syntaxGroupDependency.addDisPropVisibleAttribute("12#" + "PreferredSelected");
		syntaxGroupDependency.addDisPropVisibleAttribute("13#" + "ValidationSelected");
		syntaxGroupDependency.addDisPropVisibleAttribute("14#" + "SolverSelected");
		syntaxGroupDependency.addDisPropVisibleAttribute("15#" + "ForcedSelected");
		
		syntaxGroupDependency.addDisPropVisibleAttribute("16#" + "Optional");
		syntaxGroupDependency.addDisPropVisibleAttribute("16#" + "SimAllowed");
		
		

		// *************************---------------****************************
		// Softgoals model

		syntaxMetaView = new MetaView("SoftGoals", "Soft Goals Model",
				"Soft Goals Palette", 2);
		metaViews.add(syntaxMetaView);

		MetaConcept syntaxAbsSoftGoal = new MetaConcept("Softgoal", false,
				"Softgoal", "", null, 0, 0, null, true, null, 3, true, semSoftgoal);

		syntaxAbsSoftGoal.addModelingAttribute("name", "String", false, "Name",
				"");
		syntaxAbsSoftGoal.addDisPanelVisibleAttribute("03#" + "name");

		syntaxAbsSoftGoal.addDisPropEditableAttribute("03#" + "name");
		syntaxAbsSoftGoal.addDisPropVisibleAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxAbsSoftGoal);
		syntaxElements.put("Softgoal", syntaxAbsSoftGoal);

		MetaConcept syntaxTopSoftGoal = new MetaConcept("TopSoftgoal", true,
				"Top Softgoal", "refassoftgoal","Defines a top goal of the"
						+ " system from the stakeholder"
						+ " perspective that can at most be satisficed without"
						+ " a clear cut verification. Given the satisficing problem,"
						+ " it includes an scale of levels of satisfaction/denial."
						+ " The SG satisficing level can be measured globally or"
						+ " locally, for the system or for each user, depending"	
						+ " on the SG",  100, 40,
				"/com/variamos/gui/refas/editor/images/softgoal.png", true,
				Color.WHITE.toString(), 3, true, semSoftgoal);

		syntaxTopSoftGoal.addMetaExtendRelation(syntaxAbsSoftGoal, false);

		syntaxMetaView.addConcept(syntaxTopSoftGoal);
		syntaxElements.put("TopSoftgoal", syntaxTopSoftGoal);

		MetaConcept syntaxGeneralSoftGoal = new MetaConcept("GeneralSSoftgoal",
				true, "General Softgoal", "refassoftgoal", "Defines a general"
						+ " softgoal of the system from the stakeholder"
						+ " perspective that can at most be satisficed without"
						+ " a clear cut verification. Given the satisficing problem,"
						+ " it includes an scale of levels of satisfaction/denial."
						+ " The SG satisficing level can be measured globally or"
						+ " locally, for the system or for each user, depending"	
						+ " on the SG", 100, 40,
				"/com/variamos/gui/refas/editor/images/softgoal.png", true,
				Color.WHITE.toString(), 1, true, semSoftgoal);

		syntaxGeneralSoftGoal.addMetaExtendRelation(syntaxAbsSoftGoal, false);
		syntaxMetaView.addConcept(syntaxGeneralSoftGoal);
		syntaxElements.put("GeneralSSoftgoal", syntaxGeneralSoftGoal);

		// Direct Soft relation

		List<IntDirectSemanticEdge> directSoftSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		directSoftSemanticEdges.add(directSGSGSemEdge);

		MetaDirectRelation metaSoftEdge = new MetaDirectRelation(
				"Soft Relation", true, "Soft Relation", "ploptional",
				"Direct relation between two soft concepts. Defines"
				+ " different types of relations and cartinalities", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAbsSoftGoal, syntaxAbsSoftGoal, directSoftSemanticEdges,
				allSGDirectRelation);
		syntaxAbsSoftGoal.addMetaEdgeAsOrigin(syntaxAbsSoftGoal, metaSoftEdge);
		syntaxElements.put("Soft Relation", metaSoftEdge);

		semanticRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticRelations.add(semanticSGSGGroupRelation);

		// Group soft relation

		syntaxGroupDependency = new MetaGroupDependency("SoftgoalGroupDep", true,
				"SoftgoalGroupDep", "plgroup", "Direct relation between soft"
						+ " concepts. Defines different types of relations"
						+ " and cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);

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

		syntaxMetaView.addConcept(syntaxContextGroup);
		syntaxElements.put("CG", syntaxContextGroup);

		MetaConcept syntaxAbsVariable = new MetaConcept("Variable", false,
				"Variable","", null, 0, 0, null, true, null, 1, true, semVariable);

		syntaxElements.put("Variable", syntaxAbsVariable);

		MetaConcept syntaxGlobalVariable = new MetaConcept("GlobalVariable",
				true, "Global Variable", "refasglobcnxt", "A global variable"
						+ " is an abstraction of a variable or component of the"
						+ " system or the environment that are relevant the system."
						+ " The relevance applies to the system in general."
						+ " The variable values should be"
						+ " simplified as much as possible considering the modeling"
						+ " requirements", 150, 40,
				"/com/variamos/gui/refas/editor/images/globCnxtVar.png", true,
				Color.BLUE.toString(), 1, true, semVariable);

		syntaxGlobalVariable.addMetaExtendRelation(syntaxAbsVariable, false);

		syntaxMetaView.addConcept(syntaxGlobalVariable);
		syntaxElements.put("GlobalVariable", syntaxGlobalVariable);

		MetaConcept syntaxLocalVariable = new MetaConcept("LocalVariable",
				true, "Local Variable", "refaslocalcnxt", " A local variable"
						+ " represents an instance of a component or a variable"
						+ " with local scope. This variables may have different"
						+ " values for each user of the system. Local variables"
						+ " are used mainly for SG with local satisfaction"
						+ " evaluation", 100, 40,
				"/com/variamos/gui/refas/editor/images/localCnxtVar.png", true,
				Color.BLUE.toString(), 1, true, semVariable);

		syntaxLocalVariable.addMetaExtendRelation(syntaxAbsVariable, false);

		syntaxMetaView.addConcept(syntaxLocalVariable);
		syntaxElements.put("LocalVariable", syntaxLocalVariable);
		
		MetaEnumeration metaEnumeration = new MetaEnumeration("ME",
				true, "MetaEnumeration", "refasenumeration","Allows the"
						+ " creation of user defined enumeration for"
						+ " variables"
				, 100, 150,
				"/com/variamos/gui/refas/editor/images/assump.png", true, "",
				1, true);
		syntaxMetaView.addConcept(metaEnumeration);
		syntaxElements.put("ME", metaEnumeration);
		
		MetaView syntaxMetaChildView = new MetaView("ContandModelEnum",
				"Context without Enumerations", "Context Palette", 1);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		//syntaxMetaChildView.addConcept(metaEnumeration);
		syntaxMetaChildView.addConcept(syntaxContextGroup);
		syntaxMetaChildView.addConcept(syntaxLocalVariable);
		syntaxMetaChildView.addConcept(syntaxGlobalVariable);

		

		// Direct variable relations

		List<IntDirectSemanticEdge> directCVCGSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		directCVCGSemanticEdges.add(directCVCGSemanticEdge);

		MetaDirectRelation metaVariableEdge = new MetaDirectRelation("Variable To Context Relation",
				true, "Variable To Context Relation", "ploptional", "Associates a Variable"
						+ " with the Context Group", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAbsVariable, syntaxContextGroup, directCVCGSemanticEdges,
				noneDirectRelation);
		syntaxAbsVariable.addMetaEdgeAsOrigin(syntaxContextGroup,
				metaVariableEdge);
		syntaxElements.put("Variable To Context Relation", metaVariableEdge);

		MetaDirectRelation metaContextEdge = new MetaDirectRelation("Context To Context Relation",
				true, "Context To Context Relation", "ploptional", "Associates a"
						+ " Context Group with other Context Group", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxContextGroup, syntaxContextGroup,
				directCVCGSemanticEdges, noneDirectRelation);
		syntaxContextGroup.addMetaEdgeAsOrigin(syntaxContextGroup,
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
				"refasclaim","A claim includes a group of"
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

		MetaConcept syntaxSoftDependency = new MetaConcept("SD", true,
				"Soft Dependency", "refassoftdep", "A Soft Dependency"
						+ " (SD express a logical condition useful to express"
						+ " an expected level of"
						+ " satisfaction of a softgoal. The soft dependency is"
						+ " activated only when the conditional expression is true."
						+ " The SD includes a relation with a softgoal (SG)", 100, 40,
				"/com/variamos/gui/refas/editor/images/softdep.png", true,
				Color.BLUE.toString(), 1, true, semSoftDependency);

		syntaxMetaView.addConcept(syntaxSoftDependency);
		syntaxElements.put("SD", syntaxSoftDependency);

		semanticRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticRelations.add(semanticOperClaimGroupRelation);

		syntaxGroupDependency = new MetaGroupDependency("OperClaimGD", true,
				"OperClaimGD", "plgroup", "Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " expected on the softgoal in case the Claim is satisfied", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);
		syntaxMetaView.addConcept(syntaxGroupDependency);
		syntaxElements.put("OperClaimGD", syntaxGroupDependency);

		List<IntDirectSemanticEdge> directSDSGSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		directSDSGSemanticEdges.add(directSDSGSemanticEdge);

		MetaDirectRelation metaSDSGEdge = new MetaDirectRelation("SDSGRelation", true,
				"SDSGRelation", "ploptional","Express the relation between"
						+ " the SD and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxSoftDependency, syntaxAbsSoftGoal,
				directSDSGSemanticEdges, softdepDirectRelation);
		syntaxSoftDependency.addMetaEdgeAsOrigin(syntaxAbsSoftGoal,
				metaSDSGEdge);

		syntaxElements.put("SDSGRelation", metaSDSGEdge);

		List<IntDirectSemanticEdge> directClaimSGSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		directClaimSGSemanticEdges.add(directClaimSGSemanticEdge);

		MetaDirectRelation metaClaimSGEdge = new MetaDirectRelation("Claim-Softgoal Relation",
				true, "Claim-Softgoal Relation", "ploptional", "Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxClaim, syntaxAbsSoftGoal, directClaimSGSemanticEdges,
				claimDirectRelation);
		syntaxClaim.addMetaEdgeAsOrigin(syntaxAbsSoftGoal, metaClaimSGEdge);

		syntaxElements.put("Claim-Softgoal Relation", metaClaimSGEdge);

		// *************************---------------****************************
		// Assets model

		syntaxMetaView = new MetaView("Assets", "Assets General Model",
				"Assets Palette", 5);
		metaViews.add(syntaxMetaView);
		syntaxMetaView.addConcept(sOperationalization);

		MetaConcept syntaxAsset = new MetaConcept("AS", true, "Asset",
				"refasasset","Represents a asset of the system. The most"
						+ " important assets to represent are those than"
						+ " can implement operationalizations", 100, 40,
				"/com/variamos/gui/refas/editor/images/component.png", true,
				Color.WHITE.toString(), 1, true, semAsset);
		syntaxAsset.addModelingAttribute("name", "String", false, "Name", ""); // TODO
																				// move
																				// to
		// semantic
		// attributes
		syntaxAsset.addDisPanelVisibleAttribute("03#" + "name");
		syntaxAsset.addDisPropEditableAttribute("03#" + "name");
		syntaxAsset.addDisPropVisibleAttribute("03#" + "name");
		syntaxMetaView.addConcept(syntaxAsset);

		syntaxMetaView.addConcept(sOperationalization);
		syntaxElements.put("AS", syntaxAsset);

		syntaxMetaChildView = new MetaView("FunctionalAssets",
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

		syntaxGroupDependency = new MetaGroupDependency("AssetOperGroupDep", true,
				"AssetOperGroupDep", "plgroup","Represents the implementation "
						+ "of an operationalization by a group of assets", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);

		syntaxMetaView.addConcept(syntaxGroupDependency);
		syntaxElements.put("Asset-Oper GroupDep", syntaxGroupDependency);

		List<IntDirectSemanticEdge> directAssetOperSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		directAssetOperSemanticEdges.add(directAssetOperSemanticEdge);

		MetaDirectRelation metaOperEdge = new MetaDirectRelation("Asset To Oper Relation",
				true, "Asset To Oper Relation", "ploptional", "Represents the "
						+ "implementation of an operationzalization by an"
						+ " asset",50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAsset, sOperationalization, directAssetOperSemanticEdges,
				implementationDirectRelation);

		// syntaxMetaView.addConcept(metaOperEdge);
		syntaxAsset.addMetaEdgeAsOrigin(sOperationalization, metaOperEdge);
		syntaxElements.put("Asset To Oper Relation", metaOperEdge);

	}

	public static void main(String[] args) {
		SemanticPlusSyntax mst = new SemanticPlusSyntax();
		List<IntSemanticElement> ascs = (List<IntSemanticElement>) mst
				.getSemanticConcepts().values();
		for (int i = 0; i < ascs.size(); i++)
			System.out.println(ascs.get(i));
	}

}