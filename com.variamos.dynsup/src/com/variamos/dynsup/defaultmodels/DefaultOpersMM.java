package com.variamos.dynsup.defaultmodels;

import java.util.ArrayList;
import java.util.List;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelExpr;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.OpersExpr;
import com.variamos.dynsup.model.OpersIOAttribute;
import com.variamos.dynsup.model.OpersLabeling;
import com.variamos.dynsup.model.OpersOverTwoRel;
import com.variamos.dynsup.model.OpersPairwiseRel;
import com.variamos.dynsup.model.OpersRelType;
import com.variamos.dynsup.model.OpersSubOperation;
import com.variamos.dynsup.model.OpersSubOperationExpType;
import com.variamos.dynsup.model.OpersVariable;
import com.variamos.dynsup.statictypes.SatisficingType;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.ExpressionVertexType;
import com.variamos.dynsup.types.StringType;
import com.variamos.dynsup.types.VariableType;
import com.variamos.hlcl.LabelingOrder;
import com.variamos.hlcl.RangeDomain;
import com.variamos.hlcl.StringDomain;

public class DefaultOpersMM {
	static OpersConcept verifDeadElemOperationAction = null;
	static OpersSubOperationExpType verifDeadElemOperSubActionNormal = null;
	static OpersSubOperationExpType verifDeadElemOperSubActionRelaxable = null;
	static OpersSubOperationExpType verifDeadElemOperSubActionVerification = null;

	static OpersConcept verifParentsOperationAction = null;
	static OpersSubOperationExpType verifParentsOperSubActionNormal = null;
	static OpersSubOperationExpType verifParentsOperSubActionRelaxable = null;
	static OpersSubOperationExpType verifParentsOperSubActionVerification = null;

	static OpersConcept verifRootOperationAction = null;
	static OpersSubOperationExpType verifRootOperSubActionNormal = null;
	static OpersSubOperationExpType verifRootOperSubActionRelaxable = null;
	static OpersSubOperationExpType verifRootOperSubActionVerification = null;

	static OpersConcept verifFalseOptOperationAction = null;
	static OpersSubOperationExpType verifFalseOptOperSubActionNormal = null;
	static OpersSubOperationExpType verifFalseOptOperSubActionRelaxable = null;
	static OpersSubOperationExpType verifFalseOptOperSubActionVerification = null;

	static OpersConcept simulationOperationAction = null;

	static OpersSubOperationExpType simulationExecOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulationPreValOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulationPreUpdOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulationPosValOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulationPostUpdOptOperSubActionNormal = null;

	static OpersConcept simulScenOperationAction = null;

	static OpersSubOperationExpType simulScenExecOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulScenPreValOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulScenPreUpdOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulScenPosValOptOperSubActionNormal = null;
	static OpersSubOperationExpType simulScenPostUpdOptOperSubActionNormal = null;

	static OpersConcept configTemporalOperationAction = null;

	static OpersSubOperationExpType configTemporalOptOperSubActionNormal = null;

	static OpersConcept configPermanentOperationAction = null;

	static OpersSubOperationExpType configPermanentOptOperSubActionNormal = null;

	static OpersConcept updateCoreOperationAction = null;

	static OpersSubOperationExpType updateCoreOptOperSubActionNormal = null;

	@SuppressWarnings("unchecked")
	public static void createOpersMetaModel(ModelInstance refas, boolean empty) {
		InstElement metaMetaModel = ((InstConcept) refas.getSyntaxModel()
				.getVertex("OMModel"));
		InstElement metaOperationMenu = ((InstConcept) refas.getSyntaxModel()
				.getVertex("OMOperGroup"));
		InstElement metaOperationAction = ((InstConcept) refas.getSyntaxModel()
				.getVertex("OMOperation"));
		InstElement metaOperationSubAction = ((InstConcept) refas
				.getSyntaxModel().getVertex("OMSubOper"));
		InstElement metaLabeling = ((InstConcept) refas.getSyntaxModel()
				.getVertex("OMLabeling"));
		InstElement metaExpType = ((InstConcept) refas.getSyntaxModel()
				.getVertex("OMExpType"));

		// MetaEnumeration metaEnumeration = (MetaEnumeration) ((InstConcept)
		// refas
		// .getSyntaxModel().getVertex("TypeEnumeration"))
		// .getEditableMetaElement();
		InstElement metaMetaConcept = ((InstConcept) refas.getSyntaxModel()
				.getVertex("OMConcept"));
		InstConcept metaMetaInstConcept = ((InstConcept) refas.getSyntaxModel()
				.getVertex("OMConcept"));
		InstElement metaMetaPairwiseRelation = ((InstConcept) refas
				.getSyntaxModel().getVertex("OMPWRel"));
		InstConcept metaMetaInstOverTwoRel = ((InstConcept) refas
				.getSyntaxModel().getVertex("OMOTRel"));

		InstElement infraMetaMetaConcept = ((InstConcept) refas
				.getSyntaxModel().getVertex("OMInfConcept"));
		InstElement infraMetaMetaPairwiseRelation = ((InstConcept) refas
				.getSyntaxModel().getVertex("OMInfraPWRel"));
		InstElement infraMetaMetaOverTwoRelation = ((InstConcept) refas
				.getSyntaxModel().getVertex("OMInfraOTRel"));

		InstPairwiseRel metaPairwRelCCExt = ((InstPairwiseRel) refas
				.getSyntaxModel().getConstraintInstEdge("ExtendsCCRel"));
		InstPairwiseRel metaPairwRelOCExt = ((InstPairwiseRel) refas
				.getSyntaxModel().getConstraintInstEdge("ExtendsOCRel"));

		InstPairwiseRel metaPairwRelAso = ((InstPairwiseRel) refas
				.getSyntaxModel().getConstraintInstEdge(
						"InfraSyntaxOpersM2AsoRel"));

		OpersLabeling simulationExecOperUniqueLabeling = null;
		simulationExecOperUniqueLabeling = new OpersLabeling("unique");
		OpersLabeling simsceExecOperLabeling1 = null;
		simsceExecOperLabeling1 = new OpersLabeling("all");

		OpersLabeling simsceExecOperLabeling2 = null;
		simsceExecOperLabeling2 = new OpersLabeling("once"); // TODO define max
																// for SG

		OpersConcept refasModel = new OpersConcept("REFAS");

		ElemAttribute attribute = null;
		/*
		 * attribute = new ElemAttribute("TotalOrder", "Integer",
		 * AttributeType.EXECCURRENTSTATE, false, "***TotalOrder***", 0, new
		 * RangeDomain(0, 2000), 2, -1, "", "", -1, "", "");
		 * simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		 * refasModel.getIdentifier(), attribute.getName(), true));
		 * refasModel.putSemanticAttribute("TotalOrder", attribute);
		 * 
		 * attribute = new ElemAttribute("TotalOpt", "Integer",
		 * AttributeType.EXECCURRENTSTATE, false, "***TotalOpt***", 0, new
		 * RangeDomain(0, 2000), 2, -1, "", "", -1, "", "");
		 * simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		 * refasModel.getIdentifier(), attribute.getName(), true));
		 * refasModel.putSemanticAttribute("TotalOpt", attribute);
		 * 
		 * attribute = new ElemAttribute("TotalSG", "Integer",
		 * AttributeType.EXECCURRENTSTATE, false, "***TotalSG***", 0, new
		 * RangeDomain(0, 2000), 2, -1, "", "", -1, "", "");
		 * simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(refasModel
		 * .getIdentifier(), attribute.getName(), true));
		 * refasModel.putSemanticAttribute("TotalSG", attribute);
		 */
		OpersSubOperation simulOperationSubAction = null;
		OpersSubOperation simSceOperationSubAction = null;
		InstConcept instRefasModel = null;

		if (!empty) {
			instRefasModel = new InstConcept("REFAS", metaMetaModel, refasModel);
			refas.getVariabilityVertex().put("REFAS", instRefasModel);

			InstAttribute ia = null;
			List<InstAttribute> ias = null;

			OpersConcept operationMenu = new OpersConcept("SimulationGroup");

			InstConcept instOperationGroup = new InstConcept("SimulationGroup",
					metaOperationMenu, operationMenu);
			refas.getVariabilityVertex().put("SimulationGroup",
					instOperationGroup);

			instOperationGroup.getInstAttribute("visible").setValue(true);
			instOperationGroup.getInstAttribute("menuType").setValue("4");
			instOperationGroup.getInstAttribute("name").setValue(
					"Basic Simulation (Dynamic)");
			instOperationGroup.getInstAttribute("shortcut").setValue("S");
			instOperationGroup.getInstAttribute("Index").setValue(1);

			simulationOperationAction = new OpersConcept("SimulationOper");

			InstConcept instOperationAction = new InstConcept("SimulationOper",
					metaOperationAction, simulationOperationAction);
			refas.getVariabilityVertex().put("SimulationOper",
					instOperationAction);

			instOperationAction.getInstAttribute("name").setValue(
					"Start Simulation");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(true);
			instOperationAction.getInstAttribute("iterationName").setValue(
					"Next Solution");
			instOperationAction.getInstAttribute("prevSpacer").setValue(true);

			InstPairwiseRel instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-menu", instEdgeOper);
			instEdgeOper.setIdentifier("sim-menu");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			OpersSubOperation operationSubAction = new OpersSubOperation(1,
					"Sim-Pre-Validation");

			// simulationOperationAction.addExpressionSubAction(operationSubAction);

			InstConcept instOperationSubAction = new InstConcept(
					"Sim-Pre-Validation", metaOperationSubAction,
					operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"SINGLEVERIFICATION");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("Sim-Pre-Validation",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-pre-val", instEdgeOper);
			instEdgeOper.setIdentifier("sim-pre-val");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			simulationPreValOptOperSubActionNormal = new OpersSubOperationExpType();

			InstConcept instOperSubOperationExpType = new InstConcept(
					"exptype", metaExpType,
					simulationPreValOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			OpersLabeling operationLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			InstConcept instLabeling = new InstConcept(
					"Sim-Pre-Validation-lab", metaLabeling, operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("Sim-Pre-Validation-lab",
					instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-pre-val-lab", instEdgeOper);
			instEdgeOper.setIdentifier("sim-pre-val-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			operationSubAction = new OpersSubOperation(2, "Sim-Pre-Update");
			// simulationOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("Sim-Pre-Update",
					metaOperationSubAction, operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"SINGLEUPDATE");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(2);

			refas.getVariabilityVertex().put("Sim-Pre-Update",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-pre-upd", instEdgeOper);
			instEdgeOper.setIdentifier("sim-pre-upd");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			simulationPreUpdOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, simulationPreUpdOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Sim-pre-update-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex()
					.put("Sim-pre-update-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-pre-upd-lab", instEdgeOper);
			instEdgeOper.setIdentifier("sim-pre-upd-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			simulOperationSubAction = new OpersSubOperation(3, "Sim-Execution");

			List<OpersExpr> semanticExpressions = new ArrayList<OpersExpr>();

			simulationExecOperUniqueLabeling
					.setSemanticExpressions(semanticExpressions);

			OpersExpr t1;
			/*
			 * = new SemanticExpression("sub", refas
			 * .getSemanticExpressionTypes().get("Sum"),
			 * ExpressionVertexType.LEFTITERCONFIXEDVARIABLE, instRefasModel,
			 * "TotalOrder", 0);
			 * 
			 * semanticExpressions.add(t1);
			 * 
			 * t1 = new SemanticExpression("sub",
			 * refas.getSemanticExpressionTypes() .get("Sum"),
			 * ExpressionVertexType.LEFTITERCONFIXEDVARIABLE, instRefasModel,
			 * "TotalOpt", 0);
			 * 
			 * semanticExpressions.add(t1);
			 */

			OpersExpr t2;

			OpersExpr t3;

			// simulationOperationAction
			// .addExpressionSubAction(simulOperationSubAction);

			instOperationSubAction = new InstConcept("Sim-Execution",
					metaOperationSubAction, simulOperationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"ITERATIVEUPDATE");
			instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("Index").setValue(3);

			refas.getVariabilityVertex().put("Sim-Execution",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-exec", instEdgeOper);
			instEdgeOper.setIdentifier("sim-exec");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			simulationExecOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, simulationExecOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			// simulOperationSubAction
			// .addOperationLabeling(simulationExecOperUniqueLabeling);

			instLabeling = new InstConcept("Sim-Execution-lab", metaLabeling,
					simulationExecOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(true);

			List<InstAttribute> sortatt = (List<InstAttribute>) instLabeling
					.getInstAttribute("sortorder").getValue();
			sortatt.add(new InstAttribute("enum1", new ElemAttribute(
					"EnumValue", StringType.IDENTIFIER, AttributeType.SYNTAX,
					false, "Enumeration Value", "", "", 1, -1, "", "", -1, "",
					""), LabelingOrder.MIN));
			sortatt.add(new InstAttribute("enum2", new ElemAttribute(
					"EnumValue", StringType.IDENTIFIER, AttributeType.SYNTAX,
					false, "Enumeration Value", "", "", 1, -1, "", "", -1, "",
					""), LabelingOrder.MIN));

			refas.getVariabilityVertex().put("Sim-Execution-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-Execution-lab",
					instEdgeOper);
			instEdgeOper.setIdentifier("sim-Execution-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			operationSubAction = new OpersSubOperation(4, "Sim-Post-Validation");

			// simulationOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("Sim-Post-Validation",
					metaOperationSubAction, operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"SINGLEVERIFICATION");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			refas.getVariabilityVertex().put("Sim-Post-Validation",
					instOperationSubAction);
			instOperationSubAction.getInstAttribute("Index").setValue(4);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-pos-val", instEdgeOper);
			instEdgeOper.setIdentifier("sim-pos-val");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			simulationPosValOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, simulationPosValOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// simulOperationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Sim-pos-val-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("Sim-pos-val-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-pos-val-lab", instEdgeOper);
			instEdgeOper.setIdentifier("sim-pos-val-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			operationSubAction = new OpersSubOperation(5, "Sim-Post-Update");

			// simulationOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("Sim-Post-Update",
					metaOperationSubAction, operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"SINGLEUPDATE");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(5);

			refas.getVariabilityVertex().put("Sim-Post-Update",
					instOperationSubAction);

			simulationPostUpdOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, simulationPostUpdOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// simulOperationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Sim-Post-Update-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("Sim-Post-Update-lab",
					instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-Post-Update-lab",
					instEdgeOper);
			instEdgeOper.setIdentifier("sim-Post-Update-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			operationMenu = new OpersConcept("SimulationSCeOper");

			instOperationGroup = new InstConcept("SimulationSceGroup",
					metaOperationMenu, operationMenu);
			refas.getVariabilityVertex().put("SimulationSceGroup",
					instOperationGroup);

			instOperationGroup.getInstAttribute("visible").setValue(true);
			instOperationGroup.getInstAttribute("menuType").setValue("4");
			instOperationGroup.getInstAttribute("name").setValue(
					"Simulation Scenarios");
			instOperationGroup.getInstAttribute("shortcut").setValue("C");
			instOperationGroup.getInstAttribute("Index").setValue(1);

			simulScenOperationAction = new OpersConcept("SimulationScenarios");

			instEdgeOper = new InstPairwiseRel();
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
			instOperationAction.getInstAttribute("prevSpacer").setValue(true);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("simsce-menu", instEdgeOper);
			instEdgeOper.setIdentifier("simsce-menu");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			operationSubAction = new OpersSubOperation(1,
					"SimSce-Pre-Validation");

			// simulScenOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("SimSce-Pre-Validation",
					metaOperationSubAction, operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"SINGLEVERIFICATION");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("SimSce-Pre-Validation",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("simsce-pre-val", instEdgeOper);
			instEdgeOper.setIdentifier("simsce-pre-val");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			simulScenPreValOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, simulScenPreValOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// simulOperationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("SimSce-pre-val-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex()
					.put("SimSce-pre-val-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("simsce-pre-val-lab",
					instEdgeOper);
			instEdgeOper.setIdentifier("simsce-pre-val-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			operationSubAction = new OpersSubOperation(2, "SimSce-Pre-Update");

			// simulScenOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("SimSce-Pre-Update",
					metaOperationSubAction, operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"SINGLEUPDATE");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(2);

			refas.getVariabilityVertex().put("SimSce-Pre-Update",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("simsce-pre-upd", instEdgeOper);
			instEdgeOper.setIdentifier("simsce-pre-upd");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			simulScenPreValOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, simulScenPreValOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// simulOperationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("SimSce-pre-upd-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex()
					.put("SimSce-pre-upd-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("simsce-pre-upd-lab",
					instEdgeOper);
			instEdgeOper.setIdentifier("simsce-pre-upd-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			simSceOperationSubAction = new OpersSubOperation(3,
					"SimSce-Execution");
			// simulScenOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("SimSce-Execution",
					metaOperationSubAction, simSceOperationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"ITERATIVEUPDATE");
			instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("Index").setValue(3);

			refas.getVariabilityVertex().put("SimSce-Execution",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("simsce-exec", instEdgeOper);
			instEdgeOper.setIdentifier("simsce-exec");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			simulScenExecOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, simulScenExecOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			// simulOperationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("SimSce-exec-lab1", metaLabeling,
					simsceExecOperLabeling1);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("SimSce-exec-lab1", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("simsce-exec-lab1", instEdgeOper);
			instEdgeOper.setIdentifier("simsce-exec-lab1");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// simulOperationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("SimSce-exec-lab2", metaLabeling,
					simsceExecOperLabeling2);

			instLabeling.getInstAttribute("labelId").setValue("L2");
			instLabeling.getInstAttribute("position").setValue(2);
			instLabeling.getInstAttribute("once").setValue(true);
			instLabeling.getInstAttribute("order").setValue(true);

			sortatt = (List<InstAttribute>) instLabeling.getInstAttribute(
					"sortorder").getValue();
			sortatt.add(new InstAttribute("enum1", new ElemAttribute(
					"EnumValue", StringType.IDENTIFIER, AttributeType.SYNTAX,
					false, "Enumeration Value", "", "", 1, -1, "", "", -1, "",
					""), LabelingOrder.MAX));

			refas.getVariabilityVertex().put("SimSce-exec-lab2", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("simsce-exec-lab2", instEdgeOper);
			instEdgeOper.setIdentifier("simsce-exec-lab2");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			operationSubAction = new OpersSubOperation(4,
					"SimSce-Post-Validation");

			// simulScenOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("SimSce-Post-Validation",
					metaOperationSubAction, operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"SINGLEVERIFICATION");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(4);

			refas.getVariabilityVertex().put("SimSce-Post-Validation",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("simsce-pos-val", instEdgeOper);
			instEdgeOper.setIdentifier("simsce-pos-val");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			simulScenPosValOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, simulScenPosValOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// simulOperationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("SimSce-pos-val-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex()
					.put("SimSce-pos-val-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("simsce-pos-val-lab",
					instEdgeOper);
			instEdgeOper.setIdentifier("simsce-pos-val-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			operationSubAction = new OpersSubOperation(5, "SimSce-Post-Update");
			// simulScenOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("SimSce-Post-Update",
					metaOperationSubAction, operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"SINGLEUPDATE");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(5);

			refas.getVariabilityVertex().put("SimSce-Post-Update",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("simsce-pos-upd", instEdgeOper);
			instEdgeOper.setIdentifier("simsce-pos-upd");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			simulScenPostUpdOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, simulScenPostUpdOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// simulOperationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("SimSce-Post-Update-lab",
					metaLabeling, operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);

			refas.getVariabilityVertex().put("SimSce-Post-Update-lab",
					instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("simSce-Post-Update-lab",
					instEdgeOper);
			instEdgeOper.setIdentifier("simSce-Post-Update-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			operationMenu = new OpersConcept("Verification");

			instOperationGroup = new InstConcept("Verification",
					metaOperationMenu, operationMenu);
			refas.getVariabilityVertex()
					.put("Verification", instOperationGroup);

			instOperationGroup.getInstAttribute("visible").setValue(false);
			instOperationGroup.getInstAttribute("menuType").setValue("2");
			instOperationGroup.getInstAttribute("name")
					.setValue("Verification");
			instOperationGroup.getInstAttribute("shortcut").setValue("V");

			updateCoreOperationAction = new OpersConcept("UpdateCoreOper");

			instOperationAction = new InstConcept("UpdateCoreOper",
					metaOperationAction, updateCoreOperationAction);
			refas.getVariabilityVertex().put("UpdateCoreOper",
					instOperationAction);

			instOperationAction.getInstAttribute("name").setValue(
					"Update Core Operation");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-menu-upd", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-upd");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			operationSubAction = new OpersSubOperation(1, "UpdateCoreSubOper");
			// updateCoreOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("UpdateCoreSubOper",
					metaOperationSubAction, operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"SINGLEUPDATE");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("UpdateCoreSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("upd-core", instEdgeOper);
			instEdgeOper.setIdentifier("upd-core");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			updateCoreOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, updateCoreOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Upd-core-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("Upd-core-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("upd-core-lab", instEdgeOper);
			instEdgeOper.setIdentifier("upd-core-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			verifDeadElemOperationAction = new OpersConcept(
					"VerifyDeadElementsOper");

			instOperationAction = new InstConcept("VerifyDeadElementsOper",
					metaOperationAction, verifDeadElemOperationAction);
			refas.getVariabilityVertex().put("VerifyDeadElementsOper",
					instOperationAction);

			instOperationAction.getInstAttribute("name").setValue(
					"Verify Dead Elements Operation");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-menu-dead", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-dead");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			operationSubAction = new OpersSubOperation(1,
					"VerifyDeadElementsSubOper");
			// verifDeadElemOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept(
					"VerifyDeadElementsSubOper", metaOperationSubAction,
					operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"MUTIVERIFICATION");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);

			refas.getVariabilityVertex().put("VerifyDeadElementsSubOper",
					instOperationSubAction);

			instOperationSubAction.getInstAttribute("Index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-dead", instEdgeOper);
			instEdgeOper.setIdentifier("ver-dead");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			verifDeadElemOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifDeadElemOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			verifDeadElemOperSubActionRelaxable = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifDeadElemOperSubActionRelaxable);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "RELAXABLE");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			verifDeadElemOperSubActionVerification = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifDeadElemOperSubActionVerification);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "VERIFICATION");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Ver-dead-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("Ver-dead-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-dead-lab", instEdgeOper);
			instEdgeOper.setIdentifier("ver-dead-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			verifParentsOperationAction = new OpersConcept("VerifyParentsOper");

			instOperationAction = new InstConcept("VerifyParentsOper",
					metaOperationAction, verifParentsOperationAction);
			refas.getVariabilityVertex().put("VerifyParentsOper",
					instOperationAction);

			instOperationAction.getInstAttribute("name").setValue(
					"Verify Parents Operation");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-menu-pare", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-pare");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			operationSubAction = new OpersSubOperation(1,
					"VerifyParentsSubOper");
			// verifParentsOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("VerifyParentsSubOper",
					metaOperationSubAction, operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"MUTIVERIFICATION");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);

			refas.getVariabilityVertex().put("VerifyParentsSubOper",
					instOperationSubAction);

			instOperationSubAction.getInstAttribute("Index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-par", instEdgeOper);
			instEdgeOper.setIdentifier("ver-par");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			verifParentsOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifParentsOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			verifParentsOperSubActionRelaxable = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifParentsOperSubActionRelaxable);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "RELAXABLE");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			verifParentsOperSubActionVerification = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifParentsOperSubActionVerification);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "VERIFICATION");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Ver-par-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("Ver-par-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-par-lab", instEdgeOper);
			instEdgeOper.setIdentifier("ver-par-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			verifRootOperationAction = new OpersConcept("VerifyRootsOper");

			instOperationAction = new InstConcept("VerifyRootsOper",
					metaOperationAction, verifRootOperationAction);
			refas.getVariabilityVertex().put("VerifyRootsOper",
					instOperationAction);

			instOperationAction.getInstAttribute("name").setValue(
					"Verify Roots Operation");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-menu-root", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-root");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			operationSubAction = new OpersSubOperation(1, "VerifyRootsSubOper");
			// verifRootOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("VerifyRootsSubOper",
					metaOperationSubAction, operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"MUTIVERIFICATION");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);

			refas.getVariabilityVertex().put("VerifyRootsSubOper",
					instOperationSubAction);

			instOperationSubAction.getInstAttribute("Index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-root", instEdgeOper);
			instEdgeOper.setIdentifier("ver-root");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			verifRootOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifRootOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			verifRootOperSubActionRelaxable = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifRootOperSubActionRelaxable);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "RELAXABLE");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			verifRootOperSubActionVerification = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifRootOperSubActionVerification);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "VERIFICATION");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Ver-root-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("Ver-root-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-root-lab", instEdgeOper);
			instEdgeOper.setIdentifier("ver-root-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			verifFalseOptOperationAction = new OpersConcept(
					"VerifyFalseOperations");

			instOperationAction = new InstConcept("VerifyFalseOperations",
					metaOperationAction, verifFalseOptOperationAction);
			refas.getVariabilityVertex().put("VerifyFalseOperations",
					instOperationAction);

			instOperationAction.getInstAttribute("name").setValue(
					"Verify False Optional Operation");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-menu-false", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-false");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			operationSubAction = new OpersSubOperation(1,
					"VerifyFalseSubOperations");
			// operationSubAction.addOperationLabeling(new
			// OperationLabeling("unique",
			// "L1", 1, false, null, null));
			// verifFalseOptOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept(
					"VerifyFalseSubOperations", metaOperationSubAction,
					operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"MUTIVERIFICATION");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);

			refas.getVariabilityVertex().put("VerifyFalseSubOperations",
					instOperationSubAction);

			instOperationSubAction.getInstAttribute("Index").setValue(1);

			instOperationSubAction.getInstAttribute("Index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-false", instEdgeOper);
			instEdgeOper.setIdentifier("ver-false");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			verifFalseOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifFalseOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			verifFalseOptOperSubActionRelaxable = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifFalseOptOperSubActionRelaxable);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "RELAXABLE");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			verifFalseOptOperSubActionVerification = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifFalseOptOperSubActionVerification);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "VERIFICATION");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Ver-false-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			instLabeling.getInstAttribute("order").setValue(true);

			refas.getVariabilityVertex().put("Ver-false-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-false-lab", instEdgeOper);
			instEdgeOper.setIdentifier("ver-false-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			instOperationGroup = new InstConcept("Configuration",
					metaOperationMenu, operationMenu);
			refas.getVariabilityVertex().put("Configuration",
					instOperationGroup);

			instOperationGroup.getInstAttribute("visible").setValue(false);
			instOperationGroup.getInstAttribute("menuType").setValue("4");
			instOperationGroup.getInstAttribute("name").setValue(
					"Configuration");
			instOperationGroup.getInstAttribute("shortcut").setValue("C");

			configTemporalOperationAction = new OpersConcept(
					"ConfigureTemporalOper");

			instOperationAction = new InstConcept("ConfigureTemporalOper",
					metaOperationAction, configTemporalOperationAction);
			refas.getVariabilityVertex().put("ConfigureTemporalOper",
					instOperationAction);

			instOperationAction.getInstAttribute("name").setValue(
					"Configure Temporal Operation");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-menu-conft", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-conft");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			operationSubAction = new OpersSubOperation(1,
					"ConfigureTemporalSubOper");
			// operationSubAction.addOperationLabeling(new
			// OperationLabeling("unique",
			// "L1", 1, false, null, null));
			// configTemporalOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept(
					"ConfigureTemporalSubOper", metaOperationSubAction,
					operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"SINGLEUPDATE");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);

			refas.getVariabilityVertex().put("ConfigureTemporalSubOper",
					instOperationSubAction);

			instOperationSubAction.getInstAttribute("Index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("conf-temp", instEdgeOper);
			instEdgeOper.setIdentifier("conf-temp");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			configTemporalOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, configTemporalOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Conf-temp-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("Conf-temp-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("conf-temp-lab", instEdgeOper);
			instEdgeOper.setIdentifier("conf-temp-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			configPermanentOperationAction = new OpersConcept(
					"ConfigurePermanentOper");

			instOperationAction = new InstConcept("ConfigurePermanentOper",
					metaOperationAction, configPermanentOperationAction);
			refas.getVariabilityVertex().put("ConfigurePermanentOper",
					instOperationAction);

			instOperationAction.getInstAttribute("name").setValue(
					"Configure Permanent Operation");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-menu-confp", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-confp");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			operationSubAction = new OpersSubOperation(1,
					"ConfigurePermanentSubOper");
			// configPermanentOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept(
					"ConfigurePermanentSubOper", metaOperationSubAction,
					operationSubAction);

			instOperationSubAction.getInstAttribute("type").setValue(
					"SINGLEUPDATE");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);

			refas.getVariabilityVertex().put("ConfigurePermanentSubOpe",
					instOperationSubAction);

			instOperationSubAction.getInstAttribute("Index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("conf-perm", instEdgeOper);
			instEdgeOper.setIdentifier("conf-perm");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			configPermanentOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, configPermanentOptOperSubActionNormal);

			instOperSubOperationExpType.addInstAttribute(
					"suboperexptype",
					metaExpType.getEdSyntaxEle().getAbstractAttribute(
							"suboperexptype", null, null), "NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			semanticExpressions = new ArrayList<OpersExpr>();

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Conf-perm-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("Conf-perm-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("conf-perm-lab", instEdgeOper);
			instEdgeOper.setIdentifier("conf-perm-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);
		}

		// END Operations definition
		// --------------------------------------------------------------

		// FIXED concept's definition

		OpersConcept semInfraMConcept = new OpersConcept("InfraMetaConcept");

		InstConcept instVertexIE = new InstConcept("InfraMetaConcept",
				infraMetaMetaConcept, semInfraMConcept);

		attribute = new ElemAttribute("True", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", "",
				true, 2, -1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("True", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}
		attribute = new ElemAttribute("False", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***NotSelected***", "",
				false, 2, -1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("False", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}
		ElemAttribute attributeSel = new ElemAttribute("Sel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", "",
				false, 2, -1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Sel", attributeSel);

		if (!empty) {
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraMConcept.getIdentifier(), attributeSel.getName(),
					true));
			simulOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semInfraMConcept.getIdentifier(), attributeSel.getName(),
					true));
			simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semInfraMConcept.getIdentifier(), attributeSel.getName(),
					true));
		}
		attribute = new ElemAttribute("Exclu", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Excluded***", "",
				false, 2, -1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Exclu", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			// simsceExecOperLabeling2
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			simulOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}
		attribute = new ElemAttribute("Description", "String",
				AttributeType.OPERATION, false, "Description", "", "", 0, -1,
				"", "", -1, "", "");

		attribute = new ElemAttribute("Active", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Is Active",
				"Ignored for operations", true, 0, -1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Active", attribute);

		attribute = new ElemAttribute("Visibility", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Is Visible", "", true, 0,
				-1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Visibility", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("Allowed", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Is Allowed", "", true, 0,
				-1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Allowed", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);

		attribute = new ElemAttribute("ConfSel", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Configuration Selected",
				"Manually/Implication selected for this configuration", false,
				2, -1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("ConfSel", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			// simsceExecOperLabeling2
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			simulOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("ConfNotSel", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Configuration Not Selected",
				"Manually/Implication not selected for this configuration",
				false, 2, -1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("ConfNotSel", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Proh", "Boolean",
				AttributeType.OPERATION, true, "Prohibited",
				"Manually prohibited (exluded) by design", false, 0, -1, "",
				"", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Proh", attribute);
		semInfraMConcept.addPropVisibleAttribute("08#" + "Proh");
		semInfraMConcept.addPropEditableAttribute("08#" + "Proh");

		if (!empty) {
			simulOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Required", "Boolean",
				AttributeType.OPERATION, true, "Is Required",
				"Manually defined as required", false, 2, -1, "", "", -1, "",
				"");

		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		semInfraMConcept.putSemanticAttribute("Required", attribute);

		if (!empty) {
			simulOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Core", "Boolean",
				AttributeType.OPERATION, false, "Is a Core Concept",
				"Core element defined by the core update operation", false, 2,
				-1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Core", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Dead", "Boolean",
				AttributeType.OPERATION, false, "Is a Dead Concept",
				"Dead element defined by core update operation", false, 2, -1,
				"", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Dead", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("NReqSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected by simulation",
				"Selected for this solution (with or without constraint)",
				false, 0, -1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("NReqSel", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("NNotSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Not selected(inactive)", "", false, 0, -1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("NNotSel", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addOutVariable(attribute);

		semInfraMConcept.addPropVisibleAttribute("01#" + "Sel");
		semInfraMConcept.addPropVisibleAttribute("05#" + "NReqSel");

		semInfraMConcept.addPropVisibleAttribute("02#" + "Exclu");
		semInfraMConcept.addPropVisibleAttribute("04#" + "NNotSel");

		semInfraMConcept.addPropVisibleAttribute("07#" + "Core");
		semInfraMConcept.addPropVisibleAttribute("08#" + "Dead");
		semInfraMConcept.addPropEditableAttribute("04#" + "Required");
		semInfraMConcept.addPropVisibleAttribute("04#" + "Required");

		attribute = new ElemAttribute("DBVis", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Visible on Dashboard",
				"Element displayed on simulation dashboard", true, 0, -1, "",
				"", -1, "", "");
		semInfraMConcept.putSemanticAttribute("DBVis", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("ExportOnConfig", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Include in XLS export",
				"Element exported in XLS solutions file", true, 0, -1, "", "",
				-1, "", "");
		semInfraMConcept.putSemanticAttribute("ExportOnConfig", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		semInfraMConcept.addPropEditableAttribute("15#" + "ConfSel" + "#"
				+ "Core" + "#==#" + "false" + "#" + "false");
		semInfraMConcept.addPropEditableAttribute("16#" + "ConfNotSel" + "#"
				+ "Dead" + "#==#" + "false" + "#" + "false");
		semInfraMConcept.addPropEditableAttribute("03#" + "DBVis");
		semInfraMConcept.addPropEditableAttribute("04#" + "ExportOnConfig");

		semInfraMConcept.addPropVisibleAttribute("01#" + "Active");
		semInfraMConcept.addPropVisibleAttribute("02#" + "Visibility");

		semInfraMConcept.addPropVisibleAttribute("03#" + "DBVis");
		semInfraMConcept.addPropVisibleAttribute("04#" + "ExportOnConfig");
		semInfraMConcept.addPropVisibleAttribute("15#" + "ConfSel" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		semInfraMConcept.addPropVisibleAttribute("16#" + "ConfNotSel" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");

		refas.getVariabilityVertex().put("InfraMetaConcept", instVertexIE);

		OpersConcept semInfraOTRel = new OpersConcept("InfraMetaOTRel");

		/*
		 * semGeneralGroup.putSemanticAttribute("Sel", new ElemAttribute("Sel",
		 * "Boolean", AttributeType.EXECCURRENTSTATE, false, "***Selected***",
		 * false, 2, -1, "", "", -1, "", "")); semGeneralGroup
		 * .putSemanticAttribute("Exclu", new ElemAttribute("Exclu", "Boolean",
		 * AttributeType.EXECCURRENTSTATE, false, "***Not Avaliable***", false,
		 * 2, -1, "", "", -1, "", ""));
		 */
		InstConcept instVertexGR = new InstConcept("InfraMetaOTRel",
				infraMetaMetaOverTwoRelation, semInfraOTRel);

		refas.getVariabilityVertex().put("InfraMetaOTRel", instVertexGR);

		attribute = new ElemAttribute("True", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", "",
				true, 2, -1, "", "", -1, "", "");
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semInfraOTRel.getIdentifier(), attribute.getName(), true));
		semInfraOTRel.putSemanticAttribute("True", attribute);

		if (!empty) {
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("False", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***NotSelected***", "",
				false, 2, -1, "", "", -1, "", "");
		semInfraOTRel.putSemanticAttribute("False", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Sel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***",
				"Element selected for this solution (green)", false, 2, -1, "",
				"", -1, "", "");

		semInfraOTRel.putSemanticAttribute("Sel", attribute);
		if (!empty) {
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Core", "Boolean",
				AttributeType.OPERATION, false, "Is a Core Concept",
				"Core element defined by the core update operation", false, 2,
				-1, "", "", -1, "", "");

		if (!empty) {
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
		}
		semInfraOTRel.putSemanticAttribute("Core", attribute);

		if (!empty) {
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Exclu", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Excluded***",
				"Element excluded for this solution (red)", false, 2, -1, "",
				"", -1, "", "");
		if (!empty) {
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
		}
		semInfraOTRel.putSemanticAttribute("Exclu", attribute);

		if (!empty) {
			simulOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Description", "String",
				AttributeType.OPERATION, false, "Description",
				"Element description", "", 0, -1, "", "", -1, "", "");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		semInfraOTRel.putSemanticAttribute("Description", attribute);

		semInfraOTRel.putSemanticAttribute("relationType", new ElemAttribute(
				"relationType", "Class", AttributeType.OPERATION, true,
				"Relation Type", "Type of over two relation",
				OpersRelType.class.getCanonicalName(), null, null, 0, 6, "",
				"", 6, "", ""));
		semInfraOTRel.addPropEditableAttribute("06#" + "relationType");
		semInfraOTRel.addPropVisibleAttribute("06#" + "relationType");
		semInfraOTRel.addPanelVisibleAttribute("06#" + "relationType");
		semInfraOTRel.addPanelSpacersAttribute("#" + "relationType" + "#");

		semInfraOTRel.putSemanticAttribute("LowRange", new ElemAttribute(
				"LowRange", "Integer", AttributeType.OPERATION, "Low Range",
				"Low value for range relation type", 1, false, new RangeDomain(
						0, 50), 0, 6, "", "", 6, "", ""));
		semInfraOTRel.addPropEditableAttribute("08#" + "LowRange");
		semInfraOTRel.addPropVisibleAttribute("08#" + "LowRange" + "#"
				+ "relationType" + "#==#" + "range" + "#" + "1");
		semInfraOTRel.addPanelVisibleAttribute("08#" + "LowRange" + "#"
				+ "relationType" + "#==#" + "range");
		semInfraOTRel.addPanelSpacersAttribute(" [#" + "LowRange" + "#");

		semInfraOTRel.putSemanticAttribute("HighRange", new ElemAttribute(
				"HighRange", "Integer", AttributeType.OPERATION, "High Range",
				"High value for range relation type", 1, false,
				new RangeDomain(0, 50), 0, 6, "", "", 6, "", ""));
		semInfraOTRel.addPropEditableAttribute("09#" + "HighRange");
		semInfraOTRel.addPropVisibleAttribute("09#" + "HighRange" + "#"
				+ "relationType" + "#==#" + "range" + "#" + "1");
		semInfraOTRel.addPanelVisibleAttribute("09#" + "HighRange" + "#"
				+ "relationType" + "#==#" + "range");
		semInfraOTRel.addPanelSpacersAttribute("-#" + "HighRange" + "#]");

		OpersConcept semGeneralPair = new OpersConcept("InfraMPWRel");
		InstConcept instInfraPair = new InstConcept("InfraMPWRel",
				infraMetaMetaPairwiseRelation, semGeneralPair);

		semGeneralPair.putSemanticAttribute("relationType", new ElemAttribute(
				"relationType", "Class", AttributeType.OPERATION, true,
				"Relation Type", "Type of pairwise relation",
				OpersRelType.class.getCanonicalName(), null, "", 0, 6, "", "",
				6, "#-#\n", ""));
		semGeneralPair.addPropEditableAttribute("06#" + "relationType");
		semGeneralPair.addPropVisibleAttribute("06#" + "relationType");
		semGeneralPair.addPanelVisibleAttribute("06#" + "relationType");
		semGeneralPair.addPanelSpacersAttribute("#" + "relationType" + "#\n");

		refas.getVariabilityVertex().put("InfraMPWRel", instInfraPair);

		OpersVariable semVariable = new OpersVariable("Variable");

		simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), "Exclu", true));

		ArrayList<OpersExpr> semanticExpressions = new ArrayList<OpersExpr>();

		semVariable.setSemanticExpressions(semanticExpressions);

		InstConcept instVertexVAR = new InstConcept("Variable",
				infraMetaMetaConcept, semVariable);

		OpersExpr t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexVAR, instVertexVAR, "varConfValue",
				"value");

		OpersExpr t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexVAR, "varConfDom", "");

		t1 = new OpersExpr("varConfigVal=value=varConfigDomain", refas
				.getSemanticExpressionTypes().get("Implies"), t3, t1);

		// semanticExpressions.add(t1);
		// simulationExecOptOperSubActionNormal.addSemanticExpression(t1);

		attribute = new ElemAttribute("DBVis", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Visible on Dashboard", "",
				true, 0, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("DBVis", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("ExportOnConfig", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Export on Configuration",
				"", true, 0, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("ExportOnConfig", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("Scope", "Boolean",
				AttributeType.OPERATION, true, "Global Scope",
				"Global or Concern Level scope (Ignored for operations)", true,
				0, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("Scope", attribute);
		if (!empty) {
			// simulationExecOperUniqueLabeling.addAttribute(new
			// OpersIOAttribute(
			// semVariable.getIdentifier(), attribute.getName(), true));
			// simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
			// semVariable.getIdentifier(), attribute.getName(), true));
			// simulOperationSubAction.addInAttribute(new OpersIOAttribute(
			// semVariable.getIdentifier(), attribute.getName(), true));
			// simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
			// semVariable.getIdentifier(), attribute.getName(), true));
		}
		// TODO use scope

		attribute = new ElemAttribute(
				"ConcernLevel",
				"Class",
				AttributeType.OPERATION,
				false,
				"Concern Level",
				"Concern level of this element associated from the context view",
				InstConcept.class.getCanonicalName(), "CG", null, 0, -1, "",
				"", -1, "", "");
		semVariable.putSemanticAttribute("ConcernLevel", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);
		// TODO: use concern level

		attribute = new ElemAttribute("name", "String",
				AttributeType.OPERATION, false, "Name",
				"Name to identify the variable", "", 0, 1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("name", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute(
				"variableType",
				"Enumeration",
				AttributeType.OPERATION,
				true,
				"Variable Type",
				"Type of variable (Operations support: Boolean, Integer (positive), String and Enumeration)",
				VariableType.class.getCanonicalName(), "String", "", 0, 2, "",
				"", -1, "", "variableType" + "#!=#" + "Enumeration");
		semVariable.putSemanticAttribute("variableType", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("varDom", "String",
				AttributeType.OPERATION, false, "Variable Domain",
				"Defined domain (positive numbers) {n-m,o,p-r}", "0,1", 0, 3,
				"variableType" + "#==#" + "Integer", "variableType" + "#==#"
						+ "Integer", -1, "", "");
		semVariable.putSemanticAttribute("varDom", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("enumType", "Class",
				AttributeType.OPERATION, false, "Enumeration",
				"Enumeration type from the context view",
				InstConcept.class.getCanonicalName(), "ME", "String", "", 0, 4,
				"variableType" + "#==#" + "Enumeration", "variableType"
						+ "#==#" + "Enumeration", -1, "", "");
		semVariable.putSemanticAttribute("enumType", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		// TODO define domain for enumtype
		attribute = new ElemAttribute("value", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "Value",
				"Variable current value (defined by an operation execution)",
				0, 1, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("value", attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("isContext", "Boolean",
				AttributeType.OPERATION, false, "Context Defined",
				"(Ignored for operations)", false, 0, 5, "", "", -1, "", "");
		semVariable.putSemanticAttribute("isContext", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("ExtVisible", "Boolean",
				AttributeType.OPERATION, false, "Externally Visible",
				"(Ignored by operations)", false, 0, 8, "", "", -1, "", "");
		semVariable.putSemanticAttribute("ExtVisible", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("ExtControl", "Boolean",
				AttributeType.OPERATION, false, "Externally Controlled",
				"(Ignored by operations)", false, 0, 9, "", "", -1, "", "");
		semVariable.putSemanticAttribute("ExtControl", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("varConfValue", "Integer",
				AttributeType.GLOBALCONFIG, false, "Configured Value",
				"Configured value (positive numbers)"
						+ " (not used by dynamic operations)", 0, 0, -1, "",
				"", -1, "", "");
		semVariable.putSemanticAttribute("varConfValue", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("varConfDom", "String",
				AttributeType.GLOBALCONFIG, false, "Configured Domain",
				"Configured domain (positive numbers) {n-m,o,p-r}"
						+ " (not used by dynamic operations)", "", 0, 1,
				"variableType" + "#==#" + "Integer" + "||" + "variableType"
						+ "#==#" + "Enumeration" + "||" + "variableType"
						+ "#==#" + "Boolean", "", -1, "", "");
		semVariable.putSemanticAttribute("varConfDom", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulationExecOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// simsceExecOperLabeling2.addAttribute(attribute);
		// simsceExecOperLabeling2.addAttribute(new
		// OpersIOAttribute(semVariable
		// .getIdentifier(), attribute.getName(), true));
		// simsceExecOperLabeling1.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);

		semVariable.addPropEditableAttribute("01#" + "name");
		semVariable.addPropEditableAttribute("02#" + "variableType");
		semVariable.addPropEditableAttribute("03#" + "varDom");
		semVariable.addPropEditableAttribute("04#" + "enumType");
		semVariable.addPropEditableAttribute("05#" + "isContext");

		semVariable.addPropEditableAttribute("08#" + "ExtVisible");
		semVariable.addPropEditableAttribute("09#" + "ExtControl");

		semVariable.addPropEditableAttribute("01#" + "varConfDom");

		semVariable.addPropVisibleAttribute("01#" + "name");
		semVariable.addPropVisibleAttribute("02#" + "variableType");
		semVariable.addPropVisibleAttribute("03#" + "varDom" + "#"
				+ "variableType" + "#==#" + "Integer");
		semVariable.addPropVisibleAttribute("04#" + "enumType" + "#"
				+ "variableType" + "#==#" + "Enumeration");
		semVariable.addPropVisibleAttribute("05#" + "isContext");

		semVariable.addPropVisibleAttribute("06#" + "value");
		semVariable.addPropVisibleAttribute("07#" + "value");
		semVariable.addPropVisibleAttribute("08#" + "ExtVisible");
		semVariable.addPropVisibleAttribute("09#" + "ExtControl");

		semVariable.addPropVisibleAttribute("01#" + "varConfDom" + "#"
				+ "variableType" + "#==#" + "Enumeration");
		semVariable.addPropVisibleAttribute("01#" + "varConfDom" + "#"
				+ "variableType" + "#==#" + "Integer");
		semVariable.addPropVisibleAttribute("01#" + "varConfDom" + "#"
				+ "variableType" + "#==#" + "Boolean");

		semVariable.addPanelVisibleAttribute("05#" + "variableType" + "#"
				+ "variableType" + "#!=#" + "Enumeration");
		semVariable.addPanelVisibleAttribute("06#" + "enumType" + "#"
				+ "variableType" + "#==#" + "Enumeration");
		semVariable.addPanelVisibleAttribute("07#" + "varDom" + "#"
				+ "variableType" + "#==#" + "Integer");
		semVariable.addPanelSpacersAttribute("{#" + "variableType" + "#} ");

		semVariable.addPanelSpacersAttribute("{#" + "varDom" + "#} ");

		semVariable.addPropEditableAttribute("03#" + "DBVis");
		semVariable.addPropEditableAttribute("04#" + "ExportOnConfig");
		semVariable.addPropVisibleAttribute("03#" + "DBVis");
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

		OpersElement semContextGroup = new OpersElement("ConcernLevel");

		semContextGroup.putSemanticAttribute("name", new ElemAttribute("name",
				"String", AttributeType.OPERATION, false, "Group Name", "",
				"<<new>>", 0, 1, "", "", 1, "", ""));
		semContextGroup.putSemanticAttribute("instances", new ElemAttribute(
				"instances", "Integer", AttributeType.OPERATION, false,
				"Number of Instances",
				"Instances of the concern level (Ignored for operations)", "1",
				0, 7, "", "", -1, "", ""));
		semContextGroup.putSemanticAttribute("ExtVisible", new ElemAttribute(
				"ExtVisible", "Boolean", AttributeType.OPERATION, false,
				"External Visible", "(Ignored for operations)", false, 0, 8,
				"", "", -1, "", ""));
		semContextGroup.putSemanticAttribute("ExtControl", new ElemAttribute(
				"ExtControl", "Boolean", AttributeType.OPERATION, false,
				"Externally Controlled", "(Ignored for operations)", false, 0,
				9, "", "", -1, "", ""));

		semContextGroup.addPropEditableAttribute("01#" + "name");
		semContextGroup.addPropEditableAttribute("07#" + "instances");
		semContextGroup.addPropEditableAttribute("08#" + "ExtVisible");
		semContextGroup.addPropEditableAttribute("09#" + "ExtControl");

		semContextGroup.addPropVisibleAttribute("01#" + "name");
		semContextGroup.addPropVisibleAttribute("07#" + "instances");
		semContextGroup.addPropVisibleAttribute("08#" + "ExtVisible");
		semContextGroup.addPropVisibleAttribute("09#" + "ExtControl");

		InstConcept instVertexCG = new InstConcept("ConcernLevel",
				infraMetaMetaConcept, semContextGroup);
		refas.getVariabilityVertex().put("ConcernLevel", instVertexCG);

		// Start Concept's definition
		// -------------------------------------------------------

		if (!empty) {
			OpersConcept semGeneralElement = new OpersConcept("GeneralElement");
			// From refas name depends all the static operations, do not change
			// it

			/*
			 * semGeneralElement.putSemanticAttribute("Sel", new
			 * ElemAttribute("Sel", "Boolean", AttributeType.EXECCURRENTSTATE,
			 * false, "***Selected***", false, 2, -1, "", "", -1, "", ""));
			 * semGeneralElement .putSemanticAttribute("Exclu", new
			 * ElemAttribute("Exclu", "Boolean", AttributeType.EXECCURRENTSTATE,
			 * false, "***Not Avaliable***", false, 2, -1, "", "", -1, "", ""));
			 */
			InstConcept instVertexGE = new InstConcept("GeneralElement",
					metaMetaConcept, semGeneralElement);

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
			// c = (ArrayList<InstAttribute>) ((InstAttribute)
			// instClaimSemOTAsso
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

			semanticExpressions = new ArrayList<OpersExpr>();

			semGeneralElement.setSemanticExpressions(semanticExpressions);

			t1 = new OpersExpr("Req Implies Selected", refas
					.getSemanticExpressionTypes().get("Implies"), instVertexGE,
					instVertexGE, "Required", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("NextPrefSel_=_0", refas
					.getSemanticExpressionTypes().get("Equals"), instVertexGE,
					"NPrefSel", true, 0);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("NextNotPrefSel_=_0", refas
					.getSemanticExpressionTypes().get("Equals"), instVertexGE,
					"NNotPrefSel", true, 0);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("UserReq Implies Req", refas
					.getSemanticExpressionTypes().get("Implies"), instVertexGE,
					instVertexGE, "Required", "Sel");

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexGE, "HasParent", true, 1);

			OpersExpr t2 = new OpersExpr("2", refas
					.getSemanticExpressionTypes().get("NotEquals"),
					instVertexGE, "userId", "GeneralFeature");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"NotEquals"), instVertexGE, "userId", "LeafFeature");

			t3 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"And"), t2, t3);

			t1 = new OpersExpr("NoLFet & NoGFet Implies hasParent", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), instVertexGE, instVertexGE, "NReqSel", "ConfSel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Product"), instVertexGE, "NPrefSel", true, t1);

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Sum"), instVertexGE, instVertexGE, "NPrefSel", "ConfSel");

			t3 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"Product"), instVertexGE, "NReqSel", true, t3);

			t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"Sum"), t1, t3);

			t1 = new OpersExpr("Opt...", refas.getSemanticExpressionTypes()
					.get("Equals"), instVertexGE, "Opt", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t2 = new OpersExpr("Opt =0", refas.getSemanticExpressionTypes()
					.get("Equals"), instVertexGE, "Opt", true, 0);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t2);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t2);

			semanticExpressions.add(t2);

			t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"Sum"), instVertexGE, instVertexGE, "ConfSel", "NReqSel");

			t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get(
					"Sum"), instVertexGE, "Core", true, t1);

			t1 = new OpersExpr("Core+ConfigSel+NextReqSel <=1", refas
					.getSemanticExpressionTypes().get("LessOrEquals"), 1,
					false, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("4", refas.getSemanticExpressionTypes()
					.get("Or"), instVertexGE, instVertexGE, "ConfNotSel",
					"NNotPrefSel");

			t1 = new OpersExpr("5", refas.getSemanticExpressionTypes()
					.get("Or"), instVertexGE, "Proh", true, t1);

			t1 = new OpersExpr("6", refas.getSemanticExpressionTypes()
					.get("Or"), instVertexGE, "Dead", true, t1);

			t1 = new OpersExpr("NotAvail (Dead Or Prohibit Or NotSelec)", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					instVertexGE, "Exclu", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("5", refas.getSemanticExpressionTypes()
					.get("Or"), instVertexGE, instVertexGE, "NReqSel",
					"NPrefSel");

			t2 = new OpersExpr("5", refas.getSemanticExpressionTypes()
					.get("Or"), instVertexGE, instVertexGE, "Core", "ConfSel");

			t1 = new OpersExpr("5", refas.getSemanticExpressionTypes()
					.get("Or"), t1, t2);

			t1 = new OpersExpr("Selected (Core, NextReqSel, NextPrefSel)",
					refas.getSemanticExpressionTypes().get("DoubleImplies"),
					instVertexGE, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get(
					"Product"), instVertexGE, instVertexGE, "Sel", "Exclu");

			t1 = new OpersExpr("Selected+NotAvail <=1", refas
					.getSemanticExpressionTypes().get("Equals"), 0, false, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			refas.getVariabilityVertex().put("GeneralElement", instVertexGE);

			InstPairwiseRel instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("getobe", instEdge);
			instEdge.setIdentifier("getobe");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexIE, true);
			instEdge.setSourceRelation(instVertexGE, true);

			// Design attributes: Do not change identifiers

			// simulationExecOperUniqueLabeling.addAttribute(attribute);
			semGeneralElement.putSemanticAttribute("Description", attribute);

			attribute = new ElemAttribute("Scope", "Boolean",
					AttributeType.OPERATION, true, "Global Scope", "", true, 0,
					-1, "", "", -1, "", "");
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			semGeneralElement.putSemanticAttribute("Scope", attribute);
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			// TODO use scope

			attribute = new ElemAttribute("ConcernLevel", "Class",
					AttributeType.OPERATION, false, "Concern Level",
					"Concern Level of the element (Ignored for operations)",
					InstConcept.class.getCanonicalName(), "CG", null, 2, -1,
					"", "", -1, "", "");
			semGeneralElement.putSemanticAttribute("ConcernLevel", attribute);
			// simulationExecOperUniqueLabeling.addAttribute(attribute);
			// simulOperationSubAction.addInVariable(attribute);
			// TODO: use concern level

			semGeneralElement.addPropEditableAttribute("05#" + "Scope");
			semGeneralElement.addPropEditableAttribute("06#" + "ConcernLevel"
					+ "#" + "Scope" + "#==#" + "false" + "#" + "");

			semGeneralElement.addPropEditableAttribute("08#"
					+ "IgnoreForSimulation");
			semGeneralElement.addPropVisibleAttribute("05#" + "Scope");
			semGeneralElement.addPropVisibleAttribute("06#" + "ConcernLevel"
					+ "#" + "Scope" + "#==#" + "false" + "#" + "");

			semGeneralElement.addPanelVisibleAttribute("00#" + "ConcernLevel"
					+ "#" + "Scope" + "#==#" + "false");
			semGeneralElement.addPanelSpacersAttribute("<<#" + "ConcernLevel"
					+ "#>>\n");

			// Configuration attributes: do no change identifiers
			/*
			 * attribute = new ElemAttribute("ReqLev", "Integer",
			 * AttributeType.OPERATION, "Required Level", "", 0, false, new
			 * RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
			 * semGeneralElement.putSemanticAttribute("ReqLev", attribute); //
			 * simulationExecOperUniqueLabeling.addAttribute(attribute); //
			 * simulOperationSubAction.addOutVariable(attribute); // TODO define
			 * domain or Enum Level
			 * 
			 * semGeneralElement.addPropVisibleAttribute("05#" + "ReqLev" + "#"
			 * + "Core" + "#==#" + "true");
			 * 
			 * // Simulation attributes: do not modify identifiers
			 * 
			 * attribute = new ElemAttribute("IniReqLev", "Integer",
			 * AttributeType.EXECCURRENTSTATE, false, "Initial Required Level",
			 * "", 0, new RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
			 * semGeneralElement.putSemanticAttribute("IniReqLev", attribute);
			 */

			// simulationExecOperUniqueLabeling.addAttribute(attribute);
			// simulationOperationAction.addInVariable(attribute);
			/*
			 * attribute = new ElemAttribute("SimReqLev", "Integer",
			 * AttributeType.EXECCURRENTSTATE, false, "Required Level", "", 0,
			 * new RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
			 */
			// semGeneralElement.putSemanticAttribute("SimReqLev",
			// attribute);
			// simulationExecOperUniqueLabeling.addAttribute(attribute);

			attribute = new ElemAttribute("HasParent", "Boolean",
					AttributeType.EXECCURRENTSTATE, false, "Has Parent", "",
					true, 0, -1, "", "", -1, "", "");
			semGeneralElement.putSemanticAttribute("HasParent", attribute);
			// TODO add to verification
			// simulationExecOperUniqueLabeling.addAttribute(attribute);
			// simulOperationSubAction.addOutVariable(attribute);

			attribute = new ElemAttribute("Opt", "Integer",
					AttributeType.EXECCURRENTSTATE, false, "FilterVariable",
					"", 0, new RangeDomain(0, 20), 0, -1, "", "", -1, "", "");
			semGeneralElement.putSemanticAttribute("Opt", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));

			attribute = new ElemAttribute("Order", "Integer",
					AttributeType.EXECCURRENTSTATE, false, "SortVariable", "",
					0, new RangeDomain(0, 40), 0, -1, "", "", -1, "", "");
			semGeneralElement.putSemanticAttribute("Order", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			// simulOperationSubAction.addInVariable(attribute);

			attribute = new ElemAttribute("NPrefSel", "Boolean",
					AttributeType.EXECCURRENTSTATE, false,
					"Selected by configuration", "", false, 0, -1, "", "", -1,
					"", "");
			semGeneralElement.putSemanticAttribute("NPrefSel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));

			attribute = new ElemAttribute("NNotPrefSel", "Boolean",
					AttributeType.EXECCURRENTSTATE, false,
					"Not Selected by configuration", "", false, 0, -1, "", "",
					-1, "", "");
			semGeneralElement.putSemanticAttribute("NNotPrefSel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));

			semGeneralElement.addPropVisibleAttribute("06#" + "NNotPrefSel");
			semGeneralElement.addPropVisibleAttribute("03#" + "NPrefSel");

			OpersConcept semHardConcept = new OpersConcept("semHardConcept");

			attribute = new ElemAttribute("satType", "Enumeration",
					AttributeType.OPERATION, false, "satType", "",
					"com.variamos.dynsup.statictypes.SatisfactionType",
					"achieve", "", 0, -1, "", "", -1, "", "");
			semHardConcept.putSemanticAttribute("satType", attribute);
			// simulationExecOperUniqueLabeling.addAttribute(attribute);

			semHardConcept.addPropEditableAttribute("01#" + "satType");
			semHardConcept.addPropVisibleAttribute("01#" + "satType");

			InstConcept instVertexHC = new InstConcept("HardConcept",
					metaMetaConcept, semHardConcept);
			refas.getVariabilityVertex().put("HardConcept", instVertexHC);

			semanticExpressions = new ArrayList<OpersExpr>();

			semHardConcept.setSemanticExpressions(semanticExpressions);

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Product"), instVertexGE, "NReqSel", true, 4);

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Sum"), t1, 0);

			t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"Sum"), instVertexGE, "NPrefSel", true, t1);

			t1 = new OpersExpr("Order...", refas.getSemanticExpressionTypes()
					.get("Equals"), instVertexGE, "Order", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("hctoge", instEdge);
			instEdge.setIdentifier("hctoge");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexGE, true);
			instEdge.setSourceRelation(instVertexHC, true);

			// Feature concepts

			OpersConcept semFeature = new OpersConcept("Feature");
			InstConcept instVertexF = new InstConcept("Feature",
					metaMetaConcept, semFeature);

			attribute = new ElemAttribute("IsRootFeature", "Boolean",
					AttributeType.OPERATION, true, "Is a Root Feature Concept",
					"", false, 2, -1, "", "", -1, "", "");
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semFeature.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semFeature.getIdentifier(), attribute.getName(), true));
			semFeature.putSemanticAttribute("IsRootFeature", attribute);
			simulOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semFeature.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semFeature.getIdentifier(), attribute.getName(), true));

			semanticExpressions = new ArrayList<OpersExpr>();

			semFeature.setSemanticExpressions(semanticExpressions);

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Product"), instVertexGE, "NReqSel", true, 4);

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Sum"), t1, 0);

			t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"Sum"), instVertexGE, "NPrefSel", true, t1);

			t1 = new OpersExpr("Order...", refas.getSemanticExpressionTypes()
					.get("Equals"), instVertexGE, "Order", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexGE, "IsRootFeature", true, 1);

			OpersExpr t2_1 = new OpersExpr("2-1", refas
					.getSemanticExpressionTypes().get("Equals"), instVertexGE,
					"userId", "mandatory");

			OpersExpr t2_2 = new OpersExpr("2-2", refas
					.getSemanticExpressionTypes().get("Equals"), instVertexGE,
					"userId", "optional");

			t2 = new OpersExpr("2", refas.getSemanticExpressionTypes()
					.get("Or"), t2_1, t2_2);

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), 0, true, t2);

			t3 = new OpersExpr("3-", refas.getSemanticExpressionTypes().get(
					"Equals"), 0, false, t3);

			t1 = new OpersExpr("IsRootFeature=...", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			// t1 = new SemanticExpression("1",
			// refas.getSemanticExpressionTypes().get(
			// "Equals"), instVertexGE, "IsRootFeature", 0);
			//
			// t3 = new SemanticExpression("3",
			// refas.getSemanticExpressionTypes().get(
			// "NotEquals"), instVertexGE, "userId", "Feature");
			//
			// t1 = new SemanticExpression("Not Feat Implies NoRoot", refas
			// .getSemanticExpressionTypes().get("Implies"), t3, t1);
			//
			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

			// semanticExpressions.add(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexGE, "IsRootFeature", true, 1);

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexGE, "Sel", true, 1);

			t1 = new OpersExpr("Root Implies Req", refas
					.getSemanticExpressionTypes().get("Implies"), t1, t3);

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexGE, "IsRootFeature", true, 1);

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexGE, "Sel", true, 1);

			t1 = new OpersExpr("Root Implies Selected", refas
					.getSemanticExpressionTypes().get("Implies"), t1, t3);

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			refas.getVariabilityVertex().put("Feature", instVertexF);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ftoge", instEdge);
			instEdge.setIdentifier("ftoge");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexGE, true);
			instEdge.setSourceRelation(instVertexF, true);

			/*
			 * instEdge = new InstPairwiseRel();
			 * refas.getConstraintInstEdges().put("ovartovar", instEdge);
			 * instEdge.setIdentifier("ovartovar");
			 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			 * instEdge.setTargetRelation(instVertexVAR, true);
			 * instEdge.setSourceRelation(instVertexF, true);
			 */
			// definition of other concepts

			OpersConcept semAssumption = new OpersConcept("Assumption");
			InstConcept instVertexAS = new InstConcept("Assumption",
					metaMetaConcept, semAssumption);
			refas.getVariabilityVertex().put("Assumption", instVertexAS);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("assutoge", instEdge);
			instEdge.setIdentifier("assutoge");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexHC, true);
			instEdge.setSourceRelation(instVertexAS, true);

			OpersConcept semGoal = new OpersConcept("Goal");
			semGoal.addPanelVisibleAttribute("01#" + "satType");
			semGoal.addPanelSpacersAttribute("<#" + "satType" + "#>\n");
			InstConcept instVertexG = new InstConcept("Goal", metaMetaConcept,
					semGoal);
			refas.getVariabilityVertex().put("Goal", instVertexG);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("gtoge", instEdge);
			instEdge.setIdentifier("gtoge");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexHC, true);
			instEdge.setSourceRelation(instVertexG, true);

			OpersConcept semOperationalization = new OpersConcept(
					"Operationalization");

			attribute = new ElemAttribute("attributeValue", "Set",
					AttributeType.SYNTAX, false, "values", "",
					InstAttribute.class.getCanonicalName(),
					new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "", "");
			semOperationalization.putSemanticAttribute("attributeValue",
					attribute);
			// simulationExecOperUniqueLabeling.addAttribute(attribute);

			semOperationalization.addPropVisibleAttribute("06#"
					+ "attributeValue");
			semOperationalization.addPropEditableAttribute("06#"
					+ "attributeValue");

			simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(
					semOperationalization.getIdentifier(), "Sel", true));

			InstConcept instVertexOper = new InstConcept("Operationalization",
					metaMetaConcept, semOperationalization);
			refas.getVariabilityVertex().put("Operationalization",
					instVertexOper);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("opertoge", instEdge);
			instEdge.setIdentifier("opertoge");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexHC, true);
			instEdge.setSourceRelation(instVertexOper, true);

			OpersConcept semSoftgoal = new OpersConcept("Softgoal");

			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), "Sel", false));

			StringDomain d = new StringDomain();
			d.add("low");
			d.add("high");
			d.add("close");
			attribute = new ElemAttribute("satisficingLevel", "String",
					AttributeType.OPERATION, "Satisficing Level",
					"Satisficing for dynamic operations (low/high/close)",
					"high", false, d, 0, 10, "", "", -1, "", "");

			semSoftgoal.putSemanticAttribute("satisficingLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			semSoftgoal.addPropEditableAttribute("11#" + "satisficingLevel");
			semSoftgoal.addPropVisibleAttribute("11#" + "satisficingLevel");

			attribute = new ElemAttribute("satisficingType", "Enumeration",
					AttributeType.OPERATION, false, "Satisficing Type",
					"Satisficing for static operations",
					SatisficingType.class.getCanonicalName(),
					"Achieve as close as possible", "", 0, 10, "", "", -1, "",
					"");
			semSoftgoal.putSemanticAttribute("satisficingType", attribute);

			attribute = new ElemAttribute("ConfigReqLevel", "Integer",
					AttributeType.OPERATION, "Config Req Level (5=ignored)",
					"SG required level (defined: 0..4 ignored: 5) ", 5, false,
					new RangeDomain(0, 5), 0, 5, "Required" + "#==#" + "true"
							+ "#" + "0", "", -1, "", "");
			semSoftgoal.putSemanticAttribute("ConfigReqLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));

			semSoftgoal.addPropEditableAttribute("10#" + "satisficingType");
			semSoftgoal.addPropEditableAttribute("05#" + "ConfigReqLevel" + "#"
					+ "Required" + "#==#" + "true" + "#" + "5");

			semSoftgoal.addPropVisibleAttribute("10#" + "satisficingType");
			semSoftgoal.addPropVisibleAttribute("05#" + "ConfigReqLevel");

			semanticExpressions = new ArrayList<OpersExpr>();

			semSoftgoal.setSemanticExpressions(semanticExpressions);

			InstConcept instVertexSG = new InstConcept("Softgoal",
					metaMetaConcept, semSoftgoal);

			t3 = new OpersExpr("22", refas.getSemanticExpressionTypes().get(
					"NotEquals"), instVertexSG, "ConfigReqLevel", true, 5);

			t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, "satisficingLevel", "close");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t3);

			t1 = new OpersExpr("26", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, instVertexSG, "ConfigReqLevel",
					"SDReqLevel");

			t1 = new OpersExpr("ConfReqLev...", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t3 = new OpersExpr("23", refas.getSemanticExpressionTypes().get(
					"NotEquals"), instVertexSG, "ConfigReqLevel", true, 5);

			t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, "satisficingLevel", "low");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t3);

			t1 = new OpersExpr("21", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"), instVertexSG, instVertexSG,
					"ConfigReqLevel", "SDReqLevel");

			t1 = new OpersExpr("ConfReqLev...", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t3 = new OpersExpr("25", refas.getSemanticExpressionTypes().get(
					"NotEquals"), instVertexSG, "ConfigReqLevel", true, 5);

			t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, "satisficingLevel", "high");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t3);

			t1 = new OpersExpr("20", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"), instVertexSG, instVertexSG,
					"ConfigReqLevel", "SDReqLevel");

			t1 = new OpersExpr("ConfReqLev...", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Subtraction"), instVertexGE, "Sel", false, 1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Product"), 8, true, t1);

			t3 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Product"), instVertexGE, "NReqSel", true, 4);

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Sum"), t1, t3);

			t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"Sum"), instVertexGE, "NPrefSel", true, t1);

			t1 = new OpersExpr("Order...", refas.getSemanticExpressionTypes()
					.get("Equals"), instVertexGE, "Order", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"), instVertexSG, instVertexSG, "SDReqLevel",
					"ClaimExpLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"DoubleImplies"), instVertexSG, "Sel", true, t1);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, "satisficingLevel", "high");

			t1 = new OpersExpr("high: SDReqLevel<=ClaimExpLevel...", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"), instVertexSG, instVertexSG, "SDReqLevel",
					"ClaimExpLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"DoubleImplies"), instVertexSG, "Sel", true, t1);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, "satisficingLevel", "low");

			t1 = new OpersExpr("low: SDReqLevel>=ClaimExpLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, instVertexSG, "SDReqLevel",
					"ClaimExpLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"DoubleImplies"), instVertexSG, "Sel", true, t1);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, "satisficingLevel", "close");

			t1 = new OpersExpr("close: SDReqLevel=ClaimExpLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			refas.getVariabilityVertex().put("Softgoal", instVertexSG);

			attribute = new ElemAttribute("SDReqLevel", "Integer",
					AttributeType.EXECCURRENTSTATE, false,
					"Required Level by SD",
					"Required level (0..4) for the soft dependency relation",
					0, new RangeDomain(0, 4), 2, -1, "", "", -1, "", "");
			semSoftgoal.putSemanticAttribute("SDReqLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));

			attribute = new ElemAttribute("ClaimExpLevel", "Integer",
					AttributeType.EXECCURRENTSTATE, false,
					"Expected Level by Claim",
					"Expected level (0..4) for the claim relation", 0,
					new RangeDomain(0, 4), 2, -1, "", "", -1, "", "");
			semSoftgoal.putSemanticAttribute("ClaimExpLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simulOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));

			semSoftgoal.addPropVisibleAttribute("16#" + "SDReqLevel");
			semSoftgoal.addPropVisibleAttribute("16#" + "ClaimExpLevel");

			semSoftgoal.addPropEditableAttribute("18#" + "SDReqLevel");
			semSoftgoal.addPropEditableAttribute("18#" + "ClaimExpLevel");

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sgtoge", instEdge);
			instEdge.setIdentifier("sgtoge");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexGE, true);
			instEdge.setSourceRelation(instVertexSG, true);

			OpersConcept semAsset = new OpersConcept("Asset");
			InstConcept instVertexAsset = new InstConcept("Asset",
					metaMetaConcept, semAsset);
			refas.getVariabilityVertex().put("Asset", instVertexAsset);

			semanticExpressions = new ArrayList<OpersExpr>();

			semAsset.setSemanticExpressions(semanticExpressions);

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Product"), instVertexGE, "NReqSel", true, 4);

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Sum"), t1, 0);

			t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"Sum"), instVertexGE, "NPrefSel", true, t1);

			t1 = new OpersExpr("Order...", refas.getSemanticExpressionTypes()
					.get("Equals"), instVertexGE, "Order", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("astoge", instEdge);
			instEdge.setIdentifier("astoge");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexGE, true);
			instEdge.setSourceRelation(instVertexAsset, true);

			List<OpersRelType> claimSemOverTwoRelList = new ArrayList<OpersRelType>();
			claimSemOverTwoRelList.add(new OpersRelType("and", "And", "And",
					false, false, false, 2, -1, 1, 1));
			claimSemOverTwoRelList.add(new OpersRelType("or", "Or", "Or",
					false, true, true, 2, -1, 1, 1));
			claimSemOverTwoRelList.add(new OpersRelType("mutex", "Mutex",
					"Mutex.", false, true, true, 2, -1, 1, 1));

			OpersOverTwoRel semClaim = new OpersOverTwoRel("Claim",
					claimSemOverTwoRelList);
			InstConcept instVertexCL = new InstConcept("Claim", semClaim,
					metaMetaInstOverTwoRel);

			semanticExpressions = new ArrayList<OpersExpr>();

			semClaim.setSemanticExpressions(semanticExpressions);

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Product"), instVertexGE, "NReqSel", true, 4);

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Sum"), t1, 0);

			t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"Sum"), instVertexGE, "NPrefSel", true, t1);

			t1 = new OpersExpr("Order...", refas.getSemanticExpressionTypes()
					.get("Equals"), instVertexGE, "Order", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			List<OpersRelType> operclaimPairwiseRelList = new ArrayList<OpersRelType>();
			operclaimPairwiseRelList.add(new OpersRelType("OperToClaim", "",
					"", true, true, true, 1, 1, 1, 1));

			OpersPairwiseRel directOperClaimSemanticEdge = new OpersPairwiseRel(
					"OperClaimPWAsso", true, operclaimPairwiseRelList);

			InstConcept instDirOperClaimSemanticEdge = new InstConcept(
					"OperClaimPWAsso", metaMetaPairwiseRelation,
					directOperClaimSemanticEdge);

			refas.getVariabilityVertex().put("OperClaimPWAsso",
					instDirOperClaimSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("opctoip", instEdge);
			instEdge.setIdentifier("opctoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirOperClaimSemanticEdge, true);

			instDirOperClaimSemanticEdge.createInstAttributes();

			InstAttribute ia = instDirOperClaimSemanticEdge
					.getInstAttribute("relTypesAttr");
			List<InstAttribute> ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
					"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
					"OperToClaim#OperToClaim#true#true#true#1#-1#1#1"));

			ia = instDirOperClaimSemanticEdge.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexOper, instVertexCL, "Sel",
					"ConditionalExpression");

			t1 = new OpersExpr("OPERCLSelected", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instVertexCL, "Sel", true, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("OPERCLNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexOper, instVertexCL, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
					"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			// ia = instVertexCL.getInstAttribute("relTypesAttr");
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
			// ia = instVertexCL.getInstAttribute("opersExprs");
			// ias = (List<InstAttribute>) ia.getValue();
			//
			// semanticExpressions = new ArrayList<IntSemanticExpression>();
			//
			// t2 = new SemanticExpression("1",
			// refas.getSemanticExpressionTypes().get(
			// "Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
			// instVertexCL, instVertexOper, null, "Sel", "True", true);
			//
			// t1 = new SemanticExpression("1",
			// refas.getSemanticExpressionTypes().get(
			// "GreaterOrEq"), t2, ExpressionVertexType.RIGHTRELATIONCONCEPT,
			// instVertexCL, "LowRange");
			//
			// t2 = new SemanticExpression("1",
			// refas.getSemanticExpressionTypes().get(
			// "Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
			// instVertexCL, instDirOperClaimSemanticEdge, null, "Sel",
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
			// instVertexCL, "Sel", true, t1);
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
			// instVertexHC, "Sel", "Sel");
			//
			// t3 = new SemanticExpression("3",
			// refas.getSemanticExpressionTypes().get(
			// "Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
			// instVertexHC, "Sel");
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
			// instVertexHC, "Sel", "Sel");
			//
			// t3 = new SemanticExpression("3",
			// refas.getSemanticExpressionTypes().get(
			// "Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
			// instVertexHC, "Sel");
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

			semanticExpressions = new ArrayList<OpersExpr>();

			refas.getVariabilityVertex().put("Claim", instVertexCL);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("cltoge", instEdge);
			instEdge.setIdentifier("cltoge");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
			instEdge.setTargetRelation(instVertexGE, true);
			instEdge.setSourceRelation(instVertexCL, true);

			attribute = new ElemAttribute(
					"ConditionalExpression",
					ModelExpr.class.getCanonicalName(),
					AttributeType.OPERATION,
					false,
					"Conditional Expression",
					"Claim activation expression (in addition to operationalizations/left features)",
					null, 0, -1, "", "", -1, "", "");
			semClaim.putSemanticAttribute("ConditionalExpression", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semClaim.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semClaim
					.getIdentifier(), attribute.getName(), true));
			// simulOperationSubAction.addInVariable(attribute);

			attribute = new ElemAttribute("CompExp", "Boolean",
					AttributeType.GLOBALCONFIG, false,
					"Boolean Comp. Expression", "", true, 0, -1, "", "", -1,
					"", "");
			semClaim.putSemanticAttribute("CompExp", attribute);
			// simulationExecOperUniqueLabeling.addAttribute(attribute);

			attribute = new ElemAttribute("ConfidenceLevel", "Integer",
					AttributeType.OPERATION, "Confidence Level",
					"(Ignored for operations)", 1, false,
					new RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
			semClaim.putSemanticAttribute("ConfidenceLevel", attribute);
			// simulationExecOperUniqueLabeling.addAttribute(attribute);
			// simulOperationSubAction.addInVariable(attribute);

			/*
			 * attribute = new ElemAttribute("ClaimSelected", "Boolean",
			 * AttributeType.GLOBALCONFIG, false, "Claim Selected", "", false,
			 * 0, -1, "", "", -1, "", "");
			 * semClaim.putSemanticAttribute("ClaimSelected", attribute); //
			 * simulationExecOperUniqueLabeling.addAttribute(attribute);
			 */
			attribute = new ElemAttribute(
					"ClaimExpression",
					"String",
					AttributeType.OPERATION,
					false,
					"Claim Expression Text",
					"Textual representation of the conditional expression (only to display)",
					"", 0, -1, "", "", -1, "", "");
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

			// semClaim.addPropVisibleAttribute("02#" + "ClaimSelected");

			semClaim.addPanelVisibleAttribute("10#" + "ClaimExpression");

			// semClaim.addPanelSpacersAttribute("#" + "Operationalizations" +
			// "#\n#");

			OpersOverTwoRel semSoftDependency = new OpersOverTwoRel(
					"SoftDependency", null);
			InstConcept instVertexSD = new InstConcept("SoftDependency",
					semSoftDependency, metaMetaInstConcept);
			refas.getVariabilityVertex().put("SoftDependency", instVertexSD);

			semanticExpressions = new ArrayList<OpersExpr>();

			semSoftDependency.setSemanticExpressions(semanticExpressions);

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Product"), instVertexGE, "NReqSel", true, 4);

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Sum"), t1, 0);

			t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
					"Sum"), instVertexGE, "NPrefSel", true, t1);

			t1 = new OpersExpr("Order...", refas.getSemanticExpressionTypes()
					.get("Equals"), instVertexGE, "Order", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sdtoge", instEdge);
			instEdge.setIdentifier("sdtoge");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
			instEdge.setTargetRelation(instVertexGE, true);
			instEdge.setSourceRelation(instVertexSD, true);

			attribute = new ElemAttribute("ConditionalExpression",
					ModelExpr.class.getCanonicalName(),
					AttributeType.OPERATION, false, "Conditional Expression",
					"Soft dependency activation expression", null, 0, -1, "",
					"", -1, "", "");
			semSoftDependency.putSemanticAttribute("ConditionalExpression",
					attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semSoftDependency.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftDependency.getIdentifier(), attribute.getName(),
					true));
			// simulOperationSubAction.addInVariable(attribute);

			attribute = new ElemAttribute(
					"SDExpression",
					"String",
					AttributeType.OPERATION,
					false,
					"SD Expression Text",
					"Textual representation of the conditional expression (only to display)",
					"", 2, -1, "", "", -1, "", "");
			semSoftDependency.putSemanticAttribute("SDExpression", attribute);
			// simulationExecOperUniqueLabeling.addAttribute(attribute);

			attribute = new ElemAttribute("CompExp", "Boolean",
					AttributeType.GLOBALCONFIG, false,
					"Boolean Comp. Expression", "", true, 2, -1, "", "", -1,
					"", "");
			semSoftDependency.putSemanticAttribute("CompExp", attribute);
			// simulationExecOperUniqueLabeling.addAttribute(attribute);

			attribute = new ElemAttribute("SDSelected", "Boolean",
					AttributeType.GLOBALCONFIG, false, "SD Selected", "",
					false, 2, -1, "", "", -1, "", "");
			semSoftDependency.putSemanticAttribute("SDSelected", attribute);
			// simulationExecOperUniqueLabeling.addAttribute(attribute);

			semSoftDependency.addPanelVisibleAttribute("03#"
					+ "ConditionalExpression");

			// semSoftDependency.addPanelVisibleAttribute("10#" +
			// "SDExpression");

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

			// OLD Relations
			List<OpersRelType> hardSemOverTwoRelList = new ArrayList<OpersRelType>();
			hardSemOverTwoRelList.add(new OpersRelType("and", "And", "And",
					false, false, false, 2, -1, 1, 1));
			hardSemOverTwoRelList.add(new OpersRelType("or", "Or", "Or", false,
					true, true, 2, -1, 1, 1));
			hardSemOverTwoRelList.add(new OpersRelType("mutex", "Mutex",
					"Mutex.", false, true, true, 2, -1, 1, 1));
			hardSemOverTwoRelList.add(new OpersRelType("range", "Range",
					"Range", false, true, true, 2, -1, 1, 1));
			hardSemOverTwoRelList.add(new OpersRelType("none", "None", "None",
					false, true, true, 2, -1, 1, 1));
			hardSemOverTwoRelList.add(new OpersRelType("other", "other",
					"other", false, true, true, 2, -1, 1, 1));
			hardSemOverTwoRelList.add(new OpersRelType("And/Or/Mutex/[m,n]",
					"And/Or/Mutex/[m,n]", "AOMR", false, true, true, 2, -1, 1,
					1));

			List<OpersRelType> featSemOverTwoRelList = new ArrayList<OpersRelType>();
			featSemOverTwoRelList.add(new OpersRelType("and", "and", "And",
					false, false, false, 2, -1, 1, 1));
			featSemOverTwoRelList.add(new OpersRelType("or", "Or", "Or", false,
					true, true, 2, -1, 1, 1));
			featSemOverTwoRelList.add(new OpersRelType("mutex", "mutex",
					"Mutex.", false, true, true, 2, -1, 1, 1));
			featSemOverTwoRelList.add(new OpersRelType("range", "range",
					"range", false, true, true, 2, -1, 1, 1));
			featSemOverTwoRelList.add(new OpersRelType("other", "other",
					"other", false, true, true, 2, -1, 1, 1));

			OpersOverTwoRel semHardOverTwoRelation = new OpersOverTwoRel(
					"SMMOverTwoRelation", null);// hardSemOverTwoRelList);

			InstConcept instVertexHHGR = new InstConcept("GoalOTAsso",
					semHardOverTwoRelation, metaMetaInstOverTwoRel);
			refas.getVariabilityVertex().put("GoalOTAsso", instVertexHHGR);

			InstConcept instHchcHHGRHC = new InstConcept("GoaltoOTAssoPWAsso",
					metaMetaPairwiseRelation);
			refas.getVariabilityVertex().put("GoaltoOTAssoPWAsso",
					instHchcHHGRHC);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("hhtogr", instEdge);
			instEdge.setIdentifier("hhtogr");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
			instEdge.setTargetRelation(instVertexGR, true);
			instEdge.setSourceRelation(instVertexHHGR, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("hctoHHGR-HHGR-HHHHGR", instEdge);
			instEdge.setIdentifier("hctoHHGR-HHGR-HHHHGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instHchcHHGRHC, true);
			instEdge.setSourceRelation(instVertexHHGR, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("hctoHHGR-HHHHGR-H", instEdge);
			instEdge.setIdentifier("hctoHHGR-HHHHGR-H");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexHC, true);
			instEdge.setSourceRelation(instHchcHHGRHC, true);

			InstConcept instHchcHHGRGR = new InstConcept(
					"GoalfromOTAssoPWAsso", metaMetaPairwiseRelation);
			refas.getVariabilityVertex().put("GoalfromOTAssoPWAsso",
					instHchcHHGRGR);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("HHGRtohc-H-HHHHGR", instEdge);
			instEdge.setIdentifier("HHGRtohc-H-HHHHGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instHchcHHGRGR, true);
			instEdge.setSourceRelation(instVertexHC, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("HHGRtohc-HHHHGR-H", instEdge);
			instEdge.setIdentifier("HHGRtohc-H-HHHHGR-H");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexHHGR, true);
			instEdge.setSourceRelation(instHchcHHGRGR, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("gfottoip", instEdge);
			instEdge.setIdentifier("ogfottoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instHchcHHGRGR, true);

			instHchcHHGRGR.createInstAttributes();

			ia = instVertexHHGR.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""),
					"and#and#true#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""),
					"or#or#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"mutex", "", "", 1, -1, "", "", -1, "", ""),
					"mutex#mutex#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					"range#range#false#true#true#1#-1#1#1"));

			ia = instVertexHHGR.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("ANDhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTCONCEPTVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", true, "True");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexHHGR, instVertexHC, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("ORhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTCONCEPTVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", true, "False");

			t1 = new OpersExpr("ORhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexHHGR, instVertexHC, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("MUTEXhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTCONCEPTVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHC, "Sel", 0);

			t1 = new OpersExpr("sub2", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexHHGR, instVertexHC, t1, 1);

			t1 = new OpersExpr("MUTEXhardRel", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					instVertexHC, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHC, "Sel", 0);

			t1 = new OpersExpr("MUTEXrestric", refas
					.getSemanticExpressionTypes().get("LessOrEquals"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexHHGR, instVertexHC, t1, 1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"mutex", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("RANGEhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTCONCEPTVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexHHGR, instVertexHC, null, "Sel", "True", true);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"), t2,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
					"LowRange");

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexHHGR, instVertexHC, null, "Sel", "True", true);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"), t2,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
					"HighRange");

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t3);

			t1 = new OpersExpr("RANGEHardRel", refas
					.getSemanticExpressionTypes().get("Equals"),
					instVertexHHGR, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			// List<AbstractSemanticVertex> semanticVertices = new
			// ArrayList<AbstractSemanticVertex>();

			List<OpersRelType> structHardSemPairwiseRelList = new ArrayList<OpersRelType>();
			structHardSemPairwiseRelList.add(new OpersRelType("means_ends",
					"means-ends", "means-ends", true, true, true, 1, -1, 1, 1));
			structHardSemPairwiseRelList.add(new OpersRelType("implication",
					"impl.", "Impl.", false, true, true, 1, -1, 1, 1));

			List<OpersRelType> sideHardSemPairwiseRelList = new ArrayList<OpersRelType>();
			sideHardSemPairwiseRelList.add(new OpersRelType("conflict",
					"conflict", "conflict", false, true, true, 1, -1, 1, 1));
			sideHardSemPairwiseRelList.add(new OpersRelType("alternative",
					"altern.", "altern.", false, true, true, 1, -1, 1, 1));
			sideHardSemPairwiseRelList.add(new OpersRelType("preferred",
					"pref.", "pref.", false, true, true, 1, -1, 1, 1));
			sideHardSemPairwiseRelList.add(new OpersRelType("require", "req.",
					"req.", false, true, true, 1, -1, 1, 1));
			sideHardSemPairwiseRelList.add(new OpersRelType("condition",
					"cond.", "cond.", false, true, true, 1, -1, 1, 1));

			List<OpersRelType> sgPairwiseRelList = new ArrayList<OpersRelType>();
			sgPairwiseRelList.add(new OpersRelType("contribution",
					"contribution", "contribution", true, true, true, 1, -1, 1,
					1));
			sgPairwiseRelList.add(new OpersRelType("conflict", "conflict",
					"conflict", false, true, true, 1, -1, 1, 1));
			sgPairwiseRelList.add(new OpersRelType("alternative", "altern.",
					"altern.", false, true, true, 1, -1, 1, 1));
			sgPairwiseRelList.add(new OpersRelType("preferred", "pref.",
					"pref.", false, true, true, 1, -1, 1, 1));
			sgPairwiseRelList.add(new OpersRelType("implication", "impl.",
					"Impl.", false, true, true, 1, -1, 1, 1));
			sgPairwiseRelList.add(new OpersRelType("require", "req.", "req.",
					false, true, true, 1, -1, 1, 1));

			OpersPairwiseRel directHardHardSemanticEdge = new OpersPairwiseRel(
					"GoalGoalSidePWAsso", false, sideHardSemPairwiseRelList);
			InstConcept instDirHardHardSemanticEdge = new InstConcept(
					"GoalGoalSidePWAsso", metaMetaPairwiseRelation,
					directHardHardSemanticEdge);

			directHardHardSemanticEdge.putSemanticAttribute("relationType",
					new ElemAttribute("relationType", "Class",
							AttributeType.OPERATION, true, "Relation Type", "",
							OpersRelType.class.getCanonicalName(), null, "", 0,
							6, "", "", 6, "#-#\n", ""));
			directHardHardSemanticEdge.addPropEditableAttribute("06#"
					+ "relationType");
			directHardHardSemanticEdge.addPropVisibleAttribute("06#"
					+ "relationType");
			directHardHardSemanticEdge.addPanelVisibleAttribute("06#"
					+ "relationType");
			directHardHardSemanticEdge.addPanelSpacersAttribute("#"
					+ "relationType" + "#\n");

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ggstoip", instEdge);
			instEdge.setIdentifier("ggstoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirHardHardSemanticEdge, true);

			ia = instDirHardHardSemanticEdge.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();

			ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"conflict", "", "", 1, -1, "", "", -1, "", ""),
					"conflict#conflict#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("alternative", new ElemAttribute(
					"alternative", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
					"altern.#altern.#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("preferred", new ElemAttribute(
					"preferred", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "preferred", "", "", 1, -1, "", "", -1, "", ""),
					"preferred#preferred#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("require", new ElemAttribute("require",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"require", "", "", 1, -1, "", "", -1, "", ""),
					"require#require#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("condition", new ElemAttribute(
					"condition", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "condition", "", "", 1, -1, "", "", -1, "", ""),
					"condition#condition#false#true#true#1#-1#1#1"));

			ia = instDirHardHardSemanticEdge.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHC, instVertexHC, "Sel", "Sel");

			t1 = new OpersExpr("CONFSelected", refas
					.getSemanticExpressionTypes().get("Implies"), 1, false, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"conflict", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instVertexHC, instVertexHC, "Sel", true, 1);

			t1 = new OpersExpr("ALTSelected", refas
					.getSemanticExpressionTypes().get("Implies"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexHC, "Sel", true, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("alternative", new ElemAttribute(
					"alternative", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHC, instVertexHC, "Sel", "Sel");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexHC, "Sel");

			t1 = new OpersExpr("PREFSelected", refas
					.getSemanticExpressionTypes().get("And"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("preferred", new ElemAttribute(
					"preferred", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "preferred", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Subtraction"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirHardHardSemanticEdge, instVertexHC, "Sel", false, 1);

			t1 = new OpersExpr("REQSelected", refas
					.getSemanticExpressionTypes().get("GreaterOrEq"), 1, false,
					t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("require", new ElemAttribute("require",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"condition", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("CONDSelected", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHC, instVertexHC, "Sel", "Sel");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("CONDNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHC, instVertexHC, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("condition", new ElemAttribute(
					"condition", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "condition", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("GoalGoalSidePWAsso",
					instDirHardHardSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("GoalGoalSidePWAsso-GR",
					instEdge);
			instEdge.setIdentifier("GoalGoalSidePWAsso-GR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirHardHardSemanticEdge, true);
			instEdge.setSourceRelation(instVertexHC, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("GoalGoalSidePW-GR-Asso",
					instEdge);
			instEdge.setIdentifier("GoalGoalSidePW-GR-Asso");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexHC, true);
			instEdge.setSourceRelation(instDirHardHardSemanticEdge, true);

			OpersPairwiseRel directStructHardHardSemanticEdge = new OpersPairwiseRel(
					"structHardHardPWAsso", false, structHardSemPairwiseRelList);

			InstConcept instDirStructHardHardSemanticEdge = new InstConcept(
					"structHardHardPWAsso", metaMetaPairwiseRelation,
					directStructHardHardSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("shhtoip", instEdge);
			instEdge.setIdentifier("shhtoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirStructHardHardSemanticEdge, true);

			ia = instDirStructHardHardSemanticEdge
					.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("means_ends", new ElemAttribute(
					"means_ends", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "means_ends", "", "", 1, -1, "", "", -1, "", ""),
					"means_ends#means-ends#true#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("implication", new ElemAttribute(
					"implication", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "implication", "", "", 1, -1, "", "", -1, "", ""),
					"implication#implication#false#true#true#1#-1#1#1"));

			ia = instDirStructHardHardSemanticEdge
					.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHC, instVertexHC, "Sel", "Sel");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHC, instVertexHC, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("means_ends", new ElemAttribute(
					"means_ends", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "means_ends", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTRELVARIABLE,
					instDirStructHardHardSemanticEdge, instVertexHC, "Sel",
					true, 1);

			t1 = new OpersExpr("Sel", refas.getSemanticExpressionTypes().get(
					"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexHC, "Sel", true, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("implication", new ElemAttribute(
					"implication", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "implication", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("structHardHardPWAsso",
					instDirStructHardHardSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("structHardHardPWAsso-GR",
					instEdge);
			instEdge.setIdentifier("structHardHardPWAsso-GR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirStructHardHardSemanticEdge, true);
			instEdge.setSourceRelation(instVertexHC, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("structHardHardPW-GR-Asso",
					instEdge);
			instEdge.setIdentifier("structHardHardPW-GR-Asso");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexHC, true);
			instEdge.setSourceRelation(instDirStructHardHardSemanticEdge, true);

			List<OpersRelType> featSideSemPairwiseRelList = new ArrayList<OpersRelType>();
			featSideSemPairwiseRelList.add(new OpersRelType("require",
					"require", "require", false, true, true, 1, -1, 1, 1));
			featSideSemPairwiseRelList.add(new OpersRelType("conflict",
					"excl.", "excl.", false, true, true, 1, -1, 1, 1));

			List<OpersRelType> featVertSemPairwiseRelList = new ArrayList<OpersRelType>();
			featVertSemPairwiseRelList.add(new OpersRelType("mandatory",
					"mandatory", "mandatory", true, true, true, 1, -1, 1, 1));
			featVertSemPairwiseRelList.add(new OpersRelType("optional", "opt.",
					"opt.", false, true, true, 1, -1, 1, 1));

			OpersPairwiseRel directFeaFeatVertSemEdge = new OpersPairwiseRel(
					"FeatFeatParentPWAsso", false, featVertSemPairwiseRelList);
			InstConcept instDirFeaFeatVertSemEdge = new InstConcept(
					"FeatFeatParentPWAsso", metaMetaPairwiseRelation,
					directFeaFeatVertSemEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ffptoip", instEdge);
			instEdge.setIdentifier("ffptoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirFeaFeatVertSemEdge, true);

			ia = instDirFeaFeatVertSemEdge.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("mandatory", new ElemAttribute(
					"mandatory", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "mandatory", "", "", 1, -1, "", "", -1, "", ""),
					"mandatory#mandatory#true#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("optional", new ElemAttribute("optional",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"optional", "", "", 1, -1, "", "", -1, "", ""),
					"optional#optional#false#true#true#1#-1#1#1"));

			ia = instDirFeaFeatVertSemEdge.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("MANSelected", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexF, instVertexF, "Sel", "Sel");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("MANNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexF, instVertexF, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("mandatory", new ElemAttribute(
					"mandatory", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "mandatory", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("OPTSelected", refas
					.getSemanticExpressionTypes().get("LessOrEquals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexF, instVertexF, "Sel", "Sel");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("OPTNotAvailable", refas
					.getSemanticExpressionTypes().get("LessOrEquals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
					instVertexF, instVertexF, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			attribute = new ElemAttribute("optional", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "optional", "", "", 1, -1, "",
					"", -1, "", "");
			ias.add(new InstAttribute("optional", attribute,
					semanticExpressions));
			// simulOperationSubAction.addInVariable(attribute);
			// simulOperationSubAction.addInAttribute(new OpersIOAttribute(
			// semGeneralElement.getIdentifier(), attribute.getName(), true));

			refas.getVariabilityVertex().put("FeatFeatParentPWAsso",
					instDirFeaFeatVertSemEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("FeatFeatParentPWAsso-GR",
					instEdge);
			instEdge.setIdentifier("FeatFeatParentPWAsso-GR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirFeaFeatVertSemEdge, true);
			instEdge.setSourceRelation(instVertexF, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("FeatFeatParentPW-GR-Asso",
					instEdge);
			instEdge.setIdentifier("FeatFeatParentPW-GR-Asso");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexF, true);
			instEdge.setSourceRelation(instDirFeaFeatVertSemEdge, true);

			OpersPairwiseRel directFeatFeatSideSemEdge = new OpersPairwiseRel(
					"FeatFeatSidePWAsso", false, featSideSemPairwiseRelList);
			InstConcept instDirFeatFeatSideSemEdge = new InstConcept(
					"FeatFeatSidePWAsso", metaMetaPairwiseRelation,
					directFeatFeatSideSemEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ffstoip", instEdge);
			instEdge.setIdentifier("ffstoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirFeatFeatSideSemEdge, true);

			ia = instDirFeatFeatSideSemEdge.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();

			ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"conflict", "", "", 1, -1, "", "", -1, "", ""),
					"conflict#conflict#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("require", new ElemAttribute("require",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"require", "", "", 1, -1, "", "", -1, "", ""),
					"require#require#false#true#true#1#-1#1#1"));

			ia = instDirFeatFeatSideSemEdge.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexF, instVertexF, "Sel", "Sel");

			t1 = new OpersExpr("CONFSelected", refas
					.getSemanticExpressionTypes().get("Implies"), 1, false, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"conflict", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Subtraction"),
					ExpressionVertexType.LEFTUNIQUEOUTRELVARIABLE,
					instDirFeatFeatSideSemEdge, instVertexF, "Sel", false, 1);

			t1 = new OpersExpr("REQSelected", refas
					.getSemanticExpressionTypes().get("GreaterOrEq"), 1, false,
					t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("require", new ElemAttribute("require",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"condition", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("FeatFeatSidePWAsso",
					instDirFeatFeatSideSemEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("FeatFeatSidePWAsso-GR",
					instEdge);
			instEdge.setIdentifier("FeatFeatSidePWAsso-GR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirFeatFeatSideSemEdge, true);
			instEdge.setSourceRelation(instVertexF, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("FeatFeatSidePW-GR-Asso",
					instEdge);
			instEdge.setIdentifier("FeatFeatSidePW-GR-Asso");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexF, true);
			instEdge.setSourceRelation(instDirFeatFeatSideSemEdge, true);

			OpersOverTwoRel semFeatOverTwoRelation = new OpersOverTwoRel(
					"FeatFeatOTAsso", null);// featSemOverTwoRelList);
			InstConcept instVertexFFGR = new InstConcept("FeatFeatOTAsso",
					semFeatOverTwoRelation, metaMetaInstOverTwoRel);
			refas.getVariabilityVertex().put("FeatFeatOTAsso", instVertexFFGR);

			ia = instVertexFFGR.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""),
					"and#and#true#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""),
					"or#or#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"mutex", "", "", 1, -1, "", "", -1, "", ""),
					"mutex#mutex#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					"range#range#false#true#true#1#-1#1#1"));

			ia = instVertexFFGR.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("ANDhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexF,
					instVertexFFGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexFFGR, instVertexF, "Sel", true, "True");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexF, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("ORhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexF,
					instVertexFFGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexFFGR, instVertexF, "Sel", true, "False");

			t1 = new OpersExpr("ORhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexF, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("MUTEXhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexF,
					instVertexFFGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexF, "Sel", 0);

			t1 = new OpersExpr("sub2", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexF, t1, 1);

			t1 = new OpersExpr("MUTEXhardRel", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					instVertexF, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexF, "Sel", 0);

			t1 = new OpersExpr("MUTEXrestric", refas
					.getSemanticExpressionTypes().get("LessOrEquals"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexF, t1, 1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"mutex", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("RANGEhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
					instVertexFFGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexHC, null, "Sel", "True", true);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"), t2,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
					"LowRange");

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexHC, null, "Sel", "True", true);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"), t2,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
					"HighRange");

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t3);

			t1 = new OpersExpr("RANGEHardRel", refas
					.getSemanticExpressionTypes().get("Equals"),
					instVertexFFGR, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			List<OpersRelType> assetoperPairwiseRelList = new ArrayList<OpersRelType>();
			assetoperPairwiseRelList.add(new OpersRelType("implementation",
					"implementation", "imp.", true, true, true, 1, 1, 1, 1));

			OpersPairwiseRel semAssetOperPairwiseRel = new OpersPairwiseRel(
					"varAssetOperPWAsso", false, assetoperPairwiseRelList);
			InstConcept instSemAssetOperPairwiseRel = new InstConcept(
					"varAssetOperPWAsso", metaMetaPairwiseRelation,
					semAssetOperPairwiseRel);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("vaptoip", instEdge);
			instEdge.setIdentifier("vaptoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instSemAssetOperPairwiseRel, true);

			ia = instDirStructHardHardSemanticEdge
					.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();

			ias.add(new InstAttribute("implementation", new ElemAttribute(
					"implementation", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "implementation", "", "", 1,
					-1, "", "", -1, "", ""),
					"implementation#implementation#false#true#true#1#-1#1#1"));

			ia = instDirStructHardHardSemanticEdge
					.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("IMPLSelected1", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexAsset, instVertexHC, "Sel", "Sel");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("IMPLNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexAsset, instVertexHC, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("implementation", new ElemAttribute(
					"implementation", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "implementation", "", "", 1,
					-1, "", "", -1, "", ""), semanticExpressions));

			refas.getVariabilityVertex().put("varAssetOperPWAsso",
					instSemAssetOperPairwiseRel);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("varAssetOperPWAsso-GR",
					instEdge);
			instEdge.setIdentifier("varAssetOperPWAsso-GR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instSemAssetOperPairwiseRel, true);
			instEdge.setSourceRelation(instVertexAsset, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("varAssetOperPW-GR-Asso",
					instEdge);
			instEdge.setIdentifier("varAssetOperPW-GR-Asso");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexOper, true);
			instEdge.setSourceRelation(instSemAssetOperPairwiseRel, true);

			List<OpersRelType> assetPairwiseRelList = new ArrayList<OpersRelType>();
			assetPairwiseRelList.add(new OpersRelType("delegation",
					"delegation", "deleg.", true, true, true, 1, 1, 1, 1));
			assetPairwiseRelList.add(new OpersRelType("assembly", "assembly",
					"assembly", true, true, true, 1, 1, 1, 1));

			OpersPairwiseRel semAssetPairwiseRel = new OpersPairwiseRel(
					"varAssetPWAsso", false, assetPairwiseRelList);
			InstConcept instSemAssetPairwiseRel = new InstConcept(
					"varAssetPWAsso", metaMetaPairwiseRelation,
					semAssetPairwiseRel);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("vatoip", instEdge);
			instEdge.setIdentifier("vatoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instSemAssetPairwiseRel, true);

			ia = instSemAssetPairwiseRel.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("mandatory", new ElemAttribute(
					"mandatory", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "mandatory", "", "", 1, -1, "", "", -1, "", ""),
					"mandatory#mandatory#true#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("optional", new ElemAttribute("optional",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"optional", "", "", 1, -1, "", "", -1, "", ""),
					"optional#optional#false#true#true#1#-1#1#1"));

			ia = instSemAssetPairwiseRel.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("DELSelected", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexAsset, instVertexAsset, "Sel", "Sel");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("DELNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexAsset, instVertexAsset, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("delegation", new ElemAttribute(
					"delegation", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "delegation", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();
			t1 = new OpersExpr("ASSESelected", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexAsset, instVertexAsset, "Sel", "Sel");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("ASSENotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexF, instVertexF, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			ias.add(new InstAttribute("assembly", new ElemAttribute("assembly",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"assembly", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("varAssetPWAsso",
					instSemAssetPairwiseRel);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("varAssetPWAsso-GR", instEdge);
			instEdge.setIdentifier("varAssetPWAsso-GR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instSemAssetPairwiseRel, true);
			instEdge.setSourceRelation(instVertexAsset, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("varAssetPW-GR-Asso", instEdge);
			instEdge.setIdentifier("varAssetPW-GR-Asso");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexAsset, true);
			instEdge.setSourceRelation(instSemAssetPairwiseRel, true);

			List<OpersRelType> vcntxPairwiseRelList = new ArrayList<OpersRelType>();
			vcntxPairwiseRelList.add(new OpersRelType("Variable Context", "",
					"", true, true, true, 1, 1, 1, 1));

			OpersPairwiseRel semvarcntxPairwiseRel = new OpersPairwiseRel(
					"varcntxPWAsso", false, vcntxPairwiseRelList);

			InstConcept instSemvarcntxPairwiseRel = new InstConcept(
					"varcntxPWAsso", metaMetaPairwiseRelation,
					semvarcntxPairwiseRel);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("vcxtoip", instEdge);
			instEdge.setIdentifier("vcxtoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instSemvarcntxPairwiseRel, true);

			ia = instSemvarcntxPairwiseRel.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();

			ias.add(new InstAttribute("Variable Context", new ElemAttribute(
					"Variable Context", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "", "", "", 1, -1, "", "", -1,
					"", ""),
					"Variable Context#Variable Context#false#true#true#1#-1#1#1"));

			ia = instSemvarcntxPairwiseRel.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			ias.add(new InstAttribute("Variable Context", new ElemAttribute(
					"Variable Context", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "Variable Context", "", "", 1,
					-1, "", "", -1, "", ""), semanticExpressions));

			refas.getVariabilityVertex().put("varcntxPWAsso",
					instSemvarcntxPairwiseRel);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("varcntxPWAsso-GR", instEdge);
			instEdge.setIdentifier("varcntxPWAsso-GR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instSemvarcntxPairwiseRel, true);
			instEdge.setSourceRelation(instVertexVAR, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("varcntxPW-GR-Asso", instEdge);
			instEdge.setIdentifier("varcntxPW-GR-Asso");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexCG, true);
			instEdge.setSourceRelation(instSemvarcntxPairwiseRel, true);

			List<OpersRelType> sdPairwiseRelList = new ArrayList<OpersRelType>();
			sdPairwiseRelList.add(new OpersRelType("SD", "", "", true, true,
					true, 1, 1, 1, 1));

			/*
			 * SemanticPairwiseRelation semSDPairwiseRel = new
			 * SemanticPairwiseRelation( "sdPWAsso", false, sdPairwiseRelList);
			 * InstConcept instSemSDPairwiseRel = new InstConcept("sdPWAsso",
			 * metaPairwiseRelation, semSDPairwiseRel);
			 * refas.getVariabilityVertex().put("sdPWAsso",
			 * instSemSDPairwiseRel);
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
			 * refas.getVariabilityVertex().put("operclaimPWAsso",
			 * instSemCLPWAsso);
			 * 
			 * instEdge = new InstPairwiseRelation();
			 * refas.getConstraintInstEdges().put("operclaimPWAsso-GR",
			 * instEdge); instEdge.setIdentifier("operclaimPWAsso-GR");
			 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			 * instEdge.setTargetRelation(instSemCLPWAsso, true);
			 * instEdge.setSourceRelation(instVertexOper, true);
			 * 
			 * instEdge = new InstPairwiseRelation();
			 * refas.getConstraintInstEdges().put("operclaimPW-GR-Asso",
			 * instEdge); instEdge.setIdentifier("operclaimPW-GR-Asso");
			 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			 * instEdge.setTargetRelation(instVertexCL, true);
			 * instEdge.setSourceRelation(instSemCLPWAsso, true);
			 */

			List<OpersRelType> claimSGPairwiseRelList = new ArrayList<OpersRelType>();
			claimSGPairwiseRelList.add(new OpersRelType("ClaimToSG", "", "",
					true, true, true, 1, 1, 1, 1));

			/*
			 * SemanticPairwiseRelation semClaimSGPairwiseRel = new
			 * SemanticPairwiseRelation( "claimSGPWAsso", false,
			 * claimSGPairwiseRelList); InstConcept instSemCLSGPWAsso = new
			 * InstConcept("claimSGPWAsso", metaPairwiseRelation,
			 * semClaimSGPairwiseRel);
			 * refas.getVariabilityVertex().put("claimSGPWAsso",
			 * instSemCLSGPWAsso);
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
			List<OpersRelType> groupPairwiseRelList = new ArrayList<OpersRelType>();
			groupPairwiseRelList.add(new OpersRelType("Group", "", "", true,
					true, true, 1, 1, 1, 1));
			/*
			 * SemanticPairwiseRelation semGroupPairwiseRel = new
			 * SemanticPairwiseRelation( "groupPWAsso", false,
			 * groupPairwiseRelList); InstConcept instSemGroupPWAsso = new
			 * InstConcept("groupPWAsso", metaPairwiseRelation,
			 * semGroupPairwiseRel);
			 * refas.getVariabilityVertex().put("groupPWAsso",
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
			List<OpersRelType> nonePairwiseRelList = new ArrayList<OpersRelType>();
			nonePairwiseRelList.add(new OpersRelType("Group", "", "", true,
					true, true, 1, 1, 1, 1));

			/*
			 * SemanticPairwiseRelation nonePairwiseRel = new
			 * SemanticPairwiseRelation( "NonePWAsso", false,
			 * nonePairwiseRelList);
			 * refas.getVariabilityVertex().put("NonePWAsso", new
			 * InstConcept("NonePWAsso", metaPairwiseRelation,
			 * nonePairwiseRel));
			 * 
			 * SemanticPairwiseRelation extendsPairwiseRel = new
			 * SemanticPairwiseRelation( "extendsPWAsso", false,
			 * nonePairwiseRelList);
			 * refas.getVariabilityVertex().put("extendsPWAsso", new
			 * InstConcept( "extendsPWAsso", metaPairwiseRelation,
			 * extendsPairwiseRel));
			 * 
			 * SemanticPairwiseRelation viewPairwiseRel = new
			 * SemanticPairwiseRelation( "viewPWAsso", false,
			 * nonePairwiseRelList);
			 * refas.getVariabilityVertex().put("viewPWAsso", new
			 * InstConcept("viewPWAsso", metaPairwiseRelation,
			 * viewPairwiseRel));
			 */
			List<OpersRelType> genconsPairwiseRelList = new ArrayList<OpersRelType>();
			genconsPairwiseRelList.add(new OpersRelType("GeneralConstraint",
					"", "", true, true, true, 1, 1, 1, 1));

			// Feature to Feature

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ffgrtogr", instEdge);
			instEdge.setIdentifier("ffgrtogr");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
			instEdge.setTargetRelation(instVertexGR, true);
			instEdge.setSourceRelation(instVertexFFGR, true);

			InstConcept instFeatFeatFFFGR = new InstConcept(
					"FeatFeatToOTAssoPWAsso", metaMetaPairwiseRelation);
			refas.getVariabilityVertex().put("FeatFeatToOTAssoPWAsso",
					instFeatFeatFFFGR);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("featfeatF-FFFGR", instEdge);
			instEdge.setIdentifier("featfeatF-FFFGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instFeatFeatFFFGR, true);
			instEdge.setSourceRelation(instVertexF, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("featfeatFFFGR-FFGR", instEdge);
			instEdge.setIdentifier("featfeatFFFGR-FFGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexFFGR, true);
			instEdge.setSourceRelation(instFeatFeatFFFGR, true);

			InstConcept instFeatFeatFGRF = new InstConcept(
					"FeatFeatFromOTAssoPWAsso", metaMetaPairwiseRelation);
			refas.getVariabilityVertex().put("FeatFeatFromOTAssoPWAsso",
					instFeatFeatFGRF);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("featfeatFFFGR-F", instEdge);
			instEdge.setIdentifier("featfeatFFFGR-F");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexF, true);
			instEdge.setSourceRelation(instFeatFeatFGRF, true);

			instEdge = new InstPairwiseRel();
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
			 * refas.getVariabilityVertex().put("GoalGoalOTAsso", new
			 * InstConcept( "GoalGoalOTAsso", metaOverTwoRelation,
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
			 * refas.getVariabilityVertex().put("OperOperOTAsso", new
			 * InstConcept( "OperOperOTAsso", metaOverTwoRelation,
			 * semanticOperOperGroupRelation));
			 */
			// getConstraintInstEdges().put("OperOperPWAsso",
			// new InstPairwiseRelation(directOperOperSemanticEdge));

			// SG to SG

			// semanticVertices = new ArrayList<AbstractSemanticVertex>();
			// semanticVertices.add(semSoftgoal);

			OpersPairwiseRel directSGSGSemEdge = new OpersPairwiseRel(
					"SgSgPWAsso", true, sgPairwiseRelList);
			attribute = new ElemAttribute("sourceLevel", "Integer",
					AttributeType.OPERATION, "Source Level", "", 1, false,
					new RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
			directSGSGSemEdge.putSemanticAttribute("sourceLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));

			attribute = new ElemAttribute("targetLevel", "Integer",
					AttributeType.OPERATION, "Target Level", "", 1, false,
					new RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
			directSGSGSemEdge.putSemanticAttribute("targetLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));

			attribute = new ElemAttribute("AggregationLow", "Integer",
					AttributeType.OPERATION, false, "Aggregation Low", "", 0,
					0, -1, "", "", -1, "", "");
			directSGSGSemEdge.putSemanticAttribute("AggregationLow", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));

			directSGSGSemEdge
					.addPanelVisibleAttribute("07#" + "AggregationLow");

			directSGSGSemEdge.addPanelSpacersAttribute("[#" + "AggregationLow"
					+ "#..");

			directSGSGSemEdge
					.addPropEditableAttribute("07#" + "AggregationLow");

			directSGSGSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

			attribute = new ElemAttribute("AggregationHigh", "Integer",
					AttributeType.OPERATION, false, "AggregationHigh", "", 0,
					0, -1, "", "", -1, "", "");
			directSGSGSemEdge
					.putSemanticAttribute("AggregationHigh", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));

			directSGSGSemEdge.addPanelVisibleAttribute("08#"
					+ "AggregationHigh");

			directSGSGSemEdge.addPanelSpacersAttribute("#" + "AggregationHigh"
					+ "#]\n");

			directSGSGSemEdge.addPropEditableAttribute("08#"
					+ "AggregationHigh");

			directSGSGSemEdge
					.addPropVisibleAttribute("08#" + "AggregationHigh");

			directSGSGSemEdge.addPropEditableAttribute("08#" + "sourceLevel");
			directSGSGSemEdge.addPropVisibleAttribute("08#" + "sourceLevel");
			directSGSGSemEdge.addPanelVisibleAttribute("08#" + "sourceLevel");

			directSGSGSemEdge.addPanelSpacersAttribute(":#" + "targetLevel"
					+ "#");
			directSGSGSemEdge.addPropEditableAttribute("09#" + "targetLevel");
			directSGSGSemEdge.addPropVisibleAttribute("09#" + "targetLevel");
			directSGSGSemEdge.addPanelVisibleAttribute("09#" + "targetLevel");

			InstConcept instDirSGSGSemanticEdge = new InstConcept("SgSgPWAsso",
					metaMetaPairwiseRelation, directSGSGSemEdge);

			refas.getVariabilityVertex().put("SgSgPWAsso",
					instDirSGSGSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sgsgtoip", instEdge);
			instEdge.setIdentifier("sgsgtoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirSGSGSemanticEdge, true);

			ia = instDirSGSGSemanticEdge.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("contribution", new ElemAttribute(
					"contribution", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "contribution", "", "", 1, -1,
					"", "", -1, "", ""),
					"contribution#contribution#true#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"conflict", "", "", 1, -1, "", "", -1, "", ""),
					"conflict#conflict#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("alternative", new ElemAttribute(
					"alternative", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
					"altern.#altern.#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("preferred", new ElemAttribute(
					"preferred", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "preferred", "", "", 1, -1, "", "", -1, "", ""),
					"preferred#preferred#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("require", new ElemAttribute("require",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"require", "", "", 1, -1, "", "", -1, "", ""),
					"require#require#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("implication", new ElemAttribute(
					"implication", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "implication", "", "", 1, -1, "", "", -1, "", ""),
					"implication#implication#false#true#true#1#-1#1#1"));

			ia = instDirSGSGSemanticEdge.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			// contribution of direct relations

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSGSGSemanticEdge, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
					"low");

			t1 = new OpersExpr("low: SGReqLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSGSGSemanticEdge, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
					"high");

			t1 = new OpersExpr("high: SGReqLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSGSGSemanticEdge, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
					"close");

			t1 = new OpersExpr("close: SGReqLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("contribution", new ElemAttribute(
					"contribution", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "contribution", "", "", 1, -1,
					"", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			// Wrong expressions - correct for conflict

			t1 = new OpersExpr("CLEx SrcLv", refas.getSemanticExpressionTypes()
					.get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "ClaimExpLevel",
					"sourceLevel");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instVertexSG, instVertexSG, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, "satisficingLevel", "low");

			t1 = new OpersExpr("low: source & target", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("CLEx SrcLv", refas.getSemanticExpressionTypes()
					.get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "ClaimExpLevel",
					"sourceLevel");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instVertexSG, instVertexSG, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, "satisficingLevel", "high");

			t1 = new OpersExpr("high: source & target", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("CLEx SrcLv", refas.getSemanticExpressionTypes()
					.get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "ClaimExpLevel",
					"sourceLevel");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instVertexSG, instVertexSG, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, "satisficingLevel", "high");

			t1 = new OpersExpr("close: source & target", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"conflict", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "Sel", true, 1);

			t1 = new OpersExpr("ALTSelected", refas
					.getSemanticExpressionTypes().get("Implies"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexSG, "Sel", true, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("alternative", new ElemAttribute(
					"alternative", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexSG, instVertexSG, "Sel", "Sel");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexSG, "Sel");

			t1 = new OpersExpr("PREFSelected", refas
					.getSemanticExpressionTypes().get("And"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("preferred", new ElemAttribute(
					"preferred", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "preferred", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Subtraction"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "Sel", false, 1);

			t1 = new OpersExpr("REQSelected", refas
					.getSemanticExpressionTypes().get("GreaterOrEq"), 1, false,
					t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("require", new ElemAttribute("require",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"require", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "Sel", true, 1);

			t1 = new OpersExpr("IMPSelected", refas
					.getSemanticExpressionTypes().get("Implies"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexSG, "Sel", true, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("implication", new ElemAttribute(
					"implication", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "implication", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sgsgPW-sgpwsg", instEdge);
			instEdge.setIdentifier("sgsgPW-sgpwsg");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirSGSGSemanticEdge, true);
			instEdge.setSourceRelation(instVertexSG, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sgsgPW-sgsgpw", instEdge);
			instEdge.setIdentifier("sgsgPW-sgsgpw");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexSG, true);
			instEdge.setSourceRelation(instDirSGSGSemanticEdge, true);

			OpersOverTwoRel semanticSGSGGroupRelation = new OpersOverTwoRel(
					"SgSgOTAsso", null);// hardSemOverTwoRelList);

			InstConcept instVertexSGGR = new InstConcept("SgSgOTAsso",
					semanticSGSGGroupRelation, metaMetaInstOverTwoRel);
			refas.getVariabilityVertex().put("SgSgOTAsso", instVertexSGGR);

			OpersPairwiseRel directGRSGSemEdge = new OpersPairwiseRel(
					"sgsgOTAssoToPWAsso", true, sgPairwiseRelList);
			attribute = new ElemAttribute("targetLevel", "Integer",
					AttributeType.OPERATION, "Target Level", "", 1, false,
					new RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
			directGRSGSemEdge.putSemanticAttribute("targetLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directGRSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directGRSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					directGRSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					directGRSGSemEdge.getIdentifier(), attribute.getName(),
					true));

			directGRSGSemEdge.addPanelSpacersAttribute(":#" + "targetLevel"
					+ "#");
			directGRSGSemEdge.addPropEditableAttribute("09#" + "targetLevel");
			directGRSGSemEdge.addPropVisibleAttribute("09#" + "targetLevel");
			directGRSGSemEdge.addPanelVisibleAttribute("09#" + "targetLevel");

			// FIXME remove, use other
			InstConcept instSgsgGRSG = new InstConcept("sgsgOTAssoToPWAsso",
					metaMetaPairwiseRelation, directGRSGSemEdge);

			refas.getVariabilityVertex()
					.put("sgsgOTAssoToPWAsso", instSgsgGRSG);

			ia = instSgsgGRSG.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("contribution", new ElemAttribute(
					"contribution", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "contribution", "", "", 1, -1,
					"", "", -1, "", ""),
					"contribution#contribution#true#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"conflict", "", "", 1, -1, "", "", -1, "", ""),
					"conflict#conflict#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("alternative", new ElemAttribute(
					"alternative", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
					"altern.#altern.#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("preferred", new ElemAttribute(
					"preferred", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "preferred", "", "", 1, -1, "", "", -1, "", ""),
					"preferred#preferred#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("require", new ElemAttribute("require",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"require", "", "", 1, -1, "", "", -1, "", ""),
					"require#require#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("implication", new ElemAttribute(
					"implication", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "implication", "", "", 1, -1, "", "", -1, "", ""),
					"implication#implication#false#true#true#1#-1#1#1"));

			ia = instSgsgGRSG.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			// Expressions for contribution with OTRel

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instSgsgGRSG, instVertexSGGR, "Sel", true, 1);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instSgsgGRSG, instVertexSG, "ClaimExpLevel", true,
					"targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instSgsgGRSG, instVertexSG, "satisficingLevel", "low");

			t1 = new OpersExpr("low: SGReqLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instSgsgGRSG, instVertexSGGR, "Sel", true, 1);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instSgsgGRSG, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
					"high");

			t1 = new OpersExpr("high: SGReqLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instSgsgGRSG, instVertexSGGR, "Sel", true, 1);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instSgsgGRSG, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
					"close");

			t1 = new OpersExpr("close: SGReqLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("contribution", new ElemAttribute(
					"contribution", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "contribution", "", "", 1, -1,
					"", "", -1, "", ""), semanticExpressions));

			// FIX me wrong relations or good for conflict
			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("SG Gr Sel", refas.getSemanticExpressionTypes()
					.get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instVertexSG, "Sel", 1);

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instVertexSG, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, "satisficingLevel", "low");

			t1 = new OpersExpr("low: source & target", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("SG Gr Sel", refas.getSemanticExpressionTypes()
					.get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instVertexSG, "Sel", 1);

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instVertexSG, instVertexSG, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, "satisficingLevel", "high");

			t1 = new OpersExpr("high: source & target", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("SG Gr Sel", refas.getSemanticExpressionTypes()
					.get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instVertexSG, "Sel", 1);

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instVertexSG, instVertexSG, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexSG, "satisficingLevel", "high");

			t1 = new OpersExpr("close: source & target", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"conflict", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "Sel", true, 1);

			t1 = new OpersExpr("ALTSelected", refas
					.getSemanticExpressionTypes().get("Implies"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexSG, "Sel", true, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("alternative", new ElemAttribute(
					"alternative", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexSG, instVertexSG, "Sel", "Sel");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexSG, "Sel");

			t1 = new OpersExpr("PREFSelected", refas
					.getSemanticExpressionTypes().get("And"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("preferred", new ElemAttribute(
					"preferred", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "preferred", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Subtraction"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "Sel", false, 1);

			t1 = new OpersExpr("REQSelected", refas
					.getSemanticExpressionTypes().get("GreaterOrEq"), 1, false,
					t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("require", new ElemAttribute("require",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"require", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "Sel", true, 1);

			t1 = new OpersExpr("IMPSelected", refas
					.getSemanticExpressionTypes().get("Implies"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexSG, "Sel", true, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("implication", new ElemAttribute(
					"implication", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "implication", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			OpersPairwiseRel directSGGRSemEdge = new OpersPairwiseRel(
					"sgsgOTAssoFromPWAsso", true, sgPairwiseRelList);
			attribute = new ElemAttribute("sourceLevel", "Integer",
					AttributeType.OPERATION, "Source Level", "", 1, false,
					new RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
			directSGGRSemEdge.putSemanticAttribute("sourceLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));

			attribute = new ElemAttribute("AggregationLow", "Integer",
					AttributeType.OPERATION, false, "Aggregation Low", "", 0,
					0, -1, "", "", -1, "", "");
			directSGGRSemEdge.putSemanticAttribute("AggregationLow", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));

			directSGGRSemEdge
					.addPanelVisibleAttribute("07#" + "AggregationLow");

			directSGGRSemEdge.addPanelSpacersAttribute("[#" + "AggregationLow"
					+ "#..");

			directSGGRSemEdge
					.addPropEditableAttribute("07#" + "AggregationLow");

			directSGGRSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

			attribute = new ElemAttribute("AggregationHigh", "Integer",
					AttributeType.OPERATION, false, "AggregationHigh", "", 0,
					0, -1, "", "", -1, "", "");
			directSGGRSemEdge
					.putSemanticAttribute("AggregationHigh", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));

			directSGGRSemEdge.addPanelVisibleAttribute("08#"
					+ "AggregationHigh");

			directSGGRSemEdge.addPanelSpacersAttribute("#" + "AggregationHigh"
					+ "#]\n");

			directSGGRSemEdge.addPropEditableAttribute("08#"
					+ "AggregationHigh");

			directSGGRSemEdge
					.addPropVisibleAttribute("08#" + "AggregationHigh");

			directSGGRSemEdge.addPropEditableAttribute("08#" + "sourceLevel");
			directSGGRSemEdge.addPropVisibleAttribute("08#" + "sourceLevel");
			directSGGRSemEdge.addPanelVisibleAttribute("08#" + "sourceLevel");

			InstConcept instSgsgSGR = new InstConcept("sgsgOTAssoFromPWAsso",
					metaMetaPairwiseRelation, directSGGRSemEdge);

			refas.getVariabilityVertex().put("sgsgOTAssoFromPWAsso",
					instSgsgSGR);

			// extends
			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sgsggrtoip", instEdge);
			instEdge.setIdentifier("sgsggrtoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instSgsgGRSG, true);

			// extends
			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sggrtogr", instEdge);
			instEdge.setIdentifier("sggrtogr");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
			instEdge.setTargetRelation(instVertexGR, true);
			instEdge.setSourceRelation(instVertexSGGR, true);

			// From SG to group
			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sgsgSGR-SGsgsg", instEdge);
			instEdge.setIdentifier("sgsgSGR-SGsgsg");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instSgsgSGR, true);
			instEdge.setSourceRelation(instVertexSG, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sgsgSGR-sgsgSG", instEdge);
			instEdge.setIdentifier("sgsgSGR-sgsgSG");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexSGGR, true);
			instEdge.setSourceRelation(instSgsgSGR, true);

			// From group to SG
			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("SGGRtosg-GRsgsgGR", instEdge);
			instEdge.setIdentifier("SGGRtosg-GRsgsgGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instSgsgGRSG, true);
			instEdge.setSourceRelation(instVertexSGGR, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("SGGRtosg-SGsgsgSG", instEdge);
			instEdge.setIdentifier("SGGRtosg-SGsgsgSG");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexSG, true);
			instEdge.setSourceRelation(instSgsgGRSG, true);

			// FIX for SG to SG relations
			ia = instVertexSGGR.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""),
					"and#and#true#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""),
					"or#or#false#true#true#1#-1#1#1"));

			/*
			 * ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
			 * StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex", "",
			 * 1, -1, "", "", -1, "", ""),
			 * "mutex#mutex#false#true#true#1#-1#1#1"));
			 * 
			 * ias.add(new InstAttribute("range", new ElemAttribute("range",
			 * StringType.IDENTIFIER, AttributeType.OPTION, false, "range", "",
			 * 1, -1, "", "", -1, "", ""),
			 * "range#range#false#true#true#1#-1#1#1"));
			 */
			ia = instVertexSGGR.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("sub2", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
					instVertexSG, instSgsgSGR, "ClaimExpLevel", "sourceLevel");

			t1 = new OpersExpr("sub1", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCFIXEDSUBEXP,
					instSgsgSGR, instDirSGSGSemanticEdge, t1, "True");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexSGGR, instVertexSG, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("sub2", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
					instVertexSG, instSgsgSGR, "ClaimExpLevel", "sourceLevel");

			t1 = new OpersExpr("sub1", refas.getSemanticExpressionTypes().get(
					"Or"), ExpressionVertexType.LEFTITERINCFIXEDSUBEXP,
					instSgsgSGR, instDirSGSGSemanticEdge, t1, "False");

			t1 = new OpersExpr("ORhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexSGGR, instVertexSG, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));
			/*
			 * semanticExpressions = new ArrayList<OpersExpr>();
			 * 
			 * t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
			 * "Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
			 * instVertexHC, "Sel", 0);
			 * 
			 * t1 = new OpersExpr("sub2",
			 * refas.getSemanticExpressionTypes().get( "Equals"),
			 * ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexSGGR,
			 * instVertexHC, t1, 1);
			 * 
			 * t1 = new OpersExpr("MUTEXhardRel", refas
			 * .getSemanticExpressionTypes().get("DoubleImplies"), instVertexHC,
			 * "Sel", true, t1);
			 * 
			 * simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			 * simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			 * semanticExpressions.add(t1);
			 * 
			 * ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
			 * StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex", "",
			 * 1, -1, "", "", -1, "", ""), semanticExpressions));
			 * 
			 * semanticExpressions = new ArrayList<OpersExpr>();
			 * 
			 * t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
			 * "Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
			 * instVertexSGGR, instVertexHC, null, "Sel", "True", true);
			 * 
			 * t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
			 * "GreaterOrEq"), t2, ExpressionVertexType.RIGHTCONCEPTVARIABLE,
			 * instVertexHC, "LowRange");
			 * 
			 * t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
			 * "Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
			 * instVertexSGGR, instVertexHC, null, "Sel", "True", true);
			 * 
			 * t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
			 * "LessOrEquals"), t2, ExpressionVertexType.RIGHTCONCEPTVARIABLE,
			 * instVertexHC, "HighRange");
			 * 
			 * t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
			 * "And"), t1, t3);
			 * 
			 * t1 = new OpersExpr("RANGEHardRel", refas
			 * .getSemanticExpressionTypes().get("Equals"), instVertexSGGR,
			 * "Sel", true, t1);
			 * 
			 * simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			 * simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			 * updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			 * semanticExpressions.add(t1);
			 * 
			 * ias.add(new InstAttribute("range", new ElemAttribute("range",
			 * StringType.IDENTIFIER, AttributeType.OPTION, false, "range", "",
			 * 1, -1, "", "", -1, "", ""), semanticExpressions));
			 */
			// CV to CG
			// semanticVertices = new ArrayList<AbstractSemanticVertex>();
			// semanticVertices.add(semContextGroup);

			OpersPairwiseRel directCVCGSemanticEdge = new OpersPairwiseRel(
					"CVCGPWAsso", false, vcntxPairwiseRelList);
			InstConcept instDirCVCGSemanticEdge = new InstConcept("CVCGPWAsso",
					metaMetaPairwiseRelation, directCVCGSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("svcgtoip", instEdge);
			instEdge.setIdentifier("svcgtoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirCVCGSemanticEdge, true);

			ia = instDirCVCGSemanticEdge.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();

			ias.add(new InstAttribute("Variable Context", new ElemAttribute(
					"Variable Context", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "", "", "", 1, -1, "", "", -1,
					"", ""),
					"Variable Context#Variable Context#false#true#true#1#-1#1#1"));

			ia = instDirCVCGSemanticEdge.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			ias.add(new InstAttribute("Variable Context", new ElemAttribute(
					"Variable Context", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "Variable Context", "", "", 1,
					-1, "", "", -1, "", ""), semanticExpressions));

			refas.getVariabilityVertex().put("CVCGPWAsso",
					instDirCVCGSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("CVCGPWAsso-OOGR", instEdge);
			instEdge.setIdentifier("CVCGPWAsso-OOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexCG, true);
			instEdge.setSourceRelation(instDirCVCGSemanticEdge, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("CVCGPWAsso-OGRO", instEdge);
			instEdge.setIdentifier("CVCGPWAsso-OGRO");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirCVCGSemanticEdge, true);
			instEdge.setSourceRelation(instVertexVAR, true);

			// Oper to Claim
			OpersOverTwoRel semanticOperClaimGroupRelation = new OpersOverTwoRel(
					"OperCLOTAsso", null);// hardSemOverTwoRelList);

			// semanticVertices = new ArrayList<AbstractSemanticVertex>();
			// semanticVertices.add(semClaim);

			InstConcept instVertexCLGR = new InstConcept("OperCLOTAsso",
					semanticOperClaimGroupRelation, metaMetaInstOverTwoRel);

			ia = instVertexCLGR.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""),
					"and#and#true#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""),
					"or#or#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"mutex", "", "", 1, -1, "", "", -1, "", ""),
					"mutex#mutex#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					"range#range#false#true#true#1#-1#1#1"));

			ia = instVertexCLGR.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("ANDhardConcept", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
					instVertexHHGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexOper, "Sel", true, "True");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexCLGR, instVertexOper, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("ORhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
					instVertexHHGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexOper, "Sel", true, "False");

			t1 = new OpersExpr("ORhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexCLGR, instVertexOper, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("MUTEXhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
					instVertexHHGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOper, "Sel", 0);

			t1 = new OpersExpr("sub2", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexCLGR, instVertexOper, t1, 1);

			t1 = new OpersExpr("MUTEXhardRel", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					instVertexHC, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOper, "Sel", 0);

			t1 = new OpersExpr("MUTEXrestr", refas.getSemanticExpressionTypes()
					.get("LessEquals"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexCLGR, instVertexOper, t1, 1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"mutex", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("RANGEhardConcept", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
					instVertexHHGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexCLGR, instVertexOper, null, "Sel", "True", true);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"), t2,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexCLGR,
					"LowRange");

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexCLGR, instVertexOper, null, "Sel", "True", true);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"), t2,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexCLGR,
					"HighRange");

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t3);

			t1 = new OpersExpr("RANGEHardRel", refas
					.getSemanticExpressionTypes().get("Equals"),
					instVertexCLGR, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("OperCLOTAsso", instVertexCLGR);

			OpersPairwiseRel directOperClaimToSemanticEdge = new OpersPairwiseRel(
					"OperClaimToPWAsso", true, operclaimPairwiseRelList);

			InstConcept instDirOperClaimToSemanticEdge = new InstConcept(
					"OperClaimToPWAsso", metaMetaPairwiseRelation,
					directOperClaimToSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ocltoip", instEdge);
			instEdge.setIdentifier("ocltoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirOperClaimToSemanticEdge, true);

			ia = instDirOperClaimToSemanticEdge
					.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
					"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
					"OperToClaim#OperToClaim#true#true#true#1#-1#1#1"));

			ia = instDirOperClaimToSemanticEdge.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexCLGR, instVertexCL, "Sel",
					"ConditionalExpression");

			t1 = new OpersExpr("OPERCLSelected", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instVertexCL, "Sel", true, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("OPERCLNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexCLGR, instVertexCL, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("operToClaim", new ElemAttribute(
					"operToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "operToClaim", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("OperClaimToPWAsso",
					instDirOperClaimToSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("clgrtogr", instEdge);
			instEdge.setIdentifier("clgrtogr");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
			instEdge.setTargetRelation(instVertexGR, true);
			instEdge.setSourceRelation(instVertexCLGR, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("OperClaimToPWAsso-OOGR",
					instEdge);
			instEdge.setIdentifier("OperClaimToPWAsso-OOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexCL, true);
			instEdge.setSourceRelation(instDirOperClaimToSemanticEdge, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("OperClaimToPWAsso-OGRO",
					instEdge);
			instEdge.setIdentifier("OperClaimToPWAsso-OGRO");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirOperClaimToSemanticEdge, true);
			instEdge.setSourceRelation(instVertexCLGR, true);

			OpersPairwiseRel directOperClaimFromSemanticEdge = new OpersPairwiseRel(
					"OperClaimFromPWAsso", true, nonePairwiseRelList);

			InstConcept instDirOperClaimFromSemanticEdge = new InstConcept(
					"OperClaimFromPWAsso", metaMetaPairwiseRelation,
					directOperClaimFromSemanticEdge);
			refas.getVariabilityVertex().put("OperClaimFromPWAsso",
					instDirOperClaimFromSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("oclftoip", instEdge);
			instEdge.setIdentifier("oclftoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirOperClaimFromSemanticEdge, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("OperClaimFromPWAsso-OOGR",
					instEdge);
			instEdge.setIdentifier("OperClaimFromPWAsso-OOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexCLGR, true);
			instEdge.setSourceRelation(instDirOperClaimFromSemanticEdge, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("OperClaimFromPWAsso-OGRO",
					instEdge);
			instEdge.setIdentifier("OperClaimFromPWAsso-OGRO");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirOperClaimFromSemanticEdge, true);
			instEdge.setSourceRelation(instVertexOper, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("OperClaimPWAsso-OOGR", instEdge);
			instEdge.setIdentifier("OperClaimPWAsso-OOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexCL, true);
			instEdge.setSourceRelation(instDirOperClaimSemanticEdge, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("OperClaimPWAsso-OGRO", instEdge);
			instEdge.setIdentifier("OperClaimPWAsso-OGRO");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirOperClaimSemanticEdge, true);
			instEdge.setSourceRelation(instVertexOper, true);

			// LFeat to Claim
			OpersOverTwoRel semanticLFClaimGroupRelation = new OpersOverTwoRel(
					"LFtoClaimOTAsso", null); // hardSemOverTwoRelList);

			InstConcept instVertexLFCLGR = new InstConcept("LFtoClaimOTAsso",
					semanticLFClaimGroupRelation, metaMetaInstOverTwoRel);

			refas.getVariabilityVertex().put("LFtoClaimOTAsso",
					instVertexLFCLGR);

			OpersPairwiseRel directFClaimToSemanticEdge = new OpersPairwiseRel(
					"FClaimToPWAsso", true, operclaimPairwiseRelList);

			InstConcept instDirFClaimToSemanticEdge = new InstConcept(
					"FClaimToPWAsso", metaMetaPairwiseRelation,
					directFClaimToSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("lftcltoip", instEdge);
			instEdge.setIdentifier("lftcltoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirFClaimToSemanticEdge, true);

			ia = instDirFClaimToSemanticEdge.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
					"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
					"OperToClaim#OperToClaim#true#true#true#1#-1#1#1"));

			ia = instDirFClaimToSemanticEdge.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexLFCLGR, instVertexCL, "Sel",
					"ConditionalExpression");

			t1 = new OpersExpr("OPERCLSelected", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instVertexCL, "Sel", true, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("OPERCLNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexLFCLGR, instVertexCL, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
					"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("FClaimToPWAsso",
					instDirFClaimToSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("fctogr", instEdge);
			instEdge.setIdentifier("fctogr");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
			instEdge.setTargetRelation(instVertexGR, true);
			instEdge.setSourceRelation(instVertexLFCLGR, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("FClaimToPWAsso-OOGR", instEdge);
			instEdge.setIdentifier("FClaimToPWAsso-OOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexCL, true);
			instEdge.setSourceRelation(instDirFClaimToSemanticEdge, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("FClaimToPWAsso-OGRO", instEdge);
			instEdge.setIdentifier("FClaimToPWAsso-OGRO");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirFClaimToSemanticEdge, true);
			instEdge.setSourceRelation(instVertexLFCLGR, true);

			OpersPairwiseRel directFClaimFromSemanticEdge = new OpersPairwiseRel(
					"FClaimFromPWAsso", true, nonePairwiseRelList);

			InstConcept instDirFClaimFromSemanticEdge = new InstConcept(
					"FClaimFromPWAsso", metaMetaPairwiseRelation,
					directFClaimFromSemanticEdge);
			refas.getVariabilityVertex().put("FClaimFromPWAsso",
					instDirFClaimFromSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("fclftoip", instEdge);
			instEdge.setIdentifier("fclftoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirFClaimFromSemanticEdge, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("FClaimFromPWAsso-OOGR",
					instEdge);
			instEdge.setIdentifier("FClaimFromPWAsso-OOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexLFCLGR, true);
			instEdge.setSourceRelation(instDirFClaimFromSemanticEdge, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("FClaimFromPWAsso-OGRO",
					instEdge);
			instEdge.setIdentifier("FClaimFromPWAsso-OGRO");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirFClaimFromSemanticEdge, true);
			instEdge.setSourceRelation(instVertexCL, true);

			OpersPairwiseRel directLFClaimSemanticEdge = new OpersPairwiseRel(
					"LFClaimPWAsso", true, operclaimPairwiseRelList);

			InstConcept instDirLFClaimSemanticEdge = new InstConcept(
					"LFClaimPWAsso", metaMetaPairwiseRelation,
					directLFClaimSemanticEdge);
			refas.getVariabilityVertex().put("LFClaimPWAsso",
					instDirLFClaimSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("lfcltoip", instEdge);
			instEdge.setIdentifier("lfcltoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirLFClaimSemanticEdge, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("LFClaimPWAsso-OOGR", instEdge);
			instEdge.setIdentifier("LFClaimPWAsso-OOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexCL, true);
			instEdge.setSourceRelation(instDirLFClaimSemanticEdge, true);

			instEdge = new InstPairwiseRel();
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
			attribute = new ElemAttribute("CLSGLevel", "Integer",
					AttributeType.OPERATION, "Relation Level",
					"Required level for the Claim (0..4)", 2, false,
					new RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
			directClaimSGSemanticEdge.putSemanticAttribute("CLSGLevel",
					attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directClaimSGSemanticEdge.getIdentifier(), attribute
							.getName(), true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					directClaimSGSemanticEdge.getIdentifier(), attribute
							.getName(), true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					directClaimSGSemanticEdge.getIdentifier(), attribute
							.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directClaimSGSemanticEdge.getIdentifier(), attribute
							.getName(), true));

			directClaimSGSemanticEdge.addPropEditableAttribute("08#"
					+ "CLSGLevel");
			directClaimSGSemanticEdge.addPropVisibleAttribute("08#"
					+ "CLSGLevel");
			directClaimSGSemanticEdge.addPanelVisibleAttribute("08#"
					+ "CLSGLevel");
			InstConcept instDirClaimSGSemanticEdge = new InstConcept(
					"ClaimSGPWAsso", metaMetaPairwiseRelation,
					directClaimSGSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("clsgtoip", instEdge);
			instEdge.setIdentifier("clsgtoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirClaimSGSemanticEdge, true);

			ia = instDirClaimSGSemanticEdge.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("ClaimToSG", new ElemAttribute(
					"ClaimToSG", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "Claim To SG", "", "", 1, -1, "", "", -1, "", ""),
					"ClaimToSG#ClaimToSG#true#true#true#1#-1#1#1"));

			ia = instDirClaimSGSemanticEdge.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexCL, "Sel", true, t1);

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirClaimSGSemanticEdge, instVertexSG,
					"satisficingLevel", "low");

			t1 = new OpersExpr("low: ClaimExpLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexCL, "Sel", true, t1);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirClaimSGSemanticEdge, instVertexSG,
					"satisficingLevel", "high");

			t1 = new OpersExpr("high: ClaimExpLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexCL, "Sel", true, t1);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirClaimSGSemanticEdge, instVertexSG,
					"satisficingLevel", "close");

			t1 = new OpersExpr("close: ClaimExpLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("ClaimToSG", new ElemAttribute(
					"ClaimToSG", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "claim To SG", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("ClaimSGPWAsso",
					instDirClaimSGSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("CLSGPWAsso-OOGR", instEdge);
			instEdge.setIdentifier("CLSGPWAsso-OOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexSG, true);
			instEdge.setSourceRelation(instDirClaimSGSemanticEdge, true);

			instEdge = new InstPairwiseRel();
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
			attribute = new ElemAttribute("level", "Integer",
					AttributeType.OPERATION, "Level",
					"Required level for the SD (0..4)", 1, false,
					new RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
			directSDSGSemanticEdge.putSemanticAttribute("level", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSDSGSemanticEdge.getIdentifier(),
					attribute.getName(), true));
			simulOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSDSGSemanticEdge.getIdentifier(),
					attribute.getName(), true));
			simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
					directSDSGSemanticEdge.getIdentifier(),
					attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSDSGSemanticEdge.getIdentifier(),
					attribute.getName(), true));

			directSDSGSemanticEdge.addPropEditableAttribute("08#" + "level");
			directSDSGSemanticEdge.addPropVisibleAttribute("08#" + "level");
			directSDSGSemanticEdge.addPanelVisibleAttribute("08#" + "level");
			InstConcept instDirSDSGSemanticEdge = new InstConcept("SDSGPWAsso",
					metaMetaPairwiseRelation, directSDSGSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sdsgtoip", instEdge);
			instEdge.setIdentifier("sdsgtoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirSDSGSemanticEdge, true);

			ia = instDirSDSGSemanticEdge.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("SD", new ElemAttribute("SD",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "SD",
					"", "", 1, -1, "", "", -1, "", ""),
					"SD#SD#true#true#true#1#-1#1#1"));

			ia = instDirSDSGSemanticEdge.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSDSGSemanticEdge, "SDReqLevel", "level");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexSD, "Sel", true, t1);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirSDSGSemanticEdge, instVertexSG, "satisficingLevel",
					"low");

			t1 = new OpersExpr("low: SDReqLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSDSGSemanticEdge, "SDReqLevel", "level");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexSD, "Sel", true, t1);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirSDSGSemanticEdge, instVertexSG, "satisficingLevel",
					"high");

			t1 = new OpersExpr("high: SDReqLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSDSGSemanticEdge, "SDReqLevel", "level");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexSD, "Sel", true, t1);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirSDSGSemanticEdge, instVertexSG, "satisficingLevel",
					"close");

			t1 = new OpersExpr("close: SDReqLevel", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("SDSelected", refas.getSemanticExpressionTypes()
					.get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
					instVertexCL, instVertexCL, "Sel", "ConditionalExpression");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("SD", new ElemAttribute("SD",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "SD",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			refas.getVariabilityVertex().put("SDSGPWAsso",
					instDirSDSGSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("SDSGPWAsso-OOGR", instEdge);
			instEdge.setIdentifier("SDSGPWAsso-OOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexSG, true);
			instEdge.setSourceRelation(instDirSDSGSemanticEdge, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("SDSGPWAsso-OGRO", instEdge);
			instEdge.setIdentifier("SDSGPWAsso-OGRO");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirSDSGSemanticEdge, true);
			instEdge.setSourceRelation(instVertexSD, true);

			// Asset to Asset

			OpersOverTwoRel semanticAssetAssetOvertwoRel = new OpersOverTwoRel(
					"AssetAssetOTAsso", null);// hardSemOverTwoRelList);

			InstConcept instVertexASSETGR = new InstConcept("AssetAssetOTAsso",
					semanticAssetAssetOvertwoRel, metaMetaInstOverTwoRel);

			refas.getVariabilityVertex().put("AssetAssetOTAsso",
					instVertexASSETGR);

			InstConcept instAssetassetASGR = new InstConcept(
					"AssetAssetToOTAssoPWAsso", metaMetaPairwiseRelation);
			refas.getVariabilityVertex().put("AssetAssetToOTAssoPWAsso",
					instAssetassetASGR);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("agrtogr", instEdge);
			instEdge.setIdentifier("agrtogr");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
			instEdge.setTargetRelation(instVertexGR, true);
			instEdge.setSourceRelation(instVertexASSETGR, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ASSETGRtoassetA-AGR", instEdge);
			instEdge.setIdentifier("ASSETGRtoassetA-AGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexAsset, true);
			instEdge.setSourceRelation(instAssetassetASGR, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("ASSETGRtoassetAGR-GR", instEdge);
			instEdge.setIdentifier("ASSETGRtoassetAGR-GR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instAssetassetASGR, true);
			instEdge.setSourceRelation(instVertexASSETGR, true);

			InstConcept instAssetassetGRAS = new InstConcept(
					"AssetAssetFromOTAssoPWAsso", metaMetaPairwiseRelation);
			refas.getVariabilityVertex().put("AssetAssetFromOTAssoPWAsso",
					instAssetassetGRAS);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("assettoAssetGR-AGR-A", instEdge);
			instEdge.setIdentifier("assettoAssetGR-AGR-A");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instAssetassetGRAS, true);
			instEdge.setSourceRelation(instVertexAsset, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("assettoAssetGR-GR-AGR",
					instEdge);
			instEdge.setIdentifier("assettoAssetGR-GR-AGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexASSETGR, true);
			instEdge.setSourceRelation(instAssetassetGRAS, true);

			// Asset to Oper
			// TODO use list of possible relations
			OpersOverTwoRel semanticAssetOperGroupRelation = new OpersOverTwoRel(
					"AssetOperOTAsso", null);// hardSemOverTwoRelList);

			// semanticVertices = new ArrayList<AbstractSemanticVertex>();
			// semanticVertices.add(semOperationalization);

			InstConcept instVertexOPERGR = new InstConcept("AssetOperOTAsso",
					semanticAssetOperGroupRelation, metaMetaInstOverTwoRel);

			refas.getVariabilityVertex().put("AssetOperOTAsso",
					instVertexOPERGR);

			InstConcept instAssetOperAOGR = new InstConcept(
					"AssetOperToOTAssoPWAsso", metaMetaPairwiseRelation);
			refas.getVariabilityVertex().put("AssetOperToOTAssoPWAsso",
					instAssetOperAOGR);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("opgrtogr", instEdge);
			instEdge.setIdentifier("opgrtogr");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
			instEdge.setTargetRelation(instVertexGR, true);
			instEdge.setSourceRelation(instVertexOPERGR, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("aogrtogr", instEdge);
			instEdge.setIdentifier("aogrtogr");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
			instEdge.setTargetRelation(instVertexGR, true);
			instEdge.setSourceRelation(instAssetOperAOGR, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("assettoOperGR-AAOGR", instEdge);
			instEdge.setIdentifier("assettoOperGR-AAOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instAssetOperAOGR, true);
			instEdge.setSourceRelation(instVertexAsset, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("assettoOperGR-AOGRGR", instEdge);
			instEdge.setIdentifier("assettoOperGR-AOGRGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexOPERGR, true);
			instEdge.setSourceRelation(instAssetOperAOGR, true);

			ia = instVertexOPERGR.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""),
					"and#and#true#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""),
					"or#or#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"mutex", "", "", 1, -1, "", "", -1, "", ""),
					"mutex#mutex#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					"range#range#false#true#true#1#-1#1#1"));

			ia = instVertexOPERGR.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("ANDhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexF,
					instVertexOPERGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, "Sel", true, "True");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexOper, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("ORhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexOper,
					instVertexOPERGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, "Sel", true, "False");

			t1 = new OpersExpr("ORhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexAsset, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("MUTEXhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexOper,
					instVertexOPERGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexAsset, "Sel", 0);

			t1 = new OpersExpr("sub2", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexAsset, t1, 1);

			t1 = new OpersExpr("MUTEXhardRel", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					instVertexF, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexAsset, "Sel", 0);

			t1 = new OpersExpr("MUTEXrestric", refas
					.getSemanticExpressionTypes().get("LessOrEquals"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexAsset, t1, 1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"mutex", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("RANGEhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexOper,
					instVertexOPERGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexAsset, null, "Sel", "True",
					true);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"), t2,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsset,
					"LowRange");

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexAsset, null, "Sel", "True",
					true);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"), t2,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsset,
					"HighRange");

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t3);

			t1 = new OpersExpr("RANGEHardRel", refas
					.getSemanticExpressionTypes().get("Equals"),
					instVertexOPERGR, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			ia = instVertexOPERGR.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""),
					"and#and#true#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""),
					"or#or#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "XOR",
					"", "", 1, -1, "", "", -1, "", ""),
					"mutex#mutex#false#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					"range#range#false#true#true#1#-1#1#1"));

			ia = instVertexOPERGR.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("ANDhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexF,
					instVertexOPERGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, "Sel", true, "True");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexOper, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("ORhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexOper,
					instVertexOPERGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, "Sel", true, "False");

			t1 = new OpersExpr("ORhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexAsset, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("MUTEXhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexOper,
					instVertexOPERGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexAsset, "Sel", 0);

			t1 = new OpersExpr("sub2", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexAsset, t1, 1);

			t1 = new OpersExpr("MUTEXhardRel", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					instVertexF, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexAsset, "Sel", 0);

			t1 = new OpersExpr("MUTEXrestric", refas
					.getSemanticExpressionTypes().get("LessOrEquals"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexAsset, t1, 1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"mutex", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("RANGEhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexOper,
					instVertexOPERGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexAsset, null, "Sel", "True",
					true);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"), t2,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsset,
					"LowRange");

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexAsset, null, "Sel", "True",
					true);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"), t2,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsset,
					"HighRange");

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t3);

			t1 = new OpersExpr("RANGEHardRel", refas
					.getSemanticExpressionTypes().get("Equals"),
					instVertexOPERGR, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			OpersPairwiseRel groupAssetOperSemanticEdge = new OpersPairwiseRel(
					"AssetOperPWAsso", false, assetoperPairwiseRelList);

			InstConcept instAssetOperGRAO = new InstConcept(
					"AssetOperFromoOTAssoPWAsso", metaMetaPairwiseRelation,
					groupAssetOperSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("aofottoip", instEdge);
			instEdge.setIdentifier("aofottoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instAssetOperGRAO, true);

			ia = instAssetOperGRAO.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("implementation", new ElemAttribute(
					"implementation", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "implementation", "", "", 1,
					-1, "", "", -1, "", ""),
					"implementation#implementation#true#true#true#1#-1#1#1"));

			ia = instAssetOperGRAO.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("IMPSelected", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexAsset, instVertexOPERGR, "Sel", "Sel");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("IMPNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexAsset, instVertexOPERGR, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("implementation", new ElemAttribute(
					"implementation", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "implementation", "", "", 1,
					-1, "", "", -1, "", ""), semanticExpressions));

			refas.getVariabilityVertex().put("AssetOperFromOTAssoPWAsso",
					instAssetOperGRAO);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("OPERGRtooper-OOGR", instEdge);
			instEdge.setIdentifier("OPERGRtooper-OOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexOper, true);
			instEdge.setSourceRelation(instAssetOperGRAO, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("OPERGRtooper-OGRO", instEdge);
			instEdge.setIdentifier("OPERGRtooper-OGRO");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instAssetOperGRAO, true);
			instEdge.setSourceRelation(instVertexOPERGR, true);

			OpersPairwiseRel directAssetOperSemanticEdge = new OpersPairwiseRel(
					"AssetOperPWAsso", false, assetoperPairwiseRelList);
			InstConcept instDirAssetOperSemanticEdge = new InstConcept(
					"AssetOperPWAsso", metaMetaPairwiseRelation,
					directAssetOperSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("aoptoip", instEdge);
			instEdge.setIdentifier("aoptoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirAssetOperSemanticEdge, true);

			ia = instDirAssetOperSemanticEdge.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("implementation", new ElemAttribute(
					"implementation", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "implementation", "", "", 1,
					-1, "", "", -1, "", ""),
					"implementation#implementation#true#true#true#1#-1#1#1"));

			ia = instDirAssetOperSemanticEdge.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("IMPSelected", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexOPERGR, instVertexOper, "Sel", "Sel");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("IMPNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexOPERGR, instVertexOper, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("implementation", new ElemAttribute(
					"implementation", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "implementation", "", "", 1,
					-1, "", "", -1, "", ""), semanticExpressions));

			refas.getVariabilityVertex().put("AssetOperPWAsso",
					instDirAssetOperSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("AssetOperPWAsso-OOGR", instEdge);
			instEdge.setIdentifier("AssetOperPWAsso-OOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexOper, true);
			instEdge.setSourceRelation(instDirAssetOperSemanticEdge, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("AssetOperPWAsso-OGRO", instEdge);
			instEdge.setIdentifier("AssetOperPWAsso-OGRO");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirAssetOperSemanticEdge, true);
			instEdge.setSourceRelation(instVertexAsset, true);

			semanticExpressions = new ArrayList<OpersExpr>();

			simsceExecOperLabeling2.setSemanticExpressions(semanticExpressions);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
					instVertexSG, "Sel", 0);

			t1 = new OpersExpr("max soft goals", refas
					.getSemanticExpressionTypes().get("Sum"),
					ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
					instRefasModel, instVertexSG, t1, 0);

			semanticExpressions.add(t1);

			semanticExpressions = new ArrayList<OpersExpr>();

			refasModel.setSemanticExpressions(semanticExpressions);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
					instVertexGE, "NPrefSel", 0);

			t1 = new OpersExpr("prefSel <=1", refas
					.getSemanticExpressionTypes().get("LessOrEquals"),
					ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
					instRefasModel, instVertexGE, t1, 1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			semanticExpressions = new ArrayList<OpersExpr>();

			simulationExecOperUniqueLabeling
					.setSemanticExpressions(semanticExpressions);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
					instVertexGE, "Order", 0);

			// t1 = new SemanticExpression("TotalOrder", refas
			// .getSemanticExpressionTypes().get("Equals"),
			// ExpressionVertexType.LEFTITERCONCEPTVARIABLE, instRefasModel,
			// instVertexGE, t1, "TotalOrder");

			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
					instVertexGE, "Opt", 0);

			// t1 = new SemanticExpression("TotalOpt", refas
			// .getSemanticExpressionTypes().get("Equals"),
			// ExpressionVertexType.LEFTITERCONCEPTVARIABLE, instRefasModel,
			// instVertexGE, t1, "TotalOpt");

			semanticExpressions.add(t1);

			semanticExpressions = new ArrayList<OpersExpr>();

			simsceExecOperLabeling2.setSemanticExpressions(semanticExpressions);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
					instVertexSG, "Sel", 0);

			// t1 = new SemanticExpression("TotalSG", refas
			// .getSemanticExpressionTypes().get("Equals"),
			// ExpressionVertexType.LEFTITERCONCEPTVARIABLE, instRefasModel,
			// instVertexSG, t1, "TotalSG");

			semanticExpressions.add(t1);
		}
	}
}
