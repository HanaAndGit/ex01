package com.yi.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yi.domain.Board;
import com.yi.domain.Criteria;
import com.yi.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	private static final String namespace = "mappers.BoardMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(Board vo) throws Exception {
		sqlSession.insert(namespace + "insert", vo);

	}

	@Override
	public Board readByNo(int bno) throws Exception {
		return sqlSession.selectOne(namespace + "selectByNo", bno);
	}

	@Override
	public List<Board> list() throws Exception {
		return sqlSession.selectList(namespace + "list");
	}

	@Override
	public void update(Board vo) throws Exception {
		sqlSession.update(namespace + "update", vo);

	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete(namespace + "delete", bno);

	}

	@Override
	public void updateCnt(Board vo) throws Exception {
		sqlSession.update(namespace + "updateCnt", vo);
	}

	@Override
	public List<Board> listPage(int page) throws Exception {
		//1번째 페이지 -> 0부터 2번째 페이지 -> 10부터
		if(page < 0) { 
			page = 1;
		}
		page = (page-1)*10;
		
		return sqlSession.selectList(namespace + "listPage", page);
	}

	@Override
	public List<Board> listCriteria(Criteria cri) throws Exception {
		return sqlSession.selectList(namespace + "listCriteria", cri);
	}

	@Override
	public int totalCount() throws Exception {
		return sqlSession.selectOne(namespace + "totalCount");
	}

	@Override
	public List<Board> listSearchCriteria(SearchCriteria cri) throws Exception {
		return sqlSession.selectList(namespace + "listSearchCriteria", cri);  
	}

	@Override
	public int totalSearchCount(SearchCriteria cri) throws Exception {
		return sqlSession.selectOne(namespace + "totalSearchCount", cri);
	}

	@Override
	public Board readSearchByNo(int bno) throws Exception {
		return sqlSession.selectOne(namespace + "selectSearchByNo", bno);
	}
	
	

}
