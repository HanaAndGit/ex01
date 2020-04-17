package com.yi.domain;

public class PageMaker {
	private int totalCount; //게시글 전체 갯수
	private int startPage; //시작번호
	private int endPage; //끝 번호
	private boolean prev; //이전 여부
	private boolean next; //이후 여부
	private int displayPageNum = 10; //보여지는 페이지 번호의 수
	private Criteria cri;
	
	
	private void calculatorData() {
		//현재 페이지의 끝 번호를 구한다. 
		//ex. 15번 페이지를 선택했을 경우, 마지막 번호가 20이 되어야함
		//15/10=1.5 -> Math.ceil 거쳐서 올림되어 2가됨. -> 2 * displayPageNum(10) = 20
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		//현재 페이지의 시작 번호를 구한다.
		//ex. 20-10 +1 = 11 
		//현재 끝 번호가 20이면 한 페이지에 보여지는 수를 빼고, 1을 더하면 시작 번호. 
		startPage = (endPage - displayPageNum) + 1;
		
		//실제 끝 번호를 구함.
		//ex.전체 게시글 갯수가 153개라면, 153/10 = 15.3 -> Math.ceil 거쳐서 올림되어 16이됨. 
		int tempEndPage = (int)(Math.ceil(totalCount/(double) cri.getPerPage()));
		
		//만약, 실제 끝 번호보다 가상 끝번호가 클 경우 실제 끝 번호로 변경해줌. 
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = (startPage == 1)?false:true;
		next = (endPage * cri.getPerPage() >= totalCount )?false:true;
	}
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//함수 호출
		calculatorData();
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
	
	
}
