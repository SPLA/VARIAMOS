package com.variamos.perspsupport.instancesupport;

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

import com.cfm.productline.AbstractElement;
import com.variamos.perspsupport.expressionsupport.SemanticExpression;
import com.variamos.perspsupport.semanticinterface.IntInstanceExpression;
import com.variamos.perspsupport.semanticinterface.IntSemanticElement;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.syntaxsupport.MetaConcept;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaEnumeration;
import com.variamos.perspsupport.syntaxsupport.MetaPairwiseRelation;
import com.variamos.perspsupport.syntaxsupport.MetaView;

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
public abstract class InstElement implements Serializable, EditableElement,
		Comparable<InstElement> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1699725673312851979L;

	/**
	 * Dynamic storage of modeling, semantic and simulation instance attribute
	 * instances
	 */
	public static final String VAR_AUTOIDENTIFIER = "identifier",
			VAR_INSTATTRIBUTES = "InstAttribute";

	private Map<String, Object> dynamicAttributes = new HashMap<>();

	private List<IntInstanceExpression> instanceExpressions;
	private IntSemanticElement editableSemanticElement;

	public List<IntInstanceExpression> getInstanceExpressions() {
		return instanceExpressions;
	}

	public void setInstanceExpressions(
			List<IntInstanceExpression> instanceExpressions) {
		this.instanceExpressions = instanceExpressions;
	}

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

	private String supportMetaElementIden; // AutoIdentifier

	private Map<String, String> volatileDefects;

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
		volatileDefects = new TreeMap<String, String>();
		dynamicAttributes.put(VAR_AUTOIDENTIFIER, identifier);
		dynamicAttributes.put(MetaConcept.VAR_USERIDENTIFIER, identifier);
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

	public Object getInstAttributeValue(String attributeName) {
		if (this.getInstAttribute(attributeName) != null)
			return this.getInstAttribute(attributeName).getValue();
		return null;
	}

	public void copyValuesToInstAttributes(List<InstElement> parents) {
		for (InstAttribute instAttribute : getInstAttributes().values()) {
			if (editableMetaElement != null) {
				if (instAttribute.getIdentifier().equals(
						MetaConcept.VAR_AUTOIDENTIFIER))
					instAttribute.setValue(editableMetaElement
							.getAutoIdentifier());
				if (instAttribute.getIdentifier().equals(
						MetaConcept.VAR_USERIDENTIFIER))
					instAttribute.setValue(editableMetaElement
							.getUserIdentifier());
				if (instAttribute.getIdentifier().equals("SemanticType")
						&& editableMetaElement.getTransInstSemanticElement() != null)
					instAttribute.setValue(editableMetaElement
							.getTransInstSemanticElement().getIdentifier());
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

				if (editableMetaElement instanceof MetaConcept) {
					if (instAttribute.getIdentifier().equals("TopConcept"))
						instAttribute
								.setValue(((MetaConcept) editableMetaElement)
										.isTopConcept());
					if (instAttribute.getIdentifier().equals("BackgroundColor"))
						instAttribute
								.setValue(((MetaConcept) editableMetaElement)
										.getBackgroundColor());
					if (instAttribute.getIdentifier().equals("Resizable"))
						instAttribute
								.setValue(((MetaConcept) editableMetaElement)
										.isResizable());
				}
				if (editableMetaElement instanceof MetaView) {
					if (instAttribute.getIdentifier().equals("Index"))
						instAttribute.setValue(((MetaView) editableMetaElement)
								.getIndex());
					if (instAttribute.getIdentifier().equals("PaletteNames"))
						instAttribute.setValue(((MetaView) editableMetaElement)
								.getPaletteName());
				}

				if (editableMetaElement instanceof MetaPairwiseRelation) {
					if (instAttribute.getIdentifier().equals("Palette"))
						instAttribute
								.setValue(((MetaPairwiseRelation) editableMetaElement)
										.getPalette());
				}

				if (instAttribute.getIdentifier().equals("BorderStroke"))
					instAttribute.setValue(editableMetaElement
							.getBorderStroke());

				if (instAttribute.getIdentifier().equals("value")) // TODO
																	// review
																	// what to
																	// associate
					instAttribute.setValue(editableMetaElement
							.getModelingAttributesNames(parents));
			}
			if (editableSemanticElement != null) {
				if (instAttribute.getIdentifier().equals("Identifier"))
					instAttribute.setValue(editableSemanticElement
							.getIdentifier());
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

	@SuppressWarnings("unchecked")
	public InstAttribute getInstAttribute(String name) {
		return ((Map<String, InstAttribute>) getDynamicVariable(VAR_INSTATTRIBUTES))
				.get(name);
	}

	@SuppressWarnings("unchecked")
	public List<InstAttribute> getInstAttributesList() {
		List<InstAttribute> out = new ArrayList<InstAttribute>();
		out.addAll(((Map<String, InstAttribute>) getDynamicVariable(VAR_INSTATTRIBUTES))
				.values());
		return out;
	}

	public String getInstAttributeFullIdentifier(String insAttributeLocalId) {
		// System.out.println("InstE"+ this.getIdentifier() +
		// "_"+insAttributeLocalId);
		if (this.getInstAttribute(insAttributeLocalId) == null)
			return null;
		return this.getIdentifier() + "_"
				+ this.getInstAttribute(insAttributeLocalId).getIdentifier();
	}

	public String getIdentifier() {
		return (String) getDynamicVariable(VAR_AUTOIDENTIFIER);
		// return identifier;
	}

	public String getUserIdentifier() {
		return (String) getDynamicVariable(MetaConcept.VAR_USERIDENTIFIER);
		// return identifier;
	}

	public String toString() {
		return getText(null);
	}

	@SuppressWarnings("unchecked")
	public String getText(List<InstElement> parents) {
		String out = "";
		String out2 = "";
		if (getEditableMetaElement() != null) {
			out2 = "\n";
			Set<String> modelingAttributes = getEditableMetaElement()
					.getDeclaredModelingAttributesNames();
			for (String attributeName : modelingAttributes) {
				if (!attributeName.equals(MetaConcept.VAR_USERIDENTIFIER)
						&& !attributeName.equals("identifier")
						&& !attributeName.equals("Description")) {
					AbstractAttribute i = getEditableMetaElement()
							.getModelingAttribute(attributeName, parents);
					String v = "";
					if (i != null)
						v = ":" + i.getType();
					// System.out.println(attributeName);
					out2 += attributeName + v + "\n";
				}
			}
		}
		if (getEditableSemanticElement() != null) {
			out2 = "\n";
			Set<String> modelingAttributes = getEditableSemanticElement()
					.getDeclaredSemanticAttributes();
			for (String attributeName : modelingAttributes) {
				if (!attributeName.equals(MetaConcept.VAR_USERIDENTIFIER)
						&& !attributeName.equals("identifier")
						&& !attributeName.equals("Description"))
					out2 += attributeName + "\n";
			}
		}
		// TODO: Remove when Claim is no longer a OverTwoRelation
		if (this.getInstAttribute("Name") != null
				&& this.getInstAttribute("Name").getValue().equals("Claim"))
			out = "<<MetaConcept>>\nClaim\n\n";
		else if (getTransSupportMetaElement() != null) {
			Set<String> visibleAttributesNames = getTransSupportMetaElement()
					.getPanelVisibleAttributesSet(parents);
			List<String> listVisibleAttributes = new ArrayList<String>();
			listVisibleAttributes.addAll(visibleAttributesNames);
			Collections.sort(listVisibleAttributes);
			Set<String> spacersAttributes = getTransSupportMetaElement()
					.getPanelSpacersAttributesSet(parents);
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
					if (varValue == null || varValue.getValue() == null)
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
								// System.out.println(this.getIdentifier());
								// if (instAttribute != null) {
								if (instAttribute.getType() != null
										&& instAttribute.getType()
												.equals("Set"))
									for (InstAttribute e : (Collection<InstAttribute>) instAttribute
											.getValue())
										out += e.toString().trim() + "\n";
								else
									out += instAttribute.toString().trim();
								// }
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
								&& getInstAttributes().get(name) != null
								&& getInstAttributes().get(name).toString()
										.trim().equals(""))
							out += "<<NoName>>";
						else {
							InstAttribute instAttribute = getInstAttributes()
									.get(name);
							if (instAttribute.getEnumType() != null
									&& instAttribute.getEnumType().equals(
											InstEnumeration.class
													.getCanonicalName())) {
								out += (String) instAttribute.getValue();
								if (instAttribute.getValueObject() != null) {
									Map<String, InstAttribute> o = (Map<String, InstAttribute>) ((InstEnumeration) instAttribute
											.getValueObject())
											.getDynamicVariable("InstAttribute");
									InstAttribute oo = o
											.get(MetaEnumeration.VAR_METAENUMVALUE);
									Collection<InstAttribute> ooo = (Collection<InstAttribute>) oo
											.getInstAttributeAttribute("Value");
									String outt = "{ ";
									for (InstAttribute i : ooo) {
										String values[] = ((String) i
												.getValue()).split("-");
										outt += values[1] + ", ";
									}
									outt = outt.substring(0, outt.length() - 2);
									outt += " }";
									out += AbstractElement.multiLine(outt, 40);
								}
							} else
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

	public void createInstAttributes(List<InstElement> parents) {
		if (getTransSupportMetaElement() != null) {
			Set<String> names = getTransSupportMetaElement()
					.getModelingAttributesNames(parents);
			names.addAll(getTransSupportMetaElement().getAllAttributesNames(
					parents));
			if (getInstAttributes().keySet().equals(names))
				return;
			names = getTransSupportMetaElement().getModelingAttributesNames(
					parents);
			Iterator<String> modelingAttributes = names.iterator();
			while (modelingAttributes.hasNext()) {
				String name = modelingAttributes.next();
				if (name.equals(MetaElement.VAR_AUTOIDENTIFIER))
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name, parents),
							getIdentifier());
				else if (name.equals(MetaElement.VAR_USERIDENTIFIER))
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name, parents),
							getUserIdentifier());
				else if (name.equals(MetaElement.VAR_DESCRIPTION))
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name, parents),
							getTransSupportMetaElement().getDescription());
				else
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name, parents), null);
			}

			if (getTransSupportMetaElement() instanceof MetaElement
					&& getTransSupportMetaElement()
							.getTransInstSemanticElement() != null) {
				InstElement instElement = (InstElement) getTransSupportMetaElement()
						.getTransInstSemanticElement();
				IntSemanticElement metaConcept = instElement
						.getEditableSemanticElement();
				Iterator<String> semanticAttributes = instElement
						.getEditableSemanticElement()
						.getAllAttributesNames(parents).iterator(); // TODO get
																	// parents
																	// attributes
																	// too.
				while (semanticAttributes.hasNext()) {
					String name = semanticAttributes.next();
					// System.out.println(name + "\n");
					if (name.equals(MetaElement.VAR_AUTOIDENTIFIER))
						addInstAttribute(name,
								metaConcept.getSemanticAttribute(name),
								getIdentifier());
					else if (name.equals(MetaElement.VAR_USERIDENTIFIER))
						addInstAttribute(name,
								metaConcept.getSemanticAttribute(name),
								getUserIdentifier());
					else if (name.equals(MetaElement.VAR_DESCRIPTION))
						addInstAttribute(name,
								metaConcept.getSemanticAttribute(name),
								getTransSupportMetaElement().getDescription());
					else if (name.equals("relationTypesAttributes")
							|| name.equals("relationTypesSemExpressions")) {
						addInstAttribute(name,
								metaConcept.getSemanticAttribute(name),
								new ArrayList<SemanticExpression>());
					} else
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

	@Override
	public int compareTo(InstElement view) {
		String index = this.getInstAttribute("Index").getValue()
				+ view.getIdentifier();
		String other = view.getInstAttribute("Index").getValue()
				+ view.getIdentifier();
		return index.compareTo(other);
	}

	public boolean isChild(InstElement element) {
		if (this.getTransSupportMetaElement().getTransInstSemanticElement()
				.getIdentifier().equals(element.getIdentifier())
				|| this.getIdentifier().equals(element.getIdentifier()))
			return true;

		for (InstElement rel : this.getTransSupportMetaElement()
				.getTransInstSemanticElement().getTargetRelations())
			if (((InstPairwiseRelation) rel).getSupportMetaPairwiseRelIden()
					.equals("ExtendsRelation"))
				return rel.getTargetRelations().get(0).isChild(element);

		for (InstElement rel : this.getTargetRelations())
			if ((rel instanceof InstPairwiseRelation)
					&& ((InstPairwiseRelation) rel)
							.getSupportMetaPairwiseRelIden().equals(
									"ExtendsRelation"))
				return rel.getTargetRelations().get(0).isChild(element);

		return false;
	}
}