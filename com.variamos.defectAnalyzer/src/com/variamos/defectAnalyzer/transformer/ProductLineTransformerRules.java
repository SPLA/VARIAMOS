package com.variamos.defectAnalyzer.transformer;

import java.util.HashMap;
import java.util.Map;

import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.variamos.defectAnalyzer.model.VariabilityElementDefAna;

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
