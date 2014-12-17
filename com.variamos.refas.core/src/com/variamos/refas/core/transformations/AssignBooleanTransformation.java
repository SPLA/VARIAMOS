package com.variamos.refas.core.transformations;

import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.cfm.hlcl.NumericIdentifier;
import com.variamos.refas.core.simulationmodel.AbstractBooleanTransformation;
import com.variamos.refas.core.simulationmodel.AbstractTransformation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
/**
 * Class to create the Assign expression. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-14
 */
public class AssignBooleanTransformation extends AbstractBooleanTransformation {
	private static final String TRANSFORMATION = "#>=#";
	
	public AssignBooleanTransformation(InstVertex left, InstVertex right, String leftAttributeName, String rightAttributeName)
	{
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
	}
	
	public AssignBooleanTransformation(InstVertex left, String attributeName,  AbstractTransformation subExpression)
	{
		super(left, attributeName, true, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}
	
	public AssignBooleanTransformation(InstVertex left, String attributeName, BooleanExpression booleanExpression)
	{
		super(left, attributeName, true, booleanExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}
	
	public AssignBooleanTransformation(InstVertex left, String attributeName,  NumericExpression numericExpression)
	{
		super(left, attributeName, true, numericExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}

	public AssignBooleanTransformation(InstVertex toRelation, String string,
			NumericIdentifier number) {
		super(toRelation, string, true, number);
		this.expressionConnectors.add(TRANSFORMATION);
	}

	@Override
	public BooleanExpression transform(HlclFactory f, Map<String, Identifier> idMap) {
		List<Expression> expressionTerms = expressionTerms(f, idMap);
		return f.assign((Identifier) expressionTerms.get(0), (Expression)expressionTerms.get(1));
	}
	
	@Override
	public BooleanExpression transformNegation(HlclFactory f, Map<String, Identifier> idMap,boolean noAssign, boolean valueNegation) {
		List<Expression> expressionTerms = expressionTermsNegation(f, idMap,false,false);
		return f.notEquals((Identifier) expressionTerms.get(0), (Expression)expressionTerms.get(1));
	}

}
