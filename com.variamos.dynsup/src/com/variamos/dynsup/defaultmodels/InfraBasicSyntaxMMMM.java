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
				"BsNode", true, true, "BsNode", "infrasyntaxm2bigconcept",
				"Basic Meta Meta Meta Node", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Concept.addModelingAttribute("abstract",
				new ElemAttribute("abstract", "Boolean", AttributeType.SYNTAX,
						false, "Abstract Node", "", false, 0, 3, "", "", -1,
						"", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 2, "", "", 1, "<<BsNode>>\n#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		basicOpersSyntaxM3Concept.addModelingAttribute("nodeType",
				new ElemAttribute("nodeType", "Enumeration",
						AttributeType.SYNTAX, false, "Node Type", "",
						"NodeTypeEnum", "", "", 0, 3, "", "", -1, "", ""));
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

		InstConcept instInfraSyntaxOpersM3Concept = new InstConcept("BsNode",
				null, basicOpersSyntaxM3Concept);
		instInfraSyntaxOpersM3Concept
				.setTransSupInstElement(instInfraSyntaxOpersM3Concept);
		instInfraSyntaxOpersM3Concept.createInstAttributes(null);
		instInfraSyntaxOpersM3Concept.copyValuesToInstAttributes(null);
		variabilityInstVertex.put("BsNode", instInfraSyntaxOpersM3Concept);

		SyntaxElement basicOpersSyntaxM3Classifier = new SyntaxElement('C',
				"BsClassifier", false, true, "BsClassifier",
				"infrasyntaxm2bigconcept", "Meta Meta Meta Element", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Classifier.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 2, "", "", 1,
				"<<BsOperElement>>\n#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n\n", "");

		InstConcept instInfraSyntaxOpersM3Classifier = new InstConcept(
				"BsClassifier", instInfraSyntaxOpersM3Concept,
				basicOpersSyntaxM3Classifier);

		instInfraSyntaxOpersM3Classifier.createInstAttributes(null);
		instInfraSyntaxOpersM3Classifier.copyValuesToInstAttributes(null);
		variabilityInstVertex.put("BsClassifier",
				instInfraSyntaxOpersM3Classifier);

		SyntaxElement basicOpersSyntaxM3Named = new SyntaxElement('C',
				"BsNamedElement", false, true, "BsNamedElement",
				"infrasyntaxm2bigconcept", "Meta Meta Meta Element", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Named.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 2, "", "", 1,
				"<<BsNamedElement>>\n#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n\n", "");

		basicOpersSyntaxM3Named.addModelingAttribute("name", new ElemAttribute(
				"name", "String", AttributeType.SYNTAX, false, "Element Name",
				"", "", 0, 3, "", "", -1, "", ""));

		basicOpersSyntaxM3Named.addModelingAttribute("displayName",
				new ElemAttribute("displayName", "String",
						AttributeType.SYNTAX, false, "Concept Display Name",
						"", "", 0, 3, "", "", -1, "", ""));

		InstConcept instInfraSyntaxOpersM3NamedElement = new InstConcept(
				"BsNamedElement", instInfraSyntaxOpersM3Concept,
				basicOpersSyntaxM3Named);

		instInfraSyntaxOpersM3Classifier.createInstAttributes(null);
		instInfraSyntaxOpersM3Classifier.copyValuesToInstAttributes(null);
		variabilityInstVertex.put("BsNamedElement",
				instInfraSyntaxOpersM3NamedElement);

		SyntaxElement basicOpersSyntaxM3Set = new SyntaxElement('C',
				"BsCollection", true, true, "BsCollection",
				"infrasyntaxm2bigconcept", "Meta Meta Meta Element", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Set.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 2, "", "", 1,
				"<<BsCollection>>\n#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n\n", "");

		// FIXME include a boolean to identify in the set should be treated as a
		// list of values when instantiated, if not an attribute should be
		// assumed

		InstConcept instInfraSyntaxOpersM3Set = new InstConcept("BsCollection",
				instInfraSyntaxOpersM3Concept, basicOpersSyntaxM3Set);
		instInfraSyntaxOpersM3Set.createInstAttributes(null);
		instInfraSyntaxOpersM3Set.copyValuesToInstAttributes(null);
		variabilityInstVertex.put("BsCollection", instInfraSyntaxOpersM3Set);

		// FIXME for concrete syntax
		SyntaxElement basicOpersSyntaxM3Tree = new SyntaxElement('C', "BsTree",
				true, true, "BsTree", "infrasyntaxm2bigconcept",
				"Meta Meta Meta Element", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Tree.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 2, "", "", 1, "<<BsTree>>\n#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		basicOpersSyntaxM3Tree.addModelingAttribute("selectConcept", "Boolean",
				false, "Select the Concepts", "", false, 0, 2, "", "", -1, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#", "");
		// Select concepts instead of attributes

		basicOpersSyntaxM3Tree.addModelingAttribute("attributesToEdit",
				"String", false, "User Identifier", "", "", 0, 2, "", "", -1,
				"#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#", "");
		// attributes to edit separated by semicolon

		InstConcept instInfraSyntaxOpersM3Tree = new InstConcept("BsTree",
				instInfraSyntaxOpersM3Concept, basicOpersSyntaxM3Tree);
		instInfraSyntaxOpersM3Set.createInstAttributes(null);
		instInfraSyntaxOpersM3Set.copyValuesToInstAttributes(null);
		// variabilityInstVertex.put("BsTree", instInfraSyntaxOpersM3Tree);

		// BsEnum is currently not used but is required to move types from
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

		InstConcept instInfraSyntaxOpersM3Enum = new InstConcept("BsEnum",
				instInfraSyntaxOpersM3Concept, basicOpersSyntaxM3Enum);
		variabilityInstVertex.put("BsEnum", instInfraSyntaxOpersM3Enum);

		SyntaxElement basicOpersSyntaxM3EnumLiteral = new SyntaxElement('E',
				"BsEnumLiteral", false, true, "BsEnumLiteral",
				"infrasyntaxm2miniconcept",
				"Operations Meta Meta Meta Enumeration Literal", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3EnumLiteral.addModelingAttribute("identifier",
				"String", false, "Identifier", "", "", 0, 1, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n\n", "");

		basicOpersSyntaxM3EnumLiteral
				.addModelingAttribute("litValue", new ElemAttribute("litValue",
						"String", AttributeType.SYNTAX, false, "Concept Name",
						"", "", 0, 2, "", "", -1, "", ""));

		InstConcept instInfraSyntaxOpersM3EnumLiteral = new InstConcept(
				"BsEnumLiteral", instInfraSyntaxOpersM3Set,
				basicOpersSyntaxM3EnumLiteral);
		variabilityInstVertex.put("BsEnumLiteral",
				instInfraSyntaxOpersM3EnumLiteral);

		SyntaxElement infraSyntaxM3EnumEnumLit = new SyntaxElement('P',
				"BsEnumEnumLit", false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsEnumEnumLit", new InstPairwiseRel(
				"BsEnumEnumLit", infraSyntaxM3EnumEnumLit));

		InstPairwiseRel instEdge = new InstPairwiseRel();
		constraintInstEdges.put("enum-enumlit", instEdge);
		instEdge.setIdentifier("enum-enumlit");
		instEdge.setEdSyntaxEle(infraSyntaxM3EnumEnumLit);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3EnumLiteral, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Enum, true);

		// arrow

		SyntaxElement infraSyntaxM3Asso = new SyntaxElement('P', "BsAso",
				false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsAso", new InstPairwiseRel("BsAso",
				infraSyntaxM3Asso));

		// FIXME next three are temporal but must be removed when the extends
		// and metasso
		// applies for the instances
		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-a-co", instEdge);
		instEdge.setIdentifier("co-a-co");
		instEdge.setEdSyntaxEle(infraSyntaxM3Asso);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Concept, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Concept, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-a-set", instEdge);
		instEdge.setIdentifier("co-a-set");
		instEdge.setEdSyntaxEle(infraSyntaxM3Asso);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Set, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Concept, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-a-tree", instEdge);
		instEdge.setIdentifier("co-a-tree");
		instEdge.setEdSyntaxEle(infraSyntaxM3Asso);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Tree, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Concept, true);

		// Meta Arrow and Generalization and Association

		SyntaxElement basicOpersSyntaxM3Arrow = new SyntaxElement('P',
				"BsArrow", true, true, "BsArrow", "infrasyntaxm2miniconcept",
				"Relations for the meta-concept", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Arrow.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 2, "", "", 1, "<<BsArrow>>\n#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		basicOpersSyntaxM3Arrow.addModelingAttribute("Style",
				new ElemAttribute("Style", "String", AttributeType.SYNTAX,
						false, "Drawing Style", "", "refasclaim", 0, 8, "", "",
						-1, "", ""));

		InstConcept instInfraSyntaxOpersM3Arrow = new InstConcept("BsArrow",
				instInfraSyntaxOpersM3Concept, basicOpersSyntaxM3Arrow);
		variabilityInstVertex.put("BsArrow", instInfraSyntaxOpersM3Arrow);

		SyntaxElement infraSyntaxM3ConRel1 = new SyntaxElement('P',
				"BsConRel1", false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsConRel1", new InstPairwiseRel("BsConRel1",
				infraSyntaxM3ConRel1));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-rel1", instEdge);
		instEdge.setIdentifier("co-rel1");
		instEdge.setEdSyntaxEle(infraSyntaxM3ConRel1);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Arrow, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Concept, true);

		SyntaxElement infraSyntaxM3ConRel2 = new SyntaxElement('P',
				"BsConRel2", false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsConRel2", new InstPairwiseRel("BsConRel2",
				infraSyntaxM3ConRel2));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-rel2", instEdge);
		instEdge.setIdentifier("co-rel2");
		instEdge.setEdSyntaxEle(infraSyntaxM3ConRel2);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Arrow, true);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Concept, true);

		SyntaxElement basicOpersSyntaxM3Gen = new SyntaxElement('P',
				"BsGeneralization", false, true, "BsGeneralization",
				"refasextends", "Relations for the meta-concept", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instInfraSyntaxOpersM3Gen = new InstConcept(
				"BsGeneralization", instInfraSyntaxOpersM3Arrow,
				basicOpersSyntaxM3Gen);
		variabilityInstVertex
				.put("BsGeneralization", instInfraSyntaxOpersM3Gen);

		SyntaxElement infraSyntaxM3ConGen1 = new SyntaxElement('P',
				"BsConGen1", false, true, "Generalization Relation",
				"defaultAsso", "Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsConGen1", new InstPairwiseRel("BsConGen1",
				infraSyntaxM3ConGen1));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-gen1", instEdge);
		instEdge.setIdentifier("co-gen1");
		instEdge.setEdSyntaxEle(infraSyntaxM3ConGen1);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Gen, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Concept, true);

		SyntaxElement infraSyntaxM3ConGen2 = new SyntaxElement('P',
				"BsConGen2", false, true, "Generalization Relation",
				"defaultAsso", "Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsConGen2", new InstPairwiseRel("BsConGen2",
				infraSyntaxM3ConGen2));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-gen2", instEdge);
		instEdge.setIdentifier("co-gen2");
		instEdge.setEdSyntaxEle(infraSyntaxM3ConGen2);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Gen, true);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Concept, true);

		SyntaxElement basicOpersSyntaxM3Asso = new SyntaxElement('P',
				"BsAssociation", false, true, "BsAssociation", "defaultAsso",
				"Relations for the meta-concept", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Asso.addModelingAttribute("lowerBound",
				new ElemAttribute("lowerBound", "Integer",
						AttributeType.SYNTAX, false, "Low Cardinality", "", "",
						0, 1, "", "", -1, "", ""));

		basicOpersSyntaxM3Asso.addModelingAttribute("upperBound", "Integer",
				false, "High Cardinality", "", "", 0, 2, "", "", -1, "#"
						+ "highCard" + "#all#\n\n", "");

		InstConcept instInfraSyntaxOpersM3Asso = new InstConcept(
				"BsAssociation", instInfraSyntaxOpersM3Arrow,
				basicOpersSyntaxM3Asso);
		variabilityInstVertex.put("BsAssociation", instInfraSyntaxOpersM3Asso);

		SyntaxElement infraSyntaxM3ConAsso1 = new SyntaxElement('P',
				"BsConAsso1", false, true, "Association Relation",
				"defaultAsso", "Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsConAsso1", new InstPairwiseRel("BsConAsso1",
				infraSyntaxM3ConAsso1));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-asso1", instEdge);
		instEdge.setIdentifier("co-asso1");
		instEdge.setEdSyntaxEle(infraSyntaxM3ConAsso1);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Asso, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3NamedElement, true);

		SyntaxElement infraSyntaxM3ConAsso2 = new SyntaxElement('P',
				"BsConAsso2", false, true, "Association Relation",
				"defaultAsso", "Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsConAsso2", new InstPairwiseRel("BsConAsso2",
				infraSyntaxM3ConAsso2));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-asso2", instEdge);
		instEdge.setIdentifier("co-asso2");
		instEdge.setEdSyntaxEle(infraSyntaxM3ConAsso2);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Asso, true);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3NamedElement, true);

		// Generalization relations

		SyntaxElement infraSyntaxM3EnumEleGen = new SyntaxElement('P',
				"BsEnumEleGen", false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsArrowEleGen", new InstPairwiseRel(
				"BsEnumEleGen", infraSyntaxM3EnumEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-enum-ele-gen", instEdge);
		instEdge.setIdentifier("co-enum-ele-gen");
		instEdge.setTransSupInstElement(instInfraSyntaxOpersM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM3EnumEleGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Classifier, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Enum, true);

		SyntaxElement infraSyntaxM3EnumLitEleGen = new SyntaxElement('P',
				"BsEnumLinEleGen", false, true, "Normal Relation",
				"defaultAsso", "Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsEnumLinEleGen", new InstPairwiseRel(
				"BsEnumLinEleGen", infraSyntaxM3EnumLitEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-enumlit-ele-gen", instEdge);
		instEdge.setIdentifier("co-enumlit-ele-gen");
		instEdge.setTransSupInstElement(instInfraSyntaxOpersM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM3EnumLitEleGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3NamedElement, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3EnumLiteral, true);

		SyntaxElement infraSyntaxM3ArrowEleGen = new SyntaxElement('P',
				"BsArrowEleGen", false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsArrowEleGen", new InstPairwiseRel(
				"BsArrowEleGen", infraSyntaxM3ArrowEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-arrow-ele-gen", instEdge);
		instEdge.setIdentifier("co-arrow-ele-gen");
		instEdge.setTransSupInstElement(instInfraSyntaxOpersM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM3ArrowEleGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3NamedElement, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Arrow, true);

		SyntaxElement infraSyntaxM3NodeEleGen = new SyntaxElement('P',
				"BsNodeEleGen", false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsNodeEleGen", new InstPairwiseRel(
				"BsNodeEleGen", infraSyntaxM3NodeEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-node-ele-gen", instEdge);
		instEdge.setIdentifier("co-node-ele-gen");
		instEdge.setTransSupInstElement(instInfraSyntaxOpersM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM3NodeEleGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Classifier, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Concept, true);

		SyntaxElement infraSyntaxM3SetEleGen = new SyntaxElement('P',
				"BsSetEleGen", false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsSetEleGen", new InstPairwiseRel(
				"BsSetEleGen", infraSyntaxM3SetEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-set-ele-gen", instEdge);
		instEdge.setIdentifier("co-set-ele-gen");
		instEdge.setTransSupInstElement(instInfraSyntaxOpersM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM3SetEleGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3NamedElement, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Set, true);

		SyntaxElement infraSyntaxM3TreeEleGen = new SyntaxElement('P',
				"BsSetEleGen", false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsSetEleGen", new InstPairwiseRel(
				"BsSetEleGen", infraSyntaxM3TreeEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-tree-ele-gen", instEdge);
		instEdge.setIdentifier("co-tree-ele-gen");
		instEdge.setTransSupInstElement(instInfraSyntaxOpersM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM3TreeEleGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3NamedElement, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Tree, true);

		SyntaxElement infraSyntaxM3EleNameGen = new SyntaxElement('P',
				"BsEleNameGen", false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsEleNameGen", new InstPairwiseRel(
				"BsEleNameGen", infraSyntaxM3EleNameGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-ele-name-gen", instEdge);
		instEdge.setIdentifier("co-ele-name-gen");
		instEdge.setTransSupInstElement(instInfraSyntaxOpersM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM3EleNameGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3NamedElement, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Classifier, true);

		// Node Attributes

		SyntaxElement basicOpersSyntaxM3Attribute = new SyntaxElement('A',
				"BsAttibure", false, true, "BsAttribute",
				"infrasyntaxm2bigconcept", "Attributes for the meta-concept",
				120, 120, "/com/variamos/gui/perspeditor/images/concept.png",
				true, Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Attribute.addModelingAttribute("type",
				new ElemAttribute("type", "Enumeration", AttributeType.SYNTAX,
						false, "Type", "", "TypeEnum", "", "", 0, 3, "", "",
						-1, "type" + "#all#\n\n", ""));

		// attribute Type
		basicOpersSyntaxM3Attribute.addModelingAttribute("displayType",
				new ElemAttribute("displayType", "Enumeration",
						AttributeType.SYNTAX, false, "Display Type", "",
						"DisplayTypeEnum", "", "", 0, 3, "", "", -1,
						"displayType" + "#all#\n\n", ""));

		basicOpersSyntaxM3Attribute.addModelingAttribute("affectProps",
				"Boolean", false, "Affect Prties", "", "", 0, 4, "", "", -1,
				"#" + "affectProps" + "#all#\n\n", "");

		// classCanName
		basicOpersSyntaxM3Attribute.addModelingAttribute("enumType", "String",
				false, "Enumeration Type", "", "", 0, 7, "", "", -1, "#"
						+ "enumType" + "#all#\n\n", "");

		// metaCInstType
		basicOpersSyntaxM3Attribute.addModelingAttribute("instanceType",
				"String", false, "Instance Type", "", "", 0, 8, "", "", -1, "#"
						+ "instanceType" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("defaultValue", "Any",
				false, "Default Value", "", "", 0, 9, "", "", -1, "#"
						+ "defaultValue" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("domain", "String",
				false, "Domain", "", "", 0, 10, "", "", -1, "#" + "domain"
						+ "#all#\n\n", "");
		basicOpersSyntaxM3Attribute.addModelingAttribute("hint", "String",
				false, "Hint", "", "", 0, 11, "", "", -1, "#" + "hint"
						+ "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("propertyPosition",
				"Integer", false, "Property Position", "", "", 0, 12, "", "",
				-1, "#" + "propertyPosition" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute(
				"shapeDisplayPosition", "Integer", false,
				"Element Disp. Position", "", "", 0, 13, "", "", -1, "#"
						+ "elementDisplayPosition" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute("shapeDisplaySpacers",
				"String", false, "Element Disp. Spacers", "", "", 0, 14, "",
				"", -1, "#" + "elementDisplaySpacers" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute(
				"propertyEditionCondition", "String", false,
				"Prop. Tab Edition Cond.", "", "", 0, 15, "", "", -1, "#"
						+ "propTabEditionCondition" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute(
				"propertyVisualCondition", "String", false,
				"Prop. Tab Visual Cond.", "", "", 0, 16, "", "", -1, "#"
						+ "propTabVisualCondition" + "#all#\n\n", "");

		basicOpersSyntaxM3Attribute.addModelingAttribute(
				"shapeDisplayCondition", "String", false, "Shape Visual Cond.",
				"", "", 0, 17, "", "", -1, "#" + "elementDisplayCondition"
						+ "#all#\n\n", "");

		// basicOpersSyntaxM3Attribute.addModelingAttribute("domFiltOwnFields",
		// "String", false, "Filter domain (Own Fields)", "", "", 0, 18,
		// "", "", -1, "#" + "domFiltOwnFields" + "#all#\n\n", "");
		//
		// basicOpersSyntaxM3Attribute.addModelingAttribute("domFilTRelFields",
		// "String", false, "Filter domain (Rel. Fields)", "", "", 0, 19,
		// "", "", -1, "#" + "domFilTRelFields" + "#all#\n\n", "");
		//
		// basicOpersSyntaxM3Attribute.addModelingAttribute("defDomValueField",
		// "String", false, "Def. Domain (Filter Field)", "", "", 0, 20,
		// "", "", -1, "#" + "defDomValueField" + "#all#\n\n", "");

		InstConcept instInfraSyntaxOpersM3Attribute = new InstConcept(
				"BsAttribute", instInfraSyntaxOpersM3Set,
				basicOpersSyntaxM3Attribute);
		variabilityInstVertex.put("BsAttribute",
				instInfraSyntaxOpersM3Attribute);

		SyntaxElement infraSyntaxM3ConAtt = new SyntaxElement('P', "BsConAtt",
				false, true, "Normal Relation", "defaultAsso",
				"Concept-Attribute relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsConAtt", new InstPairwiseRel("BsConAtt",
				infraSyntaxM3ConAtt));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-att", instEdge);
		instEdge.setIdentifier("co-att");
		instEdge.setEdSyntaxEle(infraSyntaxM3ConAtt);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Attribute, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3NamedElement, true);

		// SyntaxElement infraSyntaxM3SetAtt = new SyntaxElement('P',
		// "BsSetAtt",
		// false, true, "Normal Relation", "defaultAsso",
		// "Set-Attribute relation", 50, 50,
		// "/com/variamos/gui/perspeditor/images/plnode.png", 1, null);
		//
		// constraintInstEdges.put("BsSetAtt", new InstPairwiseRel("BsTreeAtt",
		// infraSyntaxM3SetAtt));
		//
		// instEdge = new InstPairwiseRel();
		// constraintInstEdges.put("set-attribute", instEdge);
		// instEdge.setIdentifier("set-attribute");
		// instEdge.setEdSyntaxEle(infraSyntaxM3SetAtt);
		// instEdge.setTargetRelation(instInfraSyntaxOpersM3Attribute, true);
		// instEdge.setSourceRelation(instInfraSyntaxOpersM3Set, true);

		SyntaxElement infraSyntaxM3TreeAtt = new SyntaxElement('P',
				"BsTreeAtt", false, true, "Normal Relation", "defaultAsso",
				"Tree-Attribute relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsTreeAtt", new InstPairwiseRel("BsTreeAtt",
				infraSyntaxM3TreeAtt));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("tree-attribute", instEdge);
		instEdge.setIdentifier("tree-attribute");
		instEdge.setEdSyntaxEle(infraSyntaxM3TreeAtt);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Attribute, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Tree, true);

		SyntaxElement infraSyntaxM3ArrowAtt = new SyntaxElement('P',
				"BsArrowAtt", false, true, "Normal Relation", "defaultAsso",
				"Arrow-Attribute relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsArrowAtt", new InstPairwiseRel("BsArrowAtt",
				infraSyntaxM3TreeAtt));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("arrow-attribute", instEdge);
		instEdge.setIdentifier("arrow-attribute");
		instEdge.setEdSyntaxEle(infraSyntaxM3ArrowAtt);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3Attribute, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Tree, true);

		SyntaxElement infraSyntaxM3AttNameGen = new SyntaxElement('P',
				"BsAttNameGen", false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("BsAttNameGen", new InstPairwiseRel(
				"BsAttNameGen", infraSyntaxM3SetEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-att-name-gen", instEdge);
		instEdge.setIdentifier("co-att-name-gen");
		instEdge.setTransSupInstElement(instInfraSyntaxOpersM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM3AttNameGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM3NamedElement, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM3Attribute, true);

		// Enums Instances are currently not used but are required to move types
		// from
		// com.variamos.dynsup.types to dynamic definitions

		SyntaxElement basicOpersSyntaxM3TypesEnum = new SyntaxElement('E',
				"AttribTypesEnum", false, true, "AttribTypesEnum",
				"infrasyntaxm2concept", "Enum", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instInfraSyntaxOpersM3TypesEnum = new InstConcept(
				"AttribTypesEnum", instInfraSyntaxOpersM3Enum,
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
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""), "Any"));
		enumVals.add(new InstAttribute("enum9", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Instance"));
		enumVals.add(new InstAttribute("enum10", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"InstanceSet"));
		enumVals.add(new InstAttribute("enum11", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"SemanticElement"));
		enumVals.add(new InstAttribute("enum11", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"MetaElement"));

		variabilityInstVertex.put("AttribTypesEnum",
				instInfraSyntaxOpersM3TypesEnum);

		SyntaxElement basicOpersSyntaxM3NodeTypesEnum = new SyntaxElement('E',
				"NodeTypesEnum", false, true, "NodeTypesEnum",
				"infrasyntaxm2concept", "Enum", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instInfraSyntaxOpersM3NodeTypesEnum = new InstConcept(
				"NodeTypesEnum", instInfraSyntaxOpersM3Enum,
				basicOpersSyntaxM3NodeTypesEnum);

		enumVals = new ArrayList<InstAttribute>();
		instInfraSyntaxOpersM3NodeTypesEnum.getInstAttribute("enumValues")
				.setValue(enumVals);
		// enumVals.add(new InstAttribute("enum1", new
		// ElemAttribute("EnumValue",
		// StringType.IDENTIFIER, AttributeType.SYNTAX, false,
		// "Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
		// "ListElements"));
		enumVals.add(new InstAttribute("enum2", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Concept"));
		// enumVals.add(new InstAttribute("enum3", new
		// ElemAttribute("EnumValue",
		// StringType.IDENTIFIER, AttributeType.SYNTAX, false,
		// "Enumeration Value", "", "", 1, -1, "", "", -1, "", ""), "Enum"));
		// enumVals.add(new InstAttribute("enum4", new
		// ElemAttribute("EnumValue",
		// StringType.IDENTIFIER, AttributeType.SYNTAX, false,
		// "Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
		// "Generalization"));
		enumVals.add(new InstAttribute("enum5", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Binary"));
		enumVals.add(new InstAttribute("enum6", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Paradigm"));
		enumVals.add(new InstAttribute("enum7", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"N-ary"));

		variabilityInstVertex.put("NodeTypesEnum",
				instInfraSyntaxOpersM3NodeTypesEnum);

		SyntaxElement basicOpersSyntaxM3DispTypesEnum = new SyntaxElement('E',
				"DisplayTypesEnum", false, true, "DisplayTypesEnum",
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
				"Model_Property"));
		enumVals.add(new InstAttribute("enum2", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Model_Status"));
		enumVals.add(new InstAttribute("enum3", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Config_Property"));
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
