package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession; 

	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = sqlSession.selectList("gb.allList");
		
		return list;
	}
	
	public int add(GuestbookVo guestbookVo) {
		return sqlSession.insert("gb.add", guestbookVo);
	}

	public int delete(int no, String password) {
		
		Map<String, Object> gbmap = new HashMap<String, Object>();
		gbmap.put("no", no);
		gbmap.put("password", password);

		return sqlSession.delete("gb.delete", gbmap);
	}
}
