package com.variamos.gui.pl.editor.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JTextArea;

import com.cfm.productline.ProductLine;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.defectAnalyzer.defectAnalyzer.DefectsVerifier;
import com.variamos.defectAnalyzer.model.VariabilityModel;
import com.variamos.defectAnalyzer.model.defects.Defect;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.pl.editor.defectAnalyzer.DefectAnalyzerUtil;

@SuppressWarnings("serial")
public class VerifyFalseOptionalElementAction extends AbstractEditorAction {
	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {

		ProductLine pl = null;
		SolverEditorType prologEditorType = SolverEditorType.GNU_PROLOG;

		VariamosGraphEditor editor = getEditor(e);
		editor.bringUpTab("Messages");
		/*mxGraphComponent graphComponent = editor.getGraphComponent();
		mxGraph graph = graphComponent.getGraph();
		ProductLineGraph plGraph = (ProductLineGraph) graph;
		pl = plGraph.getProductLine();*/
		
		pl = (ProductLine)editor.getEditedModel();

		JTextArea messagesArea = editor.getMessagesArea();
		StringBuilder outputMessage = new StringBuilder();
		messagesArea.requestFocus();

		try {

			VariabilityModel variabilityModel = DefectAnalyzerUtil
					.transformProductLine(pl);


			DefectsVerifier verifier = DefectAnalyzerUtil
					.createVerifierClass(pl, prologEditorType);

			if (!variabilityModel.getOptionalVariabilityElements().isEmpty()) {

				List<Defect> falseOptionalElementsList = verifier
						.identifyFalseOptionalFeatures(variabilityModel
								.getOptionalVariabilityElements());
				if (falseOptionalElementsList.size() > 0) {
					for (Defect falseOptionalElements : falseOptionalElementsList) {
						outputMessage
								.append(("FALSE OPTIONAL ELEMENTS" + falseOptionalElements
										.getId()));
						outputMessage.append("\n");
					}
				} else {
					outputMessage
							.append(("None variability element is false optional"));
				}
			}else{
				outputMessage
				.append(("This model does not have optional items to verify"));
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