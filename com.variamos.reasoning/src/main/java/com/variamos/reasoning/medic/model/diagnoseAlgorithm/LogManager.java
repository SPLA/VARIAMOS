package com.variamos.reasoning.medic.model.diagnoseAlgorithm;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;

public class LogManager {
	public static final String LOGNAME="logFiles/ExecutionLog";
	public static final String LOGEXT=".log";
	private BufferedWriter out;
	public String problemPath;
	public String problemName;
	
	public LogManager(String path, String name){
		problemPath= path;
		problemName=name;
		
	}
	
	
	public void initLog(){
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 String time =timestamp.getTime()+"";
		String fileName= problemPath+problemName.substring(0, (problemName.length() -3))+LOGEXT;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
			out.write("Log File : " + timestamp+ "File name " + fileName+ " \n");
		} catch (Exception e) {
			// TODO Auto-generated catch blo
			
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * @param sentences is a string with the sentences in prolog 
	 */
	public void writeInFile(String sentences){
		try {
			out.write(sentences);
			//System.out.println(sentences);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void closeLog(){
	try{
		out.flush();
		out.close();
	}catch (Exception e){
		e.printStackTrace();
	}
	}

	
	

}
