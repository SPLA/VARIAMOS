package com.variamos.dynsup.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.dynsup.defaultmodels.DefaultOpersMM;
import com.variamos.dynsup.defaultmodels.DefaultSyntaxMM;
import com.variamos.dynsup.defaultmodels.InfraBasicSyntaxMMMM;
import com.variamos.dynsup.defaultmodels.InfraSyntaxOpersMMM;
import com.variamos.dynsup.defaultmodels.InfraSyntaxSyntaxMMM;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstOverTwoRel;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.types.PerspectiveType;

/**
 * A class to represent the model with vertex and edges. Maintains the
 * collections from ProductLine but are not used. Methods should be standardized
 * to use the HLCL. Initially based on ProductLine class. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-10
 */
public class InstanceModel {
	// FIXME v1.1 RENAME to InstSupModel

	private InstanceModel syntaxModel;
	private InstanceModel operationalModel;
	private Map<String, OpersExprType> semanticExpressionTypes;

	/**
	 * 
	 */
	private Map<String, InstElement> variabilityInstVertex;

	/**
	 * 
	 */
	private Map<String, InstOverTwoRel> instGroupDependencies;
	/**
	 * 
	 */
	private Map<String, InstPairwiseRel> constraintInstEdges;

	/**
	 * 
	 */
	protected String name;

	private PerspectiveType perspectiveType;

	public InstanceModel(PerspectiveType perspectiveType,
			Map<String, OpersExprType> metaExpressionTypes) {
		this(perspectiveType, metaExpressionTypes, null, null);
	}

	public InstanceModel(Map<String, OpersExprType> metaExpressionTypes,
			InstanceModel syntaxRefas) {
		this(PerspectiveType.OPERATIONSSUPERSTRUCTURE, metaExpressionTypes,
				syntaxRefas, null);
	}

	public InstanceModel(PerspectiveType perspectiveType,
			Map<String, OpersExprType> semanticExpressionTypes,
			InstanceModel syntaxRefas, InstanceModel semanticRefas) {
		this.perspectiveType = perspectiveType;
		this.syntaxModel = syntaxRefas;
		this.semanticExpressionTypes = semanticExpressionTypes;
		this.operationalModel = semanticRefas;
		variabilityInstVertex = new HashMap<String, InstElement>();
		instGroupDependencies = new HashMap<String, InstOverTwoRel>();
		constraintInstEdges = new HashMap<String, InstPairwiseRel>();
		name = "";

		switch (perspectiveType) {
		case INFRASTRUCTUREBASICSYNTAX:
			createInfraBasicSyntax();
			break;
		case OPERATIONSINFRASTRUCTURE:
			createOperationsInfrastructure();
			break;
		case SYNTAXINFRASTRUCTURE:
			createSyntaxInfrastructure();
			break;
		case MODELING:
			break;
		case OPERATIONSSUPERSTRUCTURE:
			createOperationsSuperstructure(false);
			break;
		case CONFIG_SIMULATION:
			break;
		case SYNTAXSUPERSTRUCTURE:
			createSyntaxSuperstructure();
			break;
		default:
			break;
		}
	}

	private void createInfraBasicSyntax() {
		InfraBasicSyntaxMMMM.createBasicSyntaxMetaMetaMetaModel(this);
	}

	/**
	 * Creates the meta-meta-elements (objects) to support the Operations
	 * Meta-Model. Concepts are displayed in the palette of the operational
	 * perspective (PWAssociations can be used)
	 */
	private void createOperationsInfrastructure() {
		InfraSyntaxOpersMMM.createSyntaxOpersMetaMetaModel(this);

	}

	public InstanceModel getSyntaxModel() {
		return syntaxModel;
	}

	public String getInstViewName(int modelViewInd, int modelViewSubInd) {
		// List<InstView> instViews = this.getSyntaxRefas().getInstViews();
		List<InstElement> instViews = this.getSyntaxModel()
				.getVariabilityVertex("SyMView");
		if (modelViewInd == -1)
			if (instViews.size() > 0)
				return instViews.get(0).getEdSyntaxEle().getAutoIdentifier();
			else
				return "";
		if (modelViewInd < instViews.size() && modelViewSubInd == -1)
			return instViews.get(modelViewInd).getEdSyntaxEle()
					.getAutoIdentifier();

		if (modelViewInd != -1 && modelViewInd < instViews.size()
				&& modelViewSubInd != -1)
			// && modelViewSubInd < instViews.get(modelViewInd)
			// .getChildViews().size())
			return "";// ((MetaView) instViews.get(modelViewInd).getChildViews()
						// .get(modelViewSubInd).getEditableMetaElement())
						// .getIdentifier();
		return null;
	}

	public String getInstViewPalettesName(int modelViewInd, int modelViewSubInd) {
		// List<InstView> instViews = this.getSyntaxRefas().getInstViews();
		List<InstElement> instViews = this.getSyntaxModel()
				.getVariabilityVertex("SyMView");
		if (modelViewInd == -1)
			if (instViews.size() > 0)
				return instViews.get(0).getEdSyntaxEle().getPaletteName();
			else
				return "";
		if (modelViewInd < instViews.size() && modelViewSubInd == -1)
			return instViews.get(modelViewInd).getEdSyntaxEle()
					.getPaletteName();

		if (modelViewInd != -1 && modelViewInd < instViews.size()
				&& modelViewSubInd != -1)
			// && modelViewSubInd < instViews.get(modelViewInd)
			// .getChildViews().size())
			return "";// ((MetaView) instViews.get(modelViewInd).getChildViews()
						// .get(modelViewSubInd).getEditableMetaElement())
						// .getPaletteName();
		return null;
	}

	public Map<String, OpersExprType> getSemanticExpressionTypes() {
		return semanticExpressionTypes;
	}

	public InstanceModel getOperationalModel() {
		return operationalModel;
	}

	public Map<String, InstOverTwoRel> getInstGroupDependencies() {
		return instGroupDependencies;
	}

	public Collection<InstOverTwoRel> getInstGroupDependenciesCollection() {
		return instGroupDependencies.values();
	}

	public Map<String, InstPairwiseRel> getConstraintInstEdges() {
		return constraintInstEdges;
	}

	public Collection<InstPairwiseRel> getConstraintInstEdgesCollection() {
		return constraintInstEdges.values();
	}

	public void putVariabilityInstVertex(InstElement element) {
		variabilityInstVertex.put(element.getIdentifier(), element);
	}

	public void putInstGroupDependency(InstOverTwoRel groupDep) {
		instGroupDependencies.put(groupDep.getIdentifier(), groupDep);
	}

	public void putConstraintInstEdge(InstPairwiseRel element) {
		constraintInstEdges.put(element.getIdentifier(), element);
	}

	public String addNewVariabilityInstElement(InstElement element) {
		String id = getNextVariabilityInstVertextId(element);
		InstElement varElement = element;
		varElement.setIdentifier(id);
		varElement.setInstAttribute("name", id);
		variabilityInstVertex.put(id, element);
		return id;
	}

	public String addNewInstGroupDependency(InstOverTwoRel groupDep) {
		String id = getNextInstGroupDependencyId(groupDep);
		groupDep.setIdentifier(id);
		groupDep.setInstAttribute("name", id);
		instGroupDependencies.put(id, groupDep);
		return id;
	}

	public String addNewConstraintInstEdge(InstPairwiseRel directRelation) {
		String id = getNextConstraintInstEdgeId(directRelation);
		directRelation.setIdentifier(id);
		constraintInstEdges.put(id, directRelation);
		return id;
	}

	private String getNextVariabilityInstVertextId(InstElement element) {
		int id = 1;
		String classId = null;
		if (element instanceof InstElement)
			if (element.getTransSupInstElement()
					.getInstAttributeValue("userId") != null)
				classId = (String) element.getTransSupInstElement()
						.getInstAttributeValue("userId");
			else if (element.getTransSupportMetaElement().getUserIdentifier() == null)
				classId = element.getTransSupportMetaElement()
						.getAutoIdentifier();
			else
				classId = element.getTransSupportMetaElement()
						.getUserIdentifier();

		// if (element instanceof InstConcept)
		// if (((InstConcept) element).getTransSupportMetaElement()
		// .getUserIdentifier() == null)
		// classId = ((InstConcept) element).getTransSupportMetaElement()
		// .getAutoIdentifier();
		// else
		// classId = ((InstConcept) element).getTransSupportMetaElement()
		// .getUserIdentifier();
		// // .getSupportMetaElementUserIdentifier();
		// else {
		// if (element instanceof InstEnumeration)
		// if (((InstEnumeration) element).getTransSupportMetaElement()
		// .getUserIdentifier() == null)
		// classId = ((InstEnumeration) element)
		// .getTransSupportMetaElement().getAutoIdentifier();
		// else
		// classId = ((InstEnumeration) element)
		// .getTransSupportMetaElement().getUserIdentifier();
		// // .getSupportMetaElementUserIdentifier();
		// else if (((InstOverTwoRelation) element)
		// .getTransSupportMetaElement().getUserIdentifier() == null)
		// classId = ((InstOverTwoRelation) element)
		// .getTransSupportMetaElement().getAutoIdentifier();
		// else
		// classId = ((InstOverTwoRelation) element)
		// .getTransSupportMetaElement().getUserIdentifier();
		// // .getSupportMetaElementUserIdentifier();
		//
		// }

		while (variabilityInstVertex.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextInstGroupDependencyId(InstOverTwoRel grouDep) {

		int id = 1;
		String classId = grouDep.getSupportMetaElementUserIdentifier();

		while (instGroupDependencies.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	private String getNextConstraintInstEdgeId(InstPairwiseRel element) {

		int id = 1;
		String classId = null;
		classId = "E";

		while (constraintInstEdges.containsKey(classId + id)) {
			id++;
		}
		return classId + id;
	}

	public Map<String, InstElement> getVariabilityVertex() {
		return variabilityInstVertex;
	}

	public List<InstElement> getVariabilityVertex(String stereotype) {
		Iterator<InstElement> iter = variabilityInstVertex.values().iterator();
		List<InstElement> out = new ArrayList<InstElement>();
		while (iter.hasNext()) {
			InstElement element = iter.next();
			if (element.getTransSupportMetaElement() != null
					&& element.getTransSupportMetaElement().getAutoIdentifier()
							.equals(stereotype))
				out.add(element);
		}
		try {
			Collections.sort(out);
		} catch (Exception e) {

		}
		return out;
	}

	public List<InstElement> getVariabilityVertexMC(String metatype) {
		Iterator<InstElement> iter = variabilityInstVertex.values().iterator();
		List<InstElement> out = new ArrayList<InstElement>();
		while (iter.hasNext()) {
			InstElement element = iter.next();
			if (element.getTransSupportMetaElement() != null) {
				SyntaxElement e = element.getTransSupportMetaElement();
				InstElement el = this.getSyntaxModel()
						.getVertex(e.getAutoIdentifier()).getEdSyntaxEle()
						.getTransInstSemanticElement();
				while (el != null) {
					if (el.getUserIdentifier().equals(metatype)) {
						out.add(element);
						break;
					}
					InstElement el2 = null;
					for (InstElement ee : el.getTargetRelations()) {

						// System.out.println(ee.getTransSupportMetaElement()
						// .getUserIdentifier());
						if (ee.getTransSupportMetaElement().getUserIdentifier()
								.equals("ExtendsRelation"))
							el2 = ee.getTargetRelations().get(0);
					}
					el = el2;
				}
			}
		}
		try {
			Collections.sort(out);
		} catch (Exception e) {

		}
		return out;
	}

	public Collection<InstElement> getVariabilityVertexCollection() {
		return variabilityInstVertex.values();
	}

	public void setVariabilityVertex(Map<String, InstElement> elements) {
		this.variabilityInstVertex = elements;
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

	public InstElement getVertex(String vertexId) {
		InstElement out = variabilityInstVertex.get(vertexId);
		if (out == null)
			out = instGroupDependencies.get(vertexId);
		return out;
	}

	public InstElement getVertexByName(String elementName) {
		for (InstElement e : variabilityInstVertex.values()) {
			String name = (String) e.getInstAttributeValue("opname");
			if (name != null)
				if (name.equals(elementName))
					return e;
		}
		return null;
	}

	public Set<InstElement> getVertices() {
		Set<InstElement> out = new HashSet<InstElement>();
		out.addAll(variabilityInstVertex.values());
		out.addAll(instGroupDependencies.values());
		return out;
	}

	public Set<InstElement> getElements() {
		Set<InstElement> out = new HashSet<InstElement>();
		out.addAll(variabilityInstVertex.values());
		out.addAll(instGroupDependencies.values());
		out.addAll(constraintInstEdges.values());
		return out;
	}

	public InstPairwiseRel getConstraintInstEdge(String edgeId) {
		return constraintInstEdges.get(edgeId);
	}

	public com.variamos.dynsup.instance.InstElement getElement(String vertexId) {
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
		if (obj instanceof InstOverTwoRel) {
			InstOverTwoRel overtwo = (InstOverTwoRel) obj;
			instGroupDependencies.remove(overtwo.getIdentifier());
		} else if (obj instanceof InstPairwiseRel)
			constraintInstEdges.remove(((InstPairwiseRel) obj).getIdentifier());
	}

	/**
	 * @return all InstVertex with constraints (excludes MetaEnumation)
	 */
	public List<InstElement> getConstraintVertexCollection() {
		ArrayList<InstElement> out = new ArrayList<InstElement>();
		out.addAll(variabilityInstVertex.values());
		out.addAll(instGroupDependencies.values());
		return out;
	}

	public Map<String, InstElement> getConstraintVertex() {
		Map<String, InstElement> out = new HashMap<String, InstElement>();
		out.putAll(variabilityInstVertex);
		out.putAll(instGroupDependencies);
		return out;
	}

	public List<String> modelElements(int modelViewInd, int modelViewSubInd) {
		List<String> elements = new ArrayList<String>();
		List<InstElement> views = this.getVariabilityVertex("SyMView");
		// modelViewInd = -1; // TODO for initial testing, delete
		if (modelViewInd == -1) {
			for (InstElement instVertex : variabilityInstVertex.values()) {
				if (instVertex.getInstAttributeValue("Visible") != null
						&& (boolean) instVertex
								.getInstAttributeValue("Visible"))
					elements.add(instVertex.getEdSyntaxEle()
							.getAutoIdentifier());
			}
		} else if (modelViewInd < views.size() && modelViewSubInd == -1) {
			for (InstElement instElement : views.get(modelViewInd)
					.getTargetRelations()) {
				// if (instElement instanceof InstVertex ||instElement
				// instanceof InstView) {
				if (instElement.getTargetRelations().get(0)
						.getTargetRelations().size() > 0) {
					InstElement instVertex = instElement.getTargetRelations()
							.get(0).getTargetRelations().get(0)
							.getTargetRelations().get(0);
					if (instVertex.getEdSyntaxEle() != null
							&& instVertex.getInstAttributeValue("Visible") != null
							&& (boolean) instVertex
									.getInstAttributeValue("Visible"))
						elements.add(instVertex.getEdSyntaxEle()
								.getAutoIdentifier());
					// }
				}
			}
		}
		if (modelViewInd != -1
				&& modelViewInd < views.size()
				&& modelViewSubInd != -1
				&& modelViewSubInd < views.get(modelViewInd)
						.getTargetRelations().size()) {
			for (InstElement instElement : views.get(modelViewInd)
					.getTargetRelations()) {
				InstElement instVertex = instElement.getTargetRelations()
						.get(0).getTargetRelations().get(0)
						.getTargetRelations().get(0);
				if (instVertex.getEdSyntaxEle() != null
						&& instVertex.getInstAttributeValue("Visible") != null
						&& (boolean) instVertex
								.getInstAttributeValue("Visible"))
					elements.add(instVertex.getEdSyntaxEle()
							.getAutoIdentifier());
			}
		}
		return elements;
	}

	/**
	 * Creates the elements (objects) to support the syntax Meta Model. Concepts
	 * are displayed in the palette of the syntax perspective. (PWAssociations
	 * can be used)
	 */
	private void createSyntaxInfrastructure() {
		InfraSyntaxSyntaxMMM.createSyntaxSyntaxMetaMetaModel(this);

	}

	/**
	 * Creates the default elements (objects) of the Semantic Model (Semantic
	 * concepts). Elements are loaded in the Semantic perspective (advanced
	 * perspectiv) and defines the semantic for elements on the syntax
	 * perspective (associated to concepts).
	 */

	public void createOperationsSuperstructure(boolean empty) {
		DefaultOpersMM.createOpersMetaModel(this, empty);
	}

	/**
	 * Creates the default objects of the Meta Model (Syntax concepts). They can
	 * be edited on the Syntax perspective and are the support elements for the
	 * modeling perspective displayed in the views (palettes)
	 */
	private void createSyntaxSuperstructure() {
		DefaultSyntaxMM.createSyntaxMetaModel(this);
	}

	public PerspectiveType getPerspectiveType() {
		return perspectiveType;
	}

	public void setInstGroupDependencies(
			Map<String, InstOverTwoRel> instGroupDependencies) {
		this.instGroupDependencies = instGroupDependencies;
	}

	public Map<String, InstElement> getValidPairwiseRelations(
			InstElement instElement, InstElement instElement2) {
		InstElement instSyntaxElement = this.getVertex(instElement
				.getSupInstEleId());
		InstElement instSyntaxElement2 = null;
		if (instElement2 != null)
			instSyntaxElement2 = this.getVertex(instElement2.getSupInstEleId());
		if (instSyntaxElement2 == null) {
			System.out
					.println("getValidPairwiseRelations error - "
							+ (instElement2 == null ? "" : instElement2
									.getIdentifier()));
		}
		return getValidPairwiseRelations(instSyntaxElement, instSyntaxElement2,
				true);
	}

	private Map<String, InstElement> getValidPairwiseRelations(
			InstElement instElement, InstElement instElement2, boolean first) {
		SyntaxElement metaElement = null;
		if (instElement != null)
			metaElement = instElement.getEdSyntaxEle();
		Map<String, InstElement> out = new HashMap<String, InstElement>();
		if (instElement2 == null) {
			return out;
		}
		SyntaxElement metaElement2 = instElement2.getEdSyntaxEle();
		for (InstPairwiseRel pwr : constraintInstEdges.values()) {
			if (pwr.getSourceRelations().size() > 0
					&& pwr.getTargetRelations().size() > 0) {
				SyntaxElement sourceMetaElement = pwr.getSourceRelations()
						.get(0).getEdSyntaxEle();
				SyntaxElement targetMetaElement = pwr.getTargetRelations()
						.get(0).getEdSyntaxEle();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getAutoIdentifier().equals(
						metaElement.getAutoIdentifier())
						&& targetMetaElement.getAutoIdentifier().equals(
								metaElement2.getAutoIdentifier())
				// && pwr.getEditableMetaElement().getVisible()
				)
					out.put(pwr.getIdentifier(), pwr);
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
		for (InstElement pwr : this.variabilityInstVertex.values()) {
			if (pwr.getSourceRelations().size() > 0
					&& pwr.getTargetRelations().size() > 0) {
				SyntaxElement sourceMetaElement = pwr.getSourceRelations()
						.get(0).getSourceRelations().get(0).getEdSyntaxEle();
				SyntaxElement targetMetaElement = pwr.getTargetRelations()
						.get(0).getTargetRelations().get(0).getEdSyntaxEle();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getAutoIdentifier().equals(
						metaElement.getAutoIdentifier())
						&& targetMetaElement.getAutoIdentifier().equals(
								metaElement2.getAutoIdentifier())
						&& pwr.getEdSyntaxEle().getVisible())
					if (!pwr.getSupInstEleId().equals("SyMExtend"))
						out.put(pwr.getIdentifier(), pwr);
				// TODO validate the other end when the OTR type has
				// exclusive connections
			}
		}

		if (metaElement instanceof SyntaxElement && first) {
			List<InstElement> rel = instElement.getTargetRelations();
			for (InstElement element : rel)
				if (element.getTargetRelations().get(0).getSupInstEleId() != null
						&& element.getTargetRelations().get(0)
								.getSupInstEleId().equals("SyMExtend")) {
					out.putAll(getValidPairwiseRelations(element
							.getTargetRelations().get(0).getTargetRelations()
							.get(0).getTargetRelations().get(0), instElement2,
							true));
				}
		}
		if (metaElement2 instanceof SyntaxElement) {
			List<InstElement> rel = instElement2.getTargetRelations();
			for (InstElement element : rel) {
				if (element.getTargetRelations().get(0).getSupInstEleId() != null
						&& element.getTargetRelations().get(0)
								.getSupInstEleId().equals("SyMExtend")) {
					out.putAll(getValidPairwiseRelations(instElement, element
							.getTargetRelations().get(0).getTargetRelations()
							.get(0).getTargetRelations().get(0), false));

				}
			}
		}
		return out;
	}

	public InstElement getValidMetaPairwiseRelation(InstElement instElement,
			InstElement instElement2, String metaPairwiseIden) {
		InstElement instSyntaxElement = this.getVertex(instElement
				.getSupInstEleId());
		InstElement instSyntaxElement2 = this.getVertex(instElement2
				.getSupInstEleId());
		return getValidMetaPairwiseRelation(instSyntaxElement,
				instSyntaxElement2, metaPairwiseIden, true);
	}

	private InstElement getValidMetaPairwiseRelation(InstElement instElement,
			InstElement instElement2, String metaPairwiseIden, boolean first) {
		SyntaxElement metaElement = instElement.getEdSyntaxEle();
		if (instElement2 == null) {
			System.out.println("getValidMetaPairwiseRelation error");
		}
		SyntaxElement metaElement2 = instElement2.getEdSyntaxEle();
		for (InstPairwiseRel pwr : constraintInstEdges.values()) {
			if (pwr.getSourceRelations().size() > 0
					&& pwr.getTargetRelations().size() > 0
					&& pwr.getEdSyntaxEle() != null) {
				SyntaxElement sourceMetaElement = pwr.getSourceRelations()
						.get(0).getEdSyntaxEle();
				SyntaxElement targetMetaElement = pwr.getTargetRelations()
						.get(0).getEdSyntaxEle();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getAutoIdentifier().equals(
						metaElement.getAutoIdentifier())
						&& targetMetaElement.getAutoIdentifier().equals(
								metaElement2.getAutoIdentifier())
						&& pwr.getEdSyntaxEle().getAutoIdentifier()
								.equals(metaPairwiseIden))
					return pwr;
			}
		}
		InstElement out2 = null;
		for (InstElement pwr : this.variabilityInstVertex.values()) {
			if (pwr.getSourceRelations().size() > 0
					&& pwr.getTargetRelations().size() > 0) {
				SyntaxElement sourceMetaElement = pwr.getSourceRelations()
						.get(0).getSourceRelations().get(0).getEdSyntaxEle();
				SyntaxElement targetMetaElement = pwr.getTargetRelations()
						.get(0).getTargetRelations().get(0).getEdSyntaxEle();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getAutoIdentifier().equals(
						metaElement.getAutoIdentifier())
						&& targetMetaElement.getAutoIdentifier().equals(
								metaElement2.getAutoIdentifier())
						&& pwr.getEdSyntaxEle().getAutoIdentifier()
								.equals(metaPairwiseIden))
					out2 = pwr;
				// TODO validate the other end when the OTR type has
				// exclusive connections
				if (out2 != null)
					return out2;
			}
		}

		if (metaElement2 instanceof SyntaxElement) {
			List<InstElement> rel = instElement2.getTargetRelations();
			for (InstElement element : rel) {
				if (element.getTargetRelations().get(0).getSupInstEleId() != null
						&& element.getTargetRelations().get(0)
								.getSupInstEleId().equals("SyMExtend")) {
					InstElement out = (getValidMetaPairwiseRelation(
							instElement, element.getTargetRelations().get(0)
									.getTargetRelations().get(0)
									.getTargetRelations().get(0),
							metaPairwiseIden, false));
					if (out != null)
						return out;
				}
			}
		}
		if (metaElement instanceof SyntaxElement && first) {
			List<InstElement> rel = instElement.getTargetRelations();
			for (InstElement element : rel)
				if (element.getTargetRelations().get(0).getSupInstEleId() != null
						&& element.getTargetRelations().get(0)
								.getSupInstEleId().equals("SyMExtend")) {
					return (getValidMetaPairwiseRelation(element
							.getTargetRelations().get(0).getTargetRelations()
							.get(0).getTargetRelations().get(0), instElement2,
							metaPairwiseIden, true));
				}
		}
		return null;
	}

	public List<InstElement> getParentSMMSyntaxElement(InstElement instElement) {
		if (instElement == null)
			return null;
		InstElement instSyntaxElement = this.getSyntaxModel().getVertex(
				instElement.getSupInstEleId());
		if (instSyntaxElement != null)
			return getRecursiveParentSyntaxConcept(instSyntaxElement);
		else
			return null;
	}

	private List<InstElement> getRecursiveParentSyntaxConcept(
			InstElement instElement) {
		List<InstElement> out = new ArrayList<InstElement>();
		List<InstElement> rel = instElement.getTargetRelations();
		for (InstElement element : rel) {
			if (element.getTargetRelations().get(0).getSupInstEleId() != null
					&& element.getTargetRelations().get(0).getSupInstEleId()
							.equals("SyMExtend")) {
				InstElement parent = element.getTargetRelations().get(0)
						.getTargetRelations().get(0).getTargetRelations()
						.get(0);
				if (parent != instElement) {
					out.add(parent);
					out.addAll(getRecursiveParentSyntaxConcept(parent));
				}
			}
		}
		return out;
	}

	public void updateValidationLists(InstElement elm, InstElement instSource,
			InstElement instTarget, List<InstElement> syntaxParents,
			List<InstElement> opersParents) {
		List<InstAttribute> visible = elm.getVisibleAttributes(syntaxParents);
		InstPairwiseRel instPairwise = (InstPairwiseRel) elm;
		if (instSource == null && instPairwise.getSourceRelations().size() > 0)
			instSource = instPairwise.getSourceRelations().get(0);
		if (instTarget == null && instPairwise.getTargetRelations().size() > 0)
			instTarget = instPairwise.getTargetRelations().get(0);
		for (InstAttribute v : visible) {
			Map<String, InstElement> mapElements = null;
			if (elm instanceof InstPairwiseRel && instSource != null
					&& instSource.getTransSupInstElement() != null) {
				mapElements = getSyntaxModel().getValidPairwiseRelations(
						instSource, instTarget);
			}
			v.updateValidationList(elm, mapElements);
		}
	}

	public boolean elementsValidation(String element, int modelViewInd,
			int modelViewSubInd) {

		List<InstElement> views = this.getVariabilityVertex("SyMView");
		// FIXME Find views by stereotype, not by instViews object
		if (modelViewInd < views.size() && modelViewSubInd == -1) {
			for (InstElement instElement : views.get(modelViewInd)
					.getTargetRelations()) {
				if (instElement.getTargetRelations().get(0).getSupInstEleId() != null
						&& instElement.getTargetRelations().get(0)
								.getTargetRelations().get(0)
								.getTargetRelations().get(0).getIdentifier()
								.equals(element))
					return true;
			}
		}
		/*
		 * if (modelViewInd < instViews.size() && modelViewSubInd != -1 &&
		 * modelViewSubInd < instViews.get(modelViewInd)
		 * .getChildViews().size()) { // FIXME use relations instead
		 * List<InstElement> instVertex = instViews.get(modelViewInd)
		 * .getChildViews().get(modelViewSubInd).getTargetRelations(); for
		 * (InstElement instElement : instVertex) { if
		 * (instElement.getTargetRelations().get(0)
		 * .getTargetRelations().get(0).getTargetRelations()
		 * .get(0).getIdentifier().equals(element)) return true; }
		 * 
		 * }
		 */
		return false;
	}

	public void clear() {
		this.instGroupDependencies.clear();
		this.variabilityInstVertex.clear();
		this.constraintInstEdges.clear();
	}
}
