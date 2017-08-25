package com.variamos.common.core.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.variamos.common.core.exceptions.TechnicalException;

/**
 * Clase utilitaria para manejo de archivos y directorios
 * 
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
	 * 
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
			PrintWriter out = new PrintWriter(bw);
			out.println(constraintProgram);
			out.close();
		} catch (java.io.IOException ioex) {
			throw new TechnicalException(ioex);
		}
	}

	/**
	 * Write to disk an input File
	 * 
	 * @param constraintProgram
	 * @throws IOException
	 */
	public static String writePrologFile(File file, String constraintProgram)
			throws TechnicalException {

		// Se guarda la representación en el archivo temporal de prolog
		FileUtils.writeFile(file.getAbsolutePath(), constraintProgram);
		return file.getAbsolutePath();
		

	}
}
