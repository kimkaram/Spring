package com.kh.second.member.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.kh.second.member.model.service.MemberService;
import com.kh.second.member.model.vo.Member;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="bcrypto.do", method=RequestMethod.POST)
	public String testBcryptoPassword(@RequestParam("userpwd") String userPwd) {
		logger.info("암호 : " + userPwd);
		String buserPwd = bcryptPasswordEncoder.encode(userPwd);
		logger.info("변환암호 : " + buserPwd);
		
		//로그인시 전송온 암호와 crypto 처리된 암호값 비교시에는 matches(원래문자열, 암호화된문자열)
		
		logger.info("비교결과 : " + bcryptPasswordEncoder.matches(userPwd, buserPwd));
		
		return "test/testCrypto";
	}
	
	@RequestMapping(value="enroll.do")
	public String moveMemberJoinView() {
		return "member/enrollPage";
	}
	
	@RequestMapping(value="minsert.do", method=RequestMethod.POST)
	public String insertMember(Member member, @RequestParam(name="photo", required=false) MultipartFile mphoto, HttpServletRequest request) {
		logger.info("member : " + member);
		logger.info("프로필사진 : " + mphoto);
		
		String savePath = request.getSession().getServletContext().getRealPath("resources/files/photo");
		logger.info("savePath : " + savePath);
		
		try {
			mphoto.transferTo(new File(savePath + "\\" + mphoto.getOriginalFilename()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//비밀번호 암호화 처리
		member.setUserpwd(bcryptPasswordEncoder.encode(member.getUserpwd()));
		
		int result = memberService.insertMember(member);
		logger.info("minsert result : " + result);
		
		
		return "home";
	}
	
	@RequestMapping(value="login.do", method=RequestMethod.POST)
	public String loginCheck(Member member, HttpSession session, SessionStatus status) {
		
		Member loginMember = memberService.loginCheck(member);
		
		if(loginMember != null) {
			//로그인 성공시
			session.setAttribute("loginMember", loginMember);
			status.setComplete(); //요청 성공
			return "home";
		} else {
			//로그인 실패시
			return "common/error";
		}
		
	}
	
}
