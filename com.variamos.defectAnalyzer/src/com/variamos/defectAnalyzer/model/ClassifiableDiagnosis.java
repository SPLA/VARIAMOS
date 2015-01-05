package com.variamos.defectAnalyzer.model;

import java.util.List;

import com.variamos.defectAnalyzer.model.defects.Defect;

public class ClassifiableDiagnosis {
	// Puede ser una causa o una corrección según como se use
		private List<Dependency> diagnosticElements;
		private Long id;
		private List<Defect> defects;
		/**
		 * @return the diagnosticElements
		 */
		public List<Dependency> getDiagnosticElements() {
			return diagnosticElements;
		}
		/**
		 * @param diagnosticElements the diagnosticElements to set
		 */
		public void setDiagnosticElements(List<Dependency> diagnosticElements) {
			this.diagnosticElements = diagnosticElements;
		}
		/**
		 * @return the defectsList
		 */
		public List<Defect> getDefects() {
			return defects;
		}
		/**
		 * @param defects the defectsList to set
		 */
		public void setDefects(List<Defect> defects) {
			this.defects = defects;
		}
		public ClassifiableDiagnosis(List<Dependency> diagnosticElements,
				List<Defect> defectsList) {
			super();
			this.diagnosticElements = diagnosticElements;
			this.defects = defectsList;
		}
		
		
		public ClassifiableDiagnosis(List<Dependency> diagnosticElements, Long id,
				List<Defect> defectsList) {
			super();
			this.diagnosticElements = diagnosticElements;
			this.id = id;
			this.defects = defectsList;
		}
		public ClassifiableDiagnosis() {
			super();
			// TODO Auto-generated constructor stub
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "DefectsByMCSMUSes [diagnosticElements="
					+ diagnosticElements + ", defectsList=" + defects + "]";
		}
		/**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}
		
		
}
