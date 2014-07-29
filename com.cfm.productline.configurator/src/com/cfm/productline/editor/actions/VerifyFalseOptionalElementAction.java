package com.cfm.productline.editor.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JTextArea;

import com.cfm.productline.ProductLine;
import com.cfm.productline.defectAnalyzer.VariabilityModelVerifier;
import com.cfm.productline.editor.ProductLineEditor;
import com.cfm.productline.editor.ProductLineGraph;
import com.cfm.productline.editor.defectAnalyzer.DefectAnalyzerUtil;
import com.cfm.productline.exceptions.FunctionalException;
import com.cfm.productline.model.defect.Defect;
import com.cfm.productline.model.defectAnalyzerModel.VariabilityModel;
import com.cfm.productline.model.enums.SolverEditorType;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

@SuppressWarnings("serial")
public class VerifyFalseOptionalElementAction extends ConfiguratorEditorAction {
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
		messagesArea.requestFocus();

		try {

			VariabilityModel variabilityModel = DefectAnalyzerUtil
					.transformProductLine(pl);


			VariabilityModelVerifier verifier = DefectAnalyzerUtil
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