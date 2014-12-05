package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.List;

import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

public class OutgoingSemanticRelation extends AbstractSemanticRelation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6285686523477071286L;
	/**
	 * 
	 */
	private AbstractSemanticConcept semanticConcept;

	public OutgoingSemanticRelation(AbstractSemanticConcept semanticConcept) {
		super(false, new ArrayList<AbstractSemanticConcept>(),
				new ArrayList<AbstractSemanticConcept>());
		this.semanticConcept = semanticConcept;
	}

	public OutgoingSemanticRelation(boolean toSoftSemanticConcept,
			AbstractSemanticConcept semanticConcept	) {
		super(toSoftSemanticConcept, new ArrayList<AbstractSemanticConcept>(),
				new ArrayList<AbstractSemanticConcept>());
		this.semanticConcept = semanticConcept;
	}
	public OutgoingSemanticRelation(boolean toSoftSemanticConcept,
			AbstractSemanticConcept semanticConcept,			
			List<AbstractSemanticConcept> conflicts,
			List<AbstractSemanticConcept> alwaysAllows) {
		super(toSoftSemanticConcept, conflicts, alwaysAllows);
		this.semanticConcept = semanticConcept;
	}

	public AbstractSemanticConcept getGroupDependency() {
		return semanticConcept;
	}

	public void setGroupDependency(AbstractSemanticConcept semanticConcept) {
		this.semanticConcept = semanticConcept;
	}


}
