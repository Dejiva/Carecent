package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.List;

public class PaginationDto implements Serializable {
	private int noOfpages;
	private int currentPage;
	private List<MessageDto> messageDtoList;
	public PaginationDto()
	{
		
	}
	public int getNoOfpages() {
		return noOfpages;
	}
	public void setNoOfpages(int noOfpages) {
		this.noOfpages = noOfpages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<MessageDto> getMessageDtoList() {
		return messageDtoList;
	}
	public void setMessageDtoList(List<MessageDto> messageDtoList) {
		this.messageDtoList = messageDtoList;
	}
	

}
