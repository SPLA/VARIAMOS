package com.variamos.gui.refas.editor;

<<<<<<< HEAD
import java.awt.Image;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.tree.DefaultMutableTreeNode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.cfm.productline.AbstractElement;
import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.Editable;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.constraints.ExcludesConstraint;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.cfm.productline.constraints.MandatoryConstraint;
import com.cfm.productline.constraints.OptionalConstraint;
import com.cfm.productline.constraints.RequiresConstraint;
import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.canvas.mxGraphicsCanvas2D;
import com.mxgraph.examples.swing.Stencils;
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
import com.variamos.gui.maineditor.GraphTree;
import com.variamos.gui.maineditor.VariamosGraphComponent;
import com.variamos.pl.editor.logic.ConstraintMode;
import com.variamos.refas.concepts.Assumption;
import com.variamos.refas.concepts.Goal;
import com.variamos.refas.concepts.Operationalization;
import com.variamos.refas.concepts.Refas;

public class RefasGraph extends AbstractGraph {

	protected ConstraintMode constraintAddingMode = ConstraintMode.None;

	public static final String PL_EVT_NODE_CHANGE = "plEvtNodeChange";
	private Refas refas = null;
	private int modelView = 0;

	public int getModelView() {
		return modelView;
	}

	public void setModelView(int modelView) {
		this.modelView = modelView;
	}

	public RefasGraph() {
		init();
	}

	protected void init() {
		super.init();
		// Loads the defalt stylesheet from an external file
		mxCodec codec = new mxCodec();
		Document doc = mxUtils.loadDocument(VariamosGraphComponent.class
				.getResource("/com/variamos/gui/pl/editor/style/styles.xml")
				.toString());
		codec.decode(doc.getDocumentElement(), stylesheet);
		loadStencil();
	}

	public void loadStencil() {
		try {
			String filename = Stencils.class.getResource(
					"/com/variamos/gui/refas/editor/style/shapes.xml")
					.getPath();
			Document doc;

			doc = mxXmlUtils.parseXml(mxUtils.readFile(filename));

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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// TODO review from here for requirements

	protected boolean addingVertex(mxCell cell, mxCell parent, int index) {

		if (cell.getValue() instanceof AbstractElement) {
			AbstractElement a = (AbstractElement) cell.getValue();

			if (a.getIdentifier() != null && !"".equals(a.getIdentifier()))
				return false;

			Refas pl = getRefas();
			boolean added = false;
			if (cell.getValue() instanceof AbstractElement) {
				added = pl.addElement(modelView, a);
			}

			if (added) {
				mxGraphModel refasGraph = (mxGraphModel) getModel();
				Object o = refasGraph.getRoot(); // Main Root
				Object o1 = refasGraph.getChildAt(o, 0); // Null Root
				Object mv0 = refasGraph.getChildAt(o1, modelView);
	
				mxGraphModel model = refasGraph;
				model.getCells().remove(cell.getId());
				model.getCells().put(a.getIdentifier(), cell);
				cell.setId(a.getIdentifier());
				parent.remove(index); // Remove from original position
				model.add(mv0, cell, 0); // Add to the parent according to the
											// model
				boolean valid [] = getRefas().elementsValidation(a.getClass().getSimpleName());
				for (int i = 0; i < 5; i++) {
					if (valid[i] && i!=modelView)
					{
					mxCell c2 = null;
					try {
						c2 = (mxCell) cell.clone();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					c2.setId(a.getIdentifier());
					Object mv1 = refasGraph.getChildAt(o1, i);
					model.add(mv1, c2, 0); // Add a clone to other models
					}
				}
				this.refresh();
				return true;
			} else {
				mxGraphModel model = (mxGraphModel) getModel();
				model.getCells().remove(cell.getId());
				if (parent.getIndex(cell) == index)
					parent.remove(index);
				return false;
			}
		}

		if (cell.getValue() instanceof GroupConstraint) {
			GroupConstraint gc = (GroupConstraint) cell.getValue();

			if (gc.getIdentifier() != null && !"".equals(gc.getIdentifier()))
				return false;

			Refas pl = getRefas();
			String id = pl.getNextConstraintId();

			gc.setIdentifier(id);

			// Change the cell id in the model
			mxGraphModel model = (mxGraphModel) getModel();
			model.getCells().remove(cell.getId());
			model.getCells().put(id, cell);
			cell.setId(id);
		}
		if (cell.getValue() instanceof GenericConstraint) {
			GenericConstraint gc = (GenericConstraint) cell.getValue();

			if (gc.getIdentifier() != null && !"".equals(gc.getIdentifier()))
				return false;

			Refas pl = getRefas();
			String id = pl.getNextConstraintId();

			gc.setIdentifier(id);

			// Change the cell id in the model
			mxGraphModel model = (mxGraphModel) getModel();
			model.getCells().remove(cell.getId());
			model.getCells().put(id, cell);
			cell.setId(id);
		}

		if (cell.getValue() instanceof Asset) {
			Asset a = (Asset) cell.getValue();

			if (a.getIdentifier() != null && !"".equals(a.getIdentifier()))
				return false;

			Refas pl = getRefas();
			pl.addAsset(a);

			mxGraphModel model = (mxGraphModel) getModel();
			model.getCells().remove(cell.getId());
			model.getCells().put(a.getIdentifier(), cell);
			cell.setId(a.getIdentifier());
		}
		return true;
	}

	protected void removingClones(mxCell cell) {
		Object vertices[] = mxGraphModel.getChildCells(getModel(),
				getDefaultParent(), true, false);
		;
	}

	public void setRefas(Refas pl) {
		buildFromProductLine(pl);
		mxGraphLayout layout = new mxFastOrganicLayout(this);
		layout.execute(getDefaultParent()); // todo change root?
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
		for (int mvInd = 0; mvInd < 5; mvInd++) {
			mxCell mv0 = (mxCell) refasGraph.getChildAt(o1, mvInd); // Root
																	// model
																	// view
																	// mvInd
			Object[] vertices = mxGraphModel.getChildCells(getModel(), mv0,
					true, false);
			for (int i = 0; i < vertices.length; i++) {
				mxCell cell = ((mxCell) vertices[i]);
				if (modelView != mvInd) {
					getModel().setVisible(cell, false);
					Object[] edges1 = getEdges(cell);
					for (Object oo : edges1)
						getModel().setVisible(oo, false);
					this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE,
							"cell", cell));
				}
			}
			for (int i = 0; i < vertices.length; i++) {
				mxCell cell = ((mxCell) vertices[i]);
				if (modelView == mvInd) {
					getModel().setVisible(cell, true);
					Object[] edges2 = getEdges(cell);
					for (Object oo : edges2)
						getModel().setVisible(oo, true);
					this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE,
							"cell", cell));
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
				pl.addVariabilityPoint(vp);

				for (Object edgObj : getEdges(cell, null, false, true, true)) {
					mxCell edge = (mxCell) edgObj;
					if (edge.getValue() instanceof Constraint) {
						pl.addConstraint((Constraint) edge.getValue());
					}
				}

			}

			if (value instanceof Constraint) {
				Constraint c = (Constraint) value;
				pl.addConstraint(c);
			}
		}

		// Add the assets to the PLModel only after the VPs are in it
		for (Object obj : vertices) {
			mxCell cell = (mxCell) obj;
			Object value = cell.getValue();

			if (value instanceof Asset) {
				Asset a = (Asset) value;
				pl.addAsset(a);
				// Get its connections.
				Object[] edges = getEdges(cell);
				for (Object o : edges) {
					mxCell edge = (mxCell) o;

					mxCell target = (mxCell) edge.getTarget();
					if (target.getValue() instanceof VariabilityElement) {
						VariabilityElement ve = (VariabilityElement) target
								.getValue();
						String assetIdentifier = a.getIdentifier();
						ve.getAssets().add(assetIdentifier);

						System.out.println("Added asset");
					}

					mxCell source = (mxCell) edge.getSource();
					if (source.getValue() instanceof VariabilityElement) {
						VariabilityElement ve = (VariabilityElement) source
								.getValue();
						ve.getAssets().add(a.getIdentifier());
						System.out.println("Added asset");
					}
				}

			}
		}

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

	private void buildFromProductLine(Refas pl) {

		for (VariabilityElement vp : pl.getVariabilityElements())
			insertVertex(null, vp.getIdentifier(), vp, 0, 0, 80, 40, "plnode");

		for (Constraint c : pl.getConstraints())
			buildConstraint(pl, c);

		// pl.printDebug(System.out);
	}

	private void buildConstraint(Refas pl, Constraint c) {
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

			if (cell.getValue() instanceof GroupConstraint) {
				GroupConstraint gc = (GroupConstraint) cell.getValue();
				if (gc.getParent() == null)
					return "Needs Parent";
			}

			if ( // TODO evaluate new constraints
			cell.getValue() instanceof GroupConstraint
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
		if (cell.getValue() instanceof GroupConstraint) {
			GroupConstraint gc = (GroupConstraint) cell.getValue();
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

	public void refreshVariable(Editable e) {

		mxCell cell = getCellById(e.getIdentifier());
		// Update visibility
		if (e instanceof VariabilityElement) {
			VariabilityElement v = (VariabilityElement) e;
			// v.printDebug(System.out);
			getModel().setVisible(cell, v.isVisible());
			// v.getName();
			Object[] edges = getEdges(cell);
			for (Object o : edges)
				getModel().setVisible(o, v.isVisible());
		}
		getModel().setValue(cell, e);
		this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE, "cell", cell,
				"element", e));

=======
import java.util.Hashtable;

import javax.swing.tree.DefaultMutableTreeNode;

import org.w3c.dom.Document;

import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.Editable;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.constraints.ExcludesConstraint;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.cfm.productline.constraints.MandatoryConstraint;
import com.cfm.productline.constraints.OptionalConstraint;
import com.cfm.productline.constraints.RequiresConstraint;
import com.mxgraph.io.mxCodec;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxUtils;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.gui.maineditor.GraphTree;
import com.variamos.gui.maineditor.VariamosGraphComponent;
import com.variamos.pl.editor.logic.ConstraintMode;
import com.variamos.refas.concepts.Goal;
import com.variamos.refas.concepts.Refas;

public class RefasGraph extends AbstractGraph {

	protected ConstraintMode constraintAddingMode = ConstraintMode.None;
	
	public static final String PL_EVT_NODE_CHANGE = "plEvtNodeChange";
	
	public RefasGraph() {
		init();
	}

	protected void init() {
		super.init();
		// Loads the defalt stylesheet from an external file
		mxCodec codec = new mxCodec();
		Document doc = mxUtils.loadDocument(VariamosGraphComponent.class
				.getResource(
						"/com/variamos/gui/rq/editor/style/styles.xml")
				.toString());
		codec.decode(doc.getDocumentElement(), stylesheet);
		
	}
	
//TODO review from here for requirements
	
	private void addingVertex(mxCell cell) {
		
		if( cell.getValue() instanceof VariabilityElement ){
			VariabilityElement elm = (VariabilityElement) cell.getValue();
			
			if( elm.getIdentifier() != null && !"".equals(elm.getIdentifier()) )
				return;
			
			Refas pl = getRefas();
			String id = pl.getNextVPId();
			
			//Aqui se setea el name y id por primera vez.
			elm.setIdentifier(id);
			elm.setName(id);
			
			//Change the cell id in the model.
			mxGraphModel model = (mxGraphModel) getModel();
			model.getCells().remove(cell.getId());
			model.getCells().put(id, cell);
			cell.setId(id);
		}
		
		if( cell.getValue() instanceof GroupConstraint ){
			GroupConstraint gc = (GroupConstraint) cell.getValue();
		
			if( gc.getIdentifier() != null && !"".equals(gc.getIdentifier()) )
				return;
			
			Refas pl = getRefas();
			String id = pl.getNextConstraintId();
			
			gc.setIdentifier(id);
			
			//Change the cell id in the model
			mxGraphModel model = (mxGraphModel) getModel();
			model.getCells().remove(cell.getId());
			model.getCells().put(id, cell);
			cell.setId(id);
		}
		if( cell.getValue() instanceof GenericConstraint ){
			GenericConstraint gc = (GenericConstraint) cell.getValue();
			
			if( gc.getIdentifier() != null && !"".equals(gc.getIdentifier()) )
				return;
			
			Refas pl = getRefas();
			String id = pl.getNextConstraintId();
			
			gc.setIdentifier(id);
			
			//Change the cell id in the model
			mxGraphModel model = (mxGraphModel) getModel();
			model.getCells().remove(cell.getId());
			model.getCells().put(id, cell);
			cell.setId(id);
		}
		
		if( cell.getValue() instanceof Asset ){
			Asset a = (Asset) cell.getValue();
			
			if( a.getIdentifier() != null && !"".equals(a.getIdentifier()))
				return;
			
			Refas pl = getRefas();
			pl.addAsset(a);
			
			mxGraphModel model = (mxGraphModel) getModel();
			model.getCells().remove(cell.getId());
			model.getCells().put(a.getIdentifier(), cell);
			cell.setId(a.getIdentifier());
		}
		
		if( cell.getValue() instanceof Goal ){
			Goal a = (Goal) cell.getValue();
			
			if( a.getIdentifier() != null && !"".equals(a.getIdentifier()))
				return;
			
			Refas pl = getRefas();
			pl.addGoal(a);
			
			mxGraphModel model = (mxGraphModel) getModel();
			model.getCells().remove(cell.getId());
			model.getCells().put(a.getIdentifier(), cell);
			cell.setId(a.getIdentifier());
		}
		
		
	}
	
	public void setRefas(Refas pl){
		buildFromProductLine(pl);
		mxGraphLayout layout = new mxFastOrganicLayout(this);
        layout.execute( getDefaultParent() );
	}
	
	public void setPLElementsVisibility(boolean visibility){
		Object[] vertices = mxGraphModel.getChildCells(getModel(), getDefaultParent(), true, false);
		System.out.println("Setting PL Visibility: " + visibility);
		for(Object obj : vertices){
			mxCell cell = (mxCell)obj;
			Object value = cell.getValue();
						
			if( value instanceof VariabilityElement ){
				VariabilityElement vp = (VariabilityElement)value;
				//cell.setVisible(visibility && vp.isVisible());
				boolean vis = visibility && vp.isVisible();
				getModel().setVisible(cell, vis);
				
				Object[] edges = getEdges(cell);
				for(Object o : edges)
					getModel().setVisible(o, vis);
				
				this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE, "cell", cell));
				
			}
			
			if( value instanceof Constraint ){
				//cell.setVisible(visibility);
				getModel().setVisible(cell, visibility );
				this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE, "cell", cell));
			}
		}
	}
	
	public void setAssetsVisibility(boolean visibility){
		Object[] vertices = mxGraphModel.getChildCells(getModel(), getDefaultParent(), true, false);
		
		for(Object obj : vertices){
			mxCell cell = (mxCell)obj;
			Object value = cell.getValue();
			if( value instanceof Asset ){
				getModel().setVisible(cell, visibility);
				
				Object[] edges = getEdges(cell);
				for(Object o : edges)
					getModel().setVisible(o, visibility);
				
				this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE, "cell", cell));
			}
		}
	}
	
	public Refas getRefas(){
		Refas pl = new Refas();
		
		//Object[] vertices = getChildVertices(getDefaultParent());
		Object[] vertices = mxGraphModel.getChildCells(getModel(), getDefaultParent(), true, false);
		
		for(Object obj : vertices){
			mxCell cell = (mxCell)obj;
			Object value = cell.getValue();
			
			if( value instanceof VariabilityElement ){
				VariabilityElement vp = (VariabilityElement) value;
				pl.addVariabilityPoint(vp);
				
				for(Object edgObj : getEdges(cell, null, false, true, true) ){
					mxCell edge = (mxCell)edgObj;
					if( edge.getValue() instanceof Constraint ){
						pl.addConstraint( (Constraint) edge.getValue());
					}
				}
				
			}
			
			if( value instanceof Constraint ){
				Constraint c = (Constraint) value;
				pl.addConstraint(c);
			}
		}
		
		//Add the assets to the PLModel only after the VPs are in it
		for(Object obj : vertices){
			mxCell cell = (mxCell)obj;
			Object value = cell.getValue();
			
			if( value instanceof Asset ){
				Asset a = (Asset) value;
				pl.addAsset(a);
				//Get its connections.
				Object[] edges = getEdges(cell);
				for(Object o : edges){
					mxCell edge = (mxCell)o;
					
					mxCell target = (mxCell)edge.getTarget();
					if( target.getValue() instanceof VariabilityElement ){
						VariabilityElement ve = (VariabilityElement) target.getValue();
						String assetIdentifier=a.getIdentifier();
						ve.getAssets().add(assetIdentifier);
						
						
						System.out.println("Added asset");
					}
					
					mxCell source = (mxCell)edge.getSource();
					if( source.getValue() instanceof VariabilityElement ){
						VariabilityElement ve = (VariabilityElement) source.getValue();
						ve.getAssets().add(a.getIdentifier());
						System.out.println("Added asset");
					}
				}
					
			}
		}
		
		return pl;
	}

	public void buildFromRefas(Refas pl, RefasGraphTree pli) {
		
		for (VariabilityElement vp : pl.getVariabilityElements())
		{
			DefaultMutableTreeNode root = pli.getRoot2();
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(vp);
			root.add(node);
			pli.getModel().nodeStructureChanged(root);
		}
	}
	
	private void buildFromProductLine(Refas pl) {
		
		for (VariabilityElement vp : pl.getVariabilityElements())
			insertVertex(null, vp.getIdentifier(), vp, 0, 0, 80, 40, "plnode");
		
		for (Constraint c : pl.getConstraints())
			buildConstraint(pl, c);
		
		//pl.printDebug(System.out);
	}
	
	private void buildConstraint(Refas pl, Constraint c){
		/*
		 * TODO constraints of the new language
		if( c instanceof OptionalConstraint ){
			OptionalConstraint oc = (OptionalConstraint)c;
			insertEdge(null, c.getIdentifier(), c, getCellById(oc.getFeature1Id()), getCellById(oc.getFeature2Id()), "ploptional");
		}
		if( c instanceof ExcludesConstraint ){
			ExcludesConstraint ec = (ExcludesConstraint)c;
			insertEdge(null, c.getIdentifier(), c, getCellById(ec.getFeature1Id()), getCellById(ec.getFeature2Id()), "plexcludes");
		}
		if( c instanceof RequiresConstraint ){
			RequiresConstraint rc = (RequiresConstraint)c;
			insertEdge(null, c.getIdentifier(), c, getCellById(rc.getFeature1Id()), getCellById(rc.getFeature2Id()), "plrequires");
		}
		
		if( c instanceof MandatoryConstraint ){
			MandatoryConstraint mc = (MandatoryConstraint)c;
			insertEdge(null, c.getIdentifier(), c, getCellById(mc.getFeature1Id()), getCellById(mc.getFeature2Id()), "plmandatory");
			
		}
		
		if( c instanceof GroupConstraint ){
			GroupConstraint gc = (GroupConstraint)c;
			mxCell parent = getCellById(gc.getParent());
			
			//Insert the middlepoint
			mxCell cons = (mxCell) insertVertex(null, c.getIdentifier(), gc, 0, 0, 20, 20, "plgroup");

			//Connect parent -> middlepoint
			insertEdge(null, "", "", parent, cons);
			for(int i = 0; i < gc.getChildCount(); i++){
				mxCell child = getCellById(gc.getChildId(i));
				insertEdge(null, "", "", cons, child);
			}
		}
		
		if( c instanceof GenericConstraint ){
			insertVertex(null, c.getIdentifier(), c, 0, 0, 80, 40, "plcons");
		}*/
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
	
	public void connectDefaultConstraint(mxCell source, mxCell target){
		Constraint c = newConstraint(source.getId(), target.getId());
		
		double cX =  (target.getGeometry().getCenterX() + source.getGeometry().getCenterX())/2;
		double cY =  (target.getGeometry().getCenterY() + source.getGeometry().getCenterY())/2;
		
		mxCell constraintCell = (mxCell)insertVertex(null, c.getIdentifier(), c, cX, cY, 40, 20, "plcons");
		c.setIdentifier(constraintCell.getId());
		constraintCell.setConnectable(false);
		
		//Create edges.
		insertEdge(null, null, null, source, constraintCell);
		insertEdge(null, "", "", constraintCell, target);
	}
	
	protected Constraint newConstraint(String idSource, String idTarget){
		Constraint c = new GenericConstraint();
		
//		switch(constraintAddingMode){
//			case Default:
//				c.setText("General Constraint");
//				break;
//				
//			case Excludes:
//				c.setText("Exclude(:" + idSource + ", :" + idTarget+ ")" );
//				break;
//				
//			case Mandatory:
//				c.setText("Mandatory(:" + idSource + ", :" + idTarget+ ")" );
//				break;
//			case Optional:
//				c.setText("Optional(:" + idSource + ", :" + idTarget+ ")" );
//				break;
//			case Requires:
//				c.setText("Requires(:" + idSource + ", :" + idTarget+ ")" );
//				break;
//		default:
//			break;
//		}
		
		return c;
	}

//	@Override
//	public boolean isValidSource(Object cell) {
//		boolean isValid = consMode != ConstraintMode.None;
//		if( cell instanceof mxCell ){
//			mxCell s = (mxCell) cell;
//			if( !s.isEdge() && s.getValue() instanceof Constraint ){
//				isValid = true;
//				System.out.println("This is a constraint !");
//			}
//		}
//		
//		boolean all = super.isValidSource(cell) && isValid;
//		System.out.println("Is SUper valid Source: " + super.isValidSource(cell));
//		return all;
//	}
//	
//	@Override
//	public boolean isValidTarget(Object cell) {
//		if( cell instanceof mxCell ){
//			mxCell c = (mxCell)cell;
//			if( c.isEdge() )
//				return false;
//			
//			if( c.getValue() instanceof VariabilityPoint )
//				return true;
//		}
//		return super.isValidTarget(cell);
//	}

	@Override
	public String validateEdge(Object edge, Object source, Object target) {
		return super.validateEdge(edge, source, target);
	}

	@Override
	public String validateCell(Object objCell, Hashtable<Object, Object> context) {
		if( objCell instanceof mxCell ){
			mxCell cell = (mxCell)objCell;
			
			
			if( cell.getValue() instanceof GroupConstraint ){
				GroupConstraint gc = (GroupConstraint)cell.getValue();
				if( gc.getParent() == null )
					return "Needs Parent";
			}
			
			if( //TODO evaluate new constraints
					cell.getValue() instanceof GroupConstraint || 
					cell.getValue() instanceof MandatoryConstraint || 
					cell.getValue() instanceof OptionalConstraint || 
					cell.getValue() instanceof RequiresConstraint || 
					cell.getValue() instanceof ExcludesConstraint ||
					cell.getValue() instanceof GenericConstraint
				){
				
			}else{
				
				if( cell.getValue() instanceof Constraint )
					return "Not Implemented Yet";
			}
		}
		return super.validateCell(objCell, context);
	}

	@Override
	public boolean isValidConnection(Object source, Object target) {
		if( !(source instanceof mxCell) || !(target instanceof mxCell) ){
			return super.isValidConnection(source, target);
		}
		mxCell s = (mxCell) source;
		mxCell t = (mxCell) target;
		
		if( s.isEdge() || t.isEdge() )
			return false;
		
		if( s.getValue() instanceof Constraint ){
			return ! (t.getValue() instanceof Constraint);
		}
			
		if( t.getValue() instanceof Constraint ){
			return ! (s.getValue() instanceof Constraint);
		}
		
		if( s.getValue() instanceof VariabilityElement && t.getValue() instanceof VariabilityElement )
			return constraintAddingMode != ConstraintMode.None;
		
//		boolean ret = super.isValidConnection(source, target);
//		
//		System.out.println("Is valid Connection: " + ret);
		return super.isValidConnection(source, target);
	}

	@Override
	public String convertValueToString(Object obj) {
		mxCell cell = (mxCell)obj;
		//VariabilityPoint
		if( cell.getValue() instanceof VariabilityElement ){
			VariabilityElement vp = (VariabilityElement)cell.getValue();
			String name = vp.getName();
			if( name == null )
				name = "";
			
			return name;
		}
		
		if( cell.getValue() instanceof Asset ){
			Asset a = (Asset)cell.getValue();
			String name = a.getName();
			if( name == null )
				name = "";
			
			return name;
		}
		
		//GroupConstraint
		//TODO evaluate new group constraints
		if( cell.getValue() instanceof GroupConstraint ){
			GroupConstraint gc = (GroupConstraint) cell.getValue();
			return gc.getCardinalityString();
		}
		
		//Optional and mandatory
		//TODO evaluate new constraints		
		if( cell.getValue() instanceof OptionalConstraint || cell.getValue() instanceof MandatoryConstraint )
			return ""; 
		
		if( cell.getValue() instanceof ExcludesConstraint )
			return "excludes";
		
		if( cell.getValue() instanceof RequiresConstraint )
			return "requires";
		
		if( cell.getValue() instanceof GenericConstraint)
			return ((GenericConstraint)cell.getValue()).getText();
		
		return super.convertValueToString(obj);
	}

	@Override
	public void cellLabelChanged(Object cell, Object value, boolean autoSize) {
		super.cellLabelChanged(cell, value, autoSize);
	}
	
	public void refreshVariable(Editable e){
		
		mxCell cell = getCellById(e.getIdentifier());
		//Update visibility
		if( e instanceof VariabilityElement ){
			VariabilityElement v = (VariabilityElement) e;
			//v.printDebug(System.out);
			getModel().setVisible(cell, v.isVisible());
			//v.getName();
			Object[] edges = getEdges(cell);
			for(Object o : edges)
				getModel().setVisible(o, v.isVisible());
		}
		getModel().setValue(cell, e);
		this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE, "cell", cell, "element", e));
		
>>>>>>> branch 'development' of https://github.com/jcmunozf/VARIAMOS
	}
}
