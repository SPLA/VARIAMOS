package com.variamos.common.core.utilities;

import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * @param enumValue
	 * @return Formated string with the enum Value
	 * @author JuanCMunoz
	 */
	public static String formatEnumValue(String enumValue) {
		String patternString = "([_])";
		Pattern p = Pattern.compile(patternString);

		String[] split = p.split(enumValue.toString());
		String out = split[0] + " ";
		for (int j = 1; j < split.length; j++)
			out += split[j].toLowerCase() + " ";
		return out.trim();

	}
	
	/**
	 * This methods splits text into different lines to easy visualization
	 * @param str
	 * @param lineLenght
	 * @return formatted string with text separated into multiple lines
	 * @author JuanCMunoz
	 */
	public static String multiLine(String str, int lineLenght)
	{
		String ini = str.replace("\n", "");
		String out = "";
		while (ini.length()>lineLenght)
		{
			String subIdeal = ini.substring(0, lineLenght);
			int cutLow = subIdeal.lastIndexOf(" ");
			int cutHigh = ini.indexOf(" ", lineLenght-1);
			int cut;
			if (cutLow<cutHigh && cutLow !=-1)
			{
				out = out+ini.substring(0,cutLow)+"\n";
				cut = cutLow;
			}
			else
				if (cutHigh !=-1)
				{
				out = out+ini.substring(0,cutHigh)+"\n";
				cut=cutHigh;
				}
			
				else
				{
					out = out+ini;
					ini = "";
					return out;
				}
			ini=ini.substring(cut+1);
		}
		
		
		return out+ini;
	}
}
