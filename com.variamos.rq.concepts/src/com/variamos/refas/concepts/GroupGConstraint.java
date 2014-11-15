package com.variamos.refas.concepts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public static final String 	VAR_IDENTIFIER = "Identifier",
			VAR_DESCRIPTION = "Description",
			VAR_VISIBILITY = "Visibility",
			VAR_VALIDITY = "Validity",
			VAR_ALLOCATION = "Allocation",
			VAR_TEXT = "Text",
					VAR_LOWERLIMIT = "Lower limit",
					VAR_UPPERLIMIT = "Upper limit",
					VAR_SHOWLIMIT = "Range/Cardinality",
					VAR_CARDINALITY = "Carinality (AND,OR,XOR)",
					VAR_TYPE = "Type(Means-ends, required.etc)todo:enum",
					VAR_PARENT = "Parent";
	
	protected Map<String, Variable> vars = new HashMap<>();
	
	private List<String> children;

	public GroupGConstraint() {
		super();
		
		vars.put(VAR_IDENTIFIER, StringType.newVariable(VAR_IDENTIFIER));
		vars.put(VAR_DESCRIPTION, StringType.newVariable(VAR_DESCRIPTION));
		vars.put(VAR_VISIBILITY, BooleanType.newVariable(VAR_VISIBILITY));
		vars.put(VAR_VALIDITY, BooleanType.newVariable(VAR_VALIDITY));
		vars.put(VAR_ALLOCATION, StringType.newVariable(VAR_ALLOCATION));
		vars.put(VAR_TEXT, StringType.newVariable(VAR_TEXT));
		vars.put(VAR_LOWERLIMIT, IntegerType.newVariable(VAR_LOWERLIMIT));
		vars.put(VAR_UPPERLIMIT, IntegerType.newVariable(VAR_UPPERLIMIT));
		vars.put(VAR_SHOWLIMIT, BooleanType.newVariable(VAR_SHOWLIMIT));
		vars.put(VAR_CARDINALITY, StringType.newVariable(VAR_CARDINALITY));
		vars.put(VAR_TYPE, StringType.newVariable(VAR_TYPE));
		vars.put(VAR_PARENT, StringType.newVariable(VAR_PARENT));
		
		setVariableValue(VAR_VISIBILITY, Boolean.TRUE);
		setVariableValue(VAR_VALIDITY, Boolean.TRUE);

		children = new ArrayList<>();
	}

	public Variable getVariable(String name){
		return vars.get(name);
	}
	
	public void setVariableValue(String name, Object value){
		//GARA
		getVariable(name).setValue(value);
	}
	
	public Object getVariableValue(String name){
		return getVariable(name).getValue();
	}
	
	public GroupGConstraint(String alias) {
		this ();
		if (alias != null)
			this.alias = alias;
	}
	
	public GroupGConstraint(String parent, int lowerLimit, int upperLimit) {
		this();
		setVariableValue(VAR_LOWERLIMIT, lowerLimit);
		setVariableValue(VAR_UPPERLIMIT, upperLimit);
		setVariableValue(VAR_SHOWLIMIT, true);
		setVariableValue(VAR_TYPE, "");
		setVariableValue(VAR_PARENT, firstUpperCaseString(parent));

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

		setVariableValue(VAR_PARENT, firstUpperCaseString(id));


		// If the parent was a child, remove it.
		children.remove(getVariableValue(VAR_PARENT));
	}

	public String getParent() {
		// if( children.size() == 0 )
		// return null;
		// return children.get(0);

		return getVariableValue(VAR_PARENT).toString();
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
		related.add(getVariableValue(VAR_PARENT).toString());
		related.addAll(children);
		return related;
		// return children;
	}

	public int getLowerLimit() {
		return Integer.parseInt(getVariableValue(VAR_LOWERLIMIT).toString());
	}

	public void setLowerLimit(int lowerLimit) {
		setVariableValue(VAR_LOWERLIMIT, lowerLimit);
	}

	public int getUpperLimit() {
		return Integer.parseInt(getVariableValue(VAR_UPPERLIMIT).toString());

	}

	public void setUpperLimit(int upperLimit) {
		setVariableValue(VAR_UPPERLIMIT, upperLimit);
	}

	public void addChildId(String childId) {

		if (childId.equals(getVariableValue(VAR_PARENT).toString()))
			setVariableValue(VAR_PARENT, null);
		children.add(firstUpperCaseString(childId));
	}

	public String getCardinalityString() {
		StringBuffer buf = new StringBuffer();
		String upper = (getUpperLimit() == INFINITE) ? "*" : "" + getUpperLimit();
		if ((Boolean)getVariableValue(VAR_SHOWLIMIT))
			buf.append("[").append(getLowerLimit()).append(", ").append(upper).append("]");
		else
			buf.append(getVariableValue(VAR_CARDINALITY));
		buf.append(" - "+getVariableValue(VAR_TYPE));
		return buf.toString();
	}

	@Override
	public String getText() {
		StringBuffer buf = new StringBuffer();

		String cardString = getCardinalityString();
		buf.append(getVariableValue(VAR_PARENT).toString()).append(" ").append(cardString).append("{");

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
		return new Variable[]{ 		vars.get(VAR_DESCRIPTION),
		vars.get(VAR_VISIBILITY), 
		vars.get(VAR_VALIDITY),
		vars.get(VAR_ALLOCATION), 
		vars.get(VAR_TEXT), 
		vars.get(VAR_LOWERLIMIT),
		vars.get(VAR_UPPERLIMIT),
		vars.get(VAR_SHOWLIMIT), 
		vars.get(VAR_CARDINALITY), 
		vars.get(VAR_TYPE)  };
	}

//	public Variable getVarLowerLimit() {
//		return getVariableValue(VAR_LOWERLIMIT);
//	}

	public void setVarLowerLimit(Variable varLowerLimit) {
		setVariableValue(VAR_LOWERLIMIT, varLowerLimit);
	}

//	public Variable getVarUpperLimit() {
//		return getVariableValue(VAR_UPPERLIMIT);
//	}

	public void setVarUpperLimit(Variable varUpperLimit) {
		setVariableValue(VAR_UPPERLIMIT, varUpperLimit);
	}

}
