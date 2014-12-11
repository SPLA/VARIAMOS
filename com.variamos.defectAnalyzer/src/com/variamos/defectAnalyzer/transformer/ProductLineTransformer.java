package com.variamos.defectAnalyzer.transformer;

import java.util.HashMap;
import java.util.Map;

import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.Identifier;
import com.cfm.productline.Constraint;
import com.cfm.productline.ProductLine;
import com.cfm.productline.constraints.GroupConstraint;
import com.cfm.productline.constraints.OptionalConstraint;
import com.cfm.productline.productLine.Pl2Hlcl;
import com.variamos.core.exceptions.TransformerException;
import com.variamos.defectAnalyzer.dto.VMTransformerInDTO;
import com.variamos.defectAnalyzer.model.Dependency;
import com.variamos.defectAnalyzer.model.VariabilityElementDefAna;
import com.variamos.defectAnalyzer.model.VariabilityModel;

/**
 * Transforma los modelos desde el modelo de línea de productos que usa el
 * configurador
 * 
 * @author LuFe
 * 
 */
public class ProductLineTransformer implements ITransformer {

	private ProductLine productLine;

	private Map<String, VariabilityElementDefAna> optionalVariabilityElements;
	private Long constraintCounter;
	private VariabilityModel variabilityModel;
	private Map<String, VariabilityElementDefAna> variabilityElementMap;
	private Map<Long, Dependency> variabilityDependenciesMap;
	private Map<String, Identifier> idMap;

	private ProductLineTransformerRules transformerRules;

	private void init(VMTransformerInDTO inDTO) {
		optionalVariabilityElements = new HashMap<String, VariabilityElementDefAna>();
		constraintCounter = 0L;
		transformerRules = new ProductLineTransformerRules();
		variabilityElementMap = new HashMap<String, VariabilityElementDefAna>();
		variabilityDependenciesMap = new HashMap<Long, Dependency>();
		idMap = new HashMap<>();
		productLine = inDTO.getProductLineConfigurator();
	}

	@Override
	public VariabilityModel transform(VMTransformerInDTO inDTO)
			throws TransformerException {

		if (inDTO.getProductLineConfigurator() != null) {
			init(inDTO);

			// Se lee el product line del configurador

			variabilityModel = new VariabilityModel(inDTO.getNotationType());
			variabilityModel.setName(productLine.getName());

			createProductLineConfiguratorElements();
			// Carga de los variabilityModel
			if (!idMap.isEmpty()) {
				// Restricciones Mandatory, optionales, grupales, de inclusión y
				// de
				// exclusión
				createProductLineConfiguratorConstraints();
			} else {
				throw new TransformerException(
						"Variability elements were not created");
			}

			variabilityModel.setElements(variabilityElementMap);
			variabilityModel.setDependencies(variabilityDependenciesMap);
			variabilityModel.setOptionalVariabilityElements(optionalVariabilityElements);
			return variabilityModel;
		} else {
			throw new TransformerException("Product line model was not loaded");
		}

	}

	private void createProductLineConfiguratorElements()
			throws TransformerException {
		if (productLine != null) {
			// Por cada variabilityElement del productLine, se crea el
			// variability model del defect analyzer
			// Se necesita definir el tema del dominio para productline y de los
			// atributos
			// FIXME
			for (com.cfm.productline.VariabilityElement variabilityElement : productLine
					.getVariabilityElements()) {
				variabilityElementMap.put(
						variabilityElement.getIdentifier(),
						new VariabilityElementDefAna(variabilityElement
								.getIdentifier()));
			}

			idMap = transformerRules.getIdentifiers(variabilityElementMap);

		} else {
			throw new TransformerException(
					"No se puede transformar el modelo, no se encuentra modelo de línea de productos de configurador a transformar");
		}
	}

	private void createProductLineConfiguratorConstraints()
			throws TransformerException {
		if (productLine != null) {

			for (Constraint constraint : productLine.getConstraints()) {
				Expression constraintExpression = Pl2Hlcl.transformConstraint(
						constraint, idMap);

				// Si la constraint tiene más de una expression cada expression
				// se crea como una variabilityDependency diferente
				// Si se crea más de una expressión cada una se utiliza como
				// una dependency diferente
				if (constraintExpression instanceof HlclProgram) {
					for (Expression expression : (HlclProgram) constraintExpression) {
						Expression groupalExpression = expression;
						Dependency groupalVariabilityDependency = new Dependency(
								constraint.toString(), constraintCounter);
						groupalVariabilityDependency
								.setConstraintExpression(groupalExpression);
						variabilityDependenciesMap.put(constraintCounter,
								groupalVariabilityDependency);
						constraintCounter++;
					}
				} else {

					Dependency variabilityDependency = new Dependency(
							constraint.getText(), constraintCounter);
					variabilityDependency
							.setConstraintExpression(constraintExpression);
					variabilityDependenciesMap.put(constraintCounter,
							variabilityDependency);
					constraintCounter++;
				}

				if (constraint instanceof OptionalConstraint) {
					optionalVariabilityElements.put(
							variabilityElementMap.get(
									((OptionalConstraint) constraint)
											.getFeature2Id()).getName(),
							variabilityElementMap
									.get(((OptionalConstraint) constraint)
											.getFeature2Id()));
				}

				if (constraint instanceof GroupConstraint) {
					// Se recorre los hijos de la cardinalidad para
					// adicionarlos
					// al mapa de elementos opcionales
					int childrenSize = ((GroupConstraint) constraint)
							.getChildren().size();
					for (int i = 0; i < childrenSize; i++) {
						String childNode = ((GroupConstraint) constraint)
								.getChildren().get(i);
						optionalVariabilityElements.put(variabilityElementMap
								.get(childNode).getName(),
								variabilityElementMap.get(childNode));
					}

				}

			}
		} else {
			throw new TransformerException(
					"No se puede transformar el modelo, no se encuentra modelo de línea de productos de configurador a transformar");
		}

	}

}
