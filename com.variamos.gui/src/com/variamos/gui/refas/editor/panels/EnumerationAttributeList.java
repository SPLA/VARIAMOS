package com.variamos.gui.refas.editor.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.refas.editor.panels.PropertyParameterDialog.DialogButtonAction;
import com.variamos.syntax.instancesupport.InstAttribute;
import com.variamos.syntax.instancesupport.InstElement;
import com.variamos.syntax.metamodelsupport.AbstractAttribute;
import com.variamos.syntax.metamodelsupport.MetaEnumeration;
import com.variamos.syntax.types.StringType;

/**
 * A class to support the property list for instance enumerations on modeling. Initially
 * copied from VariabilityAttributeList on pl.editor. Part of PhD work at University of
 * Paris 1
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
	/**
	 * 
	 */
	private InstAttribute spoof = new InstAttribute("Add ...",
			new AbstractAttribute("Add ...", StringType.IDENTIFIER, false,
					"Add ...", ""), "Add ...");

	public EnumerationAttributeList(VariamosGraphEditor editor) {
		this.editor = editor;
		init(null);
	}

	public EnumerationAttributeList(VariamosGraphEditor editor, InstElement elm) {
		this.editor = editor;
		this.element = elm;
		InstAttribute o = elm.getInstAttributes().get(
				MetaEnumeration.VAR_METAENUMVALUE);
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
		setPreferredSize(new Dimension(100, 80));
		setMaximumSize(new Dimension(100, 80));

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

	protected void editItem(InstAttribute var) {
		final boolean insert = (var == null);

		if (insert) {
			//TODO move validation to a method on InstEnumeration
		Collection<InstAttribute> instAttributes = (Collection<InstAttribute>) element
				.getInstAttributes().get(MetaEnumeration.VAR_METAENUMVALUE)
				.getValue();
		boolean notFound = true;
		int i = 1;
		/*
		 * while (notFound) { for (InstAttribute i : instAttributes) {
		 * if(i.getIdentifier().equals("enum"+i)) break; }
		 * //instAttributes.contains(new InstAttribute("enum"+i++))); ) }
		 */
		while (instAttributes.contains(new InstAttribute("enum" + i))) {
			i++;//TODO fix ids? verify they are different
		}

		// Name
		var = new InstAttribute("enum" + i,
				new AbstractAttribute("EnumValue", StringType.IDENTIFIER,
						false, "Enumeration Value", ""), "");
		
		}
		final InstAttribute name = var;
		
		// HACK for accesing a non-final variable inside of an inner class
		final InstAttribute[] buffer = { name };

		// SetDomain metaDomain = new SetDomain();
		// metaDomain.setIdentifier("MetaDomain");

		// DomainRegister reg = editor.getDomainRegister();
		// for( Type d : reg.getRegisteredDomains() )
		// metaDomain.getElements().add(d.getIdentifier());
		// reg.registerDomain(metaDomain);

		// String domainRepresentation = "0, 1";
		// if(!insert)
		// = var.getDomain().getStringRepresentation();


		final PropertyParameterDialog dialog = new PropertyParameterDialog(
				editor, name);
		dialog.setOnAccept(new DialogButtonAction() {
			@Override
			public boolean onAction() {
				// This calls Pull on each parameter
				dialog.getParameters();

				InstAttribute v = buffer[0];
				v.setIdentifier((String) name.getIdentifier());
				if (insert) {
					((DefaultListModel<InstAttribute>) getModel())
							.insertElementAt(v, getModel().getSize() - 1);
					((Collection<InstAttribute>) element.getInstAttributes()
							.get(MetaEnumeration.VAR_METAENUMVALUE).getValue())
							.add(v);
				}

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
		// DomainRegister reg = editor.getDomainRegister();
		// reg.unregisterDomain("MetaDomain");

		updateUI();
	}

}
