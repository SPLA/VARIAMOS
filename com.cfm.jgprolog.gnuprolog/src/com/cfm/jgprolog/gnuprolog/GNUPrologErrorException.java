package com.cfm.jgprolog.gnuprolog;

import com.cfm.jgprolog.core.PrologException;

@SuppressWarnings("serial")
public class GNUPrologErrorException extends PrologException{
	
//	public static HashMap<String, GNUPrologErrorException> proto = new HashMap<>();
	
//	static{
//		proto.put("existence_error", new GNUPrologExistenceErrorException());
//		proto.put("instantiation_error", new GNUPrologInstantiationErrorException());
//		proto.put("type_error", new GNUPrologTypeErrorException());
//		proto.put("domain_error", new GNUPrologDomainErrorException());
//		proto.put("permission_error", new GNUPrologPermisionErrorException());
//		proto.put("permission_error", new GNUPrologPermisionErrorException());
//		
//	}

	public GNUPrologErrorException() {
		super(JGProlog.Pl_Write_To_String(JGProlog.Pl_Get_Exception()));
	}

}
