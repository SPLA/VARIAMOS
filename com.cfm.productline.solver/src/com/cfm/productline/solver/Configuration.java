package com.cfm.productline.solver;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Configuration {
	
	public static final int 	IGNORED = -1,
								ENFORCED = 1,
								BANNED = 0;
	
	protected TreeMap<String, Integer> configuration;

	public Configuration(){
		configuration = new TreeMap<String, Integer>();
	}
	
	public Configuration set(String id, Integer value){
		if( value == null )
			ignore(id);
		else
			configuration.put(id, value);
		return this;
	}
	
	public Configuration enforce(String id){
		configuration.put(id, ENFORCED);
		return this;
	}
	
	public Configuration ban(String id){
		configuration.put(id, BANNED);
		return this;
	}
	
	public Configuration ignore(String id){
		configuration.remove(id);
		return this;
	}

	public Set<String> getNotIgnored() {
		return configuration.keySet();
	}
	
	public int stateOf(String id){
		if( !configuration.containsKey(id) )
			return IGNORED;
		
		return configuration.get(id);
	}

	public void debugPrint() {
		for(String key : configuration.keySet()){
			System.out.println(key + " : " + configuration.get(key));
		}
	}

	@Override
	public boolean equals(Object obj) {
		
		if( obj == null && this.configuration.size() == 0 )
			return true;
		
		if( obj instanceof Configuration ){
			Configuration other = (Configuration) obj;
			
			Set<String> mine = getNotIgnored();
			if( mine.size() != other.getNotIgnored().size() )
				return false;
			
			for( String id : other.getNotIgnored() ){
				if( stateOf(id) != other.stateOf(id) )
					return false;
			}
			
			return true;
		}
		return super.equals(obj);
	}

	public TreeMap<String, Integer> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(TreeMap<String, Integer> configuration) {
		this.configuration = configuration;
	}

}
