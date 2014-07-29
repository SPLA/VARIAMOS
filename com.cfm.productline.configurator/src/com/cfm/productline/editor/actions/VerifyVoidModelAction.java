package com.cfm.productline.editor.actions;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import com.cfm.productline.ProductLine;
import com.cfm.productline.defectAnalyzer.VariabilityModelVerifier;
import com.cfm.productline.editor.ProductLineEditor;
import com.cfm.productline.editor.defectAnalyzer.DefectAnalyzerUtil;
import com.cfm.productline.exceptions.FunctionalException;
import com.cfm.productline.model.defect.Defect;
import com.cfm.productline.model.enums.SolverEditorType;

@SuppressWarnings("serial")
public class VerifyVoidModelAction extends ConfiguratorEditorAction {
	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {

		ProductLine pl = null;
		SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;

		ProductLineEditor editor = getEditor(e);
		editor.bringUpTab("Messages");
		/*mxGraphComponent graphComponent = editor.getGraphComponent();
		mxGraph graph = graphComponent.getGraph();
		ProductLineGraph plGraph = (ProductLineGraph) graph;
		pl = plGraph.getProductLine();*/
		
		pl = editor.getEditedProductLine();

		JTextArea messagesArea = editor.getMessagesArea();
		StringBuilder outputMessage = new StringBuilder();
		try {

			VariabilityModelVerifier verifier = DefectAnalyzerUtil
					.createVerifierClass(pl, prologEditorType);
			Defect voidModel = verifier.isVoid();
			if (voidModel != null) {
				outputMessage.append("MODEL IS VOID");
			} else {
				outputMessage.append("MODEL IS NOT VOID");
			}

		} catch (FunctionalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			outputMessage.append(e1.getMessage());
		}

		// Set the end messages
		messagesArea.setText(outputMessage.toString());
	}
}