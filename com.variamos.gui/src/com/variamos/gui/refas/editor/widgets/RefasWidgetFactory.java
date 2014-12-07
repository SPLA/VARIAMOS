package com.variamos.gui.refas.editor.widgets;

import java.util.HashMap;
import java.util.Map;

import com.mxgraph.view.mxGraph;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.refas.core.staticconcepts.SemanticPlusSyntax;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.type.BooleanType;
import com.variamos.syntaxsupport.type.ClassType;
import com.variamos.syntaxsupport.type.EnumerationType;
import com.variamos.syntaxsupport.type.IntegerType;
import com.variamos.syntaxsupport.type.MClassType;
import com.variamos.syntaxsupport.type.MEnumerationType;
import com.variamos.syntaxsupport.type.StringType;


public class RefasWidgetFactory {
	//private DomainRegister register;
	SemanticPlusSyntax semanticSyntaxObject;
	mxGraph graph;
	
	private Map<String, Class<? extends WidgetR>> widgetReg;
	
	public RefasWidgetFactory(VariamosGraphEditor editor){
		//this.register = editor.getDomainRegister();
		this.semanticSyntaxObject = editor.getSematicSintaxObject();
		this.graph = editor.getGraphComponent().getGraph();
		
		widgetReg = new HashMap<String, Class<? extends WidgetR>>();
		widgetReg.put(IntegerType.IDENTIFIER, IntegerWidget.class);
		widgetReg.put(StringType.IDENTIFIER, StringWidget.class);
		widgetReg.put(BooleanType.IDENTIFIER, BooleanWidget.class);
		widgetReg.put(EnumerationType.IDENTIFIER, EnumerationWidget.class);
		widgetReg.put(MEnumerationType.IDENTIFIER, MEnumerationWidget.class);
		widgetReg.put(ClassType.IDENTIFIER, ClassWidget.class);
		widgetReg.put(MClassType.IDENTIFIER, MClassWidget.class);
		
		
	}
	

	public WidgetR getWidgetFor(InstAttribute v){
		//Type d = register.getDomain(v.getType());
		
		String type = v.getModelingAttributeType();
		
		Class<? extends WidgetR> c = null; 
		
		if( widgetReg.containsKey(type) ){
			c = widgetReg.get(type);
		}else{
			//Custom types.
			
			//SetDomain
//			if( d instanceof SetDomain ){
//				c = SetWidget.class;
//			}
		}
		
		if( c == null )
			return null;
		
		WidgetR w = null;
		try {
			w = c.newInstance();
			w.configure(v,semanticSyntaxObject,graph);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return w;
	}
	
}
