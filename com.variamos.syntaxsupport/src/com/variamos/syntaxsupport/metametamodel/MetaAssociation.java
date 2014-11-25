package com.variamos.syntaxsupport.metametamodel;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticDirectRelation;


/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class MetaAssociation extends MetaDirectRelation{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6241085598671687248L;
	private int iniLowCardinality;
	private int iniHighCardinality;
	private int endLowCardinality;
	public MetaAssociation(IntSemanticDirectRelation semanticRelation,
			MetaElement origin, MetaElement destination) {
		super(semanticRelation, origin, destination);
		// TODO Auto-generated constructor stub
	}

	public MetaAssociation(IntSemanticDirectRelation semanticRelation,
			MetaElement origin, MetaElement destination, int iniLowCardinality,
			int iniHighCardinality, int endLowCardinality,
			int endHighCardinality, String iniDescription,
			String endDescription, boolean arrowDirection, TypeOfLine typeOfLine) {
		super(semanticRelation, origin, destination);
		this.iniLowCardinality = iniLowCardinality;
		this.iniHighCardinality = iniHighCardinality;
		this.endLowCardinality = endLowCardinality;
		this.endHighCardinality = endHighCardinality;
		this.iniDescription = iniDescription;
		this.endDescription = endDescription;
		this.arrowDirection = arrowDirection;
		this.typeOfLine = typeOfLine;
	}
	private int endHighCardinality;
	private String iniDescription;
	private String endDescription;
	private boolean arrowDirection;
	private TypeOfLine typeOfLine;
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
}
