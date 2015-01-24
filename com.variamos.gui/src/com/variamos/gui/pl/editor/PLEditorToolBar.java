package com.variamos.gui.pl.editor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.TransferHandler;

import com.variamos.gui.maineditor.EditorActions.NewAction;
import com.variamos.gui.maineditor.EditorActions.OpenAction;
import com.variamos.gui.maineditor.EditorActions.SaveAction;
import com.variamos.gui.maineditor.EditorActions.HistoryAction;

/*
 * import com.mxgraph.swing.util.mxGraphActions;
 import com.mxgraph.util.mxConstants;
 */
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxResources;
//import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.refas.editor.actions.ClearSimulationAction;
import com.variamos.gui.refas.editor.actions.ClearVerificationAction;
import com.variamos.gui.refas.editor.actions.NextSimulationAction;
import com.variamos.gui.refas.editor.actions.ParentElementAction;
import com.variamos.gui.refas.editor.actions.RootElementAction;
import com.variamos.gui.refas.editor.actions.StartSimulationAction;
import com.variamos.gui.refas.editor.actions.VerificationAction;
import com.variamos.gui.refas.editor.actions.VerifyDeadElementAction;
import com.variamos.gui.refas.editor.actions.VerifyFalseOptElementAction;

public class PLEditorToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8015443128436394471L;

	/**
	 * 
	 * @param frame
	 * @param orientation
	 */
	private boolean ignoreZoomChange = false;

	/**
	 * 
	 */
	public PLEditorToolBar(final VariamosGraphEditor variamosGraphEditor,
			int orientation) {
		super(orientation);
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(3, 3, 3, 3), getBorder()));
		setFloatable(false);

		add(variamosGraphEditor.bind("New", new NewAction(),
				"/com/mxgraph/examples/swing/images/new.gif"));
		add(variamosGraphEditor.bind("Open", new OpenAction(),
				"/com/mxgraph/examples/swing/images/open.gif"));
		add(variamosGraphEditor.bind("Save", new SaveAction(false),
				"/com/mxgraph/examples/swing/images/save.gif"));

		addSeparator();
		/*
		 * add(editor.bind("Print", new PrintAction(),
		 * "/com/mxgraph/examples/swing/images/print.gif"));
		 * 
		 * addSeparator();
		 */

		add(variamosGraphEditor.bind("Cut", TransferHandler.getCutAction(),
				"/com/mxgraph/examples/swing/images/cut.gif"));

		add(variamosGraphEditor.bind("Copy", TransferHandler.getCopyAction(),
				"/com/mxgraph/examples/swing/images/copy.gif"));
		add(variamosGraphEditor.bind("Paste", TransferHandler.getPasteAction(),
				"/com/mxgraph/examples/swing/images/paste.gif"));

		addSeparator();

		add(variamosGraphEditor.bind("Delete",
				mxGraphActions.getDeleteAction(),
				"/com/mxgraph/examples/swing/images/delete.gif"));

		addSeparator();

		add(variamosGraphEditor.bind("Undo", new HistoryAction(true),
				"/com/mxgraph/examples/swing/images/undo.gif"));
		add(variamosGraphEditor.bind("Redo", new HistoryAction(false),
				"/com/mxgraph/examples/swing/images/redo.gif"));


		if (variamosGraphEditor.getPerspective() == 2) {
			addSeparator();

			add(variamosGraphEditor.bind("clearElements", new ClearVerificationAction(),
					"/com/mxgraph/examples/swing/images/pan.gif"));
			add(variamosGraphEditor.bind("verifOptional", new VerificationAction(),
					"/com/mxgraph/examples/swing/images/checkmark.gif"));
			addSeparator();
			add(variamosGraphEditor.bind("verifyRoot", new RootElementAction(),
					"/com/mxgraph/examples/swing/images/tree.gif"));
			add(variamosGraphEditor.bind("verifyParents", new ParentElementAction(),
					"/com/mxgraph/examples/swing/images/straight.gif"));
			add(variamosGraphEditor.bind("verifyDeadElement", new VerifyDeadElementAction(),
					"/com/variamos/gui/refas/editor/images/www.iconfinder.com/dead.png"));
			add(variamosGraphEditor.bind("verifyFalseOptionalElements", new VerifyFalseOptElementAction(),
					"/com/variamos/gui/refas/editor/images/www.iconfinder.com/false.png"));
	//		add(variamosGraphEditor.bind("updateCore", new UpdateAction(),
	//				"/com/mxgraph/examples/swing/images/tree.gif"));
		}
			
		if (variamosGraphEditor.getPerspective() == 4) {			
			addSeparator();
			add(variamosGraphEditor.bind("clean", new ClearSimulationAction(),
					"/com/variamos/gui/refas/editor/images/www.iconfinder.com/player_stop.png"));
			add(variamosGraphEditor.bind("first", new StartSimulationAction(),
					"/com/variamos/gui/refas/editor/images/www.iconfinder.com/direction_right.png"));
			add(variamosGraphEditor.bind("next", new NextSimulationAction(),
					"/com/variamos/gui/refas/editor/images/www.iconfinder.com/fastforward.png"));
		}

		// Gets the list of available fonts from the local graphics environment
		// and adds some frequently used fonts at the beginning of the list

		/*
		 * GraphicsEnvironment env = GraphicsEnvironment
		 * .getLocalGraphicsEnvironment(); List<String> fonts = new
		 * ArrayList<String>(); fonts.addAll(Arrays.asList(new String[] {
		 * "Helvetica", "Verdana", "Times New Roman", "Garamond", "Courier New",
		 * "-" }));
		 * fonts.addAll(Arrays.asList(env.getAvailableFontFamilyNames()));
		 * 
		 * final JComboBox fontCombo = new JComboBox(fonts.toArray());
		 * fontCombo.setEditable(true); fontCombo.setMinimumSize(new
		 * Dimension(120, 0)); fontCombo.setPreferredSize(new Dimension(120,
		 * 0)); fontCombo.setMaximumSize(new Dimension(120, 100));
		 * add(fontCombo);
		 * 
		 * fontCombo.addActionListener(new ActionListener() {
		 * 
		 * public void actionPerformed(ActionEvent e) { String font =
		 * fontCombo.getSelectedItem().toString();
		 * 
		 * if (font != null && !font.equals("-")) { mxGraph graph =
		 * editor.getGraphComponent().getGraph();
		 * graph.setCellStyles(mxConstants.STYLE_FONTFAMILY, font); } } });
		 * 
		 * final JComboBox sizeCombo = new JComboBox(new Object[] { "6pt",
		 * "8pt", "9pt", "10pt", "12pt", "14pt", "18pt", "24pt", "30pt", "36pt",
		 * "48pt", "60pt" }); sizeCombo.setEditable(true);
		 * sizeCombo.setMinimumSize(new Dimension(65, 0));
		 * sizeCombo.setPreferredSize(new Dimension(65, 0));
		 * sizeCombo.setMaximumSize(new Dimension(65, 100)); add(sizeCombo);
		 * 
		 * sizeCombo.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { mxGraph graph =
		 * editor.getGraphComponent().getGraph();
		 * graph.setCellStyles(mxConstants.STYLE_FONTSIZE, sizeCombo
		 * .getSelectedItem().toString().replace("pt", "")); } });
		 * 
		 * addSeparator();
		 * 
		 * add(editor.bind("Bold", new FontStyleAction(true),
		 * "/com/mxgraph/examples/swing/images/bold.gif"));
		 * add(editor.bind("Italic", new FontStyleAction(false),
		 * "/com/mxgraph/examples/swing/images/italic.gif"));
		 * 
		 * addSeparator();
		 * 
		 * add(editor.bind("Left", new KeyValueAction(mxConstants.STYLE_ALIGN,
		 * mxConstants.ALIGN_LEFT),
		 * "/com/mxgraph/examples/swing/images/left.gif"));
		 * add(editor.bind("Center", new KeyValueAction(mxConstants.STYLE_ALIGN,
		 * mxConstants.ALIGN_CENTER),
		 * "/com/mxgraph/examples/swing/images/center.gif"));
		 * add(editor.bind("Right", new KeyValueAction(mxConstants.STYLE_ALIGN,
		 * mxConstants.ALIGN_RIGHT),
		 * "/com/mxgraph/examples/swing/images/right.gif"));
		 * 
		 * addSeparator();
		 * 
		 * add(editor.bind("Font", new ColorAction("Font",
		 * mxConstants.STYLE_FONTCOLOR),
		 * "/com/mxgraph/examples/swing/images/fontcolor.gif"));
		 * add(editor.bind("Stroke", new ColorAction("Stroke",
		 * mxConstants.STYLE_STROKECOLOR),
		 * "/com/mxgraph/examples/swing/images/linecolor.gif"));
		 * add(editor.bind("Fill", new ColorAction("Fill",
		 * mxConstants.STYLE_FILLCOLOR),
		 * "/com/mxgraph/examples/swing/images/fillcolor.gif"));
		 */

		addSeparator();

		final mxGraphView view = variamosGraphEditor.getGraphComponent()
				.getGraph().getView();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		final JComboBox zoomCombo = new JComboBox(new Object[] { "400%",
				"300%", "200%", "175%", "150%", "125%", "110%", "100%", "90%",
				"75%", "67%", "50%", mxResources.get("page"),
				mxResources.get("width"), mxResources.get("actualSize") });
		zoomCombo.setEditable(true);
		zoomCombo.setMinimumSize(new Dimension(75, 0));
		zoomCombo.setPreferredSize(new Dimension(75, 0));
		zoomCombo.setMaximumSize(new Dimension(75, 100));
		zoomCombo.setMaximumRowCount(9);
		add(zoomCombo);

		// Sets the zoom in the zoom combo the current value
		mxIEventListener scaleTracker = new mxIEventListener() {
			/**
			 * 
			 */
			public void invoke(Object sender, mxEventObject evt) {
				ignoreZoomChange = true;

				try {
					zoomCombo.setSelectedItem((int) Math.round(100 * view
							.getScale()) + "%");
				} finally {
					ignoreZoomChange = false;
				}
			}
		};

		// Installs the scale tracker to update the value in the combo box
		// if the zoom is changed from outside the combo box
		view.getGraph().getView().addListener(mxEvent.SCALE, scaleTracker);
		view.getGraph().getView()
				.addListener(mxEvent.SCALE_AND_TRANSLATE, scaleTracker);

		// Invokes once to sync with the actual zoom value
		scaleTracker.invoke(null, null);

		zoomCombo.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				mxGraphComponent graphComponent = variamosGraphEditor
						.getGraphComponent();

				// Zoomcombo is changed when the scale is changed in the diagram
				// but the change is ignored here
				if (!ignoreZoomChange) {
					String zoom = zoomCombo.getSelectedItem().toString();

					if (zoom.equals(mxResources.get("page"))) {
						graphComponent.setPageVisible(true);
						graphComponent
								.setZoomPolicy(mxGraphComponent.ZOOM_POLICY_PAGE);
					} else if (zoom.equals(mxResources.get("width"))) {
						graphComponent.setPageVisible(true);
						graphComponent
								.setZoomPolicy(mxGraphComponent.ZOOM_POLICY_WIDTH);
					} else if (zoom.equals(mxResources.get("actualSize"))) {
						graphComponent.zoomActual();
					} else {
						try {
							zoom = zoom.replace("%", "");
							double scale = Math.min(16, Math.max(0.01,
									Double.parseDouble(zoom) / 100));
							graphComponent.zoomTo(scale,
									graphComponent.isCenterZoom());
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(variamosGraphEditor,
									ex.getMessage());
						}
					}
				}
			}
		});
	}
}
