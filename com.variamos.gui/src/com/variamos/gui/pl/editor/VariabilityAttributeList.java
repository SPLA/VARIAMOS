package com.variamos.gui.pl.editor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import com.cfm.hlcl.BinaryDomain;
import com.cfm.hlcl.DomainParser;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.Variable;
import com.cfm.productline.type.IntegerType;
import com.cfm.productline.type.StringType;
import com.variamos.gui.pl.editor.VariabilityParameterDialog.DialogButtonAction;

@SuppressWarnings("serial")
public class VariabilityAttributeList extends JList<Variable> {
	private ProductLineGraphEditor editor;
	private VariabilityElement element;
	
	private Variable spoof = new Variable("Add ...", "Add ...", StringType.IDENTIFIER);
	
	public VariabilityAttributeList(ProductLineGraphEditor editor){
		this.editor = editor;
		init(null);
	}

	public VariabilityAttributeList(ProductLineGraphEditor editor, VariabilityElement elm) {
		this.editor = editor;
		this.element = elm;
		init(elm.getVarAttributes());
	}
	
	private void init(List<Variable> varAttributes){
		setModel(new DefaultListModel<Variable>());
		final DefaultListModel<Variable> model = (DefaultListModel<Variable>) getModel();
		
		if( varAttributes != null )
			for(Variable v : varAttributes)
				model.addElement(v);
		
		model.addElement(spoof);
		
		//setSize(new Dimension(150, 150));
		setPreferredSize(new Dimension(150, 150));
		
		addMouseListener(new MouseAdapter() {
			
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		            int index = locationToIndex(evt.getPoint());
		            Variable v = null;
		            
		            if( index != model.getSize() - 1 )
		            	v = getModel().getElementAt(index);
		            
		            editItem(v);
		        } 
		    }
		});
		setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(
					@SuppressWarnings("rawtypes") JList list, Object value, int index,
					boolean isSelected, boolean cellHasFocus) {
				JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				lbl.setText( ((Variable)value).getName() );
				return lbl;
			}
		});
	}
	
	protected void editItem(Variable var){
		final boolean insert = (var == null);
		
		if( insert ){
			var = new Variable();
			var.setType(IntegerType.IDENTIFIER);
			var.setDomain(BinaryDomain.INSTANCE);
		}
		//HACK for accesing a non-final variable inside of an inner class
		final Variable[] buffer = { var };
		
		//Name
		final Variable name = new Variable("Name", var.getName(), StringType.IDENTIFIER);

		
		//SetDomain metaDomain = new SetDomain();
		//metaDomain.setIdentifier("MetaDomain");

		//DomainRegister reg = editor.getDomainRegister();
		//for( Type d : reg.getRegisteredDomains() )
		//	metaDomain.getElements().add(d.getIdentifier());
		//reg.registerDomain(metaDomain);
		
		String domainRepresentation = "0, 1";
		if(!insert)
			domainRepresentation = var.getDomain().getStringRepresentation();
			
		//Domain
		final Variable domain = new Variable("Domain", domainRepresentation, StringType.IDENTIFIER);
		
		final VariabilityParameterDialog dialog = new VariabilityParameterDialog(editor, name, domain);
		dialog.setOnAccept(new DialogButtonAction() {
			@Override
			public boolean onAction() {
				//This calls Pull on each parameter
				dialog.getParameters();
				
				Variable v = buffer[0];
				//Set name and domain
				v.setName( (String)name.getValue());
				//v.setType( (String)domain.getType() );
				v.setDomain(DomainParser.parseDomain( (String)domain.getValue()));
				
//				System.out.println( v.getDomain() );
				
				if( insert ){
					((DefaultListModel<Variable>)getModel()).insertElementAt(v, getModel().getSize() - 1);
					element.getVarAttributes().add(v);
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
//		DomainRegister reg = editor.getDomainRegister();
//		reg.unregisterDomain("MetaDomain");
		
		updateUI();
	}
	
}
