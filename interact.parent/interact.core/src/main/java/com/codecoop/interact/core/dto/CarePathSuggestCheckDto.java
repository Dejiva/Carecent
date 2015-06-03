package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CarePathSuggestCheckDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7033656427571266245L;
	
	private String carePathCode;
	private String carePathXml;
	private Map<String, String> carePathAttrCodeNameMap = new HashMap<String, String>(); 
	
	
	public String getCarePathCode() {
		return carePathCode;
	}
	public void setCarePathCode(String carePathCode) {
		this.carePathCode = carePathCode;
	}
	public String getCarePathXml() {
		return carePathXml;
	}
	public void setCarePathXml(String carePathXml) {
		this.carePathXml = carePathXml;
	}
	public Map<String, String> getCarePathAttrCodeNameMap() {
		return carePathAttrCodeNameMap;
	}
	public void setCarePathAttrCodeNameMap(
			Map<String, String> carePathAttrCodeNameMap) {
		this.carePathAttrCodeNameMap = carePathAttrCodeNameMap;
	}
	
}
