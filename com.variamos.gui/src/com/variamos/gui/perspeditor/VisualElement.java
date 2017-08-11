package com.variamos.gui.perspeditor;

import java.awt.Color;
import java.awt.Font;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.OpersVariable;
import com.variamos.dynsup.model.SyntaxElement;

public class VisualElement implements Comparable<VisualElement> {

	private InstElement instElement;
	private OpersElement opersElement;
	private boolean selected, notAvailable, updated;
	private Color green, red, yellow;
	private String otherParameters = "";

	public VisualElement(InstElement instElement) {
		defineColors();
		this.instElement = instElement;
		this.opersElement = instElement.getTransSupportMetaElement()
				.getTransSemanticConcept();
		updateValues();
		this.updated = false;

	}

	public String getElementId() {
		return instElement.getIdentifier();
	}

	public String getMetaElementId() {
		return instElement.getTransSupportMetaElement().getAutoIdentifier();
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
		boolean newSelected = false, newNotAvailable = false;
		String newOtherParameters = "";
		if (instElement.getInstAttribute("Sel") != null)
			newSelected = instElement.getInstAttribute("Sel").getAsBoolean();
		if (instElement.getInstAttribute("NotAvailable") != null)
			newNotAvailable = instElement.getInstAttribute("NotAvailable")
					.getAsBoolean();
		if (instElement.getInstAttribute("CompExp") != null) {
			newOtherParameters = "{BoolExp:"
					+ instElement.getInstAttribute("CompExp").getAsBoolean()
					+ "}";
		}
		if (instElement.getInstAttribute("SDReqLevel") != null) {
			newOtherParameters = "{Required: "
					+ instElement.getInstAttribute("SDReqLevel").getAsInteger()
					+ " Expected: "
					+ instElement.getInstAttribute("ClaimExpLevel")
							.getAsInteger() + "}";
		}

		List<InstElement> parents = instElement.getTransSupportMetaElement()
				.getTransInstSemanticElement().getParentOpersConcept();
		boolean child = false;
		for (InstElement e : parents)
			if (e.getIdentifier().equals("NmVariable"))
				child = true;

		if (opersElement instanceof OpersVariable || child) {
			String out = " : ";
			if (instElement.getInstAttribute("variableType").getValue()
					.equals("Integer")) {
				out += "int : "
						+ instElement.getInstAttribute("value").getAsInteger()
						+ "";
			} else if (instElement.getInstAttribute("variableType").getValue()
					.equals("Float")) {
				out += "float : "
						+ instElement.getInstAttribute("value").getAsFloat()
						+ "";
			} else if (instElement.getInstAttribute("variableType").getValue()
					.equals("Enumeration")) {
				out += "enum : ";
				Object object = instElement.getInstAttribute("enumType")
						.getValueObject();
				if (object != null) {
					@SuppressWarnings("unchecked")
					Collection<InstAttribute> values = (Collection<InstAttribute>) ((InstElement) object)
							.getInstAttribute(SyntaxElement.VAR_METAENUMVALUE)
							.getValue();
					for (InstAttribute value : values) {
						String[] split = ((String) value.getValue()).split("#");
						String val = null;
						if (instElement.getInstAttribute("value").getValue() instanceof Integer)
							val = ((Integer) instElement.getInstAttribute(
									"value").getValue()).toString();
						else if (instElement.getInstAttribute("value")
								.getValue() instanceof Float)
							val = ((Float) instElement
									.getInstAttribute("value").getValue())
									.toString();
						else
							val = (String) instElement
									.getInstAttribute("value").getValue();
						if (split[0].equals(val))
							out += value + "";
					}
				}
			} else if (instElement.getInstAttribute("variableType").getValue()
					.equals("Boolean")) {
				out += "bool : "
						+ instElement.getInstAttribute("value").getAsBoolean()
						+ "";
			} else if (instElement.getInstAttribute("variableType").getValue()
					.equals("LowLevel variable")) {
				out += "float : "
						+ instElement.getInstAttribute("value").getAsFloat()
						+ "";
			} else
				out += "Other type";
			newOtherParameters = out;

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
		if (showNames && instElement.getInstAttribute("name") != null
				&& !instElement.getInstAttribute("name").getValue().equals(""))
			label = new JLabel((String) instElement.getInstAttribute("name")
					.getValue());
		else
			label = new JLabel(instElement.getIdentifier());
		row.add(label);

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

		return row;
	}

	// FIXME: use an attribute to define attributes visible for dashboard
	public int getCols() {

		List<InstElement> parents = instElement.getTransSupportMetaElement()
				.getTransInstSemanticElement().getParentOpersConcept();
		boolean child = false;
		for (InstElement e : parents)
			if (e.getIdentifier().equals("NmVariable"))
				child = true;
		String opersId = opersElement.getIdentifier();
		if (opersElement != null
				&& ((opersElement.getIdentifier() != null && opersElement
						.getIdentifier().equals("NmVariable")) || child

				// if (opersId.equals("AbstVariable")
				|| opersId != null
						&& (opersId.equals("SoftDependency") || opersId
								.equals("Claim"))))
			return 2;
		if (opersId.equals("Softgoal"))
			return 1;
		if (opersElement instanceof OpersVariable)
			return 2;
		return 3;
	}

	@Override
	public int compareTo(VisualElement arg0) {

		return instElement.getIdentifier().compareTo(arg0.getElementId());
	}

}
