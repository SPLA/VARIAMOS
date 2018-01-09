package com.variamos.dynsup.defaultmodels;

import java.util.ArrayList;
import java.util.List;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.model.ModelExpr;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.OpersExpr;
import com.variamos.dynsup.model.OpersIOAttribute;
import com.variamos.dynsup.statictypes.SatisficingType;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.ExpressionVertexType;
import com.variamos.dynsup.types.StringType;
import com.variamos.hlcl.model.domains.RangeDomain;

public class DefaultRefasMM {

	private static InstConcept instVertexOper;

	@SuppressWarnings("unchecked")
	static void createREFASMetaModel(InstanceModel refas) {

		ArrayList<OpersExpr> semExpr = new ArrayList<OpersExpr>();

		ElemAttribute attribute = null;

		OpersConcept semAssumption = new OpersConcept("Assumption");
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				semAssumption.getIdentifier(), "Sel", true));

		InstConcept instVertexAS = new InstConcept("Assumption",
				DefaultOpersMM.metaMetaInstConcept, semAssumption);
		refas.getVariabilityVertex().put("Assumption", instVertexAS);

		InstPairwiseRel instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assutoge", instEdge);
		instEdge.setIdentifier("assutoge");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexHC, true);
		instEdge.setSourceRelation(instVertexAS, true);

		OpersConcept semGoal = new OpersConcept("Goal");

		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				semGoal.getIdentifier(), "Sel", true));
		attribute = new ElemAttribute("satType", "Enumeration",
				AttributeType.OPERATION, false, "satType", "",
				"com.variamos.dynsup.statictypes.SatisfactionType", "achieve",
				"", 0, 1, "", "", 1, "<#" + "satType" + "#all#>\n", "");
		semGoal.putSemanticAttribute("satType", attribute);
		// semGoal.addPanelVisibleAttribute("01#" + "satType");
		// semGoal.addPanelSpacersAttribute("<#" + "satType" + "#>\n");
		InstConcept instVertexG = new InstConcept("Goal",
				DefaultOpersMM.metaMetaInstConcept, semGoal);
		refas.getVariabilityVertex().put("Goal", instVertexG);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("gtoge", instEdge);
		instEdge.setIdentifier("gtoge");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexHC, true);
		instEdge.setSourceRelation(instVertexG, true);

		OpersConcept semOperationalization = new OpersConcept(
				"Operationalization");
		attribute = new ElemAttribute("attributeValue", "Set",
				AttributeType.SYNTAX, false, "values", "",
				InstAttribute.class.getCanonicalName(), "Variable",
				new ArrayList<InstAttribute>(), null, "", 0, 6, "", "", 6,
				"#attributeValue#2#\n", "");
		semOperationalization.putSemanticAttribute("attributeValue", attribute);
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		semOperationalization.addPropVisibleAttribute("06#" + "attributeValue");
		semOperationalization
				.addPropEditableAttribute("06#" + "attributeValue");

		DefaultOpersMM.simsceExecOperLabeling1
				.addAttribute(new OpersIOAttribute(semOperationalization
						.getIdentifier(), "Sel", true));

		instVertexOper = new InstConcept("Operationalization",
				DefaultOpersMM.metaMetaInstConcept, semOperationalization);
		refas.getVariabilityVertex().put("Operationalization", instVertexOper);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("opertoge", instEdge);
		instEdge.setIdentifier("opertoge");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexHC, true);
		instEdge.setSourceRelation(instVertexOper, true);

		semExpr = new ArrayList<OpersExpr>();

		semOperationalization.setSemanticExpressions(semExpr);

		OpersExpr t1 = new OpersExpr("061 Ver CL/SD - sel==true", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexOper,
				instVertexOper, instVertexOper, "Sel", "TrueVal");

		DefaultOpersMM.sasverConflClOperSubActionVerification
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionVerification
				.addSemanticExpression(t1);

		semExpr.add(t1);

		OpersConcept semSoftgoal = new OpersConcept("Softgoal");

		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), "Sel", true));

		attribute = new ElemAttribute("satisficingLevel", "String",
				AttributeType.OPERATION, "Satisficing Level",
				"Satisficing for dynamic operations (low/high/close)", "high",
				false, null, 0, 11, "", "", -1, "", "");

		semSoftgoal.putSemanticAttribute("satisficingLevel", attribute);
		DefaultOpersMM.verifDeadElemSubOperationAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.verifDeadElemOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.verifFalseOptSubOperationAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.verifFalseOptElemOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));

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
		DefaultOpersMM.verifDeadElemSubOperationAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.verifDeadElemOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.verifFalseOptSubOperationAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.verifFalseOptElemOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));

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
		// DefaultOpersMM.sasverClCoreOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.sasverClCoreOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverClallOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverClallOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.sasverClneverOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.sasverClneverOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.sasverCoreOpersOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.sasverCoreOpersOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.sasverAllOpersOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.sasverAllOpersOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// // sasverNoLoopsOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// // semSoftgoal.getIdentifier(), attribute.getName(), true));
		// // sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// // semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.sasverSGConflOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.DefaultOpersMM.sasverSGConflOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.sasverConflClSDOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.sasverConflClSDOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.sasverConflClOperationSubAction.addInAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// DefaultOpersMM.sasverConflClOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverConflSDOperationSubAction.addInAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverConflSDOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));

		semExpr = new ArrayList<OpersExpr>();

		semSoftgoal.setSemanticExpressions(semExpr);

		DefaultOpersMM.instVertexSG = new InstConcept("Softgoal",
				DefaultOpersMM.metaMetaInstConcept, semSoftgoal);

		OpersExpr t3 = new OpersExpr("22", refas.getSemanticExpressionTypes()
				.get("NotEquals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "ConfigReqLevel", true, 5);

		t1 = new OpersExpr("4", refas.getSemanticExpressionTypes()
				.get("Equals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "satisficingLevel", "close");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				DefaultOpersMM.instVertexSG, t1, t3);

		t1 = new OpersExpr("26", refas.getSemanticExpressionTypes().get(
				"Equals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, DefaultOpersMM.instVertexSG,
				"ConfigReqLevel", "SDReqLevel");

		t1 = new OpersExpr("064 Ver/Val - ConfReqLev...", refas
				.getSemanticExpressionTypes().get("Implies"),
				DefaultOpersMM.instVertexSG, t3, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		semExpr.add(t1);

		t3 = new OpersExpr("23", refas.getSemanticExpressionTypes().get(
				"NotEquals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "ConfigReqLevel", true, 5);

		t1 = new OpersExpr("4", refas.getSemanticExpressionTypes()
				.get("Equals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "satisficingLevel", "low");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				DefaultOpersMM.instVertexSG, t1, t3);

		t1 = new OpersExpr("21", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, DefaultOpersMM.instVertexSG,
				"ConfigReqLevel", "SDReqLevel");

		t1 = new OpersExpr("066 Ver/Val - ConfReqLev...", refas
				.getSemanticExpressionTypes().get("Implies"),
				DefaultOpersMM.instVertexSG, t3, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		semExpr.add(t1);

		t3 = new OpersExpr("25", refas.getSemanticExpressionTypes().get(
				"NotEquals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "ConfigReqLevel", true, 5);

		t1 = new OpersExpr("4", refas.getSemanticExpressionTypes()
				.get("Equals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "satisficingLevel", "high");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				DefaultOpersMM.instVertexSG, t1, t3);

		t1 = new OpersExpr("20", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, DefaultOpersMM.instVertexSG,
				"ConfigReqLevel", "SDReqLevel");

		t1 = new OpersExpr("065 Ver/Val - ConfReqLev...", refas
				.getSemanticExpressionTypes().get("Implies"),
				DefaultOpersMM.instVertexSG, t3, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Subtraction"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexGE, "Sel", false, 1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"Product"), DefaultOpersMM.instVertexSG, 3, true, t1);

		t3 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Product"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexGE, "SimulSel", true, 2);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("Sum"),
				DefaultOpersMM.instVertexSG, t1, t3);

		// t1 = new OpersExpr("4",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// DefaultOpersMM.instVertexSG, instVertexGE, "TestConfSel", true, t1);

		t1 = new OpersExpr("067 Var - OrderSG...", refas
				.getSemanticExpressionTypes().get("Equals"),
				DefaultOpersMM.instVertexSG, DefaultOpersMM.instVertexGE,
				"Order", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, DefaultOpersMM.instVertexSG,
				"SDReqLevel", "ClaimExpLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "Sel", true, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "satisficingLevel", "high");

		t1 = new OpersExpr("069 Ver/Val - high: SDReqLevel<=ClaimExpLevel...",
				refas.getSemanticExpressionTypes().get("Implies"),
				DefaultOpersMM.instVertexSG, t3, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, DefaultOpersMM.instVertexSG,
				"SDReqLevel", "ClaimExpLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "Sel", true, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "satisficingLevel", "low");

		t1 = new OpersExpr("070 Ver/Val - low: SDReqLevel>=ClaimExpLevel",
				refas.getSemanticExpressionTypes().get("Implies"),
				DefaultOpersMM.instVertexSG, t3, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, DefaultOpersMM.instVertexSG,
				"SDReqLevel", "ClaimExpLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "Sel", true, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "satisficingLevel", "close");

		t1 = new OpersExpr("068 Ver/Val - close: SDReqLevel=ClaimExpLevel",
				refas.getSemanticExpressionTypes().get("Implies"),
				DefaultOpersMM.instVertexSG, t3, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		semExpr.add(t1);

		refas.getVariabilityVertex().put("Softgoal",
				DefaultOpersMM.instVertexSG);

		attribute = new ElemAttribute("SDReqLevel", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "Required Level by SD",
				"Required level (0..4) for the soft dependency relation", 0,
				new RangeDomain(0, 4, 0), 2, 16, "", "", -1, "", "",
				"ConfigReqLevel", "sourceLevel;targetLevel;level;CLSGLevel",
				"defaultDomainValue");
		semSoftgoal.putSemanticAttribute("SDReqLevel", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));

		attribute = new ElemAttribute("ClaimExpLevel", "Integer",
				AttributeType.EXECCURRENTSTATE, false,
				"Expected Level by Claim",
				"Expected level (0..4) for the claim relation", 0,
				new RangeDomain(0, 4, 0), 2, 18, "", "", -1, "", "",
				"ConfigReqLevel", "sourceLevel;targetLevel;level;CLSGLevel",
				"defaultDomainValue");
		semSoftgoal.putSemanticAttribute("ClaimExpLevel", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				semSoftgoal.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// semSoftgoal.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftgoal
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftgoal.getIdentifier(),
						attribute.getName(), true));

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
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexGE, true);
		instEdge.setSourceRelation(DefaultOpersMM.instVertexSG, true);

		// ---
		OpersConcept semanticOperClaimGroupRelation = new OpersConcept(
				"OperClaimN-ary");// hardSemOverTwoRelList);

		attribute = new ElemAttribute("structVal", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "No loops validation",
				"", 0, new RangeDomain(0, 40, 0), 0, -1, "false", "", -1, "",
				"");
		semanticOperClaimGroupRelation.putSemanticAttribute("structVal",
				attribute);
		DefaultOpersMM.sasverNoLoopsOperationSubActionMV
				.addOutAttribute(new OpersIOAttribute(
						semanticOperClaimGroupRelation.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(
						semanticOperClaimGroupRelation.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(
						semanticOperClaimGroupRelation.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperMVUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						semanticOperClaimGroupRelation.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperRedUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						semanticOperClaimGroupRelation.getIdentifier(),
						attribute.getName(), true));

		// ---

		OpersConcept semClaim = new OpersConcept("Claim");

		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), "Sel", true));

		attribute = new ElemAttribute("outCl", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		semClaim.putSemanticAttribute("outCl", attribute);
		DefaultOpersMM.sasverClallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));

		InstConcept instVertexCL = new InstConcept("Claim", semClaim,
				DefaultOpersMM.metaMetaInstOverTwoRel);

		semExpr = new ArrayList<OpersExpr>();

		semanticOperClaimGroupRelation.setSemanticExpressions(semExpr);

		InstConcept instVertexCLGR = new InstConcept("OperClaimN-ary",
				semanticOperClaimGroupRelation,
				DefaultOpersMM.metaMetaInstOverTwoRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("opclottoigr", instEdge);
		instEdge.setIdentifier("opclottoigr");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelOCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexCLGR, true);

		t1 = new OpersExpr("103 VerCl/SD osel ==true", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexCLGR,
				instVertexCLGR, instVertexCLGR, "OSel", "TrueVal");

		DefaultOpersMM.sasverConflClOperSubActionVerification
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionVerification
				.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("105 UpCore - ANDHCGrCoreConcept", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexCLGR,
				instVertexCL, instVertexCLGR, "Core", "OCore");

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		semExpr = new ArrayList<OpersExpr>();

		semClaim.setSemanticExpressions(semExpr);

		// t1 = new OpersExpr("sub1",
		// refas.getSemanticExpressionTypes().get("Or"),
		// ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCL,
		// instVertexOper, "Sel", true, "FalseVal");
		//
		// t1 = new OpersExpr("ORClRel1",
		// refas.getSemanticExpressionTypes().get(
		// "Or"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
		// instVertexCL, instVertexOper, t1, "FalseVal");
		//
		// OpersExpr t2 = new OpersExpr("sub2",
		// refas.getSemanticExpressionTypes()
		// .get("Or"), ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
		// instVertexCL, instVertexCLGR, "OSel", true, "FalseVal");
		//
		// t2 = new OpersExpr("ORClRel2",
		// refas.getSemanticExpressionTypes().get(
		// "Or"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
		// instVertexCL, instVertexCLGR, t2, "FalseVal");
		//
		// t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("Or"),
		// instVertexCL, t1, t2);
		//
		// t1 = new OpersExpr("090 VerCL  - outcl <=> osel or sel", refas
		// .getSemanticExpressionTypes().get("DoubleImplies"),
		// instVertexCL, instVertexCL, "outCl", false, t1);
		// FIXME expression not working, FIX the implementation to support this
		// expression

		t1 = new OpersExpr("sub2",
				refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCRELVARIABLE, instVertexCL,
				instVertexCLGR, "PSel", true, "FalseVal");

		t1 = new OpersExpr("ORClRel2", refas.getSemanticExpressionTypes().get(
				"Or"), ExpressionVertexType.LEFTITERINCRELVARIABLE,
				instVertexCL, instVertexCLGR, t1, "FalseVal");

		t1 = new OpersExpr("090 VerCL  - outcl <=> osel or sel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexCL, instVertexCL, "outCl", false, t1);

		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("093 VerCL outcl <=> sel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexCL, instVertexCL,
				instVertexCL, "Sel", "outCl");

		semExpr.add(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexCL, DefaultOpersMM.instVertexGE,
				"SimulSel", true, 2);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexCL, t1, 0);

		// t1 = new OpersExpr("4",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// instVertexCL, instVertexGE, "TestConfSel", true, t1);

		t1 = new OpersExpr("092 Val - OrderCl...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexCL,
				DefaultOpersMM.instVertexGE, "Order", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		OpersExpr t2 = new OpersExpr("4", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexCL, instVertexCL, "Sel", true, 0);

		t1 = new OpersExpr("4", refas.getSemanticExpressionTypes()
				.get("Equals"), instVertexCL, instVertexCL,
				"ConditionalExpression", true, 0);

		t1 = new OpersExpr("091 Ver/Val - No Cond - No sel", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexCL, t1,
				t2);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDCoreOperSubActionNormal
		// .addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		semExpr.add(t1);

		OpersConcept directOperClaimSemanticEdge = new OpersConcept(
				"OperClaimBinary");

		attribute = new ElemAttribute("outCl", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directOperClaimSemanticEdge.putSemanticAttribute("outCl", attribute);
		DefaultOpersMM.sasverClallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(directOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));

		InstConcept instDirOperClaimSemanticEdge = new InstConcept(
				"OperClaimBinary", DefaultOpersMM.metaMetaPairwiseRelation,
				directOperClaimSemanticEdge);

		attribute = new ElemAttribute("PSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***",
				"Element selected for this solution (green)", false, 2, -1, "",
				"", -1, "", "");

		directOperClaimSemanticEdge.putSemanticAttribute("PSel", attribute);

		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				instDirOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(instDirOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(instDirOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(instDirOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(instDirOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(instDirOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(instDirOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(instDirOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(instDirOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(instDirOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(instDirOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(instDirOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						instDirOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(instDirOperClaimSemanticEdge
						.getIdentifier(), attribute.getName(), true));

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
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(
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
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directOperClaimSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directOperClaimSemanticEdge.getIdentifier(), attribute
								.getName(), true));

		refas.getVariabilityVertex().put("OperClaimBinary",
				instDirOperClaimSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("opctoip", instEdge);
		instEdge.setIdentifier("opctoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

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
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("115 VerSAS/Val - OperCLEq", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE,
				instDirOperClaimSemanticEdge, instVertexCL,
				instDirOperClaimSemanticEdge, "Sel", "PSel");
		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexOper, instVertexCL,
				"Sel", "ConditionalExpression");

		t1 = new OpersExpr("117 VerCl - OPERCLSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexCL, "outCl", true, t1);

		semExpr.add(t1);
		// DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClneverOperSubActionNormal
		// .addSemanticExpression(t1);
		// sasverClCoreOperSubActionNormal.addSemanticExpression(t1);

		// t1 = new OpersExpr("CLOperExclu1Exclu", refas
		// .getSemanticExpressionTypes().get("And"),
		// ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
		// instDirOperClaimSemanticEdge, instVertexOper, instVertexCL,
		// "Exclu", "Exclu");

		t1 = new OpersExpr("116 Ver/Val - OPERCLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimSemanticEdge, instVertexOper, instVertexCL,
				"Exclu", "Exclu");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
				"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		semExpr = new ArrayList<OpersExpr>();

		refas.getVariabilityVertex().put("Claim", instVertexCL);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("cltoge", instEdge);
		instEdge.setIdentifier("cltoge");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelOCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexGE, true);
		instEdge.setSourceRelation(instVertexCL, true);

		attribute = new ElemAttribute(
				"ConditionalExpression",
				"Instance",
				AttributeType.OPERATION,
				false,
				"Conditional Expression",
				"Claim activation expression (in addition to operationalizations/left features)",
				ModelExpr.class.getCanonicalName(), "", "", 0, 3, "", "", -1,
				"#ConditionalExpression#all#", "");

		semClaim.putSemanticAttribute("ConditionalExpression", attribute);
		semClaim.addPropEditableAttribute("03#" + "ConditionalExpression");
		semClaim.addPropVisibleAttribute("03#" + "ConditionalExpression");

		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				semClaim.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addInAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addInAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addInAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addInAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semClaim.getIdentifier(),
						attribute.getName(), true));

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

		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), "Sel", true));
		InstConcept instVertexSD = new InstConcept("SoftDependency",
				semSoftDependency, DefaultOpersMM.metaMetaInstConcept);
		refas.getVariabilityVertex().put("SoftDependency", instVertexSD);

		semExpr = new ArrayList<OpersExpr>();

		semSoftDependency.setSemanticExpressions(semExpr);

		t1 = new OpersExpr("124 VerSD - sel <=> outSd", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTVARIABLE,
				ExpressionVertexType.RIGHTVARIABLE, instVertexSD, instVertexSD,
				instVertexSD, "Sel", "outSd");

		semExpr.add(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Product"), instVertexSD, DefaultOpersMM.instVertexGE,
				"SimulSel", true, 2);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("Sum"),
				instVertexSD, t1, 0);

		// t1 = new OpersExpr("4",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// instVertexSD, instVertexGE, "TestConfSel", true, t1);

		t1 = new OpersExpr("125 Val - OrderSD...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexSD,
				DefaultOpersMM.instVertexGE, "Order", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sdtoge", instEdge);
		instEdge.setIdentifier("sdtoge");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelOCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexGE, true);
		instEdge.setSourceRelation(instVertexSD, true);

		attribute = new ElemAttribute("outSd", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		semSoftDependency.putSemanticAttribute("outSd", attribute);
		DefaultOpersMM.sasverSDallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("ConditionalExpression", "Instance",
				AttributeType.OPERATION, false, "Conditional Expression",
				"Soft dependency activation expression",
				ModelExpr.class.getCanonicalName(), "", "", 0, 3, "", "", -1,
				"#ConditionalExpression#all#", "");

		semSoftDependency.putSemanticAttribute("ConditionalExpression",
				attribute);
		semSoftDependency.addPropEditableAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropVisibleAttribute("03#"
				+ "ConditionalExpression");

		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				semSoftDependency.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(semSoftDependency
						.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("CompExp", "Boolean",
				AttributeType.GLOBALCONFIG, false, "Boolean Comp. Expression",
				"", true, 2, 1, "", "", -1, "", "");
		semSoftDependency.putSemanticAttribute("CompExp", attribute);
		semSoftDependency.addPropEditableAttribute("01#" + "CompExp");
		semSoftDependency.addPropVisibleAttribute("01#" + "CompExp");
		// simulationExecOperUniqueLabeling.addAttribute(attribute);

		OpersConcept semHardOverTwoRelation = new OpersConcept("HardN-ary");// hardSemOverTwoRelList);

		attribute = new ElemAttribute("structVal", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "No loops validation",
				"", 0, new RangeDomain(0, 40, 0), 0, -1, "false", "", -1, "",
				"");
		semHardOverTwoRelation.putSemanticAttribute("structVal", attribute);
		DefaultOpersMM.sasverNoLoopsOperationSubActionMV
				.addOutAttribute(new OpersIOAttribute(semHardOverTwoRelation
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(semHardOverTwoRelation
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperMVUniqueLabeling
				.addAttribute(new OpersIOAttribute(semHardOverTwoRelation
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperRedUniqueLabeling
				.addAttribute(new OpersIOAttribute(semHardOverTwoRelation
						.getIdentifier(), attribute.getName(), true));

		InstConcept instVertexHHGR = new InstConcept("HardN-ary",
				semHardOverTwoRelation, DefaultOpersMM.metaMetaInstOverTwoRel);
		refas.getVariabilityVertex().put("HardN-ary", instVertexHHGR);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("hhtoigr", instEdge);
		instEdge.setIdentifier("hhtoigr");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelOCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexHHGR, true);

		// FIXME create two: one for means and one for MeansHardN-aryToHardersal
		// Copy the expressions from the PW definition
		// means 148 149 150 151 152 153 154 155 156 157
		// trav 158 159 160 161 162 163
		OpersConcept altHardNaryToHardEdge = new OpersConcept(
				"TravHardN-aryToHard");

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 3,
				"", "", 3, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		altHardNaryToHardEdge.putSemanticAttribute("AggregationLow", attribute);
		altHardNaryToHardEdge
				.addPropEditableAttribute("03#" + "AggregationLow");
		altHardNaryToHardEdge.addPropVisibleAttribute("03#" + "AggregationLow");
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				altHardNaryToHardEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				altHardNaryToHardEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(altHardNaryToHardEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(altHardNaryToHardEdge
						.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 4,
				"", "", 4, "#" + "AggregationHigh" + "#all#]\n",
				"AggregationHigh" + "#!=#" + "0");
		altHardNaryToHardEdge
				.putSemanticAttribute("AggregationHigh", attribute);
		altHardNaryToHardEdge.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		altHardNaryToHardEdge
				.addPropVisibleAttribute("04#" + "AggregationHigh");
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				altHardNaryToHardEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				altHardNaryToHardEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(altHardNaryToHardEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(altHardNaryToHardEdge
						.getIdentifier(), attribute.getName(), true));

		InstConcept instAltNaryToHard = new InstConcept("TravHardN-aryToHard",
				DefaultOpersMM.metaMetaPairwiseRelation, altHardNaryToHardEdge);
		refas.getVariabilityVertex().put("TravHardN-aryToHard",
				instAltNaryToHard);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("althhotfromip", instEdge);
		instEdge.setIdentifier("althhotfromip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instAltNaryToHard, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("althctoHHGR-HHGR-HHHHGR", instEdge);
		instEdge.setIdentifier("althctoHHGR-HHGR-HHHHGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instAltNaryToHard, true);
		instEdge.setSourceRelation(instVertexHHGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("althctoHHGR-HHHHGR-H", instEdge);
		instEdge.setIdentifier("althctoHHGR-HHHHGR-H");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexHC, true);
		instEdge.setSourceRelation(instAltNaryToHard, true);

		OpersConcept meansHardToHardNaryEdge = new OpersConcept(
				"MeansHardN-aryToHard");

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 3,
				"", "", 3, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		meansHardToHardNaryEdge.putSemanticAttribute("AggregationLow",
				attribute);
		meansHardToHardNaryEdge.addPropEditableAttribute("03#"
				+ "AggregationLow");
		meansHardToHardNaryEdge.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				meansHardToHardNaryEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				meansHardToHardNaryEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(meansHardToHardNaryEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(meansHardToHardNaryEdge
						.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 4,
				"", "", 4, "#" + "AggregationHigh" + "#all#]\n",
				"AggregationHigh" + "#!=#" + "0");
		meansHardToHardNaryEdge.putSemanticAttribute("AggregationHigh",
				attribute);
		meansHardToHardNaryEdge.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		meansHardToHardNaryEdge.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				meansHardToHardNaryEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				meansHardToHardNaryEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(meansHardToHardNaryEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(meansHardToHardNaryEdge
						.getIdentifier(), attribute.getName(), true));

		InstConcept instHchcHHGRHC = new InstConcept("MeansHardN-aryToHard",
				DefaultOpersMM.metaMetaPairwiseRelation,
				meansHardToHardNaryEdge);
		refas.getVariabilityVertex()
				.put("MeansHardN-aryToHard", instHchcHHGRHC);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("hhotfromip", instEdge);
		instEdge.setIdentifier("hhotfromip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instHchcHHGRHC, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("hctoHHGR-HHGR-HHHHGR", instEdge);
		instEdge.setIdentifier("hctoHHGR-HHGR-HHHHGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instHchcHHGRHC, true);
		instEdge.setSourceRelation(instVertexHHGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("hctoHHGR-HHHHGR-H", instEdge);
		instEdge.setIdentifier("hctoHHGR-HHHHGR-H");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexHC, true);
		instEdge.setSourceRelation(instHchcHHGRHC, true);

		OpersConcept fromHardHardSemanticEdge = new OpersConcept(
				"HardConceptToHardN-ary");

		attribute = new ElemAttribute("PSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***",
				"Element selected for this solution (green)", false, 2, -1, "",
				"", -1, "", "");

		fromHardHardSemanticEdge.putSemanticAttribute("PSel", attribute);

		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addOutAttribute(new OpersIOAttribute(fromHardHardSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addOutAttribute(new OpersIOAttribute(fromHardHardSemanticEdge
						.getIdentifier(), attribute.getName(), true));

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
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(fromHardHardSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(fromHardHardSemanticEdge
						.getIdentifier(), attribute.getName(), true));

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
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				fromHardHardSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(fromHardHardSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(fromHardHardSemanticEdge
						.getIdentifier(), attribute.getName(), true));

		InstConcept instHchcHHGRGR = new InstConcept("HardConceptToHardN-ary",
				DefaultOpersMM.metaMetaPairwiseRelation,
				fromHardHardSemanticEdge);

		refas.getVariabilityVertex().put("HardConceptToHardN-ary",
				instHchcHHGRGR);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("HHGRtohc-H-HHHHGR", instEdge);
		instEdge.setIdentifier("HHGRtohc-H-HHHHGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instHchcHHGRGR, true);
		instEdge.setSourceRelation(DefaultOpersMM.instVertexHC, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("HHGRtohc-HHHHGR-H", instEdge);
		instEdge.setIdentifier("HHGRtohc-H-HHHHGR-H");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexHHGR, true);
		instEdge.setSourceRelation(instHchcHHGRGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ogfottoip", instEdge);
		instEdge.setIdentifier("ogfottoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
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
		// DefaultOpersMM.instVertexHC, "Sel", "Sel");
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
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("DEFnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instHchcHHGRGR,
				DefaultOpersMM.instVertexHC, instHchcHHGRGR, "Sel", "TrueVal");

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("DEFSelSel", refas.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instHchcHHGRGR,
				DefaultOpersMM.instVertexHC, instHchcHHGRGR, "Sel", "TrueVal");

		t1 = new OpersExpr("046 Ver/Val - MANSel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTVARIABLE, instHchcHHGRGR,
				instHchcHHGRGR, "PSel", false, t1);

		semExpr.add(t1);

		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		// iterate onver multi-instances

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instHchcHHGRGR, DefaultOpersMM.instVertexHC, "Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instHchcHHGRGR, instHchcHHGRGR, "AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instHchcHHGRGR, DefaultOpersMM.instVertexHC, "Sel", true, 0);

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		// t1 = new OpersExpr("DEFCore1Core", refas.getSemanticExpressionTypes()
		// .get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,DefaultOpersMM.instVertexHC,
		// instHchcHHGRGR, "Core", "TrueVal");
		//
		// t1 = new OpersExpr("DEFCore1",
		// refas.getSemanticExpressionTypes().get(
		// "DoubleImplies"),
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instHchcHHGRGR,
		// DefaultOpersMM.instVertexHC, "Core", false, t1);
		//
		// semExpr.add(t1);
		// DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		// // simulExecOptSubOperNormal.addSemanticExpression(t1);
		// // simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		//
		// verifParentsOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MAExclu1Exclu", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instHchcHHGRGR,
				DefaultOpersMM.instVertexHC, instHchcHHGRGR, "Exclu", "TrueVal");

		t1 = new OpersExpr("044 Ver/Val DEFExclu", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instHchcHHGRGR,
				DefaultOpersMM.instVertexHC, "Exclu", false, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("2def", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instHchcHHGRGR, DefaultOpersMM.instVertexHC, "structVal", 1);

		t1 = new OpersExpr(
				"045 NoLoop - DEFStruc",
				"To eliminate the structural loop remove this structural relation (mandatory between "
						+ "#source# and #target#) or remove another relation with error mark.",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE, instHchcHHGRGR,
				DefaultOpersMM.instVertexHC, "structVal", true, t1);

		semExpr.add(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionMVRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedToVerify
				.addSemanticExpression(t1);

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
				DefaultOpersMM.instVertexHC, instVertexHHGR, "Core", "OCore");

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHHGR,
				DefaultOpersMM.instVertexHC, "Sel", true, "TrueVal");

		t1 = new OpersExpr("034 Ver/Val - ANDhardSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				DefaultOpersMM.instVertexHC, t1, "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		// FIXME Check the core propagation
		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"Core", true, "TrueVal");

		t1 = new OpersExpr("035 UpCore ANDhardCoreRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHHGR, t1, "OCore");

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("ORhardConcept",
		// refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHHGR,
		// DefaultOpersMM.instVertexHC, "Sel", "Sel");
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
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"Sel", true, "FalseVal");

		t1 = new OpersExpr("036 Ver/Val ORHCRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				DefaultOpersMM.instVertexHC, t1, "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		// FIXME Check the core propagation
		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"Core", true, "FalseVal");

		t1 = new OpersExpr("037 VerPar - ORhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHHGR, t1, "OCore");

		DefaultOpersMM.verifParentsOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("MUTEXhardConcept", refas
		// .getSemanticExpressionTypes().get("Equals"),
		// ExpressionVertexType.LEFTVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHHGR,
		// DefaultOpersMM.instVertexHC, "Sel", "Sel");
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
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"Sel", 0);

		t1 = new OpersExpr("sub2hcgrsel", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, DefaultOpersMM.instVertexHC, t1, 1);

		t1 = new OpersExpr("039 Ver/Val - MUTEXhardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexHHGR, DefaultOpersMM.instVertexHC, "Sel", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"Sel", 0);

		t1 = new OpersExpr("038 Ver/Val MUTEXrestric", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				DefaultOpersMM.instVertexHC, t1, 1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("RANGEhardConcept", refas
		// .getSemanticExpressionTypes().get("Equals"),
		// ExpressionVertexType.LEFTVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHHGR,
		// DefaultOpersMM.instVertexHC, "Sel", "Sel");
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
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		// FIXME support multi-instance

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"Sel", true, 0);

		t1 = new OpersExpr("incon", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, DefaultOpersMM.instVertexHC, t1, "LowRange");

		t2 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"Sel", true, 0);

		t2 = new OpersExpr("incon", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, DefaultOpersMM.instVertexHC, t2, "HighRange");

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexHHGR, t1, t2);

		t1 = new OpersExpr("040 Ver/Val RANGEHardRel", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexHHGR,
				instVertexHHGR, "OSel", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"Sel", true, 0);

		t1 = new OpersExpr("incon", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, DefaultOpersMM.instVertexHC, t1, 1);

		t1 = new OpersExpr("040xex Ver/Val RANGEHardRel", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexHHGR,
				instVertexHHGR, "OSel", false, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHHGR,
				DefaultOpersMM.instVertexHC, null, "TrueVal", 0, true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, instVertexHHGR, t1,
				DefaultOpersMM.instVertexHC, "LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHHGR,
				DefaultOpersMM.instVertexHC, null, "Core", "TrueVal", true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexHHGR, instVertexHHGR, t2,
				DefaultOpersMM.instVertexHC, "OCore");

		t1 = new OpersExpr("041 UpCore ANDFCRel", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexHHGR,
				t1, t2);

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("R2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHHGR,
				DefaultOpersMM.instVertexHC, null, "Sel", 0, true);

		t1 = new OpersExpr("#NEW wrong card low", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHHGR, t2, instVertexHHGR, "LowRange", "This relation"
						+ " has a wrong cardinality in the LowRange value");

		DefaultOpersMM.wrongCardOperSubActionRelaxable
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("R2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexHHGR,
				DefaultOpersMM.instVertexHC, null, "Sel", 0, true);

		t1 = new OpersExpr("#NEW wrong card high", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexHHGR,
				instVertexHHGR, t2, instVertexHHGR, "HighRange",
				"This relation"
						+ " has a wrong cardinality in the HighRange value");

		DefaultOpersMM.wrongCardOperSubActionRelaxable
				.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		OpersConcept directHardHardSemanticEdge = new OpersConcept(
				"TravHardBinary");

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
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directHardHardSemanticEdge.getIdentifier(),
				attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directHardHardSemanticEdge.getIdentifier(),
				attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directHardHardSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directHardHardSemanticEdge
						.getIdentifier(), attribute.getName(), true));

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

		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directHardHardSemanticEdge.getIdentifier(),
				attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directHardHardSemanticEdge.getIdentifier(),
				attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directHardHardSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directHardHardSemanticEdge
						.getIdentifier(), attribute.getName(), true));

		InstConcept instDirHardHardSemanticEdge = new InstConcept(
				"TravHardBinary", DefaultOpersMM.metaMetaPairwiseRelation,
				directHardHardSemanticEdge);

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
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
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
				instDirHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirHardHardSemanticEdge, instDirHardHardSemanticEdge,
				"AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"Sel", true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instDirHardHardSemanticEdge, instDirHardHardSemanticEdge,
				"AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirHardHardSemanticEdge, t1, t2);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"Sel", false, t1);

		t1 = new OpersExpr("CONFnoHighSel", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"FalseVal", false, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirHardHardSemanticEdge, instDirHardHardSemanticEdge,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("057 Val - Aggre:CONFSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirHardHardSemanticEdge, t2, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("CONFnoHighSelSel", refas
				.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, instDirHardHardSemanticEdge,
				DefaultOpersMM.instVertexHC, "Sel", "FalseVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"Sel", false, t1);

		t1 = new OpersExpr("CONFnoHighSel", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"FalseVal", false, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirHardHardSemanticEdge, instDirHardHardSemanticEdge,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("059 Val - NoAggre:CONFSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirHardHardSemanticEdge, t2, t1);

		// FIXME: compare and sync with exp 095 on the catalog todo

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("CONFSelSel", refas.getSemanticExpressionTypes()
				.get("Or"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				DefaultOpersMM.instVertexHC, "Sel", "FalseVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"Sel", false, t1);

		t1 = new OpersExpr("058 Ver - CONFSel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"FalseVal", false, t1);

		semExpr.add(t1);

		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				DefaultOpersMM.instVertexHC, DefaultOpersMM.instVertexHC,
				"Sel", true, 1);

		t1 = new OpersExpr("ALTSelected", refas.getSemanticExpressionTypes()
				.get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"PSel", true, t1);

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
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// ias.add(new InstAttribute("alternative", new ElemAttribute(
		// "alternative", StringType.IDENTIFIER, AttributeType.OPTION,
		// false, "alternative", "", "", 1, -1, "", "", -1, "", ""),
		// semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				DefaultOpersMM.instVertexHC, "Sel", "Sel");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
				"Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirHardHardSemanticEdge, DefaultOpersMM.instVertexHC, "Sel");

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
				instDirHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				DefaultOpersMM.instVertexHC, "Sel", "Sel");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
		// "Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirHardHardSemanticEdge,DefaultOpersMM.instVertexHC, "Core",
		// false, 1);
		//
		// t1 = new OpersExpr("REQSelected", refas.getSemanticExpressionTypes()
		// .get("GreaterOrEq"), 1, false, t1);
		//
		// semExpr.add(t1);
		//
		// DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("061 Ver/Val CONDSelected", refas
				.getSemanticExpressionTypes().get("NotEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				DefaultOpersMM.instVertexHC, "Sel", "Sel");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("060 Ver/Val CONDNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				DefaultOpersMM.instVertexHC, "Exclu", "Exclu");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		ias.add(new InstAttribute("condition", new ElemAttribute("condition",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"condition", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex().put("TravHardBinary",
				instDirHardHardSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("GoalGoalSidePWAsso-GR", instEdge);
		instEdge.setIdentifier("GoalGoalSidePWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instDirHardHardSemanticEdge, true);
		instEdge.setSourceRelation(DefaultOpersMM.instVertexHC, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("GoalGoalSidePW-GR-Asso", instEdge);
		instEdge.setIdentifier("GoalGoalSidePW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexHC, true);
		instEdge.setSourceRelation(instDirHardHardSemanticEdge, true);

		OpersConcept directStructHardHardSemanticEdge = new OpersConcept(
				"MeansHardBinary");

		attribute = new ElemAttribute("PSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***",
				"Element selected for this solution (green)", false, 2, -1, "",
				"", -1, "", "");

		directStructHardHardSemanticEdge
				.putSemanticAttribute("PSel", attribute);

		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		// sasverNoLoopsOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));

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
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));

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
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directStructHardHardSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));

		attribute = new ElemAttribute("pOutAnaSel", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directStructHardHardSemanticEdge.putSemanticAttribute("pOutAnaSel",
				attribute);
		DefaultOpersMM.sasverNoLoopsOperationSubActionMV
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperMVUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));

		DefaultOpersMM.sasverNoLoopsOperRedUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directStructHardHardSemanticEdge.getIdentifier(),
						attribute.getName(), true));

		InstConcept instDirStructHardHardSemanticEdge = new InstConcept(
				"MeansHardBinary", DefaultOpersMM.metaMetaPairwiseRelation,
				directStructHardHardSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("shhtoip", instEdge);
		instEdge.setIdentifier("shhtoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
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
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				instDirStructHardHardSemanticEdge, "Sel", "PSel");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("MANnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MANSelSel", refas.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				instDirStructHardHardSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("053 Ver - MANSel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", false, t1);

		semExpr.add(t1);

		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

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

		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		// iterate onver multi-instances

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"Sel", true, 0);

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MACore1Core", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				instDirStructHardHardSemanticEdge, "Core", "TrueVal");

		t1 = new OpersExpr("052 UpCore MANCore1", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"Core", false, t1);

		semExpr.add(t1);
		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MACore1Core", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR,
				instDirStructHardHardSemanticEdge, "OCore", "TrueVal");

		t1 = new OpersExpr("153 UpCore MANCore1", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"Core", false, t1);

		semExpr.add(t1);
		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		// simulExecOptSubOperNormal.addSemanticExpression(t1);
		// simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		DefaultOpersMM.verifParentsOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("MAExclu1Exclu", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				instDirStructHardHardSemanticEdge, "Exclu", "TrueVal");

		t1 = new OpersExpr("050 Ver/Val - MAExclu", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"Exclu", false, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("MAExclu1Exclu", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR,
				instDirStructHardHardSemanticEdge, "Exclu", "TrueVal");

		t1 = new OpersExpr("151 Ver/Val - MAExclu", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"Exclu", false, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"structVal", 1);

		t1 = new OpersExpr(
				"048 NoLoop - structVal",
				"To eliminate the structural loop remove this structural relation (mandatory between "
						+ "#source# and #target#) or remove another relation with error mark.",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"structVal", true, t1);

		semExpr.add(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionMVRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedToVerify
				.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"structVal", 1);

		t1 = new OpersExpr("048X 2",
				"This relation creates an structural loop", refas
						.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR, "structVal",
				true, t1);

		semExpr.add(t1);
		// sasverNoLoopsOperSubActionMVRelaxable.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionRedToVerify.addSemanticExpression(t1);

		ias.add(new InstAttribute("mandatory", new ElemAttribute("mandatory",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"mandatory", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("149 OPTSel", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				instDirStructHardHardSemanticEdge, "Sel", "PSel");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("OPTnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("OPTSelSel", refas.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				instDirStructHardHardSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("056 Ver/Val OPTSel", refas
				.getSemanticExpressionTypes().get("Implies"),
				ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "PSel", false, t1);

		semExpr.add(t1);

		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

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

		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirStructHardHardSemanticEdge,
				instDirStructHardHardSemanticEdge, "AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"Sel", true, 0);

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"structVal", 1);

		t1 = new OpersExpr(
				"048b NoLoop structVal",
				"To eliminate the structural loop remove this structural relation (mandatory between "
						+ "#source# and #target#) or remove another relation with error mark.",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"structVal", true, t1);

		semExpr.add(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionMVRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedToVerify
				.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirStructHardHardSemanticEdge, DefaultOpersMM.instVertexHC,
				"structVal", 1);

		t1 = new OpersExpr(
				"149b NoLoop structVal",
				"To eliminate the structural loop remove this structural relation (mandatory between "
						+ "#source# and #target#) or remove another relation with error mark.",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirStructHardHardSemanticEdge, instVertexHHGR, "structVal",
				true, t1);

		semExpr.add(t1);
		// sasverNoLoopsOperSubActionMVRelaxable.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionRedToVerify.addSemanticExpression(t1);

		ias.add(new InstAttribute("optional", new ElemAttribute("optional",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "optional",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex().put("MeansHardBinary",
				instDirStructHardHardSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("structHardHardPWAsso-GR", instEdge);
		instEdge.setIdentifier("structHardHardPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instDirStructHardHardSemanticEdge, true);
		instEdge.setSourceRelation(DefaultOpersMM.instVertexHC, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges()
				.put("structHardHardPW-GR-Asso", instEdge);
		instEdge.setIdentifier("structHardHardPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexHC, true);
		instEdge.setSourceRelation(instDirStructHardHardSemanticEdge, true);

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
		// instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		// instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
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
		// instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		// instEdge.setTargetRelation(instLowSemexpcntxPairwiseRel, true);
		// instEdge.setSourceRelation(instVertexLowExp, true);
		//
		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("lowexprcntxPW-GR-Asso",
		// instEdge);
		// instEdge.setIdentifier("lowexpcntxPW-GR-Asso");
		// instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
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
		// instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		// instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
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
		// instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		// instEdge.setTargetRelation(instLowSemvarcntxPairwiseRel, true);
		// instEdge.setSourceRelation(instVertexLowVAR, true);
		//
		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("lowvarcntxPW-GR-Asso", instEdge);
		// instEdge.setIdentifier("lowvarcntxPW-GR-Asso");
		// instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		// instEdge.setTargetRelation(instVertexCG, true);
		// instEdge.setSourceRelation(instLowSemvarcntxPairwiseRel, true);

		OpersConcept semvarcntxPairwiseRel = new OpersConcept(
				"NmVarToConcernPWlaimPW");

		InstConcept instSemvarcntxPairwiseRel = new InstConcept(
				"NmVarToConcernBinary",
				DefaultOpersMM.metaMetaPairwiseRelation, semvarcntxPairwiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vcxtoip", instEdge);
		instEdge.setIdentifier("vcxtoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
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

		refas.getVariabilityVertex().put("NmVarToConcernBinary",
				instSemvarcntxPairwiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("varcntxPWAsso-GR", instEdge);
		instEdge.setIdentifier("varcntxPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instSemvarcntxPairwiseRel, true);
		instEdge.setSourceRelation(DefaultOpersMM.instVertexVAR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("varcntxPW-GR-Asso", instEdge);
		instEdge.setIdentifier("varcntxPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexCG, true);
		instEdge.setSourceRelation(instSemvarcntxPairwiseRel, true);

		OpersConcept directSGSGSemEdge = new OpersConcept("SoftgoalBinary");

		attribute = new ElemAttribute("outConflSG", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directSGSGSemEdge.putSemanticAttribute("outConflSG", attribute);
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("sourceLevel", "Integer",
				AttributeType.OPERATION, "Source Level", "", 1, false,
				new RangeDomain(0, 4, 0), 0, 8, "", "", 8,
				"SL: #sourceLevel#all# - ", "");
		directSGSGSemEdge.putSemanticAttribute("sourceLevel", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));

		directSGSGSemEdge.addPropEditableAttribute("08#" + "sourceLevel");
		directSGSGSemEdge.addPropVisibleAttribute("08#" + "sourceLevel");

		attribute = new ElemAttribute("targetLevel", "Integer",
				AttributeType.OPERATION, "Target Level", "", 1, false,
				new RangeDomain(0, 4, 0), 0, 9, "", "", 9,
				"TL: #targetLevel#all#", "");
		directSGSGSemEdge.putSemanticAttribute("targetLevel", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));

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
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));

		directSGSGSemEdge.addPropEditableAttribute("07#" + "AggregationLow");

		directSGSGSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 8,
				"", "", 8, "#" + "AggregationHigh" + "#all#\n",
				"AggregationHigh" + "#!=#" + "0");
		directSGSGSemEdge.putSemanticAttribute("AggregationHigh", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSGSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSGSGSemEdge
						.getIdentifier(), attribute.getName(), true));

		directSGSGSemEdge.addPropEditableAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPropVisibleAttribute("08#" + "AggregationHigh");

		InstConcept instDirSGSGSemanticEdge = new InstConcept("SoftgoalBinary",
				DefaultOpersMM.metaMetaPairwiseRelation, directSGSGSemEdge);

		refas.getVariabilityVertex().put("SoftgoalBinary",
				instDirSGSGSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgsgtoip", instEdge);
		instEdge.setIdentifier("sgsgtoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instDirSGSGSemanticEdge, true);

		ia = instDirSGSGSemanticEdge.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("contribution", new ElemAttribute(
				"contribution", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "contribution", "", "", 1, -1, "", "", -1, "", ""),
				"contribution#contribution#true#true#true#0#-1#0#-1"));

		// TODO Activate then the expressions are corrected
		// ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
		// StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
		// "", "", 1, -1, "", "", -1, "", ""),
		// "conflict#conflict#false#true#true#0#-1#0#-1"));

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
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");
		semExpr.add(t1);
		DefaultOpersMM.sasverSGConflOperSubActionVerification
				.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirSGSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "low");

		t1 = new OpersExpr("080 Ver/Val low: SGReqLevel",
				"This contribution relation requires arevision to solve"
						+ " a conflict between the soft goals involved", refas
						.getSemanticExpressionTypes().get("Implies"),
				instDirSGSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirSGSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "high");

		t1 = new OpersExpr("079 Ver/Val high: SGReqLevel",
				"This contribution relation requires a revision to solve"
						+ " a conflict between the soft goals involved", refas
						.getSemanticExpressionTypes().get("Implies"),
				instDirSGSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirSGSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "close");

		t1 = new OpersExpr("078b Ver/Val close: SGReqLevel",
				"This contribution relation requires a revision to solve"
						+ " a conflict between the soft goals involved", refas
						.getSemanticExpressionTypes().get("Implies"),
				instDirSGSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		ias.add(new InstAttribute("contribution", new ElemAttribute(
				"contribution", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "contribution", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		semExpr = new ArrayList<OpersExpr>();

		// Wrong expressions - correct for conflict

		t1 = new OpersExpr("CLEx SrcLv", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirSGSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "low");

		t1 = new OpersExpr("078 Ver/Val low: source & target",
				"This contribution relation requires a revision to solve"
						+ " a conflict between the soft goals involved", refas
						.getSemanticExpressionTypes().get("Implies"),
				instDirSGSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("CLEx SrcLv", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirSGSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "high");

		t1 = new OpersExpr("077 Ver/Val high: source & target", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirSGSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("CLEx SrcLv", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "sourceLevel");

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSGSGSemanticEdge, "ClaimExpLevel", "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirSGSGSemanticEdge, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "close");

		t1 = new OpersExpr("076 Ver/Val close: source & target", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirSGSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		// TODO enable when the expressions are correct
		// ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
		// StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
		// "", "", 1, -1, "", "", -1, "", ""), semExpr));

		// semExpr = new ArrayList<OpersExpr>();
		//
		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirSGSGSemanticEdge,DefaultOpersMM.instVertexSG, "Sel", true, 1);
		//
		// t1 = new OpersExpr("ALTSelected", refas.getSemanticExpressionTypes()
		// .get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// instDirSGSGSemanticEdge,DefaultOpersMM.instVertexSG, "Sel", true,
		// t1);
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
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
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
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,DefaultOpersMM.instVertexSG,
		// DefaultOpersMM.instVertexSG, "Sel", "Sel");
		//
		// t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
		// "Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// DefaultOpersMM.instVertexSG, "Sel");
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
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "Sel", "Sel");

		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
		// "Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirSGSGSemanticEdge,DefaultOpersMM.instVertexSG, "Sel", false,
		// 1);
		//
		// t1 = new OpersExpr("REQSelected", refas.getSemanticExpressionTypes()
		// .get("GreaterOrEq"), 1, false, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("083 UpCore - requires", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				DefaultOpersMM.instVertexSG, "Core", "Core");

		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
		// "Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirSGSGSemanticEdge,DefaultOpersMM.instVertexSG, "Core", false,
		// 1);
		//
		// t1 = new OpersExpr("REQSelected", refas.getSemanticExpressionTypes()
		// .get("GreaterOrEq"), 1, false, t1);
		semExpr.add(t1);

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG, "Sel",
				true, 1);

		t1 = new OpersExpr("075 Ver/Val SGPWIMPSel", refas
				.getSemanticExpressionTypes().get("Implies"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG, "Sel",
				true, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		ias.add(new InstAttribute("implication", new ElemAttribute(
				"implication", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implication", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgsgPW-sgpwsg", instEdge);
		instEdge.setIdentifier("sgsgPW-sgpwsg");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instDirSGSGSemanticEdge, true);
		instEdge.setSourceRelation(DefaultOpersMM.instVertexSG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgsgPW-sgsgpw", instEdge);
		instEdge.setIdentifier("sgsgPW-sgsgpw");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexSG, true);
		instEdge.setSourceRelation(instDirSGSGSemanticEdge, true);

		OpersConcept semanticSGSGGroupRelation = new OpersConcept(
				"SoftgoalN-ary");// hardSemOverTwoRelList);

		InstConcept instVertexSGGR = new InstConcept("SoftgoalN-ary",
				semanticSGSGGroupRelation,
				DefaultOpersMM.metaMetaInstOverTwoRel);
		refas.getVariabilityVertex().put("SoftgoalN-ary", instVertexSGGR);

		OpersConcept directGRSGSemEdge = new OpersConcept("SgN-aryToSg");

		attribute = new ElemAttribute("outConflSG", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directGRSGSemEdge.putSemanticAttribute("outConflSG", attribute);
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("targetLevel", "Integer",
				AttributeType.OPERATION, "Target Level", "", 1, false,
				new RangeDomain(0, 4, 0), 0, 9, "", "", 9,
				":#targetLevel#all#", "");
		directGRSGSemEdge.putSemanticAttribute("targetLevel", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));

		directGRSGSemEdge.addPanelSpacersAttribute(":#" + "targetLevel" + "#");
		directGRSGSemEdge.addPropEditableAttribute("09#" + "targetLevel");
		directGRSGSemEdge.addPropVisibleAttribute("09#" + "targetLevel");

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 7,
				"", "", 7, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		directGRSGSemEdge.putSemanticAttribute("AggregationLow", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));

		directGRSGSemEdge.addPropEditableAttribute("07#" + "AggregationLow");

		directGRSGSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 8,
				"", "", 8, "#" + "AggregationHigh" + "#all#\n",
				"AggregationHigh" + "#!=#" + "0");
		directGRSGSemEdge.putSemanticAttribute("AggregationHigh", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directGRSGSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directGRSGSemEdge
						.getIdentifier(), attribute.getName(), true));

		directGRSGSemEdge.addPropEditableAttribute("08#" + "AggregationHigh");

		directGRSGSemEdge.addPropVisibleAttribute("08#" + "AggregationHigh");
		// directGRSGSemEdge.addPanelVisibleAttribute("09#" +
		// "targetLevel");

		// FIXME remove, use other
		InstConcept instSgOTToSg = new InstConcept("SgN-aryToSg",
				DefaultOpersMM.metaMetaPairwiseRelation, directGRSGSemEdge);

		refas.getVariabilityVertex().put("SgN-aryToSg", instSgOTToSg);

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
		DefaultOpersMM.sasverSGConflOperSubActionVerification
				.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instSgOTToSg, instVertexSGGR, "OSel", true, 1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instSgOTToSg, DefaultOpersMM.instVertexSG, "ClaimExpLevel",
				true, "targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instSgOTToSg, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instSgOTToSg, DefaultOpersMM.instVertexSG, "satisficingLevel",
				"low");

		t1 = new OpersExpr(
				"086  Ver/Val - low: SGReqLevel",
				"This contribution relation requires a revision to solve"
						+ " a conflict between the soft goal OT and the soft goal involved",
				refas.getSemanticExpressionTypes().get("Implies"),
				instSgOTToSg, t3, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instSgOTToSg, instVertexSGGR, "OSel", true, 1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instSgOTToSg,
				DefaultOpersMM.instVertexSG, instSgOTToSg, "ClaimExpLevel",
				"targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instSgOTToSg, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "high");

		t1 = new OpersExpr(
				"085 Ver/Val high: SGReqLevel",
				"This contribution relation requires a revision to solve"
						+ " a conflict between the soft goal OT and the soft goal involved",
				refas.getSemanticExpressionTypes().get("Implies"),
				instSgOTToSg, t3, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instSgOTToSg, instVertexSGGR, "OSel", true, 1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instSgOTToSg,
				DefaultOpersMM.instVertexSG, instSgOTToSg, "ClaimExpLevel",
				"targetLevel");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instSgOTToSg, t1, t3);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSGSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "close");

		t1 = new OpersExpr(
				"084 Ver/Val close: SGReqLevel",
				"This contribution relation requires a revision to solve"
						+ " a conflict between the soft goal OT and the soft goal involved",
				refas.getSemanticExpressionTypes().get("Implies"),
				instSgOTToSg, t3, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

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
		// DefaultOpersMM.instVertexSG, "Sel", 1);
		//
		// t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
		// "GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// ExpressionVertexType.RIGHTCONCEPTVARIABLE,DefaultOpersMM.instVertexSG,
		// DefaultOpersMM.instVertexSG, "ClaimExpLevel", "targetLevel");
		//
		// t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
		// "Implies"), t1, t3);
		//
		// t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
		// .get("Equals"),DefaultOpersMM.instVertexSG, "satisficingLevel",
		// "low");
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
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		//
		// t1 = new OpersExpr("SG Gr Sel",
		// refas.getSemanticExpressionTypes().get(
		// "Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// DefaultOpersMM.instVertexSG, "Sel", 1);
		//
		// t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
		// "LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// DefaultOpersMM.instVertexSG,DefaultOpersMM.instVertexSG,
		// "ClaimExpLevel", "targetLevel");
		//
		// t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
		// "Implies"), t1, t3);
		//
		// t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
		// .get("Equals"),DefaultOpersMM.instVertexSG, "satisficingLevel",
		// "high");
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
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		//
		// t1 = new OpersExpr("SG Gr Sel",
		// refas.getSemanticExpressionTypes().get(
		// "Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// DefaultOpersMM.instVertexSG, "Sel", 1);
		//
		// t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// DefaultOpersMM.instVertexSG,DefaultOpersMM.instVertexSG,
		// "ClaimExpLevel", "targetLevel");
		//
		// t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
		// "Implies"), t1, t3);
		//
		// t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
		// .get("Equals"),DefaultOpersMM.instVertexSG, "satisficingLevel",
		// "high");
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
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// // sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		//
		// ias.add(new InstAttribute("conflict", new ElemAttribute("conflict",
		// StringType.IDENTIFIER, AttributeType.OPTION, false, "conflict",
		// "", "", 1, -1, "", "", -1, "", ""), semExpr));
		//
		// semExpr = new ArrayList<OpersExpr>();
		//
		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirSGSGSemanticEdge,DefaultOpersMM.instVertexSG, "Sel", true, 1);
		//
		// t1 = new OpersExpr("ALTSelected", refas.getSemanticExpressionTypes()
		// .get("Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// instDirSGSGSemanticEdge,DefaultOpersMM.instVertexSG, "Sel", true,
		// t1);
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
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,DefaultOpersMM.instVertexSG,
		// DefaultOpersMM.instVertexSG, "Sel", "Sel");
		//
		// t3 = new OpersExpr("3", refas.getSemanticExpressionTypes().get(
		// "Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// DefaultOpersMM.instVertexSG, "Sel");
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
				instVertexSGGR, DefaultOpersMM.instVertexSG, "OSel", "Sel");

		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
		// "Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirSGSGSemanticEdge,DefaultOpersMM.instVertexSG, "Sel", false,
		// 1);
		//
		// t1 = new OpersExpr("REQSelected", refas.getSemanticExpressionTypes()
		// .get("GreaterOrEq"), 1, false, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("089 UpCore - requires", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instSgOTToSg,
				instVertexSGGR, DefaultOpersMM.instVertexSG, "OCore", "Core");

		// t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
		// "Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// instDirSGSGSemanticEdge,DefaultOpersMM.instVertexSG, "Core", false,
		// 1);
		//
		// t1 = new OpersExpr("REQSelected", refas.getSemanticExpressionTypes()
		// .get("GreaterOrEq"), 1, false, t1);

		semExpr.add(t1);

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("require", new ElemAttribute("require",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "require",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instSgOTToSg, DefaultOpersMM.instVertexSG, "Sel", true, 1);

		t1 = new OpersExpr("089XX SGPW2IMPSel", refas
				.getSemanticExpressionTypes().get("Implies"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE, instSgOTToSg,
				instVertexSGGR, "OSel", true, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		ias.add(new InstAttribute("implication", new ElemAttribute(
				"implication", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implication", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		OpersConcept directSgToSgGRSemEdge = new OpersConcept("SgToSgN-ary");
		attribute = new ElemAttribute("sourceLevel", "Integer",
				AttributeType.OPERATION, "Source Level", "", 1, false,
				new RangeDomain(0, 4, 0), 0, 8, "", "", 8, "#sourceLevel#all#",
				"");
		directSgToSgGRSemEdge.putSemanticAttribute("sourceLevel", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// directSgToSgGRSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directSgToSgGRSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));

		directSgToSgGRSemEdge.addPropEditableAttribute("08#" + "sourceLevel");
		directSgToSgGRSemEdge.addPropVisibleAttribute("08#" + "sourceLevel");

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 7,
				"", "", 7, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		directSgToSgGRSemEdge.putSemanticAttribute("AggregationLow", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));

		directSgToSgGRSemEdge
				.addPropEditableAttribute("07#" + "AggregationLow");

		directSgToSgGRSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 8,
				"", "", 8, "#" + "AggregationHigh" + "#all#\n",
				"AggregationHigh" + "#!=#" + "0");
		directSgToSgGRSemEdge
				.putSemanticAttribute("AggregationHigh", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));

		directSgToSgGRSemEdge.addPropEditableAttribute("08#"
				+ "AggregationHigh");

		directSgToSgGRSemEdge
				.addPropVisibleAttribute("08#" + "AggregationHigh");
		// directSgToSgGRSemEdge.addPanelVisibleAttribute("08#" +
		// "sourceLevel");

		InstConcept instSgToSgGR = new InstConcept("SgToSgN-ary",
				DefaultOpersMM.metaMetaPairwiseRelation, directSgToSgGRSemEdge);

		attribute = new ElemAttribute("sourceClExp", "Boolean",
				AttributeType.OPERATION, false, "sourceClExp", "", 0, 0, -1,
				"", "", -1, "", "");
		directSgToSgGRSemEdge.putSemanticAttribute("sourceClExp", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSgToSgGRSemEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addOutAttribute(new OpersIOAttribute(
		// directSgToSgGRSemEdge.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directSgToSgGRSemEdge.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSgToSgGRSemEdge
						.getIdentifier(), attribute.getName(), true));

		refas.getVariabilityVertex().put("SgToSgN-ary", instSgToSgGR);

		instSgToSgGR.createInstAttributes();

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
				DefaultOpersMM.instVertexSG, instSgToSgGR, "ClaimExpLevel",
				"sourceLevel");

		t1 = new OpersExpr("071 Ver/Val - ANDhardSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instSgToSgGR, instSgToSgGR, "sourceClExp", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		semExpr.add(t1);

		ias.add(new InstAttribute("Default", new ElemAttribute("Default",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "Default",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		// extends
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgsggrtoip", instEdge);
		instEdge.setIdentifier("sgsggrtoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instSgOTToSg, true);

		// extends
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sggrtogr", instEdge);
		instEdge.setIdentifier("sggrtogr");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelOCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexSGGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sggrsgtoip", instEdge);
		instEdge.setIdentifier("sggrsgtoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instSgToSgGR, true);

		// From SG to group
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgsgSGR-SGsgsg", instEdge);
		instEdge.setIdentifier("sgsgSGR-SGsgsg");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instSgToSgGR, true);
		instEdge.setSourceRelation(DefaultOpersMM.instVertexSG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgsgSGR-sgsgSG", instEdge);
		instEdge.setIdentifier("sgsgSGR-sgsgSG");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSGGR, true);
		instEdge.setSourceRelation(instSgToSgGR, true);

		// From group to SG
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("SGGRtosg-GRsgsgGR", instEdge);
		instEdge.setIdentifier("SGGRtosg-GRsgsgGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instSgOTToSg, true);
		instEdge.setSourceRelation(instVertexSGGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("SGGRtosg-SGsgsgSG", instEdge);
		instEdge.setIdentifier("SGGRtosg-SGsgsgSG");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexSG, true);
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
				instVertexSGGR, t1, "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub1", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTSUBITERINCRELVARIABLE,
				instVertexSGGR, instSgToSgGR, "sourceClExp", true, "TrueVal");

		t1 = new OpersExpr("073 Ver/Val - ANDSGSGGrCoreRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCRELVARIABLE, instVertexSGGR,
				instVertexSGGR, t1, "OCore");

		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("sub1",
				refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCRELVARIABLE, instVertexSGGR,
				instSgToSgGR, "sourceClExp", true, "TrueVal");

		t1 = new OpersExpr("074 Ver/Val - ORSGSGGrSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexSGGR,
				instVertexSGGR, t1, "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		semExpr.add(t1);

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		OpersConcept directCVCGSemanticEdge = new OpersConcept("VaClPW");
		InstConcept instDirCVCGSemanticEdge = new InstConcept("VaClPW",
				DefaultOpersMM.metaMetaPairwiseRelation, directCVCGSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("svcgtoip", instEdge);
		instEdge.setIdentifier("svcgtoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
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
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexCG, true);
		instEdge.setSourceRelation(instDirCVCGSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("CVCGPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("CVCGPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instDirCVCGSemanticEdge, true);
		instEdge.setSourceRelation(DefaultOpersMM.instVertexVAR, true);

		// Oper to Claim

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semClaim);

		OpersConcept directOperClaimToSemanticEdge = new OpersConcept(
				"OperClaimN-aryToClaim");

		InstConcept instDirOperClaimOTToClaim = new InstConcept(
				"OperClaimN-aryToClaim",
				DefaultOpersMM.metaMetaPairwiseRelation,
				directOperClaimToSemanticEdge);

		OpersConcept directOperClaimFromSemanticEdge = new OpersConcept(
				"OperToOperClaimN-ary");
		InstConcept instDirOpertoOperClaimOTEdge = new InstConcept(
				"OperToOperClaimN-ary",
				DefaultOpersMM.metaMetaPairwiseRelation,
				directOperClaimFromSemanticEdge);

		attribute = new ElemAttribute("AggregationLow", "Integer",
				AttributeType.OPERATION, false, "Aggregation Low", "", 0, 0, 3,
				"", "", 3, "[#" + "AggregationLow" + "#all#..",
				"AggregationHigh" + "#!=#" + "0");
		directOperClaimFromSemanticEdge.putSemanticAttribute("AggregationLow",
				attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directOperClaimFromSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directOperClaimFromSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));

		attribute = new ElemAttribute("AggregationHigh", "Integer",
				AttributeType.OPERATION, false, "AggregationHigh", "", 0, 0, 4,
				"", "", 4, "#" + "AggregationHigh" + "#all#]\n",
				"AggregationHigh" + "#!=#" + "0");
		directOperClaimFromSemanticEdge.putSemanticAttribute("AggregationHigh",
				attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directOperClaimFromSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directOperClaimFromSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));

		instDirOpertoOperClaimOTEdge.createInstAttributes();

		ia = instDirOpertoOperClaimOTEdge.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("Default", new ElemAttribute("Default",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "Default",
				"", "", 1, -1, "", "", -1, "", ""),
				"Default##true#true#true#0#-1#0#-1"));

		ia = instDirOpertoOperClaimOTEdge.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("DEFnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOpertoOperClaimOTEdge, DefaultOpersMM.instVertexHC,
				instDirOpertoOperClaimOTEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("MANnoHighSel", refas.getSemanticExpressionTypes()
				.get("DoubleImplies"), ExpressionVertexType.RIGHTVARIABLE,
				instDirOpertoOperClaimOTEdge, instDirOpertoOperClaimOTEdge,
				"PSel", false, t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirOpertoOperClaimOTEdge, instDirOpertoOperClaimOTEdge,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("042 Val - NoAggre:DEFSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirOpertoOperClaimOTEdge, t2, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("DEFSelSel", refas.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOpertoOperClaimOTEdge, DefaultOpersMM.instVertexHC,
				instDirOpertoOperClaimOTEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("046 Ver/Val - MANSel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTVARIABLE,
				instDirOpertoOperClaimOTEdge, instDirOpertoOperClaimOTEdge,
				"PSel", false, t1);

		semExpr.add(t1);

		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		// iterate onver multi-instances

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirOpertoOperClaimOTEdge, DefaultOpersMM.instVertexHC,
				"Sel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTVARIABLE,
				instDirOpertoOperClaimOTEdge, instDirOpertoOperClaimOTEdge,
				"AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirOpertoOperClaimOTEdge, DefaultOpersMM.instVertexHC,
				"Sel", true, 0);

		t2 = new OpersExpr("AggHigh", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTVARIABLE,
				instDirOpertoOperClaimOTEdge, instDirOpertoOperClaimOTEdge,
				"AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirOpertoOperClaimOTEdge, t1, t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirOpertoOperClaimOTEdge, instDirOpertoOperClaimOTEdge,
				"PSel", true, 1);

		t1 = new OpersExpr("Aggre:MANSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instDirOpertoOperClaimOTEdge, t1, t2);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirOpertoOperClaimOTEdge, instDirOpertoOperClaimOTEdge,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("043 Val - Aggre:DEFSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirOpertoOperClaimOTEdge, t2, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		// t1 = new OpersExpr("DEFCore1Core", refas.getSemanticExpressionTypes()
		// .get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,DefaultOpersMM.instVertexHC,
		// instHchcHHGRGR, "Core", "TrueVal");
		//
		// t1 = new OpersExpr("DEFCore1",
		// refas.getSemanticExpressionTypes().get(
		// "DoubleImplies"),
		// ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instHchcHHGRGR,
		// DefaultOpersMM.instVertexHC, "Core", false, t1);
		//
		// semExpr.add(t1);
		// DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		// // simulExecOptSubOperNormal.addSemanticExpression(t1);
		// // simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		//
		// verifParentsOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MAExclu1Exclu", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOpertoOperClaimOTEdge, DefaultOpersMM.instVertexHC,
				instDirOpertoOperClaimOTEdge, "Exclu", "TrueVal");

		t1 = new OpersExpr("044 Ver/Val DEFExclu", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOpertoOperClaimOTEdge, DefaultOpersMM.instVertexHC,
				"Exclu", false, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("2def", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirOpertoOperClaimOTEdge, DefaultOpersMM.instVertexHC,
				"structVal", 1);

		t1 = new OpersExpr(
				"045 NoLoop - DEFStruc",
				"To eliminate the structural loop remove this structural relation (mandatory between "
						+ "#source# and #target#) or remove another relation with error mark.",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirOpertoOperClaimOTEdge, DefaultOpersMM.instVertexHC,
				"structVal", true, t1);

		semExpr.add(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionMVRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedToVerify
				.addSemanticExpression(t1);

		ias.add(new InstAttribute("Default", new ElemAttribute("Default",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "Default",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		// -----------------------

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
		// ExpressionVertexType.RIGHTCONCEPTVARIABLE,DefaultOpersMM.instVertexHC,
		// instVertexCLGR, "Sel", "Sel");
		//
		// DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCRELVARIABLE, instVertexCLGR,
				instDirOpertoOperClaimOTEdge, "PSel", true, "TrueVal");

		t1 = new OpersExpr("104 Ver/Val - ANDOperCLhardSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCRELVARIABLE, instVertexCLGR,
				instVertexCLGR, t1, "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, "Core", true, "TrueVal");

		t1 = new OpersExpr("106 UpCore - ANDhardOperCLCoreRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, t1, "OCore");

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("ORhardConcept",
		// refas.getSemanticExpressionTypes()
		// .get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// ExpressionVertexType.RIGHTCONCEPTVARIABLE,DefaultOpersMM.instVertexHC,
		// instVertexCLGR, "Sel", "Sel");
		//
		// DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, "Sel", true, "FalseVal");

		t1 = new OpersExpr("107 UpCore - ORhardSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, t1, "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		// t1 = new OpersExpr("MUTEXhardSelConcept", refas
		// .getSemanticExpressionTypes().get("Equals"),
		// ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
		// ExpressionVertexType.RIGHTCONCEPTVARIABLE,DefaultOpersMM.instVertexHC,
		// instVertexCLGR, "Sel", "Sel");
		//
		// DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);
		// semExpr.add(t1);

		// FIXME correct the INCREL attribute and validate is the selected for
		// ITERS and UNIQUE

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, "Sel", 0);

		t1 = new OpersExpr("sub2clopersel", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexCLGR, instVertexOper, t1, 1);

		t1 = new OpersExpr("108 Ver/Val MUTEXhardSelLRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexCLGR, DefaultOpersMM.instVertexHC, "OSel", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
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

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
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

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, null, "Sel", "FalseVal", true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexCLGR, instVertexOper, t2, instVertexCLGR, "LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, null, "Sel", "FalseVal", true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexCLGR, instVertexOper, t2, instVertexCLGR, "HighRange");

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexCLGR, t1, t2);

		t1 = new OpersExpr("110 Ver/Val RANGEHardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexCLGR, instVertexCLGR, "OSel", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, null, "TrueVal", 0, true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexCLGR, instVertexCLGR, t1, DefaultOpersMM.instVertexF,
				"LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, null, "Core", "TrueVal", true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexCLGR, instVertexCLGR, t2, instVertexOper, "OCore");

		t1 = new OpersExpr("112 UpCore - ANDFCRel", refas
				.getSemanticExpressionTypes().get("Implies"), instVertexCLGR,
				t1, t2);

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("R2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, null, "Sel", 0, true);

		t1 = new OpersExpr("#NEW wrong card low", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexCLGR, t2, instVertexCLGR, "LowRange", "This relation"
						+ " has a wrong cardinality in the LowRange value");

		DefaultOpersMM.wrongCardOperSubActionRelaxable
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("R2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE, instVertexCLGR,
				instVertexOper, null, "Sel", 0, true);

		t1 = new OpersExpr("#NEW wrong card high", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexCLGR,
				instVertexCLGR, t2, instVertexCLGR, "HighRange",
				"This relation"
						+ " has a wrong cardinality in the HighRange value");

		DefaultOpersMM.wrongCardOperSubActionRelaxable
				.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex().put("OperClaimN-ary", instVertexCLGR);

		attribute = new ElemAttribute("PSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***",
				"Element selected for this solution (green)", false, 2, -1, "",
				"", -1, "", "");

		directOperClaimToSemanticEdge.putSemanticAttribute("PSel", attribute);

		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		// sasverNoLoopsOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));

		attribute = new ElemAttribute("outCl", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directOperClaimToSemanticEdge.putSemanticAttribute("outCl", attribute);
		DefaultOpersMM.sasverClallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));

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
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));

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
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directOperClaimToSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(
						directOperClaimToSemanticEdge.getIdentifier(),
						attribute.getName(), true));

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ocltoip", instEdge);
		instEdge.setIdentifier("ocltoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instDirOperClaimOTToClaim, true);

		instDirOperClaimOTToClaim.createInstAttributes();

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
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexCLGR, instVertexCL,
				"OSel", "ConditionalExpression");

		t1 = new OpersExpr("123 Ver/Val - OPERCLSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTVARIABLE, instDirOperClaimOTToClaim,
				instDirOperClaimOTToClaim, "PSel", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDCoreOperSubActionNormal
		// .addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("MANnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexCLGR,
				instDirOperClaimOTToClaim, "OSel", "TrueVal");

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("MANSelSel", refas.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexCL, instVertexCL, "Sel",
				"TrueVal");

		t1 = new OpersExpr("120 Ver/Val MANSel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirOperClaimOTToClaim, instDirOperClaimOTToClaim, "PSel",
				false, t1);

		semExpr.add(t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		// iterate onver multi-instances

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexCLGR, "OSel", true, 0);

		t1 = new OpersExpr("AggLow", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirOperClaimOTToClaim, instDirOperClaimOTToClaim,
				"AggregationLow", false, t1);

		t2 = new OpersExpr("AggHigh2", refas.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexCLGR, "OSel", true, 0);

		t2 = new OpersExpr("AggHigh1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirOperClaimOTToClaim, instDirOperClaimOTToClaim,
				"AggregationHigh", false, t2);

		t1 = new OpersExpr("And",
				refas.getSemanticExpressionTypes().get("And"),
				instDirOperClaimOTToClaim, t1, t2);

		t2 = new OpersExpr("12", refas.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTVARIABLE,
				instDirOperClaimOTToClaim, instDirOperClaimOTToClaim, "PSel",
				true, 1);

		t1 = new OpersExpr("Aggre:MANSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instDirOperClaimOTToClaim, t1, t2);

		t2 = new OpersExpr("111", refas.getSemanticExpressionTypes().get(
				"NotEquals"), ExpressionVertexType.LEFTVARIABLE,
				instDirOperClaimOTToClaim, instDirOperClaimOTToClaim,
				"AggregationHigh", true, 0);

		t1 = new OpersExpr("119 Val - Aggre:MANSelected", refas
				.getSemanticExpressionTypes().get("Implies"),
				instDirOperClaimOTToClaim, t2, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("122 Ver/Val OPERCLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexCLGR, instVertexCL,
				"Exclu", "Exclu");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexCLGR, instVertexCL,
				"OSel", "ConditionalExpression");

		t1 = new OpersExpr("117### VerCl - OPERCLSelected", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirOperClaimOTToClaim, instVertexCL, "outCl", true, t1);

		// semExpr.add(t1);
		// DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClneverOperSubActionNormal
		// .addSemanticExpression(t1);

		ias.add(new InstAttribute("OperToClaim", new ElemAttribute(
				"OperToClaim", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "OperToClaim", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		refas.getVariabilityVertex().put("OperClaimN-aryToClaim",
				instDirOperClaimOTToClaim);

		// extends
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("clgrtogr", instEdge);
		instEdge.setIdentifier("clgrtogr");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelOCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexCLGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("OperClaimToPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("OperClaimToPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirOperClaimOTToClaim, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("OperClaimToPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("OperClaimToPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instDirOperClaimOTToClaim, true);
		instEdge.setSourceRelation(instVertexCLGR, true);

		refas.getVariabilityVertex().put("OperToOperClaimN-ary",
				instDirOpertoOperClaimOTEdge);
		// FIXME review syntax to associate it

		attribute = new ElemAttribute("PSel", "Boolean",
				AttributeType.EXECCURRENTSTATE, false, "***Selected***",
				"Element selected for this solution (green)", false, 2, -1, "",
				"", -1, "", "");

		directOperClaimFromSemanticEdge.putSemanticAttribute("PSel", attribute);

		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directOperClaimFromSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directOperClaimFromSemanticEdge.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.simulSubOperationAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		// sasverNoLoopsOperationSubAction
		// .addOutAttribute(new OpersIOAttribute(semGeneralPair
		// .getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// semGeneralPair.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						directOperClaimFromSemanticEdge.getIdentifier(),
						attribute.getName(), true));

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("oclftoip", instEdge);
		instEdge.setIdentifier("oclftoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instDirOpertoOperClaimOTEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges()
				.put("OperClaimFromPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("OperClaimFromPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCLGR, true);
		instEdge.setSourceRelation(instDirOpertoOperClaimOTEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges()
				.put("OperClaimFromPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("OperClaimFromPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instDirOpertoOperClaimOTEdge, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("OperClaimPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("OperClaimPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirOperClaimSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("OperClaimPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("OperClaimPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instDirOperClaimSemanticEdge, true);
		instEdge.setSourceRelation(instVertexOper, true);

		// LFeat to Claim
		OpersConcept semanticLFClaimGroupRelation = new OpersConcept(
				"LFtoClaimOTAsso"); // hardSemOverTwoRelList);

		InstConcept instVertexLFCLGR = new InstConcept("LfClOT",
				semanticLFClaimGroupRelation,
				DefaultOpersMM.metaMetaInstOverTwoRel);

		refas.getVariabilityVertex().put("LfClOT", instVertexLFCLGR);

		OpersConcept directFClaimToSemanticEdge = new OpersConcept("FeClToPW");

		InstConcept instDirFClaimToSemanticEdge = new InstConcept(
				"FeClOTToClPW", DefaultOpersMM.metaMetaPairwiseRelation,
				directFClaimToSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("lftcltoip", instEdge);
		instEdge.setIdentifier("lftcltoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDCoreOperSubActionNormal
		// .addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("Ver/Val - OPERCLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirFClaimToSemanticEdge, instVertexLFCLGR, instVertexCL,
				"Exclu", "Exclu");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

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
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelOCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexLFCLGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("FClaimToPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("FClaimToPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirFClaimToSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("FClaimToPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("FClaimToPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instDirFClaimToSemanticEdge, true);
		instEdge.setSourceRelation(instVertexLFCLGR, true);

		OpersConcept directFClaimFromSemanticEdge = new OpersConcept(
				"FeClFromPW");

		InstConcept instDirFClaimFromSemanticEdge = new InstConcept(
				"FeClFromPWo", DefaultOpersMM.metaMetaPairwiseRelation,
				directFClaimFromSemanticEdge);
		refas.getVariabilityVertex().put("FeClFromPW",
				instDirFClaimFromSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("fclftoip", instEdge);
		instEdge.setIdentifier("fclftoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instDirFClaimFromSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("FClaimFromPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("FClaimFromPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexLFCLGR, true);
		instEdge.setSourceRelation(instDirFClaimFromSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("FClaimFromPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("FClaimFromPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instDirFClaimFromSemanticEdge, true);
		instEdge.setSourceRelation(DefaultOpersMM.instVertexF, true);

		OpersConcept directLFClaimSemanticEdge = new OpersConcept("LfClPW");

		InstConcept instDirLFClaimSemanticEdge = new InstConcept("LfClPW",
				DefaultOpersMM.metaMetaPairwiseRelation,
				directLFClaimSemanticEdge);
		refas.getVariabilityVertex().put("LfClPW", instDirLFClaimSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("lfcltoip", instEdge);
		instEdge.setIdentifier("lfcltoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instDirLFClaimSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("LFClaimPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("LFClaimPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirLFClaimSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("LFClaimPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("LFClaimPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instDirLFClaimSemanticEdge, true);
		instEdge.setSourceRelation(DefaultOpersMM.instVertexF, true);

		// Claim to SG

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semSoftgoal);
		OpersConcept directClaimSGSemanticEdge = new OpersConcept(
				"ClaimSgBinary");

		attribute = new ElemAttribute("outConflClSD", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directClaimSGSemanticEdge.putSemanticAttribute("outConflClSD",
				attribute);
		DefaultOpersMM.sasverConflClOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("CLSGLevel", "Integer",
				AttributeType.OPERATION, "Relation Level",
				"Required level for the Claim (0..4)", 2, false,
				new RangeDomain(0, 4, 0), 0, 8, "", "", 8, "#CLSGLevel#all#",
				"");
		directClaimSGSemanticEdge.putSemanticAttribute("CLSGLevel", attribute);
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
		// true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));

		directClaimSGSemanticEdge.addPropEditableAttribute("08#" + "CLSGLevel");
		directClaimSGSemanticEdge.addPropVisibleAttribute("08#" + "CLSGLevel");
		// directClaimSGSemanticEdge.addPanelVisibleAttribute("08#"
		// + "CLSGLevel");
		InstConcept instDirClaimSGSemanticEdge = new InstConcept(
				"ClaimSgBinary", DefaultOpersMM.metaMetaPairwiseRelation,
				directClaimSGSemanticEdge);

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
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));

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
		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directClaimSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directClaimSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("clsgtoip", instEdge);
		instEdge.setIdentifier("clsgtoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
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
		// ExpressionVertexType.RIGHTCONCEPTVARIABLE,DefaultOpersMM.instVertexSG,
		// instDirClaimSGSemanticEdge, "Sel", "Sel");
		//
		// semExpr.add(t1);
		// DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.verifFalseOptOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClCoreOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverClneverOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverCoreOpersOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverAllOpersOperSubActionNormal.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSGConflOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClSDOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflClOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverConflSDOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t2 = new OpersExpr("CLSGnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexCL,
				instDirClaimSGSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t2, t1);

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "low");

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t2 = new OpersExpr("CLSGSelSel", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexCL,
				instDirClaimSGSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t2, t1);

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "low");

		t1 = new OpersExpr("100 Ver -  Low: ClaimExpLevel",
				"This claim to soft goal relation requires a revision to solve"
						+ " a conflict with the expected value", refas
						.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t3, t1);

		semExpr.add(t1);

		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
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
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "low");

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// FIXME include in only one of the sets, the previous or this one

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t2 = new OpersExpr("CLSGnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirClaimSGSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t2, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "high");

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t2 = new OpersExpr("CLSGSelSel", refas.getSemanticExpressionTypes()
				.get("And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirClaimSGSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t2, t1);

		t3 = new OpersExpr("3", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "high");

		t1 = new OpersExpr("102 Ver - High: Claim",
				"This claim to soft goal relation requires a revision to solve"
						+ " a conflict with the expected value", refas
						.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t3, t1);

		semExpr.add(t1);

		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
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
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "high");

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t2 = new OpersExpr("CLSGnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexCL,
				instDirClaimSGSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t2, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "close");

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "CLSGLevel");

		t2 = new OpersExpr("CLSGnoHighSelSel", refas
				.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, instVertexCL,
				instDirClaimSGSemanticEdge, "Sel", "TrueVal");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), instDirClaimSGSemanticEdge, t2, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "close");

		t1 = new OpersExpr("101 Ver - close: ClaimExpLevel",
				"This claim to soft goal relation requires a revision to solve"
						+ " a conflict with the expected value", refas
						.getSemanticExpressionTypes().get("Implies"),
				instDirClaimSGSemanticEdge, t3, t1);

		semExpr.add(t1);

		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
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
				instDirClaimSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "close");

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		ias.add(new InstAttribute("ClaimToSG", new ElemAttribute("ClaimToSG",
				StringType.IDENTIFIER, AttributeType.OPTION, false,
				"ClaimToSG", "", "", 1, -1, "", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex().put("ClaimSgBinary",
				instDirClaimSGSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("CLSGPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("CLSGPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexSG, true);
		instEdge.setSourceRelation(instDirClaimSGSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("CLSGPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("CLSGPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instDirClaimSGSemanticEdge, true);
		instEdge.setSourceRelation(instVertexCL, true);

		// SD to SG

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semSoftgoal);

		OpersConcept directSDSGSemanticEdge = new OpersConcept("SdSgBinary");
		attribute = new ElemAttribute("outConflClSD", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		directSDSGSemanticEdge.putSemanticAttribute("outConflClSD", attribute);
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addOutAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));

		attribute = new ElemAttribute("level", "Integer",
				AttributeType.OPERATION, "Level",
				"Required level for the SD (0..4)", 1, false, new RangeDomain(
						0, 4, 0), 0, 8, "", "", 8, "level#all#", "");
		directSDSGSemanticEdge.putSemanticAttribute("level", attribute);

		DefaultOpersMM.simulExecOperUniLab.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.simulSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simSceSubOperationAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
				true));
		DefaultOpersMM.sasverSDCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDallOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSDneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClCoreOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClallOperUniqLab
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverClneverOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverCoreOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverAllOpersOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		// sasverNoLoopsOperationSubAction.addInAttribute(new OpersIOAttribute(
		// directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
		// true));
		// sasverNoLoopsOperUniqueLabeling.addAttribute(new OpersIOAttribute(
		// directSDSGSemanticEdge.getIdentifier(), attribute.getName(),
		// true));
		DefaultOpersMM.sasverSGConflOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverSGConflOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflClOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperationSubAction
				.addInAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverConflSDOperUniqueLabeling
				.addAttribute(new OpersIOAttribute(directSDSGSemanticEdge
						.getIdentifier(), attribute.getName(), true));

		directSDSGSemanticEdge.addPropEditableAttribute("08#" + "level");
		directSDSGSemanticEdge.addPropVisibleAttribute("08#" + "level");
		// directSDSGSemanticEdge.addPanelVisibleAttribute("08#" + "level");

		InstConcept instDirSDSGSemanticEdge = new InstConcept("SdSgBinary",

		DefaultOpersMM.metaMetaPairwiseRelation, directSDSGSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sdsgtoip", instEdge);
		instEdge.setIdentifier("sdsgtoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
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
		// DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSDSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSDSGSemanticEdge, "SDReqLevel", "level");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSD, "Sel", true, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSDSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "low");

		t1 = new OpersExpr("129 Ver/Val low: SDReqLevel",
				"This soft dependency to soft goal relation requires a revision"
						+ " to solve a conflict with the required value", refas
						.getSemanticExpressionTypes().get("Implies"),
				instDirSDSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionRelaxable
				.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSDSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSDSGSemanticEdge, "SDReqLevel", "level");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSD, "Sel", true, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSDSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "high");

		t1 = new OpersExpr("128 Ver/Val high: SDReqLevel",
				"This soft dependency to soft goal relation requires a revision "
						+ "to solve a conflict with the required value", refas
						.getSemanticExpressionTypes().get("Implies"),
				instDirSDSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionRelaxable
				.addSemanticExpression(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE,
				instDirSDSGSemanticEdge, DefaultOpersMM.instVertexSG,
				instDirSDSGSemanticEdge, "SDReqLevel", "level");

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSD, "Sel", true, t1);

		t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instDirSDSGSemanticEdge, DefaultOpersMM.instVertexSG,
				"satisficingLevel", "close");

		t1 = new OpersExpr("127 VerGM/Val - close: SDReqLevel",
				"This soft dependency to soft goal relation requires a revision "
						+ "to solve a conflict with the required value", refas
						.getSemanticExpressionTypes().get("Implies"),
				instDirSDSGSemanticEdge, t3, t1);

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDneverOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionRelaxable
				.addSemanticExpression(t1);

		t1 = new OpersExpr("126 Ver/Val - SDSelected", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
				instDirSDSGSemanticEdge, instVertexSD, instVertexSD, "Sel",
				"ConditionalExpression");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.sasverSDCoreOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		ias.add(new InstAttribute("SD", new ElemAttribute("SD",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "SD", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex().put("SdSgBinary", instDirSDSGSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("SDSGPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("SDSGPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexSG, true);
		instEdge.setSourceRelation(instDirSDSGSemanticEdge, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("SDSGPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("SDSGPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instDirSDSGSemanticEdge, true);
		instEdge.setSourceRelation(instVertexSD, true);
	}

	@SuppressWarnings("unchecked")
	public static void createRefasAssets(InstanceModel refas) {

		ArrayList<OpersExpr> semExpr;

		OpersConcept semAsset = new OpersConcept("Asset");

		ElemAttribute attribute = new ElemAttribute("structVal", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "No loops validation",
				"", 0, new RangeDomain(0, 40, 0), 0, -1, "false", "", -1, "",
				"");
		semAsset.putSemanticAttribute("structVal", attribute);
		DefaultOpersMM.sasverNoLoopsOperationSubActionMV
				.addOutAttribute(new OpersIOAttribute(semAsset.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(semAsset.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperMVUniqueLabeling
				.addAttribute(new OpersIOAttribute(semAsset.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperRedUniqueLabeling
				.addAttribute(new OpersIOAttribute(semAsset.getIdentifier(),
						attribute.getName(), true));

		DefaultOpersMM.simsceExecOperLab2.addAttribute(new OpersIOAttribute(
				semAsset.getIdentifier(), "Sel", true));
		InstConcept instVertexAsset = new InstConcept("Asset",
				DefaultOpersMM.metaMetaInstConcept, semAsset);
		refas.getVariabilityVertex().put("Asset", instVertexAsset);

		semExpr = new ArrayList<OpersExpr>();

		semAsset.setSemanticExpressions(semExpr);

		OpersExpr t1 = new OpersExpr("2", refas.getSemanticExpressionTypes()
				.get("Product"), DefaultOpersMM.instVertexGE,
				DefaultOpersMM.instVertexGE, "SimulSel", true, 2);

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("Sum"),
				DefaultOpersMM.instVertexGE, t1, 0);

		// t1 = new OpersExpr("4",
		// refas.getSemanticExpressionTypes().get("Sum"),
		// instVertexAsset, instVertexGE, "TestConfSel", true, t1);

		t1 = new OpersExpr("131 Val - OrderA...", refas
				.getSemanticExpressionTypes().get("Equals"), instVertexAsset,
				DefaultOpersMM.instVertexGE, "Order", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		semExpr.add(t1);

		InstPairwiseRel instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("astoge", instEdge);
		instEdge.setIdentifier("astoge");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexGE, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		OpersConcept semAssetOperPairwiseRel = new OpersConcept(
				"AssetOperBinary");

		attribute = new ElemAttribute("pOutAnaSel", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		semAssetOperPairwiseRel.putSemanticAttribute("pOutAnaSel", attribute);
		DefaultOpersMM.sasverNoLoopsOperationSubActionMV
				.addOutAttribute(new OpersIOAttribute(semAssetOperPairwiseRel
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(semAssetOperPairwiseRel
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperMVUniqueLabeling
				.addAttribute(new OpersIOAttribute(semAssetOperPairwiseRel
						.getIdentifier(), attribute.getName(), true));

		DefaultOpersMM.sasverNoLoopsOperRedUniqueLabeling
				.addAttribute(new OpersIOAttribute(semAssetOperPairwiseRel
						.getIdentifier(), attribute.getName(), true));

		InstConcept instSemAssetOperPairwiseRel = new InstConcept(
				"AssetOperBinary", DefaultOpersMM.metaMetaPairwiseRelation,
				semAssetOperPairwiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vaptoip", instEdge);
		instEdge.setIdentifier("vaptoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instSemAssetOperPairwiseRel, true);

		InstAttribute ia = instSemAssetOperPairwiseRel
				.getInstAttribute("relTypesAttr");
		List<InstAttribute> ias = (List<InstAttribute>) ia.getValue();

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
		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);

		t1 = new OpersExpr("135 Ver/Val - manLSelected1", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instSemAssetOperPairwiseRel, instVertexAsset, instVertexOper,
				"Sel", "Sel");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

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
		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("136 Ver/Val - manLNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instSemAssetOperPairwiseRel, instVertexAsset, instVertexOper,
				"Exclu", "Exclu");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instSemAssetOperPairwiseRel, instVertexOper, "structVal", 1);

		t1 = new OpersExpr(
				"137 NoLoop - 22p",
				"To eliminate the structural loop remove this structural relation (mandatory between "
						+ "#source# and #target#) or remove another relation with error mark.",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instSemAssetOperPairwiseRel, instVertexAsset, "structVal",
				true, t1);

		semExpr.add(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionMVRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedToVerify
				.addSemanticExpression(t1);

		ias.add(new InstAttribute("implementedBy", new ElemAttribute(
				"implementedBy", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implementedBy", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		refas.getVariabilityVertex().put("AssetOperBinary",
				instSemAssetOperPairwiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("varAssetOperBinaryAsso-GR",
				instEdge);
		instEdge.setIdentifier("varAssetOperBinaryAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instSemAssetOperPairwiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("varAssetOperBinary-GR-Asso",
				instEdge);
		instEdge.setIdentifier("varAssetOperBinary-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instSemAssetOperPairwiseRel, true);

		OpersConcept semAssetPairwiseRel = new OpersConcept("AssetBinary");

		attribute = new ElemAttribute("pOutAnaSel", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		semAssetPairwiseRel.putSemanticAttribute("pOutAnaSel", attribute);
		DefaultOpersMM.sasverNoLoopsOperationSubActionMV
				.addOutAttribute(new OpersIOAttribute(semAssetPairwiseRel
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(semAssetPairwiseRel
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperMVUniqueLabeling
				.addAttribute(new OpersIOAttribute(semAssetPairwiseRel
						.getIdentifier(), attribute.getName(), true));

		DefaultOpersMM.sasverNoLoopsOperRedUniqueLabeling
				.addAttribute(new OpersIOAttribute(semAssetPairwiseRel
						.getIdentifier(), attribute.getName(), true));

		InstConcept instSemAssetPairwiseRel = new InstConcept("AssetBinary",
				DefaultOpersMM.metaMetaPairwiseRelation, semAssetPairwiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vatoip", instEdge);
		instEdge.setIdentifier("vatoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
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
				instSemAssetPairwiseRel, DefaultOpersMM.instVertexHC, "Core",
				true, t1);

		semExpr.add(t1);
		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("DELSelected", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instSemAssetPairwiseRel, instVertexAsset, instVertexAsset,
				"Sel", "Sel");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("DELNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instSemAssetPairwiseRel, instVertexAsset, instVertexAsset,
				"Exclu", "Exclu");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instSemAssetPairwiseRel, instVertexAsset, "structVal", 1);

		t1 = new OpersExpr(
				"2",
				"To eliminate the structural loop remove this structural relation (mandatory between "
						+ "#source# and #target#) or remove another relation with error mark.",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instSemAssetPairwiseRel, instVertexAsset, "structVal", true, t1);

		semExpr.add(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionMVRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedToVerify
				.addSemanticExpression(t1);

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("ASSENotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instSemAssetPairwiseRel, DefaultOpersMM.instVertexF,
				DefaultOpersMM.instVertexF, "Exclu", "Exclu");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		semExpr.add(t1);

		ias.add(new InstAttribute("assembly", new ElemAttribute("assembly",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "assembly",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		refas.getVariabilityVertex()
				.put("AssetBinary", instSemAssetPairwiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("varAssetPWAsso-GR", instEdge);
		instEdge.setIdentifier("varAssetPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instSemAssetPairwiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("varAssetPW-GR-Asso", instEdge);
		instEdge.setIdentifier("varAssetPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instSemAssetPairwiseRel, true);

		// Asset to Asset

		OpersConcept semanticAssetAssetOvertwoRel = new OpersConcept(
				"AssetN-ary");// hardSemOverTwoRelList);

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

		InstConcept instVertexASSETGR = new InstConcept("AssetN-ary",
				semanticAssetAssetOvertwoRel,
				DefaultOpersMM.metaMetaInstOverTwoRel);

		refas.getVariabilityVertex().put("AssetN-ary", instVertexASSETGR);

		InstConcept instAssetassetASGR = new InstConcept("AssetN-aryToAsset",
				DefaultOpersMM.metaMetaPairwiseRelation);
		refas.getVariabilityVertex().put("AssetN-aryToAsset",
				instAssetassetASGR);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("agrtogr", instEdge);
		instEdge.setIdentifier("agrtogr");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelOCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexASSETGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ASSETGRtoassetA-AGR", instEdge);
		instEdge.setIdentifier("ASSETGRtoassetA-AGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instAssetassetASGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("ASSETGRtoassetAGR-GR", instEdge);
		instEdge.setIdentifier("ASSETGRtoassetAGR-GR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instAssetassetASGR, true);
		instEdge.setSourceRelation(instVertexASSETGR, true);

		InstConcept instAssetassetGRAS = new InstConcept("AssetToAssetN-ary",
				DefaultOpersMM.metaMetaPairwiseRelation);
		refas.getVariabilityVertex().put("AssetToAssetN-ary",
				instAssetassetGRAS);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assettoAssetGR-AGR-A", instEdge);
		instEdge.setIdentifier("assettoAssetGR-AGR-A");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instAssetassetGRAS, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assettoAssetGR-GR-AGR", instEdge);
		instEdge.setIdentifier("assettoAssetGR-GR-AGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexASSETGR, true);
		instEdge.setSourceRelation(instAssetassetGRAS, true);

		// Asset to Oper
		// TODO use list of possible relations
		OpersConcept semanticAssetOperGroupRelation = new OpersConcept(
				"AssetOperN-ary");// hardSemOverTwoRelList);

		attribute = new ElemAttribute("structVal", "Integer",
				AttributeType.EXECCURRENTSTATE, false, "No loops validation",
				"", 0, new RangeDomain(0, 40, 0), 0, -1, "false", "", -1, "",
				"");
		semanticAssetOperGroupRelation.putSemanticAttribute("structVal",
				attribute);
		DefaultOpersMM.sasverNoLoopsOperationSubActionMV
				.addOutAttribute(new OpersIOAttribute(
						semanticAssetOperGroupRelation.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(
						semanticAssetOperGroupRelation.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperMVUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						semanticAssetOperGroupRelation.getIdentifier(),
						attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperRedUniqueLabeling
				.addAttribute(new OpersIOAttribute(
						semanticAssetOperGroupRelation.getIdentifier(),
						attribute.getName(), true));

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semOperationalization);

		InstConcept instVertexAssOPERGR = new InstConcept("AssetOperN-ary",
				semanticAssetOperGroupRelation,
				DefaultOpersMM.metaMetaInstOverTwoRel);

		refas.getVariabilityVertex().put("AssetOperN-ary", instVertexAssOPERGR);

		InstConcept instAssetOperAOGR = new InstConcept(
				"AssetToAssetOperN-ary",
				DefaultOpersMM.metaMetaPairwiseRelation);
		refas.getVariabilityVertex().put("AssetToAssetOperN-ary",
				instAssetOperAOGR);

		// extends
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("opgrtogr", instEdge);
		instEdge.setIdentifier("opgrtogr");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelOCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexAssOPERGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("aopgrtogr", instEdge);
		instEdge.setIdentifier("aopgrtogr");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instAssetOperAOGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assettoOperGR-AAOGR", instEdge);
		instEdge.setIdentifier("assettoOperGR-AAOGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instAssetOperAOGR, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assettoOperGR-AOGRGR", instEdge);
		instEdge.setIdentifier("assettoOperGR-AOGRGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
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

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("142 UpCore - ANDAssOperCoreConcept", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAssOPERGR,
				instVertexOper, instVertexAssOPERGR, "Core", "OCore");

		// verifParentsOperSubActionNormal.addSemanticExpression(t1);

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, "Sel", true, "TrueVal");

		t1 = new OpersExpr("140 Ver/Val - ANDAssOperSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexOper, t1, "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, "Core", true, "TrueVal");

		t1 = new OpersExpr("141 UpCore - ANDAssOperCoreRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, t1, "OCore");

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
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

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, "Sel", true, "FalseVal");

		t1 = new OpersExpr("143 Ver/Val ORAssOperSelRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, t1, "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
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

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
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

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, "Sel", 0);

		t1 = new OpersExpr("144 Ver/Val MUTEXAssOperGrrestric", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, t1, 1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
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

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, null, "Sel", "FalseVal",
				true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAssOPERGR, t1, instVertexAsset,
				"LowRange");

		OpersExpr t2 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, null, "Sel", "FalseVal",
				true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAssOPERGR, t2, instVertexAsset,
				"HighRange");

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexAssOPERGR, t1, t2);

		t1 = new OpersExpr("146 Ver/Val RANGEHardRel", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexAssOPERGR, instVertexAssOPERGR, "OSel", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, null, "TrueVal", 0, true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAssOPERGR, t1, instVertexAsset,
				"LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, null, "Core", "TrueVal",
				true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAssOPERGR, t2, instVertexAsset,
				"OCore");

		t1 = new OpersExpr("147 UpCore - ANDFCRel", refas
				.getSemanticExpressionTypes().get("Implies"),
				instVertexAssOPERGR, t1, t2);

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("R2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, null, "Sel", 0, true);

		t1 = new OpersExpr("#NEW wrong card low", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAssOPERGR, t2,
				instVertexAssOPERGR, "LowRange", "This relation"
						+ " has a wrong cardinality in the LowRange value");

		DefaultOpersMM.wrongCardOperSubActionRelaxable
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("R2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAsset, null, "Sel", 0, true);

		t1 = new OpersExpr("#NEW wrong card high", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAssOPERGR, instVertexAssOPERGR, t2,
				instVertexAssOPERGR, "HighRange", "This relation"
						+ " has a wrong cardinality in the HighRange value");

		DefaultOpersMM.wrongCardOperSubActionRelaxable
				.addSemanticExpression(t1);
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
		DefaultOpersMM.sasverNoLoopsOperationSubActionMV
				.addOutAttribute(new OpersIOAttribute(
						semanticAssetLfGroupRelation.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(
						semanticAssetLfGroupRelation.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperMVUniqueLabeling
				.addAttribute(new OpersIOAttribute(semanticAssetLfGroupRelation
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperRedUniqueLabeling
				.addAttribute(new OpersIOAttribute(semanticAssetLfGroupRelation
						.getIdentifier(), attribute.getName(), true));

		InstConcept instVertexAsLFGR = new InstConcept("AssetLfOT",
				semanticAssetLfGroupRelation,
				DefaultOpersMM.metaMetaInstOverTwoRel);

		refas.getVariabilityVertex().put("AssetLfOT", instVertexAsLFGR);

		InstConcept instLFOperAOGR = new InstConcept("AssetLfToOT",
				DefaultOpersMM.metaMetaPairwiseRelation);
		refas.getVariabilityVertex().put("AssetLfToOT", instLFOperAOGR);

		// extends
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("lfgrtogrhr", instEdge);
		instEdge.setIdentifier("lfgrtogrhr");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelOCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaOT, true);
		instEdge.setSourceRelation(instVertexAsLFGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("lfdstogr", instEdge);
		instEdge.setIdentifier("lfdstogr");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instLFOperAOGR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assettolfGR-AAOGR", instEdge);
		instEdge.setIdentifier("assettolfGR-AAOGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instLFOperAOGR, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("assettolfGR-AOGRGR", instEdge);
		instEdge.setIdentifier("assettolfGR-AOGRGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
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

		t1 = new OpersExpr("NA (Ver/Val ANDfeatSetConcept)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsLFGR,
				DefaultOpersMM.instVertexF, instVertexAsLFGR, "Sel", "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("NA (UpCore ANDFeOpCoreConcept)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsLFGR,
				DefaultOpersMM.instVertexF, instVertexAsLFGR, "Core", "OCore");

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, "Sel", true, "TrueVal");

		t1 = new OpersExpr("NA (Ver/Val - ANDOperOperGrRel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexAsLFGR,
				DefaultOpersMM.instVertexF, t1, "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, "Core", true, "TrueVal");

		t1 = new OpersExpr("NA (ANDOperOperGrCoreRel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexAsLFGR,
				DefaultOpersMM.instVertexF, t1, "OCore");

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("and", new ElemAttribute("and",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "and", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("NA (OROperConcept)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsLFGR,
				DefaultOpersMM.instVertexF, instVertexAsLFGR, "Sel", "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get("Or"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, "Sel", true, "False");

		t1 = new OpersExpr("NA (ORAssetRel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexAsLFGR,
				instVertexAsset, t1, "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("or", new ElemAttribute("or",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "or", "",
				"", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("NA (MUTEXOperConcept)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsLFGR,
				DefaultOpersMM.instVertexF, instVertexAsLFGR, "Sel", "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsset, instVertexAsset, "Sel", 0);

		t1 = new OpersExpr("sub2opergrsel", refas.getSemanticExpressionTypes()
				.get("Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, t1, 1);

		t1 = new OpersExpr("NA (MUTEXhardRel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexAsLFGR, DefaultOpersMM.instVertexF, "Sel", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, "Sel", 0);

		t1 = new OpersExpr("NA (MUTEXrestric)", refas
				.getSemanticExpressionTypes().get("LessOrEquals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexAsLFGR,
				instVertexAsset, t1, 1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("mutex", new ElemAttribute("mutex",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "mutex",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("NA (RANGEOperConcept)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTCONCEPTVARIABLE, instVertexAsLFGR,
				DefaultOpersMM.instVertexF, instVertexAsLFGR, "Sel", "OSel");

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, null, "Sel", "FalseVal",
				true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsLFGR, t2, instVertexAsset,
				"LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, null, "Sel", "FalseVal",
				true);

		OpersExpr t3 = new OpersExpr("1", refas.getSemanticExpressionTypes()
				.get("LessOrEquals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexAsLFGR,
				instVertexAsLFGR, t2, instVertexAsset, "HighRange");

		t1 = new OpersExpr("3", refas.getSemanticExpressionTypes().get("And"),
				instVertexAsLFGR, t1, t3);

		t1 = new OpersExpr("NA (RANGEHardRel)", refas
				.getSemanticExpressionTypes().get("DoubleImplies"),
				instVertexAsLFGR, instVertexAsLFGR, "OSel", true, t1);

		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, null, "TrueVal", 0, true);

		t1 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsLFGR, t1,
				DefaultOpersMM.instVertexF, "LowRange");

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get("And"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, null, "Core", "TrueVal",
				true);

		t2 = new OpersExpr("1", refas.getSemanticExpressionTypes().get(
				"DoubleImplies"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsLFGR, t2, instVertexAsset,
				"OCore");

		t1 = new OpersExpr("NA ** ANDFCRel", refas.getSemanticExpressionTypes()
				.get("Implies"), instVertexAsLFGR, t1, t2);

		DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("R2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, null, "Sel", 0, true);

		t1 = new OpersExpr("#NEW wrong card low", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexAsLFGR,
				instVertexAsLFGR, t2, instVertexAsLFGR, "LowRange",
				"This relation"
						+ " has a wrong cardinality in the LowRange value");

		DefaultOpersMM.wrongCardOperSubActionRelaxable
				.addSemanticExpression(t1);
		semExpr.add(t1);

		t2 = new OpersExpr("R2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTSUBITERINCCONVARIABLE,
				instVertexAsLFGR, instVertexAsset, null, "Sel", 0, true);

		t1 = new OpersExpr("#NEW wrong card high", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERINCCONVARIABLE, instVertexAsLFGR,
				instVertexAsLFGR, t2, instVertexAsLFGR, "HighRange",
				"This relation"
						+ " has a wrong cardinality in the HighRange value");

		DefaultOpersMM.wrongCardOperSubActionRelaxable
				.addSemanticExpression(t1);
		semExpr.add(t1);

		ias.add(new InstAttribute("range", new ElemAttribute("range",
				StringType.IDENTIFIER, AttributeType.OPTION, false, "range",
				"", "", 1, -1, "", "", -1, "", ""), semExpr));

		OpersConcept groupAssetOperSemanticEdge = new OpersConcept(
				"AssetOperN-aryToOper");

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

		attribute = new ElemAttribute("pOutAnaSel", "Boolean",
				AttributeType.OPERATION, false,
				"Selected for SD verifications", "", false, 0, -1, "", "", -1,
				"level#all#", "");
		groupAssetOperSemanticEdge
				.putSemanticAttribute("pOutAnaSel", attribute);
		DefaultOpersMM.sasverNoLoopsOperationSubActionMV
				.addOutAttribute(new OpersIOAttribute(
						groupAssetOperSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperationSubActionRed
				.addOutAttribute(new OpersIOAttribute(
						groupAssetOperSemanticEdge.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.sasverNoLoopsOperMVUniqueLabeling
				.addAttribute(new OpersIOAttribute(groupAssetOperSemanticEdge
						.getIdentifier(), attribute.getName(), true));

		DefaultOpersMM.sasverNoLoopsOperRedUniqueLabeling
				.addAttribute(new OpersIOAttribute(groupAssetOperSemanticEdge
						.getIdentifier(), attribute.getName(), true));

		InstConcept instAssetOperGRAO = new InstConcept("AssetOperN-aryToOper",
				DefaultOpersMM.metaMetaPairwiseRelation,
				groupAssetOperSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("aofottoip", instEdge);
		instEdge.setIdentifier("aofottoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instAssetOperGRAO, true);

		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("aogrtogr", instEdge);
		// instEdge.setIdentifier("aogrtogr");
		// instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		// instEdge.setTargetRelation(DefaultOpersMM.instNmMetaOT, true);
		// instEdge.setSourceRelation(instAssetOperAOGR, true);

		ia = instAssetOperGRAO.getInstAttribute("relTypesAttr");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("implementedBy", new ElemAttribute(
				"implemented by", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implemented by", "", "", 1, -1, "", "", -1, "", ""),
				"implementedBy#implemented by#true#true#true#0#-1#0#1"));

		ia = instAssetOperGRAO.getInstAttribute("opersExprs");
		ias = (List<InstAttribute>) ia.getValue();

		semExpr = new ArrayList<OpersExpr>();

		t1 = new OpersExpr("132 (Ver/Val - AssetOperGRIMPSel)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instAssetOperGRAO, instVertexAssOPERGR, instVertexOper, "OSel",
				"Sel");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("133 (Ver/Val IMPNotAvailable)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instAssetOperGRAO, instVertexOper, instVertexAssOPERGR,
				"Exclu", "Exclu");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instAssetOperGRAO, instVertexOper, "structVal", 1);

		t1 = new OpersExpr(
				"134 (NoLoop structVal)",
				"To eliminate the structural loop remove this structural relation (mandatory between "
						+ "#source# and #target#) or remove another relation with error mark.",
				refas.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instAssetOperGRAO, instVertexAssOPERGR, "structVal", true, t1);

		semExpr.add(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionMVRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedToVerify
				.addSemanticExpression(t1);

		ias.add(new InstAttribute("implementedBy", new ElemAttribute(
				"implementedBy", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implemented by", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		refas.getVariabilityVertex().put("AssetOperN-aryToOper",
				instAssetOperGRAO);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("OPERGRtooper-OOGR", instEdge);
		instEdge.setIdentifier("OPERGRtooper-OOGR");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instAssetOperGRAO, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("OPERGRtooper-OGRO", instEdge);
		instEdge.setIdentifier("OPERGRtooper-OGRO");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instAssetOperGRAO, true);
		instEdge.setSourceRelation(instVertexAssOPERGR, true);

		InstConcept instAssetLfGRAO = new InstConcept("AssetLfOTToLf",
				DefaultOpersMM.metaMetaPairwiseRelation,
				groupAssetOperSemanticEdge);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("aofottoip", instEdge);
		instEdge.setIdentifier("aofottoip");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		instEdge.setTargetRelation(DefaultOpersMM.instNmMetaPW, true);
		instEdge.setSourceRelation(instAssetLfGRAO, true);

		// instEdge = new InstPairwiseRel();
		// refas.getConstraintInstEdges().put("aogrtogr", instEdge);
		// instEdge.setIdentifier("aogrtogr");
		// instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelCCExt);
		// instEdge.setTargetRelation(DefaultOpersMM.instNmMetaOT, true);
		// instEdge.setSourceRelation(instAssetOperAOGR, true);

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
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("NA IMPNotAvailable", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instAssetLfGRAO, instVertexAsset, instVertexAsLFGR, "Exclu",
				"Exclu");

		semExpr.add(t1);
		DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifDeadElemSubOperNormal.addSemanticExpression(t1);
		DefaultOpersMM.verifFalseOptOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSDneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClCoreOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverClallOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverClneverOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverCoreOpersOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverAllOpersOperSubActionNormal
				.addSemanticExpression(t1);
		// sasverNoLoopsOperSubActionNormal.addSemanticExpression(t1);
		DefaultOpersMM.sasverSGConflOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClSDOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflClOperSubActionNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverConflSDOperSubActionNormal
				.addSemanticExpression(t1);

		t1 = new OpersExpr("2", refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE, instAssetLfGRAO,
				instVertexAsLFGR, "structVal", 1);

		t1 = new OpersExpr("NA 2", refas.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instAssetLfGRAO, instVertexAsset, "structVal", true, t1);

		semExpr.add(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionMVRelaxable
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedNormal
				.addSemanticExpression(t1);
		DefaultOpersMM.sasverNoLoopsOperSubActionRedToVerify
				.addSemanticExpression(t1);

		ias.add(new InstAttribute("implementedBy", new ElemAttribute(
				"implementedBy", StringType.IDENTIFIER, AttributeType.OPTION,
				false, "implementedBy", "", "", 1, -1, "", "", -1, "", ""),
				semExpr));

		refas.getVariabilityVertex().put("AssetLfOTToLf", instAssetLfGRAO);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("AssetLfOTToLf-from", instEdge);
		instEdge.setIdentifier("AssetLfOTToLf-from");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(DefaultOpersMM.instVertexF, true);
		instEdge.setSourceRelation(instAssetLfGRAO, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("AssetLfOTToLf-to", instEdge);
		instEdge.setIdentifier("AssetLfOTToLf-to");
		instEdge.setSupportMetaPairwiseRelation(DefaultOpersMM.metaPairwRelAso);
		instEdge.setTargetRelation(instAssetLfGRAO, true);
		instEdge.setSourceRelation(instVertexAsLFGR, true);

	}

	static void createREFASMetaConcept(InstanceModel refas) {
		ArrayList<OpersExpr> semExpr = new ArrayList<OpersExpr>();

		ElemAttribute attribute = null;

		attribute = new ElemAttribute("TotalOrder", "Integer",
				AttributeType.EXECCURRENTSTATE, "***TotalOrder***", "", 0,
				false, new RangeDomain(0, 2000, 0), 2, -1, "", "", -1, "", "");
		// simulationExecOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// refasModel.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.generalModel.putSemanticAttribute("TotalOrder",
				attribute);

		attribute = new ElemAttribute("TrueVal", " Booelan",
				AttributeType.EXECCURRENTSTATE, false, "***TrueVal***", "",
				true, 2, -1, "", "", -1, "", "");
		// simulationExecOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// refasModel.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.generalModel.putSemanticAttribute("TrueVal", attribute);

		DefaultOpersMM.variabfactorSubOperationAction2
				.addOutAttribute(new OpersIOAttribute(
						DefaultOpersMM.generalModel.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.variabfactorOperUniqueLabeling2
				.addAttribute(new OpersIOAttribute(DefaultOpersMM.generalModel
						.getIdentifier(), attribute.getName(), true));

		// "totalAnaSel" change to, totalAnaSel instead
		attribute = new ElemAttribute("totalAnaSel", "Integer",
				AttributeType.EXECCURRENTSTATE, "***TotalAnalysisSelected***",
				"", 0, false, new RangeDomain(0, 2000, 0), 2, -1, "", "", -1,
				"", "");
		DefaultOpersMM.generalModel.putSemanticAttribute("totalAnaSel",
				attribute);
		DefaultOpersMM.lcaSubOperationAction
				.addOutAttribute(new OpersIOAttribute(
						DefaultOpersMM.generalModel.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.lcaOperUniqueLabeling.addAttribute(new OpersIOAttribute(
				DefaultOpersMM.generalModel.getIdentifier(), attribute
						.getName(), true));
		DefaultOpersMM.variabfactorSubOperationAction2
				.addOutAttribute(new OpersIOAttribute(
						DefaultOpersMM.generalModel.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.variabfactorOperUniqueLabeling2
				.addAttribute(new OpersIOAttribute(DefaultOpersMM.generalModel
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.ecrSubOperationAction1
				.addOutAttribute(new OpersIOAttribute(
						DefaultOpersMM.generalModel.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.ecrOperUniqueLabeling1
				.addAttribute(new OpersIOAttribute(DefaultOpersMM.generalModel
						.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.ecrSubOperationAction2
				.addOutAttribute(new OpersIOAttribute(
						DefaultOpersMM.generalModel.getIdentifier(), attribute
								.getName(), true));
		DefaultOpersMM.ecrOperUniqueLabeling2
				.addAttribute(new OpersIOAttribute(DefaultOpersMM.generalModel
						.getIdentifier(), attribute.getName(), true));
		// simulationExecOperUniqueLabeling.addAttribute(new
		// OpersIOAttribute(
		// refasModel.getIdentifier(), attribute.getName(), true));
		DefaultOpersMM.generalModel.putSemanticAttribute("totalAnaSel",
				attribute);

		semExpr = new ArrayList<OpersExpr>();

		DefaultOpersMM.simsceExecOperLab2.setSemanticExpressions(semExpr);

		// FIXME Change to GeneralModel
		refas.getVariabilityVertex().put("GeneralModel",
				DefaultOpersMM.instGeneralModel);

		OpersExpr t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFSUBTITERCONVARIABLE,
				DefaultOpersMM.instGeneralModel, DefaultOpersMM.instVertexSG,
				"Sel", 0);

		t1 = new OpersExpr("max soft goals", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
				DefaultOpersMM.instGeneralModel, DefaultOpersMM.instVertexSG,
				t1, 0);

		// semExpr.add(t1); // FIXME not used

		// ----------------------

		semExpr = new ArrayList<OpersExpr>();

		DefaultOpersMM.generalModel.setSemanticExpressions(semExpr);

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
		// DefaultOpersMM.simulExecOptSubOperNormal.addSemanticExpression(t1);
		// DefaultOpersMM.simulScenExecOptSubOperNormal.addSemanticExpression(t1);

		// semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFSUBTITERCONVARIABLE,
				DefaultOpersMM.instGeneralModel, DefaultOpersMM.instVertexF,
				"IsRootFeature", 0);

		t1 = new OpersExpr("022 (NEW Roots)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
				DefaultOpersMM.instGeneralModel, DefaultOpersMM.instVertexF,
				t1, 1);

		DefaultOpersMM.verifRootSubOperVeri.addSemanticExpression(t1);

		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFSUBTITERCONVARIABLE,
				DefaultOpersMM.instGeneralModel, DefaultOpersMM.instVertexF,
				"TrueVal", 0);

		t1 = new OpersExpr("023 (NEW totalAnSel Den)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
				DefaultOpersMM.instGeneralModel,
				DefaultOpersMM.instGeneralModel, t1, "totalAnaSel");

		DefaultOpersMM.variabfactorOperSubActionNormal2
				.addSemanticExpression(t1);
		DefaultOpersMM.ecrOperSubActionNormal2.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFSUBTITERCONVARIABLE,
				DefaultOpersMM.instGeneralModel, DefaultOpersMM.instVertexF,
				"inAnaSel", 0);

		t1 = new OpersExpr("024 (NEW inAnaSel total)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
				DefaultOpersMM.instGeneralModel,
				DefaultOpersMM.instGeneralModel, t1, "totalAnaSel");

		DefaultOpersMM.lcaSubOperNormal.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFSUBTITERCONVARIABLE,
				DefaultOpersMM.instGeneralModel, DefaultOpersMM.instVertexF,
				"outAnaSel", 0);

		t1 = new OpersExpr("025 (NEW outAnaSel total)", refas
				.getSemanticExpressionTypes().get("Equals"),
				ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
				DefaultOpersMM.instGeneralModel,
				DefaultOpersMM.instGeneralModel, t1, "totalAnaSel");

		DefaultOpersMM.ecrOperSubActionNormal1.addSemanticExpression(t1);
		semExpr.add(t1);

		t1 = new OpersExpr("sub", refas.getSemanticExpressionTypes().get(
				"Product"), ExpressionVertexType.LEFSUBTITERCONVARIABLE,
				DefaultOpersMM.instGeneralModel, DefaultOpersMM.instVertexF,
				"HasParent", 1);

		t1 = new OpersExpr("Parents", refas.getSemanticExpressionTypes().get(
				"Less"), ExpressionVertexType.LEFTITERCONCEPTVARIABLE,
				DefaultOpersMM.instGeneralModel, DefaultOpersMM.instVertexF,
				t1, 1);

		// verifParentsOperSubActionVerification.addSemanticExpression(t1);

		// semExpr.add(t1);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFSUBTITERCONVARIABLE,
				DefaultOpersMM.instGeneralModel, DefaultOpersMM.instVertexGE,
				"Core", 0);

		t1 = new OpersExpr("Core", refas.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTITERINCCONVARIABLE,
				DefaultOpersMM.instGeneralModel,
				DefaultOpersMM.instGeneralModel, t1, "TotalOrder");

		// DefaultOpersMM.updCoreOptSubOperNormal.addSemanticExpression(t1);

		// semExpr.add(t1);

		// --------------------------
		semExpr = new ArrayList<OpersExpr>();
		DefaultOpersMM.simulExecOperUniLab.setSemanticExpressions(semExpr);

		t1 = new OpersExpr("sub",
				refas.getSemanticExpressionTypes().get("Sum"),
				ExpressionVertexType.LEFSUBTITERCONVARIABLE,
				DefaultOpersMM.instGeneralModel, DefaultOpersMM.instVertexGE,
				"Order", 0);

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

		semExpr = new ArrayList<OpersExpr>();
		DefaultOpersMM.simsceExecOperLab2.setSemanticExpressions(semExpr);

		t1 = new OpersExpr("OrderLab...", refas.getSemanticExpressionTypes()
				.get("Sum"), ExpressionVertexType.LEFSUBTITERCONVARIABLE,
				DefaultOpersMM.instGeneralModel, DefaultOpersMM.instVertexSG,
				"Sel", 0);

		semExpr.add(t1);
	}
}
