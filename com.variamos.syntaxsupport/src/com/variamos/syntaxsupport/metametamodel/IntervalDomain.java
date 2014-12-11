package com.variamos.syntaxsupport.metametamodel;


/**
 * document
 * Part of PhD work at University of Paris 1
 * @author  Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 		    
 * @version 1.1
 * @since	2014-11-24
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
