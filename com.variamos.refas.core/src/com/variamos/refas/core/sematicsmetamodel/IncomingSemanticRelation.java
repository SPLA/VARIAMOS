package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.List;

import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

public class IncomingSemanticRelation extends AbstractSemanticRelation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2106426302856470039L;
	private SemanticGroupDependency groupDependency;

	public IncomingSemanticRelation(SemanticGroupDependency groupDependency) {
		super(false, new ArrayList<AbstractSemanticConcept>(),
				new ArrayList<AbstractSemanticConcept>());
		this.groupDependency = groupDependency;
	}

	public IncomingSemanticRelation(boolean toSoftSemanticConcept,
			SemanticGroupDependency groupDependency	) {
		super(toSoftSemanticConcept, new ArrayList<AbstractSemanticConcept>(),
				new ArrayList<AbstractSemanticConcept>());
		this.groupDependency = groupDependency;
	}
	public IncomingSemanticRelation(boolean toSoftSemanticConcept,
			SemanticGroupDependency groupDependency,
			List<AbstractSemanticConcept> conflicts,
			List<AbstractSemanticConcept> alwaysAllows) {
		super(toSoftSemanticConcept, conflicts, alwaysAllows);
		this.groupDependency = groupDependency;
	}

	public SemanticGroupDependency getGroupDependency() {
		return groupDependency;
	}

	public void setGroupDependency(SemanticGroupDependency groupDependency) {
		this.groupDependency = groupDependency;
	}


}
