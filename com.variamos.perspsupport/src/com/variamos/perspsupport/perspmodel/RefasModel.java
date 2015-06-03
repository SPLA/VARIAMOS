package com.variamos.perspsupport.perspmodel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.cfm.common.AbstractModel;
import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.VariabilityElement;
import com.variamos.hlcl.RangeDomain;
import com.variamos.perspsupport.expressionsupport.InstanceExpression;
import com.variamos.perspsupport.expressionsupport.SemanticExpressionType;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstEnumeration;
import com.variamos.perspsupport.instancesupport.InstOverTwoRelation;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.instancesupport.InstVertex;
import com.variamos.perspsupport.instancesupport.InstView;
import com.variamos.perspsupport.semanticinterface.IntSemanticRelationType;
import com.variamos.perspsupport.semanticsupport.AbstractSemanticVertex;
import com.variamos.perspsupport.semanticsupport.SemanticConcept;
import com.variamos.perspsupport.semanticsupport.SemanticContextGroup;
import com.variamos.perspsupport.semanticsupport.SemanticOverTwoRelation;
import com.variamos.perspsupport.semanticsupport.SemanticPairwiseRelation;
import com.variamos.perspsupport.semanticsupport.SemanticRelationType;
import com.variamos.perspsupport.semanticsupport.SemanticVariable;
import com.variamos.perspsupport.semanticsupport.SoftSemanticConcept;
import com.variamos.perspsupport.semanticsupport.SemanticReasoningConcept;
import com.variamos.perspsupport.syntaxsupport.MetaConcept;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaEnumeration;
import com.variamos.perspsupport.syntaxsupport.MetaOverTwoRelation;
import com.variamos.perspsupport.syntaxsupport.MetaPairwiseRelation;
import com.variamos.perspsupport.syntaxsupport.MetaView;
import com.variamos.perspsupport.syntaxsupport.SyntaxAttribute;
import com.variamos.perspsupport.syntaxsupport.SemanticAttribute;
import com.variamos.perspsupport.syntaxsupport.GlobalConfigAttribute;
import com.variamos.perspsupport.syntaxsupport.ExecCurrentStateAttribute;
import com.variamos.perspsupport.types.ConceptType;
import com.variamos.perspsupport.types.PerspectiveType;

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
	// TODO Move variables and enums to otherElements
	/**
	 * 
	 */
	private Map<String, InstVertex> otherInstVertex;

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

	private List<InstView> instViews;

	public RefasModel(PerspectiveType perspectiveType,
			Map<String, SemanticExpressionType> metaExpressionTypes) {
		this(perspectiveType, metaExpressionTypes, null, null);
	}

	public RefasModel(Map<String, SemanticExpressionType> metaExpressionTypes,
			RefasModel syntaxRefas) {
		this(PerspectiveType.semantic, metaExpressionTypes, syntaxRefas, null);
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
		otherInstVertex = new HashMap<String, InstVertex>();
		constraintInstEdges = new HashMap<String, InstPairwiseRelation>();
		instViews = new ArrayList<InstView>();
		name = "";

		switch (perspectiveType) {
		case basicSemantic:
			createBasicSemantic();
			break;
		case basicSyntax:
			createBasicSyntax();
			break;
		case modeling:
			break;
		case semantic:
			createSemantic();
			break;
		case simulation:
			break;
		case syntax:
			createSyntax();
			break;
		default:
			break;
		}
	}

	public RefasModel getSyntaxRefas() {
		return syntaxRefas;
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

	public Collection<Constraint> getConstraints() {
		return null;
	}

	public void setConstraints(Map<String, Constraint> constraints) {

	}

	public Collection<VariabilityElement> getVariabilityElements() {
		return null;
	}

	public void putVariabilityInstVertex(InstVertex element) {
		variabilityInstVertex.put(element.getIdentifier(), element);
	}

	public void putOtherInstVertex(InstVertex element) {
		otherInstVertex.put(element.getIdentifier(), element);
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

	public String addNewOtherInstElement(InstVertex element) {
		String id = getNextOtherInstVertexId(element);
		InstVertex varElement = (InstVertex) element;
		varElement.setIdentifier(id);
		varElement.setInstAttribute("name", id);
		otherInstVertex.put(id, element);
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

	public String addNewOtherInstEdge(InstPairwiseRelation directRelation) {
		String id = getNextOtherInstEdgeId(directRelation);
		directRelation.setIdentifier(id);
		return id;
	}

	private String getNextVariabilityInstVertextId(InstVertex element) {
		int id = 1;
		String classId = null;
		if (element instanceof InstConcept)
			classId = ((InstConcept) element).getSupportMetaElementIdentifier();
		else {
			if (element instanceof InstEnumeration)
				classId = ((InstEnumeration) element)
						.getSupportMetaElementIdentifier();
			else
				classId = ((InstOverTwoRelation) element)
						.getSupportMetaElementIdentifier();
		}
		while (variabilityInstVertex.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextOtherInstVertexId(InstVertex element) {
		int id = 1;
		String classId = null;
		if (element instanceof InstConcept)
			classId = ((InstConcept) element).getSupportMetaElementIdentifier();
		else {
			if (element instanceof InstEnumeration)
				classId = ((InstEnumeration) element)
						.getSupportMetaElementIdentifier();
			else
				classId = ((InstOverTwoRelation) element)
						.getSupportMetaElementIdentifier();
		}
		while (otherInstVertex.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextInstGroupDependencyId(InstOverTwoRelation grouDep) {

		int id = 1;
		String classId = grouDep.getSupportMetaElementIdentifier();

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

	private String getNextOtherInstEdgeId(InstPairwiseRelation element) {

		int id = 1;
		String classId = null;
		classId = MetaPairwiseRelation.getClassId();

		while (constraintInstEdges.containsKey(classId + id)) {
			id++;
		}
		return classId + "O" + id;
	}

	public Map<String, InstElement> getVariabilityVertex() {
		return variabilityInstVertex;
	}

	public Collection<InstElement> getVariabilityVertexCollection() {
		return variabilityInstVertex.values();
	}

	public void setVariabilityVertex(Map<String, InstElement> elements) {
		this.variabilityInstVertex = elements;
	}

	public Map<String, InstVertex> getOtherVertex() {
		return otherInstVertex;
	}

	public void setOtherVertex(Map<String, InstVertex> elements) {
		this.otherInstVertex = elements;
	}

	public VariabilityElement getVariabilityElement(String string) {
		return null;
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
		out.addAll(otherInstVertex.values());
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
		// modelViewInd = -1; // TODO for initial testing, delete
		if (modelViewInd == -1) {
			for (InstElement instVertex : variabilityInstVertex.values()) {
				if (instVertex.getEditableMetaElement().getVisible())
					elements.add(instVertex.getEditableMetaElement()
							.getIdentifier());
			}
			for (InstVertex instVertex : otherInstVertex.values()) {
				if (instVertex.getEditableMetaElement().getVisible())
					elements.add(instVertex.getEditableMetaElement()
							.getIdentifier());
			}
		} else if (modelViewInd < instViews.size() && modelViewSubInd == -1) {
			for (InstVertex instVertex : instViews.get(modelViewInd)
					.getInstVertices()) {
				if (instVertex.getEditableMetaElement().getVisible())
					elements.add(instVertex.getEditableMetaElement()
							.getIdentifier());
			}
		}
		if (modelViewInd != -1
				&& modelViewInd < instViews.size()
				&& modelViewSubInd != -1
				&& modelViewSubInd < instViews.get(modelViewInd)
						.getChildViews().size()) {
			for (InstVertex instVertex : instViews.get(modelViewInd)
					.getInstVertices()) {
				if (instVertex.getEditableMetaElement().getVisible())
					elements.add(instVertex.getEditableMetaElement()
							.getIdentifier());
			}
		}
		return elements;
	}

	/**
	 * Creates the objects to support the Semantic Model. They are displayed on
	 * the palette of the semantic perspective
	 */
	private void createBasicSemantic() {
		SemanticConcept semConcept = new SemanticConcept();

		semConcept.putSemanticAttribute("identifier", new SyntaxAttribute(
				"Identifier", "String", false, "Concept Identifier", ""));
		semConcept.addPropEditableAttribute("01#" + "identifier");
		semConcept.addPropVisibleAttribute("01#" + "identifier");

		semConcept.addPanelVisibleAttribute("01#" + "identifier");
		semConcept.addPanelSpacersAttribute("#" + "identifier" + "#\n\n");

		InstConcept instSemConcept = new InstConcept("Concept", null,
				semConcept);

		MetaConcept metaConcept = new MetaConcept("Concept", true, "Concept",
				"refasenumeration", "MetaConcept", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instSemConcept, true);

		SemanticConcept semOverTwoRelation = new SemanticConcept(semConcept,
				"OverTwoRelation");

		InstConcept instSemOverTwoRelation = new InstConcept("OverTwoRelation",
				null, semOverTwoRelation);

		MetaConcept enumeration = new MetaConcept("Enumeration", true,
				"Enumeration", "refasenumeration", "MetaEnumeration", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instSemConcept, true);

		MetaConcept overTwoRelation = new MetaConcept("OverTwoRelation", true,
				"OverTwoRelation", "refasminiclass", "OverTwoRelation", 100,
				150, "/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instSemOverTwoRelation, true);

		List<IntSemanticRelationType> semPairwExtRelList = new ArrayList<IntSemanticRelationType>();
		semPairwExtRelList.add(new SemanticRelationType("extends", "extends",
				"extends", false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> semPairwAsoRelList = new ArrayList<IntSemanticRelationType>();
		semPairwAsoRelList.add(new SemanticRelationType("association",
				"association", "association", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation semPairwExtRel = new SemanticPairwiseRelation(
				"SemExtRel", false, semPairwExtRelList);

		InstConcept instSemPairwExtRel = new InstConcept("ExtendsRelation",
				null, semPairwExtRel);

		SemanticPairwiseRelation semPairwAsoRel = new SemanticPairwiseRelation(
				"SemAsoRel", false, semPairwAsoRelList);

		InstConcept instSemPairwAsoRel = new InstConcept("AssociationRelation",
				null, semPairwAsoRel);

		InstConcept instConcept = new InstConcept("Concept", null, metaConcept);
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("Concept", instConcept);

		variabilityInstVertex.put("Enumeration", new InstConcept("Enumeration",
				null, enumeration));
		InstConcept instOverTwo = new InstConcept("OverTwoRelation", null,
				overTwoRelation);
		variabilityInstVertex.put("OverTwoRelation", instOverTwo);

		MetaPairwiseRelation pairwiseRelation = new MetaPairwiseRelation();

		constraintInstEdges.put("PairwiseRelation", new InstPairwiseRelation(
				pairwiseRelation));

		MetaPairwiseRelation metaPairwiseRelExtends = new MetaPairwiseRelation(
				"ExtendsRelation", false, "Extends Relation", "refasextends",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instSemPairwExtRel);

		MetaPairwiseRelation metaPairwiseRelAso = new MetaPairwiseRelation(
				"AssociationRelation", false, "Association Relation",
				"defaultEdge", "View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instSemPairwAsoRel);

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
		rel.setIdentifier("AssoCCRel");
		rel.setTargetRelation(instConcept, true);
		rel.setSourceRelation(instConcept, true);
		constraintInstEdges.put("AssoCCRel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoOCRel");
		rel.setTargetRelation(instConcept, true);
		rel.setSourceRelation(instOverTwo, true);
		constraintInstEdges.put("AssoOCRel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoCORel");
		rel.setTargetRelation(instOverTwo, true);
		rel.setSourceRelation(instConcept, true);
		constraintInstEdges.put("AssoCORel", rel);
	}

	/**
	 * Creates the objects to support the syntac Meta Model. They are displayed
	 * on the palette of the syntax perspective
	 */
	private void createBasicSyntax() {
		SemanticConcept semView = new SemanticConcept();

		semView.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", false, "MetaConcept Type",
				ConceptType.class.getCanonicalName(), "MetaView"));
		semView.putSemanticAttribute("Identifier", new SyntaxAttribute(
				"Identifier", "String", false, "Concept Identifier", ""));
		semView.putSemanticAttribute("Visible", new SyntaxAttribute("Visible",
				"Boolean", false, "Visible", true));
		semView.putSemanticAttribute("Name", new SyntaxAttribute("Name",
				"String", false, "Concept Name", ""));
		semView.putSemanticAttribute("Sytle", new SyntaxAttribute("Sytle",
				"String", false, "Drawing Style", "refasclaim"));
		semView.putSemanticAttribute("Description", new SyntaxAttribute(
				"Description", "String", false, "Description", ""));
		semView.putSemanticAttribute("Width", new SyntaxAttribute("Width",
				"Integer", false, "Initial Width", 100));
		semView.putSemanticAttribute("Height", new SyntaxAttribute("Height",
				"Integer", false, "Initial Height", 40));
		semView.putSemanticAttribute("Image", new SyntaxAttribute("Image",
				"String", false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png"));
		semView.putSemanticAttribute("BorderStroke", new SyntaxAttribute(
				"BorderStroke", "Integer", false, "Border Stroke", 1));

		semView.addPropVisibleAttribute("00#" + "MetaType");
		semView.addPropEditableAttribute("01#" + "Identifier");
		semView.addPropVisibleAttribute("01#" + "Identifier");
		semView.addPropEditableAttribute("02#" + "Visible");
		semView.addPropVisibleAttribute("02#" + "Visible");
		semView.addPropEditableAttribute("03#" + "Name");
		semView.addPropVisibleAttribute("03#" + "Name");
		semView.addPropEditableAttribute("04#" + "Sytle");
		semView.addPropVisibleAttribute("04#" + "Sytle");
		semView.addPropEditableAttribute("05#" + "Description");
		semView.addPropVisibleAttribute("05#" + "Description");
		semView.addPropEditableAttribute("06#" + "Width");
		semView.addPropVisibleAttribute("06#" + "Width");
		semView.addPropEditableAttribute("07#" + "Height");
		semView.addPropVisibleAttribute("07#" + "Height");
		semView.addPropEditableAttribute("08#" + "Image");
		semView.addPropVisibleAttribute("08#" + "Image");
		// semView.addDisPropEditableAttribute("11#" + "BorderStroke");
		semView.addPropVisibleAttribute("11#" + "BorderStroke");

		semView.addPanelVisibleAttribute("01#" + "Name");
		semView.addPanelSpacersAttribute("#" + "Name" + "#");

		InstConcept instSemView = new InstConcept("View", null, semView);

		MetaConcept view = new MetaConcept("View", true, "View", "refasview",
				"View", 100, 30,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.WHITE.toString(), 3, instSemView, true);

		variabilityInstVertex.put("View", new InstConcept("View", null, view));

		SemanticConcept semVertex = new SemanticConcept();

		semVertex.putSemanticAttribute("Name", new SyntaxAttribute("Name",
				"String", false, "Concept Name", ""));
		semVertex.putSemanticAttribute("Description", new SyntaxAttribute(
				"Description", "String", false, "Description", ""));

		semVertex.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", false, "MetaConcept Type",
				ConceptType.class.getCanonicalName(), "MetaConcept"));
		semVertex.putSemanticAttribute("Identifier", new SyntaxAttribute(
				"Identifier", "String", false, "Concept Identifier", ""));
		semVertex.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", false, "Visible", true));
		semVertex.putSemanticAttribute("Name", new SyntaxAttribute("Name",
				"String", false, "Concept Name", ""));
		semVertex.putSemanticAttribute("Sytle", new SyntaxAttribute("Sytle",
				"String", false, "Drawing Style", "refasclaim"));
		semVertex.putSemanticAttribute("Description", new SyntaxAttribute(
				"Description", "String", false, "Description", ""));
		semVertex.putSemanticAttribute("Width", new SyntaxAttribute("Width",
				"Integer", false, "Initial Width", 100));
		semVertex.putSemanticAttribute("Height", new SyntaxAttribute("Height",
				"Integer", false, "Initial Height", 40));
		semVertex.putSemanticAttribute("Image", new SyntaxAttribute("Image",
				"String", false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png"));
		semVertex.putSemanticAttribute("TopConcept", new SyntaxAttribute(
				"TopConcept", "Boolean", false, "Is Top Concept", true));
		semVertex.putSemanticAttribute("BackgroundColor", new SyntaxAttribute(
				"BackgroundColor", "String", false, "Bacground Color",
				"java.awt.Color[r=0,g=0,b=255]"));
		semVertex.putSemanticAttribute("BorderStroke", new SyntaxAttribute(
				"BorderStroke", "Integer", false, "Border Stroke", 1));
		semVertex.putSemanticAttribute("Resizable", new SyntaxAttribute(
				"Resizable", "Boolean", false, "Is Resizable", true));
		semVertex.putSemanticAttribute("value", new SyntaxAttribute("value",
				"Set", false, "values", ""));

		semVertex.addPropVisibleAttribute("00#" + "MetaType");
		semVertex.addPropEditableAttribute("01#" + "Identifier");
		semVertex.addPropVisibleAttribute("01#" + "Identifier");
		semVertex.addPropEditableAttribute("02#" + "Visible");
		semVertex.addPropVisibleAttribute("02#" + "Visible");
		semVertex.addPropEditableAttribute("03#" + "Name");
		semVertex.addPropVisibleAttribute("03#" + "Name");
		semVertex.addPropEditableAttribute("04#" + "Sytle");
		semVertex.addPropVisibleAttribute("04#" + "Sytle");
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
		// semVertex.addDisPropEditableAttribute("10#" + "BackgroundColor");
		semVertex.addPropVisibleAttribute("10#" + "BackgroundColor");
		// semVertex.addDisPropEditableAttribute("11#" + "BorderStroke");
		semVertex.addPropVisibleAttribute("11#" + "BorderStroke");
		// semVertex.addDisPropEditableAttribute("12#" + "Resizable");
		semVertex.addPropVisibleAttribute("12#" + "Resizable");
		semVertex.addPropEditableAttribute("14#" + "value");
		semVertex.addPropVisibleAttribute("14#" + "value");

		semVertex.addPanelVisibleAttribute("01#" + "Name");
		semVertex.addPanelSpacersAttribute("<<MetaConcept>>\n#" + "Name"
				+ "#\n\n");

		InstConcept instSemVertex = new InstConcept("Concept", null, semVertex);

		MetaConcept concept = new MetaConcept("Concept", true, "Concept",
				"refasminiclass", "MetaConcept", 100, 150,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemVertex, true);

		variabilityInstVertex.put("Concept", new InstConcept("Concept", null,
				concept));

		SemanticConcept semElement = new SemanticConcept();

		semElement.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", false, "MetaConcept Type",
				ConceptType.class.getCanonicalName(), "MetaEnumeration"));
		semElement.putSemanticAttribute("Identifier", new SyntaxAttribute(
				"Identifier", "String", false, "Concept Identifier", ""));
		semElement.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", false, "Visible", true));
		semElement.putSemanticAttribute("Name", new SyntaxAttribute("Name",
				"String", false, "Concept Name", ""));
		semElement.putSemanticAttribute("value", new SyntaxAttribute("value",
				"Set", false, "values", ""));
		semElement.putSemanticAttribute("dummy", new SyntaxAttribute("dummy",
				"String", false, "dummy", ""));

		semElement.addPropVisibleAttribute("00#" + "MetaType");
		semElement.addPropEditableAttribute("01#" + "Identifier");
		semElement.addPropVisibleAttribute("01#" + "Identifier");
		semElement.addPropEditableAttribute("02#" + "Visible");
		semElement.addPropVisibleAttribute("02#" + "Visible");
		semElement.addPropEditableAttribute("03#" + "Name");
		semElement.addPropVisibleAttribute("03#" + "Name");
		semElement.addPropEditableAttribute("06#" + "value");
		semElement.addPropVisibleAttribute("06#" + "value");

		// semElement.addPanelVisibleAttribute("01#" + "Name");
		// semElement.addPanelSpacersAttribute("#" + "Name" + "#");
		semElement.addPanelSpacersAttribute("#" + "value" + "#\n\n");

		InstConcept instSemEnum = new InstConcept("Enumeration", null,
				semElement);

		MetaConcept enumeration = new MetaConcept("Enumeration", true,
				"Enumeration", "refasenumeration", "MetaEnumeration", 100, 150,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemEnum, true);

		variabilityInstVertex.put("Enumeration", new InstConcept("Enumeration",
				null, enumeration));

		SemanticConcept semOverTwoRelation = new SemanticConcept(semElement,
				"OverTwoRelation");

		semOverTwoRelation.putSemanticAttribute(
				"MetaType",
				new SyntaxAttribute("MetaType", "Enumeration", false,
						"MetaConcept Type", ConceptType.class
								.getCanonicalName(), "MetaOverTwoRelation"));

		semOverTwoRelation.addPropVisibleAttribute("00#" + "MetaType");
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);

		InstConcept instSemOverTwoRelation = new InstConcept("OverTwoRelation",
				null, semOverTwoRelation);

		MetaConcept overTwoRelation = new MetaConcept("OverTwoRelation", true,
				"OverTwoRelation", "refasminiclass", "MetaOverTwoRelation",
				100, 150, "/com/variamos/gui/perspeditor/images/concept.png",
				true, Color.BLUE.toString(), 3, instSemOverTwoRelation, true);

		overTwoRelation.addModelingAttribute("Type", new SyntaxAttribute(
				"Type", "String", false, "Relation Type", ""));
		overTwoRelation.addPanelVisibleAttribute("01#" + "dummy");
		overTwoRelation.addPanelSpacersAttribute("<<MetaOverTwoAssociation>>#"
				+ "dummy" + "#");

		overTwoRelation.addPropVisibleAttribute("03#" + "Type");
		overTwoRelation.addPropEditableAttribute("03#" + "Type");
		overTwoRelation.addPanelVisibleAttribute("03#" + "Type" + "#" + "Type"
				+ "#!=#" + "" + "#" + "");
		overTwoRelation.addPanelSpacersAttribute("\n{#" + "Type" + "#}");

		variabilityInstVertex.put("OverTwoRelation", new InstConcept(
				"OverTwoRelation", null, overTwoRelation));

		SemanticConcept semExtendRelation = new SemanticConcept(semElement,
				"OverTwoRelation");

		InstConcept instSemExtendRelation = new InstConcept("ExtendRelation",
				null, semExtendRelation);

		MetaConcept extendRelation = new MetaConcept("ExtendRelation", false,
				"Extend Relation", "refasminiclass", "Extend relation", 50, 50,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemExtendRelation, true);
		extendRelation.addPanelVisibleAttribute("01#dummy");
		extendRelation
				.addPanelSpacersAttribute("<<MetaExtendsAssociation>>#dummy#\n\n");

		variabilityInstVertex.put("ExtendRelation", new InstConcept(
				"ExtendRelation", null, extendRelation));

		MetaPairwiseRelation metaPairwiseRelExtends = new MetaPairwiseRelation(
				"ExtendsRelation", false, "Extends Relation", "refasextends",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		constraintInstEdges.put("ExtendsRelation", new InstPairwiseRelation(
				"ExtendsRelation", metaPairwiseRelExtends));

		MetaPairwiseRelation metaPairwiseRelFromView = new MetaPairwiseRelation(
				"ViewRelation", false, "View Relation", "refasviewrel",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		constraintInstEdges.put("ViewRelation", new InstPairwiseRelation(
				"ViewRelation", metaPairwiseRelFromView));

		MetaPairwiseRelation metaPairwiseRelNormal = new MetaPairwiseRelation(
				"NormalRelation", false, "Normal Relation", "defaultEdge",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		constraintInstEdges.put("NormalRelation", new InstPairwiseRelation(
				"NormalRelation", metaPairwiseRelNormal));

		SemanticConcept semPairwiseRelation = new SemanticConcept(semElement,
				"PairwiseRelation");

		InstConcept instSemPairwiseRelationn = new InstConcept(
				"PairwiseRelation", null, semPairwiseRelation);

		MetaConcept pairwiseRelation = new MetaConcept("PairwiseRelation",
				true, "PairwiseRelation", "refasminiclass",
				"MetaPairwiseRelation", 100, 150,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemPairwiseRelationn, true);

		pairwiseRelation.addModelingAttribute("Type", new SyntaxAttribute(
				"Type", "String", false, "Relation Type", ""));
		pairwiseRelation.addPanelVisibleAttribute("01#" + "dummy");
		pairwiseRelation
				.addPanelSpacersAttribute("<<MetaPairwiseAssociation>>#"
						+ "dummy" + "#");

		pairwiseRelation.addPropEditableAttribute("03#" + "Type");
		pairwiseRelation.addPropVisibleAttribute("03#" + "Type");
		pairwiseRelation.addPanelVisibleAttribute("03#" + "Type" + "#" + "Type"
				+ "#!=#" + "" + "#" + "");
		pairwiseRelation.addPanelSpacersAttribute("\n{#" + "Type" + "#}");

		pairwiseRelation.addModelingAttribute("SourceCardinality",
				new SyntaxAttribute("SourceCardinality", "String",
						false, "Source Cardinality", "String", "[]"));

		pairwiseRelation.addPropEditableAttribute("04#" + "SourceCardinality");
		pairwiseRelation.addPropVisibleAttribute("04#" + "SourceCardinality");
		pairwiseRelation.addPanelVisibleAttribute("04#" + "SourceCardinality"
				+ "#" + "Type" + "#!=#" + "" + "#" + "");
		pairwiseRelation.addPanelSpacersAttribute("#" + "SourceCardinality"
				+ "#");

		pairwiseRelation.addModelingAttribute("TargetCardinality",
				new SyntaxAttribute("TargetCardinality", "String",
						false, "Target Cardinality", "String", "[]"));

		pairwiseRelation.addPropEditableAttribute("05#" + "TargetCardinality");
		pairwiseRelation.addPropVisibleAttribute("05#" + "TargetCardinality");
		pairwiseRelation.addPanelVisibleAttribute("05#" + "TargetCardinality"
				+ "#" + "Type" + "#!=#" + "" + "#" + "");
		pairwiseRelation.addPanelSpacersAttribute(" #" + "TargetCardinality"
				+ "#\n");

		variabilityInstVertex.put("PairwiseRelation", new InstConcept(
				"PairwiseRelation", null, pairwiseRelation));
	}

	/**
	 * Creates the objects of the Semantic Model (Semantic concepts). They can
	 * be edited (not saved) on the Semantic perspective and defines the
	 * semantic for elements on the modeling perspective
	 */
	public void createSemantic() {
		MetaConcept metaConcept = (MetaConcept) ((InstConcept) this
				.getSyntaxRefas().getVertex("Concept"))
				.getEditableMetaElement();
		MetaConcept metaOverTwoRelation = (MetaConcept) ((InstConcept) this
				.getSyntaxRefas().getVertex("OverTwoRelation"))
				.getEditableMetaElement();
		MetaPairwiseRelation metaPairwRelCCExt = (MetaPairwiseRelation) ((InstPairwiseRelation) this
				.getSyntaxRefas().getConstraintInstEdge("ExtendsCCRel"))
				.getEditableMetaElement();
		MetaPairwiseRelation metaPairwRelOCExt = (MetaPairwiseRelation) ((InstPairwiseRelation) this
				.getSyntaxRefas().getConstraintInstEdge("ExtendsOCRel"))
				.getEditableMetaElement();
		/*
		 * MetaPairwiseRelation metaPairwRelOOExt = (MetaPairwiseRelation)
		 * ((InstPairwiseRelation) this
		 * .getSyntaxRefas().getConstraintInstEdge("ExtendsOORel"))
		 * .getEditableMetaElement();
		 * 
		 * MetaPairwiseRelation metaPairwRelCCAso = (MetaPairwiseRelation)
		 * ((InstPairwiseRelation) this
		 * .getSyntaxRefas().getConstraintInstEdge("AssoCCRel"))
		 * .getEditableMetaElement();
		 */
		MetaPairwiseRelation metaPairwRelCOAso = (MetaPairwiseRelation) ((InstPairwiseRelation) this
				.getSyntaxRefas().getConstraintInstEdge("AssoCORel"))
				.getEditableMetaElement();
		MetaPairwiseRelation metaPairwRelOCAso = (MetaPairwiseRelation) ((InstPairwiseRelation) this
				.getSyntaxRefas().getConstraintInstEdge("AssoOCRel"))
				.getEditableMetaElement();

		SemanticConcept semGeneralElement = new SemanticConcept(
				"SemGeneralElement");
		InstVertex instVertexGE = new InstConcept("SemGeneralElement",
				metaConcept, semGeneralElement);
		variabilityInstVertex.put("SemGeneralElement", instVertexGE);

		// Design attributes

		semGeneralElement.putSemanticAttribute("Description",
				new SemanticAttribute("Description", "String", false,
						"Description", ""));

		semGeneralElement.putSemanticAttribute("Required",
				new SemanticAttribute("Required", "Boolean", true,
						"Is Required", false));

		semGeneralElement.putSemanticAttribute("Scope", new SemanticAttribute(
				"Scope", "Boolean", true, "Global Scope", true));

		semGeneralElement.putSemanticAttribute("ContextGroup",
				new SemanticAttribute("ContextGroup", "Class", false,
						"Context Group", InstConcept.class.getCanonicalName(),
						"CG", null, ""));

		semGeneralElement.putSemanticAttribute("Core", new SemanticAttribute(
				"Core", "Boolean", false, "Is a Core Concept", false));

		semGeneralElement.putSemanticAttribute("Dead", new SemanticAttribute(
				"Dead", "Boolean", false, "Is a Dead Concept", false));

		semGeneralElement.putSemanticAttribute("IsRootFeature",
				new SemanticAttribute("IsRootFeature", "Boolean", true,
						"Is a Root Feature Concept", false));

		semGeneralElement.addPropEditableAttribute("04#" + "Required");
		semGeneralElement.addPropEditableAttribute("05#" + "Scope");
		semGeneralElement.addPropEditableAttribute("06#" + "ContextGroup" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");
		semGeneralElement.addPropVisibleAttribute("04#" + "Required");
		semGeneralElement.addPropVisibleAttribute("05#" + "Scope");
		semGeneralElement.addPropVisibleAttribute("06#" + "ContextGroup" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");
		semGeneralElement.addPropVisibleAttribute("07#" + "Core");
		semGeneralElement.addPropVisibleAttribute("08#" + "Dead");

		semGeneralElement.addPanelVisibleAttribute("00#" + "ContextGroup" + "#"
				+ "Scope" + "#==#" + "false");
		semGeneralElement.addPanelSpacersAttribute("<<#" + "ContextGroup"
				+ "#>>\n");
		// Configuration attributes

		semGeneralElement.putSemanticAttribute("Active",
				new GlobalConfigAttribute("Active", "Boolean", true,
						"Is Active", true));
		semGeneralElement.putSemanticAttribute("Visibility",
				new GlobalConfigAttribute("Visibility", "Boolean", false,
						"Is Visible", true));

		semGeneralElement.putSemanticAttribute("Allowed",
				new GlobalConfigAttribute("Allowed", "Boolean", true,
						"Is Allowed", true));
		semGeneralElement.putSemanticAttribute("RequiredLevel",
				new SemanticAttribute("RequiredLevel", "Integer", false,
						"Required Level", 0, new RangeDomain(0, 4)));
		// TODO define domain or Enum Level

		semGeneralElement.putSemanticAttribute("ConfigSelected",
				new GlobalConfigAttribute("ConfigSelected", "Boolean", true,
						"Configuration Selected", false));
		semGeneralElement.putSemanticAttribute("ConfigNotSelected",
				new GlobalConfigAttribute("ConfigNotSelected", "Boolean", true,
						"Configuration Not Selected", false));

		semGeneralElement.putSemanticAttribute("DashBoardVisible",
				new GlobalConfigAttribute("DashBoardVisible", "Boolean", false,
						"Visible on Dashboard", true));

		semGeneralElement.putSemanticAttribute("ExportOnConfig",
				new GlobalConfigAttribute("ExportOnConfig", "Boolean", false,
						"Export on Configuration", true));

		semGeneralElement.addPropEditableAttribute("15#" + "ConfigSelected"
				+ "#" + "Core" + "#==#" + "false" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("16#" + "ConfigNotSelected"
				+ "#" + "Dead" + "#==#" + "false" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("03#" + "DashBoardVisible");
		semGeneralElement.addPropEditableAttribute("04#" + "ExportOnConfig");

		semGeneralElement.addPropVisibleAttribute("01#" + "Active");
		semGeneralElement.addPropVisibleAttribute("02#" + "Visibility");

		semGeneralElement.addPropVisibleAttribute("03#" + "DashBoardVisible");
		semGeneralElement.addPropVisibleAttribute("04#" + "ExportOnConfig");
		semGeneralElement.addPropVisibleAttribute("05#" + "RequiredLevel" + "#"
				+ "Core" + "#==#" + "true");

		semGeneralElement.addPropVisibleAttribute("15#" + "ConfigSelected"
				+ "#" + "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropVisibleAttribute("16#" + "ConfigNotSelected"
				+ "#" + "Active" + "#==#" + "true" + "#" + "false");

		// Simulation attributes

		semGeneralElement.putSemanticAttribute("InitialRequiredLevel",
				new ExecCurrentStateAttribute("InitialRequiredLevel",
						"Integer", false, "Initial Required Level", 0,
						new RangeDomain(0, 5)));
		semGeneralElement.putSemanticAttribute("SimRequiredLevel",
				new ExecCurrentStateAttribute("SimRequiredLevel", "Integer",
						false, "Required Level", 0, new RangeDomain(0, 5)));
		semGeneralElement.putSemanticAttribute("HasParent",
				new ExecCurrentStateAttribute("HasParent", "Boolean", false,
						"Has Parent", true));

		semGeneralElement.putSemanticAttribute("Opt",
				new ExecCurrentStateAttribute("Opt", "Integer", false,
						"FilterVariable", 0, new RangeDomain(0, 20)));

		semGeneralElement.putSemanticAttribute("Order",
				new ExecCurrentStateAttribute("Order", "Integer", false,
						"SortVariable", 0, new RangeDomain(0, 40)));

		semGeneralElement.putSemanticAttribute("NextNotSelected",
				new ExecCurrentStateAttribute("NextNotSelected", "Boolean",
						false, "Not selected(inactive)", false));

		semGeneralElement.putSemanticAttribute("NextPrefSelected",
				new ExecCurrentStateAttribute("NextPrefSelected", "Boolean",
						false, "Selected by configuration", false));

		semGeneralElement.putSemanticAttribute("NextNotPrefSelected",
				new ExecCurrentStateAttribute("NextNotPrefSelected", "Boolean",
						false, "Not Selected by configuration", false));

		semGeneralElement.putSemanticAttribute("NextReqSelected",
				new ExecCurrentStateAttribute("NextReqSelected", "Boolean",
						false, "Selected by simulation", false));

		semGeneralElement.addPropVisibleAttribute("01#" + "Selected");
		semGeneralElement.addPropVisibleAttribute("03#" + "NextPrefSelected");
		semGeneralElement.addPropVisibleAttribute("05#" + "NextReqSelected");

		semGeneralElement.addPropVisibleAttribute("02#" + "NotAvailable");
		semGeneralElement.addPropVisibleAttribute("04#" + "NextNotSelected");
		semGeneralElement
				.addPropVisibleAttribute("06#" + "NextNotPrefSelected");

		SemanticConcept semHardConcept = new SemanticConcept(semGeneralElement,
				"semHardConcept");

		semHardConcept.putSemanticAttribute("satisfactionType",
				new SemanticAttribute("satisfactionType", "Enumeration", false,
						"satisfactionType",
						"com.variamos.semantic.types.SatisfactionType",
						"achieve", ""));
		semHardConcept.addPropEditableAttribute("01#" + "satisfactionType");
		semHardConcept.addPropVisibleAttribute("01#" + "satisfactionType");

		InstVertex instVertexHC = new InstConcept("SemHardConcept",
				metaConcept, semHardConcept);
		variabilityInstVertex.put("SemHardConcept", instVertexHC);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("hctoge", instEdge);
		instEdge.setIdentifier("hctoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexHC, true);

		// Feature concepts

		SemanticConcept semFeature = new SemanticConcept(semGeneralElement,
				"Feature");
		InstVertex instVertexF = new InstConcept("SemFeature", metaConcept,
				semFeature);
		variabilityInstVertex.put("SemFeature", instVertexF);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ftoge", instEdge);
		instEdge.setIdentifier("ftoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexF, true);

		// definition of other concepts

		SemanticConcept semAssumption = new SemanticConcept(semHardConcept,
				"Assumption");
		InstVertex instVertexAS = new InstConcept("SemAssumption", metaConcept,
				semAssumption);
		variabilityInstVertex.put("SemAssumption", instVertexAS);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("assutoge", instEdge);
		instEdge.setIdentifier("assutoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexAS, true);

		SemanticConcept semGoal = new SemanticConcept(semHardConcept, "Goal");
		semGoal.addPanelVisibleAttribute("01#" + "satisfactionType");
		semGoal.addPanelSpacersAttribute("<#" + "satisfactionType" + "#>\n");
		InstVertex instVertexG = new InstConcept("SemGoal", metaConcept,
				semGoal);
		variabilityInstVertex.put("SemGoal", instVertexG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("gtoge", instEdge);
		instEdge.setIdentifier("gtoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexG, true);

		SemanticConcept semOperationalization = new SemanticConcept(
				semHardConcept, "Operationalization");
		InstVertex instVertexOper = new InstConcept("SemOperationalization",
				metaConcept, semOperationalization);
		variabilityInstVertex.put("SemOperationalization", instVertexOper);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("opertoge", instEdge);
		instEdge.setIdentifier("opertoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexOper, true);

		SoftSemanticConcept semSoftgoal = new SoftSemanticConcept(
				semGeneralElement, "SoftGoal");
		InstVertex instVertexSG = new InstConcept("SemSoftgoal", metaConcept,
				semSoftgoal);
		variabilityInstVertex.put("SemSoftgoal", instVertexSG);

		semSoftgoal.putSemanticAttribute("SDReqLevel",
				new ExecCurrentStateAttribute("SDReqLevel", "Integer", false,
						"Required Level by SD", 0, new RangeDomain(0, 4)));

		semSoftgoal.putSemanticAttribute("ClaimExpLevel",
				new ExecCurrentStateAttribute("ClaimExpLevel", "Integer",
						false, "Expected Level by Claim", 0, new RangeDomain(0,
								4)));

		semSoftgoal.addPropVisibleAttribute("16#" + "SDReqLevel");
		semSoftgoal.addPropVisibleAttribute("16#" + "ClaimExpLevel");

		semSoftgoal.addPropEditableAttribute("18#" + "SDReqLevel");
		semSoftgoal.addPropEditableAttribute("18#" + "ClaimExpLevel");

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgtoge", instEdge);
		instEdge.setIdentifier("sgtoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexSG, true);

		SemanticVariable semVariable = new SemanticVariable("Variable");

		semVariable.putSemanticAttribute("DashBoardVisible",
				new GlobalConfigAttribute("DashBoardVisible", "Boolean", false,
						"Visible on Dashboard", true));

		semVariable.putSemanticAttribute("ExportOnConfig",
				new GlobalConfigAttribute("ExportOnConfig", "Boolean", false,
						"Export on Configuration", true));

		semVariable.putSemanticAttribute("Scope", new SemanticAttribute(
				"Scope", "Boolean", true, "Global Scope", true));

		semVariable.putSemanticAttribute("ContextGroup", new SemanticAttribute(
				"ContextGroup", "Class", false, "Context Group",
				InstConcept.class.getCanonicalName(), "CG", null, ""));

		semVariable.addPropEditableAttribute("03#" + "DashBoardVisible");
		semVariable.addPropEditableAttribute("04#" + "ExportOnConfig");
		semVariable.addPropVisibleAttribute("03#" + "DashBoardVisible");
		semVariable.addPropVisibleAttribute("04#" + "ExportOnConfig");

		semVariable.addPropEditableAttribute("05#" + "Scope");
		semVariable.addPropEditableAttribute("06#" + "ContextGroup" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");

		semVariable.addPropVisibleAttribute("05#" + "Scope");
		semVariable.addPropVisibleAttribute("06#" + "ContextGroup" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");

		semVariable.addPanelVisibleAttribute("00#" + "ContextGroup" + "#"
				+ "Scope" + "#==#" + "false");
		semVariable.addPanelSpacersAttribute("<<#" + "ContextGroup" + "#>>\n");

		InstVertex instVertexVAR = new InstConcept("SemVariable", metaConcept,
				semVariable);
		variabilityInstVertex.put("SemVariable", instVertexVAR);

		SemanticContextGroup semContextGroup = new SemanticContextGroup(
				"ContextGroup");
		InstVertex instVertexCG = new InstConcept("SemContextGroup",
				metaConcept, semContextGroup);
		variabilityInstVertex.put("SemContextGroup", instVertexCG);

		SemanticConcept semAsset = new SemanticConcept(semGeneralElement,
				"SemAsset");
		InstVertex instVertexAsset = new InstConcept("SemAsset", metaConcept,
				semAsset);
		variabilityInstVertex.put("SemAsset", instVertexAsset);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("astoge", instEdge);
		instEdge.setIdentifier("astoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		List<IntSemanticRelationType> claimSemOverTwoRelList = new ArrayList<IntSemanticRelationType>();
		claimSemOverTwoRelList.add(new SemanticRelationType("and", "And",
				"And", false, false, false, 2, -1, 1, 1));
		claimSemOverTwoRelList.add(new SemanticRelationType("or", "Or", "Or",
				false, true, true, 2, -1, 1, 1));
		claimSemOverTwoRelList.add(new SemanticRelationType("mutex", "Mutex",
				"Mutex.", false, true, true, 2, -1, 1, 1));

		SemanticReasoningConcept semClaim = new SemanticReasoningConcept(
				semGeneralElement, "Claim", true, claimSemOverTwoRelList);
		InstVertex instVertexCL = new InstConcept("SemClaim",
				metaOverTwoRelation, semClaim);
		variabilityInstVertex.put("SemClaim", instVertexCL);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cltoge", instEdge);
		instEdge.setIdentifier("cltoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexCL, true);

		// semClaim.putSemanticAttribute("Operationalizations",
		// new SemanticAttribute("Operationalizations", "MClass", false,
		// "Operationalizations",
		// InstConcept.class.getCanonicalName(),
		// "SemOperationalization", "", ""));
		semClaim.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression",
						InstanceExpression.class.getCanonicalName(), false,
						"Conditional Expression", null));
		semClaim.putSemanticAttribute("CompExp", new GlobalConfigAttribute(
				"CompExp", "Boolean", false, "Boolean Comp. Expression", true));
		semClaim.putSemanticAttribute("ClaimSelected",
				new GlobalConfigAttribute("ClaimSelected", "Boolean", false,
						"Claim Selected", false));
		semClaim.putSemanticAttribute("ClaimExpression",
				new SemanticAttribute("ClaimExpression", "String", false,
						"Claim Expression Text", ""));

		// semClaim.addPanelVisibleAttribute("01#" + "Operationalizations");
		semClaim.addPanelVisibleAttribute("03#" + "ConditionalExpression"); // TODO
																			// move
																			// to
																			// semantic
																			// attributes

		// semClaim.addPropEditableAttribute("01#" + "Operationalizations");
		semClaim.addPropEditableAttribute("03#" + "ConditionalExpression");
		semClaim.addPropEditableAttribute("04#" + "ClaimExpression");

		// semClaim.addPropVisibleAttribute("01#" + "Operationalizations");

		semClaim.addPropVisibleAttribute("03#" + "ConditionalExpression");
		semClaim.addPropVisibleAttribute("04#" + "ClaimExpression");

		semClaim.addPropEditableAttribute("01#" + "CompExp");
		semClaim.addPropVisibleAttribute("01#" + "CompExp");

		semClaim.addPropVisibleAttribute("02#" + "ClaimSelected");

		semClaim.addPanelVisibleAttribute("10#" + "ClaimExpression");

		// semClaim.addPanelSpacersAttribute("#" + "Operationalizations" +
		// "#\n#");

		SemanticReasoningConcept semSoftDependency = new SemanticReasoningConcept(
				semGeneralElement, "SoftDependency", true, null);
		InstVertex instVertexSD = new InstConcept("SemSoftDep", metaConcept,
				semSoftDependency);
		variabilityInstVertex.put("SemSoftDep", instVertexSD);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sdtoge", instEdge);
		instEdge.setIdentifier("sdtoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexSD, true);

		semSoftDependency.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression",
						InstanceExpression.class.getCanonicalName(), false,
						"Conditional Expression", null));
		semSoftDependency.putSemanticAttribute("SDExpression",
				new SemanticAttribute("SDExpression", "String", false,
						"SD Expression Text", ""));
		semSoftDependency.putSemanticAttribute("CompExp",
				new GlobalConfigAttribute("CompExp", "Boolean", false,
						"Boolean Comp. Expression", true));
		semSoftDependency.putSemanticAttribute("SDSelected",
				new GlobalConfigAttribute("SDSelected", "Boolean", false,
						"SD Selected", false));

		semSoftDependency.addPanelVisibleAttribute("03#"
				+ "ConditionalExpression");

		semSoftDependency.addPanelVisibleAttribute("10#" + "SDExpression");

		semSoftDependency.addPropEditableAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropEditableAttribute("04#" + "SDExpression");

		semSoftDependency.addPropVisibleAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropVisibleAttribute("04#" + "SDExpression");

		semSoftDependency.addPropEditableAttribute("01#" + "CompExp");
		semSoftDependency.addPropVisibleAttribute("01#" + "CompExp");

		semSoftDependency.addPropVisibleAttribute("02#" + "SDSelected");

		// Elements Lists
		List<AbstractSemanticVertex> semAssumptionElements = new ArrayList<AbstractSemanticVertex>();
		semAssumptionElements.add(semAssumption);

		List<AbstractSemanticVertex> SemGoalOperElements = new ArrayList<AbstractSemanticVertex>();
		SemGoalOperElements.add(semGoal);
		SemGoalOperElements.add(semOperationalization);

		List<AbstractSemanticVertex> semGoalElements = new ArrayList<AbstractSemanticVertex>();
		semGoalElements.add(semGoal);

		List<AbstractSemanticVertex> semOperationalizationElements = new ArrayList<AbstractSemanticVertex>();
		semOperationalizationElements.add(semOperationalization);

		List<AbstractSemanticVertex> semSoftgoalElements = new ArrayList<AbstractSemanticVertex>();
		semSoftgoalElements.add(semSoftgoal);

		List<AbstractSemanticVertex> semClaimsElements = new ArrayList<AbstractSemanticVertex>();
		semClaimsElements.add(semClaim);

		List<AbstractSemanticVertex> semSDElements = new ArrayList<AbstractSemanticVertex>();
		semSDElements.add(semSoftDependency);

		// Relations

		List<IntSemanticRelationType> hardSemOverTwoRelList = new ArrayList<IntSemanticRelationType>();
		hardSemOverTwoRelList.add(new SemanticRelationType("and", "And", "And",
				false, false, false, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("or", "Or", "Or",
				false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("mutex", "Mutex",
				"Mutex.", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("range", "Range",
				"Range", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("none", "None",
				"None", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("other", "other",
				"other", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType(
				"And/Or/Mutex/[m,n]", "And/Or/Mutex/[m,n]", "AOMR", false,
				true, true, 2, -1, 1, 1));

		List<IntSemanticRelationType> featSemOverTwoRelList = new ArrayList<IntSemanticRelationType>();
		featSemOverTwoRelList.add(new SemanticRelationType("and", "And", "And",
				false, false, false, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("or", "Or", "Or",
				false, true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("mutex", "Mutex",
				"Mutex.", false, true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("range", "Range",
				"Range", false, true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("other", "other",
				"other", false, true, true, 2, -1, 1, 1));

		SemanticOverTwoRelation semHardOverTwoRelation = new SemanticOverTwoRelation(
				semGeneralElement, "OverTwoRelation", hardSemOverTwoRelList);

		InstVertex instVertexHHGR = new InstConcept("HardHardOverTwoRel",
				metaOverTwoRelation, semHardOverTwoRelation);
		variabilityInstVertex.put("HardHardOverTwoRel", instVertexHHGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("hctoHHGR", instEdge);
		instEdge.setIdentifier("hctoHHGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCAso);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexHHGR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("HHGRtohc", instEdge);
		instEdge.setIdentifier("HHGRtohc");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCOAso);
		instEdge.setTargetRelation(instVertexHHGR, true);
		instEdge.setSourceRelation(instVertexHC, true);

		List<AbstractSemanticVertex> semanticVertices = new ArrayList<AbstractSemanticVertex>();

		List<IntSemanticRelationType> structHardSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		structHardSemPairwiseRelList.add(new SemanticRelationType("means_ends",
				"means-ends", "means-ends", true, true, true, 1, -1, 1, 1));
		structHardSemPairwiseRelList
				.add(new SemanticRelationType("implication", "impl.", "Impl.",
						false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> sideHardSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		sideHardSemPairwiseRelList.add(new SemanticRelationType("conflict",
				"conflict", "conflict", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new SemanticRelationType("alternative",
				"altern.", "altern.", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new SemanticRelationType("preferred",
				"pref.", "pref.", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new SemanticRelationType("require",
				"req.", "req.", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new SemanticRelationType("condition",
				"cond.", "cond.", false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> sgPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		sgPairwiseRelList.add(new SemanticRelationType("contribution",
				"contribution", "contribution", true, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("conflict", "conflict",
				"conflict", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("alternative",
				"altern.", "altern.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("preferred", "pref.",
				"pref.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("implication", "impl.",
				"Impl.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("require", "req.",
				"req.", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation directHardHardSemanticEdge = new SemanticPairwiseRelation(
				"sideHardHardDirectEdge", false, sideHardSemPairwiseRelList);
		constraintInstEdges.put("sideHardHardDirectEdge",
				new InstPairwiseRelation(directHardHardSemanticEdge));

		SemanticPairwiseRelation directStructHardHardSemanticEdge = new SemanticPairwiseRelation(
				"structHardHardDirectEdge", false, structHardSemPairwiseRelList);
		constraintInstEdges.put("structHardHardDirectEdge",
				new InstPairwiseRelation(directStructHardHardSemanticEdge));

		List<IntSemanticRelationType> featSideSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		featSideSemPairwiseRelList.add(new SemanticRelationType("require",
				"require", "require", false, true, true, 1, -1, 1, 1));
		featSideSemPairwiseRelList.add(new SemanticRelationType("conflict",
				"excl.", "excl.", false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> featVertSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		featVertSemPairwiseRelList.add(new SemanticRelationType("mandatory",
				"mandatory", "mandatory", true, true, true, 1, -1, 1, 1));
		featVertSemPairwiseRelList.add(new SemanticRelationType("optional",
				"opt.", "opt.", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation directFeatureFeatureVertSemanticEdge = new SemanticPairwiseRelation(
				"FeatureFeatureDirectEdge", false, featVertSemPairwiseRelList);
		constraintInstEdges.put("FeatureFeatureVertDirectEdge",
				new InstPairwiseRelation(directFeatureFeatureVertSemanticEdge));

		SemanticPairwiseRelation directFeatureFeatureSideSemanticEdge = new SemanticPairwiseRelation(
				"FeatureFeatureDirectEdge", false, featSideSemPairwiseRelList);
		constraintInstEdges.put("FeatureFeatureSideDirectEdge",
				new InstPairwiseRelation(directFeatureFeatureSideSemanticEdge));

		SemanticOverTwoRelation semFeatOverTwoRelation = new SemanticOverTwoRelation(
				semGeneralElement, "OverTwoRelation", featSemOverTwoRelList);
		InstVertex instVertexFFGR = new InstConcept("FeatureFeatureGroupRel",
				metaOverTwoRelation, semFeatOverTwoRelation);
		variabilityInstVertex.put("FeatureFeatureGroupRel", instVertexFFGR);

		List<IntSemanticRelationType> assetoperPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		assetoperPairwiseRelList.add(new SemanticRelationType("implementation",
				"implementation", "imp.", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semAssetOperPairwiseRel = new SemanticPairwiseRelation(
				"varAssetOperPairwiseRel", false, assetoperPairwiseRelList);
		constraintInstEdges.put("varAssetOperPairwiseRel",
				new InstPairwiseRelation(semAssetOperPairwiseRel));

		List<IntSemanticRelationType> assetPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		assetPairwiseRelList.add(new SemanticRelationType("delegation",
				"delegation", "deleg.", true, true, true, 1, 1, 1, 1));
		assetPairwiseRelList.add(new SemanticRelationType("Assembly",
				"Assembly", "asssembly", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semAssetPairwiseRel = new SemanticPairwiseRelation(
				"varAssetPairwiseRel", false, assetPairwiseRelList);
		constraintInstEdges.put("varAssetPairwiseRel",
				new InstPairwiseRelation(semAssetPairwiseRel));

		List<IntSemanticRelationType> vcntxPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		vcntxPairwiseRelList.add(new SemanticRelationType("Variable Context",
				"", "", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semvarcntxPairwiseRel = new SemanticPairwiseRelation(
				"varcntxPairwiseRel", false, vcntxPairwiseRelList);
		constraintInstEdges.put("varcntxPairwiseRel", new InstPairwiseRelation(
				semvarcntxPairwiseRel));

		List<IntSemanticRelationType> sdPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		sdPairwiseRelList.add(new SemanticRelationType("SD", "", "", true,
				true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semSDPairwiseRel = new SemanticPairwiseRelation(
				"sdPairwiseRel", false, sdPairwiseRelList);
		constraintInstEdges.put("sdPairwiseRel", new InstPairwiseRelation(
				semSDPairwiseRel));

		List<IntSemanticRelationType> operclaimPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		operclaimPairwiseRelList.add(new SemanticRelationType("OperToClaim",
				"", "", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semOperClaimPairwiseRel = new SemanticPairwiseRelation(
				"operclaimPairwiseRel", false, operclaimPairwiseRelList);
		constraintInstEdges.put("operclaimPairwiseRel",
				new InstPairwiseRelation(semOperClaimPairwiseRel));

		List<IntSemanticRelationType> claimSGPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		claimSGPairwiseRelList.add(new SemanticRelationType("ClaimToSG", "",
				"", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semClaimSGPairwiseRel = new SemanticPairwiseRelation(
				"claimSGPairwiseRel", false, claimSGPairwiseRelList);
		constraintInstEdges.put("claimSGPairwiseRel", new InstPairwiseRelation(
				semClaimSGPairwiseRel));

		List<IntSemanticRelationType> groupPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		groupPairwiseRelList.add(new SemanticRelationType("Group", "", "",
				true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semGroupPairwiseRel = new SemanticPairwiseRelation(
				"groupPairwiseRel", false, groupPairwiseRelList);
		constraintInstEdges.put("groupPairwiseRel", new InstPairwiseRelation(
				semGroupPairwiseRel));
		semGroupPairwiseRel.putSemanticAttribute("AggregationLow1",
				new SemanticAttribute("AggregationLow1", "Integer", false,
						"Aggregation Low1", 0));

//		semGroupPairwiseRel.addPanelVisibleAttribute("03#" + "AggregationLow1");

//		semGroupPairwiseRel.addPanelSpacersAttribute("[#" + "AggregationLow1"
//				+ "#..");

//		semGroupPairwiseRel.addPropEditableAttribute("03#" + "AggregationLow");

//		semGroupPairwiseRel.addPropVisibleAttribute("03#" + "AggregationLow1"
//				+"#"+ "AggregationHigh1" + "#!=#" + "0");

		semGroupPairwiseRel.putSemanticAttribute("AggregationHigh1",
				new SemanticAttribute("AggregationHigh1", "Integer", false,
						"Aggregation High1", 0));

//		semGroupPairwiseRel.addPanelVisibleAttribute("04#" + "AggregationHigh1"
//				+"#"+ "AggregationHigh1" + "#!=#" + "0");

//		semGroupPairwiseRel.addPanelSpacersAttribute("#" + "AggregationHigh1"
//				+ "#]");

//		semGroupPairwiseRel.addPropEditableAttribute("04#" + "AggregationHigh");

//		semGroupPairwiseRel.addPropVisibleAttribute("04#" + "AggregationHigh1");

		List<IntSemanticRelationType> nonePairwiseRelList = new ArrayList<IntSemanticRelationType>();
		nonePairwiseRelList.add(new SemanticRelationType("Group", "", "", true,
				true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation nonePairwiseRel = new SemanticPairwiseRelation(
				"NonePairwiseRel", false, nonePairwiseRelList);
		constraintInstEdges.put("NonePairwiseRel", new InstPairwiseRelation(
				nonePairwiseRel));

		SemanticPairwiseRelation extendsPairwiseRel = new SemanticPairwiseRelation(
				"NonePairwiseRel", false, nonePairwiseRelList);
		constraintInstEdges.put("extendsDirectEdge", new InstPairwiseRelation(
				extendsPairwiseRel));

		List<IntSemanticRelationType> genconsPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		genconsPairwiseRelList.add(new SemanticRelationType(
				"GeneralConstraint", "", "", true, true, true, 1, 1, 1, 1));

		// Feature to Feature

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ftoFFGR", instEdge);
		instEdge.setIdentifier("ftoFFGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCOAso);
		instEdge.setTargetRelation(instVertexFFGR, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("FFGRtof", instEdge);
		instEdge.setIdentifier("FFGRtof");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCAso);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instVertexFFGR, true);

		// Goal to Goal

		// SemanticPairwiseRelation directGoalGoalSemanticEdge = new
		// SemanticPairwiseRelation(
		// "GoalGoalDirectEdge", false, hardSemPairwiseRelList);
		/*
		 * variabilityInstVertex.put("GoalGoalOverTwoRel", new InstConcept(
		 * "GoalGoalOverTwoRel", metaOverTwoRelation,
		 * semanticGoalGoalGroupRelation));
		 */
		// constraintInstEdges.put("GoalGoalDirectEdge", new
		// InstPairwiseRelation(
		// directGoalGoalSemanticEdge));

		// Oper to Goal and Oper

		// SemanticPairwiseRelation directOperGoalSemanticEdge = new
		// SemanticPairwiseRelation(
		// "OperGoalDirectEdge", false, hardSemPairwiseRelList);
		// InstConcept instOperGoal = new InstConcept("OperGoalDirectEdge",
		// metaConcept, directOperGoalSemanticEdge);
		// variabilityInstVertex.put("OperGoalDirectEdge", instOperGoal);

		// instEdge = new InstPairwiseRelation(directOperGoalSemanticEdge);
		// instEdge.setSourceRelation(instVertexOper, true);
		// instEdge.setTargetRelation(instOperGoal, true);
		// constraintInstEdges.put("OperGoalDirectEdge", instEdge);

		// Oper to Oper

		// SemanticPairwiseRelation directOperOperSemanticEdge = new
		// SemanticPairwiseRelation(
		// "OperOperDirectEdge", false, hardSemPairwiseRelList);
		/*
		 * variabilityInstVertex.put("OperOperOverTwoRel", new InstConcept(
		 * "OperOperOverTwoRel", metaOverTwoRelation,
		 * semanticOperOperGroupRelation));
		 */
		// constraintInstEdges.put("OperOperDirectEdge",
		// new InstPairwiseRelation(directOperOperSemanticEdge));

		// SG to SG

		SemanticOverTwoRelation semanticSGSGGroupRelation = new SemanticOverTwoRelation(
				semGeneralElement, "SGtoSGOverTwoRel", hardSemOverTwoRelList);

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semSoftgoal);

		SemanticPairwiseRelation directSGSGSemEdge = new SemanticPairwiseRelation(
				"SGSGDirectEdge", true, sgPairwiseRelList);
		directSGSGSemEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_SOURCE_LEVEL,
				new SemanticAttribute(
						SemanticPairwiseRelation.VAR_SOURCE_LEVEL, "Integer",
						false, SemanticPairwiseRelation.VAR_SOURCE_LEVELNAME,
						0, new RangeDomain(0, 5)));
		directSGSGSemEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_TARGET_LEVEL,
				new SemanticAttribute(
						SemanticPairwiseRelation.VAR_TARGET_LEVEL, "Integer",
						false, SemanticPairwiseRelation.VAR_TARGET_LEVELNAME,
						0, new RangeDomain(0, 5)));
		directSGSGSemEdge.putSemanticAttribute("AggregationLow",
				new SemanticAttribute("AggregationLow", "Integer", false,
						"Aggregation Low", 0));

		directSGSGSemEdge.addPanelVisibleAttribute("07#" + "AggregationLow");

		directSGSGSemEdge.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");

		directSGSGSemEdge.addPropEditableAttribute("07#" + "AggregationLow");

		directSGSGSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

		directSGSGSemEdge.putSemanticAttribute("AggregationHigh",
				new SemanticAttribute("AggregationHigh", "Integer", false,
						"AggregationHigh", 0));

		directSGSGSemEdge.addPanelVisibleAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");

		directSGSGSemEdge.addPropEditableAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPropVisibleAttribute("08#" + "AggregationHigh");

		// directSGSGSemEdge.putSemanticAttribute(
		// SemanticPairwiseRelation.VAR_LEVEL, new SemanticAttribute(
		// SemanticPairwiseRelation.VAR_LEVEL, "Enumeration",
		// false, SemanticPairwiseRelation.VAR_LEVELNAME,
		// SemanticPairwiseRelation.VAR_LEVELCLASS, "plus plus",
		// ""));
		directSGSGSemEdge.addPropEditableAttribute("08#"
				+ SemanticPairwiseRelation.VAR_SOURCE_LEVEL);
		directSGSGSemEdge.addPropVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_SOURCE_LEVEL);
		directSGSGSemEdge.addPanelVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_SOURCE_LEVEL);

		directSGSGSemEdge.addPanelSpacersAttribute(":#"
				+ SemanticPairwiseRelation.VAR_TARGET_LEVEL + "#");
		directSGSGSemEdge.addPropEditableAttribute("09#"
				+ SemanticPairwiseRelation.VAR_TARGET_LEVEL);
		directSGSGSemEdge.addPropVisibleAttribute("09#"
				+ SemanticPairwiseRelation.VAR_TARGET_LEVEL);
		directSGSGSemEdge.addPanelVisibleAttribute("09#"
				+ SemanticPairwiseRelation.VAR_TARGET_LEVEL);

		InstVertex instVertexSGGR = new InstConcept("SGtoSGOverTwoRel",
				metaOverTwoRelation, semanticSGSGGroupRelation);
		variabilityInstVertex.put("SGtoSGOverTwoRel", instVertexSGGR);
		constraintInstEdges.put("SGSGDirectEdge", new InstPairwiseRelation(
				directSGSGSemEdge));

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgtoSGGR", instEdge);
		instEdge.setIdentifier("sgtoSGGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCOAso);
		instEdge.setTargetRelation(instVertexSGGR, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("SGGRtosg", instEdge);
		instEdge.setIdentifier("SGGRtosg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCAso);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instVertexSGGR, true);

		// CV to CG
		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semContextGroup);

		SemanticPairwiseRelation directCVCGSemanticEdge = new SemanticPairwiseRelation(
				"CVCGDirectRel", false, vcntxPairwiseRelList);
		constraintInstEdges.put("CVCGDirectRel", new InstPairwiseRelation(
				directCVCGSemanticEdge));

		// Oper to Claim
		SemanticOverTwoRelation semanticOperClaimGroupRelation = new SemanticOverTwoRelation(
				semGeneralElement, "OpertoClaimOverTwoRel",
				hardSemOverTwoRelList);

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semClaim);

		SemanticPairwiseRelation directOperClaimSemanticEdge = new SemanticPairwiseRelation(
				"OperClaimPairwiseRel", true, operclaimPairwiseRelList);

		InstVertex instVertexCLGR = new InstConcept("OpertoClaimOverTwoRel",
				metaOverTwoRelation, semanticOperClaimGroupRelation);
		variabilityInstVertex.put("OpertoClaimOverTwoRel", instVertexCLGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("opertoCLGR", instEdge);
		instEdge.setIdentifier("opertoCLGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCOAso);
		instEdge.setTargetRelation(instVertexCLGR, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("CLGRtoclaim", instEdge);
		instEdge.setIdentifier("CLGRtoclaim");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instVertexCLGR, true);

		constraintInstEdges.put("OperClaimPairwiseRel",
				new InstPairwiseRelation(directOperClaimSemanticEdge));

		// Claim to SG

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semSoftgoal);

		SemanticPairwiseRelation directClaimSGSemanticEdge = new SemanticPairwiseRelation(
				"ClaimSGDirectEdge", true, claimSGPairwiseRelList);
		directClaimSGSemanticEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_SOURCE_LEVEL,
				new SemanticAttribute(
						SemanticPairwiseRelation.VAR_SOURCE_LEVEL, "Integer",
						false, SemanticPairwiseRelation.VAR_SOURCE_LEVEL, 2,
						new RangeDomain(0, 5)));
		// directClaimSGSemanticEdge.putSemanticAttribute(
		// SemanticPairwiseRelation.VAR_LEVEL, new SemanticAttribute(
		// SemanticPairwiseRelation.VAR_LEVEL, "Enumeration",
		// false, SemanticPairwiseRelation.VAR_LEVEL,
		// SemanticPairwiseRelation.VAR_LEVELCLASS, "plus plus",
		// ""));
		directClaimSGSemanticEdge.addPropEditableAttribute("08#"
				+ SemanticPairwiseRelation.VAR_SOURCE_LEVEL);
		directClaimSGSemanticEdge.addPropVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_SOURCE_LEVEL);
		directClaimSGSemanticEdge.addPanelVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_SOURCE_LEVEL);
		constraintInstEdges.put("ClaimSGDirectEdge", new InstPairwiseRelation(
				directClaimSGSemanticEdge));

		// SD to SG

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semSoftgoal);

		SemanticPairwiseRelation directSDSGSemanticEdge = new SemanticPairwiseRelation(
				"SDSGDirectEdge", true, sdPairwiseRelList);
		directSDSGSemanticEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_LEVEL, new SemanticAttribute(
						SemanticPairwiseRelation.VAR_LEVEL, "Integer", false,
						SemanticPairwiseRelation.VAR_LEVELNAME, 0,
						new RangeDomain(0, 5)));
		// directSDSGSemanticEdge.putSemanticAttribute(
		// SemanticPairwiseRelation.VAR_LEVEL, new SemanticAttribute(
		// SemanticPairwiseRelation.VAR_LEVEL, "Enumeration",
		// false, SemanticPairwiseRelation.VAR_LEVELNAME,
		// SemanticPairwiseRelation.VAR_LEVELCLASS, "plus plus",
		// ""));
		directSDSGSemanticEdge.addPropEditableAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directSDSGSemanticEdge.addPropVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directSDSGSemanticEdge.addPanelVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		constraintInstEdges.put("SDSGDirectEdge", new InstPairwiseRelation(
				directSDSGSemanticEdge));

		// Asset to Asset

		SemanticOverTwoRelation semanticAssetAssetOvertwoRel = new SemanticOverTwoRelation(
				semGeneralElement, "AssetOperGroupRel", hardSemOverTwoRelList);

		InstVertex instVertexASSETGR = new InstConcept("AssetAssetOvertwoRel",
				metaOverTwoRelation, semanticAssetAssetOvertwoRel);
		variabilityInstVertex.put("AssetAssetOvertwoRel", instVertexASSETGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ASSETGRtoasset", instEdge);
		instEdge.setIdentifier("ASSETGRtoasset");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCAso);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instVertexASSETGR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("assettoAssetGR", instEdge);
		instEdge.setIdentifier("assettoAssetGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCOAso);
		instEdge.setTargetRelation(instVertexASSETGR, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		// Asset to Oper
		// TODO use list of possible relations
		SemanticOverTwoRelation semanticAssetOperGroupRelation = new SemanticOverTwoRelation(
				semGeneralElement, "AssetOperGroupRel", hardSemOverTwoRelList);

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semOperationalization);

		InstVertex instVertexOPERGR = new InstConcept("AssetOperGroupRel",
				metaOverTwoRelation, semanticAssetOperGroupRelation);
		variabilityInstVertex.put("AssetOperGroupRel", instVertexOPERGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("assettoOperGR", instEdge);
		instEdge.setIdentifier("assettoOperGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCOAso);
		instEdge.setTargetRelation(instVertexOPERGR, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("OPERGRtooper", instEdge);
		instEdge.setIdentifier("OPERGRtooper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCAso);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instVertexOPERGR, true);

		SemanticPairwiseRelation directAssetOperSemanticEdge = new SemanticPairwiseRelation(
				"AssetOperDirectEdge", false, assetoperPairwiseRelList);
		constraintInstEdges.put("AssetOperDirectEdge",
				new InstPairwiseRelation(directAssetOperSemanticEdge));

	}

	/**
	 * Creates the objects of the Meta Model (Syntax concepts). They can be
	 * edited (not saved) on the Syntax perspective and are the support elements
	 * for the modeling perspective
	 */
	private void createSyntax() {

		MetaView syntaxMetaView = null;
		InstVertex instVertex = null;
		InstView instView = null;

		MetaConcept metaView = (MetaConcept) getSyntaxRefas().getVertex("View")
				.getEditableMetaElement();

		MetaConcept supportMetaElementConcept = (MetaConcept) getSyntaxRefas()
				.getVertex("Concept").getEditableMetaElement();
		MetaConcept supportMetaElementOverTwo = (MetaConcept) getSyntaxRefas()
				.getVertex("OverTwoRelation").getEditableMetaElement();

		MetaConcept supportMetaElementPairwise = (MetaConcept) getSyntaxRefas()
				.getVertex("PairwiseRelation").getEditableMetaElement();

		MetaConcept supportMetaExtendsPairwise = (MetaConcept) getSyntaxRefas()
				.getVertex("ExtendRelation").getEditableMetaElement();

		MetaPairwiseRelation metaPairwiseRelFromView = (MetaPairwiseRelation) ((InstPairwiseRelation) this
				.getSyntaxRefas().getConstraintInstEdge("ViewRelation"))
				.getEditableMetaElement();

		MetaPairwiseRelation metaPairwiseRelExtends = (MetaPairwiseRelation) ((InstPairwiseRelation) this
				.getSyntaxRefas().getConstraintInstEdge("ExtendsRelation"))
				.getEditableMetaElement();

		MetaPairwiseRelation metaPairwiseRelNormal = (MetaPairwiseRelation) ((InstPairwiseRelation) this
				.getSyntaxRefas().getConstraintInstEdge("NormalRelation"))
				.getEditableMetaElement();

		// *************************---------------****************************
		// Goals and availability model

		syntaxMetaView = new MetaView("Variability", true, "Variability View",
				"plnode", "Defines a feature", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Variability Palette", 1, null);

		InstPairwiseRelation directExtendsSemanticEdge = getSemanticRefas()
				.getConstraintInstEdge("extendsDirectEdge");

		MetaPairwiseRelation metaExtendsRel = new MetaPairwiseRelation(
				"ExtendsRelation", true, "ExtendsRelation", "",
				"Extends relation between two"
						+ " hard concepts. Extends syntatic and semanatic"
						+ "attributes", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directExtendsSemanticEdge);

		instView = new InstView("Variability", metaView, syntaxMetaView);
		instViews.add(instView);

		InstConcept semFeature = ((InstConcept) getSemanticRefas().getVertex(
				"SemFeature"));

		InstConcept semHardConcept = ((InstConcept) this.getSemanticRefas()
				.getVertex("SemHardConcept"));

		InstConcept semGoal = ((InstConcept) this.getSemanticRefas().getVertex(
				"SemGoal"));

		MetaConcept syntaxFeature = new MetaConcept("Feature", false,
				"Feature", "plnode", "Defines a feature", 100, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semFeature, true);

		syntaxFeature.addModelingAttribute("name", "String", false, "Name", "");

		syntaxFeature.addPanelVisibleAttribute("03#" + "name");

		syntaxFeature.addPropEditableAttribute("03#" + "name");

		syntaxFeature.addPropVisibleAttribute("03#" + "name");
		
		syntaxFeature.addModelingAttribute("scope", "ContextGroup", false,
				"scope", "");

		InstVertex instVertexF = new InstConcept("Feature",
				supportMetaElementConcept, syntaxFeature);
		variabilityInstVertex.put("Feature", instVertexF);
		// syntaxMetaView.addConcept(syntaxFeature);
		instView.addInstVertex(instVertexF);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-feat", instEdge);
		instEdge.setIdentifier("variab-feat");
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setSourceRelation(instView, true);

		MetaConcept syntaxVariabilityArtifact = new MetaConcept("VA", false,
				"VariabilityArtifact", null, "", 0, 0, null, true, null, 3,
				semFeature, true);
		syntaxVariabilityArtifact.addModelingAttribute("name", "String", false,
				"Name", "");

		syntaxVariabilityArtifact.addPanelVisibleAttribute("03#" + "name");

		syntaxVariabilityArtifact.addPropEditableAttribute("03#" + "name");

		syntaxVariabilityArtifact.addPropVisibleAttribute("03#" + "name");
		
		syntaxVariabilityArtifact.addModelingAttribute("scope", "ContextGroup", false,
				"scope", "");

		InstVertex instVertexVA = new InstConcept("VA",
				supportMetaElementConcept, syntaxVariabilityArtifact);
		variabilityInstVertex.put("VA", instVertexVA);

		MetaConcept syntaxRootFeature = new MetaConcept("RootFeature", true,
				"RootFeature", "plnode", "Defines a root feature", 100, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semFeature, true);

		syntaxRootFeature.setParent(syntaxFeature);

		InstVertex instVertexRF = new InstConcept("RootFeature",
				supportMetaElementConcept, syntaxRootFeature);
		variabilityInstVertex.put("RootFeature", instVertexRF);
		instView.addInstVertex(instVertexRF);

		MetaConcept syntaxGeneralFeature = new MetaConcept("GeneralFeature",
				true, "GeneralFeature", "plnode", "Defines a general feature",
				100, 50, "/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semFeature, true);

		syntaxGeneralFeature.setParent(syntaxFeature);

		InstVertex instVertexGF = new InstConcept("GeneralFeature",
				supportMetaElementConcept, syntaxGeneralFeature);
		variabilityInstVertex.put("GeneralFeature", instVertexGF);
		instView.addInstVertex(instVertexGF);

		MetaConcept syntaxVertexLF = new MetaConcept("LeafFeature", true,
				"LeafFeature", "plnode", "Defines a leaf feature", 100, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semFeature, true);

		syntaxVertexLF.setParent(syntaxFeature);

		InstVertex instVertexLF = new InstConcept("LeafFeature",
				supportMetaElementConcept, syntaxVertexLF);
		variabilityInstVertex.put("LeafFeature", instVertexLF);
		instView.addInstVertex(instVertexLF);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-rfeat", instEdge);
		instEdge.setIdentifier("variab-rfeat");
		instEdge.setTargetRelation(instVertexRF, true);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setSourceRelation(instView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gfeat", instEdge);
		instEdge.setIdentifier("variab-gfeat");
		instEdge.setTargetRelation(instVertexGF, true);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setSourceRelation(instView, true);

		MetaConcept syntaxGoal = new MetaConcept("Goal", true, "Goal",
				"refasgoal", "Defines a goal of the system"
						+ " from the stakeholder perspective that can be"
						+ " satisfied with a clear cut condition", 120, 60,
				"/com/variamos/gui/perspeditor/images/goal.png", true,
				Color.BLUE.toString(), 3, semGoal, true);

		syntaxGoal.setParent(syntaxVariabilityArtifact);

		InstVertex instVertexG = new InstConcept("Goal",
				supportMetaElementConcept, syntaxGoal);
		variabilityInstVertex.put("Goal", instVertexG);
		instView.addInstVertex(instVertexG);

		MetaConcept syntaxTopGoal = new MetaConcept("TopGoal", false,
				"Top Goal", "refasgoal", "Defines a top goal of the system"
						+ " from the stakeholder perspective that can be"
						+ " satisfied with a clear cut condition", 120, 60,
				"/com/variamos/gui/perspeditor/images/goal.png", true,
				Color.BLUE.toString(), 3, semGoal, true);

		syntaxTopGoal.setParent(syntaxGoal);

		InstVertex instVertexTG = new InstConcept("TopGoal",
				supportMetaElementConcept, syntaxTopGoal);
		variabilityInstVertex.put("TopGoal", instVertexTG);
		instView.addInstVertex(instVertexTG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-lfeat", instEdge);
		instEdge.setIdentifier("variab-lfeat");
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setSourceRelation(instView, true);

		InstConcept instLFExtendsPairWiseRel = new InstConcept(
				"LF Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("LF Extends Relation",
				instLFExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extlf-fext", instEdge);
		instEdge.setIdentifier("variab-extlf-fext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instLFExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extlfext-f", instEdge);
		instEdge.setIdentifier("variab-extlfext-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instLFExtendsPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extlf-f", instEdge);
		instEdge.setIdentifier("variab-extlf-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instVertexLF, true);

		InstConcept instGFExtendsPairWiseRel = new InstConcept(
				"GF Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("GF Extends Relation",
				instGFExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extgf-fext", instEdge);
		instEdge.setIdentifier("variab-extgf-fext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGFExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexGF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extgfext-f", instEdge);
		instEdge.setIdentifier("variab-extgfext-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instGFExtendsPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extgf-f", instEdge);
		instEdge.setIdentifier("variab-extgf-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instVertexGF, true);

		InstConcept instRFExtendsPairWiseRel = new InstConcept(
				"RF Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("RF Extends Relation",
				instRFExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extrf-fext", instEdge);
		instEdge.setIdentifier("variab-extrf-fext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instRFExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexRF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extrfext-f", instEdge);
		instEdge.setIdentifier("variab-extrfext-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instRFExtendsPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extrf-f", instEdge);
		instEdge.setIdentifier("variab-extrf-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instVertexRF, true);

		// Feature direct relations

		InstPairwiseRelation semGroupPaiwiseRel = getSemanticRefas()
				.getConstraintInstEdge("groupPairwiseRel");

		MetaPairwiseRelation metaGroupPairwiseRel = new MetaPairwiseRelation(
				"Group Relation", true, "Group Relation", "",
				"Direct relation with a over two relation concept."
						+ " No additional type defined", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				semGroupPaiwiseRel);

		InstConcept instGroupPairWiseRel = new InstConcept("Group Relation",
				supportMetaElementPairwise, metaGroupPairwiseRel);
		metaGroupPairwiseRel.addModelingAttribute("AggregationLow", "Integer",
				false, "Aggregation Low", 1);
		metaGroupPairwiseRel.addPropEditableAttribute("03#" + "AggregationLow");
		metaGroupPairwiseRel.addPropVisibleAttribute("03#" + "AggregationLow");
		metaGroupPairwiseRel.addPanelVisibleAttribute("03#" + "AggregationLow"
				+"#"+ "AggregationHigh" + "#!=#" + "0");
		metaGroupPairwiseRel.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");

		metaGroupPairwiseRel.addModelingAttribute("AggregationHigh", "Integer",
				false, "Aggregation High", 1);
		metaGroupPairwiseRel
				.addPropEditableAttribute("04#" + "AggregationHigh");
		metaGroupPairwiseRel.addPropVisibleAttribute("04#" + "AggregationHigh");
		metaGroupPairwiseRel
				.addPanelVisibleAttribute("04#" + "AggregationHigh"
						+"#"+ "AggregationHigh" + "#!=#" + "0");
		metaGroupPairwiseRel.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");

		instGroupPairWiseRel.setInstAttribute("Type", "Default");
		this.variabilityInstVertex.put("Group Relation", instGroupPairWiseRel);

		InstPairwiseRelation directFeatureFeatureVertSemanticEdge = getSemanticRefas()
				.getConstraintInstEdge("FeatureFeatureVertDirectEdge");

		InstPairwiseRelation directFeatureFeatureSideSemanticEdge = getSemanticRefas()
				.getConstraintInstEdge("FeatureFeatureSideDirectEdge");

		MetaPairwiseRelation metaFeatVertPairwiseRel = new MetaPairwiseRelation(
				"Feature Child Relation", true, "Feature Child Relation", "",
				"Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				directFeatureFeatureVertSemanticEdge);

		InstConcept instFeatVertPairWiseRel = new InstConcept(
				"Feature Child Relation", supportMetaElementPairwise,
				metaFeatVertPairwiseRel);
		this.variabilityInstVertex.put("Feature Child Relation",
				instFeatVertPairWiseRel);

		MetaPairwiseRelation metaFeatSidePairwiseRel = new MetaPairwiseRelation(
				"Feature Side Relation", true, "Feature Side Relation", "",
				"Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				directFeatureFeatureSideSemanticEdge);

		InstConcept instFeatSidePairWiseRel = new InstConcept(
				"Feature Side Relation", supportMetaElementPairwise,
				metaFeatSidePairwiseRel);
		this.variabilityInstVertex.put("Feature Side Relation",
				instFeatSidePairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-featPR", instEdge);
		instEdge.setIdentifier("variab-featPR");
		instEdge.setEditableMetaElement(metaFeatSidePairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-rfeatVPR", instEdge);
		instEdge.setIdentifier("variab-rfeatVPR");
		instEdge.setEditableMetaElement(metaFeatVertPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexRF, true);
		instEdge.setSourceRelation(instVertexGF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gfeatPR", instEdge);
		instEdge.setIdentifier("variab-gfeatPR");
		instEdge.setEditableMetaElement(metaFeatVertPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGF, true);
		instEdge.setSourceRelation(instVertexGF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-lfeat1PR", instEdge);
		instEdge.setIdentifier("variab-lfeat1PR");
		instEdge.setEditableMetaElement(metaFeatVertPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGF, true);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-lfeat2PR", instEdge);
		instEdge.setIdentifier("variab-lfeat2PR");
		instEdge.setEditableMetaElement(metaFeatVertPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexRF, true);
		instEdge.setSourceRelation(instVertexLF, true);
		// Features OverTwoRelations

		InstConcept semanticFeatureFeatureGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("FeatureFeatureGroupRel"));

		MetaOverTwoRelation featureMetaOverTwoRel = new MetaOverTwoRelation(
				"FeatOverTwoRel", true, "FeatOverTwoRel", "plgroup",
				"Group relation between"
						+ " Feature concepts. Defines different types of"
						+ " cardinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticFeatureFeatureGroupRelation, false);

		instVertex = new InstConcept("FeatOverTwoRel",
				supportMetaElementOverTwo, featureMetaOverTwoRel);
		variabilityInstVertex.put("FeatOverTwoRel", instVertex);
		instView.addInstVertex(instVertex);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-featGD", instEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setIdentifier("variab-featGD");
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-GDtoRF", instEdge);
		instEdge.setIdentifier("variab-GDtoRF");
		instEdge.setEditableMetaElement(metaFeatVertPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexRF, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-GDtoGF", instEdge);
		instEdge.setIdentifier("variab-GDtoGF");
		instEdge.setEditableMetaElement(metaFeatVertPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGF, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-lftoGD", instEdge);
		instEdge.setIdentifier("variab-lftoGD");
		instEdge.setEditableMetaElement(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gftoGD", instEdge);
		instEdge.setIdentifier("variab-gftoGD");
		instEdge.setEditableMetaElement(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instVertexGF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-va", instEdge);
		instEdge.setIdentifier("variab-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instView, true);

		InstConcept instGExtendsPairWiseRel = new InstConcept(
				"G Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("G Extends Relation",
				instGExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvatgext", instEdge);
		instEdge.setIdentifier("variab-extvatgext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instGExtendsPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvatextg", instEdge);
		instEdge.setIdentifier("variab-extvatextg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvatg", instEdge);
		instEdge.setIdentifier("variab-extvatg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertexG, true);

		/*
		 * instEdge = new InstEdge();
		 * this.constraintInstEdges.put("variab-topgoal", instEdge);
		 * instEdge.setIdentifier("variab-topgoal");
		 * instEdge.setTargetRelation(instVertexG, true);
		 * instEdge.setSourceRelation(instView, true);
		 */
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-goal", instEdge);
		instEdge.setIdentifier("variab-goal");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexG, true);
		instEdge.setSourceRelation(instView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extgtg", instEdge);
		instEdge.setIdentifier("variab-extgtg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexG, true);
		instEdge.setSourceRelation(instVertexTG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-topgoal", instEdge);
		instEdge.setIdentifier("variab-topgoal");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexTG, true);
		instEdge.setSourceRelation(instView, true);

		MetaConcept syntaxGeneralGoal = new MetaConcept("GeneralGoal", false,
				"General Goal", "refasgoal", "Defines a general goal of the"
						+ " system from the stakeholder perspective that can"
						+ " be satisfied with a clear cut condition", 120, 60,
				"/com/variamos/gui/perspeditor/images/goal.png", true,
				Color.BLUE.toString(), 2, semGoal, true);
		syntaxGeneralGoal.setParent(syntaxGoal);

		InstVertex instVertexGG = new InstConcept("GeneralGoal",
				supportMetaElementConcept, syntaxGeneralGoal);
		variabilityInstVertex.put("GeneralGoal", instVertexGG);
		instView.addInstVertex(instVertexGG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaggg", instEdge);
		instEdge.setIdentifier("variab-extvaggg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexG, true);
		instEdge.setSourceRelation(instVertexGG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gengoal", instEdge);
		instEdge.setIdentifier("variab-gengoal");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexGG, true);
		instEdge.setSourceRelation(instView, true);

		InstConcept semOperationalization = ((InstConcept) this
				.getSemanticRefas().getVertex("SemOperationalization"));
		MetaConcept sOperationalization = new MetaConcept("OPER", true, "OPER",
				"refasoper", "An operationalization allows"
						+ " the partial or complete satisfaction of a goal or"
						+ " another operationalization. If"
						+ " the operationalizations defined is satisfied,"
						+ " according to the defined relation, the goal"
						+ " associated will be also satisfied", 100, 60,
				"/com/variamos/gui/perspeditor/images/operational.png", true,
				Color.BLUE.toString(), 2, semOperationalization, true);

		sOperationalization.setParent(syntaxVariabilityArtifact);

		InstVertex instVertexOper = new InstConcept("OPER",
				supportMetaElementConcept, sOperationalization);
		variabilityInstVertex.put("OPER", instVertexOper);
		instView.addInstVertex(instVertexOper);

		InstConcept instOExtendsPairWiseRel = new InstConcept(
				"O Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("O Extends Relation",
				instOExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaextoper", instEdge);
		instEdge.setIdentifier("variab-extvaextoper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instOExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaoperext", instEdge);
		instEdge.setIdentifier("variab-extvaoperext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instOExtendsPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaoper", instEdge);
		instEdge.setIdentifier("variab-extvaoper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-oper", instEdge);
		instEdge.setIdentifier("variab-oper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instView, true);

		InstConcept semAssumption = ((InstConcept) this.getSemanticRefas()
				.getVertex("SemAssumption"));

		MetaConcept syntaxAssumption = new MetaConcept("Assu", true,
				"Assumption", "refasassump", "An assumption is a"
						+ " condition that should me truth for the goal or"
						+ " operationalization to be satisfied", 100, 60,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.WHITE.toString(), 1, semAssumption, true);

		syntaxAssumption.setParent(syntaxVariabilityArtifact);

		InstVertex instVertexAssum = new InstConcept("Assu",
				supportMetaElementConcept, syntaxAssumption);
		variabilityInstVertex.put("Assu", instVertexAssum);
		instView.addInstVertex(instVertexAssum);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-assum", instEdge);
		instEdge.setIdentifier("variab-assum");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexAssum, true);
		instEdge.setSourceRelation(instView, true);

		InstConcept instAExtendsPairWiseRel = new InstConcept(
				"A Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("A Extends Relation",
				instAExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaextassu", instEdge);
		instEdge.setIdentifier("variab-extvaextassu");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instAExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAssum, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaassuext", instEdge);
		instEdge.setIdentifier("variab-extvaassuext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instAExtendsPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaassu", instEdge);
		instEdge.setIdentifier("variab-extvaassu");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertexAssum, true);

		// Direct Hard Relations

		InstPairwiseRelation directStructHardHardSemanticEdge = getSemanticRefas()
				.getConstraintInstEdge("structHardHardDirectEdge");

		MetaPairwiseRelation metaStructHardPairwiseRel = new MetaPairwiseRelation(
				"HardRelation", true, "HardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 50, 50,
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
		this.variabilityInstVertex.put("GrpMeansEndsRelation",
				instGrpMeansEndsRelation);

		MetaPairwiseRelation metaDirStructHardPairwiseRel = new MetaPairwiseRelation(
				"HardRelation", true, "HardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directStructHardHardSemanticEdge);

		metaDirStructHardPairwiseRel.addModelingAttribute("Aggregation",
				"String", false, "Aggregation", "");
		metaDirStructHardPairwiseRel.addPropEditableAttribute("03#"
				+ "Aggregation");
		metaDirStructHardPairwiseRel.addPropVisibleAttribute("03#"
				+ "Aggregation");
		metaDirStructHardPairwiseRel.addPanelVisibleAttribute("03#" + "Aggregation");
		
		metaDirStructHardPairwiseRel.addModelingAttribute("AggregationLow",
				"Integer", false, "Aggregation Low", 1);
		metaDirStructHardPairwiseRel.addPropEditableAttribute("03#"
				+ "AggregationLow");
		metaDirStructHardPairwiseRel.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		metaDirStructHardPairwiseRel.addPanelVisibleAttribute("03#" + "AggregationLow"
				+"#"+ "AggregationHigh" + "#!=#" + "0");
		metaDirStructHardPairwiseRel.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");

		metaDirStructHardPairwiseRel.addModelingAttribute("AggregationHigh",
				"Integer", false, "Aggregation High", 1);
		metaDirStructHardPairwiseRel.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		metaDirStructHardPairwiseRel.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		metaDirStructHardPairwiseRel.addPanelVisibleAttribute("04#" + "AggregationHigh"
				+"#"+ "AggregationHigh" + "#!=#" + "0");
		metaDirStructHardPairwiseRel.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");

		// TODO create another meta element
		InstConcept instDirMeansEndsRelation = new InstConcept(
				"DirMeansEndsRelation", supportMetaElementPairwise,
				metaDirStructHardPairwiseRel);
		instDirMeansEndsRelation.setInstAttribute("Type", "MeansEnds");
		instDirMeansEndsRelation.getInstAttribute("SourceCardinality").setValue("[0..1]");
		instDirMeansEndsRelation.getInstAttribute("TargetCardinality").setValue("[0..1]");
		this.variabilityInstVertex.put("DirMeansEndsRelation",
				instDirMeansEndsRelation);

		InstPairwiseRelation directSideHardHardSemanticEdge = getSemanticRefas()
				.getConstraintInstEdge("sideHardHardDirectEdge");

		MetaPairwiseRelation metaSideHardPairwiseRel = new MetaPairwiseRelation(
				"SideHardRelation", true, "SideHardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 50, 50,
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
		this.variabilityInstVertex.put("GrpSideHardRelation",
				instGrpSideHardHardPairWiseRel);

		MetaPairwiseRelation metaDirSideHardPairwiseRel = new MetaPairwiseRelation(
				"SideHardRelation", true, "SideHardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSideHardHardSemanticEdge);

		metaDirSideHardPairwiseRel.addModelingAttribute("AggregationLow",
				"Integer", false, "Aggregation Low", 0);
		metaDirSideHardPairwiseRel.addPropEditableAttribute("03#"
				+ "AggregationLow");
		metaDirSideHardPairwiseRel.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		metaDirSideHardPairwiseRel.addPanelVisibleAttribute("03#" + "AggregationLow"
				+"#"+ "AggregationHigh" + "#!=#" + "0");
		metaDirSideHardPairwiseRel.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");
		
		metaDirSideHardPairwiseRel.addModelingAttribute("AggregationHigh",
				"Integer", false, "Aggregation High", 0);
		metaDirSideHardPairwiseRel.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		metaDirSideHardPairwiseRel.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		metaDirSideHardPairwiseRel.addPanelVisibleAttribute("04#" + "AggregationHigh" + "#"
				+ "AggregationHigh" + "#!=#" + "0");
		metaDirSideHardPairwiseRel.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");
		
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
		this.variabilityInstVertex.put("DirSideHardRelation",
				instDirSideHardHardPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-vaPRst-pwr", instEdge);
		instEdge.setIdentifier("variab-vaPRstpwr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirMeansEndsRelation, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwr-vaPRst", instEdge);
		instEdge.setIdentifier("variab-pwr-vaPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instDirMeansEndsRelation, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-vaPRst", instEdge);
		instEdge.setIdentifier("variab-vaPRst");
		instEdge.setEditableMetaElement(metaStructHardPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-vaPRsi", instEdge);
		instEdge.setIdentifier("variab-vaPRsi");
		instEdge.setEditableMetaElement(metaDirSideHardPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-opertoLF", instEdge);
		instEdge.setIdentifier("variab-opertoLF");
		instEdge.setEditableMetaElement(metaFeatVertPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instVertexOper, true);

		// Hard OverTwoRelations

		InstConcept semanticHardHardGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("HardHardOverTwoRel"));

		MetaOverTwoRelation hardMetaOverTwoRel = new MetaOverTwoRelation(
				"HardOverTwoRel", true, "HardOverTwoRel", "plgroup",
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
		this.variabilityInstVertex.put("Hard Group Relation",
				instGroupHardPairWiseRel);

		instVertex = new InstConcept("HardOverTwoRel",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertex.getInstAttribute("Type").setValue( "Group");
		variabilityInstVertex.put("HardOverTwoRel", instVertex);
		instView.addInstVertex(instVertex);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("strvariab-otr-pwrme", instEdge);
		instEdge.setIdentifier("strvariab-otr-pwrme");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpMeansEndsRelation, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("strvariab-pwrme-va", instEdge);
		instEdge.setIdentifier("strvariab-pwrme-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instGrpMeansEndsRelation, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sidevariab-otr-pwrme", instEdge);
		instEdge.setIdentifier("sidevariab-otr-pwrme");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpSideHardHardPairWiseRel, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sidevariab-pwrme-va", instEdge);
		instEdge.setIdentifier("sidevariab-pwrme-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instGrpSideHardHardPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sidevariab-va-pwrd", instEdge);
		instEdge.setIdentifier("sidevariab-va-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirSideHardHardPairWiseRel, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sidevariab-pwrd-va", instEdge);
		instEdge.setIdentifier("sidevariab-pwrd-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instDirSideHardHardPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-va-pwrg", instEdge);
		instEdge.setIdentifier("variab-va-pwrg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGroupHardPairWiseRel, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrg-otr", instEdge);
		instEdge.setIdentifier("variab-pwrg-otr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instGroupHardPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-VAtoHOT", instEdge);
		instEdge.setIdentifier("variab-VAtoHOT");
		instEdge.setEditableMetaElement(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-HOTtoVAst", instEdge);
		instEdge.setIdentifier("variab-HOTtoVAst");
		instEdge.setEditableMetaElement(metaStructHardPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-HOTtoVAsi", instEdge);
		instEdge.setIdentifier("variab-HOTtoVAsi");
		instEdge.setEditableMetaElement(metaSideHardPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-overtwo", instEdge);
		instEdge.setIdentifier("variab-overtwo");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instView, true);

		// *************************---------------****************************
		// Softgoals model

		syntaxMetaView = new MetaView("SoftGoals", true, "Soft Goals View",
				"plnode", "Defines sofgoals", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Soft Goals Palette", 2, null);
		instView = new InstView("SoftGoals", metaView, syntaxMetaView);
		instViews.add(instView);

		InstConcept semSoftgoal = ((InstConcept) this.getSemanticRefas()
				.getVertex("SemSoftgoal"));
		MetaConcept syntaxSoftGoal = new MetaConcept(
				"Softgoal",
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

		syntaxSoftGoal
				.addModelingAttribute("name", "String", false, "Name", "");
		syntaxSoftGoal.addPanelVisibleAttribute("03#" + "name");

		syntaxSoftGoal.addPropEditableAttribute("03#" + "name");
		syntaxSoftGoal.addPropVisibleAttribute("03#" + "name");

		syntaxSoftGoal.addModelingAttribute("scope", "ContextGroup", false,
				"scope", "");
		
		InstVertex instVertexSG = new InstConcept("Softgoal",
				supportMetaElementConcept, syntaxSoftGoal);
		variabilityInstVertex.put("Softgoal", instVertexSG);
		instView.addInstVertex(instVertexSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-softgoal", instEdge);
		instEdge.setIdentifier("sg-softgoal");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instView, true);

		MetaConcept syntaxTopSoftGoal = new MetaConcept(
				"TopSoftgoal",
				false,
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

		syntaxTopSoftGoal.setParent(syntaxSoftGoal);

		InstVertex instVertexTSG = new InstConcept("TopSoftgoal",
				supportMetaElementConcept, syntaxTopSoftGoal);
		variabilityInstVertex.put("TopSoftgoal", instVertexTSG);
		instView.addInstVertex(instVertexTSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-tsgasgPR", instEdge);
		instEdge.setIdentifier("variab-tsgasgPR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instVertexTSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-topSgoal", instEdge);
		instEdge.setIdentifier("sg-topSgoal");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexTSG, true);
		instEdge.setSourceRelation(instView, true);

		MetaConcept syntaxGeneralSoftGoal = new MetaConcept(
				"GeneralSoftgoal",
				false,
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

		syntaxGeneralSoftGoal.setParent(syntaxSoftGoal);
		InstVertex instVertexGSG = new InstConcept("GeneralSoftgoal",
				supportMetaElementConcept, syntaxGeneralSoftGoal);
		variabilityInstVertex.put("GeneralSoftgoal", instVertexGSG);
		instView.addInstVertex(instVertexGSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gsgasgPR", instEdge);
		instEdge.setIdentifier("variab-gsgasgPR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instVertexGSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-genSgoal", instEdge);
		instEdge.setIdentifier("sg-genSgoal");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexGSG, true);
		instEdge.setSourceRelation(instView, true);

		// Direct Soft relation

		InstPairwiseRelation directSGSGSemEdge = getSemanticRefas()
				.getConstraintInstEdge("SGSGDirectEdge");

		MetaPairwiseRelation metaSoftPairWiseRel = new MetaPairwiseRelation(
				"Soft Relation", true, "Soft Relation", "",
				"Direct relation between two soft concepts. Defines"
						+ " different types of relations and cardinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSGSGSemEdge);

		metaSoftPairWiseRel.addModelingAttribute("SourceLevel", "Integer",
				false, "Source Level", 0);
		metaSoftPairWiseRel.addPropEditableAttribute("04#" + "SourceLevel");
		metaSoftPairWiseRel.addPropVisibleAttribute("04#" + "SourceLevel");

		metaSoftPairWiseRel.addModelingAttribute("TargetLevel", "Integer",
				false, "Target Level", 0);
		metaSoftPairWiseRel.addPropEditableAttribute("05#" + "TargetLevel");
		metaSoftPairWiseRel.addPropVisibleAttribute("05#" + "TargetLevel");

		InstConcept instGrpSoftPairWiseRel = new InstConcept(
				"Group Soft Relation", supportMetaElementPairwise,
				metaSoftPairWiseRel);

		instGrpSoftPairWiseRel.setInstAttribute("Type", "Contribution");
		instGrpSoftPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instGrpSoftPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("Group Soft Relation",
				instGrpSoftPairWiseRel);

		MetaPairwiseRelation metaDirSoftPairWiseRel = new MetaPairwiseRelation(
				"Soft Relation", true, "Soft Relation", "",
				"Direct relation between two soft concepts. Defines"
						+ " different types of relations and cardinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSGSGSemEdge);

		metaDirSoftPairWiseRel.addModelingAttribute("AggregationLow", "Integer",
				false, "Aggregation Low", 1);
		metaDirSoftPairWiseRel.addPropEditableAttribute("03#" + "AggregationLow");
		metaDirSoftPairWiseRel.addPropVisibleAttribute("03#" + "AggregationLow");
		metaDirSoftPairWiseRel.addPanelVisibleAttribute("03#" + "AggregationLow"
				+"#"+ "AggregationHigh" + "#!=#" + "0");
		metaDirSoftPairWiseRel.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");
		
		metaDirSoftPairWiseRel.addModelingAttribute("AggregationHigh", "Integer",
				false, "Aggregation High", 1);
		metaDirSoftPairWiseRel.addPropEditableAttribute("04#" + "AggregationHigh");
		metaDirSoftPairWiseRel.addPropVisibleAttribute("04#" + "AggregationHigh");
		metaDirSoftPairWiseRel.addPanelVisibleAttribute("04#" + "AggregationHigh"
				+"#"+ "AggregationHigh" + "#!=#" + "0");
		metaDirSoftPairWiseRel.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");
		
		metaDirSoftPairWiseRel.addModelingAttribute("SourceLevel", "Integer",
				false, "Source Level", 0);
		metaDirSoftPairWiseRel.addPropEditableAttribute("05#" + "SourceLevel");
		metaDirSoftPairWiseRel.addPropVisibleAttribute("05#" + "SourceLevel");

		metaDirSoftPairWiseRel.addModelingAttribute("TargetLevel", "Integer",
				false, "Target Level", 0);
		metaDirSoftPairWiseRel.addPropEditableAttribute("06#" + "TargetLevel");
		metaDirSoftPairWiseRel.addPropVisibleAttribute("06#" + "TargetLevel");

		InstConcept instDirSoftPairWiseRel = new InstConcept(
				"Direct Soft Relation", supportMetaElementPairwise,
				metaDirSoftPairWiseRel);

		instDirSoftPairWiseRel.setInstAttribute("Type", "Contribution");
		instDirSoftPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instDirSoftPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("Direct Soft Relation",
				instDirSoftPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-asgPR", instEdge);
		instEdge.setIdentifier("variab-asgPR");
		instEdge.setEditableMetaElement(metaSoftPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-sg-pwrd", instEdge);
		instEdge.setIdentifier("variab-sg-pwrd");
		instEdge.setEditableMetaElement(metaSoftPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirSoftPairWiseRel, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrd-sg", instEdge);
		instEdge.setIdentifier("variab-pwrd-sg");
		instEdge.setEditableMetaElement(metaSoftPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instDirSoftPairWiseRel, true);

		InstConcept semanticSGSGGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("SGtoSGOverTwoRel"));

		// Group soft relation

		hardMetaOverTwoRel = new MetaOverTwoRelation("SoftgoalOverTwoRel",
				true, "SoftgoalOverTwoRel", "plgroup",
				"Direct relation between soft"
						+ " concepts. Defines different types of relations"
						+ " and cardinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticSGSGGroupRelation, false);

		// TODO Create another group
		InstConcept instGroupSoftPairWiseRel = new InstConcept(
				"Soft Group Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGroupSoftPairWiseRel.setInstAttribute("Type", "Default7");
		instGroupSoftPairWiseRel
				.setInstAttribute("SourceCardinality", "[0..*]");
		instGroupSoftPairWiseRel
				.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("Soft Group Relation",
				instGroupSoftPairWiseRel);

		instVertex = new InstConcept("SoftgoalOverTwoRel",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertex.getInstAttribute("Type").setValue( "Group");
		variabilityInstVertex.put("SoftgoalOverTwoRel", instVertex);
		instView.addInstVertex(instVertex);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-asgtoOT", instEdge);
		instEdge.setIdentifier("sg-asgtoOT");
		instEdge.setEditableMetaElement(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-sg-pwrg", instEdge);
		instEdge.setIdentifier("sg-sg-pwrg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGroupSoftPairWiseRel, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-pwrg-otr", instEdge);
		instEdge.setIdentifier("sg-pwrg-ovt");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instGroupSoftPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-pwrs-sg", instEdge);
		instEdge.setIdentifier("sg--pwrs-sg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instGrpSoftPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-ovt-pwrs", instEdge);
		instEdge.setIdentifier("sg-ovt-pwrs");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpSoftPairWiseRel, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-Totoasg", instEdge);
		instEdge.setIdentifier("sg-OTtoasg");
		instEdge.setEditableMetaElement(metaSoftPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-overtwoS", instEdge);
		instEdge.setIdentifier("sg-overtwoS");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instView, true);

		// *************************---------------****************************
		// Context model

		syntaxMetaView = new MetaView("Context", true, "Context View",
				"plnode", "Defines a feature", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Context Palette", 3, null);
		instView = new InstView("Context", metaView, syntaxMetaView);
		instViews.add(instView);
		// syntaxMetaView.addConcept(syntaxVariable);
		InstConcept semContextGroup = ((InstConcept) this.getSemanticRefas()
				.getVertex("SemContextGroup"));
		MetaConcept syntaxContextGroup = new MetaConcept("CG", true,
				"ContextGroup", "refascontextgrp", " A context group"
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

		syntaxContextGroup.addModelingAttribute("name", "String", false,
				"Name", "");

		InstVertex instVertexCG = new InstConcept("CG",
				supportMetaElementConcept, syntaxContextGroup);
		variabilityInstVertex.put("CG", instVertexCG);
		instView.addInstVertex(instVertexCG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-cg", instEdge);
		instEdge.setIdentifier("context-cg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instView, true);

		InstConcept semVariable = ((InstConcept) this.getSemanticRefas()
				.getVertex("SemVariable"));
		MetaConcept syntaxAbsVariable = new MetaConcept(
				"Variable",
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

		syntaxAbsVariable.addModelingAttribute("name", "String", false, "Name",
				"");
		syntaxAbsVariable.addModelingAttribute("type", "String", false, "Type",
				"");
		syntaxAbsVariable.addModelingAttribute("domain", "String", false,
				"Domain", "");
		syntaxAbsVariable.addModelingAttribute("enumeration", "MetaEnumeration", false,
				"Enumeration", "");
		syntaxAbsVariable.addModelingAttribute("scope", "ContextGroup", false,
				"scope", "");
		
		InstVertex instVertexVar = new InstConcept("Variable",
				supportMetaElementConcept, syntaxAbsVariable);
		variabilityInstVertex.put("Variable", instVertexVar);
		instView.addInstVertex(instVertexVar);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-v", instEdge);
		instEdge.setIdentifier("context-v");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexVar, true);
		instEdge.setSourceRelation(instView, true);

		MetaConcept syntaxGlobalVariable = new MetaConcept(
				"GlobalVariable",
				false,
				"Global Variable",
				"refasglobcnxt",
				"A global variable"
						+ " is an abstraction of a variable or component of the"
						+ " system or the environment that are relevant the system."
						+ " The relevance applies to the system in general."
						+ " The variable values should be"
						+ " simplified as much as possible considering the modeling"
						+ " requirements", 150, 40,
				"/com/variamos/gui/perspeditor/images/globCnxtVar.png", true,
				Color.BLUE.toString(), 1, semVariable, true);

		syntaxGlobalVariable.setParent(syntaxAbsVariable);

		InstVertex instVertexGV = new InstConcept("GlobalVariable",
				supportMetaElementConcept, syntaxGlobalVariable);
		variabilityInstVertex.put("GlobalVariable", instVertexGV);
		instView.addInstVertex(instVertexGV);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-gvtoV", instEdge);
		instEdge.setIdentifier("context-gvtoV");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexVar, true);
		instEdge.setSourceRelation(instVertexGV, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-gv", instEdge);
		instEdge.setIdentifier("context-gv");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexGV, true);
		instEdge.setSourceRelation(instView, true);

		MetaConcept syntaxContextVariable = new MetaConcept(
				"ContextVariable",
				false,
				"Context Variable",
				"refaslocalcnxt",
				" A context variable"
						+ " represents an a variable associated with a context group"
						+ "  This variables may have different"
						+ " values for each context group instance in the system. Context variables"
						+ " are used for SG with a context group associated."
						+ " Context groups are not currently supported.", 150,
				40, "/com/variamos/gui/perspeditor/images/localCnxtVar.png",
				true, Color.BLUE.toString(), 1, semVariable, true);

		syntaxContextVariable.setParent(syntaxAbsVariable);

		InstVertex instVertexCV = new InstConcept("ContextVariable",
				supportMetaElementConcept, syntaxContextVariable);
		variabilityInstVertex.put("ContextVariable", instVertexCV);
		instView.addInstVertex(instVertexCV);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-lvtoV", instEdge);
		instEdge.setIdentifier("context-lvtoV");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexVar, true);
		instEdge.setSourceRelation(instVertexCV, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-lv", instEdge);
		instEdge.setIdentifier("context-lv");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexCV, true);
		instEdge.setSourceRelation(instView, true);

		MetaEnumeration metaEnumeration = new MetaEnumeration("ME", true,
				"MetaEnumeration", "refasenumeration", "Allows the"
						+ " creation of user defined enumerations for"
						+ " variables", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true, "", 1,
				true);
		MetaView syntaxMetaChildView = new MetaView("Context",
				"Context with Enumerations", "Context Palette", 0, null);
		InstView childView = new InstView("Context", metaView,
				syntaxMetaChildView);
		instView.addChildView(childView);
		instVertex = new InstConcept("ME", supportMetaElementConcept,
				metaEnumeration);
		variabilityInstVertex.put("ME", instVertex);
		instView.addInstVertex(instVertex);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-me", instEdge);
		instEdge.setIdentifier("context-me");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instView, true);

		syntaxMetaChildView = new MetaView("ContandModelEnum",
				"Context without Enumerations", "Context Palette", 1, null);
		childView = new InstView("ContandModelEnum", metaView,
				syntaxMetaChildView);
		instView.addChildView(childView);
		// syntaxMetaChildView.addConcept(metaEnumeration);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-withoutcg", instEdge);
		instEdge.setIdentifier("context-withoutcg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(childView, true);
		childView.addInstVertex(instVertexCG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-withoutlv", instEdge);
		instEdge.setIdentifier("context-withoutlv");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexCV, true);
		instEdge.setSourceRelation(childView, true);
		childView.addInstVertex(instVertexCV);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-withoutgv", instEdge);
		instEdge.setIdentifier("context-withoutgv");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexGV, true);
		instEdge.setSourceRelation(childView, true);
		childView.addInstVertex(instVertexGV);

		// Direct variable relations

		InstPairwiseRelation directCVCGSemanticEdge = getSemanticRefas()
				.getConstraintInstEdge("CVCGDirectRel");

		MetaPairwiseRelation metaVariableEdge = new MetaPairwiseRelation(
				"Variable To Context Relation", true,
				"Variable To Context Relation", "",
				"Associates a Context Variable" + " with the Context Group",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directCVCGSemanticEdge);

		MetaPairwiseRelation metaContextEdge = new MetaPairwiseRelation(
				"Context To Context Relation", true,
				"Context To Context Relation", "", "Associates a Context Group"
						+ " with other Context Group", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directCVCGSemanticEdge);

		metaContextEdge.addModelingAttribute("cardinality", "String", false, "cardinality","");
		metaContextEdge.addPanelVisibleAttribute("01#cardinality");
		metaContextEdge.addPropEditableAttribute("01#cardinality");
		metaContextEdge.addPropVisibleAttribute("01#cardinality");
		
		InstConcept instVariablePairWiseRel = new InstConcept(
				"Variable To Context Relation", supportMetaElementPairwise,
				metaVariableEdge);
		instVariablePairWiseRel.setInstAttribute("Type", "Control");
		instVariablePairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instVariablePairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		this.variabilityInstVertex.put("Variable To Context Relation",
				instVariablePairWiseRel);

		InstConcept instCGPairWiseRel = new InstConcept(
				"Context Group Relation", supportMetaElementPairwise,
				metaContextEdge);
		instCGPairWiseRel.setInstAttribute("Type", "SubGroup");
		instCGPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instCGPairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		this.variabilityInstVertex.put("Context Group Relation",
				instCGPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-cvCGPR", instEdge);
		instEdge.setIdentifier("variab-cvCGPR");
		instEdge.setEditableMetaElement(metaVariableEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instVertexCV, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-vCGPR", instEdge);
		instEdge.setIdentifier("variab-vCGPR");
		instEdge.setEditableMetaElement(metaVariableEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instVertexVar, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gvCGPR", instEdge);
		instEdge.setIdentifier("variab-gvCGPR");
		instEdge.setEditableMetaElement(metaVariableEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instVertexGV, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-var-pwrd", instEdge);
		instEdge.setIdentifier("variab-var-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVariablePairWiseRel, true);
		instEdge.setSourceRelation(instVertexVar, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrd-cg", instEdge);
		instEdge.setIdentifier("variab-pwrd-cg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instVariablePairWiseRel, true);

		// MetaPairwiseRelation metaContextEdge = new MetaPairwiseRelation(
		// "Context To Context Relation", true,
		// "Context To Context Relation", "", "Associates a"
		// + " Context Group with other Context Group", 50, 50,
		// "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
		// directCVCGSemanticEdge);
		// constraintInstEdges.put("Context To Context Relation", new
		// InstPairwiseRelation(
		// metaContextEdge, metaVariableEdge));

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-CGCGPR", instEdge);
		instEdge.setIdentifier("variab-CGCGPR");
		instEdge.setEditableMetaElement(metaContextEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instVertexCG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-CG-pwrd", instEdge);
		instEdge.setIdentifier("variab-CG-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instCGPairWiseRel, true);
		instEdge.setSourceRelation(instVertexCG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrd-cg", instEdge);
		instEdge.setIdentifier("variab-pwrd-cg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instCGPairWiseRel, true);

		// *************************---------------****************************
		// SG satisficing Model

		syntaxMetaView = new MetaView("SoftGoalsSatisficing", true,
				"SG Satisficing View", "plnode", "Defines a feature", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Soft Goals Satisficing Palette", 4, null);
		instView = new InstView("SoftGoalsSatisficing", metaView,
				syntaxMetaView);

		// TODO Create another group
		InstConcept instGroupClaimPairWiseRel = new InstConcept(
				"Claim Group Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGroupClaimPairWiseRel.setInstAttribute("Type", "Default");
		instGroupClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGroupClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("Claim Group Relation",
				instGroupClaimPairWiseRel);

		instViews.add(instView);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-tsg", instEdge);
		instEdge.setIdentifier("sgs-tsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexTSG, true);
		instEdge.setSourceRelation(instView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-gsg", instEdge);
		instEdge.setIdentifier("sgs-gsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexGSG, true);
		instEdge.setSourceRelation(instView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-sg", instEdge);
		instEdge.setIdentifier("sgs-sg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instView, true);

		instView.addInstVertex(instVertexOper);
		instView.addInstVertex(instVertexLF);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-oper", instEdge);
		instEdge.setIdentifier("sgs-oper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instView, true);
		instView.addInstVertex(instVertexOper);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-feat", instEdge);
		instEdge.setIdentifier("sgs-feat");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instView, true);
		instView.addInstVertex(instVertexLF);

		InstConcept semClaim = ((InstConcept) this.getSemanticRefas()
				.getVertex("SemClaim"));

		MetaConcept syntaxClaim = new MetaConcept("CL", true, "Claim",
				"refasclaim", "A claim includes a group of"
						+ " operationalizations and a logical condition"
						+ " to evaluate the claim satisfaction."
						+ " The claim is activated only when all the"
						+ " operationalizations are selected and the"
						+ " conditional expression is true. The claim"
						+ " includes a relation with a softgoal (SG)", 100, 50,
				"/com/variamos/gui/perspeditor/images/claim.png", true,
				Color.BLUE.toString(), 1, semClaim, true);

		syntaxClaim.addModelingAttribute("name", "String", false, "Name", "");

		syntaxClaim.addPanelVisibleAttribute("03#" + "name");

		syntaxClaim.addPropEditableAttribute("03#" + "name");

		syntaxClaim.addPropVisibleAttribute("03#" + "name");

		syntaxClaim.addPanelSpacersAttribute("#" + "name" + "#:\n");

		syntaxClaim.addModelingAttribute("ConditionalExpression", "String",
				false, "ConditionalExpression", "");
		syntaxClaim.addPropEditableAttribute("05#" + "ConditionalExpression");
		syntaxClaim.addPropVisibleAttribute("05#" + "ConditionalExpression");

		InstVertex instVertexCL = new InstConcept("CL",
				supportMetaElementOverTwo, syntaxClaim);
		variabilityInstVertex.put("CL", instVertexCL);
		instView.addInstVertex(instVertexCL);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-claim", instEdge);
		instEdge.setIdentifier("sgs-claim");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instView, true);

		InstConcept semSoftDependency = ((InstConcept) this.getSemanticRefas()
				.getVertex("SemSoftDep"));
		MetaConcept syntaxSoftDependency = new MetaConcept(
				"SoftDependency",
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

		syntaxSoftDependency.addModelingAttribute("name", "String", false,
				"Name", "");

		syntaxSoftDependency.addPanelVisibleAttribute("03#" + "name");

		syntaxSoftDependency.addPropEditableAttribute("03#" + "name");

		syntaxSoftDependency.addPropVisibleAttribute("03#" + "name");

		syntaxSoftDependency.addPanelSpacersAttribute("#" + "name" + "#:\n");

		syntaxSoftDependency.addModelingAttribute("ConditionalExpression",
				"String", false, "ConditionalExpression", "");
		syntaxSoftDependency.addPropEditableAttribute("05#"
				+ "ConditionalExpression");
		syntaxSoftDependency.addPropVisibleAttribute("05#"
				+ "ConditionalExpression");

		InstVertex instVertexSD = new InstConcept("SoftDependency",
				supportMetaElementConcept, syntaxSoftDependency);
		variabilityInstVertex.put("SoftDependency", instVertexSD);
		instView.addInstVertex(instVertexSD);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-sd", instEdge);
		instEdge.setIdentifier("sgs-sd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexSD, true);
		instEdge.setSourceRelation(instView, true);

		InstConcept semanticOperClaimGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("OpertoClaimOverTwoRel"));

		hardMetaOverTwoRel = new MetaOverTwoRelation(
				"OperClaimOverTwoRel",
				true,
				"OperClaimOverTwoRel",
				"plgroup",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " expected on the softgoal in case the Claim is satisfied",
				20, 20, "/com/variamos/gui/pl/editor/images/plgroup.png",
				false, "white", 1, semanticOperClaimGroupRelation, false);

		InstPairwiseRelation semClaimPairwiseRel = getSemanticRefas()
				.getConstraintInstEdge("OperClaimPairwiseRel");

		MetaPairwiseRelation metaClaimPairwiseRel = new MetaPairwiseRelation(
				"ClaimRelation",
				true,
				"ClaimRelation",
				"",
				"Represent the relation between"
						+ " an operationalization(s) and a claim. The operationalization(s)"
						+ " is required to satisfy a claim", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				semClaimPairwiseRel);

		InstConcept instGrpClaimPairWiseRel = new InstConcept(
				"GrpClaimRelation", supportMetaElementPairwise,
				metaClaimPairwiseRel);

		instGrpClaimPairWiseRel.setInstAttribute("Type", "OPER-CL");
		instGrpClaimPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instGrpClaimPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("GrpClaimRelation",
				instGrpClaimPairWiseRel);

		MetaPairwiseRelation metaDirClaimPairwiseRel = new MetaPairwiseRelation(
				"ClaimRelation",
				true,
				"ClaimRelation",
				"",
				"Represent the relation between"
						+ " an operationalization(s) and a claim. The operationalization(s)"
						+ " is required to satisfy a claim", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				semClaimPairwiseRel);
		metaDirClaimPairwiseRel.addModelingAttribute("AggregationLow", "Integer",
				false, "Aggregation Low", 1);
		metaDirClaimPairwiseRel.addPropEditableAttribute("03#" + "AggregationLow");
		metaDirClaimPairwiseRel.addPropVisibleAttribute("03#" + "AggregationLow");
		metaDirClaimPairwiseRel.addPanelVisibleAttribute("03#" + "AggregationLow"
				+"#"+ "AggregationHigh" + "#!=#" + "0");
		metaDirClaimPairwiseRel.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");
		
		metaDirClaimPairwiseRel.addModelingAttribute("AggregationHigh", "Integer",
				false, "Aggregation High", 1);
		metaDirClaimPairwiseRel.addPropEditableAttribute("04#" + "AggregationHigh");
		metaDirClaimPairwiseRel.addPropVisibleAttribute("04#" + "AggregationHigh");
		metaDirClaimPairwiseRel.addPanelVisibleAttribute("04#" + "AggregationHigh"
				+"#"+ "AggregationHigh" + "#!=#" + "0");
		metaDirClaimPairwiseRel.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");

		InstConcept instDirClaimPairWiseRel = new InstConcept(
				"DirClaimRelation", supportMetaElementPairwise,
				metaDirClaimPairwiseRel);

		instDirClaimPairWiseRel.setInstAttribute("Type", "OPER-CL");
		instDirClaimPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instDirClaimPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("DirClaimRelation",
				instDirClaimPairWiseRel);

		instVertex = new InstConcept("OperClaimOverTwoRel",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertex.getInstAttribute("Type").setValue("Group");

		variabilityInstVertex.put("OperClaimOverTwoRel", instVertex);
		instView.addInstVertex(instVertex);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-operclaim", instEdge);
		instEdge.setIdentifier("sgs-operclaim");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-pwrd-claim", instEdge);
		instEdge.setIdentifier("sgs-pwrd-claim");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirClaimPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-oper-pwrd", instEdge);
		instEdge.setIdentifier("sgs-oper-pwrd");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirClaimPairWiseRel, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-feaclaim", instEdge);
		instEdge.setIdentifier("sgs-feaclaim");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-OpertoOT", instEdge);
		instEdge.setIdentifier("sgs-OpertoOT");
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setEditableMetaElement(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-Oper-pwrg", instEdge);
		instEdge.setIdentifier("sgs-Oper-pwrg");
		instEdge.setTargetRelation(instGroupClaimPairWiseRel, true);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-pwrg-claimgp", instEdge);
		instEdge.setIdentifier("sgs-pwrg-claimgp");
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setEditableMetaElement(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instGroupClaimPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-featoOT", instEdge);
		instEdge.setIdentifier("sgs-featoOT");
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setEditableMetaElement(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-OTtoCL", instEdge);
		instEdge.setIdentifier("sgs-OTtoCL");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-pwr-CL", instEdge);
		instEdge.setIdentifier("sgs-pwr-CL");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instGrpClaimPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-claimgp-pwr", instEdge);
		instEdge.setIdentifier("sgs-claimgp-pwr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpClaimPairWiseRel, true);
		instEdge.setSourceRelation(instVertex, true);

		InstPairwiseRelation directSDSGSemanticEdge = getSemanticRefas()
				.getConstraintInstEdge("SDSGDirectEdge");

		MetaPairwiseRelation metaSDSGEdge = new MetaPairwiseRelation(
				"SDSGRelation",
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
		this.variabilityInstVertex.put("SDSGRelation", instSDPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-SDASGPR", instEdge);
		instEdge.setIdentifier("variab-SDASGPR");
		instEdge.setEditableMetaElement(metaSDSGEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instVertexSD, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrd-SG", instEdge);
		instEdge.setIdentifier("variab-Spwrd-SG");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instSDPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-SD-pwrd", instEdge);
		instEdge.setIdentifier("variab-SD-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instSDPairWiseRel, true);
		instEdge.setSourceRelation(instVertexSD, true);

		// constraintInstEdges.put("SDSGRelation", new InstEdge(
		// MetaEdge, metaSDSGEdge));

		InstPairwiseRelation directClaimSGSemanticEdge = getSemanticRefas()
				.getConstraintInstEdge("ClaimSGDirectEdge");

		MetaPairwiseRelation metaClaimSGEdge = new MetaPairwiseRelation(
				"Claim-Softgoal Relation",
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
		this.variabilityInstVertex.put("CLSGRelation", instCLPairWiseRel);
		/*
		 * constraintInstEdges .put("Claim-Softgoal Relation", new
		 * InstPairwiseRelation( metaPairwiseRelation, metaClaimSGEdge));
		 */
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-CLASGPR", instEdge);
		instEdge.setIdentifier("variab-CLASGPR");
		instEdge.setEditableMetaElement(metaClaimSGEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instVertexCL, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-CLtoSG", instEdge);
		instEdge.setIdentifier("sgs-CLtoSG");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instVertexCL, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-pwrd-SG", instEdge);
		instEdge.setIdentifier("sgs-pwrdSG");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instCLPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-CL-pwrd", instEdge);
		instEdge.setIdentifier("sgs-CL-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instCLPairWiseRel, true);
		instEdge.setSourceRelation(instVertexCL, true);

		// *************************---------------****************************
		// Assets model

		syntaxMetaView = new MetaView("Assets", true, "Assets View", "plnode",
				"Defines an Asset", 100, 90,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Assets Palette", 5, null);
		instView = new InstView("Assets", metaView, syntaxMetaView);
		instViews.add(instView);
		// syntaxMetaView.addConcept(sOperationalization);
		// syntaxMetaView.addConcept(syntaxVertexLF);
		instView.addInstVertex(instVertexOper);
		instView.addInstVertex(instVertexLF);

		InstConcept semAsset = ((InstConcept) this.getSemanticRefas()
				.getVertex("SemAsset"));
		MetaConcept syntaxAsset = new MetaConcept("Asset", true, "Asset",
				"refasasset", "Represents a asset of the system. The most"
						+ " important assets to represent are those than"
						+ " can implement operationalizations", 100, 40,
				"/com/variamos/gui/perspeditor/images/component.png", true,
				Color.WHITE.toString(), 1, semAsset, true);
		syntaxAsset.addModelingAttribute("name", "String", false, "Name", "");
		syntaxAsset.addModelingAttribute("scope", "ContextGroup", false,
				"scope", "");

		// Create another meta element
		InstConcept instGroupAssetPairWiseRel = new InstConcept(
				"Soft Group Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGroupAssetPairWiseRel.setInstAttribute("Type", "Default");
		instGroupAssetPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGroupAssetPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("Soft Group Relation",
				instGroupAssetPairWiseRel);

		// syntaxMetaView.addConcept(syntaxAsset);

		syntaxAsset.addPanelVisibleAttribute("03#" + "name");
		syntaxAsset.addPropEditableAttribute("03#" + "name");
		syntaxAsset.addPropVisibleAttribute("03#" + "name");
		syntaxMetaChildView = new MetaView("Assets", "Assets General View",
				"Assets Palette", 0, null);
		childView = new InstView("Assets", metaView, syntaxMetaChildView);
		instView.addChildView(childView);

		childView.addInstVertex(instVertexOper);
		childView.addInstVertex(instVertexLF);

		InstVertex instVertexAsset = new InstConcept("Asset",
				supportMetaElementConcept, syntaxAsset);
		variabilityInstVertex.put("Asset", instVertexAsset);
		instView.addInstVertex(instVertexAsset);
		childView.addInstVertex(instVertexAsset);

		InstConcept semanticAssetOperGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("AssetOperGroupRel"));

		InstPairwiseRelation directAssetOperSemanticEdge = getSemanticRefas()
				.getConstraintInstEdge("AssetOperDirectEdge");

		InstConcept semanticAssetAssetGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("AssetAssetOvertwoRel"));

		InstPairwiseRelation directAssetSemanticEdge = getSemanticRefas()
				.getConstraintInstEdge("varAssetPairwiseRel");

		hardMetaOverTwoRel = new MetaOverTwoRelation("AssetOperGroupDep", true,
				"AssetOperGroupDep", "plgroup",
				"Represents the implementation "
						+ "of an operationalization by a group of assets", 20,
				20, "/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticAssetOperGroupRelation, false);
		InstVertex instVertexAssetOper = new InstConcept("AssetOperGroupDep",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexAssetOper.getInstAttribute("Type").setValue("Group");
		variabilityInstVertex.put("AssetOperGroupDep", instVertexAssetOper);
		instView.addInstVertex(instVertexAssetOper);

		hardMetaOverTwoRel = new MetaOverTwoRelation("AssetFeatGroupDep", true,
				"AssetFeatGroupDep", "plgroup",
				"Represents the implementation "
						+ "of a feautre by a group of assets", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticAssetOperGroupRelation, false);
		InstVertex instVertexAssetFeat = new InstConcept("AssetFeatGroupDep",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexAssetFeat.getInstAttribute("Type").setValue("Group");
		variabilityInstVertex.put("AssetFeatGroupDep", instVertexAssetFeat);
		instView.addInstVertex(instVertexAssetFeat);

		hardMetaOverTwoRel = new MetaOverTwoRelation("AssetAssetOvertwoRel",
				true, "AssetAssetOvertwoRel", "plgroup",
				"Represents the relation "
						+ "of an asset with a group of assets", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticAssetAssetGroupRelation, false);
		InstVertex instVertexAssetAsset = new InstConcept(
				"AssetAssetOvertwoRel", supportMetaElementOverTwo,
				hardMetaOverTwoRel);
		instVertexAssetAsset.getInstAttribute("Type").setValue("Group");
		variabilityInstVertex.put("AssetAssetOvertwoRel", instVertexAssetAsset);
		instView.addInstVertex(instVertexAssetAsset);

		MetaPairwiseRelation metaOperPairWiseRel = new MetaPairwiseRelation(
				"Asset To Oper Relation", true, "Asset To Oper Relation", "",
				"Represents the "
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
		this.variabilityInstVertex.put("Dir Asset To Oper Relation",
				instDirOperPairWiseRel);

		InstConcept instGrpOperPairWiseRel = new InstConcept(
				"Group Asset To Oper Relation", supportMetaElementPairwise,
				metaOperPairWiseRel);

		instGrpOperPairWiseRel.setInstAttribute("Type", "Implementation");
		instGrpOperPairWiseRel.setInstAttribute("SourceCardinality", "[0..1]");
		instGrpOperPairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		this.variabilityInstVertex.put("Group Asset To Oper Relation",
				instGrpOperPairWiseRel);

		MetaPairwiseRelation metaFeaturePairWiseRel = new MetaPairwiseRelation(
				"Asset To Feature Relation", true, "Asset To Feature Relation",
				"", "Represents the " + "implementation of an feature by an"
						+ " asset", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directAssetOperSemanticEdge);

		InstConcept instFeautePairWiseRel = new InstConcept(
				"Asset To Feature Relation", supportMetaElementPairwise,
				metaFeaturePairWiseRel);
		this.variabilityInstVertex.put("Asset To Feature Relation",
				instFeautePairWiseRel);

		MetaPairwiseRelation metaAssetPairWiseRel = new MetaPairwiseRelation(
				"Asset To Asset Relation", true, "Asset To Asset Relation", "",
				"Represents a " + "type of an operationzalization between "
						+ " assets", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directAssetSemanticEdge);
		metaAssetPairWiseRel.addModelingAttribute("AggregationLow", "Integer",
				false, "Aggregation Low", 1);
		metaAssetPairWiseRel.addPropEditableAttribute("03#" + "AggregationLow");
		metaAssetPairWiseRel.addPropVisibleAttribute("03#" + "AggregationLow");
		metaAssetPairWiseRel.addPanelVisibleAttribute("03#" + "AggregationLow"
				+"#"+ "AggregationHigh" + "#!=#" + "0");
		metaAssetPairWiseRel.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");

		metaAssetPairWiseRel.addModelingAttribute("AggregationHigh", "Integer",
				false, "AggregationHigh", 1);
		metaAssetPairWiseRel.addPropEditableAttribute("04#" + "AggregationHigh");
		metaAssetPairWiseRel.addPropVisibleAttribute("04#" + "AggregationHigh");
		metaAssetPairWiseRel.addPanelVisibleAttribute("04#" + "AggregationHigh"
				+"#"+ "AggregationHigh" + "#!=#" + "0");
		metaAssetPairWiseRel.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");
		
		InstConcept instDirAssetPairWiseRel = new InstConcept(
				"Dir Asset To Asset Relation", supportMetaElementPairwise,
				metaAssetPairWiseRel);

		instDirAssetPairWiseRel.setInstAttribute("Type", "ASSET-ASSET");
		instDirAssetPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instDirAssetPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("Dir Asset To Asset Relation",
				instDirAssetPairWiseRel);

		MetaPairwiseRelation metaGrpAssetPairWiseRel = new MetaPairwiseRelation(
				"Asset To Asset Relation", true, "Asset To Asset Relation", "",
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
		this.variabilityInstVertex.put("Group Asset To Asset Relation",
				instGrpAssetPairWiseRel);

		InstConcept instGrpAssetAssetPairWiseRel = new InstConcept(
				"Group Asset-Asset Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGrpAssetAssetPairWiseRel.setInstAttribute("Type", "Default");
		instGrpAssetAssetPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpAssetAssetPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("Group Asset-Asset Relation",
				instGrpAssetAssetPairWiseRel);

		InstConcept instGrpAssetOperPairWiseRel = new InstConcept(
				"Group Asset-Oper Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGrpAssetOperPairWiseRel.setInstAttribute("Type", "Default");
		instGrpAssetOperPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpAssetOperPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("Group Asset-Oper Relation",
				instGrpAssetOperPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assettoOper", instEdge);
		instEdge.setIdentifier("asset0-assettoOper");
		instEdge.setEditableMetaElement(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assetoper-pwrd", instEdge);
		instEdge.setIdentifier("asset0-assetoper-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirOperPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwrd-assetoper", instEdge);
		instEdge.setIdentifier("asset0-pwrd-assetoper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instDirOperPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assettoFeat", instEdge);
		instEdge.setIdentifier("asset0-assettoFeat");
		instEdge.setEditableMetaElement(metaFeaturePairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assettoasset", instEdge);
		instEdge.setIdentifier("asset0-assettoasset");
		instEdge.setEditableMetaElement(metaAssetPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assetasset-pwrd", instEdge);
		instEdge.setIdentifier("asset0-assetasset-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirAssetPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwrd-assetasset", instEdge);
		instEdge.setIdentifier("asset0-pwrd-assetasset");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instDirAssetPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assettoOTG", instEdge);
		instEdge.setIdentifier("asset0-assettoOTG");
		instEdge.setEditableMetaElement(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetOper, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-asset-pwrgo", instEdge);
		instEdge.setIdentifier("asset0-asset-pwrgo");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpAssetOperPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwrgo-assetgp", instEdge);
		instEdge.setIdentifier("asset0-pwrgo-assetgp");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetOper, true);
		instEdge.setSourceRelation(instGrpAssetOperPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assettoOTF", instEdge);
		instEdge.setIdentifier("asset0-assettoOTF");
		instEdge.setEditableMetaElement(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetFeat, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-OTGtoOper", instEdge);
		instEdge.setIdentifier("asset0-OTGtoOper");
		instEdge.setEditableMetaElement(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instVertexAssetOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwro-oper", instEdge);
		instEdge.setIdentifier("asset0-pwro-oper");
		instEdge.setEditableMetaElement(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instGrpOperPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assetgp-pwro", instEdge);
		instEdge.setIdentifier("asset0-assetgp-pwro");
		instEdge.setEditableMetaElement(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpOperPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAssetOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-asset-pwrg", instEdge);
		instEdge.setIdentifier("asset0-asset-pwrg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpAssetAssetPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwrg-assetgp", instEdge);
		instEdge.setIdentifier("asset0-pwrg-assetgp");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetAsset, true);
		instEdge.setSourceRelation(instGrpAssetAssetPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assettoOTAsset", instEdge);
		instEdge.setIdentifier("asset0-assettoOTAsset");
		instEdge.setEditableMetaElement(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetAsset, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assetgp-pwr", instEdge);
		instEdge.setIdentifier("asset0-assetgp-pwr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpAssetPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAssetAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwr-asset", instEdge);
		instEdge.setIdentifier("asset0-pwr-asset");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instGrpAssetPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-OTGtoAsset", instEdge);
		instEdge.setIdentifier("asset0-OTGtoAsset");
		instEdge.setEditableMetaElement(metaAssetPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instVertexAssetAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-OTGtoFeat", instEdge);
		instEdge.setIdentifier("asset0-OTGtoFeat");
		instEdge.setEditableMetaElement(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instVertexAssetFeat, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-asset", instEdge);
		instEdge.setIdentifier("asset0-asset");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(childView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-oper", instEdge);
		instEdge.setIdentifier("asset0-oper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(childView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-feat", instEdge);
		instEdge.setIdentifier("asset0-feat");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(childView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assetoper", instEdge);
		instEdge.setIdentifier("asset0-assetoper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexAssetOper, true);
		instEdge.setSourceRelation(childView, true);

		syntaxMetaChildView = new MetaView("FunctionalAssets",
				"Functional Assets Relations", "Assets Palette", 1, null);
		childView = new InstView("FunctionalAssets", metaView,
				syntaxMetaChildView);
		instView.addChildView(childView);
		// syntaxMetaChildView.addConcept(sOperationalization);
		// childView.addInstVertex(instVertexOper);
		childView.addInstVertex(instVertexAsset);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset1-asset", instEdge);
		instEdge.setIdentifier("asset1-asset");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(childView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset1-oper", instEdge);
		instEdge.setIdentifier("asset1-oper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(childView, true);

		syntaxMetaChildView = new MetaView("StructuralAssets",
				"Structural Assets Relations", "Assets Palette", 2, null);
		childView = new InstView("StructuralAssets", metaView,
				syntaxMetaChildView);
		instView.addChildView(childView);

		// syntaxMetaChildView.addConcept(sOperationalization);
		// childView.addInstVertex(instVertexOper);
		childView.addInstVertex(instVertexAsset);

		// syntaxMetaView.addConcept(syntaxGroupDependency);
		// syntaxMetaChildView.addConcept(syntaxGroupDependency);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset2-asset", instEdge);
		instEdge.setIdentifier("asset2-asset");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(childView, true);

		/*
		 * instEdge = new InstEdge();
		 * this.constraintInstEdges.put("asset2-grpoper", instEdge);
		 * instEdge.setIdentifier("asset2-grpoper");
		 * instEdge.setTargetRelation(instVertex, true);
		 * instEdge.setSourceRelation(childView, true);
		 */

		// constraintInstEdges.put("Asset To Oper Relation", new InstEdge(
		// MetaEdge, metaOperEdge));

	}

	public PerspectiveType getPerspectiveType() {
		return perspectiveType;
	}

	public void setInstGroupDependencies(
			Map<String, InstOverTwoRelation> instGroupDependencies) {
		this.instGroupDependencies = instGroupDependencies;
	}

	public void setInstViews(List<InstView> instViews) {
		this.instViews = instViews;
	}

	public List<InstView> getInstViews() {
		return instViews;
	}

	public Map<String, MetaElement> getValidPairwiseRelations(
			MetaElement instElement, MetaElement instElement2, boolean first) {
		Map<String, MetaElement> out = new HashMap<String, MetaElement>();
		for (InstPairwiseRelation pwr : constraintInstEdges.values()) {
			if (pwr.getSourceRelations().size() > 0
					&& pwr.getTargetRelations().size() > 0) {
				MetaElement sourceMetaElement = pwr.getSourceRelations().get(0)
						.getEditableMetaElement();
				MetaElement targetMetaElement = pwr.getTargetRelations().get(0)
						.getEditableMetaElement();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getIdentifier().equals(
						instElement.getIdentifier())
						&& targetMetaElement.getIdentifier().equals(
								instElement2.getIdentifier()))
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
		if (instElement instanceof MetaConcept
				&& ((MetaConcept) instElement).getParent() != null && first)
			out.putAll(getValidPairwiseRelations(
					((MetaConcept) instElement).getParent(), instElement2, true));

		if (instElement2 instanceof MetaConcept
				&& ((MetaConcept) instElement2).getParent() != null)
			out.putAll(getValidPairwiseRelations(instElement,
					((MetaConcept) instElement2).getParent(), false));

		return out;
	}

	public MetaPairwiseRelation getValidMetaPairwiseRelation(
			MetaElement instElement, MetaElement instElement2,
			String metaPairwiseIden, boolean first) {
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
				if (sourceMetaElement.getIdentifier().equals(
						instElement.getIdentifier())
						&& targetMetaElement.getIdentifier().equals(
								instElement2.getIdentifier())
						&& pwr.getEditableMetaElement().getIdentifier()
								.equals(metaPairwiseIden))
					return (MetaPairwiseRelation) pwr.getEditableMetaElement();
			}
		}
		if (instElement2 instanceof MetaConcept
				&& ((MetaConcept) instElement2).getParent() != null) {
			MetaPairwiseRelation out = (getValidMetaPairwiseRelation(
					instElement, ((MetaConcept) instElement2).getParent(),
					metaPairwiseIden, false));
			if (out != null)
				return out;
		}
		if (instElement instanceof MetaConcept
				&& ((MetaConcept) instElement).getParent() != null && first)
			return (getValidMetaPairwiseRelation(
					((MetaConcept) instElement).getParent(), instElement2,
					metaPairwiseIden, true));

		return null;
	}

	public void updateValidationLists(InstElement elm, InstElement instSource,
			InstElement instTarget) {
		List<InstAttribute> visible = elm.getVisibleVariables();
		InstPairwiseRelation instPairwise = (InstPairwiseRelation) elm;
		if (instSource == null && instPairwise.getSourceRelations().size() > 0)
			instSource = instPairwise.getSourceRelations().get(0);
		if (instTarget == null && instPairwise.getTargetRelations().size() > 0)
			instTarget = instPairwise.getTargetRelations().get(0);
		for (InstAttribute v : visible) {
			Map<String, MetaElement> mapElements = null;
			if (elm instanceof InstPairwiseRelation && instSource != null) {
				mapElements = getSyntaxRefas().getValidPairwiseRelations(
						instSource.getTransSupportMetaElement(),
						instTarget.getTransSupportMetaElement(), true);
			}
			v.updateValidationList((InstElement) elm, mapElements);
		}
	}

	public boolean elementsValidation(String element, int modelViewInd,
			int modelViewSubInd) {
		if (modelViewInd < instViews.size() && modelViewSubInd == -1) {
			for (InstElement instElement : instViews.get(modelViewInd)
					.getTargetRelations()) {
				if (instElement.getTargetRelations().get(0).getIdentifier()
						.equals(element))
					return true;
			}
		}
		if (modelViewInd < instViews.size()
				&& modelViewSubInd != -1
				&& modelViewSubInd < instViews.get(modelViewInd)
						.getChildViews().size()) {
			Iterator<InstVertex> instVertex = instViews.get(modelViewInd)
					.getChildViews().get(modelViewSubInd).getInstVertices()
					.iterator();
			while (instVertex.hasNext())
				if (instVertex.next().getIdentifier().equals(element))
					return true;
		}
		return false;
	}

	public void clear() {
		this.instViews.clear();
		this.instGroupDependencies.clear();
		this.variabilityInstVertex.clear();
		this.constraintInstEdges.clear();
		this.otherInstVertex.clear();
	}
}