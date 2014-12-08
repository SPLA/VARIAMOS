package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.List;

import com.variamos.syntaxsupport.semanticinterface.IntDirectEdgeType;
import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticEdge;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public class DirectSemanticEdge extends AbstractSemanticEdge implements
		IntDirectSemanticEdge { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 7976788205587295216L;
	private boolean exclusive;
	private List<IntDirectEdgeType> semanticRelationTypes;
	private List<AbstractSemanticVertex> directSemanticEdges;

	public DirectSemanticEdge() { 
		super();
	}

	public DirectSemanticEdge(String identifier, Boolean toSoftSemanticConcept,
			boolean exclusive, List<IntDirectEdgeType> semanticRelationTypes) {
		this(identifier, toSoftSemanticConcept,
				new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>(), exclusive,
				new ArrayList<AbstractSemanticVertex>(), semanticRelationTypes);
	}

	public DirectSemanticEdge(String identifier, boolean toSoftSemanticConcept,
			boolean exclusive, List<IntDirectEdgeType> semanticRelationTypes) {
		this(identifier, toSoftSemanticConcept,
				new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>(), exclusive,
				new ArrayList<AbstractSemanticVertex>(), semanticRelationTypes);
	}

	public DirectSemanticEdge(String identifier, boolean toSoftSemanticConcept,
			List<AbstractSemanticVertex> conflicts, boolean exclusive,
			List<IntDirectEdgeType> semanticRelationTypes) {
		this(identifier, toSoftSemanticConcept, conflicts,
				new ArrayList<AbstractSemanticVertex>(), exclusive,
				new ArrayList<AbstractSemanticVertex>(), semanticRelationTypes);
	}

	public DirectSemanticEdge(String identifier, boolean toSoftSemanticConcept,
			boolean exclusive,
			List<AbstractSemanticVertex> semanticRelationEdges,
			List<IntDirectEdgeType> semanticRelationTypes) {
		this(identifier, toSoftSemanticConcept,
				new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>(), exclusive,
				semanticRelationEdges, semanticRelationTypes);
	}

	public DirectSemanticEdge(String identifier, boolean toSoftSemanticConcept,
			List<AbstractSemanticVertex> conflicts,
			List<AbstractSemanticVertex> alwaysAllows, boolean exclusive,
			List<AbstractSemanticVertex> directSemanticEdges,
			List<IntDirectEdgeType> semanticRelationTypes) {
		super(identifier, toSoftSemanticConcept, conflicts, alwaysAllows);
		this.exclusive = exclusive;
		this.semanticRelationTypes = semanticRelationTypes;
		this.directSemanticEdges = directSemanticEdges;
	}

	public DirectSemanticEdge(String string, boolean b,
			List<IntDirectEdgeType> requires_conflictsDirectRelation,
			List<AbstractSemanticVertex> semanticVertexs, boolean c) {
		// TODO Auto-generated constructor stub
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}

	public List<IntDirectEdgeType> getSemanticRelationTypes() {
		return semanticRelationTypes;
	}

	public void setSemanticRelationTypes(
			List<IntDirectEdgeType> semanticRelationTypes) {
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
