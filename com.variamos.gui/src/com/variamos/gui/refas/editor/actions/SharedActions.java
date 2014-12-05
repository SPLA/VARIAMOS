package com.variamos.gui.refas.editor.actions;

import java.util.Iterator;
import java.util.Map;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.refas.editor.RefasGraph;
import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticConcept;
import com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency;
import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;
import com.variamos.syntaxsupport.metametamodel.MetaElement;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;

public class SharedActions {
	public static mxGraph beforeSaveGraph(mxGraph graph) {
		mxIGraphModel refasGraph = graph.getModel();
		if (graph instanceof RefasGraph) {
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
						ic.clearInstAttributesClassObjects();
						Iterator<InstAttribute> ias = ic.getInstAttributes()
								.values().iterator();
						while (ias.hasNext()) {
							InstAttribute ia = (InstAttribute) ias.next();
							ia.clearMetaAttribute();
						}
					}
					if (value instanceof InstGroupDependency) {
						InstGroupDependency ic = (InstGroupDependency) value;
						String str = (String) ic
								.getSemanticGroupDependencyIdentifier();
						ic.setSemanticGroupDependencyIdentifier(str);
						ic.clearMetaGroupDependency();
						ic.clearInstAttributesClassObjects();
						Iterator<InstAttribute> ias = ic.getInstAttributes()
								.values().iterator();
						while (ias.hasNext()) {
							InstAttribute ia = (InstAttribute) ias.next();
							ia.clearMetaAttribute();
						}
					}

				}
			}
		}
		return graph;
	}

	public static mxGraph afterSaveGraph(mxGraph graph,
			VariamosGraphEditor editor) {
		mxIGraphModel refasGraph = graph.getModel();
		if (graph instanceof RefasGraph) {
			Object o = refasGraph.getRoot(); // Main Root
			Object o1 = refasGraph.getChildAt(o, 0); // Null Root
			for (int mvInd = 0; mvInd < refasGraph.getChildCount(o1); mvInd++) {
				mxCell mv0 = (mxCell) refasGraph.getChildAt(o1, mvInd); // Root
																		// model
																		// view
																		// mvInd
				if (refasGraph.getChildCount(mv0) > 0
						&& mv0.getChildAt(0).getValue().equals(mv0.getValue())) {
					for (int i = 0; i < refasGraph.getChildCount(mv0); i++) {
						mxCell mv1 = (mxCell) refasGraph.getChildAt(mv0, i);
						for (int j = 0; j < refasGraph.getChildCount(mv1); j++) {
							mxCell mv2 = (mxCell) refasGraph.getChildAt(mv1, j);
							loadSupportObjects(editor, mv2.getValue());
						}
					}
				} else
					for (int i = 0; i < refasGraph.getChildCount(mv0); i++) {
						mxCell mv1 = (mxCell) refasGraph.getChildAt(mv0, i);
						loadSupportObjects(editor, mv1.getValue());
					}
			}
		}
		return graph;
	}

	private static void loadSupportObjects(VariamosGraphEditor editor,
			Object value) {
		if (value instanceof InstConcept) {
			InstConcept ic = (InstConcept) value;
			MetaElement mc = editor.getSematicSintaxObject().getMetaConcept(
					ic.getMetaConceptIdentifier());
			ic.setMetaConcept(mc);
			Iterator<InstAttribute> ias = ic.getInstAttributes().values()
					.iterator();
			while (ias.hasNext()) {
				InstAttribute ia = (InstAttribute) ias.next();
				ia.setAttribute(mc.getAbstractAttribute(ia.getAttributeName()));
			}
		}
		if (value instanceof InstGroupDependency) {
			InstGroupDependency ic = (InstGroupDependency) value;
			MetaElement mc = editor.getSematicSintaxObject().getMetaConcept(
					ic.getMetaGroupDependencyIdentifier());
			AbstractSemanticConcept sgd = editor.getSematicSintaxObject()
					.getSemanticConcept(
							(String) ic.getSemanticGroupDependencyIdentifier());
			ic.setMetaGroupDependency(mc);
			if (sgd != null)
				ic.setSemanticGroupDependency((SemanticGroupDependency) sgd);
			Map<String, InstAttribute> tmp = ic.getInstAttributes();
			Iterator<InstAttribute> ias = ic.getInstAttributes().values()
					.iterator();
			while (ias.hasNext()) {
				InstAttribute ia = (InstAttribute) ias.next();
				AbstractAttribute toSet = sgd.getSemanticAttribute(ia
						.getAttributeName());
				if (toSet == null)
					ia.setAttribute(mc.getAbstractAttribute(ia
							.getAttributeName()));
				else
					ia.setAttribute(toSet);
			}
		}

	}
}
