package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.Date;

public class MedicineRecommendationDto implements Serializable{
	
	private Long id;
	private String recommendationType;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRecommendationType() {
		return recommendationType;
	}
	public void setRecommendationType(String recommendationType) {
		this.recommendationType = recommendationType;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getUserCreated() {
		return userCreated;
	}
	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	public String getUserModified() {
		return userModified;
	}
	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}
	

}
