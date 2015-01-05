package com.variamos.defectAnalyzer.model;

import java.util.ArrayList;
import java.util.List;

import com.variamos.defectAnalyzer.model.defects.Defect;

/**
 * Objeto que representa el defecto, las causas y los subconjuntos corrección
 * que podrían solucionar el defecto
 * 
 * @author LuFe
 * 
 */
public class Diagnosis {
	private Defect defect;
	private List<List<Dependency>> correctionSubsets;
	private List<List<Dependency>> causes;
	private boolean timeOverCorrections=Boolean.FALSE;
	private boolean timeOverCauses=Boolean.FALSE;
	private boolean correcMayoresUno=Boolean.FALSE;
	
	public Diagnosis() {
		super();
		causes = new ArrayList<List<Dependency>>();
		correctionSubsets=new ArrayList<List<Dependency>>();
	}

	/**
	 * @return the defect
	 */
	public Defect getDefect() {
		return defect;
	}

	/**
	 * @param defect
	 *            the defect to set
	 */
	public void setDefect(Defect defect) {
		this.defect = defect;
	}

	/**
	 * @return the causes
	 */
	public List<List<Dependency>> getCauses() {
		return causes;
	}

	/**
	 * @param causes
	 *            the causes to set
	 */
	public void setCauses(List<List<Dependency>> causes) {
		this.causes = causes;
	}

	/**
	 * @param correctionSubsets
	 *            the correctionSubsets to set
	 */
	public void setCorrectionSubsets(List<List<Dependency>> correctionSubsets) {
		this.correctionSubsets = correctionSubsets;
	}

	/**
	 * @return the correctionSubsets
	 */
	public List<List<Dependency>> getCorrectionSubsets() {
		return correctionSubsets;
	}

	public void printCorrections() {
		int i = 1;
		System.out.println("Defecto" + defect.getId());
		for (List<Dependency> mcsSet : correctionSubsets) {
			System.out.println("Corrección: "+ i);
			printDependenciesSet(mcsSet);
			i++;
		}
	}

	public void printCauses() {
		int i = 1;
		
		for (List<Dependency> musSet : causes) {
			System.out.println("Causa: "+ i);
			printDependenciesSet(musSet);
			i++;
		}
	}
	
	public void printDependenciesSet(List<Dependency> set){
		for (Dependency dependency : set) {
			System.out.print(dependency.getOriginalRelationShipText());
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

	/**
	 * @return the timeOverCorrections
	 */
	public boolean isTimeOverCorrections() {
		return timeOverCorrections;
	}

	/**
	 * @param timeOverCorrections the timeOverCorrections to set
	 */
	public void setTimeOverCorrections(boolean timeOverCorrections) {
		this.timeOverCorrections = timeOverCorrections;
	}

	/**
	 * @return the timeOverCauses
	 */
	public boolean isTimeOverCauses() {
		return timeOverCauses;
	}

	/**
	 * @param timeOverCauses the timeOverCauses to set
	 */
	public void setTimeOverCauses(boolean timeOverCauses) {
		this.timeOverCauses = timeOverCauses;
	}

	/**
	 * @return the correcMayoresUno
	 */
	public boolean isCorrecMayoresUno() {
		return correcMayoresUno;
	}

	/**
	 * @param correcMayoresUno the correcMayoresUno to set
	 */
	public void setCorrecMayoresUno(boolean correcMayoresUno) {
		this.correcMayoresUno = correcMayoresUno;
	}

}
