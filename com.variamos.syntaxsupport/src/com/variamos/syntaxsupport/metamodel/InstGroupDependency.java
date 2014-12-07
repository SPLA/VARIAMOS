package com.variamos.syntaxsupport.metamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaVertex;
import com.variamos.syntaxsupport.metametamodel.MetaGroupDependency;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupDependency;

public class InstGroupDependency extends InstElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7122291624405069534L;
	private MetaGroupDependency metaGroupDependency;
	public static final String VAR_OUTCARDINALITY = "outCardinalitiy",
			VAR_INCARDINALITY = "inCardinalitiy",
			VAR_METAGROUPDEPIDENTIFIER = "metaGroupDepIde",
			VAR_SEMANTICGROUPDEPENDENCYID = "semGroupDepIde",
			VAR_SEMANTICGROUPDEPENDENCY = "semGroupDep";
	private String semGroupDepOld = "";

	public InstGroupDependency() {
		super();
//		vars.put(VAR_METAGROUPDEPIDENTIFIER,null);
//		vars.put(VAR_SEMANTICGROUPDEPENDENCYID,null);
//		vars.put(VAR_SEMANTICGROUPDEPENDENCY,null);
	}

	public InstGroupDependency(MetaGroupDependency metaGroupDependency) {
		super("");
		vars.put(VAR_METAGROUPDEPIDENTIFIER, metaGroupDependency.getIdentifier());
		this.metaGroupDependency = metaGroupDependency;
//		vars.put(VAR_SEMANTICGROUPDEPENDENCYID,null);
//		vars.put(VAR_SEMANTICGROUPDEPENDENCY,null);
		createInstAttributes();
	}

	public InstGroupDependency(String identifier,
			MetaGroupDependency metaGroupDependency) {
		super(identifier);
		vars.put(VAR_METAGROUPDEPIDENTIFIER, metaGroupDependency.getIdentifier());
		this.metaGroupDependency = metaGroupDependency;
//		vars.put(VAR_SEMANTICGROUPDEPENDENCYID,null);
//		vars.put(VAR_SEMANTICGROUPDEPENDENCY,null);
		createInstAttributes();
	}

	public MetaGroupDependency getMetaGroupDependency() {
		return metaGroupDependency;
	}

	public String getMetaGroupDependencyIdentifier() {
		return (String) vars.get(VAR_METAGROUPDEPIDENTIFIER);
	}

	public String getSemanticGroupDependencyIdentifier() {
		if ((String) vars.get(VAR_SEMANTICGROUPDEPENDENCYID) == null)
			if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject() != null)
				return ((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject()).getIdentifier();
			else
				return null;
		return (String) vars.get(VAR_SEMANTICGROUPDEPENDENCYID);
	}
	
	public IntSemanticGroupDependency getSemanticGroupDependency() {
		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject() != null)
			return ((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject());
		else
			return null;		
	}

	public void setMetaGroupDependency(MetaVertex metaGroupDependency) {
		this.metaGroupDependency = (MetaGroupDependency) metaGroupDependency;
		setVariable(VAR_METAGROUPDEPIDENTIFIER,
				metaGroupDependency.getIdentifier());

	}
	
	public void setSemanticGroupDependency(IntSemanticGroupDependency sgd) {
		setVariable(VAR_SEMANTICGROUPDEPENDENCYID,
				sgd.getIdentifier());
		setInstAttribute(VAR_SEMANTICGROUPDEPENDENCY,
			sgd);
		
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

	private void createInstAttributes() {
		Iterator<String> modelingAttributes = getMetaGroupDependency()
				.getModelingAttributes().iterator();
		while (modelingAttributes.hasNext()) {
			String name = modelingAttributes.next();
			if (name.equals("identifier"))
				addInstAttribute(name, getMetaGroupDependency()
						.getModelingAttribute(name), getIdentifier());
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

	public Set<String> getDisPropVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject() != null)
				modelingAttributesNames.addAll(((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject())
						.getDisPropVisibleAttributes());

		modelingAttributesNames.addAll(getMetaGroupDependency().getDisPropVisibleAttributes());
		return modelingAttributesNames;
	}
	
	public Set<String> getDisPropEditableAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)!= null &&
				getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject() != null)
				modelingAttributesNames.addAll(((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject())
						.getDisPropEditableAttributes());

		modelingAttributesNames.addAll(getMetaGroupDependency().getDisPropEditableAttributes());
		return modelingAttributesNames;
	}
	
	public Set<String> getDisPanelVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)!= null &&
				getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject() != null)
				modelingAttributesNames.addAll(((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject())
						.getDisPanelVisibleAttributes());

		modelingAttributesNames.addAll(getMetaGroupDependency().getDisPanelVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelSpacersAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)!= null &&
				getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject() != null)
			modelingAttributesNames.addAll(((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject())
						.getDisPanelSpacersAttributes());

		modelingAttributesNames.addAll(getMetaGroupDependency().getDisPanelSpacersAttributes());
		return modelingAttributesNames;
	}
	
	public Set<String> getSemanticAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)!= null &&
				getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject() != null) //TODO simulation attributes too?
		{
			IntSemanticGroupDependency tmp = (IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject();
			modelingAttributesNames.addAll(
					((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject()).getSemanticAttributes());
		}
		return modelingAttributesNames;
	}
	
	private IntSemanticGroupDependency getSemanticRelation() {
		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY) != null &&
				getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject() != null)
			return ((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject());
		return null;
	}

	private AbstractAttribute getSemanticAttribute(String name) {
		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY) != null &&
				getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject() != null)
			return ((IntSemanticGroupDependency) getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject())
				.getSemanticAttribute(name);
		return null;
	}

	@Override
	public InstAttribute[] getEditableVariables() {

		if (getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY)!= null &&
				getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject() != null) {
			Object o = getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject();
			String semGroupDep =(String)((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICGROUPDEPENDENCY).getObject()).getIdentifier();

			if (!semGroupDepOld.equals(semGroupDep)) {
				semGroupDepOld = semGroupDep;
				setInstAttribute(VAR_SEMANTICGROUPDEPENDENCYID,semGroupDep);
				createInstAttributes();
			}
		}
		Set<String> attributesNames = getDisPropEditableAttributes();
		List<String> listEditableAttributes = new ArrayList<String>();
		listEditableAttributes.addAll(attributesNames);
		Collections.sort(listEditableAttributes);

		List<String> listEditableAttribNames = new ArrayList<String>();
		for (String attribute : listEditableAttributes) {
			int endName = attribute.indexOf("#", 3);
			if (endName != -1)
				listEditableAttribNames.add(attribute.substring(3, endName));
			else
				listEditableAttribNames.add(attribute.substring(3));
		}

		InstAttribute[] editableInstAttributes = new InstAttribute[attributesNames
				.size()];
		int i = 0;
		for (String attributeName : listEditableAttribNames) {
			editableInstAttributes[i++] = getInstAttribute(attributeName);
		}
		return editableInstAttributes;

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

	public void clearMetaGroupDependency() {
		metaGroupDependency = null;
		setInstAttribute(VAR_SEMANTICGROUPDEPENDENCY,null);
		// TODO Auto-generated method stub
	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		AbstractAttribute out = getSemanticAttribute(attributeName);
		if (out == null)
			return getMetaGroupDependency().getModelingAttribute(attributeName);
		else
			return out;
	}

}
