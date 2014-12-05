package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.List;

import com.variamos.syntaxsupport.metametamodel.MetaDirectRelation;

public class InstRelation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6134025886276124795L;
	private int identifier;
	private MetaDirectRelation metaRelation;
	private InstElement fromRelation;
	private InstElement toRelation;
	private List<InstAttribute> attributes;
	
	public InstRelation()
	{}
	public int getIdentifier() {
		return identifier;
	}
	public MetaDirectRelation getMetaRelation() {
		return metaRelation;
	}
	public List<InstAttribute> getAttributes() {
		return attributes;
	}
	public InstElement getFromRelation() {
		return fromRelation;
	}
	public InstElement getToRelation() {
		return toRelation;
	}

}
