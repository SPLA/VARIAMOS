package com.variamos.perspsupport.opers;

/**
 * A class to represent the Semantics of Variables. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-02
 * @see com.cfm.productline.
 */
public class OpersVariable extends OpersAbstractVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5538738414024566452L;

	public OpersVariable(String name) {
		super(null, name, true);
	}

	public String toString() {

		return " VAR: " + super.toString();
	}
}
