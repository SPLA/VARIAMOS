package com.variamos.perspsupport.syntaxsupport;

/**
 * @author Juan Carlos Muñoz 2015
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class TemporalConfigAttribute extends ConfigurationAttribute {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -7430454253717334119L;

	 public TemporalConfigAttribute()
	 {
		 super();
	 }
	
	/**
	 * 
	 */
	 
	 public TemporalConfigAttribute(String name, String type, boolean affectProperties, String displayName, Object defaultValue)
	 {
		 super(name, type, affectProperties, displayName, defaultValue);
	 }
	 
	 public TemporalConfigAttribute(String name, String type,  boolean affectProperties, String displayName, String enumType, Object defaultValue)
	 {
		 super(name, type, affectProperties, displayName, enumType, defaultValue);
	 }
		
}
