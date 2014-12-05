package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.cfm.productline.Prototype;
import com.cfm.productline.Variable;
import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public abstract class InstElement implements Serializable, Prototype,
		EditableElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2214656166959965220L;
/*	private String identifier;
	private Map<String, InstAttribute> instAttributes;
	private Map<String, InstRelation> instRelations;
*/
	public static final String  VAR_IDENTIFIER = "Identifier",
								VAR_INSTATTRIBUTES = "InstAttribute",
								VAR_INSTRELATIONS = "InstRelations";
	
	protected Map<String, Object> vars = new HashMap<>();

	public InstElement()
	{
		this(null, new HashMap<String, InstAttribute>(),
				new HashMap<String, InstRelation>());
	}
	
	public InstElement(String identifier) {
		this(identifier, new HashMap<String, InstAttribute>(),
				new HashMap<String, InstRelation>());
	}

	public InstElement(String identifier,
			Map<String, InstAttribute> instAttributes,
			Map<String, InstRelation> instRelations) {
		super();
		vars.put(VAR_IDENTIFIER, identifier);
		vars.put(VAR_INSTATTRIBUTES, instAttributes);
		vars.put(VAR_INSTRELATIONS, instRelations);
		
//		this.identifier = identifier;
//		this.instAttributes = instAttributes;
//		this.instRelations = instRelations;
	}
	public Object getVariable(String name){
		return vars.get(name);
	}
	
	public void setVariable(String name, Object value){
		vars.put(name, value);
	}	


	public String getIdentifier() {
		return (String)getVariable(VAR_IDENTIFIER);
		//return identifier;
	}

	public void setIdentifier(String identifier) {
		setVariable(VAR_IDENTIFIER, identifier);
		//this.identifier = identifier;
	}

	@SuppressWarnings("unchecked")
	public Map<String, InstAttribute> getInstAttributes() {
		return (Map<String, InstAttribute>)getVariable(VAR_INSTATTRIBUTES);
	//	return instAttributes;
	}
	
	public void setInstAttributes(Map<String, InstAttribute> instAttributes) {
		setVariable(VAR_INSTATTRIBUTES, instAttributes);
	}

	@SuppressWarnings("unchecked")
	public Map<String, InstRelation> getInstRelations() {
		return (Map<String, InstRelation> )getVariable(VAR_INSTRELATIONS);
	//	return instRelations;
	}
	
	public void setInstRelations(Map<String, InstAttribute> instRelations) {
		setVariable(VAR_INSTRELATIONS, instRelations);
	}


	@Override
	public abstract InstAttribute[] getEditableVariables();

	@SuppressWarnings("unchecked")
	public InstAttribute getInstAttribute(String name) {
		return ((Map<String, InstAttribute>)getVariable(VAR_INSTATTRIBUTES)).get(name);
	//	return instAttributes.get(name);
	}

	public void setInstAttribute(String name, Object value) {
		if (getInstAttribute(name) != null)
			getInstAttribute(name).setValue(value);
	}

	public void addInstAttribute(String name, AbstractAttribute metaAttribute,
			Object value) {
		if (getInstAttribute(name) == null) {
			InstAttribute instAttribute = new InstAttribute(name,
					metaAttribute,
					value == null ? metaAttribute.getDefaultValue() : value);
			getInstAttributes().put(name, instAttribute);
			//instAttributes.put(name, instAttribute);
		}
	}

	public String toString() {
	/*	if (getInstAttributes().get("name") == null)
			return "Error: no name attribure!";
		else
			return getInstAttributes().get("name").toString();
	}*/
		return "";
	}
	
	public Map<String, Object> getVars() {
		return vars;
	}
	
	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}
	public void clearInstAttributesClassObjects()
	{
		for (InstAttribute attribute : this.getInstAttributes().values())
		{
			attribute.setObject(null);
			//String type = attribute.getAttribute().getType();
			 //if (type.equals("MCLass") || type.equals("MClass"));
				// attribute.getValue()
		}
	}
}
