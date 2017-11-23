package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

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
 * A class to assemble components
 * 
 * @author Daniel Correa <yo@danielgara.com>
 * @version 1.1
 * @since 2017-11-20
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
			mxCell fragment_root_cell=null;			
			mxCell target_cell=null;
			mxCell source_cell=null;			
			String style ="";
			String id="";
			
			for(int i=0;i<parent.getChildCount();i++) {
				actual_for_cell = (mxCell) parent.getChildAt(i);
				String cellid = actual_for_cell.getId();
				if(cellid.contains("mv") && actual_for_cell.getChildCount()>0) {
					style = actual_for_cell.getChildAt(0).getStyle();
					if(style.equals("refasasset") || style.equals("refasfile")) {
						fragment_root_cell=actual_for_cell;
					}
				}
			}
			
			List<Map<String, String>> files = new ArrayList<>();
			String xml_com ="";
			String xml_file ="";
			
			if(fragment_root_cell!=null) {
				//capture info
				for(int i=0;i<fragment_root_cell.getChildCount();i++) {
					actual_for_cell = (mxCell) fragment_root_cell.getChildAt(i);
					style = actual_for_cell.getStyle();
					id = actual_for_cell.getId();
					if(!style.equals("refasasset") && !style.equals("refasfile")) {
						target_cell = (mxCell) actual_for_cell.getTarget();
						source_cell = (mxCell) actual_for_cell.getSource();
						
						int pre=xml_com.indexOf("as=\"InstAtt\"");
						int pos_pre_dyn, pos_dyn;
						Map<String, String> file_map = new HashMap<String, String>();
						
						//Check if component is selected
						xml_com =mxXmlUtils.getXml(codec.encode(target_cell));						
						pos_pre_dyn=xml_com.indexOf("as=\"SelectedToAssemble\"",pre);
						pos_dyn=xml_com.indexOf("as=\"attId\" value=\"SelectedToAssemble\"",pos_pre_dyn);
						String selected=extract_string("Value\" value=\"",">",xml_com,pos_dyn);
						if(selected.equals("1")){
							//Component_folder
							pos_pre_dyn=xml_com.indexOf("as=\"Name\"",pre);
							pos_dyn=xml_com.indexOf("as=\"attId\" value=\"Name\"",pos_pre_dyn);
							String target=extract_string("Value\" value=\"",">",xml_com,pos_dyn);
							file_map.put("component_folder", target);
							//ID
							xml_file =mxXmlUtils.getXml(codec.encode(source_cell));
							pos_pre_dyn=xml_file.indexOf("as=\"Name\"");
							pos_dyn=xml_file.indexOf("as=\"attId\" value=\"Name\"",pos_pre_dyn);
							String source=extract_string("Value\" value=\"",">",xml_file,pos_dyn);
							file_map.put("ID", source);
							//Filename
							pos_pre_dyn=xml_file.indexOf("as=\"filename\"");
							pos_dyn=xml_file.indexOf("as=\"attId\" value=\"filename\"",pos_pre_dyn);
							file_map.put("filename", extract_string("Value\" value=\"",">",xml_file,pos_dyn));
							//Destination
							pos_pre_dyn=xml_file.indexOf("as=\"destination\"");
							pos_dyn=xml_file.indexOf("as=\"attId\" value=\"destination\"",pos_pre_dyn);
							file_map.put("destination", extract_string("Value\" value=\"",">",xml_file,pos_dyn));
							files.add(file_map);
						}
					}
				}
				Fragmental.principal(files);
				String found_errors=Fragmental.get_errors();
				if(found_errors.equals("")) {
					found_errors+="!!!Components successfully assembled!!!";
		        }else {
		        	found_errors+="!!!Components assembled with multiple errors!!!";
		        }
				JOptionPane.showMessageDialog(editor.getMainFrame(),found_errors, "Update Message",
						JOptionPane.INFORMATION_MESSAGE, null);
			}else {
				JOptionPane.showMessageDialog(editor.getMainFrame(),"There are not components files to assemble", "Update Message",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static String extract_string(String s_init, String s_final, String content, int pos_ref){
        int pos_init = content.indexOf(s_init,pos_ref);
        int len_init = s_init.length();
        int pos_final = content.indexOf(s_final, pos_init);
        if((pos_init != -1) && (pos_final != -1)){
            String f_string = content.substring(pos_init+len_init, pos_final-2).trim();
            return f_string;
        }else{
            return null;
        }
    }
}