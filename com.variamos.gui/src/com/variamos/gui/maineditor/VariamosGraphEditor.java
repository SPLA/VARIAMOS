package com.variamos.gui.maineditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ProgressMonitor;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.cfm.common.AbstractModel;
import com.cfm.productline.AbstractElement;
import com.cfm.productline.Editable;
import com.cfm.productline.ProductLine;
import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.shape.mxStencilShape;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphSelectionModel;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstCell;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstOverTwoRel;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.interfaces.IntInstAttribute;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.staticexpr.ElementExpressionSet;
import com.variamos.dynsup.translation.ModelExpr2HLCL;
import com.variamos.dynsup.translation.SolverOpersTask;
import com.variamos.dynsup.translation.SolverTasks;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.DomainRegister;
import com.variamos.dynsup.types.PerspectiveType;
import com.variamos.gui.perspeditor.PerspEditorFunctions;
import com.variamos.gui.perspeditor.PerspEditorGraph;
import com.variamos.gui.perspeditor.PerspEditorToolBar;
import com.variamos.gui.perspeditor.SpringUtilities;
import com.variamos.gui.perspeditor.actions.FileTasks;
import com.variamos.gui.perspeditor.actions.SharedActions;
import com.variamos.gui.perspeditor.panels.ElementDesignPanel;
import com.variamos.gui.perspeditor.panels.ElementsOperationAssociationDialog;
import com.variamos.gui.perspeditor.panels.ExternalContextDialog;
import com.variamos.gui.perspeditor.panels.StaticExpressionsPanel;
import com.variamos.gui.perspeditor.panels.VariamosDashBoardFrame;
import com.variamos.gui.perspeditor.widgets.MClassWidget;
import com.variamos.gui.perspeditor.widgets.MEnumerationWidget;
import com.variamos.gui.perspeditor.widgets.RefasWidgetFactory;
import com.variamos.gui.perspeditor.widgets.WidgetR;
import com.variamos.gui.pl.editor.ConfigurationPropertiesTab;
import com.variamos.gui.pl.editor.ConfiguratorPanel;
import com.variamos.gui.pl.editor.ProductLineGraph;
import com.variamos.gui.pl.editor.widgets.WidgetPL;
import com.variamos.hlcl.HlclProgram;
import com.variamos.io.ConsoleTextArea;
import com.variamos.io.SXFMReader;
import com.variamos.solver.Configuration;

import fm.FeatureModelException;

/**
 * A class to represented the editor for each perspective. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.0
 * @since 2014 *
 */

@SuppressWarnings("serial")
public class VariamosGraphEditor extends BasicGraphEditor implements
		PropertyChangeListener {

	static {
		try {
			mxResources.add("com/variamos/gui/maineditor/resources/editor");
		} catch (Exception e) {
			// ignore
		}
	}
	private int modelViewIndex = 0;
	private int modelSubViewIndex = 0;
	private List<String> validElements = null;

	protected DomainRegister domasinRegister = new DomainRegister();
	protected GraphTree productLineIndex;
	protected ConfiguratorPanel configurator;
	protected ConfigurationPropertiesTab configuratorProperties;
	private ModelInstance refasModel;
	private ProgressMonitor progressMonitor;
	private SolverTasks task;
	private SolverOpersTask semTask;
	protected StaticExpressionsPanel expressions;
	protected JTextArea consoleTextArea;
	private ElementDesignPanel elementDesignPanel;
	protected JPanel elementConfigPropPanel;
	protected JPanel elementExpressionPanel;
	protected JPanel elementSimPropPanel;
	protected JPanel perspectiveToolBarPanel;
	// Bottom tabs
	protected JTabbedPane extensionTabs;

	protected int mode = 0;
	private int tabIndex = 0, lastTabIndex = 0;
	private ModelExpr2HLCL refas2hlcl;
	private VariamosGraphEditor modelEditor;
	private InstCell lastEditableElement;
	private boolean recursiveCall = false;
	private boolean updateExpressions = true;
	private String editableElementType = null;

	private String lastSolverInvocations = "";
	private Configuration lastConfiguration;

	private List<String> defects = new ArrayList<String>();

	private HlclProgram configHlclProgram;

	private boolean invalidConfigHlclProgram = true;

	private boolean updateTabs = false;

	private ExternalContextDialog ecd = new ExternalContextDialog(this);

	private ElementsOperationAssociationDialog eoad;

	VariamosDashBoardFrame dashBoardFrame = new VariamosDashBoardFrame(
			getEditedModel());

	private FileTasks fileTask;

	public void updateDashBoard(boolean showDashboard, boolean updateConcepts,
			boolean updated) {
		dashBoardFrame.updateDashBoard(refasModel, showDashboard,
				updateConcepts, updated);

	}

	public void showNames(boolean showNames) {
		dashBoardFrame.setShowNames(showNames);
	}

	public void showDashBoard(boolean visible) {
		dashBoardFrame.showDashBoard(visible);
	}

	public void hideDashBoard() {
		dashBoardFrame.hideDashBoard();
	}

	public ModelExpr2HLCL getRefas2hlcl() {
		return refas2hlcl;
	}

	public VariamosGraphEditor getEditor() {
		return this;
	}

	public VariamosGraphEditor(MainFrame frame, String perspTitle,
			VariamosGraphComponent component, int perspective,
			AbstractModel abstractModel) {
		super(frame, perspTitle, component, perspective);

		// Default defects validation
		defects.add("Root");
		defects.add("Parent");
		defects.add("FalseOpt");
		defects.add("Dead");

		refasModel = (ModelInstance) abstractModel;
		refas2hlcl = new ModelExpr2HLCL(refasModel);
		configurator.setRefas2hlcl(refas2hlcl);

		registerEvents();
		// List<InstView> instViews =
		// refasModel.getSyntaxRefas().getInstViews();

		PerspEditorGraph refasGraph = ((PerspEditorGraph) graphComponent
				.getGraph());
		refasGraph.setValidation(false);
		refasGraph.setModelInstance(abstractModel);
		refasGraph.setValidation(true);
		graphEditorFunctions = new PerspEditorFunctions(this);
		// RefasGraph refasGraph = (RefasGraph) component.getGraph();

		this.graphLayout("organicLayout", false);
		this.getGraphComponent().zoomAndCenter();
		defineViewTabs();
	}

	public void defineViewTabs() {
		while (modelsTabPane.getTabCount() > 0)
			modelsTabPane.removeTabAt(0);
		PerspEditorGraph perspEditorGraph = ((PerspEditorGraph) graphComponent
				.getGraph());
		perspEditorGraph.loadStyles();
		perspEditorGraph.loadStencil();
		List<InstElement> instViews = null;
		if (refasModel.getSyntaxModel() != null)
			instViews = refasModel.getSyntaxModel().getVariabilityVertex(
					"SMView");
		if (instViews != null)
			if (instViews.size() == 0) {
				center.setDividerLocation(0);
				upperPart.setDividerLocation(0);
				graphAndRight.setDividerLocation(700);
				setVisibleModel(-1, -1);
				updateView();
			} else {
				int i = 0;
				mxCell root = (mxCell) perspEditorGraph.getModel().getRoot();
				mxCell parent = (mxCell) root.getChildAt(0);
				for (InstElement instElement : instViews) {

					if (instElement.getSupInstEleId().equals("SMView")) {
						if (parent.getChildCount() <= i
								&& parent.getId().equals("1")) {
							mxCell child = new mxCell(new InstCell(null, null,
									false));
							child.setId("mv" + i);
							perspEditorGraph.addCell(child, parent);
						}
					}
					i++;
					// TODO: Modify to work with relations. No more instView
					// concepts
					// if (instElement instanceof InstView) {
					// InstView instView = (InstView) instElement;
					// if (instView.getChildViews().size() > 0) {
					// modelsTabPane.add(instView.getEdSyntaxEle()
					// .getName(), tabPane);
					//
					// // mxCell child = new mxCell(new InstCell(null,
					// // false));
					// // child.setId("mv" + i);
					// // refasGraph.addCell(child, parent);
					// // Add the parent as first child
					// for (int j = 0; j < instView.getChildViews().size(); j++)
					// {
					// // mxCell child2 = new mxCell(new InstCell(null,
					// // false));
					// // child2.setId("mv" + i + "-" + j);
					// // refasGraph.addCell(child2,
					// // parent);
					// InstView instChildView = instView
					// .getChildViews().get(j);
					// JButton a = new JButton(instChildView
					// .getEdSyntaxEle().getName());
					// tabPane.add(a);
					// a.addActionListener(new ModelButtonAction());
					// }
					// // TODO include recursive calls if more view levels
					// // are
					// // required
					// }
					// }
					// else {
					modelsTabPane.add(instElement.getEdSyntaxEle().getName(),
							null);
					modelsTabPane.setMaximumSize(new Dimension(10, 22));
					modelsTabPane.setPreferredSize(new Dimension(10, 22));
					modelsTabPane.setMinimumSize(new Dimension(10, 22));

					// }
					final EditorPalette palette = new EditorPalette();
					palette.setName("ee");
					final JScrollPane scrollPane = new JScrollPane(palette);

					scrollPane
							.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					scrollPane
							.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					modelsTabPane.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							// System.out.println("Tab: "
							// + modelsTabPane.getTitleAt(modelsTabPane
							// .getSelectedIndex()));

							// TODO change to RefasModel
							List<InstElement> finalInstViews = refasModel
									.getSyntaxModel().getVariabilityVertex(
											"SMView");
							VariamosGraphEditor editor = getEditor();
							((MainFrame) editor.getFrame()).waitingCursor(true);
							int modelInd = getModelViewIndex();
							for (int i = 0; i < finalInstViews.size(); i++) {
								if (modelInd != i
										&& modelsTabPane.getSelectedIndex() != -1
										&& modelsTabPane.getTitleAt(
												modelsTabPane
														.getSelectedIndex())
												.equals(finalInstViews.get(i)
														.getEdSyntaxEle()
														.getName())) {
									/*
									 * if (finalInstViews.get(i) instanceof
									 * InstView) { if (((InstView)
									 * finalInstViews.get(i))
									 * .getChildViews().size() > 0) { // if
									 * (false) //TODO validate the // name // of
									 * // the // button with the tab, if true,
									 * // identify // the // subview //
									 * editor.setVisibleModel(i ,0); // else
									 * editor.setVisibleModel(i, 0);
									 * editor.updateView();
									 * center.setDividerLocation(60);
									 * center.setMaximumSize(center .getSize());
									 * center.setMinimumSize(center .getSize());
									 * center.setResizeWeight(0); } } else {
									 */
									editor.setVisibleModel(i, -1);
									editor.updateView();
									center.setDividerLocation(25);
									center.setMaximumSize(center.getSize());
									// center.setMinimumSize(center.getSize());
									center.setPreferredSize(center.getSize());
									center.setResizeWeight(0);
									// }
								}
							}
							((MainFrame) editor.getFrame())
									.waitingCursor(false);
						}
					});
				}
				center.setDividerLocation(25);
				upperPart.setDividerLocation(0);
				if (refasModel.getPerspectiveType().equals(
						PerspectiveType.CONFIG_SIMULATION))
					graphAndRight.setDividerLocation(1100);
				else
					graphAndRight.setDividerLocation(700);

				setVisibleModel(0, -1);
				updateView();
				this.setModified(false);
			}
	}

	public AbstractGraphEditorFunctions getGraphEditorFunctions() {
		return graphEditorFunctions;
	}

	public void setGraphEditorFunctions(AbstractGraphEditorFunctions gef) {
		graphEditorFunctions = gef;
	}

	public int getModelViewIndex() {
		return modelViewIndex;
	}

	public void setAdvancedPerspective(boolean advancedPerspective) {
		getMainFrame().setAdvancedPerspective(advancedPerspective);
		this.installToolBar(getMainFrame(), perspective);
	}

	public void setShowSimulationCustomizationBox(
			boolean showSimulationCustomizationBox) {
		getMainFrame().setShowSimulationCustomizationBox(
				showSimulationCustomizationBox);

	}

	public boolean isShowSimulationCustomizationBox() {
		return getMainFrame().isShowSimulationCustomizationBox();
	}

	public int getModelSubViewIndex() {
		return modelSubViewIndex;
	}

	public void setVisibleModel(int modelIndex, int modelSubIndex) {
		// System.out.println(modelIndex + " " + modelSubIndex);
		modelViewIndex = modelIndex;
		modelSubViewIndex = modelSubIndex;
		PerspEditorGraph refasGraph = ((PerspEditorGraph) getGraphComponent()
				.getGraph());
		if (perspective == 4)
			validElements = null;
		else
			validElements = refasGraph.getValidElements(modelViewIndex,
					modelSubViewIndex);
		refasGraph.setModelViewIndex(modelIndex);
		refasGraph.setModelViewSubIndex(modelSubIndex);
		SharedActions.setVisibleViews(refasGraph.getModel(), false,
				modelViewIndex, modelSubViewIndex);
		refasGraph.refresh();

		elementDesignPanel.repaint();
		// elementDesPropPanel.repaint();
		elementConfigPropPanel.repaint();
		elementExpressionPanel.repaint();
		elementSimPropPanel.repaint();
	}

	public void updateEditor() {
		validElements = ((PerspEditorGraph) getGraphComponent().getGraph())
				.getValidElements(modelViewIndex, modelSubViewIndex);
		dashBoardFrame = new VariamosDashBoardFrame(getEditedModel());
		graphEditorFunctions.updateEditor(this.validElements,
				getGraphComponent(), modelViewIndex);
	}

	@Override
	public void updateTitle() {
		if (perspective == 4 && modelEditor != null)
			currentFile = modelEditor.getCurrentFile();
		super.updateTitle();

	}

	// When tab change or save/load file (resetEditor)
	public void updateView() {
		validElements = ((PerspEditorGraph) getGraphComponent().getGraph())
				.getValidElements(modelViewIndex, modelSubViewIndex);
		graphEditorFunctions.updateView(this.validElements,
				getGraphComponent(), modelViewIndex);
		this.setInvalidConfigHlclProgram(true);
		this.updateTitle();
	}

	/**
	 * @param appTitle
	 * @param component
	 *            New constructor to load directly files and perspectives
	 * @throws FeatureModelException
	 */
	@Deprecated
	public static VariamosGraphEditor loader(MainFrame frame, String appTitle,
			String file, String perspective) throws FeatureModelException {
		AbstractModel abstractModel = null;

		int persp = 0;
		if (perspective.equals("ProductLine")) {
			persp = 0;
			if (file != null) {
				SXFMReader reader = new SXFMReader();
				abstractModel = reader.readFile(file);
			} else

				abstractModel = new ProductLine();
			ProductLineGraph plGraph = new ProductLineGraph();
			// plGraph.add
			VariamosGraphEditor vge = new VariamosGraphEditor(frame,
					"Configurator - VariaMos", new VariamosGraphComponent(
							plGraph, Color.WHITE), persp, abstractModel);
			return vge;
		} else if (perspective.equals("modeling")) {

			System.out.println("Initializing modeling perspective...");
			persp = 2;
			PerspEditorGraph refasGraph = null;
			if (file != null) {
				SXFMReader reader = new SXFMReader();
				abstractModel = reader.readRefasFile(file, new ModelInstance(
						PerspectiveType.MODELING, null));
				refasGraph = new PerspEditorGraph(persp);
			} else {
				{
					abstractModel = new ModelInstance(PerspectiveType.MODELING,
							null);
					refasGraph = new PerspEditorGraph(persp);

				}

				// ProductLineGraph plGraph2 = new ProductLineGraph();
				VariamosGraphEditor vge2 = new VariamosGraphEditor(frame,
						"Configurator - VariaMos", new VariamosGraphComponent(
								refasGraph, Color.WHITE), persp, abstractModel);
				vge2.createFrame().setVisible(true);
				vge2.setVisibleModel(0, -1);
				vge2.setDefaultButton();
				vge2.setPerspective(2);
				vge2.setGraphEditorFunctions(new PerspEditorFunctions(vge2));
				vge2.updateEditor();

				System.out.println("System Model perspective initialized.");
				return vge2;
			}
		} else if (perspective.equals("metamodeling")) {

			System.out.println("Initializing Syntax Meta-Model perspective...");
			// todo: change for metamodeling
			persp = 3;
			PerspEditorGraph refasGraph = null;
			if (file != null) {
				SXFMReader reader = new SXFMReader();
				abstractModel = reader.readRefasFile(file, new ModelInstance(
						PerspectiveType.MODELING, null));
				refasGraph = new PerspEditorGraph(persp);
			} else {
				{
					abstractModel = new ModelInstance(PerspectiveType.MODELING,
							null);
					refasGraph = new PerspEditorGraph(persp);

				}

				// ProductLineGraph plGraph2 = new ProductLineGraph();
				VariamosGraphEditor vge2 = new VariamosGraphEditor(frame,
						"Configurator - VariaMos", new VariamosGraphComponent(
								refasGraph, Color.WHITE), persp, abstractModel);
				vge2.createFrame().setVisible(true);
				vge2.setVisibleModel(0, -1);
				vge2.setPerspective(3);
				vge2.setGraphEditorFunctions(new PerspEditorFunctions(vge2));
				vge2.updateEditor();
				mxCell root = new mxCell();
				mxCell parent = new mxCell();
				root.insert(parent);
				InstAttribute att = new InstAttribute();
				att.setInstAttributeAttribute("versionNumber",
						MainFrame.getVariamosVersionNumber());
				parent.setValue(att);
				refasGraph.getModel().setRoot(root);
				System.out
						.println("Syntax Meta-Model perspective initialized.");
				return vge2;
			}
		}
		return null;
	}

	@Deprecated
	public void editModel(ModelInstance pl) {
		// productLineIndex.reset();
		AbstractGraph abstractGraph = null;
		// todo: review other perspectives
		if (perspective == 0)
			abstractGraph = new ProductLineGraph();
		if (perspective == 2 || perspective == 1 || perspective == 3
				|| perspective == 4)
			abstractGraph = new PerspEditorGraph(perspective);
		// abstractGraph = (AbstractGraph) getGraphComponent()
		// .getGraph();
		((VariamosGraphComponent) graphComponent).updateGraph(abstractGraph);
		registerEvents();

		abstractGraph.setModelInstance(pl);
	}

	public void resetView() {
		updateEditor();
		mxGraph graph = getGraphComponent().getGraph();
		// Check modified flag and display save dialog
		mxCell root = new mxCell();
		mxCell parent = new mxCell();
		root.insert(parent);
		InstAttribute att = new InstAttribute();
		att.setInstAttributeAttribute("versionNumber",
				MainFrame.getVariamosVersionNumber());
		parent.setValue(att);
		graph.getModel().setRoot(root);
		refasModel.clear();
		// if (perspective == 2) {
		setGraphEditorFunctions(new PerspEditorFunctions(this));
		((PerspEditorGraph) graph).defineInitialGraph();
		System.out.println("");
		// }
		if (perspective % 2 != 0) {
			this.graphLayout("organicLayout", true);
			this.getGraphComponent().zoomAndCenter();
		}
		setCurrentFile(null);
		getGraphComponent().zoomAndCenter();

		setModified(false);
	}

	private void registerEvents() {
		mxGraphSelectionModel selModel = getGraphComponent().getGraph()
				.getSelectionModel();
		selModel.addListener(mxEvent.CHANGE, new mxIEventListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void invoke(Object sender, mxEventObject evt) {
				// Collection<mxCell> added = (Collection<mxCell>)
				// evt.getProperty("added");
				// System.out.println("Added: " + added);

				Collection<mxCell> removed = (Collection<mxCell>) evt
						.getProperty("removed");
				// System.out.println("Removed: " + removed);

				// editProperties(null);
				editPropertiesRefas(null);

				if (removed == null)
					return;

				mxCell cell = null;
				if (removed.size() == 1)
					cell = removed.iterator().next();

				// Multiselection case
				if (cell == null) {

					return;
				}
				// if (cell.getValue() instanceof Editable) {
				// Editable elm = (Editable) cell.getValue();
				// editProperties(elm);
				// // getGraphComponent().scrollCellToVisible(cell, true);
				// }
				InstCell value = (InstCell) cell.getValue();
				if (value != null)
					// EditableElement elm = (EditableElement) value;
					editPropertiesRefas(value);
				// getGraphComponent().scrollCellToVisible(cell, true);
			}
		});
	}

	public static String loadShape(EditorPalette palette, File f)
			throws IOException {
		String nodeXml = mxUtils.readFile(f.getAbsolutePath());
		addStencilShape(palette, nodeXml, f.getParent() + File.separator);
		return nodeXml;
	}

	/**
	 * Loads and registers the shape as a new shape in mxGraphics2DCanvas and
	 * adds a new entry to use that shape in the specified palette
	 * 
	 * @param palette
	 *            The palette to add the shape to.
	 * @param nodeXml
	 *            The raw XML of the shape
	 * @param path
	 *            The path to the directory the shape exists in
	 * @return the string name of the shape
	 */
	public static String addStencilShape(EditorPalette palette, String nodeXml,
			String path) {

		// Some editors place a 3 byte BOM at the start of files
		// Ensure the first char is a "<"
		int lessthanIndex = nodeXml.indexOf("<");
		nodeXml = nodeXml.substring(lessthanIndex);
		mxStencilShape newShape = new mxStencilShape(nodeXml);
		String name = newShape.getName();
		ImageIcon icon = null;

		if (path != null) {
			String iconPath = path + newShape.getIconPath();
			icon = new ImageIcon(iconPath);
		}

		// Registers the shape in the canvas shape registry
		mxGraphics2DCanvas.putShape(name, newShape);

		if (palette != null && icon != null) {
			palette.addTemplate(name, icon, "shape=" + name, 80, 80, "");
		}

		return name;
	}

	@Override
	protected Component getLeftComponent() {
		productLineIndex = new GraphTree();
		productLineIndex.bind((AbstractGraph) getGraphComponent().getGraph());

		JSplitPane inner = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				productLineIndex, null);
		inner.setDividerLocation(250);
		inner.setResizeWeight(1);
		inner.setDividerSize(6);
		inner.setBorder(null);

		return inner;
	}

	@Override
	public Component getExtensionsTab() {
		if (extensionTabs != null)
			return extensionTabs;

		ConsoleTextArea.setTextArea(new JTextArea("Output"));
		// ConsoleTextArea.setTextEditable(false);

		// elementDesPropPanel = new JPanel();
		// elementDesPropPanel.setLayout(new SpringLayout());

		elementDesignPanel = new ElementDesignPanel();

		elementConfigPropPanel = new JPanel();
		elementConfigPropPanel.setLayout(new SpringLayout());

		elementExpressionPanel = new JPanel();
		// elementExpressionPanel.setLayout(new SpringLayout());

		consoleTextArea = new JTextArea("Element Expressions");
		// consoleTextArea.setEditable(false);
		// elementExpressionPanel.add(expressionsArea);

		elementSimPropPanel = new JPanel();
		elementSimPropPanel.setLayout(new SpringLayout());

		configurator = new ConfiguratorPanel();

		configuratorProperties = new ConfigurationPropertiesTab();

		expressions = new StaticExpressionsPanel();

		// if (getPerspective() == 2) {

		// }

		// Bottom panel : Properties, Messages and Configuration
		extensionTabs = new JTabbedPane(JTabbedPane.TOP,
				JTabbedPane.SCROLL_TAB_LAYOUT);
		extensionTabs.addTab(mxResources.get("elementExpressionTab"),
				new JScrollPane(expressions));
		extensionTabs.addTab(mxResources.get("messagesTab"), new JScrollPane(
				ConsoleTextArea.getTextArea()));
		extensionTabs.addTab(mxResources.get("configurationTab"),
				new JScrollPane(configurator));
		extensionTabs.setMinimumSize(new Dimension(30, 100));
		extensionTabs.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				((MainFrame) getFrame()).waitingCursor(true);
				if (((JTabbedPane) e.getSource()).getTabCount() > 3
						&& ((JTabbedPane) e.getSource()).getSelectedIndex() >= 0) {
					tabIndex = ((JTabbedPane) e.getSource()).getSelectedIndex();
					lastTabIndex = tabIndex;
					Component c = ((JTabbedPane) e.getSource())
							.getComponent(tabIndex);
					if (c != null) {
						c.revalidate();
						c.repaint();
					}
					String name = ((JTabbedPane) e.getSource())
							.getTitleAt(tabIndex);
					if (name.equals("Element Expressions (Visual)")
							&& editableElementType != null
							&& (perspective == 2 || perspective == 4)
							&& updateExpressions) {
						InstElement elm = lastEditableElement.getInstElement();
						if (elm instanceof InstConcept) {
							String iden = ((InstConcept) elm)
									.getTransSupportMetaElement()
									.getAutoIdentifier();
							// System.out.println(iden);
							if (iden.equals("CG")
									|| iden.equals("ContextVariable")
									|| iden.equals("GlobalVariable")
									|| iden.equals("GlobalVariable")
									|| iden.equals("Variable")
									|| iden.equals("ENUM"))
								editableElementType = "var";
							else
								editableElementType = "vertex";
						}
						if (elm instanceof InstPairwiseRel) {
							editableElementType = "edge";
						}
						if (elm instanceof InstOverTwoRel) {
							editableElementType = "groupdep";
						}
						ElementExpressionSet metaExpressionSet = refas2hlcl
								.getElementConstraintGroup(lastEditableElement
										.getInstElement().getIdentifier(),
										editableElementType,
										ModelExpr2HLCL.SIMUL_EXEC);

						expressions.configure(getEditedModel(),
								metaExpressionSet,
								lastEditableElement.getInstElement());
						updateExpressions = false;
					}
				}
				// System.out.println(tabIndex);
				((MainFrame) getFrame()).waitingCursor(false);
			}

		});
		updateVisibleProperties(null);
		return extensionTabs;
	}

	private void updateVisibleProperties(final InstCell elm) {

		if (elm == null)
			extensionTabs.removeAll();

		if (elm != null && this.lastEditableElement != elm) {
			extensionTabs.removeAll();
			// extensionTabs.addTab(mxResources.get("elementDisPropTab"),
			// new JScrollPane(elementDesPropPanel));
			extensionTabs.addTab(mxResources.get("elementDisPropTab"),
					new JScrollPane(elementDesignPanel));
			if (perspective == 4) {
				extensionTabs.addTab(mxResources.get("elementConfPropTab"),
						new JScrollPane(elementConfigPropPanel));
				// extensionTabs.addTab(mxResources.get("elementExpressionTab"),
				// new JScrollPane(elementExpressionPanel));

			}

			if (perspective == 2 || perspective == 4) {
				extensionTabs.addTab(mxResources.get("elementExpressionTab"),
						new JScrollPane(consoleTextArea));

				extensionTabs.addTab(mxResources.get("elementSimPropTab"),
						new JScrollPane(elementSimPropPanel));
			}
			extensionTabs.addTab(mxResources.get("editExpressionsTab"),
					new JScrollPane(expressions));
			if (perspective == 4 && getMainFrame() != null
					&& getMainFrame().isAdvancedPerspective()) {
				extensionTabs.addTab(mxResources.get("configurationTab"),
						new JScrollPane(configurator));
			}
		}
		if (elm == null || elm != null && this.lastEditableElement != elm) {
			extensionTabs.addTab(mxResources.get("messagesTab"),
					new JScrollPane(ConsoleTextArea.getTextArea()));

			if (perspective == 2 && getMainFrame() != null
					&& getMainFrame().isAdvancedPerspective()) {
				extensionTabs.addTab(mxResources.get("modelConfPropTab"),
						configuratorProperties.getScrollPane());

			}

		}

	}

	public void bringUpExtension(String name) {
		for (int i = 0; i < extensionTabs.getTabCount(); i++) {
			if (extensionTabs.getTitleAt(i).equals(name)) {
				extensionTabs.setSelectedIndex(i);
				return;
			}
		}
	}

	public void bringUpTab(String name) {
		for (int i = 0; i < extensionTabs.getTabCount(); i++) {
			if (extensionTabs.getTitleAt(i).equals(name)) {
				extensionTabs.setSelectedIndex(i);
				Component c = extensionTabs.getComponent(i);
				if (c != null) {
					c.revalidate();
					c.repaint();
					return;
				}
			}
		}
	}

	public JTextArea getMessagesArea() {
		return ConsoleTextArea.getTextArea();
	}

	public ConfiguratorPanel getConfigurator() {
		return configurator;
	}

	public void editModelReset() {
		productLineIndex.reset();
		if (perspective == 0)
			// editModel(new ProductLine())
			;
		else {
			// TODO fix when the syntax o semantic model is loaded -> update
			// dependent models.
			/*
			 * Refas refas = new Refas(PerspectiveType.modeling, ((Refas)
			 * getEditedModel()).getSyntaxRefas(), ((Refas)
			 * getEditedModel()).getSemanticRefas()); refas2hlcl = new
			 * Refas2Hlcl(refas); editModel(refas);
			 * configurator.setRefas2hlcl(refas2hlcl);
			 */
		}

	}

	public void populateIndex(ProductLine pl) {

		// productLineIndex.populate(pl);
		AbstractGraph plGraph = (AbstractGraph) getGraphComponent().getGraph();
		plGraph.buildFromProductLine2(pl, productLineIndex);
		// ((mxGraphModel) plGraph.getModel()).clear();
		// plGraph.setProductLine(pl);

	}

	public ModelInstance getEditedModel() {
		return refasModel;
		/*
		 * if (perspective == 0) return ((AbstractGraph)
		 * getGraphComponent().getGraph()) .getProductLine(); else return
		 * ((AbstractGraph) getGraphComponent().getGraph()).getRefas();
		 */
	}

	// jcmunoz: new method for REFAS

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void editPropertiesRefas() {
		editPropertiesRefas(lastEditableElement);
	}

	public void editPropertiesRefas(final InstCell instCell) {
		try {
			updateVisibleProperties(instCell);
			if (recursiveCall)
				return;
			elementDesignPanel.editorProperties(this, instCell);

			this.extensionTabs.repaint();
			// elementDesPropPanel.removeAll();
			elementConfigPropPanel.removeAll();
			elementExpressionPanel.removeAll();
			elementSimPropPanel.removeAll();

			if (instCell == null || instCell.getInstElement() == null) {
				if (lastTabIndex != 0)
					lastTabIndex = 0;
				else {
					tabIndex = 0;
					extensionTabs.setSelectedIndex(0);

				}
				lastEditableElement = null;
				return;
			} else {
				final InstElement finalEditElm = instCell.getInstElement();
				recursiveCall = true;
				((MainFrame) getFrame()).waitingCursor(true);
				if (lastEditableElement != instCell) {
					lastEditableElement = instCell;
					// TODO workaround to update after simul
					updateExpressions = true;
				}
				if (extensionTabs.getTabCount() > tabIndex && tabIndex >= 0) {
					extensionTabs.setSelectedIndex(tabIndex);
					extensionTabs.getSelectedComponent().repaint();
				}

				List<InstAttribute> visible = finalEditElm
						.getVisibleAttributes(refasModel
								.getParentSMMSyntaxElement(finalEditElm));

				for (InstAttribute instAttribute : visible) {
					Map<String, InstElement> mapElements = null;
					if (finalEditElm instanceof InstPairwiseRel) {
						InstPairwiseRel instPairwise = (InstPairwiseRel) finalEditElm;
						mapElements = refasModel.getSyntaxModel()
								.getValidPairwiseRelations(
										instPairwise.getSourceRelations()
												.get(0),
										instPairwise.getTargetRelations()
												.get(0));
					}
					instAttribute.updateValidationList(finalEditElm,
							mapElements);
				}
				visible = finalEditElm.getVisibleAttributes(refasModel
						.getParentSMMSyntaxElement(finalEditElm));

				List<InstAttribute> editables = finalEditElm
						.getEditableAttributes(visible);
				// finalEditElm
				// .getEditableVariables(refasModel
				// .getParentSMMSyntaxElement((InstElement) finalEditElm));

				if (finalEditElm instanceof InstConcept) {
					String iden = ((InstConcept) finalEditElm)
							.getTransSupportMetaElement().getAutoIdentifier();
					// System.out.println(iden);
					if (iden.equals("CG") || iden.equals("ContextVariable")
							|| iden.equals("GlobalVariable")
							|| iden.equals("Variable") || iden.equals("ENUM"))

						editableElementType = "var";
					else

						editableElementType = "vertex";
				}
				if (finalEditElm instanceof InstPairwiseRel) {
					if (((InstPairwiseRel) finalEditElm).getSourceRelations()
							.size() == 0) {
						((MainFrame) getFrame()).waitingCursor(false);
						// TODO workaround for non supported relations - delete
						// after fix
						return;
					}

					editableElementType = "edge";
				}
				if (finalEditElm instanceof InstOverTwoRel) {
					editableElementType = "groupdep";
				}
				if (editableElementType != null)
					if (this.perspective == 2)

						if (finalEditElm instanceof InstElement
						// refas2hlcl.validateConceptType(finalEditElm,
						// "GeneralConcept")
						)
							consoleTextArea.setText(refas2hlcl
									.getElementTextConstraints(
											finalEditElm.getIdentifier(),
											editableElementType,
											ModelExpr2HLCL.CONF_EXEC));
				if (this.perspective == 4)

					consoleTextArea.setText(refas2hlcl
							.getElementTextConstraints(
									finalEditElm.getIdentifier(),
									editableElementType,
									ModelExpr2HLCL.SIMUL_EXEC));
				// expressions.configure(
				// getEditedModel(),
				// refas2hlcl.getElementConstraintGroup(
				// elm.getIdentifier(), type), (InstElement) elm);

				// TODO split in two new classes, one for each panel

				JPanel elementSimPropSubPanel = elementStateTab(visible,
						editables, finalEditElm, instCell);

				JPanel elementConfPropSubPanel = elementConfigTab(visible,
						editables, finalEditElm, instCell);

				JScrollPane jj = new JScrollPane(elementConfPropSubPanel);
				jj.setAutoscrolls(true);
				elementConfigPropPanel.add(jj);
				elementSimPropPanel.add(elementSimPropSubPanel);
				extensionTabs.getSelectedComponent().repaint();
				elementDesignPanel.revalidate();
				elementConfigPropPanel.revalidate();
				elementExpressionPanel.revalidate();
				elementSimPropPanel.revalidate();
			}
			refresh();
			((MainFrame) getFrame()).waitingCursor(false);

		} catch (Exception e) {

			ConsoleTextArea.addText(e.getMessage());
			ConsoleTextArea.addText(e.getStackTrace());
		} finally {
			recursiveCall = false;
		}
	}

	public JPanel elementConfigTab(List<InstAttribute> visible,
			List<InstAttribute> editables, final InstElement instElement,
			final InstCell instCell) {
		RefasWidgetFactory factory = new RefasWidgetFactory(this);
		JPanel elementConfPropSubPanel = new JPanel(new SpringLayout());
		int configurationPanelElements = 0;
		int instances = instElement.getInstances(refasModel);
		int pos = -1;
		do {
			for (InstAttribute instAttribute : visible) {
				if (instances > 1 && pos == -1)
					instAttribute.updateAdditionalAttributes(instances);
				if (instances > 1 && pos != -1)
					instAttribute = instAttribute.getAdditionalAttribute(pos);
				Map<String, InstElement> mapElements = null;
				if (instElement instanceof InstPairwiseRel) {
					InstPairwiseRel instPairwise = (InstPairwiseRel) instElement;
					mapElements = refasModel.getSyntaxModel()
							.getValidPairwiseRelations(
									instPairwise.getSourceRelations().get(0),
									instPairwise.getTargetRelations().get(0));
				}
				instAttribute.updateValidationList(instElement, mapElements);

				if (instAttribute.getType().equals(
						"com.variamos.dynsup.model.ModelExpr")) {
					continue;
				}

				final WidgetR w = factory.getWidgetFor(instAttribute);

				if (w == null) {
					recursiveCall = false;
					System.err.print(" VGE: No Widget found for "
							+ instAttribute);
					continue;
				}
				// TODO: Add listeners to w.

				w.getEditor().addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent arg0) {
						// Makes it pull the values.
						IntInstAttribute v = w.getInstAttribute();
						if (v.getType().equals("String"))
							v.setValue(AbstractElement.multiLine(v.toString(),
									15));
						// Divide lines every 15 characters (aprox.)
						onVariableEdited(instElement, v);
					}

					@Override
					public void focusGained(FocusEvent arg0) {
					}
				});

				w.getGroup().addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent arg0) {
						// Makes it pull the values.
						IntInstAttribute v = w.getInstAttribute();
						if (v.getType().equals("String"))
							v.setValue(AbstractElement.multiLine(v.toString(),
									15));
						// Divide lines every 15 characters (aprox.)
						onVariableEdited(instElement, v);
					}

					@Override
					public void focusGained(FocusEvent arg0) {
					}
				});

				w.getEditor().addPropertyChangeListener(
						new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								if (WidgetPL.PROPERTY_VALUE.equals(evt
										.getPropertyName())) {
									w.getInstAttribute();
									updateExpressions = true;
									onVariableEdited(instElement,
											w.getInstAttribute());
								}
							}
						});
				w.getGroup().addPropertyChangeListener(
						new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								if (WidgetPL.PROPERTY_VALUE.equals(evt
										.getPropertyName())) {
									w.getInstAttribute();
									updateExpressions = true;
									onVariableEdited(instElement,
											w.getInstAttribute());
								}
							}
						});
				if (w instanceof MClassWidget
						|| w instanceof MEnumerationWidget) {
					w.getEditor().setPreferredSize(new Dimension(200, 100));
				} else {
					w.getEditor().setPreferredSize(new Dimension(200, 20));
					w.getEditor().setMaximumSize(new Dimension(200, 20));
				}
				w.editVariable(instAttribute);
				if (!editables.contains(instAttribute)) {
					w.getEditor().setEnabled(false);
				}
				// GARA
				// variablesPanel.add(new JLabel(v.getName() + ":: "));
				if (instAttribute.getAttribute() instanceof ElemAttribute
						&& instAttribute.getAttribute().getAttributeType()
								.equals(AttributeType.GLOBALCONFIG)) {
					JLabel label = new JLabel(instAttribute.getDisplayName()
							+ ": ");
					elementConfPropSubPanel.add(label);
					label.setToolTipText(instAttribute.getToolTipText());
					elementConfPropSubPanel.add(w);

					if (instAttribute.isAffectProperties()) {

						if (instAttribute.getIdentifier().startsWith("Conf")) {
							JButton button = new JButton("Test");
							button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// new Thread() {
									// public void run() {
									// synchronized (getEditor()) {
									clearNotificationBar();
									configModel(instElement, true);
									// executeSimulation(true,
									// Refas2Hlcl.CONF_EXEC);
									editPropertiesRefas(instCell);
									updateExpressions = true;
									// }

									// }
									// }.start();
								}
							});
							elementConfPropSubPanel.add(button);
							button = new JButton("Configure");
							button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// new Thread() {
									// public void run() {
									// synchronized (getEditor()) {
									clearNotificationBar();
									configModel(instElement, false);
									// executeSimulation(true,
									// Refas2Hlcl.CONF_EXEC);
									editPropertiesRefas(instCell);
									updateExpressions = true;
									// }

									// }
									// }.start();
								}
							});
							elementConfPropSubPanel.add(button);
						} else {
							JButton button = new JButton("Validate");
							button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									modelEditor.clearNotificationBar();
									editPropertiesRefas(instCell);
								}
							});
							elementConfPropSubPanel.add(button);

							elementConfPropSubPanel.add(new JPanel());
						}

					} else {
						elementConfPropSubPanel.add(new JPanel());
						elementConfPropSubPanel.add(new JPanel());
					}

					configurationPanelElements++;
				}

			}
			elementConfPropSubPanel.setMinimumSize(new Dimension(350,
					configurationPanelElements * 20));
			elementConfPropSubPanel.setMaximumSize(new Dimension(350,
					configurationPanelElements * 20));

			SpringUtilities.makeCompactGrid(elementConfPropSubPanel,
					configurationPanelElements, 4, 4, 4, 4, 4);
			pos++;
		} while (pos + 1 < instances);

		return elementConfPropSubPanel;
	}

	public JPanel elementStateTab(List<InstAttribute> visible,
			List<InstAttribute> editables, final InstElement instElement,
			final InstCell instCell) {
		RefasWidgetFactory factory = new RefasWidgetFactory(this);

		JPanel elementSimPropSubPanel = new JPanel(new SpringLayout());
		int simulationPanelElements = 1;

		int instances = instElement.getInstances(refasModel);
		int pos = -1;
		do {
			for (InstAttribute instAttribute : visible) {
				if (instances > 1 && pos == -1)
					instAttribute.updateAdditionalAttributes(instances);
				if (instances > 1 && pos != -1)
					instAttribute = instAttribute.getAdditionalAttribute(pos);
				Map<String, InstElement> mapElements = null;
				if (instElement instanceof InstPairwiseRel) {
					InstPairwiseRel instPairwise = (InstPairwiseRel) instElement;
					mapElements = refasModel.getSyntaxModel()
							.getValidPairwiseRelations(
									instPairwise.getSourceRelations().get(0),
									instPairwise.getTargetRelations().get(0));
				}
				instAttribute.updateValidationList(instElement, mapElements);

				if (instAttribute.getType().equals(
						"com.variamos.dynsup.model.ModelExpr")) {
					continue;
				}

				final WidgetR w = factory.getWidgetFor(instAttribute);

				if (w == null) {
					recursiveCall = false;
					System.err.print(" VGE: No Widget found for "
							+ instAttribute);
					continue;
				}
				// TODO: Add listeners to w.

				w.getEditor().addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent arg0) {
						// Makes it pull the values.
						IntInstAttribute v = w.getInstAttribute();
						if (v.getType().equals("String"))
							v.setValue(AbstractElement.multiLine(v.toString(),
									15));
						// Divide lines every 15 characters (aprox.)
						onVariableEdited(instElement, v);
					}

					@Override
					public void focusGained(FocusEvent arg0) {
					}
				});

				w.getGroup().addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent arg0) {
						// Makes it pull the values.
						IntInstAttribute v = w.getInstAttribute();
						if (v.getType().equals("String"))
							v.setValue(AbstractElement.multiLine(v.toString(),
									15));
						// Divide lines every 15 characters (aprox.)
						onVariableEdited(instElement, v);
					}

					@Override
					public void focusGained(FocusEvent arg0) {
					}
				});

				w.getEditor().addPropertyChangeListener(
						new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								if (WidgetPL.PROPERTY_VALUE.equals(evt
										.getPropertyName())) {
									w.getInstAttribute();
									updateExpressions = true;
									onVariableEdited(instElement,
											w.getInstAttribute());
								}
							}
						});
				w.getGroup().addPropertyChangeListener(
						new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								if (WidgetPL.PROPERTY_VALUE.equals(evt
										.getPropertyName())) {
									w.getInstAttribute();
									updateExpressions = true;
									onVariableEdited(instElement,
											w.getInstAttribute());
								}
							}
						});
				if (w instanceof MClassWidget
						|| w instanceof MEnumerationWidget) {
					w.getEditor().setPreferredSize(new Dimension(200, 100));
				} else {
					w.getEditor().setPreferredSize(new Dimension(200, 20));
					w.getEditor().setMaximumSize(new Dimension(200, 20));
				}
				w.editVariable(instAttribute);
				if (!editables.contains(instAttribute)) {
					w.getEditor().setEnabled(false);
				}
				// GARA
				// variablesPanel.add(new JLabel(v.getName() + ":: "));
				if (instAttribute.getAttribute() instanceof ElemAttribute
						&& instAttribute.getAttribute().getAttributeType()
								.equals(AttributeType.EXECCURRENTSTATE)) {
					JLabel label = new JLabel(instAttribute.getDisplayName()
							+ ": ");
					elementSimPropSubPanel.add(label);
					label.setToolTipText(instAttribute.getToolTipText());
					elementSimPropSubPanel.add(w);

					if (instAttribute.isAffectProperties()) {
						JComponent wc = w.getEditor();
						if (wc instanceof ItemSelectable)
							((ItemSelectable) wc)
									.addItemListener(new ItemListener() {
										@Override
										public void itemStateChanged(ItemEvent e) {
											editPropertiesRefas(instCell);
											updateExpressions = true;
										}
									});
						ImageIcon icon = new ImageIcon(
								BasicGraphEditor.class
										.getResource("/com/variamos/gui/perspeditor/images/www.iconfinder.com/refresh.png"));
						JButton button = new JButton(icon);
						button.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (!recursiveCall) {
									clearNotificationBar();
									executeSimulation(true, true,
											ModelExpr2HLCL.CONF_EXEC);
									editPropertiesRefas(instCell);
									updateExpressions = true;
								}
							}
						});
						elementSimPropSubPanel.add(button);
					} else
						elementSimPropSubPanel.add(new JPanel());

					simulationPanelElements++;
				}
			}
			if (simulationPanelElements == ((simulationPanelElements / 2 * 2))) {
				elementSimPropSubPanel.add(new JPanel());
				elementSimPropSubPanel.add(new JPanel());
				elementSimPropSubPanel.add(new JPanel());
			}

			SpringUtilities.makeCompactGrid(elementSimPropSubPanel,
					simulationPanelElements / 2, 6, 4, 4, 4, 4);
			pos++;
		} while (pos + 1 < instances);

		return elementSimPropSubPanel;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (updateTabs)
			this.editPropertiesRefas(lastEditableElement);
		updateTabs = false;
	}

	public void refresh() {

		try {
			((PerspEditorGraph) getGraphComponent().getGraph())
					.refreshVariable(lastEditableElement);
		} catch (Exception e) {
			lastEditableElement = ((PerspEditorGraph) this.getGraphComponent()
					.getGraph()).getInstCell();
			try {
				if (lastEditableElement != null
						&& lastEditableElement.getInstElement() != null)
					((PerspEditorGraph) getGraphComponent().getGraph())
							.refreshVariable(lastEditableElement);
			} catch (Exception ex) {
				System.out.println("VariamosGraphEditor: Update error");
				ConsoleTextArea.addText(ex.getStackTrace());
			}
		}
	}

	public void refreshElement(InstElement elm) {
		List<InstAttribute> visible = elm.getVisibleVariables(refasModel
				.getParentSMMSyntaxElement(elm));
		RefasWidgetFactory factory = new RefasWidgetFactory(this);
		for (InstAttribute v : visible) {
			Map<String, InstElement> mapElements = null;
			if (elm instanceof InstPairwiseRel) {
				InstPairwiseRel instPairwise = (InstPairwiseRel) elm;
				try {
					mapElements = refasModel.getSyntaxModel()
							.getValidPairwiseRelations(
									instPairwise.getSourceRelations().get(0),
									instPairwise.getTargetRelations().get(0));
				} catch (Exception e) {
					// FIXME
					ConsoleTextArea.addText(e.getStackTrace());
				}
			}
			v.updateValidationList(elm, mapElements);
			final WidgetR w = factory.getWidgetFor(v);
			if (w == null) {
				return;
			}
			w.editVariable(v);
		}
	}

	protected void onVariableEdited(Editable e) {
		((AbstractGraph) getGraphComponent().getGraph()).refreshVariable(e);
	}

	@SuppressWarnings("unchecked")
	protected void onVariableEdited(InstElement editableElement,
			IntInstAttribute instAttribute) {
		if (editableElement instanceof InstConcept) {
			SyntaxElement editableMetaElement = ((InstConcept) editableElement)
					.getEdSyntaxEle();
			if (editableMetaElement != null) {
				if (instAttribute.getIdentifier().equals(
						SyntaxElement.VAR_USERIDENTIFIER))
					editableMetaElement
							.setUserIdentifier((String) instAttribute
									.getValue());
				if (instAttribute.getIdentifier().equals("OperationsMMType"))
					editableMetaElement
							.setTransInstSemanticElement(this.refasModel
									.getOperationalModel().getElement(
											(String) instAttribute.getValue()));
				// if (instAttribute.getIdentifier().equals("Visible"))
				// editableMetaElement.setVisible((boolean) instAttribute
				// .getValue());
				if (instAttribute.getIdentifier().equals("Name"))
					editableMetaElement.setName((String) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Style"))
					editableMetaElement.setStyle((String) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("PaletteName"))
					editableMetaElement.setPaletteName((String) instAttribute
							.getValue());

				if (instAttribute.getIdentifier().equals("Description"))
					editableMetaElement.setDescription((String) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Width"))
					editableMetaElement
							.setWidth((int) instAttribute.getValue());
				if (instAttribute.getIdentifier().equals("Height"))
					editableMetaElement.setHeight((int) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Image"))
					editableMetaElement.setImage((String) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("TopConcept"))
					editableMetaElement.setTopConcept((boolean) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("BackgroundColor"))
					editableMetaElement
							.setBackgroundColor((String) instAttribute
									.getValue());
				if (instAttribute.getIdentifier().equals("BorderStroke"))
					editableMetaElement.setBorderStroke((int) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Resizable"))
					editableMetaElement.setResizable((boolean) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("value"))
					editableMetaElement
							.setModelingAttributes((Map<String, ElemAttribute>) instAttribute
									.getValue());
			}
			// IntSemanticElement editableSemanticElement = ((InstConcept)
			// editableElement)
			// .getEditableSemanticElement();
			// if (editableSemanticElement != null) {
			// if (instAttribute.getIdentifier().equals("Identifier"))
			// editableSemanticElement
			// .setIdentifier((String) instAttribute.getValue());
			// }
		}
		refresh();
	}

	@Override
	protected PerspectiveToolBar installToolBar(MainFrame mainFrame,
			int perspective) {
		if (perspectiveToolBarPanel == null) {
			perspectiveToolBarPanel = new JPanel();
			add(perspectiveToolBarPanel, BorderLayout.NORTH);
		}

		perspectiveToolBarPanel.removeAll();
		perspectiveToolBarPanel.setLayout(new BorderLayout());
		// if (perspective == 3)
		perspectiveToolBarPanel.add(new PerspEditorToolBar(this,
				JToolBar.HORIZONTAL), BorderLayout.WEST);
		// else
		// jp.add(new PLEditorToolBar(this, JToolBar.HORIZONTAL),
		// BorderLayout.WEST);
		perspectiveToolBarPanel.add(new JLabel(), BorderLayout.CENTER);
		PerspectiveToolBar perspectiveToolBar = new PerspectiveToolBar(
				mainFrame, JToolBar.HORIZONTAL, perspective);
		perspectiveToolBarPanel.add(perspectiveToolBar, BorderLayout.EAST);

		return perspectiveToolBar;
	}

	public final MainFrame getMainFrame() {
		Container contairner1 = this.getParent();
		if (contairner1 != null) {
			Container contairner2 = contairner1.getParent();
			Container contairner3 = contairner2.getParent();
			Container contairner4 = contairner3.getParent();
			return (MainFrame) contairner4;
		}
		return null;
	}

	public void refreshPalette() {
		int i = graphAndRight.getDividerLocation();
		graphAndRight.setDividerLocation(i + 1);
	}

	public void setModelEditor(VariamosGraphEditor modelEditor) {
		this.modelEditor = modelEditor;
	}

	public void updateObjects() {
		if (perspective == 4) {
			clearElementState(ModelExpr2HLCL.SIMUL_EXEC);
			// executeSimulation(true, Refas2Hlcl.DESIGN_EXEC);
			this.updateRefasModel(modelEditor.getEditedModel());
			mxGraph source = modelEditor.getGraphComponent().getGraph();
			mxGraph target = graphComponent.getGraph();
			target.setCellsDeletable(true);
			target.setCellsMovable(true);
			target.setCellsResizable(true);
			SharedActions.beforeGraphOperation(source, false, 0, -1);
			SharedActions.cloneGraph(source, target, this.getModelViewIndex(),
					this.getModelSubViewIndex());
			target.setCellsDeletable(false);
			target.setCellsMovable(false);
			target.setCellsResizable(false);
			// System.out.println(this.getModelViewIndex() + " "
			// + this.getModelSubViewIndex());
			SharedActions.afterOpenCloneGraph(source, this);
			SharedActions.afterOpenCloneGraph(target, this);
			((mxCell) graphComponent.getGraph().getDefaultParent())
					.setValue("simul");
			// Different from null, to display simulation colors
			this.refresh();
		} else if (perspective == 2) {
			clearElementState(ModelExpr2HLCL.DESIGN_EXEC);
			mxGraph target = graphComponent.getGraph();
			SharedActions.afterOpenCloneGraph(target, this);
		}
	}

	private void updateRefasModel(AbstractModel editedModel) {
		refasModel = (ModelInstance) editedModel;
		this.refas2hlcl.setRefas(refasModel);
	}

	/**
	 * include execution time
	 */
	@Override
	protected void mouseLocationChanged(MouseEvent e) {
		String solver = "";
		if (!lastSolverInvocations.equals("")) {
			solver = " Last Execution Time(ms) {Total[Solver]}: "
					+ lastSolverInvocations + " || ";
		}
		status(solver + e.getX() + ", " + e.getY());
	}

	/**
	 * Include execution time
	 */
	@Override
	protected void installRepaintListener() {
		graphComponent.getGraph().addListener(mxEvent.REPAINT,
				new mxIEventListener() {
					@Override
					public void invoke(Object source, mxEventObject evt) {
						String solver = "";
						String buffer = (graphComponent.getTripleBuffer() != null) ? ""
								: " (unbufferedX)";
						if (!lastSolverInvocations.equals("")) {
							solver = " Last Execution Time(ms) {Total[Solver]}: "
									+ lastSolverInvocations + " || ";

						}

						mxRectangle dirty = (mxRectangle) evt
								.getProperty("region");

						if (dirty == null) {
							status(solver + "Repaint all" + buffer);
						} else {
							status(solver + "Repaint: x="
									+ (int) (dirty.getX()) + " y="
									+ (int) (dirty.getY()) + " w="
									+ (int) (dirty.getWidth()) + " h="
									+ (int) (dirty.getHeight()) + buffer);
						}
					}
				});
	}

	public void clearNotificationBar() {
		lastSolverInvocations = "";
	}

	public void clearElementErrors() {
		refas2hlcl.cleanGUIErrors();
		this.refresh();
	}

	public void clearQueryMonitor() {
		refas2hlcl.clearQueryMonitor();
	}

	public void clearElementState(int execType) {
		refas2hlcl.cleanGUIElements(execType);
		if (task != null) {
			task.setTerminated(true);
			task = null;
		}

		this.refresh();

	}

	public void endSimulation() {
		if (task != null) {
			task.setTerminated(true);
			task = null;
		}

		this.refresh();

	}

	// Static operation's definition
	public void executeSimulation(boolean firstSimulExecution,
			boolean reloadDashboard, int type) {
		executeSimulation(firstSimulExecution, reloadDashboard, type, true, "");
	}

	// TODO support ALL operations dynamically, not only the first

	public void callOperations(List<String> operations) {
		// FIXME support multiple models selected from the menu not only REFAS
		InstElement refas = refasModel.getSyntaxModel().getVertex("REFAS");
		InstConcept element = new InstConcept("REFAS1", refas);
		element.createInstAttributes(null);
		this.refasModel.getVariabilityVertex().put("REFAS1", element);
		System.out.println(operations);
		boolean first = true;
		if (operations.get(0).startsWith("N:"))
			first = false;
		executeOperationsThead(first, operations);
	}

	// Dynamic operation's definition
	public SolverOpersTask executeOperationsThead(boolean firstSimulExecution,
			List<String> operations) {

		if (!firstSimulExecution && semTask != null) {
			semTask.setFirstSimulExec(false);
			semTask.setNext(true);
		} else {
			if (semTask != null)
				semTask.setTerminated(true);
			progressMonitor = new ProgressMonitor(VariamosGraphEditor.this,
					"Executing operation", "", 0, 100);
			progressMonitor.setMillisToDecideToPopup(5);
			progressMonitor.setMillisToPopup(5);
			progressMonitor.setProgress(1);
			semTask = new SolverOpersTask(progressMonitor, refasModel,
					refas2hlcl, configHlclProgram, firstSimulExecution,
					operations, lastConfiguration);

			semTask.addPropertyChangeListener(this);
			semTask.execute();
		}
		return semTask;
	}

	// Static operation's definition
	public SolverTasks executeSimulation(boolean firstSimulExecution,
			boolean reloadDashboard, int type, boolean update, String element) {

		if (!firstSimulExecution && task != null
				&& task.getExecType() == ModelExpr2HLCL.SIMUL_EXEC) {
			task.setFirstSimulExec(false);
			task.setNext(true);
		} else {
			if (task != null)
				task.setTerminated(true);
			progressMonitor = new ProgressMonitor(VariamosGraphEditor.this,
					"Executing Simulation", "", 0, 100);
			progressMonitor.setMillisToDecideToPopup(5);
			progressMonitor.setMillisToPopup(5);
			progressMonitor.setProgress(0);
			task = new SolverTasks(progressMonitor, type, refas2hlcl,
					configHlclProgram, firstSimulExecution, reloadDashboard,
					update, element, lastConfiguration);
			task.addPropertyChangeListener(this);
			task.execute();
		}
		return task;
	}

	// Static operation's definition
	public void exportConfiguration(String file) {
		if (task == null || task.isDone() || task.getProgress() == 100) {
			progressMonitor = new ProgressMonitor(VariamosGraphEditor.this,
					"Exporting Solutions", "", 0, 100);
			progressMonitor.setMillisToDecideToPopup(5);
			progressMonitor.setMillisToPopup(5);
			progressMonitor.setProgress(0);

			task = new SolverTasks(progressMonitor,
					ModelExpr2HLCL.SIMUL_EXPORT, this.refasModel, refas2hlcl,
					file);
			task.addPropertyChangeListener(this);
			((MainFrame) getFrame()).waitingCursor(true);
			task.execute();
		}
	}

	// Static operation's definition without swing worker
	@Deprecated
	public boolean executeSimulation(int type, boolean update, String element) {
		boolean wasFirst = false, first = false;
		long iniTime = System.currentTimeMillis();
		((MainFrame) getFrame()).waitingCursor(true);
		boolean result = false;
		try {
			if (first || lastConfiguration == null) {
				result = refas2hlcl.execute(null, element,
						ModelExpr2HLCL.ONE_SOLUTION, type);
				wasFirst = true;
			} else {
				result = refas2hlcl.execute(null, element,
						ModelExpr2HLCL.NEXT_SOLUTION, type);
			}
			lastConfiguration = refas2hlcl.getConfiguration();
			if (result) {
				if (update) {
					refas2hlcl.updateGUIElements(null);
					ConsoleTextArea.addText(refas2hlcl.getText());
					// bringUpTab(mxResources.get("elementSimPropTab"));
					editPropertiesRefas(lastEditableElement);
				}

			} else {
				if (first) {
					switch (type) {
					case ModelExpr2HLCL.DESIGN_EXEC:
						JOptionPane
								.showMessageDialog(
										frame,
										"Last validated change makes the model inconsistent."
												+ " \n Please review the restrictions defined and "
												+ "try again. \nModel visual representation was not updated.",
										"Simulation Execution Error",
										JOptionPane.INFORMATION_MESSAGE, null);
						break;
					case ModelExpr2HLCL.CONF_EXEC:
						JOptionPane
								.showMessageDialog(
										frame,
										"Last configuration change validated makes the model "
												+ "\n inconsistent. Please review the selection and "
												+ "try again. \nAttributes values were not updated.",
										"Simulation Execution Error",
										JOptionPane.INFORMATION_MESSAGE, null);
						break;
					case ModelExpr2HLCL.SIMUL_EXEC:
					case ModelExpr2HLCL.SIMUL_EXPORT:
						JOptionPane
								.showMessageDialog(
										frame,
										"No solution found for this model configuration."
												+ " \n Please review the restrictions defined and "
												+ "try again. \nAttributes values were not updated.",
										"Simulation Execution Error",
										JOptionPane.INFORMATION_MESSAGE, null);
						break;
					}
				} else
					JOptionPane.showMessageDialog(frame,
							"No more solutions found", "Simulation Message",
							JOptionPane.INFORMATION_MESSAGE, null);
			}
			refresh();
			// updateObjects();
			((MainFrame) getFrame()).waitingCursor(false);
			long endTime = System.currentTimeMillis();
			lastSolverInvocations = "NormalExec: " + (endTime - iniTime) + "["
					+ refas2hlcl.getLastExecutionTime() / 1000000 + "]"
					+ " -- ";
		} catch (Exception e) {
			ConsoleTextArea.addText(e.getStackTrace());
			ConsoleTextArea.addText(e.toString());
			JOptionPane
					.showMessageDialog(
							frame,
							"Simulation execution unhandled error, please verify Instance Expressions or report a problem.",
							"Simulation Error",
							JOptionPane.INFORMATION_MESSAGE, null);

			((MainFrame) getFrame()).waitingCursor(false);
		}
		return wasFirst;
	}

	/*
	 * private List<String> compareSolutions(Configuration lastConfiguration,
	 * Configuration currentConfiguration) { List<String> out = new
	 * ArrayList<String>(); Map<String, Integer> lastConfig =
	 * lastConfiguration.getConfiguration(); Map<String, Integer> currentConfig
	 * = currentConfiguration .getConfiguration(); for (String solution :
	 * lastConfig.keySet()) if (lastConfig.get(solution) !=
	 * currentConfig.get(solution)) out.add(solution); return out; }
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressMonitor.setProgress(progress);
			if (task != null) {
				String message = String.format("Completed %d%%.\n", progress);
				progressMonitor.setNote(message);
				if (task.getProgress() == 100
						&& (task.getExecType() == ModelExpr2HLCL.SIMUL_EXEC || task
								.getExecType() == ModelExpr2HLCL.SIMUL_MAPE)) {
					refas2hlcl.updateGUIElements(null);
					updateDashBoard(true, task.isReloadDashBoard(),
							task.isUpdate());
					ConsoleTextArea.addText(refas2hlcl.getText());
					// bringUpTab(mxResources.get("elementSimPropTab"));
					editPropertiesRefas(lastEditableElement);

				}
			}
			if (semTask != null) {
				String message = String.format("Completed %d%%.\n", progress);
				progressMonitor.setNote(message);
				if (semTask.getProgress() == 100 // TODO validate for simulation
				// && (semTask.getExecType() == ModelExpr2HLCL.SIMUL_EXEC ||
				// task
				// .getExecType() == ModelExpr2HLCL.SIMUL_MAPE)
				) {
					List<String> outVariables = semTask.getOutVariables();
					refas2hlcl.updateGUIElements(null, outVariables);
					updateDashBoard(semTask.isShowDashboard(),
							semTask.isReloadDashBoardConcepts(),
							semTask.isUpdate());
					ConsoleTextArea.addText(refas2hlcl.getText());
					updateSimulResults();
					// bringUpTab(mxResources.get("elementSimPropTab"));
					editPropertiesRefas(lastEditableElement);

				}
			}

			if (progressMonitor.isCanceled()
					|| (fileTask != null && fileTask.isDone())) {
				if (progressMonitor.isCanceled()) {
					task.cancel(true);
					JOptionPane
							.showMessageDialog(
									frame,
									"Execution incomplete, partial solution file saved",
									"Task Notification",
									JOptionPane.INFORMATION_MESSAGE, null);
					((MainFrame) getFrame()).waitingCursor(false);
				}
			} else if (progressMonitor.isCanceled()
					|| (task != null && task.isDone())) {
				if (progressMonitor.isCanceled()) {
					task.cancel(true);
					if (task.getExecType() == ModelExpr2HLCL.SIMUL_EXPORT) {
						JOptionPane
								.showMessageDialog(
										frame,
										"Execution incomplete, partial solution file saved",
										"Task Notification",
										JOptionPane.INFORMATION_MESSAGE, null);
					} else
						JOptionPane.showMessageDialog(frame,
								"Execution cancelled", "Task Notification",
								JOptionPane.INFORMATION_MESSAGE, null);
					((MainFrame) getFrame()).waitingCursor(false);
				} else {
					editPropertiesRefas(lastEditableElement);
					ConsoleTextArea.addText(refas2hlcl.getText());
					((MainFrame) getFrame()).waitingCursor(false);
					lastSolverInvocations = task.getExecutionTime();
					switch (task.getExecType()) {
					case ModelExpr2HLCL.CONF_EXEC:
						invalidConfigHlclProgram = task
								.isInvalidConfigHlclProgram();
						break;
					case ModelExpr2HLCL.DESIGN_EXEC:
						if (!task.getErrorTitle().equals("")) {
							JOptionPane.showMessageDialog(frame,
									task.getErrorMessage(),
									task.getErrorTitle(),
									JOptionPane.INFORMATION_MESSAGE, null);

						}
						refresh();
						break;
					case ModelExpr2HLCL.SIMUL_EXEC:
						updateDashBoard(true, task.isReloadDashBoard(),
								task.isUpdate());
					case ModelExpr2HLCL.SIMUL_EXPORT:
						refresh();
						lastConfiguration = task.getLastConfiguration();
						if (!task.getErrorTitle().equals("")) {
							JOptionPane.showMessageDialog(frame,
									task.getErrorMessage(),
									task.getErrorTitle(),
									JOptionPane.INFORMATION_MESSAGE, null);
						}

						break;
					}

				}
			} else if (progressMonitor.isCanceled()
					|| (semTask != null && semTask.isDone())) {
				if (progressMonitor.isCanceled()) {
					semTask.cancel(true);
					JOptionPane.showMessageDialog(frame, "Execution cancelled",
							"Task Notification",
							JOptionPane.INFORMATION_MESSAGE, null);
					((MainFrame) getFrame()).waitingCursor(false);
				} else {
					editPropertiesRefas(lastEditableElement);
					ConsoleTextArea.addText(refas2hlcl.getText());
					((MainFrame) getFrame()).waitingCursor(false);
					lastSolverInvocations = semTask.getExecutionTime();
					updateDashBoard(semTask.isShowDashboard(),
							semTask.isReloadDashBoardConcepts(),
							semTask.isUpdate());
				}
			}
		}
	}

	public void updateSimulResults() {

		ConsoleTextArea.addText(refas2hlcl.getText());
		if (task != null && !task.getErrorTitle().equals("")) {
			JOptionPane
					.showMessageDialog(frame, task.getErrorMessage(),
							task.getErrorTitle(),
							JOptionPane.INFORMATION_MESSAGE, null);

		}
		if (semTask != null && !semTask.getErrorTitle().equals("")) {
			JOptionPane.showMessageDialog(frame, semTask.getErrorMessage(),
					semTask.getErrorTitle(), JOptionPane.INFORMATION_MESSAGE,
					null);
			semTask.clearErrorMessage();

		}
	}

	// Static operation's definition
	public void configModel(InstElement element, boolean test) {
		if (task == null || task.isDone()) {
			progressMonitor = new ProgressMonitor(VariamosGraphEditor.this,
					"Executing Element Configuration", "", 0, 100);
			progressMonitor.setMillisToDecideToPopup(5);
			progressMonitor.setMillisToPopup(5);
			progressMonitor.setProgress(0);

			task = new SolverTasks(progressMonitor, VariamosGraphEditor.this,
					ModelExpr2HLCL.CONF_EXEC, refas2hlcl, configHlclProgram,
					invalidConfigHlclProgram, test, element, defects,
					lastConfiguration);
			task.addPropertyChangeListener(this);
			((MainFrame) getFrame()).waitingCursor(true);
			task.execute();
		}
	}

	// Static operation's definition
	public void verify() {
		verify(defects);
	}

	// Static operation's definition
	public void verify(List<String> defect) {
		if (task == null || task.isDone() || task.getProgress() == 0) {
			progressMonitor = new ProgressMonitor(VariamosGraphEditor.this,
					"System Verification", "", 0, 100);
			progressMonitor.setMillisToDecideToPopup(5);
			progressMonitor.setMillisToPopup(5);
			progressMonitor.setProgress(0);
			task = new SolverTasks(progressMonitor, VariamosGraphEditor.this,
					ModelExpr2HLCL.DESIGN_EXEC, refas2hlcl, configHlclProgram,
					invalidConfigHlclProgram, false, null, defect,
					lastConfiguration);
			task.addPropertyChangeListener(this);
			((MainFrame) getFrame()).waitingCursor(true);
			task.execute();
		}
	}

	public void setInvalidConfigHlclProgram(boolean invalidConfigHlclProgram) {
		this.invalidConfigHlclProgram = invalidConfigHlclProgram;
	}

	public void updateDefects(String string, boolean b) {
		if (b) {
			if (!defects.contains(string))
				defects.add(string);
		} else
			defects.remove(string);

		// System.out.println(string + " " + b);

	}

	public List<InstElement> getInstViews() {
		return refasModel.getSyntaxModel().getVariabilityVertex("SMView");
	}

	public void setProgressMonitor(ProgressMonitor progressMonitor) {
		this.progressMonitor = progressMonitor;
	}

	public void setFileTask(FileTasks fileTask) {
		this.fileTask = fileTask;
		task = null;

	}

	public void recoverClones() {
		SharedActions.recoverClonedElements(getGraphComponent().getGraph(),
				this);

	}

	public void showExternalContextDialog() {

		ecd.center();
	}

	public void showElementOperationAssociationDialog(int dialog) {
		eoad = new ElementsOperationAssociationDialog(this, dialog);
		eoad.center();
	}

	public void updatePespectiveMenuTab(String buttonText) {
		MainFrame mainFrame = this.getMainFrame();
		int perspectiveInd = mainFrame.getPerspective();
		PerspectiveToolBar perspective = this.installToolBar(mainFrame,
				perspectiveInd);

		if (perspectiveInd != 1
				&& buttonText.equals(mxResources.get("semanticPerspButton"))) {
			// System.out.println("semanticPerspButton");
			mainFrame.setPerspective(1);
		}
		if (perspectiveInd != 2
				&& buttonText.equals(mxResources.get("modelingPerspButton"))) {
			mainFrame.setPerspective(2);
			VariamosGraphEditor ed = mainFrame.getEditor(2);
			List<InstElement> views = ed.getEditedModel().getSyntaxModel()
					.getVariabilityVertex("SMView");
			if (views.size() == 0) {
				JOptionPane.showMessageDialog(this,
						mxResources.get("nometamodelerror"),
						"Not a valid MetaModel",
						JOptionPane.INFORMATION_MESSAGE, null);
				mainFrame.setPerspective(3);
			} else {
				ed.updateEditor();
				ed.setVisibleModel(0, -1);
				ed.defineViewTabs();
				// System.out.println("modelingPerspButton");
			}
		}
		if (perspectiveInd != 3
				&& buttonText.equals(mxResources.get("syntaxPerspButton"))) {
			mainFrame.setPerspective(3);
			// System.out.println("syntaxPerspButton");
		}

		if (perspectiveInd != 4
				&& buttonText.equals(mxResources.get("simulationPerspButton"))) {
			mainFrame.setPerspective(4);
			// System.out.println("simulationPerspButton");
		}
		perspective.updatePerspective(mainFrame.getPerspective());
		mainFrame.validate();
		mainFrame.repaint();

	}

}
