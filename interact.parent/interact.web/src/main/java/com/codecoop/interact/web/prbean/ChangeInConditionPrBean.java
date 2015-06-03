package com.codecoop.interact.web.prbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ChangeInConditionPrBean implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -6694564048080502476L;
	private String symptomName;
	private Long symptomId;
	private List<Attributes> attributes;
	private String startedOnDate;
	private String symptomStatus;
	private String thinsMadeSymptomBetter;
	private String thingsMadeSymptomWorse;
	private Boolean occurredBefore;
	private String treatmentForLastEpisode;
	private String comments;
	private Date dateCreated;
	

	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public String getSymptomName() {
		return symptomName;
	}
	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}
	public List<Attributes> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attributes> attributes) {
		this.attributes = attributes;
	}
	
	public String getSymptomStatus() {
		return symptomStatus;
	}
	public void setSymptomStatus(String symptomStatus) {
		this.symptomStatus = symptomStatus;
	}
	public String getThinsMadeSymptomBetter() {
		return thinsMadeSymptomBetter;
	}
	public void setThinsMadeSymptomBetter(String thinsMadeSymptomBetter) {
		this.thinsMadeSymptomBetter = thinsMadeSymptomBetter;
	}
	public String getThingsMadeSymptomWorse() {
		return thingsMadeSymptomWorse;
	}
	public void setThingsMadeSymptomWorse(String thingsMadeSymptomWorse) {
		this.thingsMadeSymptomWorse = thingsMadeSymptomWorse;
	}
	public Boolean getOccurredBefore() {
		return occurredBefore;
	}
	public void setOccurredBefore(Boolean occurredBefore) {
		this.occurredBefore = occurredBefore;
	}
	public String getTreatmentForLastEpisode() {
		return treatmentForLastEpisode;
	}
	public void setTreatmentForLastEpisode(String treatmentForLastEpisode) {
		this.treatmentForLastEpisode = treatmentForLastEpisode;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void setSymptomId(Long symptomId) {
		this.symptomId = symptomId;
	}
	public Long getSymptomId() {
		return symptomId;
	}
	public void setStartedOnDate(String startedOnDate) {
		this.startedOnDate = startedOnDate;
	}
	public String getStartedOnDate() {
		return startedOnDate;
	}
//	@Override
//	public int compareTo(ChangeInConditionPrBean o) {
//		// TODO Auto-generated method stub
//		 if (getDateCreated() == null || o.getDateCreated() == null)
//		      return 0;
//		    return getDateCreated().compareTo(o.getDateCreated());
//	}
//	
	 
}
