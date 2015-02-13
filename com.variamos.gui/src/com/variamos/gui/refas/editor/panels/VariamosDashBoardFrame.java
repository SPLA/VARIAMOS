package com.variamos.gui.refas.editor.panels;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import com.variamos.gui.pl.editor.SpringUtilities;
import com.variamos.gui.refas.editor.VisualElement;
import com.variamos.refas.RefasModel;
import com.variamos.semantic.semanticsupport.SemanticConcept;
import com.variamos.semantic.semanticsupport.SemanticVariable;
import com.variamos.semantic.semanticsupport.SoftSemanticConcept;
import com.variamos.semantic.semanticsupport.SoftSemanticConceptSatisficing;
import com.variamos.syntax.instancesupport.InstVertex;
import com.variamos.syntax.semanticinterface.IntSemanticConcept;

public class VariamosDashBoardFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1719783068334097524L;

	private RefasModel refasModel;

	private JPanel solutionPanel = new JPanel(new SpringLayout());

	private Map<String, Map<String, VisualElement>> elements = null;

	public VariamosDashBoardFrame(RefasModel refasModel) {
		this.refasModel = refasModel;
		this.setTitle("Simulation DashBoard");

	}

	public void showDashBoard() {
		this.setVisible(true);
	}

	public void initSolutionPanel() {
		add(new JScrollPane(solutionPanel));
		int concepts = loadConcepts();
		SpringUtilities.makeCompactGrid(solutionPanel, concepts, 1, 4, 4, 4, 4);

	}

	private int loadConcepts() {
		int out = 0;
		elements = new HashMap<String, Map<String, VisualElement>>();
		for (InstVertex instVertex : refasModel
				.getVariabilityVertexCollection()) {
			if (instVertex.getInstAttribute("DashBoardVisible") != null
					&& instVertex.getInstAttribute("DashBoardVisible")
							.getAsBoolean()) {
				String metaId = instVertex.getTransSupportMetaElement()
						.getIdentifier();
				String instId = instVertex.getIdentifier();
				if (elements.get(metaId) == null)
					elements.put(metaId, new TreeMap<String, VisualElement>());
				VisualElement visualElement = new VisualElement(instVertex);
				elements.get(metaId).put(instId, visualElement);
				solutionPanel.add(visualElement.showElement());
				out++;
			}
		}
		return out;
	}

	public void updateDashBoard(RefasModel refasModel, boolean updateConcepts) {
		this.refasModel = refasModel;
		int concepts = 0;
		if (updateConcepts)
			loadConcepts();
		this.getContentPane().removeAll();
		solutionPanel.removeAll();
		for (Map<String, VisualElement> groupElement : elements.values()) {
			boolean title = false;
			JPanel typePanel = new JPanel(new SpringLayout());
			int elements = 0;
			for (VisualElement element : groupElement.values()) {
				if (!title) {
					solutionPanel.add(new JLabel("***"
							+ element.getMetaElementId() + "***"));
					title = true;
					concepts++;
				}
				typePanel.add(element.showElement());
				elements++;				
			}
			if(elements%3>0)
			{
				typePanel.add(new JLabel(""));
				elements++;
			}
			if(elements%3>0)
			{
				typePanel.add(new JLabel(""));
				elements++;
			}
			SpringUtilities.makeCompactGrid(typePanel, elements/3, 3, 4, 4, 4, 4);
			solutionPanel.add(typePanel);
			concepts++;
		}
		add(new JScrollPane(solutionPanel));
		SpringUtilities.makeCompactGrid(solutionPanel, concepts, 1, 4, 4, 4, 4);
		revalidate();
		pack();
	}
}
