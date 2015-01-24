package com.variamos.refas.core.simulationmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.RangeDomain;
import com.variamos.refas.core.expressions.AndBooleanExpression;
import com.variamos.refas.core.expressions.DoubleImplicationBooleanExpression;
import com.variamos.refas.core.expressions.EqualsComparisonExpression;
import com.variamos.refas.core.expressions.GreaterOrEqualsBooleanExpression;
import com.variamos.refas.core.expressions.LessBooleanExpression;
import com.variamos.refas.core.expressions.LessOrEqualsBooleanExpression;
import com.variamos.refas.core.expressions.NotBooleanExpression;
import com.variamos.refas.core.expressions.NumberNumericExpression;
import com.variamos.refas.core.expressions.ProdNumericExpression;
import com.variamos.refas.core.expressions.SumNumericExpression;
import com.variamos.refas.core.refas.Refas;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaVertex;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;

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
		AbstractNumericExpression coreOutExp = null;
		AbstractNumericExpression reqOutExp = null;
		AbstractNumericExpression prefOutExp = null;

		for (InstVertex vertex : refas.getVariabilityVertexCollection()) {
			MetaVertex metaElement = ((MetaVertex) vertex
					.getTransSupportMetaElement());
			IntSemanticElement semElement = metaElement
					.getTransSemanticConcept();
			while (semElement != null && semElement.getIdentifier() != null
					&& !semElement.getIdentifier().equals("SemGeneralElement"))
				semElement = semElement.getParent();
			if (semElement != null && semElement.getIdentifier() != null
					&& semElement.getIdentifier().equals("SemGeneralElement"))
				if (vertex.getInstAttribute("Active").getAsBoolean()) {
					if (execType == Refas2Hlcl.VAL_UPD_EXEC) {
						if (rootOutExp == null)
							rootOutExp = new SumNumericExpression(vertex,
									"IsRootFeature", true,
									new NumberNumericExpression(0));
						else
							rootOutExp = new SumNumericExpression(vertex,
									"IsRootFeature", true, rootOutExp);
						if (parentOutExp == null)
							parentOutExp = new ProdNumericExpression(vertex,
									"HasParent", true,
									new NumberNumericExpression(1));
						else
							parentOutExp = new ProdNumericExpression(vertex,
									"HasParent", true, parentOutExp);

						if (coreOutExp == null)
							coreOutExp = new SumNumericExpression(vertex,
									"Selected", true,
									new NumberNumericExpression(0));
						else
							coreOutExp = new SumNumericExpression(vertex,
									"Selected", true, coreOutExp);
					}
					if (execType == Refas2Hlcl.SIMUL_EXEC) {
						if (reqOutExp == null)
							reqOutExp = new SumNumericExpression(vertex,
									"NextReqSelected", true,
									new NumberNumericExpression(0));
						else
							reqOutExp = new SumNumericExpression(vertex,
									"NextReqSelected", true, reqOutExp);

						if (prefOutExp == null)
							prefOutExp = new SumNumericExpression(vertex,
									"NextPrefSelected", true,
									new NumberNumericExpression(0));
						else
							prefOutExp = new SumNumericExpression(vertex,
									"NextPrefSelected", true, prefOutExp);
					}
				}
			List<BooleanExpression> rootList = new ArrayList<BooleanExpression>();
			List<BooleanExpression> parentList = new ArrayList<BooleanExpression>();
			List<BooleanExpression> coreList = new ArrayList<BooleanExpression>();
			List<BooleanExpression> simulList = new ArrayList<BooleanExpression>();
			if (rootOutExp != null) {
				AbstractBooleanExpression transformation51 = new GreaterOrEqualsBooleanExpression(
						rootOutExp, new NumberNumericExpression(2));
				Map<String, Identifier> idMap = new HashMap<String, Identifier>();

				idMap.putAll(transformation51.getIdentifiers(getHlclFactory()));
				rootList.add(this.getHlclFactory().doubleImplies(
						this.getHlclFactory().newIdentifier("amodel_roots"),
						transformation51.transform(getHlclFactory(), idMap)));
				rootList.add(this.getHlclFactory().lessOrEqualsThan(
						this.getHlclFactory().newIdentifier("amodel_roots"),
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
						this.getHlclFactory().newIdentifier("amodel_parents"),
						transformation51.transform(getHlclFactory(), idMap)));
				parentList.add(this.getHlclFactory().lessOrEqualsThan(
						this.getHlclFactory().newIdentifier("amodel_parents"),
						getHlclFactory().number(0)));
				booleanExpressions.put("Parent", parentList);
			} else
				booleanExpressions.put("Parent", parentList);

			if (coreOutExp != null) {
				Map<String, Identifier> idMap = new HashMap<String, Identifier>();

				idMap.putAll(coreOutExp.getIdentifiers(getHlclFactory()));
				Identifier identifier = this.getHlclFactory().newIdentifier(
						"amodel_order");
				identifier.setDomain(new RangeDomain(0, 200));
				coreList.add(this.getHlclFactory().equals(identifier,
						coreOutExp.transform(getHlclFactory(), idMap)));
				/*
				 * coreList.add(this.getHlclFactory().lessOrEqualsThan(
				 * this.getHlclFactory().newIdentifier("model_parents"),
				 * getHlclFactory().number(0)));
				 */
				booleanExpressions.put("Core", coreList);
			} else
				booleanExpressions.put("Core", coreList);

			/*
			 * if (reqOutExp != null) { LessOrEqualsBooleanExpression
			 * compPrefOutExp = new LessOrEqualsBooleanExpression( prefOutExp,
			 * new NumberNumericExpression(0)); LessOrEqualsBooleanExpression
			 * compReqOutExp = new LessOrEqualsBooleanExpression( reqOutExp, new
			 * NumberNumericExpression(0)); Map<String, Identifier> idMap = new
			 * HashMap<String, Identifier>();
			 * 
			 * idMap.putAll(reqOutExp.getIdentifiers(getHlclFactory()));
			 * Identifier identifier1 = this.getHlclFactory().newIdentifier(
			 * "amodel_req"); identifier1.setDomain(new RangeDomain(0, 200));
			 * idMap.putAll(prefOutExp.getIdentifiers(getHlclFactory()));
			 * 
			 * simulList.add(this.getHlclFactory().equals(identifier1,
			 * reqOutExp.transform(getHlclFactory(), idMap)));
			 * 
			 * simulList.add(this.getHlclFactory().implies(
			 * compPrefOutExp.transform(getHlclFactory(), idMap),
			 * compReqOutExp.transform(getHlclFactory(), idMap)));
			 * 
			 * }
			 */
			if (prefOutExp != null) {
				Map<String, Identifier> idMap = new HashMap<String, Identifier>();

				idMap.putAll(prefOutExp.getIdentifiers(getHlclFactory()));
				Identifier identifier = this.getHlclFactory().newIdentifier(
						"amodel_pref");
				identifier.setDomain(new RangeDomain(0, 200));
				simulList.add(this.getHlclFactory().equals(
						this.getHlclFactory().newIdentifier("amodel_pref"),
						prefOutExp.transform(getHlclFactory(), idMap)));

				simulList.add(this.getHlclFactory().lessOrEqualsThan(
						this.getHlclFactory().newIdentifier("amodel_pref"),
						getHlclFactory().number(1)));

				booleanExpressions.put("Simul", simulList);
			} else
				booleanExpressions.put("Simul", simulList);

		}
	}
}
