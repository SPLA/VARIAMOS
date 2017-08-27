package com.variamos.gui.pl.editor;

import java.awt.Image;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.tree.DefaultMutableTreeNode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.Editable;
import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.constraints.ExcludesConstraint;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.cfm.productline.constraints.MandatoryConstraint;
import com.cfm.productline.constraints.OptionalConstraint;
import com.cfm.productline.constraints.RequiresConstraint;
import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.canvas.mxGraphicsCanvas2D;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.shape.mxStencil;
import com.mxgraph.shape.mxStencilRegistry;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.editor.logic.ConstraintMode;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.gui.maineditor.GraphTree;

public class ProductLineGraph extends AbstractGraph {

	protected ConstraintMode constraintAddingMode = ConstraintMode.None;

	public static final String PL_EVT_NODE_CHANGE = "plEvtNodeChange";

	public ProductLineGraph() {
		init();
	}

	@Override
	protected void init() {
		super.init();

		// Loads the default styles sheet from an external file
		// To draw elements on the Graph
		mxCodec codec = new mxCodec();
		Document doc = mxUtils.loadDocument(ProductLineGraph.class.getResource(
				"/com/variamos/gui/perspeditor/style/styles.xml").toString());
		codec.decode(doc.getDocumentElement(), stylesheet);
		loadStencil();
	}

	public void loadStencil() {
		try {
			String filename = ProductLineGraph.class.getResource(
					"/com/variamos/gui/perspeditor/style/shapes.xml").getPath();
			Document doc;

			doc = mxXmlUtils.parseXml(mxUtils.readFile(filename));

			Element shapes = doc.getDocumentElement();
			NodeList list = shapes.getElementsByTagName("shape");

			for (int i = 0; i < list.getLength(); i++) {
				Element shape = (Element) list.item(i);
				mxStencilRegistry.addStencil(shape.getAttribute("name"),
						new mxStencil(shape) {
							@Override
							protected mxGraphicsCanvas2D createCanvas(
									final mxGraphics2DCanvas gc) {
								// Redirects image loading to graphics canvas
								return new mxGraphicsCanvas2D(gc.getGraphics()) {
									@Override
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

	@Override
	public void setModelInstance(InstanceModel abstractModel) {
		// ProductLine pl = (ProductLine) abstractModel;
		// buildFromProductLine(pl);
		// mxGraphLayout layout = new mxFastOrganicLayout(this);
		// layout.execute(getDefaultParent());
	}

	@Override
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

	@Override
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

	@Override
	public ProductLine getProductLine() {
		ProductLine pl = new ProductLine();

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

	@Override
	public void buildFromProductLine2(ProductLine pl, GraphTree pli) {

		for (VariabilityElement vp : pl.getVariabilityElements()) {
			DefaultMutableTreeNode root = pli.getRoot2();
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(vp);
			root.add(node);
			pli.getModel().nodeStructureChanged(root);
		}
	}

	private void buildFromProductLine(ProductLine pl) {

		for (VariabilityElement vp : pl.getVariabilityElements())
			insertVertex(null, vp.getIdentifier(), vp, 0, 0, 80, 40, "plnode");

		for (Constraint c : pl.getConstraints())
			buildConstraint(pl, c);

		// pl.printDebug(System.out);
	}

	private void buildConstraint(ProductLine pl, Constraint c) {

		if (c instanceof OptionalConstraint) {
			OptionalConstraint oc = (OptionalConstraint) c;
			insertEdge(null, c.getIdentifier(), c,
					getCellById(oc.getFeature1Id()),
					getCellById(oc.getFeature2Id()), "ploptional");
		}
		if (c instanceof ExcludesConstraint) {
			ExcludesConstraint ec = (ExcludesConstraint) c;
			insertEdge(null, c.getIdentifier(), c,
					getCellById(ec.getFeature1Id()),
					getCellById(ec.getFeature2Id()), "plexcludes");
		}
		if (c instanceof RequiresConstraint) {
			RequiresConstraint rc = (RequiresConstraint) c;
			insertEdge(null, c.getIdentifier(), c,
					getCellById(rc.getFeature1Id()),
					getCellById(rc.getFeature2Id()), "plrequires");
		}

		if (c instanceof MandatoryConstraint) {
			MandatoryConstraint mc = (MandatoryConstraint) c;
			insertEdge(null, c.getIdentifier(), c,
					getCellById(mc.getFeature1Id()),
					getCellById(mc.getFeature2Id()), "plmandatory");

		}

		if (c instanceof GroupConstraint) {
			GroupConstraint gc = (GroupConstraint) c;
			mxCell parent = getCellById(gc.getParent());

			// Insert the middlepoint
			mxCell cons = (mxCell) insertVertex(null, c.getIdentifier(), gc, 0,
					0, 20, 20, "plgroup");

			// Connect parent -> middlepoint
			insertEdge(null, "", "", parent, cons);
			for (int i = 0; i < gc.getChildCount(); i++) {
				mxCell child = getCellById(gc.getChildId(i));
				insertEdge(null, "", "", cons, child);
			}
		}

		if (c instanceof GenericConstraint) {
			insertVertex(null, c.getIdentifier(), c, 0, 0, 80, 40, "plcons");
		}
	}

	@Override
	public mxCell getCellById(String id) {
		return (mxCell) ((mxGraphModel) getModel()).getCell(id);
	}

	@Override
	public ConstraintMode getConsMode() {
		return constraintAddingMode;
	}

	@Override
	public void setConsMode(ConstraintMode consMode) {
		this.constraintAddingMode = consMode;
	}

	@Override
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

	@Override
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

			if (cell.getValue() instanceof GroupConstraint
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
		if (cell.getValue() instanceof GroupConstraint) {
			GroupConstraint gc = (GroupConstraint) cell.getValue();
			return gc.getCardinalityString();
		}

		// Optional and mandatory
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

	@Override
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

	}
}