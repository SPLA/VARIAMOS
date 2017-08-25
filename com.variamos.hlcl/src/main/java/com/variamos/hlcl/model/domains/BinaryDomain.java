package com.variamos.hlcl.model.domains;

public class BinaryDomain extends IntervalDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1499843595727738269L;
	public static final BinaryDomain INSTANCE = new BinaryDomain();

	public BinaryDomain() {
		super();
		add(0);
		add(1);
	}

}
