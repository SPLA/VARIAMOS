package com.cfm.hlcl;

import java.util.List;


public interface Domain {
	public int size();
	public String getStringRepresentation();
	public List<Integer> getPossibleValues();
}
