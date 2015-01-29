package com.variamos.defectAnalyzer.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		try {
			String modelName = "";
			String modelPath = "";
			String outputPath = "";
			System.out.print("Introduzca el nombre del modelo ");
			BufferedReader entrada = new BufferedReader(new InputStreamReader(
					System.in));
			//modelName = entrada.readLine();
			modelName="tesis";
			System.out.print("Introduzca la ruta completa del modelo ");
			//modelPath = entrada.readLine();
			modelPath="D://WebPortalTesis.sxfm";
			System.out
					.print("Introduzca la ruta completa del directorio de salida ");
			//outputPath = entrada.readLine();
			outputPath="D:/";
			// Analizar modelo: identificar defectos, causas y correcciones,
			// clasificar causas correcciones,exportar resultados
			DefectAnalyzerMain defectAnalyzer = new DefectAnalyzerMain();
			//defectAnalyzer.analyzeSplotFM(modelName, modelPath, outputPath);

		} catch (Exception e) {
			System.out.println("Ocurrio el siguiente error");
			e.printStackTrace();
		}

	}

}
