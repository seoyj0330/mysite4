package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileuploadService;
import com.javaex.vo.FileuploadVo;

@Controller
@RequestMapping("/fileupload")
public class FileuploadController {
	
	@Autowired
	private FileuploadService fservice;
	
	@RequestMapping("/form")
		public String form(Model model) {
		System.out.println("form진입!");
		
		List<FileuploadVo> flist = fservice.piclist();
		model.addAttribute("flist", flist);
		
			return  "fileupload/form";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, Model model) { //파일을 담을 수 있는 객체 이름 : multipart , jsp에 보내야하는데 담아서 보내야하니까 model 필요
		
		System.out.println(file.toString());
		String saveName= fservice.restore(file);
		
		String url ="upload/" + saveName;			//파일 이름앞에 upload/ 이것을 붙혀준거임
		
		// 모델 어트리뷰트에 담아 보냄 (dispatcherservlet이 result에) 
		model.addAttribute("url", url);
		
		return "fileupload/result";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") int no) {
		System.out.println("delete진입");
		
		int result = fservice.delete(no);
		System.out.println(no);
		System.out.println("처리결과" + result);
		
		return "redirect:/fileupload/form";
		
	}
}
