package com.variamos.syntaxsupport.metamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.MetaPairwiseRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaView;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;

public class InstView extends InstElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715363504436780438L;

	private List<InstView> childViews;

	private List<InstVertex> instVertices;

	private MetaConcept supportMetaConcept;

	public List<InstVertex> getInstVertices() {
		return instVertices;
	}

	public void setInstVertices(List<InstVertex> instVertexs) {
		this.instVertices = instVertexs;
	}

	public List<InstView> getChildViews() {
		return childViews;
	}

	public void setChildViews(List<InstView> childViews) {
		this.childViews = childViews;
	}

	public InstView(String identifier) {
		super(identifier);
		vars.put(VAR_INSTATTRIBUTES, new HashMap<String, InstAttribute>());
		instVertices = new ArrayList<InstVertex>();
		childViews = new ArrayList<InstView>();
		createInstAttributes();
		copyValuesToInstAttributes();
	}

	public InstView(String identifier, MetaConcept metaConcept,
			MetaElement editableMetaElement) {
		super(identifier);
		vars.put(VAR_INSTATTRIBUTES, new HashMap<String, InstAttribute>());
		this.supportMetaConcept = metaConcept;
		instVertices = new ArrayList<InstVertex>();
		childViews = new ArrayList<InstView>();
		setEditableMetaElement(editableMetaElement);
		createInstAttributes();
		copyValuesToInstAttributes();
	}

	public InstView(MetaConcept metaConcept) {
		super("");
		this.supportMetaConcept = metaConcept;
		instVertices = new ArrayList<InstVertex>();
		childViews = new ArrayList<InstView>();
	}

	@Override
	public String getIdentifier() {
		return super.getIdentifier();

	}

	@Override
	public List<InstAttribute> getEditableVariables() {
		// superclass
		Set<String> attributesNames = getSupportMetaView()
				.getPropEditableAttributes();
		return getFilteredInstAttributes(attributesNames, null);
	}

	@Override
	public Map<String, InstAttribute> getInstAttributes() {
		return (Map<String, InstAttribute>) getVariable(VAR_INSTATTRIBUTES);
	}

	@Override
	public List<InstAttribute> getVisibleVariables() {
		Set<String> attributesNames = this.getSupportMetaView()
				.getPropVisibleAttributes();
		return getFilteredInstAttributes(attributesNames, null);
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
			int valueEnd = attribute.indexOf("#", condEnd + 1);
			if (nameEnd != -1) {
				String name = null;
				String type = null;
				String variable = null;
				String condition = null;
				String value = null;
				String defvalue = null;
				name = attribute.substring(3, nameEnd);
				variable = attribute.substring(nameEnd + 1, varEnd);
				condition = attribute.substring(varEnd + 1, condEnd);
				if (valueEnd != -1) {
					value = attribute.substring(condEnd + 1, valueEnd);
					type = getInstAttributes().get(name)
							.getAttributeType();
					defvalue = attribute.substring(valueEnd + 1);
				} else
					value = attribute.substring(condEnd + 1);
				InstAttribute varValue = getInstAttributes().get(variable);
				if (varValue == null) {
					if (valueEnd != -1)
						getInstAttributes().get(name).setValue(
								createValue(type, defvalue));
					continue;
				} else if (varValue.getValue().toString().trim().equals(value)) {
					if (condition.equals("!=")) {
						if (valueEnd != -1)
							getInstAttributes().get(name).setValue(
									createValue(type, defvalue));
						continue;
					}
				} else {
					if (condition.equals("==")) {
						if (valueEnd != -1)
							getInstAttributes().get(name).setValue(
									createValue(type, defvalue));
						continue;
					}
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

	private Object createValue(String type, String value) {
		if (type.equals("Boolean"))
			return new Boolean(value);
		if (type.equals("Integer"))
			return new Integer(value);
		return value;

	}

	public MetaConcept getSupportMetaView() {
		return supportMetaConcept;
	}

	@Override
	public MetaElement getSupportMetaElement() {
		return supportMetaConcept;
	}

	public void addInstVertex(InstVertex instVertex) {
		instVertices.add(instVertex);
	}

	public void addChildView(InstView instView) {
		childViews.add(instView);
	}
}
