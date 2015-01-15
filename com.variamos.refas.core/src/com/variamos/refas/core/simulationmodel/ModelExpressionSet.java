package com.variamos.refas.core.simulationmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.variamos.refas.core.expressions.AndBooleanExpression;
import com.variamos.refas.core.expressions.DoubleImplicationBooleanExpression;
import com.variamos.refas.core.expressions.GreaterOrEqualsBooleanExpression;
import com.variamos.refas.core.expressions.NotBooleanExpression;
import com.variamos.refas.core.expressions.NumberNumericExpression;
import com.variamos.refas.core.expressions.SumNumericExpression;
import com.variamos.refas.core.refas.Refas;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;

public class ModelExpressionSet extends MetaExpressionSet {

	private Refas refas;
	private List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();

	public List<BooleanExpression> getBooleanExpressions() {
		return booleanExpressions;
	}

	public ModelExpressionSet(String identifier, String description,
			Map<String, Identifier> idMap, HlclFactory hlclFactory,
			Refas refas, int execType) {
		super(identifier, description, idMap, hlclFactory);
		this.refas = refas;
		defineTransforamtions(execType);
	}

	private void defineTransforamtions(int execType) {
		AbstractNumericExpression rootOutExp = null;
		for (InstVertex vertex : refas.getVariabilityVertexCollection()) {
			if (vertex.getInstAttribute("Active").getAsBoolean()) {
					if (rootOutExp == null)
						rootOutExp = new SumNumericExpression(vertex,
								"IsRootFeature", true,
								new NumberNumericExpression(0));
					else
						rootOutExp = new SumNumericExpression(vertex,
								"IsRootFeature", true, rootOutExp);
			}
		}
		if (rootOutExp != null) {
			AbstractBooleanExpression transformation51 = new GreaterOrEqualsBooleanExpression(
					rootOutExp, new NumberNumericExpression(2));
			Map<String, Identifier> idMap = new HashMap<String, Identifier>();
			idMap.putAll(transformation51.getIdentifiers(getHlclFactory()));
			booleanExpressions.add(this.getHlclFactory().doubleImplies(
					this.getHlclFactory().newIdentifier("model_roots"),
					transformation51.transform(getHlclFactory(), idMap)));
			booleanExpressions.add(this.getHlclFactory().lessOrEqualsThan(
					this.getHlclFactory().newIdentifier("model_roots"),
					getHlclFactory().number(0)));
		}
	}
}
