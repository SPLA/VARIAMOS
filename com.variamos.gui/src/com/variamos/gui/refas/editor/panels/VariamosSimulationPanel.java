package com.variamos.gui.refas.editor.panels;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.variamos.gui.pl.editor.SpringUtilities;
import com.variamos.refas.RefasModel;
import com.variamos.semantic.semanticsupport.SemanticVariable;
import com.variamos.semantic.semanticsupport.SoftSemanticConcept;
import com.variamos.semantic.semanticsupport.SoftSemanticConceptSatisficing;
import com.variamos.syntax.instancesupport.InstVertex;
import com.variamos.syntax.semanticinterface.IntSemanticConcept;

public class VariamosSimulationPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1719783068334097524L;

	private RefasModel refas;

	private JPanel solutionPanel;

	public void initSolutionPanel() {
	//	solutionPanel.setMaximumSize(new Dimension(600, 55 * expressionCount));

	//	solutionPanel
	//			.setPreferredSize(new Dimension(600, 55 * expressionCount));
		add(new JScrollPane(solutionPanel));
		this.setAutoscrolls(true);
		int concepts = loadConcepts();
		SpringUtilities.makeCompactGrid(solutionPanel, concepts, 1, 4,
				4, 4, 4);

	}

	private int loadConcepts() {
		int out =0;
		for (InstVertex instVertex : refas.getVariabilityVertexCollection()) {
			if (instVertex.getInstAttribute("DashBoardVisible") != null
					&& instVertex.getInstAttribute("DashBoardVisible")
							.getAsBoolean()) {
				showVertex(instVertex);
				out++;
			}
		}
		return out;
	}

	private void showVertex(InstVertex instVertex) {
		IntSemanticConcept semanticElement = instVertex
				.getTransSupportMetaElement().getTransSemanticConcept();
		if (semanticElement instanceof SoftSemanticConceptSatisficing) {
			JPanel row = new JPanel();
			row.add(new JLabel(instVertex.getIdentifier()));
			row.add(new JLabel(" "));
			row.add(new JLabel("Selected: "));
			row.add(new JLabel(instVertex.getInstAttribute("Selected").getAsBoolean()+""));
			row.add(new JLabel("Expression: "));
			row.add(new JLabel(instVertex.getInstAttribute("CompExp").getAsBoolean()+""));
			solutionPanel.add(row);
		}
		if (semanticElement instanceof SoftSemanticConcept) {
			JPanel row = new JPanel();
			row.add(new JLabel(instVertex.getIdentifier()));
			row.add(new JLabel(" "));
			row.add(new JLabel("Selected: "));
			row.add(new JLabel(instVertex.getInstAttribute("Selected").getAsBoolean()+""));
			row.add(new JLabel("SD Required: "));
			row.add(new JLabel(instVertex.getInstAttribute("SDReqLevel").getAsInteger()+""));
			row.add(new JLabel("Claim Expected: "));
			row.add(new JLabel(instVertex.getInstAttribute("ClaimExpLevel").getAsInteger()+""));			
			solutionPanel.add(row);
		}
		if (semanticElement instanceof SemanticVariable) {
			JPanel row = new JPanel();
			row.add(new JLabel(instVertex.getIdentifier()));
			row.add(new JLabel(" "));
			row.add(new JLabel("Type: "));
			row.add(new JLabel((String)instVertex.getInstAttribute("variableType").getValue()));
			row.add(new JLabel("Value: "));
			row.add(new JLabel(instVertex.getInstAttribute("value").getAsInteger()+""));
			row.add(new JLabel("Claim Expected: "));
			row.add(new JLabel(instVertex.getInstAttribute("ClaimExpLevel").getAsBoolean()+""));			
			solutionPanel.add(row);
		}
	}

}
