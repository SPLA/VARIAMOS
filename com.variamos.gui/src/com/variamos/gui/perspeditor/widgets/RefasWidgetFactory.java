package com.variamos.gui.perspeditor.widgets;

import java.util.HashMap;
import java.util.Map;

import com.mxgraph.view.mxGraph;
import com.variamos.dynsup.interfaces.IntInstAttribute;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.types.BooleanType;
import com.variamos.dynsup.types.ClassMultiSelectionType;
import com.variamos.dynsup.types.ClassSingleSelectionType;
import com.variamos.dynsup.types.DomainType;
import com.variamos.dynsup.types.EnumerationMultiSelectionType;
import com.variamos.dynsup.types.EnumerationSingleSelectionType;
import com.variamos.dynsup.types.IntegerType;
import com.variamos.dynsup.types.SetType;
import com.variamos.dynsup.types.StringType;
import com.variamos.gui.core.io.ConsoleTextArea;
import com.variamos.gui.maineditor.VariamosGraphEditor;

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
	InstanceModel semanticModel;
	boolean showSimulationCustomizationBox;
	private int perspective;

	private Map<String, Class<? extends WidgetR>> widgetReg;

	public RefasWidgetFactory(VariamosGraphEditor editor) {
		// this.register = editor.getDomainRegister();
		this.graph = editor.getGraphComponent().getGraph();
		perspective = editor.getPerspective();
		this.semanticModel = editor.getEditedModel().getOperationalModel();
		this.showSimulationCustomizationBox = editor
				.isShowSimulationCustomizationBox();

		widgetReg = new HashMap<String, Class<? extends WidgetR>>();
		widgetReg.put(IntegerType.IDENTIFIER, IntegerWidget.class);
		widgetReg.put(StringType.IDENTIFIER, StringWidget.class);
		widgetReg.put(DomainType.IDENTIFIER, StringWidget.class);
		widgetReg.put(BooleanType.IDENTIFIER, BooleanWidget.class);
		widgetReg.put(EnumerationSingleSelectionType.IDENTIFIER,
				EnumerationWidget.class);
		widgetReg.put(EnumerationMultiSelectionType.IDENTIFIER,
				MEnumerationWidget.class);
		widgetReg.put(ClassSingleSelectionType.IDENTIFIER, ClassWidget.class);
		widgetReg.put(SetType.IDENTIFIER, SetWidget.class);
		// widgetReg.put(SortIdListType.IDENTIFIER, SortIdListWidget.class);
		widgetReg.put(ClassMultiSelectionType.IDENTIFIER, MClassWidget.class);
		widgetReg.put("MetaEnumeration", EnumerationWidget.class);
		widgetReg.put("Instance", StringWidget.class);
		widgetReg.put("InstanceSet", StringWidget.class);
		widgetReg.put("SeMetaModel", StringWidget.class);
		widgetReg.put("OpMetaModel", StringWidget.class);
		widgetReg.put("ClassSet", StringWidget.class);
	}

	public WidgetR getWidgetFor(IntInstAttribute v) {
		// Type d = register.getDomain(v.getType());

		String type = v.getType();

		Class<? extends WidgetR> c = null;
		// FIXME v1.1 workaround for null types
		if (type == null)
			type = "String";

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
			w.configure(v, graph, semanticModel,
					showSimulationCustomizationBox, perspective);
		} catch (InstantiationException e) {
			ConsoleTextArea.addText(e.getStackTrace());
		} catch (IllegalAccessException e) {
			ConsoleTextArea.addText(e.getStackTrace());
		}

		return w;
	}
}
