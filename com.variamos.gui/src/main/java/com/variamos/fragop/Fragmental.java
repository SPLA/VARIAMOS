/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.variamos.fragop;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import static java.nio.file.StandardCopyOption.*;

/**
 *
 * @author DanielGara
 */
public class Fragmental {
    
    public static String assets_folder="C:/assets/";
    public static String assembled_folder="C:/assembled/";
    public static List<Map<String, String>> data;
    public static List<String> error_var;

    public static void principal(List<Map<String, String>> data_received) {
    	error_var=new ArrayList<>();
    	data = new ArrayList<>();
        data=data_received;
        Fragment.data_no_fragments = new ArrayList<>();
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
        //fragments
        for(int i=0;i<data.size();i++){
        	if(data.get(i).get("filename").equals("")) {}
        	else if(data.get(i).get("filename").contains(".frag")){
                parse_fragment(data.get(i));
            }
        }
    }
    
    public static void parse_fragment(Map<String, String> fragment){
        File source_f = new File(assets_folder+fragment.get("component_folder")+"/"+fragment.get("filename"));
        if(source_f.exists()){
            try{
                String f_content = FileUtils.readFileToString(source_f, "utf-8");
                Fragment f1 = new Fragment(f_content,fragment.get("filename"));
            }
            catch(Exception e){
            	error_var.add(e.getMessage()+e.getStackTrace());
            }
        }else {
        	error_var.add(fragment.get("filename")+" doesn't exists, check the filename and path");
        }
    }
    
    public static void move_asset(Map<String, String> asset){
        set_directories_move_file(asset.get("filename"),asset.get("destination"),asset.get("component_folder"));        
    }
    
    public static void clean_directories(){
        File source_f = new File(assembled_folder);
        if(source_f.exists()){
            try{
                FileUtils.deleteDirectory(source_f);
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
                Files.copy(source_f.toPath(), dest_f.toPath(), REPLACE_EXISTING);
            }
            catch(Exception e){
            	error_var.add(e.getMessage()+e.getStackTrace());
            }
        }else{
        	error_var.add(filename+" doesn't exists, check the filename and path");
        }
    }    
}