package com.variamos.refas.core.staticconcepts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.cfm.common.AbstractModel;
import com.cfm.productline.AbstractElement;
import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.VariabilityElement;
import com.mxgraph.util.mxResources;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstElement;

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
	protected String name;
	protected ModelView[] modelViews;

	public ModelView[] getModelViews() {
		return modelViews;
	}

	public Refas() {
		vElements = new HashMap<String, VariabilityElement>();
		defaultModelViews();
		name = "";
	}

	/**
	 * jcmunoz: temporal method to fill valid element by models model order:
	 * Goals, SG, Context, SG Satisficing, Assets
	 */

	public void defaultModelViews() {
		@SuppressWarnings("unchecked")
		ArrayList<String> validElements[] = new ArrayList[Integer
				.parseInt(mxResources.get("modelViews"))];
		modelViews = new ModelView[Integer.parseInt(mxResources
				.get("modelViews"))];

		for (int i = 0; i < Integer.parseInt(mxResources.get("modelViews")); i++) {
			validElements[i] = new ArrayList<String>();
		}

		for (int i = 0; i < Integer.parseInt(mxResources.get("modelViews")); i++) {
			modelViews[i] = new ModelView(validElements[i]);
		}

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

	public String addConstraint(int modelView, Constraint a) {
		return modelViews[modelView].addConstraint(a);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String addElement(int modelView, AbstractElement a) {
		return modelViews[modelView].addElement(a);
	}

	public String addElement(int modelViewIndex, InstElement element) {
		return modelViews[modelViewIndex].addInstConceptElement(element);
	}

	public Map<String, InstElement> getElements(int modelView) {
		return modelViews[modelView].getElements();

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
