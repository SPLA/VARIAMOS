package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class SimulationAttribute extends AbstractAttribute {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -7430454253717334119L;

	/**
	 * 
	 */
	 
	 public SimulationAttribute(String name, String type, Object defaultValue)
	 {
		 super(name, type, defaultValue);
	 }
	 
	 public SimulationAttribute(String name, String type, String enumType, Object defaultValue)
	 {
		 super(name, type, enumType, defaultValue);
	 }
		
}
