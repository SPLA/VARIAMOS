package com.variamos.perspsupport.syntaxsupport;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.perspsupport.instancesupport.InstElement;

/**
 * A class to represented concepts of the meta model. Extends from MetaVertex
 * adding the SemanticConcept and extend relations. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.perspsupport.syntaxsupport.MetaElement
 * @see com.variamos.perspsupport.syntaxsupport.MetaVertex
 */
public class MetaConcept extends MetaVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8416609016211900965L;
	private char type;

	public MetaConcept() {
		super();
	}

	public MetaConcept(char type) {
		super();
		this.type = type;
	}

	public MetaConcept(char type, String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke,
			InstElement instSemanticElement, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> attributes) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable, propVisibleAttributes,
				propEditableAttributes, panelVisibleAttributes,
				panelSparerAttributes, attributes);
		this.type = type;
	}

	public MetaConcept(char type, String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke,
			InstElement instSemanticElement, boolean resizable) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable);
		this.type = type;
	}

	public char getType() {
		return type;
	}

	/**
	 * Name changed from standard to avoid graph serialization of the object
	 * Emulates the transient attribute modifier
	 * 
	 * @return
	 */
	// public IntSemanticConcept getTransSemanticConcept() {
	// return semanticConcept;
	// }
	//
	// /**
	// * Name changed from standard to avoid graph serialization of the object
	// */
	// public void setTransSemanticConcept(IntSemanticConcept semanticConcept) {
	// this.semanticConcept = semanticConcept;
	// }

	@Override
	public Set<String> getPropVisibleAttributesSet(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getTransInstSemanticElement() != null
				&& getTransInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getTransInstSemanticElement()
					.getEditableSemanticElement().getPropVisibleAttributes());
		if (parents != null)
			for (InstElement parent : parents) {
				MetaConcept parentConcept = (MetaConcept) parent
						.getEditableMetaElement();
				if (parentConcept.getTransInstSemanticElement()
						.getEditableSemanticElement() != null)
					modelingAttributesNames.addAll(parentConcept
							.getTransInstSemanticElement()
							.getEditableSemanticElement()
							.getPropVisibleAttributes());
				modelingAttributesNames.addAll(parentConcept
						.getPropVisibleAttributesSet());

			}
		modelingAttributesNames.addAll(super.getPropVisibleAttributesSet());
		return modelingAttributesNames;
	}

	@Override
	public Set<String> getPropEditableAttributesSet(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getTransInstSemanticElement() != null
				&& getTransInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getTransInstSemanticElement()
					.getEditableSemanticElement().getPropEditableAttributes());

		if (parents != null)
			for (InstElement parent : parents) {
				MetaConcept parentConcept = (MetaConcept) parent
						.getEditableMetaElement();
				if (parentConcept.getTransInstSemanticElement()
						.getEditableSemanticElement() != null)
					modelingAttributesNames.addAll(parentConcept
							.getTransInstSemanticElement()
							.getEditableSemanticElement()
							.getPropEditableAttributes());
				modelingAttributesNames.addAll(parentConcept
						.getPropEditableAttributesSet());
			}
		modelingAttributesNames.addAll(super.getPropEditableAttributesSet());
		return modelingAttributesNames;
	}

	@Override
	public Set<String> getPanelVisibleAttributesSet(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getTransInstSemanticElement() != null
				&& getTransInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getTransInstSemanticElement()
					.getEditableSemanticElement().getPanelVisibleAttributes());

		if (parents != null)
			for (InstElement parent : parents) {
				MetaConcept parentConcept = (MetaConcept) parent
						.getEditableMetaElement();
				if (parentConcept.getTransInstSemanticElement()
						.getEditableSemanticElement() != null)
					modelingAttributesNames.addAll(parentConcept
							.getTransInstSemanticElement()
							.getEditableSemanticElement()
							.getPanelVisibleAttributes());
				modelingAttributesNames.addAll(parentConcept
						.getPanelVisibleAttributesSet());

			}
		modelingAttributesNames.addAll(super.getPanelVisibleAttributesSet());
		return modelingAttributesNames;
	}

	@Override
	public Set<String> getPanelSpacersAttributesSet(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getTransInstSemanticElement() != null
				&& getTransInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getTransInstSemanticElement()
					.getEditableSemanticElement().getPanelSpacersAttributes());

		if (parents != null)
			for (InstElement parent : parents) {
				MetaConcept parentConcept = (MetaConcept) parent
						.getEditableMetaElement();
				if (parentConcept.getTransInstSemanticElement()
						.getEditableSemanticElement() != null)
					modelingAttributesNames.addAll(parentConcept
							.getTransInstSemanticElement()
							.getEditableSemanticElement()
							.getPanelSpacersAttributes());

				modelingAttributesNames.addAll(parentConcept
						.getPanelSpacersAttributesSet());

			}
		modelingAttributesNames.addAll(super.getPanelSpacersAttributesSet());
		return modelingAttributesNames;
	}

	@Override
	public Set<String> getModelingAttributesNames(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(super.getModelingAttributesNames());
		if (parents != null)
			for (InstElement parent : parents) {
				MetaConcept parentConcept = (MetaConcept) parent
						.getEditableMetaElement();
				modelingAttributesNames.addAll(parentConcept
						.getModelingAttributesNames());
			}
		return modelingAttributesNames;
	}

	@Override
	public Set<String> getAllAttributesNames(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();
		if (getTransInstSemanticElement() != null)
			modelingAttributesNames.addAll(getTransInstSemanticElement()
					.getEditableSemanticElement().getSemanticAttributesNames());
		if (parents != null)
			for (InstElement parent : parents) {
				MetaConcept parentConcept = (MetaConcept) parent
						.getEditableMetaElement();
				modelingAttributesNames.addAll(parentConcept
						.getTransInstSemanticElement()
						.getEditableSemanticElement()
						.getSemanticAttributesNames());
				modelingAttributesNames.addAll(parentConcept
						.getModelingAttributesNames(parents));
			}
		modelingAttributesNames
				.addAll(this.getModelingAttributesNames(parents));
		return modelingAttributesNames;
	}

	@Override
	public AbstractAttribute getModelingAttribute(String name,
			List<InstElement> parents) {
		if (super.getModelingAttribute(name) != null)
			return super.getModelingAttribute(name);
		else {
			if (parents != null)
				for (InstElement parent : parents) {
					MetaConcept parentConcept = (MetaConcept) parent
							.getEditableMetaElement();

					if (parentConcept.getModelingAttribute(name) != null) {
						return parentConcept.getModelingAttribute(name);
					}
				}
		}
		return null;
	}

	public AbstractAttribute getSemanticAttribute(String name) {
		if (getTransInstSemanticElement() != null)
			return getTransInstSemanticElement().getEditableSemanticElement()
					.getSemanticAttribute(name);
		return null;
	}

	@Override
	public AbstractAttribute getAbstractAttribute(String attributeName,
			List<InstElement> parents) {
		AbstractAttribute out = getSemanticAttribute(attributeName);
		if (out == null)
			return getModelingAttribute(attributeName, parents);
		else
			return out;
	}
}
