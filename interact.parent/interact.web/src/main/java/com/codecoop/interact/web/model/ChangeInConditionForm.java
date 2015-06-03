package com.codecoop.interact.web.model;

import java.io.Serializable;

/**
 * 
 * @author jgumparthi
 *
 */
public class ChangeInConditionForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3949031803090865272L;
	private String symptomValues48;
	
	public void setSymptomValues48(String symptomValues48) {
		this.symptomValues48 = symptomValues48;
	}
	public String getSymptomValues48() {
		return symptomValues48;
	}
	@Override
	public String toString() {
		return "ChangeInConditionForm [symptomValues48=" + symptomValues48
				+ "]";
	}
	
}
