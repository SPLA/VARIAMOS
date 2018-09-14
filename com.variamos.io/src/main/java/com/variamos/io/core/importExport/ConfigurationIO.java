package com.variamos.io.core.importExport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.variamos.common.core.exceptions.TechnicalException;
import com.variamos.solver.model.SolverSolution;

/**
 *FIXME: Separate the functionalities which are utilities of those that contain business logic. This module only can
 *have dependencies with the common module and third-party libraries.
 *
 */
@Deprecated
public class ConfigurationIO {
	

	public static void saveMapToJSONFile(Map<String, Number> config, String fileAbsPath) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		FileWriter writer = new FileWriter(fileAbsPath);
		writer.write(gson.toJson(config));
		writer.close();
	}

	

	public static Map<String, Number> loadMapFromJSONFile(String fileAbsPath) throws FileNotFoundException {
		Gson gson = new GsonBuilder().create();

		FileReader fr = new FileReader(fileAbsPath);
		@SuppressWarnings("unchecked")
		Map<String, Number> out = gson.fromJson(fr, Map.class);
		try {
			fr.close();
		} catch (IOException e) {
			throw new TechnicalException(e);
		}
		return out;
	}

	
}
