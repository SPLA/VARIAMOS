package com.variamos.gui.core.maineditor.models;

import java.util.List;

import javax.swing.ProgressMonitor;

import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.translation.ModelExpr2HLCL;
import com.variamos.dynsup.translation.SolverOpersTask;
import com.variamos.dynsup.translation.SolverTasks;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.solver.model.SolverSolution;

public class DynamicBehaviorDTO {

	private InstanceModel refasModel;
	private SolverTasks solverTask;
	private SolverOpersTask semTask;
	private ModelExpr2HLCL refas2hlcl;
	private SolverSolution lastSolution;
	private HlclProgram configHlclProgram;
	private boolean invalidConfigHlclProgram = true;
	private ProgressMonitor progressMonitor;

	
	
	
	public DynamicBehaviorDTO(ProgressMonitor progressMonitor) {
		super();
		this.progressMonitor = progressMonitor;
	}

	

	// TODO support ALL operations dynamically, not only the first

	

	// Dynamic operation's definition
	public SolverOpersTask executeOperationsThead(boolean firstSimulExecution, List<String> operations,
			String filename) {

		if (!firstSimulExecution && getSemTask() != null) {
			getSemTask().setFirstSimulExec(false);
			getSemTask().setNext(true);
		} else {
			if (getSemTask() != null)
				getSemTask().setTerminated(true);
//			progressMonitor = new ProgressMonitor(DynamicBehaviorDTO.this, "Executing operation", "", 0, 100);
//			progressMonitor.setMillisToDecideToPopup(5);
//			progressMonitor.setMillisToPopup(5);
//			progressMonitor.setProgress(1);
			SolverOpersTask semTask = new SolverOpersTask(progressMonitor, getRefasModel(),
					getRefas2hlcl(), getConfigHlclProgram(), firstSimulExecution,
					operations, getLastSolution(), filename);

			//semTask.addPropertyChangeListener(this);
			semTask.execute();

			setSemTask(semTask);
		}
		return getSemTask();
	}

	// Static operation's definition
	public SolverTasks executeSimulation(boolean firstSimulExecution, boolean reloadDashboard, int type, boolean update,
			String element) {

		if (!firstSimulExecution && getSolverTask() != null
				&& getSolverTask().getExecType() == ModelExpr2HLCL.SIMUL_EXEC) {
			getSolverTask().setFirstSimulExec(false);
			getSolverTask().setNext(true);
		} else {
			if (getSolverTask() != null)
				getSolverTask().setTerminated(true);
//			progressMonitor = new ProgressMonitor(VariamosGraphEditor.this, "Executing Simulation", "", 0, 100);
//			progressMonitor.setMillisToDecideToPopup(5);
//			progressMonitor.setMillisToPopup(5);
//			progressMonitor.setProgress(0);
			SolverTasks task = new SolverTasks(progressMonitor, type, getRefas2hlcl(),
					getConfigHlclProgram(), firstSimulExecution, reloadDashboard, update, element,
					getLastSolution());
			//task.addPropertyChangeListener(this);
			task.execute();
			setSolverTask(task);
		}
		return getSolverTask();
	}

	public InstanceModel getRefasModel() {
		return refasModel;
	}

	public void setRefasModel(InstanceModel refasModel) {
		this.refasModel = refasModel;
	}

	public SolverTasks getSolverTask() {
		return solverTask;
	}

	public void setSolverTask(SolverTasks task) {
		this.solverTask = task;
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

	public SolverSolution getLastSolution() {
		return lastSolution;
	}

	public void setLastSolution(SolverSolution lastSolution) {
		this.lastSolution = lastSolution;
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
