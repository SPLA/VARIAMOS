package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent the outgoing allowed relations for a group dependency.
 * Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-01
 * @see com.cfm.productline.
 */
public class OutgoingSemanticEdge extends AbstractSemanticEdge {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6285686523477071286L;
	/**
	 * 
	 */
	private AbstractSemanticVertex semanticConcept;

	public OutgoingSemanticEdge(String identifier,
			AbstractSemanticVertex semanticConcept) {
		super(identifier, false, new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>());
		this.semanticConcept = semanticConcept;
	}

	public OutgoingSemanticEdge(String identifier,
			boolean toSoftSemanticConcept,
			AbstractSemanticVertex semanticConcept) {
		super(identifier, toSoftSemanticConcept,
				new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>());
		this.semanticConcept = semanticConcept;
	}

	public OutgoingSemanticEdge(String identifier,
			boolean toSoftSemanticConcept,
			AbstractSemanticVertex semanticConcept,
			List<AbstractSemanticVertex> conflicts,
			List<AbstractSemanticVertex> alwaysAllows) {
		super(identifier, toSoftSemanticConcept, conflicts, alwaysAllows);
		this.semanticConcept = semanticConcept;
	}

	public AbstractSemanticVertex getGroupDependency() {
		return semanticConcept;
	}

	public void setGroupDependency(AbstractSemanticVertex semanticConcept) {
		this.semanticConcept = semanticConcept;
	}

}
