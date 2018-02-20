package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserBoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao bDao;

	public Map<String, Object> getList(int crtPage, String kwd){
	
	      int listCnt = 10;
//	      int crtPage = 1; //원래는 parameter로 받아와야 하는 값임
	    
	      crtPage = (crtPage <=0 ) ? crtPage=1 : crtPage; //0보다 작거나 같으면 전자 , 아니면 후자
	      
	      int startRnum = (crtPage-1) * listCnt ; //0부터 설정. 0, 10, 20 ...  
	      int endRnum = startRnum + listCnt; //10, 20, 30 ...
	      
	      System.out.println("startRnum : " + startRnum);
	      System.out.println("endRnum : " + endRnum);
	      
	      List<UserBoardVo> boardlist = bDao.getList(startRnum, endRnum, kwd);
	  	//페이지 버튼 관련
	  	
	  	//전체 글 갯수 
	  	int totalCount = bDao.selectTotalCount(kwd);
	  	System.out.println("totalCount :" + totalCount);
	  	
	  	//페이지당 버튼 갯수
	  	int pageBtnCount = 5;
	  	
	  	int endPageBtnNo = (int)(Math.ceil(crtPage/(double)pageBtnCount)  *pageBtnCount);
	  	int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
	  	
	  	boolean next = false;
	  	if(endPageBtnNo * listCnt < totalCount) {
	  		next = true;
	  	}  else {
	  		endPageBtnNo = (int) (Math.ceil(totalCount/(double)listCnt));
	  	}
	  	
	  	boolean prev = false;
	  	if(startPageBtnNo !=1) {
	  		prev = true;
	  	}
/*	  	
 		원래 했던 키워드 방법
 		
  		List<UserBoardVo> ublist; 
		
		if(kwd== "") {
			ublist = bDao.getList(startRnum,endRnum);
		} else {
			ublist = bDao.search(kwd);
		}
	  	*/
	  	
	  	Map<String, Object> ubMap = new HashMap<String, Object>();			//주소값을 갖는것
	  	ubMap.put("boardlist", boardlist);
	  	ubMap.put("prev",prev);
	  	ubMap.put("startPageBtnNo",startPageBtnNo);
	  	ubMap.put("endPageBtnNo", endPageBtnNo);
	  	ubMap.put("next", next);
	  	ubMap.put("crtPage", crtPage);
	  	ubMap.put("kwd", kwd);
	  	
	  	System.out.println(ubMap.toString());
	  	return ubMap;
	
	}

	
	public int write(BoardVo boardVo) {

		return bDao.write(boardVo);
	}
	
	public int delete(int no) {
		
		return bDao.delete(no);
	}
	
	@Transactional
	public BoardVo view(int no) {
		bDao.hit(no);
		BoardVo boardVo = bDao.getArticle(no);
		return boardVo;
	}
	
	public BoardVo getArticle(int no) {
	
		return bDao.getArticle(no);
	}

	public int modify(BoardVo boardVo) {
	
		return bDao.modify(boardVo);
	}


}

