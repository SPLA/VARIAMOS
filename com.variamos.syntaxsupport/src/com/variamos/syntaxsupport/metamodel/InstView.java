package com.variamos.syntaxsupport.metamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.variamos.syntaxsupport.metametamodel.MetaElement;
import com.variamos.syntaxsupport.metametamodel.MetaView;

public class InstView extends InstElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715363504436780438L;

	private List<InstView> childViews;

	private List<InstVertex> instVertices;

	private MetaView metaView;

	public List<InstVertex> getInstVertices() {
		return instVertices;
	}

	public void setInstVertices(List<InstVertex> instVertexs) {
		this.instVertices = instVertexs;
	}

	public List<InstView> getChildViews() {
		return childViews;
	}

	public void setChildViews(List<InstView> childViews) {
		this.childViews = childViews;
	}

	public InstView(String identifier) {
		super(identifier);
		instVertices = new ArrayList<InstVertex>();
		childViews = new ArrayList<InstView>();
	}

	public InstView(MetaView metaView) {
		super("");
		this.metaView = metaView;
		instVertices = new ArrayList<InstVertex>();
		childViews = new ArrayList<InstView>();
	}

	@Override
	public String getIdentifier() {
		return getIdentifier();

	}

	@Override
	public List<InstAttribute> getEditableVariables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, InstAttribute> getInstAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InstAttribute> getVisibleVariables() {
		// TODO Auto-generated method stub
		return null;
	}

	public MetaView getMetaView() {
		return metaView;
	}

	@Override
	public MetaElement getSupportMetaElement() {
		return metaView;
	}

	public void addInstVertex(InstVertex instVertex) {
		instVertices.add(instVertex);
	}

	public void addChildView(InstView instView) {
		childViews.add(instView);
	}
}
