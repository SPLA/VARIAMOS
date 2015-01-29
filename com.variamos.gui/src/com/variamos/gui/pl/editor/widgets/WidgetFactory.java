package com.variamos.gui.pl.editor.widgets;

import java.util.HashMap;
import java.util.Map;

import com.cfm.productline.Variable;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.syntax.types.BooleanType;
import com.variamos.syntax.types.IntegerType;
import com.variamos.syntax.types.StringType;


public class WidgetFactory {
	//private DomainRegister register;
	
	private Map<String, Class<? extends WidgetPL>> widgetReg;
	
	public WidgetFactory(VariamosGraphEditor editor){
		//this.register = editor.getDomainRegister();
		
		widgetReg = new HashMap<String, Class<? extends WidgetPL>>();
		widgetReg.put(IntegerType.IDENTIFIER, IntegerWidget.class);
		widgetReg.put(StringType.IDENTIFIER, StringWidget.class);
		widgetReg.put(BooleanType.IDENTIFIER, BooleanWidget.class);
		
	}
	
	
	public WidgetPL getWidgetFor(Variable v){
		//Type d = register.getDomain(v.getType());
		
		String type = v.getType();
		
		Class<? extends WidgetPL> c = null; 
		
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
		
		WidgetPL w = null;
		try {
			w = c.newInstance();
			w.configure(v);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return w;
	}
	
}
