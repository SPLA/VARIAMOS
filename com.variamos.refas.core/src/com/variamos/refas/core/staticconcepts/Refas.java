package com.variamos.refas.core.staticconcepts;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.cfm.common.AbstractModel;
import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.VariabilityElement;
import com.mxgraph.util.mxResources;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodel.InstEnumeration;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;

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

	/**
	 * Adds required resources for i18n
	 */
	static {
		try {
			mxResources.add("com/mxgraph/examples/swing/resources/editor");
		} catch (Exception e) {
			// ignore
		}
	}

	protected Map<String, VariabilityElement> vElements;
	protected Map<String, Constraint> constraints;

	private Map<String, InstVertex> instElements;
	private Map<String, InstEdge> instEdges; // Methods missing to handle

	protected String fileName;

	public Refas() {
		vElements = new HashMap<String, VariabilityElement>();

		instElements = new HashMap<>();
		instEdges = new HashMap<>();

		fileName = "";
	}

	public Collection<Constraint> getConstraints() {
		return constraints.values();
	}

	public void setConstraints(Map<String, Constraint> constraints) {
		this.constraints = constraints;
	}

	public Collection<VariabilityElement> getVariabilityElements() {
		return vElements.values();
	}

	public void putInstElement(InstVertex element) {
		instElements.put(element.getIdentifier(), element);
	}

	public void putInstEdge(InstEdge element) {
		instEdges.put(element.getIdentifier(), element);
	}

	public String addNewInstElement(InstVertex element) {
		String id = getNextInstElementId(element);
		InstVertex varElement = (InstVertex) element;
		varElement.setIdentifier(id);
		varElement.setInstAttribute("name", id);
		instElements.put(id, element);
		return id;
	}

	public String addNewInstEdge(InstEdge directRelation) {
		String id = getNextInstEdgeId(directRelation);
		directRelation.setIdentifier(id);
		instEdges.put(id, directRelation);
		return id;
	}

	private String getNextInstElementId(InstVertex element) {

		int id = 1;
		String classId = null;
		if (element instanceof InstConcept)
			classId = ((InstConcept) element).getMetaVertexIdentifier();
		else {
			if (element instanceof InstEnumeration)
				classId = ((InstEnumeration) element)
						.getMetaVertexIdentifier();
			else
				classId = ((InstGroupDependency) element)
						.getMetaVertexIdentifier();
		}

		while (instElements.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextInstEdgeId(InstEdge element) {

		int id = 1;
		String classId = null;
		classId = MetaEdge.getClassId();

		while (instEdges.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	public Map<String, InstVertex> getElements() {
		return instElements;
	}

	public void setElements(Map<String, InstVertex> elements) {
		this.instElements = elements;
	}

	public VariabilityElement getVariabilityElement(String string) {
		return vElements.get(string);
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

}
