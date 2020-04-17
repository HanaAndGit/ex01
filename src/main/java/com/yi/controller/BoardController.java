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
import com.yi.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService service;
	
	@RequestMapping(value = "/board/register", method = RequestMethod.GET)
	public String registerGET() {
		
		return "/board/register";
	}
	
	@RequestMapping(value = "/board/register", method = RequestMethod.POST)
	public String registerPOST(Board vo, Model model) throws Exception {
		service.create(vo);
		/* return "/board/success"; */
		/* return "/board/list"; */
		/* return "forward:/board/list"; */
		//register 이후에 바로 list로 가고싶을 경우 
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception{
		List<Board> list = service.list();
		model.addAttribute("list", list);
		return "/board/listPage";
	}
	
	@RequestMapping(value = "/board/read", method = RequestMethod.GET)
	public String read(int bno, Model model) throws Exception{
		Board vo = service.readByNo(bno);
		int viewcnt = vo.getViewcnt();
		viewcnt++;
		vo.setViewcnt(viewcnt);
		service.updateCnt(vo);
		model.addAttribute("vo", vo);
		return "/board/read";
	}
	
	@RequestMapping(value = "/board/update", method = RequestMethod.GET)
	public String modify(int bno, Model model) throws Exception {
		Board vo = service.readByNo(bno);
		model.addAttribute("vo", vo);
		return "/board/update";
	}
	
	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public String update(Board vo, Model model) throws Exception {
		service.update(vo);
		model.addAttribute("vo", vo);
		 return "/board/update";
	}
	
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public String delete(int bno) throws Exception {
		service.delete(bno);
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/board/listPage", method = RequestMethod.GET)
	public String listPage(Criteria cri, Model model) throws Exception {
		List<Board> list = service.listCriteria(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.totalCount());
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		return "/board/listPage";
	}
	
	@RequestMapping(value = "/board/readPage", method = RequestMethod.GET)
	public String readPage(int bno, Criteria cri,Model model) throws Exception{
		Board vo = service.readByNo(bno);
		int viewcnt = vo.getViewcnt();
		viewcnt++;
		vo.setViewcnt(viewcnt);
		service.updateCnt(vo);
		model.addAttribute("vo", vo);
		model.addAttribute("cri", cri);
		return "/board/readPage";
	}
	
	@RequestMapping(value = "/board/deletePage", method = RequestMethod.GET)
	public String deletePage(int bno, Criteria cri) throws Exception {
		service.delete(bno);
		return "redirect:/board/listPage?page="+cri.getPage();
	}
	@RequestMapping(value = "/board/updatePage", method = RequestMethod.GET)
	public String modifyGET(int bno, Criteria cri, Model model) throws Exception {
		Board vo = service.readByNo(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("cri", cri);
		return "/board/update";
	}
	
	@RequestMapping(value = "/board/updatePage", method = RequestMethod.POST)
	public String updatePagePOST(Board vo, Criteria cri, Model model) throws Exception {
		service.update(vo);
		model.addAttribute("vo", vo);
		 return "redirect:/board/readPage?page="+cri.getPage()+"&bno="+vo.getBno();  
	}
}   
