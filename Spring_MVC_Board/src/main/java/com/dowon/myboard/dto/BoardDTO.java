package com.dowon.myboard.dto;

import java.sql.Timestamp;

/*
 * DB의 테이블 정보를 담는 객체
 */

public class BoardDTO {
	int bId; // Board Number (PK)
	String bName; // User ID (20)
	String bTitle; // Board Title (100)
	String bContent; // Board Contect(500);
	int bHit; // Hit Number
	Timestamp bDate; // Current Time

	public BoardDTO() {

	}

	public BoardDTO(int bId, String bName, String bTitle, String bContent, int bHit, Timestamp bDate) {
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bHit = bHit;
		this.bDate = bDate;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public Timestamp getbDate() {
		return bDate;
	}

	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}

	@Override
	public String toString() {
		return "bId : " + this.bId + "bName : " + this.bName + "bTitle : " + this.bTitle + "bContent : " + this.bContent
				+ "bHit : " + this.bHit + "bDate : " + this.bDate;
	}
}
