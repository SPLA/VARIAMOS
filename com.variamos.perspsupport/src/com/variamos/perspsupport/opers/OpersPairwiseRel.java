package com.variamos.perspsupport.opers;

import java.util.List;

import com.variamos.perspsupport.opersint.IntOpersPairwiseRel;
import com.variamos.perspsupport.opersint.IntOpersRelType;
import com.variamos.perspsupport.syntaxsupport.SemanticAttribute;
import com.variamos.semantic.types.AttributeType;
import com.variamos.semantic.types.LevelType;
import com.variamos.semantic.types.SatisficingType;

/**
 * A class to represent the edges with semantic back object. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 * @see com.cfm.productline.
 */
public class OpersPairwiseRel extends OpersAbstractElement implements
		IntOpersPairwiseRel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7976788205587295216L;
	/**
	 * 
	 */
	private List<IntOpersRelType> semanticRelationTypes;
	// private boolean toSoftSemanticConcept;
	public static final String
	/**
	* 
	*/
	VAR_SOURCE_LEVEL = "sourceLevel",
	/**
			* 
			*/
	VAR_TARGET_LEVEL = "targetLevel",
	/**
			* 
			*/
	VAR_LEVEL = "level",
	/**
	 * 
	 */
	VAR_SOURCE_LEVELNAME = "Source Level",
	/**
					 * 
					 */
	VAR_TARGET_LEVELNAME = "Target Level",
	/**
			 * 
			 */
	VAR_LEVELNAME = "Level",
	/**
	* 
	*/
	VAR_LEVELCLASS = LevelType.class.getCanonicalName(),
	/**
	 * 
	*/
	VAR_SATISFICINGTYPE = "satisficingType",
	/**
	 * 
	*/
	VAR_SATISFICINGTYPENAME = "Satisficing Level",
	/**
	* 
	*/
	VAR_SATISFICINGTYPECLASS = SatisficingType.class.getCanonicalName(),

	VAR_RELATIONTYPE_IDEN = "relationType",
			VAR_RELATIONTYPE_NAME = "Relation Type",
			VAR_RELATIONTYPE_CLASS = OpersRelType.class
					.getCanonicalName();

	public OpersPairwiseRel() {
		super(null);
		defineSemanticAttributes();
	}

	public OpersPairwiseRel(String identifier,
			boolean toSoftSemanticConcept,
			List<IntOpersRelType> semanticRelationTypes) {
		super(identifier);
		this.semanticRelationTypes = semanticRelationTypes;
		// this.toSoftSemanticConcept = toSoftSemanticConcept;
		defineSemanticAttributes();
	}

	public List<IntOpersRelType> getSemanticRelationTypes() {
		return semanticRelationTypes;
	}

	public void setSemanticRelationTypes(
			List<IntOpersRelType> semanticRelationTypes) {
		this.semanticRelationTypes = semanticRelationTypes;
	}

	private void defineSemanticAttributes() {
		putSemanticAttribute(VAR_RELATIONTYPE_IDEN, new SemanticAttribute(
				VAR_RELATIONTYPE_IDEN, "Class", AttributeType.OPERATION, true,
				VAR_RELATIONTYPE_NAME, VAR_RELATIONTYPE_CLASS, null, "", 0, 6,
				"", "", 6, "#-#\n", ""));
		addPropEditableAttribute("06#" + VAR_RELATIONTYPE_IDEN);
		addPropVisibleAttribute("06#" + VAR_RELATIONTYPE_IDEN);
		addPanelVisibleAttribute("06#" + VAR_RELATIONTYPE_IDEN);
		addPanelSpacersAttribute("#" + VAR_RELATIONTYPE_IDEN + "#\n");
	}

}
