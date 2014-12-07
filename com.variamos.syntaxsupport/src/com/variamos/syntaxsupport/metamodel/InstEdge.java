package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cfm.productline.Prototype;
import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metametamodel.MetaGroupDependency;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

public class InstEdge implements Serializable, Prototype,
EditableElement{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6134025886276124795L;
	private String identifier;
//	private MetaEdge metaEdge;
	private InstElement fromRelation;
	private InstElement toRelation;
	private List<InstAttribute> attributes;
	public static final String VAR_METAEDGEIDE= "MetaEdgeIde",
			VAR_METAEDGE= "MetaEdge",
			VAR_INSTATTRIBUTES = "InstAttribute",
					VAR_METAEDGECLASS = "com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency";
	
	protected Map<String, Object> vars = new HashMap<>();
	
	public InstEdge()
	{
		this(new HashMap<String, InstAttribute>());
		vars.put(VAR_METAEDGE, new SemanticAttribute(VAR_METAEDGE,"Class",VAR_METAEDGECLASS,"",""));
		vars.put(VAR_METAEDGEIDE, "");
		addInstAttribute(VAR_METAEDGEIDE, (SemanticAttribute)vars.get(VAR_METAEDGE),"");
	}
	
	public InstEdge(Map<String, InstAttribute> instAttributes)
	{
		vars.put(VAR_INSTATTRIBUTES, instAttributes);
		
	}
	
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getMetaEdgeIdentifier() {
		// return metaConcept.getIdentified();
		return (String) vars.get(VAR_METAEDGEIDE);
	}
	
	public void addInstAttribute(String name, AbstractAttribute modelingAttribute,
			Object value) {
		if (getInstAttribute(name) == null) {
			InstAttribute instAttribute = new InstAttribute(name,
					modelingAttribute,
					value == null ? modelingAttribute.getDefaultValue() : value);
			getInstAttributes().put(name, instAttribute);
			//instAttributes.put(name, instAttribute);
		}
	}
	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}
	
	public MetaEdge getMetaEdge() {
		if (getInstAttribute(VAR_METAEDGE) != null)
			return (MetaEdge)(getInstAttribute(VAR_METAEDGE).getObject());
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
		// return new InstAttribute[0];
		InstAttribute[] editableInstAttributes = null;
		if (getMetaEdge() != null) {
			Set<String> attributesNames = getMetaEdge()
					.getDisPropEditableAttributes();
			List<String> listEditableAttributes = new ArrayList<String>();
			listEditableAttributes.addAll(attributesNames);
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

			editableInstAttributes = new InstAttribute[attributesNames.size() + 1];
			int i = 0;
			editableInstAttributes[i++] = getInstAttribute(VAR_METAEDGEIDE);
			;
			for (String attributeName : listEditableAttribNames) {
				editableInstAttributes[i++] = getInstAttribute(attributeName);
			}
		} else {
			editableInstAttributes = new InstAttribute[1];
			int i = 0;
			editableInstAttributes[i++] = getInstAttribute(VAR_METAEDGEIDE);
			;

		}

		return editableInstAttributes;

	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, InstAttribute> getInstAttributes() {
		return (Map<String, InstAttribute>)getVariable(VAR_INSTATTRIBUTES);
	}

	@SuppressWarnings("unchecked")
	public InstAttribute getInstAttribute(String name) {
		return ((Map<String, InstAttribute>)getVariable(VAR_INSTATTRIBUTES)).get(name);
	//	return instAttributes.get(name);
	}
	public Object getVariable(String name){
		return vars.get(name);
	}
	
	public void setVariable(String name, Object value){
		vars.put(name, value);
	}	

}
