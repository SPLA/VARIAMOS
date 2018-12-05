package com.variamos.dynsup.instance;

import java.io.Serializable;

import com.mxgraph.model.mxCell;

/**
 * A class to support clones of instance elements with shared values. Part of
 * PhD work at University of Paris 1
 * 
 * @author Juan C. Muï¿½oz Fernï¿½ndez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-01-29 *
 */
public class InstCell implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2175833122091796227L;
	private InstElement originalInstElement;
	private InstElement volatileInstElement;
	private boolean cloned;
	private mxCell mxCellinst;

	public InstCell() {
	}

	public InstCell(mxCell mxCellinst) {
		this.mxCellinst = mxCellinst;
	}

	public InstCell(mxCell mxCellinst, InstElement instElement, boolean cloned) {
		this.cloned = cloned;
		if (cloned)
			volatileInstElement = instElement;
		else
			originalInstElement = instElement;
		this.mxCellinst = mxCellinst;
	}

	@Override
	public InstCell clone() throws CloneNotSupportedException {
		InstCell out = (InstCell) super.clone();
		if (originalInstElement != null)
			out.originalInstElement = originalInstElement.clone();
		if (volatileInstElement != null)
			out.volatileInstElement = volatileInstElement.clone();
		return out;
	}

	public InstElement getOriginalInstElement() {
		return originalInstElement;
	}

	public void setOriginalInstElement(InstElement originalInstElement) {
		this.originalInstElement = originalInstElement;
	}

	public InstElement getInstElement() {
		if (cloned)
			return volatileInstElement;
		else
			return originalInstElement;
	}

	public boolean equals(Object o) {
		if (getInstElement() != null && o != null
				&& ((InstCell) o).getInstElement() != null)
			return getInstElement().equals(((InstCell) o).getInstElement());
		else
			return false;
	}

	public void setInstElement(InstElement instElement) {
		if (cloned)
			this.volatileInstElement = instElement;
		else
			originalInstElement = instElement;
	}

	public boolean isCloned() {
		return cloned;
	}

	public void setCloned(boolean cloned) {
		this.cloned = cloned;
	}

	public String toString() {
		if (getInstElement() != null)
			return getInstElement().toString();
		else
			return null;
	}

	public double getWidth() {
		return mxCellinst.getGeometry().getWidth();
	}

	public void setMxCell(mxCell cell) {
		mxCellinst = cell;

	}
}

