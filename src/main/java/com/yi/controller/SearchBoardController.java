package com.yi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yi.domain.Board;
import com.yi.domain.Criteria;
import com.yi.domain.PageMaker;
import com.yi.domain.SearchCriteria;
import com.yi.service.BoardService;

@Controller
@RequestMapping("/sboard/*") //command가 항상 /sboard/로 시작한다.
public class SearchBoardController {
	@Autowired
	BoardService service;
	
	@RequestMapping(value = "listPage", method = RequestMethod.GET)
	public String listPage(SearchCriteria cri, Model model) throws Exception {
		List<Board> list = service.listSearchCriteria(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		//검색했을 때 나오는 내용에 따른 페이지 수 (ex. 내용이 1개 => 버튼 1)
		pageMaker.setTotalCount(service.totalSearchCount(cri));     
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("cri", cri);
		return "/sboard/listPage";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String registerGET() {
		
		return "/sboard/register";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPOST(Board vo, Model model) throws Exception {
		service.create(vo);
		return "redirect:/sboard/listPage";
	}
	
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public String readPage(int bno, SearchCriteria cri,Model model) throws Exception{
		System.out.println("cri :" + cri);
		Board vo = service.readByNo(bno);  
		int viewcnt = vo.getViewcnt();
		viewcnt++;
		vo.setViewcnt(viewcnt);      
		service.updateCnt(vo);    
		model.addAttribute("vo", vo);   
		model.addAttribute("cri", cri);
		return "/sboard/read";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String modifyGET(int bno, SearchCriteria cri, Model model) throws Exception {
		Board vo = service.readByNo(bno);
		System.out.println("객체 : " + vo);
		model.addAttribute("vo", vo);
		model.addAttribute("cri", cri); 
		return "/sboard/update";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String updatePagePOST(Board vo, SearchCriteria cri, Model model) throws Exception {
		service.update(vo);
		//redirect로 돌아갈 때 : 객체 인식 x 
		model.addAttribute("bno", vo.getBno());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
		model.addAttribute("page", cri.getPage());
		 return "redirect:/sboard/read";  
		 
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String deletePage(int bno, SearchCriteria cri, Model model) throws Exception {
		service.delete(bno);
		model.addAttribute("page", cri.getPage());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
		return "redirect:/sboard/listPage";
	}
}
