package com.variamos.syntaxsupport.metamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaOverTwoRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaVertex;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticOverTwoRelation;

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
	 * Support concept (defines the available attributes and syntax)
	 */
	private MetaOverTwoRelation supportMetaOverTwoRelation;
	/**
	 * Assigned during the generation of expressions for
	 * SingleElementExpressionSet required during the generation of expressions
	 * for MultiElementExpressionSet
	 */
	private Set<String> sourceAttributeNames;

	/**
	 * TODO review if needed
	 */
	private String semGroupDepOld = "";

	public InstOverTwoRelation() {
		super();

		sourceAttributeNames = new HashSet<String>();
		// vars.put(VAR_METAGROUPDEPIDENTIFIER,null);
		// vars.put(VAR_SEMANTICGROUPDEPENDENCYID,null);
		// vars.put(VAR_SEMANTICGROUPDEPENDENCY,null);
	}

	public InstOverTwoRelation(MetaOverTwoRelation metaOverTwoRelation) {
		super("");
		vars.put(VAR_METAOVERTWOREL_IDEN,
				metaOverTwoRelation.getIdentifier());
		this.supportMetaOverTwoRelation = metaOverTwoRelation;
		setVariable(MetaElement.VAR_DESCRIPTION,
				metaOverTwoRelation.getDescription());

		sourceAttributeNames = new HashSet<String>();
		createInstAttributes();
	}

	public InstOverTwoRelation(String identifier,
			MetaOverTwoRelation supportMetaOvetTwoRelation,
			MetaElement editableMetaElement) {
		super(identifier);
		setEditableMetaElement(editableMetaElement);
		if (supportMetaOvetTwoRelation != null) {
			vars.put(VAR_METAOVERTWOREL_IDEN,
					supportMetaOvetTwoRelation.getIdentifier());
			this.supportMetaOverTwoRelation = supportMetaOvetTwoRelation;
			setVariable(MetaElement.VAR_DESCRIPTION,
					supportMetaOvetTwoRelation.getDescription());
		}
		sourceAttributeNames = new HashSet<String>();
		createInstAttributes();
	}

	public InstOverTwoRelation(String identifier,
			MetaOverTwoRelation metaGroupDependency,
			IntSemanticElement semanticElement) {
		super(identifier);
		setEditableSemanticElement(semanticElement);
		vars.put(VAR_METAOVERTWOREL_IDEN,
				metaGroupDependency.getIdentifier());
		this.supportMetaOverTwoRelation = metaGroupDependency;
		setVariable(MetaElement.VAR_DESCRIPTION,
				metaGroupDependency.getDescription());

		sourceAttributeNames = new HashSet<String>();
		createInstAttributes();
	}

	public InstOverTwoRelation(String identifier,
			MetaOverTwoRelation metaGroupDependency) {
		super(identifier);
		vars.put(VAR_METAOVERTWOREL_IDEN,
				metaGroupDependency.getIdentifier());
		this.supportMetaOverTwoRelation = metaGroupDependency;
		setVariable(MetaElement.VAR_DESCRIPTION,
				metaGroupDependency.getDescription());

		sourceAttributeNames = new HashSet<String>();
		createInstAttributes();
	}

	protected void createInstAttributes() {
		if (getMetaOverTwoRelation() != null) {
			for (String name : getMetaOverTwoRelation()
					.getModelingAttributesNames()) {
				if (name.equals(MetaElement.VAR_IDENTIFIER))
					addInstAttribute(name, getMetaOverTwoRelation()
							.getModelingAttribute(name), getIdentifier());
				else if (name.equals(MetaElement.VAR_DESCRIPTION))
					addInstAttribute(name, getMetaOverTwoRelation()
							.getModelingAttribute(name),
							getMetaOverTwoRelation().getDescription());
				else
					addInstAttribute(name, getMetaOverTwoRelation()
							.getModelingAttribute(name), null);
			}

			Iterator<String> semanticAttributes = getSemanticAttributes()
					.iterator();
			if (getSemanticRelation() != null)
				while (semanticAttributes.hasNext()) {
					String name = semanticAttributes.next();
					if (name.equals("identifier"))
						addInstAttribute(name, getSemanticAttribute(name),
								getIdentifier());
					else
						addInstAttribute(name, getSemanticAttribute(name), null);
				}
		}
	}

	public Set<String> getSourceAttributeNames() {
		return sourceAttributeNames;
	}

	/**
	 * Add the name of the attributes for MultiElementExpressionSet
	 * 
	 * @param sourceAttributeNames
	 *            Name of attributes for double implication
	 */
	public void addSourceAttributeNames(Set<String> sourceAttributeNames) {
		this.sourceAttributeNames.addAll(sourceAttributeNames);
	}

	public void clearSourceAttributeNames() {
		this.sourceAttributeNames.clear();
	}

	public MetaOverTwoRelation getMetaOverTwoRelation() {
		return supportMetaOverTwoRelation;
	}

	public String getMetaVertexIdentifier() {
		return (String) vars.get(VAR_METAOVERTWOREL_IDEN);
	}

	public String getSemanticOverTwoRelationIden() {
		if ((String) vars.get(VAR_SEMANTICOVERTWOREL_IDEN) == null)
			if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ).getValueObject() != null)
				return ((IntSemanticOverTwoRelation) getInstAttribute(
						VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
						.getIdentifier();
			else
				return null;
		return (String) vars.get(VAR_SEMANTICOVERTWOREL_IDEN);
	}

	public IntSemanticOverTwoRelation getSemanticOverTwoRelation() {
		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ).getValueObject() != null)
			return ((IntSemanticOverTwoRelation) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject());
		else
			return null;
	}

	public void setIdentifier(String identifier) {
		super.setIdentifier(identifier);
		setVariable(MetaElement.VAR_DESCRIPTION,
				supportMetaOverTwoRelation.getDescription());

	}

	public void setMetaVertex(MetaVertex metaOverTwoRelation) {
		this.supportMetaOverTwoRelation = (MetaOverTwoRelation) metaOverTwoRelation;
		setVariable(VAR_METAOVERTWOREL_IDEN,
				metaOverTwoRelation.getIdentifier());
		setVariable(MetaElement.VAR_DESCRIPTION,
				metaOverTwoRelation.getDescription());

	}

	public void setSemanticOverTwoRelation(IntSemanticOverTwoRelation sgd) {
		setVariable(VAR_SEMANTICOVERTWOREL_IDEN, sgd.getIdentifier());
		setInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ, sgd);

	}

	public void setSemanticOverTwoRelationIden(
			String semanticOverTwoRelationIdentifier) {
		setVariable(VAR_SEMANTICOVERTWOREL_IDEN,
				semanticOverTwoRelationIdentifier);
	}

	public void setMetaOverTwoRelationIden(
			String metaOverTwoRelationIdentifier) {
		setVariable(VAR_METAOVERTWOREL_IDEN, metaOverTwoRelationIdentifier);
		// createInstAttributes();
	}

	public String getOutCardinality() {
		return (String) (getVariable(VAR_OUTCARDINALITY));
	}

	public void setOutCardinality(String identifier) {
		vars.put(VAR_OUTCARDINALITY, identifier);
		;
	}

	public String getInCardinality() {
		return (String) (getVariable(VAR_INCARDINALITY));
	}

	public void setInCardinality(String identifier) {
		vars.put(VAR_INCARDINALITY, identifier);
		;
	}

	public Set<String> getDisPropVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ).getValueObject() != null)
			modelingAttributesNames
					.addAll(((IntSemanticOverTwoRelation) getInstAttribute(
							VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
							.getPropVisibleAttributes());

		modelingAttributesNames.addAll(getMetaOverTwoRelation()
				.getPropVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPropEditableAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null)
			modelingAttributesNames
					.addAll(((IntSemanticOverTwoRelation) getInstAttribute(
							VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
							.getPropEditableAttributes());

		modelingAttributesNames.addAll(getMetaOverTwoRelation()
				.getPropEditableAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null)
			modelingAttributesNames
					.addAll(((IntSemanticOverTwoRelation) getInstAttribute(
							VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
							.getPanelVisibleAttributes());

		modelingAttributesNames.addAll(getMetaOverTwoRelation()
				.getPanelVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelSpacersAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null)
			modelingAttributesNames
					.addAll(((IntSemanticOverTwoRelation) getInstAttribute(
							VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
							.getPanelSpacersAttributes());

		modelingAttributesNames.addAll(getMetaOverTwoRelation()
				.getPanelSpacersAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getSemanticAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null) // TODO simulation attributes
													// too?
		{
			IntSemanticOverTwoRelation tmp = (IntSemanticOverTwoRelation) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject();
			modelingAttributesNames
					.addAll(((IntSemanticOverTwoRelation) getInstAttribute(
							VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
							.getSemanticAttributesNames());
		}
		return modelingAttributesNames;
	}

	private IntSemanticOverTwoRelation getSemanticRelation() {
		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null)
			return ((IntSemanticOverTwoRelation) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject());
		return null;
	}

	private AbstractAttribute getSemanticAttribute(String name) {
		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null)
			return ((IntSemanticOverTwoRelation) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
					.getSemanticAttribute(name);
		return null;
	}

	@Override
	public List<InstAttribute> getEditableVariables() {

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null) {
			Object o = getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
					.getValueObject();
			String semGroupDep = (String) ((IntSemanticOverTwoRelation) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
					.getIdentifier();

			if (!semGroupDepOld.equals(semGroupDep)) {
				semGroupDepOld = semGroupDep;
				setInstAttribute(VAR_SEMANTICOVERTWOREL_IDEN, semGroupDep);
				createInstAttributes();
			}
		}
		Set<String> attributesNames = getDisPropEditableAttributes();
		return getFilteredInstAttributes(attributesNames, null);

	}

	@Override
	public List<InstAttribute> getVisibleVariables() {

		if (getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ) != null
				&& getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
						.getValueObject() != null) {
			Object o = getInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ)
					.getValueObject();
			String semGroupDep = (String) ((IntSemanticOverTwoRelation) getInstAttribute(
					VAR_SEMANTICOVERTWOREL_OBJ).getValueObject())
					.getIdentifier();

			if (!semGroupDepOld.equals(semGroupDep)) {
				semGroupDepOld = semGroupDep;
				setInstAttribute(VAR_SEMANTICOVERTWOREL_IDEN, semGroupDep);
				createInstAttributes();
			}
		}
		Set<String> attributesNames = getDisPropVisibleAttributes();

		return getFilteredInstAttributes(attributesNames, null);

	}

	public String toString() {
		String out = "";
		// List<String> visibleAttributesNames = metaConcept
		// .getPanelVisibleAttributes();
		if (getMetaOverTwoRelation() != null) {
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

	public void clearMetaVertex() {
		supportMetaOverTwoRelation = null;
		setInstAttribute(VAR_SEMANTICOVERTWOREL_OBJ, null);
		// TODO Auto-generated method stub
	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		AbstractAttribute out = getSemanticAttribute(attributeName);
		if (out == null)
			return getMetaOverTwoRelation().getModelingAttribute(attributeName);
		else
			return out;
	}

	@Override
	public MetaVertex getMetaVertex() {
		return supportMetaOverTwoRelation;
	}

	@Override
	public MetaElement getSupportMetaElement() {
		return supportMetaOverTwoRelation;
	}

}
