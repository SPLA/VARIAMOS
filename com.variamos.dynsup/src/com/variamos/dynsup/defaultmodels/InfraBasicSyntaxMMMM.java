package com.variamos.dynsup.defaultmodels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.StringType;

public class InfraBasicSyntaxMMMM {
	// FIXME v1.1 update all the method
	public static void createBasicSyntaxMetaMetaMetaModel(
			InstanceModel modelInstance) {

		Map<String, InstElement> variabilityInstVertex = modelInstance
				.getVariabilityVertex();

		Map<String, InstPairwiseRel> constraintInstEdges = modelInstance
				.getConstraintInstEdges();

		SyntaxElement basicOpersSyntaxM3Concept = new SyntaxElement('C',
				"BsConcept", true, true, "BsConcept",
				"infrasyntaxm2bigconcept", "Basic Meta Meta Meta Concept", 120,
				120, "/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Concept.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 2, "", "", 1, "<<BsConcept>>\n#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		basicOpersSyntaxM3Concept
				.addModelingAttribute("Name", new ElemAttribute("Name",
						"String", AttributeType.SYNTAX, false, "Concept Name",
						"", "", 0, 3, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("description",
				new ElemAttribute("description", "String",
						AttributeType.SYNTAX, false, "Description", "", "", 0,
						4, "", "", -1, "", ""));

		basicOpersSyntaxM3Concept.addModelingAttribute("Visible",
				new ElemAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", "", true, 0, 5, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Editable",
				new ElemAttribute("Editable", "Boolean", AttributeType.SYNTAX,
						false, "Editable", "", true, 0, 6, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept
				.addModelingAttribute("Name", new ElemAttribute("Name",
						"String", AttributeType.SYNTAX, false, "Concept Name",
						"", "", 0, 7, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Style",
				new ElemAttribute("Style", "String", AttributeType.SYNTAX,
						false, "Drawing Style", "", "refasclaim", 0, 8, "", "",
						-1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Width",
				new ElemAttribute("Width", "Integer", AttributeType.SYNTAX,
						false, "Initial Width", "", 100, 0, 9, "", "", -1, "",
						""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Height",
				new ElemAttribute("Height", "Integer", AttributeType.SYNTAX,
						false, "Initial Height", "", 40, 0, 10, "", "", -1, "",
						""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Image",
				new ElemAttribute("Image", "String", AttributeType.SYNTAX,
						false, "Image File", "",
						"/com/variamos/gui/perspeditor/images/claim.png", 0,
						11, "", "", -1, "", ""));

		basicOpersSyntaxM3Concept.addModelingAttribute("BackgroundColor",
				new ElemAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color", "",
						"java.awt.Color[r=0,g=0,b=255]", 0, 12, "", "", -1, "",
						""));
		basicOpersSyntaxM3Concept.addModelingAttribute("BorderStroke",
				new ElemAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", "", 13,
						0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Resizable",
				new ElemAttribute("Resizable", "Boolean", AttributeType.SYNTAX,
						false, "Is Resizable", "", true, 0, 14, "", "", -1, "",
						""));

		InstConcept instInfraSyntaxOpersM3Concept = new InstConcept(
				"BsConcept", null, basicOpersSyntaxM3Concept);
		instInfraSyntaxOpersM3Concept
				.setTransSupInstElement(instInfraSyntaxOpersM3Concept);
		instInfraSyntaxOpersM3Concept.createInstAttributes(null);
		instInfraSyntaxOpersM3Concept.copyValuesToInstAttributes(null);
		variabilityInstVertex.put("BsConcept", instInfraSyntaxOpersM3Concept);

		SyntaxElement basicOpersSyntaxM3OperConcept = new SyntaxElement('C',
				"BsOperConcept", true, true, "BsOperConcept",
				"infrasyntaxm2bigconcept", "Operations Meta Meta Meta Concept",
				120, 120, "/com/variamos/gui/perspeditor/images/concept.png",
				true, Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3OperConcept.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 2, "", "", 1,
				"<<BsOperConcept>>\n#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n\n", "");

		basicOpersSyntaxM3OperConcept
				.addModelingAttribute("Name", new ElemAttribute("Name",
						"String", AttributeType.SYNTAX, false, "Concept Name",
						"", "", 0, 3, "", "", -1, "", ""));
		basicOpersSyntaxM3OperConcept.addModelingAttribute("description",
				new ElemAttribute("description", "String",
						AttributeType.SYNTAX, false, "Description", "", "", 0,
						4, "", "", -1, "", ""));

		basicOpersSyntaxM3OperConcept.addModelingAttribute("Style",
				new ElemAttribute("Style", "String", AttributeType.SYNTAX,
						false, "Drawing Style", "", "refasclaim", 0, 8, "", "",
						-1, "", ""));
		basicOpersSyntaxM3OperConcept.addModelingAttribute("Width",
				new ElemAttribute("Width", "Integer", AttributeType.SYNTAX,
						false, "Initial Width", "", 100, 0, 9, "", "", -1, "",
						""));
		basicOpersSyntaxM3OperConcept.addModelingAttribute("Height",
				new ElemAttribute("Height", "Integer", AttributeType.SYNTAX,
						false, "Initial Height", "", 40, 0, 10, "", "", -1, "",
						""));
		basicOpersSyntaxM3OperConcept.addModelingAttribute("Image",
				new ElemAttribute("Image", "String", AttributeType.SYNTAX,
						false, "Image File", "",
						"/com/variamos/gui/perspeditor/images/claim.png", 0,
						11, "", "", -1, "", ""));

		basicOpersSyntaxM3OperConcept.addModelingAttribute("BackgroundColor",
				new ElemAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color", "",
						"java.awt.Color[r=0,g=0,b=255]", 0, 12, "", "", -1, "",
						""));
		basicOpersSyntaxM3OperConcept.addModelingAttribute("BorderStroke",
				new ElemAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", "", 13,
						0, -1, "", "", -1, "", ""));

		InstConcept instInfraSyntaxOpersM3OperConcept = new InstConcept(
				"BsOperConcept", null, basicOpersSyntaxM3OperConcept);
		instInfraSyntaxOpersM3OperConcept
				.setTransSupInstElement(instInfraSyntaxOpersM3Concept);
		instInfraSyntaxOpersM3OperConcept.createInstAttributes(null);
		instInfraSyntaxOpersM3OperConcept.copyValuesToInstAttributes(null);
		variabilityInstVertex.put("BsOperConcept",
				instInfraSyntaxOpersM3OperConcept);

		// Currently not used but required to move types from
		// com.variamos.dynsup.types to dynamic definitions
		SyntaxElement basicOpersSyntaxM3Enum = new SyntaxElement('E', "BsEnum",
				true, true, "BsEnum", "infrasyntaxm2miniconcept",
				"Operations Meta Meta Meta Enumeration", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Enum.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 1, "", "", 4, "<<BsEnum>>\n#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n\n", "");

		basicOpersSyntaxM3Enum.addModelingAttribute("enumValues", "Set", false,
				"att", "", SyntaxElement.VAR_METAENUMVALUECLASS, "Enumeration",
				new ArrayList<InstAttribute>(), 0, 1, "", "", 5, "#"
						+ "enumValues" + "#all#\n", "");

		basicOpersSyntaxM3Enum.addModelingAttribute("Name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", "", 0, 2, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("description",
				new ElemAttribute("description", "String",
						AttributeType.SYNTAX, false, "Description", "", "", 0,
						3, "", "", -1, "", ""));

		basicOpersSyntaxM3Enum.addModelingAttribute("Style", new ElemAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "", "refasclaim", 0, 8, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("Width", new ElemAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", "", 100, 0, 9, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("Height",
				new ElemAttribute("Height", "Integer", AttributeType.SYNTAX,
						false, "Initial Height", "", 40, 0, 10, "", "", -1, "",
						""));
		basicOpersSyntaxM3Enum.addModelingAttribute("Image", new ElemAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"", "/com/variamos/gui/perspeditor/images/claim.png", 0, 11,
				"", "", -1, "", ""));

		basicOpersSyntaxM3Enum.addModelingAttribute("BackgroundColor",
				new ElemAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color", "",
						"java.awt.Color[r=0,g=0,b=255]", 0, 12, "", "", -1, "",
						""));
		basicOpersSyntaxM3Enum.addModelingAttribute("BorderStroke",
				new ElemAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", "", 1, 0,
						13, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("Resizable",
				new ElemAttribute("Resizable", "Boolean", AttributeType.SYNTAX,
						false, "Is Resizable", "", true, 0, 14, "", "", -1, "",
						""));

		InstConcept instInfraSyntaxOpersM3Enum = new InstConcept("BsEnum",
				instInfraSyntaxOpersM3Concept, basicOpersSyntaxM3Enum);
		variabilityInstVertex.put("BsEnum", instInfraSyntaxOpersM3Enum);

		// Association

		SyntaxElement infraSyntaxM3Asso = new SyntaxElement('P', "BsAso",
				false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsAso", new InstPairwiseRel("BsAso",
				infraSyntaxM3Asso));

		InstPairwiseRel instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-a-co", instEdge);
		instEdge.setIdentifier("co-a-co");
		instEdge.setEdSyntaxEle(infraSyntaxM3Asso);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Concept, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Concept, true);

		// Node Attributes

		SyntaxElement basicOpersSyntaxM3Attribute = new SyntaxElement('A',
				"BsAttribute", false, true, "BsAttribute",
				"infrasyntaxm2bigconcept", "Attributes for the meta-concept",
				120, 120, "/com/variamos/gui/perspeditor/images/concept.png",
				true, Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Attribute.addModelingAttribute("Name",
				new ElemAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", "InstAttribute", 0, 1, "",
						"", 1, "", ""));

		basicOpersSyntaxM3Attribute.addModelingAttribute("type", "String",
				false, "Type", "", "", 0, 2, "", "", 2, "#" + "type"
						+ "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("attributeType",
				"String", false, "Attribute Type", "", "", 0, 3, "", "", -1,
				"#" + "attributeType" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("affectProps",
				"Boolean", false, "Affect Properties", "", "", 0, 4, "", "",
				-1, "#" + "affectProps" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("dispName", "String",
				false, "Display Name", "", "", 0, 5, "", "", -1, "#"
						+ "dispName" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("toolTipText",
				"String", false, "Tool Tip Text", "", "", 0, -1, "", "", -1,
				"#" + "toolTipText" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("classCanName",
				"String", false, "Enumeration Type", "", "", 0, 7, "", "", -1,
				"#" + "classCanName" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("metaCInstType",
				"String", false, "Instance Type", "", "", 0, 8, "", "", -1, "#"
						+ "metaCInstType" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("defaultValue",
				"String", false, "Default Value", "", "", 0, 9, "", "", -1, "#"
						+ "defaultValue" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("domain", "String",
				false, "Domain", "", "", 0, 10, "", "", -1, "#" + "domain"
						+ "#all#\n\n", "");
		basicOpersSyntaxM3Attribute.addModelingAttribute("hint", "String",
				false, "Hint", "", "", 0, 11, "", "", -1, "#" + "hint"
						+ "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("propTabPosition",
				"Integer", false, "Prop. Tab Position", "", "", 0, 12, "", "",
				-1, "#" + "propTabPosition" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute(
				"elementDisplayPosition", "Integer", false,
				"Element Disp. Position", "", "", 0, 13, "", "", -1, "#"
						+ "elementDisplayPosition" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute(
				"elementDisplaySpacers", "String", false,
				"Element Disp. Spacers", "", "", 0, 14, "", "", -1, "#"
						+ "elementDisplaySpacers" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute(
				"propTabEditionCondition", "String", false,
				"Prop. Tab Edition Cond.", "", "", 0, 15, "", "", -1, "#"
						+ "propTabEditionCondition" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute(
				"propTabVisualCondition", "String", false,
				"Prop. Tab Visual Cond.", "", "", 0, 16, "", "", -1, "#"
						+ "propTabVisualCondition" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute(
				"elementDisplayCondition", "String", false,
				"Graph Visual Cond.", "", "", 0, 17, "", "", -1, "#"
						+ "elementDisplayCondition" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("domFiltOwnFields",
				"String", false, "Filter domain (Own Fields)", "", "", 0, 18,
				"", "", -1, "#" + "domFiltOwnFields" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("domFilTRelFields",
				"String", false, "Filter domain (Rel. Fields)", "", "", 0, 19,
				"", "", -1, "#" + "domFilTRelFields" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("defDomValueField",
				"String", false, "Def. Domain (Filter Field)", "", "", 0, 20,
				"", "", -1, "#" + "defDomValueField" + "#all#\n\n", "");

		InstConcept instInfraSyntaxOpersM3Attribute = new InstConcept(
				"BsAttribute", instInfraSyntaxOpersM3Concept,
				basicOpersSyntaxM3Attribute);
		variabilityInstVertex.put("BsAttribute",
				instInfraSyntaxOpersM3Attribute);

		SyntaxElement infraSyntaxM3ConAtt = new SyntaxElement('P', "BsConAtt",
				false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsConAtt", new InstPairwiseRel("BsConAtt",
				infraSyntaxM3ConAtt));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-att", instEdge);
		instEdge.setIdentifier("co-att");
		instEdge.setEdSyntaxEle(infraSyntaxM3ConAtt);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Attribute, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Concept, true);

		// Enums Instances

		SyntaxElement basicOpersSyntaxM3TypesEnum = new SyntaxElement('E',
				"TypesEnum", false, true, "TypesEnum", "infrasyntaxm2concept",
				"Enum", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instInfraSyntaxOpersM3TypesEnum = new InstConcept(
				"TypesEnum", instInfraSyntaxOpersM3Enum,
				basicOpersSyntaxM3TypesEnum);

		ArrayList<InstAttribute> enumVals = new ArrayList<InstAttribute>();
		instInfraSyntaxOpersM3TypesEnum.getInstAttribute("enumValues")
				.setValue(enumVals);
		enumVals.add(new InstAttribute("enum1", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Integer"));
		enumVals.add(new InstAttribute("enum2", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"String"));
		enumVals.add(new InstAttribute("enum3", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Boolean"));
		enumVals.add(new InstAttribute("enum4", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Float"));
		enumVals.add(new InstAttribute("enum5", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Enumeration"));
		enumVals.add(new InstAttribute("enum6", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"MetaEnumeration"));
		enumVals.add(new InstAttribute("enum7", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Class"));
		enumVals.add(new InstAttribute("enum8", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Class"));
		enumVals.add(new InstAttribute("enum8", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""), "Set"));

		variabilityInstVertex.put("TypesEnum", instInfraSyntaxOpersM3TypesEnum);

		SyntaxElement basicOpersSyntaxM3DispTypesEnum = new SyntaxElement('E',
				"DisplayTypesEnum", false, true, "DispplayTypesEnum",
				"infrasyntaxm2concept", "Enum", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instInfraSyntaxOpersM3DispTypesEnum = new InstConcept(
				"DisplayTypesEnum", instInfraSyntaxOpersM3Enum,
				basicOpersSyntaxM3DispTypesEnum);

		enumVals = new ArrayList<InstAttribute>();
		instInfraSyntaxOpersM3DispTypesEnum.getInstAttribute("enumValues")
				.setValue(enumVals);
		enumVals.add(new InstAttribute("enum1", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Model_Definition"));
		enumVals.add(new InstAttribute("enum2", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Model_Status"));
		enumVals.add(new InstAttribute("enum3", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Config_Definition"));
		enumVals.add(new InstAttribute("enum4", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Config_Status"));
		enumVals.add(new InstAttribute("enum5", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Both_Status"));

		variabilityInstVertex.put("DisplayTypesEnum",
				instInfraSyntaxOpersM3DispTypesEnum);

	}
}
