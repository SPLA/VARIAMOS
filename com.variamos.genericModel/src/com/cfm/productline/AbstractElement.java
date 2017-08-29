package com.cfm.productline;

import java.io.Serializable;

/**
 * @author jcmunoz
 * New class to generalize the modeling View
 * All model elements extends from AbstractElement
 */
@Deprecated
public abstract class AbstractElement  implements Serializable, Prototype, Editable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1876789098L;
	protected String alias;
	public String getAlias() {
		return alias;
	}
	
	
	
	public String getClassId()
	{
		return "Abs_";
		
	}
	public AbstractElement()
	{
		alias = this.getClass().getSimpleName();
	}
	public AbstractElement(String alias)
	{
		this();
		if (alias != null)
			this.alias = alias;
	}
	public abstract String getIdentifier();
	public abstract void setIdentifier(String id);
	public abstract void setName(String in);
}
