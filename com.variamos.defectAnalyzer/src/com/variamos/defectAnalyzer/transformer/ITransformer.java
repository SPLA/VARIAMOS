package com.variamos.defectAnalyzer.transformer;

import com.variamos.core.exceptions.TransformerException;
import com.variamos.defectAnalyzer.dto.VMTransformerInDTO;
import com.variamos.defectAnalyzer.model.VariabilityModel;


public interface ITransformer {

	public abstract VariabilityModel transform (VMTransformerInDTO inDTO) throws TransformerException;
		
	
}
