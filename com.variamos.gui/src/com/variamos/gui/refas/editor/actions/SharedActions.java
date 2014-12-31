package com.variamos.gui.refas.editor.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.refas.editor.RefasGraph;
import com.variamos.refas.core.refas.Refas;
import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticElement;
import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticVertex;
import com.variamos.refas.core.sematicsmetamodel.SemanticPairwiseRelation;
import com.variamos.refas.core.sematicsmetamodel.SemanticOverTwoRelation;
import com.variamos.refas.core.sematicsmetamodel.SemanticVariable;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;
import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.MetaPairwiseRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaOverTwoRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaVertex;
import com.variamos.syntaxsupport.metamodelsupport.ModelingAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticOverTwoRelation;

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
					for (int k = 0; k < concept.getChildCount(); k++) {
						mxCell concept2 = (mxCell) refasGraph.getChildAt(
								concept, k);
						Object value2 = concept2.getValue();
						deleteSupportObjects(value2);

					}
					deleteSupportObjects(value);
				}
			}
		}
		return graph;
	}

	private static void deleteSupportObjects(Object value) {
		if (value instanceof InstOverTwoRelation) {
			InstOverTwoRelation ic = (InstOverTwoRelation) value;
			String str = (String) ic.getSemanticOverTwoRelationIden();
			ic.setSemanticOverTwoRelationIden(str);
			ic.clearMetaVertex();
			ic.clearInstAttributesObjects();
		} else if (value instanceof InstVertex) {
			InstVertex instVertex = (InstVertex) value;
			instVertex.clearMetaVertex();
			instVertex.clearInstAttributesObjects();
		}

		if (value instanceof InstPairwiseRelation) {
			InstPairwiseRelation ic = (InstPairwiseRelation) value;
			ic.updateIdentifiers();
			ic.clearMetaPairwiseRelation();
			ic.clearRelations();
			ic.clearInstAttributesClassObjects();
		}

	}

	/**
	 * Load metamodel objects after saving or loading the graph
	 * 
	 * @param graph
	 * @param editor
	 * @return
	 */
	public static mxGraph afterSaveGraph(mxGraph graph,
			VariamosGraphEditor editor) {
		mxIGraphModel refasGraph = graph.getModel();
		if (graph instanceof RefasGraph) {
			Object o = refasGraph.getRoot(); // Main Root
			Object o1 = refasGraph.getChildAt(o, 0); // Null Root
			for (int mvInd = 0; mvInd < refasGraph.getChildCount(o1); mvInd++) {
				mxCell mv0 = (mxCell) refasGraph.getChildAt(o1, mvInd);
				// Root model view mvInd
				if (refasGraph.getChildCount(mv0) > 0
						&& mv0.getChildAt(0).getValue().equals(mv0.getValue())) {
					for (int i = 0; i < refasGraph.getChildCount(mv0); i++) {
						mxCell mv1 = (mxCell) refasGraph.getChildAt(mv0, i);
						for (int j = 0; j < refasGraph.getChildCount(mv1); j++) {
							mxCell mv2 = (mxCell) refasGraph.getChildAt(mv1, j);
							loadSupportObjects(editor, mv2.getValue(), mv2,
									graph);
						}
					}
				} else
					for (int i = 0; i < refasGraph.getChildCount(mv0); i++) {
						mxCell mv1 = (mxCell) refasGraph.getChildAt(mv0, i);
						loadSupportObjects(editor, mv1.getValue(), mv1, graph);
					}
			}
		}
		return graph;
	}

	private static void loadSupportObjects(VariamosGraphEditor editor,
			Object value, mxCell source, mxGraph graph) {
		Refas refas = ((RefasGraph) editor.getGraphComponent().getGraph())
				.getRefas();
		if (value instanceof InstOverTwoRelation) {
			InstOverTwoRelation ic = (InstOverTwoRelation) value;
			MetaOverTwoRelation mgd = (MetaOverTwoRelation) editor
					.getSematicSintaxObject().getSyntaxElement(
							ic.getMetaVertexIdentifier());
			IntSemanticElement sgd = editor.getSematicSintaxObject()
					.getSemanticElement(ic.getSemanticOverTwoRelationIden());
			ic.setMetaVertex(mgd);
			refas.putInstGroupDependency(ic);
			if (sgd != null)
				ic.setSemanticOverTwoRelation((SemanticOverTwoRelation) sgd);
			Iterator<InstAttribute> ias = ic.getInstAttributes().values()
					.iterator();
			while (ias.hasNext()) {
				InstAttribute ia = (InstAttribute) ias.next();
				AbstractAttribute toSet = sgd.getSemanticAttribute(ia
						.getAttributeName());
				if (toSet == null)
					if (ia.getAttributeName().equals(
							MetaOverTwoRelation.VAR_SEMANTICPAIRWISEREL)) {
						MetaElement n = editor.getSematicSintaxObject()
								.getSyntaxElements().get(mgd.getName());
						AbstractAttribute m = n
								.getModelingAttribute(InstOverTwoRelation.VAR_SEMANTICOVERTWOREL_OBJ);
						ia.setAttribute(m);
						List<IntSemanticOverTwoRelation> semGD = ((MetaOverTwoRelation) n)
								.getSemanticRelations();
						ia.setValidationGDList(semGD);
					} else {
						AbstractAttribute a = ((InstOverTwoRelation) value)
								.getAbstractAttribute(ia.getAttributeName());
						ia.setAttribute(a);
					}
				else
					ia.setAttribute(toSet);
			}
		} else if (value instanceof InstVertex) {
			InstVertex ic = (InstVertex) value;
			MetaVertex mc = (MetaVertex) editor.getSematicSintaxObject()
					.getSyntaxElement(ic.getMetaVertexIdentifier());
			if (mc == null)
				System.err.println("Concept Null"
						+ ic.getMetaVertexIdentifier());
			else
				ic.setMetaVertex(mc);
			refas.putVariabilityInstVertex(ic);
			Iterator<InstAttribute> ias = ic.getInstAttributes().values()
					.iterator();
			while (ias.hasNext()) {
				InstAttribute ia = (InstAttribute) ias.next();
				ia.setAttribute(mc.getAbstractAttribute(ia.getAttributeName()));

			}
		}
		if (value instanceof InstPairwiseRelation) {
			InstPairwiseRelation ic = (InstPairwiseRelation) value;
			MetaPairwiseRelation me = (MetaPairwiseRelation) editor.getSematicSintaxObject()
					.getSyntaxElement(ic.getMetaPairwiseRelationIden());
			SemanticPairwiseRelation semanticEdgeIde = (SemanticPairwiseRelation) editor
					.getSematicSintaxObject().getSemanticElement(
							ic.getSemanticPairwiseRelationIde());
			InstVertex from = (InstVertex) source.getSource().getValue();
			InstVertex to = (InstVertex) source.getTarget().getValue();
			ic.setSourceRelation(from, true);
			ic.setTargetRelation(to, true);
			if (me != null) {
				ic.setMetaPairwiseRelation(me);
				if (semanticEdgeIde != null) {
					ic.setSemanticEdge(semanticEdgeIde);
					ic.loadSemantic();
				}
			}
			// TODO add edges to groupDependecies and claims to otherInstEdges
			refas.putConstraintInstEdge(ic);
		}

	}

}
