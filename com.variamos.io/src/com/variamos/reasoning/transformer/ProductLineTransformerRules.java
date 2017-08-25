package com.variamos.reasoning.transformer;

import java.util.HashMap;
import java.util.Map;

import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.reasoning.defectAnalyzer.model.VariabilityElementDefAna;

public class ProductLineTransformerRules {

	private static HlclFactory f = null;

	public ProductLineTransformerRules() {
		super();
		f = new HlclFactory();
	}

	public Map<String, Identifier> getIdentifiers(
			Map<String, VariabilityElementDefAna> variabilityElementsMap) {

		Map<String, Identifier> idMap = new HashMap<>();
		// For each variability element create a variable
		for (VariabilityElementDefAna elm : variabilityElementsMap.values()) {
			idMap.put(elm.getName(),
					f.newIdentifier(elm.getName(), elm.getName()));
		}
		return idMap;
	}

}
