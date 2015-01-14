package com.variamos.refas.core.simulationmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import com.variamos.refas.core.refas.Refas;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.SimulationControlAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SimulationStateAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntRefas2Hlcl;

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
	private Configuration configuration;
	private boolean hasSolution;
	private Solver swiSolver;

	public Configuration getConfiguration() {
		return configuration;
	}

	public static final int ONE_SOLUTION = 0, NEXT_SOLUTION = 1,
			DESIGN_EXEC = 0, CONF_EXEC = 1, SIMUL_EXEC = 2;

	public Refas2Hlcl(Refas refas) {
		this.refas = refas;
		constraintGroups = new HashMap<String, MetaExpressionSet>();

	}

	/**
	 * Create a new HlclProgram with the expression of all concepts and
	 * relations and calls SWIProlog to return a solution or all solutions (only
	 * ONE_SOLUTION currently supported)
	 */

	public HlclProgram getHlclProgram(int execType) {
		HlclProgram hlclProgram = new HlclProgram();
		constraintGroups = new HashMap<String, MetaExpressionSet>();
		createModelExpressions(execType);
		createVertexExpressions(null, execType);
		createEdgeExpressions(null, execType);
		// Previous call to createEdgeExpressions is required to fill the
		// attribute names for createGroupExpressions
		createGroupExpressions(null, execType);

		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		List<BooleanExpression> modelExpressions = new ArrayList<BooleanExpression>();
		for (MetaExpressionSet constraintGroup : constraintGroups.values())
			if (constraintGroup instanceof ModelExpressionSet)
				modelExpressions.addAll(((ModelExpressionSet) constraintGroup)
						.getBooleanExpressions());
			else
				transformations.addAll(constraintGroup.getTransformations());

		for (BooleanExpression transformation : modelExpressions) {
			hlclProgram.add(transformation);
		}
		for (AbstractExpression transformation : transformations) {
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

	public boolean execute(int solutions, int execType) {
		if (solutions == 0 || swiSolver == null) {
			text = "";

			hlclProgram = getHlclProgram(execType);

			Set<Identifier> identifiers = new TreeSet<Identifier>();
			for (Expression exp : hlclProgram) {
				// System.out.println(HlclUtil.getUsedIdentifiers(exp));
				identifiers.addAll(HlclUtil.getUsedIdentifiers(exp));
				text += exp + "\n";
			}
			swiSolver = new SWIPrologSolver(hlclProgram);

			try {
				ConfigurationOptions configurationOptions = new ConfigurationOptions();
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
			Configuration newConf = swiSolver.getSolution();
			if (newConf != null)
				configuration = newConf;
			else
				return false;
		} else
			throw new RuntimeException("Solution parameter not supported");
		// System.out.println("configuration: " + configuration.toString());

		return true;
	}

	/**
	 * Resets the GUI with false
	 */
	public void cleanGUIElements() {
		// Call the SWIProlog and obtain the result

		int i = 0;
		for (InstVertex instVertex : refas.getVariabilityVertex().values()) {
			for (InstAttribute instAttribute : instVertex.getInstAttributes()
					.values()) {
				// System.out.println(vertexId + " " + attribute);
				if (instAttribute.getAttribute() instanceof SimulationStateAttribute
						&& instAttribute.getAttributeType().equals("Boolean"))

					if (instAttribute.getAttributeType().equals("Boolean"))
						instAttribute.setValue(false);
			}
		}
	}

	/**
	 * Updates the GUI with the configuration
	 */
	public void updateGUIElements() {
		// Call the SWIProlog and obtain the result
		Map<String, Integer> prologOut = configuration.getConfiguration();

		int i = 0;
		for (String identifier : prologOut.keySet()) {
			String[] split = identifier.split("_");
			String vertexId = split[0];
			String attribute = split[1];
			if (!vertexId.equals("Model")) {

				InstElement vertex = refas.getElement(vertexId);
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
			}
			/*
			 * System.out.print(vertexId + " " + attribute + " " +
			 * vertex.getInstAttribute(attribute) .getAttributeType() + "; ");
			 */
		}
	}

	/**
	 * Updates the GUI errors
	 */
	public void updateErrorMark(List<String> identifiers) {
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
			createGroupExpressions(identifier, execType);
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
								idMap, f, elm));
			}
		else if (refas.getConstraintInstEdges().get(identifier) != null)
			constraintGroups.put(identifier,
					new PairwiseElementExpressionSet(identifier, idMap, f,
							refas.getConstraintInstEdges().get(identifier)));

	}

	private void createGroupExpressions(String identifier, int execType) {
		createEdgeExpressions(null, execType); // TODO define a better solution
		if (identifier == null)
			for (InstOverTwoRelation elm : refas
					.getInstGroupDependenciesCollection()) {
				constraintGroups.put(elm.getIdentifier(),
						new OverTwoElementsExpressionSet(elm.getIdentifier(),
								idMap, f, elm, execType));
			}
		else
			constraintGroups.put(identifier,
					new OverTwoElementsExpressionSet(identifier, idMap, f,
							refas.getInstGroupDependencies().get(identifier),
							execType));

	}

	public String getElementTextConstraints(String identifier, String string,
			int execType) {
		String out = "";
		for (Expression expression : getElementConstraintGroup(identifier,
				string, execType).getExpressions())
			out += expression.toString() + "\n";
		return out;
	}

	public Refas getRefas() {
		return refas;
	}

	public void setRefas(Refas refas) {
		this.refas = refas;
	}

}
