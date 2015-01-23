package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;
import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;

/**
 * A class to represented modeling elements from meta model and semantic model
 * on VariaMos. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-21 *
 * @see com.variamos.syntaxsupport.metamodel.InsVertex
 * @see com.variamos.syntaxsupport.metamodel.InsEdge
 */
public abstract class InstElement implements Serializable, EditableElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1699725673312851979L;

	/**
	 * Dynamic storage of modeling, semantic and simulation instance attribute
	 * instances
	 */
	public static final String VAR_IDENTIFIER = "identifier",
			VAR_INSTATTRIBUTES = "InstAttribute";

	private Map<String, Object> dynamicAttributes = new HashMap<>();

	private IntSemanticElement editableSemanticElement;

	private MetaElement editableMetaElement;

	/**
	 * The elements incoming to the element
	 */
	private List<InstElement> volatileSourceRelations;
	/**
	 * The elements outgoing from the element
	 */
	private List<InstElement> volatileTargetRelations;

	private boolean optional = false;

	private String supportMetaElementIden;
	
	private Map<String,String> volatileDefects;

	public Map<String, String> getDefects() {
		return volatileDefects;
	}

	public void setDefects(Map<String, String> defects) {
		this.volatileDefects = defects;
	}

	public void putDefect(String identifier, String defect) {
		this.volatileDefects.put(identifier, defect);
	}
	
	public void removeDefect(String identifier) {
		this.volatileDefects.remove(identifier);
	}
	
	public InstElement(String identifier) {
		volatileSourceRelations = new ArrayList<InstElement>();
		volatileTargetRelations = new ArrayList<InstElement>();
		volatileDefects = new TreeMap<String,String>() ;
		dynamicAttributes.put(VAR_IDENTIFIER, identifier);
	}

	public boolean isOptional() {
		return optional;
	}

	public List<InstElement> getTargetRelations() {
		return volatileTargetRelations;
	}

	public void addTargetRelation(InstElement target, boolean firstCall) {
		this.volatileTargetRelations.add(target);
		if (firstCall)
			target.addSourceRelation(this, false);
	}

	public List<InstElement> getSourceRelations() {
		return volatileSourceRelations;
	}

	public void addSourceRelation(InstElement source, boolean firstCall) {
		this.volatileSourceRelations.add(source);
		if (firstCall)
			source.addTargetRelation(this, false);
	}

	public void copyValuesToInstAttributes() {
		for (InstAttribute instAttribute : getInstAttributes().values()) {
			if (editableMetaElement != null) {
				if (instAttribute.getIdentifier().equals("Parent"))
					instAttribute.setValue(editableMetaElement.getParent());
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
					if (editableMetaElement instanceof MetaConcept) {
						instAttribute
								.setValue(((MetaConcept) editableMetaElement)
										.isTopConcept());
						if (instAttribute.getIdentifier().equals(
								"BackgroundColor"))
							instAttribute
									.setValue(((MetaConcept) editableMetaElement)
											.getBackgroundColor());
						if (instAttribute.getIdentifier().equals("Resizable"))
							instAttribute
									.setValue(((MetaConcept) editableMetaElement)
											.isResizable());
					}
				if (instAttribute.getIdentifier().equals("BorderStroke"))
					instAttribute.setValue(editableMetaElement
							.getBorderStroke());

				if (instAttribute.getIdentifier().equals("value"))
					instAttribute.setValue(editableMetaElement
							.getModelingAttributesNames());
			}
			if (editableSemanticElement != null) {
				if (instAttribute.getIdentifier().equals("Identifier"))
					instAttribute.setValue(editableSemanticElement
							.getIdentifier());
				if (instAttribute.getIdentifier().equals("Parent"))
					instAttribute.setValue(editableSemanticElement.getParent());
			}
		}
	}

	public Map<String, Object> getDynamicAttributes() {
		return dynamicAttributes;
	}

	public void setDynamicAttributes(Map<String, Object> dynamicAttributesMap) {
		this.dynamicAttributes = dynamicAttributesMap;
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

	public String getSupportMetaElementIden() {
		return supportMetaElementIden;
	}

	public void setSupportMetaElementIden(String supportMetaElementIden) {
		this.supportMetaElementIden = supportMetaElementIden;
	}

	public abstract MetaElement getTransSupportMetaElement();

	public abstract void setTransSupportMetaElement(
			MetaElement supportMetaElement);

	public void setEditableMetaElement(MetaElement metaElement) {
		this.editableMetaElement = metaElement;
	}

	public Object getDynamicVariable(String name) {
		return dynamicAttributes.get(name);
	}

	public void setDynamicVariable(String name, Object value) {
		dynamicAttributes.put(name, value);
	}

	public InstAttribute getInstAttribute(String name) {
		return ((Map<String, InstAttribute>) getDynamicVariable(VAR_INSTATTRIBUTES))
				.get(name);
	}

	public List<InstAttribute> getInstAttributesList() {
		List<InstAttribute> out = new ArrayList<InstAttribute>();
		out.addAll(((Map<String, InstAttribute>) getDynamicVariable(VAR_INSTATTRIBUTES))
				.values());
		return out;
	}

	public String getInstAttributeFullIdentifier(String insAttributeLocalId) {
		return this.getIdentifier() + "_"
				+ this.getInstAttribute(insAttributeLocalId).getIdentifier();
	}

	public String getIdentifier() {
		return (String) getDynamicVariable(VAR_IDENTIFIER);
		// return identifier;
	}

	@SuppressWarnings("unchecked")
	public String toString() {
		String out = "";
		String out2 = "";
		if (getEditableMetaElement() != null
				&& getEditableMetaElement() != null) {
			out2 = "\n";
			Set<String> modelingAttributes = getEditableMetaElement()
					.getDeclaredModelingAttributesNames();
			for (String attributeName : modelingAttributes) {
				if (!attributeName.equals("identifier")
						&& !attributeName.equals("Description"))
					out2 += attributeName + "\n";
			}
		}
		if (getEditableSemanticElement() != null) {
			out2 = "\n";
			Set<String> modelingAttributes = getEditableSemanticElement()
					.getSemanticAttributes().keySet();
			for (String attributeName : modelingAttributes) {
				if (!attributeName.equals("identifier")
						&& !attributeName.equals("Description"))
					out2 += attributeName + "\n";
			}
		}

		if (getTransSupportMetaElement() != null) {
			Set<String> visibleAttributesNames = getTransSupportMetaElement()
					.getPanelVisibleAttributes();
			List<String> listVisibleAttributes = new ArrayList<String>();
			listVisibleAttributes.addAll(visibleAttributesNames);
			Collections.sort(listVisibleAttributes);
			Set<String> spacersAttributes = getTransSupportMetaElement()
					.getPanelSpacersAttributes();
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
							else {
								InstAttribute instAttribute = getInstAttributes()
										.get(name);
								if (instAttribute.getAttributeType().equals(
										"Set"))
									for (InstAttribute e : (Collection<InstAttribute>) instAttribute
											.getValue())
										out += e.toString().trim() + "\n";
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
		return out + out2;
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
		if (getTransSupportMetaElement() != null) {
			Iterator<String> modelingAttributes = getTransSupportMetaElement()
					.getModelingAttributesNames().iterator();
			while (modelingAttributes.hasNext()) {
				String name = modelingAttributes.next();
				if (name.equals(MetaElement.VAR_IDENTIFIER))
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name), getIdentifier());
				else if (name.equals(MetaElement.VAR_DESCRIPTION))
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name),
							getTransSupportMetaElement().getDescription());
				else
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name), null);
			}

			if (getTransSupportMetaElement() instanceof MetaConcept) {
				MetaConcept metaConcept = (MetaConcept) getTransSupportMetaElement();
				Iterator<String> semanticAttributes = metaConcept
						.getSemanticAttributes().iterator();
				while (semanticAttributes.hasNext()) {
					String name = semanticAttributes.next();
					if (name.equals(MetaElement.VAR_IDENTIFIER))
						addInstAttribute(name,
								metaConcept.getSemanticAttribute(name),
								getIdentifier());
					else if (name.equals(MetaElement.VAR_DESCRIPTION))
						addInstAttribute(name,
								metaConcept.getSemanticAttribute(name),
								getTransSupportMetaElement().getDescription());
					else
						addInstAttribute(name,
								metaConcept.getSemanticAttribute(name), null);
				}
			}
		}
	}

	public void setTargetRelation(InstElement targetRelation, boolean firstCall) {
		removeTargetRelations();
		addTargetRelation(targetRelation, firstCall);

	}

	public void clearRelations() {
		removeTargetRelations();
		removeSourceRelations();

	}

	public void setSourceRelation(InstElement sourceRelation, boolean firstCall) {
		removeSourceRelations();
		addSourceRelation(sourceRelation, firstCall);
	}

	protected void removeSourceRelations() {
		for (InstElement instElement : volatileSourceRelations)
			instElement.removeTargetRelation(this);
		volatileSourceRelations.clear();

	}

	private void removeTargetRelation(InstElement instElement) {
		volatileTargetRelations.remove(instElement);

	}

	protected void removeTargetRelations() {
		for (InstElement instElement : volatileTargetRelations)
			instElement.removeSourceRelation(this);
		volatileTargetRelations.clear();

	}

	private void removeSourceRelation(InstElement instElement) {
		volatileSourceRelations.remove(instElement);

	}

	public void setOptional(boolean optional) {
		this.optional = optional;

	}

	public void clearEditableMetaVertex() {
		editableMetaElement = null;
		editableSemanticElement = null;
	};

	public void clearMetaPairwiseRelation(String attribute) {
		dynamicAttributes.put(attribute, null);
	}

	public void clearInstAttributesObjects() {
		for (InstAttribute attribute : this.getInstAttributes().values()) {
			attribute.setValueObject(null);
			attribute.clearModelingAttribute();
		}
	}
	public void clearDefects() {
		volatileDefects.clear();
		
	}
}