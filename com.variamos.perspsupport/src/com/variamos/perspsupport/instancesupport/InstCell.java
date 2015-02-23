package com.variamos.perspsupport.instancesupport;

import java.io.Serializable;

/**
 * A class to support clones of instance elements with shared values. Part of
 * PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-01-29 *
 */
public class InstCell implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2175833122091796227L;
	private InstElement originalInstElement;
	private InstElement volatileInstElement;
	private boolean cloned;

	public InstCell() {
		this.cloned = false;
	}
	
	public InstCell(InstElement instElement, boolean cloned) {
		this.cloned = cloned;
		if (cloned)
			volatileInstElement = instElement;
		else
			originalInstElement = instElement;
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
	
	public boolean equals(Object o)
	{
		return getInstElement().equals(((InstCell)o).getInstElement());
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
}
