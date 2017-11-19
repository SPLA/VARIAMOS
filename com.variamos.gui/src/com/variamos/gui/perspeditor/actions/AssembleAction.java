package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;
import com.variamos.dynsup.instance.InstCell;
import com.variamos.fragop.Fragmental;
import com.variamos.gui.core.viewcontrollers.AbstractVariamoGUIAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.PerspEditorGraph;
import com.variamos.gui.perspeditor.panels.AboutDialog;

/**
 * A class to call the about dialog. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-03-15
 */
@SuppressWarnings("serial")
public class AssembleAction extends AbstractVariamoGUIAction {

	public AssembleAction() {}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		try {
			mxGraphComponent graphComponent = editor.getGraphComponent();
			mxGraph graph = graphComponent.getGraph();
			mxIGraphModel model = graph.getModel();
			mxCell root = (mxCell) model.getRoot();
			mxCodec codec = new mxCodec();
			mxCell parent = (mxCell) root.getChildAt(0);
			mxCell actual_for_cell=null;
			mxCell feature_root_cell=null;
			mxCell fragment_root_cell=null;
			
			mxCell target_cell=null;
			
			String style ="";
			String id="";
			Map<String, String> map = null;

			//List<Map<String, List<Map<String, String>>>> components = new ArrayList<>();
			//List<Map<String, List<Map<String, String>>>> files_list = new ArrayList<>();
			//Map<String,  List<Map<String, String>>> files_map = new HashMap<String,  List<Map<String, String>>>();
			//List<Map<String, String>> files_data = new ArrayList<>();
			List<Map<String, String>> relations = new ArrayList<>();
			
			for(int i=0;i<parent.getChildCount();i++) {
				actual_for_cell = (mxCell) parent.getChildAt(i);
				String cellid = actual_for_cell.getId();
				if(cellid.contains("mv") && actual_for_cell.getChildCount()>0) {
					style = actual_for_cell.getChildAt(0).getStyle();
					if(style.equals("plnode")) {
						feature_root_cell=actual_for_cell;
					}else if(style.equals("refasasset") || style.equals("refasfile")) {
						fragment_root_cell=actual_for_cell;
					}
				}
			}
			
			if(fragment_root_cell!=null) {
				for(int i=0;i<fragment_root_cell.getChildCount();i++) {
					actual_for_cell = (mxCell) fragment_root_cell.getChildAt(i);
					style = actual_for_cell.getStyle();
					id = actual_for_cell.getId();
					if(!style.equals("refasasset") && !style.equals("refasfile")) {
						String source = actual_for_cell.getSource().getId();
						target_cell = (mxCell) actual_for_cell.getTarget();
						
						String xml =mxXmlUtils.getXml(codec.encode(target_cell));
						int pos_pre_dyn=xml.indexOf("as=\"Name\"");
						int pos_dyn=xml.indexOf("as=\"attId\" value=\"Name\"",pos_pre_dyn);
						int pos_init=xml.indexOf("Value\" value=\"",pos_dyn);
						int pos_final=xml.indexOf(">",pos_init);
						String target=xml.substring(pos_init+14, pos_final-2);
						
						System.out.println(source + "-" + target);
						map = new HashMap<String, String>();
						map.put(source, target);
						relations.add(map);
					}
				
				}
			}
			System.out.println("Cantidad Relations:" + relations.size());
			if(relations.size()>0) {
				System.out.println(relations.get(0).toString());
			}else{
				//assemble error
			}
			
			/*
			mxCell mvc = (mxCell) parent.getChildAt(0);
			
			System.out.println(root.getChildCount());
			System.out.println(root.getId());
			System.out.println(parent.getChildCount());
			System.out.println(parent.getId());
			System.out.println(mvc.getChildCount());
			System.out.println(mvc.getId());
			
			mxCell parent3 = (mxCell) parent.getChildAt(5);
			System.out.println(parent3.getAttribute("identifier"));
			mxCell parent2 = (mxCell) parent.getChildAt(7);
			System.out.println(parent2.getAttribute("identifier"));*/
			
			
			//String xml = URLEncoder.encode(mxXmlUtils.getXml(codec.encode(model)), "UTF-8");
			//System.out.println(xml);
			
			//BIEN-String xml = URLEncoder.encode(mxXmlUtils.getXml(codec.encode(graph.getModel())), "UTF-8");
			//BIEN-System.out.println(xml);
			/*mxCell root = (mxCell) editor.getGraphComponent().getGraph().getModel().getRoot();
			mxCell root2 = (mxCell) editor.getGraphComponent().getGraph().getModel().getRoot();
			mxCell parent = (mxCell) root.getChildAt(0);	
			mxCell parent2 = (mxCell) parent.getChildAt(0);
			System.out.println(parent.getChildCount());
			System.out.println(root.getAttribute("identifier"));
			System.out.println(root.getAttribute("userId"));
			System.out.println(parent2.getAttribute("identifier"));
			System.out.println(parent2.getAttribute("userId"));
			System.out.println(parent.getValue());*/
			

			//aca sigue mi código
			//Fragmental.principal();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}