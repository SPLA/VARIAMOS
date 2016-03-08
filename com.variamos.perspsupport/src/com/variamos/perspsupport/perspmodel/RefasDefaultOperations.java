package com.variamos.perspsupport.perspmodel;

import java.util.ArrayList;
import java.util.List;

import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.LabelingOrder;
import com.variamos.hlcl.NumericExpression;
import com.variamos.hlcl.RangeDomain;
import com.variamos.hlcl.StringDomain;
import com.variamos.perspsupport.expressionsupport.InstanceExpression;
import com.variamos.perspsupport.expressionsupport.OperationLabeling;
import com.variamos.perspsupport.expressionsupport.OperationSubActionExpType;
import com.variamos.perspsupport.expressionsupport.SemanticExpression;
import com.variamos.perspsupport.expressionsupport.SemanticOperationAction;
import com.variamos.perspsupport.expressionsupport.SemanticOperationGroup;
import com.variamos.perspsupport.expressionsupport.SemanticOperationSubAction;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.instancesupport.InstVertex;
import com.variamos.perspsupport.semanticinterface.IntSemanticExpression;
import com.variamos.perspsupport.semanticinterface.IntSemanticRelationType;
import com.variamos.perspsupport.semanticsupport.SemanticConcept;
import com.variamos.perspsupport.semanticsupport.SemanticContextGroup;
import com.variamos.perspsupport.semanticsupport.SemanticOverTwoRelation;
import com.variamos.perspsupport.semanticsupport.SemanticPairwiseRelation;
import com.variamos.perspsupport.semanticsupport.SemanticReasoningConcept;
import com.variamos.perspsupport.semanticsupport.SemanticRelationType;
import com.variamos.perspsupport.semanticsupport.SemanticVariable;
import com.variamos.perspsupport.semanticsupport.SoftSemanticConcept;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.syntaxsupport.ExecCurrentStateAttribute;
import com.variamos.perspsupport.syntaxsupport.GlobalConfigAttribute;
import com.variamos.perspsupport.syntaxsupport.MetaConcept;
import com.variamos.perspsupport.syntaxsupport.MetaPairwiseRelation;
import com.variamos.perspsupport.syntaxsupport.OptionAttribute;
import com.variamos.perspsupport.syntaxsupport.SemanticAttribute;
import com.variamos.perspsupport.syntaxsupport.SyntaxAttribute;
import com.variamos.perspsupport.types.ExpressionVertexType;
import com.variamos.perspsupport.types.OperationSubActionExecType;
import com.variamos.perspsupport.types.OperationSubActionType;
import com.variamos.perspsupport.types.StringType;
import com.variamos.semantic.types.AttributeType;

public class RefasDefaultOperations {
	static SemanticOperationAction verifDeadElemOperationAction = null;
	static OperationSubActionExpType verifDeadElemOperSubActionNormal = null;
	static OperationSubActionExpType verifDeadElemOperSubActionRelaxable = null;
	static OperationSubActionExpType verifDeadElemOperSubActionVerification = null;

	static SemanticOperationAction verifParentsOperationAction = null;
	static OperationSubActionExpType verifParentsOperSubActionNormal = null;
	static OperationSubActionExpType verifParentsOperSubActionRelaxable = null;
	static OperationSubActionExpType verifParentsOperSubActionVerification = null;

	static SemanticOperationAction verifRootOperationAction = null;
	static OperationSubActionExpType verifRootOperSubActionNormal = null;
	static OperationSubActionExpType verifRootOperSubActionRelaxable = null;
	static OperationSubActionExpType verifRootOperSubActionVerification = null;

	static SemanticOperationAction verifFalseOptOperationAction = null;
	static OperationSubActionExpType verifFalseOptOperSubActionNormal = null;
	static OperationSubActionExpType verifFalseOptOperSubActionRelaxable = null;
	static OperationSubActionExpType verifFalseOptOperSubActionVerification = null;

	static OperationLabeling simulationExecOperUniqueLabeling = null;

	static SemanticOperationAction simulationOperationAction = null;

	static OperationSubActionExpType simulationExecOptOperSubActionNormal = null;
	static OperationSubActionExpType simulationPreValOptOperSubActionNormal = null;
	static OperationSubActionExpType simulationPreUpdOptOperSubActionNormal = null;
	static OperationSubActionExpType simulationPosValOptOperSubActionNormal = null;
	static OperationSubActionExpType simulationPostUpdOptOperSubActionNormal = null;

	static SemanticOperationAction simulScenOperationAction = null;

	static OperationSubActionExpType simulScenExecOptOperSubActionNormal = null;
	static OperationSubActionExpType simulScenPreValOptOperSubActionNormal = null;
	static OperationSubActionExpType simulScenPreUpdOptOperSubActionNormal = null;
	static OperationSubActionExpType simulScenPosValOptOperSubActionNormal = null;
	static OperationSubActionExpType simulScenPostUpdOptOperSubActionNormal = null;

	static SemanticOperationAction configTemporalOperationAction = null;

	static OperationSubActionExpType configTemporalOptOperSubActionNormal = null;

	static SemanticOperationAction configPermanentOperationAction = null;

	static OperationSubActionExpType configPermanentOptOperSubActionNormal = null;

	static SemanticOperationAction updateCoreOperationAction = null;

	static OperationSubActionExpType updateCoreOptOperSubActionNormal = null;

	@SuppressWarnings("unchecked")
	public static void createDefaultOperations(ModelInstance refas) {

		HlclFactory hlclFactory = new HlclFactory();

		MetaConcept metaModel = (MetaConcept) ((InstConcept) refas
				.getSyntaxModel().getVertex("OMMModel"))
				.getEditableMetaElement();
		MetaConcept metaOperationMenu = (MetaConcept) ((InstConcept) refas
				.getSyntaxModel().getVertex("OMMOperationGroup"))
				.getEditableMetaElement();
		MetaConcept metaOperationAction = (MetaConcept) ((InstConcept) refas
				.getSyntaxModel().getVertex("OMMOperation"))
				.getEditableMetaElement();
		MetaConcept metaOperationSubAction = (MetaConcept) ((InstConcept) refas
				.getSyntaxModel().getVertex("OMMSubOperation"))
				.getEditableMetaElement();
		MetaConcept metaConcept = (MetaConcept) ((InstConcept) refas
				.getSyntaxModel().getVertex("OMMConcept"))
				.getEditableMetaElement();
		MetaConcept metaLabeling = (MetaConcept) ((InstConcept) refas
				.getSyntaxModel().getVertex("OMMLabeling"))
				.getEditableMetaElement();
		// MetaEnumeration metaEnumeration = (MetaEnumeration) ((InstConcept)
		// refas
		// .getSyntaxModel().getVertex("TypeEnumeration"))
		// .getEditableMetaElement();
		MetaConcept metaPairwiseRelation = (MetaConcept) ((InstConcept) refas
				.getSyntaxModel().getVertex("OMMPairWiseRelation"))
				.getEditableMetaElement();
		MetaConcept metaOverTwoRelation = (MetaConcept) ((InstConcept) refas
				.getSyntaxModel().getVertex("OMMOverTwoRelation"))
				.getEditableMetaElement();
		MetaPairwiseRelation metaPairwRelCCExt = (MetaPairwiseRelation) ((InstPairwiseRelation) refas
				.getSyntaxModel().getConstraintInstEdge("ExtendsCCRel"))
				.getEditableMetaElement();
		MetaPairwiseRelation metaPairwRelOCExt = (MetaPairwiseRelation) ((InstPairwiseRelation) refas
				.getSyntaxModel().getConstraintInstEdge("ExtendsOCRel"))
				.getEditableMetaElement();

		MetaPairwiseRelation metaPairwRelAso = (MetaPairwiseRelation) ((InstPairwiseRelation) refas
				.getSyntaxModel().getConstraintInstEdge("DirectRelation"))
				.getEditableMetaElement();

		SemanticConcept refasModel = new SemanticConcept("REFAS");

		AbstractAttribute attribute = null;
		attribute = new ExecCurrentStateAttribute("TotalOrder", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "***TotalOrder***", 0,
				new RangeDomain(0, 2000), 2, -1, "", "", -1, "", "");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		refasModel.putSemanticAttribute("TotalOrder", attribute);

		attribute = new ExecCurrentStateAttribute("TotalOpt", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "***TotalOpt***", 0,
				new RangeDomain(0, 2000), 2, -1, "", "", -1, "", "");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		refasModel.putSemanticAttribute("TotalOpt", attribute);

		InstVertex instRefasModel = new InstConcept("REFAS", metaModel,
				refasModel);
		refas.getVariabilityVertex().put("REFAS", instRefasModel);

		InstAttribute ia = null;
		List<InstAttribute> ias = null;

		SemanticOperationGroup operationMenu = new SemanticOperationGroup(1,
				"SimulationGroup", 2);

		InstVertex instOperationGroup = new InstConcept("SimulationGroup",
				metaOperationMenu, operationMenu);
		refas.getVariabilityVertex().put("SimulationGroup", instOperationGroup);

		instOperationGroup.getInstAttribute("visible").setValue(true);
		instOperationGroup.getInstAttribute("menuType").setValue("4");
		instOperationGroup.getInstAttribute("name").setValue(
				"Basic Simulation (New)");
		instOperationGroup.getInstAttribute("shortcut").setValue("S");

		simulationOperationAction = new SemanticOperationAction(1,
				"SimulationOper");

		InstVertex instOperationAction = new InstConcept("SimulationOper",
				metaOperationAction, simulationOperationAction);
		refas.getVariabilityVertex().put("SimulationOper", instOperationAction);

		instOperationAction.getInstAttribute("name").setValue(
				"Simulation Operation (Partially Working)");
		instOperationAction.getInstAttribute("shortcut").setValue("S");
		instOperationAction.getInstAttribute("iteration").setValue(true);

		InstPairwiseRelation instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sim-menu", instEdgeOper);
		instEdgeOper.setIdentifier("sim-menu");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationAction, true);
		instEdgeOper.setSourceRelation(instOperationGroup, true);

		SemanticOperationSubAction operationSubAction = new SemanticOperationSubAction(
				1, "Sim-Pre-Validation", OperationSubActionType.VERIFICATION);

		// simulationOperationAction.addExpressionSubAction(operationSubAction);

		InstVertex instOperationSubAction = new InstConcept(
				"Sim-Pre-Validation", metaOperationSubAction,
				operationSubAction);
		refas.getVariabilityVertex().put("Sim-Pre-Validation",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sim-pre-val", instEdgeOper);
		instEdgeOper.setIdentifier("sim-pre-val");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		simulationPreValOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulationPreValOptOperSubActionNormal);

		OperationLabeling operationLabeling = new OperationLabeling("unique",
				"L1", 1, false, null, null);

		// operationSubAction.addOperationLabeling(operationLabeling);

		InstVertex instLabeling = new InstConcept("Sim-Pre-Validation-lab",
				metaLabeling, operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex()
				.put("Sim-Pre-Validation-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sim-pre-val-lab", instEdgeOper);
		instEdgeOper.setIdentifier("sim-pre-val-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		operationSubAction = new SemanticOperationSubAction(2,
				"Sim-Pre-Update", OperationSubActionType.SINGLEUPDATE);
		// simulationOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("Sim-Pre-Update",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("Sim-Pre-Update",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sim-pre-upd", instEdgeOper);
		instEdgeOper.setIdentifier("sim-pre-upd");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		simulationPreUpdOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulationPreUpdOptOperSubActionNormal);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// operationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("sim-pre-upd-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("sim-pre-upd-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sim-pre-upd-lab", instEdgeOper);
		instEdgeOper.setIdentifier("sim-pre-upd-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		SemanticOperationSubAction simulOperationSubAction = new SemanticOperationSubAction(
				3, "Sim-Execution", OperationSubActionType.ITERATIVEUPDATE);
		List<LabelingOrder> labord = new ArrayList<LabelingOrder>();
		labord.add(LabelingOrder.MIN);
		labord.add(LabelingOrder.MIN);
		List<NumericExpression> orderExpressionList = new ArrayList<NumericExpression>();
		orderExpressionList.add(hlclFactory.newIdentifier("REFAS1_TotalOrder"));
		orderExpressionList.add(hlclFactory.newIdentifier("REFAS1_TotalOpt"));
		simulationExecOperUniqueLabeling = new OperationLabeling("unique",
				"L1", 1, false, labord, orderExpressionList);

		List<IntSemanticExpression> semanticExpressions = new ArrayList<IntSemanticExpression>();

		simulationExecOperUniqueLabeling
				.setSemanticExpressions(semanticExpressions);

		SemanticExpression t1 = new SemanticExpression("sub", refas
				.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTITERCONFIXEDVARIABLE, instRefasModel,
				"TotalOrder", 0);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
				instRefasModel, "TotalOpt", 0);

		semanticExpressions.add(t1);

		SemanticExpression t2;

		SemanticExpression t3;

		// simulationOperationAction
		// .addExpressionSubAction(simulOperationSubAction);

		instOperationSubAction = new InstConcept("Sim-Execution",
				metaOperationSubAction, simulOperationSubAction);
		refas.getVariabilityVertex().put("Sim-Execution",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sim-exec", instEdgeOper);
		instEdgeOper.setIdentifier("sim-exec");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		simulationExecOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		simulOperationSubAction
				.addOperationSubActionExpType(simulationExecOptOperSubActionNormal);

		// simulOperationSubAction
		// .addOperationLabeling(simulationExecOperUniqueLabeling);

		instLabeling = new InstConcept("Sim-Execution-lab", metaLabeling,
				simulationExecOperUniqueLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("Sim-Execution-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("Sim-Execution-lab", instEdgeOper);
		instEdgeOper.setIdentifier("Sim-Execution-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		operationSubAction = new SemanticOperationSubAction(4,
				"Sim-Post-Validation", OperationSubActionType.VERIFICATION);

		// simulationOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("Sim-Post-Validation",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("Sim-Post-Validation",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sim-pos-val", instEdgeOper);
		instEdgeOper.setIdentifier("sim-pos-val");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		simulationPosValOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulationPosValOptOperSubActionNormal);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// simulOperationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("sim-pos-val-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("sim-pos-val-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sim-pos-val-lab", instEdgeOper);
		instEdgeOper.setIdentifier("sim-pos-val-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		operationSubAction = new SemanticOperationSubAction(5,
				"Sim-Post-Update", OperationSubActionType.SINGLEUPDATE);

		// simulationOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("Sim-Post-Update",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("Sim-Post-Update",
				instOperationSubAction);

		simulationPostUpdOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulationPostUpdOptOperSubActionNormal);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// simulOperationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("Sim-Post-Update-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("Sim-Post-Update-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("Sim-Post-Update-lab", instEdgeOper);
		instEdgeOper.setIdentifier("Sim-Post-Update-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		operationMenu = new SemanticOperationGroup(1, "SimulationSCeOper", 2);

		instOperationGroup = new InstConcept("SimulationSceGroup",
				metaOperationMenu, operationMenu);
		refas.getVariabilityVertex().put("SimulationSceGroup",
				instOperationGroup);

		instOperationGroup.getInstAttribute("visible").setValue(true);
		instOperationGroup.getInstAttribute("menuType").setValue("4");
		instOperationGroup.getInstAttribute("name").setValue(
				"Simulation Scenarios");
		instOperationGroup.getInstAttribute("shortcut").setValue("C");

		simulScenOperationAction = new SemanticOperationAction(1,
				"SimulationScenarios");

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sim-pos-upd", instEdgeOper);
		instEdgeOper.setIdentifier("sim-pos-upd");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		instOperationAction = new InstConcept("SimulationScenarios",
				metaOperationAction, simulScenOperationAction);
		refas.getVariabilityVertex().put("SimulationScenarios",
				instOperationAction);

		instOperationAction.getInstAttribute("name").setValue(
				"Simulation Scenarios");
		instOperationAction.getInstAttribute("shortcut").setValue("S");
		instOperationAction.getInstAttribute("iteration").setValue(true);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-menu", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-menu");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationAction, true);
		instEdgeOper.setSourceRelation(instOperationGroup, true);

		operationSubAction = new SemanticOperationSubAction(1,
				"SimSce-Pre-Validation", OperationSubActionType.VERIFICATION);

		// simulScenOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("SimSce-Pre-Validation",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("SimSce-Pre-Validation",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-pre-val", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-pre-val");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		simulScenPreValOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulScenPreValOptOperSubActionNormal);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// simulOperationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("simsce-pre-val-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("simsce-pre-val-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-pre-val-lab", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-pre-val-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		operationSubAction = new SemanticOperationSubAction(2,
				"SimSce-Pre-Update", OperationSubActionType.SINGLEUPDATE);

		// simulScenOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("SimSce-Pre-Update",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("SimSce-Pre-Update",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-pre-upd", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-pre-upd");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		simulScenPreValOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulScenPreValOptOperSubActionNormal);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// simulOperationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("simsce-pre-upd-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("simsce-pre-upd-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-pre-upd-lab", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-pre-upd-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		operationSubAction = new SemanticOperationSubAction(3,
				"SimSce-Execution", OperationSubActionType.ITERATIVEUPDATE);
		// simulScenOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("SimSce-Execution",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("SimSce-Execution",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-exec", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-exec");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		simulScenExecOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulScenExecOptOperSubActionNormal);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// simulOperationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("simsce-exec-lab1", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("simsce-exec-lab1", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-exec-lab1", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-exec-lab1");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		operationLabeling = new OperationLabeling("unique", "L2", 1, false,
				null, null);

		// simulOperationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("simsce-exec-lab2", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L2");
		instLabeling.getInstAttribute("position").setValue(2);
		instLabeling.getInstAttribute("once").setValue(true);

		refas.getVariabilityVertex().put("simsce-exec-lab2", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-exec-lab2", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-exec-lab2");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		operationSubAction = new SemanticOperationSubAction(4,
				"SimSce-Post-Validation", OperationSubActionType.VERIFICATION);

		// simulScenOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("SimSce-Post-Validation",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("SimSce-Post-Validation",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-pos-val", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-pos-val");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		simulScenPosValOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulScenPosValOptOperSubActionNormal);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// simulOperationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("simsce-pos-val-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("simsce-pos-val-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-pos-val-lab", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-pos-val-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		operationSubAction = new SemanticOperationSubAction(5,
				"SimSce-Post-Update", OperationSubActionType.SINGLEUPDATE);
		// simulScenOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("SimSce-Post-Update",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("SimSce-Post-Update",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-pos-upd", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-pos-upd");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		simulScenPostUpdOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulScenPostUpdOptOperSubActionNormal);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// simulOperationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("SimSce-Post-Update-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex()
				.put("SimSce-Post-Update-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("SimSce-Post-Update-lab",
				instEdgeOper);
		instEdgeOper.setIdentifier("SimSce-Post-Update-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		operationMenu = new SemanticOperationGroup(1, "Verification", 2);

		instOperationGroup = new InstConcept("Verification", metaOperationMenu,
				operationMenu);
		refas.getVariabilityVertex().put("Verification", instOperationGroup);

		instOperationGroup.getInstAttribute("visible").setValue(false);
		instOperationGroup.getInstAttribute("menuType").setValue("2");
		instOperationGroup.getInstAttribute("name").setValue("Verification");
		instOperationGroup.getInstAttribute("shortcut").setValue("V");

		updateCoreOperationAction = new SemanticOperationAction(1,
				"UpdateCoreOper");

		instOperationAction = new InstConcept("UpdateCoreOper",
				metaOperationAction, updateCoreOperationAction);
		refas.getVariabilityVertex().put("UpdateCoreOper", instOperationAction);

		instOperationAction.getInstAttribute("name").setValue(
				"Update Core Operation");
		instOperationAction.getInstAttribute("shortcut").setValue("S");

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-menu-upd", instEdgeOper);
		instEdgeOper.setIdentifier("ver-menu-upd");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationAction, true);
		instEdgeOper.setSourceRelation(instOperationGroup, true);

		operationSubAction = new SemanticOperationSubAction(1,
				"UpdateCoreSubOper", OperationSubActionType.SINGLEUPDATE);
		// updateCoreOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("UpdateCoreSubOper",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("UpdateCoreSubOper",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("upd-core", instEdgeOper);
		instEdgeOper.setIdentifier("upd-core");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		updateCoreOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(updateCoreOptOperSubActionNormal);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// operationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("upd-core-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("upd-core-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("upd-core-lab", instEdgeOper);
		instEdgeOper.setIdentifier("upd-core-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		verifDeadElemOperationAction = new SemanticOperationAction(1,
				"VerifyDeadElementsOper");

		instOperationAction = new InstConcept("VerifyDeadElementsOper",
				metaOperationAction, verifDeadElemOperationAction);
		refas.getVariabilityVertex().put("VerifyDeadElementsOper",
				instOperationAction);

		instOperationAction.getInstAttribute("name").setValue(
				"Verify Dead Elements Operation");
		instOperationAction.getInstAttribute("shortcut").setValue("S");

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-menu-dead", instEdgeOper);
		instEdgeOper.setIdentifier("ver-menu-dead");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationAction, true);
		instEdgeOper.setSourceRelation(instOperationGroup, true);

		operationSubAction = new SemanticOperationSubAction(1,
				"VerifyDeadElementsSubOper",
				OperationSubActionType.VERIFICATION);
		// verifDeadElemOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("VerifyDeadElementsSubOper",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("VerifyDeadElementsSubOper",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-dead", instEdgeOper);
		instEdgeOper.setIdentifier("ver-dead");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		verifDeadElemOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(verifDeadElemOperSubActionNormal);

		verifDeadElemOperSubActionRelaxable = new OperationSubActionExpType(
				OperationSubActionExecType.RELAXABLE);
		operationSubAction
				.addOperationSubActionExpType(verifDeadElemOperSubActionRelaxable);

		verifDeadElemOperSubActionVerification = new OperationSubActionExpType(
				OperationSubActionExecType.VERIFICATION);
		operationSubAction
				.addOperationSubActionExpType(verifDeadElemOperSubActionVerification);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// operationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("ver-dead-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("ver-dead-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-dead-lab", instEdgeOper);
		instEdgeOper.setIdentifier("ver-dead-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		verifParentsOperationAction = new SemanticOperationAction(2,
				"VerifyParentsOper");

		instOperationAction = new InstConcept("VerifyParentsOper",
				metaOperationAction, verifParentsOperationAction);
		refas.getVariabilityVertex().put("VerifyParentsOper",
				instOperationAction);

		instOperationAction.getInstAttribute("name").setValue(
				"Verify Parents Operation");
		instOperationAction.getInstAttribute("shortcut").setValue("S");

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-menu-pare", instEdgeOper);
		instEdgeOper.setIdentifier("ver-menu-pare");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationAction, true);
		instEdgeOper.setSourceRelation(instOperationGroup, true);

		operationSubAction = new SemanticOperationSubAction(1,
				"VerifyParentsSubOper", OperationSubActionType.VERIFICATION);
		// verifParentsOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("VerifyParentsSubOper",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("VerifyParentsSubOper",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-par", instEdgeOper);
		instEdgeOper.setIdentifier("ver-par");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		verifParentsOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(verifParentsOperSubActionNormal);

		verifParentsOperSubActionRelaxable = new OperationSubActionExpType(
				OperationSubActionExecType.RELAXABLE);
		operationSubAction
				.addOperationSubActionExpType(verifParentsOperSubActionRelaxable);

		verifParentsOperSubActionVerification = new OperationSubActionExpType(
				OperationSubActionExecType.VERIFICATION);
		operationSubAction
				.addOperationSubActionExpType(verifParentsOperSubActionVerification);

		verifRootOperationAction = new SemanticOperationAction(3,
				"VerifyRootsOper");

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// operationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("ver-par-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("ver-par-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-par-lab", instEdgeOper);
		instEdgeOper.setIdentifier("ver-par-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		instOperationAction = new InstConcept("VerifyRootsOper",
				metaOperationAction, verifRootOperationAction);
		refas.getVariabilityVertex()
				.put("VerifyRootsOper", instOperationAction);

		instOperationAction.getInstAttribute("name").setValue(
				"Verify Roots Operation");
		instOperationAction.getInstAttribute("shortcut").setValue("S");

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-menu-root", instEdgeOper);
		instEdgeOper.setIdentifier("ver-menu-root");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationAction, true);
		instEdgeOper.setSourceRelation(instOperationGroup, true);

		operationSubAction = new SemanticOperationSubAction(1,
				"VerifyRootsSubOper", OperationSubActionType.VERIFICATION);
		// verifRootOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("VerifyRootsSubOper",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("VerifyRootsSubOper",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-root", instEdgeOper);
		instEdgeOper.setIdentifier("ver-root");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		verifRootOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(verifRootOperSubActionNormal);

		verifRootOperSubActionRelaxable = new OperationSubActionExpType(
				OperationSubActionExecType.RELAXABLE);
		operationSubAction
				.addOperationSubActionExpType(verifRootOperSubActionRelaxable);

		verifRootOperSubActionVerification = new OperationSubActionExpType(
				OperationSubActionExecType.VERIFICATION);
		operationSubAction
				.addOperationSubActionExpType(verifRootOperSubActionVerification);

		verifFalseOptOperationAction = new SemanticOperationAction(4,
				"VerifyFalseOperations");

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// operationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("ver-root-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("ver-root-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-root-lab", instEdgeOper);
		instEdgeOper.setIdentifier("ver-root-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		instOperationAction = new InstConcept("VerifyFalseOperations",
				metaOperationAction, verifFalseOptOperationAction);
		refas.getVariabilityVertex().put("VerifyFalseOperations",
				instOperationAction);

		instOperationAction.getInstAttribute("name").setValue(
				"Verify False Optional Operation");
		instOperationAction.getInstAttribute("shortcut").setValue("S");

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-menu-false", instEdgeOper);
		instEdgeOper.setIdentifier("ver-menu-false");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationAction, true);
		instEdgeOper.setSourceRelation(instOperationGroup, true);

		operationSubAction = new SemanticOperationSubAction(1,
				"VerifyFalseSubOperations", OperationSubActionType.VERIFICATION);
		// operationSubAction.addOperationLabeling(new
		// OperationLabeling("unique",
		// "L1", 1, false, null, null));
		// verifFalseOptOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("VerifyFalseSubOperations",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("VerifyFalseSubOperations",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-false", instEdgeOper);
		instEdgeOper.setIdentifier("ver-false");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		verifFalseOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(verifFalseOptOperSubActionNormal);

		verifFalseOptOperSubActionRelaxable = new OperationSubActionExpType(
				OperationSubActionExecType.RELAXABLE);
		operationSubAction
				.addOperationSubActionExpType(verifFalseOptOperSubActionRelaxable);

		verifFalseOptOperSubActionVerification = new OperationSubActionExpType(
				OperationSubActionExecType.VERIFICATION);
		operationSubAction
				.addOperationSubActionExpType(verifFalseOptOperSubActionVerification);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// operationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("ver-false-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("ver-false-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-false-lab", instEdgeOper);
		instEdgeOper.setIdentifier("ver-false-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		instOperationGroup = new InstConcept("Configuration",
				metaOperationMenu, operationMenu);
		refas.getVariabilityVertex().put("Configuration", instOperationGroup);

		instOperationGroup.getInstAttribute("visible").setValue(false);
		instOperationGroup.getInstAttribute("menuType").setValue("4");
		instOperationGroup.getInstAttribute("name").setValue("Configuration");
		instOperationGroup.getInstAttribute("shortcut").setValue("C");

		configTemporalOperationAction = new SemanticOperationAction(1,
				"ConfigureTemporalOper");

		instOperationAction = new InstConcept("ConfigureTemporalOper",
				metaOperationAction, configTemporalOperationAction);
		refas.getVariabilityVertex().put("ConfigureTemporalOper",
				instOperationAction);

		instOperationAction.getInstAttribute("name").setValue(
				"Configure Temporal Operation");
		instOperationAction.getInstAttribute("shortcut").setValue("S");

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-menu-conft", instEdgeOper);
		instEdgeOper.setIdentifier("ver-menu-conft");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationAction, true);
		instEdgeOper.setSourceRelation(instOperationGroup, true);

		operationSubAction = new SemanticOperationSubAction(1,
				"ConfigureTemporalSubOper", OperationSubActionType.SINGLEUPDATE);
		// operationSubAction.addOperationLabeling(new
		// OperationLabeling("unique",
		// "L1", 1, false, null, null));
		// configTemporalOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("ConfigureTemporalSubOper",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("ConfigureTemporalSubOper",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("conf-temp", instEdgeOper);
		instEdgeOper.setIdentifier("conf-temp");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		configTemporalOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(configTemporalOptOperSubActionNormal);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		// operationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("conf-temp-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("conf-temp-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("conf-temp-lab", instEdgeOper);
		instEdgeOper.setIdentifier("conf-temp-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		configPermanentOperationAction = new SemanticOperationAction(1,
				"ConfigurePermanentOper");

		instOperationAction = new InstConcept("ConfigurePermanentOper",
				metaOperationAction, configPermanentOperationAction);
		refas.getVariabilityVertex().put("ConfigurePermanentOper",
				instOperationAction);

		instOperationAction.getInstAttribute("name").setValue(
				"Configure Permanent Operation");
		instOperationAction.getInstAttribute("shortcut").setValue("S");

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ver-menu-confp", instEdgeOper);
		instEdgeOper.setIdentifier("ver-menu-confp");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationAction, true);
		instEdgeOper.setSourceRelation(instOperationGroup, true);

		operationSubAction = new SemanticOperationSubAction(1,
				"ConfigurePermanentSubOper",
				OperationSubActionType.SINGLEUPDATE);
		// configPermanentOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("ConfigurePermanentSubOper",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("ConfigurePermanentSubOpe",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("conf-perm", instEdgeOper);
		instEdgeOper.setIdentifier("conf-perm");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		configPermanentOptOperSubActionNormal = new OperationSubActionExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(configPermanentOptOperSubActionNormal);

		operationLabeling = new OperationLabeling("unique", "L1", 1, false,
				null, null);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		// operationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("conf-perm-lab", metaLabeling,
				operationLabeling);

		instLabeling.getInstAttribute("labelId").setValue("L1");
		instLabeling.getInstAttribute("position").setValue(1);
		instLabeling.getInstAttribute("once").setValue(false);

		refas.getVariabilityVertex().put("conf-perm-lab", instLabeling);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("conf-perm-lab", instEdgeOper);
		instEdgeOper.setIdentifier("conf-perm-lab");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		// END Operations definition
		// --------------------------------------------------------------

		// Start Concept's definition
		// -------------------------------------------------------

		SemanticConcept semGeneralElement = new SemanticConcept(
				"GeneralElement"); // From refas name depends all the
									// operations,
									// do not change it

		semGeneralElement.putSemanticAttribute("Selected",
				new ExecCurrentStateAttribute("Selected", "Boolean",
						AttributeType.EXECCURRENTSTATE, false,
						"***Selected***", false, 2, -1, "", "", -1, "", ""));
		semGeneralElement
				.putSemanticAttribute("NotAvailable",
						new ExecCurrentStateAttribute("NotAvailable",
								"Boolean", AttributeType.EXECCURRENTSTATE,
								false, "***Not Avaliable***", false, 2, -1, "",
								"", -1, "", ""));

		InstVertex instVertexGE = new InstConcept("GeneralElement",
				metaConcept, semGeneralElement);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		refasModel.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
				instVertexGE, "NextPrefSelected", 0);

		t1 = new SemanticExpression("prefSel <=1", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTITERCONCEPTVARIABLE, instRefasModel,
				instVertexGE, t1, 1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
				instVertexGE, "Order", 0);

		t1 = new SemanticExpression("TotalOrder", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERCONCEPTVARIABLE, instRefasModel,
				instVertexGE, t1, "TotalOrder");

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
				instVertexGE, "Opt", 0);

		t1 = new SemanticExpression("TotalOpt", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERCONCEPTVARIABLE, instRefasModel,
				instVertexGE, t1, "TotalOpt");

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		// t1 = new SemanticExpression("REFAS_pref<=1", refas
		// .getSemanticExpressionTypes().get("LessOrEquals"),
		// instRefasModel, "pref", 1);
		//
		// simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		//
		// semanticExpressions.add(t1);

		// InstEnumeration instVertexHStrME = new InstEnumeration(
		// "HardStructEnumeration", metaEnumeration);
		// refas.getVariabilityVertex().put("HardStructEnumeration",
		// instVertexHStrME);

		// ArrayList<InstAttribute> c = (ArrayList<InstAttribute>)
		// ((InstAttribute) instVertexHStrME
		// .getInstAttribute("value")).getInstAttributeAttribute("Value");
		// InstAttribute a = new InstAttribute();
		// a.setInstAttributeAttribute("Value",
		// "1-means_ends-means_ends-true-true-true-1-1-1-1");
		// a.setInstAttributeAttribute("DisplayValue", null);
		// a.setInstAttributeAttribute("attributeIden", "EnumValue");
		// a.setInstAttributeAttribute("Identifier", "enum11");
		// c.add(a);
		// a = new InstAttribute();
		// a.setInstAttributeAttribute("Value",
		// "12-impl.-Impl.-true-true-true-1-1-1-1");
		// a.setInstAttributeAttribute("DisplayValue", null);
		// a.setInstAttributeAttribute("attributeIden", "EnumValue");
		// a.setInstAttributeAttribute("Identifier", "enum12");
		// c.add(a);
		//
		// InstEnumeration instVertexHSideME = new InstEnumeration(
		// "HardSideEnumeration", metaEnumeration);
		// // refas.getVariabilityVertex().put("HardSideEnumeration",
		// // instVertexHSideME);
		//
		// c = (ArrayList<InstAttribute>) ((InstAttribute) instVertexHSideME
		// .getInstAttribute("value")).getInstAttributeAttribute("Value");
		// a = new InstAttribute();
		// a.setInstAttributeAttribute("Value",
		// "1-conflict-conflict-false-true-true-1-1-1-1");
		// a.setInstAttributeAttribute("DisplayValue", null);
		// a.setInstAttributeAttribute("attributeIden", "EnumValue");
		// a.setInstAttributeAttribute("Identifier", "enum1");
		// c.add(a);
		// a = new InstAttribute();
		// a.setInstAttributeAttribute("Value",
		// "2-altern.-altern.-false-true-true-1-1-1-1");
		// a.setInstAttributeAttribute("DisplayValue", null);
		// a.setInstAttributeAttribute("attributeIden", "EnumValue");
		// a.setInstAttributeAttribute("Identifier", "enum2");
		// c.add(a);
		// a = new InstAttribute();
		// a.setInstAttributeAttribute("Value",
		// "3-preferred-pref.-false-true-true-1-1-1-1");
		// a.setInstAttributeAttribute("DisplayValue", null);
		// a.setInstAttributeAttribute("attributeIden", "EnumValue");
		// a.setInstAttributeAttribute("Identifier", "enum3");
		// c.add(a);
		// a = new InstAttribute();
		// a.setInstAttributeAttribute("Value",
		// "4-req.-req..-false-true-true-1-1-1-1");
		// a.setInstAttributeAttribute("DisplayValue", null);
		// a.setInstAttributeAttribute("attributeIden", "EnumValue");
		// a.setInstAttributeAttribute("Identifier", "enum4");
		// c.add(a);
		// a = new InstAttribute();
		// a.setInstAttributeAttribute("Value",
		// "5-cond.-cond.-false-true-true-1-1-1-1");
		// a.setInstAttributeAttribute("DisplayValue", null);
		// a.setInstAttributeAttribute("attributeIden", "EnumValue");
		// a.setInstAttributeAttribute("Identifier", "enum5");
		// c.add(a);
		//
		// InstEnumeration instClaimSemOTAsso = new InstEnumeration(
		// "ClaimSemOTAsso", metaEnumeration);
		// // refas.getVariabilityVertex().put("ClaimSemOTAsso",
		// // instClaimSemOTAsso);
		//
		// c = (ArrayList<InstAttribute>) ((InstAttribute) instClaimSemOTAsso
		// .getInstAttribute("value")).getInstAttributeAttribute("Value");
		// a = new InstAttribute();
		// a.setInstAttributeAttribute("Value",
		// "1#And#And#false#false#false#2#1#1#1");
		// a.setInstAttributeAttribute("DisplayValue", null);
		// a.setInstAttributeAttribute("attributeIden", "EnumValue");
		// a.setInstAttributeAttribute("Identifier", "enum1");
		// c.add(a);
		// a = new InstAttribute();
		// a.setInstAttributeAttribute("Value",
		// "2#Or#Or#false#true#true#2#1#1#1");
		// a.setInstAttributeAttribute("DisplayValue", null);
		// a.setInstAttributeAttribute("attributeIden", "EnumValue");
		// a.setInstAttributeAttribute("Identifier", "enum2");
		// c.add(a);
		// a = new InstAttribute();
		// a.setInstAttributeAttribute("Value",
		// "3#mutex#mutex#false#true#true#2#1#1#1");
		// a.setInstAttributeAttribute("DisplayValue", null);
		// a.setInstAttributeAttribute("attributeIden", "EnumValue");
		// a.setInstAttributeAttribute("Identifier", "enum3");
		// c.add(a);

		// Semantic Element

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semGeneralElement.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("Req Implies Selected", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexGE,
				instVertexGE, "Required", "Selected");

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("NextPrefSel_=_0", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"NextPrefSelected", 0);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("NextNotPrefSel_=_0", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"NextNotPrefSelected", 0);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("UserReq Implies Req", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexGE,
				instVertexGE, "Required", "Selected");

		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexGE, "HasParent", 1);

		t2 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("NotEquals"), instVertexGE, "userIdentifier",
				"GeneralFeature");

		t3 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("NotEquals"), instVertexGE, "userIdentifier",
				"LeafFeature");

		t3 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("And"), t2, t3);

		t1 = new SemanticExpression("NoLFet & NoGFet Implies hasParent", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, instVertexGE, "NextReqSelected",
				"ConfigSelected");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextPrefSelected", true, t1);

		t3 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, instVertexGE, "NextPrefSelected",
				"ConfigSelected");

		t3 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", true, t3);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), t1, t3);

		t1 = new SemanticExpression("Opt...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Opt", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t2 = new SemanticExpression("Opt =0", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Opt", 0);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t2);

		semanticExpressions.add(t2);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, instVertexGE, "ConfigSelected",
				"NextReqSelected");

		t1 = new SemanticExpression("5", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "Core", true, t1);

		t1 = new SemanticExpression("Core+ConfigSel+NextReqSel <=1", refas
				.getSemanticExpressionTypes().get("LessOrEquals"), 1, false, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Or"), instVertexGE, instVertexGE, "ConfigNotSelected",
				"NextNotPrefSelected");

		t1 = new SemanticExpression("5", refas.getSemanticExpressionTypes()
				.get("Or"), instVertexGE, "Dead", true, t1);

		t1 = new SemanticExpression("NotAvail (Dead Or NotSelec)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexGE, "NotAvailable", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("5", refas.getSemanticExpressionTypes()
				.get("Or"), instVertexGE, instVertexGE, "NextReqSelected",
				"NextPrefSelected");

		t2 = new SemanticExpression("5", refas.getSemanticExpressionTypes()
				.get("Or"), instVertexGE, instVertexGE, "Core",
				"ConfigSelected");

		t1 = new SemanticExpression("5", refas.getSemanticExpressionTypes()
				.get("Or"), t1, t2);

		t1 = new SemanticExpression("Selected (Core, NextReqSel, NextPrefSel)",
				refas.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexGE, "Selected", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("5", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, instVertexGE, "Selected",
				"NotAvailable");

		t1 = new SemanticExpression("Selected+NotAvail <=1", refas
				.getSemanticExpressionTypes().get("Equals"), 0, false, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		refas.getVariabilityVertex().put("GeneralElement", instVertexGE);

		// Design attributes: Do not change identifiers

		attribute = new ExecCurrentStateAttribute("True", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", true,
				2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralElement.putSemanticAttribute("True", attribute);
		simulOperationSubAction.addInVariable(attribute);

		attribute = new ExecCurrentStateAttribute("False", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***NotSelected***",
				false, 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralElement.putSemanticAttribute("False", attribute);
		simulOperationSubAction.addInVariable(attribute);

		attribute = new ExecCurrentStateAttribute("Selected", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", false,
				2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralElement.putSemanticAttribute("Selected", attribute);
		simulOperationSubAction.addOutVariable(attribute);

		attribute = new ExecCurrentStateAttribute("NotAvailable", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Not Avaliable***",
				false, 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralElement.putSemanticAttribute("NotAvailable", attribute);
		simulOperationSubAction.addOutVariable(attribute);

		attribute = new SemanticAttribute("Description", "String",
				AttributeType.OPERATION, false, "Description", "", 0, -1, "",
				"", -1, "", "");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralElement.putSemanticAttribute("Description", attribute);

		attribute = new SemanticAttribute("Required", "Boolean",
				AttributeType.OPERATION, true, "Is Required", false, 2, -1, "",
				"", -1, "", "");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralElement.putSemanticAttribute("Required", attribute);
		simulOperationSubAction.addInVariable(attribute);

		attribute = new SemanticAttribute("Scope", "Boolean",
				AttributeType.OPERATION, true, "Global Scope", true, 0, -1, "",
				"", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralElement.putSemanticAttribute("Scope", attribute);
		simulOperationSubAction.addInVariable(attribute);
		// TODO use scope

		attribute = new SemanticAttribute("ConcernLevel", "Class",
				AttributeType.OPERATION, false, "Concern Level",
				InstConcept.class.getCanonicalName(), "CG", null, "", 2, -1,
				"", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("ConcernLevel", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);
		// TODO: use concern level

		attribute = new SemanticAttribute("Core", "Boolean",
				AttributeType.OPERATION, false, "Is a Core Concept", false, 2,
				-1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralElement.putSemanticAttribute("Core", attribute);
		simulOperationSubAction.addInVariable(attribute);

		attribute = new SemanticAttribute("Dead", "Boolean",
				AttributeType.OPERATION, false, "Is a Dead Concept", false, 2,
				-1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralElement.putSemanticAttribute("Dead", attribute);
		simulOperationSubAction.addInVariable(attribute);

		attribute = new SemanticAttribute("IgnoreForSimulation", "Boolean",
				AttributeType.OPERATION, true, "Ignore for Simulation", false,
				0, -1, "", "", -1, "", "");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralElement
				.putSemanticAttribute("IgnoreForSimulation", attribute);

		semGeneralElement.addPropEditableAttribute("04#" + "Required");
		semGeneralElement.addPropEditableAttribute("05#" + "Scope");
		semGeneralElement.addPropEditableAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");

		semGeneralElement.addPropEditableAttribute("08#"
				+ "IgnoreForSimulation");
		semGeneralElement.addPropVisibleAttribute("04#" + "Required");
		semGeneralElement.addPropVisibleAttribute("05#" + "Scope");
		semGeneralElement.addPropVisibleAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");
		semGeneralElement.addPropVisibleAttribute("07#" + "Core");
		semGeneralElement.addPropVisibleAttribute("08#" + "Dead");
		semGeneralElement
				.addPropVisibleAttribute("08#" + "IgnoreForSimulation");

		semGeneralElement.addPanelVisibleAttribute("00#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false");
		semGeneralElement.addPanelSpacersAttribute("<<#" + "ConcernLevel"
				+ "#>>\n");

		// Configuration attributes: do no change identifiers

		attribute = new GlobalConfigAttribute("Active", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Is Active", true, 0, -1, "",
				"", -1, "", "");
		semGeneralElement.putSemanticAttribute("Active", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);

		attribute = new GlobalConfigAttribute("Visibility", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Is Visible", true, 0, -1,
				"", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("Visibility", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new GlobalConfigAttribute("Allowed", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Is Allowed", true, 0, -1,
				"", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("Allowed", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);

		attribute = new SemanticAttribute("RequiredLevel", "Integer",
				AttributeType.OPERATION, "Required Level", 0, false,
				new RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("RequiredLevel", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulOperationSubAction.addOutVariable(attribute);
		// TODO define domain or Enum Level

		attribute = new GlobalConfigAttribute("ConfigSelected", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Configuration Selected",
				false, 2, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("ConfigSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulOperationSubAction.addInVariable(attribute);

		attribute = new GlobalConfigAttribute("ConfigNotSelected", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Configuration Not Selected",
				false, 2, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("ConfigNotSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulOperationSubAction.addInVariable(attribute);

		attribute = new GlobalConfigAttribute("DashBoardVisible", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Visible on Dashboard",
				true, 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("DashBoardVisible", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new GlobalConfigAttribute("ExportOnConfig", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Export on Configuration",
				true, 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("ExportOnConfig", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		semGeneralElement.addPropEditableAttribute("15#" + "ConfigSelected"
				+ "#" + "Core" + "#==#" + "false" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("16#" + "ConfigNotSelected"
				+ "#" + "Dead" + "#==#" + "false" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("03#" + "DashBoardVisible");
		semGeneralElement.addPropEditableAttribute("04#" + "ExportOnConfig");

		semGeneralElement.addPropVisibleAttribute("01#" + "Active");
		semGeneralElement.addPropVisibleAttribute("02#" + "Visibility");

		semGeneralElement.addPropVisibleAttribute("03#" + "DashBoardVisible");
		semGeneralElement.addPropVisibleAttribute("04#" + "ExportOnConfig");
		semGeneralElement.addPropVisibleAttribute("05#" + "RequiredLevel" + "#"
				+ "Core" + "#==#" + "true");

		semGeneralElement.addPropVisibleAttribute("15#" + "ConfigSelected"
				+ "#" + "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropVisibleAttribute("16#" + "ConfigNotSelected"
				+ "#" + "Active" + "#==#" + "true" + "#" + "false");

		// Simulation attributes: do not modify identifiers

		attribute = new ExecCurrentStateAttribute("InitialRequiredLevel",
				"Integer", AttributeType.EXECCURRENTSTATE, false,
				"Initial Required Level", 0, new RangeDomain(0, 5), 0, -1, "",
				"", -1, "", "");
		semGeneralElement.putSemanticAttribute("InitialRequiredLevel",
				attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulationOperationAction.addInVariable(attribute);

		attribute = new ExecCurrentStateAttribute("SimRequiredLevel",
				"Integer", AttributeType.EXECCURRENTSTATE, false,
				"Required Level", 0, new RangeDomain(0, 5), 0, -1, "", "", -1,
				"", "");
		semGeneralElement.putSemanticAttribute("SimRequiredLevel", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ExecCurrentStateAttribute("HasParent", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "Has Parent", true, 0,
				-1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("HasParent", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulOperationSubAction.addOutVariable(attribute);

		attribute = new ExecCurrentStateAttribute("Opt", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "FilterVariable", 0,
				new RangeDomain(0, 20), 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("Opt", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ExecCurrentStateAttribute("Order", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "SortVariable", 0,
				new RangeDomain(0, 40), 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("Order", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);

		attribute = new ExecCurrentStateAttribute("NextNotSelected", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Not selected(inactive)", false, 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("NextNotSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulOperationSubAction.addOutVariable(attribute);

		attribute = new ExecCurrentStateAttribute("NextPrefSelected",
				"Boolean", AttributeType.EXECCURRENTSTATE, false,
				"Selected by configuration", false, 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("NextPrefSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulOperationSubAction.addInVariable(attribute);

		attribute = new ExecCurrentStateAttribute("NextNotPrefSelected",
				"Boolean", AttributeType.EXECCURRENTSTATE, false,
				"Not Selected by configuration", false, 0, -1, "", "", -1, "",
				"");
		semGeneralElement
				.putSemanticAttribute("NextNotPrefSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulOperationSubAction.addInVariable(attribute);

		attribute = new ExecCurrentStateAttribute("NextReqSelected", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected by simulation", false, 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("NextReqSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulOperationSubAction.addOutVariable(attribute);

		semGeneralElement.addPropVisibleAttribute("01#" + "Selected");
		semGeneralElement.addPropVisibleAttribute("03#" + "NextPrefSelected");
		semGeneralElement.addPropVisibleAttribute("05#" + "NextReqSelected");

		semGeneralElement.addPropVisibleAttribute("02#" + "NotAvailable");
		semGeneralElement.addPropVisibleAttribute("04#" + "NextNotSelected");
		semGeneralElement
				.addPropVisibleAttribute("06#" + "NextNotPrefSelected");

		SemanticConcept semHardConcept = new SemanticConcept(semGeneralElement,
				"semHardConcept");

		attribute = new SemanticAttribute("satisfactionType", "Enumeration",
				AttributeType.OPERATION, false, "satisfactionType",
				"com.variamos.semantic.types.SatisfactionType", "achieve", "",
				0, -1, "", "", -1, "", "");
		semHardConcept.putSemanticAttribute("satisfactionType", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		semHardConcept.addPropEditableAttribute("01#" + "satisfactionType");
		semHardConcept.addPropVisibleAttribute("01#" + "satisfactionType");

		InstVertex instVertexHC = new InstConcept("HardConcept", metaConcept,
				semHardConcept);
		refas.getVariabilityVertex().put("HardConcept", instVertexHC);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semHardConcept.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", 4);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Sum"), t1, 0);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("Order...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Order", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("hctoge", instEdge);
		instEdge.setIdentifier("hctoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexHC, true);

		// Feature concepts

		SemanticConcept semFeature = new SemanticConcept(semGeneralElement,
				"Feature");
		InstVertex instVertexF = new InstConcept("Feature", metaConcept,
				semFeature);

		attribute = new SemanticAttribute("IsRootFeature", "Boolean",
				AttributeType.OPERATION, true, "Is a Root Feature Concept",
				false, 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		semFeature.putSemanticAttribute("IsRootFeature", attribute);
		simulOperationSubAction.addOutVariable(attribute);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semFeature.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", 4);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Sum"), t1, 0);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("Order...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Order", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexGE, "IsRootFeature", 1);

		SemanticExpression t2_1 = new SemanticExpression("2-1", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"userIdentifier", "mandatory");

		SemanticExpression t2_2 = new SemanticExpression("2-2", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"userIdentifier", "optional");

		t2 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Or"), t2_1, t2_2);

		t3 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("And"), 0, true, t2);

		t3 = new SemanticExpression("3-", refas.getSemanticExpressionTypes()
				.get("Equals"), 0, false, t3);

		t1 = new SemanticExpression("IsRootFeature=...", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		// t1 = new SemanticExpression("1",
		// refas.getSemanticExpressionTypes().get(
		// "Equals"), instVertexGE, "IsRootFeature", 0);
		//
		// t3 = new SemanticExpression("3",
		// refas.getSemanticExpressionTypes().get(
		// "NotEquals"), instVertexGE, "userIdentifier", "Feature");
		//
		// t1 = new SemanticExpression("Not Feat Implies NoRoot", refas
		// .getSemanticExpressionTypes().get("Implies"), t3, t1);
		//
		// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

		// semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexGE, "IsRootFeature", 1);

		t3 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexGE, "Selected", 1);

		t1 = new SemanticExpression("Root Implies Req", refas
				.getSemanticExpressionTypes().get("Implies"), t1, t3);

		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexGE, "IsRootFeature", 1);

		t3 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexGE, "Selected", 1);

		t1 = new SemanticExpression("Root Implies Selected", refas
				.getSemanticExpressionTypes().get("Implies"), t1, t3);

		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		refas.getVariabilityVertex().put("Feature", instVertexF);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ftoge", instEdge);
		instEdge.setIdentifier("ftoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexF, true);

		// definition of other concepts

		SemanticConcept semAssumption = new SemanticConcept(semHardConcept,
				"Assumption");
		InstVertex instVertexAS = new InstConcept("Assumption", metaConcept,
				semAssumption);
		refas.getVariabilityVertex().put("Assumption", instVertexAS);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("assutoge", instEdge);
		instEdge.setIdentifier("assutoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexAS, true);

		SemanticConcept semGoal = new SemanticConcept(semHardConcept, "Goal");
		semGoal.addPanelVisibleAttribute("01#" + "satisfactionType");
		semGoal.addPanelSpacersAttribute("<#" + "satisfactionType" + "#>\n");
		InstVertex instVertexG = new InstConcept("Goal", metaConcept, semGoal);
		refas.getVariabilityVertex().put("Goal", instVertexG);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("gtoge", instEdge);
		instEdge.setIdentifier("gtoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexG, true);

		SemanticConcept semOperationalization = new SemanticConcept(
				semHardConcept, "Operationalization");

		attribute = new SyntaxAttribute("attributeValue", "Set",
				AttributeType.SYNTAX, false, "values",
				InstAttribute.class.getCanonicalName(),
				new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "", "");
		semOperationalization.putSemanticAttribute("attributeValue", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		semOperationalization.addPropVisibleAttribute("06#" + "attributeValue");
		semOperationalization
				.addPropEditableAttribute("06#" + "attributeValue");

		InstVertex instVertexOper = new InstConcept("Operationalization",
				metaConcept, semOperationalization);
		refas.getVariabilityVertex().put("Operationalization", instVertexOper);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("opertoge", instEdge);
		instEdge.setIdentifier("opertoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexOper, true);

		SoftSemanticConcept semSoftgoal = new SoftSemanticConcept(
				semGeneralElement, "SoftGoal");

		StringDomain d = new StringDomain();
		d.add("low");
		d.add("high");
		d.add("close");
		attribute = new SemanticAttribute("satisficingLevel", "String",
				AttributeType.OPERATION, "Satisficing Level", "low", false, d,
				0, 10, "", "", -1, "", "");
		semSoftgoal.putSemanticAttribute("satisficingLevel", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulOperationSubAction.addInVariable(attribute);
		semSoftgoal.addPropEditableAttribute("11#" + "satisficingLevel");
		semSoftgoal.addPropVisibleAttribute("11#" + "satisficingLevel");

		attribute = new SemanticAttribute(
				SoftSemanticConcept.VAR_SATISFICINGTYPE, "Enumeration",
				AttributeType.OPERATION, false,
				SoftSemanticConcept.VAR_SATISFICINGTYPENAME,
				SoftSemanticConcept.VAR_SATISFICINGTYPECLASS,
				"Achieve as close as possible", "", 0, 10, "", "", -1, "", "");
		semSoftgoal.putSemanticAttribute(
				SoftSemanticConcept.VAR_SATISFICINGTYPE, attribute);

		attribute = new SemanticAttribute(
				SoftSemanticConcept.VAR_CONFREQLEVELTYPE, "Integer",
				AttributeType.OPERATION,
				SoftSemanticConcept.VAR_CONFREQLEVELTYPENAME, 0, false,
				new RangeDomain(0, 5), 0, 5, "Required" + "#==#" + "true" + "#"
						+ "0", "", -1, "", "");
		semSoftgoal.putSemanticAttribute(
				SoftSemanticConcept.VAR_CONFREQLEVELTYPE, attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulOperationSubAction.addInVariable(attribute);

		semSoftgoal.addPropEditableAttribute("10#"
				+ SoftSemanticConcept.VAR_SATISFICINGTYPE);
		semSoftgoal.addPropEditableAttribute("05#"
				+ SoftSemanticConcept.VAR_CONFREQLEVELTYPE + "#" + "Required"
				+ "#==#" + "true" + "#" + "0");

		semSoftgoal.addPropVisibleAttribute("10#"
				+ SoftSemanticConcept.VAR_SATISFICINGTYPE);
		semSoftgoal.addPropVisibleAttribute("05#"
				+ SoftSemanticConcept.VAR_CONFREQLEVELTYPE);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semSoftgoal.setSemanticExpressions(semanticExpressions);

		InstVertex instVertexSG = new InstConcept("Softgoal", metaConcept,
				semSoftgoal);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Subtraction"), instVertexGE, "Selected", 1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Product"), 8, true, t1);

		t3 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", 4);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Product"), t1, t3);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("Order...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Order", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("LessOrEquals"), instVertexSG, instVertexSG, "SDReqLevel",
				"ClaimExpLevel");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"), instVertexSG, "Selected", true, t1);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexSG, "satisficingLevel", "high");

		t1 = new SemanticExpression("high: SDReqLevel<=ClaimExpLevel...", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("GreaterOrEq"), instVertexSG, instVertexSG, "SDReqLevel",
				"ClaimExpLevel");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"), instVertexSG, "Selected", true, t1);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexSG, "satisficingLevel", "low");

		t1 = new SemanticExpression("low: SDReqLevel>=ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexSG, instVertexSG, "SDReqLevel",
				"ClaimExpLevel");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"), instVertexSG, "Selected", true, t1);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexSG, "satisficingLevel", "close");

		t1 = new SemanticExpression("close: SDReqLevel=ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		refas.getVariabilityVertex().put("Softgoal", instVertexSG);

		attribute = new ExecCurrentStateAttribute("SDReqLevel", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "Required Level by SD",
				0, new RangeDomain(0, 4), 2, -1, "", "", -1, "", "");
		semSoftgoal.putSemanticAttribute("SDReqLevel", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ExecCurrentStateAttribute("ClaimExpLevel", "Integer",
				AttributeType.EXECCURRENTSTATE, false,
				"Expected Level by Claim", 0, new RangeDomain(0, 4), 2, -1, "",
				"", -1, "", "");
		semSoftgoal.putSemanticAttribute("ClaimExpLevel", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		semSoftgoal.addPropVisibleAttribute("16#" + "SDReqLevel");
		semSoftgoal.addPropVisibleAttribute("16#" + "ClaimExpLevel");

		semSoftgoal.addPropEditableAttribute("18#" + "SDReqLevel");
		semSoftgoal.addPropEditableAttribute("18#" + "ClaimExpLevel");

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sgtoge", instEdge);
		instEdge.setIdentifier("sgtoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexSG, true);

		SemanticVariable semVariable = new SemanticVariable("Variable");

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semVariable.setSemanticExpressions(semanticExpressions);

		InstVertex instVertexVAR = new InstConcept("Variable", metaConcept,
				semVariable);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexVAR, instVertexVAR,
				"variableConfigValue", "value");

		t3 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexVAR, "variableConfigDomain", "");

		t1 = new SemanticExpression("varConfigVal=value=varConfigDomain", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		attribute = new GlobalConfigAttribute("DashBoardVisible", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Visible on Dashboard",
				true, 0, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("DashBoardVisible", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new GlobalConfigAttribute("ExportOnConfig", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Export on Configuration",
				true, 0, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("ExportOnConfig", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute("Scope", "Boolean",
				AttributeType.OPERATION, true, "Global Scope", true, 0, -1, "",
				"", -1, "", "");
		semVariable.putSemanticAttribute("Scope", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulOperationSubAction.addInVariable(attribute);
		// TODO use scope

		attribute = new SemanticAttribute("ConcernLevel", "Class",
				AttributeType.OPERATION, false, "Concern Level",
				InstConcept.class.getCanonicalName(), "CG", null, "", 0, -1,
				"", "", -1, "", "");
		semVariable.putSemanticAttribute("ConcernLevel", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);
		// TODO: use concern level

		attribute = new SemanticAttribute(SemanticVariable.VAR_NAME, "String",
				AttributeType.OPERATION, false, SemanticVariable.VAR_NAMENAME,
				"", 0, 1, "", "", -1, "", "");
		semVariable.putSemanticAttribute(SemanticVariable.VAR_NAME, attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute(SemanticVariable.VAR_VARIABLETYPE,
				"Enumeration", AttributeType.OPERATION, true,
				SemanticVariable.VAR_VARIABLETYPENAME,
				SemanticVariable.VAR_VARIABLETYPECLASS, "String", "", 0, 2, "",
				"", -1, "", SemanticVariable.VAR_VARIABLETYPE + "#!=#"
						+ "Enumeration");
		semVariable.putSemanticAttribute(SemanticVariable.VAR_VARIABLETYPE,
				attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute(SemanticVariable.VAR_VARIABLEDOMAIN,
				"String", AttributeType.OPERATION, false,
				SemanticVariable.VAR_VARIABLEDOMAINNAME, "0,1", 0, 3,
				SemanticVariable.VAR_VARIABLETYPE + "#==#" + "Integer",
				SemanticVariable.VAR_VARIABLETYPE + "#==#" + "Integer", -1, "",
				"");
		semVariable.putSemanticAttribute(SemanticVariable.VAR_VARIABLEDOMAIN,
				attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute(SemanticVariable.VAR_ENUMERATIONTYPE,
				"Class", AttributeType.OPERATION, false,
				SemanticVariable.VAR_ENUMERATIONTYPENAME,
				SemanticVariable.VAR_ENUMERATIONTYPECLASS, "ME", "String", "",
				0, 4, SemanticVariable.VAR_VARIABLETYPE + "#==#"
						+ "Enumeration", SemanticVariable.VAR_VARIABLETYPE
						+ "#==#" + "Enumeration", -1, "", "");
		semVariable.putSemanticAttribute(SemanticVariable.VAR_ENUMERATIONTYPE,
				attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		// TODO define domain for enumtype
		attribute = new ExecCurrentStateAttribute(SemanticVariable.VAR_VALUE,
				"Integer", AttributeType.EXECCURRENTSTATE, false,
				SemanticVariable.VAR_VALUENAME, 0, 1, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute(SemanticVariable.VAR_VALUE, attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute(SemanticVariable.VAR_CONTEXT,
				"Boolean", AttributeType.OPERATION, false,
				SemanticVariable.VAR_CONTEXTNAME, false, 0, 5, "", "", -1, "",
				"");
		semVariable.putSemanticAttribute(SemanticVariable.VAR_CONTEXT,
				attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute(SemanticVariable.VAR_EXTVISIBLE,
				"Boolean", AttributeType.OPERATION, false,
				SemanticVariable.VAR_EXTVISIBLENAME, false, 0, 8, "", "", -1,
				"", "");
		semVariable.putSemanticAttribute(SemanticVariable.VAR_EXTVISIBLE,
				attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute(SemanticVariable.VAR_EXTCONTROL,
				"Boolean", AttributeType.OPERATION, false,
				SemanticVariable.VAR_EXTCONTROLNAME, false, 0, 9, "", "", -1,
				"", "");
		semVariable.putSemanticAttribute(SemanticVariable.VAR_EXTCONTROL,
				attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new GlobalConfigAttribute(
				SemanticVariable.VAR_VARIABLECONFIGVALUE, "Integer",
				AttributeType.GLOBALCONFIG, false,
				SemanticVariable.VAR_VARIABLECONFIGVALUENAME, 0, 0, -1, "", "",
				-1, "", "");
		semVariable.putSemanticAttribute(
				SemanticVariable.VAR_VARIABLECONFIGVALUE, attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new GlobalConfigAttribute(
				SemanticVariable.VAR_VARIABLECONFIGDOMAIN, "String",
				AttributeType.GLOBALCONFIG, false,
				SemanticVariable.VAR_VARIABLECONFIGDOMAINNAME, "", 0, 1,
				SemanticVariable.VAR_VARIABLETYPE + "#==#" + "Integer" + "||"
						+ SemanticVariable.VAR_VARIABLETYPE + "#==#"
						+ "Enumeration" + "||"
						+ SemanticVariable.VAR_VARIABLETYPE + "#==#"
						+ "Boolean", "", -1, "", "");
		semVariable.putSemanticAttribute(
				SemanticVariable.VAR_VARIABLECONFIGDOMAIN, attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		semVariable.addPropEditableAttribute("01#" + SemanticVariable.VAR_NAME);
		semVariable.addPropEditableAttribute("02#"
				+ SemanticVariable.VAR_VARIABLETYPE);
		semVariable.addPropEditableAttribute("03#"
				+ SemanticVariable.VAR_VARIABLEDOMAIN);
		semVariable.addPropEditableAttribute("04#"
				+ SemanticVariable.VAR_ENUMERATIONTYPE);
		semVariable.addPropEditableAttribute("05#"
				+ SemanticVariable.VAR_CONTEXT);

		semVariable.addPropEditableAttribute("08#"
				+ SemanticVariable.VAR_EXTVISIBLE);
		semVariable.addPropEditableAttribute("09#"
				+ SemanticVariable.VAR_EXTCONTROL);

		semVariable.addPropEditableAttribute("01#"
				+ SemanticVariable.VAR_VARIABLECONFIGDOMAIN);

		semVariable.addPropVisibleAttribute("01#" + SemanticVariable.VAR_NAME);
		semVariable.addPropVisibleAttribute("02#"
				+ SemanticVariable.VAR_VARIABLETYPE);
		semVariable.addPropVisibleAttribute("03#"
				+ SemanticVariable.VAR_VARIABLEDOMAIN + "#"
				+ SemanticVariable.VAR_VARIABLETYPE + "#==#" + "Integer");
		semVariable.addPropVisibleAttribute("04#"
				+ SemanticVariable.VAR_ENUMERATIONTYPE + "#"
				+ SemanticVariable.VAR_VARIABLETYPE + "#==#" + "Enumeration");
		semVariable.addPropVisibleAttribute("05#"
				+ SemanticVariable.VAR_CONTEXT);

		semVariable.addPropVisibleAttribute("06#" + SemanticVariable.VAR_VALUE);
		semVariable.addPropVisibleAttribute("07#" + SemanticVariable.VAR_VALUE);
		semVariable.addPropVisibleAttribute("08#"
				+ SemanticVariable.VAR_EXTVISIBLE);
		semVariable.addPropVisibleAttribute("09#"
				+ SemanticVariable.VAR_EXTCONTROL);

		semVariable.addPropVisibleAttribute("01#"
				+ SemanticVariable.VAR_VARIABLECONFIGDOMAIN + "#"
				+ SemanticVariable.VAR_VARIABLETYPE + "#==#" + "Enumeration");
		semVariable.addPropVisibleAttribute("01#"
				+ SemanticVariable.VAR_VARIABLECONFIGDOMAIN + "#"
				+ SemanticVariable.VAR_VARIABLETYPE + "#==#" + "Integer");
		semVariable.addPropVisibleAttribute("01#"
				+ SemanticVariable.VAR_VARIABLECONFIGDOMAIN + "#"
				+ SemanticVariable.VAR_VARIABLETYPE + "#==#" + "Boolean");

		semVariable.addPanelVisibleAttribute("05#"
				+ SemanticVariable.VAR_VARIABLETYPE + "#"
				+ SemanticVariable.VAR_VARIABLETYPE + "#!=#" + "Enumeration");
		semVariable.addPanelVisibleAttribute("06#"
				+ SemanticVariable.VAR_ENUMERATIONTYPE + "#"
				+ SemanticVariable.VAR_VARIABLETYPE + "#==#" + "Enumeration");
		semVariable.addPanelVisibleAttribute("07#"
				+ SemanticVariable.VAR_VARIABLEDOMAIN + "#"
				+ SemanticVariable.VAR_VARIABLETYPE + "#==#" + "Integer");
		semVariable.addPanelSpacersAttribute("{#"
				+ SemanticVariable.VAR_VARIABLETYPE + "#} ");

		semVariable.addPanelSpacersAttribute("{#"
				+ SemanticVariable.VAR_VARIABLEDOMAIN + "#} ");

		semVariable.addPropEditableAttribute("03#" + "DashBoardVisible");
		semVariable.addPropEditableAttribute("04#" + "ExportOnConfig");
		semVariable.addPropVisibleAttribute("03#" + "DashBoardVisible");
		semVariable.addPropVisibleAttribute("04#" + "ExportOnConfig");

		semVariable.addPropEditableAttribute("05#" + "Scope");
		semVariable.addPropEditableAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");

		semVariable.addPropVisibleAttribute("05#" + "Scope");
		semVariable.addPropVisibleAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");

		semVariable.addPanelVisibleAttribute("00#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false");
		semVariable.addPanelSpacersAttribute("<<#" + "ConcernLevel" + "#>>\n");

		refas.getVariabilityVertex().put("Variable", instVertexVAR);

		SemanticContextGroup semContextGroup = new SemanticContextGroup(
				"ConcernLevel");
		InstVertex instVertexCG = new InstConcept("ConcernLevel", metaConcept,
				semContextGroup);
		refas.getVariabilityVertex().put("ConcernLevel", instVertexCG);

		SemanticConcept semAsset = new SemanticConcept(semGeneralElement,
				"Asset");
		InstVertex instVertexAsset = new InstConcept("Asset", metaConcept,
				semAsset);
		refas.getVariabilityVertex().put("Asset", instVertexAsset);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semAsset.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", 4);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Sum"), t1, 0);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("Order...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Order", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("astoge", instEdge);
		instEdge.setIdentifier("astoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		List<IntSemanticRelationType> claimSemOverTwoRelList = new ArrayList<IntSemanticRelationType>();
		claimSemOverTwoRelList.add(new SemanticRelationType("and", "And",
				"And", false, false, false, 2, -1, 1, 1));
		claimSemOverTwoRelList.add(new SemanticRelationType("or", "Or", "Or",
				false, true, true, 2, -1, 1, 1));
		claimSemOverTwoRelList.add(new SemanticRelationType("mutex", "Mutex",
				"Mutex.", false, true, true, 2, -1, 1, 1));

		SemanticReasoningConcept semClaim = new SemanticReasoningConcept(
				semGeneralElement, "Claim", true, claimSemOverTwoRelList);
		InstVertex instVertexCL = new InstConcept("Claim", metaOverTwoRelation,
				semClaim);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semClaim.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", 4);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Sum"), t1, 0);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("Order...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Order", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		List<IntSemanticRelationType> operclaimPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		operclaimPairwiseRelList.add(new SemanticRelationType("OperToClaim",
				"", "", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation directOperClaimSemanticEdge = new SemanticPairwiseRelation(
				"OperClaimPWAsso", true, operclaimPairwiseRelList);

		InstConcept instDirOperClaimSemanticEdge = new InstConcept(
				"OperClaimPWAsso", metaPairwiseRelation,
				directOperClaimSemanticEdge);

		refas.getVariabilityVertex().put("OperClaimPWAsso",
				instDirOperClaimSemanticEdge);

		ia = instDirOperClaimSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("OperToClaim", new OptionAttribute(
				"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "OperToClaim", "", 1, -1, "", "", -1, "", ""),
				"OperToClaim#OperToClaim#true#true#true#1#-1#1#1"));

		ia = instDirOperClaimSemanticEdge
				.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexOper,
				instVertexCL, "Selected", "ConditionalExpression");

		t1 = new SemanticExpression("OPERCLSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE, instVertexCL,
				"Selected", true, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("OPERCLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexOper,
				instVertexCL, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("OperToClaim", new OptionAttribute(
				"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "OperToClaim", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		// ia = instVertexCL.getInstAttribute("relationTypesAttributes");
		// ias = (List<InstAttribute>) ia.getValue();
		// ias.add(new InstAttribute("and", new AbstractAttribute("and",
		// StringType.IDENTIFIER, false, "and", "", 1, -1, "", "", -1, "",
		// ""), "and#and#true#true#true#1#-1#1#1"));
		//
		// ias.add(new InstAttribute("or", new AbstractAttribute("or",
		// StringType.IDENTIFIER, false, "or", "", 1, -1, "", "", -1, "",
		// ""), "or#or#false#true#true#1#-1#1#1"));
		//
		// ias.add(new InstAttribute("mutex", new AbstractAttribute("mutex",
		// StringType.IDENTIFIER, false, "mutex", "", 1, -1, "", "", -1,
		// "", ""), "mutex#mutex#false#true#true#1#-1#1#1"));
		//
		// ia = instVertexCL.getInstAttribute("operationsExpressions");
		// ias = (List<InstAttribute>) ia.getValue();
		//
		// semanticExpressions = new ArrayList<IntSemanticExpression>();
		//
		// t2 = new SemanticExpression("1",
		// refas.getSemanticExpressionTypes().get(
		// "Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
		// instVertexCL, instVertexOper, null, "Selected", "True", true);
		//
		// t1 = new SemanticExpression("1",
		// refas.getSemanticExpressionTypes().get(
		// "GreaterOrEq"), t2, ExpressionVertexType.RIGHTRELATIONCONCEPT,
		// instVertexCL, "LowRange");
		//
		// t2 = new SemanticExpression("1",
		// refas.getSemanticExpressionTypes().get(
		// "Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
		// instVertexCL, instDirOperClaimSemanticEdge, null, "Selected",
		// "True", true);
		//
		// t3 = new SemanticExpression("1",
		// refas.getSemanticExpressionTypes().get(
		// "LessOrEquals"), t2, ExpressionVertexType.RIGHTRELATIONCONCEPT,
		// instVertexCL, "HighRange");
		//
		// t1 = new SemanticExpression("3",
		// refas.getSemanticExpressionTypes().get(
		// "And"), t1, t3);
		//
		// t1 = new SemanticExpression("ANDLowRange", refas
		// .getSemanticExpressionTypes().get("DoubleImplies"),
		// instVertexCL, "Selected", true, t1);
		//
		// simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		// refas.updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		// semanticExpressions.add(t1);
		//
		// ias.add(new InstAttribute("and", new AbstractAttribute("and",
		// StringType.IDENTIFIER, false, "and", "", 1, -1, "", "", -1, "",
		// ""), semanticExpressions));
		//
		// semanticExpressions = new ArrayList<IntSemanticExpression>();
		//
		// t1 = new SemanticExpression("1",
		// refas.getSemanticExpressionTypes().get(
		// "And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
		// instVertexHC, "Selected", "Selected");
		//
		// t3 = new SemanticExpression("3",
		// refas.getSemanticExpressionTypes().get(
		// "Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// instVertexHC, "Selected");
		//
		// t1 = new SemanticExpression("ORSelected", refas
		// .getSemanticExpressionTypes().get("And"), t3, t1);
		//
		// simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		// refas.updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		// semanticExpressions.add(t1);
		//
		// ias.add(new InstAttribute("or", new AbstractAttribute("or",
		// StringType.IDENTIFIER, false, "or", "", 1, -1, "", "", -1, "",
		// ""), semanticExpressions));
		//
		// semanticExpressions = new ArrayList<IntSemanticExpression>();
		//
		// t1 = new SemanticExpression("1",
		// refas.getSemanticExpressionTypes().get(
		// "And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
		// instVertexHC, "Selected", "Selected");
		//
		// t3 = new SemanticExpression("3",
		// refas.getSemanticExpressionTypes().get(
		// "Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// instVertexHC, "Selected");
		//
		// t1 = new SemanticExpression("MUTEXSelected", refas
		// .getSemanticExpressionTypes().get("And"), t3, t1);
		//
		// simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		// semanticExpressions.add(t1);
		//
		// ias.add(new InstAttribute("mutex", new AbstractAttribute("mutex",
		// StringType.IDENTIFIER, false, "mutex", "", 1, -1, "", "", -1,
		// "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		refas.getVariabilityVertex().put("Claim", instVertexCL);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("cltoge", instEdge);
		instEdge.setIdentifier("cltoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexCL, true);

		attribute = new SemanticAttribute("ConditionalExpression",
				InstanceExpression.class.getCanonicalName(),
				AttributeType.OPERATION, false, "Conditional Expression", null,
				0, -1, "", "", -1, "", "");
		semClaim.putSemanticAttribute("ConditionalExpression", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);

		attribute = new GlobalConfigAttribute("CompExp", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Boolean Comp. Expression",
				true, 0, -1, "", "", -1, "", "");
		semClaim.putSemanticAttribute("CompExp", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute("ConfidenceLevel", "Integer",
				AttributeType.OPERATION, "Confidence Level", 1, false,
				new RangeDomain(1, 5), 0, -1, "", "", -1, "", "");
		semClaim.putSemanticAttribute("ConfidenceLevel", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);

		attribute = new GlobalConfigAttribute("ClaimSelected", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Claim Selected", false, 0,
				-1, "", "", -1, "", "");
		semClaim.putSemanticAttribute("ClaimSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute("ClaimExpression", "String",
				AttributeType.OPERATION, false, "Claim Expression Text", "", 0,
				-1, "", "", -1, "", "");
		semClaim.putSemanticAttribute("ClaimExpression", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);

		// semClaim.addPanelVisibleAttribute("01#" + "Operationalizations");
		semClaim.addPanelVisibleAttribute("03#" + "ConditionalExpression"); // TODO
																			// move
																			// to
																			// semantic
																			// attributes

		// semClaim.addPropEditableAttribute("01#" + "Operationalizations");
		semClaim.addPropEditableAttribute("03#" + "ConditionalExpression");
		semClaim.addPropEditableAttribute("04#" + "ClaimExpression");
		semClaim.addPropEditableAttribute("05#" + "ConfidenceLevel");

		// semClaim.addPropVisibleAttribute("01#" + "Operationalizations");

		semClaim.addPropVisibleAttribute("03#" + "ConditionalExpression");
		semClaim.addPropVisibleAttribute("04#" + "ClaimExpression");
		semClaim.addPropVisibleAttribute("05#" + "ConfidenceLevel");

		semClaim.addPropEditableAttribute("01#" + "CompExp");
		semClaim.addPropVisibleAttribute("01#" + "CompExp");

		semClaim.addPropVisibleAttribute("02#" + "ClaimSelected");

		semClaim.addPanelVisibleAttribute("10#" + "ClaimExpression");

		// semClaim.addPanelSpacersAttribute("#" + "Operationalizations" +
		// "#\n#");

		SemanticReasoningConcept semSoftDependency = new SemanticReasoningConcept(
				semGeneralElement, "SoftDependency", true, null);
		InstVertex instVertexSD = new InstConcept("SoftDep", metaConcept,
				semSoftDependency);
		refas.getVariabilityVertex().put("SoftDep", instVertexSD);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semSoftDependency.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", 4);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Sum"), t1, 0);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("Order...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Order", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sdtoge", instEdge);
		instEdge.setIdentifier("sdtoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexSD, true);

		attribute = new SemanticAttribute("ConditionalExpression",
				InstanceExpression.class.getCanonicalName(),
				AttributeType.OPERATION, false, "Conditional Expression", null,
				0, -1, "", "", -1, "", "");
		semSoftDependency.putSemanticAttribute("ConditionalExpression",
				attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);

		attribute = new SemanticAttribute("SDExpression", "String",
				AttributeType.OPERATION, false, "SD Expression Text", "", 2,
				-1, "", "", -1, "", "");
		semSoftDependency.putSemanticAttribute("SDExpression", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new GlobalConfigAttribute("CompExp", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Boolean Comp. Expression",
				true, 2, -1, "", "", -1, "", "");
		semSoftDependency.putSemanticAttribute("CompExp", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new GlobalConfigAttribute("SDSelected", "Boolean",
				AttributeType.GLOBALCONFIG, false, "SD Selected", false, 2, -1,
				"", "", -1, "", "");
		semSoftDependency.putSemanticAttribute("SDSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		semSoftDependency.addPanelVisibleAttribute("03#"
				+ "ConditionalExpression");

		// semSoftDependency.addPanelVisibleAttribute("10#" + "SDExpression");

		semSoftDependency.addPropEditableAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropEditableAttribute("04#" + "SDExpression");

		semSoftDependency.addPropVisibleAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropVisibleAttribute("04#" + "SDExpression");

		semSoftDependency.addPropEditableAttribute("01#" + "CompExp");
		semSoftDependency.addPropVisibleAttribute("01#" + "CompExp");

		semSoftDependency.addPropVisibleAttribute("02#" + "SDSelected");

		semSoftDependency.addPanelVisibleAttribute("10#" + "SDExpression");

		// Elements Lists
		// List<AbstractSemanticVertex> semAssumptionElements = new
		// ArrayList<AbstractSemanticVertex>();
		// semAssumptionElements.add(semAssumption);

		// List<AbstractSemanticVertex> SemGoalOperElements = new
		// ArrayList<AbstractSemanticVertex>();
		// SemGoalOperElements.add(semGoal);
		// SemGoalOperElements.add(semOperationalization);

		// List<AbstractSemanticVertex> semGoalElements = new
		// ArrayList<AbstractSemanticVertex>();
		// semGoalElements.add(semGoal);
		//
		// List<AbstractSemanticVertex> semOperationalizationElements = new
		// ArrayList<AbstractSemanticVertex>();
		// semOperationalizationElements.add(semOperationalization);
		//
		// List<AbstractSemanticVertex> semSoftgoalElements = new
		// ArrayList<AbstractSemanticVertex>();
		// semSoftgoalElements.add(semSoftgoal);
		//
		// List<AbstractSemanticVertex> semClaimsElements = new
		// ArrayList<AbstractSemanticVertex>();
		// semClaimsElements.add(semClaim);
		//
		// List<AbstractSemanticVertex> semSDElements = new
		// ArrayList<AbstractSemanticVertex>();
		// semSDElements.add(semSoftDependency);

		// Relations

		List<IntSemanticRelationType> hardSemOverTwoRelList = new ArrayList<IntSemanticRelationType>();
		hardSemOverTwoRelList.add(new SemanticRelationType("and", "And", "And",
				false, false, false, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("or", "Or", "Or",
				false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("mutex", "Mutex",
				"Mutex.", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("range", "Range",
				"Range", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("none", "None",
				"None", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("other", "other",
				"other", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType(
				"And/Or/Mutex/[m,n]", "And/Or/Mutex/[m,n]", "AOMR", false,
				true, true, 2, -1, 1, 1));

		List<IntSemanticRelationType> featSemOverTwoRelList = new ArrayList<IntSemanticRelationType>();
		featSemOverTwoRelList.add(new SemanticRelationType("and", "And", "And",
				false, false, false, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("or", "Or", "Or",
				false, true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("mutex", "Mutex",
				"Mutex.", false, true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("range", "Range",
				"range", false, true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("other", "other",
				"other", false, true, true, 2, -1, 1, 1));

		SemanticConcept semGeneralGroup = new SemanticConcept("GeneralGroup");

		semGeneralGroup.putSemanticAttribute("Selected",
				new ExecCurrentStateAttribute("Selected", "Boolean",
						AttributeType.EXECCURRENTSTATE, false,
						"***Selected***", false, 2, -1, "", "", -1, "", ""));
		semGeneralGroup
				.putSemanticAttribute("NotAvailable",
						new ExecCurrentStateAttribute("NotAvailable",
								"Boolean", AttributeType.EXECCURRENTSTATE,
								false, "***Not Avaliable***", false, 2, -1, "",
								"", -1, "", ""));

		InstVertex instVertexGR = new InstConcept("GeneralGroup", metaConcept,
				semGeneralGroup);

		refas.getVariabilityVertex().put("GeneralGroup", instVertexGR);

		attribute = new ExecCurrentStateAttribute("True", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", true,
				2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralGroup.putSemanticAttribute("True", attribute);
		simulOperationSubAction.addInVariable(attribute);

		attribute = new ExecCurrentStateAttribute("False", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***NotSelected***",
				false, 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralGroup.putSemanticAttribute("False", attribute);
		simulOperationSubAction.addInVariable(attribute);

		attribute = new ExecCurrentStateAttribute("Selected", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", false,
				2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralGroup.putSemanticAttribute("Selected", attribute);
		simulOperationSubAction.addOutVariable(attribute);

		attribute = new ExecCurrentStateAttribute("NotAvailable", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Not Avaliable***",
				false, 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralGroup.putSemanticAttribute("NotAvailable", attribute);
		simulOperationSubAction.addOutVariable(attribute);

		attribute = new SemanticAttribute("Description", "String",
				AttributeType.OPERATION, false, "Description", "", 0, -1, "",
				"", -1, "", "");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralGroup.putSemanticAttribute("Description", attribute);

		SemanticOverTwoRelation semHardOverTwoRelation = new SemanticOverTwoRelation(
				semGeneralGroup, "SMMOverTwoRelation", hardSemOverTwoRelList);

		InstVertex instVertexHHGR = new InstConcept("GoalOTAsso",
				metaOverTwoRelation, semHardOverTwoRelation);
		refas.getVariabilityVertex().put("GoalOTAsso", instVertexHHGR);

		InstConcept instHchcHHGRHC = new InstConcept("GoaltoOTAssoPWAsso",
				metaPairwiseRelation);
		refas.getVariabilityVertex().put("GoaltoOTAssoPWAsso", instHchcHHGRHC);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("hhtoge", instEdge);
		instEdge.setIdentifier("hhtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGR, true);
		instEdge.setSourceRelation(instVertexHHGR, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("hctoHHGR-HHGR-HHHHGR", instEdge);
		instEdge.setIdentifier("hctoHHGR-HHGR-HHHHGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instHchcHHGRHC, true);
		instEdge.setSourceRelation(instVertexHHGR, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("hctoHHGR-HHHHGR-H", instEdge);
		instEdge.setIdentifier("hctoHHGR-HHHHGR-H");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instHchcHHGRHC, true);

		InstConcept instHchcHHGRGR = new InstConcept("GoalfromOTAssoPWAsso",
				metaPairwiseRelation);
		refas.getVariabilityVertex()
				.put("GoalfromOTAssoPWAsso", instHchcHHGRGR);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("HHGRtohc-H-HHHHGR", instEdge);
		instEdge.setIdentifier("HHGRtohc-H-HHHHGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instHchcHHGRGR, true);
		instEdge.setSourceRelation(instVertexHC, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("HHGRtohc-HHHHGR-H", instEdge);
		instEdge.setIdentifier("HHGRtohc-H-HHHHGR-H");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexHHGR, true);
		instEdge.setSourceRelation(instHchcHHGRGR, true);

		ia = instVertexHHGR.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("and", new OptionAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				1, -1, "", "", -1, "", ""), "and#and#true#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("or", new OptionAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				1, -1, "", "", -1, "", ""), "or#or#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("mutex", new OptionAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", 1, -1, "", "", -1, "", ""),
				"mutex#mutex#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("range", new OptionAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", 1, -1, "", "", -1, "", ""),
				"range#range#false#true#true#1#-1#1#1"));

		ia = instVertexHHGR.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
				instVertexHC, "Selected", "True");

		t1 = new SemanticExpression("ANDhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHC, t1, "Selected");

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("and", new OptionAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
				instVertexHC, "Selected", "False");

		t1 = new SemanticExpression("ORhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHC, t1, "Selected");

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("or", new OptionAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
				instVertexHC, "Selected", 0);

		t1 = new SemanticExpression("sub2", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, instVertexHC, t1, 1);

		t1 = new SemanticExpression("MUTEXhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexHC, "Selected", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("mutex", new OptionAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t2 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, instVertexHC, null, "Selected", "True", true);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("GreaterOrEq"), t2,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
				"LowRange");

		t2 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, instVertexHC, null, "Selected", "True", true);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("LessOrEquals"), t2,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
				"HighRange");

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("And"), t1, t3);

		t1 = new SemanticExpression("RANGEHardRel", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexHHGR,
				"Selected", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("range", new OptionAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		// List<AbstractSemanticVertex> semanticVertices = new
		// ArrayList<AbstractSemanticVertex>();

		List<IntSemanticRelationType> structHardSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		structHardSemPairwiseRelList.add(new SemanticRelationType("means_ends",
				"means-ends", "means-ends", true, true, true, 1, -1, 1, 1));
		structHardSemPairwiseRelList
				.add(new SemanticRelationType("implication", "impl.", "Impl.",
						false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> sideHardSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		sideHardSemPairwiseRelList.add(new SemanticRelationType("conflict",
				"conflict", "conflict", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new SemanticRelationType("alternative",
				"altern.", "altern.", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new SemanticRelationType("preferred",
				"pref.", "pref.", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new SemanticRelationType("require",
				"req.", "req.", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new SemanticRelationType("condition",
				"cond.", "cond.", false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> sgPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		sgPairwiseRelList.add(new SemanticRelationType("contribution",
				"contribution", "contribution", true, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("conflict", "conflict",
				"conflict", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("alternative",
				"altern.", "altern.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("preferred", "pref.",
				"pref.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("implication", "impl.",
				"Impl.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("require", "req.",
				"req.", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation directHardHardSemanticEdge = new SemanticPairwiseRelation(
				"GoalGoalSidePWAsso", false, sideHardSemPairwiseRelList);
		InstConcept instDirHardHardSemanticEdge = new InstConcept(
				"GoalGoalSidePWAsso", metaPairwiseRelation,
				directHardHardSemanticEdge);

		ia = instDirHardHardSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("conflict", new OptionAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", 1, -1, "", "", -1, "", ""),
				"conflict#conflict#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("alternative", new OptionAttribute(
				"alternative", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "alternative", "", 1, -1, "", "", -1, "", ""),
				"altern.#altern.#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("preferred", new OptionAttribute("preferred",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"preferred", "", 1, -1, "", "", -1, "", ""),
				"preferred#preferred#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("require", new OptionAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", 1, -1, "", "", -1, "", ""),
				"require#require#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("condition", new OptionAttribute("condition",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"condition", "", 1, -1, "", "", -1, "", ""),
				"condition#condition#false#true#true#1#-1#1#1"));

		ia = instDirHardHardSemanticEdge
				.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Selected", "Selected");

		t1 = new SemanticExpression("CONFSelected", refas
				.getSemanticExpressionTypes().get("Implies"), 1, false, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("conflict", new OptionAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexHC, instVertexHC, null, "Selected", true, 1);

		t1 = new SemanticExpression("ALTSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE, instVertexHC,
				"Selected", true, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("alternative", new OptionAttribute(
				"alternative", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "alternative", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Selected", "Selected");

		t3 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Negation"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE, instVertexHC,
				"Selected");

		t1 = new SemanticExpression("PREFSelected", refas
				.getSemanticExpressionTypes().get("And"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("preferred", new OptionAttribute("preferred",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"preferred", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Subtraction"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, instVertexHC, null, "Selected",
				false, 1);

		t1 = new SemanticExpression("REQSelected", refas
				.getSemanticExpressionTypes().get("GreaterOrEq"), 1, false, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("require", new OptionAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"condition", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("CONDSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("CONDNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("condition", new OptionAttribute("condition",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"condition", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		refas.getVariabilityVertex().put("GoalGoalSidePWAsso",
				instDirHardHardSemanticEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("GoalGoalSidePWAsso-GR", instEdge);
		instEdge.setIdentifier("GoalGoalSidePWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirHardHardSemanticEdge, true);
		instEdge.setSourceRelation(instVertexHC, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("GoalGoalSidePW-GR-Asso", instEdge);
		instEdge.setIdentifier("GoalGoalSidePW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instDirHardHardSemanticEdge, true);

		SemanticPairwiseRelation directStructHardHardSemanticEdge = new SemanticPairwiseRelation(
				"structHardHardPWAsso", false, structHardSemPairwiseRelList);

		InstConcept instDirStructHardHardSemanticEdge = new InstConcept(
				"structHardHardPWAsso", metaPairwiseRelation,
				directStructHardHardSemanticEdge);

		ia = instDirStructHardHardSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("means_ends", new OptionAttribute(
				"means_ends", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "means_ends", "", 1, -1, "", "", -1, "", ""),
				"means_ends#means-ends#true#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("implication", new OptionAttribute(
				"implication", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implication", "", 1, -1, "", "", -1, "", ""),
				"implication#implication#false#true#true#1#-1#1#1"));

		ia = instDirStructHardHardSemanticEdge
				.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("means_ends", new OptionAttribute(
				"means_ends", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "means_ends", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTRELVARIABLE,
				instDirStructHardHardSemanticEdge, null, instVertexHC,
				"Selected", true, 1);

		t1 = new SemanticExpression("Selected", refas
				.getSemanticExpressionTypes().get("Implies"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE, instVertexHC,
				"Selected", true, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("implication", new OptionAttribute(
				"implication", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implication", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		refas.getVariabilityVertex().put("structHardHardPWAsso",
				instDirStructHardHardSemanticEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("structHardHardPWAsso-GR", instEdge);
		instEdge.setIdentifier("structHardHardPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirStructHardHardSemanticEdge, true);
		instEdge.setSourceRelation(instVertexHC, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges()
				.put("structHardHardPW-GR-Asso", instEdge);
		instEdge.setIdentifier("structHardHardPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instDirStructHardHardSemanticEdge, true);

		List<IntSemanticRelationType> featSideSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		featSideSemPairwiseRelList.add(new SemanticRelationType("require",
				"require", "require", false, true, true, 1, -1, 1, 1));
		featSideSemPairwiseRelList.add(new SemanticRelationType("conflict",
				"excl.", "excl.", false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> featVertSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		featVertSemPairwiseRelList.add(new SemanticRelationType("mandatory",
				"mandatory", "mandatory", true, true, true, 1, -1, 1, 1));
		featVertSemPairwiseRelList.add(new SemanticRelationType("optional",
				"opt.", "opt.", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation directFeaFeatVertSemEdge = new SemanticPairwiseRelation(
				"FeatFeatParentPWAsso", false, featVertSemPairwiseRelList);
		InstConcept instDirFeaFeatVertSemEdge = new InstConcept(
				"FeatFeatParentPWAsso", metaPairwiseRelation,
				directFeaFeatVertSemEdge);

		ia = instDirFeaFeatVertSemEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("mandatory", new OptionAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", 1, -1, "", "", -1, "", ""),
				"mandatory#mandatory#true#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("optional", new OptionAttribute("optional",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "optional",
				"", 1, -1, "", "", -1, "", ""),
				"optional#optional#false#true#true#1#-1#1#1"));

		ia = instDirFeaFeatVertSemEdge
				.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("MANSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("MANNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("mandatory", new OptionAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("OPTSelected", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("OPTNotAvailable", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE, instVertexF,
				instVertexF, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		attribute = new OptionAttribute("optional", StringType.IDENTIFIER,
				AttributeType.OPTION, false, "optional", "", 1, -1, "", "", -1,
				"", "");
		ias.add(new InstAttribute("optional", attribute, semanticExpressions));
		simulOperationSubAction.addInVariable(attribute);

		refas.getVariabilityVertex().put("FeatFeatParentPWAsso",
				instDirFeaFeatVertSemEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("FeatFeatParentPWAsso-GR", instEdge);
		instEdge.setIdentifier("FeatFeatParentPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirFeaFeatVertSemEdge, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges()
				.put("FeatFeatParentPW-GR-Asso", instEdge);
		instEdge.setIdentifier("FeatFeatParentPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instDirFeaFeatVertSemEdge, true);

		SemanticPairwiseRelation directFeatFeatSideSemEdge = new SemanticPairwiseRelation(
				"FeatFeatSidePWAsso", false, featSideSemPairwiseRelList);
		InstConcept instDirFeatFeatSideSemEdge = new InstConcept(
				"FeatFeatSidePWAsso", metaPairwiseRelation,
				directFeatFeatSideSemEdge);

		ia = instDirFeatFeatSideSemEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("conflict", new OptionAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", 1, -1, "", "", -1, "", ""),
				"conflict#conflict#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("require", new OptionAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", 1, -1, "", "", -1, "", ""),
				"require#require#false#true#true#1#-1#1#1"));

		ia = instDirFeatFeatSideSemEdge
				.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "Selected", "Selected");

		t1 = new SemanticExpression("CONFSelected", refas
				.getSemanticExpressionTypes().get("Implies"), 1, false, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("conflict", new OptionAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Subtraction"),
				ExpressionVertexType.LEFTUNIQUEOUTRELVARIABLE,
				instDirFeatFeatSideSemEdge, null, instVertexF, "Selected",
				false, 1);

		t1 = new SemanticExpression("REQSelected", refas
				.getSemanticExpressionTypes().get("GreaterOrEq"), 1, false, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("require", new OptionAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"condition", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		refas.getVariabilityVertex().put("FeatFeatSidePWAsso",
				instDirFeatFeatSideSemEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("FeatFeatSidePWAsso-GR", instEdge);
		instEdge.setIdentifier("FeatFeatSidePWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirFeatFeatSideSemEdge, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("FeatFeatSidePW-GR-Asso", instEdge);
		instEdge.setIdentifier("FeatFeatSidePW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instDirFeatFeatSideSemEdge, true);

		SemanticOverTwoRelation semFeatOverTwoRelation = new SemanticOverTwoRelation(
				semGeneralGroup, "FeatFeatOTAsso", featSemOverTwoRelList);
		InstVertex instVertexFFGR = new InstConcept("FeatFeatOTAsso",
				metaOverTwoRelation, semFeatOverTwoRelation);
		refas.getVariabilityVertex().put("FeatFeatOTAsso", instVertexFFGR);

		List<IntSemanticRelationType> assetoperPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		assetoperPairwiseRelList.add(new SemanticRelationType("implementation",
				"implementation", "imp.", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semAssetOperPairwiseRel = new SemanticPairwiseRelation(
				"varAssetOperPWAsso", false, assetoperPairwiseRelList);
		InstConcept instSemAssetOperPairwiseRel = new InstConcept(
				"varAssetOperPWAsso", metaPairwiseRelation,
				semAssetOperPairwiseRel);

		ia = instDirStructHardHardSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("implementation", new OptionAttribute(
				"implementation", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implementation", "", 1, -1, "", "", -1, "", ""),
				"implementation#implementation#false#true#true#1#-1#1#1"));

		ia = instDirStructHardHardSemanticEdge
				.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("IMPLSelected1", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexHC, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("IMPLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexHC, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("implementation", new OptionAttribute(
				"implementation", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implementation", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		refas.getVariabilityVertex().put("varAssetOperPWAsso",
				instSemAssetOperPairwiseRel);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("varAssetOperPWAsso-GR", instEdge);
		instEdge.setIdentifier("varAssetOperPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSemAssetOperPairwiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("varAssetOperPW-GR-Asso", instEdge);
		instEdge.setIdentifier("varAssetOperPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instSemAssetOperPairwiseRel, true);

		List<IntSemanticRelationType> assetPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		assetPairwiseRelList.add(new SemanticRelationType("delegation",
				"delegation", "deleg.", true, true, true, 1, 1, 1, 1));
		assetPairwiseRelList.add(new SemanticRelationType("assembly",
				"assembly", "assembly", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semAssetPairwiseRel = new SemanticPairwiseRelation(
				"varAssetPWAsso", false, assetPairwiseRelList);
		InstConcept instSemAssetPairwiseRel = new InstConcept("varAssetPWAsso",
				metaPairwiseRelation, semAssetPairwiseRel);

		ia = instSemAssetPairwiseRel
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("mandatory", new OptionAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", 1, -1, "", "", -1, "", ""),
				"mandatory#mandatory#true#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("optional", new OptionAttribute("optional",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "optional",
				"", 1, -1, "", "", -1, "", ""),
				"optional#optional#false#true#true#1#-1#1#1"));

		ia = instSemAssetPairwiseRel.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("DELSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexAsset, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("DELNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexAsset, "NotAvailable",
				"NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("delegation", new OptionAttribute(
				"delegation", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "delegation", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();
		t1 = new SemanticExpression("ASSESelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexAsset, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("ASSENotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("assembly", new OptionAttribute("assembly",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "assembly",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		refas.getVariabilityVertex().put("varAssetPWAsso",
				instSemAssetPairwiseRel);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("varAssetPWAsso-GR", instEdge);
		instEdge.setIdentifier("varAssetPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSemAssetPairwiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("varAssetPW-GR-Asso", instEdge);
		instEdge.setIdentifier("varAssetPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instSemAssetPairwiseRel, true);

		List<IntSemanticRelationType> vcntxPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		vcntxPairwiseRelList.add(new SemanticRelationType("Variable Context",
				"", "", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semvarcntxPairwiseRel = new SemanticPairwiseRelation(
				"varcntxPWAsso", false, vcntxPairwiseRelList);

		InstConcept instSemvarcntxPairwiseRel = new InstConcept(
				"varcntxPWAsso", metaPairwiseRelation, semvarcntxPairwiseRel);

		ia = instSemvarcntxPairwiseRel
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("Variable Context",
				new OptionAttribute("Variable Context", StringType.IDENTIFIER,
						AttributeType.OPTION, false, "", "", 1, -1, "", "", -1,
						"", ""),
				"Variable Context#Variable Context#false#true#true#1#-1#1#1"));

		ia = instSemvarcntxPairwiseRel
				.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		ias.add(new InstAttribute("Variable Context", new OptionAttribute(
				"Variable Context", StringType.IDENTIFIER,
				AttributeType.OPTION, false, "Variable Context", "", 1, -1, "",
				"", -1, "", ""), semanticExpressions));

		refas.getVariabilityVertex().put("varcntxPWAsso",
				instSemvarcntxPairwiseRel);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("varcntxPWAsso-GR", instEdge);
		instEdge.setIdentifier("varcntxPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSemvarcntxPairwiseRel, true);
		instEdge.setSourceRelation(instVertexVAR, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("varcntxPW-GR-Asso", instEdge);
		instEdge.setIdentifier("varcntxPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instSemvarcntxPairwiseRel, true);

		List<IntSemanticRelationType> sdPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		sdPairwiseRelList.add(new SemanticRelationType("SD", "", "", true,
				true, true, 1, 1, 1, 1));

		/*
		 * SemanticPairwiseRelation semSDPairwiseRel = new
		 * SemanticPairwiseRelation( "sdPWAsso", false, sdPairwiseRelList);
		 * InstConcept instSemSDPairwiseRel = new InstConcept("sdPWAsso",
		 * metaPairwiseRelation, semSDPairwiseRel);
		 * refas.getVariabilityVertex().put("sdPWAsso", instSemSDPairwiseRel);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("sdPWAsso-GR", instEdge);
		 * instEdge.setIdentifier("sdPWAsso-GR");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		 * instEdge.setTargetRelation(instSemSDPairwiseRel, true);
		 * instEdge.setSourceRelation(instVertexSD, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("sdPW-GR-Asso", instEdge);
		 * instEdge.setIdentifier("sdPW-GR-Asso");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		 * instEdge.setTargetRelation(instVertexSG, true);
		 * instEdge.setSourceRelation(instSemSDPairwiseRel, true);
		 */

		/*
		 * SemanticPairwiseRelation semOperClaimPairwiseRel = new
		 * SemanticPairwiseRelation( "operclaimPWAsso", false,
		 * operclaimPairwiseRelList);
		 * 
		 * InstConcept instSemCLPWAsso = new InstConcept("operclaimPWAsso",
		 * metaPairwiseRelation, semOperClaimPairwiseRel);
		 * 
		 * refas.getVariabilityVertex().put("operclaimPWAsso", instSemCLPWAsso);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("operclaimPWAsso-GR", instEdge);
		 * instEdge.setIdentifier("operclaimPWAsso-GR");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		 * instEdge.setTargetRelation(instSemCLPWAsso, true);
		 * instEdge.setSourceRelation(instVertexOper, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("operclaimPW-GR-Asso", instEdge);
		 * instEdge.setIdentifier("operclaimPW-GR-Asso");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		 * instEdge.setTargetRelation(instVertexCL, true);
		 * instEdge.setSourceRelation(instSemCLPWAsso, true);
		 */

		List<IntSemanticRelationType> claimSGPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		claimSGPairwiseRelList.add(new SemanticRelationType("ClaimToSG", "",
				"", true, true, true, 1, 1, 1, 1));

		/*
		 * SemanticPairwiseRelation semClaimSGPairwiseRel = new
		 * SemanticPairwiseRelation( "claimSGPWAsso", false,
		 * claimSGPairwiseRelList); InstConcept instSemCLSGPWAsso = new
		 * InstConcept("claimSGPWAsso", metaPairwiseRelation,
		 * semClaimSGPairwiseRel);
		 * refas.getVariabilityVertex().put("claimSGPWAsso", instSemCLSGPWAsso);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("CLSGPWAsso-GR", instEdge);
		 * instEdge.setIdentifier("CLSGPWAsso-GR");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		 * instEdge.setTargetRelation(instSemCLSGPWAsso, true);
		 * instEdge.setSourceRelation(instVertexCL, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("CLSGPW-GR-Asso", instEdge);
		 * instEdge.setIdentifier("CLSGPW-GR-Asso");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		 * instEdge.setTargetRelation(instVertexSG, true);
		 * instEdge.setSourceRelation(instSemCLSGPWAsso, true);
		 */
		List<IntSemanticRelationType> groupPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		groupPairwiseRelList.add(new SemanticRelationType("Group", "", "",
				true, true, true, 1, 1, 1, 1));
		/*
		 * SemanticPairwiseRelation semGroupPairwiseRel = new
		 * SemanticPairwiseRelation( "groupPWAsso", false,
		 * groupPairwiseRelList); InstConcept instSemGroupPWAsso = new
		 * InstConcept("groupPWAsso", metaPairwiseRelation,
		 * semGroupPairwiseRel); refas.getVariabilityVertex().put("groupPWAsso",
		 * instSemGroupPWAsso);
		 * 
		 * semGroupPairwiseRel.putSemanticAttribute("AggregationLow1", new
		 * SemanticAttribute("AggregationLow1", "Integer", false,
		 * "Aggregation Low1", 0, 0, -1, "", "", -1, "", ""));
		 * 
		 * semGroupPairwiseRel.putSemanticAttribute("AggregationHigh1", new
		 * SemanticAttribute("AggregationHigh1", "Integer", false,
		 * "Aggregation High1", 0, 0, -1, "", "", -1, "", ""));
		 */
		List<IntSemanticRelationType> nonePairwiseRelList = new ArrayList<IntSemanticRelationType>();
		nonePairwiseRelList.add(new SemanticRelationType("Group", "", "", true,
				true, true, 1, 1, 1, 1));

		/*
		 * SemanticPairwiseRelation nonePairwiseRel = new
		 * SemanticPairwiseRelation( "NonePWAsso", false, nonePairwiseRelList);
		 * refas.getVariabilityVertex().put("NonePWAsso", new
		 * InstConcept("NonePWAsso", metaPairwiseRelation, nonePairwiseRel));
		 * 
		 * SemanticPairwiseRelation extendsPairwiseRel = new
		 * SemanticPairwiseRelation( "extendsPWAsso", false,
		 * nonePairwiseRelList);
		 * refas.getVariabilityVertex().put("extendsPWAsso", new InstConcept(
		 * "extendsPWAsso", metaPairwiseRelation, extendsPairwiseRel));
		 * 
		 * SemanticPairwiseRelation viewPairwiseRel = new
		 * SemanticPairwiseRelation( "viewPWAsso", false, nonePairwiseRelList);
		 * refas.getVariabilityVertex().put("viewPWAsso", new
		 * InstConcept("viewPWAsso", metaPairwiseRelation, viewPairwiseRel));
		 */
		List<IntSemanticRelationType> genconsPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		genconsPairwiseRelList.add(new SemanticRelationType(
				"GeneralConstraint", "", "", true, true, true, 1, 1, 1, 1));

		// Feature to Feature

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ffgrtogr", instEdge);
		instEdge.setIdentifier("ffgrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGR, true);
		instEdge.setSourceRelation(instVertexFFGR, true);

		InstConcept instFeatFeatFFFGR = new InstConcept(
				"FeatFeatToOTAssoPWAsso", metaPairwiseRelation);
		refas.getVariabilityVertex().put("FeatFeatToOTAssoPWAsso",
				instFeatFeatFFFGR);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("featfeatF-FFFGR", instEdge);
		instEdge.setIdentifier("featfeatF-FFFGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instFeatFeatFFFGR, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("featfeatFFFGR-FFGR", instEdge);
		instEdge.setIdentifier("featfeatFFFGR-FFGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexFFGR, true);
		instEdge.setSourceRelation(instFeatFeatFFFGR, true);

		InstConcept instFeatFeatFGRF = new InstConcept(
				"FeatFeatFromOTAssoPWAsso", metaPairwiseRelation);
		refas.getVariabilityVertex().put("FeatFeatFromOTAssoPWAsso",
				instFeatFeatFGRF);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("featfeatFFFGR-F", instEdge);
		instEdge.setIdentifier("featfeatFFFGR-F");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instFeatFeatFGRF, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("featfeatFFGR-FFFGR", instEdge);
		instEdge.setIdentifier("featfeatFFGR-FFFGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instFeatFeatFGRF, true);
		instEdge.setSourceRelation(instVertexFFGR, true);

		// Goal to Goal

		// SemanticPairwiseRelation directGoalGoalSemanticEdge = new
		// SemanticPairwiseRelation(
		// "GoalGoalPWAsso", false, hardSemPairwiseRelList);
		/*
		 * refas.getVariabilityVertex().put("GoalGoalOTAsso", new InstConcept(
		 * "GoalGoalOTAsso", metaOverTwoRelation,
		 * semanticGoalGoalGroupRelation));
		 */
		// getConstraintInstEdges().put("GoalGoalPWAsso", new
		// InstPairwiseRelation(
		// directGoalGoalSemanticEdge));

		// Oper to Goal and Oper

		// SemanticPairwiseRelation directOperGoalSemanticEdge = new
		// SemanticPairwiseRelation(
		// "OperGoalPWAsso", false, hardSemPairwiseRelList);
		// InstConcept instOperGoal = new InstConcept("OperGoalPWAsso",
		// metaConcept, directOperGoalSemanticEdge);
		// refas.getVariabilityVertex().put("OperGoalPWAsso", instOperGoal);

		// instEdge = new InstPairwiseRelation(directOperGoalSemanticEdge);
		// instEdge.setSourceRelation(instVertexOper, true);
		// instEdge.setTargetRelation(instOperGoal, true);
		// getConstraintInstEdges().put("OperGoalPWAsso", instEdge);

		// Oper to Oper

		// SemanticPairwiseRelation directOperOperSemanticEdge = new
		// SemanticPairwiseRelation(
		// "OperOperPWAsso", false, hardSemPairwiseRelList);
		/*
		 * refas.getVariabilityVertex().put("OperOperOTAsso", new InstConcept(
		 * "OperOperOTAsso", metaOverTwoRelation,
		 * semanticOperOperGroupRelation));
		 */
		// getConstraintInstEdges().put("OperOperPWAsso",
		// new InstPairwiseRelation(directOperOperSemanticEdge));

		// SG to SG

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semSoftgoal);

		SemanticPairwiseRelation directSGSGSemEdge = new SemanticPairwiseRelation(
				"SgSgPWAsso", true, sgPairwiseRelList);
		attribute = new SemanticAttribute(
				SemanticPairwiseRelation.VAR_SOURCE_LEVEL, "Integer",
				AttributeType.OPERATION,
				SemanticPairwiseRelation.VAR_SOURCE_LEVELNAME, 0, false,
				new RangeDomain(0, 5), 0, -1, "", "", -1, "", "");
		directSGSGSemEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_SOURCE_LEVEL, attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute(
				SemanticPairwiseRelation.VAR_TARGET_LEVEL, "Integer",
				AttributeType.OPERATION,
				SemanticPairwiseRelation.VAR_TARGET_LEVELNAME, 0, false,
				new RangeDomain(0, 5), 0, -1, "", "", -1, "", "");
		directSGSGSemEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_TARGET_LEVEL, attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", 0, 0, -1,
				"", "", -1, "", "");
		directSGSGSemEdge.putSemanticAttribute("AggregationLow", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		directSGSGSemEdge.addPanelVisibleAttribute("07#" + "AggregationLow");

		directSGSGSemEdge.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");

		directSGSGSemEdge.addPropEditableAttribute("07#" + "AggregationLow");

		directSGSGSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

		attribute = new SemanticAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", 0, 0, -1,
				"", "", -1, "", "");
		directSGSGSemEdge.putSemanticAttribute("AggregationHigh", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		directSGSGSemEdge.addPanelVisibleAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");

		directSGSGSemEdge.addPropEditableAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPropVisibleAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPropEditableAttribute("08#"
				+ SemanticPairwiseRelation.VAR_SOURCE_LEVEL);
		directSGSGSemEdge.addPropVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_SOURCE_LEVEL);
		directSGSGSemEdge.addPanelVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_SOURCE_LEVEL);

		directSGSGSemEdge.addPanelSpacersAttribute(":#"
				+ SemanticPairwiseRelation.VAR_TARGET_LEVEL + "#");
		directSGSGSemEdge.addPropEditableAttribute("09#"
				+ SemanticPairwiseRelation.VAR_TARGET_LEVEL);
		directSGSGSemEdge.addPropVisibleAttribute("09#"
				+ SemanticPairwiseRelation.VAR_TARGET_LEVEL);
		directSGSGSemEdge.addPanelVisibleAttribute("09#"
				+ SemanticPairwiseRelation.VAR_TARGET_LEVEL);

		InstConcept instDirSGSGSemanticEdge = new InstConcept("SgSgPWAsso",
				metaPairwiseRelation, directSGSGSemEdge);

		ia = instDirSGSGSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("contribution", new OptionAttribute(
				"contribution", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "contribution", "", 1, -1, "", "", -1, "", ""),
				"contribution#contribution#true#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("conflict", new OptionAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", 1, -1, "", "", -1, "", ""),
				"conflict#conflict#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("alternative", new OptionAttribute(
				"alternative", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "alternative", "", 1, -1, "", "", -1, "", ""),
				"altern.#altern.#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("preferred", new OptionAttribute("preferred",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"preferred", "", 1, -1, "", "", -1, "", ""),
				"preferred#preferred#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("require", new OptionAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", 1, -1, "", "", -1, "", ""),
				"require#require#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("implication", new OptionAttribute(
				"implication", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implication", "", 1, -1, "", "", -1, "", ""),
				"implication#implication#false#true#true#1#-1#1#1"));

		ia = instDirSGSGSemanticEdge.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirSGSGSemanticEdge, "SDReqLevel", "sourceLevel");

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirSGSGSemanticEdge, "SDReqLevel", "targetLevel");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Implies"), t1, t3);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
				"low");

		t1 = new SemanticExpression("low: SGReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("GreaterOrEq"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirSGSGSemanticEdge, "SDReqLevel", "sourceLevel");

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("GreaterOrEq"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirSGSGSemanticEdge, "SDReqLevel", "targetLevel");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Implies"), t1, t3);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
				"high");

		t1 = new SemanticExpression("high: SGReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirSGSGSemanticEdge, "SDReqLevel", "sourceLevel");

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirSGSGSemanticEdge, "SDReqLevel", "targetLevel");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Implies"), t1, t3);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
				"close");

		t1 = new SemanticExpression("close: SGReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		// TODO include expressions for contribution

		ias.add(new InstAttribute("contribution", new OptionAttribute(
				"contribution", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "contribution", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexSG,
				instVertexSG, "Selected", "Selected");

		t1 = new SemanticExpression("CONFSelected", refas
				.getSemanticExpressionTypes().get("Implies"), 1, false, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("conflict", new OptionAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, null, "Selected", true,
				1);

		t1 = new SemanticExpression("ALTSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE, instVertexSG,
				"Selected", true, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("alternative", new OptionAttribute(
				"alternative", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "alternative", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexSG,
				instVertexSG, "Selected", "Selected");

		t3 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Negation"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE, instVertexSG,
				"Selected");

		t1 = new SemanticExpression("PREFSelected", refas
				.getSemanticExpressionTypes().get("And"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("preferred", new OptionAttribute("preferred",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"preferred", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Subtraction"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, null, "Selected", false,
				1);

		t1 = new SemanticExpression("REQSelected", refas
				.getSemanticExpressionTypes().get("GreaterOrEq"), 1, false, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("require", new OptionAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, null, "Selected", true,
				1);

		t1 = new SemanticExpression("IMPSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE, instVertexSG,
				"Selected", true, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("implication", new OptionAttribute(
				"implication", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implication", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		refas.getVariabilityVertex().put("SgSgPWAsso", instDirSGSGSemanticEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sgsgPW-sgpwsg", instEdge);
		instEdge.setIdentifier("sgsgPW-sgpwsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirSGSGSemanticEdge, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sgsgPW-sgsgpw", instEdge);
		instEdge.setIdentifier("sgsgPW-sgsgpw");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instDirSGSGSemanticEdge, true);

		SemanticOverTwoRelation semanticSGSGGroupRelation = new SemanticOverTwoRelation(
				semGeneralGroup, "SgSgOTAsso", hardSemOverTwoRelList);

		InstVertex instVertexSGGR = new InstConcept("SgSgOTAsso",
				metaOverTwoRelation, semanticSGSGGroupRelation);
		refas.getVariabilityVertex().put("SgSgOTAsso", instVertexSGGR);

		InstConcept instSgsgSGR = new InstConcept("sgsgOTAssoFromPWAsso",
				metaPairwiseRelation);

		refas.getVariabilityVertex().put("sgsgOTAssoFromPWAsso", instSgsgSGR);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sggrtogr", instEdge);
		instEdge.setIdentifier("sggrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGR, true);
		instEdge.setSourceRelation(instVertexSGGR, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sgsgSGR-SGsgsg", instEdge);
		instEdge.setIdentifier("sgsgSGR-SGsgsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSgsgSGR, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sgsgSGR-sgsgSG", instEdge);
		instEdge.setIdentifier("sgsgSGR-sgsgSG");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSGGR, true);
		instEdge.setSourceRelation(instSgsgSGR, true);

		InstConcept instSgsgGRSG = new InstConcept("sgsgOTAssoToPWAsso",
				metaPairwiseRelation);

		refas.getVariabilityVertex().put("sgsgOTAssoToPWAsso", instSgsgGRSG);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("SGGRtosg-GRsgsgGR", instEdge);
		instEdge.setIdentifier("SGGRtosg-GRsgsgGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSgsgGRSG, true);
		instEdge.setSourceRelation(instVertexSGGR, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("SGGRtosg-SGsgsgSG", instEdge);
		instEdge.setIdentifier("SGGRtosg-SGsgsgSG");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instSgsgGRSG, true);

		// CV to CG
		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semContextGroup);

		SemanticPairwiseRelation directCVCGSemanticEdge = new SemanticPairwiseRelation(
				"CVCGPWAsso", false, vcntxPairwiseRelList);
		InstConcept instDirCVCGSemanticEdge = new InstConcept("CVCGPWAsso",
				metaPairwiseRelation, directCVCGSemanticEdge);

		ia = instDirCVCGSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("Variable Context",
				new OptionAttribute("Variable Context", StringType.IDENTIFIER,
						AttributeType.OPTION, false, "", "", 1, -1, "", "", -1,
						"", ""),
				"Variable Context#Variable Context#false#true#true#1#-1#1#1"));

		ia = instDirCVCGSemanticEdge.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		ias.add(new InstAttribute("Variable Context", new OptionAttribute(
				"Variable Context", StringType.IDENTIFIER,
				AttributeType.OPTION, false, "Variable Context", "", 1, -1, "",
				"", -1, "", ""), semanticExpressions));

		refas.getVariabilityVertex().put("CVCGPWAsso", instDirCVCGSemanticEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("CVCGPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("CVCGPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instDirCVCGSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("CVCGPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("CVCGPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirCVCGSemanticEdge, true);
		instEdge.setSourceRelation(instVertexVAR, true);

		// Oper to Claim
		SemanticOverTwoRelation semanticOperClaimGroupRelation = new SemanticOverTwoRelation(
				semGeneralGroup, "OperCLOTAsso", hardSemOverTwoRelList);

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semClaim);

		InstVertex instVertexCLGR = new InstConcept("OperCLOTAsso",
				metaOverTwoRelation, semanticOperClaimGroupRelation);

		ia = instVertexCLGR.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("and", new OptionAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				1, -1, "", "", -1, "", ""), "and#and#true#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("or", new OptionAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				1, -1, "", "", -1, "", ""), "or#or#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("mutex", new OptionAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", 1, -1, "", "", -1, "", ""),
				"mutex#mutex#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("range", new OptionAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", 1, -1, "", "", -1, "", ""),
				"range#range#false#true#true#1#-1#1#1"));

		ia = instVertexCLGR.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
				instVertexOper, "Selected", "True");

		t1 = new SemanticExpression("ANDhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, t1, "Selected");

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("and", new OptionAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
				instVertexOper, "Selected", "False");

		t1 = new SemanticExpression("ORhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, t1, "Selected");

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("or", new OptionAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
				instVertexOper, "Selected", 0);

		t1 = new SemanticExpression("sub2", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexCLGR, instVertexOper, t1, 1);

		t1 = new SemanticExpression("MUTEXhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexHC, "Selected", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("mutex", new OptionAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t2 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexCLGR, instVertexOper, null, "Selected", "True", true);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("GreaterOrEq"), t2,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexCLGR,
				"LowRange");

		t2 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexCLGR, instVertexOper, null, "Selected", "True", true);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("LessOrEquals"), t2,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexCLGR,
				"HighRange");

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("And"), t1, t3);

		t1 = new SemanticExpression("RANGEHardRel", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexCLGR,
				"Selected", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("range", new OptionAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		refas.getVariabilityVertex().put("OperCLOTAsso", instVertexCLGR);

		SemanticPairwiseRelation directOperClaimToSemanticEdge = new SemanticPairwiseRelation(
				"OperClaimToPWAsso", true, operclaimPairwiseRelList);

		InstConcept instDirOperClaimToSemanticEdge = new InstConcept(
				"OperClaimToPWAsso", metaPairwiseRelation,
				directOperClaimToSemanticEdge);

		ia = instDirOperClaimToSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("OperToClaim", new OptionAttribute(
				"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "OperToClaim", "", 1, -1, "", "", -1, "", ""),
				"OperToClaim#OperToClaim#true#true#true#1#-1#1#1"));

		ia = instDirOperClaimToSemanticEdge
				.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexCLGR,
				instVertexCL, "Selected", "ConditionalExpression");

		t1 = new SemanticExpression("OPERCLSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE, instVertexCL,
				"Selected", true, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("OPERCLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexCLGR,
				instVertexCL, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("operToClaim", new OptionAttribute(
				"operToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "operToClaim", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		refas.getVariabilityVertex().put("OperClaimToPWAsso",
				instDirOperClaimToSemanticEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("clgrtogr", instEdge);
		instEdge.setIdentifier("clgrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGR, true);
		instEdge.setSourceRelation(instVertexCLGR, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("OperClaimToPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("OperClaimToPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirOperClaimToSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("OperClaimToPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("OperClaimToPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirOperClaimToSemanticEdge, true);
		instEdge.setSourceRelation(instVertexCLGR, true);

		SemanticPairwiseRelation directOperClaimFromSemanticEdge = new SemanticPairwiseRelation(
				"OperClaimFromPWAsso", true, nonePairwiseRelList);

		InstConcept instDirOperClaimFromSemanticEdge = new InstConcept(
				"OperClaimFromPWAsso", metaPairwiseRelation,
				directOperClaimFromSemanticEdge);
		refas.getVariabilityVertex().put("OperClaimFromPWAsso",
				instDirOperClaimFromSemanticEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges()
				.put("OperClaimFromPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("OperClaimFromPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCLGR, true);
		instEdge.setSourceRelation(instDirOperClaimFromSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges()
				.put("OperClaimFromPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("OperClaimFromPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirOperClaimFromSemanticEdge, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("OperClaimPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("OperClaimPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirOperClaimSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("OperClaimPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("OperClaimPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirOperClaimSemanticEdge, true);
		instEdge.setSourceRelation(instVertexOper, true);

		// LFeat to Claim
		SemanticOverTwoRelation semanticLFClaimGroupRelation = new SemanticOverTwoRelation(
				semGeneralGroup, "LFtoClaimOTAsso", hardSemOverTwoRelList);

		InstVertex instVertexLFCLGR = new InstConcept("LFtoClaimOTAsso",
				metaOverTwoRelation, semanticLFClaimGroupRelation);

		refas.getVariabilityVertex().put("LFtoClaimOTAsso", instVertexLFCLGR);

		SemanticPairwiseRelation directFClaimToSemanticEdge = new SemanticPairwiseRelation(
				"FClaimToPWAsso", true, operclaimPairwiseRelList);

		InstConcept instDirFClaimToSemanticEdge = new InstConcept(
				"FClaimToPWAsso", metaPairwiseRelation,
				directFClaimToSemanticEdge);

		ia = instDirFClaimToSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("OperToClaim", new OptionAttribute(
				"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "OperToClaim", "", 1, -1, "", "", -1, "", ""),
				"OperToClaim#OperToClaim#true#true#true#1#-1#1#1"));

		ia = instDirFClaimToSemanticEdge
				.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexLFCLGR, instVertexCL, "Selected",
				"ConditionalExpression");

		t1 = new SemanticExpression("OPERCLSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE, instVertexCL,
				"Selected", true, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("OPERCLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexLFCLGR, instVertexCL, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("OperToClaim", new OptionAttribute(
				"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "OperToClaim", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		refas.getVariabilityVertex().put("FClaimToPWAsso",
				instDirFClaimToSemanticEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("fctogr", instEdge);
		instEdge.setIdentifier("fctogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGR, true);
		instEdge.setSourceRelation(instVertexLFCLGR, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("FClaimToPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("FClaimToPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirFClaimToSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("FClaimToPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("FClaimToPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirFClaimToSemanticEdge, true);
		instEdge.setSourceRelation(instVertexLFCLGR, true);

		SemanticPairwiseRelation directFClaimFromSemanticEdge = new SemanticPairwiseRelation(
				"FClaimFromPWAsso", true, nonePairwiseRelList);

		InstConcept instDirFClaimFromSemanticEdge = new InstConcept(
				"FClaimFromPWAsso", metaPairwiseRelation,
				directFClaimFromSemanticEdge);
		refas.getVariabilityVertex().put("FClaimFromPWAsso",
				instDirFClaimFromSemanticEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("FClaimFromPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("FClaimFromPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexLFCLGR, true);
		instEdge.setSourceRelation(instDirFClaimFromSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("FClaimFromPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("FClaimFromPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirFClaimFromSemanticEdge, true);
		instEdge.setSourceRelation(instVertexCL, true);

		SemanticPairwiseRelation directLFClaimSemanticEdge = new SemanticPairwiseRelation(
				"LFClaimPWAsso", true, operclaimPairwiseRelList);

		InstConcept instDirLFClaimSemanticEdge = new InstConcept(
				"LFClaimPWAsso", metaPairwiseRelation,
				directLFClaimSemanticEdge);
		refas.getVariabilityVertex().put("LFClaimPWAsso",
				instDirLFClaimSemanticEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("LFClaimPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("LFClaimPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirLFClaimSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("LFClaimPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("LFClaimPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirLFClaimSemanticEdge, true);
		instEdge.setSourceRelation(instVertexF, true);

		// Claim to SG

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semSoftgoal);
		SemanticPairwiseRelation directClaimSGSemanticEdge = new SemanticPairwiseRelation(
				"ClaimSGPWAsso", true, claimSGPairwiseRelList);
		attribute = new SemanticAttribute(SemanticPairwiseRelation.VAR_LEVEL,
				"Integer", AttributeType.OPERATION,
				SemanticPairwiseRelation.VAR_LEVEL, 2, false, new RangeDomain(
						0, 5), 0, -1, "", "", -1, "", "");
		directClaimSGSemanticEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_LEVEL, attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulOperationSubAction.addInVariable(attribute);

		directClaimSGSemanticEdge.addPropEditableAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directClaimSGSemanticEdge.addPropVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directClaimSGSemanticEdge.addPanelVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		InstConcept instDirClaimSGSemanticEdge = new InstConcept(
				"ClaimSGPWAsso", metaPairwiseRelation,
				directClaimSGSemanticEdge);

		ia = instDirClaimSGSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("ClaimToSG", new OptionAttribute("ClaimToSG",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"ClaimToSG", "", 1, -1, "", "", -1, "", ""),
				"ClaimToSG#ClaimToSG#true#true#true#1#-1#1#1"));

		ia = instDirClaimSGSemanticEdge
				.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("GreaterOrEq"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "level");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexCL, "Selected", true, t1);

		t3 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG, "satisficingLevel",
				"low");

		t1 = new SemanticExpression("low: ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "level");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexCL, "Selected", true, t1);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG, "satisficingLevel",
				"high");

		t1 = new SemanticExpression("high: ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "level");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexCL, "Selected", true, t1);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG, "satisficingLevel",
				"close");

		t1 = new SemanticExpression("close: ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("claimToSG", new OptionAttribute("claimToSG",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"claimToSG", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		refas.getVariabilityVertex().put("ClaimSGPWAsso",
				instDirClaimSGSemanticEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("CLSGPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("CLSGPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instDirClaimSGSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("CLSGPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("CLSGPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirClaimSGSemanticEdge, true);
		instEdge.setSourceRelation(instVertexCL, true);

		// SD to SG

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semSoftgoal);

		SemanticPairwiseRelation directSDSGSemanticEdge = new SemanticPairwiseRelation(
				"SDSGPWAsso", true, sdPairwiseRelList);
		attribute = new SemanticAttribute(SemanticPairwiseRelation.VAR_LEVEL,
				"Integer", AttributeType.OPERATION,
				SemanticPairwiseRelation.VAR_LEVELNAME, 0, false,
				new RangeDomain(0, 5), 0, -1, "", "", -1, "", "");
		directSDSGSemanticEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_LEVEL, attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);

		directSDSGSemanticEdge.addPropEditableAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directSDSGSemanticEdge.addPropVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directSDSGSemanticEdge.addPanelVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		InstConcept instDirSDSGSemanticEdge = new InstConcept("SDSGPWAsso",
				metaPairwiseRelation, directSDSGSemanticEdge);

		ia = instDirSDSGSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("SD", new OptionAttribute("SD",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "SD", "",
				1, -1, "", "", -1, "", ""), "SD#SD#true#true#true#1#-1#1#1"));

		ia = instDirSDSGSemanticEdge.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("GreaterOrEq"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirSDSGSemanticEdge, "SDReqLevel", "level");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSD, "Selected", true, t1);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSG, "satisficingLevel",
				"low");

		t1 = new SemanticExpression("low: SDReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirSDSGSemanticEdge, "SDReqLevel", "level");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSD, "Selected", true, t1);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSG, "satisficingLevel",
				"high");

		t1 = new SemanticExpression("high: SDReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirSDSGSemanticEdge, "SDReqLevel", "level");

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSD, "Selected", true, t1);

		t3 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSG, "satisficingLevel",
				"close");

		t1 = new SemanticExpression("close: SDReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("SDSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE, instVertexCL,
				instVertexCL, "Selected", "ConditionalExpression");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("SD", new OptionAttribute("SD",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "SD", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		refas.getVariabilityVertex().put("SDSGPWAsso", instDirSDSGSemanticEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("SDSGPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("SDSGPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instDirSDSGSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("SDSGPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("SDSGPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirSDSGSemanticEdge, true);
		instEdge.setSourceRelation(instVertexSD, true);

		// Asset to Asset

		SemanticOverTwoRelation semanticAssetAssetOvertwoRel = new SemanticOverTwoRelation(
				semGeneralGroup, "AssetOperOTAsso", hardSemOverTwoRelList);

		InstVertex instVertexASSETGR = new InstConcept("AssetAssetOTAsso",
				metaOverTwoRelation, semanticAssetAssetOvertwoRel);

		refas.getVariabilityVertex().put("AssetAssetOTAsso", instVertexASSETGR);

		InstConcept instAssetassetASGR = new InstConcept(
				"AssetAssetToOTAssoPWAsso", metaPairwiseRelation);
		refas.getVariabilityVertex().put("AssetAssetToOTAssoPWAsso",
				instAssetassetASGR);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("agrtogr", instEdge);
		instEdge.setIdentifier("agrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGR, true);
		instEdge.setSourceRelation(instVertexASSETGR, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ASSETGRtoassetA-AGR", instEdge);
		instEdge.setIdentifier("ASSETGRtoassetA-AGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instAssetassetASGR, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("ASSETGRtoassetAGR-GR", instEdge);
		instEdge.setIdentifier("ASSETGRtoassetAGR-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instAssetassetASGR, true);
		instEdge.setSourceRelation(instVertexASSETGR, true);

		InstConcept instAssetassetGRAS = new InstConcept(
				"AssetAssetFromOTAssoPWAsso", metaPairwiseRelation);
		refas.getVariabilityVertex().put("AssetAssetFromOTAssoPWAsso",
				instAssetassetGRAS);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("assettoAssetGR-AGR-A", instEdge);
		instEdge.setIdentifier("assettoAssetGR-AGR-A");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instAssetassetGRAS, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("assettoAssetGR-GR-AGR", instEdge);
		instEdge.setIdentifier("assettoAssetGR-GR-AGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexASSETGR, true);
		instEdge.setSourceRelation(instAssetassetGRAS, true);

		// Asset to Oper
		// TODO use list of possible relations
		SemanticOverTwoRelation semanticAssetOperGroupRelation = new SemanticOverTwoRelation(
				semGeneralGroup, "AssetOperOTAsso", hardSemOverTwoRelList);

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semOperationalization);

		InstVertex instVertexOPERGR = new InstConcept("AssetOperOTAsso",
				metaOverTwoRelation, semanticAssetOperGroupRelation);

		refas.getVariabilityVertex().put("AssetOperOTAsso", instVertexOPERGR);

		InstConcept instAssetOperAOGR = new InstConcept(
				"AssetOperToOTAssoPWAsso", metaPairwiseRelation);
		refas.getVariabilityVertex().put("AssetOperToOTAssoPWAsso",
				instAssetOperAOGR);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("aogrtogr", instEdge);
		instEdge.setIdentifier("aogrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGR, true);
		instEdge.setSourceRelation(instAssetOperAOGR, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("assettoOperGR-AAOGR", instEdge);
		instEdge.setIdentifier("assettoOperGR-AAOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instAssetOperAOGR, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("assettoOperGR-AOGRGR", instEdge);
		instEdge.setIdentifier("assettoOperGR-AOGRGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexOPERGR, true);
		instEdge.setSourceRelation(instAssetOperAOGR, true);

		SemanticPairwiseRelation groupAssetOperSemanticEdge = new SemanticPairwiseRelation(
				"AssetOperPWAsso", false, assetoperPairwiseRelList);

		InstConcept instAssetOperGRAO = new InstConcept(
				"AssetOperFromoOTAssoPWAsso", metaPairwiseRelation,
				groupAssetOperSemanticEdge);

		ia = instAssetOperGRAO.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("implementation", new OptionAttribute(
				"implementation", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implementation", "", 1, -1, "", "", -1, "", ""),
				"implementation#implementation#true#true#true#1#-1#1#1"));

		ia = instAssetOperGRAO.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("IMPSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexOPERGR, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("IMPNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexOPERGR, "NotAvailable",
				"NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("implementation", new OptionAttribute(
				"implementation", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implementation", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		refas.getVariabilityVertex().put("AssetOperFromOTAssoPWAsso",
				instAssetOperGRAO);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("OPERGRtooper-OOGR", instEdge);
		instEdge.setIdentifier("OPERGRtooper-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instAssetOperGRAO, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("OPERGRtooper-OGRO", instEdge);
		instEdge.setIdentifier("OPERGRtooper-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instAssetOperGRAO, true);
		instEdge.setSourceRelation(instVertexOPERGR, true);

		SemanticPairwiseRelation directAssetOperSemanticEdge = new SemanticPairwiseRelation(
				"AssetOperPWAsso", false, assetoperPairwiseRelList);
		InstConcept instDirAssetOperSemanticEdge = new InstConcept(
				"AssetOperPWAsso", metaPairwiseRelation,
				directAssetOperSemanticEdge);

		ia = instDirAssetOperSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("implementation", new OptionAttribute(
				"implementation", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implementation", "", 1, -1, "", "", -1, "", ""),
				"implementation#implementation#true#true#true#1#-1#1#1"));

		ia = instDirAssetOperSemanticEdge
				.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("IMPSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCRELVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTRELVARIABLE,
				instVertexOPERGR, instVertexOper, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("IMPNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCRELVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTRELVARIABLE,
				instVertexOPERGR, instVertexOper, "NotAvailable",
				"NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("implementation", new OptionAttribute(
				"implementation", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implementation", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		refas.getVariabilityVertex().put("AssetOperPWAsso",
				instDirAssetOperSemanticEdge);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("AssetOperPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("AssetOperPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instDirAssetOperSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("AssetOperPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("AssetOperPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirAssetOperSemanticEdge, true);
		instEdge.setSourceRelation(instVertexAsset, true);

	}
}
