package com.variamos.io;

import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import com.cfm.productline.Constraint;
import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.constraints.ExcludesConstraint;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.cfm.productline.constraints.MandatoryConstraint;
import com.cfm.productline.constraints.OptionalConstraint;
import com.cfm.productline.constraints.RequiresConstraint;

import constraints.CNFClause;
import constraints.CNFLiteral;
import constraints.PropositionalFormula;
import fm.FeatureGroup;
import fm.FeatureModel;
import fm.FeatureModelException;
import fm.FeatureTreeNode;
import fm.GroupedFeature;
import fm.SolitaireFeature;
import fm.XMLFeatureModel;
//FIXME  change reader
public class SXFMReader {
	public SXFMReader() {

	}

	public ProductLine readFile(String filename) throws FeatureModelException {

		FeatureModel featureModel = new XMLFeatureModel(filename,
				XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		featureModel.loadModel();

		ProductLine pl = new ProductLine();

		for (FeatureTreeNode node : featureModel.getNodes()) {
			if (!(node instanceof FeatureGroup))// This node has no information
				pl.addVariabilityPoint(new VariabilityElement(null, node
						.getID()));

			addConstraintFrom(pl, node);
		}

		for (PropositionalFormula cons : featureModel.getConstraints()) {
			addFormula(pl, cons);
		}

		pl.setName(filename);

		return pl;
	}

	private void addFormula(ProductLine pl, PropositionalFormula formula) {

		Constraint c = null;
		if (formula.countVars() == 2) {
			Collection<CNFClause> clauses = formula.toCNFClauses();
			Object[] clausesObjs = clauses.toArray();
			CNFClause clause = (CNFClause) clausesObjs[0];
			List<CNFLiteral> literals = clause.getLiterals();

			if (!literals.get(0).isPositive()) {
				if (!literals.get(1).isPositive())
					// ~A or ~B
					c = new ExcludesConstraint(literals.get(0).getVariable()
							.getID(), literals.get(1).getVariable().getID());
				else
					c = new RequiresConstraint(literals.get(0).getVariable()
							.getID(), literals.get(1).getVariable().getID());
			} else {
				c = new GenericConstraint(formula.getFormula());
			}
		} else {
			c = new GenericConstraint(formula.getFormula());
		}

		c.setIdentifier(formula.getName());
		pl.addConstraint(c);
	}

	private void addConstraintFrom(ProductLine pl, FeatureTreeNode node) {
		FeatureTreeNode parent = (FeatureTreeNode) node.getParent();
		if (parent == null) // this is the root node, there are not rules
			return;

		if (node instanceof GroupedFeature) {
			return;
		}

		Constraint c = null;
		if (node instanceof SolitaireFeature) {
			SolitaireFeature sf = (SolitaireFeature) node;

			if (sf.isOptional()) {// Optional relation
				c = new OptionalConstraint(parent.getID(), node.getID());
			} else {
				c = new MandatoryConstraint(parent.getID(), node.getID());
			}
			c.setIdentifier(sf.getID());
		}

		if (node instanceof FeatureGroup) {
			FeatureGroup fg = (FeatureGroup) node;
			c = new GroupConstraint(parent.getID(), fg.getMin(), fg.getMax());

			@SuppressWarnings("unchecked")
			Enumeration<FeatureTreeNode> children = fg.children();
			while (children.hasMoreElements()) {
				FeatureTreeNode child = children.nextElement();
				((GroupConstraint) c).addChildId(child.getID());
			}
		}
		pl.addConstraint(c);
	}
}

