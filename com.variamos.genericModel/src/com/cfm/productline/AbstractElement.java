package com.cfm.productline;

import java.io.Serializable;

/**
 * @author jcmunoz
 * New class to generalize the modeling View
 * All model elements extends from AbstractElement
 */
public abstract class AbstractElement  implements Serializable, Prototype, Editable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1876789098L;
	protected String alias;
	public String getAlias() {
		return alias;
	}
	
	public static String multiLine(String str, int lineLenght)
	{
		String ini = str.replace("\n", "");
		String out = "";
		while (ini.length()>lineLenght)
		{
			String subIdeal = ini.substring(0, lineLenght);
			int cutLow = subIdeal.lastIndexOf(" ");
			int cutHigh = ini.indexOf(" ", lineLenght-1);
			int cut;
			if (cutLow<cutHigh && cutLow !=-1)
			{
				out = out+ini.substring(0,cutLow)+"\n";
				cut = cutLow;
			}
			else
				if (cutHigh !=-1)
				{
				out = out+ini.substring(0,cutHigh)+"\n";
				cut=cutHigh;
				}
			
				else
				{
					out = out+ini;
					ini = "";
					return out;
				}
			ini=ini.substring(cut+1);
		}
		
		
		return out+ini;
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
