package com.variamos.dynsup.defaultmodels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.ConceptType;

public class InfraBasicSyntaxMMMM {
	public static void createBasicSyntaxMetaMetaMetaModel(
			ModelInstance modelInstance) {

		Map<String, InstElement> variabilityInstVertex = modelInstance
				.getVariabilityVertex();

		SyntaxElement basicOpersSyntaxM3Concept = new SyntaxElement('C',
				"OMMConcept", true, true, "OMMConcept",
				"infrabasicsyntaxm3miniconcept",
				"Operations Meta Meta Meta Concept", 180, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Concept.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		// basicOpersSyntaxM3Concept.addPanelVisibleAttribute("04#"
		// + SyntaxElement.VAR_USERIDENTIFIER);
		// basicOpersSyntaxM3Concept.addPanelSpacersAttribute("#"
		// + SyntaxElement.VAR_USERIDENTIFIER + "#\n\n");

		basicOpersSyntaxM3Concept.addModelingAttribute("Name",
				new ElemAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", "", 0, -1, "", "", -1, "",
						""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Description",
				new ElemAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", "", 0,
						-1, "", "", -1, "", ""));

		basicOpersSyntaxM3Concept.addModelingAttribute("MetaType",
				new ElemAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "SyntaxConcept Type",
						ConceptType.class.getCanonicalName(), "SyntaxConcept",
						"", "", 0, -1, "", "", -1, "", ""));
		// metaBasicConcept.addModelingAttribute("Identifier",
		// new ElemAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Visible",
				new ElemAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", "", true, 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept
				.addModelingAttribute("Editable", new ElemAttribute("Editable",
						"Boolean", AttributeType.SYNTAX, false, "Editable", "",
						true, 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Name",
				new ElemAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", "", 0, -1, "", "", -1, "",
						""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Style",
				new ElemAttribute("Style", "String", AttributeType.SYNTAX,
						false, "Drawing Style", "", "refasclaim", 0, -1, "",
						"", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Width",
				new ElemAttribute("Width", "Integer", AttributeType.SYNTAX,
						false, "Initial Width", "", 100, 0, -1, "", "", -1, "",
						""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Height",
				new ElemAttribute("Height", "Integer", AttributeType.SYNTAX,
						false, "Initial Height", "", 40, 0, -1, "", "", -1, "",
						""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Image",
				new ElemAttribute("Image", "String", AttributeType.SYNTAX,
						false, "Image File", "",
						"/com/variamos/gui/perspeditor/images/claim.png", 0,
						-1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("TopConcept",
				new ElemAttribute("TopConcept", "Boolean",
						AttributeType.SYNTAX, false, "Is Top Concept", "",
						true, 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("BackgroundColor",
				new ElemAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color", "",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		basicOpersSyntaxM3Concept.addModelingAttribute("BorderStroke",
				new ElemAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", "", 1, 0,
						-1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Resizable",
				new ElemAttribute("Resizable", "Boolean", AttributeType.SYNTAX,
						false, "Is Resizable", "", true, 0, -1, "", "", -1, "",
						""));

		InstConcept instInfraSyntaxOpersM2Concept = new InstConcept(
				"OMMConcept", null, basicOpersSyntaxM3Concept);
		instInfraSyntaxOpersM2Concept
				.setTransSupInstElement(instInfraSyntaxOpersM2Concept);
		instInfraSyntaxOpersM2Concept.createInstAttributes(null);
		instInfraSyntaxOpersM2Concept.copyValuesToInstAttributes(null);
		variabilityInstVertex.put("OMMConcept", instInfraSyntaxOpersM2Concept);

		SyntaxElement basicOpersSyntaxM3Enum = new SyntaxElement('E',
				"OMMEnum", true, true, "OMMEnum",
				"infrabasicsyntaxm3miniconcept",
				"Operations Meta Meta Meta Enumeration", 180, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Enum.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<OMMEnum>>\n#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n\n", "");

		basicOpersSyntaxM3Enum.addModelingAttribute("other", "Set", false,
				"att", "", SyntaxElement.VAR_METAENUMVALUECLASS,
				new ArrayList<InstAttribute>(), 0, 1, "", "", 5, "#" + "other"
						+ "#all#\n", "");

		basicOpersSyntaxM3Enum.addModelingAttribute("Name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", "", 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("Description",
				new ElemAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", "", 0,
						-1, "", "", -1, "", ""));

		basicOpersSyntaxM3Enum.addModelingAttribute("MetaType",
				new ElemAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "SyntaxConcept Type",
						ConceptType.class.getCanonicalName(), "SyntaxConcept",
						"", "", 0, -1, "", "", -1, "", ""));
		// metaBasicConcept.addModelingAttribute("Identifier",
		// new ElemAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("Visible",
				new ElemAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", "", true, 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum
				.addModelingAttribute("Editable", new ElemAttribute("Editable",
						"Boolean", AttributeType.SYNTAX, false, "Editable", "",
						true, 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("Name", new ElemAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", "", 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("Style", new ElemAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "", "refasclaim", 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("Width", new ElemAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", "", 100, 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("Height",
				new ElemAttribute("Height", "Integer", AttributeType.SYNTAX,
						false, "Initial Height", "", 40, 0, -1, "", "", -1, "",
						""));
		basicOpersSyntaxM3Enum.addModelingAttribute("Image", new ElemAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"", "/com/variamos/gui/perspeditor/images/claim.png", 0, -1,
				"", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("TopConcept",
				new ElemAttribute("TopConcept", "Boolean",
						AttributeType.SYNTAX, false, "Is Top Concept", "",
						true, 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("BackgroundColor",
				new ElemAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color", "",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		basicOpersSyntaxM3Enum.addModelingAttribute("BorderStroke",
				new ElemAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", "", 1, 0,
						-1, "", "", -1, "", ""));
		basicOpersSyntaxM3Enum.addModelingAttribute("Resizable",
				new ElemAttribute("Resizable", "Boolean", AttributeType.SYNTAX,
						false, "Is Resizable", "", true, 0, -1, "", "", -1, "",
						""));

		InstConcept instInfraSyntaxOpersM2Enum = new InstConcept("OMMEnum",
				instInfraSyntaxOpersM2Concept, basicOpersSyntaxM3Enum);
		variabilityInstVertex.put("OMMEnum", instInfraSyntaxOpersM2Enum);

		// TODO add OMMAssociation

		// TODO add OMMExtends

	}
}
