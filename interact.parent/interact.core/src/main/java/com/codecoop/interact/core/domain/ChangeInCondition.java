package com.codecoop.interact.core.domain;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name ="CHANGE_IN_CONDITION", catalog = "INTERACT")
public class ChangeInCondition implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3808814937944087589L;
	private Long id;
	private Long sbarId;
	private Long signsSymptomsLabworkAttrId;
	private String changeInConditionValue;
	private Date startedDate;
	private Boolean gottenBetter;
	private Boolean gottenWorse;
	private String thinsMadeSymptomBetter;
	private String thingsMadeSymptomWorse;
	private Boolean occurredBefore;
	private String treatmentForLastEpisode;
	private String comments;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;

	public ChangeInCondition() {
	}

	public ChangeInCondition(Long sbarId,
			Long signsSymptomsLabworkAttrId, String changeInConditionValue,
			Date startedDate, Boolean gottenBetter, Boolean gottenWorse,
			String thinsMadeSymptomBetter, String thingsMadeSymptomWorse,
			Boolean occurredBefore, String treatmentForLastEpisode,
			String comments, Date dateCreated, String userCreated,
			Date dateModified, String userModified) {
		this.sbarId =sbarId;
		this.signsSymptomsLabworkAttrId = signsSymptomsLabworkAttrId;
		this.changeInConditionValue = changeInConditionValue;
		this.startedDate = startedDate;
		this.gottenBetter = gottenBetter;
		this.gottenWorse = gottenWorse;
		this.thinsMadeSymptomBetter = thinsMadeSymptomBetter;
		this.thingsMadeSymptomWorse = thingsMadeSymptomWorse;
		this.occurredBefore = occurredBefore;
		this.treatmentForLastEpisode = treatmentForLastEpisode;
		this.comments = comments;
		this.dateCreated = dateCreated;
		this.userCreated = userCreated;
		this.dateModified = dateModified;
		this.userModified = userModified;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

	@Column(name = "SIGNS_SYMPTOMS_LABWORK_ATTR_ID")
	public Long getSignsSymptomsLabworkAttrId() {
		return this.signsSymptomsLabworkAttrId;
	}
	@Column(name ="SBAR_ID")
	public Long getSbarId() {
		return sbarId;
	}

	public void setSbarId(Long sbarId) {
		this.sbarId = sbarId;
	}

	public void setSignsSymptomsLabworkAttrId(Long signsSymptomsLabworkAttrId) {
		this.signsSymptomsLabworkAttrId = signsSymptomsLabworkAttrId;
	}

	@Column(name = "CHANGE_IN_CONDITION_VALUE", length = 60)
	public String getChangeInConditionValue() {
		return this.changeInConditionValue;
	}

	public void setChangeInConditionValue(String changeInConditionValue) {
		this.changeInConditionValue = changeInConditionValue;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STARTED_DATE", length = 19)
	public Date getStartedDate() {
		return this.startedDate;
	}

	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}

	@Column(name = "GOTTEN_BETTER")
	public Boolean getGottenBetter() {
		return this.gottenBetter;
	}

	public void setGottenBetter(Boolean gottenBetter) {
		this.gottenBetter = gottenBetter;
	}

	@Column(name = "GOTTEN_WORSE")
	public Boolean getGottenWorse() {
		return this.gottenWorse;
	}

	public void setGottenWorse(Boolean gottenWorse) {
		this.gottenWorse = gottenWorse;
	}

	@Column(name = "THINS_MADE_SYMPTOM_BETTER", length = 50)
	public String getThinsMadeSymptomBetter() {
		return this.thinsMadeSymptomBetter;
	}

	public void setThinsMadeSymptomBetter(String thinsMadeSymptomBetter) {
		this.thinsMadeSymptomBetter = thinsMadeSymptomBetter;
	}

	@Column(name = "THINGS_MADE_SYMPTOM_WORSE", length = 50)
	public String getThingsMadeSymptomWorse() {
		return this.thingsMadeSymptomWorse;
	}

	public void setThingsMadeSymptomWorse(String thingsMadeSymptomWorse) {
		this.thingsMadeSymptomWorse = thingsMadeSymptomWorse;
	}

	@Column(name = "OCCURRED_BEFORE")
	public Boolean getOccurredBefore() {
		return this.occurredBefore;
	}

	public void setOccurredBefore(Boolean occurredBefore) {
		this.occurredBefore = occurredBefore;
	}

	@Column(name = "TREATMENT_FOR_LAST_EPISODE", length = 150)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED", length = 19)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "USER_CREATED", length = 50)
	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFIED", length = 19)
	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Column(name = "USER_MODIFIED", length = 50)
	public String getUserModified() {
		return this.userModified;
	}

	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}

	@Override
	public String toString() {
		return "ChangeInCondition [id=" + id + ", patientEpisodeId="
				+ sbarId + ", signsSymptomsLabworkAttrId="
				+ signsSymptomsLabworkAttrId + ", changeInConditionValue="
				+ changeInConditionValue + ", startedDate=" + startedDate
				+ ", gottenBetter=" + gottenBetter + ", gottenWorse="
				+ gottenWorse + ", thinsMadeSymptomBetter="
				+ thinsMadeSymptomBetter + ", thingsMadeSymptomWorse="
				+ thingsMadeSymptomWorse + ", occurredBefore=" + occurredBefore
				+ ", treatmentForLastEpisode=" + treatmentForLastEpisode
				+ ", comments=" + comments + ", dateCreated=" + dateCreated
				+ ", userCreated=" + userCreated + ", dateModified="
				+ dateModified + ", userModified=" + userModified + "]";
	}

	
	

}
