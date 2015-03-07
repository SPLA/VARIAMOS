package com.variamos.gui.maineditor;

import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import com.variamos.gui.perspeditor.PerspEditorFunctions;
import com.variamos.gui.perspeditor.PerspEditorGraph;
import com.variamos.gui.perspeditor.PerspEditorMenuBar;
import com.variamos.perspsupport.expressionsupport.SemanticExpressionType;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.perspsupport.types.PerspectiveType;

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
	private boolean showPerspectiveButton = false;

	public int getPerspective() {
		return perspective;
	}

	private List<VariamosGraphEditor> graphEditors;
	private List<PerspEditorMenuBar> editorsMenu;

	public MainFrame() {
		graphEditors = new ArrayList<VariamosGraphEditor>();
		editorsMenu = new ArrayList<PerspEditorMenuBar>();
		Map<String, SemanticExpressionType> metaExpressionTypes = createMetaExpressionTypes();
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
		PerspEditorGraph refasGraph = null;
		Color bgColor = null;
		VariamosGraphEditor modelEditor = null;
		String perspTitle = "";
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0: // semantic
				abstractModel = new RefasModel(metaExpressionTypes,
						basicSemanticRefas);
				semanticRefas = abstractModel;
				syntaxRefas = new RefasModel(PerspectiveType.syntax,
						metaExpressionTypes, basicSyntaxRefas, semanticRefas);
				bgColor = new Color(252, 233, 252);
				perspTitle = "Semantic - VariaMos";
				System.out.println("Creating Semantic Perspective...");
				break;

			case 1:// modeling
				abstractModel = new RefasModel(PerspectiveType.modeling,
						metaExpressionTypes, syntaxRefas, semanticRefas);

				bgColor = new Color(236, 238, 255);
				perspTitle = "System Model - VariaMos";
				System.out.println("Creating Modeling Perspective...");
				break;

			case 2:// syntax
				abstractModel = syntaxRefas;

				bgColor = new Color(255, 255, 245);
				perspTitle = "Syntax - VariaMos";
				System.out.println("Creating Syntax Perspective...");
				break;

			case 3:// simulation
				abstractModel = new RefasModel(PerspectiveType.simulation,
						metaExpressionTypes, syntaxRefas, semanticRefas);
				bgColor = new Color(236, 252, 255);
				perspTitle = "Simulation - VariaMos";
				System.out.println("Creating Simulation Perspective...");
				break;

			}
			refasGraph = new PerspEditorGraph(i + 1, abstractModel);

			VariamosGraphEditor editor = new VariamosGraphEditor(this, perspTitle,
					new VariamosGraphComponent(refasGraph, bgColor), i + 1,
					abstractModel);
			editor.setGraphEditorFunctions(new PerspEditorFunctions(editor));
			if (i == 1)
				modelEditor = editor;

			if (i == 3)
				editor.setModelEditor(modelEditor);

			waitCursor = new Cursor(Cursor.WAIT_CURSOR);
			defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

			graphEditors.add(editor);

			editorsMenu.add(new PerspEditorMenuBar(graphEditors.get(i)));

			editor.updateView();
		}

		System.out.println("GUI creation complete");
		this.add(graphEditors.get(1));
		this.setJMenuBar(editorsMenu.get(1));
		this.setVisible(true);
	}

	private Map<String, SemanticExpressionType> createMetaExpressionTypes() {
		Map<String, SemanticExpressionType> out = new HashMap<String, SemanticExpressionType>();
		out.put("And", new SemanticExpressionType("And", "#/\"", "#/\"", "and",
				SemanticExpressionType.BOOLEXP, SemanticExpressionType.BOOLEXP,
				SemanticExpressionType.BOOLEXP, false, false));
		out.put("Assign", new SemanticExpressionType("Assign", "=", "=",
				"assign", SemanticExpressionType.IDEN,
				SemanticExpressionType.EXP, SemanticExpressionType.BOOLEXP,
				false, false));
		out.put("Subtraction", new SemanticExpressionType("Subtraction", "-",
				"-", "diff", SemanticExpressionType.NUMEXP,
				SemanticExpressionType.NUMEXP, SemanticExpressionType.NUMEXP,
				false, false));
		out.put("DoubleImplies", new SemanticExpressionType("DoubleImplies",
				"#<==>", "#<==>", "doubleImplies",
				SemanticExpressionType.BOOLEXP, SemanticExpressionType.BOOLEXP,
				SemanticExpressionType.BOOLEXP, false, false));
		out.put("Equals", new SemanticExpressionType("Equals", "#=", "#=",
				"equals", SemanticExpressionType.EXP,
				SemanticExpressionType.EXP, SemanticExpressionType.BOOLEXP,
				false, false));
		out.put("Greater", new SemanticExpressionType("Greater", "#>", "#>",
				"greaterThan", SemanticExpressionType.NUMEXP,
				SemanticExpressionType.NUMEXP, SemanticExpressionType.BOOLEXP,
				false, false));
		out.put("GreaterOrEq", new SemanticExpressionType("GreaterOrEq", "#>=",
				"#>=", "greaterOrEqualsThan", SemanticExpressionType.NUMEXP,
				SemanticExpressionType.NUMEXP, SemanticExpressionType.BOOLEXP,
				false, false));
		out.put("Implies", new SemanticExpressionType("Implies", "#==>",
				"#==>", "implies", SemanticExpressionType.BOOLEXP,
				SemanticExpressionType.BOOLEXP, SemanticExpressionType.BOOLEXP,
				false, false));
		out.put("Less", new SemanticExpressionType("Less", "#<", "#<",
				"lessThan", SemanticExpressionType.NUMEXP,
				SemanticExpressionType.NUMEXP, SemanticExpressionType.BOOLEXP,
				false, false));
		out.put("LessOrEq", new SemanticExpressionType("LessOrEquals", "#<=",
				"#<=", "lessOrEqualsThan", SemanticExpressionType.NUMEXP,
				SemanticExpressionType.NUMEXP, SemanticExpressionType.BOOLEXP,
				false, false));
		out.put("Literal", new SemanticExpressionType("LiteralBool", "", "",
				"literalBooleanExpression", SemanticExpressionType.LIT,
				SemanticExpressionType.NONE, SemanticExpressionType.BOOLEXP,
				true, false));
		out.put("Negation", new SemanticExpressionType("Negation", "-", "-",
				"not", SemanticExpressionType.BOOLEXP,
				SemanticExpressionType.NONE, SemanticExpressionType.BOOLEXP,
				true, false));
		out.put("Number", new SemanticExpressionType("Number", "", "",
				"number", SemanticExpressionType.INTVAL,
				SemanticExpressionType.NONE, SemanticExpressionType.NUMEXP,
				true, false));
		out.put("NotEquals", new SemanticExpressionType("NotEquals", "\\==",
				"\\==", "notEquals", SemanticExpressionType.EXP,
				SemanticExpressionType.EXP, SemanticExpressionType.BOOLEXP,
				false, false));
		out.put("Or", new SemanticExpressionType("Or", "#\"/", "#\"/", "or",
				SemanticExpressionType.BOOLEXP, SemanticExpressionType.BOOLEXP,
				SemanticExpressionType.BOOLEXP, false, false));
		out.put("Product", new SemanticExpressionType("Product", "*", "*",
				"prod", SemanticExpressionType.NUMEXP,
				SemanticExpressionType.NUMEXP, SemanticExpressionType.NUMEXP,
				false, true));
		out.put("Sum", new SemanticExpressionType("Sum", "+", "+", "sum",
				SemanticExpressionType.NUMEXP, SemanticExpressionType.NUMEXP,
				SemanticExpressionType.NUMEXP, false, true));
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
		graphEditors.get(perspective - 1).installToolBar(this, perspective);
		graphEditors.get(perspective - 1).updateObjects();
		graphEditors.get(perspective - 1).setVisibleModel(0, - 1);
		graphEditors.get(perspective - 1).setDefaultButton();
		graphEditors.get(perspective - 1).updateView();
		if (perspective != 4)
			graphEditors.get(3).hideDashBoard();
		this.revalidate();
		this.repaint();
	}

	public void setAdvancedPerspective(boolean advancedPerspective) {
			showPerspectiveButton = advancedPerspective;
			int i=0;
			for (VariamosGraphEditor e : graphEditors)
			{
				e.installToolBar(this, i++);
			}
	}
	
	public boolean isAdvancedPerspective()
	{
		return showPerspectiveButton;
	}
}
