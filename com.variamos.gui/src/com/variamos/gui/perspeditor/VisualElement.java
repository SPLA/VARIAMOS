package com.variamos.gui.perspeditor;

import java.awt.Color;
import java.awt.Font;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstEnum;
import com.variamos.dynsup.interfaces.IntOpersElement;
import com.variamos.dynsup.model.OpersReasoningConcept;
import com.variamos.dynsup.model.OpersSoftConcept;
import com.variamos.dynsup.model.OpersVariable;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.model.SyntaxEnum;

public class VisualElement implements Comparable<VisualElement> {

	private InstElement instElement;
	private IntOpersElement opersElement;
	private boolean selected, notAvailable, updated;
	private Color green, red, yellow;
	private String otherParameters = "";

	public VisualElement(InstElement instElement) {
		defineColors();
		this.instElement = instElement;
		this.opersElement = ((SyntaxElement) instElement
				.getTransSupportMetaElement()).getTransSemanticConcept();
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
		if (instElement.getInstAttribute("Selected") != null)
			newSelected = instElement.getInstAttribute("Selected")
					.getAsBoolean();
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
		if (opersElement instanceof OpersVariable) {
			if (opersElement instanceof OpersVariable) {
				String out = " : ";
				if (instElement.getInstAttribute("variableType").getValue()
						.equals("Integer")) {
					out += "int : "
							+ instElement.getInstAttribute("value")
									.getAsInteger() + "";
				} else if (instElement.getInstAttribute("variableType")
						.getValue().equals("Enumeration")) {
					out += "enum : ";
					Object object = instElement.getInstAttribute(
							"enumerationType").getValueObject();
					if (object != null) {
						@SuppressWarnings("unchecked")
						Collection<InstAttribute> values = (Collection<InstAttribute>) ((InstAttribute) ((InstEnum) object)
								.getInstAttribute(SyntaxEnum.VAR_METAENUMVALUE))
								.getValue();
						for (InstAttribute value : values) {
							String[] split = ((String) value.getValue())
									.split("-");
							String val = null;
							if (instElement.getInstAttribute("value")
									.getValue() instanceof Integer)
								val = ((Integer) instElement.getInstAttribute(
										"value").getValue()).toString();
							else

								val = (String) instElement.getInstAttribute(
										"value").getValue();
							if (split[0].equals(val))
								out += value + "";
						}
					}
				} else if (instElement.getInstAttribute("variableType")
						.getValue().equals("Boolean")) {
					out += "bool : "
							+ instElement.getInstAttribute("value")
									.getAsBoolean() + "";
				} else
					out += "Other type";
				newOtherParameters = out;
			}
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
		if (showNames && instElement.getInstAttribute("name") != null)
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

	public int getCols() {
		String opersId = opersElement.getIdentifier();
		// FIXME validate considering parents and remove classes
		if (opersId.equals("AbstVariable")
				|| opersId.equals("AbstReasoningElement"))
			return 2;
		if (opersId.equals("AbstSoftElement"))
			return 1;
		if (opersElement instanceof OpersVariable)
			return 2;
		if (opersElement instanceof OpersReasoningConcept)
			return 2;
		if (opersElement instanceof OpersSoftConcept)
			return 1;
		return 3;
	}

	@Override
	public int compareTo(VisualElement arg0) {

		return instElement.getIdentifier().compareTo(arg0.getElementId());
	}

}
