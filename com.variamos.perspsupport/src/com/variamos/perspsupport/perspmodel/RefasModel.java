package com.variamos.perspsupport.perspmodel;

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
import com.variamos.hlcl.RangeDomain;
import com.variamos.perspsupport.expressionsupport.InstanceExpression;
import com.variamos.perspsupport.expressionsupport.SemanticExpression;
import com.variamos.perspsupport.expressionsupport.SemanticExpressionType;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstEnumeration;
import com.variamos.perspsupport.instancesupport.InstOverTwoRelation;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.instancesupport.InstVertex;
import com.variamos.perspsupport.instancesupport.InstView;
import com.variamos.perspsupport.semanticinterface.IntSemanticExpression;
import com.variamos.perspsupport.semanticinterface.IntSemanticRelationType;
import com.variamos.perspsupport.semanticsupport.SemanticConcept;
import com.variamos.perspsupport.semanticsupport.SemanticContextGroup;
import com.variamos.perspsupport.semanticsupport.SemanticOverTwoRelation;
import com.variamos.perspsupport.semanticsupport.SemanticPairwiseRelation;
import com.variamos.perspsupport.semanticsupport.SemanticReasoningConcept;
import com.variamos.perspsupport.semanticsupport.SemanticRelationType;
import com.variamos.perspsupport.semanticsupport.SemanticVariable;
import com.variamos.perspsupport.semanticsupport.SoftSemanticConcept;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.syntaxsupport.ExecCurrentStateAttribute;
import com.variamos.perspsupport.syntaxsupport.GlobalConfigAttribute;
import com.variamos.perspsupport.syntaxsupport.MetaConcept;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaEnumeration;
import com.variamos.perspsupport.syntaxsupport.MetaOverTwoRelation;
import com.variamos.perspsupport.syntaxsupport.MetaPairwiseRelation;
import com.variamos.perspsupport.syntaxsupport.MetaView;
import com.variamos.perspsupport.syntaxsupport.SemanticAttribute;
import com.variamos.perspsupport.syntaxsupport.SyntaxAttribute;
import com.variamos.perspsupport.types.ConceptType;
import com.variamos.perspsupport.types.ExpressionVertexType;
import com.variamos.perspsupport.types.PerspectiveType;
import com.variamos.perspsupport.types.StringType;

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
public class RefasModel extends AbstractModel {

	private RefasModel syntaxRefas;
	private RefasModel semanticRefas;
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

	public RefasModel(PerspectiveType perspectiveType,
			Map<String, SemanticExpressionType> metaExpressionTypes) {
		this(perspectiveType, metaExpressionTypes, null, null);
	}

	public RefasModel(Map<String, SemanticExpressionType> metaExpressionTypes,
			RefasModel syntaxRefas) {
		this(PerspectiveType.SEMANTIC, metaExpressionTypes, syntaxRefas, null);
	}

	public RefasModel(PerspectiveType perspectiveType,
			Map<String, SemanticExpressionType> semanticExpressionTypes,
			RefasModel syntaxRefas, RefasModel semanticRefas) {
		this.perspectiveType = perspectiveType;
		this.syntaxRefas = syntaxRefas;
		this.semanticExpressionTypes = semanticExpressionTypes;
		this.semanticRefas = semanticRefas;
		variabilityInstVertex = new HashMap<String, InstElement>();
		instGroupDependencies = new HashMap<String, InstOverTwoRelation>();
		constraintInstEdges = new HashMap<String, InstPairwiseRelation>();
		name = "";

		switch (perspectiveType) {
		case CORESEMANTIC:
			createCoreSemantic();
			break;
		case CORESYNTAX:
			createCoreSyntax();
			break;
		case MODELING:
			break;
		case SEMANTIC:
			createDefaultSemantic();
			break;
		case CONFIG_SIMULATION:
			break;
		case SYNTAX:
			createDefaultSyntax();
			break;
		default:
			break;
		}
	}

	public RefasModel getSyntaxRefas() {
		return syntaxRefas;
	}

	public String getInstViewName(int modelViewInd, int modelViewSubInd) {
		// List<InstView> instViews = this.getSyntaxRefas().getInstViews();
		List<InstElement> instViews = this.getSyntaxRefas()
				.getVariabilityVertex("View");
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
		List<InstElement> instViews = this.getSyntaxRefas()
				.getVariabilityVertex("View");
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

	public RefasModel getSemanticRefas() {
		return semanticRefas;
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
		Collections.sort(out);
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
		List<InstElement> views = this.getVariabilityVertex("View");
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
	 * Creates the elements (objects) to support the Semantic Model. Concepts
	 * are displayed in the palette of the semantic perspective (PWAssociations
	 * can be used)
	 */
	private void createCoreSemantic() {
		MetaConcept metaBasicConcept = new MetaConcept();

		metaBasicConcept.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		metaBasicConcept.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaBasicConcept.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		metaBasicConcept.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaBasicConcept.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		metaBasicConcept.addModelingAttribute("Name", new SyntaxAttribute(
				"Name", "String", false, "Concept Name", "", 0, -1, "", "", -1,
				"", ""));
		metaBasicConcept.addModelingAttribute("Description",
				new SyntaxAttribute("Description", "String", false,
						"Description", "", 0, -1, "", "", -1, "", ""));

		metaBasicConcept.addModelingAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", false, "MetaConcept Type",
				ConceptType.class.getCanonicalName(), "MetaConcept", 0, -1, "",
				"", -1, "", ""));
		// metaBasicConcept.addModelingAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", false, "Visible", true, 0, -1, "", "",
				-1, "", ""));
		metaBasicConcept.addModelingAttribute("Name", new SyntaxAttribute(
				"Name", "String", false, "Concept Name", "", 0, -1, "", "", -1,
				"", ""));
		metaBasicConcept.addModelingAttribute("Style", new SyntaxAttribute(
				"Style", "String", false, "Drawing Style", "refasclaim", 0, -1,
				"", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Width", new SyntaxAttribute(
				"Width", "Integer", false, "Initial Width", 100, 0, -1, "", "",
				-1, "", ""));
		metaBasicConcept.addModelingAttribute("Height", new SyntaxAttribute(
				"Height", "Integer", false, "Initial Height", 40, 0, -1, "",
				"", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Image", new SyntaxAttribute(
				"Image", "String", false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		metaBasicConcept.addModelingAttribute("TopConcept",
				new SyntaxAttribute("TopConcept", "Boolean", false,
						"Is Top Concept", true, 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String", false,
						"Background Color", "java.awt.Color[r=0,g=0,b=255]", 0,
						-1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer", false,
						"Border Stroke", 1, 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Resizable", new SyntaxAttribute(
				"Resizable", "Boolean", false, "Is Resizable", true, 0, -1, "",
				"", -1, "", ""));

		SemanticConcept semConcept = new SemanticConcept();

		// semConcept.putSemanticAttribute("identifier", new SyntaxAttribute(
		// "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// "", "", -1, "", ""));
		// semConcept.addPropEditableAttribute("01#" + "identifier");
		// semConcept.addPropVisibleAttribute("01#" + "identifier");

		// semConcept.addPanelVisibleAttribute("01#" + "identifier");
		// semConcept.addPanelSpacersAttribute("#" + "identifier" + "#\n\n");

		InstConcept instSemConcept = new InstConcept("Concept", null,
				semConcept);

		MetaConcept metaConcept = new MetaConcept(
				'C',
				"Concept",
				true,
				"Concept",
				"refasenumeration",
				"Semantic Concept: Define a concept for the semantic operations",
				100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, instSemConcept, true);

		metaConcept.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		metaConcept.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaConcept.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		metaConcept.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaConcept.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		SemanticConcept semPairwiseRelation = new SemanticConcept(semConcept,
				"CSPairWiseRelation");

		semPairwiseRelation.putSemanticAttribute("enumerationType",
				new SemanticAttribute("enumerationType", "Class", false,
						"enumeration",
						InstEnumeration.class.getCanonicalName(),
						"TypeEnumeration", "String", "", 0, -1, "", "", -1, "",
						""));

		semPairwiseRelation.addPropEditableAttribute("03#" + "enumerationType");
		semPairwiseRelation.addPropVisibleAttribute("03#" + "enumerationType");

		semPairwiseRelation.putSemanticAttribute("relationTypesAttributes",
				new SyntaxAttribute("relationTypesAttributes", "Set", false,
						"relationTypes",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		semPairwiseRelation.putSemanticAttribute(
				"relationTypesSemExpressions",
				new SyntaxAttribute("relationTypesSemExpressions", "Set",
						false, "semanticExpressions", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		InstConcept instSemPairwiseRelation = new InstConcept(
				"CSPairWiseRelation", null, semPairwiseRelation);

		MetaConcept metaPairWiseRelation = new MetaConcept(
				'P',
				"CSPairWiseRelation",
				true,
				"CSPairWiseRelation",
				"refasenumeration",
				"Semantic PairWise Relation: Defines a direct relation for the semantic operations",
				150, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, instSemPairwiseRelation, true);

		metaPairWiseRelation.addModelingAttribute(
				MetaConcept.VAR_USERIDENTIFIER, new SyntaxAttribute(
						MetaConcept.VAR_USERIDENTIFIER, "String", false,
						"User Identifier", null, 0, 1, "", "", -1, "", ""));
		metaPairWiseRelation.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairWiseRelation.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairWiseRelation.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairWiseRelation.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

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
		MetaEnumeration enumeration = new MetaEnumeration(
				"TypeEnumeration",
				true,
				"TypeEnumeration",
				"refasenumeration",
				"Semantic Enumeration Type: Define an enumeration of types used in paiwise and overtwo relations",
				100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, true);

		enumeration.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		enumeration.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		enumeration.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		enumeration.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		enumeration.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		enumeration.addModelingAttribute("value", "Set", false, "value",
				InstAttribute.class.getCanonicalName(),
				new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "", "");
		enumeration.addPropEditableAttribute("01#" + "value");
		enumeration.addPropVisibleAttribute("01#" + "value");

		SemanticConcept semOverTwoRelation = new SemanticConcept(semConcept,
				"CSOverTwoRelation");

		semOverTwoRelation.putSemanticAttribute("enumerationType",
				new SemanticAttribute("enumerationType", "Class", false,
						"enumeration",
						InstEnumeration.class.getCanonicalName(),
						"TypeEnumeration", "String", "", 0, -1, "", "", -1, "",
						""));

		semOverTwoRelation.addPropEditableAttribute("03#" + "enumerationType");
		semOverTwoRelation.addPropVisibleAttribute("03#" + "enumerationType");

		semOverTwoRelation.putSemanticAttribute("relationTypesAttributes",
				new SyntaxAttribute("relationTypesAttributes", "Set", false,
						"relationTypes",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		semOverTwoRelation.putSemanticAttribute(
				"relationTypesSemExpressions",
				new SyntaxAttribute("relationTypesSemExpressions", "Set",
						false, "semanticExpressions", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		InstConcept instSemOverTwoRelation = new InstConcept(
				"CSOverTwoRelation", null, semOverTwoRelation);

		MetaConcept overTwoRelation = new MetaConcept('O', "CSOverTwoRelation",
				true, "CSOverTwoRelation", "refasminiclass",
				"Over Two Relation", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, instSemOverTwoRelation, true);

		overTwoRelation.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		overTwoRelation.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		overTwoRelation.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		overTwoRelation.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		overTwoRelation.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		List<IntSemanticRelationType> semPairwExtRelList = new ArrayList<IntSemanticRelationType>();
		semPairwExtRelList.add(new SemanticRelationType("extends", "extends",
				"extends", false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> semPairwAsoRelList = new ArrayList<IntSemanticRelationType>();
		semPairwAsoRelList.add(new SemanticRelationType("association",
				"association", "association", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation semPairwExtRel = new SemanticPairwiseRelation(
				"ExtRel", false, semPairwExtRelList);

		InstConcept instSemPairwExtRel = new InstConcept("ExtendsRelation",
				metaBasicConcept, semPairwExtRel);

		SemanticPairwiseRelation semPairwAsoRel = new SemanticPairwiseRelation(
				"AsoRel", false, semPairwAsoRelList);

		InstConcept instSemPairwAsoRel = new InstConcept("AssociationRelation",
				metaBasicConcept, semPairwAsoRel);

		InstConcept instConcept = new InstConcept("Concept", metaBasicConcept,
				metaConcept);

		variabilityInstVertex.put("Concept", instConcept);

		InstConcept instPairWiseRelation = new InstConcept(
				"CSPairWiseRelation", metaBasicConcept, metaPairWiseRelation);
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("CSPairWiseRelation", instPairWiseRelation);

		variabilityInstVertex.put("TypeEnumeration", new InstConcept(
				"TypeEnumeration", metaBasicConcept, enumeration));
		InstConcept instOverTwo = new InstConcept("CSOverTwoRelation",
				metaBasicConcept, overTwoRelation);
		variabilityInstVertex.put("CSOverTwoRelation", instOverTwo);

		MetaPairwiseRelation metaPairwiseRelAso = new MetaPairwiseRelation(
				"AssociationRelation", false, "Association Relation",
				"defaultAsso", "Association Relation: ", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instSemPairwAsoRel);

		metaPairwiseRelAso.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));

		metaPairwiseRelAso.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairwiseRelAso.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairwiseRelAso.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairwiseRelAso.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		constraintInstEdges.put("DirectRelation", new InstPairwiseRelation(
				metaPairwiseRelAso));

		MetaPairwiseRelation metaPairwiseRelExtends = new MetaPairwiseRelation(
				"ExtendsRelation",
				false,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instSemPairwExtRel);

		metaPairwiseRelExtends.addModelingAttribute(
				MetaConcept.VAR_USERIDENTIFIER, new SyntaxAttribute(
						MetaConcept.VAR_USERIDENTIFIER, "String", false,
						"User Identifier", null, 0, 1, "", "", -1, "", ""));
		metaPairwiseRelExtends.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairwiseRelExtends.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairwiseRelExtends.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairwiseRelExtends.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstPairwiseRelation rel = new InstPairwiseRelation(semPairwExtRel);
		rel.setEditableMetaElement(metaPairwiseRelExtends);
		rel.setIdentifier("ExtendsCCRel");
		rel.setTargetRelation(instConcept, true);
		rel.setSourceRelation(instConcept, true);
		constraintInstEdges.put("ExtendsCCRel", rel);

		rel = new InstPairwiseRelation(semPairwExtRel);
		rel.setEditableMetaElement(metaPairwiseRelExtends);
		rel.setIdentifier("ExtendsOCRel");
		rel.setTargetRelation(instConcept, true);
		rel.setSourceRelation(instOverTwo, true);
		constraintInstEdges.put("ExtendsOCRel", rel);

		rel = new InstPairwiseRelation(semPairwExtRel);
		rel.setEditableMetaElement(metaPairwiseRelExtends);
		rel.setIdentifier("ExtendsOORel");
		rel.setTargetRelation(instOverTwo, true);
		rel.setSourceRelation(instOverTwo, true);
		constraintInstEdges.put("ExtendsOORel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoCPWRel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instConcept, true);
		constraintInstEdges.put("AssoCPWRel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoPWCRel");
		rel.setTargetRelation(instConcept, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoPWCRel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoOCPWRel");
		rel.setTargetRelation(instConcept, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoOCPWRel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoPWOCRel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instOverTwo, true);
		constraintInstEdges.put("AssoPWOCRel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoCOPWRel");
		rel.setTargetRelation(instOverTwo, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoCOPWRel", rel);

		rel = new InstPairwiseRelation(semPairwAsoRel);
		rel.setEditableMetaElement(metaPairwiseRelAso);
		rel.setIdentifier("AssoPWCORel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instConcept, true);
		constraintInstEdges.put("AssoPWCORel", rel);
	}

	/**
	 * Creates the elements (objects) to support the syntax Meta Model. Concepts
	 * are displayed in the palette of the syntax perspective. (PWAssociations
	 * can be used)
	 */
	private void createCoreSyntax() {
		MetaConcept metaBasicConcept = new MetaConcept();

		metaBasicConcept.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		metaBasicConcept.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaBasicConcept.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		metaBasicConcept.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaBasicConcept.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		metaBasicConcept.addModelingAttribute("Name", new SyntaxAttribute(
				"Name", "String", false, "Concept Name", "", 0, -1, "", "", -1,
				"", ""));
		metaBasicConcept.addModelingAttribute("Description",
				new SyntaxAttribute("Description", "String", false,
						"Description", "", 0, -1, "", "", -1, "", ""));

		metaBasicConcept.addModelingAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", false, "MetaConcept Type",
				ConceptType.class.getCanonicalName(), "MetaConcept", 0, -1, "",
				"", -1, "", ""));
		// metaBasicConcept.addModelingAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", false, "Visible", true, 0, -1, "", "",
				-1, "", ""));
		metaBasicConcept.addModelingAttribute("Name", new SyntaxAttribute(
				"Name", "String", false, "Concept Name", "", 0, -1, "", "", -1,
				"", ""));
		metaBasicConcept.addModelingAttribute("Style", new SyntaxAttribute(
				"Style", "String", false, "Drawing Style", "refasclaim", 0, -1,
				"", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Width", new SyntaxAttribute(
				"Width", "Integer", false, "Initial Width", 100, 0, -1, "", "",
				-1, "", ""));
		metaBasicConcept.addModelingAttribute("Height", new SyntaxAttribute(
				"Height", "Integer", false, "Initial Height", 40, 0, -1, "",
				"", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Image", new SyntaxAttribute(
				"Image", "String", false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		metaBasicConcept.addModelingAttribute("TopConcept",
				new SyntaxAttribute("TopConcept", "Boolean", false,
						"Is Top Concept", true, 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("BackgroundColor",
				new SyntaxAttribute("BackgroundColor", "String", false,
						"Background Color", "java.awt.Color[r=0,g=0,b=255]", 0,
						-1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("BorderStroke",
				new SyntaxAttribute("BorderStroke", "Integer", false,
						"Border Stroke", 1, 0, -1, "", "", -1, "", ""));
		metaBasicConcept.addModelingAttribute("Resizable", new SyntaxAttribute(
				"Resizable", "Boolean", false, "Is Resizable", true, 0, -1, "",
				"", -1, "", ""));

		SemanticConcept semView = new SemanticConcept();

		semView.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", false, "MetaConcept Type",
				ConceptType.class.getCanonicalName(), "MetaView", 0, -1, "",
				"", -1, "", ""));
		semView.putSemanticAttribute("Index", new SyntaxAttribute("Index",
				"Integer", false, "View Index", 3, 0, -1, "", "", -1, "", ""));
		semView.putSemanticAttribute("Identifier", new SyntaxAttribute(
				"Identifier", "String", false, "View Identifier", "", 0, -1,
				"", "", -1, "", ""));
		semView.putSemanticAttribute("Visible", new SyntaxAttribute("Visible",
				"Boolean", false, "Visible", true, 0, -1, "", "", -1, "", ""));
		semView.putSemanticAttribute("Parent", new SyntaxAttribute("Parent",
				"String", false, "Parent View", "", 0, -1, "", "", -1, "", ""));
		semView.putSemanticAttribute("Name", new SyntaxAttribute("Name",
				"String", false, "Concept Name", "", 0, -1, "", "", -1, "", ""));
		semView.putSemanticAttribute("Style", new SyntaxAttribute("Style",
				"String", false, "Drawing Style", "refasclaim", 0, -1, "", "",
				-1, "", ""));
		semView.putSemanticAttribute("Description", new SyntaxAttribute(
				"Description", "String", false, "Description", "", 0, -1, "",
				"", -1, "", ""));
		semView.putSemanticAttribute("Width", new SyntaxAttribute("Width",
				"Integer", false, "Initial Width", 100, 0, -1, "", "", -1, "",
				""));
		semView.putSemanticAttribute("Height", new SyntaxAttribute("Height",
				"Integer", false, "Initial Height", 40, 0, -1, "", "", -1, "",
				""));
		semView.putSemanticAttribute("Image", new SyntaxAttribute("Image",
				"String", false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		semView.putSemanticAttribute("BorderStroke", new SyntaxAttribute(
				"BorderStroke", "Integer", false, "Border Stroke", 1, 0, -1,
				"", "", -1, "", ""));
		semView.putSemanticAttribute("PaletteNames", new SyntaxAttribute(
				"PaletteNames", "String", false, "Palette Name", "", 0, -1, "",
				"", -1, "", ""));

		semView.addPropEditableAttribute("03#" + "PaletteNames");
		semView.addPropVisibleAttribute("03#" + "PaletteNames");
		semView.addPanelVisibleAttribute("05#" + "PaletteNames" + "#"
				+ "PaletteNames" + "#!=#" + "" + "#" + "");
		semView.addPanelSpacersAttribute("{Palettes:#" + "PaletteNames"
				+ "#}\n\n");
		semView.addPropVisibleAttribute("00#" + "MetaType");
		// semView.addPropEditableAttribute("01#" + "Identifier");
		// semView.addPropVisibleAttribute("01#" + "Identifier");
		// semView.addPropEditableAttribute("02#" + "Index");
		// semView.addPropVisibleAttribute("02#" + "Index");
		semView.addPropEditableAttribute("03#" + "Visible");
		semView.addPropVisibleAttribute("03#" + "Visible");
		// semView.addPropEditableAttribute("04#" + "Parent");
		// semView.addPropVisibleAttribute("04#" + "Parent");
		semView.addPropEditableAttribute("05#" + "Name");
		semView.addPropVisibleAttribute("05#" + "Name");
		semView.addPropEditableAttribute("06#" + "Style");
		semView.addPropVisibleAttribute("06#" + "Style");
		semView.addPropEditableAttribute("07#" + "Description");
		semView.addPropVisibleAttribute("07#" + "Description");
		semView.addPropEditableAttribute("08#" + "Width");
		semView.addPropVisibleAttribute("08#" + "Width");
		semView.addPropEditableAttribute("09#" + "Height");
		semView.addPropVisibleAttribute("09#" + "Height");
		semView.addPropEditableAttribute("10#" + "Image");
		semView.addPropVisibleAttribute("10#" + "Image");
		// semView.addDisPropEditableAttribute("11#" + "BorderStroke");
		semView.addPropVisibleAttribute("11#" + "BorderStroke");

		// semView.addPanelVisibleAttribute("01#" + "Name");
		// semView.addPanelSpacersAttribute("#" + "Name" + "#");

		InstConcept instSemView = new InstConcept("View", null, semView);

		MetaConcept view = new MetaConcept('V', "View", true, "View",
				"refasview", "View/SubView Concept", 100, 30,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.WHITE.toString(), 3, instSemView, true);

		view.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		view.addPropVisibleAttribute("01#" + MetaConcept.VAR_USERIDENTIFIER);
		view.addPropEditableAttribute("01#" + MetaConcept.VAR_USERIDENTIFIER);

		view.addPanelVisibleAttribute("04#" + MetaConcept.VAR_USERIDENTIFIER);
		view.addPanelSpacersAttribute("#" + MetaConcept.VAR_USERIDENTIFIER
				+ "#\n\n");

		InstConcept instView = new InstConcept("View", metaBasicConcept, view);
		variabilityInstVertex.put("View", instView);

		SemanticConcept semVertex = new SemanticConcept();

		semVertex
				.putSemanticAttribute("Name", new SyntaxAttribute("Name",
						"String", false, "Concept Name", "", 0, -1, "", "", -1,
						"", ""));
		semVertex.putSemanticAttribute("Description", new SyntaxAttribute(
				"Description", "String", false, "Description", "", 0, -1, "",
				"", -1, "", ""));

		semVertex.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", false, "MetaConcept Type",
				ConceptType.class.getCanonicalName(), "MetaConcept", 0, -1, "",
				"", -1, "", ""));
		// semVertex.putSemanticAttribute("Identifier", new SyntaxAttribute(
		// "Identifier", "String", false, "Concept Identifier", "", 0, -1,
		// "", "", -1, "", ""));
		semVertex.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", false, "Visible", true, 0, -1, "", "",
				-1, "", ""));
		semVertex
				.putSemanticAttribute("Name", new SyntaxAttribute("Name",
						"String", false, "Concept Name", "", 0, -1, "", "", -1,
						"", ""));
		semVertex.putSemanticAttribute("Style", new SyntaxAttribute("Style",
				"String", false, "Drawing Style", "refasclaim", 0, -1, "", "",
				-1, "", ""));
		semVertex.putSemanticAttribute("Width", new SyntaxAttribute("Width",
				"Integer", false, "Initial Width", 100, 0, -1, "", "", -1, "",
				""));
		semVertex.putSemanticAttribute("Height", new SyntaxAttribute("Height",
				"Integer", false, "Initial Height", 40, 0, -1, "", "", -1, "",
				""));
		semVertex.putSemanticAttribute("Image", new SyntaxAttribute("Image",
				"String", false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		semVertex.putSemanticAttribute("TopConcept", new SyntaxAttribute(
				"TopConcept", "Boolean", false, "Is Top Concept", true, 0, -1,
				"", "", -1, "", ""));
		semVertex.putSemanticAttribute("BackgroundColor", new SyntaxAttribute(
				"BackgroundColor", "String", false, "Background Color",
				"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "", ""));
		semVertex.putSemanticAttribute("BorderStroke", new SyntaxAttribute(
				"BorderStroke", "Integer", false, "Border Stroke", 1, 0, -1,
				"", "", -1, "", ""));
		semVertex.putSemanticAttribute("Resizable", new SyntaxAttribute(
				"Resizable", "Boolean", false, "Is Resizable", true, 0, -1, "",
				"", -1, "", ""));
		semVertex.putSemanticAttribute("value", new SyntaxAttribute("value",
				"Set", false, "values", "", 0, -1, "", "", -1, "", ""));

		semVertex.addPropVisibleAttribute("00#" + "MetaType");
		// semVertex.addPropEditableAttribute("01#" + "Identifier");
		// semVertex.addPropVisibleAttribute("01#" + "Identifier");
		semVertex.addPropEditableAttribute("02#" + "Visible");
		semVertex.addPropVisibleAttribute("02#" + "Visible");
		semVertex.addPropEditableAttribute("03#" + "Name");
		semVertex.addPropVisibleAttribute("03#" + "Name");
		semVertex.addPropEditableAttribute("04#" + "Style");
		semVertex.addPropVisibleAttribute("04#" + "Style");
		semVertex.addPropEditableAttribute("05#" + "Description");
		semVertex.addPropVisibleAttribute("05#" + "Description");
		semVertex.addPropEditableAttribute("06#" + "Width");
		semVertex.addPropVisibleAttribute("06#" + "Width");
		semVertex.addPropEditableAttribute("07#" + "Height");
		semVertex.addPropVisibleAttribute("07#" + "Height");
		semVertex.addPropEditableAttribute("08#" + "Image");
		semVertex.addPropVisibleAttribute("08#" + "Image");
		semVertex.addPropEditableAttribute("09#" + "TopConcept");
		semVertex.addPropVisibleAttribute("09#" + "TopConcept");
		semVertex.addPropEditableAttribute("10#" + "BackgroundColor");
		semVertex.addPropVisibleAttribute("10#" + "BackgroundColor");
		semVertex.addPropEditableAttribute("11#" + "BorderStroke");
		semVertex.addPropVisibleAttribute("11#" + "BorderStroke");
		semVertex.addPropEditableAttribute("12#" + "Resizable");
		semVertex.addPropVisibleAttribute("12#" + "Resizable");
		// semVertex.addPropEditableAttribute("14#" + "value");
		// semVertex.addPropVisibleAttribute("14#" + "value");

		SemanticConcept semPWAsso = new SemanticConcept();

		semPWAsso.putSemanticAttribute("Name", new SyntaxAttribute("Name",
				"String", false, "Association Name", "", 0, -1, "", "", -1, "",
				""));
		semPWAsso.putSemanticAttribute("Description", new SyntaxAttribute(
				"Description", "String", false, "Description", "", 0, -1, "",
				"", -1, "", ""));

		semPWAsso.putSemanticAttribute("MetaType", new SyntaxAttribute(
				"MetaType", "Enumeration", false, "MetaPWAsso Type",
				ConceptType.class.getCanonicalName(), "MetaConcept", 0, -1, "",
				"", -1, "", ""));
		semPWAsso.putSemanticAttribute("SemanticType", new SemanticAttribute(
				"SemanticType", "Class", false, "Semantic Type",
				SemanticConcept.class.getCanonicalName(), "P", null, "", 0, -1,
				"", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("Identifier", new SyntaxAttribute(
				"Identifier", "String", false, "Association Identifier", "", 0,
				-1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", false, "Visible", true, 0, -1, "", "",
				-1, "", ""));
		semPWAsso
				.putSemanticAttribute("Name", new SyntaxAttribute("Name",
						"String", false, "Concept Name", "", 0, -1, "", "", -1,
						"", ""));
		semPWAsso.putSemanticAttribute("Style",
				new SyntaxAttribute("Style", "String", false, "Drawing Style",
						"", 0, -1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("Width", new SyntaxAttribute("Width",
				"Integer", false, "Initial Width", 50, 0, -1, "", "", -1, "",
				""));
		semPWAsso.putSemanticAttribute("Height", new SyntaxAttribute("Height",
				"Integer", false, "Initial Height", 50, 0, -1, "", "", -1, "",
				""));
		semPWAsso.putSemanticAttribute("Image", new SyntaxAttribute("Image",
				"String", false, "Image File",
				"/com/variamos/gui/perspeditor/images/claim.png", 0, -1, "",
				"", -1, "", ""));
		semPWAsso.putSemanticAttribute("BackgroundColor", new SyntaxAttribute(
				"BackgroundColor", "String", false, "Background Color",
				"java.awt.Color[r=0,g=0,b=255]", 0, -1, "", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("BorderStroke", new SyntaxAttribute(
				"BorderStroke", "Integer", false, "Border Stroke", 1, 0, -1,
				"", "", -1, "", ""));
		semPWAsso.putSemanticAttribute("value", new SyntaxAttribute("value",
				"Set", false, "values", "", 0, -1, "", "", -1, "", ""));

		semPWAsso.addPropVisibleAttribute("00#" + "MetaType");
		// semPWAsso.addPropEditableAttribute("01#" + "Identifier");
		// semPWAsso.addPropVisibleAttribute("01#" + "Identifier");
		semPWAsso.addPropEditableAttribute("02#" + "Visible");
		semPWAsso.addPropVisibleAttribute("02#" + "Visible");
		semPWAsso.addPropEditableAttribute("03#" + "Name");
		semPWAsso.addPropVisibleAttribute("03#" + "Name");
		semPWAsso.addPropEditableAttribute("04#" + "Style");
		semPWAsso.addPropVisibleAttribute("04#" + "Style");
		semPWAsso.addPropEditableAttribute("05#" + "Description");
		semPWAsso.addPropVisibleAttribute("05#" + "Description");
		semPWAsso.addPropEditableAttribute("06#" + "Width");
		semPWAsso.addPropVisibleAttribute("06#" + "Width");
		semPWAsso.addPropEditableAttribute("07#" + "Height");
		semPWAsso.addPropVisibleAttribute("07#" + "Height");
		semPWAsso.addPropEditableAttribute("08#" + "Image");
		semPWAsso.addPropVisibleAttribute("08#" + "Image");
		semPWAsso.addPropEditableAttribute("10#" + "BackgroundColor");
		semPWAsso.addPropVisibleAttribute("10#" + "BackgroundColor");
		semPWAsso.addPropEditableAttribute("11#" + "BorderStroke");
		semPWAsso.addPropVisibleAttribute("11#" + "BorderStroke");
		semPWAsso.addPropEditableAttribute("14#" + "value");
		semPWAsso.addPropVisibleAttribute("14#" + "value");

		SemanticConcept semConcept = new SemanticConcept(semVertex, "Concept");

		semConcept.putSemanticAttribute("SemanticType", new SemanticAttribute(
				"SemanticType", "Class", false, "Semantic Type",
				SemanticConcept.class.getCanonicalName(), "C", null, "", 0, -1,
				"", "", -1, "", ""));

		semConcept.addPropEditableAttribute("00#" + "SemanticType");
		semConcept.addPropVisibleAttribute("00#" + "SemanticType");

		InstConcept instSemVertex = new InstConcept("Concept", null, semConcept);

		MetaConcept concept = new MetaConcept('C', "Concept", true, "Concept",
				"refasminiclass", "Meta Concept", 150, 180,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemVertex, true);

		concept.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		concept.addPropVisibleAttribute("01#" + MetaConcept.VAR_USERIDENTIFIER);
		concept.addPropEditableAttribute("01#" + MetaConcept.VAR_USERIDENTIFIER);

		concept.addPanelVisibleAttribute("04#" + MetaConcept.VAR_USERIDENTIFIER);
		concept.addPanelSpacersAttribute("#" + MetaConcept.VAR_USERIDENTIFIER
				+ "#\n\n");

		concept.addPanelVisibleAttribute("00#" + "SemanticType");
		concept.addPanelSpacersAttribute("<<MetaConcept>>\n{SemType:\"#"
				+ "SemanticType" + "#\"}\n");
		// concept.addPanelVisibleAttribute("01#" + "Name");
		// concept.addPanelSpacersAttribute("#" + "Name" + "#\n\n");

		InstConcept instConcept = new InstConcept("Concept", metaBasicConcept,
				concept);
		variabilityInstVertex.put("Concept", instConcept);

		SemanticConcept semElementNoSyntax = new SemanticConcept();

		semElementNoSyntax.putSemanticAttribute(
				"MetaType",
				new SyntaxAttribute("MetaType", "Enumeration", false,
						"MetaConcept Type", ConceptType.class
								.getCanonicalName(), "MetaEnumeration", 0, -1,
						"", "", -1, "", ""));
		// semElementNoSyntax.putSemanticAttribute("Identifier",
		// new SyntaxAttribute("Identifier", "String", false,
		// "Concept Identifier", "", 0, -1, "", "", -1, "", ""));
		semElementNoSyntax.putSemanticAttribute("Visible", new SyntaxAttribute(
				"Visible", "Boolean", false, "Visible", true, 0, -1, "", "",
				-1, "", ""));
		semElementNoSyntax.putSemanticAttribute("Name", new SyntaxAttribute(
				"Name", "String", false, "Concept Name", "", 0, -1, "", "", -1,
				"", ""));
		semElementNoSyntax
				.putSemanticAttribute("value", new SyntaxAttribute("value",
						"Set", false, "values", "", 0, -1, "", "", -1, "", ""));
		semElementNoSyntax.putSemanticAttribute("dummy", new SyntaxAttribute(
				"dummy", "String", false, "dummy", "", 0, -1, "", "", -1, "",
				""));

		semElementNoSyntax.addPropVisibleAttribute("00#" + "MetaType");
		// semElementNoSyntax.addPropEditableAttribute("01#" + "Identifier");
		// semElementNoSyntax.addPropVisibleAttribute("01#" + "Identifier");
		semElementNoSyntax.addPropEditableAttribute("02#" + "Visible");
		semElementNoSyntax.addPropVisibleAttribute("02#" + "Visible");
		semElementNoSyntax.addPropEditableAttribute("03#" + "Name");
		semElementNoSyntax.addPropVisibleAttribute("03#" + "Name");
		semElementNoSyntax.addPropEditableAttribute("06#" + "value");
		semElementNoSyntax.addPropVisibleAttribute("06#" + "value");

		// semElement.addPanelVisibleAttribute("01#" + "Name");
		// semElement.addPanelSpacersAttribute("#" + "Name" + "#");
		semElementNoSyntax.addPanelSpacersAttribute("#" + "value" + "#\n\n");

		InstConcept instSemEnum = new InstConcept("Enumeration", null,
				semElementNoSyntax);

		MetaConcept enumeration = new MetaConcept('E', "Enumeration", true,
				"Enumeration", "refasenumeration", "MetaEnumeration", 100, 150,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemEnum, true);

		variabilityInstVertex.put("Enumeration", new InstConcept("Enumeration",
				metaBasicConcept, enumeration));

		SemanticConcept semOverTwoRelation = new SemanticConcept(semVertex,
				"OverTwoRelation");

		semOverTwoRelation.putSemanticAttribute(
				"MetaType",
				new SyntaxAttribute("MetaType", "Enumeration", false,
						"MetaConcept Type", ConceptType.class
								.getCanonicalName(), "MetaOverTwoRelation", 0,
						-1, "", "", -1, "", ""));
		semOverTwoRelation.putSemanticAttribute(
				"SemanticType",
				new SemanticAttribute("SemanticType", "Class", false,
						"Semantic Type", SemanticConcept.class
								.getCanonicalName(), "O", null, "", 0, -1, "",
						"", -1, "", ""));

		semOverTwoRelation.addPropVisibleAttribute("00#" + "MetaType");
		semOverTwoRelation.addPropVisibleAttribute("00#" + "SemanticType");
		semOverTwoRelation.addPropEditableAttribute("00#" + "SemanticType");
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);

		semOverTwoRelation.addPanelVisibleAttribute("00#" + "SemanticType");
		semOverTwoRelation
				.addPanelSpacersAttribute("<<MetaOverTwoAsso>>\n{SemType:\"#"
						+ "SemanticType" + "#\"}\n");
		// semOverTwoRelation.addPanelVisibleAttribute("01#" + "Name");
		// semOverTwoRelation.addPanelSpacersAttribute("#" + "Name" + "#");

		InstConcept instSemOverTwoRelation = new InstConcept("OverTwoRelation",
				null, semOverTwoRelation);

		MetaConcept overTwoRelation = new MetaConcept('O', "OverTwoRelation",
				true, "OverTwoRelation", "refasminiclass",
				"MetaOverTwoRelation", 180, 70,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemOverTwoRelation, true);

		overTwoRelation.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		overTwoRelation.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		overTwoRelation.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		overTwoRelation.addModelingAttribute("Type", new SyntaxAttribute(
				"Type", "String", false, "Relation Type", "", 0, -1, "", "",
				-1, "", ""));

		// overTwoRelation.addPropVisibleAttribute("03#" + "Type");
		// overTwoRelation.addPropEditableAttribute("03#" + "Type");
		// overTwoRelation.addPanelVisibleAttribute("03#" + "Type" + "#" +
		// "Type"
		// + "#!=#" + "" + "#" + "");
		// overTwoRelation.addPanelSpacersAttribute("\n{#" + "Type" + "#}");

		InstConcept instOverTwoRelation = new InstConcept("OverTwoRelation",
				metaBasicConcept, overTwoRelation);
		variabilityInstVertex.put("OverTwoRelation", instOverTwoRelation);

		MetaPairwiseRelation metaPairwiseRelNormal = new MetaPairwiseRelation(
				"NormalRelation", false, "Normal Relation", "defaultAsso",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		metaPairwiseRelNormal.addModelingAttribute(
				MetaConcept.VAR_USERIDENTIFIER, new SyntaxAttribute(
						MetaConcept.VAR_USERIDENTIFIER, "String", false,
						"User Identifier", null, 0, 1, "", "", -1, "", ""));
		metaPairwiseRelNormal.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaPairwiseRelNormal.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		constraintInstEdges.put("NormalRelation", new InstPairwiseRelation(
				"NormalRelation", metaPairwiseRelNormal));

		SemanticConcept semExtendRelation = new SemanticConcept(
				semElementNoSyntax, "ExtendRelation");

		InstConcept instSemExtendRelation = new InstConcept("ExtendRelation",
				null, semExtendRelation);

		MetaConcept extendRelation = new MetaConcept('X', "ExtendRelation",
				true, "ExtendRelation", "refasminiclass", "Extend relation",
				150, 70, "/com/variamos/gui/perspeditor/images/concept.png",
				true, Color.BLUE.toString(), 3, instSemExtendRelation, true);
		extendRelation.addPanelVisibleAttribute("01#Name");
		extendRelation
				.addPanelSpacersAttribute("<<MetaExtendsAsso>>\n#Name#\n\n");

		InstConcept instExtendRelation = new InstConcept("ExtendRelation",
				metaBasicConcept, extendRelation);

		variabilityInstVertex.put("ExtendRelation", instExtendRelation);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ce-e-c", instEdge);
		instEdge.setIdentifier("ce-e-c");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instConcept, true);
		instEdge.setSourceRelation(instExtendRelation, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ce-c-e", instEdge);
		instEdge.setIdentifier("ce-c-e");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instExtendRelation, true);
		instEdge.setSourceRelation(instConcept, true);

		MetaPairwiseRelation metaPairwiseRelExtends = new MetaPairwiseRelation(
				"ExtendsRelation", false, "Extends Relation", "refasextends",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		constraintInstEdges.put("ExtendsRelation", new InstPairwiseRelation(
				"ExtendsRelation", metaPairwiseRelExtends));

		SemanticConcept semViewConceptAsso = new SemanticConcept(
				semElementNoSyntax, "ViewConceptAsso");

		InstConcept instSemViewConceptAsso = new InstConcept("ExtendRelation",
				null, semViewConceptAsso);

		MetaConcept viewConceptAsso = new MetaConcept('I', "ViewConceptAsso",
				true, "ViewConceptAsso", "refasminiclass",
				"View-Concept Association", 150, 70,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemViewConceptAsso, true);
		viewConceptAsso.addPanelVisibleAttribute("01#dummy");
		viewConceptAsso
				.addPanelSpacersAttribute("<<MetaViewConceptAsso>>#dummy#\n");
		viewConceptAsso.addModelingAttribute("Palette", new SyntaxAttribute(
				"Palette", "String", false, "Palette Name", "", 0, -1, "", "",
				-1, "", ""));

		viewConceptAsso.addPropEditableAttribute("02#" + "Palette");
		viewConceptAsso.addPropVisibleAttribute("02#" + "Palette");
		viewConceptAsso.addPanelVisibleAttribute("02#" + "Palette" + "#"
				+ "Palette" + "#!=#" + "" + "#" + "");
		viewConceptAsso.addPanelSpacersAttribute("{Palette:#" + "Palette"
				+ "#}\n");

		InstConcept instViewConceptAsso = new InstConcept("ViewConceptAsso",
				metaBasicConcept, viewConceptAsso);
		variabilityInstVertex.put("ViewConceptAsso", instViewConceptAsso);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vc-v-vc", instEdge);
		instEdge.setIdentifier("vc-v-vc");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewConceptAsso, true);
		instEdge.setSourceRelation(instView, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vc-vc-c", instEdge);
		instEdge.setIdentifier("vc-vc-c");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instConcept, true);
		instEdge.setSourceRelation(instViewConceptAsso, true);

		// TODO remove if Claims and SDs are Concepts
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vc-vc-otr", instEdge);
		instEdge.setIdentifier("vc-vc-otr");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instOverTwoRelation, true);
		instEdge.setSourceRelation(instViewConceptAsso, true);

		MetaPairwiseRelation metaPairwiseRelFromView = new MetaPairwiseRelation(
				"ViewRelation", false, "View Relation", "refasviewrel",
				"View-Concept relation", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1, null);

		InstPairwiseRelation instViewRelation = new InstPairwiseRelation(
				"ViewRelation", metaPairwiseRelFromView);

		constraintInstEdges.put("ViewRelation", instViewRelation);

		SemanticConcept semPairwiseRelation = new SemanticConcept(semPWAsso,
				"PairwiseRelation");

		semPairwiseRelation.putSemanticAttribute(
				"SemanticType",
				new SemanticAttribute("SemanticType", "Class", false,
						"Semantic Type", SemanticConcept.class
								.getCanonicalName(), "P", null, "", 0, -1, "",
						"", -1, "", ""));

		semPairwiseRelation.addPropVisibleAttribute("00#" + "SemanticType");
		semPairwiseRelation.addPropEditableAttribute("00#" + "SemanticType");
		semPairwiseRelation.addPanelVisibleAttribute("00#" + "SemanticType");
		semPairwiseRelation
				.addPanelSpacersAttribute("<<MetaPairwiseAsso>>\n{SemType:\"#"
						+ "SemanticType" + "#\",\n");
		// semPairwiseRelation.addPanelVisibleAttribute("10#" + "Name");
		// semPairwiseRelation.addPanelSpacersAttribute("#" + "Name" + "#\n\n");

		InstConcept instSemPairwiseRelationn = new InstConcept(
				"PairwiseRelation", null, semPairwiseRelation);

		MetaConcept pairwiseRelation = new MetaConcept('P', "PairwiseRelation",
				true, "PairwiseRelation", "refasminiclass",
				"MetaPairwiseRelation", 150, 200,
				"/com/variamos/gui/perspeditor/images/concept.png", true,
				Color.BLUE.toString(), 3, instSemPairwiseRelationn, true);

		pairwiseRelation.addModelingAttribute("Type", new SyntaxAttribute(
				"Type", "String", false, "Relation Type", "", 0, -1, "", "",
				-1, "", ""));

		// pairwiseRelation.addPropEditableAttribute("03#" + "Type");
		// pairwiseRelation.addPropVisibleAttribute("03#" + "Type");
		// pairwiseRelation.addPanelVisibleAttribute("03#" + "Type" + "#" +
		// "Type"
		// + "#!=#" + "" + "#" + "");
		// pairwiseRelation.addPanelSpacersAttribute("\n{#" + "Type" + "#}");

		pairwiseRelation.addModelingAttribute("SourceCardinality",
				new SyntaxAttribute("SourceCardinality", "String", false,
						"Source Cardinality", "String", "[]", 0, -1, "", "",
						-1, "", ""));

		pairwiseRelation.addPropEditableAttribute("04#" + "SourceCardinality");
		pairwiseRelation.addPropVisibleAttribute("04#" + "SourceCardinality");
		pairwiseRelation.addPanelVisibleAttribute("01#" + "SourceCardinality"
				+ "#" + "Type" + "#!=#" + "" + "#" + "");
		pairwiseRelation.addPanelSpacersAttribute("SourCard:#"
				+ "SourceCardinality" + "#,");

		pairwiseRelation.addModelingAttribute("TargetCardinality",
				new SyntaxAttribute("TargetCardinality", "String", false,
						"Target Cardinality", "String", "[]", 0, 5, "", "", 5,
						"TargCard:#-#}\n", "Type#!=##"));

		pairwiseRelation.addPropEditableAttribute("05#" + "TargetCardinality");
		pairwiseRelation.addPropVisibleAttribute("05#" + "TargetCardinality");
		pairwiseRelation.addPanelVisibleAttribute("02#" + "TargetCardinality"
				+ "#" + "Type" + "#!=#" + "" + "#" + "");
		pairwiseRelation.addPanelSpacersAttribute("TargCard:#"
				+ "TargetCardinality" + "#}\n");

		InstConcept instPairwiseRelation = new InstConcept("PairwiseRelation",
				metaBasicConcept, pairwiseRelation);
		variabilityInstVertex.put("PairwiseRelation", instPairwiseRelation);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cpw-pw-c", instEdge);
		instEdge.setIdentifier("cpw-pw-c");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instConcept, true);
		instEdge.setSourceRelation(instPairwiseRelation, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cpw-c-pw", instEdge);
		instEdge.setIdentifier("cpw-c-pw");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instPairwiseRelation, true);
		instEdge.setSourceRelation(instConcept, true);

		// TODO remove if Claims and SDs are Concepts
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cpw-pw-otr", instEdge);
		instEdge.setIdentifier("cpw-pw-otr");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instOverTwoRelation, true);
		instEdge.setSourceRelation(instPairwiseRelation, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cpw-otr-pw", instEdge);
		instEdge.setIdentifier("cpw-otr-pw");
		instEdge.setEditableMetaElement(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instPairwiseRelation, true);
		instEdge.setSourceRelation(instOverTwoRelation, true);
	}

	/**
	 * Creates the default elements (objects) of the Semantic Model (Semantic
	 * concepts). Elements are loaded in the Semantic perspective (advanced
	 * perspectiv) and defines the semantic for elements on the syntax
	 * perspective (associated to concepts).
	 */
	@SuppressWarnings("unchecked")
	public void createDefaultSemantic() {
		MetaConcept metaConcept = (MetaConcept) ((InstConcept) this
				.getSyntaxRefas().getVertex("Concept"))
				.getEditableMetaElement();
		MetaEnumeration metaEnumeration = (MetaEnumeration) ((InstConcept) this
				.getSyntaxRefas().getVertex("TypeEnumeration"))
				.getEditableMetaElement();
		MetaConcept metaPairwiseRelation = (MetaConcept) ((InstConcept) this
				.getSyntaxRefas().getVertex("CSPairWiseRelation"))
				.getEditableMetaElement();
		MetaConcept metaOverTwoRelation = (MetaConcept) ((InstConcept) this
				.getSyntaxRefas().getVertex("CSOverTwoRelation"))
				.getEditableMetaElement();
		MetaPairwiseRelation metaPairwRelCCExt = (MetaPairwiseRelation) ((InstPairwiseRelation) this
				.getSyntaxRefas().getConstraintInstEdge("ExtendsCCRel"))
				.getEditableMetaElement();
		MetaPairwiseRelation metaPairwRelOCExt = (MetaPairwiseRelation) ((InstPairwiseRelation) this
				.getSyntaxRefas().getConstraintInstEdge("ExtendsOCRel"))
				.getEditableMetaElement();

		MetaPairwiseRelation metaPairwRelAso = (MetaPairwiseRelation) ((InstPairwiseRelation) this
				.getSyntaxRefas().getConstraintInstEdge("DirectRelation"))
				.getEditableMetaElement();

		InstEnumeration instVertexHStrME = new InstEnumeration(
				"HardStructEnumeration", metaEnumeration);
		// variabilityInstVertex.put("HardStructEnumeration", instVertexHStrME);

		ArrayList<InstAttribute> c = (ArrayList<InstAttribute>) ((InstAttribute) instVertexHStrME
				.getInstAttribute("value")).getInstAttributeAttribute("Value");
		InstAttribute a = new InstAttribute();
		a.setInstAttributeAttribute("Value",
				"1-means_ends-means_ends-true-true-true-1-1-1-1");
		a.setInstAttributeAttribute("DisplayValue", null);
		a.setInstAttributeAttribute("attributeIden", "EnumValue");
		a.setInstAttributeAttribute("Identifier", "enum11");
		c.add(a);
		a = new InstAttribute();
		a.setInstAttributeAttribute("Value",
				"12-impl.-Impl.-true-true-true-1-1-1-1");
		a.setInstAttributeAttribute("DisplayValue", null);
		a.setInstAttributeAttribute("attributeIden", "EnumValue");
		a.setInstAttributeAttribute("Identifier", "enum12");
		c.add(a);

		InstEnumeration instVertexHSideME = new InstEnumeration(
				"HardSideEnumeration", metaEnumeration);
		// variabilityInstVertex.put("HardSideEnumeration", instVertexHSideME);

		c = (ArrayList<InstAttribute>) ((InstAttribute) instVertexHSideME
				.getInstAttribute("value")).getInstAttributeAttribute("Value");
		a = new InstAttribute();
		a.setInstAttributeAttribute("Value",
				"1-conflict-conflict-false-true-true-1-1-1-1");
		a.setInstAttributeAttribute("DisplayValue", null);
		a.setInstAttributeAttribute("attributeIden", "EnumValue");
		a.setInstAttributeAttribute("Identifier", "enum1");
		c.add(a);
		a = new InstAttribute();
		a.setInstAttributeAttribute("Value",
				"2-altern.-altern.-false-true-true-1-1-1-1");
		a.setInstAttributeAttribute("DisplayValue", null);
		a.setInstAttributeAttribute("attributeIden", "EnumValue");
		a.setInstAttributeAttribute("Identifier", "enum2");
		c.add(a);
		a = new InstAttribute();
		a.setInstAttributeAttribute("Value",
				"3-preferred-pref.-false-true-true-1-1-1-1");
		a.setInstAttributeAttribute("DisplayValue", null);
		a.setInstAttributeAttribute("attributeIden", "EnumValue");
		a.setInstAttributeAttribute("Identifier", "enum3");
		c.add(a);
		a = new InstAttribute();
		a.setInstAttributeAttribute("Value",
				"4-req.-req..-false-true-true-1-1-1-1");
		a.setInstAttributeAttribute("DisplayValue", null);
		a.setInstAttributeAttribute("attributeIden", "EnumValue");
		a.setInstAttributeAttribute("Identifier", "enum4");
		c.add(a);
		a = new InstAttribute();
		a.setInstAttributeAttribute("Value",
				"5-cond.-cond.-false-true-true-1-1-1-1");
		a.setInstAttributeAttribute("DisplayValue", null);
		a.setInstAttributeAttribute("attributeIden", "EnumValue");
		a.setInstAttributeAttribute("Identifier", "enum5");
		c.add(a);

		InstEnumeration instClaimSemOTAsso = new InstEnumeration(
				"ClaimSemOTAsso", metaEnumeration);
		// variabilityInstVertex.put("ClaimSemOTAsso", instClaimSemOTAsso);

		c = (ArrayList<InstAttribute>) ((InstAttribute) instClaimSemOTAsso
				.getInstAttribute("value")).getInstAttributeAttribute("Value");
		a = new InstAttribute();
		a.setInstAttributeAttribute("Value",
				"1#And#And#false#false#false#2#1#1#1");
		a.setInstAttributeAttribute("DisplayValue", null);
		a.setInstAttributeAttribute("attributeIden", "EnumValue");
		a.setInstAttributeAttribute("Identifier", "enum1");
		c.add(a);
		a = new InstAttribute();
		a.setInstAttributeAttribute("Value", "2#Or#Or#false#true#true#2#1#1#1");
		a.setInstAttributeAttribute("DisplayValue", null);
		a.setInstAttributeAttribute("attributeIden", "EnumValue");
		a.setInstAttributeAttribute("Identifier", "enum2");
		c.add(a);
		a = new InstAttribute();
		a.setInstAttributeAttribute("Value",
				"3#mutex#mutex#false#true#true#2#1#1#1");
		a.setInstAttributeAttribute("DisplayValue", null);
		a.setInstAttributeAttribute("attributeIden", "EnumValue");
		a.setInstAttributeAttribute("Identifier", "enum3");
		c.add(a);

		SemanticConcept semGeneralElement = new SemanticConcept(
				"GeneralElement"); // From this name depends all the operations,
									// do not change it
		List<IntSemanticExpression> semanticExpressions = new ArrayList<IntSemanticExpression>();

		semGeneralElement.setSemanticExpresions(semanticExpressions);
		InstVertex instVertexGE = new InstConcept("GeneralElement",
				metaConcept, semGeneralElement);

		SemanticExpression t1 = new SemanticExpression("1", this
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"IsRootFeature", 0);

		SemanticExpression t3 = new SemanticExpression("3", this
				.getSemanticExpressionTypes().get("NotEquals"), instVertexGE,
				"userIdentifier", "Feature");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, "IsRootFeature", 1);

		t3 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, "sysRequired", 1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t1, t3);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, "IsRootFeature", 0);

		t3 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, instVertexGE, "Required",
				"sysRequired");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t1, t3);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, "IsRootFeature", 1);

		t3 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, "Selected", 1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t1, t3);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, "NextPrefSelected", 0);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, "NextNotPrefSelected", 0);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, "HasParent", 1);

		SemanticExpression t2 = new SemanticExpression("2", this
				.getSemanticExpressionTypes().get("NotEquals"), instVertexGE,
				"userIdentifier", "GeneralFeature");

		t3 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"NotEquals"), instVertexGE, "userIdentifier", "LeafFeature");

		t3 = new SemanticExpression("4", this.getSemanticExpressionTypes().get(
				"And"), t2, t3);

		t1 = new SemanticExpression("4", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Sum"), instVertexGE, instVertexGE, "NextReqSelected",
				"ConfigSelected");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, "NextPrefSelected", true, t1);

		t3 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, instVertexGE, "NextPrefSelected",
				"ConfigSelected");

		t1 = new SemanticExpression("4", this.getSemanticExpressionTypes().get(
				"Sum"), t1, t3);

		t1 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, "Opt", true, t1);

		semanticExpressions.add(t1);

		t2 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, "Opt", 0);

		semanticExpressions.add(t2);

		t2 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"NotEquals"), instVertexGE, "userIdentifier", "SoftGoal");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Subtraction"), instVertexGE, "Selected", 1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Product"), 8, true, t1);

		t3 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, "NextReqSelected", 4);

		t1 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"Product"), t1, t3);

		t1 = new SemanticExpression("4", this.getSemanticExpressionTypes().get(
				"Sum"), instVertexGE, "NextPrefSelected", true, t1);

		t1 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, "Order", true, t1);

		t1 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"Implies"), t2, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("4", this.getSemanticExpressionTypes().get(
				"Sum"), instVertexGE, instVertexGE, "ConfigSelected",
				"NextReqSelected");

		t1 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"Sum"), instVertexGE, "Core", true, t1);

		t1 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"LessOrEquals"), 1, false, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("4", this.getSemanticExpressionTypes().get(
				"Or"), instVertexGE, instVertexGE, "ConfigNotSelected",
				"NextNotPrefSelected");

		t1 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"Or"), instVertexGE, "Dead", true, t1);

		t1 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"DoubleImplies"), instVertexGE, "NotAvailable", true, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"Or"), instVertexGE, instVertexGE, "NextReqSelected",
				"NextPrefSelected");

		t2 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"Or"), instVertexGE, instVertexGE, "Core", "ConfigSelected");

		t1 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"Or"), t1, t2);

		t1 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"DoubleImplies"), instVertexGE, "Selected", true, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"Product"), instVertexGE, instVertexGE, "Selected",
				"NotAvailable");

		t1 = new SemanticExpression("5", this.getSemanticExpressionTypes().get(
				"Equals"), 0, false, t1);

		semanticExpressions.add(t1);

		variabilityInstVertex.put("GeneralElement", instVertexGE);

		// Design attributes: Do not change identifiers

		semGeneralElement.putSemanticAttribute("Description",
				new SemanticAttribute("Description", "String", false,
						"Description", "", 0, -1, "", "", -1, "", ""));

		semGeneralElement.putSemanticAttribute("Required",
				new SemanticAttribute("Required", "Boolean", true,
						"Is Required", false, 2, -1, "", "", -1, "", ""));

		semGeneralElement.putSemanticAttribute("Scope", new SemanticAttribute(
				"Scope", "Boolean", true, "Global Scope", true, 0, -1, "", "",
				-1, "", ""));

		semGeneralElement.putSemanticAttribute("ConcernLevel",
				new SemanticAttribute("ConcernLevel", "Class", false,
						"Concern Level", InstConcept.class.getCanonicalName(),
						"CG", null, "", 2, -1, "", "", -1, "", ""));

		semGeneralElement.putSemanticAttribute("Core", new SemanticAttribute(
				"Core", "Boolean", false, "Is a Core Concept", false, 2, -1,
				"", "", -1, "", ""));

		semGeneralElement.putSemanticAttribute("Dead", new SemanticAttribute(
				"Dead", "Boolean", false, "Is a Dead Concept", false, 2, -1,
				"", "", -1, "", ""));

		semGeneralElement.putSemanticAttribute("IsRootFeature",
				new SemanticAttribute("IsRootFeature", "Boolean", true,
						"Is a Root Feature Concept", false, 2, -1, "", "", -1,
						"", ""));

		semGeneralElement.putSemanticAttribute("IgnoreForSimulation",
				new SemanticAttribute("IgnoreForSimulation", "Boolean", true,
						"Ignore for Simulation", false, 0, -1, "", "", -1, "",
						""));

		semGeneralElement.addPropEditableAttribute("04#" + "Required");
		semGeneralElement.addPropEditableAttribute("05#" + "Scope");
		semGeneralElement.addPropEditableAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");

		semGeneralElement.addPropEditableAttribute("08#"
				+ "IgnoreForSimulation");
		semGeneralElement.addPropVisibleAttribute("04#" + "Required");
		semGeneralElement.addPropVisibleAttribute("05#" + "Scope");
		semGeneralElement.addPropVisibleAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");
		semGeneralElement.addPropVisibleAttribute("07#" + "Core");
		semGeneralElement.addPropVisibleAttribute("08#" + "Dead");
		semGeneralElement
				.addPropVisibleAttribute("08#" + "IgnoreForSimulation");

		semGeneralElement.addPanelVisibleAttribute("00#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false");
		semGeneralElement.addPanelSpacersAttribute("<<#" + "ConcernLevel"
				+ "#>>\n");

		// Configuration attributes: do no change identifiers

		semGeneralElement.putSemanticAttribute("Active",
				new GlobalConfigAttribute("Active", "Boolean", true,
						"Is Active", true, 0, -1, "", "", -1, "", ""));
		semGeneralElement.putSemanticAttribute("Visibility",
				new GlobalConfigAttribute("Visibility", "Boolean", false,
						"Is Visible", true, 0, -1, "", "", -1, "", ""));

		semGeneralElement.putSemanticAttribute("Allowed",
				new GlobalConfigAttribute("Allowed", "Boolean", true,
						"Is Allowed", true, 0, -1, "", "", -1, "", ""));
		semGeneralElement.putSemanticAttribute("RequiredLevel",
				new SemanticAttribute("RequiredLevel", "Integer", false,
						"Required Level", 0, new RangeDomain(0, 4), 0, -1, "",
						"", -1, "", ""));
		// TODO define domain or Enum Level

		semGeneralElement.putSemanticAttribute("ConfigSelected",
				new GlobalConfigAttribute("ConfigSelected", "Boolean", true,
						"Configuration Selected", false, 2, -1, "", "", -1, "",
						""));
		semGeneralElement.putSemanticAttribute("ConfigNotSelected",
				new GlobalConfigAttribute("ConfigNotSelected", "Boolean", true,
						"Configuration Not Selected", false, 2, -1, "", "", -1,
						"", ""));

		semGeneralElement
				.putSemanticAttribute("DashBoardVisible",
						new GlobalConfigAttribute("DashBoardVisible",
								"Boolean", false, "Visible on Dashboard", true,
								0, -1, "", "", -1, "", ""));

		semGeneralElement.putSemanticAttribute("ExportOnConfig",
				new GlobalConfigAttribute("ExportOnConfig", "Boolean", false,
						"Export on Configuration", true, 0, -1, "", "", -1, "",
						""));

		semGeneralElement.addPropEditableAttribute("15#" + "ConfigSelected"
				+ "#" + "Core" + "#==#" + "false" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("16#" + "ConfigNotSelected"
				+ "#" + "Dead" + "#==#" + "false" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("03#" + "DashBoardVisible");
		semGeneralElement.addPropEditableAttribute("04#" + "ExportOnConfig");

		semGeneralElement.addPropVisibleAttribute("01#" + "Active");
		semGeneralElement.addPropVisibleAttribute("02#" + "Visibility");

		semGeneralElement.addPropVisibleAttribute("03#" + "DashBoardVisible");
		semGeneralElement.addPropVisibleAttribute("04#" + "ExportOnConfig");
		semGeneralElement.addPropVisibleAttribute("05#" + "RequiredLevel" + "#"
				+ "Core" + "#==#" + "true");

		semGeneralElement.addPropVisibleAttribute("15#" + "ConfigSelected"
				+ "#" + "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropVisibleAttribute("16#" + "ConfigNotSelected"
				+ "#" + "Active" + "#==#" + "true" + "#" + "false");

		// Simulation attributes: do not modify identifiers

		semGeneralElement.putSemanticAttribute("InitialRequiredLevel",
				new ExecCurrentStateAttribute("InitialRequiredLevel",
						"Integer", false, "Initial Required Level", 0,
						new RangeDomain(0, 5), 0, -1, "", "", -1, "", ""));
		semGeneralElement.putSemanticAttribute("SimRequiredLevel",
				new ExecCurrentStateAttribute("SimRequiredLevel", "Integer",
						false, "Required Level", 0, new RangeDomain(0, 5), 0,
						-1, "", "", -1, "", ""));
		semGeneralElement.putSemanticAttribute("HasParent",
				new ExecCurrentStateAttribute("HasParent", "Boolean", false,
						"Has Parent", true, 0, -1, "", "", -1, "", ""));

		semGeneralElement.putSemanticAttribute("Opt",
				new ExecCurrentStateAttribute("Opt", "Integer", false,
						"FilterVariable", 0, new RangeDomain(0, 20), 0, -1, "",
						"", -1, "", ""));

		semGeneralElement.putSemanticAttribute("Order",
				new ExecCurrentStateAttribute("Order", "Integer", false,
						"SortVariable", 0, new RangeDomain(0, 40), 0, -1, "",
						"", -1, "", ""));

		semGeneralElement.putSemanticAttribute("NextNotSelected",
				new ExecCurrentStateAttribute("NextNotSelected", "Boolean",
						false, "Not selected(inactive)", false, 0, -1, "", "",
						-1, "", ""));

		semGeneralElement.putSemanticAttribute("NextPrefSelected",
				new ExecCurrentStateAttribute("NextPrefSelected", "Boolean",
						false, "Selected by configuration", false, 0, -1, "",
						"", -1, "", ""));

		semGeneralElement.putSemanticAttribute("NextNotPrefSelected",
				new ExecCurrentStateAttribute("NextNotPrefSelected", "Boolean",
						false, "Not Selected by configuration", false, 0, -1,
						"", "", -1, "", ""));

		semGeneralElement.putSemanticAttribute("NextReqSelected",
				new ExecCurrentStateAttribute("NextReqSelected", "Boolean",
						false, "Selected by simulation", false, 0, -1, "", "",
						-1, "", ""));

		semGeneralElement.addPropVisibleAttribute("01#" + "Selected");
		semGeneralElement.addPropVisibleAttribute("03#" + "NextPrefSelected");
		semGeneralElement.addPropVisibleAttribute("05#" + "NextReqSelected");

		semGeneralElement.addPropVisibleAttribute("02#" + "NotAvailable");
		semGeneralElement.addPropVisibleAttribute("04#" + "NextNotSelected");
		semGeneralElement
				.addPropVisibleAttribute("06#" + "NextNotPrefSelected");

		SemanticConcept semHardConcept = new SemanticConcept(semGeneralElement,
				"semHardConcept");

		semHardConcept.putSemanticAttribute("satisfactionType",
				new SemanticAttribute("satisfactionType", "Enumeration", false,
						"satisfactionType",
						"com.variamos.semantic.types.SatisfactionType",
						"achieve", "", 0, -1, "", "", -1, "", ""));
		semHardConcept.addPropEditableAttribute("01#" + "satisfactionType");
		semHardConcept.addPropVisibleAttribute("01#" + "satisfactionType");

		InstVertex instVertexHC = new InstConcept("HardConcept", metaConcept,
				semHardConcept);
		variabilityInstVertex.put("HardConcept", instVertexHC);

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("hctoge", instEdge);
		instEdge.setIdentifier("hctoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexHC, true);

		// Feature concepts

		SemanticConcept semFeature = new SemanticConcept(semGeneralElement,
				"Feature");
		InstVertex instVertexF = new InstConcept("Feature", metaConcept,
				semFeature);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semFeature.setSemanticExpresions(semanticExpressions);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexGE, "IsRootFeature", 1);

		SemanticExpression t2_1 = new SemanticExpression("2-1", this
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"userIdentifier", "mandatory");

		SemanticExpression t2_2 = new SemanticExpression("2-2", this
				.getSemanticExpressionTypes().get("Equals"), instVertexGE,
				"userIdentifier", "optional");

		t2 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Or"), t2_1, t2_2);

		t3 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"And"), 0, true, t2);

		t3 = new SemanticExpression("3-", this.getSemanticExpressionTypes()
				.get("Equals"), 0, false, t3);

		t1 = new SemanticExpression("4", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		variabilityInstVertex.put("Feature", instVertexF);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ftoge", instEdge);
		instEdge.setIdentifier("ftoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexF, true);

		// definition of other concepts

		SemanticConcept semAssumption = new SemanticConcept(semHardConcept,
				"Assumption");
		InstVertex instVertexAS = new InstConcept("Assumption", metaConcept,
				semAssumption);
		variabilityInstVertex.put("Assumption", instVertexAS);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("assutoge", instEdge);
		instEdge.setIdentifier("assutoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexAS, true);

		SemanticConcept semGoal = new SemanticConcept(semHardConcept, "Goal");
		semGoal.addPanelVisibleAttribute("01#" + "satisfactionType");
		semGoal.addPanelSpacersAttribute("<#" + "satisfactionType" + "#>\n");
		InstVertex instVertexG = new InstConcept("Goal", metaConcept, semGoal);
		variabilityInstVertex.put("Goal", instVertexG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("gtoge", instEdge);
		instEdge.setIdentifier("gtoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexG, true);

		SemanticConcept semOperationalization = new SemanticConcept(
				semHardConcept, "Operationalization");

		semOperationalization.putSemanticAttribute("attributeValue",
				new SyntaxAttribute("attributeValue", "Set", false, "values",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));
		semOperationalization.addPropVisibleAttribute("06#" + "attributeValue");
		semOperationalization
				.addPropEditableAttribute("06#" + "attributeValue");

		InstVertex instVertexOper = new InstConcept("Operationalization",
				metaConcept, semOperationalization);
		variabilityInstVertex.put("Operationalization", instVertexOper);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("opertoge", instEdge);
		instEdge.setIdentifier("opertoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instVertexOper, true);

		SoftSemanticConcept semSoftgoal = new SoftSemanticConcept(
				semGeneralElement, "SoftGoal");

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semSoftgoal.setSemanticExpresions(semanticExpressions);

		InstVertex instVertexSG = new InstConcept("Softgoal", metaConcept,
				semSoftgoal);

		t1 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"LessOrEquals"), instVertexSG, instVertexSG, "SDReqLevel",
				"ClaimExpLevel");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"DoubleImplies"), instVertexSG, "Selected", true, t1);

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexSG, "satisficingType", "high");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"GreaterOrEq"), instVertexSG, instVertexSG, "SDReqLevel",
				"ClaimExpLevel");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"DoubleImplies"), instVertexSG, "Selected", true, t1);

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexSG, "satisficingType", "low");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexSG, instVertexSG, "SDReqLevel",
				"ClaimExpLevel");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"DoubleImplies"), instVertexSG, "Selected", true, t1);

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexSG, "satisficingType", "close");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		variabilityInstVertex.put("Softgoal", instVertexSG);

		semSoftgoal.putSemanticAttribute("SDReqLevel",
				new ExecCurrentStateAttribute("SDReqLevel", "Integer", false,
						"Required Level by SD", 0, new RangeDomain(0, 4), 2,
						-1, "", "", -1, "", ""));

		semSoftgoal.putSemanticAttribute("ClaimExpLevel",
				new ExecCurrentStateAttribute("ClaimExpLevel", "Integer",
						false, "Expected Level by Claim", 0, new RangeDomain(0,
								4), 2, -1, "", "", -1, "", ""));

		semSoftgoal.addPropVisibleAttribute("16#" + "SDReqLevel");
		semSoftgoal.addPropVisibleAttribute("16#" + "ClaimExpLevel");

		semSoftgoal.addPropEditableAttribute("18#" + "SDReqLevel");
		semSoftgoal.addPropEditableAttribute("18#" + "ClaimExpLevel");

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgtoge", instEdge);
		instEdge.setIdentifier("sgtoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexSG, true);

		SemanticVariable semVariable = new SemanticVariable("Variable");

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semVariable.setSemanticExpresions(semanticExpressions);

		InstVertex instVertexVAR = new InstConcept("Variable", metaConcept,
				semVariable);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexVAR, instVertexVAR, "variableConfigValue",
				"value");

		t3 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"Equals"), instVertexVAR, "variableConfigDomain", "");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		semVariable
				.putSemanticAttribute("DashBoardVisible",
						new GlobalConfigAttribute("DashBoardVisible",
								"Boolean", false, "Visible on Dashboard", true,
								0, -1, "", "", -1, "", ""));

		semVariable.putSemanticAttribute("ExportOnConfig",
				new GlobalConfigAttribute("ExportOnConfig", "Boolean", false,
						"Export on Configuration", true, 0, -1, "", "", -1, "",
						""));

		semVariable.putSemanticAttribute("Scope", new SemanticAttribute(
				"Scope", "Boolean", true, "Global Scope", true, 0, -1, "", "",
				-1, "", ""));

		semVariable.putSemanticAttribute("ConcernLevel", new SemanticAttribute(
				"ConcernLevel", "Class", false, "Concern Level",
				InstConcept.class.getCanonicalName(), "CG", null, "", 0, -1,
				"", "", -1, "", ""));

		semVariable.addPropEditableAttribute("03#" + "DashBoardVisible");
		semVariable.addPropEditableAttribute("04#" + "ExportOnConfig");
		semVariable.addPropVisibleAttribute("03#" + "DashBoardVisible");
		semVariable.addPropVisibleAttribute("04#" + "ExportOnConfig");

		semVariable.addPropEditableAttribute("05#" + "Scope");
		semVariable.addPropEditableAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");

		semVariable.addPropVisibleAttribute("05#" + "Scope");
		semVariable.addPropVisibleAttribute("06#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false" + "#" + "");

		semVariable.addPanelVisibleAttribute("00#" + "ConcernLevel" + "#"
				+ "Scope" + "#==#" + "false");
		semVariable.addPanelSpacersAttribute("<<#" + "ConcernLevel" + "#>>\n");

		variabilityInstVertex.put("Variable", instVertexVAR);

		SemanticContextGroup semContextGroup = new SemanticContextGroup(
				"ConcernLevel");
		InstVertex instVertexCG = new InstConcept("ConcernLevel", metaConcept,
				semContextGroup);
		variabilityInstVertex.put("ConcernLevel", instVertexCG);

		SemanticConcept semAsset = new SemanticConcept(semGeneralElement,
				"Asset");
		InstVertex instVertexAsset = new InstConcept("Asset", metaConcept,
				semAsset);
		variabilityInstVertex.put("Asset", instVertexAsset);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("astoge", instEdge);
		instEdge.setIdentifier("astoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelCCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		List<IntSemanticRelationType> claimSemOverTwoRelList = new ArrayList<IntSemanticRelationType>();
		claimSemOverTwoRelList.add(new SemanticRelationType("and", "And",
				"And", false, false, false, 2, -1, 1, 1));
		claimSemOverTwoRelList.add(new SemanticRelationType("or", "Or", "Or",
				false, true, true, 2, -1, 1, 1));
		claimSemOverTwoRelList.add(new SemanticRelationType("mutex", "Mutex",
				"Mutex.", false, true, true, 2, -1, 1, 1));

		SemanticReasoningConcept semClaim = new SemanticReasoningConcept(
				semGeneralElement, "Claim", true, claimSemOverTwoRelList);
		InstVertex instVertexCL = new InstConcept("Claim", metaOverTwoRelation,
				semClaim);
		variabilityInstVertex.put("Claim", instVertexCL);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cltoge", instEdge);
		instEdge.setIdentifier("cltoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexCL, true);

		semClaim.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression",
						InstanceExpression.class.getCanonicalName(), false,
						"Conditional Expression", null, 0, -1, "", "", -1, "",
						""));
		semClaim.putSemanticAttribute("CompExp", new GlobalConfigAttribute(
				"CompExp", "Boolean", false, "Boolean Comp. Expression", true,
				0, -1, "", "", -1, "", ""));
		semClaim.putSemanticAttribute("ConfidenceLevel", new SemanticAttribute(
				"ConfidenceLevel", "Integer", false, "Confidence Level", 1,
				new RangeDomain(1, 5), 0, -1, "", "", -1, "", ""));
		semClaim.putSemanticAttribute("ClaimSelected",
				new GlobalConfigAttribute("ClaimSelected", "Boolean", false,
						"Claim Selected", false, 0, -1, "", "", -1, "", ""));
		semClaim.putSemanticAttribute("ClaimExpression", new SemanticAttribute(
				"ClaimExpression", "String", false, "Claim Expression Text",
				"", 0, -1, "", "", -1, "", ""));

		// semClaim.addPanelVisibleAttribute("01#" + "Operationalizations");
		semClaim.addPanelVisibleAttribute("03#" + "ConditionalExpression"); // TODO
																			// move
																			// to
																			// semantic
																			// attributes

		// semClaim.addPropEditableAttribute("01#" + "Operationalizations");
		semClaim.addPropEditableAttribute("03#" + "ConditionalExpression");
		semClaim.addPropEditableAttribute("04#" + "ClaimExpression");
		semClaim.addPropEditableAttribute("05#" + "ConfidenceLevel");

		// semClaim.addPropVisibleAttribute("01#" + "Operationalizations");

		semClaim.addPropVisibleAttribute("03#" + "ConditionalExpression");
		semClaim.addPropVisibleAttribute("04#" + "ClaimExpression");
		semClaim.addPropVisibleAttribute("05#" + "ConfidenceLevel");

		semClaim.addPropEditableAttribute("01#" + "CompExp");
		semClaim.addPropVisibleAttribute("01#" + "CompExp");

		semClaim.addPropVisibleAttribute("02#" + "ClaimSelected");

		semClaim.addPanelVisibleAttribute("10#" + "ClaimExpression");

		// semClaim.addPanelSpacersAttribute("#" + "Operationalizations" +
		// "#\n#");

		SemanticReasoningConcept semSoftDependency = new SemanticReasoningConcept(
				semGeneralElement, "SoftDependency", true, null);
		InstVertex instVertexSD = new InstConcept("SoftDep", metaConcept,
				semSoftDependency);
		variabilityInstVertex.put("SoftDep", instVertexSD);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sdtoge", instEdge);
		instEdge.setIdentifier("sdtoge");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelOCExt);
		instEdge.setTargetRelation(instVertexGE, true);
		instEdge.setSourceRelation(instVertexSD, true);

		semSoftDependency.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression",
						InstanceExpression.class.getCanonicalName(), false,
						"Conditional Expression", null, 0, -1, "", "", -1, "",
						""));
		semSoftDependency.putSemanticAttribute("SDExpression",
				new SemanticAttribute("SDExpression", "String", false,
						"SD Expression Text", "", 2, -1, "", "", -1, "", ""));
		semSoftDependency.putSemanticAttribute("CompExp",
				new GlobalConfigAttribute("CompExp", "Boolean", false,
						"Boolean Comp. Expression", true, 2, -1, "", "", -1,
						"", ""));
		semSoftDependency.putSemanticAttribute("SDSelected",
				new GlobalConfigAttribute("SDSelected", "Boolean", false,
						"SD Selected", false, 2, -1, "", "", -1, "", ""));

		semSoftDependency.addPanelVisibleAttribute("03#"
				+ "ConditionalExpression");

		// semSoftDependency.addPanelVisibleAttribute("10#" + "SDExpression");

		semSoftDependency.addPropEditableAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropEditableAttribute("04#" + "SDExpression");

		semSoftDependency.addPropVisibleAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropVisibleAttribute("04#" + "SDExpression");

		semSoftDependency.addPropEditableAttribute("01#" + "CompExp");
		semSoftDependency.addPropVisibleAttribute("01#" + "CompExp");

		semSoftDependency.addPropVisibleAttribute("02#" + "SDSelected");

		semSoftDependency.addPanelVisibleAttribute("10#" + "SDExpression");

		// Elements Lists
		// List<AbstractSemanticVertex> semAssumptionElements = new
		// ArrayList<AbstractSemanticVertex>();
		// semAssumptionElements.add(semAssumption);

		// List<AbstractSemanticVertex> SemGoalOperElements = new
		// ArrayList<AbstractSemanticVertex>();
		// SemGoalOperElements.add(semGoal);
		// SemGoalOperElements.add(semOperationalization);

		// List<AbstractSemanticVertex> semGoalElements = new
		// ArrayList<AbstractSemanticVertex>();
		// semGoalElements.add(semGoal);
		//
		// List<AbstractSemanticVertex> semOperationalizationElements = new
		// ArrayList<AbstractSemanticVertex>();
		// semOperationalizationElements.add(semOperationalization);
		//
		// List<AbstractSemanticVertex> semSoftgoalElements = new
		// ArrayList<AbstractSemanticVertex>();
		// semSoftgoalElements.add(semSoftgoal);
		//
		// List<AbstractSemanticVertex> semClaimsElements = new
		// ArrayList<AbstractSemanticVertex>();
		// semClaimsElements.add(semClaim);
		//
		// List<AbstractSemanticVertex> semSDElements = new
		// ArrayList<AbstractSemanticVertex>();
		// semSDElements.add(semSoftDependency);

		// Relations

		List<IntSemanticRelationType> hardSemOverTwoRelList = new ArrayList<IntSemanticRelationType>();
		hardSemOverTwoRelList.add(new SemanticRelationType("and", "And", "And",
				false, false, false, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("or", "Or", "Or",
				false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("mutex", "Mutex",
				"Mutex.", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("range", "Range",
				"Range", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("none", "None",
				"None", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType("other", "other",
				"other", false, true, true, 2, -1, 1, 1));
		hardSemOverTwoRelList.add(new SemanticRelationType(
				"And/Or/Mutex/[m,n]", "And/Or/Mutex/[m,n]", "AOMR", false,
				true, true, 2, -1, 1, 1));

		List<IntSemanticRelationType> featSemOverTwoRelList = new ArrayList<IntSemanticRelationType>();
		featSemOverTwoRelList.add(new SemanticRelationType("and", "And", "And",
				false, false, false, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("or", "Or", "Or",
				false, true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("mutex", "Mutex",
				"Mutex.", false, true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("range", "Range",
				"Range", false, true, true, 2, -1, 1, 1));
		featSemOverTwoRelList.add(new SemanticRelationType("other", "other",
				"other", false, true, true, 2, -1, 1, 1));

		SemanticOverTwoRelation semHardOverTwoRelation = new SemanticOverTwoRelation(
				semGeneralElement, "OverTwoRelation", hardSemOverTwoRelList);

		InstVertex instVertexHHGR = new InstConcept("GoalOTAsso",
				metaOverTwoRelation, semHardOverTwoRelation);
		variabilityInstVertex.put("GoalOTAsso", instVertexHHGR);

		InstConcept instHchcHHGRHC = new InstConcept("GoaltoOTAssoPWAsso",
				metaPairwiseRelation);
		variabilityInstVertex.put("GoaltoOTAssoPWAsso", instHchcHHGRHC);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("hctoHHGR-HHGR-HHHHGR", instEdge);
		instEdge.setIdentifier("hctoHHGR-HHGR-HHHHGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instHchcHHGRHC, true);
		instEdge.setSourceRelation(instVertexHHGR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("hctoHHGR-HHHHGR-H", instEdge);
		instEdge.setIdentifier("hctoHHGR-HHHHGR-H");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instHchcHHGRHC, true);

		InstConcept instHchcHHGRGR = new InstConcept("GoalfromOTAssoPWAsso",
				metaPairwiseRelation);
		variabilityInstVertex.put("GoalfromOTAssoPWAsso", instHchcHHGRGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("HHGRtohc-H-HHHHGR", instEdge);
		instEdge.setIdentifier("HHGRtohc-H-HHHHGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instHchcHHGRGR, true);
		instEdge.setSourceRelation(instVertexHC, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("HHGRtohc-HHHHGR-H", instEdge);
		instEdge.setIdentifier("HHGRtohc-H-HHHHGR-H");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexHHGR, true);
		instEdge.setSourceRelation(instHchcHHGRGR, true);

		// List<AbstractSemanticVertex> semanticVertices = new
		// ArrayList<AbstractSemanticVertex>();

		List<IntSemanticRelationType> structHardSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		structHardSemPairwiseRelList.add(new SemanticRelationType("means_ends",
				"means-ends", "means-ends", true, true, true, 1, -1, 1, 1));
		structHardSemPairwiseRelList
				.add(new SemanticRelationType("implication", "impl.", "Impl.",
						false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> sideHardSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		sideHardSemPairwiseRelList.add(new SemanticRelationType("conflict",
				"conflict", "conflict", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new SemanticRelationType("alternative",
				"altern.", "altern.", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new SemanticRelationType("preferred",
				"pref.", "pref.", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new SemanticRelationType("require",
				"req.", "req.", false, true, true, 1, -1, 1, 1));
		sideHardSemPairwiseRelList.add(new SemanticRelationType("condition",
				"cond.", "cond.", false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> sgPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		sgPairwiseRelList.add(new SemanticRelationType("contribution",
				"contribution", "contribution", true, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("conflict", "conflict",
				"conflict", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("alternative",
				"altern.", "altern.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("preferred", "pref.",
				"pref.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("implication", "impl.",
				"Impl.", false, true, true, 1, -1, 1, 1));
		sgPairwiseRelList.add(new SemanticRelationType("require", "req.",
				"req.", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation directHardHardSemanticEdge = new SemanticPairwiseRelation(
				"GoalGoalSidePWAsso", false, sideHardSemPairwiseRelList);
		InstConcept instDirHardHardSemanticEdge = new InstConcept(
				"GoalGoalSidePWAsso", metaPairwiseRelation,
				directHardHardSemanticEdge);

		InstAttribute ia = instDirHardHardSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		List<InstAttribute> ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("conflict", new AbstractAttribute("conflict",
				StringType.IDENTIFIER, false, "conflict", "", 1, -1, "", "",
				-1, "", ""), "conflict#conflict#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("alternative", new AbstractAttribute(
				"alternative", StringType.IDENTIFIER, false, "alternative", "",
				1, -1, "", "", -1, "", ""),
				"altern.#altern.#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("preferred", new AbstractAttribute(
				"preferred", StringType.IDENTIFIER, false, "preferred", "", 1,
				-1, "", "", -1, "", ""),
				"preferred#preferred#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("require", new AbstractAttribute("require",
				StringType.IDENTIFIER, false, "require", "", 1, -1, "", "", -1,
				"", ""), "require#require#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("condition", new AbstractAttribute(
				"condition", StringType.IDENTIFIER, false, "condition", "", 1,
				-1, "", "", -1, "", ""),
				"condition#condition#false#true#true#1#-1#1#1"));

		ia = instDirHardHardSemanticEdge
				.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Selected", "Selected");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), 1, false, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("conflict", new AbstractAttribute("conflict",
				StringType.IDENTIFIER, false, "conflict", "", 1, -1, "", "",
				-1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexHC, "Selected", true, 1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexHC, "Selected", true, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("alternative", new AbstractAttribute(
				"alternative", StringType.IDENTIFIER, false, "alternative", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Selected", "Selected");

		t3 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexHC, "Selected");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"And"), t3, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("preferred", new AbstractAttribute(
				"preferred", StringType.IDENTIFIER, false, "preferred", "", 1,
				-1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexHC, "Selected", false, 1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"GreaterOrEq"), 1, false, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("require", new AbstractAttribute("require",
				StringType.IDENTIFIER, false, "condition", "", 1, -1, "", "",
				-1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Selected", "Selected");

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("condition", new AbstractAttribute(
				"condition", StringType.IDENTIFIER, false, "condition", "", 1,
				-1, "", "", -1, "", ""), semanticExpressions));

		variabilityInstVertex.put("GoalGoalSidePWAsso",
				instDirHardHardSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("GoalGoalSidePWAsso-GR", instEdge);
		instEdge.setIdentifier("GoalGoalSidePWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirHardHardSemanticEdge, true);
		instEdge.setSourceRelation(instVertexHC, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("GoalGoalSidePW-GR-Asso", instEdge);
		instEdge.setIdentifier("GoalGoalSidePW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instDirHardHardSemanticEdge, true);

		SemanticPairwiseRelation directStructHardHardSemanticEdge = new SemanticPairwiseRelation(
				"structHardHardPWAsso", false, structHardSemPairwiseRelList);

		InstConcept instDirStructHardHardSemanticEdge = new InstConcept(
				"structHardHardPWAsso", metaPairwiseRelation,
				directStructHardHardSemanticEdge);

		ia = instDirStructHardHardSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("means_ends", new AbstractAttribute(
				"means_ends", StringType.IDENTIFIER, false, "means_ends", "",
				1, -1, "", "", -1, "", ""),
				"means_ends#means-ends#true#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("implication", new AbstractAttribute(
				"implication", StringType.IDENTIFIER, false, "implication", "",
				1, -1, "", "", -1, "", ""),
				"implication#implication#false#true#true#1#-1#1#1"));

		ia = instDirStructHardHardSemanticEdge
				.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "Selected", "Selected");

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexHC,
				instVertexHC, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("means_ends", new AbstractAttribute(
				"means_ends", StringType.IDENTIFIER, false, "means_ends", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexHC, "Selected", true, 1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexHC, "Selected", true, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("implication", new AbstractAttribute(
				"implication", StringType.IDENTIFIER, false, "implication", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		variabilityInstVertex.put("structHardHardPWAsso",
				instDirStructHardHardSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("structHardHardPWAsso-GR", instEdge);
		instEdge.setIdentifier("structHardHardPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirStructHardHardSemanticEdge, true);
		instEdge.setSourceRelation(instVertexHC, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("structHardHardPW-GR-Asso", instEdge);
		instEdge.setIdentifier("structHardHardPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexHC, true);
		instEdge.setSourceRelation(instDirStructHardHardSemanticEdge, true);

		List<IntSemanticRelationType> featSideSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		featSideSemPairwiseRelList.add(new SemanticRelationType("require",
				"require", "require", false, true, true, 1, -1, 1, 1));
		featSideSemPairwiseRelList.add(new SemanticRelationType("conflict",
				"excl.", "excl.", false, true, true, 1, -1, 1, 1));

		List<IntSemanticRelationType> featVertSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		featVertSemPairwiseRelList.add(new SemanticRelationType("mandatory",
				"mandatory", "mandatory", true, true, true, 1, -1, 1, 1));
		featVertSemPairwiseRelList.add(new SemanticRelationType("optional",
				"opt.", "opt.", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation directFeaFeatVertSemEdge = new SemanticPairwiseRelation(
				"FeatFeatParentPWAsso", false, featVertSemPairwiseRelList);
		InstConcept instDirFeaFeatVertSemEdge = new InstConcept(
				"FeatFeatParentPWAsso", metaPairwiseRelation,
				directFeaFeatVertSemEdge);

		ia = instDirFeaFeatVertSemEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("mandatory", new AbstractAttribute(
				"mandatory", StringType.IDENTIFIER, false, "mandatory", "", 1,
				-1, "", "", -1, "", ""),
				"mandatory#mandatory#true#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("optional", new AbstractAttribute("optional",
				StringType.IDENTIFIER, false, "optional", "", 1, -1, "", "",
				-1, "", ""), "optional#optional#false#true#true#1#-1#1#1"));

		ia = instDirFeaFeatVertSemEdge
				.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "Selected", "Selected");

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("mandatory", new AbstractAttribute(
				"mandatory", StringType.IDENTIFIER, false, "mandatory", "", 1,
				-1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "Selected", "Selected");

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE, instVertexF,
				instVertexF, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("optional", new AbstractAttribute("optional",
				StringType.IDENTIFIER, false, "optional", "", 1, -1, "", "",
				-1, "", ""), semanticExpressions));

		variabilityInstVertex.put("FeatFeatParentPWAsso",
				instDirFeaFeatVertSemEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("FeatFeatParentPWAsso-GR", instEdge);
		instEdge.setIdentifier("FeatFeatParentPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirFeaFeatVertSemEdge, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("FeatFeatParentPW-GR-Asso", instEdge);
		instEdge.setIdentifier("FeatFeatParentPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instDirFeaFeatVertSemEdge, true);

		SemanticPairwiseRelation directFeatFeatSideSemEdge = new SemanticPairwiseRelation(
				"FeatFeatSidePWAsso", false, featSideSemPairwiseRelList);
		InstConcept instDirFeatFeatSideSemEdge = new InstConcept(
				"FeatFeatSidePWAsso", metaPairwiseRelation,
				directFeatFeatSideSemEdge);

		ia = instDirFeatFeatSideSemEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("conflict", new AbstractAttribute("conflict",
				StringType.IDENTIFIER, false, "conflict", "", 1, -1, "", "",
				-1, "", ""), "conflict#conflict#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("require", new AbstractAttribute("require",
				StringType.IDENTIFIER, false, "require", "", 1, -1, "", "", -1,
				"", ""), "require#require#false#true#true#1#-1#1#1"));

		ia = instDirFeatFeatSideSemEdge
				.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "Selected", "Selected");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), 1, false, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("conflict", new AbstractAttribute("conflict",
				StringType.IDENTIFIER, false, "conflict", "", 1, -1, "", "",
				-1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexF, "Selected", false, 1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"GreaterOrEq"), 1, false, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("require", new AbstractAttribute("require",
				StringType.IDENTIFIER, false, "condition", "", 1, -1, "", "",
				-1, "", ""), semanticExpressions));

		variabilityInstVertex.put("FeatFeatSidePWAsso",
				instDirFeatFeatSideSemEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("FeatFeatSidePWAsso-GR", instEdge);
		instEdge.setIdentifier("FeatFeatSidePWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirFeatFeatSideSemEdge, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("FeatFeatSidePW-GR-Asso", instEdge);
		instEdge.setIdentifier("FeatFeatSidePW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instDirFeatFeatSideSemEdge, true);

		SemanticOverTwoRelation semFeatOverTwoRelation = new SemanticOverTwoRelation(
				semGeneralElement, "FeatFeatOTAsso", featSemOverTwoRelList);
		InstVertex instVertexFFGR = new InstConcept("FeatFeatOTAsso",
				metaOverTwoRelation, semFeatOverTwoRelation);
		variabilityInstVertex.put("FeatFeatOTAsso", instVertexFFGR);

		List<IntSemanticRelationType> assetoperPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		assetoperPairwiseRelList.add(new SemanticRelationType("implementation",
				"implementation", "imp.", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semAssetOperPairwiseRel = new SemanticPairwiseRelation(
				"varAssetOperPWAsso", false, assetoperPairwiseRelList);
		InstConcept instSemAssetOperPairwiseRel = new InstConcept(
				"varAssetOperPWAsso", metaPairwiseRelation,
				semAssetOperPairwiseRel);

		ia = instDirStructHardHardSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("implication", new AbstractAttribute(
				"implementation", StringType.IDENTIFIER, false,
				"implementation", "", 1, -1, "", "", -1, "", ""),
				"implementation#implementation#false#true#true#1#-1#1#1"));

		ia = instDirStructHardHardSemanticEdge
				.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexHC, "Selected", "Selected");

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexHC, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("implementation", new AbstractAttribute(
				"implementation", StringType.IDENTIFIER, false,
				"implementation", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		variabilityInstVertex.put("varAssetOperPWAsso",
				instSemAssetOperPairwiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("varAssetOperPWAsso-GR", instEdge);
		instEdge.setIdentifier("varAssetOperPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSemAssetOperPairwiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("varAssetOperPW-GR-Asso", instEdge);
		instEdge.setIdentifier("varAssetOperPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instSemAssetOperPairwiseRel, true);

		List<IntSemanticRelationType> assetPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		assetPairwiseRelList.add(new SemanticRelationType("delegation",
				"delegation", "deleg.", true, true, true, 1, 1, 1, 1));
		assetPairwiseRelList.add(new SemanticRelationType("assembly",
				"assembly", "assembly", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semAssetPairwiseRel = new SemanticPairwiseRelation(
				"varAssetPWAsso", false, assetPairwiseRelList);
		InstConcept instSemAssetPairwiseRel = new InstConcept("varAssetPWAsso",
				metaPairwiseRelation, semAssetPairwiseRel);

		ia = instSemAssetPairwiseRel
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("mandatory", new AbstractAttribute(
				"mandatory", StringType.IDENTIFIER, false, "mandatory", "", 1,
				-1, "", "", -1, "", ""),
				"mandatory#mandatory#true#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("optional", new AbstractAttribute("optional",
				StringType.IDENTIFIER, false, "optional", "", 1, -1, "", "",
				-1, "", ""), "optional#optional#false#true#true#1#-1#1#1"));

		ia = instSemAssetPairwiseRel
				.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexAsset, "Selected", "Selected");

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexAsset, "NotAvailable",
				"NotAvailable");

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("delegation", new AbstractAttribute(
				"delegation", StringType.IDENTIFIER, false, "delegation", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();
		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexAsset, "Selected", "Selected");

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexF,
				instVertexF, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("assembly", new AbstractAttribute("assembly",
				StringType.IDENTIFIER, false, "assembly", "", 1, -1, "", "",
				-1, "", ""), semanticExpressions));

		variabilityInstVertex.put("varAssetPWAsso", instSemAssetPairwiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("varAssetPWAsso-GR", instEdge);
		instEdge.setIdentifier("varAssetPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSemAssetPairwiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("varAssetPW-GR-Asso", instEdge);
		instEdge.setIdentifier("varAssetPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instSemAssetPairwiseRel, true);

		List<IntSemanticRelationType> vcntxPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		vcntxPairwiseRelList.add(new SemanticRelationType("Variable Context",
				"", "", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semvarcntxPairwiseRel = new SemanticPairwiseRelation(
				"varcntxPWAsso", false, vcntxPairwiseRelList);

		InstConcept instSemvarcntxPairwiseRel = new InstConcept(
				"varcntxPWAsso", metaPairwiseRelation, semvarcntxPairwiseRel);

		ia = instSemvarcntxPairwiseRel
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();

		ias.add(new InstAttribute("Variable Context", new AbstractAttribute(
				"Variable Context", StringType.IDENTIFIER, false, "", "", 1,
				-1, "", "", -1, "", ""),
				"Variable Context#Variable Context#false#true#true#1#-1#1#1"));

		ia = instSemvarcntxPairwiseRel
				.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		ias.add(new InstAttribute("Variable Context", new AbstractAttribute(
				"Variable Context", StringType.IDENTIFIER, false,
				"Variable Context", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		variabilityInstVertex.put("varcntxPWAsso", instSemvarcntxPairwiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("varcntxPWAsso-GR", instEdge);
		instEdge.setIdentifier("varcntxPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSemvarcntxPairwiseRel, true);
		instEdge.setSourceRelation(instVertexVAR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("varcntxPW-GR-Asso", instEdge);
		instEdge.setIdentifier("varcntxPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instSemvarcntxPairwiseRel, true);

		List<IntSemanticRelationType> sdPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		sdPairwiseRelList.add(new SemanticRelationType("SD", "", "", true,
				true, true, 1, 1, 1, 1));

		/*
		 * SemanticPairwiseRelation semSDPairwiseRel = new
		 * SemanticPairwiseRelation( "sdPWAsso", false, sdPairwiseRelList);
		 * InstConcept instSemSDPairwiseRel = new InstConcept("sdPWAsso",
		 * metaPairwiseRelation, semSDPairwiseRel);
		 * variabilityInstVertex.put("sdPWAsso", instSemSDPairwiseRel);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("sdPWAsso-GR", instEdge);
		 * instEdge.setIdentifier("sdPWAsso-GR");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		 * instEdge.setTargetRelation(instSemSDPairwiseRel, true);
		 * instEdge.setSourceRelation(instVertexSD, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("sdPW-GR-Asso", instEdge);
		 * instEdge.setIdentifier("sdPW-GR-Asso");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		 * instEdge.setTargetRelation(instVertexSG, true);
		 * instEdge.setSourceRelation(instSemSDPairwiseRel, true);
		 */
		List<IntSemanticRelationType> operclaimPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		operclaimPairwiseRelList.add(new SemanticRelationType("OperToClaim",
				"", "", true, true, true, 1, 1, 1, 1));

		SemanticPairwiseRelation semOperClaimPairwiseRel = new SemanticPairwiseRelation(
				"operclaimPWAsso", false, operclaimPairwiseRelList);

		InstConcept instSemCLPWAsso = new InstConcept("operclaimPWAsso",
				metaPairwiseRelation, semOperClaimPairwiseRel);

		// variabilityInstVertex.put("operclaimPWAsso", instSemCLPWAsso);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("operclaimPWAsso-GR", instEdge);
		instEdge.setIdentifier("operclaimPWAsso-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSemCLPWAsso, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("operclaimPW-GR-Asso", instEdge);
		instEdge.setIdentifier("operclaimPW-GR-Asso");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instSemCLPWAsso, true);

		List<IntSemanticRelationType> claimSGPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		claimSGPairwiseRelList.add(new SemanticRelationType("ClaimToSG", "",
				"", true, true, true, 1, 1, 1, 1));

		/*
		 * SemanticPairwiseRelation semClaimSGPairwiseRel = new
		 * SemanticPairwiseRelation( "claimSGPWAsso", false,
		 * claimSGPairwiseRelList); InstConcept instSemCLSGPWAsso = new
		 * InstConcept("claimSGPWAsso", metaPairwiseRelation,
		 * semClaimSGPairwiseRel); variabilityInstVertex.put("claimSGPWAsso",
		 * instSemCLSGPWAsso);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("CLSGPWAsso-GR", instEdge);
		 * instEdge.setIdentifier("CLSGPWAsso-GR");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		 * instEdge.setTargetRelation(instSemCLSGPWAsso, true);
		 * instEdge.setSourceRelation(instVertexCL, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("CLSGPW-GR-Asso", instEdge);
		 * instEdge.setIdentifier("CLSGPW-GR-Asso");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		 * instEdge.setTargetRelation(instVertexSG, true);
		 * instEdge.setSourceRelation(instSemCLSGPWAsso, true);
		 */
		List<IntSemanticRelationType> groupPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		groupPairwiseRelList.add(new SemanticRelationType("Group", "", "",
				true, true, true, 1, 1, 1, 1));
		/*
		 * SemanticPairwiseRelation semGroupPairwiseRel = new
		 * SemanticPairwiseRelation( "groupPWAsso", false,
		 * groupPairwiseRelList); InstConcept instSemGroupPWAsso = new
		 * InstConcept("groupPWAsso", metaPairwiseRelation,
		 * semGroupPairwiseRel); variabilityInstVertex.put("groupPWAsso",
		 * instSemGroupPWAsso);
		 * 
		 * semGroupPairwiseRel.putSemanticAttribute("AggregationLow1", new
		 * SemanticAttribute("AggregationLow1", "Integer", false,
		 * "Aggregation Low1", 0, 0, -1, "", "", -1, "", ""));
		 * 
		 * semGroupPairwiseRel.putSemanticAttribute("AggregationHigh1", new
		 * SemanticAttribute("AggregationHigh1", "Integer", false,
		 * "Aggregation High1", 0, 0, -1, "", "", -1, "", ""));
		 */
		List<IntSemanticRelationType> nonePairwiseRelList = new ArrayList<IntSemanticRelationType>();
		nonePairwiseRelList.add(new SemanticRelationType("Group", "", "", true,
				true, true, 1, 1, 1, 1));

		/*
		 * SemanticPairwiseRelation nonePairwiseRel = new
		 * SemanticPairwiseRelation( "NonePWAsso", false, nonePairwiseRelList);
		 * variabilityInstVertex.put("NonePWAsso", new InstConcept("NonePWAsso",
		 * metaPairwiseRelation, nonePairwiseRel));
		 * 
		 * SemanticPairwiseRelation extendsPairwiseRel = new
		 * SemanticPairwiseRelation( "extendsPWAsso", false,
		 * nonePairwiseRelList); variabilityInstVertex.put("extendsPWAsso", new
		 * InstConcept( "extendsPWAsso", metaPairwiseRelation,
		 * extendsPairwiseRel));
		 * 
		 * SemanticPairwiseRelation viewPairwiseRel = new
		 * SemanticPairwiseRelation( "viewPWAsso", false, nonePairwiseRelList);
		 * variabilityInstVertex.put("viewPWAsso", new InstConcept("viewPWAsso",
		 * metaPairwiseRelation, viewPairwiseRel));
		 */
		List<IntSemanticRelationType> genconsPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		genconsPairwiseRelList.add(new SemanticRelationType(
				"GeneralConstraint", "", "", true, true, true, 1, 1, 1, 1));

		// Feature to Feature

		InstConcept instFeatFeatFFFGR = new InstConcept(
				"FeatFeatToOTAssoPWAsso", metaPairwiseRelation);
		variabilityInstVertex.put("FeatFeatToOTAssoPWAsso", instFeatFeatFFFGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("featfeatF-FFFGR", instEdge);
		instEdge.setIdentifier("featfeatF-FFFGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instFeatFeatFFFGR, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("featfeatFFFGR-FFGR", instEdge);
		instEdge.setIdentifier("featfeatFFFGR-FFGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexFFGR, true);
		instEdge.setSourceRelation(instFeatFeatFFFGR, true);

		InstConcept instFeatFeatFGRF = new InstConcept(
				"FeatFeatFromOTAssoPWAsso", metaPairwiseRelation);
		variabilityInstVertex.put("FeatFeatFromOTAssoPWAsso", instFeatFeatFGRF);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("featfeatFFFGR-F", instEdge);
		instEdge.setIdentifier("featfeatFFFGR-F");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instFeatFeatFGRF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("featfeatFFGR-FFFGR", instEdge);
		instEdge.setIdentifier("featfeatFFGR-FFFGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instFeatFeatFGRF, true);
		instEdge.setSourceRelation(instVertexFFGR, true);

		// Goal to Goal

		// SemanticPairwiseRelation directGoalGoalSemanticEdge = new
		// SemanticPairwiseRelation(
		// "GoalGoalPWAsso", false, hardSemPairwiseRelList);
		/*
		 * variabilityInstVertex.put("GoalGoalOTAsso", new InstConcept(
		 * "GoalGoalOTAsso", metaOverTwoRelation,
		 * semanticGoalGoalGroupRelation));
		 */
		// constraintInstEdges.put("GoalGoalPWAsso", new
		// InstPairwiseRelation(
		// directGoalGoalSemanticEdge));

		// Oper to Goal and Oper

		// SemanticPairwiseRelation directOperGoalSemanticEdge = new
		// SemanticPairwiseRelation(
		// "OperGoalPWAsso", false, hardSemPairwiseRelList);
		// InstConcept instOperGoal = new InstConcept("OperGoalPWAsso",
		// metaConcept, directOperGoalSemanticEdge);
		// variabilityInstVertex.put("OperGoalPWAsso", instOperGoal);

		// instEdge = new InstPairwiseRelation(directOperGoalSemanticEdge);
		// instEdge.setSourceRelation(instVertexOper, true);
		// instEdge.setTargetRelation(instOperGoal, true);
		// constraintInstEdges.put("OperGoalPWAsso", instEdge);

		// Oper to Oper

		// SemanticPairwiseRelation directOperOperSemanticEdge = new
		// SemanticPairwiseRelation(
		// "OperOperPWAsso", false, hardSemPairwiseRelList);
		/*
		 * variabilityInstVertex.put("OperOperOTAsso", new InstConcept(
		 * "OperOperOTAsso", metaOverTwoRelation,
		 * semanticOperOperGroupRelation));
		 */
		// constraintInstEdges.put("OperOperPWAsso",
		// new InstPairwiseRelation(directOperOperSemanticEdge));

		// SG to SG

		SemanticOverTwoRelation semanticSGSGGroupRelation = new SemanticOverTwoRelation(
				semGeneralElement, "SgSgOTAsso", hardSemOverTwoRelList);

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semSoftgoal);

		SemanticPairwiseRelation directSGSGSemEdge = new SemanticPairwiseRelation(
				"SgSgPWAsso", true, sgPairwiseRelList);
		directSGSGSemEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_SOURCE_LEVEL,
				new SemanticAttribute(
						SemanticPairwiseRelation.VAR_SOURCE_LEVEL, "Integer",
						false, SemanticPairwiseRelation.VAR_SOURCE_LEVELNAME,
						0, new RangeDomain(0, 5), 0, -1, "", "", -1, "", ""));
		directSGSGSemEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_TARGET_LEVEL,
				new SemanticAttribute(
						SemanticPairwiseRelation.VAR_TARGET_LEVEL, "Integer",
						false, SemanticPairwiseRelation.VAR_TARGET_LEVELNAME,
						0, new RangeDomain(0, 5), 0, -1, "", "", -1, "", ""));
		directSGSGSemEdge.putSemanticAttribute("AggregationLow",
				new SemanticAttribute("AggregationLow", "Integer", false,
						"Aggregation Low", 0, 0, -1, "", "", -1, "", ""));

		directSGSGSemEdge.addPanelVisibleAttribute("07#" + "AggregationLow");

		directSGSGSemEdge.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");

		directSGSGSemEdge.addPropEditableAttribute("07#" + "AggregationLow");

		directSGSGSemEdge.addPropVisibleAttribute("07#" + "AggregationLow");

		directSGSGSemEdge.putSemanticAttribute("AggregationHigh",
				new SemanticAttribute("AggregationHigh", "Integer", false,
						"AggregationHigh", 0, 0, -1, "", "", -1, "", ""));

		directSGSGSemEdge.addPanelVisibleAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");

		directSGSGSemEdge.addPropEditableAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPropVisibleAttribute("08#" + "AggregationHigh");

		directSGSGSemEdge.addPropEditableAttribute("08#"
				+ SemanticPairwiseRelation.VAR_SOURCE_LEVEL);
		directSGSGSemEdge.addPropVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_SOURCE_LEVEL);
		directSGSGSemEdge.addPanelVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_SOURCE_LEVEL);

		directSGSGSemEdge.addPanelSpacersAttribute(":#"
				+ SemanticPairwiseRelation.VAR_TARGET_LEVEL + "#");
		directSGSGSemEdge.addPropEditableAttribute("09#"
				+ SemanticPairwiseRelation.VAR_TARGET_LEVEL);
		directSGSGSemEdge.addPropVisibleAttribute("09#"
				+ SemanticPairwiseRelation.VAR_TARGET_LEVEL);
		directSGSGSemEdge.addPanelVisibleAttribute("09#"
				+ SemanticPairwiseRelation.VAR_TARGET_LEVEL);

		InstConcept instDirSGSGSemanticEdge = new InstConcept("SgSgPWAsso",
				metaPairwiseRelation, directSGSGSemEdge);

		ia = instDirSGSGSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("contribution", new AbstractAttribute(
				"contribution", StringType.IDENTIFIER, false, "contribution",
				"", 1, -1, "", "", -1, "", ""),
				"contribution#contribution#true#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("conflict", new AbstractAttribute("conflict",
				StringType.IDENTIFIER, false, "conflict", "", 1, -1, "", "",
				-1, "", ""), "conflict#conflict#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("alternative", new AbstractAttribute(
				"alternative", StringType.IDENTIFIER, false, "alternative", "",
				1, -1, "", "", -1, "", ""),
				"altern.#altern.#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("preferred", new AbstractAttribute(
				"preferred", StringType.IDENTIFIER, false, "preferred", "", 1,
				-1, "", "", -1, "", ""),
				"preferred#preferred#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("require", new AbstractAttribute("require",
				StringType.IDENTIFIER, false, "require", "", 1, -1, "", "", -1,
				"", ""), "require#require#false#true#true#1#-1#1#1"));

		ias.add(new InstAttribute("implication", new AbstractAttribute(
				"implication", StringType.IDENTIFIER, false, "implication", "",
				1, -1, "", "", -1, "", ""),
				"implication#implication#false#true#true#1#-1#1#1"));

		ia = instDirSGSGSemanticEdge
				.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHT, instVertexSG,
				instDirSGSGSemanticEdge, "SDReqLevel", "sourceLevel");

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHT, instVertexSG,
				instDirSGSGSemanticEdge, "SDReqLevel", "targetLevel");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t1, t3);

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSG, "satisficingType", "low");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHT, instVertexSG,
				instDirSGSGSemanticEdge, "SDReqLevel", "sourceLevel");

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHT, instVertexSG,
				instDirSGSGSemanticEdge, "SDReqLevel", "targetLevel");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t1, t3);

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSG, "satisficingType", "high");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHT, instVertexSG,
				instDirSGSGSemanticEdge, "SDReqLevel", "sourceLevel");

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHT, instVertexSG,
				instDirSGSGSemanticEdge, "SDReqLevel", "targetLevel");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t1, t3);

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSG, "satisficingType", "close");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("contribution", new AbstractAttribute(
				"contribution", StringType.IDENTIFIER, false, "contribution",
				"", 1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Sum"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexSG,
				instVertexSG, "Selected", "Selected");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), 1, false, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("conflict", new AbstractAttribute("conflict",
				StringType.IDENTIFIER, false, "conflict", "", 1, -1, "", "",
				-1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexSG, "Selected", true, 1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSG, "Selected", true, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("alternative", new AbstractAttribute(
				"alternative", StringType.IDENTIFIER, false, "alternative", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexSG,
				instVertexSG, "Selected", "Selected");

		t3 = new SemanticExpression("3", this.getSemanticExpressionTypes().get(
				"Negation"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSG, "Selected");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"And"), t3, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("preferred", new AbstractAttribute(
				"preferred", StringType.IDENTIFIER, false, "preferred", "", 1,
				-1, "", "", -1, "", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Subtraction"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexSG, "Selected", false, 1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"GreaterOrEq"), 1, false, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("require", new AbstractAttribute("require",
				StringType.IDENTIFIER, false, "require", "", 1, -1, "", "", -1,
				"", ""), semanticExpressions));

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexSG, "Selected", true, 1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSG, "Selected", true, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("implication", new AbstractAttribute(
				"implication", StringType.IDENTIFIER, false, "implication", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		variabilityInstVertex.put("SgSgPWAsso", instDirSGSGSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgsgPW-sgpwsg", instEdge);
		instEdge.setIdentifier("sgsgSGR-SGsgsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirSGSGSemanticEdge, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgsgPW-sgsgpw", instEdge);
		instEdge.setIdentifier("sgsgPW-sgsgpw");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instDirSGSGSemanticEdge, true);

		InstVertex instVertexSGGR = new InstConcept("SgSgOTAsso",
				metaOverTwoRelation, semanticSGSGGroupRelation);
		variabilityInstVertex.put("SgSgOTAsso", instVertexSGGR);

		InstConcept instSgsgSGR = new InstConcept("sgsgOTAssoFromPWAsso",
				metaPairwiseRelation);
		variabilityInstVertex.put("sgsgOTAssoFromPWAsso", instSgsgSGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgsgSGR-SGsgsg", instEdge);
		instEdge.setIdentifier("sgsgSGR-SGsgsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSgsgSGR, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgsgSGR-sgsgSG", instEdge);
		instEdge.setIdentifier("sgsgSGR-sgsgSG");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSGGR, true);
		instEdge.setSourceRelation(instSgsgSGR, true);

		InstConcept instSgsgGRSG = new InstConcept("sgsgOTAssoToPWAsso",
				metaPairwiseRelation);
		variabilityInstVertex.put("sgsgOTAssoToPWAsso", instSgsgGRSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("SGGRtosg-GRsgsgGR", instEdge);
		instEdge.setIdentifier("SGGRtosg-GRsgsgGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instSgsgGRSG, true);
		instEdge.setSourceRelation(instVertexSGGR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("SGGRtosg-SGsgsgSG", instEdge);
		instEdge.setIdentifier("SGGRtosg-SGsgsgSG");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instSgsgGRSG, true);

		// CV to CG
		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semContextGroup);

		SemanticPairwiseRelation directCVCGSemanticEdge = new SemanticPairwiseRelation(
				"CVCGPWAsso", false, vcntxPairwiseRelList);
		InstConcept instDirCVCGSemanticEdge = new InstConcept("CVCGPWAsso",
				metaPairwiseRelation, directCVCGSemanticEdge);
		variabilityInstVertex.put("CVCGPWAsso", instDirCVCGSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("CVCGPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("CVCGPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instDirCVCGSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("CVCGPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("CVCGPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirCVCGSemanticEdge, true);
		instEdge.setSourceRelation(instVertexVAR, true);

		// Oper to Claim
		SemanticOverTwoRelation semanticOperClaimGroupRelation = new SemanticOverTwoRelation(
				semGeneralElement, "OperCLOTAsso", hardSemOverTwoRelList);

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semClaim);

		InstVertex instVertexCLGR = new InstConcept("OperCLOTAsso",
				metaOverTwoRelation, semanticOperClaimGroupRelation);
		variabilityInstVertex.put("OperCLOTAsso", instVertexCLGR);

		SemanticPairwiseRelation directOperClaimToSemanticEdge = new SemanticPairwiseRelation(
				"OperClaimToPWAsso", true, operclaimPairwiseRelList);

		InstConcept instDirOperClaimToSemanticEdge = new InstConcept(
				"OperClaimToPWAsso", metaPairwiseRelation,
				directOperClaimToSemanticEdge);
		variabilityInstVertex.put("OperClaimToPWAsso",
				instDirOperClaimToSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("OperClaimToPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("OperClaimToPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirOperClaimToSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("OperClaimToPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("OperClaimToPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirOperClaimToSemanticEdge, true);
		instEdge.setSourceRelation(instVertexCLGR, true);

		SemanticPairwiseRelation directOperClaimFromSemanticEdge = new SemanticPairwiseRelation(
				"OperClaimFromPWAsso", true, nonePairwiseRelList);

		InstConcept instDirOperClaimFromSemanticEdge = new InstConcept(
				"OperClaimFromPWAsso", metaPairwiseRelation,
				directOperClaimFromSemanticEdge);
		variabilityInstVertex.put("OperClaimFromPWAsso",
				instDirOperClaimFromSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("OperClaimFromPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("OperClaimFromPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCLGR, true);
		instEdge.setSourceRelation(instDirOperClaimFromSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("OperClaimFromPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("OperClaimFromPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirOperClaimFromSemanticEdge, true);
		instEdge.setSourceRelation(instVertexOper, true);

		SemanticPairwiseRelation directOperClaimSemanticEdge = new SemanticPairwiseRelation(
				"OperClaimPWAsso", true, operclaimPairwiseRelList);

		InstConcept instDirOperClaimSemanticEdge = new InstConcept(
				"OperClaimPWAsso", metaPairwiseRelation,
				directOperClaimSemanticEdge);

		ia = instDirOperClaimSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("OperToClaim", new AbstractAttribute(
				"OperToClaim", StringType.IDENTIFIER, false, "ClaimToSG", "",
				1, -1, "", "", -1, "", ""),
				"OperToClaim#OperToClaim#true#true#true#1#-1#1#1"));

		ia = instDirOperClaimSemanticEdge
				.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"And"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexOper,
				instVertexCL, "Selected", "CompExp");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"DoubleImplies"),
				ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE, instVertexCL,
				"Selected", true, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE, instVertexOper,
				instVertexCL, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("OperToClaim", new AbstractAttribute(
				"OperToClaim", StringType.IDENTIFIER, false, "ClaimToSG", "",
				1, -1, "", "", -1, "", ""), semanticExpressions));

		variabilityInstVertex.put("OperClaimPWAsso",
				instDirOperClaimSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("OperClaimPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("OperClaimPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirOperClaimSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("OperClaimPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("OperClaimPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirOperClaimSemanticEdge, true);
		instEdge.setSourceRelation(instVertexOper, true);

		// LFeat to Claim
		SemanticOverTwoRelation semanticLFClaimGroupRelation = new SemanticOverTwoRelation(
				semGeneralElement, "LFtoClaimOTAsso", hardSemOverTwoRelList);

		InstVertex instVertexLFCLGR = new InstConcept("LFtoClaimOTAsso",
				metaOverTwoRelation, semanticLFClaimGroupRelation);
		variabilityInstVertex.put("LFtoClaimOTAsso", instVertexLFCLGR);

		SemanticPairwiseRelation directFClaimToSemanticEdge = new SemanticPairwiseRelation(
				"FClaimToPWAsso", true, operclaimPairwiseRelList);

		InstConcept instDirFClaimToSemanticEdge = new InstConcept(
				"FClaimToPWAsso", metaPairwiseRelation,
				directFClaimToSemanticEdge);
		variabilityInstVertex
				.put("FClaimToPWAsso", instDirFClaimToSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("FClaimToPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("FClaimToPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirFClaimToSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("FClaimToPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("FClaimToPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirFClaimToSemanticEdge, true);
		instEdge.setSourceRelation(instVertexLFCLGR, true);

		SemanticPairwiseRelation directFClaimFromSemanticEdge = new SemanticPairwiseRelation(
				"FClaimFromPWAsso", true, nonePairwiseRelList);

		InstConcept instDirFClaimFromSemanticEdge = new InstConcept(
				"FClaimFromPWAsso", metaPairwiseRelation,
				directFClaimFromSemanticEdge);
		variabilityInstVertex.put("FClaimFromPWAsso",
				instDirFClaimFromSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("FClaimFromPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("FClaimFromPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexLFCLGR, true);
		instEdge.setSourceRelation(instDirFClaimFromSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("FClaimFromPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("FClaimFromPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirFClaimFromSemanticEdge, true);
		instEdge.setSourceRelation(instVertexCL, true);

		SemanticPairwiseRelation directLFClaimSemanticEdge = new SemanticPairwiseRelation(
				"LFClaimPWAsso", true, operclaimPairwiseRelList);

		InstConcept instDirLFClaimSemanticEdge = new InstConcept(
				"LFClaimPWAsso", metaPairwiseRelation,
				directLFClaimSemanticEdge);
		variabilityInstVertex.put("LFClaimPWAsso", instDirLFClaimSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("LFClaimPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("LFClaimPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirLFClaimSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("LFClaimPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("LFClaimPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirLFClaimSemanticEdge, true);
		instEdge.setSourceRelation(instVertexF, true);

		// Claim to SG

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semSoftgoal);
		SemanticPairwiseRelation directClaimSGSemanticEdge = new SemanticPairwiseRelation(
				"ClaimSGPWAsso", true, claimSGPairwiseRelList);
		directClaimSGSemanticEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_LEVEL, new SemanticAttribute(
						SemanticPairwiseRelation.VAR_LEVEL, "Integer", false,
						SemanticPairwiseRelation.VAR_LEVEL, 2, new RangeDomain(
								0, 5), 0, -1, "", "", -1, "", ""));

		directClaimSGSemanticEdge.addPropEditableAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directClaimSGSemanticEdge.addPropVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directClaimSGSemanticEdge.addPanelVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		InstConcept instDirClaimSGSemanticEdge = new InstConcept(
				"ClaimSGPWAsso", metaPairwiseRelation,
				directClaimSGSemanticEdge);

		ia = instDirClaimSGSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("ClaimToSG", new AbstractAttribute(
				"ClaimToSG", StringType.IDENTIFIER, false, "ClaimToSG", "", 1,
				-1, "", "", -1, "", ""),
				"ClaimToSG#ClaimToSG#true#true#true#1#-1#1#1"));

		ia = instDirClaimSGSemanticEdge
				.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHT, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "level");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexCL, "Selected", true, t1);

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexSG, "satisficingType", "low");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHT, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "level");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexCL, "Selected", true, t1);

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexSG, "satisficingType", "high");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHT, instVertexSG,
				instDirClaimSGSemanticEdge, "ClaimExpLevel", "level");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexCL, "Selected", true, t1);

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				instVertexSG, "satisficingType", "close");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("ClaimToSG", new AbstractAttribute(
				"ClaimToSG", StringType.IDENTIFIER, false, "ClaimToSG", "", 1,
				-1, "", "", -1, "", ""), semanticExpressions));

		variabilityInstVertex.put("ClaimSGPWAsso", instDirClaimSGSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("CLSGPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("CLSGPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instDirClaimSGSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("CLSGPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("CLSGPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirClaimSGSemanticEdge, true);
		instEdge.setSourceRelation(instVertexCL, true);

		// SD to SG

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semSoftgoal);

		SemanticPairwiseRelation directSDSGSemanticEdge = new SemanticPairwiseRelation(
				"SDSGPWAsso", true, sdPairwiseRelList);
		directSDSGSemanticEdge.putSemanticAttribute(
				SemanticPairwiseRelation.VAR_LEVEL, new SemanticAttribute(
						SemanticPairwiseRelation.VAR_LEVEL, "Integer", false,
						SemanticPairwiseRelation.VAR_LEVELNAME, 0,
						new RangeDomain(0, 5), 0, -1, "", "", -1, "", ""));

		directSDSGSemanticEdge.addPropEditableAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directSDSGSemanticEdge.addPropVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		directSDSGSemanticEdge.addPanelVisibleAttribute("08#"
				+ SemanticPairwiseRelation.VAR_LEVEL);
		InstConcept instDirSDSGSemanticEdge = new InstConcept("SDSGPWAsso",
				metaPairwiseRelation, directSDSGSemanticEdge);

		ia = instDirSDSGSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("SD", new AbstractAttribute("SD",
				StringType.IDENTIFIER, false, "SD", "", 1, -1, "", "", -1, "",
				""), "SD#SD#true#true#true#1#-1#1#1"));

		ia = instDirSDSGSemanticEdge
				.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"GreaterOrEq"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHT, instVertexSG,
				instDirClaimSGSemanticEdge, "SDReqLevel", "level");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSD, "Selected", true, t1);

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSD, "satisficingType", "low");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"LessOrEquals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHT, instVertexSG,
				instDirClaimSGSemanticEdge, "SDReqLevel", "level");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSD, "Selected", true, t1);

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSD, "satisficingType", "high");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
				ExpressionVertexType.RIGHT, instVertexSG,
				instDirClaimSGSemanticEdge, "SDReqLevel", "level");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSD, "Selected", true, t1);

		t3 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				instVertexSD, "satisficingType", "close");

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Implies"), t3, t1);

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE, instVertexSD,
				instVertexSD, "Selected", "CompExp");

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("SD", new AbstractAttribute("SD",
				StringType.IDENTIFIER, false, "SD", "", 1, -1, "", "", -1, "",
				""), semanticExpressions));

		variabilityInstVertex.put("SDSGPWAsso", instDirSDSGSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("SDSGPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("SDSGPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instDirSDSGSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("SDSGPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("SDSGPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirSDSGSemanticEdge, true);
		instEdge.setSourceRelation(instVertexSD, true);

		// Asset to Asset

		SemanticOverTwoRelation semanticAssetAssetOvertwoRel = new SemanticOverTwoRelation(
				semGeneralElement, "AssetOperOTAsso", hardSemOverTwoRelList);

		InstVertex instVertexASSETGR = new InstConcept("AssetAssetOTAsso",
				metaOverTwoRelation, semanticAssetAssetOvertwoRel);
		variabilityInstVertex.put("AssetAssetOTAsso", instVertexASSETGR);

		InstConcept instAssetassetASGR = new InstConcept(
				"AssetAssetToOTAssoPWAsso", metaPairwiseRelation);
		variabilityInstVertex.put("AssetAssetToOTAssoPWAsso",
				instAssetassetASGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ASSETGRtoassetA-AGR", instEdge);
		instEdge.setIdentifier("ASSETGRtoassetA-AGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instAssetassetASGR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("ASSETGRtoassetAGR-GR", instEdge);
		instEdge.setIdentifier("ASSETGRtoassetAGR-GR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instAssetassetASGR, true);
		instEdge.setSourceRelation(instVertexASSETGR, true);

		InstConcept instAssetassetGRAS = new InstConcept(
				"AssetAssetFromOTAssoPWAsso", metaPairwiseRelation);
		variabilityInstVertex.put("AssetAssetFromOTAssoPWAsso",
				instAssetassetGRAS);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("assettoAssetGR-AGR-A", instEdge);
		instEdge.setIdentifier("assettoAssetGR-AGR-A");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instAssetassetGRAS, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("assettoAssetGR-GR-AGR", instEdge);
		instEdge.setIdentifier("assettoAssetGR-GR-AGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexASSETGR, true);
		instEdge.setSourceRelation(instAssetassetGRAS, true);

		// Asset to Oper
		// TODO use list of possible relations
		SemanticOverTwoRelation semanticAssetOperGroupRelation = new SemanticOverTwoRelation(
				semGeneralElement, "AssetOperOTAsso", hardSemOverTwoRelList);

		// semanticVertices = new ArrayList<AbstractSemanticVertex>();
		// semanticVertices.add(semOperationalization);

		InstVertex instVertexOPERGR = new InstConcept("AssetOperOTAsso",
				metaOverTwoRelation, semanticAssetOperGroupRelation);
		variabilityInstVertex.put("AssetOperOTAsso", instVertexOPERGR);

		InstConcept instAssetOperAOGR = new InstConcept(
				"AssetOperToOTAssoPWAsso", metaPairwiseRelation);
		variabilityInstVertex.put("AssetOperToOTAssoPWAsso", instAssetOperAOGR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("assettoOperGR-AAOGR", instEdge);
		instEdge.setIdentifier("assettoOperGR-AAOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instAssetOperAOGR, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("assettoOperGR-AOGRGR", instEdge);
		instEdge.setIdentifier("assettoOperGR-AOGRGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexOPERGR, true);
		instEdge.setSourceRelation(instAssetOperAOGR, true);

		SemanticPairwiseRelation groupAssetOperSemanticEdge = new SemanticPairwiseRelation(
				"AssetOperPWAsso", false, assetoperPairwiseRelList);

		InstConcept instAssetOperGRAO = new InstConcept(
				"AssetOperFromoOTAssoPWAsso", metaPairwiseRelation,
				groupAssetOperSemanticEdge);

		ia = instAssetOperGRAO.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("implementation", new AbstractAttribute(
				"implementation", StringType.IDENTIFIER, false,
				"implementation", "", 1, -1, "", "", -1, "", ""),
				"implementation#implementation#true#true#true#1#-1#1#1"));

		ia = instAssetOperGRAO.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexOper, "Selected", "Selected");

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexOper, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("implementation", new AbstractAttribute(
				"implementation", StringType.IDENTIFIER, false,
				"implementation", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		variabilityInstVertex.put("AssetOperFromOTAssoPWAsso",
				instAssetOperGRAO);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("OPERGRtooper-OOGR", instEdge);
		instEdge.setIdentifier("OPERGRtooper-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instAssetOperGRAO, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("OPERGRtooper-OGRO", instEdge);
		instEdge.setIdentifier("OPERGRtooper-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instAssetOperGRAO, true);
		instEdge.setSourceRelation(instVertexOPERGR, true);

		SemanticPairwiseRelation directAssetOperSemanticEdge = new SemanticPairwiseRelation(
				"AssetOperPWAsso", false, assetoperPairwiseRelList);
		InstConcept instDirAssetOperSemanticEdge = new InstConcept(
				"AssetOperPWAsso", metaPairwiseRelation,
				directAssetOperSemanticEdge);

		ia = instDirAssetOperSemanticEdge
				.getInstAttribute("relationTypesAttributes");
		ias = (List<InstAttribute>) ia.getValue();
		ias.add(new InstAttribute("implementation", new AbstractAttribute(
				"implementation", StringType.IDENTIFIER, false,
				"implementation", "", 1, -1, "", "", -1, "", ""),
				"implementation#implementation#true#true#true#1#-1#1#1"));

		ia = instDirAssetOperSemanticEdge
				.getInstAttribute("relationTypesSemExpressions");
		ias = (List<InstAttribute>) ia.getValue();

		semanticExpressions = new ArrayList<IntSemanticExpression>();

		t1 = new SemanticExpression("1", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexOper, "Selected", "Selected");

		semanticExpressions.add(t1);

		t1 = new SemanticExpression("2", this.getSemanticExpressionTypes().get(
				"Equals"), ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
				ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
				instVertexAsset, instVertexOper, "NotAvailable", "NotAvailable");

		semanticExpressions.add(t1);

		ias.add(new InstAttribute("implementation", new AbstractAttribute(
				"implementation", StringType.IDENTIFIER, false,
				"implementation", "", 1, -1, "", "", -1, "", ""),
				semanticExpressions));

		variabilityInstVertex.put("AssetOperPWAsso",
				instDirAssetOperSemanticEdge);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("AssetOperPWAsso-OOGR", instEdge);
		instEdge.setIdentifier("AssetOperPWAsso-OOGR");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instDirAssetOperSemanticEdge, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("AssetOperPWAsso-OGRO", instEdge);
		instEdge.setIdentifier("AssetOperPWAsso-OGRO");
		instEdge.setSupportMetaPairwiseRelation(metaPairwRelAso);
		instEdge.setTargetRelation(instDirAssetOperSemanticEdge, true);
		instEdge.setSourceRelation(instVertexAsset, true);
	}

	/**
	 * Creates the default objects of the Meta Model (Syntax concepts). They can
	 * be edited on the Syntax perspective and are the support elements for the
	 * modeling perspective displayed in the views (palettes)
	 */
	private void createDefaultSyntax() {

		MetaView syntaxMetaView = null;

		MetaConcept metaView = (MetaConcept) getSyntaxRefas().getVertex("View")
				.getEditableMetaElement();

		MetaConcept supportMetaElementConcept = (MetaConcept) getSyntaxRefas()
				.getVertex("Concept").getEditableMetaElement();
		MetaConcept supportMetaElementOverTwo = (MetaConcept) getSyntaxRefas()
				.getVertex("OverTwoRelation").getEditableMetaElement();

		MetaConcept supportMetaElementPairwise = (MetaConcept) getSyntaxRefas()
				.getVertex("PairwiseRelation").getEditableMetaElement();

		MetaConcept supportMetaExtendsPairwise = (MetaConcept) getSyntaxRefas()
				.getVertex("ExtendRelation").getEditableMetaElement();

		MetaConcept supportMetaViewPairwise = (MetaConcept) getSyntaxRefas()
				.getVertex("ViewConceptAsso").getEditableMetaElement();

		MetaPairwiseRelation metaPairwiseRelNormal = (MetaPairwiseRelation) ((InstPairwiseRelation) this
				.getSyntaxRefas().getConstraintInstEdge("NormalRelation"))
				.getEditableMetaElement();

		// *************************---------------****************************
		// *************************---------------****************************
		// Goals and Variability model

		syntaxMetaView = new MetaView("Variability", true, "Variability View",
				"plnode", "Defines a feature", 130, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Goals Palette;Feature Palette", 1, null);

		InstPairwiseRelation directExtendsSemanticEdge = getSemanticRefas()
				.getConstraintInstEdge("extendsPWAsso");

		InstPairwiseRelation directViewSemanticEdge = getSemanticRefas()
				.getConstraintInstEdge("viewPWAsso");

		MetaPairwiseRelation metaExtendsRel = new MetaPairwiseRelation(
				"ExtendsRelation", false, "ExtendsRelation", "",
				"Extends relation between two hard concepts. Extends syntatic and semantic"
						+ "attributes", 50, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directExtendsSemanticEdge);

		MetaPairwiseRelation metaViewRel = new MetaPairwiseRelation(
				"ViewRelation", false, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);

		InstConcept instViewC = new InstConcept("Variability", metaView,
				syntaxMetaView);

		variabilityInstVertex.put("Variability", instViewC);

		InstConcept semFeature = ((InstConcept) getSemanticRefas().getVertex(
				"Feature"));

		InstConcept semHardConcept = ((InstConcept) this.getSemanticRefas()
				.getVertex("HardConcept"));

		InstConcept semGoal = ((InstConcept) this.getSemanticRefas().getVertex(
				"Goal"));

		MetaConcept syntaxFeature = new MetaConcept('C', "Feature", false,
				"Feature", "plnode", "Defines a feature", 100, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semFeature, true);

		syntaxFeature.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		syntaxFeature.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxFeature.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		syntaxFeature.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxFeature.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		syntaxFeature.addModelingAttribute("name", "String", false, "Name", "",
				0, 3, "", "", 3, "", "");

		// syntaxFeature.addPanelVisibleAttribute("03#" + "name");

		syntaxFeature.addPropEditableAttribute("03#" + "name");

		syntaxFeature.addPropVisibleAttribute("03#" + "name");

		// syntaxFeature.addModelingAttribute("concern", "ConcernLevel", false,
		// "Concern Level", "", 0, -1, "", "", -1, "", "");

		InstVertex instVertexF = new InstConcept("Feature",
				supportMetaElementConcept, syntaxFeature);
		variabilityInstVertex.put("Feature", instVertexF);
		// syntaxMetaView.addConcept(syntaxFeature);

		MetaPairwiseRelation metaViewF = new MetaPairwiseRelation(
				"ViewFeatRel", true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewF.setPalette("Feature Palette");

		InstConcept instViewF = new InstConcept("ViewFeatRel",
				supportMetaViewPairwise, metaViewF);
		this.variabilityInstVertex.put("ViewFeatRel", instViewF);

		// instViewF.setInstAttribute("Palette", "Feature Palette");

		InstPairwiseRelation instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vf-tof", instEdge);
		instEdge.setIdentifier("vf-tof");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instViewF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vf-fromview", instEdge);
		instEdge.setIdentifier("vf-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewF, true);
		instEdge.setSourceRelation(instViewC, true);

		MetaConcept syntaxVariabilityArtifact = new MetaConcept('C', "VA",
				false, "VariabilityArtifact", null, "", 0, 0, null, true, null,
				3, semHardConcept, true);

		syntaxVariabilityArtifact.addModelingAttribute(
				MetaConcept.VAR_USERIDENTIFIER, new SyntaxAttribute(
						MetaConcept.VAR_USERIDENTIFIER, "String", false,
						"User Identifier", null, 0, 1, "", "", -1, "", ""));
		syntaxVariabilityArtifact.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxVariabilityArtifact.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		syntaxVariabilityArtifact.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxVariabilityArtifact.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		syntaxVariabilityArtifact.addModelingAttribute("name", "String", false,
				"Name", "", 0, -1, "", "", -1, "", "");

		// syntaxVariabilityArtifact.addPanelVisibleAttribute("03#" + "name");

		syntaxVariabilityArtifact.addPropEditableAttribute("03#" + "name");

		syntaxVariabilityArtifact.addPropVisibleAttribute("03#" + "name");

		// syntaxVariabilityArtifact.addModelingAttribute("concern",
		// "ConcernLevel", false, "Concern Level", "", 0, -1, "", "", -1,
		// "", "");

		InstVertex instVertexVA = new InstConcept("VA",
				supportMetaElementConcept, syntaxVariabilityArtifact);
		variabilityInstVertex.put("VA", instVertexVA);

		MetaConcept syntaxRootFeature = new MetaConcept('C', "RootFeature",
				true, "RootFeature", "plnode", "Defines a root feature", 100,
				50, "/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semFeature, true);

		InstVertex instVertexRF = new InstConcept("RootFeature",
				supportMetaElementConcept, syntaxRootFeature);
		variabilityInstVertex.put("RootFeature", instVertexRF);

		MetaConcept syntaxGeneralFeature = new MetaConcept('C',
				"GeneralFeature", true, "GeneralFeature", "plnode",
				"Defines a general feature", 100, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semFeature, true);

		InstVertex instVertexGF = new InstConcept("GeneralFeature",
				supportMetaElementConcept, syntaxGeneralFeature);
		variabilityInstVertex.put("GeneralFeature", instVertexGF);

		MetaConcept syntaxVertexLF = new MetaConcept('C', "LeafFeature", true,
				"LeafFeature", "plnode", "Defines a leaf feature", 100, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, semFeature, true);

		InstVertex instVertexLF = new InstConcept("LeafFeature",
				supportMetaElementConcept, syntaxVertexLF);
		variabilityInstVertex.put("LeafFeature", instVertexLF);

		MetaPairwiseRelation metaViewRF = new MetaPairwiseRelation(
				"ViewRootFeaRel", true, "ViewRelation", "",
				"View relation between a view and a concepts.", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);

		InstConcept instViewRF = new InstConcept("ViewRootFeaRel",
				supportMetaViewPairwise, metaViewRF);
		this.variabilityInstVertex.put("ViewRootFeaRel", instViewRF);

		instViewRF.setInstAttribute("Palette", "Feature Palette");

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vrf-torf", instEdge);
		instEdge.setIdentifier("vrf-torf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexRF, true);
		instEdge.setSourceRelation(instViewRF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vrf-fromview", instEdge);
		instEdge.setIdentifier("vrf-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewRF, true);
		instEdge.setSourceRelation(instViewC, true);

		MetaPairwiseRelation metaViewGF = new MetaPairwiseRelation(
				"ViewGFeatRel", true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);

		InstConcept instViewGF = new InstConcept("ViewGFeatRel",
				supportMetaViewPairwise, metaViewGF);
		this.variabilityInstVertex.put("ViewGFeatRel", instViewGF);

		instViewGF.setInstAttribute("Palette", "Feature Palette");

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vgf-togf", instEdge);
		instEdge.setIdentifier("vgf-togf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGF, true);
		instEdge.setSourceRelation(instViewGF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vgf-fromview", instEdge);
		instEdge.setIdentifier("vgf-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewGF, true);
		instEdge.setSourceRelation(instViewC, true);

		MetaConcept syntaxGoal = new MetaConcept('C', "Goal", true, "Goal",
				"refasgoal", "Defines a goal of the system"
						+ " from the stakeholder perspective that can be"
						+ " satisfied with a clear cut condition", 120, 60,
				"/com/variamos/gui/perspeditor/images/goal.png", true,
				Color.BLUE.toString(), 3, semGoal, true);

		InstVertex instVertexG = new InstConcept("Goal",
				supportMetaElementConcept, syntaxGoal);
		variabilityInstVertex.put("Goal", instVertexG);

		MetaConcept syntaxTopGoal = new MetaConcept('C', "TopGoal", false,
				"Top Goal", "refasgoal", "Defines a top goal of the system"
						+ " from the stakeholder perspective that can be"
						+ " satisfied with a clear cut condition", 120, 60,
				"/com/variamos/gui/perspeditor/images/goal.png", true,
				Color.BLUE.toString(), 3, semGoal, true);

		InstVertex instVertexTG = new InstConcept("TopGoal",
				supportMetaElementConcept, syntaxTopGoal);
		variabilityInstVertex.put("TopGoal", instVertexTG);

		MetaPairwiseRelation metaViewLF = new MetaPairwiseRelation(
				"ViewRelation", true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);

		InstConcept instViewLF = new InstConcept("View Leaf Feature Relation",
				supportMetaViewPairwise, metaViewLF);
		this.variabilityInstVertex
				.put("View Leaf Feature Relation", instViewLF);

		instViewLF.setInstAttribute("Palette", "Feature Palette");

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vlf-tolf", instEdge);
		instEdge.setIdentifier("vlf-tolf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instViewLF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vlf-fromview", instEdge);
		instEdge.setIdentifier("vlf-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewLF, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instLFExtendsPairWiseRel = new InstConcept(
				"LF Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("LF Extends Relation",
				instLFExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extlf-fext", instEdge);
		instEdge.setIdentifier("variab-extlf-fext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instLFExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extlfext-f", instEdge);
		instEdge.setIdentifier("variab-extlfext-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instLFExtendsPairWiseRel, true);

		InstConcept instGFExtendsPairWiseRel = new InstConcept(
				"GF Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("GF Extends Relation",
				instGFExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extgf-fext", instEdge);
		instEdge.setIdentifier("variab-extgf-fext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGFExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexGF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extgfext-f", instEdge);
		instEdge.setIdentifier("variab-extgfext-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instGFExtendsPairWiseRel, true);

		InstConcept instRFExtendsPairWiseRel = new InstConcept(
				"RF Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("RF Extends Relation",
				instRFExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extrf-fext", instEdge);
		instEdge.setIdentifier("variab-extrf-fext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instRFExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexRF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extrfext-f", instEdge);
		instEdge.setIdentifier("variab-extrfext-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instRFExtendsPairWiseRel, true);

		// Feature direct relations

		InstElement semGroupPaiwiseRel = getSemanticRefas().getVertex(
				"groupPWAsso");

		MetaPairwiseRelation metaGroupPairwiseRel = new MetaPairwiseRelation(
				"Group Relation", true, "Group Relation", "",
				"Direct relation with a over two relation concept."
						+ " No additional type defined", 60, 40,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				semGroupPaiwiseRel);

		// InstConcept instGroupPairWiseRel = new InstConcept("Group Relation",
		// supportMetaElementPairwise, metaGroupPairwiseRel);
		metaGroupPairwiseRel.addModelingAttribute("AggregationLow", "Integer",
				false, "Aggregation Low", 0, 0, -1, "", "", -1, "", "");
		metaGroupPairwiseRel.addPropEditableAttribute("03#" + "AggregationLow");
		metaGroupPairwiseRel.addPropVisibleAttribute("03#" + "AggregationLow");
		metaGroupPairwiseRel.addPanelVisibleAttribute("03#" + "AggregationLow"
				+ "#" + "AggregationHigh" + "#!=#" + "0");
		metaGroupPairwiseRel.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");

		metaGroupPairwiseRel.addModelingAttribute("AggregationHigh", "Integer",
				false, "Aggregation High", 0, 0, -1, "", "", -1, "", "");
		metaGroupPairwiseRel
				.addPropEditableAttribute("04#" + "AggregationHigh");
		metaGroupPairwiseRel.addPropVisibleAttribute("04#" + "AggregationHigh");
		metaGroupPairwiseRel.addPanelVisibleAttribute("04#" + "AggregationHigh"
				+ "#" + "AggregationHigh" + "#!=#" + "0");
		metaGroupPairwiseRel.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");

		// instGroupPairWiseRel.setInstAttribute("Type", "Default");
		// this.variabilityInstVertex.put("Group Relation",
		// instGroupPairWiseRel);

		InstElement directFeatFeatVertSemEdge = getSemanticRefas().getVertex(
				"FeatFeatParentPWAsso");

		InstElement directFeatFeatSideSemEdge = getSemanticRefas().getVertex(
				"FeatFeatSidePWAsso");

		MetaPairwiseRelation metaFeatVertPairwiseRel = new MetaPairwiseRelation(
				"Feature Child Relation", true, "Feature Child Relation", "",
				"Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				directFeatFeatVertSemEdge);

		// InstConcept instFeatVertPairWiseRel = new InstConcept(
		// "Feature Child Relation", supportMetaElementPairwise,
		// metaFeatVertPairwiseRel);
		// this.variabilityInstVertex.put("Feature Child Relation",
		// instFeatVertPairWiseRel);

		MetaPairwiseRelation metaFeatSidePairwiseRel = new MetaPairwiseRelation(
				"DirSideRelation", true, "Feature Side Relation", "",
				"Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 70, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				directFeatFeatSideSemEdge);

		// InstConcept instFeatSidePairWiseRel = new InstConcept(
		// "Feature Side Relation", supportMetaElementPairwise,
		// metaFeatSidePairwiseRel);
		// this.variabilityInstVertex.put("Feature Side Relation",
		// instFeatSidePairWiseRel);

		InstConcept instDirSideRelation = new InstConcept("DirSideRelation",
				supportMetaElementPairwise, metaFeatSidePairwiseRel);
		instDirSideRelation.setInstAttribute("Type", "DirSideRelation");
		instDirSideRelation.getInstAttribute("SourceCardinality").setValue(
				"[0..1]");
		instDirSideRelation.getInstAttribute("TargetCardinality").setValue(
				"[0..1]");
		this.variabilityInstVertex.put("DirSideRelation", instDirSideRelation);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-fPRst-pwrf", instEdge);
		instEdge.setIdentifier("variab-fPRstpwrf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirSideRelation, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrf-fPRst", instEdge);
		instEdge.setIdentifier("variab-pwrf-fPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instDirSideRelation, true);

		InstConcept instDirStructureRFRelation = new InstConcept(
				"DirStructureRFRelation", supportMetaElementPairwise,
				metaFeatVertPairwiseRel);
		instDirStructureRFRelation.setInstAttribute("Type", "Structure");
		instDirStructureRFRelation.getInstAttribute("SourceCardinality")
				.setValue("[0..1]");
		instDirStructureRFRelation.getInstAttribute("TargetCardinality")
				.setValue("[0..1]");
		this.variabilityInstVertex.put("DirStructureRFRelation",
				instDirStructureRFRelation);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gfPRst-pwrrf", instEdge);
		instEdge.setIdentifier("variab-gfPRst-pwrrf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirStructureRFRelation, true);
		instEdge.setSourceRelation(instVertexGF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrgf-rfPRst", instEdge);
		instEdge.setIdentifier("variab-pwrgf-rfPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexRF, true);
		instEdge.setSourceRelation(instDirStructureRFRelation, true);

		InstConcept instDirStructureLFRFRelation = new InstConcept(
				"DirStructureLFRFRelation", supportMetaElementPairwise,
				metaFeatVertPairwiseRel);
		instDirStructureLFRFRelation.setInstAttribute("Type", "Structure");
		instDirStructureLFRFRelation.getInstAttribute("SourceCardinality")
				.setValue("[0..1]");
		instDirStructureLFRFRelation.getInstAttribute("TargetCardinality")
				.setValue("[0..1]");
		this.variabilityInstVertex.put("DirStructureLFRFRelation",
				instDirStructureLFRFRelation);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-lfPRst-pwrlrf", instEdge);
		instEdge.setIdentifier("variab-lfPRstpwrlrf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirStructureLFRFRelation, true);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrlrf-rfPRst", instEdge);
		instEdge.setIdentifier("variab-pwrlrf-rfPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexRF, true);
		instEdge.setSourceRelation(instDirStructureLFRFRelation, true);

		InstConcept instDirStructureLFGRRelation = new InstConcept(
				"DirStructureLFGRRelation", supportMetaElementPairwise,
				metaFeatVertPairwiseRel);
		instDirStructureLFGRRelation.setInstAttribute("Type", "Structure");
		instDirStructureLFGRRelation.getInstAttribute("SourceCardinality")
				.setValue("[0..1]");
		instDirStructureLFGRRelation.getInstAttribute("TargetCardinality")
				.setValue("[0..1]");
		this.variabilityInstVertex.put("DirStructureLFGRRelation",
				instDirStructureLFGRRelation);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-lfPRst-pwrlgf", instEdge);
		instEdge.setIdentifier("variab-lfPRstpwrlgf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirStructureLFGRRelation, true);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrlgf-gfPRst", instEdge);
		instEdge.setIdentifier("variab-pwrlgf-gfPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGF, true);
		instEdge.setSourceRelation(instDirStructureLFGRRelation, true);

		InstConcept instDirStructureGFRelation = new InstConcept(
				"DirStructureGFRelation", supportMetaElementPairwise,
				metaFeatVertPairwiseRel);
		instDirStructureGFRelation.setInstAttribute("Type", "Structure");
		instDirStructureGFRelation.getInstAttribute("SourceCardinality")
				.setValue("[0..1]");
		instDirStructureGFRelation.getInstAttribute("TargetCardinality")
				.setValue("[0..1]");
		this.variabilityInstVertex.put("DirStructureGFRelation",
				instDirStructureGFRelation);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-gfPRst-pwrgf", instEdge);
		instEdge.setIdentifier("variab-gfPRstpwrgf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirStructureGFRelation, true);
		instEdge.setSourceRelation(instVertexGF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrgf-gfPRst", instEdge);
		instEdge.setIdentifier("variab-pwrgf-gfPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGF, true);
		instEdge.setSourceRelation(instDirStructureGFRelation, true);

		// Features OverTwoRelations

		InstConcept semanticFeatFeatGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("FeatFeatOTAsso"));

		MetaOverTwoRelation featureMetaOverTwoRel = new MetaOverTwoRelation(
				"FeatOTAsso", true, "FeatOTAsso", "plgroup",
				"Group relation between"
						+ " Feature concepts. Defines different types of"
						+ " cardinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticFeatFeatGroupRelation, false);

		InstVertex instVertexFOTR = new InstConcept("FeatOTAsso",
				supportMetaElementOverTwo, featureMetaOverTwoRel);
		variabilityInstVertex.put("FeatOTAsso", instVertexFOTR);

		MetaPairwiseRelation metaViewFG = new MetaPairwiseRelation(
				"ViewRelation", true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);

		InstConcept instViewFG = new InstConcept("View Feature OT Relation",
				supportMetaViewPairwise, metaViewFG);
		this.variabilityInstVertex.put("View Feature OT Relation", instViewFG);

		instViewFG.setInstAttribute("Palette", "Feature Palette");

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vfg-tofg", instEdge);
		instEdge.setIdentifier("vfg-tofg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexFOTR, true);
		instEdge.setSourceRelation(instViewFG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vfg-fromview", instEdge);
		instEdge.setIdentifier("vfg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewFG, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instGrpVertRelation = new InstConcept("GrpVertRelation",
				supportMetaElementPairwise, metaGroupPairwiseRel);

		instGrpVertRelation.setInstAttribute("Type", "Group");
		instGrpVertRelation.setInstAttribute("SourceCardinality", "[1..1]");
		instGrpVertRelation.setInstAttribute("TargetCardinality", "[0..1]");
		this.variabilityInstVertex.put("GrpVertRelation", instGrpVertRelation);

		InstConcept instGrpStrucRelation = new InstConcept("GrpStrucRelation",
				supportMetaElementPairwise, metaFeatVertPairwiseRel);

		instGrpStrucRelation.setInstAttribute("Type", "Structural");
		instGrpStrucRelation.setInstAttribute("SourceCardinality", "[1..1]");
		instGrpStrucRelation.setInstAttribute("TargetCardinality", "[0..1]");
		this.variabilityInstVertex
				.put("GrpStrucRelation", instGrpStrucRelation);

		InstConcept instGrpSideFeatPairWiseRel = new InstConcept(
				"GrpSideFeatRelation", supportMetaElementPairwise,
				metaFeatSidePairwiseRel);
		instGrpSideFeatPairWiseRel.setInstAttribute("Type", "SideRelations");
		instGrpSideFeatPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpSideFeatPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("GrpSideFeatRelation",
				instGrpSideFeatPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("strvariab-f-pwrme", instEdge);
		instEdge.setIdentifier("strvariab-f-pwrme");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpVertRelation, true);
		instEdge.setSourceRelation(instVertexF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("strfeatf-pwrme-va", instEdge);
		instEdge.setIdentifier("strfeatf-pwrme-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexFOTR, true);
		instEdge.setSourceRelation(instGrpVertRelation, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("strvariab-otr-fpwrme", instEdge);
		instEdge.setIdentifier("strvariab-otr-fpwrme");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpStrucRelation, true);
		instEdge.setSourceRelation(instVertexFOTR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("strfeat-pwrme-va", instEdge);
		instEdge.setIdentifier("strfeat-pwrme-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instGrpStrucRelation, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sidefeat-otr-pwrme", instEdge);
		instEdge.setIdentifier("sidefeat-otr-pwrme");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpSideFeatPairWiseRel, true);
		instEdge.setSourceRelation(instVertexFOTR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sidefeat-pwrme-f", instEdge);
		instEdge.setIdentifier("sidefeat-pwrme-f");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexF, true);
		instEdge.setSourceRelation(instGrpSideFeatPairWiseRel, true);

		InstConcept instViewVA = new InstConcept("View VA Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View VA Relation", instViewVA);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vva-tova", instEdge);
		instEdge.setIdentifier("vva-tova");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instViewVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vva-fromview", instEdge);
		instEdge.setIdentifier("vva-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewVA, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instGExtendsPairWiseRel = new InstConcept(
				"G Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("G Extends Relation",
				instGExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvatgext", instEdge);
		instEdge.setIdentifier("variab-extvatgext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instGExtendsPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvatextg", instEdge);
		instEdge.setIdentifier("variab-extvatextg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexG, true);

		InstConcept instTGExtendsPairWiseRel = new InstConcept(
				"TG Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("TG Extends Relation",
				instTGExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvattgext", instEdge);
		instEdge.setIdentifier("variab-extvattgext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexG, true);
		instEdge.setSourceRelation(instTGExtendsPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvatexttg", instEdge);
		instEdge.setIdentifier("variab-extvatexttg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instTGExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexTG, true);

		InstConcept instViewG = new InstConcept("View G Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View G Relation", instViewG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vg-tog", instEdge);
		instEdge.setIdentifier("vg-tog");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexG, true);
		instEdge.setSourceRelation(instViewG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vg-fromview", instEdge);
		instEdge.setIdentifier("vg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewG, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instViewTG = new InstConcept("View TG Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View TG Relation", instViewTG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vtg-totg", instEdge);
		instEdge.setIdentifier("vtg-totg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexTG, true);
		instEdge.setSourceRelation(instViewTG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vtg-fromview", instEdge);
		instEdge.setIdentifier("vtg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewTG, true);
		instEdge.setSourceRelation(instViewC, true);

		MetaConcept syntaxGeneralGoal = new MetaConcept('C', "GeneralGoal",
				false, "General Goal", "refasgoal",
				"Defines a general goal of the"
						+ " system from the stakeholder perspective that can"
						+ " be satisfied with a clear cut condition", 120, 60,
				"/com/variamos/gui/perspeditor/images/goal.png", true,
				Color.BLUE.toString(), 2, semGoal, true);

		InstVertex instVertexGG = new InstConcept("GeneralGoal",
				supportMetaElementConcept, syntaxGeneralGoal);
		variabilityInstVertex.put("GeneralGoal", instVertexGG);

		InstConcept instViewGG = new InstConcept("View GG Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View GG Relation", instViewGG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vgg-togg", instEdge);
		instEdge.setIdentifier("vgg-togg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGG, true);
		instEdge.setSourceRelation(instViewGG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vgg-fromview", instEdge);
		instEdge.setIdentifier("vgg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewGG, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instGGExtendsPairWiseRel = new InstConcept(
				"GG Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("GG Extends Relation",
				instGGExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvatggext", instEdge);
		instEdge.setIdentifier("variab-extvatggext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexG, true);
		instEdge.setSourceRelation(instGGExtendsPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvatextgg", instEdge);
		instEdge.setIdentifier("variab-extvatextgg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGGExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexGG, true);

		InstConcept semOperationalization = ((InstConcept) this
				.getSemanticRefas().getVertex("Operationalization"));
		MetaConcept sOperationalization = new MetaConcept('C', "OPER", true,
				"OPER", "refasoper", "An operationalization allows"
						+ " the partial or complete satisfaction of a goal or"
						+ " another operationalization. If"
						+ " the operationalizations defined is satisfied,"
						+ " according to the defined relation, the goal"
						+ " associated will be also satisfied", 100, 60,
				"/com/variamos/gui/perspeditor/images/operational.png", true,
				Color.BLUE.toString(), 2, semOperationalization, true);

		sOperationalization.addModelingAttribute(
				MetaConcept.VAR_USERIDENTIFIER, new SyntaxAttribute(
						MetaConcept.VAR_USERIDENTIFIER, "String", false,
						"User Identifier", null, 0, 1, "", "", -1, "", ""));
		sOperationalization.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		sOperationalization.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		sOperationalization.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		sOperationalization.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstVertex instVertexOper = new InstConcept("OPER",
				supportMetaElementConcept, sOperationalization);

		variabilityInstVertex.put("OPER", instVertexOper);

		InstConcept instOExtendsPairWiseRel = new InstConcept(
				"O Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("O Extends Relation",
				instOExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaextoper", instEdge);
		instEdge.setIdentifier("variab-extvaextoper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instOExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaoperext", instEdge);
		instEdge.setIdentifier("variab-extvaoperext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instOExtendsPairWiseRel, true);

		InstConcept instViewOper = new InstConcept("View Oper Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View Oper Relation", instViewOper);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("voper-tooper", instEdge);
		instEdge.setIdentifier("voper-tooper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instViewOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("voper-fromview", instEdge);
		instEdge.setIdentifier("voper-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewOper, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept semAssumption = ((InstConcept) this.getSemanticRefas()
				.getVertex("Assumption"));

		MetaConcept syntaxAssumption = new MetaConcept('C', "Assu", true,
				"Assumption", "refasassump", "An assumption is a"
						+ " condition that should me truth for the goal or"
						+ " operationalization to be satisfied", 100, 60,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.WHITE.toString(), 1, semAssumption, true);

		syntaxAssumption.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		syntaxAssumption.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxAssumption.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		syntaxAssumption.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxAssumption.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		InstVertex instVertexAssum = new InstConcept("Assu",
				supportMetaElementConcept, syntaxAssumption);
		variabilityInstVertex.put("Assu", instVertexAssum);

		InstConcept instViewAssum = new InstConcept("View Assum Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View Assum Relation", instViewAssum);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vassum-toassum", instEdge);
		instEdge.setIdentifier("vassum-toassum");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssum, true);
		instEdge.setSourceRelation(instViewAssum, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vassum-fromview", instEdge);
		instEdge.setIdentifier("vassum-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewAssum, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instAExtendsPairWiseRel = new InstConcept(
				"A Extends Relation", supportMetaExtendsPairwise,
				metaExtendsRel);
		this.variabilityInstVertex.put("A Extends Relation",
				instAExtendsPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaextassu", instEdge);
		instEdge.setIdentifier("variab-extvaextassu");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instAExtendsPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAssum, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-extvaassuext", instEdge);
		instEdge.setIdentifier("variab-extvaassuext");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instAExtendsPairWiseRel, true);

		// Direct Hard Relations

		InstElement directStructHardHardSemanticEdge = getSemanticRefas()
				.getVertex("structHardHardPWAsso");

		MetaPairwiseRelation metaStructHardPairwiseRel = new MetaPairwiseRelation(
				"HardRelation", true, "HardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 70, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directStructHardHardSemanticEdge);

		InstConcept instGrpMeansEndsRelation = new InstConcept(
				"GrpMeansEndsRelation", supportMetaElementPairwise,
				metaStructHardPairwiseRel);

		instGrpMeansEndsRelation.setInstAttribute("Type", "MeansEnds");
		instGrpMeansEndsRelation
				.setInstAttribute("SourceCardinality", "[1..1]");
		instGrpMeansEndsRelation
				.setInstAttribute("TargetCardinality", "[0..1]");
		this.variabilityInstVertex.put("GrpMeansEndsRelation",
				instGrpMeansEndsRelation);

		MetaPairwiseRelation metaDirStructHardPairwiseRel = new MetaPairwiseRelation(
				"HardRelation", true, "HardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 70, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directStructHardHardSemanticEdge);

		metaDirStructHardPairwiseRel.addModelingAttribute("Aggregation",
				"String", false, "Aggregation", "", 0, -1, "", "", -1, "", "");
		metaDirStructHardPairwiseRel.addPropEditableAttribute("03#"
				+ "Aggregation");
		metaDirStructHardPairwiseRel.addPropVisibleAttribute("03#"
				+ "Aggregation");
		metaDirStructHardPairwiseRel.addPanelVisibleAttribute("03#"
				+ "Aggregation");

		metaDirStructHardPairwiseRel.addModelingAttribute("AggregationLow",
				"Integer", false, "Aggregation Low", 0, 0, -1, "", "", -1, "",
				"");
		metaDirStructHardPairwiseRel.addPropEditableAttribute("03#"
				+ "AggregationLow");
		metaDirStructHardPairwiseRel.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		metaDirStructHardPairwiseRel.addPanelVisibleAttribute("03#"
				+ "AggregationLow" + "#" + "AggregationHigh" + "#!=#" + "0");
		metaDirStructHardPairwiseRel.addPanelSpacersAttribute("[#"
				+ "AggregationLow" + "#..");

		metaDirStructHardPairwiseRel.addModelingAttribute("AggregationHigh",
				"Integer", false, "Aggregation High", 0, 0, -1, "", "", -1, "",
				"");
		metaDirStructHardPairwiseRel.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		metaDirStructHardPairwiseRel.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		metaDirStructHardPairwiseRel.addPanelVisibleAttribute("04#"
				+ "AggregationHigh" + "#" + "AggregationHigh" + "#!=#" + "0");
		metaDirStructHardPairwiseRel.addPanelSpacersAttribute("#"
				+ "AggregationHigh" + "#]\n");

		// TODO create another meta element
		InstConcept instDirMeansEndsRelation = new InstConcept(
				"DirMeansEndsRelation", supportMetaElementPairwise,
				metaDirStructHardPairwiseRel);
		instDirMeansEndsRelation.setInstAttribute("Type", "MeansEnds");
		instDirMeansEndsRelation.getInstAttribute("SourceCardinality")
				.setValue("[0..1]");
		instDirMeansEndsRelation.getInstAttribute("TargetCardinality")
				.setValue("[0..1]");
		this.variabilityInstVertex.put("DirMeansEndsRelation",
				instDirMeansEndsRelation);

		InstElement directSideHardHardSemanticEdge = getSemanticRefas()
				.getVertex("GoalGoalSidePWAsso");

		MetaPairwiseRelation metaSideHardPairwiseRel = new MetaPairwiseRelation(
				"SideHardRelation", true, "SideHardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 70, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSideHardHardSemanticEdge);

		InstConcept instGrpSideHardHardPairWiseRel = new InstConcept(
				"GrpSideHardRelation", supportMetaElementPairwise,
				metaSideHardPairwiseRel);
		instGrpSideHardHardPairWiseRel
				.setInstAttribute("Type", "SideRelations");
		instGrpSideHardHardPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpSideHardHardPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("GrpSideHardRelation",
				instGrpSideHardHardPairWiseRel);

		MetaPairwiseRelation metaDirSideHardPairwiseRel = new MetaPairwiseRelation(
				"SideHardRelation", true, "SideHardRelation", "",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 70, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSideHardHardSemanticEdge);

		metaDirSideHardPairwiseRel.addModelingAttribute("AggregationLow",
				"Integer", false, "Aggregation Low", 0, 0, -1, "", "", -1, "",
				"");
		metaDirSideHardPairwiseRel.addPropEditableAttribute("03#"
				+ "AggregationLow");
		metaDirSideHardPairwiseRel.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		metaDirSideHardPairwiseRel.addPanelVisibleAttribute("03#"
				+ "AggregationLow" + "#" + "AggregationHigh" + "#!=#" + "0");
		metaDirSideHardPairwiseRel.addPanelSpacersAttribute("[#"
				+ "AggregationLow" + "#..");

		metaDirSideHardPairwiseRel.addModelingAttribute("AggregationHigh",
				"Integer", false, "Aggregation High", 0, 0, -1, "", "", -1, "",
				"");
		metaDirSideHardPairwiseRel.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		metaDirSideHardPairwiseRel.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		metaDirSideHardPairwiseRel.addPanelVisibleAttribute("04#"
				+ "AggregationHigh" + "#" + "AggregationHigh" + "#!=#" + "0");
		metaDirSideHardPairwiseRel.addPanelSpacersAttribute("#"
				+ "AggregationHigh" + "#]\n");

		// TODO create another
		InstConcept instDirSideHardHardPairWiseRel = new InstConcept(
				"DirSideHardRelation", supportMetaElementPairwise,
				metaDirSideHardPairwiseRel);
		instDirSideHardHardPairWiseRel
				.setInstAttribute("Type", "SideRelations");
		instDirSideHardHardPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instDirSideHardHardPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("DirSideHardRelation",
				instDirSideHardHardPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-vaPRst-pwr", instEdge);
		instEdge.setIdentifier("variab-vaPRstpwr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirMeansEndsRelation, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwr-vaPRst", instEdge);
		instEdge.setIdentifier("variab-pwr-vaPRst");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instDirMeansEndsRelation, true);

		// Hard OverTwoRelations

		InstConcept semanticHardHardGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("GoalOTAsso"));

		MetaOverTwoRelation hardMetaOverTwoRel = new MetaOverTwoRelation(
				"HardOverTwoRel", true, "HardOverTwoRel", "plgroup",
				"Group relation between"
						+ " hard concepts. Defines different types of"
						+ " relations and cardinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticHardHardGroupRelation, false);

		// TODO Create another group
		InstConcept instGroupHardPairWiseRel = new InstConcept(
				"Hard Group Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGroupHardPairWiseRel.setInstAttribute("Type", "Default");
		instGroupHardPairWiseRel
				.setInstAttribute("SourceCardinality", "[0..*]");
		instGroupHardPairWiseRel
				.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("Hard Group Relation",
				instGroupHardPairWiseRel);

		InstVertex instVertexHOTR = new InstConcept("HardOverTwoRel",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexHOTR.getInstAttribute("Type").setValue("Group");
		variabilityInstVertex.put("HardOverTwoRel", instVertexHOTR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("strvariab-otr-pwrme", instEdge);
		instEdge.setIdentifier("strvariab-otr-pwrme");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpMeansEndsRelation, true);
		instEdge.setSourceRelation(instVertexHOTR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("strvariab-pwrme-va", instEdge);
		instEdge.setIdentifier("strvariab-pwrme-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instGrpMeansEndsRelation, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sidevariab-otr-pwrme", instEdge);
		instEdge.setIdentifier("sidevariab-otr-pwrme");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpSideHardHardPairWiseRel, true);
		instEdge.setSourceRelation(instVertexHOTR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sidevariab-pwrme-va", instEdge);
		instEdge.setIdentifier("sidevariab-pwrme-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instGrpSideHardHardPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sidevariab-va-pwrd", instEdge);
		instEdge.setIdentifier("sidevariab-va-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirSideHardHardPairWiseRel, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sidevariab-pwrd-va", instEdge);
		instEdge.setIdentifier("sidevariab-pwrd-va");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instDirSideHardHardPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrg-otr", instEdge);
		instEdge.setIdentifier("variab-pwrg-otr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexHOTR, true);
		instEdge.setSourceRelation(instGroupHardPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-va-pwrg-otr", instEdge);
		instEdge.setIdentifier("variab-va-pwrg-otr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGroupHardPairWiseRel, true);
		instEdge.setSourceRelation(instVertexVA, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-HOTtoVAsi", instEdge);
		instEdge.setIdentifier("variab-HOTtoVAsi");
		instEdge.setEditableMetaElement(metaSideHardPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVA, true);
		instEdge.setSourceRelation(instVertexHOTR, true);

		InstConcept instViewHOTR = new InstConcept("View Hard Group Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex
				.put("View Hard Group Relation", instViewHOTR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vhotr-tohotr", instEdge);
		instEdge.setIdentifier("vhotr-tohotr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexHOTR, true);
		instEdge.setSourceRelation(instViewHOTR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vhotr-fromview", instEdge);
		instEdge.setIdentifier("vhotr-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewHOTR, true);
		instEdge.setSourceRelation(instViewC, true);

		// *************************---------------****************************
		// *************************---------------****************************
		// Softgoals model

		syntaxMetaView = new MetaView("SoftGoals", true, "Soft Goals View",
				"plnode", "Defines sofgoals", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Soft Goals Palette", 2, null);
		instViewC = new InstConcept("SoftGoals", metaView, syntaxMetaView);

		variabilityInstVertex.put("SoftGoals", instViewC);

		InstConcept semSoftgoal = ((InstConcept) this.getSemanticRefas()
				.getVertex("Softgoal"));
		MetaConcept syntaxSoftGoal = new MetaConcept(
				'C',
				"Softgoal",
				true,
				"Softgoal",
				"refassoftgoal",
				"Defines a soft goal of the"
						+ " system from the stakeholder"
						+ " perspective to represent non-functional requirements"
						+ ". Given the satisficing problem,"
						+ " it includes an scale of levels of satisfaction/denial."
						+ " The SG satisficing level can be measured globally or"
						+ " locally, for the system or for each user, depending"
						+ " on the SG", 100, 60,
				"/com/variamos/gui/perspeditor/images/softgoal.png", true,
				Color.WHITE.toString(), 3, semSoftgoal, true);

		syntaxSoftGoal.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		syntaxSoftGoal.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxSoftGoal.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		syntaxSoftGoal.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxSoftGoal.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		syntaxSoftGoal.addModelingAttribute("name", "String", false, "Name",
				"", 0, -1, "", "", -1, "", "");
		// syntaxSoftGoal.addPanelVisibleAttribute("03#" + "name");

		syntaxSoftGoal.addPropEditableAttribute("03#" + "name");
		syntaxSoftGoal.addPropVisibleAttribute("03#" + "name");

		// syntaxSoftGoal.addModelingAttribute("concern", "ConcernLevel", false,
		// "Concern Level", "", 0, -1, "", "", -1, "", "");

		InstVertex instVertexSG = new InstConcept("Softgoal",
				supportMetaElementConcept, syntaxSoftGoal);
		variabilityInstVertex.put("Softgoal", instVertexSG);

		InstConcept instViewSG = new InstConcept("View SG Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View SG Relation", instViewSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vsg-tosg", instEdge);
		instEdge.setIdentifier("vsg-tosg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instViewSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vsg-fromview", instEdge);
		instEdge.setIdentifier("vsg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewSG, true);
		instEdge.setSourceRelation(instViewC, true);

		MetaConcept syntaxTopSoftGoal = new MetaConcept(
				'C',
				"TopSoftgoal",
				false,
				"Top Softgoal",
				"refassoftgoal",
				"Defines a top goal of the"
						+ " system from the stakeholder"
						+ " perspective that can at most be satisficed without"
						+ " a clear cut verification. Given the satisficing problem,"
						+ " it includes an scale of levels of satisfaction/denial."
						+ " The SG satisficing level can be measured globally or"
						+ " locally, for the system or for each user, depending"
						+ " on the SG", 100, 60,
				"/com/variamos/gui/perspeditor/images/softgoal.png", true,
				Color.WHITE.toString(), 3, semSoftgoal, true);
		InstVertex instVertexTSG = new InstConcept("TopSoftgoal",
				supportMetaElementConcept, syntaxTopSoftGoal);
		variabilityInstVertex.put("TopSoftgoal", instVertexTSG);

		InstConcept instExtTSG = new InstConcept("TSG Ext Relation",
				supportMetaExtendsPairwise, metaViewRel);
		this.variabilityInstVertex.put("TSG Ext Relation", instExtTSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("exttsg-sggr", instEdge);
		instEdge.setIdentifier("exttsg-sggr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instExtTSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("extgrtsg-sg", instEdge);
		instEdge.setIdentifier("extgrtsg-sg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instExtTSG, true);
		instEdge.setSourceRelation(instVertexTSG, true);

		InstConcept instViewTSG = new InstConcept("View TSG Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View TSG Relation", instViewTSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vtsg-totsg", instEdge);
		instEdge.setIdentifier("vtsg-totsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexTSG, true);
		instEdge.setSourceRelation(instViewTSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vtsg-fromview", instEdge);
		instEdge.setIdentifier("vtsg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewTSG, true);
		instEdge.setSourceRelation(instViewC, true);

		MetaConcept syntaxGeneralSoftGoal = new MetaConcept(
				'C',
				"GeneralSoftgoal",
				false,
				"General Softgoal",
				"refassoftgoal",
				"Defines a general"
						+ " softgoal of the system from the stakeholder"
						+ " perspective that can at most be satisficed without"
						+ " a clear cut verification. Given the satisficing problem,"
						+ " it includes an scale of levels of satisfaction/denial."
						+ " The SG satisficing level can be measured globally or"
						+ " locally, for the system or for each user, depending"
						+ " on the SG", 100, 60,
				"/com/variamos/gui/perspeditor/images/softgoal.png", true,
				Color.WHITE.toString(), 1, semSoftgoal, true);

		InstVertex instVertexGSG = new InstConcept("GeneralSoftgoal",
				supportMetaElementConcept, syntaxGeneralSoftGoal);
		variabilityInstVertex.put("GeneralSoftgoal", instVertexGSG);

		InstConcept instExtGSG = new InstConcept("GSG Ext Relation",
				supportMetaExtendsPairwise, metaViewRel);
		this.variabilityInstVertex.put("GSG Ext Relation", instExtGSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("extgsg-sggr", instEdge);
		instEdge.setIdentifier("extgsg-sggr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instExtGSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("extgrgsg-sg", instEdge);
		instEdge.setIdentifier("extgrgsg-sg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instExtGSG, true);
		instEdge.setSourceRelation(instVertexGSG, true);

		InstConcept instViewGSG = new InstConcept("View GSG Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View GSG Relation", instViewGSG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vgsg-togsg", instEdge);
		instEdge.setIdentifier("vgsg-togsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGSG, true);
		instEdge.setSourceRelation(instViewGSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vgsg-fromview", instEdge);
		instEdge.setIdentifier("vgsg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewGSG, true);
		instEdge.setSourceRelation(instViewC, true);

		// Direct Soft relation

		InstElement directSGSGSemEdge = getSemanticRefas().getVertex(
				"SgSgPWAsso");

		MetaPairwiseRelation metaGroupSoftFromPairWiseRel = new MetaPairwiseRelation(
				"GroupSoftFromRelation", true, "Group Soft From Relation", "",
				"Direct relation between two soft concepts. Defines"
						+ " different types of relations and cardinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				null);

		MetaPairwiseRelation metaGroupSoftToPairWiseRel = new MetaPairwiseRelation(
				"GroupSoftToRelation", true, "Group Soft To Relation", "",
				"Direct relation between two soft concepts. Defines"
						+ " different types of relations and cardinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSGSGSemEdge);

		/*
		 * metaSoftPairWiseRel.addModelingAttribute("SourceLevel", "Integer",
		 * false, "Source Level", 0, 0, -1, "", "", -1, "", "");
		 * metaSoftPairWiseRel.addPropEditableAttribute("04#" + "SourceLevel");
		 * metaSoftPairWiseRel.addPropVisibleAttribute("04#" + "SourceLevel");
		 * 
		 * metaSoftPairWiseRel.addModelingAttribute("TargetLevel", "Integer",
		 * false, "Target Level", 0, 0, -1, "", "", -1, "", "");
		 * metaSoftPairWiseRel.addPropEditableAttribute("05#" + "TargetLevel");
		 * metaSoftPairWiseRel.addPropVisibleAttribute("05#" + "TargetLevel");
		 */

		MetaPairwiseRelation metaDirSoftPairWiseRel = new MetaPairwiseRelation(
				"DirSoftRelation", true, "Dir Soft Relation", "",
				"Direct relation between two soft concepts. Defines"
						+ " different types of relations and cardinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSGSGSemEdge);
		/*
		 * metaDirSoftPairWiseRel.addModelingAttribute("AggregationLow",
		 * "Integer", false, "Aggregation Low", 0, 0, -1, "", "", -1, "", "");
		 * metaDirSoftPairWiseRel.addPropEditableAttribute("03#" +
		 * "AggregationLow"); metaDirSoftPairWiseRel
		 * .addPropVisibleAttribute("03#" + "AggregationLow");
		 * metaDirSoftPairWiseRel.addPanelVisibleAttribute("03#" +
		 * "AggregationLow" + "#" + "AggregationHigh" + "#!=#" + "0");
		 * metaDirSoftPairWiseRel.addPanelSpacersAttribute("[#" +
		 * "AggregationLow" + "#..");
		 * 
		 * metaDirSoftPairWiseRel.addModelingAttribute("AggregationHigh",
		 * "Integer", false, "Aggregation High", 0, 0, -1, "", "", -1, "", "");
		 * metaDirSoftPairWiseRel.addPropEditableAttribute("04#" +
		 * "AggregationHigh");
		 * metaDirSoftPairWiseRel.addPropVisibleAttribute("04#" +
		 * "AggregationHigh");
		 * metaDirSoftPairWiseRel.addPanelVisibleAttribute("04#" +
		 * "AggregationHigh" + "#" + "AggregationHigh" + "#!=#" + "0");
		 * metaDirSoftPairWiseRel.addPanelSpacersAttribute("#" +
		 * "AggregationHigh" + "#]\n");
		 */
		/*
		 * metaDirSoftPairWiseRel.addModelingAttribute("SourceLevel", "Integer",
		 * false, "Source Levelx", 0, 0, -1, "", "", -1, "", "");
		 * metaDirSoftPairWiseRel.addPropEditableAttribute("05#" +
		 * "SourceLevel"); metaDirSoftPairWiseRel.addPropVisibleAttribute("05#"
		 * + "SourceLevel");
		 * 
		 * metaDirSoftPairWiseRel.addModelingAttribute("TargetLevel", "Integer",
		 * false, "Target Level", 0, 0, -1, "", "", -1, "", "");
		 * metaDirSoftPairWiseRel.addPropEditableAttribute("06#" +
		 * "TargetLevel"); metaDirSoftPairWiseRel.addPropVisibleAttribute("06#"
		 * + "TargetLevel");
		 */
		InstConcept instDirSoftPairWiseRel = new InstConcept(
				"Direct Soft Relation", supportMetaElementPairwise,
				metaDirSoftPairWiseRel);

		instDirSoftPairWiseRel.setInstAttribute("Type", "Contribution");
		instDirSoftPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instDirSoftPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("Direct Soft Relation",
				instDirSoftPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-sg-pwrd", instEdge);
		instEdge.setIdentifier("variab-sg-pwrd");
		// instEdge.setEditableMetaElement(metaSoftPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirSoftPairWiseRel, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrd-sg", instEdge);
		instEdge.setIdentifier("variab-pwrd-sg");
		// instEdge.setEditableMetaElement(metaSoftPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instDirSoftPairWiseRel, true);

		InstConcept semanticSGSGGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("SgSgOTAsso"));

		// Group soft relation

		hardMetaOverTwoRel = new MetaOverTwoRelation("SoftgoalOTAsso", true,
				"SoftgoalOTAsso", "plgroup", "Direct relation between soft"
						+ " concepts. Defines different types of relations"
						+ " and cardinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticSGSGGroupRelation, false);

		// TODO Create another group

		InstConcept instGrpSoftFromPairWiseRel = new InstConcept(
				"GroupSoftFromRelation", supportMetaElementPairwise,
				metaGroupSoftFromPairWiseRel);

		instGrpSoftFromPairWiseRel.setInstAttribute("Type", "Contribution");
		instGrpSoftFromPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpSoftFromPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("GroupSoftFromRelation",
				instGrpSoftFromPairWiseRel);

		InstConcept instGrpSoftToPairWiseRel = new InstConcept(
				"GroupSoftToRelation", supportMetaElementPairwise,
				metaGroupSoftToPairWiseRel);

		instGrpSoftToPairWiseRel.setInstAttribute("Type", "Default7");
		instGrpSoftToPairWiseRel
				.setInstAttribute("SourceCardinality", "[0..*]");
		instGrpSoftToPairWiseRel
				.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("GroupSoftToRelation",
				instGrpSoftToPairWiseRel);

		InstVertex instVertexSGOTR = new InstConcept("SoftgoalOTAsso",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexSGOTR.getInstAttribute("Type").setValue("Group");
		variabilityInstVertex.put("SoftgoalOTAsso", instVertexSGOTR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-sg-pwrg", instEdge);
		instEdge.setIdentifier("sg-sg-pwrg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpSoftFromPairWiseRel, true);
		instEdge.setSourceRelation(instVertexSG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-pwrg-otr", instEdge);
		instEdge.setIdentifier("sg-pwrg-ovt");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSGOTR, true);
		instEdge.setSourceRelation(instGrpSoftFromPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-pwrs-sg", instEdge);
		instEdge.setIdentifier("sg--pwrs-sg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instGrpSoftToPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sg-ovt-pwrs", instEdge);
		instEdge.setIdentifier("sg-ovt-pwrs");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpSoftToPairWiseRel, true);
		instEdge.setSourceRelation(instVertexSGOTR, true);

		InstConcept instViewSGOTR = new InstConcept("View SG Group Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View SG Group Relation", instViewSGOTR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vsgg-tosgg", instEdge);
		instEdge.setIdentifier("vsgg-tosgg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSGOTR, true);
		instEdge.setSourceRelation(instViewSGOTR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vsgg-fromview", instEdge);
		instEdge.setIdentifier("vsgg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewSGOTR, true);
		instEdge.setSourceRelation(instViewC, true);

		// *************************---------------****************************
		// *************************---------------****************************
		// Context model

		syntaxMetaView = new MetaView("Context", true, "Context View",
				"plnode", "Defines a feature", 100, 80,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Context Palette", 3, null);
		instViewC = new InstConcept("Context", metaView, syntaxMetaView);
		variabilityInstVertex.put("Context", instViewC);
		// syntaxMetaView.addConcept(syntaxVariable);
		InstConcept semContextGroup = ((InstConcept) this.getSemanticRefas()
				.getVertex("ConcernLevel"));
		MetaConcept syntaxContextGroup = new MetaConcept('C', "CG", true,
				"ConcernLevel", "refascontextgrp", " A Concern Level"
						+ " is defined to associate variables with common"
						+ " characteristics. The type defines if variables"
						+ " are sensed or profiled, in the first case they"
						+ " are modifie by the system or environment; in the"
						+ " second case they are defined by the administrator"
						+ " or the user. The scope can be local or global,"
						+ " the first means the value is independently for"
						+ " each user while global variables are shared"
						+ " between all the users.", 150, 40,
				"/com/variamos/gui/perspeditor/images/contextgrp.png", true,
				Color.BLUE.toString(), 1, semContextGroup, true);

		syntaxContextGroup.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		syntaxContextGroup.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxContextGroup.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		syntaxContextGroup.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxContextGroup.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		syntaxContextGroup.addModelingAttribute("name", "String", false,
				"Name", "", 0, -1, "", "", -1, "", "");

		InstVertex instVertexCG = new InstConcept("CG",
				supportMetaElementConcept, syntaxContextGroup);
		variabilityInstVertex.put("CG", instVertexCG);

		InstConcept instViewCG = new InstConcept("View CG Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View CG Relation", instViewCG);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vcg-tocg", instEdge);
		instEdge.setIdentifier("vcg-tocg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instViewCG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vcg-fromview", instEdge);
		instEdge.setIdentifier("vcg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewCG, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept semVariable = ((InstConcept) this.getSemanticRefas()
				.getVertex("Variable"));
		MetaConcept syntaxAbsVariable = new MetaConcept(
				'C',
				"Variable",
				true,
				"Variable",
				"refasglobcnxt",
				"A variable"
						+ " is an abstraction of a variable or component of the"
						+ " system or the environment that are relevant the system."
						+ " The variable values should be"
						+ " simplified as much as possible considering the modeling"
						+ " requirements", 150, 40,
				"/com/variamos/gui/perspeditor/images/globCnxtVar.png", true,
				Color.BLUE.toString(), 1, semVariable, true);

		syntaxAbsVariable.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		syntaxAbsVariable.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxAbsVariable.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		syntaxAbsVariable.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxAbsVariable.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n");

		syntaxAbsVariable.addModelingAttribute("name", "String", false, "Name",
				"", 0, -1, "", "", -1, "", "");
		syntaxAbsVariable.addModelingAttribute("type", "String", false, "Type",
				"", 0, -1, "", "", -1, "", "");
		syntaxAbsVariable.addModelingAttribute("domain", "String", false,
				"Domain", "", 0, -1, "", "", -1, "", "");
		syntaxAbsVariable.addModelingAttribute("enumeration",
				"MetaEnumeration", false, "Enumeration", "", 0, -1, "", "", -1,
				"", "");
		// syntaxAbsVariable.addModelingAttribute("concern", "ConcernLevel",
		// false, "Concern Level", "", 0, -1, "", "", -1, "", "");

		InstVertex instVertexVar = new InstConcept("Variable",
				supportMetaElementConcept, syntaxAbsVariable);
		variabilityInstVertex.put("Variable", instVertexVar);

		InstConcept instViewVar = new InstConcept("View Var Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View Var Relation", instViewVar);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vvar-tovar", instEdge);
		instEdge.setIdentifier("vvar-tovar");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVar, true);
		instEdge.setSourceRelation(instViewVar, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vvar-fromview", instEdge);
		instEdge.setIdentifier("vvar-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewVar, true);
		instEdge.setSourceRelation(instViewC, true);

		MetaConcept syntaxGlobalVariable = new MetaConcept('C',
				"GlobalVariable", false, "Global Variable", "refasglobcnxt",
				"Old Concept, replaced by Variable Concept", 150, 40,
				"/com/variamos/gui/perspeditor/images/globCnxtVar.png", true,
				Color.BLUE.toString(), 1, semVariable, true);

		InstVertex instVertexGV = new InstConcept("GlobalVariable",
				supportMetaElementConcept, syntaxGlobalVariable);
		variabilityInstVertex.put("GlobalVariable", instVertexGV);

		InstConcept instExtGV = new InstConcept("GVar Ext Relation",
				supportMetaExtendsPairwise, metaViewRel);
		this.variabilityInstVertex.put("GVar Ext Relation", instExtGV);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("extgvar-vargr", instEdge);
		instEdge.setIdentifier("extgvar-vargr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVar, true);
		instEdge.setSourceRelation(instExtGV, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("extgrgvar-var", instEdge);
		instEdge.setIdentifier("extgrgvar-var");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instExtGV, true);
		instEdge.setSourceRelation(instVertexGV, true);

		InstConcept instViewGV = new InstConcept("View GVar Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View GVar Relation", instViewGV);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vgv-togv", instEdge);
		instEdge.setIdentifier("vgv-togv");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGV, true);
		instEdge.setSourceRelation(instViewGV, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vgv-fromview", instEdge);
		instEdge.setIdentifier("vgv-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewGV, true);
		instEdge.setSourceRelation(instViewC, true);

		MetaConcept syntaxContextVariable = new MetaConcept('C',
				"ContextVariable", false, "Context Variable", "refaslocalcnxt",
				" Old concept, replaced by Variable", 150, 40,
				"/com/variamos/gui/perspeditor/images/localCnxtVar.png", true,
				Color.BLUE.toString(), 1, semVariable, true);

		InstVertex instVertexCV = new InstConcept("ContextVariable",
				supportMetaElementConcept, syntaxContextVariable);
		variabilityInstVertex.put("ContextVariable", instVertexCV);

		InstConcept instExtCV = new InstConcept("CVar Ext Relation",
				supportMetaExtendsPairwise, metaViewRel);
		this.variabilityInstVertex.put("CVar Ext Relation", instExtCV);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("extcvar-vargr", instEdge);
		instEdge.setIdentifier("extcvar-vargr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexVar, true);
		instEdge.setSourceRelation(instExtCV, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("extgrcvar-var", instEdge);
		instEdge.setIdentifier("extgrcvar-var");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instExtCV, true);
		instEdge.setSourceRelation(instVertexCV, true);

		InstConcept instViewCV = new InstConcept("View CVar Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View CVar Relation", instViewCV);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vcv-tocv", instEdge);
		instEdge.setIdentifier("vcv-tocv");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCV, true);
		instEdge.setSourceRelation(instViewCV, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vcv-fromview", instEdge);
		instEdge.setIdentifier("vcv-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewCV, true);
		instEdge.setSourceRelation(instViewC, true);

		MetaEnumeration metaEnumeration = new MetaEnumeration("ME", true,
				"MetaEnumeration", "refasenumeration", "Allows the"
						+ " creation of user defined enumerations for"
						+ " variables", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true, "", 1,
				true);

		metaEnumeration.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		metaEnumeration.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaEnumeration.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		metaEnumeration.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		metaEnumeration.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		MetaView syntaxMetaChildView = new MetaView("FullContext",
				"Context with Enumerations", "Context Palette", 0, null);
		InstView childView = new InstView("FullContext", metaView,
				syntaxMetaChildView);
		// instView.addChildView(childView);
		// variabilityInstVertex.put("FullContext", childView);
		InstVertex instVertexME = new InstConcept("ME",
				supportMetaElementConcept, metaEnumeration);
		variabilityInstVertex.put("ME", instVertexME);

		InstConcept instViewME = new InstConcept("View ME Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View ME Relation", instViewME);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vme-tome", instEdge);
		instEdge.setIdentifier("vme-tome");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexME, true);
		instEdge.setSourceRelation(instViewME, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vme-fromview", instEdge);
		instEdge.setIdentifier("vme-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewME, true);
		instEdge.setSourceRelation(instViewC, true);

		syntaxMetaChildView = new MetaView("VariabContext",
				"Context without Enumerations", "Context Palette", 1, null);
		childView = new InstView("VariabContext", metaView, syntaxMetaChildView);
		// instView.addChildView(childView);
		// variabilityInstVertex.put("VariabContext", childView);
		// syntaxMetaChildView.addConcept(metaEnumeration);

		// InstConcept instViewCG2 = new InstConcept("View CG2 Relation",
		// supportMetaViewPairwise, metaViewRel);
		// this.variabilityInstVertex.put("View CG2 Relation", instViewCG2);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vcg2-tocv", instEdge);
		 * instEdge.setIdentifier("vcg2-tocv");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexCG, true);
		 * instEdge.setSourceRelation(instViewCG2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vcg2-fromview", instEdge);
		 * instEdge.setIdentifier("vcg2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewCG2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		childView.addInstVertex(instVertexCG);

		// InstConcept instViewCV2 = new InstConcept("View CVar2 Relation",
		// supportMetaViewPairwise, metaViewRel);
		// this.variabilityInstVertex.put("View CVar2 Relation", instViewCV2);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vcv2-tocv", instEdge);
		 * instEdge.setIdentifier("vcv2-tocv");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexCV, true);
		 * instEdge.setSourceRelation(instViewCV2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vcv2-fromview", instEdge);
		 * instEdge.setIdentifier("vcv2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewCV2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		childView.addInstVertex(instVertexCV);

		// InstConcept instViewGV2 = new InstConcept("View GVar2 Relation",
		// supportMetaViewPairwise, metaViewRel);
		// this.variabilityInstVertex.put("View GVar2 Relation", instViewGV2);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vgv2-tocv", instEdge);
		 * instEdge.setIdentifier("vgv2-tocv");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexGV, true);
		 * instEdge.setSourceRelation(instViewGV2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vgv2-fromview", instEdge);
		 * instEdge.setIdentifier("vgv2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewGV2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		childView.addInstVertex(instVertexGV);

		// Direct variable relations

		InstElement directCVCGSemanticEdge = getSemanticRefas().getVertex(
				"CVCGPWAsso");

		MetaPairwiseRelation metaVariableEdge = new MetaPairwiseRelation(
				"Variable To Context Relation", true,
				"Variable To Context Relation", "",
				"Associates a Context Variable" + " with the Context Group",
				60, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directCVCGSemanticEdge);

		MetaPairwiseRelation metaContextEdge = new MetaPairwiseRelation(
				"Context To Context Relation", true,
				"Context To Context Relation", "", "Associates a Context Group"
						+ " with other Context Group", 60, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directCVCGSemanticEdge);

		metaContextEdge.addModelingAttribute("cardinality", "String", false,
				"cardinality", "", 0, -1, "", "", -1, "", "");
		metaContextEdge.addPanelVisibleAttribute("01#cardinality");
		metaContextEdge.addPropEditableAttribute("01#cardinality");
		metaContextEdge.addPropVisibleAttribute("01#cardinality");

		InstConcept instVariablePairWiseRel = new InstConcept(
				"Variable To Context Relation", supportMetaElementPairwise,
				metaVariableEdge);
		instVariablePairWiseRel.setInstAttribute("Type", "Control");
		instVariablePairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instVariablePairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		this.variabilityInstVertex.put("Variable To Context Relation",
				instVariablePairWiseRel);

		InstConcept instCGPairWiseRel = new InstConcept(
				"Context Group Relation", supportMetaElementPairwise,
				metaContextEdge);
		instCGPairWiseRel.setInstAttribute("Type", "SubGroup");
		instCGPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instCGPairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		this.variabilityInstVertex.put("Context Group Relation",
				instCGPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-var-pwrd", instEdge);
		instEdge.setIdentifier("variab-var-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVariablePairWiseRel, true);
		instEdge.setSourceRelation(instVertexVar, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrd-cg", instEdge);
		instEdge.setIdentifier("variab-pwrd-cg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instVariablePairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cg-CG-pwrd", instEdge);
		instEdge.setIdentifier("cg-CG-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instCGPairWiseRel, true);
		instEdge.setSourceRelation(instVertexCG, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("cg-pwrd-cg", instEdge);
		instEdge.setIdentifier("cg-pwrd-cg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCG, true);
		instEdge.setSourceRelation(instCGPairWiseRel, true);

		// *************************---------------****************************
		// *************************---------------****************************
		// SG satisficing Model

		syntaxMetaView = new MetaView(
				"SoftGoalsSatisficing",
				true,
				"SG Satisficing View",
				"plnode",
				"Defines a feature",
				100,
				80,
				"/com/variamos/gui/pl/editor/images/plnode.png",
				3,
				"SG Satisficing Palette - Goals;SG Satisficing Palette - Features",
				4, null);
		instViewC = new InstConcept("SoftGoalsSatisficing", metaView,
				syntaxMetaView);

		InstConcept instGroupOperClaimPairWiseRel = new InstConcept(
				"OperClaim Group Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGroupOperClaimPairWiseRel.setInstAttribute("Type", "Default");
		instGroupOperClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGroupOperClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("OperClaim Group Relation",
				instGroupOperClaimPairWiseRel);

		InstConcept instGroupLFClaimPairWiseRel = new InstConcept(
				"LFClaim Group Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGroupLFClaimPairWiseRel.setInstAttribute("Type", "Default");
		instGroupLFClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGroupLFClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("LFClaim Group Relation",
				instGroupLFClaimPairWiseRel);

		variabilityInstVertex.put("SoftGoalsSatisficing", instViewC);

		InstConcept instViewTSGs = new InstConcept("ViewS TSG Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("ViewS TSG Relation", instViewTSGs);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vstsg-totsg", instEdge);
		instEdge.setIdentifier("vstsg-totsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexTSG, true);
		instEdge.setSourceRelation(instViewTSGs, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vstsg-fromview", instEdge);
		instEdge.setIdentifier("vstsg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewTSGs, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instViewGSGs = new InstConcept("ViewS GSG Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("ViewS GSG Relation", instViewGSGs);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vsgsg-togsg", instEdge);
		instEdge.setIdentifier("vsgsg-togsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexGSG, true);
		instEdge.setSourceRelation(instViewGSGs, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vsgsg-fromview", instEdge);
		instEdge.setIdentifier("vsgsg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewGSGs, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instViewSGs = new InstConcept("ViewS SG Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("ViewS SG Relation", instViewSGs);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vssg-totsg", instEdge);
		instEdge.setIdentifier("vssg-totsg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instViewSGs, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vssg-fromview", instEdge);
		instEdge.setIdentifier("vssg-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewSGs, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept instViewOpers = new InstConcept("ViewS Oper Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("ViewS Oper Relation", instViewOpers);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vsoper-totoper", instEdge);
		instEdge.setIdentifier("vsoper-tooper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instViewOpers, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vsoper-fromview", instEdge);
		instEdge.setIdentifier("vsoper-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewOpers, true);
		instEdge.setSourceRelation(instViewC, true);

		MetaPairwiseRelation metaViewLFsg = new MetaPairwiseRelation(
				"ViewRelation", true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewLFsg.setPalette("SG Satisficing Palette - Features");

		InstConcept instViewLFs = new InstConcept("ViewS LF Relation",
				supportMetaViewPairwise, metaViewLFsg);
		this.variabilityInstVertex.put("ViewS LF Relation", instViewLFs);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vslf-tolf", instEdge);
		instEdge.setIdentifier("vslf-tolf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instViewLFs, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vslf-fromview", instEdge);
		instEdge.setIdentifier("vslf-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewLFs, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept semClaim = ((InstConcept) this.getSemanticRefas()
				.getVertex("Claim"));

		MetaConcept syntaxClaim = new MetaConcept('C', "CL", true, "Claim",
				"refasclaim", "A claim includes a group of"
						+ " operationalizations and a logical condition"
						+ " to evaluate the claim satisfaction."
						+ " The claim is activated only when all the"
						+ " operationalizations are selected and the"
						+ " conditional expression is true. The claim"
						+ " includes a relation with a softgoal (SG)", 100, 50,
				"/com/variamos/gui/perspeditor/images/claim.png", true,
				Color.BLUE.toString(), 1, semClaim, true);

		syntaxClaim.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		syntaxClaim.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxClaim.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		syntaxClaim.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxClaim.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n");

		syntaxClaim.addModelingAttribute("name", "String", false, "Name", "",
				0, -1, "", "", -1, "", "");

		syntaxClaim
				.addModelingAttribute("conditionalExpression",
						new SemanticAttribute("conditionalExpression",
								"String", false, "Cond. Expression Text", "",
								0, -1, "", "", -1, "", ""));

		// syntaxClaim.addModelingAttribute("concern", "ConcernLevel", false,
		// "Concern Level", "", 0, -1, "", "", -1, "", "");

		// syntaxClaim.addPanelVisibleAttribute("03#" + "name");

		syntaxClaim.addPropEditableAttribute("03#" + "name");

		syntaxClaim.addPropVisibleAttribute("03#" + "name");

		// syntaxClaim.addPanelSpacersAttribute("#" + "name" + "#:\n");

		syntaxClaim.addPanelVisibleAttribute("10#" + "conditionalExpression");

		syntaxClaim.addPropEditableAttribute("10#" + "conditionalExpression");

		syntaxClaim.addPropVisibleAttribute("10#" + "conditionalExpression");

		InstVertex instVertexCL = new InstConcept("CL",
				supportMetaElementOverTwo, syntaxClaim);
		variabilityInstVertex.put("CL", instVertexCL);

		InstConcept instViewCL = new InstConcept("View CL Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View CL Relation", instViewCL);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vcl-tocl", instEdge);
		instEdge.setIdentifier("vcl-tocl");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instViewCL, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vcl-fromview", instEdge);
		instEdge.setIdentifier("vcl-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewCL, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept semSoftDependency = ((InstConcept) this.getSemanticRefas()
				.getVertex("SoftDep"));
		MetaConcept syntaxSoftDependency = new MetaConcept(
				'C',
				"SoftDependency",
				true,
				"Soft Dependency",
				"refassoftdep",
				"A Soft Dependency"
						+ " (SD express a logical condition useful to express"
						+ " an expected level of"
						+ " satisfaction of a softgoal. The soft dependency is"
						+ " activated only when the conditional expression is true."
						+ " The SD includes a relation with a softgoal (SG)",
				100, 70, "/com/variamos/gui/perspeditor/images/softdep.png",
				true, Color.BLUE.toString(), 1, semSoftDependency, true);

		syntaxSoftDependency.addModelingAttribute(
				MetaConcept.VAR_USERIDENTIFIER, new SyntaxAttribute(
						MetaConcept.VAR_USERIDENTIFIER, "String", false,
						"User Identifier", null, 0, 1, "", "", -1, "", ""));
		syntaxSoftDependency.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxSoftDependency.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		syntaxSoftDependency.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxSoftDependency.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n");

		syntaxSoftDependency.addModelingAttribute("name", "String", false,
				"Name", "", 0, -1, "", "", -1, "", "");

		// syntaxSoftDependency
		// .addModelingAttribute("conditionalExpression",
		// new SemanticAttribute("conditionalExpression",
		// "String", false, "Cond. Expression Text", "",
		// 0, -1, "", "", -1, "", ""));

		// syntaxSoftDependency.addModelingAttribute("concern", "ConcernLevel",
		// false, "Concern Level", "", 0, -1, "", "", -1, "", "");

		// syntaxSoftDependency.addPanelVisibleAttribute("03#" + "name");

		syntaxSoftDependency.addPropEditableAttribute("03#" + "name");

		syntaxSoftDependency.addPropVisibleAttribute("03#" + "name");

		// syntaxSoftDependency.addPanelSpacersAttribute("#" + "name" + "#:\n");

		// syntaxSoftDependency.addPanelVisibleAttribute("10#"
		// + "conditionalExpression");

		// syntaxSoftDependency.addPropEditableAttribute("10#"
		// + "conditionalExpression");

		// syntaxSoftDependency.addPropVisibleAttribute("10#"
		// + "conditionalExpression");

		InstVertex instVertexSD = new InstConcept("SoftDependency",
				supportMetaElementConcept, syntaxSoftDependency);
		variabilityInstVertex.put("SoftDependency", instVertexSD);

		InstConcept instViewSD = new InstConcept("View SD Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View SD Relation", instViewSD);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vsd-tosd", instEdge);
		instEdge.setIdentifier("vsd-tosd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSD, true);
		instEdge.setSourceRelation(instViewSD, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vsd-fromview", instEdge);
		instEdge.setIdentifier("vsd-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewSD, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept semanticOperClaimGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("OperCLOTAsso"));

		hardMetaOverTwoRel = new MetaOverTwoRelation(
				"OperClaimOverTwoRel",
				true,
				"OperClaimOverTwoRel",
				"plgroup",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " expected on the softgoal in case the Claim is satisfied",
				20, 20, "/com/variamos/gui/pl/editor/images/plgroup.png",
				false, "white", 1, semanticOperClaimGroupRelation, false);

		InstElement semClaimPairwiseRel = getSemanticRefas().getVertex(
				"OperClaimPWAsso");

		MetaPairwiseRelation metaClaimPairwiseRel = new MetaPairwiseRelation(
				"ClaimRelation",
				true,
				"ClaimRelation",
				"",
				"Represent the relation between"
						+ " an operationalization(s) and a claim. The operationalization(s)"
						+ " is required to satisfy a claim", 60, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				semClaimPairwiseRel);

		InstConcept instGrpOperClaimPairWiseRel = new InstConcept(
				"GrpOperClaimRelation", supportMetaElementPairwise,
				metaClaimPairwiseRel);

		instGrpOperClaimPairWiseRel.setInstAttribute("Type", "OPER-CL");
		instGrpOperClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpOperClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("GrpOperClaimRelation",
				instGrpOperClaimPairWiseRel);

		InstConcept instGrpLFClaimPairWiseRel = new InstConcept(
				"GrpLFClaimRelation", supportMetaElementPairwise,
				metaClaimPairwiseRel);

		instGrpLFClaimPairWiseRel.setInstAttribute("Type", "LF-CL");
		instGrpLFClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpLFClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("GrpLFClaimRelation",
				instGrpLFClaimPairWiseRel);

		MetaPairwiseRelation metaDirClaimPairwiseRel = new MetaPairwiseRelation(
				"ClaimRelation",
				true,
				"ClaimRelation",
				"",
				"Represent the relation between"
						+ " an operationalization(s) and a claim. The operationalization(s)"
						+ " is required to satisfy a claim", 60, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				semClaimPairwiseRel);
		metaDirClaimPairwiseRel.addModelingAttribute("AggregationLow",
				"Integer", false, "Aggregation Low", 0, 0, -1, "", "", -1, "",
				"");
		metaDirClaimPairwiseRel.addPropEditableAttribute("03#"
				+ "AggregationLow");
		metaDirClaimPairwiseRel.addPropVisibleAttribute("03#"
				+ "AggregationLow");
		metaDirClaimPairwiseRel.addPanelVisibleAttribute("03#"
				+ "AggregationLow" + "#" + "AggregationHigh" + "#!=#" + "0");
		metaDirClaimPairwiseRel.addPanelSpacersAttribute("[#"
				+ "AggregationLow" + "#..");

		metaDirClaimPairwiseRel.addModelingAttribute("AggregationHigh",
				"Integer", false, "Aggregation High", 0, 0, -1, "", "", -1, "",
				"");
		metaDirClaimPairwiseRel.addPropEditableAttribute("04#"
				+ "AggregationHigh");
		metaDirClaimPairwiseRel.addPropVisibleAttribute("04#"
				+ "AggregationHigh");
		metaDirClaimPairwiseRel.addPanelVisibleAttribute("04#"
				+ "AggregationHigh" + "#" + "AggregationHigh" + "#!=#" + "0");
		metaDirClaimPairwiseRel.addPanelSpacersAttribute("#"
				+ "AggregationHigh" + "#]\n");

		InstConcept instDirOperClaimPairWiseRel = new InstConcept(
				"DirOperClaimRelation", supportMetaElementPairwise,
				metaDirClaimPairwiseRel);

		instDirOperClaimPairWiseRel.setInstAttribute("Type", "OPER-CL");
		instDirOperClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instDirOperClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("DirOperClaimRelation",
				instDirOperClaimPairWiseRel);

		InstConcept instDirLFClaimPairWiseRel = new InstConcept(
				"DirLFClaimRelation", supportMetaElementPairwise,
				metaDirClaimPairwiseRel);

		instDirLFClaimPairWiseRel.setInstAttribute("Type", "LF-CL");
		instDirLFClaimPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instDirLFClaimPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("DirLFClaimRelation",
				instDirLFClaimPairWiseRel);

		InstVertex instVertexOCOTR = new InstConcept("OperClaimOverTwoRel",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexOCOTR.getInstAttribute("Type").setValue("Group");

		variabilityInstVertex.put("OperClaimOverTwoRel", instVertexOCOTR);

		InstConcept instViewOCOTR = new InstConcept("View OC Group Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View OC Group Relation", instViewOCOTR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vocotr-tovocotr", instEdge);
		instEdge.setIdentifier("vocotr-tovocotr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOCOTR, true);
		instEdge.setSourceRelation(instViewOCOTR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vocotr-fromview", instEdge);
		instEdge.setIdentifier("vocotr-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewOCOTR, true);
		instEdge.setSourceRelation(instViewC, true);

		InstConcept semanticLFClaimGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("LFtoClaimOTAsso"));

		hardMetaOverTwoRel = new MetaOverTwoRelation(
				"LFClaimOTAsso",
				true,
				"LFClaimOTAsso",
				"plgroup",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " expected on the softgoal in case the Claim is satisfied",
				20, 20, "/com/variamos/gui/pl/editor/images/plgroup.png",
				false, "white", 1, semanticLFClaimGroupRelation, false);

		InstVertex instVertexFCOTR = new InstConcept("LFClaimOTAsso",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexFCOTR.getInstAttribute("Type").setValue("Group");

		variabilityInstVertex.put("LFClaimOTAsso", instVertexFCOTR);

		MetaPairwiseRelation metaViewLFCL = new MetaPairwiseRelation(
				"ViewRelation", true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewLFCL.setPalette("SG Satisficing Palette - Features");

		InstConcept instViewFCOTR = new InstConcept("View FC Group Relation",
				supportMetaViewPairwise, metaViewLFCL);
		this.variabilityInstVertex.put("View FC Group Relation", instViewFCOTR);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vfcotr-tovfcotr", instEdge);
		instEdge.setIdentifier("vfcotr-tovfcotr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexFCOTR, true);
		instEdge.setSourceRelation(instViewFCOTR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vfcotr-fromview", instEdge);
		instEdge.setIdentifier("vfcotr-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewFCOTR, true);
		instEdge.setSourceRelation(instViewC, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-pwrd-opclaim", instEdge);
		instEdge.setIdentifier("sgs-pwrd-opclaim");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirOperClaimPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-oper-pwrd", instEdge);
		instEdge.setIdentifier("sgs-oper-pwrd");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirOperClaimPairWiseRel, true);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-pwrd-lfclaim", instEdge);
		instEdge.setIdentifier("sgs-pwrd-lfclaim");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instDirLFClaimPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-lf-pwrd", instEdge);
		instEdge.setIdentifier("sgs-lf-pwrd");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirLFClaimPairWiseRel, true);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-Oper-pwrg", instEdge);
		instEdge.setIdentifier("sgs-Oper-pwrg");
		instEdge.setTargetRelation(instGroupOperClaimPairWiseRel, true);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instVertexOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-pwrg-opclaimgp", instEdge);
		instEdge.setIdentifier("sgs-pwrg-opclaimgp");
		instEdge.setTargetRelation(instVertexOCOTR, true);
		instEdge.setEditableMetaElement(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instGroupOperClaimPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-LF-pwrg", instEdge);
		instEdge.setIdentifier("sgs-LF-pwrg");
		instEdge.setTargetRelation(instGroupLFClaimPairWiseRel, true);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instVertexLF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-pwrg-lfclaimgp", instEdge);
		instEdge.setIdentifier("sgs-pwrg-lfclaimgp");
		instEdge.setTargetRelation(instVertexFCOTR, true);
		instEdge.setEditableMetaElement(metaGroupPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setSourceRelation(instGroupLFClaimPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-pwr-CL", instEdge);
		instEdge.setIdentifier("sgs-pwr-CL");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instGrpOperClaimPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-claimgp-pwr", instEdge);
		instEdge.setIdentifier("sgs-claimgp-pwr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpOperClaimPairWiseRel, true);
		instEdge.setSourceRelation(instVertexOCOTR, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-pwr-LFCL", instEdge);
		instEdge.setIdentifier("sgs-pwr-LFCL");
		instEdge.setEditableMetaElement(metaClaimPairwiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexCL, true);
		instEdge.setSourceRelation(instGrpLFClaimPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-lfclaimgp-pwr", instEdge);
		instEdge.setIdentifier("sgs-lfclaimgp-pwr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpLFClaimPairWiseRel, true);
		instEdge.setSourceRelation(instVertexFCOTR, true);

		InstElement directSDSGSemanticEdge = getSemanticRefas().getVertex(
				"SDSGPWAsso");

		MetaPairwiseRelation metaSDSGEdge = new MetaPairwiseRelation(
				"SDSGRelation",
				true,
				"SDSGRelation",
				"",
				"Express the relation between"
						+ " the SD and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSDSGSemanticEdge);

		InstConcept instSDPairWiseRel = new InstConcept("SDSGRelation",
				supportMetaElementPairwise, metaSDSGEdge);

		instSDPairWiseRel.setInstAttribute("Type", "SD-SG");
		instSDPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instSDPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("SDSGRelation", instSDPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-pwrd-SG", instEdge);
		instEdge.setIdentifier("variab-Spwrd-SG");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instSDPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("variab-SD-pwrd", instEdge);
		instEdge.setIdentifier("variab-SD-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instSDPairWiseRel, true);
		instEdge.setSourceRelation(instVertexSD, true);

		// constraintInstEdges.put("SDSGRelation", new InstEdge(
		// MetaEdge, metaSDSGEdge));

		InstElement directClaimSGSemanticEdge = getSemanticRefas().getVertex(
				"ClaimSGPWAsso");

		MetaPairwiseRelation metaClaimSGEdge = new MetaPairwiseRelation(
				"Claim-Softgoal Relation",
				true,
				"Claim-Softgoal Relation",
				"",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directClaimSGSemanticEdge);

		InstConcept instCLPairWiseRel = new InstConcept("CLSGRelation",
				supportMetaElementPairwise, metaClaimSGEdge);

		instCLPairWiseRel.setInstAttribute("Type", "CL-SG");
		instCLPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instCLPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("CLSGRelation", instCLPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-pwrd-SG", instEdge);
		instEdge.setIdentifier("sgs-pwrdSG");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexSG, true);
		instEdge.setSourceRelation(instCLPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("sgs-CL-pwrd", instEdge);
		instEdge.setIdentifier("sgs-CL-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instCLPairWiseRel, true);
		instEdge.setSourceRelation(instVertexCL, true);

		// *************************---------------****************************
		// *************************---------------****************************
		// Assets model

		syntaxMetaView = new MetaView("Assets", true, "Assets View", "plnode",
				"Defines an Asset", 100, 90,
				"/com/variamos/gui/pl/editor/images/plnode.png", 3,
				"Assets Palette - Opers;Assets Palette - Features", 5, null);
		instViewC = new InstConcept("Assets", metaView, syntaxMetaView);
		variabilityInstVertex.put("Assets", instViewC);
		// syntaxMetaView.addConcept(sOperationalization);
		// syntaxMetaView.addConcept(syntaxVertexLF);

		InstConcept semAsset = ((InstConcept) this.getSemanticRefas()
				.getVertex("Asset"));
		MetaConcept syntaxAsset = new MetaConcept('C', "Asset", true, "Asset",
				"refasasset", "Represents a asset of the system. The most"
						+ " important assets to represent are those than"
						+ " can implement operationalizations", 100, 40,
				"/com/variamos/gui/perspeditor/images/component.png", true,
				Color.WHITE.toString(), 1, semAsset, true);

		syntaxAsset.addModelingAttribute(MetaConcept.VAR_USERIDENTIFIER,
				new SyntaxAttribute(MetaConcept.VAR_USERIDENTIFIER, "String",
						false, "User Identifier", null, 0, 1, "", "", -1, "",
						""));
		syntaxAsset.addPropVisibleAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxAsset.addPropEditableAttribute("01#"
				+ MetaConcept.VAR_USERIDENTIFIER);

		syntaxAsset.addPanelVisibleAttribute("04#"
				+ MetaConcept.VAR_USERIDENTIFIER);
		syntaxAsset.addPanelSpacersAttribute("#"
				+ MetaConcept.VAR_USERIDENTIFIER + "#\n\n");

		syntaxAsset.addModelingAttribute("name", "String", false, "Name", "",
				0, -1, "", "", -1, "", "");
		// syntaxAsset.addModelingAttribute("concern", "ConcernLevel", false,
		// "Concern Level", "", 0, -1, "", "", -1, "", "");

		// Create another meta element
		InstConcept instGroupAssetPairWiseRel = new InstConcept(
				"Soft Group Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGroupAssetPairWiseRel.setInstAttribute("Type", "Default");
		instGroupAssetPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGroupAssetPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("Soft Group Relation",
				instGroupAssetPairWiseRel);

		// syntaxMetaView.addConcept(syntaxAsset);

		// syntaxAsset.addPanelVisibleAttribute("03#" + "name");
		syntaxAsset.addPropEditableAttribute("03#" + "name");
		syntaxAsset.addPropVisibleAttribute("03#" + "name");
		syntaxMetaChildView = new MetaView("Assets", "Assets General View",
				"Assets Palette", 0, null);
		childView = new InstView("GeneralAssets", metaView, syntaxMetaChildView);
		// variabilityInstVertex.put("GeneralAssets", childView);

		childView.addInstVertex(instVertexOper);
		childView.addInstVertex(instVertexLF);

		InstVertex instVertexAsset = new InstConcept("Asset",
				supportMetaElementConcept, syntaxAsset);
		variabilityInstVertex.put("Asset", instVertexAsset);
		childView.addInstVertex(instVertexAsset);

		InstConcept semanticAssetOperGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("AssetOperOTAsso"));

		InstElement directAssetOperSemanticEdge = getSemanticRefas().getVertex(
				"AssetOperPWAsso");

		InstConcept semanticAssetAssetGroupRelation = ((InstConcept) this
				.getSemanticRefas().getVertex("AssetAssetOTAsso"));

		InstElement directAssetSemanticEdge = getSemanticRefas().getVertex(
				"varAssetPWAsso");

		hardMetaOverTwoRel = new MetaOverTwoRelation("AssetOperGroupDep", true,
				"AssetOperGroupDep", "plgroup",
				"Represents the implementation "
						+ "of an operationalization by a group of assets", 20,
				20, "/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticAssetOperGroupRelation, false);
		InstVertex instVertexAssetOper = new InstConcept("AssetOperGroupDep",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexAssetOper.getInstAttribute("Type").setValue("Group");
		variabilityInstVertex.put("AssetOperGroupDep", instVertexAssetOper);

		hardMetaOverTwoRel = new MetaOverTwoRelation("AssetFeatGroupDep", true,
				"AssetFeatGroupDep", "plgroup",
				"Represents the implementation "
						+ "of a feautre by a group of assets", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticAssetOperGroupRelation, false);
		InstVertex instVertexAssetFeat = new InstConcept("AssetFeatGroupDep",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexAssetFeat.getInstAttribute("Type").setValue("Group");
		variabilityInstVertex.put("AssetFeatGroupDep", instVertexAssetFeat);

		hardMetaOverTwoRel = new MetaOverTwoRelation("AssetAssetOTAsso", true,
				"AssetAssetOTAsso", "plgroup", "Represents the relation "
						+ "of an asset with a group of assets", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, semanticAssetAssetGroupRelation, false);
		InstVertex instVertexAssetAsset = new InstConcept("AssetAssetOTAsso",
				supportMetaElementOverTwo, hardMetaOverTwoRel);
		instVertexAssetAsset.getInstAttribute("Type").setValue("Group");
		variabilityInstVertex.put("AssetAssetOTAsso", instVertexAssetAsset);

		MetaPairwiseRelation metaOperPairWiseRel = new MetaPairwiseRelation(
				"Asset To Oper Relation", true, "Asset To Oper Relation", "",
				"Represents the "
						+ "implementation of an operationzalization by an"
						+ " asset", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directAssetOperSemanticEdge);

		InstConcept instDirOperPairWiseRel = new InstConcept(
				"Dir Asset To Oper Relation", supportMetaElementPairwise,
				metaOperPairWiseRel);

		instDirOperPairWiseRel.setInstAttribute("Type", "Implementation");
		instDirOperPairWiseRel.setInstAttribute("SourceCardinality", "[0..1]");
		instDirOperPairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		this.variabilityInstVertex.put("Dir Asset To Oper Relation",
				instDirOperPairWiseRel);

		InstConcept instGrpOperPairWiseRel = new InstConcept(
				"Group Asset To Oper Relation", supportMetaElementPairwise,
				metaOperPairWiseRel);

		instGrpOperPairWiseRel.setInstAttribute("Type", "Implementation");
		instGrpOperPairWiseRel.setInstAttribute("SourceCardinality", "[0..1]");
		instGrpOperPairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		this.variabilityInstVertex.put("Group Asset To Oper Relation",
				instGrpOperPairWiseRel);

		InstConcept instGrpLFPairWiseRel = new InstConcept(
				"Group Asset To L Relation", supportMetaElementPairwise,
				metaOperPairWiseRel);

		instGrpLFPairWiseRel.setInstAttribute("Type", "Implementation");
		instGrpLFPairWiseRel.setInstAttribute("SourceCardinality", "[0..1]");
		instGrpLFPairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		this.variabilityInstVertex.put("Group Asset To LF Relation",
				instGrpLFPairWiseRel);

		MetaPairwiseRelation metaFeaturePairWiseRel = new MetaPairwiseRelation(
				"Asset To Feature Relation", true, "Asset To Feature Relation",
				"", "Represents the " + "implementation of an feature by an"
						+ " asset", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directAssetOperSemanticEdge);

		InstConcept instDirFeatPairWiseRel = new InstConcept(
				"Asset To Feature Relation", supportMetaElementPairwise,
				metaFeaturePairWiseRel);
		instDirFeatPairWiseRel.setInstAttribute("Type", "Implementation");
		instDirFeatPairWiseRel.setInstAttribute("SourceCardinality", "[0..1]");
		instDirFeatPairWiseRel.setInstAttribute("TargetCardinality", "[0..1]");
		this.variabilityInstVertex.put("Asset To Feature Relation",
				instDirFeatPairWiseRel);

		MetaPairwiseRelation metaAssetPairWiseRel = new MetaPairwiseRelation(
				"Asset To Asset Relation", true, "Asset To Asset Relation", "",
				"Represents a " + "type of an operationzalization between "
						+ " assets", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directAssetSemanticEdge);
		metaAssetPairWiseRel.addModelingAttribute("AggregationLow", "Integer",
				false, "Aggregation Low", 0, 0, -1, "", "", -1, "", "");
		metaAssetPairWiseRel.addPropEditableAttribute("03#" + "AggregationLow");
		metaAssetPairWiseRel.addPropVisibleAttribute("03#" + "AggregationLow");
		metaAssetPairWiseRel.addPanelVisibleAttribute("03#" + "AggregationLow"
				+ "#" + "AggregationHigh" + "#!=#" + "0");
		metaAssetPairWiseRel.addPanelSpacersAttribute("[#" + "AggregationLow"
				+ "#..");

		metaAssetPairWiseRel.addModelingAttribute("AggregationHigh", "Integer",
				false, "AggregationHigh", 0, 0, -1, "", "", -1, "", "");
		metaAssetPairWiseRel
				.addPropEditableAttribute("04#" + "AggregationHigh");
		metaAssetPairWiseRel.addPropVisibleAttribute("04#" + "AggregationHigh");
		metaAssetPairWiseRel.addPanelVisibleAttribute("04#" + "AggregationHigh"
				+ "#" + "AggregationHigh" + "#!=#" + "0");
		metaAssetPairWiseRel.addPanelSpacersAttribute("#" + "AggregationHigh"
				+ "#]\n");

		InstConcept instDirAssetPairWiseRel = new InstConcept(
				"Dir Asset To Asset Relation", supportMetaElementPairwise,
				metaAssetPairWiseRel);

		instDirAssetPairWiseRel.setInstAttribute("Type", "ASSET-ASSET");
		instDirAssetPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instDirAssetPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("Dir Asset To Asset Relation",
				instDirAssetPairWiseRel);

		MetaPairwiseRelation metaGrpAssetPairWiseRel = new MetaPairwiseRelation(
				"Asset To Asset Relation", true, "Asset To Asset Relation", "",
				"Represents a " + "type of an operationzalization between "
						+ " assets", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directAssetSemanticEdge);

		InstConcept instGrpAssetPairWiseRel = new InstConcept(
				"Group Asset To Asset Relation", supportMetaElementPairwise,
				metaGrpAssetPairWiseRel);

		instGrpAssetPairWiseRel.setInstAttribute("Type", "ASSET-ASSET");
		instGrpAssetPairWiseRel.setInstAttribute("SourceCardinality", "[0..*]");
		instGrpAssetPairWiseRel.setInstAttribute("TargetCardinality", "[0..*]");
		this.variabilityInstVertex.put("Group Asset To Asset Relation",
				instGrpAssetPairWiseRel);

		InstConcept instGrpAssetAssetPairWiseRel = new InstConcept(
				"Group Asset-Asset Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGrpAssetAssetPairWiseRel.setInstAttribute("Type", "Default");
		instGrpAssetAssetPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpAssetAssetPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("Group Asset-Asset Relation",
				instGrpAssetAssetPairWiseRel);

		InstConcept instGrpAssetOperPairWiseRel = new InstConcept(
				"Group Asset-Oper Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGrpAssetOperPairWiseRel.setInstAttribute("Type", "Default");
		instGrpAssetOperPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpAssetOperPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("Group Asset-Oper Relation",
				instGrpAssetOperPairWiseRel);

		InstConcept instGrpAssetFeatPairWiseRel = new InstConcept(
				"Group Asset-Feat Relation", supportMetaElementPairwise,
				metaGroupPairwiseRel);

		instGrpAssetFeatPairWiseRel.setInstAttribute("Type", "Default");
		instGrpAssetFeatPairWiseRel.setInstAttribute("SourceCardinality",
				"[0..*]");
		instGrpAssetFeatPairWiseRel.setInstAttribute("TargetCardinality",
				"[0..*]");
		this.variabilityInstVertex.put("Group Asset-Feat Relation",
				instGrpAssetFeatPairWiseRel);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assetoper-pwrd", instEdge);
		instEdge.setIdentifier("asset0-assetoper-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirOperPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwrd-assetoper", instEdge);
		instEdge.setIdentifier("asset0-pwrd-assetoper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instDirOperPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assetlf-pwrd", instEdge);
		instEdge.setIdentifier("asset0-assetlf-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirFeatPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwrd-assetlf", instEdge);
		instEdge.setIdentifier("asset0-pwrd-assetlf");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instDirFeatPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assetasset-pwrd", instEdge);
		instEdge.setIdentifier("asset0-assetasset-pwrd");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instDirAssetPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwrd-assetasset", instEdge);
		instEdge.setIdentifier("asset0-pwrd-assetasset");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instDirAssetPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-asset-pwrgo", instEdge);
		instEdge.setIdentifier("asset0-asset-pwrgo");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpAssetOperPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwrgo-assetgp", instEdge);
		instEdge.setIdentifier("asset0-pwrgo-assetgp");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetOper, true);
		instEdge.setSourceRelation(instGrpAssetOperPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-asset-pwrgo2", instEdge);
		instEdge.setIdentifier("asset0-asset-pwrgo2");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpAssetFeatPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwrgo-assetgp2", instEdge);
		instEdge.setIdentifier("asset0-pwrgo-assetgp2");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetFeat, true);
		instEdge.setSourceRelation(instGrpAssetFeatPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwro-oper", instEdge);
		instEdge.setIdentifier("asset0-pwro-oper");
		instEdge.setEditableMetaElement(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instGrpOperPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assetgp-pwro", instEdge);
		instEdge.setIdentifier("asset0-assetgp-pwro");
		instEdge.setEditableMetaElement(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpOperPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAssetOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-fpwro-oper", instEdge);
		instEdge.setIdentifier("asset0-fpwro-oper");
		instEdge.setEditableMetaElement(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instGrpLFPairWiseRel, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assetgp-fpwro", instEdge);
		instEdge.setIdentifier("asset0-assetgp-fpwro");
		instEdge.setEditableMetaElement(metaOperPairWiseRel);
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpLFPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAssetFeat, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-asset-fpwrg", instEdge);
		instEdge.setIdentifier("asset0-asset-fpwrg");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpAssetAssetPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwrg-assetgp", instEdge);
		instEdge.setIdentifier("asset0-pwrg-assetgp");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetAsset, true);
		instEdge.setSourceRelation(instGrpAssetAssetPairWiseRel, true);
		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-assetgp-pwr", instEdge);
		instEdge.setIdentifier("asset0-assetgp-pwr");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instGrpAssetPairWiseRel, true);
		instEdge.setSourceRelation(instVertexAssetAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("asset0-pwr-asset", instEdge);
		instEdge.setIdentifier("asset0-pwr-asset");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instGrpAssetPairWiseRel, true);

		InstConcept instViewAsset4 = new InstConcept("View Asset Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View Asset Relation", instViewAsset4);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vasset4-toasset", instEdge);
		 * instEdge.setIdentifier("vasset4-toasset");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexAsset, true);
		 * instEdge.setSourceRelation(instViewAsset4, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vasset4-fromview", instEdge);
		 * instEdge.setIdentifier("vasset4-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAsset4, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		/*
		 * InstConcept instViewAOper3 = new InstConcept("ViewA Oper3 Relation",
		 * supportMetaViewPairwise, metaViewRel);
		 * this.variabilityInstVertex.put("ViewA Oper3 Relation",
		 * instViewAOper3);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vaoper3-tooper", instEdge);
		 * instEdge.setIdentifier("vaoper3-tooper");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexOper, true);
		 * instEdge.setSourceRelation(instViewAOper3, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vaoper3-fromview", instEdge);
		 * instEdge.setIdentifier("vaoper3-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAOper3, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		MetaPairwiseRelation metaViewLF2 = new MetaPairwiseRelation(
				"ViewRelation", true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewLF2.setPalette("Assets Palette - Features");

		/*
		 * InstConcept instViewLF2 = new InstConcept("View LF2 Relation",
		 * supportMetaViewPairwise, metaViewLF2);
		 * this.variabilityInstVertex.put("View LF2 Relation", instViewLF2);
		 */
		MetaPairwiseRelation metaViewLF3 = new MetaPairwiseRelation(
				"ViewRelation", true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewLF3.setPalette("Assets Palette - Features");

		InstConcept instViewLF3 = new InstConcept("View LF3 Relation",
				supportMetaViewPairwise, metaViewLF3);
		this.variabilityInstVertex.put("View LF3 Relation", instViewLF3);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vlf3-tolft", instEdge);
		instEdge.setIdentifier("vlf3-tolft");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexLF, true);
		instEdge.setSourceRelation(instViewLF3, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vlf3-fromview", instEdge);
		instEdge.setIdentifier("vlf3-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewLF3, true);
		instEdge.setSourceRelation(instViewC, true);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vlf2-tolft", instEdge);
		 * instEdge.setIdentifier("vlf2-tolft");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexLF, true);
		 * instEdge.setSourceRelation(instViewLF2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vlf2-fromview", instEdge);
		 * instEdge.setIdentifier("vlf2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewLF2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		MetaPairwiseRelation metaViewAsFG2 = new MetaPairwiseRelation(
				"ViewRelation", true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 40,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewAsFG2.setPalette("Assets Palette - Features");

		InstConcept instViewAssetLF2 = new InstConcept("View ASSLF Relation",
				supportMetaViewPairwise, metaViewAsFG2);
		this.variabilityInstVertex.put("View ASSLF Relation", instViewAssetLF2);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vassetlf-toassoper", instEdge);
		 * instEdge.setIdentifier("vassetlf-toassoper");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexAssetFeat, true);
		 * instEdge.setSourceRelation(instViewAssetLF2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vassetlf2-fromview", instEdge);
		 * instEdge.setIdentifier("vassetlf2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAssetLF2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		MetaPairwiseRelation metaViewAsFG = new MetaPairwiseRelation(
				"ViewRelation", true, "ViewRelation", "",
				"View relation between a view and a concepts.", 60, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directViewSemanticEdge);
		metaViewAsFG.setPalette("Assets Palette - Features");

		InstConcept instViewAssetLF = new InstConcept("View ASSLF Relation",
				supportMetaViewPairwise, metaViewAsFG);

		this.variabilityInstVertex.put("View ASSLF Relation", instViewAssetLF);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vlfoper2-toassoper", instEdge);
		instEdge.setIdentifier("vlfoper2-toassoper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetFeat, true);
		instEdge.setSourceRelation(instViewAssetLF, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vlfoper-fromview", instEdge);
		instEdge.setIdentifier("vlfoper-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewAssetLF, true);
		instEdge.setSourceRelation(instViewC, true);

		/*
		 * InstConcept instViewAssOper = new
		 * InstConcept("View AssOper Relation", supportMetaViewPairwise,
		 * metaViewRel); this.variabilityInstVertex
		 * .put("View AssOper Relation", instViewAssOper);
		 */
		InstConcept instViewAssOper2 = new InstConcept(
				"View AssOper2 Relation", supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View AssOper2 Relation",
				instViewAssOper2);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vassoper-toassoper", instEdge);
		instEdge.setIdentifier("vassoper-toassoper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAssetOper, true);
		instEdge.setSourceRelation(instViewAssOper2, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vassoper2-fromview", instEdge);
		instEdge.setIdentifier("vassoper2-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewAssOper2, true);
		instEdge.setSourceRelation(instViewC, true);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vassoper3-toassoper", instEdge);
		 * instEdge.setIdentifier("vassoper3-toassoper");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexAssetOper, true);
		 * instEdge.setSourceRelation(instViewAssOper, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vassoper-fromview", instEdge);
		 * instEdge.setIdentifier("vassoper-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAssOper, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		syntaxMetaChildView = new MetaView("FunctionalAssets",
				"Functional Assets Relations", "Assets Palette", 1, null);
		childView = new InstView("FunctionalAssets", metaView,
				syntaxMetaChildView);
		// instView.addChildView(childView);
		// syntaxMetaChildView.addConcept(sOperationalization);
		// childView.addInstVertex(instVertexOper);
		childView.addInstVertex(instVertexAsset);
		// variabilityInstVertex.put("FunctionalAssets", childView);

		InstConcept instViewAsset = new InstConcept("View Asset Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("View Asset Relation", instViewAsset);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vasset-toasset", instEdge);
		instEdge.setIdentifier("vasset-toasset");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexAsset, true);
		instEdge.setSourceRelation(instViewAsset, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vasset-fromview", instEdge);
		instEdge.setIdentifier("vasset-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewAsset, true);
		instEdge.setSourceRelation(instViewC, true);

		// InstConcept instViewAsset2 = new InstConcept("View Asset2 Relation",
		// supportMetaViewPairwise, metaViewRel);
		// this.variabilityInstVertex.put("View Asset2 Relation",
		// instViewAsset2);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vasset2-toasset", instEdge);
		 * instEdge.setIdentifier("vasset2-toasset");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexAsset, true);
		 * instEdge.setSourceRelation(instViewAsset2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vasset2-fromview", instEdge);
		 * instEdge.setIdentifier("vasset2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAsset2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		InstConcept instViewAOper = new InstConcept("ViewA Oper Relation",
				supportMetaViewPairwise, metaViewRel);
		this.variabilityInstVertex.put("ViewA Oper Relation", instViewAOper);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vaoper-tooper", instEdge);
		instEdge.setIdentifier("vaoper-tooper");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instVertexOper, true);
		instEdge.setSourceRelation(instViewAOper, true);

		instEdge = new InstPairwiseRelation();
		this.constraintInstEdges.put("vaoper-fromview", instEdge);
		instEdge.setIdentifier("vaoper-fromview");
		instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		instEdge.setTargetRelation(instViewAOper, true);
		instEdge.setSourceRelation(instViewC, true);

		// InstConcept instViewAOper2 = new InstConcept("ViewA Oper2 Relation",
		// supportMetaViewPairwise, metaViewRel);
		// this.variabilityInstVertex.put("ViewA Oper2 Relation",
		// instViewAOper2);
		/*
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vaoper2-tooper", instEdge);
		 * instEdge.setIdentifier("vaoper2-tooper");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexOper, true);
		 * instEdge.setSourceRelation(instViewAOper2, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vaoper2-fromview", instEdge);
		 * instEdge.setIdentifier("vaoper2-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAOper2, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
		syntaxMetaChildView = new MetaView("StructuralAssets",
				"Structural Assets Relations", "Assets Palette", 2, null);
		childView = new InstView("StructuralAssets", metaView,
				syntaxMetaChildView);
		// instView.addChildView(childView);
		// variabilityInstVertex.put("StructuralAssets", childView);

		// syntaxMetaChildView.addConcept(sOperationalization);
		// childView.addInstVertex(instVertexOper);
		childView.addInstVertex(instVertexAsset);

		// syntaxMetaView.addConcept(syntaxGroupDependency);
		// syntaxMetaChildView.addConcept(syntaxGroupDependency);

		// InstConcept instViewAsset3 = new InstConcept("View Asset3 Relation",
		// supportMetaViewPairwise, metaViewRel);
		// this.variabilityInstVertex.put("View Asset3 Relation",
		// instViewAsset3);

		/*
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vasset3-toasset", instEdge);
		 * instEdge.setIdentifier("vasset3-toasset");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instVertexAsset, true);
		 * instEdge.setSourceRelation(instViewAsset3, true);
		 * 
		 * instEdge = new InstPairwiseRelation();
		 * this.constraintInstEdges.put("vasset3-fromview", instEdge);
		 * instEdge.setIdentifier("vasset3-fromview");
		 * instEdge.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
		 * instEdge.setTargetRelation(instViewAsset3, true);
		 * instEdge.setSourceRelation(childView, true);
		 */
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
								.equals("ExtendRelation")) {
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
								.equals("ExtendRelation")) {
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
								.equals("ExtendRelation")) {
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
								.equals("ExtendRelation")) {
					return (getValidMetaPairwiseRelation(element
							.getTargetRelations().get(0).getTargetRelations()
							.get(0).getTargetRelations().get(0), instElement2,
							metaPairwiseIden, true));
				}
		}
		return null;
	}

	public List<InstElement> getParentSyntaxConcept(InstElement instElement) {
		if (instElement == null)
			return null;
		InstElement instSyntaxElement = this.getSyntaxRefas().getVertex(
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
							.equals("ExtendRelation")) {
				InstElement parent = element.getTargetRelations().get(0)
						.getTargetRelations().get(0).getTargetRelations()
						.get(0);
				// parent.createInstAttributes(parents);
				out.add(parent);
				out.addAll(getRecursiveParentSyntaxConcept(element
						.getTargetRelations().get(0).getTargetRelations()
						.get(0).getTargetRelations().get(0)));
			}
		}
		return out;
	}

	public void updateValidationLists(InstElement elm, InstElement instSource,
			InstElement instTarget, List<InstElement> parents) {
		List<InstAttribute> visible = elm.getVisibleVariables(parents);
		InstPairwiseRelation instPairwise = (InstPairwiseRelation) elm;
		if (instSource == null && instPairwise.getSourceRelations().size() > 0)
			instSource = instPairwise.getSourceRelations().get(0);
		if (instTarget == null && instPairwise.getTargetRelations().size() > 0)
			instTarget = instPairwise.getTargetRelations().get(0);
		for (InstAttribute v : visible) {
			Map<String, MetaElement> mapElements = null;
			if (elm instanceof InstPairwiseRelation && instSource != null
					&& instSource.getTransSupportMetaElement() != null) {
				mapElements = getSyntaxRefas().getValidPairwiseRelations(
						instSource, instTarget);
			}
			v.updateValidationList((InstElement) elm, mapElements);
		}
	}

	public boolean elementsValidation(String element, int modelViewInd,
			int modelViewSubInd) {

		List<InstElement> views = this.getVariabilityVertex("View");
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