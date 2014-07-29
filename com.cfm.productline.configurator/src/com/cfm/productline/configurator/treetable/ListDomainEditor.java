package com.cfm.productline.configurator.treetable;

import java.awt.BorderLayout;
import java.awt.Color;
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

import com.cfm.hlcl.Domain;
import com.cfm.productline.configurator.DomainAnnotation;

@SuppressWarnings("serial")
public class ListDomainEditor extends JPanel{
	
	private JList<Integer> list;
	private JList<Integer> disabled;

	static class IntegerListModel extends AbstractListModel<Integer>{
		private List<Integer> values;

		public IntegerListModel(List<Integer> values){
			this.values = values;
		}
		
		@Override
		public Integer getElementAt(int arg0) {
			return values.get(arg0);
		}

		@Override
		public int getSize() {
			return values.size();
		}
		
		public void addElement(int elm){
			values.add(elm);
			this.fireContentsChanged(this, 0, values.size());
		}
		
		public void clear(){
			values.clear();
			this.fireContentsChanged(this, 0, values.size());
		}
		
		public void addAll(List<Integer> values){
			this.values.addAll(values);
			this.fireContentsChanged(this, 0, values.size());
		}
		
	}
	
	static class DomainListModel extends AbstractListModel<Integer>{
		private List<Integer> values;
		private List<DomainAnnotation> domainAnnotations;
		
		public DomainListModel(List<Integer> values, List<DomainAnnotation> domainAnnotations){
			this.values = values;
			this.domainAnnotations = domainAnnotations;
		}
		
		@Override
		public Integer getElementAt(int arg0) {
			return values.get(arg0);
		}

		@Override
		public int getSize() {
			return values.size();
		}
		
		public void addElement(int elm){
			values.add(elm);
			this.fireContentsChanged(this, 0, values.size());
		}
		
		public void clear(){
			values.clear();
			this.fireContentsChanged(this, 0, values.size());
		}
		
		public void addAll(List<Integer> values){
			this.values.addAll(values);
			this.fireContentsChanged(this, 0, values.size());
		}

		public DomainAnnotation getAnnotationFor(Integer value) {
			for(DomainAnnotation da : domainAnnotations)
				if( da.getValue() == value )
					return da;
			
			return null;
		}
	}
	
	
	static class DomainListCellRenderer extends DefaultListCellRenderer{

		@Override
		public Component getListCellRendererComponent(JList<?> list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			
			JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
					cellHasFocus);
			
			DomainListModel model = (DomainListModel)list.getModel();
			DomainAnnotation da = model.getAnnotationFor( (Integer) value);
			if( da != null && da.notAvailable()){
				lbl.setForeground(Color.LIGHT_GRAY);
				lbl.setEnabled(false);
				
				String txt = lbl.getText();
				//txt += " (" + da.getStep() + ")";
				//lbl.setText(txt);
			}
			
			return lbl;
		}
		
	}
	
	public ListDomainEditor(ConfigurationNode node){
		setLayout(new BorderLayout());
		setupFor(node);
	}
	
	public void setupFor(ConfigurationNode node){
		if(list != null){
			remove(list);
			remove(disabled);
		}
		
		Domain domain = node.getVariable().getDomain();
		List<Integer> values = domain.getPossibleValues();
		List<Integer> disabledValues = new ArrayList<>();
		
		for(DomainAnnotation da : node.getDomainAnnotations()){
			values.remove( (Object)da.getValue() );
			disabledValues.add( da.getValue() );
		}
		
		list = new JList<>(new DomainListModel(values, node.getDomainAnnotations()));
		list.setCellRenderer(new DomainListCellRenderer());
		list.setPreferredSize(new Dimension(60, 200));

		disabled = new JList<>(new DomainListModel(disabledValues, node.getDomainAnnotations()));
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
	
	public Integer getSelectedValue() {
		return list.getSelectedValue();
	}
	
}
