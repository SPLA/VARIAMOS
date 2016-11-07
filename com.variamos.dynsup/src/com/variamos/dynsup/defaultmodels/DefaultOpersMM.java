package com.variamos.dynsup.defaultmodels;

import java.util.ArrayList;
import java.util.List;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.LowExpr;
import com.variamos.dynsup.model.ModelExpr;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.OpersExpr;
import com.variamos.dynsup.model.OpersIOAttribute;
import com.variamos.dynsup.model.OpersLabeling;
import com.variamos.dynsup.model.OpersSubOperation;
import com.variamos.dynsup.model.OpersSubOperationExpType;
import com.variamos.dynsup.model.OpersVariable;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.statictypes.SatisficingType;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.ExpressionVertexType;
import com.variamos.dynsup.types.OperationActionType;
import com.variamos.dynsup.types.OperationSubActionDefectsVerifierMethodType;
import com.variamos.dynsup.types.StringType;
import com.variamos.dynsup.types.VariableType;
import com.variamos.hlcl.LabelingOrder;
import com.variamos.hlcl.RangeDomain;
import com.variamos.hlcl.StringDomain;

public class DefaultOpersMM {
	static OpersConcept verifDeadElemOperationAction = null;
	static OpersSubOperationExpType verifDeadElemOperSubActionNormal = null;

	static OpersConcept verifFalseOptOperationAction = null;
	static OpersSubOperationExpType verifFalseOptOperSubActionNormal = null;

	static OpersConcept verifParentsOperationAction = null;
	static OpersSubOperationExpType verifParentsOperSubActionNormal = null;
	static OpersSubOperationExpType verifParentsOperSubActionToVerify = null;

	static OpersConcept verifRootOperationAction = null;
	static OpersSubOperationExpType verifRootOperSubActionNormal = null;
	static OpersSubOperationExpType verifRootOperSubActionRelaxable = null;
	static OpersSubOperationExpType verifRootOperSubActionVerification = null;

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

	static OpersConcept sasverSDallOperationAction = null;
	static OpersConcept sasverClallOperationAction = null;
	static OpersConcept sasverSDneverOperationAction = null;
	static OpersConcept sasverClneverOperationAction = null;
	static OpersConcept sasverAllOpersOperationAction = null;
	static OpersConcept sasverNoLoopsOperationAction = null;
	static OpersConcept sasverSGConflperationAction = null;
	static OpersConcept sasverConflClSDOperationAction = null;
	static OpersConcept sasverConflClOperationAction = null;
	static OpersConcept sasverConflSDOperationAction = null;

	static OpersSubOperationExpType updateCoreOptOperSubActionNormal = null;

	static OpersSubOperationExpType sasverSDallOperSubActionNormal = null;
	static OpersSubOperationExpType sasverClallOperSubActionNormal = null;
	static OpersSubOperationExpType sasverSDneverOperSubActionNormal = null;
	static OpersSubOperationExpType sasverClneverOperSubActionNormal = null;
	static OpersSubOperationExpType sasverAllOpersOperSubActionNormal = null;
	static OpersSubOperationExpType sasverNoLoopsOperSubActionNormal = null;
	static OpersSubOperationExpType sasverSGConflOperSubActionNormal = null;
	static OpersSubOperationExpType sasverConflClSDOperSubActionNormal = null;
	static OpersSubOperationExpType sasverConflClOperSubActionNormal = null;
	static OpersSubOperationExpType sasverConflSDOperSubActionNormal = null;

	static OpersSubOperation simulSubOperationAction = null;
	static OpersSubOperation simSceSubOperationAction = null;
	static OpersSubOperation verifRootSubOperationAction = null;
	static OpersSubOperation verifParentsSubOperationAction = null;
	static OpersSubOperation updateCoreSubOperationAction = null;
	static OpersSubOperation verifFalseOptSubOperationAction = null;
	static OpersSubOperation verifDeadElemSubOperationAction = null;

	static OpersSubOperation sasverSDallOperationSubAction = null;
	static OpersSubOperation sasverClallOperationSubAction = null;
	static OpersSubOperation sasverSDneverOperationSubAction = null;
	static OpersSubOperation sasverClneverOperationSubAction = null;
	static OpersSubOperation sasverAllOpersOperationSubAction = null;
	static OpersSubOperation sasverNoLoopsOperationSubAction = null;
	static OpersSubOperation sasverSGConflOperationSubAction = null;
	static OpersSubOperation sasverConflClSDOperationSubAction = null;
	static OpersSubOperation sasverConflSDOperationSubAction = null;
	static OpersSubOperation sasverConflClOperationSubAction = null;

	static OpersLabeling simulationExecOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling simsceExecOperLabeling1 = new OpersLabeling("all");

	static OpersLabeling simsceExecOperLabeling2 = new OpersLabeling("once"); // TODO
	// define
	// max
	// for SG
	static OpersLabeling updateCoreOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling verifDeadElemOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling verifFalseOptElemOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling sasverSDallOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling sasverClallOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling sasverSDneverOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling sasverClneverOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling sasverAllOpersOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling sasverNoLoopsOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling sasverSGConflOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling sasverConflClSDOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling sasverConflClOperUniqueLabeling = new OpersLabeling(
			"unique");

	static OpersLabeling sasverConflSDOperUniqueLabeling = new OpersLabeling(
			"unique");
	static InstElement metaMetaModel = null;
	static InstElement metaOperationMenu = null;
	static InstElement metaOperationAction = null;
	static InstElement metaOperationSubAction = null;
	static InstElement metaLabeling = null;
	static InstElement metaExpType = null;
	static InstConcept metaMetaInstConcept = null;
	static InstElement metaMetaPairwiseRelation = null;
	static InstConcept metaMetaInstOverTwoRel = null;
	static InstElement infraMetaMetaConcept = null;
	static InstElement infraMetaMetaPairwiseRelation = null;
	static InstElement infraMetaMetaOverTwoRelation = null;
	static InstPairwiseRel metaPairwRelCCExt = null;
	static InstPairwiseRel metaPairwRelOCExt = null;
	static InstPairwiseRel metaPairwRelAso = null;

	public static void createOpersMetaModel(ModelInstance refas, boolean empty) {
		createOpersMetaModelOpers(refas, empty);
		createOpersMetaModelnmElements(refas, empty);
		createOpersMetaModelElements(refas, empty);
	}

	@SuppressWarnings("unchecked")
	private static void createOpersMetaModelOpers(ModelInstance refas,
			boolean empty) {
		metaMetaModel = (refas.getSyntaxModel().getVertex("OMModel"));
		metaOperationMenu = (refas.getSyntaxModel().getVertex("OMOperGroup"));
		metaOperationAction = (refas.getSyntaxModel().getVertex("OMOperation"));
		metaOperationSubAction = (refas.getSyntaxModel().getVertex("OMSubOper"));
		metaLabeling = (refas.getSyntaxModel().getVertex("OMLabeling"));
		metaExpType = (refas.getSyntaxModel().getVertex("OMExpType"));

		// MetaEnumeration metaEnumeration = (MetaEnumeration) ((InstConcept)
		// refas
		// .getSyntaxModel().getVertex("TypeEnumeration"))
		// .getEditableMetaElement();
		metaMetaInstConcept = ((InstConcept) refas.getSyntaxModel().getVertex(
				"OMConcept"));
		metaMetaPairwiseRelation = (refas.getSyntaxModel().getVertex("OMPWRel"));
		metaMetaInstOverTwoRel = ((InstConcept) refas.getSyntaxModel()
				.getVertex("OMOTRel"));

		infraMetaMetaConcept = (refas.getSyntaxModel().getVertex("OMnmConcept"));
		infraMetaMetaPairwiseRelation = (refas.getSyntaxModel()
				.getVertex("OMnmPWRel"));
		infraMetaMetaOverTwoRelation = (refas.getSyntaxModel()
				.getVertex("OMnmOTRel"));

		metaPairwRelCCExt = (refas.getSyntaxModel()
				.getConstraintInstEdge("OMExtCEdge"));
		metaPairwRelOCExt = (refas.getSyntaxModel()

		.getConstraintInstEdge("OMExtOTCEdge")); // FIXME separate OT
													// from OT and OT
													// from C.
													// OMExtOTOTEdge

		metaPairwRelAso = (refas.getSyntaxModel()
				.getConstraintInstEdge("OMAsoEdge"));

		/*
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

		if (!empty) {

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

			simulationOperationAction = new OpersConcept("BasicSimulOper");

			InstConcept instOperationAction = new InstConcept("BasicSimulOper",
					metaOperationAction, simulationOperationAction);
			refas.getVariabilityVertex().put("BasicSimulOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Validation_with_Dashboard.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Start Simulation (Dynamic)");
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
					"BasSim-Pre-Validation");

			// simulationOperationAction.addExpressionSubAction(operationSubAction);

			InstConcept instOperationSubAction = new InstConcept(
					"BasSim-Pre-Validation", metaOperationSubAction,
					operationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Single verification");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("BasSim-Pre-Validation",
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			OpersLabeling operationLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			InstConcept instLabeling = new InstConcept(
					"BasSim-Pre-Validation-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("BasSim-Pre-Validation-lab",
					instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-pre-val-lab", instEdgeOper);
			instEdgeOper.setIdentifier("sim-pre-val-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			operationSubAction = new OpersSubOperation(2, "BasSim-Pre-Update");
			// simulationOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("BasSim-Pre-Update",
					metaOperationSubAction, operationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Single update");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(2);

			refas.getVariabilityVertex().put("BasSim-Pre-Update",
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("BasSim-pre-update-lab",
					metaLabeling, operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("BasSim-pre-update-lab",
					instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-pre-upd-lab", instEdgeOper);
			instEdgeOper.setIdentifier("sim-pre-upd-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			simulSubOperationAction = new OpersSubOperation(3,
					"BasSim-Execution");

			List<OpersExpr> semanticExpressions = new ArrayList<OpersExpr>();

			simulationExecOperUniqueLabeling
					.setSemanticExpressions(semanticExpressions);

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

			// simulationOperationAction
			// .addExpressionSubAction(simulOperationSubAction);

			instOperationSubAction = new InstConcept("BasSim-Execution",
					metaOperationSubAction, simulSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Simulation Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent. "
									+ "\n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");

			instOperationSubAction.getInstAttribute("type").setValue(
					"Iterative update");
			instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("Index").setValue(3);

			refas.getVariabilityVertex().put("BasSim-Execution",
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			// simulOperationSubAction
			// .addOperationLabeling(simulationExecOperUniqueLabeling);

			instLabeling = new InstConcept("BasSim-Execution-lab",
					metaLabeling, simulationExecOperUniqueLabeling);

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

			refas.getVariabilityVertex().put("BasSim-Execution-lab",
					instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("bassim-execution-lab",
					instEdgeOper);
			instEdgeOper.setIdentifier("bassim-execution-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			OpersLabeling simulationExecOperLowLabeling = null;
			simulationExecOperLowLabeling = new OpersLabeling("lowlab");

			instLabeling = new InstConcept("BasSim-Execution-lowLab",
					metaLabeling, simulationExecOperLowLabeling);
			instLabeling.getInstAttribute("includeLabel").setValue(false);
			instLabeling.getInstAttribute("labelId").setValue("L2");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("BasSim-Execution-lowLab",
					instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("bassim-execution-lowlab",
					instEdgeOper);
			instEdgeOper.setIdentifier("bassim-execution-lowlab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			simulationExecOperLowLabeling = new OpersLabeling("lowlab");

			instLabeling = new InstConcept("BasSim-Execution-lowLab",
					metaLabeling, simulationExecOperLowLabeling);
			instLabeling.getInstAttribute("includeLabel").setValue(false);
			instLabeling.getInstAttribute("labelId").setValue("L2");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("BasSim-Execution-lowLab",
					instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("bassim-execution-lowlab",
					instEdgeOper);
			instEdgeOper.setIdentifier("bassim-execution-lowlab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			operationSubAction = new OpersSubOperation(4,
					"BasSim-Post-Validation");

			// simulationOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("BasSim-Post-Validation",
					metaOperationSubAction, operationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Single verification");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			refas.getVariabilityVertex().put("BasSim-Post-Validation",
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// simulOperationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("BasSim-pos-val-lab", metaLabeling,
					operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex()
					.put("BasSim-pos-val-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-pos-val-lab", instEdgeOper);
			instEdgeOper.setIdentifier("sim-pos-val-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			operationSubAction = new OpersSubOperation(5, "BasSim-Post-Update");

			// simulationOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("BasSim-Post-Update",
					metaOperationSubAction, operationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Single update");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(5);

			refas.getVariabilityVertex().put("BasSim-Post-Update",
					instOperationSubAction);

			simulationPostUpdOptOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, simulationPostUpdOptOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			operationLabeling = new OpersLabeling("unique");

			// simulOperationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("BasSim-Post-Update-lab",
					metaLabeling, operationLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("BasSim-Post-Update-lab",
					instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("bassim-post-update-lab",
					instEdgeOper);
			instEdgeOper.setIdentifier("bassim-post-update-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			operationMenu = new OpersConcept("SimulSCeOper");

			instOperationGroup = new InstConcept("SimulSceGroup",
					metaOperationMenu, operationMenu);
			refas.getVariabilityVertex().put("SimulSceGroup",
					instOperationGroup);

			instOperationGroup.getInstAttribute("visible").setValue(true);
			instOperationGroup.getInstAttribute("menuType").setValue("4");
			instOperationGroup.getInstAttribute("name").setValue(
					"Simulation Scenarios  (Dynamic)");
			instOperationGroup.getInstAttribute("shortcut").setValue("C");
			instOperationGroup.getInstAttribute("Index").setValue(1);

			simulScenOperationAction = new OpersConcept("SimulSceOper");

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-pos-upd", instEdgeOper);
			instEdgeOper.setIdentifier("sim-pos-upd");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			instOperationAction = new InstConcept("SimulSceOper",
					metaOperationAction, simulScenOperationAction);
			refas.getVariabilityVertex().put("SimulSceOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Validation_with_Dashboard.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Start Simulation (Dynamic)");
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
			instOperationSubAction.getInstAttribute("name").setValue(" ");

			instOperationSubAction.getInstAttribute("type").setValue(
					"Single verification");
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

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
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Single update");
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

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

			simSceSubOperationAction = new OpersSubOperation(3,
					"SimSce-Execution");
			// simulScenOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("SimSce-Execution",
					metaOperationSubAction, simSceSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Simulation Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent. "
									+ "\n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Iterative update");
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

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
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Single verification");
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

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
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Single update");
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

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

			// ------------------------------------------------------
			operationMenu = new OpersConcept("SAS Verification");

			instOperationGroup = new InstConcept("SAS Verification",
					metaOperationMenu, operationMenu);
			refas.getVariabilityVertex().put("SAS Verification",
					instOperationGroup);

			instOperationGroup.getInstAttribute("visible").setValue(true);
			instOperationGroup.getInstAttribute("menuType").setValue("2");
			instOperationGroup.getInstAttribute("name").setValue(
					"SAS Verification");
			instOperationGroup.getInstAttribute("shortcut").setValue("A");

			sasverSDallOperationAction = new OpersConcept("SDAlwaysActivated");

			instOperationAction = new InstConcept("SDAlwaysActivated",
					metaOperationAction, sasverSDallOperationAction);
			refas.getVariabilityVertex().put("SDAlwaysActivated",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"SDs Always Activated");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sasver-sd-all", instEdgeOper);
			instEdgeOper.setIdentifier("sasver-sd-all");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverSDallOperationSubAction = new OpersSubOperation(1,
					"SDAlwaysActivatedSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept(
					"SDAlwaysActivatedSubOper", metaOperationSubAction,
					sasverSDallOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("SDAlwaysActivatedSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sd-all", instEdgeOper);
			instEdgeOper.setIdentifier("sd-all");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverSDallOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverSDallOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverSDallOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("sd-all-lab", metaLabeling,
					sasverSDallOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("sd-all-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sd-all-lab", instEdgeOper);
			instEdgeOper.setIdentifier("sd-all-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverClallOperationAction = new OpersConcept("ClAlwaysActivated");

			instOperationAction = new InstConcept("ClAlwaysActivated",
					metaOperationAction, sasverClallOperationAction);
			refas.getVariabilityVertex().put("ClAlwaysActivated",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Claims Always Activated");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sasver-cl-all", instEdgeOper);
			instEdgeOper.setIdentifier("sasver-cl-all");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverClallOperationSubAction = new OpersSubOperation(1,
					"ClAlwaysActivatedSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept(
					"ClAlwaysActivatedSubOper", metaOperationSubAction,
					sasverClallOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("ClAlwaysActivatedSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("cl-all", instEdgeOper);
			instEdgeOper.setIdentifier("cl-all");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverClallOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverClallOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverClallOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("cl-all-lab", metaLabeling,
					sasverClallOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("cl-all-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("cl-all-lab", instEdgeOper);
			instEdgeOper.setIdentifier("cl-all-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverSDneverOperationAction = new OpersConcept("SDneverActivated");

			instOperationAction = new InstConcept("SDneverActivated",
					metaOperationAction, sasverSDneverOperationAction);
			refas.getVariabilityVertex().put("SDneverActivated",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"SDs Never Activated");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sasver-sd-never", instEdgeOper);
			instEdgeOper.setIdentifier("sasver-sd-never");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverSDneverOperationSubAction = new OpersSubOperation(1,
					"SDneverActivatedSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("SDneverActivatedSubOper",
					metaOperationSubAction, sasverSDneverOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("SDneverActivatedSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sd-never", instEdgeOper);
			instEdgeOper.setIdentifier("sd-never");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverSDneverOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverSDneverOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverSDneverOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("cl-all-lab", metaLabeling,
					sasverSDneverOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("sd-never-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sd-never-lab", instEdgeOper);
			instEdgeOper.setIdentifier("sd-never-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverClneverOperationAction = new OpersConcept("ClneverActivated");

			instOperationAction = new InstConcept("ClneverActivated",
					metaOperationAction, sasverClneverOperationAction);
			refas.getVariabilityVertex().put("ClneverActivated",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Claims Never Activated");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sasver-cl-never", instEdgeOper);
			instEdgeOper.setIdentifier("sasver-cl-never");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverClneverOperationSubAction = new OpersSubOperation(1,
					"ClneverActivatedSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("ClneverActivatedSubOper",
					metaOperationSubAction, sasverClneverOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("ClneverActivatedSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("cl-never", instEdgeOper);
			instEdgeOper.setIdentifier("cl-never");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverClneverOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverClneverOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverClneverOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("cl-all-lab", metaLabeling,
					sasverClneverOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("cl-never-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("cl-never-lab", instEdgeOper);
			instEdgeOper.setIdentifier("cl-never-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverAllOpersOperationAction = new OpersConcept(
					"AllOpersActivated");

			instOperationAction = new InstConcept("AllOpersActivated",
					metaOperationAction, sasverAllOpersOperationAction);
			refas.getVariabilityVertex().put("AllOpersActivated",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"All Operationalizations Activated");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("sasver-all-opers", instEdgeOper);
			instEdgeOper.setIdentifier("sasver-all-opers");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverAllOpersOperationSubAction = new OpersSubOperation(1,
					"AllOpersActivatedSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept(
					"AllOpersActivatedSubOper", metaOperationSubAction,
					sasverAllOpersOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("AllOpersActivatedSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("all-opers", instEdgeOper);
			instEdgeOper.setIdentifier("all-opers");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverAllOpersOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverAllOpersOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverAllOpersOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("cl-all-lab", metaLabeling,
					sasverAllOpersOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("all-opers-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("all-opers-lab", instEdgeOper);
			instEdgeOper.setIdentifier("all-opers-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverNoLoopsOperationAction = new OpersConcept(
					"NoLoopsStructuralRelations");

			instOperationAction = new InstConcept("NoLoopsStructuralRelations",
					metaOperationAction, sasverNoLoopsOperationAction);
			refas.getVariabilityVertex().put("NoLoopsStructuralRelations",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"No Loops in Struct. Rel.");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sasver-no-loops", instEdgeOper);
			instEdgeOper.setIdentifier("sasver-no-loops");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverNoLoopsOperationSubAction = new OpersSubOperation(1,
					"NoLoopsStructuralRelationsSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept(
					"NoLoopsStructuralRelationsSubOper",
					metaOperationSubAction, sasverNoLoopsOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex()
					.put("NoLoopsStructuralRelationsSubOper",
							instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("no-loops", instEdgeOper);
			instEdgeOper.setIdentifier("no-loops");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverNoLoopsOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverNoLoopsOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverNoLoopsOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("no-loops-lab", metaLabeling,
					sasverNoLoopsOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("no-loops-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("no-loops-lab", instEdgeOper);
			instEdgeOper.setIdentifier("no-loops-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverSGConflperationAction = new OpersConcept("SGConflicts");

			instOperationAction = new InstConcept("SGConflicts",
					metaOperationAction, sasverSGConflperationAction);
			refas.getVariabilityVertex()
					.put("SGConflicts", instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Softgoals Contribution Conflicts");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sasver-sg-confl", instEdgeOper);
			instEdgeOper.setIdentifier("sasver-sg-confl");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverSGConflOperationSubAction = new OpersSubOperation(1,
					"SGConflictsSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("SGConflictsSubOper",
					metaOperationSubAction, sasverSGConflOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("SGConflictsSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sg-confl", instEdgeOper);
			instEdgeOper.setIdentifier("sg-confl");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverSGConflOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverSGConflOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverSGConflOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("confl-cl-sd-lab", metaLabeling,
					sasverSGConflOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("confl-cl-sd-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("confl-cl-sd-lab", instEdgeOper);
			instEdgeOper.setIdentifier("confl-cl-sd-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverConflClOperationAction = new OpersConcept("ConflictCl");

			instOperationAction = new InstConcept("ConflictCl",
					metaOperationAction, sasverConflClSDOperationAction);
			refas.getVariabilityVertex().put("ConflictCl", instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Conflicts with Claims");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sasver-confl-cl", instEdgeOper);
			instEdgeOper.setIdentifier("sasver-confl-cl");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverConflClOperationSubAction = new OpersSubOperation(1,
					"ConflictClSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("ConflictClSubOper",
					metaOperationSubAction, sasverConflClOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("ConflictClSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("confl-cld", instEdgeOper);
			instEdgeOper.setIdentifier("confl-cl");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverConflClOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverConflClOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverConflClOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("confl-cl-lab", metaLabeling,
					sasverConflClOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("confl-cl-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("confl-cl-lab", instEdgeOper);
			instEdgeOper.setIdentifier("confl-cl-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverConflClSDOperationAction = new OpersConcept("ConflictClSD");

			instOperationAction = new InstConcept("ConflictClSD",
					metaOperationAction, sasverConflClSDOperationAction);
			refas.getVariabilityVertex().put("ConflictClSD",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Conflicts with Claims & SDs");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sasver-confl-clsd",
					instEdgeOper);
			instEdgeOper.setIdentifier("sasver-confl-clsd");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverConflClSDOperationSubAction = new OpersSubOperation(1,
					"ConflictClSDSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("ConflictClSDSubOper",
					metaOperationSubAction, sasverConflClSDOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("ConflictClSDSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("confl-cl-sd", instEdgeOper);
			instEdgeOper.setIdentifier("confl-cl-sd");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverConflClSDOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverConflClSDOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverConflClSDOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("sg-confl-lab", metaLabeling,
					sasverConflClSDOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("confl-cl-sd-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("confl-cl-sd-lab", instEdgeOper);
			instEdgeOper.setIdentifier("confl-cl-sd-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverConflSDOperationAction = new OpersConcept("ConflictSD");

			instOperationAction = new InstConcept("ConflictSD",
					metaOperationAction, sasverConflSDOperationAction);
			refas.getVariabilityVertex().put("ConflictSD", instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Conflicts with SDs");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sasver-confl-sd", instEdgeOper);
			instEdgeOper.setIdentifier("sasver-confl-sd");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverConflSDOperationSubAction = new OpersSubOperation(1,
					"ConflictSDSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("ConflictSDSubOper",
					metaOperationSubAction, sasverConflSDOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("Index").setValue(1);

			refas.getVariabilityVertex().put("ConflictSDSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("confl-sd", instEdgeOper);
			instEdgeOper.setIdentifier("confl-sd");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverConflSDOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverConflSDOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverConflSDOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("confl-sd-lab", metaLabeling,
					sasverConflSDOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("confl-sd-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("confl-sd-lab", instEdgeOper);
			instEdgeOper.setIdentifier("confl-sd-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ------------------------------------------------------

			operationMenu = new OpersConcept("Verification");

			instOperationGroup = new InstConcept("Verification",
					metaOperationMenu, operationMenu);
			refas.getVariabilityVertex()
					.put("Verification", instOperationGroup);

			instOperationGroup.getInstAttribute("visible").setValue(true);
			instOperationGroup.getInstAttribute("menuType").setValue("2");
			instOperationGroup.getInstAttribute("name")
					.setValue("Verification");
			instOperationGroup.getInstAttribute("shortcut").setValue("V");

			updateCoreOperationAction = new OpersConcept("UpdateCoreOper");

			instOperationAction = new InstConcept("UpdateCoreOper",
					metaOperationAction, updateCoreOperationAction);
			refas.getVariabilityVertex().put("UpdateCoreOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
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

			updateCoreSubOperationAction = new OpersSubOperation(1,
					"UpdateCoreSubOper");
			// updateCoreOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("UpdateCoreSubOper",
					metaOperationSubAction, updateCoreSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Update Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier update");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Core");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(true);
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			updateCoreOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Upd-core-lab", metaLabeling,
					updateCoreOperUniqueLabeling);

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
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
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

			verifDeadElemSubOperationAction = new OpersSubOperation(1,
					"VerifyDeadElementsSubOper");
			// verifDeadElemOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept(
					"VerifyDeadElementsSubOper", metaOperationSubAction,
					verifDeadElemSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"Dead element.");
			instOperationSubAction.getInstAttribute("errorMsg").setValue(
					" #number# dead elements identified.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getDeadElements
									.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("UpdateCoreOper");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			instLabeling = new InstConcept("Ver-dead-lab", metaLabeling,
					verifDeadElemOperUniqueLabeling);

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
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
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

			verifParentsSubOperationAction = new OpersSubOperation(1,
					"VerifyParentsSubOper");
			// verifParentsOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("VerifyParentsSubOper",
					metaOperationSubAction, verifParentsSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"This concept requires a parent.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"#number# concepts without a parent or with more than one parent identified.\n Please add/remove appropiated relations.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getRedundancies
									.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("UpdateCoreOper");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			verifParentsOperSubActionToVerify = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifParentsOperSubActionToVerify);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("TOVERIFY");

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
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
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

			verifRootSubOperationAction = new OpersSubOperation(1,
					"VerifyRootsSubOper");
			// verifRootOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("VerifyRootsSubOper",
					metaOperationSubAction, verifRootSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction
					.getInstAttribute("errorHint")
					.setValue(
							"This is a root concept. More than one root concepts identified.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"#number# roots identified.\n Please keep only one of them.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Multi verification");
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			verifRootOperSubActionRelaxable = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifRootOperSubActionRelaxable);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("RELAXABLE");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			verifRootOperSubActionVerification = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifRootOperSubActionVerification);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("VERIFICATION");

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
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
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

			verifFalseOptSubOperationAction = new OpersSubOperation(1,
					"VerifyFalseSubOperations");

			instOperationSubAction = new InstConcept(
					"VerifyFalseSubOperations", metaOperationSubAction,
					verifFalseOptSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"This is a false optional concept.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review required attributes and relations. #number# false optional concept(s) identified.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("UpdateCoreOper");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

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
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Configure.toString());
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
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Configuration Error");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Configuration Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last change on the configuration makes the model "
									+ "\n inconsistent. Please review the selection and "
									+ "try again. \nAttributes values were not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Single update");
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

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
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Configure.toString());
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
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Configuration Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last change on the configuration makes the model "
									+ "\n inconsistent. Please review the selection and "
									+ "try again. \nAttributes values were not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Single update");
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

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

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
	}

	private static InstConcept instVertexIE = null;
	private static InstConcept instInfraPair = null;
	private static InstConcept instVertexGR = null;
	private static InstConcept instVertexVAR = null;
	private static InstConcept instVertexCG = null;

	private static void createOpersMetaModelnmElements(ModelInstance refas,
			boolean empty) {
		// FIXED concept's definition
		ElemAttribute attribute = null;

		OpersConcept semInfraMConcept = new OpersConcept("nmMetaConcept");

		instVertexIE = new InstConcept("nmMetaConcept", infraMetaMetaConcept,
				semInfraMConcept);

		attribute = new ElemAttribute("TrueVal", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***True***", "", true,
				2, -1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("TrueVal", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}
		attribute = new ElemAttribute("FalseVal", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***NotSelected***", "",
				false, 2, -1, "", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("FalseVal", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}
		ElemAttribute attributeSel = new ElemAttribute("Sel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", "",
				false, 2, 1, "false", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Sel", attributeSel);
		semInfraMConcept.addPropVisibleAttribute("01#" + "Sel");

		if (!empty) {
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraMConcept.getIdentifier(), attributeSel.getName(),
					true));
			simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraMConcept.getIdentifier(), attributeSel.getName(),
					true));
			simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraMConcept.getIdentifier(), attributeSel.getName(),
					true));
			verifDeadElemSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attributeSel.getName(), true));
			verifDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraMConcept.getIdentifier(), attributeSel.getName(),
					true));
			verifFalseOptSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attributeSel.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attributeSel.getName(), true));
		}
		attribute = new ElemAttribute("Exclu", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Excluded***", "",
				false, 2, 2, "false", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Exclu", attribute);
		semInfraMConcept.addPropVisibleAttribute("02#" + "Exclu");

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			// simsceExecOperLabeling2
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}
		attribute = new ElemAttribute("Description", "String",
				AttributeType.OPERATION, false, "Description", "", "", 0, -1,
				"", "", -1, "", "");
		// TODO move to syntax

		attribute = new ElemAttribute("Active", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Is Active",
				"Currently not used by dynamic operations", true, 0, 1,
				"false", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Active", attribute);
		semInfraMConcept.addPropVisibleAttribute("01#" + "Active");

		// attribute = new ElemAttribute("Visibility", "Boolean",
		// AttributeType.GLOBALCONFIG, false, "Is Visible", "", true, 0,
		// 2, "false", "", -1, "", "");
		// semInfraMConcept.putSemanticAttribute("Visibility", attribute);
		// semInfraMConcept.addPropVisibleAttribute("02#" + "Visibility");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		// attribute = new ElemAttribute("Allowed", "Boolean",
		// AttributeType.GLOBALCONFIG, true, "Is Allowed", "", true, 0,
		// -1, "", "", -1, "", "");
		// semInfraMConcept.putSemanticAttribute("Allowed", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);

		attribute = new ElemAttribute("ConfSel", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Configuration Selected",
				"Manually/Implication selected for this configuration", false,
				2, 15, "Active" + "#==#" + "true" + "#" + "false", "Core"
						+ "#==#" + "false#false", -1, "", "");
		semInfraMConcept.putSemanticAttribute("ConfSel", attribute);
		semInfraMConcept.addPropVisibleAttribute("15#" + "ConfSel" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		semInfraMConcept.addPropEditableAttribute("15#" + "ConfSel" + "#"
				+ "Core" + "#==#" + "false" + "#" + "false");

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			// simsceExecOperLabeling2
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("ConfNotSel", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Configuration Not Selected",
				"Manually/Implication not selected for this configuration",
				false, 2, 16, "Dead" + "#==#" + "false" + "#" + "false",
				"Active" + "#==#" + "true" + "#" + "false", -1, "", "");
		semInfraMConcept.putSemanticAttribute("ConfNotSel", attribute);
		semInfraMConcept.addPropEditableAttribute("16#" + "ConfNotSel" + "#"
				+ "Dead" + "#==#" + "false" + "#" + "false");
		semInfraMConcept.addPropVisibleAttribute("16#" + "ConfNotSel" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Proh", "Boolean",
				AttributeType.OPERATION, true, "Prohibited",
				"Manually prohibited (exluded) by design", false, 0, 8, "", "",
				-1, "", "");
		semInfraMConcept.putSemanticAttribute("Proh", attribute);
		semInfraMConcept.addPropVisibleAttribute("08#" + "Proh");
		semInfraMConcept.addPropEditableAttribute("08#" + "Proh");

		if (!empty) {
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Required", "Boolean",
				AttributeType.OPERATION, true, "Is Required",
				"Manually defined as required", false, 2, 4, "", "", -1, "", "");

		semInfraMConcept.putSemanticAttribute("Required", attribute);
		semInfraMConcept.addPropEditableAttribute("04#" + "Required");
		semInfraMConcept.addPropVisibleAttribute("04#" + "Required");

		if (!empty) {
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Core", "Boolean",
				AttributeType.OPERATION, false, "Is a Core Concept",
				"Core element defined by the core update operation", false, 2,
				7, "false", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Core", attribute);
		semInfraMConcept.addPropVisibleAttribute("07#" + "Core");

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Dead", "Boolean",
				AttributeType.OPERATION, false, "Is a Dead Concept",
				"Dead element defined by core update operation", false, 2, 8,
				"false", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("Dead", attribute);

		semInfraMConcept.addPropVisibleAttribute("08#" + "Dead");

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("NReqSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected by simulation",
				"Selected for this solution (with or without constraint)",
				false, 0, 5, "false", "", -1, "", "");
		semInfraMConcept.putSemanticAttribute("NReqSel", attribute);
		semInfraMConcept.addPropVisibleAttribute("05#" + "NReqSel");

		if (!empty) {
			simulationExecOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2
					.addAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraMConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("NNotSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Not selected(inactive)", "", false, 0, 4, "false", "", -1, "",
				"");
		semInfraMConcept.putSemanticAttribute("NNotSel", attribute);
		semInfraMConcept.addPropVisibleAttribute("04#" + "NNotSel");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addOutVariable(attribute);

		attribute = new ElemAttribute("DBVis", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Visible on Dashboard",
				"Display element on simulation dashboard", true, 0, 3, "", "",
				-1, "", "");
		semInfraMConcept.putSemanticAttribute("DBVis", attribute);
		semInfraMConcept.addPropEditableAttribute("03#" + "DBVis");
		semInfraMConcept.addPropVisibleAttribute("03#" + "DBVis");

		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("exportOnConfig", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Include in XLS export",
				"Export element in XLS solutions file", true, 0, 4, "", "", -1,
				"", "");
		semInfraMConcept.putSemanticAttribute("exportOnConfig", attribute);
		semInfraMConcept.addPropEditableAttribute("04#" + "exportOnConfig");
		semInfraMConcept.addPropVisibleAttribute("04#" + "exportOnConfig");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		refas.getVariabilityVertex().put("nmMetaConcept", instVertexIE);

		OpersConcept semInfraOTRel = new OpersConcept("nmMetaOTRel");

		/*
		 * semGeneralGroup.putSemanticAttribute("Sel", new ElemAttribute("Sel",
		 * "Boolean", AttributeType.EXECCURRENTSTATE, false, "***Selected***",
		 * false, 2, -1, "", "", -1, "", "")); semGeneralGroup
		 * .putSemanticAttribute("Exclu", new ElemAttribute("Exclu", "Boolean",
		 * AttributeType.EXECCURRENTSTATE, false, "***Not Avaliable***", false,
		 * 2, -1, "", "", -1, "", ""));
		 */
		instVertexGR = new InstConcept("nmMetaOTRel",
				infraMetaMetaOverTwoRelation, semInfraOTRel);

		refas.getVariabilityVertex().put("nmMetaOTRel", instVertexGR);

		attribute = new ElemAttribute("TrueVal", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", "",
				true, 2, -1, "", "", -1, "", "");
		semInfraOTRel.putSemanticAttribute("TrueVal", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("FalseVal", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***NotSelected***", "",
				false, 2, -1, "", "", -1, "", "");
		semInfraOTRel.putSemanticAttribute("FalseVal", attribute);

		if (!empty) {
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
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
			simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
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
			updateCoreSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
		}
		semInfraOTRel.putSemanticAttribute("Core", attribute);

		if (!empty) {
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
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
			simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
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
				InstAttribute.class.getCanonicalName(), null, null, 0, 6, "",
				"", 6, "#relationType#all#", ""));
		semInfraOTRel.addPropEditableAttribute("06#" + "relationType");
		semInfraOTRel.addPropVisibleAttribute("06#" + "relationType");
		// semInfraOTRel.addPanelVisibleAttribute("06#" + "relationType");
		// semInfraOTRel.addPanelSpacersAttribute("#" + "relationType" + "#");

		attribute = new ElemAttribute("LowRange", "Integer",
				AttributeType.OPERATION, "Low Range",
				"Low value for range relation type", 1, false, new RangeDomain(
						0, 50, 0), 0, 8, "", "relationType" + "#==#" + "range"
						+ "#" + "1", 8, " [#" + "LowRange" + "#all#",
				"relationType" + "#==#" + "range");

		semInfraOTRel.putSemanticAttribute("LowRange", attribute);
		semInfraOTRel.addPropEditableAttribute("08#" + "LowRange");
		semInfraOTRel.addPropVisibleAttribute("08#" + "LowRange" + "#"
				+ "relationType" + "#==#" + "range" + "#" + "1");
		// semInfraOTRel.addPanelVisibleAttribute("08#" + "LowRange" + "#"
		// + "relationType" + "#==#" + "range");
		// semInfraOTRel.addPanelSpacersAttribute(" [#" + "LowRange" + "#");

		if (!empty) {
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("HighRange", "Integer",
				AttributeType.OPERATION, "High Range",
				"High value for range relation type", 1, false,
				new RangeDomain(0, 50, 0), 0, 9, "", "relationType" + "#==#"
						+ "range" + "#" + "1", 9,
				"-#" + "HighRange" + "#all#]", "relationType" + "#==#"
						+ "range");

		semInfraOTRel.putSemanticAttribute("HighRange", attribute);
		semInfraOTRel.addPropEditableAttribute("09#" + "HighRange");
		semInfraOTRel.addPropVisibleAttribute("09#" + "HighRange" + "#"
				+ "relationType" + "#==#" + "range" + "#" + "1");

		// semInfraOTRel.addPanelVisibleAttribute("09#" + "HighRange" + "#"
		// + "relationType" + "#==#" + "range");
		// semInfraOTRel.addPanelSpacersAttribute("-#" + "HighRange" + "#]");

		if (!empty) {
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
		}

		OpersConcept semGeneralPair = new OpersConcept("nmMetaPWRel");
		instInfraPair = new InstConcept("nmMetaPWRel",
				infraMetaMetaPairwiseRelation, semGeneralPair);

		attribute = new ElemAttribute(InstPairwiseRel.VAR_METAPAIRWISE,
				"Class", AttributeType.OPERATION, true,
				InstPairwiseRel.VAR_METAPAIRWISE_NAME, "",
				InstPairwiseRel.VAR_METAPAIRWISE_CLASS, new SyntaxElement('P'),
				0, 2, "", "", -1, "", "");

		semGeneralPair.putSemanticAttribute(InstPairwiseRel.VAR_METAPAIRWISE,
				attribute);

		verifParentsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGeneralPair.getIdentifier(), attribute.getName(), true));

		semGeneralPair.putSemanticAttribute("relationType", new ElemAttribute(
				"relationType", "Class", AttributeType.OPERATION, true,
				"Relation Type", "Type of pairwise relation",
				InstAttribute.class.getCanonicalName(), null, "", 0, 6, "", "",
				6, "#" + "relationType" + "#all#\n", ""));
		semGeneralPair.addPropEditableAttribute("06#" + "relationType");
		semGeneralPair.addPropVisibleAttribute("06#" + "relationType");
		// semGeneralPair.addPanelVisibleAttribute("06#" + "relationType");
		// semGeneralPair.addPanelSpacersAttribute("#" + "relationType" +
		// "#\n");

		refas.getVariabilityVertex().put("nmMetaPWRel", instInfraPair);

		OpersVariable semVariable = new OpersVariable("nmVariable");

		// simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(semVariable
		// .getIdentifier(), "Exclu", true));

		ArrayList<OpersExpr> semanticExpressions = new ArrayList<OpersExpr>();

		semVariable.setSemanticExpressions(semanticExpressions);

		instVertexVAR = new InstConcept("nmVariable", infraMetaMetaConcept,
				semVariable);

		OpersExpr t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexVAR, instVertexVAR, "varConfValue",
				"value");

		OpersExpr t2 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexVAR, "isConfDom", true, 1);

		t1 = new OpersExpr("varConfigVal=value=varConfigDomain", refas
				.getSemanticExpressionTypes().get("Implies"), t2, t1);

		semanticExpressions.add(t1);
		simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
		simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
		// verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// verifRootOperSubActionNormal.addSemanticExpression(t1);

		attribute = new ElemAttribute("DBVis", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Visible on Dashboard", "",
				true, 0, 3, "", "", -1, "", "");
		semVariable.putSemanticAttribute("DBVis", attribute);
		semVariable.addPropEditableAttribute("03#" + "DBVis");
		semVariable.addPropVisibleAttribute("03#" + "DBVis");

		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("exportOnConfig", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Export on Configuration",
				"", true, 0, 4, "", "", -1, "", "");
		semVariable.putSemanticAttribute("exportOnConfig", attribute);
		semVariable.addPropEditableAttribute("04#" + "exportOnConfig");
		semVariable.addPropVisibleAttribute("04#" + "exportOnConfig");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("Scope", "Boolean",
				AttributeType.OPERATION, true, "Global Scope",
				"Global or Concern Level scope (Ignored for operations)", true,
				0, 5, "", "", -1, "", "");
		semVariable.putSemanticAttribute("Scope", attribute);

		semVariable.addPropEditableAttribute("05#" + "Scope");
		semVariable.addPropVisibleAttribute("05#" + "Scope");
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

				InstConcept.class.getCanonicalName(), "CG", null, "", 0, 6, "",
				"Scope" + "#==#" + "false" + "#" + "", 0, "<<#"
						+ "ConcernLevel" + "#all#>>\n", "Scope" + "#==#"
						+ "false");

		semVariable.putSemanticAttribute("ConcernLevel", attribute);
		semVariable.addPropEditableAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");
		semVariable.addPropVisibleAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addInVariable(attribute);
		// TODO: use concern level
		// semVariable.addPanelVisibleAttribute("00#" + "ConcernLevel" + "#"
		// + "Scope" + "#==#" + "false");
		// semVariable.addPanelSpacersAttribute("<<#" + "ConcernLevel" +
		// "#>>\n");

		attribute = new ElemAttribute("name", "String",
				AttributeType.OPERATION, false, "Name",
				"Name to identify the variable", "", 0, 1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("name", attribute);
		semVariable.addPropEditableAttribute("01#" + "name");
		semVariable.addPropVisibleAttribute("01#" + "name");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("variableType", "Enumeration",
				AttributeType.OPERATION, true, "Variable Type",
				"Type of variable", VariableType.class.getCanonicalName(),
				"String", "", 0, 2, "", "", 5, "{#" + "variableType"
						+ "#all#} ", "variableType" + "#!=#" + "Enumeration");
		semVariable.putSemanticAttribute("variableType", attribute);
		semVariable.addPropEditableAttribute("02#" + "variableType");
		semVariable.addPropVisibleAttribute("02#" + "variableType");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// semVariable.addPanelVisibleAttribute("05#" + "variableType" + "#"
		// + "variableType" + "#!=#" + "Enumeration");
		// semVariable.addPanelSpacersAttribute("{#" + "variableType" + "#} ");

		attribute = new ElemAttribute("varDom", "String",
				AttributeType.OPERATION, false, "Variable Domain",
				"Defined domain {n..m,o,p..r} (no spaces)", "0,1", 0, 3,
				"variableType" + "#==#" + "Integer", "variableType" + "#==#"
						+ "Integer", 7, "{#" + "varDom" + "#all#} ",
				"variableType" + "#==#" + "Integer");
		semVariable.putSemanticAttribute("varDom", attribute);
		semVariable.addPropEditableAttribute("03#" + "varDom");
		semVariable.addPropVisibleAttribute("03#" + "varDom" + "#"
				+ "variableType" + "#==#" + "Integer");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// semVariable.addPanelVisibleAttribute("07#" + "varDom" + "#"
		// + "variableType" + "#==#" + "Integer");
		// semVariable.addPanelSpacersAttribute("{#" + "varDom" + "#} ");

		attribute = new ElemAttribute("floatDom", "String",
				AttributeType.OPERATION, false, "Float Domain",
				"Defined domain {n..m,o,p..r} (no spaces)", "0,1", 0, 3,
				"variableType" + "#==#" + "Float", "variableType" + "#==#"
						+ "Float", 7, "{#" + "floatDom" + "#all#} ",
				"variableType" + "#==#" + "Float");
		semVariable.putSemanticAttribute("floatDom", attribute);

		semVariable.addPropEditableAttribute("03#" + "floatDom");
		semVariable.addPropVisibleAttribute("03#" + "floatDom" + "#"
				+ "variableType" + "#==#" + "Float");

		// semVariable.addPanelVisibleAttribute("07#" + "floatDom" + "#"
		// + "variableType" + "#==#" + "Float");
		// semVariable.addPanelSpacersAttribute("{#" + "floatDom" + "#} ");

		attribute = new ElemAttribute("floatPrec", "Integer",
				AttributeType.OPERATION, false, "Float Precision",
				"Number of decimal for float precision", 2, 0, 3,
				"variableType" + "#==#" + "Float", "variableType" + "#==#"
						+ "Float", 7, "#" + "floatPrec" + "#all#",
				"variableType" + "#==#" + "Float");
		semVariable.putSemanticAttribute("floatPrec", attribute);

		semVariable.addPropEditableAttribute("03#" + "floatPrec");
		semVariable.addPropVisibleAttribute("03#" + "floatPrec" + "#"
				+ "variableType" + "#==#" + "Float");

		// semVariable.addPanelVisibleAttribute("07#" + "floatPrec" + "#"
		// + "variableType" + "#==#" + "Float");

		attribute = new ElemAttribute("enumType", "Class",
				AttributeType.OPERATION, false, "Enumeration",
				"Enumeration type from the context view",
				InstConcept.class.getCanonicalName(), "ME", "String", "", 0, 4,
				"variableType" + "#==#" + "Enumeration", "variableType"
						+ "#==#" + "Enumeration", 6, "#" + "variableType"
						+ "#all#", "variableType" + "#==#" + "Enumeration");
		semVariable.putSemanticAttribute("enumType", attribute);
		semVariable.addPropEditableAttribute("04#" + "enumType");
		semVariable.addPropVisibleAttribute("04#" + "enumType" + "#"
				+ "variableType" + "#==#" + "Enumeration");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// semVariable.addPanelVisibleAttribute("06#" + "enumType" + "#"
		// + "variableType" + "#==#" + "Enumeration");

		// TODO define domain for enumtype
		attribute = new ElemAttribute("value", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "Value",
				"Variable current value (defined by an operation execution)",
				0, 1, 7, "", "", -1, "", "");
		semVariable.putSemanticAttribute("value", attribute);
		semVariable.addPropVisibleAttribute("07#" + "value");
		semVariable.addPropVisibleAttribute("07#" + "value");
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("isContext", "Boolean",
				AttributeType.OPERATION, false, "Context Defined",
				"(Ignored for operations)", false, 0, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("isContext", attribute);
		semVariable.addPropEditableAttribute("05#" + "isContext");
		semVariable.addPropVisibleAttribute("05#" + "isContext");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("extVisible", "Boolean",
				AttributeType.OPERATION, false, "Externally Visible",
				"(Ignored by operations)", false, 0, 8, "", "", -1, "", "");
		semVariable.putSemanticAttribute("extVisible", attribute);
		semVariable.addPropEditableAttribute("08#" + "extVisible");
		semVariable.addPropVisibleAttribute("08#" + "extVisible");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("extControl", "Boolean",
				AttributeType.OPERATION, false, "Externally Controlled",
				"(Ignored by operations)", false, 0, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("extControl", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		semVariable.addPropEditableAttribute("09#" + "extControl");
		semVariable.addPropVisibleAttribute("09#" + "extControl");

		attribute = new ElemAttribute("isConfDom", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Configure Domain",
				"Configured value", 0, 0, 1, "", "variableType" + "#==#"
						+ "Integer" + "$" + "variableType" + "#==#"
						+ "Enumeration" + "$" + "variableType" + "#==#"
						+ "Boolean", -1, "", "", "varConfDom", "", null);

		// TODO define multiple conditions
		semVariable.putSemanticAttribute("isConfDom", attribute);
		semVariable.addPropEditableAttribute("01#" + "isConfDom");
		semVariable.addPropVisibleAttribute("01#" + "isConfDom" + "#"
				+ "variableType" + "#==#" + "Enumeration");
		semVariable.addPropVisibleAttribute("01#" + "isConfDom" + "#"
				+ "variableType" + "#==#" + "Integer");
		semVariable.addPropVisibleAttribute("01#" + "isConfDom" + "#"
				+ "variableType" + "#==#" + "Boolean");
		semVariable.addPropVisibleAttribute("01#" + "isConfDom" + "#"
				+ "variableType" + "#==#" + "Float");

		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("varConfValue", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "Configured Value",
				"Configured value", 0, 0, 8, "", "", -1, "", "", "varConfDom",
				"", null);

		// semVariable.addPropVisibleAttribute("08#" + "varConfValue");
		// semVariable.addPropVisibleAttribute("08#" + "varConfValue");
		semVariable.putSemanticAttribute("varConfValue", attribute);
		simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("varConfDom", "String",
				AttributeType.GLOBALCONFIG, false, "Configured Domain",
				"Configured domain {n..m,o,p..r} (no spaces)"
						+ " (not used by dynamic operations)", "", 0, 2, "",
				"variableType" + "#==#" + "Integer" + "$" + "variableType"
						+ "#==#" + "Enumeration" + "$" + "variableType"
						+ "#==#" + "Boolean", -1, "", "");
		semVariable.putSemanticAttribute("varConfDom", attribute);
		semVariable.addPropEditableAttribute("02#" + "varConfDom");
		semVariable.addPropVisibleAttribute("02#" + "varConfDom" + "#"
				+ "isConfDom" + "#==#" + "true");

		attribute = new ElemAttribute("LowLevelExpressionText",
				LowExpr.class.getCanonicalName(), AttributeType.OPERATION,
				false, "Low-Level Expr. Text",
				"Expression at the solver level (language independent)", null,
				0, 3, "", "variableType" + "#==#" + "LowLevel expression", -1,
				"", "");
		semVariable.putSemanticAttribute("LowLevelExpressionText", attribute);
		semVariable.addPropEditableAttribute("03#" + "LowLevelExpressionText");
		semVariable.addPropVisibleAttribute("03#" + "LowLevelExpressionText"
				+ "#" + "variableType" + "#==#" + "LowLevel expression");

		attribute = new ElemAttribute("LowLevelExpressionOper", "Class",
				AttributeType.OPERATION, false, "SubOper",
				"Sub Operation to include this low-level expressions",
				OpersConcept.class.getCanonicalName(), "OMSubOper", null, "",
				0, 3, "", "variableType" + "#==#" + "LowLevel expression", -1,
				"", "");
		semVariable.putSemanticAttribute("LowLevelExpressionOper", attribute);
		semVariable.addPropEditableAttribute("03#" + "LowLevelExpressionOper");
		semVariable.addPropVisibleAttribute("03#" + "LowLevelExpressionOper"
				+ "#" + "variableType" + "#==#" + "LowLevel expression");

		attribute = new ElemAttribute("LowLevelVarOper", "Class",
				AttributeType.OPERATION, false, "SubOper",
				"Sub Operation to include this low-level variable",
				OpersConcept.class.getCanonicalName(), "OMSubOper", null, "",
				0, 4, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semVariable.putSemanticAttribute("LowLevelVarOper", attribute);
		semVariable.addPropEditableAttribute("04#" + "LowLevelVarOper");
		semVariable.addPropVisibleAttribute("04#" + "LowLevelVarOper" + "#"
				+ "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute("LowLevelVarLabel", "Class",
				AttributeType.OPERATION, false, "Labeling",
				"Labeling with only a set of variables",
				OpersLabeling.class.getCanonicalName(), "OMLabeling", null, "",
				0, 5, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semVariable.putSemanticAttribute("LowLevelVarLabel", attribute);
		semVariable.addPropEditableAttribute("05#" + "LowLevelVarLabel");
		semVariable.addPropVisibleAttribute("05#" + "LowLevelVarLabel" + "#"
				+ "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute("LowLevelVarValue", "String",
				AttributeType.GLOBALCONFIG, false, "Input Value",
				"Value defined for input variable", "", 0, 5, "",
				"variableType" + "#==#" + "LowLevel variable", -1, "", "");
		semVariable.putSemanticAttribute("LowLevelVarValue", attribute);
		semVariable.addPropEditableAttribute("05#" + "LowLevelVarValue");
		semVariable.addPropVisibleAttribute("05#" + "LowLevelVarValue" + "#"
				+ "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute("LowLevelVarOper", "Class",
				AttributeType.OPERATION, false, "SubOper",
				"Sub Operation to include this low-level variable",
				OpersConcept.class.getCanonicalName(), "OMSubOper", null, "",
				0, 4, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semVariable.putSemanticAttribute("LowLevelVarOper", attribute);
		semVariable.addPropEditableAttribute("04#" + "LowLevelVarOper");
		semVariable.addPropVisibleAttribute("04#" + "LowLevelVarOper" + "#"
				+ "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute("LowLevelVarLabel", "Class",
				AttributeType.OPERATION, false, "Labeling",
				"Labeling with only a set of variables",
				OpersLabeling.class.getCanonicalName(), "OMLabeling", null, "",
				0, 5, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semVariable.putSemanticAttribute("LowLevelVarLabel", attribute);
		semVariable.addPropEditableAttribute("05#" + "LowLevelVarLabel");
		semVariable.addPropVisibleAttribute("05#" + "LowLevelVarLabel" + "#"
				+ "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute("LowLevelVarValue", "String",
				AttributeType.GLOBALCONFIG, false, "Input Value",
				"Value defined for input variable", "", 0, 5, "",
				"variableType" + "#==#" + "LowLevel variable", -1, "", "");
		semVariable.putSemanticAttribute("LowLevelVarValue", attribute);
		semVariable.addPropEditableAttribute("05#" + "LowLevelVarValue");
		semVariable.addPropVisibleAttribute("05#" + "LowLevelVarValue" + "#"
				+ "variableType" + "#==#" + "LowLevel variable");

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

		refas.getVariabilityVertex().put("nmVariable", instVertexVAR);

		OpersElement semContextGroup = new OpersElement("nmConcernLevel");

		semContextGroup.putSemanticAttribute("name", new ElemAttribute("name",
				"String", AttributeType.OPERATION, false, "Group Name", "",
				"<<new>>", 0, 1, "", "", 1, "", ""));
		semContextGroup.addPropVisibleAttribute("01#" + "name");
		semContextGroup.addPropEditableAttribute("01#" + "name");

		semContextGroup
				.putSemanticAttribute(
						"minInstances",
						new ElemAttribute(
								"minInstances",
								"Integer",
								AttributeType.OPERATION,
								false,
								"Min Number of Instances",
								"Min number of instances allowed of the concern level (Ignored for operations)",
								"1", 0, 6, "", "", -1, "", ""));
		semContextGroup.addPropEditableAttribute("06#" + "minInstances");
		semContextGroup.addPropVisibleAttribute("06#" + "minInstances");
		semContextGroup
				.putSemanticAttribute(
						"instances",
						new ElemAttribute(
								"instances",
								"Integer",
								AttributeType.OPERATION,
								false,
								"Max Number of Instances",
								"Max number of instances allowed of the concern level (Ignored for operations)",
								"1", 0, 7, "", "", -1, "", ""));
		semContextGroup.addPropEditableAttribute("07#" + "instances");
		semContextGroup.addPropVisibleAttribute("07#" + "instances");
		semContextGroup.putSemanticAttribute("ExtVisible", new ElemAttribute(
				"ExtVisible", "Boolean", AttributeType.OPERATION, false,
				"External Visible", "(Ignored for operations)", false, 0, 8,
				"", "", -1, "", ""));
		semContextGroup.addPropEditableAttribute("08#" + "ExtVisible");
		semContextGroup.addPropVisibleAttribute("08#" + "ExtVisible");
		semContextGroup.putSemanticAttribute("ExtControl", new ElemAttribute(
				"ExtControl", "Boolean", AttributeType.OPERATION, false,
				"Externally Controlled", "(Ignored for operations)", false, 0,
				9, "", "", -1, "", ""));
		semContextGroup.addPropEditableAttribute("09#" + "ExtControl");
		semContextGroup.addPropVisibleAttribute("09#" + "ExtControl");

		instVertexCG = new InstConcept("nmConcernLevel", infraMetaMetaConcept,
				semContextGroup);
		refas.getVariabilityVertex().put("nmConcernLevel", instVertexCG);
	}

	@SuppressWarnings("unchecked")
	private static void createOpersMetaModelElements(ModelInstance refas,
			boolean empty) {

		ArrayList<OpersExpr> semanticExpressions = new ArrayList<OpersExpr>();

		ElemAttribute attribute = null;

		// Start Concept's definition
		// -------------------------------------------------------

		if (!empty) {
			OpersConcept semGeneralElement = new OpersConcept("GeneralConcept");
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
			InstConcept instVertexGE = new InstConcept("GeneralConcept",
					metaMetaInstConcept, semGeneralElement);

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

			OpersExpr t1 = new OpersExpr("Req Implies Selected", refas
					.getSemanticExpressionTypes().get("Implies"), instVertexGE,
					instVertexGE, "Required", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("Core Implies Selected", refas
					.getSemanticExpressionTypes().get("Implies"), instVertexGE,
					instVertexGE, "Core", "Sel");

			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("Req Implies Core", refas
					.getSemanticExpressionTypes().get("Implies"), instVertexGE,
					instVertexGE, "Required", "Core");

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			// t1 = new OpersExpr("Req Implies Verification Error", refas
			// .getSemanticExpressionTypes().get("Implies"), instVertexGE,
			// instVertexGE, "Required", "Ver");
			//
			// verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// semanticExpressions.add(t1);

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

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexGE, "Core", true, 1);

			OpersExpr t2 = new OpersExpr("2", refas
					.getSemanticExpressionTypes().get("Equals"), instVertexGE,
					"FeatureType", "Root");

			t1 = new OpersExpr("NoLFet & NoGFet Implies hasParent", refas
					.getSemanticExpressionTypes().get("Implies"), t2, t1);

			verifParentsOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), instVertexGE, instVertexGE, "NReqSel", "ConfSel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Product"), instVertexGE, "NPrefSel", true, t1);

			OpersExpr t3 = new OpersExpr("3", refas
					.getSemanticExpressionTypes().get("Sum"), instVertexGE,
					instVertexGE, "NPrefSel", "ConfSel");

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

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Product"), instVertexGE, instVertexGE, "Sel", "Proh");

			t1 = new OpersExpr("Sel * Proh = 0)", refas
					.getSemanticExpressionTypes().get("Equals"), t1, 0);

			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Product"), instVertexGE, instVertexGE, "Core", "Proh");

			t1 = new OpersExpr("Core * Proh = 0)", refas
					.getSemanticExpressionTypes().get("Equals"), t1, 0);

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
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

			refas.getVariabilityVertex().put("GeneralConcept", instVertexGE);

			InstPairwiseRel instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("getobe", instEdge);
			instEdge.setIdentifier("getobe");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexIE, true);
			instEdge.setSourceRelation(instVertexGE, true);

			// Design attributes: Do not change identifiers

			// simulationExecOperUniqueLabeling.addAttribute(attribute);
			// semGeneralElement.putSemanticAttribute("Description", attribute);

			attribute = new ElemAttribute("Scope", "Boolean",
					AttributeType.OPERATION, true, "Global Scope", "", true, 0,
					5, "", "", -1, "", "");
			semGeneralElement.addPropEditableAttribute("05#" + "Scope");
			semGeneralElement.addPropVisibleAttribute("05#" + "Scope");
			// simulationExecOperUniqueLabeling.addAttribute(new
			// OpersIOAttribute(
			// semGeneralElement.getIdentifier(), attribute.getName(),
			// true));
			// simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
			// semGeneralElement.getIdentifier(), attribute.getName(),
			// true));
			semGeneralElement.putSemanticAttribute("Scope", attribute);
			// simulOperationSubAction.addInAttribute(new OpersIOAttribute(
			// semGeneralElement.getIdentifier(), attribute.getName(),
			// true));
			// simSceOperationSubAction.addInAttribute(new OpersIOAttribute(
			// semGeneralElement.getIdentifier(), attribute.getName(),
			// true));
			// TODO use scope

			attribute = new ElemAttribute("ConcernLevel", "Class",
					AttributeType.OPERATION, false, "Concern Level",
					"Concern Level of the element (Ignored for operations)",

					InstConcept.class.getCanonicalName(), "CG", null, "", 2, 6,
					"", "Scope" + "#==#" + "false", 0, "<<#" + "ConcernLevel"
							+ "#all#>>\n", "Scope" + "#==#" + "false");

			semGeneralElement.putSemanticAttribute("ConcernLevel", attribute);

			semGeneralElement.addPropEditableAttribute("06#" + "ConcernLevel"
					+ "#" + "Scope" + "#==#" + "false" + "#" + "");
			semGeneralElement.addPropVisibleAttribute("06#" + "ConcernLevel"
					+ "#" + "Scope" + "#==#" + "false" + "#" + "");

			// semGeneralElement.addPanelVisibleAttribute("00#" + "ConcernLevel"
			// + "#" + "Scope" + "#==#" + "false");
			// semGeneralElement.addPanelSpacersAttribute("<<#" + "ConcernLevel"
			// + "#>>\n");
			// simulationExecOperUniqueLabeling.addAttribute(attribute);
			// simulOperationSubAction.addInVariable(attribute);
			// TODO: use concern level

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
					"", 0, new RangeDomain(0, 20, 0), 0, -1, "", "", -1, "", "");
			semGeneralElement.putSemanticAttribute("Opt", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));

			attribute = new ElemAttribute("Order", "Integer",
					AttributeType.EXECCURRENTSTATE, false, "SortVariable", "",
					0, new RangeDomain(0, 40, 0), 0, -1, "", "", -1, "", "");
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
					"Selected by configuration", "", false, 0, 3, "false", "",
					-1, "", "");
			semGeneralElement.putSemanticAttribute("NPrefSel", attribute);
			semGeneralElement.addPropVisibleAttribute("03#" + "NPrefSel");
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));

			attribute = new ElemAttribute("NNotPrefSel", "Boolean",
					AttributeType.EXECCURRENTSTATE, false,
					"Not Selected by configuration", "", false, 0, 6, "false",
					"", -1, "", "");
			semGeneralElement.putSemanticAttribute("NNotPrefSel", attribute);
			semGeneralElement.addPropVisibleAttribute("06#" + "NNotPrefSel");
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					semGeneralElement.getIdentifier(), attribute.getName(),
					true));

			OpersConcept semHardConcept = new OpersConcept("semHardConcept");

			attribute = new ElemAttribute("satType", "Enumeration",
					AttributeType.OPERATION, false, "satType", "",
					"com.variamos.dynsup.statictypes.SatisfactionType",
					"achieve", "", 0, 1, "", "", -1, "", "");
			semHardConcept.putSemanticAttribute("satType", attribute);
			// simulationExecOperUniqueLabeling.addAttribute(attribute);

			semHardConcept.addPropEditableAttribute("01#" + "satType");
			semHardConcept.addPropVisibleAttribute("01#" + "satType");

			InstConcept instVertexHC = new InstConcept("HardConcept",
					metaMetaInstConcept, semHardConcept);
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
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semFeature.getIdentifier(), "Sel", true));

			InstConcept instVertexF = new InstConcept("Feature",
					metaMetaInstConcept, semFeature);

			attribute = new ElemAttribute("IsRootFeature", "Boolean",
					AttributeType.OPERATION, true, "Is a Root Feature Concept",
					"", false, 2, -1, "", "", -1, "", "");
			// simulationExecOperUniqueLabeling.addAttribute(new
			// OpersIOAttribute(
			// semFeature.getIdentifier(), attribute.getName(), true));
			// simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
			// semFeature.getIdentifier(), attribute.getName(), true));
			// updateCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
			// semFeature.getIdentifier(), attribute.getName(), true));
			semFeature.putSemanticAttribute("IsRootFeature", attribute);
			// simulOperationSubAction.addOutAttribute(new OpersIOAttribute(
			// semFeature.getIdentifier(), attribute.getName(), true));
			// simSceOperationSubAction.addOutAttribute(new OpersIOAttribute(
			// semFeature.getIdentifier(), attribute.getName(), true));
			verifRootSubOperationAction.addOutAttribute(new OpersIOAttribute(
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

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Equals"), instInfraPair, "MetaPaiwise", "mandatory");

			t2 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Equals"), instInfraPair, "MetaPaiwise", "optional");

			t2 = new OpersExpr("2", refas.getSemanticExpressionTypes()
					.get("Or"), t1, t2);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITEROUTFIXEDSUBEXP,
					instInfraPair, instInfraPair, t2, 0);

			t1 = new OpersExpr("Core", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"), ExpressionVertexType.LEFTITEROUTSUBEXP,
					instInfraPair, instVertexF, t1, 1);

			t1 = new OpersExpr("HasParent", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"), instVertexF, "HasParent", true, t1);

			// verifParentsOperSubActionRelaxable.addSemanticExpression(t1);

			t1 = new OpersExpr("HasParent", refas.getSemanticExpressionTypes()
					.get("Equals"), instVertexF, "Core", true, 1);

			verifParentsOperSubActionToVerify.addSemanticExpression(t1);

			verifParentsOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("2-1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexGE, "FeatureType", "Root");

			t3 = new OpersExpr("3-", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexF, "IsRootFeature", true, 1);

			t1 = new OpersExpr("IsRootFeature=...", refas
					.getSemanticExpressionTypes().get("Implies"), t1, t3);

			verifRootOperSubActionRelaxable.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("2-1", refas.getSemanticExpressionTypes().get(
					"NotEquals"), instVertexGE, "FeatureType", "Root");

			t3 = new OpersExpr("3-", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexF, "IsRootFeature", true, 0);

			t1 = new OpersExpr("IsRootFeature=...", refas
					.getSemanticExpressionTypes().get("Implies"), t1, t3);

			verifRootOperSubActionNormal.addSemanticExpression(t1);

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

			t1 = new OpersExpr("2-1", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexGE, "FeatureType", "Root");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Equals"), instVertexGE, "Core", true, 1);

			t1 = new OpersExpr("Root Implies Req", refas
					.getSemanticExpressionTypes().get("Implies"), t1, t3);

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
			// "Equals"), instVertexGE, "IsRootFeature", true, 1);
			//
			// t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
			// "Equals"), instVertexGE, "Sel", true, 1);
			//
			// t1 = new OpersExpr("Root Implies Selected", refas
			// .getSemanticExpressionTypes().get("Implies"), t1, t3);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

			// semanticExpressions.add(t1);

			refas.getVariabilityVertex().put("Feature", instVertexF);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ftoge", instEdge);
			instEdge.setIdentifier("ftoge");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexGE, true);
			instEdge.setSourceRelation(instVertexF, true);

			OpersConcept semRFeature = new OpersConcept("RootFeature");
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semRFeature.getIdentifier(), "Sel", true));

			InstConcept instVertexRF = new InstConcept("RootFeature",
					metaMetaInstConcept, semRFeature);

			StringDomain rootTypeDomain = new StringDomain();
			rootTypeDomain.add("Root");
			rootTypeDomain.add("General");
			rootTypeDomain.add("Leaf");
			attribute = new ElemAttribute("FeatureType", "String",
					AttributeType.OPERATION, "Feature Type", "", "Root", false,
					rootTypeDomain, 2, -1, "", "", -1, "", "");

			semRFeature.putSemanticAttribute("FeatureType", attribute);
			verifRootSubOperationAction.addInAttribute(new OpersIOAttribute(
					semRFeature.getIdentifier(), attribute.getName(), true));
			verifParentsSubOperationAction.addInAttribute(new OpersIOAttribute(
					semRFeature.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
					semRFeature.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semRFeature
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semRFeature
							.getIdentifier(), attribute.getName(), true));

			refas.getVariabilityVertex().put("RootFeature", instVertexRF);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("rftof", instEdge);
			instEdge.setIdentifier("rftof");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexF, true);
			instEdge.setSourceRelation(instVertexRF, true);

			OpersConcept semGFeature = new OpersConcept("GeneralFeature");
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semGFeature.getIdentifier(), "Sel", true));

			InstConcept instVertexGF = new InstConcept("GeneralFeature",
					metaMetaInstConcept, semGFeature);

			attribute = new ElemAttribute("FeatureType", "String",
					AttributeType.OPERATION, "Feature Type", "", "General",
					false, rootTypeDomain, 2, -1, "", "", -1, "", "");
			semGFeature.putSemanticAttribute("FeatureType", attribute);
			verifRootSubOperationAction.addInAttribute(new OpersIOAttribute(
					semGFeature.getIdentifier(), attribute.getName(), true));
			verifParentsSubOperationAction.addInAttribute(new OpersIOAttribute(
					semGFeature.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
					semGFeature.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semGFeature
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semGFeature
							.getIdentifier(), attribute.getName(), true));

			refas.getVariabilityVertex().put("GeneralFeature", instVertexGF);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("gftof", instEdge);
			instEdge.setIdentifier("gftof");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexF, true);
			instEdge.setSourceRelation(instVertexGF, true);

			OpersConcept semLFeature = new OpersConcept("LeafFeature");
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semLFeature.getIdentifier(), "Sel", true));

			InstConcept instVertexLF = new InstConcept("LeafFeature",
					metaMetaInstConcept, semLFeature);

			attribute = new ElemAttribute("FeatureType", "String",
					AttributeType.OPERATION, "Feature Type", "", "Leaf", false,
					rootTypeDomain, 2, -1, "", "", -1, "", "");
			semLFeature.putSemanticAttribute("FeatureType", attribute);
			verifRootSubOperationAction.addInAttribute(new OpersIOAttribute(
					semLFeature.getIdentifier(), attribute.getName(), true));
			verifParentsSubOperationAction.addInAttribute(new OpersIOAttribute(
					semLFeature.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
					semLFeature.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semLFeature
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semLFeature
							.getIdentifier(), attribute.getName(), true));

			refas.getVariabilityVertex().put("LeafFeature", instVertexLF);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("lftof", instEdge);
			instEdge.setIdentifier("lftof");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexF, true);
			instEdge.setSourceRelation(instVertexLF, true);

			// definition of other concepts

			OpersConcept semAssumption = new OpersConcept("Assumption");
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semAssumption.getIdentifier(), "Sel", true));

			InstConcept instVertexAS = new InstConcept("Assumption",
					metaMetaInstConcept, semAssumption);
			refas.getVariabilityVertex().put("Assumption", instVertexAS);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("assutoge", instEdge);
			instEdge.setIdentifier("assutoge");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexHC, true);
			instEdge.setSourceRelation(instVertexAS, true);

			OpersConcept semGoal = new OpersConcept("Goal");

			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semGoal
					.getIdentifier(), "Sel", true));
			attribute = new ElemAttribute("satType", "Enumeration",
					AttributeType.OPERATION, false, "satType", "",
					"com.variamos.dynsup.statictypes.SatisfactionType",
					"achieve", "", 0, 1, "", "", 1, "<#" + "satType"
							+ "#all#>\n", "");
			semGoal.putSemanticAttribute("satType", attribute);
			// semGoal.addPanelVisibleAttribute("01#" + "satType");
			// semGoal.addPanelSpacersAttribute("<#" + "satType" + "#>\n");
			InstConcept instVertexG = new InstConcept("Goal",
					metaMetaInstConcept, semGoal);
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
					new ArrayList<InstAttribute>(), 0, 6, "", "", 6,
					"#attributeValue#2#\n", "");
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
					metaMetaInstConcept, semOperationalization);
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
					semSoftgoal.getIdentifier(), "Sel", true));

			StringDomain d = new StringDomain();
			d.add("low");
			d.add("high");
			d.add("close");
			attribute = new ElemAttribute("satisficingLevel", "String",
					AttributeType.OPERATION, "Satisficing Level",
					"Satisficing for dynamic operations (low/high/close)",
					"high", false, d, 0, 11, "", "", -1, "", "");

			semSoftgoal.putSemanticAttribute("satisficingLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
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
			semSoftgoal.addPropEditableAttribute("10#" + "satisficingType");
			semSoftgoal.addPropVisibleAttribute("10#" + "satisficingType");

			attribute = new ElemAttribute("ConfigReqLevel", "Integer",
					AttributeType.OPERATION, "Config Req Level (5=ignored)",
					"SG required level (defined: 0..4 ignored: 5) ", 5, false,
					new RangeDomain(0, 5, 0), 0, 5, "Required" + "#==#"
							+ "true" + "#" + "0", "", -1, "", "");
			semSoftgoal.putSemanticAttribute("ConfigReqLevel", attribute);
			semSoftgoal.addPropEditableAttribute("05#" + "ConfigReqLevel" + "#"
					+ "Required" + "#==#" + "true" + "#" + "5");
			semSoftgoal.addPropVisibleAttribute("05#" + "ConfigReqLevel");
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));

			attribute = new ElemAttribute("defaultDomainValue", "Integer",
					AttributeType.OPERATION, "Default Domain Value",
					"Default value for the domain when no relation exists", 5,
					false, new RangeDomain(0, 5, 0), 0, 5, "Required" + "#==#"
							+ "true" + "#" + "0", "", -1, "", "");
			semSoftgoal.putSemanticAttribute("defaultDomainValue", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));

			semanticExpressions = new ArrayList<OpersExpr>();

			semSoftgoal.setSemanticExpressions(semanticExpressions);

			InstConcept instVertexSG = new InstConcept("Softgoal",
					metaMetaInstConcept, semSoftgoal);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

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
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			refas.getVariabilityVertex().put("Softgoal", instVertexSG);

			attribute = new ElemAttribute("SDReqLevel", "Integer",
					AttributeType.EXECCURRENTSTATE, false,
					"Required Level by SD",
					"Required level (0..4) for the soft dependency relation",
					0, new RangeDomain(0, 4, 0), 2, 16, "", "", -1, "", "",
					"ConfigReqLevel",
					"sourceLevel;targetLevel;level;CLSGLevel",
					"defaultDomainValue");
			semSoftgoal.putSemanticAttribute("SDReqLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));

			attribute = new ElemAttribute("ClaimExpLevel", "Integer",
					AttributeType.EXECCURRENTSTATE, false,
					"Expected Level by Claim",
					"Expected level (0..4) for the claim relation", 0,
					new RangeDomain(0, 4, 0), 2, 18, "", "", -1, "", "",
					"ConfigReqLevel",
					"sourceLevel;targetLevel;level;CLSGLevel",
					"defaultDomainValue");
			semSoftgoal.putSemanticAttribute("ClaimExpLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semSoftgoal.getIdentifier(), attribute.getName(), true));

			semSoftgoal.addPropVisibleAttribute("16#" + "SDReqLevel");
			semSoftgoal.addPropVisibleAttribute("18#" + "ClaimExpLevel");

			semSoftgoal.addPropEditableAttribute("16#" + "SDReqLevel");
			semSoftgoal.addPropEditableAttribute("18#" + "ClaimExpLevel");

			attribute = new ElemAttribute(
					"defaultDomainValue",
					"Integer",
					AttributeType.OPERATION,
					false,
					"Default Value (Filtered Attributes)",
					"Default value for ClaimExpLevel/SDReqLevel when no Claim/SD"
							+ " constraints the domain (e.j. 4 for maximizing SG)",
					0, new RangeDomain(0, 4, 0), 2, 19, "", "", -1, "", "");
			semSoftgoal.putSemanticAttribute("defaultDomainValue", attribute);

			semSoftgoal.addPropVisibleAttribute("19#" + "defaultDomainValue");

			semSoftgoal.addPropEditableAttribute("19#" + "defaultDomainValue");

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sgtoge", instEdge);
			instEdge.setIdentifier("sgtoge");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexGE, true);
			instEdge.setSourceRelation(instVertexSG, true);

			OpersConcept semAsset = new OpersConcept("Asset");

			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semAsset
					.getIdentifier(), "Sel", true));
			InstConcept instVertexAsset = new InstConcept("Asset",
					metaMetaInstConcept, semAsset);
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

			OpersConcept semClaim = new OpersConcept("Claim");

			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semClaim
					.getIdentifier(), "Sel", true));
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

			OpersConcept directOperClaimSemanticEdge = new OpersConcept(
					"OperClPW");

			InstConcept instDirOperClaimSemanticEdge = new InstConcept(
					"OperClPW", metaMetaPairwiseRelation,
					directOperClaimSemanticEdge);

			refas.getVariabilityVertex().put("OperClPW",
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
					"OperToClaim##true#true#true#1#-1#1#1"));

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
					"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

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
					null, 0, 3, "", "", 3, "#ConditionalExpression#all#", "");
			semClaim.putSemanticAttribute("ConditionalExpression", attribute);
			semClaim.addPropEditableAttribute("03#" + "ConditionalExpression");
			semClaim.addPropVisibleAttribute("03#" + "ConditionalExpression");
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semClaim.getIdentifier(), attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(semClaim
					.getIdentifier(), attribute.getName(), true));

			attribute = new ElemAttribute("CompExp", "Boolean",
					AttributeType.GLOBALCONFIG, false,
					"Boolean Comp. Expression", "", true, 0, 1, "", "", -1, "",
					"");
			semClaim.putSemanticAttribute("CompExp", attribute);
			semClaim.addPropEditableAttribute("01#" + "CompExp");
			semClaim.addPropVisibleAttribute("01#" + "CompExp");

			// simulationExecOperUniqueLabeling.addAttribute(attribute);

			attribute = new ElemAttribute("ConfidenceLevel", "Integer",
					AttributeType.OPERATION, "Confidence Level",
					"(Ignored for operations)", 1, false, new RangeDomain(0, 4,
							0), 0, 5, "", "", -1, "", "");
			semClaim.putSemanticAttribute("ConfidenceLevel", attribute);
			semClaim.addPropEditableAttribute("05#" + "ConfidenceLevel");
			semClaim.addPropVisibleAttribute("05#" + "ConfidenceLevel");

			OpersConcept semSoftDependency = new OpersConcept("SoftDependency");

			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftDependency.getIdentifier(), "Sel", true));
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
					"Soft dependency activation expression", null, 0, 3, "",
					"", 3, "#ConditionalExpression#all#", "");
			semSoftDependency.putSemanticAttribute("ConditionalExpression",
					attribute);
			semSoftDependency.addPropEditableAttribute("03#"
					+ "ConditionalExpression");
			semSoftDependency.addPropVisibleAttribute("03#"
					+ "ConditionalExpression");
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semSoftDependency.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					semSoftDependency.getIdentifier(), attribute.getName(),
					true));

			attribute = new ElemAttribute("CompExp", "Boolean",
					AttributeType.GLOBALCONFIG, false,
					"Boolean Comp. Expression", "", true, 2, 1, "", "", -1, "",
					"");
			semSoftDependency.putSemanticAttribute("CompExp", attribute);
			semSoftDependency.addPropEditableAttribute("01#" + "CompExp");
			semSoftDependency.addPropVisibleAttribute("01#" + "CompExp");
			// simulationExecOperUniqueLabeling.addAttribute(attribute);

			attribute = new ElemAttribute("SDSelected", "Boolean",
					AttributeType.GLOBALCONFIG, false, "SD Selected", "",
					false, 2, 2, "false", "", -1, "", "");
			semSoftDependency.putSemanticAttribute("SDSelected", attribute);
			semSoftDependency.addPropVisibleAttribute("02#" + "SDSelected");
			// simulationExecOperUniqueLabeling.addAttribute(attribute);

			OpersConcept semHardOverTwoRelation = new OpersConcept("SMOverTwo");// hardSemOverTwoRelList);

			InstConcept instVertexHHGR = new InstConcept("GoalOT",
					semHardOverTwoRelation, metaMetaInstOverTwoRel);
			refas.getVariabilityVertex().put("GoalOT", instVertexHHGR);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("hhtoigr", instEdge);
			instEdge.setIdentifier("hhtoigr");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
			instEdge.setTargetRelation(instVertexGR, true);
			instEdge.setSourceRelation(instVertexHHGR, true);

			InstConcept instHchcHHGRHC = new InstConcept("GoaltoOT",
					metaMetaPairwiseRelation);
			refas.getVariabilityVertex().put("GoaltoOT", instHchcHHGRHC);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("hhotfromip", instEdge);
			instEdge.setIdentifier("hhotfromip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instVertexGR, true);
			instEdge.setSourceRelation(instHchcHHGRHC, true);

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

			InstConcept instHchcHHGRGR = new InstConcept("GoalfromOT",
					metaMetaPairwiseRelation);
			refas.getVariabilityVertex().put("GoalfromOT", instHchcHHGRGR);

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
			refas.getConstraintInstEdges().put("ogfottoip", instEdge);
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
					ExpressionVertexType.LEFTVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("ANDhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHHGR, instVertexHC, "Core", "Core");

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", true, "TrueVal");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexHHGR, instVertexHC, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexHC, "Core", true, "TrueVal");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexHHGR, instVertexHC, t1, "Core");

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("ORhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", true, "FalseVal");

			t1 = new OpersExpr("ORhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexHHGR, instVertexHC, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexHC, "Core", true, "FalseVal");

			t1 = new OpersExpr("ORhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexHHGR, instVertexHC, t1, "Core");

			verifParentsOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("MUTEXhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"mutex", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("RANGEhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", true, "FalseVal");

			t1 = new OpersExpr("incon", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexHHGR, instVertexHC, t1, "LowRange");

			t2 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexHC, "Sel", true, "FalseVal");

			t2 = new OpersExpr("incon", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexHHGR, instVertexHC, t2, "HighRange");

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t2);

			t1 = new OpersExpr("RANGEHardRel", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					instVertexHHGR, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			OpersConcept directHardHardSemanticEdge = new OpersConcept(
					"travHardPW");

			attribute = new ElemAttribute("AggregationLow", "Integer",
					AttributeType.OPERATION, false, "Aggregation Low", "", 0,
					0, 3, "", "", 3, "[#" + "AggregationLow" + "#all#..",
					"AggregationHigh" + "#!=#" + "0");
			directHardHardSemanticEdge.putSemanticAttribute("AggregationLow",
					attribute);
			directHardHardSemanticEdge.addPropEditableAttribute("03#"
					+ "AggregationLow");
			directHardHardSemanticEdge.addPropVisibleAttribute("03#"
					+ "AggregationLow");

			attribute = new ElemAttribute("AggregationHigh", "Integer",
					AttributeType.OPERATION, false, "AggregationHigh", "", 0,
					0, 4, "", "", 4, "#" + "AggregationHigh" + "#all#]\n",
					"AggregationHigh" + "#!=#" + "0");
			directHardHardSemanticEdge.putSemanticAttribute("AggregationHigh",
					attribute);
			directHardHardSemanticEdge.addPropEditableAttribute("04#"
					+ "AggregationHigh");
			directHardHardSemanticEdge.addPropVisibleAttribute("04#"
					+ "AggregationHigh");

			InstConcept instDirHardHardSemanticEdge = new InstConcept(
					"travHardPW", metaMetaPairwiseRelation,
					directHardHardSemanticEdge);

			directHardHardSemanticEdge.putSemanticAttribute("relationType",
					new ElemAttribute("relationType", "Class",
							AttributeType.OPERATION, true, "Relation Type", "",
							InstAttribute.class.getCanonicalName(), null, "",
							0, 6, "", "", 6, "#" + "relationType" + "#all#\n",
							""));
			directHardHardSemanticEdge.addPropEditableAttribute("06#"
					+ "relationType");
			directHardHardSemanticEdge.addPropVisibleAttribute("06#"
					+ "relationType");
			// directHardHardSemanticEdge.addPanelVisibleAttribute("06#"
			// + "relationType");
			// directHardHardSemanticEdge.addPanelSpacersAttribute("#"
			// + "relationType" + "#\n");

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
					.getSemanticExpressionTypes().get("LessOrEquals"), 1,
					false, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

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
					"required", "", "", 1, -1, "", "", -1, "", ""),
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("CONDNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHC, instVertexHC, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("condition", new ElemAttribute(
					"condition", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "condition", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("travHardPW",
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

			OpersConcept directStructHardHardSemanticEdge = new OpersConcept(
					"meansHardPW");

			attribute = new ElemAttribute("AggregationLow", "Integer",
					AttributeType.OPERATION, false, "Aggregation Low", "", 0,
					0, 3, "", "", 3, "[#" + "AggregationLow" + "#all#..",
					"AggregationHigh" + "#!=#" + "0");
			directStructHardHardSemanticEdge.putSemanticAttribute(
					"AggregationLow", attribute);
			directStructHardHardSemanticEdge.addPropEditableAttribute("03#"
					+ "AggregationLow");
			directStructHardHardSemanticEdge.addPropVisibleAttribute("03#"
					+ "AggregationLow");

			attribute = new ElemAttribute("AggregationHigh", "Integer",
					AttributeType.OPERATION, false, "AggregationHigh", "", 0,
					0, 4, "", "", 4, "#" + "AggregationHigh" + "#all#]\n",
					"AggregationHigh" + "#!=#" + "0");
			directStructHardHardSemanticEdge.putSemanticAttribute(
					"AggregationHigh", attribute);
			directStructHardHardSemanticEdge.addPropEditableAttribute("04#"
					+ "AggregationHigh");
			directStructHardHardSemanticEdge.addPropVisibleAttribute("04#"
					+ "AggregationHigh");

			InstConcept instDirStructHardHardSemanticEdge = new InstConcept(
					"meansHardPW", metaMetaPairwiseRelation,
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
			ias.add(new InstAttribute("mandatory", new ElemAttribute(
					"mandatory", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "mandatory", "", "", 1, -1, "", "", -1, "", ""),
					"mandatory#mandatory#true#true#true#1#-1#1#1"));

			ias.add(new InstAttribute("optional", new ElemAttribute("optional",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"optional", "", "", 1, -1, "", "", -1, "", ""),
					"optional#optional#false#true#true#1#-1#1#1"));

			ia = instDirStructHardHardSemanticEdge
					.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("MANSelected", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHC, instVertexHC, "Sel", "Sel");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("MANSelected1", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHC, instVertexHC, "Core", "Core");

			semanticExpressions.add(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);
			verifParentsOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexHC, instVertexHC, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("mandatory", new ElemAttribute(
					"mandatory", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "mandatory", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					instDirStructHardHardSemanticEdge, instVertexHC, "Sel",
					true, 1);

			t1 = new OpersExpr("Sel", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instVertexHC, "Sel", true, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("optional", new ElemAttribute("optional",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"optional", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("meansHardPW",
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

			OpersConcept directFeaFeatVertSemEdge = new OpersConcept(
					"parentFeatPW");
			InstConcept instDirFeaFeatVertSemEdge = new InstConcept(
					"parentFeatPW", metaMetaPairwiseRelation,
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("MANSelected1", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexF, instVertexF, "Core", "Core");

			semanticExpressions.add(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);
			verifParentsOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("MANNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexF, instVertexF, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("OPTSelected", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexF, instVertexF, "Core", "Core");

			semanticExpressions.add(t1);
			verifParentsOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("OPTNotAvailable", refas
					.getSemanticExpressionTypes().get("LessOrEquals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
					instVertexF, instVertexF, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			attribute = new ElemAttribute("optional", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "optional", "", "", 1, -1, "",
					"", -1, "", "");
			ias.add(new InstAttribute("optional", attribute,
					semanticExpressions));

			refas.getVariabilityVertex().put("parentFeatPW",
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

			OpersConcept directFeatFeatSideSemEdge = new OpersConcept(
					"altFeatPW");
			InstConcept instDirFeatFeatSideSemEdge = new InstConcept(
					"altFeatPW", metaMetaPairwiseRelation,
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
					.getSemanticExpressionTypes().get("LessOrEquals"), 1,
					false, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"conflict", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("requires", refas.getSemanticExpressionTypes()
					.get("LessOrEquals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexF, instVertexF, "Sel", "Sel");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("require", new ElemAttribute("require",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"condition", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("altFeatPW",
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

			OpersConcept semFeatOverTwoRelation = new OpersConcept("FeatOT");// featSemOverTwoRelList);
			InstConcept instVertexFFGR = new InstConcept("FeatOT",
					semFeatOverTwoRelation, metaMetaInstOverTwoRel);
			refas.getVariabilityVertex().put("FeatOT", instVertexFFGR);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("ANDhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexF,
					instVertexFFGR, "Core", "Core");

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);
			verifParentsOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexFFGR, instVertexF, "Sel", true, "TrueVal");

			t1 = new OpersExpr("ANDFSRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexF, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexFFGR, instVertexF, "Core", true, "TrueVal");

			t1 = new OpersExpr("ANDFCRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexF, t1, "Core");

			// verifRootOperSubActionNormal.addSemanticExpression(t1);
			verifParentsOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("and", new ElemAttribute("and",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "and",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("ORFConcept", refas.getSemanticExpressionTypes()
					.get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexF,
					instVertexFFGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexFFGR, instVertexF, "Sel", true, "FalseVal");

			t1 = new OpersExpr("ORFSRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexF, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexFFGR, instVertexF, "Core", true, "FalseVal");

			t1 = new OpersExpr("ORFCRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexF, t1, "Core");

			verifParentsOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("MUTEXFConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexF,
					instVertexFFGR, "Sel", "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			verifRootOperSubActionNormal.addSemanticExpression(t1);
			verifParentsOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexF, "Sel", 0);

			t1 = new OpersExpr("sub2", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexF, t1, 1);

			t1 = new OpersExpr("MUTEXFRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"), instVertexF, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexFFGR, instVertexF, "Core", true, "FalseVal");

			t1 = new OpersExpr("MUTEXFCRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexF, t1, "Core");

			verifParentsOperSubActionNormal.addSemanticExpression(t1);
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			verifRootOperSubActionNormal.addSemanticExpression(t1);
			verifParentsOperSubActionNormal.addSemanticExpression(t1);
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			verifRootOperSubActionNormal.addSemanticExpression(t1);
			verifParentsOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexFFGR, instVertexHC, null, "Sel", "FalseVal", true);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, t1, instVertexHC, "LowRange");

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexFFGR, instVertexHC, null, "Sel", "FalseVal", true);

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, t2, instVertexHC, "HighRange");

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t2);

			t1 = new OpersExpr("RANGEHardRel", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					instVertexFFGR, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexFFGR, instVertexF, "Core", true, "FalseVal");

			t1 = new OpersExpr("RANGEFCRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexFFGR, instVertexF, t1, "Core");

			verifParentsOperSubActionNormal.addSemanticExpression(t1);
			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			OpersConcept semAssetOperPairwiseRel = new OpersConcept(
					"AssetOperPW");
			InstConcept instSemAssetOperPairwiseRel = new InstConcept(
					"AssetOperPW", metaMetaPairwiseRelation,
					semAssetOperPairwiseRel);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("vaptoip", instEdge);
			instEdge.setIdentifier("vaptoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instSemAssetOperPairwiseRel, true);

			ia = instSemAssetOperPairwiseRel.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();

			ias.add(new InstAttribute("mandatory", new ElemAttribute(
					"mandatory", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "mandatory", "", "", 1, -1, "", "", -1, "", ""),
					"mandatory#mandatory#false#true#true#1#-1#1#1"));

			ia = instDirStructHardHardSemanticEdge
					.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("manLSelected1", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexAsset, instVertexHC, "Sel", "Sel");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("manLSelected1", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexAsset, instVertexHC, "Core", "Core");

			semanticExpressions.add(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);
			verifParentsOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("manLNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexAsset, instVertexHC, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("mandatory", new ElemAttribute(
					"mandatory", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "mandatory", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("AssetOperPW",
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

			OpersConcept semAssetPairwiseRel = new OpersConcept("AssetPW");
			InstConcept instSemAssetPairwiseRel = new InstConcept("AssetPW",
					metaMetaPairwiseRelation, semAssetPairwiseRel);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("DELNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexAsset, instVertexAsset, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("ASSENotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexF, instVertexF, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			ias.add(new InstAttribute("assembly", new ElemAttribute("assembly",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"assembly", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex()
					.put("AssetPW", instSemAssetPairwiseRel);

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

			OpersConcept semvarcntxPairwiseRel = new OpersConcept("VaClPW");

			InstConcept instSemvarcntxPairwiseRel = new InstConcept("VaClPW",
					metaMetaPairwiseRelation, semvarcntxPairwiseRel);

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
					AttributeType.OPTION, false, "Variable Context", "", "", 1,
					-1, "", "", -1, "", ""),
					"Variable Context##false#true#true#1#-1#1#1"));

			ia = instSemvarcntxPairwiseRel.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			ias.add(new InstAttribute("Variable Context", new ElemAttribute(
					"Variable Context", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "Variable Context", "", "", 1,
					-1, "", "", -1, "", ""), semanticExpressions));

			refas.getVariabilityVertex().put("VaClPW",
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

			// Feature to Feature

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ffgrtogr", instEdge);
			instEdge.setIdentifier("ffgrtogr");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
			instEdge.setTargetRelation(instVertexGR, true);
			instEdge.setSourceRelation(instVertexFFGR, true);

			InstConcept instFeatFeatFFFGR = new InstConcept("FeatToOT",
					metaMetaPairwiseRelation);
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

			InstConcept instFeatFeatFGRF = new InstConcept("FeatFromOT",
					metaMetaPairwiseRelation);
			refas.getVariabilityVertex().put("FeatFromOT", instFeatFeatFGRF);

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

			OpersConcept directSGSGSemEdge = new OpersConcept("SgSgPWAsso");
			attribute = new ElemAttribute("sourceLevel", "Integer",
					AttributeType.OPERATION, "Source Level", "", 1, false,
					new RangeDomain(0, 4, 0), 0, 8, "", "", 8,
					"SL: #sourceLevel#all# - ", "");
			directSGSGSemEdge.putSemanticAttribute("sourceLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			directSGSGSemEdge.addPropEditableAttribute("08#" + "sourceLevel");
			directSGSGSemEdge.addPropVisibleAttribute("08#" + "sourceLevel");

			attribute = new ElemAttribute("targetLevel", "Integer",
					AttributeType.OPERATION, "Target Level", "", 1, false,
					new RangeDomain(0, 4, 0), 0, 9, "", "", 9,
					"TL: #targetLevel#all#", "");
			directSGSGSemEdge.putSemanticAttribute("targetLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			directSGSGSemEdge.addPanelSpacersAttribute(":#" + "targetLevel"
					+ "#");
			directSGSGSemEdge.addPropEditableAttribute("09#" + "targetLevel");
			directSGSGSemEdge.addPropVisibleAttribute("09#" + "targetLevel");
			// directSGSGSemEdge.addPanelVisibleAttribute("09#" +
			// "targetLevel");

			attribute = new ElemAttribute("AggregationLow", "Integer",
					AttributeType.OPERATION, false, "Aggregation Low", "", 0,
					0, 7, "", "", 7, "[#" + "AggregationLow" + "#all#..",
					"AggregationHigh" + "#!=#" + "0");
			directSGSGSemEdge.putSemanticAttribute("AggregationLow", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));

			directSGSGSemEdge
					.addPropEditableAttribute("07#" + "AggregationLow");

			directSGSGSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

			attribute = new ElemAttribute("AggregationHigh", "Integer",
					AttributeType.OPERATION, false, "AggregationHigh", "", 0,
					0, 8, "", "", 8, "#" + "AggregationHigh" + "#all#\n",
					"AggregationHigh" + "#!=#" + "0");
			directSGSGSemEdge
					.putSemanticAttribute("AggregationHigh", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					directSGSGSemEdge.getIdentifier(), attribute.getName(),
					true));

			directSGSGSemEdge.addPropEditableAttribute("08#"
					+ "AggregationHigh");

			directSGSGSemEdge
					.addPropVisibleAttribute("08#" + "AggregationHigh");

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("contribution", new ElemAttribute(
					"contribution", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "contribution", "", "", 1, -1,
					"", "", -1, "", ""), semanticExpressions));

			semanticExpressions = new ArrayList<OpersExpr>();

			// Wrong expressions - correct for conflict

			t1 = new OpersExpr("CLEx SrcLv", refas.getSemanticExpressionTypes()
					.get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSGSGSemanticEdge, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
					"low");

			t1 = new OpersExpr("low: source & target", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("CLEx SrcLv", refas.getSemanticExpressionTypes()
					.get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSGSGSemanticEdge, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
					"high");

			t1 = new OpersExpr("high: source & target", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("CLEx SrcLv", refas.getSemanticExpressionTypes()
					.get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");

			t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
					instDirSGSGSemanticEdge, "ClaimExpLevel", "targetLevel");

			t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
					"Implies"), t1, t3);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
					"close");

			t1 = new OpersExpr("close: source & target", refas
					.getSemanticExpressionTypes().get("Implies"), t3, t1);

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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

			OpersConcept semanticSGSGGroupRelation = new OpersConcept("SgOT");// hardSemOverTwoRelList);

			InstConcept instVertexSGGR = new InstConcept("SgOT",
					semanticSGSGGroupRelation, metaMetaInstOverTwoRel);
			refas.getVariabilityVertex().put("SgOT", instVertexSGGR);

			OpersConcept directGRSGSemEdge = new OpersConcept("SgToOT");
			attribute = new ElemAttribute("targetLevel", "Integer",
					AttributeType.OPERATION, "Target Level", "", 1, false,
					new RangeDomain(0, 4, 0), 0, 9, "", "", 9,
					":#targetLevel#all#", "");
			directGRSGSemEdge.putSemanticAttribute("targetLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directGRSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directGRSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					directGRSGSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					directGRSGSemEdge.getIdentifier(), attribute.getName(),
					true));

			directGRSGSemEdge.addPanelSpacersAttribute(":#" + "targetLevel"
					+ "#");
			directGRSGSemEdge.addPropEditableAttribute("09#" + "targetLevel");
			directGRSGSemEdge.addPropVisibleAttribute("09#" + "targetLevel");
			// directGRSGSemEdge.addPanelVisibleAttribute("09#" +
			// "targetLevel");

			// FIXME remove, use other
			InstConcept instSgsgGRSG = new InstConcept("SgToOT",
					metaMetaPairwiseRelation, directGRSGSemEdge);

			refas.getVariabilityVertex().put("SgToOT", instSgsgGRSG);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("implication", new ElemAttribute(
					"implication", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "implication", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			OpersConcept directSGGRSemEdge = new OpersConcept("SgFromOT");
			attribute = new ElemAttribute("sourceLevel", "Integer",
					AttributeType.OPERATION, "Source Level", "", 1, false,
					new RangeDomain(0, 4, 0), 0, 8, "", "", 8,
					"#sourceLevel#all#", "");
			directSGGRSemEdge.putSemanticAttribute("sourceLevel", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			directSGGRSemEdge.addPropEditableAttribute("08#" + "sourceLevel");
			directSGGRSemEdge.addPropVisibleAttribute("08#" + "sourceLevel");
			// directSGGRSemEdge.addPanelVisibleAttribute("08#" +
			// "sourceLevel");

			attribute = new ElemAttribute("AggregationLow", "Integer",
					AttributeType.OPERATION, false, "Aggregation Low", "", 0,
					0, 7, "", "", 7, "[#" + "AggregationLow" + "#all#..",
					"AggregationHigh" + "#!=#" + "0");
			directSGGRSemEdge.putSemanticAttribute("AggregationLow", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));

			directSGGRSemEdge
					.addPropEditableAttribute("07#" + "AggregationLow");

			directSGGRSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

			attribute = new ElemAttribute("AggregationHigh", "Integer",
					AttributeType.OPERATION, false, "AggregationHigh", "", 0,
					0, 8, "", "", 8, "#" + "AggregationHigh" + "#all#]\n",
					"AggregationHigh" + "#!=#" + "0");
			directSGGRSemEdge
					.putSemanticAttribute("AggregationHigh", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSGGRSemEdge.getIdentifier(), attribute.getName(),
					true));

			directSGGRSemEdge.addPropEditableAttribute("08#"
					+ "AggregationHigh");

			directSGGRSemEdge
					.addPropVisibleAttribute("08#" + "AggregationHigh");

			InstConcept instSgsgSGR = new InstConcept("SgFromOT",

			metaMetaPairwiseRelation, directSGGRSemEdge);

			refas.getVariabilityVertex().put("SgFromOT", instSgsgSGR);

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

			ia = instVertexSGGR.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			t1 = new OpersExpr("sub2", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
					instVertexSG, instSgsgSGR, "ClaimExpLevel", "sourceLevel");

			t1 = new OpersExpr("sub1", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCFIXEDSUBEXP,
					instSgsgSGR, instDirSGSGSemanticEdge, t1, "TrueVal");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexSGGR, instVertexSG, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub2", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
					instVertexSG, instSgsgSGR, "ClaimExpLevel", "sourceLevel");

			t1 = new OpersExpr("sub1", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCFIXEDSUBEXP,
					instSgsgSGR, instDirSGSGSemanticEdge, t1, "TrueVal");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexSGGR, instVertexSG, t1, "Core");

			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
					instSgsgSGR, instDirSGSGSemanticEdge, t1, "FalseVal");

			t1 = new OpersExpr("ORhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexSGGR, instVertexSG, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			ias.add(new InstAttribute("or", new ElemAttribute("or",
					StringType.IDENTIFIER, AttributeType.OPTION, false, "or",
					"", "", 1, -1, "", "", -1, "", ""), semanticExpressions));

			OpersConcept directCVCGSemanticEdge = new OpersConcept("VaClPW");
			InstConcept instDirCVCGSemanticEdge = new InstConcept("VaClPW",
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
					AttributeType.OPTION, false, "Variable Context", "", "", 1,
					-1, "", "", -1, "", ""),
					"Variable Context##false#true#true#1#-1#1#1"));

			ia = instDirCVCGSemanticEdge.getInstAttribute("opersExprs");
			ias = (List<InstAttribute>) ia.getValue();

			semanticExpressions = new ArrayList<OpersExpr>();

			ias.add(new InstAttribute("Variable Context", new ElemAttribute(
					"Variable Context", StringType.IDENTIFIER,
					AttributeType.OPTION, false, "Variable Context", "", "", 1,
					-1, "", "", -1, "", ""), semanticExpressions));

			refas.getVariabilityVertex().put("VaClPW", instDirCVCGSemanticEdge);

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
			OpersConcept semanticOperClaimGroupRelation = new OpersConcept(
					"OperClOT");// hardSemOverTwoRelList);

			attribute = new ElemAttribute("AggregationLow", "Integer",
					AttributeType.OPERATION, false, "Aggregation Low", "", 0,
					0, 3, "", "", 3, "[#" + "AggregationLow" + "#all#..",
					"AggregationHigh" + "#!=#" + "0");
			semanticOperClaimGroupRelation.putSemanticAttribute(
					"AggregationLow", attribute);
			semanticOperClaimGroupRelation.addPropEditableAttribute("03#"
					+ "AggregationLow");
			semanticOperClaimGroupRelation.addPropVisibleAttribute("03#"
					+ "AggregationLow");

			attribute = new ElemAttribute("AggregationHigh", "Integer",
					AttributeType.OPERATION, false, "AggregationHigh", "", 0,
					0, 4, "", "", 4, "#" + "AggregationHigh" + "#all#]\n",
					"AggregationHigh" + "#!=#" + "0");
			semanticOperClaimGroupRelation.putSemanticAttribute(
					"AggregationHigh", attribute);
			semanticOperClaimGroupRelation.addPropEditableAttribute("04#"
					+ "AggregationHigh");
			semanticOperClaimGroupRelation.addPropVisibleAttribute("04#"
					+ "AggregationHigh");

			// semanticVertices = new ArrayList<AbstractSemanticVertex>();
			// semanticVertices.add(semClaim);

			InstConcept instVertexCLGR = new InstConcept("OperClOT",
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("ANDhardConcept", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
					instVertexHHGR, "Core", "Core");

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexOper, "Sel", true, "TrueVal");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexCLGR, instVertexOper, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexOper, "Core", true, "TrueVal");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexCLGR, instVertexOper, t1, "Core");

			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexHHGR, instVertexOper, "Sel", true, "FalseVal");

			t1 = new OpersExpr("ORhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexCLGR, instVertexOper, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexCLGR, instVertexOper, null, "Sel", "FalseVal",
					true);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOper, t2, instVertexCLGR, "LowRange");

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexCLGR, instVertexOper, null, "Sel", "FalseVal",
					true);

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOper, t2, instVertexCLGR, "HighRange");

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t2);

			t1 = new OpersExpr("RANGEHardRel", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					instVertexCLGR, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("OperClOT", instVertexCLGR);

			OpersConcept directOperClaimToSemanticEdge = new OpersConcept(
					"OperClToPW");

			InstConcept instDirOperClaimToSemanticEdge = new InstConcept(
					"OperClToPW", metaMetaPairwiseRelation,
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("OPERCLNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexCLGR, instVertexCL, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("operToClaim", new ElemAttribute(
					"operToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "operToClaim", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("OperClToPW",
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

			OpersConcept directOperClaimFromSemanticEdge = new OpersConcept(
					"OperClFromPW");

			InstConcept instDirOperClaimFromSemanticEdge = new InstConcept(
					"OperClFromPW", metaMetaPairwiseRelation,
					directOperClaimFromSemanticEdge);
			refas.getVariabilityVertex().put("OperClFromPW",
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
			OpersConcept semanticLFClaimGroupRelation = new OpersConcept(
					"LFtoClaimOTAsso"); // hardSemOverTwoRelList);

			InstConcept instVertexLFCLGR = new InstConcept("LfClOT",
					semanticLFClaimGroupRelation, metaMetaInstOverTwoRel);

			refas.getVariabilityVertex().put("LfClOT", instVertexLFCLGR);

			OpersConcept directFClaimToSemanticEdge = new OpersConcept(
					"FeClToPW");

			InstConcept instDirFClaimToSemanticEdge = new InstConcept(
					"FeClToPW", metaMetaPairwiseRelation,
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("OPERCLNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexLFCLGR, instVertexCL, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
					"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("FeClToPW",
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

			OpersConcept directFClaimFromSemanticEdge = new OpersConcept(
					"FeClFromPW");

			InstConcept instDirFClaimFromSemanticEdge = new InstConcept(
					"FeClFromPWo", metaMetaPairwiseRelation,
					directFClaimFromSemanticEdge);
			refas.getVariabilityVertex().put("FeClFromPW",
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

			OpersConcept directLFClaimSemanticEdge = new OpersConcept("LfClPW");

			InstConcept instDirLFClaimSemanticEdge = new InstConcept("LfClPW",
					metaMetaPairwiseRelation, directLFClaimSemanticEdge);
			refas.getVariabilityVertex().put("LfClPW",
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
			OpersConcept directClaimSGSemanticEdge = new OpersConcept(
					"ClaimSGPWAsso");
			attribute = new ElemAttribute("CLSGLevel", "Integer",
					AttributeType.OPERATION, "Relation Level",
					"Required level for the Claim (0..4)", 2, false,
					new RangeDomain(0, 4, 0), 0, 8, "", "", 8,
					"#CLSGLevel#all#", "");
			directClaimSGSemanticEdge.putSemanticAttribute("CLSGLevel",
					attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directClaimSGSemanticEdge.getIdentifier(), attribute
							.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					directClaimSGSemanticEdge.getIdentifier(), attribute
							.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					directClaimSGSemanticEdge.getIdentifier(), attribute
							.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directClaimSGSemanticEdge.getIdentifier(), attribute
							.getName(), true));

			directClaimSGSemanticEdge.addPropEditableAttribute("08#"
					+ "CLSGLevel");
			directClaimSGSemanticEdge.addPropVisibleAttribute("08#"
					+ "CLSGLevel");
			// directClaimSGSemanticEdge.addPanelVisibleAttribute("08#"
			// + "CLSGLevel");
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
					false, "ClaimToSG", "", "", 1, -1, "", "", -1, "", ""),
					"ClaimToSG##true#true#true#1#-1#1#1"));

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("ClaimToSG", new ElemAttribute(
					"ClaimToSG", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "ClaimToSG", "", "", 1, -1, "", "", -1, "", ""),
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

			OpersConcept directSDSGSemanticEdge = new OpersConcept("SdSgPW");
			attribute = new ElemAttribute("level", "Integer",
					AttributeType.OPERATION, "Level",
					"Required level for the SD (0..4)", 1, false,
					new RangeDomain(0, 4, 0), 0, 8, "", "", 8, "level#all#", "");
			directSDSGSemanticEdge.putSemanticAttribute("level", attribute);
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					directSDSGSemanticEdge.getIdentifier(),
					attribute.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					directSDSGSemanticEdge.getIdentifier(),
					attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					directSDSGSemanticEdge.getIdentifier(),
					attribute.getName(), true));
			simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(
					directSDSGSemanticEdge.getIdentifier(),
					attribute.getName(), true));

			directSDSGSemanticEdge.addPropEditableAttribute("08#" + "level");
			directSDSGSemanticEdge.addPropVisibleAttribute("08#" + "level");
			// directSDSGSemanticEdge.addPanelVisibleAttribute("08#" + "level");

			InstConcept instDirSDSGSemanticEdge = new InstConcept("SdSgPW",

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
					"SD##true#true#true#1#-1#1#1"));

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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

			refas.getVariabilityVertex().put("SdSgPW", instDirSDSGSemanticEdge);

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

			OpersConcept semanticAssetAssetOvertwoRel = new OpersConcept(
					"AssetOT");// hardSemOverTwoRelList);

			attribute = new ElemAttribute("AggregationLow", "Integer",
					AttributeType.OPERATION, false, "Aggregation Low", "", 0,
					0, 3, "", "", 3, "[#" + "AggregationLow" + "#all#..",
					"AggregationHigh" + "#!=#" + "0");
			semanticAssetAssetOvertwoRel.putSemanticAttribute("AggregationLow",
					attribute);
			semanticAssetAssetOvertwoRel.addPropEditableAttribute("03#"
					+ "AggregationLow");
			semanticAssetAssetOvertwoRel.addPropVisibleAttribute("03#"
					+ "AggregationLow");

			attribute = new ElemAttribute("AggregationHigh", "Integer",
					AttributeType.OPERATION, false, "AggregationHigh", "", 0,
					0, 4, "", "", 4, "#" + "AggregationHigh" + "#all#]\n",
					"AggregationHigh" + "#!=#" + "0");
			semanticAssetAssetOvertwoRel.putSemanticAttribute(
					"AggregationHigh", attribute);
			semanticAssetAssetOvertwoRel.addPropEditableAttribute("04#"
					+ "AggregationHigh");
			semanticAssetAssetOvertwoRel.addPropVisibleAttribute("04#"
					+ "AggregationHigh");

			InstConcept instVertexASSETGR = new InstConcept("AssetOT",
					semanticAssetAssetOvertwoRel, metaMetaInstOverTwoRel);

			refas.getVariabilityVertex().put("AssetOT", instVertexASSETGR);

			InstConcept instAssetassetASGR = new InstConcept("AssetToOT",
					metaMetaPairwiseRelation);
			refas.getVariabilityVertex().put("AssetToOT", instAssetassetASGR);

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

			InstConcept instAssetassetGRAS = new InstConcept("AssetFromOT",
					metaMetaPairwiseRelation);
			refas.getVariabilityVertex().put("AssetFromOT", instAssetassetGRAS);

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
			OpersConcept semanticAssetOperGroupRelation = new OpersConcept(
					"AssetOperOT");// hardSemOverTwoRelList);

			// semanticVertices = new ArrayList<AbstractSemanticVertex>();
			// semanticVertices.add(semOperationalization);

			InstConcept instVertexOPERGR = new InstConcept("AssetOperOT",
					semanticAssetOperGroupRelation, metaMetaInstOverTwoRel);

			refas.getVariabilityVertex().put("AssetOperOT", instVertexOPERGR);

			InstConcept instAssetOperAOGR = new InstConcept("AssetOperToOT",
					metaMetaPairwiseRelation);
			refas.getVariabilityVertex()
					.put("AssetOperToOT", instAssetOperAOGR);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("ANDhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexF,
					instVertexOPERGR, "Core", "Core");

			verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, "Sel", true, "TrueVal");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexOper, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, "Core", true, "TrueVal");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexOper, t1, "Core");

			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Or"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, "Sel", true, "FalseVal");

			t1 = new OpersExpr("ORhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexAsset, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, null, "Sel", "FalseVal",
					true);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, t1, instVertexAsset, "LowRange");

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, null, "Sel", "FalseVal",
					true);

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, t2, instVertexAsset, "HighRange");

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t2);

			t1 = new OpersExpr("RANGEHardRel", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					instVertexOPERGR, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("ANDhardConcept", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
					ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexF,
					instVertexOPERGR, "Core", "Core");

			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, "Sel", true, "TrueVal");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexOper, t1, "Sel");

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"And"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, "Core", true, "TrueVal");

			t1 = new OpersExpr("ANDhardRel", refas.getSemanticExpressionTypes()
					.get("DoubleImplies"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, instVertexOper, t1, "Core");

			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, null, "Sel", "FalseVal",
					true);

			t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"GreaterOrEq"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, t2, instVertexAsset, "LowRange");

			t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexOPERGR, instVertexAsset, null, "Sel", "FalseVal",
					true);

			t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
					"LessOrEquals"),
					ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexOPERGR, t2, instVertexAsset, "HighRange");

			t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
					"And"), t1, t3);

			t1 = new OpersExpr("RANGEHardRel", refas
					.getSemanticExpressionTypes().get("DoubleImplies"),
					instVertexOPERGR, "Sel", true, t1);

			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			// updateCoreOptOperSubActionNormal.addSemanticExpression(t1);
			semanticExpressions.add(t1);

			ias.add(new InstAttribute("range", new ElemAttribute("range",
					StringType.IDENTIFIER, AttributeType.OPTION, false,
					"range", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			OpersConcept groupAssetOperSemanticEdge = new OpersConcept(
					"AssetOperFromOT");

			InstConcept instAssetOperGRAO = new InstConcept("AssetOperFromOT",
					metaMetaPairwiseRelation, groupAssetOperSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("aofottoip", instEdge);
			instEdge.setIdentifier("aofottoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instAssetOperGRAO, true);

			ia = instAssetOperGRAO.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("mandatory", new ElemAttribute(
					"mandatory", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "mandatory", "", "", 1, -1, "", "", -1, "", ""),
					"mandatory#mandatory#true#true#true#1#-1#1#1"));

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("IMPNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexAsset, instVertexOPERGR, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("mandatory", new ElemAttribute(
					"mandatory", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "mandatory", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("AssetOperFromOT",
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

			OpersConcept directAssetOperSemanticEdge = new OpersConcept(
					"AssetOperPW");
			InstConcept instDirAssetOperSemanticEdge = new InstConcept(
					"AssetOperPW", metaMetaPairwiseRelation,
					directAssetOperSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("aoptoip", instEdge);
			instEdge.setIdentifier("aoptoip");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
			instEdge.setTargetRelation(instInfraPair, true);
			instEdge.setSourceRelation(instDirAssetOperSemanticEdge, true);

			ia = instDirAssetOperSemanticEdge.getInstAttribute("relTypesAttr");
			ias = (List<InstAttribute>) ia.getValue();
			ias.add(new InstAttribute("mandatory", new ElemAttribute(
					"mandatory", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "mandatory", "", "", 1, -1, "", "", -1, "", ""),
					"mandatory#mandatory#true#true#true#1#-1#1#1"));

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
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			t1 = new OpersExpr("IMPNotAvailable", refas
					.getSemanticExpressionTypes().get("Equals"),
					ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
					ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
					instVertexOPERGR, instVertexOper, "Exclu", "Exclu");

			semanticExpressions.add(t1);
			simulationExecOptOperSubActionNormal.addSemanticExpression(t1);
			simulScenExecOptOperSubActionNormal.addSemanticExpression(t1);
			verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
			verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
			// verifRootOperSubActionNormal.addSemanticExpression(t1);

			ias.add(new InstAttribute("mandatory", new ElemAttribute(
					"mandatory", StringType.IDENTIFIER, AttributeType.OPTION,
					false, "mandatory", "", "", 1, -1, "", "", -1, "", ""),
					semanticExpressions));

			refas.getVariabilityVertex().put("AssetOperPW",
					instDirAssetOperSemanticEdge);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("AssetOperPW-OOGR", instEdge);
			instEdge.setIdentifier("AssetOperPW-OOGR");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instVertexOper, true);
			instEdge.setSourceRelation(instDirAssetOperSemanticEdge, true);

			instEdge = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("AssetOperPW-OGRO", instEdge);
			instEdge.setIdentifier("AssetOperPW-OGRO");
			instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdge.setTargetRelation(instDirAssetOperSemanticEdge, true);
			instEdge.setSourceRelation(instVertexAsset, true);

			OpersConcept refasModel = new OpersConcept("REFAS");

			attribute = new ElemAttribute("TotalOrder", "Integer",
					AttributeType.EXECCURRENTSTATE, "***TotalOrder***", "", 0,
					false, new RangeDomain(0, 2000, 0), 2, -1, "", "", -1, "",
					"");
			simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					refasModel.getIdentifier(), attribute.getName(), true));
			refasModel.putSemanticAttribute("TotalOrder", attribute);

			semanticExpressions = new ArrayList<OpersExpr>(); // FIXME not used

			simsceExecOperLabeling2.setSemanticExpressions(semanticExpressions);

			InstConcept instRefasModel = null;
			instRefasModel = new InstConcept("REFAS", metaMetaModel, refasModel);
			refas.getVariabilityVertex().put("REFAS", instRefasModel);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
					instVertexSG, "Sel", 0);

			t1 = new OpersExpr("max soft goals", refas
					.getSemanticExpressionTypes().get("Sum"),
					ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
					instRefasModel, instVertexSG, t1, 0);

			semanticExpressions.add(t1); // FIXME not used

			// ----------------------

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

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
					instVertexF, "IsRootFeature", 0);

			t1 = new OpersExpr("Roots", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
					instVertexF, instVertexF, t1, 1);

			verifRootOperSubActionVerification.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Prod"), ExpressionVertexType.LEFTITERANYCONVARIABLE,
					instVertexF, "HasParent", 1);

			t1 = new OpersExpr("Parents", refas.getSemanticExpressionTypes()
					.get("Less"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexF, instVertexF, t1, 1);

			// verifParentsOperSubActionVerification.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE,
					instVertexGE, "Core", 0);

			t1 = new OpersExpr("Core", refas.getSemanticExpressionTypes().get(
					"Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
					instVertexGE, instRefasModel, t1, "TotalOrder");

			updateCoreOptOperSubActionNormal.addSemanticExpression(t1);

			semanticExpressions.add(t1);

			// --------------------------
			semanticExpressions = new ArrayList<OpersExpr>(); // FIXME not used

			simulationExecOperUniqueLabeling
					.setSemanticExpressions(semanticExpressions);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
					instVertexGE, "Order", 0);

			semanticExpressions.add(t1);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
					instVertexGE, "Opt", 0);

			semanticExpressions.add(t1);

			semanticExpressions = new ArrayList<OpersExpr>(); // FIXME not used

			simsceExecOperLabeling2.setSemanticExpressions(semanticExpressions);

			t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
					"Sum"), ExpressionVertexType.LEFTITERCONFIXEDVARIABLE,
					instVertexSG, "Sel", 0);

			semanticExpressions.add(t1);
		}
	}
}
