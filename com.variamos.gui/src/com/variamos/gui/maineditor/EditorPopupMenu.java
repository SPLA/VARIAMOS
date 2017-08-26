package com.variamos.gui.maineditor;

import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.TransferHandler;

import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.EditorActions.HistoryAction;
import com.variamos.gui.perspeditor.actions.RecoverCloneAction;
import com.variamos.gui.pl.editor.actions.FigureAction;

public class EditorPopupMenu extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3132749140550242191L;

	public EditorPopupMenu(BasicGraphEditor editor) {
		boolean selected = !editor.getGraphComponent().getGraph()
				.isSelectionEmpty();

		add(editor.bind(mxResources.get("undo"), new HistoryAction(true),
				"/com/mxgraph/examples/swing/images/undo.gif"));

		addSeparator();

		add(
				editor.bind(mxResources.get("cut"),
						TransferHandler.getCutAction(),
						"/com/mxgraph/examples/swing/images/cut.gif"))
				.setEnabled(selected);
		add(
				editor.bind(mxResources.get("copy"),
						TransferHandler.getCopyAction(),
						"/com/mxgraph/examples/swing/images/copy.gif"))
				.setEnabled(selected);
		add(editor.bind(mxResources.get("paste"),
				TransferHandler.getPasteAction(),
				"/com/mxgraph/examples/swing/images/paste.gif"));

		addSeparator();

		add(
				editor.bind(mxResources.get("delete"),
						mxGraphActions.getDeleteAction(),
						"/com/mxgraph/examples/swing/images/delete.gif"))
				.setEnabled(selected);

		addSeparator();

		// Creates the format menu
		JMenu menu = (JMenu) add(new JMenu(mxResources.get("format")));

		EditorMenuBar.populateFormatMenu(menu, editor);

		// Creates the figure menu
		menu = (JMenu) add(new JMenu(mxResources.get("figure")));
		populateFigureMenu(menu, editor);

		// Creates the shape menu
		menu = (JMenu) add(new JMenu(mxResources.get("shape")));

		EditorMenuBar.populateShapeMenu(menu, editor);

		// Creates the clones menu
		menu = (JMenu) add(new JMenu(mxResources.get("clones")));

		populateCloneMenu(menu, editor);

		addSeparator();

		add(
				editor.bind(mxResources.get("edit"),
						mxGraphActions.getEditAction())).setEnabled(selected);

		addSeparator();

		add(editor.bind(mxResources.get("selectVertices"),
				mxGraphActions.getSelectVerticesAction()));
		add(editor.bind(mxResources.get("selectEdges"),
				mxGraphActions.getSelectEdgesAction()));

		addSeparator();

		add(editor.bind(mxResources.get("selectAll"),
				mxGraphActions.getSelectAllAction()));
	}

	public static void populateFigureMenu(JMenu menu, BasicGraphEditor editor) {
		menu.add(editor
				.bind("Cloud", new FigureAction(mxConstants.SHAPE_CLOUD)));
		menu.add(editor.bind("Ellipse", new FigureAction(
				mxConstants.SHAPE_ELLIPSE)));
		menu.add(editor.bind("Double Ellipse", new FigureAction(
				mxConstants.SHAPE_DOUBLE_ELLIPSE)));
		menu.add(editor.bind("Rectangle", new FigureAction(
				mxConstants.SHAPE_RECTANGLE)));
		menu.add(editor.bind("Rhombus", new FigureAction(
				mxConstants.SHAPE_RHOMBUS)));
		menu.add(editor.bind("Triangle", new FigureAction(
				mxConstants.SHAPE_TRIANGLE)));
	};

	public static void populateCloneMenu(JMenu menu, BasicGraphEditor editor) {
		menu.add(editor
				.bind("Recreate cloned concepts of this model ", new RecoverCloneAction()));
	};
}
