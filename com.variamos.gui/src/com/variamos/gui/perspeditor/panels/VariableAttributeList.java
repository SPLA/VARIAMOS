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
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.panels.PropertyParameterDialog.DialogButtonAction;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstCell;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.partialsorts.EnumerationSort;
import com.variamos.perspsupport.semanticsupport.SemanticVariable;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.types.BooleanType;
import com.variamos.perspsupport.types.ClassSingleSelectionType;
import com.variamos.perspsupport.types.EnumerationSingleSelectionType;
import com.variamos.perspsupport.types.StringType;

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
public class VariableAttributeList extends JList<InstAttribute> {

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
			new AbstractAttribute("Add ...", StringType.IDENTIFIER, false,
					"Add ...", "", 1, -1, "", "", -1, "", ""), "Add ...");

	public VariableAttributeList(VariamosGraphEditor editor) {
		this.editor = editor;
		init(null);
	}

	@SuppressWarnings("unchecked")
	public VariableAttributeList(final VariamosGraphEditor editor,
			final InstCell instCell) {
		this.editor = editor;
		this.instCell = instCell;
		this.element = instCell.getInstElement();
		InstAttribute o = element.getInstAttributes().get("attributeValue");
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

		final InstAttribute instName = new InstAttribute(
				SemanticVariable.VAR_NAME, new AbstractAttribute(
						SemanticVariable.VAR_NAME, StringType.IDENTIFIER,
						false, SemanticVariable.VAR_NAMENAME, "", 1, -1, "",
						"", -1, "", ""), "");

		final InstAttribute instValue = new InstAttribute(
				SemanticVariable.VAR_VALUE, new AbstractAttribute(
						SemanticVariable.VAR_VALUE, IntegerType.IDENTIFIER,
						false, SemanticVariable.VAR_VALUENAME, 0, 1, -1, "",
						"", -1, "", ""), 0);

		final InstAttribute instExtVisible = new InstAttribute(
				SemanticVariable.VAR_EXTVISIBLE, new AbstractAttribute(
						SemanticVariable.VAR_EXTVISIBLE,
						BooleanType.IDENTIFIER, false,
						SemanticVariable.VAR_EXTVISIBLENAME, false, 1, -1, "",
						"", -1, "", ""), false);

		final InstAttribute instExtControl = new InstAttribute(
				SemanticVariable.VAR_EXTCONTROL, new AbstractAttribute(
						SemanticVariable.VAR_EXTCONTROL,
						BooleanType.IDENTIFIER, false,
						SemanticVariable.VAR_EXTCONTROLNAME, false, 1, -1, "",
						"", -1, "", ""), false);

		final InstAttribute instVariableType = new InstAttribute(
				SemanticVariable.VAR_VARIABLETYPE, new AbstractAttribute(
						SemanticVariable.VAR_VARIABLETYPE,
						EnumerationSingleSelectionType.IDENTIFIER, false,
						SemanticVariable.VAR_VARIABLETYPENAME,
						SemanticVariable.VAR_VARIABLETYPECLASS, "String", "",
						"", 1, -1, "", "", -1, "", ""), "");

		final InstAttribute instContext = new InstAttribute(
				SemanticVariable.VAR_CONTEXT, new AbstractAttribute(
						SemanticVariable.VAR_CONTEXT, BooleanType.IDENTIFIER,
						false, SemanticVariable.VAR_CONTEXTNAME, false, 1, -1,
						"", "", -1, "", ""), false);

		final InstAttribute instVariableDomain = new InstAttribute(
				SemanticVariable.VAR_VARIABLEDOMAIN, new AbstractAttribute(
						SemanticVariable.VAR_VARIABLEDOMAIN,
						StringType.IDENTIFIER, false,
						SemanticVariable.VAR_VARIABLEDOMAINNAME, "", 1, -1, "",
						"", -1, "", ""), "");
		final InstAttribute instEnumerationType = new InstAttribute(
				SemanticVariable.VAR_ENUMERATIONTYPE, new AbstractAttribute(
						SemanticVariable.VAR_ENUMERATIONTYPE,
						ClassSingleSelectionType.IDENTIFIER, false,
						SemanticVariable.VAR_ENUMERATIONTYPENAME,
						SemanticVariable.VAR_ENUMERATIONTYPECLASS, "ME", "",
						"", 1, -1, "", "", -1, "", ""), "");
		final InstAttribute instVariableConfigValue = new InstAttribute(
				SemanticVariable.VAR_VARIABLECONFIGVALUE,
				new AbstractAttribute(SemanticVariable.VAR_VARIABLECONFIGVALUE,
						IntegerType.IDENTIFIER, false,
						SemanticVariable.VAR_VARIABLECONFIGVALUENAME, 1, 1, -1,
						"", "", -1, "", ""), 1);
		final InstAttribute instVariableConfigDomain = new InstAttribute(
				SemanticVariable.VAR_VARIABLECONFIGDOMAIN,
				new AbstractAttribute(
						SemanticVariable.VAR_VARIABLECONFIGDOMAIN,
						StringType.IDENTIFIER, false,
						SemanticVariable.VAR_VARIABLECONFIGDOMAINNAME, "", 1,
						-1, "", "", -1, "", ""), "");
		if (insert) {
			// TODO move validation to a method on InstEnumeration
			@SuppressWarnings("unchecked")
			Collection<InstAttribute> instAttributes = (Collection<InstAttribute>) element
					.getInstAttributes().get("attributeValue").getValue();
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
			instAttribute = new InstAttribute("enum" + i,
					new AbstractAttribute("EnumValue", StringType.IDENTIFIER,
							false, "Enumeration Value", "", 1, -1, "", "", -1,
							"", ""), "");
		} else {
			String split[] = ((String) instAttribute.getValue()).split("#");
			instName.setValue(split[0]);
			instValue.setValue(split[1]);
			instExtVisible.setValue(split[2]);
			instExtControl.setValue(split[3]);
			instVariableType.setValue(split[4]);
			instContext.setValue(split[5]);
			instVariableDomain.setValue(split[6]);
			instEnumerationType.setValue(split[7]);
			instVariableConfigValue.setValue(split[8]);
			instVariableConfigDomain.setValue(split[9]);
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
				editor, instName, instValue, instExtVisible, instExtControl,
				instVariableType, instContext, instVariableDomain,
				instEnumerationType, instVariableConfigValue,
				instVariableConfigDomain);
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
				// if (((Integer) instSourceCardinality.getValue()).intValue() <
				// -1) {
				// JOptionPane
				// .showMessageDialog(
				// dialog,
				// "Source Cardinality is not positive number (or -1)",
				// "Negative Number Error",
				// JOptionPane.ERROR_MESSAGE, null);
				// return false;
				// }
				// if (((Integer) instVariableConfigValue.getValue()).intValue()
				// < -1) {
				// JOptionPane
				// .showMessageDialog(
				// dialog,
				// "Target Cardinality is not positive number (or -1)",
				// "Negative Number Error",
				// JOptionPane.ERROR_MESSAGE, null);
				// return false;
				// }
				InstAttribute v = buffer[0];
				v.setValue(instName.getValue()
						+ "#"
						+ ((Integer) instValue.getValue()).intValue()
						+ "#"
						+ (Boolean) instExtVisible.getValue()
						+ "#"
						+ (Boolean) instExtControl.getValue()
						+ "#"
						+ (String) instVariableType.getValue()
						+ "#"
						+ (Boolean) instContext.getValue()
						+ "#"
						+ (String) instVariableDomain.getValue()
						+ "#"
						+ (String) instEnumerationType.getValue()
						+ "#"
						+ ((Integer) instVariableConfigValue.getValue())
								.intValue() + "-"
						+ (String) instVariableConfigDomain.getValue());

				List<InstAttribute> attributes = ((List<InstAttribute>) element
						.getInstAttributes().get("attributeValue").getValue());
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
						.getInstAttributes().get("attributeValue").getValue());

				attributes.remove(v);

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
