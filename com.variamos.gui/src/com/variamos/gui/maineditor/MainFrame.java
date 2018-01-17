package com.variamos.gui.maineditor;

import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mxgraph.util.mxResources;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.model.OpersExprType;
import com.variamos.dynsup.types.PerspectiveType;
import com.variamos.gui.core.io.ConsoleTextArea;
import com.variamos.gui.core.maineditor.models.ParadigmTypeEnum;
import com.variamos.gui.core.viewcontrollers.VariamosGUIPerspectiveEditorActionsController;
import com.variamos.gui.perspeditor.PerspEditorFunctions;
import com.variamos.gui.perspeditor.PerspEditorGraph;
import com.variamos.gui.perspeditor.PerspEditorMenuBar;
import com.variamos.gui.util.ResourcesPathsUtil;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.reasoning.defectAnalyzer.core.DefectsVerifier;
import com.variamos.reasoning.defectAnalyzer.core.IntDefectsVerifier;

/**
 * Crea las distintas partes del editor 
 * 
 * Una instancia del variamosgraph editor por cada perspectiva
 * Carga la ventana principal
 * Controlador principal de la interfaz grafica. 
 * @author lufe0
 *
 */
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
	private static String variamosVersionNumber = "1.0.1.20";
	private String variamosVersionName = "1.0 Beta 20";
	private String variamosBuild = "20180109-1300";
	private String downloadId = "566";
	private static boolean solverError = false;
	private static String filesUrl = "";

	private List<VariamosGraphEditor> graphEditors;
	private List<PerspEditorMenuBar> editorsMenu;

	public MainFrame(String[] args) {
		
		try {
			createResources();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		graphEditors = new ArrayList<VariamosGraphEditor>();
		editorsMenu = new ArrayList<PerspEditorMenuBar>();
				
		Map<String, OpersExprType> metaExpressionTypes = createMetaExpressionTypes();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1166, 768);

		System.out
				.print("Loading Syntax, Semantic and Operations Infrastructure...");

		InstanceModel InfraBasicSyntax = new InstanceModel(
				PerspectiveType.INFRASTRUCTUREBASICSYNTAX, metaExpressionTypes);
		InstanceModel syntaxInfrastructure = new InstanceModel(
				PerspectiveType.SYNTAXINFRASTRUCTURE, metaExpressionTypes,
				InfraBasicSyntax, null);
		InstanceModel operationsInfrastructure = new InstanceModel(
				PerspectiveType.OPERATIONSINFRASTRUCTURE, metaExpressionTypes,
				InfraBasicSyntax, null);
		InstanceModel semanticSuperstructure = null;
		InstanceModel syntaxSuperstructure = null;
		InstanceModel abstractModel = null;
		PerspEditorGraph refasGraph = null;
		Color bgColor = null;
		VariamosGraphEditor modelEditor = null;
		String perspTitle = "";
		System.out.println(" done");
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0: // operations 1
				System.out
						.print("Loading Semantic and Operations Meta-Models Perspective...");
				abstractModel = new InstanceModel(metaExpressionTypes,
						operationsInfrastructure);
				semanticSuperstructure = abstractModel;
				syntaxSuperstructure = new InstanceModel(
						PerspectiveType.SYNTAXSUPERSTRUCTURE,
						metaExpressionTypes, syntaxInfrastructure,
						semanticSuperstructure);
				bgColor = new Color(255, 244, 255);
				perspTitle = "Semantic & Operations - VariaMos "
						+ variamosVersionNumber + "b" + variamosBuild;
				break;

			case 1:// modeling 2
				System.out.print("Loading Modeling Perspective...");
				abstractModel = new InstanceModel(PerspectiveType.MODELING,
						metaExpressionTypes, syntaxSuperstructure,
						semanticSuperstructure);

				bgColor = new Color(240, 244, 255);
				perspTitle = "Req. Model - VariaMos " + variamosVersionNumber
						+ "b" + variamosBuild;
				this.setTitle("New Diagram - " + perspTitle);
				break;

			case 2:// syntax 3
				System.out.print("Loading Syntax Meta-Model Perspective...");
				abstractModel = syntaxSuperstructure;
				// TO View SyntaxMM in Syntax Perspective
				// abstractModel = syntaxInfrastructure;

				// TO View OperMM in Syntax Perspective
				// abstractModel = new InstanceModel(
				// PerspectiveType.OPERATIONSINFRASTRUCTURE,
				// metaExpressionTypes, InfraBasicSyntax, null);

				// TO View BasicMMM in Syntax Perspective DO NOT REMOVE
				// abstractModel = new InstanceModel(
				// PerspectiveType.INFRASTRUCTUREBASICSYNTAX,
				// metaExpressionTypes, InfraBasicSyntax, null);

				bgColor = new Color(255, 255, 245);
				perspTitle = "Syntax - VariaMos " + variamosVersionNumber + "b"
						+ variamosBuild;
				break;

			case 3:// simulation 4
				System.out
						.print("Loading Configuration and Simulation Perspective...");
				abstractModel = new InstanceModel(
						PerspectiveType.CONFIG_SIMULATION, metaExpressionTypes,
						syntaxSuperstructure, semanticSuperstructure);
				bgColor = new Color(236, 252, 255);
				perspTitle = "Config/Simul - VariaMos " + variamosVersionNumber
						+ "b" + variamosBuild;
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
			System.out.println(" done");
			editor.updateView();
		}
		
		System.out.println("GUI load completed");
		this.add(graphEditors.get(2));
		this.setJMenuBar(editorsMenu.get(2));
		VariamosGUIPerspectiveEditorActionsController.changeVariamosParadigmView(graphEditors);
		this.setVisible(true);
		if (args == null || args.length == 0 || !args[0].equals("noupdate")) {
			this.checkUpdates(false);
		}
		if (args != null
				&& (args.length > 0 && args[0].equals("debug") || (args.length == 2 && args[1]
						.equals("debug")))) {
			ConsoleTextArea.setDebug(true);
		}
		try {
			if (args == null || args.length == 0 || !args[0].equals("nosolver"))
				verifySolver();
		} catch (UnsatisfiedLinkError e) {
			ConsoleTextArea.addText(e.getStackTrace());
			JOptionPane
					.showMessageDialog(
							this,
							"The SWIProlog Solver is not correctly configured for VariaMos. \n"
									+ "Double check you have the version 7.2.3 and the required variables \n"
									+ "defined. Please visit http://variamos.com and follow the steps",
							"Solver Configuration Error: Operations will not work",
							JOptionPane.ERROR_MESSAGE, null);
			solverError = true;
		}
	}

	private void verifySolver() {
		HlclFactory f = new HlclFactory();
		HlclProgram model = new HlclProgram();
		model.add(f.equals(f.number(1), f.number(1)));
		IntDefectsVerifier verifier = new DefectsVerifier(model);
		verifier.isVoid();
	}

	private Map<String, OpersExprType> createMetaExpressionTypes() {
		Map<String, OpersExprType> out = new HashMap<String, OpersExprType>();
		out.put("And", new OpersExprType("And", "#/\"", "#/\"", "and",
				OpersExprType.BOOLEXP, OpersExprType.BOOLEXP,
				OpersExprType.BOOLEXP, false, false));
		out.put("Assign", new OpersExprType("Assign", "=", "=", "assign",
				OpersExprType.IDEN, OpersExprType.EXP, OpersExprType.NONE,
				false, false));
		out.put("Is", new OpersExprType("Is", "is", "is", "is",
				OpersExprType.IDEN, OpersExprType.EXP, OpersExprType.NONE,
				false, false));
		out.put("Subtraction", new OpersExprType("Subtraction", "-", "-",
				"diff", OpersExprType.NUMEXP, OpersExprType.NUMEXP,
				OpersExprType.NUMEXP, false, false));
		out.put("DoubleImplies", new OpersExprType("DoubleImplies", "#<==>",
				"#<==>", "doubleImplies", OpersExprType.BOOLEXP,
				OpersExprType.BOOLEXP, OpersExprType.BOOLEXP, false, false));
		out.put("Equals", new OpersExprType("Equals", "#=", "#=", "equals",
				OpersExprType.EXP, OpersExprType.EXP, OpersExprType.BOOLEXP,
				false, false));
		out.put("Greater", new OpersExprType("Greater", "#>", "#>",
				"greaterThan", OpersExprType.NUMEXP, OpersExprType.NUMEXP,
				OpersExprType.BOOLEXP, false, false));
		out.put("GreaterOrEq", new OpersExprType("GreaterOrEq", "#>=", "#>=",
				"greaterOrEqualsThan", OpersExprType.NUMEXP,
				OpersExprType.NUMEXP, OpersExprType.BOOLEXP, false, false));
		out.put("Implies", new OpersExprType("Implies", "#==>", "#==>",
				"implies", OpersExprType.BOOLEXP, OpersExprType.BOOLEXP,
				OpersExprType.BOOLEXP, false, false));
		out.put("Less", new OpersExprType("Less", "#<", "#<", "lessThan",
				OpersExprType.NUMEXP, OpersExprType.NUMEXP,
				OpersExprType.BOOLEXP, false, false));
		out.put("LessOrEquals", new OpersExprType("LessOrEquals", "#<=", "#<=",
				"lessOrEqualsThan", OpersExprType.NUMEXP, OpersExprType.NUMEXP,
				OpersExprType.BOOLEXP, false, false));
		/*
		 * out.put("LiteralBool", new SemanticExpressionType("LiteralBool", "",
		 * "", "literalBooleanExpression", SemanticExpressionType.LIT,
		 * SemanticExpressionType.NONE, SemanticExpressionType.BOOLEXP, true,
		 * false));
		 */
		out.put("Negation", new OpersExprType("Negation", "-", "-", "not",
				OpersExprType.BOOLEXP, OpersExprType.NONE,
				OpersExprType.BOOLEXP, true, false));
		out.put("Number", new OpersExprType("Number", "", "", "number",
				OpersExprType.INTVAL, OpersExprType.NONE, OpersExprType.NUMEXP,
				true, false));
		out.put("NotEquals", new OpersExprType("NotEquals", "\\==", "\\==",
				"notEquals", OpersExprType.EXP, OpersExprType.EXP,
				OpersExprType.BOOLEXP, false, false));
		out.put("Or", new OpersExprType("Or", "#\"/", "#\"/", "or",
				OpersExprType.BOOLEXP, OpersExprType.BOOLEXP,
				OpersExprType.BOOLEXP, false, false));
		out.put("Product", new OpersExprType("Product", "*", "*", "prod",
				OpersExprType.NUMEXP, OpersExprType.NUMEXP,
				OpersExprType.NUMEXP, false, true));
		out.put("Sum", new OpersExprType("Sum", "+", "+", "sum",
				OpersExprType.NUMEXP, OpersExprType.NUMEXP,
				OpersExprType.NUMEXP, false, true));
		return out;
	}

	public void waitingCursor(boolean waitingCursor) {
		if (waitingCursor)
			this.setCursor(waitCursor);
		else
			this.setCursor(defaultCursor);

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

	public VariamosGraphEditor getEditor(int perspective) {
		return graphEditors.get(perspective - 1);
	}

	public void setAdvancedPerspective(boolean advancedPerspective) {
		showPerspectiveButton = advancedPerspective;
		int i = 0;
		for (VariamosGraphEditor e : graphEditors) {
			e.installToolBar(this, i++);
		}
	}

	
	public void checkUpdates(boolean b) {
		InputStream input;
		try {
			input = new URL("http://variamos.com/home/Variamos.txt")
					.openStream();

			@SuppressWarnings("resource")
			java.util.Scanner s = new java.util.Scanner(input)
					.useDelimiter(":");
			String newVersion = s.hasNext() ? s.next() : null;
			if (newVersion != null && !variamosVersionNumber.equals(newVersion))
				// && (!newVersion.equals("1.0.1.18"))
				// && !variamosVersionNumber
				// .equalsIgnoreCase("1.0.1.19")))
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
			// else if (variamosVersionNumber.equalsIgnoreCase("1.0.1.19"))
			// JOptionPane
			// .showMessageDialog(
			// this,
			// "VariaMos keeped the compatibility of models until Version Beta 18. \n"
			// +
			// " Nevertheless, models created in version Beta 18 and older are not\n"
			// +
			// " compatible with this unstable compilation of Beta 19. Also, models created in this\n"
			// +
			// " compilation may have small compatibility issues in the stable compilation of Beta 19. If you \n"
			// +
			// " already defined models, we suggest you to continue using version Beta 18.",
			// "Update Message",
			// JOptionPane.INFORMATION_MESSAGE, null);
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

	// based on
	// http://stackoverflow.com/questions/10308221/how-to-copy-file-inside-jar-to-outside-the-jar
	private String createResources() throws Exception {
		filesUrl = new File(MainFrame.class.getProtectionDomain()
				.getCodeSource().getLocation().toURI().getPath())
				.getParentFile().getPath().replace('\\', '/');

		File directory = new File(getFilesUrl());
		directory.mkdir();
		if (!directory.exists() || !directory.canRead()
				|| !directory.canWrite()) {
			System.out.println("VariaMos needs the folder " + getFilesUrl()
					+ " with writing privileges. Please change the"
					+ " privileges or manually create the folder "
					+ "with writing privileges and execute VariaMos againt");
			JOptionPane.showMessageDialog(this, "VariaMos needs the folder "
					+ getFilesUrl() + " with writing privileges. Please change"
					+ " the privileges or manually create the "
					+ "folder with writing privileges", "Fatal Error",
					JOptionPane.ERROR_MESSAGE, null);
		}
		InputStream stream = null;
		OutputStream resStreamOut = null;
		String[] resourceNames = {
				ResourcesPathsUtil.STYLES_PATH ,
				ResourcesPathsUtil.SHAPES_PATH };
		try {
			for (String resourceName : resourceNames) {
				stream = getClass().getClassLoader().getResourceAsStream(resourceName);
				if (stream == null) {
					throw new Exception("Cannot get resource \"" + resourceName
							+ "\" from Jar file.");
				}
				String fileName = resourceName.substring(resourceName
						.lastIndexOf("/") + 1);

				// System.out.println(fileName);
				File file = new File(getFilesUrl() + fileName);

				int readBytes;
				byte[] buffer = new byte[4096];
				if (!file.exists()) {
					resStreamOut = new FileOutputStream(getFilesUrl()
							+ fileName);
					while ((readBytes = stream.read(buffer)) > 0) {
						resStreamOut.write(buffer, 0, readBytes);
					}
					resStreamOut.close();
				}
				stream.close();
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return getFilesUrl();
	}
	
	public static String getFilesUrl() {
		return filesUrl + "/VariaMos-" + variamosVersionNumber + "-Resources/";
	}

	public int getPerspective() {
		return perspective;
	}

	public static boolean getSolverError() {
		return solverError;
	}
	
	public boolean isAdvancedPerspective() {
		return showPerspectiveButton;
	}

	public void setShowSimulationCustomizationBox(
			boolean showSimulationCustomizationBox) {
		this.showSimulationCustomizationBox = showSimulationCustomizationBox;
	}

	public boolean isShowSimulationCustomizationBox() {
		return showSimulationCustomizationBox;
	}

	public static String getVariamosVersionNumber() {
		return variamosVersionNumber;
	}

	public String getVariamosVersionName() {
		return variamosVersionName;
	}

	public String getVariamosBuild() {
		return variamosBuild;
	}
	
	public void setPerspective(int perspective) {
		this.perspective = perspective;
	}
}
