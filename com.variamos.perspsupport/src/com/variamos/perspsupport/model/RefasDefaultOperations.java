package com.variamos.perspsupport.model;

import java.util.ArrayList;
import java.util.List;

import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.LabelingOrder;
import com.variamos.hlcl.NumericExpression;
import com.variamos.hlcl.RangeDomain;
import com.variamos.hlcl.StringDomain;
import com.variamos.perspsupport.expressionsupport.InstanceExpression;
import com.variamos.perspsupport.expressionsupport.OpersIOAttribute;
import com.variamos.perspsupport.expressionsupport.OpersLabeling;
import com.variamos.perspsupport.expressionsupport.OpersOperation;
import com.variamos.perspsupport.expressionsupport.OpersOperationGroup;
import com.variamos.perspsupport.expressionsupport.OpersSubOperation;
import com.variamos.perspsupport.expressionsupport.OpersSubOperationExpType;
import com.variamos.perspsupport.expressionsupport.SemanticExpression;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.instancesupport.InstVertex;
import com.variamos.perspsupport.opers.OpersConcept;
import com.variamos.perspsupport.opers.OpersContextGroup;
import com.variamos.perspsupport.opers.OpersOverTwoRel;
import com.variamos.perspsupport.opers.OpersPairwiseRel;
import com.variamos.perspsupport.opers.OpersReasoningConcept;
import com.variamos.perspsupport.opers.OpersRelType;
import com.variamos.perspsupport.opers.OpersSoftConcept;
import com.variamos.perspsupport.opers.OpersVariable;
import com.variamos.perspsupport.opersint.IntMetaExpression;
import com.variamos.perspsupport.opersint.IntOpersRelType;
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
	static OpersOperation verifDeadElemOperationAction = null;
	static OpersSubOperationExpType verifDeadElemOperSubActionNormal = null;
	static OpersSubOperationExpType verifDeadElemOperSubActionRelaxable = null;
	static OpersSubOperationExpType verifDeadElemOperSubActionVerification = null;

	static OpersOperation verifParentsOperationAction = null;
	static OpersSubOperationExpType verifParentsOperSubActionNormal = null;
	static OpersSubOperationExpType verifParentsOperSubActionRelaxable = null;
	static OpersSubOperationExpType verifParentsOperSubActionVerification = null;

	static OpersOperation verifRootOperationAction = null;
	static OpersSubOperationExpType verifRootOperSubActionNormal = null;
	static OpersSubOperationExpType verifRootOperSubActionRelaxable = null;
	static OpersSubOperationExpType verifRootOperSubActionVerification = null;

	static OpersOperation verifFalseOptOperationAction = null;
	static OpersSubOperationExpType verifFalseOptOperSubActionNormal = null;
	static OpersSubOperationExpType verifFalseOptOperSubActionRelaxable = null;
	static OpersSubOperationExpType verifFalseOptOperSubActionVerification = null;

	static OpersOperation simulationOperationAction = null;

	static OpersSubOperationExpType simulationExecOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulationPreValOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulationPreUpdOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulationPosValOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulationPostUpdOptOperSubActionNormal = null;

	static OpersOperation simulScenOperationAction = null;

	static OpersSubOperationExpType simulScenExecOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulScenPreValOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulScenPreUpdOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulScenPosValOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulScenPostUpdOptOperSubActionNormal = null;

	static OpersOperation configTemporalOperationAction = null;

	static OpersSubOperationExpType configTemporalOptOperSubActionNormal = null;

	static OpersOperation configPermanentOperationAction = null;

	static OpersSubOperationExpType configPermanentOptOperSubActionNormal = null;

	static OpersOperation updateCoreOperationAction = null;

	static OpersSubOperationExpType updateCoreOptOperSubActionNormal = null;

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

		List<LabelingOrder> labord = new ArrayList<LabelingOrder>();
		labord.add(LabelingOrder.MIN);
		labord.add(LabelingOrder.MIN);
		List<NumericExpression> orderExpressionList = new ArrayList<NumericExpression>();
		orderExpressionList.add(hlclFactory.newIdentifier("REFAS1_TotalOrder"));
		orderExpressionList.add(hlclFactory.newIdentifier("REFAS1_TotalOpt"));

		OpersLabeling simulationExecOperUniqueLabeling = null;
		simulationExecOperUniqueLabeling = new OpersLabeling("unique", "L1", 1,
				false, labord, orderExpressionList);

		OpersLabeling simsceExecOperLabeling1 = null;
		simsceExecOperLabeling1 = new OpersLabeling("all", "L1", 1, false,
				null, null);
		labord = new ArrayList<LabelingOrder>();
		labord.add(LabelingOrder.MAX);

		orderExpressionList = new ArrayList<NumericExpression>();
		orderExpressionList.add(hlclFactory.newIdentifier("REFAS1_TotalSG"));

		OpersLabeling simsceExecOperLabeling2 = null;
		simsceExecOperLabeling2 = new OpersLabeling("once", "L2", 2, true,
				labord, orderExpressionList); // TODO define max for SG

		OpersConcept refasModel = new OpersConcept("REFAS");

		AbstractAttribute attribute = null;
		attribute = new ExecCurrentStateAttribute("TotalOrder", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "***TotalOrder***", 0,
				new RangeDomain(0, 2000), 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				refasModel.getIdentifier(), attribute.getName(), true));
		refasModel.putSemanticAttribute("TotalOrder", attribute);

		attribute = new ExecCurrentStateAttribute("TotalOpt", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "***TotalOpt***", 0,
				new RangeDomain(0, 2000), 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				refasModel.getIdentifier(), attribute.getName(), true));
		refasModel.putSemanticAttribute("TotalOpt", attribute);

		attribute = new ExecCurrentStateAttribute("TotalSG", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "***TotalSG***", 0,
				new RangeDomain(0, 2000), 2, -1, "", "", -1, "", "");
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(refasModel
				.getIdentifier(), attribute.getName(), true));
		refasModel.putSemanticAttribute("TotalSG", attribute);

		InstVertex instRefasModel = new InstConcept("REFAS", metaModel,
				refasModel);
		refas.getVariabilityVertex().put("REFAS", instRefasModel);

		InstAttribute ia = null;
		List<InstAttribute> ias = null;

		OpersOperationGroup operationMenu = new OpersOperationGroup(1,
				"SimulationGroup", 2);

		InstVertex instOperationGroup = new InstConcept("SimulationGroup",
				metaOperationMenu, operationMenu);
		refas.getVariabilityVertex().put("SimulationGroup", instOperationGroup);

		instOperationGroup.getInstAttribute("visible").setValue(true);
		instOperationGroup.getInstAttribute("menuType").setValue("4");
		instOperationGroup.getInstAttribute("name").setValue(
				"Basic Simulation (New)");
		instOperationGroup.getInstAttribute("shortcut").setValue("S");

		simulationOperationAction = new OpersOperation(1, "SimulationOper");

		InstVertex instOperationAction = new InstConcept("SimulationOper",
				metaOperationAction, simulationOperationAction);
		refas.getVariabilityVertex().put("SimulationOper", instOperationAction);

		instOperationAction.getInstAttribute("name").setValue(
				"Simulation Operation (For V.19)");
		instOperationAction.getInstAttribute("shortcut").setValue("S");
		instOperationAction.getInstAttribute("iteration").setValue(true);

		InstPairwiseRelation instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("sim-menu", instEdgeOper);
		instEdgeOper.setIdentifier("sim-menu");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationAction, true);
		instEdgeOper.setSourceRelation(instOperationGroup, true);

		OpersSubOperation operationSubAction = new OpersSubOperation(1,
				"Sim-Pre-Validation", false,
				OperationSubActionType.VERIFICATION);

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

		simulationPreValOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulationPreValOptOperSubActionNormal);

		OpersLabeling operationLabeling = new OpersLabeling("unique", "L1", 1,
				false, null, null);

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

		operationSubAction = new OpersSubOperation(2, "Sim-Pre-Update", false,
				OperationSubActionType.SINGLEUPDATE);
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

		simulationPreUpdOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulationPreUpdOptOperSubActionNormal);

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		OpersSubOperation simulOperationSubAction = new OpersSubOperation(3,
				"Sim-Execution", true, OperationSubActionType.ITERATIVEUPDATE);

		List<IntMetaExpression> semanticExpressions = new ArrayList<IntMetaExpression>();

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

		simulationExecOptOperSubActionNormal = new OpersSubOperationExpType(
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

		operationSubAction = new OpersSubOperation(4, "Sim-Post-Validation",
				false, OperationSubActionType.VERIFICATION);

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

		simulationPosValOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulationPosValOptOperSubActionNormal);

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		operationSubAction = new OpersSubOperation(5, "Sim-Post-Update", false,
				OperationSubActionType.SINGLEUPDATE);

		// simulationOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("Sim-Post-Update",
				metaOperationSubAction, operationSubAction);
		refas.getVariabilityVertex().put("Sim-Post-Update",
				instOperationSubAction);

		simulationPostUpdOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulationPostUpdOptOperSubActionNormal);

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		operationMenu = new OpersOperationGroup(1, "SimulationSCeOper", 2);

		instOperationGroup = new InstConcept("SimulationSceGroup",
				metaOperationMenu, operationMenu);
		refas.getVariabilityVertex().put("SimulationSceGroup",
				instOperationGroup);

		instOperationGroup.getInstAttribute("visible").setValue(true);
		instOperationGroup.getInstAttribute("menuType").setValue("4");
		instOperationGroup.getInstAttribute("name").setValue(
				"Simulation Scenarios");
		instOperationGroup.getInstAttribute("shortcut").setValue("C");

		simulScenOperationAction = new OpersOperation(1, "SimulationScenarios");

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
				"Simulation Scenarios  (For V.19)");
		instOperationAction.getInstAttribute("shortcut").setValue("S");
		instOperationAction.getInstAttribute("iteration").setValue(true);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-menu", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-menu");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationAction, true);
		instEdgeOper.setSourceRelation(instOperationGroup, true);

		operationSubAction = new OpersSubOperation(1, "SimSce-Pre-Validation",
				false, OperationSubActionType.VERIFICATION);

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

		simulScenPreValOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulScenPreValOptOperSubActionNormal);

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		operationSubAction = new OpersSubOperation(2, "SimSce-Pre-Update",
				false, OperationSubActionType.SINGLEUPDATE);

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

		simulScenPreValOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulScenPreValOptOperSubActionNormal);

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		OpersSubOperation simSceOperationSubAction = new OpersSubOperation(3,
				"SimSce-Execution", true,
				OperationSubActionType.ITERATIVEUPDATE);
		// simulScenOperationAction.addExpressionSubAction(operationSubAction);

		instOperationSubAction = new InstConcept("SimSce-Execution",
				metaOperationSubAction, simSceOperationSubAction);
		refas.getVariabilityVertex().put("SimSce-Execution",
				instOperationSubAction);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-exec", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-exec");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instOperationSubAction, true);
		instEdgeOper.setSourceRelation(instOperationAction, true);

		simulScenExecOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		simSceOperationSubAction
				.addOperationSubActionExpType(simulScenExecOptOperSubActionNormal);

		// simulOperationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("simsce-exec-lab1", metaLabeling,
				simsceExecOperLabeling1);

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

		// simulOperationSubAction.addOperationLabeling(operationLabeling);

		instLabeling = new InstConcept("simsce-exec-lab2", metaLabeling,
				simsceExecOperLabeling2);

		instLabeling.getInstAttribute("labelId").setValue("L2");
		instLabeling.getInstAttribute("position").setValue(2);
		instLabeling.getInstAttribute("once").setValue(true);

		refas.getVariabilityVertex().put("simsce-exec-lab2", instLabeling);

		semanticExpressions = new ArrayList<IntMetaExpression>();

		simsceExecOperLabeling2.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
				instRefasModel, "Selected", 0);

		semanticExpressions.add(t1);

		instEdgeOper = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("simsce-exec-lab2", instEdgeOper);
		instEdgeOper.setIdentifier("simsce-exec-lab2");
		instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdgeOper.setTargetRelation(instLabeling, true);
		instEdgeOper.setSourceRelation(instOperationSubAction, true);

		operationSubAction = new OpersSubOperation(4, "SimSce-Post-Validation",
				false, OperationSubActionType.VERIFICATION);

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

		simulScenPosValOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulScenPosValOptOperSubActionNormal);

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		operationSubAction = new OpersSubOperation(5, "SimSce-Post-Update",
				false, OperationSubActionType.SINGLEUPDATE);
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

		simulScenPostUpdOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(simulScenPostUpdOptOperSubActionNormal);

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		operationMenu = new OpersOperationGroup(1, "Verification", 2);

		instOperationGroup = new InstConcept("Verification", metaOperationMenu,
				operationMenu);
		refas.getVariabilityVertex().put("Verification", instOperationGroup);

		instOperationGroup.getInstAttribute("visible").setValue(false);
		instOperationGroup.getInstAttribute("menuType").setValue("2");
		instOperationGroup.getInstAttribute("name").setValue("Verification");
		instOperationGroup.getInstAttribute("shortcut").setValue("V");

		updateCoreOperationAction = new OpersOperation(1, "UpdateCoreOper");

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

		operationSubAction = new OpersSubOperation(1, "UpdateCoreSubOper",
				false, OperationSubActionType.SINGLEUPDATE);
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

		updateCoreOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(updateCoreOptOperSubActionNormal);

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		verifDeadElemOperationAction = new OpersOperation(1,
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

		operationSubAction = new OpersSubOperation(1,
				"VerifyDeadElementsSubOper", false,
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

		verifDeadElemOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(verifDeadElemOperSubActionNormal);

		verifDeadElemOperSubActionRelaxable = new OpersSubOperationExpType(
				OperationSubActionExecType.RELAXABLE);
		operationSubAction
				.addOperationSubActionExpType(verifDeadElemOperSubActionRelaxable);

		verifDeadElemOperSubActionVerification = new OpersSubOperationExpType(
				OperationSubActionExecType.VERIFICATION);
		operationSubAction
				.addOperationSubActionExpType(verifDeadElemOperSubActionVerification);

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		verifParentsOperationAction = new OpersOperation(2, "VerifyParentsOper");

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

		operationSubAction = new OpersSubOperation(1, "VerifyParentsSubOper",
				false, OperationSubActionType.VERIFICATION);
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

		verifParentsOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(verifParentsOperSubActionNormal);

		verifParentsOperSubActionRelaxable = new OpersSubOperationExpType(
				OperationSubActionExecType.RELAXABLE);
		operationSubAction
				.addOperationSubActionExpType(verifParentsOperSubActionRelaxable);

		verifParentsOperSubActionVerification = new OpersSubOperationExpType(
				OperationSubActionExecType.VERIFICATION);
		operationSubAction
				.addOperationSubActionExpType(verifParentsOperSubActionVerification);

		verifRootOperationAction = new OpersOperation(3, "VerifyRootsOper");

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		operationSubAction = new OpersSubOperation(1, "VerifyRootsSubOper",
				false, OperationSubActionType.VERIFICATION);
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

		verifRootOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(verifRootOperSubActionNormal);

		verifRootOperSubActionRelaxable = new OpersSubOperationExpType(
				OperationSubActionExecType.RELAXABLE);
		operationSubAction
				.addOperationSubActionExpType(verifRootOperSubActionRelaxable);

		verifRootOperSubActionVerification = new OpersSubOperationExpType(
				OperationSubActionExecType.VERIFICATION);
		operationSubAction
				.addOperationSubActionExpType(verifRootOperSubActionVerification);

		verifFalseOptOperationAction = new OpersOperation(4,
				"VerifyFalseOperations");

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		operationSubAction = new OpersSubOperation(1,
				"VerifyFalseSubOperations", false,
				OperationSubActionType.VERIFICATION);
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

		verifFalseOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(verifFalseOptOperSubActionNormal);

		verifFalseOptOperSubActionRelaxable = new OpersSubOperationExpType(
				OperationSubActionExecType.RELAXABLE);
		operationSubAction
				.addOperationSubActionExpType(verifFalseOptOperSubActionRelaxable);

		verifFalseOptOperSubActionVerification = new OpersSubOperationExpType(
				OperationSubActionExecType.VERIFICATION);
		operationSubAction
				.addOperationSubActionExpType(verifFalseOptOperSubActionVerification);

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		configTemporalOperationAction = new OpersOperation(1,
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

		operationSubAction = new OpersSubOperation(1,
				"ConfigureTemporalSubOper", false,
				OperationSubActionType.SINGLEUPDATE);
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

		configTemporalOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(configTemporalOptOperSubActionNormal);

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

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

		configPermanentOperationAction = new OpersOperation(1,
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

		operationSubAction = new OpersSubOperation(1,
				"ConfigurePermanentSubOper", false,
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

		configPermanentOptOperSubActionNormal = new OpersSubOperationExpType(
				OperationSubActionExecType.NORMAL);
		operationSubAction
				.addOperationSubActionExpType(configPermanentOptOperSubActionNormal);

		operationLabeling = new OpersLabeling("unique", "L1", 1, false, null,
				null);

		semanticExpressions = new ArrayList<IntMetaExpression>();

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		OpersConcept semGeneralElement = new OpersConcept("GeneralElement"); // From
																				// refas
																				// name
																				// depends
																				// all
																				// the
																				// operations,
																				// do
																				// not
																				// change
																				// it

		/*
		 * semGeneralElement.putSemanticAttribute("Selected", new
		 * ExecCurrentStateAttribute("Selected", "Boolean",
		 * AttributeType.EXECCURRENTSTATE, false, "***Selected***", false, 2,
		 * -1, "", "", -1, "", "")); semGeneralElement
		 * .putSemanticAttribute("NotAvailable", new
		 * ExecCurrentStateAttribute("NotAvailable", "Boolean",
		 * AttributeType.EXECCURRENTSTATE, false, "***Not Avaliable***", false,
		 * 2, -1, "", "", -1, "", ""));
		 */
		InstVertex instVertexGE = new InstConcept("GeneralElement",
				metaConcept, semGeneralElement);

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

		semanticExpressions = new ArrayList<IntMetaExpression>();

		semGeneralElement.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("Req Implies Selected", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexGE,
				instVertexGE, "Required", "Selected");

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("NextPrefSel_=_0", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"NextPrefSelected", true, 0);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("NextNotPrefSel_=_0", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"NextNotPrefSelected", true, 0);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("UserReq Implies Req", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexGE,
				instVertexGE, "Required", "Selected");

		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexGE, "HasParent", true, 1);

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t2 = new SemanticExpression("Opt =0", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Opt", true, 0);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t2);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t2);

		semanticExpressions.add(t2);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, instVertexGE, "ConfigSelected",
				"NextReqSelected");

		t1 = new SemanticExpression("5", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "Core", true, t1);

		t1 = new SemanticExpression("Core+ConfigSel+NextReqSel <=1", refas
				.getSemanticExpressionTypes().get("LessOrEquals"), 1, false, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("5", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, instVertexGE, "Selected",
				"NotAvailable");

		t1 = new SemanticExpression("Selected+NotAvail <=1", refas
				.getSemanticExpressionTypes().get("Equals"), 0, false, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		refas.getVariabilityVertex().put("GeneralElement", instVertexGE);

		// Design attributes: Do not change identifiers

		attribute = new ExecCurrentStateAttribute("True", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", true,
				2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		semGeneralElement.putSemanticAttribute("True", attribute);
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));

		attribute = new ExecCurrentStateAttribute("False", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***NotSelected***",
				false, 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		semGeneralElement.putSemanticAttribute("False", attribute);
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));

		attribute = new ExecCurrentStateAttribute("Selected", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", false,
				2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		semGeneralElement.putSemanticAttribute("Selected", attribute);
		simulOperationSubAction.addOutVariable(attribute);
		simulOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addOutVariable(attribute);
		simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		attribute = new ExecCurrentStateAttribute("NotAvailable", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Not Avaliable***",
				false, 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		semGeneralElement.putSemanticAttribute("NotAvailable", attribute);
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
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
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));

		attribute = new SemanticAttribute("Scope", "Boolean",
				AttributeType.OPERATION, true, "Global Scope", true, 0, -1, "",
				"", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		semGeneralElement.putSemanticAttribute("Scope", attribute);
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
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
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		semGeneralElement.putSemanticAttribute("Core", attribute);
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));

		attribute = new SemanticAttribute("Dead", "Boolean",
				AttributeType.OPERATION, false, "Is a Dead Concept", false, 2,
				-1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		semGeneralElement.putSemanticAttribute("Dead", attribute);
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));

		attribute = new SemanticAttribute("IgnoreForSimulation", "Boolean",
				AttributeType.OPERATION, true, "Ignore for Simulation", false,
				0, -1, "", "", -1, "", "");
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
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addOutVariable(attribute);
		// TODO define domain or Enum Level

		attribute = new GlobalConfigAttribute("ConfigSelected", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Configuration Selected",
				false, 2, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("ConfigSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));

		attribute = new GlobalConfigAttribute("ConfigNotSelected", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Configuration Not Selected",
				false, 2, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("ConfigNotSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));

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
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulationOperationAction.addInVariable(attribute);

		attribute = new ExecCurrentStateAttribute("SimRequiredLevel",
				"Integer", AttributeType.EXECCURRENTSTATE, false,
				"Required Level", 0, new RangeDomain(0, 5), 0, -1, "", "", -1,
				"", "");
		// semGeneralElement.putSemanticAttribute("SimRequiredLevel",
		// attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ExecCurrentStateAttribute("HasParent", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "Has Parent", true, 0,
				-1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("HasParent", attribute);
		// TODO add to verification
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addOutVariable(attribute);

		attribute = new ExecCurrentStateAttribute("Opt", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "FilterVariable", 0,
				new RangeDomain(0, 20), 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("Opt", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));

		attribute = new ExecCurrentStateAttribute("Order", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "SortVariable", 0,
				new RangeDomain(0, 40), 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("Order", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		// simulOperationSubAction.addInVariable(attribute);

		attribute = new ExecCurrentStateAttribute("NextNotSelected", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Not selected(inactive)", false, 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("NextNotSelected", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addOutVariable(attribute);

		attribute = new ExecCurrentStateAttribute("NextPrefSelected",
				"Boolean", AttributeType.EXECCURRENTSTATE, false,
				"Selected by configuration", false, 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("NextPrefSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));

		attribute = new ExecCurrentStateAttribute("NextNotPrefSelected",
				"Boolean", AttributeType.EXECCURRENTSTATE, false,
				"Not Selected by configuration", false, 0, -1, "", "", -1, "",
				"");
		semGeneralElement
				.putSemanticAttribute("NextNotPrefSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));

		attribute = new ExecCurrentStateAttribute("NextReqSelected", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected by simulation", false, 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("NextReqSelected", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simulOperationSubAction.addOutVariable(attribute);
		simulOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addOutVariable(attribute);
		simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semGeneralElement.getIdentifier(), attribute.getName(), true));

		semGeneralElement.addPropVisibleAttribute("01#" + "Selected");
		semGeneralElement.addPropVisibleAttribute("03#" + "NextPrefSelected");
		semGeneralElement.addPropVisibleAttribute("05#" + "NextReqSelected");

		semGeneralElement.addPropVisibleAttribute("02#" + "NotAvailable");
		semGeneralElement.addPropVisibleAttribute("04#" + "NextNotSelected");
		semGeneralElement
				.addPropVisibleAttribute("06#" + "NextNotPrefSelected");

		OpersConcept semHardConcept = new OpersConcept(semGeneralElement,
				"semHardConcept");

		attribute = new SemanticAttribute("satisfactionType", "Enumeration",
				AttributeType.OPERATION, false, "satisfactionType",
				"com.variamos.semantic.types.SatisfactionType", "achieve", "",
				0, -1, "", "", -1, "", "");
		semHardConcept.putSemanticAttribute("satisfactionType", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		semHardConcept.addPropEditableAttribute("01#" + "satisfactionType");
		semHardConcept.addPropVisibleAttribute("01#" + "satisfactionType");

		InstVertex instVertexHC = new InstConcept("HardConcept", metaConcept,
				semHardConcept);
		refas.getVariabilityVertex().put("HardConcept", instVertexHC);

		semanticExpressions = new ArrayList<IntMetaExpression>();

		semHardConcept.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", true, 4);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Sum"), t1, 0);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("Order...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Order", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("hctoge", instEdge);
		instEdge.setIdentifier("hctoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexHC, true);

		// Feature concepts

		OpersConcept semFeature = new OpersConcept(semGeneralElement, "Feature");
		InstVertex instVertexF = new InstConcept("Feature", metaConcept,
				semFeature);

		attribute = new SemanticAttribute("IsRootFeature", "Boolean",
				AttributeType.OPERATION, true, "Is a Root Feature Concept",
				false, 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));
		semFeature.putSemanticAttribute("IsRootFeature", attribute);
		simulOperationSubAction.addOutVariable(attribute);
		simulOperationSubAction.addOutAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addOutVariable(attribute);
		simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));

		semanticExpressions = new ArrayList<IntMetaExpression>();

		semFeature.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", true, 4);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Sum"), t1, 0);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("Order...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Order", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexGE, "IsRootFeature", true, 1);

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
				.get("Equals"), instVertexGE, "IsRootFeature", true, 1);

		t3 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexGE, "Selected", true, 1);

		t1 = new SemanticExpression("Root Implies Req", refas
				.getSemanticExpressionTypes().get("Implies"), t1, t3);

		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexGE, "IsRootFeature", true, 1);

		t3 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexGE, "Selected", true, 1);

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

		OpersConcept semAssumption = new OpersConcept(semHardConcept,
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

		OpersConcept semGoal = new OpersConcept(semHardConcept, "Goal");
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

		OpersConcept semOperationalization = new OpersConcept(semHardConcept,
				"Operationalization");

		attribute = new SyntaxAttribute("attributeValue", "Set",
				AttributeType.SYNTAX, false, "values",
				InstAttribute.class.getCanonicalName(),
				new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "", "");
		semOperationalization.putSemanticAttribute("attributeValue", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		semOperationalization.addPropVisibleAttribute("06#" + "attributeValue");
		semOperationalization
				.addPropEditableAttribute("06#" + "attributeValue");

		simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(
				semOperationalization.getIdentifier(), "Selected", true));

		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semOperationalization.getIdentifier(), "Selected", false));

		InstVertex instVertexOper = new InstConcept("Operationalization",
				metaConcept, semOperationalization);
		refas.getVariabilityVertex().put("Operationalization", instVertexOper);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("opertoge", instEdge);
		instEdge.setIdentifier("opertoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexOper, true);

		OpersSoftConcept semSoftgoal = new OpersSoftConcept(semGeneralElement,
				"Softgoal");

		simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), "Selected", true));

		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), "Selected", false));

		StringDomain d = new StringDomain();
		d.add("low");
		d.add("high");
		d.add("close");
		attribute = new SemanticAttribute("satisficingLevel", "String",
				AttributeType.OPERATION, "Satisficing Level", "low", false, d,
				0, 10, "", "", -1, "", "");
		semSoftgoal.putSemanticAttribute("satisficingLevel", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		semSoftgoal.addPropEditableAttribute("11#" + "satisficingLevel");
		semSoftgoal.addPropVisibleAttribute("11#" + "satisficingLevel");

		attribute = new SemanticAttribute(OpersSoftConcept.VAR_SATISFICINGTYPE,
				"Enumeration", AttributeType.OPERATION, false,
				OpersSoftConcept.VAR_SATISFICINGTYPENAME,
				OpersSoftConcept.VAR_SATISFICINGTYPECLASS,
				"Achieve as close as possible", "", 0, 10, "", "", -1, "", "");
		semSoftgoal.putSemanticAttribute(OpersSoftConcept.VAR_SATISFICINGTYPE,
				attribute);

		attribute = new SemanticAttribute(
				OpersSoftConcept.VAR_CONFREQLEVELTYPE, "Integer",
				AttributeType.OPERATION,
				OpersSoftConcept.VAR_CONFREQLEVELTYPENAME, 0, false,
				new RangeDomain(0, 5), 0, 5, "Required" + "#==#" + "true" + "#"
						+ "0", "", -1, "", "");
		semSoftgoal.putSemanticAttribute(OpersSoftConcept.VAR_CONFREQLEVELTYPE,
				attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));

		semSoftgoal.addPropEditableAttribute("10#"
				+ OpersSoftConcept.VAR_SATISFICINGTYPE);
		semSoftgoal.addPropEditableAttribute("05#"
				+ OpersSoftConcept.VAR_CONFREQLEVELTYPE + "#" + "Required"
				+ "#==#" + "true" + "#" + "0");

		semSoftgoal.addPropVisibleAttribute("10#"
				+ OpersSoftConcept.VAR_SATISFICINGTYPE);
		semSoftgoal.addPropVisibleAttribute("05#"
				+ OpersSoftConcept.VAR_CONFREQLEVELTYPE);

		semanticExpressions = new ArrayList<IntMetaExpression>();

		semSoftgoal.setSemanticExpressions(semanticExpressions);

		InstVertex instVertexSG = new InstConcept("Softgoal", metaConcept,
				semSoftgoal);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Subtraction"), instVertexGE, "Selected", false, 1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Product"), 8, true, t1);

		t3 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", true, 4);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Product"), t1, t3);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("Order...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Order", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		refas.getVariabilityVertex().put("Softgoal", instVertexSG);

		attribute = new ExecCurrentStateAttribute("SDReqLevel", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "Required Level by SD",
				0, new RangeDomain(0, 4), 2, -1, "", "", -1, "", "");
		semSoftgoal.putSemanticAttribute("SDReqLevel", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling1.addAttribute(attribute);
		simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simulOperationSubAction.addOutVariable(attribute);
		simulOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addOutVariable(attribute);
		simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));

		attribute = new ExecCurrentStateAttribute("ClaimExpLevel", "Integer",
				AttributeType.EXECCURRENTSTATE, false,
				"Expected Level by Claim", 0, new RangeDomain(0, 4), 2, -1, "",
				"", -1, "", "");
		semSoftgoal.putSemanticAttribute("ClaimExpLevel", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling1.addAttribute(attribute);
		simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simulOperationSubAction.addOutVariable(attribute);
		simulOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addOutVariable(attribute);
		simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));

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

		OpersVariable semVariable = new OpersVariable("Variable");

		simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), "NotAvailable", true));

		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), "NotAvailable", false));

		semanticExpressions = new ArrayList<IntMetaExpression>();

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

		// semanticExpressions.add(t1);
		// simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		attribute = new GlobalConfigAttribute("DashBoardVisible", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Visible on Dashboard",
				true, 0, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("DashBoardVisible", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new GlobalConfigAttribute("ExportOnConfig", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Export on Configuration",
				true, 0, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("ExportOnConfig", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute("Scope", "Boolean",
				AttributeType.OPERATION, true, "Global Scope", true, 0, -1, "",
				"", -1, "", "");
		semVariable.putSemanticAttribute("Scope", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		// TODO use scope

		attribute = new SemanticAttribute("ConcernLevel", "Class",
				AttributeType.OPERATION, false, "Concern Level",
				InstConcept.class.getCanonicalName(), "CG", null, "", 0, -1,
				"", "", -1, "", "");
		semVariable.putSemanticAttribute("ConcernLevel", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);
		// TODO: use concern level

		attribute = new SemanticAttribute(OpersVariable.VAR_NAME, "String",
				AttributeType.OPERATION, false, OpersVariable.VAR_NAMENAME, "",
				0, 1, "", "", -1, "", "");
		semVariable.putSemanticAttribute(OpersVariable.VAR_NAME, attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute(OpersVariable.VAR_VARIABLETYPE,
				"Enumeration", AttributeType.OPERATION, true,
				OpersVariable.VAR_VARIABLETYPENAME,
				OpersVariable.VAR_VARIABLETYPECLASS, "String", "", 0, 2, "",
				"", -1, "", OpersVariable.VAR_VARIABLETYPE + "#!=#"
						+ "Enumeration");
		semVariable.putSemanticAttribute(OpersVariable.VAR_VARIABLETYPE,
				attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute(OpersVariable.VAR_VARIABLEDOMAIN,
				"String", AttributeType.OPERATION, false,
				OpersVariable.VAR_VARIABLEDOMAINNAME, "0,1", 0, 3,
				OpersVariable.VAR_VARIABLETYPE + "#==#" + "Integer",
				OpersVariable.VAR_VARIABLETYPE + "#==#" + "Integer", -1, "", "");
		semVariable.putSemanticAttribute(OpersVariable.VAR_VARIABLEDOMAIN,
				attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute(OpersVariable.VAR_ENUMERATIONTYPE,
				"Class", AttributeType.OPERATION, false,
				OpersVariable.VAR_ENUMERATIONTYPENAME,
				OpersVariable.VAR_ENUMERATIONTYPECLASS, "ME", "String", "", 0,
				4, OpersVariable.VAR_VARIABLETYPE + "#==#" + "Enumeration",
				OpersVariable.VAR_VARIABLETYPE + "#==#" + "Enumeration", -1,
				"", "");
		semVariable.putSemanticAttribute(OpersVariable.VAR_ENUMERATIONTYPE,
				attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		// TODO define domain for enumtype
		attribute = new ExecCurrentStateAttribute(OpersVariable.VAR_VALUE,
				"Integer", AttributeType.EXECCURRENTSTATE, false,
				OpersVariable.VAR_VALUENAME, 0, 1, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute(OpersVariable.VAR_VALUE, attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling1.addAttribute(attribute);
		simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));

		attribute = new SemanticAttribute(OpersVariable.VAR_CONTEXT, "Boolean",
				AttributeType.OPERATION, false, OpersVariable.VAR_CONTEXTNAME,
				false, 0, 5, "", "", -1, "", "");
		semVariable.putSemanticAttribute(OpersVariable.VAR_CONTEXT, attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute(OpersVariable.VAR_EXTVISIBLE,
				"Boolean", AttributeType.OPERATION, false,
				OpersVariable.VAR_EXTVISIBLENAME, false, 0, 8, "", "", -1, "",
				"");
		semVariable.putSemanticAttribute(OpersVariable.VAR_EXTVISIBLE,
				attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute(OpersVariable.VAR_EXTCONTROL,
				"Boolean", AttributeType.OPERATION, false,
				OpersVariable.VAR_EXTCONTROLNAME, false, 0, 9, "", "", -1, "",
				"");
		semVariable.putSemanticAttribute(OpersVariable.VAR_EXTCONTROL,
				attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new GlobalConfigAttribute(
				OpersVariable.VAR_VARIABLECONFIGVALUE, "Integer",
				AttributeType.GLOBALCONFIG, false,
				OpersVariable.VAR_VARIABLECONFIGVALUENAME, 0, 0, -1, "", "",
				-1, "", "");
		semVariable.putSemanticAttribute(OpersVariable.VAR_VARIABLECONFIGVALUE,
				attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new GlobalConfigAttribute(
				OpersVariable.VAR_VARIABLECONFIGDOMAIN, "String",
				AttributeType.GLOBALCONFIG, false,
				OpersVariable.VAR_VARIABLECONFIGDOMAINNAME, "", 0, 1,
				OpersVariable.VAR_VARIABLETYPE + "#==#" + "Integer" + "||"
						+ OpersVariable.VAR_VARIABLETYPE + "#==#"
						+ "Enumeration" + "||" + OpersVariable.VAR_VARIABLETYPE
						+ "#==#" + "Boolean", "", -1, "", "");
		semVariable.putSemanticAttribute(
				OpersVariable.VAR_VARIABLECONFIGDOMAIN, attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// simsceExecOperLabeling2.addAttribute(attribute);
		// simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semVariable
		// .getIdentifier(), attribute.getName(), true));
		// simsceExecOperLabeling1.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);

		semVariable.addPropEditableAttribute("01#" + OpersVariable.VAR_NAME);
		semVariable.addPropEditableAttribute("02#"
				+ OpersVariable.VAR_VARIABLETYPE);
		semVariable.addPropEditableAttribute("03#"
				+ OpersVariable.VAR_VARIABLEDOMAIN);
		semVariable.addPropEditableAttribute("04#"
				+ OpersVariable.VAR_ENUMERATIONTYPE);
		semVariable.addPropEditableAttribute("05#" + OpersVariable.VAR_CONTEXT);

		semVariable.addPropEditableAttribute("08#"
				+ OpersVariable.VAR_EXTVISIBLE);
		semVariable.addPropEditableAttribute("09#"
				+ OpersVariable.VAR_EXTCONTROL);

		semVariable.addPropEditableAttribute("01#"
				+ OpersVariable.VAR_VARIABLECONFIGDOMAIN);

		semVariable.addPropVisibleAttribute("01#" + OpersVariable.VAR_NAME);
		semVariable.addPropVisibleAttribute("02#"
				+ OpersVariable.VAR_VARIABLETYPE);
		semVariable.addPropVisibleAttribute("03#"
				+ OpersVariable.VAR_VARIABLEDOMAIN + "#"
				+ OpersVariable.VAR_VARIABLETYPE + "#==#" + "Integer");
		semVariable.addPropVisibleAttribute("04#"
				+ OpersVariable.VAR_ENUMERATIONTYPE + "#"
				+ OpersVariable.VAR_VARIABLETYPE + "#==#" + "Enumeration");
		semVariable.addPropVisibleAttribute("05#" + OpersVariable.VAR_CONTEXT);

		semVariable.addPropVisibleAttribute("06#" + OpersVariable.VAR_VALUE);
		semVariable.addPropVisibleAttribute("07#" + OpersVariable.VAR_VALUE);
		semVariable.addPropVisibleAttribute("08#"
				+ OpersVariable.VAR_EXTVISIBLE);
		semVariable.addPropVisibleAttribute("09#"
				+ OpersVariable.VAR_EXTCONTROL);

		semVariable.addPropVisibleAttribute("01#"
				+ OpersVariable.VAR_VARIABLECONFIGDOMAIN + "#"
				+ OpersVariable.VAR_VARIABLETYPE + "#==#" + "Enumeration");
		semVariable.addPropVisibleAttribute("01#"
				+ OpersVariable.VAR_VARIABLECONFIGDOMAIN + "#"
				+ OpersVariable.VAR_VARIABLETYPE + "#==#" + "Integer");
		semVariable.addPropVisibleAttribute("01#"
				+ OpersVariable.VAR_VARIABLECONFIGDOMAIN + "#"
				+ OpersVariable.VAR_VARIABLETYPE + "#==#" + "Boolean");

		semVariable.addPanelVisibleAttribute("05#"
				+ OpersVariable.VAR_VARIABLETYPE + "#"
				+ OpersVariable.VAR_VARIABLETYPE + "#!=#" + "Enumeration");
		semVariable.addPanelVisibleAttribute("06#"
				+ OpersVariable.VAR_ENUMERATIONTYPE + "#"
				+ OpersVariable.VAR_VARIABLETYPE + "#==#" + "Enumeration");
		semVariable.addPanelVisibleAttribute("07#"
				+ OpersVariable.VAR_VARIABLEDOMAIN + "#"
				+ OpersVariable.VAR_VARIABLETYPE + "#==#" + "Integer");
		semVariable.addPanelSpacersAttribute("{#"
				+ OpersVariable.VAR_VARIABLETYPE + "#} ");

		semVariable.addPanelSpacersAttribute("{#"
				+ OpersVariable.VAR_VARIABLEDOMAIN + "#} ");

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

		OpersContextGroup semContextGroup = new OpersContextGroup(
				"ConcernLevel");
		InstVertex instVertexCG = new InstConcept("ConcernLevel", metaConcept,
				semContextGroup);
		refas.getVariabilityVertex().put("ConcernLevel", instVertexCG);

		OpersConcept semAsset = new OpersConcept(semGeneralElement, "Asset");
		InstVertex instVertexAsset = new InstConcept("Asset", metaConcept,
				semAsset);
		refas.getVariabilityVertex().put("Asset", instVertexAsset);

		semanticExpressions = new ArrayList<IntMetaExpression>();

		semAsset.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", true, 4);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Sum"), t1, 0);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("Order...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Order", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("astoge", instEdge);
		instEdge.setIdentifier("astoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		List<IntOpersRelType> claimSemOverTwoRelList = new ArrayList<IntOpersRelType>();
		claimSemOverTwoRelList.add(new OpersRelType("and", "And", "And", false,
				false, false, 2, -1, 1, 1));
		claimSemOverTwoRelList.add(new OpersRelType("or", "Or", "Or", false,
				true, true, 2, -1, 1, 1));
		claimSemOverTwoRelList.add(new OpersRelType("mutex", "Mutex", "Mutex.",
				false, true, true, 2, -1, 1, 1));

		OpersReasoningConcept semClaim = new OpersReasoningConcept(
				semGeneralElement, "Claim", true, claimSemOverTwoRelList);
		InstVertex instVertexCL = new InstConcept("Claim", metaOverTwoRelation,
				semClaim);

		semanticExpressions = new ArrayList<IntMetaExpression>();

		semClaim.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", true, 4);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Sum"), t1, 0);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("Order...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Order", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

		List<IntOpersRelType> operclaimPairwiseRelList = new ArrayList<IntOpersRelType>();
		operclaimPairwiseRelList.add(new OpersRelType("OperToClaim", "", "",
				true, true, true, 1, 1, 1, 1));

		OpersPairwiseRel directOperClaimSemanticEdge = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("OPERCLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexOper,
				instVertexCL, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semClaim
				.getIdentifier(), attribute.getName(), true));
		// simulOperationSubAction.addInVariable(attribute);

		attribute = new GlobalConfigAttribute("CompExp", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Boolean Comp. Expression",
				true, 0, -1, "", "", -1, "", "");
		semClaim.putSemanticAttribute("CompExp", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute("ConfidenceLevel", "Integer",
				AttributeType.OPERATION, "Confidence Level", 1, false,
				new RangeDomain(1, 5), 0, -1, "", "", -1, "", "");
		semClaim.putSemanticAttribute("ConfidenceLevel", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);

		attribute = new GlobalConfigAttribute("ClaimSelected", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Claim Selected", false, 0,
				-1, "", "", -1, "", "");
		semClaim.putSemanticAttribute("ClaimSelected", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new SemanticAttribute("ClaimExpression", "String",
				AttributeType.OPERATION, false, "Claim Expression Text", "", 0,
				-1, "", "", -1, "", "");
		semClaim.putSemanticAttribute("ClaimExpression", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
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

		OpersReasoningConcept semSoftDependency = new OpersReasoningConcept(
				semGeneralElement, "SoftDependency", true, null);
		InstVertex instVertexSD = new InstConcept("SoftDep", metaConcept,
				semSoftDependency);
		refas.getVariabilityVertex().put("SoftDep", instVertexSD);

		semanticExpressions = new ArrayList<IntMetaExpression>();

		semSoftDependency.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Product"), instVertexGE, "NextReqSelected", true, 4);

		t1 = new SemanticExpression("3", refas.getSemanticExpressionTypes()
				.get("Sum"), t1, 0);

		t1 = new SemanticExpression("4", refas.getSemanticExpressionTypes()
				.get("Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("Order...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"Order", true, t1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
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
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new GlobalConfigAttribute("SDSelected", "Boolean",
				AttributeType.GLOBALCONFIG, false, "SD Selected", false, 2, -1,
				"", "", -1, "", "");
		semSoftDependency.putSemanticAttribute("SDSelected", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

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

		List<IntOpersRelType> hardSemOverTwoRelList = new ArrayList<IntOpersRelType>();
		hardSemOverTwoRelList.add(new OpersRelType("and", "And", "And", false,
				false, false, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new OpersRelType("or", "Or", "Or", false,
				true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new OpersRelType("mutex", "Mutex", "Mutex.",
				false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new OpersRelType("range", "Range", "Range",
				false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new OpersRelType("none", "None", "None",
				false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new OpersRelType("other", "other", "other",
				false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new OpersRelType("And/Or/Mutex/[m,n]",
				"And/Or/Mutex/[m,n]", "AOMR", false, true, true, 2, -1, 1, 1));

		List<IntOpersRelType> featSemOverTwoRelList = new ArrayList<IntOpersRelType>();
		featSemOverTwoRelList.add(new OpersRelType("and", "And", "And", false,
				false, false, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new OpersRelType("or", "Or", "Or", false,
				true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new OpersRelType("mutex", "Mutex", "Mutex.",
				false, true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new OpersRelType("range", "Range", "range",
				false, true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new OpersRelType("other", "other", "other",
				false, true, true, 2, -1, 1, 1));

		OpersConcept semGeneralGroup = new OpersConcept("GeneralGroup");

		/*
		 * semGeneralGroup.putSemanticAttribute("Selected", new
		 * ExecCurrentStateAttribute("Selected", "Boolean",
		 * AttributeType.EXECCURRENTSTATE, false, "***Selected***", false, 2,
		 * -1, "", "", -1, "", "")); semGeneralGroup
		 * .putSemanticAttribute("NotAvailable", new
		 * ExecCurrentStateAttribute("NotAvailable", "Boolean",
		 * AttributeType.EXECCURRENTSTATE, false, "***Not Avaliable***", false,
		 * 2, -1, "", "", -1, "", ""));
		 */
		InstVertex instVertexGR = new InstConcept("GeneralGroup", metaConcept,
				semGeneralGroup);

		refas.getVariabilityVertex().put("GeneralGroup", instVertexGR);

		attribute = new ExecCurrentStateAttribute("True", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", true,
				2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));
		semGeneralGroup.putSemanticAttribute("True", attribute);
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));

		attribute = new ExecCurrentStateAttribute("False", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***NotSelected***",
				false, 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));
		semGeneralGroup.putSemanticAttribute("False", attribute);
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));

		attribute = new ExecCurrentStateAttribute("Selected", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", false,
				2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));
		semGeneralGroup.putSemanticAttribute("Selected", attribute);
		simulOperationSubAction.addOutVariable(attribute);
		simulOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addOutVariable(attribute);
		simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));

		attribute = new ExecCurrentStateAttribute("NotAvailable", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Not Avaliable***",
				false, 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));
		semGeneralGroup.putSemanticAttribute("NotAvailable", attribute);
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGeneralGroup.getIdentifier(), attribute.getName(), true));

		attribute = new SemanticAttribute("Description", "String",
				AttributeType.OPERATION, false, "Description", "", 0, -1, "",
				"", -1, "", "");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		semGeneralGroup.putSemanticAttribute("Description", attribute);

		OpersOverTwoRel semHardOverTwoRelation = new OpersOverTwoRel(
				semGeneralGroup, "SMMOverTwoRelation", hardSemOverTwoRelList);

		InstVertex instVertexHHGR = new InstConcept("GoalOTAsso",
				metaOverTwoRelation, semHardOverTwoRelation);
		refas.getVariabilityVertex().put("GoalOTAsso", instVertexHHGR);

		InstConcept instHchcHHGRHC = new InstConcept("GoaltoOTAssoPWAsso",
				metaPairwiseRelation);
		refas.getVariabilityVertex().put("GoaltoOTAssoPWAsso", instHchcHHGRHC);

		instEdge = new InstPairwiseRelation();
		refas.getConstraintInstEdges().put("hhtogr", instEdge);
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
				instVertexHC, "Selected", "True");

		t1 = new SemanticExpression("ANDhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHC, t1, "Selected");

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("and", new OptionAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
				instVertexHC, "Selected", "False");

		t1 = new SemanticExpression("ORhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHC, t1, "Selected");

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("or", new OptionAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("mutex", new OptionAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("range", new OptionAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		// List<AbstractSemanticVertex> semanticVertices = new
		// ArrayList<AbstractSemanticVertex>();

		List<IntOpersRelType> structHardSemPairwiseRelList = new ArrayList<IntOpersRelType>();
		structHardSemPairwiseRelList.add(new OpersRelType("means_ends",
				"means-ends", "means-ends", true, true, true, 1, -1, 1, 1));
		structHardSemPairwiseRelList.add(new OpersRelType("implication",
				"impl.", "Impl.", false, true, true, 1, -1, 1, 1));

		List<IntOpersRelType> sideHardSemPairwiseRelList = new ArrayList<IntOpersRelType>();
		sideHardSemPairwiseRelList.add(new OpersRelType("conflict", "conflict",
				"conflict", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new OpersRelType("alternative",
				"altern.", "altern.", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new OpersRelType("preferred", "pref.",
				"pref.", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new OpersRelType("require", "req.",
				"req.", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new OpersRelType("condition", "cond.",
				"cond.", false, true, true, 1, -1, 1, 1));

		List<IntOpersRelType> sgPairwiseRelList = new ArrayList<IntOpersRelType>();
		sgPairwiseRelList.add(new OpersRelType("contribution", "contribution",
				"contribution", true, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new OpersRelType("conflict", "conflict",
				"conflict", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new OpersRelType("alternative", "altern.",
				"altern.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new OpersRelType("preferred", "pref.", "pref.",
				false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new OpersRelType("implication", "impl.", "Impl.",
				false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new OpersRelType("require", "req.", "req.",
				false, true, true, 1, -1, 1, 1));

		OpersPairwiseRel directHardHardSemanticEdge = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Selected", "Selected");

		t1 = new SemanticExpression("CONFSelected", refas
				.getSemanticExpressionTypes().get("Implies"), 1, false, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("conflict", new OptionAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexHC, instVertexHC, null, "Selected", true, 1);

		t1 = new SemanticExpression("ALTSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE, instVertexHC,
				"Selected", true, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("alternative", new OptionAttribute(
				"alternative", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "alternative", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("preferred", new OptionAttribute("preferred",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"preferred", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Subtraction"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, instVertexHC, null, "Selected",
				false, 1);

		t1 = new SemanticExpression("REQSelected", refas
				.getSemanticExpressionTypes().get("GreaterOrEq"), 1, false, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("require", new OptionAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"condition", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("CONDSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("CONDNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		OpersPairwiseRel directStructHardHardSemanticEdge = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("means_ends", new OptionAttribute(
				"means_ends", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "means_ends", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		List<IntOpersRelType> featSideSemPairwiseRelList = new ArrayList<IntOpersRelType>();
		featSideSemPairwiseRelList.add(new OpersRelType("require", "require",
				"require", false, true, true, 1, -1, 1, 1));
		featSideSemPairwiseRelList.add(new OpersRelType("conflict", "excl.",
				"excl.", false, true, true, 1, -1, 1, 1));

		List<IntOpersRelType> featVertSemPairwiseRelList = new ArrayList<IntOpersRelType>();
		featVertSemPairwiseRelList.add(new OpersRelType("mandatory",
				"mandatory", "mandatory", true, true, true, 1, -1, 1, 1));
		featVertSemPairwiseRelList.add(new OpersRelType("optional", "opt.",
				"opt.", false, true, true, 1, -1, 1, 1));

		OpersPairwiseRel directFeaFeatVertSemEdge = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("MANSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("MANNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("mandatory", new OptionAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("OPTSelected", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("OPTNotAvailable", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE, instVertexF,
				instVertexF, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		attribute = new OptionAttribute("optional", StringType.IDENTIFIER,
				AttributeType.OPTION, false, "optional", "", 1, -1, "", "", -1,
				"", "");
		ias.add(new InstAttribute("optional", attribute, semanticExpressions));
		// simulOperationSubAction.addInVariable(attribute);
		// simulOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));

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

		OpersPairwiseRel directFeatFeatSideSemEdge = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "Selected", "Selected");

		t1 = new SemanticExpression("CONFSelected", refas
				.getSemanticExpressionTypes().get("Implies"), 1, false, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("conflict", new OptionAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Subtraction"),
				ExpressionVertexType.LEFTUNIQUEOUTRELVARIABLE,
				instDirFeatFeatSideSemEdge, null, instVertexF, "Selected",
				false, 1);

		t1 = new SemanticExpression("REQSelected", refas
				.getSemanticExpressionTypes().get("GreaterOrEq"), 1, false, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		OpersOverTwoRel semFeatOverTwoRelation = new OpersOverTwoRel(
				semGeneralGroup, "FeatFeatOTAsso", featSemOverTwoRelList);
		InstVertex instVertexFFGR = new InstConcept("FeatFeatOTAsso",
				metaOverTwoRelation, semFeatOverTwoRelation);
		refas.getVariabilityVertex().put("FeatFeatOTAsso", instVertexFFGR);

		List<IntOpersRelType> assetoperPairwiseRelList = new ArrayList<IntOpersRelType>();
		assetoperPairwiseRelList.add(new OpersRelType("implementation",
				"implementation", "imp.", true, true, true, 1, 1, 1, 1));

		OpersPairwiseRel semAssetOperPairwiseRel = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("IMPLSelected1", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexHC, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("IMPLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexHC, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		List<IntOpersRelType> assetPairwiseRelList = new ArrayList<IntOpersRelType>();
		assetPairwiseRelList.add(new OpersRelType("delegation", "delegation",
				"deleg.", true, true, true, 1, 1, 1, 1));
		assetPairwiseRelList.add(new OpersRelType("assembly", "assembly",
				"assembly", true, true, true, 1, 1, 1, 1));

		OpersPairwiseRel semAssetPairwiseRel = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("DELSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexAsset, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("DELNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexAsset, "NotAvailable",
				"NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("delegation", new OptionAttribute(
				"delegation", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "delegation", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();
		t1 = new SemanticExpression("ASSESelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexAsset, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("ASSENotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		List<IntOpersRelType> vcntxPairwiseRelList = new ArrayList<IntOpersRelType>();
		vcntxPairwiseRelList.add(new OpersRelType("Variable Context", "", "",
				true, true, true, 1, 1, 1, 1));

		OpersPairwiseRel semvarcntxPairwiseRel = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

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

		List<IntOpersRelType> sdPairwiseRelList = new ArrayList<IntOpersRelType>();
		sdPairwiseRelList.add(new OpersRelType("SD", "", "", true, true, true,
				1, 1, 1, 1));

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

		List<IntOpersRelType> claimSGPairwiseRelList = new ArrayList<IntOpersRelType>();
		claimSGPairwiseRelList.add(new OpersRelType("ClaimToSG", "", "", true,
				true, true, 1, 1, 1, 1));

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
		List<IntOpersRelType> groupPairwiseRelList = new ArrayList<IntOpersRelType>();
		groupPairwiseRelList.add(new OpersRelType("Group", "", "", true, true,
				true, 1, 1, 1, 1));
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
		List<IntOpersRelType> nonePairwiseRelList = new ArrayList<IntOpersRelType>();
		nonePairwiseRelList.add(new OpersRelType("Group", "", "", true, true,
				true, 1, 1, 1, 1));

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
		List<IntOpersRelType> genconsPairwiseRelList = new ArrayList<IntOpersRelType>();
		genconsPairwiseRelList.add(new OpersRelType("GeneralConstraint", "",
				"", true, true, true, 1, 1, 1, 1));

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

		OpersPairwiseRel directSGSGSemEdge = new OpersPairwiseRel("SgSgPWAsso",
				true, sgPairwiseRelList);
		attribute = new SemanticAttribute(OpersPairwiseRel.VAR_SOURCE_LEVEL,
				"Integer", AttributeType.OPERATION,
				OpersPairwiseRel.VAR_SOURCE_LEVELNAME, 0, false,
				new RangeDomain(0, 5), 0, -1, "", "", -1, "", "");
		directSGSGSemEdge.putSemanticAttribute(
				OpersPairwiseRel.VAR_SOURCE_LEVEL, attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));

		attribute = new SemanticAttribute(OpersPairwiseRel.VAR_TARGET_LEVEL,
				"Integer", AttributeType.OPERATION,
				OpersPairwiseRel.VAR_TARGET_LEVELNAME, 0, false,
				new RangeDomain(0, 5), 0, -1, "", "", -1, "", "");
		directSGSGSemEdge.putSemanticAttribute(
				OpersPairwiseRel.VAR_TARGET_LEVEL, attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));

		attribute = new SemanticAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", 0, 0, -1,
				"", "", -1, "", "");
		directSGSGSemEdge.putSemanticAttribute("AggregationLow", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));

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
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));

		directSGSGSemEdge.addPanelVisibleAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");

		directSGSGSemEdge.addPropEditableAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPropVisibleAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPropEditableAttribute("08#"
				+ OpersPairwiseRel.VAR_SOURCE_LEVEL);
		directSGSGSemEdge.addPropVisibleAttribute("08#"
				+ OpersPairwiseRel.VAR_SOURCE_LEVEL);
		directSGSGSemEdge.addPanelVisibleAttribute("08#"
				+ OpersPairwiseRel.VAR_SOURCE_LEVEL);

		directSGSGSemEdge.addPanelSpacersAttribute(":#"
				+ OpersPairwiseRel.VAR_TARGET_LEVEL + "#");
		directSGSGSemEdge.addPropEditableAttribute("09#"
				+ OpersPairwiseRel.VAR_TARGET_LEVEL);
		directSGSGSemEdge.addPropVisibleAttribute("09#"
				+ OpersPairwiseRel.VAR_TARGET_LEVEL);
		directSGSGSemEdge.addPanelVisibleAttribute("09#"
				+ OpersPairwiseRel.VAR_TARGET_LEVEL);

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

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		// TODO include expressions for contribution

		ias.add(new InstAttribute("contribution", new OptionAttribute(
				"contribution", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "contribution", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("2", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexSG,
				instVertexSG, "Selected", "Selected");

		t1 = new SemanticExpression("CONFSelected", refas
				.getSemanticExpressionTypes().get("Implies"), 1, false, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("conflict", new OptionAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("alternative", new OptionAttribute(
				"alternative", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "alternative", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("preferred", new OptionAttribute("preferred",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"preferred", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Subtraction"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, null, "Selected", false,
				1);

		t1 = new SemanticExpression("REQSelected", refas
				.getSemanticExpressionTypes().get("GreaterOrEq"), 1, false, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("require", new OptionAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		OpersOverTwoRel semanticSGSGGroupRelation = new OpersOverTwoRel(
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

		OpersPairwiseRel directCVCGSemanticEdge = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		OpersOverTwoRel semanticOperClaimGroupRelation = new OpersOverTwoRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
				instVertexOper, "Selected", "True");

		t1 = new SemanticExpression("ANDhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, t1, "Selected");

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("and", new OptionAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
				instVertexOper, "Selected", "False");

		t1 = new SemanticExpression("ORhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, t1, "Selected");

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("or", new OptionAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("mutex", new OptionAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
		updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
		semanticExpressions.add(t1);

		ias.add(new InstAttribute("range", new OptionAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		refas.getVariabilityVertex().put("OperCLOTAsso", instVertexCLGR);

		OpersPairwiseRel directOperClaimToSemanticEdge = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("OPERCLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexCLGR,
				instVertexCL, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		OpersPairwiseRel directOperClaimFromSemanticEdge = new OpersPairwiseRel(
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
		OpersOverTwoRel semanticLFClaimGroupRelation = new OpersOverTwoRel(
				semGeneralGroup, "LFtoClaimOTAsso", hardSemOverTwoRelList);

		InstVertex instVertexLFCLGR = new InstConcept("LFtoClaimOTAsso",
				metaOverTwoRelation, semanticLFClaimGroupRelation);

		refas.getVariabilityVertex().put("LFtoClaimOTAsso", instVertexLFCLGR);

		OpersPairwiseRel directFClaimToSemanticEdge = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("OPERCLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexLFCLGR, instVertexCL, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		OpersPairwiseRel directFClaimFromSemanticEdge = new OpersPairwiseRel(
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

		OpersPairwiseRel directLFClaimSemanticEdge = new OpersPairwiseRel(
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
		OpersPairwiseRel directClaimSGSemanticEdge = new OpersPairwiseRel(
				"ClaimSGPWAsso", true, claimSGPairwiseRelList);
		attribute = new SemanticAttribute("CLSGLevel", "Integer",
				AttributeType.OPERATION, "Relation Level", 2, false,
				new RangeDomain(0, 5), 0, -1, "", "", -1, "", "");
		directClaimSGSemanticEdge.putSemanticAttribute("CLSGLevel", attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		directClaimSGSemanticEdge.addPropEditableAttribute("08#" + "CLSGLevel");
		directClaimSGSemanticEdge.addPropVisibleAttribute("08#" + "CLSGLevel");
		directClaimSGSemanticEdge.addPanelVisibleAttribute("08#" + "CLSGLevel");
		InstConcept instDirClaimSGSemanticEdge = new InstConcept(
				"ClaimSGPWAsso", metaPairwiseRelation,
				directClaimSGSemanticEdge);

		ia = instDirClaimSGSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("ClaimToSG", new OptionAttribute("ClaimToSG",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"Claim To SG", "", 1, -1, "", "", -1, "", ""),
				"ClaimToSG#ClaimToSG#true#true#true#1#-1#1#1"));

		ia = instDirClaimSGSemanticEdge
				.getInstAttribute("operationsExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("GreaterOrEq"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("ClaimToSG", new OptionAttribute("ClaimToSG",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"claim To SG", "", 1, -1, "", "", -1, "", ""),
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

		OpersPairwiseRel directSDSGSemanticEdge = new OpersPairwiseRel(
				"SDSGPWAsso", true, sdPairwiseRelList);
		attribute = new SemanticAttribute(OpersPairwiseRel.VAR_LEVEL,
				"Integer", AttributeType.OPERATION,
				OpersPairwiseRel.VAR_LEVELNAME, 0, false,
				new RangeDomain(0, 5), 0, -1, "", "", -1, "", "");
		directSDSGSemanticEdge.putSemanticAttribute(OpersPairwiseRel.VAR_LEVEL,
				attribute);
		simulationExecOperUniqueLabeling.addAttribute(attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simulOperationSubAction.addInVariable(attribute);
		simulOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simSceOperationSubAction.addInVariable(attribute);
		simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLabeling2.addAttribute(attribute);
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		directSDSGSemanticEdge.addPropEditableAttribute("08#"
				+ OpersPairwiseRel.VAR_LEVEL);
		directSDSGSemanticEdge.addPropVisibleAttribute("08#"
				+ OpersPairwiseRel.VAR_LEVEL);
		directSDSGSemanticEdge.addPanelVisibleAttribute("08#"
				+ OpersPairwiseRel.VAR_LEVEL);
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

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
				"low");

		t1 = new SemanticExpression("low: SDReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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
				"high");

		t1 = new SemanticExpression("high: SDReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("SDSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE, instVertexCL,
				instVertexCL, "Selected", "ConditionalExpression");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		OpersOverTwoRel semanticAssetAssetOvertwoRel = new OpersOverTwoRel(
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
		OpersOverTwoRel semanticAssetOperGroupRelation = new OpersOverTwoRel(
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

		OpersPairwiseRel groupAssetOperSemanticEdge = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("IMPSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexOPERGR, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("IMPNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexOPERGR, "NotAvailable",
				"NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		OpersPairwiseRel directAssetOperSemanticEdge = new OpersPairwiseRel(
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

		semanticExpressions = new ArrayList<IntMetaExpression>();

		t1 = new SemanticExpression("IMPSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCRELVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTRELVARIABLE,
				instVertexOPERGR, instVertexOper, "Selected", "Selected");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		t1 = new SemanticExpression("IMPNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCRELVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTRELVARIABLE,
				instVertexOPERGR, instVertexOper, "NotAvailable",
				"NotAvailable");

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		semanticExpressions = new ArrayList<IntMetaExpression>();

		refasModel.setSemanticExpressions(semanticExpressions);

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
				instVertexGE, "NextPrefSelected", 0);

		t1 = new SemanticExpression("prefSel <=1", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTITERCONCEPTVARIABLE, instRefasModel,
				instVertexGE, t1, 1);

		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

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

		t1 = new SemanticExpression("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
				instVertexSG, "Selected", 0);

		t1 = new SemanticExpression("TotalSG", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERCONCEPTVARIABLE, instRefasModel,
				instVertexSG, t1, "TotalSG");

		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

		semanticExpressions.add(t1);

	}
}
