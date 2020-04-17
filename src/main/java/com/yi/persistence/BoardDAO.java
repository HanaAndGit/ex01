package com.yi.persistence;

import java.util.List;

import com.yi.domain.Board;
import com.yi.domain.Criteria;

public interface BoardDAO {
	public void insert(Board vo) throws Exception;
	public Board readByNo(int bno) throws Exception;
	public List<Board> list() throws Exception;
	public void update(Board vo) throws Exception;
	public void delete(int bno) throws Exception;
	public void updateCnt(Board vo) throws Exception;
	public List<Board> listPage(int page) throws Exception;
	public List<Board> listCriteria(Criteria cri) throws Exception;
	public int totalCount() throws Exception;
	
}
