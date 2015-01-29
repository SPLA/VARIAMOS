package com.cfm.productline.constraints;

import java.util.ArrayList;

import java.util.List;


import com.cfm.productline.Constraint;
import com.cfm.productline.Variable;

@SuppressWarnings("serial")
public class TwoOperandConstraint extends Constraint{
	private List<String> features = new ArrayList<>(2);
	
	public TwoOperandConstraint() {
		super();
		features.add(null);
		features.add(null);
	}
	
	public TwoOperandConstraint(String f1, String f2){
		this();
		setFeature1Id(firstUpperCaseString(f1));
		setFeature2Id(firstUpperCaseString(f2));
	}
	
	public List<String> getRelatedIds(){
		return features;
	}
	
	public String getFeature1Id(){
		return features.get(0);
	}
	
	public String getFeature2Id(){
		return features.get(1);
	}
	
	public void setFeature1Id(String id){
		features.set(0, id);
	}
	
	public void setFeature2Id(String id){
		features.set(1, id);
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	@Override
	public Variable[] getEditableVariables() {
		return new Variable[]{};
	}

}
