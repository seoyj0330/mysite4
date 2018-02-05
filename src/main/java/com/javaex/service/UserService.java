package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
	public UserVo login(String email, String password) {
		
		return userDao.getUser(email,password);
	}
	
	public void join(UserVo userVo){
		
		userDao.insert(userVo);
	}

	public int modify(UserVo userVo) {
		return userDao.modify(userVo);
	}
	
//	public UserVo modifyform(int no) {
//		
//		return userDao.getUser(no);
//	}
	

	
}
