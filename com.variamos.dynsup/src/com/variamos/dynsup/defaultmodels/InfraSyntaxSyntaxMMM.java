package com.variamos.dynsup.defaultmodels;

import java.awt.Color;
import java.util.Map;

import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.types.AttributeType;

public class InfraSyntaxSyntaxMMM {
	public static void createSyntaxSyntaxMetaMetaModel(
			ModelInstance modelInstance) {

		Map<String, InstElement> variabilityInstVertex = modelInstance
				.getVariabilityVertex();

		Map<String, InstPairwiseRel> constraintInstEdges = modelInstance
				.getConstraintInstEdges();

		InstElement infraBasicSyntaxOpersM3Concept = modelInstance
				.getSyntaxModel().getVertex("OMMConcept");

		SyntaxElement infraSyntaxM2Node = new SyntaxElement('C', "SyMNode",
				true, true, "SyMNode", "infrasyntaxm2miniconcept",
				"Syntax Meta Node", 170, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxM2Node.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 3, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		// infraSyntaxM2Concept.addPanelVisibleAttribute("03#"
		// + SyntaxElement.VAR_USERIDENTIFIER);
		// infraSyntaxM2Concept.addPanelSpacersAttribute("#"
		// + SyntaxElement.VAR_USERIDENTIFIER + "#\n\n");

		infraSyntaxM2Node.addModelingAttribute("description",
				new ElemAttribute("description", "String",
						AttributeType.SYNTAX, false, "Description", "", "", 0,
						5, "", "", -1, "", ""));

		// infraSyntaxM2Node.addModelingAttribute("MetaType", new ElemAttribute(
		// "MetaType", "Enumeration", AttributeType.SYNTAX, false,
		// "MetaConcept Type", "", ConceptType.class.getCanonicalName(),
		// "SyntaxConcept", "", 0, 0, "false", "", -1, "", ""));

		infraSyntaxM2Node.addModelingAttribute("Visible", new ElemAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				"", true, 0, 2, "", "", -1, "", ""));
		infraSyntaxM2Node.addModelingAttribute("Name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false,
				"Meta Concept Name", "", "", 0, 3, "", "", -1, "", ""));
		infraSyntaxM2Node.addModelingAttribute("Style", new ElemAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "", "refasclaim", 0, 4, "", "", -1, "", ""));
		infraSyntaxM2Node.addModelingAttribute("Width", new ElemAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", "", 100, 0, 6, "", "", -1, "", ""));
		infraSyntaxM2Node.addModelingAttribute("Height", new ElemAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", "", 40, 0, 7, "", "", -1, "", ""));
		infraSyntaxM2Node.addModelingAttribute("Image", new ElemAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"", "/com/variamos/gui/perspeditor/images/claim.png", 0, 8, "",
				"", -1, "", ""));
		// infraSyntaxM2Node.addModelingAttribute("TopConcept", new
		// ElemAttribute(
		// "TopConcept", "Boolean", AttributeType.SYNTAX, false,
		// "Is Top Concept", "", true, 0, 9, "", "", -1, "", ""));
		infraSyntaxM2Node.addModelingAttribute("BackgroundColor",
				new ElemAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color", "",
						"java.awt.Color[r=0,g=0,b=255]", 0, 10, "", "", -1, "",
						""));
		infraSyntaxM2Node.addModelingAttribute("BorderStroke",
				new ElemAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", "", 1, 0,
						11, "", "", -1, "", ""));
		infraSyntaxM2Node.addModelingAttribute("Resizable", new ElemAttribute(
				"Resizable", "Boolean", AttributeType.SYNTAX, false,
				"Is Resizable", "", true, 0, 12, "", "", -1, "", ""));

		infraSyntaxM2Node.addModelingAttribute("value", new ElemAttribute(
				"value", "Set", AttributeType.SYNTAX, false,
				"dynamic meta-attributes", "", "", 0, -1, "", "", -1, "", ""));

		infraSyntaxM2Node.addModelingAttribute("OperationsMMType",
				new ElemAttribute("OperationsMMType", "Class",
						AttributeType.OPERATION, false, "Operations MMType",
						"Type from the Operations Meta-Model",
						OpersConcept.class.getCanonicalName(), "C", null, "",
						0, 0, "", "", 1, "<<SyMConcept>>\n{OperType:\"#"
								+ "OperationsMMType" + "#all#\"}\n", ""));

		// infraSyntaxM2Concept.addPanelVisibleAttribute("00#"
		// + "OperationsMMType");
		// infraSyntaxM2Concept
		// .addPanelSpacersAttribute("<<MetaConcept>>\n{OperType:\"#"
		// + "OperationsMMType" + "#\"}\n");

		InstConcept instInfraSyntaxOpersM2Concept = new InstConcept("SyMNode",
				infraBasicSyntaxOpersM3Concept, infraSyntaxM2Node);
		variabilityInstVertex.put("SyMNode", instInfraSyntaxOpersM2Concept);

		// OpersConcept infraOpersM2OTRel = new
		// OpersConcept("SyMOverTwo");
		//
		// infraOpersM2OTRel.putSemanticAttribute("Name", new ElemAttribute(
		// "Name", "String", AttributeType.SYNTAX, false,
		// "Meta Concept Name", "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2OTRel.putSemanticAttribute("description",
		// new ElemAttribute("description", "String",
		// AttributeType.SYNTAX, false, "Description", "", 0, -1,
		// "", "", -1, "", ""));
		//
		// infraOpersM2OTRel.putSemanticAttribute("MetaType", new ElemAttribute(
		// "MetaType", "Enumeration", AttributeType.SYNTAX, false,
		// "MetaConcept Type", ConceptType.class.getCanonicalName(),
		// "SyntaxConcept", "", 0, -1, "", "", -1, "", ""));
		// // semVertex.putSemanticAttribute("Identifier", new ElemAttribute(
		// // "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// // "", "", -1, "", ""));
		// infraOpersM2OTRel.putSemanticAttribute("Visible", new ElemAttribute(
		// "Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
		// true, 0, -1, "", "", -1, "", ""));
		// infraOpersM2OTRel.putSemanticAttribute("Name", new ElemAttribute(
		// "Name", "String", AttributeType.SYNTAX, false, "MConcept Name",
		// "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2OTRel.putSemanticAttribute("Style", new ElemAttribute(
		// "Style", "String", AttributeType.SYNTAX, false,
		// "Drawing Style", "refasclaim", 0, -1, "", "", -1, "", ""));
		// infraOpersM2OTRel.putSemanticAttribute("Width", new ElemAttribute(
		// "Width", "Integer", AttributeType.SYNTAX, false,
		// "Initial Width", 100, 0, -1, "", "", -1, "", ""));
		// infraOpersM2OTRel.putSemanticAttribute("Height", new ElemAttribute(
		// "Height", "Integer", AttributeType.SYNTAX, false,
		// "Initial Height", 40, 0, -1, "", "", -1, "", ""));
		// infraOpersM2OTRel.putSemanticAttribute("Image", new ElemAttribute(
		// "Image", "String", AttributeType.SYNTAX, false, "Image File",
		// "/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
		// "", -1, "", ""));
		// infraOpersM2OTRel.putSemanticAttribute("TopConcept", new
		// ElemAttribute(
		// "TopConcept", "Boolean", AttributeType.SYNTAX, false,
		// "Is Top Concept", true, 0, -1, "", "", -1, "", ""));
		// infraOpersM2OTRel.putSemanticAttribute("BackgroundColor",
		// new ElemAttribute("BackgroundColor", "String",
		// AttributeType.SYNTAX, false, "Background Color",
		// "java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
		// ""));
		// infraOpersM2OTRel.putSemanticAttribute("BorderStroke",
		// new ElemAttribute("BorderStroke", "Integer",
		// AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
		// "", "", -1, "", ""));
		// infraOpersM2OTRel.putSemanticAttribute("Resizable", new
		// ElemAttribute(
		// "Resizable", "Boolean", AttributeType.SYNTAX, false,
		// "Is Resizable", true, 0, -1, "", "", -1, "", ""));
		// infraOpersM2OTRel.putSemanticAttribute("value", new ElemAttribute(
		// "value", "Set", AttributeType.SYNTAX, false, "values", "", 0,
		// -1, "", "", -1, "", ""));
		//
		// infraOpersM2OTRel.addPropVisibleAttribute("00#" + "MetaType");
		// // semVertex.addPropEditableAttribute("01#" + "Identifier");
		// // semVertex.addPropVisibleAttribute("01#" + "Identifier");
		// infraOpersM2OTRel.addPropEditableAttribute("02#" + "Visible");
		// infraOpersM2OTRel.addPropVisibleAttribute("02#" + "Visible");
		// infraOpersM2OTRel.addPropEditableAttribute("03#" + "Name");
		// infraOpersM2OTRel.addPropVisibleAttribute("03#" + "Name");
		// infraOpersM2OTRel.addPropEditableAttribute("04#" + "Style");
		// infraOpersM2OTRel.addPropVisibleAttribute("04#" + "Style");
		// infraOpersM2OTRel.addPropEditableAttribute("05#" + "description");
		// infraOpersM2OTRel.addPropVisibleAttribute("05#" + "description");
		// infraOpersM2OTRel.addPropEditableAttribute("06#" + "Width");
		// infraOpersM2OTRel.addPropVisibleAttribute("06#" + "Width");
		// infraOpersM2OTRel.addPropEditableAttribute("07#" + "Height");
		// infraOpersM2OTRel.addPropVisibleAttribute("07#" + "Height");
		// infraOpersM2OTRel.addPropEditableAttribute("08#" + "Image");
		// infraOpersM2OTRel.addPropVisibleAttribute("08#" + "Image");
		// infraOpersM2OTRel.addPropEditableAttribute("09#" + "TopConcept");
		// infraOpersM2OTRel.addPropVisibleAttribute("09#" + "TopConcept");
		// infraOpersM2OTRel.addPropEditableAttribute("10#" +
		// "BackgroundColor");
		// infraOpersM2OTRel.addPropVisibleAttribute("10#" + "BackgroundColor");
		// infraOpersM2OTRel.addPropEditableAttribute("11#" + "BorderStroke");
		// infraOpersM2OTRel.addPropVisibleAttribute("11#" + "BorderStroke");
		// infraOpersM2OTRel.addPropEditableAttribute("12#" + "Resizable");
		// infraOpersM2OTRel.addPropVisibleAttribute("12#" + "Resizable");
		//
		// infraOpersM2OTRel.putSemanticAttribute("MetaType", new ElemAttribute(
		// "MetaType", "Enumeration", AttributeType.SYNTAX, false,
		// "MetaConcept Type", ConceptType.class.getCanonicalName(),
		// "SyntaxOverTwoRel", "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2OTRel.putSemanticAttribute("OperationsMMType",
		// new ElemAttribute("OperationsMMType", "Class",
		// AttributeType.OPERATION, false, "Operations MMType",
		// OpersConcept.class.getCanonicalName(), "O", null, "",
		// 0, -1, "", "", -1, "", ""));
		//
		// infraOpersM2OTRel.addPropVisibleAttribute("00#" + "MetaType");
		// infraOpersM2OTRel.addPropVisibleAttribute("00#" +
		// "OperationsMMType");
		// infraOpersM2OTRel.addPropEditableAttribute("00#" +
		// "OperationsMMType");
		// // semOverTwoRelations.add(semanticAssetOperGroupRelation);
		//
		// infraOpersM2OTRel.addPanelVisibleAttribute("00#" +
		// "OperationsMMType");
		// infraOpersM2OTRel
		// .addPanelSpacersAttribute("<<MetaOverTwoAsso>>\n{OperType:\"#"
		// + "OperationsMMType" + "#\"}\n");
		// // semOverTwoRelation.addPanelVisibleAttribute("01#" + "Name");
		// // semOverTwoRelation.addPanelSpacersAttribute("#" + "Name" + "#");
		//
		// InstConcept instInfraOpersM2OTRel = new InstConcept(
		// "SMMOverTwoRelation", null, infraOpersM2OTRel);

		// InstConcept instSemEnum = new InstConcept("Enumeration", null,
		// semElementNoSyntax);

		// MetaConcept enumeration = new MetaConcept('E', "Enumeration", true,
		// "Enumeration", "refasenumeration", "MetaEnumeration", 100, 150,
		// "/com/variamos/gui/perspeditor/images/concept.png", true,
		// Color.BLUE.toString(), 3, instSemEnum, true);
		//
		// variabilityInstVertex.put("Enumeration", new
		// InstConcept("Enumeration",
		// metaBasicConcept, enumeration));

		// OpersConcept infraOpersM2PWRel = new
		// OpersConcept("SMMPairwiseRelation");
		//
		// infraOpersM2PWRel.putSemanticAttribute("Name", new ElemAttribute(
		// "Name", "String", AttributeType.SYNTAX, false,
		// "Meta Association Name", "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2PWRel.putSemanticAttribute("description",
		// new ElemAttribute("description", "String",
		// AttributeType.SYNTAX, false, "Description", "", 0, -1,
		// "", "", -1, "", ""));
		//
		// infraOpersM2PWRel.putSemanticAttribute("MetaType", new ElemAttribute(
		// "MetaType", "Enumeration", AttributeType.SYNTAX, false,
		// "MetaPWAsso Type", ConceptType.class.getCanonicalName(),
		// "SyntaxConcept", "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2PWRel.putSemanticAttribute("OperationsMMType",
		// new ElemAttribute("OperationsMMType", "Class",
		// AttributeType.SYNTAX, false, "Operations MMType",
		// OpersConcept.class.getCanonicalName(), "P", null, "",
		// 0, -1, "", "", -1, "", ""));
		// infraOpersM2PWRel.putSemanticAttribute("Identifier", new
		// ElemAttribute(
		// "Identifier", "String", AttributeType.SYNTAX, false,
		// "Association Identifier", "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2PWRel.putSemanticAttribute("Visible", new ElemAttribute(
		// "Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
		// true, 0, -1, "", "", -1, "", ""));
		// infraOpersM2PWRel.putSemanticAttribute("Name", new ElemAttribute(
		// "Name", "String", AttributeType.SYNTAX, false, "Concept Name",
		// "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2PWRel.putSemanticAttribute("Style", new ElemAttribute(
		// "Style", "String", AttributeType.SYNTAX, false,
		// "Drawing Style", "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2PWRel.putSemanticAttribute("Width", new ElemAttribute(
		// "Width", "Integer", AttributeType.SYNTAX, false,
		// "Initial Width", 50, 0, -1, "", "", -1, "", ""));
		// infraOpersM2PWRel.putSemanticAttribute("Height", new ElemAttribute(
		// "Height", "Integer", AttributeType.SYNTAX, false,
		// "Initial Height", 50, 0, -1, "", "", -1, "", ""));
		// infraOpersM2PWRel.putSemanticAttribute("Image", new ElemAttribute(
		// "Image", "String", AttributeType.SYNTAX, false, "Image File",
		// "/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
		// "", -1, "", ""));
		// infraOpersM2PWRel.putSemanticAttribute("BackgroundColor",
		// new ElemAttribute("BackgroundColor", "String",
		// AttributeType.SYNTAX, false, "Background Color",
		// "java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
		// ""));
		// infraOpersM2PWRel.putSemanticAttribute("BorderStroke",
		// new ElemAttribute("BorderStroke", "Integer",
		// AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
		// "", "", -1, "", ""));
		// infraOpersM2PWRel.putSemanticAttribute("value", new ElemAttribute(
		// "value", "Set", AttributeType.SYNTAX, false, "values", "", 0,
		// -1, "", "", -1, "", ""));
		//
		// infraOpersM2PWRel.addPropVisibleAttribute("00#" + "MetaType");
		// // semPWAsso.addPropEditableAttribute("01#" + "Identifier");
		// // semPWAsso.addPropVisibleAttribute("01#" + "Identifier");
		// infraOpersM2PWRel.addPropEditableAttribute("02#" + "Visible");
		// infraOpersM2PWRel.addPropVisibleAttribute("02#" + "Visible");
		// infraOpersM2PWRel.addPropEditableAttribute("03#" + "Name");
		// infraOpersM2PWRel.addPropVisibleAttribute("03#" + "Name");
		// infraOpersM2PWRel.addPropEditableAttribute("04#" + "Style");
		// infraOpersM2PWRel.addPropVisibleAttribute("04#" + "Style");
		// infraOpersM2PWRel.addPropEditableAttribute("05#" + "description");
		// infraOpersM2PWRel.addPropVisibleAttribute("05#" + "description");
		// infraOpersM2PWRel.addPropEditableAttribute("06#" + "Width");
		// infraOpersM2PWRel.addPropVisibleAttribute("06#" + "Width");
		// infraOpersM2PWRel.addPropEditableAttribute("07#" + "Height");
		// infraOpersM2PWRel.addPropVisibleAttribute("07#" + "Height");
		// infraOpersM2PWRel.addPropEditableAttribute("08#" + "Image");
		// infraOpersM2PWRel.addPropVisibleAttribute("08#" + "Image");
		// infraOpersM2PWRel.addPropEditableAttribute("10#" +
		// "BackgroundColor");
		// infraOpersM2PWRel.addPropVisibleAttribute("10#" + "BackgroundColor");
		// infraOpersM2PWRel.addPropEditableAttribute("11#" + "BorderStroke");
		// infraOpersM2PWRel.addPropVisibleAttribute("11#" + "BorderStroke");
		// infraOpersM2PWRel.addPropEditableAttribute("14#" + "value");
		// infraOpersM2PWRel.addPropVisibleAttribute("14#" + "value");
		//
		// infraOpersM2PWRel.putSemanticAttribute("OperationsMMType",
		// new ElemAttribute("OperationsMMType", "Class",
		// AttributeType.OPERATION, false, "Operations MMType",
		// OpersConcept.class.getCanonicalName(), "P", null, "",
		// 0, -1, "", "", -1, "", ""));
		//
		// infraOpersM2PWRel.addPropVisibleAttribute("00#" +
		// "OperationsMMType");
		// infraOpersM2PWRel.addPropEditableAttribute("00#" +
		// "OperationsMMType");
		// infraOpersM2PWRel.addPanelVisibleAttribute("00#" +
		// "OperationsMMType");
		// infraOpersM2PWRel
		// .addPanelSpacersAttribute("<<MetaPairwiseAsso>>\n{OperType:\"#"
		// + "OperationsMMType" + "#\",\n");
		// // semPairwiseRelation.addPanelVisibleAttribute("10#" + "Name");
		// // semPairwiseRelation.addPanelSpacersAttribute("#" + "Name" +
		// "#\n\n");
		//
		// InstConcept instInfraOpersM2PWRel = new InstConcept(
		// "SMMPairwiseRelation", null, infraOpersM2PWRel);

		// semElement.addPanelVisibleAttribute("01#" + "Name");
		// semElement.addPanelSpacersAttribute("#" + "Name" + "#");

		// OpersConcept infraOpersM2ExtendsRelation = new OpersConcept(
		// "SMMExtendRelation");
		//
		// infraOpersM2ExtendsRelation.putSemanticAttribute("MetaType",
		// new ElemAttribute("MetaType", "Enumeration",
		// AttributeType.SYNTAX, false, "MetaConcept Type",
		// ConceptType.class.getCanonicalName(), "SyntaxEnum", "",
		// 0, -1, "", "", -1, "", ""));
		// // semElementNoSyntax.putSemanticAttribute("Identifier",
		// // new ElemAttribute("Identifier", "String", false,
		// // "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2ExtendsRelation.putSemanticAttribute("Visible",
		// new ElemAttribute("Visible", "Boolean", AttributeType.SYNTAX,
		// false, "Visible", true, 0, -1, "", "", -1, "", ""));
		// infraOpersM2ExtendsRelation.putSemanticAttribute("Name",
		// new ElemAttribute("Name", "String", AttributeType.SYNTAX,
		// false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2ExtendsRelation.putSemanticAttribute("value",
		// new ElemAttribute("value", "Set", AttributeType.SYNTAX, false,
		// "values", "", 0, -1, "", "", -1, "", ""));
		// // semElementNoSyntax.putSemanticAttribute("dummy", new
		// ElemAttribute(
		// // "dummy", "String", AttributeType.SYNTAX, false, "dummy", "", 0,
		// // -1, "", "", -1, "", ""));
		//
		// infraOpersM2ExtendsRelation.addPropVisibleAttribute("00#" +
		// "MetaType");
		// // semElementNoSyntax.addPropEditableAttribute("01#" + "Identifier");
		// // semElementNoSyntax.addPropVisibleAttribute("01#" + "Identifier");
		// infraOpersM2ExtendsRelation.addPropEditableAttribute("02#" +
		// "Visible");
		// infraOpersM2ExtendsRelation.addPropVisibleAttribute("02#" +
		// "Visible");
		// infraOpersM2ExtendsRelation.addPropEditableAttribute("03#" + "Name");
		// infraOpersM2ExtendsRelation.addPropVisibleAttribute("03#" + "Name");
		// infraOpersM2ExtendsRelation.addPropEditableAttribute("06#" +
		// "value");
		// infraOpersM2ExtendsRelation.addPropVisibleAttribute("06#" + "value");
		// infraOpersM2ExtendsRelation.addPanelSpacersAttribute("#" + "value"
		// + "#\n\n");
		//
		// InstConcept instInfraOpersM2ExtendsRelation = new InstConcept(
		// "SMMExtendRelation", null, infraOpersM2ExtendsRelation);
		//
		// OpersConcept infraOpersM2ViewConceptAsso = new OpersConcept(
		// "SMMViewConceptAsso");

		// infraOpersM2ViewConceptAsso.putSemanticAttribute("MetaType",
		// new ElemAttribute("MetaType", "Enumeration",
		// AttributeType.SYNTAX, false, "MetaConcept Type",
		// ConceptType.class.getCanonicalName(), "SyntaxEnum", "",
		// 0, -1, "", "", -1, "", ""));
		// // semElementNoSyntax.putSemanticAttribute("Identifier",
		// // new ElemAttribute("Identifier", "String", false,
		// // "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2ViewConceptAsso.putSemanticAttribute("Visible",
		// new ElemAttribute("Visible", "Boolean", AttributeType.SYNTAX,
		// false, "Visible", true, 0, -1, "", "", -1, "", ""));
		// infraOpersM2ViewConceptAsso.putSemanticAttribute("Name",
		// new ElemAttribute("Name", "String", AttributeType.SYNTAX,
		// false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2ViewConceptAsso.putSemanticAttribute("value",
		// new ElemAttribute("value", "Set", AttributeType.SYNTAX, false,
		// "values", "", 0, -1, "", "", -1, "", ""));
		// // semElementNoSyntax.putSemanticAttribute("dummy", new
		// ElemAttribute(
		// // "dummy", "String", AttributeType.SYNTAX, false, "dummy", "", 0,
		// // -1, "", "", -1, "", ""));
		//
		// infraOpersM2ViewConceptAsso.addPropVisibleAttribute("00#" +
		// "MetaType");
		// // semElementNoSyntax.addPropEditableAttribute("01#" + "Identifier");
		// // semElementNoSyntax.addPropVisibleAttribute("01#" + "Identifier");
		// infraOpersM2ViewConceptAsso.addPropEditableAttribute("02#" +
		// "Visible");
		// infraOpersM2ViewConceptAsso.addPropVisibleAttribute("02#" +
		// "Visible");
		// infraOpersM2ViewConceptAsso.addPropEditableAttribute("03#" + "Name");
		// infraOpersM2ViewConceptAsso.addPropVisibleAttribute("03#" + "Name");
		// infraOpersM2ViewConceptAsso.addPropEditableAttribute("06#" +
		// "value");
		// infraOpersM2ViewConceptAsso.addPropVisibleAttribute("06#" + "value");
		// infraOpersM2ViewConceptAsso.addPanelSpacersAttribute("#" + "value"
		// + "#\n\n");
		//
		// InstConcept instInfraOpersM2ViewConceptAsso = new InstConcept(
		// "ExtendRelation", null, infraOpersM2ViewConceptAsso);

		// End Opers M2 Model

		// Begin Syntax M2 Model

		SyntaxElement infraSyntaxM2View = new SyntaxElement('V', "SyMView",
				true, true, "SyMView", "infrasyntaxm2view",
				"MM View/MM SubView Concept", 100, 30,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.WHITE.toString(), 3, null, true);

		// infraSyntaxM2View.addModelingAttribute("MetaType", new ElemAttribute(
		// "MetaType", "Enumeration", AttributeType.SYNTAX, false,
		// "MetaConcept Type", "", ConceptType.class.getCanonicalName(),
		// "SyntaxView", "", 0, 0, "false", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("index", new ElemAttribute(
				"index", "Integer", AttributeType.SYNTAX, false, "View index",
				"", 3, 0, 2, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Identifier", new ElemAttribute(
				"Identifier", "String", AttributeType.SYNTAX, false,
				"View Identifier", "", "", 0, -1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Visible", new ElemAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				"", true, 0, 3, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "View name", "",
				"", 0, 5, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Style", new ElemAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "", "refasclaim", 0, -1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("description",
				new ElemAttribute("description", "String",
						AttributeType.SYNTAX, false, "Description", "", "", 0,
						7, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Width", new ElemAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", "", 100, 0, -1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Height", new ElemAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", "", 40, 0, -1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Image", new ElemAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image file",
				"", "/com/variamos/gui/perspeditor/images/claim.png", 0, -1,
				"", "", 10, "", ""));
		infraSyntaxM2View.addModelingAttribute("BorderStroke",
				new ElemAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", "", 1, 0,
						-1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("PaletteNames",
				new ElemAttribute("PaletteNames", "String",
						AttributeType.SYNTAX, false, "Palette Name", "", "", 0,
						3, "", "", 5, "{Palettes:#" + "PaletteNames"
								+ "#all#\n\n", "PaletteNames" + "#!=#" + ""));

		infraSyntaxM2View.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SyMView>>\n#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		// infraSyntaxM2View.addPanelVisibleAttribute("04#"
		// + SyntaxElement.VAR_USERIDENTIFIER);
		// infraSyntaxM2View.addPanelSpacersAttribute("#"
		// + SyntaxElement.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxM2View = new InstConcept("SyMView",
				infraBasicSyntaxOpersM3Concept, infraSyntaxM2View);
		variabilityInstVertex.put("SyMView", instInfraSyntaxM2View);

		SyntaxElement infraSyntaxM2OTRel = new SyntaxElement('O', "SyMOverTwo",
				true, true, "SyMOverTwo", "infrasyntaxm2microconcept",
				"SyntaxOverTwoRel", 160, 60,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxM2OTRel.addModelingAttribute("description",
				new ElemAttribute("description", "String",
						AttributeType.SYNTAX, false, "Description", "", "", 0,
						5, "", "", -1, "", ""));

		// infraSyntaxM2OTRel.addModelingAttribute("MetaType", new
		// ElemAttribute(
		// "MetaType", "Enumeration", AttributeType.SYNTAX, false,
		// "MetaConcept Type", "", ConceptType.class.getCanonicalName(),
		// "SyntaxConcept", "", 0, -1, "", "", -1, "", ""));

		// semVertex.putSemanticAttribute("Identifier", new ElemAttribute(
		// "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// "", "", -1, "", ""));
		infraSyntaxM2OTRel.addModelingAttribute("Visible", new ElemAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				"", true, 0, 2, "", "", -1, "", ""));
		infraSyntaxM2OTRel.addModelingAttribute("Name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "MConcept Name",
				"", "", 0, 3, "", "", -1, "", ""));
		infraSyntaxM2OTRel.addModelingAttribute("Style", new ElemAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "", "refasclaim", 0, 4, "", "", -1, "", ""));
		infraSyntaxM2OTRel.addModelingAttribute("Width", new ElemAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", "", 130, 0, 6, "", "", -1, "", ""));
		infraSyntaxM2OTRel.addModelingAttribute("Height", new ElemAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", "", 90, 0, 7, "", "", -1, "", ""));
		infraSyntaxM2OTRel.addModelingAttribute("Image", new ElemAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"", "/com/variamos/gui/perspeditor/images/claim.png", 0, 8, "",
				"", -1, "", ""));
		// infraSyntaxM2OTRel.addModelingAttribute("TopConcept",
		// new ElemAttribute("TopConcept", "Boolean",
		// AttributeType.SYNTAX, false, "Is Top Concept", "",
		// true, 0, -1, "", "", -1, "", ""));
		infraSyntaxM2OTRel.addModelingAttribute("BackgroundColor",
				new ElemAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color", "",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		infraSyntaxM2OTRel.addModelingAttribute("BorderStroke",
				new ElemAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", "", 1, 0,
						-1, "", "", -1, "", ""));
		infraSyntaxM2OTRel.addModelingAttribute("Resizable", new ElemAttribute(
				"Resizable", "Boolean", AttributeType.SYNTAX, false,
				"Is Resizable", "", true, 0, -1, "", "", -1, "", ""));
		infraSyntaxM2OTRel.addModelingAttribute("value", new ElemAttribute(
				"value", "Set", AttributeType.SYNTAX, false, "values", "", "",
				0, -1, "", "", -1, "", ""));

		infraSyntaxM2OTRel.addModelingAttribute("OperationsMMType",
				new ElemAttribute("OperationsMMType", "Class",
						AttributeType.OPERATION, false, "Operations MMType",
						"Type from the Operations Meta-Model",
						OpersConcept.class.getCanonicalName(), "O", null, "",
						0, 0, "", "", 1, "<<SyMOverTwoAsso>>\n{OperType:\"#"
								+ "OperationsMMType" + "#all#\"}\n", ""));
		infraSyntaxM2OTRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 3, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n", "");
		//
		// infraSyntaxM2OTRel.addPanelVisibleAttribute("00#" +
		// "OperationsMMType");
		// infraSyntaxM2OTRel
		// .addPanelSpacersAttribute("<<MetaOverTwoAsso>>\n{OperType:\"#"
		// + "OperationsMMType" + "#\"}\n");
		// semOverTwoRelation.addPanelVisibleAttribute("01#" + "Name");
		// semOverTwoRelation.addPanelSpacersAttribute("#" + "Name" + "#");

		infraSyntaxM2OTRel.addModelingAttribute("Type", new ElemAttribute(
				"Type", "String", AttributeType.SYNTAX, false, "Relation Type",
				"", "", 0, -1, "", "", -1, "", ""));

		// overTwoRelation.addPropVisibleAttribute("03#" + "Type");
		// overTwoRelation.addPropEditableAttribute("03#" + "Type");

		InstConcept instInfraSyntaxM2OTRel = new InstConcept("SyMOverTwo",
				infraBasicSyntaxOpersM3Concept, infraSyntaxM2OTRel);
		variabilityInstVertex.put("SyMOverTwo", instInfraSyntaxM2OTRel);

		SyntaxElement infraSyntaxM2NormalRelation = new SyntaxElement('P',
				"SyMAso", false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		constraintInstEdges.put("SyMAso", new InstPairwiseRel("SyMAso",
				infraSyntaxM2NormalRelation));

		SyntaxElement infraSyntaxM2ExtendsRelation = new SyntaxElement('X',
				"SyMExtend", true, true, "SyMExtend",
				"infrasyntaxm2microconcept", "Extend relation", 80, 50,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);
		// infraSyntaxM2ExtendsRelation.addModelingAttribute("MetaType",
		// new ElemAttribute("MetaType", "Enumeration",
		// AttributeType.SYNTAX, false, "MetaConcept Type", "",
		// ConceptType.class.getCanonicalName(), "SyntaxEnum", "",
		// 0, 0, "false", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("Identifier",
		// new ElemAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		infraSyntaxM2ExtendsRelation.addModelingAttribute("Visible",
				new ElemAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", "", true, 0, 2, "", "", -1, "", ""));
		infraSyntaxM2ExtendsRelation.addModelingAttribute("Name",
				new ElemAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", "", 0, 3, "", "", 1,
						"<<SyMExtends>>\n#Name#all#\n\n", ""));
		infraSyntaxM2ExtendsRelation.addModelingAttribute("value",
				new ElemAttribute("value", "Set", AttributeType.SYNTAX, false,
						"values", "", "", 0, 6, "", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("dummy", new ElemAttribute(
		// "dummy", "String", AttributeType.SYNTAX, false, "dummy", "", 0,
		// -1, "", "", -1, "", ""));

		InstConcept instInfraSyntaxM2ExtendsRelation = new InstConcept(
				"SyMExtend", infraBasicSyntaxOpersM3Concept,
				infraSyntaxM2ExtendsRelation);

		variabilityInstVertex
				.put("SyMExtend", instInfraSyntaxM2ExtendsRelation);

		InstPairwiseRel instEdge = new InstPairwiseRel();
		constraintInstEdges.put("ce-e-c", instEdge);
		instEdge.setIdentifier("ce-e-c");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ExtendsRelation, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("ce-c-e", instEdge);
		instEdge.setIdentifier("ce-c-e");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2ExtendsRelation, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Concept, true);

		SyntaxElement metaPairwiseRelExtends = new SyntaxElement('P',
				"ExtendsRelation", false, true, "Extends Relation",
				"refasextends", "Extends relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		constraintInstEdges.put("ExtendsRelation", new InstPairwiseRel(
				"ExtendsRelation", metaPairwiseRelExtends));

		SyntaxElement infraSyntaxM2ViewConceptAsso = new SyntaxElement('I',
				"SyMViewNode", true, true, "SyMViewNode",
				"infrasyntaxm2microconcept", "View-Concept Association", 150,
				40, "/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		// infraSyntaxM2ViewConceptAsso.addModelingAttribute("MetaType",
		// new ElemAttribute("MetaType", "Enumeration",
		// AttributeType.SYNTAX, false, "MetaConcept Type", "",
		// ConceptType.class.getCanonicalName(), "SyntaxEnum", "",
		// "", 0, -1, "", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("Identifier",
		// new ElemAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		infraSyntaxM2ViewConceptAsso.addModelingAttribute("Visible",
				new ElemAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", "", true, 0, 2, "", "", -1, "", ""));
		infraSyntaxM2ViewConceptAsso
				.addModelingAttribute("Name", new ElemAttribute("Name",
						"String", AttributeType.SYNTAX, false, "Concept Name",
						"", "", 0, 3, "", "", -1, "", ""));
		infraSyntaxM2ViewConceptAsso.addModelingAttribute("value",
				new ElemAttribute("value", "Set", AttributeType.SYNTAX, false,
						"values", "", "", 0, -1, "", "", -1, "", ""));
		infraSyntaxM2ViewConceptAsso.addModelingAttribute("dummy",
				new ElemAttribute("dummy", "String", AttributeType.SYNTAX,
						false, "dummy", "", "", 0, -1, "", "", 1,
						"<<SyMViewConceptAsso>>#dummy#all#\n", ""));

		// infraSyntaxM2ViewConceptAsso.addPanelVisibleAttribute("01#dummy");
		// infraSyntaxM2ViewConceptAsso
		// .addPanelSpacersAttribute("<<MetaViewConceptAsso>>#dummy#\n");
		infraSyntaxM2ViewConceptAsso.addModelingAttribute("Palette",
				new ElemAttribute("Palette", "String", AttributeType.SYNTAX,
						false, "Palette Name", "", "", 0, 2, "", "", 2,
						"{Palette:#" + "Palette" + "#all#}\n", "Palette"
								+ "#!=#" + ""));

		InstConcept instInfraSyntaxM2ViewConceptAsso = new InstConcept(
				"SyMViewNode", infraBasicSyntaxOpersM3Concept,
				infraSyntaxM2ViewConceptAsso);
		variabilityInstVertex.put("SyMViewNode",
				instInfraSyntaxM2ViewConceptAsso);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-v-vc", instEdge);
		instEdge.setIdentifier("vc-v-vc");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2ViewConceptAsso, true);
		instEdge.setSourceRelation(instInfraSyntaxM2View, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-vc-c", instEdge);
		instEdge.setIdentifier("vc-vc-c");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ViewConceptAsso, true);

		// TODO remove if Claims and SDs are Concepts
		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-vc-otr", instEdge);
		instEdge.setIdentifier("vc-vc-otr");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2OTRel, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ViewConceptAsso, true);

		SyntaxElement metaPairwiseRelFromView = new SyntaxElement('P',
				"ViewRelation", false, true, "View Relation",
				"infrasyntaxm2viewrel", "View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		InstPairwiseRel instViewRelation = new InstPairwiseRel("ViewRelation",
				metaPairwiseRelFromView);

		constraintInstEdges.put("ViewRelation", instViewRelation);

		SyntaxElement infraSyntaxM2PWRel = new SyntaxElement('P',
				"SyMPairwise", true, true, "SyMPairwise",
				"infrasyntaxm2microconcept", "SyntaxPairwiseRel", 200, 90,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxM2PWRel.addModelingAttribute("description",
				new ElemAttribute("description", "String",
						AttributeType.SYNTAX, false, "Description", "", "", 0,
						5, "", "", -1, "", ""));

		// infraSyntaxM2PWRel.addModelingAttribute("MetaType", new
		// ElemAttribute(
		// "MetaType", "Enumeration", AttributeType.SYNTAX, false,
		// "MetaPWAsso Type", "", ConceptType.class.getCanonicalName(),
		// "SyntaxConcept", "", 0, 0, "false", "", -1, "", ""));

		infraSyntaxM2PWRel.addModelingAttribute("OperationsMMType",
				new ElemAttribute("OperationsMMType", "Class",
						AttributeType.SYNTAX, false, "Operations MMType",
						"Type from the Operations Meta-Model",
						OpersConcept.class.getCanonicalName(), "P", null, "",
						0, 0, "", "", 1, "<<SyMPairwiseAsso>>\n{OperType:\"#"
								+ "OperationsMMType" + "#all#\",\n", ""));
		infraSyntaxM2PWRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 3, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n", "");
		infraSyntaxM2PWRel.addModelingAttribute("Identifier",
				new ElemAttribute("Identifier", "String", AttributeType.SYNTAX,
						false, "Association Identifier", "", "", 0, -1, "", "",
						-1, "", ""));
		infraSyntaxM2PWRel.addModelingAttribute("Visible", new ElemAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				"", true, 0, 2, "", "", -1, "", ""));
		infraSyntaxM2PWRel.addModelingAttribute("Name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false,
				"Meta Association Name", "", "", 0, 3, "", "", -1, "", ""));
		infraSyntaxM2PWRel.addModelingAttribute("Style", new ElemAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "", "", 0, 4, "", "", -1, "", ""));
		infraSyntaxM2PWRel.addModelingAttribute("Width", new ElemAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", "", 130, 0, 6, "", "", -1, "", ""));
		infraSyntaxM2PWRel.addModelingAttribute("Height", new ElemAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", "", 90, 0, 7, "", "", -1, "", ""));
		infraSyntaxM2PWRel.addModelingAttribute("Image", new ElemAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"", "/com/variamos/gui/perspeditor/images/claim.png", 0, 8, "",
				"", -1, "", ""));
		infraSyntaxM2PWRel.addModelingAttribute("BackgroundColor",
				new ElemAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color", "",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", 10, "",
						""));
		infraSyntaxM2PWRel.addModelingAttribute("BorderStroke",
				new ElemAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", "", 1, 0,
						11, "", "", -1, "", ""));
		infraSyntaxM2PWRel.addModelingAttribute("value", new ElemAttribute(
				"value", "Set", AttributeType.SYNTAX, false, "values", "", "",
				0, 14, "", "", -1, "", ""));

		infraSyntaxM2PWRel.addModelingAttribute("Type", new ElemAttribute(
				"Type", "String", AttributeType.SYNTAX, false, "Relation Type",
				"", "", 0, -1, "", "", -1, "", ""));

		// pairwiseRelation.addPropEditableAttribute("03#" + "Type");
		// pairwiseRelation.addPropVisibleAttribute("03#" + "Type");

		infraSyntaxM2PWRel.addModelingAttribute("SourceCardinality",
				new ElemAttribute("SourceCardinality", "String",
						AttributeType.SYNTAX, false, "Source Cardinality", "",
						"String", "[]", "", 0, 4, "", "", 4, "{SourCard:#"
								+ "SourceCardinality" + "#all#,", ""));

		infraSyntaxM2PWRel.addModelingAttribute("TargetCardinality",
				new ElemAttribute("TargetCardinality", "String",
						AttributeType.SYNTAX, false, "Target Cardinality", "",
						"String", "[]", "", 0, 5, "", "", 5,
						"TargCard:#TargetCardinality#all#}\n", ""));

		InstConcept instInfraSyntaxM2PWRel = new InstConcept("SyMPairwise",
				infraBasicSyntaxOpersM3Concept, infraSyntaxM2PWRel);
		variabilityInstVertex.put("SyMPairwise", instInfraSyntaxM2PWRel);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("cpw-pw-c", instEdge);
		instEdge.setIdentifier("cpw-pw-c");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		instEdge.setSourceRelation(instInfraSyntaxM2PWRel, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("cpw-c-pw", instEdge);
		instEdge.setIdentifier("cpw-c-pw");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2PWRel, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Concept, true);

		// TODO remove if Claims and SDs are Concepts
		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("cpw-pw-otr", instEdge);
		instEdge.setIdentifier("cpw-pw-otr");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2OTRel, true);
		instEdge.setSourceRelation(instInfraSyntaxM2PWRel, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("cpw-otr-pw", instEdge);
		instEdge.setIdentifier("cpw-otr-pw");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2PWRel, true);
		instEdge.setSourceRelation(instInfraSyntaxM2OTRel, true);
	}
}
