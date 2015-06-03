package com.codecoop.interact.core.dto;

import java.io.Serializable;


public class QIHospTransInfostatisticsDto implements Serializable{
	private static final long serialVersionUID = 9221269105377315848L;
	
	private QIHospTransInfostatisticsTODDto timeOfDayStatistics;
	private QIHospTransInfostatisticsDOWDto dayOfWeekStatistics;
	
	public QIHospTransInfostatisticsTODDto getTimeOfDayStatistics() {
		return timeOfDayStatistics;
	}
	public void setTimeOfDayStatistics(
			QIHospTransInfostatisticsTODDto timeOfDayStatistics) {
		this.timeOfDayStatistics = timeOfDayStatistics;
	}
	public QIHospTransInfostatisticsDOWDto getDayOfWeekStatistics() {
		return dayOfWeekStatistics;
	}
	public void setDayOfWeekStatistics(
			QIHospTransInfostatisticsDOWDto dayOfWeekStatistics) {
		this.dayOfWeekStatistics = dayOfWeekStatistics;
	}
}
