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
	
	public void add(GuestbookVo guestbookVo) {
		sqlSession.insert("gb.add", guestbookVo);
	}

	public int delete(int no, String password) {
		
		Map<String, Object> gbmap = new HashMap<String, Object>();
		gbmap.put("no", no);
		gbmap.put("password", password);

		return sqlSession.delete("gb.delete", gbmap);
	}
	
	public List<GuestbookVo> selectgbListPage(int page){
		
		return sqlSession.selectList("gb.selectListByPage",page);
	}
	
	public GuestbookVo addAjax(GuestbookVo guestbookVo) {
		System.out.println("guestbookVo.no : " + guestbookVo.getNo());		//no받아오기전
		
		sqlSession.insert("gb.addAjax", guestbookVo);
		int getNo = guestbookVo.getNo();		//새로 받아온 값 
		System.out.println("받아온값 " + getNo);
			
		return guestbookVo;
	}
	
	public String sysdate(GuestbookVo guestbookVo) {
		
		return sqlSession.selectOne("gb.sysdate");
		
	}
}
