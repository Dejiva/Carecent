package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class MessageAlertDto implements Serializable{
	private Long id;
	private String msgSubj;
	private String msgBody;
	private String messageAlertType;
	public MessageAlertDto()
	{
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMsgSubj() {
		return msgSubj;
	}
	public void setMsgSubj(String msgSubj) {
		this.msgSubj = msgSubj;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getMessageAlertType() {
		return messageAlertType;
	}
	public void setMessageAlertType(String messageAlertType) {
		this.messageAlertType = messageAlertType;
	}
	
}
