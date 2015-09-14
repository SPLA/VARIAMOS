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

import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.panels.AttributeEditionPanel.DialogButtonAction;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.syntaxsupport.EditableElementAttribute;
import com.variamos.perspsupport.types.StringType;

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
public class PropertyAttributeList extends JList<AbstractAttribute> {

	/**
	 * Reference to the editor required for Dialog
	 */
	private VariamosGraphEditor editor;
	/**
	 * Reference to the InstEnumeration required to validate Id
	 */
	private Map<String, AbstractAttribute> attributes;
	/**
	 * 
	 */
	private AbstractAttribute spoof = new AbstractAttribute("Add ...",
			StringType.IDENTIFIER, false, "Add ...", "", 1, -1, "", "", -1, "",
			"");

	private AttributeEditionPanel attributeEdition;

	public PropertyAttributeList(VariamosGraphEditor editor) {
		this.editor = editor;
		init(null);
	}

	public PropertyAttributeList(VariamosGraphEditor editor,
			Map<String, AbstractAttribute> attributes,
			AttributeEditionPanel attributeEdition) {
		this.editor = editor;
		this.attributes = attributes;
		this.attributeEdition = attributeEdition;
		init(attributes);

	}

	private void init(Map<String, AbstractAttribute> varAttributes) {
		setModel(new DefaultListModel<AbstractAttribute>());
		final DefaultListModel<AbstractAttribute> model = (DefaultListModel<AbstractAttribute>) getModel();

		if (varAttributes != null)
			for (AbstractAttribute v : varAttributes.values())
				model.addElement(v);

		model.addElement(spoof);

		// setSize(new Dimension(150, 150));
		setPreferredSize(new Dimension(100, 120));
		setMaximumSize(new Dimension(100, 120));

		addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					int index = locationToIndex(evt.getPoint());
					AbstractAttribute v = null;

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
				lbl.setText((String) ((AbstractAttribute) value).getName()
						+ ":" + ((AbstractAttribute) value).getType());
				return lbl;
			}
		});
	}

	protected void editItem(AbstractAttribute var) {
		final boolean insert = (var == null);
		attributeEdition.setEnabled(true);
		this.setEnabled(false);
		if (insert) {
			// TODO move validation to a method on InstEnumeration
			// Name
			var = new AbstractAttribute("EnumValue", StringType.IDENTIFIER,
					false, "Enumeration Value", "", 1, -1, "", "", -1, "", "");

		}

		// HACK for accesing a non-final variable inside of an inner class
		final AbstractAttribute[] buffer = { var };
		Map<String, EditableElementAttribute> att = var
				.getDynamicAttributeComponentsMap();

		// TODO manage dynamically

		final EditableElementAttribute name = att.get("Name");
		final EditableElementAttribute type = att.get("Type");
		final EditableElementAttribute ClassCanName = att.get("ClassCanName");
		final EditableElementAttribute MetaCInstType = att.get("MetaCInstType");
		final EditableElementAttribute displayName = att.get("DispName");
		final EditableElementAttribute defaultValue = att.get("DefaultValue");
		final EditableElementAttribute domain = att.get("Domain");
		final EditableElementAttribute hint = att.get("Hint");

		final EditableElementAttribute propTabPosition = att
				.get("propTabPosition");
		final EditableElementAttribute propTabEditionCondition = att
				.get("propTabEditionCondition");
		final EditableElementAttribute propTabVisualCondition = att
				.get("propTabVisualCondition");
		final EditableElementAttribute elementDisplayPosition = att
				.get("elementDisplayPosition");
		final EditableElementAttribute elementDisplaySpacers = att
				.get("elementDisplaySpacers");
		final EditableElementAttribute elementDisplayCondition = att
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

		attributeEdition.loadElementAttributes(editor, name, displayName, type,
				ClassCanName, MetaCInstType, defaultValue, domain, hint,
				propTabPosition, propTabEditionCondition,
				propTabVisualCondition, elementDisplayPosition,
				elementDisplaySpacers, elementDisplayCondition);
		attributeEdition.revalidate();
		attributeEdition.repaint();
		attributeEdition.setOnAccept(new DialogButtonAction() {
			@Override
			public boolean onAction() {
				// This calls Pull on each parameter
				attributeEdition.getParameters();

				AbstractAttribute v = buffer[0];
				v.setName((String) name.getValue());
				v.setDisplayName((String) displayName.getValue());
				// v.setDomain((Domain)domain.getValue());
				v.setHint((String) hint.getValue());
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
				v.setType((String) type.getAttributeType());
				v.setClassCanonicalName((String) ClassCanName.getValue());
				v.setMetaConceptInstanceType((String) MetaCInstType.getValue());
				v.setDefaultValue(defaultValue.getValue());
				// v.setDomain((Domain) domain.getValue());
				// v.setDisplayName((String) name.getDisplayName());
				if (insert) {
					((DefaultListModel<AbstractAttribute>) getModel())
							.insertElementAt(v, getModel().getSize() - 1);
					attributes.put(name.getName(), v);
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
	}

	protected void afterAction() {
		updateUI();
	}

}
