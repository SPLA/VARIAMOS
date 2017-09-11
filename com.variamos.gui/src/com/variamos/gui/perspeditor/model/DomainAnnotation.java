package com.variamos.gui.perspeditor.model;

@Deprecated
public class DomainAnnotation {
	private int value;
	private ChoiceEnum choice; 
	private int step;
	
	public DomainAnnotation(int value, ChoiceEnum choice, int step) {
		super();
		this.value = value;
		this.choice = choice;
		this.step = step;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public ChoiceEnum getChoice() {
		return choice;
	}
	public void setChoice(ChoiceEnum choice) {
		this.choice = choice;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public boolean notAvailable() {
		return choice == ChoiceEnum.CROSS;
	}
}
