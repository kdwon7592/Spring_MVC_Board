package com.dowon.myboard.dto;

import java.sql.Timestamp;

public class ReplyDTO {
	int rId; // primary key
	int bId; // foreign key
	String rName;
	String rComment;
	Timestamp rDate;
	
	public ReplyDTO() {

	}
	
	public ReplyDTO(int rId, int bId, String rName, String rComment, Timestamp rDate)
	{
		this.rId = rId;
		this.bId = bId;
		this.rName = rName;
		this.rComment = rComment;
		this.rDate = rDate;
	}
	
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrComment() {
		return rComment;
	}
	public void setrComment(String rComment) {
		this.rComment = rComment;
	}
	public Timestamp getrDate() {
		return rDate;
	}
	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}
}
