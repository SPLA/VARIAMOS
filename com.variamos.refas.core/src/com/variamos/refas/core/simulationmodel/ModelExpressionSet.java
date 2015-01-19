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
import com.variamos.refas.core.expressions.LessBooleanExpression;
import com.variamos.refas.core.expressions.NotBooleanExpression;
import com.variamos.refas.core.expressions.NumberNumericExpression;
import com.variamos.refas.core.expressions.ProdNumericExpression;
import com.variamos.refas.core.expressions.SumNumericExpression;
import com.variamos.refas.core.refas.Refas;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;

public class ModelExpressionSet extends MetaExpressionSet {

	private Refas refas;
	private Map<String, List<BooleanExpression>> booleanExpressions = new HashMap<String, List<BooleanExpression>>();

	public List<BooleanExpression> getBooleanExpressionList(String element) {
		return booleanExpressions.get(element);
	}

	public ModelExpressionSet(String identifier, String description,
			Map<String, Identifier> idMap, HlclFactory hlclFactory,
			Refas refas, int execType) {
		super(identifier, description, idMap, hlclFactory);
		this.refas = refas;
		defineExpressions(execType);
	}

	private void defineExpressions(int execType) {
		AbstractNumericExpression rootOutExp = null;
		AbstractNumericExpression parentOutExp = null;
		if (execType == Refas2Hlcl.VAL_UPD_EXEC) {
			for (InstVertex vertex : refas.getVariabilityVertexCollection()) {
				if (vertex.getInstAttribute("Active").getAsBoolean()) {
					if (rootOutExp == null)
						rootOutExp = new SumNumericExpression(vertex,
								"IsRootFeature", true,
								new NumberNumericExpression(0));
					else
						rootOutExp = new SumNumericExpression(vertex,
								"IsRootFeature", true, rootOutExp);
					if (parentOutExp == null)
						parentOutExp = new ProdNumericExpression(vertex,
								"HasParent", true, new NumberNumericExpression(
										1));
					else
						parentOutExp = new ProdNumericExpression(vertex,
								"HasParent", true, parentOutExp);
				}
			}
			List<BooleanExpression> rootList = new ArrayList<BooleanExpression>();
			List<BooleanExpression> parentList = new ArrayList<BooleanExpression>();
			List<BooleanExpression> coreList = new ArrayList<BooleanExpression>();
			if (rootOutExp != null) {
				AbstractBooleanExpression transformation51 = new GreaterOrEqualsBooleanExpression(
						rootOutExp, new NumberNumericExpression(2));
				Map<String, Identifier> idMap = new HashMap<String, Identifier>();

				idMap.putAll(transformation51.getIdentifiers(getHlclFactory()));
				rootList.add(this.getHlclFactory().doubleImplies(
						this.getHlclFactory().newIdentifier("model_roots"),
						transformation51.transform(getHlclFactory(), idMap)));
				rootList.add(this.getHlclFactory().lessOrEqualsThan(
						this.getHlclFactory().newIdentifier("model_roots"),
						getHlclFactory().number(0)));
				booleanExpressions.put("Root", rootList);
			} else
				booleanExpressions.put("Root", rootList);
			if (parentOutExp != null) {
				AbstractBooleanExpression transformation51 = new LessBooleanExpression(
						parentOutExp, new NumberNumericExpression(1));
				Map<String, Identifier> idMap = new HashMap<String, Identifier>();

				idMap.putAll(transformation51.getIdentifiers(getHlclFactory()));
				parentList.add(this.getHlclFactory().doubleImplies(
						this.getHlclFactory().newIdentifier("model_parents"),
						transformation51.transform(getHlclFactory(), idMap)));
				parentList.add(this.getHlclFactory().lessOrEqualsThan(
						this.getHlclFactory().newIdentifier("model_parents"),
						getHlclFactory().number(0)));
				booleanExpressions.put("Parent", parentList);
			} else
				booleanExpressions.put("Parent", parentList);
			booleanExpressions.put("Core", coreList);
		}
	}
}
