package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class ContactDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5619373235632511291L;
	
	private String contactType;
	private String contact;
	private boolean primaryContact;
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public boolean isPrimaryContact() {
		return primaryContact;
	}
	public void setPrimaryContact(boolean primaryContact) {
		this.primaryContact = primaryContact;
	}
		
}
