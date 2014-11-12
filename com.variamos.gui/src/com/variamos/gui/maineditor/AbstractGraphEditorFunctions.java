package com.variamos.gui.maineditor;

<<<<<<< HEAD
import java.awt.event.MouseEvent;

import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.EditorPalette;
import com.mxgraph.swing.mxGraphComponent;

public abstract class AbstractGraphEditorFunctions {
	protected VariamosGraphEditor editor;
	public AbstractGraphEditorFunctions(VariamosGraphEditor editor)
	{
		this.editor = editor;
	}
	public abstract void loadRegularPalette(EditorPalette palette,  mxGraphComponent graphComponent);
	public abstract void showGraphPopupMenu(MouseEvent e, mxGraphComponent graphComponent, BasicGraphEditor basicGraphEditor);
	public abstract void updateEditor ();
	
=======
import com.mxgraph.examples.swing.editor.EditorPalette;
import com.mxgraph.swing.mxGraphComponent;

public abstract class AbstractGraphEditorFunctions {

	public abstract void loadRegularPalette(EditorPalette palette,  mxGraphComponent graphComponent);
>>>>>>> branch 'development' of https://github.com/jcmunozf/VARIAMOS
}
