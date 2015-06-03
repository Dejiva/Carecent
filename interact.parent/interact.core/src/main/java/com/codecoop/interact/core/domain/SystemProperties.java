package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "SYSTEM_PROPERTIES", catalog = "INTERACT")
public class SystemProperties implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6270403868762917734L;
	/**
	 * 
	 */

	
	private Long id;
	private String propertyName;
	private String propertyValue;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROPERTY_NAME", length = 100)
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Column(name = "PROPERTY_VALUE", length = 100)
	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

}
