package com.variamos.dynsup.interfaces;

import com.variamos.dynsup.model.InstanceModel;

/**
 * Public application interface useful to connect methods from the dynsup functionality to the GUI 
 * or standalone use
 * @author lufe0
 *
 */
public class APIDynsup {

	/**
	 * Creates the default elements (objects) of the Semantic Model (Semantic
	 * concepts). Elements are loaded in the Semantic perspective (advanced
	 * perspectiv) and defines the semantic for elements on the syntax
	 * perspective (associated to concepts).
	 */

	public static void createOperationsSuperstructure(InstanceModel instanceModel, boolean empty ) {
		instanceModel.createOperationsSuperstructure(empty);
	}
}
