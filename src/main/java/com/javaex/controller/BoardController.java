package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserBoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	private BoardService bService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model, @RequestParam(value="kwd", required=false, defaultValue="" )String kwd) {
		
		System.out.println("list 진입");
		List<UserBoardVo> ublist = bService.getList(kwd);

		model.addAttribute("list", ublist);

		return "board/list";
	}
	
	@RequestMapping(value="/writeform", method=RequestMethod.GET)
	public String writeform() {
	
		System.out.println("writeform 진입");
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo bVo, HttpSession session) {
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		System.out.println("write진입");
		//System.out.println(bVo);
		bVo.setUser_no(authUser.getNo());
		
		int result= bService.write(bVo);
		
		System.out.println(bVo.toString());
		System.out.println("처리결과 : " + result);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(@RequestParam("boardno") int boardno, Model model) {
		
		System.out.println("view진입");
		
		BoardVo bVo = bService.view(boardno);
		
		System.out.println(bVo);
		model.addAttribute("bVo", bVo);
		return"board/view";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam("deleteno") int deleteno) {
		System.out.println("delete진입");
		bService.delete(deleteno);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(Model model, HttpSession session, @RequestParam("boardno") int no) {
		
		System.out.println("modifyform 진입");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if(authUser !=null) {
			BoardVo bvo = bService.getArticle(no);
			model.addAttribute("bvo", bvo);
			
			return "board/modify";
		
		}else {
			return "user/loginform";
		}
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@RequestParam("no") int no, @ModelAttribute BoardVo boardVo) {
		
		System.out.println("modify 진입");
		
		boardVo.setNo(no);
		int result =bService.modify(boardVo);
		
		System.out.println(boardVo.toString());
		System.out.println("처리결과 : " + result);
		
		return "redirect:/board/list";
		
	}
	
	
}
