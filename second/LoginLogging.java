package com.kh.second.common;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kh.second.member.model.vo.Member;

@Repository
@Aspect
public class LoginLogging {
	private static final Logger logger = LoggerFactory.getLogger(LoginLogging.class);
	
	@Pointcut("execution(* com.kh.second.member.model.dao.MemberDao.loginCheck(..))")
	public void loginPointCut() {}
	
	@AfterReturning(pointcut="loginPointCut()", returning="returnObj")
	public void loginLog(JoinPoint jp, Object returnObj) {
		//비즈니스 메소드가 리턴한 결과 데이터를 다른 용도로 처리할 때 사용함
		if(returnObj instanceof Member) {
			Member member = (Member)returnObj;
			logger.info(new Date() + " : $" + member.getUserid() + "$ 님이 접속했습니다.");
		}
	}
	
}
