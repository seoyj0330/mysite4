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
	
	public boolean emailCheck(String email) {
		boolean result;
		
		UserVo userVo = userDao.getUser(email);
		
		if(userVo !=null) {		//이미 email이 존재한다면 실패
			result = false; 
		} else {
			result = true;
		}
		return result;
	}

	
}
