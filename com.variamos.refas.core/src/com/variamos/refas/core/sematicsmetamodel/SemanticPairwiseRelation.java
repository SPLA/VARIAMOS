package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.List;

import com.variamos.refas.core.types.LevelType;
import com.variamos.refas.core.types.SatisficingType;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticPairwiseRelType;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticPairwiseRelation;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticRelationType;

/**
 * A class to represent the edges with semantic back object. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 * @see com.cfm.productline.
 */
public class SemanticPairwiseRelation extends AbstractSemanticElement implements
		IntSemanticPairwiseRelation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7976788205587295216L;
	private List<IntSemanticRelationType> semanticRelationTypes;
	private List<AbstractSemanticVertex> directSemanticEdges;
	private boolean toSoftSemanticConcept;
	public static final String
	/**
	* 
	*/
	VAR_LEVEL = "level",
			/**
	 * 
	 */
			VAR_LEVELNAME = "Level",
			/**
	* 
	*/
			VAR_LEVELCLASS = LevelType.class.getCanonicalName(),
			/**
	 * 
	*/
			VAR_SATISFICINGTYPE = "satisficingType",
			/**
	 * 
	*/
			VAR_SATISFICINGTYPENAME = "Satisficing Level",
			/**
	* 
	*/
			VAR_SATISFICINGTYPECLASS = SatisficingType.class.getCanonicalName();


	public SemanticPairwiseRelation() {
		super(null);
	}


	public SemanticPairwiseRelation(String identifier, boolean toSoftSemanticConcept,
			List<IntSemanticRelationType> semanticRelationTypes) {
		super(identifier);
		this.semanticRelationTypes = semanticRelationTypes;
		this.toSoftSemanticConcept = toSoftSemanticConcept;
	}

	public List<IntSemanticRelationType> getSemanticRelationTypes() {
		return semanticRelationTypes;
	}

	public void setSemanticRelationTypes(
			List<IntSemanticRelationType> semanticRelationTypes) {
		this.semanticRelationTypes = semanticRelationTypes;
	}



}
