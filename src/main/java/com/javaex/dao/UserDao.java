package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public UserVo getUser(String email, String password) {
	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("email", email);
		map.put("password", password);
		
		return sqlSession.selectOne("user.selectUserByEmailPW", map);
	}
	
	public void insert(UserVo userVo) {
	
		sqlSession.insert("user.join",userVo);
	}
	
/*	public UserVo getUser(int no) {
		
		return sqlSession.selectOne("user.selectUserByNo", no);
	}*/
	
	public int modify(UserVo userVo) {
		
		return sqlSession.update("user.modify", userVo);
	}
}
