package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.List;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticPairwiseRelType;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticPairwiseRelation;

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
public class SemanticPairwiseRelation extends AbstractSemanticEdge implements
		IntSemanticPairwiseRelation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7976788205587295216L;
	private boolean exclusive;
	private List<IntSemanticPairwiseRelType> semanticRelationTypes;
	private List<AbstractSemanticVertex> directSemanticEdges;

	public SemanticPairwiseRelation() {
		super();
	}

	public SemanticPairwiseRelation(String identifier, Boolean toSoftSemanticConcept,
			boolean exclusive, List<IntSemanticPairwiseRelType> semanticRelationTypes) {
		this(identifier, toSoftSemanticConcept,
				new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>(), exclusive,
				new ArrayList<AbstractSemanticVertex>(), semanticRelationTypes);
	}

	public SemanticPairwiseRelation(String identifier, boolean toSoftSemanticConcept,
			boolean exclusive, List<IntSemanticPairwiseRelType> semanticRelationTypes) {
		this(identifier, toSoftSemanticConcept,
				new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>(), exclusive,
				new ArrayList<AbstractSemanticVertex>(), semanticRelationTypes);
	}

	public SemanticPairwiseRelation(String identifier, boolean toSoftSemanticConcept,
			List<AbstractSemanticVertex> conflicts, boolean exclusive,
			List<IntSemanticPairwiseRelType> semanticRelationTypes) {
		this(identifier, toSoftSemanticConcept, conflicts,
				new ArrayList<AbstractSemanticVertex>(), exclusive,
				new ArrayList<AbstractSemanticVertex>(), semanticRelationTypes);
	}

	public SemanticPairwiseRelation(String identifier, boolean toSoftSemanticConcept,
			boolean exclusive,
			List<AbstractSemanticVertex> semanticRelationEdges,
			List<IntSemanticPairwiseRelType> semanticRelationTypes) {
		this(identifier, toSoftSemanticConcept,
				new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>(), exclusive,
				semanticRelationEdges, semanticRelationTypes);
	}

	public SemanticPairwiseRelation(String identifier, boolean toSoftSemanticConcept,
			List<AbstractSemanticVertex> conflicts,
			List<AbstractSemanticVertex> alwaysAllows, boolean exclusive,
			List<AbstractSemanticVertex> directSemanticEdges,
			List<IntSemanticPairwiseRelType> semanticRelationTypes) {
		super(identifier, toSoftSemanticConcept, conflicts, alwaysAllows);
		this.exclusive = exclusive;
		this.semanticRelationTypes = semanticRelationTypes;
		this.directSemanticEdges = directSemanticEdges;
	}

	public SemanticPairwiseRelation(String string, boolean b,
			List<IntSemanticPairwiseRelType> requires_conflictsDirectRelation,
			List<AbstractSemanticVertex> semanticVertexs, boolean c) {
		// TODO Auto-generated constructor stub
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}

	public List<IntSemanticPairwiseRelType> getSemanticRelationTypes() {
		return semanticRelationTypes;
	}

	public void setSemanticRelationTypes(
			List<IntSemanticPairwiseRelType> semanticRelationTypes) {
		this.semanticRelationTypes = semanticRelationTypes;
	}

	public List<AbstractSemanticVertex> getDirectSemanticEdges() {
		return directSemanticEdges;
	}

	public void setDirectSemanticEdges(
			List<AbstractSemanticVertex> directSemanticEdges) {
		this.directSemanticEdges = directSemanticEdges;
	}

}
