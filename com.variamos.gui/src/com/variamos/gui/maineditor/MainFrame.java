package com.variamos.gui.maineditor;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import com.cfm.common.AbstractModel;
import com.variamos.gui.refas.editor.RefasGraph;
import com.variamos.gui.refas.editor.RefasGraphEditorFunctions;
import com.variamos.gui.refas.editor.RefasMenuBar;
import com.variamos.gui.refas.editor.SemanticPlusSyntax;
import com.variamos.syntaxsupport.refas.Refas;

public class MainFrame extends JFrame {
	private String perspectiveTitle;
	/**
	 * 
	 */
	protected String appTitle;
	private int perspective=1;
	private int oldPerspective=1;
	public int getPerspective() {
		return perspective;
	}



	private List<VariamosGraphEditor> graphEditors;

	public MainFrame() {
		graphEditors = new ArrayList<VariamosGraphEditor>();
		this.appTitle = "VariaMos";
		this.perspectiveTitle = "Modeling Perspective";
		this.setTitle(perspectiveTitle + " - " + appTitle);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1070, 740);		
		AbstractModel abstractModel = null;
		SemanticPlusSyntax sematicSyntaxObject = new SemanticPlusSyntax();
		VariamosGraphEditor.setSematicSyntaxObject(sematicSyntaxObject);
		RefasGraph refasGraph = null;
		for (int i = 0; i < 4; i++) {
			abstractModel = new Refas();
			refasGraph = new RefasGraph(sematicSyntaxObject);
			VariamosGraphEditor editor  = new VariamosGraphEditor(this, 
					new VariamosGraphComponent(refasGraph),i+1,abstractModel);
			editor.setGraphEditorFunctions (new RefasGraphEditorFunctions(editor));

			graphEditors.add(editor);
		}
		this.setJMenuBar(new RefasMenuBar(graphEditors.get(2)));
		this.add(graphEditors.get(2));
		this.setVisible(true);
	}
	
	public void setPerspective(int perspective) {
		this.perspective = perspective;
	}
	
	public void setLayout()
	{
		this.getRootPane().getContentPane().removeAll();
		this.add(graphEditors.get(perspective-1));
		this.revalidate();
		this.repaint();
		oldPerspective = perspective;
	}
}
