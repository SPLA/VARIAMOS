package com.variamos.gui.refas.editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;

import com.cfm.productline.Variable;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.pl.editor.SpringUtilities;
import com.variamos.gui.refas.editor.widgets.WidgetR;
import com.variamos.gui.refas.editor.widgets.RefasWidgetFactory;
import com.variamos.syntaxsupport.metamodel.InstAttribute;

/**
 * @author unknown
 *
 */
@SuppressWarnings("serial")
public class GoalsParameterDialog extends JDialog{
	private HashMap<String, WidgetR> widgets;
	private DialogButtonAction onAccept, onCancel;
	
	static interface DialogButtonAction{
		public boolean onAction();
	}
	
	public GoalsParameterDialog(VariamosGraphEditor editor, InstAttribute... arguments){
		super(editor.getFrame(), "Parameters");
		
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());
		
		RefasWidgetFactory factory = new RefasWidgetFactory();
		
		widgets = new HashMap<String, WidgetR>();
		
		for(InstAttribute p : arguments){
			WidgetR w = factory.getWidgetFor(p);
			w.editVariable(p);
			
			w.addPropertyChangeListener("value", new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					revalidate();
					doLayout();
					pack();
				}
			});
			
		//	widgets.put(p.getName(), w);

		//	panel.add(new JLabel(p.getName() + ": "));
			panel.add(w);
		}
		
		SpringUtilities.makeCompactGrid(panel,
										arguments.length, 2,
										4, 4,
										4, 4);
		
		add(panel, BorderLayout.CENTER);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new SpringLayout());
		
		final JButton btnCancel = new JButton();
		btnCancel.setText("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( onCancel == null ){
					dispose();
					return;
				}
				if( onCancel.onAction() )
					dispose();
			}
		});
		
		buttonsPanel.add(btnCancel);
		
		final JButton btnAccept = new JButton();
		btnAccept.setText("Accept");
		btnAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( onAccept != null )
					if( onAccept.onAction() )
						dispose();
			}
		});
		
		buttonsPanel.add(btnAccept);
		
		
		
		SpringUtilities.makeCompactGrid(buttonsPanel,
					1, 2,
					4, 4,
					4, 4);
		
		add(buttonsPanel, BorderLayout.SOUTH);
		
		getRootPane().setDefaultButton(btnAccept);
		getRootPane().registerKeyboardAction(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				btnCancel.doClick();
			}
			
		}, KeyStroke.getKeyStroke("ESCAPE"), JComponent.WHEN_IN_FOCUSED_WINDOW);
		pack();
	}
	
	/**
	 * @return
	 */
	public Map<String, InstAttribute> getParameters(){
		Map<String, InstAttribute> map = new HashMap<>();
		
		for(String s : widgets.keySet()){
			InstAttribute v = widgets.get(s).getInstAttribute();
	//		map.put(v.getName(), v);
		}
		
		return map;
	}
	
	public void center(){
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setOnAccept(DialogButtonAction onAccept) {
		this.onAccept = onAccept;
	}

	public void setOnCancel(DialogButtonAction onCancel) {
		this.onCancel = onCancel;
	}
	
}