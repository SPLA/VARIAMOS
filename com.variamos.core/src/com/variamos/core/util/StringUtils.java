package com.variamos.core.util;

import java.util.regex.Pattern;

public class StringUtils {
	public static String formatEnumValue(String enumValue) {
		String patternString = "([_])";
		Pattern p = Pattern.compile(patternString);

		String[] split = p.split(enumValue.toString());
		String out = split[0] + " ";
		for (int j = 1; j < split.length; j++)
			out += split[j].toLowerCase() + " ";
		return out.trim();

	}
}
