package com.variamos.perspsupport.perspmodel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cfm.common.AbstractModel;
import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.VariabilityElement;
import com.variamos.perspsupport.expressionsupport.SemanticExpressionType;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstEnumeration;
import com.variamos.perspsupport.instancesupport.InstOverTwoRelation;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.instancesupport.InstVertex;
import com.variamos.perspsupport.semanticinterface.IntSemanticRelationType;
import com.variamos.perspsupport.semanticsupport.SemanticConcept;
import com.variamos.perspsupport.semanticsupport.SemanticPairwiseRelation;
import com.variamos.perspsupport.semanticsupport.SemanticRelationType;
import com.variamos.perspsupport.syntaxsupport.MetaConcept;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaEnumeration;
import com.variamos.perspsupport.syntaxsupport.MetaPairwiseRelation;
import com.variamos.perspsupport.syntaxsupport.MetaView;
import com.variamos.perspsupport.syntaxsupport.SemanticAttribute;
import com.variamos.perspsupport.syntaxsupport.SyntaxAttribute;
import com.variamos.perspsupport.types.ConceptType;
import com.variamos.perspsupport.types.PerspectiveType;
import com.variamos.semantic.types.AttributeType;

/**
 * A class to represent the model with vertex and edges. Maintains the
 * collections from ProductLine but are not used. Methods should be standardized
 * to use the HLCL. Initially based on ProductLine class. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-10
 */
public class RefasModel extends AbstractModel {

	private RefasModel syntaxRefas;
	private RefasModel semanticRefas;
	private Map<String, SemanticExpressionType> semanticExpressionTypes;

	/**
	 * 
	 */
	private Map<String, InstElement> variabilityInstVertex;

	/**
	 * 
	 */
	private Map<String, InstOverTwoRelation> instGroupDependencies;
	/**
	 * 
	 */
	private Map<String, InstPairwiseRelation> constraintInstEdges;

	/**
	 * 
	 */
	protected String name;

	private PerspectiveType perspectiveType;

	public RefasModel(PerspectiveType perspectiveType,
			Map<String, SemanticExpressionType> metaExpressionTypes) {
		this(perspectiveType, metaExpressionTypes, null, null);
	}

	public RefasModel(Map<String, SemanticExpressionType> metaExpressionTypes,
			RefasModel syntaxRefas) {
		this(PerspectiveType.SEMANTIC, metaExpressionTypes, syntaxRefas, null);
	}

	public RefasModel(PerspectiveType perspectiveType,
			Map<String, SemanticExpressionType> semanticExpressionTypes,
			RefasModel syntaxRefas, RefasModel semanticRefas) {
		this.perspectiveType = perspectiveType;
		this.syntaxRefas = syntaxRefas;
		this.semanticExpressionTypes = semanticExpressionTypes;
		this.semanticRefas = semanticRefas;
		variabilityInstVertex = new HashMap<String, InstElement>();
		instGroupDependencies = new HashMap<String, InstOverTwoRelation>();
		constraintInstEdges = new HashMap<String, InstPairwiseRelation>();
		name = "";

		switch (perspectiveType) {
		case CORESEMANTIC:
			createCoreSemantic();
			break;
		case CORESYNTAX:
			createCoreSyntax();
			break;
		case MODELING:
			break;
		case SEMANTIC:
			createDefaultSemantic();
			break;
		case CONFIG_SIMULATION:
			break;
		case SYNTAX:
			createDefaultSyntax();
			break;
		default:
			break;
		}
	}

	public RefasModel getSyntaxRefas() {
		return syntaxRefas;
	}

	public String getInstViewName(int modelViewInd, int modelViewSubInd) {
		// List<InstView> instViews = this.getSyntaxRefas().getInstViews();
		List<InstElement> instViews = this.getSyntaxRefas()
				.getVariabilityVertex("View");
		if (modelViewInd == -1)
			if (instViews.size() > 0)
				return ((MetaView) instViews.get(0).getEditableMetaElement())
						.getAutoIdentifier();
			else
				return "";
		if (modelViewInd < instViews.size() && modelViewSubInd == -1)
			return ((MetaView) instViews.get(modelViewInd)
					.getEditableMetaElement()).getAutoIdentifier();

		if (modelViewInd != -1 && modelViewInd < instViews.size()
				&& modelViewSubInd != -1)
			// && modelViewSubInd < instViews.get(modelViewInd)
			// .getChildViews().size())
			return "";// ((MetaView) instViews.get(modelViewInd).getChildViews()
						// .get(modelViewSubInd).getEditableMetaElement())
						// .getIdentifier();
		return null;
	}

	public String getInstViewPalettesName(int modelViewInd, int modelViewSubInd) {
		// List<InstView> instViews = this.getSyntaxRefas().getInstViews();
		List<InstElement> instViews = this.getSyntaxRefas()
				.getVariabilityVertex("View");
		if (modelViewInd == -1)
			if (instViews.size() > 0)
				return ((MetaView) instViews.get(0).getEditableMetaElement())
						.getPaletteName();
			else
				return "";
		if (modelViewInd < instViews.size() && modelViewSubInd == -1)
			return ((MetaView) instViews.get(modelViewInd)
					.getEditableMetaElement()).getPaletteName();

		if (modelViewInd != -1 && modelViewInd < instViews.size()
				&& modelViewSubInd != -1)
			// && modelViewSubInd < instViews.get(modelViewInd)
			// .getChildViews().size())
			return "";// ((MetaView) instViews.get(modelViewInd).getChildViews()
						// .get(modelViewSubInd).getEditableMetaElement())
						// .getPaletteName();
		return null;
	}

	public Map<String, SemanticExpressionType> getSemanticExpressionTypes() {
		return semanticExpressionTypes;
	}

	public RefasModel getSemanticRefas() {
		return semanticRefas;
	}

	public Map<String, InstOverTwoRelation> getInstGroupDependencies() {
		return instGroupDependencies;
	}

	public Collection<InstOverTwoRelation> getInstGroupDependenciesCollection() {
		return instGroupDependencies.values();
	}

	public Map<String, InstPairwiseRelation> getConstraintInstEdges() {
		return constraintInstEdges;
	}

	public Collection<InstPairwiseRelation> getConstraintInstEdgesCollection() {
		return constraintInstEdges.values();
	}

	@Deprecated
	public Collection<Constraint> getConstraints() {
		return null;
	}

	public void setConstraints(Map<String, Constraint> constraints) {

	}

	@Deprecated
	public Collection<VariabilityElement> getVariabilityElements() {
		return null;
	}

	public void putVariabilityInstVertex(InstVertex element) {
		variabilityInstVertex.put(element.getIdentifier(), element);
	}

	public void putInstGroupDependency(InstOverTwoRelation groupDep) {
		instGroupDependencies.put(groupDep.getIdentifier(), groupDep);
	}

	public void putConstraintInstEdge(InstPairwiseRelation element) {
		constraintInstEdges.put(element.getIdentifier(), element);
	}

	public String addNewVariabilityInstElement(InstVertex element) {
		String id = getNextVariabilityInstVertextId(element);
		InstVertex varElement = (InstVertex) element;
		varElement.setIdentifier(id);
		varElement.setInstAttribute("name", id);
		variabilityInstVertex.put(id, element);
		return id;
	}

	public String addNewInstGroupDependency(InstOverTwoRelation groupDep) {
		String id = getNextInstGroupDependencyId(groupDep);
		groupDep.setIdentifier(id);
		groupDep.setInstAttribute("name", id);
		instGroupDependencies.put(id, groupDep);
		return id;
	}

	public String addNewConstraintInstEdge(InstPairwiseRelation directRelation) {
		String id = getNextConstraintInstEdgeId(directRelation);
		directRelation.setIdentifier(id);
		constraintInstEdges.put(id, directRelation);
		return id;
	}

	private String getNextVariabilityInstVertextId(InstVertex element) {
		int id = 1;
		String classId = null;
		if (element instanceof InstVertex)
			if (((InstVertex) element).getTransSupportMetaElement()
					.getUserIdentifier() == null)
				classId = ((InstVertex) element).getTransSupportMetaElement()
						.getAutoIdentifier();
			else
				classId = ((InstVertex) element).getTransSupportMetaElement()
						.getUserIdentifier();

		// if (element instanceof InstConcept)
		// if (((InstConcept) element).getTransSupportMetaElement()
		// .getUserIdentifier() == null)
		// classId = ((InstConcept) element).getTransSupportMetaElement()
		// .getAutoIdentifier();
		// else
		// classId = ((InstConcept) element).getTransSupportMetaElement()
		// .getUserIdentifier();
		// // .getSupportMetaElementUserIdentifier();
		// else {
		// if (element instanceof InstEnumeration)
		// if (((InstEnumeration) element).getTransSupportMetaElement()
		// .getUserIdentifier() == null)
		// classId = ((InstEnumeration) element)
		// .getTransSupportMetaElement().getAutoIdentifier();
		// else
		// classId = ((InstEnumeration) element)
		// .getTransSupportMetaElement().getUserIdentifier();
		// // .getSupportMetaElementUserIdentifier();
		// else if (((InstOverTwoRelation) element)
		// .getTransSupportMetaElement().getUserIdentifier() == null)
		// classId = ((InstOverTwoRelation) element)
		// .getTransSupportMetaElement().getAutoIdentifier();
		// else
		// classId = ((InstOverTwoRelation) element)
		// .getTransSupportMetaElement().getUserIdentifier();
		// // .getSupportMetaElementUserIdentifier();
		//
		// }

		while (variabilityInstVertex.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextInstGroupDependencyId(InstOverTwoRelation grouDep) {

		int id = 1;
		String classId = grouDep.getSupportMetaElementUserIdentifier();

		while (instGroupDependencies.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextConstraintInstEdgeId(InstPairwiseRelation element) {

		int id = 1;
		String classId = null;
		classId = MetaPairwiseRelation.getClassId();

		while (constraintInstEdges.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	public Map<String, InstElement> getVariabilityVertex() {
		return variabilityInstVertex;
	}

	public List<InstElement> getVariabilityVertex(String stereotype) {
		Iterator<InstElement> iter = variabilityInstVertex.values().iterator();
		List<InstElement> out = new ArrayList<InstElement>();
		while (iter.hasNext()) {
			InstElement element = iter.next();
			if (element.getTransSupportMetaElement() != null
					&& element.getTransSupportMetaElement().getAutoIdentifier()
							.equals(stereotype))
				out.add((InstElement) element);
		}
		try {
			Collections.sort(out);
		} catch (Exception e) {

		}
		return out;
	}

	public List<InstElement> getVariabilityVertexMC(String metatype) {
		Iterator<InstElement> iter = variabilityInstVertex.values().iterator();
		List<InstElement> out = new ArrayList<InstElement>();
		while (iter.hasNext()) {
			InstElement element = iter.next();
			if (element.getTransSupportMetaElement() != null) {
				MetaElement e = element.getTransSupportMetaElement();
				InstElement el = this.getSyntaxRefas()
						.getVertex(e.getAutoIdentifier())
						.getEditableMetaElement().getTransInstSemanticElement();
				while (el != null) {
					if (el.getUserIdentifier().equals(metatype)) {
						out.add((InstElement) element);
						break;
					}
					InstElement el2 = null;
					for (InstElement ee : el.getTargetRelations()) {

						// System.out.println(ee.getTransSupportMetaElement()
						// .getUserIdentifier());
						if (ee.getTransSupportMetaElement().getUserIdentifier()
								.equals("ExtendsRelation"))
							el2 = ee.getTargetRelations().get(0);
					}
					el = el2;
				}
			}
		}
		try {
			Collections.sort(out);
		} catch (Exception e) {

		}
		return out;
	}

	public Collection<InstElement> getVariabilityVertexCollection() {
		return variabilityInstVertex.values();
	}

	public void setVariabilityVertex(Map<String, InstElement> elements) {
		this.variabilityInstVertex = elements;
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Map<String, Asset> getAssets() {
		// TODO support HLCL
		return null;
	}

	@Override
	public Constraint getConstraint(String consId) {
		// TODO support HLCL
		return null;
	}

	public InstElement getVertex(String vertexId) {
		InstElement out = variabilityInstVertex.get(vertexId);
		if (out == null)
			out = instGroupDependencies.get(vertexId);
		return out;
	}

	public Set<InstElement> getVertices() {
		Set<InstElement> out = new HashSet<InstElement>();
		out.addAll(variabilityInstVertex.values());
		out.addAll(instGroupDependencies.values());
		return out;
	}

	public Set<InstElement> getElements() {
		Set<InstElement> out = new HashSet<InstElement>();
		out.addAll(variabilityInstVertex.values());
		out.addAll(instGroupDependencies.values());
		out.addAll(constraintInstEdges.values());
		return out;
	}

	public InstPairwiseRelation getConstraintInstEdge(String edgeId) {
		return constraintInstEdges.get(edgeId);
	}

	public com.variamos.perspsupport.instancesupport.InstElement getElement(
			String vertexId) {
		InstElement out = getVertex(vertexId);
		if (out == null)
			out = constraintInstEdges.get(vertexId);
		return out;
	}

	public void removeElement(InstElement obj) {
		obj.clearRelations();
		if (obj instanceof InstConcept) {
			InstConcept concept = (InstConcept) obj;

			variabilityInstVertex.remove(concept.getIdentifier());
		}
		if (obj instanceof InstEnumeration) {
			InstEnumeration concept = (InstEnumeration) obj;
			variabilityInstVertex.remove(concept.getIdentifier());
		}
		if (obj instanceof InstOverTwoRelation) {
			InstOverTwoRelation overtwo = (InstOverTwoRelation) obj;
			instGroupDependencies.remove(overtwo.getIdentifier());
		} else if (obj instanceof InstPairwiseRelation)
			constraintInstEdges.remove(((InstPairwiseRelation) obj)
					.getIdentifier());
	}

	/**
	 * @return all InstVertex with constraints (excludes MetaEnumation)
	 */
	public List<InstElement> getConstraintVertexCollection() {
		ArrayList<InstElement> out = new ArrayList<InstElement>();
		out.addAll(variabilityInstVertex.values());
		out.addAll(instGroupDependencies.values());
		return out;
	}

	public Map<String, InstElement> getConstraintVertex() {
		Map<String, InstElement> out = new HashMap<String, InstElement>();
		out.putAll(variabilityInstVertex);
		out.putAll(instGroupDependencies);
		return out;
	}

	public List<String> modelElements(int modelViewInd, int modelViewSubInd) {
		List<String> elements = new ArrayList<String>();
		List<InstElement> views = this.getVariabilityVertex("View");
		// modelViewInd = -1; // TODO for initial testing, delete
		if (modelViewInd == -1) {
			for (InstElement instVertex : variabilityInstVertex.values()) {
				if (instVertex.getInstAttributeValue("Visible") != null
						&& (boolean) instVertex
								.getInstAttributeValue("Visible"))
					elements.add(instVertex.getEditableMetaElement()
							.getAutoIdentifier());
			}
		} else if (modelViewInd < views.size() && modelViewSubInd == -1) {
			for (InstElement instElement : views.get(modelViewInd)
					.getTargetRelations()) {
				// if (instElement instanceof InstVertex ||instElement
				// instanceof InstView) {
				if (instElement.getTargetRelations().get(0)
						.getTargetRelations().size() > 0) {
					InstElement instVertex = instElement.getTargetRelations()
							.get(0).getTargetRelations().get(0)
							.getTargetRelations().get(0);
					if (instVertex.getEditableMetaElement() != null
							&& instVertex.getInstAttributeValue("Visible") != null
							&& (boolean) instVertex
									.getInstAttributeValue("Visible"))
						elements.add(instVertex.getEditableMetaElement()
								.getAutoIdentifier());
					// }
				}
			}
		}
		if (modelViewInd != -1
				&& modelViewInd < views.size()
				&& modelViewSubInd != -1
				&& modelViewSubInd < views.get(modelViewInd)
						.getTargetRelations().size()) {
			for (InstElement instElement : views.get(modelViewInd)
					.getTargetRelations()) {
				InstElement instVertex = instElement.getTargetRelations()
						.get(0).getTargetRelations().get(0)
						.getTargetRelations().get(0);
				if (instVertex.getEditableMetaElement() != null
						&& instVertex.getInstAttributeValue("Visible") != null
						&& (boolean) instVertex
								.getInstAttributeValue("Visible"))
					elements.add(instVertex.getEditableMetaElement()
							.getAutoIdentifier());
			}
		}
		return elements;
	}

	/**
	 * Creates the elements (objects) to support the Semantic Model. Concepts
	 * are displayed in the palette of the semantic perspective (PWAssociations
	 * can be used)
	 */
	private void createCoreSemantic() {
		MetaConcept metaBasicConcept = new MetaConcept();

		metaBasicConcept.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaBasicConcept.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		metaBasicConcept.addModelingAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));

		metaBasicConcept.addModelingAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaConcept Type", ConceptType.class.getCanonicalName(),
				"MetaConcept", 0, -1, "", "", -1, "", ""));
		// metaBasicConcept.addModelingAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				true, 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Style", new SyntaxAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "refasclaim", 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Width", new SyntaxAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", 100, 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Height", new SyntaxAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", 40, 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Image", new SyntaxAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		metaBasicConcept.addModelingAttribute("TopConcept",
				new SyntaxAttribute("TopConcept", "Boolean",
						AttributeType.SYNTAX, false, "Is Top Concept", true, 0,
						-1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		metaBasicConcept.addModelingAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Resizable", new SyntaxAttribute(
				"Resizable", "Boolean", AttributeType.SYNTAX, false,
				"Is Resizable", true, 0, -1, "", "", -1, "", ""));

		SemanticConcept semConcept = new SemanticConcept();

		// FOR SD and CLAIMS - Review to change
		semConcept.putSemanticAttribute("relationTypesAttributes",
				new SyntaxAttribute("relationTypesAttributes", "Set",
						AttributeType.SYNTAX, false, "relationTypes",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		semConcept.putSemanticAttribute("relationTypesSemExpressions",
				new SyntaxAttribute("relationTypesSemExpressions", "Set",
						AttributeType.SYNTAX, false, "semanticExpressions",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));
		// semConcept.putSemanticAttribute("identifier", new SyntaxAttribute(
		// "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// "", "", -1, "", ""));
		// semConcept.addPropEditableAttribute("01#" + "identifier");
		// semConcept.addPropVisibleAttribute("01#" + "identifier");

		// semConcept.addPanelVisibleAttribute("01#" + "identifier");
		// semConcept.addPanelSpacersAttribute("#" + "identifier" + "#\n\n");

		InstConcept instSemConcept = new InstConcept("Concept", null,
				semConcept);

		MetaConcept metaConcept = new MetaConcept(
				'C',
				"Concept",
				true,
				"Concept",
				"refasenumeration",
				"Semantic Concept: Define a concept for the semantic operations",
				100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, instSemConcept, true);

		metaConcept.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaConcept.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		SemanticConcept semPairwiseRelation = new SemanticConcept(semConcept,
				"CSPairWiseRelation");

		semPairwiseRelation.putSemanticAttribute("enumerationType",
				new SemanticAttribute("enumerationType", "Class",
						AttributeType.OPERATION, false, "enumeration",
						InstEnumeration.class.getCanonicalName(),
						"TypeEnumeration", "String", "", 0, -1, "", "", -1, "",
						""));

		semPairwiseRelation.addPropEditableAttribute("03#" + "enumerationType");
		semPairwiseRelation.addPropVisibleAttribute("03#" + "enumerationType");

		semPairwiseRelation.putSemanticAttribute("relationTypesAttributes",
				new SyntaxAttribute("relationTypesAttributes", "Set",
						AttributeType.SYNTAX, false, "relationTypes",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		semPairwiseRelation.putSemanticAttribute("relationTypesSemExpressions",
				new SyntaxAttribute("relationTypesSemExpressions", "Set",
						AttributeType.SYNTAX, false, "semanticExpressions",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		InstConcept instSemPairwiseRelation = new InstConcept(
				"CSPairWiseRelation", null, semPairwiseRelation);

		MetaConcept metaPairWiseRelation = new MetaConcept(
				'P',
				"CSPairWiseRelation",
				true,
				"CSPairWiseRelation",
				"refasenumeration",
				"Semantic PairWise Relation: Defines a direct relation for the semantic operations",
				150, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, instSemPairwiseRelation, true);

		metaPairWiseRelation.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairWiseRelation.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

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
		MetaEnumeration enumeration = new MetaEnumeration(
				"TypeEnumeration",
				true,
				"TypeEnumeration",
				"refasenumeration",
				"Semantic Enumeration Type: Define an enumeration of types used in paiwise and overtwo relations",
				100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, true);

		enumeration.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		enumeration.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		enumeration.addModelingAttribute("value", "Set", false, "value",
				InstAttribute.class.getCanonicalName(),
				new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "", "");
		enumeration.addPropEditableAttribute("01#" + "value");
		enumeration.addPropVisibleAttribute("01#" + "value");

		SemanticConcept semOverTwoRelation = new SemanticConcept(semConcept,
				"CSOverTwoRelation");

		semOverTwoRelation.putSemanticAttribute("enumerationType",
				new SemanticAttribute("enumerationType", "Class",
						AttributeType.OPERATION, false, "enumeration",
						InstEnumeration.class.getCanonicalName(),
						"TypeEnumeration", "String", "", 0, -1, "", "", -1, "",
						""));

		semOverTwoRelation.addPropEditableAttribute("03#" + "enumerationType");
		semOverTwoRelation.addPropVisibleAttribute("03#" + "enumerationType");

		semOverTwoRelation.putSemanticAttribute("relationTypesAttributes",
				new SyntaxAttribute("relationTypesAttributes", "Set",
						AttributeType.SYNTAX, false, "relationTypes",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		semOverTwoRelation.putSemanticAttribute("relationTypesSemExpressions",
				new SyntaxAttribute("relationTypesSemExpressions", "Set",
						AttributeType.SYNTAX, false, "semanticExpressions",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		InstConcept instSemOverTwoRelation = new InstConcept(
				"CSOverTwoRelation", null, semOverTwoRelation);

		MetaConcept overTwoRelation = new MetaConcept('O', "CSOverTwoRelation",
				true, "CSOverTwoRelation", "refasminiclass",
				"Over Two Relation", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instSemOverTwoRelation, true);

		overTwoRelation.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		overTwoRelation.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		List<IntSemanticRelationType> semPairwExtRelList = new ArrayList<IntSemanticRelationType>();
		semPairwExtRelList.add(new SemanticRelationType("extends", "extends",
				"extends", false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> semPairwAsoRelList = new ArrayList<IntSemanticRelationType>();
		semPairwAsoRelList.add(new SemanticRelationType("association",
				"association", "association", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation semPairwExtRel = new SemanticPairwiseRelation(
				"ExtRel", false, semPairwExtRelList);

		InstConcept instSemPairwExtRel = new InstConcept("ExtendsRelation",
				metaBasicConcept, semPairwExtRel);

		SemanticPairwiseRelation semPairwAsoRel = new SemanticPairwiseRelation(
				"AsoRel", false, semPairwAsoRelList);

		InstConcept instSemPairwAsoRel = new InstConcept("AssociationRelation",
				metaBasicConcept, semPairwAsoRel);

		InstConcept instConcept = new InstConcept("Concept", metaBasicConcept,
				metaConcept);

		variabilityInstVertex.put("Concept", instConcept);

		InstConcept instPairWiseRelation = new InstConcept(
				"CSPairWiseRelation", metaBasicConcept, metaPairWiseRelation);
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("CSPairWiseRelation", instPairWiseRelation);

		variabilityInstVertex.put("TypeEnumeration", new InstConcept(
				"TypeEnumeration", metaBasicConcept, enumeration));
		InstConcept instOverTwo = new InstConcept("CSOverTwoRelation",
				metaBasicConcept, overTwoRelation);
		variabilityInstVertex.put("CSOverTwoRelation", instOverTwo);

		MetaPairwiseRelation metaPairwiseRelAso = new MetaPairwiseRelation(
				"AssociationRelation", false, "Association Relation",
				"defaultAsso", "Association Relation: ", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instSemPairwAsoRel);

		metaPairwiseRelAso.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairwiseRelAso.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		constraintInstEdges.put("DirectRelation", new InstPairwiseRelation(
				metaPairwiseRelAso));

		MetaPairwiseRelation metaPairwiseRelExtends = new MetaPairwiseRelation(
				"ExtendsRelation",
				false,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instSemPairwExtRel);

		metaPairwiseRelExtends.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairwiseRelExtends.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstPairwiseRelation rel = new InstPairwiseRelation(semPairwExtRel);
		rel.setEditableMetaElement(metaPairwiseRelExtends);
		rel.setIdentifier("ExtendsCCRel");
		rel.setTargetRelation(instConcept, true);
		rel.setSourceRelation(instConcept, true);
		constraintInstEdges.put("ExtendsCCRel", rel);

		rel = new InstPairwiseRelation(semPairwExtRel);
		rel.setEditableMetaElement(metaPairwiseRelExtends);
		rel.setIdentifier("ExtendsOCRel");
		rel.setTargetRelation(instConcept, true);
		rel.setSourceRelation(instOverTwo, true);
		constraintInstEdges.put("ExtendsOCRel", rel);

		rel = new InstPairwiseRelation(semPairwExtRel);
		rel.setEditableMetaElement(metaPairwiseRelExtends);
		rel.setIdentifier("ExtendsOORel");
		rel.setTargetRelation(instOverTwo, true);
		rel.setSourceRelation(instOverTwo, true);
		constraintInstEdges.put("ExtendsOORel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoCPWRel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instConcept, true);
		constraintInstEdges.put("AssoCPWRel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoPWCRel");
		rel.setTargetRelation(instConcept, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoPWCRel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoOCPWRel");
		rel.setTargetRelation(instConcept, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoOCPWRel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoPWOCRel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instOverTwo, true);
		constraintInstEdges.put("AssoPWOCRel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoCOPWRel");
		rel.setTargetRelation(instOverTwo, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoCOPWRel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoPWCORel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instConcept, true);
		constraintInstEdges.put("AssoPWCORel", rel);

		SemanticConcept semOperationGroup = new SemanticConcept(semConcept,
				"CSOperGroup");

		semOperationGroup.putSemanticAttribute("menuType",
				new SemanticAttribute("menuType", "String",
						AttributeType.OPERATION, false, "Oper Group Type",
						null, null, null, 0, 5, "", "", 5, "", ""));
		semOperationGroup.addPropEditableAttribute("05#" + "menuType");
		semOperationGroup.addPropVisibleAttribute("05#" + "menuType");

		semOperationGroup.putSemanticAttribute("visible",
				new SemanticAttribute("visible", "Boolean",
						AttributeType.OPERATION, false, "Visible", true, 0, 8,
						"", "", 8, "", ""));
		semOperationGroup.addPropEditableAttribute("08#" + "visible");
		semOperationGroup.addPropVisibleAttribute("08#" + "visible");

		semOperationGroup.putSemanticAttribute("name", new SemanticAttribute(
				"name", "String", AttributeType.OPERATION, false, "Name", null,
				0, 6, "", "", 6, "", ""));
		semOperationGroup.addPropEditableAttribute("06#" + "name");
		semOperationGroup.addPropVisibleAttribute("06#" + "name");
		semOperationGroup.addPanelVisibleAttribute("06#" + "name");
		semOperationGroup.addPanelSpacersAttribute("#" + "name" + "#");

		semOperationGroup.putSemanticAttribute("shortcut",
				new SemanticAttribute("shortcut", "String",
						AttributeType.OPERATION, false, "Shortcut", null, 0, 7,
						"", "", 7, "", ""));
		semOperationGroup.addPropEditableAttribute("07#" + "shortcut");
		semOperationGroup.addPropVisibleAttribute("07#" + "shortcut");

		semOperationGroup.putSemanticAttribute("Index", new SemanticAttribute(
				"Index", "Integer", AttributeType.OPERATION, false, "Position",
				1, 0, 9, "", "", 9, "", ""));
		semOperationGroup.addPropEditableAttribute("09#" + "Index");
		semOperationGroup.addPropVisibleAttribute("09#" + "Index");

		InstConcept instSemOperationGroup = new InstConcept("CSOperGroup",
				null, semOperationGroup);

		MetaConcept operationGroup = new MetaConcept('M', "CSOperGroup", true,
				"CSOperGroup", "refasminiclass", "Operation Group", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instSemOperationGroup, true);

		operationGroup.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		operationGroup.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instOperMenu = new InstConcept("CSOperGroup",
				metaBasicConcept, operationGroup);
		variabilityInstVertex.put("CSOperGroup", instOperMenu);

		SemanticConcept semModel = new SemanticConcept(semConcept, "CSModel");

		semModel.putSemanticAttribute("name", new SemanticAttribute("name",
				"String", AttributeType.OPERATION, false, "Name", null, 0, 6,
				"", "", 6, "", ""));
		semModel.addPropEditableAttribute("06#" + "name");
		semModel.addPropVisibleAttribute("06#" + "name");
		semModel.addPanelVisibleAttribute("06#" + "name");
		semModel.addPanelSpacersAttribute("#" + "name" + "#");

		InstConcept instSemModel = new InstConcept("CSModel", null, semModel);

		MetaConcept metaModel = new MetaConcept('C', "CSModel", true,
				"CSModel", "refasminiclass", "Semantic Model", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instSemModel, true);

		metaModel.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaModel.addPanelSpacersAttribute("#" + MetaConcept.VAR_USERIDENTIFIER
				+ "#\n\n");

		InstConcept instMetaModel = new InstConcept("CSModel",
				metaBasicConcept, metaModel);
		variabilityInstVertex.put("CSModel", instMetaModel);

		SemanticConcept semOperationAction = new SemanticConcept(semConcept,
				"CSOpAction");

		semOperationAction.putSemanticAttribute("name", new SemanticAttribute(
				"name", "String", AttributeType.OPERATION, false, "Name", null,
				0, 6, "", "", 6, "", ""));
		semOperationAction.addPropEditableAttribute("06#" + "name");
		semOperationAction.addPropVisibleAttribute("06#" + "name");
		semOperationAction.addPanelVisibleAttribute("06#" + "name");
		semOperationAction.addPanelSpacersAttribute("#" + "name" + "#");

		semOperationAction.putSemanticAttribute("shortcut",
				new SemanticAttribute("shortcut", "String",
						AttributeType.OPERATION, false, "Shortcut", null, 0, 6,
						"", "", 6, "", ""));
		semOperationAction.addPropEditableAttribute("07#" + "shortcut");
		semOperationAction.addPropVisibleAttribute("07#" + "shortcut");

		semOperationAction.putSemanticAttribute("Index", new SemanticAttribute(
				"Index", "Integer", AttributeType.OPERATION, false, "Position",
				1, 0, 6, "", "", 6, "", ""));
		semOperationAction.addPropEditableAttribute("08#" + "Index");
		semOperationAction.addPropVisibleAttribute("08#" + "Index");

		InstConcept instSemOperationAction = new InstConcept("CSOpAction",
				null, semOperationAction);

		MetaConcept operationAction = new MetaConcept('A', "CSOpAction", true,
				"CSOpAction", "refasminiclass", "Operation Action", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instSemOperationAction, true);

		operationAction.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		operationAction.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instOperAction = new InstConcept("CSOpAction",
				metaBasicConcept, operationAction);
		variabilityInstVertex.put("CSOpAction", instOperAction);

		SemanticConcept semOperationSubAction = new SemanticConcept(semConcept,
				"CSOpSubAction");

		semOperationSubAction.putSemanticAttribute("name",
				new SemanticAttribute("name", "String",
						AttributeType.OPERATION, false, "Name", null, 0, 6, "",
						"", 6, "", ""));
		semOperationSubAction.addPropEditableAttribute("06#" + "name");
		semOperationSubAction.addPropVisibleAttribute("06#" + "name");
		semOperationSubAction.addPanelVisibleAttribute("06#" + "name");
		semOperationSubAction.addPanelSpacersAttribute("#" + "name" + "#");

		semOperationSubAction.putSemanticAttribute("shortcut",
				new SemanticAttribute("shortcut", "String",
						AttributeType.OPERATION, false, "Shortcut", null, 0, 6,
						"", "", 6, "", ""));
		semOperationSubAction.addPropEditableAttribute("07#" + "shortcut");
		semOperationSubAction.addPropVisibleAttribute("07#" + "shortcut");

		semOperationSubAction.putSemanticAttribute("Index",
				new SemanticAttribute("Index", "Integer",
						AttributeType.OPERATION, false, "Position", 1, 0, 6,
						"", "", 6, "", ""));
		semOperationSubAction.addPropEditableAttribute("08#" + "Index");
		semOperationSubAction.addPropVisibleAttribute("08#" + "Index");

		InstConcept instSemOperationSubAction = new InstConcept(
				"CSOpSubAction", null, semOperationSubAction);

		MetaConcept operationSubAction = new MetaConcept('S', "CSOpSubAction",
				true, "CSOpSubAction", "refasminiclass", "Operation Action",
				100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, instSemOperationSubAction, true);

		operationSubAction.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		operationSubAction.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instOperSubAction = new InstConcept("CSOpSubAction",
				metaBasicConcept, operationSubAction);
		variabilityInstVertex.put("CSOpSubAction", instOperSubAction);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoMenu-Act");
		rel.setTargetRelation(instOperAction, true);
		rel.setSourceRelation(instOperMenu, true);
		constraintInstEdges.put("AssoMenu-Act", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoAct-SubAct");
		rel.setTargetRelation(instOperSubAction, true);
		rel.setSourceRelation(instOperAction, true);
		constraintInstEdges.put("AssoAct-SubAct", rel);
	}

	/**
	 * Creates the elements (objects) to support the syntax Meta Model. Concepts
	 * are displayed in the palette of the syntax perspective. (PWAssociations
	 * can be used)
	 */
	private void createCoreSyntax() {
		MetaConcept metaBasicConcept = new MetaConcept();

		metaBasicConcept.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaBasicConcept.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		metaBasicConcept.addModelingAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));

		metaBasicConcept.addModelingAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaConcept Type", ConceptType.class.getCanonicalName(),
				"MetaConcept", 0, -1, "", "", -1, "", ""));
		// metaBasicConcept.addModelingAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				true, 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Style", new SyntaxAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "refasclaim", 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Width", new SyntaxAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", 100, 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Height", new SyntaxAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", 40, 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Image", new SyntaxAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		metaBasicConcept.addModelingAttribute("TopConcept",
				new SyntaxAttribute("TopConcept", "Boolean",
						AttributeType.SYNTAX, false, "Is Top Concept", true, 0,
						-1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		metaBasicConcept.addModelingAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Resizable", new SyntaxAttribute(
				"Resizable", "Boolean", AttributeType.SYNTAX, false,
				"Is Resizable", true, 0, -1, "", "", -1, "", ""));

		SemanticConcept semView = new SemanticConcept();

		semView.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaConcept Type", ConceptType.class.getCanonicalName(),
				"MetaView", 0, -1, "", "", -1, "", ""));
		semView.putSemanticAttribute("Index", new SyntaxAttribute("Index",
				"Integer", AttributeType.SYNTAX, false, "View Index", 3, 0, -1,
				"", "", -1, "", ""));
		semView.putSemanticAttribute("Identifier", new SyntaxAttribute(
				"Identifier", "String", AttributeType.SYNTAX, false,
				"View Identifier", "", 0, -1, "", "", -1, "", ""));
		semView.putSemanticAttribute("Visible", new SyntaxAttribute("Visible",
				"Boolean", AttributeType.SYNTAX, false, "Visible", true, 0, -1,
				"", "", -1, "", ""));
		semView.putSemanticAttribute("Parent", new SyntaxAttribute("Parent",
				"String", AttributeType.SYNTAX, false, "Parent View", "", 0,
				-1, "", "", -1, "", ""));
		semView.putSemanticAttribute("Name", new SyntaxAttribute("Name",
				"String", AttributeType.SYNTAX, false, "Concept Name", "", 0,
				-1, "", "", -1, "", ""));
		semView.putSemanticAttribute("Style", new SyntaxAttribute("Style",
				"String", AttributeType.SYNTAX, false, "Drawing Style",
				"refasclaim", 0, -1, "", "", -1, "", ""));
		semView.putSemanticAttribute("Description", new SyntaxAttribute(
				"Description", "String", AttributeType.SYNTAX, false,
				"Description", "", 0, -1, "", "", -1, "", ""));
		semView.putSemanticAttribute("Width", new SyntaxAttribute("Width",
				"Integer", AttributeType.SYNTAX, false, "Initial Width", 100,
				0, -1, "", "", -1, "", ""));
		semView.putSemanticAttribute("Height", new SyntaxAttribute("Height",
				"Integer", AttributeType.SYNTAX, false, "Initial Height", 40,
				0, -1, "", "", -1, "", ""));
		semView.putSemanticAttribute("Image", new SyntaxAttribute("Image",
				"String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		semView.putSemanticAttribute("BorderStroke", new SyntaxAttribute(
				"BorderStroke", "Integer", AttributeType.SYNTAX, false,
				"Border Stroke", 1, 0, -1, "", "", -1, "", ""));
		semView.putSemanticAttribute("PaletteNames", new SyntaxAttribute(
				"PaletteNames", "String", AttributeType.SYNTAX, false,
				"Palette Name", "", 0, -1, "", "", -1, "", ""));

		semView.addPropEditableAttribute("03#" + "PaletteNames");
		semView.addPropVisibleAttribute("03#" + "PaletteNames");
		semView.addPanelVisibleAttribute("05#" + "PaletteNames" + "#"
				+ "PaletteNames" + "#!=#" + "" + "#" + "");
		semView.addPanelSpacersAttribute("{Palettes:#" + "PaletteNames"
				+ "#}\n\n");
		semView.addPropVisibleAttribute("00#" + "MetaType");
		// semView.addPropEditableAttribute("01#" + "Identifier");
		// semView.addPropVisibleAttribute("01#" + "Identifier");
		semView.addPropEditableAttribute("02#" + "Index");
		semView.addPropVisibleAttribute("02#" + "Index");
		semView.addPropEditableAttribute("03#" + "Visible");
		semView.addPropVisibleAttribute("03#" + "Visible");
		// semView.addPropEditableAttribute("04#" + "Parent");
		// semView.addPropVisibleAttribute("04#" + "Parent");
		semView.addPropEditableAttribute("05#" + "Name");
		semView.addPropVisibleAttribute("05#" + "Name");
		semView.addPropEditableAttribute("06#" + "Style");
		semView.addPropVisibleAttribute("06#" + "Style");
		semView.addPropEditableAttribute("07#" + "Description");
		semView.addPropVisibleAttribute("07#" + "Description");
		semView.addPropEditableAttribute("08#" + "Width");
		semView.addPropVisibleAttribute("08#" + "Width");
		semView.addPropEditableAttribute("09#" + "Height");
		semView.addPropVisibleAttribute("09#" + "Height");
		semView.addPropEditableAttribute("10#" + "Image");
		semView.addPropVisibleAttribute("10#" + "Image");
		// semView.addDisPropEditableAttribute("11#" + "BorderStroke");
		semView.addPropVisibleAttribute("11#" + "BorderStroke");

		// semView.addPanelVisibleAttribute("01#" + "Name");
		// semView.addPanelSpacersAttribute("#" + "Name" + "#");

		InstConcept instSemView = new InstConcept("View", null, semView);

		MetaConcept view = new MetaConcept('V', "View", true, "View",
				"refasview", "View/SubView Concept", 100, 30,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.WHITE.toString(), 3, instSemView, true);

		view.addPanelVisibleAttribute("04#" + MetaConcept.VAR_USERIDENTIFIER);
		view.addPanelSpacersAttribute("#" + MetaConcept.VAR_USERIDENTIFIER
				+ "#\n\n");

		InstConcept instView = new InstConcept("View", metaBasicConcept, view);
		variabilityInstVertex.put("View", instView);

		SemanticConcept semVertex = new SemanticConcept();

		semVertex.putSemanticAttribute("Name", new SyntaxAttribute("Name",
				"String", AttributeType.SYNTAX, false, "Concept Name", "", 0,
				-1, "", "", -1, "", ""));
		semVertex.putSemanticAttribute("Description", new SyntaxAttribute(
				"Description", "String", AttributeType.SYNTAX, false,
				"Description", "", 0, -1, "", "", -1, "", ""));

		semVertex.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaConcept Type", ConceptType.class.getCanonicalName(),
				"MetaConcept", 0, -1, "", "", -1, "", ""));
		// semVertex.putSemanticAttribute("Identifier", new SyntaxAttribute(
		// "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// "", "", -1, "", ""));
		semVertex.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				true, 0, -1, "", "", -1, "", ""));
		semVertex.putSemanticAttribute("Name", new SyntaxAttribute("Name",
				"String", AttributeType.SYNTAX, false, "Concept Name", "", 0,
				-1, "", "", -1, "", ""));
		semVertex.putSemanticAttribute("Style", new SyntaxAttribute("Style",
				"String", AttributeType.SYNTAX, false, "Drawing Style",
				"refasclaim", 0, -1, "", "", -1, "", ""));
		semVertex.putSemanticAttribute("Width", new SyntaxAttribute("Width",
				"Integer", AttributeType.SYNTAX, false, "Initial Width", 100,
				0, -1, "", "", -1, "", ""));
		semVertex.putSemanticAttribute("Height", new SyntaxAttribute("Height",
				"Integer", AttributeType.SYNTAX, false, "Initial Height", 40,
				0, -1, "", "", -1, "", ""));
		semVertex.putSemanticAttribute("Image", new SyntaxAttribute("Image",
				"String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		semVertex.putSemanticAttribute("TopConcept", new SyntaxAttribute(
				"TopConcept", "Boolean", AttributeType.SYNTAX, false,
				"Is Top Concept", true, 0, -1, "", "", -1, "", ""));
		semVertex.putSemanticAttribute("BackgroundColor", new SyntaxAttribute(
				"BackgroundColor", "String", AttributeType.SYNTAX, false,
				"Background Color", "java.awt.Color[r=0,g=0,b=255]", 0, -1, "",
				"", -1, "", ""));
		semVertex.putSemanticAttribute("BorderStroke", new SyntaxAttribute(
				"BorderStroke", "Integer", AttributeType.SYNTAX, false,
				"Border Stroke", 1, 0, -1, "", "", -1, "", ""));
		semVertex.putSemanticAttribute("Resizable", new SyntaxAttribute(
				"Resizable", "Boolean", AttributeType.SYNTAX, false,
				"Is Resizable", true, 0, -1, "", "", -1, "", ""));
		semVertex.putSemanticAttribute("value", new SyntaxAttribute("value",
				"Set", AttributeType.SYNTAX, false, "values", "", 0, -1, "",
				"", -1, "", ""));

		semVertex.addPropVisibleAttribute("00#" + "MetaType");
		// semVertex.addPropEditableAttribute("01#" + "Identifier");
		// semVertex.addPropVisibleAttribute("01#" + "Identifier");
		semVertex.addPropEditableAttribute("02#" + "Visible");
		semVertex.addPropVisibleAttribute("02#" + "Visible");
		semVertex.addPropEditableAttribute("03#" + "Name");
		semVertex.addPropVisibleAttribute("03#" + "Name");
		semVertex.addPropEditableAttribute("04#" + "Style");
		semVertex.addPropVisibleAttribute("04#" + "Style");
		semVertex.addPropEditableAttribute("05#" + "Description");
		semVertex.addPropVisibleAttribute("05#" + "Description");
		semVertex.addPropEditableAttribute("06#" + "Width");
		semVertex.addPropVisibleAttribute("06#" + "Width");
		semVertex.addPropEditableAttribute("07#" + "Height");
		semVertex.addPropVisibleAttribute("07#" + "Height");
		semVertex.addPropEditableAttribute("08#" + "Image");
		semVertex.addPropVisibleAttribute("08#" + "Image");
		semVertex.addPropEditableAttribute("09#" + "TopConcept");
		semVertex.addPropVisibleAttribute("09#" + "TopConcept");
		semVertex.addPropEditableAttribute("10#" + "BackgroundColor");
		semVertex.addPropVisibleAttribute("10#" + "BackgroundColor");
		semVertex.addPropEditableAttribute("11#" + "BorderStroke");
		semVertex.addPropVisibleAttribute("11#" + "BorderStroke");
		semVertex.addPropEditableAttribute("12#" + "Resizable");
		semVertex.addPropVisibleAttribute("12#" + "Resizable");
		// semVertex.addPropEditableAttribute("14#" + "value");
		// semVertex.addPropVisibleAttribute("14#" + "value");

		SemanticConcept semPWAsso = new SemanticConcept();

		semPWAsso.putSemanticAttribute("Name", new SyntaxAttribute("Name",
				"String", AttributeType.SYNTAX, false, "Association Name", "",
				0, -1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("Description", new SyntaxAttribute(
				"Description", "String", AttributeType.SYNTAX, false,
				"Description", "", 0, -1, "", "", -1, "", ""));

		semPWAsso.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaPWAsso Type", ConceptType.class.getCanonicalName(),
				"MetaConcept", 0, -1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("SemanticType", new SemanticAttribute(
				"SemanticType", "Class", AttributeType.SYNTAX, false,
				"Semantic Type", SemanticConcept.class.getCanonicalName(), "P",
				null, "", 0, -1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("Identifier", new SyntaxAttribute(
				"Identifier", "String", AttributeType.SYNTAX, false,
				"Association Identifier", "", 0, -1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				true, 0, -1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("Name", new SyntaxAttribute("Name",
				"String", AttributeType.SYNTAX, false, "Concept Name", "", 0,
				-1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("Style", new SyntaxAttribute("Style",
				"String", AttributeType.SYNTAX, false, "Drawing Style", "", 0,
				-1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("Width", new SyntaxAttribute("Width",
				"Integer", AttributeType.SYNTAX, false, "Initial Width", 50, 0,
				-1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("Height", new SyntaxAttribute("Height",
				"Integer", AttributeType.SYNTAX, false, "Initial Height", 50,
				0, -1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("Image", new SyntaxAttribute("Image",
				"String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		semPWAsso.putSemanticAttribute("BackgroundColor", new SyntaxAttribute(
				"BackgroundColor", "String", AttributeType.SYNTAX, false,
				"Background Color", "java.awt.Color[r=0,g=0,b=255]", 0, -1, "",
				"", -1, "", ""));
		semPWAsso.putSemanticAttribute("BorderStroke", new SyntaxAttribute(
				"BorderStroke", "Integer", AttributeType.SYNTAX, false,
				"Border Stroke", 1, 0, -1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("value", new SyntaxAttribute("value",
				"Set", AttributeType.SYNTAX, false, "values", "", 0, -1, "",
				"", -1, "", ""));

		semPWAsso.addPropVisibleAttribute("00#" + "MetaType");
		// semPWAsso.addPropEditableAttribute("01#" + "Identifier");
		// semPWAsso.addPropVisibleAttribute("01#" + "Identifier");
		semPWAsso.addPropEditableAttribute("02#" + "Visible");
		semPWAsso.addPropVisibleAttribute("02#" + "Visible");
		semPWAsso.addPropEditableAttribute("03#" + "Name");
		semPWAsso.addPropVisibleAttribute("03#" + "Name");
		semPWAsso.addPropEditableAttribute("04#" + "Style");
		semPWAsso.addPropVisibleAttribute("04#" + "Style");
		semPWAsso.addPropEditableAttribute("05#" + "Description");
		semPWAsso.addPropVisibleAttribute("05#" + "Description");
		semPWAsso.addPropEditableAttribute("06#" + "Width");
		semPWAsso.addPropVisibleAttribute("06#" + "Width");
		semPWAsso.addPropEditableAttribute("07#" + "Height");
		semPWAsso.addPropVisibleAttribute("07#" + "Height");
		semPWAsso.addPropEditableAttribute("08#" + "Image");
		semPWAsso.addPropVisibleAttribute("08#" + "Image");
		semPWAsso.addPropEditableAttribute("10#" + "BackgroundColor");
		semPWAsso.addPropVisibleAttribute("10#" + "BackgroundColor");
		semPWAsso.addPropEditableAttribute("11#" + "BorderStroke");
		semPWAsso.addPropVisibleAttribute("11#" + "BorderStroke");
		semPWAsso.addPropEditableAttribute("14#" + "value");
		semPWAsso.addPropVisibleAttribute("14#" + "value");

		SemanticConcept semConcept = new SemanticConcept(semVertex, "Concept");

		semConcept.putSemanticAttribute("SemanticType", new SemanticAttribute(
				"SemanticType", "Class", AttributeType.OPERATION, false,
				"Semantic Type", SemanticConcept.class.getCanonicalName(), "C",
				null, "", 0, -1, "", "", -1, "", ""));

		semConcept.addPropEditableAttribute("00#" + "SemanticType");
		semConcept.addPropVisibleAttribute("00#" + "SemanticType");

		InstConcept instSemVertex = new InstConcept("Concept", null, semConcept);

		MetaConcept concept = new MetaConcept('C', "Concept", true, "Concept",
				"refasminiclass", "Meta Concept", 150, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemVertex, true);

		concept.addPanelVisibleAttribute("04#" + MetaConcept.VAR_USERIDENTIFIER);
		concept.addPanelSpacersAttribute("#" + MetaConcept.VAR_USERIDENTIFIER
				+ "#\n\n");

		concept.addPanelVisibleAttribute("00#" + "SemanticType");
		concept.addPanelSpacersAttribute("<<MetaConcept>>\n{SemType:\"#"
				+ "SemanticType" + "#\"}\n");
		// concept.addPanelVisibleAttribute("01#" + "Name");
		// concept.addPanelSpacersAttribute("#" + "Name" + "#\n\n");

		InstConcept instConcept = new InstConcept("Concept", metaBasicConcept,
				concept);
		variabilityInstVertex.put("Concept", instConcept);

		SemanticConcept semElementNoSyntax = new SemanticConcept();

		semElementNoSyntax.putSemanticAttribute("MetaType",
				new SyntaxAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "MetaConcept Type",
						ConceptType.class.getCanonicalName(),
						"MetaEnumeration", 0, -1, "", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		semElementNoSyntax.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				true, 0, -1, "", "", -1, "", ""));
		semElementNoSyntax.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", 0, -1, "", "", -1, "", ""));
		semElementNoSyntax.putSemanticAttribute("value", new SyntaxAttribute(
				"value", "Set", AttributeType.SYNTAX, false, "values", "", 0,
				-1, "", "", -1, "", ""));
		semElementNoSyntax.putSemanticAttribute("dummy", new SyntaxAttribute(
				"dummy", "String", AttributeType.SYNTAX, false, "dummy", "", 0,
				-1, "", "", -1, "", ""));

		semElementNoSyntax.addPropVisibleAttribute("00#" + "MetaType");
		// semElementNoSyntax.addPropEditableAttribute("01#" + "Identifier");
		// semElementNoSyntax.addPropVisibleAttribute("01#" + "Identifier");
		semElementNoSyntax.addPropEditableAttribute("02#" + "Visible");
		semElementNoSyntax.addPropVisibleAttribute("02#" + "Visible");
		semElementNoSyntax.addPropEditableAttribute("03#" + "Name");
		semElementNoSyntax.addPropVisibleAttribute("03#" + "Name");
		semElementNoSyntax.addPropEditableAttribute("06#" + "value");
		semElementNoSyntax.addPropVisibleAttribute("06#" + "value");

		// semElement.addPanelVisibleAttribute("01#" + "Name");
		// semElement.addPanelSpacersAttribute("#" + "Name" + "#");
		semElementNoSyntax.addPanelSpacersAttribute("#" + "value" + "#\n\n");

		InstConcept instSemEnum = new InstConcept("Enumeration", null,
				semElementNoSyntax);

		MetaConcept enumeration = new MetaConcept('E', "Enumeration", true,
				"Enumeration", "refasenumeration", "MetaEnumeration", 100, 150,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemEnum, true);

		variabilityInstVertex.put("Enumeration", new InstConcept("Enumeration",
				metaBasicConcept, enumeration));

		SemanticConcept semOverTwoRelation = new SemanticConcept(semVertex,
				"OverTwoRelation");

		semOverTwoRelation.putSemanticAttribute("MetaType",
				new SyntaxAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "MetaConcept Type",
						ConceptType.class.getCanonicalName(),
						"MetaOverTwoRelation", 0, -1, "", "", -1, "", ""));
		semOverTwoRelation.putSemanticAttribute("SemanticType",
				new SemanticAttribute("SemanticType", "Class",
						AttributeType.OPERATION, false, "Semantic Type",
						SemanticConcept.class.getCanonicalName(), "O", null,
						"", 0, -1, "", "", -1, "", ""));

		semOverTwoRelation.addPropVisibleAttribute("00#" + "MetaType");
		semOverTwoRelation.addPropVisibleAttribute("00#" + "SemanticType");
		semOverTwoRelation.addPropEditableAttribute("00#" + "SemanticType");
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);

		semOverTwoRelation.addPanelVisibleAttribute("00#" + "SemanticType");
		semOverTwoRelation
				.addPanelSpacersAttribute("<<MetaOverTwoAsso>>\n{SemType:\"#"
						+ "SemanticType" + "#\"}\n");
		// semOverTwoRelation.addPanelVisibleAttribute("01#" + "Name");
		// semOverTwoRelation.addPanelSpacersAttribute("#" + "Name" + "#");

		InstConcept instSemOverTwoRelation = new InstConcept("OverTwoRelation",
				null, semOverTwoRelation);

		MetaConcept overTwoRelation = new MetaConcept('O', "OverTwoRelation",
				true, "OverTwoRelation", "refasminiclass",
				"MetaOverTwoRelation", 180, 70,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemOverTwoRelation, true);

		overTwoRelation.addModelingAttribute("Type", new SyntaxAttribute(
				"Type", "String", AttributeType.SYNTAX, false, "Relation Type",
				"", 0, -1, "", "", -1, "", ""));

		// overTwoRelation.addPropVisibleAttribute("03#" + "Type");
		// overTwoRelation.addPropEditableAttribute("03#" + "Type");
		// overTwoRelation.addPanelVisibleAttribute("03#" + "Type" + "#" +
		// "Type"
		// + "#!=#" + "" + "#" + "");
		// overTwoRelation.addPanelSpacersAttribute("\n{#" + "Type" + "#}");

		InstConcept instOverTwoRelation = new InstConcept("OverTwoRelation",
				metaBasicConcept, overTwoRelation);
		variabilityInstVertex.put("OverTwoRelation", instOverTwoRelation);

		MetaPairwiseRelation metaPairwiseRelNormal = new MetaPairwiseRelation(
				"NormalRelation", false, "Normal Relation", "defaultAsso",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		constraintInstEdges.put("NormalRelation", new InstPairwiseRelation(
				"NormalRelation", metaPairwiseRelNormal));

		SemanticConcept semExtendRelation = new SemanticConcept(
				semElementNoSyntax, "ExtendRelation");

		InstConcept instSemExtendRelation = new InstConcept("ExtendRelation",
				null, semExtendRelation);

		MetaConcept extendRelation = new MetaConcept('X', "ExtendRelation",
				true, "ExtendRelation", "refasminiclass", "Extend relation",
				150, 70, "/com/variamos/gui/perspeditor/images/concept.png",
				true, Color.BLUE.toString(), 3, instSemExtendRelation, true);
		extendRelation.addPanelVisibleAttribute("01#Name");
		extendRelation
				.addPanelSpacersAttribute("<<MetaExtendsAsso>>\n#Name#\n\n");

		InstConcept instExtendRelation = new InstConcept("ExtendRelation",
				metaBasicConcept, extendRelation);

		variabilityInstVertex.put("ExtendRelation", instExtendRelation);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ce-e-c", instEdge);
		instEdge.setIdentifier("ce-e-c");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instConcept, true);
		instEdge.setSourceRelation(instExtendRelation, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ce-c-e", instEdge);
		instEdge.setIdentifier("ce-c-e");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instExtendRelation, true);
		instEdge.setSourceRelation(instConcept, true);

		MetaPairwiseRelation metaPairwiseRelExtends = new MetaPairwiseRelation(
				"ExtendsRelation", false, "Extends Relation", "refasextends",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		constraintInstEdges.put("ExtendsRelation", new InstPairwiseRelation(
				"ExtendsRelation", metaPairwiseRelExtends));

		SemanticConcept semViewConceptAsso = new SemanticConcept(
				semElementNoSyntax, "ViewConceptAsso");

		InstConcept instSemViewConceptAsso = new InstConcept("ExtendRelation",
				null, semViewConceptAsso);

		MetaConcept viewConceptAsso = new MetaConcept('I', "ViewConceptAsso",
				true, "ViewConceptAsso", "refasminiclass",
				"View-Concept Association", 150, 70,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemViewConceptAsso, true);
		viewConceptAsso.addPanelVisibleAttribute("01#dummy");
		viewConceptAsso
				.addPanelSpacersAttribute("<<MetaViewConceptAsso>>#dummy#\n");
		viewConceptAsso.addModelingAttribute("Palette", new SyntaxAttribute(
				"Palette", "String", AttributeType.SYNTAX, false,
				"Palette Name", "", 0, -1, "", "", -1, "", ""));

		viewConceptAsso.addPropEditableAttribute("02#" + "Palette");
		viewConceptAsso.addPropVisibleAttribute("02#" + "Palette");
		viewConceptAsso.addPanelVisibleAttribute("02#" + "Palette" + "#"
				+ "Palette" + "#!=#" + "" + "#" + "");
		viewConceptAsso.addPanelSpacersAttribute("{Palette:#" + "Palette"
				+ "#}\n");

		InstConcept instViewConceptAsso = new InstConcept("ViewConceptAsso",
				metaBasicConcept, viewConceptAsso);
		variabilityInstVertex.put("ViewConceptAsso", instViewConceptAsso);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vc-v-vc", instEdge);
		instEdge.setIdentifier("vc-v-vc");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewConceptAsso, true);
		instEdge.setSourceRelation(instView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vc-vc-c", instEdge);
		instEdge.setIdentifier("vc-vc-c");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instConcept, true);
		instEdge.setSourceRelation(instViewConceptAsso, true);

		// TODO remove if Claims and SDs are Concepts
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vc-vc-otr", instEdge);
		instEdge.setIdentifier("vc-vc-otr");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instOverTwoRelation, true);
		instEdge.setSourceRelation(instViewConceptAsso, true);

		MetaPairwiseRelation metaPairwiseRelFromView = new MetaPairwiseRelation(
				"ViewRelation", false, "View Relation", "refasviewrel",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		InstPairwiseRelation instViewRelation = new InstPairwiseRelation(
				"ViewRelation", metaPairwiseRelFromView);

		constraintInstEdges.put("ViewRelation", instViewRelation);

		SemanticConcept semPairwiseRelation = new SemanticConcept(semPWAsso,
				"PairwiseRelation");

		semPairwiseRelation.putSemanticAttribute("SemanticType",
				new SemanticAttribute("SemanticType", "Class",
						AttributeType.OPERATION, false, "Semantic Type",
						SemanticConcept.class.getCanonicalName(), "P", null,
						"", 0, -1, "", "", -1, "", ""));

		semPairwiseRelation.addPropVisibleAttribute("00#" + "SemanticType");
		semPairwiseRelation.addPropEditableAttribute("00#" + "SemanticType");
		semPairwiseRelation.addPanelVisibleAttribute("00#" + "SemanticType");
		semPairwiseRelation
				.addPanelSpacersAttribute("<<MetaPairwiseAsso>>\n{SemType:\"#"
						+ "SemanticType" + "#\",\n");
		// semPairwiseRelation.addPanelVisibleAttribute("10#" + "Name");
		// semPairwiseRelation.addPanelSpacersAttribute("#" + "Name" + "#\n\n");

		InstConcept instSemPairwiseRelationn = new InstConcept(
				"PairwiseRelation", null, semPairwiseRelation);

		MetaConcept pairwiseRelation = new MetaConcept('P', "PairwiseRelation",
				true, "PairwiseRelation", "refasminiclass",
				"MetaPairwiseRelation", 150, 200,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemPairwiseRelationn, true);

		pairwiseRelation.addModelingAttribute("Type", new SyntaxAttribute(
				"Type", "String", AttributeType.SYNTAX, false, "Relation Type",
				"", 0, -1, "", "", -1, "", ""));

		// pairwiseRelation.addPropEditableAttribute("03#" + "Type");
		// pairwiseRelation.addPropVisibleAttribute("03#" + "Type");
		// pairwiseRelation.addPanelVisibleAttribute("03#" + "Type" + "#" +
		// "Type"
		// + "#!=#" + "" + "#" + "");
		// pairwiseRelation.addPanelSpacersAttribute("\n{#" + "Type" + "#}");

		pairwiseRelation.addModelingAttribute("SourceCardinality",
				new SyntaxAttribute("SourceCardinality", "String",
						AttributeType.SYNTAX, false, "Source Cardinality",
						"String", "[]", 0, -1, "", "", -1, "", ""));

		pairwiseRelation.addPropEditableAttribute("04#" + "SourceCardinality");
		pairwiseRelation.addPropVisibleAttribute("04#" + "SourceCardinality");
		pairwiseRelation.addPanelVisibleAttribute("01#" + "SourceCardinality"
				+ "#" + "Type" + "#!=#" + "" + "#" + "");
		pairwiseRelation.addPanelSpacersAttribute("SourCard:#"
				+ "SourceCardinality" + "#,");

		pairwiseRelation.addModelingAttribute("TargetCardinality",
				new SyntaxAttribute("TargetCardinality", "String",
						AttributeType.SYNTAX, false, "Target Cardinality",
						"String", "[]", 0, 5, "", "", 5, "TargCard:#-#}\n",
						"Type#!=##"));

		pairwiseRelation.addPropEditableAttribute("05#" + "TargetCardinality");
		pairwiseRelation.addPropVisibleAttribute("05#" + "TargetCardinality");
		pairwiseRelation.addPanelVisibleAttribute("02#" + "TargetCardinality"
				+ "#" + "Type" + "#!=#" + "" + "#" + "");
		pairwiseRelation.addPanelSpacersAttribute("TargCard:#"
				+ "TargetCardinality" + "#}\n");

		InstConcept instPairwiseRelation = new InstConcept("PairwiseRelation",
				metaBasicConcept, pairwiseRelation);
		variabilityInstVertex.put("PairwiseRelation", instPairwiseRelation);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cpw-pw-c", instEdge);
		instEdge.setIdentifier("cpw-pw-c");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instConcept, true);
		instEdge.setSourceRelation(instPairwiseRelation, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cpw-c-pw", instEdge);
		instEdge.setIdentifier("cpw-c-pw");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instPairwiseRelation, true);
		instEdge.setSourceRelation(instConcept, true);

		// TODO remove if Claims and SDs are Concepts
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cpw-pw-otr", instEdge);
		instEdge.setIdentifier("cpw-pw-otr");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instOverTwoRelation, true);
		instEdge.setSourceRelation(instPairwiseRelation, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cpw-otr-pw", instEdge);
		instEdge.setIdentifier("cpw-otr-pw");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instPairwiseRelation, true);
		instEdge.setSourceRelation(instOverTwoRelation, true);
	}

	/**
	 * Creates the default elements (objects) of the Semantic Model (Semantic
	 * concepts). Elements are loaded in the Semantic perspective (advanced
	 * perspectiv) and defines the semantic for elements on the syntax
	 * perspective (associated to concepts).
	 */
	@SuppressWarnings("unchecked")
	public void createDefaultSemantic() {
		RefasOperSem.createDefaultSemantic(this);
	}

	/**
	 * Creates the default objects of the Meta Model (Syntax concepts). They can
	 * be edited on the Syntax perspective and are the support elements for the
	 * modeling perspective displayed in the views (palettes)
	 */
	private void createDefaultSyntax() {
		RefasSyntax.createDefaultSyntax(this);
	}

	public PerspectiveType getPerspectiveType() {
		return perspectiveType;
	}

	public void setInstGroupDependencies(
			Map<String, InstOverTwoRelation> instGroupDependencies) {
		this.instGroupDependencies = instGroupDependencies;
	}

	public Map<String, MetaElement> getValidPairwiseRelations(
			InstElement instElement, InstElement instElement2) {
		InstElement instSyntaxElement = this.getVertex(instElement
				.getSupportMetaElementIden());
		InstElement instSyntaxElement2 = this.getVertex(instElement2
				.getSupportMetaElementIden());
		if (instSyntaxElement2 == null) {
			System.out.println("getValidPairwiseRelations error - "
					+ instElement2.getIdentifier());
		}
		return getValidPairwiseRelations(instSyntaxElement, instSyntaxElement2,
				true);
	}

	private Map<String, MetaElement> getValidPairwiseRelations(
			InstElement instElement, InstElement instElement2, boolean first) {
		MetaElement metaElement = instElement.getEditableMetaElement();
		Map<String, MetaElement> out = new HashMap<String, MetaElement>();
		if (instElement2 == null) {
			return out;
		}
		MetaElement metaElement2 = instElement2.getEditableMetaElement();
		for (InstPairwiseRelation pwr : constraintInstEdges.values()) {
			if (pwr.getSourceRelations().size() > 0
					&& pwr.getTargetRelations().size() > 0) {
				MetaElement sourceMetaElement = pwr.getSourceRelations().get(0)
						.getEditableMetaElement();
				MetaElement targetMetaElement = pwr.getTargetRelations().get(0)
						.getEditableMetaElement();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getAutoIdentifier().equals(
						metaElement.getAutoIdentifier())
						&& targetMetaElement.getAutoIdentifier().equals(
								metaElement2.getAutoIdentifier())
				// && pwr.getEditableMetaElement().getVisible()
				)
					out.put(pwr.getIdentifier(), pwr.getEditableMetaElement());
				// TODO validate the other end when the OTR type has
				// exclusive connections

				/*
				 * if (instElement instanceof MetaOverTwoRelation) if
				 * (targetMetaElement.getIdentifier().equals(
				 * instElement2.getIdentifier())) out.put(pwr.getIdentifier(),
				 * pwr.getEditableMetaElement()); if (instElement2 instanceof
				 * MetaOverTwoRelation) if
				 * (sourceMetaElement.getIdentifier().equals(
				 * instElement.getIdentifier())) out.put(pwr.getIdentifier(),
				 * pwr.getEditableMetaElement());
				 */
			}
		}
		for (InstElement pwr : this.variabilityInstVertex.values()) {
			if (pwr.getSourceRelations().size() > 0
					&& pwr.getTargetRelations().size() > 0) {
				MetaElement sourceMetaElement = pwr.getSourceRelations().get(0)
						.getSourceRelations().get(0).getEditableMetaElement();
				MetaElement targetMetaElement = pwr.getTargetRelations().get(0)
						.getTargetRelations().get(0).getEditableMetaElement();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getAutoIdentifier().equals(
						metaElement.getAutoIdentifier())
						&& targetMetaElement.getAutoIdentifier().equals(
								metaElement2.getAutoIdentifier())
						&& pwr.getEditableMetaElement().getVisible())
					out.put(pwr.getIdentifier(), pwr.getEditableMetaElement());
				// TODO validate the other end when the OTR type has
				// exclusive connections
			}
		}

		if (metaElement instanceof MetaConcept && first) {
			List<InstElement> rel = instElement.getTargetRelations();
			for (InstElement element : rel)
				if (element.getTargetRelations().get(0)
						.getSupportMetaElementIden() != null
						&& element.getTargetRelations().get(0)
								.getSupportMetaElementIden()
								.equals("ExtendRelation")) {
					out.putAll(getValidPairwiseRelations(element
							.getTargetRelations().get(0).getTargetRelations()
							.get(0).getTargetRelations().get(0), instElement2,
							true));
				}
		}
		if (metaElement2 instanceof MetaConcept) {
			List<InstElement> rel = instElement2.getTargetRelations();
			for (InstElement element : rel) {
				if (element.getTargetRelations().get(0)
						.getSupportMetaElementIden() != null
						&& element.getTargetRelations().get(0)
								.getSupportMetaElementIden()
								.equals("ExtendRelation")) {
					out.putAll(getValidPairwiseRelations(instElement, element
							.getTargetRelations().get(0).getTargetRelations()
							.get(0).getTargetRelations().get(0), false));

				}
			}
		}
		return out;
	}

	public MetaElement getValidMetaPairwiseRelation(InstElement instElement,
			InstElement instElement2, String metaPairwiseIden) {
		InstElement instSyntaxElement = this.getVertex(instElement
				.getSupportMetaElementIden());
		InstElement instSyntaxElement2 = this.getVertex(instElement2
				.getSupportMetaElementIden());
		return getValidMetaPairwiseRelation(instSyntaxElement,
				instSyntaxElement2, metaPairwiseIden, true);
	}

	private MetaElement getValidMetaPairwiseRelation(InstElement instElement,
			InstElement instElement2, String metaPairwiseIden, boolean first) {
		MetaElement metaElement = instElement.getEditableMetaElement();
		if (instElement2 == null) {
			System.out.println("getValidMetaPairwiseRelation error");
		}
		MetaElement metaElement2 = instElement2.getEditableMetaElement();
		for (InstPairwiseRelation pwr : constraintInstEdges.values()) {
			if (pwr.getSourceRelations().size() > 0
					&& pwr.getTargetRelations().size() > 0
					&& pwr.getEditableMetaElement() != null) {
				MetaElement sourceMetaElement = pwr.getSourceRelations().get(0)
						.getEditableMetaElement();
				MetaElement targetMetaElement = pwr.getTargetRelations().get(0)
						.getEditableMetaElement();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getAutoIdentifier().equals(
						metaElement.getAutoIdentifier())
						&& targetMetaElement.getAutoIdentifier().equals(
								metaElement2.getAutoIdentifier())
						&& pwr.getEditableMetaElement().getAutoIdentifier()
								.equals(metaPairwiseIden))
					return (MetaPairwiseRelation) pwr.getEditableMetaElement();
			}
		}
		MetaElement out2 = null;
		for (InstElement pwr : this.variabilityInstVertex.values()) {
			if (pwr.getSourceRelations().size() > 0
					&& pwr.getTargetRelations().size() > 0) {
				MetaElement sourceMetaElement = pwr.getSourceRelations().get(0)
						.getSourceRelations().get(0).getEditableMetaElement();
				MetaElement targetMetaElement = pwr.getTargetRelations().get(0)
						.getTargetRelations().get(0).getEditableMetaElement();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getAutoIdentifier().equals(
						metaElement.getAutoIdentifier())
						&& targetMetaElement.getAutoIdentifier().equals(
								metaElement2.getAutoIdentifier())
						&& pwr.getEditableMetaElement().getAutoIdentifier()
								.equals(metaPairwiseIden))
					out2 = pwr.getEditableMetaElement();
				// TODO validate the other end when the OTR type has
				// exclusive connections
				if (out2 != null)
					return out2;
			}
		}

		if (metaElement2 instanceof MetaConcept) {
			List<InstElement> rel = instElement2.getTargetRelations();
			for (InstElement element : rel) {
				if (element.getTargetRelations().get(0)
						.getSupportMetaElementIden() != null
						&& element.getTargetRelations().get(0)
								.getSupportMetaElementIden()
								.equals("ExtendRelation")) {
					MetaElement out = (getValidMetaPairwiseRelation(
							instElement, element.getTargetRelations().get(0)
									.getTargetRelations().get(0)
									.getTargetRelations().get(0),
							metaPairwiseIden, false));
					if (out != null)
						return out;
				}
			}
		}
		if (metaElement instanceof MetaConcept && first) {
			List<InstElement> rel = instElement.getTargetRelations();
			for (InstElement element : rel)
				if (element.getTargetRelations().get(0)
						.getSupportMetaElementIden() != null
						&& element.getTargetRelations().get(0)
								.getSupportMetaElementIden()
								.equals("ExtendRelation")) {
					return (getValidMetaPairwiseRelation(element
							.getTargetRelations().get(0).getTargetRelations()
							.get(0).getTargetRelations().get(0), instElement2,
							metaPairwiseIden, true));
				}
		}
		return null;
	}

	public List<InstElement> getParentSyntaxConcept(InstElement instElement) {
		if (instElement == null)
			return null;
		InstElement instSyntaxElement = this.getSyntaxRefas().getVertex(
				instElement.getSupportMetaElementIden());
		if (instSyntaxElement != null)
			return getRecursiveParentSyntaxConcept(instSyntaxElement);
		else
			return null;
	}

	private List<InstElement> getRecursiveParentSyntaxConcept(
			InstElement instElement) {
		List<InstElement> out = new ArrayList<InstElement>();
		List<InstElement> rel = instElement.getTargetRelations();
		for (InstElement element : rel) {
			if (element.getTargetRelations().get(0).getSupportMetaElementIden() != null
					&& element.getTargetRelations().get(0)
							.getSupportMetaElementIden()
							.equals("ExtendRelation")) {
				InstElement parent = element.getTargetRelations().get(0)
						.getTargetRelations().get(0).getTargetRelations()
						.get(0);
				// parent.createInstAttributes(parents);
				out.add(parent);
				out.addAll(getRecursiveParentSyntaxConcept(element
						.getTargetRelations().get(0).getTargetRelations()
						.get(0).getTargetRelations().get(0)));
			}
		}
		return out;
	}

	public void updateValidationLists(InstElement elm, InstElement instSource,
			InstElement instTarget, List<InstElement> parents) {
		List<InstAttribute> visible = elm.getVisibleVariables(parents);
		InstPairwiseRelation instPairwise = (InstPairwiseRelation) elm;
		if (instSource == null && instPairwise.getSourceRelations().size() > 0)
			instSource = instPairwise.getSourceRelations().get(0);
		if (instTarget == null && instPairwise.getTargetRelations().size() > 0)
			instTarget = instPairwise.getTargetRelations().get(0);
		for (InstAttribute v : visible) {
			Map<String, MetaElement> mapElements = null;
			if (elm instanceof InstPairwiseRelation && instSource != null
					&& instSource.getTransSupportMetaElement() != null) {
				mapElements = getSyntaxRefas().getValidPairwiseRelations(
						instSource, instTarget);
			}
			v.updateValidationList((InstElement) elm, mapElements);
		}
	}

	public boolean elementsValidation(String element, int modelViewInd,
			int modelViewSubInd) {

		List<InstElement> views = this.getVariabilityVertex("View");
		// FIXME Find views by stereotype, not by instViews object
		if (modelViewInd < views.size() && modelViewSubInd == -1) {
			for (InstElement instElement : views.get(modelViewInd)
					.getTargetRelations()) {
				if (instElement.getTargetRelations().get(0)
						.getSupportMetaElementIden() != null
						&& instElement.getTargetRelations().get(0)
								.getTargetRelations().get(0)
								.getTargetRelations().get(0).getIdentifier()
								.equals(element))
					return true;
			}
		}
		/*
		 * if (modelViewInd < instViews.size() && modelViewSubInd != -1 &&
		 * modelViewSubInd < instViews.get(modelViewInd)
		 * .getChildViews().size()) { // FIXME use relations instead
		 * List<InstElement> instVertex = instViews.get(modelViewInd)
		 * .getChildViews().get(modelViewSubInd).getTargetRelations(); for
		 * (InstElement instElement : instVertex) { if
		 * (instElement.getTargetRelations().get(0)
		 * .getTargetRelations().get(0).getTargetRelations()
		 * .get(0).getIdentifier().equals(element)) return true; }
		 * 
		 * }
		 */
		return false;
	}

	public void clear() {
		this.instGroupDependencies.clear();
		this.variabilityInstVertex.clear();
		this.constraintInstEdges.clear();
	}
}