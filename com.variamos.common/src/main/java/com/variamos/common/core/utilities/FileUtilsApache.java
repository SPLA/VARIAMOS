package com.variamos.common.core.utilities;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtilsApache {
	
	public static String readFileToString(File f, String cod) throws IOException {
		return FileUtils.readFileToString(f,cod); 
	}
	
	public static void writeStringToFile(File f, String n, String cod) throws IOException {
		FileUtils.writeStringToFile(f,n,cod); 
	}
	
	public static void deleteDirectory(File f) throws IOException {
		FileUtils.deleteDirectory(f); 
	}
}
