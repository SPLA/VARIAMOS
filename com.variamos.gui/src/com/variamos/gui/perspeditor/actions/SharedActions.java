package com.variamos.gui.perspeditor.actions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.PerspEditorGraph;
import com.variamos.perspsupport.expressionsupport.InstanceExpression;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstCell;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstOverTwoRelation;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.instancesupport.InstVertex;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.perspsupport.semanticinterface.IntSemanticElement;
import com.variamos.perspsupport.semanticinterface.IntSemanticRelationType;
import com.variamos.perspsupport.semanticsupport.SemanticPairwiseRelation;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.syntaxsupport.MetaOverTwoRelation;
import com.variamos.perspsupport.syntaxsupport.MetaPairwiseRelation;
import com.variamos.perspsupport.syntaxsupport.MetaVertex;

public class SharedActions {

	private static Set<String> instAttributesToDelete = new HashSet<String>();
	private static boolean additionAttributes = false;

	public static mxGraph cloneGraph(mxGraph source, mxGraph target,
			int modelViewIndex, int modelViewSubIndex) {
		setVisibleViews(source.getModel(), true, 0, 0);
		if (target == null)
			target = new mxGraph();
		target.selectAll();
		target.removeCells();
		target.addCells(source.cloneCells(source.getChildCells(source
				.getDefaultParent())));
		setVisibleViews(source.getModel(), false, modelViewIndex,
				modelViewSubIndex);
		setVisibleViews(target.getModel(), false, modelViewIndex,
				modelViewSubIndex);
		return target;
	}

	public static mxGraph beforeGraphOperation(mxGraph graph,
			boolean beforeSave, int modelViewIndex, int modelViewSubIndex) {
		((PerspEditorGraph) graph).setValidation(false);

		long startTime = System.currentTimeMillis();
		((PerspEditorGraph) graph).setValidation(false);
		mxGraph outGraph = cloneGraph(graph, null, modelViewIndex,
				modelViewSubIndex);
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("clone clean: " + elapsedTime);
		startTime = System.currentTimeMillis();

		mxIGraphModel refasGraph = outGraph.getModel();
		if (graph instanceof PerspEditorGraph) {

			Object o = refasGraph.getRoot(); // Main Root
			mxCell o1 = (mxCell) refasGraph.getChildAt(o, 0); // Null Root
			for (int i = 0; i < o1.getChildCount(); i++) {
				mxCell mv = (mxCell) refasGraph.getChildAt(o1, i);
				for (int j = 0; j < mv.getChildCount(); j++) {
					mxCell concept = (mxCell) refasGraph.getChildAt(mv, j);
					InstElement value = (InstElement) ((InstCell) concept
							.getValue()).getInstElement();
					for (int k = 0; k < concept.getChildCount(); k++) {
						mxCell concept2 = (mxCell) refasGraph.getChildAt(
								concept, k);
						InstElement value2 = (InstElement) ((InstCell) concept2
								.getValue()).getInstElement();
						updateIdAndObjects(value2, beforeSave);

					}
					updateIdAndObjects(value, beforeSave);
				}
			}
		}
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println("object clean: " + elapsedTime);
		return outGraph;
	}

	public static void setVisibleViews(mxIGraphModel refasGraph,
			boolean showAll, int modelViewIndex, int modelViewSubIndex) {

		Object o = refasGraph.getRoot(); // Main Root
		Object o1 = refasGraph.getChildAt(o, 0); // Null Root
		for (int mvInd = 0; mvInd < refasGraph.getChildCount(o1); mvInd++) {
			mxCell mv0 = (mxCell) refasGraph.getChildAt(o1, mvInd); // View root
			if (refasGraph.getChildCount(mv0) > 0) {
				mxCell child = (mxCell) refasGraph.getChildAt(mv0, 0);
				if (child.getId().startsWith(mv0.getId())) {
					for (int mvSubInd = 0; mvSubInd < refasGraph
							.getChildCount(mv0); mvSubInd++) {
						mxCell mv00 = (mxCell) refasGraph.getChildAt(mv0,
								mvSubInd);
						if (refasGraph.getChildCount(mv00) > 0) {
							if (!showAll
									&& (modelViewIndex != mvInd || (modelViewSubIndex != -1 && modelViewSubIndex != mvSubInd)))
								refasGraph.setVisible(mv00, false);
							else
								refasGraph.setVisible(mv00, true);
						}
					}
				} else {
					if (modelViewIndex != mvInd && !showAll)
						refasGraph.setVisible(mv0, false);
					else
						refasGraph.setVisible(mv0, true);
				}
			}
		}
	}

	private static void updateIdAndObjects(Object value, boolean beforeSave) {
		if (value instanceof InstOverTwoRelation) {
			InstOverTwoRelation ic = (InstOverTwoRelation) value;
			String str = null;// (String) ic.getSemanticOverTwoRelationIden();
			ic.setSemanticOverTwoRelationIden(str);
			str = (String) ic.getSupportMetaElementIdentifier();
			ic.setMetaOverTwoRelationIden(str);
			if (beforeSave) {
				ic.clearEditableMetaVertex();
				ic.clearInstAttributesObjects();
			}
		} else if (value instanceof InstVertex && beforeSave) {
			InstVertex instVertex = (InstVertex) value;
			instVertex.clearEditableMetaVertex();
			instVertex.clearInstAttributesObjects();
		}

		if (value instanceof InstPairwiseRelation) {
			InstPairwiseRelation ic = (InstPairwiseRelation) value;
			ic.updateIdentifiers();
			if (beforeSave) {
				ic.clearMetaPairwiseRelation();
				ic.clearEditableMetaVertex();
				// ic.clearRelations();
				ic.clearInstAttributesObjects();
			}

		}

	}

	public static void beforeLoadGraph(mxGraph graph, VariamosGraphEditor editor) {
		if (graph instanceof PerspEditorGraph) {
			((PerspEditorGraph) graph).setValidation(false);
		}
	}

	public static void afterSaveGraph(mxGraph graph, VariamosGraphEditor editor) {
		if (graph instanceof PerspEditorGraph) {
			((PerspEditorGraph) graph).setValidation(true);
		}
	}

	/**
	 * Load metamodel/semantic objects after loading or cloning the graph
	 * 
	 * @param graph
	 * @param editor
	 * @return
	 */
	public static mxGraph afterOpenCloneGraph(mxGraph graph,
			VariamosGraphEditor editor) {

		setVisibleViews(graph.getModel(), true, 0, 0);
		mxIGraphModel refasGraph = graph.getModel();
		instAttributesToDelete = new HashSet<String>();
		additionAttributes = false;
		if (graph instanceof PerspEditorGraph) {
			Object o = refasGraph.getRoot(); // Main Root
			mxCell o1 = (mxCell) refasGraph.getChildAt(o, 0);
			Object[] all0Cells = getSortedCells(graph, o1);
			for (Object any0Cell : all0Cells) {
				// Root model view mvInd
				mxCell mv0 = (mxCell) any0Cell;

				if (refasGraph.getChildCount(mv0) > 0) {
					// First vertices and after edges
					Object[] all1Cells = getSortedCells(graph, mv0);

					for (Object any1Cell : all1Cells) {
						// for (int i = 0; i < refasGraph.getChildCount(mv0);
						// i++) {
						// mxCell mv1 = (mxCell) refasGraph.getChildAt(mv0, i);
						mxCell mv1 = (mxCell) any1Cell;
						if (refasGraph.getChildCount(mv1) > 0
						// && mv0.getChildAt(0).getValue()
						// .equals(mv0.getValue())
						) {
							Object[] all2Cells = getSortedCells(graph, mv1);
							for (Object any2Cell : all2Cells) {
								mxCell mv2 = (mxCell) any2Cell;
								try {
									loadSupportObjects(editor, mv2.getValue(),
											mv2, graph);
								} catch (Exception e) {
									e.printStackTrace();
									System.err.println(mv2.getValue()
											.toString());
								}
							}
						} else
							try {
								loadSupportObjects(editor, mv1.getValue(), mv1,
										graph);
							} catch (Exception e) {
								e.printStackTrace();
								System.err.println(mv1.getValue().toString());
							}
					}
				} else
					try {
						loadSupportObjects(editor, mv0.getValue(), mv0, graph);
					} catch (Exception e) {
						e.printStackTrace();
						System.err.println(mv0.getValue().toString());
					}
			}
			((PerspEditorGraph) graph).setValidation(true);
		}

		setVisibleViews(graph.getModel(), false, 0, -1);
		if (instAttributesToDelete.size() > 0)
			JOptionPane
					.showMessageDialog(
							editor.getFrame(),
							"The model loaded contains a set concept attributes "
									+ instAttributesToDelete.toString()
									+ "\n that are not supported by the current version of VariaMos."
									+ "\n If you save this model, all the non supported attributes will be permanently lost.",
							"Incompatible Model Message",
							JOptionPane.INFORMATION_MESSAGE, null);

		if (additionAttributes)
			JOptionPane
					.showMessageDialog(
							editor.getFrame(),
							"New concept attributes to make the model compatible with the current \n"
									+ "version of VariaMos were added. Saving this file will make it incompatible"
									+ "\n with older versions of the tool.",
							"Incompatible Model Message",
							JOptionPane.INFORMATION_MESSAGE, null);
		return graph;
	}

	public static InstElement getOriginalInstElement(mxGraph graph,
			mxCell cloned) {
		mxIGraphModel refasGraph = graph.getModel();
		String cellId = cloned.getId();
		int subview = cellId.indexOf("-");
		String id = subview == -1 ? cellId.substring(1, cellId.length())
				: cellId.substring(1, subview);
		if (graph instanceof PerspEditorGraph) {
			Object o = refasGraph.getRoot(); // Main Root
			Object o1 = refasGraph.getChildAt(o, 0); // Null Root
			for (int mvInd = 0; mvInd < refasGraph.getChildCount(o1); mvInd++) {
				// Root model view mvInd
				mxCell mv0 = (mxCell) refasGraph.getChildAt(o1, mvInd);
				// First vertices and after edges
				Object[] allCells = getSortedCells(graph, mv0);

				for (Object anyCell : allCells) {
					mxCell mv1 = (mxCell) anyCell;
					if (refasGraph.getChildCount(mv1) > 0
					// && mv0.getChildAt(0).getValue()
					// .equals(mv0.getValue())
					) {
						Object[] all2Cells = getSortedCells(graph, mv1);
						for (Object any2Cell : all2Cells) {
							mxCell mv2 = (mxCell) any2Cell;
							String cellId2 = mv2.getId();
							int subview2 = cellId2.indexOf("-");
							String id2 = subview2 == -1 ? cellId2.substring(1,
									cellId2.length()) : cellId2.substring(1,
									subview2);
							if (id.equals(id2)
									&& !((InstCell) mv2.getValue()).isCloned())
								return ((InstCell) mv2.getValue())
										.getInstElement();
						}
					} else {
						mxCell mv2 = (mxCell) anyCell;
						String cellId2 = mv2.getId();
						int subview2 = cellId2.indexOf("-");
						String id2 = subview2 == -1 ? cellId2.substring(1,
								cellId2.length()) : cellId2.substring(1,
								subview2);
						if (id.equals(id2)
								&& !((InstCell) mv2.getValue()).isCloned())
							return ((InstCell) mv2.getValue()).getInstElement();
					}

				}
			}
		}
		return null;
	}

	private static Object[] getSortedCells(mxGraph graph, mxCell mv0) {
		Object[] vertexCells = graph.getChildCells(mv0, true, false);
		Object[] edgeCells = graph.getChildCells(mv0, false, true);
		Object[] mvCells = graph.getChildCells(mv0, false, false);
		Object[] allCells = new Object[vertexCells.length + edgeCells.length
				+ mvCells.length];

		System.arraycopy(vertexCells, 0, allCells, 0, vertexCells.length);
		System.arraycopy(edgeCells, 0, allCells, vertexCells.length,
				edgeCells.length);

		System.arraycopy(mvCells, 0, allCells, vertexCells.length
				+ edgeCells.length, mvCells.length);
		return allCells;
	}

	private static void loadSupportObjects(VariamosGraphEditor editor,
			Object value, mxCell source, mxGraph graph) {
		RefasModel refas = ((PerspEditorGraph) editor.getGraphComponent().getGraph())
				.getRefas();
		InstCell instCell = ((InstCell) value);
		InstElement instElement = null;
		if (instCell.isCloned()) {
			instElement = getOriginalInstElement(graph, source);
			instCell.setInstElement(instElement);
		} else
			instElement = instCell.getInstElement();

		if (instElement instanceof InstOverTwoRelation) {
			InstOverTwoRelation instOverTwoRelation = (InstOverTwoRelation) instElement;
			InstElement instVertex = refas.getSyntaxRefas().getVertex(
					instOverTwoRelation.getSupportMetaElementIdentifier());
			if (instVertex == null) {
				System.err
						.println("OverTwoRel Null"
								+ instOverTwoRelation
										.getSupportMetaElementIdentifier());
				return;
			} else {
				MetaOverTwoRelation metaOverTwoRelation = (MetaOverTwoRelation) instVertex
						.getEditableMetaElement();
				instOverTwoRelation
						.setTransSupportMetaElement(metaOverTwoRelation);
			}
			refas.putInstGroupDependency(instOverTwoRelation);
			Iterator<InstAttribute> ias = instOverTwoRelation
					.getInstAttributes().values().iterator();
			// System.out.println(instOverTwoRelation.getInstAttributes().size());
			while (ias.hasNext()) {
				InstAttribute ia = (InstAttribute) ias.next();
				AbstractAttribute attribute = instOverTwoRelation
						.getAbstractAttribute(ia.getAttributeName());
				if (attribute != null) {
					ia.setAttribute(attribute);

					List<IntSemanticRelationType> semGD = ((MetaOverTwoRelation) instOverTwoRelation
							.getTransSupportMetaElement())
							.getSemanticRelationTypes();
					ia.setValidationRelationTypes(semGD);
					if (ia.getAttributeType().equals("Boolean")
							&& ia.getValue() instanceof String)
						if (((String) ia.getValue()).equals("0"))
							ia.setValue(false);
						else
							ia.setValue(true);
				} else {
					instAttributesToDelete.add(ia.getAttributeName());
					ias.remove();
				}
			}
			if (instOverTwoRelation.getInstAttributes().size() < instOverTwoRelation
					.getTransSupportMetaElement().getModelingAttributes()
					.size()
					+ instOverTwoRelation.getTransSupportMetaElement()
							.getAllAttributesNames().size()) {
				for (String attributeName : instOverTwoRelation
						.getTransSupportMetaElement().getAllAttributesNames()) {
					if (instOverTwoRelation.getInstAttribute(attributeName) == null
							&& instOverTwoRelation.getTransSupportMetaElement()
									.getSemanticAttribute(attributeName) != null) {
						instOverTwoRelation.addInstAttribute(attributeName,
								instOverTwoRelation
										.getTransSupportMetaElement()
										.getSemanticAttribute(attributeName),
								null);
						// System.out.println("create" + attributeName);
						additionAttributes = true;
					} else if (instOverTwoRelation
							.getInstAttribute(attributeName) == null) {
						instOverTwoRelation.addInstAttribute(attributeName,
								instOverTwoRelation
										.getTransSupportMetaElement()
										.getModelingAttribute(attributeName),
								null);
						// System.out.println("create" + attributeName);
						additionAttributes = true;
					}
				}
			}
			editor.refreshElement(instOverTwoRelation);
		} else if (instElement instanceof InstVertex) {
			InstVertex instVertex = (InstVertex) instElement;
			MetaVertex metaVertex = (MetaVertex) refas.getSyntaxRefas()
					.getVertex(instVertex.getSupportMetaElementIdentifier())
					.getEditableMetaElement();
			if (metaVertex == null)
				System.err.println("Concept Null"
						+ instVertex.getSupportMetaElementIdentifier());
			else
				instVertex.setTransSupportMetaElement(metaVertex);
			refas.putVariabilityInstVertex(instVertex);
			Iterator<InstAttribute> ias = instVertex.getInstAttributes()
					.values().iterator();
			while (ias.hasNext()) {
				InstAttribute ia = (InstAttribute) ias.next();
				AbstractAttribute attribute = metaVertex
						.getAbstractAttribute(ia.getAttributeName());
				if (attribute != null) {
					ia.setAttribute(attribute);
					if (ia.getAttributeType().equals("Boolean")
							&& ia.getValue() instanceof String)
						if (((String) ia.getValue()).equals("0"))
							ia.setValue(false);
						else
							ia.setValue(true);
					if (ia.getIdentifier().equals("ConditionalExpression")) {
						InstanceExpression instanceExpression = (InstanceExpression) ia
								.getValue();
						if (instanceExpression != null)
							instanceExpression.loadVolatileElements(refas
									.getVariabilityVertex());
					}
				} else {
					instAttributesToDelete.add(ia.getAttributeName());
					ias.remove();
				}
			}
			int semAtt = 0;
			if (instVertex.getTransSupportMetaElement().getAllAttributesNames() != null)
				semAtt = instVertex.getTransSupportMetaElement()
						.getAllAttributesNames().size();
			if (instVertex.getInstAttributes().size() < semAtt) {
				// TODO modify to support syntax attributes changes
				for (String attributeName : instVertex
						.getTransSupportMetaElement().getAllAttributesNames()) {
					if (instVertex.getInstAttribute(attributeName) == null
							&& instVertex.getTransSupportMetaElement()
									.getSemanticAttribute(attributeName) != null) {
						instVertex.addInstAttribute(attributeName, instVertex
								.getTransSupportMetaElement()
								.getSemanticAttribute(attributeName), null);
						// System.out.println("create" + attributeName);
						additionAttributes = true;
					} else if (instVertex.getInstAttribute(attributeName) == null) {
						instVertex.addInstAttribute(attributeName, instVertex
								.getTransSupportMetaElement()
								.getModelingAttribute(attributeName), null);
						// System.out.println("create" + attributeName);
						additionAttributes = true;
					}
				}
			}
		}
		if (instElement instanceof InstPairwiseRelation) {
			try {
				InstPairwiseRelation instPairwiseRelation = (InstPairwiseRelation) instElement;
				// instPairwiseRelation
				// .createAttributes(new HashMap<String, InstAttribute>());
				InstVertex sourceVertex = (InstVertex) ((InstCell) source
						.getSource().getValue()).getInstElement();
				InstVertex targetVertex = (InstVertex) ((InstCell) source
						.getTarget().getValue()).getInstElement();
				if (sourceVertex == null || targetVertex == null) {
					System.out.println("Error load" + source.getId());
					return;
				}
				MetaPairwiseRelation metaPairwiseRelation = refas
						.getSyntaxRefas().getValidMetaPairwiseRelation(
								sourceVertex.getTransSupportMetaElement(),
								targetVertex.getTransSupportMetaElement(),
								instPairwiseRelation
										.getSupportMetaPairwiseRelIden(), true);
				instPairwiseRelation.setSourceRelation(sourceVertex, true);
				instPairwiseRelation.setTargetRelation(targetVertex, true);
				if (metaPairwiseRelation != null) {
					instPairwiseRelation
							.setSupportMetaPairwiseRelation(metaPairwiseRelation);
					instPairwiseRelation.setUpdatePairwiseRelationType();
					instPairwiseRelation.setDynamicVariable("relationType",
							instPairwiseRelation.getSemanticPairwiseRelType());

					Iterator<InstAttribute> instAttributesIter = instPairwiseRelation
							.getInstAttributes().values().iterator();
					while (instAttributesIter.hasNext()) {
						try {

							InstAttribute instAttribute = (InstAttribute) instAttributesIter
									.next();

							AbstractAttribute absAttribute = metaPairwiseRelation
									.getAbstractAttribute(instAttribute
											.getAttributeName());
							if (absAttribute == null)
								absAttribute = instPairwiseRelation
										.getSemanticAttribute();
							if (absAttribute != null) {
								instAttribute.setAttribute(absAttribute);
								// if (absAttribute != null)// TODO find a
								// better
								// fix
								if (instAttribute.getAttributeType().equals(
										"Boolean")
										&& instAttribute.getValue() != null
										&& instAttribute.getValue() instanceof String)
									if (((String) instAttribute.getValue())
											.equals("0"))
										instAttribute.setValue(false);
									else
										instAttribute.setValue(true);
								if (instAttribute
										.getIdentifier()
										.equals(SemanticPairwiseRelation.VAR_RELATIONTYPE_IDEN))
									instAttribute.setValue(instPairwiseRelation
											.getSemanticPairwiseRelType());
								List<IntSemanticRelationType> semGD = ((MetaPairwiseRelation) instPairwiseRelation
										.getTransSupportMetaElement())
										.getSemanticRelationTypes();
								instAttribute.setValidationRelationTypes(semGD);
							} else {
								instAttributesToDelete.add(instAttribute
										.getAttributeName());
								instAttributesIter.remove();
							}

						} catch (Exception e) {
							System.err.println("Contained exception");
							e.printStackTrace();
						}
					}
					if (instPairwiseRelation.getInstAttributes().size() < instPairwiseRelation
							.getTransSupportMetaElement()
							.getAllAttributesNames().size()
							+ instPairwiseRelation.getTransSupportMetaElement()
									.getModelingAttributes().size()) {
						for (String attributeName : instPairwiseRelation
								.getTransSupportMetaElement()
								.getAllAttributesNames()) {
							if (instPairwiseRelation
									.getInstAttribute(attributeName) == null
									&& instPairwiseRelation
											.getTransSupportMetaElement()
											.getSemanticAttribute(attributeName) != null) {
								instPairwiseRelation.addInstAttribute(
										attributeName,
										instPairwiseRelation
												.getTransSupportMetaElement()
												.getSemanticAttribute(
														attributeName), null);
								// System.out.println("create" + attributeName);
								additionAttributes = true;
							} else if (instPairwiseRelation
									.getInstAttribute(attributeName) == null) {
								instPairwiseRelation.addInstAttribute(
										attributeName,
										instPairwiseRelation
												.getTransSupportMetaElement()
												.getModelingAttribute(
														attributeName), null);
								// System.out.println("create" + attributeName);
								additionAttributes = true;
							}
						}

					}
				}
				// TODO add edges to groupDependecies and claims to
				// otherInstEdges
				refas.putConstraintInstEdge(instPairwiseRelation);

				editor.refreshElement(instPairwiseRelation);
			} catch (Exception e) {
				System.err.println("Contained exception");
				e.printStackTrace();
			}
		}
	}

	public static boolean validateConceptType(InstElement instElement,
			String element) {
		if (instElement == null || !(instElement instanceof InstVertex))
			return false;
		MetaVertex metaElement = ((MetaVertex) instElement
				.getTransSupportMetaElement());
		if (metaElement == null)
			return false;
		IntSemanticElement semElement = metaElement.getTransSemanticConcept();
		while (semElement != null && semElement.getIdentifier() != null
				&& !semElement.getIdentifier().equals(element))
			semElement = semElement.getParent();
		if (semElement != null && semElement.getIdentifier() != null
				&& semElement.getIdentifier().equals(element)) {
			return true;
		}
		return false;
	}
}
