package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;

/**
 * document
 * Part of PhD work at University of Paris 1
 * @author  Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 		    
 * @version 1.1
 * @since	2014-11-24
 * @see 	com.variamos.syntaxsupport.metametamodel.AbtractAttribute
 */
public class Domain implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2159691019934108020L;
	private int size;

	public int getSize() {
		return size;
	}
}
