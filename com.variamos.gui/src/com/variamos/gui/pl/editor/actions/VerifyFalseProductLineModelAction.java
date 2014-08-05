package com.variamos.gui.pl.editor.actions;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import com.cfm.productline.ProductLine;
import com.cfm.productline.defectAnalyzer.VariabilityModelVerifier;
import com.cfm.productline.exceptions.FunctionalException;
import com.cfm.productline.model.defect.Defect;
import com.cfm.productline.model.enums.SolverEditorType;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.pl.editor.ProductLineGraphEditor;
import com.variamos.pl.editor.defectAnalyzer.DefectAnalyzerUtil;

@SuppressWarnings("serial")
public class VerifyFalseProductLineModelAction extends
		AbstractEditorAction {
	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		
		ProductLine pl = null;
		SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;

		ProductLineGraphEditor editor = getEditor(e);
		
		JTextArea messagesArea = editor.getMessagesArea();
		StringBuilder outputMessage = new StringBuilder();
		
		editor.bringUpTab("Messages");
		
		/*mxGraphComponent graphComponent = editor.getGraphComponent();
		mxGraph graph = graphComponent.getGraph();
		ProductLineGraph plGraph = (ProductLineGraph) graph;
		pl = plGraph.getProductLine();*/
		
		
		pl = editor.getEditedProductLine();
		
		pl.printDebug(System.out);

		// Start verification operations
		// VOID MODEL
		// Start verification operations
		// VOID MODEL
		try {
			
			VariabilityModelVerifier verifier = DefectAnalyzerUtil
					.createVerifierClass(pl, prologEditorType);
			
			Defect falseProductLine = verifier.isFalsePLM();
			if (falseProductLine != null) {
				outputMessage.append("Model is a false product line");
			} else {
				outputMessage.append("Model is not a false product line");
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