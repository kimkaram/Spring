package com.kh.second.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterAdvice {
	@Pointcut("execution(* com.kh.second..*Impl.*(..))")
	public void allPointcut() {}
	
	@After("allPointcut()")
	public void finallyLog() {
		//예외 발생 여부에 상관없이 무조건 수행되는 어드바이스
		System.out.println("[후실행] 비즈니스 로직 수행 후 무조건 실행");
	}
	
}
