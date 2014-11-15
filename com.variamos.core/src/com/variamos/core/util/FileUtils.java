package com.variamos.core.util;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria para manejo de archivos y directorios
 * @author Luisa Rincon <lufe089@gmail.com>
 *
 */
public class FileUtils {
	/**
	 * Lee la lista de archivos de un directorio
	 * 
	 * @param directoryPath
	 * @return
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
	 * Lee los directorios de una lista de directorios
	 * @param directoryPath
	 * @return
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
	 * Recibe una cadena y la escribe en un archivo
	 * 
	 * @param constraintProgram
	 */
	public static void writeFile(String path, String constraintProgram) {
		try {
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println(constraintProgram);
			salida.close();
		} catch (java.io.IOException ioex) {
			System.out.println("se presento el error: " + ioex.toString());
		}
	}

}
