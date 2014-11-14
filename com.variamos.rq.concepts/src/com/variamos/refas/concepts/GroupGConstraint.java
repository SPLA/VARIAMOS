package com.variamos.refas.concepts;

import java.util.ArrayList;
import java.util.List;

import com.cfm.productline.Constraint;
import com.cfm.productline.Editable;
import com.cfm.productline.Variable;
import com.cfm.productline.type.BooleanType;
import com.cfm.productline.type.IntegerType;
import com.cfm.productline.type.StringType;

@SuppressWarnings("serial")
public class GroupGConstraint extends Constraint implements Editable {

	public static final int INFINITE = -1;

	//private int lowerLimit, upperLimit;	
	private Variable varLowerLimit = IntegerType.newVariable("lowerLimit"), 
					varUpperLimit = IntegerType.newVariable("upperLimit"),
					varShowLimit = BooleanType.newVariable("ShowLimit"),
					varCardinality = StringType.newVariable("Cardinality"),
					varType = StringType.newVariable("Type");
	private List<String> children;
	private String parent;

	public GroupGConstraint() {
		super();
		parent = null;
		children = new ArrayList<>();
	}

	public GroupGConstraint(String parent, int lowerLimit, int upperLimit) {
		this();
		varLowerLimit.setValue(lowerLimit);
		varUpperLimit.setValue(upperLimit);
		varShowLimit.setValue(true);
		varType.setValue("");
		//this.lowerLimit = lowerLimit;
		//this.upperLimit = upperLimit;
		// children.add(parent);
		this.parent = firstUpperCaseString(parent);
	}

	// This element is at the position 0 at all times
	public void setParent(String id) {
		// //Remove the previous parent
		// String idPrev = getParent();
		// if( idPrev != null )
		// children.remove(idPrev);
		//
		// children.remove(id);
		// children.add(0, id);

		parent = firstUpperCaseString(id);

		// If the parent was a child, remove it.
		children.remove(parent);
	}

	public String getParent() {
		// if( children.size() == 0 )
		// return null;
		// return children.get(0);

		return parent;
	}

	public int getChildCount() {
		// return children.size() - 1;
		return children.size();
	}

	public String getChildId(int index) {
		// return children.get(index + 1);
		return children.get(index);
	}

	@Override
	public List<String> getRelatedIds() {
		List<String> related = new ArrayList<>(1 + children.size());
		related.add(parent);
		related.addAll(children);
		return related;
		// return children;
	}

	public int getLowerLimit() {
		return varLowerLimit.getAsInteger();
	}

	public void setLowerLimit(int lowerLimit) {
		varLowerLimit.setValue(lowerLimit);
	}

	public int getUpperLimit() {
		return varUpperLimit.getAsInteger();
	}

	public void setUpperLimit(int upperLimit) {
		varUpperLimit.setValue(upperLimit);
	}

	public void addChildId(String childId) {
		// Adding children before parent... must add a null in parent
		// if( children.size() == 0 )
		// children.add(null);
		//
		// //What if the children is the parent?
		// if( childId.equals(getParent()) )
		// setParent(null);
		//
		// children.add(childId);
		// If this child was the parent?
		if (childId == parent)
			parent = null;
		children.add(firstUpperCaseString(childId));
	}

	public String getCardinalityString() {
		StringBuffer buf = new StringBuffer();
		String upper = (getUpperLimit() == INFINITE) ? "*" : "" + getUpperLimit();
		if (varShowLimit.getAsBoolean())
			buf.append("[").append(getLowerLimit()).append(", ").append(upper).append("]");
		else
			buf.append(varCardinality.toString());
		buf.append(" - "+varType.toString());
		return buf.toString();
	}

	@Override
	public String getText() {
		StringBuffer buf = new StringBuffer();

		String cardString = getCardinalityString();
		buf.append(parent).append(" ").append(cardString).append("{");

		for (int i = 0; i < children.size(); i++) {
			buf.append(firstUpperCaseString(children.get(i)));
			if (i < children.size() - 1)
				buf.append(", ");
		}
		buf.append("}");
		return buf.toString();
	}

	public List<String> getChildren() {
		return children;
	}

	public void setChildren(List<String> children) {
		List<String> childrenSet = new ArrayList<String>();
		for (int i = 0; i < children.size(); i++) {
			childrenSet.add(firstUpperCaseString(children.get(i)));

		}

		this.children = childrenSet;
	}

	public void removeChild(String id) {

		children.remove(firstUpperCaseString(id));
	}

	@Override
	public Variable[] getEditableVariables() {
		return new Variable[]{ varLowerLimit, varUpperLimit, varShowLimit, varCardinality, varType };
	}

	public Variable getVarLowerLimit() {
		return varLowerLimit;
	}

	public void setVarLowerLimit(Variable varLowerLimit) {
		this.varLowerLimit = varLowerLimit;
	}

	public Variable getVarUpperLimit() {
		return varUpperLimit;
	}

	public void setVarUpperLimit(Variable varUpperLimit) {
		this.varUpperLimit = varUpperLimit;
	}

}
