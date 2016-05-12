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
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.OpersOverTwoRel;
import com.variamos.dynsup.model.SyntaxElement;

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
public class InstOverTwoRel extends InstVertex {
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

	public InstOverTwoRel() {
		super("", new HashMap<String, InstAttribute>());

		sourcePositiveAttributeNames = new HashSet<String>();
		sourceNegativeAttributeNames = new HashSet<String>();
		// vars.put(VAR_METAGROUPDEPIDENTIFIER,null);
		// vars.put(VAR_SEMANTICGROUPDEPENDENCYID,null);
		// vars.put(VAR_SEMANTICGROUPDEPENDENCY,null);
	}

	public InstOverTwoRel(InstElement metaOverTwoRelation) {
		super("", new HashMap<String, InstAttribute>());
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_METAOVERTWOREL_IDEN, metaOverTwoRelation
				.getEdSyntaxEle().getAutoIdentifier());
		setTransSupInstElement(metaOverTwoRelation);
		setDynamicVariable(SyntaxElement.VAR_DESCRIPTION, metaOverTwoRelation
				.getEdSyntaxEle().getDescription());

		sourcePositiveAttributeNames = new HashSet<String>();
		sourceNegativeAttributeNames = new HashSet<String>();
		createInstAttributes(null);
	}

	public InstOverTwoRel(String identifier,
			SyntaxElement editableSyntaxElement, InstElement supInstElement) {
		this(identifier, supInstElement, editableSyntaxElement);
		setTransSupInstElement(supInstElement);
	}

	public InstOverTwoRel(String identifier,
			InstElement supportMetaOvetTwoRelation,
			SyntaxElement editableMetaElement) {
		super(identifier, new HashMap<String, InstAttribute>());
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		setEdSyntaxEle(editableMetaElement);
		if (supportMetaOvetTwoRelation != null) {
			dynamicAttributesMap.put(VAR_METAOVERTWOREL_IDEN,
					supportMetaOvetTwoRelation.getEdSyntaxEle()
							.getAutoIdentifier());
			setTransSupInstElement(supportMetaOvetTwoRelation);
			setDynamicVariable(SyntaxElement.VAR_DESCRIPTION,
					supportMetaOvetTwoRelation.getEdSyntaxEle()
							.getDescription());
		}
		sourcePositiveAttributeNames = new HashSet<String>();
		sourceNegativeAttributeNames = new HashSet<String>();
		createInstAttributes(null);
	}

	public InstOverTwoRel(String identifier,
			InstElement supportMetaOvetTwoRelation, OpersElement semanticElement) {
		super(identifier, new HashMap<String, InstAttribute>());
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		setEdOperEle(semanticElement);
		dynamicAttributesMap
				.put(VAR_METAOVERTWOREL_IDEN, supportMetaOvetTwoRelation
						.getEdSyntaxEle().getAutoIdentifier());
		setTransSupInstElement(supportMetaOvetTwoRelation);
		setDynamicVariable(SyntaxElement.VAR_DESCRIPTION,
				supportMetaOvetTwoRelation.getEdSyntaxEle().getDescription());

		sourcePositiveAttributeNames = new HashSet<String>();
		sourceNegativeAttributeNames = new HashSet<String>();
		createInstAttributes(null);
	}

	public InstOverTwoRel(String identifier,
			InstElement supportMetaOvetTwoRelation) {
		super(identifier, new HashMap<String, InstAttribute>());
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap
				.put(VAR_METAOVERTWOREL_IDEN, supportMetaOvetTwoRelation
						.getEdSyntaxEle().getAutoIdentifier());
		setTransSupInstElement(supportMetaOvetTwoRelation);
		setDynamicVariable(SyntaxElement.VAR_DESCRIPTION,
				supportMetaOvetTwoRelation.getEdSyntaxEle().getDescription());

		sourcePositiveAttributeNames = new HashSet<String>();
		sourceNegativeAttributeNames = new HashSet<String>();
		createInstAttributes(null);
	}

	@Deprecated
	public void createInstAttributes() {
		if (getSupportMetaOverTwoRelation() != null) {
			for (String name : getSupportMetaOverTwoRelation()
					.getModelingAttributesNames(null)) {
				if (name.equals(SyntaxElement.VAR_AUTOIDENTIFIER))
					addInstAttribute(name, getSupportMetaOverTwoRelation()
							.getModelingAttribute(name, null), getIdentifier());
				else if (name.equals(SyntaxElement.VAR_DESCRIPTION))
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

	public SyntaxElement getSupportMetaOverTwoRelation() {
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
						((OpersOverTwoRel) getInstAttribute(
								VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
								.getIdentifier());
				return ((OpersOverTwoRel) getInstAttribute(
						VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
						.getIdentifier();
			}

			else
				return null;
		return (String) dynamicAttributesMap.get(VAR_SEMANTICOVERTWOREL_IDEN);
	}

	public OpersOverTwoRel getSemanticOverTwoRelation() {
		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ).getValueObject() != null)
			return ((OpersOverTwoRel) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject());
		else
			return null;
	}

	public void setTransSupportMetaElement(
			InstElement supportMetaOvetTwoRelation) {
		super.setTransSupInstElement(supportMetaOvetTwoRelation);
		setDynamicVariable(VAR_METAOVERTWOREL_IDEN, supportMetaOvetTwoRelation
				.getEdSyntaxEle().getAutoIdentifier());
		setDynamicVariable(SyntaxElement.VAR_DESCRIPTION,
				supportMetaOvetTwoRelation.getEdSyntaxEle().getDescription());

	}

	public void setSemanticOverTwoRelation(OpersOverTwoRel sgd) {
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
		return (String) (getDynamicAttribute(VAR_OUTCARDINALITY));
	}

	public void setOutCardinality(String identifier) {
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_OUTCARDINALITY, identifier);
		;
	}

	public String getInCardinality() {
		return (String) (getDynamicAttribute(VAR_INCARDINALITY));
	}

	public void setInCardinality(String identifier) {
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_INCARDINALITY, identifier);
		;
	}

	public Set<String> getDisPropVisibleAttributes(
			List<InstElement> syntaxParents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null)
			modelingAttributesNames.addAll(((OpersOverTwoRel) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
					.getPropVisibleAttributes(null));
		// FIXME how to pass parents to opersconcept
		modelingAttributesNames.addAll(getSupportMetaOverTwoRelation()
				.getPropVisibleAttributesSet(syntaxParents));
		return modelingAttributesNames;
	}

	public Set<String> getDisPropEditableAttributes(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null)
			// FIXME how to pass parents to opersconcept
			modelingAttributesNames.addAll(((OpersOverTwoRel) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
					.getPropEditableAttributes(null));

		modelingAttributesNames.addAll(getSupportMetaOverTwoRelation()
				.getPropEditableAttributesSet(parents));
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelVisibleAttributes(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null)
			modelingAttributesNames.addAll(((OpersOverTwoRel) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
					.getPanelVisibleAttributes(null));

		modelingAttributesNames.addAll(getSupportMetaOverTwoRelation()
				.getPanelVisibleAttributesSet(parents));
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelSpacersAttributes(
			List<InstElement> syntaxParents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null)
			modelingAttributesNames.addAll(((OpersOverTwoRel) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
					.getPanelSpacersAttributes(null));

		modelingAttributesNames.addAll(getSupportMetaOverTwoRelation()
				.getPanelSpacersAttributesSet(syntaxParents));
		return modelingAttributesNames;
	}

	public Set<String> getSemanticAttributesNames(List<InstElement> opersParents) {
		Set<String> modelingAttributesNames = new HashSet<String>();
		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null) // TODO simulation attributes
													// too?
		{
			OpersOverTwoRel tmp = (OpersOverTwoRel) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject();
			modelingAttributesNames.addAll(tmp
					.getAllSemanticAttributesNames(opersParents));
		}
		return modelingAttributesNames;
	}

	@Override
	public List<InstAttribute> getEditableVariables(
			List<InstElement> syntaxParents) {

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null) {
			Object o = getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
					.getValueObject();
			String semGroupDep = (String) ((OpersOverTwoRel) o).getIdentifier();

			if (!semGroupDepOld.equals(semGroupDep)) {
				semGroupDepOld = semGroupDep;
				setInstAttribute(VAR_SEMANTICOVERTWOREL_IDEN, semGroupDep);
				createInstAttributes(syntaxParents);
			}
		}
		Set<String> attributesNames = getDisPropEditableAttributes(syntaxParents);
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

	@Override
	public void clearEditableMetaVertex() {
		super.clearEditableMetaVertex();
		// supportMetaElement = null;
		setInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ, null);
		// TODO Auto-generated method stub
	}

	public ElemAttribute getAbstractAttribute(String attributeName,
			List<InstElement> opersParents) {
		ElemAttribute out = getTransSupportMetaElement().getSemanticAttribute(
				attributeName);
		if (out == null)
			return getSupportMetaOverTwoRelation().getModelingAttribute(
					attributeName, null);
		else
			return out;
	}
}
