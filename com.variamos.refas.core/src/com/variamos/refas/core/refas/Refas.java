package com.variamos.refas.core.refas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cfm.common.AbstractModel;
import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.VariabilityElement;
import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticEdge;
import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticVertex;
import com.variamos.refas.core.sematicsmetamodel.SemanticPairwiseRelation;
import com.variamos.refas.core.sematicsmetamodel.HardSemanticConcept;
import com.variamos.refas.core.sematicsmetamodel.IncomingSemanticEdge;
import com.variamos.refas.core.sematicsmetamodel.OutgoingSemanticEdge;
import com.variamos.refas.core.sematicsmetamodel.SemanticContextGroup;
import com.variamos.refas.core.sematicsmetamodel.SemanticOverTwoRelation;
import com.variamos.refas.core.sematicsmetamodel.SemanticVariable;
import com.variamos.refas.core.sematicsmetamodel.SoftSemanticConcept;
import com.variamos.refas.core.sematicsmetamodel.SoftSemanticConceptSatisficing;
import com.variamos.refas.core.types.ConceptType;
import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.refas.core.types.GroupRelationType;
import com.variamos.refas.core.types.PerspectiveType;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstEnumeration;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodel.InstView;
import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.MetaPairwiseRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaEnumeration;
import com.variamos.syntaxsupport.metamodelsupport.MetaOverTwoRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaView;
import com.variamos.syntaxsupport.metamodelsupport.ModelingAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SemanticAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SimulationConfigAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SimulationStateAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticPairwiseRelType;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticPairwiseRelation;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticOverTwoRelation;

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
	private Map<String, InstVertex> variabilityInstVertex; // TODO Move
															// variables and
															// enums to
															// otherElements
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
			classId = ((InstConcept) element).getMetaVertexIdentifier();
		else {
			if (element instanceof InstEnumeration)
				classId = ((InstEnumeration) element).getMetaVertexIdentifier();
			else
				classId = ((InstOverTwoRelation) element)
						.getMetaVertexIdentifier();
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
			classId = ((InstConcept) element).getMetaVertexIdentifier();
		else {
			if (element instanceof InstEnumeration)
				classId = ((InstEnumeration) element).getMetaVertexIdentifier();
			else
				classId = ((InstOverTwoRelation) element)
						.getMetaVertexIdentifier();
		}
		while (otherInstVertex.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextInstGroupDependencyId(InstOverTwoRelation grouDep) {

		int id = 1;
		String classId = grouDep.getMetaVertexIdentifier();

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

	public void removeElement(Object obj) {
		((InstElement)obj).clearRelations();
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

	private void createBasicSyntax() {
		AbstractSemanticVertex semView = new AbstractSemanticVertex();

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

		AbstractSemanticVertex semVertex = new AbstractSemanticVertex();

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

		AbstractSemanticVertex semEnum = new AbstractSemanticVertex();

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

		AbstractSemanticVertex semOverTwoRelation = new AbstractSemanticVertex(
				semEnum, "OverTwoRelation", true);

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

	private void createBasicSemantic() {
		AbstractSemanticVertex semVertex = new AbstractSemanticVertex();

		semVertex.putSemanticAttribute("Identifier", new ModelingAttribute(
				"Identifier", "String", false, "Concept Identifier", ""));
		semVertex.addPropEditableAttribute("01#" + "Identifier");
		semVertex.addPropVisibleAttribute("01#" + "Identifier");

		semVertex.addPanelVisibleAttribute("01#" + "Identifier");
		semVertex.addPanelSpacersAttribute("#" + "Identifier" + "#\n\n");

		MetaConcept concept = new MetaConcept("Concept", true, "Concept",
				"refasenumeration", "MetaConcept", 100, 150,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.BLUE.toString(), 3, true, semVertex);
		AbstractSemanticVertex semOverTwoRelation = new AbstractSemanticVertex(
				semVertex, "OverTwoRelation", true);

		MetaConcept enumeration = new MetaConcept("Enumeration", true,
				"Enumeration", "refasenumeration", "MetaEnumeration", 100, 150,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.BLUE.toString(), 3, true, semVertex);

		MetaConcept overTwoRelation = new MetaConcept("OverTwoRelation", true,
				"OverTwoRelation", "refasenumeration", "OverTwoRelation", 100,
				150, "/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.BLUE.toString(), 3, true, semOverTwoRelation);

		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("Concept", new InstConcept("Concept", null,
				concept));

		variabilityInstVertex.put("Enumeration", new InstConcept("Enumeration",
				null, enumeration));

		variabilityInstVertex.put("OverTwoRelation", new InstConcept(
				"OverTwoRelation", null, overTwoRelation));

		MetaPairwiseRelation pairwiseRelation = new MetaPairwiseRelation();

		constraintInstEdges.put("PairwiseRelation", new InstPairwiseRelation(
				pairwiseRelation));

	}

	public void createSemantic() {
		MetaConcept metaConcept = (MetaConcept) ((InstConcept) this
				.getSyntaxRefas().getVertex("Concept"))
				.getEditableMetaElement();
		MetaConcept metaOverTwoRelation = (MetaConcept) ((InstConcept) this
				.getSyntaxRefas().getVertex("OverTwoRelation"))
				.getEditableMetaElement();
		IncomingSemanticEdge groupRelation = null;
		AbstractSemanticVertex semGeneralElement = new AbstractSemanticVertex();
		InstVertex instVertexGE = new InstConcept("SemGeneralElement",
				metaConcept, semGeneralElement);
		variabilityInstVertex.put("SemGeneralElement", instVertexGE);

		// Design attributes

		semGeneralElement.putSemanticAttribute("Description",
				new SemanticAttribute("Description", "String", false,
						"Description", ""));
		semGeneralElement.addPropEditableAttribute("04#" + "Description");
		semGeneralElement.addPropVisibleAttribute("04#" + "Description");

		// Configuration attributes

		semGeneralElement.putSemanticAttribute("Active",
				new SimulationConfigAttribute("Active", "Boolean", true,
						"Is Active", true));
		semGeneralElement.putSemanticAttribute("Visibility",
				new SimulationConfigAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		semGeneralElement.putSemanticAttribute("Required",
				new SimulationConfigAttribute("Required", "Boolean", true,
						"Is Required", false));
		semGeneralElement.putSemanticAttribute("Allowed",
				new SimulationConfigAttribute("Allowed", "Boolean", true,
						"Is Allowed", true));
		semGeneralElement.putSemanticAttribute("RequiredLevel",
				new SimulationConfigAttribute("RequiredLevel", "Integer",
						false, "Required Level", 0)); // TODO define domain
														// or Enum
														// Level

		semGeneralElement.putSemanticAttribute("ForcedSatisfied",
				new SimulationConfigAttribute("ForcedSatisfied", "Boolean",
						false, "Force Satisfaction", false));
		semGeneralElement.putSemanticAttribute("ForcedSelected",
				new SimulationConfigAttribute("ForcedSelected", "Boolean",
						false, "Force Selection", false));

		semGeneralElement.addPropEditableAttribute("01#" + "Active");
		// semGeneralElement.addDisPropEditableAttribute("02#" +
		// "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("03#" + "Allowed" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("04#" + "Required" + "#"
				+ "Allowed" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true" + "#" + "0");
		semGeneralElement.addPropEditableAttribute("10#" + "ForcedSatisfied"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("15#" + "ForcedSelected"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");

		semGeneralElement.addPropVisibleAttribute("01#" + "Active");
		semGeneralElement.addPropVisibleAttribute("02#" + "Visibility");
		semGeneralElement.addPropVisibleAttribute("03#" + "Allowed");
		semGeneralElement.addPropVisibleAttribute("04#" + "Required");
		semGeneralElement.addPropVisibleAttribute("05#" + "RequiredLevel" + "#"
				+ "Required" + "#==#" + "true");
		semGeneralElement.addPropVisibleAttribute("10#" + "ForcedSatisfied");
		semGeneralElement.addPropVisibleAttribute("15#" + "ForcedSelected");

		// Simulation attributes

		semGeneralElement.putSemanticAttribute("InitialRequiredLevel",
				new SimulationStateAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		semGeneralElement.putSemanticAttribute("SimRequiredLevel",
				new SimulationStateAttribute("SimRequiredLevel", "Integer",
						false, "Required Level", false));
		semGeneralElement
				.putSemanticAttribute("ValidationRequiredLevel",
						new SimulationStateAttribute("ValidationRequiredLevel",
								"Integer", false,
								"Required Level by Validation", false));
		semGeneralElement.putSemanticAttribute("SimRequired",
				new SimulationStateAttribute("SimRequired", "Boolean", false,
						"***Required***", false));

		semGeneralElement.putSemanticAttribute("Satisfied",
				new SimulationStateAttribute("Satisfied", "Boolean", false,
						"***Satisfied***", false));
		semGeneralElement.putSemanticAttribute("AlternativeSatisfied",
				new SimulationStateAttribute("AlternativeSatisfied", "Boolean",
						false, "Satisfied by Alternatives", false));
		semGeneralElement.putSemanticAttribute("ValidationSatisfied",
				new SimulationStateAttribute("ValidationSatisfied", "Boolean",
						false, "Satisfied by Validation", false));
		semGeneralElement.putSemanticAttribute("SatisfiedLevel",
				new SimulationStateAttribute("SatisfiedLevel", "Integer",
						false, "Satisficing Level", false));
		semGeneralElement.putSemanticAttribute("SatisfactionConflict",
				new SimulationStateAttribute("SatisfactionConflict", "Boolean",
						false, "Satisfaction Conflict", false));

		semGeneralElement.putSemanticAttribute("Selected",
				new SimulationStateAttribute("Selected", "Boolean", false,
						"***Selected***", false));
		semGeneralElement.putSemanticAttribute("NotPrefSelected",
				new SimulationStateAttribute("NotPrefSelected", "Boolean",
						false, "Not Preferred Selected", false));
		semGeneralElement.putSemanticAttribute("ValidationSelected",
				new SimulationStateAttribute("ValidationSelected", "Boolean",
						false, "Selected by Validation", false));
		semGeneralElement.putSemanticAttribute("SolverSelected",
				new SimulationStateAttribute("SolverSelected", "Boolean",
						false, "Selected by Solver", false));

		semGeneralElement.putSemanticAttribute("Optional",
				new SimulationStateAttribute("Optional", "Boolean", false,
						"*Is Optional*", false));

		semGeneralElement.putSemanticAttribute("SimAllowed",
				new SimulationStateAttribute("SimAllowed", "Boolean", false,
						"Is Allowed", true));

		semGeneralElement.addPropVisibleAttribute("01#" + "SimRequired");
		semGeneralElement.addPropVisibleAttribute("03#" + "SimRequiredLevel");
		semGeneralElement.addPropVisibleAttribute("05#"
				+ "InitialRequiredLevel");
		semGeneralElement.addPropVisibleAttribute("07#"
				+ "ValidationRequiredLevel");

		semGeneralElement.addPropVisibleAttribute("09#" + "Selected");
		semGeneralElement.addPropVisibleAttribute("11#" + "NotPrefSelected");
		semGeneralElement.addPropVisibleAttribute("13#" + "ValidationSelected");
		semGeneralElement.addPropVisibleAttribute("15#" + "SolverSelected");

		semGeneralElement.addPropVisibleAttribute("02#" + "Satisfied");
		semGeneralElement.addPropVisibleAttribute("04#"
				+ "AlternativeSatisfied");
		semGeneralElement
				.addPropVisibleAttribute("06#" + "ValidationSatisfied");
		semGeneralElement.addPropVisibleAttribute("08#" + "SatisfiedLevel");
		semGeneralElement.addPropVisibleAttribute("10#"
				+ "SatisfactionConflict");

		semGeneralElement.addPropVisibleAttribute("12#" + "SimAllowed");

		semGeneralElement.addPropVisibleAttribute("14#" + "Optional");

		// Definition of variability concept and relations
		HardSemanticConcept semHardConcept = new HardSemanticConcept(
				semGeneralElement, "semHardConcept");
		InstVertex instVertexHC = new InstConcept("SemHardConcept",
				metaConcept, semHardConcept);
		variabilityInstVertex.put("SemHardConcept", instVertexHC);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("hctoge", instEdge);
		instEdge.setIdentifier("hctoge");
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexHC, true);

		// Direct Relations of the semanticAssumption
		/*
		 * dirRelation = new DirectRelation(semVariabilityElement,
		 * DirectRelationType.preferred);
		 * semVariabilityElement.addDirectRelation(dirRelation); dirRelation =
		 * new DirectRelation(semVariabilityElement,
		 * DirectRelationType.required);
		 * semVariabilityElement.addDirectRelation(dirRelation); dirRelation =
		 * new DirectRelation(semVariabilityElement,
		 * DirectRelationType.conflict);
		 * semVariabilityElement.addDirectRelation(dirRelation); dirRelation =
		 * new DirectRelation(semVariabilityElement,
		 * DirectRelationType.alternative);
		 * semVariabilityElement.addDirectRelation(dirRelation); dirRelation =
		 * new DirectRelation(semVariabilityElement, DirectRelationType.mutex);
		 * semVariabilityElement.addDirectRelation(dirRelation);
		 */

		List<OutgoingSemanticEdge> semHardOutgoingRelation = new ArrayList<OutgoingSemanticEdge>();
		semHardOutgoingRelation.add(new OutgoingSemanticEdge("OutHC",
				semHardConcept));

		// Feature concepts

		HardSemanticConcept semFeature = new HardSemanticConcept(
				semGeneralElement, "Feature");
		InstVertex instVertexF = new InstConcept("SemFeature", metaConcept,
				semFeature);
		variabilityInstVertex.put("SemFeature", instVertexF);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ftoge", instEdge);
		instEdge.setIdentifier("ftoge");
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexF, true);

		// definition of other concepts

		HardSemanticConcept semAssumption = new HardSemanticConcept(
				semHardConcept, "Assumption");
		InstVertex instVertexAS = new InstConcept("SemAssumption", metaConcept,
				semAssumption);
		variabilityInstVertex.put("SemAssumption", instVertexAS);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("astoge", instEdge);
		instEdge.setIdentifier("astoge");
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexAS, true);

		HardSemanticConcept semGoal = new HardSemanticConcept(semHardConcept,
				"Goal");
		semGoal.addPanelVisibleAttribute("01#" + "satisfactionType");
		semGoal.addPanelSpacersAttribute("<#" + "satisfactionType" + "#>\n");
		InstVertex instVertexG = new InstConcept("SemGoal", metaConcept,
				semGoal);
		variabilityInstVertex.put("SemGoal", instVertexG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("gtoge", instEdge);
		instEdge.setIdentifier("gtoge");
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexG, true);

		HardSemanticConcept semOperationalization = new HardSemanticConcept(
				semHardConcept, "Operationalization");
		InstVertex instVertexOper = new InstConcept("SemOperationalization",
				metaConcept, semOperationalization);
		variabilityInstVertex.put("SemOperationalization", instVertexOper);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("opertoge", instEdge);
		instEdge.setIdentifier("opertoge");
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexOper, true);

		SoftSemanticConcept semSoftgoal = new SoftSemanticConcept(
				semGeneralElement, "SoftGoal");
		InstVertex instVertexSG = new InstConcept("SemSoftgoal", metaConcept,
				semSoftgoal);
		variabilityInstVertex.put("SemSoftgoal", instVertexSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgtoge", instEdge);
		instEdge.setIdentifier("sgtoge");
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

		HardSemanticConcept semAsset = new HardSemanticConcept(
				semGeneralElement, "Asset");
		InstVertex instVertexAsset = new InstConcept("SemAsset", metaConcept,
				semAsset);
		variabilityInstVertex.put("SemAsset", instVertexAsset);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("astoge", instEdge);
		instEdge.setIdentifier("astoge");
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

		List<GroupRelationType> altern_impl_meansGroupRelation = new ArrayList<GroupRelationType>();
		altern_impl_meansGroupRelation.add(GroupRelationType.alternative);
		altern_impl_meansGroupRelation.add(GroupRelationType.means_ends);
		altern_impl_meansGroupRelation.add(GroupRelationType.implication);

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

		List<GroupRelationType> allSGGroupRelation = new ArrayList<GroupRelationType>();
		allSGGroupRelation.add(GroupRelationType.means_ends);
		allSGGroupRelation.add(GroupRelationType.implication);
		allSGGroupRelation.add(GroupRelationType.alternative);
		allSGGroupRelation.add(GroupRelationType.conflict);
		allSGGroupRelation.add(GroupRelationType.required);

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

		SemanticOverTwoRelation semanticHardHardGroupRelation = new SemanticOverTwoRelation(
				"HardHardOverTwoRel", false, requires_conflictsGroupRelation,
				semHardOutgoingRelation);
		groupRelation = new IncomingSemanticEdge("IncSemHardHardGroupRel",
				semanticHardHardGroupRelation);
		semHardConcept.addGroupRelation(groupRelation);
		InstVertex instVertexHHGR = new InstConcept("HardHardOverTwoRel",
				metaOverTwoRelation, semanticHardHardGroupRelation);
		variabilityInstVertex.put("HardHardOverTwoRel", instVertexHHGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("hctoHHGR", instEdge);
		instEdge.setIdentifier("hctoHHGR");
		instEdge.setTargetRelation(instVertexHHGR, true);
		instEdge.setSourceRelation(instVertexHC, true);

		// required and conflict direct relations of the HardSemanticConcept
		List<IntSemanticPairwiseRelType> requires_conflictsDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		requires_conflictsDirectRelation.add(DirectEdgeType.required);
		requires_conflictsDirectRelation.add(DirectEdgeType.conflict);

		List<AbstractSemanticVertex> semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semHardConcept);

		SemanticPairwiseRelation directHardHardSemanticEdge = new SemanticPairwiseRelation(
				"HardHardDirectEdge", false, requires_conflictsDirectRelation,
				semanticVertices, false);
		semHardConcept.addDirectRelation(directHardHardSemanticEdge);
		constraintInstEdges.put("HardHardDirectEdge", new InstPairwiseRelation(
				directHardHardSemanticEdge));

		// Feature to Feature

		List<OutgoingSemanticEdge> outgoingFeatureRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingFeatureRelation.add(new OutgoingSemanticEdge("OutsemFeature",
				semFeature));
		SemanticOverTwoRelation semanticFeatureFeatureGroupRelation = new SemanticOverTwoRelation(
				"FeatureFeatureGroupRel", false, featureMeansGroupRelation,
				outgoingFeatureRelation);
		groupRelation = new IncomingSemanticEdge("IncFeatureFeatureGroupRel",
				semanticFeatureFeatureGroupRelation);
		semFeature.addGroupRelation(groupRelation);

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semFeature);

		SemanticPairwiseRelation directFeatureFeatureSemanticEdge = new SemanticPairwiseRelation(
				"FeatureFeatureDirectEdge", false, false, semanticVertices,
				alter_preff_impl_meansDirectRelation);
		semGoal.addDirectRelation(directFeatureFeatureSemanticEdge);
		variabilityInstVertex.put("FeauteFeateuGroupRel", new InstConcept(
				"FeauteFeateuGroupRel", metaConcept,
				semanticFeatureFeatureGroupRelation));
		constraintInstEdges.put("FeauteFeatureDirectEdge",
				new InstPairwiseRelation(directFeatureFeatureSemanticEdge));

		// Goal to Goal

		List<OutgoingSemanticEdge> outgoingGoalRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingGoalRelation.add(new OutgoingSemanticEdge("Out3SemGoal",
				semGoal));
		SemanticOverTwoRelation semanticGoalGoalGroupRelation = new SemanticOverTwoRelation(
				"GoalGoalOverTwoRel", false, altern_impl_meansGroupRelation,
				outgoingGoalRelation);
		groupRelation = new IncomingSemanticEdge("GoalGoalOverTwoRel",
				semanticGoalGoalGroupRelation);
		semGoal.addGroupRelation(groupRelation);

		semanticVertices.add(semGoal);

		SemanticPairwiseRelation directGoalGoalSemanticEdge = new SemanticPairwiseRelation(
				"GoalGoalDirectEdge", false, false, semanticVertices,
				alter_preff_impl_meansDirectRelation);
		semGoal.addDirectRelation(directGoalGoalSemanticEdge);
		variabilityInstVertex.put("GoalGoalOverTwoRel", new InstConcept(
				"GoalGoalOverTwoRel", metaOverTwoRelation,
				semanticGoalGoalGroupRelation));
		constraintInstEdges.put("GoalGoalDirectEdge", new InstPairwiseRelation(
				directGoalGoalSemanticEdge));

		// Oper to Goal and Oper
		List<OutgoingSemanticEdge> outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingOperationalizationRelation.add(new OutgoingSemanticEdge(
				"Out2SemGoal", semGoal));
		outgoingOperationalizationRelation.add(new OutgoingSemanticEdge(
				"Out2semOper", semOperationalization));
		SemanticOverTwoRelation semanticOperGoalGroupRelation = new SemanticOverTwoRelation(
				"OperGoalOverTwoRel", false,
				means_endsImplicationGroupRelation,
				outgoingOperationalizationRelation);

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semOperationalization);
		semanticVertices.add(semGoal);

		SemanticPairwiseRelation directOperGoalSemanticEdge = new SemanticPairwiseRelation(
				"OperGoalDirectEdge", false, false, semanticVertices,
				means_endsImplicationDirectRelation);
		InstConcept instOperGoal = new InstConcept("OperGoalDirectEdge",
				metaConcept, directOperGoalSemanticEdge);
		variabilityInstVertex.put("OperGoalDirectEdge", instOperGoal);
		groupRelation = new IncomingSemanticEdge("IncSemOperGoalGroupRel",
				semanticOperGoalGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);
		semOperationalization.addDirectRelation(directOperGoalSemanticEdge);
		variabilityInstVertex.put("OperGoalOverTwoRel", new InstConcept(
				"OperGoalOverTwoRel", metaOverTwoRelation,
				semanticOperGoalGroupRelation));
		instEdge = new InstPairwiseRelation(directOperGoalSemanticEdge);
		instEdge.setSourceRelation(instVertexOper, true);
		instEdge.setTargetRelation(instOperGoal, true);
		constraintInstEdges.put("OperGoalDirectEdge", instEdge);

		// Oper to Oper
		outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingOperationalizationRelation.add(new OutgoingSemanticEdge(
				"OutsemOper", semOperationalization));
		SemanticOverTwoRelation semanticOperOperGroupRelation = new SemanticOverTwoRelation(
				"OperOperOverTwoRel", false, alternativeGroupRelation,
				outgoingOperationalizationRelation);
		groupRelation = new IncomingSemanticEdge("OperOperOverTwoRel",
				semanticOperOperGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semOperationalization);

		SemanticPairwiseRelation directOperOperSemanticEdge = new SemanticPairwiseRelation(
				"OperOperDirectEdge", false, false, semanticVertices,
				alternative_prefferedDirectRelation);
		semOperationalization.addDirectRelation(directOperOperSemanticEdge);
		variabilityInstVertex.put("OperOperOverTwoRel", new InstConcept(
				"OperOperOverTwoRel", metaOverTwoRelation,
				semanticOperOperGroupRelation));
		constraintInstEdges.put("OperOperDirectEdge", new InstPairwiseRelation(
				directOperOperSemanticEdge));

		// SG to SG
		List<OutgoingSemanticEdge> outgoingSoftgoalRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingSoftgoalRelation.add(new OutgoingSemanticEdge("OutsemSoftgoal",
				true, semSoftgoal));
		SemanticOverTwoRelation semanticSGSGGroupRelation = new SemanticOverTwoRelation(
				"SGtoSGOverTwoRel", false, allSGGroupRelation,
				outgoingSoftgoalRelation);
		groupRelation = new IncomingSemanticEdge("SGtoSGOverTwoRel",
				semanticSGSGGroupRelation);
		semSoftgoal.addGroupRelation(groupRelation);

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semSoftgoal);

		SemanticPairwiseRelation directSGSGSemEdge = new SemanticPairwiseRelation(
				"SGSGDirectEdge", true, false, semanticVertices,
				allSGDirectRelation);
		directSGSGSemEdge.putSemanticAttribute(AbstractSemanticEdge.VAR_LEVEL,
				new SemanticAttribute(AbstractSemanticEdge.VAR_LEVEL,
						"Enumeration", false,
						AbstractSemanticEdge.VAR_LEVELNAME,
						AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		directSGSGSemEdge.addPropEditableAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directSGSGSemEdge.addPropVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directSGSGSemEdge.addPanelVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		semSoftgoal.addDirectRelation(directSGSGSemEdge);
		variabilityInstVertex.put("SGtoSGOverTwoRel", new InstConcept(
				"SGtoSGOverTwoRel", metaOverTwoRelation,
				semanticSGSGGroupRelation));
		constraintInstEdges.put("SGSGDirectEdge", new InstPairwiseRelation(
				directSGSGSemEdge));

		// CV to CG
		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semContextGroup);

		SemanticPairwiseRelation directCVCGSemanticEdge = new SemanticPairwiseRelation(
				"CVCGDirectRel", false, false, semanticVertices,
				noneDirectRelation);
		semVariable.addDirectRelation(directCVCGSemanticEdge);
		constraintInstEdges.put("CVCGDirectRel", new InstPairwiseRelation(
				directCVCGSemanticEdge));

		// Oper to Claim
		outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingSoftgoalRelation.add(new OutgoingSemanticEdge("outclaim",
				semClaim));
		SemanticOverTwoRelation semanticOperClaimGroupRelation = new SemanticOverTwoRelation(
				"OpertoClaimOverTwoRel", true, implicationGroupRelation,
				outgoingSoftgoalRelation);
		groupRelation = new IncomingSemanticEdge("IncOperClaimGroupRel",
				semanticOperClaimGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semClaim);

		SemanticPairwiseRelation directOperClaimSemanticEdge = new SemanticPairwiseRelation(
				"OperClaimDirectEdge", true, true, semanticVertices,
				implicationDirectRelation);
		semOperationalization.addDirectRelation(directOperClaimSemanticEdge);
		directOperClaimSemanticEdge.putSemanticAttribute(
				AbstractSemanticEdge.VAR_LEVEL, new SemanticAttribute(
						AbstractSemanticEdge.VAR_LEVEL, "Enumeration", false,
						AbstractSemanticEdge.VAR_LEVEL,
						AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		directOperClaimSemanticEdge.addPropEditableAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directOperClaimSemanticEdge.addPropVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directOperClaimSemanticEdge.addPanelVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		semClaim.addDirectRelation(directOperClaimSemanticEdge);
		variabilityInstVertex.put("OpertoClaimOverTwoRel", new InstConcept(
				"OpertoClaimOverTwoRel", metaOverTwoRelation,
				semanticOperClaimGroupRelation));
		constraintInstEdges.put("OperClaimDirectEdge",
				new InstPairwiseRelation(directOperClaimSemanticEdge));

		// Claim to SG

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semSoftgoal);

		SemanticPairwiseRelation directClaimSGSemanticEdge = new SemanticPairwiseRelation(
				"ClaimSGDirectEdge", true, true, semanticVertices,
				claimDirectRelation);
		directClaimSGSemanticEdge.putSemanticAttribute(
				AbstractSemanticEdge.VAR_LEVEL, new SemanticAttribute(
						AbstractSemanticEdge.VAR_LEVEL, "Enumeration", false,
						AbstractSemanticEdge.VAR_LEVEL,
						AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		directClaimSGSemanticEdge.addPropEditableAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directClaimSGSemanticEdge.addPropVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directClaimSGSemanticEdge.addPanelVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		semClaim.addDirectRelation(directClaimSGSemanticEdge);
		constraintInstEdges.put("ClaimSGDirectEdge", new InstPairwiseRelation(
				directClaimSGSemanticEdge));

		// SD to SG

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semSoftgoal);

		SemanticPairwiseRelation directSDSGSemanticEdge = new SemanticPairwiseRelation(
				"SDSGDirectEdge", true, true, semanticVertices,
				softdepDirectRelation);
		directSDSGSemanticEdge.putSemanticAttribute(
				AbstractSemanticEdge.VAR_LEVEL, new SemanticAttribute(
						AbstractSemanticEdge.VAR_LEVEL, "Enumeration", false,
						AbstractSemanticEdge.VAR_LEVELNAME,
						AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		directSDSGSemanticEdge.addPropEditableAttribute("04#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directSDSGSemanticEdge.addPropVisibleAttribute("04#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directSDSGSemanticEdge.addPanelVisibleAttribute("04#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		semSoftDependency.addDirectRelation(directSDSGSemanticEdge);
		constraintInstEdges.put("SDSGDirectEdge", new InstPairwiseRelation(
				directSDSGSemanticEdge));

		// Asset to Oper
		List<OutgoingSemanticEdge> outgoingAssetRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingAssetRelation.add(new OutgoingSemanticEdge("outoper",
				semOperationalization));
		SemanticOverTwoRelation semanticAssetOperGroupRelation = new SemanticOverTwoRelation(
				"AssetOperGroupRel", false, implementationGroupRelation,
				outgoingAssetRelation);
		groupRelation = new IncomingSemanticEdge("AssetOperGroupRel",
				semanticAssetOperGroupRelation);
		semSoftgoal.addGroupRelation(groupRelation);

		semanticVertices = new ArrayList<AbstractSemanticVertex>();
		semanticVertices.add(semOperationalization);

		SemanticPairwiseRelation directAssetOperSemanticEdge = new SemanticPairwiseRelation(
				"AssetOperDirectEdge", false, true, semanticVertices,
				implementationDirectRelation);
		semAsset.addDirectRelation(directAssetOperSemanticEdge);
		variabilityInstVertex.put("AssetOperGroupRel", new InstConcept(
				"AssetOperGroupRel", metaOverTwoRelation,
				semanticAssetOperGroupRelation));
		constraintInstEdges.put("AssetOperDirectEdge",
				new InstPairwiseRelation(directAssetOperSemanticEdge));

	}

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

		MetaPairwiseRelation metaPairwiseRelView = new MetaPairwiseRelation(
				"ExtendsRelation", false, "Extends Relation", "refasviewrel",
				"Extends relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null, null); // TODO
																					// Move
																					// to
																					// upper
																					// perspective

		instView = new InstView("Variability", metaView, syntaxMetaView);
		instViews.add(instView);

		MetaConcept metaElementConcept = (MetaConcept) getSyntaxRefas()
				.getVertex("Concept").getEditableMetaElement();
		MetaConcept metaElementOverTwo = (MetaConcept) getSyntaxRefas()
				.getVertex("OverTwoRelation").getEditableMetaElement();
		MetaPairwiseRelation metaPairwiseRelation = (MetaPairwiseRelation) getSyntaxRefas()
				.getConstraintInstEdge("PairwiseRelation")
				.getEditableMetaElement();
		IntSemanticConcept semFeature = (IntSemanticConcept) ((InstConcept) getSemanticRefas()
				.getVertex("SemFeature")).getEditableSemanticElement();
		MetaConcept syntaxFeature = new MetaConcept("SemFeature", true,
				"Feature", "plnode", "Defines a feature", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, true, semFeature);
		syntaxFeature.addModelingAttribute("name", "String", false, "Name", "");

		InstVertex instVertexF = new InstConcept("Feature", metaElementConcept,
				syntaxFeature);
		variabilityInstVertex.put("Feature", instVertexF);
		syntaxMetaView.addConcept(syntaxFeature);
		instView.addInstVertex(instVertexF);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-feat", instEdge);
		instEdge.setIdentifier("variab-fea");
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setSourceRelation(instView, true);
		syntaxFeature.addPanelVisibleAttribute("03#" + "name");

		syntaxFeature.addPropEditableAttribute("03#" + "name");

		syntaxFeature.addPropVisibleAttribute("03#" + "name");

		// Feature direct relations

		// TODO delete
		List<IntSemanticPairwiseRelType> allSGDirectRelation = new ArrayList<IntSemanticPairwiseRelType>();
		allSGDirectRelation.add(DirectEdgeType.alternative);
		allSGDirectRelation.add(DirectEdgeType.preferred);
		allSGDirectRelation.add(DirectEdgeType.implication);
		allSGDirectRelation.add(DirectEdgeType.means_ends);
		allSGDirectRelation.add(DirectEdgeType.conflict);
		allSGDirectRelation.add(DirectEdgeType.required);

		IntSemanticPairwiseRelation directFeatureFeatureSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("FeauteFeatureDirectEdge")
				.getEditableSemanticElement();
		List<IntSemanticPairwiseRelation> directFeatureSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directFeatureSemanticEdges.add(directFeatureFeatureSemanticEdge);

		MetaPairwiseRelation metaFeatureEdge = new MetaPairwiseRelation(
				"Feature Relation", false, "Feature Relation", "",
				"Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				syntaxFeature, syntaxFeature, directFeatureSemanticEdges,
				allSGDirectRelation);
		syntaxFeature
				.addMetaPairwiseRelAsOrigin(syntaxFeature, metaFeatureEdge);
		// constraintInstEdges.put("Feature Relation", new InstEdge(
		// MetaEdge, metaFeatureEdge));
		syntaxMetaView.addConcept(metaFeatureEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-featPR", instEdge);
		instEdge.setIdentifier("variab-feaPR");
		instEdge.setMetaPairwiseRelation(metaFeatureEdge);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instVertexF, true);
		// Features OverTwoRelations

		List<IntSemanticOverTwoRelation> semanticFeatOverTwoRel = new ArrayList<IntSemanticOverTwoRelation>();
		// semanticFeatRelations.add(semanticFeatureFeatureGroupRelation);

		MetaOverTwoRelation featureMetaOverTwoRel = new MetaOverTwoRelation(
				"FeatOverTwoRel", true, "FeatOverTwoRel", "plgroup",
				"Group relation between"
						+ " Feature concepts. Defines different types of"
						+ " cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticFeatOverTwoRel);

		syntaxMetaView.addConcept(featureMetaOverTwoRel);
		instVertex = new InstConcept("FeatOverTwoRel", metaElementOverTwo,
				featureMetaOverTwoRel);
		variabilityInstVertex.put("FeatOverTwoRel", instVertex);
		instView.addInstVertex(instVertex);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-featGD", instEdge);
		instEdge.setIdentifier("variab-feaGD");
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-GDtoF", instEdge);
		instEdge.setIdentifier("variab-GDtoF");
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-ftoGD", instEdge);
		instEdge.setIdentifier("variab-ftoGD");
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instVertexF, true);

		featureMetaOverTwoRel.addModelingAttribute("Active",
				new SimulationConfigAttribute("Active", "Boolean", true,
						"Is Active", true));
		featureMetaOverTwoRel.addModelingAttribute("Visibility",
				new SimulationConfigAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		featureMetaOverTwoRel.addModelingAttribute("Required",
				new SimulationConfigAttribute("Required", "Boolean", true,
						"Is Required", false));
		featureMetaOverTwoRel.addModelingAttribute("Allowed",
				new SimulationConfigAttribute("Allowed", "Boolean", false,
						"Is Allowed", true));
		featureMetaOverTwoRel.addModelingAttribute("RequiredLevel",
				new SimulationConfigAttribute("RequiredLevel", "Integer",
						false, "Required Level", 0)); // TODO define domain
														// or Enum
														// Level

		featureMetaOverTwoRel.addModelingAttribute("ForcedSatisfied",
				new SimulationConfigAttribute("ForcedSatisfied", "Boolean",
						false, "Force Satisfaction", false));
		featureMetaOverTwoRel.addModelingAttribute("ForcedSelected",
				new SimulationConfigAttribute("ForcedSelected", "Boolean",
						false, "Force Selection", false));

		featureMetaOverTwoRel.addPropEditableAttribute("01#" + "Active");
		// syntaxFeatureGroupDep.addDisPropEditableAttribute("02#" +
		// "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		featureMetaOverTwoRel.addPropEditableAttribute("03#" + "Allowed" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		featureMetaOverTwoRel.addPropEditableAttribute("04#" + "Required" + "#"
				+ "Allowed" + "#==#" + "true" + "#" + "false");
		featureMetaOverTwoRel.addPropEditableAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true" + "#" + "0");
		featureMetaOverTwoRel.addPropEditableAttribute("10#"
				+ "ForcedSatisfied" + "#" + "Allowed" + "#==#" + "true" + "#"
				+ "false");
		featureMetaOverTwoRel.addPropEditableAttribute("15#" + "ForcedSelected"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");

		featureMetaOverTwoRel.addPropVisibleAttribute("01#" + "Active");
		featureMetaOverTwoRel.addPropVisibleAttribute("02#" + "Visibility");
		featureMetaOverTwoRel.addPropVisibleAttribute("03#" + "Allowed");
		featureMetaOverTwoRel.addPropVisibleAttribute("04#" + "Required");
		featureMetaOverTwoRel.addPropVisibleAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true");
		featureMetaOverTwoRel
				.addPropVisibleAttribute("10#" + "ForcedSatisfied");
		featureMetaOverTwoRel.addPropVisibleAttribute("15#" + "ForcedSelected");

		// Simulation attributes

		featureMetaOverTwoRel.addModelingAttribute("InitialRequiredLevel",
				new SimulationStateAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		featureMetaOverTwoRel.addModelingAttribute("SimRequiredLevel",
				new SimulationStateAttribute("SimRequiredLevel", "Integer",
						false, "Required Level", false));
		featureMetaOverTwoRel
				.addModelingAttribute("ValidationRequiredLevel",
						new SimulationStateAttribute("ValidationRequiredLevel",
								"Integer", false,
								"Required Level by Validation", false));
		featureMetaOverTwoRel.addModelingAttribute("SimRequired",
				new SimulationStateAttribute("SimRequired", "Boolean", false,
						"Required", false));

		featureMetaOverTwoRel.addModelingAttribute("Satisfied",
				new SimulationStateAttribute("Satisfied", "Boolean", false,
						"Satisfied", false));
		featureMetaOverTwoRel.addModelingAttribute("AlternativeSatisfied",
				new SimulationStateAttribute("AlternativeSatisfied", "Boolean",
						false, "Satisfied by Alternatives", false));
		featureMetaOverTwoRel.addModelingAttribute("ValidationSatisfied",
				new SimulationStateAttribute("ValidationSatisfied", "Boolean",
						false, "Satisfied by Validation", false));
		featureMetaOverTwoRel.addModelingAttribute("SatisfiedLevel",
				new SimulationStateAttribute("SatisfiedLevel", "Integer",
						false, "Satisficing Level", false));
		featureMetaOverTwoRel.addModelingAttribute("SatisfactionConflict",
				new SimulationStateAttribute("SatisfactionConflict", "Boolean",
						false, "Satisfaction Conflict", false));

		featureMetaOverTwoRel.addModelingAttribute("Selected",
				new SimulationStateAttribute("Selected", "Boolean", false,
						"Selected", false));
		featureMetaOverTwoRel.addModelingAttribute("NotPrefSelected",
				new SimulationStateAttribute("NotPrefSelected", "Boolean",
						false, "Not Preferred Selected", false));
		featureMetaOverTwoRel.addModelingAttribute("ValidationSelected",
				new SimulationStateAttribute("ValidationSelected", "Boolean",
						false, "Selected by Validation", false));
		featureMetaOverTwoRel.addModelingAttribute("SolverSelected",
				new SimulationStateAttribute("SolverSelected", "Boolean",
						false, "Selected by Solver", false));

		featureMetaOverTwoRel.addModelingAttribute("Optional",
				new SimulationStateAttribute("Optional", "Boolean", false,
						"Is Optional", false));

		featureMetaOverTwoRel.addModelingAttribute("SimAllowed",
				new SimulationStateAttribute("SimAllowed", "Boolean", false,
						"Is Allowed", true));

		featureMetaOverTwoRel.addPropVisibleAttribute("01#" + "SimRequired");
		featureMetaOverTwoRel.addPropVisibleAttribute("03#"
				+ "SimRequiredLevel");
		featureMetaOverTwoRel.addPropVisibleAttribute("05#"
				+ "InitialRequiredLevel");
		featureMetaOverTwoRel.addPropVisibleAttribute("07#"
				+ "ValidationRequiredLevel");
		featureMetaOverTwoRel.addPropVisibleAttribute("09#" + "Selected");
		featureMetaOverTwoRel
				.addPropVisibleAttribute("11#" + "NotPrefSelected");
		featureMetaOverTwoRel.addPropVisibleAttribute("13#"
				+ "ValidationSelected");
		featureMetaOverTwoRel.addPropVisibleAttribute("15#" + "SolverSelected");

		featureMetaOverTwoRel.addPropVisibleAttribute("02#" + "Satisfied");
		featureMetaOverTwoRel.addPropVisibleAttribute("04#"
				+ "AlternativeSatisfied");
		featureMetaOverTwoRel.addPropVisibleAttribute("06#"
				+ "ValidationSatisfied");
		featureMetaOverTwoRel.addPropVisibleAttribute("08#" + "SatisfiedLevel");
		featureMetaOverTwoRel.addPropVisibleAttribute("10#"
				+ "SatisfactionConflict");

		featureMetaOverTwoRel.addPropVisibleAttribute("12#" + "SimAllowed");

		featureMetaOverTwoRel.addPropVisibleAttribute("14#" + "Optional");

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

		InstVertex instVertexVA = new InstConcept("VA", metaElementConcept,
				syntaxVariabilityArtifact);
		variabilityInstVertex.put("VA", instVertexVA);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-va", instEdge);
		instEdge.setIdentifier("variab-va");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
		InstVertex instVertexG = new InstConcept("Goal", metaElementConcept,
				syntaxGoal);
		variabilityInstVertex.put("Goal", instVertexG);
		instView.addInstVertex(instVertexG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvatg", instEdge);
		instEdge.setIdentifier("variab-extvatg");
		// instEdge.setMetaPairwiseRelation(metaEdgeExtends);
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
				metaElementConcept, syntaxTopGoal);
		variabilityInstVertex.put("TopGoal", instVertexTG);
		instView.addInstVertex(instVertexTG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extgtg", instEdge);
		instEdge.setIdentifier("variab-extgtg");
		// instEdge.setMetaPairwiseRelation(metaEdgeExtends);
		instEdge.setTargetRelation(instVertexG, true);
		instEdge.setSourceRelation(instVertexTG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-topgoal", instEdge);
		instEdge.setIdentifier("variab-topgoal");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
				metaElementConcept, syntaxGeneralGoal);
		variabilityInstVertex.put("GeneralGoal", instVertexGG);
		instView.addInstVertex(instVertexGG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaggg", instEdge);
		instEdge.setIdentifier("variab-extvaggg");
		// instEdge.setMetaPairwiseRelation(metaEdgeExtends);
		instEdge.setTargetRelation(instVertexG, true);
		instEdge.setSourceRelation(instVertexGG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gengoal", instEdge);
		instEdge.setIdentifier("variab-gengoal");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexGG, true);
		instEdge.setSourceRelation(instView, true);

		IntSemanticConcept semOperationalization = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemOperationalization"))
				.getEditableSemanticElement();
		MetaConcept sOperationalization = new MetaConcept("OPER", true,
				"Operationalization", "refasoper",
				"An operationalization allows"
						+ " the partial or complete satisfaction of a goal or"
						+ " another operationalization. If"
						+ " the operationalizations defined is satisfied,"
						+ " according to the defined relation, the goal"
						+ " associated will be also satisfied", 100, 60,
				"/com/variamos/gui/refas/editor/images/operational.png", true,
				Color.BLUE.toString(), 2, true, semOperationalization);
		sOperationalization.setParent(syntaxVariabilityArtifact);

		syntaxMetaView.addConcept(sOperationalization);
		InstVertex instVertexOper = new InstConcept("OPER", metaElementConcept,
				sOperationalization);
		variabilityInstVertex.put("OPER", instVertexOper);
		instView.addInstVertex(instVertexOper);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaoper", instEdge);
		instEdge.setIdentifier("variab-extvaoper");
		// instEdge.setMetaPairwiseRelation(metaEdgeExtends);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-oper", instEdge);
		instEdge.setIdentifier("variab-oper");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
				metaElementConcept, syntaxAssumption);
		variabilityInstVertex.put("Assu", instVertexAssum);
		instView.addInstVertex(instVertexAssum);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-assum", instEdge);
		instEdge.setIdentifier("variab-assum");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexAssum, true);
		instEdge.setSourceRelation(instView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaassu", instEdge);
		instEdge.setIdentifier("variab-extvaassu");
		// instEdge.setMetaPairwiseRelation(metaEdgeExtends);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertexAssum, true);

		// Direct Hard Relations

		List<IntSemanticPairwiseRelation> directHardSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		IntSemanticPairwiseRelation directHardHardSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("HardHardDirectEdge")
				.getEditableSemanticElement();
		IntSemanticPairwiseRelation directGoalGoalSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("GoalGoalDirectEdge")
				.getEditableSemanticElement();
		IntSemanticPairwiseRelation directOperGoalSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("OperGoalDirectEdge")
				.getEditableSemanticElement();
		IntSemanticPairwiseRelation directOperOperSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("OperOperDirectEdge")
				.getEditableSemanticElement();

		directHardSemanticEdges.add(directHardHardSemanticEdge);
		directHardSemanticEdges.add(directGoalGoalSemanticEdge);
		directHardSemanticEdges.add(directOperGoalSemanticEdge);
		directHardSemanticEdges.add(directOperOperSemanticEdge);

		MetaPairwiseRelation metaHardEdge = new MetaPairwiseRelation(
				"HardRelation", true, "HardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cartinalities", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxVariabilityArtifact, syntaxVariabilityArtifact,
				directHardSemanticEdges, allSGDirectRelation);
		syntaxVariabilityArtifact.addMetaPairwiseRelAsOrigin(
				syntaxVariabilityArtifact, metaHardEdge);
		// constraintInstEdges.put("HardRelation", new InstEdge(
		// MetaEdge, metaHardEdge));

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-vaPR", instEdge);
		instEdge.setIdentifier("variab-vaPR");
		instEdge.setMetaPairwiseRelation(metaHardEdge);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertexVA, true);

		// Hard OverTwoRelations

		IntSemanticOverTwoRelation semanticGoalGoalGroupRelation = (IntSemanticOverTwoRelation) ((InstConcept) this
				.getSemanticRefas().getVertex("GoalGoalOverTwoRel"))
				.getEditableSemanticElement();
		IntSemanticOverTwoRelation semanticOperGoalGroupRelation = (IntSemanticOverTwoRelation) ((InstConcept) this
				.getSemanticRefas().getVertex("OperGoalOverTwoRel"))
				.getEditableSemanticElement();
		IntSemanticOverTwoRelation semanticOperOperGroupRelation = (IntSemanticOverTwoRelation) ((InstConcept) this
				.getSemanticRefas().getVertex("OperOperOverTwoRel"))
				.getEditableSemanticElement();
		IntSemanticOverTwoRelation semanticHardHardGroupRelation = (IntSemanticOverTwoRelation) ((InstConcept) this
				.getSemanticRefas().getVertex("HardHardOverTwoRel"))
				.getEditableSemanticElement();

		List<IntSemanticOverTwoRelation> semanticRelations = new ArrayList<IntSemanticOverTwoRelation>();
		semanticRelations.add(semanticGoalGoalGroupRelation);
		semanticRelations.add(semanticOperGoalGroupRelation);
		semanticRelations.add(semanticOperOperGroupRelation);
		semanticRelations.add(semanticHardHardGroupRelation);

		MetaOverTwoRelation hardMetaOverTwoRel = new MetaOverTwoRelation(
				"HardOverTwoRel", true, "HardOverTwoRel", "plgroup",
				"Group relation between"
						+ " hard concepts. Defines different types of"
						+ " relations and cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);

		syntaxMetaView.addConcept(hardMetaOverTwoRel);
		instVertex = new InstConcept("HardOverTwoRel", metaElementOverTwo,
				hardMetaOverTwoRel);
		variabilityInstVertex.put("HardOverTwoRel", instVertex);
		instView.addInstVertex(instVertex);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gtoHOT", instEdge);
		instEdge.setIdentifier("variab-gtoHOT");
		instEdge.setMetaPairwiseRelation(metaHardEdge);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instVertexG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-OpertoHOT", instEdge);
		instEdge.setIdentifier("variab-OpertoHOT");
		instEdge.setMetaPairwiseRelation(metaHardEdge);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-VAtoHOT", instEdge);
		instEdge.setIdentifier("variab-VAtoHOT");
		instEdge.setMetaPairwiseRelation(metaHardEdge);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-HOTtoVA", instEdge);
		instEdge.setIdentifier("variab-HOTtoVA");
		instEdge.setMetaPairwiseRelation(metaHardEdge);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-HOTtoG", instEdge);
		instEdge.setIdentifier("variab-HOTtoG");
		instEdge.setMetaPairwiseRelation(metaHardEdge);
		instEdge.setTargetRelation(instVertexG, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-HOTtoOper", instEdge);
		instEdge.setIdentifier("variab-HOTtoOper");
		instEdge.setMetaPairwiseRelation(metaHardEdge);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-overtwo", instEdge);
		instEdge.setIdentifier("variab-overtwo");
		instEdge.setMetaPairwiseRelation(metaHardEdge);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instView, true);

		hardMetaOverTwoRel.addModelingAttribute("Active",
				new SimulationConfigAttribute("Active", "Boolean", true,
						"Is Active", true));
		hardMetaOverTwoRel.addModelingAttribute("Visibility",
				new SimulationConfigAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		hardMetaOverTwoRel.addModelingAttribute("Required",
				new SimulationConfigAttribute("Required", "Boolean", true,
						"Is Required", false));
		hardMetaOverTwoRel.addModelingAttribute("Allowed",
				new SimulationConfigAttribute("Allowed", "Boolean", false,
						"Is Allowed", true));
		hardMetaOverTwoRel.addModelingAttribute("RequiredLevel",
				new SimulationConfigAttribute("RequiredLevel", "Integer",
						false, "Required Level", 0)); // TODO define domain
														// or Enum
														// Level
		hardMetaOverTwoRel.addModelingAttribute("ForcedSatisfied",
				new SimulationConfigAttribute("ForcedSatisfied", "Boolean",
						false, "Force Satisfaction", false));
		hardMetaOverTwoRel.addModelingAttribute("ForcedSelected",
				new SimulationConfigAttribute("ForcedSelected", "Boolean",
						false, "Force Selection", false));

		hardMetaOverTwoRel.addPropEditableAttribute("01#" + "Active");
		// syntaxGroupDependency.addDisPropEditableAttribute("02#" +
		// "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		hardMetaOverTwoRel.addPropEditableAttribute("03#" + "Allowed" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		hardMetaOverTwoRel.addPropEditableAttribute("04#" + "Required" + "#"
				+ "Allowed" + "#==#" + "true" + "#" + "false");
		hardMetaOverTwoRel.addPropEditableAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true" + "#" + "0");
		hardMetaOverTwoRel.addPropEditableAttribute("10#" + "ForcedSatisfied"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");
		hardMetaOverTwoRel.addPropEditableAttribute("15#" + "ForcedSelected"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");

		hardMetaOverTwoRel.addPropVisibleAttribute("01#" + "Active");
		hardMetaOverTwoRel.addPropVisibleAttribute("02#" + "Visibility");
		hardMetaOverTwoRel.addPropVisibleAttribute("03#" + "Allowed");
		hardMetaOverTwoRel.addPropVisibleAttribute("04#" + "Required");
		hardMetaOverTwoRel.addPropVisibleAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true");
		hardMetaOverTwoRel.addPropVisibleAttribute("10#" + "ForcedSatisfied");
		hardMetaOverTwoRel.addPropVisibleAttribute("15#" + "ForcedSelected");

		// Simulation attributes

		hardMetaOverTwoRel.addModelingAttribute("InitialRequiredLevel",
				new SimulationStateAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		hardMetaOverTwoRel.addModelingAttribute("SimRequiredLevel",
				new SimulationStateAttribute("SimRequiredLevel", "Integer",
						false, "Required Level", false));
		hardMetaOverTwoRel
				.addModelingAttribute("ValidationRequiredLevel",
						new SimulationStateAttribute("ValidationRequiredLevel",
								"Integer", false,
								"Required Level by Validation", false));
		hardMetaOverTwoRel.addModelingAttribute("SimRequired",
				new SimulationStateAttribute("SimRequired", "Boolean", false,
						"***Required***", false));

		hardMetaOverTwoRel.addModelingAttribute("Satisfied",
				new SimulationStateAttribute("Satisfied", "Boolean", false,
						"***Satisfied***", false));
		hardMetaOverTwoRel.addModelingAttribute("AlternativeSatisfied",
				new SimulationStateAttribute("AlternativeSatisfied", "Boolean",
						false, "Satisfied by Alternatives", false));
		hardMetaOverTwoRel.addModelingAttribute("ValidationSatisfied",
				new SimulationStateAttribute("ValidationSatisfied", "Boolean",
						false, "Satisfied by Validation", false));
		hardMetaOverTwoRel.addModelingAttribute("SatisfiedLevel",
				new SimulationStateAttribute("SatisfiedLevel", "Integer",
						false, "Satisficing Level", false));
		hardMetaOverTwoRel.addModelingAttribute("SatisfactionConflict",
				new SimulationStateAttribute("SatisfactionConflict", "Boolean",
						false, "Satisfaction Conflict", false));

		hardMetaOverTwoRel.addModelingAttribute("Selected",
				new SimulationStateAttribute("Selected", "Boolean", false,
						"***Selected***", false));
		hardMetaOverTwoRel.addModelingAttribute("NotPrefSelected",
				new SimulationStateAttribute("NotPrefSelected", "Boolean",
						false, "Not Preferred Selected", false));
		hardMetaOverTwoRel.addModelingAttribute("ValidationSelected",
				new SimulationStateAttribute("ValidationSelected", "Boolean",
						false, "Selected by Validation", false));
		hardMetaOverTwoRel.addModelingAttribute("SolverSelected",
				new SimulationStateAttribute("SolverSelected", "Boolean",
						false, "Selected by Solver", false));

		hardMetaOverTwoRel.addModelingAttribute("Optional",
				new SimulationStateAttribute("Optional", "Boolean", false,
						"*Is Optional*", false));

		hardMetaOverTwoRel.addModelingAttribute("SimAllowed",
				new SimulationStateAttribute("SimAllowed", "Boolean", false,
						"Is Allowed", true));

		hardMetaOverTwoRel.addPropVisibleAttribute("01#" + "SimRequired");
		hardMetaOverTwoRel.addPropVisibleAttribute("03#" + "SimRequiredLevel");
		hardMetaOverTwoRel.addPropVisibleAttribute("05#"
				+ "InitialRequiredLevel");
		hardMetaOverTwoRel.addPropVisibleAttribute("07#"
				+ "ValidationRequiredLevel");
		hardMetaOverTwoRel.addPropVisibleAttribute("09#" + "Selected");
		hardMetaOverTwoRel.addPropVisibleAttribute("11#" + "NotPrefSelected");
		hardMetaOverTwoRel
				.addPropVisibleAttribute("13#" + "ValidationSelected");
		hardMetaOverTwoRel.addPropVisibleAttribute("15#" + "SolverSelected");

		hardMetaOverTwoRel.addPropVisibleAttribute("02#" + "Satisfied");
		hardMetaOverTwoRel.addPropVisibleAttribute("04#"
				+ "AlternativeSatisfied");
		hardMetaOverTwoRel.addPropVisibleAttribute("06#"
				+ "ValidationSatisfied");
		hardMetaOverTwoRel.addPropVisibleAttribute("08#" + "SatisfiedLevel");
		hardMetaOverTwoRel.addPropVisibleAttribute("10#"
				+ "SatisfactionConflict");

		hardMetaOverTwoRel.addPropVisibleAttribute("12#" + "SimAllowed");

		hardMetaOverTwoRel.addPropVisibleAttribute("14#" + "Optional");

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
				metaElementConcept, syntaxAbsSoftGoal);
		variabilityInstVertex.put("Softgoal", instVertexASG);
		instView.addInstVertex(instVertexASG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-softgoal", instEdge);
		instEdge.setIdentifier("sg-softgoal");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
				metaElementConcept, syntaxTopSoftGoal);
		variabilityInstVertex.put("TopSoftgoal", instVertexTSG);
		instView.addInstVertex(instVertexTSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-tsgasgPR", instEdge);
		instEdge.setIdentifier("variab-tsgasgPR");
		// instEdge.setMetaPairwiseRelation(metaEdgeExtends);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertexTSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-topSgoal", instEdge);
		instEdge.setIdentifier("sg-topSgoal");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
				metaElementConcept, syntaxGeneralSoftGoal);
		variabilityInstVertex.put("GeneralSSoftgoal", instVertexGSG);
		instView.addInstVertex(instVertexGSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gsgasgPR", instEdge);
		instEdge.setIdentifier("variab-gsgasgPR");
		// instEdge.setMetaPairwiseRelation(metaEdgeExtends);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertexGSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-genSgoal", instEdge);
		instEdge.setIdentifier("sg-genSgoal");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexGSG, true);
		instEdge.setSourceRelation(instView, true);

		// Direct Soft relation

		IntSemanticPairwiseRelation directSGSGSemEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("SGSGDirectEdge")
				.getEditableSemanticElement();

		List<IntSemanticPairwiseRelation> directSoftSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();

		directSoftSemanticEdges.add(directSGSGSemEdge);

		MetaPairwiseRelation metaSoftEdge = new MetaPairwiseRelation(
				"Soft Relation", true, "Soft Relation", "",
				"Direct relation between two soft concepts. Defines"
						+ " different types of relations and cartinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAbsSoftGoal, syntaxAbsSoftGoal, directSoftSemanticEdges,
				directSGSGSemEdge.getSemanticRelationTypes());
		syntaxAbsSoftGoal.addMetaPairwiseRelAsOrigin(syntaxAbsSoftGoal,
				metaSoftEdge);
		// constraintInstEdges.put("Soft Relation", new InstEdge(
		// MetaEdge, metaSoftEdge));

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-asgPR", instEdge);
		instEdge.setIdentifier("variab-asgPR");
		instEdge.setMetaPairwiseRelation(metaSoftEdge);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertexASG, true);

		IntSemanticOverTwoRelation semanticSGSGGroupRelation = (IntSemanticOverTwoRelation) ((InstConcept) this
				.getSemanticRefas().getVertex("SGtoSGOverTwoRel"))
				.getEditableSemanticElement();

		semanticRelations = new ArrayList<IntSemanticOverTwoRelation>();
		semanticRelations.add(semanticSGSGGroupRelation);

		// Group soft relation

		hardMetaOverTwoRel = new MetaOverTwoRelation("SoftgoalOverTwoRel",
				true, "SoftgoalOverTwoRel", "plgroup",
				"Direct relation between soft"
						+ " concepts. Defines different types of relations"
						+ " and cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);

		syntaxMetaView.addConcept(hardMetaOverTwoRel);
		instVertex = new InstConcept("SoftgoalOverTwoRel", metaElementOverTwo,
				hardMetaOverTwoRel);
		variabilityInstVertex.put("SoftgoalOverTwoRel", instVertex);
		instView.addInstVertex(instVertex);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-asgtoOT", instEdge);
		instEdge.setIdentifier("sg-asgtoOT");
		instEdge.setMetaPairwiseRelation(metaSoftEdge);
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instVertexASG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-Totoasg", instEdge);
		instEdge.setIdentifier("sg-OTtoasg");
		instEdge.setMetaPairwiseRelation(metaSoftEdge);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-overtwoS", instEdge);
		instEdge.setIdentifier("sg-overtwoS");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
		InstVertex instVertexCG = new InstConcept("CG", metaElementOverTwo,
				syntaxContextGroup);
		variabilityInstVertex.put("CG", instVertexCG);
		instView.addInstVertex(instVertexCG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-cg", instEdge);
		instEdge.setIdentifier("context-cg");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instView, true);

		IntSemanticConcept semVariable = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemVariable"))
				.getEditableSemanticElement();
		MetaConcept syntaxAbsVariable = new MetaConcept("Variable", false,
				"Variable", "", null, 0, 0, null, true, null, 1, true,
				semVariable);

		InstVertex instVertexV = new InstConcept("Variable",
				metaElementConcept, syntaxAbsVariable);
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
				metaElementConcept, syntaxGlobalVariable);
		variabilityInstVertex.put("GlobalVariable", instVertexGV);
		instView.addInstVertex(instVertexGV);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-gvtoV", instEdge);
		instEdge.setIdentifier("context-gvtoV");
		// instEdge.setMetaPairwiseRelation(metaEdgeExtends);
		instEdge.setTargetRelation(instVertexV, true);
		instEdge.setSourceRelation(instVertexGV, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-gv", instEdge);
		instEdge.setIdentifier("context-gv");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
				metaElementConcept, syntaxLocalVariable);
		variabilityInstVertex.put("LocalVariable", instVertexLV);
		instView.addInstVertex(instVertexLV);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-lvtoV", instEdge);
		instEdge.setIdentifier("context-lvtoV");
		// instEdge.setMetaPairwiseRelation(metaEdgeExtends);
		instEdge.setTargetRelation(instVertexV, true);
		instEdge.setSourceRelation(instVertexLV, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-lv", instEdge);
		instEdge.setIdentifier("context-lv");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
		instVertex = new InstConcept("ME", metaElementConcept, metaEnumeration);
		variabilityInstVertex.put("ME", instVertex);
		instView.addInstVertex(instVertex);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-me", instEdge);
		instEdge.setIdentifier("context-me");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(childView, true);
		childView.addInstVertex(instVertexCG);
		syntaxMetaChildView.addConcept(syntaxLocalVariable);
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-withoutlv", instEdge);
		instEdge.setIdentifier("context-withoutlv");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexLV, true);
		instEdge.setSourceRelation(childView, true);
		childView.addInstVertex(instVertexLV);
		syntaxMetaChildView.addConcept(syntaxGlobalVariable);
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("context-withoutgv", instEdge);
		instEdge.setIdentifier("context-withoutgv");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexGV, true);
		instEdge.setSourceRelation(childView, true);
		childView.addInstVertex(instVertexGV);

		// Direct variable relations

		IntSemanticPairwiseRelation directCVCGSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("CVCGDirectRel")
				.getEditableSemanticElement();

		List<IntSemanticPairwiseRelation> directCVCGSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directCVCGSemanticEdges.add(directCVCGSemanticEdge);

		MetaPairwiseRelation metaVariableEdge = new MetaPairwiseRelation(
				"Variable To Context Relation", true,
				"Variable To Context Relation", "", "Associates a Variable"
						+ " with the Context Group", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAbsVariable, syntaxContextGroup, directCVCGSemanticEdges,
				directCVCGSemanticEdge.getSemanticRelationTypes());
		syntaxAbsVariable.addMetaPairwiseRelAsOrigin(syntaxContextGroup,
				metaVariableEdge);
		// constraintInstEdges.put("Variable To Context Relation", new InstEdge(
		// MetaEdge, metaVariableEdge));

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-varCGPR", instEdge);
		instEdge.setIdentifier("variab-varCGPR");
		instEdge.setMetaPairwiseRelation(metaVariableEdge);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instVertexV, true);

		MetaPairwiseRelation metaContextEdge = new MetaPairwiseRelation(
				"Context To Context Relation", true,
				"Context To Context Relation", "", "Associates a"
						+ " Context Group with other Context Group", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxContextGroup, syntaxContextGroup,
				directCVCGSemanticEdges,
				directCVCGSemanticEdge.getSemanticRelationTypes());
		syntaxContextGroup.addMetaPairwiseRelAsOrigin(syntaxContextGroup,
				metaContextEdge);
		// constraintInstEdges.put("Context To Context Relation", new InstEdge(
		// MetaEdge, metaVariableEdge));

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-CGCGPR", instEdge);
		instEdge.setIdentifier("variab-CGCGPR");
		instEdge.setMetaPairwiseRelation(metaVariableEdge);
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
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexTSG, true);
		instEdge.setSourceRelation(instView, true);
		syntaxMetaView.addConcept(syntaxGeneralSoftGoal);
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-gsg", instEdge);
		instEdge.setIdentifier("sgs-gsg");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexGSG, true);
		instEdge.setSourceRelation(instView, true);
		syntaxMetaView.addConcept(sOperationalization);
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-oper", instEdge);
		instEdge.setIdentifier("sgs-oper");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
						+ " includes a relation with a softgoal (SG)", 100, 40,
				"/com/variamos/gui/refas/editor/images/claim.png", true,
				Color.BLUE.toString(), 1, true, semClaim);
		syntaxMetaView.addConcept(syntaxClaim);
		InstVertex instVertexCL = new InstConcept("CL", metaElementConcept,
				syntaxClaim);
		variabilityInstVertex.put("CL", instVertexCL);
		instView.addInstVertex(instVertexCL);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-claim", instEdge);
		instEdge.setIdentifier("sgs-claim");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
				100, 40, "/com/variamos/gui/refas/editor/images/softdep.png",
				true, Color.BLUE.toString(), 1, true, semSoftDependency);

		syntaxMetaView.addConcept(syntaxSoftDependency);
		InstVertex instVertexSD = new InstConcept("SoftDependency",
				metaElementConcept, syntaxSoftDependency);
		variabilityInstVertex.put("SoftDependency", instVertexSD);
		instView.addInstVertex(instVertexSD);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-sd", instEdge);
		instEdge.setIdentifier("sgs-sd");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexSD, true);
		instEdge.setSourceRelation(instView, true);

		IntSemanticOverTwoRelation semanticOperClaimGroupRelation = (IntSemanticOverTwoRelation) ((InstConcept) this
				.getSemanticRefas().getVertex("OpertoClaimOverTwoRel"))
				.getEditableSemanticElement();

		semanticRelations = new ArrayList<IntSemanticOverTwoRelation>();
		semanticRelations.add(semanticOperClaimGroupRelation);

		hardMetaOverTwoRel = new MetaOverTwoRelation(
				"OperClaimOverTwoRel",
				true,
				"OperClaimOverTwoRel",
				"plgroup",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " expected on the softgoal in case the Claim is satisfied",
				20, 20, "/com/variamos/gui/pl/editor/images/plgroup.png",
				false, "white", 1, false, semanticRelations);
		syntaxMetaView.addConcept(hardMetaOverTwoRel);
		instVertex = new InstConcept("OperClaimOverTwoRel", metaElementOverTwo,
				hardMetaOverTwoRel);
		variabilityInstVertex.put("OperClaimOverTwoRel", instVertex);
		instView.addInstVertex(instVertex);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-operclaim", instEdge);
		instEdge.setIdentifier("sgs-operclaim");
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-OpertoOT", instEdge);
		instEdge.setIdentifier("sgs-OpertoOT");
		instEdge.setTargetRelation(instVertex, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-OTtoCL", instEdge);
		instEdge.setIdentifier("sgs-OTtoCL");
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instVertex, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-CLtoSG", instEdge);
		instEdge.setIdentifier("sgs-CLtoSG");
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
				syntaxSoftDependency, syntaxAbsSoftGoal,
				directSDSGSemanticEdges, directSDSGSemanticEdge
						.getSemanticRelationTypes());
		syntaxSoftDependency.addMetaPairwiseRelAsOrigin(syntaxAbsSoftGoal,
				metaSDSGEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-SDASGPR", instEdge);
		instEdge.setIdentifier("variab-SDASGPR");
		instEdge.setMetaPairwiseRelation(metaSDSGEdge);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertexSD, true);

		// constraintInstEdges.put("SDSGRelation", new InstEdge(
		// MetaEdge, metaSDSGEdge));

		IntSemanticPairwiseRelation directClaimSGSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("ClaimSGDirectEdge")
				.getEditableSemanticElement();

		List<IntSemanticPairwiseRelation> directClaimSGSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directClaimSGSemanticEdges.add(directClaimSGSemanticEdge);

		MetaPairwiseRelation metaClaimSGEdge = new MetaPairwiseRelation(
				"Claim-Softgoal Relation",
				true,
				"Claim-Softgoal Relation",
				"",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxClaim, syntaxAbsSoftGoal, directClaimSGSemanticEdges,
				directClaimSGSemanticEdge.getSemanticRelationTypes());
		syntaxClaim.addMetaPairwiseRelAsOrigin(syntaxAbsSoftGoal,
				metaClaimSGEdge);

		constraintInstEdges
				.put("Claim-Softgoal Relation", new InstPairwiseRelation(
						metaPairwiseRelation, metaClaimSGEdge));

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-CLASGPR", instEdge);
		instEdge.setIdentifier("variab-CLASGPR");
		instEdge.setMetaPairwiseRelation(metaClaimSGEdge);
		instEdge.setTargetRelation(instVertexASG, true);
		instEdge.setSourceRelation(instVertexCL, true);

		// *************************---------------****************************
		// Assets model

		syntaxMetaView = new MetaView("Assets", true, "Assets General Model",
				"plnode", "Defines a feature", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Assets Palette", 5);
		instView = new InstView("Assets", metaView, syntaxMetaView);
		instViews.add(instView);
		syntaxMetaView.addConcept(sOperationalization);
		instView.addInstVertex(instVertexOper);
		IntSemanticConcept semAsset = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SemAsset"))
				.getEditableSemanticElement();
		MetaConcept syntaxAsset = new MetaConcept("SemAssumption", true,
				"Asset", "refasasset",
				"Represents a asset of the system. The most"
						+ " important assets to represent are those than"
						+ " can implement operationalizations", 100, 40,
				"/com/variamos/gui/refas/editor/images/component.png", true,
				Color.WHITE.toString(), 1, true, semAsset);
		syntaxAsset.addModelingAttribute("name", "String", false, "Name", ""); // TODO
																				// move
																				// to
		// semantic
		// attributes
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
		InstVertex instVertexAsset = new InstConcept("Assumption",
				metaElementConcept, syntaxAsset);
		variabilityInstVertex.put("Assumption", instVertexAsset);
		instView.addInstVertex(instVertexAsset);

		IntSemanticOverTwoRelation semanticAssetOperGroupRelation = (IntSemanticOverTwoRelation) ((InstConcept) this
				.getSemanticRefas().getVertex("AssetOperGroupRel"))
				.getEditableSemanticElement();

		semanticRelations = new ArrayList<IntSemanticOverTwoRelation>();
		semanticRelations.add(semanticAssetOperGroupRelation);

		hardMetaOverTwoRel = new MetaOverTwoRelation("AssetOperGroupDep", true,
				"AssetOperGroupDep", "plgroup",
				"Represents the implementation "
						+ "of an operationalization by a group of assets", 20,
				20, "/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);
		InstVertex instVertexAssetOper = new InstConcept("Asset-OperGroupDep",
				metaElementOverTwo, hardMetaOverTwoRel);
		variabilityInstVertex.put("Asset-OperGroupDep", instVertexAssetOper);
		instView.addInstVertex(instVertexAssetOper);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assettoOT", instEdge);
		instEdge.setIdentifier("asset0-assettoOT");
		instEdge.setTargetRelation(instVertexAssetOper, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-OTtoasset", instEdge);
		instEdge.setIdentifier("asset0-OTtoasset");
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instVertexAssetOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-asset", instEdge);
		instEdge.setIdentifier("asset0-asset");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(childView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-oper", instEdge);
		instEdge.setIdentifier("asset0-oper");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(childView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assetoper", instEdge);
		instEdge.setIdentifier("asset0-assetoper");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(childView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset1-oper", instEdge);
		instEdge.setIdentifier("asset1-oper");
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
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
		instEdge.setMetaPairwiseRelation(metaPairwiseRelView);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(childView, true);

		/*
		 * instEdge = new InstEdge();
		 * this.constraintInstEdges.put("asset2-grpoper", instEdge);
		 * instEdge.setIdentifier("asset2-grpoper");
		 * instEdge.setTargetRelation(instVertex, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		IntSemanticPairwiseRelation directAssetOperSemanticEdge = (IntSemanticPairwiseRelation) getSemanticRefas()
				.getConstraintInstEdge("AssetOperDirectEdge")
				.getEditableSemanticElement();
		List<IntSemanticPairwiseRelation> directAssetOperSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directAssetOperSemanticEdges.add(directAssetOperSemanticEdge);

		MetaPairwiseRelation metaOperEdge = new MetaPairwiseRelation(
				"Asset To Oper Relation", true, "Asset To Oper Relation", "",
				"Represents the "
						+ "implementation of an operationzalization by an"
						+ " asset", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAsset, sOperationalization, directAssetOperSemanticEdges,
				directAssetOperSemanticEdge.getSemanticRelationTypes());

		syntaxMetaView.addConcept(metaOperEdge);
		syntaxAsset.addMetaPairwiseRelAsOrigin(sOperationalization,
				metaOperEdge);
		// constraintInstEdges.put("Asset To Oper Relation", new InstEdge(
		// MetaEdge, metaOperEdge));

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-AsOperPR", instEdge);
		instEdge.setIdentifier("variab-AsOperPR");
		instEdge.setMetaPairwiseRelation(metaOperEdge);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instVertexAsset, true);
	}

	private InstElement getInstView(int index) {
		return instViews.get(index);
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
				if (!(sourceMetaElement instanceof MetaPairwiseRelation)
						&& !(targetMetaElement instanceof MetaPairwiseRelation))
					if (sourceMetaElement.getIdentifier().equals(
							instElement.getIdentifier())
							&& targetMetaElement.getIdentifier().equals(
									instElement2.getIdentifier()))
						out.put(pwr.getIdentifier(),
								pwr.getMetaPairwiseRelation());
				// TODO validate the other end when the OTR has connections
				if (sourceMetaElement instanceof MetaPairwiseRelation)
					if (targetMetaElement.getIdentifier().equals(
							instElement2.getIdentifier()))
						out.put(pwr.getIdentifier(),
								pwr.getMetaPairwiseRelation());
				if (targetMetaElement instanceof MetaPairwiseRelation)
					if (sourceMetaElement.getIdentifier().equals(
							instElement2.getIdentifier()))
						out.put(pwr.getIdentifier(),
								pwr.getMetaPairwiseRelation());
			}
		}
		if (instElement2 instanceof MetaConcept
				&& ((MetaConcept) instElement2).getParent() != null)
			out.putAll(getValidPairwiseRelations(instElement,
					((MetaConcept) instElement2).getParent(), false));
		if (instElement instanceof MetaConcept
				&& ((MetaConcept) instElement).getParent() != null && first)
			out.putAll(getValidPairwiseRelations(
					((MetaConcept) instElement).getParent(), instElement2, true));

		return out;
	}
}
