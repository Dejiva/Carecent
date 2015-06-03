package com.codecoop.interact.core.dto;

import java.io.Serializable;



public class QIHospTransInfostatisticsTODDto implements Serializable{
	private static final long serialVersionUID = -6117586007752252961L;
	
	private Long morning;
	private Integer morningPstage;
	private Long afterNoon;
	private Integer afterNoonPstage;
	private Long evening;
	private Integer eveningPstage;
	private Long night;
	private Integer nightPstage;
	private Long total;
	
	public Long getMorning() {
		return morning;
	}
	public void setMorning(Long morning) {
		this.morning = morning;
	}
	public Integer getMorningPstage() {
		return morningPstage;
	}
	public void setMorningPstage(Integer morningPstage) {
		this.morningPstage = morningPstage;
	}
	public Long getAfterNoon() {
		return afterNoon;
	}
	public void setAfterNoon(Long afterNoon) {
		this.afterNoon = afterNoon;
	}
	public Integer getAfterNoonPstage() {
		return afterNoonPstage;
	}
	public void setAfterNoonPstage(Integer afterNoonPstage) {
		this.afterNoonPstage = afterNoonPstage;
	}
	public Long getEvening() {
		return evening;
	}
	public void setEvening(Long evening) {
		this.evening = evening;
	}
	public Integer getEveningPstage() {
		return eveningPstage;
	}
	public void setEveningPstage(Integer eveningPstage) {
		this.eveningPstage = eveningPstage;
	}
	public Long getNight() {
		return night;
	}
	public void setNight(Long night) {
		this.night = night;
	}
	public Integer getNightPstage() {
		return nightPstage;
	}
	public void setNightPstage(Integer nightPstage) {
		this.nightPstage = nightPstage;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
}
