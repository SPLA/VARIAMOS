package com.variamos.gui.util;

import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.mxgraph.util.mxResources;
import com.variamos.gui.core.mxgraph.editor.DefaultFileFilter;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;

public class FileUtils {

	/**
	 * Open a file chooser with predefined extensions to save models as images or dynamic models according to the default extension
	 * defined inside the perspectiveEditor object.
	 * @param variamosEditor
	 * @param defaultExtension
	 * @return name of the file to save
	 */
	public static String getFileNameByFileChooserToSaveFiles(VariamosGraphEditor variamosEditor, String defaultExtension) {

		String path;
		FileFilter selectedFilter = null;

		final String fileExtensionName = variamosEditor.getExtensionName();
		String fileName = null;

		if (variamosEditor.getLastDir() != null) {
			path = variamosEditor.getLastDir();
		} else if (variamosEditor.getCurrentFile() != null) {
			path = variamosEditor.getCurrentFile().getParent();
		} else {
			path = System.getProperty("user.dir");
		}

		JFileChooser fileChooser = new JFileChooser(path);

		// Adds the default file format
		FileFilter defaultFilter = new DefaultFileFilter("." + defaultExtension,
				fileExtensionName + " (." + defaultExtension + ")");
		fileChooser.addChoosableFileFilter(defaultFilter);

		// Adds special vector graphics formats and HTML
		fileChooser.addChoosableFileFilter(new DefaultFileFilter(".svg", "SVG " + mxResources.get("file") + " (.svg)"));

		// Adds a filter for each supported image format
		Object[] imageFormats = ImageIO.getReaderFormatNames();

		// Finds all distinct extensions
		HashSet<String> formats = new HashSet<String>();

		for (int i = 0; i < imageFormats.length; i++) {
			String ext = imageFormats[i].toString().toLowerCase();
			formats.add(ext);
		}

		imageFormats = formats.toArray();

		for (int i = 0; i < imageFormats.length; i++) {
			String ext = imageFormats[i].toString();
			fileChooser.addChoosableFileFilter(new DefaultFileFilter("." + ext,
					ext.toUpperCase() + " " + mxResources.get("file") + " (." + ext + ")"));
		}

		// Adds filter that accepts all supported image formats
		fileChooser.addChoosableFileFilter(new DefaultFileFilter.ImageFileFilter(mxResources.get("allImages")));
		fileChooser.setFileFilter(defaultFilter);
		int rc = fileChooser.showDialog(null, mxResources.get("save"));

		if (rc != JFileChooser.APPROVE_OPTION) {
			// Indicates that the waiting is over.
			((MainFrame) variamosEditor.getFrame()).waitingCursor(false);
			return "";
		} else {
			// Update the last path dir
			String lastDir = fileChooser.getSelectedFile().getParent();

			// Update the last path where files were saved or open. In this way, it is
			// possible to record the path for next actions that open or close files
			variamosEditor.setLastDir(lastDir);
		}

		fileName = fileChooser.getSelectedFile().getAbsolutePath();
		selectedFilter = fileChooser.getFileFilter();

		if (selectedFilter instanceof DefaultFileFilter) {
			String ext = ((DefaultFileFilter) selectedFilter).getExtension();

			if (!fileName.toLowerCase().endsWith(ext)) {
				fileName += ext;
			}
		}

		return fileName;

	}
}
