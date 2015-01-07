package com.variamos.defectAnalyzer.defectAnalyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.variamos.defectAnalyzer.model.ClassifiableDiagnosis;
import com.variamos.defectAnalyzer.model.ClassifiedDiagnosis;
import com.variamos.defectAnalyzer.model.Dependency;
import com.variamos.defectAnalyzer.model.Diagnosis;
import com.variamos.defectAnalyzer.model.defects.Defect;
import com.variamos.defectAnalyzer.model.enums.ClassificationType;
import com.variamos.defectAnalyzer.util.SetUtil;

public class VariabilityModelCausesCorrectionsSorter {

	public VariabilityModelCausesCorrectionsSorter() {
		super();
		
	}

	public ClassifiedDiagnosis classifyDiagnosis(
			List<Diagnosis> allDiagnostics,
			ClassificationType classsificationType) {

		// Almacena la colección completa de todos los MUSes o de todos los
		// MCSes según el parámetro de entrada
		List<List<Dependency>> collectionALLDiagnosisElements = new ArrayList<List<Dependency>>();
		Set<List<Dependency>> collectionALLDiagnosisElementsSet = new HashSet<List<Dependency>>();
		List<ClassifiableDiagnosis> commonDiagnosis = new ArrayList<ClassifiableDiagnosis>();
		List<ClassifiableDiagnosis> noCommonDiagnosis = new ArrayList<ClassifiableDiagnosis>();
		

		// Se obtiene la colección de todos los MUSES o de todas las causas
		// según el classificationType
		for (Diagnosis diagnosticElement : allDiagnostics) {
			if (classsificationType.equals(ClassificationType.CAUSES)) {
				collectionALLDiagnosisElements.addAll(diagnosticElement
						.getCauses());
			} else if (classsificationType
					.equals(ClassificationType.CORRECTIONS)) {
				collectionALLDiagnosisElements.addAll(diagnosticElement
						.getCorrectionSubsets());
			}

		}

		
		//Se pasa a Set para eliminar repetidos
		for(List<Dependency> listDependency:collectionALLDiagnosisElements){
			collectionALLDiagnosisElementsSet.add(listDependency);
		}

		// Se eliminan los repetidos que tenían orden distinto y no eran detectados por el equals de la clase Dependency
		collectionALLDiagnosisElements.clear();
		collectionALLDiagnosisElements.addAll(SetUtil.maintainNoEqualsSets(collectionALLDiagnosisElementsSet));
		
		// Se recorre la unión anteriormente obtenida ( es una causa una
		// correción)
		long id=1;
		for (List<Dependency> set : collectionALLDiagnosisElements) {

			List<Defect> defects = searchDiagnosisOnDefects(allDiagnostics,
					set, classsificationType);
			ClassifiableDiagnosis defectsByMCSMUSes = new ClassifiableDiagnosis(set,id,
					defects);
			if (defects != null && defects.size()>1) {
				commonDiagnosis.add(defectsByMCSMUSes);
			} else {
				noCommonDiagnosis.add(defectsByMCSMUSes);
			}
			id++;
		}

		// Se construye el elemento clasificado a retornar
		ClassifiedDiagnosis classifiedDiagnosis = new ClassifiedDiagnosis(
				commonDiagnosis, noCommonDiagnosis);

		return classifiedDiagnosis;
	}

	/**
	 * Busca los defectos para los que se encuentra ese MCS igual
	 * 
	 * @param defectsList
	 * @param MCS
	 * @return
	 */
	private List<Defect> searchDiagnosisOnDefects(
			List<Diagnosis> allDiagnostics, List<Dependency> MCS,
			ClassificationType classsificationType) {

		// Lista de defectos en los que se encuentra el MCS
		List<Defect> defects = new ArrayList<Defect>();
		for (Diagnosis diagnostic : allDiagnostics) {

			if (classsificationType.equals(ClassificationType.CAUSES)) {
				// Se verifica si en la colección de causas de este diagnostico
				// esta ese MCS
				if (SetUtil.verifyExistSetInSetofSets(MCS,
						diagnostic.getCauses())) {
					defects.add(diagnostic.getDefect());
				}
			} else if (classsificationType
					.equals(ClassificationType.CORRECTIONS)) {

				// Se verifica si en la colección de causas de este diagnostico
				// esta ese MCS
				if (SetUtil.verifyExistSetInSetofSets(MCS,
						diagnostic.getCorrectionSubsets())) {
					defects.add(diagnostic.getDefect());

				}

			}

		}
		return defects;
	}
	

}
