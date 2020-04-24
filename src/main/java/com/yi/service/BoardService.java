package com.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yi.domain.Board;
import com.yi.domain.Criteria;
import com.yi.domain.SearchCriteria;
import com.yi.persistence.BoardDAO;

@Repository
public class BoardService {
	//Dependency Injection 의존적 주입 
	@Autowired
	BoardDAO dao;
	
	public void create(Board vo) throws Exception {
		dao.insert(vo);
	}
	
	public Board readByNo(int bno) throws Exception {
		return dao.readByNo(bno);
	}
	
	public List<Board> list() throws Exception{
		return dao.list();
	}
	
	public void update(Board vo) throws Exception{
		dao.update(vo);
	}
	
	public void delete(int bno) throws Exception{
		dao.delete(bno);
	}
	
	public void updateCnt(Board vo) throws Exception {
		dao.updateCnt(vo);
	}
//	
//	public List<Board> listPage(int page) throws Exception{
//		return dao.listPage(page);
//	}
	public List<Board> listCriteria(Criteria cri) throws Exception{
		return dao.listCriteria(cri);
	}
	
	public int totalCount() throws Exception{
		return dao.totalCount();
	}
	
	public List<Board> listSearchCriteria(SearchCriteria cri) throws Exception{
		return dao.listSearchCriteria(cri);
	}
	
	public int totalSearchCount(SearchCriteria cri) throws Exception{
		return dao.totalSearchCount(cri);
	}
	
	public Board readSearchByNo(int bno) throws Exception {
		return dao.readSearchByNo(bno);
	}
}      
