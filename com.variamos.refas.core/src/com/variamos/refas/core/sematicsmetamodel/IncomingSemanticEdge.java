package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.List;

import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

public class IncomingSemanticEdge extends AbstractSemanticEdge {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2106426302856470039L;
	private SemanticGroupDependency groupDependency;

	public IncomingSemanticEdge(String identifier, SemanticGroupDependency groupDependency) {
		super(identifier, false, new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>());
		this.groupDependency = groupDependency;
	}

	public IncomingSemanticEdge(String identifier, boolean toSoftSemanticConcept,
			SemanticGroupDependency groupDependency	) {
		super(identifier, toSoftSemanticConcept, new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>());
		this.groupDependency = groupDependency;
	}
	public IncomingSemanticEdge(String identifier, boolean toSoftSemanticConcept,
			SemanticGroupDependency groupDependency,
			List<AbstractSemanticVertex> conflicts,
			List<AbstractSemanticVertex> alwaysAllows) {
		super(identifier, toSoftSemanticConcept, conflicts, alwaysAllows);
		this.groupDependency = groupDependency;
	}

	public SemanticGroupDependency getGroupDependency() {
		return groupDependency;
	}

	public void setGroupDependency(SemanticGroupDependency groupDependency) {
		this.groupDependency = groupDependency;
	}


}
