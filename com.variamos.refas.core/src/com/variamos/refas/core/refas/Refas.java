package com.variamos.refas.core.refas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cfm.common.AbstractModel;
import com.cfm.hlcl.RangeDomain;
import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.VariabilityElement;
import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticVertex;
import com.variamos.refas.core.sematicsmetamodel.SemanticConcept;
import com.variamos.refas.core.sematicsmetamodel.SemanticContextGroup;
import com.variamos.refas.core.sematicsmetamodel.SemanticOverTwoRelation;
import com.variamos.refas.core.sematicsmetamodel.SemanticPairwiseRelation;
import com.variamos.refas.core.sematicsmetamodel.SemanticRelationType;
import com.variamos.refas.core.sematicsmetamodel.SemanticVariable;
import com.variamos.refas.core.sematicsmetamodel.SoftSemanticConcept;
import com.variamos.refas.core.sematicsmetamodel.SoftSemanticConceptSatisficing;
import com.variamos.refas.core.types.ConceptType;
import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.refas.core.types.GroupRelationType;
import com.variamos.refas.core.types.PerspectiveType;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstEnumeration;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodel.InstView;
import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaEnumeration;
import com.variamos.syntaxsupport.metamodelsupport.MetaOverTwoRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaPairwiseRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaView;
import com.variamos.syntaxsupport.metamodelsupport.ModelingAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SemanticAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SimulationConfigAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SimulationStateAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticOverTwoRelation;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticPairwiseRelType;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticPairwiseRelation;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticRelationType;

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
public class Refas extends AbstractModel {

	private Refas syntaxRefas;
	private Refas semanticRefas;
	/**
	 * 
	 */
	private Map<String, InstVertex> variabilityInstVertex;
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

	public Refas(PerspectiveType perspectiveType) {
		this(perspectiveType, null, null);
	}

	public Refas(Refas syntaxRefas) {
		this(PerspectiveType.semantic, syntaxRefas, null);
	}

	public Refas(PerspectiveType perspectiveType, Refas syntaxRefas,
			Refas semanticRefas) {
		this.perspectiveType = perspectiveType;
		this.syntaxRefas = syntaxRefas;
		this.semanticRefas = semanticRefas;
		variabilityInstVertex = new HashMap<String, InstVertex>();
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

	public Refas getSyntaxRefas() {
		return syntaxRefas;
	}

	public Refas getSemanticRefas() {
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

	public Map<String, InstVertex> getVariabilityVertex() {
		return variabilityInstVertex;
	}

	public Collection<InstVertex> getVariabilityVertexCollection() {
		return variabilityInstVertex.values();
	}

	public void setVariabilityVertex(Map<String, InstVertex> elements) {
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

	public InstVertex getVertex(String vertexId) {
		InstVertex out = variabilityInstVertex.get(vertexId);
		if (out == null)
			out = instGroupDependencies.get(vertexId);
		return out;
	}

	public Set<InstVertex> getVertices() {
		Set<InstVertex> out = new HashSet<InstVertex>();
		out.addAll(variabilityInstVertex.values());
		out.addAll(instGroupDependencies.values());
		out.addAll(otherInstVertex.values());
		return out;
	}

	public InstPairwiseRelation getConstraintInstEdge(String edgeId) {
		return constraintInstEdges.get(edgeId);
	}

	public com.variamos.syntaxsupport.metamodel.InstElement getElement(
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
	public List<InstVertex> getConstraintVertexCollection() {
		ArrayList<InstVertex> out = new ArrayList<InstVertex>();
		out.addAll(variabilityInstVertex.values());
		out.addAll(instGroupDependencies.values());
		return out;
	}

	public Map<String, InstVertex> getConstraintVertex() {
		Map<String, InstVertex> out = new HashMap<String, InstVertex>();
		out.putAll(variabilityInstVertex);
		out.putAll(instGroupDependencies);
		return out;
	}

	public List<String> modelElements(int modelViewInd, int modelViewSubInd) {
		List<String> elements = new ArrayList<String>();
		// modelViewInd = -1; // TODO for initial testing, delete
		if (modelViewInd == -1) {
			for (InstVertex instVertex : variabilityInstVertex.values()) {
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

		semConcept.putSemanticAttribute("identifier", new ModelingAttribute(
				"Identifier", "String", false, "Concept Identifier", ""));
		semConcept.addPropEditableAttribute("01#" + "identifier");
		semConcept.addPropVisibleAttribute("01#" + "identifier");

		semConcept.addPanelVisibleAttribute("01#" + "identifier");
		semConcept.addPanelSpacersAttribute("#" + "identifier" + "#\n\n");

		MetaConcept metaConcept = new MetaConcept("Concept", true, "Concept",
				"refasenumeration", "MetaConcept", 100, 150,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.BLUE.toString(), 3, true, semConcept);
		SemanticConcept semOverTwoRelation = new SemanticConcept(semConcept,
				"OverTwoRelation");

		MetaConcept enumeration = new MetaConcept("Enumeration", true,
				"Enumeration", "refasenumeration", "MetaEnumeration", 100, 150,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.BLUE.toString(), 3, true, semConcept);

		MetaConcept overTwoRelation = new MetaConcept("OverTwoRelation", true,
				"OverTwoRelation", "refasenumeration", "OverTwoRelation", 100,
				150, "/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.BLUE.toString(), 3, true, semOverTwoRelation);

		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("Concept", new InstConcept("Concept", null,
				metaConcept));

		variabilityInstVertex.put("Enumeration", new InstConcept("Enumeration",
				null, enumeration));

		variabilityInstVertex.put("OverTwoRelation", new InstConcept(
				"OverTwoRelation", null, overTwoRelation));

		MetaPairwiseRelation pairwiseRelation = new MetaPairwiseRelation();

		constraintInstEdges.put("PairwiseRelation", new InstPairwiseRelation(
				pairwiseRelation));

	}

	/**
	 * Creates the objects to support the syntac Meta Model. They are displayed
	 * on the palette of the syntax perspective
	 */
	private void createBasicSyntax() {
		SemanticConcept semView = new SemanticConcept();

		semView.putSemanticAttribute("MetaType", new ModelingAttribute(
				"MetaType", "Enumeration", false, "MetaConcept Type",
				ConceptType.class.getCanonicalName(), "MetaView"));
		semView.putSemanticAttribute("Identifier", new ModelingAttribute(
				"Identifier", "String", false, "Concept Identifier", ""));
		semView.putSemanticAttribute("Visible", new ModelingAttribute(
				"Visible", "Boolean", false, "Visible", true));
		semView.putSemanticAttribute("Name", new ModelingAttribute("Name",
				"String", false, "Concept Name", ""));
		semView.putSemanticAttribute("Sytle", new ModelingAttribute("Sytle",
				"String", false, "Drawing Style", "refasclaim"));
		semView.putSemanticAttribute("Description", new ModelingAttribute(
				"Description", "String", false, "Description", ""));
		semView.putSemanticAttribute("Width", new ModelingAttribute("Width",
				"Integer", false, "Initial Width", 100));
		semView.putSemanticAttribute("Height", new ModelingAttribute("Height",
				"Integer", false, "Initial Height", 40));
		semView.putSemanticAttribute("Image", new ModelingAttribute("Image",
				"String", false, "Image File",
				"/com/variamos/gui/refas/editor/images/claim.png"));
		semView.putSemanticAttribute("BorderStroke", new ModelingAttribute(
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

		MetaConcept view = new MetaConcept("View", true, "View", "refasview",
				"View", 100, 30,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.WHITE.toString(), 3, true, semView);

		variabilityInstVertex.put("View", new InstConcept("View", null, view));

		SemanticConcept semVertex = new SemanticConcept();

		semVertex.putSemanticAttribute("Name", new ModelingAttribute("Name",
				"String", false, "Concept Name", ""));
		semVertex.putSemanticAttribute("Description", new ModelingAttribute(
				"Description", "String", false, "Description", ""));

		semVertex.putSemanticAttribute("MetaType", new ModelingAttribute(
				"MetaType", "Enumeration", false, "MetaConcept Type",
				ConceptType.class.getCanonicalName(), "MetaConcept"));
		semVertex.putSemanticAttribute("Identifier", new ModelingAttribute(
				"Identifier", "String", false, "Concept Identifier", ""));
		semVertex.putSemanticAttribute("Visible", new ModelingAttribute(
				"Visible", "Boolean", false, "Visible", true));
		semVertex.putSemanticAttribute("Name", new ModelingAttribute("Name",
				"String", false, "Concept Name", ""));
		semVertex.putSemanticAttribute("Sytle", new ModelingAttribute("Sytle",
				"String", false, "Drawing Style", "refasclaim"));
		semVertex.putSemanticAttribute("Description", new ModelingAttribute(
				"Description", "String", false, "Description", ""));
		semVertex.putSemanticAttribute("Width", new ModelingAttribute("Width",
				"Integer", false, "Initial Width", 100));
		semVertex.putSemanticAttribute("Height", new ModelingAttribute(
				"Height", "Integer", false, "Initial Height", 40));
		semVertex.putSemanticAttribute("Image", new ModelingAttribute("Image",
				"String", false, "Image File",
				"/com/variamos/gui/refas/editor/images/claim.png"));
		semVertex.putSemanticAttribute("TopConcept", new ModelingAttribute(
				"TopConcept", "Boolean", false, "Is Top Concept", true));
		semVertex.putSemanticAttribute("BackgroundColor",
				new ModelingAttribute("BackgroundColor", "String", false,
						"Bacground Color", "java.awt.Color[r=0,g=0,b=255]"));
		semVertex.putSemanticAttribute("BorderStroke", new ModelingAttribute(
				"BorderStroke", "Integer", false, "Border Stroke", 1));
		semVertex.putSemanticAttribute("Resizable", new ModelingAttribute(
				"Resizable", "Boolean", false, "Is Resizable", true));
		semVertex.putSemanticAttribute("value", new ModelingAttribute("value",
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
		semVertex.addPanelSpacersAttribute("#" + "Name" + "#\n\n");

		MetaConcept concept = new MetaConcept("Concept", true, "Concept",
				"refasenumeration", "MetaConcept", 100, 150,
				"/com/variamos/gui/refas/editor/images/concept.png", true,
				Color.BLUE.toString(), 3, true, semVertex);

		variabilityInstVertex.put("Concept", new InstConcept("Concept", null,
				concept));

		SemanticConcept semEnum = new SemanticConcept();

		semEnum.putSemanticAttribute("MetaType", new ModelingAttribute(
				"MetaType", "Enumeration", false, "MetaConcept Type",
				ConceptType.class.getCanonicalName(), "MetaEnumeration"));
		semEnum.putSemanticAttribute("Identifier", new ModelingAttribute(
				"Identifier", "String", false, "Concept Identifier", ""));
		semEnum.putSemanticAttribute("Visible", new ModelingAttribute(
				"Visible", "Boolean", false, "Visible", true));
		semEnum.putSemanticAttribute("Name", new ModelingAttribute("Name",
				"String", false, "Concept Name", ""));
		semEnum.putSemanticAttribute("value", new ModelingAttribute("value",
				"Set", false, "values", ""));

		semEnum.addPropVisibleAttribute("00#" + "MetaType");
		semEnum.addPropEditableAttribute("01#" + "Identifier");
		semEnum.addPropVisibleAttribute("01#" + "Identifier");
		semEnum.addPropEditableAttribute("02#" + "Visible");
		semEnum.addPropVisibleAttribute("02#" + "Visible");
		semEnum.addPropEditableAttribute("03#" + "Name");
		semEnum.addPropVisibleAttribute("03#" + "Name");
		semEnum.addPropEditableAttribute("04#" + "value");
		semEnum.addPropVisibleAttribute("04#" + "value");

		semEnum.addPanelVisibleAttribute("01#" + "Name");
		semEnum.addPanelSpacersAttribute("#" + "Name" + "#\n\n");
		semEnum.addPanelSpacersAttribute("#" + "value" + "#\n\n");

		SemanticConcept semOverTwoRelation = new SemanticConcept(semEnum,
				"OverTwoRelation");

		semOverTwoRelation.putSemanticAttribute(
				"MetaType",
				new ModelingAttribute("MetaType", "Enumeration", false,
						"MetaConcept Type", ConceptType.class
								.getCanonicalName(), "MetaOverTwoRelation"));

		semEnum.addPropVisibleAttribute("00#" + "MetaType");
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);

		MetaConcept enumeration = new MetaConcept("Enumeration", true,
				"Enumeration", "refasenumeration", "MetaEnumeration", 100, 150,
				"/com/variamos/gui/refas/editor/images/concept.png", true,
				Color.BLUE.toString(), 3, true, semEnum);

		variabilityInstVertex.put("Enumeration", new InstConcept("Enumeration",
				null, enumeration));

		MetaConcept overTwoRelation = new MetaConcept("OverTwoRelation", true,
				"OverTwoRelation", "refasenumeration", "MetaOverTwoRelation",
				100, 150, "/com/variamos/gui/refas/editor/images/concept.png",
				true, Color.BLUE.toString(), 3, true, semOverTwoRelation);
		MetaPairwiseRelation pairwiseRelation = new MetaPairwiseRelation();

		constraintInstEdges.put("PairwiseRelation", new InstPairwiseRelation(
				pairwiseRelation));

		variabilityInstVertex.put("OverTwoRelation", new InstConcept(
				"OverTwoRelation", null, overTwoRelation));
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

		MetaPairwiseRelation metaPairwiseRelExtends = new MetaPairwiseRelation(
				"ExtendsRelation", false, "Extends Relation", "refasextends",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null, null);

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

		semGeneralElement.putSemanticAttribute("Core", new SemanticAttribute(
				"Core", "Boolean", false, "Is a Core Concept", false));

		semGeneralElement.putSemanticAttribute("Dead", new SemanticAttribute(
				"Dead", "Boolean", false, "Is a Dead Concept", false));

		semGeneralElement.putSemanticAttribute("IsRootFeature",
				new SemanticAttribute("IsRootFeature", "Boolean", true,
						"Is a Root Feature Concept", false));

		semGeneralElement.addPropEditableAttribute("04#" + "Required");
		semGeneralElement.addPropVisibleAttribute("04#" + "Required");
		semGeneralElement.addPropVisibleAttribute("05#" + "Core");
		semGeneralElement.addPropVisibleAttribute("06#" + "Dead");
		// Configuration attributes

		semGeneralElement.putSemanticAttribute("Active",
				new SimulationConfigAttribute("Active", "Boolean", true,
						"Is Active", true));
		semGeneralElement.putSemanticAttribute("Visibility",
				new SimulationConfigAttribute("Visibility", "Boolean", false,
						"Is Visible", true));

		semGeneralElement.putSemanticAttribute("Allowed",
				new SimulationConfigAttribute("Allowed", "Boolean", true,
						"Is Allowed", true));
		semGeneralElement.putSemanticAttribute("RequiredLevel",
				new SemanticAttribute("RequiredLevel", "Integer", false,
						"Required Level", 0)); // TODO define domain
												// or Enum
												// Level

		semGeneralElement.putSemanticAttribute("ConfigSelected",
				new SimulationConfigAttribute("ConfigSelected", "Boolean",
						true, "Configuration Selected", false));
		semGeneralElement.putSemanticAttribute("ConfigNotSelected",
				new SimulationConfigAttribute("ConfigNotSelected", "Boolean",
						true, "Configuration Not Selected", false));

		semGeneralElement.addPropEditableAttribute("01#" + "Active" + "#"
				+ "Core" + "#==#" + "false" + "#" + "true");
		// semGeneralElement.addDisPropEditableAttribute("02#" +
		// "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		// semGeneralElement.addPropEditableAttribute("05#" + "RequiredLevel"
		// + "#" + "Required" + "#==#" + "true" + "#" + "0");

		// semGeneralElement.addPropEditableAttribute("10#" + "ConfigSatisfied"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		// semGeneralElement.addPropEditableAttribute("11#" +
		// "ConfigNotSatisfied"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("15#" + "ConfigSelected"
				+ "#" + "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("16#" + "ConfigNotSelected"
				+ "#" + "Active" + "#==#" + "true" + "#" + "false");

		semGeneralElement.addPropVisibleAttribute("01#" + "Active");
		semGeneralElement.addPropVisibleAttribute("02#" + "Visibility");
		semGeneralElement.addPropVisibleAttribute("05#" + "RequiredLevel" + "#"
				+ "Core" + "#==#" + "true");

		/*
		 * semGeneralElement.addPropVisibleAttribute("10#" + "ConfigSatisfied");
		 * semGeneralElement.addPropVisibleAttribute("10#" +
		 * "ConfigNotSatisfied");
		 * semGeneralElement.addPropVisibleAttribute("15#" + "ConfigSelected");
		 * semGeneralElement.addPropVisibleAttribute("16#" +
		 * "ConfigNotSelected");
		 */
		// semGeneralElement.addPropVisibleAttribute("10#" + "ConfigSatisfied"
		// + "#" + "Core" + "#==#" + "false" + "#" + "true");
		// semGeneralElement.addPropVisibleAttribute("10#" +
		// "ConfigNotSatisfied"
		// + "#" + "Core" + "#==#" + "false" + "#" + "false");
		semGeneralElement.addPropVisibleAttribute("15#" + "ConfigSelected"
				+ "#" + "Core" + "#==#" + "false" + "#" + "true");
		semGeneralElement.addPropVisibleAttribute("16#" + "ConfigNotSelected"
				+ "#" + "Core" + "#==#" + "false" + "#" + "false");

		// Simulation attributes

		semGeneralElement.putSemanticAttribute("InitialRequiredLevel",
				new SimulationStateAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		semGeneralElement.putSemanticAttribute("SimRequiredLevel",
				new SimulationStateAttribute("SimRequiredLevel", "Integer",
						false, "Required Level", false));
		semGeneralElement.putSemanticAttribute("HasParent",
				new SimulationStateAttribute("HasParent", "Boolean", false,
						"Has Parent", true));

		semGeneralElement.putSemanticAttribute("Opt",
				new SimulationStateAttribute("Opt", "Integer", false,
						"FilterVariable", 0, new RangeDomain(0, 20)));

		semGeneralElement.putSemanticAttribute("Order",
				new SimulationStateAttribute("Order", "Integer", false,
						"SortVariable", 0, new RangeDomain(0, 40)));

		semGeneralElement.putSemanticAttribute("NextNotSelected",
				new SimulationStateAttribute("NextNotSelected", "Boolean",
						false, "Selection conflict", false));

		semGeneralElement.putSemanticAttribute("NextPrefSelected",
				new SimulationStateAttribute("NextPrefSelected", "Boolean",
						false, "Selected by preference", false));
		semGeneralElement.putSemanticAttribute("NextReqSelected",
				new SimulationStateAttribute("NextReqSelected", "Boolean",
						false, "Selection by restriction(s)", false));

		semGeneralElement.addPropVisibleAttribute("01#" + "Selected");
		semGeneralElement.addPropVisibleAttribute("03#" + "NextPrefSelected");
		semGeneralElement.addPropVisibleAttribute("05#" + "NextReqSelected");

		semGeneralElement.addPropVisibleAttribute("02#" + "NotAvailable");
		semGeneralElement.addPropVisibleAttribute("04#" + "NextNotSelected");
		semGeneralElement.addPropVisibleAttribute("06#" + "Order");

		SemanticConcept semHardConcept = new SemanticConcept(semGeneralElement,
				"semHardConcept");

		semHardConcept.putSemanticAttribute("satisfactionType",
				new SemanticAttribute("satisfactionType", "Enumeration", false,
						"satisfactionType",
						"com.variamos.refas.core.types.SatisfactionType",
						"achieve", ""));
		semHardConcept.addPropEditableAttribute("01#" + "satisfactionType");
		semHardConcept.addPropVisibleAttribute("01#" + "satisfactionType");

		InstVertex instVertexHC = new InstConcept("SemHardConcept",
				metaConcept, semHardConcept);
		variabilityInstVertex.put("SemHardConcept", instVertexHC);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("hctoge", instEdge);
		instEdge.setIdentifier("hctoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
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
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
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
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
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
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
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
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexOper, true);

		SoftSemanticConcept semSoftgoal = new SoftSemanticConcept(
				semGeneralElement, "SoftGoal");
		InstVertex instVertexSG = new InstConcept("SemSoftgoal", metaConcept,
				semSoftgoal);
		variabilityInstVertex.put("SemSoftgoal", instVertexSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgtoge", instEdge);
		instEdge.setIdentifier("sgtoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexSG, true);

		SemanticVariable semVariable = new SemanticVariable("Variable");
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
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		SoftSemanticConceptSatisficing semClaim = new SoftSemanticConceptSatisficing(
				semGeneralElement, "Claim", true);
		InstVertex instVertexCL = new InstConcept("SemClaim", metaConcept,
				semClaim);
		variabilityInstVertex.put("SemClaim", instVertexCL);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cltoge", instEdge);
		instEdge.setIdentifier("cltoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexCL, true);

		semClaim.putSemanticAttribute("Operationalizations",
				new SemanticAttribute("Operationalizations", "MClass", false,
						"Operationalizations",
						"com.variamos.syntaxsupport.metamodel.InstConcept",
						"SemOperationalization", "", ""));
		semClaim.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression", "String", false,
						"Conditional Expression", ""));
		semClaim.putSemanticAttribute("CompExp", new SimulationConfigAttribute(
				"CompExp", "Boolean", false, "Boolean Comp. Expression", true));
		semClaim.putSemanticAttribute("ClaimSelected",
				new SimulationConfigAttribute("ClaimSelected", "Boolean",
						false, "Claim Selected", false));

		semClaim.addPanelVisibleAttribute("01#" + "Operationalizations");
		semClaim.addPanelVisibleAttribute("03#" + "ConditionalExpression"); // TODO
																			// move
																			// to
																			// semantic
																			// attributes

		semClaim.addPropEditableAttribute("01#" + "Operationalizations");
		semClaim.addPropEditableAttribute("03#" + "ConditionalExpression");

		semClaim.addPropVisibleAttribute("01#" + "Operationalizations");
		semClaim.addPropVisibleAttribute("03#" + "ConditionalExpression");

		semClaim.addPropEditableAttribute("01#" + "CompExp");
		semClaim.addPropVisibleAttribute("01#" + "CompExp");

		semClaim.addPropVisibleAttribute("02#" + "ClaimSelected");

		semClaim.addPanelSpacersAttribute("#" + "Operationalizations" + "#\n#");

		SoftSemanticConceptSatisficing semSoftDependency = new SoftSemanticConceptSatisficing(
				semGeneralElement, "SoftDependency", false);
		InstVertex instVertexSD = new InstConcept("SemSoftDep", metaConcept,
				semSoftDependency);
		variabilityInstVertex.put("SemSoftDep", instVertexSD);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sdtoge", instEdge);
		instEdge.setIdentifier("sdtoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexSD, true);

		semSoftDependency.putSemanticAttribute("CompExp",
				new SimulationConfigAttribute("CompExp", "Boolean", false,
						"Boolean Comp. Expression", true));
		semSoftDependency.putSemanticAttribute("SDSelected",
				new SimulationConfigAttribute("SDSelected", "Boolean", false,
						"SD Selected", false));

		semSoftDependency.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression", "String", false,
						"Conditional Expression", ""));
		semSoftDependency.addPanelVisibleAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropEditableAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropVisibleAttribute("03#"
				+ "ConditionalExpression");

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

		// features relations
		List<GroupRelationType> featureMeansGroupRelation = new ArrayList<GroupRelationType>();
		featureMeansGroupRelation.add(GroupRelationType.means_ends);

		List<IntSemanticPairwiseRelType> FeatureDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		FeatureDirectRelation.add(DirectEdgeType.mandatory);
		FeatureDirectRelation.add(DirectEdgeType.optional);
		FeatureDirectRelation.add(DirectEdgeType.conflict);
		FeatureDirectRelation.add(DirectEdgeType.required);

		// goal relations
		List<GroupRelationType> alternativeGroupRelation = new ArrayList<GroupRelationType>();
		alternativeGroupRelation.add(GroupRelationType.alternative);
		/*
		 * List<GroupRelationType> altern_impl_meansGroupRelation = new
		 * ArrayList<GroupRelationType>();
		 * altern_impl_meansGroupRelation.add(GroupRelationType.alternative);
		 * altern_impl_meansGroupRelation.add(GroupRelationType.means_ends);
		 * altern_impl_meansGroupRelation.add(GroupRelationType.implication);
		 */
		List<IntSemanticPairwiseRelType> alternative_prefferedDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		alternative_prefferedDirectRelation.add(DirectEdgeType.alternative);
		alternative_prefferedDirectRelation.add(DirectEdgeType.preferred);

		List<IntSemanticPairwiseRelType> alter_preff_impl_meansDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.alternative);
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.preferred);
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.implication);
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.means_ends);

		List<IntSemanticPairwiseRelType> allSGDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		allSGDirectRelation.add(DirectEdgeType.alternative);
		allSGDirectRelation.add(DirectEdgeType.preferred);
		allSGDirectRelation.add(DirectEdgeType.implication);
		allSGDirectRelation.add(DirectEdgeType.means_ends);
		allSGDirectRelation.add(DirectEdgeType.conflict);
		allSGDirectRelation.add(DirectEdgeType.required);

		List<GroupRelationType> means_endsImplicationGroupRelation = new ArrayList<GroupRelationType>();
		means_endsImplicationGroupRelation.add(GroupRelationType.means_ends);
		means_endsImplicationGroupRelation.add(GroupRelationType.implication);

		List<GroupRelationType> implicationGroupRelation = new ArrayList<GroupRelationType>();
		implicationGroupRelation.add(GroupRelationType.implication);

		List<IntSemanticPairwiseRelType> implicationDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		implicationDirectRelation.add(DirectEdgeType.implication);

		List<IntSemanticPairwiseRelType> means_endsImplicationDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		means_endsImplicationDirectRelation.add(DirectEdgeType.means_ends);
		means_endsImplicationDirectRelation.add(DirectEdgeType.implication);

		List<IntSemanticPairwiseRelType> softdepDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		softdepDirectRelation.add(DirectEdgeType.softdependency);

		List<IntSemanticPairwiseRelType> noneDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		noneDirectRelation.add(DirectEdgeType.none);

		List<IntSemanticPairwiseRelType> claimDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		claimDirectRelation.add(DirectEdgeType.claim);

		List<GroupRelationType> implementationGroupRelation = new ArrayList<GroupRelationType>();
		implementationGroupRelation.add(GroupRelationType.implementation);

		List<IntSemanticPairwiseRelType> implementationDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		implementationDirectRelation.add(DirectEdgeType.implementation);

		// required and conflict group relations of the HardSemanticConcept
		List<GroupRelationType> requires_conflictsGroupRelation = new ArrayList<GroupRelationType>();
		requires_conflictsGroupRelation.add(GroupRelationType.required);
		requires_conflictsGroupRelation.add(GroupRelationType.conflict);

		List<IntSemanticRelationType> hardSemOverTwoRelList = new ArrayList<IntSemanticRelationType>();
		hardSemOverTwoRelList.add(new SemanticRelationType("and", "And",
				"means-ends", false, false, false, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("or", "Or",
				"conflict", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("mutex", "Mutex",
				"altern.", false, true, true, 2, -1, 1, 1));
		// hardSemOverTwoRelList.add(new SemanticRelationType("range", "Range",
		// "excludes", false, true, true, 2, -1, 1, 1));

		List<IntSemanticRelationType> featSemOverTwoRelList = new ArrayList<IntSemanticRelationType>();
		featSemOverTwoRelList.add(new SemanticRelationType("and", "And",
				"means-ends", false, false, false, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("or", "Or",
				"conflict", false, true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("mutex", "Mutex",
				"altern.", false, true, true, 2, -1, 1, 1));

		SemanticOverTwoRelation semHardOverTwoRelation = new SemanticOverTwoRelation(
				semGeneralElement, "OverTwoRelation", hardSemOverTwoRelList);

		InstVertex instVertexHHGR = new InstConcept("HardHardOverTwoRel",
				metaOverTwoRelation, semHardOverTwoRelation);
		variabilityInstVertex.put("HardHardOverTwoRel", instVertexHHGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("hctoHHGR", instEdge);
		instEdge.setIdentifier("hctoHHGR");
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexHHGR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("HHGRtohc", instEdge);
		instEdge.setIdentifier("HHGRtohc");
		instEdge.setTargetRelation(instVertexHHGR, true);
		instEdge.setSourceRelation(instVertexHC, true);

		// required and conflict direct relations of the HardSemanticConcept
		List<IntSemanticPairwiseRelType> requires_conflictsDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		requires_conflictsDirectRelation.add(DirectEdgeType.required);
		requires_conflictsDirectRelation.add(DirectEdgeType.conflict);

		List<AbstractSemanticVertex> semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semHardConcept);

		List<IntSemanticRelationType> hardSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		hardSemPairwiseRelList.add(new SemanticRelationType("means_ends",
				"means-ends", "means-ends", true, true, true, 1, -1, 1, 1));
		hardSemPairwiseRelList.add(new SemanticRelationType("conflict",
				"conflict", "conflict", false, true, true, 1, -1, 1, 1));
		hardSemPairwiseRelList.add(new SemanticRelationType("alternative",
				"altern.", "altern.", false, true, true, 1, -1, 1, 1));
		hardSemPairwiseRelList.add(new SemanticRelationType("preferred",
				"pref.", "pref.", false, true, true, 1, -1, 1, 1));
		hardSemPairwiseRelList.add(new SemanticRelationType("implication",
				"impl.", "Impl.", false, true, true, 1, -1, 1, 1));
		hardSemPairwiseRelList.add(new SemanticRelationType("required", "req.",
				"requ.", false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> sgPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		sgPairwiseRelList.add(new SemanticRelationType("Means_Ends",
				"means-ends", "means-ends", true, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("Conflict", "conflict",
				"conflict", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("Alternative",
				"altern.", "altern.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("Preferred", "pref.",
				"pref.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("Implication", "impl.",
				"Impl.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("Required", "req.",
				"requ.", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation directHardHardSemanticEdge = new SemanticPairwiseRelation(
				"HardHardDirectEdge", false, hardSemPairwiseRelList);
		semHardConcept.addDirectRelation(directHardHardSemanticEdge);
		constraintInstEdges.put("HardHardDirectEdge", new InstPairwiseRelation(
				directHardHardSemanticEdge));

		List<IntSemanticRelationType> featSideSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		featSideSemPairwiseRelList.add(new SemanticRelationType("required",
				"required", "required", false, true, true, 1, -1, 1, 1));
		featSideSemPairwiseRelList.add(new SemanticRelationType("conflict",
				"excl.", "excl.", false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> featVertSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		featVertSemPairwiseRelList.add(new SemanticRelationType("mandatory",
				"mandatory", "mandatory", true, true, true, 1, -1, 1, 1));
		featVertSemPairwiseRelList.add(new SemanticRelationType("optional",
				"opt.", "opt.", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation directFeatureFeatureVertSemanticEdge = new SemanticPairwiseRelation(
				"FeatureFeatureDirectEdge", false, featVertSemPairwiseRelList);
		semFeature.addDirectRelation(directFeatureFeatureVertSemanticEdge);
		constraintInstEdges.put("FeatureFeatureVertDirectEdge",
				new InstPairwiseRelation(directFeatureFeatureVertSemanticEdge));

		SemanticPairwiseRelation directFeatureFeatureSideSemanticEdge = new SemanticPairwiseRelation(
				"FeatureFeatureDirectEdge", false, featSideSemPairwiseRelList);
		semFeature.addDirectRelation(directFeatureFeatureSideSemanticEdge);
		constraintInstEdges.put("FeatureFeatureSideDirectEdge",
				new InstPairwiseRelation(directFeatureFeatureSideSemanticEdge));

		SemanticOverTwoRelation semFeatOverTwoRelation = new SemanticOverTwoRelation(
				semGeneralElement, "OverTwoRelation", featSemOverTwoRelList);
		InstVertex instVertexFFGR = new InstConcept("FeatureFeatureGroupRel",
				metaOverTwoRelation, semFeatOverTwoRelation);
		variabilityInstVertex.put("FeatureFeatureGroupRel", instVertexFFGR);

		List<IntSemanticRelationType> assetoperPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		assetoperPairwiseRelList.add(new SemanticRelationType("Implementation",
				"Implementation", "imp.", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semAssetOperPairwiseRel = new SemanticPairwiseRelation(
				"varAssetOperPairwiseRel", false, assetoperPairwiseRelList);
		semOperationalization.addDirectRelation(semAssetOperPairwiseRel);
		constraintInstEdges.put("varAssetOperPairwiseRel",
				new InstPairwiseRelation(semAssetOperPairwiseRel));

		List<IntSemanticRelationType> assetPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		assetPairwiseRelList.add(new SemanticRelationType("Delegation",
				"Delegation", "deleg.", true, true, true, 1, 1, 1, 1));
		assetPairwiseRelList.add(new SemanticRelationType("Assembly",
				"Assembly", "asssembly", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semAssetPairwiseRel = new SemanticPairwiseRelation(
				"varAssetPairwiseRel", false, assetPairwiseRelList);
		semOperationalization.addDirectRelation(semAssetPairwiseRel);
		constraintInstEdges.put("varAssetPairwiseRel",
				new InstPairwiseRelation(semAssetPairwiseRel));

		List<IntSemanticRelationType> vcntxPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		vcntxPairwiseRelList.add(new SemanticRelationType("Variable Context",
				"", "", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semvarcntxPairwiseRel = new SemanticPairwiseRelation(
				"varcntxPairwiseRel", false, vcntxPairwiseRelList);
		semOperationalization.addDirectRelation(semvarcntxPairwiseRel);
		constraintInstEdges.put("varcntxPairwiseRel", new InstPairwiseRelation(
				semvarcntxPairwiseRel));

		List<IntSemanticRelationType> sdPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		sdPairwiseRelList.add(new SemanticRelationType("SD", "", "", true,
				true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semSDPairwiseRel = new SemanticPairwiseRelation(
				"sdPairwiseRel", false, sdPairwiseRelList);
		semOperationalization.addDirectRelation(semSDPairwiseRel);
		constraintInstEdges.put("sdPairwiseRel", new InstPairwiseRelation(
				semSDPairwiseRel));

		List<IntSemanticRelationType> operclaimPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		operclaimPairwiseRelList.add(new SemanticRelationType("OperToClaim",
				"", "", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semOperClaimPairwiseRel = new SemanticPairwiseRelation(
				"operclaimPairwiseRel", false, operclaimPairwiseRelList);
		semOperationalization.addDirectRelation(semOperClaimPairwiseRel);
		constraintInstEdges.put("operclaimPairwiseRel",
				new InstPairwiseRelation(semOperClaimPairwiseRel));

		List<IntSemanticRelationType> claimSGPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		claimSGPairwiseRelList.add(new SemanticRelationType("ClaimToSG", "",
				"", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semClaimSGPairwiseRel = new SemanticPairwiseRelation(
				"claimSGPairwiseRel", false, claimSGPairwiseRelList);
		semClaim.addDirectRelation(semClaimSGPairwiseRel);
		constraintInstEdges.put("claimSGPairwiseRel", new InstPairwiseRelation(
				semClaimSGPairwiseRel));

		List<IntSemanticRelationType> groupPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		groupPairwiseRelList.add(new SemanticRelationType("Group", "", "",
				true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semGroupPairwiseRel = new SemanticPairwiseRelation(
				"groupPairwiseRel", false, groupPairwiseRelList);
		semHardConcept.addDirectRelation(semGroupPairwiseRel);
		constraintInstEdges.put("groupPairwiseRel", new InstPairwiseRelation(
				semGroupPairwiseRel));

		List<IntSemanticRelationType> nonePairwiseRelList = new ArrayList<IntSemanticRelationType>();
		nonePairwiseRelList.add(new SemanticRelationType("None", "", "", true,
				true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation nonePairwiseRel = new SemanticPairwiseRelation(
				"NonePairwiseRel", false, nonePairwiseRelList);
		semFeature.addDirectRelation(nonePairwiseRel);
		constraintInstEdges.put("NonePairwiseRel", new InstPairwiseRelation(
				nonePairwiseRel));

		List<IntSemanticRelationType> genconsPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		genconsPairwiseRelList.add(new SemanticRelationType(
				"GeneralConstraint", "", "", true, true, true, 1, 1, 1, 1));

		// Feature to Feature

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ftoFFGR", instEdge);
		instEdge.setIdentifier("ftoFFGR");
		instEdge.setTargetRelation(instVertexFFGR, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("FFGRtof", instEdge);
		instEdge.setIdentifier("FFGRtof");
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instVertexFFGR, true);

		// Goal to Goal

		SemanticPairwiseRelation directGoalGoalSemanticEdge = new SemanticPairwiseRelation(
				"GoalGoalDirectEdge", false, hardSemPairwiseRelList);
		semGoal.addDirectRelation(directGoalGoalSemanticEdge);
		/*
		 * variabilityInstVertex.put("GoalGoalOverTwoRel", new InstConcept(
		 * "GoalGoalOverTwoRel", metaOverTwoRelation,
		 * semanticGoalGoalGroupRelation));
		 */
		constraintInstEdges.put("GoalGoalDirectEdge", new InstPairwiseRelation(
				directGoalGoalSemanticEdge));

		// Oper to Goal and Oper

		SemanticPairwiseRelation directOperGoalSemanticEdge = new SemanticPairwiseRelation(
				"OperGoalDirectEdge", false, hardSemPairwiseRelList);
		InstConcept instOperGoal = new InstConcept("OperGoalDirectEdge",
				metaConcept, directOperGoalSemanticEdge);
		variabilityInstVertex.put("OperGoalDirectEdge", instOperGoal);

		instEdge = new InstPairwiseRelation(directOperGoalSemanticEdge);
		instEdge.setSourceRelation(instVertexOper, true);
		instEdge.setTargetRelation(instOperGoal, true);
		constraintInstEdges.put("OperGoalDirectEdge", instEdge);

		// Oper to Oper

		SemanticPairwiseRelation directOperOperSemanticEdge = new SemanticPairwiseRelation(
				"OperOperDirectEdge", false, hardSemPairwiseRelList);
		semOperationalization.addDirectRelation(directOperOperSemanticEdge);
		/*
		 * variabilityInstVertex.put("OperOperOverTwoRel", new InstConcept(
		 * "OperOperOverTwoRel", metaOverTwoRelation,
		 * semanticOperOperGroupRelation));
		 */constraintInstEdges.put("OperOperDirectEdge",
				new InstPairwiseRelation(directOperOperSemanticEdge));

		// SG to SG

		SemanticOverTwoRelation semanticSGSGGroupRelation = new SemanticOverTwoRelation(
				semGeneralElement, "SGtoSGOverTwoRel", hardSemOverTwoRelList);

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semSoftgoal);

		SemanticPairwiseRelation directSGSGSemEdge = new SemanticPairwiseRelation(
				"SGSGDirectEdge", true, sgPairwiseRelList);
		directSGSGSemEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_LEVEL, new SemanticAttribute(
						SemanticPairwiseRelation.VAR_LEVEL, "Enumeration",
						false, SemanticPairwiseRelation.VAR_LEVELNAME,
						SemanticPairwiseRelation.VAR_LEVELCLASS, "plus plus",
						""));
		directSGSGSemEdge.addPropEditableAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directSGSGSemEdge.addPropVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directSGSGSemEdge.addPanelVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);

		semSoftgoal.addDirectRelation(directSGSGSemEdge);
		InstVertex instVertexSGGR = new InstConcept("SGtoSGOverTwoRel",
				metaOverTwoRelation, semanticSGSGGroupRelation);
		variabilityInstVertex.put("SGtoSGOverTwoRel", instVertexSGGR);
		constraintInstEdges.put("SGSGDirectEdge", new InstPairwiseRelation(
				directSGSGSemEdge));

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgtoSGGR", instEdge);
		instEdge.setIdentifier("sgtoSGGR");
		instEdge.setTargetRelation(instVertexSGGR, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("SGGRtosg", instEdge);
		instEdge.setIdentifier("SGGRtosg");
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instVertexSGGR, true);

		// CV to CG
		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semContextGroup);

		SemanticPairwiseRelation directCVCGSemanticEdge = new SemanticPairwiseRelation(
				"CVCGDirectRel", false, vcntxPairwiseRelList);
		semVariable.addDirectRelation(directCVCGSemanticEdge);
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
		semOperationalization.addDirectRelation(directOperClaimSemanticEdge);
		directOperClaimSemanticEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_LEVEL, new SemanticAttribute(
						SemanticPairwiseRelation.VAR_LEVEL, "Enumeration",
						false, SemanticPairwiseRelation.VAR_LEVEL,
						SemanticPairwiseRelation.VAR_LEVELCLASS, "plus plus",
						""));
		directOperClaimSemanticEdge.addPropEditableAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directOperClaimSemanticEdge.addPropVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directOperClaimSemanticEdge.addPanelVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		semClaim.addDirectRelation(directOperClaimSemanticEdge);
		InstVertex instVertexCLGR = new InstConcept("OpertoClaimOverTwoRel",
				metaOverTwoRelation, semanticOperClaimGroupRelation);
		variabilityInstVertex.put("OpertoClaimOverTwoRel", instVertexCLGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("opertoCLGR", instEdge);
		instEdge.setIdentifier("opertoCLGR");
		instEdge.setTargetRelation(instVertexCLGR, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("CLGRtoclaim", instEdge);
		instEdge.setIdentifier("CLGRtoclaim");
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
				SemanticPairwiseRelation.VAR_LEVEL, new SemanticAttribute(
						SemanticPairwiseRelation.VAR_LEVEL, "Enumeration",
						false, SemanticPairwiseRelation.VAR_LEVEL,
						SemanticPairwiseRelation.VAR_LEVELCLASS, "plus plus",
						""));
		directClaimSGSemanticEdge.addPropEditableAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directClaimSGSemanticEdge.addPropVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directClaimSGSemanticEdge.addPanelVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		semClaim.addDirectRelation(directClaimSGSemanticEdge);
		constraintInstEdges.put("ClaimSGDirectEdge", new InstPairwiseRelation(
				directClaimSGSemanticEdge));

		// SD to SG

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semSoftgoal);

		SemanticPairwiseRelation directSDSGSemanticEdge = new SemanticPairwiseRelation(
				"SDSGDirectEdge", true, sdPairwiseRelList);
		directSDSGSemanticEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_LEVEL, new SemanticAttribute(
						SemanticPairwiseRelation.VAR_LEVEL, "Enumeration",
						false, SemanticPairwiseRelation.VAR_LEVELNAME,
						SemanticPairwiseRelation.VAR_LEVELCLASS, "plus plus",
						""));
		directSDSGSemanticEdge.addPropEditableAttribute("04#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directSDSGSemanticEdge.addPropVisibleAttribute("04#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directSDSGSemanticEdge.addPanelVisibleAttribute("04#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		semSoftDependency.addDirectRelation(directSDSGSemanticEdge);
		constraintInstEdges.put("SDSGDirectEdge", new InstPairwiseRelation(
				directSDSGSemanticEdge));

		// Asset to Oper
		// TODO use list of possible relations
		SemanticOverTwoRelation semanticAssetOperGroupRelation = new SemanticOverTwoRelation(
				semGeneralElement, "AssetOperGroupRel", hardSemOverTwoRelList);

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semOperationalization);

		SemanticPairwiseRelation directAssetOperSemanticEdge = new SemanticPairwiseRelation(
				"AssetOperDirectEdge", false, assetoperPairwiseRelList);
		semAsset.addDirectRelation(directAssetOperSemanticEdge);
		InstVertex instVertexOPERGR = new InstConcept("AssetOperGroupRel",
				metaOverTwoRelation, semanticAssetOperGroupRelation);
		variabilityInstVertex.put("AssetOperGroupRel", instVertexOPERGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("assettoCLGR", instEdge);
		instEdge.setIdentifier("assettoCLGR");
		instEdge.setTargetRelation(instVertexOPERGR, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("OPERGRtooper", instEdge);
		instEdge.setIdentifier("OPERGRtooper");
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instVertexOPERGR, true);

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

		// *************************---------------****************************
		// Goals and availability model

		syntaxMetaView = new MetaView("Variability", true,
				"Goals and Variability Model", "plnode", "Defines a feature",
				100, 80, "/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Goals and Variability Palette", 1);
		MetaConcept metaView = (MetaConcept) getSyntaxRefas().getVertex("View")
				.getEditableMetaElement();

		// MetaPairwiseRelation metaEdgeExtends = new MetaPairwiseRelation(
		// "ExtendsRelation", false, "Extends Relation", "refasextends",
		// "Extends relation", 50, 50,
		// "/com/variamos/gui/pl/editor/images/plnode.png", 1, null, null); //
		// TODO
		// // Move
		// // to
		// // upper
		// // perspective

		MetaPairwiseRelation metaPairwiseRelFromView = new MetaPairwiseRelation(
				"ViewRelation", false, "View Relation", "refasviewrel",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null, null);

		MetaPairwiseRelation metaPairwiseRelExtends = new MetaPairwiseRelation(
				"ExtendsRelation", false, "Extends Relation", "refasextends",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null, null);

		MetaPairwiseRelation metaPairwiseRelNormal = new MetaPairwiseRelation(
				"NormalRelation", false, "Normal Relation", "defaultEdge",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null, null);
		// TODO
		// Move
		// to
		// upper
		// perspective

		instView = new InstView("Variability", metaView, syntaxMetaView);
		instViews.add(instView);

		MetaConcept supportMetaElementConcept = (MetaConcept) getSyntaxRefas()
				.getVertex("Concept").getEditableMetaElement();
		MetaConcept supportMetaElementOverTwo = (MetaConcept) getSyntaxRefas()
				.getVertex("OverTwoRelation").getEditableMetaElement();
		/*
		 * MetaPairwiseRelation supportMetaPairwiseRelation =
		 * (MetaPairwiseRelation) getSyntaxRefas()
		 * .getConstraintInstEdge("PairwiseRelation") .getEditableMetaElement();
		 */
		IntSemanticConcept semFeature = (IntSemanticConcept) ((InstConcept) getSemanticRefas()
				.getVertex("SemFeature")).getEditableSemanticElement();

		MetaConcept syntaxFeature = new MetaConcept("Feature", false,
				"Feature", "plnode", "Defines a feature", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, true, semFeature);

		syntaxFeature.addModelingAttribute("name", "String", false, "Name", "");

		syntaxFeature.addPanelVisibleAttribute("03#" + "name");

		syntaxFeature.addPropEditableAttribute("03#" + "name");

		syntaxFeature.addPropVisibleAttribute("03#" + "name");

		InstVertex instVertexF = new InstConcept("Feature",
				supportMetaElementConcept, syntaxFeature);
		variabilityInstVertex.put("Feature", instVertexF);
		// syntaxMetaView.addConcept(syntaxFeature);
		// instView.addInstVertex(instVertexF);

		MetaConcept syntaxRootFeature = new MetaConcept("RootFeature", true,
				"RootFeature", "plnode", "Defines a root feature", 100, 70,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, true, semFeature);

		syntaxRootFeature.setParent(syntaxFeature);

		InstVertex instVertexRF = new InstConcept("RootFeature",
				supportMetaElementConcept, syntaxRootFeature);
		variabilityInstVertex.put("RootFeature", instVertexRF);
		syntaxMetaView.addConcept(syntaxRootFeature);
		instView.addInstVertex(instVertexRF);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-rfeat", instEdge);
		instEdge.setIdentifier("variab-rfeat");
		instEdge.setTargetRelation(instVertexRF, true);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setSourceRelation(instView, true);

		MetaConcept syntaxGeneralFeature = new MetaConcept("GeneralFeature",
				true, "GeneralFeature", "plnode", "Defines a general feature",
				100, 70, "/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, true, semFeature);

		syntaxGeneralFeature.setParent(syntaxFeature);

		InstVertex instVertexGF = new InstConcept("GeneralFeature",
				supportMetaElementConcept, syntaxGeneralFeature);
		variabilityInstVertex.put("GeneralFeature", instVertexGF);
		syntaxMetaView.addConcept(syntaxFeature);
		instView.addInstVertex(instVertexGF);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gfeat", instEdge);
		instEdge.setIdentifier("variab-gfeat");
		instEdge.setTargetRelation(instVertexGF, true);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setSourceRelation(instView, true);

		MetaConcept syntaxVertexLF = new MetaConcept("LeafFeature", true,
				"LeafFeature", "plnode", "Defines a leaf feature", 100, 70,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, true, semFeature);

		syntaxVertexLF.setParent(syntaxFeature);

		InstVertex instVertexLF = new InstConcept("LeafFeature",
				supportMetaElementConcept, syntaxVertexLF);
		variabilityInstVertex.put("LeafFeature", instVertexLF);
		syntaxMetaView.addConcept(syntaxVertexLF);
		instView.addInstVertex(instVertexLF);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-lfeat", instEdge);
		instEdge.setIdentifier("variab-lfeat");
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setSourceRelation(instView, true);

		// Feature direct relations

		// TODO delete
		List<IntSemanticPairwiseRelType> allSGDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		allSGDirectRelation.add(DirectEdgeType.alternative);
		allSGDirectRelation.add(DirectEdgeType.preferred);
		allSGDirectRelation.add(DirectEdgeType.implication);
		allSGDirectRelation.add(DirectEdgeType.means_ends);
		allSGDirectRelation.add(DirectEdgeType.conflict);
		allSGDirectRelation.add(DirectEdgeType.required);

		IntSemanticPairwiseRelation semNonePaiwiseRel = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("NonePairwiseRel")
				.getEditableSemanticElement();

		IntSemanticPairwiseRelation semGroupPaiwiseRel = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("groupPairwiseRel")
				.getEditableSemanticElement();

		MetaPairwiseRelation metaNonePairwiseRel = new MetaPairwiseRelation(
				"None Relation", true, "None Relation", "",
				"Direct relation with a over two relation concept."
						+ " No additional type defined", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				syntaxFeature, syntaxFeature, semNonePaiwiseRel);

		MetaPairwiseRelation metaGroupPairwiseRel = new MetaPairwiseRelation(
				"Group Relation", true, "Group Relation", "",
				"Direct relation with a over two relation concept."
						+ " No additional type defined", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				syntaxFeature, syntaxFeature, semGroupPaiwiseRel);

		IntSemanticPairwiseRelation directFeatureFeatureVertSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("FeatureFeatureVertDirectEdge")
				.getEditableSemanticElement();

		IntSemanticPairwiseRelation directFeatureFeatureSideSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("FeatureFeatureSideDirectEdge")
				.getEditableSemanticElement();

		MetaPairwiseRelation metaFeatVertPairwiseRel = new MetaPairwiseRelation(
				"Feature Child Relation", true, "Feature Child Relation", "",
				"Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				syntaxFeature, syntaxFeature,
				directFeatureFeatureVertSemanticEdge);

		MetaPairwiseRelation metaFeatSidePairwiseRel = new MetaPairwiseRelation(
				"Feature Side Relation", true, "Feature Side Relation", "",
				"Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				syntaxFeature, syntaxFeature,
				directFeatureFeatureSideSemanticEdge);

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
		this.constraintInstEdges.put("variab-lfeatPR", instEdge);
		instEdge.setIdentifier("variab-lfeatPR");
		instEdge.setEditableMetaElement(metaFeatVertPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGF, true);
		instEdge.setSourceRelation(instVertexLF, true);
		// Features OverTwoRelations

		IntSemanticOverTwoRelation semanticFeatureFeatureGroupRelation = (IntSemanticOverTwoRelation) ((InstConcept) this
				.getSemanticRefas().getVertex("FeatureFeatureGroupRel"))
				.getEditableSemanticElement();

		MetaOverTwoRelation featureMetaOverTwoRel = new MetaOverTwoRelation(
				"FeatOverTwoRel", true, "FeatOverTwoRel", "plgroup",
				"Group relation between"
						+ " Feature concepts. Defines different types of"
						+ " cardinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticFeatureFeatureGroupRelation);

		syntaxMetaView.addConcept(featureMetaOverTwoRel);
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

		IntSemanticConcept semHardConcept = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemHardConcept"))
				.getEditableSemanticElement();
		MetaConcept syntaxVariabilityArtifact = new MetaConcept("VA", false,
				"VariabilityArtifact", null, "", 0, 0, null, true, null, 3,
				true, semHardConcept);
		syntaxVariabilityArtifact.addModelingAttribute("name", "String", false,
				"Name", "");

		syntaxVariabilityArtifact.addPanelVisibleAttribute("03#" + "name");

		syntaxVariabilityArtifact.addPropEditableAttribute("03#" + "name");

		syntaxVariabilityArtifact.addPropVisibleAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxVariabilityArtifact);

		InstVertex instVertexVA = new InstConcept("VA",
				supportMetaElementConcept, syntaxVariabilityArtifact);
		variabilityInstVertex.put("VA", instVertexVA);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-va", instEdge);
		instEdge.setIdentifier("variab-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instView, true);

		IntSemanticConcept semGoal = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemGoal"))
				.getEditableSemanticElement();

		MetaConcept syntaxGoal = new MetaConcept("Goal", false, "Goal",
				"refasgoal", "Defines a goal of the system"
						+ " from the stakeholder perspective that can be"
						+ " satisfied with a clear cut condition", 100, 70,
				"/com/variamos/gui/refas/editor/images/goal.png", true,
				Color.BLUE.toString(), 3, true, semGoal);

		syntaxGoal.setParent(syntaxVariabilityArtifact);

		syntaxMetaView.addConcept(syntaxGoal);
		InstVertex instVertexG = new InstConcept("Goal",
				supportMetaElementConcept, syntaxGoal);
		variabilityInstVertex.put("Goal", instVertexG);
		instView.addInstVertex(instVertexG);

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

		MetaConcept syntaxTopGoal = new MetaConcept("TopGoal", true,
				"Top Goal", "refasgoal", "Defines a top goal of the system"
						+ " from the stakeholder perspective that can be"
						+ " satisfied with a clear cut condition", 120, 60,
				"/com/variamos/gui/refas/editor/images/goal.png", true,
				Color.BLUE.toString(), 3, true, semGoal);

		syntaxTopGoal.setParent(syntaxGoal);

		syntaxMetaView.addConcept(syntaxTopGoal);
		InstVertex instVertexTG = new InstConcept("TopGoal",
				supportMetaElementConcept, syntaxTopGoal);
		variabilityInstVertex.put("TopGoal", instVertexTG);
		instView.addInstVertex(instVertexTG);

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

		MetaConcept syntaxGeneralGoal = new MetaConcept("GeneralGoal", true,
				"General Goal", "refasgoal", "Defines a general goal of the"
						+ " system from the stakeholder perspective that can"
						+ " be satisfied with a clear cut condition", 120, 60,
				"/com/variamos/gui/refas/editor/images/goal.png", true,
				Color.BLUE.toString(), 2, true, semGoal);
		syntaxGeneralGoal.setParent(syntaxGoal);

		syntaxMetaView.addConcept(syntaxGeneralGoal);
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

		IntSemanticConcept semOperationalization = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemOperationalization"))
				.getEditableSemanticElement();
		MetaConcept sOperationalization = new MetaConcept("OPER", true, "OPER",
				"refasoper", "An operationalization allows"
						+ " the partial or complete satisfaction of a goal or"
						+ " another operationalization. If"
						+ " the operationalizations defined is satisfied,"
						+ " according to the defined relation, the goal"
						+ " associated will be also satisfied", 100, 60,
				"/com/variamos/gui/refas/editor/images/operational.png", true,
				Color.BLUE.toString(), 2, true, semOperationalization);
		sOperationalization.setParent(syntaxVariabilityArtifact);

		syntaxMetaView.addConcept(sOperationalization);
		InstVertex instVertexOper = new InstConcept("OPER",
				supportMetaElementConcept, sOperationalization);
		variabilityInstVertex.put("OPER", instVertexOper);
		instView.addInstVertex(instVertexOper);

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

		IntSemanticConcept semAssumption = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemAssumption"))
				.getEditableSemanticElement();

		MetaConcept syntaxAssumption = new MetaConcept("Assu", true,
				"Assumption", "refasassump", "An assumption is a"
						+ " condition that should me truth for the goal or"
						+ " operationalization to be satisfied", 100, 60,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.WHITE.toString(), 1, true, semAssumption);
		syntaxAssumption.setParent(syntaxVariabilityArtifact);

		syntaxMetaView.addConcept(syntaxAssumption);
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

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaassu", instEdge);
		instEdge.setIdentifier("variab-extvaassu");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertexAssum, true);

		// Direct Hard Relations

		IntSemanticPairwiseRelation directHardHardSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("HardHardDirectEdge")
				.getEditableSemanticElement();

		MetaPairwiseRelation metaHardPairwiseRel = new MetaPairwiseRelation(
				"HardRelation", true, "HardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxVariabilityArtifact, syntaxVariabilityArtifact,
				directHardHardSemanticEdge);

		MetaPairwiseRelation metaHardPairwiseRel2 = new MetaPairwiseRelation(
				"HardRelation2", true, "HardRelation2", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxVariabilityArtifact, syntaxVariabilityArtifact,
				directHardHardSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-vaPR", instEdge);
		instEdge.setIdentifier("variab-vaPR");
		instEdge.setEditableMetaElement(metaHardPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-vaPR2", instEdge);
		instEdge.setIdentifier("variab-vaPR2");
		instEdge.setEditableMetaElement(metaHardPairwiseRel2);
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

		IntSemanticOverTwoRelation semanticHardHardGroupRelation = (IntSemanticOverTwoRelation) ((InstConcept) this
				.getSemanticRefas().getVertex("HardHardOverTwoRel"))
				.getEditableSemanticElement();

		MetaOverTwoRelation hardMetaOverTwoRel = new MetaOverTwoRelation(
				"HardOverTwoRel", true, "HardOverTwoRel", "plgroup",
				"Group relation between"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticHardHardGroupRelation);

		syntaxMetaView.addConcept(hardMetaOverTwoRel);
		instVertex = new InstConcept("HardOverTwoRel",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		variabilityInstVertex.put("HardOverTwoRel", instVertex);
		instView.addInstVertex(instVertex);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("variab-gtoHOT", instEdge);
		 * instEdge.setIdentifier("variab-gtoHOT");
		 * instEdge.setMetaPairwiseRelation(metaHardEdge);
		 * instEdge.setTargetRelation(instVertex, true);
		 * instEdge.setSourceRelation(instVertexG, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("variab-OpertoHOT", instEdge);
		 * instEdge.setIdentifier("variab-OpertoHOT");
		 * instEdge.setMetaPairwiseRelation(metaHardEdge);
		 * instEdge.setTargetRelation(instVertex, true);
		 * instEdge.setSourceRelation(instVertexOper, true);
		 */
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-VAtoHOT", instEdge);
		instEdge.setIdentifier("variab-VAtoHOT");
		instEdge.setEditableMetaElement(metaNonePairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-HOTtoVA", instEdge);
		instEdge.setIdentifier("variab-HOTtoVA");
		instEdge.setEditableMetaElement(metaHardPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertex, true);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("variab-HOTtoG", instEdge);
		 * instEdge.setIdentifier("variab-HOTtoG");
		 * instEdge.setMetaPairwiseRelation(metaHardEdge);
		 * instEdge.setTargetRelation(instVertexG, true);
		 * instEdge.setSourceRelation(instVertex, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("variab-HOTtoOper", instEdge);
		 * instEdge.setIdentifier("variab-HOTtoOper");
		 * instEdge.setMetaPairwiseRelation(metaHardEdge);
		 * instEdge.setTargetRelation(instVertexOper, true);
		 * instEdge.setSourceRelation(instVertex, true);
		 */instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-overtwo", instEdge);
		instEdge.setIdentifier("variab-overtwo");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instView, true);

		// *************************---------------****************************
		// Softgoals model

		syntaxMetaView = new MetaView("SoftGoals", true, "Soft Goals Model",
				"plnode", "Defines sofgoals", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Soft Goals Palette", 2);
		instView = new InstView("SoftGoals", metaView, syntaxMetaView);
		instViews.add(instView);

		IntSemanticConcept semSoftgoal = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemSoftgoal"))
				.getEditableSemanticElement();
		MetaConcept syntaxAbsSoftGoal = new MetaConcept("Softgoal", false,
				"Softgoal", "", null, 0, 0, null, true, null, 3, true,
				semSoftgoal);

		syntaxAbsSoftGoal.addModelingAttribute("name", "String", false, "Name",
				"");
		syntaxAbsSoftGoal.addPanelVisibleAttribute("03#" + "name");

		syntaxAbsSoftGoal.addPropEditableAttribute("03#" + "name");
		syntaxAbsSoftGoal.addPropVisibleAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxAbsSoftGoal);
		InstVertex instVertexASG = new InstConcept("Softgoal",
				supportMetaElementConcept, syntaxAbsSoftGoal);
		variabilityInstVertex.put("Softgoal", instVertexASG);
		instView.addInstVertex(instVertexASG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-softgoal", instEdge);
		instEdge.setIdentifier("sg-softgoal");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instView, true);

		MetaConcept syntaxTopSoftGoal = new MetaConcept(
				"TopSoftgoal",
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
				"/com/variamos/gui/refas/editor/images/softgoal.png", true,
				Color.WHITE.toString(), 3, true, semSoftgoal);

		syntaxTopSoftGoal.setParent(syntaxAbsSoftGoal);

		syntaxMetaView.addConcept(syntaxTopSoftGoal);
		InstVertex instVertexTSG = new InstConcept("TopSoftgoal",
				supportMetaElementConcept, syntaxTopSoftGoal);
		variabilityInstVertex.put("TopSoftgoal", instVertexTSG);
		instView.addInstVertex(instVertexTSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-tsgasgPR", instEdge);
		instEdge.setIdentifier("variab-tsgasgPR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertexTSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-topSgoal", instEdge);
		instEdge.setIdentifier("sg-topSgoal");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexTSG, true);
		instEdge.setSourceRelation(instView, true);

		MetaConcept syntaxGeneralSoftGoal = new MetaConcept(
				"GeneralSSoftgoal",
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
				"/com/variamos/gui/refas/editor/images/softgoal.png", true,
				Color.WHITE.toString(), 1, true, semSoftgoal);

		syntaxGeneralSoftGoal.setParent(syntaxAbsSoftGoal);
		syntaxMetaView.addConcept(syntaxGeneralSoftGoal);
		InstVertex instVertexGSG = new InstConcept("GeneralSSoftgoal",
				supportMetaElementConcept, syntaxGeneralSoftGoal);
		variabilityInstVertex.put("GeneralSSoftgoal", instVertexGSG);
		instView.addInstVertex(instVertexGSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gsgasgPR", instEdge);
		instEdge.setIdentifier("variab-gsgasgPR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertexGSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-genSgoal", instEdge);
		instEdge.setIdentifier("sg-genSgoal");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexGSG, true);
		instEdge.setSourceRelation(instView, true);

		// Direct Soft relation

		IntSemanticPairwiseRelation directSGSGSemEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("SGSGDirectEdge")
				.getEditableSemanticElement();

		MetaPairwiseRelation metaSoftEdge = new MetaPairwiseRelation(
				"Soft Relation", true, "Soft Relation", "",
				"Direct relation between two soft concepts. Defines"
						+ " different types of relations and cardinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAbsSoftGoal, syntaxAbsSoftGoal, directSGSGSemEdge);
		syntaxAbsSoftGoal.addMetaPairwiseRelAsOrigin(syntaxAbsSoftGoal,
				metaSoftEdge);
		// constraintInstEdges.put("Soft Relation", new InstEdge(
		// MetaEdge, metaSoftEdge));

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-asgPR", instEdge);
		instEdge.setIdentifier("variab-asgPR");
		instEdge.setEditableMetaElement(metaSoftEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertexASG, true);

		IntSemanticOverTwoRelation semanticSGSGGroupRelation = (IntSemanticOverTwoRelation) ((InstConcept) this
				.getSemanticRefas().getVertex("SGtoSGOverTwoRel"))
				.getEditableSemanticElement();

		// Group soft relation

		hardMetaOverTwoRel = new MetaOverTwoRelation("SoftgoalOverTwoRel",
				true, "SoftgoalOverTwoRel", "plgroup",
				"Direct relation between soft"
						+ " concepts. Defines different types of relations"
						+ " and cardinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticSGSGGroupRelation);

		syntaxMetaView.addConcept(hardMetaOverTwoRel);
		instVertex = new InstConcept("SoftgoalOverTwoRel",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		variabilityInstVertex.put("SoftgoalOverTwoRel", instVertex);
		instView.addInstVertex(instVertex);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-asgtoOT", instEdge);
		instEdge.setIdentifier("sg-asgtoOT");
		instEdge.setEditableMetaElement(metaNonePairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instVertexASG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-Totoasg", instEdge);
		instEdge.setIdentifier("sg-OTtoasg");
		instEdge.setEditableMetaElement(metaSoftEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-overtwoS", instEdge);
		instEdge.setIdentifier("sg-overtwoS");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instView, true);

		// *************************---------------****************************
		// Context model

		syntaxMetaView = new MetaView("Context", true, "Context Model",
				"plnode", "Defines a feature", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Context Palette", 3);
		instView = new InstView("Context", metaView, syntaxMetaView);
		instViews.add(instView);
		// syntaxMetaView.addConcept(syntaxVariable);
		IntSemanticConcept semContextGroup = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemContextGroup"))
				.getEditableSemanticElement();
		MetaConcept syntaxContextGroup = new MetaConcept("CG", true,
				"Context Group", "refascontextgrp", " A context group"
						+ " is defined to associate variables with common"
						+ " characteristics. The type defines if variables"
						+ " are sensed or profiled, in the first case they"
						+ " are modifie by the system or environment; in the"
						+ " second case they are defined by the administrator"
						+ " or the user. The scope can be local or global,"
						+ " the first means the value is independently for"
						+ " each user while global variables are shared"
						+ " between all the users.", 150, 40,
				"/com/variamos/gui/refas/editor/images/contextgrp.png", true,
				Color.BLUE.toString(), 1, true, semContextGroup);

		syntaxMetaView.addConcept(syntaxContextGroup);
		InstVertex instVertexCG = new InstConcept("CG",
				supportMetaElementOverTwo, syntaxContextGroup);
		variabilityInstVertex.put("CG", instVertexCG);
		instView.addInstVertex(instVertexCG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-cg", instEdge);
		instEdge.setIdentifier("context-cg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instView, true);

		IntSemanticConcept semVariable = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemVariable"))
				.getEditableSemanticElement();
		MetaConcept syntaxAbsVariable = new MetaConcept("Variable", false,
				"Variable", "", null, 0, 0, null, true, null, 1, true,
				semVariable);

		InstVertex instVertexV = new InstConcept("Variable",
				supportMetaElementConcept, syntaxAbsVariable);
		variabilityInstVertex.put("Variable", instVertexV);
		instView.addInstVertex(instVertexV);

		/*
		 * instEdge = new InstEdge();
		 * this.constraintInstEdges.put("context-var", instEdge);
		 * instEdge.setIdentifier("context-var");
		 * instEdge.setTargetRelation(instVertexV, true);
		 * instEdge.setSourceRelation(instView, true);
		 */
		MetaConcept syntaxGlobalVariable = new MetaConcept(
				"GlobalVariable",
				true,
				"Global Variable",
				"refasglobcnxt",
				"A global variable"
						+ " is an abstraction of a variable or component of the"
						+ " system or the environment that are relevant the system."
						+ " The relevance applies to the system in general."
						+ " The variable values should be"
						+ " simplified as much as possible considering the modeling"
						+ " requirements", 150, 40,
				"/com/variamos/gui/refas/editor/images/globCnxtVar.png", true,
				Color.BLUE.toString(), 1, true, semVariable);

		syntaxGlobalVariable.setParent(syntaxAbsVariable);

		syntaxMetaView.addConcept(syntaxGlobalVariable);
		InstVertex instVertexGV = new InstConcept("GlobalVariable",
				supportMetaElementConcept, syntaxGlobalVariable);
		variabilityInstVertex.put("GlobalVariable", instVertexGV);
		instView.addInstVertex(instVertexGV);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-gvtoV", instEdge);
		instEdge.setIdentifier("context-gvtoV");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexV, true);
		instEdge.setSourceRelation(instVertexGV, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-gv", instEdge);
		instEdge.setIdentifier("context-gv");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexGV, true);
		instEdge.setSourceRelation(instView, true);

		MetaConcept syntaxLocalVariable = new MetaConcept(
				"LocalVariable",
				true,
				"Local Variable",
				"refaslocalcnxt",
				" A local variable"
						+ " represents an instance of a component or a variable"
						+ " with local scope. This variables may have different"
						+ " values for each user of the system. Local variables"
						+ " are used mainly for SG with local satisfaction"
						+ " evaluation", 100, 40,
				"/com/variamos/gui/refas/editor/images/localCnxtVar.png", true,
				Color.BLUE.toString(), 1, true, semVariable);

		syntaxLocalVariable.setParent(syntaxAbsVariable);

		syntaxMetaView.addConcept(syntaxLocalVariable);
		InstVertex instVertexLV = new InstConcept("LocalVariable",
				supportMetaElementConcept, syntaxLocalVariable);
		variabilityInstVertex.put("LocalVariable", instVertexLV);
		instView.addInstVertex(instVertexLV);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-lvtoV", instEdge);
		instEdge.setIdentifier("context-lvtoV");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelExtends);
		instEdge.setTargetRelation(instVertexV, true);
		instEdge.setSourceRelation(instVertexLV, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-lv", instEdge);
		instEdge.setIdentifier("context-lv");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexLV, true);
		instEdge.setSourceRelation(instView, true);

		MetaEnumeration metaEnumeration = new MetaEnumeration("ME", true,
				"MetaEnumeration", "refasenumeration", "Allows the"
						+ " creation of user defined enumerations for"
						+ " variables", 100, 150,
				"/com/variamos/gui/refas/editor/images/assump.png", true, "",
				1, true);
		MetaView syntaxMetaChildView = new MetaView("Context",
				"Context with Enumerations", "Context Palette", 0);
		InstView childView = new InstView("Context", metaView,
				syntaxMetaChildView);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		instView.addChildView(childView);
		syntaxMetaView.addConcept(metaEnumeration);
		syntaxMetaChildView.addConcept(metaEnumeration);
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
				"Context without Enumerations", "Context Palette", 1);
		childView = new InstView("ContandModelEnum", metaView,
				syntaxMetaChildView);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		instView.addChildView(childView);
		// syntaxMetaChildView.addConcept(metaEnumeration);
		syntaxMetaChildView.addConcept(syntaxContextGroup);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-withoutcg", instEdge);
		instEdge.setIdentifier("context-withoutcg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(childView, true);
		childView.addInstVertex(instVertexCG);
		syntaxMetaChildView.addConcept(syntaxLocalVariable);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-withoutlv", instEdge);
		instEdge.setIdentifier("context-withoutlv");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexLV, true);
		instEdge.setSourceRelation(childView, true);
		childView.addInstVertex(instVertexLV);
		syntaxMetaChildView.addConcept(syntaxGlobalVariable);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-withoutgv", instEdge);
		instEdge.setIdentifier("context-withoutgv");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexGV, true);
		instEdge.setSourceRelation(childView, true);
		childView.addInstVertex(instVertexGV);

		// Direct variable relations

		IntSemanticPairwiseRelation directCVCGSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("CVCGDirectRel")
				.getEditableSemanticElement();

		MetaPairwiseRelation metaVariableEdge = new MetaPairwiseRelation(
				"Variable To Context Relation", true,
				"Variable To Context Relation", "", "Associates a Variable"
						+ " with the Context Group", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAbsVariable, syntaxContextGroup, directCVCGSemanticEdge);
		syntaxAbsVariable.addMetaPairwiseRelAsOrigin(syntaxContextGroup,
				metaVariableEdge);
		// constraintInstEdges.put("Variable To Context Relation", new InstEdge(
		// MetaEdge, metaVariableEdge));

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-varCGPR", instEdge);
		instEdge.setIdentifier("variab-varCGPR");
		instEdge.setEditableMetaElement(metaVariableEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instVertexV, true);

		MetaPairwiseRelation metaContextEdge = new MetaPairwiseRelation(
				"Context To Context Relation", true,
				"Context To Context Relation", "", "Associates a"
						+ " Context Group with other Context Group", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxContextGroup, syntaxContextGroup, directCVCGSemanticEdge);
		syntaxContextGroup.addMetaPairwiseRelAsOrigin(syntaxContextGroup,
				metaContextEdge);
		// constraintInstEdges.put("Context To Context Relation", new InstEdge(
		// MetaEdge, metaVariableEdge));

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-CGCGPR", instEdge);
		instEdge.setIdentifier("variab-CGCGPR");
		instEdge.setEditableMetaElement(metaVariableEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instVertexCG, true);

		// *************************---------------****************************
		// SG Satisficing Model

		syntaxMetaView = new MetaView("SoftGoalsSatisficing", true,
				"SG Satisficing Model", "plnode", "Defines a feature", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Soft Goals Satisficing Palette", 4);
		instView = new InstView("SoftGoalsSatisficing", metaView,
				syntaxMetaView);

		instViews.add(instView);
		syntaxMetaView.addConcept(syntaxTopSoftGoal);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-tsg", instEdge);
		instEdge.setIdentifier("sgs-tsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexTSG, true);
		instEdge.setSourceRelation(instView, true);
		syntaxMetaView.addConcept(syntaxGeneralSoftGoal);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-gsg", instEdge);
		instEdge.setIdentifier("sgs-gsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexGSG, true);
		instEdge.setSourceRelation(instView, true);

		syntaxMetaView.addConcept(sOperationalization);
		instView.addInstVertex(instVertexOper);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-oper", instEdge);
		instEdge.setIdentifier("sgs-oper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instView, true);
		instView.addInstVertex(instVertexOper);

		IntSemanticConcept semClaim = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemClaim"))
				.getEditableSemanticElement();
		MetaConcept syntaxClaim = new MetaConcept("CL", true, "Claim",
				"refasclaim", "A claim includes a group of"
						+ " operationalizations and a logical condition"
						+ " to evaluate the claim satisfaction."
						+ " The claim is activated only when all the"
						+ " operationalizations are selected and the"
						+ " conditional expression is true. The claim"
						+ " includes a relation with a softgoal (SG)", 100, 50,
				"/com/variamos/gui/refas/editor/images/claim.png", true,
				Color.BLUE.toString(), 1, true, semClaim);
		syntaxMetaView.addConcept(syntaxClaim);
		InstVertex instVertexCL = new InstConcept("CL",
				supportMetaElementConcept, syntaxClaim);
		variabilityInstVertex.put("CL", instVertexCL);
		instView.addInstVertex(instVertexCL);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-claim", instEdge);
		instEdge.setIdentifier("sgs-claim");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelFromView);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instView, true);

		IntSemanticConcept semSoftDependency = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemSoftDep"))
				.getEditableSemanticElement();
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
				100, 70, "/com/variamos/gui/refas/editor/images/softdep.png",
				true, Color.BLUE.toString(), 1, true, semSoftDependency);

		syntaxMetaView.addConcept(syntaxSoftDependency);
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

		IntSemanticOverTwoRelation semanticOperClaimGroupRelation = (IntSemanticOverTwoRelation) ((InstConcept) this
				.getSemanticRefas().getVertex("OpertoClaimOverTwoRel"))
				.getEditableSemanticElement();

		hardMetaOverTwoRel = new MetaOverTwoRelation(
				"OperClaimOverTwoRel",
				true,
				"OperClaimOverTwoRel",
				"plgroup",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " expected on the softgoal in case the Claim is satisfied",
				20, 20, "/com/variamos/gui/pl/editor/images/plgroup.png",
				false, "white", 1, false, semanticOperClaimGroupRelation);

		IntSemanticPairwiseRelation semClaimPairwiseRel = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("OperClaimPairwiseRel")
				.getEditableSemanticElement();

		MetaPairwiseRelation metaClaimPairwiseRel = new MetaPairwiseRelation(
				"ClaimRelation",
				true,
				"ClaimRelation",
				"",
				"Represent the relation between"
						+ " an operationalization(s) and a claim. The operationalization(s)"
						+ " is required to satisfy a claim", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxSoftDependency, syntaxAbsSoftGoal, semClaimPairwiseRel);

		syntaxMetaView.addConcept(hardMetaOverTwoRel);
		instVertex = new InstConcept("OperClaimOverTwoRel",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
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
		this.constraintInstEdges.put("sgs-OpertoOT", instEdge);
		instEdge.setIdentifier("sgs-OpertoOT");
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setEditableMetaElement(metaNonePairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-OTtoCL", instEdge);
		instEdge.setIdentifier("sgs-OTtoCL");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-CLtoSG", instEdge);
		instEdge.setIdentifier("sgs-CLtoSG");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertexCL, true);

		IntSemanticPairwiseRelation directSDSGSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("SDSGDirectEdge")
				.getEditableSemanticElement();

		List<IntSemanticPairwiseRelation> directSDSGSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directSDSGSemanticEdges.add(directSDSGSemanticEdge);

		MetaPairwiseRelation metaSDSGEdge = new MetaPairwiseRelation(
				"SDSGRelation",
				true,
				"SDSGRelation",
				"",
				"Express the relation between"
						+ " the SD and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxSoftDependency, syntaxAbsSoftGoal, directSDSGSemanticEdge);
		syntaxSoftDependency.addMetaPairwiseRelAsOrigin(syntaxAbsSoftGoal,
				metaSDSGEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-SDASGPR", instEdge);
		instEdge.setIdentifier("variab-SDASGPR");
		instEdge.setEditableMetaElement(metaSDSGEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertexSD, true);

		// constraintInstEdges.put("SDSGRelation", new InstEdge(
		// MetaEdge, metaSDSGEdge));

		IntSemanticPairwiseRelation directClaimSGSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("ClaimSGDirectEdge")
				.getEditableSemanticElement();

		MetaPairwiseRelation metaClaimSGEdge = new MetaPairwiseRelation(
				"Claim-Softgoal Relation",
				true,
				"Claim-Softgoal Relation",
				"",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxClaim, syntaxAbsSoftGoal, directClaimSGSemanticEdge);
		syntaxClaim.addMetaPairwiseRelAsOrigin(syntaxAbsSoftGoal,
				metaClaimSGEdge);

		/*
		 * constraintInstEdges .put("Claim-Softgoal Relation", new
		 * InstPairwiseRelation( metaPairwiseRelation, metaClaimSGEdge));
		 */
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-CLASGPR", instEdge);
		instEdge.setIdentifier("variab-CLASGPR");
		instEdge.setEditableMetaElement(metaClaimSGEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertexCL, true);

		// *************************---------------****************************
		// Assets model

		syntaxMetaView = new MetaView("Assets", true, "Assets General Model",
				"plnode", "Defines an Asset", 100, 90,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Assets Palette", 5);
		instView = new InstView("Assets", metaView, syntaxMetaView);
		instViews.add(instView);
		syntaxMetaView.addConcept(sOperationalization);
		syntaxMetaView.addConcept(syntaxVertexLF);
		instView.addInstVertex(instVertexOper);
		instView.addInstVertex(instVertexLF);

		IntSemanticConcept semAsset = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemAsset"))
				.getEditableSemanticElement();
		MetaConcept syntaxAsset = new MetaConcept("Asset", true, "Asset",
				"refasasset", "Represents a asset of the system. The most"
						+ " important assets to represent are those than"
						+ " can implement operationalizations", 100, 40,
				"/com/variamos/gui/refas/editor/images/component.png", true,
				Color.WHITE.toString(), 1, true, semAsset);
		syntaxAsset.addModelingAttribute("name", "String", false, "Name", ""); // TODO
																				// move
																				// to
		// semantic
		// attributes

		syntaxMetaView.addConcept(syntaxAsset);

		syntaxAsset.addPanelVisibleAttribute("03#" + "name");
		syntaxAsset.addPropEditableAttribute("03#" + "name");
		syntaxAsset.addPropVisibleAttribute("03#" + "name");
		syntaxMetaChildView = new MetaView("Assets", "Assets General Model",
				"Assets Palette", 0);
		childView = new InstView("Assets", metaView, syntaxMetaChildView);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		instView.addChildView(childView);

		syntaxMetaView.addConcept(syntaxAsset);
		syntaxMetaChildView.addConcept(syntaxAsset);
		syntaxMetaView.addConcept(sOperationalization);
		syntaxMetaChildView.addConcept(sOperationalization);
		syntaxMetaView.addConcept(syntaxVertexLF);
		syntaxMetaChildView.addConcept(syntaxVertexLF);
		childView.addInstVertex(instVertexOper);
		childView.addInstVertex(instVertexLF);

		InstVertex instVertexAsset = new InstConcept("Asset",
				supportMetaElementConcept, syntaxAsset);
		variabilityInstVertex.put("Asset", instVertexAsset);
		instView.addInstVertex(instVertexAsset);

		IntSemanticOverTwoRelation semanticAssetOperGroupRelation = (IntSemanticOverTwoRelation) ((InstConcept) this
				.getSemanticRefas().getVertex("AssetOperGroupRel"))
				.getEditableSemanticElement();

		IntSemanticPairwiseRelation directAssetOperSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("AssetOperDirectEdge")
				.getEditableSemanticElement();

		IntSemanticPairwiseRelation directAssetSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("varAssetPairwiseRel")
				.getEditableSemanticElement();

		hardMetaOverTwoRel = new MetaOverTwoRelation("AssetOperGroupDep", true,
				"AssetOperGroupDep", "plgroup",
				"Represents the implementation "
						+ "of an operationalization by a group of assets", 20,
				20, "/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticAssetOperGroupRelation);
		syntaxMetaView.addConcept(hardMetaOverTwoRel);
		InstVertex instVertexAssetOper = new InstConcept("Asset-OperGroupDep",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		variabilityInstVertex.put("Asset-OperGroupDep", instVertexAssetOper);
		instView.addInstVertex(instVertexAssetOper);

		MetaPairwiseRelation metaOperEdge = new MetaPairwiseRelation(
				"Asset To Oper Relation", true, "Asset To Oper Relation", "",
				"Represents the "
						+ "implementation of an operationzalization by an"
						+ " asset", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAsset, sOperationalization, directAssetOperSemanticEdge);
		syntaxMetaView.addConcept(metaOperEdge);

		MetaPairwiseRelation metaFeatureEdge = new MetaPairwiseRelation(
				"Asset To Feature Relation", true, "Asset To Feature Relation",
				"", "Represents the " + "implementation of an feature by an"
						+ " asset", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAsset, syntaxVertexLF, directAssetOperSemanticEdge);
		syntaxMetaView.addConcept(metaFeatureEdge);

		MetaPairwiseRelation metaAssetEdge = new MetaPairwiseRelation(
				"Asset To Asset Relation", true, "Asset To Asset Relation", "",
				"Represents a " + "type of an operationzalization between "
						+ " assets", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAsset, syntaxAsset, directAssetSemanticEdge);
		syntaxMetaView.addConcept(metaAssetEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assettoOper", instEdge);
		instEdge.setIdentifier("asset0-assettoOper");
		instEdge.setEditableMetaElement(metaOperEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assettoFeat", instEdge);
		instEdge.setIdentifier("asset0-assettoFeat");
		instEdge.setEditableMetaElement(metaFeatureEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assettoasset", instEdge);
		instEdge.setIdentifier("asset0-assettoasset");
		instEdge.setEditableMetaElement(metaAssetEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assettoOT", instEdge);
		instEdge.setIdentifier("asset0-assettoOT");
		instEdge.setEditableMetaElement(metaNonePairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetOper, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-OTtoasset", instEdge);
		instEdge.setIdentifier("asset0-OTtoasset");
		instEdge.setEditableMetaElement(metaOperEdge);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instVertexAssetOper, true);

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
				"Functional Assets Relations", "Assets Palette", 1);
		childView = new InstView("FunctionalAssets", metaView,
				syntaxMetaChildView);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		instView.addChildView(childView);
		// syntaxMetaChildView.addConcept(sOperationalization);
		// childView.addInstVertex(instVertexOper);
		syntaxMetaChildView.addConcept(syntaxAsset);
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
				"Structural Assets Relations", "Assets Palette", 2);
		childView = new InstView("StructuralAssets", metaView,
				syntaxMetaChildView);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		instView.addChildView(childView);

		// syntaxMetaChildView.addConcept(sOperationalization);
		// childView.addInstVertex(instVertexOper);
		syntaxMetaChildView.addConcept(syntaxAsset);
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
		if (instSource == null)
			instSource = instPairwise.getSourceRelations().get(0);
		if (instTarget == null)
			instTarget = instPairwise.getTargetRelations().get(0);
		for (InstAttribute v : visible) {
			Map<String, MetaElement> mapElements = null;
			if (elm instanceof InstPairwiseRelation) {
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
			Iterator<InstElement> metaConcept = instViews.get(modelViewInd)
					.getSourceRelations().iterator();
			for (int i = 0; i < instViews.get(modelViewInd).getInstVertices()
					.size(); i++) {

				if (metaConcept.next().getIdentifier().equals(element))
					return true;
			}
		}
		if (modelViewInd < instViews.size()
				&& modelViewSubInd != -1
				&& modelViewSubInd < instViews.get(modelViewInd)
						.getChildViews().size()) {
			Iterator<InstVertex> metaElements = instViews.get(modelViewInd)
					.getChildViews().get(modelViewSubInd).getInstVertices()
					.iterator();
			while (metaElements.hasNext())
				if (metaElements.next().getIdentifier().equals(element))
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