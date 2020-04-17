package com.yi.ex01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yi.domain.Board;
import com.yi.domain.Criteria;
import com.yi.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDAOTest {
	
	@Autowired
	private BoardDAO dao;
	
	@Test
	public void testDAO() {
		System.out.println(dao);
	}
	
	@Test
	public void testInsert() throws Exception {
		Board vo = new Board();
		vo.setTitle("게시글을 등록합니다.");
		vo.setContent("게시글의 내용입니다.");
		vo.setWriter("user00");
		dao.insert(vo);
	}
	
	@Test
	public void testSelectByNo() throws Exception{
		int bno = 1;
		System.out.println(dao.readByNo(bno));
	}
	
	@Test
	public void testList() throws Exception{
		for(Board b : dao.list()) {
			System.out.println(b);
		}
	}
	
	@Test
	public void testUpdate() throws Exception{
		Board vo = new Board(1, "변경된 제목", "변경된 내용", "user11");
		System.out.println("변경 전 :" + vo);
		dao.update(vo);
		System.out.println("변경 후 : " + vo);
	}
	
	@Test
	public void testDelete() throws Exception{
		int bno = 1;
		dao.delete(bno);
	}
	
	@Test
	public void testListPage() throws Exception {
		dao.listPage(2);
	}
	
	@Test            
	public void testListCriteria() throws Exception{      
		Criteria cri = new Criteria(); //페이지 번호, 페이지 당 display 게시글 갯수
		cri.setPage(3);
		cri.setPerPage(6);
		dao.listCriteria(cri);
	}
	
}
