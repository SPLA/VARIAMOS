package com.variamos.core.refas;

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
import com.variamos.refas.core.sematicsmetamodel.DirectSemanticEdge;
import com.variamos.refas.core.sematicsmetamodel.HardSemanticConcept;
import com.variamos.refas.core.sematicsmetamodel.IncomingSemanticEdge;
import com.variamos.refas.core.sematicsmetamodel.OutgoingSemanticEdge;
import com.variamos.refas.core.sematicsmetamodel.SemanticContextGroup;
import com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency;
import com.variamos.refas.core.sematicsmetamodel.SemanticVariable;
import com.variamos.refas.core.sematicsmetamodel.SoftSemanticConcept;
import com.variamos.refas.core.sematicsmetamodel.SoftSemanticConceptSatisficing;
import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.refas.core.types.GroupRelationType;
import com.variamos.refas.core.types.PerspectiveType;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metametamodel.ConfigurationAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;
import com.variamos.syntaxsupport.metametamodel.MetaDirectRelation;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metametamodel.MetaElement;
import com.variamos.syntaxsupport.metametamodel.MetaEnumeration;
import com.variamos.syntaxsupport.metametamodel.MetaGroupDependency;
import com.variamos.syntaxsupport.metametamodel.MetaView;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;
import com.variamos.syntaxsupport.metametamodel.SimulationAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstEnumeration;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodel.InstView;
import com.variamos.syntaxsupport.semanticinterface.IntDirectEdgeType;
import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticEdge;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupDependency;

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
	private Map<String, InstGroupDependency> instGroupDependencies;
	/**
	 * 
	 */
	private Map<String, InstEdge> constraintInstEdges;

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
		instGroupDependencies = new HashMap<String, InstGroupDependency>();
		otherInstVertex = new HashMap<String, InstVertex>();
		constraintInstEdges = new HashMap<String, InstEdge>();
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

	public Map<String, InstGroupDependency> getInstGroupDependencies() {
		return instGroupDependencies;
	}

	public Collection<InstGroupDependency> getInstGroupDependenciesCollection() {
		return instGroupDependencies.values();
	}

	public Map<String, InstEdge> getConstraintInstEdges() {
		return constraintInstEdges;
	}

	public Collection<InstEdge> getConstraintInstEdgesCollection() {
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

	public void putInstGroupDependency(InstGroupDependency groupDep) {
		instGroupDependencies.put(groupDep.getIdentifier(), groupDep);
	}

	public void putConstraintInstEdge(InstEdge element) {
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

	public String addNewInstGroupDependency(InstGroupDependency groupDep) {
		String id = getNextInstGroupDependencyId(groupDep);
		groupDep.setIdentifier(id);
		groupDep.setInstAttribute("name", id);
		instGroupDependencies.put(id, groupDep);
		return id;
	}

	public String addNewConstraintInstEdge(InstEdge directRelation) {
		String id = getNextConstraintInstEdgeId(directRelation);
		directRelation.setIdentifier(id);
		constraintInstEdges.put(id, directRelation);
		return id;
	}

	public String addNewOtherInstEdge(InstEdge directRelation) {
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
				classId = ((InstGroupDependency) element)
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
				classId = ((InstGroupDependency) element)
						.getMetaVertexIdentifier();
		}
		while (otherInstVertex.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextInstGroupDependencyId(InstGroupDependency grouDep) {

		int id = 1;
		String classId = grouDep.getMetaVertexIdentifier();

		while (instGroupDependencies.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextConstraintInstEdgeId(InstEdge element) {

		int id = 1;
		String classId = null;
		classId = MetaEdge.getClassId();

		while (constraintInstEdges.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextOtherInstEdgeId(InstEdge element) {

		int id = 1;
		String classId = null;
		classId = MetaEdge.getClassId();

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

	public InstEdge getConstraintInstEdge(String edgeId) {
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
		if (obj instanceof InstConcept) {
			InstConcept concept = (InstConcept) obj;
			variabilityInstVertex.remove(concept.getIdentifier());
		}
		if (obj instanceof InstEnumeration) {
			InstEnumeration concept = (InstEnumeration) obj;
			variabilityInstVertex.remove(concept.getIdentifier());
		}
		if (obj instanceof InstGroupDependency)
			instGroupDependencies.remove(((InstGroupDependency) obj)
					.getIdentifier());
		else if (obj instanceof InstEdge)
			constraintInstEdges.remove(((InstEdge) obj).getIdentifier());
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
				if (instVertex.getMetaVertex().getVisible())
					elements.add(instVertex.getMetaVertex().getIdentifier());
			}
			for (InstVertex instVertex : otherInstVertex.values()) {
				if (instVertex.getMetaVertex().getVisible())
					elements.add(instVertex.getMetaVertex().getIdentifier());
			}
		} else if (modelViewInd < instViews.size() && modelViewSubInd == -1) {
			for (InstVertex instVertex : instViews.get(modelViewInd)
					.getInstVertices()) {
				if (instVertex.getMetaVertex().getVisible())
					elements.add(instVertex.getMetaVertex().getIdentifier());
			}
		}
		if (modelViewInd != -1
				&& modelViewInd < instViews.size()
				&& modelViewSubInd != -1
				&& modelViewSubInd < instViews.get(modelViewInd)
						.getChildViews().size()) {
			for (InstVertex instVertex : instViews.get(modelViewInd)
					.getInstVertices()) {
				if (instVertex.getMetaVertex().getVisible())
					elements.add(instVertex.getMetaVertex().getIdentifier());
			}
		}
		return elements;
	}

	private void createBasicSyntax() {
		AbstractSemanticVertex semVertex = new AbstractSemanticVertex();
		MetaConcept concept = new MetaConcept("Concept", true, "Concept",
				"refasenumeration", "MetaConcept", 100, 150,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.BLUE.toString(), 3, true, semVertex);

		List<IntSemanticGroupDependency> semOverTwoRelations = new ArrayList<IntSemanticGroupDependency>();
		semOverTwoRelations = new ArrayList<IntSemanticGroupDependency>();
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("Concept", new InstConcept(concept));
		MetaConcept enumeration = new MetaConcept("Enumeration", true,
				"Enumeration", "refasenumeration", "MetaEnumeration", 100, 150,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.BLUE.toString(), 3, true, semVertex);
		variabilityInstVertex.put("Enumeration", new InstConcept(enumeration));
		MetaGroupDependency overTwoRelation = new MetaGroupDependency(
				"OverTwoRelation", true, "OverTwoRelation", "plgroup",
				"OverTwoRelation", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", true,
				Color.BLUE.toString(), 3, true, semOverTwoRelations);

		variabilityInstVertex.put("OverTwoRelation", new InstGroupDependency(
				overTwoRelation));
	}

	private void createBasicSemantic() {
		AbstractSemanticVertex semVertex = new AbstractSemanticVertex();
		MetaConcept concept = new MetaConcept("Concept", true, "Concept",
				"refasenumeration", "MetaConcept", 100, 150,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.BLUE.toString(), 3, true, semVertex);

		List<IntSemanticGroupDependency> semOverTwoRelations = new ArrayList<IntSemanticGroupDependency>();
		semOverTwoRelations = new ArrayList<IntSemanticGroupDependency>();
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("Concept", new InstConcept(concept));
		MetaConcept enumeration = new MetaConcept("Enumeration", true,
				"Enumeration", "refasenumeration", "MetaEnumeration", 100, 150,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.BLUE.toString(), 3, true, semVertex);
		variabilityInstVertex.put("Enumeration", new InstConcept(enumeration));
		MetaGroupDependency overTwoRelation = new MetaGroupDependency(
				"OverTwoRelation", true, "OverTwoRelation", "plgroup",
				"OverTwoRelation", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", true,
				Color.BLUE.toString(), 3, true, semOverTwoRelations);

		variabilityInstVertex.put("OverTwoRelation", new InstGroupDependency(
				overTwoRelation));
	}

	public void createSemantic() {
		MetaConcept metaConcept = ((InstConcept) this.getSyntaxRefas()
				.getVertex("Concept")).getMetaConcept();
		MetaGroupDependency metaOverTwoRelation = ((InstGroupDependency) this
				.getSyntaxRefas().getVertex("OverTwoRelation"))
				.getMetaGroupDependency();
		IncomingSemanticEdge groupRelation = null;
		AbstractSemanticVertex semGeneralElement = new AbstractSemanticVertex();
		variabilityInstVertex.put("GE", new InstConcept(metaConcept,
				semGeneralElement));

		// Design attributes

		semGeneralElement.putSemanticAttribute("Description",
				new SemanticAttribute("Description", "String", false,
						"Description", ""));
		semGeneralElement.addDisPropEditableAttribute("04#" + "Description");
		semGeneralElement.addDisPropVisibleAttribute("04#" + "Description");

		// Configuration attributes

		semGeneralElement.putSemanticAttribute("Active",
				new ConfigurationAttribute("Active", "Boolean", true,
						"Is Active", true));
		semGeneralElement.putSemanticAttribute("Visibility",
				new ConfigurationAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		semGeneralElement.putSemanticAttribute("Required",
				new ConfigurationAttribute("Required", "Boolean", true,
						"Is Required", false));
		semGeneralElement.putSemanticAttribute("Allowed",
				new ConfigurationAttribute("Allowed", "Boolean", true,
						"Is Allowed", true));
		semGeneralElement.putSemanticAttribute("RequiredLevel",
				new ConfigurationAttribute("RequiredLevel", "Integer", false,
						"Required Level", 0)); // TODO define domain
												// or Enum
												// Level

		semGeneralElement.putSemanticAttribute("ForcedSatisfied",
				new ConfigurationAttribute("ForcedSatisfied", "Boolean", false,
						"Force Satisfaction", false));
		semGeneralElement.putSemanticAttribute("ForcedSelected",
				new ConfigurationAttribute("ForcedSelected", "Boolean", false,
						"Force Selection", false));

		semGeneralElement.addDisPropEditableAttribute("01#" + "Active");
		// semGeneralElement.addDisPropEditableAttribute("02#" +
		// "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addDisPropEditableAttribute("03#" + "Allowed" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addDisPropEditableAttribute("04#" + "Required" + "#"
				+ "Allowed" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addDisPropEditableAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true" + "#" + "0");
		semGeneralElement.addDisPropEditableAttribute("10#" + "ForcedSatisfied"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addDisPropEditableAttribute("15#" + "ForcedSelected"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");

		semGeneralElement.addDisPropVisibleAttribute("01#" + "Active");
		semGeneralElement.addDisPropVisibleAttribute("02#" + "Visibility");
		semGeneralElement.addDisPropVisibleAttribute("03#" + "Allowed");
		semGeneralElement.addDisPropVisibleAttribute("04#" + "Required");
		semGeneralElement.addDisPropVisibleAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true");
		semGeneralElement.addDisPropVisibleAttribute("10#" + "ForcedSatisfied");
		semGeneralElement.addDisPropVisibleAttribute("15#" + "ForcedSelected");

		// Simulation attributes

		semGeneralElement.putSemanticAttribute("InitialRequiredLevel",
				new SimulationAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		semGeneralElement.putSemanticAttribute("SimRequiredLevel",
				new SimulationAttribute("SimRequiredLevel", "Integer", false,
						"Required Level", false));
		semGeneralElement.putSemanticAttribute("ValidationRequiredLevel",
				new SimulationAttribute("ValidationRequiredLevel", "Integer",
						false, "Required Level by Validation", false));
		semGeneralElement.putSemanticAttribute("SimRequired",
				new SimulationAttribute("SimRequired", "Boolean", false,
						"***Required***", false));

		semGeneralElement.putSemanticAttribute("Satisfied",
				new SimulationAttribute("Satisfied", "Boolean", false,
						"***Satisfied***", false));
		semGeneralElement.putSemanticAttribute("AlternativeSatisfied",
				new SimulationAttribute("AlternativeSatisfied", "Boolean",
						false, "Satisfied by Alternatives", false));
		semGeneralElement.putSemanticAttribute("ValidationSatisfied",
				new SimulationAttribute("ValidationSatisfied", "Boolean",
						false, "Satisfied by Validation", false));
		semGeneralElement.putSemanticAttribute("SatisfiedLevel",
				new SimulationAttribute("SatisfiedLevel", "Integer", false,
						"Satisficing Level", false));
		semGeneralElement.putSemanticAttribute("NoSatisfactionConflict",
				new SimulationAttribute("NoSatisfactionConflict", "Boolean",
						false, "No Satisfaction Conflict", true));

		semGeneralElement.putSemanticAttribute("Selected",
				new SimulationAttribute("Selected", "Boolean", false,
						"***Selected***", false));
		semGeneralElement.putSemanticAttribute("PreferredSelected",
				new SimulationAttribute("PreferredSelected", "Boolean", false,
						"Select by Preferred", true));
		semGeneralElement.putSemanticAttribute("ValidationSelected",
				new SimulationAttribute("ValidationSelected", "Boolean", false,
						"Selected by Validation", false));
		semGeneralElement.putSemanticAttribute("SolverSelected",
				new SimulationAttribute("SolverSelected", "Boolean", false,
						"Selected by Solver", false));

		semGeneralElement.putSemanticAttribute("Optional",
				new SimulationAttribute("Optional", "Boolean", false,
						"*Is Optional*", false));

		semGeneralElement.putSemanticAttribute("SimAllowed",
				new SimulationAttribute("SimAllowed", "Boolean", false,
						"Is Allowed", true));

		semGeneralElement.addDisPropVisibleAttribute("01#" + "SimRequired");
		semGeneralElement
				.addDisPropVisibleAttribute("03#" + "SimRequiredLevel");
		semGeneralElement.addDisPropVisibleAttribute("05#"
				+ "InitialRequiredLevel");
		semGeneralElement.addDisPropVisibleAttribute("07#"
				+ "ValidationRequiredLevel");

		semGeneralElement.addDisPropVisibleAttribute("09#" + "Selected");
		semGeneralElement.addDisPropVisibleAttribute("11#"
				+ "PreferredSelected");
		semGeneralElement.addDisPropVisibleAttribute("13#"
				+ "ValidationSelected");
		semGeneralElement.addDisPropVisibleAttribute("15#" + "SolverSelected");

		semGeneralElement.addDisPropVisibleAttribute("02#" + "Satisfied");
		semGeneralElement.addDisPropVisibleAttribute("04#"
				+ "AlternativeSatisfied");
		semGeneralElement.addDisPropVisibleAttribute("06#"
				+ "ValidationSatisfied");
		semGeneralElement.addDisPropVisibleAttribute("08#" + "SatisfiedLevel");
		semGeneralElement.addDisPropVisibleAttribute("10#"
				+ "NoSatisfactionConflict");

		semGeneralElement.addDisPropVisibleAttribute("12#" + "SimAllowed");

		semGeneralElement.addDisPropVisibleAttribute("14#" + "Optional");

		// Definition of variability concept and relations
		HardSemanticConcept semHardConcept = new HardSemanticConcept(
				semGeneralElement, "semHardConcept");
		variabilityInstVertex.put("HC", new InstConcept(metaConcept,
				semHardConcept));

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
		semHardOutgoingRelation
				.add(new OutgoingSemanticEdge("", semHardConcept));

		// Feature concepts

		HardSemanticConcept semFeature = new HardSemanticConcept(
				semGeneralElement, "Feature");
		variabilityInstVertex
				.put("F", new InstConcept(metaConcept, semFeature));

		// definition of other concepts

		HardSemanticConcept semAssumption = new HardSemanticConcept(
				semHardConcept, "Assumption");
		variabilityInstVertex.put("AS", new InstConcept(metaConcept,
				semAssumption));

		HardSemanticConcept semGoal = new HardSemanticConcept(semHardConcept,
				"Goal");
		semGoal.addDisPanelVisibleAttribute("01#" + "satisfactionType");
		semGoal.addDisPanelSpacersAttribute("<#" + "satisfactionType" + "#>\n");
		variabilityInstVertex.put("G", new InstConcept(metaConcept, semGoal));

		HardSemanticConcept semOperationalization = new HardSemanticConcept(
				semHardConcept, "Operationalization");
		variabilityInstVertex.put("OPER", new InstConcept(metaConcept,
				semOperationalization));

		SoftSemanticConcept semSoftgoal = new SoftSemanticConcept(
				semGeneralElement, "SoftGoal");
		variabilityInstVertex.put("SG", new InstConcept(metaConcept,
				semSoftgoal));

		SemanticVariable semVariable = new SemanticVariable("Variable");
		variabilityInstVertex.put("VAR", new InstConcept(metaConcept,
				semVariable));

		SemanticContextGroup semContextGroup = new SemanticContextGroup(
				"ContextGroup");
		variabilityInstVertex.put("CG", new InstConcept(metaConcept,
				semContextGroup));

		HardSemanticConcept semAsset = new HardSemanticConcept(
				semGeneralElement, "Asset");
		variabilityInstVertex.put("ASSE",
				new InstConcept(metaConcept, semAsset));

		SoftSemanticConceptSatisficing semClaim = new SoftSemanticConceptSatisficing(
				semGeneralElement, "Claim", true);
		variabilityInstVertex.put("CL", new InstConcept(metaConcept, semClaim));
		semClaim.putSemanticAttribute("Operationalizations",
				new SemanticAttribute("Operationalizations", "MClass", false,
						"Operationalizations",
						"com.variamos.syntaxsupport.metamodel.InstConcept",
						"OPER", "", ""));
		semClaim.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression", "String", false,
						"Conditional Expression", ""));
		semClaim.putSemanticAttribute("CompExp", new ConfigurationAttribute(
				"CompExp", "Boolean", false, "Boolean Comp. Expression", true));
		semClaim.putSemanticAttribute("ClaimSelected",
				new ConfigurationAttribute("ClaimSelected", "Boolean", false,
						"Claim Selected", false));

		semClaim.addDisPanelVisibleAttribute("01#" + "Operationalizations");
		semClaim.addDisPanelVisibleAttribute("03#" + "ConditionalExpression"); // TODO
																				// move
																				// to
																				// semantic
																				// attributes

		semClaim.addDisPropEditableAttribute("01#" + "Operationalizations");
		semClaim.addDisPropEditableAttribute("03#" + "ConditionalExpression");

		semClaim.addDisPropVisibleAttribute("01#" + "Operationalizations");
		semClaim.addDisPropVisibleAttribute("03#" + "ConditionalExpression");

		semClaim.addDisPropEditableAttribute("01#" + "CompExp");
		semClaim.addDisPropVisibleAttribute("01#" + "CompExp");

		semClaim.addDisPropVisibleAttribute("02#" + "ClaimSelected");

		semClaim.addDisPanelSpacersAttribute("#" + "Operationalizations"
				+ "#\n#");

		SoftSemanticConceptSatisficing semSoftDependency = new SoftSemanticConceptSatisficing(
				semGeneralElement, "SoftDependency", false);
		variabilityInstVertex.put("SD", new InstConcept(metaConcept,
				semSoftDependency));

		semSoftDependency.putSemanticAttribute("CompExp",
				new ConfigurationAttribute("CompExp", "Boolean", false,
						"Boolean Comp. Expression", true));
		semSoftDependency.putSemanticAttribute("SDSelected",
				new ConfigurationAttribute("SDSelected", "Boolean", false,
						"SD Selected", false));

		semSoftDependency.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression", "String", false,
						"Conditional Expression", ""));
		semSoftDependency.addDisPanelVisibleAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addDisPropEditableAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addDisPropVisibleAttribute("03#"
				+ "ConditionalExpression");

		semSoftDependency.addDisPropEditableAttribute("01#" + "CompExp");
		semSoftDependency.addDisPropVisibleAttribute("01#" + "CompExp");

		semSoftDependency.addDisPropVisibleAttribute("02#" + "SDSelected");

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

		List<IntDirectEdgeType> FeatureDirectRelation = new ArrayList<IntDirectEdgeType>();
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

		List<IntDirectEdgeType> alternative_prefferedDirectRelation = new ArrayList<IntDirectEdgeType>();
		alternative_prefferedDirectRelation.add(DirectEdgeType.alternative);
		alternative_prefferedDirectRelation.add(DirectEdgeType.preferred);

		List<IntDirectEdgeType> alter_preff_impl_meansDirectRelation = new ArrayList<IntDirectEdgeType>();
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.alternative);
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.preferred);
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.implication);
		alter_preff_impl_meansDirectRelation.add(DirectEdgeType.means_ends);

		List<IntDirectEdgeType> allSGDirectRelation = new ArrayList<IntDirectEdgeType>();
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

		List<IntDirectEdgeType> implicationDirectRelation = new ArrayList<IntDirectEdgeType>();
		implicationDirectRelation.add(DirectEdgeType.implication);

		List<IntDirectEdgeType> means_endsImplicationDirectRelation = new ArrayList<IntDirectEdgeType>();
		means_endsImplicationDirectRelation.add(DirectEdgeType.means_ends);
		means_endsImplicationDirectRelation.add(DirectEdgeType.implication);

		List<IntDirectEdgeType> softdepDirectRelation = new ArrayList<IntDirectEdgeType>();
		softdepDirectRelation.add(DirectEdgeType.softdependency);

		List<IntDirectEdgeType> noneDirectRelation = new ArrayList<IntDirectEdgeType>();
		noneDirectRelation.add(DirectEdgeType.none);

		List<IntDirectEdgeType> claimDirectRelation = new ArrayList<IntDirectEdgeType>();
		claimDirectRelation.add(DirectEdgeType.claim);

		List<GroupRelationType> implementationGroupRelation = new ArrayList<GroupRelationType>();
		implementationGroupRelation.add(GroupRelationType.implementation);

		List<IntDirectEdgeType> implementationDirectRelation = new ArrayList<IntDirectEdgeType>();
		implementationDirectRelation.add(DirectEdgeType.implementation);

		// required and conflict group relations of the HardSemanticConcept
		List<GroupRelationType> requires_conflictsGroupRelation = new ArrayList<GroupRelationType>();
		requires_conflictsGroupRelation.add(GroupRelationType.required);
		requires_conflictsGroupRelation.add(GroupRelationType.conflict);

		SemanticGroupDependency semanticHardHardGroupRelation = new SemanticGroupDependency(
				"HardHardGroupRel", false, requires_conflictsGroupRelation,
				semHardOutgoingRelation);
		groupRelation = new IncomingSemanticEdge("",
				semanticHardHardGroupRelation);
		semHardConcept.addGroupRelation(groupRelation);
		variabilityInstVertex.put("HardHardGroupRel", new InstGroupDependency(
				metaOverTwoRelation, semanticHardHardGroupRelation));

		// required and conflict direct relations of the HardSemanticConcept
		List<IntDirectEdgeType> requires_conflictsDirectRelation = new ArrayList<IntDirectEdgeType>();
		requires_conflictsDirectRelation.add(DirectEdgeType.required);
		requires_conflictsDirectRelation.add(DirectEdgeType.conflict);

		List<AbstractSemanticVertex> semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semHardConcept);

		DirectSemanticEdge directHardHardSemanticEdge = new DirectSemanticEdge(
				"HardHardDirectEdge", false, requires_conflictsDirectRelation,
				semanticVertexs, false);
		semHardConcept.addDirectRelation(directHardHardSemanticEdge);
		constraintInstEdges.put("HardHardDirectEdge", new InstEdge(
				directHardHardSemanticEdge));

		// Feature to Feature

		List<OutgoingSemanticEdge> outgoingFeatureRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingFeatureRelation.add(new OutgoingSemanticEdge("", semFeature));
		SemanticGroupDependency semanticFeatureFeatureGroupRelation = new SemanticGroupDependency(
				"FeatureFeatureGroupRel", false, featureMeansGroupRelation,
				outgoingFeatureRelation);
		groupRelation = new IncomingSemanticEdge("",
				semanticFeatureFeatureGroupRelation);
		semFeature.addGroupRelation(groupRelation);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semFeature);

		DirectSemanticEdge directFeatureFeatureSemanticEdge = new DirectSemanticEdge(
				"FeatureFeatureDirectEdge", false, false, semanticVertexs,
				alter_preff_impl_meansDirectRelation);
		semGoal.addDirectRelation(directFeatureFeatureSemanticEdge);
		variabilityInstVertex.put("FeauteFeateuGroupRel", new InstConcept(
				metaConcept, semanticFeatureFeatureGroupRelation));
		constraintInstEdges.put("FeauteFeatureDirectEdge", new InstEdge(
				directFeatureFeatureSemanticEdge));

		// Goal to Goal

		List<OutgoingSemanticEdge> outgoingGoalRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingGoalRelation.add(new OutgoingSemanticEdge("", semGoal));
		SemanticGroupDependency semanticGoalGoalGroupRelation = new SemanticGroupDependency(
				"GoalGoalGroupRel", false, altern_impl_meansGroupRelation,
				outgoingGoalRelation);
		groupRelation = new IncomingSemanticEdge("",
				semanticGoalGoalGroupRelation);
		semGoal.addGroupRelation(groupRelation);

		semanticVertexs.add(semGoal);

		DirectSemanticEdge directGoalGoalSemanticEdge = new DirectSemanticEdge(
				"GoalGoalDirectEdge", false, false, semanticVertexs,
				alter_preff_impl_meansDirectRelation);
		semGoal.addDirectRelation(directGoalGoalSemanticEdge);
		variabilityInstVertex.put("GoalGoalGroupRel", new InstGroupDependency(
				metaOverTwoRelation, semanticGoalGoalGroupRelation));
		constraintInstEdges.put("GoalGoalDirectEdge", new InstEdge(
				directGoalGoalSemanticEdge));

		// Oper to Goal and Oper
		List<OutgoingSemanticEdge> outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingOperationalizationRelation.add(new OutgoingSemanticEdge("",
				semGoal));
		outgoingOperationalizationRelation.add(new OutgoingSemanticEdge("",
				semOperationalization));
		SemanticGroupDependency semanticOperGoalGroupRelation = new SemanticGroupDependency(
				"OPerGoalGroupRel", false, means_endsImplicationGroupRelation,
				outgoingOperationalizationRelation);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semOperationalization);
		semanticVertexs.add(semGoal);

		DirectSemanticEdge directOperGoalSemanticEdge = new DirectSemanticEdge(
				"OperGoalDirectEdge", false, false, semanticVertexs,
				means_endsImplicationDirectRelation);
		groupRelation = new IncomingSemanticEdge("",
				semanticOperGoalGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);
		semOperationalization.addDirectRelation(directOperGoalSemanticEdge);
		variabilityInstVertex.put("OperGoalGroupRel", new InstGroupDependency(
				metaOverTwoRelation, semanticOperGoalGroupRelation));
		constraintInstEdges.put("OperGoalDirectEdge", new InstEdge(
				directOperGoalSemanticEdge));

		// Oper to Oper
		outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingOperationalizationRelation.add(new OutgoingSemanticEdge("",
				semOperationalization));
		SemanticGroupDependency semanticOperOperGroupRelation = new SemanticGroupDependency(
				"OperOperGroupRel", false, alternativeGroupRelation,
				outgoingOperationalizationRelation);
		groupRelation = new IncomingSemanticEdge("",
				semanticOperOperGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semOperationalization);

		DirectSemanticEdge directOperOperSemanticEdge = new DirectSemanticEdge(
				"OperOperDirectEdge", false, false, semanticVertexs,
				alternative_prefferedDirectRelation);
		semOperationalization.addDirectRelation(directOperOperSemanticEdge);
		variabilityInstVertex.put("OperOperGroupRel", new InstGroupDependency(
				metaOverTwoRelation, semanticOperOperGroupRelation));
		constraintInstEdges.put("OperOperDirectEdge", new InstEdge(
				directOperOperSemanticEdge));

		// SG to SG
		List<OutgoingSemanticEdge> outgoingSoftgoalRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingSoftgoalRelation.add(new OutgoingSemanticEdge("", true,
				semSoftgoal));
		SemanticGroupDependency semanticSGSGGroupRelation = new SemanticGroupDependency(
				"SGSGGroupRel", false, allSGGroupRelation,
				outgoingSoftgoalRelation);
		groupRelation = new IncomingSemanticEdge("SGSGGroupRel",
				semanticSGSGGroupRelation);
		semSoftgoal.addGroupRelation(groupRelation);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semSoftgoal);

		DirectSemanticEdge directSGSGSemEdge = new DirectSemanticEdge(
				"SGSGDirectEdge", true, false, semanticVertexs,
				allSGDirectRelation);
		directSGSGSemEdge.putSemanticAttribute(AbstractSemanticEdge.VAR_LEVEL,
				new SemanticAttribute(AbstractSemanticEdge.VAR_LEVEL,
						"Enumeration", false,
						AbstractSemanticEdge.VAR_LEVELNAME,
						AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		directSGSGSemEdge.addDisPropEditableAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directSGSGSemEdge.addDisPropVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directSGSGSemEdge.addDisPanelVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		semSoftgoal.addDirectRelation(directSGSGSemEdge);
		variabilityInstVertex.put("SGSGGroupRel", new InstGroupDependency(
				metaOverTwoRelation, semanticSGSGGroupRelation));
		constraintInstEdges.put("SGSGDirectEdge", new InstEdge(
				directSGSGSemEdge));

		// CV to CG
		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semContextGroup);

		DirectSemanticEdge directCVCGSemanticEdge = new DirectSemanticEdge(
				"CVCGDirectRel", false, false, semanticVertexs,
				noneDirectRelation);
		semVariable.addDirectRelation(directCVCGSemanticEdge);
		constraintInstEdges.put("CVCGDirectRel", new InstEdge(
				directCVCGSemanticEdge));

		// Oper to Claim
		outgoingOperationalizationRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingSoftgoalRelation.add(new OutgoingSemanticEdge("outclaim",
				semClaim));
		SemanticGroupDependency semanticOperClaimGroupRelation = new SemanticGroupDependency(
				"OperClaimGroupRel", true, implicationGroupRelation,
				outgoingSoftgoalRelation);
		groupRelation = new IncomingSemanticEdge("",
				semanticOperClaimGroupRelation);
		semOperationalization.addGroupRelation(groupRelation);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semClaim);

		DirectSemanticEdge directOperClaimSemanticEdge = new DirectSemanticEdge(
				"OperClaimDirectEdge", true, true, semanticVertexs,
				implicationDirectRelation);
		semOperationalization.addDirectRelation(directOperClaimSemanticEdge);
		directOperClaimSemanticEdge.putSemanticAttribute(
				AbstractSemanticEdge.VAR_LEVEL, new SemanticAttribute(
						AbstractSemanticEdge.VAR_LEVEL, "Enumeration", false,
						AbstractSemanticEdge.VAR_LEVEL,
						AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		directOperClaimSemanticEdge.addDisPropEditableAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directOperClaimSemanticEdge.addDisPropVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directOperClaimSemanticEdge.addDisPanelVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		semClaim.addDirectRelation(directOperClaimSemanticEdge);
		variabilityInstVertex.put("OperClaimGroupRel", new InstGroupDependency(
				metaOverTwoRelation, semanticOperClaimGroupRelation));
		constraintInstEdges.put("OperClaimDirectEdge", new InstEdge(
				directOperClaimSemanticEdge));

		// Claim to SG

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semSoftgoal);

		DirectSemanticEdge directClaimSGSemanticEdge = new DirectSemanticEdge(
				"ClaimSGDirectEdge", true, true, semanticVertexs,
				claimDirectRelation);
		directClaimSGSemanticEdge.putSemanticAttribute(
				AbstractSemanticEdge.VAR_LEVEL, new SemanticAttribute(
						AbstractSemanticEdge.VAR_LEVEL, "Enumeration", false,
						AbstractSemanticEdge.VAR_LEVEL,
						AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		directClaimSGSemanticEdge.addDisPropEditableAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directClaimSGSemanticEdge.addDisPropVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directClaimSGSemanticEdge.addDisPanelVisibleAttribute("08#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		semClaim.addDirectRelation(directClaimSGSemanticEdge);
		constraintInstEdges.put("ClaimSGDirectEdge", new InstEdge(
				directClaimSGSemanticEdge));

		// SD to SG

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semSoftgoal);

		DirectSemanticEdge directSDSGSemanticEdge = new DirectSemanticEdge(
				"SDSGDirectEdge", true, true, semanticVertexs,
				softdepDirectRelation);
		directSDSGSemanticEdge.putSemanticAttribute(
				AbstractSemanticEdge.VAR_LEVEL, new SemanticAttribute(
						AbstractSemanticEdge.VAR_LEVEL, "Enumeration", false,
						AbstractSemanticEdge.VAR_LEVELNAME,
						AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		directSDSGSemanticEdge.addDisPropEditableAttribute("04#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directSDSGSemanticEdge.addDisPropVisibleAttribute("04#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		directSDSGSemanticEdge.addDisPanelVisibleAttribute("04#"
				+ AbstractSemanticEdge.VAR_LEVEL);
		semSoftDependency.addDirectRelation(directSDSGSemanticEdge);
		constraintInstEdges.put("SDSGDirectEdge", new InstEdge(
				directSDSGSemanticEdge));

		// Asset to Oper
		List<OutgoingSemanticEdge> outgoingAssetRelation = new ArrayList<OutgoingSemanticEdge>();
		outgoingAssetRelation.add(new OutgoingSemanticEdge("outoper",
				semOperationalization));
		SemanticGroupDependency semanticAssetOperGroupRelation = new SemanticGroupDependency(
				"AssetOperGroupRel", false, implementationGroupRelation,
				outgoingAssetRelation);
		groupRelation = new IncomingSemanticEdge("AssetOperGroupRel",
				semanticAssetOperGroupRelation);
		semSoftgoal.addGroupRelation(groupRelation);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semOperationalization);

		DirectSemanticEdge directAssetOperSemanticEdge = new DirectSemanticEdge(
				"AssetOperDirectEdge", false, true, semanticVertexs,
				implementationDirectRelation);
		semAsset.addDirectRelation(directAssetOperSemanticEdge);
		variabilityInstVertex.put("AssetOperGroupRel", new InstGroupDependency(
				metaOverTwoRelation, semanticAssetOperGroupRelation));
		constraintInstEdges.put("AssetOperDirectEdge", new InstEdge(
				directAssetOperSemanticEdge));

	}

	private void createSyntax() {

		MetaView syntaxMetaView = null;
		InstVertex instVertex = null;
		InstView instView = null;

		// *************************---------------****************************
		// Goals and availability model

		syntaxMetaView = new MetaView("GoalsAndVaribilityModel",
				"Goals and Variability Model", "Goals and Variability Palette",
				1);
		instView = new InstView(syntaxMetaView);
		instViews.add(instView);

		MetaElement metaElementConcept = getSyntaxRefas().getVertex("Concept")
				.getEditableMetaElement();
		MetaElement metaElementOverTwo = getSyntaxRefas().getVertex(
				"OverTwoRelation").getEditableMetaElement();
		IntSemanticConcept semFeature = (IntSemanticConcept) ((InstConcept) getSemanticRefas()
				.getVertex("F")).getEditableSemanticElement();
		MetaConcept syntaxFeature = new MetaConcept("F", true, "Feature",
				"plnode", "Defines a feature", 100, 40,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, true, semFeature);
		syntaxFeature.addModelingAttribute("name", "String", false, "Name", "");

		instVertex = new InstConcept(syntaxFeature, metaElementConcept);
		variabilityInstVertex.put("F", instVertex);
		syntaxMetaView.addConcept(syntaxFeature);
		instView.addInstVertex(instVertex);

		syntaxFeature.addDisPanelVisibleAttribute("03#" + "name");

		syntaxFeature.addDisPropEditableAttribute("03#" + "name");

		syntaxFeature.addDisPropVisibleAttribute("03#" + "name");

		// Feature direct relations

		// TODO delete
		List<IntDirectEdgeType> allSGDirectRelation = new ArrayList<IntDirectEdgeType>();
		allSGDirectRelation.add(DirectEdgeType.alternative);
		allSGDirectRelation.add(DirectEdgeType.preferred);
		allSGDirectRelation.add(DirectEdgeType.implication);
		allSGDirectRelation.add(DirectEdgeType.means_ends);
		allSGDirectRelation.add(DirectEdgeType.conflict);
		allSGDirectRelation.add(DirectEdgeType.required);

		IntDirectSemanticEdge directFeatureFeatureSemanticEdge = (IntDirectSemanticEdge) getSemanticRefas()
				.getConstraintInstEdge("FeauteFeatureDirectEdge")
				.getEditableSemanticElement();
		List<IntDirectSemanticEdge> directFeatureSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		directFeatureSemanticEdges.add(directFeatureFeatureSemanticEdge);

		MetaDirectRelation metaFeatureEdge = new MetaDirectRelation(
				"Feature Relation", false, "Feature Relation", "plnode",
				"Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				syntaxFeature, syntaxFeature, directFeatureSemanticEdges,
				allSGDirectRelation);
		syntaxFeature.addMetaEdgeAsOrigin(syntaxFeature, metaFeatureEdge);
		constraintInstEdges.put("Feature Relation", new InstEdge(
				metaFeatureEdge));
		syntaxMetaView.addConcept(metaFeatureEdge);

		// Group Feature Relations

		List<IntSemanticGroupDependency> semanticFeatRelations = new ArrayList<IntSemanticGroupDependency>();
		// semanticFeatRelations.add(semanticFeatureFeatureGroupRelation);

		MetaGroupDependency syntaxFeatureGroupDep = new MetaGroupDependency(
				"FeatGroupDep", true, "FeatGroupDep", "plgroup",
				"Group relation between"
						+ " Feature concepts. Defines different types of"
						+ " cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticFeatRelations);

		syntaxMetaView.addConcept(syntaxFeatureGroupDep);
		instVertex = new InstGroupDependency(syntaxFeatureGroupDep,
				metaElementOverTwo);
		variabilityInstVertex.put("FeatGroupDep", instVertex);
		instView.addInstVertex(instVertex);

		syntaxFeatureGroupDep.addModelingAttribute("Active",
				new ConfigurationAttribute("Active", "Boolean", true,
						"Is Active", true));
		syntaxFeatureGroupDep.addModelingAttribute("Visibility",
				new ConfigurationAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		syntaxFeatureGroupDep.addModelingAttribute("Required",
				new ConfigurationAttribute("Required", "Boolean", true,
						"Is Required", false));
		syntaxFeatureGroupDep.addModelingAttribute("Allowed",
				new ConfigurationAttribute("Allowed", "Boolean", false,
						"Is Allowed", true));
		syntaxFeatureGroupDep.addModelingAttribute("RequiredLevel",
				new ConfigurationAttribute("RequiredLevel", "Integer", false,
						"Required Level", 0)); // TODO define domain
												// or Enum
												// Level

		syntaxFeatureGroupDep.addModelingAttribute("ForcedSatisfied",
				new ConfigurationAttribute("ForcedSatisfied", "Boolean", false,
						"Force Satisfaction", false));
		syntaxFeatureGroupDep.addModelingAttribute("ForcedSelected",
				new ConfigurationAttribute("ForcedSelected", "Boolean", false,
						"Force Selection", false));

		syntaxFeatureGroupDep.addDisPropEditableAttribute("01#" + "Active");
		// syntaxFeatureGroupDep.addDisPropEditableAttribute("02#" +
		// "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		syntaxFeatureGroupDep.addDisPropEditableAttribute("03#" + "Allowed"
				+ "#" + "Active" + "#==#" + "true" + "#" + "false");
		syntaxFeatureGroupDep.addDisPropEditableAttribute("04#" + "Required"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");
		syntaxFeatureGroupDep.addDisPropEditableAttribute("05#"
				+ "RequiredLevel" + "#" + "Required" + "#==#" + "true" + "#"
				+ "0");
		syntaxFeatureGroupDep.addDisPropEditableAttribute("10#"
				+ "ForcedSatisfied" + "#" + "Allowed" + "#==#" + "true" + "#"
				+ "false");
		syntaxFeatureGroupDep.addDisPropEditableAttribute("15#"
				+ "ForcedSelected" + "#" + "Allowed" + "#==#" + "true" + "#"
				+ "false");

		syntaxFeatureGroupDep.addDisPropVisibleAttribute("01#" + "Active");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("02#" + "Visibility");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("03#" + "Allowed");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("04#" + "Required");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("05#"
				+ "RequiredLevel" + "#" + "Required" + "#==#" + "true");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("10#"
				+ "ForcedSatisfied");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("15#"
				+ "ForcedSelected");

		// Simulation attributes

		syntaxFeatureGroupDep.addModelingAttribute("InitialRequiredLevel",
				new SimulationAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		syntaxFeatureGroupDep.addModelingAttribute("SimRequiredLevel",
				new SimulationAttribute("SimRequiredLevel", "Integer", false,
						"Required Level", false));
		syntaxFeatureGroupDep.addModelingAttribute("ValidationRequiredLevel",
				new SimulationAttribute("ValidationRequiredLevel", "Integer",
						false, "Required Level by Validation", false));
		syntaxFeatureGroupDep.addModelingAttribute("SimRequired",
				new SimulationAttribute("SimRequired", "Boolean", false,
						"Required", false));

		syntaxFeatureGroupDep.addModelingAttribute("Satisfied",
				new SimulationAttribute("Satisfied", "Boolean", false,
						"Satisfied", false));
		syntaxFeatureGroupDep.addModelingAttribute("AlternativeSatisfied",
				new SimulationAttribute("AlternativeSatisfied", "Boolean",
						false, "Satisfied by Alternatives", false));
		syntaxFeatureGroupDep.addModelingAttribute("ValidationSatisfied",
				new SimulationAttribute("ValidationSatisfied", "Boolean",
						false, "Satisfied by Validation", false));
		syntaxFeatureGroupDep.addModelingAttribute("SatisfiedLevel",
				new SimulationAttribute("SatisfiedLevel", "Integer", false,
						"Satisficing Level", false));
		syntaxFeatureGroupDep.addModelingAttribute("NoSatisfactionConflict",
				new SimulationAttribute("NoSatisfactionConflict", "Boolean",
						false, "No Satisfaction Conflict", true));

		syntaxFeatureGroupDep.addModelingAttribute("Selected",
				new SimulationAttribute("Selected", "Boolean", false,
						"Selected", false));
		syntaxFeatureGroupDep.addModelingAttribute("PreferredSelected",
				new SimulationAttribute("PreferredSelected", "Boolean", false,
						"Select by Preferred", true));
		syntaxFeatureGroupDep.addModelingAttribute("ValidationSelected",
				new SimulationAttribute("ValidationSelected", "Boolean", false,
						"Selected by Validation", false));
		syntaxFeatureGroupDep.addModelingAttribute("SolverSelected",
				new SimulationAttribute("SolverSelected", "Boolean", false,
						"Selected by Solver", false));

		syntaxFeatureGroupDep.addModelingAttribute("Optional",
				new SimulationAttribute("Optional", "Boolean", false,
						"Is Optional", false));

		syntaxFeatureGroupDep.addModelingAttribute("SimAllowed",
				new SimulationAttribute("SimAllowed", "Boolean", false,
						"Is Allowed", true));

		syntaxFeatureGroupDep.addDisPropVisibleAttribute("01#" + "SimRequired");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("03#"
				+ "SimRequiredLevel");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("05#"
				+ "InitialRequiredLevel");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("07#"
				+ "ValidationRequiredLevel");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("09#" + "Selected");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("11#"
				+ "PreferredSelected");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("13#"
				+ "ValidationSelected");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("15#"
				+ "SolverSelected");

		syntaxFeatureGroupDep.addDisPropVisibleAttribute("02#" + "Satisfied");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("04#"
				+ "AlternativeSatisfied");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("06#"
				+ "ValidationSatisfied");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("08#"
				+ "SatisfiedLevel");
		syntaxFeatureGroupDep.addDisPropVisibleAttribute("10#"
				+ "NoSatisfactionConflict");

		syntaxFeatureGroupDep.addDisPropVisibleAttribute("12#" + "SimAllowed");

		syntaxFeatureGroupDep.addDisPropVisibleAttribute("14#" + "Optional");

		IntSemanticConcept semHardConcept = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("HC"))
				.getEditableSemanticElement();
		MetaConcept syntaxVariabilityArtifact = new MetaConcept("VA", false,
				"VariabilityArtifact", null, "", 0, 0, null, true, null, 3,
				true, semHardConcept);
		syntaxVariabilityArtifact.addModelingAttribute("name", "String", false,
				"Name", "");

		syntaxVariabilityArtifact.addDisPanelVisibleAttribute("03#" + "name");

		syntaxVariabilityArtifact.addDisPropEditableAttribute("03#" + "name");

		syntaxVariabilityArtifact.addDisPropVisibleAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxVariabilityArtifact);

		variabilityInstVertex.put("VA", new InstConcept(
				syntaxVariabilityArtifact, metaElementConcept));

		IntSemanticConcept semGoal = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("G"))
				.getEditableSemanticElement();
		MetaConcept syntaxTopGoal = new MetaConcept("TopGoal", true,
				"Top Goal", "refasgoal", "Defines a top goal of the system"
						+ " from the stakeholder perspective that can be"
						+ " satisfied with a clear cut condition", 100, 40,
				"/com/variamos/gui/refas/editor/images/goal.png", true,
				Color.BLUE.toString(), 3, true, semGoal);
		syntaxTopGoal.addMetaExtendRelation(syntaxVariabilityArtifact, false);

		syntaxMetaView.addConcept(syntaxTopGoal);
		instVertex = new InstConcept(syntaxTopGoal, metaElementConcept);
		variabilityInstVertex.put("TopGoal", instVertex);
		instView.addInstVertex(instVertex);

		MetaConcept syntaxGeneralGoal = new MetaConcept("GeneralGoal", true,
				"General Goal", "refasgoal", "Defines a general goal of the"
						+ " system from the stakeholder perspective that can"
						+ " be satisfied with a clear cut condition", 100, 40,
				"/com/variamos/gui/refas/editor/images/goal.png", true,
				Color.BLUE.toString(), 2, true, semGoal);
		syntaxGeneralGoal.addMetaExtendRelation(syntaxVariabilityArtifact,
				false);

		syntaxMetaView.addConcept(syntaxGeneralGoal);
		instVertex = new InstConcept(syntaxGeneralGoal, metaElementConcept);
		variabilityInstVertex.put("GeneralGoal", instVertex);
		instView.addInstVertex(instVertex);

		IntSemanticConcept semOperationalization = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("OPER"))
				.getEditableSemanticElement();
		MetaConcept sOperationalization = new MetaConcept("OPER", true,
				"Operationalization", "refasoper",
				"An operationalization allows"
						+ " the partial or complete satisfaction of a goal or"
						+ " another operationalization. If"
						+ " the operationalizations defined is satisfied,"
						+ " according to the defined relation, the goal"
						+ " associated will be also satisfied", 100, 40,
				"/com/variamos/gui/refas/editor/images/operational.png", true,
				Color.BLUE.toString(), 2, true, semOperationalization);
		sOperationalization.addMetaExtendRelation(syntaxVariabilityArtifact,
				false);

		syntaxMetaView.addConcept(sOperationalization);
		InstVertex instVertexOper = new InstConcept(sOperationalization, metaElementConcept);
		variabilityInstVertex.put("OPER", instVertexOper);
		instView.addInstVertex(instVertexOper);

		IntSemanticConcept semAssumption = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("AS"))
				.getEditableSemanticElement();

		MetaConcept syntaxAssumption = new MetaConcept("Assumption", true,
				"semanticAssumption", "refassassump", "An assumption is a"
						+ " condition that should me truth for the goal or"
						+ " operationalization to be satisfied", 100, 40,
				"/com/variamos/gui/refas/editor/images/assump.png", true,
				Color.WHITE.toString(), 1, true, semAssumption);
		syntaxAssumption
				.addMetaExtendRelation(syntaxVariabilityArtifact, false);

		syntaxMetaView.addConcept(syntaxAssumption);
		instVertex = new InstConcept(syntaxAssumption, metaElementConcept);
		variabilityInstVertex.put("Assumption", instVertex);
		instView.addInstVertex(instVertex);

		// Direct Hard Relations

		List<IntDirectSemanticEdge> directHardSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		IntDirectSemanticEdge directHardHardSemanticEdge = (IntDirectSemanticEdge) getSemanticRefas()
				.getConstraintInstEdge("HardHardDirectEdge")
				.getEditableSemanticElement();
		IntDirectSemanticEdge directGoalGoalSemanticEdge = (IntDirectSemanticEdge) getSemanticRefas()
				.getConstraintInstEdge("GoalGoalDirectEdge")
				.getEditableSemanticElement();
		IntDirectSemanticEdge directOperGoalSemanticEdge = (IntDirectSemanticEdge) getSemanticRefas()
				.getConstraintInstEdge("OperGoalDirectEdge")
				.getEditableSemanticElement();
		IntDirectSemanticEdge directOperOperSemanticEdge = (IntDirectSemanticEdge) getSemanticRefas()
				.getConstraintInstEdge("OperOperDirectEdge")
				.getEditableSemanticElement();

		directHardSemanticEdges.add(directHardHardSemanticEdge);
		directHardSemanticEdges.add(directGoalGoalSemanticEdge);
		directHardSemanticEdges.add(directOperGoalSemanticEdge);
		directHardSemanticEdges.add(directOperOperSemanticEdge);

		MetaDirectRelation metaHardEdge = new MetaDirectRelation(
				"HardRelation", true, "HardRelation", "ploptional",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cartinalities", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxVariabilityArtifact, syntaxVariabilityArtifact,
				directHardSemanticEdges, allSGDirectRelation);
		syntaxVariabilityArtifact.addMetaEdgeAsOrigin(
				syntaxVariabilityArtifact, metaHardEdge);
		constraintInstEdges.put("HardRelation", new InstEdge(metaHardEdge));

		// Group Hard Relations

		IntSemanticGroupDependency semanticGoalGoalGroupRelation = (IntSemanticGroupDependency) ((InstGroupDependency) this
				.getSemanticRefas().getVertex("GoalGoalGroupRel"))
				.getEditableSemanticElement();
		IntSemanticGroupDependency semanticOperGoalGroupRelation = (IntSemanticGroupDependency) ((InstGroupDependency) this
				.getSemanticRefas().getVertex("OperGoalGroupRel"))
				.getEditableSemanticElement();
		IntSemanticGroupDependency semanticOperOperGroupRelation = (IntSemanticGroupDependency) ((InstGroupDependency) this
				.getSemanticRefas().getVertex("OperOperGroupRel"))
				.getEditableSemanticElement();
		IntSemanticGroupDependency semanticHardHardGroupRelation = (IntSemanticGroupDependency) ((InstGroupDependency) this
				.getSemanticRefas().getVertex("HardHardGroupRel"))
				.getEditableSemanticElement();

		List<IntSemanticGroupDependency> semanticRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticRelations.add(semanticGoalGoalGroupRelation);
		semanticRelations.add(semanticOperGoalGroupRelation);
		semanticRelations.add(semanticOperOperGroupRelation);
		semanticRelations.add(semanticHardHardGroupRelation);

		MetaGroupDependency syntaxGroupDependency = new MetaGroupDependency(
				"HardGroupDep", true, "HardGroupDep", "plgroup",
				"Group relation between"
						+ " hard concepts. Defines different types of"
						+ " relations and cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);

		syntaxMetaView.addConcept(syntaxGroupDependency);
		instVertex = new InstGroupDependency(syntaxGroupDependency,
				metaElementConcept);
		variabilityInstVertex.put("HardGroupDep", instVertex);
		instView.addInstVertex(instVertex);

		syntaxGroupDependency.addModelingAttribute("Active",
				new ConfigurationAttribute("Active", "Boolean", true,
						"Is Active", true));
		syntaxGroupDependency.addModelingAttribute("Visibility",
				new ConfigurationAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		syntaxGroupDependency.addModelingAttribute("Required",
				new ConfigurationAttribute("Required", "Boolean", true,
						"Is Required", false));
		syntaxGroupDependency.addModelingAttribute("Allowed",
				new ConfigurationAttribute("Allowed", "Boolean", false,
						"Is Allowed", true));
		syntaxGroupDependency.addModelingAttribute("RequiredLevel",
				new ConfigurationAttribute("RequiredLevel", "Integer", false,
						"Required Level", 0)); // TODO define domain
												// or Enum
												// Level
		syntaxGroupDependency.addModelingAttribute("ForcedSatisfied",
				new ConfigurationAttribute("ForcedSatisfied", "Boolean", false,
						"Force Satisfaction", false));
		syntaxGroupDependency.addModelingAttribute("ForcedSelected",
				new ConfigurationAttribute("ForcedSelected", "Boolean", false,
						"Force Selection", false));

		syntaxGroupDependency.addDisPropEditableAttribute("01#" + "Active");
		// syntaxGroupDependency.addDisPropEditableAttribute("02#" +
		// "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		syntaxGroupDependency.addDisPropEditableAttribute("03#" + "Allowed"
				+ "#" + "Active" + "#==#" + "true" + "#" + "false");
		syntaxGroupDependency.addDisPropEditableAttribute("04#" + "Required"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");
		syntaxGroupDependency.addDisPropEditableAttribute("05#"
				+ "RequiredLevel" + "#" + "Required" + "#==#" + "true" + "#"
				+ "0");
		syntaxGroupDependency.addDisPropEditableAttribute("10#"
				+ "ForcedSatisfied" + "#" + "Allowed" + "#==#" + "true" + "#"
				+ "false");
		syntaxGroupDependency.addDisPropEditableAttribute("15#"
				+ "ForcedSelected" + "#" + "Allowed" + "#==#" + "true" + "#"
				+ "false");

		syntaxGroupDependency.addDisPropVisibleAttribute("01#" + "Active");
		syntaxGroupDependency.addDisPropVisibleAttribute("02#" + "Visibility");
		syntaxGroupDependency.addDisPropVisibleAttribute("03#" + "Allowed");
		syntaxGroupDependency.addDisPropVisibleAttribute("04#" + "Required");
		syntaxGroupDependency.addDisPropVisibleAttribute("05#"
				+ "RequiredLevel" + "#" + "Required" + "#==#" + "true");
		syntaxGroupDependency.addDisPropVisibleAttribute("10#"
				+ "ForcedSatisfied");
		syntaxGroupDependency.addDisPropVisibleAttribute("15#"
				+ "ForcedSelected");

		// Simulation attributes

		syntaxGroupDependency.addModelingAttribute("InitialRequiredLevel",
				new SimulationAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		syntaxGroupDependency.addModelingAttribute("SimRequiredLevel",
				new SimulationAttribute("SimRequiredLevel", "Integer", false,
						"Required Level", false));
		syntaxGroupDependency.addModelingAttribute("ValidationRequiredLevel",
				new SimulationAttribute("ValidationRequiredLevel", "Integer",
						false, "Required Level by Validation", false));
		syntaxGroupDependency.addModelingAttribute("SimRequired",
				new SimulationAttribute("SimRequired", "Boolean", false,
						"***Required***", false));

		syntaxGroupDependency.addModelingAttribute("Satisfied",
				new SimulationAttribute("Satisfied", "Boolean", false,
						"***Satisfied***", false));
		syntaxGroupDependency.addModelingAttribute("AlternativeSatisfied",
				new SimulationAttribute("AlternativeSatisfied", "Boolean",
						false, "Satisfied by Alternatives", false));
		syntaxGroupDependency.addModelingAttribute("ValidationSatisfied",
				new SimulationAttribute("ValidationSatisfied", "Boolean",
						false, "Satisfied by Validation", false));
		syntaxGroupDependency.addModelingAttribute("SatisfiedLevel",
				new SimulationAttribute("SatisfiedLevel", "Integer", false,
						"Satisficing Level", false));
		syntaxGroupDependency.addModelingAttribute("NoSatisfactionConflict",
				new SimulationAttribute("NoSatisfactionConflict", "Boolean",
						false, "No Satisfaction Conflict", true));

		syntaxGroupDependency.addModelingAttribute("Selected",
				new SimulationAttribute("Selected", "Boolean", false,
						"***Selected***", false));
		syntaxGroupDependency.addModelingAttribute("PreferredSelected",
				new SimulationAttribute("PreferredSelected", "Boolean", false,
						"Select by Preferred", true));
		syntaxGroupDependency.addModelingAttribute("ValidationSelected",
				new SimulationAttribute("ValidationSelected", "Boolean", false,
						"Selected by Validation", false));
		syntaxGroupDependency.addModelingAttribute("SolverSelected",
				new SimulationAttribute("SolverSelected", "Boolean", false,
						"Selected by Solver", false));

		syntaxGroupDependency.addModelingAttribute("Optional",
				new SimulationAttribute("Optional", "Boolean", false,
						"*Is Optional*", false));

		syntaxGroupDependency.addModelingAttribute("SimAllowed",
				new SimulationAttribute("SimAllowed", "Boolean", false,
						"Is Allowed", true));

		syntaxGroupDependency.addDisPropVisibleAttribute("01#" + "SimRequired");
		syntaxGroupDependency.addDisPropVisibleAttribute("03#"
				+ "SimRequiredLevel");
		syntaxGroupDependency.addDisPropVisibleAttribute("05#"
				+ "InitialRequiredLevel");
		syntaxGroupDependency.addDisPropVisibleAttribute("07#"
				+ "ValidationRequiredLevel");
		syntaxGroupDependency.addDisPropVisibleAttribute("09#" + "Selected");
		syntaxGroupDependency.addDisPropVisibleAttribute("11#"
				+ "PreferredSelected");
		syntaxGroupDependency.addDisPropVisibleAttribute("13#"
				+ "ValidationSelected");
		syntaxGroupDependency.addDisPropVisibleAttribute("15#"
				+ "SolverSelected");

		syntaxGroupDependency.addDisPropVisibleAttribute("02#" + "Satisfied");
		syntaxGroupDependency.addDisPropVisibleAttribute("04#"
				+ "AlternativeSatisfied");
		syntaxGroupDependency.addDisPropVisibleAttribute("06#"
				+ "ValidationSatisfied");
		syntaxGroupDependency.addDisPropVisibleAttribute("08#"
				+ "SatisfiedLevel");
		syntaxGroupDependency.addDisPropVisibleAttribute("10#"
				+ "NoSatisfactionConflict");

		syntaxGroupDependency.addDisPropVisibleAttribute("12#" + "SimAllowed");

		syntaxGroupDependency.addDisPropVisibleAttribute("14#" + "Optional");

		// *************************---------------****************************
		// Softgoals model

		syntaxMetaView = new MetaView("SoftGoals", "Soft Goals Model",
				"Soft Goals Palette", 2);
		instView = new InstView(syntaxMetaView);
		instViews.add(instView);

		IntSemanticConcept semSoftgoal = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SG"))
				.getEditableSemanticElement();
		MetaConcept syntaxAbsSoftGoal = new MetaConcept("Softgoal", false,
				"Softgoal", "", null, 0, 0, null, true, null, 3, true,
				semSoftgoal);

		syntaxAbsSoftGoal.addModelingAttribute("name", "String", false, "Name",
				"");
		syntaxAbsSoftGoal.addDisPanelVisibleAttribute("03#" + "name");

		syntaxAbsSoftGoal.addDisPropEditableAttribute("03#" + "name");
		syntaxAbsSoftGoal.addDisPropVisibleAttribute("03#" + "name");

		syntaxMetaView.addConcept(syntaxAbsSoftGoal);
		instVertex = new InstConcept(syntaxAbsSoftGoal, metaElementConcept);
		variabilityInstVertex.put("Softgoal", instVertex);
		instView.addInstVertex(instVertex);

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
						+ " on the SG", 100, 40,
				"/com/variamos/gui/refas/editor/images/softgoal.png", true,
				Color.WHITE.toString(), 3, true, semSoftgoal);

		syntaxTopSoftGoal.addMetaExtendRelation(syntaxAbsSoftGoal, false);

		syntaxMetaView.addConcept(syntaxTopSoftGoal);
		instVertex = new InstConcept(syntaxTopSoftGoal, metaElementConcept);
		variabilityInstVertex.put("TopSoftgoal", instVertex);
		instView.addInstVertex(instVertex);

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
						+ " on the SG", 100, 40,
				"/com/variamos/gui/refas/editor/images/softgoal.png", true,
				Color.WHITE.toString(), 1, true, semSoftgoal);

		syntaxGeneralSoftGoal.addMetaExtendRelation(syntaxAbsSoftGoal, false);
		syntaxMetaView.addConcept(syntaxGeneralSoftGoal);
		instVertex = new InstConcept(syntaxGeneralSoftGoal, metaElementConcept);
		variabilityInstVertex.put("GeneralSSoftgoal", instVertex);
		instView.addInstVertex(instVertex);

		// Direct Soft relation

		IntDirectSemanticEdge directSGSGSemEdge = (IntDirectSemanticEdge) getSemanticRefas()
				.getConstraintInstEdge("SGSGDirectEdge")
				.getEditableSemanticElement();

		List<IntDirectSemanticEdge> directSoftSemanticEdges = new ArrayList<IntDirectSemanticEdge>();

		directSoftSemanticEdges.add(directSGSGSemEdge);

		MetaDirectRelation metaSoftEdge = new MetaDirectRelation(
				"Soft Relation", true, "Soft Relation", "ploptional",
				"Direct relation between two soft concepts. Defines"
						+ " different types of relations and cartinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAbsSoftGoal, syntaxAbsSoftGoal, directSoftSemanticEdges,
				directSGSGSemEdge.getSemanticRelationTypes());
		syntaxAbsSoftGoal.addMetaEdgeAsOrigin(syntaxAbsSoftGoal, metaSoftEdge);
		constraintInstEdges.put("Soft Relation", new InstEdge(metaSoftEdge));

		IntSemanticGroupDependency semanticSGSGGroupRelation = (IntSemanticGroupDependency) ((InstGroupDependency) this
				.getSemanticRefas().getVertex("SGSGGroupRel"))
				.getEditableSemanticElement();

		semanticRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticRelations.add(semanticSGSGGroupRelation);

		// Group soft relation

		syntaxGroupDependency = new MetaGroupDependency("SoftgoalGroupDep",
				true, "SoftgoalGroupDep", "plgroup",
				"Direct relation between soft"
						+ " concepts. Defines different types of relations"
						+ " and cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);

		syntaxMetaView.addConcept(syntaxGroupDependency);
		instVertex = new InstGroupDependency(syntaxGroupDependency,
				metaElementOverTwo);
		variabilityInstVertex.put("SoftgoalGroupDep", instVertex);
		instView.addInstVertex(instVertex);

		// *************************---------------****************************
		// Context model

		syntaxMetaView = new MetaView("Context", "Context Model",
				"Context Palette", 3);
		instView = new InstView(syntaxMetaView);
		instViews.add(instView);
		// syntaxMetaView.addConcept(syntaxVariable);
		IntSemanticConcept semContextGroup = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("CG"))
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
		InstVertex instVertexCG = new InstConcept(syntaxContextGroup, metaElementOverTwo);
		variabilityInstVertex.put("CG", instVertexCG);
		instView.addInstVertex(instVertexCG);

		IntSemanticConcept semVariable = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("VAR"))
				.getEditableSemanticElement();
		MetaConcept syntaxAbsVariable = new MetaConcept("Variable", false,
				"Variable", "", null, 0, 0, null, true, null, 1, true,
				semVariable);

		instVertex = new InstConcept(syntaxAbsVariable, metaElementConcept);
		variabilityInstVertex.put("Variable", instVertex);
		instView.addInstVertex(instVertex);

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

		syntaxGlobalVariable.addMetaExtendRelation(syntaxAbsVariable, false);

		syntaxMetaView.addConcept(syntaxGlobalVariable);
		InstVertex instVertexGV = new InstConcept(syntaxGlobalVariable, metaElementConcept);
		variabilityInstVertex.put("GlobalVariable", instVertexGV);
		instView.addInstVertex(instVertexGV);

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

		syntaxLocalVariable.addMetaExtendRelation(syntaxAbsVariable, false);

		syntaxMetaView.addConcept(syntaxLocalVariable);
		InstVertex instVertexLV = new InstConcept(syntaxLocalVariable, metaElementConcept);
		variabilityInstVertex.put("LocalVariable", instVertexLV);
		instView.addInstVertex(instVertexLV);

		MetaEnumeration metaEnumeration = new MetaEnumeration("ME", true,
				"MetaEnumeration", "refasenumeration", "Allows the"
						+ " creation of user defined enumeration for"
						+ " variables", 100, 150,
				"/com/variamos/gui/refas/editor/images/assump.png", true, "",
				1, true);
		syntaxMetaView.addConcept(metaEnumeration);
		instVertex = new InstEnumeration(metaEnumeration, metaElementConcept);
		variabilityInstVertex.put("ME", instVertex);
		instView.addInstVertex(instVertex);

		MetaView syntaxMetaChildView = new MetaView("ContandModelEnum",
				"Context without Enumerations", "Context Palette", 1);
		InstView childView = new InstView(syntaxMetaChildView); 
		syntaxMetaView.addChildView(syntaxMetaChildView);
		instView.addChildView(childView);
		// syntaxMetaChildView.addConcept(metaEnumeration);
		syntaxMetaChildView.addConcept(syntaxContextGroup);
		childView.addInstVertex(instVertexCG);
		syntaxMetaChildView.addConcept(syntaxLocalVariable);
		childView.addInstVertex(instVertexLV);
		syntaxMetaChildView.addConcept(syntaxGlobalVariable);
		childView.addInstVertex(instVertexGV);

		// Direct variable relations

		IntDirectSemanticEdge directCVCGSemanticEdge = (IntDirectSemanticEdge) getSemanticRefas()
				.getConstraintInstEdge("CVCGDirectRel")
				.getEditableSemanticElement();

		List<IntDirectSemanticEdge> directCVCGSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		directCVCGSemanticEdges.add(directCVCGSemanticEdge);

		MetaDirectRelation metaVariableEdge = new MetaDirectRelation(
				"Variable To Context Relation", true,
				"Variable To Context Relation", "ploptional",
				"Associates a Variable" + " with the Context Group", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAbsVariable, syntaxContextGroup, directCVCGSemanticEdges,
				directCVCGSemanticEdge.getSemanticRelationTypes());
		syntaxAbsVariable.addMetaEdgeAsOrigin(syntaxContextGroup,
				metaVariableEdge);
		constraintInstEdges.put("Variable To Context Relation", new InstEdge(
				metaVariableEdge));

		MetaDirectRelation metaContextEdge = new MetaDirectRelation(
				"Context To Context Relation", true,
				"Context To Context Relation", "ploptional", "Associates a"
						+ " Context Group with other Context Group", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxContextGroup, syntaxContextGroup,
				directCVCGSemanticEdges,
				directCVCGSemanticEdge.getSemanticRelationTypes());
		syntaxContextGroup.addMetaEdgeAsOrigin(syntaxContextGroup,
				metaContextEdge);
		constraintInstEdges.put("Context To Context Relation", new InstEdge(
				metaVariableEdge));

		// *************************---------------****************************
		// SG Satisficing Model

		syntaxMetaView = new MetaView("SoftGoalsSatisficing",
				"SG Satisficing Model", "Soft Goals Satisficing Palette", 4);
		instView = new InstView(syntaxMetaView);
		instViews.add(instView);
		syntaxMetaView.addConcept(syntaxTopSoftGoal);
		syntaxMetaView.addConcept(syntaxGeneralSoftGoal);
		syntaxMetaView.addConcept(sOperationalization);
		instView.addInstVertex(instVertexOper);
		IntSemanticConcept semClaim = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("CL"))
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
		instVertex = new InstConcept(syntaxClaim, metaElementConcept);
		variabilityInstVertex.put("CL", instVertex);
		instView.addInstVertex(instVertex);

		IntSemanticConcept semSoftDependency = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("SD"))
				.getEditableSemanticElement();
		MetaConcept syntaxSoftDependency = new MetaConcept(
				"SD",
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
		instVertex = new InstConcept(syntaxSoftDependency, metaElementConcept);
		variabilityInstVertex.put("SD", instVertex);
		instView.addInstVertex(instVertex);

		IntSemanticGroupDependency semanticOperClaimGroupRelation = (IntSemanticGroupDependency) ((InstGroupDependency) this
				.getSemanticRefas().getVertex("OperClaimGroupRel"))
				.getEditableSemanticElement();

		semanticRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticRelations.add(semanticOperClaimGroupRelation);

		syntaxGroupDependency = new MetaGroupDependency(
				"OperClaimGD",
				true,
				"OperClaimGD",
				"plgroup",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " expected on the softgoal in case the Claim is satisfied",
				20, 20, "/com/variamos/gui/pl/editor/images/plgroup.png",
				false, "white", 1, false, semanticRelations);
		syntaxMetaView.addConcept(syntaxGroupDependency);
		instVertex = new InstGroupDependency(syntaxGroupDependency,
				metaElementOverTwo);
		variabilityInstVertex.put("OperClaimGD", instVertex);
		instView.addInstVertex(instVertex);

		IntDirectSemanticEdge directSDSGSemanticEdge = (IntDirectSemanticEdge) getSemanticRefas()
				.getConstraintInstEdge("SDSGDirectEdge")
				.getEditableSemanticElement();

		List<IntDirectSemanticEdge> directSDSGSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		directSDSGSemanticEdges.add(directSDSGSemanticEdge);

		MetaDirectRelation metaSDSGEdge = new MetaDirectRelation(
				"SDSGRelation",
				true,
				"SDSGRelation",
				"ploptional",
				"Express the relation between"
						+ " the SD and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxSoftDependency, syntaxAbsSoftGoal,
				directSDSGSemanticEdges, directSDSGSemanticEdge
						.getSemanticRelationTypes());
		syntaxSoftDependency.addMetaEdgeAsOrigin(syntaxAbsSoftGoal,
				metaSDSGEdge);

		constraintInstEdges.put("SDSGRelation", new InstEdge(metaSDSGEdge));

		IntDirectSemanticEdge directClaimSGSemanticEdge = (IntDirectSemanticEdge) getSemanticRefas()
				.getConstraintInstEdge("ClaimSGDirectEdge")
				.getEditableSemanticElement();

		List<IntDirectSemanticEdge> directClaimSGSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		directClaimSGSemanticEdges.add(directClaimSGSemanticEdge);

		MetaDirectRelation metaClaimSGEdge = new MetaDirectRelation(
				"Claim-Softgoal Relation",
				true,
				"Claim-Softgoal Relation",
				"ploptional",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxClaim, syntaxAbsSoftGoal, directClaimSGSemanticEdges,
				directClaimSGSemanticEdge.getSemanticRelationTypes());
		syntaxClaim.addMetaEdgeAsOrigin(syntaxAbsSoftGoal, metaClaimSGEdge);

		constraintInstEdges.put("Claim-Softgoal Relation", new InstEdge(
				metaClaimSGEdge));

		// *************************---------------****************************
		// Assets model

		syntaxMetaView = new MetaView("Assets", "Assets General Model",
				"Assets Palette", 5);
		 instView = new InstView(syntaxMetaView);
		instViews.add(instView);
		syntaxMetaView.addConcept(sOperationalization);
		instView.addInstVertex(instVertexOper);
		IntSemanticConcept semAsset = (IntSemanticConcept) ((InstConcept) this
				.getSemanticRefas().getVertex("AS"))
				.getEditableSemanticElement();
		MetaConcept syntaxAsset = new MetaConcept("AS", true, "Asset",
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
		syntaxAsset.addDisPanelVisibleAttribute("03#" + "name");
		syntaxAsset.addDisPropEditableAttribute("03#" + "name");
		syntaxAsset.addDisPropVisibleAttribute("03#" + "name");
		syntaxMetaView.addConcept(syntaxAsset);

		syntaxMetaView.addConcept(sOperationalization);
		InstVertex instVertexAsset = new InstConcept(syntaxAsset, metaElementConcept);
		variabilityInstVertex.put("AS", instVertexAsset);
		instView.addInstVertex(instVertexAsset);

		syntaxMetaChildView = new MetaView("FunctionalAssets",
				"Functional Assets Relations", "Assets Palette", 1);
		childView = new InstView(syntaxMetaChildView);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		instView.addChildView(childView);
		syntaxMetaChildView.addConcept(sOperationalization);
		childView.addInstVertex(instVertexOper);
		syntaxMetaChildView.addConcept(syntaxAsset);
		childView.addInstVertex(instVertexAsset);

		syntaxMetaChildView = new MetaView("StructuralAssets",
				"Structural Assets Relations", "Assets Palette", 2);
		childView = new InstView(syntaxMetaChildView);
		syntaxMetaView.addChildView(syntaxMetaChildView);
		instView.addChildView(childView);
		syntaxMetaChildView.addConcept(sOperationalization);
		childView.addInstVertex(instVertexOper);
		syntaxMetaChildView.addConcept(syntaxAsset);
		childView.addInstVertex(instVertexAsset);
		
		IntSemanticGroupDependency semanticAssetOperGroupRelation = (IntSemanticGroupDependency) ((InstGroupDependency) this
				.getSemanticRefas().getVertex("AssetOperGroupRel"))
				.getEditableSemanticElement();

		semanticRelations = new ArrayList<IntSemanticGroupDependency>();
		semanticRelations.add(semanticAssetOperGroupRelation);

		syntaxGroupDependency = new MetaGroupDependency("AssetOperGroupDep",
				true, "AssetOperGroupDep", "plgroup",
				"Represents the implementation "
						+ "of an operationalization by a group of assets", 20,
				20, "/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticRelations);

		syntaxMetaView.addConcept(syntaxGroupDependency);
		instVertex = new InstGroupDependency(syntaxGroupDependency,
				metaElementOverTwo);
		variabilityInstVertex.put("Asset-Oper GroupDep",instVertex);
		instView.addInstVertex(instVertex);
		IntDirectSemanticEdge directAssetOperSemanticEdge = (IntDirectSemanticEdge) getSemanticRefas()
				.getConstraintInstEdge("AssetOperDirectEdge")
				.getEditableSemanticElement();
		List<IntDirectSemanticEdge> directAssetOperSemanticEdges = new ArrayList<IntDirectSemanticEdge>();
		directAssetOperSemanticEdges.add(directAssetOperSemanticEdge);

		MetaDirectRelation metaOperEdge = new MetaDirectRelation(
				"Asset To Oper Relation", true, "Asset To Oper Relation",
				"ploptional", "Represents the "
						+ "implementation of an operationzalization by an"
						+ " asset", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				syntaxAsset, sOperationalization, directAssetOperSemanticEdges,
				directAssetOperSemanticEdge.getSemanticRelationTypes());

		// syntaxMetaView.addConcept(metaOperEdge);
		syntaxAsset.addMetaEdgeAsOrigin(sOperationalization, metaOperEdge);
		constraintInstEdges.put("Asset To Oper Relation", new InstEdge(
				metaOperEdge));

	}

	public PerspectiveType getPerspectiveType() {
		return perspectiveType;
	}

	public void setInstGroupDependencies(
			Map<String, InstGroupDependency> instGroupDependencies) {
		this.instGroupDependencies = instGroupDependencies;
	}

	public void setInstViews(List<InstView> instViews) {
		this.instViews = instViews;
	}

	public List<InstView> getInstViews() {
		return instViews;
	}

}
