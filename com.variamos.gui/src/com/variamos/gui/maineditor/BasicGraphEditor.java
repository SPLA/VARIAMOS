package com.variamos.gui.maineditor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.mxgraph.examples.swing.GraphEditor;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxEdgeLabelLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.layout.mxPartitionLayout;
import com.mxgraph.layout.mxStackLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphOutline;
import com.mxgraph.swing.handler.mxKeyboardHandler;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.swing.util.mxMorphing;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUndoManager;
import com.mxgraph.util.mxUndoableEdit;
import com.mxgraph.util.mxUndoableEdit.mxUndoableChange;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.maineditor.AbstractGraphEditorFunctions;
import com.variamos.gui.pl.editor.ProductLineMenuBar;
import com.variamos.gui.refas.editor.ModelButtonAction;
import com.variamos.gui.refas.editor.RefasMenuBar;
import com.variamos.syntaxsupport.metamodel.EditableElement;

/**
 * @author example mxgraph jcmunoz: All this package needs review. Only original
 *         example code should be in all the classes. Calls to ProductLine
 *         commented
 *
 */

public class BasicGraphEditor extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 186586L;

	/**
	 * Adds required resources for i18n
	 */
	static {
		try {
			mxResources.add("com/mxgraph/examples/swing/resources/editor");
		} catch (Exception e) {
			// ignore
		}
	}

	protected JFrame frame;

	/**
	 * 
	 */
	protected mxGraphComponent graphComponent;

	/**
	 * 
	 */
	protected mxGraphOutline graphOutline;

	/**
	 * 
	 */
	protected JTabbedPane modelsTabPane;

	/**
	 * 
	 */
	protected JTabbedPane libraryPane;

	/**
	 * 
	 */
	protected mxUndoManager undoManager;

	/**
	 * 
	 */
	protected String appTitle;

	/**
	 * 
	 */
	protected JLabel statusBar;

	/**
	 * 
	 */
	protected File currentFile;

	/**
	 * Flag indicating whether the current graph has been modified
	 */
	protected boolean modified = false;

	/**
	 * 
	 */
	protected mxRubberband rubberband;

	/**
	 * 
	 */
	protected mxKeyboardHandler keyboardHandler;

	/**
	 * 
	 */

	protected AbstractGraphEditorFunctions graphEditorFunctions;

	protected JSplitPane upperPart;

	protected JSplitPane right;

	protected JSplitPane center;

	protected JSplitPane graphAndRight;

	protected int perspective = 2;

	public void setLayout(int perspective) {
		switch (perspective) {
		case 0:
			center.setDividerLocation(0);
			upperPart.setDividerLocation(150);
			graphAndRight.setDividerLocation(400);
			graphComponent.setBackgroundImage(null);
			frame.setJMenuBar(new ProductLineMenuBar(this));
			break;
		case 1:
			center.setDividerLocation(0);
			upperPart.setDividerLocation(0);
			graphAndRight.setDividerLocation(700);
			graphComponent.setBackgroundImage(null);
			frame.setJMenuBar(new ProductLineMenuBar(this));
			break;
		case 2:
			center.setDividerLocation(25);
			upperPart.setDividerLocation(0);
			graphAndRight.setDividerLocation(700);
			frame.setJMenuBar(new RefasMenuBar(this));
			//frame.setJMenuBar(new ProductLineMenuBar(this));
			graphComponent.setBackgroundImage(null);
			break;
		case 3:
			center.setDividerLocation(0);
			upperPart.setDividerLocation(0);
			graphAndRight.setDividerLocation(700);
			graphComponent
					.setBackgroundImage(new ImageIcon(
							BasicGraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/metamodelv4.png")));
			// frame.setJMenuBar(new RequirementsMenuBar(this));
			frame.setJMenuBar(new RefasMenuBar(this));
			break;
		case 4:
			center.setDividerLocation(0);
			upperPart.setDividerLocation(0);
			graphAndRight.setDividerLocation(700);
			// frame.setJMenuBar(new RequirementsMenuBar(this));
			frame.setJMenuBar(new RefasMenuBar(this));
			break;
		}
		
	}

	public int getPerspective() {
		return perspective;
	}

	public void setPerspective(int perspective) {
		this.perspective = perspective;
	}

	protected mxIEventListener undoHandler = new mxIEventListener() {
		public void invoke(Object source, mxEventObject evt) {
			undoManager.undoableEditHappened((mxUndoableEdit) evt
					.getProperty("edit"));
		}
	};

	/**
	 * 
	 */
	protected mxIEventListener changeTracker = new mxIEventListener() {
		public void invoke(Object source, mxEventObject evt) {
			setModified(true);
		}
	};


	/**
	 * 
	 */

	public BasicGraphEditor() {

	}

	/**
	 * Old constructor only for modeling
	 * @param appTitle
	 * @param component
	 * @param perspective
	 */
	public BasicGraphEditor(String appTitle, mxGraphComponent component,
			int perspective) {
		// Stores and updates the frame title
		this.appTitle = appTitle;
		this.perspective = perspective;

		// Stores a reference to the graph and creates the command history
		graphComponent = component;

		undoManager();
		modelsTabPane = new JTabbedPane();

		// Creates the graph outline component
		graphOutline = new mxGraphOutline(graphComponent);

		// Creates the library pane that contains the tabs with the palettes
		libraryPane = new JTabbedPane();

		// Creates the inner split 1 pane that contains the library with the
		// palettes and the graph outline on the left side of the window
		JPanel center2 = new JPanel();
		center2.setLayout(new BorderLayout());
		center2.add(graphComponent, BorderLayout.CENTER);
		add(modelsTabPane, BorderLayout.NORTH);
		center = new JSplitPane(JSplitPane.VERTICAL_SPLIT, modelsTabPane,
				graphComponent);
		center.setDividerLocation(70);
		center.setResizeWeight(0);
		center.setDividerSize(6);
		center.setBorder(null);

		// Creates the inner split 2 pane that contains the library with the
		// palettes and the graph outline on the left side of the window
		right = new JSplitPane(JSplitPane.VERTICAL_SPLIT, libraryPane,
				graphOutline);
		right.setDividerLocation(230);
		right.setResizeWeight(1);
		right.setDividerSize(6);
		right.setBorder(null);

		// Creates the outer split pane that contains the inner split 2 pane and
		// the inner split 1 on the right side of the window
		graphAndRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, center,
				right);
		graphAndRight.setOneTouchExpandable(true);
		graphAndRight.setDividerLocation(500);
		graphAndRight.setResizeWeight(1);
		graphAndRight.setDividerSize(6);
		graphAndRight.setBorder(null);

		// Creates another split for the west component
		upperPart = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				getLeftComponent(), graphAndRight);
		upperPart.setOneTouchExpandable(false);
		upperPart.setDividerLocation(150);
		upperPart.setDividerSize(6);
		upperPart.setBorder(null);

		JSplitPane everything = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				upperPart, getExtensionsTab(null));
		everything.setOneTouchExpandable(false);
		everything.setDividerLocation(400);
		everything.setResizeWeight(1);
		everything.setDividerSize(6);
		everything.setBorder(null);

		// Creates the status bar
		statusBar = createStatusBar();

		// Display some useful information about repaint events
		installRepaintListener();

		// Puts everything together
		setLayout(new BorderLayout());
		add(everything, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
		installToolBar(null,0);

		// Installs rubberband selection and handling for some special
		// keystrokes such as F2, Control-C, -V, X, A etc.
		installHandlers();
		installListeners();
		updateTitle();
	}
	
	/**
	 * new constructor only for all perspectives
	 * @param appTitle
	 * @param component
	 * @param perspective
	 */
	public BasicGraphEditor(MainFrame mainFrame, String appTitle, mxGraphComponent component, int perspective) {

		this.frame = mainFrame;
		// Stores and updates the frame title
		this.appTitle = appTitle;

		// Stores a reference to the graph and creates the command history
		graphComponent = component;

		this.perspective = perspective;
		undoManager();
		modelsTabPane = new JTabbedPane();

		// Creates the graph outline component
		graphOutline = new mxGraphOutline(graphComponent);

		// Creates the library pane that contains the tabs with the palettes
		libraryPane = new JTabbedPane();

		// Creates the inner split 1 pane that contains the library with the
		// palettes and the graph outline on the left side of the window
		JPanel center2 = new JPanel();
		center2.setLayout(new BorderLayout());
		center2.add(graphComponent, BorderLayout.CENTER);
		add(modelsTabPane, BorderLayout.NORTH);
		center = new JSplitPane(JSplitPane.VERTICAL_SPLIT, modelsTabPane,
				graphComponent);
		center.setDividerLocation(70);
		center.setResizeWeight(0);
		center.setDividerSize(6);
		center.setBorder(null);

		// Creates the inner split 2 pane that contains the library with the
		// palettes and the graph outline on the left side of the window
		right = new JSplitPane(JSplitPane.VERTICAL_SPLIT, libraryPane,
				graphOutline);
		right.setDividerLocation(230);
		right.setResizeWeight(1);
		right.setDividerSize(6);
		right.setBorder(null);

		// Creates the outer split pane that contains the inner split 2 pane and
		// the inner split 1 on the right side of the window
		graphAndRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, center,
				right);
		graphAndRight.setOneTouchExpandable(true);
		graphAndRight.setDividerLocation(500);
		graphAndRight.setResizeWeight(1);
		graphAndRight.setDividerSize(6);
		graphAndRight.setBorder(null);

		// Creates another split for the west component
		upperPart = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				getLeftComponent(), graphAndRight);
		upperPart.setOneTouchExpandable(false);
		upperPart.setDividerLocation(150);
		upperPart.setDividerSize(6);
		upperPart.setBorder(null);

		JSplitPane everything = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				upperPart, getExtensionsTab(null));
		everything.setOneTouchExpandable(false);
		everything.setDividerLocation(400);
		everything.setResizeWeight(1);
		everything.setDividerSize(6);
		everything.setBorder(null);

		// Creates the status bar
		statusBar = createStatusBar();

		// Display some useful information about repaint events
		installRepaintListener();

		// Puts everything together
		setLayout(new BorderLayout());
		add(everything, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
		installToolBar(mainFrame, perspective);

		// Installs rubberband selection and handling for some special
		// keystrokes such as F2, Control-C, -V, X, A etc.
		installHandlers();
		installListeners();
		updateTitle();
	}
	
	protected void reinstallComponent()
	{
		undoManager();
		installHandlers();
		installGraphComponentListeners();
	}

	protected void undoManager() {
		final mxGraph graph = graphComponent.getGraph();

		undoManager = createUndoManager();

		// Do not change the scale and translation after files have been loaded
		// graph.setResetViewOnRootChange(false);

		// Updates the modified flag if the graph model changes
		graph.getModel().addListener(mxEvent.CHANGE, changeTracker);

		// Adds the command history to the model and view
		graph.getModel().addListener(mxEvent.UNDO, undoHandler);
		graph.getView().addListener(mxEvent.UNDO, undoHandler);

		// Keeps the selection in sync with the command history
		mxIEventListener undoHandler = new mxIEventListener() {
			public void invoke(Object source, mxEventObject evt) {
				List<mxUndoableChange> changes = ((mxUndoableEdit) evt
						.getProperty("edit")).getChanges();
				graph.setSelectionCells(graph
						.getSelectionCellsForChanges(changes));
			}
		};

		undoManager.addListener(mxEvent.UNDO, undoHandler);
		undoManager.addListener(mxEvent.REDO, undoHandler);
	}

	protected Component getLeftComponent() {
		return new JPanel();
	}

	protected Component getExtensionsTab(final EditableElement elm) {
		return new JPanel();
	}

	/**
	 * 
	 */
	protected mxUndoManager createUndoManager() {
		return new mxUndoManager();
	}

	/**
	 * 
	 */
	public void installHandlers() {
		rubberband = new mxRubberband(graphComponent);
		keyboardHandler = new EditorKeyboardHandler(graphComponent);
	}

	/**
	 * 
	 */
	protected void installToolBar(MainFrame mainFrame, int perspective) {
		add(new EditorToolBar(this, JToolBar.HORIZONTAL), BorderLayout.NORTH);
	}

	/**
	 * 
	 */
	protected JLabel createStatusBar() {
		JLabel statusBar = new JLabel(mxResources.get("ready"));
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));

		return statusBar;
	}

	/**
	 * 
	 */
	protected void installRepaintListener() {
		graphComponent.getGraph().addListener(mxEvent.REPAINT,
				new mxIEventListener() {
					public void invoke(Object source, mxEventObject evt) {
						String buffer = (graphComponent.getTripleBuffer() != null) ? ""
								: " (unbuffered)";
						mxRectangle dirty = (mxRectangle) evt
								.getProperty("region");

						if (dirty == null) {
							status("Repaint all" + buffer);
						} else {
							status("Repaint: x=" + (int) (dirty.getX()) + " y="
									+ (int) (dirty.getY()) + " w="
									+ (int) (dirty.getWidth()) + " h="
									+ (int) (dirty.getHeight()) + buffer);
						}
					}
				});
	}

	/**
	 * 
	 */
	public EditorPalette insertPalette(String title) {
		final EditorPalette palette = new EditorPalette();
		palette.setName(title);
		final JScrollPane scrollPane = new JScrollPane(palette);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		libraryPane.add(title, scrollPane);

		// Updates the widths of the palettes if the container size changes
		libraryPane.addComponentListener(new ComponentAdapter() {
			/**
			 * 
			 */
			public void componentResized(ComponentEvent e) {
				int w = scrollPane.getWidth()
						- scrollPane.getVerticalScrollBar().getWidth();
				palette.setPreferredWidth(w);
			}

		});

		return palette;
	}

	public void clearPalettes() {
		libraryPane.removeAll();
	}

	/**
	 * 
	 */
	protected void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() < 0) {
			graphComponent.zoomIn();
		} else {
			graphComponent.zoomOut();
		}

		status(mxResources.get("scale") + ": "
				+ (int) (100 * graphComponent.getGraph().getView().getScale())
				+ "%");
	}

	/**
	 * 
	 */
	protected void showOutlinePopupMenu(MouseEvent e) {
		Point pt = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(),
				graphComponent);
		JCheckBoxMenuItem item = new JCheckBoxMenuItem(
				mxResources.get("magnifyPage"));
		item.setSelected(graphOutline.isFitPage());

		item.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				graphOutline.setFitPage(!graphOutline.isFitPage());
				graphOutline.repaint();
			}
		});

		JCheckBoxMenuItem item2 = new JCheckBoxMenuItem(
				mxResources.get("showLabels"));
		item2.setSelected(graphOutline.isDrawLabels());

		item2.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				graphOutline.setDrawLabels(!graphOutline.isDrawLabels());
				graphOutline.repaint();
			}
		});

		JCheckBoxMenuItem item3 = new JCheckBoxMenuItem(
				mxResources.get("buffering"));
		item3.setSelected(graphOutline.isTripleBuffered());

		item3.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				graphOutline.setTripleBuffered(!graphOutline.isTripleBuffered());
				graphOutline.repaint();
			}
		});

		JPopupMenu menu = new JPopupMenu();
		menu.add(item);
		menu.add(item2);
		menu.add(item3);
		menu.show(graphComponent, pt.x, pt.y);

		e.consume();
	}

	/**
	 * 
	 */
	protected void showGraphPopupMenu(MouseEvent e) {
		graphEditorFunctions.showGraphPopupMenu(e, graphComponent, this);
		/*
		 * Point pt = SwingUtilities.convertPoint(e.getComponent(),
		 * e.getPoint(), graphComponent); EditorPopupMenu menu = new
		 * EditorPopupMenu(BasicGraphEditor.this); menu.show(graphComponent,
		 * pt.x, pt.y);
		 * 
		 * e.consume();
		 */
	}

	/**
	 * @param palette
	 *            Load palette according to instantiated class
	 */

	/**
	 * 
	 */
	protected void mouseLocationChanged(MouseEvent e) {
		status(e.getX() + ", " + e.getY());
	}

	/**
	 * 
	 */
	protected void installListeners() {
		installGraphComponentListeners();
		// Installs mouse wheel listener for zooming
		MouseWheelListener wheelTracker = new MouseWheelListener() {
			/**
			 * 
			 */
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getSource() instanceof mxGraphOutline
						|| e.isControlDown()) {
					BasicGraphEditor.this.mouseWheelMoved(e);
				}
			}

		};

		// Handles mouse wheel events in the outline and graph component
		graphOutline.addMouseWheelListener(wheelTracker);
		// graphComponent.addMouseWheelListener(wheelTracker);

		// Installs the popup menu in the outline
		graphOutline.addMouseListener(new MouseAdapter() {

			/**
			 * 
			 */
			public void mousePressed(MouseEvent e) {
				// Handles context menu on the Mac where the trigger is on
				// mousepressed
				mouseReleased(e);
			}

			/**
			 * 
			 */
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showOutlinePopupMenu(e);
				}
			}

		});

	}

	protected void installGraphComponentListeners() {
		MouseWheelListener wheelTracker = new MouseWheelListener() {
			/**
		 * 
		 */
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getSource() instanceof mxGraphOutline
						|| e.isControlDown()) {
					BasicGraphEditor.this.mouseWheelMoved(e);
				}
			}

		};
		// Handles mouse wheel events in the graph component
		graphComponent.addMouseWheelListener(wheelTracker);

		// Installs the popup menu in the graph component
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {

			/**
			 * 
			 */
			public void mousePressed(MouseEvent e) {
				// Handles context menu on the Mac where the trigger is on
				// mousepressed
				mouseReleased(e);
			}

			/**
			 * 
			 */
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showGraphPopupMenu(e);
				}
			}

		});

		// Installs a mouse motion listener to display the mouse location
		graphComponent.getGraphControl().addMouseMotionListener(
				new MouseMotionListener() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * java.awt.event.MouseMotionListener#mouseDragged(java.
					 * awt.event.MouseEvent)
					 */
					public void mouseDragged(MouseEvent e) {
						mouseLocationChanged(e);
					}

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * java.awt.event.MouseMotionListener#mouseMoved(java.awt
					 * .event.MouseEvent)
					 */
					public void mouseMoved(MouseEvent e) {
						mouseDragged(e);
					}

				});

	}

	/**
	 * 
	 */
	public void setCurrentFile(File file) {
		File oldValue = currentFile;
		currentFile = file;

		firePropertyChange("currentFile", oldValue, file);

		if (oldValue != file) {
			updateTitle();
		}
	}

	/**
	 * 
	 */
	public File getCurrentFile() {
		return currentFile;
	}

	/**
	 * 
	 * @param modified
	 */
	public void setModified(boolean modified) {
		boolean oldValue = this.modified;
		this.modified = modified;

		firePropertyChange("modified", oldValue, modified);

		if (oldValue != modified) {
			updateTitle();
		}
	}

	/**
	 * 
	 * @return whether or not the current graph has been modified
	 */
	public boolean isModified() {
		return modified;
	}

	/**
	 * 
	 */
	public mxGraphComponent getGraphComponent() {
		return graphComponent;
	}

	/**
	 * 
	 */
	public mxGraphOutline getGraphOutline() {
		return graphOutline;
	}

	/**
	 * 
	 */
	public JTabbedPane getLibraryPane() {
		return libraryPane;
	}

	/**
	 * 
	 */
	public mxUndoManager getUndoManager() {
		return undoManager;
	}

	/**
	 * 
	 * @param name
	 * @param action
	 * @return a new Action bound to the specified string name
	 */
	public Action bind(String name, final Action action) {
		return bind(name, action, null);
	}

	/**
	 * 
	 * @param name
	 * @param action
	 * @return a new Action bound to the specified string name and icon
	 */

	public Action bind(String name, final Action action, String iconUrl) {
		AbstractAction newAction = new AbstractAction(name,
				(iconUrl != null) ? new ImageIcon(
						BasicGraphEditor.class.getResource(iconUrl)) : null) {
			/**
							 * 
							 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				action.actionPerformed(new ActionEvent(getGraphComponent(), e
						.getID(), e.getActionCommand()));
			}
		};

		newAction.putValue(Action.SHORT_DESCRIPTION,
				action.getValue(Action.SHORT_DESCRIPTION));

		return newAction;
	}

	/**
	 * 
	 * @param msg
	 */
	public void status(String msg) {
		statusBar.setText(msg);
	}

	/**
	 * 
	 */
	public void updateTitle() {
		JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);

		if (frame != null) {
			String title = (currentFile != null) ? currentFile
					.getAbsolutePath() : mxResources.get("newDiagram");

			if (modified) {
				title += "*";
			}

			frame.setTitle(title + " - " + appTitle);
		}
	}

	/**
	 * 
	 */
	public void about() {
		JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);

		if (frame != null) {
			EditorAboutFrame about = new EditorAboutFrame(frame);
			about.setModal(true);

			// Centers inside the application frame
			int x = frame.getX() + (frame.getWidth() - about.getWidth()) / 2;
			int y = frame.getY() + (frame.getHeight() - about.getHeight()) / 2;
			about.setLocation(x, y);

			// Shows the modal dialog and waits
			about.setVisible(true);
		}
	}

	/**
	 * 
	 */
	public void exit() {
		JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);

		if (frame != null) {
			frame.dispose();
		}
	}

	/**
	 * 
	 */
	public void setLookAndFeel(String clazz) {
		JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);

		if (frame != null) {
			try {
				UIManager.setLookAndFeel(clazz);
				SwingUtilities.updateComponentTreeUI(frame);

				// Needs to assign the key bindings again
				keyboardHandler = new EditorKeyboardHandler(graphComponent);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	public JFrame createFrame() {
		return createFrame(null);
	}

	public JFrame createFrame(JMenuBar menuBar) {
		frame = new JFrame();
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (menuBar == null)
			setLayout(2);
		else
			frame.setJMenuBar(menuBar);
		// width and height
		frame.setSize(1070, 740);

		// Updates the frame title
		updateTitle();

		return frame;
	}

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Creates an action that executes the specified layout.
	 * 
	 * @param key
	 *            Key to be used for getting the label from mxResources and also
	 *            to create the layout instance for the commercial graph editor
	 *            example.
	 * @return an action that executes the specified layout
	 */

	public Action graphLayout(final String key, boolean animate) {
		final mxIGraphLayout layout = createLayout(key, animate);

		if (layout != null) {
			return new AbstractAction(mxResources.get(key)) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					final mxGraph graph = graphComponent.getGraph();
					Object cell = graph.getSelectionCell();

					if (cell == null
							|| graph.getModel().getChildCount(cell) == 0) {
						cell = graph.getDefaultParent();
					}

					graph.getModel().beginUpdate();
					try {
						long t0 = System.currentTimeMillis();
						layout.execute(cell);
						status("Layout: " + (System.currentTimeMillis() - t0)
								+ " ms");
					} finally {
						mxMorphing morph = new mxMorphing(graphComponent, 20,
								1.2, 20);

						morph.addListener(mxEvent.DONE, new mxIEventListener() {
							public void invoke(Object sender, mxEventObject evt) {
								graph.getModel().endUpdate();
							}

						});

						morph.startAnimation();
					}

				}

			};
		} else {
			return new AbstractAction(mxResources.get(key)) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(graphComponent,
							mxResources.get("noLayout"));
				}

			};
		}
	}

	/**
	 * Creates a layout instance for the given identifier.
	 */
	protected mxIGraphLayout createLayout(String ident, boolean animate) {
		mxIGraphLayout layout = null;

		if (ident != null) {
			mxGraph graph = graphComponent.getGraph();

			if (ident.equals("verticalHierarchical")) {
				layout = new mxHierarchicalLayout(graph);
			} else if (ident.equals("horizontalHierarchical")) {
				layout = new mxHierarchicalLayout(graph, JLabel.WEST);
			} else if (ident.equals("verticalTree")) {
				layout = new mxCompactTreeLayout(graph, false);
			} else if (ident.equals("horizontalTree")) {
				layout = new mxCompactTreeLayout(graph, true);
			} else if (ident.equals("parallelEdges")) {
				layout = new mxParallelEdgeLayout(graph);
			} else if (ident.equals("placeEdgeLabels")) {
				layout = new mxEdgeLabelLayout(graph);
			} else if (ident.equals("organicLayout")) {
				layout = new mxOrganicLayout(graph);
			}
			if (ident.equals("verticalPartition")) {
				layout = new mxPartitionLayout(graph, false) {
					/**
					 * Overrides the empty implementation to return the size of
					 * the graph control.
					 */
					public mxRectangle getContainerSize() {
						return graphComponent.getLayoutAreaSize();
					}
				};
			} else if (ident.equals("horizontalPartition")) {
				layout = new mxPartitionLayout(graph, true) {
					/**
					 * Overrides the empty implementation to return the size of
					 * the graph control.
					 */
					public mxRectangle getContainerSize() {
						return graphComponent.getLayoutAreaSize();
					}
				};
			} else if (ident.equals("verticalStack")) {
				layout = new mxStackLayout(graph, false) {
					/**
					 * Overrides the empty implementation to return the size of
					 * the graph control.
					 */
					public mxRectangle getContainerSize() {
						return graphComponent.getLayoutAreaSize();
					}
				};
			} else if (ident.equals("horizontalStack")) {
				layout = new mxStackLayout(graph, true) {
					/**
					 * Overrides the empty implementation to return the size of
					 * the graph control.
					 */
					public mxRectangle getContainerSize() {
						return graphComponent.getLayoutAreaSize();
					}
				};
			} else if (ident.equals("circleLayout")) {
				layout = new mxCircleLayout(graph);
			}
		}

		return layout;
	}

	public void setDefaultButton() {
		modelsTabPane.setSelectedIndex(0);
	}
}
