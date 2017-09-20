package com.variamos.common.core.exceptions;

import java.util.Arrays;

public class GraphReadingException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public GraphReadingException() {
		
	}

	/**
	 * @param arg0 string text
	 */
	public GraphReadingException(String arg0) {
		super(arg0);
		 
	}


	/**
	 * @param arg0
	 */

	public GraphReadingException(Throwable arg0) {
		super(arg0);
		 
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public GraphReadingException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		 
	}

	public GraphReadingException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		 
	}
	
	public static String exceptionStacktraceToString(Exception e)
	{
	    return Arrays.toString(e.getStackTrace());
	}
}
