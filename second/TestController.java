package com.kh.second.test.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.second.test.model.vo.Sample;

@Controller
public class TestController {
	//로그 처리용 객체 의존성 주입(종속객체 주입) 처리
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	//뷰 페이지 내보내기 메소드
	@RequestMapping("moveAOP.do")
	public String moveAOPPage() {
		return "test/testAOPPage";
	}
	
	@RequestMapping("moveFileUp.do")
	public String moveFileUploadPage() {
		return "test/testFileUpload";
	}
	
	@RequestMapping("moveAjax.do")
	public String moveAjaxPage() {
		return "test/testAjaxPage";
	}
	
	@RequestMapping("testView.do")
	public String moveCryptoPage() {
		return "test/testCrypto";
	}
	
	//파일 업로드 처리용
	@RequestMapping(value="tinsert.do", method=RequestMethod.POST)
	public String testFileUpload(Sample sample, HttpServletRequest request, @RequestParam(name="upfile", required=false) MultipartFile file) {
		/*System.out.println("sample : " + sample);
		System.out.println("file : " + file.getOriginalFilename());*/
		
		//파일 저장 폴더 지정하기
		String savePath = request.getSession().getServletContext().getRealPath("resources/files/test");
		
		try {
			file.transferTo(new File(savePath + "\\" + file.getOriginalFilename()));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return "home";
	}
	
	//파일 다운로드 처리용
	@RequestMapping("fdown.do")
	public ModelAndView fileDownMethod(HttpServletRequest request, @RequestParam("filename") String fileName ) {
		//String fileName = request.getParameter("filename"); --> 매개변수에 @RequestParam("filename") String fileName 쓴 거랑 같음.
		
		String savePath = request.getSession().getServletContext().getRealPath("resources/files/test"); 
		File downFile = new File(savePath + "\\" + fileName);
		
		//ModelAndView(java.lang.String viewName, java.lang.String modelName(setAttribute할 때 이름 말함), java.lang.Objrct modelObject)
		//model == requestDispatcher
		//modelName == 이름, modelObject == 객체
		//위의 modelName과 modelObject는 request.setAttribute("이름", 객체);와 동일함
		return new ModelAndView("filedown", "downFile", downFile);
	}
	
	
	
	
	
	
	
	
	
}
