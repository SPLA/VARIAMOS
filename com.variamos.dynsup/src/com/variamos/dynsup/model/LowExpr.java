package com.variamos.dynsup.model;

import java.io.Serializable;

/**
 * A class to represent InstanceExpressions. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
/**
 * @author jcmunoz
 *
 */
public class LowExpr implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5244715882962436613L;
	private String lowExpressions;

	public String getLowExpressions() {
		return lowExpressions;
	}

	public void setLowExpressions(String lowExpressions) {
		this.lowExpressions = lowExpressions;
	}

}
