package com.variamos.gui.perspeditor.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.view.mxGraph;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstCell;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstOverTwoRel;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.instance.InstVertex;
import com.variamos.dynsup.interfaces.IntMetaExpression;
import com.variamos.dynsup.interfaces.IntOpersElement;
import com.variamos.dynsup.interfaces.IntOpersRelType;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelExpr;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersExpr;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.model.SyntaxOverTwoRel;
import com.variamos.dynsup.model.SyntaxPairwiseRel;
import com.variamos.dynsup.model.SyntaxVertex;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.PerspEditorGraph;

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
		mxGraph outGraph = graph;
		// cloneGraph(graph, null, modelViewIndex,
		// modelViewSubIndex);
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
				InstElement value3 = (InstElement) ((InstCell) mv.getValue())
						.getInstElement();
				for (int j = 0; j < mv.getChildCount(); j++) {
					mxCell concept = (mxCell) refasGraph.getChildAt(mv, j);
					InstElement value = (InstElement) ((InstCell) concept
							.getValue()).getInstElement();
					for (int k = 0; k < concept.getChildCount(); k++) {
						mxCell concept2 = (mxCell) refasGraph.getChildAt(
								concept, k);
						InstElement value2 = (InstElement) ((InstCell) concept2
								.getValue()).getInstElement();
						updateIdAndObjects(value2, beforeSave, true);

					}
					updateIdAndObjects(value, beforeSave, true);
				}
				updateIdAndObjects(value3, beforeSave, false);
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

	private static void updateIdAndObjects(Object value, boolean beforeSave,
			boolean model) {
		if (value instanceof InstElement) {
			InstElement instElement = (InstElement) value;
			if (value instanceof InstOverTwoRel) {
				InstOverTwoRel ic = (InstOverTwoRel) value;
				String str = null;
				ic.setSemanticOverTwoRelationIden(str);
				str = (String) ic.getSupportMetaElementIden();
				ic.setMetaOverTwoRelationIden(str);
			}
			if (value instanceof InstPairwiseRel) {
				((InstPairwiseRel) instElement).updateIdentifiers();
				if (beforeSave) {
					((InstPairwiseRel) instElement)
							.clearMetaPairwiseRelation();
				}
			}
			if (beforeSave && model) {
				instElement.clearEditableMetaVertex();
				instElement.clearInstAttributesObjects();
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
		editor.getGraphComponent().zoomActual();
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

		setVisibleViews(graph.getModel(), false, editor.getModelViewIndex(),
				editor.getModelSubViewIndex());
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

	public static mxGraph recoverClonedElements(mxGraph graph,
			VariamosGraphEditor editor) {

		setVisibleViews(graph.getModel(), true, 0, 0);
		mxIGraphModel refasGraph = graph.getModel();
		// editor.getGraphComponent().zoomActual();
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
									if (!((InstCell) mv2.getValue()).isCloned()) {
										// System.out.println(mv2.getValue());
										createClone(editor, mv2.getValue(),
												mv2, graph);
									}
								} catch (Exception e) {
									e.printStackTrace();
									System.err.println(mv2.getValue()
											.toString());
								}
							}
						} else
							try {

								if (!((InstCell) mv1.getValue()).isCloned()) {
									// System.out.println(mv1.getValue());
									createClone(editor, mv1.getValue(), mv1,
											graph);
								}
							} catch (Exception e) {
								e.printStackTrace();
								System.err.println(mv1.getValue().toString());
							}
					}
				} else
					try {

						if (!((InstCell) mv0.getValue()).isCloned()) {
							// System.out.println(mv0.getValue());
							createClone(editor, mv0.getValue(), mv0, graph);

						}
					} catch (Exception e) {
						e.printStackTrace();
						System.err.println(mv0.getValue().toString());
					}
			}
			((PerspEditorGraph) graph).setValidation(true);
		}

		setVisibleViews(graph.getModel(), false, editor.getModelViewIndex(),
				editor.getModelSubViewIndex());
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

	private static void createClone(VariamosGraphEditor editor, Object value,
			mxCell cell, mxGraph graph) {

		InstElement instElement = ((InstCell) value).getInstElement();
		String id = instElement.getIdentifier();
		int modelViewSubIndex = editor.getModelSubViewIndex();
		int modelViewIndex = editor.getModelViewIndex();
		// Move new element to the current View - clone if
		// necessary in multiple views
		mxGraphModel refasGraph = (mxGraphModel) graph.getModel();
		// Main Root
		Object rootCell = refasGraph.getRoot();
		// Null Root
		mxCell viewsParent = (mxCell) refasGraph.getChildAt(rootCell, 0);
		// Top level view
		Object topLevelView = null;
		// Parent to add new element
		mxCell correctParentCell = null;
		mxGraphModel model = refasGraph;
		// model.getCells().remove(cell.getId());
		if (modelViewSubIndex != -1) {
			// Top Level View
			topLevelView = refasGraph.getChildAt(viewsParent, modelViewIndex);
			// Child view
			correctParentCell = (mxCell) refasGraph.getChildAt(topLevelView,
					modelViewSubIndex);
		} else if (modelViewIndex != -1) {
			// Top Level view
			correctParentCell = (mxCell) refasGraph.getChildAt(viewsParent,
					modelViewIndex);
		} else {
			// Graph without Views
			// Add to the view Parent
			correctParentCell = viewsParent;
		}
		// Remove from original position
		// parent.remove(index);
		if (instElement instanceof InstVertex) {
			String name = null;
			if (instElement instanceof InstConcept) {
				InstConcept c = (InstConcept) instElement;
				name = c.getTransSupportMetaElement().getAutoIdentifier();
			}
			if (instElement instanceof InstOverTwoRel) {
				InstOverTwoRel c = (InstOverTwoRel) instElement;
				name = c.getSupportMetaOverTwoRelation().getAutoIdentifier();
			}
			viewsParent = (mxCell) refasGraph.getChildAt(rootCell, 0);
			// Null Root
			if (((ModelInstance) editor.getEditedModel())
					.getSyntaxModel()
					.elementsValidation(name, modelViewIndex, modelViewSubIndex)) {
				if ((refasGraph.getCell(modelViewIndex + id + "-"
						+ modelViewSubIndex) == null && modelViewSubIndex != -1)
						|| refasGraph.getCell(modelViewIndex + id) == null) {
					System.out.println("clone recovered" + id);
					mxCell c2 = null;
					try {
						c2 = (mxCell) cell.clone();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (modelViewSubIndex != -1)
						c2.setId(modelViewIndex + id + "-" + modelViewSubIndex);
					else
						c2.setId(modelViewIndex + id);

					c2.setValue(new InstCell(c2, ((InstCell) cell.getValue())
							.getInstElement(), true));

					model.add(correctParentCell, c2,
							correctParentCell.getChildCount());
				}
			}
			correctParentCell.setVisible(false);
			correctParentCell.setVisible(true);
		}
	}

	private static void loadSupportObjects(VariamosGraphEditor editor,
			Object value, mxCell source, mxGraph graph) {
		ModelInstance refas = ((PerspEditorGraph) editor.getGraphComponent()
				.getGraph()).getModelInstance();
		InstCell instCell = ((InstCell) value);
		InstElement instElement = null;
		instCell.setMxCell(source);
		if (instCell.isCloned()) {
			instElement = getOriginalInstElement(graph, source);
			if (instElement == null) {
				System.out.println("Unable to load clone:" + source.getId());
			}
			instCell.setInstElement(instElement);
		} else
			instElement = instCell.getInstElement();
		List<InstElement> syntaxParents = null;
		List<InstElement> opersParents = null;
		if (instElement != null) {
			syntaxParents = refas.getParentSMMSyntaxElement(instElement);
			if (instElement.getTransSupportMetaElement() != null
					&& instElement.getTransSupportMetaElement()
							.getTransInstSemanticElement() != null)
				opersParents = instElement.getTransSupportMetaElement()
						.getTransInstSemanticElement().getParentOpersConcept();
			instElement.createInstAttributes(syntaxParents);

			SyntaxElement metaElement = instElement.getEditableMetaElement();
			if (metaElement != null
					&& metaElement.getInstSemanticElementId() != null) {
				InstElement rr = refas.getOperationalModel().getVertex(
						metaElement.getInstSemanticElementId());
				metaElement.setTransInstSemanticElement(rr);
			}
			IntOpersElement semElement = instElement
					.getEditableSemanticElement();
			if (semElement != null) {
				List<IntMetaExpression> semExp = semElement
						.getSemanticExpressions();
				for (IntMetaExpression exp : semExp) {
					((OpersExpr) exp).loadVolatileElements(refas
							.getVariabilityVertex());
				}
			}
		}

		if (instElement instanceof InstOverTwoRel) {
			InstOverTwoRel instOverTwoRelation = (InstOverTwoRel) instElement;
			InstElement instSupportElement = refas.getSyntaxModel().getVertex(
					instOverTwoRelation.getSupportMetaElementUserIdentifier());
			if (instSupportElement == null) {
				System.err.println("OverTwoRel Null"
						+ instOverTwoRelation
								.getSupportMetaElementUserIdentifier());
				return;
			} else {
				SyntaxOverTwoRel metaOverTwoRelation = (SyntaxOverTwoRel) instSupportElement
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
				ElemAttribute attribute = instOverTwoRelation
						.getAbstractAttribute(ia.getAttributeName(),
								opersParents);
				if (attribute != null) {
					ia.setAttribute(attribute);

					List<IntOpersRelType> semGD = ((SyntaxOverTwoRel) instOverTwoRelation
							.getTransSupportMetaElement())
							.getSemanticRelationTypes();
					ia.setValidationRelationTypes(semGD);
					if (ia.getType().equals("Boolean")
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
							.getAllAttributesNames(syntaxParents).size()) {
				for (String attributeName : instOverTwoRelation
						.getTransSupportMetaElement().getAllAttributesNames(
								syntaxParents)) {
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
						instOverTwoRelation.addInstAttribute(
								attributeName,
								instOverTwoRelation
										.getTransSupportMetaElement()
										.getModelingAttribute(attributeName,
												null), null);
						// System.out.println("create" + attributeName);
						additionAttributes = true;
					}
				}
			}
			editor.refreshElement(instOverTwoRelation);
		} else if (instElement instanceof InstVertex) {
			InstVertex instVertex = (InstVertex) instElement;
			SyntaxVertex metaVertex = null;
			if (refas.getSyntaxModel().getVertex(
					instVertex.getSupportMetaElementIden()) != null)
				metaVertex = (SyntaxVertex) refas.getSyntaxModel()
						.getVertex(instVertex.getSupportMetaElementIden())
						.getEditableMetaElement();
			if (metaVertex == null)
				System.err.println("Concept w "
						+ instVertex.getSupportMetaElementIden());
			else {
				instVertex.setTransSupportMetaElement(metaVertex);
				refas.putVariabilityInstVertex(instVertex);
				Iterator<InstAttribute> ias = instVertex.getInstAttributes()
						.values().iterator();
				while (ias.hasNext()) {
					InstAttribute ia = (InstAttribute) ias.next();
					ElemAttribute attribute = metaVertex
							.getAbstractAttribute(ia.getAttributeName(),
									syntaxParents, opersParents);
					if (attribute != null) {
						ia.setAttribute(attribute);
						if (ia.getType().equals("Boolean")
								&& ia.getValue() instanceof String)
							if (((String) ia.getValue()).equals("0"))
								ia.setValue(false);
							else
								ia.setValue(true);
						if (ia.getIdentifier().equals("ConditionalExpression")) {
							ModelExpr instanceExpression = (ModelExpr) ia
									.getValue();
							if (instanceExpression != null)
								instanceExpression.loadVolatileElements(refas
										.getVariabilityVertex());
						}
						if (ia.getIdentifier().equals("enumerationType")) {
							Object instanceExpression = ia.getValue();
							if (ia.getAttribute().getType().equals("Class")) {

								if (instanceExpression != null) {
									// instVertex = new HashMap<String,
									// InstVertex>();
									List<InstVertex> list = getInstElements(ia
											.getAttribute()
											.getMetaConceptInstanceType(),
											graph);

									for (InstVertex concept : list) {
										// instVertex.put(concept.getIdentifier(),
										// concept);
										String out = concept.getInstAttribute(
												"name").toString();
										// ia.setValueObject(concept);
										if (ia.getValue() != null
												&& out.equals(ia.getValue()))
											ia.setValueObject(concept);
										if (ia.getValue() == null
												&& ia.getAttributeDefaultValue() != null
												&& out.equals(ia
														.getAttributeDefaultValue()))
											ia.setValueObject(concept);
									}
								}
							}
						}
					} else {
						instAttributesToDelete.add(ia.getAttributeName());
						ias.remove();
					}
				}
			}
			int semAtt = 0;
			SyntaxElement supportMetaElement = instVertex
					.getTransSupportMetaElement();
			Set<String> attributeNames = supportMetaElement
					.getAllAttributesNames(syntaxParents);
			if (attributeNames != null)
				semAtt = attributeNames.size();
			if (instVertex.getInstAttributes().size() < semAtt) {
				// TODO modify to support syntax attributes changes
				for (String attributeName : attributeNames) {
					if (instVertex.getInstAttribute(attributeName) == null
							&& supportMetaElement
									.getSemanticAttribute(attributeName) != null) {
						instVertex.addInstAttribute(attributeName,
								supportMetaElement
										.getSemanticAttribute(attributeName),
								null);
						// System.out.println("create" + attributeName);
						additionAttributes = true;
					} else if (instVertex.getInstAttribute(attributeName) == null) {
						instVertex.addInstAttribute(attributeName,
								supportMetaElement.getModelingAttribute(
										attributeName, syntaxParents), null);
						// System.out.println("create" + attributeName);
						additionAttributes = true;
					}
				}
			}
		}
		if (instElement instanceof InstPairwiseRel) {
			try {
				InstPairwiseRel instPairwiseRelation = (InstPairwiseRel) instElement;
				// instPairwiseRelation
				// .createAttributes(new HashMap<String, InstAttribute>());
				// if (source.getSource() == null)
				// source.getSource().toString();
				if (source.getSource() == null) {
					System.out.println(source.getId());
				}
				if (source.getTarget() == null) {
					System.out.println(source.getId());
				}
				InstElement sourceVertex = (InstElement) ((InstCell) source
						.getSource().getValue()).getInstElement();
				InstElement targetVertex = (InstElement) ((InstCell) source
						.getTarget().getValue()).getInstElement();
				if (sourceVertex == null || targetVertex == null) {
					System.out.println("Error load" + source.getId());
					return;
				}
				SyntaxElement metaPairwiseRelation = null;
				try {
					metaPairwiseRelation = refas.getSyntaxModel()
							.getValidMetaPairwiseRelation(
									sourceVertex,
									targetVertex,
									instPairwiseRelation
											.getSupportMetaPairwiseRelIden());
				} catch (Exception e) {
					e.printStackTrace();
					// FIXME
				}

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

							ElemAttribute absAttribute = metaPairwiseRelation
									.getAbstractAttribute(
											instAttribute.getAttributeName(),
											syntaxParents, opersParents);
							if (absAttribute == null)
								absAttribute = instPairwiseRelation
										.getSemanticAttribute();
							if (absAttribute != null) {
								instAttribute.setAttribute(absAttribute);
								// if (absAttribute != null)// TODO find a
								// better
								// fix
								if (instAttribute.getType().equals("Boolean")
										&& instAttribute.getValue() != null
										&& instAttribute.getValue() instanceof String)
									if (((String) instAttribute.getValue())
											.equals("0"))
										instAttribute.setValue(false);
									else
										instAttribute.setValue(true);
								if (instAttribute.getIdentifier().equals(
										"relationType"))
									instAttribute.setValue(instPairwiseRelation
											.getSemanticPairwiseRelType());
								try {
									List<IntOpersRelType> semGD = ((SyntaxPairwiseRel) instPairwiseRelation
											.getMetaPairwiseRelation())
											.getSemanticRelationTypes();
									instAttribute
											.setValidationRelationTypes(semGD);
								} catch (Exception e) {
									System.out
											.println("SharedActions: relation without semantic type "
													+ instPairwiseRelation
															.getIdentifier());
									// e.printStackTrace();
									// FIXME

								}
							} else {
								instAttributesToDelete.add(instAttribute
										.getAttributeName());
								instAttributesIter.remove();
							}

						} catch (Exception e) {
							System.err
									.println("Contained exception PWRel load");
							e.printStackTrace();
						}
					}
					if (instPairwiseRelation.getInstAttributes().size() < instPairwiseRelation
							.getTransSupportMetaElement()
							.getAllAttributesNames(syntaxParents).size()
							+ instPairwiseRelation.getTransSupportMetaElement()
									.getModelingAttributes().size()) {
						for (String attributeName : instPairwiseRelation
								.getTransSupportMetaElement()
								.getAllAttributesNames(syntaxParents)) {
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
														attributeName, null),
										null);
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
				System.err.println("Contained exception GenPW Rel");
				e.printStackTrace();
			}
		}
	}

	public static List<InstVertex> getInstElements(String object, mxGraph graph) {
		List<InstVertex> out = new ArrayList<InstVertex>();
		mxIGraphModel refasGraph = graph.getModel();
		Object o = refasGraph.getRoot(); // Main Root
		mxCell o1 = (mxCell) refasGraph.getChildAt(o, 0); // Null Root
		for (int i = 0; i < o1.getChildCount(); i++) {
			mxCell mv = (mxCell) refasGraph.getChildAt(o1, i);
			for (int j = 0; j < mv.getChildCount(); j++) {
				mxCell concept = (mxCell) refasGraph.getChildAt(mv, j);
				for (int k = 0; k < concept.getChildCount(); k++) {
					mxCell concept2 = (mxCell) refasGraph
							.getChildAt(concept, k);
					InstElement value = ((InstCell) concept2.getValue())
							.getInstElement();
					if (value instanceof InstVertex) {
						InstVertex ic = (InstVertex) value;
						SyntaxElement mc = ic.getTransSupportMetaElement();
						if (mc != null && mc.getAutoIdentifier().equals(object))
							out.add(ic);
					}
				}
				InstElement value = ((InstCell) concept.getValue())
						.getInstElement();
				if (value instanceof InstVertex) {
					InstVertex ic = (InstVertex) value;
					SyntaxElement mc = ic.getTransSupportMetaElement();
					if (mc != null && mc.getAutoIdentifier().equals(object))
						out.add(ic);
				}

			}
		}
		return out;
	}

	public static boolean validateConceptType(InstElement instElement,
			String element) {
		if (instElement == null)// || !(instElement instanceof InstVertex))
			return false;
		SyntaxElement metaElement = ((SyntaxElement) instElement
				.getTransSupportMetaElement());
		if (metaElement == null)
			return false;
		InstElement semElement = metaElement.getTransInstSemanticElement();
		while (semElement != null && semElement.getIdentifier() != null
				&& !semElement.getIdentifier().equals(element)) {
			InstElement sEle = semElement;
			semElement = null;
			for (InstElement ele : sEle.getTargetRelations())
				if (ele instanceof InstPairwiseRel) {
					if (((InstPairwiseRel) ele)
							.getSupportMetaPairwiseRelIden().equals(
									"ExtendsRelation")) {
						semElement = ele.getTargetRelations().get(0);
						break;
					}
				} else if (((InstPairwiseRel) ele)
						.getSupportMetaElementIden().equals("ExtendsRelation")) {
					semElement = ele.getTargetRelations().get(0);
					break;
				}
		}
		if (semElement != null && semElement.getIdentifier() != null
				&& semElement.getIdentifier().equals(element)) {
			return true;
		}
		return false;
	}
}
