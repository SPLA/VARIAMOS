package com.cfm.productline.editor.widgets;

import java.util.HashMap;
import java.util.Map;

import com.cfm.productline.Variable;
import com.cfm.productline.editor.ProductLineEditor;
import com.cfm.productline.type.BooleanType;
import com.cfm.productline.type.IntegerType;
import com.cfm.productline.type.RealType;
import com.cfm.productline.type.StringType;


public class WidgetFactory {
	//private DomainRegister register;
	
	private Map<String, Class<? extends Widget>> widgetReg;
	
	public WidgetFactory(ProductLineEditor editor){
		//this.register = editor.getDomainRegister();
		
		widgetReg = new HashMap<String, Class<? extends Widget>>();
		widgetReg.put(IntegerType.IDENTIFIER, IntegerWidget.class);
		widgetReg.put(StringType.IDENTIFIER, StringWidget.class);
		widgetReg.put(BooleanType.IDENTIFIER, BooleanWidget.class);
		widgetReg.put(RealType.IDENTIFIER, RealWidget.class);
		
	}
	
	
	public Widget getWidgetFor(Variable v){
		//Type d = register.getDomain(v.getType());
		
		String type = v.getType();
		
		Class<? extends Widget> c = null; 
		
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
		
		Widget w = null;
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
