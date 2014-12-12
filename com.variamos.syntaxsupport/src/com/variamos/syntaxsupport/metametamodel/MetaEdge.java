package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.semanticinterface.IntDirectEdgeType;
import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticEdge;

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
	private MetaVertex origin;
	private MetaVertex destination;

	private int iniLowCardinality;
	private int iniHighCardinality;
	private int endLowCardinality;
	private int endHighCardinality;
	private String iniDescription;
	private String endDescription;
	private boolean arrowDirection;
	private TypeOfLine typeOfLine;



	public MetaEdge() {


	}

	public MetaEdge(String identifier, boolean visible, String name, String style, String description, int width,
			int height, String image, int borderStroke,
			MetaVertex origin, MetaVertex destination) {
		this(identifier, visible, name, style, description, width,
				height, image, borderStroke,
				origin, destination, 1, 1, 1, 1, "", "", false,
				TypeOfLine.solid);
	}

	public MetaEdge(String identifier, boolean visible, String name, String style, String description, int width,
			int height, String image, int borderStroke,
			MetaVertex origin,
			MetaVertex destination,
			List<IntDirectSemanticEdge> directSemanticEdges) {
		this(identifier, visible, name, style, description, width,
				height, image, borderStroke,
				 origin, destination, 1, 1, 1, 1, "", "", false,
				TypeOfLine.solid);
	}

	public MetaEdge(String identifier, boolean visible, String name, String style, String description, int width,
			int height, String image, int borderStroke,			
			MetaVertex origin,
			MetaVertex destination,int iniLowCardinality,
			int iniHighCardinality, int endLowCardinality,
			int endHighCardinality, String iniDescription,
			String endDescription, boolean arrowDirection, TypeOfLine typeOfLine) {
		super( identifier, visible, name, style, description, width,
				height, image, borderStroke);
		this.origin = origin;
		this.destination = destination;

		this.iniLowCardinality = iniLowCardinality;
		this.iniHighCardinality = iniHighCardinality;
		this.endLowCardinality = endLowCardinality;
		this.endHighCardinality = endHighCardinality;
		this.iniDescription = iniDescription;
		this.endDescription = endDescription;
		this.arrowDirection = arrowDirection;
		this.typeOfLine = typeOfLine;
	}
	
	public MetaEdge(String identifier, boolean visible, String name, String style, String description, int width,
			int height, String image, int borderStroke,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes,
			MetaVertex origin,
			MetaVertex destination, int iniLowCardinality,
			int iniHighCardinality, int endLowCardinality,
			int endHighCardinality, String iniDescription,
			String endDescription, boolean arrowDirection, TypeOfLine typeOfLine) {
		super( identifier, visible, name, style, description, width,
				height, image, borderStroke,
				disPropVisibleAttributes,
				 disPropEditableAttributes,
			disPanelVisibleAttributes,
				 disPanelSpacersAttributes,
				  modelingAttributes);
		this.origin = origin;
		this.destination = destination;
		this.iniLowCardinality = iniLowCardinality;
		this.iniHighCardinality = iniHighCardinality;
		this.endLowCardinality = endLowCardinality;
		this.endHighCardinality = endHighCardinality;
		this.iniDescription = iniDescription;
		this.endDescription = endDescription;
		this.arrowDirection = arrowDirection;
		this.typeOfLine = typeOfLine;

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

	public static String getClassId() {
		return "E";
	}

}
