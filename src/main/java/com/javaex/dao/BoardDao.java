package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;
import com.javaex.vo.UserBoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<UserBoardVo> getList(int startRnum, int endRnum, String kwd){
		
		 Map<String, Object> map = new HashMap<String, Object>();
	      map.put("startRnum", startRnum);
	      map.put("endRnum", endRnum);
	      map.put("kwd", kwd);
	      System.out.println("dao : " + map.toString());
	     
	      return sqlSession.selectList("board.allList", map);
	   }

	public int write(BoardVo boardVo) {

		return sqlSession.insert("board.write", boardVo);
	}
	
	public int delete(int no) {
		
		return sqlSession.delete("board.delete", no);
	}
	
	public BoardVo getArticle(int no) {
		
		return sqlSession.selectOne("board.getArticle", no);
	}

	public int hit(int no) {
		
		return sqlSession.update("board.hit", no);
	}

	public int modify(BoardVo boardVo) {
		
		return sqlSession.update("board.modify", boardVo);
	}
	
/*	public List<UserBoardVo> search(String kwd) {
		
		List<UserBoardVo> slist =sqlSession.selectList("board.search","%"+kwd+"%");
		
		return slist;
	}*/
	
	public int selectTotalCount(String kwd) {
		return sqlSession.selectOne("board.totalCount",kwd);
	}
}
