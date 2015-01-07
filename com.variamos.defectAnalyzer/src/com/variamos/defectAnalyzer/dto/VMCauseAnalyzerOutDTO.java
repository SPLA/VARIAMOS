package com.variamos.defectAnalyzer.dto;

import java.util.ArrayList;
import java.util.List;

import com.variamos.defectAnalyzer.model.Diagnosis;

/**
 * Entrega los resultados del análisis del defecto causas y correcciones
 * 
 * @author LuFe
 * 
 */
public class VMCauseAnalyzerOutDTO  {

	
	
	private List<Diagnosis> allDiagnostics = new ArrayList<Diagnosis>();

	

	/**
	 * @return the allDiagnostics
	 */
	public List<Diagnosis> getAllDiagnostics() {
		return allDiagnostics;
	}

	/**
	 * @param allDiagnostics the allDiagnostics to set
	 */
	public void setAllDiagnostics(List<Diagnosis> allDiagnostics) {
		this.allDiagnostics = allDiagnostics;
	}

}
