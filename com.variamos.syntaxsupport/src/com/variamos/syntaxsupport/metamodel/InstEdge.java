package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cfm.productline.Prototype;
import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaDirectRelation;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metametamodel.MetaElement;
import com.variamos.syntaxsupport.metametamodel.MetaGroupDependency;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticEdge;

/**
 * A class to represented modeling instances of edges from meta model MetaEdge
 * class on VariaMos. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.syntaxsupport.metametamodel.MetaEdge
 */
public class InstEdge implements Serializable, Prototype, EditableElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6134025886276124795L;
	/**
	 * Unique identifier of the InstEdge
	 */
	private String identifier;
	/**
	 * IntVertex origin of the direct relation
	 */
	private InstVertex fromRelation;
	/**
	 * IntVertex destination of the direct relation
	 */
	private InstVertex toRelation;

	private String metaEdgeIde;

	private String semanticEdgeIde;

	public String getMetaEdgeIde() {
		return metaEdgeIde;
	}

	public void setMetaEdgeIde(String metaEdgeIde) {
		this.metaEdgeIde = metaEdgeIde;
	}

	public String getSemanticEdgeIde() {
		return semanticEdgeIde;
	}

	public void setSemanticEdgeIde(String semanticEdgeIde) {
		this.semanticEdgeIde = semanticEdgeIde;
	}

	public static final String
	/**
	 * Name of the string identifier of MetaEdge
	 */
	VAR_METAEDGEIDE = "MetaEdgeIde",
	/**
	 * Name of the MetaEdge object for both semantic and instance objects
	 */
	VAR_METAEDGE = "MetaEdge",
	/**
	 * Display Name for MetaEdge instance object
	 */
	VAR_METAEDGENAME = "Type of Relation",
	/**
	 * Name of InstAttributes variable
	 */
	VAR_INSTATTRIBUTES = "InstAttribute",
	/**
	 * Canonical class name of MetaEdge
	 */
	VAR_METAEDGECLASS = MetaEdge.class.getCanonicalName();
	/**
	 * Dynamic storage of modeling, semantic and simulation instance attribute
	 * instances
	 */
	protected Map<String, Object> vars = new HashMap<>();

	public InstEdge() {
		this(new HashMap<String, InstAttribute>());
	}

	public InstEdge(Map<String, InstAttribute> instAttributes) {
		vars.put(VAR_INSTATTRIBUTES, instAttributes);
		SemanticAttribute semAttribute = new SemanticAttribute(VAR_METAEDGE,
				"Class", true, VAR_METAEDGENAME, VAR_METAEDGECLASS, "", "");
		// Add the semanticAttribute
		vars.put(VAR_METAEDGE, semAttribute);

		// Add the InstAttribute initially empty
		vars.put(VAR_METAEDGEIDE, "");
		addInstAttribute(VAR_METAEDGE, semAttribute, "");
	}

	public void setMetaEdge(MetaEdge metaEdge) {
		getInstAttribute(VAR_METAEDGE).setValueObject(metaEdge);
		metaEdgeIde = metaEdge.getIdentifier();
		setVariable(MetaElement.VAR_DESCRIPTION, metaEdge.getDescription());
		createInstAttributes();
	}

	public void createInstAttributes() {
		if (getMetaEdge() != null) {
			Iterator<String> modelingAttributes = getMetaEdge()
					.getModelingAttributes().iterator();
			while (modelingAttributes.hasNext()) {
				String name = modelingAttributes.next();
				if (name.equals(MetaElement.VAR_IDENTIFIER))
					addInstAttribute(name,
							getMetaEdge().getModelingAttribute(name),
							getIdentifier());
				else if (name.equals(MetaElement.VAR_DESCRIPTION))
					addInstAttribute(name,
							getMetaEdge().getModelingAttribute(name),
							getMetaEdge().getDescription());
				else if (getInstAttribute(name) == null
						|| getInstAttribute(name).getValue() == null)
					addInstAttribute(name,
							getMetaEdge().getModelingAttribute(name), null);
			}

			if (getInstAttribute(MetaDirectRelation.VAR_SEMANTICDIRECTRELATION)
					.getValueObject() != null) {
				IntDirectSemanticEdge sementicRelation = (IntDirectSemanticEdge) getInstAttribute(
						MetaDirectRelation.VAR_SEMANTICDIRECTRELATION)
						.getValueObject();
				Iterator<String> semanticAttributes = sementicRelation
						.getSemanticAttributes().iterator();
				while (semanticAttributes.hasNext()) {
					String name = semanticAttributes.next();
					if (name.equals("identifier"))
						addInstAttribute(name,
								sementicRelation.getSemanticAttribute(name),
								getIdentifier());
					else
						addInstAttribute(name,
								sementicRelation.getSemanticAttribute(name),
								null);
				}
			}
		}

	}

	public void setSourceRelation(InstVertex fromRelation) {
		this.fromRelation = fromRelation;
	}

	public void setTargetRelation(InstVertex toRelation) {
		this.toRelation = toRelation;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getMetaEdgeIdentifier() {
		return metaEdgeIde;
	}

	public void setMetaEdgeIdentifier(String str) {
		vars.put(VAR_METAEDGEIDE, str);

	}

	public void addInstAttribute(String name,
			AbstractAttribute modelingAttribute, Object value) {
		if (getInstAttribute(name) == null) {
			InstAttribute instAttribute = new InstAttribute(name,
					modelingAttribute,
					value == null ? modelingAttribute.getDefaultValue() : value);
			getInstAttributes().put(name, instAttribute);
			// instAttributes.put(name, instAttribute);
		}
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public MetaEdge getMetaEdge() {
		if (getInstAttribute(VAR_METAEDGE) != null)
			return (MetaEdge) (getInstAttribute(VAR_METAEDGE).getValueObject());
		return null;
	}

	public InstVertex getFromRelation() {
		return fromRelation;
	}

	public InstVertex getToRelation() {
		return toRelation;
	}

	@Override
	public List<InstAttribute> getEditableVariables() {
		createInstAttributes();
		// return new InstAttribute[0];
		List<InstAttribute> editableInstAttributes = null;
		if (getMetaEdge() != null) {
			Set<String> attributesNames = getDisPropEditableAttributes();
			editableInstAttributes = getFilteredInstAttributes(attributesNames,
					null);
		} else {
			editableInstAttributes = new ArrayList<InstAttribute>();
			editableInstAttributes.add(getInstAttribute(VAR_METAEDGE));
		}
		return editableInstAttributes;
	}

	@Override
	public List<InstAttribute> getVisibleVariables() {
		createInstAttributes();
		// return new InstAttribute[0];
		List<InstAttribute> visibleInstAttributes = null;
		if (getMetaEdge() != null) {
			Set<String> attributesNames = getDisPropVisibleAttributes();
			visibleInstAttributes = getFilteredInstAttributes(attributesNames,
					null);
		} else {
			visibleInstAttributes = new ArrayList<InstAttribute>();
			visibleInstAttributes.add(getInstAttribute(VAR_METAEDGE));
			;
		}
		return visibleInstAttributes;
	}

	public List<InstAttribute> getFilteredInstAttributes(
			Set<String> attributesNames, List<InstAttribute> instAttributes) {
		List<String> listEditableAttributes = new ArrayList<String>();
		listEditableAttributes.addAll(attributesNames);
		Collections.sort(listEditableAttributes);

		List<String> listEditableAttribNames = new ArrayList<String>();
		for (String attribute : listEditableAttributes) {
			int nameEnd = attribute.indexOf("#", 3);
			int varEnd = attribute.indexOf("#", nameEnd + 1);
			int condEnd = attribute.indexOf("#", varEnd + 1);
			if (nameEnd != -1)
			{
				String variable = null;
				String condition = null;
				String value = null;
				variable = attribute.substring(nameEnd + 1, varEnd);
				condition = attribute.substring(varEnd + 1, condEnd);
				value = attribute.substring(condEnd + 1);
				InstAttribute varValue = getInstAttributes().get(variable);
				if (varValue == null || varValue.getValue() == null)
					continue;
				else if (varValue.getValue().toString().trim().equals(value)) {
					if (condition.equals("!="))
						continue;
				} else {
					if (condition.equals("=="))
						continue;
				}
			
				listEditableAttribNames.add(attribute.substring(3, nameEnd));
			}
			else
				listEditableAttribNames.add(attribute.substring(3));
		}

		List<InstAttribute> editableInstAttributes = new ArrayList<InstAttribute>();
		for (String attributeName : listEditableAttribNames) {
			editableInstAttributes.add(getInstAttribute(attributeName));
		}
		return editableInstAttributes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, InstAttribute> getInstAttributes() {
		return (Map<String, InstAttribute>) getVariable(VAR_INSTATTRIBUTES);
	}

	@SuppressWarnings("unchecked")
	public InstAttribute getInstAttribute(String name) {
		return ((Map<String, InstAttribute>) getVariable(VAR_INSTATTRIBUTES))
				.get(name);
		// return instAttributes.get(name);
	}

	public Object getVariable(String name) {
		return vars.get(name);
	}

	public void setVariable(String name, Object value) {
		vars.put(name, value);
	}

	public void clearMetaEdge() {
		vars.put(VAR_METAEDGE, null);
	}

	public void clearInstAttributesClassObjects() {
		for (InstAttribute attribute : this.getInstAttributes().values()) {
			attribute.setValueObject(null);
		}
	}

	public Set<String> getDisPropEditableAttributes() {
		Set<String> editableAttributes = getMetaEdge()
				.getDisPropEditableAttributes();

		if (getInstAttribute(MetaDirectRelation.VAR_SEMANTICDIRECTRELATION) != null
				&& getInstAttribute(
						MetaDirectRelation.VAR_SEMANTICDIRECTRELATION)
						.getValueObject() != null) {
			IntDirectSemanticEdge semanticRelation = (IntDirectSemanticEdge) getInstAttribute(
					MetaDirectRelation.VAR_SEMANTICDIRECTRELATION)
					.getValueObject();
			editableAttributes.addAll(semanticRelation
					.getDisPropEditableAttributes());
		}

		editableAttributes.add("02#" + VAR_METAEDGE);

		return editableAttributes;
	}

	public Set<String> getDisPropVisibleAttributes() {
		Set<String> editableAttributes = getMetaEdge()
				.getDisPropVisibleAttributes();

		if (getInstAttribute(MetaDirectRelation.VAR_SEMANTICDIRECTRELATION) != null
				&& getInstAttribute(
						MetaDirectRelation.VAR_SEMANTICDIRECTRELATION)
						.getValueObject() != null) {
			IntDirectSemanticEdge semanticRelation = (IntDirectSemanticEdge) getInstAttribute(
					MetaDirectRelation.VAR_SEMANTICDIRECTRELATION)
					.getValueObject();
			editableAttributes.addAll(semanticRelation
					.getDisPropVisibleAttributes());
		}

		editableAttributes.add("02#" + VAR_METAEDGE);

		return editableAttributes;
	}

	public Set<String> getDisPanelVisibleAttributes() {
		Set<String> editableAttributes = getMetaEdge()
				.getDisPanelVisibleAttributes();

		if (getInstAttribute(MetaDirectRelation.VAR_SEMANTICDIRECTRELATION) != null
				&& getInstAttribute(
						MetaDirectRelation.VAR_SEMANTICDIRECTRELATION)
						.getValueObject() != null) {
			IntDirectSemanticEdge semanticRelation = (IntDirectSemanticEdge) getInstAttribute(
					MetaDirectRelation.VAR_SEMANTICDIRECTRELATION)
					.getValueObject();
			editableAttributes.addAll(semanticRelation
					.getDisPanelVisibleAttributes());
		}
		return editableAttributes;
	}

	public Set<String> getDisPanelSpacersAttributes() {
		Set<String> editableAttributes = getMetaEdge()
				.getDisPanelSpacersAttributes();

		if (getInstAttribute(MetaDirectRelation.VAR_SEMANTICDIRECTRELATION) != null
				&& getInstAttribute(
						MetaDirectRelation.VAR_SEMANTICDIRECTRELATION)
						.getValueObject() != null) {
			IntDirectSemanticEdge semanticRelation = (IntDirectSemanticEdge) getInstAttribute(
					MetaDirectRelation.VAR_SEMANTICDIRECTRELATION)
					.getValueObject();
			editableAttributes.addAll(semanticRelation
					.getDisPanelSpacersAttributes());
		}
		return editableAttributes;
	}

	public String toString() { // TODO move to superclass
		String out = "";
		// List<String> visibleAttributesNames = metaConcept
		// .getPanelVisibleAttributes();
		if (getMetaEdge() != null) {
			Set<String> visibleAttributesNames = getDisPanelVisibleAttributes();
			List<String> listVisibleAttributes = new ArrayList<String>();
			listVisibleAttributes.addAll(visibleAttributesNames);
			Collections.sort(listVisibleAttributes);

			// List<String> spacersAttributes = metaConcept
			// .getPanelSpacersAttributes();
			Set<String> spacersAttributes = getDisPanelSpacersAttributes();
			for (String visibleAttribute : listVisibleAttributes) {
				boolean validCondition = true;

				int nameEnd = visibleAttribute.indexOf("#", 3);
				int varEnd = visibleAttribute.indexOf("#", nameEnd + 1);
				int condEnd = visibleAttribute.indexOf("#", varEnd + 1);

				String name = visibleAttribute.substring(3);
				if (nameEnd != -1) {
					name = visibleAttribute.substring(3, nameEnd);
					String variable = null;
					String condition = null;
					String value = null;
					variable = visibleAttribute.substring(nameEnd + 1, varEnd);
					condition = visibleAttribute.substring(varEnd + 1, condEnd);
					value = visibleAttribute.substring(condEnd + 1);
					InstAttribute varValue = getInstAttributes().get(variable);
					if (varValue == null)
						validCondition = false;
					else if (varValue.getValue().toString().trim()
							.equals(value)) {
						if (condition.equals("!="))
							validCondition = false;
					} else {
						if (condition.equals("=="))
							validCondition = false;
					}
				}
				boolean nvar = false;
				if (name != null && validCondition) {
					Iterator<String> spacers = spacersAttributes.iterator();
					while (spacers.hasNext()) {
						String spacer = spacers.next();
						if (spacer.indexOf("#" + name + "#") != -1) {
							nvar = true;
							int sp1 = spacer.indexOf("#");
							int sp2 = spacer.indexOf("#", sp1 + 1);

							out += spacer.substring(0, sp1);
							out += getInstAttributes().get(name).toString()
									.trim();
							while (sp2 != spacer.length()) {
								int sp3 = spacer.indexOf("#", sp2 + 1);
								if (sp3 == -1) {

									out += spacer.substring(sp2 + 1);
									break;
								}
								out += spacer.substring(sp2 + 1, sp3);

								sp2 = sp3;
							}
						}

					}
					if (!nvar)
						out += getInstAttributes().get(name);
				}
			}
		}
		return out;
	}

	public void clearRelations() {
		fromRelation = null;
		toRelation = null;

	}

	public void loadSemantic() {
		Iterator<InstAttribute> ias = getInstAttributes().values().iterator();
		while (ias.hasNext()) {
			InstAttribute ia = (InstAttribute) ias.next();
			if (ia.getAttributeName().equals(
					MetaGroupDependency.VAR_SEMANTICGROUPDEPENDENCY)) {

				AbstractAttribute m = getMetaEdge().getModelingAttribute(
						InstGroupDependency.VAR_SEMANTICGROUPDEPENDENCY);
				ia.setAttribute(m);
				/*
				 * List<IntSemanticGroupDependency> semGD =
				 * ((MetaGroupDependency) getMetaEdge())
				 * .getSemanticRelations();
				 * 
				 * ia.setValidationGDList(semGD);
				 */
			} else {
				ia.setAttribute(this.getMetaEdge().getModelingAttribute(
						ia.getAttributeName()));
			}
		}
		createInstAttributes();
	}

	public void updateIdentifiers() {
		Object metaEdge = getInstAttribute(VAR_METAEDGE).getValueObject();
		if (metaEdge != null) {
			metaEdgeIde = ((MetaDirectRelation) metaEdge).getIdentifier();
		}
		if (getInstAttribute(MetaDirectRelation.VAR_SEMANTICDIRECTRELATION) != null) {
			Object semanticEdge = getInstAttribute(
					MetaDirectRelation.VAR_SEMANTICDIRECTRELATION)
					.getValueObject();
			if (semanticEdge != null) {
				semanticEdgeIde = ((IntDirectSemanticEdge) semanticEdge)
						.getIdentifier();
			}
		}
	}

	public void setSemanticEdge(IntDirectSemanticEdge semanticEdgeIde2) {
		getInstAttribute(MetaDirectRelation.VAR_SEMANTICDIRECTRELATION).setValueObject(semanticEdgeIde2);

	}

	public String getSourceInstAttributeIdentifier(String insAttributeId) {
	
		return getFromRelation().getIdentifier()+"_"+getFromRelation().getInstAttribute(insAttributeId).getIdentifier();
	}

	public String getTargetInstAttributeIdentifier(String insAttributeId) {
		return getToRelation().getIdentifier()+"_"+getToRelation().getInstAttribute(insAttributeId).getIdentifier();

	}
}
