package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;
import com.variamos.syntaxsupport.metamodelsupport.MetaPairwiseRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaOverTwoRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaPairwiseRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaVertex;
import com.variamos.syntaxsupport.metamodelsupport.SemanticAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticPairwiseRelation;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;

/**
 * Class to store the back end information of relations between two elements
 * from the graph. Part of PhD work at University of Paris 1 Refactor from:
 * InstEdge.
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24 * @see
 *        com.variamos.syntaxsupport.metamodelsupport.MetaPairwiseRelation
 */
public class InstPairwiseRelation extends InstElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6134025886276124795L;
	/**
	 * Unique identifier of the InstEdge
	 */
	private String identifier;

	private String supportMetaPairwiseRelIden;

	private String semanticPairwiseRelType;

	public static final String
	/**
	 * Name of InstAttributes variable
	 */
	VAR_INSTATTRIBUTES = "InstAttribute",
	/**
	 * Name of the string identifier of MetaPairwiseRelation
	 */
	VAR_METAPAIRWISE_IDEN = "MetaPaiwiseIde",
	/**
	 * Name of the MetaPairwiseRelation object for both semantic and instance
	 * objects
	 */
	VAR_METAPAIRWISE_OBJ_IDEN = "MetaPairwise",
	/**
	 * Display Name for MetaPairwiseRelation instance object
	 */
	VAR_METAPAIRWISE_OBJ_NAME = "Type of Relation",
	/**
	 * Canonical class name of MetaPairwiseRelation
	 */
	VAR_METAPAIRWISE_OBJ_CLASS = MetaPairwiseRelation.class.getCanonicalName();

	public InstPairwiseRelation() {
		this(new HashMap<String, InstAttribute>(), null);
		createAttributes(new HashMap<String, InstAttribute>());
	}

	public InstPairwiseRelation(IntSemanticElement editableSemanticElement) {
		super(null);
		setEditableSemanticElement(editableSemanticElement);
		createAttributes(new HashMap<String, InstAttribute>());
	}

	public InstPairwiseRelation(
			MetaPairwiseRelation supportMetaPairwiseRelation,
			String supportInstPairwiseRelationIden,
			IntSemanticElement editableSemanticElement) {
		super(null);
		setSupportMetaPairwiseRelation(supportMetaPairwiseRelation);
		setEditableSemanticElement(editableSemanticElement);
		createAttributes(new HashMap<String, InstAttribute>());
	}

	/**
	 * Constructor for syntax element relations, not for modeling
	 * 
	 * @param editableMetaElement
	 *            : Only for syntax refas, not for modeling
	 */
	public InstPairwiseRelation(MetaElement editableMetaElement) {
		super(null);
		setEditableMetaElement(editableMetaElement);
		createAttributes(new HashMap<String, InstAttribute>());
	}

	/**
	 * Constructor for syntax element relations, not for modeling
	 * 
	 * @param editableMetaElement
	 *            : Only for syntax refas, not for modeling
	 */
	public InstPairwiseRelation(
			MetaPairwiseRelation supportMetaPairwiseRelation,
			MetaElement editableMetaElement) {
		super(null);
		setEditableMetaElement(editableMetaElement);

		createAttributes(new HashMap<String, InstAttribute>());
		setSupportMetaPairwiseRelation(supportMetaPairwiseRelation);
	}

	// TODO restrict available relation types according to syntax definition
	public InstPairwiseRelation(Map<String, InstAttribute> instAttributes,
			String supportMetaPairwiseRelIden) {
		super(null); // TODO use the same identifier, not a local attribute
		createAttributes(instAttributes);
		this.supportMetaPairwiseRelIden = supportMetaPairwiseRelIden;
	}

	public String getSupportMetaPairwiseRelIden() {
		return supportMetaPairwiseRelIden;
	}

	public String getSemanticPairwiseRelType() {
		if (getInstAttribute("relationType") != null && getInstAttribute(
				"relationType").getValue() != null)
			if( (semanticPairwiseRelType != null
						&& !semanticPairwiseRelType.equals(getInstAttribute(
								"relationType").getValue()) || semanticPairwiseRelType == null))
			semanticPairwiseRelType = ((String) getInstAttribute("relationType")
					.getValue()).trim();
		return semanticPairwiseRelType;
	}

	public void setSemanticPairwiseRelType(String semanticRelationType) {
		this.semanticPairwiseRelType = semanticRelationType;
	}

	public void setSupportMetaPairwiseRelIden(String metaPairwiseRelationIden) {
		supportMetaPairwiseRelIden = metaPairwiseRelationIden;
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_METAPAIRWISE_IDEN,
				metaPairwiseRelationIden);
	}

	public SemanticAttribute getSemanticAttribute() {
		return new SemanticAttribute(VAR_METAPAIRWISE_OBJ_IDEN, "Class", true,
				VAR_METAPAIRWISE_OBJ_NAME, VAR_METAPAIRWISE_OBJ_CLASS, null,
				"");

	}

	public void createAttributes(Map<String, InstAttribute> instAttributes) {
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_INSTATTRIBUTES, instAttributes);
		SemanticAttribute semAttribute = getSemanticAttribute();
		// Add the semanticAttribute
		dynamicAttributesMap.put(VAR_METAPAIRWISE_OBJ_IDEN, semAttribute);

		// Add the InstAttribute initially empty
		dynamicAttributesMap.put(VAR_METAPAIRWISE_IDEN, "");
		addInstAttribute(VAR_METAPAIRWISE_OBJ_IDEN, semAttribute, "");
	}

	public void setSupportMetaPairwiseRelation(MetaElement metaEdge) {
		getInstAttribute(VAR_METAPAIRWISE_OBJ_IDEN).setValueObject(metaEdge);
		supportMetaPairwiseRelIden = metaEdge.getIdentifier();
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				metaEdge.getDescription());
		setDynamicVariable("relationType", metaEdge.getIdentifier());
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				metaEdge.getDescription());
		createInstAttributes();
	}

	public void createInstAttributes() {
		if (getMetaPairwiseRelation() != null) {
			Iterator<String> modelingAttributes = getMetaPairwiseRelation()
					.getModelingAttributesNames().iterator();
			while (modelingAttributes.hasNext()) {
				String name = modelingAttributes.next();
				if (name.equals(MetaElement.VAR_IDENTIFIER))
					addInstAttribute(name, getMetaPairwiseRelation()
							.getModelingAttribute(name), getIdentifier());
				else if (name.equals(MetaElement.VAR_DESCRIPTION))
					addInstAttribute(name, getMetaPairwiseRelation()
							.getModelingAttribute(name),
							getMetaPairwiseRelation().getDescription());
				else if (getInstAttribute(name) == null
						|| getInstAttribute(name).getValue() == null)
					addInstAttribute(name, getMetaPairwiseRelation()
							.getModelingAttribute(name),
							semanticPairwiseRelType);
			}

			Iterator<String> semanticAttributes = getMetaPairwiseRelation()
					.getSemanticAttributes().iterator();
			while (semanticAttributes.hasNext()) {
				String name = semanticAttributes.next();
				if (name.equals("identifier"))
					addInstAttribute(name, getMetaPairwiseRelation()
							.getSemanticAttribute(name), getIdentifier());
				else
					addInstAttribute(name, getMetaPairwiseRelation()
							.getSemanticAttribute(name), null);
			}
		}

	}

	public void setSourceRelation(InstElement sourceRelation, boolean firstCall) {
		super.setSourceRelation(sourceRelation, firstCall);
	}

	public void setTargetRelation(InstElement targetRelation, boolean firstCall) {
		super.setTargetRelation(targetRelation, firstCall);
	}

	public String getIdentifier() {
		return identifier;
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

	public MetaPairwiseRelation getMetaPairwiseRelation() {
		if (getInstAttribute(VAR_METAPAIRWISE_OBJ_IDEN) != null)
			return (MetaPairwiseRelation) (getInstAttribute(VAR_METAPAIRWISE_OBJ_IDEN)
					.getValueObject());
		return null;
	}

	// public InstElement getSourceRelation() {
	// return fromRelation;
	// }
	//
	// public InstElement getTargetRelation() {
	// return toRelation;
	// }

	@Override
	public List<InstAttribute> getEditableVariables() {
		createInstAttributes();
		// return new InstAttribute[0];
		List<InstAttribute> editableInstAttributes = null;
		if (getMetaPairwiseRelation() != null) {
			Set<String> attributesNames = getDisPropEditableAttributes();
			editableInstAttributes = getFilteredInstAttributes(attributesNames,
					null);
		} else {
			editableInstAttributes = new ArrayList<InstAttribute>();
			editableInstAttributes.add(getInstAttribute(VAR_METAPAIRWISE_OBJ_IDEN));
		}
		return editableInstAttributes;
	}

	@Override
	public List<InstAttribute> getVisibleVariables() {
		createInstAttributes();
		// return new InstAttribute[0];
		List<InstAttribute> visibleInstAttributes = null;
		if (getMetaPairwiseRelation() != null) {
			Set<String> attributesNames = getDisPropVisibleAttributes();
			visibleInstAttributes = getFilteredInstAttributes(attributesNames,
					null);
		} else {
			visibleInstAttributes = new ArrayList<InstAttribute>();
			visibleInstAttributes.add(getInstAttribute(VAR_METAPAIRWISE_OBJ_IDEN));
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
			if (nameEnd != -1) {
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
			} else
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
		return (Map<String, InstAttribute>) getDynamicVariable(VAR_INSTATTRIBUTES);
	}

	@SuppressWarnings("unchecked")
	public InstAttribute getInstAttribute(String name) {
		return ((Map<String, InstAttribute>) getDynamicVariable(VAR_INSTATTRIBUTES))
				.get(name);
		// return instAttributes.get(name);
	}

	public Set<String> getDisPropEditableAttributes() {
		Set<String> editableAttributes = getMetaPairwiseRelation()
				.getPropEditableAttributes();

		if (getInstAttribute(MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN) != null
				&& getInstAttribute(
						MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN)
						.getValueObject() != null) {
			IntSemanticPairwiseRelation semanticRelation = (IntSemanticPairwiseRelation) getInstAttribute(
					MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN)
					.getValueObject();
			editableAttributes.addAll(semanticRelation
					.getPropEditableAttributes());
		}

		editableAttributes.add("02#" + VAR_METAPAIRWISE_OBJ_IDEN);

		return editableAttributes;
	}

	public IntSemanticPairwiseRelation getSemanticEdge() {
		if (getInstAttribute(MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN) != null
				&& getInstAttribute(
						MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN)
						.getValueObject() != null) {
			return (IntSemanticPairwiseRelation) getInstAttribute(
					MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN)
					.getValueObject();
		}
		return null;
	}

	public Set<String> getDisPropVisibleAttributes() {
		Set<String> editableAttributes = getMetaPairwiseRelation()
				.getPropVisibleAttributes();

		if (getInstAttribute(MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN) != null
				&& getInstAttribute(
						MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN)
						.getValueObject() != null) {
			IntSemanticPairwiseRelation semanticRelation = (IntSemanticPairwiseRelation) getInstAttribute(
					MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN)
					.getValueObject();
			editableAttributes.addAll(semanticRelation
					.getPropVisibleAttributes());
		}

		editableAttributes.add("02#" + VAR_METAPAIRWISE_OBJ_IDEN);

		return editableAttributes;
	}

	public Set<String> getDisPanelVisibleAttributes() {
		Set<String> editableAttributes = getMetaPairwiseRelation()
				.getPanelVisibleAttributes();

		if (getInstAttribute(MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN) != null
				&& getInstAttribute(
						MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN)
						.getValueObject() != null) {
			IntSemanticPairwiseRelation semanticRelation = (IntSemanticPairwiseRelation) getInstAttribute(
					MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN)
					.getValueObject();
			editableAttributes.addAll(semanticRelation
					.getPanelVisibleAttributes());
		}
		return editableAttributes;
	}

	public Set<String> getDisPanelSpacersAttributes() {
		Set<String> editableAttributes = getMetaPairwiseRelation()
				.getPanelSpacersAttributes();

		if (getInstAttribute(MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN) != null
				&& getInstAttribute(
						MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN)
						.getValueObject() != null) {
			IntSemanticPairwiseRelation semanticRelation = (IntSemanticPairwiseRelation) getInstAttribute(
					MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN)
					.getValueObject();
			editableAttributes.addAll(semanticRelation
					.getPanelSpacersAttributes());
		}
		return editableAttributes;
	}

	public String toString() { // TODO move to superclass
		String out = "";
		// List<String> visibleAttributesNames = metaConcept
		// .getPanelVisibleAttributes();
		if (getMetaPairwiseRelation() != null) {
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
				if (getInstAttributes().get(name) != null) {
					if (nameEnd != -1) {
						name = visibleAttribute.substring(3, nameEnd);
						String variable = null;
						String condition = null;
						String value = null;
						variable = visibleAttribute.substring(nameEnd + 1,
								varEnd);
						condition = visibleAttribute.substring(varEnd + 1,
								condEnd);
						value = visibleAttribute.substring(condEnd + 1);
						InstAttribute varValue = getInstAttributes().get(
								variable);
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
				} else {
					System.err.println(name + " attribute is null");
				}
			}
		}
		return out;
	}

	public void loadSemantic() {
		Iterator<InstAttribute> ias = getInstAttributes().values().iterator();
		while (ias.hasNext()) {
			InstAttribute ia = (InstAttribute) ias.next();
			if (ia.getAttributeName().equals(
					MetaOverTwoRelation.VAR_SEMANTICPAIRWISEREL)) {

				AbstractAttribute m = getMetaPairwiseRelation()
						.getModelingAttribute(
								InstOverTwoRelation.VAR_SEMANTICOVERTWOREL_OBJ);
				ia.setAttribute(m);
				/*
				 * List<IntSemanticGroupDependency> semGD =
				 * ((MetaGroupDependency) getMetaEdge())
				 * .getSemanticRelations();
				 * 
				 * ia.setValidationGDList(semGD);
				 */
			} else {
				ia.setAttribute(this.getMetaPairwiseRelation()
						.getModelingAttribute(ia.getAttributeName()));
			}
		}
		createInstAttributes();
	}

	public void updateIdentifiers() {
		Object metaEdge = getInstAttribute(VAR_METAPAIRWISE_OBJ_IDEN)
				.getValueObject();
		if (metaEdge != null) {
			supportMetaPairwiseRelIden = ((MetaPairwiseRelation) metaEdge)
					.getIdentifier();
		}
		if (getInstAttribute("relationType") != null)
			semanticPairwiseRelType = ((String) getInstAttribute("relationType")
					.getValue()).trim();

	}

	public void setSemanticEdge(IntSemanticPairwiseRelation semanticEdgeIde2) {
		getInstAttribute(MetaPairwiseRelation.VAR_SEMANTICPAIRWISEREL_IDEN)
				.setValueObject(semanticEdgeIde2);

	}

	public String getSourceInstAttributeIdentifier(String insAttributeId) {

		return getSourceRelations().get(0).getIdentifier()
				+ "_"
				+ getSourceRelations().get(0).getInstAttribute(insAttributeId)
						.getIdentifier();
	}

	public String getTargetInstAttributeIdentifier(String insAttributeId) {
		return getTargetRelations().get(0).getIdentifier()
				+ "_"
				+ getTargetRelations().get(0).getInstAttribute(insAttributeId)
						.getIdentifier();

	}

	@Override
	public MetaElement getTransSupportMetaElement() {
		return getMetaPairwiseRelation();
	}

	@Override
	public void setTransSupportMetaElement(MetaElement supportMetaElement) {
		this.setSupportMetaElementIden(supportMetaElement.getIdentifier());
		setSupportMetaPairwiseRelation(supportMetaElement);
	}

	public void setUpdatePairwiseRelationType() {
		setDynamicVariable("relationType", semanticPairwiseRelType);

	}

	public void clearMetaPairwiseRelation() {
		super.clearMetaPairwiseRelation(VAR_METAPAIRWISE_OBJ_IDEN);
	}
}
