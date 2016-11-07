package com.variamos.dynsup.translation;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.variamos.core.util.StringUtils;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersIOAttribute;
import com.variamos.dynsup.model.OpersSubOperation;
import com.variamos.dynsup.types.OperationSubActionExecType;
import com.variamos.dynsup.types.OperationSubActionType;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.HlclUtil;
import com.variamos.hlcl.Identifier;
import com.variamos.io.ConsoleTextArea;
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
 * A class to support SwingWorkers for solver execution tasks using the semantic
 * operations. Originally copied from
 * com.variamos.perspsupport.perspmodel.SolverTasks. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-12-22
 * @see com.variamos.dynsup.translation.SolverTasks
 */

public class SolverOpersTask extends SwingWorker<Void, Void> {
	private ModelExpr2HLCL refas2hlcl;
	private HlclProgram configHlclProgram;
	private boolean invalidConfigHlclProgram;
	private List<String> outVariables = null;
	private List<String> defectsFreeIdsName = null;

	public List<String> getOutVariables() {
		return outVariables;
	}

	public String getErrorTitle() {
		return errorTitle;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	private boolean test;
	private long task = 0;
	private InstElement element;
	private List<String> operationsNames;
	private boolean firstSimulExec;
	private boolean reloadDashBoard;
	private String executionTime = "";
	// private List<String> defects;
	private Configuration lastConfiguration;
	private String errorTitle = "";
	private String errorMessage = "";
	private boolean update;
	private Component parentComponent;
	private ModelInstance refasModel;
	// private String file;
	private ProgressMonitor progressMonitor;
	private boolean next = true;
	private boolean terminated = false;
	private boolean correctExecution;

	public boolean isCorrectExecution() {
		return correctExecution;
	}

	public SolverOpersTask(ProgressMonitor progressMonitor,
			ModelInstance refasModel, ModelExpr2HLCL refas2hlcl,
			HlclProgram configHlclProgram, boolean firstSimulExec,
			boolean reloadDashBoard, boolean update, List<String> operations,
			Configuration lastConfiguration) {
		this.refasModel = refasModel;
		this.progressMonitor = progressMonitor;
		this.refas2hlcl = refas2hlcl;
		this.configHlclProgram = configHlclProgram;
		this.firstSimulExec = firstSimulExec;
		this.reloadDashBoard = reloadDashBoard;
		this.update = update;
		this.operationsNames = operations;
		this.lastConfiguration = lastConfiguration;
	}

	public SolverOpersTask(ProgressMonitor progressMonitor,
			String operationIdentifier, ModelInstance refasModel,
			ModelExpr2HLCL refas2hlcl, String file) {
		this.progressMonitor = progressMonitor;
		this.refasModel = refasModel;
		this.refas2hlcl = refas2hlcl;
		// this.file = file;
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
			executeOperations();
		} catch (java.lang.UnsatisfiedLinkError e) {
			errorMessage = "Solver not correctly configured";
			errorTitle = "System Configuration Error";
			correctExecution = false;
		} catch (InterruptedException ignore) {
		} catch (Exception e) {
			ConsoleTextArea.addText(e.getMessage());
			ConsoleTextArea.addText(e.getStackTrace());
			errorMessage = "Solver Execution Problem, try again saving and loading the model.";
			errorTitle = "Verification Error";
			correctExecution = false;
		}
		task = 100;
		setProgress((int) task);
		return null;
	}

	// TODO Modify for dynamic operations
	@Deprecated
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

	// TODO Modify for dynamic operations
	@Deprecated
	private void configModel() throws InterruptedException {
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
		TreeMap<String, Number> configuredIdentNames = refas2hlcl
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
				ConsoleTextArea.addText(e.getStackTrace());
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
				ConsoleTextArea.addText(e.getStackTrace());
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

	// dynamic call implementation
	public void executeOperations() {

		long iniTime = System.currentTimeMillis();
		int result = 0;
		setProgress(10);
		while (!terminated) { // use the same task for simulation iterations
			if (!next) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					ConsoleTextArea.addText(e.getStackTrace());
				}
				continue;
			}
			next = false;

			resetFreeIdentifiers();
			for (String operationName : operationsNames) {
				InstElement operationObj = refas2hlcl.getRefas()
						.getSyntaxModel().getOperationalModel()
						.getElement(operationName);
				Set<InstElement> suboperationsObjs = new TreeSet<InstElement>();
				Map<String, InstElement> instsuboperations = new HashMap<String, InstElement>();
				// Auto sorting with treeset
				for (InstElement operpair : operationObj.getTargetRelations()) {
					InstElement suboper = operpair.getTargetRelations().get(0);
					instsuboperations.put(suboper.getIdentifier(), suboper);
					suboperationsObjs.add(suboper);
				}
				result = 0;
				for (InstElement suboper : suboperationsObjs) {
					if (result == -1)
						break;

					try {
						// Validation operations
						// System.out.println(((String) suboper
						// .getInstAttributeValue("type")));
						String type = (String) suboper
								.getInstAttributeValue("type");
						if (type.equals(StringUtils
								.formatEnumValue(OperationSubActionType.Single_Update
										.toString()))
								|| type.equals(StringUtils
										.formatEnumValue(OperationSubActionType.Iterative_Update
												.toString())))
							if (lastConfiguration == null) {
								result = refas2hlcl.execute(progressMonitor,
										ModelExpr2HLCL.ONE_SOLUTION,
										operationObj, instsuboperations
												.get(suboper.getIdentifier())); // type

							} else {
								if ((boolean) suboper
										.getInstAttributeValue("iteration")) {
									result = refas2hlcl.execute(
											progressMonitor,
											ModelExpr2HLCL.NEXT_SOLUTION,
											operationObj, instsuboperations
													.get(suboper
															.getIdentifier())); // type
								} else
									continue;
							}

						// Verification operations with CauCos
						else if (type
								.equals(StringUtils
										.formatEnumValue(OperationSubActionType.Multi_Verification
												.toString()))) {
							String errorTitle = (String) suboper
									.getInstAttributeValue("errorTitle");
							String errorText = (String) suboper
									.getInstAttributeValue("errorText");
							String errorMsg = (String) suboper
									.getInstAttributeValue("errorMsg");
							String errorHint = (String) suboper
									.getInstAttributeValue("errorHint");
							List<OpersIOAttribute> outAttributes = ((OpersSubOperation) suboper
									.getEdOperEle()).getOutAttributes();
							cauCos(0, operationObj, suboper, errorHint,
									errorMsg, outAttributes,
									operationsNames.size());
							terminated = true;
						} // Verification operations with DefectsVerifier
						else if (type
								.equals(StringUtils
										.formatEnumValue(OperationSubActionType.Defects_Verifier_Error
												.toString()))
								|| type.equals(StringUtils
										.formatEnumValue(OperationSubActionType.Defects_Verifier_Update
												.toString()))) {
							String method = (String) suboper
									.getInstAttributeValue("defectsVerifierMethod");
							String errorTitle = (String) suboper
									.getInstAttributeValue("errorTitle");
							String errorText = (String) suboper
									.getInstAttributeValue("errorText");
							String errorMsg = (String) suboper
									.getInstAttributeValue("errorMsg");
							String errorHint = (String) suboper
									.getInstAttributeValue("errorHint");
							String outAttribute = (String) suboper
									.getInstAttributeValue("outAttribute");
							boolean reuseFreeIds = (boolean) suboper
									.getInstAttributeValue("reuseFreeIds");
							boolean updateFreeIds = (boolean) suboper
									.getInstAttributeValue("updateFreeIds");
							String coreOperName = null;
							InstElement coreOperation = null;
							boolean updateOutAttributes = false;

							List<BooleanExpression> constraitsToVerifyRedundacies = null;
							if (type.equals(StringUtils
									.formatEnumValue(OperationSubActionType.Defects_Verifier_Error
											.toString()))) {
								coreOperName = (String) suboper
										.getInstAttributeValue("defectsCoreOper");
								if (coreOperName == null)
									coreOperName = "UpdateCoreOper";
								coreOperation = refas2hlcl.getRefas()
										.getSyntaxModel().getOperationalModel()
										.getElement(coreOperName);
								if (method.equals("getRedundancies")) {
									constraitsToVerifyRedundacies = refas2hlcl
											.getHlclProgram(
													operationObj,
													suboper.getIdentifier(),
													OperationSubActionExecType.TOVERIFY,
													null);
								}
							} else
								updateOutAttributes = (boolean) suboper
										.getInstAttributeValue("updateOutAttributes");

							List<OpersIOAttribute> outAttributes = ((OpersSubOperation) suboper
									.getEdOperEle()).getOutAttributes();
							defectsVerifier(operationObj, suboper, method,
									errorHint, errorMsg, outAttributes,
									operationsNames.size(), reuseFreeIds,
									updateFreeIds, outAttribute,
									updateOutAttributes, coreOperation,
									constraitsToVerifyRedundacies);
							terminated = true;
						} else {
							result = -1;
						}
						if (result == 1) {
							outVariables = refas2hlcl.getOutVariables(
									operationName, suboper.getIdentifier());
							lastConfiguration = refas2hlcl.getConfiguration();
							if (update) {
								refas2hlcl
										.updateGUIElements(null, outVariables);
								// messagesArea.setText(refas2hlcl.getText());
								// bringUpTab(mxResources.get("elementSimPropTab"));
								// editPropertiesRefas(editor.lastEditableElement);
							}
							correctExecution = true;
							long endTime = System.currentTimeMillis();
							executionTime = "NormalExec: "
									+ (endTime - iniTime)
									+ "["
									+ (refas2hlcl.getLastExecutionTime() / 1000000)
									+ "]" + " -- ";
							System.out.println(executionTime);
						} else {
							if (result == -1)
								if (firstSimulExec && lastConfiguration == null) {
									errorMessage = (String) suboper
											.getInstAttributeValue("errorText");
									errorTitle = (String) suboper
											.getInstAttributeValue("errorTitle");
									correctExecution = false;
									terminated = true;
								} else {
									errorMessage = "No more solutions found";
									errorTitle = "Simulation Message";
									correctExecution = false;
								}
							// terminated = true;
						}

					} catch (Exception e) {
						ConsoleTextArea.addText(e.getMessage());
						ConsoleTextArea.addText(e.getStackTrace());
					}
				}
				if (!firstSimulExec && result == 1)
					// Update GUI after first execution, editor is not notify
					// because the task is at 100%
					this.refas2hlcl.updateGUIElements(null, outVariables);
			}
			task = 100;
			setProgress((int) task);
			this.setProgress(100);

		}

	}

	private void resetFreeIdentifiers() {
		defectsFreeIdsName = null;
	}

	private boolean defectsVerifier(InstElement operation, InstElement subOper,
			String method, String verifMessage, String verifHint,
			List<OpersIOAttribute> outAttributes, int numberOperations,
			boolean reuseIds, boolean updateIds, String outAttribute,
			boolean updateOutAttributes, InstElement coreOperation,
			List<BooleanExpression> constraitsToVerifyRedundacies)
			throws InterruptedException {

		executionTime = "";

		List<String> outMessageList = new ArrayList<String>();
		if (coreOperation == null)
			outMessageList.addAll(defectExecution(operation, subOper, method,
					outAttributes, numberOperations, outAttribute,
					updateOutAttributes));

		else
			outMessageList.addAll(defectExecution(operation, subOper, method,
					verifMessage, verifHint, outAttributes, numberOperations,
					reuseIds, updateIds, outAttribute, updateOutAttributes,
					coreOperation, constraitsToVerifyRedundacies));

		if (progressMonitor.isCanceled())
			throw (new InterruptedException());

		for (String outMessage : outMessageList) {
			if (outMessage != null) {
				errorMessage += outMessage + " ";
				errorTitle = "Verification Message";

			}
		}

		return true;
	}

	private boolean cauCos(int type, InstElement operation,
			InstElement subOper, String verifMessage, String verifHint,
			List<OpersIOAttribute> outAttributes, int numberOperations)
			throws InterruptedException {

		executionTime = "";

		List<String> outMessageList = new ArrayList<String>();
		outMessageList.add(caucosExecution(type, operation, subOper,
				verifMessage, verifHint, outAttributes, numberOperations));

		if (progressMonitor.isCanceled())
			throw (new InterruptedException());
		return true;
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

	public boolean singleUpdate(InstElement operation, InstElement subOper,
			List<OpersIOAttribute> outAttributes) throws InterruptedException {

		int result = 0;
		result = refas2hlcl.execute(progressMonitor, 0, operation, subOper);
		if (result == 1) {
			refas2hlcl.updateGUIElements(outAttributes, null);
			return true;
		} else
			return false;
	}

	public List<String> defectExecution(InstElement operation,
			InstElement subOper, String method,
			List<OpersIOAttribute> outAttributes, int numberOperations,
			String outAttribute, boolean updateOutAttributes)
			throws InterruptedException {
		HlclFactory f = new HlclFactory();
		List<String> out = new ArrayList<String>();
		long iniTime = System.currentTimeMillis();
		int result = 0;
		// Validate if the model is correct
		// FIXME call with core once, not here

		IntDefectsVerifier defectVerifier = null;
		List<String> freeIdsNames = null;
		List<String> defectsNames = new ArrayList<String>();
		long falseOTime = 0;

		result = refas2hlcl.execute(progressMonitor, 0, operation, subOper);
		if (result == 1) {
			Map<String, Number> currentResult = refas2hlcl.getResult();
			freeIdsNames = getFreeIdentifiers(currentResult, outAttribute,
					outAttribute);
		}

		if (freeIdsNames != null) {
			Set<Identifier> freeIdentifiers = new HashSet<Identifier>();
			for (String freeIdentifier : freeIdsNames) {
				if (!freeIdentifier.startsWith("FeatOT"))
					freeIdentifiers.add(f.newIdentifier(freeIdentifier));
			}

			if (freeIdsNames.size() > 0) {
				try {
					defectVerifier = new DefectsVerifier(
							refas2hlcl.getHlclProgram(operation,
									subOper.getIdentifier(),
									OperationSubActionExecType.NORMAL, null),
							SolverEditorType.SWI_PROLOG, parentComponent,
							"dynamic verification:" + operation);

					List<Defect> coreIds = null;

					switch (method) {
					case "getFalseOptionalElements":
						coreIds = defectVerifier
								.getFalseOptionalElements(freeIdentifiers);
						break;
					case "getDeadElements":
						coreIds = defectVerifier
								.getDeadElements(freeIdentifiers);
						break;
					case "getRedundancies":
						break;
					case "getAllNonAttainableDomains":
						coreIds = defectVerifier
								.getAllNonAttainableDomains(freeIdentifiers);
						break;
					default:
						throw new FunctionalException();
					}

					falseOTime = defectVerifier.getSolverTime() / 1000000;
					if (coreIds.size() > 0) {
						List<String> newDefectsNames = new ArrayList<String>();
						List<String> newDefectsIds = new ArrayList<String>();
						for (Defect conceptVariable : coreIds) {
							String[] conceptId = conceptVariable.getId().split(
									"_");
							newDefectsNames.add(conceptId[0]);
							newDefectsIds.add(conceptVariable.getId());
						}
						defectsNames.addAll(newDefectsNames);
						freeIdsNames.removeAll(newDefectsIds);
						defectsFreeIdsName = freeIdsNames;
					}

					task += 100 / numberOperations;
					setProgress((int) task);

				} catch (FunctionalException e) {
					// TODO Auto-generated catch block
					ConsoleTextArea.addText(e.getStackTrace());
				}

			}
			if (updateOutAttributes)
				refas2hlcl.updateGUIElements(outAttributes, null);

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

		long endTime = System.currentTimeMillis();
		long defectVerifTime = defectVerifier == null ? 0 : (defectVerifier
				.getSolverTime() / 1000000);
		executionTime += element
				+ "Exec: "
				+ (endTime - iniTime)
				+ "["
				+ (refas2hlcl.getLastExecutionTime() / 1000000 + falseOTime + defectVerifTime)
				+ "]" + " -- ";
		task = 100 / numberOperations;
		setProgress((int) task);
		return out;
	}

	public List<String> defectExecution(InstElement operation,
			InstElement subOper, String method, String verifMessage,
			String verifHint, List<OpersIOAttribute> outAttributes,
			int numberOperations, boolean reuseIds, boolean updateIds,
			String outAttribute, boolean updateOutAttributes,
			InstElement coreOperation,
			List<BooleanExpression> constraitsToVerifyRedundacies)
			throws InterruptedException {
		HlclFactory f = new HlclFactory();
		List<String> out = new ArrayList<String>();
		long iniTime = System.currentTimeMillis();
		int result = 0;
		// Validate if the model is correct
		// FIXME call with core once, not here

		IntDefectsVerifier defectVerifier = null;
		List<String> freeIdsNames = null;
		List<String> defectsNames = new ArrayList<String>();
		long falseOTime = 0;

		InstElement coreSubOper = coreOperation.getTargetRelations().get(0)
				.getTargetRelations().get(0);
		String coreOutAttribute = (String) coreSubOper
				.getInstAttributeValue("outAttribute");
		boolean updateCoreOutAttribute = (boolean) coreSubOper
				.getInstAttributeValue("updateOutAttributes");
		if (reuseIds && defectsFreeIdsName != null) {
			freeIdsNames = new ArrayList<String>();
			freeIdsNames.addAll(defectsFreeIdsName);
		} else {
			result = refas2hlcl.execute(progressMonitor, 0, coreOperation,
					coreSubOper);
			if (result == 1) {
				// FIXME update outAttributes for CoreOper
				if (updateCoreOutAttribute)
					refas2hlcl.updateGUIElements(outAttributes, null);
				Map<String, Number> currentResult = refas2hlcl.getResult();
				freeIdsNames = getFreeIdentifiers(currentResult,
						coreOutAttribute, outAttribute);
			}
		}
		if (freeIdsNames != null) {
			Set<Identifier> freeIdentifiers = new HashSet<Identifier>();
			for (String freeIdentifier : freeIdsNames) {
				if (!freeIdentifier.startsWith("FeatOT"))
					freeIdentifiers.add(f.newIdentifier(freeIdentifier));
			}

			if (freeIdsNames.size() > 0) {
				try {
					defectVerifier = new DefectsVerifier(
							refas2hlcl.getHlclProgram(operation,
									subOper.getIdentifier(),
									OperationSubActionExecType.NORMAL, null),
							SolverEditorType.SWI_PROLOG, parentComponent,
							"dynamic verification:" + operation);

					List<Defect> defects = null;

					switch (method) {
					case "getFalseOptionalElements":
						defects = defectVerifier
								.getFalseOptionalElements(freeIdentifiers);
						break;
					case "getDeadElements":
						defects = defectVerifier
								.getDeadElements(freeIdentifiers);
						break;
					case "getRedundancies":

						defects = defectVerifier
								.getRedundancies(constraitsToVerifyRedundacies);
						break;
					case "getAllNonAttainableDomains":
						defects = defectVerifier
								.getAllNonAttainableDomains(freeIdentifiers);
						break;
					default:
						throw new FunctionalException();
					}

					falseOTime = defectVerifier.getSolverTime() / 1000000;
					if (defects.size() > 0) {
						List<String> newDefectsNames = new ArrayList<String>();
						List<String> newDefectsIds = new ArrayList<String>();
						for (Defect conceptVariable : defects) {
							String[] conceptId = conceptVariable.getId().split(
									"_");
							newDefectsNames.add(conceptId[0]);
							newDefectsIds.add(conceptVariable.getId());
						}
						defectsNames.addAll(newDefectsNames);
						freeIdsNames.removeAll(newDefectsIds);
						if (updateIds)
							defectsFreeIdsName = freeIdsNames;
					}

					task += 100 / numberOperations;
					setProgress((int) task);

					Set<Identifier> identDeadElements = new HashSet<Identifier>();

					refas2hlcl.updateErrorMark(defectsNames,
							operation.getIdentifier(), verifMessage);
				} catch (FunctionalException e) {
					// TODO Auto-generated catch block
					ConsoleTextArea.addText(e.getStackTrace());
				}

			}
			if (updateOutAttributes)
				refas2hlcl.updateGUIElements(outAttributes, null);
			//
			// Set<String> uniqueIdentifiers = new HashSet<String>();
			// uniqueIdentifiers.addAll(defectsNames);
			// if (method.contains("FalseOpt")) {
			// if (uniqueIdentifiers.size() > 0)
			// out.add(uniqueIdentifiers.size() + verifMessage);
			// refas2hlcl
			// .updateErrorMark(uniqueIdentifiers, "Core", verifHint);
			// }
			//
			// if (method.contains("Core"))
			// refas2hlcl.updateCoreConcepts(uniqueIdentifiers, false);
			//
			// if (method.contains("Dead"))
			// refas2hlcl.updateDeadConcepts(deadIdentifiers);
			//
			// if (updateIds && defectsFreeIdsName != null)
			// defectsFreeIdsName = freeIdsNames;
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
		task = 100 / numberOperations;
		setProgress((int) task);
		return out;
	}

	// TODO Modify for dynamic operations
	private String caucosExecution(int type, InstElement operation,
			InstElement subOper, String verifMessage, String verifHint,
			List<OpersIOAttribute> outAttributes, int numberOperations)
			throws InterruptedException {
		// type: for future variations on the execution
		String verifElement = operation.getIdentifier();
		String outMessage = null;
		long iniTime = System.currentTimeMillis();
		long iniSTime = 0;
		long endSTime = 0;
		try {

			TranslationExpressionSet transExpSet = new TranslationExpressionSet(
					refasModel, operation, null, null);
			List<BooleanExpression> verify = refas2hlcl.getHlclProgram(
					operation, subOper.getIdentifier(),
					OperationSubActionExecType.VERIFICATION, transExpSet);

			HlclProgram relaxed = refas2hlcl.getHlclProgram(operation,
					subOper.getIdentifier(),
					OperationSubActionExecType.RELAXABLE, transExpSet);
			HlclProgram fixed = refas2hlcl.getHlclProgram(operation,
					subOper.getIdentifier(), OperationSubActionExecType.NORMAL,
					transExpSet);
			Defect defect = new Defect(verify);
			defect.setDefectType(DefectType.SEMANTIC_SPECIFIC_DEFECT);
			HlclProgram modelToVerify = new HlclProgram();
			modelToVerify.addAll(verify);
			modelToVerify.addAll(relaxed);
			modelToVerify.addAll(fixed);
			iniSTime = System.currentTimeMillis();
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

	private List<String> getFreeIdentifiers(Map<String, Number> currentResult) {
		List<String> out = new ArrayList<String>();
		for (String id : currentResult.keySet()) {
			String[] o = id.split("_");
			if (o[1].equals("Sel") && currentResult.get(id).floatValue() == 0)
				out.add(id);

		}
		return out;
	}

	private List<String> getFreeIdentifiers(Map<String, Number> currentResult,
			String coreOutAttribute, String operOutAttribute) {
		List<String> out = new ArrayList<String>();
		for (String id : currentResult.keySet()) {
			String[] o = id.split("_");
			if (o[1].equals(coreOutAttribute)
					&& currentResult.get(id).floatValue() == 0) {
				String outId = id.replace(coreOutAttribute, operOutAttribute);
				out.add(outId);
			}

		}
		return out;
	}

	private List<String> getNewIdentifiers(Map<String, Number> currentResult,
			Map<String, Number> lastResult) {
		List<String> out = new ArrayList<String>();
		for (String id : currentResult.keySet()) {
			String[] o = id.split("_");
			if (o[1].equals("Sel") && lastResult.get(id).floatValue() == 0
					&& currentResult.get(id).floatValue() == 1)
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
