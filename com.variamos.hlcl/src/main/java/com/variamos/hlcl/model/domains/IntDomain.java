package com.variamos.hlcl.model.domains;

import java.util.List;

public interface IntDomain {
	public int size();

	public String getStringRepresentation();

	public List<Integer> getPossibleValues();

	public List<Float> getPossibleFloatValues();

	public List<String> getPossibleStringValues();
}
