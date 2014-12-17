package com.variamos.defectAnalyzer.dto;

import java.util.ArrayList;
import java.util.List;

import com.variamos.defectAnalyzer.diagnostic.Diagnostic;

/**
 * Entrega los resultados del análisis del defecto causas y correcciones
 * 
 * @author LuFe
 * 
 */
public class VMCauseAnalyzerOutDTO  {

	
	
	private List<Diagnostic> allDiagnostics = new ArrayList<Diagnostic>();

	

	/**
	 * @return the allDiagnostics
	 */
	public List<Diagnostic> getAllDiagnostics() {
		return allDiagnostics;
	}

	/**
	 * @param allDiagnostics the allDiagnostics to set
	 */
	public void setAllDiagnostics(List<Diagnostic> allDiagnostics) {
		this.allDiagnostics = allDiagnostics;
	}

}
