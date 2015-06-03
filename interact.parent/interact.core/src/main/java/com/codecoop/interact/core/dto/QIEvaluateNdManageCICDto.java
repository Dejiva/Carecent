package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class QIEvaluateNdManageCICDto implements Serializable {
	private static final long serialVersionUID = 4950610486483976310L;
	
	private Long nppaCount;
	private Long mdCount;
	private Long telCount;
	private Long totalTransfers;
	public Long getNppaCount() {
		return nppaCount;
	}
	public void setNppaCount(Long nppaCount) {
		this.nppaCount = nppaCount;
	}
	public Long getMdCount() {
		return mdCount;
	}
	public void setMdCount(Long mdCount) {
		this.mdCount = mdCount;
	}
	public Long getTelCount() {
		return telCount;
	}
	public void setTelCount(Long telCount) {
		this.telCount = telCount;
	}
	public Long getTotalTransfers() {
		return totalTransfers;
	}
	public void setTotalTransfers(Long totalTransfers) {
		this.totalTransfers = totalTransfers;
	}

}
