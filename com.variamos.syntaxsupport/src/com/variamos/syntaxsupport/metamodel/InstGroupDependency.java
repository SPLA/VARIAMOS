package com.variamos.syntaxsupport.metamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaElement;
import com.variamos.syntaxsupport.metametamodel.MetaVertex;
import com.variamos.syntaxsupport.metametamodel.MetaGroupDependency;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupDependency;

public class InstGroupDependency extends InstVertex {
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
	VAR_METAGROUPDEPIDENTIFIER = "metaGroupDepIde",
	/**
					 * 
					 */
	VAR_SEMANTICGROUPDEPENDENCYID = "semGroupDepIde",
	/**
					 * 
					 */
	VAR_SEMANTICGROUPDEPENDENCY = "semGroupDep";
	/**
	 * 
	 */
	private MetaGroupDependency metaGroupDependency;

	

	/**
	 * Assigned during the generation of expressions from edges Used during the
	 * the generation of expressions grom groupdep
	 */
	private Set<String> sourceAttributeNames;

	private String semGroupDepOld = "";

	public InstGroupDependency() {
		super();

		sourceAttributeNames = new HashSet<String>();
		// vars.put(VAR_METAGROUPDEPIDENTIFIER,null);
		// vars.put(VAR_SEMANTICGROUPDEPENDENCYID,null);
		// vars.put(VAR_SEMANTICGROUPDEPENDENCY,null);
	}

	public InstGroupDependency(MetaGroupDependency metaGroupDependency) {
		super("");
		vars.put(VAR_METAGROUPDEPIDENTIFIER,
				metaGroupDependency.getIdentifier());
		this.metaGroupDependency = metaGroupDependency;
		setVariable(MetaElement.VAR_DESCRIPTION,
				metaGroupDependency.getDescription());

		sourceAttributeNames = new HashSet<String>();
		createInstAttributes();
	}

	public InstGroupDependency(String identifier,
			MetaGroupDependency metaGroupDependency) {
		super(identifier);
		vars.put(VAR_METAGROUPDEPIDENTIFIER,
				metaGroupDependency.getIdentifier());
		this.metaGroupDependency = metaGroupDependency;
		setVariable(MetaElement.VAR_DESCRIPTION,
				metaGroupDependency.getDescription());

		sourceAttributeNames = new HashSet<String>();
		createInstAttributes();
	}

	private void createInstAttributes() {
		Iterator<String> modelingAttributes = getMetaGroupDependency()
				.getModelingAttributes().iterator();
		while (modelingAttributes.hasNext()) {
			String name = modelingAttributes.next();
			if (name.equals(MetaElement.VAR_IDENTIFIER))
				addInstAttribute(name, getMetaGroupDependency()
						.getModelingAttribute(name), getIdentifier());
			else if (name.equals(MetaElement.VAR_DESCRIPTION))
				addInstAttribute(name, getMetaGroupDependency()
						.getModelingAttribute(name), getMetaGroupDependency()
						.getDescription());
			else
				addInstAttribute(name, getMetaGroupDependency()
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

	public Set<String> getSourceAttributeNames() {
		return sourceAttributeNames;
	}

	public void addSourceAttributeNames(Set<String> sourceAttributeNames) {
		this.sourceAttributeNames.addAll(sourceAttributeNames);
	}

	public void clearSourceAttributeNames() {
		this.sourceAttributeNames.clear();
	}

	public MetaGroupDependency getMetaGroupDependency() {
		return metaGroupDependency;
	}

	public String getMetaVertexIdentifier() {
		return (String) vars.get(VAR_METAGROUPDEPIDENTIFIER);
	}

	public String getSemanticGroupDependencyIdentifier() {
		if ((String) vars.get(VAR_SEMANTICGROUPDEPENDENCYID) == null)
			if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getValueObject() != null)
				return ((IntSemanticGroupDependency) getInstAttribute(
						VAR_SEMANTICGROUPDEPENDENCY).getValueObject())
						.getIdentifier();
			else
				return null;
		return (String) vars.get(VAR_SEMANTICGROUPDEPENDENCYID);
	}

	public IntSemanticGroupDependency getSemanticGroupDependency() {
		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getValueObject() != null)
			return ((IntSemanticGroupDependency) getInstAttribute(
					VAR_SEMANTICGROUPDEPENDENCY).getValueObject());
		else
			return null;
	}

	public void setIdentifier(String identifier) {
		super.setIdentifier(identifier);
		setVariable(MetaElement.VAR_DESCRIPTION,
				metaGroupDependency.getDescription());

	}

	public void setMetaVertex(MetaVertex metaGroupDependency) {
		this.metaGroupDependency = (MetaGroupDependency) metaGroupDependency;
		setVariable(VAR_METAGROUPDEPIDENTIFIER,
				metaGroupDependency.getIdentifier());
		setVariable(MetaElement.VAR_DESCRIPTION,
				metaGroupDependency.getDescription());

	}

	public void setSemanticGroupDependency(IntSemanticGroupDependency sgd) {
		setVariable(VAR_SEMANTICGROUPDEPENDENCYID, sgd.getIdentifier());
		setInstAttribute(VAR_SEMANTICGROUPDEPENDENCY, sgd);

	}

	public void setSemanticGroupDependencyIdentifier(
			String semanticGroupDependencyIdentifier) {
		setVariable(VAR_SEMANTICGROUPDEPENDENCYID,
				semanticGroupDependencyIdentifier);
	}

	public void setMetaGroupDependencyIdentifier(
			String metaGroupDependencyIdentifier) {
		setVariable(VAR_METAGROUPDEPIDENTIFIER, metaGroupDependencyIdentifier);
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

		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getValueObject() != null)
			modelingAttributesNames
					.addAll(((IntSemanticGroupDependency) getInstAttribute(
							VAR_SEMANTICGROUPDEPENDENCY).getValueObject())
							.getDisPropVisibleAttributes());

		modelingAttributesNames.addAll(getMetaGroupDependency()
				.getDisPropVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPropEditableAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY) != null
				&& getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)
						.getValueObject() != null)
			modelingAttributesNames
					.addAll(((IntSemanticGroupDependency) getInstAttribute(
							VAR_SEMANTICGROUPDEPENDENCY).getValueObject())
							.getDisPropEditableAttributes());

		modelingAttributesNames.addAll(getMetaGroupDependency()
				.getDisPropEditableAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY) != null
				&& getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)
						.getValueObject() != null)
			modelingAttributesNames
					.addAll(((IntSemanticGroupDependency) getInstAttribute(
							VAR_SEMANTICGROUPDEPENDENCY).getValueObject())
							.getDisPanelVisibleAttributes());

		modelingAttributesNames.addAll(getMetaGroupDependency()
				.getDisPanelVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelSpacersAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY) != null
				&& getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)
						.getValueObject() != null)
			modelingAttributesNames
					.addAll(((IntSemanticGroupDependency) getInstAttribute(
							VAR_SEMANTICGROUPDEPENDENCY).getValueObject())
							.getDisPanelSpacersAttributes());

		modelingAttributesNames.addAll(getMetaGroupDependency()
				.getDisPanelSpacersAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getSemanticAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY) != null
				&& getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)
						.getValueObject() != null) // TODO simulation attributes
													// too?
		{
			IntSemanticGroupDependency tmp = (IntSemanticGroupDependency) getInstAttribute(
					VAR_SEMANTICGROUPDEPENDENCY).getValueObject();
			modelingAttributesNames
					.addAll(((IntSemanticGroupDependency) getInstAttribute(
							VAR_SEMANTICGROUPDEPENDENCY).getValueObject())
							.getSemanticAttributes());
		}
		return modelingAttributesNames;
	}

	private IntSemanticGroupDependency getSemanticRelation() {
		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY) != null
				&& getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)
						.getValueObject() != null)
			return ((IntSemanticGroupDependency) getInstAttribute(
					VAR_SEMANTICGROUPDEPENDENCY).getValueObject());
		return null;
	}

	private AbstractAttribute getSemanticAttribute(String name) {
		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY) != null
				&& getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)
						.getValueObject() != null)
			return ((IntSemanticGroupDependency) getInstAttribute(
					VAR_SEMANTICGROUPDEPENDENCY).getValueObject())
					.getSemanticAttribute(name);
		return null;
	}

	@Override
	public List<InstAttribute> getEditableVariables() {

		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY) != null
				&& getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)
						.getValueObject() != null) {
			Object o = getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)
					.getValueObject();
			String semGroupDep = (String) ((IntSemanticGroupDependency) getInstAttribute(
					VAR_SEMANTICGROUPDEPENDENCY).getValueObject())
					.getIdentifier();

			if (!semGroupDepOld.equals(semGroupDep)) {
				semGroupDepOld = semGroupDep;
				setInstAttribute(VAR_SEMANTICGROUPDEPENDENCYID, semGroupDep);
				createInstAttributes();
			}
		}
		Set<String> attributesNames = getDisPropEditableAttributes();
		return getFilteredInstAttributes(attributesNames, null);

	}

	@Override
	public List<InstAttribute> getVisibleVariables() {

		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY) != null
				&& getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)
						.getValueObject() != null) {
			Object o = getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)
					.getValueObject();
			String semGroupDep = (String) ((IntSemanticGroupDependency) getInstAttribute(
					VAR_SEMANTICGROUPDEPENDENCY).getValueObject())
					.getIdentifier();

			if (!semGroupDepOld.equals(semGroupDep)) {
				semGroupDepOld = semGroupDep;
				setInstAttribute(VAR_SEMANTICGROUPDEPENDENCYID, semGroupDep);
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
		if (getMetaGroupDependency() != null) {
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
		metaGroupDependency = null;
		setInstAttribute(VAR_SEMANTICGROUPDEPENDENCY, null);
		// TODO Auto-generated method stub
	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		AbstractAttribute out = getSemanticAttribute(attributeName);
		if (out == null)
			return getMetaGroupDependency().getModelingAttribute(attributeName);
		else
			return out;
	}

	@Override
	public MetaVertex getMetaVertex() {
		return metaGroupDependency;
	}

}
