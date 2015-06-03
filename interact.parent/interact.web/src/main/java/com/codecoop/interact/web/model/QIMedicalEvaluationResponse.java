package com.codecoop.interact.web.model;

import java.util.List;

import com.codecoop.interact.core.dto.QIHospTrnsInfoDto;


public class QIMedicalEvaluationResponse {
	private Long   totalTransfers;
    private List<QIHospTrnsInfoDto> medicalEvaluation;

	public Long getTotalTransfers() {
		return totalTransfers;
	}
	public void setTotalTransfers(Long totalTransfers) {
		this.totalTransfers = totalTransfers;
	}
	public List<QIHospTrnsInfoDto> getMedicalEvaluation() {
		return medicalEvaluation;
	}
	public void setMedicalEvaluation(List<QIHospTrnsInfoDto> medicalEvaluation) {
		this.medicalEvaluation = medicalEvaluation;
	}

}
