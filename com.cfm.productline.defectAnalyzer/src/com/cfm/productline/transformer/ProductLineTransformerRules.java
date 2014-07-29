package com.cfm.productline.transformer;

import java.util.HashMap;
import java.util.Map;

import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.productline.model.defectAnalyzerModel.VariabilityElement;

public class ProductLineTransformerRules {

	private static HlclFactory f = null;

	public ProductLineTransformerRules() {
		super();
		f = new HlclFactory();
	}

	public Map<String, Identifier> getIdentifiers(
			Map<String, VariabilityElement> variabilityElementsMap) {

		Map<String, Identifier> idMap = new HashMap<>();
		// For each variability element create a variable
		for (VariabilityElement elm : variabilityElementsMap.values()) {
			idMap.put(elm.getName(),
					f.newIdentifier(elm.getName(), elm.getName()));
		}
		return idMap;
	}

}
