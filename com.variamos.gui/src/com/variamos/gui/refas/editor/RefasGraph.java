package com.variamos.gui.refas.editor;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import javax.swing.tree.DefaultMutableTreeNode;

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
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.shape.mxStencil;
import com.mxgraph.shape.mxStencilRegistry;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.pl.editor.logic.ConstraintMode;
import com.variamos.refas.core.staticconcepts.GroupGConstraint;
import com.variamos.refas.core.staticconcepts.Refas;
import com.variamos.refas.core.staticconcepts.SemanticPlusSyntax;
import com.variamos.syntaxsupport.metametamodel.MetaView;
import com.variamos.syntaxsupport.metamodel.EditableElement;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;

public class RefasGraph extends AbstractGraph {

	protected ConstraintMode constraintAddingMode = ConstraintMode.None;

	public static final String PL_EVT_NODE_CHANGE = "plEvtNodeChange";
	private Refas refas = null;
	private int modelViewIndex = 0;
	private int modelViewSubIndex = -1;
	private SemanticPlusSyntax semanticPlusSyntax;

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

	public RefasGraph(SemanticPlusSyntax semanticPlusSyntax) {
		init();
		this.semanticPlusSyntax = semanticPlusSyntax;
	}

	public void defineInitialGraph() {
		List<MetaView> metaViews = semanticPlusSyntax.getMetaViews();

		mxCell root = new mxCell();
		root.insert(new mxCell());
		getModel().setRoot(root);
		for (int i = 0; i < metaViews.size(); i++) {
			mxCell parent = new mxCell("mv" + i);
			addCell(parent);
			MetaView metaView = metaViews.get(i);
			if (metaView.getChildViews().size() > 0) {
				addCell(new mxCell("mv" + i), parent); // Add the parent as
														// first child
				for (int j = 0; j < metaView.getChildViews().size(); j++) {
					addCell(new mxCell("mv" + i + "-" + j), parent);
					MetaView metaChildView = metaView.getChildViews().get(j);
				}
			}
		}
	}

	public List<String> getValidElements(int modelView, int modelSubView) {
		if (refas == null)
			refas = getRefas();
		// return refas.getValidElements(modelView);
		return semanticPlusSyntax.modelElements(modelView, modelSubView);
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
		System.out.println(filename);

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

	protected boolean addingEdge(mxCell cell, mxCell parent, int index) {

		Object cellValue = cell.getValue();
		String id = null;
		String elementIdentifier = null;
		InstEdge directRelation = new InstEdge();
		Refas refas = getRefas();

		id = refas.addDirectRelation(directRelation);
		cell.setValue(directRelation);

		mxGraphModel refasGraph = (mxGraphModel) getModel();
		/*
		 * Object o = refasGraph.getRoot(); // Main Root Object o1 =
		 * refasGraph.getChildAt(o, 0); // Null Root Object mv0 =
		 * refasGraph.getChildAt(o1, modelViewIndex);
		 */
		mxGraphModel model = refasGraph;
		model.getCells().remove(cell.getId());
		if (modelViewSubIndex != -1) {
			model.getCells().put(modelViewIndex + "-" + modelViewSubIndex + id,
					cell);
			cell.setId(modelViewIndex + "-" + modelViewSubIndex + id);
		} else {
			model.getCells().put(modelViewIndex + id, cell);
			cell.setId(modelViewIndex + id);
		}

		return true;
	}

	// TODO review from here for requirements

	protected boolean addingVertex(mxCell cell, mxCell parent, int index) {

		if (cell.getValue() instanceof AbstractElement
				|| cell.getValue() instanceof Constraint
				|| cell.getValue() instanceof InstElement) {
			String id = null;
			String elementIdentifier = null;
			Refas pl = getRefas();
			Object cellValue = cell.getValue();

			if (cellValue instanceof InstElement) {
				InstElement element = (InstElement) cellValue;
				elementIdentifier = element.getIdentifier();
				if (elementIdentifier != null && !"".equals(elementIdentifier))
					return false;
				id = pl.addElement(element);
			}

			else {
				Constraint constraint = (Constraint) cellValue;
				elementIdentifier = ((Constraint) cellValue).getIdentifier();
				if (elementIdentifier != null && !"".equals(elementIdentifier))
					return false;
			//	id = pl.addConstraint(constraint);
			}

			if (id != null) {

				// Move the element to the appropiate View - clone if necessary
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

					model.getCells()
							.put(modelViewIndex + id + "-" + modelViewSubIndex,
									cell);
					cell.setId(modelViewIndex + id + "-" + modelViewSubIndex);
				} else {
					mv0 = refasGraph.getChildAt(o1, modelViewIndex);
					model.getCells().put(modelViewIndex + id, cell);
					cell.setId(modelViewIndex + id);
				}

				parent.remove(index); // Remove from original position
				model.add(mv0, cell, 0); // Add to the parent according to the
											// model
				if (cellValue instanceof AbstractElement
						|| cellValue instanceof InstElement) {
					String name = null;
					if (cellValue instanceof AbstractElement)
						name = cellValue.getClass().getSimpleName();
					else {
						if (cellValue instanceof InstConcept) {
							InstConcept c = (InstConcept) cellValue;
							name = c.getMetaConcept().getIdentifier();
						}
						if (cellValue instanceof InstGroupDependency) {
							InstGroupDependency c = (InstGroupDependency) cellValue;
							name = c.getMetaGroupDependency().getIdentifier();
						}
					}
					o1 = refasGraph.getChildAt(o, 0); // Null Root
					for (int i = 0; i < refasGraph.getChildCount(o1); i++) {
						mxCell mv1 = (mxCell) refasGraph.getChildAt(o1, i);
						if (refasGraph.getChildCount(mv1) > 0
								&& mv1.getValue().equals(
										mv1.getChildAt(0).getValue())) {
							for (int j = 0; j < refasGraph.getChildCount(mv1); j++) {
								mxCell mv2 = (mxCell) refasGraph.getChildAt(
										mv1, j);
								if (semanticPlusSyntax.elementsValidation(name,
										i, j)
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
							if (semanticPlusSyntax.elementsValidation(name, i,
									-1) && i != modelViewIndex) {

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
								// models
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
		}

		/*
		 * if (cell.getValue() instanceof GroupConstraint) { GroupConstraint gc
		 * = (GroupConstraint) cell.getValue();
		 * 
		 * if (gc.getIdentifier() != null && !"".equals(gc.getIdentifier()))
		 * return false;
		 * 
		 * Refas pl = getRefas(); String id = pl.getNextConstraintId();
		 * 
		 * gc.setIdentifier(id);
		 * 
		 * // Change the cell id in the model mxGraphModel model =
		 * (mxGraphModel) getModel(); model.getCells().remove(cell.getId());
		 * model.getCells().put(id, cell); cell.setId(id); } if (cell.getValue()
		 * instanceof GenericConstraint) { GenericConstraint gc =
		 * (GenericConstraint) cell.getValue();
		 * 
		 * if (gc.getIdentifier() != null && !"".equals(gc.getIdentifier()))
		 * return false;
		 * 
		 * Refas pl = getRefas(); String id = pl.getNextConstraintId();
		 * 
		 * gc.setIdentifier(id);
		 * 
		 * // Change the cell id in the model mxGraphModel model =
		 * (mxGraphModel) getModel(); model.getCells().remove(cell.getId());
		 * model.getCells().put(id, cell); cell.setId(id); }
		 * 
		 * if (cell.getValue() instanceof Asset) { Asset a = (Asset)
		 * cell.getValue();
		 * 
		 * if (a.getIdentifier() != null && !"".equals(a.getIdentifier()))
		 * return false;
		 * 
		 * Refas pl = getRefas(); pl.addAsset(a);
		 * 
		 * mxGraphModel model = (mxGraphModel) getModel();
		 * model.getCells().remove(cell.getId());
		 * model.getCells().put(a.getIdentifier(), cell);
		 * cell.setId(a.getIdentifier()); }
		 */
		return true;
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
		/*
		 * Object[] vertices = mxGraphModel.getChildCells(getModel(), mv0, true,
		 * false);
		 * 
		 * for (int i = 0; i < vertices.length; i++) { mxCell cell2 = ((mxCell)
		 * vertices[i]); if (cell.getValue().equals(cell2.getValue()))
		 * mv0.remove(i); }
		 */

	}

	public void setModel(AbstractModel pl) {
		refas = (Refas) pl;
		buildFromProductLine(pl);
		mxGraphLayout layout = new mxFastOrganicLayout(this);
		layout.execute(getDefaultParent()); // todo change root?

		defineInitialGraph();
		/*
		 * mxCell root = new mxCell(); root.insert(new mxCell());
		 * getModel().setRoot(root); addCell(new mxCell("mv0")); addCell(new
		 * mxCell("mv1")); addCell(new mxCell("mv2")); addCell(new
		 * mxCell("mv3")); addCell(new mxCell("mv4"));
		 */
	}

	public void setPLElementsVisibility(boolean visibility) {
		Object[] vertices = mxGraphModel.getChildCells(getModel(),
				getDefaultParent(), true, false);
		System.out.println("Setting PL Visibility: " + visibility);
		for (Object obj : vertices) {
			mxCell cell = (mxCell) obj;
			Object value = cell.getValue();

			if (value instanceof VariabilityElement) {
				VariabilityElement vp = (VariabilityElement) value;
				// cell.setVisible(visibility && vp.isVisible());
				boolean vis = visibility && vp.isVisible();
				getModel().setVisible(cell, vis);

				Object[] edges = getEdges(cell);
				for (Object o : edges)
					getModel().setVisible(o, vis);

				this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE, "cell",
						cell));

			}

			if (value instanceof Constraint) {
				// cell.setVisible(visibility);
				getModel().setVisible(cell, visibility);
				this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE, "cell",
						cell));
			}
		}
	}

	public void setAssetsVisibility(boolean visibility) {
		Object[] vertices = mxGraphModel.getChildCells(getModel(),
				getDefaultParent(), true, false);

		for (Object obj : vertices) {
			mxCell cell = (mxCell) obj;
			Object value = cell.getValue();
			if (value instanceof Asset) {
				getModel().setVisible(cell, visibility);

				Object[] edges = getEdges(cell);
				for (Object o : edges)
					getModel().setVisible(o, visibility);

				this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE, "cell",
						cell));
			}
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
		Refas pl = null;
		if (refas == null) {
			pl = new Refas();
			refas = pl;
		}

		else
			pl = refas;
		// Object[] vertices = getChildVertices(getDefaultParent());
		Object[] vertices = mxGraphModel.getChildCells(getModel(),
				getDefaultParent(), true, false);

		for (Object obj : vertices) {
			mxCell cell = (mxCell) obj;
			Object value = cell.getValue();

			if (value instanceof VariabilityElement) {
				VariabilityElement vp = (VariabilityElement) value;
				// pl.addVariabilityPoint(vp);

				for (Object edgObj : getEdges(cell, null, false, true, true)) {
					mxCell edge = (mxCell) edgObj;
					if (edge.getValue() instanceof Constraint) {
						// pl.addConstraint((Constraint) edge.getValue());
					}
				}

			}
			/*
			 * if (value instanceof Constraint) { Constraint c = (Constraint)
			 * value; pl.addConstraint(c); }
			 */
		}

		// Add the assets to the PLModel only after the VPs are in it
		/*
		 * for (Object obj : vertices) { mxCell cell = (mxCell) obj; Object
		 * value = cell.getValue();
		 * 
		 * if (value instanceof Asset) { Asset a = (Asset) value;
		 * pl.addAsset(a); // Get its connections. Object[] edges =
		 * getEdges(cell); for (Object o : edges) { mxCell edge = (mxCell) o;
		 * 
		 * mxCell target = (mxCell) edge.getTarget(); if (target.getValue()
		 * instanceof VariabilityElement) { VariabilityElement ve =
		 * (VariabilityElement) target .getValue(); String assetIdentifier =
		 * a.getIdentifier(); ve.getAssets().add(assetIdentifier);
		 * 
		 * System.out.println("Added asset"); }
		 * 
		 * mxCell source = (mxCell) edge.getSource(); if (source.getValue()
		 * instanceof VariabilityElement) { VariabilityElement ve =
		 * (VariabilityElement) source .getValue();
		 * ve.getAssets().add(a.getIdentifier());
		 * System.out.println("Added asset"); } }
		 * 
		 * } }
		 */
		return pl;
	}

	public void buildFromRefas(Refas pl, RefasGraphTree pli) {

		for (VariabilityElement vp : pl.getVariabilityElements()) {
			DefaultMutableTreeNode root = pli.getRoot2();
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(vp);
			root.add(node);
			pli.getModel().nodeStructureChanged(root);
		}
	}

	private void buildFromProductLine(AbstractModel pl) {

		for (VariabilityElement vp : pl.getVariabilityElements())
			insertVertex(null, vp.getIdentifier(), vp, 0, 0, 80, 40, "plnode");

		// for (Constraint c : pl.getConstraints())
		// buildConstraint(pl, c);

		// pl.printDebug(System.out);
	}

	private void buildConstraint(AbstractModel pl, Constraint c) {
		/*
		 * TODO constraints of the new language if( c instanceof
		 * OptionalConstraint ){ OptionalConstraint oc = (OptionalConstraint)c;
		 * insertEdge(null, c.getIdentifier(), c,
		 * getCellById(oc.getFeature1Id()), getCellById(oc.getFeature2Id()),
		 * "ploptional"); } if( c instanceof ExcludesConstraint ){
		 * ExcludesConstraint ec = (ExcludesConstraint)c; insertEdge(null,
		 * c.getIdentifier(), c, getCellById(ec.getFeature1Id()),
		 * getCellById(ec.getFeature2Id()), "plexcludes"); } if( c instanceof
		 * RequiresConstraint ){ RequiresConstraint rc = (RequiresConstraint)c;
		 * insertEdge(null, c.getIdentifier(), c,
		 * getCellById(rc.getFeature1Id()), getCellById(rc.getFeature2Id()),
		 * "plrequires"); }
		 * 
		 * if( c instanceof MandatoryConstraint ){ MandatoryConstraint mc =
		 * (MandatoryConstraint)c; insertEdge(null, c.getIdentifier(), c,
		 * getCellById(mc.getFeature1Id()), getCellById(mc.getFeature2Id()),
		 * "plmandatory");
		 * 
		 * }
		 * 
		 * if( c instanceof GroupConstraint ){ GroupConstraint gc =
		 * (GroupConstraint)c; mxCell parent = getCellById(gc.getParent());
		 * 
		 * //Insert the middlepoint mxCell cons = (mxCell) insertVertex(null,
		 * c.getIdentifier(), gc, 0, 0, 20, 20, "plgroup");
		 * 
		 * //Connect parent -> middlepoint insertEdge(null, "", "", parent,
		 * cons); for(int i = 0; i < gc.getChildCount(); i++){ mxCell child =
		 * getCellById(gc.getChildId(i)); insertEdge(null, "", "", cons, child);
		 * } }
		 * 
		 * if( c instanceof GenericConstraint ){ insertVertex(null,
		 * c.getIdentifier(), c, 0, 0, 80, 40, "plcons"); }
		 */
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

			if (cell.getValue() instanceof GroupGConstraint) {
				GroupGConstraint gc = (GroupGConstraint) cell.getValue();
				if (gc.getParent() == null)
					return "Needs Parent";
			}

			if ( // TODO evaluate new constraints
			cell.getValue() instanceof GroupGConstraint
					|| cell.getValue() instanceof MandatoryConstraint
					|| cell.getValue() instanceof OptionalConstraint
					|| cell.getValue() instanceof RequiresConstraint
					|| cell.getValue() instanceof ExcludesConstraint
					|| cell.getValue() instanceof GenericConstraint) {

			} else {

				if (cell.getValue() instanceof Constraint)
					return "Not Implemented Yet";
			}
		}
		return super.validateCell(objCell, context);
	}

	@Override
	public boolean isValidConnection(Object source, Object target) {
		if (!(source instanceof mxCell) || !(target instanceof mxCell)) {
			return super.isValidConnection(source, target);
		}
		mxCell s = (mxCell) source;
		mxCell t = (mxCell) target;

		if (s.isEdge() || t.isEdge())
			return false;

		if (s.getValue() instanceof Constraint) {
			return !(t.getValue() instanceof Constraint);
		}

		if (t.getValue() instanceof Constraint) {
			return !(s.getValue() instanceof Constraint);
		}

		if (s.getValue() instanceof VariabilityElement
				&& t.getValue() instanceof VariabilityElement)
			return constraintAddingMode != ConstraintMode.None;

		// boolean ret = super.isValidConnection(source, target);
		//
		// System.out.println("Is valid Connection: " + ret);
		return super.isValidConnection(source, target);
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
		if (cell.getValue() instanceof GroupGConstraint) {
			GroupGConstraint gc = (GroupGConstraint) cell.getValue();
			return gc.getCardinalityString();
		}

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
		if (modelViewSubIndex == -1)
			cell = getCellById(modelViewIndex + e.getIdentifier());
		else
			cell = getCellById(modelViewIndex + e.getIdentifier() + "-"
					+ modelViewSubIndex);
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
