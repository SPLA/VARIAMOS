package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.List;

import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

public class OutgoingSemanticEdge extends AbstractSemanticEdge {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6285686523477071286L;
	/**
	 * 
	 */
	private AbstractSemanticVertex semanticConcept;

	public OutgoingSemanticEdge(String identifier, AbstractSemanticVertex semanticConcept) {
		super(identifier, false, new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>());
		this.semanticConcept = semanticConcept;
	}

	public OutgoingSemanticEdge(String identifier, boolean toSoftSemanticConcept,
			AbstractSemanticVertex semanticConcept	) {
		super(identifier, toSoftSemanticConcept, new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>());
		this.semanticConcept = semanticConcept;
	}
	public OutgoingSemanticEdge(String identifier, boolean toSoftSemanticConcept,
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
