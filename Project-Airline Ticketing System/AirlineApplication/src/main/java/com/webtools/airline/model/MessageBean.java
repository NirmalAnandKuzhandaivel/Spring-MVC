package com.webtools.airline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Messages")
public class MessageBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MESSAGE_ID")
	private int messageID;
	
	@Column(name="REFERENCE_ID")
	private long referenceID;
	
	@Column(name="user_email")
	private String userEmail;
	
	@Column(name="message_Header")
	private String messageHeader;
	
	@Column(name="message_Text")
	private String messageText;
	
	@Column(name="message_Type")
	private String messageType;
	
    @Column(name="response_status")
	private int status;
    
	public int getMessageID() {
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageHeader() {
		return messageHeader;
	}
	public void setMessageHeader(String messageHeader) {
		this.messageHeader = messageHeader;
	}
	public long getReferenceID() {
		return referenceID;
	}
	public void setReferenceID(long referenceID) {
		this.referenceID = referenceID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
    
	
}
