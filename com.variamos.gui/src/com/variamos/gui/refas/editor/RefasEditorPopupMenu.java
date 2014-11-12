package com.variamos.gui.refas.editor;

import javax.swing.JMenu;

import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.EditorPopupMenu;
import com.mxgraph.util.mxConstants;
import com.variamos.gui.pl.editor.actions.FigureAction;

public class RefasEditorPopupMenu extends EditorPopupMenu
{
	private static final long serialVersionUID = -3132749132150242191L;
	
	public RefasEditorPopupMenu(BasicGraphEditor editor)
	{
		super(editor);

	}
	/**
	 * 
	 */
	public static void populateFigureMenu(JMenu menu, BasicGraphEditor editor) {
		menu.add( editor.bind("Cloud", new FigureAction(mxConstants.SHAPE_CLOUD ) ));
		menu.add( editor.bind("Ellipse", new FigureAction(mxConstants.SHAPE_ELLIPSE) ));
		menu.add( editor.bind("Double Ellipse", new FigureAction(mxConstants.SHAPE_DOUBLE_ELLIPSE ) ));
		menu.add( editor.bind("Rectangle", new FigureAction(mxConstants.SHAPE_RECTANGLE ) ));
		menu.add( editor.bind("Rhombus", new FigureAction(mxConstants.SHAPE_RHOMBUS ) ));
		menu.add( editor.bind("Triangle", new FigureAction(mxConstants.SHAPE_TRIANGLE ) ));
	};
}
