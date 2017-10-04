package com.variamos.dynsup.defaultmodels;

import java.awt.Color;
import java.util.Map;

import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.types.AttributeType;

public class InfraSyntaxSyntaxMMM {
	public static void createSyntaxSyntaxMetaMetaModel(
			InstanceModel modelInstance) {

		Map<String, InstElement> variabilityInstVertex = modelInstance
				.getVariabilityVertex();

		Map<String, InstPairwiseRel> constraintInstEdges = modelInstance
				.getConstraintInstEdges();

		InstElement infraBasicSyntaxOpersM3Node = modelInstance
				.getSyntaxModel().getVertex("BsNode");

		InstElement infraBasicSyntaxOpersM3Set = modelInstance.getSyntaxModel()
				.getVertex("BsSet");

		InstElement infraBasicSyntaxOpersM3Gen = modelInstance.getSyntaxModel()
				.getVertex("BsGeneralization");

		InstElement infraBasicSyntaxOpersM3Arrow = modelInstance
				.getSyntaxModel().getVertex("BsArrow");

		SyntaxElement infraSyntaxM2Language = new SyntaxElement('C',
				"SyMParadigm", true, true, "SyMParadigm",
				"infrasyntaxm2miniconcept", "Syntax Meta Node", 170, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxM2Language.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 3, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		infraSyntaxM2Language.addModelingAttribute("name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false,
				"Meta Concept Name", "", "", 0, 3, "", "", -1, "", ""));

		infraSyntaxM2Language.addModelingAttribute("semType",
				new ElemAttribute("semType", "Class", AttributeType.OPERATION,
						false, "Semantic Type",
						"Type from the Operations Meta-Model",
						OpersConcept.class.getCanonicalName(), "C", null, "",
						0, 0, "", "", 1, "<<SyMParadigm>>\n" + "{SeType:\"#"
								+ "OperationsMMType" + "#all#\"}\n", ""));

		InstConcept instInfraSyntaxOpersM2Lang = new InstConcept("SyMParadigm",
				infraBasicSyntaxOpersM3Node, infraSyntaxM2Language);
		variabilityInstVertex.put("SyMParadigm", instInfraSyntaxOpersM2Lang);

		SyntaxElement infraSyntaxM2Element = new SyntaxElement('C',
				"SyMElement", false, true, "SyMElement",
				"infrasyntaxm2miniconcept", "Syntax Meta Element", 170, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxM2Element.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 3, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		infraSyntaxM2Element.addModelingAttribute("description",
				new ElemAttribute("description", "String",
						AttributeType.SYNTAX, false, "Description", "", "", 0,
						5, "", "", -1, "", ""));

		infraSyntaxM2Element.addModelingAttribute("Visible", new ElemAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				"Shows/Hides the view in the modeling perspective", true, 0, 2,
				"", "", -1, "", ""));
		infraSyntaxM2Element.addModelingAttribute("Name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false,
				"Meta Concept Name", "", "", 0, 3, "", "", -1, "", ""));
		infraSyntaxM2Element.addModelingAttribute("Style", new ElemAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "", "refasclaim", 0, 4, "", "", -1, "", ""));
		infraSyntaxM2Element.addModelingAttribute("Width", new ElemAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", "", 100, 0, 6, "", "", -1, "", ""));
		infraSyntaxM2Element.addModelingAttribute("Height", new ElemAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", "", 40, 0, 7, "", "", -1, "", ""));
		infraSyntaxM2Element.addModelingAttribute("Image", new ElemAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"", "/com/variamos/gui/perspeditor/images/claim.png", 0, 8, "",
				"", -1, "", ""));
		infraSyntaxM2Element.addModelingAttribute("BackgroundColor",
				new ElemAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color", "",
						"java.awt.Color[r=0,g=0,b=255]", 0, 10, "", "", -1, "",
						""));
		infraSyntaxM2Element.addModelingAttribute("BorderStroke",
				new ElemAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", "", 1, 0,
						11, "", "", -1, "", ""));

		InstConcept instInfraSyntaxOpersM2Element = new InstConcept(
				"SyMElement", infraBasicSyntaxOpersM3Node, infraSyntaxM2Element);
		variabilityInstVertex.put("SyMElement", instInfraSyntaxOpersM2Element);

		SyntaxElement infraSyntaxM2Node = new SyntaxElement('C', "SyMNode",
				true, true, "SyMNode", "infrasyntaxm2miniconcept",
				"Syntax Meta Node", 170, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxM2Node.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 3, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		infraSyntaxM2Node.addModelingAttribute("description",
				new ElemAttribute("description", "String",
						AttributeType.SYNTAX, false, "Description", "", "", 0,
						5, "", "", -1, "", ""));

		infraSyntaxM2Node.addModelingAttribute("Visible", new ElemAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				"Shows/Hides the view in the modeling perspective", true, 0, 2,
				"", "", -1, "", ""));
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
				"dynamic meta-attributes", "",
				"com.variamos.dynsup.model.ElemAttribute", "", "", 0, -1, "",
				"", -1, "", ""));

		infraSyntaxM2Node.addModelingAttribute("OperationsMMType",
				new ElemAttribute("OperationsMMType", "Class",
						AttributeType.OPERATION, false, "Operations MMType",
						"Type from the Operations Meta-Model",
						OpersConcept.class.getCanonicalName(), "C", null, "",
						0, 0, "", "", 1, "<<SyMConcept>>\n{SeType:\"#"
								+ "OperationsMMType" + "#all#\"}\n", ""));

		InstConcept instInfraSyntaxOpersM2Node = new InstConcept("SyMNode",
				infraBasicSyntaxOpersM3Node, infraSyntaxM2Node);
		variabilityInstVertex.put("SyMNode", instInfraSyntaxOpersM2Node);

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
				"Position of the view in the modeling perspective (relative"
						+ " to other views)", 3, 0, 2, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Identifier", new ElemAttribute(
				"Identifier", "String", AttributeType.SYNTAX, false,
				"View Identifier", "", "", 0, -1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Visible", new ElemAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				"Shows/Hides the view in the modeling perspective", true, 0, 3,
				"", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "View name",
				"Name to display in the palette of the view (Modeling"
						+ " perspectiv)", "", 0, 5, "", "", -1, "", ""));
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
				infraBasicSyntaxOpersM3Node, infraSyntaxM2View);
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
				"Shows/Hides the element in the modeling perspective", true, 0,
				2, "", "", -1, "", ""));
		infraSyntaxM2OTRel.addModelingAttribute("Name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "MConcept Name",
				"", "", 0, 3, "", "", -1, "", ""));
		infraSyntaxM2OTRel.addModelingAttribute("Style", new ElemAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style",
				"Style to render the element on the modeling perspective",
				"refasclaim", 0, 4, "", "", -1, "", ""));
		infraSyntaxM2OTRel.addModelingAttribute("Width", new ElemAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Default width of the element", "", 130, 0, 6, "", "", -1, "",
				""));
		infraSyntaxM2OTRel.addModelingAttribute("Height", new ElemAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Default height of the element", "", 90, 0, 7, "", "", -1, "",
				""));
		infraSyntaxM2OTRel.addModelingAttribute("Image", new ElemAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"Icon image for the element on the palette of the"
						+ " modeling perspective ",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, 8, "", "",
				-1, "", ""));
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
				"value", "Set", AttributeType.SYNTAX, false, "values", "",
				"com.variamos.dynsup.model.ElemAttribute", "", "", 0, -1, "",
				"", -1, "", ""));

		infraSyntaxM2OTRel.addModelingAttribute("OperationsMMType",
				new ElemAttribute("OperationsMMType", "Class",
						AttributeType.OPERATION, false, "Operations MMType",
						"Type from the Operations Meta-Model",
						OpersConcept.class.getCanonicalName(), "O", null, "",
						0, 0, "", "", 1, "<<SyMN-ary>>\n"// "<<SyMOverTwoAsso>>\n"
								+ "{SeType:\"#"
								+ "OperationsMMType"
								+ "#all#\"}\n", ""));
		infraSyntaxM2OTRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 3, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n", "");

		infraSyntaxM2OTRel.addModelingAttribute("Type", new ElemAttribute(
				"Type", "String", AttributeType.SYNTAX, false, "Relation Type",
				"", "", 0, -1, "", "", -1, "", ""));

		InstConcept instInfraSyntaxM2OTRel = new InstConcept("SyMOverTwo",
				infraBasicSyntaxOpersM3Node, infraSyntaxM2OTRel);
		variabilityInstVertex.put("SyMOverTwo", instInfraSyntaxM2OTRel);

		SyntaxElement infraSyntaxM2NormalRelation = new SyntaxElement('P',
				"SyMAso", false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("SyMAso", new InstPairwiseRel("SyMAso",
				infraSyntaxM2NormalRelation));

		SyntaxElement infraSyntaxM2ExtendsRelation = new SyntaxElement('X',
				"SyMExtend", true, true, "SyMExtend",
				"infrasyntaxm2microconcept", "Extend relation", 80, 50,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxM2ExtendsRelation.addModelingAttribute("Visible",
				new ElemAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible",
						"Shows/Hides the view in the modeling perspective",
						true, 0, 2, "", "", -1, "", ""));
		infraSyntaxM2ExtendsRelation.addModelingAttribute("Name",
				new ElemAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", "", 0, -1, "", "", 1,
						"<<SyMGeneralization>>\n#Name#all#\n\n", ""));
		infraSyntaxM2ExtendsRelation.addModelingAttribute("value",
				new ElemAttribute("value", "Set", AttributeType.SYNTAX, false,
						"values", "",
						"com.variamos.dynsup.model.ElemAttribute", "", "", 0,
						-1, "", "", -1, "", ""));

		SyntaxElement infraSyntaxM2LanguageView = new SyntaxElement('I',
				"SyMLangView", true, true, "SyMLangView",
				"infrasyntaxm2microconcept", "View-Concept Association", 150,
				40, "/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxM2LanguageView.addModelingAttribute("visible",
				new ElemAttribute("visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible",
						"Shows/Hides the view in the modeling perspective",
						true, 0, 2, "", "", -1, "", ""));

		InstConcept instInfraSyntaxM2ViewLanguageView = new InstConcept(
				"SyMLangView", infraBasicSyntaxOpersM3Node,
				infraSyntaxM2LanguageView);

		variabilityInstVertex.put("SyMLangView",
				instInfraSyntaxM2ViewLanguageView);

		InstPairwiseRel instEdge = new InstPairwiseRel();
		constraintInstEdges.put("lan-v-lc", instEdge);
		instEdge.setIdentifier("lan-v-lc");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2ViewLanguageView, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Lang, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("lan-lc-v", instEdge);
		instEdge.setIdentifier("lan-lc-v");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2View, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ViewLanguageView, true);

		InstConcept instInfraSyntaxM2ExtendsRelation = new InstConcept(
				"SyMExtend", infraBasicSyntaxOpersM3Node,
				infraSyntaxM2ExtendsRelation);
		// FIXME change to infraBasicSyntaxOpersM3Arrow and SyMGeneralization
		// Update the implementation to understand the generalization as a
		// relation and not a concept

		variabilityInstVertex
				.put("SyMExtend", instInfraSyntaxM2ExtendsRelation);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("ce-e-c", instEdge);
		instEdge.setIdentifier("ce-e-c");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Node, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ExtendsRelation, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("ce-c-e", instEdge);
		instEdge.setIdentifier("ce-c-e");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2ExtendsRelation, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Node, true);

		SyntaxElement metaPairwiseRelExtends = new SyntaxElement('P',
				"ExtendsRelation", false, true, "Extends Relation",
				"refasextends", "Extends relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("ExtendsRelation", new InstPairwiseRel(
				"ExtendsRelation", metaPairwiseRelExtends));

		SyntaxElement infraSyntaxM2Asso = new SyntaxElement('I',
				"SyMAssociation", true, true, "SyMAssociation",
				"infrasyntaxm2microconcept", "View-Concept Association", 150,
				40, "/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instinfraSyntaxM2Asso = new InstConcept("SyMAssociation",
				infraBasicSyntaxOpersM3Arrow, infraSyntaxM2Asso);
		variabilityInstVertex.put("SyMAssociation", instinfraSyntaxM2Asso);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("asso-e-e1", instEdge);
		instEdge.setIdentifier("asso-e-e1");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instinfraSyntaxM2Asso, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Element, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("asso-e-e2", instEdge);
		instEdge.setIdentifier("asso-e-e2");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Element, true);
		instEdge.setSourceRelation(instinfraSyntaxM2Asso, true);

		SyntaxElement infraSyntaxM2ViewConceptAsso = new SyntaxElement('I',
				"SyMViewNode", true, true, "SyMViewNode",
				"infrasyntaxm2microconcept", "View-Concept Association", 150,
				40, "/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxM2ViewConceptAsso.addModelingAttribute("Visible",
				new ElemAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible",
						"Shows/Hides the view in the modeling perspective",
						true, 0, 2, "", "", -1, "", ""));
		infraSyntaxM2ViewConceptAsso
				.addModelingAttribute("Name", new ElemAttribute("Name",
						"String", AttributeType.SYNTAX, false, "Concept Name",
						"", "", 0, 3, "", "", -1, "", ""));
		infraSyntaxM2ViewConceptAsso.addModelingAttribute("value",
				new ElemAttribute("value", "Set", AttributeType.SYNTAX, false,
						"values", "",
						"com.variamos.dynsup.model.ElemAttribute", "", "", 0,
						-1, "", "", -1, "", ""));
		infraSyntaxM2ViewConceptAsso.addModelingAttribute("dummy",
				new ElemAttribute("dummy", "String", AttributeType.SYNTAX,
						false, "dummy", "", "", 0, -1, "", "", 1,
						"<<SyMViewConceptAsso>>#dummy#all#\n", ""));

		infraSyntaxM2ViewConceptAsso.addModelingAttribute("Palette",
				new ElemAttribute("Palette", "String", AttributeType.SYNTAX,
						false, "Palette Name", "", "", 0, 2, "", "", 2,
						"{Palette:#" + "Palette" + "#all#}\n", "Palette"
								+ "#!=#" + ""));

		InstConcept instInfraSyntaxM2ViewConceptAsso = new InstConcept(
				"SyMViewNode", infraBasicSyntaxOpersM3Node,
				infraSyntaxM2ViewConceptAsso);
		// FIXME change to infraBasicSyntaxOpersM3Arrow
		// Update the implementation to understand the visibility as a relation
		// and not a concept

		variabilityInstVertex.put("SyMViewNode",
				instInfraSyntaxM2ViewConceptAsso);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-v-vc", instEdge);
		instEdge.setIdentifier("vc-v-vc");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2ViewConceptAsso, true);
		instEdge.setSourceRelation(instInfraSyntaxM2View, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-vc-e", instEdge);
		instEdge.setIdentifier("vc-vc-e");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Element, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ViewConceptAsso, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-ot", instEdge);
		instEdge.setIdentifier("vc-ot");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2OTRel, true);
		instEdge.setSourceRelation(instInfraSyntaxM2View, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-n", instEdge);
		instEdge.setIdentifier("vc-n");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Node, true);
		instEdge.setSourceRelation(instInfraSyntaxM2View, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-nt", instEdge);
		instEdge.setIdentifier("vc-nt");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Node, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Node, true);

		// FIME next two relations mainatied for current compatibility, with
		// genericity should not be needed

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-vc-c", instEdge);
		instEdge.setIdentifier("vc-vc-c");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Node, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ViewConceptAsso, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-vc-otr", instEdge);
		instEdge.setIdentifier("vc-vc-otr");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2OTRel, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ViewConceptAsso, true);

		SyntaxElement metaPairwiseRelFromView = new SyntaxElement('P',
				"ViewRelation", false, true, "View Relation",
				"infrasyntaxm2viewrel", "View-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

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
						0, 0, "", "", 1, "<<SyMBinary>>\n" // "<<SyMPairwiseAsso>>\n"
								+ "{SeType:\"#"
								+ "OperationsMMType"
								+ "#all#\",\n", ""));
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
				"Shows/Hides the view in the modeling perspective", true, 0, 2,
				"", "", -1, "", ""));
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
				"value", "Set", AttributeType.SYNTAX, false, "values", "",
				"com.variamos.dynsup.model.ElemAttribute", "", "", 0, -1, "",
				"", -1, "", ""));

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
				infraBasicSyntaxOpersM3Node, infraSyntaxM2PWRel);
		variabilityInstVertex.put("SyMPairwise", instInfraSyntaxM2PWRel);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("cpw-pw-c", instEdge);
		instEdge.setIdentifier("cpw-pw-c");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Node, true);
		instEdge.setSourceRelation(instInfraSyntaxM2PWRel, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("cpw-c-pw", instEdge);
		instEdge.setIdentifier("cpw-c-pw");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2PWRel, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Node, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("cpw-c-c", instEdge);
		instEdge.setIdentifier("cpw-c-c");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Node, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Node, true);

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

		SyntaxElement infraSyntaxM2Test = new SyntaxElement('P', "SyMTest",
				true, true, "SyMTest", "infrasyntaxm2microconcept",
				"SyntaxPairwiseRel", 200, 90,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instInfraSyntaxM2Test = new InstConcept("SyMTest",
				infraBasicSyntaxOpersM3Node, infraSyntaxM2Test);
		variabilityInstVertex.put("SyMTest", instInfraSyntaxM2Test);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("cpw-pw-ct", instEdge);
		instEdge.setIdentifier("cpw-pw-ct");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Node, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Node, true);

		SyntaxElement infraOpersM2Attribute = new SyntaxElement('A',
				"SyMAttribute", false, true, "SyMAttribute",
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

		// attribute Type
		infraOpersM2Attribute.addModelingAttribute("displayType",
				new ElemAttribute("displayType", "MetaEnumeration",
						AttributeType.SYNTAX, false, "Display Type", "",
						"DisplayTypeEnum", "", "", 0, 3, "", "", -1,
						"displayType" + "#all#\n\n", ""));

		infraOpersM2Attribute.addModelingAttribute("affectProps", "Boolean",
				false, "Affect Properties", "", "", 0, 4, "", "", -1, "#"
						+ "affectProps" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("dispName", "String", false,
				"Display Name", "", "", 0, 5, "", "", -1, "#" + "dispName"
						+ "#all#\n\n", "");

		// classCanName
		infraOpersM2Attribute.addModelingAttribute("enumType", "String", false,
				"Enumeration Type", "", "", 0, 7, "", "", -1, "#" + "enumType"
						+ "#all#\n\n", "");

		// metaCInstType
		infraOpersM2Attribute.addModelingAttribute("instanceType", "String",
				false, "Instance Type", "", "", 0, 8, "", "", -1, "#"
						+ "instanceType" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("defaultValue", "String",
				false, "Default Value", "", "", 0, 9, "", "", -1, "#"
						+ "defaultValue" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("domain", "String", false,
				"Domain", "", "", 0, 10, "", "", -1, "#" + "domain"
						+ "#all#\n\n", "");
		infraOpersM2Attribute.addModelingAttribute("hint", "String", false,
				"Hint", "", "", 0, 11, "", "", -1, "#" + "hint" + "#all#\n\n",
				"");

		infraOpersM2Attribute.addModelingAttribute("propertiesPosition",
				"Integer", false, "Prop. Tab Position", "", "", 0, 12, "", "",
				-1, "#" + "propTabPosition" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("graphDisplayPosition",
				"Integer", false, "Element Disp. Position", "", "", 0, 13, "",
				"", -1, "#" + "elementDisplayPosition" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("graphDisplaySpacers",
				"String", false, "Element Disp. Spacers", "", "", 0, 14, "",
				"", -1, "#" + "elementDisplaySpacers" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute(
				"propertiesEditionCondition", "String", false,
				"Prop. Tab Edition Cond.", "", "", 0, 15, "", "", -1, "#"
						+ "propTabEditionCondition" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("propertiesVisualCondition",
				"String", false, "Prop. Tab Visual Cond.", "", "", 0, 16, "",
				"", -1, "#" + "propTabVisualCondition" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("graphDisplayCondition",
				"String", false, "Graph Visual Cond.", "", "", 0, 17, "", "",
				-1, "#" + "elementDisplayCondition" + "#all#\n\n", "");

		// infraOpersM2Attribute.addModelingAttribute("domFiltOwnFields",
		// "String", false, "Filter domain (Own Fields)", "", "", 0, 18,
		// "", "", -1, "#" + "domFiltOwnFields" + "#all#\n\n", "");
		//
		// infraOpersM2Attribute.addModelingAttribute("domFilTRelFields",
		// "String", false, "Filter domain (Rel. Fields)", "", "", 0, 19,
		// "", "", -1, "#" + "domFilTRelFields" + "#all#\n\n", "");
		//
		// infraOpersM2Attribute.addModelingAttribute("defDomValueField",
		// "String", false, "Def. Domain (Filter Field)", "", "", 0, 20,
		// "", "", -1, "#" + "defDomValueField" + "#all#\n\n", "");

		InstConcept instInfraSyntaxOpersM2Attribute = new InstConcept(
				"SyMAttribute", infraBasicSyntaxOpersM3Set,
				infraOpersM2Attribute);
		variabilityInstVertex.put("SyMAttribute",
				instInfraSyntaxOpersM2Attribute);

		SyntaxElement infraSyntaxM2ConAtt = new SyntaxElement('P', "SyMConAtt",
				false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("SymConAtt", new InstPairwiseRel("SyMConAtt",
				infraSyntaxM2ConAtt));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-att", instEdge);
		instEdge.setIdentifier("co-att");
		instEdge.setEdSyntaxEle(infraSyntaxM2ConAtt);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Attribute, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Element, true);

		SyntaxElement infraSyntaxM2NaryEleGen = new SyntaxElement('P',
				"SyMNaryEleGen", false, true, "Normal Relation",
				"refasextends", "Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("SyMNaryEleGen", new InstPairwiseRel(
				"SyMNaryEleGen", infraSyntaxM2NaryEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-NaryEleGen", instEdge);
		instEdge.setIdentifier("co-NaryEleGen");
		instEdge.setTransSupInstElement(infraBasicSyntaxOpersM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM2NaryEleGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Element, true);
		instEdge.setSourceRelation(instInfraSyntaxM2OTRel, true);

		SyntaxElement infraSyntaxM2BinaryEleGen = new SyntaxElement('P',
				"SyMBinaryEleGen", false, true, "Normal Relation",
				"refasextends", "Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("SyMBinaryEleGen", new InstPairwiseRel(
				"SyMBinaryEleGen", infraSyntaxM2BinaryEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-BinaryEleGen", instEdge);
		instEdge.setIdentifier("co-BinaryEleGen");
		instEdge.setTransSupInstElement(infraBasicSyntaxOpersM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM2BinaryEleGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Element, true);
		instEdge.setSourceRelation(instInfraSyntaxM2PWRel, true);

		SyntaxElement infraSyntaxM2NodeEleGen = new SyntaxElement('P',
				"SyMNodeEleGen", false, true, "Normal Relation",
				"refasextends", "Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("SyMNodeEleGen", new InstPairwiseRel(
				"SyMNodeEleGen", infraSyntaxM2NodeEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-NodeEleGen", instEdge);
		instEdge.setIdentifier("co-NodeEleGen");
		instEdge.setTransSupInstElement(infraBasicSyntaxOpersM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM2ConAtt);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Element, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Node, true);

	}
}