package com.variamos.common.core.utilities;

import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * This method searches for "_" in a Text and changes all "_" occurences
	 * for " " (Whitespaces), finally returns String with no spaces
	 * in start or end, with "_" changed for " " (WhiteSpaces) and
	 * from the first "_" to the end, in lower case
	 * @param enumValue String to convert
	 * @return String formated with "_" changed for " " trimmed, and with lowerCase 
	 * from word next to "_" first ocurrence
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
	 * cuts last " " if possible, if not returns String
	 * @param str
	 * @param lineLenght max length of line 
	 * @return String cutted in lines of lineLength Length 
	 * @author Juan Carlos Munoz
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
