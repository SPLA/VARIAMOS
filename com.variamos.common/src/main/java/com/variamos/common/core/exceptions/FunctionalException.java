/**
 * 
 */
package com.variamos.common.core.exceptions;

/**
 * Represents a logical exception from business logic functionalities
 * 
 * @author Luisa Rincon - lufe089@gmail.com
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
	 * @param arg0 string text
	 */
	public FunctionalException(String arg0) {
		super(arg0);
		 
	}

	
	public FunctionalException(Throwable arg0) {
		super(arg0);
		 
	}

	
	public FunctionalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		 
	}

	
	public FunctionalException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		 
	}

}
