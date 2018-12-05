package com.variamos.reasoning.defectAnalyzer.model.transformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.common.model.enums.NotationType;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.VariabilityElementDefAna;

public class VariabilityModel {
	private String modelName;
	private NotationType notationType;
	private HlclProgram model;
	private Map<String, VariabilityElementDefAna> elements;
	
	private Map<Long, Dependency> dependencies;
	private String name;
	// Dependencias propias de la notaciï¿½n que se estï¿½ utilizando y que no
	// pueden ser modificables pq hacen parte de la semï¿½ntica del modelo de
	// variabilidad
	private Map<Long, Dependency> fixedDependencies;

	// Subconjunto de los elementos del modelo. Contiene solo los elementos
	// opcionales
	private Map<String, VariabilityElementDefAna> optionalVariabilityElements;

	// Dependencias de inclusiï¿½n o exclusion. Son la dependencias para las que
	// se buscan redundancias si el modelo es un feature model
	private Map<Long, Dependency> inclusionExclusionDependencies;

	// TODO borrar esto
	private List<String> domainStringList;

	// Estadisticas del modelo
	private Integer numbeOfFeatures;
	private Integer numberOfNonTrasversalDependencies;
	private Integer numberOfTraversalDependencies;
	private Integer percentageNonTraversalDependencies;
	private Integer percentageTraversalDependencies;
	private Integer numberOfDependencies;

	public VariabilityModel() {
		super();
		fixedDependencies = new HashMap<Long, Dependency>();
		optionalVariabilityElements = new HashMap<String, VariabilityElementDefAna>();
		numbeOfFeatures = numberOfNonTrasversalDependencies = numberOfTraversalDependencies = percentageNonTraversalDependencies = percentageTraversalDependencies =numberOfDependencies= 0;
	}

	public VariabilityModel(NotationType notationType) {
		this();
		this.notationType = notationType;
	}

	
	/**
	 * @return the notationType
	 */
	public NotationType getNotationType() {
		return notationType;
	}

	/**
	 * @param notationType
	 *            the notationType to set
	 */
	public void setNotationType(NotationType notationType) {
		this.notationType = notationType;
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
		result = prime * result
				+ ((modelName == null) ? 0 : modelName.hashCode());
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
		VariabilityModel other = (VariabilityModel) obj;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VariabilityModel [modelName=" + modelName + ", elements="
				+ elements + ", dependencies=" + dependencies + "]";
	}

	/**
	 * @return the elements
	 */
	public Map<String, VariabilityElementDefAna> getElements() {
		return elements;
	}

	/**
	 * @param elements
	 *            the elements to set
	 */
	public void setElements(Map<String, VariabilityElementDefAna> elements) {
		this.elements = elements;
	}

	/**
	 * @return the dependencies
	 */
	public Map<Long, Dependency> getDependencies() {
		return dependencies;
	}

	/**
	 * @param dependencies
	 *            the dependencies to set
	 */
	public void setDependencies(Map<Long, Dependency> dependencies) {
		this.dependencies = dependencies;
	}

	/**
	 * @return the fixedDependencies
	 */
	public Map<Long, Dependency> getFixedDependencies() {
		return fixedDependencies;
	}


	/**
	 * @param fixedDependencies
	 *            the fixedDependencies to set
	 */
	public void setFixedDependencies(Map<Long, Dependency> fixedDependencies) {
		this.fixedDependencies = fixedDependencies;
	}

	/**
	 * @return the optionalVariabilityElements
	 */
	public Map<String, VariabilityElementDefAna> getOptionalVariabilityElements() {
		return optionalVariabilityElements;
	}

	/**
	 * @param optionalVariabilityElements
	 *            the optionalVariabilityElements to set
	 */
	public void setOptionalVariabilityElements(
			Map<String, VariabilityElementDefAna> optionalVariabilityElements) {
		this.optionalVariabilityElements = optionalVariabilityElements;
	}

	/**
	 * @return the domainStringList
	 */
	public List<String> getDomainStringList() {
		return domainStringList;
	}

	/**
	 * @param domainStringList
	 *            the domainStringList to set
	 */
	public void setDomainStringList(List<String> domainStringList) {
		this.domainStringList = domainStringList;
	}

	/**
	 * @return the inclusionExclusionDependencies
	 */
	public Map<Long, Dependency> getInclusionExclusionDependencies() {
		return inclusionExclusionDependencies;
	}

	/**
	 * @param inclusionExclusionDependencies
	 *            the inclusionExclusionDependencies to set
	 */
	public void setInclusionExclusionDependencies(
			Map<Long, Dependency> inclusionExclusionDependencies) {
		this.inclusionExclusionDependencies = inclusionExclusionDependencies;
	}

	/**
	 * @return the numbeOfFeatures
	 */
	public Integer getNumbeOfFeatures() {
		return numbeOfFeatures;
	}

	/**
	 * @param numbeOfFeatures
	 *            the numbeOfFeatures to set
	 */
	public void setNumbeOfFeatures(Integer numbeOfFeatures) {
		this.numbeOfFeatures = numbeOfFeatures;
	}

	/**
	 * @return the numberOfNonTrasversalDependencies
	 */
	public Integer getNumberOfNonTrasversalDependencies() {
		return numberOfNonTrasversalDependencies;
	}

	/**
	 * @param numberOfNonTrasversalDependencies
	 *            the numberOfNonTrasversalDependencies to set
	 */
	public void setNumberOfNonTrasversalDependencies(
			Integer numberOfNonTrasversalDependencies) {
		this.numberOfNonTrasversalDependencies = numberOfNonTrasversalDependencies;
	}

	/**
	 * @return the numberOfTraversalDependencies
	 */
	public Integer getNumberOfTraversalDependencies() {
		return numberOfTraversalDependencies;
	}

	/**
	 * @param numberOfTraversalDependencies
	 *            the numberOfTraversalDependencies to set
	 */
	public void setNumberOfTraversalDependencies(
			Integer numberOfTraversalDependencies) {
		this.numberOfTraversalDependencies = numberOfTraversalDependencies;
	}

	/**
	 * @return the percentageNonTraversalDependencies
	 */
	public Integer getPercentageNonTraversalDependencies() {
		return percentageNonTraversalDependencies;
	}

	/**
	 * @param percentageNonTraversalDependencies
	 *            the percentageNonTraversalDependencies to set
	 */
	public void setPercentageNonTraversalDependencies(
			Integer percentageNonTraversalDependencies) {
		this.percentageNonTraversalDependencies = percentageNonTraversalDependencies;
	}

	/**
	 * @return the percentageTraversalDependencies
	 */
	public Integer getPercentageTraversalDependencies() {
		return percentageTraversalDependencies;
	}

	/**
	 * @param percentageTraversalDependencies
	 *            the percentageTraversalDependencies to set
	 */
	public void setPercentageTraversalDependencies(
			Integer percentageTraversalDependencies) {
		this.percentageTraversalDependencies = percentageTraversalDependencies;
	}

	/**
	 * @return the numberOfDependencies
	 */
	public Integer getNumberOfDependencies() {
		return numberOfDependencies;
	}

	/**
	 * @param numberOfDependencies the numberOfDependencies to set
	 */
	public void setNumberOfDependencies(Integer numberOfDependencies) {
		this.numberOfDependencies = numberOfDependencies;
	}

	public HlclProgram getModel() {
		return model;
	}

	public void setModel(HlclProgram model) {
		this.model = model;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
