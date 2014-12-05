package com.variamos.refas.core.staticconcepts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cfm.productline.AbstractElement;
import com.cfm.productline.Constraint;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;

/**
 * @author jcmunoz Class to handle dinamyc model with elements and constraints.
 *         todo: add dinamyc rules associate valid elements
 */
public class ModelView implements Serializable {
	private static final long serialVersionUID = 5153411159224220508L;

	private ArrayList<String> validElements;
	private Map<String, AbstractElement> oldElements;
	private Map<String, InstElement> elements;
	private Map<String, Constraint> constraints; // Methods missing to handle

	public ModelView(ArrayList<String> validElements) {
		this.validElements = validElements;
		oldElements = new HashMap<>();
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
			oldElements.put(id, element);
			return id;
		} else
			return null;
	}

	public String addInstConceptElement(InstElement element) {
		String id = getNextElementId(element);
		InstElement varElement = (InstElement) element;
		varElement.setIdentifier(id);
		varElement.setInstAttribute("name", "<<new>>");
		elements.put(id, element);
		return id;
	}

	public String addConstraint(Constraint constraint) {
		Class<? extends Constraint> constraintClass = constraint.getClass();
		String id = getNextConstraintId(constraint);
		String name = constraintClass.getSimpleName();
		// if (validElements.contains(name)) {
		if (constraint instanceof Constraint) {
			Constraint varElement = (Constraint) constraint;
			varElement.setIdentifier(id);
		}
		constraints.put(id, constraint);
		return id;
		// } else
		// return null;
	}

	public AbstractElement findElement(String identifier) {
		return oldElements.get(identifier);
	}

	private String getNextElementId(AbstractElement element) {

		int id = 1;
		String classId = element.getClassId();
		while (oldElements.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextElementId(InstElement element) {

		int id = 1;
		String classId = null;
		if (element instanceof InstConcept)
			classId = ((InstConcept) element).getMetaConceptIdentifier();
		else
			classId = ((InstGroupDependency) element)
					.getMetaGroupDependencyIdentifier();

		while (elements.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextConstraintId(Constraint element) {

		int id = 1;
		String classId = element.getClassId();
		while (constraints.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	public Map<String, AbstractElement> getOldElements() {
		return oldElements;
	}

	public void setOldElements(Map<String, AbstractElement> elements) {
		this.oldElements = elements;
	}

	public Map<String, InstElement> getElements() {
		return elements;
	}

	public void setElements(Map<String, InstElement> elements) {
		this.elements = elements;
	}

}
