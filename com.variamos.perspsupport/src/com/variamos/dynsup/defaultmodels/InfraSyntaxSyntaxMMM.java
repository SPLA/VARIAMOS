package com.variamos.dynsup.defaultmodels;

import java.awt.Color;
import java.util.Map;

import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersAttribute;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.SyntaxAttribute;
import com.variamos.dynsup.model.SyntaxConcept;
import com.variamos.dynsup.model.SyntaxPairwiseRel;
import com.variamos.dynsup.types.ConceptType;
import com.variamos.semantic.types.AttributeType;

public class InfraSyntaxSyntaxMMM {
	public static void createSyntaxSyntaxMetaMetaModel(
			ModelInstance modelInstance) {

		Map<String, InstElement> variabilityInstVertex = modelInstance
				.getVariabilityVertex();

		Map<String, InstPairwiseRel> constraintInstEdges = modelInstance
				.getConstraintInstEdges();
		// Begin Syntax M3 Model

		SyntaxConcept infraBasicSyntaxOpersM3Concept = new SyntaxConcept('C',
				"SMMMConcept", true, true, "SMMMConcept", "refasminiclass",
				"Syntax Meta Meta Meta Concept", 180, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraBasicSyntaxOpersM3Concept.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraBasicSyntaxOpersM3Concept.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Name",
				new SyntaxAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));

		infraBasicSyntaxOpersM3Concept.addModelingAttribute("MetaType",
				new SyntaxAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "MetaConcept Type",
						ConceptType.class.getCanonicalName(), "SyntaxConcept",
						0, -1, "", "", -1, "", ""));
		// metaBasicConcept.addModelingAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Visible",
				new SyntaxAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", true, 0, -1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Name",
				new SyntaxAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Style",
				new SyntaxAttribute("Style", "String", AttributeType.SYNTAX,
						false, "Drawing Style", "refasclaim", 0, -1, "", "",
						-1, "", ""));
		infraBasicSyntaxOpersM3Concept
				.addModelingAttribute("Width", new SyntaxAttribute("Width",
						"Integer", AttributeType.SYNTAX, false,
						"Initial Width", 100, 0, -1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept
				.addModelingAttribute("Height", new SyntaxAttribute("Height",
						"Integer", AttributeType.SYNTAX, false,
						"Initial Height", 40, 0, -1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Image",
				new SyntaxAttribute("Image", "String", AttributeType.SYNTAX,
						false, "Image File",
						"/com/variamos/gui/perspeditor/images/claim.png", 0,
						-1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("TopConcept",
				new SyntaxAttribute("TopConcept", "Boolean",
						AttributeType.SYNTAX, false, "Is Top Concept", true, 0,
						-1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Resizable",
				new SyntaxAttribute("Resizable", "Boolean",
						AttributeType.SYNTAX, false, "Is Resizable", true, 0,
						-1, "", "", -1, "", ""));

		// End Syntax M3 Model

		// Begin Opers M2 Model

		// OpersConcept infraOpersM2View = new OpersConcept();
		//
		// infraOpersM2View.putSemanticAttribute("MetaType", new
		// SyntaxAttribute(
		// "MetaType", "Enumeration", AttributeType.SYNTAX, false,
		// "MetaConcept Type", ConceptType.class.getCanonicalName(),
		// "SyntaxView", 0, -1, "", "", -1, "", ""));
		// infraOpersM2View.putSemanticAttribute("Index", new SyntaxAttribute(
		// "Index", "Integer", AttributeType.SYNTAX, false, "View Index",
		// 3, 0, -1, "", "", -1, "", ""));
		// infraOpersM2View.putSemanticAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String",
		// AttributeType.SYNTAX, false, "View Identifier", "", 0,
		// -1, "", "", -1, "", ""));
		// infraOpersM2View.putSemanticAttribute("Visible", new SyntaxAttribute(
		// "Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
		// true, 0, -1, "", "", -1, "", ""));
		// // semView.putSemanticAttribute("Parent", new
		// SyntaxAttribute("Parent",
		// // "String", AttributeType.SYNTAX, false, "Parent View", "", 0,
		// // -1, "", "", -1, "", ""));
		// infraOpersM2View.putSemanticAttribute("Name", new SyntaxAttribute(
		// "Name", "String", AttributeType.SYNTAX, false, "Concept Name",
		// "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2View.putSemanticAttribute("Style", new SyntaxAttribute(
		// "Style", "String", AttributeType.SYNTAX, false,
		// "Drawing Style", "refasclaim", 0, -1, "", "", -1, "", ""));
		// infraOpersM2View.putSemanticAttribute("Description",
		// new SyntaxAttribute("Description", "String",
		// AttributeType.SYNTAX, false, "Description", "", 0, -1,
		// "", "", -1, "", ""));
		// infraOpersM2View.putSemanticAttribute("Width", new SyntaxAttribute(
		// "Width", "Integer", AttributeType.SYNTAX, false,
		// "Initial Width", 100, 0, -1, "", "", -1, "", ""));
		// infraOpersM2View.putSemanticAttribute("Height", new SyntaxAttribute(
		// "Height", "Integer", AttributeType.SYNTAX, false,
		// "Initial Height", 40, 0, -1, "", "", -1, "", ""));
		// infraOpersM2View.putSemanticAttribute("Image", new SyntaxAttribute(
		// "Image", "String", AttributeType.SYNTAX, false, "Image File",
		// "/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
		// "", -1, "", ""));
		// infraOpersM2View.putSemanticAttribute("BorderStroke",
		// new SyntaxAttribute("BorderStroke", "Integer",
		// AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
		// "", "", -1, "", ""));
		// infraOpersM2View.putSemanticAttribute("PaletteNames",
		// new SyntaxAttribute("PaletteNames", "String",
		// AttributeType.SYNTAX, false, "Palette Name", "", 0, -1,
		// "", "", -1, "", ""));
		//
		// infraOpersM2View.addPropEditableAttribute("03#" + "PaletteNames");
		// infraOpersM2View.addPropVisibleAttribute("03#" + "PaletteNames");
		// infraOpersM2View.addPanelVisibleAttribute("05#" + "PaletteNames" +
		// "#"
		// + "PaletteNames" + "#!=#" + "" + "#" + "");
		// infraOpersM2View.addPanelSpacersAttribute("{Palettes:#"
		// + "PaletteNames" + "#}\n\n");
		// infraOpersM2View.addPropVisibleAttribute("00#" + "MetaType");
		// // semView.addPropEditableAttribute("01#" + "Identifier");
		// // semView.addPropVisibleAttribute("01#" + "Identifier");
		// infraOpersM2View.addPropEditableAttribute("02#" + "Index");
		// infraOpersM2View.addPropVisibleAttribute("02#" + "Index");
		// infraOpersM2View.addPropEditableAttribute("03#" + "Visible");
		// infraOpersM2View.addPropVisibleAttribute("03#" + "Visible");
		// // semView.addPropEditableAttribute("04#" + "Parent");
		// // semView.addPropVisibleAttribute("04#" + "Parent");
		// infraOpersM2View.addPropEditableAttribute("05#" + "Name");
		// infraOpersM2View.addPropVisibleAttribute("05#" + "Name");
		// infraOpersM2View.addPropEditableAttribute("06#" + "Style");
		// infraOpersM2View.addPropVisibleAttribute("06#" + "Style");
		// infraOpersM2View.addPropEditableAttribute("07#" + "Description");
		// infraOpersM2View.addPropVisibleAttribute("07#" + "Description");
		// infraOpersM2View.addPropEditableAttribute("08#" + "Width");
		// infraOpersM2View.addPropVisibleAttribute("08#" + "Width");
		// infraOpersM2View.addPropEditableAttribute("09#" + "Height");
		// infraOpersM2View.addPropVisibleAttribute("09#" + "Height");
		// infraOpersM2View.addPropEditableAttribute("10#" + "Image");
		// infraOpersM2View.addPropVisibleAttribute("10#" + "Image");
		// // semView.addDisPropEditableAttribute("11#" + "BorderStroke");
		// infraOpersM2View.addPropVisibleAttribute("11#" + "BorderStroke");
		//
		// // semView.addPanelVisibleAttribute("01#" + "Name");
		// // semView.addPanelSpacersAttribute("#" + "Name" + "#");
		//
		// InstConcept instInfraOpersM2View = new InstConcept("SMMView", null,
		// infraOpersM2View);

		// OpersConcept infraOpersM2Concept = new OpersConcept("SMMConcept");
		//
		// infraOpersM2Concept.putSemanticAttribute("Name", new SyntaxAttribute(
		// "Name", "String", AttributeType.SYNTAX, false,
		// "Meta Concept Name", "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2Concept.putSemanticAttribute("Description",
		// new SyntaxAttribute("Description", "String",
		// AttributeType.SYNTAX, false, "Description", "", 0, -1,
		// "", "", -1, "", ""));
		//
		// infraOpersM2Concept.putSemanticAttribute("MetaType",
		// new SyntaxAttribute("MetaType", "Enumeration",
		// AttributeType.SYNTAX, false, "MetaConcept Type",
		// ConceptType.class.getCanonicalName(), "SyntaxConcept",
		// 0, -1, "", "", -1, "", ""));
		// // semVertex.putSemanticAttribute("Identifier", new SyntaxAttribute(
		// // "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// // "", "", -1, "", ""));
		// infraOpersM2Concept.putSemanticAttribute("Visible",
		// new SyntaxAttribute("Visible", "Boolean", AttributeType.SYNTAX,
		// false, "Visible", true, 0, -1, "", "", -1, "", ""));
		// infraOpersM2Concept.putSemanticAttribute("Name", new SyntaxAttribute(
		// "Name", "String", AttributeType.SYNTAX, false, "MConcept Name",
		// "", 0, -1, "", "", -1, "", ""));
		// infraOpersM2Concept.putSemanticAttribute("Style", new
		// SyntaxAttribute(
		// "Style", "String", AttributeType.SYNTAX, false,
		// "Drawing Style", "refasclaim", 0, -1, "", "", -1, "", ""));
		// infraOpersM2Concept.putSemanticAttribute("Width", new
		// SyntaxAttribute(
		// "Width", "Integer", AttributeType.SYNTAX, false,
		// "Initial Width", 100, 0, -1, "", "", -1, "", ""));
		// infraOpersM2Concept.putSemanticAttribute("Height", new
		// SyntaxAttribute(
		// "Height", "Integer", AttributeType.SYNTAX, false,
		// "Initial Height", 40, 0, -1, "", "", -1, "", ""));
		// infraOpersM2Concept.putSemanticAttribute("Image", new
		// SyntaxAttribute(
		// "Image", "String", AttributeType.SYNTAX, false, "Image File",
		// "/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
		// "", -1, "", ""));
		// infraOpersM2Concept.putSemanticAttribute("TopConcept",
		// new SyntaxAttribute("TopConcept", "Boolean",
		// AttributeType.SYNTAX, false, "Is Top Concept", true, 0,
		// -1, "", "", -1, "", ""));
		// infraOpersM2Concept.putSemanticAttribute("BackgroundColor",
		// new SyntaxAttribute("BackgroundColor", "String",
		// AttributeType.SYNTAX, false, "Background Color",
		// "java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
		// ""));
		// infraOpersM2Concept.putSemanticAttribute("BorderStroke",
		// new SyntaxAttribute("BorderStroke", "Integer",
		// AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
		// "", "", -1, "", ""));
		// infraOpersM2Concept.putSemanticAttribute("Resizable",
		// new SyntaxAttribute("Resizable", "Boolean",
		// AttributeType.SYNTAX, false, "Is Resizable", true, 0,
		// -1, "", "", -1, "", ""));
		// infraOpersM2Concept.putSemanticAttribute("value", new
		// SyntaxAttribute(
		// "value", "Set", AttributeType.SYNTAX, false, "values", "", 0,
		// -1, "", "", -1, "", ""));
		//
		// infraOpersM2Concept.addPropVisibleAttribute("00#" + "MetaType");
		// // semVertex.addPropEditableAttribute("01#" + "Identifier");
		// // semVertex.addPropVisibleAttribute("01#" + "Identifier");
		// infraOpersM2Concept.addPropEditableAttribute("02#" + "Visible");
		// infraOpersM2Concept.addPropVisibleAttribute("02#" + "Visible");
		// infraOpersM2Concept.addPropEditableAttribute("03#" + "Name");
		// infraOpersM2Concept.addPropVisibleAttribute("03#" + "Name");
		// infraOpersM2Concept.addPropEditableAttribute("04#" + "Style");
		// infraOpersM2Concept.addPropVisibleAttribute("04#" + "Style");
		// infraOpersM2Concept.addPropEditableAttribute("05#" + "Description");
		// infraOpersM2Concept.addPropVisibleAttribute("05#" + "Description");
		// infraOpersM2Concept.addPropEditableAttribute("06#" + "Width");
		// infraOpersM2Concept.addPropVisibleAttribute("06#" + "Width");
		// infraOpersM2Concept.addPropEditableAttribute("07#" + "Height");
		// infraOpersM2Concept.addPropVisibleAttribute("07#" + "Height");
		// infraOpersM2Concept.addPropEditableAttribute("08#" + "Image");
		// infraOpersM2Concept.addPropVisibleAttribute("08#" + "Image");
		// infraOpersM2Concept.addPropEditableAttribute("09#" + "TopConcept");
		// infraOpersM2Concept.addPropVisibleAttribute("09#" + "TopConcept");
		// infraOpersM2Concept.addPropEditableAttribute("10#" +
		// "BackgroundColor");
		// infraOpersM2Concept.addPropVisibleAttribute("10#" +
		// "BackgroundColor");
		// infraOpersM2Concept.addPropEditableAttribute("11#" + "BorderStroke");
		// infraOpersM2Concept.addPropVisibleAttribute("11#" + "BorderStroke");
		// infraOpersM2Concept.addPropEditableAttribute("12#" + "Resizable");
		// infraOpersM2Concept.addPropVisibleAttribute("12#" + "Resizable");
		//
		// infraOpersM2Concept.putSemanticAttribute("OperationsMMType",
		// new OpersAttribute("OperationsMMType", "Class",
		// AttributeType.OPERATION, false, "Operations MMType",
		// OpersConcept.class.getCanonicalName(), "C", null, "",
		// 0, -1, "", "", -1, "", ""));
		//
		// infraOpersM2Concept
		// .addPropEditableAttribute("00#" + "OperationsMMType");
		// infraOpersM2Concept.addPropVisibleAttribute("00#" +
		// "OperationsMMType");
		//
		// InstConcept instInfraOpersM2Concept = new InstConcept("SMMConcept",
		// null, infraOpersM2Concept);

		SyntaxConcept infraSyntaxM2Concept = new SyntaxConcept('C',
				"SMMConcept", true, true, "SMMConcept",
				"infrasyntaxm2miniconcept", "Syntax Meta Concept", 150, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxM2Concept.addModelingAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false,
				"Meta Concept Name", "", 0, -1, "", "", -1, "", ""));
		infraSyntaxM2Concept.addModelingAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));

		infraSyntaxM2Concept.addModelingAttribute("MetaType",
				new SyntaxAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "MetaConcept Type",
						ConceptType.class.getCanonicalName(), "SyntaxConcept",
						0, -1, "", "", -1, "", ""));
		// semVertex.putSemanticAttribute("Identifier", new SyntaxAttribute(
		// "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// "", "", -1, "", ""));
		infraSyntaxM2Concept.addModelingAttribute("Visible",
				new SyntaxAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", true, 0, -1, "", "", -1, "", ""));
		infraSyntaxM2Concept.addModelingAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "MConcept Name",
				"", 0, -1, "", "", -1, "", ""));
		infraSyntaxM2Concept.addModelingAttribute("Style", new SyntaxAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "refasclaim", 0, -1, "", "", -1, "", ""));
		infraSyntaxM2Concept.addModelingAttribute("Width", new SyntaxAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", 100, 0, -1, "", "", -1, "", ""));
		infraSyntaxM2Concept
				.addModelingAttribute("Height", new SyntaxAttribute("Height",
						"Integer", AttributeType.SYNTAX, false,
						"Initial Height", 40, 0, -1, "", "", -1, "", ""));
		infraSyntaxM2Concept.addModelingAttribute("Image", new SyntaxAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		infraSyntaxM2Concept.addModelingAttribute("TopConcept",
				new SyntaxAttribute("TopConcept", "Boolean",
						AttributeType.SYNTAX, false, "Is Top Concept", true, 0,
						-1, "", "", -1, "", ""));
		infraSyntaxM2Concept.addModelingAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		infraSyntaxM2Concept.addModelingAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		infraSyntaxM2Concept.addModelingAttribute("Resizable",
				new SyntaxAttribute("Resizable", "Boolean",
						AttributeType.SYNTAX, false, "Is Resizable", true, 0,
						-1, "", "", -1, "", ""));
		infraSyntaxM2Concept.addModelingAttribute("value", new SyntaxAttribute(
				"value", "Set", AttributeType.SYNTAX, false, "values", "", 0,
				-1, "", "", -1, "", ""));

		infraSyntaxM2Concept.addPropVisibleAttribute("00#" + "MetaType");
		// semVertex.addPropEditableAttribute("01#" + "Identifier");
		// semVertex.addPropVisibleAttribute("01#" + "Identifier");
		infraSyntaxM2Concept.addPropEditableAttribute("02#" + "Visible");
		infraSyntaxM2Concept.addPropVisibleAttribute("02#" + "Visible");
		infraSyntaxM2Concept.addPropEditableAttribute("03#" + "Name");
		infraSyntaxM2Concept.addPropVisibleAttribute("03#" + "Name");
		infraSyntaxM2Concept.addPropEditableAttribute("04#" + "Style");
		infraSyntaxM2Concept.addPropVisibleAttribute("04#" + "Style");
		infraSyntaxM2Concept.addPropEditableAttribute("05#" + "Description");
		infraSyntaxM2Concept.addPropVisibleAttribute("05#" + "Description");
		infraSyntaxM2Concept.addPropEditableAttribute("06#" + "Width");
		infraSyntaxM2Concept.addPropVisibleAttribute("06#" + "Width");
		infraSyntaxM2Concept.addPropEditableAttribute("07#" + "Height");
		infraSyntaxM2Concept.addPropVisibleAttribute("07#" + "Height");
		infraSyntaxM2Concept.addPropEditableAttribute("08#" + "Image");
		infraSyntaxM2Concept.addPropVisibleAttribute("08#" + "Image");
		infraSyntaxM2Concept.addPropEditableAttribute("09#" + "TopConcept");
		infraSyntaxM2Concept.addPropVisibleAttribute("09#" + "TopConcept");
		infraSyntaxM2Concept
				.addPropEditableAttribute("10#" + "BackgroundColor");
		infraSyntaxM2Concept.addPropVisibleAttribute("10#" + "BackgroundColor");
		infraSyntaxM2Concept.addPropEditableAttribute("11#" + "BorderStroke");
		infraSyntaxM2Concept.addPropVisibleAttribute("11#" + "BorderStroke");
		infraSyntaxM2Concept.addPropEditableAttribute("12#" + "Resizable");
		infraSyntaxM2Concept.addPropVisibleAttribute("12#" + "Resizable");

		infraSyntaxM2Concept.addModelingAttribute("OperationsMMType",
				new OpersAttribute("OperationsMMType", "Class",
						AttributeType.OPERATION, false, "Operations MMType",
						OpersConcept.class.getCanonicalName(), "C", null, "",
						0, -1, "", "", -1, "", ""));

		infraSyntaxM2Concept.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxM2Concept.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		infraSyntaxM2Concept.addPanelVisibleAttribute("00#"
				+ "OperationsMMType");
		infraSyntaxM2Concept
				.addPanelSpacersAttribute("<<MetaConcept>>\n{OperType:\"#"
						+ "OperationsMMType" + "#\"}\n");
		// concept.addPanelVisibleAttribute("01#" + "Name");
		// concept.addPanelSpacersAttribute("#" + "Name" + "#\n\n");

		InstConcept instInfraSyntaxOpersM2Concept = new InstConcept(
				"SMMConcept", infraBasicSyntaxOpersM3Concept,
				infraSyntaxM2Concept);
		variabilityInstVertex.put("SMMConcept", instInfraSyntaxOpersM2Concept);

		OpersConcept infraOpersM2OTRel = new OpersConcept("SMMOverTwoRelation");

		infraOpersM2OTRel.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false,
				"Meta Concept Name", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));

		infraOpersM2OTRel.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaConcept Type", ConceptType.class.getCanonicalName(),
				"SyntaxConcept", 0, -1, "", "", -1, "", ""));
		// semVertex.putSemanticAttribute("Identifier", new SyntaxAttribute(
		// "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				true, 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "MConcept Name",
				"", 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Style", new SyntaxAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "refasclaim", 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Width", new SyntaxAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", 100, 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Height", new SyntaxAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", 40, 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Image", new SyntaxAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("TopConcept",
				new SyntaxAttribute("TopConcept", "Boolean",
						AttributeType.SYNTAX, false, "Is Top Concept", true, 0,
						-1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		infraOpersM2OTRel.putSemanticAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Resizable",
				new SyntaxAttribute("Resizable", "Boolean",
						AttributeType.SYNTAX, false, "Is Resizable", true, 0,
						-1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("value", new SyntaxAttribute(
				"value", "Set", AttributeType.SYNTAX, false, "values", "", 0,
				-1, "", "", -1, "", ""));

		infraOpersM2OTRel.addPropVisibleAttribute("00#" + "MetaType");
		// semVertex.addPropEditableAttribute("01#" + "Identifier");
		// semVertex.addPropVisibleAttribute("01#" + "Identifier");
		infraOpersM2OTRel.addPropEditableAttribute("02#" + "Visible");
		infraOpersM2OTRel.addPropVisibleAttribute("02#" + "Visible");
		infraOpersM2OTRel.addPropEditableAttribute("03#" + "Name");
		infraOpersM2OTRel.addPropVisibleAttribute("03#" + "Name");
		infraOpersM2OTRel.addPropEditableAttribute("04#" + "Style");
		infraOpersM2OTRel.addPropVisibleAttribute("04#" + "Style");
		infraOpersM2OTRel.addPropEditableAttribute("05#" + "Description");
		infraOpersM2OTRel.addPropVisibleAttribute("05#" + "Description");
		infraOpersM2OTRel.addPropEditableAttribute("06#" + "Width");
		infraOpersM2OTRel.addPropVisibleAttribute("06#" + "Width");
		infraOpersM2OTRel.addPropEditableAttribute("07#" + "Height");
		infraOpersM2OTRel.addPropVisibleAttribute("07#" + "Height");
		infraOpersM2OTRel.addPropEditableAttribute("08#" + "Image");
		infraOpersM2OTRel.addPropVisibleAttribute("08#" + "Image");
		infraOpersM2OTRel.addPropEditableAttribute("09#" + "TopConcept");
		infraOpersM2OTRel.addPropVisibleAttribute("09#" + "TopConcept");
		infraOpersM2OTRel.addPropEditableAttribute("10#" + "BackgroundColor");
		infraOpersM2OTRel.addPropVisibleAttribute("10#" + "BackgroundColor");
		infraOpersM2OTRel.addPropEditableAttribute("11#" + "BorderStroke");
		infraOpersM2OTRel.addPropVisibleAttribute("11#" + "BorderStroke");
		infraOpersM2OTRel.addPropEditableAttribute("12#" + "Resizable");
		infraOpersM2OTRel.addPropVisibleAttribute("12#" + "Resizable");

		infraOpersM2OTRel.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaConcept Type", ConceptType.class.getCanonicalName(),
				"SyntaxOverTwoRel", 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("OperationsMMType",
				new OpersAttribute("OperationsMMType", "Class",
						AttributeType.OPERATION, false, "Operations MMType",
						OpersConcept.class.getCanonicalName(), "O", null, "",
						0, -1, "", "", -1, "", ""));

		infraOpersM2OTRel.addPropVisibleAttribute("00#" + "MetaType");
		infraOpersM2OTRel.addPropVisibleAttribute("00#" + "OperationsMMType");
		infraOpersM2OTRel.addPropEditableAttribute("00#" + "OperationsMMType");
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);

		infraOpersM2OTRel.addPanelVisibleAttribute("00#" + "OperationsMMType");
		infraOpersM2OTRel
				.addPanelSpacersAttribute("<<MetaOverTwoAsso>>\n{OperType:\"#"
						+ "OperationsMMType" + "#\"}\n");
		// semOverTwoRelation.addPanelVisibleAttribute("01#" + "Name");
		// semOverTwoRelation.addPanelSpacersAttribute("#" + "Name" + "#");

		InstConcept instInfraOpersM2OTRel = new InstConcept(
				"SMMOverTwoRelation", null, infraOpersM2OTRel);

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

		OpersConcept infraOpersM2PWRel = new OpersConcept("SMMPairwiseRelation");

		infraOpersM2PWRel.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false,
				"Meta Association Name", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));

		infraOpersM2PWRel.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaPWAsso Type", ConceptType.class.getCanonicalName(),
				"SyntaxConcept", 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("OperationsMMType",
				new OpersAttribute("OperationsMMType", "Class",
						AttributeType.SYNTAX, false, "Operations MMType",
						OpersConcept.class.getCanonicalName(), "P", null, "",
						0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Identifier",
				new SyntaxAttribute("Identifier", "String",
						AttributeType.SYNTAX, false, "Association Identifier",
						"", 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				true, 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Style", new SyntaxAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Width", new SyntaxAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", 50, 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Height", new SyntaxAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", 50, 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Image", new SyntaxAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		infraOpersM2PWRel.putSemanticAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("value", new SyntaxAttribute(
				"value", "Set", AttributeType.SYNTAX, false, "values", "", 0,
				-1, "", "", -1, "", ""));

		infraOpersM2PWRel.addPropVisibleAttribute("00#" + "MetaType");
		// semPWAsso.addPropEditableAttribute("01#" + "Identifier");
		// semPWAsso.addPropVisibleAttribute("01#" + "Identifier");
		infraOpersM2PWRel.addPropEditableAttribute("02#" + "Visible");
		infraOpersM2PWRel.addPropVisibleAttribute("02#" + "Visible");
		infraOpersM2PWRel.addPropEditableAttribute("03#" + "Name");
		infraOpersM2PWRel.addPropVisibleAttribute("03#" + "Name");
		infraOpersM2PWRel.addPropEditableAttribute("04#" + "Style");
		infraOpersM2PWRel.addPropVisibleAttribute("04#" + "Style");
		infraOpersM2PWRel.addPropEditableAttribute("05#" + "Description");
		infraOpersM2PWRel.addPropVisibleAttribute("05#" + "Description");
		infraOpersM2PWRel.addPropEditableAttribute("06#" + "Width");
		infraOpersM2PWRel.addPropVisibleAttribute("06#" + "Width");
		infraOpersM2PWRel.addPropEditableAttribute("07#" + "Height");
		infraOpersM2PWRel.addPropVisibleAttribute("07#" + "Height");
		infraOpersM2PWRel.addPropEditableAttribute("08#" + "Image");
		infraOpersM2PWRel.addPropVisibleAttribute("08#" + "Image");
		infraOpersM2PWRel.addPropEditableAttribute("10#" + "BackgroundColor");
		infraOpersM2PWRel.addPropVisibleAttribute("10#" + "BackgroundColor");
		infraOpersM2PWRel.addPropEditableAttribute("11#" + "BorderStroke");
		infraOpersM2PWRel.addPropVisibleAttribute("11#" + "BorderStroke");
		infraOpersM2PWRel.addPropEditableAttribute("14#" + "value");
		infraOpersM2PWRel.addPropVisibleAttribute("14#" + "value");

		infraOpersM2PWRel.putSemanticAttribute("OperationsMMType",
				new OpersAttribute("OperationsMMType", "Class",
						AttributeType.OPERATION, false, "Operations MMType",
						OpersConcept.class.getCanonicalName(), "P", null, "",
						0, -1, "", "", -1, "", ""));

		infraOpersM2PWRel.addPropVisibleAttribute("00#" + "OperationsMMType");
		infraOpersM2PWRel.addPropEditableAttribute("00#" + "OperationsMMType");
		infraOpersM2PWRel.addPanelVisibleAttribute("00#" + "OperationsMMType");
		infraOpersM2PWRel
				.addPanelSpacersAttribute("<<MetaPairwiseAsso>>\n{OperType:\"#"
						+ "OperationsMMType" + "#\",\n");
		// semPairwiseRelation.addPanelVisibleAttribute("10#" + "Name");
		// semPairwiseRelation.addPanelSpacersAttribute("#" + "Name" + "#\n\n");

		InstConcept instInfraOpersM2PWRel = new InstConcept(
				"SMMPairwiseRelation", null, infraOpersM2PWRel);

		// semElement.addPanelVisibleAttribute("01#" + "Name");
		// semElement.addPanelSpacersAttribute("#" + "Name" + "#");

		OpersConcept infraOpersM2ExtendsRelation = new OpersConcept(
				"SMMExtendRelation");

		infraOpersM2ExtendsRelation.putSemanticAttribute("MetaType",
				new SyntaxAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "MetaConcept Type",
						ConceptType.class.getCanonicalName(), "SyntaxEnum", 0,
						-1, "", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2ExtendsRelation.putSemanticAttribute("Visible",
				new SyntaxAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", true, 0, -1, "", "", -1, "", ""));
		infraOpersM2ExtendsRelation.putSemanticAttribute("Name",
				new SyntaxAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2ExtendsRelation.putSemanticAttribute("value",
				new SyntaxAttribute("value", "Set", AttributeType.SYNTAX,
						false, "values", "", 0, -1, "", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("dummy", new SyntaxAttribute(
		// "dummy", "String", AttributeType.SYNTAX, false, "dummy", "", 0,
		// -1, "", "", -1, "", ""));

		infraOpersM2ExtendsRelation.addPropVisibleAttribute("00#" + "MetaType");
		// semElementNoSyntax.addPropEditableAttribute("01#" + "Identifier");
		// semElementNoSyntax.addPropVisibleAttribute("01#" + "Identifier");
		infraOpersM2ExtendsRelation.addPropEditableAttribute("02#" + "Visible");
		infraOpersM2ExtendsRelation.addPropVisibleAttribute("02#" + "Visible");
		infraOpersM2ExtendsRelation.addPropEditableAttribute("03#" + "Name");
		infraOpersM2ExtendsRelation.addPropVisibleAttribute("03#" + "Name");
		infraOpersM2ExtendsRelation.addPropEditableAttribute("06#" + "value");
		infraOpersM2ExtendsRelation.addPropVisibleAttribute("06#" + "value");
		infraOpersM2ExtendsRelation.addPanelSpacersAttribute("#" + "value"
				+ "#\n\n");

		InstConcept instInfraOpersM2ExtendsRelation = new InstConcept(
				"SMMExtendRelation", null, infraOpersM2ExtendsRelation);

		OpersConcept infraOpersM2ViewConceptAsso = new OpersConcept(
				"SMMViewConceptAsso");

		infraOpersM2ViewConceptAsso.putSemanticAttribute("MetaType",
				new SyntaxAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "MetaConcept Type",
						ConceptType.class.getCanonicalName(), "SyntaxEnum", 0,
						-1, "", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2ViewConceptAsso.putSemanticAttribute("Visible",
				new SyntaxAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", true, 0, -1, "", "", -1, "", ""));
		infraOpersM2ViewConceptAsso.putSemanticAttribute("Name",
				new SyntaxAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2ViewConceptAsso.putSemanticAttribute("value",
				new SyntaxAttribute("value", "Set", AttributeType.SYNTAX,
						false, "values", "", 0, -1, "", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("dummy", new SyntaxAttribute(
		// "dummy", "String", AttributeType.SYNTAX, false, "dummy", "", 0,
		// -1, "", "", -1, "", ""));

		infraOpersM2ViewConceptAsso.addPropVisibleAttribute("00#" + "MetaType");
		// semElementNoSyntax.addPropEditableAttribute("01#" + "Identifier");
		// semElementNoSyntax.addPropVisibleAttribute("01#" + "Identifier");
		infraOpersM2ViewConceptAsso.addPropEditableAttribute("02#" + "Visible");
		infraOpersM2ViewConceptAsso.addPropVisibleAttribute("02#" + "Visible");
		infraOpersM2ViewConceptAsso.addPropEditableAttribute("03#" + "Name");
		infraOpersM2ViewConceptAsso.addPropVisibleAttribute("03#" + "Name");
		infraOpersM2ViewConceptAsso.addPropEditableAttribute("06#" + "value");
		infraOpersM2ViewConceptAsso.addPropVisibleAttribute("06#" + "value");
		infraOpersM2ViewConceptAsso.addPanelSpacersAttribute("#" + "value"
				+ "#\n\n");

		InstConcept instInfraOpersM2ViewConceptAsso = new InstConcept(
				"ExtendRelation", null, infraOpersM2ViewConceptAsso);

		// End Opers M2 Model

		// Begin Syntax M2 Model

		SyntaxConcept infraSyntaxM2View = new SyntaxConcept('V', "SMMView",
				true, true, "SMMView", "infrasyntaxm2view",
				"MM View/MM SubView Concept", 100, 30,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.WHITE.toString(), 3, null, true);

		infraSyntaxM2View.addModelingAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaConcept Type", ConceptType.class.getCanonicalName(),
				"SyntaxView", 0, -1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Index", new SyntaxAttribute(
				"Index", "Integer", AttributeType.SYNTAX, false, "View Index",
				3, 0, -1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Identifier",
				new SyntaxAttribute("Identifier", "String",
						AttributeType.SYNTAX, false, "View Identifier", "", 0,
						-1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				true, 0, -1, "", "", -1, "", ""));
		// semView.putSemanticAttribute("Parent", new SyntaxAttribute("Parent",
		// "String", AttributeType.SYNTAX, false, "Parent View", "", 0,
		// -1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", 0, -1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Style", new SyntaxAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "refasclaim", 0, -1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Width", new SyntaxAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", 100, 0, -1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Height", new SyntaxAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", 40, 0, -1, "", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("Image", new SyntaxAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		infraSyntaxM2View.addModelingAttribute("PaletteNames",
				new SyntaxAttribute("PaletteNames", "String",
						AttributeType.SYNTAX, false, "Palette Name", "", 0, -1,
						"", "", -1, "", ""));

		infraSyntaxM2View.addPropEditableAttribute("03#" + "PaletteNames");
		infraSyntaxM2View.addPropVisibleAttribute("03#" + "PaletteNames");
		infraSyntaxM2View.addPanelVisibleAttribute("05#" + "PaletteNames" + "#"
				+ "PaletteNames" + "#!=#" + "" + "#" + "");
		infraSyntaxM2View.addPanelSpacersAttribute("{Palettes:#"
				+ "PaletteNames" + "#}\n\n");
		infraSyntaxM2View.addPropVisibleAttribute("00#" + "MetaType");
		// semView.addPropEditableAttribute("01#" + "Identifier");
		// semView.addPropVisibleAttribute("01#" + "Identifier");
		infraSyntaxM2View.addPropEditableAttribute("02#" + "Index");
		infraSyntaxM2View.addPropVisibleAttribute("02#" + "Index");
		infraSyntaxM2View.addPropEditableAttribute("03#" + "Visible");
		infraSyntaxM2View.addPropVisibleAttribute("03#" + "Visible");
		// semView.addPropEditableAttribute("04#" + "Parent");
		// semView.addPropVisibleAttribute("04#" + "Parent");
		infraSyntaxM2View.addPropEditableAttribute("05#" + "Name");
		infraSyntaxM2View.addPropVisibleAttribute("05#" + "Name");
		infraSyntaxM2View.addPropEditableAttribute("06#" + "Style");
		infraSyntaxM2View.addPropVisibleAttribute("06#" + "Style");
		infraSyntaxM2View.addPropEditableAttribute("07#" + "Description");
		infraSyntaxM2View.addPropVisibleAttribute("07#" + "Description");
		infraSyntaxM2View.addPropEditableAttribute("08#" + "Width");
		infraSyntaxM2View.addPropVisibleAttribute("08#" + "Width");
		infraSyntaxM2View.addPropEditableAttribute("09#" + "Height");
		infraSyntaxM2View.addPropVisibleAttribute("09#" + "Height");
		infraSyntaxM2View.addPropEditableAttribute("10#" + "Image");
		infraSyntaxM2View.addPropVisibleAttribute("10#" + "Image");
		// semView.addDisPropEditableAttribute("11#" + "BorderStroke");
		infraSyntaxM2View.addPropVisibleAttribute("11#" + "BorderStroke");

		infraSyntaxM2View.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		infraSyntaxM2View.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxM2View = new InstConcept("SMMView",
				infraBasicSyntaxOpersM3Concept, infraSyntaxM2View);
		variabilityInstVertex.put("SMMView", instInfraSyntaxM2View);

		SyntaxConcept infraSyntaxM2OTRel = new SyntaxConcept('O',
				"SMMOverTwoRelation", true, true, "SMMOverTwoRelation",
				"infrasyntaxm2miniconcept", "SyntaxOverTwoRel", 180, 70,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instInfraOpersM2OTRel, true);

		infraSyntaxM2OTRel.addModelingAttribute("Type", new SyntaxAttribute(
				"Type", "String", AttributeType.SYNTAX, false, "Relation Type",
				"", 0, -1, "", "", -1, "", ""));

		// overTwoRelation.addPropVisibleAttribute("03#" + "Type");
		// overTwoRelation.addPropEditableAttribute("03#" + "Type");
		// overTwoRelation.addPanelVisibleAttribute("03#" + "Type" + "#" +
		// "Type"
		// + "#!=#" + "" + "#" + "");
		// overTwoRelation.addPanelSpacersAttribute("\n{#" + "Type" + "#}");

		InstConcept instInfraSyntaxM2OTRel = new InstConcept(
				"SMMOverTwoRelation", infraBasicSyntaxOpersM3Concept,
				infraSyntaxM2OTRel);
		variabilityInstVertex.put("SMMOverTwoRelation", instInfraSyntaxM2OTRel);

		SyntaxPairwiseRel infraSyntaxM2NormalRelation = new SyntaxPairwiseRel(
				"SMMNormalRelation", false, true, "Normal Relation",
				"defaultAsso", "View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		constraintInstEdges.put("SMMNormalRelation", new InstPairwiseRel(
				"SMMNormalRelation", infraSyntaxM2NormalRelation));

		SyntaxConcept infraSyntaxM2ExtendsRelation = new SyntaxConcept('X',
				"SMMExtendRelation", true, true, "SMMExtendRelation",
				"infrasyntaxm2miniconcept", "Extend relation", 150, 70,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instInfraOpersM2ExtendsRelation, true);
		infraSyntaxM2ExtendsRelation.addPanelVisibleAttribute("01#Name");
		infraSyntaxM2ExtendsRelation
				.addPanelSpacersAttribute("<<MetaExtendsAsso>>\n#Name#\n\n");

		InstConcept instInfraSyntaxM2ExtendsRelation = new InstConcept(
				"SMMExtendRelation", infraBasicSyntaxOpersM3Concept,
				infraSyntaxM2ExtendsRelation);

		variabilityInstVertex.put("SMMExtendRelation",
				instInfraSyntaxM2ExtendsRelation);

		InstPairwiseRel instEdge = new InstPairwiseRel();
		constraintInstEdges.put("ce-e-c", instEdge);
		instEdge.setIdentifier("ce-e-c");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ExtendsRelation, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("ce-c-e", instEdge);
		instEdge.setIdentifier("ce-c-e");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2ExtendsRelation, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Concept, true);

		SyntaxPairwiseRel metaPairwiseRelExtends = new SyntaxPairwiseRel(
				"ExtendsRelation", false, true, "Extends Relation",
				"refasextends", "Extends relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		constraintInstEdges.put("ExtendsRelation", new InstPairwiseRel(
				"ExtendsRelation", metaPairwiseRelExtends));

		SyntaxConcept infraSyntaxM2ViewConceptAsso = new SyntaxConcept('I',
				"SMMViewConceptAsso", true, true, "SMMViewConceptAsso",
				"infrasyntaxm2miniconcept", "View-Concept Association", 150,
				70, "/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instInfraOpersM2ViewConceptAsso, true);
		infraSyntaxM2ViewConceptAsso.addPanelVisibleAttribute("01#dummy");
		infraSyntaxM2ViewConceptAsso
				.addPanelSpacersAttribute("<<MetaViewConceptAsso>>#dummy#\n");
		infraSyntaxM2ViewConceptAsso.addModelingAttribute("Palette",
				new SyntaxAttribute("Palette", "String", AttributeType.SYNTAX,
						false, "Palette Name", "", 0, -1, "", "", -1, "", ""));

		infraSyntaxM2ViewConceptAsso
				.addPropEditableAttribute("02#" + "Palette");
		infraSyntaxM2ViewConceptAsso.addPropVisibleAttribute("02#" + "Palette");
		infraSyntaxM2ViewConceptAsso.addPanelVisibleAttribute("02#" + "Palette"
				+ "#" + "Palette" + "#!=#" + "" + "#" + "");
		infraSyntaxM2ViewConceptAsso.addPanelSpacersAttribute("{Palette:#"
				+ "Palette" + "#}\n");

		InstConcept instInfraSyntaxM2ViewConceptAsso = new InstConcept(
				"SMMViewConceptAsso", infraBasicSyntaxOpersM3Concept,
				infraSyntaxM2ViewConceptAsso);
		variabilityInstVertex.put("SMMViewConceptAsso",
				instInfraSyntaxM2ViewConceptAsso);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-v-vc", instEdge);
		instEdge.setIdentifier("vc-v-vc");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2ViewConceptAsso, true);
		instEdge.setSourceRelation(instInfraSyntaxM2View, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-vc-c", instEdge);
		instEdge.setIdentifier("vc-vc-c");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ViewConceptAsso, true);

		// TODO remove if Claims and SDs are Concepts
		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("vc-vc-otr", instEdge);
		instEdge.setIdentifier("vc-vc-otr");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2OTRel, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ViewConceptAsso, true);

		SyntaxPairwiseRel metaPairwiseRelFromView = new SyntaxPairwiseRel(
				"ViewRelation", false, true, "View Relation",
				"infrasyntaxm2viewrel", "View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		InstPairwiseRel instViewRelation = new InstPairwiseRel("ViewRelation",
				metaPairwiseRelFromView);

		constraintInstEdges.put("ViewRelation", instViewRelation);

		SyntaxConcept infraSyntaxM2PWRel = new SyntaxConcept('P',
				"SMMPairwiseRelation", true, true, "SMMPairwiseRelation",
				"infrasyntaxm2miniconcept", "SyntaxPairwiseRel", 150, 200,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instInfraOpersM2PWRel, true);

		infraSyntaxM2PWRel.addModelingAttribute("Type", new SyntaxAttribute(
				"Type", "String", AttributeType.SYNTAX, false, "Relation Type",
				"", 0, -1, "", "", -1, "", ""));

		// pairwiseRelation.addPropEditableAttribute("03#" + "Type");
		// pairwiseRelation.addPropVisibleAttribute("03#" + "Type");
		// pairwiseRelation.addPanelVisibleAttribute("03#" + "Type" + "#" +
		// "Type"
		// + "#!=#" + "" + "#" + "");
		// pairwiseRelation.addPanelSpacersAttribute("\n{#" + "Type" + "#}");

		infraSyntaxM2PWRel.addModelingAttribute("SourceCardinality",
				new SyntaxAttribute("SourceCardinality", "String",
						AttributeType.SYNTAX, false, "Source Cardinality",
						"String", "[]", 0, -1, "", "", -1, "", ""));

		infraSyntaxM2PWRel
				.addPropEditableAttribute("04#" + "SourceCardinality");
		infraSyntaxM2PWRel.addPropVisibleAttribute("04#" + "SourceCardinality");
		infraSyntaxM2PWRel.addPanelVisibleAttribute("01#" + "SourceCardinality"
				+ "#" + "Type" + "#!=#" + "" + "#" + "");
		infraSyntaxM2PWRel.addPanelSpacersAttribute("SourCard:#"
				+ "SourceCardinality" + "#,");

		infraSyntaxM2PWRel.addModelingAttribute("TargetCardinality",
				new SyntaxAttribute("TargetCardinality", "String",
						AttributeType.SYNTAX, false, "Target Cardinality",
						"String", "[]", 0, 5, "", "", 5, "TargCard:#-#}\n",
						"Type#!=##"));

		infraSyntaxM2PWRel
				.addPropEditableAttribute("05#" + "TargetCardinality");
		infraSyntaxM2PWRel.addPropVisibleAttribute("05#" + "TargetCardinality");
		infraSyntaxM2PWRel.addPanelVisibleAttribute("02#" + "TargetCardinality"
				+ "#" + "Type" + "#!=#" + "" + "#" + "");
		infraSyntaxM2PWRel.addPanelSpacersAttribute("TargCard:#"
				+ "TargetCardinality" + "#}\n");

		InstConcept instInfraSyntaxM2PWRel = new InstConcept(
				"SMMPairwiseRelation", infraBasicSyntaxOpersM3Concept,
				infraSyntaxM2PWRel);
		variabilityInstVertex
				.put("SMMPairwiseRelation", instInfraSyntaxM2PWRel);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("cpw-pw-c", instEdge);
		instEdge.setIdentifier("cpw-pw-c");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		instEdge.setSourceRelation(instInfraSyntaxM2PWRel, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("cpw-c-pw", instEdge);
		instEdge.setIdentifier("cpw-c-pw");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2PWRel, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Concept, true);

		// TODO remove if Claims and SDs are Concepts
		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("cpw-pw-otr", instEdge);
		instEdge.setIdentifier("cpw-pw-otr");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2OTRel, true);
		instEdge.setSourceRelation(instInfraSyntaxM2PWRel, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("cpw-otr-pw", instEdge);
		instEdge.setIdentifier("cpw-otr-pw");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2PWRel, true);
		instEdge.setSourceRelation(instInfraSyntaxM2OTRel, true);
	}
}
