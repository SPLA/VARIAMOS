package com.variamos.refas.core.sematicsmetamodel;

import java.io.Serializable;
import java.util.List;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class SemanticService implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = -2288395296282285908L;
private String name;
 private String returnType;
 private List<String> parameters;
 
 public SemanticService(String name, String returnType, List<String> parameters)
 {
	 this.name = name;
	 this.returnType = returnType;
	 this.parameters = parameters;
	 
 }

public String getName() {
	return name;
}

public String getReturnType() {
	return returnType;
}

public List<String> getParameters() {
	return parameters;
}
}