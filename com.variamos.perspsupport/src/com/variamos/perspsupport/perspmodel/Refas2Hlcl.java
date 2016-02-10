package com.variamos.perspsupport.perspmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.ProgressMonitor;

import com.variamos.compiler.solverSymbols.LabelingOrder;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.HlclUtil;
import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.NumericExpression;
import com.variamos.perspsupport.expressionsupport.OperationLabeling;
import com.variamos.perspsupport.expressionsupport.SemanticOperationSubAction;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstOverTwoRelation;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.instancesupport.InstVertex;
import com.variamos.perspsupport.semanticinterface.IntRefas2Hlcl;
import com.variamos.perspsupport.semanticinterface.IntSemanticElement;
import com.variamos.perspsupport.semanticsupport.SemanticVariable;
import com.variamos.perspsupport.syntaxsupport.ExecCurrentStateAttribute;
import com.variamos.perspsupport.syntaxsupport.MetaVertex;
import com.variamos.perspsupport.translationsupport.TranslationExpressionSet;
import com.variamos.perspsupport.types.OperationSubActionExecType;
import com.variamos.semantic.expressions.AbstractBooleanExpression;
import com.variamos.semantic.expressions.AbstractComparisonExpression;
import com.variamos.semantic.expressions.AbstractExpression;
import com.variamos.semantic.expressionsupport.ElementExpressionSet;
import com.variamos.semantic.expressionsupport.ModelExpressionSet;
import com.variamos.semantic.expressionsupport.OverTwoElementsExpressionSet;
import com.variamos.semantic.expressionsupport.PairwiseElementExpressionSet;
import com.variamos.semantic.expressionsupport.SingleElementExpressionSet;
import com.variamos.solver.Configuration;
import com.variamos.solver.ConfigurationOptions;
import com.variamos.solver.SWIPrologSolver;
import com.variamos.solver.Solver;

/**
 * Class to create the Hlcl program. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public class Refas2Hlcl implements IntRefas2Hlcl {
	private HlclFactory f = new HlclFactory();
	private Map<String, ElementExpressionSet> constraintGroups;
	private String text;
	private HlclProgram hlclProgram = new HlclProgram();
	private RefasModel refas;
	private Map<String, Identifier> idMap = new HashMap<>();
	private Configuration configuration = new Configuration();
	private Solver swiSolver;
	private long lastExecutionTime;
	private List<String> outVariables;

	public long getLastExecutionTime() {
		return lastExecutionTime;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public static final int ONE_SOLUTION = 0, NEXT_SOLUTION = 1,
			DESIGN_EXEC = 0, CONF_EXEC = 1, SIMUL_EXEC = 2, CORE_EXEC = 3,
			VAL_UPD_EXEC = 4, SIMUL_EXPORT = 5, SIMUL_MAPE = 6;

	public Refas2Hlcl(RefasModel refas) {
		this.refas = refas;
		constraintGroups = new HashMap<String, ElementExpressionSet>();

	}

	@Deprecated
	public List<BooleanExpression> rootVerityTest() {
		// HlclProgram hlclProgram = new HlclProgram();
		constraintGroups = new HashMap<String, ElementExpressionSet>();
		createModelExpressions(Refas2Hlcl.VAL_UPD_EXEC);
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
		constraintGroups = new HashMap<String, ElementExpressionSet>();
		createModelExpressions(Refas2Hlcl.VAL_UPD_EXEC);
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
		constraintGroups = new HashMap<String, ElementExpressionSet>();
		createVertexExpressions(null, Refas2Hlcl.VAL_UPD_EXEC);

		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			List<AbstractExpression> relaxableExpressions = constraintGroup
					.getRelaxableExpressionList(element);
			if (relaxableExpressions != null)
				transformations.addAll(relaxableExpressions);
		}
		constraintGroups = new HashMap<String, ElementExpressionSet>();
		createGroupExpressions(null, 4, element);

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
		constraintGroups = new HashMap<String, ElementExpressionSet>();
		createVertexExpressions(null, 4);

		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			List<AbstractExpression> compulsoryExpressions = constraintGroup
					.getCompulsoryExpressionList(element);
			if (compulsoryExpressions != null)
				transformations.addAll(compulsoryExpressions);
		}

		constraintGroups = new HashMap<String, ElementExpressionSet>();
		createEdgeExpressions(null, 4);

		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			List<AbstractExpression> compulsoryExpressions = constraintGroup
					.getCompulsoryExpressionList(element);
			if (compulsoryExpressions != null)
				transformations.addAll(compulsoryExpressions);
		}
		constraintGroups = new HashMap<String, ElementExpressionSet>();
		createGroupExpressions(null, 4, element);

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
		constraintGroups = new HashMap<String, ElementExpressionSet>();
		createVertexExpressions(null, 0);

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

	public HlclProgram getHlclProgram(String element, int execType) {
		return getHlclProgram(element, execType, null);
	}

	public HlclProgram getHlclProgram(String element, String operation,
			String subOperation, OperationSubActionExecType operExecType) {

		HlclProgram hlclProgram = new HlclProgram();
		constraintGroups = new HashMap<String, ElementExpressionSet>();

		TranslationExpressionSet transExpSet = new TranslationExpressionSet(
				refas, operation, null, null);
		transExpSet.addExpressions(refas, null, subOperation, operExecType);
		// for (Expression exp : transExpSet.getHLCLExpressions(subOperation +
		// "-"
		// + operExecType)) {
		// System.out.println(exp.toString());
		// }

		outVariables = transExpSet.getOutVariables(refas, subOperation);

		constraintGroups.put(element, transExpSet);
		fillHlclProgram(element, subOperation, operExecType, hlclProgram);

		return hlclProgram;
	}

	public HlclProgram getHlclProgram(String element, int execType,
			InstElement instElement) {
		HlclProgram hlclProgram = new HlclProgram();
		constraintGroups = new HashMap<String, ElementExpressionSet>();

		String elementIdentifier = null;
		if (instElement == null)
			createModelExpressions(execType);
		else
			elementIdentifier = instElement.getIdentifier();
		if (instElement == null || instElement instanceof InstConcept
				|| instElement instanceof InstOverTwoRelation)
			createVertexExpressions(elementIdentifier, execType);

		if (instElement == null || instElement instanceof InstPairwiseRelation)
			createEdgeExpressions(elementIdentifier, execType);
		// Previous call to createEdgeExpressions is required to fill the
		// attribute names for createGroupExpressions

		if (instElement == null || instElement instanceof InstOverTwoRelation)
			createGroupExpressions(elementIdentifier, execType, element);

		fillHlclProgram(element, null, null, hlclProgram);
		return hlclProgram;
	}

	private void fillHlclProgram(String element, String subOperation,
			OperationSubActionExecType operExecType, HlclProgram hlclProgram) {
		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		List<BooleanExpression> modelExpressions = new ArrayList<BooleanExpression>();
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
		for (ElementExpressionSet constraintGroup : constraintGroups.values()) {
			if (constraintGroup instanceof TranslationExpressionSet) {
				HlclProgram ts = ((TranslationExpressionSet) constraintGroup)
						.getHlCLProgramExpressions(subOperation + "-"
								+ operExecType);
				if (ts != null) {
					hlclProgram.add(ts);
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

	// dynamic call implementation
	public boolean execute(ProgressMonitor progressMonitor, String element,
			int solutions, String operation) throws InterruptedException {
		lastExecutionTime = 0;
		if (solutions == 0 || swiSolver == null) {
			text = "";
			configuration = new Configuration();
			InstElement subop = refas.getSyntaxRefas().getSemanticRefas()
					.getElement("Sim-Execution");

			hlclProgram = getHlclProgram(element, operation, "Sim-Execution",
					OperationSubActionExecType.NORMAL);

			Set<Identifier> identifiers = new TreeSet<Identifier>();
			for (Expression exp : hlclProgram) {
				// System.out.println(HlclUtil.getUsedIdentifiers(exp));
				identifiers.addAll(HlclUtil.getUsedIdentifiers(exp));
				text += exp + "\n";
			}
			// if (swiSolver != null)
			// swiSolver.close();
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
					configurationOptions.setOrder(true);

				}

				configurationOptions.setOrder(true);
				configurationOptions.setStartFromZero(true);
				// FIX Luisa: iterate over all the labelings
				OperationLabeling operlab = ((SemanticOperationSubAction) subop
						.getEditableSemanticElement()).getOperLabels().get(0);
				configurationOptions.setLabelingOrder(operlab
						.getLabelingOrderList());
				configurationOptions.setOrderExpressions(operlab
						.getOrderExpressionList());
				operlab.getVariables(); // FIX Luisa: pass variables to labeling
				swiSolver.solve(new Configuration(), configurationOptions);
				lastExecutionTime = swiSolver.getLastExecutionTime();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("No solution");
				return false;
			}
		}
		if (progressMonitor != null && progressMonitor.isCanceled())
			return false;

		if (solutions == 0 || solutions == 1) {
			if (configuration != null) {
				configuration = swiSolver.getSolution();
				lastExecutionTime += swiSolver.getLastExecutionTime();
				if (configuration == null)
					return false;
			}
		} else
			throw new RuntimeException("Solution parameter not supported");
		// System.out.println("configuration: " + configuration.toString());

		return true;
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

			Set<Identifier> identifiers = new TreeSet<Identifier>();
			for (Expression exp : hlclProgram) {
				// System.out.println(HlclUtil.getUsedIdentifiers(exp));
				identifiers.addAll(HlclUtil.getUsedIdentifiers(exp));
				text += exp + "\n";
			}
			// if (swiSolver != null)
			// swiSolver.close();
			swiSolver = new SWIPrologSolver(hlclProgram);
			if (progressMonitor != null && progressMonitor.isCanceled())
				throw (new InterruptedException());
			try {
				ConfigurationOptions configurationOptions = new ConfigurationOptions();
				switch (execType) {
				case Refas2Hlcl.SIMUL_EXEC:
				case Refas2Hlcl.SIMUL_EXPORT:
				case Refas2Hlcl.SIMUL_MAPE:
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
				e.printStackTrace();
				System.out.println("No solution");
				return false;
			}
		}
		if (progressMonitor != null && progressMonitor.isCanceled())
			return false;

		if (solutions == 0 || solutions == 1) {
			if (configuration != null) {
				configuration = swiSolver.getSolution();
				lastExecutionTime += swiSolver.getLastExecutionTime();
				if (configuration == null)
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
				case Refas2Hlcl.SIMUL_EXEC:
				case Refas2Hlcl.SIMUL_EXPORT:
				case Refas2Hlcl.SIMUL_MAPE:
					if (instVertex.getInstAttribute("ConfigSelected")
							.getAsBoolean()
							|| instVertex.getInstAttribute("ConfigNotSelected")
									.getAsBoolean())
						continue;
					break;
				case Refas2Hlcl.CORE_EXEC:
					instVertex.getInstAttribute("Core").setValue(false);
				case Refas2Hlcl.DESIGN_EXEC:
					instVertex.getInstAttribute("ConfigSelected").setValue(
							false);
					instVertex.getInstAttribute("ConfigNotSelected").setValue(
							false);
					instVertex.getInstAttribute("Dead").setValue(false);
				}

				for (InstAttribute instAttribute : instVertex
						.getInstAttributes().values()) {
					// System.out.println(vertexId + " " + attribute);
					if (instAttribute.getAttribute() instanceof ExecCurrentStateAttribute
							&& instAttribute.getType().equals("Boolean")
							&& !instAttribute.getIdentifier().equals(
									"HasParent")) {
						if (execType == Refas2Hlcl.CORE_EXEC
								|| execType == Refas2Hlcl.DESIGN_EXEC
								|| execType == Refas2Hlcl.SIMUL_EXEC
								|| execType == Refas2Hlcl.SIMUL_EXPORT
								|| execType == Refas2Hlcl.SIMUL_MAPE
								|| (!instAttribute.getIdentifier().equals(
										"Selected") && !instAttribute
										.getIdentifier().equals("NotAvailable")))
							instAttribute.setValue(false);
					}
					if (instAttribute.getType().equals("Boolean")
							&& (instAttribute.getIdentifier().equals(
									"NextPrefSelected") || instAttribute
									.getIdentifier().equals(
											"NextNotPrefSelected")))
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
				if (!vertexId.equals("Amodel")) {
					InstElement vertex = refas.getElement(vertexId);
					if (vertex == null
							|| (conceptTypes != null && !conceptTypes
									.contains(vertex
											.getTransSupportMetaElement()
											.getAutoIdentifier()))
							|| (outVariables != null && !outVariables
									.contains(attribute)))
						continue;

					if (selectedAttributes == null) {
						// System.out.println(vertexId + " " + attribute + " "
						// + prologOut.get(identifier));
						if (vertex.getInstAttribute(attribute) != null
								&& vertex.getInstAttribute(attribute).getType()
										.equals("Boolean")) {
							// System.out.println(prologOut.get(identifier));
							int val = (int) Float.parseFloat(prologOut
									.get(identifier) == null ? "0" : prologOut
									.get(identifier) + "");
							if (val == 1)
								vertex.getInstAttribute(attribute).setValue(
										true);
							else if (val == 0)
								vertex.getInstAttribute(attribute).setValue(
										false);
						} else if (vertex.getInstAttribute(attribute) != null)
							vertex.getInstAttribute(attribute).setValue(
									(int) Float.parseFloat(prologOut
											.get(identifier) + ""));
					} else if (attribute.equals("Selected"))
						for (String attTarget : selectedAttributes) {
							if (vertex.getInstAttribute(attTarget) != null
									&& vertex.getInstAttribute(attTarget)
											.getType().equals("Boolean")) {
								int val = (int) Float.parseFloat(prologOut
										.get(identifier) + "");
								if (val == 1)
									vertex.getInstAttribute(attTarget)
											.setValue(true);
								else if (val == 0)
									vertex.getInstAttribute(attTarget)
											.setValue(false);
								else
									vertex.getInstAttribute(attTarget)
											.setValue(prologOut.get(identifier));
							}
							if (vertex.getInstAttribute(attTarget) != null
									&& vertex.getInstAttribute(attTarget)
											.getType().equals("Integer")) {
								int val = (int) Float.parseFloat(prologOut
										.get(identifier) + "");
								vertex.getInstAttribute(attTarget)
										.setValue(val);

							}
						}
					else if (attribute.equals("NotAvailable"))
						for (String attTarget : notAvailableAttributes) {
							if (vertex.getInstAttribute(attTarget) != null
									&& vertex.getInstAttribute(attTarget)
											.getType().equals("Boolean")) {
								int val = (int) Float.parseFloat(prologOut
										.get(identifier) + "");
								if (val == 1)
									vertex.getInstAttribute(attTarget)
											.setValue(true);
								else if (val == 0)
									vertex.getInstAttribute(attTarget)
											.setValue(false);
								else
									vertex.getInstAttribute(attTarget)
											.setValue(prologOut.get(identifier));
							}
							if (vertex.getInstAttribute(attTarget) != null
									&& vertex.getInstAttribute(attTarget)
											.getType().equals("Integer")) {
								int val = (int) Float.parseFloat(prologOut
										.get(identifier) + "");
								vertex.getInstAttribute(attTarget)
										.setValue(val);

							}
						}
					else if (attribute.equals("value"))
						// for (String attTarget : selectedAttributes) {
						// if (attTarget.equals("variableConfigValue")) {
						if (prologOut.get(identifier) != null) {
							int val = (int) Float.parseFloat(prologOut
									.get(identifier) + "");
							vertex.getInstAttribute("variableConfigDomain")
									.setValue(val + "");
							vertex.getInstAttribute("value").setValue(val + "");
						}
					/*
					 * else if (attribute.equals("variableConfigDomain")) // for
					 * (String attTarget : selectedAttributes) { // if
					 * (attTarget.equals("variableConfigValue")) { if
					 * (prologOut.get(identifier) != null) { int val = (int)
					 * Float.parseFloat(prologOut .get(identifier) + "");
					 * vertex.getInstAttribute("variableConfigDomain")
					 * .setValue(val + ""); vertex.getInstAttribute("value")
					 * .setValue(val + ""); }
					 */// else
						// vertex.getInstAttribute("variableConfigValue")
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

		if (concetType.equals("vertex"))
			createVertexExpressions(identifier, execType);
		else if (concetType.equals("edge"))
			createEdgeExpressions(identifier, execType);
		else if (concetType.equals("groupdep"))
			createGroupExpressions(identifier, execType, "");
		return constraintGroups.get(identifier);
	}

	public String getText() {
		return text;
	}

	private void createModelExpressions(int execType) {
		constraintGroups.put("Model", new ModelExpressionSet("", "", idMap, f,
				refas, execType));
	}

	private void createVertexExpressions(String identifier, int execType) {
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

	private void createEdgeExpressions(String identifier, int execType) {
		if (identifier == null)
			for (InstPairwiseRelation elm : refas
					.getConstraintInstEdgesCollection()) {
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
			String element) {
		createEdgeExpressions(null, execType); // TODO define a better solution
		if (identifier == null)
			for (InstOverTwoRelation elm : refas
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

	public RefasModel getRefas() {
		return refas;
	}

	public void setRefas(RefasModel refas) {
		this.refas = refas;
		constraintGroups = new HashMap<String, ElementExpressionSet>();
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
						.getInstAttribute("NextPrefSelected");
				InstAttribute instAttributeConf = instVertex
						.getInstAttribute("ConfigSelected");

				// System.out.println(vertexId + " " + attribute);
				if (requiredConceptsNames.contains(instVertex.getIdentifier())
						|| instVertex.getInstAttribute("ConfigSelected")
								.getAsBoolean()) {
					if (test) {
						instAttributeTest.setValue(true);
					} else {
						instAttributeConf.setValue(true);
						instVertex.getInstAttribute("Selected").setValue(true);
					}
				} else {
					// instAttributeConf.setValue(false);
					if (test) {
						instAttributeTest.setValue(false);
					} else {

						if (!instVertex.getInstAttribute("ConfigSelected")
								.getAsBoolean()
								&& !instVertex.getInstAttribute("Core")
										.getAsBoolean()) {
							instVertex.getInstAttribute("Selected").setValue(
									false);
						} else
							instVertex.getInstAttribute("Selected").setValue(
									true);
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
						.getInstAttribute("NextNotPrefSelected");
				InstAttribute instAttributeConf = instVertex
						.getInstAttribute("ConfigNotSelected");

				// System.out.println(vertexId + " " + attribute);
				if (requiredConceptsNames.contains(instVertex.getIdentifier())) {
					if (test) {
						instAttributeTest.setValue(true);
					} else {
						instAttributeConf.setValue(true);
						instVertex.getInstAttribute("NotAvailable").setValue(
								true);
					}
				} else {
					if (test) {
						instAttributeTest.setValue(false);
					} else {
						// instAttributeConf.setValue(false);
						if (!instVertex.getInstAttribute("ConfigNotSelected")
								.getAsBoolean()
								&& !instVertex.getInstAttribute("Dead")
										.getAsBoolean())
							instVertex.getInstAttribute("NotAvailable")
									.setValue(false);
						else
							instVertex.getInstAttribute("NotAvailable")
									.setValue(true);
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
						.getInstAttribute("ConfigSelected");
				if (instAttribute.getAsBoolean())
					out.put(instVertex.getIdentifier() + "_"
							+ instAttribute.getIdentifier(), 1);
				else

					out.put(instVertex.getIdentifier() + "_"
							+ instAttribute.getIdentifier(), 0);
				instAttribute = instVertex
						.getInstAttribute("ConfigNotSelected");
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
						.getInstAttribute("ConfigSelected");
				InstAttribute instAttribute4 = instVertex
						.getInstAttribute("ConfigNotSelected");
				if (!instAttribute.getAsBoolean()
						&& !instAttribute2.getAsBoolean()
						&& !instAttribute3.getAsBoolean()
						&& !instAttribute4.getAsBoolean())
					out.add(f.newIdentifier(instVertex.getIdentifier() + "_"
							+ "Selected"));
			}
		}
		return out;
	}

	public boolean validateConceptType(InstElement instElement, String element) {
		if (instElement == null || !(instElement instanceof InstVertex))
			return false;
		MetaVertex metaElement = ((MetaVertex) instElement
				.getTransSupportMetaElement());
		if (metaElement == null)
			return false;
		IntSemanticElement semElement = metaElement.getTransSemanticConcept();
		while (semElement != null && semElement.getIdentifier() != null
				&& !semElement.getIdentifier().equals(element))
			semElement = semElement.getParent();
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
						.getInstAttribute("NotAvailable");
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
			if ((!target.getInstAttribute("Selected").getAsBoolean()
					&& !target.getInstAttribute("Core").getAsBoolean() && !target
					.getInstAttribute("NotAvailable").getAsBoolean())
					|| target.getIdentifier().startsWith("FeatOverTwo")
					|| target.getIdentifier().startsWith("HardOverTwo")
					|| target.getIdentifier().startsWith("SoftgoalOverTwo")
					|| target.getIdentifier().startsWith("OperClaimOverTwo")
					|| target.getIdentifier().startsWith("AssetOperGroupDep")
					|| target.getIdentifier().startsWith("AssetFeatGroupDep")) {
				if (!target.getInstAttribute("Selected").getAsBoolean()
						&& !target.getInstAttribute("NotAvailable")
								.getAsBoolean()
						&& !target.getIdentifier().startsWith("FeatOverTwo"))
					if (!calc)
						freeIdentifiers.add(f.newIdentifier(target
								.getIdentifier() + "_Selected"));
				for (InstElement element : target.getSourceRelations()) {
					if (progressMonitor.isCanceled())
						throw (new InterruptedException());
					if (calc)
						out.addAll(getHlclProgram("Simul",
								Refas2Hlcl.CONF_EXEC, element));
					InstElement related = element.getSourceRelations().get(0);
					out.addAll(configGraph(progressMonitor, related,
							evaluatedSet, freeIdentifiers, calc));
				}
				for (InstElement element : target.getTargetRelations()) {
					if (progressMonitor.isCanceled())
						throw (new InterruptedException());
					if (calc)
						out.addAll(getHlclProgram("Simul",
								Refas2Hlcl.CONF_EXEC, element));
					out.addAll(configGraph(progressMonitor, element
							.getTargetRelations().get(0), evaluatedSet,
							freeIdentifiers, calc));
				}
			}
			out.addAll(getHlclProgram("Simul", Refas2Hlcl.CONF_EXEC, target));
		}
		return out;
	}

	public Map<String, Map<String, Integer>> execCompleteSimul(
			ProgressMonitor progressMonitor) throws InterruptedException {
		int iter = 0;
		Map<String, Map<String, Integer>> elements = new TreeMap<String, Map<String, Integer>>();
		elements = new HashMap<String, Map<String, Integer>>();
		String element = "Simul";
		int type = Refas2Hlcl.SIMUL_EXEC;
		boolean result = true;
		boolean first = true;
		int cont = 0;
		while (result && !progressMonitor.isCanceled()) {
			progressMonitor.setNote("Solutions processed: " + cont++
					+ "(total unknown)");
			if (first) {
				result = execute(progressMonitor, element,
						Refas2Hlcl.ONE_SOLUTION, type);
				first = false;
			} else
				result = execute(progressMonitor, element,
						Refas2Hlcl.NEXT_SOLUTION, type);
			if (result) {
				updateGUIElements(null);
				Map<String, Integer> newMap = new TreeMap<String, Integer>();
				for (InstElement instVertex : refas
						.getVariabilityVertexCollection()) {
					if (instVertex.getInstAttribute("ExportOnConfig") != null
							&& instVertex.getInstAttribute("ExportOnConfig")
									.getAsBoolean()) {
						String instId = instVertex.getIdentifier();
						if (instVertex.getIdentifier().contains("Variable")) {
							Object oo = instVertex.getInstAttribute(
									SemanticVariable.VAR_VALUE).getValue();
							Integer o = null;
							if (oo instanceof Integer) {
								o = (Integer) instVertex.getInstAttribute(
										SemanticVariable.VAR_VALUE).getValue();

							} else {
								o = Integer.valueOf((String) instVertex
										.getInstAttribute(
												SemanticVariable.VAR_VALUE)
										.getValue());
							}

							newMap.put(instId, o);
						} else {
							Boolean o = (Boolean) instVertex.getInstAttribute(
									"Selected").getValue();
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
