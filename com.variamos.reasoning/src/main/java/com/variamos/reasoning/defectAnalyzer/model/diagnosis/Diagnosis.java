package com.variamos.reasoning.defectAnalyzer.model.diagnosis;

import java.util.ArrayList;
import java.util.List;

import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;

/**
 * Objeto que representa el defecto, las causas y los subconjuntos corrección
 * que podrían solucionar el defecto
 * 
 * @author LuFe
 * 
 */
public class Diagnosis {
	private Defect defect;
	private List<CauCos> causes;
	private List<CauCos> corrections;

	private Long correctionsProcessingTime;
	private Long causesProcessingTime;


	public Diagnosis() {
		causes= new ArrayList<CauCos>();
		corrections=  new ArrayList<CauCos>();
	}
	
	

	public Diagnosis(Defect defect) {
		this();
		this.defect = defect;
	}



	/**
	 * @return the defect
	 */
	public Defect getDefect() {
		return defect;
	}

	public void setDefect(Defect defect) {
		this.defect = defect;
	}

	public void printCorrections() {
		int i = 1;
		System.out.println("Defecto" + defect.getId());
		for (CauCos correction : corrections) {
			System.out.println("Corrección: " + i);
			printCorrectionSet(correction.getElements());
			i++;
		}
	}

	public void printCauses() {
		int i = 1;

		for (CauCos cause : causes) {
			System.out.println("Causa: " + i);
			printCorrectionSet(cause.getElements());
			i++;
		}
	}

	public void printCorrectionSet(List<IntBooleanExpression> expressios) {
		for (IntBooleanExpression expression : expressios) {
			System.out.print(expression.toString());
			System.out.print(" , ");
			System.out.println();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defect == null) ? 0 : defect.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diagnosis other = (Diagnosis) obj;
		if (defect == null) {
			if (other.defect != null)
				return false;
		} else if (!defect.equals(other.defect))
			return false;
		return true;
	}

	

	public void setCorrections(List<CauCos> corrections) {
		this.corrections = corrections;
	}

	public Long getCorrectionsProcessingTime() {
		return correctionsProcessingTime;
	}

	public void setCorrectionsProcessingTime(Long correctionsProcessingTime) {
		this.correctionsProcessingTime = correctionsProcessingTime;
	}

	public Long getCausesProcessingTime() {
		return causesProcessingTime;
	}

	public void setCausesProcessingTime(Long causesProcessingTime) {
		this.causesProcessingTime = causesProcessingTime;
	}

	public List<CauCos> getCauses() {
		return causes;
	}

	public List<CauCos> getCorrections() {
		return corrections;
	}



	public void setCauses(List<CauCos> causes) {
		this.causes = causes;
	}



	@Override
	public String toString() {
		return "Diagnosis [defect=" + defect + "]";
	}
}
