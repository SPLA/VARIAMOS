package com.variamos.gui.refas.editor;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.cfm.common.AbstractModel;
import com.cfm.productline.AbstractElement;
import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.constraints.ExcludesConstraint;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.MandatoryConstraint;
import com.cfm.productline.constraints.OptionalConstraint;
import com.cfm.productline.constraints.RequiresConstraint;
import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.canvas.mxGraphicsCanvas2D;
import com.mxgraph.io.mxCodec;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.shape.mxStencil;
import com.mxgraph.shape.mxStencilRegistry;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.pl.editor.logic.ConstraintMode;
import com.variamos.refas.core.refas.Refas;
import com.variamos.refas.core.types.PerspectiveType;
import com.variamos.syntaxsupport.metamodel.EditableElement;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstEnumeration;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstView;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaPairwiseRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaView;

public class RefasGraph extends AbstractGraph {

	protected ConstraintMode constraintAddingMode = ConstraintMode.None;

	public static final String PL_EVT_NODE_CHANGE = "plEvtNodeChange";
	private Refas refas = null;
	private int modelViewIndex = 0;
	private int modelViewSubIndex = -1;
	private SemanticPlusSyntax semanticPlusSyntax;
	private boolean validation = true;
	private int perspective=2;

	public boolean isValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public int getModelViewIndex() {
		return modelViewIndex;
	}

	public void setModelViewIndex(int modelView) {
		this.modelViewIndex = modelView;
	}

	public int getModelViewSubIndex() {
		return modelViewSubIndex;
	}

	public void setModelViewSubIndex(int modelSubView) {
		this.modelViewSubIndex = modelSubView;
	}

	public RefasGraph(SemanticPlusSyntax semanticPlusSyntax, int perspective) {
		init();
		this.semanticPlusSyntax = semanticPlusSyntax;
		this.perspective = perspective;
	}

	public void defineInitialGraph() {
		mxCell root = new mxCell();
		root.insert(new mxCell());
		getModel().setRoot(root);
		Collection views;
		if (refas.getSyntaxRefas() == null)
			views = semanticPlusSyntax.getMetaViews();
		else {
			views = refas.getSyntaxRefas().getInstViews();
			int pos = 0;
			if (views.size() == 0) {
				for (InstVertex instVertex : refas.getVertices()) {
					mxCell child = new mxCell(instVertex.getIdentifier());
					addCell(child);
					String id = instVertex.getIdentifier();
					child.setValue(instVertex);
					child.setVisible(true);
					child.setStyle(instVertex.getTransSupportMetaElement()
							.getStyle());
					child.setGeometry(new mxGeometry(50 + pos * 3,
							50 + pos * 3, 120, 100));
					child.setVertex(true);
					mxGraphModel model = (mxGraphModel) getModel();
					model.getCells().remove(child.getId());
					model.getCells().put(id, child);
					child.setId(id);
					pos++;

				}
				for (InstView instView : refas.getInstViews()) {
					if (instView.getChildViews().size() == 0) {
						mxCell child = new mxCell(instView.getIdentifier());
						addCell(child);
						String id = instView.getIdentifier();
						child.setValue(instView);
						child.setVisible(true);
						child.setStyle(instView.getTransSupportMetaElement()
								.getStyle());
						child.setGeometry(new mxGeometry(50 + pos * 3,
								50 + pos * 3, 120, 40));
						child.setVertex(true);
						mxGraphModel model = (mxGraphModel) getModel();
						model.getCells().remove(child.getId());
						model.getCells().put(id, child);
						child.setId(id);
						pos++;
					}
					for (InstView instChildView : instView.getChildViews()) {
						mxCell child2 = new mxCell(
								instChildView.getIdentifier());
						addCell(child2);
						String id2 = instChildView.getIdentifier();
						child2.setValue(instChildView);
						child2.setVisible(true);
						child2.setStyle(instChildView
								.getTransSupportMetaElement().getStyle());
						child2.setGeometry(new mxGeometry(50 + pos * 3,
								50 + pos * 3, 120, 40));
						child2.setVertex(true);
						mxGraphModel model2 = (mxGraphModel) getModel();
						model2.getCells().remove(child2.getId());
						model2.getCells().put(id2, child2);
						child2.setId(id2);
						pos++;

					}
				}

				for (InstPairwiseRelation instEdge : refas
						.getConstraintInstEdgesCollection()) {
					if (instEdge.getSourceRelations().size() != 0
							&& instEdge.getIdentifier() != null
							&& !instEdge.getIdentifier().equals("")) {
						mxCell child = new mxCell(instEdge.getIdentifier());
						addCell(child);
						String i = instEdge.getSourceRelations().get(0)
								.getIdentifier();
						mxCell source = this.getCellById(instEdge
								.getSourceRelations().get(0).getIdentifier());
						mxCell target = this.getCellById(instEdge
								.getTargetRelations().get(0).getIdentifier());
						child.setStyle("");
						if (instEdge.getTransSupportMetaElement() != null) {
							MetaElement e = instEdge
									.getTransSupportMetaElement();
							child.setStyle(instEdge
									.getTransSupportMetaElement().getStyle());
						}

						child.setSource(source);
						child.setTarget(target);
						child.setValue(instEdge);
						mxGeometry geo = new mxGeometry();
						String id = instEdge.getIdentifier();
						source.insertEdge(child, true);
						target.insertEdge(child, false);
						child.setGeometry(geo);
						child.setVisible(true);
						child.setVertex(false);
						child.setEdge(true);
						mxGraphModel model = (mxGraphModel) getModel();
						model.getCells().remove(child.getId());
						model.getCells().put(id, child);
						child.setId(id);
					}
				}
			}
		}
		int i = 0;
		for (Object view : views) {
			mxCell parent = new mxCell("mv" + i);
			addCell(parent);
			if (refas.getSyntaxRefas() == null) {
				MetaView metaView = (MetaView) view;
				if (metaView.getChildViews().size() > 0) {
					addCell(new mxCell("mv" + i), parent); // Add the parent as
															// first child
					for (int j = 0; j < metaView.getChildViews().size(); j++) {
						addCell(new mxCell("mv" + i + "-" + j), parent);
					}
				}
			} else {
				InstView instView = (InstView) view;
				if (instView.getChildViews().size() > 0) {
					mxCell childParent = new mxCell("mv" + i);
					addCell(childParent, parent); // Add the parent as
													// first child
					for (int j = 0; j < instView.getChildViews().size(); j++) {
						/*
						 * InstView instchildView = instView.getChildViews()
						 * .get(j); for (InstVertex instVertex : instchildView
						 * .getInstVertices()) { mxCell child = new mxCell(
						 * instVertex.getIdentifier());
						 * child.setValue(instVertex); child.setVisible(true);
						 * addCell(child, childParent); }
						 */
						addCell(new mxCell("mv" + i + "-" + j), parent);
					}
				}
			}
			i++;
		}

	}

	public List<String> getValidElements(int modelView, int modelSubView) {
		if (refas.getSyntaxRefas() == null)
			return semanticPlusSyntax.modelElements(modelView, modelSubView);
		else
			return refas.getSyntaxRefas()
					.modelElements(modelView, modelSubView);
	}

	String getResource(String rsc) {
		StringBuilder result = new StringBuilder("");

		// Get file from resources folder
		InputStream in = getClass().getResourceAsStream(rsc);
		BufferedReader input = new BufferedReader(new InputStreamReader(in));

		Scanner scanner = new Scanner(input);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			result.append(line).append("\n");
		}

		scanner.close();

		return result.toString();

	}

	protected void init() {
		super.init();
		// Loads the defalt stylesheet from an external file
		mxCodec codec = new mxCodec();
		Document doc = mxUtils.loadDocument(RefasGraph.class.getResource(
				"/com/variamos/gui/pl/editor/style/styles.xml").toString());
		codec.decode(doc.getDocumentElement(), stylesheet);
		loadStencil();
	}

	public void loadStencil() {
		// try {
		String filename = RefasGraph.class.getResource(
				"/com/variamos/gui/refas/editor/style/shapes.xml").getPath();
		Document doc;
		// System.out.println(filename);

		// String s =
		// getResource("com/variamos/gui/refas/editor/style/shapes.xml");
		doc = mxXmlUtils
				.parseXml(getResource("/com/variamos/gui/refas/editor/style/shapes.xml"));

		// doc = mxXmlUtils.parseXml(mxUtils.readFile(filename));

		Element shapes = (Element) doc.getDocumentElement();
		NodeList list = shapes.getElementsByTagName("shape");

		for (int i = 0; i < list.getLength(); i++) {
			Element shape = (Element) list.item(i);
			mxStencilRegistry.addStencil(shape.getAttribute("name"),
					new mxStencil(shape) {
						protected mxGraphicsCanvas2D createCanvas(
								final mxGraphics2DCanvas gc) {
							// Redirects image loading to graphics canvas
							return new mxGraphicsCanvas2D(gc.getGraphics()) {
								protected Image loadImage(String src) {
									// Adds image base path to relative
									// image URLs
									if (!src.startsWith("/")
											&& !src.startsWith("http://")
											&& !src.startsWith("https://")
											&& !src.startsWith("file:")) {
										src = gc.getImageBasePath() + src;
									}

									// Call is cached
									return gc.loadImage(src);
								}
							};
						}
					});
		}
		/*
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	@Override
	public boolean isValidConnection(Object source, Object target) {
		if (perspective == 4)
			return false;
		if (validation) {
			if (!(source instanceof mxCell) || !(target instanceof mxCell)) {
				return super.isValidConnection(source, target);
			}

			mxCell s = (mxCell) source;
			mxCell t = (mxCell) target;

			if (s.isEdge() || t.isEdge())
				return false;
			InstElement instSource = (InstElement) s.getValue();
			InstElement instTarget = (InstElement) t.getValue();

			HashMap<String, InstAttribute> map = new HashMap<String, InstAttribute>();
			InstPairwiseRelation directRelation = new InstPairwiseRelation(map,
					"test");
			Refas refas = getRefas();
			refas.updateValidationLists(directRelation, instSource, instTarget);
			InstAttribute ia = directRelation.getInstAttribute("MetaPairwise");
			List<MetaPairwiseRelation> pwrList = ia.getValidationMEList();
			if (pwrList.size() == 0) {
				directRelation.clearMetaPairwiseRelation();
				return false;
			}

			return super.isValidConnection(source, target);
		}
		return true;
	}

	protected boolean addingEdge(mxCell cell, mxCell parent, int index) {
		InstElement source = (InstElement) cell.getSource().getValue();
		InstElement target = (InstElement) cell.getTarget().getValue();
		String id = null;
		HashMap<String, InstAttribute> map = new HashMap<String, InstAttribute>();
		// TODO fill the map with allowed relation types
		if (cell.getValue() instanceof InstPairwiseRelation) {
			InstPairwiseRelation element = (InstPairwiseRelation) cell
					.getValue();

			String elementIdentifier = element.getIdentifier();
			if (elementIdentifier != null && !"".equals(elementIdentifier))
				return true;
		}
		InstPairwiseRelation directRelation = new InstPairwiseRelation(map,
				"test");
		Refas refas = getRefas();

		id = refas.addNewConstraintInstEdge(directRelation);
		cell.setValue(directRelation);
		source.addTargetRelation(directRelation, true);
		target.addSourceRelation(directRelation, true);
		refas.updateValidationLists(directRelation, source, target);
		InstAttribute ia = directRelation.getInstAttribute("MetaPairwise");
		List<MetaPairwiseRelation> pwrList = ia.getValidationMEList();
		mxGraphModel refasGraph = (mxGraphModel) getModel();
		refasGraph.getCells().remove(cell.getId());
		if (pwrList.size() == 0) {
			directRelation.clearRelations();
			directRelation.clearMetaPairwiseRelation();
			// cell.setVisible(false); // TODO workaround to hide non allowed
			// relations - fix delete
			return false;
		}
		if (modelViewSubIndex != -1) {
			refasGraph.getCells().put(
					modelViewIndex + "-" + modelViewSubIndex + id, cell);
			cell.setId(modelViewIndex + "-" + modelViewSubIndex + id);
		} else {
			refasGraph.getCells().put(modelViewIndex + id, cell);
			cell.setId(modelViewIndex + id);
		}

		return true;
	}

	// TODO review from here for requirements

	protected boolean addingVertex(mxCell cell, mxCell parent, int index) {

		if (cell.getValue() instanceof AbstractElement
				|| cell.getValue() instanceof Constraint
				|| cell.getValue() instanceof InstVertex) {
			String id = null;
			String elementIdentifier = null;
			Refas pl = getRefas();
			Object cellValue = cell.getValue();
			if (cell.getGeometry() != null) {
				if (cellValue instanceof InstVertex) {
					InstVertex element = (InstVertex) cellValue;
					elementIdentifier = element.getIdentifier();
					if (elementIdentifier != null
							&& !"".equals(elementIdentifier))
						return false;
					if (cellValue instanceof InstOverTwoRelation)
						id = pl.addNewInstGroupDependency((InstOverTwoRelation) element);
					else if (cellValue instanceof InstEnumeration)
						id = pl.addNewOtherInstElement(element);
					else
						id = pl.addNewVariabilityInstElement(element);
				}

				else {
					elementIdentifier = ((Constraint) cellValue)
							.getIdentifier();
					if (elementIdentifier != null
							&& !"".equals(elementIdentifier))
						return false;
					// id = pl.addConstraint(constraint);
				}

				if (id != null) {

					// Move the element to the appropiate View - clone if
					// necessary
					// in multiple views
					mxGraphModel refasGraph = (mxGraphModel) getModel();
					Object o = refasGraph.getRoot(); // Main Root
					Object o1 = refasGraph.getChildAt(o, 0); // Null Root
					Object mv0 = null;
					mxGraphModel model = refasGraph;
					model.getCells().remove(cell.getId());
					if (modelViewSubIndex != -1) {
						o1 = refasGraph.getChildAt(o1, modelViewIndex); // Parent
																		// View
						mv0 = refasGraph.getChildAt(o1, modelViewSubIndex);

						model.getCells().put(
								modelViewIndex + id + "-" + modelViewSubIndex,
								cell);
						cell.setId(modelViewIndex + id + "-"
								+ modelViewSubIndex);
					} else if (modelViewIndex != -1) {
						mv0 = refasGraph.getChildAt(o1, modelViewIndex);
						model.getCells().put(modelViewIndex + id, cell);
						cell.setId(modelViewIndex + id);
					} else {
						mv0 = o1;
						model.getCells().put(id, cell);
						cell.setId(id);
					}

					parent.remove(index); // Remove from original position
					model.add(mv0, cell, 0); // Add to the parent according to
												// the
												// model
					if (cellValue instanceof AbstractElement
							|| cellValue instanceof InstVertex) {
						String name = null;
						if (cellValue instanceof AbstractElement)
							name = cellValue.getClass().getSimpleName();
						else {
							if (cellValue instanceof InstConcept) {
								InstConcept c = (InstConcept) cellValue;
								name = c.getTransSupportMetaElement()
										.getIdentifier();
							}
							if (cellValue instanceof InstOverTwoRelation) {
								InstOverTwoRelation c = (InstOverTwoRelation) cellValue;
								name = c.getSupportMetaOverTwoRelation()
										.getIdentifier();
							}
						}
						o1 = refasGraph.getChildAt(o, 0); // Null Root
						for (int i = 0; i < refasGraph.getChildCount(o1); i++) {
							mxCell mv1 = (mxCell) refasGraph.getChildAt(o1, i);
							if (refasGraph.getChildCount(mv1) > 0
									&& mv1.getValue().equals(
											mv1.getChildAt(0).getValue())) {
								for (int j = 0; j < refasGraph
										.getChildCount(mv1); j++) {
									mxCell mv2 = (mxCell) refasGraph
											.getChildAt(mv1, j);
									if (semanticPlusSyntax.elementsValidation(
											name, i, j)
											&& (i != modelViewIndex || j != modelViewSubIndex)) {

										mxCell c2 = null;
										try {
											c2 = (mxCell) cell.clone();
										} catch (CloneNotSupportedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										c2.setId(i + id + "-" + j);
										c2.setValue(cell.getValue());

										getModel().setVisible(c2, false);
										model.add(mv2, c2, mv2.getChildCount()); // Add
																					// a
																					// clone
																					// to
										// other
										// models
									}

								}

							} else {
								// if (valid[i] && i != modelViewIndex) {
								if (semanticPlusSyntax.elementsValidation(name,
										i, -1) && i != modelViewIndex) {

									mxCell c2 = null;
									try {
										c2 = (mxCell) cell.clone();
									} catch (CloneNotSupportedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									c2.setId(i + id);
									c2.setValue(cell.getValue());

									getModel().setVisible(c2, false);
									model.add(mv1, c2, mv1.getChildCount()); // Add
																				// a
																				// clone
																				// to
																				// other
								}

							}
						}
					}
					this.refresh();
					return true;
				} else {
					// If the element is not allowed in the View, is removed
					mxGraphModel model = (mxGraphModel) getModel();
					model.getCells().remove(cell.getId());
					if (parent.getIndex(cell) == index)
						parent.remove(index);
					return false;
				}
			} else
				cell.setGeometry(new mxGeometry(50, 50, 100, 40));
		}
		return true;
	}

	protected void removingRefaElements(mxCell cell) {
		Object obj = cell.getValue();
		if (obj instanceof InstElement)
			refas.removeElement((InstElement) obj);
	}

	protected void removingClones(mxCell cell) {
		mxIGraphModel refasGraph = getModel();

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
						if (cell.getValue().equals(mv2.getValue()))
							mv1.remove(j);
					}
				}
			} else
				for (int i = 0; i < refasGraph.getChildCount(mv0); i++) {
					mxCell mv1 = (mxCell) refasGraph.getChildAt(mv0, i);
					if (cell.getValue().equals(mv1.getValue()))
						mv0.remove(i);
				}
		}
	}

	public void setModel(AbstractModel pl) {
		refas = (Refas) pl;
		defineInitialGraph();
		try {
			mxGraphLayout layout = new mxOrganicLayout(this);
			layout.execute(getDefaultParent()); // todo change root?
		} catch (Exception e) {
		}

	}

	public void showElements() {
		mxIGraphModel refasGraph = getModel();

		Object o = refasGraph.getRoot(); // Main Root
		Object o1 = refasGraph.getChildAt(o, 0); // Null Root
		for (int mvInd = 0; mvInd < refasGraph.getChildCount(o1); mvInd++) {
			mxCell mv0 = (mxCell) refasGraph.getChildAt(o1, mvInd); // View root
			if (refasGraph.getChildCount(mv0) > 0) {
				mxCell child = (mxCell) refasGraph.getChildAt(mv0, 0);
				Object[] vertices = null;
				if (child.getValue().equals(mv0.getValue())) {
					for (int mvSubInd = 0; mvSubInd < refasGraph
							.getChildCount(mv0); mvSubInd++) {
						mxCell mv00 = (mxCell) refasGraph.getChildAt(mv0,
								mvSubInd);
						if (refasGraph.getChildCount(mv00) > 0) {
							vertices = mxGraphModel.getChildCells(getModel(),
									mv00, true, false);

							for (int i = 0; i < vertices.length; i++) {
								mxCell cell = ((mxCell) vertices[i]);
								if (modelViewIndex != mvInd
										|| (modelViewSubIndex != -1 && modelViewSubIndex != mvSubInd)) {

									getModel().setVisible(cell, false);
									Object[] edges1 = getEdges(cell);
									for (Object oo : edges1)
										getModel().setVisible(oo, false);
									this.fireEvent(new mxEventObject(
											PL_EVT_NODE_CHANGE, "cell", cell));
								}
							}

							for (int i = 0; i < vertices.length; i++) {
								mxCell cell = ((mxCell) vertices[i]);
								if (modelViewIndex == mvInd
										&& (modelViewSubIndex == -1 || modelViewSubIndex == mvSubInd)) {
									getModel().setVisible(cell, true);
									Object[] edges2 = getEdges(cell);
									for (Object oo : edges2)
										getModel().setVisible(oo, true);
									this.fireEvent(new mxEventObject(
											PL_EVT_NODE_CHANGE, "cell", cell));
								}
							}
						}

					}

				} else {

					vertices = mxGraphModel.getChildCells(getModel(), mv0,
							true, false);

					for (int i = 0; i < vertices.length; i++) {
						mxCell cell = ((mxCell) vertices[i]);
						if (modelViewIndex != mvInd) {

							getModel().setVisible(cell, false);
							Object[] edges1 = getEdges(cell);
							for (Object oo : edges1)
								getModel().setVisible(oo, false);
							this.fireEvent(new mxEventObject(
									PL_EVT_NODE_CHANGE, "cell", cell));
						}
					}

					for (int i = 0; i < vertices.length; i++) {
						mxCell cell = ((mxCell) vertices[i]);
						if (modelViewIndex == mvInd) {
							getModel().setVisible(cell, true);
							Object[] edges2 = getEdges(cell);
							for (Object oo : edges2)
								getModel().setVisible(oo, true);
							this.fireEvent(new mxEventObject(
									PL_EVT_NODE_CHANGE, "cell", cell));
						}
					}
				}
			}

		}
		this.refresh();

	}

	public Refas getRefas() {
		if (refas == null) {
			refas = new Refas(PerspectiveType.modeling);
			return refas;
		}
		return refas;
	}

	public mxCell getCellById(String id) {
		return (mxCell) ((mxGraphModel) getModel()).getCell(id);
	}

	public ConstraintMode getConsMode() {
		return constraintAddingMode;
	}

	public void setConsMode(ConstraintMode consMode) {
		this.constraintAddingMode = consMode;
	}

	public void connectDefaultConstraint(mxCell source, mxCell target) {
		Constraint c = newConstraint(source.getId(), target.getId());

		double cX = (target.getGeometry().getCenterX() + source.getGeometry()
				.getCenterX()) / 2;
		double cY = (target.getGeometry().getCenterY() + source.getGeometry()
				.getCenterY()) / 2;

		mxCell constraintCell = (mxCell) insertVertex(null, c.getIdentifier(),
				c, cX, cY, 40, 20, "plcons");
		c.setIdentifier(constraintCell.getId());
		constraintCell.setConnectable(false);

		// Create edges.
		insertEdge(null, null, null, source, constraintCell);
		insertEdge(null, "", "", constraintCell, target);
	}

	protected Constraint newConstraint(String idSource, String idTarget) {
		Constraint c = new GenericConstraint();

		// switch(constraintAddingMode){
		// case Default:
		// c.setText("General Constraint");
		// break;
		//
		// case Excludes:
		// c.setText("Exclude(:" + idSource + ", :" + idTarget+ ")" );
		// break;
		//
		// case Mandatory:
		// c.setText("Mandatory(:" + idSource + ", :" + idTarget+ ")" );
		// break;
		// case Optional:
		// c.setText("Optional(:" + idSource + ", :" + idTarget+ ")" );
		// break;
		// case Requires:
		// c.setText("Requires(:" + idSource + ", :" + idTarget+ ")" );
		// break;
		// default:
		// break;
		// }

		return c;
	}

	// @Override
	// public boolean isValidSource(Object cell) {
	// boolean isValid = consMode != ConstraintMode.None;
	// if( cell instanceof mxCell ){
	// mxCell s = (mxCell) cell;
	// if( !s.isEdge() && s.getValue() instanceof Constraint ){
	// isValid = true;
	// System.out.println("This is a constraint !");
	// }
	// }
	//
	// boolean all = super.isValidSource(cell) && isValid;
	// System.out.println("Is SUper valid Source: " +
	// super.isValidSource(cell));
	// return all;
	// }
	//
	// @Override
	// public boolean isValidTarget(Object cell) {
	// if( cell instanceof mxCell ){
	// mxCell c = (mxCell)cell;
	// if( c.isEdge() )
	// return false;
	//
	// if( c.getValue() instanceof VariabilityPoint )
	// return true;
	// }
	// return super.isValidTarget(cell);
	// }

	@Override
	public String validateEdge(Object edge, Object source, Object target) {
		return super.validateEdge(edge, source, target);
	}

	@Override
	public String validateCell(Object objCell, Hashtable<Object, Object> context) {
		if (objCell instanceof mxCell) {
			mxCell cell = (mxCell) objCell;

			/*
			 * if (cell.getValue() instanceof GroupGConstraint) {
			 * GroupGConstraint gc = (GroupGConstraint) cell.getValue(); if
			 * (gc.getParent() == null) return "Needs Parent"; }
			 */

			/*
			 * if ( // TODO evaluate new constraints cell.getValue() instanceof
			 * GroupGConstraint || cell.getValue() instanceof
			 * MandatoryConstraint || cell.getValue() instanceof
			 * OptionalConstraint || cell.getValue() instanceof
			 * RequiresConstraint || cell.getValue() instanceof
			 * ExcludesConstraint || cell.getValue() instanceof
			 * GenericConstraint) {
			 * 
			 * } else {
			 * 
			 * if (cell.getValue() instanceof Constraint) return
			 * "Not Implemented Yet"; }
			 */
		}
		return super.validateCell(objCell, context);
	}

	@Override
	public String convertValueToString(Object obj) {
		mxCell cell = (mxCell) obj;
		// VariabilityPoint
		if (cell.getValue() instanceof VariabilityElement) {
			VariabilityElement vp = (VariabilityElement) cell.getValue();
			String name = vp.getName();
			if (name == null)
				name = "";

			return name;
		}

		if (cell.getValue() instanceof Asset) {
			Asset a = (Asset) cell.getValue();
			String name = a.getName();
			if (name == null)
				name = "";

			return name;
		}

		// GroupConstraint
		// TODO evaluate new group constraints
		/*
		 * if (cell.getValue() instanceof GroupGConstraint) { GroupGConstraint
		 * gc = (GroupGConstraint) cell.getValue(); return
		 * gc.getCardinalityString(); }
		 */
		// Optional and mandatory
		// TODO evaluate new constraints
		if (cell.getValue() instanceof OptionalConstraint
				|| cell.getValue() instanceof MandatoryConstraint)
			return "";

		if (cell.getValue() instanceof ExcludesConstraint)
			return "excludes";

		if (cell.getValue() instanceof RequiresConstraint)
			return "requires";

		if (cell.getValue() instanceof GenericConstraint)
			return ((GenericConstraint) cell.getValue()).getText();

		return super.convertValueToString(obj);
	}

	@Override
	public void cellLabelChanged(Object cell, Object value, boolean autoSize) {
		super.cellLabelChanged(cell, value, autoSize);
	}

	public void refreshVariable(EditableElement e) {

		mxCell cell = null;
		if (modelViewIndex == -1)
			cell = getCellById(e.getIdentifier());
		else if (modelViewSubIndex == -1)
			cell = getCellById(modelViewIndex + e.getIdentifier());
		else
			cell = getCellById(modelViewIndex + "-" + modelViewSubIndex
					+ e.getIdentifier());
		// Update visibility
		if (e instanceof VariabilityElement) {
			VariabilityElement v = (VariabilityElement) e;
			// v.printDebug(System.out);
			// getModel().setVisible(cell, v.isVisible());
			// v.getName();
			Object[] edges = getEdges(cell);
			for (Object o : edges)
				getModel().setVisible(o, v.isVisible());
		}
		getModel().setValue(cell, e);
		this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE, "cell", cell,
				"element", e));
	}
}
