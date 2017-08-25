package com.variamos.reasoning.transformer;

import com.variamos.common.core.exceptions.TransformerException;
import com.variamos.reasoning.defectAnalyzer.dto.VMTransformerInDTO;
import com.variamos.reasoning.defectAnalyzer.model.VariabilityModel;


public interface ITransformer {

	public abstract VariabilityModel transform (VMTransformerInDTO inDTO) throws TransformerException;
		
	
}
