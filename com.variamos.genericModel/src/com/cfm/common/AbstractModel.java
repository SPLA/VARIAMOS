package com.cfm.common;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Map;

import com.cfm.productline.Asset;
import com.cfm.productline.Constraint;
import com.cfm.productline.VariabilityElement;

public abstract class AbstractModel {
	public abstract Collection<VariabilityElement> getVariabilityElements();
	public abstract Map<String, Asset> getAssets();
	public abstract Constraint getConstraint(String consId);
	public abstract Collection<Constraint> getConstraints();
	public abstract void setName(String filename) ;
}
