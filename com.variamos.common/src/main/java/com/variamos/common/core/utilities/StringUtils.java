package com.variamos.common.core.utilities;

import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * This method searches for a "_" in a String and returns the first part
	 * of the String before "_" with notation UpperCamelCase
	 * @param enumValue String where pattern will be searched
	 * @return  First part of the string before "_" 
	 * in notation UpperCamelCase and without white spaces. 
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
	 * @return String
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
