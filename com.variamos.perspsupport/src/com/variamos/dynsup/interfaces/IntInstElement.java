package com.variamos.dynsup.interfaces;

import java.util.List;
import java.util.Map;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstElement;

/**
 * An interface for dynamically editable elements with required methods. Used
 * for properties tab and future concepts. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-12-02
 */
public interface IntInstElement {
	public String getIdentifier();

	public List<InstAttribute> getEditableVariables(
			List<InstElement> syntaxParents);

	public Map<String, InstAttribute> getInstAttributes();

	public List<InstAttribute> getVisibleVariables(
			List<InstElement> syntaxParents);
}
