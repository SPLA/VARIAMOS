package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import com.variamos.gui.core.viewcontrollers.AbstractVariamoGUIAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class VerifyVoidModelAction extends AbstractVariamoGUIAction {
	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {

	//	ProductLine pl = null;
	//	SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;

		VariamosGraphEditor editor = getEditor(e);
		editor.bringUpTab("Messages");
		/*mxGraphComponent graphComponent = editor.getGraphComponent();
		mxGraph graph = graphComponent.getGraph();
		ProductLineGraph plGraph = (ProductLineGraph) graph;
		pl = plGraph.getProductLine();*/
		
	//	pl = (ProductLine)editor.getEditedModel();

		JTextArea messagesArea = editor.getMessagesArea();
		StringBuilder outputMessage = new StringBuilder();
		/*try {
<<<<<<< HEAD
=======

>>>>>>> development
			DefectsVerifier verifier = DefectAnalyzerUtil
					.createVerifierClass(pl, prologEditorType);
			Defect voidModel = verifier.isVoid();
			if (voidModel != null) {
				outputMessage.append("MODEL IS VOID");
			} else {
				outputMessage.append("MODEL IS NOT VOID");
			}
<<<<<<< HEAD
=======

>>>>>>> development
		} catch (FunctionalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			outputMessage.append(e1.getMessage());
		}*/

		// Set the end messages
		messagesArea.setText(outputMessage.toString());
	}
}