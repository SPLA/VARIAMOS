package com.variamos.refas.core.transformations;

import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericIdentifier;
import com.variamos.refas.core.simulationmodel.AbstractBooleanTransformation;
import com.variamos.refas.core.simulationmodel.AbstractTransformation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
/**
 * Class to create the Less expression. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public class LessBooleanTransformation extends AbstractBooleanTransformation {
	private static final String TRANSFORMATION = "#<";
	
	public LessBooleanTransformation(InstVertex left, InstVertex right, String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
	}
	public LessBooleanTransformation(InstVertex vertex,
			String attributeName, boolean replaceRight,
			AbstractTransformation subExpression) {
		super(vertex, attributeName, replaceRight, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}

	public LessBooleanTransformation(InstVertex vertex,
			String attributeName, boolean replaceRight,
			BooleanExpression simpleExpression) {
		super(vertex, attributeName, replaceRight, simpleExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}
	
	public LessBooleanTransformation(InstVertex vertex,
			String attributeName, boolean replaceRight,
			NumericIdentifier numericIdentifier) {
		super(vertex, attributeName, replaceRight, numericIdentifier);
		this.expressionConnectors.add(TRANSFORMATION);
	}

	public LessBooleanTransformation(
			AbstractTransformation leftSubExpression,
			AbstractTransformation rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}

	@Override
	public BooleanExpression transform(HlclFactory f, Map<String, Identifier> idMap) {
		List<Expression> expressionTerms = expressionTerms(f, idMap);
		
		return f.lessThan( (Identifier)expressionTerms.get(0), (Identifier)expressionTerms.get(1));
	}

}
