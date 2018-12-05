package com.variamos.reasoning.core.transformer;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntNumericExpression;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.VariabilityElementDefAna;
import com.variamos.reasoning.defectAnalyzer.model.dto.VMTransformerInDTO;
import com.variamos.reasoning.defectAnalyzer.model.transformation.Dependency;
import com.variamos.reasoning.defectAnalyzer.model.transformation.RangeDomainDefectAnalyzer;
import com.variamos.reasoning.defectAnalyzer.model.transformation.TransformerConstants;
import com.variamos.reasoning.defectAnalyzer.model.transformation.VariabilityModel;

import constraints.BooleanVariable;
import constraints.PropositionalFormula;
import fm.FeatureGroup;
import fm.FeatureModel;
import fm.FeatureModelException;
import fm.FeatureTreeNode;
import fm.RootNode;
import fm.SolitaireFeature;
import fm.XMLFeatureModel;

/**
 * La Clase TransformationManager es la encargada de realizar las respectivas
 * transformaciones de los modelos a un variability model usado en el defect
 * analyzer
 * 
 * 
 * @author Luisa Rinc0n
 * @Version 2.0 Abril/2013
 */
public class FeatureModelSPLOTransformer implements ITransformer {

	// Para el caso de las caracteristicas relacionadas con elementos mandatory
	// el dominio posible es solo 1, no cero.
	private Map<String, VariabilityElementDefAna> optionalVariabilityElements;
	private Long constraintCounter;
	private VariabilityModel variabilityModel;
	private Map<String, VariabilityElementDefAna> variabilityElementMap;
	private Map<Long, Dependency> variabilityDependenciesMap;
	private Map<Long, Dependency> permanentDependenciesMap;
	private Map<Long, Dependency> inclusionExclusionDependenciesMap;
	
	private HlclProgram model;
	private String plHlclprogram;
	private String rootName;





	private FeatureModelTransformerRules transformerRules;

	private void init(VMTransformerInDTO inDTO) {

		optionalVariabilityElements = new HashMap<String, VariabilityElementDefAna>();
		constraintCounter = 0L;
		transformerRules = new FeatureModelTransformerRules();
		variabilityElementMap = new HashMap<String, VariabilityElementDefAna>();
		variabilityDependenciesMap = new HashMap<Long, Dependency>();
		permanentDependenciesMap = new HashMap<Long, Dependency>();
		inclusionExclusionDependenciesMap = new HashMap<Long, Dependency>();
		model= new HlclProgram();

	}

	/**
	 * Lee el xml para identificar las caracterï¿½sticas raices
	 * 
	 * @param node
	 * @param tab
	 * @param plFile
	 */
	private void traverseDFSGPL(FeatureTreeNode node) {

		String featureName = transformName(node.getName());

		String dependencyName = "";

		// Caracteristica raiz
		if (node instanceof RootNode) {
			// Se crea el variabilityElement su valores de dominio posible es
			// solo el valor de 1 pq es mandatory
			VariabilityElementDefAna element = new VariabilityElementDefAna(featureName);
			RangeDomainDefectAnalyzer onlySelectedDomain = new RangeDomainDefectAnalyzer();
			element.setDomain(onlySelectedDomain);
			variabilityElementMap.put(element.getName(), element);
			rootName= element.getName();

			dependencyName = transformerRules.getRootDependencyName(element);
			Dependency variabilityDependency = new Dependency(dependencyName,
					constraintCounter);
			variabilityDependency.setConstraintExpression(transformerRules
					.getAssignRule(TransformerConstants.ONE, element));
			permanentDependenciesMap.put(constraintCounter, variabilityDependency);
			model.add(variabilityDependency.getConstraintExpression());

			constraintCounter++;

		} else {

			FeatureTreeNode parent = (FeatureTreeNode) node.getParent();

			// Se agrega la caracteristica hallada a la variable que
			// almacena las restricciones que representan opcionalidad
			String parentFeatureName = transformName(parent.getName());

			if (node instanceof SolitaireFeature) {
				VariabilityElementDefAna variabilityElementDefAna = new VariabilityElementDefAna(
						featureName);
				VariabilityElementDefAna parentVariabilityElement = variabilityElementMap
						.get(parentFeatureName);
				variabilityElementMap.put(variabilityElementDefAna.getName(),
						variabilityElementDefAna);

				// Caracteristica opcional
				if (((SolitaireFeature) node).isOptional()) {

					// Se adiciona la depencencia opcional al modelo de
					// variabilidad
					dependencyName = transformerRules
							.getOptionalDependencyName(
									parentVariabilityElement,
									variabilityElementDefAna);
					Dependency variabilityDependency = new Dependency(
							dependencyName, constraintCounter);
					variabilityDependency
							.setConstraintExpression(transformerRules
									.getOptionalRule(parentVariabilityElement,
											variabilityElementDefAna));

					// Negacion de la dependencia opcional
					variabilityDependency
							.setNegationExpression(transformerRules
									.getNegationOptionalRule(
											parentVariabilityElement,
											variabilityElementDefAna));

					variabilityDependenciesMap.put(constraintCounter,
							variabilityDependency);
					model.add(variabilityDependency.getConstraintExpression());
					
					optionalVariabilityElements.put(
							variabilityElementDefAna.getName(), variabilityElementDefAna);
					constraintCounter++;
				}
				// Caracteristica obligatoria
				else {
					// Se adiciona la depencencia opcional al modelo de
					// variabilidad
					dependencyName = transformerRules
							.getMandatoryDependencyName(
									parentVariabilityElement,
									variabilityElementDefAna);
					Dependency variabilityDependency = new Dependency(
							dependencyName, constraintCounter);
					variabilityDependency
							.setConstraintExpression(transformerRules
									.getMandatoryRule(parentVariabilityElement,
											variabilityElementDefAna));

					// Negacion de la dependencia obligatoria
					variabilityDependency
							.setNegationExpression(transformerRules
									.getNegationMandatoryRule(
											parentVariabilityElement,
											variabilityElementDefAna));

					variabilityDependenciesMap.put(constraintCounter,
							variabilityDependency);
					model.add(variabilityDependency.getConstraintExpression());

					constraintCounter++;

				}

			}
			// Grupo de caracteristicas
			else if (node instanceof FeatureGroup) {

				IntBooleanExpression constraintExpression = null;
				IntBooleanExpression negationConstraintExpression = null;
				IntBooleanExpression constraintExpression2 = null;
				IntBooleanExpression negationConstraintExpression2 = null;
				String dependencyName2 = "";
				// Se obtiene el valor menor y mayor de cardinalidad
				Long minCardinality = new Long(
						String.valueOf(((FeatureGroup) node).getMin()));
				Long maxCardinality = new Long(
						String.valueOf(((FeatureGroup) node).getMax()));
				List<VariabilityElementDefAna> constraintElements = new ArrayList<VariabilityElementDefAna>();

				// El -1 significa el * en la notacion de splot,por lo que se
				// cuenta la cantidad maxima posible segun la cantidad de hijos
				// q tenga el nodo
				if (maxCardinality == -1) {
					maxCardinality = (long) node.getChildCount();
				}

				// Se recorre los hijos de la cardinalidad para construir la
				// restriccion
				
				// One should not use deprecated types
				@SuppressWarnings("rawtypes")
				Enumeration dummies = node.children();
				List<FeatureTreeNode> childrenNodes = new ArrayList<>();
				while(dummies.hasMoreElements()) {
					childrenNodes.add((FeatureTreeNode) dummies.nextElement());
				}
				
				 		

				for(FeatureTreeNode childNode : childrenNodes){
					String childFeatureName = transformName(childNode.getName());
					VariabilityElementDefAna variabilityElementDefAna = new VariabilityElementDefAna(
							childFeatureName);
					// Se crea la caracteristica en el modelo con dominio 0, 1
					variabilityElementMap.put(variabilityElementDefAna.getName(),
							new VariabilityElementDefAna(childFeatureName));

					optionalVariabilityElements.put(
							variabilityElementMap.get(childFeatureName)
									.getName(), variabilityElementMap
									.get(childFeatureName));

					constraintElements.add(variabilityElementMap
							.get(childFeatureName));
				}

				if (minCardinality.equals(maxCardinality)) {
					constraintExpression = transformerRules
							.getGroupalDependencyRule3(variabilityElementMap
									.get(parentFeatureName), constraintElements);

					negationConstraintExpression = transformerRules
							.getNegationGroupalDependencyRule3(
									variabilityElementMap
											.get(parentFeatureName),
									constraintElements);

					dependencyName = transformerRules
							.getGroupalDependencyName3(variabilityElementMap
									.get(parentFeatureName), constraintElements
									.toString());

				} else {
					// Para el caso en el que diga cuanto es y no un *
					constraintExpression = transformerRules
							.getGroupalDependencyRule1(variabilityElementMap
									.get(parentFeatureName),
									constraintElements, minCardinality
											.intValue());

					negationConstraintExpression = transformerRules
							.getNegationGroupalDependencyRule1(
									variabilityElementMap
											.get(parentFeatureName),
									constraintElements, minCardinality
											.intValue());

					dependencyName = transformerRules
							.getGroupalDependencyName1(variabilityElementMap
									.get(parentFeatureName), constraintElements
									.toString());

					constraintExpression2 = transformerRules
							.getGroupalDependencyRule2(variabilityElementMap
									.get(parentFeatureName),
									constraintElements, maxCardinality
											.intValue());

					negationConstraintExpression2 = transformerRules
							.getNegationGroupalDependencyRule2(
									variabilityElementMap
											.get(parentFeatureName),
									constraintElements, maxCardinality
											.intValue());

					dependencyName2 = transformerRules
							.getGroupalDependencyName2(variabilityElementMap
									.get(parentFeatureName), constraintElements
									.toString());

				}

				// Se adiciona la constraint
				Dependency variabilityDependency = new Dependency(
						dependencyName, constraintCounter);
				variabilityDependency
						.setConstraintExpression(constraintExpression);
				variabilityDependency
						.setNegationExpression(negationConstraintExpression);
				variabilityDependenciesMap.put(constraintCounter,
						variabilityDependency);
				model.add(variabilityDependency.getConstraintExpression());


				constraintCounter++;
				// Si se crearon dos restricciones se crea otro objeto de
				// restriccion
				if (constraintExpression2 != null) {
					Dependency variabilityDependency2 = new Dependency(
							dependencyName2, constraintCounter);
					variabilityDependency2
							.setConstraintExpression(constraintExpression2);
					variabilityDependency2
							.setNegationExpression(negationConstraintExpression2);
					variabilityDependenciesMap.put(constraintCounter,
							variabilityDependency2);
					model.add(variabilityDependency.getConstraintExpression());

					constraintCounter++;

				}

			}

		}
		// en esta parte se llama al mismo metodo con cada hijo del nodo
		// actual,
		// de manera recursiva
		for (int i = 0; i < node.getChildCount(); i++) {
			traverseDFSGPL((FeatureTreeNode) node.getChildAt(i));
		}
	}

	/**
	 * Adiciona las restricciones de inclusion y exclusion a la ontologia, segun
	 * lo expresado en el modelo
	 * 
	 * @param featureModel
	 */
	private void traverseConstraintsGPL(FeatureModel featureModel) {

		String dependencyName = "";
		// Esta variable se utiliza para darle nombre a la relacion
		StringBuilder nameConstraintFeaturesSet = null;
		List<IntNumericExpression> numericExpressionsList = new ArrayList<IntNumericExpression>();

		for (PropositionalFormula formula : featureModel.getConstraints()) {
			nameConstraintFeaturesSet = new StringBuilder();
			Iterator<BooleanVariable> iter = formula.getVariables().iterator();
			BooleanVariable element;
			Set<VariabilityElementDefAna> relatedVariabilityElements = new HashSet<VariabilityElementDefAna>();
			numericExpressionsList.clear();
			while (iter.hasNext()) {
				element = (BooleanVariable) iter.next();
				FeatureTreeNode node = featureModel
						.getNodeByID(element.getID());
				String featureName = transformName(node.getName());
				numericExpressionsList.add(transformerRules
						.getIdentifiersOfExpression(element,
								variabilityElementMap.get(featureName)));

				nameConstraintFeaturesSet.append(featureName);
				// Si hay un siguiente elemento se adiciona un - para el nombre
				// original de la constraint
				if (iter.hasNext()) {
					nameConstraintFeaturesSet
							.append(TransformerConstants.TO);
				}
				// Creamos un set con los variabilityElement relacionados
				// con la constraint
				relatedVariabilityElements.add(variabilityElementMap
						.get(featureName));
			}

			if (!numericExpressionsList.isEmpty()) {
				dependencyName = transformerRules
						.getPropositionalName(nameConstraintFeaturesSet
								.toString());

				Dependency variabilityDependency = new Dependency(
						dependencyName, constraintCounter);
				variabilityDependency
						.setConstraintExpression(transformerRules
								.getPropositionalConstraintsRule(numericExpressionsList));
				// Negacion de la expresion.
				variabilityDependency
						.setNegationExpression(transformerRules
								.getNegationPropositionalConstraintsRule(numericExpressionsList));
				variabilityDependenciesMap.put(constraintCounter,
						variabilityDependency);
				model.add(variabilityDependency.getConstraintExpression());

				// Conjunto de dependencias de exclusion e inclusion
				inclusionExclusionDependenciesMap.put(constraintCounter,
						variabilityDependency);
				constraintCounter++;
			}
		}
	}

	@Override
	public VariabilityModel transform(VMTransformerInDTO inDTO)
			throws FunctionalException {

		init(inDTO);
		try {
			// Se lee el modelo de caracteristicas, usando la libreria que
			// provee
			// SPLOT

			FeatureModel featureModel = new XMLFeatureModel(
					inDTO.getPathToTransform(),
					XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
			featureModel.loadModel();

			variabilityModel = new VariabilityModel(inDTO.getNotationType());
			variabilityModel.setName(featureModel.getName());

			// Restricciones Mandatory, optionales, grupales
			traverseDFSGPL(featureModel.getRoot());
			traverseConstraintsGPL(featureModel);

			variabilityModel.setElements(variabilityElementMap);
			variabilityModel.setDependencies(variabilityDependenciesMap);
			//continuar con las fixed dependencies                                                                      
			variabilityModel.setFixedDependencies(permanentDependenciesMap);
			variabilityModel.setModel(model);
			variabilityModel
					.setOptionalVariabilityElements(optionalVariabilityElements);
			variabilityModel
					.setInclusionExclusionDependencies(inclusionExclusionDependenciesMap);
			variabilityModel.setNumbeOfFeatures(featureModel.countFeatures());

			int numberOfDependencies = variabilityDependenciesMap.size();
			int numberOfTraversalDependencies = featureModel.countConstraints();
			int numberOfNonTraversalDependencies = numberOfDependencies
					- numberOfTraversalDependencies;
			Float percentageTraversalDependencies = ((float) numberOfTraversalDependencies / numberOfDependencies) * 100;
			Float percentageNonTraversalDependencies = ((float) numberOfNonTraversalDependencies / numberOfDependencies) * 100;

			variabilityModel
					.setNumberOfTraversalDependencies(numberOfTraversalDependencies);
			variabilityModel.setNumberOfDependencies(numberOfDependencies);
			variabilityModel
					.setNumberOfNonTrasversalDependencies(numberOfNonTraversalDependencies);
			variabilityModel
					.setPercentageNonTraversalDependencies(percentageNonTraversalDependencies
							.intValue());
			variabilityModel
					.setPercentageTraversalDependencies(percentageTraversalDependencies
							.intValue());
			return variabilityModel;

		} catch (FeatureModelException e) {
			throw new FunctionalException(e);

		}

	}

	private String transformName(String name) {
		// Se pasa a mayusculas la primera letra para garantizar
		// que sea interpretada como variable en los solvers por ejemplo en
		// Prolog
		String changedName = evaluarPrimerCaracter(name.charAt(0))
				.toUpperCase()
				+ name.trim().substring(1).replaceAll(" ", "_")
						.replaceAll("\\-", "Minus").replaceAll("\\+", "Plus")
						.replaceAll("\\.", "dot").replaceAll("/", "");

		return changedName;
	}

	private String evaluarPrimerCaracter(char caracterInicial) {
		if ((caracterInicial >= 65 && caracterInicial <= 90)
				|| (caracterInicial >= 97 && caracterInicial <= 122))
			return String.valueOf(caracterInicial).toUpperCase();
		return "N".concat(String.valueOf(caracterInicial));
	}
	public String getRootName() {
		return rootName;
	}

}
