package com.codecoop.interact.web.model;

import java.io.Serializable;

public class IdValueBean implements Serializable {

	private static final long serialVersionUID = 267161540096197882L;
	private Long id;
	private String value;
	public IdValueBean(){
		
	}
	public IdValueBean(Long id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
