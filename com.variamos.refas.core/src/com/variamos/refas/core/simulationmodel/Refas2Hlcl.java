package com.variamos.refas.core.simulationmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
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

	public static final int ONE_SOLUTION = 0, NEXT_SOLUTION = 1;

	public Refas2Hlcl(Refas refas) {
		this.refas = refas;
		constraintGroups = new HashMap<String, MetaExpressionSet>();

	}

	/**
	 * Create a new HlclProgram with the expression of all concepts and
	 * relations and calls SWIProlog to return a solution or all solutions (only
	 * ONE_SOLUTION currently supported)
	 */

	public HlclProgram getHlclProgram()
	{
		HlclProgram hlclProgram = new HlclProgram();
		constraintGroups = new HashMap<String, MetaExpressionSet>();
		createVertexExpressions(null);
		createEdgeExpressions(null);
		// Previous call to createEdgeExpressions is required to fill the
		// attribute names for createGroupExpressions
		createGroupExpressions(null);

		List<AbstractExpression> transformations = new ArrayList<AbstractExpression>();
		for (MetaExpressionSet constraintGroup : constraintGroups.values())
			transformations.addAll(constraintGroup.getTransformations());

		for (AbstractExpression transformation : transformations) {
			idMap.putAll(transformation.getIndentifiers(f));
			if (transformation instanceof AbstractBooleanExpression) {
				hlclProgram
						.add(((AbstractBooleanExpression) transformation)
								.transform(f, idMap));
				// For negation testing
				// prog.add(((AbstractBooleanTransformation) transformation)
				// .transformNegation(f, idMap, true, false));
			} else if (transformation instanceof AbstractComparisonExpression) {
				hlclProgram
						.add(((AbstractComparisonExpression) transformation)
								.transform(f, idMap));
				// For negation testing
				// prog.add(((AbstractComparisonTransformation)
				// transformation)
				// .transformNegation(f, idMap));
			} else {
				hlclProgram
						.add(((AbstractComparisonExpression) transformation)
								.transform(f, idMap));
			}

		}
		return hlclProgram;
	}
	
	public boolean execute(int solutions) {
		if (solutions == 0 || swiSolver == null) {
			text = "";
			
			hlclProgram = getHlclProgram();

			Set<Identifier> identifiers = new TreeSet<Identifier>();
			for (Expression exp : hlclProgram) {
				// System.out.println(HlclUtil.getUsedIdentifiers(exp));
				identifiers.addAll(HlclUtil.getUsedIdentifiers(exp));
				text += exp + "\n";
			}
			swiSolver = new SWIPrologSolver(hlclProgram);

			try {
				swiSolver
						.solve(new Configuration(), new ConfigurationOptions());
			} catch (Exception e) {
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
			/*
			 * System.out.print(vertexId + " " + attribute + " " +
			 * vertex.getInstAttribute(attribute) .getAttributeType() + "; ");
			 */
		}
	}

	public MetaExpressionSet getElementConstraintGroup(String identifier,
			String type) {

		if (type.equals("vertex"))
			createVertexExpressions(identifier);
		else if (type.equals("edge"))
			createEdgeExpressions(identifier);
		else if (type.equals("groupdep"))
			createGroupExpressions(identifier);
		return constraintGroups.get(identifier);
	}

	public String getText() {
		return text;
	}

	private void createVertexExpressions(String identifier) {
		if (identifier == null)
			for (InstVertex elm : refas.getConstraintVertexCollection()) {
				constraintGroups.put(elm.getIdentifier(),
						new SingleElementExpressionSet(elm.getIdentifier(),
								idMap, f, elm));
			}
		else
			constraintGroups.put(identifier,
					new SingleElementExpressionSet(identifier, idMap, f, refas
							.getConstraintVertex().get(identifier)));
	}

	private void createEdgeExpressions(String identifier) {
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

	private void createGroupExpressions(String identifier) {
		createEdgeExpressions(null); // TODO define a better solution
		if (identifier == null)
			for (InstOverTwoRelation elm : refas
					.getInstGroupDependenciesCollection()) {
				constraintGroups.put(elm.getIdentifier(),
						new OverTwoElementsExpressionSet(elm.getIdentifier(),
								idMap, f, elm));
			}
		else
			constraintGroups.put(identifier,
					new OverTwoElementsExpressionSet(identifier, idMap, f,
							refas.getInstGroupDependencies().get(identifier)));

	}

	public String getElementTextConstraints(String identifier, String string) {
		String out = "";
		for (Expression expression : getElementConstraintGroup(identifier,
				string).getExpressions())
			out += expression.toString() + "\n";
		return out;
	}
}
