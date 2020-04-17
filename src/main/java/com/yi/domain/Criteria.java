package com.yi.domain;

public class Criteria {
	private int page; //현재 페이지 번호
	private int perPageNum;//한 페이지 display 될 게시글 갯수
	
	
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	
	public Criteria(int page, int perPage) {
		super();
		this.page = page;
		this.perPageNum = perPage;
	}


	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPerPage() {
		return perPageNum;
	}
	public void setPerPage(int perPage) {
		this.perPageNum = perPage;
	}
	
	public int getPageStart() {//db 시작 게시글 index 번호 구하는 함수
		return (this.page - 1) * perPageNum;
	}
	
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPage=" + perPageNum + "]";
	}
	
	
	
}
