package com.variamos.common.core.utilities;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.variamos.common.core.exceptions.TechnicalException;

/**
 * Utility class for handling directories and files
 * 
 * @author Luisa Rincon - lufe089@gmail.com
 * @version 1.1
 *
 */
public class FileUtils {
	
	/**
	 * Read files from a directory
	 * 
	 * @param directoryPath
	 * @return List of File objects
	 */
	public static List<File> readFileFromDirectory(String directoryPath) {
		File Dir = new File(directoryPath);
		List<File> fileList = new ArrayList<File>();
		File[] lista_Archivos = Dir.listFiles();
		for (int i = 0; i < lista_Archivos.length; i++) {
			if (lista_Archivos[i].isFile()) {
				fileList.add(lista_Archivos[i]);
			}

		}
		return fileList;

	}

	/**
	 * Read directories from a directory path
	 * 
	 * @param directoryPath
	 * @return List of File objects
	 */
	public static List<File> readDirectoryFromDirectory(String directoryPath) {
		File Dir = new File(directoryPath);
		List<File> fileList = new ArrayList<File>();
		File[] lista_Archivos = Dir.listFiles();
		for (int i = 0; i < lista_Archivos.length; i++) {
			if (lista_Archivos[i].isDirectory()) {
				fileList.add(lista_Archivos[i]);
			}

		}
		return fileList;

	}


	
	/**
	 * Write to the hard disk info received as parameter
	 * @param path
	 * @param content
	 */
	public static void writeFile(String path, String content) {
		try {

			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			out.println(content);
			out.close();
		} catch (java.io.IOException ioex) {
			throw new TechnicalException(ioex);
		}
	}

	/**
	 * Write to the hard disk info received as parameter
	 * @param file Java File object
	 * @param content to save
	 * @return absolute path where the file were saved
	 * @throws TechnicalException
	 */
	public static String writeFile(File file, String content)
			throws TechnicalException {

		// Se guarda la representaciï¿½n en el archivo temporal de prolog
		FileUtils.writeFile(file.getAbsolutePath(), content);
		return file.getAbsolutePath();
		

	}

}

