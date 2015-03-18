package com.variamos.io.configurations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * A class to draw the dashboard for initial simultaion. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-03-10
 */
public class ExportConfiguration {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1719783068334097524L;

	public ExportConfiguration() {

	}

	public void exportConfiguration(Map<String, Map<String, Integer>> elements, String file) {
		boolean title = false;
		List<List<String>> results = new ArrayList<List<String>>();
		List<String> titles = new ArrayList<String>();
		for (Map<String, Integer> groupElement : elements.values()) {			
			if (!title) {
				
				for (String element : groupElement.keySet()) {
					titles.add(element);
				}
				title = true;
			}
			List<String> row = new ArrayList<String>();
			for (String element : groupElement.keySet()) {
				row.add(groupElement.get(element)+"");
			}
			results.add(row);
		}
		HSSFWorkbook resultadosLibro = new HSSFWorkbook();
		HSSFSheet hoja = resultadosLibro.createSheet();
		ExportUtil.adicionarInfoHoja(titles, hoja,
				results, 0);
		ExportUtil.guardarXls(resultadosLibro, file);
	}

}
