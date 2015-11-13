package com.variamos.gui.perspeditor.model;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import com.cfm.productline.Variable;
import com.variamos.configurator.DomainAnnotation;
import com.variamos.hlcl.Domain;

/**
 * A class to support the domain list definition. Part of PhD work at University
 * of Paris 1. Initially copied from
 * com.variamos.gui.pl.configuration.treetable.ListDomainEditor
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.configuration.treetable.ListDomainEditor
 */
@SuppressWarnings("serial")
public class ListDomainEditor extends JPanel {

	private JList<String> list;
	private JList<String> disabled;

	static class IntegerListModel extends AbstractListModel<String> {
		private List<String> values;

		public IntegerListModel(List<String> values) {
			this.values = values;
		}

		@Override
		public String getElementAt(int arg0) {
			return values.get(arg0);
		}

		@Override
		public int getSize() {
			return values.size();
		}

		public void addElement(String elm) {
			values.add(elm);
			this.fireContentsChanged(this, 0, values.size());
		}

		public void clear() {
			values.clear();
			this.fireContentsChanged(this, 0, values.size());
		}

		public void addAll(List<String> values) {
			this.values.addAll(values);
			this.fireContentsChanged(this, 0, values.size());
		}

	}

	static class DomainListModel extends AbstractListModel<String> {
		private List<String> values;
		private List<DomainAnnotation> domainAnnotations;

		public DomainListModel(List<String> values,
				List<DomainAnnotation> domainAnnotations) {
			this.values = values;
			this.domainAnnotations = domainAnnotations;
		}

		@Override
		public String getElementAt(int arg0) {
			return values.get(arg0);
		}

		@Override
		public int getSize() {
			return values.size();
		}

		public void addElement(String elm) {
			values.add(elm);
			this.fireContentsChanged(this, 0, values.size());
		}

		public void clear() {
			values.clear();
			this.fireContentsChanged(this, 0, values.size());
		}

		public void addAll(List<String> values) {
			this.values.addAll(values);
			this.fireContentsChanged(this, 0, values.size());
		}

		public DomainAnnotation getAnnotationFor(Integer value) {
			for (DomainAnnotation da : domainAnnotations)
				if (da.getValue() == value)
					return da;

			return null;
		}
	}

	static class DomainListCellRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			JLabel lbl = (JLabel) super.getListCellRendererComponent(list,
					value, index, isSelected, cellHasFocus);

			DomainListModel model = (DomainListModel) list.getModel();
			// DomainAnnotation da = model.getAnnotationFor((Integer) value);
			// if (da != null && da.notAvailable()) {
			// lbl.setForeground(Color.LIGHT_GRAY);
			// lbl.setEnabled(false);

			// String txt = lbl.getText();
			// txt += " (" + da.getStep() + ")";
			// lbl.setText(txt);
			// }

			return lbl;
		}

	}

	public ListDomainEditor(AssociationRow node) {
		setLayout(new BorderLayout());
		for (Variable var : node.getValues())
			setupFor(node, var);
	}

	public void setupFor(AssociationRow node, Variable var) {
		if (list != null) {
			remove(list);
			remove(disabled);
		}

		Domain domain = var.getDomain();
		List<String> values = domain.getPossibleStringValues();
		List<String> disabledValues = new ArrayList<>();

		for (DomainAnnotation da : node.getDomainAnnotations()) {
			values.remove((Object) da.getValue());
			disabledValues.add(String.valueOf(da.getValue()));
		}

		list = new JList<>(new DomainListModel(values,
				node.getDomainAnnotations()));
		list.setCellRenderer(new DomainListCellRenderer());
		list.setPreferredSize(new Dimension(60, 200));

		disabled = new JList<>(new DomainListModel(disabledValues,
				node.getDomainAnnotations()));
		disabled.setCellRenderer(new DomainListCellRenderer());
		disabled.setPreferredSize(new Dimension(60, 200));

		TitledBorder br1 = BorderFactory.createTitledBorder("Values");
		JScrollPane left = new JScrollPane(list);
		left.setBorder(br1);
		add(left, BorderLayout.WEST);

		TitledBorder br2 = BorderFactory.createTitledBorder("Disabled");
		JScrollPane right = new JScrollPane(disabled);
		right.setBorder(br2);
		add(right, BorderLayout.EAST);
	}

	public String getSelectedValue() {
		return list.getSelectedValue();
	}

}
