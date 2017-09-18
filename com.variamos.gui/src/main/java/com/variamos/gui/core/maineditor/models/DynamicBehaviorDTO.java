package com.variamos.gui.core.maineditor.models;

import java.util.ArrayList;
import java.util.List;

import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.translation.ModelExpr2HLCL;
import com.variamos.dynsup.translation.SolverOpersTask;
import com.variamos.dynsup.translation.SolverTasks;
import com.variamos.dynsup.types.DomainRegister;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.solver.model.SolverSolution;

public class DynamicBehaviorDTO {

	
	private List<String> validElements = null;
	protected DomainRegister domasinRegister = new DomainRegister();
	private InstanceModel refasModel;
	private SolverTasks task;
	private SolverOpersTask semTask;
	private ModelExpr2HLCL refas2hlcl;
	private SolverSolution lastConfiguration;
	private List<String> defects = new ArrayList<String>();
	private HlclProgram configHlclProgram;
	private boolean invalidConfigHlclProgram = true;
	
	
	
	
	public List<String> getValidElements() {
		return validElements;
	}
	public void setValidElements(List<String> validElements) {
		this.validElements = validElements;
	}
	public DomainRegister getDomasinRegister() {
		return domasinRegister;
	}
	public void setDomasinRegister(DomainRegister domasinRegister) {
		this.domasinRegister = domasinRegister;
	}
	public InstanceModel getRefasModel() {
		return refasModel;
	}
	public void setRefasModel(InstanceModel refasModel) {
		this.refasModel = refasModel;
	}
	public SolverTasks getTask() {
		return task;
	}
	public void setTask(SolverTasks task) {
		this.task = task;
	}
	public SolverOpersTask getSemTask() {
		return semTask;
	}
	public void setSemTask(SolverOpersTask semTask) {
		this.semTask = semTask;
	}
	public ModelExpr2HLCL getRefas2hlcl() {
		return refas2hlcl;
	}
	public void setRefas2hlcl(ModelExpr2HLCL refas2hlcl) {
		this.refas2hlcl = refas2hlcl;
	}
	public SolverSolution getLastConfiguration() {
		return lastConfiguration;
	}
	public void setLastConfiguration(SolverSolution lastConfiguration) {
		this.lastConfiguration = lastConfiguration;
	}
	public List<String> getDefects() {
		return defects;
	}
	public void setDefects(List<String> defects) {
		this.defects = defects;
	}
	public HlclProgram getConfigHlclProgram() {
		return configHlclProgram;
	}
	public void setConfigHlclProgram(HlclProgram configHlclProgram) {
		this.configHlclProgram = configHlclProgram;
	}
	public boolean isInvalidConfigHlclProgram() {
		return invalidConfigHlclProgram;
	}
	public void setInvalidConfigHlclProgram(boolean invalidConfigHlclProgram) {
		this.invalidConfigHlclProgram = invalidConfigHlclProgram;
	}

	
}
