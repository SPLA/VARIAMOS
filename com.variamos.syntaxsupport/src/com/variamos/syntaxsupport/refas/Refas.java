package com.variamos.syntaxsupport.refas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cfm.common.AbstractModel;
import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.VariabilityElement;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstEnumeration;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstVertex;

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
/**
 * @author jcmunoz
 *
 */
public class Refas extends AbstractModel {

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
	private Map<String, InstEdge> constraintInstEdges; // TODO move relations to
														// groupdep and claims
														// to otherEDges
	/**
	 * 
	 */
	private Map<String, InstEdge> otherInstEdges; //
	/**
	 * 
	 */
	protected String fileName;

	public Refas() {
		variabilityInstVertex = new HashMap<String, InstVertex>();
		instGroupDependencies = new HashMap<String, InstGroupDependency>();
		otherInstVertex = new HashMap<String, InstVertex>();

		constraintInstEdges = new HashMap<String, InstEdge>();
		otherInstEdges = new HashMap<String, InstEdge>();

		fileName = "";
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

	public void putOtheInstEdge(InstEdge element) {
		otherInstEdges.put(element.getIdentifier(), element);
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
		otherInstEdges.put(id, directRelation);
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
		return getFileName();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String name) {
		this.fileName = name;
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
	
	public com.variamos.syntaxsupport.metamodel.InstElement getElement(String vertexId) {
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

	public Map<String,InstVertex> getConstraintVertex() {
		Map<String,InstVertex> out = new HashMap<String,InstVertex>();
		out.putAll(variabilityInstVertex);
		out.putAll(instGroupDependencies);
		return out;
	}


}
