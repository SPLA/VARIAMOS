package com.variamos.dynsup.defaultmodels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.variamos.core.util.StringUtils;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.OpersComputationType;
import com.variamos.dynsup.types.OpersDefectType;
import com.variamos.dynsup.types.OpersExecType;
import com.variamos.dynsup.types.OpersOpType;
import com.variamos.dynsup.types.OpersSubOpExecType;
import com.variamos.dynsup.types.OpersSubOpType;
import com.variamos.hlcl.IntervalDomain;
import com.variamos.reasoning.defectAnalyzer.model.enums.DefectAnalyzerMode;

public class InfraSyntaxOpersMMM {

	public static void createSyntaxOpersMetaMetaModel(
			ModelInstance modelInstance) {

		Map<String, InstElement> variabilityInstVertex = modelInstance
				.getVariabilityVertex();

		Map<String, InstPairwiseRel> constraintInstEdges = modelInstance
				.getConstraintInstEdges();

		InstElement basicOpersSyntaxM3Concept = modelInstance.getSyntaxModel()
				.getVertex("OMMConcept");

		List<Integer> dom = new ArrayList<Integer>();
		dom.add(2);
		dom.add(4);
		IntervalDomain d = new IntervalDomain();
		d.setRangeValues(dom);

		// Begin Opers M2 Model

		SyntaxElement infraSyntaxOpersM2InfraConcept = new SyntaxElement(
				'C',
				"SeMnmConcept",
				false,
				false,
				"SeMnmConcept",
				"infrasyntaxm2biggrayconcept",
				"Operations Non-Modifiable MetaMetaConcept: Defines an element "
						+ "with  the attributes for meta-concepts to support the visual "
						+ "representation of states and operation results",
				100, 250, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2InfraConcept.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4,
				"<<SeMNmConcept>>\n" + "#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n" + "<<non-modifiable>>" + "\n\n", "");

		InstConcept instInfraSyntaxOpersM2nmConcept = new InstConcept(
				"SeMnmConcept", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2InfraConcept);

		variabilityInstVertex.put("SeMnmConcept",
				instInfraSyntaxOpersM2nmConcept);

		SyntaxElement infraSyntaxOpersM2Concept = new SyntaxElement(
				'C',
				"SeMConcept",
				true,
				true,
				"SeMConcept",
				"infrasyntaxm2concept",
				"Operations MetaMetaConcept: Define a MMConcept for the operations",
				100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2Concept.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SeMConcept>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n",
				"");

		InstConcept instInfraSyntaxOpersM2Concept = new InstConcept(
				"SeMConcept", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2Concept);

		variabilityInstVertex.put("SeMConcept", instInfraSyntaxOpersM2Concept);

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

		SyntaxElement infraSyntaxOpersM2PWRel = new SyntaxElement('P',
				"SeMnmPWRel", false, false, "SeMnmPWRel",
				"infrasyntaxm2minigrayconcept",
				"Operations Non-Modifiable MMPairWise Relation: Defines a "
						+ "type attribute for meta-pairwise-relations.", 150,
				150, "/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2PWRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SeMnmRel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n"
						+ "<<non-modifiable>>" + "\n\n", "");

		infraSyntaxOpersM2PWRel.addModelingAttribute(
				"relTypesAttr",
				new ElemAttribute("relTypesAttr", "Set", AttributeType.SYNTAX,
						false, "relationTypes", "", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2PWRel.addModelingAttribute("opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expr.", "",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		InstConcept instInfraSyntaxOpersM2PWRel = new InstConcept("SeMnmPWRel",
				basicOpersSyntaxM3Concept, infraSyntaxOpersM2PWRel);
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("SeMnmPWRel", instInfraSyntaxOpersM2PWRel);

		SyntaxElement metaMetaPairwiseRel = new SyntaxElement(
				'P',
				"SeMPWRel",
				true,
				true,
				"SeMPWRel",
				"infrasyntaxopersm2miniconcept",
				"Operations MMPairWise Relation: Defines a direct relation for the operations meta-model",
				150, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		metaMetaPairwiseRel.addModelingAttribute(
				"relTypesAttr",
				new ElemAttribute("relTypesAttr", "Set", AttributeType.SYNTAX,
						false, "relationTypes", "", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		metaMetaPairwiseRel.addModelingAttribute("opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expr.", "",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		metaMetaPairwiseRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SeMPWRel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n",
				"");

		InstConcept instPairWiseRelation = new InstConcept("SeMPWRel",
				basicOpersSyntaxM3Concept, metaMetaPairwiseRel);
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("SeMPWRel", instPairWiseRelation);

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

		SyntaxElement infraSyntaxOpersM2InfraOTRel = new SyntaxElement('T',
				"SeMnmOTRel", false, false, "SeMnmOTRel",
				"infrasyntaxm2biggrayconcept",
				"Operations Non-Modifiable MMOverTwo Relation: Defines a "
						+ "type attribute for meta-pairwise-relations", 100,
				150, "/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2InfraOTRel.addModelingAttribute(
				"relTypesAttr",
				new ElemAttribute("relTypesAttr", "Set", AttributeType.SYNTAX,
						false, "relationTypes", "", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2InfraOTRel.addModelingAttribute(
				"opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "semanticExpressions", "", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2InfraOTRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SeMnmOTRel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n"
						+ "<<non-modifiable>>" + "\n\n", "");

		InstConcept instInfraSyntaxOpersM2nmOTRel = new InstConcept(
				"SeMnmOTRel", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2InfraOTRel);
		variabilityInstVertex.put("SeMnmOTRel", instInfraSyntaxOpersM2nmOTRel);

		SyntaxElement infraSyntaxOpersM2OTRel = new SyntaxElement('O',
				"SeMOTRel", true, true, "SeMOTRel",
				"infrasyntaxopersm2miniconcept", "Over Two Relation", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OTRel.addModelingAttribute(
				"relTypesAttr",
				new ElemAttribute("relTypesAttr", "Set", AttributeType.SYNTAX,
						false, "relationTypes", "", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2OTRel.addModelingAttribute(
				"opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "semanticExpressions", "", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2OTRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SeMOTRel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n",
				"");

		InstConcept instInfraSyntaxOpersM2OTRel = new InstConcept("SeMOTRel",
				basicOpersSyntaxM3Concept, infraSyntaxOpersM2OTRel);
		variabilityInstVertex.put("SeMOTRel", instInfraSyntaxOpersM2OTRel);

		OpersConcept basicOpersM2ExtRel = new OpersConcept("ExtRel");

		InstConcept instBasicOpersM2ExtRel = new InstConcept("ExtendsRelation",
				basicOpersSyntaxM3Concept, basicOpersM2ExtRel);

		OpersConcept basicOpersM2AsoRel = new OpersConcept("AsoRel");

		InstConcept instBasicOpersM2AsoRel = new InstConcept("SeMAsoEdge",
				basicOpersSyntaxM3Concept, basicOpersM2AsoRel);

		// variabilityInstVertex.put("TypeEnumeration", new InstConcept(
		// "TypeEnumeration", metaBasicConcept, enumeration));

		SyntaxElement infraSyntaxOpersM2AsoRel = new SyntaxElement('P',
				"SeMAsoEdge", false, true, "Association Relation",
				"defaultAsso", "Association Relation: ", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instBasicOpersM2AsoRel);

		infraSyntaxOpersM2AsoRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		infraSyntaxOpersM2AsoRel.addModelingAttribute(
				InstPairwiseRel.VAR_METAPAIRWISE, new ElemAttribute(
						InstPairwiseRel.VAR_METAPAIRWISE, "Class",
						AttributeType.OPERATION, true,
						InstPairwiseRel.VAR_METAPAIRWISE_NAME, "",
						InstPairwiseRel.VAR_METAPAIRWISE_CLASS,
						new SyntaxElement('P'), 0, 2, "", "", -1, "", ""));

		constraintInstEdges.put("SeMAsoEdge", new InstPairwiseRel(
				infraSyntaxOpersM2AsoRel));

		SyntaxElement infraSyntaxOpersM2ExtRel = new SyntaxElement(
				'P',
				"ExtendsRelation",
				false,
				true,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instBasicOpersM2ExtRel);

		infraSyntaxOpersM2ExtRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		InstPairwiseRel rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("SeMExtCEdge");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("SeMExtCEdge", rel);

		infraSyntaxOpersM2ExtRel.addModelingAttribute(
				InstPairwiseRel.VAR_METAPAIRWISE, new ElemAttribute(
						InstPairwiseRel.VAR_METAPAIRWISE, "Class",
						AttributeType.OPERATION, true,
						InstPairwiseRel.VAR_METAPAIRWISE_NAME, "",
						InstPairwiseRel.VAR_METAPAIRWISE_CLASS,
						new SyntaxElement('P'), 0, 2, "", "", -1, "", ""));

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("SeMExtnmCEdge");
		rel.setTargetRelation(instInfraSyntaxOpersM2nmConcept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("SeMExtnmCEdge", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("SeMExtOTCEdge");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("SeMExtOTCEdge", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("SeMExtOTOdge");
		rel.setTargetRelation(instInfraSyntaxOpersM2OTRel, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("SeMExtOTEdge", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("SeMExtnmOTEdge");
		rel.setTargetRelation(instInfraSyntaxOpersM2nmOTRel, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("MExtnmOTEdge", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsPIPRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2PWRel, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("ExtendsPIPRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssonmCPWRel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2nmConcept, true);
		constraintInstEdges.put("AssonmCPWRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssonmPWCRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2nmConcept, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssonmPWCRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoOCPWRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoOCPWRel", rel);

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

		SyntaxElement infraSyntaxOpersM2OperGroup = new SyntaxElement(
				'M',
				"OpMOperGroup",
				true,
				true,
				"OpMOperGroup",
				"sinfrasyntaxopersm2opergroup",
				"Defines meta-elements that groups together related operations",
				110, 55, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperGroup
				.addModelingAttribute(
						"menuType",
						new ElemAttribute(
								"menuType",
								"String",
								AttributeType.OPERATION,
								"Perspective to display",
								"Supported option are 2 (Modeling) and 4 (Model Configuration/Simulation)",
								"4", false, d, 0, 5, "", "", -1, "", ""));

		infraSyntaxOpersM2OperGroup.addModelingAttribute("visible",
				new ElemAttribute("visible", "Boolean",
						AttributeType.OPERATION, false, "Visible", "", true, 0,
						8, "", "", -1, "", ""));

		infraSyntaxOpersM2OperGroup.addModelingAttribute("clearButton",
				new ElemAttribute("clearButton", "Boolean",
						AttributeType.OPERATION, false,
						"Show clear errors on menu",
						"Currently ignored by the implementation", false, 0, 9,
						"false", "", -1, "", ""));

		infraSyntaxOpersM2OperGroup.addModelingAttribute("execAll",
				new ElemAttribute("execAll", "Boolean",
						AttributeType.OPERATION, false,
						"Show menu: execute all",
						"Currently ignored by the implementation", false, 0,
						10, "false", "", -1, "", ""));

		infraSyntaxOpersM2OperGroup.addModelingAttribute("name",
				new ElemAttribute("name", "String", AttributeType.OPERATION,
						false, "Name", "", null, 0, 6, "", "", 6, "", ""));

		infraSyntaxOpersM2OperGroup.addModelingAttribute("shortcut",
				new ElemAttribute("shortcut", "String",
						AttributeType.OPERATION, false, "Shortcut",
						"Keyboard commmand to call the operation", null, 0, 9,
						"false", "", -1, "", ""));

		infraSyntaxOpersM2OperGroup
				.addModelingAttribute(
						"index",
						new ElemAttribute(
								"index",
								"Integer",
								AttributeType.OPERATION,
								false,
								"Position",
								"sorting index for this group of operations in the menu",
								1, 0, 6, "", "", -1, "", ""));

		infraSyntaxOpersM2OperGroup.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4,
				"<<OpMOperGroup>>\n#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n", "");

		InstConcept instInfraSyntaxOpersM2OperGroup = new InstConcept(
				"OpMOperGroup", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperGroup);
		variabilityInstVertex.put("OpMOperGroup",
				instInfraSyntaxOpersM2OperGroup);

		SyntaxElement infraSyntaxOpersM2MetaModel = new SyntaxElement('C',
				"SeMModel", true, true, "SeMModel",
				"infrasyntaxopersm2miniconcept", "Semantic Model", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2MetaModel.addModelingAttribute("name",
				new ElemAttribute("name", "String", AttributeType.OPERATION,
						false, "Name", "", null, 0, 6, "", "", 4, "", ""));

		infraSyntaxOpersM2MetaModel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SeMModel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n",
				"");

		InstConcept instInfraSyntaxOpersM2MetaModel = new InstConcept(
				"SeMModel", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2MetaModel);
		variabilityInstVertex.put("SeMModel", instInfraSyntaxOpersM2MetaModel);

		SyntaxElement infraSyntaxOpersM2OperAction = new SyntaxElement('A',
				"OpMOperation", true, true, "OpMOperation",
				"sinfrasyntaxopersm2oper",
				"Is an executable action over a visual model composed of one"
						+ " or several suboperations", 100, 75,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperAction
				.addModelingAttribute("operType", new ElemAttribute("operType",
						"Enumeration", AttributeType.OPERATION, true,
						"Operation Type",
						"Sequential execution (verification, validation)",
						OpersOpType.class.getCanonicalName(),
						OpersOpType.Verification, "", 0, 4, "", "", -1, "", ""));
		infraSyntaxOpersM2OperAction
				.addModelingAttribute(
						"compType",
						new ElemAttribute(
								"compType",
								"Enumeration",
								AttributeType.OPERATION,
								false,
								"Simple quotient",
								"Type of quotient applied between suboper 1 and suboper2",
								OpersComputationType.class.getCanonicalName(),
								"", "", 0, 11,
								"operType#==#Computational analysis# ",
								"operType#==#Computational analysis# ", -1, "",
								""));

		infraSyntaxOpersM2OperAction.addModelingAttribute(
				"execType",
				new ElemAttribute("execType", "Enumeration",
						AttributeType.OPERATION, false, "Execution Type",
						"Currently ignored by the implementation "
								+ "(on_demand assumed)", OpersExecType.class
								.getCanonicalName(), "on demand", "", 0, 5,
						"false", "", -1, "", ""));
		infraSyntaxOpersM2OperAction.addModelingAttribute("name",
				new ElemAttribute("name", "String", AttributeType.OPERATION,
						false, "Display Name", "", null, 0, 6, "", "", -1, "",
						""));

		infraSyntaxOpersM2OperAction.addModelingAttribute("shortcut",
				new ElemAttribute("shortcut", "String",
						AttributeType.OPERATION, false, "Shortcut", "", null,
						0, 7, "false", "", -1, "", ""));

		infraSyntaxOpersM2OperAction.addModelingAttribute("position",
				new ElemAttribute("position", "Integer",
						AttributeType.OPERATION, false, "Position",
						"Sorting index of this operation in the menu", 8, 0,
						-1, "", "", -1, "", ""));

		infraSyntaxOpersM2OperAction.addModelingAttribute("visible",
				new ElemAttribute("visible", "Boolean",
						AttributeType.OPERATION, false, "Visible Operation",
						"Show/Hide this operation in the menu", true, 0, 9, "",
						"", -1, "", ""));

		infraSyntaxOpersM2OperAction.addModelingAttribute("iteration",
				new ElemAttribute("iteration", "Boolean",
						AttributeType.OPERATION, false, "Iterate Button",
						"Select this option if the operation includes "
								+ "an Iterative Solutions sub-operation"
								+ " to display the next menu", false, 0, 9, "",
						"", -1, "", ""));

		infraSyntaxOpersM2OperAction.addModelingAttribute("iterationName",
				new ElemAttribute("iterationName", "String",
						AttributeType.OPERATION, false, "Iteration Name",
						"Name to show on the menu for the iteration opcion "
								+ "(e.g., next solution)", null, 0, -1,
						"false", "", -1, "", ""));

		infraSyntaxOpersM2OperAction.addModelingAttribute("prevSpacer",
				new ElemAttribute("prevSpacer", "Boolean",
						AttributeType.OPERATION, false,
						"Add Previous Spacer when presenting this operation"
								+ " in the menu", "", false, 0, -1, "", "", -1,
						"", ""));

		infraSyntaxOpersM2OperAction.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4,
				"<<OpMOperation>>\n#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n", "");

		InstConcept instInfraSyntaxOpersM2OperAction = new InstConcept(
				"OpMOperation", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperAction);
		variabilityInstVertex.put("OpMOperation",
				instInfraSyntaxOpersM2OperAction);

		SyntaxElement infraSyntaxOpersM2OperSubAction = new SyntaxElement(
				'S',
				"OpMSubOper",
				true,
				true,
				"OpMSubOper",
				"sinfrasyntaxopersm2suboper",
				"Is the unit of action to define an operation. "
						+ "An instance of the OpMOperation must have at least one OpMSubOper instance.",
				100, 55, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("name",
				new ElemAttribute("name", "String", AttributeType.OPERATION,
						false, "Name",
						"Name to identify and display  the operation", null, 0,
						-1, "", "", -1, "", ""));

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("index",
				new ElemAttribute("index", "Integer", AttributeType.OPERATION,
						false, "Execution Order",
						"Defines the order of execution between suboperations",
						1, 0, 6, "", "", -1, "", ""));

		// infraSyntaxOpersM2OperSubAction.addModelingAttribute("iteration",
		// new ElemAttribute("iteration", "Boolean",
		// AttributeType.OPERATION, false, "Iterative Sub-Oper",
		// "Select this option if the operation include "
		// + "an Iterative Solutions sub-operation"
		// + " to display the next menu", false, 0, 8, "",
		// "", -1, "", ""));
		infraSyntaxOpersM2OperSubAction.addModelingAttribute(
				"completedMessage", new ElemAttribute("completedMessage",
						"String", AttributeType.OPERATION, false,
						"Text when completed",
						"Message to show when the operation is "
								+ "completed succesfully", "", 0, 9, "", "",
						-1, "", ""));

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("showDashboard",
				new ElemAttribute("showDashboard", "Boolean",
						AttributeType.OPERATION, false, "ShowDashboard", "",
						false, 0, 12, "", "", -1, "", ""));

		infraSyntaxOpersM2OperSubAction.addModelingAttribute(
				"type",
				new ElemAttribute("type", "Enumeration",
						AttributeType.OPERATION, true, "Algorithm Type",
						"Algorithm ype for the Suboperation: determines the path for "
								+ "the execution model", OpersSubOpType.class
								.getCanonicalName(), "First solution", "", 0,
						4, "", "", 10, "\ntype: #" + "type" + "#all#", ""));
		infraSyntaxOpersM2OperSubAction.addModelingAttribute("defectType",
				new ElemAttribute("defectType", "Enumeration",
						AttributeType.OPERATION, false, "DefectsVerif. Method",
						"Specifies the method of the Defects Verifier to use",
						OpersDefectType.class.getCanonicalName(), "", "", 0, 5,
						"type#!=#Multi verification# "
						/*
						 * "type#==#IdDef defects verif$ type#==#Defects
						 * verifier update# "
						 */
						, "type#!=#Multi verification# "
						/*
						 * "type#==#IdDef defects verif$typ e#==#Defects
						 * verifier update# "
						 */, 11, "\nMethod: #" + "defectType" + "#all#",
						"type#==#IdDef defects verif$type#"
								+ "==#UpdModel defects verif"));

		infraSyntaxOpersM2OperSubAction.addModelingAttribute(
				"defectsCoreOper",
				new ElemAttribute("defectsCoreOper", "Class",
						AttributeType.OPERATION, false,
						"Def. Verif. Core Oper",
						"Specifies the core operation to obtain the"
								+ " free identifiers", InstConcept.class
								.getCanonicalName(), "OpMOperation",
						"Update Core Elements", "", 0, 11,
						"type#==#IdDef defects verif#\"\"",
						"type#==#IdDef defects verif#\"\"", 12, "\ncoreOper: #"
								+ "defectsCoreOper" + "#all#",
						"type#==#IdDef defects verif"));

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("outAttribute",
				new ElemAttribute("outAttribute", "String",
						AttributeType.OPERATION, false, "Output Attribute",
						"Attribute to validate the elements with errors, must "
								+ "be an output attributes with 0,1 domain",
						"Sel", 0, 11, /*
									 * "type#==#IdDef defects verif$" +
									 * "type#==#UpdModel defects verif$" +
									 * "type#==#Multi verification# "
									 */"", "", 13, "\noutAtt: #"
								+ "outAttribute" + "#all#",
						"type#==#IdDef defects verif$"
								+ "type#==#UpdModel defects verif$"
								+ "type#==#Multi verification"));
		infraSyntaxOpersM2OperSubAction.addModelingAttribute("indivVerExp",
				new ElemAttribute("indivVerExp", "Boolean",
						AttributeType.OPERATION, false, "Indiv. Verif. Expr.",
						"Use Verification Expressions individually with"
								+ " multiple calls CauCos", false, 0, 11,
						"type#==#Multi verification# ",
						"type#==#Multi verification# ", -1, "", ""));
		infraSyntaxOpersM2OperSubAction.addModelingAttribute(
				"useNatLangExprDesc", new ElemAttribute("useNatLangExprDesc",
						"Boolean", AttributeType.OPERATION, false,
						"Use Nat. Lang. Desc.",
						"Use the natural language description of the relaxable "
								+ "expressions for this operation", false, 0,
						11, "type#==#Multi verification# ",
						"type#==#Multi verification# ", -1, "", ""));
		infraSyntaxOpersM2OperSubAction
				.addModelingAttribute(
						"indivRelExp",
						new ElemAttribute(
								"indivRelExp",
								"Boolean",
								AttributeType.OPERATION,
								false,
								"Indiv. Relax. Expr.",
								"Use Relaxable Expressions Individually with multiple calls to CauCos",
								false, 0, 11, "type#==#Multi verification# ",
								"type#==#Multi verification# ", -1, "", ""));

		infraSyntaxOpersM2OperSubAction.addModelingAttribute(
				"updateOutAttributes", new ElemAttribute("updateOutAttributes",
						"Boolean", AttributeType.OPERATION, false,
						"Update Out Attribs",
						"Use Update Output Attributes to update the "
								+ "results on the model", "", false, "", 0, 11,
						"type#!=#Multi verification# "
						/*
						 * "type#==#IdDef defects verif$type#== #Defects
						 * verifier update#\"\""
						 */, "", -1, "", ""));

		infraSyntaxOpersM2OperSubAction
				.addModelingAttribute(
						"reuseFreeIds",
						new ElemAttribute(
								"reuseFreeIds",
								"Boolean",
								AttributeType.OPERATION,
								false,
								"Reuse Free Identif.",
								"Update Free Identifiers to optimize execution "
										+ "of consecutive verifications using the"
										+ " DefectsVerifier (all verification "
										+ "must share the same set of free identifiers) ",
								false,
								0,
								12,
								"type#==#IdDef defects verif$type#==#UpdModel defects verif#false",
								"", -1, "", ""));
		infraSyntaxOpersM2OperSubAction
				.addModelingAttribute(
						"updateFreeIds",
						new ElemAttribute(
								"updateFreeIds",
								"Boolean",
								AttributeType.OPERATION,
								false,
								"Update Free Identif.",
								"Update Free Identifiers to optimize execution "
										+ "of consecutive verifications using the"
										+ " DefectsVerifier (all verification "
										+ "must share the same set of free identifiers) ",
								false,
								0,
								13,
								"type#==#IdDef defects verif$type#==#UpdModel defects verif#false",
								"", -1, "", ""));

		infraSyntaxOpersM2OperSubAction
				.addModelingAttribute(
						"errorTitle",
						new ElemAttribute(
								"errorTitle",
								"String",
								AttributeType.OPERATION,
								false,
								"Error title text",
								"Title of the dialog box when operation returns an error",
								"", 0, 14, "", "", -1, "", ""));

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("errorText",
				new ElemAttribute("errorText", "String",
						AttributeType.OPERATION, false, "General error text",
						"Text to display when the operation execution fails",
						"", 0, 15, "", "", -1, "", ""));

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("errorHint",
				new ElemAttribute("errorHint", "String",
						AttributeType.OPERATION, false, "Error hint text",
						"Hint text to display in each element with error", "",
						0, 16, "", "", -1, "", ""));

		infraSyntaxOpersM2OperSubAction
				.addModelingAttribute(
						"mode",
						new ElemAttribute(
								"mode",
								"Enumeration",
								AttributeType.OPERATION,
								false,
								"Defects Model",
								"Complete explores all the combination of errors, partial"
										+ " stops with the first set of error, incomplete"
										+ " identifies up to a number of error",
								DefectAnalyzerMode.class.getCanonicalName(),
								StringUtils
										.formatEnumValue(DefectAnalyzerMode.INCOMPLETE_FAST
												.toString()), "", 0, 16, "",
								"", -1, "", ""));

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("errorMsg",
				new ElemAttribute("errorMsg", "String",
						AttributeType.OPERATION, false, "Error Message", "",
						"", 0, 17, "", "", -1, "", ""));

		infraSyntaxOpersM2OperSubAction.addModelingAttribute(
				"exptype",
				new ElemAttribute("exptype", "Set", AttributeType.SYNTAX,
						false, "exptype", "", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", 6,
						"#\n", ""));

		infraSyntaxOpersM2OperSubAction.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<OpMSubOper>>\n#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n", "");

		InstConcept instInfraSyntaxOpersM2OperSubAction = new InstConcept(
				"OpMSubOper", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperSubAction);
		variabilityInstVertex.put("OpMSubOper",
				instInfraSyntaxOpersM2OperSubAction);

		SyntaxElement infraSyntaxOpersM2OperLabeling = new SyntaxElement('L',
				"OpMLabeling", true, true, "OpMLabeling",
				"sinfrasyntaxopersm2label", "Operation Labeling", 100, 45,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperLabeling.addModelingAttribute("labelId",
				new ElemAttribute("labelId", "String", AttributeType.OPERATION,
						false, "Label ID",
						"Identifier for the set of variables. Must"
								+ " be unique if multiple labeling exist.",
						"L1", 0, 5, "", "", -1, "", ""));

		infraSyntaxOpersM2OperLabeling.addModelingAttribute("outputSet",
				new ElemAttribute("outputSet", "Boolean",
						AttributeType.OPERATION, false, "Output Set",
						"Include set of variables in the constraint"
								+ " program call to obtain the variables",
						true, 0, 6, "", "", -1, "", ""));

		infraSyntaxOpersM2OperLabeling.addModelingAttribute("includeLabel",
				new ElemAttribute("includeLabel", "Boolean",
						AttributeType.OPERATION, true, "Include Label",
						"Include label in the constraint program."
								+ " In not selected, only define the"
								+ " set of variables", true, 0, 7, "", "", -1,
						"", ""));

		infraSyntaxOpersM2OperLabeling.addModelingAttribute("position",
				new ElemAttribute("position", "Integer",
						AttributeType.OPERATION, false, "Position", "", 1, 0,
						8, "includeLabel" + "#==#" + "true", "", -1, "", ""));

		infraSyntaxOpersM2OperLabeling.addModelingAttribute("once",
				new ElemAttribute("once", "Boolean", AttributeType.OPERATION,
						false, "Once",
						"Obtain only the first value for the variables"
								+ " included in this labeling. By default"
								+ " obtains the complete combination of"
								+ " values allowed", false, 0, 8,
						"includeLabel" + "#==#" + "true", "", -1, "", ""));

		infraSyntaxOpersM2OperLabeling.addModelingAttribute("order",
				new ElemAttribute("order", "Boolean", AttributeType.OPERATION,
						false, "Order",
						"Include sort function to order solutions."
								+ " Include an sort order function in the "
								+ "column on the right for each order"
								+ " meta-expression.", false, 0, 9,
						"includeLabel" + "#==#" + "true", "", -1, "", ""));

		infraSyntaxOpersM2OperLabeling.addModelingAttribute(
				"sortorder",
				new ElemAttribute("sortorder", "Set", AttributeType.SYNTAX,
						false, "sortorder", "", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1,
						"#\n", ""));

		infraSyntaxOpersM2OperLabeling.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4,
				"<<OpMLabeling>>\n#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n", "");

		InstConcept instInfraSyntaxOpersM2OperLabeling = new InstConcept(
				"OpMLabeling", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperLabeling);
		variabilityInstVertex.put("OpMLabeling",
				instInfraSyntaxOpersM2OperLabeling);

		SyntaxElement infraSyntaxOpersM2ExpType = new SyntaxElement('S',
				"OpMExpType", false, true, "OpMExpType", "whitebox",
				"Operation Expression Type", 100, 75,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2ExpType
				.addModelingAttribute(
						"suboperexptype",
						new ElemAttribute("suboperexptype", "Enumeration",
								AttributeType.OPERATION, false,
								"Expression Type", "", OpersSubOpExecType.class
										.getCanonicalName(), new InstAttribute(
										"enumName", new ElemAttribute(
												"EnumNameValue", "Enumeration",
												AttributeType.SYNTAX, false,
												"Value Name", "",
												OpersSubOpExecType.class
														.getCanonicalName(),
												"", "RELAXABLE", 1, -1, "", "",
												-1, "", ""), ""), "", 0, 6, "",
								"", 7, "", ""));

		InstConcept instInfraSyntaxOpersM2ExpType = new InstConcept(
				"OpMExpType", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2ExpType);
		variabilityInstVertex.put("OpMExpType", instInfraSyntaxOpersM2ExpType);

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

		// Its a meta-meta-attribute, no relation required
		// rel = new InstPairwiseRel(basicOpersM2AsoRel);
		// rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		// rel.setIdentifier("AssoSubAct-ExpType");
		// rel.setTargetRelation(instInfraSyntaxOpersM2ExpType, true);
		// rel.setSourceRelation(instInfraSyntaxOpersM2OperSubAction, true);
		// constraintInstEdges.put("AssoSubAct-ExpType", rel);
	}
}
