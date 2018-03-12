package com.variamos.dynsup.translation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.ProgressMonitor;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.common.core.exceptions.TechnicalException;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstOverTwoRel;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.model.ModelExpr;
import com.variamos.dynsup.model.OpersIOAttribute;
import com.variamos.dynsup.model.OpersSubOperation;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.staticexpr.ElementExpressionSet;
import com.variamos.dynsup.staticexpr.ModelExpressionSet;
import com.variamos.dynsup.staticexpr.OverTwoElementsExpressionSet;
import com.variamos.dynsup.staticexpr.PairwiseElementExpressionSet;
import com.variamos.dynsup.staticexpr.SingleElementExpressionSet;
import com.variamos.dynsup.staticexprsup.AbstractBooleanExpression;
import com.variamos.dynsup.staticexprsup.AbstractComparisonExpression;
import com.variamos.dynsup.staticexprsup.AbstractExpression;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.OpersSubOpExecType;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.model.Labeling;
import com.variamos.hlcl.model.LabelingOrderEnum;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntExpression;
import com.variamos.hlcl.model.expressions.IntNumericExpression;
import com.variamos.solver.core.IntSolver;
import com.variamos.solver.core.SWIPrologSolver;
import com.variamos.solver.model.ConfigurationOptionsDTO;
import com.variamos.solver.model.SolverSolution;

/**
 * Class to create the Hlcl program. Part of PhD work at University of Paris 1
 * Renamed from Refas2HLCL
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public class ModelExpr2HLCL {
	private HlclFactory f = new HlclFactory();
	private String text;
	private HlclProgram hlclProgram = new HlclProgram();
	private InstanceModel refas;
	private Map<String, Identifier> idMap = new HashMap<>();
	private SolverSolution configuration = new SolverSolution();
	private IntSolver swiSolver;
	private long lastExecutionTime;

	public long getLastExecutionTime() {
		return lastExecutionTime;
	}

	public SolverSolution getConfiguration() {
		return configuration;
	}

	public static final int ONE_SOLUTION = 0, NEXT_SOLUTION = 1;
	// FIXME v1.1 copy change to new version Luisa
	// Only for old static operations
	public static final int DESIGN_EXEC = 0, CONF_EXEC = 1, SIMUL_EXEC = 2,
			CORE_EXEC = 3, VAL_UPD_EXEC = 4, SIMUL_EXPORT = 5, SIMUL_MAPE = 6;

	public ModelExpr2HLCL(InstanceModel refas) {
		this.refas = refas;
		// constraintGroups = new HashMap<String, ElementExpressionSet>();

	}

	@Deprecated
	public List<IntBooleanExpression> rootVerityTest() {
		// HlclProgram hlclProgram = new HlclProgram();
		Map<String, ElementExpressionSet> constraintGroups = new HashMap<String, ElementExpressionSet>();
		createModelExpressions(ModelExpr2HLCL.VAL_UPD_EXEC, constraintGroups);
		List<IntBooleanExpression> modelExpressions = new ArrayList<IntBooleanExpression>();
		for (ElementExpressionSet constraintGroup : constraintGroups.values())
			if (constraintGroup instanceof ModelExpressionSet)
				modelExpressions.addAll(((ModelExpressionSet) constraintGroup)
						.getBooleanExpressionList("Root"));
		return modelExpressions;
		/*
		 * for (BooleanExpression transformation : modelExpressions) {
		 * hlclProgram.add(transformation); } return hlclProgram;
		 */
	}

	// Static implementation
	public List<IntBooleanExpression> verityTest(String element) {
		// HlclProgram hlclProgram = new HlclProgram();
		Map<String, ElementExpressionSet> constraintGroups = new HashMap<String, ElementExpressionSet>();
		createModelExpressions(ModelExpr2HLCL.VAL_UPD_EXEC, constraintGroups);
		List<IntBooleanExpression> modelExpressions = new ArrayList<IntBooleanExpression>();
		for (ElementExpressionSet constraintGroup : constraintGroups.values())
			if (constraintGroup instanceof ModelExpressionSet)
				modelExpressions.addAll(((ModelExpressionSet) constraintGroup)
						.getBooleanExpressionList(element));
		return modelExpressions;
		/*
		 * for (BooleanExpression transformation : modelExpressions) {
		 * hlclProgram.add(transformation); } return hlclProgram;
		 */
	}

	// Static implementation
	public HlclProgram relaxedTest(String operId, String element)
			throws FunctionalException {
		HlclProgram hlclProgram = new HlclProgram();
		Map<String, ElementExpressionSet> constraintGroups = new HashMap<String, ElementExpressionSet>();
		createVertexExpressions(null, ModelExpr2HLCL.VAL_UPD_EXEC,
				constraintGroups);

		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			List<AbstractExpression> relaxableExpressions = constraintGroup
					.getRelaxableExpressionList(element);
			if (relaxableExpressions != null)
				transformations.addAll(relaxableExpressions);
		}
		constraintGroups = new HashMap<String, ElementExpressionSet>();
		createGroupExpressions(operId, null, 4, element, constraintGroups);

		/*
		 * List<AbstractExpression> transformations2 = new
		 * ArrayList<AbstractExpression>(); for (ElementExpressionSet
		 * constraintGroup : constraintGroups.values()) {
		 * List<AbstractExpression> relaxableExpressions = constraintGroup
		 * .getRelaxableExpressionList(element); if (relaxableExpressions !=
		 * null) transformations2.addAll(relaxableExpressions); }
		 */

		for (AbstractExpression transformation : transformations) {
			idMap.putAll(transformation.getIdentifiers(f));
			if (transformation instanceof AbstractBooleanExpression) {
				hlclProgram.add(((AbstractBooleanExpression) transformation)
						.transform(f, idMap));
			} else/*
				 * if (transformation instanceof AbstractComparisonExpression) {
				 * hlclProgram.add(((AbstractComparisonExpression)
				 * transformation) .transform(f, idMap)); } else
				 */{
				hlclProgram.add(((AbstractComparisonExpression) transformation)
						.transform(f, idMap));
			}

		}
		return hlclProgram;
	}

	// Static implementation
	public HlclProgram compulsoryTest(String operId, String element)
			throws FunctionalException {
		HlclProgram hlclProgram = new HlclProgram();
		Map<String, ElementExpressionSet> constraintGroups = new HashMap<String, ElementExpressionSet>();
		createVertexExpressions(null, 4, constraintGroups);

		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			List<AbstractExpression> compulsoryExpressions = constraintGroup
					.getCompulsoryExpressionList(element);
			if (compulsoryExpressions != null)
				transformations.addAll(compulsoryExpressions);
		}

		constraintGroups = new HashMap<String, ElementExpressionSet>();
		createEdgeExpressions(operId, null, 4, constraintGroups);

		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			List<AbstractExpression> compulsoryExpressions = constraintGroup
					.getCompulsoryExpressionList(element);
			if (compulsoryExpressions != null)
				transformations.addAll(compulsoryExpressions);
		}
		constraintGroups = new HashMap<String, ElementExpressionSet>();
		createGroupExpressions(operId, null, 4, element, constraintGroups);

		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			List<AbstractExpression> compulsoryExpressions = constraintGroup
					.getCompulsoryExpressionList(element);
			if (compulsoryExpressions != null)
				transformations.addAll(compulsoryExpressions);
		}

		for (AbstractExpression transformation : transformations) {
			idMap.putAll(transformation.getIdentifiers(f));
			if (transformation instanceof AbstractBooleanExpression) {
				hlclProgram.add(((AbstractBooleanExpression) transformation)
						.transform(f, idMap));
			} else if (transformation instanceof AbstractComparisonExpression) {
				hlclProgram.add(((AbstractComparisonExpression) transformation)
						.transform(f, idMap));
			} else {
				hlclProgram.add(((AbstractComparisonExpression) transformation)
						.transform(f, idMap));
			}

		}
		return hlclProgram;
	}

	@Deprecated
	public HlclProgram rootRelaxedTest() throws FunctionalException {
		HlclProgram hlclProgram = new HlclProgram();

		Map<String, ElementExpressionSet> constraintGroups = new HashMap<String, ElementExpressionSet>();
		createVertexExpressions(null, 0, constraintGroups);

		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			List<AbstractExpression> relaxableExpressions = constraintGroup
					.getRelaxableExpressionList("Root");
			if (relaxableExpressions != null)
				transformations.addAll(relaxableExpressions);
		}

		for (AbstractExpression transformation : transformations) {
			idMap.putAll(transformation.getIdentifiers(f));
			if (transformation instanceof AbstractBooleanExpression) {
				hlclProgram.add(((AbstractBooleanExpression) transformation)
						.transform(f, idMap));
			} else if (transformation instanceof AbstractComparisonExpression) {
				hlclProgram.add(((AbstractComparisonExpression) transformation)
						.transform(f, idMap));
			} else {
				hlclProgram.add(((AbstractComparisonExpression) transformation)
						.transform(f, idMap));
			}

		}
		return hlclProgram;
	}

	/**
	 * Create a new HlclProgram with the expression of all concepts and
	 * relations and calls SWIProlog to return a solution or all solutions
	 * 
	 * @throws FunctionalException
	 */

	// Static call without TranslationExpressionSet
	public HlclProgram getHlclProgram(String operId, int execType)
			throws FunctionalException {
		return getHlclProgram(operId, execType, null);
	}

	// Dynamic call with TranslationExpressionSet
	public HlclProgram getHlclProgram(InstElement operation,
			String subOperation, OpersSubOpExecType operExecType,
			TranslationExpressionSet transExpSet) throws FunctionalException {
		if (transExpSet == null)
			transExpSet = new TranslationExpressionSet(refas, operation, null,
					null);

		HlclProgram hlclProgram = new HlclProgram();
		String operationName = operation.getIdentifier();

		Map<String, ElementExpressionSet> constraintGroups = new HashMap<String, ElementExpressionSet>();

		transExpSet.addExpressions(refas, null, subOperation, operExecType);
		// for (Expression exp : transExpSet.getHLCLExpressions(subOperation +
		// "-"
		// + operExecType)) {
		// System.out.println(exp + exp.toString());
		// }
		// List<Labeling> labelings =
		// transExpSet.getLabelings(refas, subOperation, operExecType);
		constraintGroups.put(operationName, transExpSet);
		fillHlclProgram(operationName, subOperation, operExecType, hlclProgram,
				constraintGroups);
		return hlclProgram;
	}

	public List<ModelExpr> getInstanceExpressions(InstElement operation,
			String subOperation, OpersSubOpExecType operExecType) {
		TranslationExpressionSet transExpSet = new TranslationExpressionSet(
				refas, operation, null, null);
		transExpSet.addExpressions(refas, null, subOperation, operExecType);
		List<ModelExpr> ts = transExpSet.getInstanceExpressions(subOperation
				+ "-" + operExecType);

		return ts;
	}

	// Static call
	public HlclProgram getHlclProgram(String operId, int execType,
			InstElement instElement) throws FunctionalException {
		HlclProgram hlclProgram = new HlclProgram();

		Map<String, ElementExpressionSet> constraintGroups;
		constraintGroups = new HashMap<String, ElementExpressionSet>();

		String elementIdentifier = null;
		if (instElement == null)
			createModelExpressions(execType, constraintGroups);
		else
			elementIdentifier = instElement.getIdentifier();
		if (instElement == null || instElement instanceof InstConcept
				|| instElement instanceof InstOverTwoRel)
			createVertexExpressions(elementIdentifier, execType,
					constraintGroups);

		if (instElement == null || instElement instanceof InstPairwiseRel)
			createEdgeExpressions(operId, elementIdentifier, execType,
					constraintGroups);
		// Previous call to createEdgeExpressions is required to fill the
		// attribute names for createGroupExpressions

		if (instElement == null || instElement instanceof InstOverTwoRel)
			createGroupExpressions(operId, elementIdentifier, execType, operId,
					constraintGroups);

		fillHlclProgram(operId, null, null, hlclProgram, constraintGroups);
		return hlclProgram;
	}

	// Static and Dynamic calls
	private void fillHlclProgram(String element, String subOperation,
			OpersSubOpExecType operExecType, HlclProgram hlclProgram,
			Map<String, ElementExpressionSet> constraintGroups)
			throws FunctionalException {
		List<AbstractExpression> staticTransformations = new ArrayList<AbstractExpression>();
		List<IntBooleanExpression> modelExpressions = new ArrayList<IntBooleanExpression>();

		// Static call without TranslationExpressionSet
		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			if (constraintGroup instanceof SingleElementExpressionSet) {
				if (((SingleElementExpressionSet) constraintGroup)
						.getBooleanExpression(element) != null) {
					modelExpressions
							.add(((SingleElementExpressionSet) constraintGroup)
									.getBooleanExpression(element));
				}
			}
			if (constraintGroup instanceof ModelExpressionSet) {
				if (((ModelExpressionSet) constraintGroup)
						.getBooleanExpressionList(element) != null) {
					modelExpressions
							.addAll(((ModelExpressionSet) constraintGroup)
									.getBooleanExpressionList(element));
				}
			} else {
				if (constraintGroup.getVerificationExpressionsList(element) != null)
					staticTransformations.addAll(constraintGroup
							.getVerificationExpressionsList(element)); // Not
																		// used
				if (constraintGroup.getRelaxableExpressionList(element) != null)
					staticTransformations.addAll(constraintGroup
							.getRelaxableExpressionList(element));
				if (constraintGroup.getCompulsoryExpressionList(element) != null)
					staticTransformations.addAll(constraintGroup
							.getCompulsoryExpressionList(element));
				if (element.equals("") || element.equals("Simul"))
					staticTransformations.addAll(constraintGroup
							.getElementExpressions());
			}
		}

		for (IntBooleanExpression modelExpression : modelExpressions) {
			hlclProgram.add(modelExpression);
		}

		// Dynamic call with TranslationExpressionSet
		// TODO a single constraintGroup for dynamic calls
		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			if (constraintGroup instanceof TranslationExpressionSet) {
				// Get instance and low expressions
				HlclProgram ts = ((TranslationExpressionSet) constraintGroup)
						.getHlCLProgramExpressions(subOperation + "-"
								+ operExecType);
				if (ts != null) {
					hlclProgram.addAll(ts);
				}
				ts = ((TranslationExpressionSet) constraintGroup)
						.getLiteralExpressions(subOperation + "-"
								+ operExecType);
				if (ts != null) {
					hlclProgram.addAll(ts);
				}
			}
		}

		for (AbstractExpression staticTransformation : staticTransformations) {
			// System.out.println(staticTransformation.expressionStructure());
			idMap.putAll(staticTransformation.getIdentifiers(f));
			if (staticTransformation instanceof AbstractBooleanExpression) {
				hlclProgram
						.add(((AbstractBooleanExpression) staticTransformation)
								.transform(f, idMap));
			} else if (staticTransformation instanceof AbstractComparisonExpression) {
				hlclProgram
						.add(((AbstractComparisonExpression) staticTransformation)
								.transform(f, idMap));
			} else {
				hlclProgram
						.add(((AbstractComparisonExpression) staticTransformation)
								.transform(f, idMap));
			}

		}
	}

	public IntNumericExpression getSumExpression(InstElement last,
			Iterator<InstElement> iterVertex, String attributeName) {
		if (iterVertex.hasNext()) {
			InstElement instVertex = iterVertex.next();
			if (last.getInstAttribute(attributeName) != null
					&& this.validateConceptType(last, "GeneralConcept")
					&& last.getInstAttribute("Active").getAsBoolean() == true)
				return f.sum(
						f.newIdentifier(last.getIdentifier() + "_"
								+ attributeName),
						getSumExpression(instVertex, iterVertex, attributeName));
			else
				return getSumExpression(instVertex, iterVertex, attributeName);
		} else if (this.validateConceptType(last, "GeneralConcept")
				&& last.getInstAttribute("Active").getAsBoolean() == true)
			return f.newIdentifier(last.getIdentifier() + "_" + attributeName);
		else
			return f.number(0);

	}

	public List<String> getOutVariables(String operation, String subAction) {
		List<String> out = new ArrayList<String>();
		List<InstElement> operActions = refas.getOperationalModel()
				.getVariabilityVertex("OpMOperation");
		InstElement operAction = null;
		for (InstElement oper : operActions) {
			if (oper.getIdentifier().equals(operation)) {
				operAction = oper;
				break;
			}
		}
		for (InstElement rel : operAction.getTargetRelations()) {
			InstElement subOper = rel.getTargetRelations().get(0);
			if (subOper.getIdentifier().equals(subAction))
				for (OpersIOAttribute att : ((OpersSubOperation) subOper
						.getEdOperEle()).getOutAttributes())
					out.add(att.getAttributeId());
		}
		return out;
	}

	// dynamic call implementation 0 OK, -1 General Error +1 Specific Error
	// (general)
	public int execute(ProgressMonitor progressMonitor, int solutions,
			InstElement operation, InstElement suboper)
			throws InterruptedException, FunctionalException {
		lastExecutionTime = 0;
		if (solutions == 0 || swiSolver == null) {
			text = "";
			configuration = new SolverSolution();
			// FIXME: execute for all sub-operations exp types?

			TranslationExpressionSet transExpSet = new TranslationExpressionSet(
					refas, operation, null, null);
			HlclProgram exp = getHlclProgram(operation,
					suboper.getIdentifier(), OpersSubOpExecType.NORMAL,
					transExpSet);
			if (exp.size() >= 1) {

				hlclProgram = exp;
				List<Labeling> labelings = transExpSet.getLabelings(refas,
						suboper.getIdentifier(), null);
				// Start Execution Model
				swiSolver = new SWIPrologSolver(hlclProgram);
				if (progressMonitor != null && progressMonitor.isCanceled())
					throw (new InterruptedException());
				try {
					ConfigurationOptionsDTO configurationOptions = new ConfigurationOptionsDTO();
					// FIXME support types other than normal
					configurationOptions.setLabelings(labelings);
					configurationOptions.setOrder(true);

					configurationOptions.setStartFromZero(true);

					swiSolver.solve(new SolverSolution(), configurationOptions);

					lastExecutionTime = swiSolver.getLastExecutionTime();
				} catch (Exception ex) {
					// FIXME issue#230
					throw new FunctionalException(
							FunctionalException.exceptionStacktraceToString(ex));
				}
			} else
				return 1;
		}

		if (progressMonitor != null && progressMonitor.isCanceled())
			return -1;

		if (solutions == 0 || solutions == 1) {
			if (configuration != null) {
				try {
					configuration = swiSolver.getSolution();
				} catch (TechnicalException e) {

					// FIXME issue#230
					throw new FunctionalException(
							"Prolog Exception"
									+ e.getMessage()
									+ " "
									+ FunctionalException
											.exceptionStacktraceToString(e));

				}
				lastExecutionTime += swiSolver.getLastExecutionTime();
				if (configuration == null)
					return -1;
			}
		} else
			throw new FunctionalException("Solution parameter not supported");
		return 0;
	}

	// Dynamic implementation to export
	public Map<String, Map<String, Integer>> execExport(
			ProgressMonitor progressMonitor, InstElement operation,
			InstElement suboper) throws InterruptedException,
			FunctionalException {
		int iter = 0;
		Map<String, Map<String, Integer>> elements = new TreeMap<String, Map<String, Integer>>();
		elements = new HashMap<String, Map<String, Integer>>();
		int result = 0;
		boolean first = true;
		int cont = 0;
		while (result == 0 && !progressMonitor.isCanceled()) {
			progressMonitor.setNote("Solutions processed: " + cont++
					+ "(total unknown)");
			if (first) {
				result = execute(progressMonitor, ModelExpr2HLCL.ONE_SOLUTION,
						operation, suboper);
				first = false;
			} else
				result = execute(progressMonitor, ModelExpr2HLCL.NEXT_SOLUTION,
						operation, suboper);
			if (result == 0 && !progressMonitor.isCanceled()) {
				String outAttribute = (String) suboper
						.getInstAttributeValue("outAttribute");
				updateGUIElements(null, null, null);
				Map<String, Integer> newMap = new TreeMap<String, Integer>();
				for (InstElement instVertex : refas
						.getVariabilityVertexCollection()) {
					if (instVertex.getInstAttribute("exportOnConfig") != null
							&& instVertex.getInstAttribute("exportOnConfig")
									.getAsBoolean()) {
						String instId = instVertex.getIdentifier();
						if (instVertex.getIdentifier().contains("Variable")) {
							Object oo = instVertex.getInstAttribute("value")
									.getValue();
							Integer o = null;
							if (oo instanceof Integer) {
								o = (Integer) instVertex.getInstAttribute(
										"value").getValue();

							} else {
								o = Integer.valueOf((String) instVertex
										.getInstAttribute("value").getValue());
							}

							newMap.put(instId, o);
						} else {
							Boolean o = (Boolean) instVertex.getInstAttribute(
									outAttribute).getValue();
							Integer integer;
							if (o.booleanValue())
								integer = 1;
							else
								integer = 0;
							newMap.put(instId, integer);
						}
					}
				}
				iter++;
				elements.put(iter + "", newMap);
			}
		}
		return elements;
	}

	// Dynamic implementation to export
	public int execCount(ProgressMonitor progressMonitor,
			InstElement operation, InstElement suboper)
			throws InterruptedException, FunctionalException {
		int iter = 0;
		Map<String, Map<String, Integer>> elements = new TreeMap<String, Map<String, Integer>>();
		elements = new HashMap<String, Map<String, Integer>>();
		int result = 0;
		boolean first = true;
		int cont = 0;
		while (result == 0 && !progressMonitor.isCanceled()) {
			progressMonitor.setNote("Solutions processed: " + cont++
					+ "(total unknown)");
			if (first) {
				result = execute(progressMonitor, ModelExpr2HLCL.ONE_SOLUTION,
						operation, suboper);
				first = false;
			} else
				result = execute(progressMonitor, ModelExpr2HLCL.NEXT_SOLUTION,
						operation, suboper);
			if (result == 0 && !progressMonitor.isCanceled()) {
				iter++;
			}
		}
		return iter;
	}

	// static call implementation
	// No longer needed when the dynamic implementation is completed
	public boolean execute(ProgressMonitor progressMonitor, String operId,
			int solutions, int execType) throws InterruptedException,
			FunctionalException {
		lastExecutionTime = 0;
		if (solutions == 0 || swiSolver == null) {
			text = "";
			configuration = new SolverSolution();

			hlclProgram = getHlclProgram(operId, execType);

			// Set<Identifier> identifiers = new TreeSet<Identifier>();
			// for (Expression exp : hlclProgram) {
			// System.out.println(HlclUtil.getUsedIdentifiers(exp));
			// identifiers.addAll(HlclUtil.getUsedIdentifiers(exp));
			// text += exp + "\n";
			// }
			// if (swiSolver != null)
			// swiSolver.close();
			swiSolver = new SWIPrologSolver(hlclProgram);
			if (progressMonitor != null && progressMonitor.isCanceled())
				throw (new InterruptedException());
			try {
				ConfigurationOptionsDTO configurationOptions = new ConfigurationOptionsDTO();
				switch (execType) {
				case ModelExpr2HLCL.SIMUL_EXEC:
				case ModelExpr2HLCL.SIMUL_EXPORT:
				case ModelExpr2HLCL.SIMUL_MAPE:
					configurationOptions.setOrder(true);

				}
				configurationOptions.setStartFromZero(true);
				List<IntNumericExpression> orderExpressionList = new ArrayList<IntNumericExpression>();
				List<LabelingOrderEnum> labelingOrderList = new ArrayList<LabelingOrderEnum>();
				labelingOrderList.add(LabelingOrderEnum.MIN);
				labelingOrderList.add(LabelingOrderEnum.MIN);
				Iterator<InstElement> iterVertex = refas
						.getVariabilityVertexCollection().iterator();
				InstElement instVertex = iterVertex.next();
				orderExpressionList.add(getSumExpression(instVertex,
						iterVertex, "Order"));
				iterVertex = refas.getVariabilityVertexCollection().iterator();
				instVertex = iterVertex.next();
				orderExpressionList.add(getSumExpression(instVertex,
						iterVertex, "Opt"));
				configurationOptions.setLabelingOrder(labelingOrderList);
				configurationOptions.setOrderExpressions(orderExpressionList);
				swiSolver.solve(new SolverSolution(), configurationOptions);
				lastExecutionTime = swiSolver.getLastExecutionTime();
			} catch (Exception e) {
				// FIXME issue#230
				throw new FunctionalException("No solution" + e.getMessage()
						+ " "
						+ FunctionalException.exceptionStacktraceToString(e));

			}
		}
		if (progressMonitor != null && progressMonitor.isCanceled())
			return false;

		if (solutions == 0 || solutions == 1) {
			if (configuration != null) {
				configuration = swiSolver.getSolution();
				lastExecutionTime += swiSolver.getLastExecutionTime();
				if (configuration == null
						|| (progressMonitor != null && progressMonitor
								.isCanceled()))
					return false;
			}
		} else
			throw new RuntimeException("Solution parameter not supported");
		// System.out.println("configuration: " + configuration.toString());

		return true;
	}

	/**
	 * Resets the GUI with false
	 */
	public void cleanGUIElements(int execType) {
		// Call the SWIProlog and obtain the result

		for (InstElement instVertex : refas.getVariabilityVertex().values()) {
			if (execType == ModelExpr2HLCL.DESIGN_EXEC) {
				if (instVertex.getInstAttribute("isConfDom") != null)
					instVertex.getInstAttribute("isConfDom").setValue(false);
				if (instVertex.getInstAttribute("varConfValue") != null)
					instVertex.getInstAttribute("varConfValue").setValue(0);
				if (instVertex.getInstAttribute("varConfDom") != null)
					instVertex.getInstAttribute("varConfDom").setValue("");
			}
			if (this.validateConceptType(instVertex, "GeneralConcept")) {

				/*
				 * if (instVertex.getInstAttribute("Core").getAsBoolean() ||
				 * instVertex.getInstAttribute("Dead").getAsBoolean()) continue;
				 */
				switch (execType) {
				case ModelExpr2HLCL.SIMUL_EXEC:
				case ModelExpr2HLCL.SIMUL_EXPORT:
				case ModelExpr2HLCL.SIMUL_MAPE:
					break;
				case ModelExpr2HLCL.CORE_EXEC:
					instVertex.getInstAttribute("Core").setValue(false);
				case ModelExpr2HLCL.DESIGN_EXEC:
					instVertex.getInstAttribute("ConfSel").setValue(false);
					instVertex.getInstAttribute("ConfNotSel").setValue(false);
					instVertex.getInstAttribute("Dead").setValue(false);

				}

				for (InstAttribute instAttribute : instVertex
						.getInstAttributes().values()) {
					if (instAttribute.getIdentifier().equals("TrueVal")
							|| instVertex.getIdentifier().equals("FalseVal"))
						continue;
					// System.out.println(vertexId + " " + attribute);
					if (instAttribute.getAttribute() instanceof ElemAttribute
							&& instAttribute
									.getAttribute()
									.getAttributeType()
									.equals(AttributeType.EXECCURRENTSTATE
											.toString())
							&& instAttribute.getType().equals("Boolean")
							&& !instAttribute.getIdentifier().equals(
									"HasParent")) {
						if (execType == ModelExpr2HLCL.CORE_EXEC
								|| execType == ModelExpr2HLCL.DESIGN_EXEC
								|| (execType == ModelExpr2HLCL.SIMUL_EXEC
										|| execType == ModelExpr2HLCL.SIMUL_EXPORT || execType == ModelExpr2HLCL.SIMUL_MAPE)
								&& (!instAttribute.getIdentifier()
										.equals("Sel") && !instAttribute
										.getIdentifier().equals("Exclu"))) {
							if (instAttribute.getIdentifier().equals("ConfSel")
									|| instAttribute.getIdentifier().equals(
											"ConfNotSel")
									|| instAttribute.getIdentifier().equals(
											"TestConfSel")
									|| instAttribute.getIdentifier().equals(
											"TestConfNotSel"))
								continue;
							instAttribute.setValue(false);
						}
					}
					if (execType != ModelExpr2HLCL.SIMUL_EXEC
							&& (instAttribute.getType().equals("Boolean") && (instAttribute
									.getIdentifier().equals("TestConfSel")
									|| instAttribute.getIdentifier().equals(
											"TestConfNotSel")
									|| instAttribute.getIdentifier().equals(
											"SimulSel") || instAttribute
									.getIdentifier().equals("NNotSel"))))
						instAttribute.setValue(false);
				}
			}
		}
	}

	/**
	 * Resets the GUI errors
	 */
	public void cleanGUIErrors() {
		for (InstElement instVertex : refas.getElements()) {
			instVertex.clearDefects();
			if (instVertex.getInstAttribute("Dead") != null)
				instVertex.getInstAttribute("Dead").setValue(false);
			if (instVertex.getInstAttribute("Core") != null)
				instVertex.getInstAttribute("Core").setValue(false);
			if (instVertex.getInstAttribute("OCore") != null)
				instVertex.getInstAttribute("OCore").setValue(false);
			if (instVertex.getInstAttribute("Sel") != null)
				instVertex.getInstAttribute("Sel").setValue(false);
			if (instVertex.getInstAttribute("OSel") != null)
				instVertex.getInstAttribute("OSel").setValue(false);
			if (instVertex.getInstAttribute("Var") != null)
				instVertex.getInstAttribute("Var").setValue(false);
			if (instVertex.getInstAttribute("PSel") != null)
				instVertex.getInstAttribute("PSel").setValue(false);
			if (instVertex.getInstAttribute("SimulSel") != null)
				instVertex.getInstAttribute("SimulSel").setValue(false);
			if (instVertex.getInstAttribute("NNotSel") != null)
				instVertex.getInstAttribute("NNotSel").setValue(false);
			if (instVertex.getInstAttribute("TestConfSel") != null)
				instVertex.getInstAttribute("TestConfSel").setValue(false);
			if (instVertex.getInstAttribute("TestConfNotSel") != null)
				instVertex.getInstAttribute("TestConfNotSel").setValue(false);
		}
	}

	public Map<String, Number> getResult() {
		return configuration.getSolverSolution();
	}

	/**
	 * Updates the GUI with the configuration
	 */
	public void updateGUIElements(List<String> attributes) {
		updateGUIElements(attributes, new ArrayList<String>(), null, null,
				null, null);
	}

	/**
	 * Updates the GUI with the configuration
	 */
	public void updateGUIElements(List<String> attributes,
			List<String> outVariables, InstElement instSubOper) {
		updateGUIElements(attributes, new ArrayList<String>(), null,
				outVariables, null, instSubOper);
	}

	public int getSingleOutValue(List<String> outVariables,
			InstElement instOperSubAction) {
		if (configuration != null) {
			Map<String, Number> prologOut;
			prologOut = configuration.getSolverSolution();
			for (String identifier : prologOut.keySet()) {
				String[] split = identifier.split("_");
				String vertexId = split[0];
				String attribute = split[1];
				InstElement vertex = refas.getElement(vertexId);
				if (outVariables.contains(attribute) && vertexId != null) {
					InstAttribute instAttribute = vertex
							.getInstAttribute(attribute);
					if (instAttribute != null
							&& instAttribute.getType().equals("Boolean")) {
						// System.out.println(prologOut.get(identifier));
						int val = (int) Float.parseFloat(prologOut
								.get(identifier) == null ? "0" : prologOut
								.get(identifier) + "");
						if (val == 1)
							return 1;
						else if (val == 0)
							return 0;
					} else if (instAttribute != null
							&& instAttribute.getType().equals("Integer"))
						return (int) Float.parseFloat(prologOut.get(identifier)
								+ "");
				}

			}
		}
		return 0;
	}

	/**
	 * Updates the GUI with the configuration
	 */
	public void updateGUIElements(List<String> selectedAttributes,
			List<String> notAvailableAttributes, List<String> conceptTypes,
			List<String> outVariables, Map<String, Number> config,
			InstElement instOperSubAction) {
		// Call the SWIProlog and obtain the result
		if (configuration != null) {
			Map<String, Number> prologOut;
			if (config == null)
				prologOut = configuration.getSolverSolution();
			else
				prologOut = config;

			for (String identifier : prologOut.keySet()) {
				String[] split = identifier.split("_");
				String vertexId = split[0];
				String attribute = split[1];
				// System.out.println(vertexId + " " + attribute + " "
				// + prologOut.get(identifier));
				InstElement vertex = refas.getElement(vertexId);
				if (!vertexId.equals("Amodel")
						&& (outVariables == null || outVariables.size() == 0
								|| outVariables.contains(attribute) || (vertex != null
								&& vertex.getTransSupInstElement() != null
								&& vertex.getTransSupInstElement()
										.getEdSyntaxEle() != null
								&& vertex.getTransSupInstElement()
										.getEdSyntaxEle()
										.getInstSemanticElementId() != null
								&& vertex.getTransSupInstElement()
										.getEdSyntaxEle()
										.getInstSemanticElementId()
										.equals("NmVariable")
								&& instOperSubAction != null
								&& vertex.getInstAttribute("variableType")
										.getValue().equals("LowLevel variable") && (vertex
								.getInstAttribute("LowLevelVarOutSubOper")
								.getValue().equals(instOperSubAction
								.getDynamicAttribute("userId")))))) {
					if (vertex == null
							|| (conceptTypes != null && !conceptTypes
									.contains(vertex
											.getTransSupportMetaElement()
											.getAutoIdentifier())))
						continue;
					InstAttribute instAttribute = vertex
							.getInstAttribute(attribute);
					if (selectedAttributes == null) {
						if (instAttribute != null
								&& instAttribute.getType().equals("Boolean")) {
							// System.out.println(prologOut.get(identifier));
							int val = (int) Float.parseFloat(prologOut
									.get(identifier) == null ? "0" : prologOut
									.get(identifier) + "");
							if (val == 1)
								instAttribute.setValue(true);
							else if (val == 0)
								instAttribute.setValue(false);
						} else if (instAttribute != null
								&& instAttribute.getType().equals("Integer"))
							instAttribute
									.setValue((int) Float.parseFloat(prologOut
											.get(identifier) + ""));
						else if (instAttribute != null)
							instAttribute.setValue(Float.parseFloat(prologOut
									.get(identifier) + ""));
					} else if (attribute.equals("Sel"))
						for (String attTarget : selectedAttributes) {
							InstAttribute instTarget = vertex
									.getInstAttribute(attTarget);
							if (instTarget != null
									&& instTarget.getType().equals("Boolean")) {
								int val = (int) Float.parseFloat(prologOut
										.get(identifier) + "");
								if (val == 1)
									instTarget.setValue(true);
								else if (val == 0)
									instTarget.setValue(false);
								else
									instTarget.setValue(prologOut
											.get(identifier));
							}
							if (instTarget != null
									&& instTarget.getType().equals("Float")) {
								float val = Float.parseFloat(prologOut
										.get(identifier) + "");
								instTarget.setValue(val);
							} else if (instTarget != null
									&& instTarget.getType().equals("Integer")) {
								int val = (int) Float.parseFloat(prologOut
										.get(identifier) + "");
								instTarget.setValue(val);
							}

						}
					else if (attribute.equals("Exclu"))
						for (String attTarget : notAvailableAttributes) {
							InstAttribute instTarget = vertex
									.getInstAttribute(attTarget);
							if (instTarget != null
									&& instTarget.getType().equals("Boolean")) {
								int val = (int) Float.parseFloat(prologOut
										.get(identifier) + "");
								if (val == 1)
									instTarget.setValue(true);
								else if (val == 0)
									instTarget.setValue(false);
								else
									instTarget.setValue(prologOut
											.get(identifier));
							}
							if (instTarget != null
									&& (instTarget.getType().equals("Integer") || instTarget
											.getType().equals("Float"))) {
								float val = Float.parseFloat(prologOut
										.get(identifier) + "");
								instTarget.setValue(val);

							}
						}
					else if (attribute.equals("value"))
						// for (String attTarget : selectedAttributes) {
						// if (attTarget.equals("varConfValue")) {
						if (prologOut.get(identifier) != null) {
							int val = (int) Float.parseFloat(prologOut
									.get(identifier) + "");
							vertex.getInstAttribute("varConfDom").setValue(
									val + "");
							vertex.getInstAttribute("value").setValue(val + "");
						}
					/*
					 * else if (attribute.equals("varConfDom")) // for (String
					 * attTarget : selectedAttributes) { // if
					 * (attTarget.equals("varConfValue")) { if
					 * (prologOut.get(identifier) != null) { int val = (int)
					 * Float.parseFloat(prologOut .get(identifier) + "");
					 * vertex.getInstAttribute("varConfDom") .setValue(val +
					 * ""); vertex.getInstAttribute("value") .setValue(val +
					 * ""); }
					 */// else
						// vertex.getInstAttribute("varConfValue")
						// .setValue(null);
					// }
					// }
				}
			}
		}
	}

	/**
	 * Updates the GUI with the configuration
	 */
	public void updateGUIElements(List<OpersIOAttribute> selectedAttributes,
			Map<String, Number> config) {
		// Call the SWIProlog and obtain the result
		if (configuration != null) {
			Map<String, Number> prologOut;
			if (config == null)
				prologOut = configuration.getSolverSolution();
			else
				prologOut = config;

			for (String identifier : prologOut.keySet()) {
				String[] split = identifier.split("_");
				String vertexId = split[0];
				String attribute = split[1];
				// System.out.println(vertexId + " " + attribute + " "
				// + prologOut.get(identifier));
				if (!vertexId.equals("Amodel")) {
					InstElement vertex = refas.getElement(vertexId);
					InstAttribute instAttribute = vertex
							.getInstAttribute(attribute);
					for (OpersIOAttribute attTarget : selectedAttributes) {
						boolean exists = false;

						List<InstElement> opersParents = null;
						if (vertex.getTransSupportMetaElement() != null
								&& vertex.getTransSupportMetaElement()
										.getTransInstSemanticElement() != null) {
							opersParents = vertex.getTransSupportMetaElement()
									.getTransInstSemanticElement()
									.getParentOpersConcept();
							if (vertex.getTransSupportMetaElement()
									.getTransInstSemanticElement()
									.getIdentifier()
									.equals(attTarget.getConceptId())
									&& attribute.equals(attTarget
											.getAttributeId()))
								exists = true;
							if (opersParents != null)
								for (InstElement parent : opersParents)
									if (parent.getIdentifier().equals(
											attTarget.getConceptId())
											&& attribute.equals(attTarget
													.getAttributeId()))
										exists = true;
						}
						if (!exists)
							continue;

						InstAttribute instTarget = vertex
								.getInstAttribute(attTarget.getAttributeId());
						if (instTarget != null
								&& instTarget.getType().equals("Boolean")) {
							int val = (int) Float.parseFloat(prologOut
									.get(identifier) + "");
							if (val == 1)
								instTarget.setValue(true);
							else if (val == 0)
								instTarget.setValue(false);
							else
								instTarget.setValue(prologOut.get(identifier));
						}
						if (instTarget != null
								&& instTarget.getType().equals("Float")) {
							float val = Float.parseFloat(prologOut
									.get(identifier) + "");
							instTarget.setValue(val);
						} else if (instTarget != null
								&& instTarget.getType().equals("Integer")) {
							int val = (int) Float.parseFloat(prologOut
									.get(identifier) + "");
							instTarget.setValue(val);
						}
						if (attTarget.getAttributeId().equals("value"))
							// for (String attTarget : selectedAttributes) {
							// if (attTarget.equals("varConfValue")) {
							if (prologOut.get(identifier) != null) {
								int val = (int) Float.parseFloat(prologOut
										.get(identifier) + "");
								vertex.getInstAttribute("varConfDom").setValue(
										val + "");
								vertex.getInstAttribute("value").setValue(
										val + "");
							}
					}
				}
			}
		}
	}

	/**
	 * Updates the GUI errors
	 */
	public void updateErrorMark(Collection<String> identifiers) {
		// Call the SWIProlog and obtain the result

		for (InstElement instVertex : refas.getVariabilityVertex().values()) {
			InstAttribute instAttribute = instVertex
					.getInstAttribute("VerificationError");
			// System.out.println(vertexId + " " + attribute);
			if (identifiers != null
					&& identifiers.contains(instVertex.getIdentifier()))
				instAttribute.setValue(true);
			else
				instAttribute.setValue(false);
		}

	}

	/**
	 * Updates the GUI errors
	 */
	public void updateErrorMark(Collection<String> identifiers,
			String defectId, String defectDescription) {

		String description = null;

		if (defectDescription == null || defectDescription.equals(""))
			return;

		if (defectDescription == null || defectDescription.equals(""))
			return;

		if (defectDescription.contains("#number#"))
			description = defectDescription.replace("#number#",
					identifiers.size() + "");
		else
			description = defectDescription;

		for (InstElement instVertex : refas.getVariabilityVertex().values()) {

			if (identifiers != null
					&& identifiers.contains(instVertex.getIdentifier()))
				instVertex.putDefect(defectId, description);
			else
				instVertex.removeDefect(defectId);
		}
		for (InstElement instVertex : refas.getInstGroupDependencies().values()) {

			if (identifiers != null
					&& identifiers.contains(instVertex.getIdentifier()))
				instVertex.putDefect(defectId, description);
			else
				instVertex.removeDefect(defectId);
		}
		for (InstElement instVertex : refas.getConstraintInstEdges().values()) {

			if (identifiers != null
					&& identifiers.contains(instVertex.getIdentifier()))
				instVertex.putDefect(defectId, description);
			else
				instVertex.removeDefect(defectId);
		}

	}

	public void updateErrorMark(Collection<String> identifiers,
			String defectId, ArrayList<String> defectDescriptions) {
		// Call the SWIProlog and obtain the result

		int pos = 0;
		for (InstElement instVertex : refas.getElements()) {
			instVertex.removeDefect(defectId);
		}

		for (String identifier : identifiers) {

			String description = defectDescriptions.get(pos++);

			InstElement element = refas.getElement(identifier);

			if (description.contains("#number#"))
				description = description.replace("#number#",
						identifiers.size() + "");

			if (description.contains("#source#")
					&& element.getSourceRelations().size() != 0)
				if (element.getSourceRelations().get(0)
						.getInstAttributeValue("name") != null)
					description = description.replace("#source#",
							element.getSourceRelations().get(0)
									.getInstAttributeValue("name")
									+ "");
				else
					description = description.replace("#source#", element
							.getSourceRelations().get(0).getIdentifier()
							+ "");
			if (description.contains("#target#")
					&& element.getTargetRelations().size() != 0)
				if (element.getTargetRelations().get(0)
						.getInstAttributeValue("name") != null)
					description = description.replace("#target#",
							element.getTargetRelations().get(0)
									.getInstAttributeValue("name")
									+ "");
				else
					description = description.replace("#target#", element
							.getTargetRelations().get(0).getIdentifier()
							+ "");

			if (description.contains("#element#"))
				if (element.getInstAttributeValue("name") != null)
					description = description.replace("#element#",
							element.getInstAttributeValue("name") + "");
				else
					description = description.replace("#element#",
							element.getIdentifier() + "");

			element.putDefect(defectId, description);
		}
	}

	/**
	 * Resets the GUI optional variable
	 */
	public void cleanElementsOptional() {
		for (InstElement instVertex : refas.getVariabilityVertex().values()) {
			InstAttribute instAttribute = instVertex
					.getInstAttribute("Optional");
			if (instAttribute.getType().equals("Boolean"))
				instAttribute.setValue(false);
		}
	}

	public ElementExpressionSet getElementConstraintGroup(String operId,
			String identifier, String concetType, int execType)
			throws FunctionalException {

		Map<String, ElementExpressionSet> constraintGroups = new HashMap<String, ElementExpressionSet>();

		if (concetType.equals("vertex"))
			createVertexExpressions(identifier, execType, constraintGroups);
		else if (concetType.equals("edge"))
			createEdgeExpressions(operId, identifier, execType,
					constraintGroups);
		else if (concetType.equals("groupdep"))
			createGroupExpressions(operId, identifier, execType, "",
					constraintGroups);
		return constraintGroups.get(identifier);
	}

	public String getText() {
		return text;
	}

	private void createModelExpressions(int execType,
			Map<String, ElementExpressionSet> constraintGroups) {
		constraintGroups.put("Model", new ModelExpressionSet("", "", idMap, f,
				refas, execType));
	}

	private void createVertexExpressions(String identifier, int execType,
			Map<String, ElementExpressionSet> constraintGroups)
			throws FunctionalException {
		if (identifier == null)
			for (InstElement elm : refas.getConstraintVertexCollection()) {
				// if (this.validateConceptType(elm, "GeneralConcept"))
				constraintGroups.put(elm.getIdentifier(),
						new SingleElementExpressionSet(elm.getIdentifier(),
								idMap, f, elm, execType));
			}
		else
			constraintGroups.put(identifier,
					new SingleElementExpressionSet(identifier, idMap, f, refas
							.getConstraintVertex().get(identifier), execType));
	}

	private void createEdgeExpressions(String operId, String identifier,
			int execType, Map<String, ElementExpressionSet> constraintGroups) {
		if (identifier == null)
			for (InstPairwiseRel elm : refas.getConstraintInstEdgesCollection()) {
				if (elm.getMetaPairwiseRelation() == null
						|| !elm.getMetaPairwiseRelation().getAutoIdentifier()
								.equals("Variable To Context Relation"))
					constraintGroups.put(
							elm.getIdentifier(),
							new PairwiseElementExpressionSet(operId, elm
									.getIdentifier(), idMap, f, elm, execType));
			}
		else if (refas.getConstraintInstEdges().get(identifier) != null)
			constraintGroups.put(identifier,
					new PairwiseElementExpressionSet(operId, identifier, idMap,
							f, refas.getConstraintInstEdges().get(identifier),
							execType));

	}

	private void createGroupExpressions(String operId, String identifier,
			int execType, String element,
			Map<String, ElementExpressionSet> constraintGroups)
			throws FunctionalException {
		createEdgeExpressions(operId, null, execType, constraintGroups); // TODO
																			// define
		// a better
		// solution
		if (identifier == null)
			for (InstOverTwoRel elm : refas
					.getInstGroupDependenciesCollection()) {
				constraintGroups.put(elm.getIdentifier(),
						new OverTwoElementsExpressionSet(elm.getIdentifier(),
								idMap, f, elm, execType, element));
			}
		else
			constraintGroups.put(identifier,
					new OverTwoElementsExpressionSet(identifier, idMap, f,
							refas.getInstGroupDependencies().get(identifier),
							execType, element));

	}

	public String getElementTextConstraints(String operId, String identifier,
			String string, int execType) throws FunctionalException {
		String out = "";
		ElementExpressionSet expressions = getElementConstraintGroup(operId,
				identifier, string, execType);
		if (expressions != null)
			for (IntExpression expression : expressions.getExpressions())
				out += expression.toString() + "\n";
		return out;
	}

	public InstanceModel getRefas() {
		return refas;
	}

	public void setRefas(InstanceModel refas) {
		this.refas = refas;
		// constraintGroups = new HashMap<String, ElementExpressionSet>();
		swiSolver = null;
		// TODO close solver?
	}

	/**
	 * Update Core concepts on model
	 * 
	 * @param outIdentifiers
	 */
	public void updateCoreConcepts(Collection<String> outIdentifiers,
			boolean all) {
		for (InstElement instVertex : refas.getVariabilityVertex().values()) {
			// if (validateConceptType(instVertex)) {
			InstAttribute instAttribute = instVertex.getInstAttribute("Core");
			// System.out.println(vertexId + " " + attribute);
			if (outIdentifiers != null
					&& outIdentifiers.contains(instVertex.getIdentifier()))
				instAttribute.setValue(true);
			else {
				if (all)
					instAttribute.setValue(false);
			}
		}
		// }
	}

	public void updateRequiredConcepts(List<String> requiredConceptsNames,
			boolean test) {
		for (InstElement instVertex : refas.getVariabilityVertex().values()) {
			if (validateConceptType(instVertex, "GeneralConcept")) {
				InstAttribute instAttributeTest = instVertex
						.getInstAttribute("TestConfSel");
				InstAttribute instAttributeConf = instVertex
						.getInstAttribute("ConfSel");

				// System.out.println(vertexId + " " + attribute);
				if (requiredConceptsNames.contains(instVertex.getIdentifier())
						|| instVertex.getInstAttribute("ConfSel")
								.getAsBoolean()) {
					if (test) {
						instAttributeTest.setValue(true);
					} else {
						instAttributeConf.setValue(true);
						instVertex.getInstAttribute("Sel").setValue(true);
					}
				} else {
					// instAttributeConf.setValue(false);
					if (test) {
						instAttributeTest.setValue(false);
					} else {

						if (!instVertex.getInstAttribute("ConfSel")
								.getAsBoolean()
								&& !instVertex.getInstAttribute("Core")
										.getAsBoolean()) {
							instVertex.getInstAttribute("Sel").setValue(false);
						} else
							instVertex.getInstAttribute("Sel").setValue(true);
					}

				}
			}
		}
	}

	public void updateDeadConfigConcepts(List<String> requiredConceptsNames,
			boolean test) {
		for (InstElement instVertex : refas.getVariabilityVertex().values()) {
			if (validateConceptType(instVertex, "GeneralConcept")) {
				InstAttribute instAttributeTest = instVertex
						.getInstAttribute("TestConfNotSel");
				InstAttribute instAttributeConf = instVertex
						.getInstAttribute("ConfNotSel");

				// System.out.println(vertexId + " " + attribute);
				if (requiredConceptsNames.contains(instVertex.getIdentifier())) {
					if (test) {
						instAttributeTest.setValue(true);
					} else {
						instAttributeConf.setValue(true);
						instVertex.getInstAttribute("Exclu").setValue(true);
					}
				} else {
					if (test) {
						instAttributeTest.setValue(false);
					} else {
						// instAttributeConf.setValue(false);
						if (!instVertex.getInstAttribute("ConfNotSel")
								.getAsBoolean()
								&& !instVertex.getInstAttribute("Dead")
										.getAsBoolean())
							instVertex.getInstAttribute("Exclu")
									.setValue(false);
						else
							instVertex.getInstAttribute("Exclu").setValue(true);
					}
				}
			}
		}
	}

	public TreeMap<String, Number> getConfiguredIdentifier(
			Set<InstElement> elementSubSet) {
		TreeMap<String, Number> out = new TreeMap<String, Number>();
		for (InstElement instVertex : refas.getVariabilityVertex().values()) {
			if (validateConceptType(instVertex, "GeneralConcept")
					&& (elementSubSet == null || elementSubSet
							.contains(instVertex))) {
				InstAttribute instAttribute = instVertex
						.getInstAttribute("ConfSel");
				if (instAttribute.getAsBoolean())
					out.put(instVertex.getIdentifier() + "_"
							+ instAttribute.getIdentifier(), 1);
				else

					out.put(instVertex.getIdentifier() + "_"
							+ instAttribute.getIdentifier(), 0);
				instAttribute = instVertex.getInstAttribute("ConfNotSel");
				if (instAttribute.getAsBoolean())
					out.put(instVertex.getIdentifier() + "_"
							+ instAttribute.getIdentifier(), 1);
				else
					out.put(instVertex.getIdentifier() + "_"
							+ instAttribute.getIdentifier(), 0);
			}
		}
		return out;
	}

	public Set<Identifier> getFreeIdentifiers() {
		Set<Identifier> out = new HashSet<Identifier>();
		for (InstElement instVertex : refas.getVariabilityVertex().values()) {
			if (validateConceptType(instVertex, "GeneralConcept")) {
				InstAttribute instAttribute = instVertex
						.getInstAttribute("Core");
				InstAttribute instAttribute2 = instVertex
						.getInstAttribute("Dead");
				InstAttribute instAttribute3 = instVertex
						.getInstAttribute("ConfSel");
				InstAttribute instAttribute4 = instVertex
						.getInstAttribute("ConfNotSel");
				if (!instAttribute.getAsBoolean()
						&& !instAttribute2.getAsBoolean()
						&& !instAttribute3.getAsBoolean()
						&& !instAttribute4.getAsBoolean())
					out.add(f.newIdentifier(instVertex.getIdentifier() + "_"
							+ "Sel"));
			}
		}
		return out;
	}

	public boolean validateConceptType(InstElement instElement, String element) {
		if (instElement == null)// || !(instElement instanceof InstVertex))
			return false;
		SyntaxElement metaElement = (instElement.getTransSupportMetaElement());
		if (metaElement == null)
			return false;
		InstElement semElement = metaElement.getTransInstSemanticElement();
		while (semElement != null && semElement.getIdentifier() != null
				&& !semElement.getIdentifier().equals(element)) {
			InstElement sEle = semElement;
			semElement = null;
			for (InstElement ele : sEle.getTargetRelations())
				if (ele instanceof InstPairwiseRel) {
					if (((InstPairwiseRel) ele).getSupportMetaPairwiseRelIden()
							.equals("ExtendsRelation")) {
						semElement = ele.getTargetRelations().get(0);
						break;
					}
				} else if (((InstPairwiseRel) ele).getSupInstEleId().equals(
						"ExtendsRelation")) {
					semElement = ele.getTargetRelations().get(0);
					break;
				}
		}
		if (semElement != null && semElement.getIdentifier() != null
				&& semElement.getIdentifier().equals(element)) {
			return true;
		}
		return false;
	}

	public void updateDeadConcepts(List<String> deadIdentifiers) {
		for (InstElement instVertex : refas.getVariabilityVertex().values()) {
			if (validateConceptType(instVertex, "GeneralConcept")) {
				InstAttribute instAttributeDead = instVertex
						.getInstAttribute("Dead");
				InstAttribute instAttributeNotAva = instVertex
						.getInstAttribute("Exclu");
				// System.out.println(vertexId + " " + attribute);
				if (deadIdentifiers != null
						&& deadIdentifiers.contains(instVertex.getIdentifier())) {
					instAttributeDead.setValue(true);
					instAttributeNotAva.setValue(true);
				}

				else {
					instAttributeDead.setValue(false);
					instAttributeNotAva.setValue(false);
				}
			}
		}
	}

	public HlclProgram configGraph(ProgressMonitor progressMonitor,
			InstElement target, Set<InstElement> evaluatedSet,
			Set<Identifier> freeIdentifiers, boolean calc)
			throws InterruptedException, FunctionalException {
		HlclProgram out = new HlclProgram();
		if (progressMonitor.isCanceled())
			throw (new InterruptedException());
		if (evaluatedSet.add(target)) {
			if ((target.getInstAttribute("Sel") != null
					&& target.getInstAttribute("Core") != null
					&& target.getInstAttribute("Exclu") != null
					&& !target.getInstAttribute("Sel").getAsBoolean()
					&& !target.getInstAttribute("Core").getAsBoolean() && !target
					.getInstAttribute("Exclu").getAsBoolean())
					|| target.getIdentifier().startsWith("FeatOT")
					|| target.getIdentifier().startsWith("HardOT")
					|| target.getIdentifier().startsWith("SoftgoalOT")
					|| target.getIdentifier().startsWith("OperClaimOT")
					|| target.getIdentifier().startsWith("AssetOperOT")
					|| target.getIdentifier().startsWith("AssetFeatOT")) {
				if (!target.getInstAttribute("Sel").getAsBoolean()
						&& !target.getInstAttribute("Exclu").getAsBoolean()
						&& !target.getIdentifier().startsWith("FeatOT"))
					if (!calc)
						freeIdentifiers.add(f.newIdentifier(target
								.getIdentifier() + "_" + "Sel"));
				for (InstElement element : target.getSourceRelations()) {
					if (progressMonitor.isCanceled())
						throw (new InterruptedException());
					if (calc)
						out.addAll(getHlclProgram("Simul",
								ModelExpr2HLCL.CONF_EXEC, element));
					InstElement related = element.getSourceRelations().get(0);
					out.addAll(configGraph(progressMonitor, related,
							evaluatedSet, freeIdentifiers, calc));
				}
				for (InstElement element : target.getTargetRelations()) {
					if (progressMonitor.isCanceled())
						throw (new InterruptedException());
					if (calc)
						out.addAll(getHlclProgram("Simul",
								ModelExpr2HLCL.CONF_EXEC, element));
					out.addAll(configGraph(progressMonitor, element
							.getTargetRelations().get(0), evaluatedSet,
							freeIdentifiers, calc));
				}
			}
			out.addAll(getHlclProgram("Simul", ModelExpr2HLCL.CONF_EXEC, target));
		}
		return out;
	}

	// Static implementation to export
	public Map<String, Map<String, Integer>> execCompleteSimul(
			ProgressMonitor progressMonitor) throws InterruptedException,
			FunctionalException {
		int iter = 0;
		Map<String, Map<String, Integer>> elements = new TreeMap<String, Map<String, Integer>>();
		elements = new HashMap<String, Map<String, Integer>>();
		String element = "Simul";
		int type = ModelExpr2HLCL.SIMUL_EXEC;
		boolean result = true;
		boolean first = true;
		int cont = 0;
		while (result && !progressMonitor.isCanceled()) {
			progressMonitor.setNote("Solutions processed: " + cont++
					+ "(total unknown)");
			if (first) {
				result = execute(progressMonitor, element,
						ModelExpr2HLCL.ONE_SOLUTION, type);
				first = false;
			} else
				result = execute(progressMonitor, element,
						ModelExpr2HLCL.NEXT_SOLUTION, type);
			if (result && !progressMonitor.isCanceled()) {
				updateGUIElements(null, null, null);
				Map<String, Integer> newMap = new TreeMap<String, Integer>();
				for (InstElement instVertex : refas
						.getVariabilityVertexCollection()) {
					if (instVertex.getInstAttribute("exportOnConfig") != null
							&& instVertex.getInstAttribute("exportOnConfig")
									.getAsBoolean()) {
						String instId = instVertex.getIdentifier();
						if (instVertex.getIdentifier().contains("Variable")) {
							Object oo = instVertex.getInstAttribute("value")
									.getValue();
							Integer o = null;
							if (oo instanceof Integer) {
								o = (Integer) instVertex.getInstAttribute(
										"value").getValue();

							} else {
								o = Integer.valueOf((String) instVertex
										.getInstAttribute("value").getValue());
							}

							newMap.put(instId, o);
						} else {
							Boolean o = (Boolean) instVertex.getInstAttribute(
									"Sel").getValue();
							Integer integer;
							if (o.booleanValue())
								integer = 1;
							else
								integer = 0;
							newMap.put(instId, integer);
						}
					}
				}
				iter++;
				elements.put(iter + "", newMap);
			}
		}
		return elements;
	}

	public void clearQueryMonitor() {
		if (swiSolver != null)
			swiSolver.clearQueryMonitor();

	}
}
