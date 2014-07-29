package com.cfm.productline.transformer;

import com.cfm.productline.dto.VMTransformerInDTO;
import com.cfm.productline.exceptions.TransformerException;
import com.cfm.productline.model.defectAnalyzerModel.VariabilityModel;


public interface ITransformer {

	public abstract VariabilityModel transform (VMTransformerInDTO inDTO) throws TransformerException;
		
	
}
