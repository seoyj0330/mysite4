package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao gbdao;
	
	public List<GuestbookVo> getList() {
		
		return gbdao.getList();
	}
	
	public void add(GuestbookVo guestbookVo) {
		
		gbdao.add(guestbookVo);
	}
	
	public int delete(int no, String password) {
		
		return gbdao.delete(no, password);
	}
	
	public List<GuestbookVo> getgbListPage(int page) {
		
		return gbdao.selectgbListPage(page);
	}
	
	public GuestbookVo insertajax(GuestbookVo gbVo) {
		gbdao.addAjax(gbVo);		//no값 받아온 것에다가 
		
		String sysdate = gbdao.sysdate(gbVo);	//날짜받아온것을 넣고
		gbVo.setRegDate(sysdate);	//vo에 날짜를 넣어줌
		
		return gbVo;
	}
	
}
