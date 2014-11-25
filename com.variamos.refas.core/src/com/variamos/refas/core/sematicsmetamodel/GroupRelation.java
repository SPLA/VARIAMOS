package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public class GroupRelation extends AbstractRelation {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6309224856276191013L;
	private boolean exclusive;
	private Dependency throughDep;
	private List<AbstractSemanticConcept> alwaysAllows;
	private SemanticAttribute[] ranges = new SemanticAttribute[2];
	private SemanticAttribute cardinality;

	public GroupRelation(boolean exclusive, AbstractSemanticConcept toConcept,
			Dependency throughDep) {
		this(exclusive, toConcept, throughDep,
				new ArrayList<AbstractSemanticConcept>(), null, null);
	}

	public GroupRelation(boolean exclusive, AbstractSemanticConcept toConcept,
			Dependency throughDep, SemanticAttribute semanticAttribute1,
			SemanticAttribute semanticAttribute2) {
		this(exclusive, toConcept, throughDep,
				new ArrayList<AbstractSemanticConcept>(), semanticAttribute1,
				semanticAttribute2);

	}

	public GroupRelation(boolean exclusive, AbstractSemanticConcept toConcept,
			Dependency throughDep, List<AbstractSemanticConcept> conflicts) {
		this(exclusive, toConcept, throughDep, conflicts,
				new ArrayList<AbstractSemanticConcept>(), null, null);
	}

	public GroupRelation(boolean exclusive, AbstractSemanticConcept toConcept,
			Dependency throughDep, List<AbstractSemanticConcept> conflicts,
			List<AbstractSemanticConcept> alwaysAllows) {
		this(exclusive, toConcept, throughDep, conflicts, alwaysAllows, null,
				null);
	}

	public GroupRelation(boolean exclusive, AbstractSemanticConcept toConcept,
			Dependency throughDep, List<AbstractSemanticConcept> conflicts,
			List<AbstractSemanticConcept> alwaysAllows,
			SemanticAttribute semanticAttribute1,
			SemanticAttribute semanticAttribute2) {
		super(toConcept, conflicts);
		this.exclusive = exclusive;
		this.throughDep = throughDep;
		this.ranges[0] = semanticAttribute1;
		this.ranges[1] = semanticAttribute2;
		//ArrayList<String> cardinality = new ArrayList<String>();
		//new SemanticEnumeration("CardinalityType", "String", cardinality);
		this.cardinality = new SemanticAttribute("cardinality","CardinalityType",true);
		this.alwaysAllows = alwaysAllows;
	}

	public GroupRelation(boolean exclusive, AbstractSemanticConcept toConcept,
			Dependency throughDep, List<AbstractSemanticConcept> alwaysAllows,
			SemanticAttribute semanticAttribute1,
			SemanticAttribute semanticAttribute2) {
		super(toConcept);
		this.exclusive = exclusive;
		this.throughDep = throughDep;
		this.ranges[0] = semanticAttribute1;
		this.ranges[1] = semanticAttribute2;
		this.cardinality = new SemanticAttribute("cardinality","CandinalityType",true);
		this.alwaysAllows = alwaysAllows;
	}

	public String toString() {
		String alwaysAllowsOut = "";
		for (int i = 0; i< alwaysAllows.size();i++)
			alwaysAllowsOut += alwaysAllows.get(i).getName()+", ";
		return " Exc:" + exclusive + "throughDep: " + throughDep.getType() + (ranges[0]==null?"":("cardin: " 
	+ ranges[0].getName() + " "+ ranges[1].getName())) + " cardType: " + cardinality.getName()
	+ " " + alwaysAllowsOut;
	}
}
