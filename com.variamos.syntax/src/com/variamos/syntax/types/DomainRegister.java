package com.variamos.syntax.types;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * document. Copied from ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-12-01
 * @see com.cfm.productline.type.DomainRegister
 */
public class DomainRegister implements Serializable{
	private static final long serialVersionUID = -7785266173877405598L;
	
	private Map<String, Type> reg = new HashMap<>();
	
	public DomainRegister(){
		//Register the basic types
		registerDomain(new IntegerType());
		registerDomain(new StringType());
		registerDomain(new BooleanType());
	}
	
	public void registerDomain(Type domain){
		reg.put(domain.getIdentifier(), domain);
	}
	
	public void registerDomain(String identifier, Type domain){
		reg.put(identifier, domain);
	}
	
	public <T extends Type> T getDomain(String identifier){
		return (T) reg.get(identifier);
	}
	
	public Collection<Type> getRegisteredDomains(){
		return reg.values();
	}

	public void unregisterDomain(String string) {
		reg.remove(string);
	}
	
//	public Set<String> domainsFor(Object value){
//		Set<String> domains = new HashSet<>();
//		
//		if( value instanceof String)
//			domains.add(Domain.STRING);
//		
//		if( value instanceof Integer)
//			domains.add( Domain.INTEGER);
//		
//		if( value instanceof Float)
//			domains.add( Domain.REAL);
//		
//		if( value instanceof Boolean)
//			domains.add( Domain.BOOLEAN);
//		
//		for(Domain d : getRegisteredDomains())
//			if( d.contains(value) )
//				domains.add(d.getIdentifier());
//		
//		return domains;
//	}
}
