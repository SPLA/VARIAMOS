package com.cfm.productline.prologEditors;

import java.util.List;
import java.util.Set;

import com.cfm.hlcl.BooleanOperation;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.IntervalDomain;
import com.cfm.hlcl.NumericExpression;
import com.cfm.hlcl.RangeDomain;
import com.cfm.productline.compiler.solverSymbols.LabelingOrder;
import com.cfm.productline.compiler.solverSymbols.SWIPrologSymbols;
import com.variamos.core.exceptions.TechnicalException;

public class Hlcl2SWIProlog extends Hlcl2Prolog implements SWIPrologSymbols {

	@Override
	protected void writeFooter(StringBuilder out) {

		StringBuilder footerExpression = new StringBuilder();
		StringBuilder insideLabeling = new StringBuilder();
		StringBuilder orderExpression = new StringBuilder();
		int idx = 0;
		if (params.isFdLabeling()) {
			footerExpression.append(LABELING);

			if (params.isFf()) {
				insideLabeling.append(FF);
			}
			if (params.isOrder()) {
				if (params.getLabelingOrder() == null
						|| params.getOrderExpressions() == null
						|| (params.getLabelingOrder().size() != params
								.getOrderExpressions().size())) {
					throw new TechnicalException("order params are missed");
				}

				// Add a comma after the FF instruction.
				if (params.isFf()) {
					insideLabeling.append(COMMA);
				}

				for (LabelingOrder labOrder : params.getLabelingOrder()) {
					if (labOrder.equals(LabelingOrder.MIN)) {
						insideLabeling.append(MIN);

					} else {
						insideLabeling.append(MAX);
					}
					insideLabeling.append(OPEN_PARENTHESIS);
					transformNumericExpression(params.getOrderExpressions()
							.get(idx), orderExpression);
					insideLabeling.append(orderExpression);
					insideLabeling.append(CLOSE_PARENHESIS);
					idx++;

					if (idx < (params.getOrderExpressions().size() - 1)) {
						insideLabeling.append(COMMA);
					}
				}
			}

			footerExpression.append(OPEN_PARENTHESIS);
			if (insideLabeling.length() > 0) {
				footerExpression.append(OPEN_BRACKET);
				footerExpression.append(insideLabeling);
				footerExpression.append(CLOSE_BRACKET);
				footerExpression.append(COMMA);
			}
			footerExpression.append(INVOCATION);
			footerExpression.append(CLOSE_PARENHESIS);
			footerExpression.append(DOT);
			out.append(footerExpression);
		}

	}

	@Override
	protected void transformBooleanOperation(BooleanOperation e,
			StringBuilder out) {
		out.append(OPEN_PARENTHESIS);
		transformBooleanExpression(e.getLeft(), out);

		out.append(SPACE);
		switch (e.getOperator()) {
		case And:
			out.append(AND);
			break;
		case DoubleImplies:
			out.append(EQUIVALENT);
			break;
		case Implies:
			out.append(IMPLIES);
			break;
		case Or:
			out.append(OR);
			break;
		}
		out.append(SPACE);
		transformBooleanExpression(e.getRight(), out);
		out.append(CLOSE_PARENHESIS);
	}

	@Override
	protected void writeHeader(HlclProgram program, StringBuilder out) {
		Set<Identifier> ids = HlclUtil.getUsedIdentifiers(program);
		out.append(HEADER);
		out.append(makeDomainsAndVariables(ids));
	}

	private StringBuilder makeDomainsAndVariables(Set<Identifier> ids) {
		// Se contruye la lista de características y de dominios
		StringBuilder dommainAndVariables = new StringBuilder("L=[");
		StringBuilder variablesList = new StringBuilder();
		StringBuilder domainString = new StringBuilder();
		String id = "";
		for (Identifier identifier : ids) {
			id = identifier.getId();
			variablesList.append(id);
			variablesList.append(COMMA);

			if (identifier.getDomain() instanceof RangeDomain) {
				// Sample WidthResolution in 0..1
				Integer lowerValue = ((RangeDomain) identifier.getDomain())
						.getLowerValue();
				Integer upperValue = ((RangeDomain) identifier.getDomain())
						.getUpperValue();
				domainString.append(id);
				domainString.append(IN);
				domainString.append(lowerValue);
				domainString.append(DOMAIN_INTERVAL);
				domainString.append(upperValue);

			} else if (identifier.getDomain() instanceof IntervalDomain) {

				// Sample WidthResolution in 0 \/ 800 \/ 1024 \/
				// 1366
				List<Integer> domains = ((IntervalDomain) identifier
						.getDomain()).getRangeValues();

				domainString.append(id);
				domainString.append(IN);

				for (int i = 0; i < domains.size(); i++) {
					Integer domainValue = domains.get(i);
					domainString.append(Integer.toString(domainValue));
					if (i < domains.size() - 1) {
						domainString.append(ORDOMAIN);

					}

				}

			}
			domainString.append(COMMA);
		}
		variablesList.append("],");
		domainString.append(LF);
		dommainAndVariables.append(variablesList.toString().replace(",]",
				CLOSE_BRACKET));
		dommainAndVariables.append(LF);
		// add domain string
		dommainAndVariables.append(domainString);

		// TODO implements composed domain transformation

		return dommainAndVariables;
	}

	@Override
	protected void writeHeaderWithDefinedDomains(HlclProgram program,
			List<String> domainList, StringBuilder out) {
		// TODO Auto-generated method stub

	}

}
