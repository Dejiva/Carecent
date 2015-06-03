package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.Date;

public class UtilsDto implements Serializable {
	private static final long serialVersionUID = -3913760740864851351L;
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
