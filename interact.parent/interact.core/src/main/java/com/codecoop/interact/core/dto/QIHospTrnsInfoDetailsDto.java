package com.codecoop.interact.core.dto;

import java.util.List;

public class QIHospTrnsInfoDetailsDto {
	
	private Long   total;
	private List<QIHospTrnsInfoDto>  info;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<QIHospTrnsInfoDto> getInfo() {
		return info;
	}
	public void setInfo(List<QIHospTrnsInfoDto> info) {
		this.info = info;
	}
}
