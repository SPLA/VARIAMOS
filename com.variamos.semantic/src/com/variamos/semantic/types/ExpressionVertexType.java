
package com.variamos.semantic.types;

/**
 * An enumeration to represent possible components of a Transformation. Part of
 * PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-14
 */
public enum ExpressionVertexType {
	LEFT, RIGHT, 
	LEFTBOOLEANEXPRESSIONVALUE,RIGHTBOOLEANEXPRESSIONVALUE,
	LEFTNUMERICEXPRESSIONVALUE,RIGHTNUMERICEXPRESSIONVALUE, 
	LEFTSUBEXPRESSION, RIGHTSUBEXPRESSION, LEFTVARIABLEVALUE,RIGHTVARIABLEVALUE
}
