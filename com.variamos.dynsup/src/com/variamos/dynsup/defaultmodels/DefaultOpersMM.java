package com.variamos.dynsup.defaultmodels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.variamos.common.core.utilities.StringUtils;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.model.LowExpr;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.OpersExpr;
import com.variamos.dynsup.model.OpersIOAttribute;
import com.variamos.dynsup.model.OpersLabeling;
import com.variamos.dynsup.model.OpersSubOperation;
import com.variamos.dynsup.model.OpersSubOperationExpType;
import com.variamos.dynsup.model.OpersVariable;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.ExpressionVertexType;
import com.variamos.dynsup.types.OpersDefectType;
import com.variamos.dynsup.types.OpersOpType;
import com.variamos.dynsup.types.OpersSubOpType;
import com.variamos.dynsup.types.StringType;
import com.variamos.dynsup.types.VariableType;
import com.variamos.hlcl.model.LabelingOrderEnum;
import com.variamos.hlcl.model.domains.RangeDomain;
import com.variamos.hlcl.model.domains.StringDomain;

public class DefaultOpersMM {

	// Initial analysis

	protected static OpersConcept voidModelOper = null;
	protected static OpersSubOperation voidModelSubOperationAction = null;
	protected static OpersSubOperationExpType voidModelSubOperNormal = null;
	protected static OpersLabeling voidModelOperUniqueLabeling = new OpersLabeling(
			"unique");

	protected static OpersConcept validProductElemOper = null;
	protected static OpersSubOperation validProductSubOperationAction = null;
	protected static OpersSubOperationExpType validProductSubOperNormal = null;
	protected static OpersLabeling validProductOperUniqueLabeling = new OpersLabeling(
			"unique");

	protected static OpersConcept validPartialConfOper = null;
	protected static OpersSubOperation validPartialConfSubOperationAction = null;
	protected static OpersSubOperationExpType validPartialConfSubOperNormal = null;
	protected static OpersLabeling validPartialConfOperUniqueLabeling = new OpersLabeling(
			"unique");

	protected static OpersConcept allProductsOper = null;
	protected static OpersSubOperation allProductsSubOperationAction = null;
	protected static OpersSubOperationExpType allProductsSubOperNormal = null;
	protected static OpersLabeling allProductsOperUniqueLabeling = new OpersLabeling(
			"unique");

	protected static OpersConcept numProductsOper = null;
	protected static OpersSubOperation numProductsSubOperationAction = null;
	protected static OpersSubOperationExpType numProductsSubOperNormal = null;
	protected static OpersLabeling numProductsOperUniqueLabeling = new OpersLabeling(
			"unique");

	protected static OpersConcept filterOper = null;
	protected static OpersSubOperation filterSubOperationAction = null;
	protected static OpersSubOperationExpType filterSubOperNormal = null;
	protected static OpersLabeling filterOperUniqueLabeling = new OpersLabeling(
			"unique");

	// End initial analysis

	// Anormalities detection
	protected static OpersConcept verifDeadElemOper = null;
	protected static OpersSubOperation verifDeadElemSubOperationAction = null;
	protected static OpersSubOperationExpType verifDeadElemSubOperNormal = null;
	protected static OpersLabeling verifDeadElemOperUniqueLabeling = new OpersLabeling(
			"unique");

	protected static OpersConcept condDeadElemOper = null;
	protected static OpersSubOperation condDeadElemSubOperationAction = null;
	protected static OpersSubOperationExpType condDeadElemSubOperNormal = null;
	protected static OpersLabeling condDeadElemOperUniqueLabeling = new OpersLabeling(
			"unique");

	protected static OpersConcept verifFalseOptOper = null;
	protected static OpersSubOperation verifFalseOptSubOperationAction = null;
	protected static OpersSubOperationExpType verifFalseOptOperSubActionNormal = null;
	protected static OpersLabeling verifFalseOptElemOperUniqueLabeling = new OpersLabeling(
			"unique");

	protected static OpersConcept wrongCardOptOper = null;
	protected static OpersSubOperation wrongCardSubOperationAction = null;
	protected static OpersSubOperationExpType wrongCardOperSubActionNormal = null;
	protected static OpersSubOperationExpType wrongCardOperSubActionRelaxable = null;
	protected static OpersLabeling wrongCardElemOperUniqueLabeling = new OpersLabeling(
			"unique");

	protected static OpersConcept redundanOptOper = null;
	protected static OpersSubOperation redundanSubOperationAction = null;
	protected static OpersSubOperationExpType redundanOperSubActionNormal = null;
	protected static OpersSubOperationExpType redundanOperSubActionToVerify = null;
	protected static OpersLabeling redundanOperUniqueLabeling = new OpersLabeling(
			"unique");

	// End Anormalities detection

	// Core features

	protected static OpersConcept updCoreOper = null;
	protected static OpersSubOperation updateCoreSubOperationAction = null;
	protected static OpersSubOperationExpType updCoreOptSubOperNormal = null;
	protected static OpersLabeling updateCoreOperUniqueLabeling = new OpersLabeling(
			"unique");

	// Variant features

	protected static OpersConcept variantOper = null;
	protected static OpersSubOperation variantSubOperationAction = null;
	protected static OpersSubOperationExpType variantOptSubOperNormal = null;
	protected static OpersLabeling variantOperUniqueLabeling = new OpersLabeling(
			"unique");

	// Computational Analysis

	protected static OpersConcept homogeneityOper = null;
	protected static OpersSubOperation homogeneitySubOperationAction1 = null;
	protected static OpersSubOperation homogeneitySubOperationAction2 = null;
	protected static OpersSubOperationExpType homogeneityOperSubActionNormal1 = null;
	protected static OpersSubOperationExpType homogeneityOperSubActionToVerify1 = null;
	protected static OpersSubOperationExpType homogeneityOperSubActionNormal2 = null;
	protected static OpersLabeling homogoneityOperUniqueLabeling1 = new OpersLabeling(
			"unique");
	protected static OpersLabeling homogoneityOperUniqueLabeling2 = new OpersLabeling(
			"unique");

	protected static OpersConcept commonalityOper = null;
	protected static OpersSubOperation commonalitySubOperationAction1 = null;
	protected static OpersSubOperation commonalitySubOperationAction2 = null;
	protected static OpersSubOperationExpType commonalityOperSubActionNormal1 = null;
	protected static OpersSubOperationExpType commonalityOperSubActionNormal2 = null;
	protected static OpersLabeling commonalityOperUniqueLabeling1 = new OpersLabeling(
			"unique");
	protected static OpersLabeling commonalityOperUniqueLabeling2 = new OpersLabeling(
			"unique");

	protected static OpersConcept variabfactorOper = null;
	protected static OpersSubOperation variabfactorSubOperationAction1 = null;
	protected static OpersSubOperation variabfactorSubOperationAction2 = null;
	protected static OpersSubOperationExpType variabfactorOperSubActionNormal1 = null;
	protected static OpersSubOperationExpType variabfactorOperSubActionNormal2 = null;
	protected static OpersLabeling variabfactorOperUniqueLabeling1 = new OpersLabeling(
			"unique");
	protected static OpersLabeling variabfactorOperUniqueLabeling2 = new OpersLabeling(
			"unique");

	protected static OpersConcept degreeOrthoOper = null;
	protected static OpersSubOperation degreeOrthoSubOperationAction1 = null;
	protected static OpersSubOperation degreeOrthoSubOperationAction2 = null;
	protected static OpersSubOperationExpType degreeOrthoOperSubActionNormal1 = null;
	protected static OpersSubOperationExpType degreeOrthoOperSubActionNormal2 = null;
	protected static OpersLabeling degreeOrthoOperUniqueLabeling1 = new OpersLabeling(
			"unique");
	protected static OpersLabeling degreeOrthoOperUniqueLabeling2 = new OpersLabeling(
			"unique");

	protected static OpersConcept ecrOper = null;
	protected static OpersSubOperation ecrSubOperationAction1 = null;
	protected static OpersSubOperation ecrSubOperationAction2 = null;
	protected static OpersSubOperationExpType ecrOperSubActionNormal1 = null;
	protected static OpersSubOperationExpType ecrOperSubActionNormal2 = null;
	protected static OpersLabeling ecrOperUniqueLabeling1 = new OpersLabeling(
			"unique");
	protected static OpersLabeling ecrOperUniqueLabeling2 = new OpersLabeling(
			"unique");

	// End computational analysis

	// Lowest Common Ancestor

	protected static OpersConcept lcaOper = null;
	protected static OpersSubOperation lcaSubOperationAction = null;
	protected static OpersSubOperationExpType lcaSubOperNormal = null;
	protected static OpersLabeling lcaOperUniqueLabeling = new OpersLabeling(
			"unique");

	// Root feature

	protected static OpersConcept rootOper = null;
	protected static OpersSubOperation rootSubOperationAction = null;
	protected static OpersSubOperationExpType rootSubOperNormal = null;
	protected static OpersLabeling rootOperUniqueLabeling = new OpersLabeling(
			"unique");

	// Other opers

	protected static OpersConcept verifFalsePLOper = null;
	protected static OpersSubOperation verifFalsePLSubOperationAction = null;
	protected static OpersSubOperationExpType verifFalsePLOperSubActionNormal = null;
	protected static OpersSubOperationExpType verifFalsePLOperSubActionToVerify = null;
	protected static OpersLabeling verifFalsePLElemOperUniqueLabeling = new OpersLabeling(
			"unique");

	protected static OpersConcept verifParentsOper = null;
	protected static OpersSubOperationExpType verifParentsOperSubActionNormal = null;
	protected static OpersSubOperationExpType verifParentsOperSubActionToVerify = null;

	protected static OpersConcept verifRootOper = null;
	protected static OpersSubOperationExpType verifRootOperSubActionNormal = null;
	protected static OpersSubOperationExpType verifRootOperSubActionRelaxable = null;
	protected static OpersSubOperationExpType verifRootSubOperVeri = null;

	protected static OpersConcept simulOper = null;
	protected static OpersSubOperationExpType simulExecOptSubOperNormal = null;

	protected static OpersConcept simulScenOper = null;
	protected static OpersSubOperationExpType simulScenExecOptSubOperNormal = null;

	protected static OpersConcept configTempOper = null;

	protected static OpersSubOperationExpType configTemporalOptOperSubActionNormal = null;

	protected static OpersConcept configPermOper = null;

	protected static OpersSubOperationExpType configPermanentOptOperSubActionNormal = null;

	protected static OpersConcept sasverSDallOper = null;
	protected static OpersSubOperationExpType sasverSDallOperSubActionNormal = null;

	protected static OpersConcept sasverSDCoreOper = null;
	protected static OpersSubOperationExpType sasverSDCoreOperSubActionNormal = null;

	protected static OpersConcept sasverSDneverOperationAction = null;
	protected static OpersSubOperationExpType sasverSDneverOperSubActionNormal = null;

	protected static OpersConcept sasverClCoreOperationAction = null;
	protected static OpersSubOperationExpType sasverClCoreOperSubActionNormal = null;

	protected static OpersConcept sasverClallOperationAction = null;
	protected static OpersSubOperationExpType sasverClallOperSubActionNormal = null;

	protected static OpersConcept sasverClneverOperationAction = null;
	protected static OpersSubOperationExpType sasverClneverOperSubActionNormal = null;

	protected static OpersConcept sasverCoreOpersOperationAction = null;
	protected static OpersSubOperationExpType sasverCoreOpersOperSubActionNormal = null;

	protected static OpersConcept sasverAllOpersOperationAction = null;
	protected static OpersSubOperationExpType sasverAllOpersOperSubActionNormal = null;

	protected static OpersConcept sasverNoLoopsOperationAction = null;
	protected static OpersSubOperationExpType sasverNoLoopsOperSubActionMVNormal = null;
	protected static OpersSubOperationExpType sasverNoLoopsOperSubActionMVRelaxable = null;

	protected static OpersSubOperationExpType sasverNoLoopsOperSubActionRedNormal = null;
	protected static OpersSubOperationExpType sasverNoLoopsOperSubActionRedToVerify = null;

	protected static OpersConcept sasverSGConflperationAction = null;
	protected static OpersSubOperationExpType sasverSGConflOperSubActionNormal = null;
	protected static OpersSubOperationExpType sasverSGConflOperSubActionRelaxable = null;
	protected static OpersSubOperationExpType sasverSGConflOperSubActionVerification = null;

	protected static OpersConcept sasverConflClSDOperationAction = null;
	protected static OpersSubOperationExpType sasverConflClSDOperSubActionNormal = null;
	protected static OpersSubOperationExpType sasverConflClSDOperSubActionRelaxable = null;
	protected static OpersSubOperationExpType sasverConflClSDOperSubActionVerification = null;

	protected static OpersConcept sasverConflClOperationAction = null;
	protected static OpersSubOperationExpType sasverConflClOperSubActionNormal = null;
	protected static OpersSubOperationExpType sasverConflClOperSubActionRelaxable = null;
	protected static OpersSubOperationExpType sasverConflClOperSubActionVerification = null;

	protected static OpersConcept sasverConflSDOperationAction = null;
	protected static OpersSubOperationExpType sasverConflSDOperSubActionNormal = null;
	protected static OpersSubOperationExpType sasverConflSDOperSubActionRelaxable = null;

	protected static OpersSubOperation simulSubOperationAction = null;
	protected static OpersSubOperation simulPreUpdateSubOperationAction = null;
	protected static OpersSubOperation simSceSubOperationAction = null;
	protected static OpersSubOperation verifRootSubOperationAction = null;
	protected static OpersSubOperation verifParentsSubOperationAction = null;

	protected static OpersSubOperation sasverSDCoreOperationSubAction = null;
	protected static OpersSubOperation sasverSDallOperationSubAction = null;
	protected static OpersSubOperation sasverSDneverOperationSubAction = null;
	protected static OpersSubOperation sasverClCoreOperationSubAction = null;
	protected static OpersSubOperation sasverClallOperationSubAction = null;
	protected static OpersSubOperation sasverClneverOperationSubAction = null;
	protected static OpersSubOperation sasverCoreOpersOperationSubAction = null;
	protected static OpersSubOperation sasverAllOpersOperationSubAction = null;
	protected static OpersSubOperation sasverNoLoopsOperationSubActionMV = null;
	protected static OpersSubOperation sasverNoLoopsOperationSubActionRed = null;
	protected static OpersSubOperation sasverSGConflOperationSubAction = null;
	protected static OpersSubOperation sasverConflClSDOperationSubAction = null;
	protected static OpersSubOperation sasverConflSDOperationSubAction = null;
	protected static OpersSubOperation sasverConflClOperationSubAction = null;

	protected static OpersLabeling simulPreUpdateOperUniLab = new OpersLabeling(
			"unique");

	protected static OpersLabeling simulExecOperUniLab = new OpersLabeling(
			"unique");

	protected static OpersLabeling simsceExecOperLabeling1 = new OpersLabeling(
			"all");

	protected static OpersLabeling simsceExecOperLab2 = new OpersLabeling(
			"once"); // TODO
	// define
	// max
	// for SG
	protected static OpersLabeling verifParentsElemOperUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverSDCoreOperUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverSDallOperUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverSDneverOperUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverClCoreOperUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverClallOperUniqLab = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverClneverOperUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverCoreOpersOperUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverAllOpersOperUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverNoLoopsOperMVUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverNoLoopsOperRedUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverSGConflOperUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverConflClSDOperUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverConflClOperUniqueLabeling = new OpersLabeling(
			"unique");
	protected static OpersLabeling sasverConflSDOperUniqueLabeling = new OpersLabeling(
			"unique");

	protected static InstElement metaMetaModel = null;
	protected static InstElement metaOperationMenu = null;
	protected static InstElement metaOperationAction = null;
	protected static InstElement metaOperationSubAction = null;
	protected static InstElement metaLabeling = null;
	protected static InstElement metaExpType = null;
	protected static InstConcept metaMetaInstConcept = null;
	protected static InstElement metaMetaPairwiseRelation = null;
	protected static InstConcept metaMetaInstOverTwoRel = null;
	protected static InstElement infraMetaMetaConcept = null;
	protected static InstElement infraMetaMetaAttribute = null;
	protected static InstElement infraMetaMetaCollection = null;
	protected static InstElement infraMetaMetaPairwiseRelation = null;
	protected static InstElement infraMetaMetaOverTwoRelation = null;
	protected static InstPairwiseRel metaPairwRelCCExt = null;
	protected static InstPairwiseRel metaPairwRelOCExt = null;
	protected static InstPairwiseRel metaPairwRelAso = null;

	protected static OpersConcept generalModel = new OpersConcept(
			"GeneralModel");
	protected static InstConcept instGeneralModel = null;

	protected static InstConcept instVertexGE = null;
	protected static InstConcept instVertexSG = null;
	protected static InstConcept instVertexF = null;
	protected static InstConcept instVertexHC = null;

	public static void createOpersMetaModel(InstanceModel refas, boolean empty) {
		createOpersMetaModelOpers(refas, empty, true);
		createSemanticNmMetaModel(refas, empty);
		if (!empty) {
			createGeneralMetaModel(refas);
			createFeatureMetaModel(refas);
			DefaultRefasMM.createREFASMetaModel(refas);
			DefaultRefasMM.createREFASMetaConcept(refas);
			DefaultRefasMM.createRefasAssets(refas);
		}
	}

	@SuppressWarnings("unchecked")
	private static void createOpersMetaModelOpers(InstanceModel refas,
			boolean empty, boolean newOpers) {
		metaMetaModel = (refas.getSyntaxModel().getVertex("SeMModel"));
		metaOperationMenu = (refas.getSyntaxModel().getVertex("OpMOperGroup"));
		metaOperationAction = (refas.getSyntaxModel().getVertex("OpMOperation"));
		metaOperationSubAction = (refas.getSyntaxModel()
				.getVertex("OpMSubOper"));
		metaLabeling = (refas.getSyntaxModel().getVertex("OpMLabeling"));
		metaExpType = (refas.getSyntaxModel().getVertex("OpMExpType"));

		// MetaEnumeration metaEnumeration = (MetaEnumeration) ((InstConcept)
		// refas
		// .getSyntaxModel().getVertex("TypeEnumeration"))
		// .getEditableMetaElement();
		metaMetaInstConcept = ((InstConcept) refas.getSyntaxModel().getVertex(
				"SeMConcept"));
		metaMetaPairwiseRelation = (refas.getSyntaxModel()
				.getVertex("SeMPWRel"));
		metaMetaInstOverTwoRel = ((InstConcept) refas.getSyntaxModel()
				.getVertex("SeMOTRel"));

		infraMetaMetaConcept = (refas.getSyntaxModel()
				.getVertex("SeMnmConcept"));
		infraMetaMetaAttribute = (refas.getSyntaxModel()
				.getVertex("SeMAttribute"));
		infraMetaMetaCollection = (refas.getSyntaxModel()
				.getVertex("SeMCollection"));
		infraMetaMetaPairwiseRelation = (refas.getSyntaxModel()
				.getVertex("SeMnmPWRel"));
		infraMetaMetaOverTwoRelation = (refas.getSyntaxModel()
				.getVertex("SeMnmOTRel"));

		metaPairwRelCCExt = (refas.getSyntaxModel()
				.getConstraintInstEdge("SeMExtCEdge"));
		metaPairwRelOCExt = (refas.getSyntaxModel()

		.getConstraintInstEdge("SeMExtOTCEdge")); // FIXME separate OT
													// from OT and OT
													// from C.
													// OMExtOTOTEdge

		metaPairwRelAso = (refas.getSyntaxModel()
				.getConstraintInstEdge("SeMAsoEdge"));

		/*
		 * attribute = new ElemAttribute("TotalOpt", "Integer",
		 * AttributeType.EXECCURRENTSTATE, false, "***TotalOpt***", 0, new
		 * RangeDomain(0, 2000), 2, -1, "", "", -1, "", "");
		 * simulationExecOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		 * dynamicBehaviorDTO.getRefasModel().getIdentifier(), attribute.getName(), true));
		 * dynamicBehaviorDTO.getRefasModel().putSemanticAttribute("TotalOpt", attribute);
		 * 
		 * attribute = new ElemAttribute("TotalSG", "Integer",
		 * AttributeType.EXECCURRENTSTATE, false, "***TotalSG***", 0, new
		 * RangeDomain(0, 2000), 2, -1, "", "", -1, "", "");
		 * simsceExecOperLabeling2.addAttribute(new OpersIOAttribute(dynamicBehaviorDTO.getRefasModel()
		 * .getIdentifier(), attribute.getName(), true));
		 * dynamicBehaviorDTO.getRefasModel().putSemanticAttribute("TotalSG", attribute);
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
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
			//Luisa: ISSUE #245 HOT FIX
			if (newOpers) {
				instOperationGroup.getInstAttribute("visible").setValue(true);
			}else {
				instOperationGroup.getInstAttribute("visible").setValue(false);
			}
				
			instOperationGroup.getInstAttribute("menuType").setValue("4");
			
			instOperationGroup.getInstAttribute("opgname").setValue(
					"Basic Simulation (Dynamic)");
			instOperationGroup.getInstAttribute("shortcut").setValue("S");
			instOperationGroup.getInstAttribute("index").setValue(1);

			simulOper = new OpersConcept("BasicSimulOper");

			InstConcept instOperationAction = new InstConcept("BasicSimulOper",
					metaOperationAction, simulOper);
			refas.getVariabilityVertex().put("BasicSimulOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Validation.toString());
			instOperationAction.getInstAttribute("opname").setValue(
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
			// //instOperationSubAction.getInstAttribute("iteration")
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
					StringUtils.formatEnumValue(OpersSubOpType.First_Solution
							.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
			 * ExpressionVertexType.LEFTITERCONFIXEDVARIABLE, instdynamicBehaviorDTO.getRefasModel(),
			 * "TotalOrder", 0);
			 * 
			 * semanticExpressions.add(t1);
			 * 
			 * t1 = new SemanticExpression("sub",
			 * refas.getSemanticExpressionTypes() .get("Sum"),
			 * ExpressionVertexType.LEFTITERCONFIXEDVARIABLE, instdynamicBehaviorDTO.getRefasModel(),
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
					StringUtils
							.formatEnumValue(OpersSubOpType.Iterate_Solutions
									.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					true);
			// instOperationSubAction.getInstAttribute("iteration").setValue(true);
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
					""), LabelingOrderEnum.MIN));
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
			// //instOperationSubAction.getInstAttribute("iteration")
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
			// //instOperationSubAction.getInstAttribute("iteration")
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
			//Luisa: ISSUE #245 HOT FIX: We hide this menu  while we separate functionalities according to the notation where they might be used
			if (newOpers) {
				instOperationGroup.getInstAttribute("visible").setValue(true);
			}else {
				instOperationGroup.getInstAttribute("visible").setValue(false);
			}
			instOperationGroup.getInstAttribute("menuType").setValue("4");
			instOperationGroup.getInstAttribute("opgname").setValue(
					"Simulation Scenarios  (Dynamic)");
			instOperationGroup.getInstAttribute("shortcut").setValue("C");
			instOperationGroup.getInstAttribute("index").setValue(1);

			simulScenOper = new OpersConcept("SceSimulOper");

			instOperationAction = new InstConcept("SceSimulOper",
					metaOperationAction, simulScenOper);
			refas.getVariabilityVertex().put("SceSimulOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Validation.toString());
//			instOperationAction.getInstAttribute("opname").setValue(
//					"Start Simulation (Dynamic)");
			//Luisa: ISSUE #245 HOT FIX
			instOperationAction.getInstAttribute("opname").setValue(
					"Start Simulation");
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
			// //instOperationSubAction.getInstAttribute("iteration")
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
			// //instOperationSubAction.getInstAttribute("iteration")
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
					StringUtils
							.formatEnumValue(OpersSubOpType.Iterate_Solutions
									.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					true);
			// instOperationSubAction.getInstAttribute("iteration").setValue(true);
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
					""), LabelingOrderEnum.MAX));

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
			// //instOperationSubAction.getInstAttribute("iteration")
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
			// //instOperationSubAction.getInstAttribute("iteration")
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
			instOperationGroup.getInstAttribute("opgname").setValue(
					"SAS Verification");
			instOperationGroup.getInstAttribute("shortcut").setValue("A");

			// ---
			sasverSDCoreOper = new OpersConcept("SDCoreOper");

			instOperationAction = new InstConcept("SDCoreOper",
					metaOperationAction, sasverSDCoreOper);
			refas.getVariabilityVertex().put("SDCoreOper", instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
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
			instOperationSubAction
					.getInstAttribute("type")
					.setValue(
							StringUtils
									.formatEnumValue(OpersSubOpType.UpdModel_Defects_Verif
											.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getFalseOptionalElements.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outSd");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify SoftDeps Always Active");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(2);

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
					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getFalseOptionalElements.toString());

			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("Identify SoftDeps on the Model");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outSd");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify SoftDeps Never Allowed");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(4);

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
					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getDeadElements.toString());

			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("Identify SoftDeps on the Model");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outSd");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
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
			instOperationSubAction
					.getInstAttribute("type")
					.setValue(
							StringUtils
									.formatEnumValue(OpersSubOpType.UpdModel_Defects_Verif
											.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getFalseOptionalElements.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outCl");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify Claims Always Active");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(6);

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
					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getFalseOptionalElements.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("Identify Claims on the Model");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outCl");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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

			sasverClallOperUniqLab = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("cl-all-lab", metaLabeling,
					sasverClallOperUniqLab);

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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify Claims Never Allowed");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(8);

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
					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getDeadElements.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("Identify Claims on the Model");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outCl");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
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
			instOperationSubAction
					.getInstAttribute("type")
					.setValue(

							StringUtils
									.formatEnumValue(OpersSubOpType.UpdModel_Defects_Verif
											.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getFalseOptionalElements.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
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

					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getDeadElements.toString());

			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("Identify Operat. on the Model");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify Loops in Struct. Rels");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("visible").setValue(false);
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(10);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("sasver-no-loops", instEdgeOper);
			instEdgeOper.setIdentifier("sasver-no-loops");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			sasverNoLoopsOperationSubActionMV = new OpersSubOperation(1,
					"NoLoopsStructRelSubOper");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("NoLoopsStructRelSubOper",
					metaOperationSubAction, sasverNoLoopsOperationSubActionMV);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("useNatLangExprDesc")
					.setValue(true);

			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorHint")
					.setValue(
							"This concept has incoming/outgoing relations that "
									+ "creates an structural loop or double structures.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review the structural relations marked with error. #number# relations are "
									+ "involved in structural loops.");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(

					StringUtils
							.formatEnumValue(OpersSubOpType.Multi_Verification
									.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"pOutAnaSel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("NoLoopsStructRelSubOper",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("no-loops", instEdgeOper);
			instEdgeOper.setIdentifier("no-loops");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverNoLoopsOperSubActionMVRelaxable = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverNoLoopsOperSubActionMVRelaxable);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("RELAXABLE");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverNoLoopsOperSubActionMVNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverNoLoopsOperSubActionMVNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverNoLoopsOperMVUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("no-loops-lab", metaLabeling,
					sasverNoLoopsOperMVUniqueLabeling);

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

			sasverNoLoopsOperationSubActionRed = new OpersSubOperation(1,
					"NoLoopsStructRelSubOperRed");
			// updateCoreOperationAction
			// .addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept(
					"NoLoopsStructRelSubOperRed", metaOperationSubAction,
					sasverNoLoopsOperationSubActionRed);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verify Error");
			instOperationSubAction
					.getInstAttribute("errorHint")
					.setValue(
							"This concepts has incoming/outoging relations that"
									+ " creates an structural loop or double structures.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review the structual relations marked with error."
									+ " #number# relations are involved in structural loops.");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent or the operation has "
									+ "errors. \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was partially updated.");
			instOperationSubAction.getInstAttribute("type").setValue(

					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getRedundancies.toString());
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"pOutAnaSel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);

			refas.getVariabilityVertex().put("NoLoopsStructRelSubOperRed",
					instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("no-loopsRed", instEdgeOper);
			instEdgeOper.setIdentifier("no-loopsRed");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			sasverNoLoopsOperSubActionRedToVerify = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverNoLoopsOperSubActionRedToVerify);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("TOVERIFY");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverNoLoopsOperSubActionRedNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, sasverNoLoopsOperSubActionRedNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			sasverNoLoopsOperRedUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("no-loopsRed-lab", metaLabeling,
					sasverNoLoopsOperRedUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("no-loopsRed-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("no-loopsRed-lab-pw",
					instEdgeOper);
			instEdgeOper.setIdentifier("no-loopsRed-lab-pw");
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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify SG Contribs with Conflict");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(12);

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

					StringUtils
							.formatEnumValue(OpersSubOpType.Multi_Verification
									.toString()));
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
			// instOperationSubAction.getInstAttribute("iteration")
			// //.setValue(false);
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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify Claims with Conflicts");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(14);

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

					StringUtils
							.formatEnumValue(OpersSubOpType.Multi_Verification
									.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outConflClSD");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify Claims & SoftDeps with Conflicts");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("visible").setValue(false);
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(16);

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

					StringUtils
							.formatEnumValue(OpersSubOpType.Multi_Verification
									.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outConflClSD");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify SoftDeps with Conflicts");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(18);

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

					StringUtils
							.formatEnumValue(OpersSubOpType.Multi_Verification
									.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"outConflClSD");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
			instOperationGroup.getInstAttribute("opgname").setValue(
					"General Verification");
			instOperationGroup.getInstAttribute("shortcut").setValue("V");

			updCoreOper = new OpersConcept("UpdateCoreOper");

			instOperationAction = new InstConcept("UpdateCoreOper",
					metaOperationAction, updCoreOper);
			refas.getVariabilityVertex().put("UpdateCoreOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Update Core Elements");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(1);

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
			instOperationSubAction
					.getInstAttribute("type")
					.setValue(
							StringUtils
									.formatEnumValue(OpersSubOpType.UpdModel_Defects_Verif
											.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getFalseOptionalElements.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Core");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(true);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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

			// Variant Features variantOper

			variantOper = new OpersConcept("VariantOper");

			instOperationAction = new InstConcept("VariantOper",
					metaOperationAction, variantOper);
			if (newOpers)
				refas.getVariabilityVertex().put("VariantOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify Variant Features");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(2);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-menu-Variant",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-Variant");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			variantSubOperationAction = new OpersSubOperation(1,
					"VariantSubOper");
			// updateCoreOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("VariantSubOper",
					metaOperationSubAction, variantSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Variant Features result");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction
					.getInstAttribute("type")
					.setValue(
							StringUtils
									.formatEnumValue(OpersSubOpType.UpdModel_Defects_Verif
											.toString()));
			instOperationSubAction.getInstAttribute("errorHint").setValue(
					"Variant Features");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue(" Variant features identified in gray.");

			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getFalseOptionalElements.toString());

			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Var");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(true);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("index").setValue(1);
			if (newOpers)
				refas.getVariabilityVertex().put("VariantSubOper",
						instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("upd-variant", instEdgeOper);
			instEdgeOper.setIdentifier("upd-variant");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			variantOptSubOperNormal = new OpersSubOperationExpType();
			variantOptSubOperNormal.setIdentifier("NORMAL");

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, variantOptSubOperNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			variantOperUniqueLabeling = new OpersLabeling("unique");

			// operationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("Upd-variant-lab", metaLabeling,
					variantOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Upd-variant-lab",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("upd-variant-lab-pw",
						instEdgeOper);
			instEdgeOper.setIdentifier("upd-variant-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Dead elements

			verifDeadElemOper = new OpersConcept("IdentifyDeadOper");

			instOperationAction = new InstConcept("IdentifyDeadOper",
					metaOperationAction, verifDeadElemOper);
			refas.getVariabilityVertex().put("IdentifyDeadOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify Dead Elements");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(3);

			instEdgeOper = new InstPairwiseRel();
			refas.getConstraintInstEdges().put("ver-menu-dead", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-dead");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			verifDeadElemSubOperationAction = new OpersSubOperation(1,
					"IdentifyDeadSubOper");

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
					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getDeadElements.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("Update Core Elements");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);

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

			// False optional

			verifFalseOptOper = new OpersConcept("IdentifyFalseOper");
			instOperationAction = new InstConcept("IdentifyFalseOper",
					metaOperationAction, verifFalseOptOper);
			refas.getVariabilityVertex().put("IdentifyFalseOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
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
					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getFalseOptionalElements.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("Update Core Elements");
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);

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

			instLabeling = new InstConcept("Ver-false-lab", metaLabeling,
					verifFalseOptElemOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);

			refas.getVariabilityVertex().put("Ver-false-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();

			refas.getConstraintInstEdges()
					.put("ver-false-lab-pw", instEdgeOper);
			instEdgeOper.setIdentifier("ver-false-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Wrong Cardinalities

			wrongCardOptOper = new OpersConcept("WrongcardOper");

			instOperationAction = new InstConcept("WrongcardOper",
					metaOperationAction, wrongCardOptOper);
			if (newOpers)
				refas.getVariabilityVertex().put("WrongcardOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Verify Wrong Cardinalities");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-menu-Wrongcard",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-Wrongcard");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			wrongCardSubOperationAction = new OpersSubOperation(1,
					"WrongcardSubOper");

			instOperationSubAction = new InstConcept("WrongcardSubOper",
					metaOperationSubAction, wrongCardSubOperationAction);
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
					"This over two relation has wrong cardinalities.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"#number# relations with wrong cardinalities.\n Please review the relations with error marks.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils
							.formatEnumValue(OpersSubOpType.Multi_Verification
									.toString()));
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"OSel");
			instOperationSubAction.getInstAttribute("indivRelExp").setValue(
					true);
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("useNatLangExprDesc")
					.setValue(true);

			if (newOpers)
				refas.getVariabilityVertex().put("WrongcardSubOper",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-wrongcard",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-wrongcard");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			wrongCardOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, wrongCardOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			wrongCardOperSubActionRelaxable = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, wrongCardOperSubActionRelaxable);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("RELAXABLE");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			instLabeling = new InstConcept("Ver-wrongcard-lab", metaLabeling,
					wrongCardElemOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Ver-wrongcard-lab",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-wrongcard-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-wrongcard-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Redundancies

			redundanOptOper = new OpersConcept("RedundOper");

			instOperationAction = new InstConcept("RedundOper",
					metaOperationAction, redundanOptOper);
			if (newOpers)
				refas.getVariabilityVertex().put("RedundOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Verify Redundancies");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-menu-Redund",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-Redund");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			redundanSubOperationAction = new OpersSubOperation(1,
					"RedundSubOper");

			instOperationSubAction = new InstConcept("RedundSubOper",
					metaOperationSubAction, redundanSubOperationAction);
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
					"This element has redundant relations.");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"#number# redundancies identified.\n Please keep only one root concept.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getRedundancies.toString());
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("RedundSubOper",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-redund", instEdgeOper);
			instEdgeOper.setIdentifier("ver-redund");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			redundanOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, redundanOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			redundanOperSubActionToVerify = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, redundanOperSubActionToVerify);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("TOVERIFY");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			// instLabeling = new InstConcept("Ver-redund-lab", metaLabeling,
			// redundanOperUniqueLabeling);
			//
			// instLabeling.getInstAttribute("labelId").setValue("L1");
			// instLabeling.getInstAttribute("position").setValue(1);
			// instLabeling.getInstAttribute("once").setValue(false);
			// instLabeling.getInstAttribute("order").setValue(false);
			// if (newOpers)
			// refas.getVariabilityVertex()
			// .put("Ver-redund-lab", instLabeling);
			//
			// instEdgeOper = new InstPairwiseRel();
			// refas.getConstraintInstEdges().put("ver-redund-lab-pw",
			// instEdgeOper);
			// instEdgeOper.setIdentifier("ver-redund-lab-pw");
			// instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			// instEdgeOper.setTargetRelation(instLabeling, true);
			// instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// False PL

			verifFalsePLOper = new OpersConcept("IdentifyFalsePL");
			instOperationAction = new InstConcept("IdentifyFalsePL",
					metaOperationAction, verifFalsePLOper);
			if (newOpers)
				refas.getVariabilityVertex().put("IdentifyFalsePL",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify False Product Line");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-menu-falsepl",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-falsepl");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			verifFalsePLSubOperationAction = new OpersSubOperation(1,
					"IdentifyFalsePLSubOper");
			instOperationSubAction = new InstConcept("IdentifyFalsePLSubOper",
					metaOperationSubAction, verifFalsePLSubOperationAction);
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
					"This is a unique feature");
			instOperationSubAction
					.getInstAttribute("errorMsg")
					.setValue(
							"Please review the model relations. This is a false product line model.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getFalsePLs.toString());
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("IdentifyFalsePLSubOper",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);
			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-falsepl", instEdgeOper);
			instEdgeOper.setIdentifier("ver-falsepl");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			verifFalsePLOperSubActionNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifFalsePLOperSubActionNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			verifFalsePLOperSubActionToVerify = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, verifFalsePLOperSubActionToVerify);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("TOVERIFY");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			instLabeling = new InstConcept("Ver-falsepl-lab", metaLabeling,
					verifFalsePLElemOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Ver-falsple-lab",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-falsepl-lab-pw",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-falsepl-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// ----------------------------------------------------------

			operationMenu = new OpersConcept("FMVerification");

			instOperationGroup = new InstConcept("FMVerification",
					metaOperationMenu, operationMenu);
			refas.getVariabilityVertex().put("FMVerification",
					instOperationGroup);

			instOperationGroup.getInstAttribute("visible").setValue(true);
			instOperationGroup.getInstAttribute("menuType").setValue("2");
			instOperationGroup.getInstAttribute("opgname").setValue(
					"FM Analysis");
			instOperationGroup.getInstAttribute("shortcut").setValue("V");

			// Void model

			voidModelOper = new OpersConcept("VoidModelOper");

			instOperationAction = new InstConcept("VoidModelOper",
					metaOperationAction, voidModelOper);
			if (newOpers)
				refas.getVariabilityVertex().put("VoidModelOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Verify Void Model");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("voidmodel-menu",
						instEdgeOper);
			instEdgeOper.setIdentifier("voidmodel-menu");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			voidModelSubOperationAction = new OpersSubOperation(3,
					"VoidModelSubOper");

			instOperationSubAction = new InstConcept("VoidModelSubOper",
					metaOperationSubAction, voidModelSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction.getInstAttribute("errorText").setValue(
					"The model evaluated is void");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.First_Solution
							.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("The model is not void");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("index").setValue(3);
			if (newOpers)
				refas.getVariabilityVertex().put("VoidModelSubOper",
						instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("voidmodel-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("voidmodel-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			voidModelSubOperNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, voidModelSubOperNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			instLabeling = new InstConcept("VoidModel-lab", metaLabeling,
					voidModelOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("VoidModel-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("VoidModel-lab-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("VoidModel-lab-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// All Products

			allProductsOper = new OpersConcept("AllProductsOper");

			instOperationAction = new InstConcept("AllProductsOper",
					metaOperationAction, allProductsOper);
			if (newOpers)
				refas.getVariabilityVertex().put("AllProductsOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Export.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Export All Products");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(4);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("AllProducts-menu",
						instEdgeOper);
			instEdgeOper.setIdentifier("AllProducts-menu");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			allProductsSubOperationAction = new OpersSubOperation(3,
					"AllProductsSubOper");

			instOperationSubAction = new InstConcept("AllProductsSubOper",
					metaOperationSubAction, allProductsSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction.getInstAttribute("errorText").setValue(
					"Error found exporting the products");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.Export_Solutions
							.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("Solutions exported successfully");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("index").setValue(3);
			if (newOpers)
				refas.getVariabilityVertex().put("AllProductsSubOper",
						instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("AllProducts-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("AllProducts-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			allProductsSubOperNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, allProductsSubOperNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			// simulOperationSubAction.addOperationLabeling(operationLabeling);

			instLabeling = new InstConcept("AllProducts-lab", metaLabeling,
					allProductsOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("AllProducts-lab",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("AllProducts-lab-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("AllProducts-lab-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Number of Products

			numProductsOper = new OpersConcept("NumProductsOper");

			instOperationAction = new InstConcept("NumProductsOper",
					metaOperationAction, numProductsOper);
			if (newOpers)
				refas.getVariabilityVertex().put("NumProductsOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Number of Products");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("NumProducts-menu",
						instEdgeOper);
			instEdgeOper.setIdentifier("NumProducts-menu");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			numProductsSubOperationAction = new OpersSubOperation(3,
					"NumProductsSubOper");
			// simulScenOperationAction.addExpressionSubAction(operationSubAction);

			instOperationSubAction = new InstConcept("NumProductsSubOper",
					metaOperationSubAction, numProductsSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction.getInstAttribute("errorText").setValue(
					"Error found counting the products");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.Number_Solutions
							.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("#number# products from the FM");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("index").setValue(3);
			if (newOpers)
				refas.getVariabilityVertex().put("NumProductsSubOper",
						instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("NumProducts-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("NumProducts-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			numProductsSubOperNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, numProductsSubOperNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			instLabeling = new InstConcept("NumProducts-lab", metaLabeling,
					numProductsOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("NumProducts-lab",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("NumProducts-lab-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("NumProducts-lab-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Homogeneity

			homogeneityOper = new OpersConcept("HomogeneityOper");

			instOperationAction = new InstConcept("HomogeneityOper",
					metaOperationAction, homogeneityOper);
			if (newOpers)
				refas.getVariabilityVertex().put("HomogeneityOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					StringUtils
							.formatEnumValue(OpersOpType.Computational_Analysis
									.toString()));
			instOperationAction.getInstAttribute("compType").setValue(
					"One less quotient");
			instOperationAction.getInstAttribute("opname").setValue(
					"Calculate Homogeneity");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("visible").setValue(true);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(24);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-menu-homog",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-homog");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			homogeneitySubOperationAction1 = new OpersSubOperation(1,
					"HomogeneitySubOper1");

			instOperationSubAction = new InstConcept("HomogeneitySubOper1",
					metaOperationSubAction, homogeneitySubOperationAction1);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getFalsePLs.toString());
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("HomogeneitySubOper1",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-homog1", instEdgeOper);
			instEdgeOper.setIdentifier("ver-homog1");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			homogeneityOperSubActionNormal1 = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, homogeneityOperSubActionNormal1);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			homogeneityOperSubActionToVerify1 = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, homogeneityOperSubActionToVerify1);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("TOVERIFY");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			homogoneityOperUniqueLabeling1 = new OpersLabeling("unique");

			instLabeling = new InstConcept("Ver-homog-lab1", metaLabeling,
					homogoneityOperUniqueLabeling1);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex()
						.put("Ver-homog-lab1", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-homog-lab1",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-homog-lab1");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			homogeneitySubOperationAction2 = new OpersSubOperation(1,
					"HomogeneitySubOper2");

			instOperationSubAction = new InstConcept("HomogeneitySubOper2",
					metaOperationSubAction, homogeneitySubOperationAction2);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Calculation Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.Number_Solutions
							.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue(
							"#numerator# unique features. #denominator# "
									+ "total products. Thus, the homogeneity"
									+ " of this model is #result#");
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"DSel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("HomogeneitySubOper2",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-homog2", instEdgeOper);
			instEdgeOper.setIdentifier("ver-homog2");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			homogeneityOperSubActionNormal2 = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, homogeneityOperSubActionNormal2);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			homogoneityOperUniqueLabeling2 = new OpersLabeling("unique");

			instLabeling = new InstConcept("Ver-homog-lab2", metaLabeling,
					homogoneityOperUniqueLabeling2);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex()
						.put("Ver-homog-lab2", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-homog-lab2",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-homog-lab2");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Variability factor

			variabfactorOper = new OpersConcept("VariabFactorOper");

			instOperationAction = new InstConcept("VariabFactorOper",
					metaOperationAction, variabfactorOper);
			if (newOpers)
				refas.getVariabilityVertex().put("VariabFactorOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					StringUtils
							.formatEnumValue(OpersOpType.Computational_Analysis
									.toString()));
			instOperationAction.getInstAttribute("compType").setValue(
					"Quotient denominator exp base 2");
			instOperationAction.getInstAttribute("opname").setValue(
					"Calculate the Variability Factor");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("visible").setValue(true);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(26);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-menu-VariabFactor",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-VariabFactor");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			variabfactorSubOperationAction1 = new OpersSubOperation(1,
					"VariabFactorSubOper1");

			instOperationSubAction = new InstConcept("VariabFactorSubOper1",
					metaOperationSubAction, variabfactorSubOperationAction1);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.Number_Solutions
							.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"NSel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("VariabFactorSubOper1",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-VariabFactor1",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-VariabFactor1");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			variabfactorOperSubActionNormal1 = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, variabfactorOperSubActionNormal1);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			variabfactorOperUniqueLabeling1 = new OpersLabeling("unique");

			instLabeling = new InstConcept("Ver-VariabFactor-lab1",
					metaLabeling, variabfactorOperUniqueLabeling1);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Ver-VariabFactor-lab1",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-VariabFactor-lab1",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-VariabFactor-lab1");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			variabfactorSubOperationAction2 = new OpersSubOperation(1,
					"VariabFactorSubOper2");

			instOperationSubAction = new InstConcept("VariabFactorSubOper2",
					metaOperationSubAction, variabfactorSubOperationAction2);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Calculation Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.First_Solution
							.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("completedMessage")
					.setValue(
							"#numerator# total products. #denominator# "
									+ "features. The varibility factor of this model is #result#");
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"totalAnaSel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("VariabFactorSubOper2",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(2);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-VariabFactor2",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-VariabFactor2");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			variabfactorOperSubActionNormal2 = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, variabfactorOperSubActionNormal2);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			variabfactorOperUniqueLabeling2 = new OpersLabeling("unique");

			instLabeling = new InstConcept("Ver-VariabFactor-lab2",
					metaLabeling, variabfactorOperUniqueLabeling2);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Ver-VariabFactor-lab2",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-VariabFactor-lab2",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-VariabFactor-lab2");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Degree of orthogonality

			degreeOrthoOper = new OpersConcept("degreeOrthoOper");

			instOperationAction = new InstConcept("degreeOrthoOper",
					metaOperationAction, degreeOrthoOper);
			if (newOpers)
				refas.getVariabilityVertex().put("degreeOrthoOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					StringUtils
							.formatEnumValue(OpersOpType.Computational_Analysis
									.toString()));
			instOperationAction.getInstAttribute("compType").setValue(
					"Simple quotient");
			instOperationAction
					.getInstAttribute("opname")
					.setValue(
							"Calculate the degree of orthogonality (requires a selected feature)");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("visible").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(27);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-menu-degreeOrtho",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-degreeOrtho");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			degreeOrthoSubOperationAction1 = new OpersSubOperation(1,
					"degreeOrthoSubOper1");

			instOperationSubAction = new InstConcept("degreeOrthoSubOper1",
					metaOperationSubAction, degreeOrthoSubOperationAction1);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.Number_Solutions
							.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"NSel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("degreeOrthoSubOper1",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-degreeOrtho1",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-degreeOrtho1");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			degreeOrthoOperSubActionNormal1 = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, degreeOrthoOperSubActionNormal1);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			degreeOrthoOperUniqueLabeling1 = new OpersLabeling("unique");

			instLabeling = new InstConcept("Ver-degreeOrtho-lab1",
					metaLabeling, degreeOrthoOperUniqueLabeling1);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Ver-degreeOrtho-lab1",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-degreeOrtho-lab1",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-degreeOrtho-lab1");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			degreeOrthoSubOperationAction2 = new OpersSubOperation(1,
					"degreeOrthoSubOper2");

			instOperationSubAction = new InstConcept("degreeOrthoSubOper2",
					metaOperationSubAction, degreeOrthoSubOperationAction2);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Calculation Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.Number_Solutions
							.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("completedMessage")
					.setValue(
							"#numerator# total products. #denominator# "
									+ "product of the subtree. The degree of"
									+ " orthogonality of this model considering"
									+ " the selected feature is #result#");
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"DSel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("degreeOrthoSubOper2",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-degreeOrtho2",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-degreeOrtho2");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			degreeOrthoOperSubActionNormal2 = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, degreeOrthoOperSubActionNormal2);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			degreeOrthoOperUniqueLabeling2 = new OpersLabeling("unique");

			instLabeling = new InstConcept("Ver-degreeOrtho-lab2",
					metaLabeling, degreeOrthoOperUniqueLabeling2);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Ver-degreeOrtho-lab2",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-degreeOrtho-lab2",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-degreeOrtho-lab2");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Extra Constraint representativeness

			ecrOper = new OpersConcept("ECROper");

			instOperationAction = new InstConcept("ECROper",
					metaOperationAction, ecrOper);
			if (newOpers)
				refas.getVariabilityVertex()
						.put("ECROper", instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					StringUtils
							.formatEnumValue(OpersOpType.Computational_Analysis
									.toString()));
			instOperationAction.getInstAttribute("compType").setValue(
					"Simple quotient");
			instOperationAction.getInstAttribute("opname").setValue(
					"Calculate Extra Constraint representativeness (ECR)");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("visible").setValue(true);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges()
						.put("ver-menu-ECR", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-ECR");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			ecrSubOperationAction1 = new OpersSubOperation(1, "ECRSubOper1");

			instOperationSubAction = new InstConcept("ECRSubOper1",
					metaOperationSubAction, ecrSubOperationAction1);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.First_Solution
							.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"totalAnaSel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("ECRSubOper1",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-ECR1", instEdgeOper);
			instEdgeOper.setIdentifier("ver-ECR1");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			ecrOperSubActionNormal1 = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, ecrOperSubActionNormal1);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			ecrOperUniqueLabeling1 = new OpersLabeling("unique");

			instLabeling = new InstConcept("Ver-ECR-lab1", metaLabeling,
					ecrOperUniqueLabeling1);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Ver-ECR-lab1", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges()
						.put("ver-ECR-lab1", instEdgeOper);
			instEdgeOper.setIdentifier("ver-ECR-lab1");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			ecrSubOperationAction2 = new OpersSubOperation(1, "ECRSubOper2");

			instOperationSubAction = new InstConcept("ECRSubOper2",
					metaOperationSubAction, ecrSubOperationAction2);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Calculation Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.First_Solution
							.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("completedMessage")
					.setValue(
							"#numerator# features in cross tree relations. #denominator# "
									+ "total features. The ECR of this model is #result#");
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"totalAnaSel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("ECRSubOper2",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-ECR2", instEdgeOper);
			instEdgeOper.setIdentifier("ver-ECR2");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			ecrOperSubActionNormal2 = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, ecrOperSubActionNormal2);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			ecrOperUniqueLabeling2 = new OpersLabeling("unique");

			instLabeling = new InstConcept("Ver-ECR-lab2", metaLabeling,
					ecrOperUniqueLabeling2);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Ver-ECR-lab2", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges()
						.put("ver-ECR-lab2", instEdgeOper);
			instEdgeOper.setIdentifier("ver-ECR-lab2");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Lowest Common Ancestor

			lcaOper = new OpersConcept("LCAOper");

			instOperationAction = new InstConcept("LCAOper",
					metaOperationAction, lcaOper);
			if (newOpers)
				refas.getVariabilityVertex()
						.put("LCAOper", instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identiy Lowest Common Ancestor");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("lca-menu", instEdgeOper);
			instEdgeOper.setIdentifier("lca-menu");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			lcaSubOperationAction = new OpersSubOperation(3, "LCASubOper");

			instOperationSubAction = new InstConcept("LCASubOper",
					metaOperationSubAction, lcaSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction.getInstAttribute("errorText").setValue(
					"Error identifing the LCA");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.First_Solution
							.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("index").setValue(3);
			if (newOpers)
				refas.getVariabilityVertex().put("LCASubOper",
						instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("lca-rel", instEdgeOper);
			instEdgeOper.setIdentifier("lca-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			lcaSubOperNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, lcaSubOperNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			instLabeling = new InstConcept("lca-lab", metaLabeling,
					lcaOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("lca-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("lca-lab-rel", instEdgeOper);
			instEdgeOper.setIdentifier("lca-lab-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Root Features

			rootOper = new OpersConcept("RootOper");

			instOperationAction = new InstConcept("RootOper",
					metaOperationAction, rootOper);
			if (newOpers)
				refas.getVariabilityVertex().put("RootOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Direct Root Feature");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(30);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("Root-menu", instEdgeOper);
			instEdgeOper.setIdentifier("Root-menu");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			rootSubOperationAction = new OpersSubOperation(3, "RootSubOper");

			instOperationSubAction = new InstConcept("RootSubOper",
					metaOperationSubAction, rootSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction.getInstAttribute("errorText").setValue(
					"The execution encountered an error");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.First_Solution
							.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("index").setValue(3);
			if (newOpers)
				refas.getVariabilityVertex().put("RootSubOper",
						instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("Root-rel", instEdgeOper);
			instEdgeOper.setIdentifier("Root-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			rootSubOperNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, rootSubOperNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			instLabeling = new InstConcept("Root-lab", metaLabeling,
					rootOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Root-lab", instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges()
						.put("Root-lab-rel", instEdgeOper);
			instEdgeOper.setIdentifier("Root-lab-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Old operations for FM

			verifParentsOper = new OpersConcept("IdentifyWithoutParentsOper");

			instOperationAction = new InstConcept("IdentifyWithoutParentsOper",
					metaOperationAction, verifParentsOper);

			refas.getVariabilityVertex().put("IdentifyWithoutParentsOper",
					instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify Elements Without Parents");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("visible").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(31);

			instEdgeOper = new InstPairwiseRel();

			refas.getConstraintInstEdges().put("ver-menu-pare", instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-pare");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			instEdgeOper.setSourceRelation(instOperationGroup, true);

			verifParentsSubOperationAction = new OpersSubOperation(1,
					"IdentifyWithoutParentsSubOper");

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
							"#number# concepts without a parent or with more than one"
									+ " parent identified.\n Please add/remove appropiated relations.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getRedundancies.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("Update Core Elements");
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
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
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Verify a Single Root");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(32);

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
					StringUtils
							.formatEnumValue(OpersSubOpType.Multi_Verification
									.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);

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

			// ----------------------------------------------------------

			operationMenu = new OpersConcept("FMConfVerif");

			instOperationGroup = new InstConcept("FMConfVerif",
					metaOperationMenu, operationMenu);
			if (newOpers)
				refas.getVariabilityVertex().put("FMConfVerif",
						instOperationGroup);

			instOperationGroup.getInstAttribute("visible").setValue(true);
			instOperationGroup.getInstAttribute("menuType").setValue("4");
			instOperationGroup.getInstAttribute("opgname").setValue(
					"FM Analysis");
			instOperationGroup.getInstAttribute("shortcut").setValue("V");

			// Valid Product

			validProductElemOper = new OpersConcept("ValidProductOper");

			instOperationAction = new InstConcept("ValidProductOper",
					metaOperationAction, validProductElemOper);
			if (newOpers)
				refas.getVariabilityVertex().put("ValidProductOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Verify Valid Product");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(2);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("validProduct-menu",
						instEdgeOper);
			instEdgeOper.setIdentifier("validProduct-menu");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			validProductSubOperationAction = new OpersSubOperation(3,
					"ValidProductSubOper");

			instOperationSubAction = new InstConcept("ValidProductSubOper",
					metaOperationSubAction, validProductSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction.getInstAttribute("errorText").setValue(
					"The product evaluated is not complete");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.First_Solution
							.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("The product is valid");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("index").setValue(3);
			if (newOpers)
				refas.getVariabilityVertex().put("ValidProductSubOper",
						instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("validProduct-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("validProduct-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			validProductSubOperNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, validProductSubOperNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			instLabeling = new InstConcept("ValidProduct-lab", metaLabeling,
					validProductOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("ValidProduct-lab",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ValidProduct-lab-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("ValidProduct-lab-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Valid Partial Configuration

			validPartialConfOper = new OpersConcept("ValidPartialConfOper");

			instOperationAction = new InstConcept("ValidPartialConfOper",
					metaOperationAction, validPartialConfOper);
			if (newOpers)
				refas.getVariabilityVertex().put("ValidPartialConfOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Verify Valid Partial Configuration");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(3);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ValidPartialConf-menu",
						instEdgeOper);
			instEdgeOper.setIdentifier("ValidPartialConf-menu");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			validPartialConfSubOperationAction = new OpersSubOperation(3,
					"ValidPartialConfSubOper");

			instOperationSubAction = new InstConcept("ValidPartialConfSubOper",
					metaOperationSubAction, validPartialConfSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction.getInstAttribute("errorText").setValue(
					"The partial configuration evaluated is invalid");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.First_Solution
							.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("Valid partial configuration");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("index").setValue(3);
			if (newOpers)
				refas.getVariabilityVertex().put("ValidPartialConfSubOper",
						instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ValidPartialConf-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("ValidPartialConf-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			validPartialConfSubOperNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, validPartialConfSubOperNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			instLabeling = new InstConcept("ValidPartialConf-lab",
					metaLabeling, validPartialConfOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("ValidPartialConf-lab",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ValidPartialConf-lab-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("ValidPartialConf-lab-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Filter

			filterOper = new OpersConcept("FilterOper");

			instOperationAction = new InstConcept("FilterOper",
					metaOperationAction, filterOper);
			if (newOpers)
				refas.getVariabilityVertex().put("FilterOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Export.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Export Filtered Products");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(6);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("FilterProducts-menu",
						instEdgeOper);
			instEdgeOper.setIdentifier("AllProducts-menu");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			filterSubOperationAction = new OpersSubOperation(3,
					"FilterProductsSubOper");

			instOperationSubAction = new InstConcept("FilterProductsSubOper",
					metaOperationSubAction, filterSubOperationAction);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction.getInstAttribute("errorText").setValue(
					"Error found exporting the filtered products");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.Export_Solutions
							.toString()));
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("Products exported successfully");
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration").setValue(true);
			instOperationSubAction.getInstAttribute("index").setValue(3);
			if (newOpers)
				refas.getVariabilityVertex().put("FilterProductsSubOper",
						instOperationSubAction);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("FilterProducts-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("FilterProducts-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			filterSubOperNormal = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, filterSubOperNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			instLabeling = new InstConcept("FilterProducts-lab", metaLabeling,
					filterOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("FilterProducts-lab",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("FilterProducts-lab-rel",
						instEdgeOper);
			instEdgeOper.setIdentifier("FilterProducts-lab-rel");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Conditional Dead elements

			condDeadElemOper = new OpersConcept("CondDeadOper");

			instOperationAction = new InstConcept("CondDeadOper",
					metaOperationAction, condDeadElemOper);
			if (newOpers)
				refas.getVariabilityVertex().put("CondDeadOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					OpersOpType.Verification.toString());
			instOperationAction.getInstAttribute("opname").setValue(
					"Identify Conditional Dead Elements");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(8);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-menu-conddead",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-conddead");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			condDeadElemSubOperationAction = new OpersSubOperation(1,
					"CondDeadSubOper");

			instOperationSubAction = new InstConcept("CondDeadSubOper",
					metaOperationSubAction, condDeadElemSubOperationAction);
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
					"Conditional Dead element.");
			instOperationSubAction.getInstAttribute("errorMsg").setValue(
					" #number# conditional dead elements identified.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils
							.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif
									.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction.getInstAttribute("defectType").setValue(
					OpersDefectType.getDeadElements.toString());
			instOperationSubAction.getInstAttribute("defectsCoreOper")
					.setValue("Update Core Elements");
			// FIXME new core oper including Conf selected
			instOperationSubAction.getInstAttribute("completedMessage")
					.setValue("No errors found");
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"Sel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("CondDeadSubOper",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges()
						.put("ver-conddead", instEdgeOper);
			instEdgeOper.setIdentifier("ver-conddead");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			condDeadElemSubOperNormal = new OpersSubOperationExpType();
			condDeadElemSubOperNormal.setIdentifier("NORMAL");

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, condDeadElemSubOperNormal);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			instLabeling = new InstConcept("Ver-conddead-lab", metaLabeling,
					condDeadElemOperUniqueLabeling);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Ver-conddead-lab",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-conddead-lab-pw",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-conddead-lab-pw");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			// Commonality

			commonalityOper = new OpersConcept("CommonOper");

			instOperationAction = new InstConcept("CommonOper",
					metaOperationAction, commonalityOper);
			if (newOpers)
				refas.getVariabilityVertex().put("CommonOper",
						instOperationAction);
			instOperationAction.getInstAttribute("operType").setValue(
					StringUtils
							.formatEnumValue(OpersOpType.Computational_Analysis
									.toString()));
			instOperationAction.getInstAttribute("compType").setValue(
					"Simple quotient");
			instOperationAction.getInstAttribute("opname").setValue(
					"Calculate Commonality");
			instOperationAction.getInstAttribute("shortcut").setValue("S");
			instOperationAction.getInstAttribute("iteration").setValue(false);
			instOperationAction.getInstAttribute("visible").setValue(true);
			instOperationAction.getInstAttribute("prevSpacer").setValue(false);
			instOperationAction.getInstAttribute("position").setValue(25);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-menu-Common",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-menu-Common");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationAction, true);
			if (newOpers)
				instEdgeOper.setSourceRelation(instOperationGroup, true);

			commonalitySubOperationAction1 = new OpersSubOperation(1,
					"CommonSubOper1");

			instOperationSubAction = new InstConcept("CommonSubOper1",
					metaOperationSubAction, commonalitySubOperationAction1);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Model Verification Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.Number_Solutions
							.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"NSel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("CommonSubOper1",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-Common1", instEdgeOper);
			instEdgeOper.setIdentifier("ver-Common1");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			commonalityOperSubActionNormal1 = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, commonalityOperSubActionNormal1);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			commonalityOperUniqueLabeling1 = new OpersLabeling("unique");

			instLabeling = new InstConcept("Ver-Common-lab1", metaLabeling,
					commonalityOperUniqueLabeling1);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Ver-Common-lab1",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-Common-lab1",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-Common-lab1");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instLabeling, true);
			instEdgeOper.setSourceRelation(instOperationSubAction, true);

			commonalitySubOperationAction2 = new OpersSubOperation(1,
					"CommonSubOper2");

			instOperationSubAction = new InstConcept("CommonSubOper2",
					metaOperationSubAction, commonalitySubOperationAction2);
			instOperationSubAction.getInstAttribute("name").setValue(" ");
			instOperationSubAction.getInstAttribute("errorTitle").setValue(
					"Calculation Error");
			instOperationSubAction
					.getInstAttribute("errorText")
					.setValue(
							"Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.");
			instOperationSubAction.getInstAttribute("type").setValue(
					StringUtils.formatEnumValue(OpersSubOpType.Number_Solutions
							.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			instOperationSubAction
					.getInstAttribute("completedMessage")
					.setValue(
							"#numerator# number of products in the configuration. #denominator# "
									+ "total products. The commonality of this model considering the current"
									+ " configuration is #result#");
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);
			instOperationSubAction.getInstAttribute("outAttribute").setValue(
					"DSel");
			instOperationSubAction.getInstAttribute("updateOutAttributes")
					.setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("CommonSubOper2",
						instOperationSubAction);

			instOperationSubAction.getInstAttribute("index").setValue(1);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-common2", instEdgeOper);
			instEdgeOper.setIdentifier("ver-common2");
			instEdgeOper.setSupportMetaPairwiseRelation(metaPairwRelAso);
			instEdgeOper.setTargetRelation(instOperationSubAction, true);
			instEdgeOper.setSourceRelation(instOperationAction, true);

			commonalityOperSubActionNormal2 = new OpersSubOperationExpType();

			instOperSubOperationExpType = new InstConcept("exptype",
					metaExpType, commonalityOperSubActionNormal2);

			instOperSubOperationExpType.getInstAttribute("suboperexptype")
					.setValue("NORMAL");

			((List<InstAttribute>) instOperationSubAction
					.getInstAttributeValue("exptype")).add(new InstAttribute(
					"enum1", new ElemAttribute("EnumValue",
							StringType.IDENTIFIER, AttributeType.SYNTAX, false,
							"Enumeration Value", "", "", 1, -1, "", "", -1, "",
							""), instOperSubOperationExpType));

			commonalityOperUniqueLabeling2 = new OpersLabeling("unique");

			instLabeling = new InstConcept("Ver-common-lab2", metaLabeling,
					commonalityOperUniqueLabeling2);

			instLabeling.getInstAttribute("labelId").setValue("L1");
			instLabeling.getInstAttribute("position").setValue(1);
			instLabeling.getInstAttribute("once").setValue(false);
			instLabeling.getInstAttribute("order").setValue(false);
			if (newOpers)
				refas.getVariabilityVertex().put("Ver-common-lab2",
						instLabeling);

			instEdgeOper = new InstPairwiseRel();
			if (newOpers)
				refas.getConstraintInstEdges().put("ver-common-lab2",
						instEdgeOper);
			instEdgeOper.setIdentifier("ver-common-lab2");
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
			instOperationGroup.getInstAttribute("opgname").setValue(
					"Configuration");
			instOperationGroup.getInstAttribute("shortcut").setValue("C");

			configTempOper = new OpersConcept("ConfigureTemporalOper");

			instOperationAction = new InstConcept("ConfigureTemporalOper",
					metaOperationAction, configTempOper);
			refas.getVariabilityVertex().put("ConfigureTemporalOper",
					instOperationAction);
			// instOperationAction.getInstAttribute("operType").setValue(
			// OperationActionType.Configure.toString());
			instOperationAction.getInstAttribute("opname").setValue(
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
					StringUtils.formatEnumValue(OpersSubOpType.Number_Solutions
							.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);

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
			// instOperationAction.getInstAttribute("operType").setValue(
			// OperationActionType.Configure.toString());
			instOperationAction.getInstAttribute("opname").setValue(
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
					StringUtils.formatEnumValue(OpersSubOpType.First_Solution
							.toString()));
			instOperationSubAction.getInstAttribute("showDashboard").setValue(
					false);
			// instOperationSubAction.getInstAttribute("iteration")
			// .setValue(false);

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

	protected static InstConcept instVertexIE = null;
	protected static InstConcept instNmMetaPW = null;
	protected static InstConcept instNmMetaOT = null;
	protected static InstConcept instVertexVAR = null;
	protected static InstConcept instVertexLowVAR = null;
	protected static InstConcept instVertexLowExp = null;
	protected static InstConcept instVertexCG = null;

	private static void createSemanticNmMetaModel(InstanceModel refas,
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
			// voidModelSubOperationAction
			// .addInAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			redundanSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			ecrSubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
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
			variantSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variantOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDCoreOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperUniqLab
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
			condDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			condDeadElemOperUniqueLabeling
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
			// voidModelSubOperationAction
			// .addInAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			redundanSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			ecrSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
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
			variantSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverSDneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClCoreOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClallOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverClneverOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverCoreOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverAllOpersOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
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
			sasverConflClSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflClOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			sasverConflSDOperationSubAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			condDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}
		ElemAttribute attributeSel = new ElemAttribute("Sel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***", "",
				false, 2, 1, "false", "", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("Sel", attributeSel);
		semNmMetaConcept.addPropVisibleAttribute("01#" + "Sel");

		if (!empty) {
			wrongCardSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			wrongCardElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			voidModelSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			voidModelOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			validProductSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			validProductOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			validPartialConfSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			allProductsSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			allProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			numProductsSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			numProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			filterSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			filterOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			redundanSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			redundanOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			variabfactorOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			degreeOrthoSubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attributeSel.getName(), true));
			degreeOrthoOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semNmMetaConcept.getIdentifier(), attributeSel.getName(),
					true));
			verifFalsePLSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
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
			condDeadElemSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			condDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
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
			sasverClallOperUniqLab.addAttribute(new OpersIOAttribute(
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
			// voidModelSubOperationAction
			// .addOutAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			// voidModelOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			redundanSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			redundanOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
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
			sasverClallOperUniqLab
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
				2, 15, "Active" + "#==#" + "true" + "#" + "false", "Active"
						+ "#==#" + "true#false", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("ConfSel", attribute);

		if (!empty) {
			// voidModelSubOperationAction
			// .addInAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			// voidModelOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// redundanSubOperationAction
			// .addInAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			// redundanOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			condDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			condDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
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
			sasverClallOperUniqLab
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
			// voidModelSubOperationAction
			// .addInAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			// voidModelOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// redundanSubOperationAction
			// .addInAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			// redundanOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			condDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			condDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
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
			sasverClallOperUniqLab
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
			voidModelSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			voidModelOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			redundanSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			redundanOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
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
			variantSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variantOperUniqueLabeling
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
			sasverClallOperUniqLab
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
			condDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			condDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Required", "Boolean",
				AttributeType.OPERATION, true, "Is Required",
				"Defined as required by the user (full mandatory)", false, 2,
				4, "", "", -1, "", "");

		semNmMetaConcept.putSemanticAttribute("Required", attribute);
		semNmMetaConcept.addPropEditableAttribute("04#" + "Required");
		semNmMetaConcept.addPropVisibleAttribute("04#" + "Required");

		if (!empty) {
			voidModelSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			voidModelOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// redundanSubOperationAction
			// .addInAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			// redundanOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			wrongCardSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			wrongCardElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));

			verifFalsePLSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
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
			variantSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variantOperUniqueLabeling
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
			sasverClallOperUniqLab
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
			condDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			condDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Core", "Boolean",
				AttributeType.OPERATION, false, "Is a Core Concept",
				"Core element result of a core update operation", false, 2, 7,
				"false", "", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("Core", attribute);
		semNmMetaConcept.addPropVisibleAttribute("07#" + "Core");

		if (!empty) {
			// voidModelSubOperationAction
			// .addOutAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			// voidModelOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// redundanSubOperationAction
			// .addOutAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			// redundanOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			wrongCardSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			wrongCardElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
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
			variantSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variantOperUniqueLabeling
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
			sasverClallOperUniqLab
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
			condDeadElemSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			condDeadElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalseOptElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
		}

		attribute = new ElemAttribute("Var", "Boolean",
				AttributeType.OPERATION, false, "Is a Variant Feature",
				"Variant Feature identified by the variant operation", false,
				2, -1, "false", "", -1, "", "");
		semNmMetaConcept.putSemanticAttribute("Var", attribute);

		if (!empty) {
			variantSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variantOperUniqueLabeling
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
			// voidModelSubOperationAction
			// .addInAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			// voidModelOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			redundanSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			redundanOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction
					.addInAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
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
			sasverClallOperUniqLab
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
			// voidModelSubOperationAction
			// .addOutAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			// voidModelOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			// redundanSubOperationAction
			// .addOutAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			// redundanOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semNmMetaConcept
			// .getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semNmMetaConcept
							.getIdentifier(), attribute.getName(), true));
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
			sasverClallOperUniqLab
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
			voidModelSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			voidModelOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// redundanSubOperationAction.addInAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// redundanOperUniqueLabeling.addAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
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
			variantSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			variantOperUniqueLabeling.addAttribute(new OpersIOAttribute(
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
			sasverClallOperUniqLab.addAttribute(new OpersIOAttribute(
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
			condDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			condDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
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
			voidModelSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			voidModelOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			redundanSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			redundanOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
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
			sasverClallOperUniqLab.addAttribute(new OpersIOAttribute(
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
			voidModelSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			voidModelOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			redundanSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			redundanOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
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
			sasverClallOperUniqLab.addAttribute(new OpersIOAttribute(
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
				"Core element result of a core update operation", false, 2, -1,
				"", "", -1, "", "");

		if (!empty) {
			simulExecOperUniLab.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));
			simsceExecOperLab2.addAttribute(new OpersIOAttribute(semInfraOTRel
					.getIdentifier(), attribute.getName(), true));

			updateCoreSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			updateCoreOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			variantSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			variantOperUniqueLabeling.addAttribute(new OpersIOAttribute(
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

		}
		semInfraOTRel.putSemanticAttribute("OCore", attribute);

		if (!empty) {
			// voidModelSubOperationAction.addInAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// voidModelOperUniqueLabeling.addAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// validProductSubOperationAction.addIntAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// validProductOperUniqueLabeling.addAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// validPartialConfSubOperationAction
			// .addInAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
			// validPartialConfOperUniqueLabeling
			// .addAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
			// allProductsSubOperationAction.addInAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// allProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// numProductsSubOperationAction.addInAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// numProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// filterSubOperationAction.addInAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// filterOperUniqueLabeling.addAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// variabfactorSubOperationAction1
			// .addInAttribute(new OpersIOAttribute(semInfraOTRel
			// .getIdentifier(), attribute.getName(), true));
			// variabfactorOperUniqueLabeling1.addAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// degreeOrthoSubOperationAction1.addInAttribute(new
			// OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// degreeOrthoOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
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
			sasverClallOperUniqLab.addAttribute(new OpersIOAttribute(
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
		// FIXME review three input of this attribute

		if (!empty) {
			// voidModelSubOperationAction.addOutAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			// voidModelOperUniqueLabeling.addAttribute(new OpersIOAttribute(
			// semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction.addOutAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			redundanOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction
					.addOutAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
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
			sasverClallOperUniqLab.addAttribute(new OpersIOAttribute(
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

		semInfraOTRel.putSemanticAttribute(
				"relationType",
				new ElemAttribute("relationType", "Class",
						AttributeType.OPERATION, true, "Relation Type",
						"Type of over-two relation from the selected"
								+ " relation group", InstAttribute.class
								.getCanonicalName(), null, null, null, 0, 6,
						"", "", 6, "#relationType#all#", ""));
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
			wrongCardSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			wrongCardElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			voidModelSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			voidModelOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			redundanSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			redundanOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
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
			sasverClallOperUniqLab.addAttribute(new OpersIOAttribute(
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
			variantSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			variantOperUniqueLabeling.addAttribute(new OpersIOAttribute(
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
			condDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			condDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
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
			wrongCardSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			wrongCardElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			voidModelSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			voidModelOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validProductSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validProductOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			validPartialConfSubOperationAction
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			validPartialConfOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			allProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			allProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			numProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			numProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			filterSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			filterOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			redundanSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			redundanOperUniqueLabeling.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction1.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogeneitySubOperationAction2.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			homogoneityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction1.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalitySubOperationAction2.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			commonalityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			variabfactorSubOperationAction1
					.addInAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
			variabfactorOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			degreeOrthoSubOperationAction1.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			degreeOrthoOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			verifFalsePLElemOperUniqueLabeling
					.addAttribute(new OpersIOAttribute(semInfraOTRel
							.getIdentifier(), attribute.getName(), true));
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
			sasverClallOperUniqLab.addAttribute(new OpersIOAttribute(
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
			condDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
					semInfraOTRel.getIdentifier(), attribute.getName(), true));
			condDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
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
		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
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
//TODO variables
		
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
		voidModelSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		voidModelOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		validProductSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		validProductOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		validPartialConfSubOperationAction
				.addOutAttribute(new OpersIOAttribute(semVariable
						.getIdentifier(), attribute.getName(), true));
		validPartialConfOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		allProductsSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		allProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		numProductsSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		numProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		filterSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		filterOperUniqueLabeling.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		redundanOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction1.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		homogoneityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction2.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		homogoneityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction1.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		commonalityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction2.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		commonalityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		variabfactorSubOperationAction1.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		variabfactorOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		degreeOrthoSubOperationAction1.addOutAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		degreeOrthoOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		verifFalsePLSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semInfraOTRel.getIdentifier(), attribute.getName(), true));
		verifFalsePLElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semInfraOTRel.getIdentifier(), attribute.getName(), true));
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

		voidModelSubOperationAction.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		voidModelOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		validProductSubOperationAction.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		validProductOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		validPartialConfSubOperationAction.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		validPartialConfOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		allProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		allProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		numProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		numProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		filterSubOperationAction.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		filterOperUniqueLabeling.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		redundanSubOperationAction.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		redundanOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction1.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		homogoneityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction2.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		homogoneityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction1.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		commonalityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction2.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		commonalityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		variabfactorSubOperationAction1.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		variabfactorOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		degreeOrthoSubOperationAction1.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		degreeOrthoOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		verifFalsePLElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));
		simulExecOperUniLab.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		simulSubOperationAction.addInAttribute(new OpersIOAttribute(semVariable
				.getIdentifier(), attribute.getName(), true));
		simSceSubOperationAction.addInAttribute(new OpersIOAttribute(
				semVariable.getIdentifier(), attribute.getName(), true));

		// Variable with the domain restricted to the varConfDom when the
		// isConfDomain is true
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
				OpersConcept.class.getCanonicalName(), "OpMSubOper", null, "",
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
				OpersConcept.class.getCanonicalName(), "OpMSubOper", null, "",
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
				OpersLabeling.class.getCanonicalName(), "OpMLabeling", null,
				"", 0, 5, "", "variableType" + "#==#" + "LowLevel variable",
				-1, "", "");
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
				OpersConcept.class.getCanonicalName(), "OpMSubOper", null, "",
				0, 6, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semVariable.putSemanticAttribute("LowLevelVarInSubOper", attribute);
		semVariable.addPropEditableAttribute("06#" + "LowLevelVarInSubOper");
		semVariable.addPropVisibleAttribute("06#" + "LowLevelVarInSubOper"
				+ "#" + "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute("LowLevelInVarLabel", "Class",
				AttributeType.OPERATION, false, "Input Labeling as low var",
				"Labeling with only a set of variables for input suboper",
				OpersLabeling.class.getCanonicalName(), "OpMLabeling", null,
				"", 0, 7, "", "variableType" + "#==#" + "LowLevel variable",
				-1, "", "");
		semVariable.putSemanticAttribute("LowLevelInVarLabel", attribute);

		attribute = new ElemAttribute("InputSubOperAsInteger", "Class",
				AttributeType.OPERATION, false,
				"SubOper to associate (Input is Int)",
				"Sub Operation to include the low-level variable previous "
						+ "calculated in a low level expression converted "
						+ "to an Integer value",
				OpersConcept.class.getCanonicalName(), "OpMSubOper", null, "",
				0, 8, "", "variableType" + "#==#" + "LowLevel variable", -1,
				"", "");
		semVariable.putSemanticAttribute("InputSubOperAsInteger", attribute);

		attribute = new ElemAttribute("IntegerInVarLabel", "Class",
				AttributeType.OPERATION, false, "Input Labeling as int",
				"Labeling with only a set of variables for input suboper",
				OpersLabeling.class.getCanonicalName(), "OpMLabeling", null,
				"", 0, 9, "", "variableType" + "#==#" + "LowLevel variable",
				-1, "", "");
		semVariable.putSemanticAttribute("IntegerInVarLabel", attribute);

		attribute = new ElemAttribute("LowLevelVarValue", "String",
				AttributeType.GLOBALCONFIG, false, "Fixed Input Value (Float)",
				"Fixed value defined for an input variable", "", 0, 8, "",
				"variableType" + "#==#" + "LowLevel variable", -1, "", "");
		semVariable.putSemanticAttribute("LowLevelVarValue", attribute);

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

		SyntaxElement infraOpersM2Attribute = new SyntaxElement('A',
				"NmAttribute", false, true, "NmAttribute",
				"infrasyntaxm2bigconcept", "Attributes for the meta-concept",
				120, 120, "/com/variamos/gui/perspeditor/images/concept.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraOpersM2Attribute.addModelingAttribute("Name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", "InstAttribute", 0, 1, "", "", 1, "", ""));

		infraOpersM2Attribute.addModelingAttribute("type", new ElemAttribute(
				"type", "MetaEnumeration", AttributeType.SYNTAX, false, "Type",
				"", "TypeEnum", "", "", 0, 3, "", "", -1, "type" + "#all#\n\n",
				""));

		// classCanName
		infraOpersM2Attribute.addModelingAttribute("enumType", "String", false,
				"Enumeration Type", "", "", 0, 7, "", "", -1, "#" + "enumType"
						+ "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("configuredValue", "String",
				false, "Configured Value", "", "", 0, 9, "", "", -1, "#"
						+ "configuredValue" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("configuredDomain",
				"String", false, "Configured Domain", "", "", 0, 9, "", "", -1,
				"#" + "configuredDomain" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("domain", "String", false,
				"Domain", "", "", 0, 10, "", "", -1, "#" + "domain"
						+ "#all#\n\n", "");

		InstConcept instInfraSyntaxOpersM2Attribute = new InstConcept(
				"NmAttribute", infraMetaMetaCollection, infraOpersM2Attribute);
		refas.getVariabilityVertex().put("NmAttribute",
				instInfraSyntaxOpersM2Attribute);

		SyntaxElement infraOpersM2Enum = new SyntaxElement('A',
				"NmEnumeration", false, true, "NmEnumeration",
				"infrasyntaxm2miniconcept", "Enumeration meta-concept", 120,
				120, "/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instInfraSyntaxOpersM2Enum = new InstConcept(
				"NmEnumeration", infraMetaMetaCollection, infraOpersM2Enum);
		refas.getVariabilityVertex().put("NmEnumeration",
				instInfraSyntaxOpersM2Enum);

		SyntaxElement infraOpersM2EnumLit = new SyntaxElement('A',
				"NmEnumLiteral", false, true, "NmEnumLiteral",
				"infrasyntaxm2miniconcept", "Enumeration meta-concept", 120,
				120, "/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraOpersM2EnumLit.addModelingAttribute("Name", new ElemAttribute(
				"literalId", "Integer", AttributeType.SYNTAX, false,
				"Concept Name", "", "InstAttribute", 0, 1, "", "", 1, "", ""));

		infraOpersM2EnumLit
				.addModelingAttribute("type", new ElemAttribute("litValue",
						"String", AttributeType.SYNTAX, false, "Type", "",
						"TypeEnum", "", "", 0, 3, "", "", -1, "type"
								+ "#all#\n\n", ""));

		InstConcept instInfraSyntaxOpersM2EnumLit = new InstConcept(
				"NmEnumLiteral", infraMetaMetaCollection, infraOpersM2EnumLit);
		refas.getVariabilityVertex().put("NmEnumLiteral",
				instInfraSyntaxOpersM2EnumLit);

		InstPairwiseRel instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("enutoenumlit", instEdge);
		instEdge.setIdentifier("enutoenumlit");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2EnumLit, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Enum, true);

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
				OpersConcept.class.getCanonicalName(), "OpMSubOper", null, "",
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
		voidModelSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		voidModelOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		validProductSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		validProductOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		validPartialConfSubOperationAction
				.addOutAttribute(new OpersIOAttribute(semLowVariable
						.getIdentifier(), attribute.getName(), true));
		validPartialConfOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		allProductsSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		allProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		numProductsSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		numProductsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		filterSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		filterOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		redundanOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction1.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		homogoneityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction2.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		homogoneityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction1.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		commonalityOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction2.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		commonalityOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		variabfactorSubOperationAction1.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		variabfactorOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		degreeOrthoSubOperationAction1.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		degreeOrthoOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		verifFalsePLSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
		verifFalsePLElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semLowVariable.getIdentifier(), attribute.getName(), true));
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
				OpersConcept.class.getCanonicalName(), "OpMSubOper", null, "",
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
				OpersLabeling.class.getCanonicalName(), "OpMLabeling", null,
				"", 0, 5, "", "", -1, "", "");
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
				OpersConcept.class.getCanonicalName(), "OpMSubOper", null, "",
				0, 6, "", "", -1, "", "");
		semLowVariable.putSemanticAttribute("LowLevelVarInSubOper", attribute);
		semLowVariable.addPropEditableAttribute("06#" + "LowLevelVarInSubOper");
		semLowVariable.addPropVisibleAttribute("06#" + "LowLevelVarInSubOper"
				+ "#" + "variableType" + "#==#" + "LowLevel variable");

		attribute = new ElemAttribute("LowLevelInVarLabel", "Class",
				AttributeType.OPERATION, false, "Input Labeling as low var",
				"Labeling with only a set of variables for input suboper",
				OpersLabeling.class.getCanonicalName(), "OpMLabeling", null,
				"", 0, 7, "", "", -1, "", "");
		semLowVariable.putSemanticAttribute("LowLevelInVarLabel", attribute);
		semLowVariable.addPropEditableAttribute("07#" + "LowLevelInVarLabel");
		semLowVariable.addPropVisibleAttribute("07#" + "LowLevelInVarLabel");

		attribute = new ElemAttribute(
				"InputSubOperAsInteger",
				"Class",
				AttributeType.OPERATION,
				false,
				"Input SubOper as int",
				"Sub Operation to include the low-level variable previous calculated, in a low level expression, as Integer",
				OpersConcept.class.getCanonicalName(), "OpMSubOper", null, "",
				0, 8, "", "", -1, "", "");
		semLowVariable.putSemanticAttribute("InputSubOperAsInteger", attribute);
		semLowVariable
				.addPropEditableAttribute("08#" + "InputSubOperAsInteger");
		semLowVariable.addPropVisibleAttribute("08#" + "InputSubOperAsInteger");

		// FIXME not used at the moment
		attribute = new ElemAttribute("IntegerInVarLabel", "Class",
				AttributeType.OPERATION, false, "Input Labeling as int",
				"Labeling with only a set of variables for input suboper",
				OpersLabeling.class.getCanonicalName(), "OpMLabeling", null,
				"", 0, 9, "", "", -1, "", "");
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

	private static void createGeneralMetaModel(InstanceModel refas) {
		ArrayList<OpersExpr> semExpr = new ArrayList<OpersExpr>();

		ElemAttribute attribute = null;

		instGeneralModel = new InstConcept("GeneralModel",
				DefaultOpersMM.metaMetaModel, generalModel);

		OpersConcept semGeneralElement = new OpersConcept("GeneralConcept");
		// From refas name depends all the static operations, do not change
		// it

		instVertexGE = new InstConcept("GeneralConcept", metaMetaInstConcept,
				semGeneralElement);

		// Semantic Element

		semExpr = new ArrayList<OpersExpr>();

		semGeneralElement.setSemanticExpressions(semExpr);

		OpersExpr t1 = new OpersExpr("001 Val/Ver - req implies sel", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexGE,
				instVertexGE, instVertexGE, "Required", "Sel");

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionNormal.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		wrongCardOperSubActionRelaxable.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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

		wrongCardOperSubActionNormal.addSemanticExpression(t1);

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
		variantOptSubOperNormal.addSemanticExpression(t1);
		// verifParentsOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexGE, instVertexGE, instVertexGE, "SimulSel", "Core");

		OpersExpr t2 = new OpersExpr("2", refas.getSemanticExpressionTypes()
				.get("LessOrEquals"), instVertexGE, 1, false, t1);

		t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexGE, instVertexGE, instVertexGE, "ConfSel", "SimulSel");

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), instVertexGE, 1, false, t1);

		t1 = new OpersExpr(
				"003 Val - Core+SimulSel <=1 ConfigSel+SimulSel <=1", refas
						.getSemanticExpressionTypes().get("And"), instVertexGE,
				t1, t2);

		// voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionNormal.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("011 (NEW ConfSel <==> Sel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexGE, instVertexGE, instVertexGE, "ConfSel", "Sel");

		validProductSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexGE, instVertexGE, instVertexGE, "Var", "Core");

		t1 = new OpersExpr("012 (NEW  Core + Var = 1)", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE, t1,
				1);

		variantOptSubOperNormal.addSemanticExpression(t1);
		// verifParentsOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		// t1 = new OpersExpr("4", refas.getSemanticExpressionTypes().get("Or"),
		// instVertexGE, instVertexGE, instVertexGE, "ConfNotSel",
		// "TestConfNotSel");

		t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get("Or"),
				instVertexGE, instVertexGE, instVertexGE, "Proh", "ConfNotSel");

		t1 = new OpersExpr("6", refas.getSemanticExpressionTypes().get("Or"),
				instVertexGE, instVertexGE, "Dead", true, t1);

		t1 = new OpersExpr(
				"004 (Val - NotAvail (Dead Or Prohibit Or NotSelec))", refas
						.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexGE, instVertexGE, "Exclu", true, t1);

		// voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionNormal.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, instVertexGE, instVertexGE, "Sel",
				"Proh");

		t1 = new OpersExpr("005 (Ver/Val - Sel * Proh = 0))", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE, t1,
				0);

		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
		verifFalseOptOperSubActionNormal.addSemanticExpression(t1);

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
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

		t1 = new OpersExpr("007 (UpCore - core * proh = 0))", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE, t1,
				0);

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		variantOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		// t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get("Or"),
		// instVertexGE, instVertexGE, instVertexGE, "SimulSel",
		// "TestConfSel");

		t2 = new OpersExpr("5", refas.getSemanticExpressionTypes().get("Or"),
				instVertexGE, instVertexGE, instVertexGE, "Core", "ConfSel");

		t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get("Or"),
				instVertexGE, instVertexGE, "SimulSel", true, t2);

		t1 = new OpersExpr("002 (Val - Selected (Core, ConfSel, SimulSel))",
				refas.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexGE, instVertexGE, "Sel", true, t1);

		// voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionNormal.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get("Or"),
				instVertexGE, instVertexGE, instVertexGE, "Core", "ConfSel");

		t1 = new OpersExpr("#010 (Val - Selected (Core, ConfSel))", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexGE,
				instVertexGE, "Sel", false, t1);

		condDeadElemSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("5", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, instVertexGE, instVertexGE, "Sel",
				"Exclu");

		t1 = new OpersExpr("006 (Val - Selected*Exclu =0)", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexGE, 0,
				false, t1);

		// voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
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
		sasverNoLoopsOperationSubActionMV.addOutAttribute(new OpersIOAttribute(
				semHardConcept.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(semHardConcept
						.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperMVUniqueLabeling.addAttribute(new OpersIOAttribute(
				semHardConcept.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperRedUniqueLabeling.addAttribute(new OpersIOAttribute(
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

		t1 = new OpersExpr("036 (O Val - OrderHC...)", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexHC,
				instVertexGE, "Order", true, t1);

		allProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
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
	private static void createFeatureMetaModel(InstanceModel refas) {
		ArrayList<OpersExpr> semExpr = new ArrayList<OpersExpr>();

		ElemAttribute attribute = null;

		OpersConcept semFeature = new OpersConcept("Feature");
		simsceExecOperLab2.addAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), "Sel", true));

		instVertexF = new InstConcept("Feature", metaMetaInstConcept,
				semFeature);

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

		attribute = new ElemAttribute("oTmpAnaSel", "Integer",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected for verifications", "", 0,
				new RangeDomain(0, 400, 0), 0, 10, "", "", -1,
				"oTmpAnaSel#all#", "");
		semFeatOverTwoRelation.putSemanticAttribute("oTmpAnaSel", attribute);
		lcaSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semFeatOverTwoRelation.getIdentifier(), attribute.getName(),
				true));
		lcaOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semFeatOverTwoRelation.getIdentifier(), attribute.getName(),
				true));

		attribute = new ElemAttribute("oOutAnaSel", "Boolean",
				AttributeType.OPERATION, false, "Selected for verifications",
				"", false, 0, -1, "", "", -1, "oOutAnaSel#all#", "");
		semFeatOverTwoRelation.putSemanticAttribute("oOutAnaSel", attribute);
		rootSubOperationAction.addOutAttribute(new OpersIOAttribute(
				semFeatOverTwoRelation.getIdentifier(), attribute.getName(),
				true));
		rootOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semFeatOverTwoRelation.getIdentifier(), attribute.getName(),
				true));
		sasverNoLoopsOperationSubActionMV.addOutAttribute(new OpersIOAttribute(
				semFeatOverTwoRelation.getIdentifier(), attribute.getName(),
				true));
		sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(semFeatOverTwoRelation
						.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperMVUniqueLabeling.addAttribute(new OpersIOAttribute(
				semFeatOverTwoRelation.getIdentifier(), attribute.getName(),
				true));
		sasverNoLoopsOperRedUniqueLabeling.addAttribute(new OpersIOAttribute(
				semFeatOverTwoRelation.getIdentifier(), attribute.getName(),
				true));

		ecrSubOperationAction1.addOutAttribute(new OpersIOAttribute(
				semFeatOverTwoRelation.getIdentifier(), attribute.getName(),
				true));

		semFeatOverTwoRelation.setSemanticExpressions(semExpr);

		OpersExpr t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes()
				.get("Or"), ExpressionVertexType.LEFTSUBITEROUTRELVARIABLE,
				instVertexFFGR, instNmMetaPW, "pOutAnaSel", true, "FalseVal");

		t1 = new OpersExpr("047NEW VerPar - isSructParent", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITEROUTRELVARIABLE, instVertexFFGR,
				instVertexFFGR, t1, "oOutAnaSel");

		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, "inAnaSel", true, "FalseVal");

		t1 = new OpersExpr("052 (NNEW structValMan for Analysis)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexFFGR, t1, "oOutAnaSel");

		semExpr.add(t1);
		rootSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, null, "tmpAnaSel", 0, true);

		t1 = new OpersExpr("Core", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, instVertexF, t1, 0);

		OpersExpr t3 = new OpersExpr("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexFFGR, instVertexF, null, "inAnaSel", 0, true);

		t3 = new OpersExpr("Core", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, instVertexF, t3, 0);

		t1 = new OpersExpr("# structValMan for Analysis", "", refas
				.getSemanticExpressionTypes().get("Sum"), instVertexFFGR, t3,
				t1);

		t1 = new OpersExpr("Core", refas.getSemanticExpressionTypes().get(
				"Equals"), instVertexFFGR, instVertexFFGR, "oTmpAnaSel", true,
				t1);

		OpersExpr t2 = new OpersExpr("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexFFGR, instVertexF, null, "outAnaSel", 0, true);

		t2 = new OpersExpr("Core", refas.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, instVertexF, t2, 0);

		t1 = new OpersExpr("053 (NNEW structValMan for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexFFGR,
				t2, t1);

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("#structValMan for Analysis", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instVertexFFGR,
				instVertexFFGR, "oTmpAnaSel", 0);

		t2 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, null, "outAnaSel", 0, true);

		t2 = new OpersExpr("Core", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, instVertexF, t2, 1);

		t1 = new OpersExpr("054 (NNEW structValMan for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexFFGR,
				t2, t1);

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCRELVARIABLE, instVertexFFGR,
				instNmMetaPW, "pOutAnaSel", true, "FalseVal");

		t1 = new OpersExpr("outAnaSel", refas.getSemanticExpressionTypes().get(
				"Or"), ExpressionVertexType.LEFTITERINCRELVARIABLE,
				instVertexFFGR, instNmMetaPW, t1, "FalseVal");

		t1 = new OpersExpr("055 (TODEF outAnaSel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTVARIABLE, instVertexFFGR,
				instVertexFFGR, "oOutAnaSel", true, t1);

		ecrOperSubActionNormal1.addSemanticExpression(t1);
		// rootSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		OpersConcept directFeaFeatVertSemEdge = new OpersConcept(
				"ParentFeaturePW");
		attribute = new ElemAttribute("pOutAnaSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected for SD verifications", "", false, 0, 12, "", "", -1,
				"level#all#", "");
		directFeaFeatVertSemEdge.putSemanticAttribute("pOutAnaSel", attribute);
		rootSubOperationAction.addOutAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		rootOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverNoLoopsOperationSubActionMV.addOutAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(directFeaFeatVertSemEdge
						.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperMVUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverNoLoopsOperRedUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));

		ecrSubOperationAction1.addOutAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));

		attribute = new ElemAttribute("pTmpAnaSel", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "Selected for analysis",
				"", 0, new RangeDomain(0, 400, 0), 0, 10, "", "", -1,
				"level#all#", "");
		directFeaFeatVertSemEdge.putSemanticAttribute("pTmpAnaSel", attribute);
		lcaSubOperationAction.addOutAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));
		lcaOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaFeatVertSemEdge.getIdentifier(), attribute.getName(),
				true));

		InstConcept instDirFeaFeatVertSemEdge = new InstConcept(
				"ParentFeaturePW", metaMetaPairwiseRelation,
				directFeaFeatVertSemEdge);

		InstPairwiseRel instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ffptoip", instEdge);
		instEdge.setIdentifier("ffptoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instDirFeaFeatVertSemEdge, true);

		attribute = new ElemAttribute("inAnaSel", "Boolean",
				AttributeType.SYNTAX, false, "Selected for Analysis",
				"Marked as selected for an analysis operation "
						+ "(e.g., LCA, RootOpers)", false, 0, 10, "", "", -1,
				"", "");
		semFeature.putSemanticAttribute("inAnaSel", attribute);
		degreeOrthoSubOperationAction2.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		degreeOrthoOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		lcaSubOperationAction.addInAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));
		lcaOperUniqueLabeling.addAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));
		rootSubOperationAction.addInAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));
		rootOperUniqueLabeling.addAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("tmpAnaSel", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "Selected for Analysis",
				"Propagate selection count for an analysis operation "
						+ "(e.g., LCA, RootOpers)", 0, new RangeDomain(0, 400,
						0), 0, 10, "", "", -1, "", "");
		semFeature.putSemanticAttribute("tmpAnaSel", attribute);
		lcaSubOperationAction.addOutAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));
		lcaOperUniqueLabeling.addAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("outAnaSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected from Analysis Oper", "", false, 0, 10, "false", "",
				-1, "", "");
		semFeature.putSemanticAttribute("outAnaSel", attribute);
		degreeOrthoSubOperationAction2.addOutAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		degreeOrthoOperUniqueLabeling2.addAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		lcaSubOperationAction.addOutAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));
		lcaOperUniqueLabeling.addAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));
		rootSubOperationAction.addOutAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));
		rootOperUniqueLabeling.addAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));
		ecrSubOperationAction1.addOutAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("structVal", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "No loops validation",
				"", 0, new RangeDomain(0, 40, 0), 0, -1, "false", "", -1, "",
				"");
		semFeature.putSemanticAttribute("structVal", attribute);
		sasverNoLoopsOperationSubActionMV.addOutAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(semFeature
						.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperMVUniqueLabeling.addAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperRedUniqueLabeling.addAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));

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

		attribute = new ElemAttribute("FeatureType", "String",
				AttributeType.OPERATION, "Feature Type", "", "None", false,
				null, 2, -1, "", "", -1, "", "");

		semFeature.putSemanticAttribute("FeatureType", attribute);
		voidModelSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		validProductSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		validPartialConfSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		allProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		numProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		filterSubOperationAction.addInAttribute(new OpersIOAttribute(semFeature
				.getIdentifier(), attribute.getName(), true));
		verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		redundanSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction1.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction2.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction1.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction2.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		variabfactorSubOperationAction1.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		degreeOrthoSubOperationAction1.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		verifRootSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		verifParentsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		variantSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));

		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		verifFalseOptSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		verifDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		condDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));
		condDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semFeature.getIdentifier(), attribute.getName(), true));

		semExpr = new ArrayList<OpersExpr>();

		semFeature.setSemanticExpressions(semExpr);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, instVertexGE, "SimulSel", true, 2);

		// t1 = new OpersExpr("3",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// instVertexF, t1, 0);

		// t1 = new OpersExpr("4",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// instVertexF, instVertexGE, "TestConfSel", true, t1);

		t1 = new OpersExpr("016 (NEW Val - OrderF...)", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexF,
				instVertexGE, "Order", true, t1);

		allProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCRELVARIABLE, instVertexF,
				instNmMetaPW, "pOutAnaSel", true, "TrueVal");

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTITERINCRELVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexF, instVertexF,
				instNmMetaPW, t1, "TrueVal");

		t2 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexF, instVertexF,
				instVertexF, "outAnaSel", "IsRootFeature");

		t1 = new OpersExpr("014 (NEW VerPar - isSructParent)", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexF, t2,
				t1);

		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		// verifParentsOperSubActionRelaxable.addSemanticExpression(t1);

		// t1 = new OpersExpr("HasParent",
		// refas.getSemanticExpressionTypes().get(
		// "Equals"), instVertexF, instVertexF, "Core", true, 1);

		// verifParentsOperSubActionToVerify.addSemanticExpression(t1);

		// verifParentsOperSubActionNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("2-1",
				"Remove this or another of the root features", refas
						.getSemanticExpressionTypes().get("Equals"),
				instVertexF, instVertexF, "FeatureType", "Root");

		t1 = new OpersExpr("013 (NEW VerRoot - IsRootFeature=...)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexF, instVertexF, "IsRootFeature", true, t1);

		verifRootOperSubActionRelaxable.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("2-1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), instVertexF, instVertexF, "FeatureType", "Root");

		t3 = new OpersExpr("3-", refas.getSemanticExpressionTypes().get(
				"Equals"), instVertexF, instVertexF, "IsRootFeature", true, 0);

		t1 = new OpersExpr("000 (IsRootFeature=...)", refas
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
				"Equals"), instVertexF, instVertexF, "FeatureType", "Root");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexF, instVertexF, "Sel", true, 1);

		t1 = new OpersExpr("015*** NEW VOID (Root Implies Sel)", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexF, t1,
				t3);
		voidModelSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("2-1", refas.getSemanticExpressionTypes().get(
				"Equals"), instVertexF, instVertexF, "FeatureType", "Root");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexF, instVertexF, "Core", true, 1);

		t1 = new OpersExpr("015 (NEW Ver/Val - Root Implies Core)", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexF, t1,
				t3);
		// voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionNormal.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);

		verifParentsOperSubActionNormal.addSemanticExpression(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);
		variantOptSubOperNormal.addSemanticExpression(t1);
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

		t1 = new OpersExpr("2-1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), instVertexF, instVertexF, "FeatureType", "Root");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexF, instVertexF, "Core", true, 0);

		t1 = new OpersExpr("017 (NEW FM analysis - !Root Implies !Core)", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexF, t1,
				t3);
		// voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionNormal.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCRELVARIABLE, instVertexF,
				instDirFeaFeatVertSemEdge, "pOutAnaSel", true, "FalseVal");

		t1 = new OpersExpr("018 (NEW outAnaSel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCRELVARIABLE, instVertexF,
				instVertexF, "outAnaSel", false, t1);

		ecrOperSubActionNormal1.addSemanticExpression(t1);
		rootSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("019 (NEW true for homogeneity)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexF, instVertexF, instVertexF, "Sel", "TrueVal");

		homogeneityOperSubActionToVerify1.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCRELVARIABLE, instVertexF,
				instDirFeaFeatVertSemEdge, "pTmpAnaSel", true, 0);

		t1 = new OpersExpr("sub LCA", refas.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTVARIABLE,
				ExpressionVertexType.RIGHTMODELVARS, instVertexF, instVertexF,
				instGeneralModel, "tmpAnaSel", "totalAnaSel");

		t1 = new OpersExpr("020 (NEW outAnaOut LCA)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexF, instVertexF, "outAnaSel", false, t1);

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCRELVARIABLE, instVertexF,
				instDirFeaFeatVertSemEdge, "pTmpAnaSel", true, 0);

		t1 = new OpersExpr("021 (NEW outAnaOut LCA)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERINCRELVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexF,
				instDirFeaFeatVertSemEdge, instVertexF, t1, "tmpAnaSel");

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

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
		voidModelSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		validProductSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		validPartialConfSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		allProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		numProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		filterSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		redundanSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction1.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction2.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction1.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction2.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		variabfactorSubOperationAction1.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		degreeOrthoSubOperationAction1.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		verifRootSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		verifParentsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		variantSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));

		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		verifFalseOptSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		verifDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		condDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		condDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
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
		voidModelSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		validProductSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		validPartialConfSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		allProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		numProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		filterSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		redundanSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction1.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction2.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction1.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction2.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		variabfactorSubOperationAction1.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		degreeOrthoSubOperationAction1.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));

		verifRootSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		verifParentsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		variantSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		verifFalseOptSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		verifDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		condDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semGFeature.getIdentifier(), attribute.getName(), true));
		condDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
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
		voidModelSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		validProductSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		validPartialConfSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		allProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		numProductsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		filterSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
				semRFeature.getIdentifier(), attribute.getName(), true));
		redundanSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		redundanOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction1.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		homogeneitySubOperationAction2.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction1.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		commonalitySubOperationAction2.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		variabfactorSubOperationAction1.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		degreeOrthoSubOperationAction1.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));

		verifRootSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		verifParentsSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		updateCoreSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		variantSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		sasverSDCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		sasverClCoreOperationSubAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		verifFalseOptSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		verifDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		verifFalsePLSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		condDeadElemSubOperationAction.addInAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));
		condDeadElemOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				semLFeature.getIdentifier(), attribute.getName(), true));

		refas.getVariabilityVertex().put("LeafFeature", instVertexLF);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("lftof", instEdge);
		instEdge.setIdentifier("lftof");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instVertexLF, true);

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

		t1 = new OpersExpr("028 (NEW Ver/Val - MANSelected)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexF, "Sel",
				"Sel");

		semExpr.add(t1);

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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

		t1 = new OpersExpr("027 (NEW UpCore/Val MANSelected1)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexF, "Core",
				"Core");

		semExpr.add(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);
		variantOptSubOperNormal.addSemanticExpression(t1);
		// voidModelSubOperNormal.addSemanticExpression(t1);
		// validProductSubOperNormal.addSemanticExpression(t1);
		// validPartialConfSubOperNormal.addSemanticExpression(t1);
		// allProductsSubOperNormal.addSemanticExpression(t1);
		// numProductsSubOperNormal.addSemanticExpression(t1);
		// filterSubOperNormal.addSemanticExpression(t1);
		// verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionToVerify.addSemanticExpression(t1);
		// homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		// homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		// commonalityOperSubActionNormal1.addSemanticExpression(t1);
		// commonalityOperSubActionNormal2.addSemanticExpression(t1);
		// variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		// degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("026 (NEW Ver/Val MANNotAvailable)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexF, "Exclu",
				"Exclu");

		semExpr.add(t1);

		// voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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

		t1 = new OpersExpr(
				"031 (NEW NoLoop structValMan)",
				"To eliminate the structural loop remove this structural relation (mandatory between "
						+ "#source# and #target#) or remove another relation with error mark.",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "structVal", true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionMVRelaxable.addSemanticExpression(t1);
		sasverNoLoopsOperSubActionRedNormal.addSemanticExpression(t1);
		sasverNoLoopsOperSubActionRedToVerify.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "structVal", 1);

		// FIXME include oStructVal in FeatureOT, change structVal, move to OT

		t1 = new OpersExpr("033 (NEW structValReq for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instDirFeaFeatVertSemEdge,
				instDirFeaFeatVertSemEdge, "pOutAnaSel", 0);

		semExpr.add(t1);
		ecrOperSubActionNormal1.addSemanticExpression(t1);

		t1 = new OpersExpr("032 (NEW structValMan for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instDirFeaFeatVertSemEdge,
				instVertexF, instDirFeaFeatVertSemEdge, "inAnaSel",
				"pOutAnaSel");

		semExpr.add(t1);
		rootSubOperNormal.addSemanticExpression(t1);

		// FIXME simplify, reduce 3 fisrt expressions to 1
		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "inAnaSel", 0);

		t2 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "tmpAnaSel", 0);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				instDirFeaFeatVertSemEdge, t1, t2);

		t1 = new OpersExpr("#structValMan for Analysis", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.RIGHTVARIABLE, instDirFeaFeatVertSemEdge,
				instDirFeaFeatVertSemEdge, "pTmpAnaSel", false, t1);

		t2 = new OpersExpr("2", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "outAnaSel", 0);

		t1 = new OpersExpr("034 (NEW structValMan for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirFeaFeatVertSemEdge, t2, t1);

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("#structValMan for Analysis", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instDirFeaFeatVertSemEdge,
				instDirFeaFeatVertSemEdge, "pTmpAnaSel", 0);

		t2 = new OpersExpr("2", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "outAnaSel", 1);

		t1 = new OpersExpr("035 (NEW structValMan for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirFeaFeatVertSemEdge, t2, t1);

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("mandatory", new ElemAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("030 (NEW Ver/Val OPTSelected)", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexF, "Sel",
				"Sel");

		semExpr.add(t1);

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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

		t1 = new OpersExpr("027 (bNEW VerPar OPTSelected)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexF, "Core",
				"Core");

		semExpr.add(t1);
		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("029 (NEW Ver/Val OPTNotAvailable)", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, instVertexF, "Exclu",
				"Exclu");

		semExpr.add(t1);

		// voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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

		t1 = new OpersExpr(
				"031 (bNEW NoLoop structValOpt)",
				"To eliminate the structural loop remove this structural relation (mandatory between "
						+ "#source# and #target#) or remove another relation with error mark.",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "structVal", true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionMVRelaxable.addSemanticExpression(t1);
		sasverNoLoopsOperSubActionRedNormal.addSemanticExpression(t1);
		sasverNoLoopsOperSubActionRedToVerify.addSemanticExpression(t1);

		t1 = new OpersExpr("033 (bNEW structValReq for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instDirFeaFeatVertSemEdge,
				instDirFeaFeatVertSemEdge, "pOutAnaSel", 0);

		semExpr.add(t1);
		ecrOperSubActionNormal1.addSemanticExpression(t1);

		t1 = new OpersExpr("032 (bNEW structValOpt for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instDirFeaFeatVertSemEdge,
				instVertexF, instDirFeaFeatVertSemEdge, "inAnaSel",
				"pOutAnaSel");

		semExpr.add(t1);
		rootSubOperNormal.addSemanticExpression(t1);

		// FIMXE simplify
		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "inAnaSel", 0);

		t2 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "tmpAnaSel", 0);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				instDirFeaFeatVertSemEdge, t1, t2);

		t1 = new OpersExpr("#### structValMan for Analysis", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.RIGHTVARIABLE, instDirFeaFeatVertSemEdge,
				instDirFeaFeatVertSemEdge, "pTmpAnaSel", false, t1);

		t2 = new OpersExpr("2", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "outAnaSel", 0);

		t1 = new OpersExpr("034 (bNEW structValMan for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirFeaFeatVertSemEdge, t2, t1);

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("structValMan for Analysis", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instDirFeaFeatVertSemEdge,
				instDirFeaFeatVertSemEdge, "pTmpAnaSel", 0);

		t2 = new OpersExpr("2", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "outAnaSel", 1);

		t1 = new OpersExpr("035 (bNEW structValMan for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirFeaFeatVertSemEdge, t2, t1);

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexF, "structVal", 1);

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

		attribute = new ElemAttribute("pOutAnaSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Out Selection of Analysis oper", "", 1, 0, 10, "false", "",
				-1, "", "");

		directFeatFeatSideSemEdge.putSemanticAttribute("pOutAnaSel", attribute);
		rootSubOperationAction.addOutAttribute(new OpersIOAttribute(
				directFeatFeatSideSemEdge.getIdentifier(), attribute.getName(),
				true));
		rootOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeatFeatSideSemEdge.getIdentifier(), attribute.getName(),
				true));
		ecrSubOperationAction1.addOutAttribute(new OpersIOAttribute(
				directFeatFeatSideSemEdge.getIdentifier(), attribute.getName(),
				true));

		attribute = new ElemAttribute("pTmpAnaSel", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "Selected for analysis",
				"", 0, new RangeDomain(0, 400, 0), 0, 10, "", "", -1,
				"level#all#", "");
		directFeatFeatSideSemEdge.putSemanticAttribute("pTmpAnaSel", attribute);
		lcaSubOperationAction.addOutAttribute(new OpersIOAttribute(
				directFeatFeatSideSemEdge.getIdentifier(), attribute.getName(),
				true));
		lcaOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeatFeatSideSemEdge.getIdentifier(), attribute.getName(),
				true));

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

		t1 = new OpersExpr("036 (NEW Ver/Val CONFSelected)", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				instDirFeatFeatSideSemEdge, 1, false, t1);

		semExpr.add(t1);

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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
		wrongCardOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("039 (NEW structValReq for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instDirFeatFeatSideSemEdge,
				instDirFeatFeatSideSemEdge, "pOutAnaSel", 1);

		semExpr.add(t1);
		ecrOperSubActionNormal1.addSemanticExpression(t1);

		t1 = new OpersExpr("038 (NEW structValReq for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instDirFeatFeatSideSemEdge,
				instDirFeatFeatSideSemEdge, "pOutAnaSel", 0);

		semExpr.add(t1);
		rootSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("040 (NEW structValReq for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instDirFeatFeatSideSemEdge,
				instDirFeatFeatSideSemEdge, "pTmpAnaSel", 0);

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("excludes", new ElemAttribute("excludes",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "excludes",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("037 (NEW Ver/Val - requiresAltFeat)", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeatFeatSideSemEdge, instVertexF, instVertexF, "Sel",
				"Sel");

		semExpr.add(t1);

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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
		wrongCardOperSubActionNormal.addSemanticExpression(t1);

		// t1 = new OpersExpr("requires",
		// refas.getSemanticExpressionTypes().get(
		// "LessOrEquals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
		// instVertexF, "Core", "Core");
		//
		// semExpr.add(t1);

		// updCoreOptSubOperNormal.addSemanticExpression(t1);

		// t1 = new OpersExpr("requires",
		// refas.getSemanticExpressionTypes().get(
		// "LessOrEquals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
		// instVertexF, "Core", "Core");
		//
		// semExpr.add(t1);

		// updCoreOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("039 (bNEW structValReq for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instDirFeatFeatSideSemEdge,
				instDirFeatFeatSideSemEdge, "pOutAnaSel", 1);

		semExpr.add(t1);
		ecrOperSubActionNormal1.addSemanticExpression(t1);

		t1 = new OpersExpr("038 (bNEW structValReq for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instDirFeatFeatSideSemEdge,
				instDirFeatFeatSideSemEdge, "pOutAnaSel", 0);

		semExpr.add(t1);
		rootSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("040 (bNEW structValReq for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instDirFeatFeatSideSemEdge,
				instDirFeatFeatSideSemEdge, "pTmpAnaSel", 0);

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

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

		t1 = new OpersExpr("NA ANDFGrSelConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexFFGR,
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

		t1 = new OpersExpr("026 (O remove ANDFeGrCoreConcept)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexFFGR,
				instVertexF, instVertexFFGR, "Core", "OCore");

		// updCoreOptSubOperNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexF,
				instVertexF, "Sel", true, "TrueVal");

		t1 = new OpersExpr("042 (NEW Ver/Val - ANDFSRel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexFFGR, t1, "OSel");

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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

		t1 = new OpersExpr("041 (NEW VerPar/UpCore -  ANDFCRel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexFFGR, t1, "OCore");

		verifParentsOperSubActionNormal.addSemanticExpression(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);
		variantOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("NA ORFConcept", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexFFGR,
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

		t1 = new OpersExpr("043 (NEW Ver/Val - ORFSRel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, t1, "OSel");

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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
		t1 = new OpersExpr("041 (bNEW VerPar - ORFCRel)", refas
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

		t1 = new OpersExpr("NA  MUTEXFConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexFFGR,
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

		t1 = new OpersExpr("044 (NEW Ver/Val - MUTEXFRel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexFFGR, instVertexFFGR, "OSel", true, t1);

		// FIXME review if instVertexFFGR or Feature

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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
		t1 = new OpersExpr("041 (cNEW MUTEXFCRel)", refas
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

		t1 = new OpersExpr("045 (NEW Ver/Val - MUTEXrestric)", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, t1, 1);

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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

		t1 = new OpersExpr("NA RANGEFeatConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexFFGR,
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
				instVertexF, null, "Sel", 0, true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, instVertexFFGR, t1, instVertexFFGR, "LowRange");
		// FIXME v1.1 copy change to new version Luisa

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, null, "Sel", 0, true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, instVertexFFGR, t2, instVertexFFGR, "HighRange");
		// FIXME v1.1 copy change to new version Luisa

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexFFGR, t1, t2);

		t1 = new OpersExpr("046 (NEW Ver/Val - RANGEFeatRel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexFFGR, instVertexFFGR, "OSel", true, t1);

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				DefaultOpersMM.instVertexF, "Sel", true, 0);

		t1 = new OpersExpr("incon", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, DefaultOpersMM.instVertexF, t1, 1);

		t1 = new OpersExpr("051 (NEW Ver/Val RANGEHardRel)", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexFFGR,
				instVertexFFGR, "OSel", false, t1);

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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
		t1 = new OpersExpr("041 (dNEW VerPar - RANGEFCRel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexFFGR, t1, "OCore");

		verifParentsOperSubActionNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("L2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, null, "TrueVal", 0, true);

		t1 = new OpersExpr("L1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, instVertexFFGR, t1, instVertexFFGR, "LowRange");

		t2 = new OpersExpr("R2", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, null, "Core", "TrueVal", true);

		t2 = new OpersExpr("R1", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexFFGR, instVertexFFGR, t2, instVertexFFGR, "OCore");

		t1 = new OpersExpr("048 (NEW UpCore - ANDFCRel)", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexFFGR,
				t1, t2);

		updCoreOptSubOperNormal.addSemanticExpression(t1);
		variantOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("R2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, null, "Sel", 0, true);

		t1 = new OpersExpr("049 (NEW wrong card low)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexFFGR, t2, instVertexFFGR, "LowRange", "This relation"
						+ " has a wrong cardinality in the LowRange value");

		wrongCardOperSubActionRelaxable.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("R2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexFFGR,
				instVertexF, null, "Sel", 0, true);

		t1 = new OpersExpr("050 (NEW wrong card high)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexFFGR,
				instVertexFFGR, t2, instVertexFFGR, "HighRange",
				"This relation"
						+ " has a wrong cardinality in the HighRange value");

		wrongCardOperSubActionRelaxable.addSemanticExpression(t1);
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

		OpersConcept defaultFeatureToFeatureOT = new OpersConcept(
				"FeatureToFeatureOT");

		// attribute = new ElemAttribute("pOutAnaSel", "Boolean",
		// AttributeType.EXECCURRENTSTATE, false,
		// "CrossRelations identification", "", 0, 0, 10, "false", "", -1,
		// "", "");
		//
		// defaultFeatureToFeatureOT.putSemanticAttribute("pOutAnaSel",
		// attribute);
		// rootSubOperationAction.addOutAttribute(new OpersIOAttribute(
		// defaultFeatureToFeatureOT.getIdentifier(), attribute.getName(),
		// true));
		// rootOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// defaultFeatureToFeatureOT.getIdentifier(), attribute.getName(),
		// true));
		// ecrSubOperationAction1.addOutAttribute(new OpersIOAttribute(
		// defaultFeatureToFeatureOT.getIdentifier(), attribute.getName(),
		// true));
		// ecrOperUniqueLabeling1.addAttribute(new OpersIOAttribute(
		// defaultFeatureToFeatureOT.getIdentifier(), attribute.getName(),
		// true));

		InstConcept instFeatFeatFFFGR = new InstConcept("FeatureToFeatureOT",
				metaMetaPairwiseRelation, defaultFeatureToFeatureOT);
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

		OpersConcept directFeaParentOTSemEdge = new OpersConcept(
				"ParentFeatureOTToFeature");

		attribute = new ElemAttribute("pTmpAnaSel", "Integer",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected from Analysis Operation", "", 0, new RangeDomain(0,
						400, 0), 0, 10, "", "", -1, "pTmpAnaSel#all#", "");
		directFeaParentOTSemEdge.putSemanticAttribute("pTmpAnaSel", attribute);
		lcaSubOperationAction.addOutAttribute(new OpersIOAttribute(
				directFeaParentOTSemEdge.getIdentifier(), attribute.getName(),
				true));
		lcaOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaParentOTSemEdge.getIdentifier(), attribute.getName(),
				true));

		attribute = new ElemAttribute("pOutAnaSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected from Analysis Operation", "", false, 0, 10, "", "",
				-1, "level#all#", "");
		directFeaParentOTSemEdge.putSemanticAttribute("pOutAnaSel", attribute);
		rootSubOperationAction.addOutAttribute(new OpersIOAttribute(
				directFeaParentOTSemEdge.getIdentifier(), attribute.getName(),
				true));
		rootOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaParentOTSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverNoLoopsOperationSubActionMV.addOutAttribute(new OpersIOAttribute(
				directFeaParentOTSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(directFeaParentOTSemEdge
						.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperMVUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaParentOTSemEdge.getIdentifier(), attribute.getName(),
				true));
		sasverNoLoopsOperRedUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaParentOTSemEdge.getIdentifier(), attribute.getName(),
				true));
		ecrSubOperationAction1.addOutAttribute(new OpersIOAttribute(
				directFeaParentOTSemEdge.getIdentifier(), attribute.getName(),
				true));

		InstConcept instParFeatFeatFGRF = new InstConcept(
				"ParentFeatureOTToFeature", metaMetaPairwiseRelation,
				directFeaParentOTSemEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ffpotptoip", instEdge);
		instEdge.setIdentifier("ffpotptoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instParFeatFeatFGRF, true);
		refas.getVariabilityVertex().put("ParentFeatureOTToFeature",
				instParFeatFeatFGRF);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("parfeatfeatFFFGR-F", instEdge);
		instEdge.setIdentifier("parfeatfeatFFFGR-F");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instParFeatFeatFGRF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("parfeatfeatFFGR-FFFGR", instEdge);
		instEdge.setIdentifier("parfeatfeatFFGR-FFFGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instParFeatFeatFGRF, true);
		instEdge.setSourceRelation(instVertexFFGR, true);

		ia = instParFeatFeatFGRF.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("mandatory", new ElemAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", "", 1, -1, "", "", -1, "", ""),
				"mandatory#mandatory#true#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("optional", new ElemAttribute("optional",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "optional",
				"", "", 1, -1, "", "", -1, "", ""),
				"optional#optional#false#true#true#1#-1#1#1"));

		ia = instParFeatFeatFGRF.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("055 (NNEW MANNotAvailable)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instParFeatFeatFGRF, instVertexFFGR, instVertexF, "Exclu",
				"Exclu");

		semExpr.add(t1);

		// voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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

		t1 = new OpersExpr("056 (NNEW UpCore/Val MANSelected1)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instParFeatFeatFGRF, instVertexFFGR, instVertexF, "OCore",
				"Core");

		semExpr.add(t1);
		updCoreOptSubOperNormal.addSemanticExpression(t1);
		variantOptSubOperNormal.addSemanticExpression(t1);
		// voidModelSubOperNormal.addSemanticExpression(t1);
		// validProductSubOperNormal.addSemanticExpression(t1);
		// validPartialConfSubOperNormal.addSemanticExpression(t1);
		// allProductsSubOperNormal.addSemanticExpression(t1);
		// numProductsSubOperNormal.addSemanticExpression(t1);
		// filterSubOperNormal.addSemanticExpression(t1);
		// variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		// degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("057 (NNEW Ver/val - MANSelected)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instParFeatFeatFGRF, instVertexFFGR, instVertexF, "OSel", "Sel");

		semExpr.add(t1);

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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

		// FIXME test

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instParFeatFeatFGRF, instVertexF, "structVal", 1);

		t1 = new OpersExpr(
				"060 (NNEW NoLoop structValMan)",
				"To eliminate the structural loop remove this structural relation (mandatory between "
						+ "#source# and #target#) or remove another relation with error mark.",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instParFeatFeatFGRF, instVertexFFGR, "oStructVal", true, t1);

		semExpr.add(t1);
		sasverNoLoopsOperSubActionMVRelaxable.addSemanticExpression(t1);
		sasverNoLoopsOperSubActionRedToVerify.addSemanticExpression(t1);

		t1 = new OpersExpr("061 (NNEW oStructValMan for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instParFeatFeatFGRF,
				instVertexFFGR, instParFeatFeatFGRF, "oOutAnaSel", "pOutAnaSel");

		semExpr.add(t1);
		rootSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instParFeatFeatFGRF, instVertexFFGR, "oTmpAnaSel", 0);

		t1 = new OpersExpr("062 (NNEW structValMan for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instParFeatFeatFGRF,
				instParFeatFeatFGRF, "pTmpAnaSel", true, t1);

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("mandatory", new ElemAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("058 (NNEW Ver/Val OPTNotAvailable)", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
				instParFeatFeatFGRF, instVertexF, instVertexFFGR, "Exclu",
				"Exclu");

		semExpr.add(t1);

		// voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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

		t1 = new OpersExpr("059 (NNEW Ver/Val OPTSelected)", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instParFeatFeatFGRF, instVertexFFGR, instVertexF, "OSel", "Sel");

		semExpr.add(t1);

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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

		t1 = new OpersExpr("061 (bNEW oStructValOpt for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instParFeatFeatFGRF,
				instVertexFFGR, instParFeatFeatFGRF, "oOutAnaSel", "pOutAnaSel");

		semExpr.add(t1);
		rootSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("062 (bNEW structValMan for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instParFeatFeatFGRF,
				instVertexFFGR, instParFeatFeatFGRF, "oTmpAnaSel", "pTmpAnaSel");

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("056 (bNNEW VerPar OPTSelected)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexFFGR, instVertexF,
				"OCore", "Core");

		semExpr.add(t1);
		verifParentsOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr(
				"057 (bNEW NoLoop structValOpt)",
				"To eliminate the structural loop remove this structural relation (mandatory between "
						+ "#source# and #target#) or remove another relation with error mark.",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirFeaFeatVertSemEdge, instVertexFFGR, "structVal", true,
				t1);

		semExpr.add(t1);
		// sasverNoLoopsOperSubActionMVRelaxable.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionRedToVerify.addSemanticExpression(t1);

		ias.add(new InstAttribute("optional", new ElemAttribute("optional",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "optional",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		OpersConcept directFeaCrossTreeOTSemEdge = new OpersConcept(
				"CrossFeatureOTToFeature");

		attribute = new ElemAttribute("pTmpAnaSel", "Integer",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected from Analysis Operation", "", 0, new RangeDomain(0,
						400, 0), 0, 10, "", "", -1, "pTmpAnaSel#all#", "");
		directFeaCrossTreeOTSemEdge.putSemanticAttribute("pTmpAnaSel",
				attribute);
		lcaSubOperationAction.addOutAttribute(new OpersIOAttribute(
				directFeaCrossTreeOTSemEdge.getIdentifier(), attribute
						.getName(), true));
		lcaOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaCrossTreeOTSemEdge.getIdentifier(), attribute
						.getName(), true));

		attribute = new ElemAttribute("pOutAnaSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false,
				"Selected from Analysis Operation", "", false, 0, 10, "", "",
				-1, "level#all#", "");
		directFeaCrossTreeOTSemEdge.putSemanticAttribute("pOutAnaSel",
				attribute);
		rootSubOperationAction.addOutAttribute(new OpersIOAttribute(
				directFeaCrossTreeOTSemEdge.getIdentifier(), attribute
						.getName(), true));
		rootOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaCrossTreeOTSemEdge.getIdentifier(), attribute
						.getName(), true));
		sasverNoLoopsOperationSubActionMV.addOutAttribute(new OpersIOAttribute(
				directFeaCrossTreeOTSemEdge.getIdentifier(), attribute
						.getName(), true));
		sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(directFeaParentOTSemEdge
						.getIdentifier(), attribute.getName(), true));
		sasverNoLoopsOperMVUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaCrossTreeOTSemEdge.getIdentifier(), attribute
						.getName(), true));
		sasverNoLoopsOperRedUniqueLabeling.addAttribute(new OpersIOAttribute(
				directFeaCrossTreeOTSemEdge.getIdentifier(), attribute
						.getName(), true));
		ecrSubOperationAction1.addOutAttribute(new OpersIOAttribute(
				directFeaCrossTreeOTSemEdge.getIdentifier(), attribute
						.getName(), true));

		InstConcept instCrossFeatFeatFGRF = new InstConcept(
				"CrossFeatureOTToFeature", metaMetaPairwiseRelation,
				directFeaCrossTreeOTSemEdge);
		refas.getVariabilityVertex().put("CrossFeatureOTToFeature",
				instCrossFeatFeatFGRF);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ffctotptoip", instEdge);
		instEdge.setIdentifier("ffctotptoip");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instNmMetaPW, true);
		instEdge.setSourceRelation(instCrossFeatFeatFGRF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("crossfeatfeatFFFGR-F", instEdge);
		instEdge.setIdentifier("crossfeatfeatFFFGR-F");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instCrossFeatFeatFGRF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("crossfeatfeatFFGR-FFFGR", instEdge);
		instEdge.setIdentifier("crossfeatfeatFFGR-FFFGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instCrossFeatFeatFGRF, true);
		instEdge.setSourceRelation(instVertexFFGR, true);

		ia = instCrossFeatFeatFGRF.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("excludes", new ElemAttribute("excludes",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "excludes",
				"", "", 1, -1, "", "", -1, "", ""),
				"excludes#excludes#false#true#true#0#-1#0#-1"));

		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", "", 1, -1, "", "", -1, "", ""),
				"require#require#false#true#true#0#-1#0#-1"));

		ia = instCrossFeatFeatFGRF.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instCrossFeatFeatFGRF, instVertexFFGR, instVertexF, "OSel",
				"Sel");

		t1 = new OpersExpr("063 (NEW Ver/Val OSel Sel)", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				instCrossFeatFeatFGRF, 1, false, t1);

		semExpr.add(t1);

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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
		wrongCardOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("065 (NEW Val - Exclu = False)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instCrossFeatFeatFGRF, instVertexFFGR, instVertexF, "Exclu",
				"FalseVal");

		// voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		// redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("066 (NEW structValExclu for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instCrossFeatFeatFGRF,
				instCrossFeatFeatFGRF, "pOutAnaSel", 0);

		semExpr.add(t1);
		rootSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("067 (NEW structValExclu for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instCrossFeatFeatFGRF,
				instCrossFeatFeatFGRF, "pTmpAnaSel", 0);

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("excludes", new ElemAttribute("excludes",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "excludes",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("064 (NEW Ver/Val -  requiresAltFeat)", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instCrossFeatFeatFGRF, instVertexFFGR, instVertexF, "OSel",
				"Sel");

		semExpr.add(t1);

		voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionToVerify.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		verifDeadElemSubOperNormal.addSemanticExpression(t1);
		condDeadElemSubOperNormal.addSemanticExpression(t1);
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
		wrongCardOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("066 (bNEW structValReq for Analysis for OT)", "",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instCrossFeatFeatFGRF,
				instCrossFeatFeatFGRF, "pOutAnaSel", 0);

		semExpr.add(t1);
		rootSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("067 (bNEW structValExclu for Analysis)", "", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTVARIABLE, instCrossFeatFeatFGRF,
				instCrossFeatFeatFGRF, "pTmpAnaSel", 0);

		semExpr.add(t1);
		lcaSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("065 (bNEW Var - Exclu = False)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFeatFeatSideSemEdge, instVertexFFGR, instVertexF,
				"Exclu", "FalseVal");

		// voidModelSubOperNormal.addSemanticExpression(t1);
		validProductSubOperNormal.addSemanticExpression(t1);
		validPartialConfSubOperNormal.addSemanticExpression(t1);
		allProductsSubOperNormal.addSemanticExpression(t1);
		numProductsSubOperNormal.addSemanticExpression(t1);
		filterSubOperNormal.addSemanticExpression(t1);
		verifFalsePLOperSubActionNormal.addSemanticExpression(t1);
		redundanOperSubActionNormal.addSemanticExpression(t1);
		homogeneityOperSubActionNormal1.addSemanticExpression(t1);
		homogeneityOperSubActionNormal2.addSemanticExpression(t1);
		commonalityOperSubActionNormal1.addSemanticExpression(t1);
		commonalityOperSubActionNormal2.addSemanticExpression(t1);
		variabfactorOperSubActionNormal1.addSemanticExpression(t1);
		degreeOrthoOperSubActionNormal1.addSemanticExpression(t1);
		simulExecOptSubOperNormal.addSemanticExpression(t1);
		simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

	}
}
