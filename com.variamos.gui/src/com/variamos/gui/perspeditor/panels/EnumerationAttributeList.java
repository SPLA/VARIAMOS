package com.variamos.gui.perspeditor.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.cfm.productline.type.IntegerType;
import com.variamos.dynsup.instance.EnumerationSort;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstCell;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.StringType;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.panels.PropertyParameterDialog.DialogButtonAction;

/**
 * A class to support the property list for instance enumerations on modeling.
 * Initially copied from VariabilityAttributeList on pl.editor. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-10
 * @see com.variamos.gui.pl.editor.VariabilityAttributeList
 */
@SuppressWarnings("serial")
public class EnumerationAttributeList extends JList<InstAttribute> {

	/**
	 * Reference to the editor required for Dialog
	 */
	private VariamosGraphEditor editor;
	/**
	 * Reference to the InstEnumeration required to validate Id
	 */
	private InstElement element;

	private InstCell instCell;
	/**
	 * 
	 */
	private InstAttribute spoof = new InstAttribute("New Enum Type ...",
			new ElemAttribute("New Enum Type ...", StringType.IDENTIFIER,
					AttributeType.SYNTAX, false, "New Enum Type ...", "", "",
					1, -1, "", "", -1, "", ""), "New Enum Type ...");

	public EnumerationAttributeList(VariamosGraphEditor editor) {
		this.editor = editor;
		init(null);
	}

	@SuppressWarnings("unchecked")
	public EnumerationAttributeList(final VariamosGraphEditor editor,
			final InstCell instCell) {
		this.editor = editor;
		this.instCell = instCell;
		this.element = instCell.getInstElement();
		InstAttribute o = element.getInstAttributes().get(
				SyntaxElement.VAR_METAENUMVALUE);
		if (o != null)
			init((Collection<InstAttribute>) o.getValue());
		o = element.getInstAttributes().get("other");
		if (o != null)
			init((Collection<InstAttribute>) o.getValue());
	}

	private void init(Collection<InstAttribute> varAttributes) {
		setModel(new DefaultListModel<InstAttribute>());
		final DefaultListModel<InstAttribute> model = (DefaultListModel<InstAttribute>) getModel();
		model.addElement(spoof);

		if (varAttributes != null)
			for (InstAttribute v : varAttributes)
				model.addElement(v);

		setSize(new Dimension(150, 550));
		setPreferredSize(new Dimension(100, 580));
		setMaximumSize(new Dimension(100, 580));

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					int index = locationToIndex(evt.getPoint());
					InstAttribute v = null;

					if (index != 0)
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
				lbl.setText((String) ((InstAttribute) value).getValue());
				return lbl;
			}
		});
	}

	protected void editItem(InstAttribute instAttribute) {
		final boolean insert = (instAttribute == null);

		final InstAttribute instName = new InstAttribute("enumName",
				new ElemAttribute("EnumNameValue", StringType.IDENTIFIER,
						AttributeType.SYNTAX, false, "Value Name", "", "", 1,
						-1, "", "", -1, "", ""), "");

		final InstAttribute instIdentifier = new InstAttribute("enumId",
				new ElemAttribute("EnumIdValue", IntegerType.IDENTIFIER,
						AttributeType.SYNTAX, false, "Value Id(int)", "", "",
						1, -1, "", "", -1, "", ""), 0);
		if (insert) {
			// TODO move validation to a method on InstEnumeration
			@SuppressWarnings("unchecked")
			Collection<InstAttribute> instAttributes = null;
			if (element.getInstAttributes()
					.get(SyntaxElement.VAR_METAENUMVALUE) != null)
				instAttributes = (Collection<InstAttribute>) element
						.getInstAttributes()
						.get(SyntaxElement.VAR_METAENUMVALUE).getValue();
			if (element.getInstAttributes().get("other") != null)
				instAttributes = (Collection<InstAttribute>) element
						.getInstAttributes().get("other").getValue();
			int i = 1;
			/*
			 * while (notFound) { for (InstAttribute i : instAttributes) {
			 * if(i.getIdentifier().equals("enum"+i)) break; }
			 * //instAttributes.contains(new InstAttribute("enum"+i++))); ) }
			 */
			while (instAttributes.contains(new InstAttribute("enum" + i))) {
				i++;// TODO fix ids? verify they are different
			}

			// Name
			instAttribute = new InstAttribute("enum" + i, new ElemAttribute(
					"EnumValue", StringType.IDENTIFIER, AttributeType.SYNTAX,
					false, "Enumeration Value", "", "", 1, -1, "", "", -1, "",
					""), "");
		} else {
			String split[] = ((String) instAttribute.getValue()).split("#");
			instIdentifier.setValue(split[0]);
			instName.setValue(split[1]);
		}
		final InstAttribute finalInstAttribute = instAttribute;

		// HACK for accesing a non-final variable inside of an inner class
		final InstAttribute[] buffer = { finalInstAttribute };

		// SetDomain metaDomain = new SetDomain();
		// metaDomain.setIdentifier("MetaDomain");

		// DomainRegister reg = editor.getDomainRegister();
		// for( Type d : reg.getRegisteredDomains() )
		// metaDomain.getElements().add(d.getIdentifier());
		// reg.registerDomain(metaDomain);

		// String domainRepresentation = "0, 1";
		// if(!insert)
		// = var.getDomain().getStringRepresentation();

		final PropertyParameterDialog dialog = new PropertyParameterDialog(130,
				300, editor, element, instIdentifier, instName);
		dialog.setOnAccept(new DialogButtonAction() {
			@SuppressWarnings("unchecked")
			@Override
			public boolean onAction() {
				// This calls Pull on each parameter
				try {
					dialog.getParameters();
				} catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(dialog,
							"Value is not a valid number",
							"Number Format Error", JOptionPane.ERROR_MESSAGE,
							null);
					return false;
				}
				if (((Integer) instIdentifier.getValue()).intValue() < 0) {
					JOptionPane.showMessageDialog(dialog,
							"Value is not positive number",
							"Negative Number Error", JOptionPane.ERROR_MESSAGE,
							null);
					return false;
				}
				InstAttribute v = buffer[0];
				v.setValue(((Integer) instIdentifier.getValue()).intValue()
						+ "#" + (String) instName.getValue());

				List<InstAttribute> attributes = null;
				if (element.getInstAttributes().get(
						SyntaxElement.VAR_METAENUMVALUE) != null)
					attributes = ((List<InstAttribute>) element
							.getInstAttributes()
							.get(SyntaxElement.VAR_METAENUMVALUE).getValue());
				if (element.getInstAttributes().get("other") != null)
					attributes = ((List<InstAttribute>) element
							.getInstAttributes().get("other").getValue());
				if (insert) {
					((DefaultListModel<InstAttribute>) getModel())
							.insertElementAt(v, getModel().getSize() - 1);
					attributes.add(v);
				}
				Collections.sort(attributes, new EnumerationSort());

				afterAction();
				return true;
			}
		});

		dialog.setOnDelete(new DialogButtonAction() {
			@SuppressWarnings("unchecked")
			@Override
			public boolean onAction() {
				// This calls Pull on each parameter
				dialog.getParameters();
				InstAttribute v = buffer[0];
				List<InstAttribute> attributes = ((List<InstAttribute>) element
						.getInstAttributes()
						.get(SyntaxElement.VAR_METAENUMVALUE).getValue());

				attributes.remove(v);

				((DefaultListModel<InstAttribute>) getModel()).removeElement(v);

				afterAction();
				return true;
			}
		});

		dialog.setOnCancel(new DialogButtonAction() {

			@Override
			public boolean onAction() {
				afterAction();
				return true;
			}
		});
		dialog.center();
	}

	protected void afterAction() {

		final InstCell finalInstCell = instCell;
		new Thread() {
			@Override
			public void run() {
				try {
					sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				editor.editPropertiesRefas(finalInstCell);
			}
		}.start();

		updateUI();
	}

}
