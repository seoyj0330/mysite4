package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileuploadDao;
import com.javaex.vo.FileuploadVo;

@Service
public class FileuploadService {
	
	@Autowired
	private FileuploadDao fDao;
	
	//파일(그림) 저장
	public String restore(MultipartFile file) {
		String saveDir = "D:\\javaStudy\\upload";
		
		FileuploadVo fvo = new FileuploadVo();
		//파일정보 수집
		
		
		//원래파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(exName);
		
		//저장파일이름		겹치면 덮어씌워버리니까 주의
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+exName;
		System.out.println(saveName);
		
		//파일저장위치(path)
		String filePath = saveDir + "\\" + saveName;
		System.out.println(filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println(fileSize);
		
		//vo만들어서 값 넣어주기		//파일 저장할때 DB저장하기위해서
		fvo.setOrgName(orgName);
		fvo.setExName(exName);
		fvo.setSaveName(saveName);
		fvo.setFilePath(filePath);
		fvo.setFileSize(fileSize);
		
		
		//파일카피
		try {
			
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir+"/"+saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			
			if(bout != null) {
				bout.close();
			}
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		
		//DB 저장
		fDao.insert(fvo);
		
		return saveName;
	}

	public List<FileuploadVo> piclist() {
		return fDao.piclist();
	}

	public int delete(int no) {
		
		return fDao.delete(no);
	}

	
}
 

