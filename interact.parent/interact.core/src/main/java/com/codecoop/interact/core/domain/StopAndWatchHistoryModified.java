package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "STOP_AND_WATCH_HISTORY_MODIFIED", catalog = "INTERACT")
public class StopAndWatchHistoryModified implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -109806303509613641L;
	/**
	 * 
	 */

	private Long id;
	private StopAndWatchHistory stopAndWatchHistory;
	private String modifiedBy;
	private Date dateModified;
	
	

	public StopAndWatchHistoryModified() {
	}
	
		public StopAndWatchHistoryModified(StopAndWatchHistory stopAndWatchHistory,
			String modifiedBy, Date dateModified) {
		super();
		this.stopAndWatchHistory = stopAndWatchHistory;
		this.modifiedBy = modifiedBy;
		this.dateModified = dateModified;
	}

		@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFIED", length = 19)
	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOP_AND_WATCH_HISTORY_ID")
	public StopAndWatchHistory getStopAndWatchHistory() {
		return stopAndWatchHistory;
	}

	public void setStopAndWatchHistory(StopAndWatchHistory stopAndWatchHistory) {
		this.stopAndWatchHistory = stopAndWatchHistory;
	}

	@Column(name = "MODIFIED_BY", length = 50)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	
	


}
