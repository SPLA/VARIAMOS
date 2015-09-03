package com.variamos.gui.maineditor;

import java.awt.Color;
import java.awt.Cursor;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.variamos.core.enums.SolverEditorType;
import com.variamos.defectAnalyzer.defectAnalyzer.DefectsVerifier;
import com.variamos.defectAnalyzer.defectAnalyzer.IntDefectsVerifier;
import com.variamos.gui.perspeditor.PerspEditorFunctions;
import com.variamos.gui.perspeditor.PerspEditorGraph;
import com.variamos.gui.perspeditor.PerspEditorMenuBar;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclProgram;
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
	/**
	 * 
	 */
	private int perspective = 1;
	private Cursor waitCursor, defaultCursor;
	private boolean showPerspectiveButton = false;
	private boolean showSimulationCustomizationBox = false;
	private String variamosVersionNumber = "1.0.1.12";
	private String variamosVersionName = "1.0 Beta 12";
	private String variamosBuild = "20150828 1200";
	private String downloadId = "366";

	public int getPerspective() {
		return perspective;
	}

	private List<VariamosGraphEditor> graphEditors;
	private List<PerspEditorMenuBar> editorsMenu;

	public MainFrame(String arg0) {
		graphEditors = new ArrayList<VariamosGraphEditor>();
		editorsMenu = new ArrayList<PerspEditorMenuBar>();
		Map<String, SemanticExpressionType> metaExpressionTypes = createMetaExpressionTypes();
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
				perspTitle = "Semantic - VariaMos " + variamosVersionNumber;
				System.out.println("Creating Semantic Perspective...");
				break;

			case 1:// modeling
				abstractModel = new RefasModel(PerspectiveType.modeling,
						metaExpressionTypes, syntaxRefas, semanticRefas);

				bgColor = new Color(236, 238, 255);
				perspTitle = "Req. Model - VariaMos " + variamosVersionNumber;
				System.out
						.println("Creating Rerquirements Model Perspective...");
				this.setTitle("New Diagram - "+perspTitle);
				break;

			case 2:// syntax
				abstractModel = syntaxRefas;

				bgColor = new Color(255, 255, 245);
				perspTitle = "Syntax - VariaMos " + variamosVersionNumber;
				System.out.println("Creating Syntax Model Perspective...");
				break;

			case 3:// simulation
				abstractModel = new RefasModel(PerspectiveType.simulation,
						metaExpressionTypes, syntaxRefas, semanticRefas);
				bgColor = new Color(236, 252, 255);
				perspTitle = "Config/Simul - VariaMos " + variamosVersionNumber;
				System.out
						.println("Creating Configuration and Simulation Perspective...");
				break;

			}

			refasGraph = new PerspEditorGraph(i + 1, abstractModel);

			VariamosGraphEditor editor = new VariamosGraphEditor(this,
					perspTitle,
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
		try {
			if (arg0 == null || !arg0.equals("nosolver"))
				verifySolver();
			if (arg0 == null || !arg0.equals("noupdate")) {
				this.checkUpdates(false);
			}
		} catch (UnsatisfiedLinkError e) {
			e.printStackTrace();
			JOptionPane
					.showMessageDialog(
							this,
							"Solver not properly configured, visit http://variamos.com and follow the steps",
							"Solver Error", JOptionPane.INFORMATION_MESSAGE,
							null);
		}
	}

	private void verifySolver() {
		HlclFactory f = new HlclFactory();
		HlclProgram model = new HlclProgram();
		model.add(f.equals(f.number(1), f.number(1)));
		IntDefectsVerifier verifier = new DefectsVerifier(model,
				SolverEditorType.SWI_PROLOG);
		verifier.isVoid();
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
		out.put("LessOrEquals", new SemanticExpressionType("LessOrEquals",
				"#<=", "#<=", "lessOrEqualsThan",
				SemanticExpressionType.NUMEXP, SemanticExpressionType.NUMEXP,
				SemanticExpressionType.BOOLEXP, false, false));
		/*
		 * out.put("LiteralBool", new SemanticExpressionType("LiteralBool", "",
		 * "", "literalBooleanExpression", SemanticExpressionType.LIT,
		 * SemanticExpressionType.NONE, SemanticExpressionType.BOOLEXP, true,
		 * false));
		 */
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
		// graphEditors.get(perspective - 1).setVisibleModel(0, -1);
		graphEditors.get(perspective - 1).setDefaultButton();
		graphEditors.get(perspective - 1).updateView();
		if (perspective != 4)
			graphEditors.get(3).hideDashBoard();
		this.revalidate();
		this.repaint();
	}
	
	public VariamosGraphEditor getEditor(int perspective)
	{
		return graphEditors.get(perspective - 1);
	}

	public void setAdvancedPerspective(boolean advancedPerspective) {
		showPerspectiveButton = advancedPerspective;
		int i = 0;
		for (VariamosGraphEditor e : graphEditors) {
			e.installToolBar(this, i++);
		}
	}

	public boolean isAdvancedPerspective() {
		return showPerspectiveButton;
	}

	public void setShowSimulationCustomizationBox(boolean showSimulationCustomizationBox) {
		this.showSimulationCustomizationBox = showSimulationCustomizationBox;
	}

	public boolean isShowSimulationCustomizationBox() {
		return showSimulationCustomizationBox;
	}
	
	public String getVariamosVersionNumber() {
		return variamosVersionNumber;
	}

	public String getVariamosVersionName() {
		return variamosVersionName;
	}

	public String getVariamosBuild() {
		return variamosBuild;
	}

	public void checkUpdates(boolean b) {
		InputStream input;
		try {
			input = new URL("http://variamos.com/home/Variamos.txt")
					.openStream();

			java.util.Scanner s = new java.util.Scanner(input)
					.useDelimiter(":");
			String newVersion = s.hasNext() ? s.next() : null;
			if (newVersion != null && !variamosVersionNumber.equals(newVersion))
				JOptionPane.showMessageDialog(this, "Your current version is "
						+ variamosVersionNumber + ". The latest version is: "
						+ newVersion + ". Please visit variamos.com.",
						"New VariaMos Version available",
						JOptionPane.INFORMATION_MESSAGE, null);
			else if (b)
				JOptionPane
						.showMessageDialog(this,
								"Your current version of VariaMos "
										+ variamosVersionNumber
										+ "  is up to date.", "Update Message",
								JOptionPane.INFORMATION_MESSAGE, null);
			input = new URL("http://variamos.com/home/?wpdmdl=" + downloadId)
					.openStream();
			s.close();
		} catch (java.net.UnknownHostException e) {
			System.out.println("Could not connect to Variamos.com.");
		} catch (MalformedURLException e) {
			System.out.println("Error on updates verification.");
		} catch (IOException e) {
			System.out.println("Error on updates verification.");
		}
	}
}
