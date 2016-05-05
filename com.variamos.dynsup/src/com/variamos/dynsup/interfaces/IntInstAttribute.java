package com.variamos.dynsup.interfaces;

public interface IntInstAttribute {

	public Object getValue();

	public boolean isAffectProperties();

	public void setValue(Object text);

	public void setType(String selectedItem);

	public String getType();

	public Integer getAsInteger();

	public Float getAsFloat();

	public Boolean getAsBoolean();

	public String getIdentifier();

	public String getDisplayName();

	public String getName();
	
	public Object getGroup();
	
	public void setGroup(Object o);
}
