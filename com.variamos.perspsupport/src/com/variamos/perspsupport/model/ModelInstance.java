package com.variamos.perspsupport.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cfm.common.AbstractModel;
import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.VariabilityElement;
import com.variamos.hlcl.IntervalDomain;
import com.variamos.perspsupport.expressionsupport.SemanticExpressionType;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstEnumeration;
import com.variamos.perspsupport.instancesupport.InstOverTwoRelation;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.instancesupport.InstVertex;
import com.variamos.perspsupport.opers.OpersConcept;
import com.variamos.perspsupport.opers.OpersPairwiseRel;
import com.variamos.perspsupport.opers.OpersRelType;
import com.variamos.perspsupport.opersint.IntOpersRelType;
import com.variamos.perspsupport.syntaxsupport.MetaConcept;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaPairwiseRelation;
import com.variamos.perspsupport.syntaxsupport.MetaView;
import com.variamos.perspsupport.syntaxsupport.SemanticAttribute;
import com.variamos.perspsupport.syntaxsupport.SyntaxAttribute;
import com.variamos.perspsupport.types.ConceptType;
import com.variamos.perspsupport.types.OperationSubActionExecType;
import com.variamos.perspsupport.types.OperationSubActionType;
import com.variamos.perspsupport.types.PerspectiveType;
import com.variamos.semantic.types.AttributeType;

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
public class ModelInstance extends AbstractModel {

	private ModelInstance syntaxModel;
	private ModelInstance operationalModel;
	private Map<String, SemanticExpressionType> semanticExpressionTypes;

	/**
	 * 
	 */
	private Map<String, InstElement> variabilityInstVertex;

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

	public ModelInstance(PerspectiveType perspectiveType,
			Map<String, SemanticExpressionType> metaExpressionTypes) {
		this(perspectiveType, metaExpressionTypes, null, null);
	}

	public ModelInstance(
			Map<String, SemanticExpressionType> metaExpressionTypes,
			ModelInstance syntaxRefas) {
		this(PerspectiveType.OPERATIONSSUPERSTRUCTURE, metaExpressionTypes,
				syntaxRefas, null);
	}

	public ModelInstance(PerspectiveType perspectiveType,
			Map<String, SemanticExpressionType> semanticExpressionTypes,
			ModelInstance syntaxRefas, ModelInstance semanticRefas) {
		this.perspectiveType = perspectiveType;
		this.syntaxModel = syntaxRefas;
		this.semanticExpressionTypes = semanticExpressionTypes;
		this.operationalModel = semanticRefas;
		variabilityInstVertex = new HashMap<String, InstElement>();
		instGroupDependencies = new HashMap<String, InstOverTwoRelation>();
		constraintInstEdges = new HashMap<String, InstPairwiseRelation>();
		name = "";

		switch (perspectiveType) {
		case OPERATIONSINFRASTRUCTURE:
			createOperationsInfrastructure();
			break;
		case SYNTAXINFRASTRUCTURE:
			createSyntaxInfrastructure();
			break;
		case MODELING:
			break;
		case OPERATIONSSUPERSTRUCTURE:
			createOperationsSuperstructure();
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

	public ModelInstance getSyntaxModel() {
		return syntaxModel;
	}

	public String getInstViewName(int modelViewInd, int modelViewSubInd) {
		// List<InstView> instViews = this.getSyntaxRefas().getInstViews();
		List<InstElement> instViews = this.getSyntaxModel()
				.getVariabilityVertex("SMMView");
		if (modelViewInd == -1)
			if (instViews.size() > 0)
				return ((MetaView) instViews.get(0).getEditableMetaElement())
						.getAutoIdentifier();
			else
				return "";
		if (modelViewInd < instViews.size() && modelViewSubInd == -1)
			return ((MetaView) instViews.get(modelViewInd)
					.getEditableMetaElement()).getAutoIdentifier();

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
				.getVariabilityVertex("SMMView");
		if (modelViewInd == -1)
			if (instViews.size() > 0)
				return ((MetaView) instViews.get(0).getEditableMetaElement())
						.getPaletteName();
			else
				return "";
		if (modelViewInd < instViews.size() && modelViewSubInd == -1)
			return ((MetaView) instViews.get(modelViewInd)
					.getEditableMetaElement()).getPaletteName();

		if (modelViewInd != -1 && modelViewInd < instViews.size()
				&& modelViewSubInd != -1)
			// && modelViewSubInd < instViews.get(modelViewInd)
			// .getChildViews().size())
			return "";// ((MetaView) instViews.get(modelViewInd).getChildViews()
						// .get(modelViewSubInd).getEditableMetaElement())
						// .getPaletteName();
		return null;
	}

	public Map<String, SemanticExpressionType> getSemanticExpressionTypes() {
		return semanticExpressionTypes;
	}

	public ModelInstance getOperationalModel() {
		return operationalModel;
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

	@Deprecated
	public Collection<Constraint> getConstraints() {
		return null;
	}

	public void setConstraints(Map<String, Constraint> constraints) {

	}

	@Deprecated
	public Collection<VariabilityElement> getVariabilityElements() {
		return null;
	}

	public void putVariabilityInstVertex(InstVertex element) {
		variabilityInstVertex.put(element.getIdentifier(), element);
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

	private String getNextVariabilityInstVertextId(InstVertex element) {
		int id = 1;
		String classId = null;
		if (element instanceof InstVertex)
			if (((InstVertex) element).getTransSupportMetaElement()
					.getUserIdentifier() == null)
				classId = ((InstVertex) element).getTransSupportMetaElement()
						.getAutoIdentifier();
			else
				classId = ((InstVertex) element).getTransSupportMetaElement()
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

	private String getNextInstGroupDependencyId(InstOverTwoRelation grouDep) {

		int id = 1;
		String classId = grouDep.getSupportMetaElementUserIdentifier();

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
				out.add((InstElement) element);
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
				MetaElement e = element.getTransSupportMetaElement();
				InstElement el = this.getSyntaxModel()
						.getVertex(e.getAutoIdentifier())
						.getEditableMetaElement().getTransInstSemanticElement();
				while (el != null) {
					if (el.getUserIdentifier().equals(metatype)) {
						out.add((InstElement) element);
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

	public InstElement getVertex(String vertexId) {
		InstElement out = variabilityInstVertex.get(vertexId);
		if (out == null)
			out = instGroupDependencies.get(vertexId);
		return out;
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

	public InstPairwiseRelation getConstraintInstEdge(String edgeId) {
		return constraintInstEdges.get(edgeId);
	}

	public com.variamos.perspsupport.instancesupport.InstElement getElement(
			String vertexId) {
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
		List<InstElement> views = this.getVariabilityVertex("SMMView");
		// modelViewInd = -1; // TODO for initial testing, delete
		if (modelViewInd == -1) {
			for (InstElement instVertex : variabilityInstVertex.values()) {
				if (instVertex.getInstAttributeValue("Visible") != null
						&& (boolean) instVertex
								.getInstAttributeValue("Visible"))
					elements.add(instVertex.getEditableMetaElement()
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
					if (instVertex.getEditableMetaElement() != null
							&& instVertex.getInstAttributeValue("Visible") != null
							&& (boolean) instVertex
									.getInstAttributeValue("Visible"))
						elements.add(instVertex.getEditableMetaElement()
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
				if (instVertex.getEditableMetaElement() != null
						&& instVertex.getInstAttributeValue("Visible") != null
						&& (boolean) instVertex
								.getInstAttributeValue("Visible"))
					elements.add(instVertex.getEditableMetaElement()
							.getAutoIdentifier());
			}
		}
		return elements;
	}

	/**
	 * Creates the meta-meta-elements (objects) to support the Operations
	 * Meta-Model. Concepts are displayed in the palette of the operational
	 * perspective (PWAssociations can be used)
	 */
	private void createOperationsInfrastructure() {

		// Begin Syntax M3 model
		MetaConcept basicOpersSyntaxM3Concept = new MetaConcept('C',
				"BasicOpersSyntaxM3Concept", true, true,
				"BasicOpersSyntaxM3Concept", "infrabasicsyntaxm3miniconcept",
				"Operations Meta Meta Meta Concept", 180, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		basicOpersSyntaxM3Concept.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		basicOpersSyntaxM3Concept.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		basicOpersSyntaxM3Concept.addModelingAttribute("Name",
				new SyntaxAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));

		basicOpersSyntaxM3Concept.addModelingAttribute("MetaType",
				new SyntaxAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "MetaConcept Type",
						ConceptType.class.getCanonicalName(), "MetaConcept", 0,
						-1, "", "", -1, "", ""));
		// metaBasicConcept.addModelingAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Visible",
				new SyntaxAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", true, 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Name",
				new SyntaxAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Style",
				new SyntaxAttribute("Style", "String", AttributeType.SYNTAX,
						false, "Drawing Style", "refasclaim", 0, -1, "", "",
						-1, "", ""));
		basicOpersSyntaxM3Concept
				.addModelingAttribute("Width", new SyntaxAttribute("Width",
						"Integer", AttributeType.SYNTAX, false,
						"Initial Width", 100, 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept
				.addModelingAttribute("Height", new SyntaxAttribute("Height",
						"Integer", AttributeType.SYNTAX, false,
						"Initial Height", 40, 0, -1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Image",
				new SyntaxAttribute("Image", "String", AttributeType.SYNTAX,
						false, "Image File",
						"/com/variamos/gui/perspeditor/images/claim.png", 0,
						-1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("TopConcept",
				new SyntaxAttribute("TopConcept", "Boolean",
						AttributeType.SYNTAX, false, "Is Top Concept", true, 0,
						-1, "", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		basicOpersSyntaxM3Concept.addModelingAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		basicOpersSyntaxM3Concept.addModelingAttribute("Resizable",
				new SyntaxAttribute("Resizable", "Boolean",
						AttributeType.SYNTAX, false, "Is Resizable", true, 0,
						-1, "", "", -1, "", ""));

		MetaPairwiseRelation basicOpersSyntaxM3ExtendsRelation = new MetaPairwiseRelation(
				"ExtendsRelation",
				false,
				true,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/pl/editor/images/plnode.png", 1);

		// End Syntax M3 Model

		// Begin Basic M2 Model

		OpersConcept basicOpersM2Concept = new OpersConcept();

		// FOR SD and CLAIMS - Review to change
		basicOpersM2Concept.putSemanticAttribute("relationTypesAttributes",
				new SyntaxAttribute("relationTypesAttributes", "Set",
						AttributeType.SYNTAX, false, "relationTypes",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		basicOpersM2Concept.putSemanticAttribute("operationsExpressions",
				new SyntaxAttribute("operationsExpressions", "Set",
						AttributeType.SYNTAX, false,
						"Operations Meta-Model Expressions",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));
		// semConcept.putSemanticAttribute("identifier", new SyntaxAttribute(
		// "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// "", "", -1, "", ""));
		// semConcept.addPropEditableAttribute("01#" + "identifier");
		// semConcept.addPropVisibleAttribute("01#" + "identifier");

		// semConcept.addPanelVisibleAttribute("01#" + "identifier");
		// semConcept.addPanelSpacersAttribute("#" + "identifier" + "#\n\n");

		InstConcept instBasicOpersM2Concept = new InstConcept(
				"basicOpersM2Concept", null, basicOpersM2Concept);

		OpersConcept basicOpersM2PWRel = new OpersConcept("BasicOpersM2PWRel");

		// semPairwiseRelation.putSemanticAttribute("enumerationType",
		// new SemanticAttribute("enumerationType", "Class",
		// AttributeType.OPERATION, false, "enumerationX",
		// InstEnumeration.class.getCanonicalName(),
		// "TypeEnumeration", "String", "", 0, -1, "", "", -1, "",
		// ""));
		//
		// semPairwiseRelation.addPropEditableAttribute("03#" +
		// "enumerationType");
		// semPairwiseRelation.addPropVisibleAttribute("03#" +
		// "enumerationType");

		basicOpersM2PWRel.putSemanticAttribute("relationTypesAttributes",
				new SyntaxAttribute("relationTypesAttributes", "Set",
						AttributeType.SYNTAX, false, "relationTypes",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		basicOpersM2PWRel.putSemanticAttribute("operationsExpressions",
				new SyntaxAttribute("operationsExpressions", "Set",
						AttributeType.SYNTAX, false, "semanticExpressions",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		InstConcept instBasicOpersM2PWRel = new InstConcept(
				"BasicOpersM2PWRel", null, basicOpersM2PWRel);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		instEdge.setIdentifier("pwtoc");
		instEdge.setSupportMetaPairwiseRelation(basicOpersSyntaxM3ExtendsRelation);
		instEdge.setTargetRelation(instBasicOpersM2Concept, true);
		instEdge.setSourceRelation(instBasicOpersM2PWRel, true);

		OpersPairwiseRel basicOpersM2ExtendsRelation = new OpersPairwiseRel(
				"ExtRel", false, null);

		InstConcept instBasicOpersM2ExtendsRelation = new InstConcept(
				"ExtendsRelation", basicOpersSyntaxM3Concept,
				basicOpersM2ExtendsRelation);
		OpersConcept basicOpersM2OTRel = new OpersConcept("basicOpersM2OTRel");

		// semOverTwoRelation.putSemanticAttribute("enumerationType",
		// new SemanticAttribute("enumerationType", "Class",
		// AttributeType.OPERATION, false, "enumerationX",
		// InstEnumeration.class.getCanonicalName(),
		// "TypeEnumeration", "String", "", 0, -1, "", "", -1, "",
		// ""));
		//
		// semOverTwoRelation.addPropEditableAttribute("03#" +
		// "enumerationType");
		// semOverTwoRelation.addPropVisibleAttribute("03#" +
		// "enumerationType");

		basicOpersM2OTRel.putSemanticAttribute("relationTypesAttributes",
				new SyntaxAttribute("relationTypesAttributes", "Set",
						AttributeType.SYNTAX, false, "relationTypes",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		basicOpersM2OTRel.putSemanticAttribute("operationsExpressions",
				new SyntaxAttribute("operationsExpressions", "Set",
						AttributeType.SYNTAX, false, "semanticExpressions",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		InstConcept instBasicOpersM2OTRel = new InstConcept(
				"basicOpersM2OTRel", null, basicOpersM2OTRel);

		instEdge = new InstPairwiseRelation();
		instEdge.setIdentifier("otrtoc");
		instEdge.setSupportMetaPairwiseRelation(basicOpersSyntaxM3ExtendsRelation);
		instEdge.setTargetRelation(instBasicOpersM2Concept, true);
		instEdge.setSourceRelation(instBasicOpersM2OTRel, true);

		OpersConcept basicOpersM2SemModel = new OpersConcept(
				"BasicOpersM2Model");

		basicOpersM2SemModel.putSemanticAttribute("name",
				new SemanticAttribute("name", "String",
						AttributeType.OPERATION, false, "Name", null, 0, 6, "",
						"", 6, "", ""));
		basicOpersM2SemModel.addPropEditableAttribute("06#" + "name");
		basicOpersM2SemModel.addPropVisibleAttribute("06#" + "name");
		basicOpersM2SemModel.addPanelVisibleAttribute("06#" + "name");
		basicOpersM2SemModel.addPanelSpacersAttribute("#" + "name" + "#");

		InstConcept instBasicOpersM2SemModel = new InstConcept(
				"BasicOpersM2Model", null, basicOpersM2SemModel);

		OpersConcept basicOpebrsM2OperGroup = new OpersConcept(
				"BasicOpersM2OperGroup");

		// 4 config/simul
		// 2 req model
		List<Integer> dom = new ArrayList<Integer>();
		dom.add(2);
		dom.add(4);
		IntervalDomain d = new IntervalDomain();
		d.setRangeValues(dom);

		basicOpebrsM2OperGroup.putSemanticAttribute("menuType",
				new SemanticAttribute("menuType", "Integer",
						AttributeType.OPERATION, "Oper Group Type", 4, false,
						d, 0, 5, "", "", 5, "", ""));
		basicOpebrsM2OperGroup.addPropEditableAttribute("05#" + "menuType");
		basicOpebrsM2OperGroup.addPropVisibleAttribute("05#" + "menuType");

		basicOpebrsM2OperGroup.putSemanticAttribute("visible",
				new SemanticAttribute("visible", "Boolean",
						AttributeType.OPERATION, false, "Visible", true, 0, 8,
						"", "", 8, "", ""));
		basicOpebrsM2OperGroup.addPropEditableAttribute("08#" + "visible");
		basicOpebrsM2OperGroup.addPropVisibleAttribute("08#" + "visible");

		basicOpebrsM2OperGroup.putSemanticAttribute("name",
				new SemanticAttribute("name", "String",
						AttributeType.OPERATION, false, "Name", null, 0, 6, "",
						"", 6, "", ""));
		basicOpebrsM2OperGroup.addPropEditableAttribute("06#" + "name");
		basicOpebrsM2OperGroup.addPropVisibleAttribute("06#" + "name");
		basicOpebrsM2OperGroup.addPanelVisibleAttribute("06#" + "name");
		basicOpebrsM2OperGroup.addPanelSpacersAttribute("#" + "name" + "#");

		basicOpebrsM2OperGroup.putSemanticAttribute("shortcut",
				new SemanticAttribute("shortcut", "String",
						AttributeType.OPERATION, false, "Shortcut", null, 0, 7,
						"", "", 7, "", ""));
		basicOpebrsM2OperGroup.addPropEditableAttribute("07#" + "shortcut");
		basicOpebrsM2OperGroup.addPropVisibleAttribute("07#" + "shortcut");

		basicOpebrsM2OperGroup.putSemanticAttribute("Index",
				new SemanticAttribute("Index", "Integer",
						AttributeType.OPERATION, false, "Position", 1, 0, 9,
						"", "", 9, "", ""));
		basicOpebrsM2OperGroup.addPropEditableAttribute("09#" + "Index");
		basicOpebrsM2OperGroup.addPropVisibleAttribute("09#" + "Index");

		InstConcept instBasicOpersM2OperGroup = new InstConcept(
				"BasicOpersM2OperGroup", null, basicOpebrsM2OperGroup);

		OpersConcept basicOpersM2OperAction = new OpersConcept(
				"BasicOpersM2Operation");

		basicOpersM2OperAction.putSemanticAttribute("name",
				new SemanticAttribute("name", "String",
						AttributeType.OPERATION, false, "Name", null, 0, 6, "",
						"", 6, "", ""));
		basicOpersM2OperAction.putSemanticAttribute("shortcut",
				new SemanticAttribute("shortcut", "String",
						AttributeType.OPERATION, false, "Shortcut", null, 0, 7,
						"", "", 7, "", ""));
		basicOpersM2OperAction.putSemanticAttribute("Index",
				new SemanticAttribute("Index", "Integer",
						AttributeType.OPERATION, false, "Position", 1, 0, 8,
						"", "", 8, "", ""));
		basicOpersM2OperAction.putSemanticAttribute("iteration",
				new SemanticAttribute("iteration", "Boolean",
						AttributeType.OPERATION, false, "Iterate Button",
						false, 0, 9, "", "", 9, "", ""));
		basicOpersM2OperAction.putSemanticAttribute("iterationName",
				new SemanticAttribute("iterationName", "String",
						AttributeType.OPERATION, false, "Iterate Name", null,
						0, 6, "", "", 6, "", ""));
		basicOpersM2OperAction.putSemanticAttribute("prevSpacer",
				new SemanticAttribute("prevSpacer", "Boolean",
						AttributeType.OPERATION, false, "Add Previous Spacer",
						false, 0, 9, "", "", 9, "", ""));

		basicOpersM2OperAction.addPropEditableAttribute("06#" + "name");
		basicOpersM2OperAction.addPropVisibleAttribute("06#" + "name");
		basicOpersM2OperAction.addPanelVisibleAttribute("06#" + "name");
		basicOpersM2OperAction.addPanelSpacersAttribute("#" + "name" + "#");

		basicOpersM2OperAction.addPropEditableAttribute("07#" + "shortcut");
		basicOpersM2OperAction.addPropVisibleAttribute("07#" + "shortcut");

		basicOpersM2OperAction.addPropEditableAttribute("08#" + "Index");
		basicOpersM2OperAction.addPropVisibleAttribute("08#" + "Index");

		basicOpersM2OperAction.addPropEditableAttribute("09#" + "iteration");
		basicOpersM2OperAction.addPropVisibleAttribute("09#" + "iteration");

		basicOpersM2OperAction
				.addPropEditableAttribute("09#" + "iterationName");
		basicOpersM2OperAction.addPropVisibleAttribute("09#" + "iterationName");

		basicOpersM2OperAction.addPropEditableAttribute("10#" + "prevSpacer");
		basicOpersM2OperAction.addPropVisibleAttribute("10#" + "prevSpacer");

		InstConcept instBasicOpersM2OperAction = new InstConcept(
				"BasicOpersM2Operation", null, basicOpersM2OperAction);

		OpersConcept basicOpersM2OperSubAction = new OpersConcept(
				"BasicOpersM2SubOper");

		basicOpersM2OperSubAction.putSemanticAttribute("name",
				new SemanticAttribute("name", "String",
						AttributeType.OPERATION, false, "Name", null, 0, 6, "",
						"", 6, "", ""));
		basicOpersM2OperSubAction.addPropEditableAttribute("06#" + "name");
		basicOpersM2OperSubAction.addPropVisibleAttribute("06#" + "name");
		basicOpersM2OperSubAction.addPanelVisibleAttribute("06#" + "name");
		basicOpersM2OperSubAction.addPanelSpacersAttribute("#" + "name" + "#");

		basicOpersM2OperSubAction.putSemanticAttribute("Index",
				new SemanticAttribute("Index", "Integer",
						AttributeType.OPERATION, false, "Position", 1, 0, 6,
						"", "", 6, "", ""));
		basicOpersM2OperSubAction.addPropEditableAttribute("08#" + "Index");
		basicOpersM2OperSubAction.addPropVisibleAttribute("08#" + "Index");

		basicOpersM2OperSubAction
				.putSemanticAttribute("iteration",
						new SyntaxAttribute("iteration", "Boolean",
								AttributeType.OPERATION, false,
								"Iterate Sub-Operation", false, 0, 6, "", "",
								6, "", ""));
		basicOpersM2OperSubAction.addPropEditableAttribute("09#" + "iteration");
		basicOpersM2OperSubAction.addPropVisibleAttribute("09#" + "iteration");

		basicOpersM2OperSubAction.putSemanticAttribute("type",
				new SyntaxAttribute("type", "Enumeration",
						AttributeType.OPERATION, false, "Type",
						OperationSubActionType.class.getCanonicalName(),
						"SINGLEUPDATE", 0, 6, "", "", 6, "", ""));
		basicOpersM2OperSubAction.addPropEditableAttribute("10#" + "type");
		basicOpersM2OperSubAction.addPropVisibleAttribute("10#" + "type");

		basicOpersM2OperSubAction.putSemanticAttribute(
				"exptype",
				new SyntaxAttribute("exptype", "Set", AttributeType.SYNTAX,
						false, "exptype", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, 2, "", "", 2, "#\n",
						""));

		InstConcept instBasicOpersM2OperSubAction = new InstConcept(
				"BasicOpersM2SubOper", null, basicOpersM2OperSubAction);

		OpersConcept basicOpersM2Labeling = new OpersConcept(
				"BasicOpersM2Labeling");

		basicOpersM2Labeling.putSemanticAttribute("labelId",
				new SemanticAttribute("labelId", "String",
						AttributeType.OPERATION, false, "Label ID", null, 0, 6,
						"", "", 6, "", ""));
		basicOpersM2Labeling.putSemanticAttribute("position",
				new SemanticAttribute("position", "Integer",
						AttributeType.OPERATION, false, "Position", 1, 0, 6,
						"", "", 6, "", ""));
		basicOpersM2Labeling.putSemanticAttribute("once",
				new SemanticAttribute("once", "Boolean",
						AttributeType.OPERATION, false, "Once", false, 0, 6,
						"", "", 6, "", ""));

		basicOpersM2Labeling.putSemanticAttribute(
				"sortorder",
				new SyntaxAttribute("sortorder", "Set", AttributeType.SYNTAX,
						false, "sortorder", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, 2, "", "", 2, "#\n",
						""));
		basicOpersM2Labeling.addPanelVisibleAttribute("04#" + "identifier");
		basicOpersM2Labeling.addPanelSpacersAttribute("#" + "identifier" + "#");

		basicOpersM2Labeling.addPropEditableAttribute("06#" + "labelId");
		basicOpersM2Labeling.addPropVisibleAttribute("06#" + "labelId");
		basicOpersM2Labeling.addPropEditableAttribute("07#" + "position");
		basicOpersM2Labeling.addPropVisibleAttribute("07#" + "position");
		basicOpersM2Labeling.addPropEditableAttribute("08#" + "once");
		basicOpersM2Labeling.addPropVisibleAttribute("08#" + "once");

		InstConcept instBasicOpersM2Labeling = new InstConcept(
				"BasicOpersM2Labeling", null, basicOpersM2Labeling);

		OpersConcept basicOpersM2ExpType = new OpersConcept(
				"BasicOpersM2ExpType");

		basicOpersM2ExpType.putSemanticAttribute("suboperexptype",
				new SyntaxAttribute("suboperexptype", "Enumeration",
						AttributeType.OPERATION, false, "Expression Type",
						OperationSubActionExecType.class.getCanonicalName(),
						"NORMAL", 0, 6, "", "", 6, "", ""));

		InstConcept instBasicOpersM2ExpType = new InstConcept(
				"BasicOpersM2ExpType", null, basicOpersM2ExpType);

		// End Basic M2 Model

		// Begin Opers M2 Model

		MetaConcept infraSyntaxOpersM2InfraConcept = new MetaConcept(
				'N',
				"InfraSyntaxOpersM2InfraConcept",
				false,
				false,
				"InfraSyntaxOpersM2InfraConcept",
				"infrasyntaxm2biggrayconcept",
				"Operations Infra MetaMetaConcept: Define Infrastructure (fixed) MMConcepts for the operations",
				100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, instBasicOpersM2Concept, true);

		infraSyntaxOpersM2InfraConcept.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2InfraConcept.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2InfraConcept = new InstConcept(
				"InfraSyntaxOpersM2InfraConcept", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2InfraConcept);

		variabilityInstVertex.put("InfraSyntaxOpersM2InfraConcept",
				instInfraSyntaxOpersM2InfraConcept);

		MetaConcept infraSyntaxOpersM2Concept = new MetaConcept(
				'C',
				"InfraSyntaxOpersM2Concept",
				true,
				true,
				"InfraSyntaxOpersM2Concept",
				"infrasyntaxm2concept",
				"Operations MetaMetaConcept: Define a MMConcept for the operations",
				100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, instBasicOpersM2Concept, true);

		infraSyntaxOpersM2Concept.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2Concept.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2Concept = new InstConcept(
				"InfraSyntaxOpersM2Concept", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2Concept);

		variabilityInstVertex.put("InfraSyntaxOpersM2Concept",
				instInfraSyntaxOpersM2Concept);

		MetaPairwiseRelation metaMetaPairwiseRelExtends = new MetaPairwiseRelation(
				"ExtendsRelation",
				false,
				true,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instBasicOpersM2ExtendsRelation);

		InstPairwiseRelation rel = new InstPairwiseRelation(
				basicOpersM2ExtendsRelation);
		rel.setEditableMetaElement(metaMetaPairwiseRelExtends);
		rel.setIdentifier("ExtendsCCRel");
		rel.setTargetRelation(instBasicOpersM2Concept, true);
		rel.setSourceRelation(instBasicOpersM2PWRel, true);

		MetaConcept infraSyntaxOpersM2PWRel = new MetaConcept(
				'W',
				"InfraSyntaxOpersM2InfraPWRel",
				false,
				false,
				"InfraSyntaxOpersM2InfraPWRel",
				"infrasyntaxm2concept",
				"Operations Infrastructure (fiexd) MMPairWise Relation: Defines a direct relation for the operations meta-model",
				150, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, instBasicOpersM2PWRel, true);

		infraSyntaxOpersM2PWRel.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2PWRel.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2PWRel = new InstConcept(
				"InfraSyntaxOpersM2InfraPWRel", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2PWRel);
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("InfraSyntaxOpersM2InfraPWRel",
				instInfraSyntaxOpersM2PWRel);

		MetaConcept metaMetaPairwiseRel = new MetaConcept(
				'P',
				"InfraSyntaxOpersM2PWRel",
				true,
				true,
				"InfraSyntaxOpersM2PWRel",
				"refasenumeration",
				"Operations MMPairWise Relation: Defines a direct relation for the operations meta-model",
				150, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, instBasicOpersM2PWRel, true);

		metaMetaPairwiseRel.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaMetaPairwiseRel.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instPairWiseRelation = new InstConcept(
				"InfraSyntaxOpersM2PWRel", basicOpersSyntaxM3Concept,
				metaMetaPairwiseRel);
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("InfraSyntaxOpersM2PWRel",
				instPairWiseRelation);

		/*
		 * SemanticConcept semEnumeration = new SemanticConcept(semConcept,
		 * "Enumeration"); semEnumeration.setSemanticAttribute("value", new
		 * SemanticAttribute("value", "Set", false, "value",
		 * InstAttribute.class.getCanonicalName(), new
		 * ArrayList<InstAttribute>(), 0, 1, "", "", -1, "", ""));
		 * semEnumeration.addPropEditableAttribute("01#" + "value");
		 * semEnumeration.addPropVisibleAttribute("01#" + "value");
		 * 
		 * InstConcept instSemEnumeration = new InstConcept("Enumeration", null,
		 * semEnumeration);
		 */
		// MetaEnumeration enumeration = new MetaEnumeration(
		// "TypeEnumeration",
		// true,
		// "TypeEnumeration",
		// "refasenumeration",
		// "Semantic Enumeration Type: Define an enumeration of types used in paiwise and overtwo relations",
		// 100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
		// true, Color.BLUE.toString(), 3, true);
		//
		// enumeration.addPanelVisibleAttribute("04#"
		// + MetaConcept.VAR_USERIDENTIFIER);
		// enumeration.addPanelSpacersAttribute("#"
		// + MetaConcept.VAR_USERIDENTIFIER + "#\n\n");
		//
		// enumeration.addModelingAttribute("value", "Set", false, "value",
		// InstAttribute.class.getCanonicalName(),
		// new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "", "");
		// enumeration.addPropEditableAttribute("01#" + "value");
		// enumeration.addPropVisibleAttribute("01#" + "value");

		MetaConcept infraSyntaxOpersM2InfraOTRel = new MetaConcept('T',
				"InfraSyntaxOpersM2InfraOTRel", false, false,
				"InfraSyntaxOpersM2InfraOTRel", "infrasyntaxm2concept",
				"Over Two Relation", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instBasicOpersM2OTRel, true);

		infraSyntaxOpersM2InfraOTRel.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2InfraOTRel.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2InfraOTRel = new InstConcept(
				"InfraSyntaxOpersM2InfraOTRel", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2InfraOTRel);
		variabilityInstVertex.put("InfraSyntaxOpersM2InfraOTRel",
				instInfraSyntaxOpersM2InfraOTRel);

		MetaConcept infraSyntaxOpersM2OTRel = new MetaConcept('O',
				"InfraSyntaxOpersM2OTRel", true, true,
				"InfraSyntaxOpersM2OTRel", "infrasyntaxopersm2miniconcept",
				"Over Two Relation", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instBasicOpersM2OTRel, true);

		infraSyntaxOpersM2OTRel.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2OTRel.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2OTRel = new InstConcept(
				"InfraSyntaxOpersM2OTRel", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OTRel);
		variabilityInstVertex.put("InfraSyntaxOpersM2OTRel",
				instInfraSyntaxOpersM2OTRel);

		List<IntOpersRelType> basicOpersM2ExtRelList = new ArrayList<IntOpersRelType>();
		basicOpersM2ExtRelList.add(new OpersRelType("extends", "extends",
				"extends", false, true, true, 1, -1, 1, 1));

		List<IntOpersRelType> basicOpersM2AsoRellList = new ArrayList<IntOpersRelType>();
		basicOpersM2AsoRellList.add(new OpersRelType("association",
				"association", "association", false, true, true, 1, -1, 1, 1));

		OpersPairwiseRel basicOpersM2ExtRel = new OpersPairwiseRel("ExtRel",
				false, basicOpersM2ExtRelList);

		InstConcept instBasicOpersM2ExtRel = new InstConcept("ExtendsRelation",
				basicOpersSyntaxM3Concept, basicOpersM2ExtRel);

		OpersPairwiseRel basicOpersM2AsoRel = new OpersPairwiseRel("AsoRel",
				false, basicOpersM2AsoRellList);

		InstConcept instBasicOpersM2AsoRel = new InstConcept(
				"AssociationRelation", basicOpersSyntaxM3Concept,
				basicOpersM2AsoRel);

		// variabilityInstVertex.put("TypeEnumeration", new InstConcept(
		// "TypeEnumeration", metaBasicConcept, enumeration));

		MetaPairwiseRelation infraSyntaxOpersM2AsoRel = new MetaPairwiseRelation(
				"InfraSyntaxOpersM2AsoRel", false, true,
				"Association Relation", "defaultAsso",
				"Association Relation: ", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instBasicOpersM2AsoRel);

		infraSyntaxOpersM2AsoRel.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2AsoRel.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		constraintInstEdges.put("InfraSyntaxOpersM2AsoRel",
				new InstPairwiseRelation(infraSyntaxOpersM2AsoRel));

		MetaPairwiseRelation infraSyntaxOpersM2ExtRel = new MetaPairwiseRelation(
				"ExtendsRelation",
				false,
				true,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instBasicOpersM2ExtRel);

		infraSyntaxOpersM2ExtRel.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2ExtRel.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		rel = new InstPairwiseRelation(basicOpersM2ExtRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsCCRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("ExtendsCCRel", rel);

		rel = new InstPairwiseRelation(basicOpersM2ExtRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsCICRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2InfraConcept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("ExtendsCICRel", rel);

		rel = new InstPairwiseRelation(basicOpersM2ExtRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsOCRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("ExtendsOCRel", rel);

		rel = new InstPairwiseRelation(basicOpersM2ExtRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsOORel");
		rel.setTargetRelation(instInfraSyntaxOpersM2OTRel, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("ExtendsOORel", rel);

		rel = new InstPairwiseRelation(basicOpersM2ExtRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsOIORel");
		rel.setTargetRelation(instInfraSyntaxOpersM2InfraOTRel, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("ExtendsOIORel", rel);

		rel = new InstPairwiseRelation(basicOpersM2ExtRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsPIPRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2PWRel, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("ExtendsPIPRel", rel);

		rel = new InstPairwiseRelation(basicOpersM2AsoRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoCPWRel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("AssoCPWRel", rel);

		rel = new InstPairwiseRelation(basicOpersM2AsoRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoPWCRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoPWCRel", rel);

		rel = new InstPairwiseRelation(basicOpersM2AsoRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoOCPWRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoOCPWRel", rel);

		rel = new InstPairwiseRelation(basicOpersM2AsoRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoPWOCRel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("AssoPWOCRel", rel);

		rel = new InstPairwiseRelation(basicOpersM2AsoRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoCOPWRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2OTRel, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoCOPWRel", rel);

		rel = new InstPairwiseRelation(basicOpersM2AsoRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoPWCORel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("AssoPWCORel", rel);

		MetaConcept infraSyntaxOpersM2OperGroup = new MetaConcept('M',
				"InfraSyntaxOpersM2OperGroup", true, true,
				"InfraSyntaxOpersM2OperGroup", "infrasyntaxopersm2miniconcept",
				"Operation Group", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instBasicOpersM2OperGroup, true);

		infraSyntaxOpersM2OperGroup.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2OperGroup.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2OperGroup = new InstConcept(
				"InfraSyntaxOpersM2OperGroup", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperGroup);
		variabilityInstVertex.put("InfraSyntaxOpersM2OperGroup",
				instInfraSyntaxOpersM2OperGroup);

		MetaConcept infraSyntaxOpersM2MetaModel = new MetaConcept('C',
				"InfraSyntaxOpersM2Model", true, true,
				"InfraSyntaxOpersM2Model", "infrasyntaxopersm2miniconcept",
				"Semantic Model", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instBasicOpersM2SemModel, true);

		infraSyntaxOpersM2MetaModel.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2MetaModel.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2MetaModel = new InstConcept(
				"InfraSyntaxOpersM2Model", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2MetaModel);
		variabilityInstVertex.put("InfraSyntaxOpersM2Model",
				instInfraSyntaxOpersM2MetaModel);

		MetaConcept infraSyntaxOpersM2OperAction = new MetaConcept('A',
				"InfraSyntaxOpersM2Operation", true, true,
				"InfraSyntaxOpersM2Operation", "infrasyntaxopersm2miniconcept",
				"Operation Action", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instBasicOpersM2OperAction, true);

		infraSyntaxOpersM2OperAction.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2OperAction.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2OperAction = new InstConcept(
				"InfraSyntaxOpersM2Operation", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperAction);
		variabilityInstVertex.put("InfraSyntaxOpersM2Operation",
				instInfraSyntaxOpersM2OperAction);

		MetaConcept infraSyntaxOpersM2OperSubAction = new MetaConcept('S',
				"InfraSyntaxOpersM2SubOper", true, true,
				"InfraSyntaxOpersM2SubOper", "infrasyntaxopersm2miniconcept",
				"Operation Action", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instBasicOpersM2OperSubAction, true);

		infraSyntaxOpersM2OperSubAction.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2OperSubAction.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2OperSubAction = new InstConcept(
				"InfraSyntaxOpersM2SubOper", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperSubAction);
		variabilityInstVertex.put("InfraSyntaxOpersM2SubOper",
				instInfraSyntaxOpersM2OperSubAction);

		MetaConcept infraSyntaxOpersM2OperLabeling = new MetaConcept('S',
				"InfraSyntaxOpersM2Labeling", true, true,
				"InfraSyntaxOpersM2Labeling", "infrasyntaxopersm2miniconcept",
				"Operation Labeling", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instBasicOpersM2Labeling, true);

		infraSyntaxOpersM2OperLabeling.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2OperLabeling.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2OperLabeling = new InstConcept(
				"InfraSyntaxOpersM2Labeling", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperLabeling);
		variabilityInstVertex.put("InfraSyntaxOpersM2Labeling",
				instInfraSyntaxOpersM2OperLabeling);

		MetaConcept infraSyntaxOpersM2ExpType = new MetaConcept('S',
				"InfraSyntaxOpersM2ExpType", false, true,
				"InfraSyntaxOpersM2ExpType", "infrasyntaxopersm2miniconcept",
				"Operation Expression Type", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instBasicOpersM2ExpType, true);

		infraSyntaxOpersM2ExpType.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxOpersM2ExpType.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxOpersM2ExpType = new InstConcept(
				"InfraSyntaxOpersM2Labeling", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2ExpType);
		variabilityInstVertex.put("InfraSyntaxOpersM2ExpType",
				instInfraSyntaxOpersM2ExpType);

		rel = new InstPairwiseRelation(basicOpersM2AsoRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoMenu-Act");
		rel.setTargetRelation(instInfraSyntaxOpersM2OperAction, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperGroup, true);
		constraintInstEdges.put("AssoMenu-Act", rel);

		rel = new InstPairwiseRelation(basicOpersM2AsoRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoAct-SubAct");
		rel.setTargetRelation(instInfraSyntaxOpersM2OperSubAction, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperAction, true);
		constraintInstEdges.put("AssoAct-SubAct", rel);

		rel = new InstPairwiseRelation(basicOpersM2AsoRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoSubAct-Lab");
		rel.setTargetRelation(instInfraSyntaxOpersM2OperLabeling, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperSubAction, true);
		constraintInstEdges.put("AssoSubAct-Lab", rel);

		rel = new InstPairwiseRelation(basicOpersM2AsoRel);
		rel.setEditableMetaElement(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoSubAct-ExpType");
		rel.setTargetRelation(instInfraSyntaxOpersM2ExpType, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperSubAction, true);
		constraintInstEdges.put("AssoSubAct-ExpType", rel);
	}

	/**
	 * Creates the elements (objects) to support the syntax Meta Model. Concepts
	 * are displayed in the palette of the syntax perspective. (PWAssociations
	 * can be used)
	 */
	private void createSyntaxInfrastructure() {

		// Begin Syntax M3 Model

		MetaConcept infraBasicSyntaxOpersM3Concept = new MetaConcept('C',
				"SMMMConcept", true, true, "SMMMConcept", "refasminiclass",
				"Syntax Meta Meta Meta Concept", 180, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraBasicSyntaxOpersM3Concept.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraBasicSyntaxOpersM3Concept.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Name",
				new SyntaxAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));

		infraBasicSyntaxOpersM3Concept.addModelingAttribute("MetaType",
				new SyntaxAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "MetaConcept Type",
						ConceptType.class.getCanonicalName(), "MetaConcept", 0,
						-1, "", "", -1, "", ""));
		// metaBasicConcept.addModelingAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Visible",
				new SyntaxAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", true, 0, -1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Name",
				new SyntaxAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Style",
				new SyntaxAttribute("Style", "String", AttributeType.SYNTAX,
						false, "Drawing Style", "refasclaim", 0, -1, "", "",
						-1, "", ""));
		infraBasicSyntaxOpersM3Concept
				.addModelingAttribute("Width", new SyntaxAttribute("Width",
						"Integer", AttributeType.SYNTAX, false,
						"Initial Width", 100, 0, -1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept
				.addModelingAttribute("Height", new SyntaxAttribute("Height",
						"Integer", AttributeType.SYNTAX, false,
						"Initial Height", 40, 0, -1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Image",
				new SyntaxAttribute("Image", "String", AttributeType.SYNTAX,
						false, "Image File",
						"/com/variamos/gui/perspeditor/images/claim.png", 0,
						-1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("TopConcept",
				new SyntaxAttribute("TopConcept", "Boolean",
						AttributeType.SYNTAX, false, "Is Top Concept", true, 0,
						-1, "", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		infraBasicSyntaxOpersM3Concept.addModelingAttribute("Resizable",
				new SyntaxAttribute("Resizable", "Boolean",
						AttributeType.SYNTAX, false, "Is Resizable", true, 0,
						-1, "", "", -1, "", ""));

		// End Syntax M3 Model

		// Begin Opers M2 Model

		OpersConcept infraOpersM2View = new OpersConcept();

		infraOpersM2View.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaConcept Type", ConceptType.class.getCanonicalName(),
				"MetaView", 0, -1, "", "", -1, "", ""));
		infraOpersM2View.putSemanticAttribute("Index", new SyntaxAttribute(
				"Index", "Integer", AttributeType.SYNTAX, false, "View Index",
				3, 0, -1, "", "", -1, "", ""));
		infraOpersM2View.putSemanticAttribute("Identifier",
				new SyntaxAttribute("Identifier", "String",
						AttributeType.SYNTAX, false, "View Identifier", "", 0,
						-1, "", "", -1, "", ""));
		infraOpersM2View.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				true, 0, -1, "", "", -1, "", ""));
		// semView.putSemanticAttribute("Parent", new SyntaxAttribute("Parent",
		// "String", AttributeType.SYNTAX, false, "Parent View", "", 0,
		// -1, "", "", -1, "", ""));
		infraOpersM2View.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", 0, -1, "", "", -1, "", ""));
		infraOpersM2View.putSemanticAttribute("Style", new SyntaxAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "refasclaim", 0, -1, "", "", -1, "", ""));
		infraOpersM2View.putSemanticAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));
		infraOpersM2View.putSemanticAttribute("Width", new SyntaxAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", 100, 0, -1, "", "", -1, "", ""));
		infraOpersM2View.putSemanticAttribute("Height", new SyntaxAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", 40, 0, -1, "", "", -1, "", ""));
		infraOpersM2View.putSemanticAttribute("Image", new SyntaxAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		infraOpersM2View.putSemanticAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		infraOpersM2View.putSemanticAttribute("PaletteNames",
				new SyntaxAttribute("PaletteNames", "String",
						AttributeType.SYNTAX, false, "Palette Name", "", 0, -1,
						"", "", -1, "", ""));

		infraOpersM2View.addPropEditableAttribute("03#" + "PaletteNames");
		infraOpersM2View.addPropVisibleAttribute("03#" + "PaletteNames");
		infraOpersM2View.addPanelVisibleAttribute("05#" + "PaletteNames" + "#"
				+ "PaletteNames" + "#!=#" + "" + "#" + "");
		infraOpersM2View.addPanelSpacersAttribute("{Palettes:#"
				+ "PaletteNames" + "#}\n\n");
		infraOpersM2View.addPropVisibleAttribute("00#" + "MetaType");
		// semView.addPropEditableAttribute("01#" + "Identifier");
		// semView.addPropVisibleAttribute("01#" + "Identifier");
		infraOpersM2View.addPropEditableAttribute("02#" + "Index");
		infraOpersM2View.addPropVisibleAttribute("02#" + "Index");
		infraOpersM2View.addPropEditableAttribute("03#" + "Visible");
		infraOpersM2View.addPropVisibleAttribute("03#" + "Visible");
		// semView.addPropEditableAttribute("04#" + "Parent");
		// semView.addPropVisibleAttribute("04#" + "Parent");
		infraOpersM2View.addPropEditableAttribute("05#" + "Name");
		infraOpersM2View.addPropVisibleAttribute("05#" + "Name");
		infraOpersM2View.addPropEditableAttribute("06#" + "Style");
		infraOpersM2View.addPropVisibleAttribute("06#" + "Style");
		infraOpersM2View.addPropEditableAttribute("07#" + "Description");
		infraOpersM2View.addPropVisibleAttribute("07#" + "Description");
		infraOpersM2View.addPropEditableAttribute("08#" + "Width");
		infraOpersM2View.addPropVisibleAttribute("08#" + "Width");
		infraOpersM2View.addPropEditableAttribute("09#" + "Height");
		infraOpersM2View.addPropVisibleAttribute("09#" + "Height");
		infraOpersM2View.addPropEditableAttribute("10#" + "Image");
		infraOpersM2View.addPropVisibleAttribute("10#" + "Image");
		// semView.addDisPropEditableAttribute("11#" + "BorderStroke");
		infraOpersM2View.addPropVisibleAttribute("11#" + "BorderStroke");

		// semView.addPanelVisibleAttribute("01#" + "Name");
		// semView.addPanelSpacersAttribute("#" + "Name" + "#");

		InstConcept instInfraOpersM2View = new InstConcept("SMMView", null,
				infraOpersM2View);

		OpersConcept infraOpersM2Concept = new OpersConcept("SMMConcept");

		infraOpersM2Concept.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false,
				"Meta Concept Name", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2Concept.putSemanticAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));

		infraOpersM2Concept.putSemanticAttribute("MetaType",
				new SyntaxAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "MetaConcept Type",
						ConceptType.class.getCanonicalName(), "MetaConcept", 0,
						-1, "", "", -1, "", ""));
		// semVertex.putSemanticAttribute("Identifier", new SyntaxAttribute(
		// "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// "", "", -1, "", ""));
		infraOpersM2Concept.putSemanticAttribute("Visible",
				new SyntaxAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", true, 0, -1, "", "", -1, "", ""));
		infraOpersM2Concept.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "MConcept Name",
				"", 0, -1, "", "", -1, "", ""));
		infraOpersM2Concept.putSemanticAttribute("Style", new SyntaxAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "refasclaim", 0, -1, "", "", -1, "", ""));
		infraOpersM2Concept.putSemanticAttribute("Width", new SyntaxAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", 100, 0, -1, "", "", -1, "", ""));
		infraOpersM2Concept.putSemanticAttribute("Height", new SyntaxAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", 40, 0, -1, "", "", -1, "", ""));
		infraOpersM2Concept.putSemanticAttribute("Image", new SyntaxAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		infraOpersM2Concept.putSemanticAttribute("TopConcept",
				new SyntaxAttribute("TopConcept", "Boolean",
						AttributeType.SYNTAX, false, "Is Top Concept", true, 0,
						-1, "", "", -1, "", ""));
		infraOpersM2Concept.putSemanticAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		infraOpersM2Concept.putSemanticAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		infraOpersM2Concept.putSemanticAttribute("Resizable",
				new SyntaxAttribute("Resizable", "Boolean",
						AttributeType.SYNTAX, false, "Is Resizable", true, 0,
						-1, "", "", -1, "", ""));
		infraOpersM2Concept.putSemanticAttribute("value", new SyntaxAttribute(
				"value", "Set", AttributeType.SYNTAX, false, "values", "", 0,
				-1, "", "", -1, "", ""));

		infraOpersM2Concept.addPropVisibleAttribute("00#" + "MetaType");
		// semVertex.addPropEditableAttribute("01#" + "Identifier");
		// semVertex.addPropVisibleAttribute("01#" + "Identifier");
		infraOpersM2Concept.addPropEditableAttribute("02#" + "Visible");
		infraOpersM2Concept.addPropVisibleAttribute("02#" + "Visible");
		infraOpersM2Concept.addPropEditableAttribute("03#" + "Name");
		infraOpersM2Concept.addPropVisibleAttribute("03#" + "Name");
		infraOpersM2Concept.addPropEditableAttribute("04#" + "Style");
		infraOpersM2Concept.addPropVisibleAttribute("04#" + "Style");
		infraOpersM2Concept.addPropEditableAttribute("05#" + "Description");
		infraOpersM2Concept.addPropVisibleAttribute("05#" + "Description");
		infraOpersM2Concept.addPropEditableAttribute("06#" + "Width");
		infraOpersM2Concept.addPropVisibleAttribute("06#" + "Width");
		infraOpersM2Concept.addPropEditableAttribute("07#" + "Height");
		infraOpersM2Concept.addPropVisibleAttribute("07#" + "Height");
		infraOpersM2Concept.addPropEditableAttribute("08#" + "Image");
		infraOpersM2Concept.addPropVisibleAttribute("08#" + "Image");
		infraOpersM2Concept.addPropEditableAttribute("09#" + "TopConcept");
		infraOpersM2Concept.addPropVisibleAttribute("09#" + "TopConcept");
		infraOpersM2Concept.addPropEditableAttribute("10#" + "BackgroundColor");
		infraOpersM2Concept.addPropVisibleAttribute("10#" + "BackgroundColor");
		infraOpersM2Concept.addPropEditableAttribute("11#" + "BorderStroke");
		infraOpersM2Concept.addPropVisibleAttribute("11#" + "BorderStroke");
		infraOpersM2Concept.addPropEditableAttribute("12#" + "Resizable");
		infraOpersM2Concept.addPropVisibleAttribute("12#" + "Resizable");

		infraOpersM2Concept.putSemanticAttribute("OperationsMMType",
				new SemanticAttribute("OperationsMMType", "Class",
						AttributeType.OPERATION, false, "Operations MMType",
						OpersConcept.class.getCanonicalName(), "C", null, "",
						0, -1, "", "", -1, "", ""));

		infraOpersM2Concept
				.addPropEditableAttribute("00#" + "OperationsMMType");
		infraOpersM2Concept.addPropVisibleAttribute("00#" + "OperationsMMType");

		InstConcept instInfraOpersM2Concept = new InstConcept("SMMConcept",
				null, infraOpersM2Concept);

		MetaConcept infraSyntaxM2Concept = new MetaConcept('C', "SMMConcept",
				true, true, "SMMConcept", "infrasyntaxm2miniconcept",
				"Syntax Meta Concept", 150, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instInfraOpersM2Concept, true);

		infraSyntaxM2Concept.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxM2Concept.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		infraSyntaxM2Concept.addPanelVisibleAttribute("00#"
				+ "OperationsMMType");
		infraSyntaxM2Concept
				.addPanelSpacersAttribute("<<MetaConcept>>\n{OperType:\"#"
						+ "OperationsMMType" + "#\"}\n");
		// concept.addPanelVisibleAttribute("01#" + "Name");
		// concept.addPanelSpacersAttribute("#" + "Name" + "#\n\n");

		InstConcept instInfraSyntaxOpersM2Concept = new InstConcept(
				"SMMConcept", infraBasicSyntaxOpersM3Concept,
				infraSyntaxM2Concept);
		variabilityInstVertex.put("SMMConcept", instInfraSyntaxOpersM2Concept);

		OpersConcept infraOpersM2OTRel = new OpersConcept("SMMOverTwoRelation");

		infraOpersM2OTRel.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false,
				"Meta Concept Name", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));

		infraOpersM2OTRel.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaConcept Type", ConceptType.class.getCanonicalName(),
				"MetaConcept", 0, -1, "", "", -1, "", ""));
		// semVertex.putSemanticAttribute("Identifier", new SyntaxAttribute(
		// "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				true, 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "MConcept Name",
				"", 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Style", new SyntaxAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "refasclaim", 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Width", new SyntaxAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", 100, 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Height", new SyntaxAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", 40, 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Image", new SyntaxAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("TopConcept",
				new SyntaxAttribute("TopConcept", "Boolean",
						AttributeType.SYNTAX, false, "Is Top Concept", true, 0,
						-1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		infraOpersM2OTRel.putSemanticAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("Resizable",
				new SyntaxAttribute("Resizable", "Boolean",
						AttributeType.SYNTAX, false, "Is Resizable", true, 0,
						-1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("value", new SyntaxAttribute(
				"value", "Set", AttributeType.SYNTAX, false, "values", "", 0,
				-1, "", "", -1, "", ""));

		infraOpersM2OTRel.addPropVisibleAttribute("00#" + "MetaType");
		// semVertex.addPropEditableAttribute("01#" + "Identifier");
		// semVertex.addPropVisibleAttribute("01#" + "Identifier");
		infraOpersM2OTRel.addPropEditableAttribute("02#" + "Visible");
		infraOpersM2OTRel.addPropVisibleAttribute("02#" + "Visible");
		infraOpersM2OTRel.addPropEditableAttribute("03#" + "Name");
		infraOpersM2OTRel.addPropVisibleAttribute("03#" + "Name");
		infraOpersM2OTRel.addPropEditableAttribute("04#" + "Style");
		infraOpersM2OTRel.addPropVisibleAttribute("04#" + "Style");
		infraOpersM2OTRel.addPropEditableAttribute("05#" + "Description");
		infraOpersM2OTRel.addPropVisibleAttribute("05#" + "Description");
		infraOpersM2OTRel.addPropEditableAttribute("06#" + "Width");
		infraOpersM2OTRel.addPropVisibleAttribute("06#" + "Width");
		infraOpersM2OTRel.addPropEditableAttribute("07#" + "Height");
		infraOpersM2OTRel.addPropVisibleAttribute("07#" + "Height");
		infraOpersM2OTRel.addPropEditableAttribute("08#" + "Image");
		infraOpersM2OTRel.addPropVisibleAttribute("08#" + "Image");
		infraOpersM2OTRel.addPropEditableAttribute("09#" + "TopConcept");
		infraOpersM2OTRel.addPropVisibleAttribute("09#" + "TopConcept");
		infraOpersM2OTRel.addPropEditableAttribute("10#" + "BackgroundColor");
		infraOpersM2OTRel.addPropVisibleAttribute("10#" + "BackgroundColor");
		infraOpersM2OTRel.addPropEditableAttribute("11#" + "BorderStroke");
		infraOpersM2OTRel.addPropVisibleAttribute("11#" + "BorderStroke");
		infraOpersM2OTRel.addPropEditableAttribute("12#" + "Resizable");
		infraOpersM2OTRel.addPropVisibleAttribute("12#" + "Resizable");

		infraOpersM2OTRel.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaConcept Type", ConceptType.class.getCanonicalName(),
				"MetaOverTwoRelation", 0, -1, "", "", -1, "", ""));
		infraOpersM2OTRel.putSemanticAttribute("OperationsMMType",
				new SemanticAttribute("OperationsMMType", "Class",
						AttributeType.OPERATION, false, "Operations MMType",
						OpersConcept.class.getCanonicalName(), "O", null, "",
						0, -1, "", "", -1, "", ""));

		infraOpersM2OTRel.addPropVisibleAttribute("00#" + "MetaType");
		infraOpersM2OTRel.addPropVisibleAttribute("00#" + "OperationsMMType");
		infraOpersM2OTRel.addPropEditableAttribute("00#" + "OperationsMMType");
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);

		infraOpersM2OTRel.addPanelVisibleAttribute("00#" + "OperationsMMType");
		infraOpersM2OTRel
				.addPanelSpacersAttribute("<<MetaOverTwoAsso>>\n{OperType:\"#"
						+ "OperationsMMType" + "#\"}\n");
		// semOverTwoRelation.addPanelVisibleAttribute("01#" + "Name");
		// semOverTwoRelation.addPanelSpacersAttribute("#" + "Name" + "#");

		InstConcept instInfraOpersM2OTRel = new InstConcept(
				"SMMOverTwoRelation", null, infraOpersM2OTRel);

		// InstConcept instSemEnum = new InstConcept("Enumeration", null,
		// semElementNoSyntax);

		// MetaConcept enumeration = new MetaConcept('E', "Enumeration", true,
		// "Enumeration", "refasenumeration", "MetaEnumeration", 100, 150,
		// "/com/variamos/gui/perspeditor/images/concept.png", true,
		// Color.BLUE.toString(), 3, instSemEnum, true);
		//
		// variabilityInstVertex.put("Enumeration", new
		// InstConcept("Enumeration",
		// metaBasicConcept, enumeration));

		OpersConcept infraOpersM2PWRel = new OpersConcept("SMMPairwiseRelation");

		infraOpersM2PWRel.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false,
				"Meta Association Name", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Description",
				new SyntaxAttribute("Description", "String",
						AttributeType.SYNTAX, false, "Description", "", 0, -1,
						"", "", -1, "", ""));

		infraOpersM2PWRel.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", AttributeType.SYNTAX, false,
				"MetaPWAsso Type", ConceptType.class.getCanonicalName(),
				"MetaConcept", 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("OperationsMMType",
				new SemanticAttribute("OperationsMMType", "Class",
						AttributeType.SYNTAX, false, "Operations MMType",
						OpersConcept.class.getCanonicalName(), "P", null, "",
						0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Identifier",
				new SyntaxAttribute("Identifier", "String",
						AttributeType.SYNTAX, false, "Association Identifier",
						"", 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", AttributeType.SYNTAX, false, "Visible",
				true, 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", AttributeType.SYNTAX, false, "Concept Name",
				"", 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Style", new SyntaxAttribute(
				"Style", "String", AttributeType.SYNTAX, false,
				"Drawing Style", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Width", new SyntaxAttribute(
				"Width", "Integer", AttributeType.SYNTAX, false,
				"Initial Width", 50, 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Height", new SyntaxAttribute(
				"Height", "Integer", AttributeType.SYNTAX, false,
				"Initial Height", 50, 0, -1, "", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("Image", new SyntaxAttribute(
				"Image", "String", AttributeType.SYNTAX, false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String",
						AttributeType.SYNTAX, false, "Background Color",
						"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "",
						""));
		infraOpersM2PWRel.putSemanticAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer",
						AttributeType.SYNTAX, false, "Border Stroke", 1, 0, -1,
						"", "", -1, "", ""));
		infraOpersM2PWRel.putSemanticAttribute("value", new SyntaxAttribute(
				"value", "Set", AttributeType.SYNTAX, false, "values", "", 0,
				-1, "", "", -1, "", ""));

		infraOpersM2PWRel.addPropVisibleAttribute("00#" + "MetaType");
		// semPWAsso.addPropEditableAttribute("01#" + "Identifier");
		// semPWAsso.addPropVisibleAttribute("01#" + "Identifier");
		infraOpersM2PWRel.addPropEditableAttribute("02#" + "Visible");
		infraOpersM2PWRel.addPropVisibleAttribute("02#" + "Visible");
		infraOpersM2PWRel.addPropEditableAttribute("03#" + "Name");
		infraOpersM2PWRel.addPropVisibleAttribute("03#" + "Name");
		infraOpersM2PWRel.addPropEditableAttribute("04#" + "Style");
		infraOpersM2PWRel.addPropVisibleAttribute("04#" + "Style");
		infraOpersM2PWRel.addPropEditableAttribute("05#" + "Description");
		infraOpersM2PWRel.addPropVisibleAttribute("05#" + "Description");
		infraOpersM2PWRel.addPropEditableAttribute("06#" + "Width");
		infraOpersM2PWRel.addPropVisibleAttribute("06#" + "Width");
		infraOpersM2PWRel.addPropEditableAttribute("07#" + "Height");
		infraOpersM2PWRel.addPropVisibleAttribute("07#" + "Height");
		infraOpersM2PWRel.addPropEditableAttribute("08#" + "Image");
		infraOpersM2PWRel.addPropVisibleAttribute("08#" + "Image");
		infraOpersM2PWRel.addPropEditableAttribute("10#" + "BackgroundColor");
		infraOpersM2PWRel.addPropVisibleAttribute("10#" + "BackgroundColor");
		infraOpersM2PWRel.addPropEditableAttribute("11#" + "BorderStroke");
		infraOpersM2PWRel.addPropVisibleAttribute("11#" + "BorderStroke");
		infraOpersM2PWRel.addPropEditableAttribute("14#" + "value");
		infraOpersM2PWRel.addPropVisibleAttribute("14#" + "value");

		infraOpersM2PWRel.putSemanticAttribute("OperationsMMType",
				new SemanticAttribute("OperationsMMType", "Class",
						AttributeType.OPERATION, false, "Operations MMType",
						OpersConcept.class.getCanonicalName(), "P", null, "",
						0, -1, "", "", -1, "", ""));

		infraOpersM2PWRel.addPropVisibleAttribute("00#" + "OperationsMMType");
		infraOpersM2PWRel.addPropEditableAttribute("00#" + "OperationsMMType");
		infraOpersM2PWRel.addPanelVisibleAttribute("00#" + "OperationsMMType");
		infraOpersM2PWRel
				.addPanelSpacersAttribute("<<MetaPairwiseAsso>>\n{OperType:\"#"
						+ "OperationsMMType" + "#\",\n");
		// semPairwiseRelation.addPanelVisibleAttribute("10#" + "Name");
		// semPairwiseRelation.addPanelSpacersAttribute("#" + "Name" + "#\n\n");

		InstConcept instInfraOpersM2PWRel = new InstConcept(
				"SMMPairwiseRelation", null, infraOpersM2PWRel);

		// semElement.addPanelVisibleAttribute("01#" + "Name");
		// semElement.addPanelSpacersAttribute("#" + "Name" + "#");

		OpersConcept infraOpersM2ExtendsRelation = new OpersConcept(
				"SMMExtendRelation");

		infraOpersM2ExtendsRelation.putSemanticAttribute("MetaType",
				new SyntaxAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "MetaConcept Type",
						ConceptType.class.getCanonicalName(),
						"MetaEnumeration", 0, -1, "", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2ExtendsRelation.putSemanticAttribute("Visible",
				new SyntaxAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", true, 0, -1, "", "", -1, "", ""));
		infraOpersM2ExtendsRelation.putSemanticAttribute("Name",
				new SyntaxAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2ExtendsRelation.putSemanticAttribute("value",
				new SyntaxAttribute("value", "Set", AttributeType.SYNTAX,
						false, "values", "", 0, -1, "", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("dummy", new SyntaxAttribute(
		// "dummy", "String", AttributeType.SYNTAX, false, "dummy", "", 0,
		// -1, "", "", -1, "", ""));

		infraOpersM2ExtendsRelation.addPropVisibleAttribute("00#" + "MetaType");
		// semElementNoSyntax.addPropEditableAttribute("01#" + "Identifier");
		// semElementNoSyntax.addPropVisibleAttribute("01#" + "Identifier");
		infraOpersM2ExtendsRelation.addPropEditableAttribute("02#" + "Visible");
		infraOpersM2ExtendsRelation.addPropVisibleAttribute("02#" + "Visible");
		infraOpersM2ExtendsRelation.addPropEditableAttribute("03#" + "Name");
		infraOpersM2ExtendsRelation.addPropVisibleAttribute("03#" + "Name");
		infraOpersM2ExtendsRelation.addPropEditableAttribute("06#" + "value");
		infraOpersM2ExtendsRelation.addPropVisibleAttribute("06#" + "value");
		infraOpersM2ExtendsRelation.addPanelSpacersAttribute("#" + "value"
				+ "#\n\n");

		InstConcept instInfraOpersM2ExtendsRelation = new InstConcept(
				"SMMExtendRelation", null, infraOpersM2ExtendsRelation);

		OpersConcept infraOpersM2ViewConceptAsso = new OpersConcept(
				"SMMViewConceptAsso");

		infraOpersM2ViewConceptAsso.putSemanticAttribute("MetaType",
				new SyntaxAttribute("MetaType", "Enumeration",
						AttributeType.SYNTAX, false, "MetaConcept Type",
						ConceptType.class.getCanonicalName(),
						"MetaEnumeration", 0, -1, "", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2ViewConceptAsso.putSemanticAttribute("Visible",
				new SyntaxAttribute("Visible", "Boolean", AttributeType.SYNTAX,
						false, "Visible", true, 0, -1, "", "", -1, "", ""));
		infraOpersM2ViewConceptAsso.putSemanticAttribute("Name",
				new SyntaxAttribute("Name", "String", AttributeType.SYNTAX,
						false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		infraOpersM2ViewConceptAsso.putSemanticAttribute("value",
				new SyntaxAttribute("value", "Set", AttributeType.SYNTAX,
						false, "values", "", 0, -1, "", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("dummy", new SyntaxAttribute(
		// "dummy", "String", AttributeType.SYNTAX, false, "dummy", "", 0,
		// -1, "", "", -1, "", ""));

		infraOpersM2ViewConceptAsso.addPropVisibleAttribute("00#" + "MetaType");
		// semElementNoSyntax.addPropEditableAttribute("01#" + "Identifier");
		// semElementNoSyntax.addPropVisibleAttribute("01#" + "Identifier");
		infraOpersM2ViewConceptAsso.addPropEditableAttribute("02#" + "Visible");
		infraOpersM2ViewConceptAsso.addPropVisibleAttribute("02#" + "Visible");
		infraOpersM2ViewConceptAsso.addPropEditableAttribute("03#" + "Name");
		infraOpersM2ViewConceptAsso.addPropVisibleAttribute("03#" + "Name");
		infraOpersM2ViewConceptAsso.addPropEditableAttribute("06#" + "value");
		infraOpersM2ViewConceptAsso.addPropVisibleAttribute("06#" + "value");
		infraOpersM2ViewConceptAsso.addPanelSpacersAttribute("#" + "value"
				+ "#\n\n");

		InstConcept instInfraOpersM2ViewConceptAsso = new InstConcept(
				"ExtendRelation", null, infraOpersM2ViewConceptAsso);

		// End Opers M2 Model

		// Begin Syntax M2 Model

		MetaConcept infraSyntaxM2View = new MetaConcept('V', "SMMView", true,
				true, "SMMView", "infrasyntaxm2view",
				"MM View/MM SubView Concept", 100, 30,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.WHITE.toString(), 3, instInfraOpersM2View, true);

		infraSyntaxM2View.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		infraSyntaxM2View.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstConcept instInfraSyntaxM2View = new InstConcept("SMMView",
				infraBasicSyntaxOpersM3Concept, infraSyntaxM2View);
		variabilityInstVertex.put("SMMView", instInfraSyntaxM2View);

		MetaConcept infraSyntaxM2OTRel = new MetaConcept('O',
				"SMMOverTwoRelation", true, true, "SMMOverTwoRelation",
				"infrasyntaxm2miniconcept", "MetaOverTwoRelation", 180, 70,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instInfraOpersM2OTRel, true);

		infraSyntaxM2OTRel.addModelingAttribute("Type", new SyntaxAttribute(
				"Type", "String", AttributeType.SYNTAX, false, "Relation Type",
				"", 0, -1, "", "", -1, "", ""));

		// overTwoRelation.addPropVisibleAttribute("03#" + "Type");
		// overTwoRelation.addPropEditableAttribute("03#" + "Type");
		// overTwoRelation.addPanelVisibleAttribute("03#" + "Type" + "#" +
		// "Type"
		// + "#!=#" + "" + "#" + "");
		// overTwoRelation.addPanelSpacersAttribute("\n{#" + "Type" + "#}");

		InstConcept instInfraSyntaxM2OTRel = new InstConcept(
				"SMMOverTwoRelation", infraBasicSyntaxOpersM3Concept,
				infraSyntaxM2OTRel);
		variabilityInstVertex.put("SMMOverTwoRelation", instInfraSyntaxM2OTRel);

		MetaPairwiseRelation infraSyntaxM2NormalRelation = new MetaPairwiseRelation(
				"SMMNormalRelation", false, true, "Normal Relation",
				"defaultAsso", "View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		constraintInstEdges.put("SMMNormalRelation", new InstPairwiseRelation(
				"SMMNormalRelation", infraSyntaxM2NormalRelation));

		MetaConcept infraSyntaxM2ExtendsRelation = new MetaConcept('X',
				"SMMExtendRelation", true, true, "SMMExtendRelation",
				"infrasyntaxm2miniconcept", "Extend relation", 150, 70,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instInfraOpersM2ExtendsRelation, true);
		infraSyntaxM2ExtendsRelation.addPanelVisibleAttribute("01#Name");
		infraSyntaxM2ExtendsRelation
				.addPanelSpacersAttribute("<<MetaExtendsAsso>>\n#Name#\n\n");

		InstConcept instInfraSyntaxM2ExtendsRelation = new InstConcept(
				"SMMExtendRelation", infraBasicSyntaxOpersM3Concept,
				infraSyntaxM2ExtendsRelation);

		variabilityInstVertex.put("SMMExtendRelation",
				instInfraSyntaxM2ExtendsRelation);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ce-e-c", instEdge);
		instEdge.setIdentifier("ce-e-c");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ExtendsRelation, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ce-c-e", instEdge);
		instEdge.setIdentifier("ce-c-e");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2ExtendsRelation, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Concept, true);

		MetaPairwiseRelation metaPairwiseRelExtends = new MetaPairwiseRelation(
				"ExtendsRelation", false, true, "Extends Relation",
				"refasextends", "Extends relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		constraintInstEdges.put("ExtendsRelation", new InstPairwiseRelation(
				"ExtendsRelation", metaPairwiseRelExtends));

		MetaConcept infraSyntaxM2ViewConceptAsso = new MetaConcept('I',
				"SMMViewConceptAsso", true, true, "SMMViewConceptAsso",
				"infrasyntaxm2miniconcept", "View-Concept Association", 150,
				70, "/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instInfraOpersM2ViewConceptAsso, true);
		infraSyntaxM2ViewConceptAsso.addPanelVisibleAttribute("01#dummy");
		infraSyntaxM2ViewConceptAsso
				.addPanelSpacersAttribute("<<MetaViewConceptAsso>>#dummy#\n");
		infraSyntaxM2ViewConceptAsso.addModelingAttribute("Palette",
				new SyntaxAttribute("Palette", "String", AttributeType.SYNTAX,
						false, "Palette Name", "", 0, -1, "", "", -1, "", ""));

		infraSyntaxM2ViewConceptAsso
				.addPropEditableAttribute("02#" + "Palette");
		infraSyntaxM2ViewConceptAsso.addPropVisibleAttribute("02#" + "Palette");
		infraSyntaxM2ViewConceptAsso.addPanelVisibleAttribute("02#" + "Palette"
				+ "#" + "Palette" + "#!=#" + "" + "#" + "");
		infraSyntaxM2ViewConceptAsso.addPanelSpacersAttribute("{Palette:#"
				+ "Palette" + "#}\n");

		InstConcept instInfraSyntaxM2ViewConceptAsso = new InstConcept(
				"SMMViewConceptAsso", infraBasicSyntaxOpersM3Concept,
				infraSyntaxM2ViewConceptAsso);
		variabilityInstVertex.put("SMMViewConceptAsso",
				instInfraSyntaxM2ViewConceptAsso);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vc-v-vc", instEdge);
		instEdge.setIdentifier("vc-v-vc");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2ViewConceptAsso, true);
		instEdge.setSourceRelation(instInfraSyntaxM2View, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vc-vc-c", instEdge);
		instEdge.setIdentifier("vc-vc-c");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ViewConceptAsso, true);

		// TODO remove if Claims and SDs are Concepts
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vc-vc-otr", instEdge);
		instEdge.setIdentifier("vc-vc-otr");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2OTRel, true);
		instEdge.setSourceRelation(instInfraSyntaxM2ViewConceptAsso, true);

		MetaPairwiseRelation metaPairwiseRelFromView = new MetaPairwiseRelation(
				"ViewRelation", false, true, "View Relation",
				"infrasyntaxm2viewrel", "View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		InstPairwiseRelation instViewRelation = new InstPairwiseRelation(
				"ViewRelation", metaPairwiseRelFromView);

		constraintInstEdges.put("ViewRelation", instViewRelation);

		MetaConcept infraSyntaxM2PWRel = new MetaConcept('P',
				"SMMPairwiseRelation", true, true, "SMMPairwiseRelation",
				"infrasyntaxm2miniconcept", "MetaPairwiseRelation", 150, 200,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instInfraOpersM2PWRel, true);

		infraSyntaxM2PWRel.addModelingAttribute("Type", new SyntaxAttribute(
				"Type", "String", AttributeType.SYNTAX, false, "Relation Type",
				"", 0, -1, "", "", -1, "", ""));

		// pairwiseRelation.addPropEditableAttribute("03#" + "Type");
		// pairwiseRelation.addPropVisibleAttribute("03#" + "Type");
		// pairwiseRelation.addPanelVisibleAttribute("03#" + "Type" + "#" +
		// "Type"
		// + "#!=#" + "" + "#" + "");
		// pairwiseRelation.addPanelSpacersAttribute("\n{#" + "Type" + "#}");

		infraSyntaxM2PWRel.addModelingAttribute("SourceCardinality",
				new SyntaxAttribute("SourceCardinality", "String",
						AttributeType.SYNTAX, false, "Source Cardinality",
						"String", "[]", 0, -1, "", "", -1, "", ""));

		infraSyntaxM2PWRel
				.addPropEditableAttribute("04#" + "SourceCardinality");
		infraSyntaxM2PWRel.addPropVisibleAttribute("04#" + "SourceCardinality");
		infraSyntaxM2PWRel.addPanelVisibleAttribute("01#" + "SourceCardinality"
				+ "#" + "Type" + "#!=#" + "" + "#" + "");
		infraSyntaxM2PWRel.addPanelSpacersAttribute("SourCard:#"
				+ "SourceCardinality" + "#,");

		infraSyntaxM2PWRel.addModelingAttribute("TargetCardinality",
				new SyntaxAttribute("TargetCardinality", "String",
						AttributeType.SYNTAX, false, "Target Cardinality",
						"String", "[]", 0, 5, "", "", 5, "TargCard:#-#}\n",
						"Type#!=##"));

		infraSyntaxM2PWRel
				.addPropEditableAttribute("05#" + "TargetCardinality");
		infraSyntaxM2PWRel.addPropVisibleAttribute("05#" + "TargetCardinality");
		infraSyntaxM2PWRel.addPanelVisibleAttribute("02#" + "TargetCardinality"
				+ "#" + "Type" + "#!=#" + "" + "#" + "");
		infraSyntaxM2PWRel.addPanelSpacersAttribute("TargCard:#"
				+ "TargetCardinality" + "#}\n");

		InstConcept instInfraSyntaxM2PWRel = new InstConcept(
				"SMMPairwiseRelation", infraBasicSyntaxOpersM3Concept,
				infraSyntaxM2PWRel);
		variabilityInstVertex
				.put("SMMPairwiseRelation", instInfraSyntaxM2PWRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cpw-pw-c", instEdge);
		instEdge.setIdentifier("cpw-pw-c");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		instEdge.setSourceRelation(instInfraSyntaxM2PWRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cpw-c-pw", instEdge);
		instEdge.setIdentifier("cpw-c-pw");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2PWRel, true);
		instEdge.setSourceRelation(instInfraSyntaxOpersM2Concept, true);

		// TODO remove if Claims and SDs are Concepts
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cpw-pw-otr", instEdge);
		instEdge.setIdentifier("cpw-pw-otr");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2OTRel, true);
		instEdge.setSourceRelation(instInfraSyntaxM2PWRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cpw-otr-pw", instEdge);
		instEdge.setIdentifier("cpw-otr-pw");
		instEdge.setEditableMetaElement(infraSyntaxM2NormalRelation);
		instEdge.setTargetRelation(instInfraSyntaxM2PWRel, true);
		instEdge.setSourceRelation(instInfraSyntaxM2OTRel, true);
	}

	/**
	 * Creates the default elements (objects) of the Semantic Model (Semantic
	 * concepts). Elements are loaded in the Semantic perspective (advanced
	 * perspectiv) and defines the semantic for elements on the syntax
	 * perspective (associated to concepts).
	 */
	@SuppressWarnings("unchecked")
	public void createOperationsSuperstructure() {
		RefasDefaultOperations.createDefaultOperations(this);
	}

	/**
	 * Creates the default objects of the Meta Model (Syntax concepts). They can
	 * be edited on the Syntax perspective and are the support elements for the
	 * modeling perspective displayed in the views (palettes)
	 */
	private void createSyntaxSuperstructure() {
		RefasDefaultSyntax.createDefaultSyntax(this);
	}

	public PerspectiveType getPerspectiveType() {
		return perspectiveType;
	}

	public void setInstGroupDependencies(
			Map<String, InstOverTwoRelation> instGroupDependencies) {
		this.instGroupDependencies = instGroupDependencies;
	}

	public Map<String, MetaElement> getValidPairwiseRelations(
			InstElement instElement, InstElement instElement2) {
		InstElement instSyntaxElement = this.getVertex(instElement
				.getSupportMetaElementIden());
		InstElement instSyntaxElement2 = this.getVertex(instElement2
				.getSupportMetaElementIden());
		if (instSyntaxElement2 == null) {
			System.out.println("getValidPairwiseRelations error - "
					+ instElement2.getIdentifier());
		}
		return getValidPairwiseRelations(instSyntaxElement, instSyntaxElement2,
				true);
	}

	private Map<String, MetaElement> getValidPairwiseRelations(
			InstElement instElement, InstElement instElement2, boolean first) {
		MetaElement metaElement = instElement.getEditableMetaElement();
		Map<String, MetaElement> out = new HashMap<String, MetaElement>();
		if (instElement2 == null) {
			return out;
		}
		MetaElement metaElement2 = instElement2.getEditableMetaElement();
		for (InstPairwiseRelation pwr : constraintInstEdges.values()) {
			if (pwr.getSourceRelations().size() > 0
					&& pwr.getTargetRelations().size() > 0) {
				MetaElement sourceMetaElement = pwr.getSourceRelations().get(0)
						.getEditableMetaElement();
				MetaElement targetMetaElement = pwr.getTargetRelations().get(0)
						.getEditableMetaElement();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getAutoIdentifier().equals(
						metaElement.getAutoIdentifier())
						&& targetMetaElement.getAutoIdentifier().equals(
								metaElement2.getAutoIdentifier())
				// && pwr.getEditableMetaElement().getVisible()
				)
					out.put(pwr.getIdentifier(), pwr.getEditableMetaElement());
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
				MetaElement sourceMetaElement = pwr.getSourceRelations().get(0)
						.getSourceRelations().get(0).getEditableMetaElement();
				MetaElement targetMetaElement = pwr.getTargetRelations().get(0)
						.getTargetRelations().get(0).getEditableMetaElement();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getAutoIdentifier().equals(
						metaElement.getAutoIdentifier())
						&& targetMetaElement.getAutoIdentifier().equals(
								metaElement2.getAutoIdentifier())
						&& pwr.getEditableMetaElement().getVisible())
					out.put(pwr.getIdentifier(), pwr.getEditableMetaElement());
				// TODO validate the other end when the OTR type has
				// exclusive connections
			}
		}

		if (metaElement instanceof MetaConcept && first) {
			List<InstElement> rel = instElement.getTargetRelations();
			for (InstElement element : rel)
				if (element.getTargetRelations().get(0)
						.getSupportMetaElementIden() != null
						&& element.getTargetRelations().get(0)
								.getSupportMetaElementIden()
								.equals("SMMExtendRelation")) {
					out.putAll(getValidPairwiseRelations(element
							.getTargetRelations().get(0).getTargetRelations()
							.get(0).getTargetRelations().get(0), instElement2,
							true));
				}
		}
		if (metaElement2 instanceof MetaConcept) {
			List<InstElement> rel = instElement2.getTargetRelations();
			for (InstElement element : rel) {
				if (element.getTargetRelations().get(0)
						.getSupportMetaElementIden() != null
						&& element.getTargetRelations().get(0)
								.getSupportMetaElementIden()
								.equals("SMMExtendRelation")) {
					out.putAll(getValidPairwiseRelations(instElement, element
							.getTargetRelations().get(0).getTargetRelations()
							.get(0).getTargetRelations().get(0), false));

				}
			}
		}
		return out;
	}

	public MetaElement getValidMetaPairwiseRelation(InstElement instElement,
			InstElement instElement2, String metaPairwiseIden) {
		InstElement instSyntaxElement = this.getVertex(instElement
				.getSupportMetaElementIden());
		InstElement instSyntaxElement2 = this.getVertex(instElement2
				.getSupportMetaElementIden());
		return getValidMetaPairwiseRelation(instSyntaxElement,
				instSyntaxElement2, metaPairwiseIden, true);
	}

	private MetaElement getValidMetaPairwiseRelation(InstElement instElement,
			InstElement instElement2, String metaPairwiseIden, boolean first) {
		MetaElement metaElement = instElement.getEditableMetaElement();
		if (instElement2 == null) {
			System.out.println("getValidMetaPairwiseRelation error");
		}
		MetaElement metaElement2 = instElement2.getEditableMetaElement();
		for (InstPairwiseRelation pwr : constraintInstEdges.values()) {
			if (pwr.getSourceRelations().size() > 0
					&& pwr.getTargetRelations().size() > 0
					&& pwr.getEditableMetaElement() != null) {
				MetaElement sourceMetaElement = pwr.getSourceRelations().get(0)
						.getEditableMetaElement();
				MetaElement targetMetaElement = pwr.getTargetRelations().get(0)
						.getEditableMetaElement();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getAutoIdentifier().equals(
						metaElement.getAutoIdentifier())
						&& targetMetaElement.getAutoIdentifier().equals(
								metaElement2.getAutoIdentifier())
						&& pwr.getEditableMetaElement().getAutoIdentifier()
								.equals(metaPairwiseIden))
					return (MetaPairwiseRelation) pwr.getEditableMetaElement();
			}
		}
		MetaElement out2 = null;
		for (InstElement pwr : this.variabilityInstVertex.values()) {
			if (pwr.getSourceRelations().size() > 0
					&& pwr.getTargetRelations().size() > 0) {
				MetaElement sourceMetaElement = pwr.getSourceRelations().get(0)
						.getSourceRelations().get(0).getEditableMetaElement();
				MetaElement targetMetaElement = pwr.getTargetRelations().get(0)
						.getTargetRelations().get(0).getEditableMetaElement();
				// if (!(instElement instanceof MetaOverTwoRelation)
				// && !(instElement2 instanceof MetaOverTwoRelation))
				if (sourceMetaElement.getAutoIdentifier().equals(
						metaElement.getAutoIdentifier())
						&& targetMetaElement.getAutoIdentifier().equals(
								metaElement2.getAutoIdentifier())
						&& pwr.getEditableMetaElement().getAutoIdentifier()
								.equals(metaPairwiseIden))
					out2 = pwr.getEditableMetaElement();
				// TODO validate the other end when the OTR type has
				// exclusive connections
				if (out2 != null)
					return out2;
			}
		}

		if (metaElement2 instanceof MetaConcept) {
			List<InstElement> rel = instElement2.getTargetRelations();
			for (InstElement element : rel) {
				if (element.getTargetRelations().get(0)
						.getSupportMetaElementIden() != null
						&& element.getTargetRelations().get(0)
								.getSupportMetaElementIden()
								.equals("SMMExtendRelation")) {
					MetaElement out = (getValidMetaPairwiseRelation(
							instElement, element.getTargetRelations().get(0)
									.getTargetRelations().get(0)
									.getTargetRelations().get(0),
							metaPairwiseIden, false));
					if (out != null)
						return out;
				}
			}
		}
		if (metaElement instanceof MetaConcept && first) {
			List<InstElement> rel = instElement.getTargetRelations();
			for (InstElement element : rel)
				if (element.getTargetRelations().get(0)
						.getSupportMetaElementIden() != null
						&& element.getTargetRelations().get(0)
								.getSupportMetaElementIden()
								.equals("SMMExtendRelation")) {
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
				instElement.getSupportMetaElementIden());
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
			if (element.getTargetRelations().get(0).getSupportMetaElementIden() != null
					&& element.getTargetRelations().get(0)
							.getSupportMetaElementIden()
							.equals("SMMExtendRelation")) {
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
		List<InstAttribute> visible = elm.getVisibleVariables(syntaxParents);
		InstPairwiseRelation instPairwise = (InstPairwiseRelation) elm;
		if (instSource == null && instPairwise.getSourceRelations().size() > 0)
			instSource = instPairwise.getSourceRelations().get(0);
		if (instTarget == null && instPairwise.getTargetRelations().size() > 0)
			instTarget = instPairwise.getTargetRelations().get(0);
		for (InstAttribute v : visible) {
			Map<String, MetaElement> mapElements = null;
			if (elm instanceof InstPairwiseRelation && instSource != null
					&& instSource.getTransSupportMetaElement() != null) {
				mapElements = getSyntaxModel().getValidPairwiseRelations(
						instSource, instTarget);
			}
			v.updateValidationList((InstElement) elm, mapElements);
		}
	}

	public boolean elementsValidation(String element, int modelViewInd,
			int modelViewSubInd) {

		List<InstElement> views = this.getVariabilityVertex("SMMView");
		// FIXME Find views by stereotype, not by instViews object
		if (modelViewInd < views.size() && modelViewSubInd == -1) {
			for (InstElement instElement : views.get(modelViewInd)
					.getTargetRelations()) {
				if (instElement.getTargetRelations().get(0)
						.getSupportMetaElementIden() != null
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