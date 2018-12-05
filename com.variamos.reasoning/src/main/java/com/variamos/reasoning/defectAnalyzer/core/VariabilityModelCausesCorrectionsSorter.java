package com.variamos.reasoning.defectAnalyzer.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.variamos.common.core.utilities.SetUtil;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.CauCos;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.ClassifiableDiagnosis;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.ClassificationTypeEnum;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.ClassifiedElement;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.Diagnosis;

public class VariabilityModelCausesCorrectionsSorter {

	public VariabilityModelCausesCorrectionsSorter() {
		super();

	}

	public ClassifiedElement classifyDiagnosis(List<Diagnosis> allDiagnoses,
			ClassificationTypeEnum classsificationType) {

		// Almacena la colección completa de todos los MUSes o de todos los
		// MCSes según el parámetro de entrada
		List<CauCos> cauCosAllElements = new ArrayList<CauCos>();
		Set<CauCos> caucosAllElementsSet = new HashSet<CauCos>();
		List<ClassifiableDiagnosis> commonDiagnosis = new ArrayList<ClassifiableDiagnosis>();
		List<ClassifiableDiagnosis> noCommonDiagnosis = new ArrayList<ClassifiableDiagnosis>();

		// Se obtiene la colección de todos las causas o correcciones
		// según el classificationType
		for (Diagnosis diagnosis : allDiagnoses) {
			if (classsificationType.equals(ClassificationTypeEnum.CAUSES)) {
				cauCosAllElements.addAll(diagnosis.getCauses());
			} else if (classsificationType
					.equals(ClassificationTypeEnum.CORRECTIONS)) {
				cauCosAllElements.addAll(diagnosis.getCorrections());
			}

		}

		// Se pasa a Set para eliminar repetidos
		for (CauCos cauCos : cauCosAllElements) {
			caucosAllElementsSet.add(cauCos);
		}
		int setSize=caucosAllElementsSet.size();
		// Se eliminan los repetidos que tenían orden distinto y no eran
		// detectados por el equals de la clase CauCos
		List<List<IntBooleanExpression>> cauCosExpressionsList = new ArrayList<List<IntBooleanExpression>>();
		for (CauCos cauCos : caucosAllElementsSet) {
			cauCosExpressionsList.add(cauCos.getElements());
		}
		
	
		SetUtil.maintainNoSupersets(cauCosExpressionsList);
		//FIXME terminar el filtro para que no tenga valores repetidos

		// Se recorre la unión anteriormente obtenida ( es una causa una
		// correción)
		long id = 1;
		for (CauCos cauCos : cauCosAllElements) {

			List<Defect> defects = searchDiagnosisByDefects(allDiagnoses,
					cauCos, classsificationType);
			ClassifiableDiagnosis defectsByMCSMUSes = new ClassifiableDiagnosis(
					cauCos, id, defects);
			if (defects != null && defects.size() > 1) {
				commonDiagnosis.add(defectsByMCSMUSes);
			} else {
				noCommonDiagnosis.add(defectsByMCSMUSes);
			}
			id++;
		}

		// Se construye el elemento clasificado a retornar
		ClassifiedElement classifiedDiagnosis = new ClassifiedElement(
				commonDiagnosis, noCommonDiagnosis);

		return classifiedDiagnosis;
	}

	/**
	 * Busca los defectos para los que se encuentra ese MCS igual
	 * 
	 * @param defectsList
	 * @param cauCos
	 * @return
	 */
	private List<Defect> searchDiagnosisByDefects(List<Diagnosis> allDiagnoses,
			CauCos cauCos, ClassificationTypeEnum classsificationType) {

		// Lista de defectos en los que se encuentra la causa o corrección
		List<Defect> defects = new ArrayList<Defect>();
		for (Diagnosis diagnosis : allDiagnoses) {

			if (classsificationType.equals(ClassificationTypeEnum.CAUSES)) {
				// Se verifica si en la colección de causas de este diagnostico
				// esta la causa o correccion a revisar
				List<CauCos> causes = diagnosis.getCauses();
				if (causes.contains(cauCos)) {
					defects.add(diagnosis.getDefect());
				}
			} else if (classsificationType
					.equals(ClassificationTypeEnum.CORRECTIONS)) {

				// Se verifica si en la colección de correcciones de este
				// diagnostico
				// esta ese conjunto
				List<CauCos> corrections = diagnosis.getCorrections();
				if (corrections.contains(cauCos)) {
					defects.add(diagnosis.getDefect());

				}

			}

		}
		return defects;
	}

}
