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

import com.variamos.common.core.utilities.StringUtils;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.model.ModelExpr;
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.OpersExpr;
import com.variamos.dynsup.model.OpersSubOperationExpType;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.hlcl.model.LabelingOrderEnum;

/**
 * A class to represented modeling elements from meta model and semantic model
 * on VariaMos. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-21 *
 * @see com.variamos.syntaxsupport.metamodel.InsVertex
 * @see com.variamos.syntaxsupport.metamodel.InsEdge
 */
public abstract class InstElement implements Serializable, Cloneable,
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

	private List<ModelExpr> modExp;
	private OpersElement edOperEle;
	private SyntaxElement edSyntaxEle;
	private InstElement supInstElement;

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

	private String supInstEleId; // InstIdentifier

	private Map<String, String> volatileDefects;

	@Override
	public InstElement clone() throws CloneNotSupportedException {

		InstElement out = (InstElement) super.clone();
		out.dynamicAttributes = new HashMap<>();
		for (String key : dynamicAttributes.keySet()) {
			Object att = dynamicAttributes.get(key);
			Object cl = att;
			if (att instanceof String)
				cl = ((String) att) + "";
			// FIXME consider other types of attributes
			if (att instanceof HashMap) {
				cl = new HashMap();
				for (Object subkey : ((HashMap) att).keySet()) {
					Object subatt = ((HashMap) att).get(subkey);
					Object subcl = subatt;
					if (subatt instanceof String)
						subcl = ((String) subatt) + "";
					// FIXME consider not strings but InstAttributes, clone them
					// depending on their type
					((HashMap) cl).put(subkey, subcl);
				}
			}
			out.dynamicAttributes.put(key, cl);
		}
		out.volatileSourceRelations = new ArrayList<InstElement>();
		out.volatileTargetRelations = new ArrayList<InstElement>();
		return out;
	}

	public InstElement(String identifier) {
		this(identifier, new HashMap<String, InstAttribute>());
	}

	public InstElement(String identifier,
			Map<String, InstAttribute> instAttributes) {
		volatileSourceRelations = new ArrayList<InstElement>();
		volatileTargetRelations = new ArrayList<InstElement>();
		volatileDefects = new TreeMap<String, String>();
		dynamicAttributes.put(VAR_AUTOIDENTIFIER, identifier);
		dynamicAttributes.put(SyntaxElement.VAR_USERIDENTIFIER, identifier);
		Map<String, Object> dynamicAttributesMap = this.getDynamicAttributes();
		dynamicAttributesMap.put(VAR_INSTATTRIBUTES, instAttributes);
	}

	public List<ModelExpr> getModExp() {
		return modExp;
	}

	public void setModExp(List<ModelExpr> instanceExpressions) {
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
						SyntaxElement.VAR_AUTOIDENTIFIER))
					instAttribute.setValue(edSyntaxEle.getAutoIdentifier());
				if (instAttribute.getIdentifier().equals(
						SyntaxElement.VAR_USERIDENTIFIER))
					instAttribute.setValue(edSyntaxEle.getUserIdentifier());
				if (instAttribute.getIdentifier().equals("OperationsMMType")
						&& edSyntaxEle.getTransInstSemanticElement() != null)
					instAttribute.setValue(edSyntaxEle
							.getTransInstSemanticElement().getIdentifier());
				if (instAttribute.getIdentifier().equals("Visible"))
					instAttribute.setValue(edSyntaxEle.getVisible());
				if (instAttribute.getIdentifier().equals("Editable"))
					instAttribute.setValue(edSyntaxEle.isEditable());
				if (instAttribute.getIdentifier().equals("Name"))
					instAttribute.setValue(edSyntaxEle.getName());
				if (instAttribute.getIdentifier().equals("Style"))
					instAttribute.setValue(edSyntaxEle.getStyle());
				if (instAttribute.getIdentifier().equals("description"))
					instAttribute.setValue(edSyntaxEle.getDescription());
				if (instAttribute.getIdentifier().equals("Width"))
					instAttribute.setValue(edSyntaxEle.getWidth());
				if (instAttribute.getIdentifier().equals("Height"))
					instAttribute.setValue(edSyntaxEle.getHeight());
				if (instAttribute.getIdentifier().equals("Image"))
					instAttribute.setValue(edSyntaxEle.getImage());

				if (edSyntaxEle instanceof SyntaxElement) {
					if (instAttribute.getIdentifier().equals("TopConcept"))
						instAttribute.setValue(edSyntaxEle.isTopConcept());
					if (instAttribute.getIdentifier().equals("BackgroundColor"))
						instAttribute
								.setValue(edSyntaxEle.getBackgroundColor());
					if (instAttribute.getIdentifier().equals("Resizable"))
						instAttribute.setValue(edSyntaxEle.isResizable());
				}
				if (instAttribute.getIdentifier().equals("index"))
					instAttribute.setValue(edSyntaxEle.getIndex());
				if (instAttribute.getIdentifier().equals("PaletteNames"))
					instAttribute.setValue(edSyntaxEle.getPaletteName());

				if (instAttribute.getIdentifier().equals("Palette")) {
					instAttribute.setValue(edSyntaxEle.getPalette());
				}

				if (instAttribute.getIdentifier().equals("BorderStroke"))
					instAttribute.setValue(edSyntaxEle.getBorderStroke());

				if (instAttribute.getIdentifier().equals("value"))
					// TODO review what to associate
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

	public OpersElement getEdOperEle() {
		return edOperEle;
	}

	public void setEdOperEle(OpersElement editableSemanticElement) {
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

	public String getSupInstEleId() {
		if (supInstEleId != null)
			return supInstEleId;
		else
			// Support old models
			return supSyntaxEleId;
	}

	public void setSupInstEleId(String supInstEleId) {
		this.supInstEleId = supInstEleId;
	}

	/**
	 * Name changed from standard to avoid graph serialization of the object
	 * 
	 * @return
	 */
	public SyntaxElement getTransSupportMetaElement() {
		if (supInstElement != null)
			return supInstElement.getEdSyntaxEle();
		else
			return null;// supMetaElement;
	}

	/**
	 * Name changed from standard to avoid graph serialization of the object
	 * 
	 * @return
	 */

	@Deprecated
	public void setTransSupportMetaElement(SyntaxElement supportMetaElement) {
		this.setSupSyntaxEleId(supportMetaElement.getAutoIdentifier());
		this.supInstElement = new InstConcept("", null, supportMetaElement);
	}

	/**
	 * Name changed from standard to avoid graph serialization of the object
	 * 
	 * @return
	 */
	public InstElement getTransSupInstElement() {
		return supInstElement;
	}

	/**
	 * Name changed from standard to avoid graph serialization of the object
	 * 
	 * @return
	 */

	public void setTransSupInstElement(InstElement supInstElement) {
		this.setSupInstEleId(supInstElement.getIdentifier());
		this.supInstElement = supInstElement;
	}

	public void setIdentifier(String identifier) {
		if (getDynamicAttribute(SyntaxElement.VAR_USERIDENTIFIER).equals("")) {
			setDynamicVariable(SyntaxElement.VAR_USERIDENTIFIER, identifier);
			setInstAttribute(SyntaxElement.VAR_USERIDENTIFIER, identifier);
		}
		setDynamicVariable(VAR_AUTOIDENTIFIER, identifier);
		setInstAttribute(VAR_AUTOIDENTIFIER, identifier);
		if (getEdSyntaxEle() != null)
			getEdSyntaxEle().setAutoIdentifier(identifier);
		if (supInstElement != null)
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
	public Map<String, InstAttribute> getInstAttributes() {
		return (Map<String, InstAttribute>) getDynamicAttribute(VAR_INSTATTRIBUTES);
	}

	@SuppressWarnings("unchecked")
	public Collection<InstAttribute> getInstAttributesCollection() {
		return ((Map<String, InstAttribute>) getDynamicAttribute(VAR_INSTATTRIBUTES))
				.values();
	}

	public String getInstAttributeFullIdentifier(String insAttributeLocalId,
			int instance) {
		if (this.getInstAttribute(insAttributeLocalId) == null) {
			this.createInstAttributes(null);
			if (this.getInstAttribute(insAttributeLocalId) == null) {
				System.out.println("InstV null:" + this.getIdentifier()
						+ insAttributeLocalId);
				return null;
			}
		}
		String addAtt = "";
		if (instance != -1)
			addAtt = "_" + instance;
		return this.getIdentifier() + addAtt + "_"
				+ this.getInstAttribute(insAttributeLocalId).getIdentifier();
	}

	public String getIdentifier() {
		return (String) getDynamicAttribute(VAR_AUTOIDENTIFIER);
		// return identifier;
	}

	public String getUserIdentifier() {
		return (String) getDynamicAttribute(SyntaxElement.VAR_USERIDENTIFIER);
		// return identifier;
	}

	@Override
	public String toString() {
		return getText(null);
	}

	@SuppressWarnings("unchecked")
	public String getText(List<InstElement> syntaxParents) {
		String out = "";
		String out2 = "";
		// For Syntax MM
		if (getEdSyntaxEle() != null) {
			out2 = "\n";
			// Set<String> modelingAttributes = this.getEditableMetaElement()
			// .getAllAttributesNames(parents);
			Set<String> modelingAttributes = getEdSyntaxEle()
					.getDeclaredModelingAttributesNames();
			for (String attributeName : modelingAttributes) {
				if (!attributeName.equals(SyntaxElement.VAR_USERIDENTIFIER)
						&& (!attributeName.equals("identifier"))
						&& !attributeName.equals("userId")
						&& !attributeName.equals("value")
						&& !attributeName.equals("dummy")
						&& !attributeName.equals("description")) {
					ElemAttribute i = getEdSyntaxEle().getModelingAttribute(
							attributeName, syntaxParents);
					if (i == null) {
						i = getEdSyntaxEle()
								.getSemanticAttribute(attributeName);
					}
					String v = "";
					if (i != null) {
						// FIXME V1.1 copy change to new version
						if (!i.getType().equals("Class")
								&& !i.getType().equals(""))
							v = ":" + i.getType();
						if ((i.getType().equals("Enumeration")
								|| i.getType().equals("MetaEnumeration")
								|| i.getType().equals("MetaElement")
								|| i.getType().equals("Element")
								|| i.getType().equals("InstanceSet") || i
								.getType().equals("SeMetaModel"))
								&& i.getClassCanonicalName() != null) {
							String classN = i.getClassCanonicalName()
									.substring(
											i.getClassCanonicalName()
													.lastIndexOf(".") + 1,
											i.getClassCanonicalName().length());
							v += "<" + classN + ">";
						}
						if (i.getType().equals("Set")) {
							String classN = "";
							if (i.getClassCanonicalName() != null) {
								classN = i.getClassCanonicalName().substring(
										i.getClassCanonicalName().lastIndexOf(
												".") + 1,
										i.getClassCanonicalName().length());
								v += "<" + classN + ">";
							}

						}
						// FIXME V1.1 copy change to new version
						if (i.getType().equals("Class")) {
							String classN = "";
							if (i.getClassCanonicalName() != null) {
								classN = i.getClassCanonicalName().substring(
										i.getClassCanonicalName().lastIndexOf(
												".") + 1,
										i.getClassCanonicalName().length());
								v += ":" + classN + "<"
										+ i.getMetaConceptInstanceType() + ">";
							}

						}
					}

					// System.out.println(attributeName);
					if (attributeName.length() > 1)
						out2 += attributeName.substring(0, 1).toLowerCase()
								+ attributeName.substring(1) + v + "\n";
					else
						out2 += attributeName + v + "\n";
					// out2 += attributeName + v + "\n";
				}
			}
		}
		// For Opers MM
		if (getEdOperEle() != null) {
			out2 = "\n";
			Set<String> modelingAttributes = getEdOperEle()
					.getDeclaredSemanticAttributesNames();
			for (String attributeName : modelingAttributes) {
				if (!attributeName.equals(SyntaxElement.VAR_USERIDENTIFIER)
						&& !attributeName.equals("identifier")
						&& !attributeName.equals("TrueVal")
						&& !attributeName.equals("FalseVal")
						&& !attributeName.equals("userId")
						&& !attributeName.equals("Active")
						&& !attributeName.equals("TestConfSel")
						&& !attributeName.equals("exportOnConfig")
						&& !attributeName.equals("isContext")
						&& !attributeName.equals("ExportOnConfig")
						&& !attributeName.equals("TestConfNotSel")
						&& !attributeName.equals("description")) {
					ElemAttribute i = getEdOperEle().getSemanticAttribute(
							attributeName, syntaxParents);
					if (attributeName.length() > 1) {
						out2 += attributeName.substring(0, 1).toLowerCase()
								+ attributeName.substring(1);
						if (!i.getType().equals(""))
							out2 += ":";
					} else
						out2 += attributeName + "\n";
					if (i.getType().equals("Element")
							|| i.getType().equals("MetaEnumeration")
							|| i.getType().equals("Instance")) {
						String classN = i.getClassCanonicalName().substring(
								i.getClassCanonicalName().lastIndexOf(".") + 1,
								i.getClassCanonicalName().length());
						out2 += i.getType() + "<" + classN + ">\n";
					} else if (i.getType().equals("Class")) {
						String classN = "";
						if (i.getClassCanonicalName() != null) {
							classN = i.getClassCanonicalName()
									.substring(
											i.getClassCanonicalName()
													.lastIndexOf(".") + 1,
											i.getClassCanonicalName().length());
							out2 += ":" + classN + "<"
									+ i.getMetaConceptInstanceType() + ">\n";
						}

					} else if (attributeName.length() > 1)
						out2 += i.getType() + "\n";
					else
						out2 += i.getType() + "\n";
				}
			}
		}
		// For all
		// TODO: Remove when Claim is no longer a OverTwoRelation
		if (this.getInstAttribute("Name") != null
				&& this.getInstAttribute("Name").getValue().equals("Claim"))
			out = "<<MetaConcept>>\nClaim\n\n";
		else if (getTransSupportMetaElement() != null) {
			// out = oldPanelVisible(syntaxParents);
			out += panelVisible(syntaxParents, getTransSupportMetaElement());
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
		}// else {
			// getInstAttribute(name).setValue(value);
			// System.out.println("The attribute " + name
			// + " already exists, value assigned");
			// try {
			// throw (new Exception());
			// } catch (Exception e) {
			// e.printStackTrace();
		// }
		// }
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
				else if (name.equals("relTypesAttr")
						|| name.equals("opersExprs")) {
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name, syntaxParents),
							new ArrayList<OpersExpr>());
				} else if (name.equals("exptype")) {
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name, syntaxParents),
							new ArrayList<OpersSubOperationExpType>());
				} else if (name.equals("sortorder")) {

					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name, syntaxParents),
							new ArrayList<LabelingOrderEnum>());
				} else
					addInstAttribute(name, getTransSupportMetaElement()
							.getModelingAttribute(name, syntaxParents), null);
			}

			if (getTransSupportMetaElement() instanceof SyntaxElement
					&& getTransSupportMetaElement()
							.getTransInstSemanticElement() != null) {
				InstElement instElement = getTransSupportMetaElement()
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
					else if (name.equals("relTypesAttr")
							|| name.equals("opersExprs")) {
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
								new ArrayList<LabelingOrderEnum>());
					} else
						addInstAttribute(name,
								instElement.getSemanticAttribute(name), null);
				}
			}
		}
	}

	public List<InstAttribute> getEditableAttributes(
			Collection<InstAttribute> visibleAttributes) {
		return getFiltEditableAttributes(visibleAttributes);
	}

	public List<InstAttribute> getFiltEditableAttributes(
			Collection<InstAttribute> instAttributes) {

		List<InstAttribute> listEditableAttribs = new ArrayList<InstAttribute>();
		for (InstAttribute instAttribute : instAttributes) {
			if (instAttribute.getAttribute() == null)
				continue;
			String attri = instAttribute.getAttribute()
					.getPropTabEditionCondition();
			if (attri.equals("false"))
				continue;

			String[] split = attri.split("\\$");
			for (String attribute : split) {
				int varEnd = attribute.indexOf("#", 0);
				int condEnd = attribute.indexOf("#", varEnd + 1);
				int valueEnd = attribute.indexOf("#", condEnd + 1);
				if (varEnd != -1) {
					String name = instAttribute.getName();
					String type = null;
					String variable = null;
					String condition = null;
					String value = null;
					String defvalue = null;
					variable = attribute.substring(0, varEnd);
					// System.out.println("ATTPRT: " + attribute);
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
						// FIXME use transformation of names _ for spaces
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
					listEditableAttribs.add(instAttribute);

				} else
					listEditableAttribs.add(instAttribute);
			}
		}
		return listEditableAttribs;
	}

	public List<InstAttribute> getVisibleAttributes(
			List<InstElement> syntaxParents) {

		createInstAttributes(syntaxParents);
		SyntaxElement supportElement = getTransSupportMetaElement();

		List<InstAttribute> visibleAttributes = new ArrayList<InstAttribute>();

		if (supportElement != null) {
			Set<String> attributes = supportElement
					.getAllAttributesNames(syntaxParents);
			List<String> visibleAttributesNames = new ArrayList<String>();

			for (String attributeName : attributes) {
				ElemAttribute attribute = supportElement.getAbstractAttribute(
						attributeName, syntaxParents, null);

				if (attribute.getPropTabPosition() != -1)
					visibleAttributesNames.add(attribute
							.getPropTabPositionStr() + attribute.getName());
			}
			Collections.sort(visibleAttributesNames);
			for (String attributeName : visibleAttributesNames) {
				InstAttribute attribute = getInstAttribute(attributeName
						.substring(2));

				if (attribute != null)
					visibleAttributes.add(attribute);
			}
		}
		List<InstAttribute> out = getFiltVisibleAttributes(visibleAttributes);
		return out;
	}

	public List<InstAttribute> getFiltVisibleAttributes(
			List<InstAttribute> instAttributes) {

		List<InstAttribute> listEditableAttribs = new ArrayList<InstAttribute>();
		for (InstAttribute instAttribute : instAttributes) {
			String attri = instAttribute.getAttribute()
					.getPropTabVisualCondition();
			String[] split = attri.split("\\$");
			for (String attribute : split) {
				int varEnd = attribute.indexOf("#", 0);
				int condEnd = attribute.indexOf("#", varEnd + 1);
				int valueEnd = attribute.indexOf("#", condEnd + 1);
				if (varEnd != -1) {
					String name = instAttribute.getName();
					String type = null;
					String variable = null;
					String condition = null;
					String value = null;
					String defvalue = null;
					variable = attribute.substring(0, varEnd);
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
						// FIXME use transformation of names _ for spaces
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
					listEditableAttribs.add(instAttribute);

				} else
					listEditableAttribs.add(instAttribute);
			}
		}
		return listEditableAttribs;
	}

	protected String panelVisible(List<InstElement> syntaxParents,
			SyntaxElement supportElement) {
		String out = "";
		if (supportElement == null)
			return out;
		Set<String> attributes = supportElement
				.getAllAttributesNames(syntaxParents);
		List<String> visibleAttributesNames = new ArrayList<String>();

		// FIXME use a partial sort instead of this additional structure and
		// repetitive
		for (String attributeName : attributes) {
			ElemAttribute attribute = supportElement.getAbstractAttribute(
					attributeName, syntaxParents, null);

			if (attribute.getElementDisplayPosition() != -1)
				visibleAttributesNames.add(attribute
						.getElementDisplayPositionStr() + attribute.getName());
		}
		Collections.sort(visibleAttributesNames);
		for (String attributeName : visibleAttributesNames) {
			ElemAttribute attribute = supportElement.getAbstractAttribute(
					attributeName.substring(2), syntaxParents, null);
			if (attribute == null) {
				System.out.println("NULL Attr:" + attributeName);
				continue;
			}
			String visibleAttributes = attribute.getElementDisplayCondition();

			String name = null;
			boolean validCondition = true;
			String[] split = visibleAttributes.split("\\$");
			for (String visibleAttribute : split) {
				int varEnd = visibleAttribute.indexOf("#");
				int condEnd = visibleAttribute.indexOf("#", varEnd + 1);
				int valueEnd = visibleAttribute.indexOf("#", condEnd + 1);
				if (valueEnd == -1)
					valueEnd = visibleAttribute.length();
				String variable = null;
				String condition = null;
				String value = null;
				name = attributeName.substring(2);
				if (varEnd != -1 && condEnd != -1) {

					variable = visibleAttribute.substring(0, varEnd);
					condition = visibleAttribute.substring(varEnd + 1, condEnd);
					if (condEnd < valueEnd)
						value = visibleAttribute.substring(condEnd + 1,
								valueEnd);
					else
						value = "";
					validCondition = false;
					InstAttribute varValue = getInstAttributes().get(variable);
					if (varValue == null || varValue.getValue() == null)
						continue;
					else if (varValue.getValue().toString().trim()
							.equals(value)) {
						if (condition.equals("!="))
							continue;
					} else {
						if (condition.equals("=="))
							continue;
					}
					validCondition = true;
					break;
				}
			}
			boolean nvar = false;
			if (validCondition) {
				String spacer = attribute.getElementDisplaySpacers();
				if (spacer.indexOf("#" + name + "#") != -1) {
					nvar = true;
					int sp1 = spacer.indexOf("#");
					int sp2 = spacer.indexOf("#", sp1 + 1);
					int sp3 = spacer.indexOf("#", sp2 + 1);
					String space = spacer.substring(0, sp1).replace("/n", "\n");
					if (name.equals("name")
							&& getInstAttributes().get(name).toString().trim()
									.equals(""))
						out += space + "<<NoName>>";
					else if (name.equals("relationType")
							&& getInstAttributes().get(name) != null
							&& getInstAttributes().get(name).getValueObject() != null
							&& getInstAttributes().get(name).getValueObject() instanceof InstAttribute) {
						InstAttribute att = (InstAttribute) getInstAttributes()
								.get(name).getValueObject();
						String[] atts = ((String) att
								.getInstAttributeAttribute("Value")).split("#");
						out += space + atts[1];
					} else {
						InstAttribute instAttribute = getInstAttributes().get(
								name);
						// System.out.println(this.getIdentifier());
						if (instAttribute != null) {
							if (instAttribute.getType() != null
									&& instAttribute.getType().equals("Set")) {
								out += space;
								String attribVal = "all";
								if (sp3 != -1 && sp3 > sp2)
									attribVal = spacer.substring(sp2 + 1, sp3);
								try {
									for (InstAttribute e : (Collection<InstAttribute>) instAttribute
											.getValue())
										if (attribVal.equals("all"))
											out += e.toString().trim() + "\n";
										else {
											String val = (String) e
													.getInstAttributeAttribute("Value");
											int i = 1;
											int index = val.indexOf("#");
											int pos = Integer
													.parseInt(attribVal);
											String sValue = val.substring(0,
													index);
											while (i < pos) {
												int newIndex = val.indexOf("#",
														index + 1);
												if (newIndex != -1)
													sValue = val
															.substring(
																	index + 1,
																	newIndex);
												i++;
											}
											out = sValue.toString().trim()
													+ "\n";
										}
								} catch (Exception e) {

								}

								// out = out.substring(0, out.length() - 2);
							}

							else {
								// String outt =
								// instAttribute.toString().trim();
								// if (outt.length() > 1)
								// out += outt.substring(0, 1).toLowerCase()
								// + outt.substring(1);
								// else
								if (instAttribute.toString().trim().length() > 0)
									out += space
											+ instAttribute.toString().trim();
								else
									out += space;
							}
						}
					}
					while (sp3 != spacer.length()) {
						int sp4 = spacer.indexOf("#", sp3 + 1);
						if (sp4 == -1) {
							out += spacer.substring(sp3 + 1)
									.replace("/n", "\n");
							break;
						}
						out += spacer.substring(sp3 + 1, sp4).replace("/n",
								"\n");

						sp3 = sp4;
					}
				}

				if (!nvar)
					if (name.equals("name")
							&& getInstAttributes().get(name) != null
							&& getInstAttributes().get(name).toString().trim()
									.equals(""))
						out += "<<NoName>>";
					else {
						InstAttribute instAttribute = getInstAttributes().get(
								name);
						// System.out.println(instAttribute.getEnumType());
						if (instAttribute.getEnumType() != null
								&& instAttribute.getEnumType().equals(
										InstConcept.class.getCanonicalName())) {
							out += (String) instAttribute.getValue();
							if (instAttribute.getValueObject() != null) {
								Map<String, InstAttribute> o = (Map<String, InstAttribute>) ((InstElement) instAttribute
										.getValueObject())
										.getDynamicAttribute(VAR_INSTATTRIBUTES);
								InstAttribute oo = o
										.get(SyntaxElement.VAR_METAENUMVALUE);
								Collection<InstAttribute> ooo = (Collection<InstAttribute>) oo
										.getInstAttributeAttribute("Value");
								String outt = "{ ";
								{
									for (InstAttribute i : ooo) {
										String values[] = ((String) i
												.getValue()).split("#");
										outt += values[1] + ", ";
									}
									// out = out.substring(0, out.length() - 2);
								}

								outt = outt.substring(0, outt.length() - 2);
								outt += " }";
								out += StringUtils.multiLine(outt, 40);
							}
						} else
							out += instAttribute.toString().trim();
					}
			}
		}

		return out;
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
		String index = this.getInstAttribute("index").getValue()
				+ this.getIdentifier();
		String other = view.getInstAttribute("index").getValue()
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

	// FIXME include a check for circular extends relations, requires a
	// parameter
	// with the related concept to compare to
	public List<InstElement> getParentOpersConcept() {
		List<InstElement> out = new ArrayList<InstElement>();
		List<InstElement> rel = getTargetRelations();
		for (InstElement element : rel) {
			// FIXME v1.1 include additional validation
			if (element instanceof InstPairwiseRel
					&& ((InstPairwiseRel) element)
							.getSupportMetaPairwiseRelIden() != null
					&& ((InstPairwiseRel) element)
							.getSupportMetaPairwiseRelIden().equals(
									"ExtendsRelation")) {
				InstElement parent = element.getTargetRelations().get(0);
				// parent.createInstAttributes(parents);
				out.add(parent);
				if (element.getTargetRelations().get(0) != null) {
					List<InstElement> outt = element.getTargetRelations()
							.get(0).getParentOpersConcept();
					if (outt != null)
						out.addAll(outt);

				}
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

	@Deprecated
	public Collection<String> getPanelSpacersAttributes() {
		if (getEdOperEle() != null)
			return getEdOperEle().getPanelSpacersAttributes(
					getParentOpersConcept());
		return new HashSet<String>();
	}

	@Deprecated
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

	public List<InstAttribute> getFilteredInstAttributes(
			Set<String> attributesNames, List<InstAttribute> instAttributes) {
		List<String> listEditableAttributes = new ArrayList<String>();
		listEditableAttributes.addAll(attributesNames);
		Collections.sort(listEditableAttributes);

		List<String> listEditableAttribNames = new ArrayList<String>();
		for (String attri : listEditableAttributes) {
			int nameEnd = attri.indexOf("#", 3);
			String name = null;
			if (nameEnd != -1) {
				name = attri.substring(3, nameEnd);

				attri = attri.substring(nameEnd + 1);
				String[] split = attri.split("\\$");
				for (String attribute : split) {
					int varEnd = attribute.indexOf("#");
					int condEnd = attribute.indexOf("#", varEnd + 1);
					int valueEnd = attribute.indexOf("#", condEnd + 1);
					String type = null;
					String variable = null;
					String condition = null;
					String value = null;
					String defvalue = null;
					variable = attribute.substring(0, varEnd);
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
						// FIXME use transformation of names _ for spaces
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
					listEditableAttribNames.add(name);
				}
			} else
				listEditableAttribNames.add(attri.substring(3));
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
		if (type.equals("Float"))
			return new Float(value);
		return value;

	}

	// Multi-instances only if the concept has scope or both elements in the
	// relation has the same scope
	// TODO support aggregation of multiples scopes - this support a global and
	// another scope only
	public int getInstances(InstanceModel refas) {
		int out = 1;
		if (getInstAttribute("Scope") != null) {
			boolean scope = (boolean) getInstAttributeValue("Scope");
			if (!scope) {
				if (getInstAttribute("ConcernLevel") != null) {
					String concernLevel = (String) getInstAttributeValue("ConcernLevel");
					InstElement concern = refas.getVertex(concernLevel);
					// FIXME fix the value to remove the validation
					if (concern != null)
						if (concern.getInstAttributeValue("instances") instanceof String)
							out = Integer.parseInt(((String) concern
									.getInstAttributeValue("instances")));
						else
							out = (int) concern
									.getInstAttributeValue("instances");

				}
			}
		}
		if (getTransSupportMetaElement() != null
				&& getTransSupportMetaElement().getTransInstSemanticElement() != null) {
			InstAttribute ia = getTransSupportMetaElement()
					.getTransInstSemanticElement().getInstAttribute(
							"opersExprs");
			if (ia != null && getSourceRelations().size() > 0
					&& getTargetRelations().size() > 0) {
				InstElement source = getSourceRelations().get(0);
				InstElement target = getTargetRelations().get(0);
				if (source.getInstAttribute("Scope") != null
						&& target.getInstAttribute("Scope") != null) {
					boolean scope = (boolean) source
							.getInstAttributeValue("Scope")
							|| (boolean) target.getInstAttributeValue("Scope");
					if (!scope) {
						if (source.getInstAttribute("ConcernLevel") != null
								&& target.getInstAttribute("ConcernLevel") != null) {
							String sourceConcernLevel = (String) source
									.getInstAttributeValue("ConcernLevel");
							String targetConcernLevel = (String) source
									.getInstAttributeValue("ConcernLevel");
							if (sourceConcernLevel.equals(targetConcernLevel)) {
								InstElement concern = refas
										.getVertex(sourceConcernLevel);
								out = (int) concern
										.getInstAttributeValue("instances");
							}
						}
					}
				}
			}
		}

		return out;
	}
}
