package com.cfm.productline.dto;

import java.util.ArrayList;
import java.util.List;

import com.cfm.productline.model.diagnostic.Diagnostic;

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
