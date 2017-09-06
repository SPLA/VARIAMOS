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
import com.variamos.dynsup.types.BooleanType;
import com.variamos.dynsup.types.StringType;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.panels.PropertyParameterDialog.DialogButtonAction;

/**
 * A class to support the property list for instance enumerations type on
 * semantic model. Initially copied from EnumerationeAttributeList. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-08-31
 * @see com.variamos.gui.perspeditor.panels.EnumerationeAttributeList
 */
@SuppressWarnings("serial")
public class EnumerationTypeAttributeList extends JList<InstAttribute> {

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
	private InstAttribute spoof = new InstAttribute("Add ...",
			new ElemAttribute("Add ...", StringType.IDENTIFIER,
					AttributeType.SYNTAX, false, "Add ...", "", "", 1, -1, "",
					"", -1, "", ""), "Add ...");

	public EnumerationTypeAttributeList(VariamosGraphEditor editor) {
		this.editor = editor;
		init(null);
	}

	@SuppressWarnings("unchecked")
	public EnumerationTypeAttributeList(final VariamosGraphEditor editor,
			final InstCell instCell) {
		this.editor = editor;
		this.instCell = instCell;
		this.element = instCell.getInstElement();
		InstAttribute o = element.getInstAttributes().get(
				SyntaxElement.VAR_METAENUMVALUE);
		if (o != null)
			init((Collection<InstAttribute>) o.getValue());
	}

	private void init(Collection<InstAttribute> varAttributes) {
		setModel(new DefaultListModel<InstAttribute>());
		final DefaultListModel<InstAttribute> model = (DefaultListModel<InstAttribute>) getModel();

		if (varAttributes != null)
			for (InstAttribute v : varAttributes)
				model.addElement(v);

		model.addElement(spoof);

		// setSize(new Dimension(150, 150));
		setPreferredSize(new Dimension(100, 180));
		setMaximumSize(new Dimension(100, 180));

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					int index = locationToIndex(evt.getPoint());
					InstAttribute v = null;

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
				lbl.setText((String) ((InstAttribute) value).getValue());
				return lbl;
			}
		});
	}

	protected void editItem(InstAttribute instAttribute) {
		final boolean insert = (instAttribute == null);

		final InstAttribute instIdentifier = new InstAttribute("enumId",
				new ElemAttribute("EnumIdValue", StringType.IDENTIFIER,
						AttributeType.SYNTAX, false, "Identifier", "", "", 1,
						-1, "", "", -1, "", ""), "");

		final InstAttribute instDisplayName = new InstAttribute(
				"enumDisplayName", new ElemAttribute("EnumDisplayNameValue",
						StringType.IDENTIFIER, AttributeType.SYNTAX, false,
						"Display Name", "", "", 1, -1, "", "", -1, "", ""), "");

		final InstAttribute instPanelName = new InstAttribute("enumPanelName",
				new ElemAttribute("EnumPanelNameValue", StringType.IDENTIFIER,
						AttributeType.SYNTAX, false, "Panel Name", "", "", 1,
						-1, "", "", -1, "", ""), "");

		final InstAttribute instRelationExclusive = new InstAttribute(
				"enumRelExclusive", new ElemAttribute("EnumRelExclusiveValue",
						BooleanType.IDENTIFIER, AttributeType.SYNTAX, false,
						"Relation Exclusive", "", false, 1, -1, "", "", -1, "",
						""), false);

		final InstAttribute instSourceExclusive = new InstAttribute(
				"enumSourceExclusive", new ElemAttribute(
						"EnumSourceExclusiveValue", BooleanType.IDENTIFIER,
						AttributeType.SYNTAX, false, "Source Exclusive", "",
						false, 1, -1, "", "", -1, "", ""), false);

		final InstAttribute instTargetExclusive = new InstAttribute(
				"enumTargetExclusive", new ElemAttribute(
						"EnumTargetExclusiveValue", BooleanType.IDENTIFIER,
						AttributeType.SYNTAX, false, "Target Exclusive", "",
						false, 1, -1, "", "", -1, "", ""), false);

		final InstAttribute instMinSourceCardinality = new InstAttribute(
				"enumMinSourceCardinality", new ElemAttribute(
						"EnumMinSourceCardinalityValue",
						IntegerType.IDENTIFIER, AttributeType.SYNTAX, false,
						"Min Source Cardinality(int)", "", "", 1, -1, "", "",
						-1, "", ""), 1);
		final InstAttribute instSourceCardinality = new InstAttribute(
				"enumMaxSourceCardinality", new ElemAttribute(
						"EnumMaxSourceCardinalityValue",
						IntegerType.IDENTIFIER, AttributeType.SYNTAX, false,
						"Max Source Cardinality", "", "", 1, -1, "", "", -1,
						"", ""), 1);
		final InstAttribute instMinTargetCardinality = new InstAttribute(
				"enumMinTargetCardinality", new ElemAttribute(
						"EnumMinTargetCardinalityValue",
						IntegerType.IDENTIFIER, AttributeType.SYNTAX, false,
						"Min Target Cardinality", "", "", 1, -1, "", "", -1,
						"", ""), 1);
		final InstAttribute instTargetCardinality = new InstAttribute(
				"enumMaxTargetCardinality", new ElemAttribute(
						"EnumMaxTargetCardinalityValue",
						IntegerType.IDENTIFIER, AttributeType.SYNTAX, false,
						"Max Target Cardinality", "", "", 1, -1, "", "", -1,
						"", ""), 1);
		if (insert) {
			// TODO move validation to a method on InstEnumeration
			@SuppressWarnings("unchecked")
			Collection<InstAttribute> instAttributes = (Collection<InstAttribute>) element
					.getInstAttributes().get(SyntaxElement.VAR_METAENUMVALUE)
					.getValue();
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
			instDisplayName.setValue(split[1]);
			instPanelName.setValue(split[2]);
			instRelationExclusive.setValue(split[3]);
			instSourceExclusive.setValue(split[4]);
			instTargetExclusive.setValue(split[5]);
			instMinSourceCardinality.setValue(split[6]);
			instSourceCardinality.setValue(split[7]);
			instMinTargetCardinality.setValue(split[8]);
			instTargetCardinality.setValue(split[9]);
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

		final PropertyParameterDialog dialog = new PropertyParameterDialog(350,
				300, "Relation Type Editor", editor, element, instIdentifier,
				instDisplayName, instPanelName, instRelationExclusive,
				instSourceExclusive, instTargetExclusive,
				instMinSourceCardinality, instSourceCardinality,
				instMinTargetCardinality, instTargetCardinality);
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
				if (((Integer) instSourceCardinality.getValue()).intValue() < -1) {
					JOptionPane
							.showMessageDialog(
									dialog,
									"Source Cardinality is not positive number (or -1)",
									"Negative Number Error",
									JOptionPane.ERROR_MESSAGE, null);
					return false;
				}
				if (((Integer) instTargetCardinality.getValue()).intValue() < -1) {
					JOptionPane
							.showMessageDialog(
									dialog,
									"Target Cardinality is not positive number (or -1)",
									"Negative Number Error",
									JOptionPane.ERROR_MESSAGE, null);
					return false;
				}
				InstAttribute v = buffer[0];
				v.setValue(instIdentifier.getValue()
						+ "#"
						+ (String) instDisplayName.getValue()
						+ "#"
						+ (String) instPanelName.getValue()
						+ "#"
						+ instRelationExclusive.getValue()
						+ "#"
						+ instSourceExclusive.getValue()
						+ "#"
						+ instTargetExclusive.getValue()
						+ "#"
						+ ((Integer) instMinSourceCardinality.getValue())
								.intValue()
						+ "#"
						+ ((Integer) instSourceCardinality.getValue())
								.intValue()
						+ "#"
						+ ((Integer) instMinTargetCardinality.getValue())
								.intValue()
						+ "#"
						+ ((Integer) instTargetCardinality.getValue())
								.intValue());

				List<InstAttribute> attributes = ((List<InstAttribute>) element
						.getInstAttributes()
						.get(SyntaxElement.VAR_METAENUMVALUE).getValue());
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

				System.out.println(attributes.remove(v));

				System.out
						.println(((DefaultListModel<InstAttribute>) getModel())
								.removeElement(v));

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
