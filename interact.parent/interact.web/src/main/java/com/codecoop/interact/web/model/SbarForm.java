package com.codecoop.interact.web.model;

import java.util.List;

import com.codecoop.interact.core.dto.SbarSymtomDto;


public class SbarForm {
	private Long sbarPatientEpisodeId;
	private Long nursingStaffId;
	private String repotedToDoctor;
	private String otherPertinentHistory;
	private String primaryDiagnoses;
	private Boolean medAlertChangeLastWeek;
	private String allergies;
	private Boolean medAlertInWarfarin;
	private String resultOfLastInr;
	private String latestInrDate;
	private Boolean onO2Flag;
	private Integer o2LitersPerMinute;
	private Boolean advCarePlanDNRFlag;
	private Boolean advCarePlanDNIFlag;
	private Boolean advCarePlanDNHflag;
	private String gaurdianName;
	private Boolean advCarePlanNoEtrnalFeedFlag;
	private String otherOrderOrWill;
	private String otherPreferences;
	private String rnComment;
	private String lpnComment;
	private Boolean monitorVitalSignsFlag;
	private String medicationAlertsDate;
	private Boolean labWorkFlag;
	private Boolean xRayFlag;
	private Boolean ekg;
	private Boolean providerVisitFlag;
	private Boolean transferToHospitalFlag;
	private Boolean otherNewOrdersFlag;
	private String otherNewOrders;
	private String repotedToDate;
	private Boolean oximetryOnRoomair;
	private String oximetryOnO2; 
	private String otherResidentCare;
	private String repotedToFamily;
	private Boolean manageInFacilityFlag;
	private List<SbarSymtomDto> sbarSymtomList;
	
	public List<SbarSymtomDto> getSbarSymtomList() {
		return sbarSymtomList;
	}
	public void setSbarSymtomList(List<SbarSymtomDto> sbarSymtomList) {
		this.sbarSymtomList = sbarSymtomList;
	}
	public String getOtherResidentCare() {
		return otherResidentCare;
	}
	public void setOtherResidentCare(String otherResidentCare) {
		this.otherResidentCare = otherResidentCare;
	}
	public String getMedicationAlertsDate() {
		return medicationAlertsDate;
	}
	public void setMedicationAlertsDate(String medicationAlertsDate) {
		this.medicationAlertsDate = medicationAlertsDate;
	}
	public String getOtherPertinentHistory() {
		return otherPertinentHistory;
	}
	public void setOtherPertinentHistory(String otherPertinentHistory) {
		this.otherPertinentHistory = otherPertinentHistory;
	}
	public Long getSbarPatientEpisodeId() {
		return sbarPatientEpisodeId;
	}
	public void setSbarPatientEpisodeId(Long sbarPatientEpisodeId) {
		this.sbarPatientEpisodeId = sbarPatientEpisodeId;
	}
	public Long getNursingStaffId() {
		return nursingStaffId;
	}
	public void setNursingStaffId(Long nursingStaffId) {
		this.nursingStaffId = nursingStaffId;
	}


	public String getRepotedToDoctor() {
		return repotedToDoctor;
	}
	public void setRepotedToDoctor(String repotedToDoctor) {
		this.repotedToDoctor = repotedToDoctor;
	}
	public String getPrimaryDiagnoses() {
		return primaryDiagnoses;
	}
	public void setPrimaryDiagnoses(String primaryDiagnoses) {
		this.primaryDiagnoses = primaryDiagnoses;
	}
	public Boolean getMedAlertChangeLastWeek() {
		return medAlertChangeLastWeek;
	}
	public void setMedAlertChangeLastWeek(Boolean medAlertChangeLastWeek) {
		this.medAlertChangeLastWeek = medAlertChangeLastWeek;
	}
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	public Boolean getMedAlertInWarfarin() {
		return medAlertInWarfarin;
	}
	public void setMedAlertInWarfarin(Boolean medAlertInWarfarin) {
		this.medAlertInWarfarin = medAlertInWarfarin;
	}
	public String getResultOfLastInr() {
		return resultOfLastInr;
	}
	public void setResultOfLastInr(String resultOfLastInr) {
		this.resultOfLastInr = resultOfLastInr;
	}
	public String getLatestInrDate() {
		return latestInrDate;
	}
	public void setLatestInrDate(String latestInrDate) {
		this.latestInrDate = latestInrDate;
	}
	public Boolean getOnO2Flag() {
		return onO2Flag;
	}
	public void setOnO2Flag(Boolean onO2Flag) {
		this.onO2Flag = onO2Flag;
	}
	public Integer getO2LitersPerMinute() {
		return o2LitersPerMinute;
	}
	public void setO2LitersPerMinute(Integer o2LitersPerMinute) {
		this.o2LitersPerMinute = o2LitersPerMinute;
	}
	public Boolean getAdvCarePlanDNRFlag() {
		return advCarePlanDNRFlag;
	}
	public void setAdvCarePlanDNRFlag(Boolean advCarePlanDNRFlag) {
		this.advCarePlanDNRFlag = advCarePlanDNRFlag;
	}
	public Boolean getAdvCarePlanDNIFlag() {
		return advCarePlanDNIFlag;
	}
	public void setAdvCarePlanDNIFlag(Boolean advCarePlanDNIFlag) {
		this.advCarePlanDNIFlag = advCarePlanDNIFlag;
	}
	public Boolean getAdvCarePlanDNHflag() {
		return advCarePlanDNHflag;
	}
	public void setAdvCarePlanDNHflag(Boolean advCarePlanDNHflag) {
		this.advCarePlanDNHflag = advCarePlanDNHflag;
	}
	public String getGaurdianName() {
		return gaurdianName;
	}
	public void setGaurdianName(String gaurdianName) {
		this.gaurdianName = gaurdianName;
	}
	public Boolean getAdvCarePlanNoEtrnalFeedFlag() {
		return advCarePlanNoEtrnalFeedFlag;
	}
	public void setAdvCarePlanNoEtrnalFeedFlag(Boolean advCarePlanNoEtrnalFeedFlag) {
		this.advCarePlanNoEtrnalFeedFlag = advCarePlanNoEtrnalFeedFlag;
	}
	public String getOtherOrderOrWill() {
		return otherOrderOrWill;
	}
	public void setOtherOrderOrWill(String otherOrderOrWill) {
		this.otherOrderOrWill = otherOrderOrWill;
	}
	public String getOtherPreferences() {
		return otherPreferences;
	}
	public void setOtherPreferences(String otherPreferences) {
		this.otherPreferences = otherPreferences;
	}
	public String getRnComment() {
		return rnComment;
	}
	public void setRnComment(String rnComment) {
		this.rnComment = rnComment;
	}
	public String getLpnComment() {
		return lpnComment;
	}
	public void setLpnComment(String lpnComment) {
		this.lpnComment = lpnComment;
	}
	public Boolean getMonitorVitalSignsFlag() {
		return monitorVitalSignsFlag;
	}
	public void setMonitorVitalSignsFlag(Boolean monitorVitalSignsFlag) {
		this.monitorVitalSignsFlag = monitorVitalSignsFlag;
	}
	public Boolean getLabWorkFlag() {
		return labWorkFlag;
	}
	public void setLabWorkFlag(Boolean labWorkFlag) {
		this.labWorkFlag = labWorkFlag;
	}
	public Boolean getxRayFlag() {
		return xRayFlag;
	}
	public void setxRayFlag(Boolean xRayFlag) {
		this.xRayFlag = xRayFlag;
	}
	public Boolean getEkg() {
		return ekg;
	}
	public void setEkg(Boolean ekg) {
		this.ekg = ekg;
	}
	public Boolean getProviderVisitFlag() {
		return providerVisitFlag;
	}
	public void setProviderVisitFlag(Boolean providerVisitFlag) {
		this.providerVisitFlag = providerVisitFlag;
	}
	public Boolean getTransferToHospitalFlag() {
		return transferToHospitalFlag;
	}
	public void setTransferToHospitalFlag(Boolean transferToHospitalFlag) {
		this.transferToHospitalFlag = transferToHospitalFlag;
	}
	public Boolean getOtherNewOrdersFlag() {
		return otherNewOrdersFlag;
	}
	public void setOtherNewOrdersFlag(Boolean otherNewOrdersFlag) {
		this.otherNewOrdersFlag = otherNewOrdersFlag;
	}
	
	public String getRepotedToDate() {
		return repotedToDate;
	}
	public void setRepotedToDate(String repotedToDate) {
		this.repotedToDate = repotedToDate;
	}
	public String getOtherNewOrders() {
		return otherNewOrders;
	}
	public void setOtherNewOrders(String otherNewOrders) {
		this.otherNewOrders = otherNewOrders;
	}
	public Boolean getOximetryOnRoomair() {
		return oximetryOnRoomair;
	}
	public void setOximetryOnRoomair(Boolean oximetryOnRoomair) {
		this.oximetryOnRoomair = oximetryOnRoomair;
	}
	public String getOximetryOnO2() {
		return oximetryOnO2;
	}
	public void setOximetryOnO2(String oximetryOnO2) {
		this.oximetryOnO2 = oximetryOnO2;
	}
	public String getRepotedToFamily() {
		return repotedToFamily;
	}
	public void setRepotedToFamily(String repotedToFamily) {
		this.repotedToFamily = repotedToFamily;
	}
	public Boolean getManageInFacilityFlag() {
		return manageInFacilityFlag;
	}
	public void setManageInFacilityFlag(Boolean manageInFacilityFlag) {
		this.manageInFacilityFlag = manageInFacilityFlag;
	}
	
		
	
}
