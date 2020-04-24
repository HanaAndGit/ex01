package com.yi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yi.domain.Criteria;
import com.yi.domain.PageMaker;
import com.yi.domain.ReplyVO;
import com.yi.service.ReplyService;

@RestController
@RequestMapping("/replies/*")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	//replies 커맨드로 끝내기
	//@RequestBody : body에 실려오는 json 데이터를 class 객체에 알맞게 주입을 시킴
	//Talend API (크롬 확장 프로그램) 에서 테스트 가능
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		System.out.println(vo);
		ResponseEntity<String> entity = null;
		
		try {
			service.insert(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);//400 에러
		}
		return entity;
	}
	// /replies/all/8192 (매개변수/글 번호)
	//@PathVariable 주소줄에서 받은 변수를 매개변수로 쓰기
	@RequestMapping(value="/all{bno}", method= RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list (@PathVariable int bno){
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			List<ReplyVO> list = service.list(bno);
			entity = new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// replies/2
	
	@RequestMapping(value ="/{rno}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		try {
			vo.setRno(rno); //주소줄에 넘어온 rno 번호를 넣음
			service.update(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value ="/{rno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rno") int rno){
		ResponseEntity<String> entity = null;
		try {
			service.delete(rno);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// /replies/8192/1 (페이지번호)
//		@RequestMapping(value="/{bno}/{page}", method= RequestMethod.GET)
//		public ResponseEntity<List<ReplyVO>> listPage(@PathVariable int bno, @PathVariable("page") int page){
//			ResponseEntity<List<ReplyVO>> entity = null;
//			
//			try {
//				Criteria cri = new Criteria();
//				cri.setPage(page);
//				PageMaker pageMaker = new PageMaker();
//				pageMaker.setCri(cri);
//				pageMaker.setTotalCount(35);
//				List<ReplyVO> list = service.listPage(bno, cri);
//				entity = new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
//			}catch(Exception e) {
//				e.printStackTrace();
//				entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
//			}   
//			return entity;
//		}

	@RequestMapping(value="/{bno}/{page}", method= RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> listPage(@PathVariable int bno, @PathVariable("page") int page){
		ResponseEntity<Map<String,Object>> entity = null;
		
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			int totalCount = service.totalCount(bno);
			pageMaker.setTotalCount(totalCount);
			List<ReplyVO> list = service.listPage(bno, cri);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("pageMaker", pageMaker);
			entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}   
		return entity;
	}

	
}
