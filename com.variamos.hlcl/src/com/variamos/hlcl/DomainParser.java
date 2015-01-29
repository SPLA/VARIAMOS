package com.variamos.hlcl;


public class DomainParser {
	
	public static Domain parseDomain(String str){
		
		String[] parts = str.split(",", -1);
		
		String digit = "\\d+";
		String range = "\\d+\\s*-\\s*\\d+";
		
		ComposedDomain domain = new ComposedDomain();
		
		IntervalDomain intDom = new IntervalDomain();
		RangeDomain rd = null;
		for( String part : parts ){
			part = part.trim();
			if (part.matches(digit)){
				int v = Integer.parseInt(part);
				
				if( !intDom.getDomainValues().contains(v))
					intDom.add(v);
				
				continue;
			}
			
			if( part.matches(range)){
				int sym = part.indexOf('-');
				int lowerValue = Integer.parseInt(part.substring(0, sym).trim());
				int upperValue = Integer.parseInt(part.substring(sym + 1).trim());

				rd = new RangeDomain(lowerValue, upperValue);
				domain.getDomains().add(rd);
			}
		}
		
		if( intDom.size() == 0 && domain.getDomains().size() == 1){
			//Check if it's binary
			if( rd.getLowerValue() == 0 && rd.getUpperValue() == 1)
				return BinaryDomain.INSTANCE;
			
			return rd;
		}
		
		if( rd == null){
			if( intDom.size() == 2 ){
				//Check if binary
				if( intDom.getDomainValues().contains(0) && intDom.getDomainValues().contains(1))
					return BinaryDomain.INSTANCE;
			}
			return intDom;
		}
		
		domain.getDomains().add(intDom);
		
		return domain;
	}
}
