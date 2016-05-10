package com.variamos.dynsup.defaultmodels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersPairwiseRel;
import com.variamos.dynsup.model.OpersRelType;
import com.variamos.dynsup.model.SyntaxConcept;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.model.SyntaxPairwiseRel;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.OperationSubActionExecType;
import com.variamos.dynsup.types.OperationSubActionType;
import com.variamos.hlcl.IntervalDomain;

public class InfraSyntaxOpersMMM {

	public static void createSyntaxOpersMetaMetaModel(
			ModelInstance modelInstance) {

		Map<String, InstElement> variabilityInstVertex = modelInstance
				.getVariabilityVertex();

		Map<String, InstPairwiseRel> constraintInstEdges = modelInstance
				.getConstraintInstEdges();

		// Begin Syntax M3 model
		SyntaxElement basicOpersSyntaxM3Concept = modelInstance
				.getSyntaxModel().getVertex("OMMConcept").getEdSyntaxEle();

		// SyntaxConcept basicOpersSyntaxM3Concept = new SyntaxConcept('C',
		// "BasicOpersSyntaxM3Concept", true, true,
		// "BasicOpersSyntaxM3Concept", "infrabasicsyntaxm3miniconcept",
		// "Operations Meta Meta Meta Concept", 180, 180,
		// "/com/variamos/gui/perspeditor/images/concept.png", true,
		// Color.BLUE.toString(), 3, null, true);
		//
		// basicOpersSyntaxM3Concept.addPanelVisibleAttribute("04#"
		// + SyntaxConcept.VAR_USERIDENTIFIER);
		// basicOpersSyntaxM3Concept.addPanelSpacersAttribute("#"
		// + SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");
		//
		// basicOpersSyntaxM3Concept.addModelingAttribute("Name",
		// new ElemAttribute("Name", "String", AttributeType.SYNTAX,
		// false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		// basicOpersSyntaxM3Concept.addModelingAttribute("Description",
		// new ElemAttribute("Description", "String",
		// AttributeType.SYNTAX, false, "Description", "", 0, -1,
		// "", "", -1, "", ""));
		//
		// basicOpersSyntaxM3Concept.addModelingAttribute("MetaType",
		// new ElemAttribute("MetaType", "Enumeration",
		// AttributeType.SYNTAX, false, "SyntaxConcept Type",
		// ConceptType.class.getCanonicalName(), "SyntaxConcept",
		// "", 0, -1, "", "", -1, "", ""));
		// // metaBasicConcept.addModelingAttribute("Identifier",
		// // new ElemAttribute("Identifier", "String", false,
		// // "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		// basicOpersSyntaxM3Concept.addModelingAttribute("Visible",
		// new ElemAttribute("Visible", "Boolean", AttributeType.SYNTAX,
		// false, "Visible", true, 0, -1, "", "", -1, "", ""));
		// basicOpersSyntaxM3Concept.addModelingAttribute("Name",
		// new ElemAttribute("Name", "String", AttributeType.SYNTAX,
		// false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		// basicOpersSyntaxM3Concept.addModelingAttribute("Style",
		// new ElemAttribute("Style", "String", AttributeType.SYNTAX,
		// false, "Drawing Style", "refasclaim", 0, -1, "", "",
		// -1, "", ""));
		// basicOpersSyntaxM3Concept
		// .addModelingAttribute("Width", new ElemAttribute("Width",
		// "Integer", AttributeType.SYNTAX, false,
		// "Initial Width", 100, 0, -1, "", "", -1, "", ""));
		// basicOpersSyntaxM3Concept
		// .addModelingAttribute("Height", new ElemAttribute("Height",
		// "Integer", AttributeType.SYNTAX, false,
		// "Initial Height", 40, 0, -1, "", "", -1, "", ""));
		// basicOpersSyntaxM3Concept.addModelingAttribute("Image",
		// new ElemAttribute("Image", "String", AttributeType.SYNTAX,
		// false, "Image File",
		// "/com/variamos/gui/perspeditor/images/claim.png", 0,
		// -1, "", "", -1, "", ""));
		// basicOpersSyntaxM3Concept.addModelingAttribute("TopConcept",
		// new ElemAttribute("TopConcept", "Boolean",
		// AttributeType.SYNTAX, false, "Is Top Concept", true, 0,
		// -1, "", "", -1, "", ""));
		// basicOpersSyntaxM3Concept.addModelingAttribute("BackgroundColor",
		// new ElemAttribute("BackgroundColor", "String",
		// AttributeType.SYNTAX, false, "Background Color",
		// "java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
		// ""));
		// basicOpersSyntaxM3Concept.addModelingAttribute("BorderStroke",
		// new ElemAttribute("BorderStroke", "Integer",
		// AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
		// "", "", -1, "", ""));
		// basicOpersSyntaxM3Concept
		// .addModelingAttribute("Resizable", new ElemAttribute(
		// "Resizable", "Boolean", AttributeType.SYNTAX, false,
		// "Is Resizable", true, 0, -1, "", "", -1, "", ""));
		//
		// SyntaxPairwiseRel basicOpersSyntaxM3ExtendsRelation = new
		// SyntaxPairwiseRel(
		// "ExtendsRelation",
		// false,
		// true,
		// "Extends Relation",
		// "refasextends",
		// "Extends relation: relates to concepts to extend attributes and operation constraints",
		// 50, 50, "/com/variamos/gui/pl/editor/images/plnode.png", 1);

		// End Syntax M3 Model

		// Begin Basic M2 Model

		// OpersConcept basicOpersM2Concept = new OpersConcept();

		// FOR SD and CLAIMS - Review to change

		// semConcept.putSemanticAttribute("identifier", new ElemAttribute(
		// "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// "", "", -1, "", ""));
		// semConcept.addPropEditableAttribute("01#" + "identifier");
		// semConcept.addPropVisibleAttribute("01#" + "identifier");

		// semConcept.addPanelVisibleAttribute("01#" + "identifier");
		// semConcept.addPanelSpacersAttribute("#" + "identifier" + "#\n\n");

		// InstConcept instBasicOpersM2Concept = new InstConcept(
		// "basicOpersM2Concept", null, basicOpersM2Concept);

		// OpersConcept basicOpersM2PWRel = new
		// OpersConcept("BasicOpersM2PWRel");

		// semPairwiseRelation.putSemanticAttribute("enumType",
		// new SemanticAttribute("enumType", "Class",
		// AttributeType.OPERATION, false, "enumerationX",
		// InstEnumeration.class.getCanonicalName(),
		// "TypeEnumeration", "String", "", 0, -1, "", "", -1, "",
		// ""));
		//
		// semPairwiseRelation.addPropEditableAttribute("03#" +
		// "enumType");
		// semPairwiseRelation.addPropVisibleAttribute("03#" +
		// "enumType");

		// basicOpersM2PWRel.putSemanticAttribute("relTypesAttr",
		// new ElemAttribute("relTypesAttr", "Set",
		// AttributeType.SYNTAX, false, "relationTypes",
		// InstAttribute.class.getCanonicalName(),
		// new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
		// ""));
		//
		// basicOpersM2PWRel.putSemanticAttribute("opersExprs",
		// new ElemAttribute("opersExprs", "Set",
		// AttributeType.SYNTAX, false, "semanticExpressions",
		// InstAttribute.class.getCanonicalName(),
		// new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
		// ""));
		//
		// InstConcept instBasicOpersM2PWRel = new InstConcept(
		// "BasicOpersM2PWRel", null, basicOpersM2PWRel);

		// InstPairwiseRel instEdge = new InstPairwiseRel();
		// instEdge.setIdentifier("pwtoc");
		// instEdge.setSupportMetaPairwiseRelation(basicOpersSyntaxM3ExtendsRelation);
		// instEdge.setTargetRelation(instBasicOpersM2Concept, true);
		// instEdge.setSourceRelation(instBasicOpersM2PWRel, true);

		// OpersPairwiseRel basicOpersM2ExtendsRelation = new OpersPairwiseRel(
		// "ExtRel", false, null);

		// InstConcept instBasicOpersM2ExtendsRelation = new InstConcept(
		// "ExtendsRelation", basicOpersSyntaxM3Concept,
		// basicOpersM2ExtendsRelation);
		// OpersConcept basicOpersM2OTRel = new
		// OpersConcept("basicOpersM2OTRel");

		// semOverTwoRelation.putSemanticAttribute("enumType",
		// new SemanticAttribute("enumType", "Class",
		// AttributeType.OPERATION, false, "enumerationX",
		// InstEnumeration.class.getCanonicalName(),
		// "TypeEnumeration", "String", "", 0, -1, "", "", -1, "",
		// ""));
		//
		// semOverTwoRelation.addPropEditableAttribute("03#" +
		// "enumType");
		// semOverTwoRelation.addPropVisibleAttribute("03#" +
		// "enumType");

		// basicOpersM2OTRel.putSemanticAttribute("relTypesAttr",
		// new ElemAttribute("relTypesAttr", "Set",
		// AttributeType.SYNTAX, false, "relationTypes",
		// InstAttribute.class.getCanonicalName(),
		// new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
		// ""));
		//
		// basicOpersM2OTRel.putSemanticAttribute("opersExprs",
		// new ElemAttribute("opersExprs", "Set",
		// AttributeType.SYNTAX, false, "semanticExpressions",
		// InstAttribute.class.getCanonicalName(),
		// new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
		// ""));

		// InstConcept instBasicOpersM2OTRel = new InstConcept(
		// "basicOpersM2OTRel", null, basicOpersM2OTRel);
		//
		// InstPairwiseRel instEdge = new InstPairwiseRel();
		// instEdge.setIdentifier("otrtoc");
		// instEdge.setSupportMetaPairwiseRelation(basicOpersSyntaxM3ExtendsRelation);
		// instEdge.setTargetRelation(instBasicOpersM2Concept, true);
		// instEdge.setSourceRelation(instBasicOpersM2OTRel, true);

		// OpersConcept basicOpersM2SemModel = new OpersConcept(
		// "BasicOpersM2Model");
		//
		// basicOpersM2SemModel.putSemanticAttribute("name", new ElemAttribute(
		// "name", "String", AttributeType.OPERATION, false, "Name", null,
		// 0, 6, "", "", 6, "", ""));
		// basicOpersM2SemModel.addPropEditableAttribute("06#" + "name");
		// basicOpersM2SemModel.addPropVisibleAttribute("06#" + "name");
		// basicOpersM2SemModel.addPanelVisibleAttribute("06#" + "name");
		// basicOpersM2SemModel.addPanelSpacersAttribute("#" + "name" + "#");
		//
		// InstConcept instBasicOpersM2SemModel = new InstConcept(
		// "BasicOpersM2Model", null, basicOpersM2SemModel);

		// OpersConcept basicOpebrsM2OperGroup = new OpersConcept(
		// "BasicOpersM2OperGroup");

		// 4 config/simul
		// 2 req model
		List<Integer> dom = new ArrayList<Integer>();
		dom.add(2);
		dom.add(4);
		IntervalDomain d = new IntervalDomain();
		d.setRangeValues(dom);

		// basicOpebrsM2OperGroup.putSemanticAttribute("menuType",
		// new ElemAttribute("menuType", "Integer",
		// AttributeType.OPERATION, "Oper Group Type", 4, false,
		// d, 0, 5, "", "", 5, "", ""));
		// basicOpebrsM2OperGroup.addPropEditableAttribute("05#" + "menuType");
		// basicOpebrsM2OperGroup.addPropVisibleAttribute("05#" + "menuType");
		//
		// basicOpebrsM2OperGroup.putSemanticAttribute("visible",
		// new ElemAttribute("visible", "Boolean",
		// AttributeType.OPERATION, false, "Visible", true, 0, 8,
		// "", "", 8, "", ""));
		// basicOpebrsM2OperGroup.addPropEditableAttribute("08#" + "visible");
		// basicOpebrsM2OperGroup.addPropVisibleAttribute("08#" + "visible");
		//
		// basicOpebrsM2OperGroup.putSemanticAttribute("name", new
		// ElemAttribute(
		// "name", "String", AttributeType.OPERATION, false, "Name", null,
		// 0, 6, "", "", 6, "", ""));
		// basicOpebrsM2OperGroup.addPropEditableAttribute("06#" + "name");
		// basicOpebrsM2OperGroup.addPropVisibleAttribute("06#" + "name");
		// basicOpebrsM2OperGroup.addPanelVisibleAttribute("06#" + "name");
		// basicOpebrsM2OperGroup.addPanelSpacersAttribute("#" + "name" + "#");
		//
		// basicOpebrsM2OperGroup.putSemanticAttribute("shortcut",
		// new ElemAttribute("shortcut", "String",
		// AttributeType.OPERATION, false, "Shortcut", null, 0, 7,
		// "", "", 7, "", ""));
		// basicOpebrsM2OperGroup.addPropEditableAttribute("07#" + "shortcut");
		// basicOpebrsM2OperGroup.addPropVisibleAttribute("07#" + "shortcut");
		//
		// basicOpebrsM2OperGroup.putSemanticAttribute("Index",
		// new ElemAttribute("Index", "Integer", AttributeType.OPERATION,
		// false, "Position", 1, 0, 9, "", "", 9, "", ""));
		// basicOpebrsM2OperGroup.addPropEditableAttribute("09#" + "Index");
		// basicOpebrsM2OperGroup.addPropVisibleAttribute("09#" + "Index");
		//
		// InstConcept instBasicOpersM2OperGroup = new InstConcept(
		// "BasicOpersM2OperGroup", null, basicOpebrsM2OperGroup);

		// OpersConcept basicOpersM2OperAction = new OpersConcept(
		// "BasicOpersM2Operation");
		//
		// basicOpersM2OperAction.putSemanticAttribute("name", new
		// ElemAttribute(
		// "name", "String", AttributeType.OPERATION, false, "Name", null,
		// 0, 6, "", "", 6, "", ""));
		// basicOpersM2OperAction.putSemanticAttribute("shortcut",
		// new ElemAttribute("shortcut", "String",
		// AttributeType.OPERATION, false, "Shortcut", null, 0, 7,
		// "", "", 7, "", ""));
		// basicOpersM2OperAction.putSemanticAttribute("Index",
		// new ElemAttribute("Index", "Integer", AttributeType.OPERATION,
		// false, "Position", 1, 0, 8, "", "", 8, "", ""));
		// basicOpersM2OperAction.putSemanticAttribute("iteration",
		// new ElemAttribute("iteration", "Boolean",
		// AttributeType.OPERATION, false, "Iterate Button",
		// false, 0, 9, "", "", 9, "", ""));
		// basicOpersM2OperAction.putSemanticAttribute("iterationName",
		// new ElemAttribute("iterationName", "String",
		// AttributeType.OPERATION, false, "Iterate Name", null,
		// 0, 6, "", "", 6, "", ""));
		// basicOpersM2OperAction.putSemanticAttribute("prevSpacer",
		// new ElemAttribute("prevSpacer", "Boolean",
		// AttributeType.OPERATION, false, "Add Previous Spacer",
		// false, 0, 9, "", "", 9, "", ""));
		//
		// basicOpersM2OperAction.addPropEditableAttribute("06#" + "name");
		// basicOpersM2OperAction.addPropVisibleAttribute("06#" + "name");
		// basicOpersM2OperAction.addPanelVisibleAttribute("06#" + "name");
		// basicOpersM2OperAction.addPanelSpacersAttribute("#" + "name" + "#");
		//
		// basicOpersM2OperAction.addPropEditableAttribute("07#" + "shortcut");
		// basicOpersM2OperAction.addPropVisibleAttribute("07#" + "shortcut");
		//
		// basicOpersM2OperAction.addPropEditableAttribute("08#" + "Index");
		// basicOpersM2OperAction.addPropVisibleAttribute("08#" + "Index");
		//
		// basicOpersM2OperAction.addPropEditableAttribute("09#" + "iteration");
		// basicOpersM2OperAction.addPropVisibleAttribute("09#" + "iteration");
		//
		// basicOpersM2OperAction
		// .addPropEditableAttribute("09#" + "iterationName");
		// basicOpersM2OperAction.addPropVisibleAttribute("09#" +
		// "iterationName");
		//
		// basicOpersM2OperAction.addPropEditableAttribute("10#" +
		// "prevSpacer");
		// basicOpersM2OperAction.addPropVisibleAttribute("10#" + "prevSpacer");
		//
		// InstConcept instBasicOpersM2OperAction = new InstConcept(
		// "BasicOpersM2Operation", null, basicOpersM2OperAction);
		//
		// OpersConcept basicOpersM2OperSubAction = new OpersConcept(
		// "BasicOpersM2SubOper");
		//
		// basicOpersM2OperSubAction.putSemanticAttribute("name",
		// new ElemAttribute("name", "String", AttributeType.OPERATION,
		// false, "Name", null, 0, 6, "", "", 6, "", ""));
		// basicOpersM2OperSubAction.addPropEditableAttribute("06#" + "name");
		// basicOpersM2OperSubAction.addPropVisibleAttribute("06#" + "name");
		// basicOpersM2OperSubAction.addPanelVisibleAttribute("06#" + "name");
		// basicOpersM2OperSubAction.addPanelSpacersAttribute("#" + "name" +
		// "#");
		//
		// basicOpersM2OperSubAction.putSemanticAttribute("Index",
		// new ElemAttribute("Index", "Integer", AttributeType.OPERATION,
		// false, "Position", 1, 0, 6, "", "", 6, "", ""));
		// basicOpersM2OperSubAction.addPropEditableAttribute("08#" + "Index");
		// basicOpersM2OperSubAction.addPropVisibleAttribute("08#" + "Index");
		//
		// basicOpersM2OperSubAction
		// .putSemanticAttribute("iteration",
		// new ElemAttribute("iteration", "Boolean",
		// AttributeType.OPERATION, false,
		// "Iterate Sub-Operation", false, 0, 6, "", "",
		// 6, "", ""));
		// basicOpersM2OperSubAction.addPropEditableAttribute("09#" +
		// "iteration");
		// basicOpersM2OperSubAction.addPropVisibleAttribute("09#" +
		// "iteration");
		//
		// basicOpersM2OperSubAction.putSemanticAttribute("type",
		// new ElemAttribute("type", "Enumeration",
		// AttributeType.OPERATION, false, "Type",
		// OperationSubActionType.class.getCanonicalName(),
		// "SINGLEUPDATE", 0, 6, "", "", 6, "", ""));
		// basicOpersM2OperSubAction.addPropEditableAttribute("10#" + "type");
		// basicOpersM2OperSubAction.addPropVisibleAttribute("10#" + "type");
		//
		// basicOpersM2OperSubAction.putSemanticAttribute(
		// "exptype",
		// new ElemAttribute("exptype", "Set", AttributeType.SYNTAX,
		// false, "exptype", InstAttribute.class
		// .getCanonicalName(),
		// new ArrayList<InstAttribute>(), 0, 2, "", "", 2, "#\n",
		// ""));
		//
		// InstConcept instBasicOpersM2OperSubAction = new InstConcept(
		// "BasicOpersM2SubOper", null, basicOpersM2OperSubAction);

		// OpersConcept basicOpersM2Labeling = new OpersConcept(
		// "BasicOpersM2Labeling");
		//
		// basicOpersM2Labeling.putSemanticAttribute("labelId",
		// new ElemAttribute("labelId", "String",
		// AttributeType.OPERATION, false, "Label ID", null, 0, 6,
		// "", "", 6, "", ""));
		// basicOpersM2Labeling.putSemanticAttribute("position",
		// new ElemAttribute("position", "Integer",
		// AttributeType.OPERATION, false, "Position", 1, 0, 6,
		// "", "", 6, "", ""));
		// basicOpersM2Labeling.putSemanticAttribute("once", new ElemAttribute(
		// "once", "Boolean", AttributeType.OPERATION, false, "Once",
		// false, 0, 6, "", "", 6, "", ""));
		//
		// basicOpersM2Labeling.putSemanticAttribute(
		// "sortorder",
		// new ElemAttribute("sortorder", "Set", AttributeType.SYNTAX,
		// false, "sortorder", InstAttribute.class
		// .getCanonicalName(),
		// new ArrayList<InstAttribute>(), 0, 2, "", "", 2, "#\n",
		// ""));
		// basicOpersM2Labeling.addPanelVisibleAttribute("04#" + "identifier");
		// basicOpersM2Labeling.addPanelSpacersAttribute("#" + "identifier" +
		// "#");
		//
		// basicOpersM2Labeling.addPropEditableAttribute("06#" + "labelId");
		// basicOpersM2Labeling.addPropVisibleAttribute("06#" + "labelId");
		// basicOpersM2Labeling.addPropEditableAttribute("07#" + "position");
		// basicOpersM2Labeling.addPropVisibleAttribute("07#" + "position");
		// basicOpersM2Labeling.addPropEditableAttribute("08#" + "once");
		// basicOpersM2Labeling.addPropVisibleAttribute("08#" + "once");
		//
		// InstConcept instBasicOpersM2Labeling = new InstConcept(
		// "BasicOpersM2Labeling", null, basicOpersM2Labeling);

		// OpersConcept basicOpersM2ExpType = new OpersConcept(
		// "BasicOpersM2ExpType");
		//
		// basicOpersM2ExpType.putSemanticAttribute("suboperexptype",
		// new ElemAttribute("suboperexptype", "Enumeration",
		// AttributeType.OPERATION, false, "Expression Type",
		// OperationSubActionExecType.class.getCanonicalName(),
		// "NORMAL", 0, 6, "", "", 6, "", ""));
		//
		// InstConcept instBasicOpersM2ExpType = new InstConcept(
		// "BasicOpersM2ExpType", null, basicOpersM2ExpType);

		// End Basic M2 Model

		// Begin Opers M2 Model

		SyntaxConcept infraSyntaxOpersM2InfraConcept = new SyntaxConcept(
				'N',
				"OMInfConcept",
				false,
				false,
				"OMInfConcept",
				"infrasyntaxm2biggrayconcept",
				"Operations Infra MetaMetaConcept: Define Infrastructure (fixed) MMConcepts for the operations",
				100, 250, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2InfraConcept.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2InfraConcept.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n" + "<<non-editable>>"
				+ "\n\n");

		InstConcept instInfraSyntaxOpersM2InfraConcept = new InstConcept(
				"OMInfConcept", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2InfraConcept);

		variabilityInstVertex.put("OMInfConcept",
				instInfraSyntaxOpersM2InfraConcept);

		SyntaxConcept infraSyntaxOpersM2Concept = new SyntaxConcept(
				'C',
				"OMConcept",
				true,
				true,
				"OMConcept",
				"infrasyntaxm2concept",
				"Operations MetaMetaConcept: Define a MMConcept for the operations",
				100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2Concept.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2Concept.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2Concept = new InstConcept(
				"OMConcept", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2Concept);

		variabilityInstVertex.put("OMConcept", instInfraSyntaxOpersM2Concept);

		// SyntaxPairwiseRel metaMetaPairwiseRelExtends = new SyntaxPairwiseRel(
		// "ExtendsRelation",
		// false,
		// true,
		// "Extends Relation",
		// "refasextends",
		// "Extends relation: relates to concepts to extend attributes and operation constraints",
		// 50, 50, "/com/variamos/gui/pl/editor/images/plnode.png", 1,
		// null);

		// InstPairwiseRel rel = new
		// InstPairwiseRel(basicOpersM2ExtendsRelation);
		// rel.setEditableMetaElement(metaMetaPairwiseRelExtends);
		// rel.setIdentifier("ExtendsCCRel");
		// rel.setTargetRelation(instBasicOpersM2Concept, true);
		// rel.setSourceRelation(instBasicOpersM2PWRel, true);

		SyntaxConcept infraSyntaxOpersM2PWRel = new SyntaxConcept(
				'W',
				"OMInfraPWRel",
				false,
				false,
				"OMInfraPWRel",
				"infrasyntaxm2minigrayconcept",
				"Operations Infrastructure (fixed) MMPairWise Relation: Defines a direct relation for the operations meta-model",
				150, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2PWRel.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2PWRel.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n" + "<<non-editable>>"
				+ "\n\n");

		infraSyntaxOpersM2PWRel.addModelingAttribute(
				"relTypesAttr",
				new ElemAttribute("relTypesAttr", "Set", AttributeType.SYNTAX,
						false, "relationTypes", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2PWRel.addModelingAttribute("opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expressions",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		InstConcept instInfraSyntaxOpersM2PWRel = new InstConcept(
				"OMInfraPWRel", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2PWRel);
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("OMInfraPWRel", instInfraSyntaxOpersM2PWRel);

		SyntaxConcept metaMetaPairwiseRel = new SyntaxConcept(
				'P',
				"OMPWRel",
				true,
				true,
				"OMPWRel",
				"infrasyntaxopersm2miniconcept",
				"Operations MMPairWise Relation: Defines a direct relation for the operations meta-model",
				150, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		metaMetaPairwiseRel.addModelingAttribute(
				"relTypesAttr",
				new ElemAttribute("relTypesAttr", "Set", AttributeType.SYNTAX,
						false, "relationTypes", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		metaMetaPairwiseRel.addModelingAttribute("opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expressions",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		metaMetaPairwiseRel.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		metaMetaPairwiseRel.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instPairWiseRelation = new InstConcept("OMPWRel",
				basicOpersSyntaxM3Concept, metaMetaPairwiseRel);
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("OMPWRel", instPairWiseRelation);

		/*
		 * SemanticConcept semEnumeration = new SemanticConcept(semConcept,
		 * "Enumeration"); semEnumeration.setSemanticAttribute("value", new
		 * SemanticAttribute("value", "Set", false, "value",
		 * InstAttribute.class.getCanonicalName(), new
		 * ArrayList<InstAttribute>(), 0, 1, "", "", -1, "", ""));
		 * semEnumeration.addPropEditableAttribute("01#" + "value");
		 * semEnumeration.addPropVisibleAttribute("01#" + "value");
		 * 
		 * InstConcept instSemEnumeration = new InstConcept("Enumeration", null,
		 * semEnumeration);
		 */
		// MetaEnumeration enumeration = new MetaEnumeration(
		// "TypeEnumeration",
		// true,
		// "TypeEnumeration",
		// "refasenumeration",
		// "Semantic Enumeration Type: Define an enumeration of types used in paiwise and overtwo relations",
		// 100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
		// true, Color.BLUE.toString(), 3, true);
		//
		// enumeration.addPanelVisibleAttribute("04#"
		// + MetaConcept.VAR_USERIDENTIFIER);
		// enumeration.addPanelSpacersAttribute("#"
		// + MetaConcept.VAR_USERIDENTIFIER + "#\n\n");
		//
		// enumeration.addModelingAttribute("value", "Set", false, "value",
		// InstAttribute.class.getCanonicalName(),
		// new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "", "");
		// enumeration.addPropEditableAttribute("01#" + "value");
		// enumeration.addPropVisibleAttribute("01#" + "value");

		SyntaxConcept infraSyntaxOpersM2InfraOTRel = new SyntaxConcept('T',
				"OMInfraOTRel", false, false, "OMInfraOTRel",
				"infrasyntaxm2biggrayconcept", "Over Two Relation", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2InfraOTRel.addModelingAttribute(
				"relTypesAttr",
				new ElemAttribute("relTypesAttr", "Set", AttributeType.SYNTAX,
						false, "relationTypes", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2InfraOTRel.addModelingAttribute(
				"opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "semanticExpressions", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2InfraOTRel.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2InfraOTRel.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n" + "<<non-editable>>"
				+ "\n\n");

		InstConcept instInfraSyntaxOpersM2InfraOTRel = new InstConcept(
				"OMInfraOTRel", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2InfraOTRel);
		variabilityInstVertex.put("OMInfraOTRel",
				instInfraSyntaxOpersM2InfraOTRel);

		SyntaxConcept infraSyntaxOpersM2OTRel = new SyntaxConcept('O',
				"OMOTRel", true, true, "OMOTRel",
				"infrasyntaxopersm2miniconcept", "Over Two Relation", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OTRel.addModelingAttribute(
				"relTypesAttr",
				new ElemAttribute("relTypesAttr", "Set", AttributeType.SYNTAX,
						false, "relationTypes", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2OTRel.addModelingAttribute(
				"opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "semanticExpressions", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2OTRel.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2OTRel.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2OTRel = new InstConcept("OMOTRel",
				basicOpersSyntaxM3Concept, infraSyntaxOpersM2OTRel);
		variabilityInstVertex.put("OMOTRel", instInfraSyntaxOpersM2OTRel);

		List<OpersRelType> basicOpersM2ExtRelList = new ArrayList<OpersRelType>();
		basicOpersM2ExtRelList.add(new OpersRelType("extends", "extends",
				"extends", false, true, true, 1, -1, 1, 1));

		List<OpersRelType> basicOpersM2AsoRellList = new ArrayList<OpersRelType>();
		basicOpersM2AsoRellList.add(new OpersRelType("association",
				"association", "association", false, true, true, 1, -1, 1, 1));

		OpersPairwiseRel basicOpersM2ExtRel = new OpersPairwiseRel("ExtRel",
				false, basicOpersM2ExtRelList);

		InstConcept instBasicOpersM2ExtRel = new InstConcept("ExtendsRelation",
				basicOpersSyntaxM3Concept, basicOpersM2ExtRel);

		OpersPairwiseRel basicOpersM2AsoRel = new OpersPairwiseRel("AsoRel",
				false, basicOpersM2AsoRellList);

		InstConcept instBasicOpersM2AsoRel = new InstConcept(
				"AssociationRelation", basicOpersSyntaxM3Concept,
				basicOpersM2AsoRel);

		// variabilityInstVertex.put("TypeEnumeration", new InstConcept(
		// "TypeEnumeration", metaBasicConcept, enumeration));

		SyntaxPairwiseRel infraSyntaxOpersM2AsoRel = new SyntaxPairwiseRel(
				"InfraSyntaxOpersM2AsoRel", false, true,
				"Association Relation", "defaultAsso",
				"Association Relation: ", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instBasicOpersM2AsoRel);

		infraSyntaxOpersM2AsoRel.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2AsoRel.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		constraintInstEdges.put("InfraSyntaxOpersM2AsoRel",
				new InstPairwiseRel(infraSyntaxOpersM2AsoRel));

		SyntaxPairwiseRel infraSyntaxOpersM2ExtRel = new SyntaxPairwiseRel(
				"ExtendsRelation",
				false,
				true,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instBasicOpersM2ExtRel);

		infraSyntaxOpersM2ExtRel.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2ExtRel.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstPairwiseRel rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsCCRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("ExtendsCCRel", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsCICRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2InfraConcept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("ExtendsCICRel", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsOCRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("ExtendsOCRel", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsOORel");
		rel.setTargetRelation(instInfraSyntaxOpersM2OTRel, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("ExtendsOORel", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsOIORel");
		rel.setTargetRelation(instInfraSyntaxOpersM2InfraOTRel, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("ExtendsOIORel", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsPIPRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2PWRel, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("ExtendsPIPRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoCPWRel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("AssoCPWRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoPWCRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoPWCRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoOCPWRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoOCPWRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoPWOCRel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("AssoPWOCRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoCOPWRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2OTRel, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoCOPWRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoPWCORel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("AssoPWCORel", rel);

		SyntaxConcept infraSyntaxOpersM2OperGroup = new SyntaxConcept('M',
				"OMOperGroup", true, true, "OMOperGroup",
				"infrasyntaxopersm2miniconcept", "Operation Group", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperGroup.addModelingAttribute("menuType",
				new ElemAttribute("menuType", "String",
						AttributeType.OPERATION, "Oper Group Type", "4", false,
						d, 0, 5, "", "", 5, "", ""));
		infraSyntaxOpersM2OperGroup
				.addPropEditableAttribute("05#" + "menuType");
		infraSyntaxOpersM2OperGroup.addPropVisibleAttribute("05#" + "menuType");

		infraSyntaxOpersM2OperGroup.addModelingAttribute("visible",
				new ElemAttribute("visible", "Boolean",
						AttributeType.OPERATION, false, "Visible", true, 0, 8,
						"", "", 8, "", ""));
		infraSyntaxOpersM2OperGroup.addPropEditableAttribute("08#" + "visible");
		infraSyntaxOpersM2OperGroup.addPropVisibleAttribute("08#" + "visible");

		infraSyntaxOpersM2OperGroup.addModelingAttribute("name",
				new ElemAttribute("name", "String", AttributeType.OPERATION,
						false, "Name", null, 0, 6, "", "", 6, "", ""));
		infraSyntaxOpersM2OperGroup.addPropEditableAttribute("06#" + "name");
		infraSyntaxOpersM2OperGroup.addPropVisibleAttribute("06#" + "name");
		infraSyntaxOpersM2OperGroup.addPanelVisibleAttribute("06#" + "name");
		infraSyntaxOpersM2OperGroup
				.addPanelSpacersAttribute("#" + "name" + "#");

		infraSyntaxOpersM2OperGroup.addModelingAttribute("shortcut",
				new ElemAttribute("shortcut", "String",
						AttributeType.OPERATION, false, "Shortcut", null, 0, 7,
						"", "", 7, "", ""));
		infraSyntaxOpersM2OperGroup
				.addPropEditableAttribute("07#" + "shortcut");
		infraSyntaxOpersM2OperGroup.addPropVisibleAttribute("07#" + "shortcut");

		infraSyntaxOpersM2OperGroup.addModelingAttribute("Index",
				new ElemAttribute("Index", "Integer", AttributeType.OPERATION,
						false, "Position", 1, 0, 9, "", "", 9, "", ""));
		infraSyntaxOpersM2OperGroup.addPropEditableAttribute("09#" + "Index");
		infraSyntaxOpersM2OperGroup.addPropVisibleAttribute("09#" + "Index");

		infraSyntaxOpersM2OperGroup.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2OperGroup.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2OperGroup = new InstConcept(
				"OMOperGroup", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperGroup);
		variabilityInstVertex.put("OMOperGroup",
				instInfraSyntaxOpersM2OperGroup);

		SyntaxConcept infraSyntaxOpersM2MetaModel = new SyntaxConcept('C',
				"OMModel", true, true, "OMModel",
				"infrasyntaxopersm2miniconcept", "Semantic Model", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2MetaModel.addModelingAttribute("name",
				new ElemAttribute("name", "String", AttributeType.OPERATION,
						false, "Name", null, 0, 6, "", "", 6, "", ""));
		infraSyntaxOpersM2MetaModel.addPropEditableAttribute("06#" + "name");
		infraSyntaxOpersM2MetaModel.addPropVisibleAttribute("06#" + "name");
		infraSyntaxOpersM2MetaModel.addPanelVisibleAttribute("06#" + "name");
		infraSyntaxOpersM2MetaModel
				.addPanelSpacersAttribute("#" + "name" + "#");

		infraSyntaxOpersM2MetaModel.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2MetaModel.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2MetaModel = new InstConcept(
				"OMModel", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2MetaModel);
		variabilityInstVertex.put("OMModel", instInfraSyntaxOpersM2MetaModel);

		SyntaxConcept infraSyntaxOpersM2OperAction = new SyntaxConcept('A',
				"OMOperation", true, true, "OMOperation",
				"infrasyntaxopersm2miniconcept", "Operation Action", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperAction.addModelingAttribute("name",
				new ElemAttribute("name", "String", AttributeType.OPERATION,
						false, "Name", null, 0, 6, "", "", 6, "", ""));
		infraSyntaxOpersM2OperAction.addModelingAttribute("shortcut",
				new ElemAttribute("shortcut", "String",
						AttributeType.OPERATION, false, "Shortcut", null, 0, 7,
						"", "", 7, "", ""));
		infraSyntaxOpersM2OperAction.addModelingAttribute("Index",
				new ElemAttribute("Index", "Integer", AttributeType.OPERATION,
						false, "Position", 1, 0, 8, "", "", 8, "", ""));
		infraSyntaxOpersM2OperAction.addModelingAttribute("iteration",
				new ElemAttribute("iteration", "Boolean",
						AttributeType.OPERATION, false, "Iterate Button",
						false, 0, 9, "", "", 9, "", ""));
		infraSyntaxOpersM2OperAction.addModelingAttribute("iterationName",
				new ElemAttribute("iterationName", "String",
						AttributeType.OPERATION, false, "Iterate Name", null,
						0, 6, "", "", 6, "", ""));
		infraSyntaxOpersM2OperAction.addModelingAttribute("prevSpacer",
				new ElemAttribute("prevSpacer", "Boolean",
						AttributeType.OPERATION, false, "Add Previous Spacer",
						false, 0, 9, "", "", 9, "", ""));

		infraSyntaxOpersM2OperAction.addPropEditableAttribute("06#" + "name");
		infraSyntaxOpersM2OperAction.addPropVisibleAttribute("06#" + "name");
		infraSyntaxOpersM2OperAction.addPanelVisibleAttribute("06#" + "name");
		infraSyntaxOpersM2OperAction.addPanelSpacersAttribute("#" + "name"
				+ "#");

		infraSyntaxOpersM2OperAction.addPropEditableAttribute("07#"
				+ "shortcut");
		infraSyntaxOpersM2OperAction
				.addPropVisibleAttribute("07#" + "shortcut");

		infraSyntaxOpersM2OperAction.addPropEditableAttribute("08#" + "Index");
		infraSyntaxOpersM2OperAction.addPropVisibleAttribute("08#" + "Index");

		infraSyntaxOpersM2OperAction.addPropEditableAttribute("09#"
				+ "iteration");
		infraSyntaxOpersM2OperAction.addPropVisibleAttribute("09#"
				+ "iteration");

		infraSyntaxOpersM2OperAction.addPropEditableAttribute("09#"
				+ "iterationName");
		infraSyntaxOpersM2OperAction.addPropVisibleAttribute("09#"
				+ "iterationName");

		infraSyntaxOpersM2OperAction.addPropEditableAttribute("10#"
				+ "prevSpacer");
		infraSyntaxOpersM2OperAction.addPropVisibleAttribute("10#"
				+ "prevSpacer");

		infraSyntaxOpersM2OperAction.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2OperAction.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2OperAction = new InstConcept(
				"OMOperation", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperAction);
		variabilityInstVertex.put("OMOperation",
				instInfraSyntaxOpersM2OperAction);

		SyntaxConcept infraSyntaxOpersM2OperSubAction = new SyntaxConcept('S',
				"OMSubOper", true, true, "OMSubOper",
				"infrasyntaxopersm2miniconcept", "Operation Action", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("name",
				new ElemAttribute("name", "String", AttributeType.OPERATION,
						false, "Name", null, 0, 6, "", "", 6, "", ""));
		infraSyntaxOpersM2OperSubAction
				.addPropEditableAttribute("06#" + "name");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("06#" + "name");
		infraSyntaxOpersM2OperSubAction
				.addPanelVisibleAttribute("06#" + "name");
		infraSyntaxOpersM2OperSubAction.addPanelSpacersAttribute("#" + "name"
				+ "#");

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("Index",
				new ElemAttribute("Index", "Integer", AttributeType.OPERATION,
						false, "Position", 1, 0, 6, "", "", 6, "", ""));
		infraSyntaxOpersM2OperSubAction.addPropEditableAttribute("08#"
				+ "Index");
		infraSyntaxOpersM2OperSubAction
				.addPropVisibleAttribute("08#" + "Index");

		infraSyntaxOpersM2OperSubAction
				.addModelingAttribute("iteration",
						new ElemAttribute("iteration", "Boolean",
								AttributeType.OPERATION, false,
								"Iterate Sub-Operation", false, 0, 6, "", "",
								6, "", ""));
		infraSyntaxOpersM2OperSubAction.addPropEditableAttribute("09#"
				+ "iteration");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("09#"
				+ "iteration");

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("type",
				new ElemAttribute("type", "Enumeration",
						AttributeType.OPERATION, false, "Type",
						OperationSubActionType.class.getCanonicalName(),
						"SINGLEUPDATE", "", 0, 6, "", "", 6, "", ""));
		infraSyntaxOpersM2OperSubAction
				.addPropEditableAttribute("10#" + "type");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("10#" + "type");

		infraSyntaxOpersM2OperSubAction.addModelingAttribute(
				"exptype",
				new ElemAttribute("exptype", "Set", AttributeType.SYNTAX,
						false, "exptype", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, 2, "", "", 2, "#\n",
						""));

		infraSyntaxOpersM2OperSubAction.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2OperSubAction.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2OperSubAction = new InstConcept(
				"OMSubOper", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperSubAction);
		variabilityInstVertex.put("OMSubOper",
				instInfraSyntaxOpersM2OperSubAction);

		SyntaxConcept infraSyntaxOpersM2OperLabeling = new SyntaxConcept('S',
				"OMLabeling", true, true, "OMLabeling",
				"infrasyntaxopersm2miniconcept", "Operation Labeling", 100,
				150, "/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperLabeling.addModelingAttribute("labelId",
				new ElemAttribute("labelId", "String", AttributeType.OPERATION,
						false, "Label ID", null, 0, 6, "", "", 6, "", ""));
		infraSyntaxOpersM2OperLabeling.addModelingAttribute("position",
				new ElemAttribute("position", "Integer",
						AttributeType.OPERATION, false, "Position", 1, 0, 6,
						"", "", 6, "", ""));
		infraSyntaxOpersM2OperLabeling.addModelingAttribute("once",
				new ElemAttribute("once", "Boolean", AttributeType.OPERATION,
						false, "Once", false, 0, 6, "", "", 6, "", ""));

		infraSyntaxOpersM2OperLabeling.addModelingAttribute(
				"sortorder",
				new ElemAttribute("sortorder", "Set", AttributeType.SYNTAX,
						false, "sortorder", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, 2, "", "", 2, "#\n",
						""));
		infraSyntaxOpersM2OperLabeling.addPropEditableAttribute("06#"
				+ "labelId");
		infraSyntaxOpersM2OperLabeling.addPropVisibleAttribute("06#"
				+ "labelId");
		infraSyntaxOpersM2OperLabeling.addPropEditableAttribute("07#"
				+ "position");
		infraSyntaxOpersM2OperLabeling.addPropVisibleAttribute("07#"
				+ "position");
		infraSyntaxOpersM2OperLabeling.addPropEditableAttribute("08#" + "once");
		infraSyntaxOpersM2OperLabeling.addPropVisibleAttribute("08#" + "once");

		infraSyntaxOpersM2OperLabeling.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2OperLabeling.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2OperLabeling = new InstConcept(
				"OMLabeling", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperLabeling);
		variabilityInstVertex.put("OMLabeling",
				instInfraSyntaxOpersM2OperLabeling);

		SyntaxConcept infraSyntaxOpersM2ExpType = new SyntaxConcept('S',
				"OMExpType", false, true, "OMExpType",
				"infrasyntaxopersm2miniconcept", "Operation Expression Type",
				100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2ExpType.addModelingAttribute("suboperexptype",
				new ElemAttribute("suboperexptype", "Enumeration",
						AttributeType.OPERATION, false, "Expression Type",
						OperationSubActionExecType.class.getCanonicalName(),
						"NORMAL", "", 0, 6, "", "", 6, "", ""));

		infraSyntaxOpersM2ExpType.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2ExpType.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2ExpType = new InstConcept(
				"OMLabeling", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2ExpType);
		variabilityInstVertex.put("OMExpType", instInfraSyntaxOpersM2ExpType);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoMenu-Act");
		rel.setTargetRelation(instInfraSyntaxOpersM2OperAction, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperGroup, true);
		constraintInstEdges.put("AssoMenu-Act", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoAct-SubAct");
		rel.setTargetRelation(instInfraSyntaxOpersM2OperSubAction, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperAction, true);
		constraintInstEdges.put("AssoAct-SubAct", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoSubAct-Lab");
		rel.setTargetRelation(instInfraSyntaxOpersM2OperLabeling, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperSubAction, true);
		constraintInstEdges.put("AssoSubAct-Lab", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoSubAct-ExpType");
		rel.setTargetRelation(instInfraSyntaxOpersM2ExpType, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperSubAction, true);
		constraintInstEdges.put("AssoSubAct-ExpType", rel);
	}
}
