package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.variamos.syntaxsupport.semanticinterface.IntDirectRelationType;
import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticRelation;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class MetaDirectRelation extends MetaAssociation {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2665541567934067387L;
	private IntDirectSemanticRelation semanticRelation;
	private List<IntDirectRelationType> semanticTypes;

	
	public void setSemanticRelation(IntDirectSemanticRelation semanticRelation) {
		this.semanticRelation = semanticRelation;
	}

	
	public MetaDirectRelation()
	{
		semanticTypes = new ArrayList<IntDirectRelationType>();
		
	}
	
	public MetaDirectRelation(IntDirectSemanticRelation semanticRelation,
			MetaElement origin, MetaElement destination) {
		super(origin, destination);
		this.semanticRelation = semanticRelation;
		semanticTypes = new ArrayList<IntDirectRelationType>();

	}
	
	public List<IntDirectRelationType> getSemanticTypes() {
		return semanticTypes;
	}


	public void setSemanticTypes(List<IntDirectRelationType> semanticTypes) {
		this.semanticTypes = semanticTypes;
	}

	public boolean addSemanticType(IntDirectRelationType semanticType) {
		if(semanticRelation.getSemanticRelationTypes().contains(semanticType))
		{
			this.semanticTypes.add(semanticType);
			return true;
		}
		return false;
	}

	public IntDirectSemanticRelation getSemanticRelation() {
		return semanticRelation;
	}

}
