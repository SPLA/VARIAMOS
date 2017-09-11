package com.variamos.defectAnalyzer.defectAnalyzer;

import java.io.File;
import java.io.IOException;

import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.TechnicalException;
import com.variamos.defectAnalyzer.dto.VMAnalyzerInDTO;
import com.variamos.defectAnalyzer.util.SolverOperationsUtil;

public class VariabilityModelAnalyzer {

	protected SolverEditorType prologEditorType;
	protected String prologTempPath;

	public VariabilityModelAnalyzer(VMAnalyzerInDTO inDTO) {
		// Create a temporary prolog file
		File f = null;
		try {
			f = File.createTempFile("tmp", ".pl");
			f.deleteOnExit();
			prologTempPath = f.getAbsolutePath();

			// Se ajusta la ruta para que no genere problemas con SWI prolog se
			// ajusta la ruta
			prologTempPath = prologTempPath.replace("\\", "/");
			
			prologEditorType = inDTO.getPrologEditorType();


		} catch (IOException e) {
			throw new TechnicalException(e);
		}
	}
	
	
}