package com.variamos.gui.refas.editor.widgets;

import java.util.HashMap;
import java.util.Map;

import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.type.BooleanType;
import com.variamos.syntaxsupport.type.IntegerType;
import com.variamos.syntaxsupport.type.RealType;
import com.variamos.syntaxsupport.type.StringType;


@SuppressWarnings("deprecation")
public class RefasWidgetFactory {
	//private DomainRegister register;
	
	private Map<String, Class<? extends WidgetR>> widgetReg;
	
	public RefasWidgetFactory(){
		//this.register = editor.getDomainRegister();
		
		widgetReg = new HashMap<String, Class<? extends WidgetR>>();
		widgetReg.put(IntegerType.IDENTIFIER, IntegerWidget.class);
		widgetReg.put(StringType.IDENTIFIER, StringWidget.class);
		widgetReg.put(BooleanType.IDENTIFIER, BooleanWidget.class);
		widgetReg.put(RealType.IDENTIFIER, RealWidget.class);
		
	}
	



	public WidgetR getWidgetFor(InstAttribute v){
		//Type d = register.getDomain(v.getType());
		
		String type = v.getMetaAttributeType();
		
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
			w.configure(v);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return w;
	}
	
}
