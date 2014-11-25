package com.variamos.gui.refas.editor.actions;

import java.util.Iterator;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;

public class SharedActions {
	public static mxGraph beforeSaveGraph(mxGraph graph) {
		mxIGraphModel refasGraph = graph.getModel();
		Object o = refasGraph.getRoot(); // Main Root
		mxCell o1 = (mxCell) refasGraph.getChildAt(o, 0); // Null Root
		for (int i = 0; i < o1.getChildCount(); i++) {
			mxCell mv = (mxCell) refasGraph.getChildAt(o1, i);
			for (int j = 0; j < mv.getChildCount(); j++) {
				mxCell concept = (mxCell) refasGraph.getChildAt(mv, j);
				Object value = concept.getValue();
				if (value instanceof InstConcept) {
					InstConcept ic = (InstConcept) value;
					ic.clearMetaConcept();
					Iterator<InstAttribute> ias = ic.getInstAttributes().values().iterator();
					while (ias.hasNext()) {
						InstAttribute ia = (InstAttribute) ias.next();
						ia.clearMetaAttribute();
					}
				}
				

			}
		}

		return graph;
	}
	
	public static mxGraph afterSaveGraph(mxGraph graph, VariamosGraphEditor editor) {
		mxIGraphModel refasGraph = graph.getModel();
		Object o = refasGraph.getRoot(); // Main Root
		mxCell o1 = (mxCell) refasGraph.getChildAt(o, 0); // Null Root
		for (int i = 0; i < o1.getChildCount(); i++) {
			mxCell mv = (mxCell) refasGraph.getChildAt(o1, i);
			for (int j = 0; j < mv.getChildCount(); j++) {
				mxCell concept = (mxCell) refasGraph.getChildAt(mv, j);
				Object value = concept.getValue();
				if (value instanceof InstConcept) {
					InstConcept ic = (InstConcept) value;
					MetaConcept mc = editor.getSematicSintaxObject().getMetaConcept(ic.getMetaConceptIdentifier());
					ic.setMetaConcept(mc);
					Iterator<InstAttribute> ias = ic.getInstAttributes().values().iterator();
					while (ias.hasNext()) {
						InstAttribute ia = (InstAttribute) ias.next();
						ia.setMetaAttribute(mc.getMetaAttribute(ia.getMetaAttributeName()));
					}
				}
				

			}
		}

		return graph;
	}
}
