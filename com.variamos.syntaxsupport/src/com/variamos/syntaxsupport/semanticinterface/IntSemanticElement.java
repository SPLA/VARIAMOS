package com.variamos.syntaxsupport.semanticinterface;

import java.io.Serializable;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;

public interface IntSemanticElement extends Serializable {

	String getIdentifier();

	AbstractAttribute getSemanticAttribute(String attributeName);

}
