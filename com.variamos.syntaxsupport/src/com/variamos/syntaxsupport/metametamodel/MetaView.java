package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class MetaView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3876870066100742969L;
	private String shortName;
	private String name;
	private String paletteName;
	private int index;
	private List<MetaConcept> concepts;
	
	public MetaView( String shortName, String name, String paletteName, int index)
	{
		 this.shortName =shortName;
		 this.name = name;
		 this.paletteName =paletteName;
		 this.index = index;
		 this.concepts = new ArrayList<MetaConcept>();
	}

	public void addConcept(MetaConcept sTopGoal) {
		concepts.add(sTopGoal);
		
	}

	public String getShortName() {
		return shortName;
	}

	public String getName() {
		return name;
	}

	public String getPaletteName() {
		return paletteName;
	}

	public int getIndex() {
		return index;
	}

	public List<MetaConcept> getConcepts() {
		return concepts;
	}
	
}
