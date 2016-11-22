package com.variamos.dynsup.instance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.types.AttributeType;

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
public class InstPairwiseRel extends InstElement {

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
	VAR_INSTATTRIBUTES = "InstAtt",
	/**
	 * Name of the string identifier of MetaPairwiseRelation
	 */
	VAR_METAPAIRWISE_IDEN = "MetaPaiwiseIde",

	VAR_METAPAIRWISE = "MetaPaiwise",
	/**
	 * Name of the MetaPairwiseRelation object for both semantic and instance
	 * objects
	 */
	VAR_METAPAIRWISE_NAME = "Meta Relation",
	/**
	 * Canonical class name of MetaPairwiseRelation
	 */
	VAR_METAPAIRWISE_CLASS = SyntaxElement.class.getCanonicalName(),
	/**
					 * 
					 */
	VAR_OPERSPAIRWISE_OBJ_IDEN = "opersPairwiseIde",
	/**
					 * 
					 */
	VAR_OPERSPAIRWISE_OBJ = "opersPairwise";
	/**
	 * Display Name for MetaPairwiseRelation instance object
	 */

	private String semPairwiseDepOld = "";

	public InstPairwiseRel() {
		this(new HashMap<String, InstAttribute>(), null);
		createAttributes(new HashMap<String, InstAttribute>());
	}

	public InstPairwiseRel(OpersElement editableSemanticElement) {
		super(null);
		setEdOperEle(editableSemanticElement);
		createAttributes(new HashMap<String, InstAttribute>());
	}

	public InstPairwiseRel(InstPairwiseRel supportMetaPairwiseRelation,
			String supportInstPairwiseRelationIden,
			OpersElement editableSemanticElement) {
		super(null);
		setSupportMetaPairwiseRelation(supportMetaPairwiseRelation);
		setEdOperEle(editableSemanticElement);
		createAttributes(new HashMap<String, InstAttribute>());
	}

	/**
	 * Constructor for syntax element relations, not for modeling
	 * 
	 * @param editableMetaElement
	 *            : Only for syntax refas, not for modeling
	 */
	public InstPairwiseRel(SyntaxElement editableMetaElement) {
		super(null);
		setEdSyntaxEle(editableMetaElement);
		createAttributes(new HashMap<String, InstAttribute>());
	}

	/**
	 * Constructor for syntax element relations, not for modeling
	 * 
	 * @param editableMetaElement
	 *            : Only for syntax refas, not for modeling
	 */
	public InstPairwiseRel(InstPairwiseRel supportMetaPairwiseRelation,
			SyntaxElement editableMetaElement) {
		super(null);
		setEdSyntaxEle(editableMetaElement);

		createAttributes(new HashMap<String, InstAttribute>());
		setSupportMetaPairwiseRelation(supportMetaPairwiseRelation);
	}

	// TODO restrict available relation types according to syntax definition
	public InstPairwiseRel(Map<String, InstAttribute> instAttributes,
			String supportMetaPairwiseRelIden) {
		super(null); // TODO use the same identifier, not a local attribute
		createAttributes(instAttributes);
		this.supportMetaPairwiseRelIden = supportMetaPairwiseRelIden;
	}

	public InstPairwiseRel(String identifier, SyntaxElement editableMetaElement) {
		super(identifier);
		setEdSyntaxEle(editableMetaElement);
		createAttributes(new HashMap<String, InstAttribute>());

	}

	public String getSupportMetaPairwiseRelIden() {
		return supportMetaPairwiseRelIden;
	}

	public String getSemanticPairwiseRelType() {
		if (getInstAttribute("relationType") != null
				&& getInstAttribute("relationType").getValue() != null)
			if ((semanticPairwiseRelType != null
					&& !semanticPairwiseRelType.equals(getInstAttribute(
							"relationType").getValue()) || semanticPairwiseRelType == null))
				semanticPairwiseRelType = ((String) getInstAttribute(
						"relationType").getValue()).trim();
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

	public ElemAttribute getSemanticAttribute() {
		return new ElemAttribute(VAR_METAPAIRWISE, "Class",
				AttributeType.OPERATION, true, VAR_METAPAIRWISE_NAME, "",
				VAR_METAPAIRWISE_CLASS, new SyntaxElement('P'), 0, 2, "", "",
				-1, "", "");
	}

	public void createAttributes(Map<String, InstAttribute> instAttributes) {
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_INSTATTRIBUTES, instAttributes);
		ElemAttribute semAttribute = getSemanticAttribute();
		// Add the semanticAttribute
		// dynamicAttributesMap.put(VAR_METAPAIRWISE, semAttribute);
		dynamicAttributesMap.put(VAR_METAPAIRWISE_IDEN, "");
		addInstAttribute(VAR_METAPAIRWISE, semAttribute, "");

		// Add the InstAttribute initially empty
		dynamicAttributesMap.put(VAR_OPERSPAIRWISE_OBJ_IDEN, "");
	}

	public void setSupportMetaPairwiseRelation(InstElement metaEdge) {
		getInstAttribute(VAR_METAPAIRWISE).setValueObject(
				metaEdge.getEdSyntaxEle());
		if (metaEdge.getEdSyntaxEle() != null) {
			supportMetaPairwiseRelIden = metaEdge.getEdSyntaxEle()
					.getAutoIdentifier();
			setDynamicVariable("relationType", metaEdge.getEdSyntaxEle()
					.getAutoIdentifier());
			setDynamicVariable(SyntaxElement.VAR_DESCRIPTION, metaEdge
					.getEdSyntaxEle().getDescription());
		} else
			System.out.println("no meta element");
		createInstAttributes(null);
	}

	public void createInstAttributes() {
		if (getMetaPairwiseRelation() != null) {
			Iterator<String> modelingAttributes = getMetaPairwiseRelation()
					.getModelingAttributesNames(null).iterator();
			while (modelingAttributes.hasNext()) {
				String name = modelingAttributes.next();
				if (name.equals(SyntaxElement.VAR_AUTOIDENTIFIER))
					addInstAttribute(name, getMetaPairwiseRelation()
							.getModelingAttribute(name, null), getIdentifier());
				else if (name.equals(SyntaxElement.VAR_USERIDENTIFIER))
					addInstAttribute(name, getMetaPairwiseRelation()
							.getModelingAttribute(name, null),
							getUserIdentifier());
				else if (name.equals(SyntaxElement.VAR_DESCRIPTION))
					addInstAttribute(name, getMetaPairwiseRelation()
							.getModelingAttribute(name, null),
							getMetaPairwiseRelation().getDescription());
				else if (getInstAttribute(name) == null
						|| getInstAttribute(name).getValue() == null)
					addInstAttribute(name, getMetaPairwiseRelation()
							.getModelingAttribute(name, null),
							semanticPairwiseRelType);
			}

			if (getTransSupportMetaElement() instanceof SyntaxElement
					&& getTransSupportMetaElement()
							.getTransInstSemanticElement() != null) {
				InstElement instElement = getTransSupportMetaElement()
						.getTransInstSemanticElement();
				Iterator<String> semanticAttributes = instElement
						.getAllAttributesNames(null).iterator();
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

	}

	@Override
	public void setSourceRelation(InstElement sourceRelation, boolean firstCall) {
		super.setSourceRelation(sourceRelation, firstCall);
	}

	@Override
	public void setTargetRelation(InstElement targetRelation, boolean firstCall) {
		super.setTargetRelation(targetRelation, firstCall);
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public void addInstAttribute(String name, ElemAttribute modelingAttribute,
			Object value) {
		if (getInstAttribute(name) == null) {
			InstAttribute instAttribute = new InstAttribute(name,
					modelingAttribute,
					value == null ? modelingAttribute.getDefaultValue() : value);
			getInstAttributes().put(name, instAttribute);
			// instAttributes.put(name, instAttribute);
		}
	}

	@Override
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public InstElement getTransSupInstElement() {
		return new InstPairwiseRel(getMetaPairwiseRelation());
	}

	public SyntaxElement getMetaPairwiseRelation() {
		if (getInstAttribute(VAR_METAPAIRWISE) != null)
			return (SyntaxElement) (getInstAttribute(VAR_METAPAIRWISE)
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

	/*
	 * @Override public List<InstAttribute> getEditableVariables(
	 * List<InstElement> syntaxParents) { createInstAttributes(syntaxParents);
	 * // return new InstAttribute[0]; List<InstAttribute>
	 * editableInstAttributes = null; if (getMetaPairwiseRelation() != null) {
	 * Set<String> attributesNames =
	 * getDisPropEditableAttributes(syntaxParents); editableInstAttributes =
	 * getFilteredInstAttributes(attributesNames, null); } else {
	 * editableInstAttributes = new ArrayList<InstAttribute>();
	 * editableInstAttributes .add(getInstAttribute(VAR_METAPAIRWISE_OBJ_IDEN));
	 * } return editableInstAttributes; }
	 */

	@Override
	public List<InstAttribute> getVisibleVariables(
			List<InstElement> syntaxParents) {
		createInstAttributes(syntaxParents);
		// return new InstAttribute[0];
		List<InstAttribute> visibleInstAttributes = null;
		if (getMetaPairwiseRelation() != null) {
			Set<String> attributesNames = getDisPropVisibleAttributes(syntaxParents);
			visibleInstAttributes = getFilteredInstAttributes(attributesNames,
					null);
		} else {
			visibleInstAttributes = new ArrayList<InstAttribute>();
			visibleInstAttributes.add(getInstAttribute(VAR_METAPAIRWISE));
			;
		}
		return visibleInstAttributes;
	}

	@Override
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
		return (Map<String, InstAttribute>) getDynamicAttribute(VAR_INSTATTRIBUTES);
	}

	@Override
	@SuppressWarnings("unchecked")
	public InstAttribute getInstAttribute(String name) {
		return ((Map<String, InstAttribute>) getDynamicAttribute(VAR_INSTATTRIBUTES))
				.get(name);
		// return instAttributes.get(name);
	}

	public Set<String> getDisPropEditableAttributes(List<InstElement> parents) {
		if (getMetaPairwiseRelation() == null)
			return new HashSet<String>();
		Set<String> editableAttributes = getMetaPairwiseRelation()
				.getPropEditableAttributesSet(parents);

		if (getInstAttribute(VAR_OPERSPAIRWISE_OBJ) != null
				&& getInstAttribute(VAR_OPERSPAIRWISE_OBJ).getValueObject() != null) {
			OpersConcept semanticRelation = (OpersConcept) getInstAttribute(
					VAR_OPERSPAIRWISE_OBJ).getValueObject();
			// FIXME how pass parent to opersconcept
			editableAttributes.addAll(semanticRelation
					.getPropEditableAttributes(null));
		}

		editableAttributes.add("02#" + VAR_METAPAIRWISE);

		return editableAttributes;
	}

	public OpersConcept getSemanticEdge() {
		if (getInstAttribute(VAR_OPERSPAIRWISE_OBJ) != null
				&& getInstAttribute(VAR_OPERSPAIRWISE_OBJ).getValueObject() != null) {
			return (OpersConcept) getInstAttribute(VAR_OPERSPAIRWISE_OBJ)
					.getValueObject();
		}
		return null;
	}

	public Set<String> getDisPropVisibleAttributes(List<InstElement> parents) {
		Set<String> editableAttributes = getMetaPairwiseRelation()
				.getPropVisibleAttributesSet(parents);

		if (getInstAttribute(VAR_OPERSPAIRWISE_OBJ) != null
				&& getInstAttribute(VAR_OPERSPAIRWISE_OBJ).getValueObject() != null) {
			OpersConcept semanticRelation = (OpersConcept) getInstAttribute(
					VAR_OPERSPAIRWISE_OBJ).getValueObject();
			// FIXME how to get the parents for this opersconcept
			editableAttributes.addAll(semanticRelation
					.getPropVisibleAttributes(null));
		}

		editableAttributes.add("02#" + VAR_METAPAIRWISE);

		return editableAttributes;
	}

	@Override
	public List<InstAttribute> getVisibleAttributes(
			List<InstElement> syntaxParents) {
		SyntaxElement supportElement = getMetaPairwiseRelation();
		List<InstAttribute> visibleAttributes = new ArrayList<InstAttribute>();

		if (supportElement != null) {
			Set<String> attributes = supportElement
					.getAllAttributesNames(syntaxParents);
			List<String> visibleAttributesNames = new ArrayList<String>();

			for (String attributeName : attributes) {
				ElemAttribute attribute = supportElement.getAbstractAttribute(
						attributeName, syntaxParents, null);

				if (attribute.getPropTabPosition() != -1)
					visibleAttributesNames.add(attribute
							.getPropTabPositionStr() + attribute.getName());
			}
			Collections.sort(visibleAttributesNames);
			for (String attributeName : visibleAttributesNames) {
				InstAttribute attribute = getInstAttribute(attributeName
						.substring(2));

				if (attribute != null)
					visibleAttributes.add(attribute);
			}
		}
		return visibleAttributes;
	}

	@Deprecated
	public Set<String> getDisPanelVisibleAttributes(List<InstElement> parents) {
		Set<String> editableAttributes = getMetaPairwiseRelation()
				.getPanelVisibleAttributesSet(parents);

		if (getInstAttribute(VAR_OPERSPAIRWISE_OBJ) != null
				&& getInstAttribute(VAR_OPERSPAIRWISE_OBJ).getValueObject() != null) {
			OpersConcept semanticRelation = (OpersConcept) getInstAttribute(
					VAR_OPERSPAIRWISE_OBJ).getValueObject();
			editableAttributes.addAll(semanticRelation
					.getPanelVisibleAttributes(null));
		}
		return editableAttributes;
	}

	@Deprecated
	public Set<String> getDisPanelSpacersAttributes(List<InstElement> parents) {
		Set<String> editableAttributes = getMetaPairwiseRelation()
				.getPanelSpacersAttributesSet(parents);

		if (getInstAttribute(VAR_OPERSPAIRWISE_OBJ) != null
				&& getInstAttribute(VAR_OPERSPAIRWISE_OBJ).getValueObject() != null) {
			OpersConcept semanticRelation = (OpersConcept) getInstAttribute(
					VAR_OPERSPAIRWISE_OBJ).getValueObject();
			editableAttributes.addAll(semanticRelation
					.getPanelSpacersAttributes(null));
		}
		return editableAttributes;
	}

	@Override
	public String toString() {
		return getText(null);
	}

	@Override
	public String getText(List<InstElement> parents) {// TODO move to superclass
		return panelVisible(parents, getMetaPairwiseRelation());

		/*
		 * String out = ""; // List<String> visibleAttributesNames = metaConcept
		 * // .getPanelVisibleAttributes();
		 * 
		 * if (getMetaPairwiseRelation() != null) { Set<String>
		 * visibleAttributesNames = getDisPanelVisibleAttributes(parents);
		 * List<String> listVisibleAttributes = new ArrayList<String>();
		 * listVisibleAttributes.addAll(visibleAttributesNames);
		 * Collections.sort(listVisibleAttributes);
		 * 
		 * // List<String> spacersAttributes = metaConcept //
		 * .getPanelSpacersAttributes(); Set<String> spacersAttributes =
		 * getDisPanelSpacersAttributes(parents); for (String visibleAttribute :
		 * listVisibleAttributes) { boolean validCondition = true;
		 * 
		 * int nameEnd = visibleAttribute.indexOf("#", 3); int varEnd =
		 * visibleAttribute.indexOf("#", nameEnd + 1); int condEnd =
		 * visibleAttribute.indexOf("#", varEnd + 1);
		 * 
		 * String name = visibleAttribute.substring(3); // if
		 * (getInstAttributes().get(name) != null) { if (nameEnd != -1) { name =
		 * visibleAttribute.substring(3, nameEnd); String variable = null;
		 * String condition = null; String value = null; variable =
		 * visibleAttribute.substring(nameEnd + 1, varEnd); condition =
		 * visibleAttribute.substring(varEnd + 1, condEnd); value =
		 * visibleAttribute.substring(condEnd + 1); InstAttribute varValue =
		 * getInstAttributes().get(variable); if (varValue == null)
		 * validCondition = false; else if
		 * (varValue.getValue().toString().trim() .equals(value)) { if
		 * (condition.equals("!=")) validCondition = false; } else { if
		 * (condition.equals("==")) validCondition = false; } } boolean nvar =
		 * false; if (name != null && validCondition) { Iterator<String> spacers
		 * = spacersAttributes.iterator(); while (spacers.hasNext()) { String
		 * spacer = spacers.next(); if (spacer.indexOf("#" + name + "#") != -1)
		 * { nvar = true; int sp1 = spacer.indexOf("#"); int sp2 =
		 * spacer.indexOf("#", sp1 + 1);
		 * 
		 * out += spacer.substring(0, sp1); if (name.equals("relationType") &&
		 * getInstAttributes().get(name) != null &&
		 * getInstAttributes().get(name) .getValueObject() != null &&
		 * getInstAttributes().get(name) .getValueObject() instanceof
		 * InstAttribute) { InstAttribute att = (InstAttribute)
		 * getInstAttributes() .get(name).getValueObject(); String[] atts =
		 * ((String) att .getInstAttributeAttribute("Value")) .split("#"); out
		 * += atts[1]; } else if (getInstAttributes().get(name) != null) out +=
		 * getInstAttributes().get(name).toString() .trim(); while (sp2 !=
		 * spacer.length()) { int sp3 = spacer.indexOf("#", sp2 + 1); if (sp3 ==
		 * -1) {
		 * 
		 * out += spacer.substring(sp2 + 1); break; } out +=
		 * spacer.substring(sp2 + 1, sp3);
		 * 
		 * sp2 = sp3; } }
		 * 
		 * } if (!nvar) out += getInstAttributes().get(name); } // } else { //
		 * System.err.println(name + " attribute is null"); // } } } return out;
		 */
	}

	public void updateIdentifiers() {
		Object metaEdge = getInstAttribute(VAR_METAPAIRWISE).getValueObject();
		if (metaEdge != null) {
			supportMetaPairwiseRelIden = ((SyntaxElement) metaEdge)
					.getAutoIdentifier();
		}
		if (getInstAttribute("relationType") != null
				&& (String) getInstAttribute("relationType").getValue() != null)
			semanticPairwiseRelType = ((String) getInstAttribute("relationType")
					.getValue()).trim();

	}

	public void setSemanticEdge(OpersConcept semanticEdgeIde2) {
		getInstAttribute(VAR_OPERSPAIRWISE_OBJ)
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
	public SyntaxElement getTransSupportMetaElement() {
		return getMetaPairwiseRelation();
	}

	@Override
	public void setTransSupInstElement(InstElement supportMetaElement) {
		this.setSupSyntaxEleId(supportMetaElement.getEdSyntaxEle()
				.getAutoIdentifier());
		this.clearInstAttributes();
		HashMap<String, InstAttribute> map = new HashMap<String, InstAttribute>();
		createAttributes(map);
		setSupportMetaPairwiseRelation(supportMetaElement);
	}

	public void setUpdatePairwiseRelationType() {
		setDynamicVariable("relationType", semanticPairwiseRelType);

	}

	public void clearMetaPairwiseRelation() {
		super.clearMetaPairwiseRelation(VAR_METAPAIRWISE);
	}
}
