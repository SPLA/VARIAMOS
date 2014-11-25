package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.variamos.syntaxsupport.metametamodel.MetaView;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class InstView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7286605416934464023L;
	private String identifier;
	private MetaView metaView;
	private List<InstConcept> instConcepts;
	
	
	public InstView(String identifier, MetaView metaView) {
		this( identifier, metaView, new ArrayList<InstConcept>());
	}
	
	public InstView(String identifier, MetaView metaView,
			List<InstConcept> instConcepts) {
		super();
		this.identifier = identifier;
		this.metaView = metaView;
		this.instConcepts = instConcepts;
	}


	public String getIdentifier() {
		return identifier;
	}


	public MetaView getMetaView() {
		return metaView;
	}


	public List<InstConcept> getInstConcepts() {
		return instConcepts;
	}
	
	public void addInstConcept(InstConcept instConcept)
	{
		instConcepts.add(instConcept);
	}
}
