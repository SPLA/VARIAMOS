package com.cfm.productline.transformer;

import java.io.File;
import java.util.Collection;

import com.cfm.hlcl.Expression;
import com.cfm.productline.dto.VMTransformerInDTO;
import com.cfm.productline.exceptions.FunctionalException;
import com.cfm.productline.exceptions.TransformerException;
import com.cfm.productline.model.defectAnalyzerModel.VariabilityModel;
import com.cfm.productline.model.enums.NotationType;
import com.cfm.productline.model.enums.SolverEditorType;
import com.cfm.productline.util.ConstraintRepresentationUtil;

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
			ProductLineConfiguratorTransformer plConfiguratorTransformer = new ProductLineConfiguratorTransformer();
			outDTO = plConfiguratorTransformer.transform(inDTO);
		}

		if (inDTO.getNotationType().equals(NotationType.PROLOG)) {
			BooleanPrologTransformer prologTransformer = new BooleanPrologTransformer();
			outDTO = prologTransformer.transform(inDTO);
		}

		return outDTO;
	}

	public void printTransformedModelSWIProlog(VariabilityModel variabilityModel) {
		Collection<Expression> expressions = ConstraintRepresentationUtil
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
		Collection<Expression> expressions = ConstraintRepresentationUtil
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
