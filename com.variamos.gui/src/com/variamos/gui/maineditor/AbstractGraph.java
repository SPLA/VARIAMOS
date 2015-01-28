package com.variamos.gui.maineditor;


import javax.swing.tree.DefaultMutableTreeNode;

import com.cfm.common.AbstractModel;
import com.cfm.productline.AbstractElement;
import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.Editable;
import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.shape.mxMarkerRegistry;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.view.mxGraph;
import com.variamos.editor.logic.ConstraintMode;
import com.variamos.gui.pl.editor.shapes.OptionalMarker;
import com.variamos.gui.refas.editor.RefasGraph;
import com.variamos.refas.RefasModel;
import com.variamos.semantic.types.PerspectiveType;

public abstract class AbstractGraph extends mxGraph {

	protected ConstraintMode constraintAddingMode = ConstraintMode.None;
	
	public static final String PL_EVT_NODE_CHANGE = "plEvtNodeChange";
	
	public AbstractGraph() {
		init();
	}

	protected  void init() {
		//Options
		setLabelsVisible(true);
		setAllowDanglingEdges(false);
		//Register custom styles
		mxMarkerRegistry.registerMarker("OptionalMarker", new OptionalMarker());
				
		// Loads the default styles sheet from an external file
		// To draw elements on the Graph
		/*mxCodec codec = new mxCodec();
		Document doc = mxUtils.loadDocument(VariamosGraphComponent.class
				.getResource(
						"/com/variamos/gui/pl/editor/style/styles.xml")
				.toString());
		codec.decode(doc.getDocumentElement(), stylesheet);
		*/
		setCellsEditable(false);
		setAllowNegativeCoordinates(false);
		setRestrictions();
		addListeners();
	}
	public abstract void setModel(AbstractModel abstractModel);
	private void addListeners() {
		addListener(mxEvent.CELLS_REMOVED, new mxIEventListener(){

			@Override
			public void invoke(Object sender, mxEventObject evt) {
				Object[] removedCells = (Object[]) evt.getProperty("cells");
				for( Object remObj : removedCells ){
					mxCell cell = (mxCell)remObj;
					removingRefaElements(cell);
					if( cell.isEdge() ){						
						removingEdge(cell);
					}
					else
						removingClones(cell);
				}
			}


		});
		
		addListener(mxEvent.CELLS_ADDED, new mxIEventListener() {
			
	
			public void invoke(Object sender, mxEventObject evt) {
				Object[] addedCells = (Object[]) evt.getProperty("cells");
				mxCell parentCell = (mxCell) evt.getProperty("parent");
				int indexCell = (int) evt.getProperty("index");
				for( Object obj : addedCells ){
					mxCell cell = (mxCell)obj;
					if( cell == null )
						return;
					
					if( !cell.isEdge() ){
						addingVertex(cell, parentCell, indexCell);
					}
					else
						if (!addingEdge(cell, parentCell, indexCell))
						{
							System.out.println("Relation not supported by the MetaModel");
							
						}
				}
			}

			
		});
	}

	protected void removingRefaElements(mxCell cell) {
		// TODO Auto-generated method stub
		
	}
	protected void removingClones(mxCell cell)
	{}
	protected boolean addingEdge(mxCell cell, mxCell parent, int index)
	{ return false;}
	
	protected boolean addingVertex(mxCell cell, mxCell parent, int index) {
		
		if( cell.getValue() instanceof VariabilityElement ){
			VariabilityElement elm = (VariabilityElement) cell.getValue();
			
			if( elm.getIdentifier() != null && !"".equals(elm.getIdentifier()) )
				return false;
			
			ProductLine pl = getProductLine();
			String id = pl.getNextVPId();
			
			//Aqui se setea el name y id por primera vez.
			elm.setIdentifier(id);
			elm.setName("<<new>>");
			
			//Change the cell id in the model.
			mxGraphModel model = (mxGraphModel) getModel();
			model.getCells().remove(cell.getId());
			model.getCells().put(id, cell);
			cell.setId(id);
		}
		
		if( cell.getValue() instanceof GroupConstraint ){
			GroupConstraint gc = (GroupConstraint) cell.getValue();
		
			if( gc.getIdentifier() != null && !"".equals(gc.getIdentifier()) )
				return false;
			
			ProductLine pl = getProductLine();
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
				return false;
			
			ProductLine pl = getProductLine();
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
				return false;
			
			ProductLine pl = getProductLine();
			pl.addAsset(a);
			
			mxGraphModel model = (mxGraphModel) getModel();
			model.getCells().remove(cell.getId());
			model.getCells().put(a.getIdentifier(), cell);
			cell.setId(a.getIdentifier());

		}
		return true;
	}
	
	private void removingEdge(mxCell cell){
		mxCell source = (mxCell)cell.getSource();
		mxCell target = (mxCell)cell.getTarget();
	/*	
		if( source.getValue() instanceof GroupConstraint ){
			//The target is a child, it should be removed
			GroupConstraint gc = (GroupConstraint)source.getValue();
			gc.removeChild(target.getId());
		}
		
		if( target.getValue() instanceof GroupConstraint ){
			//The source is the parent, it should be removed
			GroupConstraint gc = (GroupConstraint)target.getValue();
			gc.setParent(null);
		}
		*/
	}
	

	public void setPLElementsVisibility(boolean visibility){
		Object[] vertices = mxGraphModel.getChildCells(getModel(), getDefaultParent(), true, false);
		System.out.println("Setting PL Visibility: " + visibility);
		for(Object obj : vertices){
			mxCell cell = (mxCell)obj;
			Object value = cell.getValue();
						
			if( value instanceof AbstractElement ){
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
	
	@Deprecated
	private AbstractModel getlModel()
	{
		if (this instanceof RefasGraph)
			return getRefas();
		else
			return getProductLine();
	}
	
	//TODO: change to refas - add to refas models
	@Deprecated
	private RefasModel getRefas(){
		RefasModel pl = new RefasModel(PerspectiveType.modeling);
		
		//Object[] vertices = getChildVertices(getDefaultParent());
		Object[] vertices = mxGraphModel.getChildCells(getModel(), getDefaultParent(), true, false);
		
		for(Object obj : vertices){
			mxCell cell = (mxCell)obj;
			Object value = cell.getValue();
			
			if( value instanceof VariabilityElement ){
				VariabilityElement vp = (VariabilityElement) value;
				//pl.addVariabilityPoint(vp);
				
				for(Object edgObj : getEdges(cell, null, false, true, true) ){
					mxCell edge = (mxCell)edgObj;
					if( edge.getValue() instanceof Constraint ){
						//pl.addConstraint( (Constraint) edge.getValue());
					}
				}
				
			}
			
			if( value instanceof Constraint ){
				Constraint c = (Constraint) value;
				//pl.addConstraint(c);
			}
		}
		
		//Add the assets to the PLModel only after the VPs are in it
		for(Object obj : vertices){
			mxCell cell = (mxCell)obj;
			Object value = cell.getValue();
			
			if( value instanceof Asset ){
				Asset a = (Asset) value;
				//pl.addAsset(a);
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
	
	
	public ProductLine getProductLine(){
		ProductLine pl = new ProductLine();
		
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
	
	private void setRestrictions(){

	}
	
	public void buildFromProductLine2(ProductLine pl, GraphTree pli) {
		
		for (VariabilityElement vp : pl.getVariabilityElements())
		{
			DefaultMutableTreeNode root = pli.getRoot2();
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(vp);
			root.add(node);
			pli.getModel().nodeStructureChanged(root);
		}
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
		
	}
}
