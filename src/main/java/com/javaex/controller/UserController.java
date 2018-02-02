package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.GuestbookVo;
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
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("insert 진입");
		System.out.println(userVo.toString());
		
		userService.join(userVo);

		return "redirect:/joinsuccess";
	}
	
	
	
	
}
