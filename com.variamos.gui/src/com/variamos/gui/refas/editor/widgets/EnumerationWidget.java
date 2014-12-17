package com.variamos.gui.refas.editor.widgets;

import java.awt.BorderLayout;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.mxgraph.view.mxGraph;
import com.variamos.gui.refas.editor.SemanticPlusSyntax;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.type.EnumerationType;

/**
 * A class to support enumeration widgets on the interface. Inspired on other
 * widgets from ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-01
 * @see com.variamos.gui.pl.editor.widgets
 */
@SuppressWarnings("serial")
public class EnumerationWidget extends WidgetR {

	private JComboBox<String> txtValue;
	private Object[] enumeration;

	public EnumerationWidget() {
		super();

		setLayout(new BorderLayout());
		txtValue = new JComboBox<String>();
		add(txtValue, BorderLayout.CENTER);
		revalidate();
	}

	@Override
	public void configure(InstAttribute v,
			SemanticPlusSyntax semanticSyntaxObject, mxGraph graph) {
		super.configure(v, semanticSyntaxObject, graph);
		ClassLoader classLoader = EnumerationType.class.getClassLoader();
		@SuppressWarnings("rawtypes")
		Class aClass = null;
		try {
			aClass = classLoader.loadClass(v.getAttribute()
					.getClassCanonicalName());
			//System.out.println("aClass.getName() = " + aClass.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		enumeration = aClass.getEnumConstants();
		for (int i = 0; i < enumeration.length; i++) {
			String patternString = "([_])";
			Pattern p = Pattern.compile(patternString);

			String[] split = p.split(enumeration[i].toString());
			String out = split[0] + " ";
			for (int j = 1; j < split.length; j++)
				out += split[j].toLowerCase() + " ";
			txtValue.addItem(out);
		}
	}

	@Override
	protected void pushValue(InstAttribute v) {
		txtValue.setSelectedItem((String) v.getValue());
		revalidate();
		repaint();
	}

	@Override
	protected void pullValue(InstAttribute v) {
		v.setValue((String) txtValue.getSelectedItem());
	}

	@Override
	public JComponent getEditor() {
		return txtValue;
	}

}