/**
 * 
 */
package com.variamos.common.core.exceptions;

/**
 * Represents a controller logical exception
 * 
 * @author Luisa Fernanda Rincón Pérez <lufrinconpe@unal.edu.co>
 *
 */
public class FunctionalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public FunctionalException() {
		
	}

	/**
	 * @param arg0
	 */
	public FunctionalException(String arg0) {
		super(arg0);
		 
	}

	/**
	 * @param arg0
	 */
	public FunctionalException(Throwable arg0) {
		super(arg0);
		 
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public FunctionalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		 
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public FunctionalException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		 
	}

}
