package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticDirectRelation;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public abstract class MetaDirectRelation implements Serializable {
	public void setSemanticRelation(IntSemanticDirectRelation semanticRelation) {
		this.semanticRelation = semanticRelation;
	}
	public void setOrigin(MetaElement origin) {
		this.origin = origin;
	}
	public void setDestination(MetaElement destination) {
		this.destination = destination;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2665541567934067387L;
	private IntSemanticDirectRelation semanticRelation;
	private MetaElement origin;
	
	public MetaDirectRelation()
	{
		
	}
	
	public MetaDirectRelation(IntSemanticDirectRelation semanticRelation,
			MetaElement origin, MetaElement destination) {
		super();
		this.semanticRelation = semanticRelation;
		this.origin = origin;
		this.destination = destination;
	}
	private MetaElement destination;
	public IntSemanticDirectRelation getSemanticRelation() {
		return semanticRelation;
	}
	public MetaElement getOrigin() {
		return origin;
	}
	public MetaElement getDestination() {
		return destination;
	}
	
}
