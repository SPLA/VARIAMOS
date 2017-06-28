package com.variamos.gui.perspeditor.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.interfaces.IntInstAttribute;
import com.variamos.dynsup.model.ElemAttribAttribute;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.StringType;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.panels.AttributeEditionPanel.DialogButtonAction;
import com.variamos.hlcl.Domain;
import com.variamos.hlcl.DomainParser;

/**
 * A class to support the property of syntax and semantic concepts for modeling.
 * Initially copied from VariabilityAttributeList on pl.editor. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-28
 * @see com.variamos.gui.pl.editor.VariabilityAttributeList
 */
@SuppressWarnings("serial")
public class PropertyAttributeList extends JList<ElemAttribute> {

	/**
	 * Reference to the editor required for Dialog
	 */
	private VariamosGraphEditor editor;
	/**
	 * Reference to the InstEnumeration required to validate Id
	 */
	private Map<String, ElemAttribute> attributes;
	/**
	 * 
	 */
	// AttributeType.SYNTAX,FIXME: create Syntax or Semantic attributes and
	// allow the user to define the type
	private ElemAttribute spoof = new ElemAttribute("Add ...",
			StringType.IDENTIFIER, AttributeType.SYNTAX, false, "Add ...", "",
			"", 1, -1, "", "", -1, "", "");

	private AttributeEditionPanel attributeEdition;

	private InstElement instElement;

	private boolean editable = true;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public PropertyAttributeList(VariamosGraphEditor editor) {
		this.editor = editor;
		init(null);
	}

	public PropertyAttributeList(VariamosGraphEditor editor, boolean editable,
			InstElement instElement, Map<String, ElemAttribute> attributes,
			AttributeEditionPanel attributeEdition) {
		this.editor = editor;
		this.editable = editable;
		this.instElement = instElement;
		this.attributes = attributes;
		this.attributeEdition = attributeEdition;
		init(attributes);

	}

	private void init(Map<String, ElemAttribute> varAttributes) {
		setModel(new DefaultListModel<ElemAttribute>());
		final DefaultListModel<ElemAttribute> model = (DefaultListModel<ElemAttribute>) getModel();

		if (varAttributes != null)
			for (ElemAttribute v : varAttributes.values())
				model.addElement(v);

		model.addElement(spoof);

		// setSize(new Dimension(150, 150));
		setPreferredSize(new Dimension(150, varAttributes.size() * 18));
		setMaximumSize(new Dimension(200, varAttributes.size() * 18));
		this.setAutoscrolls(true);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					int index = locationToIndex(evt.getPoint());
					ElemAttribute v = null;

					if (index != model.getSize() - 1)
						v = getModel().getElementAt(index);

					editItem(v);
				}
			}
		});
		setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(
					@SuppressWarnings("rawtypes") JList list, Object value,
					int index, boolean isSelected, boolean cellHasFocus) {
				JLabel lbl = (JLabel) super.getListCellRendererComponent(list,
						value, index, isSelected, cellHasFocus);
				lbl.setText(((ElemAttribute) value).getName() + ":"
						+ ((ElemAttribute) value).getType());
				return lbl;
			}
		});
	}

	protected void editItem(ElemAttribute var) {
		final boolean insert = (var == null);
		attributeEdition.setEnabled(true);
		this.setEnabled(false);
		if (insert) {
			// TODO move validation to a method on InstEnumeration
			// Name
			var = new ElemAttribute("EnumValue", StringType.IDENTIFIER,
					AttributeType.SYNTAX, false, "Enumeration Value", "", "",
					1, -1, "", "", -1, "", "");

		}

		// HACK for accesing a non-final variable inside of an inner class
		final ElemAttribute[] buffer = { var };
		Map<String, IntInstAttribute> att = var
				.getDynamicAttributeComponentsMap();

		// TODO manage dynamically

		final IntInstAttribute name = att.get("Name");
		final IntInstAttribute type = att.get("Type");
		final IntInstAttribute attributeType = att.get("AttributeType");
		final IntInstAttribute ClassCanName = att.get("ClassCanName");
		final IntInstAttribute MetaCInstType = att.get("MetaCInstType");
		final IntInstAttribute displayName = att.get("DispName");
		final IntInstAttribute defaultValue = att.get("DefaultValue");
		final IntInstAttribute domain = att.get("Domain");
		final IntInstAttribute domainStr = new ElemAttribAttribute("Domain",
				"String", "Domain", "");
		if (domain.getValue() != null)
			domainStr.setValue(((Domain) domain.getValue())
					.getStringRepresentation());
		final IntInstAttribute hint = att.get("Hint");
		final IntInstAttribute toolTip = att.get("toolTipText");
		final IntInstAttribute domFiltOwn = att.get("domFiltOwnFields");
		final IntInstAttribute domFiltRel = att.get("domFilTRelFields");
		final IntInstAttribute domDefVal = att.get("defDomValueField");

		final IntInstAttribute propTabPosition = att.get("propTabPosition");
		final IntInstAttribute propTabEditionCondition = att
				.get("propTabEditionCondition");
		final IntInstAttribute propTabVisualCondition = att
				.get("propTabVisualCondition");
		final IntInstAttribute elementDisplayPosition = att
				.get("elementDisplayPosition");
		final IntInstAttribute elementDisplaySpacers = att
				.get("elementDisplaySpacers");
		final IntInstAttribute elementDisplayCondition = att
				.get("elementDisplayCondition");

		// SetDomain metaDomain = new SetDomain();
		// metaDomain.setIdentifier("MetaDomain");

		// DomainRegister reg = editor.getDomainRegister();
		// for( Type d : reg.getRegisteredDomains() )
		// metaDomain.getElements().add(d.getIdentifier());
		// reg.registerDomain(metaDomain);

		// String domainRepresentation = "0, 1";
		// if(!insert)
		// = var.getDomain().getStringRepresentation();

		attributeEdition.loadElementAttributes(editor, editable, name,
				displayName, toolTip, type, attributeType, ClassCanName,
				MetaCInstType, defaultValue, domainStr, hint, propTabPosition,
				propTabEditionCondition, propTabVisualCondition,
				elementDisplayPosition, elementDisplaySpacers,
				elementDisplayCondition, domFiltOwn, domFiltRel, domDefVal);
		attributeEdition.revalidate();
		attributeEdition.repaint();
		attributeEdition.setOnAccept(new DialogButtonAction() {
			@Override
			public boolean onAction() {
				// This calls Pull on each parameter
				attributeEdition.getParameters();

				ElemAttribute v = buffer[0];
				v.setName((String) name.getValue());
				v.setDisplayName((String) displayName.getValue());
				if (domainStr.getValue() != null
						&& !domainStr.getValue().equals(""))
					v.setDomain(DomainParser.parseDomain(
							((String) domainStr.getValue()), 0));
				else
					v.setDomain(null);
				domain.setValue(v.getDomain());
				v.setHint((String) hint.getValue());
				v.setToolTipText((String) toolTip.getValue());
				v.setDomainFiltersOwnFields((String) domFiltOwn.getValue());
				v.setDomainFiltersRelFields((String) domFiltRel.getValue());
				v.setDefaultDomainValueField((String) domDefVal.getValue());
				v.setPropTabPosition((int) propTabPosition.getValue());
				v.setPropTabEditionCondition((String) propTabEditionCondition
						.getValue());
				v.setPropTabVisualCondition((String) propTabVisualCondition
						.getValue());
				v.setElementDisplayPosition((int) elementDisplayPosition
						.getValue());
				v.setElementDisplaySpacers((String) elementDisplaySpacers
						.getValue());
				v.setElementDisplayCondition((String) elementDisplayCondition
						.getValue());
				v.setType((String) type.getValue());
				v.setAttributeType((String) attributeType.getValue());
				v.setClassCanonicalName((String) ClassCanName.getValue());
				v.setMetaConceptInstanceType((String) MetaCInstType.getValue());
				v.setDefaultValue(defaultValue.getValue());
				// v.setDomain((Domain) domain.getValue());
				// v.setDisplayName((String) name.getDisplayName());

				// FIXME if the meta-attribute exists but the name was changed
				// it is needed to delete the existing element from the
				// collection and add it again with the new identifier.
				if (insert) {
					((DefaultListModel<ElemAttribute>) getModel())
							.insertElementAt(v, getModel().getSize() - 1);
					attributes.put((String) name.getValue(), v);
					if (instElement.getEdSyntaxEle() != null) {
						SyntaxElement me = instElement.getEdSyntaxEle();
						me.addModelingAttribute((String) name.getValue(), v);
						me.addModelingAttribute(
								SyntaxElement.VAR_USERIDENTIFIER, "String",
								false, "User Identifier", "", "", 0, 4, "", "",
								99, "#" + SyntaxElement.VAR_USERIDENTIFIER
										+ "#", "");
						// me.addPanelVisibleAttribute("99#"
						// + SyntaxElement.VAR_USERIDENTIFIER);
						me.addPropVisibleAttribute("99#"
								+ (String) name.getValue());
						me.addPropEditableAttribute("99#"
								+ (String) name.getValue());
					}
					if (instElement.getEdOperEle() != null) {
						OpersElement sc = (instElement.getEdOperEle());
						sc.putSemanticAttribute((String) name.getValue(), v);
						sc.putSemanticAttribute(
								SyntaxElement.VAR_USERIDENTIFIER,
								new ElemAttribute(
										SyntaxElement.VAR_USERIDENTIFIER,
										"String", AttributeType.OPERATION,
										false, "User Identifier", "", "", 0, 4,
										"", "", 99,
										"#" + SyntaxElement.VAR_USERIDENTIFIER
												+ "#", ""));
						// sc.addPanelVisibleAttribute("99#"
						// + SyntaxElement.VAR_USERIDENTIFIER);
						sc.addPropVisibleAttribute("19#"
								+ (String) name.getValue());
						sc.addPropEditableAttribute("19#"
								+ (String) name.getValue());
					}
				}

				afterAction();
				return true;
			}
		});

		attributeEdition.setOnCancel(new DialogButtonAction() {

			@Override
			public boolean onAction() {
				afterAction();
				return true;
			}
		});

		attributeEdition.setOnDelete(new DialogButtonAction() {

			@Override
			public boolean onAction() {
				attributeEdition.getParameters();
				DefaultListModel<ElemAttribute> ldm = ((DefaultListModel<ElemAttribute>) getModel());
				int i = 0;
				for (Object ea : ldm.toArray()) {
					String nameS = (String) name.getValue();
					if (((ElemAttribute) ea).getName().equals(nameS)) {
						ldm.remove(i);
						attributes.remove(nameS);
					}
					i++;
				}
				if (instElement.getEdSyntaxEle() != null) {
					SyntaxElement me = instElement.getEdSyntaxEle();
					me.removeModelingAttribute((String) name.getValue());
				}
				if (instElement.getEdOperEle() != null) {
					OpersElement sc = (instElement.getEdOperEle());
					sc.removeSemanticAttribute((String) name.getValue());
				}

				afterAction();
				return true;
			}
		});
	}

	protected void afterAction() {
		updateUI();
	}

}
