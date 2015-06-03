package com.codecoop.interact.core.dto;


import java.util.Date;

import javax.persistence.Column;

import org.springframework.stereotype.Component;

/**
 * 
 * @author jgumparthi
 *
 */
@Component
public class ChangeInConditionDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2572318027974260580L;
	/**
	 * 
	 */
	private Long id;
	private Long patientEpisodeId;
	private Long signsSymptomsLabworkAttrId;
	private String changeInConditionValue;
	private Date symptomOccuredDate;
	private String docterNotification;
	private String symptomStatus;
	private String thinsMadeSymptomBetter;
	private String thingsMadeSymptomWorse;
	private Boolean occurredBefore;
	private String treatmentForLastEpisode;
	private String comments;
	private Date dateCreated;
	private Long sbarId;

	public String getDocterNotification() {
		return docterNotification;
	}

	public void setDocterNotification(String docterNotification) {
		this.docterNotification = docterNotification;
	}

	public ChangeInConditionDto() {
	}

	public ChangeInConditionDto(Long patientEpisodeId,
			Long signsSymptomsLabworkAttrId, String changeInConditionValue,
			Boolean gottenBetter, Boolean gottenWorse,
			String thinsMadeSymptomBetter, String thingsMadeSymptomWorse,
			Boolean occurredBefore, String treatmentForLastEpisode,
			String comments) {
		this.patientEpisodeId = patientEpisodeId;
		this.signsSymptomsLabworkAttrId = signsSymptomsLabworkAttrId;
		this.changeInConditionValue = changeInConditionValue;
		this.thinsMadeSymptomBetter = thinsMadeSymptomBetter;
		this.thingsMadeSymptomWorse = thingsMadeSymptomWorse;
		this.occurredBefore = occurredBefore;
		this.treatmentForLastEpisode = treatmentForLastEpisode;
		this.comments = comments;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatientEpisodeId() {
		return this.patientEpisodeId;
	}

	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}

	public Long getSignsSymptomsLabworkAttrId() {
		return this.signsSymptomsLabworkAttrId;
	}

	public void setSignsSymptomsLabworkAttrId(Long signsSymptomsLabworkAttrId) {
		this.signsSymptomsLabworkAttrId = signsSymptomsLabworkAttrId;
	}

	public String getChangeInConditionValue() {
		return this.changeInConditionValue;
	}

	public void setChangeInConditionValue(String changeInConditionValue) {
		this.changeInConditionValue = changeInConditionValue;
	}

	public String getThinsMadeSymptomBetter() {
		return this.thinsMadeSymptomBetter;
	}

	public void setThinsMadeSymptomBetter(String thinsMadeSymptomBetter) {
		this.thinsMadeSymptomBetter = thinsMadeSymptomBetter;
	}

	public String getThingsMadeSymptomWorse() {
		return this.thingsMadeSymptomWorse;
	}

	public void setThingsMadeSymptomWorse(String thingsMadeSymptomWorse) {
		this.thingsMadeSymptomWorse = thingsMadeSymptomWorse;
	}

	public Boolean getOccurredBefore() {
		return this.occurredBefore;
	}

	public void setOccurredBefore(Boolean occurredBefore) {
		this.occurredBefore = occurredBefore;
	}

	public String getTreatmentForLastEpisode() {
		return this.treatmentForLastEpisode;
	}

	public void setTreatmentForLastEpisode(String treatmentForLastEpisode) {
		this.treatmentForLastEpisode = treatmentForLastEpisode;
	}

	@Column(name = "COMMENTS", length = 150)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setSymptomStatus(String symptomStatus) {
		this.symptomStatus = symptomStatus;
	}

	public String getSymptomStatus() {
		return symptomStatus;
	}

	public void setSymptomOccuredDate(Date symptomOccuredDate) {
		this.symptomOccuredDate = symptomOccuredDate;
	}

	public Date getSymptomOccuredDate() {
		return symptomOccuredDate;
	}

	public Long getSbarId() {
		return sbarId;
	}

	public void setSbarId(Long sbarId) {
		this.sbarId = sbarId;
	}

}
