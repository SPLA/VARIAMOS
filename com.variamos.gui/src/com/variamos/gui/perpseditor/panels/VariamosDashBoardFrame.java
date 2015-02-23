package com.variamos.gui.perpseditor.panels;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import com.variamos.gui.perspeditor.SpringUtilities;
import com.variamos.gui.perspeditor.VisualElement;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.perspmodel.RefasModel;

public class VariamosDashBoardFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1719783068334097524L;

	private RefasModel refasModel;

	private JPanel solutionPanel = new JPanel(new SpringLayout());

	private Map<String, Map<String, VisualElement>> elements = null;
	
	private boolean showNames = true;

	public VariamosDashBoardFrame(RefasModel refasModel) {
		this.refasModel = refasModel;
		this.setTitle("Simulation DashBoard");

	}

	public void showDashBoard(boolean visible) {
		this.setVisible(visible);
	}

	public void initSolutionPanel() {
		add(new JScrollPane(solutionPanel));
		int concepts = loadConcepts();
		SpringUtilities.makeCompactGrid(solutionPanel, concepts, 1, 4, 4, 4, 4);

	}

	private int loadConcepts() {
		int out = 0;
		elements = new HashMap<String, Map<String, VisualElement>>();
		for (InstElement instVertex : refasModel
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
			//	solutionPanel.add(visualElement.showElement(showNames, true));
				out++;
			}
		}
		return out;
	}

	public void setShowNames(boolean showNames)
	{
		this.showNames = showNames;
	}
	
	public void updateDashBoard(RefasModel refasModel, boolean updateConcepts, boolean updated) {
		this.refasModel = refasModel;
		int concepts = 0;
		if (updateConcepts)
			loadConcepts();
		this.getContentPane().removeAll();
		solutionPanel.removeAll();
		for (Map<String, VisualElement> groupElement : elements.values()) {
			boolean title = false;
			JPanel typePanel = new JPanel(new SpringLayout());
			int elements = 0, cols = 3;
			for (VisualElement element : groupElement.values()) {
				if (!title) {
					JLabel titleLab = new JLabel("***                 "
							+ element.getMetaElementName() + "s              ***");

					titleLab.setFont(new Font("default", Font.BOLD, 14));
					solutionPanel.add(titleLab);
					title = true;
					cols = element.getCols();
					concepts++;
				}
				typePanel.add(element.showElement(showNames, updated));
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
			SpringUtilities.makeCompactGrid(typePanel, elements/cols, cols, 4, 4, 4, 4);
			solutionPanel.add(typePanel);
			concepts++;
		}
		add(new JScrollPane(solutionPanel));
		SpringUtilities.makeCompactGrid(solutionPanel, concepts, 1, 4, 4, 4, 4);
		revalidate();
		pack();
	}
}
