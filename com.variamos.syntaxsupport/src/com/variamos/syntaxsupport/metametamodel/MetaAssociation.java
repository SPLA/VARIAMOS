package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;

import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticRelation;


/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class MetaAssociation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6241085598671687248L;
	private String identifier;
	private int iniLowCardinality;
	private int iniHighCardinality;
	private int endLowCardinality;
	private int endHighCardinality;
	private String iniDescription;
	private String endDescription;
	private boolean arrowDirection;
	private TypeOfLine typeOfLine;
	private MetaElement origin;
	private MetaElement destination;
	
		
	public MetaAssociation() {
		this.iniLowCardinality = 1;
		this.iniHighCardinality = 1;
		this.endLowCardinality = 1;
		this.endHighCardinality = 1;
		this.iniDescription = "";
		this.endDescription = "";
		this.arrowDirection = false;
		this.typeOfLine = TypeOfLine.solid;
	}
	
	public MetaAssociation(MetaElement origin, MetaElement destination) {
		this.iniLowCardinality = 1;
		this.iniHighCardinality = 1;
		this.endLowCardinality = 1;
		this.endHighCardinality = 1;
		this.iniDescription = "";
		this.endDescription = "";
		this.arrowDirection = false;
		this.typeOfLine = TypeOfLine.solid;
		this.origin = origin;
		this.destination = destination;
	}

	public MetaAssociation (int iniLowCardinality,
			int iniHighCardinality, int endLowCardinality,
			int endHighCardinality, String iniDescription,
			String endDescription, boolean arrowDirection, TypeOfLine typeOfLine,
			MetaElement origin, MetaElement destination) {
		this.iniLowCardinality = iniLowCardinality;
		this.iniHighCardinality = iniHighCardinality;
		this.endLowCardinality = endLowCardinality;
		this.endHighCardinality = endHighCardinality;
		this.iniDescription = iniDescription;
		this.endDescription = endDescription;
		this.arrowDirection = arrowDirection;
		this.typeOfLine = typeOfLine;
		this.origin = origin;
		this.destination = destination;
	}
	
	public void setOrigin(MetaElement origin) {
		this.origin = origin;
	}
	public void setDestination(MetaElement destination) {
		this.destination = destination;
	}
	
	public MetaElement getOrigin() {
		return origin;
	}
	public MetaElement getDestination() {
		return destination;
	}
	

	public String getIdentifier() {
		return identifier;
	}	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setIniLowCardinality(int iniLowCardinality) {
		this.iniLowCardinality = iniLowCardinality;
	}

	public void setIniHighCardinality(int iniHighCardinality) {
		this.iniHighCardinality = iniHighCardinality;
	}

	public void setEndLowCardinality(int endLowCardinality) {
		this.endLowCardinality = endLowCardinality;
	}

	public void setEndHighCardinality(int endHighCardinality) {
		this.endHighCardinality = endHighCardinality;
	}

	public void setIniDescription(String iniDescription) {
		this.iniDescription = iniDescription;
	}

	public void setEndDescription(String endDescription) {
		this.endDescription = endDescription;
	}

	public void setArrowDirection(boolean arrowDirection) {
		this.arrowDirection = arrowDirection;
	}

	public void setTypeOfLine(TypeOfLine typeOfLine) {
		this.typeOfLine = typeOfLine;
	}

	public int getIniLowCardinality() {
		return iniLowCardinality;
	}
	public int getIniHighCardinality() {
		return iniHighCardinality;
	}
	public int getEndLowCardinality() {
		return endLowCardinality;
	}
	public int getEndHighCardinality() {
		return endHighCardinality;
	}
	public String getIniDescription() {
		return iniDescription;
	}
	public String getEndDescription() {
		return endDescription;
	}
	public boolean isArrowDirection() {
		return arrowDirection;
	}
	public TypeOfLine getTypeOfLine() {
		return typeOfLine;
	}
	public String toString() {
		return "MetaGroupRelation [metaConcept=" + origin + ", dependency=" + destination + "]";
	}

}
