package com.variamos.perspsupport.instancesupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.perspsupport.opersint.IntOpersElement;
import com.variamos.perspsupport.opersint.IntOpersOverTwoRel;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaOverTwoRelation;
import com.variamos.perspsupport.syntaxsupport.MetaVertex;

/**
 * Class to store the back end information of relations between more than two
 * elements from the graph. Part of PhD work at University of Paris 1 Refactor
 * from: InstGroupDependency.
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-26
 */
public class InstOverTwoRelation extends InstVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7122291624405069534L;
	public static final String
	/**
	 * 
	 */
	VAR_OUTCARDINALITY = "outCardinalitiy",
	/**
			 * 
			 */
	VAR_INCARDINALITY = "inCardinalitiy",
	/**
					 * 
					 */
	VAR_METAOVERTWOREL_IDEN = "metaGroupDepIde",
	/**
					 * 
					 */
	VAR_SEMANTICOVERTWOREL_IDEN = "semGroupDepIde",
	/**
					 * 
					 */
	VAR_SEMANTICOVERTWOREL_OBJ = "semGroupDep";
	/**
	 * Assigned during the generation of expressions for
	 * SingleElementExpressionSet required during the generation of expressions
	 * for MultiElementExpressionSet
	 */
	private Set<String> sourcePositiveAttributeNames;

	private Set<String> sourceNegativeAttributeNames;

	/**
	 * TODO review if needed
	 */
	private String semGroupDepOld = "";

	public InstOverTwoRelation() {
		super();

		sourcePositiveAttributeNames = new HashSet<String>();
		sourceNegativeAttributeNames = new HashSet<String>();
		// vars.put(VAR_METAGROUPDEPIDENTIFIER,null);
		// vars.put(VAR_SEMANTICGROUPDEPENDENCYID,null);
		// vars.put(VAR_SEMANTICGROUPDEPENDENCY,null);
	}

	public InstOverTwoRelation(MetaOverTwoRelation metaOverTwoRelation) {
		super("");
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_METAOVERTWOREL_IDEN,
				metaOverTwoRelation.getAutoIdentifier());
		setTransSupportMetaElement(metaOverTwoRelation);
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				metaOverTwoRelation.getDescription());

		sourcePositiveAttributeNames = new HashSet<String>();
		sourceNegativeAttributeNames = new HashSet<String>();
		createInstAttributes();
	}

	public InstOverTwoRelation(String identifier,
			MetaOverTwoRelation supportMetaOvetTwoRelation,
			MetaElement editableMetaElement) {
		super(identifier);
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		setEditableMetaElement(editableMetaElement);
		if (supportMetaOvetTwoRelation != null) {
			dynamicAttributesMap.put(VAR_METAOVERTWOREL_IDEN,
					supportMetaOvetTwoRelation.getAutoIdentifier());
			setTransSupportMetaElement(supportMetaOvetTwoRelation);
			setDynamicVariable(MetaElement.VAR_DESCRIPTION,
					supportMetaOvetTwoRelation.getDescription());
		}
		sourcePositiveAttributeNames = new HashSet<String>();
		sourceNegativeAttributeNames = new HashSet<String>();
		createInstAttributes();
	}

	public InstOverTwoRelation(String identifier,
			MetaOverTwoRelation supportMetaOvetTwoRelation,
			IntOpersElement semanticElement) {
		super(identifier);
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		setEditableSemanticElement(semanticElement);
		dynamicAttributesMap.put(VAR_METAOVERTWOREL_IDEN,
				supportMetaOvetTwoRelation.getAutoIdentifier());
		setTransSupportMetaElement(supportMetaOvetTwoRelation);
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				supportMetaOvetTwoRelation.getDescription());

		sourcePositiveAttributeNames = new HashSet<String>();
		sourceNegativeAttributeNames = new HashSet<String>();
		createInstAttributes();
	}

	public InstOverTwoRelation(String identifier,
			MetaOverTwoRelation supportMetaOvetTwoRelation) {
		super(identifier);
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_METAOVERTWOREL_IDEN,
				supportMetaOvetTwoRelation.getAutoIdentifier());
		setTransSupportMetaElement(supportMetaOvetTwoRelation);
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				supportMetaOvetTwoRelation.getDescription());

		sourcePositiveAttributeNames = new HashSet<String>();
		sourceNegativeAttributeNames = new HashSet<String>();
		createInstAttributes();
	}

	protected void createInstAttributes() {
		if (getSupportMetaOverTwoRelation() != null) {
			for (String name : getSupportMetaOverTwoRelation()
					.getModelingAttributesNames(null)) {
				if (name.equals(MetaElement.VAR_AUTOIDENTIFIER))
					addInstAttribute(name, getSupportMetaOverTwoRelation()
							.getModelingAttribute(name, null), getIdentifier());
				else if (name.equals(MetaElement.VAR_DESCRIPTION))
					addInstAttribute(name, getSupportMetaOverTwoRelation()
							.getModelingAttribute(name, null),
							getSupportMetaOverTwoRelation().getDescription());
				else
					addInstAttribute(name, getSupportMetaOverTwoRelation()
							.getModelingAttribute(name, null), null);
			}

			Iterator<String> semanticAttributes = this
					.getTransSupportMetaElement().getAllAttributesNames(null)
					.iterator();
			while (semanticAttributes.hasNext()) {
				String name = semanticAttributes.next();
				if (name.equals("identifier"))
					addInstAttribute(name, getTransSupportMetaElement()
							.getSemanticAttribute(name), getIdentifier());
				else
					addInstAttribute(name, getTransSupportMetaElement()
							.getSemanticAttribute(name), null);
			}
		}
	}

	public Set<String> getSourcePositiveAttributeNames() {
		return sourcePositiveAttributeNames;
	}

	public Set<String> getSourceNegativeAttributeNames() {
		return sourceNegativeAttributeNames;
	}

	/**
	 * Add the name of the attributes for MultiElementExpressionSet
	 * 
	 * @param sourcePositiveAttributeNames
	 *            Name of attributes for double implication
	 */
	public void addSourcePositiveAttributeNames(
			Set<String> sourcePositiveAttributeNames) {
		this.sourcePositiveAttributeNames.addAll(sourcePositiveAttributeNames);
	}

	/**
	 * Add the name of the attributes for MultiElementExpressionSet
	 * 
	 * @param sourcePositiveAttributeNames
	 *            Name of attributes for double implication
	 */
	public void addSourceNegativeAttributeNames(
			Set<String> sourceNegativeAttributeNames) {
		this.sourceNegativeAttributeNames.addAll(sourceNegativeAttributeNames);
	}

	public void clearSourcePositiveAttributeNames() {
		this.sourcePositiveAttributeNames.clear();
	}

	public void clearSourceNegativeAttributeNames() {
		this.sourceNegativeAttributeNames.clear();
	}

	public MetaVertex getSupportMetaOverTwoRelation() {
		return getTransSupportMetaElement();
	}

	public String getSupportMetaElementUserIdentifier() {
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		return (String) dynamicAttributesMap.get(VAR_METAOVERTWOREL_IDEN);
	}

	public String getSemanticOverTwoRelationIden() {
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		if ((String) dynamicAttributesMap.get(VAR_SEMANTICOVERTWOREL_IDEN) == null)
			if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ).getValueObject() != null) {
				dynamicAttributesMap.put(
						VAR_SEMANTICOVERTWOREL_IDEN,
						((IntOpersOverTwoRel) getInstAttribute(
								VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
								.getIdentifier());
				return ((IntOpersOverTwoRel) getInstAttribute(
						VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
						.getIdentifier();
			}

			else
				return null;
		return (String) dynamicAttributesMap.get(VAR_SEMANTICOVERTWOREL_IDEN);
	}

	public IntOpersOverTwoRel getSemanticOverTwoRelation() {
		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ).getValueObject() != null)
			return ((IntOpersOverTwoRel) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject());
		else
			return null;
	}

	public void setIdentifier(String identifier) {
		super.setIdentifier(identifier);
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				getTransSupportMetaElement().getDescription());

	}

	public void setTransSupportMetaElement(MetaVertex supportMetaOvetTwoRelation) {
		super.setTransSupportMetaElement(supportMetaOvetTwoRelation);
		setDynamicVariable(VAR_METAOVERTWOREL_IDEN,
				supportMetaOvetTwoRelation.getAutoIdentifier());
		setDynamicVariable(MetaElement.VAR_DESCRIPTION,
				supportMetaOvetTwoRelation.getDescription());

	}

	public void setSemanticOverTwoRelation(IntOpersOverTwoRel sgd) {
		setDynamicVariable(VAR_SEMANTICOVERTWOREL_IDEN, sgd.getIdentifier());
		setInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ, sgd);

	}

	public void setSemanticOverTwoRelationIden(
			String semanticOverTwoRelationIdentifier) {
		setDynamicVariable(VAR_SEMANTICOVERTWOREL_IDEN,
				semanticOverTwoRelationIdentifier);
	}

	public void setMetaOverTwoRelationIden(String metaOverTwoRelationIdentifier) {
		setDynamicVariable(VAR_METAOVERTWOREL_IDEN,
				metaOverTwoRelationIdentifier);
		// createInstAttributes();
	}

	public String getOutCardinality() {
		return (String) (getDynamicVariable(VAR_OUTCARDINALITY));
	}

	public void setOutCardinality(String identifier) {
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_OUTCARDINALITY, identifier);
		;
	}

	public String getInCardinality() {
		return (String) (getDynamicVariable(VAR_INCARDINALITY));
	}

	public void setInCardinality(String identifier) {
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_INCARDINALITY, identifier);
		;
	}

	public Set<String> getDisPropVisibleAttributes(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null)
			modelingAttributesNames
					.addAll(((IntOpersOverTwoRel) getInstAttribute(
							VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
							.getPropVisibleAttributes());

		modelingAttributesNames.addAll(getSupportMetaOverTwoRelation()
				.getPropVisibleAttributesSet(parents));
		return modelingAttributesNames;
	}

	public Set<String> getDisPropEditableAttributes(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null)
			modelingAttributesNames
					.addAll(((IntOpersOverTwoRel) getInstAttribute(
							VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
							.getPropEditableAttributes());

		modelingAttributesNames.addAll(getSupportMetaOverTwoRelation()
				.getPropEditableAttributesSet(parents));
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelVisibleAttributes(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null)
			modelingAttributesNames
					.addAll(((IntOpersOverTwoRel) getInstAttribute(
							VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
							.getPanelVisibleAttributes());

		modelingAttributesNames.addAll(getSupportMetaOverTwoRelation()
				.getPanelVisibleAttributesSet(parents));
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelSpacersAttributes(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null)
			modelingAttributesNames
					.addAll(((IntOpersOverTwoRel) getInstAttribute(
							VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
							.getPanelSpacersAttributes());

		modelingAttributesNames.addAll(getSupportMetaOverTwoRelation()
				.getPanelSpacersAttributesSet(parents));
		return modelingAttributesNames;
	}

	public Set<String> getSemanticAttributesNames() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null) // TODO simulation attributes
													// too?
		{
			IntOpersOverTwoRel tmp = (IntOpersOverTwoRel) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject();
			modelingAttributesNames.addAll(tmp.getSemanticAttributesNames());
		}
		return modelingAttributesNames;
	}

	@Override
	public List<InstAttribute> getEditableVariables(List<InstElement> parents) {

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null) {
			Object o = getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
					.getValueObject();
			String semGroupDep = (String) ((IntOpersOverTwoRel) o)
					.getIdentifier();

			if (!semGroupDepOld.equals(semGroupDep)) {
				semGroupDepOld = semGroupDep;
				setInstAttribute(VAR_SEMANTICOVERTWOREL_IDEN, semGroupDep);
				createInstAttributes();
			}
		}
		Set<String> attributesNames = getDisPropEditableAttributes(parents);
		return getFilteredInstAttributes(attributesNames, null);

	}

	public String toString() {
		String out = "";
		// List<String> visibleAttributesNames = metaConcept
		// .getPanelVisibleAttributes();
		if (getSupportMetaOverTwoRelation() != null) {
			Set<String> visibleAttributesNames = getDisPanelVisibleAttributes(null);
			List<String> listVisibleAttributes = new ArrayList<String>();
			listVisibleAttributes.addAll(visibleAttributesNames);
			Collections.sort(listVisibleAttributes);

			// List<String> spacersAttributes = metaConcept
			// .getPanelSpacersAttributes();
			Set<String> spacersAttributes = getDisPanelSpacersAttributes(null);
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
							out += getInstAttributes().get(name);
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
			if (out.equals(""))
				out = "No display attributes defined";
		}
		return out;
	}

	public void clearEditableMetaVertex() {
		super.clearEditableMetaVertex();
		// supportMetaElement = null;
		setInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ, null);
		// TODO Auto-generated method stub
	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		AbstractAttribute out = getTransSupportMetaElement()
				.getSemanticAttribute(attributeName);
		if (out == null)
			return getSupportMetaOverTwoRelation().getModelingAttribute(
					attributeName, null);
		else
			return out;
	}
}
