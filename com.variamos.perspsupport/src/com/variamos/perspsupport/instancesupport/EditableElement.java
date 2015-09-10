package com.variamos.perspsupport.instancesupport;

import java.util.List;
import java.util.Map;
/**
 * An interface for dynamically editable elements with required
 * methods. Used for properties tab and future concepts.
 * Part of PhD work
 * at University of Paris 1
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-12-02
 */
public interface EditableElement {
	public String getAutoIdentifier();
	public List<InstAttribute> getEditableVariables(List<InstElement> parents);
	public Map<String, InstAttribute> getInstAttributes() ;
	public List<InstAttribute> getVisibleVariables(List<InstElement> parents);
}
