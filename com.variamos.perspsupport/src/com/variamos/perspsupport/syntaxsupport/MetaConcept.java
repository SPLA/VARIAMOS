package com.variamos.perspsupport.syntaxsupport;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.perspsupport.semanticinterface.IntSemanticConcept;

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
	private IntSemanticConcept semanticConcept;
	private MetaConcept parent;

	public MetaConcept() {
		super();
	}

	public MetaConcept(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable,
			IntSemanticConcept semanticConcept,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> attributes, MetaConcept parent) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes, attributes);
		this.semanticConcept = semanticConcept;
		this.parent = parent;
	}

	public MetaConcept(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable,
			IntSemanticConcept semanticConcept,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> attributes) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes, attributes);
		this.semanticConcept = semanticConcept;

	}

	public MetaConcept(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, boolean topConcept, String backgroundColor,
			int borderStroke, boolean resizable,
			IntSemanticConcept semanticConcept) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable);
		this.semanticConcept = semanticConcept;
	}

	/**
	 * Name changed from standard to avoid graph serialization of the object
	 * Emulates the transient attribute modifier
	 * @return
	 */
	public IntSemanticConcept getTransSemanticConcept() {
		return semanticConcept;
	}
	/**
	 * Name changed from standard to avoid graph serialization of the object
	 */
	public void setTransSemanticConcept(IntSemanticConcept semanticConcept) {
		this.semanticConcept = semanticConcept;
	}

	public Set<String> getPropVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticConcept != null)
			modelingAttributesNames.addAll(semanticConcept
					.getPropVisibleAttributes());
		if (parent != null) {
			modelingAttributesNames.addAll(parent.getPropVisibleAttributes());

		}
		modelingAttributesNames.addAll(super.getPropVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPropEditableAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticConcept != null)
			modelingAttributesNames.addAll(semanticConcept
					.getPropEditableAttributes());

		if (parent != null) {
			modelingAttributesNames.addAll(parent.getPropEditableAttributes());
		}
		modelingAttributesNames.addAll(super.getPropEditableAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPanelVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticConcept != null)
			modelingAttributesNames.addAll(semanticConcept
					.getPanelVisibleAttributes());

		if (parent != null) {
			modelingAttributesNames.addAll(parent.getPanelVisibleAttributes());
		}
		modelingAttributesNames.addAll(super.getPanelVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPanelSpacersAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticConcept != null)
			modelingAttributesNames.addAll(semanticConcept
					.getPanelSpacersAttributes());

		if (parent != null) {
			modelingAttributesNames.addAll(parent.getPanelSpacersAttributes());
		}
		modelingAttributesNames.addAll(super.getPanelSpacersAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getModelingAttributesNames() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(super.getModelingAttributesNames());
		if (parent != null) {
			modelingAttributesNames.addAll(parent.getModelingAttributesNames());
		}
		return modelingAttributesNames;
	}

	public Set<String> getAllAttributesNames() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(semanticConcept
				.getSemanticAttributesNames());
		if (parent != null) {
			modelingAttributesNames.addAll(parent.getModelingAttributesNames());
		}
		modelingAttributesNames.addAll(this.getModelingAttributesNames());
		return modelingAttributesNames;
	}

	public AbstractAttribute getModelingAttribute(String name) {
		if (super.getModelingAttribute(name) != null)
			return super.getModelingAttribute(name);
		else {
			if (parent != null && parent.getModelingAttribute(name) != null) {
				return parent.getModelingAttribute(name);
			}
		}
		return null;
	}

	public AbstractAttribute getSemanticAttribute(String name) {
		return semanticConcept.getSemanticAttribute(name);
	}

	public MetaConcept getParent() {
		return parent;
	}
	
	public void setParent(MetaConcept parent) {
		this.parent = parent;
	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		AbstractAttribute out = getSemanticAttribute(attributeName);
		if (out == null)
			return getModelingAttribute(attributeName);
		else
			return out;
	}
}
