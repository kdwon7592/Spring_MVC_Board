package com.dowon.myboard.util;

public class PagingList {
	int maxList; //한 페이지에 표시될 게시물 최대 개수
	int firstPage; 
	int prevPage;
	int startPage;
	int currentPage;
	int endPage;
	int nextPage;
	int finalPage; 
	int total_num; //전체 레코드
	int viewPage; // 한번에 보여지는 페이지 개수
	
	public PagingList(int currentPage, int maxList) {
		this.currentPage = currentPage;
		this.viewPage = 5;
		this.maxList = (maxList != 0) ? maxList : 10;
	}
	
	public void makePaging() {
		if(total_num == 0) // 전체 페이지 개수
			return;
		
		if(currentPage == 0) { //현재 있는 페이지
			setCurrentPage(1);
		}
		if(maxList == 0) { // 한페이지에 출력할 페이지 갯수
			setMaxList(10);
		}
		setFinalPage((total_num + (maxList - 1)) / maxList); 
		//마지막 페이지 ex) 전체가 120개이고 한페이지에 10개씩 보인다면 129/10 = 12 
		
		if(currentPage > finalPage) {
			setCurrentPage(finalPage);
		}
		
		if(currentPage < 0){
			currentPage = 1;
		}
		
		boolean isFirst = currentPage == 1? true : false; // 현재 페이지가 첫 페이지 이면 true
		boolean isFinal = currentPage == finalPage ? true : false; //현재 페이지가 마지막 이면 true
		
		setStartPage(((currentPage - 1) / viewPage) * viewPage + 1); 
		//viewPage가 한 번에 보여줄 페이지 번호이기에 ex) 총 페이지가 10 개이고 한번에 4개씩 보여준다면 1~4 이후 5~8로 보여줘야한다. 
		setEndPage(startPage + viewPage - 1); 
		
		if(endPage > finalPage) {
			setEndPage(finalPage);
		}
		
		if(!isFirst) {
			setPrevPage((startPage-1) < 1 ? 1 : (startPage - 1));
			//첫페이지가 아니면 그 전페이지가 무엇인지 알려준다. 
		}
		if(!isFinal) {
			setNextPage(endPage+1 > finalPage ? finalPage : (endPage + 1));
		}
		
	}
	
	@Override
	public String toString() {
		return "PagingList [maxList=" + maxList + ", firstPage=" + firstPage + ", prevPage=" + prevPage + ", startPage="
				+ startPage + ", currentPage=" + currentPage + ", endPage=" + endPage + ", nextPage=" + nextPage
				+ ", finalPage=" + finalPage + ", total_num=" + total_num + ", viewPage=" + viewPage + "]";
	}

	public int getMaxList() {
		return maxList;
	}
	public void setMaxList(int maxPost) {
		this.maxList = maxPost;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getFinalPage() {
		return finalPage;
	}
	public void setFinalPage(int finalPage) {
		this.finalPage = finalPage;
	}
	public int getTotal_num() {
		return total_num;
	}
	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}
	public int getViewPage() {
		return viewPage;
	}
	public void setViewPage(int viewPage) {
		this.viewPage = viewPage;
	}
}
