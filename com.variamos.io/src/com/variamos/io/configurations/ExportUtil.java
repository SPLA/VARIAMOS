package com.variamos.io.configurations;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Clase utilitaria para exportar resultados en una hoja de excel
 * 
 * @author LuFe
 * 
 */
public class ExportUtil {

	/**
	 * Crea un conjunto de celdas para una fila determinada del modelo
	 * 
	 * @param row
	 * @param count
	 * @param filePath
	 * @param timeCounter
	 * @param results
	 * @param deadFeatureCount
	 * @return
	 */
	public static HSSFRow createRow(HSSFRow row, List<String> filaResultados) {

		int contadorCelda = 0;
		for (String resultadoCelda : filaResultados) {
			HSSFCell celda = row.createCell(contadorCelda);
			celda.setCellValue(resultadoCelda);
			contadorCelda++;
		}

		return row;
	}

	/**
	 * Se almacena información del XLS
	 * 
	 * @param libro
	 * @param rutaResultados
	 */
	public static void guardarXls(HSSFWorkbook libro, String rutaResultados) {
		// Se salva el libro.
		try {
			FileOutputStream elFichero = new FileOutputStream(rutaResultados);
			libro.write(elFichero);
			elFichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea la información que se requiera en la hoja de excel definida como
	 * parámetro de entrada
	 * 
	 * @param encabezadosList
	 * @param filaInicialHoja
	 * @param hoja
	 * @param resultados
	 * @param tiempoAnalisis
	 * @param cantidadModelos
	 */
	public static void adicionarInfoHoja(List<String> encabezados1List,
			List<String> encabezados2List, HSSFSheet hoja,
			List<List<String>> resultados, long tiempoAnalisis) {

		int contadorCelda = 0;
		int filaInicialHoja = 0;
		// Información con los tiempos
		// Fila con las estadísticas
		HSSFRow estadisticasTitle = hoja.createRow(filaInicialHoja);
		HSSFCell celda0 = estadisticasTitle.createCell(0);
		celda0.setCellValue("Statistics");
		filaInicialHoja++;
		// Solo si es mayor de cero se guarda como un resultado
		if (resultados.size() > 0) {
			HSSFRow modelCounter = hoja.createRow(filaInicialHoja);
			HSSFCell modelCounterCell = modelCounter.createCell(0);
			HSSFCell modelCounterValueCell = modelCounter.createCell(1);
			modelCounterCell.setCellValue("Number of solutions");
			modelCounterValueCell.setCellValue(resultados.size());
			filaInicialHoja++;
		}

		if (tiempoAnalisis > 0) {
			HSSFRow tiempoPrueba = hoja.createRow(filaInicialHoja);
			HSSFCell timerCell = tiempoPrueba.createCell(0);
			HSSFCell timerCellValue = tiempoPrueba.createCell(1);
			timerCell.setCellValue("Required time: ");

			int seconds = (int) ((tiempoAnalisis / 1000) % 60);
			int minutes = (int) ((tiempoAnalisis / 1000) / 60);
			timerCellValue.setCellValue("minutes " + minutes + "seg" + seconds
					+ " mils " + " tiempo total (ms): " + tiempoAnalisis);

			filaInicialHoja++;
		}

		HSSFRow titulo = hoja.createRow(filaInicialHoja);
		filaInicialHoja++;
		for (String encabezado : encabezados1List) {
			HSSFCell tituloCell = titulo.createCell(contadorCelda);
			tituloCell.setCellValue(encabezado);
			contadorCelda++;
		}
		contadorCelda = 0;
		HSSFRow names = hoja.createRow(filaInicialHoja);
		filaInicialHoja++;
		for (String encabezado : encabezados2List) {
			HSSFCell nameCell = names.createCell(contadorCelda);
			nameCell.setCellValue(encabezado);
			contadorCelda++;
		}

		for (List<String> filaResultados : resultados) {
			if (filaInicialHoja == 65535)
				break;
			HSSFRow row = hoja.createRow(filaInicialHoja);
			// Adiciona una fila a los resultados
			createRow(row, filaResultados);
			filaInicialHoja++;
		}

	}

}
