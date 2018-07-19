/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.variamos.reasoning.fragop;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

import com.variamos.common.core.utilities.FileUtilsApache;

import static java.nio.file.StandardCopyOption.*;

/**
 *
 * @author DanielGara
 */
public class Fragmental {
    
	public static ArrayList<String> component_folders = new ArrayList<String>();
    public static String assets_folder="C:/assets/";
    public static String assembled_folder="C:/integrated/";
    public static List<Map<String, String>> data;
    public static List<String> error_var;

    public static void principal(List<Map<String, String>> data_received) {
    	error_var=new ArrayList<>();
    	data = new ArrayList<>();
        data=data_received;
        Fragment.data_no_fragments = new ArrayList<>();
        Fragment.fragments_pmedium = new ArrayList<Fragment>();
        Fragment.fragments_plow = new ArrayList<Fragment>();
        clean_directories();
        assemble_assets();
    }
    
    public static String get_errors(){
    	String errors="";
    	if(error_var.size()<=0) {return "";}
    	for(int i=0; i<error_var.size();i++) {
    		errors+="- "+error_var.get(i)+"\n";
    	}
    	return errors;
    }
    
    public static void assemble_assets(){
		//no fragments
		for(int i=0;i<data.size();i++){
			if(data.get(i).get("filename").equals("")) {
				error_var.add("Missing filename field for: "+data.get(i).get("ID"));
			}else if(!data.get(i).get("filename").contains(".frag")){
		        move_asset(data.get(i));
		        Fragment.data_no_fragments.add(data.get(i));
		    }
		}
		
		//fragments high
		for(int i=0;i<data.size();i++){
			if(data.get(i).get("filename").equals("")) {}
			else if(data.get(i).get("filename").contains(".frag")){
				parse_fragment(data.get(i));
			}
		}
		
		//fragments medium 
		if(Fragment.fragments_pmedium.size()>0) {
			for(int i=0;i<Fragment.fragments_pmedium.size();i++){
				Fragment.fragments_pmedium.get(i).execute_actions();
			}
		}
		
		//fragments low
		if(Fragment.fragments_plow.size()>0) {
			for(int i=0;i<Fragment.fragments_plow.size();i++){
				Fragment.fragments_plow.get(i).execute_actions();
			}
		}
    }
    
    public static void parse_fragment(Map<String, String> fragment){
        File source_f = new File(assets_folder+fragment.get("component_folder")+"/"+fragment.get("filename"));
        if(source_f.exists()){
            try{
                String f_content = FileUtilsApache.readFileToString(source_f, "utf-8");
                int pos_frag=0;
                while(pos_frag!=-1) {
                	Fragment f1 = new Fragment(f_content,fragment.get("filename"),fragment.get("component_folder"),pos_frag);
                	pos_frag=extract_multiple_fragments(f_content,pos_frag);
                }
            }
            catch(Exception e){
            	//error_var.add(e.getMessage()+e.getStackTrace());
            }
        }else {
        	error_var.add(fragment.get("filename")+" doesn't exists, check the filename and path");
        }
    }
    
    public static int extract_multiple_fragments(String content, int pos_frag) {
    	pos_frag=pos_frag+10;
    	int pos_init = content.indexOf("Fragment ",pos_frag);
    	if(pos_init != -1) {
    		int pos_final = content.indexOf("{",pos_init+9);
    		if(pos_final != -1) {
    			return pos_init;
    		}else {
    			return -1;
    		}
    	}else {
    		return -1;
    	}
    	
    }
    
    
    public static void move_asset(Map<String, String> asset){
        set_directories_move_file(asset.get("filename"),asset.get("destination"),asset.get("component_folder"));        
    }
    
    public static void clean_directories(){
        File source_f = new File(assembled_folder);
        if(source_f.exists()){
            try{
                FileUtilsApache.deleteDirectory(source_f);
                source_f.mkdirs();
            }
            catch(Exception e){
                //System.out.println(e.getMessage());
            }
        }
    }
    
    public static void set_directories_move_file(String filename, String destination, String component_folder){
        File source_f = new File(assets_folder+component_folder+"/"+filename);
        if(source_f.exists()){
            File dest_f = new File(assembled_folder+destination);
            File dest_path = new File(dest_f.getParent());
            if(!dest_path.exists()){
                dest_path.mkdirs();
            }
            try{
                //Files.copy(source_f.toPath(), dest_f.toPath(), REPLACE_EXISTING);
                Files.copy(source_f.toPath(), dest_f.toPath());
            }
            catch(Exception e){
            	error_var.add("C04 - "+e.getMessage()+e.getStackTrace());
            }
        }else{
        	error_var.add(filename+" doesn't exists, check the filename and path");
        }
    }
    
    //start customization functions
    
    public static ArrayList<String> check_folder(String foldername){
    	ArrayList<String> data_file = new ArrayList<String>();
    	File source_f = new File(assets_folder+foldername+"/customization.json");
        if(source_f.exists()){
            try{
                String f_content = FileUtilsApache.readFileToString(source_f, "utf-8");
                JSONObject json = new JSONObject(f_content);
                
                if (json.get("CustomizationPoints") instanceof JSONArray) {
                	JSONArray cpoints = (JSONArray) json.get("CustomizationPoints");
                	JSONArray plans = (JSONArray) json.get("PointBracketsLans");
                	JSONArray ids = (JSONArray) json.get("IDs");
                	data_file.add("multiple");
                	data_file.add(Integer.toString(cpoints.length()));

                	for (int i = 0; i < cpoints.length(); i++) {
                		String cpoint = cpoints.get(i).toString();
	                	String plan = plans.get(i).toString();
	                	String id = ids.get(i).toString();
	                	data_file.add(id);
	                	data_file.add(cpoint);
	                	data_file.add(plan);
                	}
                }else {
                	String cpoint = json.getString("CustomizationPoints");
                	String plan = json.getString("PointBracketsLans");
                	String id = json.getString("IDs");
                	data_file.add("one");
                	data_file.add(id);
                	data_file.add(cpoint);
                	data_file.add(plan);
                }
            }
            catch(Exception e){
            	//error_var.add(e.getMessage()+e.getStackTrace());
            }
        }else {
        	data_file.add("no");
        }
    	return data_file;
    }
    
    public static String customize_one(String ID, String cpoint, String plan){
    	return Fragment.get_customization_code(ID, cpoint, plan);
    }
    
    public static void set_customize_one(String ID, String cpoint, String plan, String ccode){
    	Fragment.set_customization_code(ID, cpoint, plan, ccode);
    }
    
    /*public static void customize() {
    	for(String foldername : component_folders) {
	    	File source_f = new File(assets_folder+foldername+"/customization.json");
	        if(source_f.exists()){
	            try{
	                String f_content = FileUtilsApache.readFileToString(source_f, "utf-8");
	                JSONObject json = new JSONObject(f_content);
	                
	                if (json.get("CustomizationPoints") instanceof JSONArray) {
	                	JSONArray cpoints = (JSONArray) json.get("CustomizationPoints");
	                	JSONArray plans = (JSONArray) json.get("PointBracketsLans");
	                	JSONArray ids = (JSONArray) json.get("IDs");

	                	for (int i = 0; i < cpoints.length(); i++) {
	                		String cpoint = cpoints.get(i).toString();
		                	String plan = plans.get(i).toString();
		                	String id = ids.get(i).toString();
	                		//System.out.println(i);
	                	}
	                }else {
	                	String cpoint = json.getString("CustomizationPoints");
	                	String plan = json.getString("PointBracketsLans");
	                	String id = json.getString("IDs");
	                	//System.out.println(cpoint);
	                }
	            }
	            catch(Exception e){
	            	//error_var.add(e.getMessage()+e.getStackTrace());
	            }
	        }
    	}
    }*/
    
}