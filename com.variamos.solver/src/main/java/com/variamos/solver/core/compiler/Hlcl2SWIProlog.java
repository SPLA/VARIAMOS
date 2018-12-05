package com.variamos.solver.core.compiler;

import java.util.List;
import java.util.Set;

import com.variamos.common.core.exceptions.TechnicalException;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.core.HlclUtil;
import com.variamos.hlcl.model.LabelingOrderEnum;
import com.variamos.hlcl.model.domains.ComposedDomain;
import com.variamos.hlcl.model.domains.IntDomain;
import com.variamos.hlcl.model.domains.IntervalDomain;
import com.variamos.hlcl.model.domains.RangeDomain;
import com.variamos.hlcl.model.domains.StringDomain;
import com.variamos.hlcl.model.expressions.BooleanOperation;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.solver.model.compiler.SWIPrologSymbolsConstant;

/**
 * @author Luisa Rincon Modified by jcmunoz to support composed domains
 *
 */
public class Hlcl2SWIProlog extends Hlcl2Prolog implements SWIPrologSymbolsConstant {

	@Override
	protected void writeFooter(StringBuilder out) {

		StringBuilder footerExpression = new StringBuilder();
		if (paramList == null) {
			StringBuilder insideLabeling = new StringBuilder();
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

					int idx = 0;
					if (params.isOrder())
						for (LabelingOrderEnum labOrder : params.getLabelingOrder()) {
							if (labOrder.equals(LabelingOrderEnum.MIN)) {
								insideLabeling.append(MIN);

							} else {
								insideLabeling.append(MAX);
							}
							insideLabeling.append(OPEN_PARENTHESIS);

							StringBuilder orderExpression = new StringBuilder();
							transformNumericExpression(params
									.getOrderExpressions().get(idx),
									orderExpression);
							insideLabeling.append(orderExpression);
							insideLabeling.append(CLOSE_PARENHESIS);
							idx++;

							if (idx <= (params.getOrderExpressions().size() - 1)) {
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
				// avillota included these lines
				else{ //In the case that there is no a labeling strategy 
					footerExpression.append(OPEN_BRACKET);
					footerExpression.append(CLOSE_BRACKET);
					footerExpression.append(COMMA);
				}
				footerExpression.append(INVOCATION);
				footerExpression.append(CLOSE_PARENHESIS);
				footerExpression.append(DOT);
				out.append(footerExpression);
			}
		} else {
			for (PrologTransformParameters ptp : paramList) {

				StringBuilder insideLabeling = new StringBuilder();
				// jcmunoz validation to exclude labeling without label
				if (!ptp.isIncludeLabel())
					continue;
				if (ptp.isOnceLabeling()) {
					footerExpression.append(ONCE);
					footerExpression.append(OPEN_PARENTHESIS);

				}
				if (ptp.isFdLabeling()) {
					footerExpression.append(LABELING);

					if (ptp.isFf()) {
						insideLabeling.append(FF);
					}
					if (ptp.isOrder()) {
						if (ptp.getLabelingOrder() == null
								|| ptp.getOrderExpressions() == null
								|| (ptp.getLabelingOrder().size() != ptp
										.getOrderExpressions().size())) {
							throw new TechnicalException(
									"order params are missed");
						}

						// Add a comma after the FF instruction.
						if (ptp.isFf()) {
							insideLabeling.append(COMMA);
						}

						int idx = 0;
						for (LabelingOrderEnum labOrder : ptp.getLabelingOrder()) {
							if (labOrder.equals(LabelingOrderEnum.MIN)) {
								insideLabeling.append(MIN);

							} else {
								insideLabeling.append(MAX);
							}
							insideLabeling.append(OPEN_PARENTHESIS);

							StringBuilder orderExpression = new StringBuilder();
							transformNumericExpression(ptp
									.getOrderExpressions().get(idx),
									orderExpression);
							insideLabeling.append(orderExpression);
							insideLabeling.append(CLOSE_PARENHESIS);
							idx++;

							if (idx <= (ptp.getOrderExpressions().size() - 1)) {
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
					footerExpression.append(ptp.getLabelId());
					footerExpression.append(CLOSE_PARENHESIS);
					if (ptp.isOnceLabeling()) {
						footerExpression.append(CLOSE_PARENHESIS);

					}
					footerExpression.append(COMMA);
					footerExpression.append(LF);
				}

			}
			out.append(footerExpression);
			// System.out.println(out);
			out.deleteCharAt(out.length() - 2);
			// System.out.println(out);

			out.append(DOT);
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
		if (paramList == null) {
			out.append(HEADER);
			out.append(makeDomainsAndVariables(ids));
		} else {
			StringBuilder labids = new StringBuilder();
			StringBuilder variables = new StringBuilder();
			StringBuilder domains = new StringBuilder();

			for (PrologTransformParameters ptp : paramList) {
				if (ptp.isOutputSet()) {
					labids.append(ptp.getLabelId() + ",");
				}
				variables.append(makeVariables(ptp.getLabelId(),
						ptp.getIdentifiers()));
				if (ptp.isIncludeLabel())
					domains.append(makeDomains(ptp.getIdentifiers()));
			}
			// System.out.println(labids);
			labids.deleteCharAt(labids.length() - 1);
			// System.out.println(labids);
			out.append(HEADER_INI);
			out.append(labids);
			out.append(HEADER_END);
			out.append(variables);
			out.append(domains);
		}

	}

	private StringBuilder makeDomainsAndVariables(Set<Identifier> ids) {
		// Se contruye la lista de caracterï¿½sticas y de dominios
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
				// jcmunoz: new method for range domains
				domainString.append(getRangeDomain(identifier.getDomain(), id));
			} else if (identifier.getDomain() instanceof IntervalDomain) {

				// Sample WidthResolution in 0 \/ 800 \/ 1024 \/
				// 1366
				// jcmunoz: new method for interval domains
				domainString.append(getIntervalDomain(identifier.getDomain(),
						id));

				// jcmunoz: new condition for composed domains
			} else if (identifier.getDomain() instanceof ComposedDomain) {
				for (IntDomain domain : ((ComposedDomain) identifier.getDomain())
						.getDomains()) {
					if (domain instanceof RangeDomain) {
						// Sample WidthResolution in 0..1
						domainString.append(getRangeDomain(domain, id));
					} else if (domain instanceof IntervalDomain) {

						// Sample WidthResolution in 0 \/ 800 \/ 1024 \/
						// 1366
						domainString.append(getIntervalDomain(domain, id));
					}
					// set the id to null to define the variable only one time.
					id = null;
				}
				// jcmunoz: new condition for String domains using hashcodes
			} else if (identifier.getDomain() instanceof StringDomain) {
				StringBuffer str = getStringDomain(identifier.getDomain(), id);
				if (str == null)
					continue;
				domainString.append(str);
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

	/**
	 * New method to support Range individual or composed domains
	 * 
	 * @param domain
	 * @param id
	 *            : is null for second and additional domain ranges
	 * @return
	 */
	private StringBuffer getRangeDomain(IntDomain domain, String id) {
		StringBuffer domainString = new StringBuffer();
		Integer lowerValue = (int) ((RangeDomain) domain).getLowerValue();
		Integer upperValue = (int) ((RangeDomain) domain).getUpperValue();
		if (id != null) {
			domainString.append(id);
			domainString.append(IN);
		} else
			domainString.append(ORDOMAIN);
		domainString.append(lowerValue);
		domainString.append(DOMAIN_INTERVAL);
		domainString.append(upperValue);
		return domainString;
	}

	/**
	 * @author jcmunoz {jcmunoz@gmail.com} New method to obtain the variables
	 *         supporting different identifiers for each labeling
	 * @param ids
	 *            :
	 * @return
	 */
	private StringBuilder makeVariables(String name, List<Identifier> ids) {
		StringBuilder variables = new StringBuilder(name + "=[");
		StringBuilder variablesList = new StringBuilder();
		String id = "";
		for (Identifier identifier : ids) {
			id = identifier.getId();
			variablesList.append(id);
			variablesList.append(COMMA);
		}
		variablesList.append("],");
		variables.append(variablesList.toString().replace(",]", CLOSE_BRACKET));
		variables.append(LF);
		return variables;
	}

	/**
	 * @author jcmunoz {jcmunoz@gmail.com} New method to obtain the domains
	 *         supporting different identifiers for each labeling
	 * @param ids
	 *            :
	 * @return
	 */
	private StringBuilder makeDomains(List<Identifier> ids) {
		// identify
		// variable lists
		StringBuilder domainOut = new StringBuilder();
		StringBuilder domainString = new StringBuilder();
		String id = "";
		for (Identifier identifier : ids) {
			id = identifier.getId();
			if (identifier.getDomain() instanceof RangeDomain) {
				// Sample WidthResolution in 0..1
				// jcmunoz: new method for range domains
				domainString.append(getRangeDomain(identifier.getDomain(), id));
			} else if (identifier.getDomain() instanceof IntervalDomain) {

				// Sample WidthResolution in 0 \/ 800 \/ 1024 \/
				// 1366
				// jcmunoz: new method for interval domains
				domainString.append(getIntervalDomain(identifier.getDomain(),
						id));

				// jcmunoz: new condition for composed domains
			} else if (identifier.getDomain() instanceof ComposedDomain) {
				for (IntDomain domain : ((ComposedDomain) identifier.getDomain())
						.getDomains()) {
					if (domain instanceof RangeDomain) {
						// Sample WidthResolution in 0..1
						domainString.append(getRangeDomain(domain, id));
					} else if (domain instanceof IntervalDomain) {

						// Sample WidthResolution in 0 \/ 800 \/ 1024 \/
						// 1366
						domainString.append(getIntervalDomain(domain, id));
					}
					// set the id to null to define the variable only one time.
					id = null;
				}
				// jcmunoz: new condition for String domains using hashcodes
			} else if (identifier.getDomain() instanceof StringDomain) {
				StringBuffer str = getStringDomain(identifier.getDomain(), id);
				if (str == null)
					continue;
				domainString.append(str);
			}

			domainString.append(COMMA);
		}
		domainString.append(LF);
		domainOut.append(LF);
		// add domain string
		domainOut.append(domainString);

		// TODO implements composed domain transformation

		return domainOut;
	}

	/**
	 * @author jcmunoz {jcmunoz@gmail.com} New method to support Internal
	 *         individual or composed domains
	 * @param domain
	 * @param id
	 *            : is null for second and additional domain intervals
	 * @return
	 */
	private StringBuffer getIntervalDomain(IntDomain domain, String id) {
		StringBuffer domainString = new StringBuffer();
		List<Integer> domains = ((IntervalDomain) domain).getRangeValues();
		if (id != null) {
			domainString.append(id);
			domainString.append(IN);
		} else
			domainString.append(ORDOMAIN);
		for (int i = 0; i < domains.size(); i++) {
			Integer domainValue = domains.get(i);
			domainString.append(Integer.toString(domainValue));
			if (i < domains.size() - 1) {
				domainString.append(ORDOMAIN);
			}
		}
		return domainString;
	}

	/**
	 * @author jcmunoz {jcmunoz@gmail.com} New method to support String domains
	 *         using hashcodes
	 * @param domain
	 * @param id
	 * @return
	 */
	private StringBuffer getStringDomain(IntDomain domain, String id) {
		StringBuffer domainString = null;
		List<String> values = ((StringDomain) domain).getStringValues();
		if (values.size() > 0) {
			domainString = new StringBuffer();
			domainString.append(id);
			domainString.append(IN);
		}
		for (int i = 0; i < values.size(); i++) {
			String domainValue = values.get(i);
			domainString.append(Integer.toString(domainValue.hashCode()));
			if (i < values.size() - 1) {
				domainString.append(ORDOMAIN);
			}
		}
		return domainString;
	}

	@Override
	protected void writeHeaderWithDefinedDomains(HlclProgram program,
			List<String> domainList, StringBuilder out) {
		// TODO Auto-generated method stub

	}

}
