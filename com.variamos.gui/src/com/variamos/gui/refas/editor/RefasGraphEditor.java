package com.variamos.gui.refas.editor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import com.cfm.productline.AbstractElement;
import com.cfm.productline.Asset;
import com.cfm.productline.Editable;
import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.Variable;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.cfm.productline.type.DomainRegister;
import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.examples.swing.GraphEditor;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.shape.mxStencilShape;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraphSelectionModel;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.EditorPalette;
import com.variamos.gui.maineditor.PerspectiveToolBar;
import com.variamos.gui.maineditor.VariamosGraphComponent;
import com.variamos.gui.pl.editor.ConfiguratorPanel;
import com.variamos.gui.pl.editor.SpringUtilities;
import com.variamos.gui.pl.editor.widgets.Widget;
import com.variamos.gui.refas.editor.widgets.RefasWidgetFactory;
import com.variamos.pl.editor.logic.ConstraintMode;
//import com.variamos.pl.editor.logic.PaletteDatabase;
//import com.variamos.pl.editor.logic.PaletteDatabase.PaletteDefinition;
//import com.variamos.pl.editor.logic.PaletteDatabase.PaletteEdge;
//import com.variamos.pl.editor.logic.PaletteDatabase.PaletteNode;
import com.variamos.refas.concepts.Refas;
//import com.cfm.productline.type.IntegerType;

@SuppressWarnings("serial")
public class RefasGraphEditor extends BasicGraphEditor{

	static
	{
		try
		{
			mxResources.add("com/variamos/gui/maineditor/resources/editor");
		}
		catch (Exception e)
		{
			// ignore
		}
	}
	
	protected DomainRegister domainRegister = new DomainRegister();
	protected RefasGraphTree productLineIndex;
	protected ConfiguratorPanel configurator;
	protected JTextArea messagesArea;
	protected JPanel propertiesPanel;
	
	// Bottom tabs
	protected JTabbedPane extensionTabs;
	
	protected int mode = 0;
	
	public RefasGraphEditor(String appTitle, VariamosGraphComponent component) {
		super(appTitle, component,2);
		
		loadScriptedPalettes();
		//loadPalettes();
		registerEvents();
	}
	
	private void registerEvents() {
		mxGraphSelectionModel selModel = getGraphComponent().getGraph().getSelectionModel();
		selModel.addListener(mxEvent.CHANGE, new mxIEventListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void invoke(Object sender, mxEventObject evt) {				
			//	Collection<mxCell> added = (Collection<mxCell>) evt.getProperty("added");
				//System.out.println("Added: " + added);
				
				Collection<mxCell> removed = (Collection<mxCell>)evt.getProperty("removed");
				//System.out.println("Removed: " + removed);
				
				editProperties(null);
				
				if( removed == null )
					return;
				
				mxCell cell = null;
				if( removed.size() == 1 )
					cell = removed.iterator().next();
				
				//Multiselection case
				if( cell == null )					
					return;
				
				if( cell.getValue() instanceof Editable ){
					Editable elm = (Editable) cell.getValue();
					editProperties(elm);
					getGraphComponent().scrollCellToVisible(cell, true);
				}
			}
		});
	}
	
	private void loadScriptedPalettes() {
		//Load palette from file
//		try {
//			FileReader reader;
//			reader = new FileReader(new File("palettes.pal"));
//			Gson gson = new GsonBuilder().setPrettyPrinting()
//					.serializeNulls()
//					.registerTypeAdapter(Object.class, new NaturalDeserializer())
//					.create();
//			PaletteDatabase db = gson.fromJson(reader, PaletteDatabase.class);
//			loadPaletteDatabase(db);
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
	public static String loadShape(EditorPalette palette, File f) throws IOException{
		String nodeXml = mxUtils.readFile(f.getAbsolutePath());
		addStencilShape(palette, nodeXml, f.getParent() + File.separator);
		return nodeXml;
	}
	
	/**
	 * Loads and registers the shape as a new shape in mxGraphics2DCanvas and
	 * adds a new entry to use that shape in the specified palette
	 * @param palette The palette to add the shape to.
	 * @param nodeXml The raw XML of the shape
	 * @param path The path to the directory the shape exists in
	 * @return the string name of the shape
	 */
	public static String addStencilShape(EditorPalette palette,
			String nodeXml, String path)
	{

		// Some editors place a 3 byte BOM at the start of files
		// Ensure the first char is a "<"
		int lessthanIndex = nodeXml.indexOf("<");
		nodeXml = nodeXml.substring(lessthanIndex);
		mxStencilShape newShape = new mxStencilShape(nodeXml);
		String name = newShape.getName();
		ImageIcon icon = null;

		if (path != null)
		{
			String iconPath = path + newShape.getIconPath();
			icon = new ImageIcon(iconPath);
		}

		// Registers the shape in the canvas shape registry
		mxGraphics2DCanvas.putShape(name, newShape);

		if (palette != null && icon != null)
		{
			palette.addTemplate(name, icon, "shape=" + name, 80, 80, "");
		}

		return name;
	}

	@Override
	protected Component getLeftComponent() {
		productLineIndex = new RefasGraphTree();
		productLineIndex.bind( (RefasGraph) getGraphComponent().getGraph());
		
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
		if( extensionTabs != null)
			return extensionTabs;
		
		messagesArea = new JTextArea("Output");
		messagesArea.setEditable(false);
		
		propertiesPanel = new JPanel();
		propertiesPanel.setLayout(new SpringLayout());
		
		configurator = new ConfiguratorPanel();
		
		//Bottom panel : Properties, Messages and Configuration
		extensionTabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		extensionTabs.addTab(mxResources.get("propertiesTab"), new JScrollPane(propertiesPanel) );
		extensionTabs.addTab(mxResources.get("messagesTab"), new JScrollPane(messagesArea) );
		extensionTabs.addTab(mxResources.get("configurationTab"), new JScrollPane(configurator) );
		
		return extensionTabs;
	}
	
	public void bringUpExtension(String name){
		for(int i = 0; i < extensionTabs.getTabCount(); i++){
			if( extensionTabs.getTitleAt(i).equals(name) ){
				extensionTabs.setSelectedIndex(i);
				return;
			}
		}
	}
	
	public void bringUpTab(String name){
		for(int i = 0; i < extensionTabs.getTabCount(); i++){
			if( extensionTabs.getTitleAt(i).equals(name) ){
				extensionTabs.setSelectedIndex(i);
				return;
			}
		}
	}
	
	public JTextArea getMessagesArea() {
		return messagesArea;
	}
	
	public ConfiguratorPanel getConfigurator() {
		return configurator;
	}
	
	public void editModelReset(){
		productLineIndex.reset();
	}
	
	public void populateIndex(Refas pl){
		
		//productLineIndex.populate(pl);
		RefasGraph plGraph = (RefasGraph)getGraphComponent().getGraph();
		plGraph.buildFromRefas(pl,productLineIndex);
		//((mxGraphModel) plGraph.getModel()).clear();
		//plGraph.setProductLine(pl);
		
	}
		

	public void editRefas(Refas pl){
		//productLineIndex.reset();
		
		RefasGraph plGraph = (RefasGraph)getGraphComponent().getGraph();
		((mxGraphModel) plGraph.getModel()).clear();
		plGraph.setModel(pl);
		
		//productLineIndex.populate(pl);
		
	}
	
	public ProductLine getEditedProductLine(){
		return ((RefasGraph)getGraphComponent().getGraph()).getProductLine();
	}
	
	public void editProperties(final Editable elm){
		propertiesPanel.removeAll();
		
		if( elm == null ){
			bringUpTab("Properties");
			propertiesPanel.repaint();
			return;
		}
		
		JPanel variablesPanel = new JPanel(new SpringLayout());
		
		Variable[] editables = elm.getEditableVariables();
		
		RefasWidgetFactory factory = new RefasWidgetFactory(this);
		for(Variable v : editables){
			final Widget w = factory.getWidgetFor(v);
			if( w == null )
				//Check the problem and/or raise an exception
				return;
			
			//TODO: Add listeners to w.
			w.getEditor().addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent arg0) {
					//Makes it pull the values.
					Variable v = w.getVariable();
					if (v.getType().equals("String"))
						v.setValue(AbstractElement.multiLine(v.toString(), 15));
					System.out.println("Focus Lost: " + v.hashCode() + " val: " + v.getValue());
					//v.setVariableValue("hola");
					onVariableEdited(elm);
				}
				
				@Override
				public void focusGained(FocusEvent arg0) {
					
				}
			});
			
			w.getEditor().addPropertyChangeListener(new PropertyChangeListener() {
				
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if( Widget.PROPERTY_VALUE.equals( evt.getPropertyName() ) ){
						w.getVariable();						
						onVariableEdited(elm);
					}
				}
			});
			w.getEditor().setMinimumSize(new Dimension(50, 30));
			w.getEditor().setMaximumSize(new Dimension(200, 30));
			w.getEditor().setPreferredSize(new Dimension(200, 30));
			w.editVariable(v);
			
			//GARA
			//variablesPanel.add(new JLabel(v.getName() + ":: "));
			variablesPanel.add(new JLabel(v.getName() + ": "));
			variablesPanel.add(w);
		}
		//variablesPanel.setPreferredSize(new Dimension(250, 25 * editables.length));
		SpringUtilities.makeCompactGrid(variablesPanel,
										editables.length, 2,
										4, 4,
										4, 4
										);
		
		propertiesPanel.add(variablesPanel);
		
		JPanel attPanel = new JPanel(new SpringLayout());
		//Fill Attributes Panel (Only for VariabilityElements ) in Properties Panel
		if( elm instanceof VariabilityElement ){
			attPanel.setPreferredSize(new Dimension(150, 150));
			attPanel.add( new JLabel(mxResources.get("attributesPanel")) );
			
			GoalsAttributeList attList = new GoalsAttributeList(this, (VariabilityElement)elm);
			attPanel.add( new JScrollPane(attList) );
			
			SpringUtilities.makeCompactGrid(attPanel,
					2, 1,
					4, 4,
					4, 4
					);
			
			propertiesPanel.add(attPanel);
			
			SpringUtilities.makeCompactGrid(propertiesPanel,
					1, 2,
					4, 4,
					4, 4
					);
		}
		
		propertiesPanel.revalidate();
	}
	
	protected void onVariableEdited(Editable e){
		((RefasGraph)getGraphComponent().getGraph()).refreshVariable(e);
	}
	

	/*
	private void loadPaletteDatabase(PaletteDatabase db) {
		for(PaletteDefinition pal : db.palettes){
			EditorPalette palette = insertPalette(pal.name);
			for(PaletteNode node : pal.nodes ){
				palette.addTemplate(
						node.name,
						new ImageIcon(
								GraphEditor.class
										.getResource(node.icon)),
						node.styleName, node.width, node.height, node.prototype);
			}
			
			for(PaletteEdge edge : pal.edges){
				palette.addEdgeTemplate(
						edge.name,
						new ImageIcon(
								GraphEditor.class
										.getResource(edge.icon)),
						edge.styleName, edge.width, edge.height, edge.value);
			}
		}
	}
	*/

	protected void showGraphPopupMenu(MouseEvent e)
	{
		Point pt = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(),
				graphComponent);
		RefasEditorPopupMenu menu = new RefasEditorPopupMenu(RefasGraphEditor.this);
		menu.show(graphComponent, pt.x, pt.y);

		e.consume();
	}
	
	protected void installToolBar()
	{
		
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
	    jp.add(new RefasEditorToolBar(this, JToolBar.HORIZONTAL),BorderLayout.WEST);
	    jp.add(new JLabel(),BorderLayout.CENTER);
		jp.add(new PerspectiveToolBar(this, JToolBar.HORIZONTAL,perspective),BorderLayout.EAST);

		add (jp, BorderLayout.NORTH);
	}
}
