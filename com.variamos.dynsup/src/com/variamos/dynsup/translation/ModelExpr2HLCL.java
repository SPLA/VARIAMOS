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

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstOverTwoRel;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelInstance;
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
import com.variamos.dynsup.types.OperationSubActionExecType;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.LabelingOrder;
import com.variamos.hlcl.NumericExpression;
import com.variamos.io.ConsoleTextArea;
import com.variamos.solver.Configuration;
import com.variamos.solver.ConfigurationOptions;
import com.variamos.solver.SWIPrologSolver;
import com.variamos.solver.Solver;

/**
 * Class to create the Hlcl program. Part of PhD work at University of Paris 1
 * Renamed from Refas2HLCL
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public class ModelExpr2HLCL {
	private HlclFactory f = new HlclFactory();
	private String text;
	private HlclProgram hlclProgram = new HlclProgram();
	private ModelInstance refas;
	private Map<String, Identifier> idMap = new HashMap<>();
	private Configuration configuration = new Configuration();
	private Solver swiSolver;
	private long lastExecutionTime;

	public long getLastExecutionTime() {
		return lastExecutionTime;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public static final int ONE_SOLUTION = 0, NEXT_SOLUTION = 1,
			DESIGN_EXEC = 0, CONF_EXEC = 1, SIMUL_EXEC = 2, CORE_EXEC = 3,
			VAL_UPD_EXEC = 4, SIMUL_EXPORT = 5, SIMUL_MAPE = 6;

	public ModelExpr2HLCL(ModelInstance refas) {
		this.refas = refas;
		// constraintGroups = new HashMap<String, ElementExpressionSet>();

	}

	@Deprecated
	public List<BooleanExpression> rootVerityTest() {
		// HlclProgram hlclProgram = new HlclProgram();
		Map<String, ElementExpressionSet> constraintGroups = new HashMap<String, ElementExpressionSet>();
		createModelExpressions(ModelExpr2HLCL.VAL_UPD_EXEC, constraintGroups);
		List<BooleanExpression> modelExpressions = new ArrayList<BooleanExpression>();
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

	public List<BooleanExpression> verityTest(String element) {
		// HlclProgram hlclProgram = new HlclProgram();
		Map<String, ElementExpressionSet> constraintGroups = new HashMap<String, ElementExpressionSet>();
		createModelExpressions(ModelExpr2HLCL.VAL_UPD_EXEC, constraintGroups);
		List<BooleanExpression> modelExpressions = new ArrayList<BooleanExpression>();
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

	public HlclProgram relaxedTest(String element) {
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
		createGroupExpressions(null, 4, element, constraintGroups);

		List<AbstractExpression> transformations2 = new ArrayList<AbstractExpression>();
		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			List<AbstractExpression> relaxableExpressions = constraintGroup
					.getRelaxableExpressionList(element);
			if (relaxableExpressions != null)
				transformations2.addAll(relaxableExpressions);
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

	public HlclProgram compulsoryTest(String element) {
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
		createEdgeExpressions(null, 4, constraintGroups);

		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			List<AbstractExpression> compulsoryExpressions = constraintGroup
					.getCompulsoryExpressionList(element);
			if (compulsoryExpressions != null)
				transformations.addAll(compulsoryExpressions);
		}
		constraintGroups = new HashMap<String, ElementExpressionSet>();
		createGroupExpressions(null, 4, element, constraintGroups);

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
	public HlclProgram rootRelaxedTest() {
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
	 */

	// Static call without TranslationExpressionSet
	public HlclProgram getHlclProgram(String element, int execType) {
		return getHlclProgram(element, execType, null);
	}

	// Dynamic call with TranslationExpressionSet
	public HlclProgram getHlclProgram(String element, String operation,
			String subOperation, OperationSubActionExecType operExecType,
			TranslationExpressionSet transExpSet) {

		HlclProgram hlclProgram = new HlclProgram();

		Map<String, ElementExpressionSet> constraintGroups = new HashMap<String, ElementExpressionSet>();

		transExpSet.addExpressions(refas, null, subOperation, operExecType);
		// for (Expression exp : transExpSet.getHLCLExpressions(subOperation +
		// "-"
		// + operExecType)) {
		// System.out.println(exp.toString());
		// }
		// List<Labeling> labelings =
		// transExpSet.getLabelings(refas, subOperation, operExecType);
		constraintGroups.put(element, transExpSet);
		fillHlclProgram(element, subOperation, operExecType, hlclProgram,
				constraintGroups);

		return hlclProgram;
	}

	public HlclProgram getHlclProgram(String element, int execType,
			InstElement instElement) {
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
			createEdgeExpressions(elementIdentifier, execType, constraintGroups);
		// Previous call to createEdgeExpressions is required to fill the
		// attribute names for createGroupExpressions

		if (instElement == null || instElement instanceof InstOverTwoRel)
			createGroupExpressions(elementIdentifier, execType, element,
					constraintGroups);

		fillHlclProgram(element, null, null, hlclProgram, constraintGroups);
		return hlclProgram;
	}

	private void fillHlclProgram(String element, String subOperation,
			OperationSubActionExecType operExecType, HlclProgram hlclProgram,
			Map<String, ElementExpressionSet> constraintGroups) {
		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		List<BooleanExpression> modelExpressions = new ArrayList<BooleanExpression>();

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
					transformations.addAll(constraintGroup
							.getVerificationExpressionsList(element));
				if (constraintGroup.getRelaxableExpressionList(element) != null)
					transformations.addAll(constraintGroup
							.getRelaxableExpressionList(element));
				if (constraintGroup.getCompulsoryExpressionList(element) != null)
					transformations.addAll(constraintGroup
							.getCompulsoryExpressionList(element));
				if (element.equals("") || element.equals("Simul"))
					transformations.addAll(constraintGroup
							.getElementExpressions());
			}
		}

		for (BooleanExpression transformation : modelExpressions) {
			hlclProgram.add(transformation);
		}

		// Dynamic call with TranslationExpressionSet
		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			if (constraintGroup instanceof TranslationExpressionSet) {
				HlclProgram ts = ((TranslationExpressionSet) constraintGroup)
						.getHlCLProgramExpressions(subOperation + "-"
								+ operExecType);
				if (ts != null) {
					hlclProgram.addAll(ts);
				}
			}
		}
		for (AbstractExpression transformation : transformations) {
			// System.out.println(transformation.expressionStructure());
			idMap.putAll(transformation.getIdentifiers(f));
			if (transformation instanceof AbstractBooleanExpression) {
				hlclProgram.add(((AbstractBooleanExpression) transformation)
						.transform(f, idMap));
				// For negation testing
				// prog.add(((AbstractBooleanTransformation) transformation)
				// .transformNegation(f, idMap, true, false));
			} else if (transformation instanceof AbstractComparisonExpression) {
				hlclProgram.add(((AbstractComparisonExpression) transformation)
						.transform(f, idMap));
				// For negation testing
				// prog.add(((AbstractComparisonTransformation)
				// transformation)
				// .transformNegation(f, idMap));
			} else {
				hlclProgram.add(((AbstractComparisonExpression) transformation)
						.transform(f, idMap));
			}

		}
	}

	public NumericExpression getSumExpression(InstElement last,
			Iterator<InstElement> iterVertex, String attributeName) {
		if (iterVertex.hasNext()) {
			InstElement instVertex = iterVertex.next();
			if (last.getInstAttribute(attributeName) != null
					&& this.validateConceptType(last, "GeneralElement")
					&& last.getInstAttribute("Active").getAsBoolean() == true)
				return f.sum(
						f.newIdentifier(last.getIdentifier() + "_"
								+ attributeName),
						getSumExpression(instVertex, iterVertex, attributeName));
			else
				return getSumExpression(instVertex, iterVertex, attributeName);
		} else if (this.validateConceptType(last, "GeneralElement")
				&& last.getInstAttribute("Active").getAsBoolean() == true)
			return f.newIdentifier(last.getIdentifier() + "_" + attributeName);
		else
			return f.number(0);

	}

	public List<String> getOutVariables(String operation, String subAction) {
		List<String> out = new ArrayList<String>();
		List<InstElement> operActions = refas.getOperationalModel()
				.getVariabilityVertex("OMOperation");
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

	// dynamic call implementation
	public int execute(ProgressMonitor progressMonitor, String element,
			int solutions, String operation, InstElement suboper)
			throws InterruptedException {
		lastExecutionTime = 0;
		if (solutions == 0 || swiSolver == null) {
			text = "";
			configuration = new Configuration();
			// FIXME: execute for all sub-operations

			TranslationExpressionSet transExpSet = new TranslationExpressionSet(
					refas, operation, null, null);
			HlclProgram exp = getHlclProgram(element, operation,
					suboper.getIdentifier(), OperationSubActionExecType.NORMAL,
					transExpSet);
			if (exp.size() > 1) {

				hlclProgram = exp;
				swiSolver = new SWIPrologSolver(hlclProgram);
				if (progressMonitor != null && progressMonitor.isCanceled())
					throw (new InterruptedException());
				try {
					ConfigurationOptions configurationOptions = new ConfigurationOptions();
					switch (operation) {
					case "Simulation":/*
									 * Refas2Hlcl.SIMUL_EXEC: case
									 * Refas2Hlcl.SIMUL_EXPORT: case
									 * Refas2Hlcl.SIMUL_MAPE:
									 */

					}
					// FIXME support types other than normal
					configurationOptions.setLabelings(transExpSet.getLabelings(
							refas, suboper.getIdentifier(),
							OperationSubActionExecType.NORMAL));
					configurationOptions.setOrder(true);

					configurationOptions.setStartFromZero(true);

					swiSolver.solve(new Configuration(), configurationOptions);

					lastExecutionTime = swiSolver.getLastExecutionTime();
				} catch (Exception ex) {
					ConsoleTextArea.addText(ex.getStackTrace());
					ConsoleTextArea.addText("No solution");
					return -1;
				}
			} else
				return 0;
		}

		if (progressMonitor != null && progressMonitor.isCanceled())
			return -1;

		if (solutions == 0 || solutions == 1) {
			if (configuration != null) {
				configuration = swiSolver.getSolution();
				lastExecutionTime += swiSolver.getLastExecutionTime();
				if (configuration == null)
					return -1;
			}
		} else
			throw new RuntimeException("Solution parameter not supported");
		// System.out.println("configuration: " + configuration.toString());

		return 1;
	}

	// static call implementation
	// No longer needed when the dynamic implementation is completed
	public boolean execute(ProgressMonitor progressMonitor, String element,
			int solutions, int execType) throws InterruptedException {
		lastExecutionTime = 0;
		if (solutions == 0 || swiSolver == null) {
			text = "";
			configuration = new Configuration();

			hlclProgram = getHlclProgram(element, execType);

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
				ConfigurationOptions configurationOptions = new ConfigurationOptions();
				switch (execType) {
				case ModelExpr2HLCL.SIMUL_EXEC:
				case ModelExpr2HLCL.SIMUL_EXPORT:
				case ModelExpr2HLCL.SIMUL_MAPE:
					configurationOptions.setOrder(true);

				}
				configurationOptions.setStartFromZero(true);
				List<NumericExpression> orderExpressionList = new ArrayList<NumericExpression>();
				List<LabelingOrder> labelingOrderList = new ArrayList<LabelingOrder>();
				labelingOrderList.add(LabelingOrder.MIN);
				labelingOrderList.add(LabelingOrder.MIN);
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
				swiSolver.solve(new Configuration(), configurationOptions);
				lastExecutionTime = swiSolver.getLastExecutionTime();
			} catch (Exception e) {
				ConsoleTextArea.addText(e.getStackTrace());
				ConsoleTextArea.addText("No solution");
				return false;
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
			if (this.validateConceptType(instVertex, "GeneralElement")) {
				/*
				 * if (instVertex.getInstAttribute("Core").getAsBoolean() ||
				 * instVertex.getInstAttribute("Dead").getAsBoolean()) continue;
				 */
				switch (execType) {
				case ModelExpr2HLCL.SIMUL_EXEC:
				case ModelExpr2HLCL.SIMUL_EXPORT:
				case ModelExpr2HLCL.SIMUL_MAPE:
					if (instVertex.getInstAttribute("ConfSel").getAsBoolean()
							|| instVertex.getInstAttribute("ConfNotSel")
									.getAsBoolean())
						continue;
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
					// System.out.println(vertexId + " " + attribute);
					if (instAttribute.getAttribute() instanceof ElemAttribute
							&& ((ElemAttribute) instAttribute.getAttribute())
									.getAttributeType().equals(
											AttributeType.EXECCURRENTSTATE)
							&& instAttribute.getType().equals("Boolean")
							&& !instAttribute.getIdentifier().equals(
									"HasParent")) {
						if (execType == ModelExpr2HLCL.CORE_EXEC
								|| execType == ModelExpr2HLCL.DESIGN_EXEC
								|| execType == ModelExpr2HLCL.SIMUL_EXEC
								|| execType == ModelExpr2HLCL.SIMUL_EXPORT
								|| execType == ModelExpr2HLCL.SIMUL_MAPE
								|| (!instAttribute.getIdentifier()
										.equals("Sel") && !instAttribute
										.getIdentifier().equals("Exclu")))
							instAttribute.setValue(false);
					}
					if (instAttribute.getType().equals("Boolean")
							&& (instAttribute.getIdentifier()
									.equals("NPrefSel") || instAttribute
									.getIdentifier().equals("NNotPrefSel")))
						instAttribute.setValue(false);
				}
			}
		}
	}

	/**
	 * Resets the GUI errors
	 */
	public void cleanGUIErrors() {
		// Call the SWIProlog and obtain the result
		for (InstElement instVertex : refas.getVariabilityVertex().values()) {
			instVertex.clearDefects();
			if (instVertex.getInstAttribute("Dead") != null)
				instVertex.getInstAttribute("Dead").setValue(false);
			if (instVertex.getInstAttribute("Core") != null)
				instVertex.getInstAttribute("Core").setValue(false);
		}
	}

	public Map<String, Integer> getResult() {
		return configuration.getConfiguration();
	}

	/**
	 * Updates the GUI with the configuration
	 */
	public void updateGUIElements(List<String> attributes) {
		updateGUIElements(attributes, new ArrayList<String>(), null, null, null);
	}

	/**
	 * Updates the GUI with the configuration
	 */
	public void updateGUIElements(List<String> attributes,
			List<String> outVariables) {
		updateGUIElements(attributes, new ArrayList<String>(), null,
				outVariables, null);
	}

	/**
	 * Updates the GUI with the configuration
	 */
	public void updateGUIElements(List<String> selectedAttributes,
			List<String> notAvailableAttributes, List<String> conceptTypes,
			List<String> outVariables, Map<String, Integer> config) {
		// Call the SWIProlog and obtain the result
		if (configuration != null) {
			Map<String, Integer> prologOut;
			if (config == null)
				prologOut = configuration.getConfiguration();
			else
				prologOut = config;

			for (String identifier : prologOut.keySet()) {
				String[] split = identifier.split("_");
				String vertexId = split[0];
				String attribute = split[1];
				// System.out.println(vertexId + " " + attribute + " "
				// + prologOut.get(identifier));
				if (!vertexId.equals("Amodel")
						&& (outVariables == null || outVariables
								.contains(attribute))) {
					InstElement vertex = refas.getElement(vertexId);
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
						} else if (instAttribute != null)
							instAttribute
									.setValue((int) Float.parseFloat(prologOut
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
									&& (instTarget.getType().equals("Integer") || instTarget
											.getType().equals("Float"))) {
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
								int val = (int) Float.parseFloat(prologOut
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
		// Call the SWIProlog and obtain the result

		for (InstElement instVertex : refas.getVariabilityVertex().values()) {

			if (identifiers != null
					&& identifiers.contains(instVertex.getIdentifier()))
				instVertex.putDefect(defectId, defectDescription);
			else
				instVertex.removeDefect(defectId);
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

	public ElementExpressionSet getElementConstraintGroup(String identifier,
			String concetType, int execType) {

		Map<String, ElementExpressionSet> constraintGroups = new HashMap<String, ElementExpressionSet>();

		if (concetType.equals("vertex"))
			createVertexExpressions(identifier, execType, constraintGroups);
		else if (concetType.equals("edge"))
			createEdgeExpressions(identifier, execType, constraintGroups);
		else if (concetType.equals("groupdep"))
			createGroupExpressions(identifier, execType, "", constraintGroups);
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
			Map<String, ElementExpressionSet> constraintGroups) {
		if (identifier == null)
			for (InstElement elm : refas.getConstraintVertexCollection()) {
				// if (this.validateConceptType(elm, "GeneralElement"))
				constraintGroups.put(elm.getIdentifier(),
						new SingleElementExpressionSet(elm.getIdentifier(),
								idMap, f, elm, execType));
			}
		else
			constraintGroups.put(identifier,
					new SingleElementExpressionSet(identifier, idMap, f, refas
							.getConstraintVertex().get(identifier), execType));
	}

	private void createEdgeExpressions(String identifier, int execType,
			Map<String, ElementExpressionSet> constraintGroups) {
		if (identifier == null)
			for (InstPairwiseRel elm : refas.getConstraintInstEdgesCollection()) {
				if (elm.getMetaPairwiseRelation() == null
						|| !elm.getMetaPairwiseRelation().getAutoIdentifier()
								.equals("Variable To Context Relation"))
					constraintGroups.put(
							elm.getIdentifier(),
							new PairwiseElementExpressionSet(elm
									.getIdentifier(), idMap, f, elm, execType));
			}
		else if (refas.getConstraintInstEdges().get(identifier) != null)
			constraintGroups.put(identifier,
					new PairwiseElementExpressionSet(identifier, idMap, f,
							refas.getConstraintInstEdges().get(identifier),
							execType));

	}

	private void createGroupExpressions(String identifier, int execType,
			String element, Map<String, ElementExpressionSet> constraintGroups) {
		createEdgeExpressions(null, execType, constraintGroups); // TODO define
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

	public String getElementTextConstraints(String identifier, String string,
			int execType) {
		String out = "";
		ElementExpressionSet expressions = getElementConstraintGroup(
				identifier, string, execType);
		if (expressions != null)
			for (Expression expression : expressions.getExpressions())
				out += expression.toString() + "\n";
		return out;
	}

	public ModelInstance getRefas() {
		return refas;
	}

	public void setRefas(ModelInstance refas) {
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
			if (validateConceptType(instVertex, "GeneralElement")) {
				InstAttribute instAttributeTest = instVertex
						.getInstAttribute("NPrefSel");
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
			if (validateConceptType(instVertex, "GeneralElement")) {
				InstAttribute instAttributeTest = instVertex
						.getInstAttribute("NNotPrefSel");
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

	public TreeMap<String, Integer> getConfiguredIdentifier(
			Set<InstElement> elementSubSet) {
		TreeMap<String, Integer> out = new TreeMap<String, Integer>();
		for (InstElement instVertex : refas.getVariabilityVertex().values()) {
			if (validateConceptType(instVertex, "GeneralElement")
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
			if (validateConceptType(instVertex, "GeneralElement")) {
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
		SyntaxElement metaElement = ((SyntaxElement) instElement
				.getTransSupportMetaElement());
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
			if (validateConceptType(instVertex, "GeneralElement")) {
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
			throws InterruptedException {
		HlclProgram out = new HlclProgram();
		if (progressMonitor.isCanceled())
			throw (new InterruptedException());
		if (evaluatedSet.add(target)) {
			if ((!target.getInstAttribute("Sel").getAsBoolean()
					&& !target.getInstAttribute("Core").getAsBoolean() && !target
					.getInstAttribute("Exclu").getAsBoolean())
					|| target.getIdentifier().startsWith("FeatOverTwo")
					|| target.getIdentifier().startsWith("HardOverTwo")
					|| target.getIdentifier().startsWith("SoftgoalOverTwo")
					|| target.getIdentifier().startsWith("OperClaimOverTwo")
					|| target.getIdentifier().startsWith("AssetOperGroupDep")
					|| target.getIdentifier().startsWith("AssetFeatGroupDep")) {
				if (!target.getInstAttribute("Sel").getAsBoolean()
						&& !target.getInstAttribute("Exclu").getAsBoolean()
						&& !target.getIdentifier().startsWith("FeatOverTwo"))
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

	public Map<String, Map<String, Integer>> execCompleteSimul(
			ProgressMonitor progressMonitor) throws InterruptedException {
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
				updateGUIElements(null);
				Map<String, Integer> newMap = new TreeMap<String, Integer>();
				for (InstElement instVertex : refas
						.getVariabilityVertexCollection()) {
					if (instVertex.getInstAttribute("ExportOnConfig") != null
							&& instVertex.getInstAttribute("ExportOnConfig")
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
