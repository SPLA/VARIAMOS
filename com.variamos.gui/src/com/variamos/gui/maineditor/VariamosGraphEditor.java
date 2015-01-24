package com.variamos.gui.maineditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.cfm.common.AbstractModel;
import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.cfm.productline.AbstractElement;
import com.cfm.productline.Editable;
import com.cfm.productline.ProductLine;
import com.cfm.productline.io.SXFMReader;
import com.cfm.productline.solver.Configuration;
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
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.defectAnalyzer.defectAnalyzer.CauCosAnayzer;
import com.variamos.defectAnalyzer.defectAnalyzer.DefectsVerifier;
import com.variamos.defectAnalyzer.defectAnalyzer.IntCauCosAnalyzer;
import com.variamos.defectAnalyzer.defectAnalyzer.IntDefectsVerifier;
import com.variamos.defectAnalyzer.model.CauCos;
import com.variamos.defectAnalyzer.model.Diagnosis;
import com.variamos.defectAnalyzer.model.defects.Defect;
import com.variamos.defectAnalyzer.model.enums.DefectAnalyzerMode;
import com.variamos.defectAnalyzer.model.enums.DefectType;
import com.variamos.gui.pl.editor.ConfigurationPropertiesTab;
import com.variamos.gui.pl.editor.ConfiguratorPanel;
import com.variamos.gui.pl.editor.PLEditorToolBar;
import com.variamos.gui.pl.editor.PLGraphEditorFunctions;
import com.variamos.gui.pl.editor.ProductLineGraph;
import com.variamos.gui.pl.editor.SpringUtilities;
import com.variamos.gui.pl.editor.widgets.WidgetPL;
import com.variamos.gui.refas.editor.ModelButtonAction;
import com.variamos.gui.refas.editor.RefasEditorToolBar;
import com.variamos.gui.refas.editor.RefasGraph;
import com.variamos.gui.refas.editor.RefasGraphEditorFunctions;
import com.variamos.gui.refas.editor.SemanticPlusSyntax;
import com.variamos.gui.refas.editor.actions.SharedActions;
import com.variamos.gui.refas.editor.panels.ElementDesignPanel;
import com.variamos.gui.refas.editor.panels.RefasExpressionPanel;
import com.variamos.gui.refas.editor.widgets.MClassWidget;
import com.variamos.gui.refas.editor.widgets.MEnumerationWidget;
import com.variamos.gui.refas.editor.widgets.RefasWidgetFactory;
import com.variamos.gui.refas.editor.widgets.WidgetR;
import com.variamos.refas.core.refas.Refas;
import com.variamos.refas.core.sematicsmetamodel.SemanticConcept;
import com.variamos.refas.core.sematicsmetamodel.SemanticEnumeration;
import com.variamos.refas.core.sematicsmetamodel.SemanticVariable;
import com.variamos.refas.core.simulationmodel.MetaExpressionSet;
import com.variamos.refas.core.simulationmodel.Refas2Hlcl;
import com.variamos.refas.core.types.PerspectiveType;
import com.variamos.syntaxsupport.metamodel.EditableElement;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstView;
import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;
import com.variamos.syntaxsupport.metamodelsupport.EditableElementAttribute;
import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaView;
import com.variamos.syntaxsupport.metamodelsupport.SimulationConfigAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SimulationStateAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;
import com.variamos.syntaxsupport.type.DomainRegister;

import fm.FeatureModelException;

/**
 * @author jcmunoz
 *
 */

@SuppressWarnings("serial")
public class VariamosGraphEditor extends BasicGraphEditor {

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

	private List<MetaView> metaViews = null;

	protected DomainRegister domainRegister = new DomainRegister();
	protected GraphTree productLineIndex;
	protected ConfiguratorPanel configurator;
	protected ConfigurationPropertiesTab configuratorProperties;

	protected RefasExpressionPanel expressions;
	protected JTextArea messagesArea;
	protected JTextArea expressionsArea;
	private ElementDesignPanel elementDesignPanel;
	protected JPanel elementConfigPropPanel;
	protected JPanel elementExpressionPanel;
	protected JPanel elementSimPropPanel;
	protected PerspectiveToolBar perspectiveToolBar;
	// Bottom tabs
	protected JTabbedPane extensionTabs;
	protected static SemanticPlusSyntax sematicSyntaxObject;

	protected int mode = 0;
	private int tabIndex = 0, lastTabIndex = 0;
	private Refas2Hlcl refas2hlcl;
	private VariamosGraphEditor modelEditor;
	private EditableElement lastEditableElement;
	private boolean recursiveCall = false;
	private boolean updateExpressions = true;
	private String editableElementType = null;

	private String lastSolverInvocations = "";
	private Configuration lastConfiguration;

	private List<String> defects = new ArrayList<String>();

	public Refas2Hlcl getRefas2hlcl() {
		return refas2hlcl;
	}

	public VariamosGraphEditor getEditor() {
		return this;
	}

	public VariamosGraphEditor(String appTitle,
			VariamosGraphComponent component, int perspective,
			AbstractModel abstractModel) {
		super(appTitle, component, perspective);

		metaViews = sematicSyntaxObject.getMetaViews();
		refas2hlcl = new Refas2Hlcl((Refas) abstractModel);

		configurator.setRefas2hlcl(refas2hlcl);

		registerEvents();
		((AbstractGraph) graphComponent.getGraph()).setModel(abstractModel);
		if (perspective == 0) {
			setPerspective(0);
			graphEditorFunctions = new PLGraphEditorFunctions(this);
			graphEditorFunctions.updateEditor(validElements,
					getGraphComponent(), modelViewIndex);
		}
		mxCell root = new mxCell();
		root.insert(new mxCell());
		RefasGraph refasGraph = (RefasGraph) component.getGraph();
		refasGraph.getModel().setRoot(root);
		for (int i = 0; i < metaViews.size(); i++) {
			mxCell parent = new mxCell("mv" + i);
			refasGraph.addCell(parent);
			MetaView metaView = metaViews.get(i);
			JPanel tabPane = new JPanel();
			if (metaView.getChildViews().size() > 0) {
				modelsTabPane.add(metaView.getName(), tabPane);
				refasGraph.addCell(new mxCell("mv" + i), parent);
				// Add the parent as first child
				for (int j = 0; j < metaView.getChildViews().size(); j++) {
					refasGraph.addCell(new mxCell("mv" + i + "-" + j), parent);
					MetaView metaChildView = metaView.getChildViews().get(j);
					JButton a = new JButton(metaChildView.getName());
					tabPane.add(a);
					a.addActionListener(new ModelButtonAction());
				}
				// TODO include recursive calls if more view levels are required
			} else {
				modelsTabPane.add(metaView.getName(), tabPane);
			}
			final EditorPalette palette = new EditorPalette();
			palette.setName("ee");
			final JScrollPane scrollPane = new JScrollPane(palette);
			scrollPane
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane
					.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			modelsTabPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					List<MetaView> metaViews = sematicSyntaxObject
							.getMetaViews();
					VariamosGraphEditor editor = getEditor();
					((MainFrame) editor.getFrame()).waitingCursor(true);
					int modelInd = getModelViewIndex();
					for (int i = 0; i < metaViews.size(); i++) {
						if (modelInd != i
								&& modelsTabPane.getTitleAt(
										modelsTabPane.getSelectedIndex())
										.equals(metaViews.get(i).getName())) {

							if (metaViews.get(i).getChildViews().size() > 0) {
								// if (false) //TODO validate the name of the
								// button with the tab, if true, identify the
								// subview
								// editor.setVisibleModel(i ,0);
								// else
								editor.setVisibleModel(i, 0);
								editor.updateView();
								center.setDividerLocation(60);
								center.setMaximumSize(center.getSize());
								center.setMinimumSize(center.getSize());
								center.setResizeWeight(0);
							} else {
								editor.setVisibleModel(i, -1);
								editor.updateView();
								center.setDividerLocation(25);
								center.setMaximumSize(center.getSize());
								center.setMinimumSize(center.getSize());
								center.setPreferredSize(center.getSize());
							}
						}
					}
					((MainFrame) editor.getFrame()).waitingCursor(false);
				}
			});
		}
		setModified(false);
	}

	public VariamosGraphEditor(MainFrame frame,
			VariamosGraphComponent component, int perspective,
			AbstractModel abstractModel) {
		super(frame, "", component, perspective);
		defects.add("Root");
		defects.add("Parent");
		defects.add("FalseOpt");
		defects.add("Dead");
		defects.add("Core");
		metaViews = new ArrayList<MetaView>();
		refas2hlcl = new Refas2Hlcl((Refas) abstractModel);

		configurator.setRefas2hlcl(refas2hlcl);

		registerEvents();
		Collection<InstView> instViews = ((Refas) abstractModel)
				.getSyntaxRefas().getInstViews();
		RefasGraph refasGraph = ((RefasGraph) graphComponent.getGraph());
		refasGraph.setValidation(false);
		refasGraph.setModel(abstractModel);
		refasGraph.setValidation(true);
		graphEditorFunctions = new RefasGraphEditorFunctions(this);
		// RefasGraph refasGraph = (RefasGraph) component.getGraph();

		this.graphLayout("organicLayout", false);
		this.getGraphComponent().zoomAndCenter();
		if (instViews.size() == 0) {
			center.setDividerLocation(0);
			upperPart.setDividerLocation(0);
			graphAndRight.setDividerLocation(700);
			setVisibleModel(-1, -1);

			updateView();
		} else {
			int i = 0;
			for (InstView instView : instViews) {
				mxCell parent = new mxCell("mv" + i);
				refasGraph.addCell(parent);
				MetaView metaView = (MetaView) instView
						.getEditableMetaElement();
				metaViews.add(metaView);
				JPanel tabPane = new JPanel();
				if (metaView.getChildViews().size() > 0) {
					modelsTabPane.add(metaView.getName(), tabPane);
					refasGraph.addCell(new mxCell("mv" + i), parent);
					// Add the parent as first child
					for (int j = 0; j < metaView.getChildViews().size(); j++) {
						refasGraph.addCell(new mxCell("mv" + i + "-" + j),
								parent);
						MetaView metaChildView = metaView.getChildViews()
								.get(j);
						JButton a = new JButton(metaChildView.getName());
						tabPane.add(a);
						a.addActionListener(new ModelButtonAction());
					}
					// TODO include recursive calls if more view levels are
					// required
				} else {
					modelsTabPane.add(metaView.getName(), tabPane);
					tabPane.setMaximumSize(new Dimension(0, 0));

				}
				final EditorPalette palette = new EditorPalette();
				palette.setName("ee");
				final JScrollPane scrollPane = new JScrollPane(palette);
				scrollPane
						.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane
						.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				modelsTabPane.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						// System.out.println("Tab: "
						// + modelsTabPane.getTitleAt(modelsTabPane
						// .getSelectedIndex()));
						List<MetaView> metaViews = sematicSyntaxObject
								.getMetaViews();
						VariamosGraphEditor editor = getEditor();
						((MainFrame) editor.getFrame()).waitingCursor(true);
						int modelInd = getModelViewIndex();
						for (int i = 0; i < metaViews.size(); i++) {
							if (modelInd != i
									&& modelsTabPane.getTitleAt(
											modelsTabPane.getSelectedIndex())
											.equals(metaViews.get(i).getName())) {

								if (metaViews.get(i).getChildViews().size() > 0) {
									// if (false) //TODO validate the name of
									// the
									// button with the tab, if true, identify
									// the
									// subview
									// editor.setVisibleModel(i ,0);
									// else
									editor.setVisibleModel(i, 0);
									editor.updateView();
									center.setDividerLocation(60);
									center.setMaximumSize(center.getSize());
									center.setMinimumSize(center.getSize());
									center.setResizeWeight(0);
								} else {
									editor.setVisibleModel(i, -1);
									editor.updateView();
									center.setDividerLocation(25);
									center.setMaximumSize(center.getSize());
									center.setMinimumSize(center.getSize());
									center.setPreferredSize(center.getSize());
								}
							}
						}
						((MainFrame) editor.getFrame()).waitingCursor(false);
					}
				});
				i++;
			}
			center.setDividerLocation(25);
			upperPart.setDividerLocation(0);
			if (((Refas) abstractModel).getPerspectiveType().equals(
					PerspectiveType.simulation))
				graphAndRight.setDividerLocation(1100);
			else
				graphAndRight.setDividerLocation(700);

			setVisibleModel(0, -1);
			updateView();
			this.setModified(false);
		}
	}

	public SemanticPlusSyntax getSematicSintaxObject() {
		return sematicSyntaxObject;
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

	public int getModelSubViewIndex() {
		return modelSubViewIndex;
	}

	public List<MetaView> getMetaViews() {
		return metaViews;
	}

	public void setVisibleModel(int modelIndex, int modelSubIndex) {
		// System.out.println(modelIndex + " " + modelSubIndex);
		modelViewIndex = modelIndex;
		modelSubViewIndex = modelSubIndex;
		RefasGraph refasGraph = ((RefasGraph) getGraphComponent().getGraph());
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
		graphEditorFunctions.updateEditor(this.validElements,
				getGraphComponent(), modelViewIndex);
	}

	public void updateView() {
		graphEditorFunctions.updateView(this.validElements,
				getGraphComponent(), modelViewIndex);
		// perspectiveToolBar.updateButtons();
	}

	/**
	 * @param appTitle
	 * @param component
	 *            New constructor to load directly files and perspectives
	 * @throws FeatureModelException
	 */
	public static VariamosGraphEditor loader(String appTitle, String file,
			String perspective) throws FeatureModelException {
		AbstractModel abstractModel = null;
		sematicSyntaxObject = new SemanticPlusSyntax();

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
			VariamosGraphEditor vge = new VariamosGraphEditor(
					"Configurator - VariaMos", new VariamosGraphComponent(
							plGraph, Color.WHITE), persp, abstractModel);
			return vge;
		} else if (perspective.equals("modeling")) {

			System.out.println("Initializing modeling perspective...");
			persp = 2;
			RefasGraph refasGraph = null;
			if (file != null) {
				SXFMReader reader = new SXFMReader();
				abstractModel = reader.readRefasFile(file, new Refas(
						PerspectiveType.modeling));
				refasGraph = new RefasGraph(sematicSyntaxObject, persp);
			} else {
				{
					abstractModel = new Refas(PerspectiveType.modeling);
					refasGraph = new RefasGraph(sematicSyntaxObject, persp);

				}

				// ProductLineGraph plGraph2 = new ProductLineGraph();
				VariamosGraphEditor vge2 = new VariamosGraphEditor(
						"Configurator - VariaMos", new VariamosGraphComponent(
								refasGraph, Color.WHITE), persp, abstractModel);
				vge2.createFrame().setVisible(true);
				vge2.setVisibleModel(0, -1);
				vge2.setDefaultButton();
				vge2.setPerspective(2);
				vge2.setGraphEditorFunctions(new RefasGraphEditorFunctions(vge2));
				vge2.updateEditor();

				System.out.println("Modeling perspective initialized.");
				return vge2;
			}
		} else if (perspective.equals("metamodeling")) {

			System.out.println("Initializing meta-modeling perspective...");
			// todo: change for metamodeling
			persp = 3;
			RefasGraph refasGraph = null;
			if (file != null) {
				SXFMReader reader = new SXFMReader();
				abstractModel = reader.readRefasFile(file, new Refas(
						PerspectiveType.modeling));
				refasGraph = new RefasGraph(sematicSyntaxObject, persp);
			} else {
				{
					abstractModel = new Refas(PerspectiveType.modeling);
					refasGraph = new RefasGraph(sematicSyntaxObject, persp);

				}

				// ProductLineGraph plGraph2 = new ProductLineGraph();
				VariamosGraphEditor vge2 = new VariamosGraphEditor(
						"Configurator - VariaMos", new VariamosGraphComponent(
								refasGraph, Color.WHITE), persp, abstractModel);
				vge2.createFrame().setVisible(true);
				vge2.setVisibleModel(0, -1);
				vge2.setPerspective(3);
				vge2.setGraphEditorFunctions(new RefasGraphEditorFunctions(vge2));
				vge2.updateEditor();
				mxCell root = new mxCell();
				root.insert(new mxCell());
				refasGraph.getModel().setRoot(root);
				System.out.println("Meta-Modeling perspective initialized.");
				return vge2;
			}
		}
		return null;
	}

	public static SemanticPlusSyntax getSematicSyntaxObject() {
		return sematicSyntaxObject;
	}

	public static void setSematicSyntaxObject(
			SemanticPlusSyntax sematicSyntaxObject) {
		VariamosGraphEditor.sematicSyntaxObject = sematicSyntaxObject;
	}

	public void editModel(AbstractModel pl) {
		// productLineIndex.reset();
		AbstractGraph abstractGraph = null;
		// todo: review other perspectives
		if (perspective == 0)
			abstractGraph = new ProductLineGraph();
		if (perspective == 2 || perspective == 1 || perspective == 3
				|| perspective == 4)
			abstractGraph = new RefasGraph(sematicSyntaxObject, perspective);
		// abstractGraph = (AbstractGraph) getGraphComponent()
		// .getGraph();
		((VariamosGraphComponent) graphComponent).updateGraph(abstractGraph);
		registerEvents();

		abstractGraph.setModel(pl);
	}

	public void resetView() {
		updateEditor();
		mxGraph graph = getGraphComponent().getGraph();
		// Check modified flag and display save dialog
		mxCell root = new mxCell();
		root.insert(new mxCell());
		graph.getModel().setRoot(root);
		if (perspective == 2) {
			setGraphEditorFunctions(new RefasGraphEditorFunctions(this));
			((Refas) this.getEditedModel()).clear();
			((RefasGraph) graph).defineInitialGraph();

		}
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

				if (cell.getValue() instanceof EditableElement) {
					EditableElement elm = (EditableElement) cell.getValue();
					editPropertiesRefas(elm);
					// getGraphComponent().scrollCellToVisible(cell, true);
				}
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
	public Component getExtensionsTab(final EditableElement elm) {
		if (extensionTabs != null)
			return extensionTabs;

		messagesArea = new JTextArea("Output");
		messagesArea.setEditable(false);

		// elementDesPropPanel = new JPanel();
		// elementDesPropPanel.setLayout(new SpringLayout());

		elementDesignPanel = new ElementDesignPanel();

		elementConfigPropPanel = new JPanel();
		elementConfigPropPanel.setLayout(new SpringLayout());

		elementExpressionPanel = new JPanel();
		// elementExpressionPanel.setLayout(new SpringLayout());

		expressionsArea = new JTextArea("Element Expressions");
		expressionsArea.setEditable(false);
		// elementExpressionPanel.add(expressionsArea);

		elementSimPropPanel = new JPanel();
		elementSimPropPanel.setLayout(new SpringLayout());

		configurator = new ConfiguratorPanel();

		configuratorProperties = new ConfigurationPropertiesTab();

		expressions = new RefasExpressionPanel(this, elm);

		// if (getPerspective() == 2) {

		// }

		// Bottom panel : Properties, Messages and Configuration
		extensionTabs = new JTabbedPane(JTabbedPane.TOP,
				JTabbedPane.SCROLL_TAB_LAYOUT);
		extensionTabs.addTab(mxResources.get("elementExpressionTab"),
				new JScrollPane(expressions));
		extensionTabs.addTab(mxResources.get("messagesTab"), new JScrollPane(
				messagesArea));
		extensionTabs.addTab(mxResources.get("modelConfPropTab"),
				configuratorProperties.getScrollPane());
		extensionTabs.addTab(mxResources.get("configurationTab"),
				new JScrollPane(configurator));
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
						if (elm instanceof InstConcept) {
							String iden = ((InstConcept) elm)
									.getTransSupportMetaElement()
									.getIdentifier();
							// System.out.println(iden);
							if (iden.equals("CG")
									|| iden.equals("LocalVariable")
									|| iden.equals("GlobalVariable")
									|| iden.equals("ENUM"))
								editableElementType = "var";
							else
								editableElementType = "vertex";
						}
						if (elm instanceof InstPairwiseRelation) {
							editableElementType = "edge";
						}
						if (elm instanceof InstOverTwoRelation) {
							editableElementType = "groupdep";
						}
						MetaExpressionSet metaExpressionSet = refas2hlcl
								.getElementConstraintGroup(
										lastEditableElement.getIdentifier(),
										editableElementType,
										Refas2Hlcl.CONF_EXEC);

						expressions.configure(getEditedModel(),
								metaExpressionSet,
								(InstElement) lastEditableElement);
						updateExpressions = false;
					}
				}
				// System.out.println(tabIndex);
				((MainFrame) getFrame()).waitingCursor(false);
			}

		});
		return extensionTabs;
	}

	private void updateVisibleProperties(final EditableElement elm) {
		extensionTabs.removeAll();
		if (elm != null) {
			// extensionTabs.addTab(mxResources.get("elementDisPropTab"),
			// new JScrollPane(elementDesPropPanel));
			extensionTabs.addTab(mxResources.get("elementDisPropTab"),
					new JScrollPane(elementDesignPanel));
			if (perspective == 4) {
				extensionTabs.addTab(mxResources.get("elementConfPropTab"),
						new JScrollPane(elementConfigPropPanel));
				// extensionTabs.addTab(mxResources.get("elementExpressionTab"),
				// new JScrollPane(elementExpressionPanel));
				extensionTabs.addTab(mxResources.get("elementSimPropTab"),
						new JScrollPane(elementSimPropPanel));
			}
			if (perspective == 2 || perspective == 4) {
				extensionTabs.addTab(mxResources.get("elementExpressionTab"),
						new JScrollPane(expressionsArea));
			}
			extensionTabs.addTab(mxResources.get("editExpressionsTab"),
					new JScrollPane(expressions));
		}
		extensionTabs.addTab(mxResources.get("messagesTab"), new JScrollPane(
				messagesArea));

		extensionTabs.addTab(mxResources.get("modelConfPropTab"),
				configuratorProperties.getScrollPane());
		extensionTabs.addTab(mxResources.get("configurationTab"),
				new JScrollPane(configurator));

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
		return messagesArea;
	}

	public ConfiguratorPanel getConfigurator() {
		return configurator;
	}

	public void editModelReset() {
		productLineIndex.reset();
		if (perspective == 0)
			editModel(new ProductLine());
		else {
			// TODO fix when the syntax o semantic model is loaded -> update
			// dependent models.
			Refas refas = new Refas(PerspectiveType.modeling,
					((Refas) getEditedModel()).getSyntaxRefas(),
					((Refas) getEditedModel()).getSemanticRefas());
			refas2hlcl = new Refas2Hlcl(refas);
			editModel(refas);
			configurator.setRefas2hlcl(refas2hlcl);
		}

	}

	public void populateIndex(ProductLine pl) {

		// productLineIndex.populate(pl);
		AbstractGraph plGraph = (AbstractGraph) getGraphComponent().getGraph();
		plGraph.buildFromProductLine2(pl, productLineIndex);
		// ((mxGraphModel) plGraph.getModel()).clear();
		// plGraph.setProductLine(pl);

	}

	public AbstractModel getEditedModel() {
		if (perspective == 0)
			return ((AbstractGraph) getGraphComponent().getGraph())
					.getProductLine();
		else
			return ((AbstractGraph) getGraphComponent().getGraph()).getRefas();

	}

	// jcmunoz: new method for REFAS

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void editPropertiesRefas(final EditableElement elm) {
		try {

			updateVisibleProperties(elm);
			if (recursiveCall)
				return;
			elementDesignPanel.editorProperties(this, elm);
			this.extensionTabs.repaint();
			// elementDesPropPanel.removeAll();
			elementConfigPropPanel.removeAll();
			elementExpressionPanel.removeAll();
			elementSimPropPanel.removeAll();

			if (elm == null) {
				if (lastTabIndex != 0)
					lastTabIndex = 0;
				else {
					tabIndex = 0;
					extensionTabs.setSelectedIndex(0);

				}
				return;
			} else {
				recursiveCall = true;
				((MainFrame) getFrame()).waitingCursor(true);
				if (lastEditableElement != elm) {
					lastEditableElement = elm;
					// TODO workaround to update after simul
					updateExpressions = true;
				}
				if (extensionTabs.getTabCount() > tabIndex && tabIndex >= 0) {
					extensionTabs.setSelectedIndex(tabIndex);
					extensionTabs.getSelectedComponent().repaint();
				}
				JPanel elementConfPropSubPanel = new JPanel(new SpringLayout());
				JPanel elementSimPropSubPanel = new JPanel(new SpringLayout());

				List<InstAttribute> editables = elm.getEditableVariables();

				List<InstAttribute> visible = elm.getVisibleVariables();

				RefasWidgetFactory factory = new RefasWidgetFactory(this);

				int configurationPanelElements = 0, simulationPanelElements = 1;

				if (elm instanceof InstConcept) {
					String iden = ((InstConcept) elm)
							.getTransSupportMetaElement().getIdentifier();
					// System.out.println(iden);
					if (iden.equals("CG") || iden.equals("LocalVariable")
							|| iden.equals("GlobalVariable")
							|| iden.equals("ENUM"))

						editableElementType = "var";
					else

						editableElementType = "vertex";
				}
				if (elm instanceof InstPairwiseRelation) {
					if (((InstPairwiseRelation) elm).getSourceRelations()
							.size() == 0) {
						((MainFrame) getFrame()).waitingCursor(false);
						// TODO workaround for non supported relations - delete
						// after fix
						return;
					}

					editableElementType = "edge";
				}
				if (elm instanceof InstOverTwoRelation) {
					editableElementType = "groupdep";
				}
				if (editableElementType != null)
					if (this.perspective == 2)

						expressionsArea.setText(refas2hlcl
								.getElementTextConstraints(elm.getIdentifier(),
										editableElementType,
										Refas2Hlcl.CONF_EXEC));
				if (this.perspective == 4)

					expressionsArea
							.setText(refas2hlcl.getElementTextConstraints(
									elm.getIdentifier(), editableElementType,
									Refas2Hlcl.SIMUL_EXEC));
				// expressions.configure(
				// getEditedModel(),
				// refas2hlcl.getElementConstraintGroup(
				// elm.getIdentifier(), type), (InstElement) elm);

				// TODO split in two new classes, one for each panel
				for (InstAttribute v : visible) {
					Map<String, MetaElement> mapElements = null;
					if (elm instanceof InstPairwiseRelation) {
						InstPairwiseRelation instPairwise = (InstPairwiseRelation) elm;
						mapElements = ((Refas) getEditedModel())
								.getSyntaxRefas().getValidPairwiseRelations(
										instPairwise.getSourceRelations()
												.get(0)
												.getTransSupportMetaElement(),
										instPairwise.getTargetRelations()
												.get(0)
												.getTransSupportMetaElement(),
										true);
					}
					v.updateValidationList((InstElement) elm, mapElements);

					final WidgetR w = factory.getWidgetFor(v);

					if (w == null) {
						recursiveCall = false;
						System.err.print("No Widget found for " + v);
						return;
					}
					// TODO: Add listeners to w.

					w.getEditor().addFocusListener(new FocusListener() {
						@Override
						public void focusLost(FocusEvent arg0) {
							// Makes it pull the values.
							EditableElementAttribute v = w.getInstAttribute();
							if (v.getAttributeType().equals("String"))
								v.setValue(AbstractElement.multiLine(
										v.toString(), 15));
							// Divide lines every 15 characters (aprox.)
							onVariableEdited(elm, v);
						}

						@Override
						public void focusGained(FocusEvent arg0) {
						}
					});

					w.getEditor().addPropertyChangeListener(
							new PropertyChangeListener() {

								@Override
								public void propertyChange(
										PropertyChangeEvent evt) {
									if (WidgetPL.PROPERTY_VALUE.equals(evt
											.getPropertyName())) {
										w.getInstAttribute();
										updateExpressions = true;
										onVariableEdited(elm,
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
					w.editVariable(v);
					if (!editables.contains(v)) {
						w.getEditor().setEnabled(false);
					}
					// GARA
					// variablesPanel.add(new JLabel(v.getName() + ":: "));
					if (v.getAttribute() instanceof SimulationStateAttribute) {
						elementSimPropSubPanel.add(new JLabel(v
								.getDisplayName() + ": "));
						elementSimPropSubPanel.add(w);

						if (v.isAffectProperties()) {
							JComponent wc = w.getEditor();
							if (wc instanceof ItemSelectable)
								((ItemSelectable) wc)
										.addItemListener(new ItemListener() {
											@Override
											public void itemStateChanged(
													ItemEvent e) {
												editPropertiesRefas(elm);
												updateExpressions = true;
											}
										});
							JButton button = new JButton("Validate");
							button.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									if (!recursiveCall) {
										clearNotificationBar();
										executeSimulation(true,
												Refas2Hlcl.CONF_EXEC);
										editPropertiesRefas(elm);
										updateExpressions = true;
									}
								}
							});
							elementSimPropSubPanel.add(button);
						} else
							elementSimPropSubPanel.add(new JPanel());

						simulationPanelElements++;
					} else if (v.getAttribute() instanceof SimulationConfigAttribute) {
						elementConfPropSubPanel.add(new JLabel(v
								.getDisplayName() + ": "));
						elementConfPropSubPanel.add(w);

						if (v.isAffectProperties()) {
							if (w.getEditor() instanceof JCheckBox)
								((JCheckBox) w.getEditor())
										.addActionListener(new ActionListener() {
											public void actionPerformed(
													ActionEvent e) {
												AbstractButton aButton = (AbstractButton) e
														.getSource();
												boolean selected = aButton
														.getModel()
														.isSelected();
												// System.out.println(selected);
												((JCheckBox) w.getEditor())
														.repaint();
												new Thread() {
													public void run() {
														editPropertiesRefas(elm);
													}
												}.start();
											}
										});
							JButton button = new JButton("Configure");
							button.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									clearNotificationBar();
									executeSimulation(true,
											Refas2Hlcl.CONF_EXEC);
									editPropertiesRefas(elm);
									updateExpressions = true;
								}
							});
							elementConfPropSubPanel.add(button);
						} else
							elementConfPropSubPanel.add(new JPanel());

						configurationPanelElements++;
					}

				}

				SpringUtilities.makeCompactGrid(elementSimPropSubPanel,
						simulationPanelElements / 2, 6, 4, 4, 4, 4);

				SpringUtilities.makeCompactGrid(elementConfPropSubPanel,
						configurationPanelElements, 3, 4, 4, 4, 4);

				elementConfigPropPanel.add(elementConfPropSubPanel);
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
			e.printStackTrace();
		} finally {
			recursiveCall = false;
		}
	}

	public void refresh() {
		try {
			((RefasGraph) getGraphComponent().getGraph())
					.refreshVariable(lastEditableElement);
		} catch (Exception e) {
			lastEditableElement = ((RefasGraph) this.getGraphComponent()
					.getGraph()).getEditableElement();
			try {
				if (lastEditableElement != null)
					((RefasGraph) getGraphComponent().getGraph())
							.refreshVariable(lastEditableElement);
			} catch (Exception p) {
				System.out.println("Update error");
			}

		}

	}

	public void refreshElement(EditableElement elm) {
		List<InstAttribute> visible = elm.getVisibleVariables();
		RefasWidgetFactory factory = new RefasWidgetFactory(this);
		for (InstAttribute v : visible) {
			Map<String, MetaElement> mapElements = null;
			if (elm instanceof InstPairwiseRelation) {
				InstPairwiseRelation instPairwise = (InstPairwiseRelation) elm;
				mapElements = ((Refas) getEditedModel()).getSyntaxRefas()
						.getValidPairwiseRelations(
								instPairwise.getSourceRelations().get(0)
										.getTransSupportMetaElement(),
								instPairwise.getTargetRelations().get(0)
										.getTransSupportMetaElement(), true);
			}
			v.updateValidationList((InstElement) elm, mapElements);
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

	protected void onVariableEdited(EditableElement editableElement,
			EditableElementAttribute instAttribute) {
		if (editableElement instanceof InstConcept) {
			MetaElement editableMetaElement = ((InstConcept) editableElement)
					.getEditableMetaElement();
			if (editableMetaElement != null) {
				if (instAttribute.getIdentifier().equals("Identifier"))
					editableMetaElement.setIdentifier((String) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Visible"))
					editableMetaElement.setVisible((boolean) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Name"))
					editableMetaElement.setName((String) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Style"))
					editableMetaElement.setStyle((String) instAttribute
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
					((MetaConcept) editableMetaElement)
							.setTopConcept((boolean) instAttribute.getValue());
				if (instAttribute.getIdentifier().equals("BackgroundColor"))
					((MetaConcept) editableMetaElement)
							.setBackgroundColor((String) instAttribute
									.getValue());
				if (instAttribute.getIdentifier().equals("BorderStroke"))
					editableMetaElement.setBorderStroke((int) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Resizable"))
					((MetaConcept) editableMetaElement)
							.setResizable((boolean) instAttribute.getValue());
				if (instAttribute.getIdentifier().equals("value"))
					editableMetaElement
							.setModelingAttributes((Map<String, AbstractAttribute>) instAttribute
									.getValue());
			}
			IntSemanticElement editableSemanticElement = ((InstConcept) editableElement)
					.getEditableSemanticElement();
			if (editableSemanticElement != null) {
				if (instAttribute.getIdentifier().equals("Identifier"))
					editableSemanticElement
							.setIdentifier((String) instAttribute.getValue());
			}
		}
		refresh();
	}

	protected void installToolBar(MainFrame mainFrame, int perspective) {

		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		if (perspective == 3)
			jp.add(new RefasEditorToolBar(this, JToolBar.HORIZONTAL),
					BorderLayout.WEST);
		else
			jp.add(new PLEditorToolBar(this, JToolBar.HORIZONTAL),
					BorderLayout.WEST);
		jp.add(new JLabel(), BorderLayout.CENTER);
		if (mainFrame != null)
			perspectiveToolBar = new PerspectiveToolBar(mainFrame,
					JToolBar.HORIZONTAL, perspective);
		else
			perspectiveToolBar = new PerspectiveToolBar(this,
					JToolBar.HORIZONTAL, perspective);
		jp.add(perspectiveToolBar, BorderLayout.EAST);
		add(jp, BorderLayout.NORTH);
	}

	public final MainFrame getMainFrame() {
		Container contairner1 = this.getParent();

		Container contairner2 = contairner1.getParent();

		Container contairner3 = contairner2.getParent();

		Container contairner4 = contairner3.getParent();

		return (MainFrame) contairner4;

	}

	public void refreshPalette() {
		int i = graphAndRight.getDividerLocation();
		graphAndRight.setDividerLocation(i + 1);
	}

	public void setModelEditor(VariamosGraphEditor modelEditor) {
		this.modelEditor = modelEditor;
	}

	public void updateObjects() {
		if (perspective == 2) {
			mxGraph target = graphComponent.getGraph();
			SharedActions.afterOpenCloneGraph(target, this);
		}
		if (perspective == 4) {

			// executeSimulation(true, Refas2Hlcl.DESIGN_EXEC);

			mxGraph source = modelEditor.getGraphComponent().getGraph();
			mxGraph target = graphComponent.getGraph();
			SharedActions.beforeGraphOperation(source, false,0,-1);
			SharedActions.cloneGraph(source, target, this.getModelViewIndex(),
					this.getModelSubViewIndex());
			// System.out.println(this.getModelViewIndex() + " "
			// + this.getModelSubViewIndex());
			SharedActions.afterOpenCloneGraph(source, this);
			SharedActions.afterOpenCloneGraph(target, this);
			((mxCell) graphComponent.getGraph().getDefaultParent())
					.setValue("simul");
			// Different from null, to display simulation colors

		}
	}

	/**
	 * include execution time
	 */
	@Override
	protected void mouseLocationChanged(MouseEvent e) {
		String solver = "";
		if (!lastSolverInvocations.equals("")) {
			solver = " Last Execution Times(ms) {Total[Solver]}: "
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
					public void invoke(Object source, mxEventObject evt) {
						String solver = "";
						String buffer = (graphComponent.getTripleBuffer() != null) ? ""
								: " (unbufferedX)";
						if (!lastSolverInvocations.equals("")) {
							solver = " Last Execution Times(ms) {Total[Solver]}: "
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

	public void clearSimulation() {

		refas2hlcl.cleanGUIElements();
		try {
			((RefasGraph) getGraphComponent().getGraph())
					.refreshVariable(lastEditableElement);
		} catch (Exception e) {
			lastEditableElement = ((RefasGraph) this.getGraphComponent()
					.getGraph()).getEditableElement();
			try {
				((RefasGraph) getGraphComponent().getGraph())
						.refreshVariable(lastEditableElement);
			} catch (Exception p) {
				System.out.println("Update error");
			}

		}

	}

	public void executeSimulation(boolean first, int type) {
		executeSimulation(first, type, true, "");
	}

	public void executeSimulation(boolean first, int type, boolean update,
			String element) {

		long iniTime = System.currentTimeMillis();
		long iniSTime = 0;
		long endSTime = 0;
		((MainFrame) getFrame()).waitingCursor(true);
		boolean result = false;
		iniSTime = System.currentTimeMillis();
		if (first || lastConfiguration== null) {
			result = refas2hlcl.execute(element, Refas2Hlcl.ONE_SOLUTION, type);
		} else {
			result = refas2hlcl
					.execute(element, Refas2Hlcl.NEXT_SOLUTION, type);
			Configuration currentConfiguration = refas2hlcl.getConfiguration();
			if (result) {
				List<String> modifiedIdentifiers = compareSolutions(
						lastConfiguration, currentConfiguration);
				// System.out.println(modifiedIdentifiers);
			}
		}
		lastConfiguration = refas2hlcl.getConfiguration();
		endSTime = System.currentTimeMillis();
		if (result) {
			if (update) {
				refas2hlcl.updateGUIElements(null);
				messagesArea.setText(refas2hlcl.getText());
				// bringUpTab(mxResources.get("elementSimPropTab"));
				editPropertiesRefas(lastEditableElement);
			}

		} else {
			if (first) {
				switch (type) {
				case Refas2Hlcl.DESIGN_EXEC:
					JOptionPane
							.showMessageDialog(
									frame,
									"Last validated change makes the model inconsistent."
											+ " \n Please review the restrictions defined and "
											+ "try again. \nModel visual representation was not updated.",
									"Simulation Execution Error",
									JOptionPane.INFORMATION_MESSAGE, null);
					break;
				case Refas2Hlcl.CONF_EXEC:
					JOptionPane
							.showMessageDialog(
									frame,
									"Last configuration change validated makes the model "
											+ "\n inconsistent. Please review the selection and "
											+ "try again. \nAttributes values were not updated.",
									"Simulation Execution Error",
									JOptionPane.INFORMATION_MESSAGE, null);
					break;
				case Refas2Hlcl.SIMUL_EXEC:
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
				JOptionPane.showMessageDialog(frame, "No more solutions found",
						"Simulation Message", JOptionPane.INFORMATION_MESSAGE,
						null);
		}
		refresh();
		// updateObjects();
		((MainFrame) getFrame()).waitingCursor(false);
		long endTime = System.currentTimeMillis();
		lastSolverInvocations += "NormalExec: " + (endTime - iniTime) + "["
				+ (endSTime - iniSTime) + "]" + " -- ";

	}

	private List<String> compareSolutions(Configuration lastConfiguration,
			Configuration currentConfiguration) {
		List<String> out = new ArrayList<String>();
		Map<String, Integer> lastConfig = lastConfiguration.getConfiguration();
		Map<String, Integer> currentConfig = currentConfiguration
				.getConfiguration();
		for (String solution : lastConfig.keySet())
			if (lastConfig.get(solution) != currentConfig.get(solution))
				out.add(solution);
		return out;
	}

	public void verify() {
		verify(defects);
	}

	public void verify(List<String> defect) {
		boolean errors = false;
		((MainFrame) getFrame()).waitingCursor(true);

		List<String> verifList = new ArrayList<String>();
		List<String> actionList = new ArrayList<String>();

		verifList.add("Root");
		verifList.add("Parent");
		verifList.add("Core");

		actionList.add("Err");
		actionList.add("Err");
		actionList.add("Upd");
		List<String> verifMessageList = new ArrayList<String>();
		verifMessageList
				.add(" roots identified.\n Please keep only one of them.");
		verifMessageList
				.add(" concepts without a parent or with more than one parent identified.\n Please add/remove appropiated relations.");
		verifMessageList
				.add(" false optional concept(s) identified. Please review required attributes and relations.");
		verifMessageList
				.add(" false optional concept(s) identified. Please review required attributes and relations.");
		List<String> verifHintList = new ArrayList<String>();
		verifHintList
				.add("This is a root concept. More than one root concept identified.");
		verifHintList.add("This concept requires a parent.");
		verifHintList
				.add("This concept is a false optional. Please review required attribute or relations.");
		verifHintList
				.add("This concept is a false optional. Please review required attribute or relations.");

		int posList = 0;

		List<String> updateList = new ArrayList<String>();
		updateList.add("Core");
		updateList.add("Selected");
		// updateList.add("Satisfied");
		// updateList.add("ConfigSatisfied");
		updateList.add("ConfigSelected");
		// updateList.add("");

		List<String> outMessageList = new ArrayList<String>();
		if (defect.size() > 0)
			for (String verifElement : verifList) {
				if (defect == null || defect.contains(verifElement)
						|| verifElement.equals("Core")) {
					String verifMessage = verifMessageList.get(posList);
					String verifHint = verifMessageList.get(posList);
					if (actionList.get(posList).equals("Err")) {

						outMessageList.add(verifyDefects(verifElement,
								verifMessage, verifHint));
					} else {
						if (defect == null || defect.contains("Core")
								|| defect.contains("Dead")
								|| defect.contains("FalseOpt"))
							outMessageList
									.addAll(updateModel(verifElement,
											verifMessage, verifHint,
											updateList, defect));
					}
				}
				posList++;
			}
		if (defect == null || defect.contains("Simul"))
			executeSimulation(true, Refas2Hlcl.CONF_EXEC, false, "Simul");

		for (String outMessage : outMessageList) {
			if (outMessage != null) {
				JOptionPane.showMessageDialog(frame, outMessage,
						"Verification Message",
						JOptionPane.INFORMATION_MESSAGE, null);
				errors = true;
			}
		}
		if (!errors)
			JOptionPane.showMessageDialog(frame, "No errors found",
					"Verification Message", JOptionPane.INFORMATION_MESSAGE,
					null);
		refresh();

		((MainFrame) getFrame()).waitingCursor(false);
	}

	public List<String> updateModel(String element, String verifMessage,
			String verifHint, List<String> attributes, List<String> defect) {
		HlclFactory f = new HlclFactory();
		List<String> out = new ArrayList<String>();
		long iniTime = System.currentTimeMillis();
		long iniSTime = 0;
		long endSTime = 0;
		boolean result = false;
		iniSTime = System.currentTimeMillis();
		result = refas2hlcl.execute(element, Refas2Hlcl.ONE_SOLUTION,
				Refas2Hlcl.VAL_UPD_EXEC);
		endSTime = System.currentTimeMillis();
		if (result) {
			refas2hlcl.updateGUIElements(attributes);
			((MainFrame) getFrame()).waitingCursor(true);
			Map<String, Integer> currentResult = refas2hlcl.getResult();
			// System.out.println(currentResult);
			List<String> falseOptIdentifiers = getNewIdentifiers(currentResult,
					refas2hlcl.getResult());

			// System.out.println(falseOptIdentifiers);
			List<String> freeIdentifiers = getFreeIdentifiers(currentResult);
			List<String> deadIdentifiers = new ArrayList<String>();

			Set<Identifier> identifiers = new HashSet<Identifier>();

			for (String freeIndentifier : freeIdentifiers) {
				if (!freeIndentifier.startsWith("FeatOverTwo"))
					identifiers.add(f.newIdentifier(freeIndentifier));
			}

			if (freeIdentifiers.size() > 0
					&& (defect == null || defect.contains("Dead")
							|| defect.contains("FalseOpt") || defect
								.contains("Core"))) {
				try {
					IntDefectsVerifier defectVerifier = new DefectsVerifier(
							refas2hlcl.getHlclProgram("FalseOpt2",
									Refas2Hlcl.VAL_UPD_EXEC),
							SolverEditorType.SWI_PROLOG);

					List<Defect> falseOptionalList = null;

					if (defect == null || defect.contains("FalseOpt")
							|| defect.contains("Core"))
					// Indentify false optional from non structural
					// relations
					{
						falseOptionalList = defectVerifier
								.getFalseOptionalElements(identifiers);
						if (falseOptionalList.size() > 0) {
							List<String> falseOptIdentOthers = new ArrayList<String>();
							for (Defect conceptVariable : falseOptionalList) {
								String[] conceptId = conceptVariable.getId()
										.split("_");
								falseOptIdentOthers.add(conceptId[0]);
							}
							falseOptIdentifiers.addAll(falseOptIdentOthers);
						}
					}

					endSTime = System.currentTimeMillis();

					freeIdentifiers.removeAll(falseOptIdentifiers);

					Set<Identifier> identDeadElements = new HashSet<Identifier>();

					for (String freeIndentifier : freeIdentifiers) {
						if (!freeIndentifier.startsWith("FeatOverTwo"))
							identDeadElements.add(f
									.newIdentifier(freeIndentifier));
					}

					List<Defect> deadIndetifiersList = null;

					deadIndetifiersList = defectVerifier
							.getDeadElements(identDeadElements);

					endSTime = System.currentTimeMillis();

					if (defect == null || defect.contains("Dead"))
						if (deadIndetifiersList.size() > 0) {
							for (Defect conceptVariable : deadIndetifiersList) {
								String[] conceptId = conceptVariable.getId()
										.split("_");
								deadIdentifiers.add(conceptId[0]);
							}
							out.add(deadIndetifiersList.size()
									+ " dead elements identified.");
						}

					// refas2hlcl.updateCoreConcepts(outIdentifiers);
				} catch (FunctionalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			Set<String> uniqueIdentifiers = new HashSet<String>();
			uniqueIdentifiers.addAll(falseOptIdentifiers);
			// System.out.println(uniqueIdentifiers);

			if (defect == null || defect.contains("FalseOpt")) {
				if (uniqueIdentifiers.size() > 0)
					out.add(uniqueIdentifiers.size() + verifMessage);
				refas2hlcl.updateErrorMark(uniqueIdentifiers, element,
						verifHint);
			}

			if (defect == null || defect.contains("Core"))
				refas2hlcl.updateCoreConcepts(uniqueIdentifiers);

			if (defect == null || defect.contains("Dead"))
				refas2hlcl.updateDeadConcepts(deadIdentifiers);

			messagesArea.setText(refas2hlcl.getText());
			// bringUpTab(mxResources.get("elementSimPropTab"));
			editPropertiesRefas(lastEditableElement);
		} else {
			long endTime = System.currentTimeMillis();
			lastSolverInvocations += element + "Exec: " + (endTime - iniTime)
					+ "[" + (endSTime - iniSTime) + "]" + " -- ";
			out.add("Last validated change makes the model inconsistent."
					+ " \n Please review the restrictions defined and "
					+ "try again. \nModel visual representation was not updated.");
		}

		// updateObjects();
		long endTime = System.currentTimeMillis();
		lastSolverInvocations += element + "Exec: " + (endTime - iniTime) + "["
				+ (endSTime - iniSTime) + "]" + " -- ";
		return out;
	}

	private List<String> getFreeIdentifiers(Map<String, Integer> currentResult) {
		List<String> out = new ArrayList<String>();
		for (String id : currentResult.keySet()) {
			String[] o = id.split("_");
			if (o[1].equals("Selected") && currentResult.get(id) == 0)
				out.add(id);

		}
		return out;
	}

	private List<String> getNewIdentifiers(Map<String, Integer> currentResult,
			Map<String, Integer> lastResult) {
		List<String> out = new ArrayList<String>();
		for (String id : currentResult.keySet()) {
			String[] o = id.split("_");
			if (o[1].equals("Selected") && lastResult.get(id) == 0
					&& currentResult.get(id) == 1)
				out.add(o[0]);

		}
		return out;
	}

	private String verifyDefects(String verifElement, String verifMessage,
			String verifHint) {
		String outMessage = null;
		long iniTime = System.currentTimeMillis();
		long iniSTime = 0;
		long endSTime = 0;
		try {

			List<BooleanExpression> verify = refas2hlcl
					.verityTest(verifElement);
			HlclProgram relaxed = refas2hlcl.relaxedTest(verifElement);
			HlclProgram fixed = refas2hlcl.compulsoryTest(verifElement);
			Defect defect = new Defect(verify);
			defect.setDefectType(DefectType.SEMANTIC_SPECIFIC_DEFECT);
			HlclProgram modelToVerify = new HlclProgram();
			modelToVerify.addAll(verify);
			modelToVerify.addAll(relaxed);
			modelToVerify.addAll(fixed);
			iniSTime = System.currentTimeMillis();
			IntDefectsVerifier verifier = new DefectsVerifier(modelToVerify,
					SolverEditorType.SWI_PROLOG);
			// The model has two or more roots
			Defect voidModel = verifier.isVoid();

			Set<String> outIdentifiers = new TreeSet<String>();
			if (voidModel != null) {

				IntCauCosAnalyzer cauCosAnalyzer = new CauCosAnayzer();
				HlclProgram fixedConstraint = new HlclProgram();
				fixedConstraint.addAll(verify);
				fixedConstraint.addAll(fixed);
				Diagnosis result = cauCosAnalyzer.getCauCos(defect, relaxed,
						fixedConstraint, DefectAnalyzerMode.PARTIAL);
				endSTime = System.currentTimeMillis();
				String defects = "(";
				for (CauCos correction : result.getCorrections()) {
					List<BooleanExpression> corr = correction.getElements();
					for (BooleanExpression expression : corr) {
						Set<Identifier> iden = HlclUtil
								.getUsedIdentifiers(expression);
						Identifier firsIden = iden.iterator().next();
						String[] o = firsIden.getId().split("_");

						if (outIdentifiers.add(o[0]))
							defects += o[0] + ", ";
					}
				}
				// There are more than one root.
				if (!outIdentifiers.isEmpty()) {

					defects = defects.substring(0, defects.length() - 2) + ")";
					outMessage = outIdentifiers.size() + verifMessage + "\n"
							+ defects;
				}
			} else {
				endSTime = System.currentTimeMillis();
			}
			refas2hlcl.updateErrorMark(outIdentifiers, verifElement, verifHint);

			((MainFrame) getFrame()).waitingCursor(true);
			// if (lastEditableElement == null)
			// JOptionPane
			// .showMessageDialog(
			// frame,
			// "Please select any element and after execute the verification.",
			// "Verification Message",
			// JOptionPane.INFORMATION_MESSAGE, null);

		} catch (FunctionalException e) {
			endSTime = System.currentTimeMillis();
			outMessage = e.getMessage();
		} finally {
			long endTime = System.currentTimeMillis();
			lastSolverInvocations += verifElement + " Verif.: "
					+ (endTime - iniTime) + "[" + (endSTime - iniSTime) + "]"
					+ " -- ";
		}

		return outMessage;
	}

	public void updateDefects(String string, boolean b) {
		if (b) {
			if (!defects.contains(string))
				defects.add(string);
		} else
			defects.remove(string);

		System.out.println(string + " " + b);

	}
}
