package com.variamos.gui.treetable.core;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultTreeSelectionModel;
 
public class TreeTableSelectionModel extends DefaultTreeSelectionModel {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TreeTableSelectionModel() {
        super();
        setSelectionMode(DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
        getListSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                 
            }
        });
    }
     
    public ListSelectionModel getListSelectionModel() {
        return listSelectionModel;
    }
}