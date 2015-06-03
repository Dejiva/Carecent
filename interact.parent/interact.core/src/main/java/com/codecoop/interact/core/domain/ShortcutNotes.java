package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHORTCUT_NOTES", catalog = "INTERACT")

public class ShortcutNotes implements java.io.Serializable{
	private Long id;
	private String shortNotes;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name ="SHORT_NOTES",length=5000)
	public String getShortNotes() {
		return shortNotes;
	}
	public void setShortNotes(String shortNotes) {
		this.shortNotes = shortNotes;
	}
	

}
