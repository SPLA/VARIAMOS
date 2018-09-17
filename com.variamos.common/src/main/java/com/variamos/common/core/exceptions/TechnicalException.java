/**
 * 
 */
package com.variamos.common.core.exceptions;

import java.util.Arrays;

/**
 * Represents a controlled technical exception
 * @author Luisa Rincon - lufe089@gmail.com
 *
 */
public class TechnicalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

		
	public TechnicalException() {
		
	}

	/**
	 * @param arg0
	 */
	public TechnicalException(String arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 */
	public TechnicalException(Throwable arg0) {
		super(arg0);
		
	}


	

	

	

}
