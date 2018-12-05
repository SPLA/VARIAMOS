package com.variamos.reasoning.defectAnalyzer.model.diagnosis;

import com.variamos.reasoning.defectAnalyzer.model.transformation.IntDefectAnalyzerDomain;
import com.variamos.reasoning.defectAnalyzer.model.transformation.RangeDomainDefectAnalyzer;

public class VariabilityElementDefAna  implements
		Comparable<VariabilityElementDefAna>, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private IntDefectAnalyzerDomain domain;

	public VariabilityElementDefAna(String name) {
		super();
		this.name = transformName(name);
		// Por defecto se crea booleano
		domain = new RangeDomainDefectAnalyzer();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = transformName(name);
	}

	/**
	 * @return the domain
	 */
	public IntDefectAnalyzerDomain getDomain() {
		return domain;
	}

	/**
	 * @param domain
	 *            the domain to set
	 */
	public void setDomain(IntDefectAnalyzerDomain domain) {
		this.domain = domain;
	}

	/**
	 * // Se agrega a la lista de características, la actual (se pone sus //
	 * letras en mayúscula, se intercambian los espacios por _ y // se
	 * reemplazan los simbolos de + y - por Plus y Minus, // respectivamente)
	 * 
	 * @param name
	 * @return
	 */
	private String transformName(String name) {
		// Se pasa a mayúsculas la primera letra para garantizar
		// que sea interpretada como variable en los solvers por ejemplo en
		// Prolog
		String changedName = evaluateFirstCharacter(name.charAt(0))
				.toUpperCase()
				+ name.trim().substring(1).replaceAll(" ", "_")
						.replaceAll("\\-", "Minus").replaceAll("\\+", "Plus")
						.replaceAll("\\.", "dot").replaceAll("/", "");

		return changedName;
	}

	private String evaluateFirstCharacter(char caracterInicial) {
		if ((caracterInicial >= 65 && caracterInicial <= 90)
				|| (caracterInicial >= 97 && caracterInicial <= 122))
			return String.valueOf(caracterInicial).toUpperCase();
		return "N".concat(String.valueOf(caracterInicial));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		VariabilityElementDefAna other = (VariabilityElementDefAna) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(VariabilityElementDefAna other) {
		return name.compareTo(other.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
