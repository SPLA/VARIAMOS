package com.variamos.gui.perspeditor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.TransferHandler;

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
import com.variamos.gui.core.mxgraph.editor.EditorActionsController.HistoryAction;
import com.variamos.gui.core.mxgraph.editor.EditorActionsController.SaveAction;
import com.variamos.gui.core.viewcontrollers.VariamosGUIEditorActions.LoadAction;
import com.variamos.gui.core.viewcontrollers.VariamosGUIEditorActions.NewAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.actions.ClearConfigurationAction;
import com.variamos.gui.perspeditor.actions.ClearSimulationAction;
import com.variamos.gui.perspeditor.actions.ClearVerificationAction;
import com.variamos.gui.perspeditor.actions.NextSimulationAction;
import com.variamos.gui.perspeditor.actions.ParentElementAction;
import com.variamos.gui.perspeditor.actions.RootElementAction;
import com.variamos.gui.perspeditor.actions.StartSimulationAction;
import com.variamos.gui.perspeditor.actions.UpdateCoreAction;
import com.variamos.gui.perspeditor.actions.VerificationAction;
import com.variamos.gui.perspeditor.actions.VerifyDeadElementAction;
import com.variamos.gui.perspeditor.actions.VerifyFalseOptElementAction;


/**
 * This class support the tool bar with icons in the user interface. Icons such as new, save, undo, redo
 * are draw in this class
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-17
 * 
 */
public class PerspEditorToolBarView extends JToolBar {

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
	public PerspEditorToolBarView(final VariamosGraphEditor variamosGraphEditor,
			int orientation) {
		super(orientation);
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(3, 3, 3, 3), getBorder()));
		setFloatable(false);

		add(variamosGraphEditor.bind("New", new NewAction(),
				"/com/variamos/gui/perspeditor/images/new.gif"));
		add(variamosGraphEditor.bind("Open", new LoadAction(),
				"/com/variamos/gui/perspeditor/images/open.gif"));
		add(variamosGraphEditor.bind("Save", new SaveAction(false),
				"/com/variamos/gui/perspeditor/images/save.gif"));

		addSeparator();
		/*
		 * add(editor.bind("Print", new PrintAction(),
		 * "/com/variamos/gui/perspeditor/images/print.gif"));
		 * 
		 * addSeparator();
		 */

		add(variamosGraphEditor.bind("Cut", TransferHandler.getCutAction(),
				"/com/variamos/gui/perspeditor/images/cut.gif"));

		add(variamosGraphEditor.bind("Copy", TransferHandler.getCopyAction(),
				"/com/variamos/gui/perspeditor/images/copy.gif"));
		add(variamosGraphEditor.bind("Paste", TransferHandler.getPasteAction(),
				"/com/variamos/gui/perspeditor/images/paste.gif"));

		addSeparator();

		add(variamosGraphEditor.bind("Delete",
				mxGraphActions.getDeleteAction(),
				"/com/variamos/gui/perspeditor/images/delete.gif"));

		addSeparator();

		add(variamosGraphEditor.bind("Undo", new HistoryAction(true),
				"/com/variamos/gui/perspeditor/images/undo.gif"));
		add(variamosGraphEditor.bind("Redo", new HistoryAction(false),
				"/com/variamos/gui/perspeditor/images/redo.gif"));

		if (variamosGraphEditor.getPerspective() == 2) {
			addSeparator();
			add(variamosGraphEditor
					.bind("updateCore",
							new UpdateCoreAction(),
							"/com/variamos/gui/perspeditor/images/iconarchive.com/Highlightmarker-green-icon.png"));
			addSeparator();
			add(variamosGraphEditor.bind("clearElements",
					new ClearVerificationAction(),
					"/com/variamos/gui/perspeditor/images/pan.gif"));
			add(variamosGraphEditor.bind("verifOptional",
					new VerificationAction(),
					"/com/variamos/gui/perspeditor/images/checkmark.gif"));
			addSeparator();
			add(variamosGraphEditor.bind("verifyRoot", new RootElementAction(),
					"/com/variamos/gui/perspeditor/images/tree.gif"));
			add(variamosGraphEditor.bind("verifyParents",
					new ParentElementAction(),
					"/com/variamos/gui/perspeditor/images/straight.gif"));
			add(variamosGraphEditor
					.bind("verifyDeadElement", new VerifyDeadElementAction(),
							"/com/variamos/gui/perspeditor/images/www.iconfinder.com/dead.png"));
			add(variamosGraphEditor
					.bind("verifyFalseOptionalElements",
							new VerifyFalseOptElementAction(),
							"/com/variamos/gui/perspeditor/images/www.iconfinder.com/false.png"));
			// add(variamosGraphEditor.bind("updateCore", new UpdateAction(),
			// "/com/variamos/gui/perspeditor/images/tree.gif"));
		}

		if (variamosGraphEditor.getPerspective() == 4) {
			addSeparator();
			add(variamosGraphEditor.bind("clearconf",
					new ClearConfigurationAction(),
					"/com/variamos/gui/perspeditor/images/pan.gif"));
			addSeparator();
			add(variamosGraphEditor
					.bind("cleansimul", new ClearSimulationAction(),
							"/com/variamos/gui/perspeditor/images/www.iconfinder.com/player_stop.png"));
			add(variamosGraphEditor
					.bind("first", new StartSimulationAction(),
							"/com/variamos/gui/perspeditor/images/www.iconfinder.com/direction_right.png"));
			add(variamosGraphEditor
					.bind("next", new NextSimulationAction(),
							"/com/variamos/gui/perspeditor/images/www.iconfinder.com/fastforward.png"));
		}

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

