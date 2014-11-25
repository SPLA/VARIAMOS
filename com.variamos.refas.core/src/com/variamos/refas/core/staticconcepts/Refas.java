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

/**
 * @author jcmunoz Initially based on ProductLine class
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

	public boolean[] elementsValidation(String element) {
		boolean[] valid = new boolean[5];
		for (int i = 0; i < Integer.parseInt(mxResources.get("modelViews")); i++) {
			if (modelViews[i].getValidElements().contains(element))
				valid[i] = true;
		}

		return valid;
	}

	public ArrayList<String> getValidElements(int modelView) {
		if (modelView >= modelViews.length)
			return null;
		else
			return modelViews[modelView].getValidElements();
	}

	public void defaultModelViews() {
		@SuppressWarnings("unchecked")
		ArrayList<String> validElements[] = new ArrayList[Integer
				.parseInt(mxResources.get("modelViews"))];
		modelViews = new ModelView[Integer.parseInt(mxResources
				.get("modelViews"))];

		for (int i = 0; i < Integer.parseInt(mxResources.get("modelViews")); i++) {
			validElements[i] = new ArrayList<String>();
		}

		// todo: load from metamodel concepts and relations associated to each
		// model view
		validElements[0].add("TG");
		validElements[0].add("GG");
		validElements[0].add("OPER");
		validElements[0].add("ASSUM");
		validElements[0].add("GC");

		// OLD
		validElements[0].add("Goal");
		validElements[0].add("Operationalization");
		validElements[0].add("Assumption");
		validElements[0].add("GroupGConstraint");
		validElements[0].add("GenericConstraint");

		validElements[1].add("TSG");
		validElements[1].add("GSG");
		validElements[0].add("GC");

		validElements[1].add("SoftGoal");
		validElements[1].add("GenericConstraint");

		validElements[2].add("CG");
		validElements[2].add("GCV");
		validElements[2].add("LCV");
		validElements[2].add("GC");

		validElements[2].add("ContextGroup");
		validElements[2].add("GlobalContextVariable");
		validElements[2].add("LocalContextVariable");
		validElements[2].add("GenericConstraint");

		validElements[3].add("CL");
		validElements[3].add("SD");
		validElements[3].add("TSG");
		validElements[3].add("GSG");
		validElements[3].add("OPER");
		validElements[3].add("LCV");
		validElements[3].add("GCV");
		validElements[3].add("GC");

		validElements[3].add("Claim");
		validElements[3].add("SoftGoal");
		validElements[3].add("SoftDependency");
		validElements[3].add("Operationalization");
		validElements[3].add("ContextVariable");
		validElements[3].add("GroupGConstraint");
		validElements[3].add("GenericConstraint");

		validElements[4].add("AS");
		validElements[4].add("OPER");
		validElements[4].add("GC");

		validElements[4].add("Asset");
		validElements[4].add("Operationalization");
		validElements[4].add("GroupGConstraint");
		validElements[4].add("GenericConstraint");

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

	public String addElement(int modelViewIndex, InstConcept element) {
		return modelViews[modelViewIndex].addInstConceptElement(element);
	}

	public Map<String, InstConcept> getElements(int modelView) {
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
