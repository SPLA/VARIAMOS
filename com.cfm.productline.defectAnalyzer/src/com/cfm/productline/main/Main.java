package com.cfm.productline.main;

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
			modelName = entrada.readLine();
			System.out.print("Introduzca la ruta completa del modelo ");
			modelPath = entrada.readLine();
			System.out
					.print("Introduzca la ruta completa del directorio de salida ");
			outputPath = entrada.readLine();
			// Analizar modelo: identificar defectos, causas y correcciones,
			// clasificar causas correcciones,exportar resultados
			MainDefectAnalyzer defectAnalyzer = new MainDefectAnalyzer();
			defectAnalyzer.analyzeSplotFM(modelName, modelPath, outputPath);

		} catch (Exception e) {
			System.out.println("Ocurrio el siguiente error");
			e.printStackTrace();
		}

	}

}
