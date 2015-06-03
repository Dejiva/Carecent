package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name ="CARE_PATH_TRACKER", catalog = "INTERACT")
public class CarePathTracker implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1011774455570990794L;
	private Long id;
	private Sbar sbar;
	private CarePath carePath;
	private Integer stepId;
	private String stepName;
	private String stepType;
	private Boolean showInSbar;
	private Integer exceSequence;
    private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	
	private List<CarePathTrackerAttr> carePathTrackerAttrList=new ArrayList<CarePathTrackerAttr>();

	public CarePathTracker() {
	}
	
	public CarePathTracker(Sbar sbar,
			CarePath carePath, Integer stepId,  Date dateCreated, String userCreated,
			Date dateModified, String userModified) {
		super();
		this.sbar = sbar;
		this.carePath = carePath;
		this.stepId = stepId;
		this.dateCreated = dateCreated;
		this.userCreated = userCreated;
		this.dateModified = dateModified;
		this.userModified = userModified;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "carePathTracker", cascade={CascadeType.ALL})
	public List<CarePathTrackerAttr> getCarePathTrackerAttrList() {
		return carePathTrackerAttrList;
	}

	public void setCarePathTrackerAttrList(
			List<CarePathTrackerAttr> carePathTrackerAttrList) {
		this.carePathTrackerAttrList = carePathTrackerAttrList;
	}
	@Column(name = "STEP_NAME", length = 100)
	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	@Column(name = "STEP_TYPE", length = 100)
	public String getStepType() {
		return stepType;
	}

	public void setStepType(String stepType) {
		this.stepType = stepType;
	}
	@Column(name = "SHOW_IN_SBAR")
	public Boolean getShowInSbar() {
		return showInSbar;
	}

	public void setShowInSbar(Boolean showInSbar) {
		this.showInSbar = showInSbar;
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
    

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="SBAR_ID")
	public Sbar getSbar() {
		return sbar;
	}

	public void setSbar(Sbar sbar) {
		this.sbar = sbar;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CARE_PATH_ID")
	public CarePath getCarePath() {
		return carePath;
	}

	public void setCarePath(CarePath carePath) {
		this.carePath = carePath;
	}
	@Column(name = "EXCE_SEQUENCE")
	public Integer getExceSequence() {
		return exceSequence;
	}

	public void setExceSequence(Integer exceSequence) {
		this.exceSequence = exceSequence;
	}
	@Column(name = "STEP_ID")
	public Integer getStepId() {
		return stepId;
	}
	public void setStepId(Integer stepId) {
		this.stepId = stepId;
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


}
