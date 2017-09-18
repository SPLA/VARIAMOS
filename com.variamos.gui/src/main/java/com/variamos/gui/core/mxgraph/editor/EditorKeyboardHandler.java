/**
 * $Id: EditorKeyboardHandler.java,v 1.1 2009/10/23 11:32:08 gaudenz Exp $
 * Copyright (c) 2008, Gaudenz Alder
 */
package com.variamos.gui.core.mxgraph.editor;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxKeyboardHandler;
import com.mxgraph.swing.util.mxGraphActions;
import com.variamos.gui.core.viewcontrollers.VariamosGUIEditorActions;

/**
 * @author Administrator
 * FIXME: Complete this keyboard handler for basic actions https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html
 * 
 */
public class EditorKeyboardHandler extends mxKeyboardHandler
{

	/**
	 * 
	 * @param graphComponent
	 */
	public EditorKeyboardHandler(mxGraphComponent graphComponent)
	{
		super(graphComponent);
	}

	/**
	 * Return JTree's input map.
	 */
	protected InputMap getInputMap(int condition)
	{
		InputMap map = super.getInputMap(condition);

		if (condition == JComponent.WHEN_FOCUSED && map != null)
		{
			map.put(KeyStroke.getKeyStroke("control S"), "save");
			map.put(KeyStroke.getKeyStroke("control shift S"), "saveAs");
			map.put(KeyStroke.getKeyStroke("control N"), "new");
			map.put(KeyStroke.getKeyStroke("control O"), "open");

			map.put(KeyStroke.getKeyStroke("control Z"), "undo");
			map.put(KeyStroke.getKeyStroke("control Y"), "redo");
			map
					.put(KeyStroke.getKeyStroke("control shift V"),
							"selectVertices");
			map.put(KeyStroke.getKeyStroke("control shift E"), "selectEdges");
		}

		return map;
	}

	/**
	 * Return the mapping between JTree's input map and JGraph's actions.
	 *  an action map specifies the action corresponding to each action name.
	 *  Each InputMap/ActionMap has a parent that typically comes from the UI. Any time the look and feel is changed,
	 *   the parent is reset. 
	 *  In this way, any bindings specified by the developer are never lost on look and feel changes.
	 *  Source: https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html
	 */
	protected ActionMap createActionMap()
	{
		ActionMap map = super.createActionMap();

		map.put("save", new EditorActionsController.SaveAction(false));
		map.put("saveAs", new EditorActionsController.SaveAction(true));
		map.put("new", new VariamosGUIEditorActions.NewAction());
		map.put("open", new VariamosGUIEditorActions.LoadAction());
		map.put("undo", new EditorActionsController.HistoryAction(true));
		map.put("redo", new EditorActionsController.HistoryAction(false));
		map.put("selectVertices", mxGraphActions.getSelectVerticesAction());
		map.put("selectEdges", mxGraphActions.getSelectEdgesAction());

		return map;
	}

}

