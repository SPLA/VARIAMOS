package com.variamos.gui.maineditor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.variamos.core.refas.Refas;
import com.variamos.gui.refas.editor.RefasGraph;
import com.variamos.gui.refas.editor.RefasGraphEditorFunctions;
import com.variamos.gui.refas.editor.RefasMenuBar;
import com.variamos.gui.refas.editor.SemanticPlusSyntax;
import com.variamos.refas.core.types.PerspectiveType;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3838729345096823146L;
	private String perspectiveTitle;
	/**
	 * 
	 */
	protected String appTitle;
	private int perspective=1;
	public int getPerspective() {
		return perspective;
	}



	private List<VariamosGraphEditor> graphEditors;
	private List<RefasMenuBar>editorsMenu;

	public MainFrame() {
		graphEditors = new ArrayList<VariamosGraphEditor>();
		editorsMenu = new ArrayList<RefasMenuBar>();
		this.appTitle = "VariaMos";
		this.perspectiveTitle = "Modeling Perspective";
		this.setTitle(perspectiveTitle + " - " + appTitle);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1070, 740);		
		Refas basicSyntaxRefas = new Refas(PerspectiveType.basicSyntax);
		Refas basicSemanticRefas = new Refas(PerspectiveType.basicSemantic);
		Refas semanticRefas=null;
		Refas syntaxRefas=null;
		Refas abstractModel = null;
		SemanticPlusSyntax sematicSyntaxObject = new SemanticPlusSyntax();
		VariamosGraphEditor.setSematicSyntaxObject(sematicSyntaxObject);
		RefasGraph refasGraph = null;
		Color bgColor = null;
		for (int i = 0; i < 4; i++) {
			switch(i)
			{ 
			case 0 : //semantic
			abstractModel = new Refas(basicSemanticRefas);			
			semanticRefas = abstractModel;
			syntaxRefas = new Refas(PerspectiveType.syntax, basicSyntaxRefas, semanticRefas);
			bgColor = new Color(252,233,252);
			break;
			case 1 ://modeling
			abstractModel = new Refas(PerspectiveType.modeling, syntaxRefas, semanticRefas);

			bgColor = new Color(236,238,255);
			break;
			case 2 ://syntax
			abstractModel = syntaxRefas;

			bgColor = new Color(255,255,245);
			break;
			case 3 ://simulation
			abstractModel = new Refas(PerspectiveType.simulation, syntaxRefas, semanticRefas);
			bgColor = new Color(236,250,235);
			break;
			
			}
			refasGraph = new RefasGraph(sematicSyntaxObject);
			
			VariamosGraphEditor editor  = new VariamosGraphEditor(this, 
					new VariamosGraphComponent(refasGraph, bgColor),i+1,abstractModel);
			editor.setGraphEditorFunctions (new RefasGraphEditorFunctions(editor));

			graphEditors.add(editor);

			editorsMenu.add(new RefasMenuBar(graphEditors.get(i)));
			editor.updateView();
		}
		
		this.add(graphEditors.get(1));
		this.setVisible(true);
	}
	
	public void setPerspective(int perspective) {
		this.perspective = perspective;
	}
	
	public void setLayout()
	{
		this.getRootPane().getContentPane().removeAll();
		this.add(graphEditors.get(perspective-1));
		this.setJMenuBar(editorsMenu.get(perspective-1));
		this.revalidate();
		this.repaint();
	}
}
