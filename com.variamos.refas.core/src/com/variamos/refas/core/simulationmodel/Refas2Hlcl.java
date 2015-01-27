package com.variamos.refas.core.simulationmodel;

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

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.cfm.productline.compiler.solverSymbols.LabelingOrder;
import com.cfm.productline.solver.Configuration;
import com.cfm.productline.solver.ConfigurationOptions;
import com.cfm.productline.solver.SWIPrologSolver;
import com.cfm.productline.solver.Solver;
import com.mxgraph.model.mxCell;
import com.variamos.refas.core.refas.Refas;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.MetaVertex;
import com.variamos.syntaxsupport.metamodelsupport.SimulationControlAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SimulationStateAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntRefas2Hlcl;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;

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
	private Map<String, MetaExpressionSet> constraintGroups;
	private String text;
	private HlclProgram hlclProgram = new HlclProgram();
	private Refas refas;
	private Map<String, Identifier> idMap = new HashMap<>();
	private Configuration configuration = new Configuration();
	private boolean hasSolution;
	private Solver swiSolver;

	public Configuration getConfiguration() {
		return configuration;
	}

	public static final int ONE_SOLUTION = 0, NEXT_SOLUTION = 1,
			DESIGN_EXEC = 0, CONF_EXEC = 1, SIMUL_EXEC = 2, CORE_EXEC = 3,
			VAL_UPD_EXEC = 4;

	public Refas2Hlcl(Refas refas) {
		this.refas = refas;
		constraintGroups = new HashMap<String, MetaExpressionSet>();

	}

	public List<BooleanExpression> rootVerityTest() {
		// HlclProgram hlclProgram = new HlclProgram();
		constraintGroups = new HashMap<String, MetaExpressionSet>();
		createModelExpressions(Refas2Hlcl.VAL_UPD_EXEC);
		List<BooleanExpression> modelExpressions = new ArrayList<BooleanExpression>();
		for (MetaExpressionSet constraintGroup : constraintGroups.values())
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
		constraintGroups = new HashMap<String, MetaExpressionSet>();
		createModelExpressions(Refas2Hlcl.VAL_UPD_EXEC);
		List<BooleanExpression> modelExpressions = new ArrayList<BooleanExpression>();
		for (MetaExpressionSet constraintGroup : constraintGroups.values())
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
		constraintGroups = new HashMap<String, MetaExpressionSet>();
		createVertexExpressions(null, Refas2Hlcl.VAL_UPD_EXEC);

		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		for (MetaExpressionSet constraintGroup : constraintGroups.values()) {
			if (constraintGroup.getRelaxableExpressionList(element) != null)
				transformations.addAll(constraintGroup
						.getRelaxableExpressionList(element));
		}
		constraintGroups = new HashMap<String, MetaExpressionSet>();
		createGroupExpressions(null, 4, element);

		List<AbstractExpression> transformations2 = new ArrayList<AbstractExpression>();
		for (MetaExpressionSet constraintGroup : constraintGroups.values()) {
			if (constraintGroup.getRelaxableExpressionList(element) != null)
				transformations2.addAll(constraintGroup
						.getRelaxableExpressionList(element));
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
		constraintGroups = new HashMap<String, MetaExpressionSet>();
		createVertexExpressions(null, 4);

		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		for (MetaExpressionSet constraintGroup : constraintGroups.values()) {
			if (constraintGroup.getCompulsoryExpressionList(element) != null)
				transformations.addAll(constraintGroup
						.getCompulsoryExpressionList(element));
		}

		constraintGroups = new HashMap<String, MetaExpressionSet>();
		createEdgeExpressions(null, 4);

		for (MetaExpressionSet constraintGroup : constraintGroups.values()) {
			if (constraintGroup.getCompulsoryExpressionList(element) != null)
				transformations.addAll(constraintGroup
						.getCompulsoryExpressionList(element));
		}
		constraintGroups = new HashMap<String, MetaExpressionSet>();
		createGroupExpressions(null, 4, element);

		for (MetaExpressionSet constraintGroup : constraintGroups.values()) {
			if (constraintGroup.getCompulsoryExpressionList(element) != null)
				transformations.addAll(constraintGroup
						.getCompulsoryExpressionList(element));
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

	public HlclProgram rootRelaxedTest() {
		HlclProgram hlclProgram = new HlclProgram();
		constraintGroups = new HashMap<String, MetaExpressionSet>();
		createVertexExpressions(null, 0);

		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		for (MetaExpressionSet constraintGroup : constraintGroups.values()) {
			if (constraintGroup.getRelaxableExpressionList("Root") != null)
				transformations.addAll(constraintGroup
						.getRelaxableExpressionList("Root"));
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

	public HlclProgram getHlclProgram(String element, int execType,
			InstElement instElement) {
		HlclProgram hlclProgram = new HlclProgram();
		constraintGroups = new HashMap<String, MetaExpressionSet>();
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

		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		List<BooleanExpression> modelExpressions = new ArrayList<BooleanExpression>();
		for (MetaExpressionSet constraintGroup : constraintGroups.values())
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

		for (BooleanExpression transformation : modelExpressions) {
			hlclProgram.add(transformation);
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
		return hlclProgram;
	}

	public NumericExpression getSumExpression(InstVertex last,
			Iterator<InstVertex> iterVertex, String attributeName) {
		if (iterVertex.hasNext()) {
			InstVertex instVertex = iterVertex.next();
			if (last.getInstAttribute(attributeName) != null
					&& last.getInstAttribute("Active").getAsBoolean() == true)
				return f.sum(
						f.newIdentifier(last.getIdentifier() + "_"
								+ attributeName),
						getSumExpression(instVertex, iterVertex, attributeName));
			else
				return getSumExpression(instVertex, iterVertex, attributeName);
		} else
			return f.newIdentifier(last.getIdentifier() + "_" + attributeName);

	}

	public boolean execute(String element, int solutions, int execType) {
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
			swiSolver = new SWIPrologSolver(hlclProgram);

			try {
				ConfigurationOptions configurationOptions = new ConfigurationOptions();
				if ((execType != Refas2Hlcl.CORE_EXEC && execType != Refas2Hlcl.DESIGN_EXEC)
						|| element.equals("Core"))
					configurationOptions.setOrder(true);
				List<NumericExpression> orderExpressionList = new ArrayList<NumericExpression>();
				List<LabelingOrder> labelingOrderList = new ArrayList<LabelingOrder>();
				labelingOrderList.add(LabelingOrder.MIN);
				labelingOrderList.add(LabelingOrder.MIN);
				Iterator<InstVertex> iterVertex = refas
						.getVariabilityVertexCollection().iterator();
				InstVertex instVertex = iterVertex.next();
				orderExpressionList.add(getSumExpression(instVertex,
						iterVertex, "Order"));
				iterVertex = refas.getVariabilityVertexCollection().iterator();
				instVertex = iterVertex.next();
				orderExpressionList.add(getSumExpression(instVertex,
						iterVertex, "Opt"));
				configurationOptions.setLabelingOrder(labelingOrderList);
				configurationOptions.setOrderExpressions(orderExpressionList);
				swiSolver.solve(new Configuration(), configurationOptions);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("No solution");
				return false;
			}
		}

		if (solutions == 0 || solutions == 1) {
			if (configuration != null) {
				configuration = swiSolver.getSolution();
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
	public void cleanGUIElements(boolean simul) {
		// Call the SWIProlog and obtain the result

		int i = 0;
		for (InstVertex instVertex : refas.getVariabilityVertex().values()) {
			for (InstAttribute instAttribute : instVertex.getInstAttributes()
					.values()) {
				// System.out.println(vertexId + " " + attribute);
				if (instAttribute.getAttribute() instanceof SimulationStateAttribute
						&& instAttribute.getAttributeType().equals("Boolean")
						&& !instAttribute.getIdentifier().equals("HasParent"))

					if (instAttribute.getAttributeType().equals("Boolean")
							&& (simul || (!instAttribute.getIdentifier()
									.equals("Selected") && !instAttribute
									.getIdentifier().equals("NotAvailable"))))
						instAttribute.setValue(false);
				if (instAttribute.getAttributeType().equals("Boolean")
						&& (instAttribute.getIdentifier().equals(
								"NexPrefSelected") || instAttribute
								.getIdentifier().equals("NextNotPrefSelected")))

					if (instAttribute.getAttributeType().equals("Boolean"))
						instAttribute.setValue(false);
			}
		}
	}

	/**
	 * Resets the GUI errors
	 */
	public void cleanGUIErrors() {
		// Call the SWIProlog and obtain the result
		for (InstVertex instVertex : refas.getVariabilityVertex().values()) {
			instVertex.clearDefects();
		}
	}

	public Map<String, Integer> getResult() {
		return configuration.getConfiguration();
	}

	/**
	 * Updates the GUI with the configuration
	 */
	public void updateGUIElements(List<String> attributes) {
		// Call the SWIProlog and obtain the result
		Map<String, Integer> prologOut = configuration.getConfiguration();

		int i = 0;
		for (String identifier : prologOut.keySet()) {
			String[] split = identifier.split("_");
			String vertexId = split[0];
			String attribute = split[1];
			if (!vertexId.equals("Amodel")) {
				InstElement vertex = refas.getElement(vertexId);
				if (attributes == null) {
					// System.out.println(vertexId + " " + attribute);
					if (vertex.getInstAttribute(attribute).getAttributeType()
							.equals("Boolean"))

						if (prologOut.get(identifier).intValue() == 1)
							vertex.getInstAttribute(attribute).setValue(true);
						else if (prologOut.get(identifier).intValue() == 0)
							vertex.getInstAttribute(attribute).setValue(false);

						else
							vertex.getInstAttribute(attribute).setValue(
									prologOut.get(i));
				} else if (attribute.equals("Selected"))
					for (String attTarget : attributes) {
						if (vertex.getInstAttribute(attTarget)
								.getAttributeType().equals("Boolean"))
							if (prologOut.get(identifier).intValue() == 1)
								vertex.getInstAttribute(attTarget).setValue(
										true);
							else if (prologOut.get(identifier).intValue() == 0)
								vertex.getInstAttribute(attTarget).setValue(
										false);
							else
								vertex.getInstAttribute(attTarget).setValue(
										prologOut.get(i));
					}
			}
		}
	}

	/**
	 * Updates the GUI errors
	 */
	public void updateErrorMark(Collection<String> identifiers) {
		// Call the SWIProlog and obtain the result

		for (InstVertex instVertex : refas.getVariabilityVertex().values()) {
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

		for (InstVertex instVertex : refas.getVariabilityVertex().values()) {

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
		for (InstVertex instVertex : refas.getVariabilityVertex().values()) {
			InstAttribute instAttribute = instVertex
					.getInstAttribute("Optional");
			if (instAttribute.getAttributeType().equals("Boolean"))
				instAttribute.setValue(false);
		}
	}

	public MetaExpressionSet getElementConstraintGroup(String identifier,
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
			for (InstVertex elm : refas.getConstraintVertexCollection()) {
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
				constraintGroups.put(elm.getIdentifier(),
						new PairwiseElementExpressionSet(elm.getIdentifier(),
								idMap, f, elm, execType));
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
		MetaExpressionSet expressions = getElementConstraintGroup(identifier,
				string, execType);
		if (expressions != null)
			for (Expression expression : expressions.getExpressions())
				out += expression.toString() + "\n";
		return out;
	}

	public Refas getRefas() {
		return refas;
	}

	public void setRefas(Refas refas) {
		this.refas = refas;
		constraintGroups = new HashMap<String, MetaExpressionSet>();
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
		for (InstVertex instVertex : refas.getVariabilityVertex().values()) {
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
		for (InstVertex instVertex : refas.getVariabilityVertex().values()) {
			if (validateConceptType(instVertex)) {
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
		for (InstVertex instVertex : refas.getVariabilityVertex().values()) {
			if (validateConceptType(instVertex)
					|| instVertex.getInstAttribute("ConfigNotSelected")
							.getAsBoolean()) {
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
		for (InstVertex instVertex : refas.getVariabilityVertex().values()) {
			if (validateConceptType(instVertex)
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
		for (InstVertex instVertex : refas.getVariabilityVertex().values()) {
			if (validateConceptType(instVertex)) {
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

	public boolean validateConceptType(InstVertex instVertex) {
		MetaVertex metaElement = ((MetaVertex) instVertex
				.getTransSupportMetaElement());
		IntSemanticElement semElement = metaElement.getTransSemanticConcept();
		while (semElement != null && semElement.getIdentifier() != null
				&& !semElement.getIdentifier().equals("SemGeneralElement"))
			semElement = semElement.getParent();
		if (semElement != null && semElement.getIdentifier() != null
				&& semElement.getIdentifier().equals("SemGeneralElement")) {
			return true;
		}
		return false;
	}

	public void updateDeadConcepts(List<String> deadIdentifiers) {
		for (InstVertex instVertex : refas.getVariabilityVertex().values()) {
			if (validateConceptType(instVertex)) {
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

	public HlclProgram configGraph(InstElement target,
			Set<InstElement> evaluatedSet, Set<Identifier> freeIdentifiers) {
		HlclProgram out = new HlclProgram();
		if (evaluatedSet.add(target)) {
			if ((!target.getInstAttribute("Selected").getAsBoolean() && !target
					.getInstAttribute("NotAvailable").getAsBoolean())
					|| target.getIdentifier().startsWith("FeatOverTwo")) {
				if (!target.getInstAttribute("Selected").getAsBoolean()
						&& !target.getInstAttribute("NotAvailable")
								.getAsBoolean()
						&& !target.getIdentifier().startsWith("FeatOverTwo"))
					freeIdentifiers.add(f.newIdentifier(target.getIdentifier()
							+ "_Selected"));
				for (InstElement element : target.getSourceRelations()) {
					out.addAll(getHlclProgram("Simul", Refas2Hlcl.CONF_EXEC,
							element));
					InstElement related = element.getSourceRelations().get(0);
					out.addAll(configGraph(related,
							evaluatedSet, freeIdentifiers));
				}
				for (InstElement element : target.getTargetRelations()) {

					out.addAll(getHlclProgram("Simul", Refas2Hlcl.CONF_EXEC,
							element));
					out.addAll(configGraph(element.getTargetRelations().get(0),
							evaluatedSet, freeIdentifiers));
				}
			}
			out.addAll(getHlclProgram("", Refas2Hlcl.CONF_EXEC, target));
		}
		return out;
	}
}
