package com.variamos.dynsup.model;

import java.util.ArrayList;
import java.util.List;

import com.variamos.dynsup.interfaces.IntOpersConcept;

/**
 * A class to represent the vertex at semantic level. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 */
public class OpersVertex extends OpersElement implements
		IntOpersConcept {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3569174300072453550L;

	public static final String
	/**
	* 
	*/
	VAR_SELECTED_IDEN = "Selected";

	public OpersVertex() {
		this("", new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>());
	}

	public OpersVertex(String identifier) {
		super(identifier);
	}

	public OpersVertex(String identifier,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes) {
		super(identifier, disPropVisibleAttributes, disPropEditableAttributes,
				disPanelVisibleAttributes, disPanelSpacersAttributes);
	}

	public String toString() {

		return "name : " + getIdentifier();
	}
}
