package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestbookController {

	@Autowired
	private GuestbookService gbService;
	
	@ResponseBody 		//json형태로 변형되어서 감
	@RequestMapping(value="/guestbook/api/list", method=RequestMethod.POST)
	public List<GuestbookVo> apiList(@RequestParam("page") int page) {
		System.out.println(page);
		List<GuestbookVo> gbList = gbService.getgbListPage(page);
		System.out.println(gbList.toString());
		
		return gbList;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/guestbook/api/add", method=RequestMethod.POST )
	//리턴형 : 값을 넘겨주는 자료형, 파라미터(): 받아오는 값의 자료형을 적어줌
	public GuestbookVo add(@RequestBody GuestbookVo guestbookVo) {
		
		gbService.insertajax(guestbookVo);
		System.out.println(guestbookVo.toString());

		return guestbookVo;
	}
	
	@ResponseBody
	@RequestMapping(value="/guestbook/api/delete", method=RequestMethod.POST)
	public int delete(@RequestBody GuestbookVo guestbookVo) {
		
		int no = guestbookVo.getNo();
		String password = guestbookVo.getPassword();
		
		return gbService.delete(no, password);
		
	}
	
	//리스트에서 삭제버튼을 누르면 삭제하고자 하는 no가 modal로 보내짐
	//modal에서 받은 no과 입력한password를 보내면 controller로 넘어옴
	
}
