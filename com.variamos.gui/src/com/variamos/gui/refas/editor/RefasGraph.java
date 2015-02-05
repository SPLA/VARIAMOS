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
import com.variamos.editor.logic.ConstraintMode;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.refas.RefasModel;
import com.variamos.syntax.instancesupport.InstAttribute;
import com.variamos.syntax.instancesupport.InstCell;
import com.variamos.syntax.instancesupport.InstConcept;
import com.variamos.syntax.instancesupport.InstElement;
import com.variamos.syntax.instancesupport.InstEnumeration;
import com.variamos.syntax.instancesupport.InstOverTwoRelation;
import com.variamos.syntax.instancesupport.InstPairwiseRelation;
import com.variamos.syntax.instancesupport.InstVertex;
import com.variamos.syntax.instancesupport.InstView;
import com.variamos.syntax.metamodelsupport.MetaElement;
import com.variamos.syntax.metamodelsupport.MetaPairwiseRelation;
import com.variamos.syntax.metamodelsupport.MetaView;

public class RefasGraph extends AbstractGraph {

	protected ConstraintMode constraintAddingMode = ConstraintMode.None;

	public static final String PL_EVT_NODE_CHANGE = "plEvtNodeChange";
	private RefasModel refasModel = null;
	private int modelViewIndex = 0;
	private int modelViewSubIndex = -1;
	private boolean validation = true;
	private int perspective = 2;

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

	public RefasGraph(int perspective) {
		init();
		this.perspective = perspective;
	}

	public RefasGraph(int perspective,
			RefasModel refasModel) {
		init();		
		this.perspective = perspective;
		this.refasModel = refasModel;
	}

	public void defineInitialGraph() {
		mxCell root = new mxCell();
		root.insert(new mxCell());
		getModel().setRoot(root);
		Collection<InstView> views = refasModel.getSyntaxRefas().getInstViews();
		int pos = 0;
		if (views.size() == 0) {
			//Load Syntax and Semantic
			for (InstVertex instVertex : refasModel.getVertices()) {
				mxCell child = new mxCell(new InstCell(instVertex, false));
				child.setId(instVertex.getIdentifier());
				addCell(child);
				String id = instVertex.getIdentifier();

				child.setVisible(true);
				child.setStyle(instVertex.getTransSupportMetaElement()
						.getStyle());
				child.setGeometry(new mxGeometry(50 + pos * 3, 50 + pos * 3,
						120, 100));
				child.setVertex(true);
				mxGraphModel model = (mxGraphModel) getModel();
				model.getCells().remove(child.getId());
				model.getCells().put("0"+id, child);
				child.setId("0"+id);
				pos++;

			}
			for (InstView instView : refasModel.getInstViews()) {
				if (instView.getChildViews().size() == 0) {
					mxCell child = new mxCell(instView.getIdentifier());
					child.setValue(new InstCell(instView, false));
					addCell(child);
					String id = instView.getIdentifier();
					child.setVisible(true);
					child.setStyle(instView.getTransSupportMetaElement()
							.getStyle());
					child.setGeometry(new mxGeometry(50 + pos * 3,
							50 + pos * 3, 120, 40));
					child.setVertex(true);
					mxGraphModel model = (mxGraphModel) getModel();
					model.getCells().remove(child.getId());
					model.getCells().put("0"+id, child);
					child.setId("0"+id);
					pos++;
				}
				for (InstView instChildView : instView.getChildViews()) {
					mxCell child2 = new mxCell(new InstCell(instChildView,
							false));
					child2.setId(instChildView.getIdentifier());
					addCell(child2);
					String id2 = instChildView.getIdentifier();
					child2.setVisible(true);
					child2.setStyle(instChildView.getTransSupportMetaElement()
							.getStyle());
					child2.setGeometry(new mxGeometry(50 + pos * 3,
							50 + pos * 3, 120, 40));
					child2.setVertex(true);
					mxGraphModel model2 = (mxGraphModel) getModel();
					model2.getCells().remove(child2.getId());
					model2.getCells().put("0"+id2, child2);
					child2.setId("0"+id2);
					pos++;

				}
			}

			for (InstPairwiseRelation instEdge : refasModel
					.getConstraintInstEdgesCollection()) {
				if (instEdge.getSourceRelations().size() != 0
						&& instEdge.getIdentifier() != null
						&& !instEdge.getIdentifier().equals("")) {
					mxCell child = new mxCell(new InstCell(instEdge, false));
					child.setId(instEdge.getIdentifier());
					addCell(child);
					mxCell source = this.getCellById(modelViewIndex+instEdge
							.getSourceRelations().get(0).getIdentifier());
					mxCell target = this.getCellById(modelViewIndex+instEdge
							.getTargetRelations().get(0).getIdentifier());
					child.setStyle("");
					MetaElement e = instEdge.getTransSupportMetaElement();
					if (e != null) {
						child.setStyle(e.getStyle());
					}

					child.setSource(source);
					child.setTarget(target);
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
					model.getCells().put(modelViewIndex+id, child);
					child.setId(modelViewIndex+id);
				}
			}
		}
		
		//Load views for System Design and simulation
		int i = 0;
		for (Object view : views) {
			mxCell parent = new mxCell(new InstCell(null, false));
			parent.setId("mv" + i);
			addCell(parent);
			if (refasModel.getSyntaxRefas() == null) {
				MetaView metaView = (MetaView) view;
				if (metaView.getChildViews().size() > 0) {
					mxCell child = new mxCell(new InstCell(null, false));
					child.setId("mv" + i);
					addCell(child, parent); // Add the parent as
											// first child
					for (int j = 0; j < metaView.getChildViews().size(); j++) {
						mxCell child2 = new mxCell(new InstCell(null, false));
						child2.setId("mv" + i + "-" + j);
						addCell(child2, parent);
					}
				}
			} else {
				InstView instView = (InstView) view;
				if (instView.getChildViews().size() > 0) {
					for (int j = 0; j < instView.getChildViews().size(); j++) {
						mxCell child2 = new mxCell(new InstCell(null, false));
						child2.setId("mv" + i + "-" + j);
						addCell(child2, parent);
					}
				}
			}
			i++;
		}
	}

	public List<String> getValidElements(int modelView, int modelSubView) {
		return refasModel.getSyntaxRefas().modelElements(modelView,
					modelSubView);
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
			InstElement instSource = ((InstCell) s.getValue()).getInstElement();
			InstElement instTarget = ((InstCell) t.getValue()).getInstElement();

			HashMap<String, InstAttribute> map = new HashMap<String, InstAttribute>();
			InstPairwiseRelation directRelation = new InstPairwiseRelation(map,
					null);
			RefasModel refas = getRefas();
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
		InstElement source = ((InstCell) cell.getSource().getValue())
				.getInstElement();
		InstElement target = ((InstCell) cell.getTarget().getValue())
				.getInstElement();
		Object value = cell.getValue();
		String id = null;
		HashMap<String, InstAttribute> map = new HashMap<String, InstAttribute>();
		// TODO fill the map with allowed relation types
		if (value instanceof InstCell) {
			InstPairwiseRelation element = (InstPairwiseRelation) ((InstCell) value)
					.getInstElement();

			String elementIdentifier = element.getIdentifier();
			if (elementIdentifier != null && !"".equals(elementIdentifier))
				return true;
		}
		InstPairwiseRelation directRelation = new InstPairwiseRelation(map,
				null);
		RefasModel refas = getRefas();

		id = refas.addNewConstraintInstEdge(directRelation);
		cell.setValue(new InstCell(directRelation, false));
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
					modelViewIndex + id + "-" + modelViewSubIndex, cell);
			cell.setId(modelViewIndex + id + "-" + modelViewSubIndex);
		} else {
			refasGraph.getCells().put(modelViewIndex + id, cell);
			cell.setId(modelViewIndex + id);
		}

		return true;
	}

	public InstCell getInstCell() {
		// Move new element to the current View - clone if
		// necessary in multiple views
		mxGraphModel refasGraph = (mxGraphModel) getModel();
		// Main Root
		Object rootCell = refasGraph.getRoot();
		// Null Root
		mxCell viewsParent = (mxCell) refasGraph.getChildAt(rootCell, 0);
		// Top level view /Element
		InstCell instCell = null;
		int iTop = 0;
		while ((instCell == null || instCell.getInstElement() != null)
				&& iTop < viewsParent.getChildCount()) {
			mxCell topLevelView = (mxCell) refasGraph.getChildAt(viewsParent,
					iTop);
			InstCell value = (InstCell) topLevelView.getValue();
			iTop++;
			if (value!= null && value.getInstElement() != null)
			{
				return (InstCell) value;
			}				
			int iMed = 0;
			while ((instCell == null || instCell.getInstElement() != null)
					&& iMed < topLevelView.getChildCount()) {
				mxCell secondLevelCell = (mxCell) refasGraph.getChildAt(
						topLevelView, iMed);
				InstCell value2 = (InstCell) secondLevelCell.getValue();				
				if (value2 != null && value2.getInstElement() != null) {
					modelViewIndex = iMed;
					modelViewSubIndex = -1;
						return (InstCell) value2;
				}
				iMed++;
				int iLow = 0;
				while ((instCell == null || instCell.getInstElement() != null)
						&& iLow < secondLevelCell.getChildCount()) {
					InstCell element = (InstCell) ((mxCell) refasGraph
							.getChildAt(secondLevelCell, iLow)).getValue();					
					if (element != null && element.getInstElement() != null) {
						modelViewIndex = iMed;
						modelViewSubIndex = iLow;
							return (InstCell) element;
					}
					iLow++;
				}
			}
		}
		return null;

	}

	// TODO review from here for requirements

	protected boolean addingVertex(mxCell cell, mxCell parent, int index) {
		InstElement value = ((InstCell) cell.getValue()).getInstElement();
		if (value instanceof InstVertex) {
			String id = null;
			String elementIdentifier = null;
			RefasModel pl = getRefas();
			InstElement instElement = ((InstCell) cell.getValue())
					.getInstElement();
			if (cell.getGeometry() != null) {
				if (instElement instanceof InstVertex) {
					InstVertex element = (InstVertex) instElement;
					elementIdentifier = element.getIdentifier();
					if (elementIdentifier != null
							&& !"".equals(elementIdentifier))
						return false;
					if (instElement instanceof InstOverTwoRelation)
						id = pl.addNewInstGroupDependency((InstOverTwoRelation) element);
					else if (instElement instanceof InstEnumeration)
						id = pl.addNewOtherInstElement(element);
					else
						id = pl.addNewVariabilityInstElement(element);
				} else {
					/*
					 * elementIdentifier = ((Constraint) instElement)
					 * .getIdentifier(); if (elementIdentifier != null &&
					 * !"".equals(elementIdentifier)) return false;
					 */
					return false;
				}
				if (id != null) {

					// Move new element to the current View - clone if
					// necessary in multiple views
					mxGraphModel refasGraph = (mxGraphModel) getModel();
					// Main Root
					Object rootCell = refasGraph.getRoot();
					// Null Root
					Object viewsParent = refasGraph.getChildAt(rootCell, 0);
					// Top level view
					Object topLevelView = null;
					// Parent to add new element
					Object correctParentCell = null;
					mxGraphModel model = refasGraph;
					model.getCells().remove(cell.getId());
					if (modelViewSubIndex != -1) {
						// Top Level View
						topLevelView = refasGraph.getChildAt(viewsParent,
								modelViewIndex);
						// Child view
						correctParentCell = refasGraph.getChildAt(topLevelView,
								modelViewSubIndex);

						model.getCells().put(
								modelViewIndex + id + "-" + modelViewSubIndex,
								cell);
						cell.setId(modelViewIndex + id + "-"
								+ modelViewSubIndex);
					} else if (modelViewIndex != -1) {
						// Top Level view
						correctParentCell = refasGraph.getChildAt(viewsParent,
								modelViewIndex);
						model.getCells().put(modelViewIndex + id, cell);
						cell.setId(modelViewIndex + id);
					} else {
						// Graph without Views
						// Add to the view Parent
						correctParentCell = viewsParent;
						model.getCells().put(id, cell);
						cell.setId(id);
					}
					// Remove from original position
					parent.remove(index);
					// Add to the parent according to the model
					model.add(correctParentCell, cell, 0);

					if (instElement instanceof InstVertex) {
						String name = null;
						if (instElement instanceof InstConcept) {
							InstConcept c = (InstConcept) instElement;
							name = c.getTransSupportMetaElement()
									.getIdentifier();
						}
						if (instElement instanceof InstOverTwoRelation) {
							InstOverTwoRelation c = (InstOverTwoRelation) instElement;
							name = c.getSupportMetaOverTwoRelation()
									.getIdentifier();
						}
						viewsParent = refasGraph.getChildAt(rootCell, 0); // Null
																			// Root
						for (int i = 0; i < refasGraph
								.getChildCount(viewsParent); i++) {
							mxCell mv1 = (mxCell) refasGraph.getChildAt(
									viewsParent, i);
							String mv1id = mv1.getId();
							if (refasGraph.getChildCount(mv1) > 0
									&& mv1.getChildAt(0).getId()
											.startsWith(mv1id)) {
								for (int j = 0; j < refasGraph
										.getChildCount(mv1); j++) {
									mxCell mv2 = (mxCell) refasGraph
											.getChildAt(mv1, j);
									if (refasModel.getSyntaxRefas()
											.elementsValidation(name, i, j)
										&& (i != modelViewIndex || j != modelViewSubIndex)) {

										mxCell c2 = null;
										try {
											c2 = (mxCell) cell.clone();
										} catch (CloneNotSupportedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										c2.setId(i + id + "-" + j);

										c2.setValue(new InstCell(
												((InstCell) cell.getValue())
														.getInstElement(), true));

										getModel().setVisible(c2, false);
										getModel().setVisible(c2, true);
										model.add(mv2, c2, mv2.getChildCount());
										// Add a clone to other models
									}

								}

							} else {
								// if (valid[i] && i != modelViewIndex) {
								if (refasModel.getSyntaxRefas()
										.elementsValidation(name, i, -1)
										&& i != modelViewIndex) {

									mxCell c2 = null;
									try {
										c2 = (mxCell) cell.clone();
									} catch (CloneNotSupportedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									c2.setId(i + id);
									c2.setValue(new InstCell(((InstCell) cell
											.getValue()).getInstElement(), true));
									getModel().setVisible(c2, false);
									getModel().setVisible(c2, true);
									model.add(mv1, c2, mv1.getChildCount());
									// Add a clone to other
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
		InstElement instElement = ((InstCell) cell.getValue()).getInstElement();
		if (instElement != null)
			refasModel.removeElement(instElement);
	}

	protected void removingss(mxCell cell) {
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
		refasModel = (RefasModel) pl;
		defineInitialGraph();
		try {
			mxGraphLayout layout = new mxOrganicLayout(this);
			layout.execute(getDefaultParent()); // todo change root?
		} catch (Exception e) {
			System.out.println("RefasGraph: Auto Layout failed");
		}
	}

	public RefasModel getRefas() {
		/*
		 * if (refasModel == null) { refasModel = new
		 * Refas(PerspectiveType.modeling); return refasModel; }
		 */
		return refasModel;
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
			// mxCell cell = (mxCell) objCell;

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

	public void refreshVariable(InstCell instCell) {

		mxCell cell = null;
		InstElement instElement = instCell.getInstElement();
		if (modelViewIndex == -1)
			cell = getCellById(instElement.getIdentifier());
		else if (modelViewSubIndex == -1)
			cell = getCellById(modelViewIndex + instElement.getIdentifier());
		else
			cell = getCellById(modelViewIndex + instElement.getIdentifier()
					+ "-" + modelViewSubIndex);
		getModel().setValue(cell, instCell);
		this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE, "cell", cell,
				"element", instCell));
	}
}
