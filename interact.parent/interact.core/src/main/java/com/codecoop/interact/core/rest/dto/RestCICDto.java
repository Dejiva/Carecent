package com.codecoop.interact.core.rest.dto;

import java.io.Serializable;

public class RestCICDto implements Serializable {
	private static final long serialVersionUID = -9116645865663177699L;
	private Long id;
	private Long sslAttrId;
	private String cicVal;
	private String sslAttrName;
	private String units;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSslAttrId() {
		return sslAttrId;
	}
	public void setSslAttrId(Long sslAttrId) {
		this.sslAttrId = sslAttrId;
	}
	public String getCicVal() {
		return cicVal;
	}
	public void setCicVal(String cicVal) {
		this.cicVal = cicVal;
	}
	public String getSslAttrName() {
		return sslAttrName;
	}
	public void setSslAttrName(String sslAttrName) {
		this.sslAttrName = sslAttrName;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	
	
	

}
