package com.variamos.gui.pl.editor.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JTextArea;

import com.cfm.productline.ProductLine;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.defectAnalyzer.defectAnalyzer.VariabilityModelVerifier;
import com.variamos.defectAnalyzer.model.VariabilityModel;
import com.variamos.defectAnalyzer.model.defects.Defect;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.pl.editor.defectAnalyzer.DefectAnalyzerUtil;

@SuppressWarnings("serial")
public class VerifyDeadElementAction extends AbstractEditorAction {
	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {

		ProductLine pl = null;
		SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;

		VariamosGraphEditor editor = getEditor(e);
		editor.bringUpTab("Messages");
		//vamos igual
		
		//QUITE ESTAS 4
		/*mxGraphComponent graphComponent = editor.getGraphComponent();
		mxGraph graph = graphComponent.getGraph();
		ProductLineGraph plGraph = (ProductLineGraph) graph;
		pl = plGraph.getProductLine();*/
		
		//PUSE ESTA
		pl = (ProductLine)editor.getEditedModel();
		//pl.printDebug(System.out);
		

		JTextArea messagesArea = editor.getMessagesArea();
		StringBuilder outputMessage = new StringBuilder();
		messagesArea.requestFocus();
		// Start verification operations
		// VOID MODEL
		// Start verification operations
		// VOID MODEL
		try {

			messagesArea.setText("Analyzing...");
			VariabilityModel variabilityModel = DefectAnalyzerUtil
					.transformProductLine(pl);
			
			//System.out.println(variabilityModel.toString());

			VariabilityModelVerifier verifier = DefectAnalyzerUtil
					.createVerifierClass(pl, prologEditorType);

			List<Defect> deadFeatures = verifier
					.identifyDeadFeatures(variabilityModel.getElements());

			if (!deadFeatures.isEmpty()) {
				for (Defect deadElement : deadFeatures) {
					outputMessage
					.append(("DEAD FEATURE " + pl.getVariabilityElement(deadElement.getId()).getName()));
					//outputMessage.append(("DEAD FEATURE " + deadElement.getId()));
					outputMessage.append("\n");
				}
			} else {
				outputMessage.append(("None variability element is dead"));
			}

		} catch (FunctionalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			outputMessage.append(e1.getMessage());
		}

		// Set the end messages
		messagesArea.setText(outputMessage.toString());
		messagesArea.requestFocus();
	}
}