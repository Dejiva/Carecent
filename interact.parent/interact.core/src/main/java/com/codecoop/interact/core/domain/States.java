package com.codecoop.interact.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATES", catalog = "INTERACT")
public class States implements java.io.Serializable {

	private long id;
	private String state;

	public States() {
	}

	public States(long id) {
		this.id = id;
	}

	public States(long id, String state) {
		this.id = id;
		this.state = state;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "state", length = 30)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
