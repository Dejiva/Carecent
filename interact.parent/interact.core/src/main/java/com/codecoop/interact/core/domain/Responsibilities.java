package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESPONSIBILITIES", catalog = "INTERACT")
public class Responsibilities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -348004628422928555L;
	private Long id;
	private String responsibilitiesRole;
	//List<MessageEligibilityStaff> messageEligibilityStaffList=new ArrayList<MessageEligibilityStaff>();
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "RESPONSIBILITY_ROLE", length = 50)
	public String getResponsibilitiesRole() {
		return responsibilitiesRole;
	}
	public void setResponsibilitiesRole(String responsibilitiesRole) {
		this.responsibilitiesRole = responsibilitiesRole;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
