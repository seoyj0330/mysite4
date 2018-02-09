package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.javaex.vo.FileuploadVo;

@Controller
public class FileuploadDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(FileuploadVo fvo) {
		sqlSession.insert("fu.insertpic", fvo);
	}

	public List<FileuploadVo> piclist() {
		List<FileuploadVo> filelist = sqlSession.selectList("fu.allList");
		return filelist;
	}

	public int delete(int no) {
		return sqlSession.delete("fu.deletepic",no);
	}
}
