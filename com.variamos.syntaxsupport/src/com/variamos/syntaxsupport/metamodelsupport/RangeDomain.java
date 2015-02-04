package com.variamos.syntaxsupport.metamodelsupport;

import java.util.List;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class RangeDomain extends Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8704413196558459279L;
	private List<Integer> rangeValues;

	public List<Integer> getRangeValues() {
		return rangeValues;
	}
}
