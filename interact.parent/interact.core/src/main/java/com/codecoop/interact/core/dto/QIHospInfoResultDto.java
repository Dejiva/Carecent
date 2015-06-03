package com.codecoop.interact.core.dto;

import java.io.Serializable;



public class QIHospInfoResultDto implements Serializable {
	private static final long serialVersionUID = -9100842596849940447L;
	
	private QIHospTrnsInfoDetailsDto timeOfDay;
	private QIHospTrnsInfoDetailsDto dayOfWeek;
	
	public QIHospTrnsInfoDetailsDto getTimeOfDay() {
		return timeOfDay;
	}
	public void setTimeOfDay(QIHospTrnsInfoDetailsDto timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
	public QIHospTrnsInfoDetailsDto getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(QIHospTrnsInfoDetailsDto dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
}
