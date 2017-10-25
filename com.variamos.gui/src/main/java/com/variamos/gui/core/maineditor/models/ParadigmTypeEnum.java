package com.variamos.gui.core.maineditor.models;

public enum ParadigmTypeEnum {
	EMPTY("Empty"), 
	FEATURES("Feature-based project"),
	REFAS("Goal/soft-goal project"), 
	CONSTRAINTGRAPHS("Constraint graph project"),
	CUSTOMIZED("Customized project");
	private String label;

	private ParadigmTypeEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
