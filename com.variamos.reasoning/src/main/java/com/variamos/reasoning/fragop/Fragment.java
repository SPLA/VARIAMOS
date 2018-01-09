/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.variamos.reasoning.fragop;

import static com.variamos.reasoning.fragop.Fragmental.assembled_folder;
import static com.variamos.reasoning.fragop.Fragmental.error_var;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.common.core.utilities.FileUtilsApache;



/**
 *
 * @author DanielGara
 */
public class Fragment {
    public String content;
    public String filename;
    public int pos_frag;

	public Map<String, String> data = new HashMap<String, String>();
    public static List<Map<String, String>> data_no_fragments = new ArrayList<>();
    
    public Fragment(String c, String f, int p){
        this.setContent(c);
        this.setFilename(f);
        this.setPos_frag(p);
        this.parse_fragment_content();
        if(data.get("name")==null || data.get("action")==null || data.get("destination")==null || data.get("fpoint")== null || data.get("pointbracketslan")== null) {
        	Fragmental.error_var.add("Invalid Fragment definition for:" + this.getFilename());
        }else {
        	this.execute_actions();
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
    public int getPos_frag() {
		return pos_frag;
	}

	public void setPos_frag(int pos_frag) {
		this.pos_frag = pos_frag;
	}

	public void execute_actions(){
        Map<String, String> f_to_modify = new HashMap<String, String>();
        List<String> comment_block_tags = new ArrayList<String>();
        
        f_to_modify=get_fragment_by_ID(data.get("destination"),data.get("name"));
        comment_block_tags=get_comment_block(data.get("pointbracketslan"));
        String f_to_modify_content = "";

        if(f_to_modify!=null){
            if(data.get("action").equals("replace") || data.get("action").equals("hide") || (data.get("action").equals("add") && data.get("sourcecode") != null ) || (data.get("action").equals("remove") && data.get("fpoint") != null )){
                File source_f_to_modify = new File(assembled_folder+f_to_modify.get("destination"));
                if(source_f_to_modify.exists()){
                    try{
                        f_to_modify_content = FileUtilsApache.readFileToString(source_f_to_modify, "utf-8");
                    }
                    catch(Exception e){
                    	Fragmental.error_var.add(e.getMessage());
                    }
                }
                
                //ADD CODE OR FILE
                if(data.get("action").equals("add")){
                    if(data.get("sourcecode") != null){
                        String string_search = comment_block_tags.get(0)+"B-"+data.get("fpoint")+comment_block_tags.get(1);
                        int pos_init = f_to_modify_content.indexOf(string_search);
                        if(pos_init != -1){
                            String new_content = f_to_modify_content.substring(0,pos_init+string_search.length());
                            String trace = this.add_trace(comment_block_tags,data.get("sourcecode"),"add","injected");
                            new_content += trace;
                            new_content += f_to_modify_content.substring(pos_init+string_search.length());
                            try{
                                FileUtilsApache.writeStringToFile(source_f_to_modify, new_content, "utf-8");
                            }
                            catch(Exception e){
                            	Fragmental.error_var.add(e.getMessage());
                            }
                        }
                        else {
                        	Fragmental.error_var.add("Invalid fragmentation point: "+string_search+", doesn't exists (At file "+f_to_modify.get("filename")+") - (Fragment "+data.get("name")+")");
                        }
                    }else if(data.get("sourcefile")!=null){
                        //FALTA
                    }
                }//REPLACE - HIDE - REMOVE CODE
                else if(data.get("action").equals("replace") || data.get("action").equals("hide") || (data.get("action").equals("remove") && data.get("fpoint") != null)){
                    String string_search = comment_block_tags.get(0)+"B-"+data.get("fpoint")+comment_block_tags.get(1);
                    String string_search2 = comment_block_tags.get(0)+"E-"+data.get("fpoint")+comment_block_tags.get(1);
                    
                    int pos_init = f_to_modify_content.indexOf(string_search);
                    int pos_final = f_to_modify_content.indexOf(string_search2);
                    if(pos_init != -1 && pos_final != -1){
                        String new_content = f_to_modify_content.substring(0,pos_init+string_search.length());
                        String trace = "";
                        if(data.get("sourcecode") != null && data.get("action").equals("replace")){
                            trace = this.add_trace(comment_block_tags,data.get("sourcecode"),"add","replaced");
                        }else if(data.get("action").equals("hide")){
                            String new_code=f_to_modify_content.substring(pos_init+string_search.length(),pos_final);
                            trace = this.add_trace(comment_block_tags,new_code,"hide","hidden");
                        }else if(data.get("action").equals("remove")){
                            trace = this.add_trace(comment_block_tags,"","remove","removed");
                        }
                        new_content += trace;
                        new_content += f_to_modify_content.substring(pos_final);
                        try{
                        	FileUtilsApache.writeStringToFile(source_f_to_modify, new_content, "utf-8");
                        }
                        catch(Exception e){
                        	Fragmental.error_var.add(e.getMessage());
                        }
                    }else {
                    	Fragmental.error_var.add("Invalid fragmentation point: "+string_search+", doesn't exists (At file "+f_to_modify.get("filename")+") - (Fragment "+data.get("name")+")");
                    }
                }//REMOVE FILE
                else if(data.get("action").equals("remove") && data.get("fpoint") == null){
                    //FALTA
                }else {
                	Fragmental.error_var.add("Invalid action: "+data.get("action")+")");
                }
            }
        }
    }
    
    public String add_trace(List<String> tags, String code, String action, String label){
        String string_t="\n\n";
        string_t+=tags.get(0)+"Code "+label+" by: "+data.get("name")+tags.get(1);
        string_t+="\n";
        
        if(action.equals("hide")){
            string_t+=tags.get(0)+code+tags.get(1);
        }else if(action.equals("add")){
            string_t+=code;
        }
        
        string_t+="\n";
        string_t+=tags.get(0)+"Code "+label+" by: "+data.get("name")+tags.get(1);
        string_t+="\n";
        return string_t;
    }
    
    public void parse_fragment_content(){
        data.put("name",extract_string("Fragment ","{",this.content));
        data.put("action",extract_string("Action:", "\n",this.content));
        data.put("fpoint",extract_string("FragmentationPoint:","\n",this.content));
        data.put("pointbracketslan",extract_string("PointBracketsLan:","\n",this.content));
        data.put("destination",extract_string("Destination:","\n",this.content));
        //data.put("sourcefile",extract_string("SourceFile:","\n",this.content));        
        data.put("sourcecode",extract_string("[ALTERCODE-FRAG]","[/ALTERCODE-FRAG]",this.content));
    }
    
    public static Map<String, String> get_fragment_by_ID(String ID, String name){
        for(int i=0;i<data_no_fragments.size();i++){
            if(ID.equals(data_no_fragments.get(i).get("ID"))){
                return data_no_fragments.get(i);
            }
        }
        return null;
    }
    
    public String extract_string(String s_init, String s_final, String content){
        int pos_init = content.indexOf(s_init,this.getPos_frag());
        int len_init = s_init.length();
        int pos_final = content.indexOf(s_final, pos_init);
        if((pos_init != -1) && (pos_final != -1)){
            String f_string = content.substring(pos_init+len_init, pos_final).trim();
            return f_string;
        }else{
            return null;
        }
    }
    
    public static List<String> get_comment_block(String language){
        List<String> comment_block_tags = new ArrayList<String>();
        if(language.equals("php") || language.equals("css") || language.equals("java")){
            comment_block_tags.add("/*");
            comment_block_tags.add("*/");
        }else if(language.equals("html")){
            comment_block_tags.add("<!--");
            comment_block_tags.add("-->");
        }else{
        	Fragmental.error_var.add("Comment block for: "+language+" doesn't exist please add Fragment.java file");
        }
        return comment_block_tags;
    }
}
