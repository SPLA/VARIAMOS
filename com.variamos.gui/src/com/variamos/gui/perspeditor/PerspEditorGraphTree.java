package com.variamos.gui.perspeditor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.variamos.gui.pl.editor.ProductLineGraph;


/**
 * @author jcmunoz
 * Copied from GraphTree
 * 
 */
@SuppressWarnings("serial")
public class PerspEditorGraphTree extends JTree{
	
	//private static final String SELECTED_NODE = "ProductLineIndex.SELECTED_NODE";
	//private mxEventSource evtSource;
	private PerspEditorGraph graph;
	
	public void reset(){
		
		DefaultMutableTreeNode root = getRoot();
	    root.removeAllChildren();
	    getModel().reload();
	}
	
	public PerspEditorGraphTree(){
		//evtSource = new mxEventSource();
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		DefaultTreeModel model = new DefaultTreeModel(root);
		setModel(model);
		
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		MouseListener ml = new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        int selRow = getRowForLocation(e.getX(), e.getY());
		        TreePath selPath = getPathForLocation(e.getX(), e.getY());

		        if(selRow > 0) {
		            if(e.getClickCount() == 1) {
		            	//One click !! do nothing 
		            }
		            else if(e.getClickCount() == 2) {
		            	DefaultMutableTreeNode selectedNode = ((DefaultMutableTreeNode)selPath.getLastPathComponent());
		            	VariabilityElement v = (VariabilityElement)selectedNode.getUserObject();
		            	//System.out.println(v.getName());
		            	selectElement(v);
		            	//evtSource.fireEvent(new mxEventObject(SELECTED_NODE, "node", v));
		            }
		        }
		    }
		};
		
		addMouseListener(ml);
	}
	
	protected void selectElement(VariabilityElement elm) {
		mxCell c = graph.getCellById(elm.getIdentifier());
	//	System.out.println("Selecting: " + elm.getIdentifier() + " c: " + c);
		graph.setSelectionCell(c);
	}
	
	protected DefaultMutableTreeNode findNode(String id){
		DefaultMutableTreeNode node = null;
		DefaultMutableTreeNode root = getRoot();
		
		for(@SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration(); e.hasMoreElements() ;){
			DefaultMutableTreeNode n = e.nextElement();
			if( n.getUserObject() instanceof VariabilityElement){
				VariabilityElement v = (VariabilityElement) n.getUserObject();
				if( v.getIdentifier().equals(id) )
					return n;
			}
		}
		
		return node;
	}
	
	public void populate(ProductLine pl){
		getRoot().setUserObject(pl);
		getModel().nodeChanged(getRoot());
	}
	
	public DefaultMutableTreeNode getRoot2(){
		return ((DefaultMutableTreeNode)getModel().getRoot());
	}
	
	private DefaultMutableTreeNode getRoot(){
		return ((DefaultMutableTreeNode)getModel().getRoot());
	}
	
	public DefaultTreeModel getModel(){
		return (DefaultTreeModel) super.getModel();
	}
	
	public void bind(PerspEditorGraph graph){
		graph.addListener(mxEvent.CELLS_ADDED, new mxIEventListener() {
			
			@Override
			public void invoke(Object sender, mxEventObject evt) {
				for(Object obj : (Object[])evt.getProperty("cells")){
					mxCell cell = (mxCell) obj; 
					if( cell == null )
						continue;
					
					if( cell.isEdge() )
						onAddedEdge(cell);
					else
						onAddedVertex( cell );
				}

			}
		});
		
		graph.addListener(mxEvent.CELLS_REMOVED, new mxIEventListener() {
			
			@Override
			public void invoke(Object sender, mxEventObject evt) {
				for(Object obj : (Object[])evt.getProperty("cells")){
					mxCell cell = (mxCell) obj;
					DefaultMutableTreeNode n = findNode(cell.getId());
					if( n != null )
						getModel().removeNodeFromParent(n);
				}
			}
		});
		
		graph.addListener(ProductLineGraph.PL_EVT_NODE_CHANGE, new mxIEventListener() {
			
			@Override
			public void invoke(Object sender, mxEventObject evt) {
				Object elm = evt.getProperty("element");
				if( elm instanceof VariabilityElement ){
					VariabilityElement e = (VariabilityElement) elm;
					DefaultMutableTreeNode n = findNode(e.getIdentifier());
					if( n != null )
						getModel().nodeChanged(n);
				}
			}
		});
		
		this.graph = graph;
	}

	protected void onAddedEdge(mxCell cell){
		
	}
	
	protected void onAddedVertex(mxCell cell) {
		if( ! (cell.getValue() instanceof VariabilityElement) )
			return;
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(cell.getValue());
		getRoot().add(node);
		getModel().nodeStructureChanged(getRoot());
	}
}
