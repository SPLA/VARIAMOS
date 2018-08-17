package com.variamos.common.core.utilities;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtilsApache {
	/**Reads a file and returns the String readed. 
	 * @param cod - encoding of the file. 
	 * @param f - file to read.
	 * @return String readed from file. 
	 */
	public static String readFileToString(File f, String cod) throws IOException {
		return FileUtils.readFileToString(f,cod); 
	}
	
	/**Write a string into a file.
	 * @param cod - encoding of the file. 
	 * @param n - String to write in file
	 * @param f - file to read. 
	 */
	public static void writeStringToFile(File f, String n, String cod) throws IOException {
		FileUtils.writeStringToFile(f,n,cod); 
	}
	
	/**Deletes a directory.
	 * @param f - directory to delete. 
	 */
	public static void deleteDirectory(File f) throws IOException {
		FileUtils.deleteDirectory(f); 
	}
}
