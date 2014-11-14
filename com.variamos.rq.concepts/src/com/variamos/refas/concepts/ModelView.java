package com.variamos.refas.concepts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cfm.productline.AbstractElement;
import com.cfm.productline.Constraint;

/**
 * @author jcmunoz Class to handle dinamyc model with elements and constraints.
 *         todo: add dinamyc rules associate valid elements
 */
public class ModelView implements Serializable {
	private static final long serialVersionUID = 5153411159224220508L;

	private ArrayList<String> validElements;
	private Map<String, AbstractElement> elements;
	private Map<String, Constraint> constraints; // Methods missing to handle

	public ModelView(ArrayList<String> validElements) {
		this.validElements = validElements;
		elements = new HashMap<>();
		constraints = new HashMap<>();
	}

	public ArrayList<String> getValidElements() {
		return validElements;
	}
	public String addElement(AbstractElement element) {
		String id = getNextElementId(element);
		String name = element.getAlias();
		if (validElements.contains(name)) {
			if (element instanceof AbstractElement) {
				AbstractElement varElement = (AbstractElement) element;
				varElement.setIdentifier(id);
				varElement.setName("<<new>>");
			}
			elements.put(id, element);
			return id;
		} else
			return null;
	}
	
	public String addConstraint(Constraint constraint) {
		Class<? extends Constraint> constraintClass = constraint.getClass();
		String id = getNextConstraintId(constraint);
		String name = constraintClass.getSimpleName();
		if (validElements.contains(name)) {
			if (constraint instanceof Constraint) {
				Constraint varElement = (Constraint) constraint;
				varElement.setIdentifier(id);				
			}
			constraints.put(id, constraint);
			return id;
		} else
			return null;
	}

	public AbstractElement findElement(String identifier) {
		return elements.get(identifier);
	}

	private String getNextElementId(AbstractElement element) {

		int id = 1;
		String classId = element.getClassId();
		while (elements.containsKey(classId + id)) {
			id++;
		}
		return classId+ id;
	}
	
	private String getNextConstraintId(Constraint element) {

		int id = 1;
		String classId = element.getClassId();
		while (constraints.containsKey(classId + id)) {
			id++;
		}
		return classId+ id;
	}

	public Map<String, AbstractElement> getElements() {
		return elements;
	}

	public void setElements(Map<String, AbstractElement> elements) {
		this.elements = elements;
	}
}
