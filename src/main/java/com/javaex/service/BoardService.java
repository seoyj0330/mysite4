package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserBoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao bDao;

	public List<UserBoardVo> getList(){
		
		return bDao.getList();
	}
	
	public int write(BoardVo boardVo) {
		
		return bDao.write(boardVo);
	}
	
	public int delete(int no) {
		
		return bDao.delete(no);
	}
	
	public BoardVo view(int no) {
		bDao.hit(no);
		BoardVo boardVo = bDao.getArticle(no);
		return boardVo;
	}
	
	public BoardVo getArticle(int no) {
		return bDao.getArticle(no);
	}
}
