package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class RunningCarePathAttrDetails implements Serializable{

	private static final long serialVersionUID = 9060857891951518310L;
	
	private String carePathCode;
	private String carePathName;
	private String xmlFileLoacation;
	private String carePathAttrCode;
	private String carePathAttrName;
	public String getCarePathCode() {
		return carePathCode;
	}
	public void setCarePathCode(String carePathCode) {
		this.carePathCode = carePathCode;
	}
	public String getXmlFileLoacation() {
		return xmlFileLoacation;
	}
	public String getCarePathName() {
		return carePathName;
	}
	public void setCarePathName(String carePathName) {
		this.carePathName = carePathName;
	}
	public void setXmlFileLoacation(String xmlFileLoacation) {
		this.xmlFileLoacation = xmlFileLoacation;
	}
	public String getCarePathAttrCode() {
		return carePathAttrCode;
	}
	public void setCarePathAttrCode(String carePathAttrCode) {
		this.carePathAttrCode = carePathAttrCode;
	}
	public String getCarePathAttrName() {
		return carePathAttrName;
	}
	public void setCarePathAttrName(String carePathAttrName) {
		this.carePathAttrName = carePathAttrName;
	}

}
