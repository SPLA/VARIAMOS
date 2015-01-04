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
import com.variamos.syntaxsupport.semanticinterface.IntSemanticRelationType;

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
			String str = null;// (String) ic.getSemanticOverTwoRelationIden();
			// ic.setSemanticOverTwoRelationIden(str);
			str = (String) ic.getMetaVertexIdentifier();
			ic.setMetaOverTwoRelationIden(str);
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
			MetaOverTwoRelation mgd = (MetaOverTwoRelation) refas
					.getSyntaxRefas().getVertex(ic.getMetaVertexIdentifier())
					.getEditableMetaElement();
			// IntSemanticElement sgd = editor.getSematicSintaxObject()
			// .getSemanticElement(ic.getSemanticOverTwoRelationIden());
			ic.setMetaVertex(mgd);
			refas.putInstGroupDependency(ic);
			// if (sgd != null)
			// ic.setSemanticOverTwoRelation((SemanticOverTwoRelation) sgd);
			Iterator<InstAttribute> ias = ic.getInstAttributes().values()
					.iterator();
			while (ias.hasNext()) {
				InstAttribute ia = (InstAttribute) ias.next();
				ia.setAttribute(ic.getAbstractAttribute(ia.getAttributeName()));
				List<IntSemanticRelationType> semGD = ((MetaOverTwoRelation) ic
						.getSupportMetaElement()).getSemanticRelationTypes();
				ia.setValidationRelationTypes(semGD);
				if (ia.getAttributeType().equals("Boolean")
						&& ia.getValue() instanceof String)
					if (((String) ia.getValue()).equals("0"))
						ia.setValue(false);
					else
						ia.setValue(true);
			}
			editor.refreshElement(ic);
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
				if (ia.getAttributeType().equals("Boolean")
						&& ia.getValue() instanceof String)
					if (((String) ia.getValue()).equals("0"))
						ia.setValue(false);
					else
						ia.setValue(true);

			}
		}
		if (value instanceof InstPairwiseRelation) {
			InstPairwiseRelation instPairwiseRelation = (InstPairwiseRelation) value;
			MetaPairwiseRelation metaPairwiseRelation = (MetaPairwiseRelation) editor
					.getSematicSintaxObject().getSyntaxElement(
							instPairwiseRelation.getMetaPairwiseRelationIden());
			SemanticPairwiseRelation semanticRelation = (SemanticPairwiseRelation) editor
					.getSematicSintaxObject().getSemanticElement(
							instPairwiseRelation
									.getSemanticPairwiseRelationIde());
			InstVertex sourceVertex = (InstVertex) source.getSource()
					.getValue();
			InstVertex targetVertex = (InstVertex) source.getTarget()
					.getValue();
			instPairwiseRelation.setSourceRelation(sourceVertex, true);
			instPairwiseRelation.setTargetRelation(targetVertex, true);
			if (metaPairwiseRelation != null) {
				instPairwiseRelation
						.setMetaPairwiseRelation(metaPairwiseRelation);
				instPairwiseRelation.setVariable(
						MetaPairwiseRelation.VAR_METAPAIRWISERELTYPE,
						instPairwiseRelation.getSemanticPairwiseRelationType());
				if (semanticRelation != null) {
					instPairwiseRelation.setSemanticEdge(semanticRelation);
					instPairwiseRelation.loadSemantic();
				}
			}
			// TODO add edges to groupDependecies and claims to otherInstEdges
			refas.putConstraintInstEdge(instPairwiseRelation);
			Iterator<InstAttribute> instAttributesIter = instPairwiseRelation
					.getInstAttributes().values().iterator();
			while (instAttributesIter.hasNext()) {
				InstAttribute instAttribute = (InstAttribute) instAttributesIter
						.next();
				AbstractAttribute absAttribute = metaPairwiseRelation
						.getModelingAttribute(instAttribute.getAttributeName());
				instAttribute.setAttribute(absAttribute);
				if (absAttribute != null)// TODO find a better fix
					if (instAttribute.getAttributeType().equals("Boolean")
							&& instAttribute.getValue() != null
							&& instAttribute.getValue() instanceof String)
						if (((String) instAttribute.getValue()).equals("0"))
							instAttribute.setValue(false);
						else
							instAttribute.setValue(true);
				if (instAttribute.getIdentifier().equals(
						MetaPairwiseRelation.VAR_METAPAIRWISERELTYPE))
					instAttribute.setValue(instPairwiseRelation
							.getSemanticPairwiseRelationType());
			}
		}

	}

}
