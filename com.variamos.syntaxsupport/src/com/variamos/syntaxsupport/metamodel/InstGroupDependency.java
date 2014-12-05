package com.variamos.syntaxsupport.metamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaElement;
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
			VAR_METAGROUPDEPENDENCY = "metaGroupDepIde",
			VAR_SEMANTICGROUPDEPENDENCY = "semanticRelIde",
			VAR_SEMANTICRELATION = "semanticRelation";
	private String semGroupDepOld = "";

	public InstGroupDependency() {
		super();
	}

	public InstGroupDependency(MetaGroupDependency metaGroupDependency) {
		super("");
		vars.put(VAR_METAGROUPDEPENDENCY, metaGroupDependency.getIdentifier());
		this.metaGroupDependency = metaGroupDependency;
		createInstAttributes();
	}

	public InstGroupDependency(String identifier,
			MetaGroupDependency metaGroupDependency) {
		super(identifier);
		vars.put(VAR_METAGROUPDEPENDENCY, metaGroupDependency.getIdentifier());
		this.metaGroupDependency = metaGroupDependency;
		createInstAttributes();
	}

	public MetaGroupDependency getMetaGroupDependency() {
		return metaGroupDependency;
	}

	public String getMetaGroupDependencyIdentifier() {
		return (String) vars.get(VAR_METAGROUPDEPENDENCY);
	}

	public Object getSemanticGroupDependencyIdentifier() {
		if ((String) vars.get(VAR_SEMANTICGROUPDEPENDENCY) == null)
			if (getInstAttribute(VAR_SEMANTICRELATION).getObject() != null)
				return ((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICRELATION).getObject()).getIdentifier();
			else
				return null;
		return (String) vars.get(VAR_SEMANTICGROUPDEPENDENCY);
	}

	public void setMetaGroupDependency(MetaElement metaGroupDependency) {
		this.metaGroupDependency = (MetaGroupDependency) metaGroupDependency;
		setVariable(VAR_METAGROUPDEPENDENCY,
				metaGroupDependency.getIdentifier());

	}
	
	public void setSemanticGroupDependency(IntSemanticGroupDependency sgd) {
		setVariable(VAR_SEMANTICGROUPDEPENDENCY,
				sgd.getIdentifier());
		setInstAttribute(VAR_SEMANTICRELATION,
				sgd);
		
	}

	public void setSemanticGroupDependencyIdentifier(
			String semanticGroupDependencyIdentifier) {
		setVariable(VAR_SEMANTICGROUPDEPENDENCY,
				semanticGroupDependencyIdentifier);
	}



	public void setMetaGroupDependencyIdentifier(
			String metaGroupDependencyIdentifier) {
		setVariable(VAR_METAGROUPDEPENDENCY, metaGroupDependencyIdentifier);
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
		Iterator<String> metaAttributes = getMetaGroupDependency()
				.getMetaAttributes().iterator();
		while (metaAttributes.hasNext()) {
			String name = metaAttributes.next();
			if (name.equals("identifier"))
				addInstAttribute(name, getMetaGroupDependency()
						.getMetaAttribute(name), getIdentifier());
			else
				addInstAttribute(name, getMetaGroupDependency()
						.getMetaAttribute(name), null);
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
		Set<String> metaAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICRELATION).getObject() != null)
				metaAttributesNames.addAll(((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICRELATION).getObject())
						.getDisPropVisibleAttributes());

		metaAttributesNames.addAll(getMetaGroupDependency().getDisPropVisibleAttributes());
		return metaAttributesNames;
	}
	
	public Set<String> getDisPropEditableAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICRELATION).getObject() != null)
				metaAttributesNames.addAll(((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICRELATION).getObject())
						.getDisPropEditableAttributes());

		metaAttributesNames.addAll(getMetaGroupDependency().getDisPropEditableAttributes());
		return metaAttributesNames;
	}
	
	public Set<String> getDisPanelVisibleAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICRELATION).getObject() != null)
				metaAttributesNames.addAll(((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICRELATION).getObject())
						.getDisPanelVisibleAttributes());

		metaAttributesNames.addAll(getMetaGroupDependency().getDisPanelVisibleAttributes());
		return metaAttributesNames;
	}

	public Set<String> getDisPanelSpacersAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();

		if (getInstAttribute(VAR_SEMANTICRELATION).getObject() != null)
			metaAttributesNames.addAll(((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICRELATION).getObject())
						.getDisPanelSpacersAttributes());

		metaAttributesNames.addAll(getMetaGroupDependency().getDisPanelSpacersAttributes());
		return metaAttributesNames;
	}
	
	public Set<String> getSemanticAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();
		if (getInstAttribute(VAR_SEMANTICRELATION).getObject() != null) //TODO simulation attributes too?
		{
			IntSemanticGroupDependency tmp = (IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICRELATION).getObject();
			metaAttributesNames.addAll(
					((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICRELATION).getObject()).getSemanticAttributes());
		}
		return metaAttributesNames;
	}
	
	private IntSemanticGroupDependency getSemanticRelation() {
		if (getInstAttribute(VAR_SEMANTICRELATION).getObject() != null)
			return ((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICRELATION).getObject());
		return null;
	}

	private AbstractAttribute getSemanticAttribute(String name) {
		return ((IntSemanticGroupDependency) getInstAttribute(VAR_SEMANTICRELATION).getObject())
				.getSemanticAttribute(name);
	}

	@Override
	public InstAttribute[] getEditableVariables() {

		if (getInstAttribute(VAR_SEMANTICRELATION).getObject() != null) {
			Object o = getInstAttribute(VAR_SEMANTICRELATION).getObject();
			String semGroupDep =(String)((IntSemanticGroupDependency)getInstAttribute(VAR_SEMANTICRELATION).getObject()).getIdentifier();

			if (!semGroupDepOld.equals(semGroupDep)) {
				semGroupDepOld = semGroupDep;
				setInstAttribute(VAR_SEMANTICGROUPDEPENDENCY,semGroupDep);
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
		// TODO Auto-generated method stub
	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		AbstractAttribute out = getSemanticAttribute(attributeName);
		if (out == null)
			return getMetaGroupDependency().getMetaAttribute(attributeName);
		else
			return out;
	}

}
