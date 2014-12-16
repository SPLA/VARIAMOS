package com.variamos.refas.core.simulationmodel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency;
import com.variamos.refas.core.staticconcepts.Refas;
import com.variamos.refas.core.transformations.AndBooleanTransformation;
import com.variamos.refas.core.transformations.AssignBooleanTransformation;
import com.variamos.refas.core.transformations.DiffNumericTransformation;
import com.variamos.refas.core.transformations.EqualsComparisonTransformation;
import com.variamos.refas.core.transformations.GreaterOrEqualsBooleanTransformation;
import com.variamos.refas.core.transformations.ImplicationBooleanTransformation;
import com.variamos.refas.core.transformations.NotBooleanTransformation;
import com.variamos.refas.core.transformations.NumberNumericTransformation;
import com.variamos.refas.core.transformations.OrBooleanTransformation;
import com.variamos.refas.core.transformations.ProdNumericTransformation;
import com.variamos.refas.core.transformations.SumNumericTransformation;
import com.variamos.refas.core.types.CardinalityType;
import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;
import com.variamos.syntaxsupport.metametamodel.MetaDirectRelation;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metametamodel.MetaGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;
import com.variamos.syntaxsupport.metamodel.InstVertex;

/**
 * Class to create the Hlcl program. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public class Refas2Hlcl {
	private HlclFactory f = new HlclFactory();
	private List<AbstractConstraintGroup> constraintGroups;
	private String text;

	public Refas2Hlcl(Refas refas) {
		text = "";
		constraintGroups = new ArrayList<AbstractConstraintGroup>();
		Map<String, Identifier> idMap = new HashMap<>();
		createVertexExpressions(refas, idMap);
		//createEdgeExpressions(refas, idMap);
		// Previous call to createEdgeExpressions is required to fill the attribute names for
		// createGroupExpressions
		//createGroupExpressions(refas, idMap);

		List<AbstractTransformation> transformations = new ArrayList<AbstractTransformation>();
		for (AbstractConstraintGroup constraintGroup : constraintGroups)
			transformations.addAll(constraintGroup.getTransformations());

		HlclProgram prog = new HlclProgram();
		for (AbstractTransformation transformation : transformations) {			
			idMap.putAll(transformation.getIndentifiers(f));				
			if (transformation instanceof AbstractBooleanTransformation)
				prog.add(((AbstractBooleanTransformation) transformation)
						.transform(f, idMap));
			else
				prog.add(((AbstractComparisonTransformation) transformation)
						.transform(f, idMap));
		}

		Set<Identifier> identifiers = new TreeSet<Identifier>();
		for (Expression exp : prog) {
			identifiers.addAll(HlclUtil.getUsedIdentifiers(exp));
			text +=exp +"\n";
		}
		// Call the SWIProlog and obtain the result
		List<String> prologOut = null;
		int i = 0;
		for (Identifier identifier : identifiers) {
			String id = identifier.getId();
			String[] split = id.split("_");
			String conceptId = split[0];
			String attribute = split[1];
			InstVertex vertex = refas.getVertex(conceptId);
			if (vertex.getInstAttribute(attribute).getModelingAttributeType()
					.equals("Boolean"))
				/*
				 * if (prologOut.get(i).equals(1))
				 * vertex.getInstAttribute(attribute).setValue(true); else
				 * vertex.getInstAttribute(attribute).setValue(false);
				 */
				vertex.getInstAttribute(attribute).setValue(true); // TODO
																	// delete
			else
				// vertex.getInstAttribute(attribute).setValue(prologOut.get(i));
				System.out.print(conceptId
						+ " "
						+ attribute
						+ " "
						+ vertex.getInstAttribute(attribute)
								.getModelingAttributeType() + "; ");
		}
		System.out.println();

	}

	public String getText() {
		return text;
	}

	private void createVertexExpressions(Refas refas, Map<String, Identifier> idMap) {
		for (InstVertex elm : refas.getVariabilityVertexCollection()) {
			constraintGroups.add( new RestrictionConstraint(
					elm.getIdentifier(), idMap, f, elm));
		}
	}

	private void createEdgeExpressions(Refas refas,
			Map<String, Identifier> idMap) {
		for (InstEdge elm : refas.getConstraintInstEdgesCollection()) {
			constraintGroups.add( new DirectEdgeConstraintGroup(
					elm.getIdentifier(), idMap, f, elm));
		}
	}

	private void createGroupExpressions(Refas refas,
			Map<String, Identifier> idMap) {
		for (InstGroupDependency elm : refas
				.getInstGroupDependenciesCollection()) {
			constraintGroups.add(new GroupDependencyConstraintGroup(
					elm.getIdentifier(), idMap, f, elm));
		}
	}

}
