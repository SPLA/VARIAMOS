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
	private static OpersConcept verifDeadElemOper = null;
	private static OpersSubOperationExpType verifDeadElemSubOperNormal = null;

	private static OpersConcept verifFalseOptOper = null;
	private static OpersSubOperationExpType verifFalseOptOperSubActionNormal = null;

	private static OpersConcept verifParentsOper = null;
	private static OpersSubOperationExpType verifParentsOperSubActionNormal = null;
	private static OpersSubOperationExpType verifParentsOperSubActionToVerify = null;

	private static OpersConcept verifRootOper = null;
	private static OpersSubOperationExpType verifRootOperSubActionNormal = null;
	private static OpersSubOperationExpType verifRootOperSubActionRelaxable = null;
	private static OpersSubOperationExpType verifRootSubOperVeri = null;

	private static OpersConcept simulOper = null;
	private static OpersSubOperationExpType simulExecOptSubOperNormal = null;

	private static OpersConcept simulScenOper = null;
	private static OpersSubOperationExpType simulScenExecOptSubOperNormal = null;

	private static OpersConcept configTempOper = null;

	private static OpersSubOperationExpType configTemporalOptOperSubActionNormal = null;

	private static OpersConcept configPermOper = null;

	private static OpersSubOperationExpType configPermanentOptOperSubActionNormal = null;

	private static OpersConcept updCoreOper = null;
	private static OpersSubOperationExpType updCoreOptSubOperNormal = null;

	private static OpersConcept sasverSDallOper = null;
	private static OpersSubOperationExpType sasverSDallOperSubActionNormal = null;

	private static OpersConcept sasverSDCoreOper = null;
	private static OpersSubOperationExpType sasverSDCoreOperSubActionNormal = null;

	private static OpersConcept sasverSDneverOperationAction = null;
	private static OpersSubOperationExpType sasverSDneverOperSubActionNormal = null;

	private static OpersConcept sasverClCoreOperationAction = null;
	private static OpersSubOperationExpType sasverClCoreOperSubActionNormal = null;

	private static OpersConcept sasverClallOperationAction = null;
	private static OpersSubOperationExpType sasverClallOperSubActionNormal = null;

	private static OpersConcept sasverClneverOperationAction = null;
	private static OpersSubOperationExpType sasverClneverOperSubActionNormal = null;

	private static OpersConcept sasverCoreOpersOperationAction = null;
	private static OpersSubOperationExpType sasverCoreOpersOperSubActionNormal = null;

	private static OpersConcept sasverAllOpersOperationAction = null;
	private static OpersSubOperationExpType sasverAllOpersOperSubActionNormal = null;

	private static OpersConcept sasverNoLoopsOperationAction = null;
	private static OpersSubOperationExpType sasverNoLoopsOperSubActionNormal = null;
	private static OpersSubOperationExpType sasverNoLoopsOperSubActionRelaxable = null;

	private static OpersConcept sasverSGConflperationAction = null;
	private static OpersSubOperationExpType sasverSGConflOperSubActionNormal = null;
	private static OpersSubOperationExpType sasverSGConflOperSubActionRelaxable = null;
	private static OpersSubOperationExpType sasverSGConflOperSubActionVerification = null;

	private static OpersConcept sasverConflClSDOperationAction = null;
	private static OpersSubOperationExpType sasverConflClSDOperSubActionNormal = null;
	private static OpersSubOperationExpType sasverConflClSDOperSubActionRelaxable = null;
	private static OpersSubOperationExpType sasverConflClSDOperSubActionVerification = null;

	private static OpersConcept sasverConflClOperationAction = null;
	private static OpersSubOperationExpType sasverConflClOperSubActionNormal = null;
	private static OpersSubOperationExpType sasverConflClOperSubActionRelaxable = null;
	private static OpersSubOperationExpType sasverConflClOperSubActionVerification = null;

	private static OpersConcept sasverConflSDOperationAction = null;
	private static OpersSubOperationExpType sasverConflSDOperSubActionNormal = null;
	private static OpersSubOperationExpType sasverConflSDOperSubActionRelaxable = null;

	private static OpersSubOperation simulSubOperationAction = null;
	private static OpersSubOperation simulPreUpdateSubOperationAction = null;
	private static OpersSubOperation simSceSubOperationAction = null;
	private static OpersSubOperation verifRootSubOperationAction = null;
	private static OpersSubOperation verifParentsSubOperationAction = null;
	private static OpersSubOperation updateCoreSubOperationAction = null;
	private static OpersSubOperation verifFalseOptSubOperationAction = null;
	private static OpersSubOperation verifDeadElemSubOperationAction = null;

	private static OpersSubOperation sasverSDCoreOperationSubAction = null;
	private static OpersSubOperation sasverSDallOperationSubAction = null;
	private static OpersSubOperation sasverSDneverOperationSubAction = null;
	private static OpersSubOperation sasverClCoreOperationSubAction = null;
	private static OpersSubOperation sasverClallOperationSubAction = null;
	private static OpersSubOperation sasverClneverOperationSubAction = null;
	private static OpersSubOperation sasverCoreOpersOperationSubAction = null;
	private static OpersSubOperation sasverAllOpersOperationSubAction = null;
	private static OpersSubOperation sasverNoLoopsOperationSubAction = null;
	private static OpersSubOperation sasverSGConflOperationSubAction = null;
	private static OpersSubOperation sasverConflClSDOperationSubAction = null;
	private static OpersSubOperation sasverConflSDOperationSubAction = null;
	private static OpersSubOperation sasverConflClOperationSubAction = null;

	private static OpersLabeling simulPreUpdateOperUniLab = new OpersLabeling(
			"unique");

	private static OpersLabeling simulExecOperUniLab = new OpersLabeling(
			"unique");

	private static OpersLabeling simsceExecOperLabeling1 = new OpersLabeling(
			"all");

	private static OpersLabeling simsceExecOperLab2 = new OpersLabeling("once"); // TODO
	// define
	// max
	// for SG
	private static OpersLabeling updateCoreOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling verifDeadElemOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling verifFalseOptElemOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling verifParentsElemOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverSDCoreOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverSDallOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverSDneverOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverClCoreOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverClallOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverClneverOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverCoreOpersOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverAllOpersOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverNoLoopsOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverSGConflOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverConflClSDOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverConflClOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static OpersLabeling sasverConflSDOperUniqueLabeling = new OpersLabeling(
			"unique");
	private static InstElement metaMetaModel = null;
	private static InstElement metaOperationMenu = null;
	private static InstElement metaOperationAction = null;
	private static InstElement metaOperationSubAction = null;
	private static InstElement metaLabeling = null;
	private static InstElement metaExpType = null;
	private static InstConcept metaMetaInstConcept = null;
	private static InstElement metaMetaPairwiseRelation = null;
	private static InstConcept metaMetaInstOverTwoRel = null;
	private static InstElement infraMetaMetaConcept = null;
	private static InstElement infraMetaMetaPairwiseRelation = null;
	private static InstElement infraMetaMetaOverTwoRelation = null;
	private static InstPairwiseRel metaPairwRelCCExt = null;
	private static InstPairwiseRel metaPairwRelOCExt = null;
	private static InstPairwiseRel metaPairwRelAso = null;

	private static InstConcept instVertexGE = null;
	private static InstConcept instVertexSG = null;
	private static InstConcept instVertexF = null;
	private static InstConcept instVertexHC = null;

	public static void createOpersMetaModel(ModelInstance refas, boolean empty) {
		createOpersMetaModelOpers(refas, empty);
		createOpersMetaModelnmElements(refas, empty);
		if (!empty) {
			createOpersMetaModelGeneralElement(refas);
			createOpersMetaModelFeaturesModel(refas);
			createOpersMetaModelElements(refas);
			createREFASMetaModelElement(refas);
		}
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

			OpersSubOperation operationSubAction = new OpersSubOperation(2,
					"None");

			InstConcept instOperationSubAction = new InstConcept("None",
					metaOperationSubAction, operationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("type").setValue("None");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(2);

			refas.getVariabilityVertex().put("None", instOperationSubAction);

			OpersSubOperationExpType operSubActionNormal = new OpersSubOperationExpType();
			operSubActionNormal.setIdentifier("NORMAL");

			InstConcept instOperSubOperationExpType = new InstConcept(
					"exptype", metaExpType, operSubActionNormal);

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

			InstConcept instLabeling = new InstConcept("None-lab",
					metaLabeling, operationLabeling);

			instLabeling.getInstAttribute("includeLabel").setValue(false);
			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("None-lab", instLabeling);

			InstPairwiseRel instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("none-lab", instEdgeOper);
			instEdgeOper.setIdentifier("none-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

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
			instOperationGroup.getInstAttribute("index").setValue(1);

			simulOper = new OpersConcept("BasicSimulOper");

			InstConcept instOperationAction = new InstConcept("BasicSimulOper",
					metaOperationAction, simulOper);
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

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-menu", instEdgeOper);
			instEdgeOper.setIdentifier("sim-menu");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			// OpersSubOperation operationSubAction = new OpersSubOperation(1,
			// "BasSim-Pre-Validation");
			//
			// //
			// simulationOperationAction.addExpressionSubAction(operationSubAction);
			//
			// InstConcept instOperationSubAction = new InstConcept(
			// "BasSim-Pre-Validation", metaOperationSubAction,
			// operationSubAction);
			// instOperationSubAction.getInstAttribute("name").setValue(" ");
			// instOperationSubAction.getInstAttribute("type").setValue(
			// "Single verification");
			// instOperationSubAction.getInstAttribute("showDashboard").setValue(
			// false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			// instOperationSubAction.getInstAttribute("index").setValue(1);
			//
			// refas.getVariabilityVertex().put("BasSim-Pre-Validation",
			// instOperationSubAction);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("sim-pre-val", instEdgeOper);
			// instEdgeOper.setIdentifier("sim-pre-val");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instOperationSubAction, true);
			// instEdgeOper.setSourceRelation(instOperationAction, true);
			//
			// simulationPreValOptOperSubActionNormal = new
			// OpersSubOperationExpType();
			//
			// InstConcept instOperSubOperationExpType = new InstConcept(
			// "exptype", metaExpType,
			// simulationPreValOptOperSubActionNormal);
			//
			// instOperSubOperationExpType.getInstAttribute("suboperexptype")
			// .setValue("NORMAL");
			//
			// ((List<InstAttribute>) instOperationSubAction
			// .getInstAttributeValue("exptype")).add(new InstAttribute(
			// "enum1", new ElemAttribute("EnumValue",
			// StringType.IDENTIFIER, AttributeType.SYNTAX, false,
			// "Enumeration Value", "", "", 1, -1, "", "", -1, "",
			// ""), instOperSubOperationExpType));
			//
			// OpersLabeling operationLabeling = new OpersLabeling("unique");
			//
			// // operationSubAction.addOperationLabeling(operationLabeling);
			//
			// InstConcept instLabeling = new InstConcept(
			// "BasSim-Pre-Validation-lab", metaLabeling,
			// operationLabeling);
			//
			// instLabeling.getInstAttribute("labelId").setValue("L1");
			// instLabeling.getInstAttribute("position").setValue(1);
			// instLabeling.getInstAttribute("once").setValue(false);
			// instLabeling.getInstAttribute("order").setValue(false);
			//
			// refas.getVariabilityVertex().put("BasSim-Pre-Validation-lab",
			// instLabeling);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("sim-pre-val-lab",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("sim-pre-val-lab");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instLabeling, true);
			// instEdgeOper.setSourceRelation(instOperationSubAction, true);
			//
			simulPreUpdateSubOperationAction = new OpersSubOperation(2,
					"BasSim-Pre-UpdateSubOper");

			instOperationSubAction = new InstConcept(
					"BasSim-Pre-UpdateSubOper", metaOperationSubAction,
					simulPreUpdateSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Single update");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(2);

			refas.getVariabilityVertex().put("BasSim-Pre-UpdateSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-pre-upd", instEdgeOper);
			instEdgeOper.setIdentifier("sim-pre-upd");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			OpersSubOperationExpType simulationPreUpdOptOperSubActionNormal = new OpersSubOperationExpType();

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

			simulPreUpdateOperUniLab = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("BasSim-pre-update-lab",
					metaLabeling, simulPreUpdateOperUniLab);
			instLabeling.getInstAttribute("includeLabel").setValue(false);
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
					"BasSim-ExecutionSubOper");

			List<OpersExpr> semanticExpressions = new ArrayList<OpersExpr>();

			simulExecOperUniLab.setSemanticExpressions(semanticExpressions);

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

			instOperationSubAction = new InstConcept("BasSim-ExecutionSubOper",
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
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					true);
			instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("index").setValue(3);

			refas.getVariabilityVertex().put("BasSim-ExecutionSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sim-exec", instEdgeOper);
			instEdgeOper.setIdentifier("sim-exec");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			simulExecOptSubOperNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, simulExecOptSubOperNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			instLabeling = new InstConcept("BasSim-Execution-lab",
					metaLabeling, simulExecOperUniLab);
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
			// sortatt.add(new InstAttribute("enum2", new ElemAttribute(
			// "EnumValue", StringType.IDENTIFIER, AttributeType.SYNTAX,
			// false, "Enumeration Value", "", "", 1, -1, "", "", -1, "",
			// ""), LabelingOrder.MIN));

			refas.getVariabilityVertex().put("BasSim-Execution-lab",
					instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("bassim-execution-lab",
					instEdgeOper);
			instEdgeOper.setIdentifier("bassim-execution-lab");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// OpersLabeling simulationExecOperLowLabeling = null;
			// simulationExecOperLowLabeling = new OpersLabeling("lowlab");
			//
			// instLabeling = new InstConcept("BasSim-Execution-lowLab",
			// metaLabeling, simulationExecOperLowLabeling);
			// instLabeling.getInstAttribute("includeLabel").setValue(false);
			// instLabeling.getInstAttribute("labelId").setValue("L2");
			// instLabeling.getInstAttribute("position").setValue(1);
			// instLabeling.getInstAttribute("once").setValue(false);
			// instLabeling.getInstAttribute("order").setValue(false);
			//
			// refas.getVariabilityVertex().put("BasSim-Execution-lowLab",
			// instLabeling);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("bassim-execution-lowlab",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("bassim-execution-lowlab");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instLabeling, true);
			// instEdgeOper.setSourceRelation(instOperationSubAction, true);
			//
			// simulationExecOperLowLabeling = new OpersLabeling("lowlab");
			//
			// instLabeling = new InstConcept("BasSim-Execution-lowLab",
			// metaLabeling, simulationExecOperLowLabeling);
			// instLabeling.getInstAttribute("includeLabel").setValue(false);
			// instLabeling.getInstAttribute("labelId").setValue("L2");
			// instLabeling.getInstAttribute("position").setValue(1);
			// instLabeling.getInstAttribute("once").setValue(false);
			// instLabeling.getInstAttribute("order").setValue(false);
			//
			// refas.getVariabilityVertex().put("BasSim-Execution-lowLab",
			// instLabeling);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("bassim-execution-lowlab",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("bassim-execution-lowlab");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instLabeling, true);
			// instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// OpersSubOperation operationSubAction = new OpersSubOperation(4,
			// "BasSim-Post-Validation");
			//
			// //
			// simulationOperationAction.addExpressionSubAction(operationSubAction);
			//
			// instOperationSubAction = new
			// InstConcept("BasSim-Post-Validation",
			// metaOperationSubAction, operationSubAction);
			// instOperationSubAction.getInstAttribute("name").setValue(" ");
			// instOperationSubAction.getInstAttribute("type").setValue(
			// "Single verification");
			// instOperationSubAction.getInstAttribute("showDashboard").setValue(
			// false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			// refas.getVariabilityVertex().put("BasSim-Post-Validation",
			// instOperationSubAction);
			// instOperationSubAction.getInstAttribute("index").setValue(4);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("sim-pos-val", instEdgeOper);
			// instEdgeOper.setIdentifier("sim-pos-val");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instOperationSubAction, true);
			// instEdgeOper.setSourceRelation(instOperationAction, true);
			//
			// simulationPosValOptOperSubActionNormal = new
			// OpersSubOperationExpType();
			//
			// instOperSubOperationExpType = new InstConcept("exptype",
			// metaExpType, simulationPosValOptOperSubActionNormal);
			//
			// instOperSubOperationExpType.getInstAttribute("suboperexptype")
			// .setValue("NORMAL");
			//
			// ((List<InstAttribute>) instOperationSubAction
			// .getInstAttributeValue("exptype")).add(new InstAttribute(
			// "enum1", new ElemAttribute("EnumValue",
			// StringType.IDENTIFIER, AttributeType.SYNTAX, false,
			// "Enumeration Value", "", "", 1, -1, "", "", -1, "",
			// ""), instOperSubOperationExpType));
			//
			// operationLabeling = new OpersLabeling("unique");
			//
			// //
			// simulOperationSubAction.addOperationLabeling(operationLabeling);
			//
			// instLabeling = new InstConcept("BasSim-pos-val-lab",
			// metaLabeling,
			// operationLabeling);
			//
			// instLabeling.getInstAttribute("labelId").setValue("L1");
			// instLabeling.getInstAttribute("position").setValue(1);
			// instLabeling.getInstAttribute("once").setValue(false);
			// instLabeling.getInstAttribute("order").setValue(false);
			//
			// refas.getVariabilityVertex()
			// .put("BasSim-pos-val-lab", instLabeling);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("sim-pos-val-lab",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("sim-pos-val-lab");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instLabeling, true);
			// instEdgeOper.setSourceRelation(instOperationSubAction, true);
			//
			// operationSubAction = new OpersSubOperation(5,
			// "BasSim-Post-Update");
			//
			// //
			// simulationOperationAction.addExpressionSubAction(operationSubAction);
			//
			// instOperationSubAction = new InstConcept("BasSim-Post-Update",
			// metaOperationSubAction, operationSubAction);
			// instOperationSubAction.getInstAttribute("name").setValue(" ");
			// instOperationSubAction.getInstAttribute("type").setValue(
			// "Single update");
			// instOperationSubAction.getInstAttribute("showDashboard").setValue(
			// false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			// instOperationSubAction.getInstAttribute("index").setValue(5);
			//
			// refas.getVariabilityVertex().put("BasSim-Post-Update",
			// instOperationSubAction);
			//
			// simulationPostUpdOptOperSubActionNormal = new
			// OpersSubOperationExpType();
			//
			// instOperSubOperationExpType = new InstConcept("exptype",
			// metaExpType, simulationPostUpdOptOperSubActionNormal);
			//
			// instOperSubOperationExpType.getInstAttribute("suboperexptype")
			// .setValue("NORMAL");
			//
			// ((List<InstAttribute>) instOperationSubAction
			// .getInstAttributeValue("exptype")).add(new InstAttribute(
			// "enum1", new ElemAttribute("EnumValue",
			// StringType.IDENTIFIER, AttributeType.SYNTAX, false,
			// "Enumeration Value", "", "", 1, -1, "", "", -1, "",
			// ""), instOperSubOperationExpType));
			//
			// operationLabeling = new OpersLabeling("unique");
			//
			// //
			// simulOperationSubAction.addOperationLabeling(operationLabeling);
			//
			// instLabeling = new InstConcept("BasSim-Post-Update-lab",
			// metaLabeling, operationLabeling);
			//
			// instLabeling.getInstAttribute("labelId").setValue("L1");
			// instLabeling.getInstAttribute("position").setValue(1);
			// instLabeling.getInstAttribute("once").setValue(false);
			// instLabeling.getInstAttribute("order").setValue(false);
			//
			// refas.getVariabilityVertex().put("BasSim-Post-Update-lab",
			// instLabeling);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("bassim-post-update-lab",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("bassim-post-update-lab");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instLabeling, true);
			// instEdgeOper.setSourceRelation(instOperationSubAction, true);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("sim-pos-upd", instEdgeOper);
			// instEdgeOper.setIdentifier("sim-pos-upd");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instOperationSubAction, true);
			// instEdgeOper.setSourceRelation(instOperationAction, true);

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
			instOperationGroup.getInstAttribute("index").setValue(1);

			simulScenOper = new OpersConcept("SceSimulOper");

			instOperationAction = new InstConcept("SceSimulOper",
					metaOperationAction, simulScenOper);
			refas.getVariabilityVertex().put("SceSimulOper",
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

			// operationSubAction = new OpersSubOperation(1,
			// "SimSce-Pre-Validation");
			//
			// //
			// simulScenOperationAction.addExpressionSubAction(operationSubAction);
			//
			// instOperationSubAction = new InstConcept("SimSce-Pre-Validation",
			// metaOperationSubAction, operationSubAction);
			// instOperationSubAction.getInstAttribute("name").setValue(" ");
			//
			// instOperationSubAction.getInstAttribute("type").setValue(
			// "Single verification");
			// instOperationSubAction.getInstAttribute("showDashboard").setValue(
			// false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			// instOperationSubAction.getInstAttribute("index").setValue(1);
			//
			// refas.getVariabilityVertex().put("SimSce-Pre-Validation",
			// instOperationSubAction);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("simsce-pre-val",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("simsce-pre-val");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instOperationSubAction, true);
			// instEdgeOper.setSourceRelation(instOperationAction, true);
			//
			// simulScenPreValOptOperSubActionNormal = new
			// OpersSubOperationExpType();
			//
			// instOperSubOperationExpType = new InstConcept("exptype",
			// metaExpType, simulScenPreValOptOperSubActionNormal);
			//
			// instOperSubOperationExpType.getInstAttribute("suboperexptype")
			// .setValue("NORMAL");
			//
			// ((List<InstAttribute>) instOperationSubAction
			// .getInstAttributeValue("exptype")).add(new InstAttribute(
			// "enum1", new ElemAttribute("EnumValue",
			// StringType.IDENTIFIER, AttributeType.SYNTAX, false,
			// "Enumeration Value", "", "", 1, -1, "", "", -1, "",
			// ""), instOperSubOperationExpType));
			//
			// operationLabeling = new OpersLabeling("unique");
			//
			// //
			// simulOperationSubAction.addOperationLabeling(operationLabeling);
			//
			// instLabeling = new InstConcept("SimSce-pre-val-lab",
			// metaLabeling,
			// operationLabeling);
			//
			// instLabeling.getInstAttribute("labelId").setValue("L1");
			// instLabeling.getInstAttribute("position").setValue(1);
			// instLabeling.getInstAttribute("once").setValue(false);
			// instLabeling.getInstAttribute("order").setValue(false);
			//
			// refas.getVariabilityVertex()
			// .put("SimSce-pre-val-lab", instLabeling);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("simsce-pre-val-lab",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("simsce-pre-val-lab");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instLabeling, true);
			// instEdgeOper.setSourceRelation(instOperationSubAction, true);
			//
			// operationSubAction = new OpersSubOperation(2,
			// "SimSce-Pre-Update");
			//
			// //
			// simulScenOperationAction.addExpressionSubAction(operationSubAction);
			//
			// instOperationSubAction = new InstConcept("SimSce-Pre-Update",
			// metaOperationSubAction, operationSubAction);
			// instOperationSubAction.getInstAttribute("name").setValue(" ");
			// instOperationSubAction.getInstAttribute("type").setValue(
			// "Single update");
			// instOperationSubAction.getInstAttribute("showDashboard").setValue(
			// false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			// instOperationSubAction.getInstAttribute("index").setValue(2);
			//
			// refas.getVariabilityVertex().put("SimSce-Pre-Update",
			// instOperationSubAction);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("simsce-pre-upd",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("simsce-pre-upd");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instOperationSubAction, true);
			// instEdgeOper.setSourceRelation(instOperationAction, true);
			//
			// simulScenPreValOptOperSubActionNormal = new
			// OpersSubOperationExpType();
			//
			// instOperSubOperationExpType = new InstConcept("exptype",
			// metaExpType, simulScenPreValOptOperSubActionNormal);
			//
			// instOperSubOperationExpType.getInstAttribute("suboperexptype")
			// .setValue("NORMAL");
			//
			// ((List<InstAttribute>) instOperationSubAction
			// .getInstAttributeValue("exptype")).add(new InstAttribute(
			// "enum1", new ElemAttribute("EnumValue",
			// StringType.IDENTIFIER, AttributeType.SYNTAX, false,
			// "Enumeration Value", "", "", 1, -1, "", "", -1, "",
			// ""), instOperSubOperationExpType));
			//
			// operationLabeling = new OpersLabeling("unique");
			//
			// //
			// simulOperationSubAction.addOperationLabeling(operationLabeling);
			//
			// instLabeling = new InstConcept("SimSce-pre-upd-lab",
			// metaLabeling,
			// operationLabeling);
			//
			// instLabeling.getInstAttribute("labelId").setValue("L1");
			// instLabeling.getInstAttribute("position").setValue(1);
			// instLabeling.getInstAttribute("once").setValue(false);
			// instLabeling.getInstAttribute("order").setValue(false);
			//
			// refas.getVariabilityVertex()
			// .put("SimSce-pre-upd-lab", instLabeling);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("simsce-pre-upd-lab",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("simsce-pre-upd-lab");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instLabeling, true);
			// instEdgeOper.setSourceRelation(instOperationSubAction, true);

			simSceSubOperationAction = new OpersSubOperation(3,
					"SceSim-Execution");
			// simulScenOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("SceSim-Execution",
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
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					true);
			instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("index").setValue(3);

			refas.getVariabilityVertex().put("SceSim-Execution",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("simsce-exec", instEdgeOper);
			instEdgeOper.setIdentifier("simsce-exec");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			simulScenExecOptSubOperNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, simulScenExecOptSubOperNormal);

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
					simsceExecOperLab2);

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

			// operationSubAction = new OpersSubOperation(4,
			// "SimSce-Post-Validation");
			//
			// //
			// simulScenOperationAction.addExpressionSubAction(operationSubAction);
			//
			// instOperationSubAction = new
			// InstConcept("SimSce-Post-Validation",
			// metaOperationSubAction, operationSubAction);
			// instOperationSubAction.getInstAttribute("name").setValue(" ");
			// instOperationSubAction.getInstAttribute("type").setValue(
			// "Single verification");
			// instOperationSubAction.getInstAttribute("showDashboard").setValue(
			// true);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			// instOperationSubAction.getInstAttribute("index").setValue(4);
			//
			// refas.getVariabilityVertex().put("SimSce-Post-Validation",
			// instOperationSubAction);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("simsce-pos-val",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("simsce-pos-val");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instOperationSubAction, true);
			// instEdgeOper.setSourceRelation(instOperationAction, true);
			//
			// simulScenPosValOptOperSubActionNormal = new
			// OpersSubOperationExpType();
			//
			// instOperSubOperationExpType = new InstConcept("exptype",
			// metaExpType, simulScenPosValOptOperSubActionNormal);
			//
			// instOperSubOperationExpType.getInstAttribute("suboperexptype")
			// .setValue("NORMAL");
			//
			// ((List<InstAttribute>) instOperationSubAction
			// .getInstAttributeValue("exptype")).add(new InstAttribute(
			// "enum1", new ElemAttribute("EnumValue",
			// StringType.IDENTIFIER, AttributeType.SYNTAX, false,
			// "Enumeration Value", "", "", 1, -1, "", "", -1, "",
			// ""), instOperSubOperationExpType));
			//
			// operationLabeling = new OpersLabeling("unique");
			//
			// //
			// simulOperationSubAction.addOperationLabeling(operationLabeling);
			//
			// instLabeling = new InstConcept("SimSce-pos-val-lab",
			// metaLabeling,
			// operationLabeling);
			//
			// instLabeling.getInstAttribute("labelId").setValue("L1");
			// instLabeling.getInstAttribute("position").setValue(1);
			// instLabeling.getInstAttribute("once").setValue(false);
			// instLabeling.getInstAttribute("order").setValue(false);
			//
			// refas.getVariabilityVertex()
			// .put("SimSce-pos-val-lab", instLabeling);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("simsce-pos-val-lab",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("simsce-pos-val-lab");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instLabeling, true);
			// instEdgeOper.setSourceRelation(instOperationSubAction, true);
			//
			// operationSubAction = new OpersSubOperation(5,
			// "SimSce-Post-Update");
			// //
			// simulScenOperationAction.addExpressionSubAction(operationSubAction);
			//
			// instOperationSubAction = new InstConcept("SimSce-Post-Update",
			// metaOperationSubAction, operationSubAction);
			// instOperationSubAction.getInstAttribute("name").setValue(" ");
			// instOperationSubAction.getInstAttribute("type").setValue(
			// "Single update");
			// instOperationSubAction.getInstAttribute("showDashboard").setValue(
			// false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			// instOperationSubAction.getInstAttribute("index").setValue(5);
			//
			// refas.getVariabilityVertex().put("SimSce-Post-Update",
			// instOperationSubAction);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("simsce-pos-upd",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("simsce-pos-upd");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instOperationSubAction, true);
			// instEdgeOper.setSourceRelation(instOperationAction, true);
			//
			// simulScenPostUpdOptOperSubActionNormal = new
			// OpersSubOperationExpType();
			//
			// instOperSubOperationExpType = new InstConcept("exptype",
			// metaExpType, simulScenPostUpdOptOperSubActionNormal);
			//
			// instOperSubOperationExpType.getInstAttribute("suboperexptype")
			// .setValue("NORMAL");
			//
			// ((List<InstAttribute>) instOperationSubAction
			// .getInstAttributeValue("exptype")).add(new InstAttribute(
			// "enum1", new ElemAttribute("EnumValue",
			// StringType.IDENTIFIER, AttributeType.SYNTAX, false,
			// "Enumeration Value", "", "", 1, -1, "", "", -1, "",
			// ""), instOperSubOperationExpType));
			//
			// operationLabeling = new OpersLabeling("unique");
			//
			// //
			// simulOperationSubAction.addOperationLabeling(operationLabeling);
			//
			// instLabeling = new InstConcept("SimSce-Post-Update-lab",
			// metaLabeling, operationLabeling);
			//
			// instLabeling.getInstAttribute("labelId").setValue("L1");
			// instLabeling.getInstAttribute("position").setValue(1);
			// instLabeling.getInstAttribute("once").setValue(false);
			//
			// refas.getVariabilityVertex().put("SimSce-Post-Update-lab",
			// instLabeling);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("simSce-Post-Update-lab",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("simSce-Post-Update-lab");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instLabeling, true);
			// instEdgeOper.setSourceRelation(instOperationSubAction, true);

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

			// ---
			sasverSDCoreOper = new OpersConcept("SDCoreOper");

			instOperationAction = new InstConcept("SDCoreOper",
					metaOperationAction, sasverSDCoreOper);
			refas.getVariabilityVertex().put("SDCoreOper", instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify SoftDeps on the Model");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("visible").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-menu-sd", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-sd");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverSDCoreOperationSubAction = new OpersSubOperation(1,
					"SDCoreSubOper");
			// updateCoreOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("SDCoreSubOper",
					metaOperationSubAction, sasverSDCoreOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Update Error");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier update");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outSd");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("SDCoreSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sd-core", instEdgeOper);
			instEdgeOper.setIdentifier("sd-core");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverSDCoreOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverSDCoreOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverSDCoreOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("sd-core-lab", metaLabeling,
					sasverSDCoreOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("sd-core-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sd-core-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("sd-core-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverSDallOper = new OpersConcept("SDAlwaysActOper");

			instOperationAction = new InstConcept("SDAlwaysActOper",
					metaOperationAction, sasverSDallOper);
			refas.getVariabilityVertex().put("SDAlwaysActOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify SoftDeps Always Active");
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
					"SDAlwaysActSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("SDAlwaysActSubOper",
					metaOperationSubAction, sasverSDallOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"This SD is selected in all the solutions.");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("errorMsg").setValue(
					"Please review the SD. #number# SD always selected.");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("SDCoreOper");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outSd");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("SDAlwaysActSubOper",
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
			refas.getConstraintInstEdges().put("sd-all-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("sd-all-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverSDneverOperationAction = new OpersConcept("SDneverActOper");

			instOperationAction = new InstConcept("SDneverActOper",
					metaOperationAction, sasverSDneverOperationAction);
			refas.getVariabilityVertex().put("SDneverActOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify SoftDeps Never Allowed");
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
					"SDneverActSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("SDneverActSubOper",
					metaOperationSubAction, sasverSDneverOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorHint")
					.setValue(
							"Double Check the conditional expression. This SD is never selected.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review the SDs conditional expressions.\n #number# SD are never selected.");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getDeadElements
									.toString());

			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("SDCoreOper");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outSd");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("SDneverActSubOper",
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

			instLabeling = new InstConcept("sd-never-lab", metaLabeling,
					sasverSDneverOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("sd-never-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sd-never-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("sd-never-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverClCoreOperationAction = new OpersConcept("ClCoreOper");

			instOperationAction = new InstConcept("ClCoreOper",
					metaOperationAction, sasverClCoreOperationAction);
			refas.getVariabilityVertex().put("ClCoreOper", instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify Claims on the Model");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("visible").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-menu-cl", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-cl");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverClCoreOperationSubAction = new OpersSubOperation(1,
					"ClCoreSubOper");
			// updateCoreOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("ClCoreSubOper",
					metaOperationSubAction, sasverClCoreOperationSubAction);
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
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outCl");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("ClCoreSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("cl-core", instEdgeOper);
			instEdgeOper.setIdentifier("cl-core");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverClCoreOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverClCoreOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverClCoreOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("cl-core-lab", metaLabeling,
					sasverClCoreOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("cl-core-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("cl-core-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("cl-core-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverClallOperationAction = new OpersConcept("ClAlwaysActOper");

			instOperationAction = new InstConcept("ClAlwaysActOper",
					metaOperationAction, sasverClallOperationAction);
			refas.getVariabilityVertex().put("ClAlwaysActOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify Claims Always Active");
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
					"ClAlwaysActSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("ClAlwaysActSubOper",
					metaOperationSubAction, sasverClallOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"This Claim/Relation is always selected.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review the Claims and relations. #number# Claims/Relations are always selected.");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("CLCoreOper");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outCl");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("ClAlwaysActSubOper",
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
			refas.getConstraintInstEdges().put("cl-all-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("cl-all-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverClneverOperationAction = new OpersConcept("ClNeverActOper");

			instOperationAction = new InstConcept("ClNeverActOper",
					metaOperationAction, sasverClneverOperationAction);
			refas.getVariabilityVertex().put("ClNeverActOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify Claims Never Allowed");
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
					"ClNeverActSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("ClNeverActSubOper",
					metaOperationSubAction, sasverClneverOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"This Claim is never selected.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review the Claims. #number# Claims are never selected.");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getDeadElements
									.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("CLCoreOper");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outCl");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("ClNeverActSubOper",
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

			instLabeling = new InstConcept("cl-never-lab", metaLabeling,
					sasverClneverOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("cl-never-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("cl-never-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("cl-never-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			// --- Required for "Verify All operationalizations", not presented
			// in paper
			// Not visible in the interface, the mentioned oper has errors, it
			// is not working correctly
			sasverCoreOpersOperationAction = new OpersConcept("OperCoreOper");

			instOperationAction = new InstConcept("OperCoreOper",
					metaOperationAction, sasverCoreOpersOperationAction);
			refas.getVariabilityVertex().put("OperCoreOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify Operat. on the Model");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("visible").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("ver-menu-operall", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-operall");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverCoreOpersOperationSubAction = new OpersSubOperation(1,
					"OperCoreSubOper");
			// updateCoreOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("OperCoreSubOper",
					metaOperationSubAction, sasverCoreOpersOperationSubAction);
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
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
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
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("OperCoreSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("oper-core", instEdgeOper);
			instEdgeOper.setIdentifier("oper-core");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverCoreOpersOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverCoreOpersOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverCoreOpersOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("oper-core-lab", metaLabeling,
					sasverCoreOpersOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("oper-core-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("oper-core-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("oper-core-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// --- Verify All operationalizations, not presented in paper
			// Not visible in the interface
			// with error, not working correctly
			sasverAllOpersOperationAction = new OpersConcept(
					"AllOpersActivated");

			instOperationAction = new InstConcept("AllOpersActivated",
					metaOperationAction, sasverAllOpersOperationAction);
			refas.getVariabilityVertex().put("AllOpersActivated",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify Operat. Always Active");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("visible").setValue(false);
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
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"This Operationalization is never selected.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review the Operationalizations. #number# are never selected.");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Defects verifier error");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getDeadElements
									.toString());

			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("OperCoreOper");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);

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

			instLabeling = new InstConcept("all-opers-lab", metaLabeling,
					sasverAllOpersOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("all-opers-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges()
					.put("all-opers-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("all-opers-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverNoLoopsOperationAction = new OpersConcept(
					"NoLoopsStructRelOper");

			instOperationAction = new InstConcept("NoLoopsStructRelOper",
					metaOperationAction, sasverNoLoopsOperationAction);
			refas.getVariabilityVertex().put("NoLoopsStructRelOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify Loops in Struct. Rels");
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
					"NoLoopsStructRelSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("NoLoopsStructRelSubOper",
					metaOperationSubAction, sasverNoLoopsOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorHint")
					.setValue(
							"This concepts has incoming relations that creates an structural loop or double structures.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review the relations. #number# are involved in structural loops.");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Multi verification");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outStructVal");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("NoLoopsStructRelSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("no-loops", instEdgeOper);
			instEdgeOper.setIdentifier("no-loops");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverNoLoopsOperSubActionRelaxable = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverNoLoopsOperSubActionRelaxable);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("RELAXABLE");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

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
			refas.getConstraintInstEdges().put("no-loops-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("no-loops-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// --- SG conflicts
			sasverSGConflperationAction = new OpersConcept("SGConflictsOper");

			instOperationAction = new InstConcept("SGConflictsOper",
					metaOperationAction, sasverSGConflperationAction);
			refas.getVariabilityVertex().put("SGConflictsOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify SG Contribs with Conflict");
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
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"This SG contribution creates conflict.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review the SG relations. #number# present conflicts.");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Multi verification");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outConflSG");
			instOperationSubAction.getInstAttribute("indivVerExp").setValue(
					true);
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);

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

			sasverSGConflOperSubActionRelaxable = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverSGConflOperSubActionRelaxable);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("RELAXABLE");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverSGConflOperSubActionVerification = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverSGConflOperSubActionVerification);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("VERIFICATION");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverSGConflOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("sg-confl-lab", metaLabeling,
					sasverSGConflOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("sg-confl-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sg-confl-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("sg-confl-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverConflClOperationAction = new OpersConcept("ConflictClOper");

			instOperationAction = new InstConcept("ConflictClOper",
					metaOperationAction, sasverConflClOperationAction);
			refas.getVariabilityVertex().put("ConflictClOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify Conflicts with Claims");
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
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"This Claim has conflicts.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review the Claims and its relations. #number# present conflicts.");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Multi verification");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outConflClSD");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("indivVerExp").setValue(
					true);
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("ConflictClSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("confl-cld", instEdgeOper);
			instEdgeOper.setIdentifier("confl-cld");
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

			sasverConflClOperSubActionVerification = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverConflClOperSubActionVerification);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("VERIFICATION");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverConflClOperSubActionRelaxable = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverConflClOperSubActionRelaxable);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("RELAXABLE");

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
			refas.getConstraintInstEdges().put("confl-cl-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("confl-cl-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverConflClSDOperationAction = new OpersConcept(
					"ConflictClSdOper");

			instOperationAction = new InstConcept("ConflictClSdOper",
					metaOperationAction, sasverConflClSDOperationAction);
			refas.getVariabilityVertex().put("ConflictClSdOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify Claims & SoftDeps with Conflicts");
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
					"ConflictClSdSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("ConflictClSdSubOper",
					metaOperationSubAction, sasverConflClSDOperationSubAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"This Claim/SD relation/condition has conflicts.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review the Claims/SDs relations and conditions. #number# present conflicts.");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Multi verification");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outConflClSD");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("indivVerExp").setValue(
					true);
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("ConflictClSdSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("confl-cl-sd", instEdgeOper);
			instEdgeOper.setIdentifier("confl-cl-sd");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverConflClSDOperSubActionVerification = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverConflClSDOperSubActionVerification);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("VERIFICATION");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverConflClSDOperSubActionRelaxable = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverConflClSDOperSubActionRelaxable);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("RELAXABLE");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

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

			instLabeling = new InstConcept("confl-cl-sd-lab", metaLabeling,
					sasverConflClSDOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("confl-cl-sd-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("confl-cl-sd-lab-pw",
					instEdgeOper);
			instEdgeOper.setIdentifier("confl-cl-sd-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ---
			sasverConflSDOperationAction = new OpersConcept("ConflictSdOper");

			instOperationAction = new InstConcept("ConflictSdOper",
					metaOperationAction, sasverConflSDOperationAction);
			refas.getVariabilityVertex().put("ConflictSdOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify SoftDeps with Conflicts");
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
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"This SD relation or condition has conflicts.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review the SD relations and conditions. #number# present conflicts.");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Multi verification");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outConflClSD");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);

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

			sasverConflSDOperSubActionRelaxable = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverConflSDOperSubActionRelaxable);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("RELAXABLE");

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
			refas.getConstraintInstEdges().put("confl-sd-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("confl-sd-lab-pw");
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
			instOperationGroup.getInstAttribute("name").setValue(
					"General Verification");
			instOperationGroup.getInstAttribute("shortcut").setValue("V");

			updCoreOper = new OpersConcept("UpdateCoreOper");

			instOperationAction = new InstConcept("UpdateCoreOper",
					metaOperationAction, updCoreOper);
			refas.getVariabilityVertex().put("UpdateCoreOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Update Core Elements");
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
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
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
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("UpdateCoreSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("upd-core", instEdgeOper);
			instEdgeOper.setIdentifier("upd-core");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			updCoreOptSubOperNormal = new OpersSubOperationExpType();
			updCoreOptSubOperNormal.setIdentifier("NORMAL");

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, updCoreOptSubOperNormal);

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
			refas.getConstraintInstEdges().put("upd-core-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("upd-core-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			verifDeadElemOper = new OpersConcept("IdentifyDeadOper");

			instOperationAction = new InstConcept("IdentifyDeadOper",
					metaOperationAction, verifDeadElemOper);
			refas.getVariabilityVertex().put("IdentifyDeadOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify Dead Elements");
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
					"IdentifyDeadSubOper");
			// verifDeadElemOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("IdentifyDeadSubOper",
					metaOperationSubAction, verifDeadElemSubOperationAction);
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
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getDeadElements
									.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("Update Core Elements");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);

			refas.getVariabilityVertex().put("IdentifyDeadSubOper",
					instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-dead", instEdgeOper);
			instEdgeOper.setIdentifier("ver-dead");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			verifDeadElemSubOperNormal = new OpersSubOperationExpType();
			verifDeadElemSubOperNormal.setIdentifier("NORMAL");

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifDeadElemSubOperNormal);

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
			refas.getConstraintInstEdges().put("ver-dead-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("ver-dead-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			verifFalseOptOper = new OpersConcept("IdentifyFalseOper");
			instOperationAction = new InstConcept("IdentifyFalseOper",
					metaOperationAction, verifFalseOptOper);
			refas.getVariabilityVertex().put("IdentifyFalseOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify False Optionals");
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
					"IdentifyFalseSubOper");
			instOperationSubAction = new InstConcept("IdentifyFalseSubOper",
					metaOperationSubAction, verifFalseOptSubOperationAction);
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
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getFalseOptionalElements
									.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("Update Core Elements");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			refas.getVariabilityVertex().put("IdentifyFalseSubOper",
					instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);
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

			operationMenu = new OpersConcept("FMVerification");

			instOperationGroup = new InstConcept("VFMerification",
					metaOperationMenu, operationMenu);
			refas.getVariabilityVertex().put("FMVerification",
					instOperationGroup);

			instOperationGroup.getInstAttribute("visible").setValue(true);
			instOperationGroup.getInstAttribute("menuType").setValue("2");
			instOperationGroup.getInstAttribute("name").setValue(
					"FM Verification");
			instOperationGroup.getInstAttribute("shortcut").setValue("V");

			verifParentsOper = new OpersConcept("IdentifyWithoutParentsOper");

			instOperationAction = new InstConcept("IdentifyWithoutParentsOper",
					metaOperationAction, verifParentsOper);
			refas.getVariabilityVertex().put("IdentifyWithoutParentsOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Identify Elements Without Parents");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("visible").setValue(true);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-menu-pare", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-pare");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			verifParentsSubOperationAction = new OpersSubOperation(1,
					"IdentifyWithoutParentsSubOper");
			// verifParentsOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept(
					"IdentifyWithoutParentsSubOper", metaOperationSubAction,
					verifParentsSubOperationAction);
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
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("defectsVerifierMethod")
					.setValue(
							OperationSubActionDefectsVerifierMethodType.getRedundancies
									.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("Update Core Elements");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);

			refas.getVariabilityVertex().put("IdentifyWithoutParentsSubOper",
					instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

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

			verifParentsElemOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Ver-par-lab", metaLabeling,
					verifParentsElemOperUniqueLabeling);

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

			verifRootOper = new OpersConcept("VerifySingleRootOper");

			instOperationAction = new InstConcept("VerifySingleRootOper",
					metaOperationAction, verifRootOper);
			refas.getVariabilityVertex().put("VerifySingleRootOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OperationActionType.Verification.toString());
			instOperationAction.getInstAttribute("name").setValue(
					"Verify a Single Root");
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
					"VerifySingleRootSubOper");
			// verifRootOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("VerifySingleRootSubOper",
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
							"This is a root concept or it has no root. None or more than one root concepts identified.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"No root or #number# roots identified.\n Please keep only one root concept.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Multi verification");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);

			refas.getVariabilityVertex().put("VerifySingleRootSubOper",
					instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

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

			verifRootSubOperVeri = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifRootSubOperVeri);

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

			// Not implemented
			instOperationGroup = new InstConcept("Configuration",
					metaOperationMenu, operationMenu);
			refas.getVariabilityVertex().put("Configuration",
					instOperationGroup);

			instOperationGroup.getInstAttribute("visible").setValue(false);
			instOperationGroup.getInstAttribute("menuType").setValue("4");
			instOperationGroup.getInstAttribute("name").setValue(
					"Configuration");
			instOperationGroup.getInstAttribute("shortcut").setValue("C");

			configTempOper = new OpersConcept("ConfigureTemporalOper");

			instOperationAction = new InstConcept("ConfigureTemporalOper",
					metaOperationAction, configTempOper);
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
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"This concept cannot be configured.");
			instOperationSubAction.getInstAttribute("errorMsg").setValue(
					"Configuration Error");

			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last change on the configuration makes the model "
									+ "\n inconsistent. Please review the selection and "
									+ "try again. \nAttributes values were not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Single update");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);

			refas.getVariabilityVertex().put("ConfigureTemporalSubOper",
					instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

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

			configPermOper = new OpersConcept("ConfigurePermanentOper");

			instOperationAction = new InstConcept("ConfigurePermanentOper",
					metaOperationAction, configPermOper);
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
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"This element cannot be configured.");
			instOperationSubAction.getInstAttribute("errorMsg").setValue(
					"Configuration error.");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last change on the configuration makes the model "
									+ "\n inconsistent. Please review the selection and "
									+ "try again. \nAttributes values were not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					"Single update");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("iteration")
					.setValue(false);

			refas.getVariabilityVertex().put("ConfigurePermanentSubOpe",
					instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

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
	private static InstConcept instNmMetaPW = null;
	private static InstConcept instNmMetaOT = null;
	private static InstConcept instVertexVAR = null;
	private static InstConcept instVertexLowVAR = null;
	private static InstConcept instVertexLowExp = null;
	private static InstConcept instVertexCG = null;

	private static void createOpersMetaModelnmElements(ModelInstance refas,
			boolean empty) {
		// FIXED concept's definition
		ElemAttribute attribute = null;

		OpersConcept semNmMetaConcept = new OpersConcept("nmMetaConcept");

		instVertexIE = new InstConcept("nmMetaConcept", infraMetaMetaConcept,
				semNmMetaConcept);

		attribute = new ElemAttribute("TrueVal", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***True***", "", true,
				2, -1, "", "", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("TrueVal", attribute);

		if (!empty) {
			simulExecOperUniLab
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));

			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}
		attribute = new ElemAttribute("FalseVal", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***NotSelected***", "",
				false, 2, -1, "", "", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("FalseVal", attribute);

		if (!empty) {
			simulExecOperUniLab
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}
		ElemAttribute attributeSel = new ElemAttribute("Sel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", "",
				false, 2, 1, "false", "", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("Sel", attributeSel);
		semNmMetaConcept.addPropVisibleAttribute("01#" + "Sel");

		if (!empty) {
			simulExecOperUniLab.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			verifDeadElemSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			verifDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			verifFalseOptSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			sasverSDallOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			sasverSDneverOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			sasverClCoreOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			sasverClneverOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			sasverCoreOpersOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			sasverAllOpersOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			// sasverNoLoopsOperationSubAction
			// .addOutAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attributeSel.getName(), true));
			// sasverNoLoopsOperUniqueLabeling.addAttribute(new
			// OpersIOAttribute(
			// semInfraMConcept.getIdentifier(), attributeSel.getName(),
			// true));
			sasverSGConflOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			sasverConflClSDOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			sasverConflClOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			sasverConflSDOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
		}
		attribute = new ElemAttribute("Exclu", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Excluded***", "",
				false, 2, 2, "false", "", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("Exclu", attribute);
		semNmMetaConcept.addPropVisibleAttribute("02#" + "Exclu");

		if (!empty) {
			simulExecOperUniLab
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// simsceExecOperLabeling2
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addOutAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}
		attribute = new ElemAttribute("description", "String",
				AttributeType.OPERATION, false, "description", "", "", 0, -1,
				"", "", -1, "", "");
		// TODO move to syntax

		attribute = new ElemAttribute("Active", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Is Active",
				"Currently not used by dynamic operations", true, 0, 1,
				"false", "", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("Active", attribute);
		semNmMetaConcept.addPropVisibleAttribute("01#" + "Active");

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
		semNmMetaConcept.putSemanticAttribute("ConfSel", attribute);
		semNmMetaConcept.addPropVisibleAttribute("15#" + "ConfSel" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		semNmMetaConcept.addPropEditableAttribute("15#" + "ConfSel" + "#"
				+ "Core" + "#==#" + "false" + "#" + "false");

		if (!empty) {
			simulExecOperUniLab
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// simsceExecOperLabeling2
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("ConfNotSel", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Configuration Not Selected",
				"Manually/Implication not selected for this configuration",
				false, 2, 16, "Dead" + "#==#" + "false" + "#" + "false",
				"Active" + "#==#" + "true" + "#" + "false", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("ConfNotSel", attribute);
		semNmMetaConcept.addPropEditableAttribute("16#" + "ConfNotSel" + "#"
				+ "Dead" + "#==#" + "false" + "#" + "false");
		semNmMetaConcept.addPropVisibleAttribute("16#" + "ConfNotSel" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");

		if (!empty) {
			simulExecOperUniLab
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Proh", "Boolean",
				AttributeType.OPERATION, true, "Prohibited",
				"Manually prohibited (exluded) by design", false, 0, 8, "", "",
				-1, "", "");
		semNmMetaConcept.putSemanticAttribute("Proh", attribute);
		semNmMetaConcept.addPropVisibleAttribute("08#" + "Proh");
		semNmMetaConcept.addPropEditableAttribute("08#" + "Proh");

		if (!empty) {
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simulExecOperUniLab
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Required", "Boolean",
				AttributeType.OPERATION, true, "Is Required",
				"Manually defined as required", false, 2, 4, "", "", -1, "", "");

		semNmMetaConcept.putSemanticAttribute("Required", attribute);
		semNmMetaConcept.addPropEditableAttribute("04#" + "Required");
		semNmMetaConcept.addPropVisibleAttribute("04#" + "Required");

		if (!empty) {
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simulExecOperUniLab
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifParentsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifParentsElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Core", "Boolean",
				AttributeType.OPERATION, false, "Is a Core Concept",
				"Core element defined by the core update operation", false, 2,
				7, "false", "", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("Core", attribute);
		semNmMetaConcept.addPropVisibleAttribute("07#" + "Core");

		if (!empty) {
			simulExecOperUniLab
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Dead", "Boolean",
				AttributeType.OPERATION, false, "Is a Dead Concept",
				"Dead element defined by core update operation", false, 2, 8,
				"false", "", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("Dead", attribute);

		semNmMetaConcept.addPropVisibleAttribute("08#" + "Dead");

		if (!empty) {
			simulExecOperUniLab
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));

		}

		attribute = new ElemAttribute("SimulSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected by simulation",
				"Selected for this solution (with or without constraint)",
				false, 0, 5, "false", "", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("SimulSel", attribute);
		semNmMetaConcept.addPropVisibleAttribute("05#" + "SimulSel");

		if (!empty) {
			simulExecOperUniLab
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addOutAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semInfraMConcept
			// .getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}

		// attribute = new ElemAttribute("NNotSel", "Boolean",
		// AttributeType.EXECCURRENTSTATE, false,
		// "Not selected(inactive)", "", false, 0, 4, "false", "", -1, "",
		// "");
		// semNmMetaConcept.putSemanticAttribute("NNotSel", attribute);
		// semNmMetaConcept.addPropVisibleAttribute("04#" + "NNotSel");

		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulOperationSubAction.addOutVariable(attribute);

		attribute = new ElemAttribute("DBVis", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Visible on Dashboard",
				"Display element on simulation dashboard", true, 0, 3, "", "",
				-1, "", "");
		semNmMetaConcept.putSemanticAttribute("DBVis", attribute);
		semNmMetaConcept.addPropEditableAttribute("03#" + "DBVis");
		semNmMetaConcept.addPropVisibleAttribute("03#" + "DBVis");

		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("exportOnConfig", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Include in XLS export",
				"Export element in XLS solutions file", true, 0, 4, "", "", -1,
				"", "");
		semNmMetaConcept.putSemanticAttribute("exportOnConfig", attribute);
		semNmMetaConcept.addPropEditableAttribute("04#" + "exportOnConfig");
		semNmMetaConcept.addPropVisibleAttribute("04#" + "exportOnConfig");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("TestConfSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected by test configuration", "", false, 0, 3, "false", "",
				-1, "", "");
		semNmMetaConcept.putSemanticAttribute("TestConfSel", attribute);
		semNmMetaConcept.addPropVisibleAttribute("03#" + "TestConfSel");
		// simulExecOperUniLab.addAttribute(new
		// OpersIOAttribute(semGeneralElement
		// .getIdentifier(), attribute.getName(), true));
		// simsceExecOperLab2.addAttribute(new
		// OpersIOAttribute(semGeneralElement
		// .getIdentifier(), attribute.getName(), true));
		// simulSubOperationAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSDneverOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverCoreOpersOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverConflClSDOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("TestConfNotSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Not Selected by test configuration", "", false, 0, 6, "false",
				"", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("TestConfNotSel", attribute);
		semNmMetaConcept.addPropVisibleAttribute("06#" + "TestConfNotSel");
		// simulExecOperUniLab.addAttribute(new
		// OpersIOAttribute(semGeneralElement
		// .getIdentifier(), attribute.getName(), true));
		// simsceExecOperLab2.addAttribute(new
		// OpersIOAttribute(semGeneralElement
		// .getIdentifier(), attribute.getName(), true));
		// simulSubOperationAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSDneverOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverCoreOpersOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// // sasverNoLoopsOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// // semGeneralElement.getIdentifier(), attribute.getName(), true));
		// // sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// // semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverConflClSDOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));

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
		instNmMetaOT = new InstConcept("nmMetaOTRel",
				infraMetaMetaOverTwoRelation, semInfraOTRel);

		refas.getVariabilityVertex().put("nmMetaOTRel", instNmMetaOT);

		attribute = new ElemAttribute("Scope", "Boolean",
				AttributeType.OPERATION, true, "Global Scope", "", true, 0, 5,
				"", "", -1, "", "");
		semInfraOTRel.addPropEditableAttribute("05#" + "Scope");
		semInfraOTRel.addPropVisibleAttribute("05#" + "Scope");
		semInfraOTRel.putSemanticAttribute("Scope", attribute);

		attribute = new ElemAttribute("ConcernLevel", "Class",
				AttributeType.OPERATION, false, "Concern Level",
				"Concern Level of the element (Ignored for operations)",

				InstConcept.class.getCanonicalName(), "CG", null, "", 2, 6, "",
				"Scope" + "#==#" + "false", 0, "<<#" + "ConcernLevel"
						+ "#all#>>\n", "Scope" + "#==#" + "false");

		semInfraOTRel.putSemanticAttribute("ConcernLevel", attribute);

		semInfraOTRel.addPropEditableAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");
		semInfraOTRel.addPropVisibleAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");

		attribute = new ElemAttribute("TrueVal", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "TrueVal", "", true, 2,
				-1, "", "", -1, "", "");
		semInfraOTRel.putSemanticAttribute("TrueVal", attribute);

		if (!empty) {
			simulExecOperUniLab.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling.addAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
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
			simulExecOperUniLab.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// updateCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling.addAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Sel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***",
				"Element selected for static operations", false, 2, -1, "", "",
				-1, "", "");

		semInfraOTRel.putSemanticAttribute("Sel", attribute);

		attribute = new ElemAttribute("OSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***",
				"Element selected for this solution (green)", false, 2, -1, "",
				"", -1, "", "");

		semInfraOTRel.putSemanticAttribute("OSel", attribute);
		if (!empty) {
			simulExecOperUniLab.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addOutAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling.addAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("OCore", "Boolean",
				AttributeType.OPERATION, false, "Is a Core Concept",
				"Core element defined by the core update operation", false, 2,
				-1, "", "", -1, "", "");

		if (!empty) {
			simulExecOperUniLab.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));

			updateCoreSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));

			sasverSDCoreOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// verifDeadElemSubOperationAction
			// .addInAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
			// verifDeadElemOperUniqueLabeling.addAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// verifFalseOptSubOperationAction
			// .addInAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
			// verifFalseOptElemOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
		}
		semInfraOTRel.putSemanticAttribute("OCore", attribute);

		if (!empty) {
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling.addAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));

		}

		attribute = new ElemAttribute("Exclu", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Excluded***",
				"Element excluded for this solution (red)", false, 2, -1, "",
				"", -1, "", "");
		if (!empty) {
			simulExecOperUniLab.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
		}
		semInfraOTRel.putSemanticAttribute("Exclu", attribute);

		if (!empty) {
			simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling.addAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));

		}

		attribute = new ElemAttribute("description", "String",
				AttributeType.OPERATION, false, "description",
				"Element description", "", 0, -1, "", "", -1, "", "");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		semInfraOTRel.putSemanticAttribute("description", attribute);

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
			simulExecOperUniLab.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling.addAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
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
			simulExecOperUniLab.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simulSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperationSubAction
			// .addInAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
			// sasverNoLoopsOperUniqueLabeling.addAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClSDOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
		}

		OpersConcept semGeneralPair = new OpersConcept("nmMetaPWRel");
		instNmMetaPW = new InstConcept("nmMetaPWRel",
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

		// attribute = new ElemAttribute("Sel", "Boolean",
		// AttributeType.EXECCURRENTSTATE, false, "***Selected***",
		// "Element selected for this solution (green)", false, 2, -1, "",
		// "", -1, "", "");
		//
		// semGeneralPair.putSemanticAttribute("Sel", attribute);
		// if (!empty) {
		// simulExecOperUniLab.addAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// simsceExecOperLab2.addAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// sasverSDCoreOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// sasverSDallOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// sasverSDneverOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// sasverClCoreOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// sasverClneverOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// sasverCoreOpersOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverCoreOpersOperUniqueLabeling
		// .addAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverAllOpersOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// // sasverNoLoopsOperationSubAction
		// // .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// // .getIdentifier(), attribute.getName(), true));
		// // sasverNoLoopsOperUniqueLabeling.addAttribute(new
		// // OpersIOAttribute(
		// // semGeneralPair.getIdentifier(), attribute.getName(), true));
		// sasverSGConflOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// sasverConflClSDOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverConflClSDOperUniqueLabeling
		// .addAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverConflClOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// sasverConflSDOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		// }

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

		refas.getVariabilityVertex().put("nmMetaPWRel", instNmMetaPW);

		OpersVariable semVariable = new OpersVariable("NmVariable");

		// simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(semVariable
		// .getIdentifier(), "Exclu", true));

		ArrayList<OpersExpr> semanticExpressions = new ArrayList<OpersExpr>();

		semVariable.setSemanticExpressions(semanticExpressions);

		instVertexVAR = new InstConcept("NmVariable", infraMetaMetaConcept,
				semVariable);

		OpersExpr t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexVAR, instVertexVAR, instVertexVAR,
				"varConfValue", "value");

		OpersExpr t2 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexVAR, instVertexVAR, "isConfDom",
				true, 1);

		t1 = new OpersExpr("130 varConfigVal=value=varConfigDomain", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexVAR,
				t2, t1);

		semanticExpressions.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

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
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		// simulPreUpdateOperUniLab.addAttribute(new
		// OpersIOAttribute(semVariable
		// .getIdentifier(), attribute.getName(), true));
		// simulPreUpdateSubOperationAction.addOutAttribute(new
		// OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverSDCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverSDneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverCoreOpersOperationSubAction.addOutAttribute(new
		// OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverSGConflOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverConflClSDOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverConflClOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));
		// sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semVariable.getIdentifier(), attribute.getName(), true));

		// Hidden, currently not used, required for MAPE-K simulation
		attribute = new ElemAttribute("isContext", "Boolean",
				AttributeType.OPERATION, false, "Context Defined",
				"(Ignored for operations)", false, 0, -1, "", "", -1, "", "");
		semVariable.putSemanticAttribute("isContext", attribute);
		semVariable.addPropEditableAttribute("05#" + "isContext");
		semVariable.addPropVisibleAttribute("05#" + "isContext");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		// Hidden, currently not used, required for MAPE-K simulation
		// attribute = new ElemAttribute("extVisible", "Boolean",
		// AttributeType.OPERATION, false, "Externally Visible",
		// "(Ignored by operations)", false, 0, -1, "", "", -1, "", "");
		// semVariable.putSemanticAttribute("extVisible", attribute);
		// semVariable.addPropEditableAttribute("08#" + "extVisible");
		// semVariable.addPropVisibleAttribute("08#" + "extVisible");

		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		// Hidden, currently not used, required for MAPE-K simulation
		// attribute = new ElemAttribute("extControl", "Boolean",
		// AttributeType.OPERATION, false, "Externally Controlled",
		// "(Ignored by operations)", false, 0, -1, "", "", -1, "", "");
		// semVariable.putSemanticAttribute("extControl", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// semVariable.addPropEditableAttribute("09#" + "extControl");
		// semVariable.addPropVisibleAttribute("09#" + "extControl");

		attribute = new ElemAttribute("isConfDom", "Boolean",
				AttributeType.GLOBALCONFIG, true, "Configure Domain",
				"Boolean to specify if the variable has a restricted domain",
				0, 0, 1, "", "variableType" + "#==#" + "Integer" + "$"
						+ "variableType" + "#==#" + "Enumeration" + "$"
						+ "variableType" + "#==#" + "Boolean", -1, "", "",
				"varConfDom", "", null);

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

		simulExecOperUniLab.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semVariable
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
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute(
				"varConfDom",
				"String",
				AttributeType.GLOBALCONFIG,
				false,
				"Restricted Domain",
				"User configured domain, restricts the original domain domain \"n..m,o,p..r\" (no spaces)",
				"", 0, 2, "isConfDom" + "#==#" + "true#", "", -1, "", "");
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

		attribute = new ElemAttribute("LowLevelExprSubOper", "Class",
				AttributeType.OPERATION, false, "SubOper",
				"Sub Operation to include this low-level expressions",
				OpersConcept.class.getCanonicalName(), "OMSubOper", null, "",
				0, 3, "", "variableType" + "#==#" + "LowLevel expression", -1,
				"", "");
		semVariable.putSemanticAttribute("LowLevelExprSubOper", attribute);
		semVariable.addPropEditableAttribute("03#" + "LowLevelExprSubOper");
		semVariable.addPropVisibleAttribute("03#" + "LowLevelExprSubOper" + "#"
				+ "variableType" + "#==#" + "LowLevel expression");

		attribute = new ElemAttribute(
				"LowLevelVarOutSubOper",
				"Class",
				AttributeType.OPERATION,
				false,
				"Out SubOper",
				"Sub operation to include the low-level variable calculated (in a low level expression)",
				OpersConcept.class.getCanonicalName(), "OMSubOper", null, "",
				0, 4, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semVariable.putSemanticAttribute("LowLevelVarOutSubOper", attribute);
		semVariable.addPropEditableAttribute("04#" + "LowLevelVarOutSubOper");
		semVariable.addPropVisibleAttribute("04#" + "LowLevelVarOutSubOper"
				+ "#" + "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute(
				"LowLevelOutVarLabel",
				"Class",
				AttributeType.OPERATION,
				false,
				"Output Labeling",
				"Labeling with only a set of output variables (i.e., without sorting) for a sub operation",
				OpersLabeling.class.getCanonicalName(), "OMLabeling", null, "",
				0, 5, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semVariable.putSemanticAttribute("LowLevelOutVarLabel", attribute);
		semVariable.addPropEditableAttribute("05#" + "LowLevelOutVarLabel");
		semVariable.addPropVisibleAttribute("05#" + "LowLevelOutVarLabel" + "#"
				+ "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute(
				"LowLevelVarInSubOper",
				"Class",
				AttributeType.OPERATION,
				false,
				"Input SubOper as low var",
				"Sub Operation to include the low-level variable with a fixed or previously calculated value",
				OpersConcept.class.getCanonicalName(), "OMSubOper", null, "",
				0, 6, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semVariable.putSemanticAttribute("LowLevelVarInSubOper", attribute);
		semVariable.addPropEditableAttribute("06#" + "LowLevelVarInSubOper");
		semVariable.addPropVisibleAttribute("06#" + "LowLevelVarInSubOper"
				+ "#" + "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute("LowLevelInVarLabel", "Class",
				AttributeType.OPERATION, false, "Input Labeling as low var",
				"Labeling with only a set of variables for input suboper",
				OpersLabeling.class.getCanonicalName(), "OMLabeling", null, "",
				0, 7, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semVariable.putSemanticAttribute("LowLevelInVarLabel", attribute);
		semVariable.addPropEditableAttribute("07#" + "LowLevelInVarLabel");
		semVariable.addPropVisibleAttribute("07#" + "LowLevelInVarLabel" + "#"
				+ "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute(
				"IntegerVarInSubOper",
				"Class",
				AttributeType.OPERATION,
				false,
				"Input SubOper as int",
				"Sub Operation to include the low-level variable previous calculated, in a low level expression, as Integer",
				OpersConcept.class.getCanonicalName(), "OMSubOper", null, "",
				0, 8, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semVariable.putSemanticAttribute("IntegerVarInSubOper", attribute);
		semVariable.addPropEditableAttribute("08#" + "IntegerVarInSubOper");
		semVariable.addPropVisibleAttribute("08#" + "IntegerVarInSubOper" + "#"
				+ "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute("IntegerInVarLabel", "Class",
				AttributeType.OPERATION, false, "Input Labeling as int",
				"Labeling with only a set of variables for input suboper",
				OpersLabeling.class.getCanonicalName(), "OMLabeling", null, "",
				0, 9, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semVariable.putSemanticAttribute("IntegerInVarLabel", attribute);
		semVariable.addPropEditableAttribute("09#" + "IntegerInVarLabel");
		semVariable.addPropVisibleAttribute("09#" + "IntegerInVarLabel" + "#"
				+ "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute("LowLevelVarValue", "String",
				AttributeType.GLOBALCONFIG, false, "Fixed Input Value",
				"Value defined for input variable", "", 0, 8, "",
				"variableType" + "#==#" + "LowLevel variable", -1, "", "");
		semVariable.putSemanticAttribute("LowLevelVarValue", attribute);
		semVariable.addPropEditableAttribute("08#" + "LowLevelVarValue");
		semVariable.addPropVisibleAttribute("08#" + "LowLevelVarValue" + "#"
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

		refas.getVariabilityVertex().put("NmVariable", instVertexVAR);

		OpersVariable semLowExp = new OpersVariable("NmLowExp");

		instVertexLowExp = new InstConcept("NmLowExp", infraMetaMetaConcept,
				semLowExp);

		attribute = new ElemAttribute("LowLevelExpressionText",
				LowExpr.class.getCanonicalName(), AttributeType.OPERATION,
				false, "Low-Level Expr. Text",
				"Expression at the solver level (language independent)", null,
				0, 3, "", "", -1, "", "");
		semLowExp.putSemanticAttribute("LowLevelExpressionText", attribute);
		semLowExp.addPropEditableAttribute("03#" + "LowLevelExpressionText");
		semLowExp.addPropVisibleAttribute("03#" + "LowLevelExpressionText"
				+ "#" + "");

		attribute = new ElemAttribute("LowLevelExprSubOper", "Class",
				AttributeType.OPERATION, false, "SubOper",
				"Sub Operation to include this low-level expressions",
				OpersConcept.class.getCanonicalName(), "OMSubOper", null, "",
				0, 3, "", "", -1, "", "");
		semLowExp.putSemanticAttribute("LowLevelExprSubOper", attribute);
		semLowExp.addPropEditableAttribute("03#" + "LowLevelExprSubOper");
		semLowExp.addPropVisibleAttribute("03#" + "LowLevelExprSubOper" + "#"
				+ "");

		refas.getVariabilityVertex().put("NmLowExp", instVertexLowExp);

		OpersVariable semLowVariable = new OpersVariable("NmLowVariable");

		instVertexLowVAR = new InstConcept("NmLowVariable",
				infraMetaMetaConcept, semLowVariable);

		attribute = new ElemAttribute("value", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "Value",
				"Variable current value (defined by an operation execution)",
				0, 1, 7, "", "", -1, "", "");
		semLowVariable.putSemanticAttribute("value", attribute);
		semLowVariable.addPropVisibleAttribute("07#" + "value");
		semLowVariable.addPropVisibleAttribute("07#" + "value");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(semLowVariable
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute(
				"LowLevelVarOutSubOper",
				"Class",
				AttributeType.OPERATION,
				false,
				"Out SubOper",
				"Sub operation to include the low-level variable calculated (in a low level expression)",
				OpersConcept.class.getCanonicalName(), "OMSubOper", null, "",
				0, 4, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semLowVariable.putSemanticAttribute("LowLevelVarOutSubOper", attribute);
		semLowVariable
				.addPropEditableAttribute("04#" + "LowLevelVarOutSubOper");
		semLowVariable.addPropVisibleAttribute("04#" + "LowLevelVarOutSubOper");

		attribute = new ElemAttribute(
				"LowLevelOutVarLabel",
				"Class",
				AttributeType.OPERATION,
				false,
				"Output Labeling",
				"Labeling with only a set of output variables (i.e., without sorting) for a sub operation",
				OpersLabeling.class.getCanonicalName(), "OMLabeling", null, "",
				0, 5, "", "", -1, "", "");
		semLowVariable.putSemanticAttribute("LowLevelOutVarLabel", attribute);
		semLowVariable.addPropEditableAttribute("05#" + "LowLevelOutVarLabel");
		semLowVariable.addPropVisibleAttribute("05#" + "LowLevelOutVarLabel");

		attribute = new ElemAttribute(
				"LowLevelVarInSubOper",
				"Class",
				AttributeType.OPERATION,
				false,
				"Input SubOper as low var",
				"Sub Operation to include the low-level variable with a fixed or previously calculated value",
				OpersConcept.class.getCanonicalName(), "OMSubOper", null, "",
				0, 6, "", "", -1, "", "");
		semLowVariable.putSemanticAttribute("LowLevelVarInSubOper", attribute);
		semLowVariable.addPropEditableAttribute("06#" + "LowLevelVarInSubOper");
		semLowVariable.addPropVisibleAttribute("06#" + "LowLevelVarInSubOper"
				+ "#" + "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute("LowLevelInVarLabel", "Class",
				AttributeType.OPERATION, false, "Input Labeling as low var",
				"Labeling with only a set of variables for input suboper",
				OpersLabeling.class.getCanonicalName(), "OMLabeling", null, "",
				0, 7, "", "", -1, "", "");
		semLowVariable.putSemanticAttribute("LowLevelInVarLabel", attribute);
		semLowVariable.addPropEditableAttribute("07#" + "LowLevelInVarLabel");
		semLowVariable.addPropVisibleAttribute("07#" + "LowLevelInVarLabel");

		attribute = new ElemAttribute(
				"IntegerVarInSubOper",
				"Class",
				AttributeType.OPERATION,
				false,
				"Input SubOper as int",
				"Sub Operation to include the low-level variable previous calculated, in a low level expression, as Integer",
				OpersConcept.class.getCanonicalName(), "OMSubOper", null, "",
				0, 8, "", "", -1, "", "");
		semLowVariable.putSemanticAttribute("IntegerVarInSubOper", attribute);
		semLowVariable.addPropEditableAttribute("08#" + "IntegerVarInSubOper");
		semLowVariable.addPropVisibleAttribute("08#" + "IntegerVarInSubOper");

		attribute = new ElemAttribute("IntegerInVarLabel", "Class",
				AttributeType.OPERATION, false, "Input Labeling as int",
				"Labeling with only a set of variables for input suboper",
				OpersLabeling.class.getCanonicalName(), "OMLabeling", null, "",
				0, 9, "", "", -1, "", "");
		semLowVariable.putSemanticAttribute("IntegerInVarLabel", attribute);
		semLowVariable.addPropEditableAttribute("09#" + "IntegerInVarLabel");
		semLowVariable.addPropVisibleAttribute("09#" + "IntegerInVarLabel");

		attribute = new ElemAttribute("LowLevelVarValue", "String",
				AttributeType.GLOBALCONFIG, false, "Fixed Input Value",
				"Value defined for input variable", "", 0, 8, "", "", -1, "",
				"");
		semLowVariable.putSemanticAttribute("LowLevelVarValue", attribute);
		semLowVariable.addPropEditableAttribute("08#" + "LowLevelVarValue");
		semLowVariable.addPropVisibleAttribute("08#" + "LowLevelVarValue");

		refas.getVariabilityVertex().put("NmLowVariable", instVertexLowVAR);

		OpersElement semContextGroup = new OpersElement("NmConcernLevel");

		//
		// semContextGroup
		// .putSemanticAttribute(
		// "minInstances",
		// new ElemAttribute(
		// "minInstances",
		// "Integer",
		// AttributeType.OPERATION,
		// false,
		// "Min Number of Instances",
		// "Min number of instances allowed of the concern level (Ignored for operations)",
		// "1", 0, 6, "", "", -1, "", ""));
		// semContextGroup.addPropEditableAttribute("06#" + "minInstances");
		// semContextGroup.addPropVisibleAttribute("06#" + "minInstances");
		semContextGroup
				.putSemanticAttribute(
						"instances",
						new ElemAttribute(
								"instances",
								"Integer",
								AttributeType.OPERATION,
								false,
								"Current Instances",
								"Current active instances of the concepts of this Concern Level",
								1, 0, 7, "", "", -1, "", ""));
		semContextGroup.addPropEditableAttribute("07#" + "instances");
		semContextGroup.addPropVisibleAttribute("07#" + "instances");

		// semContextGroup.putSemanticAttribute("ExtVisible", new ElemAttribute(
		// "ExtVisible", "Boolean", AttributeType.OPERATION, false,
		// "External Visible", "(Ignored for operations)", false, 0, -1,
		// "", "", -1, "", ""));
		// semContextGroup.addPropEditableAttribute("08#" + "ExtVisible");
		// semContextGroup.addPropVisibleAttribute("08#" + "ExtVisible");
		// semContextGroup.putSemanticAttribute("ExtControl", new ElemAttribute(
		// "ExtControl", "Boolean", AttributeType.OPERATION, false,
		// "Externally Controlled", "(Ignored for operations)", false, 0,
		// -1, "", "", -1, "", ""));
		// semContextGroup.addPropEditableAttribute("09#" + "ExtControl");
		// semContextGroup.addPropVisibleAttribute("09#" + "ExtControl");

		instVertexCG = new InstConcept("NmConcernLevel", infraMetaMetaConcept,
				semContextGroup);
		refas.getVariabilityVertex().put("NmConcernLevel", instVertexCG);
	}

	private static void createOpersMetaModelGeneralElement(ModelInstance refas) {
		ArrayList<OpersExpr> semExpr = new ArrayList<OpersExpr>();

		ElemAttribute attribute = null;

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
		instVertexGE = new InstConcept("GeneralConcept", metaMetaInstConcept,
				semGeneralElement);

		attribute = new ElemAttribute("outGE", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for GE verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		semGeneralElement.putSemanticAttribute("outGE", attribute);
		// verifDeadElemSubOperationAction.addOutAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// verifDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// verifFalseOptSubOperationAction.addOutAttribute(new OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));
		// verifFalseOptElemOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semGeneralElement.getIdentifier(), attribute.getName(), true));

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

		semExpr = new ArrayList<OpersExpr>();

		semGeneralElement.setSemanticExpressions(semExpr);

		OpersExpr t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Implies"), ExpressionVertexType.LEFTVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexGE, instVertexGE,
				instVertexGE, "Sel", "outGE");

		semExpr.add(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		t1 = new OpersExpr("001 Val/Ver - req implies sel", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexGE,
				instVertexGE, instVertexGE, "Required", "Sel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);

		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("008 Ver - core implies sel", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexGE,
				instVertexGE, instVertexGE, "Core", "Sel");

		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("009 UpCore - Req Implies Core", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexGE,
				instVertexGE, instVertexGE, "Required", "Core");

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		// verifParentsOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		// t1 = new OpersExpr("Req Implies Verification Error", refas
		// .getSemanticExpressionTypes().get("Implies"), instVertexGE,
		// instVertexGE, "Required", "Ver");
		//
		// verifDeadElemOperSubActionNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

		// semanticExpressions.add(t1);

		// t1 = new OpersExpr("NextPrefSel_=_0", refas
		// .getSemanticExpressionTypes().get("Equals"), instVertexGE,
		// instVertexGE, "TestConfSel", true, 0);
		//
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		//
		// semExpr.add(t1);
		//
		// t1 = new OpersExpr("NextNotPrefSel_=_0", refas
		// .getSemanticExpressionTypes().get("Equals"), instVertexGE,
		// instVertexGE, "TestConfNotSel", true, 0);
		//
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		// TestConfSel removed from simulation expressions - simplified
		// meta-expression

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexGE, instVertexGE, instVertexGE, "SimulSel", "ConfSel");

		// t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
		// "Product"), instVertexGE, instVertexGE, "TestConfSel", true, t1);

		// OpersExpr t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
		// .get("Sum"), instVertexGE, instVertexGE, instVertexGE,
		// "TestConfSel", "ConfSel");

		// t3 = new OpersExpr("4", refas.getSemanticExpressionTypes().get(
		// "Product"), instVertexGE, instVertexGE, "SimulSel", true, t3);

		// t1 = new OpersExpr("4",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// instVertexGE, t1, t3);

		t1 = new OpersExpr("Opt...", refas.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, instVertexGE, "Opt", true, t1);

		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		// semExpr.add(t1);

		OpersExpr t2 = new OpersExpr("Opt =0", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				instVertexGE, instVertexGE, "Opt", true, 1);

		// simulExecOptSubOperNormal.addSemanticExpression(t2);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t2);

		// semExpr.add(t2);

		t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexGE, instVertexGE, instVertexGE, "ConfSel", "SimulSel");

		t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexGE, instVertexGE, "Core", true, t1);

		t1 = new OpersExpr("003 Val - Core+ConfigSel+NextReqSel <=1", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				instVertexGE, 1, false, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		// t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get("Or"),
		// instVertexGE, instVertexGE, instVertexGE, "ConfNotSel",
		// "TestConfNotSel");

		t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get("Or"),
				instVertexGE, instVertexGE, instVertexGE, "Proh", "ConfNotSel");

		t1 = new OpersExpr("6", refas.getSemanticExpressionTypes().get("Or"),
				instVertexGE, instVertexGE, "Dead", true, t1);

		t1 = new OpersExpr("004 Val - NotAvail (Dead Or Prohibit Or NotSelec)",
				refas.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexGE, instVertexGE, "Exclu", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, instVertexGE, instVertexGE, "Sel",
				"Proh");

		t1 = new OpersExpr("005 Ver/Val - Sel * Proh = 0)", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE, t1,
				0);

		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, instVertexGE, instVertexGE, "Core",
				"Proh");

		t1 = new OpersExpr("007 UpCore - core * proh = 0)", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE, t1,
				0);

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		// t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get("Or"),
		// instVertexGE, instVertexGE, instVertexGE, "SimulSel",
		// "TestConfSel");

		t2 = new OpersExpr("5", refas.getSemanticExpressionTypes().get("Or"),
				instVertexGE, instVertexGE, instVertexGE, "Core", "ConfSel");

		t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get("Or"),
				instVertexGE, instVertexGE, "SimulSel", true, t2);

		t1 = new OpersExpr("002 Val - Selected (Core, ConfSel, SimulSel)",
				refas.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexGE, instVertexGE, "Sel", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, instVertexGE, instVertexGE, "Sel",
				"Exclu");

		t1 = new OpersExpr("006 Val - Selected*NotAvail =0", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE, 0,
				false, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		refas.getVariabilityVertex().put("GeneralConcept", instVertexGE);

		InstPairwiseRel instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("getobe", instEdge);
		instEdge.setIdentifier("getobe");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexIE, true);
		instEdge.setSourceRelation(instVertexGE, true);

		// Design attributes: Do not change identifiers

		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// semGeneralElement.putSemanticAttribute("description", attribute);

		attribute = new ElemAttribute("Scope", "Boolean",
				AttributeType.OPERATION, true, "Global Scope", "", true, 0, 5,
				"", "", -1, "", "");
		semGeneralElement.addPropEditableAttribute("05#" + "Scope");
		semGeneralElement.addPropVisibleAttribute("05#" + "Scope");
		semGeneralElement.putSemanticAttribute("Scope", attribute);

		attribute = new ElemAttribute("ConcernLevel", "Class",
				AttributeType.OPERATION, false, "Concern Level",
				"Concern Level of the element (Ignored for operations)",

				InstConcept.class.getCanonicalName(), "CG", null, "", 2, 6, "",
				"Scope" + "#==#" + "false", 0, "<<#" + "ConcernLevel"
						+ "#all#>>\n", "Scope" + "#==#" + "false");

		semGeneralElement.putSemanticAttribute("ConcernLevel", attribute);

		semGeneralElement.addPropEditableAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");
		semGeneralElement.addPropVisibleAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");

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
		 * semGeneralElement.addPropVisibleAttribute("05#" + "ReqLev" + "#" +
		 * "Core" + "#==#" + "true");
		 * 
		 * // Simulation attributes: do not modify identifiers
		 * 
		 * attribute = new ElemAttribute("IniReqLev", "Integer",
		 * AttributeType.EXECCURRENTSTATE, false, "Initial Required Level", "",
		 * 0, new RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
		 * semGeneralElement.putSemanticAttribute("IniReqLev", attribute);
		 */

		// simulationExecOperUniqueLabeling.addAttribute(attribute);
		// simulationOperationAction.addInVariable(attribute);
		/*
		 * attribute = new ElemAttribute("SimReqLev", "Integer",
		 * AttributeType.EXECCURRENTSTATE, false, "Required Level", "", 0, new
		 * RangeDomain(0, 4), 0, -1, "", "", -1, "", "");
		 */
		// semGeneralElement.putSemanticAttribute("SimReqLev",
		// attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("Opt", "Integer",
				AttributeType.EXECCURRENTSTATE, false,
				"Single Sel Variable for Concept Validation", "", 0,
				new RangeDomain(0, 20, 0), 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("Opt", attribute);
		// simulExecOperUniLab.addAttribute(new
		// OpersIOAttribute(semGeneralElement
		// .getIdentifier(), attribute.getName(), true));
		// simsceExecOperLab2.addAttribute(new
		// OpersIOAttribute(semGeneralElement
		// .getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("Order", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "SortVariable", "", 0,
				new RangeDomain(0, 8, 0), 0, -1, "", "", -1, "", "");
		semGeneralElement.putSemanticAttribute("Order", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(semGeneralElement
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semGeneralElement
				.getIdentifier(), attribute.getName(), true));
		// simulOperationSubAction.addInVariable(attribute);

		OpersConcept semHardConcept = new OpersConcept("semHardConcept");

		attribute = new ElemAttribute("structVal", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "No loops validation",
				"", 0, new RangeDomain(0, 40, 0), 0, -1, "false", "", -1, "",
				"");
		semHardConcept.putSemanticAttribute("structVal", attribute);
		sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semHardConcept.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semHardConcept.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("satType", "Enumeration",
				AttributeType.OPERATION, false, "satType", "",
				"com.variamos.dynsup.statictypes.SatisfactionType", "achieve",
				"", 0, 1, "", "", -1, "", "");
		semHardConcept.putSemanticAttribute("satType", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		semHardConcept.addPropEditableAttribute("01#" + "satType");
		semHardConcept.addPropVisibleAttribute("01#" + "satType");

		instVertexHC = new InstConcept("HardConcept", metaMetaInstConcept,
				semHardConcept);
		refas.getVariabilityVertex().put("HardConcept", instVertexHC);

		semExpr = new ArrayList<OpersExpr>();

		semHardConcept.setSemanticExpressions(semExpr);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, instVertexGE, "SimulSel", true, 2);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexHC, t1, 0);

		// t1 = new OpersExpr("4",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// instVertexHC, instVertexGE, "TestConfSel", true, t1);

		t1 = new OpersExpr("032 Val - OrderHC...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexHC,
				instVertexGE, "Order", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("hctoge", instEdge);
		instEdge.setIdentifier("hctoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexHC, true);

	}

	@SuppressWarnings("unchecked")
	private static void createOpersMetaModelFeaturesModel(ModelInstance refas) {
		ArrayList<OpersExpr> semExpr = new ArrayList<OpersExpr>();

		ElemAttribute attribute = null;

		OpersConcept semFeatOverTwoRelation = new OpersConcept("FeatureOT");// featSemOverTwoRelList);
		InstConcept instVertexFFGR = new InstConcept("FeatureOT",
				semFeatOverTwoRelation, metaMetaInstOverTwoRel);

		attribute = new ElemAttribute("HasParent", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "Has Parent", "", true,
				0, -1, "", "", -1, "", "");
		semFeatOverTwoRelation.putSemanticAttribute("HasParent", attribute);

		verifParentsSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semFeatOverTwoRelation.getIdentifier(), attribute.getName(),
				true));

		semFeatOverTwoRelation.setSemanticExpressions(semExpr);

		OpersExpr t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexFFGR, instNmMetaPW, "MetaPaiwise", true, "mandatory");

		OpersExpr t2 = new OpersExpr("sub", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexFFGR, instNmMetaPW, "MetaPaiwise", true, "optional");

		t2 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Or"),
				instVertexF, t1, t2);

		t1 = new OpersExpr("030 VerPar - isSructParent", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexFFGR, instVertexFFGR, "HasParent", true, t1);

		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		OpersConcept semFeature = new OpersConcept("Feature");
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), "Sel", true));

		instVertexF = new InstConcept("Feature", metaMetaInstConcept,
				semFeature);

		attribute = new ElemAttribute("HasParent", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "Has Parent", "", true,
				0, -1, "", "", -1, "", "");
		semFeature.putSemanticAttribute("HasParent", attribute);

		attribute = new ElemAttribute("IsRootFeature", "Boolean",
				AttributeType.OPERATION, true, "Is a Root Feature Concept", "",
				false, 2, -1, "", "", -1, "", "");
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

		semExpr = new ArrayList<OpersExpr>();

		semFeature.setSemanticExpressions(semExpr);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, instVertexGE, "SimulSel", true, 2);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexF, t1, 0);

		// t1 = new OpersExpr("4",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// instVertexF, instVertexGE, "TestConfSel", true, t1);

		t1 = new OpersExpr("013 Val - OrderF...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexF,
				instVertexGE, "Order", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITEROUTCONVARIABLE, instVertexF,
				instVertexFFGR, "HasParent", true, 0);

		t1 = new OpersExpr("Core", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITEROUTCONVARIABLE,
				instVertexF, instVertexFFGR, t1, 1);

		t1 = new OpersExpr("011 VerPar - HasParent", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexF, instVertexF, "Core", true, t1);

		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		// verifParentsOperSubActionRelaxable.addSemanticExpression(t1);

		// t1 = new OpersExpr("HasParent",
		// refas.getSemanticExpressionTypes().get(
		// "Equals"), instVertexF, instVertexF, "Core", true, 1);

		// verifParentsOperSubActionToVerify.addSemanticExpression(t1);

		// verifParentsOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("2-1", refas.getSemanticExpressionTypes().get(
				"Equals"), instVertexF, "FeatureType", "Root");

		t1 = new OpersExpr("010 VerRoot - IsRootFeature=...", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexF, instVertexF, "IsRootFeature", true, t1);

		verifRootOperSubActionRelaxable.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("2-1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), instVertexF, "FeatureType", "Root");

		OpersExpr t3 = new OpersExpr("3-", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexF, instVertexF, "IsRootFeature",
				true, 0);

		t1 = new OpersExpr("000 IsRootFeature=...", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexF, t1,
				t3);

		// verifRootOperSubActionRelaxable.addSemanticExpression(t1);

		// semExpr.add(t1);

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
				"Equals"), instVertexF, "FeatureType", "Root");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexF, instVertexF, "Core", true, 1);

		t1 = new OpersExpr("012 Ver/Val - Root Implies Req", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexF, t1,
				t3);

		verifParentsOperSubActionNormal.addSemanticExpression(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

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

		InstPairwiseRel instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ftoge", instEdge);
		instEdge.setIdentifier("ftoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexF, true);

		OpersConcept semRFeature = new OpersConcept("RootFeature");
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semRFeature
				.getIdentifier(), "Sel", true));

		InstConcept instVertexRF = new InstConcept("RootFeature",
				metaMetaInstConcept, semRFeature);

		StringDomain rootTypeDomain = new StringDomain();
		rootTypeDomain.add("Root");
		rootTypeDomain.add("General");
		rootTypeDomain.add("Leaf");
		attribute = new ElemAttribute("FeatureType", "String",
				AttributeType.OPERATION, "Feature Type", "", "Root", false,
				null, 2, -1, "", "", -1, "", "");

		semRFeature.putSemanticAttribute("FeatureType", attribute);
		verifRootSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		verifParentsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		verifFalseOptSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		verifDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));

		refas.getVariabilityVertex().put("RootFeature", instVertexRF);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("rftof", instEdge);
		instEdge.setIdentifier("rftof");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instVertexRF, true);

		OpersConcept semGFeature = new OpersConcept("GeneralFeature");
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semGFeature
				.getIdentifier(), "Sel", true));

		InstConcept instVertexGF = new InstConcept("GeneralFeature",
				metaMetaInstConcept, semGFeature);

		attribute = new ElemAttribute("FeatureType", "String",
				AttributeType.OPERATION, "Feature Type", "", "General", false,
				null, 2, -1, "", "", -1, "", "");
		semGFeature.putSemanticAttribute("FeatureType", attribute);
		verifRootSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		verifParentsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		verifFalseOptSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		verifDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));

		refas.getVariabilityVertex().put("GeneralFeature", instVertexGF);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("gftof", instEdge);
		instEdge.setIdentifier("gftof");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instVertexGF, true);

		OpersConcept semLFeature = new OpersConcept("LeafFeature");
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semLFeature
				.getIdentifier(), "Sel", true));

		InstConcept instVertexLF = new InstConcept("LeafFeature",
				metaMetaInstConcept, semLFeature);

		attribute = new ElemAttribute("FeatureType", "String",
				AttributeType.OPERATION, "Feature Type", "", "Leaf", false,
				null, 2, -1, "", "", -1, "", "");
		semLFeature.putSemanticAttribute("FeatureType", attribute);
		verifRootSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		verifParentsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		verifFalseOptSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		verifDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));

		refas.getVariabilityVertex().put("LeafFeature", instVertexLF);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("lftof", instEdge);
		instEdge.setIdentifier("lftof");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instVertexLF, true);

		OpersConcept directFeaFeatVertSemEdge = new OpersConcept(
				"ParentFeaturePW");
		//
		// attribute = new ElemAttribute("PSel", "Boolean",
		// AttributeType.EXECCURRENTSTATE, false, "***Selected***",
		// "Element selected for this solution (green)", false, 2, -1, "",
		// "", -1, "", "");
		//
		// directFeaFeatVertSemEdge.putSemanticAttribute("PSel", attribute);
		//
		// simulExecOperUniLab.addAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// simsceExecOperLab2.addAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverSDCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverSDallOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverSDneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverClCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverClneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverCoreOpersOperationSubAction.addOutAttribute(new
		// OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverAllOpersOperationSubAction.addOutAttribute(new
		// OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverNoLoopsOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));

		attribute = new ElemAttribute("outStructVal", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directFeaFeatVertSemEdge
				.putSemanticAttribute("outStructVal", attribute);
		sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));

		InstConcept instDirFeaFeatVertSemEdge = new InstConcept(
				"ParentFeaturePW", metaMetaPairwiseRelation,
				directFeaFeatVertSemEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ffptoip", instEdge);
		instEdge.setIdentifier("ffptoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirFeaFeatVertSemEdge, true);

		InstAttribute ia = instDirFeaFeatVertSemEdge
				.getInstAttribute("relTypesAttr");
		List<InstAttribute> ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("mandatory", new ElemAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", "", 1, -1, "", "", -1, "", ""),
				"mandatory#mandatory#true#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("optional", new ElemAttribute("optional",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "optional",
				"", "", 1, -1, "", "", -1, "", ""),
				"optional#optional#false#true#true#1#-1#1#1"));

		ia = instDirFeaFeatVertSemEdge.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("017 Ver/Val - MANSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexF, "Sel",
				"Sel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("166 Ver/val - MANSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexFFGR, instVertexF, "OSel",
				"Sel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);

		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MANSelected", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF,
				instDirFeaFeatVertSemEdge, "Sel", "PSel");

		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("016 UpCore/Val MANSelected1", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexF, "Core",
				"Core");

		semExpr.add(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("165 UpCore/Val MANSelected1", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexFFGR, instVertexF,
				"OCore", "Core");

		semExpr.add(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("015 Ver/Val MANNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexF, "Exclu",
				"Exclu");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("164 MANNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexFFGR, instVertexF,
				"Exclu", "Exclu");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "structVal", 1);

		t1 = new OpersExpr("020 NoLoop structValMan", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "structVal", true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "structVal", 1);

		t1 = new OpersExpr("169 NoLoop structValMan", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexFFGR, "structVal", true,
				t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		ias.add(new InstAttribute("mandatory", new ElemAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("019 Ver/Val OPTSelected", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexF, "Sel",
				"Sel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("168 Ver/Val OPTSelected", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexFFGR, instVertexF, "OSel",
				"Sel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("016b VerPar OPTSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexF, "Core",
				"Core");

		semExpr.add(t1);
		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("165b VerPar OPTSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexFFGR, instVertexF,
				"OCore", "Core");

		semExpr.add(t1);
		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("018 Ver/Val OPTNotAvailable", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexF, "Exclu",
				"Exclu");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("167b Ver/Val OPTNotAvailable", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexFFGR,
				"Exclu", "Exclu");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "structVal", 1);

		t1 = new OpersExpr("020b NoLoop structValOpt", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "structVal", true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "structVal", 1);

		t1 = new OpersExpr("169b NoLoop structValOpt", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexFFGR, "structVal", true,
				t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		attribute = new ElemAttribute("optional", StringType.IDENTIFIER,
				AttributeType.OPTION, false, "optional", "", "", 1, -1, "", "",
				-1, "", "");
		ias.add(new InstAttribute("optional", attribute, semExpr));

		refas.getVariabilityVertex().put("ParentFeaturePW",
				instDirFeaFeatVertSemEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("FeatFeatParentPWAsso-GR", instEdge);
		instEdge.setIdentifier("FeatFeatParentPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirFeaFeatVertSemEdge, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges()
				.put("FeatFeatParentPW-GR-Asso", instEdge);
		instEdge.setIdentifier("FeatFeatParentPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instDirFeaFeatVertSemEdge, true);

		OpersConcept directFeatFeatSideSemEdge = new OpersConcept(
				"CrossTreeFeaturePW");
		InstConcept instDirFeatFeatSideSemEdge = new InstConcept(
				"CrossTreeFeaturePW", metaMetaPairwiseRelation,
				directFeatFeatSideSemEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ffstoip", instEdge);
		instEdge.setIdentifier("ffstoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirFeatFeatSideSemEdge, true);

		ia = instDirFeatFeatSideSemEdge.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("excludes", new ElemAttribute("excludes",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "excludes",
				"", "", 1, -1, "", "", -1, "", ""),
				"excludes#excludes#false#true#true#0#-1#0#-1"));

		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", "", 1, -1, "", "", -1, "", ""),
				"require#require#false#true#true#0#-1#0#-1"));

		ia = instDirFeatFeatSideSemEdge.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeatFeatSideSemEdge, instVertexF, instVertexF, "Sel",
				"Sel");

		t1 = new OpersExpr("021 Ver/Val CONFSelected", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				instDirFeatFeatSideSemEdge, 1, false, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeatFeatSideSemEdge, instVertexFFGR, instVertexF,
				"OSel", "Sel");

		t1 = new OpersExpr("170 Ver/Val CONFSelected", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				instDirFeatFeatSideSemEdge, 1, false, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("172 Val - Exclu = False", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeatFeatSideSemEdge, instVertexFFGR, instVertexF,
				"Exclu", "FalseVal");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("excludes", new ElemAttribute("excludes",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "excludes",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("022 Ver/Val - requiresAltFeat", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeatFeatSideSemEdge, instVertexF, instVertexF, "Sel",
				"Sel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		// t1 = new OpersExpr("requires",
		// refas.getSemanticExpressionTypes().get(
		// "LessOrEquals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
		// instVertexF, "Core", "Core");
		//
		// semExpr.add(t1);

		// updCoreOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("171 Ver/Val -  requiresAltFeat", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeatFeatSideSemEdge, instVertexFFGR, instVertexF,
				"OSel", "Sel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		// t1 = new OpersExpr("requires",
		// refas.getSemanticExpressionTypes().get(
		// "LessOrEquals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
		// instVertexF, "Core", "Core");
		//
		// semExpr.add(t1);

		// updCoreOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("172b Var - Exclu = False", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeatFeatSideSemEdge, instVertexFFGR, instVertexF,
				"Exclu", "FalseVal");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"condition", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex().put("CrossTreeFeaturePW",
				instDirFeatFeatSideSemEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("FeatFeatSidePWAsso-GR", instEdge);
		instEdge.setIdentifier("FeatFeatSidePWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirFeatFeatSideSemEdge, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("FeatFeatSidePW-GR-Asso", instEdge);
		instEdge.setIdentifier("FeatFeatSidePW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instDirFeatFeatSideSemEdge, true);

		refas.getVariabilityVertex().put("FeatureOT", instVertexFFGR);

		ia = instVertexFFGR.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""),
				"and#and#true#true#true#2#-1#0#-1"));

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""),
				"or#or#false#true#true#2#-1#0#-1"));

		ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", "", 1, -1, "", "", -1, "", ""),
				"mutex#mutex#false#true#true#2#-1#0#-1"));

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""),
				"range#range#false#true#true#2#-1#0#-1"));

		ia = instVertexFFGR.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("024NO ANDFGrSelConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexFFGR,
				instVertexF, instVertexFFGR, "Sel", "OSel");

		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("ANDFeGrCoreConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexFFGR,
				instVertexF, instVertexFFGR, "Core", "OCore");

		// updCoreOptSubOperNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexF,
				instVertexF, "Sel", true, "TrueVal");

		t1 = new OpersExpr("025 Ver/Val - ANDFSRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexFFGR, t1, "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexF,
				instVertexF, "Core", true, "TrueVal");

		t1 = new OpersExpr("023 VerPar/UpCore -  ANDFCRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexFFGR, t1, "OCore");

		verifParentsOperSubActionNormal.addSemanticExpression(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("024bNO ORFConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexFFGR,
				instVertexF, instVertexFFGR, "Sel", "OSel");

		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexF,
				instVertexF, "Sel", true, "FalseVal");

		t1 = new OpersExpr("026 Ver/Val - ORFSRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, t1, "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexF,
				instVertexF, "Core", true, "TrueVal");
		t1 = new OpersExpr("023b VerPar - ORFCRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexFFGR, t1, "OCore");
		// Only for parents, not for updateCore
		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("024cNO  MUTEXFConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexFFGR,
				instVertexF, instVertexFFGR, "Sel", "OSel");

		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// verifParentsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexF,
				instVertexF, "Sel", 0);

		t1 = new OpersExpr("sub2fgrsel", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, instVertexF, t1, 1);

		t1 = new OpersExpr("027 Ver/Val - MUTEXFRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexFFGR, instVertexFFGR, "OSel", true, t1);

		// FIXME review if instVertexFFGR or Feature

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexF,
				instVertexF, "Core", true, "TrueVal");
		t1 = new OpersExpr("023c MUTEXFCRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexFFGR, t1, "OCore");
		// Only for parents, not for updateCore
		verifParentsOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "Sel", 0);

		t1 = new OpersExpr("028 Ver/Val - MUTEXrestric", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, t1, 1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// verifParentsOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("024dNO RANGEFeatConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexFFGR,
				instVertexF, instVertexFFGR, "Sel", "OSel");

		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// // verifParentsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, null, "Sel", "FalseVal", true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, t1, instVertexF, "LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, null, "Sel", "FalseVal", true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, t2, instVertexF, "HighRange");

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexFFGR, t1, t2);

		t1 = new OpersExpr("029 Ver/Val - RANGEFeatRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexFFGR, instVertexFFGR, "OSel", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexF,
				instVertexF, "Core", true, "TrueVal");
		t1 = new OpersExpr("023d VerPar - RANGEFCRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexFFGR, t1, "OCore");

		verifParentsOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, null, "TrueVal", 0, true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, t1, instVertexFFGR, "LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, null, "Core", "TrueVal", true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, t2, instVertexFFGR, "OCore");

		t1 = new OpersExpr("031 UpCore - ANDFCRel", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexFFGR,
				t1, t2);

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ffgrtogr", instEdge);
		instEdge.setIdentifier("ffgrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexFFGR, true);

		InstConcept instFeatFeatFFFGR = new InstConcept("FeatureToFeatureOT",
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

		// FIXME create two: one for parent and one for alternative
		// Copy the expressions from the PW definition
		// parent 164 165 166 167 168 169
		// altern 170 171 172

		InstConcept instFeatFeatFGRF = new InstConcept("FeatureOTToFeature",
				metaMetaPairwiseRelation);
		refas.getVariabilityVertex()
				.put("FeatureOTToFeature", instFeatFeatFGRF);

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
	}

	@SuppressWarnings("unchecked")
	private static void createOpersMetaModelElements(ModelInstance refas) {

		ArrayList<OpersExpr> semExpr = new ArrayList<OpersExpr>();

		ElemAttribute attribute = null;

		OpersConcept semAssumption = new OpersConcept("Assumption");
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semAssumption
				.getIdentifier(), "Sel", true));

		InstConcept instVertexAS = new InstConcept("Assumption",
				metaMetaInstConcept, semAssumption);
		refas.getVariabilityVertex().put("Assumption", instVertexAS);

		InstPairwiseRel instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assutoge", instEdge);
		instEdge.setIdentifier("assutoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexAS, true);

		OpersConcept semGoal = new OpersConcept("Goal");

		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semGoal
				.getIdentifier(), "Sel", true));
		attribute = new ElemAttribute("satType", "Enumeration",
				AttributeType.OPERATION, false, "satType", "",
				"com.variamos.dynsup.statictypes.SatisfactionType", "achieve",
				"", 0, 1, "", "", 1, "<#" + "satType" + "#all#>\n", "");
		semGoal.putSemanticAttribute("satType", attribute);
		// semGoal.addPanelVisibleAttribute("01#" + "satType");
		// semGoal.addPanelSpacersAttribute("<#" + "satType" + "#>\n");
		InstConcept instVertexG = new InstConcept("Goal", metaMetaInstConcept,
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
				new ArrayList<InstAttribute>(), 0, 6, "", "", 6,
				"#attributeValue#2#\n", "");
		semOperationalization.putSemanticAttribute("attributeValue", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		semOperationalization.addPropVisibleAttribute("06#" + "attributeValue");
		semOperationalization
				.addPropEditableAttribute("06#" + "attributeValue");

		simsceExecOperLabeling1.addAttribute(new OpersIOAttribute(
				semOperationalization.getIdentifier(), "Sel", true));

		InstConcept instVertexOper = new InstConcept("Operationalization",
				metaMetaInstConcept, semOperationalization);
		refas.getVariabilityVertex().put("Operationalization", instVertexOper);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("opertoge", instEdge);
		instEdge.setIdentifier("opertoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexOper, true);

		semExpr = new ArrayList<OpersExpr>();

		semOperationalization.setSemanticExpressions(semExpr);

		OpersExpr t1 = new OpersExpr("061 Ver CL/SD - sel==true", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexOper,
				instVertexOper, instVertexOper, "Sel", "TrueVal");

		sasverConflClOperSubActionVerification.addSemanticExpression(t1);
		sasverConflClSDOperSubActionVerification.addSemanticExpression(t1);

		semExpr.add(t1);

		OpersConcept semSoftgoal = new OpersConcept("Softgoal");

		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), "Sel", true));

		attribute = new ElemAttribute("satisficingLevel", "String",
				AttributeType.OPERATION, "Satisficing Level",
				"Satisficing for dynamic operations (low/high/close)", "high",
				false, null, 0, 11, "", "", -1, "", "");

		semSoftgoal.putSemanticAttribute("satisficingLevel", attribute);
		verifDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		verifDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		verifFalseOptSubOperationAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		verifFalseOptElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));

		semSoftgoal.addPropEditableAttribute("11#" + "satisficingLevel");
		semSoftgoal.addPropVisibleAttribute("11#" + "satisficingLevel");

		attribute = new ElemAttribute("satisficingType", "Enumeration",
				AttributeType.OPERATION, false, "Satisficing Type",
				"Satisficing for static operations",
				SatisficingType.class.getCanonicalName(),
				"Achieve as close as possible", "", 0, 10, "", "", -1, "", "");
		semSoftgoal.putSemanticAttribute("satisficingType", attribute);
		semSoftgoal.addPropEditableAttribute("10#" + "satisficingType");
		semSoftgoal.addPropVisibleAttribute("10#" + "satisficingType");

		attribute = new ElemAttribute("ConfigReqLevel", "Integer",
				AttributeType.OPERATION, "Config Req Level (5=ignored)",
				"SG required level (defined: 0..4 ignored: 5) ", 5, false,
				new RangeDomain(0, 5, 0), 0, 5, "Required" + "#==#" + "true"
						+ "#" + "5", "", -1, "", "");
		semSoftgoal.putSemanticAttribute("ConfigReqLevel", attribute);
		semSoftgoal.addPropEditableAttribute("05#" + "ConfigReqLevel" + "#"
				+ "Required" + "#==#" + "true" + "#" + "5");
		semSoftgoal.addPropVisibleAttribute("05#" + "ConfigReqLevel");
		verifDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		verifDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		verifFalseOptSubOperationAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		verifFalseOptElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("defaultDomainValue", "Integer",
				AttributeType.OPERATION, "Default Domain Value",
				"Default value for the domain when no relation exists", 5,
				false, new RangeDomain(0, 5, 0), 0, 5, "Required" + "#==#"
						+ "true" + "#" + "0", "", -1, "", "");
		semSoftgoal.putSemanticAttribute("defaultDomainValue", attribute);
		// simulExecOperUniLab.addAttribute(new OpersIOAttribute(semSoftgoal
		// .getIdentifier(), attribute.getName(), true));
		// simsceExecOperLab2.addAttribute(new OpersIOAttribute(semSoftgoal
		// .getIdentifier(), attribute.getName(), true));
		// simulSubOperationAction.addInAttribute(new
		// OpersIOAttribute(semSoftgoal
		// .getIdentifier(), attribute.getName(), true));
		// simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverSDneverOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverCoreOpersOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// // sasverNoLoopsOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// // semSoftgoal.getIdentifier(), attribute.getName(), true));
		// // sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// // semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverConflClSDOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));

		semExpr = new ArrayList<OpersExpr>();

		semSoftgoal.setSemanticExpressions(semExpr);

		instVertexSG = new InstConcept("Softgoal", metaMetaInstConcept,
				semSoftgoal);

		OpersExpr t3 = new OpersExpr("22", refas.getSemanticExpressionTypes()
				.get("NotEquals"), instVertexSG, instVertexSG,
				"ConfigReqLevel", true, 5);

		t1 = new OpersExpr("4", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexSG, "satisficingLevel", "close");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexSG, t1, t3);

		t1 = new OpersExpr("26", refas.getSemanticExpressionTypes().get(
				"Equals"), instVertexSG, instVertexSG, instVertexSG,
				"ConfigReqLevel", "SDReqLevel");

		t1 = new OpersExpr("064 Ver/Val - ConfReqLev...", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexSG, t3,
				t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t3 = new OpersExpr("23", refas.getSemanticExpressionTypes().get(
				"NotEquals"), instVertexSG, instVertexSG, "ConfigReqLevel",
				true, 5);

		t1 = new OpersExpr("4", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexSG, "satisficingLevel", "low");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexSG, t1, t3);

		t1 = new OpersExpr("21", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), instVertexSG, instVertexSG, instVertexSG,
				"ConfigReqLevel", "SDReqLevel");

		t1 = new OpersExpr("066 Ver/Val - ConfReqLev...", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexSG, t3,
				t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t3 = new OpersExpr("25", refas.getSemanticExpressionTypes().get(
				"NotEquals"), instVertexSG, instVertexSG, "ConfigReqLevel",
				true, 5);

		t1 = new OpersExpr("4", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexSG, "satisficingLevel", "high");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexSG, t1, t3);

		t1 = new OpersExpr("20", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), instVertexSG, instVertexSG, instVertexSG,
				"ConfigReqLevel", "SDReqLevel");

		t1 = new OpersExpr("065 Ver/Val - ConfReqLev...", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexSG, t3,
				t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Subtraction"), instVertexSG, instVertexGE, "Sel", false, 1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexSG, 3, true, t1);

		t3 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexSG, instVertexGE, "SimulSel", true, 2);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexSG, t1, t3);

		// t1 = new OpersExpr("4",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// instVertexSG, instVertexGE, "TestConfSel", true, t1);

		t1 = new OpersExpr("067 Var - OrderSG...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexSG,
				instVertexGE, "Order", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), instVertexSG, instVertexSG, instVertexSG,
				"SDReqLevel", "ClaimExpLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), instVertexSG, instVertexSG, "Sel", true, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexSG, "satisficingLevel", "high");

		t1 = new OpersExpr("069 Ver/Val - high: SDReqLevel<=ClaimExpLevel...",
				refas.getSemanticExpressionTypes().get("Implies"),
				instVertexSG, t3, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), instVertexSG, instVertexSG, instVertexSG,
				"SDReqLevel", "ClaimExpLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), instVertexSG, instVertexSG, "Sel", true, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexSG, "satisficingLevel", "low");

		t1 = new OpersExpr("070 Ver/Val - low: SDReqLevel>=ClaimExpLevel",
				refas.getSemanticExpressionTypes().get("Implies"),
				instVertexSG, t3, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexSG, instVertexSG, instVertexSG,
				"SDReqLevel", "ClaimExpLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), instVertexSG, instVertexSG, "Sel", true, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexSG, "satisficingLevel", "close");

		t1 = new OpersExpr("068 Ver/Val - close: SDReqLevel=ClaimExpLevel",
				refas.getSemanticExpressionTypes().get("Implies"),
				instVertexSG, t3, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		refas.getVariabilityVertex().put("Softgoal", instVertexSG);

		attribute = new ElemAttribute("SDReqLevel", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "Required Level by SD",
				"Required level (0..4) for the soft dependency relation", 0,
				new RangeDomain(0, 4, 0), 2, 16, "", "", -1, "", "",
				"ConfigReqLevel", "sourceLevel;targetLevel;level;CLSGLevel",
				"defaultDomainValue");
		semSoftgoal.putSemanticAttribute("SDReqLevel", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("ClaimExpLevel", "Integer",
				AttributeType.EXECCURRENTSTATE, false,
				"Expected Level by Claim",
				"Expected level (0..4) for the claim relation", 0,
				new RangeDomain(0, 4, 0), 2, 18, "", "", -1, "", "",
				"ConfigReqLevel", "sourceLevel;targetLevel;level;CLSGLevel",
				"defaultDomainValue");
		semSoftgoal.putSemanticAttribute("ClaimExpLevel", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semSoftgoal
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));

		semSoftgoal.addPropVisibleAttribute("16#" + "SDReqLevel");
		semSoftgoal.addPropVisibleAttribute("18#" + "ClaimExpLevel");

		semSoftgoal.addPropEditableAttribute("16#" + "SDReqLevel");
		semSoftgoal.addPropEditableAttribute("18#" + "ClaimExpLevel");

		attribute = new ElemAttribute("defaultDomainValue", "Integer",
				AttributeType.OPERATION, false,
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

		attribute = new ElemAttribute("structVal", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "No loops validation",
				"", 0, new RangeDomain(0, 40, 0), 0, -1, "false", "", -1, "",
				"");
		semAsset.putSemanticAttribute("structVal", attribute);
		sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semAsset.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semAsset.getIdentifier(), attribute.getName(), true));

		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semAsset
				.getIdentifier(), "Sel", true));
		InstConcept instVertexAsset = new InstConcept("Asset",
				metaMetaInstConcept, semAsset);
		refas.getVariabilityVertex().put("Asset", instVertexAsset);

		semExpr = new ArrayList<OpersExpr>();

		semAsset.setSemanticExpressions(semExpr);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, instVertexGE, "SimulSel", true, 2);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexGE, t1, 0);

		// t1 = new OpersExpr("4",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// instVertexAsset, instVertexGE, "TestConfSel", true, t1);

		t1 = new OpersExpr("131 Val - OrderA...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexAsset,
				instVertexGE, "Order", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("astoge", instEdge);
		instEdge.setIdentifier("astoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		// ---
		OpersConcept semanticOperClaimGroupRelation = new OpersConcept(
				"OperClaimOT");// hardSemOverTwoRelList);

		attribute = new ElemAttribute("structVal", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "No loops validation",
				"", 0, new RangeDomain(0, 40, 0), 0, -1, "false", "", -1, "",
				"");
		semanticOperClaimGroupRelation.putSemanticAttribute("structVal",
				attribute);
		sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semanticOperClaimGroupRelation.getIdentifier(), attribute
						.getName(), true));
		sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semanticOperClaimGroupRelation.getIdentifier(), attribute
						.getName(), true));

		// ---

		OpersConcept semClaim = new OpersConcept("Claim");

		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semClaim
				.getIdentifier(), "Sel", true));

		attribute = new ElemAttribute("outCl", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		semClaim.putSemanticAttribute("outCl", attribute);
		sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverClneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));

		InstConcept instVertexCL = new InstConcept("Claim", semClaim,
				metaMetaInstOverTwoRel);

		semExpr = new ArrayList<OpersExpr>();

		semanticOperClaimGroupRelation.setSemanticExpressions(semExpr);

		InstConcept instVertexCLGR = new InstConcept("OperClaimOT",
				semanticOperClaimGroupRelation, metaMetaInstOverTwoRel);

		t1 = new OpersExpr("103 VerCl/SD osel ==true", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexCLGR,
				instVertexCLGR, instVertexCLGR, "OSel", "TrueVal");

		sasverConflClOperSubActionVerification.addSemanticExpression(t1);
		sasverConflClSDOperSubActionVerification.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("105 UpCore - ANDHCGrCoreConcept", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexCLGR,
				instVertexCL, instVertexCLGR, "Core", "OCore");

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		semExpr = new ArrayList<OpersExpr>();

		semClaim.setSemanticExpressions(semExpr);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCL,
				instVertexOper, "Sel", true, "FalseVal");

		t1 = new OpersExpr("ORClRel", refas.getSemanticExpressionTypes().get(
				"Or"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexCL, instVertexOper, t1, "FalseVal");

		OpersExpr t2 = new OpersExpr("sub", refas.getSemanticExpressionTypes()
				.get("Or"), ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexCL, instVertexCLGR, "OSel", true, "FalseVal");

		t2 = new OpersExpr("ORClRel", refas.getSemanticExpressionTypes().get(
				"Or"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexCL, instVertexCLGR, t2, "FalseVal");

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("Or"),
				instVertexCL, t1, t2);

		t1 = new OpersExpr("090 VerCL  - outcl <=> osel or sel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexCL, instVertexCL, "outCl", false, t1);
		// FIXME expression not working!

		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("093 VerCL outcl <=> sel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexCL, instVertexCL,
				instVertexCL, "Sel", "outCl");

		semExpr.add(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexCL, instVertexGE, "SimulSel", true, 2);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexCL, t1, 0);

		// t1 = new OpersExpr("4",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// instVertexCL, instVertexGE, "TestConfSel", true, t1);

		t1 = new OpersExpr("092 Val - OrderCl...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexCL,
				instVertexGE, "Order", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("4", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexCL, instVertexCL, "Sel", true, 0);

		t1 = new OpersExpr("4", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexCL, instVertexCL,
				"ConditionalExpression", true, 0);

		t1 = new OpersExpr("091 Ver/Val - No Cond - No sel", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexCL, t1,
				t2);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		OpersConcept directOperClaimSemanticEdge = new OpersConcept(
				"OperClaimPW");

		attribute = new ElemAttribute("outCl", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directOperClaimSemanticEdge.putSemanticAttribute("outCl", attribute);
		sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));

		InstConcept instDirOperClaimSemanticEdge = new InstConcept(
				"OperClaimPW", metaMetaPairwiseRelation,
				directOperClaimSemanticEdge);

		attribute = new ElemAttribute("PSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***",
				"Element selected for this solution (green)", false, 2, -1, "",
				"", -1, "", "");

		directOperClaimSemanticEdge.putSemanticAttribute("PSel", attribute);

		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSDCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSDallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSDneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverCoreOpersOperationSubAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverAllOpersOperationSubAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		// sasverNoLoopsOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverConflClSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverConflClOperationSubAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverConflSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 3,
				"", "", 3, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		directOperClaimSemanticEdge.putSemanticAttribute("AggregationLow",
				attribute);
		directOperClaimSemanticEdge.addPropEditableAttribute("03#"
				+ "AggregationLow");
		directOperClaimSemanticEdge.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 4,
				"", "", 4, "#" + "AggregationHigh" + "#all#]\n",
				"AggregationHigh" + "#!=#" + "0");
		directOperClaimSemanticEdge.putSemanticAttribute("AggregationHigh",
				attribute);
		directOperClaimSemanticEdge.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		directOperClaimSemanticEdge.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));

		refas.getVariabilityVertex().put("OperClaimPW",
				instDirOperClaimSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("opctoip", instEdge);
		instEdge.setIdentifier("opctoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirOperClaimSemanticEdge, true);

		instDirOperClaimSemanticEdge.createInstAttributes();

		InstAttribute ia = instDirOperClaimSemanticEdge
				.getInstAttribute("relTypesAttr");
		List<InstAttribute> ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
				"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
				"OperToClaim##true#true#true#0#-1#0#-1"));

		ia = instDirOperClaimSemanticEdge.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t2 = new OpersExpr("CLSGnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexOper, instVertexCL,
				"Sel", "TrueVal");

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexCL,
				"ConditionalExpression", false, t2);

		t1 = new OpersExpr("OPERCLSelected", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"), ExpressionVertexType.RIGHTVARIABLE,
				instDirOperClaimSemanticEdge, instDirOperClaimSemanticEdge,
				"PSel", false, t1);
		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirOperClaimSemanticEdge, instDirOperClaimSemanticEdge,
				"AggregationHigh", true, 0);
		t1 = new OpersExpr("114 Val - NoAggre:DEFSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirOperClaimSemanticEdge, t2, t1);
		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t2 = new OpersExpr("CLSGnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexOper, instVertexCL,
				"Sel", "TrueVal");

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexCL,
				"ConditionalExpression", false, t2);

		t1 = new OpersExpr("118 VerSAS - OPERCLSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTVARIABLE,
				instDirOperClaimSemanticEdge, instDirOperClaimSemanticEdge,
				"PSel", false, t1);
		semExpr.add(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("115 VerSAS/Val - OperCLEq", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE,
				instDirOperClaimSemanticEdge, instVertexCL,
				instDirOperClaimSemanticEdge, "Sel", "PSel");
		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexOper, "Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirOperClaimSemanticEdge, instDirOperClaimSemanticEdge,
				"AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexOper, "Sel", true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instDirOperClaimSemanticEdge, instDirOperClaimSemanticEdge,
				"AggregationHigh", false, t2);

		t2 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirOperClaimSemanticEdge, t1, t2);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexCL,
				"ConditionalExpression", false, t2);

		t1 = new OpersExpr("OPERCLSelected", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"), ExpressionVertexType.RIGHTVARIABLE,
				instDirOperClaimSemanticEdge, instDirOperClaimSemanticEdge,
				"PSel", false, t1);
		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirOperClaimSemanticEdge, instDirOperClaimSemanticEdge,
				"AggregationHigh", true, 0);
		t1 = new OpersExpr("113 Val - Aggre:CLOperSel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirOperClaimSemanticEdge, t2, t1);
		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexOper, instVertexCL,
				"outCl", "ConditionalExpression");

		t1 = new OpersExpr("117 VerCl - OPERCLSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexCL, "Sel", true, t1);

		semExpr.add(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("CLOperExclu1Exclu", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexOper, instVertexCL,
				"Exclu", "TrueVal");

		t1 = new OpersExpr("116 Ver/Val - OPERCLNotAvailable", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexOper,
				instVertexCL, "Exclu", false, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
				"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		semExpr = new ArrayList<OpersExpr>();

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
				null, 0, 3, "", "", -1, "#ConditionalExpression#all#", "");
		semClaim.putSemanticAttribute("ConditionalExpression", attribute);
		semClaim.addPropEditableAttribute("03#" + "ConditionalExpression");
		semClaim.addPropVisibleAttribute("03#" + "ConditionalExpression");

		simulExecOperUniLab.addAttribute(new OpersIOAttribute(semClaim
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semClaim
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(semClaim
				.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(semClaim
				.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("CompExp", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Boolean Comp. Expression",
				"", true, 0, 1, "", "", -1, "", "");
		semClaim.putSemanticAttribute("CompExp", attribute);
		semClaim.addPropEditableAttribute("01#" + "CompExp");
		semClaim.addPropVisibleAttribute("01#" + "CompExp");

		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		attribute = new ElemAttribute("ConfidenceLevel", "Integer",
				AttributeType.OPERATION, "Confidence Level",
				"(Ignored for operations)", 1, false, new RangeDomain(0, 4, 0),
				0, 5, "", "", -1, "", "");
		semClaim.putSemanticAttribute("ConfidenceLevel", attribute);
		semClaim.addPropEditableAttribute("05#" + "ConfidenceLevel");
		semClaim.addPropVisibleAttribute("05#" + "ConfidenceLevel");

		OpersConcept semSoftDependency = new OpersConcept("SoftDependency");

		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semSoftDependency
				.getIdentifier(), "Sel", true));
		InstConcept instVertexSD = new InstConcept("SoftDependency",
				semSoftDependency, metaMetaInstConcept);
		refas.getVariabilityVertex().put("SoftDependency", instVertexSD);

		semExpr = new ArrayList<OpersExpr>();

		semSoftDependency.setSemanticExpressions(semExpr);

		t1 = new OpersExpr("124 VerSD - sel <=> outSd", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSD, instVertexSD,
				instVertexSD, "Sel", "outSd");

		semExpr.add(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexSD, instVertexGE, "SimulSel", true, 2);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexSD, t1, 0);

		// t1 = new OpersExpr("4",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// instVertexSD, instVertexGE, "TestConfSel", true, t1);

		t1 = new OpersExpr("125 Val - OrderSD...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexSD,
				instVertexGE, "Order", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sdtoge", instEdge);
		instEdge.setIdentifier("sdtoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexSD, true);

		attribute = new ElemAttribute("outSd", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		semSoftDependency.putSemanticAttribute("outSd", attribute);
		sasverSDallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("ConditionalExpression",
				ModelExpr.class.getCanonicalName(), AttributeType.OPERATION,
				false, "Conditional Expression",
				"Soft dependency activation expression", null, 0, 3, "", "",
				-1, "#ConditionalExpression#all#", "");
		semSoftDependency.putSemanticAttribute("ConditionalExpression",
				attribute);
		semSoftDependency.addPropEditableAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropVisibleAttribute("03#"
				+ "ConditionalExpression");

		simulExecOperUniLab.addAttribute(new OpersIOAttribute(semSoftDependency
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semSoftDependency
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("CompExp", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Boolean Comp. Expression",
				"", true, 2, 1, "", "", -1, "", "");
		semSoftDependency.putSemanticAttribute("CompExp", attribute);
		semSoftDependency.addPropEditableAttribute("01#" + "CompExp");
		semSoftDependency.addPropVisibleAttribute("01#" + "CompExp");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		OpersConcept semHardOverTwoRelation = new OpersConcept("SMOverTwo");// hardSemOverTwoRelList);

		attribute = new ElemAttribute("structVal", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "No loops validation",
				"", 0, new RangeDomain(0, 40, 0), 0, -1, "false", "", -1, "",
				"");
		semHardOverTwoRelation.putSemanticAttribute("structVal", attribute);
		sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semHardOverTwoRelation.getIdentifier(), attribute.getName(),
				true));
		sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semHardOverTwoRelation.getIdentifier(), attribute.getName(),
				true));

		InstConcept instVertexHHGR = new InstConcept("HardOT",
				semHardOverTwoRelation, metaMetaInstOverTwoRel);
		refas.getVariabilityVertex().put("HardOT", instVertexHHGR);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("hhtoigr", instEdge);
		instEdge.setIdentifier("hhtoigr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexHHGR, true);

		// FIXME create two: one for means and one for traversal
		// Copy the expressions from the PW definition
		// means 148 149 150 151 152 153 154 155 156 157
		// trav 158 159 160 161 162 163
		OpersConcept toHardHardSemanticEdge = new OpersConcept(
				"HardOTToHardConcept");

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 3,
				"", "", 3, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		toHardHardSemanticEdge
				.putSemanticAttribute("AggregationLow", attribute);
		toHardHardSemanticEdge.addPropEditableAttribute("03#"
				+ "AggregationLow");
		toHardHardSemanticEdge
				.addPropVisibleAttribute("03#" + "AggregationLow");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				toHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				toHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				toHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				toHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 4,
				"", "", 4, "#" + "AggregationHigh" + "#all#]\n",
				"AggregationHigh" + "#!=#" + "0");
		toHardHardSemanticEdge.putSemanticAttribute("AggregationHigh",
				attribute);
		toHardHardSemanticEdge.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		toHardHardSemanticEdge.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				toHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				toHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				toHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				toHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		InstConcept instHchcHHGRHC = new InstConcept("HardOTToHardConcept",
				metaMetaPairwiseRelation);
		refas.getVariabilityVertex().put("HardOTToHardConcept", instHchcHHGRHC);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("hhotfromip", instEdge);
		instEdge.setIdentifier("hhotfromip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instHchcHHGRHC, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("hctoHHGR-HHGR-HHHHGR", instEdge);
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

		OpersConcept fromHardHardSemanticEdge = new OpersConcept(
				"HardConceptToHardOT");

		attribute = new ElemAttribute("PSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***",
				"Element selected for this solution (green)", false, 2, -1, "",
				"", -1, "", "");

		fromHardHardSemanticEdge.putSemanticAttribute("PSel", attribute);

		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 3,
				"", "", 3, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		fromHardHardSemanticEdge.putSemanticAttribute("AggregationLow",
				attribute);
		fromHardHardSemanticEdge.addPropEditableAttribute("03#"
				+ "AggregationLow");
		fromHardHardSemanticEdge.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 4,
				"", "", 4, "#" + "AggregationHigh" + "#all#]\n",
				"AggregationHigh" + "#!=#" + "0");
		fromHardHardSemanticEdge.putSemanticAttribute("AggregationHigh",
				attribute);
		fromHardHardSemanticEdge.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		fromHardHardSemanticEdge.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		InstConcept instHchcHHGRGR = new InstConcept("HardConceptToHardOT",
				metaMetaPairwiseRelation, fromHardHardSemanticEdge);

		refas.getVariabilityVertex().put("HardConceptToHardOT", instHchcHHGRGR);

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
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instHchcHHGRGR, true);

		instHchcHHGRGR.createInstAttributes();

		ia = instHchcHHGRGR.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("Default", new ElemAttribute("Default",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "Default",
				"", "", 1, -1, "", "", -1, "", ""),
				"Default##true#true#true#0#-1#0#-1"));

		ia = instHchcHHGRGR.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("DEFSelected", refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE, instHchcHHGRGR,
		// instVertexHC, "Sel", "Sel");
		//
		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("DEFnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instHchcHHGRGR,
				instVertexHC, instHchcHHGRGR, "Sel", "TrueVal");

		t1 = new OpersExpr("MANnoHighSel", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"), ExpressionVertexType.RIGHTVARIABLE,
				instHchcHHGRGR, instHchcHHGRGR, "PSel", false, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instHchcHHGRGR, instHchcHHGRGR, "AggregationHigh", true, 0);

		t1 = new OpersExpr("042 Val - NoAggre:DEFSelected", refas
				.getSemanticExpressionTypes().get("Implies"), instHchcHHGRGR,
				t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("DEFSelSel", refas.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instHchcHHGRGR,
				instVertexHC, instHchcHHGRGR, "Sel", "TrueVal");

		t1 = new OpersExpr("046 Ver/Val - MANSel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTVARIABLE, instHchcHHGRGR,
				instHchcHHGRGR, "PSel", false, t1);

		semExpr.add(t1);

		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		// iterate onver multi-instances

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instHchcHHGRGR, instVertexHC, "Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instHchcHHGRGR, instHchcHHGRGR, "AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instHchcHHGRGR, instVertexHC, "Sel", true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instHchcHHGRGR, instHchcHHGRGR, "AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"), instHchcHHGRGR,
				t1, t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instHchcHHGRGR, instHchcHHGRGR, "PSel", true, 1);

		t1 = new OpersExpr("Aggre:MANSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instHchcHHGRGR, t1, t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instHchcHHGRGR, instHchcHHGRGR, "AggregationHigh", true, 0);

		t1 = new OpersExpr("043 Val - Aggre:DEFSelected", refas
				.getSemanticExpressionTypes().get("Implies"), instHchcHHGRGR,
				t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		// t1 = new OpersExpr("DEFCore1Core", refas.getSemanticExpressionTypes()
		// .get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
		// instHchcHHGRGR, "Core", "TrueVal");
		//
		// t1 = new OpersExpr("DEFCore1",
		// refas.getSemanticExpressionTypes().get(
		// "DoubleImplies"),
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instHchcHHGRGR,
		// instVertexHC, "Core", false, t1);
		//
		// semExpr.add(t1);
		// updCoreOptSubOperNormal.addSemanticExpression(t1);
		// // simulExecOptSubOperNormal.addSemanticExpression(t1);
		// // simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		//
		// verifParentsOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MAExclu1Exclu", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instHchcHHGRGR,
				instVertexHC, instHchcHHGRGR, "Exclu", "TrueVal");

		t1 = new OpersExpr("044 Ver/Val DEFExclu", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instHchcHHGRGR,
				instVertexHC, "Exclu", false, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2def", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instHchcHHGRGR, instVertexHC, "structVal", 1);

		t1 = new OpersExpr("045 NoLoop - DEFStruc", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE, instHchcHHGRGR,
				instVertexHC, "structVal", true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		ias.add(new InstAttribute("Default", new ElemAttribute("Default",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "Default",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		ia = instVertexHHGR.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""),
				"and#and#true#true#true#2#-1#1#1"));

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""),
				"or#or#false#true#true#2#-1#1#1"));

		ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", "", 1, -1, "", "", -1, "", ""),
				"mutex#mutex#false#true#true#2#-1#1#1"));

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""),
				"range#range#false#true#true#2#-1#1#1"));

		ia = instVertexHHGR.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("033 UpCore - ANDGrHcCoreConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexHHGR,
				instVertexHC, instVertexHHGR, "Core", "OCore");

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHHGR,
				instVertexHC, "Sel", true, "TrueVal");

		t1 = new OpersExpr("034 Ver/Val - ANDhardSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHC, t1, "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		// FIXME Check the core propagation
		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHC,
				instVertexHC, "Core", true, "TrueVal");

		t1 = new OpersExpr("035 UpCore ANDhardCoreRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHHGR, t1, "OCore");

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("ORhardConcept",
		// refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHHGR,
		// instVertexHC, "Sel", "Sel");
		//
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHC,
				instVertexHC, "Sel", true, "FalseVal");

		t1 = new OpersExpr("036 Ver/Val ORHCRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHC, t1, "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		// FIXME Check the core propagation
		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHC,
				instVertexHC, "Core", true, "FalseVal");

		t1 = new OpersExpr("037 VerPar - ORhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHHGR, t1, "OCore");

		verifParentsOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("MUTEXhardConcept", refas
		// .getSemanticExpressionTypes().get("Equals"),
		// ExpressionVertexType.LEFTVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHHGR,
		// instVertexHC, "Sel", "Sel");
		//
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHC,
				instVertexHC, "Sel", 0);

		t1 = new OpersExpr("sub2hcgrsel", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, instVertexHC, t1, 1);

		t1 = new OpersExpr("039 Ver/Val - MUTEXhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexHHGR, instVertexHC, "Sel", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHC,
				instVertexHC, "Sel", 0);

		t1 = new OpersExpr("038 Ver/Val MUTEXrestric", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHC, t1, 1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("RANGEhardConcept", refas
		// .getSemanticExpressionTypes().get("Equals"),
		// ExpressionVertexType.LEFTVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHHGR,
		// instVertexHC, "Sel", "Sel");
		//
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		// FIXME support multi-instance

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHC,
				instVertexHC, "Sel", true, "FalseVal");

		t1 = new OpersExpr("incon", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, instVertexHC, t1, "LowRange");

		t2 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHC,
				instVertexHC, "Sel", true, "FalseVal");

		t2 = new OpersExpr("incon", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, instVertexHC, t2, "HighRange");

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexHHGR, t1, t2);

		t1 = new OpersExpr("040 Ver/Val RANGEHardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexHHGR, instVertexHHGR, "OSel", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHHGR,
				instVertexHC, null, "TrueVal", 0, true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, t1, instVertexHC, "LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHHGR,
				instVertexHC, null, "Core", "TrueVal", true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, t2, instVertexHC, "OCore");

		t1 = new OpersExpr("041 UpCore ANDFCRel", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexHHGR,
				t1, t2);

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		OpersConcept directHardHardSemanticEdge = new OpersConcept("TravHardPW");

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 3,
				"", "", 3, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		directHardHardSemanticEdge.putSemanticAttribute("AggregationLow",
				attribute);
		directHardHardSemanticEdge.addPropEditableAttribute("03#"
				+ "AggregationLow");
		directHardHardSemanticEdge.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directHardHardSemanticEdge.getIdentifier(),
				attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directHardHardSemanticEdge.getIdentifier(),
				attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directHardHardSemanticEdge.getIdentifier(),
				attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directHardHardSemanticEdge.getIdentifier(),
				attribute.getName(), true));

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 4,
				"", "", 4, "#" + "AggregationHigh" + "#all#]\n",
				"AggregationHigh" + "#!=#" + "0");
		directHardHardSemanticEdge.putSemanticAttribute("AggregationHigh",
				attribute);
		directHardHardSemanticEdge.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		directHardHardSemanticEdge.addPropVisibleAttribute("04#"
				+ "AggregationHigh");

		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directHardHardSemanticEdge.getIdentifier(),
				attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directHardHardSemanticEdge.getIdentifier(),
				attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directHardHardSemanticEdge.getIdentifier(),
				attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directHardHardSemanticEdge.getIdentifier(),
				attribute.getName(), true));

		InstConcept instDirHardHardSemanticEdge = new InstConcept("TravHardPW",
				metaMetaPairwiseRelation, directHardHardSemanticEdge);

		directHardHardSemanticEdge.putSemanticAttribute("relationType",
				new ElemAttribute("relationType", "Class",
						AttributeType.OPERATION, true, "Relation Type", "",
						InstAttribute.class.getCanonicalName(), null, "", 0, 6,
						"", "", 6, "#" + "relationType" + "#all#\n", ""));
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
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirHardHardSemanticEdge, true);

		ia = instDirHardHardSemanticEdge.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", "", 1, -1, "", "", -1, "", ""),
				"conflict#conflict#false#true#true#1#-1#1#1"));

		// ias.add(new InstAttribute("alternative", new ElemAttribute(
		// "alternative", StringType.IDENTIFIER, AttributeType.OPTION,
		// false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
		// "altern.#altern.#false#true#true#1#-1#1#1"));
		//
		// ias.add(new InstAttribute("preferred", new ElemAttribute("preferred",
		// StringType.IDENTIFIER, AttributeType.OPTION, false,
		// "preferred", "", "", 1, -1, "", "", -1, "", ""),
		// "preferred#preferred#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", "", 1, -1, "", "", -1, "", ""),
				"require#require#false#true#true#0#-1#0#-1"));

		ias.add(new InstAttribute("condition", new ElemAttribute("condition",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"condition", "", "", 1, -1, "", "", -1, "", ""),
				"condition#condition#false#true#true#0#-1#0#-1"));

		ia = instDirHardHardSemanticEdge.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirHardHardSemanticEdge, instVertexHC, "Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirHardHardSemanticEdge, instDirHardHardSemanticEdge,
				"AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirHardHardSemanticEdge, instVertexHC, "Sel", true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instDirHardHardSemanticEdge, instDirHardHardSemanticEdge,
				"AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirHardHardSemanticEdge, t1, t2);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Sel", false, t1);

		t1 = new OpersExpr("CONFnoHighSel", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "FalseVal", false, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirHardHardSemanticEdge, instDirHardHardSemanticEdge,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("057 Val - Aggre:CONFSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirHardHardSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("CONFnoHighSelSel", refas
				.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, instDirHardHardSemanticEdge,
				instVertexHC, "PSel", "FalseVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Sel", false, t1);

		t1 = new OpersExpr("CONFnoHighSel", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "FalseVal", false, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirHardHardSemanticEdge, instDirHardHardSemanticEdge,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("059 Val - NoAggre:CONFSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirHardHardSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("CONFSelSel", refas.getSemanticExpressionTypes()
				.get("Or"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, instVertexHC, instVertexHC, "Sel",
				"FalseVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, instVertexHC, "Sel", false, t1);

		t1 = new OpersExpr("058 Ver - CONFSel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, instVertexHC, "FalseVal", false,
				t1);

		semExpr.add(t1);

		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexHC, instVertexHC, "Sel", true, 1);

		t1 = new OpersExpr("ALTSelected", refas.getSemanticExpressionTypes()
				.get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirHardHardSemanticEdge, instVertexHC, "PSel", true, t1);

		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// ias.add(new InstAttribute("alternative", new ElemAttribute(
		// "alternative", StringType.IDENTIFIER, AttributeType.OPTION,
		// false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
		// semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, instVertexHC, instVertexHC, "Sel",
				"Sel");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
				"Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexHC, "Sel");

		t1 = new OpersExpr("PREFSelected", refas.getSemanticExpressionTypes()
				.get("And"), instDirHardHardSemanticEdge, t3, t1);

		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		// ias.add(new InstAttribute("preferred", new ElemAttribute("preferred",
		// StringType.IDENTIFIER, AttributeType.OPTION, false,
		// "preferred", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("062 Ver/Val requires", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, instVertexHC, instVertexHC, "Sel",
				"Sel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
		// "Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirHardHardSemanticEdge, instVertexHC, "Core", false, 1);
		//
		// t1 = new OpersExpr("REQSelected", refas.getSemanticExpressionTypes()
		// .get("GreaterOrEq"), 1, false, t1);
		//
		// semExpr.add(t1);
		//
		// updCoreOptSubOperNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("061 Ver/Val CONDSelected", refas
				.getSemanticExpressionTypes().get("NotEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, instVertexHC, instVertexHC, "Sel",
				"Sel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("060 Ver/Val CONDNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, instVertexHC, instVertexHC,
				"Exclu", "Exclu");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("condition", new ElemAttribute("condition",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"condition", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex().put("TravHardPW",
				instDirHardHardSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("GoalGoalSidePWAsso-GR", instEdge);
		instEdge.setIdentifier("GoalGoalSidePWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirHardHardSemanticEdge, true);
		instEdge.setSourceRelation(instVertexHC, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("GoalGoalSidePW-GR-Asso", instEdge);
		instEdge.setIdentifier("GoalGoalSidePW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instDirHardHardSemanticEdge, true);

		OpersConcept directStructHardHardSemanticEdge = new OpersConcept(
				"meansHardPW");

		attribute = new ElemAttribute("PSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***",
				"Element selected for this solution (green)", false, 2, -1, "",
				"", -1, "", "");

		directStructHardHardSemanticEdge
				.putSemanticAttribute("PSel", attribute);

		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSDCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSDallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSDneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverCoreOpersOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverAllOpersOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		// sasverNoLoopsOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverConflClSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverConflClOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverConflSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 3,
				"", "", 3, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		directStructHardHardSemanticEdge.putSemanticAttribute("AggregationLow",
				attribute);
		directStructHardHardSemanticEdge.addPropEditableAttribute("03#"
				+ "AggregationLow");
		directStructHardHardSemanticEdge.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 4,
				"", "", 4, "#" + "AggregationHigh" + "#all#]\n",
				"AggregationHigh" + "#!=#" + "0");
		directStructHardHardSemanticEdge.putSemanticAttribute(
				"AggregationHigh", attribute);
		directStructHardHardSemanticEdge.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		directStructHardHardSemanticEdge.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));

		attribute = new ElemAttribute("outStructVal", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directStructHardHardSemanticEdge.putSemanticAttribute("outStructVal",
				attribute);
		sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));

		InstConcept instDirStructHardHardSemanticEdge = new InstConcept(
				"meansHardPW", metaMetaPairwiseRelation,
				directStructHardHardSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("shhtoip", instEdge);
		instEdge.setIdentifier("shhtoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirStructHardHardSemanticEdge, true);

		ia = instDirStructHardHardSemanticEdge.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("mandatory", new ElemAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", "", 1, -1, "", "", -1, "", ""),
				"mandatory#mandatory#true#true#true#0#-1#0#1"));

		ias.add(new InstAttribute("optional", new ElemAttribute("optional",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "optional",
				"", "", 1, -1, "", "", -1, "", ""),
				"optional#optional#false#true#true#0#-1#0#1"));

		ia = instDirStructHardHardSemanticEdge.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("047 Ver/Val - MANSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC,
				instDirStructHardHardSemanticEdge, "Sel", "PSel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MANnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC,
				instDirStructHardHardSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("MANnoHighSel", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", false, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationHigh", true, 0);

		t1 = new OpersExpr("051 Val - NoAggre:MANSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirStructHardHardSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MANnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR,
				instDirStructHardHardSemanticEdge, "OSel", "TrueVal");

		t1 = new OpersExpr("MANnoHighSel", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", false, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationHigh", true, 0);

		t1 = new OpersExpr("151 Val - NoAggre:MANSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirStructHardHardSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MANSelSel", refas.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC,
				instDirStructHardHardSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("053 Ver - MANSel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", false, t1);

		semExpr.add(t1);

		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MANSelSel", refas.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR,
				instDirStructHardHardSemanticEdge, "OSel", "TrueVal");

		t1 = new OpersExpr("148 Ver - MANSel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", false, t1);

		semExpr.add(t1);

		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		// iterate onver multi-instances

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "Sel", true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirStructHardHardSemanticEdge, t1, t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", true, 1);

		t1 = new OpersExpr("MANnoHighSel", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"), instDirStructHardHardSemanticEdge, t1,
				t2);
		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationHigh", true, 0);

		t1 = new OpersExpr("049 Val - Aggre:MANSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirStructHardHardSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("AggLowSel", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR, "OSel",
				true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationLow", false, t1);

		t2 = new OpersExpr("AggHighSel", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR, "OSel",
				true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirStructHardHardSemanticEdge, t1, t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", true, 1);

		t1 = new OpersExpr("MANnoHighSel", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"), instDirStructHardHardSemanticEdge, t1,
				t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationHigh", true, 0);

		t1 = new OpersExpr("152 Val - Aggre:MANSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirStructHardHardSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MACore1Core", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC,
				instDirStructHardHardSemanticEdge, "Core", "TrueVal");

		t1 = new OpersExpr("052 UpCore MANCore1", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "Core", false,
				t1);

		semExpr.add(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MACore1Core", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR,
				instDirStructHardHardSemanticEdge, "OCore", "TrueVal");

		t1 = new OpersExpr("153 UpCore MANCore1", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "Core", false,
				t1);

		semExpr.add(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MAExclu1Exclu", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC,
				instDirStructHardHardSemanticEdge, "Exclu", "TrueVal");

		t1 = new OpersExpr("050 Ver/Val - MAExclu", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "Exclu",
				false, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MAExclu1Exclu", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR,
				instDirStructHardHardSemanticEdge, "Exclu", "TrueVal");

		t1 = new OpersExpr("151 Ver/Val - MAExclu", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "Exclu",
				false, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "structVal", 1);

		t1 = new OpersExpr("048 NoLoop - structVal", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "structVal",
				true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "structVal", 1);

		t1 = new OpersExpr("048X 2", refas.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR, "structVal",
				true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		ias.add(new InstAttribute("mandatory", new ElemAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("149 OPTSel", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC,
				instDirStructHardHardSemanticEdge, "Sel", "PSel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("OPTnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC,
				instDirStructHardHardSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("OPTnoHighSel", refas.getSemanticExpressionTypes()
				.get("Implies"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", false, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationHigh", true, 0);

		t1 = new OpersExpr("054 Val - NoAggre:OPTSel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirStructHardHardSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("OPTnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR,
				instDirStructHardHardSemanticEdge, "OSel", "TrueVal");

		t1 = new OpersExpr("OPTnoHighSel", refas.getSemanticExpressionTypes()
				.get("Implies"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", false, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationHigh", true, 0);

		t1 = new OpersExpr("054 Val -  NoAggre:OPTSel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirStructHardHardSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("OPTSelSel", refas.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC,
				instDirStructHardHardSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("056 Ver/Val OPTSel", refas
				.getSemanticExpressionTypes().get("Implies"),
				ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", false, t1);

		semExpr.add(t1);

		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("OPTSelSel", refas.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR,
				instDirStructHardHardSemanticEdge, "OSel", "TrueVal");

		t1 = new OpersExpr("157 Ver/Val - OPTSel", refas
				.getSemanticExpressionTypes().get("Implies"),
				ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", false, t1);

		semExpr.add(t1);

		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "Sel", true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirStructHardHardSemanticEdge, t1, t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", true, 1);

		t1 = new OpersExpr("Aggre:MANSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirStructHardHardSemanticEdge, t1, t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationHigh", true, 0);

		t1 = new OpersExpr("055 Val - Aggre:OptSel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirStructHardHardSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR, "OSel",
				true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR, "OSel",
				true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirStructHardHardSemanticEdge, t1, t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", true, 1);

		t1 = new OpersExpr("Aggre:MANSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirStructHardHardSemanticEdge, t1, t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationHigh", true, 0);

		t1 = new OpersExpr("156 Val - Aggre:OptSel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirStructHardHardSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "structVal", 1);

		t1 = new OpersExpr("048b NoLoop structVal", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "structVal",
				true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHC, "structVal", 1);

		t1 = new OpersExpr("149b NoLoop structVal", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR, "structVal",
				true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		ias.add(new InstAttribute("optional", new ElemAttribute("optional",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "optional",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex().put("meansHardPW",
				instDirStructHardHardSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("structHardHardPWAsso-GR", instEdge);
		instEdge.setIdentifier("structHardHardPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirStructHardHardSemanticEdge, true);
		instEdge.setSourceRelation(instVertexHC, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges()
				.put("structHardHardPW-GR-Asso", instEdge);
		instEdge.setIdentifier("structHardHardPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instDirStructHardHardSemanticEdge, true);

		OpersConcept semAssetOperPairwiseRel = new OpersConcept("AssetOperPW");

		attribute = new ElemAttribute("outStructVal", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		semAssetOperPairwiseRel.putSemanticAttribute("outStructVal", attribute);
		sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semAssetOperPairwiseRel.getIdentifier(), attribute.getName(),
				true));
		sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semAssetOperPairwiseRel.getIdentifier(), attribute.getName(),
				true));

		InstConcept instSemAssetOperPairwiseRel = new InstConcept(
				"AssetOperPW", metaMetaPairwiseRelation,
				semAssetOperPairwiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vaptoip", instEdge);
		instEdge.setIdentifier("vaptoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instSemAssetOperPairwiseRel, true);

		ia = instSemAssetOperPairwiseRel.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("implementedBy", new ElemAttribute(
				"implementedBy", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implementedBy", "", "", 1, -1, "", "", -1, "", ""),
				"implementedBy#implementedBy#false#true#true#0#-1#0#1"));

		ia = instSemAssetOperPairwiseRel.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("138 UpCore - manLSelected1", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instSemAssetOperPairwiseRel, instVertexAsset, instVertexOper,
				"Core", "Core");

		semExpr.add(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("135 Ver/Val - manLSelected1", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instSemAssetOperPairwiseRel, instVertexAsset, instVertexOper,
				"Sel", "Sel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		// FIXME use only one: 138 or this
		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
				instSemAssetOperPairwiseRel, instVertexAsset, instVertexAsset,
				"Core", "TrueVal");

		t1 = new OpersExpr("FIX 2dde", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instSemAssetOperPairwiseRel, instVertexOper, "Core", true, t1);

		semExpr.add(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("136 Ver/Val - manLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instSemAssetOperPairwiseRel, instVertexAsset, instVertexOper,
				"Exclu", "Exclu");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instSemAssetOperPairwiseRel, instVertexOper, "structVal", 1);

		t1 = new OpersExpr("137 NoLoop - 22p", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instSemAssetOperPairwiseRel, instVertexAsset, "structVal",
				true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		ias.add(new InstAttribute("implementedBy", new ElemAttribute(
				"implementedBy", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implementedBy", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		refas.getVariabilityVertex().put("AssetOperPW",
				instSemAssetOperPairwiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("varAssetOperPWAsso-GR", instEdge);
		instEdge.setIdentifier("varAssetOperPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSemAssetOperPairwiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("varAssetOperPW-GR-Asso", instEdge);
		instEdge.setIdentifier("varAssetOperPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instSemAssetOperPairwiseRel, true);

		OpersConcept semAssetPairwiseRel = new OpersConcept("AssetPW");

		attribute = new ElemAttribute("outStructVal", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		semAssetPairwiseRel.putSemanticAttribute("outStructVal", attribute);
		sasverNoLoopsOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semAssetPairwiseRel
						.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semAssetPairwiseRel
						.getIdentifier(), attribute.getName(), true));

		InstConcept instSemAssetPairwiseRel = new InstConcept("AssetPW",
				metaMetaPairwiseRelation, semAssetPairwiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vatoip", instEdge);
		instEdge.setIdentifier("vatoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instSemAssetPairwiseRel, true);

		ia = instSemAssetPairwiseRel.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("mandatory", new ElemAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", "", 1, -1, "", "", -1, "", ""),
				"mandatory#mandatory#true#true#true#0#-1#0#1"));

		ias.add(new InstAttribute("optional", new ElemAttribute("optional",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "optional",
				"", "", 1, -1, "", "", -1, "", ""),
				"optional#optional#false#true#true#0#-1#0#1"));

		ia = instSemAssetPairwiseRel.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instSemAssetPairwiseRel, instVertexAsset, "Core", true, 0);

		t1 = new OpersExpr("NA core core", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instSemAssetPairwiseRel, instVertexHC, "Core", true, t1);

		semExpr.add(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("DELSelected", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instSemAssetPairwiseRel, instVertexAsset, instVertexAsset,
				"Sel", "Sel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("DELNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instSemAssetPairwiseRel, instVertexAsset, instVertexAsset,
				"Exclu", "Exclu");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instSemAssetPairwiseRel, instVertexAsset, "structVal", 1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instSemAssetPairwiseRel, instVertexAsset, "structVal", true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		ias.add(new InstAttribute("mandatory", new ElemAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();
		t1 = new OpersExpr("ASSESelected", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instSemAssetPairwiseRel, instVertexAsset, instVertexAsset,
				"Sel", "Sel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("ASSENotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instSemAssetPairwiseRel, instVertexF, instVertexF, "Exclu",
				"Exclu");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		ias.add(new InstAttribute("assembly", new ElemAttribute("assembly",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "assembly",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex().put("AssetPW", instSemAssetPairwiseRel);

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

		// OpersConcept semlowexpcntxPairwiseRel = new OpersConcept(
		// "NmLowExpToConcernPWlaimPW");
		//
		// InstConcept instLowSemexpcntxPairwiseRel = new InstConcept(
		// "NmLowExpToConcernPW", metaMetaPairwiseRelation,
		// semlowexpcntxPairwiseRel);
		//
		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("lowexpxtoip", instEdge);
		// instEdge.setIdentifier("vlowexptoip");
		// instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		// instEdge.setTargetRelation(instNmMetaPW, true);
		// instEdge.setSourceRelation(instLowSemexpcntxPairwiseRel, true);
		//
		// ia = instLowSemexpcntxPairwiseRel.getInstAttribute("relTypesAttr");
		// ias = (List<InstAttribute>) ia.getValue();
		//
		// ias.add(new InstAttribute("LowVExp Context", new ElemAttribute(
		// "LowExp Context", StringType.IDENTIFIER, AttributeType.OPTION,
		// false, "LowExp Context", "", "", 1, -1, "", "", -1, "", ""),
		// "LowExp Context##false#true#true#1#-1#1#1"));
		//
		// ia = instLowSemexpcntxPairwiseRel.getInstAttribute("opersExprs");
		// ias = (List<InstAttribute>) ia.getValue();
		//
		// semExpr = new ArrayList<OpersExpr>();
		//
		// ias.add(new InstAttribute("LowExp Context", new ElemAttribute(
		// "LowExp Context", StringType.IDENTIFIER, AttributeType.OPTION,
		// false, "LowExp Context", "", "", 1, -1, "", "", -1, "", ""),
		// semExpr));
		//
		// refas.getVariabilityVertex().put("NmLowVarToConcernPW",
		// instLowSemexpcntxPairwiseRel);
		//
		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("lowexprcntxPWAsso-GR", instEdge);
		// instEdge.setIdentifier("lowexprcntxPWAsso-GR");
		// instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		// instEdge.setTargetRelation(instLowSemexpcntxPairwiseRel, true);
		// instEdge.setSourceRelation(instVertexLowExp, true);
		//
		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("lowexprcntxPW-GR-Asso",
		// instEdge);
		// instEdge.setIdentifier("lowexpcntxPW-GR-Asso");
		// instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		// instEdge.setTargetRelation(instVertexCG, true);
		// instEdge.setSourceRelation(instLowSemexpcntxPairwiseRel, true);
		//
		// OpersConcept semlowvarcntxPairwiseRel = new OpersConcept(
		// "NmLowVarToConcernPWlaimPW");
		//
		// InstConcept instLowSemvarcntxPairwiseRel = new InstConcept(
		// "NmLowVarToConcernPW", metaMetaPairwiseRelation,
		// semlowvarcntxPairwiseRel);
		//
		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("lowvcxtoip", instEdge);
		// instEdge.setIdentifier("vlowcxtoip");
		// instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		// instEdge.setTargetRelation(instNmMetaPW, true);
		// instEdge.setSourceRelation(instLowSemvarcntxPairwiseRel, true);
		//
		// ia = instLowSemvarcntxPairwiseRel.getInstAttribute("relTypesAttr");
		// ias = (List<InstAttribute>) ia.getValue();
		//
		// ias.add(new InstAttribute("LowVariable Context", new ElemAttribute(
		// "LowVariable Context", StringType.IDENTIFIER,
		// AttributeType.OPTION, false, "LowVariable Context", "", "", 1,
		// -1, "", "", -1, "", ""),
		// "LowVariable Context##false#true#true#1#-1#1#1"));
		//
		// ia = instLowSemvarcntxPairwiseRel.getInstAttribute("opersExprs");
		// ias = (List<InstAttribute>) ia.getValue();
		//
		// semExpr = new ArrayList<OpersExpr>();
		//
		// ias.add(new InstAttribute("LowVariable Context", new ElemAttribute(
		// "LowVariable Context", StringType.IDENTIFIER,
		// AttributeType.OPTION, false, "LowVariable Context", "", "", 1,
		// -1, "", "", -1, "", ""), semExpr));
		//
		// refas.getVariabilityVertex().put("NmLowVarToConcernPW",
		// instLowSemvarcntxPairwiseRel);
		//
		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("lowvarcntxPWAsso-GR", instEdge);
		// instEdge.setIdentifier("lowvarcntxPWAsso-GR");
		// instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		// instEdge.setTargetRelation(instLowSemvarcntxPairwiseRel, true);
		// instEdge.setSourceRelation(instVertexLowVAR, true);
		//
		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("lowvarcntxPW-GR-Asso", instEdge);
		// instEdge.setIdentifier("lowvarcntxPW-GR-Asso");
		// instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		// instEdge.setTargetRelation(instVertexCG, true);
		// instEdge.setSourceRelation(instLowSemvarcntxPairwiseRel, true);

		OpersConcept semvarcntxPairwiseRel = new OpersConcept(
				"NmVarToConcernPWlaimPW");

		InstConcept instSemvarcntxPairwiseRel = new InstConcept(
				"NmVarToConcernPW", metaMetaPairwiseRelation,
				semvarcntxPairwiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vcxtoip", instEdge);
		instEdge.setIdentifier("vcxtoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instSemvarcntxPairwiseRel, true);

		ia = instSemvarcntxPairwiseRel.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("Variable Context", new ElemAttribute(
				"Variable Context", StringType.IDENTIFIER,
				AttributeType.OPTION, false, "Variable Context", "", "", 1, -1,
				"", "", -1, "", ""),
				"Variable Context##false#true#true#1#-1#0#1"));

		ia = instSemvarcntxPairwiseRel.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		ias.add(new InstAttribute("Variable Context", new ElemAttribute(
				"Variable Context", StringType.IDENTIFIER,
				AttributeType.OPTION, false, "Variable Context", "", "", 1, -1,
				"", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex().put("NmVarToConcernPW",
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

		OpersConcept directSGSGSemEdge = new OpersConcept("SoftgoalPWAsso");

		attribute = new ElemAttribute("outConflSG", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directSGSGSemEdge.putSemanticAttribute("outConflSG", attribute);
		sasverSGConflOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("sourceLevel", "Integer",
				AttributeType.OPERATION, "Source Level", "", 1, false,
				new RangeDomain(0, 4, 0), 0, 8, "", "", 8,
				"SL: #sourceLevel#all# - ", "");
		directSGSGSemEdge.putSemanticAttribute("sourceLevel", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(directSGSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(directSGSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));

		directSGSGSemEdge.addPropEditableAttribute("08#" + "sourceLevel");
		directSGSGSemEdge.addPropVisibleAttribute("08#" + "sourceLevel");

		attribute = new ElemAttribute("targetLevel", "Integer",
				AttributeType.OPERATION, "Target Level", "", 1, false,
				new RangeDomain(0, 4, 0), 0, 9, "", "", 9,
				"TL: #targetLevel#all#", "");
		directSGSGSemEdge.putSemanticAttribute("targetLevel", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(directSGSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(directSGSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));

		directSGSGSemEdge.addPanelSpacersAttribute(":#" + "targetLevel" + "#");
		directSGSGSemEdge.addPropEditableAttribute("09#" + "targetLevel");
		directSGSGSemEdge.addPropVisibleAttribute("09#" + "targetLevel");
		// directSGSGSemEdge.addPanelVisibleAttribute("09#" +
		// "targetLevel");

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 7,
				"", "", 7, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		directSGSGSemEdge.putSemanticAttribute("AggregationLow", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(directSGSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(directSGSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));

		directSGSGSemEdge.addPropEditableAttribute("07#" + "AggregationLow");

		directSGSGSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 8,
				"", "", 8, "#" + "AggregationHigh" + "#all#\n",
				"AggregationHigh" + "#!=#" + "0");
		directSGSGSemEdge.putSemanticAttribute("AggregationHigh", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(directSGSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(directSGSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));

		directSGSGSemEdge.addPropEditableAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPropVisibleAttribute("08#" + "AggregationHigh");

		InstConcept instDirSGSGSemanticEdge = new InstConcept("SoftgoalPW",
				metaMetaPairwiseRelation, directSGSGSemEdge);

		refas.getVariabilityVertex().put("SoftgoalPW", instDirSGSGSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgsgtoip", instEdge);
		instEdge.setIdentifier("sgsgtoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirSGSGSemanticEdge, true);

		ia = instDirSGSGSemanticEdge.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("contribution", new ElemAttribute(
				"contribution", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "contribution", "", "", 1, -1, "", "", -1, "", ""),
				"contribution#contribution#true#true#true#0#-1#0#-1"));

		ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", "", 1, -1, "", "", -1, "", ""),
				"conflict#conflict#false#true#true#0#-1#0#-1"));

		// ias.add(new InstAttribute("alternative", new ElemAttribute(
		// "alternative", StringType.IDENTIFIER, AttributeType.OPTION,
		// false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
		// "altern.#altern.#false#true#true#1#-1#1#1"));
		//
		// ias.add(new InstAttribute("preferred", new ElemAttribute("preferred",
		// StringType.IDENTIFIER, AttributeType.OPTION, false,
		// "preferred", "", "", 1, -1, "", "", -1, "", ""),
		// "preferred#preferred#false#true#true#1#-1#1#1"));
		//
		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", "", 1, -1, "", "", -1, "", ""),
				"require#require#false#true#true#0#-1#0#-1"));

		// ias.add(new InstAttribute("implication", new ElemAttribute(
		// "implication", StringType.IDENTIFIER, AttributeType.OPTION,
		// false, "implication", "", "", 1, -1, "", "", -1, "", ""),
		// "implication#implication#false#true#true#1#-1#1#1"));

		ia = instDirSGSGSemanticEdge.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		// contribution of direct relations

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("081 VerSG sourceLevel claimExpLevel", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "sourceLevel");
		semExpr.add(t1);
		sasverSGConflOperSubActionVerification.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "sourceLevel");

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirSGSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
				"low");

		t1 = new OpersExpr("080 Ver/Val low: SGReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirSGSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "sourceLevel");

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirSGSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
				"high");

		t1 = new OpersExpr("079 Ver/Val high: SGReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirSGSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "sourceLevel");

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirSGSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
				"close");

		t1 = new OpersExpr("078b Ver/Val close: SGReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirSGSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("contribution", new ElemAttribute(
				"contribution", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "contribution", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		semExpr = new ArrayList<OpersExpr>();

		// Wrong expressions - correct for conflict

		t1 = new OpersExpr("CLEx SrcLv", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "sourceLevel");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirSGSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
				"low");

		t1 = new OpersExpr("078 Ver/Val low: source & target", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirSGSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("CLEx SrcLv", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "sourceLevel");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirSGSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
				"high");

		t1 = new OpersExpr("077 Ver/Val high: source & target", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirSGSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("CLEx SrcLv", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "sourceLevel");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instDirSGSGSemanticEdge,
				"ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirSGSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
				"close");

		t1 = new OpersExpr("076 Ver/Val close: source & target", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirSGSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		// semExpr = new ArrayList<OpersExpr>();
		//
		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirSGSGSemanticEdge, instVertexSG, "Sel", true, 1);
		//
		// t1 = new OpersExpr("ALTSelected", refas.getSemanticExpressionTypes()
		// .get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// instDirSGSGSemanticEdge, instVertexSG, "Sel", true, t1);
		//
		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		//
		// ias.add(new InstAttribute("alternative", new ElemAttribute(
		// "alternative", StringType.IDENTIFIER, AttributeType.OPTION,
		// false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
		// semExpr));
		//
		// semExpr = new ArrayList<OpersExpr>();
		//
		// t1 = new OpersExpr("1",
		// refas.getSemanticExpressionTypes().get("And"),
		// ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexSG,
		// instVertexSG, "Sel", "Sel");
		//
		// t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
		// "Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// instVertexSG, "Sel");
		//
		// t1 = new OpersExpr("PREFSelected", refas.getSemanticExpressionTypes()
		// .get("And"), t3, t1);
		//
		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		//
		// ias.add(new InstAttribute("preferred", new ElemAttribute("preferred",
		// StringType.IDENTIFIER, AttributeType.OPTION, false,
		// "preferred", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("082 Val - requires", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instVertexSG, "Sel",
				"Sel");

		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
		// "Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirSGSGSemanticEdge, instVertexSG, "Sel", false, 1);
		//
		// t1 = new OpersExpr("REQSelected", refas.getSemanticExpressionTypes()
		// .get("GreaterOrEq"), 1, false, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("083 UpCore - requires", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, instVertexSG, "Core",
				"Core");

		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
		// "Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirSGSGSemanticEdge, instVertexSG, "Core", false, 1);
		//
		// t1 = new OpersExpr("REQSelected", refas.getSemanticExpressionTypes()
		// .get("GreaterOrEq"), 1, false, t1);
		semExpr.add(t1);

		updCoreOptSubOperNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "Sel", true, 1);

		t1 = new OpersExpr("075 Ver/Val SGPWIMPSel", refas
				.getSemanticExpressionTypes().get("Implies"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "Sel", true, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("implication", new ElemAttribute(
				"implication", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implication", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

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

		OpersConcept semanticSGSGGroupRelation = new OpersConcept("SoftgoalOT");// hardSemOverTwoRelList);

		InstConcept instVertexSGGR = new InstConcept("SoftgoalOT",
				semanticSGSGGroupRelation, metaMetaInstOverTwoRel);
		refas.getVariabilityVertex().put("SoftgoalOT", instVertexSGGR);

		OpersConcept directGRSGSemEdge = new OpersConcept(
				"SoftgoalOTToSoftgoalPW");

		attribute = new ElemAttribute("outConflSG", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directGRSGSemEdge.putSemanticAttribute("outConflSG", attribute);
		sasverSGConflOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("targetLevel", "Integer",
				AttributeType.OPERATION, "Target Level", "", 1, false,
				new RangeDomain(0, 4, 0), 0, 9, "", "", 9,
				":#targetLevel#all#", "");
		directGRSGSemEdge.putSemanticAttribute("targetLevel", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(directGRSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(directGRSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));

		directGRSGSemEdge.addPanelSpacersAttribute(":#" + "targetLevel" + "#");
		directGRSGSemEdge.addPropEditableAttribute("09#" + "targetLevel");
		directGRSGSemEdge.addPropVisibleAttribute("09#" + "targetLevel");

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 7,
				"", "", 7, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		directGRSGSemEdge.putSemanticAttribute("AggregationLow", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(directGRSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(directGRSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));

		directGRSGSemEdge.addPropEditableAttribute("07#" + "AggregationLow");

		directGRSGSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 8,
				"", "", 8, "#" + "AggregationHigh" + "#all#\n",
				"AggregationHigh" + "#!=#" + "0");
		directGRSGSemEdge.putSemanticAttribute("AggregationHigh", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(directGRSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(directGRSGSemEdge
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));

		directGRSGSemEdge.addPropEditableAttribute("08#" + "AggregationHigh");

		directGRSGSemEdge.addPropVisibleAttribute("08#" + "AggregationHigh");
		// directGRSGSemEdge.addPanelVisibleAttribute("09#" +
		// "targetLevel");

		// FIXME remove, use other
		InstConcept instSgOTToSg = new InstConcept("SoftgoalOTToSoftgoalPW",
				metaMetaPairwiseRelation, directGRSGSemEdge);

		refas.getVariabilityVertex()
				.put("SoftgoalOTToSoftgoalPW", instSgOTToSg);

		ia = instSgOTToSg.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("contribution", new ElemAttribute(
				"contribution", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "contribution", "", "", 1, -1, "", "", -1, "", ""),
				"contribution#contribution#true#true#true#0#-1#0#-1"));

		// ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
		// StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
		// "", "", 1, -1, "", "", -1, "", ""),
		// "conflict#conflict#false#true#true#1#-1#1#1"));
		//
		// ias.add(new InstAttribute("alternative", new ElemAttribute(
		// "alternative", StringType.IDENTIFIER, AttributeType.OPTION,
		// false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
		// "altern.#altern.#false#true#true#1#-1#1#1"));
		//
		// ias.add(new InstAttribute("preferred", new ElemAttribute("preferred",
		// StringType.IDENTIFIER, AttributeType.OPTION, false,
		// "preferred", "", "", 1, -1, "", "", -1, "", ""),
		// "preferred#preferred#false#true#true#1#-1#1#1"));
		//

		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", "", 1, -1, "", "", -1, "", ""),
				"require#require#false#true#true#0#-1#0#-1"));
		//
		// ias.add(new InstAttribute("implication", new ElemAttribute(
		// "implication", StringType.IDENTIFIER, AttributeType.OPTION,
		// false, "implication", "", "", 1, -1, "", "", -1, "", ""),
		// "implication#implication#false#true#true#1#-1#1#1"));

		ia = instSgOTToSg.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		// Expressions for contribution with OTRel

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("087 VerSG 1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instSgOTToSg, instVertexSGGR, "OSel", true, 1);

		semExpr.add(t1);
		sasverSGConflOperSubActionVerification.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instSgOTToSg, instVertexSGGR, "OSel", true, 1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instSgOTToSg, instVertexSG, "ClaimExpLevel", true,
				"targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instSgOTToSg, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instSgOTToSg, instVertexSG, "satisficingLevel", "low");

		t1 = new OpersExpr("086  Ver/Val - low: SGReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"), instSgOTToSg, t3,
				t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instSgOTToSg, instVertexSGGR, "OSel", true, 1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instSgOTToSg,
				instVertexSG, instSgOTToSg, "ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instSgOTToSg, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
				"high");

		t1 = new OpersExpr("085 Ver/Val high: SGReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"), instSgOTToSg, t3,
				t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instSgOTToSg, instVertexSGGR, "OSel", true, 1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instSgOTToSg,
				instVertexSG, instSgOTToSg, "ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instSgOTToSg, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "satisficingLevel",
				"close");

		t1 = new OpersExpr("084 Ver/Val close: SGReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"), instSgOTToSg, t3,
				t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("contribution", new ElemAttribute(
				"contribution", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "contribution", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		// FIX me wrong relations or good for conflict
		// semExpr = new ArrayList<OpersExpr>();
		//
		// t1 = new OpersExpr("SG Gr Sel",
		// refas.getSemanticExpressionTypes().get(
		// "Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instVertexSG, "Sel", 1);
		//
		// t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
		// "GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
		// instVertexSG, "ClaimExpLevel", "targetLevel");
		//
		// t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
		// "Implies"), t1, t3);
		//
		// t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
		// .get("Equals"), instVertexSG, "satisficingLevel", "low");
		//
		// t1 = new OpersExpr("low: source & target", refas
		// .getSemanticExpressionTypes().get("Implies"), t3, t1);
		//
		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		//
		// t1 = new OpersExpr("SG Gr Sel",
		// refas.getSemanticExpressionTypes().get(
		// "Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instVertexSG, "Sel", 1);
		//
		// t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
		// "LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instVertexSG, instVertexSG, "ClaimExpLevel", "targetLevel");
		//
		// t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
		// "Implies"), t1, t3);
		//
		// t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
		// .get("Equals"), instVertexSG, "satisficingLevel", "high");
		//
		// t1 = new OpersExpr("high: source & target", refas
		// .getSemanticExpressionTypes().get("Implies"), t3, t1);
		//
		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		//
		// t1 = new OpersExpr("SG Gr Sel",
		// refas.getSemanticExpressionTypes().get(
		// "Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instVertexSG, "Sel", 1);
		//
		// t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instVertexSG, instVertexSG, "ClaimExpLevel", "targetLevel");
		//
		// t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
		// "Implies"), t1, t3);
		//
		// t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
		// .get("Equals"), instVertexSG, "satisficingLevel", "high");
		//
		// t1 = new OpersExpr("close: source & target", refas
		// .getSemanticExpressionTypes().get("Implies"), t3, t1);
		//
		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		//
		// ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
		// StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
		// "", "", 1, -1, "", "", -1, "", ""), semExpr));
		//
		// semExpr = new ArrayList<OpersExpr>();
		//
		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirSGSGSemanticEdge, instVertexSG, "Sel", true, 1);
		//
		// t1 = new OpersExpr("ALTSelected", refas.getSemanticExpressionTypes()
		// .get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// instDirSGSGSemanticEdge, instVertexSG, "Sel", true, t1);
		//
		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		//
		// ias.add(new InstAttribute("alternative", new ElemAttribute(
		// "alternative", StringType.IDENTIFIER, AttributeType.OPTION,
		// false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
		// semExpr));
		//
		// semExpr = new ArrayList<OpersExpr>();
		//
		// t1 = new OpersExpr("1",
		// refas.getSemanticExpressionTypes().get("And"),
		// ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexSG,
		// instVertexSG, "Sel", "Sel");
		//
		// t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
		// "Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// instVertexSG, "Sel");
		//
		// t1 = new OpersExpr("PREFSelected", refas.getSemanticExpressionTypes()
		// .get("And"), t3, t1);
		//
		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		//
		// ias.add(new InstAttribute("preferred", new ElemAttribute("preferred",
		// StringType.IDENTIFIER, AttributeType.OPTION, false,
		// "preferred", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("088 Ver/Val requires", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instSgOTToSg,
				instVertexSG, instVertexSG, "Sel", "Sel");

		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
		// "Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirSGSGSemanticEdge, instVertexSG, "Sel", false, 1);
		//
		// t1 = new OpersExpr("REQSelected", refas.getSemanticExpressionTypes()
		// .get("GreaterOrEq"), 1, false, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("089 UpCore - requires", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instSgOTToSg,
				instVertexSG, instVertexSG, "Core", "Core");

		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
		// "Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirSGSGSemanticEdge, instVertexSG, "Core", false, 1);
		//
		// t1 = new OpersExpr("REQSelected", refas.getSemanticExpressionTypes()
		// .get("GreaterOrEq"), 1, false, t1);

		semExpr.add(t1);

		updCoreOptSubOperNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "Sel", true, 1);

		t1 = new OpersExpr("NA SGPW2IMPSel", refas.getSemanticExpressionTypes()
				.get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, instVertexSG, "Sel", true, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("implication", new ElemAttribute(
				"implication", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implication", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		OpersConcept directSgToSgGRSemEdge = new OpersConcept(
				"SoftgoalToSoftgoalOT");
		attribute = new ElemAttribute("sourceLevel", "Integer",
				AttributeType.OPERATION, "Source Level", "", 1, false,
				new RangeDomain(0, 4, 0), 0, 8, "", "", 8, "#sourceLevel#all#",
				"");
		directSgToSgGRSemEdge.putSemanticAttribute("sourceLevel", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverCoreOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// directSgToSgGRSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directSgToSgGRSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));

		directSgToSgGRSemEdge.addPropEditableAttribute("08#" + "sourceLevel");
		directSgToSgGRSemEdge.addPropVisibleAttribute("08#" + "sourceLevel");

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 7,
				"", "", 7, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		directSgToSgGRSemEdge.putSemanticAttribute("AggregationLow", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));

		directSgToSgGRSemEdge
				.addPropEditableAttribute("07#" + "AggregationLow");

		directSgToSgGRSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 8,
				"", "", 8, "#" + "AggregationHigh" + "#all#\n",
				"AggregationHigh" + "#!=#" + "0");
		directSgToSgGRSemEdge
				.putSemanticAttribute("AggregationHigh", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));

		directSgToSgGRSemEdge.addPropEditableAttribute("08#"
				+ "AggregationHigh");

		directSgToSgGRSemEdge
				.addPropVisibleAttribute("08#" + "AggregationHigh");
		// directSgToSgGRSemEdge.addPanelVisibleAttribute("08#" +
		// "sourceLevel");

		InstConcept instSgToSgGR = new InstConcept("SoftgoalToSoftgoalOT",
				metaMetaPairwiseRelation, directSgToSgGRSemEdge);

		attribute = new ElemAttribute("sourceClExp", "Boolean",
				AttributeType.OPERATION, false, "sourceClExp", "", 0, 0, -1,
				"", "", -1, "", "");
		directSgToSgGRSemEdge.putSemanticAttribute("sourceClExp", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverCoreOpersOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		// sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// directSgToSgGRSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directSgToSgGRSemEdge.getIdentifier(), attribute.getName(), true));
		sasverSGConflOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));

		refas.getVariabilityVertex().put("SoftgoalToSoftgoalOT", instSgToSgGR);

		ia = instSgToSgGR.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("Default", new ElemAttribute("Default",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "Default",
				"", "", 1, -1, "", "", -1, "", ""),
				"Default##true#true#true#0#-1#0#-1"));

		ia = instSgToSgGR.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("sub2sggrclexp2", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instSgToSgGR,
				instVertexSG, instSgToSgGR, "ClaimExpLevel", "sourceLevel");

		t1 = new OpersExpr("071 Ver/Val - ANDhardSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instSgToSgGR, instSgToSgGR, "sourceClExp", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		ias.add(new InstAttribute("Default", new ElemAttribute("Default",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "Default",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		// extends
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgsggrtoip", instEdge);
		instEdge.setIdentifier("sgsggrtoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instSgOTToSg, true);

		// extends
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sggrtogr", instEdge);
		instEdge.setIdentifier("sggrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexSGGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sggrsgtoip", instEdge);
		instEdge.setIdentifier("sggrsgtoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instSgToSgGR, true);

		// From SG to group
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgsgSGR-SGsgsg", instEdge);
		instEdge.setIdentifier("sgsgSGR-SGsgsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSgToSgGR, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgsgSGR-sgsgSG", instEdge);
		instEdge.setIdentifier("sgsgSGR-sgsgSG");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSGGR, true);
		instEdge.setSourceRelation(instSgToSgGR, true);

		// From group to SG
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("SGGRtosg-GRsgsgGR", instEdge);
		instEdge.setIdentifier("SGGRtosg-GRsgsgGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSgOTToSg, true);
		instEdge.setSourceRelation(instVertexSGGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("SGGRtosg-SGsgsgSG", instEdge);
		instEdge.setIdentifier("SGGRtosg-SGsgsgSG");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instSgOTToSg, true);

		// FIX for SG to SG relations
		ia = instVertexSGGR.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""),
				"and#and#true#true#true#0#-1#0#-1"));

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""),
				"or#or#false#true#true#0#-1#0#-1"));

		ia = instVertexSGGR.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("sub1", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTSUBITERINCRELVARIABLE,
				instVertexSGGR, instSgToSgGR, "sourceClExp", true, "TrueVal");

		t1 = new OpersExpr("072 Ver/Val - ANDSGSGGrSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCRELVARIABLE, instVertexSGGR,
				instSgToSgGR, t1, "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub1", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTSUBITERINCRELVARIABLE,
				instVertexSGGR, instSgToSgGR, "sourceClExp", true, "TrueVal");

		t1 = new OpersExpr("073 Ver/Val - ANDSGSGGrCoreRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCRELVARIABLE, instVertexSGGR,
				instSgToSgGR, t1, "OCore");

		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("sub2sggrclexp", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE, instVertexSGGR,
				instVertexSG, instSgToSgGR, "ClaimExpLevel", "sourceLevel");

		t1 = new OpersExpr("sub1",
				refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexSGGR,
				instSgToSgGR, t1, "FalseVal");

		t1 = new OpersExpr("074 Ver/Val - ORSGSGGrSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexSGGR,
				instVertexSG, t1, "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		OpersConcept directCVCGSemanticEdge = new OpersConcept("VaClPW");
		InstConcept instDirCVCGSemanticEdge = new InstConcept("VaClPW",
				metaMetaPairwiseRelation, directCVCGSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("svcgtoip", instEdge);
		instEdge.setIdentifier("svcgtoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirCVCGSemanticEdge, true);

		ia = instDirCVCGSemanticEdge.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("Variable Context", new ElemAttribute(
				"Variable Context", StringType.IDENTIFIER,
				AttributeType.OPTION, false, "Variable Context", "", "", 1, -1,
				"", "", -1, "", ""),
				"Variable Context##false#true#true#0#-1#0#-1"));

		ia = instDirCVCGSemanticEdge.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		ias.add(new InstAttribute("Variable Context", new ElemAttribute(
				"Variable Context", StringType.IDENTIFIER,
				AttributeType.OPTION, false, "Variable Context", "", "", 1, -1,
				"", "", -1, "", ""), semExpr));

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

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semClaim);

		ia = instVertexCLGR.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""),
				"and#and#true#true#true#2#-1#1#1"));

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""),
				"or#or#false#true#true#2#-1#1#1"));

		ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", "", 1, -1, "", "", -1, "", ""),
				"mutex#mutex#false#true#true#2#-1#1#1"));

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""),
				"range#range#false#true#true#2#-1#1#1"));

		ia = instVertexCLGR.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("ANDHCGrSelConcept", refas
		// .getSemanticExpressionTypes().get("DoubleImplies"),
		// ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
		// instVertexCLGR, "Sel", "Sel");
		//
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, "Sel", true, "TrueVal");

		t1 = new OpersExpr("104 Ver/Val - ANDOperCLhardSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexCLGR, t1, "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, "Core", true, "TrueVal");

		t1 = new OpersExpr("106 UpCore - ANDhardOperCLCoreRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, t1, "OCore");

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("ORhardConcept",
		// refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
		// instVertexCLGR, "Sel", "Sel");
		//
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, "Sel", true, "FalseVal");

		t1 = new OpersExpr("107 UpCore - ORhardSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, t1, "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("MUTEXhardSelConcept", refas
		// .getSemanticExpressionTypes().get("Equals"),
		// ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexHC,
		// instVertexCLGR, "Sel", "Sel");
		//
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		// FIXME correct the INCREL attribute and validate is the selected for
		// ITERS and UNIQUE

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, "Sel", 0);

		t1 = new OpersExpr("sub2clopersel", refas.getSemanticExpressionTypes()
				.get("LessOrEquals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, t1, 1);

		t1 = new OpersExpr("108 Ver/Val MUTEXhardSelLRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexCLGR, instVertexHC, "OSel", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		// FIXME review if needed , should be with REL?

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, "Sel", 0);

		t1 = new OpersExpr("109 Ver/Val MUTEXrestr", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, t1, 1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("111 Ver/Val RANGEhardConcept", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexCLGR,
				instVertexCL, instVertexCLGR, "Sel", "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, null, "Sel", "FalseVal", true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexOper, t2, instVertexCLGR, "LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, null, "Sel", "FalseVal", true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexOper, t2, instVertexCLGR, "HighRange");

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexCLGR, t1, t2);

		t1 = new OpersExpr("110 Ver/Val RANGEHardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexCLGR, instVertexCLGR, "OSel", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, null, "TrueVal", 0, true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexCLGR, t1, instVertexF, "LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, null, "Core", "TrueVal", true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexCLGR, t2, instVertexOper, "OCore");

		t1 = new OpersExpr("112 UpCore - ANDFCRel", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexCLGR,
				t1, t2);

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex().put("OperClaimOT", instVertexCLGR);

		OpersConcept directOperClaimToSemanticEdge = new OpersConcept(
				"OperClaimOTToClaimPW");

		attribute = new ElemAttribute("outCl", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directOperClaimToSemanticEdge.putSemanticAttribute("outCl", attribute);
		sasverClallOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClneverOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClCoreOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));

		InstConcept instDirOperClaimOTToClaim = new InstConcept(
				"OperClaimOTToClaimPW", metaMetaPairwiseRelation,
				directOperClaimToSemanticEdge);

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 3,
				"", "", 3, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		directOperClaimToSemanticEdge.putSemanticAttribute("AggregationLow",
				attribute);
		directOperClaimToSemanticEdge.addPropEditableAttribute("03#"
				+ "AggregationLow");
		directOperClaimToSemanticEdge.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 4,
				"", "", 4, "#" + "AggregationHigh" + "#all#]\n",
				"AggregationHigh" + "#!=#" + "0");
		directOperClaimToSemanticEdge.putSemanticAttribute("AggregationHigh",
				attribute);
		directOperClaimToSemanticEdge.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		directOperClaimToSemanticEdge.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ocltoip", instEdge);
		instEdge.setIdentifier("ocltoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirOperClaimOTToClaim, true);

		ia = instDirOperClaimOTToClaim.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
				"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
				"OperToClaim#OperToClaim#true#true#true#0#-1#0#-1"));

		ia = instDirOperClaimOTToClaim.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirOperClaimOTToClaim, instVertexCLGR, instVertexCL,
				"ConditionalExpression", "Sel");

		t1 = new OpersExpr("123 Ver/Val - OPERCLSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexCL, "Sel", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("MANnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexOper,
				instDirOperClaimOTToClaim, "Sel", "TrueVal");

		t1 = new OpersExpr("MANnoHighSel", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"), ExpressionVertexType.RIGHTVARIABLE,
				instDirOperClaimOTToClaim, instDirOperClaimOTToClaim, "PSel",
				false, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirOperClaimOTToClaim, instDirOperClaimOTToClaim,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("121 Val - NoAggre:MANSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirOperClaimOTToClaim, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MANSelSel", refas.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexCL, instVertexCL, "Sel",
				"TrueVal");

		t1 = new OpersExpr("120 Ver/Val MANSel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTVARIABLE, instDirOperClaimOTToClaim,
				instDirOperClaimOTToClaim, "PSel", false, t1);

		semExpr.add(t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		// iterate onver multi-instances

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexOper, "Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirOperClaimOTToClaim, instDirOperClaimOTToClaim,
				"AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexHC, "Sel", true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instDirOperClaimOTToClaim, instDirOperClaimOTToClaim,
				"AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirOperClaimOTToClaim, t1, t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirOperClaimOTToClaim, instDirOperClaimOTToClaim, "PSel",
				true, 1);

		t1 = new OpersExpr("Aggre:MANSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instDirOperClaimOTToClaim, t1, t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirOperClaimOTToClaim, instDirOperClaimOTToClaim,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("119 Val - Aggre:MANSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirOperClaimOTToClaim, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("122 Ver/Val OPERCLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexCLGR, instVertexCL,
				"Exclu", "Exclu");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("operToClaim", new ElemAttribute(
				"operToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "operToClaim", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		refas.getVariabilityVertex().put("OperClaimOTToClaimPW",
				instDirOperClaimOTToClaim);

		// extends
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("clgrtogr", instEdge);
		instEdge.setIdentifier("clgrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexCLGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("OperClaimToPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("OperClaimToPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirOperClaimOTToClaim, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("OperClaimToPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("OperClaimToPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirOperClaimOTToClaim, true);
		instEdge.setSourceRelation(instVertexCLGR, true);

		OpersConcept directOperClaimFromSemanticEdge = new OpersConcept(
				"OperToOperClaimOTPW");

		InstConcept instDirOperClaimFromSemanticEdge = new InstConcept(
				"OperToOperClaimOTPW", metaMetaPairwiseRelation,
				directOperClaimFromSemanticEdge);
		refas.getVariabilityVertex().put("OperToOperClaimOTPW",
				instDirOperClaimFromSemanticEdge);
		// FIXME review syntax to associate it

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("oclftoip", instEdge);
		instEdge.setIdentifier("oclftoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirOperClaimFromSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges()
				.put("OperClaimFromPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("OperClaimFromPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCLGR, true);
		instEdge.setSourceRelation(instDirOperClaimFromSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges()
				.put("OperClaimFromPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("OperClaimFromPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirOperClaimFromSemanticEdge, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("OperClaimPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("OperClaimPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirOperClaimSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("OperClaimPWAsso-OGRO", instEdge);
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

		OpersConcept directFClaimToSemanticEdge = new OpersConcept("FeClToPW");

		InstConcept instDirFClaimToSemanticEdge = new InstConcept(
				"FeClOTToClPW", metaMetaPairwiseRelation,
				directFClaimToSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("lftcltoip", instEdge);
		instEdge.setIdentifier("lftcltoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirFClaimToSemanticEdge, true);

		ia = instDirFClaimToSemanticEdge.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
				"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
				"OperToClaim#OperToClaim#true#true#true#0#-1#0#-1"));

		ia = instDirFClaimToSemanticEdge.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFClaimToSemanticEdge, instVertexLFCLGR, instVertexCL,
				"OSel", "ConditionalExpression");

		t1 = new OpersExpr("Ver/Val - OPERCLSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirFClaimToSemanticEdge, instVertexCL, "Sel", true, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("Ver/Val - OPERCLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFClaimToSemanticEdge, instVertexLFCLGR, instVertexCL,
				"Exclu", "Exclu");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
				"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		refas.getVariabilityVertex().put("FeClOTToClPW",
				instDirFClaimToSemanticEdge);

		// extends
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("fctogr", instEdge);
		instEdge.setIdentifier("fctogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instNmMetaOT, true);
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
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirFClaimFromSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("FClaimFromPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("FClaimFromPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexLFCLGR, true);
		instEdge.setSourceRelation(instDirFClaimFromSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("FClaimFromPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("FClaimFromPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirFClaimFromSemanticEdge, true);
		instEdge.setSourceRelation(instVertexF, true);

		OpersConcept directLFClaimSemanticEdge = new OpersConcept("LfClPW");

		InstConcept instDirLFClaimSemanticEdge = new InstConcept("LfClPW",
				metaMetaPairwiseRelation, directLFClaimSemanticEdge);
		refas.getVariabilityVertex().put("LfClPW", instDirLFClaimSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("lfcltoip", instEdge);
		instEdge.setIdentifier("lfcltoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
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
		OpersConcept directClaimSGSemanticEdge = new OpersConcept("ClaimSgPW");

		attribute = new ElemAttribute("outConflClSD", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directClaimSGSemanticEdge.putSemanticAttribute("outConflClSD",
				attribute);
		sasverConflClOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		attribute = new ElemAttribute("CLSGLevel", "Integer",
				AttributeType.OPERATION, "Relation Level",
				"Required level for the Claim (0..4)", 2, false,
				new RangeDomain(0, 4, 0), 0, 8, "", "", 8, "#CLSGLevel#all#",
				"");
		directClaimSGSemanticEdge.putSemanticAttribute("CLSGLevel", attribute);
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverCoreOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
		// true));
		sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		directClaimSGSemanticEdge.addPropEditableAttribute("08#" + "CLSGLevel");
		directClaimSGSemanticEdge.addPropVisibleAttribute("08#" + "CLSGLevel");
		// directClaimSGSemanticEdge.addPanelVisibleAttribute("08#"
		// + "CLSGLevel");
		InstConcept instDirClaimSGSemanticEdge = new InstConcept("ClaimSgPW",
				metaMetaPairwiseRelation, directClaimSGSemanticEdge);

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 3,
				"", "", 3, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		directClaimSGSemanticEdge.putSemanticAttribute("AggregationLow",
				attribute);
		directClaimSGSemanticEdge.addPropEditableAttribute("03#"
				+ "AggregationLow");
		directClaimSGSemanticEdge.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 4,
				"", "", 4, "#" + "AggregationHigh" + "#all#]\n",
				"AggregationHigh" + "#!=#" + "0");
		directClaimSGSemanticEdge.putSemanticAttribute("AggregationHigh",
				attribute);
		directClaimSGSemanticEdge.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		directClaimSGSemanticEdge.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("clsgtoip", instEdge);
		instEdge.setIdentifier("clsgtoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirClaimSGSemanticEdge, true);

		ia = instDirClaimSGSemanticEdge.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("ClaimToSG", new ElemAttribute("ClaimToSG",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"ClaimToSG", "", "", 1, -1, "", "", -1, "", ""),
				"ClaimToSG##true#true#true#1#-1#0#-1"));

		ia = instDirClaimSGSemanticEdge.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("SGSelected", refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSG,
		// instDirClaimSGSemanticEdge, "Sel", "Sel");
		//
		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t2 = new OpersExpr("CLSGnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t2, t1);

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG, "satisficingLevel",
				"low");

		t1 = new OpersExpr("NoAgglow: ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t3, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirClaimSGSemanticEdge, instDirClaimSGSemanticEdge,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("099 Val - NoAggreLow:CLSGSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t2 = new OpersExpr("CLSGSelSel", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t2, t1);

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG, "satisficingLevel",
				"low");

		t1 = new OpersExpr("100 Ver -  Low: ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t3, t1);

		semExpr.add(t1);

		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexCL, "Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirClaimSGSemanticEdge, instDirClaimSGSemanticEdge,
				"AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexCL, "Sel", true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instDirClaimSGSemanticEdge, instDirClaimSGSemanticEdge,
				"AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirClaimSGSemanticEdge, t1, t2);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG, "satisficingLevel",
				"low");

		t1 = new OpersExpr("NoAgglow: ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t3, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirClaimSGSemanticEdge, instDirClaimSGSemanticEdge,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("096 Ver/Val - AggreLow:CLSGSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t2 = new OpersExpr("CLSGnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t2, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG, "satisficingLevel",
				"high");

		t1 = new OpersExpr("high: ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t3, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirClaimSGSemanticEdge, instDirClaimSGSemanticEdge,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("098 Val - NoAggreHigh:CLSGSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t2 = new OpersExpr("CLSGSelSel", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t2, t1);

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG, "satisficingLevel",
				"high");

		t1 = new OpersExpr("102 Ver - High: Claim", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t3, t1);

		semExpr.add(t1);

		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexCL, "Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirClaimSGSemanticEdge, instDirClaimSGSemanticEdge,
				"AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexCL, "Sel", true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instDirClaimSGSemanticEdge, instDirClaimSGSemanticEdge,
				"AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirClaimSGSemanticEdge, t1, t2);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG, "satisficingLevel",
				"high");

		t1 = new OpersExpr("NoAgglow: ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t3, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirClaimSGSemanticEdge, instDirClaimSGSemanticEdge,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("095 Val - AggreHigh:CLSGSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t2 = new OpersExpr("CLSGnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t2, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG, "satisficingLevel",
				"close");

		t1 = new OpersExpr("close: ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t3, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirClaimSGSemanticEdge, instDirClaimSGSemanticEdge,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("097 Val - NoAggreClose:CLSGSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t2 = new OpersExpr("CLSGnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t2, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG, "satisficingLevel",
				"close");

		t1 = new OpersExpr("101 Ver - close: ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t3, t1);

		semExpr.add(t1);

		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexCL, "Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirClaimSGSemanticEdge, instDirClaimSGSemanticEdge,
				"AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexCL, "Sel", true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instDirClaimSGSemanticEdge, instDirClaimSGSemanticEdge,
				"AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirClaimSGSemanticEdge, t1, t2);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexSG, "satisficingLevel",
				"close");

		t1 = new OpersExpr("NoAgglow: ClaimExpLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t3, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirClaimSGSemanticEdge, instDirClaimSGSemanticEdge,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("094 Val - AggreClose:CLSGSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t2, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("ClaimToSG", new ElemAttribute("ClaimToSG",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"ClaimToSG", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex().put("ClaimSgPW",
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
		attribute = new ElemAttribute("outConflClSD", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directSDSGSemanticEdge.putSemanticAttribute("outConflClSD", attribute);
		sasverConflSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperationSubAction.addOutAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		attribute = new ElemAttribute("level", "Integer",
				AttributeType.OPERATION, "Level",
				"Required level for the SD (0..4)", 1, false, new RangeDomain(
						0, 4, 0), 0, 8, "", "", 8, "level#all#", "");
		directSDSGSemanticEdge.putSemanticAttribute("level", attribute);

		simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDallOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSDneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClneverOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverClneverOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverCoreOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverCoreOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverAllOpersOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverAllOpersOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
		// true));
		sasverSGConflOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverSGConflOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflClOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));

		directSDSGSemanticEdge.addPropEditableAttribute("08#" + "level");
		directSDSGSemanticEdge.addPropVisibleAttribute("08#" + "level");
		// directSDSGSemanticEdge.addPanelVisibleAttribute("08#" + "level");

		InstConcept instDirSDSGSemanticEdge = new InstConcept("SdSgPW",

		metaMetaPairwiseRelation, directSDSGSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sdsgtoip", instEdge);
		instEdge.setIdentifier("sdsgtoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirSDSGSemanticEdge, true);

		ia = instDirSDSGSemanticEdge.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("SD", new ElemAttribute("SD",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "SD", "",
				"", 1, -1, "", "", -1, "", ""), "SD##true#true#true#1#-1#0#-1"));

		ia = instDirSDSGSemanticEdge.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
		// "DoubleImplies"),
		// ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexSD,
		// instDirSDSGSemanticEdge, "Sel", "outSd");
		//
		// semanticExpressions.add(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSDSGSemanticEdge, instVertexSG, instDirSDSGSemanticEdge,
				"SDReqLevel", "level");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSD, "Sel", true, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSG, "satisficingLevel",
				"low");

		t1 = new OpersExpr("129 Ver/Val low: SDReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirSDSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionRelaxable.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSDSGSemanticEdge, instVertexSG, instDirSDSGSemanticEdge,
				"SDReqLevel", "level");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSD, "Sel", true, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSG, "satisficingLevel",
				"high");

		t1 = new OpersExpr("128 Ver/Val high: SDReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirSDSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionRelaxable.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSDSGSemanticEdge, instVertexSG, instDirSDSGSemanticEdge,
				"SDReqLevel", "level");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSD, "Sel", true, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSG, "satisficingLevel",
				"close");

		t1 = new OpersExpr("127 VerGM/Val - close: SDReqLevel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirSDSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionRelaxable.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionRelaxable.addSemanticExpression(t1);

		t1 = new OpersExpr("126 Ver/Val - SDSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSD, instVertexSD, "Sel",
				"ConditionalExpression");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("SD", new ElemAttribute("SD",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "SD", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

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

		OpersConcept semanticAssetAssetOvertwoRel = new OpersConcept("AssetOT");// hardSemOverTwoRelList);

		// attribute = new ElemAttribute("AggregationLow", "Integer",
		// AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 3,
		// "", "", 3, "[#" + "AggregationLow" + "#all#..",
		// "AggregationHigh" + "#!=#" + "0");
		// semanticAssetAssetOvertwoRel.putSemanticAttribute("AggregationLow",
		// attribute);
		// semanticAssetAssetOvertwoRel.addPropEditableAttribute("03#"
		// + "AggregationLow");
		// semanticAssetAssetOvertwoRel.addPropVisibleAttribute("03#"
		// + "AggregationLow");
		//
		// attribute = new ElemAttribute("AggregationHigh", "Integer",
		// AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 4,
		// "", "", 4, "#" + "AggregationHigh" + "#all#]\n",
		// "AggregationHigh" + "#!=#" + "0");
		// semanticAssetAssetOvertwoRel.putSemanticAttribute("AggregationHigh",
		// attribute);
		// semanticAssetAssetOvertwoRel.addPropEditableAttribute("04#"
		// + "AggregationHigh");
		// semanticAssetAssetOvertwoRel.addPropVisibleAttribute("04#"
		// + "AggregationHigh");

		InstConcept instVertexASSETGR = new InstConcept("AssetOT",
				semanticAssetAssetOvertwoRel, metaMetaInstOverTwoRel);

		refas.getVariabilityVertex().put("AssetOT", instVertexASSETGR);

		InstConcept instAssetassetASGR = new InstConcept(
				"AssetToAssetOperOTPW", metaMetaPairwiseRelation);
		refas.getVariabilityVertex().put("AssetToAssetOperOTPW",
				instAssetassetASGR);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("agrtogr", instEdge);
		instEdge.setIdentifier("agrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexASSETGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ASSETGRtoassetA-AGR", instEdge);
		instEdge.setIdentifier("ASSETGRtoassetA-AGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instAssetassetASGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ASSETGRtoassetAGR-GR", instEdge);
		instEdge.setIdentifier("ASSETGRtoassetAGR-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instAssetassetASGR, true);
		instEdge.setSourceRelation(instVertexASSETGR, true);

		InstConcept instAssetassetGRAS = new InstConcept(
				"AssetToAssetOperOTPW", metaMetaPairwiseRelation);
		refas.getVariabilityVertex().put("AssetToAssetOperOTPW",
				instAssetassetGRAS);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assettoAssetGR-AGR-A", instEdge);
		instEdge.setIdentifier("assettoAssetGR-AGR-A");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instAssetassetGRAS, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assettoAssetGR-GR-AGR", instEdge);
		instEdge.setIdentifier("assettoAssetGR-GR-AGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexASSETGR, true);
		instEdge.setSourceRelation(instAssetassetGRAS, true);

		// Asset to Oper
		// TODO use list of possible relations
		OpersConcept semanticAssetOperGroupRelation = new OpersConcept(
				"AssetOperOT");// hardSemOverTwoRelList);

		attribute = new ElemAttribute("structVal", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "No loops validation",
				"", 0, new RangeDomain(0, 40, 0), 0, -1, "false", "", -1, "",
				"");
		semanticAssetOperGroupRelation.putSemanticAttribute("structVal",
				attribute);
		sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semanticAssetOperGroupRelation.getIdentifier(), attribute
						.getName(), true));
		sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semanticAssetOperGroupRelation.getIdentifier(), attribute
						.getName(), true));

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semOperationalization);

		InstConcept instVertexAssOPERGR = new InstConcept("AssetOperOT",
				semanticAssetOperGroupRelation, metaMetaInstOverTwoRel);

		refas.getVariabilityVertex().put("AssetOperOT", instVertexAssOPERGR);

		InstConcept instAssetOperAOGR = new InstConcept("AssetOperToOT",
				metaMetaPairwiseRelation);
		refas.getVariabilityVertex().put("AssetOperToOT", instAssetOperAOGR);

		// extends
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("opgrtogr", instEdge);
		instEdge.setIdentifier("opgrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexAssOPERGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("aogrtogr", instEdge);
		instEdge.setIdentifier("aogrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaOT, true);
		instEdge.setSourceRelation(instAssetOperAOGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assettoOperGR-AAOGR", instEdge);
		instEdge.setIdentifier("assettoOperGR-AAOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instAssetOperAOGR, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assettoOperGR-AOGRGR", instEdge);
		instEdge.setIdentifier("assettoOperGR-AOGRGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexAssOPERGR, true);
		instEdge.setSourceRelation(instAssetOperAOGR, true);

		ia = instVertexAssOPERGR.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""),
				"and#and#true#true#true#2#-1#1#1"));

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""),
				"or#or#false#true#true#2#-1#1#1"));

		ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", "", 1, -1, "", "", -1, "", ""),
				"mutex#mutex#false#true#true#2#-1#1#1"));

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""),
				"range#range#false#true#true#2#-1#1#1"));

		ia = instVertexAssOPERGR.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("139 ANDFOpGrSelConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAssOPERGR,
				instVertexOper, instVertexAssOPERGR, "Sel", "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("142 UpCore - ANDAssOperCoreConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAssOPERGR,
				instVertexOper, instVertexAssOPERGR, "Core", "OCore");

		// verifParentsOperSubActionNormal.addSemanticExpression(t1);

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, "Sel", true, "TrueVal");

		t1 = new OpersExpr("140 Ver/Val - ANDAssOperSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexOper, t1, "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, "Core", true, "TrueVal");

		t1 = new OpersExpr("141 UpCore - ANDAssOperCoreRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, t1, "OCore");

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("139b Ver/Val OROperOperGRSelConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAssOPERGR,
				instVertexOper, instVertexAssOPERGR, "Sel", "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, "Sel", true, "FalseVal");

		t1 = new OpersExpr("143 Ver/Val ORAssOperSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, t1, "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("139c Ver/Val MUTEOperOperGrSelConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAssOPERGR,
				instVertexOper, instVertexAssOPERGR, "Sel", "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, "Sel", 0);

		t1 = new OpersExpr("sub2operassetsel", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, t1, 1);

		t1 = new OpersExpr("145 Ver/Val MUTEXAssOperGrSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexAssOPERGR, instVertexAssOPERGR, "OSel", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, "Sel", 0);

		t1 = new OpersExpr("144 Ver/Val MUTEXAssOperGrrestric", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, t1, 1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("139d Ver/Val RANGEOperOperGrSelConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAssOPERGR,
				instVertexOper, instVertexAssOPERGR, "Sel", "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, null, "Sel", "FalseVal",
				true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, t1, instVertexAsset, "LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, null, "Sel", "FalseVal",
				true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, t2, instVertexAsset, "HighRange");

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexAssOPERGR, t1, t2);

		t1 = new OpersExpr("146 Ver/Val RANGEHardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexAssOPERGR, instVertexAssOPERGR, "OSel", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, null, "TrueVal", 0, true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, t1, instVertexAsset, "LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, null, "Core", "TrueVal",
				true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, t2, instVertexAsset, "OCore");

		t1 = new OpersExpr("147 UpCore - ANDFCRel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instVertexAssOPERGR, t1, t2);

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		OpersConcept semanticAssetLfGroupRelation = new OpersConcept(
				"AssetLfOT");// hardSemOverTwoRelList);

		attribute = new ElemAttribute("structVal", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "No loops validation",
				"", 0, new RangeDomain(0, 40, 0), 0, -1, "false", "", -1, "",
				"");
		semanticAssetLfGroupRelation.putSemanticAttribute("structVal",
				attribute);
		sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
				semanticAssetLfGroupRelation.getIdentifier(), attribute
						.getName(), true));
		sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semanticAssetLfGroupRelation.getIdentifier(), attribute
						.getName(), true));

		InstConcept instVertexAsLFGR = new InstConcept("AssetLfOT",
				semanticAssetLfGroupRelation, metaMetaInstOverTwoRel);

		refas.getVariabilityVertex().put("AssetLfOT", instVertexAsLFGR);

		InstConcept instLFOperAOGR = new InstConcept("AssetLfToOT",
				metaMetaPairwiseRelation);
		refas.getVariabilityVertex().put("AssetLfToOT", instLFOperAOGR);

		// extends
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("lfgrtogrhr", instEdge);
		instEdge.setIdentifier("lfgrtogrhr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexAsLFGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("lfdstogr", instEdge);
		instEdge.setIdentifier("lfdstogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instLFOperAOGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assettolfGR-AAOGR", instEdge);
		instEdge.setIdentifier("assettolfGR-AAOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instLFOperAOGR, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assettolfGR-AOGRGR", instEdge);
		instEdge.setIdentifier("assettolfGR-AOGRGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexAsLFGR, true);
		instEdge.setSourceRelation(instLFOperAOGR, true);

		ia = instVertexAsLFGR.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""),
				"and#and#true#true#true#2#-1#1#1"));

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""),
				"or#or#false#true#true#2#-1#1#1"));

		ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "XOR", "",
				"", 1, -1, "", "", -1, "", ""),
				"mutex#mutex#false#true#true#2#-1#1#1"));

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""),
				"range#range#false#true#true#2#-1#1#1"));

		ia = instVertexAsLFGR.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("NA Ver/Val ANDfeatSetConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsLFGR,
				instVertexF, instVertexAsLFGR, "Sel", "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("NA UpCore ANDFeOpCoreConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsLFGR,
				instVertexF, instVertexAsLFGR, "Core", "OCore");

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, "Sel", true, "TrueVal");

		t1 = new OpersExpr("NA Ver/Val - ANDOperOperGrRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexAsLFGR,
				instVertexF, t1, "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, "Core", true, "TrueVal");

		t1 = new OpersExpr("NA ANDOperOperGrCoreRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexAsLFGR,
				instVertexF, t1, "OCore");

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("NA OROperConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsLFGR,
				instVertexF, instVertexAsLFGR, "Sel", "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, "Sel", true, "False");

		t1 = new OpersExpr("NA ORAssetRel", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexAsLFGR,
				instVertexAsset, t1, "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("NA MUTEXOperConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsLFGR,
				instVertexF, instVertexAsLFGR, "Sel", "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsset, instVertexAsset, "Sel", 0);

		t1 = new OpersExpr("sub2opergrsel", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, t1, 1);

		t1 = new OpersExpr("NA MUTEXhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexAsLFGR, instVertexF, "Sel", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, "Sel", 0);

		t1 = new OpersExpr("NA MUTEXrestric", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexAsLFGR,
				instVertexAsset, t1, 1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("NA RANGEOperConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsLFGR,
				instVertexF, instVertexAsLFGR, "Sel", "OSel");

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, null, "Sel", "FalseVal",
				true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAsLFGR, t2, instVertexAsset, "LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, null, "Sel", "FalseVal",
				true);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAsLFGR, t2, instVertexAsset, "HighRange");

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexAsLFGR, t1, t3);

		t1 = new OpersExpr("NA RANGEHardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexAsLFGR, instVertexAsLFGR, "OSel", true, t1);

		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, null, "TrueVal", 0, true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAsLFGR, t1, instVertexF, "LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, null, "Core", "TrueVal",
				true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAsLFGR, t2, instVertexAsset, "OCore");

		t1 = new OpersExpr("NA ** ANDFCRel", refas.getSemanticExpressionTypes()
				.get("Implies"), instVertexAsLFGR, t1, t2);

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		OpersConcept groupAssetOperSemanticEdge = new OpersConcept(
				"AssetOperOTtoOperPW");

		// attribute = new ElemAttribute("outConflSG", "Boolean",
		// AttributeType.OPERATION, false,
		// "Selected for SD verifications", "", false, 0, -1, "", "", -1,
		// "level#all#", "");
		// groupAssetOperSemanticEdge
		// .putSemanticAttribute("outConflSG", attribute);
		// sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// groupAssetOperSemanticEdge.getIdentifier(),
		// attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// groupAssetOperSemanticEdge.getIdentifier(),
		// attribute.getName(), true));

		attribute = new ElemAttribute("outStructVal", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		groupAssetOperSemanticEdge.putSemanticAttribute("outStructVal",
				attribute);
		sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
				groupAssetOperSemanticEdge.getIdentifier(),
				attribute.getName(), true));
		sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				groupAssetOperSemanticEdge.getIdentifier(),
				attribute.getName(), true));

		InstConcept instAssetOperGRAO = new InstConcept("AssetOperOTtoOperPW",
				metaMetaPairwiseRelation, groupAssetOperSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("aofottoip", instEdge);
		instEdge.setIdentifier("aofottoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instAssetOperGRAO, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("aogrtogr", instEdge);
		instEdge.setIdentifier("aogrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaOT, true);
		instEdge.setSourceRelation(instAssetOperAOGR, true);

		ia = instAssetOperGRAO.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("implementedBy", new ElemAttribute(
				"implemented by", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implemented by", "", "", 1, -1, "", "", -1, "", ""),
				"implementedBy#implemented by#true#true#true#0#-1#0#1"));

		ia = instAssetOperGRAO.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("132 Ver/Val - AssetOperGRIMPSel", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instAssetOperGRAO, instVertexAssOPERGR, instVertexOper, "OSel",
				"Sel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("133 Ver/Val IMPNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instAssetOperGRAO, instVertexOper, instVertexAssOPERGR,
				"Exclu", "Exclu");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instAssetOperGRAO, instVertexOper, "structVal", 1);

		t1 = new OpersExpr("134 NoLoop structVal", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instAssetOperGRAO, instVertexAssOPERGR, "structVal", true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		ias.add(new InstAttribute("implementedBy", new ElemAttribute(
				"implementedBy", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implemented by", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		refas.getVariabilityVertex().put("AssetOperOTtoOperPW",
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
		instEdge.setSourceRelation(instVertexAssOPERGR, true);

		InstConcept instAssetLfGRAO = new InstConcept("AssetLfOTToLf",
				metaMetaPairwiseRelation, groupAssetOperSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("aofottoip", instEdge);
		instEdge.setIdentifier("aofottoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instAssetLfGRAO, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("aogrtogr", instEdge);
		instEdge.setIdentifier("aogrtogr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaOT, true);
		instEdge.setSourceRelation(instAssetOperAOGR, true);

		ia = instAssetLfGRAO.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("implementedBy", new ElemAttribute(
				"implementedBy", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implementedBy", "", "", 1, -1, "", "", -1, "", ""),
				"implementedBy#implementedBy#true#true#true#0#-1#0#1"));

		ia = instAssetLfGRAO.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("NA AssetLFGRIMPSel", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instAssetLfGRAO, instVertexAsset, instVertexAsLFGR, "Sel",
				"OSel");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("NA IMPNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instAssetLfGRAO, instVertexAsset, instVertexAsLFGR, "Exclu",
				"Exclu");

		semExpr.add(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		sasverClallOperSubActionNormal.addSemanticExpression(t1);
		sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE, instAssetLfGRAO,
				instVertexAsLFGR, "structVal", 1);

		t1 = new OpersExpr("NA 2", refas.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instAssetLfGRAO, instVertexAsset, "structVal", true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);

		ias.add(new InstAttribute("implementedBy", new ElemAttribute(
				"implementedBy", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implementedBy", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		refas.getVariabilityVertex().put("AssetLfOTToLf", instAssetLfGRAO);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("AssetLfOTToLf-from", instEdge);
		instEdge.setIdentifier("AssetLfOTToLf-from");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instAssetLfGRAO, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("AssetLfOTToLf-to", instEdge);
		instEdge.setIdentifier("AssetLfOTToLf-to");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instAssetLfGRAO, true);
		instEdge.setSourceRelation(instVertexAsLFGR, true);

		// Duplicated
		// OpersConcept directAssetOperSemanticEdge = new OpersConcept(
		// "AssetOperPW");
		//
		// attribute = new ElemAttribute("outStructVal", "Boolean",
		// AttributeType.OPERATION, false,
		// "Selected for SD verifications", "", false, 0, -1, "", "", -1,
		// "level#all#", "");
		// directAssetOperSemanticEdge.putSemanticAttribute("outStructVal",
		// attribute);
		// sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// directAssetOperSemanticEdge.getIdentifier(), attribute
		// .getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directAssetOperSemanticEdge.getIdentifier(), attribute
		// .getName(), true));
		//
		// InstConcept instDirAssetOperSemanticEdge = new InstConcept(
		// "AssetOperPW", metaMetaPairwiseRelation,
		// directAssetOperSemanticEdge);
		//
		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("aoptoip", instEdge);
		// instEdge.setIdentifier("aoptoip");
		// instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		// instEdge.setTargetRelation(instInfraPair, true);
		// instEdge.setSourceRelation(instDirAssetOperSemanticEdge, true);
		//
		// ia = instDirAssetOperSemanticEdge.getInstAttribute("relTypesAttr");
		// ias = (List<InstAttribute>) ia.getValue();
		// ias.add(new InstAttribute("mandatory", new ElemAttribute("mandatory",
		// StringType.IDENTIFIER, AttributeType.OPTION, false,
		// "mandatory", "", "", 1, -1, "", "", -1, "", ""),
		// "mandatory#mandatory#true#true#true#1#-1#1#1"));
		//
		// ia = instDirAssetOperSemanticEdge.getInstAttribute("opersExprs");
		// ias = (List<InstAttribute>) ia.getValue();
		//
		// semExpr = new ArrayList<OpersExpr>();
		//
		// t1 = new OpersExpr("OpHCIMPSel", refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
		// instDirAssetOperSemanticEdge, instVertexHC, instVertexOper,
		// "Sel", "Sel");
		//
		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		//
		// t1 = new OpersExpr("sub",
		// refas.getSemanticExpressionTypes().get("And"),
		// ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
		// instDirAssetOperSemanticEdge, instVertexAsset, instVertexAsset,
		// "Core", "TrueVal");
		//
		// t1 = new OpersExpr("ANDAssOperCoreRel", refas
		// .getSemanticExpressionTypes().get("DoubleImplies"),
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
		// instDirAssetOperSemanticEdge, instVertexOper, "Core", false, t1);
		//
		// updCoreOptSubOperNormal.addSemanticExpression(t1);
		// semExpr.add(t1);
		//
		// t1 = new OpersExpr("IMPNotAvailable", refas
		// .getSemanticExpressionTypes().get("Equals"),
		// ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
		// instDirAssetOperSemanticEdge, instVertexAsset, instVertexOper,
		// "Exclu", "Exclu");
		//
		// semExpr.add(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		//
		// t1 = new OpersExpr("2",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirAssetOperSemanticEdge, instVertexOper, "structVal", 1);
		//
		// t1 = new OpersExpr("2", refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// instDirAssetOperSemanticEdge, instVertexAsset, "structVal",
		// true, t1);
		//
		// semExpr.add(t1);
		// sasverNoLoopsOperSubActionRelaxable.addSemanticExpression(t1);
		//
		// ias.add(new InstAttribute("mandatory", new ElemAttribute("mandatory",
		// StringType.IDENTIFIER, AttributeType.OPTION, false,
		// "mandatory", "", "", 1, -1, "", "", -1, "", ""), semExpr));
		//
		// refas.getVariabilityVertex().put("AssetOperPW",
		// instDirAssetOperSemanticEdge);
		//
		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("AssetOperPW-OOGR", instEdge);
		// instEdge.setIdentifier("AssetOperPW-OOGR");
		// instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		// instEdge.setTargetRelation(instVertexOper, true);
		// instEdge.setSourceRelation(instDirAssetOperSemanticEdge, true);
		//
		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("AssetOperPW-OGRO", instEdge);
		// instEdge.setIdentifier("AssetOperPW-OGRO");
		// instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		// instEdge.setTargetRelation(instDirAssetOperSemanticEdge, true);
		// instEdge.setSourceRelation(instVertexAsset, true);

	}

	private static void createREFASMetaModelElement(ModelInstance refas) {
		ArrayList<OpersExpr> semExpr = new ArrayList<OpersExpr>();

		ElemAttribute attribute = null;

		OpersConcept refasModel = new OpersConcept("REFAS");

		attribute = new ElemAttribute("TotalOrder", "Integer",
				AttributeType.EXECCURRENTSTATE, "***TotalOrder***", "", 0,
				false, new RangeDomain(0, 2000, 0), 2, -1, "", "", -1, "", "");
		// simulationExecOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// refasModel.getIdentifier(), attribute.getName(), true));
		refasModel.putSemanticAttribute("TotalOrder", attribute);

		semExpr = new ArrayList<OpersExpr>();

		simsceExecOperLab2.setSemanticExpressions(semExpr);

		InstConcept instRefasModel = null;
		instRefasModel = new InstConcept("REFAS", metaMetaModel, refasModel);
		refas.getVariabilityVertex().put("REFAS", instRefasModel);

		OpersExpr t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFSUBTITERCONVARIABLE,
				instRefasModel, instVertexSG, "Sel", 0);

		t1 = new OpersExpr("max soft goals", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
				instRefasModel, instVertexSG, t1, 0);

		// semExpr.add(t1); // FIXME not used

		// ----------------------

		semExpr = new ArrayList<OpersExpr>();

		refasModel.setSemanticExpressions(semExpr);

		// t1 = new OpersExpr("sub",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// ExpressionVertexType.LEFTITERCONFIXEDVARIABLE, instVertexGE,
		// "TestConfSel", 0);
		//
		// t1 = new OpersExpr("TestConfSel <=1",
		// refas.getSemanticExpressionTypes()
		// .get("LessOrEquals"),
		// ExpressionVertexType.LEFTITERCONCEPTVARIABLE, instRefasModel,
		// instVertexGE, t1, 1);
		//
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFSUBTITERCONVARIABLE, instRefasModel,
				instVertexF, "IsRootFeature", 0);

		t1 = new OpersExpr("014 Roots", refas.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
				instRefasModel, instVertexF, t1, 1);

		verifRootSubOperVeri.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
				"Product"), ExpressionVertexType.LEFSUBTITERCONVARIABLE,
				instRefasModel, instVertexF, "HasParent", 1);

		t1 = new OpersExpr("Parents", refas.getSemanticExpressionTypes().get(
				"Less"), ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
				instRefasModel, instVertexF, t1, 1);

		// verifParentsOperSubActionVerification.addSemanticExpression(t1);

		// semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFSUBTITERCONVARIABLE, instRefasModel,
				instVertexGE, "Core", 0);

		t1 = new OpersExpr("Core", refas.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instRefasModel, instRefasModel, t1, "TotalOrder");

		// updCoreOptSubOperNormal.addSemanticExpression(t1);

		// semExpr.add(t1);

		// --------------------------
		semExpr = new ArrayList<OpersExpr>(); // FIXME not used

		simulExecOperUniLab.setSemanticExpressions(semExpr);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFSUBTITERCONVARIABLE, instRefasModel,
				instVertexGE, "Order", 0);

		// t1 = new OpersExpr("OrderMin",
		// refas.getSemanticExpressionTypes().get(
		// "Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
		// instRefasModel, instVertexGE, t1, "FalseVal");

		semExpr.add(t1);

		// t1 = new OpersExpr("sub",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// ExpressionVertexType.LEFSUBTITERCONVARIABLE, instRefasModel,
		// instVertexGE, "Opt", 0);
		//
		// semExpr.add(t1);

		semExpr = new ArrayList<OpersExpr>(); // FIXME not used

		simsceExecOperLab2.setSemanticExpressions(semExpr);

		t1 = new OpersExpr("OrderLab...", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFSUBTITERCONVARIABLE,
				instRefasModel, instVertexSG, "Sel", 0);

		semExpr.add(t1);
	}
}
