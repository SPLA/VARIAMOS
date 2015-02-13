package com.variamos.gui.refas.editor;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.variamos.semantic.semanticsupport.SemanticConcept;
import com.variamos.semantic.semanticsupport.SemanticVariable;
import com.variamos.semantic.semanticsupport.SoftSemanticConcept;
import com.variamos.semantic.semanticsupport.SoftSemanticConceptSatisficing;
import com.variamos.syntax.instancesupport.InstElement;
import com.variamos.syntax.metamodelsupport.MetaVertex;
import com.variamos.syntax.semanticinterface.IntSemanticConcept;

public class VisualElement implements Comparable {

	private InstElement instElement;
	private boolean selected, notAvailable, compExp, updated, hasExp;
	private Color green, red, yellow;

	public VisualElement(InstElement instElement) {
		defineColors();
		this.instElement = instElement;
		updateValues();
		this.updated = false;

	}

	public String getElementId()
	{
		return instElement.getIdentifier();
	}

	public String getMetaElementId()
	{
		return instElement.getTransSupportMetaElement().getIdentifier();
	}
	
	
	private void defineColors() {
		red = new Color(255, 0, 0);
		green = new Color(146, 208, 80);
		yellow = new Color(0, 0, 255);
	}

	private void updateValues() {
		boolean newSelected, newNotAvailable, newCompExp;
		newSelected = instElement.getInstAttribute("Selected").getAsBoolean();
		newNotAvailable = instElement.getInstAttribute("NotAvailable")
				.getAsBoolean();
		if (instElement.getInstAttribute("CompExp") != null) {
			newCompExp = instElement.getInstAttribute("CompExp")
					.getAsBoolean();
			hasExp = true;
		} else {
			hasExp = false;
			newCompExp = false;
		}
		if (selected != newSelected || notAvailable != newNotAvailable
				|| compExp != newCompExp) {
			this.updated = true;
			selected = newSelected;
			notAvailable = newNotAvailable;
			compExp = newCompExp;
		}
		else
			updated = false;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	public JPanel showElement() {
		updateValues();
		IntSemanticConcept semanticElement = ((MetaVertex) instElement
				.getTransSupportMetaElement()).getTransSemanticConcept();
		JPanel row = new JPanel();
		if (semanticElement instanceof SemanticVariable) {
			row.add(new JLabel("Type: "));
			row.add(new JLabel((String) instElement.getInstAttribute(
					"variableType").getValue()));
			row.add(new JLabel("Value: "));
			row.add(new JLabel(instElement.getInstAttribute("value")
					.getAsInteger() + ""));
		} else {
			JLabel label = new JLabel(instElement.getIdentifier());
			if (selected)
				row.setBackground(green);
			if (notAvailable)
				row.setBackground(red);
			if (updated)
				label.setForeground(yellow);
			row.add(label);
		}
		if (semanticElement instanceof SoftSemanticConceptSatisficing) {

			row.add(new JLabel("Expression: "));
			row.add(new JLabel(instElement.getInstAttribute("CompExp")
					.getAsBoolean() + ""));
		}
		if (semanticElement instanceof SoftSemanticConcept) {
			row.add(new JLabel("SD Required: "));
			row.add(new JLabel(instElement.getInstAttribute("SDReqLevel")
					.getAsInteger() + ""));
			row.add(new JLabel("Claim Expected: "));
			row.add(new JLabel(instElement.getInstAttribute("ClaimExpLevel")
					.getAsInteger() + ""));
		}

		if (semanticElement instanceof SemanticConcept) {

		}
		return row;
	}

	@Override
	public int compareTo(Object arg0) {		
		return instElement.getIdentifier().compareTo((String)arg0);
	}
}
