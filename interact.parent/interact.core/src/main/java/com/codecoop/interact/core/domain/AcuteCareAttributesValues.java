package com.codecoop.interact.core.domain;

// Generated Jul 26, 2014 9:46:18 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "HOSPITAL_TRANSFER_ATTRIBUTES_VALUES", catalog = "INTERACT")
public class AcuteCareAttributesValues implements java.io.Serializable {
	private static final long serialVersionUID = -5292982990970712290L;
	private Long id;
	private Long pEpisodeId;
	private Long acuteCareAttributeId;
	private String attributeValue;

	public AcuteCareAttributesValues() {
	}

	public AcuteCareAttributesValues(Long id) {
		this.id = id;
	}

	public AcuteCareAttributesValues(Long id, Long pEpisodeId,
			 String attributeValue) {
		this.id = id;
		this.pEpisodeId = pEpisodeId;
		this.attributeValue = attributeValue;
	}

	@Id
	@GenericGenerator(name="idIncrementer" , strategy="increment")
	@GeneratedValue(generator="idIncrementer")
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "P_EPISODE_ID")
	public Long getpEpisodeId() {
		return this.pEpisodeId;
	}

	public void setpEpisodeId(Long pEpisodeId) {
		this.pEpisodeId = pEpisodeId;
	}

	@Column(name = "ATTRIBUTE_VALUE", length = 100)
	public String getAttributeValue() {
		return this.attributeValue;
	}
	
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	@Column(name = "HOSPITAL_TRANSFER_ATTRIBUTE_ID")
	public Long getAcuteCareAttributeId() {
		return acuteCareAttributeId;
	}

	public void setAcuteCareAttributeId(Long acuteCareAttributeId) {
		this.acuteCareAttributeId = acuteCareAttributeId;
	}

}
