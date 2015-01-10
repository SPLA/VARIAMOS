package com.variamos.gui.refas.editor.actions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.CellEditor;

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
			str = (String) ic.getSupportMetaElementIdentifier();
			ic.setMetaOverTwoRelationIden(str);
			ic.clearEditableMetaVertex();
			ic.clearInstAttributesObjects();
		} else if (value instanceof InstVertex) {
			InstVertex instVertex = (InstVertex) value;
			instVertex.clearEditableMetaVertex();
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
							try{
							loadSupportObjects(editor, mv2.getValue(), mv2,
									graph);
							}
							catch(Exception e)
							{								
								e.printStackTrace();
								System.err.println(mv2.getValue().toString());
							}
						}
					}
				} else
					for (int i = 0; i < refasGraph.getChildCount(mv0); i++) {
						mxCell mv1 = (mxCell) refasGraph.getChildAt(mv0, i);
						try{
						loadSupportObjects(editor, mv1.getValue(), mv1, graph);
					}
					catch(Exception e)
					{
						e.printStackTrace();
						System.err.println(mv1.getValue().toString());
					}
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
			InstOverTwoRelation instOverTwoRelation = (InstOverTwoRelation) value;
			MetaOverTwoRelation metaOverTwoRelation = (MetaOverTwoRelation) refas
					.getSyntaxRefas()
					.getVertex(instOverTwoRelation.getSupportMetaElementIdentifier())
					.getEditableMetaElement();
			instOverTwoRelation.setSupportMetaElement(metaOverTwoRelation);
			refas.putInstGroupDependency(instOverTwoRelation);
			Iterator<InstAttribute> ias = instOverTwoRelation
					.getInstAttributes().values().iterator();
			while (ias.hasNext()) {
				InstAttribute ia = (InstAttribute) ias.next();
				ia.setAttribute(instOverTwoRelation.getAbstractAttribute(ia
						.getAttributeName()));
				List<IntSemanticRelationType> semGD = ((MetaOverTwoRelation) instOverTwoRelation
						.getSupportMetaElement()).getSemanticRelationTypes();
				ia.setValidationRelationTypes(semGD);
				if (ia.getAttributeType().equals("Boolean")
						&& ia.getValue() instanceof String)
					if (((String) ia.getValue()).equals("0"))
						ia.setValue(false);
					else
						ia.setValue(true);
			}
			editor.refreshElement(instOverTwoRelation);
		} else if (value instanceof InstVertex) {
			InstVertex instVertex = (InstVertex) value;
			MetaVertex metaVertex = (MetaVertex) refas.getSyntaxRefas()
					.getVertex(instVertex.getSupportMetaElementIdentifier())
					.getEditableMetaElement();
			if (metaVertex == null)
				System.err.println("Concept Null"
						+ instVertex.getSupportMetaElementIdentifier());
			else
				instVertex.setSupportMetaElement(metaVertex);
			refas.putVariabilityInstVertex(instVertex);
			Iterator<InstAttribute> ias = instVertex.getInstAttributes()
					.values().iterator();
			while (ias.hasNext()) {
				InstAttribute ia = (InstAttribute) ias.next();
				ia.setAttribute(metaVertex.getAbstractAttribute(ia
						.getAttributeName()));
				if (ia.getAttributeType().equals("Boolean")
						&& ia.getValue() instanceof String)
					if (((String) ia.getValue()).equals("0"))
						ia.setValue(false);
					else
						ia.setValue(true);

			}
		}
		if (value instanceof InstPairwiseRelation) {
			try{
				InstPairwiseRelation instPairwiseRelation = (InstPairwiseRelation) value;
						instPairwiseRelation.createAttributes(new HashMap<String, InstAttribute>());
			InstVertex sourceVertex = (InstVertex) source.getSource()
					.getValue();
			InstVertex targetVertex = (InstVertex) source.getTarget()
					.getValue();
			MetaPairwiseRelation metaPairwiseRelation = refas.getSyntaxRefas()
					.getValidMetaPairwiseRelation(sourceVertex.getSupportMetaElement(),
							targetVertex.getSupportMetaElement(),
							instPairwiseRelation.getSupportMetaPairwiseRelIden(),
							true);
			instPairwiseRelation.setSourceRelation(sourceVertex, true);
			instPairwiseRelation.setTargetRelation(targetVertex, true);
			if (metaPairwiseRelation != null) {
				instPairwiseRelation.setSupportMetaPairwiseRelation(
						metaPairwiseRelation);
				instPairwiseRelation.setUpdatePairwiseRelationType();
				instPairwiseRelation.setVariable(
						MetaPairwiseRelation.VAR_METAPAIRWISERELTYPE,
						instPairwiseRelation.getSemanticPairwiseRelType());

				Iterator<InstAttribute> instAttributesIter = instPairwiseRelation
						.getInstAttributes().values().iterator();
				while (instAttributesIter.hasNext()) {
					try{
						
					InstAttribute instAttribute = (InstAttribute) instAttributesIter
							.next();

					AbstractAttribute absAttribute = metaPairwiseRelation.getAbstractAttribute(instAttribute
									.getAttributeName());
					if (absAttribute == null)
						absAttribute = instPairwiseRelation.getSemanticAttribute();
					instAttribute.setAttribute(absAttribute);
				//	if (absAttribute != null)// TODO find a better fix
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
								.getSemanticPairwiseRelType());
					if (instAttribute.getIdentifier().equals(
							"relationType"))
						instAttribute.setValue(instPairwiseRelation
								.getSemanticPairwiseRelType());
					List<IntSemanticRelationType> semGD = ((MetaPairwiseRelation) instPairwiseRelation
							.getSupportMetaElement()).getSemanticRelationTypes();
					instAttribute.setValidationRelationTypes(semGD);
					}					
					catch (Exception e)
					{
						System.err.println("Contained exception");
						e.printStackTrace();
					}
				}

			}
			// TODO add edges to groupDependecies and claims to otherInstEdges
			refas.putConstraintInstEdge(instPairwiseRelation);

			editor.refreshElement(instPairwiseRelation);
			}
			catch (Exception e)
			{
				System.err.println("Contained exception");
				e.printStackTrace();
			}
		}

	}
}
