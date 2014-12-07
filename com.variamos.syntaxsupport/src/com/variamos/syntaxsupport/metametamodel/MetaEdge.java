package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.variamos.syntaxsupport.semanticinterface.IntDirectRelationType;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticDirectRelation;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class MetaEdge extends MetaElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2665541567934067387L;
	private String identifier;
	private int iniLowCardinality;
	private int iniHighCardinality;
	private int endLowCardinality;
	private int endHighCardinality;
	private String iniDescription;
	private String endDescription;
	private boolean arrowDirection;
	private TypeOfLine typeOfLine;
	private MetaVertex origin;
	private MetaVertex destination;
	private List<IntSemanticDirectRelation> semanticRelations;
	private List<IntDirectRelationType> semanticTypes;
	private SemanticAttribute semanticRelation;
	public static final String VAR_SEMANTICDIRECTRELATIONCLASS = "com.variamos.refas.core.sematicsmetamodel.SemanticDirectRelation",
			VAR_SEMANTICDIRECTRELATION = "semanticRelation";

	public void setSemanticRelation(
			List<IntSemanticDirectRelation> semanticRelations) {
		this.semanticRelations = semanticRelations;
	}

	public MetaEdge() {
		this.iniLowCardinality = 1;
		this.iniHighCardinality = 1;
		this.endLowCardinality = 1;
		this.endHighCardinality = 1;
		this.iniDescription = "";
		this.endDescription = "";
		this.arrowDirection = false;
		this.typeOfLine = TypeOfLine.solid;
		semanticTypes = new ArrayList<IntDirectRelationType>();
		semanticRelation = new SemanticAttribute(VAR_SEMANTICDIRECTRELATION,
				"Class", VAR_SEMANTICDIRECTRELATIONCLASS, null, "");
	}

	public MetaEdge(
			List<IntSemanticDirectRelation> semanticRelations,
			MetaVertex origin, MetaVertex destination) {
		this(origin, destination);
		this.semanticRelations = semanticRelations;
		semanticTypes = new ArrayList<IntDirectRelationType>();
		semanticRelation = new SemanticAttribute(VAR_SEMANTICDIRECTRELATION,
				"Class", VAR_SEMANTICDIRECTRELATIONCLASS, null, "");
	}

	public MetaEdge(MetaVertex origin, MetaVertex destination) {
		this.iniLowCardinality = 1;
		this.iniHighCardinality = 1;
		this.endLowCardinality = 1;
		this.endHighCardinality = 1;
		this.iniDescription = "";
		this.endDescription = "";
		this.arrowDirection = false;
		this.typeOfLine = TypeOfLine.solid;
		this.origin = origin;
		this.destination = destination;
	}

	public MetaEdge(int iniLowCardinality, int iniHighCardinality,
			int endLowCardinality, int endHighCardinality,
			String iniDescription, String endDescription,
			boolean arrowDirection, TypeOfLine typeOfLine, MetaVertex origin,
			MetaVertex destination) {
		this.iniLowCardinality = iniLowCardinality;
		this.iniHighCardinality = iniHighCardinality;
		this.endLowCardinality = endLowCardinality;
		this.endHighCardinality = endHighCardinality;
		this.iniDescription = iniDescription;
		this.endDescription = endDescription;
		this.arrowDirection = arrowDirection;
		this.typeOfLine = typeOfLine;
		this.origin = origin;
		this.destination = destination;
	}

	public void setOrigin(MetaVertex origin) {
		this.origin = origin;
	}

	public void setDestination(MetaVertex destination) {
		this.destination = destination;
	}

	public MetaVertex getOrigin() {
		return origin;
	}

	public MetaVertex getDestination() {
		return destination;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setIniLowCardinality(int iniLowCardinality) {
		this.iniLowCardinality = iniLowCardinality;
	}

	public void setIniHighCardinality(int iniHighCardinality) {
		this.iniHighCardinality = iniHighCardinality;
	}

	public void setEndLowCardinality(int endLowCardinality) {
		this.endLowCardinality = endLowCardinality;
	}

	public void setEndHighCardinality(int endHighCardinality) {
		this.endHighCardinality = endHighCardinality;
	}

	public void setIniDescription(String iniDescription) {
		this.iniDescription = iniDescription;
	}

	public void setEndDescription(String endDescription) {
		this.endDescription = endDescription;
	}

	public void setArrowDirection(boolean arrowDirection) {
		this.arrowDirection = arrowDirection;
	}

	public void setTypeOfLine(TypeOfLine typeOfLine) {
		this.typeOfLine = typeOfLine;
	}

	public int getIniLowCardinality() {
		return iniLowCardinality;
	}

	public int getIniHighCardinality() {
		return iniHighCardinality;
	}

	public int getEndLowCardinality() {
		return endLowCardinality;
	}

	public int getEndHighCardinality() {
		return endHighCardinality;
	}

	public String getIniDescription() {
		return iniDescription;
	}

	public String getEndDescription() {
		return endDescription;
	}

	public boolean isArrowDirection() {
		return arrowDirection;
	}

	public TypeOfLine getTypeOfLine() {
		return typeOfLine;
	}

	public String toString() {
		return "MetaGroupRelation [metaConcept=" + origin + ", dependency="
				+ destination + "]";
	}

	public SemanticAttribute getSemanticRelation() {
		return semanticRelation;
	}

	public List<IntDirectRelationType> getSemanticTypes() {
		return semanticTypes;
	}

	public void setSemanticTypes(List<IntDirectRelationType> semanticTypes) {
		this.semanticTypes = semanticTypes;
	}

	public boolean addSemanticType(IntDirectRelationType semanticType) {
		this.semanticTypes.add(semanticType);
		return true;
	}

	public List<IntSemanticDirectRelation> getSemanticRelations() {
		return semanticRelations;
	}

}
