package com.codecoop.interact.core.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StopAndWatchHistoryDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1247734949619519138L;
	/**
	 * 
	 */
	private Long id;
	private Long stopAndWatchHistoryId;
	private String residentName;
	private String repotedTo;
	private String repotedBy;
	private List<String> symtomsList=new ArrayList<String>();
	private Date repotedDate;
	private Date responseDate;
	private String repotedDateString;
	private String nurseName;
	private String responseDateString;
	private String role;
	public String getNurseName() {
		return nurseName;
	}
	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}
	public String getResponseDateString() {
		return responseDateString;
	}
	public void setResponseDateString(String responseDateString) {
		this.responseDateString = responseDateString;
	}
	public String getRepotedDateString() {
		return repotedDateString;
	}
	public void setRepotedDateString(String repotedDateString) {
		this.repotedDateString = repotedDateString;
	}
	private String response;
	
	public String getRepotedBy() {
		return repotedBy;
	}
	public void setRepotedBy(String repotedBy) {
		this.repotedBy = repotedBy;
	}
	public List<String> getSymtomsList() {
		return symtomsList;
	}
	public void setSymtomsList(List<String> symtomsList) {
		this.symtomsList = symtomsList;
	}
	public Date getRepotedDate() {
		return repotedDate;
	}
	public void setRepotedDate(Date repotedDate) {
		this.repotedDate = repotedDate;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Long getStopAndWatchHistoryId() {
		return stopAndWatchHistoryId;
	}
	public void setStopAndWatchHistoryId(Long stopAndWatchHistoryId) {
		this.stopAndWatchHistoryId = stopAndWatchHistoryId;
	}
	public String getResidentName() {
		return residentName;
	}
	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}
	public String getRepotedTo() {
		return repotedTo;
	}
	public void setRepotedTo(String repotedTo) {
		this.repotedTo = repotedTo;
	}
	public Date getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
   

}
