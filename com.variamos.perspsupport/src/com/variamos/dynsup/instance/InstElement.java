package com.variamos.dynsup.instance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.cfm.productline.AbstractElement;
import com.variamos.dynsup.interfaces.IntInstElement;
import com.variamos.dynsup.interfaces.IntModelExpression;
import com.variamos.dynsup.interfaces.IntOpersElement;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.OpersExpr;
import com.variamos.dynsup.model.OpersSubOperationExpType;
import com.variamos.dynsup.model.SyntaxConcept;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.model.SyntaxPairwiseRel;
import com.variamos.dynsup.model.SyntaxView;
import com.variamos.hlcl.LabelingOrder;

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
public abstract class InstElement implements Serializable, IntInstElement,
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
			VAR_INSTATTRIBUTES = "InstAtt";

	private Map<String, Object> dynamicAttributes = new HashMap<>();

	private List<IntModelExpression> modExp;
	private IntOpersElement edOperEle;
	private SyntaxElement edSyntaxEle;
	private SyntaxElement supportMetaElement;

	/**
	 * The elements incoming to the element
	 */
	private List<InstElement> volatileSourceRelations;
	/**
	 * The elements outgoing from the element
	 */
	private List<InstElement> volatileTargetRelations;

	private boolean optional = false;

	private String supSyntaxEleId; // AutoIdentifier

	private Map<String, String> volatileDefects;

	public InstElement(String identifier) {
		this(identifier, new HashMap<String, InstAttribute>());
	}

	public InstElement(String identifier,
			Map<String, InstAttribute> instAttributes) {
		volatileSourceRelations = new ArrayList<InstElement>();
		volatileTargetRelations = new ArrayList<InstElement>();
		volatileDefects = new TreeMap<String, String>();
		dynamicAttributes.put(VAR_AUTOIDENTIFIER, identifier);
		dynamicAttributes.put(SyntaxConcept.VAR_USERIDENTIFIER, identifier);
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_INSTATTRIBUTES, instAttributes);
	}

	public List<IntModelExpression> getModExp() {
		return modExp;
	}

	public void setModExp(List<IntModelExpression> instanceExpressions) {
		this.modExp = instanceExpressions;
	}

	public Map<String, String> getDefects() {
		return volatileDefects;
	}

	public void setInstAttribute(String name, Object value) {
		if (getInstAttribute(name) != null)
			getInstAttribute(name).setValue(value);
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
			if (edSyntaxEle != null) {
				if (instAttribute.getIdentifier().equals(
						SyntaxConcept.VAR_AUTOIDENTIFIER))
					instAttribute.setValue(edSyntaxEle.getAutoIdentifier());
				if (instAttribute.getIdentifier().equals(
						SyntaxConcept.VAR_USERIDENTIFIER))
					instAttribute.setValue(edSyntaxEle.getUserIdentifier());
				if (instAttribute.getIdentifier().equals("OperationsMMType")
						&& edSyntaxEle.getTransInstSemanticElement() != null)
					instAttribute.setValue(edSyntaxEle
							.getTransInstSemanticElement().getIdentifier());
				if (instAttribute.getIdentifier().equals("Visible"))
					instAttribute.setValue(edSyntaxEle.getVisible());
				if (instAttribute.getIdentifier().equals("Name"))
					instAttribute.setValue(edSyntaxEle.getName());
				if (instAttribute.getIdentifier().equals("Style"))
					instAttribute.setValue(edSyntaxEle.getStyle());
				if (instAttribute.getIdentifier().equals("Description"))
					instAttribute.setValue(edSyntaxEle.getDescription());
				if (instAttribute.getIdentifier().equals("Width"))
					instAttribute.setValue(edSyntaxEle.getWidth());
				if (instAttribute.getIdentifier().equals("Height"))
					instAttribute.setValue(edSyntaxEle.getHeight());
				if (instAttribute.getIdentifier().equals("Image"))
					instAttribute.setValue(edSyntaxEle.getImage());

				if (edSyntaxEle instanceof SyntaxConcept) {
					if (instAttribute.getIdentifier().equals("TopConcept"))
						instAttribute.setValue(((SyntaxConcept) edSyntaxEle)
								.isTopConcept());
					if (instAttribute.getIdentifier().equals("BackgroundColor"))
						instAttribute.setValue(((SyntaxConcept) edSyntaxEle)
								.getBackgroundColor());
					if (instAttribute.getIdentifier().equals("Resizable"))
						instAttribute.setValue(((SyntaxConcept) edSyntaxEle)
								.isResizable());
				}
				if (edSyntaxEle instanceof SyntaxView) {
					if (instAttribute.getIdentifier().equals("Index"))
						instAttribute.setValue(((SyntaxView) edSyntaxEle)
								.getIndex());
					if (instAttribute.getIdentifier().equals("PaletteNames"))
						instAttribute.setValue(((SyntaxView) edSyntaxEle)
								.getPaletteName());
				}

				if (edSyntaxEle instanceof SyntaxPairwiseRel) {
					if (instAttribute.getIdentifier().equals("Palette"))
						instAttribute
								.setValue(((SyntaxPairwiseRel) edSyntaxEle)
										.getPalette());
				}

				if (instAttribute.getIdentifier().equals("BorderStroke"))
					instAttribute.setValue(edSyntaxEle.getBorderStroke());

				if (instAttribute.getIdentifier().equals("value")) // TODO
																	// review
																	// what to
																	// associate
					instAttribute.setValue(edSyntaxEle
							.getModelingAttributesNames(parents));
			}
			if (edOperEle != null) {
				if (instAttribute.getIdentifier().equals("Identifier"))
					instAttribute.setValue(edOperEle.getIdentifier());
			}
		}
	}

	public Map<String, Object> getDynamicAttributes() {
		return dynamicAttributes;
	}

	public void setDynamicAttributes(Map<String, Object> dynamicAttributesMap) {
		this.dynamicAttributes = dynamicAttributesMap;
	}

	public IntOpersElement getEdOperEle() {
		return edOperEle;
	}

	public void setEdOperEle(IntOpersElement editableSemanticElement) {
		this.edOperEle = editableSemanticElement;
	}

	public SyntaxElement getEdSyntaxEle() {
		return edSyntaxEle;
	}

	public String getSupSyntaxEleId() {
		return supSyntaxEleId;
	}

	public void setSupSyntaxEleId(String supportMetaElementIden) {
		this.supSyntaxEleId = supportMetaElementIden;
	}

	/**
	 * Name changed from standard to avoid graph serialization of the object
	 * 
	 * @return
	 */
	public SyntaxElement getTransSupportMetaElement() {
		return supportMetaElement;
	}

	/**
	 * Name changed from standard to avoid graph serialization of the object
	 * 
	 * @return
	 */

	public void setTransSupportMetaElement(SyntaxElement supportMetaElement) {
		this.setSupSyntaxEleId(supportMetaElement.getAutoIdentifier());
		this.supportMetaElement = (SyntaxElement) supportMetaElement;
	}

	public void setIdentifier(String identifier) {
		if (getDynamicAttribute(SyntaxConcept.VAR_USERIDENTIFIER).equals("")) {
			setDynamicVariable(SyntaxConcept.VAR_USERIDENTIFIER, identifier);
			setInstAttribute(SyntaxConcept.VAR_USERIDENTIFIER, identifier);
		}
		setDynamicVariable(VAR_AUTOIDENTIFIER, identifier);
		setInstAttribute(VAR_AUTOIDENTIFIER, identifier);
		if (getEdSyntaxEle() != null)
			getEdSyntaxEle().setAutoIdentifier(identifier);
		if (supportMetaElement != null)
			setDynamicVariable(SyntaxElement.VAR_DESCRIPTION,
					getTransSupportMetaElement().getDescription());
	}

	public void setEdSyntaxEle(SyntaxElement metaElement) {
		this.edSyntaxEle = metaElement;
	}

	public Object getDynamicAttribute(String name) {
		return dynamicAttributes.get(name);
	}

	public void setDynamicVariable(String name, Object value) {
		dynamicAttributes.put(name, value);
	}

	@SuppressWarnings("unchecked")
	public InstAttribute getInstAttribute(String name) {
		if (getDynamicAttribute(VAR_INSTATTRIBUTES) != null)
			return ((Map<String, InstAttribute>) getDynamicAttribute(VAR_INSTATTRIBUTES))
					.get(name);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<InstAttribute> getInstAttributesList() {
		List<InstAttribute> out = new ArrayList<InstAttribute>();
		out.addAll(((Map<String, InstAttribute>) getDynamicAttribute(VAR_INSTATTRIBUTES))
				.values());
		return out;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, InstAttribute> getInstAttributes() {
		return (Map<String, InstAttribute>) getDynamicAttribute(VAR_INSTATTRIBUTES);
	}

	@SuppressWarnings("unchecked")
	public Collection<InstAttribute> getInstAttributesCollection() {
		return ((Map<String, InstAttribute>) getDynamicAttribute(VAR_INSTATTRIBUTES))
				.values();
	}

	public String getInstAttributeFullIdentifier(String insAttributeLocalId) {
		if (this.getInstAttribute(insAttributeLocalId) == null) {
			this.createInstAttributes(null);
			if (this.getInstAttribute(insAttributeLocalId) == null) {
				System.out.println("InstV null:" + this.getIdentifier()
						+ insAttributeLocalId);
				return null;
			}
		}
		return this.getIdentifier() + "_"
				+ this.getInstAttribute(insAttributeLocalId).getIdentifier();
	}

	public String getIdentifier() {
		return (String) getDynamicAttribute(VAR_AUTOIDENTIFIER);
		// return identifier;
	}

	public String getUserIdentifier() {
		return (String) getDynamicAttribute(SyntaxConcept.VAR_USERIDENTIFIER);
		// return identifier;
	}

	public String toString() {
		return getText(null);
	}

	@SuppressWarnings("unchecked")
	public String getText(List<InstElement> syntaxParents) {
		String out = "";
		String out2 = "";
		if (getEdSyntaxEle() != null) {
			out2 = "\n";
			// Set<String> modelingAttributes = this.getEditableMetaElement()
			// .getAllAttributesNames(parents);
			Set<String> modelingAttributes = getEdSyntaxEle()
					.getDeclaredModelingAttributesNames();
			for (String attributeName : modelingAttributes) {
				if (!attributeName.equals(SyntaxConcept.VAR_USERIDENTIFIER)
						&& !attributeName.equals("identifier")
						&& !attributeName.equals("Description")) {
					ElemAttribute i = getEdSyntaxEle().getModelingAttribute(
							attributeName, syntaxParents);
					if (i == null)
						i = getEdSyntaxEle()
								.getSemanticAttribute(attributeName);
					String v = "";
					if (i != null)
						v = ":" + i.getType();
					// System.out.println(attributeName);
					out2 += attributeName + v + "\n";
				}
			}
		}
		if (getEdOperEle() != null) {
			out2 = "\n";
			Set<String> modelingAttributes = getEdOperEle()
					.getDeclaredSemanticAttributesNames();
			for (String attributeName : modelingAttributes) {
				if (!attributeName.equals(SyntaxConcept.VAR_USERIDENTIFIER)
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
					.getPanelVisibleAttributesSet(syntaxParents);
			List<String> listVisibleAttributes = new ArrayList<String>();
			listVisibleAttributes.addAll(visibleAttributesNames);
			Collections.sort(listVisibleAttributes);
			Set<String> spacersAttributes = getTransSupportMetaElement()
					.getPanelSpacersAttributesSet(syntaxParents);
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
								if (instAttribute != null) {
									if (instAttribute.getType() != null
											&& instAttribute.getType().equals(
													"Set"))
										for (InstAttribute e : (Collection<InstAttribute>) instAttribute
												.getValue())
											out += e.toString().trim() + "\n";
									else
										out += instAttribute.toString().trim();
								}
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
											InstConcept.class
													.getCanonicalName())) {
								out += (String) instAttribute.getValue();
								if (instAttribute.getValueObject() != null) {
									Map<String, InstAttribute> o = (Map<String, InstAttribute>) ((InstElement) instAttribute
											.getValueObject())
											.getDynamicAttribute(VAR_INSTATTRIBUTES);
									InstAttribute oo = o
											.get(SyntaxConcept.VAR_METAENUMVALUE);
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

	public void addInstAttribute(String name, ElemAttribute modelingAttribute,
			Object value) {
		if (getInstAttribute(name) == null) {
			InstAttribute instAttribute = new InstAttribute(name,
					modelingAttribute,
					value == null ? modelingAttribute.getDefaultValue() : value);
			getInstAttributes().put(name, instAttribute);
			// instAttributes.put(name, instAttribute);
		}
	}

	public void createInstAttributes(List<InstElement> syntaxParents) {
		if (getTransSupportMetaElement() != null) {
			Set<String> names = getTransSupportMetaElement()
					.getModelingAttributesNames(syntaxParents);
			names.addAll(getAllAttributesNames(syntaxParents));
			if (getInstAttributes().keySet().equals(names))
				return;
			names = getTransSupportMetaElement().getModelingAttributesNames(
					syntaxParents);
			Iterator<String> modelingAttributes = names.iterator();
			while (modelingAttributes.hasNext()) {
				String name = modelingAttributes.next();
				if (name.equals(SyntaxElement.VAR_AUTOIDENTIFIER))
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name, syntaxParents),
							getIdentifier());
				else if (name.equals(SyntaxElement.VAR_USERIDENTIFIER))
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name, syntaxParents),
							getUserIdentifier());
				else if (name.equals(SyntaxElement.VAR_DESCRIPTION))
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name, syntaxParents),
							getTransSupportMetaElement().getDescription());
				else
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name, syntaxParents), null);
			}

			if (getTransSupportMetaElement() instanceof SyntaxElement
					&& getTransSupportMetaElement()
							.getTransInstSemanticElement() != null) {
				InstElement instElement = (InstElement) getTransSupportMetaElement()
						.getTransInstSemanticElement();
				Iterator<String> semanticAttributes = instElement
						.getAllAttributesNames(syntaxParents).iterator();
				while (semanticAttributes.hasNext()) {
					String name = semanticAttributes.next();
					// System.out.println(name + "\n");
					if (name.equals(SyntaxElement.VAR_AUTOIDENTIFIER))
						addInstAttribute(name,
								instElement.getSemanticAttribute(name),
								getIdentifier());
					else if (name.equals(SyntaxElement.VAR_USERIDENTIFIER))
						addInstAttribute(name,
								instElement.getSemanticAttribute(name),
								getUserIdentifier());
					else if (name.equals(SyntaxElement.VAR_DESCRIPTION))
						addInstAttribute(name,
								instElement.getSemanticAttribute(name),
								getTransSupportMetaElement().getDescription());
					else if (name.equals("relationTypesAttributes")
							|| name.equals("operationsExpressions")) {
						addInstAttribute(name,
								instElement.getSemanticAttribute(name),
								new ArrayList<OpersExpr>());
					} else if (name.equals("exptype")) {
						addInstAttribute(name,
								instElement.getSemanticAttribute(name),
								new ArrayList<OpersSubOperationExpType>());
					} else if (name.equals("sortorder")) {
						addInstAttribute(name,
								instElement.getSemanticAttribute(name),
								new ArrayList<LabelingOrder>());
					} else
						addInstAttribute(name,
								instElement.getSemanticAttribute(name), null);
				}
			}
		}
	}

	public ElemAttribute getSemanticAttribute(String name) {
		return getEdOperEle().getSemanticAttribute(name,
				getParentOpersConcept());
	}

	protected Set<String> getAllAttributesNames(List<InstElement> syntaxParents) {
		if (getEdOperEle() != null)
			return getEdOperEle().getAllAttributesNames(syntaxParents,
					getParentOpersConcept());
		return new HashSet<String>();
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
		edSyntaxEle = null;
		edOperEle = null;
	};

	public void clearMetaPairwiseRelation(String attribute) {
		dynamicAttributes.put(attribute, null);
	}

	public void clearInstAttributes() {
		this.getInstAttributes().clear();
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
				+ this.getIdentifier();
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
			if (((InstPairwiseRel) rel).getSupportMetaPairwiseRelIden().equals(
					"ExtendsRelation"))
				return rel.getTargetRelations().get(0).isChild(element);

		for (InstElement rel : this.getTargetRelations())
			if ((rel instanceof InstPairwiseRel)
					&& ((InstPairwiseRel) rel).getSupportMetaPairwiseRelIden()
							.equals("ExtendsRelation"))
				return rel.getTargetRelations().get(0).isChild(element);

		return false;
	}

	public List<InstElement> getParentOpersConcept() {
		List<InstElement> out = new ArrayList<InstElement>();
		List<InstElement> rel = getTargetRelations();
		for (InstElement element : rel) {
			if (((InstPairwiseRel) element).getSupportMetaPairwiseRelIden() != null
					&& ((InstPairwiseRel) element)
							.getSupportMetaPairwiseRelIden().equals(
									"ExtendsRelation")) {
				InstElement parent = element.getTargetRelations().get(0);
				// parent.createInstAttributes(parents);
				out.add(parent);
				out.addAll(element.getTargetRelations().get(0)
						.getParentOpersConcept());
			}
		}
		return out;
	}

	public Set<String> getPropEditableAttributes() {
		if (getEdOperEle() != null)
			return getEdOperEle().getPropEditableAttributesSet(
					getParentOpersConcept());
		return new HashSet<String>();
	}

	public Collection<String> getPropVisibleAttributes() {
		if (getEdOperEle() != null)
			return getEdOperEle().getPropVisibleAttributesSet(
					getParentOpersConcept());
		return new HashSet<String>();
	}

	public Collection<String> getPanelSpacersAttributes() {
		if (getEdOperEle() != null)
			return getEdOperEle().getPanelSpacersAttributes(
					getParentOpersConcept());
		return new HashSet<String>();
	}

	public Collection<String> getPanelVisibleAttributes() {
		if (getEdOperEle() != null)
			return getEdOperEle().getPanelVisibleAttributes(
					this.getParentOpersConcept());
		return new HashSet<String>();
	}

	public Collection<? extends String> getAllSemanticAttributesNames(
			List<InstElement> syntaxParents) {
		if (getEdOperEle() != null)
			return getEdOperEle().getAllAttributesNames(syntaxParents,
					this.getParentOpersConcept());
		return new HashSet<String>();
	}

	public List<InstAttribute> getVisibleVariables(
			List<InstElement> syntaxParents) { // TODO
		// move
		// to
		// superclass
		createInstAttributes(syntaxParents);
		if (getTransSupportMetaElement() == null)
			return null;
		Set<String> attributesNames = getTransSupportMetaElement()
				.getPropVisibleAttributesSet(syntaxParents);
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
					type = getInstAttributes().get(name).getType();
					defvalue = attribute.substring(valueEnd + 1);
				} else
					value = attribute.substring(condEnd + 1);
				InstAttribute varValue = getInstAttributes().get(variable);
				if (varValue == null || varValue.getValue() == null) {
					if (valueEnd != -1)
						getInstAttributes().get(name).setValue(
								createValue(type, defvalue));
					continue;
				} else if (varValue.getValue().toString().trim()
						.equals(value.toString())) {
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
}