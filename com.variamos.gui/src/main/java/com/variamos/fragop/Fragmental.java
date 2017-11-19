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
    
    //public static String assets_folder="E://Proyectos/NetBeans/Fragmental/assets/";
    public static String assets_folder="E:/Proyectos/Fragmental/assets/";
    public static String assembled_folder="E:/Proyectos/Fragmental/assembled/";
    public static List<Map<String, String>> data = new ArrayList<>();
    public static List<String> error_var;

    public static void principal() {
    	error_var=new ArrayList<>();
        pull_data();
        clean_directories();
        assemble_assets();
        Boolean found_errors=show_errors();
        if(found_errors) {
            System.out.println("!!!Components assembled with multiple errors!!!");
        }else {
            System.out.println("!!!Components successfully assembled!!!");
        }
    }
    
    public static Boolean show_errors(){
    	if(error_var.size()<=0) {return false;}
    	for(int i=0; i<error_var.size();i++) {
    		System.out.println(error_var.get(i));
    	}
    	return true;
    }
    
    public static void assemble_assets(){
        //no fragments
        for(int i=0;i<data.size();i++){
            if(data.get(i).get("fragment").equals("0")){
                move_asset(data.get(i));
                Fragment.data_no_fragments.add(data.get(i));
            }
        }
        //fragments
        for(int i=0;i<data.size();i++){
            if(data.get(i).get("fragment").equals("1")){
                parse_fragment(data.get(i));
            }
        }
    }
    
    public static void parse_fragment(Map<String, String> fragment){
        File source_f = new File(assets_folder+fragment.get("component_folder")+"/"+fragment.get("filename"));
        if(source_f.exists()){
            try{
                String f_content = FileUtils.readFileToString(source_f, "utf-8");
                Fragment f1 = new Fragment(f_content);
            }
            catch(Exception e){
            	error_var.add(e.getMessage());
            }
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
            	error_var.add(e.getMessage());
            }
        }else{
        	error_var.add(filename+" doesn't exists, check the filename and path");
        }
    }
    
    public static void pull_data(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("component_folder", "BasicViewsHtml");
        map.put("fragment", "0");
        map.put("ID", "BasicViewsHtml-Header");
        map.put("filename", "header.jsp");
        map.put("destination", "application/views/header.jsp");
        data.add(0, map);
        
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("component_folder", "BasicViewsHtml");
        map2.put("ID", "BasicViewsHtml-Footer");
        map2.put("fragment", "0");
        map2.put("filename", "footer.jsp");
        map2.put("destination", "application/views/footer.jsp");
        data.add(1, map2);
        
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("component_folder", "ListProducts");
        map3.put("fragment", "1");
        map3.put("ID", "ListProducts-AlterHeader");
        map3.put("filename", "alterHeader.frag");
        data.add(2, map3);
    }
    
}
