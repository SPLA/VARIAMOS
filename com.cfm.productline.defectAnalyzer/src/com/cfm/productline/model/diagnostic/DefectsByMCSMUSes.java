package com.cfm.productline.model.diagnostic;

import java.util.List;

import com.cfm.productline.model.defect.Defect;
import com.cfm.productline.model.defectAnalyzerModel.Dependency;

public class DefectsByMCSMUSes {
	// Puede ser una causa o una corrección según como se use
		private List<Dependency> diagnosticElements;
		private Long id;
		private List<Defect> defectsList;
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
		public List<Defect> getDefectsList() {
			return defectsList;
		}
		/**
		 * @param defectsList the defectsList to set
		 */
		public void setDefectsList(List<Defect> defectsList) {
			this.defectsList = defectsList;
		}
		public DefectsByMCSMUSes(List<Dependency> diagnosticElements,
				List<Defect> defectsList) {
			super();
			this.diagnosticElements = diagnosticElements;
			this.defectsList = defectsList;
		}
		
		
		public DefectsByMCSMUSes(List<Dependency> diagnosticElements, Long id,
				List<Defect> defectsList) {
			super();
			this.diagnosticElements = diagnosticElements;
			this.id = id;
			this.defectsList = defectsList;
		}
		public DefectsByMCSMUSes() {
			super();
			// TODO Auto-generated constructor stub
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "DefectsByMCSMUSes [diagnosticElements="
					+ diagnosticElements + ", defectsList=" + defectsList + "]";
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
