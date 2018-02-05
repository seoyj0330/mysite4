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
	
	public int add(GuestbookVo guestbookVo) {
		
		return gbdao.add(guestbookVo);
	}
	
	public int delete(int no, String password) {
		
		return gbdao.delete(no, password);
	}
}
