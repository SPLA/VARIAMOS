package com.variamos.semantic.semanticsupport;

import java.util.List;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticRelationType;

/**
 * A class to represent types of relations at semantic level. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-01-03
 *  *  
 */

public class SemanticRelationType extends AbstractSemanticElement implements IntSemanticRelationType {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2252623722653740972L;
	private String diplayName;
	private String panelName;
	private boolean relationExclusive;
	private boolean sourceExclusive;
	private boolean targetExclusive;
	private int minSourceCardinality;
	private int maxSourceCardinality;
	private int minTargetCardinality;
	private int maxTargetCardinality;

	public SemanticRelationType() {
		super("");
	}
	
	public SemanticRelationType(String identifier) {
		super(identifier);
	}

	public SemanticRelationType(String identifier, String diplayName,
			String panelName, boolean relationExclusive,
			boolean sourceExclusive, boolean targetExclusive,
			int minSourceCardinality, int maxSourceCardinality,
			int minTargetCardinality, int maxTargetCardinality) {
		super(identifier);
		this.diplayName = diplayName;
		this.panelName = panelName;
		this.relationExclusive = relationExclusive;
		this.sourceExclusive = sourceExclusive;
		this.targetExclusive = targetExclusive;
		this.minSourceCardinality = minSourceCardinality;
		this.maxSourceCardinality = maxSourceCardinality;
		this.minTargetCardinality = minTargetCardinality;
		this.maxTargetCardinality = maxTargetCardinality;
	}

	private void createModelingAttributes()
	{
	/*	putSemanticAttribute(VAR_LOWCARDINALITY,
				new SemanticAttribute(VAR_LOWCARDINALITY, "Integer", false,
						VAR_LOWCARDINALITY, 1, ""));
		putSemanticAttribute(VAR_HIGHCARDINALITY, new SemanticAttribute(
				VAR_HIGHCARDINALITY, "Integer", false, VAR_HIGHCARDINALITY, 1,
				""));

		addPropEditableAttribute("06#" + VAR_CARDINALITYTYPE);
		addPropEditableAttribute("09#" + VAR_LOWCARDINALITY + "#"
				+ VAR_CARDINALITYTYPE + "#==#" + "range");
		addPropEditableAttribute("10#" + VAR_HIGHCARDINALITY + "#"
				+ VAR_CARDINALITYTYPE + "#==#" + "range");

		addPropVisibleAttribute("06#" + VAR_CARDINALITYTYPE);
		addPropVisibleAttribute("09#" + VAR_LOWCARDINALITY + "#"
				+ VAR_CARDINALITYTYPE + "#==#" + "range");
		addPropVisibleAttribute("10#" + VAR_HIGHCARDINALITY + "#"
				+ VAR_CARDINALITYTYPE + "#==#" + "range");

		addPanelVisibleAttribute("06#" + VAR_CARDINALITYTYPE + "#"
				+ VAR_CARDINALITYTYPE + "#!=#" + "range");
		addPanelVisibleAttribute("09#" + VAR_LOWCARDINALITY + "#"
				+ VAR_CARDINALITYTYPE + "#==#" + "range");
		addPanelVisibleAttribute("10#" + VAR_HIGHCARDINALITY + "#"
				+ VAR_CARDINALITYTYPE + "#==#" + "range");

		addPanelSpacersAttribute("#" + VAR_CARDINALITYTYPE + "#");
		addPanelSpacersAttribute("[#" + VAR_LOWCARDINALITY + "#-");
		addPanelSpacersAttribute("#" + VAR_HIGHCARDINALITY + "#]");
		*/
	}
	public String getDiplayName() {
		return diplayName;
	}

	public void setDiplayName(String diplayName) {
		this.diplayName = diplayName;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public boolean isRelationExclusive() {
		return relationExclusive;
	}

	public void setRelationExclusive(boolean relationExclusive) {
		this.relationExclusive = relationExclusive;
	}

	public boolean isSourceExclusive() {
		return sourceExclusive;
	}

	public void setSourceExclusive(boolean sourceExclusive) {
		this.sourceExclusive = sourceExclusive;
	}

	public boolean isTargetExclusive() {
		return targetExclusive;
	}

	public void setTargetExclusive(boolean targetExclusive) {
		this.targetExclusive = targetExclusive;
	}

	public int getMinSourceCardinality() {
		return minSourceCardinality;
	}

	public void setMinSourceCardinality(int minSourceCardinality) {
		this.minSourceCardinality = minSourceCardinality;
	}

	public int getMaxSourceCardinality() {
		return maxSourceCardinality;
	}

	public void setMaxSourceCardinality(int maxSourceCardinality) {
		this.maxSourceCardinality = maxSourceCardinality;
	}

	public int getMinTargetCardinality() {
		return minTargetCardinality;
	}

	public void setMinTargetCardinality(int minTargetCardinality) {
		this.minTargetCardinality = minTargetCardinality;
	}

	public int getMaxTargetCardinality() {
		return maxTargetCardinality;
	}

	public void setMaxTargetCardinality(int maxTargetCardinality) {
		this.maxTargetCardinality = maxTargetCardinality;
	}


}
