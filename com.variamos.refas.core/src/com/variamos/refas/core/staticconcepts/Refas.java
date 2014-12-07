package com.variamos.refas.core.staticconcepts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.cfm.common.AbstractModel;
import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.VariabilityElement;
import com.mxgraph.util.mxResources;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 * Initially based on ProductLine class
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
	
	private Map<String, InstElement> instElements;
	private Map<String, InstEdge> instDirectRelations; // Methods missing to handle
	
	protected String name;

	public Refas() {
		vElements = new HashMap<String, VariabilityElement>();
		
		instElements = new HashMap<>();
		instDirectRelations= new HashMap<>();
		
		name = "";
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

	public String addElement(InstElement element) {
		String id = getNextElementId(element);
		element.setIdentifier(id);
		instElements.put(id, element);
		return id;

	}
	

	public String addDirectRelation(InstEdge directRelation) {
		String id = getNextDirectRelationId(directRelation);
		directRelation.setIdentifier(id);		
		instDirectRelations.put(id, directRelation);
		return id;

	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String addInstConceptElement(InstElement element) {
		String id = getNextElementId(element);
		InstElement varElement = (InstElement) element;
		varElement.setIdentifier(id);
		varElement.setInstAttribute("name", "<<new>>");
		instElements.put(id, element);
		return id;
	}

	private String getNextElementId(InstElement element) {

		int id = 1;
		String classId = null;
		if (element instanceof InstConcept)
			classId = ((InstConcept) element).getMetaConceptIdentifier();
		else
			classId = ((InstGroupDependency) element)
					.getMetaGroupDependencyIdentifier();

		while (instElements.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}
	
	private String getNextDirectRelationId(InstEdge element) {

		int id = 1;
		String classId = null;
		classId = element.getMetaEdgeIdentifier();
		
		while (instDirectRelations.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}
	
	public Map<String, InstElement> getElements() {
		return instElements;
	}

	public void setElements(Map<String, InstElement> elements) {
		this.instElements = elements;
	}

	public VariabilityElement getVariabilityElement(String string) {
		return vElements.get(string);
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public Map<String, Asset> getAssets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Constraint getConstraint(String consId) {
		// TODO Auto-generated method stub
		return null;
	}


}
