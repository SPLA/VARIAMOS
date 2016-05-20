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
import com.variamos.dynsup.model.OpersExpr;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.BooleanType;
import com.variamos.dynsup.types.StringType;
import com.variamos.dynsup.types.VariableType;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.panels.PropertyParameterDialog.DialogButtonAction;

/**
 * A class to support the relation types for semantic pairwise and over two
 * relations. Initially copied from VariabilityAttributeList. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-10-31
 * @see com.variamos.gui.perspeditor.panels.VariabilityAttributeList
 */
@SuppressWarnings("serial")
public class RelationTypesList extends JList<InstAttribute> {

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
	private InstAttribute spoof = new InstAttribute("Add Type ...",
			new ElemAttribute("Add ...", StringType.IDENTIFIER,
					AttributeType.SYNTAX, false, "Add Type ...", "", "", 1, -1,
					"", "", -1, "", ""), "Add Type ...");

	public RelationTypesList(VariamosGraphEditor editor) {
		this.editor = editor;
		init(null);
	}

	@SuppressWarnings("unchecked")
	public RelationTypesList(final VariamosGraphEditor editor,
			final InstCell instCell) {
		this.editor = editor;
		this.instCell = instCell;
		this.element = instCell.getInstElement();
		InstAttribute o = element.getInstAttributes().get("relTypesAttr");
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

					editItem(v, index);
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

	protected void editItem(InstAttribute instAttribute, int index) {
		final boolean insert = (instAttribute == null);

		final InstAttribute instIdentifier = new InstAttribute("identifier",
				new ElemAttribute("identifier", StringType.IDENTIFIER,
						AttributeType.SYNTAX, false, "Identifier", "", "", 1,
						-1, "", "", -1, "", ""), "");

		final InstAttribute instDisplayName = new InstAttribute("displayName",
				new ElemAttribute("displayName", StringType.IDENTIFIER,
						AttributeType.SYNTAX, false, "Display Name", "", 0, 1,
						-1, "", "", -1, "", ""), "");

		final InstAttribute instRelExclusive = new InstAttribute(
				"relationExclusive", new ElemAttribute("relationExclusive",
						BooleanType.IDENTIFIER, AttributeType.SYNTAX, false,
						"Relation Exclusive", "", false, 1, -1, "", "", -1, "",
						""), false);

		final InstAttribute instSourceExclusive = new InstAttribute(
				"sourceExclusive", new ElemAttribute("sourceExclusive",
						BooleanType.IDENTIFIER, AttributeType.SYNTAX, false,
						"Source Exclusive", "", false, 1, -1, "", "", -1, "",
						""), false);

		final InstAttribute instTargetExclusive = new InstAttribute(
				"targetExclusive", new ElemAttribute("targetExclusive",
						BooleanType.IDENTIFIER, AttributeType.SYNTAX, false,
						"Target Exclusive",
						VariableType.class.getCanonicalName(), "String", "",
						"", 1, -1, "", "", -1, "", ""), false);

		final InstAttribute instMinSourceCard = new InstAttribute(
				"minSourceCardinality", new ElemAttribute(
						"minSourceCardinality", IntegerType.IDENTIFIER,
						AttributeType.SYNTAX, false,
						"Minim Source Cardinality", "", false, 1, -1, "", "",
						-1, "", ""), 0);

		final InstAttribute instMaxSourceCard = new InstAttribute(
				"maxSourceCardinality", new ElemAttribute(
						"maxSourceCardinality", IntegerType.IDENTIFIER,
						AttributeType.SYNTAX, false,
						"Maximum Source Cardinality", "", "", 1, -1, "", "",
						-1, "", ""), 1);
		final InstAttribute instMinTargetCard = new InstAttribute(
				"minTargetCardinality", new ElemAttribute(
						"minTargetCardinality", IntegerType.IDENTIFIER,
						AttributeType.SYNTAX, false,
						"Minimum Target Cardinality", "enumType", "ME", "", "",
						1, -1, "", "", -1, "", ""), 0);
		final InstAttribute instMaxTargetCard = new InstAttribute(
				"maxTargetCardinality", new ElemAttribute(
						"maxTargetCardinality", IntegerType.IDENTIFIER,
						AttributeType.SYNTAX, false,
						"Maximum Target Cardinality", "", 1, 1, -1, "", "", -1,
						"", ""), 1);
		final InstAttribute instSemanticExpressions = new InstAttribute(
				"semanticExpression", new ElemAttribute("semanticExpression",
						OpersExpr.class.getCanonicalName(),
						AttributeType.SYNTAX, false, "Semantic Expression", "",
						"", 1, -1, "", "", -1, "", ""), null);
		if (insert) {
			// TODO move validation to a method on InstEnumeration
			@SuppressWarnings("unchecked")
			Collection<InstAttribute> instAttributes = (Collection<InstAttribute>) element
					.getInstAttributes().get("relTypesAttr").getValue();
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
					"RelTypeValue", StringType.IDENTIFIER,
					AttributeType.SYNTAX, false, "Relation Type Value", "", "",
					1, -1, "", "", -1, "", ""), "");
		} else {
			String split[] = ((String) instAttribute.getValue()).split("#");
			instIdentifier.setValue(split[0]);
			instDisplayName.setValue(split[1]);
			instRelExclusive.setValue(split[2]);
			instSourceExclusive.setValue(split[3]);
			instTargetExclusive.setValue(split[4]);
			instMinSourceCard.setValue(split[5]);
			instMaxSourceCard.setValue(split[6]);
			instMinTargetCard.setValue(split[7]);
			instMaxTargetCard.setValue(split[8]);
			List<InstAttribute> semExpAttributes = ((List<InstAttribute>) element
					.getInstAttributes().get("opersExprs").getValue());
			instSemanticExpressions.setValue(semExpAttributes.get(index)
					.getValue());
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

		final PropertyParameterDialog dialog = new PropertyParameterDialog(400,
				400, editor, element, instIdentifier, instDisplayName,
				instRelExclusive, instSourceExclusive, instTargetExclusive,
				instMinSourceCard, instMaxSourceCard, instMinTargetCard,
				instMaxTargetCard, instSemanticExpressions);
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
				v.setValue(instIdentifier.getValue() + "#"
						+ instDisplayName.getValue() + "#"
						+ (Boolean) instRelExclusive.getValue() + "#"
						+ (Boolean) instSourceExclusive.getValue() + "#"
						+ (Boolean) instTargetExclusive.getValue() + "#"
						+ ((Integer) instMinSourceCard.getValue()).intValue()
						+ "#"
						+ ((Integer) instMaxSourceCard.getValue()).intValue()
						+ "#"
						+ ((Integer) instMinTargetCard.getValue()).intValue()
						+ "#"
						+ ((Integer) instMaxTargetCard.getValue()).intValue());

				List<InstAttribute> attributes = ((List<InstAttribute>) element
						.getInstAttributes().get("relTypesAttr").getValue());

				List<InstAttribute> semExpAttributes = ((List<InstAttribute>) element
						.getInstAttributes().get("opersExprs").getValue());
				if (insert) {
					((DefaultListModel<InstAttribute>) getModel())
							.insertElementAt(v, getModel().getSize() - 1);
					attributes.add(v);
					semExpAttributes.add(instSemanticExpressions);
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
						.getInstAttributes().get("relTypesAttr").getValue());

				attributes.remove(v);

				List<InstAttribute> semExpAttributes = ((List<InstAttribute>) element
						.getInstAttributes().get("opersExprs").getValue());

				semExpAttributes.remove(v);

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
