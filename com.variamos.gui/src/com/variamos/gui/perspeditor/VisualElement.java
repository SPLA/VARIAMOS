package com.variamos.gui.perspeditor;

import java.awt.Color;
import java.awt.Font;
import java.util.Collection;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstEnumeration;
import com.variamos.perspsupport.semanticinterface.IntSemanticElement;
import com.variamos.perspsupport.semanticsupport.SemanticVariable;
import com.variamos.perspsupport.semanticsupport.SoftSemanticConcept;
import com.variamos.perspsupport.semanticsupport.SoftSemanticConceptSatisficing;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaEnumeration;

public class VisualElement implements Comparable<VisualElement> {

	private InstElement instElement;
	private IntSemanticElement semanticElement;
	private boolean selected, notAvailable, updated;
	private Color green, red, yellow;
	private String otherParameters = "";

	public VisualElement(InstElement instElement) {
		defineColors();
		this.instElement = instElement;
		this.semanticElement = ((MetaElement) instElement
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
		boolean newSelected, newNotAvailable;
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
			row.add(new JLabel(" :"));
			if (instElement.getInstAttribute("variableType").getValue()
					.equals("Integer")) {
				row.add(new JLabel("int"));
				row.add(new JLabel(" :"));
				row.add(new JLabel(instElement.getInstAttribute(SemanticVariable.VAR_VALUE)
						.getAsInteger() + ""));
			} else if (instElement.getInstAttribute("variableType").getValue()
					.equals("Enumeration"))
			{
				row.add(new JLabel("enum"));
				row.add(new JLabel(" :"));
				Object object = instElement.getInstAttribute(
						"enumerationType").getValueObject();
				if (object != null) {
					@SuppressWarnings("unchecked")
					Collection<InstAttribute> values = (Collection<InstAttribute>) ((InstAttribute) ((InstEnumeration) object)
							.getInstAttribute(MetaEnumeration.VAR_METAENUMVALUE)).getValue();
					for (InstAttribute value : values) {
						String[] split = ((String) value.getValue())
								.split("-");
						String val = null;
						if (instElement.getInstAttribute(SemanticVariable.VAR_VALUE).getValue() instanceof Integer)
							val = ((Integer)instElement.getInstAttribute(SemanticVariable.VAR_VALUE).getValue()).toString();
						else

							val = (String)instElement.getInstAttribute(SemanticVariable.VAR_VALUE).getValue();
						if (split[0].equals(val))
							row.add(new JLabel(value + ""));
					}
				}
			}else if (instElement.getInstAttribute("variableType").getValue()
					.equals("Boolean")) {
				row.add(new JLabel("bool"));
				row.add(new JLabel(" :"));
				row.add(new JLabel(instElement.getInstAttribute(SemanticVariable.VAR_VALUE)
						.getAsBoolean() + ""));
			} else
				row.add(new JLabel("Other type"));

		} else {
			if (selected)
				row.setBackground(green);
			if (notAvailable)
				row.setBackground(red);
			if (this.updated) {
				label.setForeground(yellow);
				row.setBorder(BorderFactory.createLineBorder(Color.black));
				label.setFont(new Font("default", Font.BOLD, 10));
			}
			row.add(new JLabel(otherParameters));
		}
		return row;
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

	@Override
	public int compareTo(VisualElement arg0) {

		return instElement.getIdentifier().compareTo(arg0.getElementId());
	}

}
