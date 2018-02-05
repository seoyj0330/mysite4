package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService gbService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
	
		System.out.println("list 진입");
		
		List<GuestbookVo> glist = gbService.getList();
		model.addAttribute("list", glist);
		
		return "/guestbook/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("add진입");
		
		int result= gbService.add(guestbookVo);
		
		System.out.println(guestbookVo.toString());
		System.out.println("처리결과 : " + result);
		
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
	public String deleteform() {
		System.out.println("deleteform 진입");
	
		return "guestbook/deleteform";
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		
		System.out.println("delete진입");
		
		int result = gbService.delete(no, password);
		System.out.println("처리결과 : " + result);
		
		return "redirect:/guestbook/list";
	}
		
	

	
}
