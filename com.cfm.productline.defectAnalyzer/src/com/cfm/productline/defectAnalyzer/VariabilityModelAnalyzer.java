package com.cfm.productline.defectAnalyzer;

import java.io.File;
import java.io.IOException;

import com.cfm.productline.dto.VMAnalyzerInDTO;
import com.cfm.productline.exceptions.TechnicalException;
import com.cfm.productline.model.enums.SolverEditorType;
import com.cfm.productline.util.SolverOperationsUtil;

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
			// Se instancia el engine de GNU prolog
			if (prologEditorType.equals(SolverEditorType.GNU_PROLOG)
					&& !SolverOperationsUtil.isStartPrologEngine()) {
				SolverOperationsUtil.starPrologEngine();
			}

		} catch (IOException e) {
			throw new TechnicalException(e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() throws Throwable {
		// Se finalizar el PrologEngine si el editor es GNU prolog
		if (prologEditorType!=null && prologEditorType.equals(SolverEditorType.GNU_PROLOG)
				&& SolverOperationsUtil.isStartPrologEngine()) {
			SolverOperationsUtil.stopProlog();
		}
		super.finalize();
	}
}