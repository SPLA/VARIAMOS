package com.variamos.pl.configurator;


public class DomainAnnotation {
	private int value;
	private Choice choice;
	private int step;
	
	public DomainAnnotation(int value, Choice choice, int step) {
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
	public Choice getChoice() {
		return choice;
	}
	public void setChoice(Choice choice) {
		this.choice = choice;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}

	public boolean notAvailable() {
		return choice == Choice.CROSS;
	}
	
	
}
