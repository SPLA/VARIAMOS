package com.variamos.gui.refas.editor;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.variamos.semantic.semanticsupport.SemanticConcept;
import com.variamos.semantic.semanticsupport.SemanticVariable;
import com.variamos.semantic.semanticsupport.SoftSemanticConcept;
import com.variamos.semantic.semanticsupport.SoftSemanticConceptSatisficing;
import com.variamos.syntax.instancesupport.InstElement;
import com.variamos.syntax.metamodelsupport.MetaVertex;
import com.variamos.syntax.semanticinterface.IntSemanticConcept;

public class VisualElement implements Comparable {

	private InstElement instElement;
	private IntSemanticConcept semanticElement;
	private boolean selected, notAvailable, updated;
	private Color green, red, yellow;
	private String otherParameters = "";

	public VisualElement(InstElement instElement) {
		defineColors();
		this.instElement = instElement;
		this.semanticElement = ((MetaVertex) instElement
				.getTransSupportMetaElement()).getTransSemanticConcept();
		updateValues();
		this.updated = false;

	}

	public String getElementId() {
		return instElement.getIdentifier();
	}

	public String getMetaElementId() {
		return instElement.getTransSupportMetaElement().getIdentifier();
	}

	public String getMetaElementName() {
		return instElement.getTransSupportMetaElement().getName();
	}

	private void defineColors() {
		red = new Color(255, 0, 0);
		green = new Color(146, 208, 80);
		yellow = new Color(0, 0, 255);
	}

	private void updateValues() {
		boolean newSelected, newNotAvailable, newCompExp;
		String newOtherParameters = "";
		newSelected = instElement.getInstAttribute("Selected").getAsBoolean();
		newNotAvailable = instElement.getInstAttribute("NotAvailable")
				.getAsBoolean();
		if (semanticElement instanceof SoftSemanticConceptSatisficing) {
			newOtherParameters = "{BoolExp:"
					+ instElement.getInstAttribute("CompExp").getAsBoolean()
					+ "}";
		}
		if (semanticElement instanceof SoftSemanticConcept) {
			newOtherParameters = "{Required: "
					+ instElement.getInstAttribute("SDReqLevel").getAsInteger()
					+ " Expected: "
					+ instElement.getInstAttribute("ClaimExpLevel")
							.getAsInteger() + "}";
		}
		if (selected != newSelected || notAvailable != newNotAvailable
				|| !otherParameters.equals(newOtherParameters)) {
			this.updated = true;
			selected = newSelected;
			notAvailable = newNotAvailable;
			otherParameters = newOtherParameters;
		} else
			updated = false;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	public JPanel showElement(boolean showNames, boolean updated) {
		if (updated)
			updateValues();
		JPanel row = new JPanel();
		JLabel label = null;
		if (showNames)
			label = new JLabel((String) instElement.getInstAttribute("name")
					.getValue());
		else
			label = new JLabel(instElement.getIdentifier());
		row.add(label);
		if (semanticElement instanceof SemanticVariable) {
			row.add(new JLabel("Type: "));
			row.add(new JLabel((String) instElement.getInstAttribute(
					"variableType").getValue()));
			row.add(new JLabel("Value: "));
			row.add(new JLabel(instElement.getInstAttribute("value")
					.getAsInteger() + ""));
		} else {
			if (selected)
				row.setBackground(green);
			if (notAvailable)
				row.setBackground(red);
			if (this.updated) {
				label.setForeground(yellow);
				row.setBorder(BorderFactory.createLineBorder(Color.black));
				label.setFont(new Font("default", Font.BOLD, 12));
			}
			row.add(new JLabel(otherParameters));
		}
		return row;
	}

	@Override
	public int compareTo(Object arg0) {
		return instElement.getIdentifier().compareTo((String) arg0);
	}

	public int getCols() {
		if (semanticElement instanceof SemanticVariable)
			return 1;
		if (semanticElement instanceof SoftSemanticConceptSatisficing)
			return 2;
		if (semanticElement instanceof SoftSemanticConcept)
			return 1;
		return 3;
	}

}
