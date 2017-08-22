package com.variamos.reasoning.transformer;

import java.io.File;
import java.util.Collection;

import com.variamos.core.enums.NotationType;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.core.exceptions.FunctionalException;
import com.variamos.core.exceptions.TransformerException;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.reasoning.defectAnalyzer.dto.VMTransformerInDTO;
import com.variamos.reasoning.defectAnalyzer.model.VariabilityModel;
import com.variamos.reasoning.util.ConstraintRepresentationUtil;

public class VariabilityModelTransformer {

	private VMTransformerInDTO inDTO;

	public VariabilityModelTransformer() {
		super();
	}

	public VariabilityModelTransformer(VMTransformerInDTO inDTO) {
		super();
		this.inDTO = inDTO;
	}

	public VariabilityModel transformToVariabilityModel()
			throws TransformerException {
		VariabilityModel outDTO = new VariabilityModel();

		if (inDTO.getNotationType().equals(NotationType.FEATURES_MODELS)) {
			FeatureModelSPLOTransformer featureModelTransformer = new FeatureModelSPLOTransformer();
			File file= new File(inDTO.getPathToTransform());
			if(!file.exists()){
				throw new TransformerException("El modelo a analizar "
						+ inDTO.getPathToTransform() + " no existe");
			}else{
			outDTO = featureModelTransformer.transform(inDTO);
			}
		}
		if (inDTO.getNotationType().equals(NotationType.PRODUCT_LINE)) {
			ProductLineTransformer plConfiguratorTransformer = new ProductLineTransformer();
			outDTO = plConfiguratorTransformer.transform(inDTO);
		}

		

		return outDTO;
	}

	public void printTransformedModelSWIProlog(VariabilityModel variabilityModel) {
		Collection<BooleanExpression> expressions = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		try {
			String program = ConstraintRepresentationUtil
					.constraintToPrologProgram(expressions,
							SolverEditorType.SWI_PROLOG);
			System.out.println(program);
		} catch (FunctionalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void printTransformedModelGNUProlog(VariabilityModel variabilityModel) {
		Collection<BooleanExpression> expressions = ConstraintRepresentationUtil
				.dependencyToExpressionList(variabilityModel.getDependencies(),
						variabilityModel.getFixedDependencies());

		try {
			String program = ConstraintRepresentationUtil
					.constraintToPrologProgram(expressions,
							SolverEditorType.GNU_PROLOG);
			System.out.println(program);
		} catch (FunctionalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
