package com.variamos.dynsup.defaultmodels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.variamos.common.core.utilities.StringUtils;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribAttribute;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.OpersExpr;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.OpersComputationType;
import com.variamos.dynsup.types.OpersDefectType;
import com.variamos.dynsup.types.OpersExecType;
import com.variamos.dynsup.types.OpersOpType;
import com.variamos.dynsup.types.OpersSubOpExecType;
import com.variamos.dynsup.types.OpersSubOpType;
import com.variamos.dynsup.types.StringType;
import com.variamos.hlcl.model.domains.IntervalDomain;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.DefectAnalyzerModeEnum;

public class InfraSyntaxOpersMMM {

	public static void createSyntaxOpersMetaMetaModel(
			InstanceModel modelInstance) {

		Map<String, InstElement> variabilityInstVertex = modelInstance
				.getVariabilityVertex();

		Map<String, InstPairwiseRel> constraintInstEdges = modelInstance
				.getConstraintInstEdges();

		InstElement basicOpersSyntaxM3Node = modelInstance.getSyntaxModel()
				.getVertex("BsNode");

		InstElement basicOpersSyntaxM3Set = modelInstance.getSyntaxModel()
				.getVertex("BsCollection");

		InstElement basicOpersSyntaxM3Tree = modelInstance.getSyntaxModel()
				.getVertex("BsCollection");

		InstElement basicOpersSyntaxM3Gen = modelInstance.getSyntaxModel()
				.getVertex("BsGeneralization");

		InstElement infraBasicSyntaxOpersM3Arrow = modelInstance
				.getSyntaxModel().getVertex("BsArrow");

		InstElement basicOpersSyntaxM3Enum = modelInstance.getSyntaxModel()
				.getVertex("BsEnum");

		List<Integer> dom = new ArrayList<Integer>();
		dom.add(2);
		dom.add(4);
		IntervalDomain d = new IntervalDomain();
		d.setRangeValues(dom);

		// Begin Opers M2 Model

		SyntaxElement infraSyntaxOpersM2Element = new SyntaxElement('C',
				"SeMElement", false, true, "SeMElement",
				"infrasyntaxm2concept", "Operations MetaMetaElement", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2Element.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SeMElement>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n",
				"");

		InstConcept instInfraSyntaxOpersM2Element = new InstConcept(
				"SeMElement", basicOpersSyntaxM3Node, infraSyntaxOpersM2Element);

		variabilityInstVertex.put("SeMElement", instInfraSyntaxOpersM2Element);

		SyntaxElement infraSyntaxM2Asso = new SyntaxElement('I',
				"SeMAssociation", true, true, "SeMAssociation", "",
				"View-Concept Association", 150, 40,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instinfraSyntaxM2Asso = new InstConcept("SeMAssociation",
				infraBasicSyntaxOpersM3Arrow, infraSyntaxM2Asso);
		variabilityInstVertex.put("SeMAssociation", instinfraSyntaxM2Asso);

		SyntaxElement infraSyntaxM2NormalRelation = new SyntaxElement('P',
				"SeMAssociation", false, true, "Normal Relation",
				"defaultAsso", "Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		InstPairwiseRel rel = new InstPairwiseRel();
		constraintInstEdges.put("asso-e-e1", rel);
		rel.setIdentifier("asso-e-e1");
		rel.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		rel.setTargetRelation(instinfraSyntaxM2Asso, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Element, true);

		rel = new InstPairwiseRel();
		constraintInstEdges.put("asso-e-e2", rel);
		rel.setIdentifier("asso-e-e2");
		rel.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		rel.setTargetRelation(instInfraSyntaxOpersM2Element, true);
		rel.setSourceRelation(instinfraSyntaxM2Asso, true);

		SyntaxElement infraSyntaxM2ExtendsRelation = new SyntaxElement('X',
				"SeMGeneralization", true, true, "SeMGeneralization",
				"infrasyntaxm2microconcept", "Extend relation", 80, 50,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxM2ExtendsRelation.addModelingAttribute("Name",
				new ElemAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", "", 0, -1, "", "", 1,
						"<<SeMGeneralization>>\n#Name#all#\n\n", ""));

		InstConcept instInfraSyntaxM2ExtendsRelation = new InstConcept(
				"SeMGeneralization", infraBasicSyntaxOpersM3Arrow,
				infraSyntaxM2ExtendsRelation);

		variabilityInstVertex.put("SeMGeneralization",
				instInfraSyntaxM2ExtendsRelation);

		InstPairwiseRel instEdge = new InstPairwiseRel();
		constraintInstEdges.put("ce-e-c", instEdge);
		instEdge.setIdentifier("ce-e-c");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Element, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ExtendsRelation, true);

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("ce-c-e", instEdge);
		instEdge.setIdentifier("ce-c-e");
		instEdge.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2ExtendsRelation, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Element, true);

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

		infraSyntaxOpersM2InfraConcept.addModelingAttribute("exprs",
				new ElemAttribute("exprs", "InstanceSet", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expr.", "", "SeExpr", "",
						new ArrayList<InstAttribute>(), null, "", 0, -1, "",
						"", -1, "", ""));

		InstConcept instInfraSyntaxOpersM2nmConcept = new InstConcept(
				"SeMnmConcept", basicOpersSyntaxM3Node,
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
				"SeMConcept", basicOpersSyntaxM3Node, infraSyntaxOpersM2Concept);

		variabilityInstVertex.put("SeMConcept", instInfraSyntaxOpersM2Concept);

		// SyntaxPairwiseRel metaMetaPairwiseRelExtends = new SyntaxPairwiseRel(
		// "ExtendsRelation",
		// false,
		// true,
		// "Extends Relation",
		// "refasextends",
		// "Extends relation: relates to concepts to extend attributes and operation constraints",
		// 50, 50, "/com/variamos/gui/perspeditor/images/plnode.png", 1,
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
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SeMnmBinary>>\n"// "<<SeMnmRel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n"
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
						InstAttribute.class.getCanonicalName(), "OpersExpr",
						new ArrayList<InstAttribute>(), null, "", 0, -1, "",
						"", -1, "", ""));

		InstConcept instInfraSyntaxOpersM2PWRel = new InstConcept("SeMnmPWRel",
				basicOpersSyntaxM3Node, infraSyntaxOpersM2PWRel);
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("SeMnmPWRel", instInfraSyntaxOpersM2PWRel);

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
								.getCanonicalName(), "SemanticElement",
						new ArrayList<InstAttribute>(), null, "", 0, -1, "",
						"", -1, "", ""));

		infraSyntaxOpersM2InfraOTRel.addModelingAttribute(
				"opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "semanticExpressions", "", InstAttribute.class
								.getCanonicalName(), "OpersExpr",
						new ArrayList<InstAttribute>(), null, "", 0, -1, "",
						"", -1, "", ""));

		infraSyntaxOpersM2InfraOTRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SeMnmN-ary>>\n" // "<<SeMnmOTRel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n"
						+ "<<non-modifiable>>" + "\n\n", "");

		InstConcept instInfraSyntaxOpersM2nmOTRel = new InstConcept(
				"SeMnmOTRel", basicOpersSyntaxM3Node,
				infraSyntaxOpersM2InfraOTRel);
		variabilityInstVertex.put("SeMnmOTRel", instInfraSyntaxOpersM2nmOTRel);

		SyntaxElement infraSyntaxM2NaryEleGen = new SyntaxElement('P',
				"SeMNaryEleGen", false, true, "Normal Relation",
				"refasextends", "Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("SeMNaryEleGen", new InstPairwiseRel(
				"SeMNaryEleGen", infraSyntaxM2NaryEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-NaryEleGen", instEdge);
		instEdge.setIdentifier("co-NaryEleGen");
		instEdge.setTransSupInstElement(basicOpersSyntaxM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM2NaryEleGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Element, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2nmOTRel, true);

		SyntaxElement infraSyntaxM2BinaryEleGen = new SyntaxElement('P',
				"SeMBinaryEleGen", false, true, "Normal Relation",
				"refasextends", "Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("SeMBinaryEleGen", new InstPairwiseRel(
				"SeMBinaryEleGen", infraSyntaxM2BinaryEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-BinaryEleGen", instEdge);
		instEdge.setIdentifier("co-BinaryEleGen");
		instEdge.setTransSupInstElement(basicOpersSyntaxM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM2BinaryEleGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Element, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2PWRel, true);

		SyntaxElement infraSyntaxM2NodeEleGen = new SyntaxElement('P',
				"SeMNodeEleGen", false, true, "Normal Relation",
				"refasextends", "Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("SeMNodeEleGen", new InstPairwiseRel(
				"SeMNodeEleGen", infraSyntaxM2NodeEleGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-NodeEleGen", instEdge);
		instEdge.setIdentifier("co-NodeEleGen");
		instEdge.setTransSupInstElement(basicOpersSyntaxM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM2NodeEleGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Element, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2nmConcept, true);

		SyntaxElement basicOpersSyntaxM2nmCollection = new SyntaxElement('C',
				"SeMnmCollection", true, true, "SeMnmCollection",
				"infrasyntaxm2grayconcept", "Meta Meta Meta Element", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM2nmCollection.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 2, "", "", 1, // "<<BsSet>>\n#"
				"<<SeMnmCollection>>\n#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n<<non-modifiable>>\n", "");

		basicOpersSyntaxM2nmCollection.addModelingAttribute("name", "String",
				false, "Name", "", "", 0, 2, "", "", -1, "", "");

		InstConcept instInfraSyntaxOpersM2nmCollection = new InstConcept(
				"SeMnmCollection", basicOpersSyntaxM3Node,
				basicOpersSyntaxM2nmCollection);
		instInfraSyntaxOpersM2nmCollection.createInstAttributes(null);
		instInfraSyntaxOpersM2nmCollection.copyValuesToInstAttributes(null);
		variabilityInstVertex.put("SeMnmCollection",
				instInfraSyntaxOpersM2nmCollection);

		SyntaxElement infraSyntaxM2ElenmColl = new SyntaxElement('I',
				"SeMEleNmColl", true, true, "SeMEleNmColl", "",
				"Element Collection Association", 150, 40,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instinfraSyntaxM2ElenmColl = new InstConcept(
				"SeMEleNmColl", infraBasicSyntaxOpersM3Arrow,
				infraSyntaxM2ElenmColl);
		variabilityInstVertex.put("SeMEleNmColl", instinfraSyntaxM2ElenmColl);

		rel = new InstPairwiseRel();
		constraintInstEdges.put("ele-e-nmcoll1", rel);
		rel.setIdentifier("ele-e-nmcoll1");
		rel.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		rel.setTargetRelation(instinfraSyntaxM2ElenmColl, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);

		rel = new InstPairwiseRel();
		constraintInstEdges.put("ele-e-nmcoll2", rel);
		rel.setIdentifier("ele-e-nmcoll2");
		rel.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		rel.setTargetRelation(instInfraSyntaxOpersM2nmCollection, true);
		rel.setSourceRelation(instinfraSyntaxM2ElenmColl, true);

		SyntaxElement basicOpersSyntaxM2Collection = new SyntaxElement('C',
				"SeMCollection", true, true, "SeMCollection",
				"infrasyntaxm2concept", "Meta Meta Meta Element", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM2Collection.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 2, "", "", 1, // "<<BsSet>>\n#"
				"<<SeMCollection>>\n#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n\n", "");

		InstConcept instInfraSyntaxOpersM2Collection = new InstConcept(
				"SeMCollection", basicOpersSyntaxM3Node,
				basicOpersSyntaxM2Collection);
		instInfraSyntaxOpersM2Collection.createInstAttributes(null);
		instInfraSyntaxOpersM2Collection.copyValuesToInstAttributes(null);
		variabilityInstVertex.put("SeMCollection",
				instInfraSyntaxOpersM2Collection);

		SyntaxElement infraSyntaxM2EleColl = new SyntaxElement('I',
				"SeMEleNmColl", true, true, "SeMEleNmColl", "",
				"Element Collection Association", 150, 40,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instinfraSyntaxM2EleColl = new InstConcept("SeMEleColl",
				infraBasicSyntaxOpersM3Arrow, infraSyntaxM2EleColl);
		variabilityInstVertex.put("SeMEleColl", instinfraSyntaxM2EleColl);

		rel = new InstPairwiseRel();
		constraintInstEdges.put("ele-e-coll1", rel);
		rel.setIdentifier("ele-e-coll1");
		rel.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		rel.setTargetRelation(instinfraSyntaxM2EleColl, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);

		rel = new InstPairwiseRel();
		constraintInstEdges.put("ele-e-coll2", rel);
		rel.setIdentifier("ele-e-coll2");
		rel.setEdSyntaxEle(infraSyntaxM2NormalRelation);
		rel.setTargetRelation(instInfraSyntaxOpersM2Collection, true);
		rel.setSourceRelation(instinfraSyntaxM2EleColl, true);

		SyntaxElement infraSyntaxM2nmCollGen = new SyntaxElement('P',
				"SeMnmCollGen", false, true, "Normal Relation", "refasextends",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("SeMnmCollGen", new InstPairwiseRel(
				"SeMNodeEleGen", infraSyntaxM2nmCollGen));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-SeMnmCollGen", instEdge);
		instEdge.setIdentifier("co-SeMnmCollGen");
		instEdge.setTransSupInstElement(basicOpersSyntaxM3Gen);
		instEdge.setEdSyntaxEle(infraSyntaxM2nmCollGen);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2nmCollection, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Collection, true);

		SyntaxElement infraSyntaxOpersM2RelType = new SyntaxElement('A',
				"SeMRelTypes", false, false, "SeMRelTypes",
				"infrasyntaxm2minigrayconcept",
				"Relation Type for Binary and N-ary relations", 150, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2RelType.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n", "");

		infraSyntaxOpersM2RelType.addModelingAttribute("sourceLowCard",
				"String", false, "sourceLowCard", "", "", 0, 4, "", "", 4,
				"#sourceLowCard#all#\n", "");
		infraSyntaxOpersM2RelType.addModelingAttribute("sourceHighCard",
				"String", false, "sourceHighCard", "", "", 0, 4, "", "", 4,
				"#sourceHighCard#all#\n", "");
		infraSyntaxOpersM2RelType.addModelingAttribute("targetLowCard",
				"String", false, "targetLowCard", "", "", 0, 4, "", "", 4,
				"#targetLowCard#all#\n", "");
		infraSyntaxOpersM2RelType.addModelingAttribute("targetHighCard",
				"String", false, "targetHighCard", "", "", 0, 4, "", "", 4,
				"#targetHighCard#all#\n", "");

		infraSyntaxOpersM2RelType.addModelingAttribute("exprs",
				new ElemAttribute("exprs", "InstaceSet", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expr.", "", "SeExpr",
						"Boolean", new ArrayList<InstAttribute>(), null, "", 0,
						-1, "", "", -1, "", ""));

		InstConcept instInfraSyntaxOpersM2RelType = new InstConcept(
				"SeMRelTypes", basicOpersSyntaxM3Set, infraSyntaxOpersM2RelType);
		variabilityInstVertex.put("SeMRelTypes", instInfraSyntaxOpersM2RelType);

		OpersConcept basicOpersM2AsoRel = new OpersConcept("AsoRel");

		InstConcept instBasicOpersM2AsoRel = new InstConcept("SeMAsoEdge",
				basicOpersSyntaxM3Node, basicOpersM2AsoRel);

		SyntaxElement infraSyntaxOpersM2AsoRel = new SyntaxElement('P',
				"SeMAsoEdge", false, true, "Association Relation",
				"defaultAsso", "Association Relation: ", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1,
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

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssonmBintoRelType");
		rel.setTargetRelation(instInfraSyntaxOpersM2RelType, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2PWRel, true);
		constraintInstEdges.put("AssonmBintoRelType", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssonmNarytoRelType");
		rel.setTargetRelation(instInfraSyntaxOpersM2RelType, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2nmOTRel, true);
		constraintInstEdges.put("AssonmNraytoRelType", rel);

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
								.getCanonicalName(), "SemanticElement",
						new ArrayList<InstAttribute>(), null, "", 0, -1, "",
						"", -1, "", ""));

		metaMetaPairwiseRel.addModelingAttribute("opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expr.", "",
						InstAttribute.class.getCanonicalName(), "OpersExpr",
						new ArrayList<InstAttribute>(), null, "", 0, -1, "",
						"", -1, "", ""));

		metaMetaPairwiseRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SeMBinary>>\n"// "<<SeMPWRel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n",
				"");

		InstConcept instPairWiseRelation = new InstConcept("SeMPWRel",
				basicOpersSyntaxM3Node, metaMetaPairwiseRel);
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
								.getCanonicalName(), "OpersExpr",
						new ArrayList<InstAttribute>(), null, "", 0, -1, "",
						"", -1, "", ""));

		infraSyntaxOpersM2OTRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SeMN-ary>>\n" // "<<SeMOTRel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n",
				"");

		InstConcept instInfraSyntaxOpersM2OTRel = new InstConcept("SeMOTRel",
				basicOpersSyntaxM3Node, infraSyntaxOpersM2OTRel);
		variabilityInstVertex.put("SeMOTRel", instInfraSyntaxOpersM2OTRel);

		SyntaxElement basicOpersSyntaxM3TypesEnum = new SyntaxElement('E',
				"TypesEnum", false, true, "TypesEnum", "infrasyntaxm2concept",
				"Enum", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instInfraSyntaxOpersM3TypesEnum = new InstConcept(
				"TypesEnum", basicOpersSyntaxM3Enum,
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
		// enumVals.add(new InstAttribute("enum8", new
		// ElemAttribute("EnumValue",
		// StringType.IDENTIFIER, AttributeType.SYNTAX, false,
		// "Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
		// "ClassSet"));
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

		variabilityInstVertex.put("TypesEnum", instInfraSyntaxOpersM3TypesEnum);

		OpersConcept basicOpersM2ExtRel = new OpersConcept("ExtRel");

		InstConcept instBasicOpersM2ExtRel = new InstConcept("ExtendsRelation",
				basicOpersSyntaxM3Node, basicOpersM2ExtRel);

		// variabilityInstVertex.put("TypeEnumeration", new InstConcept(
		// "TypeEnumeration", metaBasicConcept, enumeration));

		SyntaxElement infraSyntaxOpersM2ExtRel = new SyntaxElement(
				'P',
				"ExtendsRelation",
				false,
				true,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/perspeditor/images/plnode.png", 1,
				instBasicOpersM2ExtRel);

		infraSyntaxOpersM2ExtRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("SeMExtCEdge");
		rel.setTransSupInstElement(basicOpersSyntaxM3Gen);
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

		SyntaxElement infraSyntaxOpersnmCEdge = new SyntaxElement(
				'P',
				"ExtendsRelation",
				false,
				true,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/perspeditor/images/plnode.png", 1,
				instBasicOpersM2ExtRel);

		infraSyntaxOpersnmCEdge.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersnmCEdge);
		rel.setIdentifier("SeMExtnmCEdge");
		rel.setTransSupInstElement(basicOpersSyntaxM3Gen);
		rel.setTargetRelation(instInfraSyntaxOpersM2nmConcept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("SeMExtnmCEdge", rel);

		SyntaxElement infraSyntaxOpersOTCEdge = new SyntaxElement(
				'P',
				"ExtendsRelation",
				false,
				true,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/perspeditor/images/plnode.png", 1,
				instBasicOpersM2ExtRel);

		infraSyntaxOpersOTCEdge.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersOTCEdge);
		rel.setIdentifier("SeMExtOTCEdge");
		rel.setTransSupInstElement(basicOpersSyntaxM3Gen);
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("SeMExtOTCEdge", rel);

		SyntaxElement infraSyntaxOpersOTOdge = new SyntaxElement(
				'P',
				"ExtendsRelation",
				false,
				true,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/perspeditor/images/plnode.png", 1,
				instBasicOpersM2ExtRel);

		infraSyntaxOpersOTOdge.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersOTOdge);
		rel.setIdentifier("SeMExtOTOdge");
		rel.setTransSupInstElement(basicOpersSyntaxM3Gen);
		rel.setTargetRelation(instInfraSyntaxOpersM2OTRel, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("SeMExtOTEdge", rel);

		SyntaxElement infraSyntaxOpersOTEdge = new SyntaxElement(
				'P',
				"ExtendsRelation",
				false,
				true,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/perspeditor/images/plnode.png", 1,
				instBasicOpersM2ExtRel);

		infraSyntaxOpersOTEdge.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersOTEdge);
		rel.setIdentifier("SeMExtnmOTEdge");
		rel.setTransSupInstElement(basicOpersSyntaxM3Gen);
		rel.setTargetRelation(instInfraSyntaxOpersM2nmOTRel, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("MExtnmOTEdge", rel);

		SyntaxElement infraSyntaxOpersPIPRel = new SyntaxElement(
				'P',
				"ExtendsRelation",
				false,
				true,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/perspeditor/images/plnode.png", 1,
				instBasicOpersM2ExtRel);

		infraSyntaxOpersPIPRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersPIPRel);
		rel.setIdentifier("ExtendsPIPRel");
		rel.setTransSupInstElement(basicOpersSyntaxM3Gen);
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

		infraSyntaxOpersM2OperGroup.addModelingAttribute("opgname",
				new ElemAttribute("opgname", "String", AttributeType.OPERATION,
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
				"OpMOperGroup", basicOpersSyntaxM3Node,
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
						false, "Name", "", null, 0, 6, "", "", -1, "", ""));

		infraSyntaxOpersM2MetaModel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<SeMParadigm>>\n"// "<<SeMModel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n",
				"");

		InstConcept instInfraSyntaxOpersM2MetaModel = new InstConcept(
				"SeMModel", basicOpersSyntaxM3Node, infraSyntaxOpersM2MetaModel);
		variabilityInstVertex.put("SeMModel", instInfraSyntaxOpersM2MetaModel);

		SyntaxElement infraSyntaxOpersM2OperAction = new SyntaxElement('A',
				"OpMOperation", true, true, "OpMOperation",
				"sinfrasyntaxopersm2oper",
				"Is an executable action over a visual model composed of one"
						+ " or several suboperations", 100, 75,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperAction.addModelingAttribute("operType",
				new ElemAttribute("operType", "Enumeration",
						AttributeType.OPERATION, true, "Operation Type",
						"Sequential execution (verification, validation)",
						OpersOpType.class.getCanonicalName(),
						OpersOpType.Verification, "", 0, 4, "", "", 12,
						"\ntype: #" + "operType" + "#all#", ""));
		infraSyntaxOpersM2OperAction
				.addModelingAttribute(
						"compType",
						new ElemAttribute(
								"compType",
								"Enumeration",
								AttributeType.OPERATION,
								false,
								"Quotient type",
								"Type of quotient applied between suboper1 and suboper2: "
										+ "Simple quotient (suboper1/suboper2); "
										+ "one less quotient ( 1 - suboper1/suboper2); "
										+ "quotient denomination exp base 2 (suboper1/ (2 exp suboper2) ) ",
								OpersComputationType.class.getCanonicalName(),
								"", "", 0, 11,
								"operType#==#Computational analysis# ",
								"operType#==#Computational analysis# ", 15,
								"\n #" + "compType" + "#all#", ""));

		infraSyntaxOpersM2OperAction.addModelingAttribute(
				"execType",
				new ElemAttribute("execType", "Enumeration",
						AttributeType.OPERATION, false, "Execution Type",
						"Currently ignored by the implementation "
								+ "(on_demand assumed)", OpersExecType.class
								.getCanonicalName(), "on demand", "", 0, 5,
						"false", "", -1, "", ""));
		infraSyntaxOpersM2OperAction.addModelingAttribute("opname",
				new ElemAttribute("opname", "String", AttributeType.OPERATION,
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
						+ "#all#", "");

		InstConcept instInfraSyntaxOpersM2OperAction = new InstConcept(
				"OpMOperation", basicOpersSyntaxM3Node,
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
						"type#==#Multi verification# ", 13, "\nindivVerExp: #"
								+ "indivVerExp" + "#all#", ""));
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
								"type#==#Multi verification# ", 12,
								"\nindivRelExp: #" + "indivRelExp" + "#all#",
								""));

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
								DefectAnalyzerModeEnum.class.getCanonicalName(),
								StringUtils
										.formatEnumValue(DefectAnalyzerModeEnum.INCOMPLETE_FAST
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
				"OpMSubOper", basicOpersSyntaxM3Node,
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
				"OpMLabeling", basicOpersSyntaxM3Node,
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
				"OpMExpType", basicOpersSyntaxM3Node, infraSyntaxOpersM2ExpType);
		variabilityInstVertex.put("OpMExpType", instInfraSyntaxOpersM2ExpType);

		SyntaxElement infraSyntaxOpersM2TreeSort = new SyntaxElement('A',
				"OpMTreeSort", false, false, "OpMTreeSort",
				"infrasyntaxm2minigrayconcept",
				"Sorting expressions for the labeling", 150, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2TreeSort.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n", "");

		infraSyntaxOpersM2TreeSort.addModelingAttribute("semConcept",
				new ElemAttribute("semConcept", "Class", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expr.", "",
						OpersElement.class.getCanonicalName(), "All", "", null,
						"", 0, -1, "", "", -1, "", ""));
		infraSyntaxOpersM2TreeSort.addModelingAttribute("exprs",
				new ElemAttribute("exprs", "Class", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expr.", "",
						OpersExpr.class.getCanonicalName(), "Numerical", "",
						null, "", 0, -1, "", "", -1, "", ""));

		InstConcept instInfraSyntaxOpersM2TreeSort = new InstConcept(
				"OpMTreeSort", basicOpersSyntaxM3Tree,
				infraSyntaxOpersM2TreeSort);
		variabilityInstVertex
				.put("OpMTreeSort", instInfraSyntaxOpersM2TreeSort);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoOpLabeling-treeSort");
		rel.setTargetRelation(instInfraSyntaxOpersM2TreeSort, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperLabeling, true);
		constraintInstEdges.put("AssoOpLabeling-treeSort", rel);

		SyntaxElement infraSyntaxOpersM2TreeLab = new SyntaxElement('A',
				"OpMTreeLab", false, false, "OpMTreeLab",
				"infrasyntaxm2minigrayconcept", "Attributes for the labeling",
				150, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2TreeLab.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n", "");

		infraSyntaxOpersM2TreeLab.addModelingAttribute("seEle",
				new ElemAttribute("seEle", "Class", AttributeType.SYNTAX,
						false, "Operations Meta-Model ", "", "SemanticElement",
						"All", "", null, "", 0, -1, "", "", -1, "", ""));
		infraSyntaxOpersM2TreeLab.addModelingAttribute(
				"seAttrib",
				new ElemAttribute("seAttrib", "Class", AttributeType.SYNTAX,
						false, "Attributes ", "", ElemAttribAttribute.class
								.getCanonicalName(), "All", "", null, "", 0,
						-1, "", "", -1, "", ""));

		InstConcept instInfraSyntaxOpersM2TreeLab = new InstConcept(
				"OpMTreeLab", basicOpersSyntaxM3Tree, infraSyntaxOpersM2TreeLab);
		variabilityInstVertex.put("OpMTreeLab", instInfraSyntaxOpersM2TreeLab);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoOpLabeling-treeTreeLab");
		rel.setTargetRelation(instInfraSyntaxOpersM2TreeLab, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperLabeling, true);
		constraintInstEdges.put("AssoOpLabeling-treeLab", rel);

		SyntaxElement infraSyntaxOpersM2TreeSubOp = new SyntaxElement('A',
				"OpMTreeSubOp", false, false, "OpMTreeSubOp",
				"infrasyntaxm2minigrayconcept",
				"Attributes for the suboperations", 150, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2TreeSubOp.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n", "");

		infraSyntaxOpersM2TreeSubOp.addModelingAttribute("seEle",
				new ElemAttribute("seEle", "Class", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expr.", "",
						"SemanticElement", "All", "", null, "", 0, -1, "", "",
						-1, "", ""));
		infraSyntaxOpersM2TreeSubOp.addModelingAttribute("seAttrib",
				new ElemAttribute("seAttrib", "Class", AttributeType.SYNTAX,
						false, "Attribute Meta-Model Expr.", "",
						ElemAttribAttribute.class.getCanonicalName(), "All",
						"", null, "", 0, -1, "", "", -1, "", ""));

		InstConcept instInfraSyntaxOpersM2TreeSubOp = new InstConcept(
				"OpMTreeSubOp", basicOpersSyntaxM3Tree,
				infraSyntaxOpersM2TreeSubOp);
		variabilityInstVertex.put("OpMTreeSubOp",
				instInfraSyntaxOpersM2TreeSubOp);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoSubOp-treeSubOp");
		rel.setTargetRelation(instInfraSyntaxOpersM2TreeSubOp, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperSubAction, true);
		constraintInstEdges.put("AssoSubOp-treeSubOp", rel);

		SyntaxElement infraSyntaxOpersM2TreeExpType = new SyntaxElement('A',
				"OpMTreeExpType", false, false, "OpMTreeExpType",
				"infrasyntaxm2minigrayconcept",
				"Expressions for the expression type", 150, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2TreeExpType.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n", "");

		infraSyntaxOpersM2TreeExpType.addModelingAttribute("semConcept",
				new ElemAttribute("semConcept", "Class", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expr.", "",
						OpersElement.class.getCanonicalName(), "All", "", null,
						"", 0, -1, "", "", -1, "", ""));
		infraSyntaxOpersM2TreeExpType.addModelingAttribute("exprs",
				new ElemAttribute("exprs", "Class", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expr.", "",
						OpersExpr.class.getCanonicalName(), "Boolean", "",
						null, "", 0, -1, "", "", -1, "", ""));

		InstConcept instInfraSyntaxOpersM2TreeExpType = new InstConcept(
				"OpMTreeExpType", basicOpersSyntaxM3Tree,
				infraSyntaxOpersM2TreeExpType);
		variabilityInstVertex.put("OpMTreeExpType",
				instInfraSyntaxOpersM2TreeExpType);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoOpLabeling-treeExpType");
		rel.setTargetRelation(instInfraSyntaxOpersM2TreeExpType, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2ExpType, true);
		constraintInstEdges.put("AssoOpLabeling-treeExpType", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoSubOp-ExpType");
		rel.setTargetRelation(instInfraSyntaxOpersM2ExpType, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperSubAction, true);
		constraintInstEdges.put("AssoSubOp-ExpType", rel);

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

		SyntaxElement infraOpersM2Attribute = new SyntaxElement('A',
				"SeMAttribute", false, true, "SeMAttribute",
				"infrasyntaxm2bigconcept", "Attributes for the meta-concept",
				120, 120, "/com/variamos/gui/perspeditor/images/concept.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraOpersM2Attribute.addModelingAttribute("Name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", "InstAttribute", 0, 1, "", "", 1, "", ""));

		infraOpersM2Attribute
				.addModelingAttribute("type", new ElemAttribute("type",
						"Enumeration", AttributeType.SYNTAX, false, "Type", "",
						"TypeEnum", "", "", 0, 3, "", "", -1, "type"
								+ "#all#\n\n", ""));

		// attribute Type
		infraOpersM2Attribute.addModelingAttribute("displayType",
				new ElemAttribute("displayType", "Enumeration",
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

		infraOpersM2Attribute.addModelingAttribute("propTabPosition",
				"Integer", false, "Prop. Tab Position", "", "", 0, 12, "", "",
				-1, "#" + "propTabPosition" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("elementDisplayPosition",
				"Integer", false, "Element Disp. Position", "", "", 0, 13, "",
				"", -1, "#" + "elementDisplayPosition" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("elementDisplaySpacers",
				"String", false, "Element Disp. Spacers", "", "", 0, 14, "",
				"", -1, "#" + "elementDisplaySpacers" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("propTabEditionCondition",
				"String", false, "Prop. Tab Edition Cond.", "", "", 0, 15, "",
				"", -1, "#" + "propTabEditionCondition" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("propTabVisualCondition",
				"String", false, "Prop. Tab Visual Cond.", "", "", 0, 16, "",
				"", -1, "#" + "propTabVisualCondition" + "#all#\n\n", "");

		infraOpersM2Attribute.addModelingAttribute("elementDisplayCondition",
				"String", false, "Graph Visual Cond.", "", "", 0, 17, "", "",
				-1, "#" + "elementDisplayCondition" + "#all#\n\n", "");

		// FIXME use to generalize attributes such as XX to restrict the
		// domain to other attributes of concepts.
		infraOpersM2Attribute.addModelingAttribute("domFiltOwnFields",
				"String", false, "Filter domain (Own Fields)", "", "", 0, 18,
				"", "", -1, "#" + "domFiltOwnFields" + "#all#\n\n", "");

		// FIXME use to generalize attributes such as "SDReqLevel" to restrict
		// the domain to attribute of related concepts.
		infraOpersM2Attribute.addModelingAttribute("domFilTRelFields",
				"String", false, "Filter domain (Rel. Fields)", "", "", 0, 19,
				"", "", -1, "#" + "domFilTRelFields" + "#all#\n\n", "");

		// FIXME use to generalize attributes such as "SDReqLevel" to define the
		// default value dynamically with another attribute of the concept.
		infraOpersM2Attribute.addModelingAttribute("defDomValueField",
				"String", false, "Def. Domain (Filter Field)", "", "", 0, 20,
				"", "", -1, "#" + "defDomValueField" + "#all#\n\n", "");

		InstConcept instInfraSyntaxOpersM2Attribute = new InstConcept(
				"SeMAttribute", basicOpersSyntaxM3Set, infraOpersM2Attribute);
		variabilityInstVertex.put("SeMAttribute",
				instInfraSyntaxOpersM2Attribute);

		SyntaxElement infraSyntaxM2ConAtt = new SyntaxElement('P', "SeMConAtt",
				false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("SeMConAtt", new InstPairwiseRel("SeMConAtt",
				infraSyntaxM2ConAtt));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("co-att", instEdge);
		instEdge.setIdentifier("co-att");
		instEdge.setEdSyntaxEle(infraSyntaxM2ConAtt);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Attribute, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Element, true);

		SyntaxElement infraSyntaxM2SetAtt = new SyntaxElement('P', "SeMSetAtt",
				false, true, "Normal Relation", "defaultAsso",
				"Concept-Concept relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/plnode.png", 1, null);

		constraintInstEdges.put("SeMSetAtt", new InstPairwiseRel("SeMSetAtt",
				infraSyntaxM2ConAtt));

		instEdge = new InstPairwiseRel();
		constraintInstEdges.put("set-att", instEdge);
		instEdge.setIdentifier("set-att");
		instEdge.setEdSyntaxEle(infraSyntaxM2SetAtt);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Attribute, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2nmCollection, true);

		SyntaxElement opersM2ComputationTypeEnum = new SyntaxElement('E',
				"OpersComputationType", false, true, "OpersComputationType",
				"infrasyntaxm2concept", "Enum", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instOpersM2ComputationTypeEnum = new InstConcept(
				"OpersComputationType", basicOpersSyntaxM3Enum,
				opersM2ComputationTypeEnum);

		enumVals = new ArrayList<InstAttribute>();
		instOpersM2ComputationTypeEnum.getInstAttribute("enumValues").setValue(
				enumVals);
		enumVals.add(new InstAttribute("enum1", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Simple_Quotient"));
		enumVals.add(new InstAttribute("enum2", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"One_Less_Quotient"));
		enumVals.add(new InstAttribute("enum3", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Quotient_Denominator_Exp_Base_2"));

		variabilityInstVertex.put("OpersComputationType",
				instOpersM2ComputationTypeEnum);

		SyntaxElement opersM2OperExpTypeEnum = new SyntaxElement('E',
				"OpersOperExpType", false, true, "OpersOperExpType",
				"infrasyntaxm2concept", "Enum", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instOpersM2OperExpTypeEnum = new InstConcept(
				"OpersOperExpType", basicOpersSyntaxM3Enum,
				opersM2OperExpTypeEnum);

		enumVals = new ArrayList<InstAttribute>();
		instOpersM2OperExpTypeEnum.getInstAttribute("enumValues").setValue(
				enumVals);
		enumVals.add(new InstAttribute("enum1", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Verification"));
		enumVals.add(new InstAttribute("enum2", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Relaxable"));
		enumVals.add(new InstAttribute("enum3", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Normal"));
		enumVals.add(new InstAttribute("enum4", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"ToVerify"));

		variabilityInstVertex.put("OpersOperExpType",
				instOpersM2OperExpTypeEnum);

		SyntaxElement opersM2OpTypeEnum = new SyntaxElement('E', "OpersOpType",
				false, true, "OpersOpType", "infrasyntaxm2concept", "Enum",
				120, 120, "/com/variamos/gui/perspeditor/images/concept.png",
				true, Color.BLUE.toString(), 3, null, true);

		InstConcept instOpersM2OpTypeEnum = new InstConcept("OpersOpType",
				basicOpersSyntaxM3Enum, opersM2OpTypeEnum);

		enumVals = new ArrayList<InstAttribute>();
		instOpersM2OpTypeEnum.getInstAttribute("enumValues").setValue(enumVals);
		enumVals.add(new InstAttribute("enum1", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Computational_Analysis"));
		enumVals.add(new InstAttribute("enum2", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Verification"));
		enumVals.add(new InstAttribute("enum3", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Export"));
		enumVals.add(new InstAttribute("enum4", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Validation"));

		variabilityInstVertex.put("OpersOpType", instOpersM2OpTypeEnum);

		SyntaxElement opersM2OperTypeEnum = new SyntaxElement('E',
				"OpersOperType", false, true, "OpersOperType",
				"infrasyntaxm2concept", "Enum", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instOpersM2OperTypeEnum = new InstConcept("OpersOperType",
				basicOpersSyntaxM3Enum, opersM2OperTypeEnum);

		enumVals = new ArrayList<InstAttribute>();
		instOpersM2OperTypeEnum.getInstAttribute("enumValues").setValue(
				enumVals);
		enumVals.add(new InstAttribute("enum1", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"First_Solutions"));
		enumVals.add(new InstAttribute("enum2", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Iterate_Solutions"));
		enumVals.add(new InstAttribute("enum3", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Number_Solutions"));
		enumVals.add(new InstAttribute("enum4", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Export_Solutions"));
		enumVals.add(new InstAttribute("enum5", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"UpdMode_Defect_Verif"));
		enumVals.add(new InstAttribute("enum5", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"IdDef_Devect_Verif"));
		enumVals.add(new InstAttribute("enum6", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"Multi_Verification"));

		variabilityInstVertex.put("OpersOperType", instOpersM2OperTypeEnum);

		SyntaxElement opersM2DefectTypeEnum = new SyntaxElement('E',
				"OpersDefectType", false, true, "OpersDefectType",
				"infrasyntaxm2concept", "Enum", 120, 120,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		InstConcept instOpersM2DefectTypeEnum = new InstConcept(
				"OpersDefectType", basicOpersSyntaxM3Enum,
				opersM2DefectTypeEnum);

		enumVals = new ArrayList<InstAttribute>();
		instOpersM2DefectTypeEnum.getInstAttribute("enumValues").setValue(
				enumVals);
		enumVals.add(new InstAttribute("enum1", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"getDeadElements"));
		enumVals.add(new InstAttribute("enum2", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"getFalseOptionalElements"));
		enumVals.add(new InstAttribute("enum3", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"getRedundancies"));
		enumVals.add(new InstAttribute("enum4", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"getNonAttainableDomains"));
		enumVals.add(new InstAttribute("enum5", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"getFalsePLs"));
		enumVals.add(new InstAttribute("enum5", new ElemAttribute("EnumValue",
				StringType.IDENTIFIER, AttributeType.SYNTAX, false,
				"Enumeration Value", "", "", 1, -1, "", "", -1, "", ""),
				"getVoids"));

		variabilityInstVertex.put("OpersDefectType", instOpersM2DefectTypeEnum);
	}
}
