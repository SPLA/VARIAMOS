package com.variamos.dynsup.staticexpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.dynsup.staticexprsup.AbstractBooleanExpression;
import com.variamos.dynsup.staticexprsup.AbstractComparisonExpression;
import com.variamos.dynsup.staticexprsup.AbstractExpression;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.HlclProgram;
import com.variamos.hlcl.Identifier;

/**
 * A class to represent the constraints. Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public abstract class ElementExpressionSet {

	protected void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Identifier of the contraint: InstEdge Identifier
	 */
	private String identifier;
	/**
	 * Semantic description of the relation
	 */
	private String description;
	/**
	 * 
	 */
	private List<AbstractExpression> elementExpressions;
	/**
	 * 
	 */
	private Map<String, List<AbstractExpression>> relaxableExpressions;
	/**
	 * 
	 */
	private Map<String, List<AbstractExpression>> compulsoryExpressions;
	/**
	 * 
	 */
	private Map<String, List<AbstractExpression>> verificationExpressions;

	/**
	 * 
	 */
	private Map<String, Identifier> idMap;
	/**
	 * 
	 */
	private HlclFactory hlclFactory;
	/**
	 * 
	 */
	private boolean optional = false;

	/**
	 * Assign the parameters on the abstract class
	 * 
	 * @param identifier
	 * @param description
	 */
	public ElementExpressionSet(String identifier, String description,
			Map<String, Identifier> idMap, HlclFactory hlclFactory) {
		super();
		elementExpressions = new ArrayList<AbstractExpression>();
		relaxableExpressions = new HashMap<String, List<AbstractExpression>>();
		compulsoryExpressions = new HashMap<String, List<AbstractExpression>>();
		verificationExpressions = new HashMap<String, List<AbstractExpression>>();
		this.idMap = idMap;
		this.hlclFactory = hlclFactory;
		this.identifier = identifier;
		this.description = description;
	}

	protected void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	protected void setOptional(boolean optional) {
		this.optional = optional;
	}

	protected Map<String, Identifier> getIdMap() {
		return idMap;
	}

	protected HlclFactory getHlclFactory() {
		return hlclFactory;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getDescription() {
		return description;
	}

	public boolean isOptional() {
		return optional;
	}

	public List<AbstractExpression> getElementExpressions() {
		return elementExpressions;
	}

	public Map<String, List<AbstractExpression>> getRelaxableExpressions() {
		return relaxableExpressions;
	}

	public Map<String, List<AbstractExpression>> getCompulsoryExpressions() {
		return compulsoryExpressions;
	}

	public Map<String, List<AbstractExpression>> getVerificationExpressions() {
		return verificationExpressions;
	}

	public List<AbstractExpression> getRelaxableExpressionList(String element) {
		return relaxableExpressions.get(element);
	}

	public List<AbstractExpression> getCompulsoryExpressionList(String element) {
		return compulsoryExpressions.get(element);
	}

	public List<AbstractExpression> getVerificationExpressionsList(
			String element) {
		return verificationExpressions.get(element);
	}

	/**
	 * Expression for textual representation
	 * 
	 * @return
	 */
	public List<Expression> getExpressions() {
		List<Expression> out = new ArrayList<Expression>();
		for (AbstractExpression expression : elementExpressions) {
			idMap.putAll(expression.getIdentifiers(hlclFactory));
			out.add(expression.transform(hlclFactory, idMap));
		}
		return out;
	}

	public List<Expression> getExpressionsNegations() {
		List<Expression> out = new ArrayList<Expression>();
		for (AbstractExpression transformation : elementExpressions) {
			idMap.putAll(transformation.getIdentifiers(hlclFactory));
			if (transformation instanceof AbstractBooleanExpression)
				out.add(((AbstractBooleanExpression) transformation)
						.transformNegation(hlclFactory, idMap, false, true));
		}
		return out;
	}

	public HlclProgram getHlclElementExpressions() {
		HlclProgram prog = new HlclProgram();
		for (AbstractExpression transformation : elementExpressions) {
			idMap.putAll(transformation.getIdentifiers(hlclFactory));
			if (transformation instanceof AbstractBooleanExpression)
				prog.add(((AbstractBooleanExpression) transformation)
						.transform(hlclFactory, idMap));
			else
				prog.add(((AbstractComparisonExpression) transformation)
						.transform(hlclFactory, idMap));
		}
		return prog;
	}

	public HlclProgram getHlclRelaxableExpressions(String element) {
		HlclProgram prog = new HlclProgram();
		for (AbstractExpression transformation : relaxableExpressions
				.get(element)) {
			idMap.putAll(transformation.getIdentifiers(hlclFactory));
			if (transformation instanceof AbstractBooleanExpression)
				prog.add(((AbstractBooleanExpression) transformation)
						.transform(hlclFactory, idMap));
			else
				prog.add(((AbstractComparisonExpression) transformation)
						.transform(hlclFactory, idMap));
		}
		return prog;
	}

}
