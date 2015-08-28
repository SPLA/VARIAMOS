package com.variamos.gui.perspeditor.widgets;

import java.util.HashMap;
import java.util.Map;

import com.mxgraph.view.mxGraph;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.perspsupport.syntaxsupport.EditableElementAttribute;
import com.variamos.perspsupport.types.BooleanType;
import com.variamos.perspsupport.types.ClassMultiSelectionType;
import com.variamos.perspsupport.types.ClassSingleSelectionType;
import com.variamos.perspsupport.types.EnumerationMultiSelectionType;
import com.variamos.perspsupport.types.EnumerationSingleSelectionType;
import com.variamos.perspsupport.types.IntegerType;
import com.variamos.perspsupport.types.SetType;
import com.variamos.perspsupport.types.StringType;

/**
 * A class to dynamically load appropriate widget. Based on WidgetFactory from
 * ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-10
 * @see com.variamos.gui.pl.editor.widgets.WidgetFactory
 */

public class RefasWidgetFactory {
	// private DomainRegister register;
	mxGraph graph;
	boolean showSimulationCustomizationBox;

	private Map<String, Class<? extends WidgetR>> widgetReg;

	public RefasWidgetFactory(VariamosGraphEditor editor) {
		// this.register = editor.getDomainRegister();
		this.graph = editor.getGraphComponent().getGraph();
		this.showSimulationCustomizationBox = editor.isShowSimulationCustomizationBox();

		widgetReg = new HashMap<String, Class<? extends WidgetR>>();
		widgetReg.put(IntegerType.IDENTIFIER, IntegerWidget.class);
		widgetReg.put(StringType.IDENTIFIER, StringWidget.class);
		widgetReg.put(BooleanType.IDENTIFIER, BooleanWidget.class);
		widgetReg.put(EnumerationSingleSelectionType.IDENTIFIER, EnumerationWidget.class);
		widgetReg.put(EnumerationMultiSelectionType.IDENTIFIER, MEnumerationWidget.class);
		widgetReg.put(ClassSingleSelectionType.IDENTIFIER, ClassWidget.class);
		widgetReg.put(SetType.IDENTIFIER, SetWidget.class);
		//widgetReg.put(SortIdListType.IDENTIFIER, SortIdListWidget.class);
		widgetReg.put(ClassMultiSelectionType.IDENTIFIER, MClassWidget.class);

	}

	public WidgetR getWidgetFor(EditableElementAttribute v) {
		// Type d = register.getDomain(v.getType());

		String type = v.getAttributeType();

		Class<? extends WidgetR> c = null;

		if (widgetReg.containsKey(type)) {
			c = widgetReg.get(type);
		} else {
			// Custom types.

			// SetDomain
			// if( d instanceof SetDomain ){
			// c = SetWidget.class;
			// }
		}

		if (c == null)
			return null;

		WidgetR w = null;
		try {
			w = c.newInstance();
			w.configure(v, graph, showSimulationCustomizationBox);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return w;
	}

}
