package com.variamos.io.core.importExport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.variamos.io.core.importExport.ExportUtil;



/**
 * A class to create an XLS file with the valid results for the current configuration.
 * Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-03-10
 */
public class ExportConfiguration {
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1719783068334097524L;

	public ExportConfiguration() {

	}

	public void exportConfiguration(Map<String, Map<String, Integer>> elements,
			List<String> names, String file) {
		boolean title = false;
		List<List<String>> results = new ArrayList<List<String>>();
		List<String> titlesIds = new ArrayList<String>();
		for (Map<String, Integer> groupElement : elements.values()) {
			if (!title) {

				for (String element : groupElement.keySet()) {
					titlesIds.add(element);
				}

				title = true;
			}
			List<String> row = new ArrayList<String>();
			for (String element : groupElement.keySet()) {
				row.add(groupElement.get(element) + "");
			}
			results.add(row);
		}
		HSSFWorkbook resultadosLibro = new HSSFWorkbook();
		HSSFSheet hoja = resultadosLibro.createSheet();
		ExportUtil.addInfoToSheet(titlesIds, names, hoja, results, 0);
		ExportUtil.saveXls(resultadosLibro, file);
	}

}
