package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/loginform", method=RequestMethod.GET)
	public String loginform() {
		
		return "user/loginform";
	}
	
	@RequestMapping(value="/user/login", method=RequestMethod.GET)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		
		UserVo authUser = userService.login(email, password);
		
		if(authUser != null) {		//로그인 상태이면
			session.setAttribute("authUser", authUser);
			return "main/index";
		} else{
			return "redirect:/user/loginform?result=fail";
		}
		
	}
	
	@RequestMapping(value="/user/joinform", method=RequestMethod.GET)
	public String joinform() {
		
		return "user/joinform";
	}
	  
	@RequestMapping(value="/user/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("join 진입");
		System.out.println(userVo.toString());
		
		userService.join(userVo);

		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/user/modifyform", method=RequestMethod.GET)
	public String modifyform(HttpSession session) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			return "redirect:/user/loginform";
		} else {

			return "user/modifyform";
		}
	}
	
	@RequestMapping(value="/user/modify", method=RequestMethod.GET)
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		
		System.out.println("modify진입");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		userVo.setNo(authUser.getNo());
		int result = userService.modify(userVo);
		System.out.println("처리결과 : " + result);
		authUser.setName(userVo.getName());
		
		return "redirect:/main";
	}
	
	@RequestMapping(value="/user/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		System.out.println("logout진입");
		session.invalidate();
		
		return "redirect:/main";
	}
		
	
	
	
}
