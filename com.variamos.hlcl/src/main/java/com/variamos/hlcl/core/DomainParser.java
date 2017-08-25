package com.variamos.hlcl.core;

import com.variamos.hlcl.model.domains.BinaryDomain;
import com.variamos.hlcl.model.domains.ComposedDomain;
import com.variamos.hlcl.model.domains.IntDomain;
import com.variamos.hlcl.model.domains.IntervalDomain;
import com.variamos.hlcl.model.domains.RangeDomain;

public class DomainParser {

	public static IntDomain parseDomain(String str, int precision) {

		String[] parts = str.split(",", -1);

		String digit = "\\d+";
		String floatD = "[0-9]*\\.?[0-9]*";
		String range = "\\-?\\d+\\s*..\\s*\\-?\\d+";
		// "\\d\\s*..\\s*\\d";

		ComposedDomain domain = new ComposedDomain();

		IntervalDomain intDom = new IntervalDomain();
		RangeDomain rd = null;
		for (String part : parts) {
			part = part.trim();
			if (part.matches(digit)) {
				int v = Integer.parseInt(part);

				if (!intDom.getDomainValues().contains(v))
					intDom.add(v);

				continue;
			}
			if (part.matches(floatD)) {
				float v = Float.parseFloat(part);

				if (!intDom.getDomainValues().contains(v))
					;
				// intDom.add(v);

				continue;
			}

			if (part.matches(range)) {
				int sym = part.indexOf("..");
				int lowerValue = Integer
						.parseInt(part.substring(0, sym).trim());
				int upperValue = Integer.parseInt(part.substring(sym + 2)
						.trim());

				rd = new RangeDomain(lowerValue, upperValue, precision);
				domain.getDomains().add(rd);
			}
		}

		if (intDom.size() == 0 && domain.getDomains().size() == 1) {
			// Check if it's binary
			if (rd.getLowerValue() == 0 && rd.getUpperValue() == 1)
				return BinaryDomain.INSTANCE;

			return rd;
		}

		if (rd == null) {
			if (intDom.size() == 2) {
				// Check if binary
				if (intDom.getDomainValues().contains(0)
						&& intDom.getDomainValues().contains(1))
					return BinaryDomain.INSTANCE;
			}
			return intDom;
		}

		domain.getDomains().add(intDom);

		return domain;
	}
}
