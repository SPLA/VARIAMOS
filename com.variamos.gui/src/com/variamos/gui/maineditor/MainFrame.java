package com.variamos.gui.maineditor;

import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import com.variamos.gui.refas.editor.RefasGraph;
import com.variamos.gui.refas.editor.RefasGraphEditorFunctions;
import com.variamos.gui.refas.editor.RefasMenuBar;
import com.variamos.gui.refas.editor.SemanticPlusSyntax;
import com.variamos.refas.RefasModel;
import com.variamos.semantic.expressionsupport.MetaExpressionType;
import com.variamos.semantic.types.PerspectiveType;

public class MainFrame extends JFrame {
	public List<VariamosGraphEditor> getGraphEditors() {
		return graphEditors;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3838729345096823146L;
	private String perspectiveTitle;
	/**
	 * 
	 */
	protected String appTitle;
	private int perspective = 1;
	private Cursor waitCursor, defaultCursor;

	public int getPerspective() {
		return perspective;
	}

	private List<VariamosGraphEditor> graphEditors;
	private List<RefasMenuBar> editorsMenu;

	public MainFrame() {
		graphEditors = new ArrayList<VariamosGraphEditor>();
		editorsMenu = new ArrayList<RefasMenuBar>();
		Map<String, MetaExpressionType> metaExpressionTypes = createMetaExpressionTypes();
		this.appTitle = "VariaMos";
		this.perspectiveTitle = "Modeling Perspective";
		this.setTitle(perspectiveTitle + " - " + appTitle);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1070, 740);
		RefasModel basicSyntaxRefas = new RefasModel(
				PerspectiveType.basicSyntax, metaExpressionTypes);
		RefasModel basicSemanticRefas = new RefasModel(
				PerspectiveType.basicSemantic, metaExpressionTypes);
		RefasModel semanticRefas = null;
		RefasModel syntaxRefas = null;
		RefasModel abstractModel = null;
		SemanticPlusSyntax sematicSyntaxObject = new SemanticPlusSyntax();
		VariamosGraphEditor.setSematicSyntaxObject(sematicSyntaxObject);
		RefasGraph refasGraph = null;
		Color bgColor = null;
		VariamosGraphEditor modelEditor = null;
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0: // semantic
				abstractModel = new RefasModel(metaExpressionTypes,
						basicSemanticRefas);
				semanticRefas = abstractModel;
				syntaxRefas = new RefasModel(PerspectiveType.syntax,
						metaExpressionTypes, basicSyntaxRefas, semanticRefas);
				bgColor = new Color(252, 233, 252);
				System.out.println("Creating Semantic Perspective...");
				break;

			case 1:// modeling
				abstractModel = new RefasModel(PerspectiveType.modeling,
						metaExpressionTypes, syntaxRefas, semanticRefas);

				bgColor = new Color(236, 238, 255);
				System.out.println("Creating Modeling Perspective...");
				break;

			case 2:// syntax
				abstractModel = syntaxRefas;

				bgColor = new Color(255, 255, 245);
				System.out.println("Creating Syntax Perspective...");
				break;

			case 3:// simulation
				abstractModel = new RefasModel(PerspectiveType.simulation,
						metaExpressionTypes, syntaxRefas, semanticRefas);
				bgColor = new Color(236, 252, 255);
				System.out.println("Creating Simulation Perspective...");
				break;

			}
			refasGraph = new RefasGraph(i + 1, abstractModel);

			VariamosGraphEditor editor = new VariamosGraphEditor(this,
					new VariamosGraphComponent(refasGraph, bgColor), i + 1,
					abstractModel);
			editor.setGraphEditorFunctions(new RefasGraphEditorFunctions(editor));
			if (i == 1)
				modelEditor = editor;

			if (i == 3)
				editor.setModelEditor(modelEditor);

			waitCursor = new Cursor(Cursor.WAIT_CURSOR);
			defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

			graphEditors.add(editor);

			editorsMenu.add(new RefasMenuBar(graphEditors.get(i)));

			editor.updateView();
		}

		System.out.println("GUI creation complete");
		this.add(graphEditors.get(1));
		this.setJMenuBar(editorsMenu.get(1));
		this.setVisible(true);
	}

	private Map<String, MetaExpressionType> createMetaExpressionTypes() {
		Map<String, MetaExpressionType> out = new HashMap<String, MetaExpressionType>();
		out.put("And", new MetaExpressionType("And", "#/\"", "#/\"", "and",
				MetaExpressionType.BOOLEXP, MetaExpressionType.BOOLEXP,
				MetaExpressionType.BOOLEXP, false,false));
		out.put("Assign", new MetaExpressionType("Assign", "=", "=", "assign",
				MetaExpressionType.IDEN, MetaExpressionType.EXP,
				MetaExpressionType.BOOLEXP, false,false));
		out.put("Subtraction", new MetaExpressionType("Subtraction", "-", "-",
				"diff", MetaExpressionType.NUMEXP, MetaExpressionType.NUMEXP,
				MetaExpressionType.NUMEXP, false,false));
		out.put("DoubleImplies", new MetaExpressionType("DoubleImplies",
				"#<==>", "#<==>", "doubleImplies", MetaExpressionType.BOOLEXP,
				MetaExpressionType.BOOLEXP, MetaExpressionType.BOOLEXP, false,false));
		out.put("Equals", new MetaExpressionType("Equals", "#=", "#=",
				"equals", MetaExpressionType.EXP, MetaExpressionType.EXP,
				MetaExpressionType.BOOLEXP, false,false));
		out.put("Greater", new MetaExpressionType("Greater", "#>", "#>",
				"greaterThan", MetaExpressionType.NUMEXP,
				MetaExpressionType.NUMEXP, MetaExpressionType.BOOLEXP, false,false));
		out.put("GreaterOrEq", new MetaExpressionType("GreaterOrEq", "#>=",
				"#>=", "greaterOrEqualsThan", MetaExpressionType.NUMEXP,
				MetaExpressionType.NUMEXP, MetaExpressionType.BOOLEXP, false,false));
		out.put("Implies", new MetaExpressionType("Implies", "#==>", "#==>",
				"implies", MetaExpressionType.BOOLEXP,
				MetaExpressionType.BOOLEXP, MetaExpressionType.BOOLEXP, false,false));
		out.put("Less", new MetaExpressionType("Less", "#<", "#<", "lessThan",
				MetaExpressionType.NUMEXP, MetaExpressionType.NUMEXP,
				MetaExpressionType.BOOLEXP, false,false));
		out.put("LessOrEq", new MetaExpressionType("LessOrEquals", "#<=",
				"#<=", "lessOrEqualsThan", MetaExpressionType.NUMEXP,
				MetaExpressionType.NUMEXP, MetaExpressionType.BOOLEXP, false,false));
		out.put("Literal", new MetaExpressionType("LiteralBool", "", "",
				"literalBooleanExpression", MetaExpressionType.LIT,
				MetaExpressionType.NONE, MetaExpressionType.BOOLEXP, true,false));
		out.put("Negation", new MetaExpressionType("Negation", "-", "-", "not",
				MetaExpressionType.BOOLEXP, MetaExpressionType.NONE,
				MetaExpressionType.BOOLEXP, true,false));
		out.put("Number", new MetaExpressionType("Number", "", "", "number",
				MetaExpressionType.INTVAL, MetaExpressionType.NONE,
				MetaExpressionType.NUMEXP, true,false));
		out.put("NotEquals", new MetaExpressionType("NotEquals", "\\==",
				"\\==", "notEquals", MetaExpressionType.EXP,
				MetaExpressionType.EXP, MetaExpressionType.BOOLEXP, false,false));
		out.put("Or", new MetaExpressionType("Or", "#\"/", "#\"/", "or",
				MetaExpressionType.BOOLEXP, MetaExpressionType.BOOLEXP,
				MetaExpressionType.BOOLEXP, false,false));
		out.put("Product", new MetaExpressionType("Product", "*", "*", "prod",
				MetaExpressionType.NUMEXP, MetaExpressionType.NUMEXP,
				MetaExpressionType.NUMEXP, false,true));
		out.put("Sum", new MetaExpressionType("Sum", "+", "+", "sum",
				MetaExpressionType.NUMEXP, MetaExpressionType.NUMEXP,
				MetaExpressionType.NUMEXP, false,true));
		return out;
	}

	public void waitingCursor(boolean waitingCursor) {
		if (waitingCursor)
			this.setCursor(waitCursor);
		else
			this.setCursor(defaultCursor);

	}

	public void setPerspective(int perspective) {
		this.perspective = perspective;
	}

	public void setLayout() {
		this.getRootPane().getContentPane().removeAll();
		this.add(graphEditors.get(perspective - 1));
		this.setJMenuBar(editorsMenu.get(perspective - 1));
		graphEditors.get(perspective - 1).updateObjects();
		graphEditors.get(perspective - 1).setVisibleModel(0, -1);
		graphEditors.get(perspective - 1).updateView();
		this.revalidate();
		this.repaint();
	}
}
