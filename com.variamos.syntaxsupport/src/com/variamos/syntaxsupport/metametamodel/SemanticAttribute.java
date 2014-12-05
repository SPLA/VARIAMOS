package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class SemanticAttribute extends AbstractAttribute {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 2582154145921483304L;
 
	 public SemanticAttribute(String name, String type, Object defaultValue)
	 {
		 super(name, type, defaultValue);
	 }

	 public SemanticAttribute(String name, String type, String enumType, Object defaultValue)
	 {
		 super(name, type, enumType, defaultValue);
	 }
	 public SemanticAttribute(String name, String type, Object defaultValue, String hint)
	 {
		 super(name, type, defaultValue, hint);
	 }

	 public SemanticAttribute(String name, String type, String enumType, Object defaultValue, String hint)
	 {
		 super(name, type, enumType, defaultValue, hint);
	 }

	 public SemanticAttribute(String name, String type, String enumType, String object, Object defaultValue, String hint)
	 {
		 super(name, type, enumType, object, defaultValue, hint);
	 }

}
