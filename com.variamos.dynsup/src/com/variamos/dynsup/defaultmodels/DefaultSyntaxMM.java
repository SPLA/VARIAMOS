package com.variamos.dynsup.defaultmodels;

import java.awt.Color;
import java.util.ArrayList;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.SyntaxConcept;
import com.variamos.dynsup.model.SyntaxPairwiseRel;
import com.variamos.dynsup.model.SyntaxView;
import com.variamos.dynsup.types.AttributeType;

public class DefaultSyntaxMM {
	public static void createSyntaxMetaModel(ModelInstance refas) {

		SyntaxView syntaxMetaView = null;

		InstElement metaView = refas.getSyntaxModel().getVertex("SMMView");

		InstElement supportMetaElementConcept = refas.getSyntaxModel()
				.getVertex("SMMConcept");
		InstElement supportMetaElementOverTwo = refas.getSyntaxModel()
				.getVertex("SMMOverTwoRelation");

		InstElement supportMetaElementPairwise = refas.getSyntaxModel()
				.getVertex("SMMPairwiseRelation");

		InstElement supportMetaExtendsPairwise = refas.getSyntaxModel()
				.getVertex("SMMExtendRelation");

		InstElement supportMetaViewPairwise = refas.getSyntaxModel().getVertex(
				"SMMViewConceptAsso");

		InstPairwiseRel metaPairwiseRelNormal = ((InstPairwiseRel) refas
				.getSyntaxModel().getConstraintInstEdge("SMMNormalRelation"));

		// Model concept

		InstConcept semREFAS = ((InstConcept) refas.getOperationalModel()
				.getVertex("REFAS"));

		SyntaxConcept syntaxRefas = new SyntaxConcept('C', "REFAS", false,
				true, "REFAS", "plnode", "Applies REFAS", 100, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semREFAS, true);

		InstConcept instRefas = new InstConcept("REFAS",
				supportMetaElementConcept, syntaxRefas);

		refas.getVariabilityVertex().put("REFAS", instRefas);

		// *************************---------------****************************
		// *************************---------------****************************
		// Goals and Variability model

		syntaxMetaView = new SyntaxView("Variability", true, true,
				"Variability View", "plnode", "Defines a feature", 130, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Goals Palette;Feature Palette", 1, null);

		InstPairwiseRel directExtendsSemanticEdge = refas.getOperationalModel()
				.getConstraintInstEdge("extendsPWAsso");

		InstPairwiseRel directViewSemanticEdge = refas.getOperationalModel()
				.getConstraintInstEdge("viewPWAsso");

		SyntaxPairwiseRel metaExtendsRel = new SyntaxPairwiseRel(
				"ExtendsRelation", false, true, "ExtendsRelation", "",
				"Extends relation between two hard concepts. Extends syntatic and semantic"
						+ "attributes", 50, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directExtendsSemanticEdge);

		SyntaxPairwiseRel metaViewRel = new SyntaxPairwiseRel("ViewRelation",
				false, true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);

		InstConcept instViewC = new InstConcept("Variability", metaView,
				syntaxMetaView);

		refas.getVariabilityVertex().put("Variability", instViewC);

		InstConcept semFeature = ((InstConcept) refas.getOperationalModel()
				.getVertex("Feature"));

		InstConcept semHardConcept = ((InstConcept) refas.getOperationalModel()
				.getVertex("HardConcept"));

		InstConcept semGoal = ((InstConcept) refas.getOperationalModel()
				.getVertex("Goal"));

		SyntaxConcept syntaxFeature = new SyntaxConcept('C', "Feature", false,
				true, "Feature", "plnode", "Defines a feature", 100, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semFeature, true);

		syntaxFeature.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		syntaxFeature.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		syntaxFeature.addModelingAttribute("name", "String", false, "Name", "",
				"", 0, 3, "", "", 3, "", "");

		// syntaxFeature.addPanelVisibleAttribute("03#" + "name");

		syntaxFeature.addPropEditableAttribute("03#" + "name");

		syntaxFeature.addPropVisibleAttribute("03#" + "name");

		// syntaxFeature.addModelingAttribute("concern", "ConcernLevel", false,
		// "Concern Level", "", 0, -1, "", "", -1, "", "");

		InstConcept instVertexF = new InstConcept("Feature",
				supportMetaElementConcept, syntaxFeature);
		refas.getVariabilityVertex().put("Feature", instVertexF);
		// syntaxMetaView.addConcept(syntaxFeature);

		SyntaxPairwiseRel metaViewF = new SyntaxPairwiseRel("ViewFeatRel",
				true, true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewF.setPalette("Feature Palette");

		InstConcept instViewF = new InstConcept("ViewFeatRel",
				supportMetaViewPairwise, metaViewF);
		refas.getVariabilityVertex().put("ViewFeatRel", instViewF);

		// instViewF.setInstAttribute("Palette", "Feature Palette");

		InstPairwiseRel instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vf-tof", instEdge);
		instEdge.setIdentifier("vf-tof");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instViewF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vf-fromview", instEdge);
		instEdge.setIdentifier("vf-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewF, true);
		instEdge.setSourceRelation(instViewC, true);

		SyntaxConcept syntaxVariabilityArtifact = new SyntaxConcept('C', "VA",
				false, true, "VariabilityArtifact", null, "", 0, 0, null, true,
				null, 3, semHardConcept, true);

		syntaxVariabilityArtifact.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		syntaxVariabilityArtifact.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		syntaxVariabilityArtifact.addModelingAttribute("name", "String", false,
				"Name", "", "", 0, -1, "", "", -1, "", "");

		// syntaxVariabilityArtifact.addPanelVisibleAttribute("03#" + "name");

		syntaxVariabilityArtifact.addPropEditableAttribute("03#" + "name");

		syntaxVariabilityArtifact.addPropVisibleAttribute("03#" + "name");

		// syntaxVariabilityArtifact.addModelingAttribute("concern",
		// "ConcernLevel", false, "Concern Level", "", 0, -1, "", "", -1,
		// "", "");

		InstConcept instVertexVA = new InstConcept("VA",
				supportMetaElementConcept, syntaxVariabilityArtifact);
		refas.getVariabilityVertex().put("VA", instVertexVA);

		SyntaxConcept syntaxRootFeature = new SyntaxConcept('C', "RootFeature",
				true, true, "RootFeature", "plnode", "Defines a root feature",
				100, 50, "/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semFeature, true);

		InstConcept instVertexRF = new InstConcept("RootFeature",
				supportMetaElementConcept, syntaxRootFeature);
		refas.getVariabilityVertex().put("RootFeature", instVertexRF);

		SyntaxConcept syntaxGeneralFeature = new SyntaxConcept('C',
				"GeneralFeature", true, true, "GeneralFeature", "plnode",
				"Defines a general feature", 100, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semFeature, true);

		InstConcept instVertexGF = new InstConcept("GeneralFeature",
				supportMetaElementConcept, syntaxGeneralFeature);
		refas.getVariabilityVertex().put("GeneralFeature", instVertexGF);

		SyntaxConcept syntaxVertexLF = new SyntaxConcept('C', "LeafFeature",
				true, true, "LeafFeature", "plnode", "Defines a leaf feature",
				100, 50, "/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semFeature, true);

		InstConcept instVertexLF = new InstConcept("LeafFeature",
				supportMetaElementConcept, syntaxVertexLF);
		refas.getVariabilityVertex().put("LeafFeature", instVertexLF);

		SyntaxPairwiseRel metaViewRF = new SyntaxPairwiseRel("ViewRootFeaRel",
				true, true, "ViewRelation", "",
				"View relation between a view and a concepts.", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);

		InstConcept instViewRF = new InstConcept("ViewRootFeaRel",
				supportMetaViewPairwise, metaViewRF);
		refas.getVariabilityVertex().put("ViewRootFeaRel", instViewRF);

		instViewRF.setInstAttribute("Palette", "Feature Palette");

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vrf-torf", instEdge);
		instEdge.setIdentifier("vrf-torf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexRF, true);
		instEdge.setSourceRelation(instViewRF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vrf-fromview", instEdge);
		instEdge.setIdentifier("vrf-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewRF, true);
		instEdge.setSourceRelation(instViewC, true);

		SyntaxPairwiseRel metaViewGF = new SyntaxPairwiseRel("ViewGFeatRel",
				true, true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);

		InstConcept instViewGF = new InstConcept("ViewGFeatRel",
				supportMetaViewPairwise, metaViewGF);
		refas.getVariabilityVertex().put("ViewGFeatRel", instViewGF);

		instViewGF.setInstAttribute("Palette", "Feature Palette");

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vgf-togf", instEdge);
		instEdge.setIdentifier("vgf-togf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGF, true);
		instEdge.setSourceRelation(instViewGF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vgf-fromview", instEdge);
		instEdge.setIdentifier("vgf-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewGF, true);
		instEdge.setSourceRelation(instViewC, true);

		SyntaxConcept syntaxGoal = new SyntaxConcept('C', "Goal", true, true,
				"Goal", "refasgoal", "Defines a goal of the system"
						+ " from the stakeholder perspective that can be"
						+ " satisfied with a clear cut condition", 120, 60,
				"/com/variamos/gui/perspeditor/images/goal.png", true,
				Color.BLUE.toString(), 3, semGoal, true);

		InstConcept instVertexG = new InstConcept("Goal",
				supportMetaElementConcept, syntaxGoal);
		refas.getVariabilityVertex().put("Goal", instVertexG);

		SyntaxConcept syntaxTopGoal = new SyntaxConcept('C', "TopGoal", false,
				true, "Top Goal", "refasgoal",
				"Defines a top goal of the system"
						+ " from the stakeholder perspective that can be"
						+ " satisfied with a clear cut condition", 120, 60,
				"/com/variamos/gui/perspeditor/images/goal.png", true,
				Color.BLUE.toString(), 3, semGoal, true);

		InstConcept instVertexTG = new InstConcept("TopGoal",
				supportMetaElementConcept, syntaxTopGoal);
		refas.getVariabilityVertex().put("TopGoal", instVertexTG);

		SyntaxPairwiseRel metaViewLF = new SyntaxPairwiseRel("ViewRelation",
				true, true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);

		InstConcept instViewLF = new InstConcept("View Leaf Feature Relation",
				supportMetaViewPairwise, metaViewLF);
		refas.getVariabilityVertex().put("View Leaf Feature Relation",
				instViewLF);

		instViewLF.setInstAttribute("Palette", "Feature Palette");

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vlf-tolf", instEdge);
		instEdge.setIdentifier("vlf-tolf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instViewLF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vlf-fromview", instEdge);
		instEdge.setIdentifier("vlf-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewLF, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instLFExtendsPairWiseRel = new InstConcept(
				"LF Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		refas.getVariabilityVertex().put("LF Extends Relation",
				instLFExtendsPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extlf-fext", instEdge);
		instEdge.setIdentifier("variab-extlf-fext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instLFExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extlfext-f", instEdge);
		instEdge.setIdentifier("variab-extlfext-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instLFExtendsPairWiseRel, true);

		InstConcept instGFExtendsPairWiseRel = new InstConcept(
				"GF Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		refas.getVariabilityVertex().put("GF Extends Relation",
				instGFExtendsPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extgf-fext", instEdge);
		instEdge.setIdentifier("variab-extgf-fext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGFExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexGF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extgfext-f", instEdge);
		instEdge.setIdentifier("variab-extgfext-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instGFExtendsPairWiseRel, true);

		InstConcept instRFExtendsPairWiseRel = new InstConcept(
				"RF Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		refas.getVariabilityVertex().put("RF Extends Relation",
				instRFExtendsPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extrf-fext", instEdge);
		instEdge.setIdentifier("variab-extrf-fext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instRFExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexRF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extrfext-f", instEdge);
		instEdge.setIdentifier("variab-extrfext-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instRFExtendsPairWiseRel, true);

		// Feature direct relations

		InstElement semGroupPaiwiseRel = refas.getOperationalModel().getVertex(
				"groupPWAsso");

		SyntaxPairwiseRel metaGroupPairwiseRel = new SyntaxPairwiseRel(
				"Group Relation", true, true, "Group Relation", "",
				"Direct relation with a over two relation concept."
						+ " No additional type defined", 60, 40,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				semGroupPaiwiseRel);

		// InstConcept instGroupPairWiseRel = new InstConcept("Group Relation",
		// supportMetaElementPairwise, metaGroupPairwiseRel);
		metaGroupPairwiseRel.addModelingAttribute("AggregationLow", "Integer",
				false, "Aggregation Low", "", 0, 0, -1, "", "", -1, "", "");
		metaGroupPairwiseRel.addPropEditableAttribute("03#" + "AggregationLow");
		metaGroupPairwiseRel.addPropVisibleAttribute("03#" + "AggregationLow");
		metaGroupPairwiseRel.addPanelVisibleAttribute("03#" + "AggregationLow"
				+ "#" + "AggregationHigh" + "#!=#" + "0");
		metaGroupPairwiseRel.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");

		metaGroupPairwiseRel.addModelingAttribute("AggregationHigh", "Integer",
				false, "Aggregation High", "", 0, 0, -1, "", "", -1, "", "");
		metaGroupPairwiseRel
				.addPropEditableAttribute("04#" + "AggregationHigh");
		metaGroupPairwiseRel.addPropVisibleAttribute("04#" + "AggregationHigh");
		metaGroupPairwiseRel.addPanelVisibleAttribute("04#" + "AggregationHigh"
				+ "#" + "AggregationHigh" + "#!=#" + "0");
		metaGroupPairwiseRel.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");

		// instGroupPairWiseRel.setInstAttribute("Type", "Default");
		// refas.getVariabilityVertex().put("Group Relation",
		// instGroupPairWiseRel);

		InstElement directFeatFeatVertSemEdge = refas.getOperationalModel()
				.getVertex("FeatFeatParentPWAsso");

		InstElement directFeatFeatSideSemEdge = refas.getOperationalModel()
				.getVertex("FeatFeatSidePWAsso");

		SyntaxPairwiseRel metaFeatVertPairwiseRel = new SyntaxPairwiseRel(
				"Feature Child Relation", true, true, "Feature Child Relation",
				"", "Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				directFeatFeatVertSemEdge);

		// InstConcept instFeatVertPairWiseRel = new InstConcept(
		// "Feature Child Relation", supportMetaElementPairwise,
		// metaFeatVertPairwiseRel);
		// refas.getVariabilityVertex().put("Feature Child Relation",
		// instFeatVertPairWiseRel);

		SyntaxPairwiseRel metaFeatSidePairwiseRel = new SyntaxPairwiseRel(
				"DirSideRelation", true, true, "Feature Side Relation", "",
				"Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 70, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				directFeatFeatSideSemEdge);

		// InstConcept instFeatSidePairWiseRel = new InstConcept(
		// "Feature Side Relation", supportMetaElementPairwise,
		// metaFeatSidePairwiseRel);
		// refas.getVariabilityVertex().put("Feature Side Relation",
		// instFeatSidePairWiseRel);

		InstConcept instDirSideRelation = new InstConcept("DirSideRelation",
				supportMetaElementPairwise, metaFeatSidePairwiseRel);
		instDirSideRelation.setInstAttribute("Type", "DirSideRelation");
		instDirSideRelation.getInstAttribute("SourceCardinality").setValue(
				"[0..1]");
		instDirSideRelation.getInstAttribute("TargetCardinality").setValue(
				"[0..1]");
		refas.getVariabilityVertex()
				.put("DirSideRelation", instDirSideRelation);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-fPRst-pwrf", instEdge);
		instEdge.setIdentifier("variab-fPRstpwrf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirSideRelation, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-pwrf-fPRst", instEdge);
		instEdge.setIdentifier("variab-pwrf-fPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instDirSideRelation, true);

		InstConcept instDirStructureRFRelation = new InstConcept(
				"DirStructureRFRelation", supportMetaElementPairwise,
				metaFeatVertPairwiseRel);
		instDirStructureRFRelation.setInstAttribute("Type", "Structure");
		instDirStructureRFRelation.getInstAttribute("SourceCardinality")
				.setValue("[0..1]");
		instDirStructureRFRelation.getInstAttribute("TargetCardinality")
				.setValue("[0..1]");
		refas.getVariabilityVertex().put("DirStructureRFRelation",
				instDirStructureRFRelation);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-gfPRst-pwrrf", instEdge);
		instEdge.setIdentifier("variab-gfPRst-pwrrf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirStructureRFRelation, true);
		instEdge.setSourceRelation(instVertexGF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-pwrgf-rfPRst", instEdge);
		instEdge.setIdentifier("variab-pwrgf-rfPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexRF, true);
		instEdge.setSourceRelation(instDirStructureRFRelation, true);

		InstConcept instDirStructureLFRFRelation = new InstConcept(
				"DirStructureLFRFRelation", supportMetaElementPairwise,
				metaFeatVertPairwiseRel);
		instDirStructureLFRFRelation.setInstAttribute("Type", "Structure");
		instDirStructureLFRFRelation.getInstAttribute("SourceCardinality")
				.setValue("[0..1]");
		instDirStructureLFRFRelation.getInstAttribute("TargetCardinality")
				.setValue("[0..1]");
		refas.getVariabilityVertex().put("DirStructureLFRFRelation",
				instDirStructureLFRFRelation);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-lfPRst-pwrlrf", instEdge);
		instEdge.setIdentifier("variab-lfPRstpwrlrf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirStructureLFRFRelation, true);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-pwrlrf-rfPRst", instEdge);
		instEdge.setIdentifier("variab-pwrlrf-rfPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexRF, true);
		instEdge.setSourceRelation(instDirStructureLFRFRelation, true);

		InstConcept instDirStructureLFGRRelation = new InstConcept(
				"DirStructureLFGRRelation", supportMetaElementPairwise,
				metaFeatVertPairwiseRel);
		instDirStructureLFGRRelation.setInstAttribute("Type", "Structure");
		instDirStructureLFGRRelation.getInstAttribute("SourceCardinality")
				.setValue("[0..1]");
		instDirStructureLFGRRelation.getInstAttribute("TargetCardinality")
				.setValue("[0..1]");
		refas.getVariabilityVertex().put("DirStructureLFGRRelation",
				instDirStructureLFGRRelation);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-lfPRst-pwrlgf", instEdge);
		instEdge.setIdentifier("variab-lfPRstpwrlgf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirStructureLFGRRelation, true);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-pwrlgf-gfPRst", instEdge);
		instEdge.setIdentifier("variab-pwrlgf-gfPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGF, true);
		instEdge.setSourceRelation(instDirStructureLFGRRelation, true);

		InstConcept instDirStructureGFRelation = new InstConcept(
				"DirStructureGFRelation", supportMetaElementPairwise,
				metaFeatVertPairwiseRel);
		instDirStructureGFRelation.setInstAttribute("Type", "Structure");
		instDirStructureGFRelation.getInstAttribute("SourceCardinality")
				.setValue("[0..1]");
		instDirStructureGFRelation.getInstAttribute("TargetCardinality")
				.setValue("[0..1]");
		refas.getVariabilityVertex().put("DirStructureGFRelation",
				instDirStructureGFRelation);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-gfPRst-pwrgf", instEdge);
		instEdge.setIdentifier("variab-gfPRstpwrgf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirStructureGFRelation, true);
		instEdge.setSourceRelation(instVertexGF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-pwrgf-gfPRst", instEdge);
		instEdge.setIdentifier("variab-pwrgf-gfPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGF, true);
		instEdge.setSourceRelation(instDirStructureGFRelation, true);

		// Features OverTwoRelations

		InstConcept semanticFeatFeatGroupRelation = ((InstConcept) refas
				.getOperationalModel().getVertex("FeatFeatOTAsso"));

		SyntaxConcept featureMetaOverTwoRel = new SyntaxConcept('O',
				"FeatOTAsso", true, true, "FeatOTAsso", "plgroup",
				"Group relation between"
						+ " Feature concepts. Defines different types of"
						+ " cardinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticFeatFeatGroupRelation, false);

		InstConcept instVertexFOTR = new InstConcept("FeatOTAsso",
				supportMetaElementOverTwo, featureMetaOverTwoRel);
		refas.getVariabilityVertex().put("FeatOTAsso", instVertexFOTR);

		SyntaxPairwiseRel metaViewFG = new SyntaxPairwiseRel("ViewRelation",
				true, true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);

		InstConcept instViewFG = new InstConcept("View Feature OT Relation",
				supportMetaViewPairwise, metaViewFG);
		refas.getVariabilityVertex()
				.put("View Feature OT Relation", instViewFG);

		instViewFG.setInstAttribute("Palette", "Feature Palette");

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vfg-tofg", instEdge);
		instEdge.setIdentifier("vfg-tofg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexFOTR, true);
		instEdge.setSourceRelation(instViewFG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vfg-fromview", instEdge);
		instEdge.setIdentifier("vfg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewFG, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instGrpVertRelation = new InstConcept("GrpVertRelation",
				supportMetaElementPairwise, metaGroupPairwiseRel);

		instGrpVertRelation.setInstAttribute("Type", "Group");
		instGrpVertRelation.setInstAttribute("SourceCardinality", "[1..1]");
		instGrpVertRelation.setInstAttribute("TargetCardinality", "[0..1]");
		refas.getVariabilityVertex()
				.put("GrpVertRelation", instGrpVertRelation);

		InstConcept instGrpStrucRelation = new InstConcept("GrpStrucRelation",
				supportMetaElementPairwise, metaFeatVertPairwiseRel);

		instGrpStrucRelation.setInstAttribute("Type", "Structural");
		instGrpStrucRelation.setInstAttribute("SourceCardinality", "[1..1]");
		instGrpStrucRelation.setInstAttribute("TargetCardinality", "[0..1]");
		refas.getVariabilityVertex().put("GrpStrucRelation",
				instGrpStrucRelation);

		InstConcept instGrpSideFeatPairWiseRel = new InstConcept(
				"GrpSideFeatRelation", supportMetaElementPairwise,
				metaFeatSidePairwiseRel);
		instGrpSideFeatPairWiseRel.setInstAttribute("Type", "SideRelations");
		instGrpSideFeatPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpSideFeatPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("GrpSideFeatRelation",
				instGrpSideFeatPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("strvariab-f-pwrme", instEdge);
		instEdge.setIdentifier("strvariab-f-pwrme");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpVertRelation, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("strfeatf-pwrme-va", instEdge);
		instEdge.setIdentifier("strfeatf-pwrme-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexFOTR, true);
		instEdge.setSourceRelation(instGrpVertRelation, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("strvariab-otr-fpwrme", instEdge);
		instEdge.setIdentifier("strvariab-otr-fpwrme");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpStrucRelation, true);
		instEdge.setSourceRelation(instVertexFOTR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("strfeat-pwrme-va", instEdge);
		instEdge.setIdentifier("strfeat-pwrme-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instGrpStrucRelation, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sidefeat-otr-pwrme", instEdge);
		instEdge.setIdentifier("sidefeat-otr-pwrme");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpSideFeatPairWiseRel, true);
		instEdge.setSourceRelation(instVertexFOTR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sidefeat-pwrme-f", instEdge);
		instEdge.setIdentifier("sidefeat-pwrme-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instGrpSideFeatPairWiseRel, true);

		InstConcept instViewVA = new InstConcept("View VA Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View VA Relation", instViewVA);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vva-tova", instEdge);
		instEdge.setIdentifier("vva-tova");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instViewVA, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vva-fromview", instEdge);
		instEdge.setIdentifier("vva-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewVA, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instGExtendsPairWiseRel = new InstConcept(
				"G Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		refas.getVariabilityVertex().put("G Extends Relation",
				instGExtendsPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extvatgext", instEdge);
		instEdge.setIdentifier("variab-extvatgext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instGExtendsPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extvatextg", instEdge);
		instEdge.setIdentifier("variab-extvatextg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexG, true);

		InstConcept instTGExtendsPairWiseRel = new InstConcept(
				"TG Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		refas.getVariabilityVertex().put("TG Extends Relation",
				instTGExtendsPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extvattgext", instEdge);
		instEdge.setIdentifier("variab-extvattgext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexG, true);
		instEdge.setSourceRelation(instTGExtendsPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extvatexttg", instEdge);
		instEdge.setIdentifier("variab-extvatexttg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instTGExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexTG, true);

		InstConcept instViewG = new InstConcept("View G Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View G Relation", instViewG);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vg-tog", instEdge);
		instEdge.setIdentifier("vg-tog");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexG, true);
		instEdge.setSourceRelation(instViewG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vg-fromview", instEdge);
		instEdge.setIdentifier("vg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewG, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instViewTG = new InstConcept("View TG Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View TG Relation", instViewTG);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vtg-totg", instEdge);
		instEdge.setIdentifier("vtg-totg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexTG, true);
		instEdge.setSourceRelation(instViewTG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vtg-fromview", instEdge);
		instEdge.setIdentifier("vtg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewTG, true);
		instEdge.setSourceRelation(instViewC, true);

		SyntaxConcept syntaxGeneralGoal = new SyntaxConcept('C', "GeneralGoal",
				false, true, "General Goal", "refasgoal",
				"Defines a general goal of the"
						+ " system from the stakeholder perspective that can"
						+ " be satisfied with a clear cut condition", 120, 60,
				"/com/variamos/gui/perspeditor/images/goal.png", true,
				Color.BLUE.toString(), 2, semGoal, true);

		InstConcept instVertexGG = new InstConcept("GeneralGoal",
				supportMetaElementConcept, syntaxGeneralGoal);
		refas.getVariabilityVertex().put("GeneralGoal", instVertexGG);

		InstConcept instViewGG = new InstConcept("View GG Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View GG Relation", instViewGG);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vgg-togg", instEdge);
		instEdge.setIdentifier("vgg-togg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGG, true);
		instEdge.setSourceRelation(instViewGG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vgg-fromview", instEdge);
		instEdge.setIdentifier("vgg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewGG, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instGGExtendsPairWiseRel = new InstConcept(
				"GG Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		refas.getVariabilityVertex().put("GG Extends Relation",
				instGGExtendsPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extvatggext", instEdge);
		instEdge.setIdentifier("variab-extvatggext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexG, true);
		instEdge.setSourceRelation(instGGExtendsPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extvatextgg", instEdge);
		instEdge.setIdentifier("variab-extvatextgg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGGExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexGG, true);

		InstConcept semOperationalization = ((InstConcept) refas
				.getOperationalModel().getVertex("Operationalization"));
		SyntaxConcept sOperationalization = new SyntaxConcept('C', "OPER",
				true, true, "OPER", "refasoper", "An operationalization allows"
						+ " the partial or complete satisfaction of a goal or"
						+ " another operationalization. If"
						+ " the operationalizations defined is satisfied,"
						+ " according to the defined relation, the goal"
						+ " associated will be also satisfied", 100, 60,
				"/com/variamos/gui/perspeditor/images/operational.png", true,
				Color.BLUE.toString(), 2, semOperationalization, true);

		sOperationalization.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		sOperationalization.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instVertexOper = new InstConcept("OPER",
				supportMetaElementConcept, sOperationalization);

		refas.getVariabilityVertex().put("OPER", instVertexOper);

		InstConcept instOExtendsPairWiseRel = new InstConcept(
				"O Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		refas.getVariabilityVertex().put("O Extends Relation",
				instOExtendsPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extvaextoper", instEdge);
		instEdge.setIdentifier("variab-extvaextoper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instOExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extvaoperext", instEdge);
		instEdge.setIdentifier("variab-extvaoperext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instOExtendsPairWiseRel, true);

		InstConcept instViewOper = new InstConcept("View Oper Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View Oper Relation", instViewOper);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("voper-tooper", instEdge);
		instEdge.setIdentifier("voper-tooper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instViewOper, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("voper-fromview", instEdge);
		instEdge.setIdentifier("voper-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewOper, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept semAssumption = ((InstConcept) refas.getOperationalModel()
				.getVertex("Assumption"));

		SyntaxConcept syntaxAssumption = new SyntaxConcept('C', "Assu", true,
				true, "Assumption", "refasassump", "An assumption is a"
						+ " condition that should me truth for the goal or"
						+ " operationalization to be satisfied", 100, 60,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.WHITE.toString(), 1, semAssumption, true);

		syntaxAssumption.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		syntaxAssumption.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instVertexAssum = new InstConcept("Assu",
				supportMetaElementConcept, syntaxAssumption);
		refas.getVariabilityVertex().put("Assu", instVertexAssum);

		InstConcept instViewAssum = new InstConcept("View Assum Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View Assum Relation", instViewAssum);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vassum-toassum", instEdge);
		instEdge.setIdentifier("vassum-toassum");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssum, true);
		instEdge.setSourceRelation(instViewAssum, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vassum-fromview", instEdge);
		instEdge.setIdentifier("vassum-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewAssum, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instAExtendsPairWiseRel = new InstConcept(
				"A Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		refas.getVariabilityVertex().put("A Extends Relation",
				instAExtendsPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extvaextassu", instEdge);
		instEdge.setIdentifier("variab-extvaextassu");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instAExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAssum, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-extvaassuext", instEdge);
		instEdge.setIdentifier("variab-extvaassuext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instAExtendsPairWiseRel, true);

		// Direct Hard Relations

		InstElement directStructHardHardSemanticEdge = refas
				.getOperationalModel().getVertex("structHardHardPWAsso");

		SyntaxPairwiseRel metaStructHardPairwiseRel = new SyntaxPairwiseRel(
				"HardRelation", true, true, "HardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 70, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directStructHardHardSemanticEdge);

		InstConcept instGrpMeansEndsRelation = new InstConcept(
				"GrpMeansEndsRelation", supportMetaElementPairwise,
				metaStructHardPairwiseRel);

		instGrpMeansEndsRelation.setInstAttribute("Type", "MeansEnds");
		instGrpMeansEndsRelation
				.setInstAttribute("SourceCardinality", "[1..1]");
		instGrpMeansEndsRelation
				.setInstAttribute("TargetCardinality", "[0..1]");
		refas.getVariabilityVertex().put("GrpMeansEndsRelation",
				instGrpMeansEndsRelation);

		SyntaxPairwiseRel metaDirStructHardPairwiseRel = new SyntaxPairwiseRel(
				"HardRelation", true, true, "HardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 70, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directStructHardHardSemanticEdge);

		/*
		 * metaDirStructHardPairwiseRel.addModelingAttribute("Aggregation",
		 * "String", false, "Aggregation", "", "", 0, -1, "", "", -1, "", "");
		 * metaDirStructHardPairwiseRel.addPropEditableAttribute("03#" +
		 * "Aggregation");
		 * metaDirStructHardPairwiseRel.addPropVisibleAttribute("03#" +
		 * "Aggregation");
		 * metaDirStructHardPairwiseRel.addPanelVisibleAttribute("03#" +
		 * "Aggregation");
		 */

		metaDirStructHardPairwiseRel.addModelingAttribute("AggregationLow",
				"Integer", false, "Aggregation Low", "", 0, 0, -1, "", "", -1,
				"", "");
		metaDirStructHardPairwiseRel.addPropEditableAttribute("03#"
				+ "AggregationLow");
		metaDirStructHardPairwiseRel.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		metaDirStructHardPairwiseRel.addPanelVisibleAttribute("03#"
				+ "AggregationLow" + "#" + "AggregationHigh" + "#!=#" + "0");
		metaDirStructHardPairwiseRel.addPanelSpacersAttribute("[#"
				+ "AggregationLow" + "#..");

		metaDirStructHardPairwiseRel.addModelingAttribute("AggregationHigh",
				"Integer", false, "Aggregation High", "", 0, 0, -1, "", "", -1,
				"", "");
		metaDirStructHardPairwiseRel.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		metaDirStructHardPairwiseRel.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		metaDirStructHardPairwiseRel.addPanelVisibleAttribute("04#"
				+ "AggregationHigh" + "#" + "AggregationHigh" + "#!=#" + "0");
		metaDirStructHardPairwiseRel.addPanelSpacersAttribute("#"
				+ "AggregationHigh" + "#]\n");

		// TODO create another meta element
		InstConcept instDirMeansEndsRelation = new InstConcept(
				"DirMeansEndsRelation", supportMetaElementPairwise,
				metaDirStructHardPairwiseRel);
		instDirMeansEndsRelation.setInstAttribute("Type", "MeansEnds");
		instDirMeansEndsRelation.getInstAttribute("SourceCardinality")
				.setValue("[0..1]");
		instDirMeansEndsRelation.getInstAttribute("TargetCardinality")
				.setValue("[0..1]");
		refas.getVariabilityVertex().put("DirMeansEndsRelation",
				instDirMeansEndsRelation);

		InstElement directSideHardHardSemanticEdge = refas
				.getOperationalModel().getVertex("GoalGoalSidePWAsso");

		SyntaxPairwiseRel metaSideHardPairwiseRel = new SyntaxPairwiseRel(
				"SideRelation", true, true, "SideRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 70, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSideHardHardSemanticEdge);

		InstConcept instGrpSideHardHardPairWiseRel = new InstConcept(
				"GrpSideHardRelation", supportMetaElementPairwise,
				metaSideHardPairwiseRel);
		instGrpSideHardHardPairWiseRel
				.setInstAttribute("Type", "SideRelations");
		instGrpSideHardHardPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpSideHardHardPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("GrpSideHardRelation",
				instGrpSideHardHardPairWiseRel);

		SyntaxPairwiseRel metaDirSideHardPairwiseRel = new SyntaxPairwiseRel(
				"SideHardRelation", true, true, "SideHardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 70, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSideHardHardSemanticEdge);

		metaDirSideHardPairwiseRel.addModelingAttribute("AggregationLow",
				"Integer", false, "Aggregation Low", "", 0, 0, -1, "", "", -1,
				"", "");
		metaDirSideHardPairwiseRel.addPropEditableAttribute("03#"
				+ "AggregationLow");
		metaDirSideHardPairwiseRel.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		metaDirSideHardPairwiseRel.addPanelVisibleAttribute("03#"
				+ "AggregationLow" + "#" + "AggregationHigh" + "#!=#" + "0");
		metaDirSideHardPairwiseRel.addPanelSpacersAttribute("[#"
				+ "AggregationLow" + "#..");

		metaDirSideHardPairwiseRel.addModelingAttribute("AggregationHigh",
				"Integer", false, "Aggregation High", "", 0, 0, -1, "", "", -1,
				"", "");
		metaDirSideHardPairwiseRel.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		metaDirSideHardPairwiseRel.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		metaDirSideHardPairwiseRel.addPanelVisibleAttribute("04#"
				+ "AggregationHigh" + "#" + "AggregationHigh" + "#!=#" + "0");
		metaDirSideHardPairwiseRel.addPanelSpacersAttribute("#"
				+ "AggregationHigh" + "#]\n");

		// TODO create another
		InstConcept instDirSideHardHardPairWiseRel = new InstConcept(
				"DirSideHardRelation", supportMetaElementPairwise,
				metaDirSideHardPairwiseRel);
		instDirSideHardHardPairWiseRel
				.setInstAttribute("Type", "SideRelations");
		instDirSideHardHardPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instDirSideHardHardPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("DirSideHardRelation",
				instDirSideHardHardPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-vaPRst-pwr", instEdge);
		instEdge.setIdentifier("variab-vaPRstpwr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirMeansEndsRelation, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-pwr-vaPRst", instEdge);
		instEdge.setIdentifier("variab-pwr-vaPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instDirMeansEndsRelation, true);

		// Hard OverTwoRelations

		InstConcept semanticHardHardGroupRelation = ((InstConcept) refas
				.getOperationalModel().getVertex("GoalOTAsso"));

		SyntaxConcept hardMetaOverTwoRel = new SyntaxConcept('O',
				"HardOverTwoRel", true, true, "HardOverTwoRel", "plgroup",
				"Group relation between"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticHardHardGroupRelation, false);

		// TODO Create another group
		InstConcept instGroupHardPairWiseRel = new InstConcept(
				"Hard Group Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGroupHardPairWiseRel.setInstAttribute("Type", "Default");
		instGroupHardPairWiseRel
				.setInstAttribute("SourceCardinality", "[0..*]");
		instGroupHardPairWiseRel
				.setInstAttribute("TargetCardinality", "[0..*]");
		refas.getVariabilityVertex().put("Hard Group Relation",
				instGroupHardPairWiseRel);

		InstConcept instVertexHOTR = new InstConcept("HardOverTwoRel",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexHOTR.getInstAttribute("Type").setValue("Group");
		refas.getVariabilityVertex().put("HardOverTwoRel", instVertexHOTR);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("strvariab-otr-pwrme", instEdge);
		instEdge.setIdentifier("strvariab-otr-pwrme");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpMeansEndsRelation, true);
		instEdge.setSourceRelation(instVertexHOTR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("strvariab-pwrme-va", instEdge);
		instEdge.setIdentifier("strvariab-pwrme-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instGrpMeansEndsRelation, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sidevariab-otr-pwrme", instEdge);
		instEdge.setIdentifier("sidevariab-otr-pwrme");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpSideHardHardPairWiseRel, true);
		instEdge.setSourceRelation(instVertexHOTR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sidevariab-pwrme-va", instEdge);
		instEdge.setIdentifier("sidevariab-pwrme-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instGrpSideHardHardPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sidevariab-va-pwrd", instEdge);
		instEdge.setIdentifier("sidevariab-va-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirSideHardHardPairWiseRel, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sidevariab-pwrd-va", instEdge);
		instEdge.setIdentifier("sidevariab-pwrd-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instDirSideHardHardPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-pwrg-otr", instEdge);
		instEdge.setIdentifier("variab-pwrg-otr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexHOTR, true);
		instEdge.setSourceRelation(instGroupHardPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-va-pwrg-otr", instEdge);
		instEdge.setIdentifier("variab-va-pwrg-otr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGroupHardPairWiseRel, true);
		instEdge.setSourceRelation(instVertexVA, true);

		/*
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-HOTtoVAsi", instEdge);
		instEdge.setIdentifier("variab-HOTtoVAsi");
		instEdge.setEdSyntaxEle(metaSideHardPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertexHOTR, true);
		*/

		InstConcept instViewHOTR = new InstConcept("View Hard Group Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View Hard Group Relation",
				instViewHOTR);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vhotr-tohotr", instEdge);
		instEdge.setIdentifier("vhotr-tohotr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexHOTR, true);
		instEdge.setSourceRelation(instViewHOTR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vhotr-fromview", instEdge);
		instEdge.setIdentifier("vhotr-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewHOTR, true);
		instEdge.setSourceRelation(instViewC, true);

		// *************************---------------****************************
		// *************************---------------****************************
		// Softgoals model

		syntaxMetaView = new SyntaxView("SoftGoals", true, true,
				"Soft Goals View", "plnode", "Defines sofgoals", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Soft Goals Palette", 2, null);
		instViewC = new InstConcept("SoftGoals", metaView, syntaxMetaView);

		refas.getVariabilityVertex().put("SoftGoals", instViewC);

		InstConcept semSoftgoal = ((InstConcept) refas.getOperationalModel()
				.getVertex("Softgoal"));
		SyntaxConcept syntaxSoftGoal = new SyntaxConcept(
				'C',
				"Softgoal",
				true,
				true,
				"Softgoal",
				"refassoftgoal",
				"Defines a soft goal of the"
						+ " system from the stakeholder"
						+ " perspective to represent non-functional requirements"
						+ ". Given the satisficing problem,"
						+ " it includes an scale of levels of satisfaction/denial."
						+ " The SG satisficing level can be measured globally or"
						+ " locally, for the system or for each user, depending"
						+ " on the SG", 100, 60,
				"/com/variamos/gui/perspeditor/images/softgoal.png", true,
				Color.WHITE.toString(), 3, semSoftgoal, true);

		syntaxSoftGoal.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		syntaxSoftGoal.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		syntaxSoftGoal.addModelingAttribute("name", "String", false, "Name",
				"", "", 0, -1, "", "", -1, "", "");
		// syntaxSoftGoal.addPanelVisibleAttribute("03#" + "name");

		syntaxSoftGoal.addPropEditableAttribute("03#" + "name");
		syntaxSoftGoal.addPropVisibleAttribute("03#" + "name");

		// syntaxSoftGoal.addModelingAttribute("concern", "ConcernLevel", false,
		// "Concern Level", "", 0, -1, "", "", -1, "", "");

		InstConcept instVertexSG = new InstConcept("Softgoal",
				supportMetaElementConcept, syntaxSoftGoal);
		refas.getVariabilityVertex().put("Softgoal", instVertexSG);

		InstConcept instViewSG = new InstConcept("View SG Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View SG Relation", instViewSG);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vsg-tosg", instEdge);
		instEdge.setIdentifier("vsg-tosg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instViewSG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vsg-fromview", instEdge);
		instEdge.setIdentifier("vsg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewSG, true);
		instEdge.setSourceRelation(instViewC, true);

		SyntaxConcept syntaxTopSoftGoal = new SyntaxConcept(
				'C',
				"TopSoftgoal",
				false,
				true,
				"Top Softgoal",
				"refassoftgoal",
				"Defines a top goal of the"
						+ " system from the stakeholder"
						+ " perspective that can at most be satisficed without"
						+ " a clear cut verification. Given the satisficing problem,"
						+ " it includes an scale of levels of satisfaction/denial."
						+ " The SG satisficing level can be measured globally or"
						+ " locally, for the system or for each user, depending"
						+ " on the SG", 100, 60,
				"/com/variamos/gui/perspeditor/images/softgoal.png", true,
				Color.WHITE.toString(), 3, semSoftgoal, true);
		InstConcept instVertexTSG = new InstConcept("TopSoftgoal",
				supportMetaElementConcept, syntaxTopSoftGoal);
		refas.getVariabilityVertex().put("TopSoftgoal", instVertexTSG);

		InstConcept instExtTSG = new InstConcept("TSG Ext Relation",
				supportMetaExtendsPairwise, metaViewRel);
		refas.getVariabilityVertex().put("TSG Ext Relation", instExtTSG);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("exttsg-sggr", instEdge);
		instEdge.setIdentifier("exttsg-sggr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instExtTSG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("extgrtsg-sg", instEdge);
		instEdge.setIdentifier("extgrtsg-sg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instExtTSG, true);
		instEdge.setSourceRelation(instVertexTSG, true);

		InstConcept instViewTSG = new InstConcept("View TSG Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View TSG Relation", instViewTSG);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vtsg-totsg", instEdge);
		instEdge.setIdentifier("vtsg-totsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexTSG, true);
		instEdge.setSourceRelation(instViewTSG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vtsg-fromview", instEdge);
		instEdge.setIdentifier("vtsg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewTSG, true);
		instEdge.setSourceRelation(instViewC, true);

		SyntaxConcept syntaxGeneralSoftGoal = new SyntaxConcept(
				'C',
				"GeneralSoftgoal",
				false,
				true,
				"General Softgoal",
				"refassoftgoal",
				"Defines a general"
						+ " softgoal of the system from the stakeholder"
						+ " perspective that can at most be satisficed without"
						+ " a clear cut verification. Given the satisficing problem,"
						+ " it includes an scale of levels of satisfaction/denial."
						+ " The SG satisficing level can be measured globally or"
						+ " locally, for the system or for each user, depending"
						+ " on the SG", 100, 60,
				"/com/variamos/gui/perspeditor/images/softgoal.png", true,
				Color.WHITE.toString(), 1, semSoftgoal, true);

		InstConcept instVertexGSG = new InstConcept("GeneralSoftgoal",
				supportMetaElementConcept, syntaxGeneralSoftGoal);
		refas.getVariabilityVertex().put("GeneralSoftgoal", instVertexGSG);

		InstConcept instExtGSG = new InstConcept("GSG Ext Relation",
				supportMetaExtendsPairwise, metaViewRel);
		refas.getVariabilityVertex().put("GSG Ext Relation", instExtGSG);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("extgsg-sggr", instEdge);
		instEdge.setIdentifier("extgsg-sggr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instExtGSG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("extgrgsg-sg", instEdge);
		instEdge.setIdentifier("extgrgsg-sg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instExtGSG, true);
		instEdge.setSourceRelation(instVertexGSG, true);

		InstConcept instViewGSG = new InstConcept("View GSG Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View GSG Relation", instViewGSG);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vgsg-togsg", instEdge);
		instEdge.setIdentifier("vgsg-togsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGSG, true);
		instEdge.setSourceRelation(instViewGSG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vgsg-fromview", instEdge);
		instEdge.setIdentifier("vgsg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewGSG, true);
		instEdge.setSourceRelation(instViewC, true);

		// Direct Soft relation

		InstElement directSGSGSemEdge = refas.getOperationalModel().getVertex(
				"SgSgPWAsso");

		InstElement directSGGRSemEdge = refas.getOperationalModel().getVertex(
				"sgsgOTAssoFromPWAsso");

		InstElement directGRSGSemEdge = refas.getOperationalModel().getVertex(
				"sgsgOTAssoToPWAsso");

		SyntaxPairwiseRel metaGroupSoftFromPairWiseRel = new SyntaxPairwiseRel(
				"GroupSoftFromRelation", true, true,
				"Group Soft From Relation", "",
				"Direct relation between two soft concepts. Defines"
						+ " different types of relations and cardinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSGGRSemEdge);

		SyntaxPairwiseRel metaGroupSoftToPairWiseRel = new SyntaxPairwiseRel(
				"GroupSoftToRelation", true, true, "Group Soft To Relation",
				"", "Direct relation between two soft concepts. Defines"
						+ " different types of relations and cardinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directGRSGSemEdge);

		/*
		 * metaSoftPairWiseRel.addModelingAttribute("SourceLevel", "Integer",
		 * false, "Source Level", 0, 0, -1, "", "", -1, "", "");
		 * metaSoftPairWiseRel.addPropEditableAttribute("04#" + "SourceLevel");
		 * metaSoftPairWiseRel.addPropVisibleAttribute("04#" + "SourceLevel");
		 * 
		 * metaSoftPairWiseRel.addModelingAttribute("TargetLevel", "Integer",
		 * false, "Target Level", 0, 0, -1, "", "", -1, "", "");
		 * metaSoftPairWiseRel.addPropEditableAttribute("05#" + "TargetLevel");
		 * metaSoftPairWiseRel.addPropVisibleAttribute("05#" + "TargetLevel");
		 */

		SyntaxPairwiseRel metaDirSoftPairWiseRel = new SyntaxPairwiseRel(
				"DirSoftRelation", true, true, "Dir Soft Relation", "",
				"Direct relation between two soft concepts. Defines"
						+ " different types of relations and cardinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSGSGSemEdge);
		/*
		 * metaDirSoftPairWiseRel.addModelingAttribute("AggregationLow",
		 * "Integer", false, "Aggregation Low", 0, 0, -1, "", "", -1, "", "");
		 * metaDirSoftPairWiseRel.addPropEditableAttribute("03#" +
		 * "AggregationLow"); metaDirSoftPairWiseRel
		 * .addPropVisibleAttribute("03#" + "AggregationLow");
		 * metaDirSoftPairWiseRel.addPanelVisibleAttribute("03#" +
		 * "AggregationLow" + "#" + "AggregationHigh" + "#!=#" + "0");
		 * metaDirSoftPairWiseRel.addPanelSpacersAttribute("[#" +
		 * "AggregationLow" + "#..");
		 * 
		 * metaDirSoftPairWiseRel.addModelingAttribute("AggregationHigh",
		 * "Integer", false, "Aggregation High", 0, 0, -1, "", "", -1, "", "");
		 * metaDirSoftPairWiseRel.addPropEditableAttribute("04#" +
		 * "AggregationHigh");
		 * metaDirSoftPairWiseRel.addPropVisibleAttribute("04#" +
		 * "AggregationHigh");
		 * metaDirSoftPairWiseRel.addPanelVisibleAttribute("04#" +
		 * "AggregationHigh" + "#" + "AggregationHigh" + "#!=#" + "0");
		 * metaDirSoftPairWiseRel.addPanelSpacersAttribute("#" +
		 * "AggregationHigh" + "#]\n");
		 */
		/*
		 * metaDirSoftPairWiseRel.addModelingAttribute("SourceLevel", "Integer",
		 * false, "Source Levelx", 0, 0, -1, "", "", -1, "", "");
		 * metaDirSoftPairWiseRel.addPropEditableAttribute("05#" +
		 * "SourceLevel"); metaDirSoftPairWiseRel.addPropVisibleAttribute("05#"
		 * + "SourceLevel");
		 * 
		 * metaDirSoftPairWiseRel.addModelingAttribute("TargetLevel", "Integer",
		 * false, "Target Level", 0, 0, -1, "", "", -1, "", "");
		 * metaDirSoftPairWiseRel.addPropEditableAttribute("06#" +
		 * "TargetLevel"); metaDirSoftPairWiseRel.addPropVisibleAttribute("06#"
		 * + "TargetLevel");
		 */
		InstConcept instDirSoftPairWiseRel = new InstConcept(
				"Direct Soft Relation", supportMetaElementPairwise,
				metaDirSoftPairWiseRel);

		instDirSoftPairWiseRel.setInstAttribute("Type", "Contribution");
		instDirSoftPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instDirSoftPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		refas.getVariabilityVertex().put("Direct Soft Relation",
				instDirSoftPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-sg-pwrd", instEdge);
		instEdge.setIdentifier("variab-sg-pwrd");
		// instEdge.setEditableMetaElement(metaSoftPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirSoftPairWiseRel, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-pwrd-sg", instEdge);
		instEdge.setIdentifier("variab-pwrd-sg");
		// instEdge.setEditableMetaElement(metaSoftPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instDirSoftPairWiseRel, true);

		InstConcept semanticSGSGGroupRelation = ((InstConcept) refas
				.getOperationalModel().getVertex("SgSgOTAsso"));

		// Group soft relation

		hardMetaOverTwoRel = new SyntaxConcept('O', "SoftgoalOTAsso", true,
				true, "SoftgoalOTAsso", "plgroup",
				"Direct relation between soft"
						+ " concepts. Defines different types of relations"
						+ " and cardinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticSGSGGroupRelation, false);

		// TODO Create another group

		InstConcept instGrpSoftFromPairWiseRel = new InstConcept(
				"GroupSoftFromRelation", supportMetaElementPairwise,
				metaGroupSoftFromPairWiseRel);

		instGrpSoftFromPairWiseRel.setInstAttribute("Type", "Contribution");
		instGrpSoftFromPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpSoftFromPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("GroupSoftFromRelation",
				instGrpSoftFromPairWiseRel);

		InstConcept instGrpSoftToPairWiseRel = new InstConcept(
				"GroupSoftToRelation", supportMetaElementPairwise,
				metaGroupSoftToPairWiseRel);

		instGrpSoftToPairWiseRel.setInstAttribute("Type", "Contribution");
		instGrpSoftToPairWiseRel
				.setInstAttribute("SourceCardinality", "[0..*]");
		instGrpSoftToPairWiseRel
				.setInstAttribute("TargetCardinality", "[0..*]");
		refas.getVariabilityVertex().put("GroupSoftToRelation",
				instGrpSoftToPairWiseRel);

		InstConcept instVertexSGOTR = new InstConcept("SoftgoalOTAsso",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexSGOTR.getInstAttribute("Type").setValue("Group");
		refas.getVariabilityVertex().put("SoftgoalOTAsso", instVertexSGOTR);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sg-sg-pwrg", instEdge);
		instEdge.setIdentifier("sg-sg-pwrg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpSoftFromPairWiseRel, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sg-pwrg-otr", instEdge);
		instEdge.setIdentifier("sg-pwrg-ovt");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSGOTR, true);
		instEdge.setSourceRelation(instGrpSoftFromPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sg-pwrs-sg", instEdge);
		instEdge.setIdentifier("sg--pwrs-sg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instGrpSoftToPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sg-ovt-pwrs", instEdge);
		instEdge.setIdentifier("sg-ovt-pwrs");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpSoftToPairWiseRel, true);
		instEdge.setSourceRelation(instVertexSGOTR, true);

		InstConcept instViewSGOTR = new InstConcept("View SG Group Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View SG Group Relation",
				instViewSGOTR);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vsgg-tosgg", instEdge);
		instEdge.setIdentifier("vsgg-tosgg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSGOTR, true);
		instEdge.setSourceRelation(instViewSGOTR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vsgg-fromview", instEdge);
		instEdge.setIdentifier("vsgg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewSGOTR, true);
		instEdge.setSourceRelation(instViewC, true);

		// *************************---------------****************************
		// *************************---------------****************************
		// Context model

		syntaxMetaView = new SyntaxView("Context", true, true, "Context View",
				"plnode", "Defines a feature", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Context Palette", 3, null);
		instViewC = new InstConcept("Context", metaView, syntaxMetaView);
		refas.getVariabilityVertex().put("Context", instViewC);
		// syntaxMetaView.addConcept(syntaxVariable);
		InstConcept semContextGroup = ((InstConcept) refas
				.getOperationalModel().getVertex("ConcernLevel"));
		SyntaxConcept syntaxContextGroup = new SyntaxConcept('C', "CG", true,
				true, "ConcernLevel", "refascontextgrp", " A Concern Level"
						+ " is defined to associate variables with common"
						+ " characteristics. The type defines if variables"
						+ " are sensed or profiled, in the first case they"
						+ " are modifie by the system or environment; in the"
						+ " second case they are defined by the administrator"
						+ " or the user. The scope can be local or global,"
						+ " the first means the value is independently for"
						+ " each user while global variables are shared"
						+ " between all the users.", 150, 40,
				"/com/variamos/gui/perspeditor/images/contextgrp.png", true,
				Color.BLUE.toString(), 1, semContextGroup, true);

		syntaxContextGroup.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		syntaxContextGroup.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		syntaxContextGroup.addModelingAttribute("name", "String", false,
				"Name", "", "", 0, -1, "", "", -1, "", "");

		InstConcept instVertexCG = new InstConcept("CG",
				supportMetaElementConcept, syntaxContextGroup);
		refas.getVariabilityVertex().put("CG", instVertexCG);

		InstConcept instViewCG = new InstConcept("View CG Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View CG Relation", instViewCG);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vcg-tocg", instEdge);
		instEdge.setIdentifier("vcg-tocg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instViewCG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vcg-fromview", instEdge);
		instEdge.setIdentifier("vcg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewCG, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept semVariable = ((InstConcept) refas.getOperationalModel()
				.getVertex("Variable"));
		SyntaxConcept syntaxAbsVariable = new SyntaxConcept(
				'C',
				"Variable",
				true,
				true,
				"Variable",
				"refasglobcnxt",
				"A variable"
						+ " is an abstraction of a variable or component of the"
						+ " system or the environment that are relevant the system."
						+ " The variable values should be"
						+ " simplified as much as possible considering the modeling"
						+ " requirements", 150, 40,
				"/com/variamos/gui/perspeditor/images/globCnxtVar.png", true,
				Color.BLUE.toString(), 1, semVariable, true);

		syntaxAbsVariable.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		syntaxAbsVariable.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n");

		syntaxAbsVariable.addModelingAttribute("name", "String", false, "Name",
				"", "", 0, -1, "", "", -1, "", "");
		syntaxAbsVariable.addModelingAttribute("type", "String", false, "Type",
				"", "", 0, -1, "", "", -1, "", "");
		syntaxAbsVariable.addModelingAttribute("domain", "String", false,
				"Domain", "", "", 0, -1, "", "", -1, "", "");
		syntaxAbsVariable.addModelingAttribute("enumeration",
				"MetaEnumeration", false, "Enumeration", "", "", 0, -1, "", "",
				-1, "", "");
		// syntaxAbsVariable.addModelingAttribute("concern", "ConcernLevel",
		// false, "Concern Level", "", 0, -1, "", "", -1, "", "");

		InstConcept instVertexVar = new InstConcept("Variable",
				supportMetaElementConcept, syntaxAbsVariable);
		refas.getVariabilityVertex().put("Variable", instVertexVar);

		InstConcept instViewVar = new InstConcept("View Var Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View Var Relation", instViewVar);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vvar-tovar", instEdge);
		instEdge.setIdentifier("vvar-tovar");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVar, true);
		instEdge.setSourceRelation(instViewVar, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vvar-fromview", instEdge);
		instEdge.setIdentifier("vvar-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewVar, true);
		instEdge.setSourceRelation(instViewC, true);

		SyntaxConcept syntaxGlobalVariable = new SyntaxConcept('C',
				"GlobalVariable", false, true, "Global Variable",
				"refasglobcnxt", "Old Concept, replaced by Variable Concept",
				150, 40,
				"/com/variamos/gui/perspeditor/images/globCnxtVar.png", true,
				Color.BLUE.toString(), 1, semVariable, true);

		InstConcept instVertexGV = new InstConcept("GlobalVariable",
				supportMetaElementConcept, syntaxGlobalVariable);
		refas.getVariabilityVertex().put("GlobalVariable", instVertexGV);

		InstConcept instExtGV = new InstConcept("GVar Ext Relation",
				supportMetaExtendsPairwise, metaViewRel);
		refas.getVariabilityVertex().put("GVar Ext Relation", instExtGV);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("extgvar-vargr", instEdge);
		instEdge.setIdentifier("extgvar-vargr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVar, true);
		instEdge.setSourceRelation(instExtGV, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("extgrgvar-var", instEdge);
		instEdge.setIdentifier("extgrgvar-var");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instExtGV, true);
		instEdge.setSourceRelation(instVertexGV, true);

		InstConcept instViewGV = new InstConcept("View GVar Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View GVar Relation", instViewGV);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vgv-togv", instEdge);
		instEdge.setIdentifier("vgv-togv");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGV, true);
		instEdge.setSourceRelation(instViewGV, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vgv-fromview", instEdge);
		instEdge.setIdentifier("vgv-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewGV, true);
		instEdge.setSourceRelation(instViewC, true);

		SyntaxConcept syntaxContextVariable = new SyntaxConcept('C',
				"ContextVariable", false, true, "Context Variable",
				"refaslocalcnxt", " Old concept, replaced by Variable", 150,
				40, "/com/variamos/gui/perspeditor/images/localCnxtVar.png",
				true, Color.BLUE.toString(), 1, semVariable, true);

		InstConcept instVertexCV = new InstConcept("ContextVariable",
				supportMetaElementConcept, syntaxContextVariable);
		refas.getVariabilityVertex().put("ContextVariable", instVertexCV);

		InstConcept instExtCV = new InstConcept("CVar Ext Relation",
				supportMetaExtendsPairwise, metaViewRel);
		refas.getVariabilityVertex().put("CVar Ext Relation", instExtCV);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("extcvar-vargr", instEdge);
		instEdge.setIdentifier("extcvar-vargr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVar, true);
		instEdge.setSourceRelation(instExtCV, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("extgrcvar-var", instEdge);
		instEdge.setIdentifier("extgrcvar-var");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instExtCV, true);
		instEdge.setSourceRelation(instVertexCV, true);

		InstConcept instViewCV = new InstConcept("View CVar Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View CVar Relation", instViewCV);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vcv-tocv", instEdge);
		instEdge.setIdentifier("vcv-tocv");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCV, true);
		instEdge.setSourceRelation(instViewCV, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vcv-fromview", instEdge);
		instEdge.setIdentifier("vcv-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewCV, true);
		instEdge.setSourceRelation(instViewC, true);

		SyntaxConcept metaEnumeration = new SyntaxConcept('E', "ME", true,
				true, "MetaEnumeration", "refasenumeration", "Allows the"
						+ " creation of user defined enumerations for"
						+ " variables", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true, "", 1,
				null, true);

		metaEnumeration.addModelingAttribute(SyntaxConcept.VAR_METAENUMNAME,
				"String", false, SyntaxConcept.VAR_METAENUMNAMENAME, "", "", 0,
				1, "", "", 1, "#-#\n\n", "");
		metaEnumeration.addModelingAttribute(SyntaxConcept.VAR_METAENUMVALUE,
				"Set", false, SyntaxConcept.VAR_METAENUMVALUENAME, "",
				SyntaxConcept.VAR_METAENUMVALUECLASS,
				new ArrayList<InstAttribute>(), 0, 2, "", "", 2, "#\n", "");

		metaEnumeration.addPropEditableAttribute("01#"
				+ SyntaxConcept.VAR_METAENUMNAME);

		metaEnumeration.addPropVisibleAttribute("01#"
				+ SyntaxConcept.VAR_METAENUMNAME);
		metaEnumeration.addPanelVisibleAttribute("05#"
				+ SyntaxConcept.VAR_METAENUMVALUE);
		metaEnumeration.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_METAENUMVALUE + "#\n");

		metaEnumeration.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		metaEnumeration.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		SyntaxView syntaxMetaChildView = new SyntaxView("FullContext",
				"Context with Enumerations", "Context Palette", 0, null);
		@SuppressWarnings("unused")
		InstConcept childView = new InstConcept("FullContext", metaView,
				syntaxMetaChildView);
		// instView.addChildView(childView);
		// refas.getVariabilityVertex().put("FullContext", childView);
		InstConcept instVertexME = new InstConcept("ME",
				supportMetaElementConcept, metaEnumeration);
		refas.getVariabilityVertex().put("ME", instVertexME);

		InstConcept instViewME = new InstConcept("View ME Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View ME Relation", instViewME);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vme-tome", instEdge);
		instEdge.setIdentifier("vme-tome");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexME, true);
		instEdge.setSourceRelation(instViewME, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vme-fromview", instEdge);
		instEdge.setIdentifier("vme-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewME, true);
		instEdge.setSourceRelation(instViewC, true);

		syntaxMetaChildView = new SyntaxView("VariabContext",
				"Context without Enumerations", "Context Palette", 1, null);
		childView = new InstConcept("VariabContext", metaView,
				syntaxMetaChildView);
		// instView.addChildView(childView);
		// refas.getVariabilityVertex().put("VariabContext", childView);
		// syntaxMetaChildView.addConcept(metaEnumeration);

		// InstConcept instViewCG2 = new InstConcept("View CG2 Relation",
		// supportMetaViewPairwise, metaViewRel);
		// refas.getVariabilityVertex().put("View CG2 Relation", instViewCG2);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vcg2-tocv", instEdge);
		 * instEdge.setIdentifier("vcg2-tocv");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexCG, true);
		 * instEdge.setSourceRelation(instViewCG2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vcg2-fromview", instEdge);
		 * instEdge.setIdentifier("vcg2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewCG2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */

		// InstConcept instViewCV2 = new InstConcept("View CVar2 Relation",
		// supportMetaViewPairwise, metaViewRel);
		// refas.getVariabilityVertex().put("View CVar2 Relation", instViewCV2);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vcv2-tocv", instEdge);
		 * instEdge.setIdentifier("vcv2-tocv");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexCV, true);
		 * instEdge.setSourceRelation(instViewCV2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vcv2-fromview", instEdge);
		 * instEdge.setIdentifier("vcv2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewCV2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */

		// InstConcept instViewGV2 = new InstConcept("View GVar2 Relation",
		// supportMetaViewPairwise, metaViewRel);
		// refas.getVariabilityVertex().put("View GVar2 Relation", instViewGV2);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vgv2-tocv", instEdge);
		 * instEdge.setIdentifier("vgv2-tocv");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexGV, true);
		 * instEdge.setSourceRelation(instViewGV2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vgv2-fromview", instEdge);
		 * instEdge.setIdentifier("vgv2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewGV2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */

		// Direct variable relations

		InstElement directCVCGSemanticEdge = refas.getOperationalModel()
				.getVertex("CVCGPWAsso");

		SyntaxPairwiseRel metaVariableEdge = new SyntaxPairwiseRel(
				"Variable To Context Relation", true, true,
				"Variable To Context Relation", "",
				"Associates a Context Variable" + " with the Context Group",
				60, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directCVCGSemanticEdge);

		SyntaxPairwiseRel metaContextEdge = new SyntaxPairwiseRel(
				"Context To Context Relation", true, true,
				"Context To Context Relation", "", "Associates a Context Group"
						+ " with other Context Group", 60, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directCVCGSemanticEdge);

		metaContextEdge.addModelingAttribute("cardinality", "String", false,
				"cardinality", "", "", "", 0, -1, "", "", -1, "", "");
		metaContextEdge.addPanelVisibleAttribute("01#cardinality");
		metaContextEdge.addPropEditableAttribute("01#cardinality");
		metaContextEdge.addPropVisibleAttribute("01#cardinality");

		InstConcept instVariablePairWiseRel = new InstConcept(
				"Variable To Context Relation", supportMetaElementPairwise,
				metaVariableEdge);
		instVariablePairWiseRel.setInstAttribute("Type", "Control");
		instVariablePairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instVariablePairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		refas.getVariabilityVertex().put("Variable To Context Relation",
				instVariablePairWiseRel);

		InstConcept instCGPairWiseRel = new InstConcept(
				"Context Group Relation", supportMetaElementPairwise,
				metaContextEdge);
		instCGPairWiseRel.setInstAttribute("Type", "SubGroup");
		instCGPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instCGPairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		refas.getVariabilityVertex().put("Context Group Relation",
				instCGPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-var-pwrd", instEdge);
		instEdge.setIdentifier("variab-var-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVariablePairWiseRel, true);
		instEdge.setSourceRelation(instVertexVar, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-pwrd-cg", instEdge);
		instEdge.setIdentifier("variab-pwrd-cg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instVariablePairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("cg-CG-pwrd", instEdge);
		instEdge.setIdentifier("cg-CG-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instCGPairWiseRel, true);
		instEdge.setSourceRelation(instVertexCG, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("cg-pwrd-cg", instEdge);
		instEdge.setIdentifier("cg-pwrd-cg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instCGPairWiseRel, true);

		// *************************---------------****************************
		// *************************---------------****************************
		// SG satisficing Model

		syntaxMetaView = new SyntaxView(
				"SoftGoalsSatisficing",
				true,
				true,
				"SG Satisficing View",
				"plnode",
				"Defines a feature",
				100,
				80,
				"/com/variamos/gui/pl/editor/images/plnode.png",
				3,
				"SG Satisficing Palette - Goals;SG Satisficing Palette - Features",
				4, null);
		instViewC = new InstConcept("SoftGoalsSatisficing", metaView,
				syntaxMetaView);

		InstConcept instGroupOperClaimPairWiseRel = new InstConcept(
				"OperClaim Group Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGroupOperClaimPairWiseRel.setInstAttribute("Type", "Default");
		instGroupOperClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGroupOperClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("OperClaim Group Relation",
				instGroupOperClaimPairWiseRel);

		InstConcept instGroupLFClaimPairWiseRel = new InstConcept(
				"LFClaim Group Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGroupLFClaimPairWiseRel.setInstAttribute("Type", "Default");
		instGroupLFClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGroupLFClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("LFClaim Group Relation",
				instGroupLFClaimPairWiseRel);

		refas.getVariabilityVertex().put("SoftGoalsSatisficing", instViewC);

		InstConcept instViewTSGs = new InstConcept("ViewS TSG Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("ViewS TSG Relation", instViewTSGs);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vstsg-totsg", instEdge);
		instEdge.setIdentifier("vstsg-totsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexTSG, true);
		instEdge.setSourceRelation(instViewTSGs, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vstsg-fromview", instEdge);
		instEdge.setIdentifier("vstsg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewTSGs, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instViewGSGs = new InstConcept("ViewS GSG Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("ViewS GSG Relation", instViewGSGs);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vsgsg-togsg", instEdge);
		instEdge.setIdentifier("vsgsg-togsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGSG, true);
		instEdge.setSourceRelation(instViewGSGs, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vsgsg-fromview", instEdge);
		instEdge.setIdentifier("vsgsg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewGSGs, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instViewSGs = new InstConcept("ViewS SG Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("ViewS SG Relation", instViewSGs);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vssg-totsg", instEdge);
		instEdge.setIdentifier("vssg-totsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instViewSGs, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vssg-fromview", instEdge);
		instEdge.setIdentifier("vssg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewSGs, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instViewOpers = new InstConcept("ViewS Oper Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("ViewS Oper Relation", instViewOpers);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vsoper-totoper", instEdge);
		instEdge.setIdentifier("vsoper-tooper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instViewOpers, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vsoper-fromview", instEdge);
		instEdge.setIdentifier("vsoper-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewOpers, true);
		instEdge.setSourceRelation(instViewC, true);

		SyntaxPairwiseRel metaViewLFsg = new SyntaxPairwiseRel("ViewRelation",
				true, true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewLFsg.setPalette("SG Satisficing Palette - Features");

		InstConcept instViewLFs = new InstConcept("ViewS LF Relation",
				supportMetaViewPairwise, metaViewLFsg);
		refas.getVariabilityVertex().put("ViewS LF Relation", instViewLFs);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vslf-tolf", instEdge);
		instEdge.setIdentifier("vslf-tolf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instViewLFs, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vslf-fromview", instEdge);
		instEdge.setIdentifier("vslf-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewLFs, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept semClaim = ((InstConcept) refas.getOperationalModel()
				.getVertex("Claim"));

		SyntaxConcept syntaxClaim = new SyntaxConcept('C', "CL", true, true,
				"Claim", "refasclaim", "A claim includes a group of"
						+ " operationalizations and a logical condition"
						+ " to evaluate the claim satisfaction."
						+ " The claim is activated only when all the"
						+ " operationalizations are selected and the"
						+ " conditional expression is true. The claim"
						+ " includes a relation with a softgoal (SG)", 100, 50,
				"/com/variamos/gui/perspeditor/images/claim.png", true,
				Color.BLUE.toString(), 1, semClaim, true);

		syntaxClaim.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		syntaxClaim.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n");

		syntaxClaim.addModelingAttribute("name", "String", false, "Name", "",
				"", 0, -1, "", "", -1, "", "");

		syntaxClaim.addModelingAttribute("conditionalExpression",
				new ElemAttribute("conditionalExpression", "String",
						AttributeType.SYNTAX, false, "Cond. Expression Text",
						"", "", 0, -1, "", "", -1, "", ""));

		// syntaxClaim.addModelingAttribute("concern", "ConcernLevel", false,
		// "Concern Level", "", 0, -1, "", "", -1, "", "");

		// syntaxClaim.addPanelVisibleAttribute("03#" + "name");

		syntaxClaim.addPropEditableAttribute("03#" + "name");

		syntaxClaim.addPropVisibleAttribute("03#" + "name");

		// syntaxClaim.addPanelSpacersAttribute("#" + "name" + "#:\n");

		syntaxClaim.addPanelVisibleAttribute("10#" + "conditionalExpression");

		syntaxClaim.addPropEditableAttribute("10#" + "conditionalExpression");

		syntaxClaim.addPropVisibleAttribute("10#" + "conditionalExpression");

		InstConcept instVertexCL = new InstConcept("CL",
				supportMetaElementOverTwo, syntaxClaim);
		refas.getVariabilityVertex().put("CL", instVertexCL);

		InstConcept instViewCL = new InstConcept("View CL Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View CL Relation", instViewCL);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vcl-tocl", instEdge);
		instEdge.setIdentifier("vcl-tocl");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instViewCL, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vcl-fromview", instEdge);
		instEdge.setIdentifier("vcl-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewCL, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept semSoftDependency = ((InstConcept) refas
				.getOperationalModel().getVertex("SoftDependency"));
		SyntaxConcept syntaxSoftDependency = new SyntaxConcept(
				'C',
				"SoftDependency",
				true,
				true,
				"Soft Dependency",
				"refassoftdep",
				"A Soft Dependency"
						+ " (SD express a logical condition useful to express"
						+ " an expected level of"
						+ " satisfaction of a softgoal. The soft dependency is"
						+ " activated only when the conditional expression is true."
						+ " The SD includes a relation with a softgoal (SG)",
				100, 70, "/com/variamos/gui/perspeditor/images/softdep.png",
				true, Color.BLUE.toString(), 1, semSoftDependency, true);

		syntaxSoftDependency.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		syntaxSoftDependency.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n");

		syntaxSoftDependency.addModelingAttribute("name", "String", false,
				"Name", "", "", 0, -1, "", "", -1, "", "");

		// syntaxSoftDependency
		// .addModelingAttribute("conditionalExpression",
		// new SemanticAttribute("conditionalExpression",
		// "String", false, "Cond. Expression Text", "",
		// 0, -1, "", "", -1, "", ""));

		// syntaxSoftDependency.addModelingAttribute("concern", "ConcernLevel",
		// false, "Concern Level", "", 0, -1, "", "", -1, "", "");

		// syntaxSoftDependency.addPanelVisibleAttribute("03#" + "name");

		syntaxSoftDependency.addPropEditableAttribute("03#" + "name");

		syntaxSoftDependency.addPropVisibleAttribute("03#" + "name");

		// syntaxSoftDependency.addPanelSpacersAttribute("#" + "name" + "#:\n");

		// syntaxSoftDependency.addPanelVisibleAttribute("10#"
		// + "conditionalExpression");

		// syntaxSoftDependency.addPropEditableAttribute("10#"
		// + "conditionalExpression");

		// syntaxSoftDependency.addPropVisibleAttribute("10#"
		// + "conditionalExpression");

		InstConcept instVertexSD = new InstConcept("SoftDependency",
				supportMetaElementConcept, syntaxSoftDependency);
		refas.getVariabilityVertex().put("SoftDependency", instVertexSD);

		InstConcept instViewSD = new InstConcept("View SD Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View SD Relation", instViewSD);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vsd-tosd", instEdge);
		instEdge.setIdentifier("vsd-tosd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSD, true);
		instEdge.setSourceRelation(instViewSD, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vsd-fromview", instEdge);
		instEdge.setIdentifier("vsd-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewSD, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept semanticOperClaimGroupRelation = ((InstConcept) refas
				.getOperationalModel().getVertex("OperCLOTAsso"));

		hardMetaOverTwoRel = new SyntaxConcept(
				'O',
				"OperClaimOverTwoRel",
				true,
				true,
				"OperClaimOverTwoRel",
				"plgroup",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " expected on the softgoal in case the Claim is satisfied",
				20, 20, "/com/variamos/gui/pl/editor/images/plgroup.png",
				false, "white", 1, semanticOperClaimGroupRelation, false);

		InstElement semClaimPairwiseRel = refas.getOperationalModel()
				.getVertex("OperClaimPWAsso");

		SyntaxPairwiseRel metaClaimPairwiseRel = new SyntaxPairwiseRel(
				"ClaimRelation",
				true,
				true,
				"ClaimRelation",
				"",
				"Represent the relation between"
						+ " an operationalization(s) and a claim. The operationalization(s)"
						+ " is required to satisfy a claim", 60, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				semClaimPairwiseRel);

		InstConcept instGrpOperClaimPairWiseRel = new InstConcept(
				"GrpOperClaimRelation", supportMetaElementPairwise,
				metaClaimPairwiseRel);

		instGrpOperClaimPairWiseRel.setInstAttribute("Type", "OPER-CL");
		instGrpOperClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpOperClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("GrpOperClaimRelation",
				instGrpOperClaimPairWiseRel);

		InstConcept instGrpLFClaimPairWiseRel = new InstConcept(
				"GrpLFClaimRelation", supportMetaElementPairwise,
				metaClaimPairwiseRel);

		instGrpLFClaimPairWiseRel.setInstAttribute("Type", "LF-CL");
		instGrpLFClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpLFClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("GrpLFClaimRelation",
				instGrpLFClaimPairWiseRel);

		SyntaxPairwiseRel metaDirClaimPairwiseRel = new SyntaxPairwiseRel(
				"ClaimRelation",
				true,
				true,
				"ClaimRelation",
				"",
				"Represent the relation between"
						+ " an operationalization(s) and a claim. The operationalization(s)"
						+ " is required to satisfy a claim", 60, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				semClaimPairwiseRel);
		metaDirClaimPairwiseRel.addModelingAttribute("AggregationLow",
				"Integer", false, "Aggregation Low", "", 0, 0, -1, "", "", -1,
				"", "");
		metaDirClaimPairwiseRel.addPropEditableAttribute("03#"
				+ "AggregationLow");
		metaDirClaimPairwiseRel.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		metaDirClaimPairwiseRel.addPanelVisibleAttribute("03#"
				+ "AggregationLow" + "#" + "AggregationHigh" + "#!=#" + "0");
		metaDirClaimPairwiseRel.addPanelSpacersAttribute("[#"
				+ "AggregationLow" + "#..");

		metaDirClaimPairwiseRel.addModelingAttribute("AggregationHigh",
				"Integer", false, "Aggregation High", "", 0, 0, -1, "", "", -1,
				"", "");
		metaDirClaimPairwiseRel.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		metaDirClaimPairwiseRel.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		metaDirClaimPairwiseRel.addPanelVisibleAttribute("04#"
				+ "AggregationHigh" + "#" + "AggregationHigh" + "#!=#" + "0");
		metaDirClaimPairwiseRel.addPanelSpacersAttribute("#"
				+ "AggregationHigh" + "#]\n");

		InstConcept instDirOperClaimPairWiseRel = new InstConcept(
				"DirOperClaimRelation", supportMetaElementPairwise,
				metaDirClaimPairwiseRel);

		instDirOperClaimPairWiseRel.setInstAttribute("Type", "OPER-CL");
		instDirOperClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instDirOperClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("DirOperClaimRelation",
				instDirOperClaimPairWiseRel);

		InstConcept instDirLFClaimPairWiseRel = new InstConcept(
				"DirLFClaimRelation", supportMetaElementPairwise,
				metaDirClaimPairwiseRel);

		instDirLFClaimPairWiseRel.setInstAttribute("Type", "LF-CL");
		instDirLFClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instDirLFClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("DirLFClaimRelation",
				instDirLFClaimPairWiseRel);

		InstConcept instVertexOCOTR = new InstConcept("OperClaimOverTwoRel",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexOCOTR.getInstAttribute("Type").setValue("Group");

		refas.getVariabilityVertex()
				.put("OperClaimOverTwoRel", instVertexOCOTR);

		InstConcept instViewOCOTR = new InstConcept("View OC Group Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View OC Group Relation",
				instViewOCOTR);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vocotr-tovocotr", instEdge);
		instEdge.setIdentifier("vocotr-tovocotr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOCOTR, true);
		instEdge.setSourceRelation(instViewOCOTR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vocotr-fromview", instEdge);
		instEdge.setIdentifier("vocotr-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewOCOTR, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept semanticLFClaimGroupRelation = ((InstConcept) refas
				.getOperationalModel().getVertex("LFtoClaimOTAsso"));

		hardMetaOverTwoRel = new SyntaxConcept(
				'O',
				"LFClaimOTAsso",
				true,
				true,
				"LFClaimOTAsso",
				"plgroup",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " expected on the softgoal in case the Claim is satisfied",
				20, 20, "/com/variamos/gui/pl/editor/images/plgroup.png",
				false, "white", 1, semanticLFClaimGroupRelation, false);

		InstConcept instVertexFCOTR = new InstConcept("LFClaimOTAsso",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexFCOTR.getInstAttribute("Type").setValue("Group");

		refas.getVariabilityVertex().put("LFClaimOTAsso", instVertexFCOTR);

		SyntaxPairwiseRel metaViewLFCL = new SyntaxPairwiseRel("ViewRelation",
				true, true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewLFCL.setPalette("SG Satisficing Palette - Features");

		InstConcept instViewFCOTR = new InstConcept("View FC Group Relation",
				supportMetaViewPairwise, metaViewLFCL);
		refas.getVariabilityVertex().put("View FC Group Relation",
				instViewFCOTR);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vfcotr-tovfcotr", instEdge);
		instEdge.setIdentifier("vfcotr-tovfcotr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexFCOTR, true);
		instEdge.setSourceRelation(instViewFCOTR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vfcotr-fromview", instEdge);
		instEdge.setIdentifier("vfcotr-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewFCOTR, true);
		instEdge.setSourceRelation(instViewC, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-pwrd-opclaim", instEdge);
		instEdge.setIdentifier("sgs-pwrd-opclaim");
		instEdge.setEdSyntaxEle(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirOperClaimPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-oper-pwrd", instEdge);
		instEdge.setIdentifier("sgs-oper-pwrd");
		instEdge.setEdSyntaxEle(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirOperClaimPairWiseRel, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-pwrd-lfclaim", instEdge);
		instEdge.setIdentifier("sgs-pwrd-lfclaim");
		instEdge.setEdSyntaxEle(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirLFClaimPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-lf-pwrd", instEdge);
		instEdge.setIdentifier("sgs-lf-pwrd");
		instEdge.setEdSyntaxEle(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirLFClaimPairWiseRel, true);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-Oper-pwrg", instEdge);
		instEdge.setIdentifier("sgs-Oper-pwrg");
		instEdge.setTargetRelation(instGroupOperClaimPairWiseRel, true);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-pwrg-opclaimgp", instEdge);
		instEdge.setIdentifier("sgs-pwrg-opclaimgp");
		instEdge.setTargetRelation(instVertexOCOTR, true);
		instEdge.setEdSyntaxEle(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instGroupOperClaimPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-LF-pwrg", instEdge);
		instEdge.setIdentifier("sgs-LF-pwrg");
		instEdge.setTargetRelation(instGroupLFClaimPairWiseRel, true);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-pwrg-lfclaimgp", instEdge);
		instEdge.setIdentifier("sgs-pwrg-lfclaimgp");
		instEdge.setTargetRelation(instVertexFCOTR, true);
		instEdge.setEdSyntaxEle(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instGroupLFClaimPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-pwr-CL", instEdge);
		instEdge.setIdentifier("sgs-pwr-CL");
		instEdge.setEdSyntaxEle(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instGrpOperClaimPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-claimgp-pwr", instEdge);
		instEdge.setIdentifier("sgs-claimgp-pwr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpOperClaimPairWiseRel, true);
		instEdge.setSourceRelation(instVertexOCOTR, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-pwr-LFCL", instEdge);
		instEdge.setIdentifier("sgs-pwr-LFCL");
		instEdge.setEdSyntaxEle(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instGrpLFClaimPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-lfclaimgp-pwr", instEdge);
		instEdge.setIdentifier("sgs-lfclaimgp-pwr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpLFClaimPairWiseRel, true);
		instEdge.setSourceRelation(instVertexFCOTR, true);

		InstElement directSDSGSemanticEdge = refas.getOperationalModel()
				.getVertex("SDSGPWAsso");

		SyntaxPairwiseRel metaSDSGEdge = new SyntaxPairwiseRel(
				"SDSGRelation",
				true,
				true,
				"SDSGRelation",
				"",
				"Express the relation between"
						+ " the SD and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSDSGSemanticEdge);

		InstConcept instSDPairWiseRel = new InstConcept("SDSGRelation",
				supportMetaElementPairwise, metaSDSGEdge);

		instSDPairWiseRel.setInstAttribute("Type", "SD-SG");
		instSDPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instSDPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		refas.getVariabilityVertex().put("SDSGRelation", instSDPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-pwrd-SG", instEdge);
		instEdge.setIdentifier("variab-Spwrd-SG");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instSDPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("variab-SD-pwrd", instEdge);
		instEdge.setIdentifier("variab-SD-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instSDPairWiseRel, true);
		instEdge.setSourceRelation(instVertexSD, true);

		// getConstraintInstEdges().put("SDSGRelation", new InstEdge(
		// MetaEdge, metaSDSGEdge));

		InstElement directClaimSGSemanticEdge = refas.getOperationalModel()
				.getVertex("ClaimSGPWAsso");

		SyntaxPairwiseRel metaClaimSGEdge = new SyntaxPairwiseRel(
				"Claim-Softgoal Relation",
				true,
				true,
				"Claim-Softgoal Relation",
				"",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directClaimSGSemanticEdge);

		InstConcept instCLPairWiseRel = new InstConcept("CLSGRelation",
				supportMetaElementPairwise, metaClaimSGEdge);

		instCLPairWiseRel.setInstAttribute("Type", "CL-SG");
		instCLPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instCLPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		refas.getVariabilityVertex().put("CLSGRelation", instCLPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-pwrd-SG", instEdge);
		instEdge.setIdentifier("sgs-pwrdSG");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instCLPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("sgs-CL-pwrd", instEdge);
		instEdge.setIdentifier("sgs-CL-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instCLPairWiseRel, true);
		instEdge.setSourceRelation(instVertexCL, true);

		// *************************---------------****************************
		// *************************---------------****************************
		// Assets model

		syntaxMetaView = new SyntaxView("Assets", true, true, "Assets View",
				"plnode", "Defines an Asset", 100, 90,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Assets Palette - Opers;Assets Palette - Features", 5, null);
		instViewC = new InstConcept("Assets", metaView, syntaxMetaView);
		refas.getVariabilityVertex().put("Assets", instViewC);
		// syntaxMetaView.addConcept(sOperationalization);
		// syntaxMetaView.addConcept(syntaxVertexLF);

		InstConcept semAsset = ((InstConcept) refas.getOperationalModel()
				.getVertex("Asset"));
		SyntaxConcept syntaxAsset = new SyntaxConcept('C', "Asset", true, true,
				"Asset", "refasasset",
				"Represents a asset of the system. The most"
						+ " important assets to represent are those than"
						+ " can implement operationalizations", 100, 40,
				"/com/variamos/gui/perspeditor/images/component.png", true,
				Color.WHITE.toString(), 1, semAsset, true);

		syntaxAsset.addPanelVisibleAttribute("04#"
				+ SyntaxConcept.VAR_USERIDENTIFIER);
		syntaxAsset.addPanelSpacersAttribute("#"
				+ SyntaxConcept.VAR_USERIDENTIFIER + "#\n\n");

		syntaxAsset.addModelingAttribute("name", "String", false, "Name", "",
				"", 0, -1, "", "", -1, "", "");
		// syntaxAsset.addModelingAttribute("concern", "ConcernLevel", false,
		// "Concern Level", "", 0, -1, "", "", -1, "", "");

		// Create another meta element
		InstConcept instGroupAssetPairWiseRel = new InstConcept(
				"Soft Group Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGroupAssetPairWiseRel.setInstAttribute("Type", "Default");
		instGroupAssetPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGroupAssetPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("Soft Group Relation",
				instGroupAssetPairWiseRel);

		// syntaxMetaView.addConcept(syntaxAsset);

		// syntaxAsset.addPanelVisibleAttribute("03#" + "name");
		syntaxAsset.addPropEditableAttribute("03#" + "name");
		syntaxAsset.addPropVisibleAttribute("03#" + "name");
		syntaxMetaChildView = new SyntaxView("Assets", "Assets General View",
				"Assets Palette", 0, null);
		childView = new InstConcept("GeneralAssets", metaView,
				syntaxMetaChildView);
		// refas.getVariabilityVertex().put("GeneralAssets", childView);

		InstConcept instVertexAsset = new InstConcept("Asset",
				supportMetaElementConcept, syntaxAsset);
		refas.getVariabilityVertex().put("Asset", instVertexAsset);

		InstConcept semanticAssetOperGroupRelation = ((InstConcept) refas
				.getOperationalModel().getVertex("AssetOperOTAsso"));

		InstElement directAssetOperSemanticEdge = refas.getOperationalModel()
				.getVertex("AssetOperPWAsso");

		InstConcept semanticAssetAssetGroupRelation = ((InstConcept) refas
				.getOperationalModel().getVertex("AssetAssetOTAsso"));

		InstElement directAssetSemanticEdge = refas.getOperationalModel()
				.getVertex("varAssetPWAsso");

		hardMetaOverTwoRel = new SyntaxConcept('O', "AssetOperGroupDep", true,
				true, "AssetOperGroupDep", "plgroup",
				"Represents the implementation "
						+ "of an operationalization by a group of assets", 20,
				20, "/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticAssetOperGroupRelation, false);
		InstConcept instVertexAssetOper = new InstConcept("AssetOperGroupDep",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexAssetOper.getInstAttribute("Type").setValue("Group");
		refas.getVariabilityVertex().put("AssetOperGroupDep",
				instVertexAssetOper);

		hardMetaOverTwoRel = new SyntaxConcept('O', "AssetFeatGroupDep", true,
				true, "AssetFeatGroupDep", "plgroup",
				"Represents the implementation "
						+ "of a feautre by a group of assets", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticAssetOperGroupRelation, false);
		InstConcept instVertexAssetFeat = new InstConcept("AssetFeatGroupDep",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexAssetFeat.getInstAttribute("Type").setValue("Group");
		refas.getVariabilityVertex().put("AssetFeatGroupDep",
				instVertexAssetFeat);

		hardMetaOverTwoRel = new SyntaxConcept('O', "AssetAssetOTAsso", true,
				true, "AssetAssetOTAsso", "plgroup", "Represents the relation "
						+ "of an asset with a group of assets", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticAssetAssetGroupRelation, false);
		InstConcept instVertexAssetAsset = new InstConcept("AssetAssetOTAsso",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexAssetAsset.getInstAttribute("Type").setValue("Group");
		refas.getVariabilityVertex().put("AssetAssetOTAsso",
				instVertexAssetAsset);

		SyntaxPairwiseRel metaOperPairWiseRel = new SyntaxPairwiseRel(
				"Asset To Oper Relation", true, true, "Asset To Oper Relation",
				"", "Represents the "
						+ "implementation of an operationzalization by an"
						+ " asset", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directAssetOperSemanticEdge);

		InstConcept instDirOperPairWiseRel = new InstConcept(
				"Dir Asset To Oper Relation", supportMetaElementPairwise,
				metaOperPairWiseRel);

		instDirOperPairWiseRel.setInstAttribute("Type", "Implementation");
		instDirOperPairWiseRel.setInstAttribute("SourceCardinality", "[0..1]");
		instDirOperPairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		refas.getVariabilityVertex().put("Dir Asset To Oper Relation",
				instDirOperPairWiseRel);

		InstConcept instGrpOperPairWiseRel = new InstConcept(
				"Group Asset To Oper Relation", supportMetaElementPairwise,
				metaOperPairWiseRel);

		instGrpOperPairWiseRel.setInstAttribute("Type", "Implementation");
		instGrpOperPairWiseRel.setInstAttribute("SourceCardinality", "[0..1]");
		instGrpOperPairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		refas.getVariabilityVertex().put("Group Asset To Oper Relation",
				instGrpOperPairWiseRel);

		InstConcept instGrpLFPairWiseRel = new InstConcept(
				"Group Asset To L Relation", supportMetaElementPairwise,
				metaOperPairWiseRel);

		instGrpLFPairWiseRel.setInstAttribute("Type", "Implementation");
		instGrpLFPairWiseRel.setInstAttribute("SourceCardinality", "[0..1]");
		instGrpLFPairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		refas.getVariabilityVertex().put("Group Asset To LF Relation",
				instGrpLFPairWiseRel);

		SyntaxPairwiseRel metaFeaturePairWiseRel = new SyntaxPairwiseRel(
				"Asset To Feature Relation", true, true,
				"Asset To Feature Relation", "", "Represents the "
						+ "implementation of an feature by an" + " asset", 50,
				50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directAssetOperSemanticEdge);

		InstConcept instDirFeatPairWiseRel = new InstConcept(
				"Asset To Feature Relation", supportMetaElementPairwise,
				metaFeaturePairWiseRel);
		instDirFeatPairWiseRel.setInstAttribute("Type", "Implementation");
		instDirFeatPairWiseRel.setInstAttribute("SourceCardinality", "[0..1]");
		instDirFeatPairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		refas.getVariabilityVertex().put("Asset To Feature Relation",
				instDirFeatPairWiseRel);

		SyntaxPairwiseRel metaAssetPairWiseRel = new SyntaxPairwiseRel(
				"Asset To Asset Relation", true, true,
				"Asset To Asset Relation", "",
				"Represents a " + "type of an operationzalization between "
						+ " assets", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directAssetSemanticEdge);
		metaAssetPairWiseRel.addModelingAttribute("AggregationLow", "Integer",
				false, "Aggregation Low", "", 0, 0, -1, "", "", -1, "", "");
		metaAssetPairWiseRel.addPropEditableAttribute("03#" + "AggregationLow");
		metaAssetPairWiseRel.addPropVisibleAttribute("03#" + "AggregationLow");
		metaAssetPairWiseRel.addPanelVisibleAttribute("03#" + "AggregationLow"
				+ "#" + "AggregationHigh" + "#!=#" + "0");
		metaAssetPairWiseRel.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");

		metaAssetPairWiseRel.addModelingAttribute("AggregationHigh", "Integer",
				false, "AggregationHigh", "", 0, 0, -1, "", "", -1, "", "");
		metaAssetPairWiseRel
				.addPropEditableAttribute("04#" + "AggregationHigh");
		metaAssetPairWiseRel.addPropVisibleAttribute("04#" + "AggregationHigh");
		metaAssetPairWiseRel.addPanelVisibleAttribute("04#" + "AggregationHigh"
				+ "#" + "AggregationHigh" + "#!=#" + "0");
		metaAssetPairWiseRel.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");

		InstConcept instDirAssetPairWiseRel = new InstConcept(
				"Dir Asset To Asset Relation", supportMetaElementPairwise,
				metaAssetPairWiseRel);

		instDirAssetPairWiseRel.setInstAttribute("Type", "ASSET-ASSET");
		instDirAssetPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instDirAssetPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		refas.getVariabilityVertex().put("Dir Asset To Asset Relation",
				instDirAssetPairWiseRel);

		SyntaxPairwiseRel metaGrpAssetPairWiseRel = new SyntaxPairwiseRel(
				"Asset To Asset Relation", true, true,
				"Asset To Asset Relation", "",
				"Represents a " + "type of an operationzalization between "
						+ " assets", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directAssetSemanticEdge);

		InstConcept instGrpAssetPairWiseRel = new InstConcept(
				"Group Asset To Asset Relation", supportMetaElementPairwise,
				metaGrpAssetPairWiseRel);

		instGrpAssetPairWiseRel.setInstAttribute("Type", "ASSET-ASSET");
		instGrpAssetPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instGrpAssetPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		refas.getVariabilityVertex().put("Group Asset To Asset Relation",
				instGrpAssetPairWiseRel);

		InstConcept instGrpAssetAssetPairWiseRel = new InstConcept(
				"Group Asset-Asset Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGrpAssetAssetPairWiseRel.setInstAttribute("Type", "Default");
		instGrpAssetAssetPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpAssetAssetPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("Group Asset-Asset Relation",
				instGrpAssetAssetPairWiseRel);

		InstConcept instGrpAssetOperPairWiseRel = new InstConcept(
				"Group Asset-Oper Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGrpAssetOperPairWiseRel.setInstAttribute("Type", "Default");
		instGrpAssetOperPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpAssetOperPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("Group Asset-Oper Relation",
				instGrpAssetOperPairWiseRel);

		InstConcept instGrpAssetFeatPairWiseRel = new InstConcept(
				"Group Asset-Feat Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGrpAssetFeatPairWiseRel.setInstAttribute("Type", "Default");
		instGrpAssetFeatPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpAssetFeatPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		refas.getVariabilityVertex().put("Group Asset-Feat Relation",
				instGrpAssetFeatPairWiseRel);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-assetoper-pwrd", instEdge);
		instEdge.setIdentifier("asset0-assetoper-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirOperPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-pwrd-assetoper", instEdge);
		instEdge.setIdentifier("asset0-pwrd-assetoper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instDirOperPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-assetlf-pwrd", instEdge);
		instEdge.setIdentifier("asset0-assetlf-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirFeatPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-pwrd-assetlf", instEdge);
		instEdge.setIdentifier("asset0-pwrd-assetlf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instDirFeatPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-assetasset-pwrd", instEdge);
		instEdge.setIdentifier("asset0-assetasset-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirAssetPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-pwrd-assetasset", instEdge);
		instEdge.setIdentifier("asset0-pwrd-assetasset");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instDirAssetPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-asset-pwrgo", instEdge);
		instEdge.setIdentifier("asset0-asset-pwrgo");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpAssetOperPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-pwrgo-assetgp", instEdge);
		instEdge.setIdentifier("asset0-pwrgo-assetgp");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetOper, true);
		instEdge.setSourceRelation(instGrpAssetOperPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-asset-pwrgo2", instEdge);
		instEdge.setIdentifier("asset0-asset-pwrgo2");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpAssetFeatPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-pwrgo-assetgp2", instEdge);
		instEdge.setIdentifier("asset0-pwrgo-assetgp2");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetFeat, true);
		instEdge.setSourceRelation(instGrpAssetFeatPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-pwro-oper", instEdge);
		instEdge.setIdentifier("asset0-pwro-oper");
		instEdge.setEdSyntaxEle(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instGrpOperPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-assetgp-pwro", instEdge);
		instEdge.setIdentifier("asset0-assetgp-pwro");
		instEdge.setEdSyntaxEle(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpOperPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAssetOper, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-fpwro-oper", instEdge);
		instEdge.setIdentifier("asset0-fpwro-oper");
		instEdge.setEdSyntaxEle(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instGrpLFPairWiseRel, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-assetgp-fpwro", instEdge);
		instEdge.setIdentifier("asset0-assetgp-fpwro");
		instEdge.setEdSyntaxEle(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpLFPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAssetFeat, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-asset-fpwrg", instEdge);
		instEdge.setIdentifier("asset0-asset-fpwrg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpAssetAssetPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-pwrg-assetgp", instEdge);
		instEdge.setIdentifier("asset0-pwrg-assetgp");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetAsset, true);
		instEdge.setSourceRelation(instGrpAssetAssetPairWiseRel, true);
		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-assetgp-pwr", instEdge);
		instEdge.setIdentifier("asset0-assetgp-pwr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpAssetPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAssetAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("asset0-pwr-asset", instEdge);
		instEdge.setIdentifier("asset0-pwr-asset");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instGrpAssetPairWiseRel, true);

		InstConcept instViewAsset4 = new InstConcept("View Asset Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View Asset Relation", instViewAsset4);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vasset4-toasset", instEdge);
		 * instEdge.setIdentifier("vasset4-toasset");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexAsset, true);
		 * instEdge.setSourceRelation(instViewAsset4, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vasset4-fromview", instEdge);
		 * instEdge.setIdentifier("vasset4-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAsset4, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		/*
		 * InstConcept instViewAOper3 = new InstConcept("ViewA Oper3 Relation",
		 * supportMetaViewPairwise, metaViewRel);
		 * refas.getVariabilityVertex().put("ViewA Oper3 Relation",
		 * instViewAOper3);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vaoper3-tooper", instEdge);
		 * instEdge.setIdentifier("vaoper3-tooper");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexOper, true);
		 * instEdge.setSourceRelation(instViewAOper3, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vaoper3-fromview", instEdge);
		 * instEdge.setIdentifier("vaoper3-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAOper3, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		SyntaxPairwiseRel metaViewLF2 = new SyntaxPairwiseRel("ViewRelation",
				true, true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewLF2.setPalette("Assets Palette - Features");

		/*
		 * InstConcept instViewLF2 = new InstConcept("View LF2 Relation",
		 * supportMetaViewPairwise, metaViewLF2);
		 * refas.getVariabilityVertex().put("View LF2 Relation", instViewLF2);
		 */
		SyntaxPairwiseRel metaViewLF3 = new SyntaxPairwiseRel("ViewRelation",
				true, true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewLF3.setPalette("Assets Palette - Features");

		InstConcept instViewLF3 = new InstConcept("View LF3 Relation",
				supportMetaViewPairwise, metaViewLF3);
		refas.getVariabilityVertex().put("View LF3 Relation", instViewLF3);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vlf3-tolft", instEdge);
		instEdge.setIdentifier("vlf3-tolft");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instViewLF3, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vlf3-fromview", instEdge);
		instEdge.setIdentifier("vlf3-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewLF3, true);
		instEdge.setSourceRelation(instViewC, true);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vlf2-tolft", instEdge);
		 * instEdge.setIdentifier("vlf2-tolft");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexLF, true);
		 * instEdge.setSourceRelation(instViewLF2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vlf2-fromview", instEdge);
		 * instEdge.setIdentifier("vlf2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewLF2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		SyntaxPairwiseRel metaViewAsFG2 = new SyntaxPairwiseRel("ViewRelation",
				true, true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewAsFG2.setPalette("Assets Palette - Features");

		InstConcept instViewAssetLF2 = new InstConcept("View ASSLF Relation",
				supportMetaViewPairwise, metaViewAsFG2);
		refas.getVariabilityVertex().put("View ASSLF Relation",
				instViewAssetLF2);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vassetlf-toassoper", instEdge);
		 * instEdge.setIdentifier("vassetlf-toassoper");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexAssetFeat, true);
		 * instEdge.setSourceRelation(instViewAssetLF2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vassetlf2-fromview", instEdge);
		 * instEdge.setIdentifier("vassetlf2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAssetLF2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		SyntaxPairwiseRel metaViewAsFG = new SyntaxPairwiseRel("ViewRelation",
				true, true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewAsFG.setPalette("Assets Palette - Features");

		InstConcept instViewAssetLF = new InstConcept("View ASSLF Relation",
				supportMetaViewPairwise, metaViewAsFG);

		refas.getVariabilityVertex()
				.put("View ASSLF Relation", instViewAssetLF);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vlfoper2-toassoper", instEdge);
		instEdge.setIdentifier("vlfoper2-toassoper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetFeat, true);
		instEdge.setSourceRelation(instViewAssetLF, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vlfoper-fromview", instEdge);
		instEdge.setIdentifier("vlfoper-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewAssetLF, true);
		instEdge.setSourceRelation(instViewC, true);

		/*
		 * InstConcept instViewAssOper = new
		 * InstConcept("View AssOper Relation", supportMetaViewPairwise,
		 * metaViewRel); refas.getVariabilityVertex()
		 * .put("View AssOper Relation", instViewAssOper);
		 */
		InstConcept instViewAssOper2 = new InstConcept(
				"View AssOper2 Relation", supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View AssOper2 Relation",
				instViewAssOper2);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vassoper-toassoper", instEdge);
		instEdge.setIdentifier("vassoper-toassoper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetOper, true);
		instEdge.setSourceRelation(instViewAssOper2, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vassoper2-fromview", instEdge);
		instEdge.setIdentifier("vassoper2-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewAssOper2, true);
		instEdge.setSourceRelation(instViewC, true);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vassoper3-toassoper", instEdge);
		 * instEdge.setIdentifier("vassoper3-toassoper");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexAssetOper, true);
		 * instEdge.setSourceRelation(instViewAssOper, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vassoper-fromview", instEdge);
		 * instEdge.setIdentifier("vassoper-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAssOper, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		syntaxMetaChildView = new SyntaxView("FunctionalAssets",
				"Functional Assets Relations", "Assets Palette", 1, null);
		childView = new InstConcept("FunctionalAssets", metaView,
				syntaxMetaChildView);
		// instView.addChildView(childView);
		// syntaxMetaChildView.addConcept(sOperationalization);
		// childView.addInstVertex(instVertexOper);
		// refas.getVariabilityVertex().put("FunctionalAssets", childView);

		InstConcept instViewAsset = new InstConcept("View Asset Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("View Asset Relation", instViewAsset);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vasset-toasset", instEdge);
		instEdge.setIdentifier("vasset-toasset");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instViewAsset, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vasset-fromview", instEdge);
		instEdge.setIdentifier("vasset-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewAsset, true);
		instEdge.setSourceRelation(instViewC, true);

		// InstConcept instViewAsset2 = new InstConcept("View Asset2 Relation",
		// supportMetaViewPairwise, metaViewRel);
		// refas.getVariabilityVertex().put("View Asset2 Relation",
		// instViewAsset2);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vasset2-toasset", instEdge);
		 * instEdge.setIdentifier("vasset2-toasset");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexAsset, true);
		 * instEdge.setSourceRelation(instViewAsset2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vasset2-fromview", instEdge);
		 * instEdge.setIdentifier("vasset2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAsset2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		InstConcept instViewAOper = new InstConcept("ViewA Oper Relation",
				supportMetaViewPairwise, metaViewRel);
		refas.getVariabilityVertex().put("ViewA Oper Relation", instViewAOper);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vaoper-tooper", instEdge);
		instEdge.setIdentifier("vaoper-tooper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instViewAOper, true);

		instEdge = new InstPairwiseRel();
		refas.getConstraintInstEdges().put("vaoper-fromview", instEdge);
		instEdge.setIdentifier("vaoper-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewAOper, true);
		instEdge.setSourceRelation(instViewC, true);

		// InstConcept instViewAOper2 = new InstConcept("ViewA Oper2 Relation",
		// supportMetaViewPairwise, metaViewRel);
		// refas.getVariabilityVertex().put("ViewA Oper2 Relation",
		// instViewAOper2);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vaoper2-tooper", instEdge);
		 * instEdge.setIdentifier("vaoper2-tooper");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexOper, true);
		 * instEdge.setSourceRelation(instViewAOper2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vaoper2-fromview", instEdge);
		 * instEdge.setIdentifier("vaoper2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAOper2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		syntaxMetaChildView = new SyntaxView("StructuralAssets",
				"Structural Assets Relations", "Assets Palette", 2, null);
		childView = new InstConcept("StructuralAssets", metaView,
				syntaxMetaChildView);
		// instView.addChildView(childView);
		// refas.getVariabilityVertex().put("StructuralAssets", childView);

		// syntaxMetaChildView.addConcept(sOperationalization);
		// childView.addInstVertex(instVertexOper);

		// syntaxMetaView.addConcept(syntaxGroupDependency);
		// syntaxMetaChildView.addConcept(syntaxGroupDependency);

		// InstConcept instViewAsset3 = new InstConcept("View Asset3 Relation",
		// supportMetaViewPairwise, metaViewRel);
		// refas.getVariabilityVertex().put("View Asset3 Relation",
		// instViewAsset3);

		/*
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vasset3-toasset", instEdge);
		 * instEdge.setIdentifier("vasset3-toasset");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexAsset, true);
		 * instEdge.setSourceRelation(instViewAsset3, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * refas.getConstraintInstEdges().put("vasset3-fromview", instEdge);
		 * instEdge.setIdentifier("vasset3-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAsset3, true);
		 * instEdge.setSourceRelation(childView, true);
		 */

	}
}
