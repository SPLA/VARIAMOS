package com.cfm.productline.prologEditors;

import java.util.List;
import java.util.Set;

import com.cfm.hlcl.AssignExpression;
import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.BooleanNegation;
import com.cfm.hlcl.BooleanOperation;
import com.cfm.hlcl.ComparisonExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.FunctionDeclarationExpression;
import com.cfm.hlcl.HlclFunction;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.ListDefinitionExpression;
import com.cfm.hlcl.LiteralBooleanExpression;
import com.cfm.hlcl.NumericExpression;
import com.cfm.hlcl.NumericIdentifier;
import com.cfm.hlcl.NumericOperation;
import com.cfm.hlcl.SymbolicExpression;
import com.cfm.productline.compiler.solverSymbols.ConstraintSymbols;

public abstract class Hlcl2Prolog implements ConstraintSymbols {
	
	protected PrologTransformParameters params;
	/**
	 * Main method
	 * 
	 * @param program
	 * @return
	 */
	public String transform(HlclProgram program) {
		return transform(program, new PrologTransformParameters());
	}
	
	/**
	 * Used when a prolog program must be directly analyzed 
	 * @param program
	 * @param domains
	 * @return
	 */
	public String transform(HlclProgram program, List<String> domains) {

		StringBuilder out = new StringBuilder();
		writeHeaderWithDefinedDomains(program, domains, out);
		transformProgram(program, out);
		writeFooter(out);
		return out.toString();
	}
	
	public String transform(HlclProgram program, PrologTransformParameters params){
		this.params = params;
		
		StringBuilder out = new StringBuilder();
		writeHeader(program, out);
		transformProgram(program, out);
		out.append(COMMA).append(LF);
		writeFooter(out);
		//System.out.println("SOLUTION: \n"+ out.toString() + "\n\n");
		return out.toString();
	}
	
	/**
	 * @param e
	 *            expression to transform
	 * @return prolog instruction that represents input expression
	 */
	public StringBuilder transformExpressionToProlog(Expression e) {

		StringBuilder out = new StringBuilder();
		if (e instanceof HlclProgram) {
			transformProgram((HlclProgram) e, out);
			return out;
		}
		if (e instanceof NumericExpression) {
			throw new RuntimeException(
					"Numeric Expression is not supported to transform");
		}
		if (e instanceof BooleanExpression) {
			transformBooleanExpression((BooleanExpression) e, out);
		}

		out.append(COMMA).append(LF);
		return out;
	}

	private void transformListExpression(ListDefinitionExpression e,
			StringBuilder out) {
		out.append("[");
		Set<Identifier> ids = HlclUtil.getUsedIdentifiers(e);
		writeIdentifiersList(ids, out);
		out.append("]");
	}

	protected void transformLine(Expression e, StringBuilder out) {
		if (e instanceof HlclProgram) {
			transformProgram((HlclProgram) e, out);
			return;
		}
		if (e instanceof NumericExpression) {
			throw new RuntimeException(
					"Numeric Expression is not supported to transform");
		}
		if (e instanceof BooleanExpression) {
			transformBooleanExpression((BooleanExpression) e, out);
		}
	}

	// Abstract methods change in each prologEditor
	protected abstract void writeFooter(StringBuilder out);

	protected abstract void writeHeader(HlclProgram program, StringBuilder out);
	
	/**
	 * This method uses predefined domains when we analyze directly constraint programs expressed in GNU Prolog o SWI Prolog
	 * @param program
	 * @param domainList
	 * @param out
	 */
	protected abstract void writeHeaderWithDefinedDomains(HlclProgram program,
			List<String> domainList, StringBuilder out); 

	protected abstract void transformBooleanOperation(BooleanOperation e,
			StringBuilder out);


	protected void transformProgram(HlclProgram program, StringBuilder out) {
		
		if( program instanceof HlclFunction )
			transformFunctionDeclaration(((HlclFunction) program).getDecl(), out);
		
		int counter = 0;
		for (BooleanExpression e : program){
			transformLine(e, out);
			counter++;
			if( counter < program.size() )
				out.append(COMMA).append(LF);
		}
		//out.append(DOT).append(LF);
	}

	protected void transformBooleanExpression(BooleanExpression e,
			StringBuilder out) {
		if (e instanceof BooleanNegation) {
			transformNot((BooleanNegation) e, out);
		}
		if (e instanceof BooleanOperation) {
			transformBooleanOperation((BooleanOperation) e, out);
		}

		if (e instanceof ComparisonExpression) {
			transformComparison((ComparisonExpression) e, out);
		}

		if (e instanceof SymbolicExpression) {
			transformSymbolic((SymbolicExpression) e, out);
		}
		if (e instanceof HlclProgram) {
			transformProgram((HlclProgram) e, out);
		}
		if (e instanceof Identifier)
			transformIdentifier((Identifier) e, out);
		
		if( e instanceof AssignExpression )
			transformAssign( (AssignExpression) e, out );
		
		if( e instanceof FunctionDeclarationExpression )
			transformFunctionDeclaration( (FunctionDeclarationExpression)e, out);
		
		if(e instanceof LiteralBooleanExpression)
			out.append((((LiteralBooleanExpression) e).getPrologConstraint()));
	}

	private void transformFunctionDeclaration(FunctionDeclarationExpression e,
			StringBuilder out) {
		
		transformSymbolic(e.getHeader(), out);
		out.append(SPACE).append(FUNCTION_DECLARATION).append(LF);
		
	}

	private void transformAssign(AssignExpression e, StringBuilder out) {
		transformIdentifier(e.getIdentifier(), out);
		out.append(SPACE);
		out.append(ASSIGN_VARIABLE);
		out.append(SPACE);
		//transformListExpression( (ListDefinitionExpression)e.getRightExpression(), out );
		out.append(transformExpressionToProlog(e.getRightExpression()));
		
	}

	protected void transformNumericOperation(NumericOperation e,
			StringBuilder out) {
		out.append(OPEN_PARENTHESIS);
		transformNumericExpression(e.getLeft(), out);

		out.append(SPACE);
		switch (e.getOperator()) {
		case Diff:
			out.append(SUBSTRACTION);
			break;
		case Prod:
			out.append(MULTIPLY);
			break;
		case Sum:
			out.append(PLUS);
			break;
		}
		out.append(SPACE);
		transformNumericExpression(e.getRight(), out);
		out.append(CLOSE_PARENHESIS);
	}

	protected void transformIdentifier(Identifier e, StringBuilder out) {
		out.append(e.getId());
	}

	protected void transformNumericIdentifier(NumericIdentifier e,
			StringBuilder out) {
		out.append(e.getValue());
	}

	protected void transformSymbolic(SymbolicExpression e, StringBuilder out) {
		out.append(e.getName()).append(OPEN_PARENTHESIS);
		Set<Identifier> ids = HlclUtil.getUsedIdentifiers(e);
		writeIdentifiersList(ids, out);
		out.append(CLOSE_PARENHESIS);
	}

	protected void transformNumericExpression(NumericExpression e,
			StringBuilder out) {

		if (e instanceof Identifier)
			transformIdentifier((Identifier) e, out);

		if (e instanceof NumericIdentifier)
			transformNumericIdentifier((NumericIdentifier) e, out);

		if (e instanceof NumericOperation) {
			transformNumericOperation((NumericOperation) e, out);
		}
	}

	protected void transformComparison(ComparisonExpression e, StringBuilder out) {
		if (e.getLeft() instanceof NumericExpression)
			transformNumericExpression((NumericExpression)e.getLeft(), out);
		else
			transformBooleanExpression((BooleanExpression)e.getLeft(), out);
		out.append(SPACE);
		switch (e.getType()) {
		case Equals:
			out.append(EQUALS);
			break;
		case GreaterOrEqualsThan:
			out.append(MORE_OR_EQUALS);
			break;
		case GreaterThan:
			out.append(MORE);
			break;
		case LessOrEqualsThan:
			out.append(LESS_OR_EQUALS);
			break;
		case LessThan:
			out.append(LESS);
			break;
		case NotEquals:
			out.append(NOT_EQUALS);
			break;
		}
		out.append(SPACE);
		if (e.getLeft() instanceof NumericExpression)
			transformNumericExpression((NumericExpression)e.getRight(), out);
		else
			transformBooleanExpression((BooleanExpression)e.getRight(), out);
	}

	protected void transformNot(BooleanNegation e, StringBuilder out) {
		out.append(ONE).append(SPACE).append(SUBSTRACTION).append(SPACE)
				.append(OPEN_PARENTHESIS);
		transformBooleanExpression(e.getExpression(), out);
		out.append(CLOSE_PARENHESIS).append(SPACE).append(MORE).append(SPACE)
				.append(ZERO);
	}

	protected void writeIdentifiersList(Set<Identifier> ids, StringBuilder out) {
		int i = 0;
		for (Identifier id : ids) {
			out.append(id.getId());
			i++;
			if (i < ids.size())
				out.append(COMMA + " ");
		}
	}
	public void writeList(List<String> ids, StringBuilder out) {
		int i = 0;
		for (String id : ids) {
			out.append(id);
			i++;
			if (i < ids.size())
				out.append(COMMA + " ");
		}
	}
	public void writeIdentifiersList(List<Identifier> ids, StringBuilder out) {
		int i = 0;
		for (Identifier id : ids) {
			out.append(id.getId());
			i++;
			if (i < ids.size())
				out.append(COMMA + " ");
		}
	}
}
