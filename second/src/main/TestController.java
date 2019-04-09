package com.kh.second.test.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.second.test.model.vo.Sample;
import com.kh.second.test.model.vo.User;

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
	
	//ajax test method ----------------------------------------------------
	@RequestMapping(value="test1.do", method=RequestMethod.POST)
	public void test1Method(Sample command, HttpServletResponse response) throws IOException {
		logger.info("test1.do run!"); //확인용 출력 구문
		System.out.println("command : " + command);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter(); //printWriter 해 줄라고 response 해 준 거
		
		if(command.getName().equals("신사임당")) {
			out.append("ok"); //단순한 문자열 출력일 떄는 append 씀
			out.flush();
		} else {
			out.append("fail");
			out.flush();
		}
		
		out.close();
	}
	
	@RequestMapping(value="test2.do", method=RequestMethod.POST)
	@ResponseBody //@ResponseBody는 밑에 있는 리턴하는 json 문자열을 response 객체에 담아서 보내라는 의미의 이노테이션임.
	public String test2Method(HttpServletResponse response) throws UnsupportedEncodingException {
		logger.info("test2.do run!");
		response.setContentType("application/json; charset=utf-8");
		
		JSONObject job = new JSONObject();
		job.put("no", 123);
		job.put("title", "test return json object");
		job.put("writer", URLEncoder.encode("홍길동", "utf-8"));
		job.put("content", URLEncoder.encode("코를 풀고 싶다.", "utf-8"));
		
		return job.toJSONString();
	}
	
	@RequestMapping(value="test3.do", method=RequestMethod.POST)
	public void test3Method(HttpServletResponse response) throws IOException {
		logger.info("test3.do run!");
		
		//List 를 json 배열로 만들어서 요청한 페이지로 전송
		List<User> list = new ArrayList<User>();
		
		list.add(new User("u1111", "p1111", "김가람", 27, "fnsk5432@naver.com", "010-2340-4764"));
		list.add(new User("u2222", "p2222", "김유정", 27, "kookkk13@naver.com", "010-2340-4763"));
		list.add(new User("u3333", "p3333", "차우리", 27, "devWoori98@naver.com", "010-2340-4767"));
		list.add(new User("u4444", "p4444", "한유진", 27, "hujin11@naver.com", "010-2340-4768"));
		list.add(new User("u5555", "p5555", "김현진", 34, "guswls464@naver.com", "010-2340-4765"));
		
		//전송용 객체 생성
		JSONObject sendObj = new JSONObject();
		//배열 객체 생성
		JSONArray jarr = new JSONArray();
		
		//list를 jarr에 복사
		for(User user : list) {
			//user 객체 저장용 json 객체 만들기
			JSONObject juser = new JSONObject();
			
			juser.put("userid", user.getUserid());
			juser.put("userpwd", user.getUserpwd());
			juser.put("username", user.getUsername());
			juser.put("age", user.getAge());
			juser.put("email", user.getEmail());
			juser.put("phone", user.getPhone());
			
			//jarr에 juser 저장
			jarr.add(juser);
		}

		sendObj.put("list", jarr);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(sendObj.toJSONString());
		out.flush();
		out.close();
		
	}
	
	
	@RequestMapping(value="test4.do", method=RequestMethod.POST)
	public ModelAndView test4Method(ModelAndView mv) throws UnsupportedEncodingException {
		logger.info("test4.do run!");
		
		//map 객체를 ModelAndView 에 담아서 JsonView로 보냄
		Sample samp = new Sample("유진", 27);
		samp.setName(URLEncoder.encode(samp.getName(), "utf-8"));

		Map<String, Sample> map = new HashMap<String, Sample>();
		map.put("samp", samp);
		
		mv.addObject(map);
		//뷰지정 : JsonView 를 bean 으로 등록하고, id를 뷰이름으로 지정함
		mv.setViewName("jsonView");
		
		
		return mv; //뷰리졸버로 전달되고, 요청한 ajax는 json 객체를 받음
	}
	
	@RequestMapping(value="test5.do", method=RequestMethod.POST)
	public ResponseEntity<String> test5Method(@RequestBody String param) throws ParseException{
		//request body에 저장되서 전송 온 json 문자열을 param 변수가 받음 
		logger.info("test5.do run!");
		
		//전송 온 json 문자열을 json 객체로 바꿈
		JSONParser jparser = new JSONParser();
		JSONObject job = (JSONObject)jparser.parse(param); 
		
		String name = (String)job.get("name");
		int age = ((Long)job.get("age")).intValue(); //long형으로 바꾼 뒤 int형으로 바꿔줄 수 있음
		
		System.out.println(name + ", " + age);
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@RequestMapping(value="test6.do", method=RequestMethod.POST)
	public ResponseEntity<String> test6Method(@RequestBody String param) throws ParseException{
		
		logger.info("test6.do run!");
		System.out.println("param : " + param);
		
		//전송 온 jsonArray 문자열을 jsonArray 객체로 바꿈
		JSONParser jparser = new JSONParser();
		JSONArray jarr = (JSONArray)jparser.parse(param);
		System.out.println("jarr size : " + jarr.size());
		
		for(int i = 0; i < jarr.size(); i++) {
			JSONObject job = (JSONObject)jarr.get(i);
			String name = (String)job.get("name");
			int age = ((Long)job.get("age")).intValue();
			System.out.println("name : " + name + ", age : " + age);
		}
		
		//정상 완료 되었음을 클라이언트로 보냄
		return new ResponseEntity<String>("success", HttpStatus.OK);
		
	}
	
}
