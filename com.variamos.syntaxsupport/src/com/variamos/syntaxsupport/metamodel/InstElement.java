package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;
import com.variamos.syntaxsupport.metametamodel.MetaElement;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;

public abstract class InstElement implements Serializable, EditableElement {
	/**
	 * Dynamic storage of modeling, semantic and simulation instance attribute
	 * instances
	 */
	public static final String VAR_IDENTIFIER = "identifier",
			VAR_INSTATTRIBUTES = "InstAttribute";

	protected Map<String, Object> vars = new HashMap<>();

	protected IntSemanticElement editableSemanticElement;

	protected MetaElement editableMetaElement;
	

	/**
	 * The elements incoming to the element
	 */
	protected List<InstElement> sourceRelations;
	/**
	 * The elements outgoing from the element
	 */
	protected List<InstElement> targetRelations;

	public InstElement(String identifier) {
		sourceRelations = new ArrayList<InstElement>();
		targetRelations = new ArrayList<InstElement>();
		vars.put(VAR_IDENTIFIER, identifier);
	}

	public List<InstElement> getTargetRelations() {
		return targetRelations;
	}

	public void addTargetRelation(InstElement target) {
		this.targetRelations.add(target);
	}

	public List<InstElement> getSourceRelations() {
		return sourceRelations;
	}

	public void addSourceRelation(InstElement source) {
		this.sourceRelations.add(source);
	}

	
	public void copyValuesToInstAttributes() {
		for (InstAttribute instAttribute : getInstAttributes().values()) {
			if (editableMetaElement != null) {
				if (instAttribute.getIdentifier().equals("Identifier"))
					instAttribute.setValue(editableMetaElement.getIdentifier());
				if (instAttribute.getIdentifier().equals("Visible"))
					instAttribute.setValue(editableMetaElement.getVisible());
				if (instAttribute.getIdentifier().equals("Name"))
					instAttribute.setValue(editableMetaElement.getName());
				if (instAttribute.getIdentifier().equals("Style"))
					instAttribute.setValue(editableMetaElement.getStyle());
				if (instAttribute.getIdentifier().equals("Description"))
					instAttribute
							.setValue(editableMetaElement.getDescription());
				if (instAttribute.getIdentifier().equals("Width"))
					instAttribute.setValue(editableMetaElement.getWidth());
				if (instAttribute.getIdentifier().equals("Height"))
					instAttribute.setValue(editableMetaElement.getHeight());
				if (instAttribute.getIdentifier().equals("Image"))
					instAttribute.setValue(editableMetaElement.getImage());
				if (instAttribute.getIdentifier().equals("TopConcept"))
					instAttribute.setValue(((MetaConcept) editableMetaElement)
							.isTopConcept());
				if (instAttribute.getIdentifier().equals("BackgroundColor"))
					instAttribute.setValue(((MetaConcept) editableMetaElement)
							.getBackgroundColor());
				if (instAttribute.getIdentifier().equals("BorderStroke"))
					instAttribute.setValue(editableMetaElement
							.getBorderStroke());
				if (instAttribute.getIdentifier().equals("Resizable"))
					instAttribute.setValue(((MetaConcept) editableMetaElement)
							.isResizable());
				if (instAttribute.getIdentifier().equals("value"))
					instAttribute.setValue(editableMetaElement
							.getModelingAttributes());
			}
			if (editableSemanticElement != null) {
				if (instAttribute.getIdentifier().equals("Identifier"))
					instAttribute.setValue(editableSemanticElement.getIdentifier());
			}
		}
	}

	public IntSemanticElement getEditableSemanticElement() {
		return editableSemanticElement;
	}

	public void setEditableSemanticElement(
			IntSemanticElement editableSemanticElement) {
		this.editableSemanticElement = editableSemanticElement;
	}

	public MetaElement getEditableMetaElement() {
		return editableMetaElement;
	}

	public abstract MetaElement getSupportMetaElement();

	public void setEditableMetaElement(MetaElement metaElement) {
		this.editableMetaElement = metaElement;
	}

	public Object getVariable(String name) {
		return vars.get(name);
	}

	public InstAttribute getInstAttribute(String name) {
		return ((Map<String, InstAttribute>) getVariable(VAR_INSTATTRIBUTES))
				.get(name);
		// return instAttributes.get(name);
	}

	public String getInstAttributeFullIdentifier(String insAttributeLocalId) {
		return this.getIdentifier() + "_"
				+ this.getInstAttribute(insAttributeLocalId).getIdentifier();
	}
	public String getIdentifier() {
		return (String) getVariable(VAR_IDENTIFIER);
		// return identifier;
	}		
	
	public String toString() { // TODO move to superclass
		String out = "";
		// List<String> visibleAttributesNames = metaConcept
		// .getPanelVisibleAttributes();
		if (getSupportMetaElement() != null) {
			Set<String> visibleAttributesNames = getSupportMetaElement()
					.getDisPanelVisibleAttributes();
			List<String> listVisibleAttributes = new ArrayList<String>();
			listVisibleAttributes.addAll(visibleAttributesNames);
			Collections.sort(listVisibleAttributes);

			// List<String> spacersAttributes = metaConcept
			// .getPanelSpacersAttributes();
			Set<String> spacersAttributes = getSupportMetaElement()
					.getDisPanelSpacersAttributes();
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
							if (name.equals("name")
									&& getInstAttributes().get(name).toString()
											.trim().equals(""))
								out += "<<NoName>>";
							{
								InstAttribute instAttribute = getInstAttributes()
										.get(name);
								if (instAttribute.getEnumType() != null
										&& instAttribute.getEnumType().equals(
												InstEnumeration.class
														.getCanonicalName()))
									out += (String) instAttribute.getValue(); // TODO
																				// retrieve
																				// the
																				// values
								else
									out += instAttribute.toString().trim();
							}
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
						if (name.equals("name")
								&& getInstAttributes().get(name).toString()
										.trim().equals(""))
							out += "<<NoName>>";
						else {
							InstAttribute instAttribute = getInstAttributes()
									.get(name);
							if (instAttribute.getEnumType() != null
									&& instAttribute.getEnumType().equals(
											InstEnumeration.class
													.getCanonicalName()))
								out += (String) instAttribute.getValue(); // TODO
																			// retrieve
																			// the
																			// list
																			// of
																			// values
							else
								out += instAttribute.toString().trim();
						}
				}
			}
			if (out.equals(""))
				out = "No display attributes defined";
		}
		return out;
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
	
	
	protected void createInstAttributes() {
		if (getSupportMetaElement() != null) {
			Iterator<String> modelingAttributes = getSupportMetaElement()
					.getModelingAttributes().iterator();
			while (modelingAttributes.hasNext()) {
				String name = modelingAttributes.next();
				if (name.equals(MetaElement.VAR_IDENTIFIER))
					addInstAttribute(name, getSupportMetaElement()
							.getModelingAttribute(name), getIdentifier());
				else if (name.equals(MetaElement.VAR_DESCRIPTION))
					addInstAttribute(name, getSupportMetaElement()
							.getModelingAttribute(name), getSupportMetaElement()
							.getDescription());
				else
					addInstAttribute(name, getSupportMetaElement()
							.getModelingAttribute(name), null);
			}

			if (getSupportMetaElement() instanceof MetaConcept)
			{
				MetaConcept metaConcept = (MetaConcept) getSupportMetaElement();
			Iterator<String> semanticAttributes = metaConcept
					.getSemanticAttributes().iterator();
			while (semanticAttributes.hasNext()) {
				String name = semanticAttributes.next();
				if (name.equals(MetaElement.VAR_IDENTIFIER))
					addInstAttribute(name, metaConcept
							.getSemanticAttribute(name), getIdentifier());
				else if (name.equals(MetaElement.VAR_DESCRIPTION))
					addInstAttribute(name, metaConcept
							.getSemanticAttribute(name), getSupportMetaElement()
							.getDescription());
				else
					addInstAttribute(name, metaConcept
							.getSemanticAttribute(name), null);
			}
			}
		}
	}
}
