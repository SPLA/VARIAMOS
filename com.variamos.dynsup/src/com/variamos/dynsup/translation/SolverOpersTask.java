package com.variamos.dynsup.translation;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.common.core.utilities.StringUtils;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.model.ModelExpr;
import com.variamos.dynsup.model.OpersIOAttribute;
import com.variamos.dynsup.model.OpersSubOperation;
import com.variamos.dynsup.types.OpersComputationType;
import com.variamos.dynsup.types.OpersOpType;
import com.variamos.dynsup.types.OpersSubOpExecType;
import com.variamos.dynsup.types.OpersSubOpType;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.core.HlclUtil;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.io.core.importExport.ExportConfiguration;
import com.variamos.reasoning.defectAnalyzer.core.CauCosAnayzer;
import com.variamos.reasoning.defectAnalyzer.core.DefectsVerifier;
import com.variamos.reasoning.defectAnalyzer.core.IntCauCosAnalyzer;
import com.variamos.reasoning.defectAnalyzer.core.IntDefectsVerifier;
import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;
import com.variamos.reasoning.defectAnalyzer.model.defects.DefectTypeEnum;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.CauCos;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.DefectAnalyzerModeEnum;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.Diagnosis;
import com.variamos.reasoning.fragop.Fragmental;
import com.variamos.reasoning.medic.model.diagnoseAlgorithm.MinimalSetsDFSIterationsHLCL;
import com.variamos.reasoning.medic.model.graph.NodeConstraintHLCL;
import com.variamos.reasoning.medic.model.graph.NodeVariableHLCL;
import com.variamos.reasoning.medic.model.graph.VertexHLCL;
import com.variamos.reasoning.util.LogParameters;
import com.variamos.solver.core.SWIPrologSolver;
import com.variamos.solver.model.SolverSolution;

/**
 * A class to support SwingWorkers for solver execution tasks using the semantic
 * operations. Originally copied from
 * com.variamos.perspsupport.perspmodel.SolverTasks. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-12-22
 * @see com.variamos.dynsup.translation.SolverTasks
 */

public class SolverOpersTask extends SwingWorker<Void, Void> {
	private ModelExpr2HLCL refas2hlcl;
	private HlclProgram configHlclProgram;
	private boolean invalidConfigHlclProgram;
	private List<String> outVariables = new ArrayList<String>();
	private List<String> defectsFreeIdsName = null;

	private boolean test;
	private long task = 0;
	private InstElement element;
	private List<String> operationsNames;
	private boolean firstSimulExec;
	private boolean reloadDashBoardConcepts = true;
	private boolean showDashboard = false;
	private String completedMessage;
	private String executionTime = "";
	// private List<String> defects;
	private SolverSolution lastConfiguration;
	private String errorTitle = "";
	private String errorMessage = "";
	private boolean update;
	private Component parentComponent;
	private InstanceModel refasModel;
	private String file;
	private ProgressMonitor progressMonitor;
	private boolean next = true;
	private boolean terminated = false;
	private boolean correctExecution = true;
	private int results[] = null;

	public boolean isCorrectExecution() {
		return correctExecution;
	}

	/**
	 * Method that builds what will be executed in the the worker
	 * 
	 * @param progressMonitor
	 * @param refasModel
	 * @param refas2hlcl
	 * @param configHlclProgram
	 * @param firstSimulExec
	 * @param operations
	 * @param lastConfiguration
	 * @param filename
	 */

	public SolverOpersTask(ProgressMonitor progressMonitor, InstanceModel refasModel, ModelExpr2HLCL refas2hlcl,
			HlclProgram configHlclProgram, boolean firstSimulExec, List<String> operations,
			SolverSolution lastConfiguration, String filename) {

		this.refasModel = refasModel;
		this.progressMonitor = progressMonitor;
		this.refas2hlcl = refas2hlcl;
		this.configHlclProgram = configHlclProgram;
		this.firstSimulExec = firstSimulExec;
		this.reloadDashBoardConcepts = false;
		this.showDashboard = false;
		this.update = false;
		this.operationsNames = operations;
		this.lastConfiguration = lastConfiguration;
		this.file = filename;
	}

	public SolverOpersTask(ProgressMonitor progressMonitor, String operationIdentifier, InstanceModel refasModel,
			ModelExpr2HLCL refas2hlcl, String file) {
		this.progressMonitor = progressMonitor;
		this.refasModel = refasModel;
		this.refas2hlcl = refas2hlcl;
		// this.file = file;
	}

	public boolean isFirstSimulExec() {
		return firstSimulExec;
	}

	public boolean isReloadDashBoardConcepts() {
		return reloadDashBoardConcepts;
	}

	public void setReloadDashBoardConcepts(boolean reloadDashBoard) {
		this.reloadDashBoardConcepts = reloadDashBoard;
	}

	public boolean isShowDashboard() {
		return showDashboard;
	}

	public void setShowDashboard(boolean showDashBoard) {
		this.showDashboard = showDashBoard;
	}

	public boolean isUpdate() {
		return update;
	}

	public SolverSolution getLastConfiguration() {
		return lastConfiguration;
	}

	@Override
	public Void doInBackground() throws FunctionalException {
		setProgress(0);
		try {
			Thread.sleep(1);
			// Method that executes the operations
			executeOperations();
		} catch (java.lang.UnsatisfiedLinkError e) {
			errorMessage = "Solver not correctly configured";
			errorTitle = "System Configuration Error";
			correctExecution = false;
			// FIXME: Review how to handle here a functional exception
		} catch (InterruptedException ignore) {
		} catch (Exception e) {

			errorMessage = "Solver Execution Problem, try again saving and loading the model.";
			errorTitle = "Verification Error";
			correctExecution = false;
			// FIXME issue#230
			e.printStackTrace();
			throw new FunctionalException(e.getMessage() + " " + FunctionalException.exceptionStacktraceToString(e));
		}
		task = 100;
		setProgress((int) task);
		return null;
	}

	// for dynamic operations
	public boolean saveConfiguration(String file, InstElement operation, InstElement suboper)
			throws InterruptedException, FunctionalException {
		setProgress(1);
		progressMonitor.setNote("Solutions processed: 0");
		Map<String, Map<String, Integer>> elements = refas2hlcl.execExport(progressMonitor, operation, suboper);
		setProgress(95);
		progressMonitor.setNote("Total Solutions processed: " + elements.size());
		List<String> names = new ArrayList<String>();
		if (elements.size() != 0)
			for (String element : elements.get("1").keySet()) {
				if (refasModel.getElement(element) != null)
					names.add((String) refasModel.getElement(element).getInstAttribute("name").getValue());
			}
		ExportConfiguration export = new ExportConfiguration();
		export.exportConfiguration(elements, names, file);
		return true;
	}

	// for dynamic operations
	public int countConfigurations(InstElement operation, InstElement suboper)
			throws InterruptedException, FunctionalException {
		setProgress(1);
		progressMonitor.setNote("Solutions processed: 0");
		int elements = refas2hlcl.execCount(progressMonitor, operation, suboper);
		setProgress(95);
		progressMonitor.setNote("Total Solutions processed: " + elements);
		completedMessage = "Total solutions: " + elements;
		return elements;
	}

	// TODO Modify for dynamic operations
	@Deprecated
	public void configModel() throws InterruptedException, FunctionalException {
		// this.clearNotificationBar();
		refas2hlcl.cleanGUIElements(ModelExpr2HLCL.CONF_EXEC);
		Set<Identifier> freeIdentifiers = null;
		Set<InstElement> elementSubSet = null;
		task = 0;
		long iniTime = 0;
		long endTime = 0;
		iniTime = System.currentTimeMillis();
		if (invalidConfigHlclProgram && element == null) {
			configHlclProgram = refas2hlcl.getHlclProgram("Simul", ModelExpr2HLCL.CONF_EXEC);
			freeIdentifiers = refas2hlcl.getFreeIdentifiers();
		} else {
			freeIdentifiers = new HashSet<Identifier>();
			elementSubSet = new HashSet<InstElement>();
			refas2hlcl.configGraph(progressMonitor, element, elementSubSet, freeIdentifiers, false);
			elementSubSet = new HashSet<InstElement>();
			configHlclProgram = refas2hlcl.configGraph(progressMonitor, element, elementSubSet, freeIdentifiers, true);
			task = 10;
			setProgress((int) task);
		}

		invalidConfigHlclProgram = false;
		TreeMap<String, Number> configuredIdentNames = refas2hlcl.getConfiguredIdentifier(elementSubSet);
		SolverSolution config = new SolverSolution();

		config.setSolverSolution(configuredIdentNames);

		List<String> requiredConceptsNames = new ArrayList<String>();
		List<String> deadConceptsNames = new ArrayList<String>();
		IntDefectsVerifier defectVerifier = new DefectsVerifier(configHlclProgram, parentComponent,
				"Configuring Selected Elements");
		// System.out.println("FREE: " + freeIdentifiers);

		// System.out.println("CONF: " + configuredIdentNames);

		if (freeIdentifiers.size() > 0) {

			List<Defect> requiredConcepts = null;

			requiredConcepts = defectVerifier.getFalseOptionalElements(freeIdentifiers, null, config);
			executionTime += "FalseOpt: " + defectVerifier.getTotalTime() + "["
					+ defectVerifier.getSolverTime() / 1000000 + "]" + " -- ";
			if (requiredConcepts.size() > 0) {
				for (Defect conceptVariable : requiredConcepts) {
					String[] conceptId = conceptVariable.getId().split("_");
					requiredConceptsNames.add(conceptId[0]);
				}

			}

		}
		long falseOTime = defectVerifier.getSolverTime() / 1000000;
		task = 80;
		setProgress((int) task);
		// System.out.println("newSEL: " + requiredConceptsNames);
		refas2hlcl.updateRequiredConcepts(requiredConceptsNames, test);
		if (freeIdentifiers.size() > 0) {
			List<Defect> deadIndetifiersList = null;
			defectVerifier.resetTime();
			deadIndetifiersList = defectVerifier.getDeadElements(freeIdentifiers, null, config);
			executionTime += "Dead: " + defectVerifier.getTotalTime() + "[" + defectVerifier.getSolverTime() / 1000000
					+ "]" + " -- ";
			if (deadIndetifiersList.size() > 0) {
				for (Defect conceptVariable : deadIndetifiersList) {
					String[] conceptId = conceptVariable.getId().split("_");
					deadConceptsNames.add(conceptId[0]);
				}

			}

		}

		task = 100;
		setProgress((int) task);

		System.out.println("newNOTAV: " + deadConceptsNames);
		refas2hlcl.updateDeadConfigConcepts(deadConceptsNames, test);

		endTime = System.currentTimeMillis();
		executionTime += "ConfigExec: " + (endTime - iniTime) + "["
				+ (falseOTime + defectVerifier.getSolverTime() / 1000000) + "]" + " -- ";
	}

	// dynamic call implementation

	public void executeOperations() throws FunctionalException {
		update = false;
		long iniTime = System.currentTimeMillis();
		int result = 0;
		setProgress(10);
		lastConfiguration = null;
		while (!terminated) { // use the same task for simulation iterations
			if (!next) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// FIXME issue#230
					throw new FunctionalException(FunctionalException.exceptionStacktraceToString(e));
				}
				continue;
			}
			next = false;

			resetFreeIdentifiers();
			// for each operation in the list of operation names, the list of operation
			// names is a parameter
			for (String operationName : operationsNames) {
				// operationObj is the hlcl program regarding the operation name
				// the call returns the object with the id
				InstElement operationObj = refas2hlcl.getRefas().getSyntaxModel().getOperationalModel()
						.getElement(operationName);

				Set<InstElement> suboperationsObjs = new TreeSet<InstElement>();
				Map<String, InstElement> instsuboperations = new HashMap<String, InstElement>();
				// Auto sorting with treeset
				String operType = (String) operationObj // operType is the type of the operation
						.getInstAttributeValue("operType");
				String computationalType = (String) operationObj // computationalType
						.getInstAttributeValue("compType");
				boolean computationalAnalysis = false;
				if (operType.equals(StringUtils.formatEnumValue(OpersOpType.Computational_Analysis.toString()))) {
					computationalAnalysis = true;
					results = new int[2];
				}
				int subOperIndex = 0;
				// to obtain the sub operations from operations (they may be more than one)
				for (InstElement operpair : operationObj.getTargetRelations()) {
					InstElement suboper = operpair.getTargetRelations().get(0);
					instsuboperations.put(suboper.getIdentifier(), suboper);
					suboperationsObjs.add(suboper);
				}
				result = 0;
				InstElement lastSubOper = null;
				// for each suboperation in an operation
				for (InstElement suboper : suboperationsObjs) {
					lastSubOper = suboper;
					// stop when the result is -1 or the operation is of type Validation
					if (result == -1 && operationObj.getInstAttributeValue("operType").equals("Validation"))
						break;
					try {
						// Validation operations
						// System.out.println(((String) suboper
						// .getInstAttributeValue("type")));
						// the type of the sub-operation
						String type = (String) suboper.getInstAttributeValue("type");
						// if the dashboard is visible or not
						boolean showDashboard = (boolean) suboper.getInstAttributeValue("showDashboard");
						// obtain the message of the operation when there is a string defined by the
						// user
						if (suboper.getInstAttributeValue("completedMessage") != null
								&& !((String) suboper.getInstAttributeValue("completedMessage")).equals(""))
							completedMessage = (String) suboper.getInstAttributeValue("completedMessage");
						boolean simul = false;
						if (showDashboard) {
							this.showDashboard = showDashboard;
						}
						// determine the type of the operation and call the method implementing the
						// operation

						// if type Number_Solutions
						if (type.equals(StringUtils.formatEnumValue(OpersSubOpType.Number_Solutions.toString()))) {
							if (computationalAnalysis) {
								results[subOperIndex++] = countConfigurations(operationObj, suboper);
								result = 0;
							} else
								countConfigurations(operationObj, suboper); // Method that calls the solver and obtains
																			// the amount of solutions
						}
						// type is Export_Solutions
						else if (type.equals(StringUtils.formatEnumValue(OpersSubOpType.Export_Solutions.toString()))) {
							saveConfiguration(file, operationObj, suboper);
						}
						// type is First_Solution or Iterate
						else if (type.equals(StringUtils.formatEnumValue(OpersSubOpType.First_Solution.toString()))
								|| type.equals(
										StringUtils.formatEnumValue(OpersSubOpType.Iterate_Solutions.toString()))) {
							simul = true;
							if (lastConfiguration == null || firstSimulExec) {
								result = refas2hlcl.execute(progressMonitor, ModelExpr2HLCL.ONE_SOLUTION, operationObj,
										instsuboperations.get(suboper.getIdentifier())); // type

							} else {
								if (type.equals(
										StringUtils.formatEnumValue(OpersSubOpType.Iterate_Solutions.toString()))) {
									this.reloadDashBoardConcepts = false;
									result = refas2hlcl.execute(progressMonitor, ModelExpr2HLCL.NEXT_SOLUTION,
											operationObj, instsuboperations.get(suboper.getIdentifier())); // type
								} else
									continue;
							}
						}
						// Verification operations with CauCos
						else if (type
								.equals(StringUtils.formatEnumValue(OpersSubOpType.Multi_Verification.toString()))) {
							String errorHint = (String) suboper.getInstAttributeValue("errorHint");
							String modeStr = suboper.getInstAttributeValue("mode").toString();
							boolean updateOutAttributes = (boolean) suboper
									.getInstAttributeValue("updateOutAttributes");
							String outAttribute = (String) suboper.getInstAttributeValue("outAttribute");
							boolean natLanguage = (boolean) suboper.getInstAttributeValue("useNatLangExprDesc");
							DefectAnalyzerModeEnum mode = null;
							for (DefectAnalyzerModeEnum m : DefectAnalyzerModeEnum.values()) {
								if (StringUtils.formatEnumValue(m.toString()).equals(modeStr)) {
									mode = m;
									break;
								}
							}
							boolean indivRelExp = (boolean) suboper.getInstAttributeValue("indivRelExp");
							boolean indivVerExp = (boolean) suboper.getInstAttributeValue("indivVerExp");
							List<OpersIOAttribute> outAttributes = ((OpersSubOperation) suboper.getEdOperEle())
									.getOutAttributes();
							result = cauCos(0, operationObj, suboper, errorHint, outAttributes, updateOutAttributes,
									outAttribute, operationsNames.size(), mode, indivVerExp, indivRelExp, natLanguage);
							terminated = true;
						}

						// Verification operations with DefectsVerifier
						else if (type.equals(StringUtils.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif.toString()))
								|| type.equals(StringUtils
										.formatEnumValue(OpersSubOpType.UpdModel_Defects_Verif.toString()))) {
							String method = (String) suboper.getInstAttributeValue("defectType");
							String errorHint = (String) suboper.getInstAttributeValue("errorHint");
							String outAttribute = (String) suboper.getInstAttributeValue("outAttribute");
							boolean reuseFreeIds = (boolean) suboper.getInstAttributeValue("reuseFreeIds");
							boolean updateFreeIds = (boolean) suboper.getInstAttributeValue("updateFreeIds");
							String coreOperName = null;
							InstElement coreOperation = null;
							boolean updateOutAttributes = false;
							List<IntBooleanExpression> constraitsToVerifyRedundacies = null;
							if (type.equals(
									StringUtils.formatEnumValue(OpersSubOpType.IdDef_Defects_Verif.toString()))) {
								coreOperName = (String) suboper.getInstAttributeValue("defectsCoreOper");
								if (coreOperName == null)
									coreOperName = "Update Core Elements";
								coreOperation = refas2hlcl.getRefas().getSyntaxModel().getOperationalModel()
										.getVertexByName(coreOperName);
								if (method.equals("getRedundancies") || method.equals("getFalsePLs")) {
									constraitsToVerifyRedundacies = refas2hlcl.getHlclProgram(operationObj,
											suboper.getIdentifier(), OpersSubOpExecType.TOVERIFY, null);
								}
							}
							updateOutAttributes = (boolean) suboper.getInstAttributeValue("updateOutAttributes");

							List<OpersIOAttribute> outAttributes = ((OpersSubOperation) suboper.getEdOperEle())
									.getOutAttributes();
							result = defectsVerifier(operationObj, suboper, method, errorHint, outAttributes,
									operationsNames.size(), reuseFreeIds, updateFreeIds, outAttribute,
									updateOutAttributes, coreOperation, constraitsToVerifyRedundacies);
							terminated = true;
						} 
						/*else if (type
								.equals(StringUtils
										.formatEnumValue(OpersSubOpType.ConfigIntegration
												.toString())))
						{
							result = 0;
							terminated = true;
						}*/
						else if (type
								.equals(StringUtils
										.formatEnumValue(OpersSubOpType.ExecuteIntegration
												.toString())))
						{
							List<Map<String, String>> files = new ArrayList<>();
							Boolean components_found=false;
							
							for (InstElement instE : refasModel.getElements()) {
								Map<String, String> file_map = new HashMap<String, String>();
								boolean value=false;
								String id= (String) instE.getIdentifier();
								if(id.startsWith("E")) {
									List<InstElement> listT = instE.getTargetRelations();
									InstElement instT = listT.get(0);
									String name= (String) instT.getInstAttributeValue("Name");
									Boolean selected= (Boolean) instT.getInstAttributeValue("SelectedToIntegrate");
									if(selected) {
										components_found=true;
										file_map.put("component_folder", name);
										List<InstElement> listS = instE.getSourceRelations();
										InstElement instS = listS.get(0);
										name= (String) instS.getInstAttributeValue("Name");
										file_map.put("ID", name);
										name= (String) instS.getInstAttributeValue("filename");
										file_map.put("filename", name);
										name= (String) instS.getInstAttributeValue("destination");
										file_map.put("destination", name);
										files.add(file_map);
									}
								}
							}
							
							if(components_found) {
								Fragmental.principal(files);
								String found_errors=Fragmental.get_errors();
								if(found_errors.equals("")) {
									completedMessage=found_errors+"!!!Components successfully assembled!!!";
						        }else {
						        	completedMessage=found_errors+"!!!Components assembled with multiple errors!!!";
						        }
							}else {							
								completedMessage="There are not components selected to be assembled";
							}
													
							result = 0;
							terminated = true;
						}
						// TODO modifications by avillota for including MEDIC
						else if (type.equals(StringUtils.formatEnumValue(OpersSubOpType.Medic.toString()))) {

							String errorHint = (String) suboper.getInstAttributeValue("errorHint");
							String outAttribute = (String) suboper.getInstAttributeValue("outAttribute");
							List<OpersIOAttribute> outAttributes = ((OpersSubOperation) suboper.getEdOperEle())
									.getOutAttributes();

							// PONER AQUi LAS INSTRUCCIONES PARA TOMAR LOS INPUTS

							// Call to the method that calls medic
							// result is -1 if something fails,
							// if the model is consistent
							// > 1 if it has inconsistencies
							result = medicExecution(operationObj, suboper, errorHint, outAttributes, outAttribute);

							// the operation should finish
							terminated = true;

						} else {
							result = -1;
						}
						if (result == 0) {
							update = true;
							outVariables = refas2hlcl.getOutVariables(operationName, suboper.getIdentifier());
							if (simul)
								lastConfiguration = refas2hlcl.getConfiguration();
							if (computationalAnalysis) {
								if (!type.equals(
										StringUtils.formatEnumValue(OpersSubOpType.Number_Solutions.toString())))
									results[subOperIndex++] = refas2hlcl.getSingleOutValue(outVariables, suboper);
								if (suboper.getInstAttributeValue("completedMessage") != null
										&& !((String) suboper.getInstAttributeValue("completedMessage")).equals("")) {
									completedMessage = (String) suboper.getInstAttributeValue("completedMessage");
									if (completedMessage.contains("#numerator#"))
										completedMessage = completedMessage.replace("#numerator#", results[0] + "");
									if (completedMessage.contains("#denominator#"))
										completedMessage = completedMessage.replace("#denominator#", results[1] + "");
									if (completedMessage.contains("#result#")) {
										if (results[1] == 0)
											errorMessage = "Division by zero";
										else {
											if (computationalType.equals(StringUtils
													.formatEnumValue(OpersComputationType.Simple_Quotient.toString())))
												completedMessage = completedMessage.replace("#result#",
														(results[0] * 1f) / results[1] + "");
											else if (computationalType.equals(StringUtils.formatEnumValue(
													OpersComputationType.One_Less_Quotient.toString())))
												completedMessage = completedMessage.replace("#result#",
														(1 - (results[0] * 1f) / results[1]) + "");
											else if (computationalType.equals(StringUtils.formatEnumValue(
													OpersComputationType.Quotient_denominator_exp_base_2

															.toString())))
												completedMessage = completedMessage.replace("#result#",
														(results[0] * 1f) / Math.pow(2, results[1]) + "");
										}

									}

								}
							} else {
								refas2hlcl.updateGUIElements(null, outVariables, suboper);
							}
							// messagesArea.setText(refas2hlcl.getText());
							// bringUpTab(mxResources.get("elementSimPropTab"));
							// editPropertiesRefas(editor.lastEditableElement);
							// }
							// correctExecution = true;
							long endTime = System.currentTimeMillis();
							executionTime = this.operationsNames.get(0) + (endTime - iniTime) + "["
									+ (refas2hlcl.getLastExecutionTime() / 1000000) + "]" + " -- ";
							System.out.println(executionTime);
						} else {
							if (result == -1)
								if (firstSimulExec && lastConfiguration == null) {
									errorMessage += (String) suboper.getInstAttributeValue("errorText");
									errorTitle = (String) suboper.getInstAttributeValue("errorTitle");
									correctExecution = false;
									terminated = true;
								} else {
									errorMessage = "No more solutions found";
									errorTitle = "Simulation Message";
									correctExecution = false;
								}
							if (result >= 1)
								if (computationalAnalysis) {
									results[subOperIndex++] = result;
									if (suboper.getInstAttributeValue("completedMessage") != null
											&& !((String) suboper.getInstAttributeValue("completedMessage"))
													.equals("")) {
										completedMessage = (String) suboper.getInstAttributeValue("completedMessage");
										if (completedMessage.contains("#numerator#"))
											completedMessage = completedMessage.replace("#numerator#", results[0] + "");
										if (completedMessage.contains("#denominator#"))
											completedMessage = completedMessage.replace("#denominator#",
													results[1] + "");
										if (completedMessage.contains("#result#")) {
											if (results[1] == 0)
												errorMessage = "Division by zero";
											else {
												if (computationalType.equals(StringUtils.formatEnumValue(
														OpersComputationType.Simple_Quotient.toString())))
													completedMessage = completedMessage.replace("#result#",
															(results[0] * 1f) / results[1] + "");
												else if (computationalType.equals(StringUtils.formatEnumValue(
														OpersComputationType.One_Less_Quotient.toString())))
													completedMessage = completedMessage.replace("#result#",
															(1 - (results[0] * 1f) / results[1]) + "");
												else if (computationalType.equals(StringUtils.formatEnumValue(
														OpersComputationType.Quotient_denominator_exp_base_2
																.toString())))
													completedMessage = completedMessage.replace("#result#",
															(results[0] * 1f) / Math.pow(2, results[1]) + "");
											}

										}

									}
								} else if (firstSimulExec && lastConfiguration == null) {
									outVariables
											.addAll(refas2hlcl.getOutVariables(operationName, suboper.getIdentifier()));
									errorMessage += (String) suboper.getInstAttributeValue("errorMsg");
									if (errorMessage.contains("#number#"))
										errorMessage = errorMessage.replace("#number#", result + "");
									errorTitle = (String) suboper.getInstAttributeValue("errorTitle");
									correctExecution = false;
									// terminated = true;
								} else {
									errorMessage = "No more solutions found";
									errorTitle = "Simulation Message";
									correctExecution = false;
								}
							// terminated = true;
						}

					} catch (Exception e) {
						// FIXME issue#230
						throw new FunctionalException(FunctionalException.exceptionStacktraceToString(e));
					}

					// Only two suboperations allowed for computational analysis
					if (subOperIndex == 2)
						break;
				}
				if (!firstSimulExec && result == 1)
					// Update GUI after first execution, editor is not notify
					// because the task is at 100%
					this.refas2hlcl.updateGUIElements(null, outVariables, lastSubOper);
			}
			task = 100;
			setProgress((int) task);
			this.setProgress(100);

		}

	}

	private void resetFreeIdentifiers() {
		defectsFreeIdsName = null;
	}

	/**
	 * Call to the method that calls medic result is -1 if something fails, 0 if the
	 * model is consistent > 1 if it has inconsistencies
	 * 
	 * @param operation
	 * @param subOper
	 * @param verifHint
	 * @param outAttributes
	 * @param outAttribute
	 * @return
	 * @throws Exception
	 */
	public int medicExecution(InstElement operation, InstElement subOper, String verifHint,
			List<OpersIOAttribute> outAttributes, // pend
			String outAttribute) throws Exception {

		int result = 0;
		TranslationExpressionSet transExpSet = new TranslationExpressionSet(refasModel, operation, null, null);

		Map<IntBooleanExpression, String> table = new HashMap<>();
		HlclProgram program = refas2hlcl.getHlclProgram(operation, subOper.getIdentifier(), OpersSubOpExecType.NORMAL,
				transExpSet, table);

		// use table

		if (program != null) {

			SWIPrologSolver swiSolver = new SWIPrologSolver();
			swiSolver.setHLCLProgram(program); // passing the hlcl program to the solver
			swiSolver.solve(); // This method prepares the solver
			boolean satisfiable = swiSolver.hasSolution(); // Consulting if the solver has one solution

			if (satisfiable) {
				result = 0;
			} else {
				ArrayList<String> inconsistentPath = new ArrayList<String>();
				ArrayList<String> inconsistentMsg = new ArrayList<String>();
				// Log parameters allow the activation of the logManager,
				// to start an execution using a log manager comment line 722 and uncomment line
				// 723
				// using a valid path and a name for the problem
				LogParameters params = new LogParameters();
				// LogParameters params= new LogParameters("/Users/Angela/Test/", "test");
				MinimalSetsDFSIterationsHLCL medic = new MinimalSetsDFSIterationsHLCL(program, params);
				String root = getRoot();
				if (root == null) // if the user did not pick a root, the algorithm starts in the first variable
					root = "CGVariable1_value";
				else // the algorithm starts with the first variable selected
					root += "_value";
				LinkedList<VertexHLCL> output = medic.sourceOfInconsistentConstraints(root, 10);
				// LinkedList<VertexHLCL> output=
				// medic.sourceOfInconsistentConstraints("CGVariable1_value",10);

				for (VertexHLCL vertex : output) {
					if (vertex instanceof NodeVariableHLCL) {
						// Add the variable into the list of IDs to be updated
						// Add the error message

						inconsistentPath.add(vertex.getId().replaceFirst("_value", ""));
						inconsistentMsg.add("variable in the inconsistent path");

						// aÃ±adir la variable a la lista y despuÃ©s una por una las retsricciones
						// unarias asociadas.
						for (NodeConstraintHLCL cons : ((NodeVariableHLCL) vertex).getUnary()) {

							String consId = getIdFromTable(cons.getConstraint(), table).replaceFirst("_value", "");
							// System.out.println(cons.getId() + " id:" + consId );
							inconsistentPath.add(consId);
							inconsistentMsg.add("Constraint in the inconsistent path");
						}

					} else {
						String consId = getIdFromTable(((NodeConstraintHLCL) vertex).getConstraint(), table)
								.replaceFirst("_value", "");
						inconsistentPath.add(consId);
						// System.out.println(vertex.getId() + " id:" + consId );
						inconsistentMsg.add("Constraint in the inconsistent path");
					}
				}
				refas2hlcl.updateErrorMark(inconsistentPath, verifHint, inconsistentMsg);

			}
			return result;
		} else {
			result = -1;
			throw new FunctionalException("The translation is not working properly, the constraint program i empty");
		}

	}

	/**
	 * Method to obtain the Id of a contstraint represented as a HLCL expression
	 * 
	 * @param constraint
	 *            hlcl expression
	 * @param table
	 *            map with the constraints and the Ids
	 * @return
	 */
	private String getIdFromTable(IntBooleanExpression constraint, Map<IntBooleanExpression, String> table) {

		return table.get(constraint);

	}

	private int defectsVerifier(InstElement operation, InstElement subOper, String method, String verifHint,
			List<OpersIOAttribute> outAttributes, int numberOperations, boolean reuseIds, boolean updateIds,
			String outAttribute, boolean updateOutAttributes, InstElement coreOperation,
			List<IntBooleanExpression> constraitsToVerifyRedundacies) throws InterruptedException, FunctionalException {
		int result = 0;
		executionTime = "";

		if (coreOperation == null && !method.equals("getRedundancies") && !method.equals("getFalsePLs"))
			// Update core
			result = defectExecution(operation, subOper, method, outAttributes, numberOperations, outAttribute,
					updateOutAttributes);

		else
			result = defectExecution(operation, subOper, method, verifHint, outAttributes, numberOperations, reuseIds,
					updateIds, outAttribute, updateOutAttributes, coreOperation, constraitsToVerifyRedundacies);

		if (progressMonitor.isCanceled())
			throw (new InterruptedException());
		return result;
	}

	private int cauCos(int type, InstElement operation, InstElement subOper, String verifHint,
			List<OpersIOAttribute> outAttributes, boolean updateOutAttributes, String outAttribute,
			int numberOperations, DefectAnalyzerModeEnum mode, boolean indivVerExp, boolean indivRelExp,
			boolean natLanguage) throws InterruptedException {
		int outResult = 0;
		executionTime = "";

		// type: for future variations on the execution
		String verifElement = operation.getIdentifier();
		long iniTime = System.currentTimeMillis();
		long iniSTime = 0;
		long endSTime = 0;
		try {
			// que es un translation expression set?
			TranslationExpressionSet transExpSet = new TranslationExpressionSet(refasModel, operation, null, null);

			List<IntBooleanExpression> verifyList = refas2hlcl.getHlclProgram(operation, subOper.getIdentifier(),
					OpersSubOpExecType.VERIFICATION, transExpSet);
			HlclProgram relaxedList = refas2hlcl.getHlclProgram(operation, subOper.getIdentifier(),
					OpersSubOpExecType.RELAXABLE, transExpSet);
			List<ModelExpr> relaxedMEList = refas2hlcl.getInstanceExpressions(operation, subOper.getIdentifier(),
					OpersSubOpExecType.RELAXABLE);
			HlclProgram fixedList = refas2hlcl.getHlclProgram(operation, subOper.getIdentifier(),
					OpersSubOpExecType.NORMAL, transExpSet);
			Set<String> outIdentifiersSet = new TreeSet<String>();
			ArrayList<String> outIdentifiersList = new ArrayList<String>();
			String defects = "(";
			HlclProgram modelToVerify = new HlclProgram();
			modelToVerify.addAll(verifyList);
			modelToVerify.addAll(relaxedList);
			modelToVerify.addAll(fixedList);
			IntDefectsVerifier verifier = new DefectsVerifier(modelToVerify);
			// The model has two or more roots
			Defect voidModel = verifier.isVoid();
			Iterator<IntBooleanExpression> verifyIter = verifyList.iterator();
			Iterator<IntBooleanExpression> relaxedIter = relaxedList.iterator();
			ArrayList<String> naturalLanguageHints = new ArrayList<String>();
			if (voidModel != null) {
				List<IntBooleanExpression> verify = verifyList;
				HlclProgram relaxed = relaxedList;
				HlclProgram fixed = fixedList;
				do {
					if (indivVerExp) {
						verify = new ArrayList<IntBooleanExpression>();
						if (verifyIter.hasNext())
							verify.add(verifyIter.next());
					}
					do {
						if (indivRelExp) {
							relaxed = new HlclProgram();
							if (relaxedIter.hasNext()) {
								relaxed.add(relaxedIter.next());
							}
						}
						Defect defect = new Defect(verify);
						defect.setDefectType(DefectTypeEnum.SEMANTIC_SPECIFIC_DEFECT);
						iniSTime = System.currentTimeMillis();
						if (progressMonitor.isCanceled())
							throw (new InterruptedException());

						IntCauCosAnalyzer cauCosAnalyzer = new CauCosAnayzer(parentComponent, verifElement);
						HlclProgram fixedConstraint = new HlclProgram();
						fixedConstraint.addAll(verify);
						fixedConstraint.addAll(fixed);
						Diagnosis result = cauCosAnalyzer.getCauCos(defect, relaxed, fixedConstraint, mode);
						endSTime = System.currentTimeMillis();

						for (CauCos correction : result.getCorrections()) {
							if (progressMonitor.isCanceled())
								throw (new InterruptedException());
							List<IntBooleanExpression> corr = correction.getElements();
							for (IntBooleanExpression expression : corr) {
								if (progressMonitor.isCanceled())
									throw (new InterruptedException());

								ModelExpr modelExpression = relaxedMEList.get(relaxedList.indexOf(expression));
								// .getSemanticExpression();
								// System.out.println(relaxedList
								// .indexOf(expression));
								Set<Identifier> iden = HlclUtil.getUsedIdentifiers(expression);
								Iterator iterIden = iden.iterator();

								if (modelExpression.getElementInstanceId() != null) {
									InstElement ie = refas2hlcl.getRefas()
											.getElement(modelExpression.getElementInstanceId());
									if (ie.getInstAttribute(outAttribute) != null
											&& outIdentifiersSet.add(modelExpression.getElementInstanceId())) {
										if (natLanguage)
											naturalLanguageHints
													.add(modelExpression.getSemanticExpression().getNaturalLangDesc());
										outIdentifiersList.add(modelExpression.getElementInstanceId());
										defects += modelExpression.getElementInstanceId() + ", ";
									}
								}

								while (iterIden.hasNext()) {
									Identifier newIden = (Identifier) iterIden.next();
									String[] o = newIden.getId().split("_");
									InstElement ie = refas2hlcl.getRefas().getElement(o[0]);
									if (ie.getInstAttribute(outAttribute) != null && outIdentifiersSet.add(o[0])) {
										if (natLanguage)
											naturalLanguageHints
													.add(modelExpression.getSemanticExpression().getNaturalLangDesc());
										defects += o[0] + ", ";
										outIdentifiersList.add(o[0]);
									}
								}
							}
						}
						if (!outIdentifiersSet.isEmpty()) {
							defects = defects.substring(0, defects.length() - 2) + ")";
							System.out.println(defects);
							outResult = outIdentifiersSet.size();
						}
					} while (indivRelExp && relaxedIter.hasNext());
				} while (indivVerExp && verifyIter.hasNext());
			} else {
				endSTime = System.currentTimeMillis();
			}
			if (natLanguage)
				refas2hlcl.updateErrorMark(outIdentifiersList, verifElement, naturalLanguageHints);
			else
				refas2hlcl.updateErrorMark(outIdentifiersList, verifElement, verifHint);
			if (updateOutAttributes)
				refas2hlcl.updateGUIElements(outAttributes, null);
		} catch (FunctionalException e) {
			endSTime = System.currentTimeMillis();
			outResult = -1;
		} finally {
			long endTime = System.currentTimeMillis();
			executionTime += verifElement + " Verif.: " + (endTime - iniTime) + "[" + (endSTime - iniSTime) + "]"
					+ " -- ";
		}

		if (progressMonitor.isCanceled())
			throw (new InterruptedException());
		return outResult;
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

	public int defectExecution(InstElement operation, InstElement subOper, String method,
			List<OpersIOAttribute> outAttributes, int numberOperations, String outAttribute,
			boolean updateOutAttributes) throws InterruptedException, FunctionalException {

		HlclFactory f = new HlclFactory();
		long iniTime = System.currentTimeMillis();
		int result = 0;
		// Validate if the model is correct
		// FIXME call with core once, not here

		IntDefectsVerifier defectVerifier = null;
		List<String> freeIdsNames = null;
		List<String> defectsNames = new ArrayList<String>();
		long falseOTime = 0;
		result = refas2hlcl.execute(progressMonitor, 0, operation, subOper);
		if (result == 0) {
			Map<String, Number> currentResult = refas2hlcl.getResult();
			freeIdsNames = getFreeIdentifiers(currentResult, outAttribute, outAttribute);
		} else
			return -1;
		if (freeIdsNames != null) {
			Set<Identifier> freeIdentifiers = new HashSet<Identifier>();
			for (String freeIdentifier : freeIdsNames) {
				if (!freeIdentifier.startsWith("FeatOT"))
					freeIdentifiers.add(f.newIdentifier(freeIdentifier));
			}

			if (freeIdsNames.size() > 0) {

				defectVerifier = new DefectsVerifier(
						refas2hlcl.getHlclProgram(operation, subOper.getIdentifier(), OpersSubOpExecType.NORMAL, null),
						parentComponent, "dynamic verification:" + operation);

				List<Defect> coreIds = null;

				switch (method) {
				case "getFalseOptionalElements":
					coreIds = defectVerifier.getFalseOptionalElements(freeIdentifiers);
					break;
				case "getDeadElements":
					coreIds = defectVerifier.getDeadElements(freeIdentifiers);
					break;
				case "getRedundancies":
					break;
				case "getAllNonAttainableDomains":
					coreIds = defectVerifier.getAllNonAttainableDomains(freeIdentifiers);
					break;
				default:
					throw new FunctionalException();
				}

				falseOTime = defectVerifier.getSolverTime() / 1000000;
				if (coreIds.size() > 0) {
					List<String> newDefectsNames = new ArrayList<String>();
					List<String> newDefectsIds = new ArrayList<String>();
					for (Defect conceptVariable : coreIds) {
						String[] conceptId = conceptVariable.getId().split("_");
						newDefectsNames.add(conceptId[0]);
						newDefectsIds.add(conceptVariable.getId());
					}
					defectsNames.addAll(newDefectsNames);
					freeIdsNames.removeAll(newDefectsIds);
					defectsFreeIdsName = freeIdsNames;
					result = coreIds.size();
				} else if (subOper.getInstAttributeValue("completedMessage") != null
						&& !((String) subOper.getInstAttributeValue("completedMessage")).equals(""))
					completedMessage = (String) subOper.getInstAttributeValue("completedMessage");
				task += 100 / numberOperations;
				setProgress((int) task);

			}
			if (updateOutAttributes)
				refas2hlcl.updateGUIElements(outAttributes, null);
		} else {
			long endTime = System.currentTimeMillis();
			executionTime += element + "Exec: " + (endTime - iniTime) + "["
					+ (refas2hlcl.getLastExecutionTime() / 1000000 + falseOTime
							+ ((defectVerifier == null) ? 0 : defectVerifier.getSolverTime() / 1000000))
					+ "]" + " -- ";
			return -1;
		}
		long endTime = System.currentTimeMillis();
		long defectVerifTime = defectVerifier == null ? 0 : (defectVerifier.getSolverTime() / 1000000);
		executionTime += element + "Exec: " + (endTime - iniTime) + "["
				+ (refas2hlcl.getLastExecutionTime() / 1000000 + falseOTime + defectVerifTime) + "]" + " -- ";
		task = 100 / numberOperations;
		setProgress((int) task);
		return result;
	}

	public int defectExecution(InstElement operation, InstElement subOper, String method, String verifHint,
			List<OpersIOAttribute> outAttributes, int numberOperations, boolean reuseIds, boolean updateIds,
			String outAttribute, boolean updateOutAttributes, InstElement coreOperation,
			List<IntBooleanExpression> constraitsToVerifyRedundacies) throws InterruptedException, FunctionalException {
		HlclFactory f = new HlclFactory();

		// outAttributes
		long iniTime = System.currentTimeMillis();
		int result = 0;
		// Validate if the model is correct
		// FIXME call with core once, not here

		IntDefectsVerifier defectVerifier = null;
		List<String> freeIdsNames = new ArrayList<String>();
		List<String> defectsNames = new ArrayList<String>();
		long falseOTime = 0;

		if (!method.equals("getRedundancies") && !method.equals("getFalsePLs")) {
			InstElement coreSubOper = coreOperation.getTargetRelations().get(0).getTargetRelations().get(0);
			String coreOutAttribute = (String) coreSubOper.getInstAttributeValue("outAttribute");
			boolean updateCoreOutAttribute = (boolean) coreSubOper.getInstAttributeValue("updateOutAttributes");
			if (reuseIds && defectsFreeIdsName != null) {
				freeIdsNames.addAll(defectsFreeIdsName);
			} else {
				result = refas2hlcl.execute(progressMonitor, 0, coreOperation, coreSubOper);
				if (result == 0) {
					// FIXME update outAttributes for CoreOper
					if (updateCoreOutAttribute)
						refas2hlcl.updateGUIElements(outAttributes, null);
					Map<String, Number> currentResult = refas2hlcl.getResult();
					freeIdsNames = getFreeIdentifiers(currentResult, coreOutAttribute, outAttribute);
				} else
					return -1;
			}
		}
		if (freeIdsNames != null || method.equals("getRedundancies") || method.equals("getFalsePLs")) {
			Set<Identifier> freeIdentifiers = new HashSet<Identifier>();
			if (!method.equals("getRedundancies") && !method.equals("getFalsePLs"))
				for (String freeIdentifier : freeIdsNames) {
					if (!freeIdentifier.startsWith("FeatOT"))
						freeIdentifiers.add(f.newIdentifier(freeIdentifier));
				}

			if (freeIdsNames.size() > 0 || method.equals("getRedundancies") || method.equals("getFalsePLs")) {
				defectVerifier = new DefectsVerifier(
						refas2hlcl.getHlclProgram(operation, subOper.getIdentifier(), OpersSubOpExecType.NORMAL, null),
						parentComponent, "dynamic verification:" + operation);

				List<Defect> defects = null;

				switch (method) {
				case "getFalseOptionalElements":
					defects = defectVerifier.getFalseOptionalElements(freeIdentifiers);
					break;
				case "getDeadElements":
					defects = defectVerifier.getDeadElements(freeIdentifiers);
					break;
				case "getRedundancies":
					// TODO add this code to an option to see redundant
					// instead of
					// non-redundant - parentOper uses this function

					defects = defectVerifier.getRedundancies(constraitsToVerifyRedundacies);
					// for (Defect defect : defects)
					// constraitsToVerifyRedundacies.removeAll(defect
					// .getVerificationExpressions());
					// defects.clear();
					// if (constraitsToVerifyRedundacies.size() > 0) {
					// List<String> newDefectsNames = new
					// ArrayList<String>();
					// List<String> newDefectsIds = new ArrayList<String>();
					// for (BooleanExpression conceptVariable :
					// constraitsToVerifyRedundacies) {
					// // FIXME better support for expression and other
					// // fields
					// String[] conceptId = conceptVariable.toString()
					// .split("_");
					// conceptId = conceptId[0].toString().split("=");
					// newDefectsNames
					// .add(conceptId[conceptId.length - 1]);
					// newDefectsIds
					// .add(conceptId[conceptId.length - 1]
					// + "_Sel");
					// }
					// defectsNames.addAll(newDefectsNames);
					// freeIdsNames.removeAll(newDefectsIds);
					// if (updateIds)
					// defectsFreeIdsName = freeIdsNames;
					// result = defects.size();
					// }
					break;
				case "getFalsePLs":
					// TODO add this code to an option to see redundant
					// instead of
					// non-redundant - parentOper uses this function

					defects = defectVerifier.getFalsePLs(constraitsToVerifyRedundacies);
					break;
				case "getAllNonAttainableDomains":
					defects = defectVerifier.getAllNonAttainableDomains(freeIdentifiers);
					break;
				default:
					throw new FunctionalException();
				}

				falseOTime = defectVerifier.getSolverTime() / 1000000;
				if (defects.size() > 0) {
					Set<String> newDefectsNames = new HashSet<String>();
					List<String> newDefectsIds = new ArrayList<String>();
					for (Defect conceptVariable : defects) {
						if (method.equals("getRedundancies") || method.equals("getFalsePLs")) {
							String[] conceptId = conceptVariable.toString().split("_");
							conceptId = conceptId[0].toString().split("=");
							newDefectsNames.add(conceptId[conceptId.length - 1]);
							newDefectsIds.add(conceptId[conceptId.length - 1] + "_Sel");

						} else {
							String[] conceptId = conceptVariable.getId().split("_");
							newDefectsNames.add(conceptId[0]);
							newDefectsIds.add(conceptVariable.getId());
						}
					}
					defectsNames.addAll(newDefectsNames);
					freeIdsNames.removeAll(newDefectsIds);
					if (updateIds)
						defectsFreeIdsName = freeIdsNames;
					result = defects.size();
				} else if (subOper.getInstAttributeValue("completedMessage") != null
						&& !((String) subOper.getInstAttributeValue("completedMessage")).equals(""))
					completedMessage = (String) subOper.getInstAttributeValue("completedMessage");
				task += 100 / numberOperations;
				setProgress((int) task);
				System.out.println(defectsNames);
				refas2hlcl.updateErrorMark(defectsNames, operation.getIdentifier(), verifHint);

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
			executionTime += element + "Exec: " + (endTime - iniTime) + "["
					+ (refas2hlcl.getLastExecutionTime() / 1000000 + falseOTime
							+ ((defectVerifier == null) ? 0 : defectVerifier.getSolverTime() / 1000000))
					+ "]" + " -- ";
			return -1;
		}
		// updateObjects();
		long endTime = System.currentTimeMillis();
		long defectVerifTime = defectVerifier == null ? 0 : (defectVerifier.getSolverTime() / 1000000);
		executionTime += element + "Exec: " + (endTime - iniTime) + "["
				+ (refas2hlcl.getLastExecutionTime() / 1000000 + falseOTime + defectVerifTime) + "]" + " -- ";
		task = 100 / numberOperations;
		setProgress((int) task);
		return result;
	}

	private List<String> getFreeIdentifiers(Map<String, Number> currentResult, String coreOutAttribute,
			String operOutAttribute) {
		List<String> out = new ArrayList<String>();
		for (String id : currentResult.keySet()) {
			String[] o = id.split("_");
			if (o[1].equals(coreOutAttribute) && currentResult.get(id).floatValue() == 0) {
				String outId = id.replace(coreOutAttribute, operOutAttribute);
				// if (currentResult.values().contains(outId))
				out.add(outId);
			}

		}
		return out;
	}

	private String getRoot() {
		String root = "";
		for (InstElement instE : refasModel.getElements()) {
			boolean value = false;
			String name = (String) instE.getIdentifier();
			if (instE.getInstAttribute("root") != null) {
				value = (boolean) instE.getInstAttribute("root").getValue();
				// System.out.println(instE.getInstAttribute("root").getValue());
				System.out.println(name + " " + value);
				if (value) {
					root = name;
					break;
				}
			}
		}

		return root;
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

	public List<String> getOutVariables() {
		return outVariables;
	}

	public String getErrorTitle() {
		return errorTitle;
	}

	public String getCompletedMessage() {
		return completedMessage;
	}

	public int[] getResults() {
		return results;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void clearErrorMessage() {
		errorMessage = "";
		errorTitle = "";
		completedMessage = "";
	}

	@Override
	public void done() {
	}
}
