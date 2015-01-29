package com.variamos.hlcl;

public class BinaryDomain extends IntervalDomain{
	public static final BinaryDomain INSTANCE = new BinaryDomain();

	public BinaryDomain() {
		super();
		add(0);
		add(1);
	}
	
	
}
