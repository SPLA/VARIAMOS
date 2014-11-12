package com.cfm.productline;

import java.io.Serializable;

/**
 * @author jcmunoz
 * New class to generalize the modeling View
 * All model elements extends from AbstractElement
 */
public abstract class AbstractElement  implements Serializable, Prototype, Editable {
	public String getClassId()
	{
		return "Abs_";
		
	}
	public abstract String getIdentifier();
	public abstract void setIdentifier(String id);
	public abstract void setName(String in);
}
