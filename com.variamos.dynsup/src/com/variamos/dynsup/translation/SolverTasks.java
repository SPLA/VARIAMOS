package com.variamos.dynsup.translation;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.HlclUtil;
import com.variamos.hlcl.Identifier;
import com.variamos.io.configurations.ExportConfiguration;
import com.variamos.reasoning.defectAnalyzer.CauCosAnayzer;
import com.variamos.reasoning.defectAnalyzer.DefectsVerifier;
import com.variamos.reasoning.defectAnalyzer.IntCauCosAnalyzer;
import com.variamos.reasoning.defectAnalyzer.IntDefectsVerifier;
import com.variamos.reasoning.defectAnalyzer.model.CauCos;
import com.variamos.reasoning.defectAnalyzer.model.Diagnosis;
import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;
import com.variamos.reasoning.defectAnalyzer.model.enums.DefectAnalyzerMode;
import com.variamos.reasoning.defectAnalyzer.model.enums.DefectType;
import com.variamos.solver.Configuration;

/**
 * A class to support SwingWorkers for solver execution tasks. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-05-07
 */

public class SolverTasks extends SwingWorker<Void, Void> {
	private ModelExpr2HLCL refas2hlcl;
	private HlclProgram configHlclProgram;
	private boolean invalidConfigHlclProgram;

	public String getErrorTitle() {
		return errorTitle;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	private boolean test;
	private int execType;
	private long task = 0;
	private InstElement element;
	private String stringElement;
	private boolean firstSimulExec;
	private boolean reloadDashBoard;
	private String executionTime = "";
	private List<String> defects;
	private Configuration lastConfiguration;
	private String errorTitle = "";
	private String errorMessage = "";
	private boolean update;
	private Component parentComponent;
	private ModelInstance refasModel;
	private String file;
	private ProgressMonitor progressMonitor;
	private boolean next = true;
	private boolean terminated = false;
	private boolean correctExecution;

	public boolean isCorrectExecution() {
		return correctExecution;
	}

	public SolverTasks(ProgressMonitor progressMonitor,
			Component parentComponent, int execType, ModelExpr2HLCL refas2hlcl,
			HlclProgram configHlclProgram, boolean invalidConfigHlclProgram,
			boolean test, InstElement element, List<String> defects,
			Configuration lastConfiguration) {
		super();
		this.progressMonitor = progressMonitor;
		this.execType = execType;
		this.refas2hlcl = refas2hlcl;
		this.configHlclProgram = configHlclProgram;
		this.invalidConfigHlclProgram = invalidConfigHlclProgram;
		this.test = test;
		this.element = element;
		this.defects = defects;
		this.lastConfiguration = lastConfiguration;
		this.parentComponent = parentComponent;
	}

	public SolverTasks(ProgressMonitor progressMonitor, int execType,
			ModelExpr2HLCL refas2hlcl, HlclProgram configHlclProgram,
			boolean firstSimulExec, boolean reloadDashBoard, boolean update,
			String element, Configuration lastConfiguration) {
		this.progressMonitor = progressMonitor;
		this.execType = execType;
		this.refas2hlcl = refas2hlcl;
		this.configHlclProgram = configHlclProgram;
		this.firstSimulExec = firstSimulExec;
		this.reloadDashBoard = reloadDashBoard;
		this.update = update;
		this.stringElement = element;
		this.lastConfiguration = lastConfiguration;
	}

	public SolverTasks(ProgressMonitor progressMonitor, int execType,
			ModelInstance refasModel, ModelExpr2HLCL refas2hlcl, String file) {
		this.progressMonitor = progressMonitor;
		this.refasModel = refasModel;
		this.execType = execType;
		this.refas2hlcl = refas2hlcl;
		this.file = file;
	}

	public boolean isFirstSimulExec() {
		return firstSimulExec;
	}

	public boolean isReloadDashBoard() {
		return reloadDashBoard;
	}

	public void setReloadDashBoard(boolean reloadDashBoard) {
		this.reloadDashBoard = reloadDashBoard;
	}

	public boolean isUpdate() {
		return update;
	}

	public Configuration getLastConfiguration() {
		return lastConfiguration;
	}

	@Override
	public Void doInBackground() {
		setProgress(0);
		try {
			Thread.sleep(1);
			switch (execType) {
			case ModelExpr2HLCL.DESIGN_EXEC:
				verify(defects);
				break;
			case ModelExpr2HLCL.CONF_EXEC:
				configModel();
				break;
			case ModelExpr2HLCL.SIMUL_EXEC:
			case ModelExpr2HLCL.SIMUL_MAPE:
				executeSimulation(execType, update, stringElement);
				break;
			case ModelExpr2HLCL.SIMUL_EXPORT:
				saveConfiguration(file);
				break;
			}
		} catch (java.lang.UnsatisfiedLinkError e) {
			errorMessage = "Solver not correctly configured";
			errorTitle = "System Configuration Error";
			correctExecution = false;
		} catch (InterruptedException ignore) {
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "Solver Execution Problem, try again saving and loading the model.";
			errorTitle = "Verification Error";
			correctExecution = false;
		}
		task = 100;
		setProgress((int) task);
		return null;
	}

	public boolean saveConfiguration(String file) throws InterruptedException {
		setProgress(1);
		progressMonitor.setNote("Solutions processed: 0");
		Map<String, Map<String, Integer>> elements = refas2hlcl
				.execCompleteSimul(progressMonitor);
		setProgress(95);
		progressMonitor
				.setNote("Total Solutions processed: " + elements.size());
		List<String> names = new ArrayList<String>();
		for (String element : elements.get("1").keySet()) {
			if (refasModel.getElement(element) != null)
				names.add((String) refasModel.getElement(element)
						.getInstAttribute("name").getValue());
		}
		ExportConfiguration export = new ExportConfiguration();
		export.exportConfiguration(elements, names, file);
		return true;
	}

	public void configModel() throws InterruptedException {
		// this.clearNotificationBar();
		refas2hlcl.cleanGUIElements(ModelExpr2HLCL.CONF_EXEC);
		Set<Identifier> freeIdentifiers = null;
		Set<InstElement> elementSubSet = null;
		task = 0;
		long iniTime = 0;
		long endTime = 0;
		iniTime = System.currentTimeMillis();
		if (invalidConfigHlclProgram && element == null) {
			configHlclProgram = refas2hlcl.getHlclProgram("Simul",
					ModelExpr2HLCL.CONF_EXEC);
			freeIdentifiers = refas2hlcl.getFreeIdentifiers();
		} else {
			freeIdentifiers = new HashSet<Identifier>();
			elementSubSet = new HashSet<InstElement>();
			refas2hlcl.configGraph(progressMonitor, element, elementSubSet,
					freeIdentifiers, false);
			elementSubSet = new HashSet<InstElement>();
			configHlclProgram = refas2hlcl.configGraph(progressMonitor,
					element, elementSubSet, freeIdentifiers, true);
			task = 10;
			setProgress((int) task);
		}

		invalidConfigHlclProgram = false;
		TreeMap<String, Integer> configuredIdentNames = refas2hlcl
				.getConfiguredIdentifier(elementSubSet);
		Configuration config = new Configuration();

		config.setConfiguration(configuredIdentNames);

		List<String> requiredConceptsNames = new ArrayList<String>();
		List<String> deadConceptsNames = new ArrayList<String>();
		IntDefectsVerifier defectVerifier = new DefectsVerifier(
				configHlclProgram, SolverEditorType.SWI_PROLOG,
				parentComponent, "Configuring Selected Elements");
		// System.out.println("FREE: " + freeIdentifiers);

		// System.out.println("CONF: " + configuredIdentNames);

		if (freeIdentifiers.size() > 0) {
			try {

				List<Defect> requiredConcepts = null;

				requiredConcepts = defectVerifier.getFalseOptionalElements(
						freeIdentifiers, null, config);
				executionTime += "FalseOpt: " + defectVerifier.getTotalTime()
						+ "[" + defectVerifier.getSolverTime() / 1000000 + "]"
						+ " -- ";
				if (requiredConcepts.size() > 0) {
					for (Defect conceptVariable : requiredConcepts) {
						String[] conceptId = conceptVariable.getId().split("_");
						requiredConceptsNames.add(conceptId[0]);
					}

				}
			} catch (FunctionalException e) {
				e.printStackTrace();
			}
		}
		long falseOTime = defectVerifier.getSolverTime() / 1000000;
		task = 80;
		setProgress((int) task);
		// System.out.println("newSEL: " + requiredConceptsNames);
		refas2hlcl.updateRequiredConcepts(requiredConceptsNames, test);
		if (freeIdentifiers.size() > 0) {
			try {
				List<Defect> deadIndetifiersList = null;
				defectVerifier.resetTime();
				deadIndetifiersList = defectVerifier.getDeadElements(
						freeIdentifiers, null, config);
				executionTime += "Dead: " + defectVerifier.getTotalTime() + "["
						+ defectVerifier.getSolverTime() / 1000000 + "]"
						+ " -- ";
				if (deadIndetifiersList.size() > 0) {
					for (Defect conceptVariable : deadIndetifiersList) {
						String[] conceptId = conceptVariable.getId().split("_");
						deadConceptsNames.add(conceptId[0]);
					}

				}
			} catch (FunctionalException e) {
				e.printStackTrace();
			}

		}

		task = 100;
		setProgress((int) task);

		System.out.println("newNOTAV: " + deadConceptsNames);
		refas2hlcl.updateDeadConfigConcepts(deadConceptsNames, test);

		endTime = System.currentTimeMillis();
		executionTime += "ConfigExec: " + (endTime - iniTime) + "["
				+ (falseOTime + defectVerifier.getSolverTime() / 1000000) + "]"
				+ " -- ";
	}

	public int getExecType() {
		return execType;
	}

	public boolean verify(List<String> defect) throws InterruptedException {

		executionTime = "";
		boolean errors = false;

		List<String> verifList = new ArrayList<String>();
		List<String> actionList = new ArrayList<String>();

		verifList.add("Root");
		verifList.add("Parent");
		verifList.add("Other");

		actionList.add("Err");
		actionList.add("Err");
		actionList.add("Upd");
		List<String> verifMessageList = new ArrayList<String>();
		verifMessageList
				.add(" roots identified.\n Please keep only one of them.");
		verifMessageList
				.add(" concepts without a parent or with more than one parent identified.\n Please add/remove appropiated relations.");
		verifMessageList
				.add(" false optional concept(s) identified. Please review required attributes and relations.");
		verifMessageList
				.add(" false optional concept(s) identified. Please review required attributes and relations.");
		List<String> verifHintList = new ArrayList<String>();
		verifHintList
				.add("This is a root concept. More than one root concept identified.");
		verifHintList.add("This concept requires a parent.");
		verifHintList
				.add("This concept is a false optional. Please review required attribute or relations.");
		verifHintList
				.add("This concept is a false optional. Please review required attribute or relations.");

		int posList = 0;

		List<String> updateList = new ArrayList<String>();
		updateList.add("Core");
		updateList.add("Sel");
		// updateList.add("Satisfied");
		// updateList.add("ConfigSatisfied");
		// updateList.add("");

		List<String> outMessageList = new ArrayList<String>();
		if (defect.size() > 0)
			for (String verifElement : verifList) {
				if (progressMonitor.isCanceled())
					throw (new InterruptedException());
				if (defect == null || defect.contains(verifElement)
						|| verifElement.equals("Other")) {
					String verifMessage = verifMessageList.get(posList);
					String verifHint = verifMessageList.get(posList);
					if (actionList.get(posList).equals("Err")) {

						outMessageList.add(verifyDefects(verifElement,
								verifMessage, verifHint));
					} else {
						if (defect == null || defect.contains("Core")
								|| defect.contains("Dead")
								|| defect.contains("FalseOpt"))
							outMessageList
									.addAll(updateModel("Core", verifMessage,
											verifHint, updateList, defect));
					}
				}
				posList++;
			}
		if (progressMonitor.isCanceled())
			throw (new InterruptedException());
		if (defect == null || defect.contains("Simul"))
			executeSimulation(ModelExpr2HLCL.CONF_EXEC, false, "Simul");

		for (String outMessage : outMessageList) {
			if (outMessage != null) {
				errorMessage += outMessage + " ";
				errorTitle = "Verification Message";
				errors = true;
			}
		}
		if (progressMonitor.isCanceled())
			throw (new InterruptedException());
		if (!errors
				&& (defect == null || (defect.size() != 1 || !defect.get(0)
						.equals("Core")))) {
			errorMessage = "No errors found";
			errorTitle = "Verification Message";
		}

		task = 100;
		setProgress((int) task);

		return true;
	}

	public void executeSimulation(int type, boolean update, String element) {

		long iniTime = System.currentTimeMillis();
		boolean result = false;
		setProgress(10);
		while (!terminated) { // use the same task for simulation iterations
			if (!next) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			next = false;
			try {
				if (firstSimulExec || lastConfiguration == null) {
					result = refas2hlcl.execute(progressMonitor, element,
							ModelExpr2HLCL.ONE_SOLUTION, type);
				} else {
					result = refas2hlcl.execute(progressMonitor, element,
							ModelExpr2HLCL.NEXT_SOLUTION, type);
					/*
					 * Configuration currentConfiguration = refas2hlcl
					 * .getConfiguration(); if (result) { List<String>
					 * modifiedIdentifiers = compareSolutions(
					 * lastConfiguration, currentConfiguration);
					 * System.out.println(modifiedIdentifiers); }
					 */
				}
				if (result
						&& (progressMonitor == null || !progressMonitor
								.isCanceled())) {
					lastConfiguration = refas2hlcl.getConfiguration();
					if (update) {
						refas2hlcl.updateGUIElements(null);
						// messagesArea.setText(refas2hlcl.getText());
						// bringUpTab(mxResources.get("elementSimPropTab"));
						// editPropertiesRefas(editor.lastEditableElement);
					}
					correctExecution = true;
				} else {
					if (progressMonitor != null && progressMonitor.isCanceled()) {
						errorMessage = "Operation sucesfully cancelled.";
						errorTitle = "Model Execution";
						correctExecution = false;

						terminated = true;
					} else if (firstSimulExec || lastConfiguration == null) {
						switch (type) {
						case ModelExpr2HLCL.DESIGN_EXEC:
							errorMessage = "Last changes on the model makes it inconsistent."
									+ " \n Please review the restrictions defined and "
									+ "try again. \nModel visual representation was not updated.";
							errorTitle = "Model Verification Error";
							correctExecution = false;
							break;
						case ModelExpr2HLCL.CONF_EXEC:
							errorMessage = "Last change on the configuration makes the model "
									+ "\n inconsistent. Please review the selection and "
									+ "try again. \nAttributes values were not updated.";
							errorTitle = "Model Configuration Error";
							correctExecution = false;
							break;
						case ModelExpr2HLCL.SIMUL_EXEC:
						case ModelExpr2HLCL.SIMUL_MAPE:
						case ModelExpr2HLCL.SIMUL_EXPORT:
							errorMessage = "No solution found for this model configuration."
									+ " \n Please review the restrictions defined and "
									+ "try again.";
							errorTitle = "Model Simulation Error";
							correctExecution = false;
							break;
						}
						terminated = true;
					} else {
						errorMessage = "No more solutions found";
						errorTitle = "Simulation Message";
						correctExecution = false;
					}
				}
				long endTime = System.currentTimeMillis();
				executionTime += "NormalExec: " + (endTime - iniTime) + "["
						+ (refas2hlcl.getLastExecutionTime() / 1000000) + "]"
						+ " -- ";
				System.out.println(executionTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!firstSimulExec && result)
				// Update GUI after first execution, editor not notify because
				// the task is at 100%
				this.refas2hlcl.updateGUIElements(null);
			task = 100;
			setProgress((int) task);
			this.setProgress(100);

		}

	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public void setFirstSimulExec(boolean first) {
		this.firstSimulExec = first;
	}

	/*
	 * private List<String> compareSolutions(Configuration lastConfiguration,
	 * Configuration currentConfiguration) { List<String> out = new
	 * ArrayList<String>(); Map<String, Integer> lastConfig =
	 * lastConfiguration.getConfiguration(); Map<String, Integer> currentConfig
	 * = currentConfiguration .getConfiguration(); for (String solution :
	 * lastConfig.keySet()) if (lastConfig.get(solution) !=
	 * currentConfig.get(solution)) out.add(solution); return out; }
	 */
	public List<String> updateModel(String element, String verifMessage,
			String verifHint, List<String> attributes, List<String> defect)
			throws InterruptedException {
		HlclFactory f = new HlclFactory();
		List<String> out = new ArrayList<String>();
		long iniTime = System.currentTimeMillis();
		boolean result = false;
		result = refas2hlcl.execute(progressMonitor, element,
				ModelExpr2HLCL.ONE_SOLUTION, ModelExpr2HLCL.VAL_UPD_EXEC);
		IntDefectsVerifier defectVerifier = null;
		long falseOTime = 0;
		if (result) {

			refas2hlcl.updateGUIElements(attributes);
			Map<String, Integer> currentResult = refas2hlcl.getResult();
			System.out.println(currentResult);
			List<String> falseOptIdentifiers = getNewIdentifiers(currentResult,
					refas2hlcl.getResult());

			// System.out.println(falseOptIdentifiers);

			List<String> freeIdentifiers = getFreeIdentifiers(currentResult);
			List<String> deadIdentifiers = new ArrayList<String>();

			Set<Identifier> identifiers = new HashSet<Identifier>();

			for (String freeIndentifier : freeIdentifiers) {
				if (!freeIndentifier.startsWith("FeatOverTwo"))
					identifiers.add(f.newIdentifier(freeIndentifier));
			}

			if (freeIdentifiers.size() > 0
					&& (defect == null || defect.contains("Dead")
							|| defect.contains("FalseOpt") || defect
								.contains("Core"))) {
				try {
					defectVerifier = new DefectsVerifier(
							refas2hlcl.getHlclProgram("FalseOpt2",
									ModelExpr2HLCL.VAL_UPD_EXEC),
							SolverEditorType.SWI_PROLOG, parentComponent,
							"Identifing core/falseoptional/dead Elements");

					List<Defect> falseOptionalList = null;

					if (defect == null || defect.contains("FalseOpt")
							|| defect.contains("Core"))
					// Indentify false optional from non structural
					// relations
					{
						falseOptionalList = defectVerifier
								.getFalseOptionalElements(identifiers);
						falseOTime = defectVerifier.getSolverTime() / 1000000;
						if (falseOptionalList.size() > 0) {
							List<String> falseOptIdentOthers = new ArrayList<String>();
							for (Defect conceptVariable : falseOptionalList) {
								String[] conceptId = conceptVariable.getId()
										.split("_");
								falseOptIdentOthers.add(conceptId[0]);
							}
							falseOptIdentifiers.addAll(falseOptIdentOthers);
						}
						freeIdentifiers.removeAll(falseOptIdentifiers);
					}
					task += 30 / defect.size();
					setProgress((int) task);

					Set<Identifier> identDeadElements = new HashSet<Identifier>();

					List<Defect> deadIndetifiersList = null;
					if (defect == null || defect.contains("Dead")
							|| defect.contains("Core"))
					// Indentify false optional from non structural
					// relations
					{
						for (String freeIndentifier : freeIdentifiers) {
							if (!freeIndentifier.startsWith("FeatOverTwo"))
								identDeadElements.add(f
										.newIdentifier(freeIndentifier));
						}
						deadIndetifiersList = defectVerifier
								.getDeadElements(identDeadElements);
					}
					task += 30 / defect.size();
					setProgress((int) task);
					System.out.println(task);

					if (defect == null || defect.contains("Dead"))
						if (deadIndetifiersList.size() > 0) {
							for (Defect conceptVariable : deadIndetifiersList) {
								String[] conceptId = conceptVariable.getId()
										.split("_");
								deadIdentifiers.add(conceptId[0]);
							}
							out.add(deadIndetifiersList.size()
									+ " dead elements identified.");
						}

					// refas2hlcl.updateCoreConcepts(outIdentifiers);
				} catch (FunctionalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			Set<String> uniqueIdentifiers = new HashSet<String>();
			uniqueIdentifiers.addAll(falseOptIdentifiers);
			// System.out.println(uniqueIdentifiers);

			if (defect == null || defect.contains("FalseOpt")) {
				if (uniqueIdentifiers.size() > 0)
					out.add(uniqueIdentifiers.size() + verifMessage);
				refas2hlcl.updateErrorMark(uniqueIdentifiers, element,
						verifHint);
			}

			if (defect == null || defect.contains("Core"))
				refas2hlcl.updateCoreConcepts(uniqueIdentifiers, false);

			if (defect == null || defect.contains("Dead"))
				refas2hlcl.updateDeadConcepts(deadIdentifiers);

		} else {
			long endTime = System.currentTimeMillis();
			executionTime += element
					+ "Exec: "
					+ (endTime - iniTime)
					+ "["
					+ (refas2hlcl.getLastExecutionTime() / 1000000 + falseOTime + ((defectVerifier == null) ? 0
							: defectVerifier.getSolverTime() / 1000000)) + "]"
					+ " -- ";
			out.add("Last validated change makes the model inconsistent."
					+ " \n Please review the restrictions defined and "
					+ "try again. \nModel visual representation was not updated.");
		}

		// updateObjects();
		long endTime = System.currentTimeMillis();
		long defectVerifTime = defectVerifier == null ? 0 : (defectVerifier
				.getSolverTime() / 1000000);
		executionTime += element
				+ "Exec: "
				+ (endTime - iniTime)
				+ "["
				+ (refas2hlcl.getLastExecutionTime() / 1000000 + falseOTime + defectVerifTime)
				+ "]" + " -- ";
		task = 100 / defect.size();
		setProgress((int) task);
		return out;
	}

	private String verifyDefects(String verifElement, String verifMessage,
			String verifHint) throws InterruptedException {
		String outMessage = null;
		long iniTime = System.currentTimeMillis();
		long iniSTime = 0;
		long endSTime = 0;
		try {

			List<BooleanExpression> verify = refas2hlcl
					.verityTest(verifElement);
			HlclProgram relaxed = refas2hlcl.relaxedTest(verifElement);
			HlclProgram fixed = refas2hlcl.compulsoryTest(verifElement);
			Defect defect = new Defect(verify);
			defect.setDefectType(DefectType.SEMANTIC_SPECIFIC_DEFECT);
			HlclProgram modelToVerify = new HlclProgram();
			modelToVerify.addAll(verify);
			modelToVerify.addAll(relaxed);
			modelToVerify.addAll(fixed);
			iniSTime = System.currentTimeMillis();
			if (modelToVerify.toString().equals("[]"))
				return "No features to verify";
			IntDefectsVerifier verifier = new DefectsVerifier(modelToVerify,
					SolverEditorType.SWI_PROLOG);
			// The model has two or more roots
			Defect voidModel = verifier.isVoid();
			if (progressMonitor.isCanceled())
				throw (new InterruptedException());
			Set<String> outIdentifiers = new TreeSet<String>();
			if (voidModel != null) {

				IntCauCosAnalyzer cauCosAnalyzer = new CauCosAnayzer(
						parentComponent, verifElement);
				HlclProgram fixedConstraint = new HlclProgram();
				fixedConstraint.addAll(verify);
				fixedConstraint.addAll(fixed);
				Diagnosis result = cauCosAnalyzer.getCauCos(defect, relaxed,
						fixedConstraint, DefectAnalyzerMode.PARTIAL);
				endSTime = System.currentTimeMillis();
				String defects = "(";
				for (CauCos correction : result.getCorrections()) {
					if (progressMonitor.isCanceled())
						throw (new InterruptedException());
					List<BooleanExpression> corr = correction.getElements();
					for (BooleanExpression expression : corr) {
						if (progressMonitor.isCanceled())
							throw (new InterruptedException());
						Set<Identifier> iden = HlclUtil
								.getUsedIdentifiers(expression);
						Identifier firsIden = iden.iterator().next();
						String[] o = firsIden.getId().split("_");

						if (outIdentifiers.add(o[0]))
							defects += o[0] + ", ";
					}
				}
				// There are more than one root.
				if (!outIdentifiers.isEmpty()) {

					defects = defects.substring(0, defects.length() - 2) + ")";
					outMessage = outIdentifiers.size() + verifMessage + "\n"
							+ defects;
				}
			} else {
				endSTime = System.currentTimeMillis();
			}
			refas2hlcl.updateErrorMark(outIdentifiers, verifElement, verifHint);
		} catch (FunctionalException e) {
			endSTime = System.currentTimeMillis();
			outMessage = e.getMessage();
		} finally {
			long endTime = System.currentTimeMillis();
			executionTime += verifElement + " Verif.: " + (endTime - iniTime)
					+ "[" + (endSTime - iniSTime) + "]" + " -- ";
		}

		return outMessage;
	}

	private List<String> getFreeIdentifiers(Map<String, Integer> currentResult) {
		List<String> out = new ArrayList<String>();
		for (String id : currentResult.keySet()) {
			String[] o = id.split("_");
			if (o[1].equals("Sel") && currentResult.get(id) == 0)
				out.add(id);

		}
		return out;
	}

	private List<String> getNewIdentifiers(Map<String, Integer> currentResult,
			Map<String, Integer> lastResult) {
		List<String> out = new ArrayList<String>();
		for (String id : currentResult.keySet()) {
			String[] o = id.split("_");
			if (o[1].equals("Sel") && lastResult.get(id) == 0
					&& currentResult.get(id) == 1)
				out.add(o[0]);

		}
		return out;
	}

	public boolean isInvalidConfigHlclProgram() {
		return invalidConfigHlclProgram;
	}

	public void setInvalidConfigHlclProgram(boolean invalidConfigHlclProgram) {
		this.invalidConfigHlclProgram = invalidConfigHlclProgram;
	}

	public String getExecutionTime() {
		return executionTime;
	}

	@Override
	public void done() {
	}
}
