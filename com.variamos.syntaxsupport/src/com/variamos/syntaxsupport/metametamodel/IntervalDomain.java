package com.variamos.syntaxsupport.metametamodel;


/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class IntervalDomain extends Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1645145411048599254L;
	private int lowerValue;
	private int upperValue;
	
	public int getLowerValue() {
		return lowerValue;
	}
	public int getUpperValue() {
		return upperValue;
	}

}
