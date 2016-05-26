package com.variamos.io;

import javax.swing.JTextArea;

public class ConsoleTextArea {
	private static JTextArea textArea;
	private static boolean debug = false;

	public static void setTextArea(JTextArea textArea) {
		ConsoleTextArea.textArea = textArea;
	}

	public static JTextArea getTextArea() {
		return textArea;
	}

	public static void setDebug(boolean debug) {
		ConsoleTextArea.debug = debug;
	}

	public static boolean getDebu() {
		return debug;
	}

	public static void addText(String text) {
		ConsoleTextArea.textArea.setText(ConsoleTextArea.textArea.getText()
				+ '\n' + text);
	}

	public static void addText(StackTraceElement[] text) {
		String out = "";

		for (StackTraceElement e : text)
			out += e.toString() + '\n';
		if (debug)
			System.err.println(out);
		else
			ConsoleTextArea.textArea.setText(ConsoleTextArea.textArea.getText()
					+ '\n' + out);
	}

	public static void clearText() {
		String out = "";
		ConsoleTextArea.textArea.setText(ConsoleTextArea.textArea.getText()
				+ '\n' + out);
	}

	public static String getText() {
		return textArea.getText();
	}

	public static void setTextEditable(boolean bol) {
		textArea.setEditable(bol);
	}
}
