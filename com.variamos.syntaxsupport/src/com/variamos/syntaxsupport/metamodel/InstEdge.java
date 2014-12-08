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
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticEdge;

public class InstEdge implements Serializable, Prototype, EditableElement {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6134025886276124795L;
	private String identifier;
	private InstElement fromRelation;
	private InstElement toRelation;
	private List<InstAttribute> attributes;
	public static final String VAR_METAEDGEIDE = "MetaEdgeIde",
			VAR_METAEDGE = "MetaEdge",
			VAR_INSTATTRIBUTES = "InstAttribute",
			VAR_METAEDGECLASS = "com.variamos.syntaxsupport.metametamodel.MetaEdge";

	protected Map<String, Object> vars = new HashMap<>();

	public InstEdge() {
		this(new HashMap<String, InstAttribute>());
	}
	
	public void setFromRelation(InstElement fromRelation) {
		this.fromRelation = fromRelation;
	}

	public void setToRelation(InstElement toRelation) {
		this.toRelation = toRelation;
	}



	public InstEdge(Map<String, InstAttribute> instAttributes) {
		vars.put(VAR_INSTATTRIBUTES, instAttributes);
		SemanticAttribute semAttribute = new SemanticAttribute(VAR_METAEDGE,
				"Class", VAR_METAEDGECLASS, "", "");
		vars.put(VAR_METAEDGE, semAttribute);
		vars.put(VAR_METAEDGEIDE, "");
		addInstAttribute(VAR_METAEDGE, semAttribute, "");
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getMetaEdgeIdentifier() {
		// return metaConcept.getIdentified();
		return (String) vars.get(VAR_METAEDGEIDE);
	}

	public void setMetaEdge(MetaEdge metaEdge) {
		getInstAttribute(VAR_METAEDGE).setObject(metaEdge);
		setVariable(VAR_METAEDGEIDE, metaEdge.getIdentifier());
		createInstAttributes();
	}

	private void createInstAttributes() {
		if (getMetaEdge() != null) {
			Iterator<String> modelingAttributes = getMetaEdge()
					.getModelingAttributes().iterator();
			while (modelingAttributes.hasNext()) {
				String name = modelingAttributes.next();
				if (name.equals("identifier"))
					addInstAttribute(name,
							getMetaEdge().getModelingAttribute(name),
							getIdentifier());
				else if (getInstAttribute(name) == null
						|| getInstAttribute(name).getValue() == null)
					addInstAttribute(name,
							getMetaEdge().getModelingAttribute(name), null);
			}

			if (getInstAttribute(MetaEdge.VAR_SEMANTICDIRECTRELATION)
					.getObject() != null) {
				IntDirectSemanticEdge sementicRelation = (IntDirectSemanticEdge) getInstAttribute(
						MetaEdge.VAR_SEMANTICDIRECTRELATION).getObject();
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
			return (MetaEdge) (getInstAttribute(VAR_METAEDGE).getObject());
		return null;
	}

	public List<InstAttribute> getAttributes() {
		return attributes;
	}

	public InstElement getFromRelation() {
		return fromRelation;
	}

	public InstElement getToRelation() {
		return toRelation;
	}

	@Override
	public InstAttribute[] getEditableVariables() {
		createInstAttributes();
		// return new InstAttribute[0];
		InstAttribute[] editableInstAttributes = null;
		if (getMetaEdge() != null) {
			Set<String> attributeNames = getDisPropEditableAttributes();
			List<String> listEditableAttributes = new ArrayList<String>();
			listEditableAttributes.addAll(attributeNames);
			Collections.sort(listEditableAttributes);

			List<String> listEditableAttribNames = new ArrayList<String>();
			for (String attribute : listEditableAttributes) {
				int endName = attribute.indexOf("#", 3);
				if (endName != -1)
					listEditableAttribNames
							.add(attribute.substring(3, endName));
				else
					listEditableAttribNames.add(attribute.substring(3));
			}

			editableInstAttributes = new InstAttribute[attributeNames.size() + 1];
			int i = 0;
			editableInstAttributes[i++] = getInstAttribute(VAR_METAEDGE);
			;
			for (String attributeName : listEditableAttribNames) {
				editableInstAttributes[i++] = getInstAttribute(attributeName);
			}
		} else {
			editableInstAttributes = new InstAttribute[1];
			int i = 0;
			editableInstAttributes[i++] = getInstAttribute(VAR_METAEDGE);
			;

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

	public Set<String> getDisPropEditableAttributes()
	{
		Set<String> editableAttributes =  getMetaEdge().getDisPropEditableAttributes();

		if (getInstAttribute(MetaEdge.VAR_SEMANTICDIRECTRELATION) != null && getInstAttribute(MetaEdge.VAR_SEMANTICDIRECTRELATION)
				.getObject() != null) {
			IntDirectSemanticEdge semanticRelation = (IntDirectSemanticEdge) getInstAttribute(
					MetaEdge.VAR_SEMANTICDIRECTRELATION).getObject();
			editableAttributes.addAll(semanticRelation.getDisPropEditableAttributes());
		}
		return editableAttributes;
	}
	
	public Set<String> getDisPanelVisibleAttributes()
	{
		Set<String> editableAttributes =  getMetaEdge().getDisPanelVisibleAttributes();

		if (getInstAttribute(MetaEdge.VAR_SEMANTICDIRECTRELATION) != null && getInstAttribute(MetaEdge.VAR_SEMANTICDIRECTRELATION)
				.getObject() != null) {
			IntDirectSemanticEdge semanticRelation = (IntDirectSemanticEdge) getInstAttribute(
					MetaEdge.VAR_SEMANTICDIRECTRELATION).getObject();
			editableAttributes.addAll(semanticRelation.getDisPanelVisibleAttributes());
		}
		return editableAttributes;
	}
	
	public Set<String> getDisPropVisibleAttributes()
	{
		Set<String> editableAttributes =  getMetaEdge().getDisPropVisibleAttributes();

		if (getInstAttribute(MetaEdge.VAR_SEMANTICDIRECTRELATION) != null && getInstAttribute(MetaEdge.VAR_SEMANTICDIRECTRELATION)
				.getObject() != null) {
			IntDirectSemanticEdge semanticRelation = (IntDirectSemanticEdge) getInstAttribute(
					MetaEdge.VAR_SEMANTICDIRECTRELATION).getObject();
			editableAttributes.addAll(semanticRelation.getDisPropVisibleAttributes());
		}
		return editableAttributes;
	}
	
	public Set<String> getDisPanelSpacersAttributes()
	{
		Set<String> editableAttributes =  getMetaEdge().getDisPanelSpacersAttributes();

		if (getInstAttribute(MetaEdge.VAR_SEMANTICDIRECTRELATION) != null && getInstAttribute(MetaEdge.VAR_SEMANTICDIRECTRELATION)
				.getObject() != null) {
			IntDirectSemanticEdge semanticRelation = (IntDirectSemanticEdge) getInstAttribute(
					MetaEdge.VAR_SEMANTICDIRECTRELATION).getObject();
			editableAttributes.addAll(semanticRelation.getDisPanelSpacersAttributes());
		}
		return editableAttributes;
	}
	
	public String toString() { //TODO move to superclass
		String out = "";
		// List<String> visibleAttributesNames = metaConcept
		// .getPanelVisibleAttributes();
		if (getMetaEdge() != null)
		{
		Set<String> visibleAttributesNames = getDisPanelVisibleAttributes();
		List<String> listVisibleAttributes = new ArrayList<String>();
		listVisibleAttributes.addAll(visibleAttributesNames);
		Collections.sort(listVisibleAttributes);

		// List<String> spacersAttributes = metaConcept
		// .getPanelSpacersAttributes();
		Set<String> spacersAttributes = getDisPanelSpacersAttributes();
		for (String visibleAttribute : listVisibleAttributes) {
			boolean validCondition=true;
			
			int nameEnd = visibleAttribute.indexOf("#",3);
			int varEnd = visibleAttribute.indexOf("#",nameEnd+1);
			int condEnd = visibleAttribute.indexOf("#",varEnd+1);
			
			String name = visibleAttribute.substring(3);
			if (nameEnd != -1)
			{				
				name  = visibleAttribute.substring(3, nameEnd);
				String variable = null;
				String value = null;				
				variable = visibleAttribute.substring(nameEnd+1, varEnd);
				value = visibleAttribute.substring(condEnd+1);
				Object varValue = getInstAttributes().get(variable);
				if(!varValue.equals(value))
					validCondition=false;
			}
			boolean nvar= false;
			if (name != null && validCondition) {				
				Iterator<String> spacers = spacersAttributes.iterator();				
				while (spacers.hasNext()) {					
					String spacer = spacers.next();
					if (spacer.indexOf("#" + name + "#") != -1) {
						nvar= true;
						int sp1 = spacer.indexOf("#");
						int sp2 = spacer.indexOf("#", sp1+1);
						
						out += spacer.substring(0,sp1);
						out += getInstAttributes().get(name).toString().trim();
						while (sp2 != spacer.length()) {
							int sp3 = spacer.indexOf("#", sp2+1);
							if (sp3==-1)
								{

								out += spacer.substring(sp2+1);
								break;
								}
							out += spacer.substring(sp2+1, sp3);
							
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
}
